package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderCheckupDao extends GenericDao<ItSimrsHeaderChekupEntity, String> {
    @Override
    protected Class<ItSimrsHeaderChekupEntity> getEntityClass() {
        return ItSimrsHeaderChekupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderChekupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderChekupEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            }
            if (mapCriteria.get("id_pasien") != null) {
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("id_branch").toString()));
            }
            if (mapCriteria.get("desa_id") != null) {
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            }
            if (mapCriteria.get("no_ktp") != null) {
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("list_no_checkup") != null) {
                criteria.add(Restrictions.in("noCheckup", (List<String>) mapCriteria.get("list_no_checkup")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("tgl_keluar_not_null") != null) {
                criteria.add(Restrictions.isNotNull("tglKeluar"));
            }

        List<ItSimrsHeaderChekupEntity> result = criteria.list();
        return result;
    }

    public HeaderDetailCheckup getLastPoliAndStatus(String noCheckup){

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)){
            String SQL = "SELECT \n" +
                    "detail.no_checkup,\n" +
                    "detail.id_pelayanan,\n" +
                    "detail.status_periksa,\n" +
                    "status.keterangan as status_name,\n" +
                    "pel.nama_pelayanan,\n" +
                    "ranap.nama_ruangan,\n" +
                    "ranap.no_ruangan,\n" +
                    "detail.id_detail_checkup\n" +
                    "FROM \n" +
                    "it_simrs_header_detail_checkup detail\n" +
                    "INNER JOIN im_simrs_status_pasien status ON status.id_status_pasien = detail.status_periksa\n" +
                    "INNER JOIN im_simrs_pelayanan pel ON pel.id_pelayanan = detail.id_pelayanan \n" +
                    "LEFT OUTER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') ranap ON ranap.id_detail_checkup = detail.id_detail_checkup\n" +
                    "WHERE (detail.no_checkup, detail.created_date) = \n" +
                    "(\n" +
                    "\tSELECT\n" +
                    "\td.no_checkup,\n" +
                    "\tmax(d.created_date) as created_date\n" +
                    "\tFROM it_simrs_header_detail_checkup d\n" +
                    "\tWHERE d.no_checkup = :noCheckup \n" +
                    "\tGROUP BY d.no_checkup\n" +
                    ")";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("noCheckup", noCheckup)
                    .list();

            if (!result.isEmpty()){
                Object[] obj = result.get(0);
                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                headerDetailCheckup.setNoCheckup(obj[0].toString());
                headerDetailCheckup.setIdPelayanan(obj[1].toString());
                headerDetailCheckup.setStatusPeriksa(obj[2].toString());
                headerDetailCheckup.setStatusPeriksaName(obj[3].toString());
                headerDetailCheckup.setNamaPelayanan(obj[4].toString());
                headerDetailCheckup.setNamaRuangan(obj[5] == null ? "" : obj[5].toString());
                headerDetailCheckup.setNoRuangan(obj[6] == null ? "" : obj[6].toString());
                headerDetailCheckup.setIdDetailCheckup(obj[7].toString());
                return headerDetailCheckup;
            }
        }
        return new HeaderDetailCheckup();
    }

    public List<String> getListNoCheckupByCriteria(Map mapCriteria, Boolean isPoli, Boolean isStatus){

        String idPasien = "%";
        String noKtp = "%";
        String nama = "%";
        String branchId = "%";
        String idPelayanan = "%";
        String statusPeriksa = "%";

        //sodiq, 17 Nov 2019, penambahan no checkup
        String noCheckup = "%";
        if(mapCriteria.get("no_checkup") != null){
            noCheckup = mapCriteria.get("no_checkup").toString();
        }

        if(mapCriteria.get("id_pasien") != null){
            idPasien = mapCriteria.get("id_pasien").toString();
        }
        if(mapCriteria.get("no_ktp") != null){
            noKtp = mapCriteria.get("no_ktp").toString();
        }
        if(mapCriteria.get("nama") != null){
            nama = "%"+ mapCriteria.get("nama").toString()+"%";
        }
        if(mapCriteria.get("branch_id") != null){
            branchId = mapCriteria.get("branch_id").toString();
        }
        if(mapCriteria.get("id_pelayanan") != null){
            idPelayanan = mapCriteria.get("id_pelayanan").toString();
        }
        if(mapCriteria.get("status_periksa") != null) {
            statusPeriksa = mapCriteria.get("status_periksa").toString();
        }

        String SQL = "SELECT\n" +
                "detail.no_checkup,\n" +
                "h.branch_id\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup detail\n" +
                "INNER JOIN it_simrs_header_checkup h ON h.no_checkup = detail.no_checkup\n" +
                "WHERE h.id_pasien LIKE :idPasien\n" +
                "AND h.no_ktp LIKE :noKtp\n" +
                "AND h.nama LIKE :nama\n" +
                "AND h.branch_id LIKE :branchId\n" +
                "AND detail.id_pelayanan LIKE :idPelayanan\n" +
                "AND detail.status_periksa LIKE :statusPeriksa\n" +
                "AND detail.flag = 'Y'\n" +
                "AND h.no_checkup LIKE :noCheckup\n" +
                "GROUP BY detail.no_checkup, h.branch_id";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPasien", idPasien)
                .setParameter("noKtp", noKtp)
                .setParameter("nama", nama)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("statusPeriksa", statusPeriksa)
                .setParameter("noCheckup", noCheckup)
                .list();

        List<String> listOfResult = new ArrayList<>();

        if (!result.isEmpty()){
            for (Object[] obj : result){
                listOfResult.add(obj[0].toString());
            }
        }

        return listOfResult;
    }

    public AlertPasien getAlertPasien(String idPasien, String branchId){

        if (branchId == null || "".equalsIgnoreCase(branchId)){
            branchId = "%";
        }

        String SQL = "SELECT ps.nama, diag.keterangan_diagnosa, ck.last_update, ck.no_checkup FROM it_simrs_header_checkup ck\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = ck.id_pasien\n" +
                "INNER JOIN (SELECT * FROM it_simrs_header_detail_checkup WHERE status_periksa = '3') hdc ON hdc.no_checkup = ck.no_checkup\n" +
                "INNER JOIN (\n" +
                "\tSELECT a.* FROM it_simrs_diagnosa_rawat a\n" +
                "\tINNER JOIN (\n" +
                "\tSELECT id_detail_checkup, \n" +
                "\tMAX(created_date) as created_date \n" +
                "\tFROM it_simrs_diagnosa_rawat\n" +
                "\tGROUP BY id_detail_checkup\n" +
                "\t) b ON b.id_detail_checkup = a.id_detail_checkup AND b.created_date = a.created_date\n" +
                ") diag ON diag.id_detail_checkup = hdc.id_detail_checkup\n" +
                "WHERE ck.id_pasien = :idPasien \n" +
                "AND ck.branch_id LIKE :branchId \n" +
                "ORDER BY hdc.last_update DESC\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("idPasien", idPasien)
                .list();

        AlertPasien alertPasien = new AlertPasien();
        for (Object[] obj : results){

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[3] == null ? "" : obj[3].toString());

            if (obj[2] != null){
                Timestamp lastUpdate = (Timestamp) obj[2];
                Long time = lastUpdate.getTime();
                Date date = new Date(time);
                alertPasien.setStTgl(date.toString());
            }
        }

        return alertPasien;
    }

    public AlertPasien gelLastDiagnosa(String noCheckup, String branchId){

        if (branchId == null || "".equalsIgnoreCase(branchId)){
            branchId = "%";
        }

        String SQL = "SELECT ps.nama, diag.keterangan_diagnosa, ck.last_update, ck.no_checkup FROM it_simrs_header_checkup ck\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = ck.id_pasien\n" +
                "INNER JOIN (SELECT * FROM it_simrs_header_detail_checkup WHERE status_periksa = '3') hdc ON hdc.no_checkup = ck.no_checkup\n" +
                "INNER JOIN (SELECT * FROM it_simrs_diagnosa_rawat WHERE jenis_diagnosa = '1') diag ON diag.id_detail_checkup = hdc.id_detail_checkup\n" +
                "WHERE ck.no_checkup = :noCheckup \n" +
                "AND ck.branch_id LIKE :branchId \n" +
                "ORDER BY hdc.last_update DESC\n" +
                "LIMIT 1\n";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("noCheckup", noCheckup)
                .list();

        AlertPasien alertPasien = new AlertPasien();
        for (Object[] obj : results){

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[3] == null ? "" : obj[3].toString());

            if (obj[2] != null){
                Timestamp lastUpdate = (Timestamp) obj[2];
                Long time = lastUpdate.getTime();
                Date date = new Date(time);
                alertPasien.setStTgl(date.toString());
            }
        }

        return alertPasien;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
