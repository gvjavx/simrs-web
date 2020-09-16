package com.neurix.akuntansi.transaksi.tutupperiod.dao;

import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItAkunTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 20/03/20.
 */
public class TutupPeriodDao extends GenericDao<ItAkunTutupPeriodEntity, String> {

    @Override
    protected Class<ItAkunTutupPeriodEntity> getEntityClass() {
        return ItAkunTutupPeriodEntity.class;
    }

    @Override
    public List<ItAkunTutupPeriodEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunTutupPeriodEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("rekening_id") != null)
            criteria.add(Restrictions.eq("rekeningId", mapCriteria.get("rekening_id").toString()));
        if (mapCriteria.get("parent_id") != null)
            criteria.add(Restrictions.eq("parentId", mapCriteria.get("parent_id").toString()));
        if (mapCriteria.get("id_tutup_period") != null)
            criteria.add(Restrictions.eq("idTutupPeriod", mapCriteria.get("id_tutup_period").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_tutup_period')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<TutupPeriod> getListDetailJurnalByCriteria(TutupPeriod bean){

        BigDecimal dcBulan = new BigDecimal(bean.getBulan());
        BigDecimal dcTahun = new BigDecimal(bean.getTahun());
        String rekenigId = "%";
        String tipeJurnalId = "%";
        String noJurnal = "%";
        if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())){
            rekenigId = bean.getRekeningId();
        }
        if (bean.getTipeJurnalId() != null && !"".equalsIgnoreCase(bean.getTipeJurnalId())){
            rekenigId = bean.getTipeJurnalId();
        }
        if (bean.getNoJurnal() != null && !"".equalsIgnoreCase(bean.getNoJurnal())){
            noJurnal = bean.getNoJurnal();
        }

        String SQL = "SELECT \n" +
                "dt.rekening_id,\n" +
                "kd.parent_id,\n" +
                "kd.kode_rekening,\n" +
                "kd.nama_kode_rekening,\n" +
                "SUM(dt.jumlah_debit) as jumlah_debit,\n" +
                "SUM(dt.jumlah_kredit) as jumlah_kredit\n" +
                "FROM it_akun_jurnal h\n" +
                "INNER JOIN it_akun_jurnal_detail dt ON dt.no_jurnal = h.no_jurnal\n" +
                "INNER JOIN im_akun_kode_rekening kd ON kd.rekening_id = dt.rekening_id\n" +
                "WHERE registered_flag = 'Y'\n" +
                "AND EXTRACT(MONTH FROM h.tanggal_jurnal) = :bulan \n" +
                "AND EXTRACT(YEAR FROM h.tanggal_jurnal) = :tahun  \n" +
                "AND h.branch_id = :unit  \n" +
                "AND dt.rekening_id LIKE :rekening \n" +
                "AND h.tipe_jurnal_id LIKE :tipeJurnalId \n" +
                "AND h.no_jurnal LIKE :nojurnal \n" +
                "GROUP\n" +
                "BY \n" +
                "dt.rekening_id,\n" +
                "kd.parent_id,\n" +
                "kd.kode_rekening,\n" +
                "kd.nama_kode_rekening\n" +
                "ORDER BY kd.parent_id, kd.kode_rekening\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", bean.getUnit())
                .setParameter("bulan", dcBulan)
                .setParameter("tahun", dcTahun)
                .setParameter("rekening", rekenigId)
                .setParameter("tipeJurnalId", tipeJurnalId)
                .setParameter("nojurnal", noJurnal)
                .list();

        List<TutupPeriod> tutupPeriods = new ArrayList<>();
        if (results.size() > 0){
            TutupPeriod tutupPeriod;
            for (Object[] obj : results){
                tutupPeriod = new TutupPeriod();
                tutupPeriod.setRekeningId(obj[0].toString());
                tutupPeriod.setParentId(obj[1].toString());
                tutupPeriod.setKodeRekening(obj[2].toString());
                tutupPeriod.setNamaKodeRekening(obj[3].toString());
                tutupPeriod.setJumlahDebit(obj[4] == null ? new BigDecimal(0) : (BigDecimal) obj[4]);
                tutupPeriod.setJumlahKredit(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
                tutupPeriods.add(tutupPeriod);
            }
        }

        return tutupPeriods;
    }

    public List<TutupPeriod> getListDetailJurnalByCriteriaPerDivisi(TutupPeriod bean){

        BigDecimal dcBulan = new BigDecimal(bean.getBulan());
        BigDecimal dcTahun = new BigDecimal(bean.getTahun());
        String rekenigId = "%";
        if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())){
            rekenigId = bean.getRekeningId();
        }

        String SQL = "SELECT\n" +
                "  dt.rekening_id,\n" +
                "  kd.parent_id,\n" +
                "  kd.kode_rekening,\n" +
                "  kd.nama_kode_rekening,\n" +
                "  SUM(dt.jumlah_debit) as jumlah_debit,\n" +
                "  SUM(dt.jumlah_kredit) as jumlah_kredit,\n" +
                "  dt.master_id,\n" +
                "  dt.divisi_id,\n" +
                "  dt.pasien_id,\n" +
                "  dt.kd_barang\n" +
                "FROM it_akun_jurnal h\n" +
                "  INNER JOIN it_akun_jurnal_detail dt ON dt.no_jurnal = h.no_jurnal\n" +
                "  INNER JOIN im_akun_kode_rekening kd ON kd.rekening_id = dt.rekening_id\n" +
                "WHERE registered_flag = 'Y'\n" +
                "      AND EXTRACT(MONTH FROM h.tanggal_jurnal) = :bulan \n" +
                "      AND EXTRACT(YEAR FROM h.tanggal_jurnal) = :tahun \n" +
                "      AND h.branch_id = :unit \n" +
                "      AND dt.rekening_id LIKE :rekening \n" +
                "GROUP\n" +
                "BY\n" +
                "  dt.rekening_id,\n" +
                "  kd.parent_id,\n" +
                "  kd.kode_rekening,\n" +
                "  kd.nama_kode_rekening,\n" +
                "  dt.master_id,\n" +
                "  dt.divisi_id,\n" +
                "  dt.pasien_id,\n" +
                "  dt.kd_barang\n" +
                "ORDER BY kd.parent_id, kd.kode_rekening";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("unit", bean.getUnit())
                .setParameter("bulan", dcBulan)
                .setParameter("tahun", dcTahun)
                .setParameter("rekening", rekenigId)
                .list();

        List<TutupPeriod> tutupPeriods = new ArrayList<>();
        if (results.size() > 0){
            TutupPeriod tutupPeriod;
            for (Object[] obj : results){
                tutupPeriod = new TutupPeriod();
                tutupPeriod.setRekeningId(obj[0].toString());
                tutupPeriod.setParentId(obj[1].toString());
                tutupPeriod.setKodeRekening(obj[2].toString());
                tutupPeriod.setNamaKodeRekening(obj[3].toString());
                tutupPeriod.setJumlahDebit(obj[4] == null ? new BigDecimal(0) : (BigDecimal) obj[4]);
                tutupPeriod.setJumlahKredit(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
                tutupPeriod.setMasterId(obj[6] == null ? "" : obj[6].toString());
                tutupPeriod.setDivisiId(obj[7] == null ? "" : obj[7].toString());
                tutupPeriod.setPasienId(obj[8] == null ? "" : obj[8].toString());
                tutupPeriod.setKdBarang(obj[9] == null ? "" : obj[9].toString());
                tutupPeriods.add(tutupPeriod);
            }
        }

        return tutupPeriods;
    }

    public Integer getLowestLevelKodeRekening(){

        String SQL = "SELECT rekening_id, MAX(level) FROM im_akun_kode_rekening \n" +
                "GROUP BY rekening_id ORDER BY level DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        Integer result = new Integer(0);
        if (results.size() > 0){
            for (Object[] obj : results){
                BigInteger bigresult = obj[1] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[1];
                result = new Integer(bigresult.intValue());
            }
        }

        return result;
    }

    public List<String> getListNoJurnalPending(String bulan, String tahun, String unit){

        BigDecimal dcBulan = new BigDecimal(bulan);
        BigDecimal dcTahun = new BigDecimal(tahun);

        String SQL = "SELECT \n" +
                "jn.no_jurnal, \n" +
                "jn.branch_id \n" +
                "FROM it_akun_jurnal_pending jn\n" +
                "WHERE jn.registered_flag = 'Y'\n" +
                "AND EXTRACT(MONTH FROM jn.tanggal_jurnal) = :bulan\n" +
                "AND EXTRACT(YEAR FROM jn.tanggal_jurnal) = :tahun\n" +
                "AND jn.branch_id = :unit\n" +
                "GROUP BY jn.no_jurnal, jn.branch_id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("bulan", dcBulan)
                .setParameter("tahun", dcTahun)
                .setParameter("unit",unit)
                .list();

        List<String> noJurnals = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                noJurnals.add(obj[0].toString());
            }
        }
        return noJurnals;
    }

    public String checkIsPendingTransitorisByNoJurnal(String noJurnal){
        String SQL = "SELECT no_jurnal, jumlah_debit\n" +
                "FROM it_akun_jurnal_detail_pending jp \n" +
                "WHERE jumlah_debit > '0'\n" +
                "AND rekening_id = '00046'\n" +
                "AND no_jurnal = :noJurnal\n" +
                "GROUP BY no_jurnal, jumlah_debit";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("noJurnal", noJurnal)
                .list();

        String result = "N";
        if (results.size() > 0){
            result = "Y";
        }

        return result;
    }

    public String checkIfJurnalTransitoris(String transId){

        String SQL = "SELECT posisi, keterangan FROM im_akun_mapping_jurnal\n" +
                "WHERE keterangan = 'piutang_transistoris_pasien_rawat_inap'\n" +
                "AND posisi = 'D'\n" +
                "AND trans_id = :transId";


        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("transId", transId)
                .list();

        String found = "N";
        if (results.size() > 0){
            found = "Y";
        }

        return found;
    }

    public List<SaldoAkhir> getNilaiSaldoAkhir(String periode, String branchId, String rekeningId, BigInteger level){

        String SQL = "SELECT\n" +
                "a.rekening_id,\n" +
                "a.periode, \n" +
                "a.posisi,\n" +
                "a.saldo,\n" +
                "b.nama_kode_rekening" +
                "FROM it_akun_saldo_akhir a\n" +
                "INNER JOIN im_akun_kode_rekening b ON b.rekening_id = a.rekening_id\n" +
                "WHERE a.periode LIKE '%'\n" +
                "AND a.branch_id = 'RS01'\n" +
                "AND a.rekening_id LIKE '00199'\n" +
                "AND b.level = '5'\n" +
                "AND a.saldo > 0\n" +
                "ORDER BY b.kode_rekening";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("periode", periode)
                .setParameter("unit", branchId)
                .setParameter("rekening", rekeningId)
                .setParameter("level", level)
                .list();

        List<SaldoAkhir> saldoAkhirs  = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                SaldoAkhir saldoAkhir = new SaldoAkhir();
                saldoAkhir.setRekeningId(obj[0].toString());
                saldoAkhir.setPeriode(obj[1].toString());
                saldoAkhir.setPosisi(obj[2].toString());
                saldoAkhir.setSaldo((BigDecimal) obj[3]);
                saldoAkhir.setNamaKodeRekening(obj[4].toString());
                saldoAkhirs.add(saldoAkhir);
            }
        }
        return saldoAkhirs;
    }
}
