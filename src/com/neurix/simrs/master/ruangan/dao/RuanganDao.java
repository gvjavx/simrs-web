package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RuanganDao extends GenericDao<MtSimrsRuanganEntity, String> {
    @Override
    protected Class<MtSimrsRuanganEntity> getEntityClass() {
        return MtSimrsRuanganEntity.class;
    }

    @Override
    public List<MtSimrsRuanganEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_ruangan") != null) {
                criteria.add(Restrictions.eq("idRuangan", (String) mapCriteria.get("id_ruangan")));
            }
            if (mapCriteria.get("nama_ruangan") != null) {
                criteria.add(Restrictions.ilike("namaRuangan", "%" + (String) mapCriteria.get("nama_ruangan") + "%"));
            }
            if (mapCriteria.get("id_kelas_ruangan") != null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("status_ruangan") != null) {
                criteria.add(Restrictions.eq("statusRuangan", (String) mapCriteria.get("status_ruangan")));
            }
            if (mapCriteria.get("no_ruangan") != null) {
                criteria.add(Restrictions.eq("noRuangan", (String) mapCriteria.get("no_ruangan")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("id_kelas_ruangan") != null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("status_ruangan") != null) {
                criteria.add(Restrictions.eq("statusRuangan", (String) mapCriteria.get("status_ruangan")));
            }

            if (mapCriteria.get("tarif") != null) {
                criteria.add(Restrictions.eq("tarif", (BigInteger) mapCriteria.get("tarif")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }


        }

        // Order by
        criteria.addOrder(Order.asc("idRuangan"));

        List<MtSimrsRuanganEntity> results = criteria.list();

        return results;
    }

    public String getNextIdRuangan() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ruangan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<Ruangan> getListRuangan(Ruangan bean) {

        String idRuang = "%";
        String idkelas = "%";
        String namaRuang = "%";

        List<Ruangan> ruanganList = new ArrayList<>();

        if (bean != null) {
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                idRuang = bean.getIdRuangan();
            }

            if (bean.getNamaRuangan() != null && !"".equalsIgnoreCase(bean.getNamaRuangan())) {
                namaRuang = bean.getNamaRuangan();
            }

            if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
                idkelas = bean.getIdKelasRuangan();
            }

            String SQL = "SELECT \n" +
                    "a.id_kelas_ruangan, \n" +
                    "b.id_ruangan, \n" +
                    "b.nama_ruangan, \n" +
                    "b.no_ruangan, \n" +
                    "c.id_detail_checkup, \n" +
                    "c.tgl_masuk, \n" +
                    "tt.id_tempat_tidur, \n" +
                    "tt.nama_tempat_tidur,\n" +
                    "c.cover_biaya,\n" +
                    "c.id_jenis_periksa_pasien,\n" +
                    "c.nama,\n" +
                    "c.id_pasien\n" +
                    "FROM im_simrs_kelas_ruangan a\n" +
                    "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON b.id_ruangan = tt.id_ruangan\n" +
                    "LEFT JOIN (\n" +
                    "SELECT a.id_detail_checkup, a.id_ruangan, a.tgl_masuk, c.cover_biaya, c.id_jenis_periksa_pasien, b.nama, b.id_pasien\n" +
                    "FROM it_simrs_rawat_inap a\n" +
                    "INNER JOIN it_simrs_header_checkup b\n" +
                    "ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN it_simrs_header_detail_checkup c \n" +
                    "ON a.id_detail_checkup = c.id_detail_checkup\n" +
                    "AND a.status = '1' \n" +
                    "WHERE a.flag = 'Y' \n" +
                    "AND b.branch_id LIKE :branchId) c ON tt.id_tempat_tidur = c.id_ruangan\n" +
                    "WHERE a.id_kelas_ruangan LIKE :idKelas \n" +
                    "AND b.id_ruangan LIKE :idRuang \n" +
                    "AND b.nama_ruangan LIKE :namaRuang \n" +
                    "ORDER BY a.id_kelas_ruangan ASC";

            List<Object[]> results = new ArrayList<>();

            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idKelas", idkelas)
                    .setParameter("idRuang", idRuang)
                    .setParameter("namaRuang", namaRuang)
                    .setParameter("branchId", CommonUtil.userBranchLogin())
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdKelasRuangan(obj[0] == null ? "" : obj[0].toString());
                    ruangan.setIdRuangan(obj[1] == null ? "" : obj[1].toString());
                    ruangan.setNamaRuangan(obj[2] == null ? "" : obj[2].toString());
                    ruangan.setNoRuangan(obj[3] == null ? "" : obj[3].toString());
                    ruangan.setIdDetailCheckup(obj[4] == null ? "" : obj[4].toString());
                    ruangan.setTglMasuk(obj[5] == null ? "" : obj[5].toString());
                    if(obj[6] != null){
                        ruangan.setIdTempatTidur(obj[6].toString());
                    }
                    if(obj[7] != null){
                        ruangan.setNamaTempatTidur(obj[7].toString());
                    }
                    if(obj[4] != null && !"".equalsIgnoreCase(obj[4].toString())){
                        ruangan.setTarifTindakan(getSumAllTarifTindakan(obj[4].toString()));
                    }
                    if(obj[8] != null && !"".equalsIgnoreCase(obj[8].toString())){
                        ruangan.setTarifBpjs((BigDecimal) obj[8]);
                    }
                    if(obj[9] != null && !"".equalsIgnoreCase(obj[9].toString())){
                        ruangan.setTipeTransaksi(obj[9].toString());
                        if("bpjs".equalsIgnoreCase(ruangan.getTipeTransaksi())){
                            if(ruangan.getTarifBpjs() != null && ruangan.getTarifTindakan() != null && ruangan.getTarifTindakan().intValue() > 0){
                                BigDecimal hasilKali = new BigDecimal(0);
                                BigDecimal hasilBagi = new BigDecimal(0);
                                hasilKali = ruangan.getTarifTindakan().divide(ruangan.getTarifBpjs(), 2, RoundingMode.HALF_UP);
                                hasilBagi = hasilKali.multiply(new BigDecimal(100));
                                ruangan.setNilaiPersen(hasilBagi);
                            }
                        }
                    }
                    ruangan.setNamaPasien(obj[10] == null ? null : obj[10].toString());
                    ruangan.setIdPasien(obj[11] == null ? "" : obj[11].toString());
                    ruanganList.add(ruangan);
                }
            }
        }
        return ruanganList;
    }

    public BigDecimal getSumAllTarifTindakan(String idDetail) {
        BigDecimal jumlah = new BigDecimal(0);
        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){
            String SQL = "SELECT \n" +
                    "id_detail_checkup,\n" +
                    "SUM(total_tarif) as total_tarif\n" +
                    "FROM\n" +
                    "it_simrs_riwayat_tindakan\n" +
                    "WHERE \n" +
                    "id_detail_checkup = :idDetail\n" +
                    "AND id_riwayat_tindakan NOT IN (\n" +
                    "\tSELECT id_riwayat_tindakan \n" +
                    "\tFROM it_simrs_tindakan_transitoris\n" +
                    "\tWHERE id_detail_checkup = :idDetail\n" +
                    ")\n" +
                    "GROUP BY id_detail_checkup";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetail)
                    .list();
            if (results.size() > 0) {
                for (Object[] obj : results) {
                    jumlah = (BigDecimal) obj[1];
                }
            }
        }
        return jumlah;
    }

    public List<MtSimrsRuanganEntity> getDataPelayanan(String namaRuangan) throws HibernateException {
        List<MtSimrsRuanganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganEntity.class)
                .add(Restrictions.eq("namaRuangan", namaRuangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<MtSimrsRuanganEntity> cekData(String idRuangan) throws HibernateException{
        List<MtSimrsRuanganEntity> results = new ArrayList<>();

        String query = "SELECT a.id_ruangan, b.id_rawat_inap\n" +
                "FROM mt_simrs_ruangan a\n" +
                "INNER JOIN it_simrs_rawat_inap b ON b.id_ruangan = a.id_ruangan\n" +
                "WHERE a.id_ruangan = '"+idRuangan+"' LIMIT 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        return results;
    }

    public List<Ruangan> getListRuanganKamar(Ruangan bean) throws HibernateException{
        List<Ruangan> results = new ArrayList<>();
        String idKelas = "";
        String status = "";
        String kategori = "%";
        if(bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            idKelas = "AND b.id_kelas_ruangan = '"+bean.getIdKelasRuangan()+"' \n";
        }

        if(bean.getKategori() != null && !"".equalsIgnoreCase(bean.getKategori())){
            kategori = bean.getKategori();
        }

        if(bean.getStatus() != null){
            if("Y".equalsIgnoreCase(bean.getStatus())){
                status = "AND c.status = 'Y' \n";
            }
        }
        String query = "SELECT\n" +
                "b.id_ruangan,\n" +
                "b.nama_ruangan,\n" +
                "b.no_ruangan,\n" +
                "c.id_tempat_tidur,\n" +
                "c.nama_tempat_tidur\n" +
                "FROM im_simrs_kelas_ruangan a\n" +
                "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                "INNER JOIN mt_simrs_ruangan_tempat_tidur c ON b.id_ruangan = c.id_ruangan\n" +
                "WHERE a.kategori LIKE :kategori\n" + idKelas  + status +
                "AND b.branch_id = :branchId\n" +
                "ORDER BY b.nama_ruangan ASC";

        List<Object[]> objects = new ArrayList<>();
        objects = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId", bean.getBranchId())
                .setParameter("kategori", kategori)
                .list();

        if(objects.size() > 0){
            for (Object[] obj: objects){
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(obj[0] == null ? null : obj[0].toString());
                ruangan.setNamaRuangan(obj[1] == null ? null : obj[1].toString());
                ruangan.setNoRuangan(obj[2] == null ? null : obj[2].toString());
                ruangan.setIdTempatTidur(obj[3] == null ? null : obj[3].toString());
                ruangan.setNamaTempatTidur(obj[4] == null ? null : obj[4].toString());
                results.add(ruangan);
            }
        }
        return results;
    }

    public List<Ruangan> getListJustRuanganKamar(String idKelas, String branchId) throws HibernateException{
        List<Ruangan> results = new ArrayList<>();
        String query = "SELECT\n" +
                "b.id_ruangan,\n" +
                "b.nama_ruangan,\n" +
                "b.no_ruangan\n" +
                "FROM im_simrs_kelas_ruangan a\n" +
                "INNER JOIN mt_simrs_ruangan b ON a.id_kelas_ruangan = b.id_kelas_ruangan\n" +
                "WHERE a.id_kelas_ruangan = :idKelas\n"+
                "AND b.branch_id = :branchId\n" +
                "ORDER BY b.nama_ruangan ASC";

        List<Object[]> objects = new ArrayList<>();
        objects = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId",branchId)
                .setParameter("idKelas", idKelas)
                .list();

        if(objects.size() > 0){
            for (Object[] obj: objects){
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(obj[0] == null ? null : obj[0].toString());
                ruangan.setNamaRuangan(obj[1] == null ? null : obj[1].toString());
                ruangan.setNoRuangan(obj[2] == null ? null : obj[2].toString());
                results.add(ruangan);
            }
        }
        return results;
    }
}