package com.neurix.simrs.transaksi.riwayattindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RiwayatTindakanDao extends GenericDao<ItSimrsRiwayatTindakanEntity, String> {
    @Override
    protected Class<ItSimrsRiwayatTindakanEntity> getEntityClass() {
        return ItSimrsRiwayatTindakanEntity.class;
    }

    @Override
    public List<ItSimrsRiwayatTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRiwayatTindakanEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_riwayat_tindakan") != null) {
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("id_riwayat_tindakan")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_tindakan") != null) {
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
            if (mapCriteria.get("keterangan") != null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("kategori_tindakan_bpjs") != null) {
                criteria.add(Restrictions.eq("kategoriTindakanBpjs", (String) mapCriteria.get("kategori_tindakan_bpjs")));
            }
            if (mapCriteria.get("approve_bpjs_flag") != null) {
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("approve_bpjs_flag")));
            }
            if (mapCriteria.get("jenis_pasien") != null) {
                criteria.add(Restrictions.eq("jenisPasien", (String) mapCriteria.get("jenis_pasien")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("not_resep") != null) {
                criteria.add(Restrictions.ne("keterangan", "resep"));
            }
        }

        criteria.addOrder(Order.asc("idRiwayatTindakan"));
        List<ItSimrsRiwayatTindakanEntity> result = criteria.list();
        return result;
    }

    public List<RiwayatTindakan> cekTodayTindakanTarifKamar(String idDetail){

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        String SQL = "SELECT id_tindakan, id_detail_checkup FROM it_simrs_riwayat_tindakan \n" +
                "WHERE CAST(created_date AS date) = CURRENT_DATE AND id_detail_checkup = :idDetail AND keterangan = 'kamar'";

        List<Object[]> result = new ArrayList<>();

        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .list();

        RiwayatTindakan tindakan;
        if (!result.isEmpty()){
            for (Object[] obj : result){
                tindakan = new RiwayatTindakan();
                tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                riwayatTindakanList.add(tindakan);
            }
        }
        return riwayatTindakanList;
    }

    public List<RiwayatTindakan> getListTindakan(RiwayatTindakan bean) {

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if (bean != null) {

            String branchId = "%";
            String noCheckup = "%";
            String idDetail = "%";

            if(bean.getBranchId() != null){
                branchId = bean.getBranchId();
            }

            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                noCheckup = bean.getNoCheckup();
            }

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                idDetail = bean.getIdDetailCheckup();
            }

            String SQL = "SELECT a.no_checkup, b.id_detail_checkup, c.id_riwayat_tindakan, \n" +
                    "c.id_tindakan, c.nama_tindakan, c.keterangan, c.jenis_pasien, c.total_tarif, c.kategori_tindakan_bpjs, \n" +
                    "c.approve_bpjs_flag, c.tanggal_tindakan, d.kategori_ina_bpjs FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN it_simrs_riwayat_tindakan c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                    "LEFT JOIN im_simrs_tindakan d ON d.id_tindakan = c.id_tindakan\n" +
                    "WHERE a.branch_id LIKE :branchId AND a.no_checkup LIKE :noCheckup AND b.id_detail_checkup LIKE :idDetail ORDER BY c.keterangan\n";

            List<Object[]> result = new ArrayList<>();

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .setParameter("noCheckup", noCheckup)
                    .setParameter("idDetail", idDetail)
                    .list();

            RiwayatTindakan tindakan;
            if (!result.isEmpty()) {
                for (Object[] obj : result) {
                    tindakan = new RiwayatTindakan();
                    tindakan.setNoCheckup(obj[0] == null ? "" : obj[0].toString());
                    tindakan.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                    tindakan.setIdRiwayatTindakan(obj[2] == null ? "" : obj[2].toString());
                    tindakan.setIdTindakan(obj[3] == null ? "" : obj[3].toString());
                    tindakan.setNamaTindakan(obj[4] == null ? "" : obj[4].toString());
                    tindakan.setKeterangan(obj[5] == null ? "" : obj[5].toString());
                    tindakan.setJenisPasien(obj[6] == null ? "" : obj[6].toString());
                    if(obj[7] != null){
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(obj[7].toString())));
                    }
                    tindakan.setKategoriTindakanBpjs(obj[8] == null ? "" : obj[8].toString());
                    tindakan.setApproveBpjsFlag(obj[9] == null ? "" : obj[9].toString());

                    if(obj[10] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[10]);
                        tindakan.setTanggalTindakan((Timestamp) obj[10]);
                        tindakan.setStTglTindakan(formatDate);
                    }
                    tindakan.setKategoriInaBpjs(obj[11] == null ? "" : obj[11].toString());

                    riwayatTindakanList.add(tindakan);
                }
            }

        }
        return riwayatTindakanList;

    }

    public List<RiwayatTindakan> getListTindakanApprove(String idDetail) {

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){

            String SQL = "SELECT \n" +
                    "a.tanggal_tindakan, \n" +
                    "a.nama_tindakan,  \n" +
                    "a.total_tarif, \n" +
                    "a.keterangan \n" +
                    "FROM it_simrs_riwayat_tindakan a\n" +
                    "WHERE a.id_detail_checkup = :idDet\n" +
                    "AND flag_update_klaim = 'Y'\n" +
                    "ORDER BY a.keterangan, a.tanggal_tindakan ASC";

            List<Object[]> result = new ArrayList<>();

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDet", idDetail)
                    .list();

            RiwayatTindakan tindakan;
            if (!result.isEmpty()) {
                for (Object[] obj : result) {
                    tindakan = new RiwayatTindakan();

                    if(obj[0] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[0]);
                        tindakan.setTanggalTindakan((Timestamp) obj[0]);
                        tindakan.setStTglTindakan(formatDate);
                    }else{
                        tindakan.setStTglTindakan("");
                    }

                    tindakan.setNamaTindakan(obj[1] == null ? "" : obj[1].toString());

                    if(obj[2] != null){
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(obj[2].toString())));
                    }else{
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(0)));
                    }

                    tindakan.setKeterangan(obj[3] == null ? "" : obj[3].toString());

                    riwayatTindakanList.add(tindakan);
                }
            }
        }
        return riwayatTindakanList;
    }

    public Boolean checkIsTransitoris(String id){
        Boolean found = false;

        String SQL = "SELECT id_riwayat_tindakan, id_detail_checkup, id_tindakan \n" +
                "FROM it_simrs_tindakan_transitoris \n" +
                "WHERE id_detail_checkup = :id \n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        if (results.size() > 0){
            found = true;
        }
        return found;
    }

    public List<String> listOfKeteranganExistByIdDetailCheckup(String id){
        String SQL = "SELECT keterangan, id_detail_checkup FROM it_simrs_riwayat_tindakan\n" +
                "WHERE id_detail_checkup = :id \n" +
                "GROUP BY keterangan, id_detail_checkup";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        List<String> listKeterangan = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                listKeterangan.add(obj[0].toString());
            }
        }

        return listKeterangan;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_riwayat_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}