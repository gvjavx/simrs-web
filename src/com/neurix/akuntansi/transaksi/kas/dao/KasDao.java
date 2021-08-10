package com.neurix.akuntansi.transaksi.kas.dao;

import com.neurix.akuntansi.transaksi.kas.model.ItAkunKasEntity;
import com.neurix.akuntansi.transaksi.kas.model.KasDetail;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class KasDao extends GenericDao<ItAkunKasEntity, String> {

    @Override
    protected Class<ItAkunKasEntity> getEntityClass() {
        return ItAkunKasEntity.class;
    }

    public List<ItAkunKasEntity> getByWithUangMuka(Map mapCriteria) {
        List<ItAkunKasEntity> results = new ArrayList<>();
        List<Object[]> resultQuery = new ArrayList<Object[]>();

        String flag = mapCriteria.get("flag") == null ? "%" : mapCriteria.get("flag").toString();
        String kasId = mapCriteria.get("kas_id") == null ? "%" : mapCriteria.get("kas_id").toString();
        String noJurnal = mapCriteria.get("no_jurnal") == null ? "%" : mapCriteria.get("no_jurnal").toString();
        String branchId = mapCriteria.get("branch_id") == null ? "%" : mapCriteria.get("branch_id").toString();
        String transaksi = mapCriteria.get("tipe_transaksi") == null ? "%" : mapCriteria.get("tipe_transaksi").toString();
        String tipeKas = mapCriteria.get("tipe_kas") == null ? "%" : mapCriteria.get("tipe_kas").toString();
        String billing = mapCriteria.get("is_billing") == null ? "%" : mapCriteria.get("is_billing").toString();
        String tangal1 = "";
        String tangal2 = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            java.util.Date sekarang =  new java.util.Date();
            java.util.Date kemarin = new DateTime(sekarang).minusDays(5).toDate();
            tangal1 = sdf.format(kemarin);
            tangal2 = sdf.format(sekarang);
        }
        catch (Exception e){
            e.getMessage();
        }
        String tanggalDari = mapCriteria.get("tanggal_dari") == null ? tangal1 : mapCriteria.get("tanggal_dari").toString();
        String tanggalSelesai = mapCriteria.get("tanggal_selesai") == null ? tangal2 : mapCriteria.get("tanggal_selesai").toString();

        String query = "" +
                "select * " +
                "from ( " +
                "select\n" +
                "       uang.id\n" +
                "                                      as kas_id,\n" +
                "       null\n" +
                "                                      as tipe_transaksi,\n" +
                "       CAST(uang.last_update as Date)\n" +
                "                                      as tanggal,\n" +
                "       null\n" +
                "                                      as coa_kas ,\n" +
                "       uang.jumlah\n" +
                "                                      as bayar,\n" +
                "       null\n" +
                "                                      as keterangan ,\n" +
                "       null\n" +
                "                                      as no_slip_bank,\n" +
                "       uang.flag\n" +
                "                                      as flag,\n" +
                "       uang.action\n" +
                "                                      as \"action\" ,\n" +
                "       jurnal.branch_id\n" +
                "                                      as branch_id,\n" +
                "       jurnal.no_jurnal\n" +
                "                                      as no_jurnal,\n" +
                "       uang.status_bayar\n" +
                "                                      as registered_flag,\n" +
                "       uang. last_update\n" +
                "                                      as registered_date,\n" +
                "       uang.last_update_who\n" +
                "                                      as registered_who,\n" +
                "       'KM'\n" +
                "                                      as tipe_kas,\n" +
                "       null\n" +
                "                                      as approval_keuangan_flag,\n" +
                "       null\n" +
                "                                      as approval_keuangan_id,\n" +
                "       null\n" +
                "                                      as approval_keuangan_name,\n" +
                "       null\n" +
                "                                      as approval_keuangan_date ,\n" +
                "       null\n" +
                "                                      as approval_kasub_keuangan_flag,\n" +
                "       null\n" +
                "                                      as approval_kasub_keuangan_id,\n" +
                "       null\n" +
                "                                      as approval_kasub_keuangan_name,\n" +
                "       null\n" +
                "                                      as approval_kasub_keuangan_date,\n" +
                "       uang.created_date\n" +
                "                                      as created_date,\n" +
                "       uang.last_update\n" +
                "                                      as last_update,\n" +
                "       uang.created_who\n" +
                "                                      as created_who,\n" +
                "       uang.last_update_who\n" +
                "                                      as last_update_who,\n" +
                "       null\n" +
                "                                      as metode_pembayaran,\n" +
                "       null\n" +
                "                                      as currencyId,\n " +
                "       cast('Y' as Varchar) " +
                "                                      as billing " +
                "from it_akun_jurnal jurnal\n" +
                "       left join it_akun_kas kas\n" +
                "         on kas.no_jurnal = jurnal.no_jurnal\n" +
                "       join it_simrs_uang_muka_pendaftaran uang\n" +
                "         on jurnal.no_jurnal = uang.no_jurnal\n" +
                "where uang.flag ilike :flag " +
                "and jurnal.branch_id ilike :branchId " +
                "and jurnal.no_jurnal ilike :noJurnal " +
                // Fahmi 2021-08-09, Filter kurang Kas ID
                "and uang.id ilike :kasId " +
                // End Fahmi
                "and uang.last_update between TO_TIMESTAMP(:tanggal1,'yyyy-MM-dd')  and TO_TIMESTAMP(:tanggal2,'yyyy-MM-dd') " +

                "union\n" +

                "select kas.*" +
                ", cast('N' as Varchar) as billing\n" +
                "from it_akun_jurnal jurnal\n" +
                "left join it_simrs_uang_muka_pendaftaran uang\n" +
                "on uang.no_jurnal = jurnal.no_jurnal\n" +
                "join it_akun_kas kas on kas.no_jurnal = jurnal.no_jurnal " +
                "where kas.flag like :flag " +
                "and jurnal.branch_id like :branchId " +
                "and jurnal.no_jurnal like :noJurnal " +
                "and tipe_kas like :tipeKas " +
                "and kas_id like :kasId " +
                "and tipe_transaksi like :tipeTransaksi " +
                "and tanggal between TO_TIMESTAMP(:tanggal1,'yyyy-MM-dd')  and TO_TIMESTAMP(:tanggal2,'yyyy-MM-dd')  " +
                ") as head where billing ilike :billing "
                ;

        resultQuery = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("flag", flag)
                .setParameter("branchId", branchId)
                .setParameter("noJurnal", noJurnal)
                .setParameter("tipeTransaksi", transaksi)
                .setParameter("tipeKas", tipeKas)
                .setParameter("kasId", kasId)
                .setParameter("tanggal1", tanggalDari)
                .setParameter("tanggal2", tanggalSelesai)
                .setParameter("billing", billing)
                .list();

        for (Object[] row : resultQuery) {
            if (row.length > 0) {
                ItAkunKasEntity itAkunKasEntity = new ItAkunKasEntity();
                itAkunKasEntity.setKasId(row[0].toString());
                itAkunKasEntity.setTipeTransaksi(row[1] != null ? row[1].toString() : "");
                itAkunKasEntity.setTanggal(row[2] != null ? (Date) row[2] : null);
                itAkunKasEntity.setCoaKas(row[3] != null ? row[3].toString() : "");
                itAkunKasEntity.setBayar(row[4] != null ? (BigDecimal) row[4] : BigDecimal.ZERO);
                itAkunKasEntity.setKeterangan(row[5] != null ? row[5].toString() : "");
                itAkunKasEntity.setNoSlipBank(row[6] != null ? row[6].toString() : "");
                itAkunKasEntity.setFlag(row[7] != null ? row[7].toString() : "");
                itAkunKasEntity.setAction(row[8] != null ? row[8].toString() : "");
                itAkunKasEntity.setBranchId(row[9] != null ? row[9].toString() : "");
                itAkunKasEntity.setNoJurnal(row[10] != null ? row[10].toString() : "");
                itAkunKasEntity.setRegisteredFlag(row[11] != null ? row[11].toString() : "");
                itAkunKasEntity.setRegisteredDate(row[12] != null ? (Timestamp) row[12] : null);
                itAkunKasEntity.setRegisteredWho(row[13] != null ? row[13].toString() : "");
                itAkunKasEntity.setTipeKas(row[14] != null ? row[14].toString() : "");
                itAkunKasEntity.setApprovalKeuanganFlag(row[15] != null ? row[15].toString() : "");
                itAkunKasEntity.setApprovalKeuanganId(row[16] != null ? row[16].toString() : "");
                itAkunKasEntity.setApprovalKeuanganName(row[17] != null ? row[17].toString() : "");
                itAkunKasEntity.setApprovalKeuanganDate(row[18] != null ? (Timestamp) row[18] : null);
                itAkunKasEntity.setApprovalKasubKeuanganFlag(row[19] != null ? row[19].toString() : "");
                itAkunKasEntity.setApprovalKasubKeuanganId(row[20] != null ? row[20].toString() : "");
                itAkunKasEntity.setApprovalKasubKeuanganName(row[21] != null ? row[21].toString() : "");
                itAkunKasEntity.setApprovalKasubKeuanganDate(row[22] != null ? (Timestamp) row[22] : null);
                itAkunKasEntity.setCreatedDate(row[23] != null ? (Timestamp) row[23] : null);
                itAkunKasEntity.setLastUpdate(row[24] != null ? (Timestamp) row[24] : null);
                itAkunKasEntity.setCreatedWho(row[25] != null ? row[25].toString() : "");
                itAkunKasEntity.setLastUpdateWho(row[26] != null ? row[26].toString() : "");
                itAkunKasEntity.setMetodePembayaran(row[27] != null ? row[27].toString() : "");

                results.add(itAkunKasEntity);
            }
        }
        return results;
    }

    @Override
    public List<ItAkunKasEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunKasEntity.class);
        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("kas_id") != null) {
                criteria.add(Restrictions.eq("kasId", (String) mapCriteria.get("kas_id")));
            }
            if (mapCriteria.get("no_jurnal") != null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tipe_transaksi") != null) {
                criteria.add(Restrictions.eq("tipeTransaksi", (String) mapCriteria.get("tipe_transaksi")));
            }
            if (mapCriteria.get("tipe_kas") != null) {
                criteria.add(Restrictions.eq("tipeKas", (String) mapCriteria.get("tipe_kas")));
            }
            if (mapCriteria.get("tanggal") != null) {
                criteria.add(Restrictions.eq("tanggal", (String) mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("tanggal_dari") != null && mapCriteria.get("tanggal_selesai") != null) {
                criteria.add(Restrictions.between("tanggal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
            } else {
                if (mapCriteria.get("tanggal_dari") != null) {
                    criteria.add(Restrictions.ge("tanggal", mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai") != null) {
                    criteria.add(Restrictions.le("tanggal", mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("kasId"));
        List<ItAkunKasEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKasId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kas')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PUP" + sId;
    }

    public List<KasDetail> getSearchNotaKas(String masterId, String branchId, String tipeBayar, String divisiId, String rekeningId) throws HibernateException {
        List<KasDetail> listOfResult = new ArrayList<KasDetail>();
        String whereBranch = "";
        String whereDivisi = "";
        String wheremaster = "";
        String wherePosisi = "";

        if (!("").equalsIgnoreCase(branchId)) {
            whereBranch = " and a.branch_id IN (" + branchId + ") ";
        }
        if (!("").equalsIgnoreCase(divisiId)) {
            whereDivisi = " and divisi_id = '" + divisiId + "' ";
        }
        if (!("").equalsIgnoreCase(masterId)) {
            wheremaster = " and master_id = '" + masterId + "' ";
        }
        switch (tipeBayar) {
            case "JKK":
                wherePosisi = "foo.debit < 0 \n";
                break;
            case "JKM":
                wherePosisi = "foo.debit > 0 \n";
                break;
            case "JKR":
                wherePosisi = "foo.debit < 0 \n";
        }

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "  master_id, \n" +
                "  nomor_rekening, \n" +
                "  noNota, \n" +
                "  to_char(debit, '99G999G999G999G999') as debit ,\n" +
                "  divisi_id\n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      b.master_id, \n" +
                "      b.nomor_rekening, \n" +
                "      a.mata_uang_id as mataUangId, \n" +
                "      b.no_nota as noNota, \n" +
                "\t  b.divisi_id,\n" +
                "      sum(jumlah_debit - jumlah_kredit) as debit \n" +
                "    from \n" +
                "      it_akun_jurnal a \n" +
                "      inner join (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal_detail \n" +
                "        where \n" +
                "          nomor_rekening = '" + rekeningId + "'\n" +
                "      ) b on a.no_jurnal = b.no_jurnal and b.no_nota != '' \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y' \n" +
                whereBranch +
                "    group by \n" +
                "      b.no_nota, \n" +
                "      a.mata_uang_id, \n" +
                "      b.master_id, \n" +
                "      b.nomor_rekening,\n" +
                "\t  b.divisi_id\n" +
                "  ) foo \n" +
                "where \n" +
                wherePosisi +
                wheremaster +
                whereDivisi;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            KasDetail data = new KasDetail();
            data.setMasterId((String) row[0]);
            data.setRekeningId((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setStJumlahPembayaran((String) row[3]);
            data.setDivisiId((String) row[4]);

            listOfResult.add(data);
        }
        return listOfResult;
    }


    public List<KasDetail> searchPengajuanBiaya(String branchId) {
        List<KasDetail> listOfResult = new ArrayList<KasDetail>();

        String query = "select\n" +
                "        pbd.pengajuan_biaya_detail_id,\n" +
                "        pbd.branch_id,\n" +
                "        pbd.divisi_id\n" +
                "    from\n" +
                "        it_akun_pengajuan_biaya_detail pbd\n" +
                "    LEFT JOIN it_akun_jurnal j ON pbd.pengajuan_biaya_detail_id = j.pengajuan_biaya_id\n" +
                "\tLEFT JOIN it_akun_jurnal_detail jd ON pbd.pengajuan_biaya_detail_id = jd.no_nota\n" +
                "    where\n" +
                "        pbd.branch_id='" + branchId + "'\n" +
                "        and closed='Y'\n" +
                "\t\tand j.no_jurnal is null\n" +
                "        and (\n" +
                "            j.registered_flag is null\n" +
                "            OR j.registered_flag='N'\n" +
                "        )\n" +
                "\t\tand jd.no_jurnal is null\n" +
                "        and pbd.flag='Y'";
        List<Object[]> results;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).list();
        KasDetail data;
        if (results.size() > 0) {
            for (Object[] rows : results) {
                data = new KasDetail();
                data.setNoNota(rows[0].toString());
                listOfResult.add(data);
            }
        }

        return listOfResult;
    }
}
