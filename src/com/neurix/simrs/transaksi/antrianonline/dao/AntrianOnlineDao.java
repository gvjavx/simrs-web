package com.neurix.simrs.transaksi.antrianonline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AntrianOnlineDao extends GenericDao<ItSimrsAntianOnlineEntity, String> {
    @Override
    protected Class<ItSimrsAntianOnlineEntity> getEntityClass() {
        return ItSimrsAntianOnlineEntity.class;
    }

    @Override
    public List<ItSimrsAntianOnlineEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAntianOnlineEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_antrian_online")!=null) {
                criteria.add(Restrictions.eq("idAntrianOnline", (String) mapCriteria.get("id_antrian_online")));
            }
            if (mapCriteria.get("no_checkup_online")!=null) {
                criteria.add(Restrictions.eq("noCheckupOnline", (String) mapCriteria.get("no_checkup_online")));
            }
            if (mapCriteria.get("id_pelayanan")!=null) {
                criteria.add(Restrictions.eq("idPelayanan", (String) mapCriteria.get("id_pelayanan")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("no_checkup")!=null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
            if(mapCriteria.get("tgl_checkup") != null) {
                criteria.add(Restrictions.sqlRestriction("DATE(tgl_checkup) = '" + mapCriteria.get("tgl_checkup")+"'"));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("idAntrianOnline"));

        List<ItSimrsAntianOnlineEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_antrian_online')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<AntianOnline> getAntrianByCriteria(String idPelayanan, String idDokter, String noCheckupOnline, Date tglCheckup, String jamAwal, String jamAkhir, String branchId, String idPasien) {
        String searchPelayanan = "";
        String searchNoCheckupOnline = "";
        String searchDokter = "";
        String searchTglCheckup = "";
        String searchJamAwal = "";
        String searchJamAkhir = "";
        String searchBranchId = "";
        String searchPasien ="";

        if (idPelayanan!=null){
            if(!idPelayanan.equalsIgnoreCase("")){
                searchPelayanan = " and a.id_pelayanan = '" + idPelayanan + "' " ;
            }
        }

        if (noCheckupOnline != null) {
            if (!noCheckupOnline.equalsIgnoreCase("")) {
                searchNoCheckupOnline = " and a.no_checkup_online = '" + noCheckupOnline + "' ";
            }
        }

        if (idDokter != null) {
            if (!idDokter.equalsIgnoreCase("")) {
                searchDokter = " and a.id_dokter = '" + idDokter + "' ";
            }
        }

        if (tglCheckup != null) {
            searchTglCheckup = " and a.tgl_checkup = '" + tglCheckup + "' ";
        }

        if (jamAwal != null) {
            if (!jamAwal.equalsIgnoreCase("")) {
                searchJamAwal = " and a.jam_awal = '" + jamAwal + "' ";
            }
        }

        if (jamAkhir != null) {
            if (!jamAkhir.equalsIgnoreCase("")) {
                searchJamAkhir = " and a.jam_akhir = '" + jamAkhir + "' ";
            }
        }
        if (branchId != null ) {
            if (!branchId.equalsIgnoreCase("")) {
                searchBranchId = " and a.branch_id = '" + branchId + "' ";
            }
        }
        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {
            searchPasien = " and b.id_pasien = '" + idPasien + "' ";
        }


        List<AntianOnline> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<>();
        String query = "SELECT a.id_antrian_online, a.id_pelayanan, a.id_dokter, c.nama_dokter, d.nama_pelayanan, a.no_checkup_online, b.nama, a.tgl_checkup, a.jam_awal, a.jam_akhir, b.last_update, a.branch_id, e.branch_name, a.no_checkup, a.id_detail_checkup, a.flag_periksa\n" +
                "FROM it_simrs_antian_online a \n" +
                "INNER JOIN it_simrs_registrasi_online b ON a.no_checkup_online = b.no_checkup_online\n" +
                "INNER JOIN im_simrs_dokter c ON a.id_dokter = c.id_dokter\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.flag,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) d ON a.id_pelayanan = d.id_pelayanan \n" +
                "INNER JOIN im_branches e ON a.branch_id = e.branch_id \n " +
                "WHERE a.flag = 'Y' \n" +
                "AND c.flag = 'Y'\n" +
                "AND d.flag = 'Y'\n" +
                "AND e.flag = 'Y'\n" +
                "AND b.flag = 'Y'\n" + searchPelayanan + searchNoCheckupOnline + searchDokter + searchTglCheckup + searchJamAwal + searchJamAkhir + searchBranchId + searchPasien +
                "ORDER BY b.last_update";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        int counter = 1;
        for (Object[] row : results) {
            AntianOnline result = new AntianOnline();
            result.setIdAntrianOnline((String) row[0]);
            result.setIdPelayanan((String) row[1]);
            result.setIdDokter((String) row[2]);
            result.setNamaDokter((String) row[3]);
            result.setNamaPelayanan((String) row[4]);
            result.setNoCheckupOnline((String) row[5]);
            result.setNama((String) row[6]);
            result.setTglCheckup(row[7].toString());
            result.setJamAwal((String) row[8]);
            result.setJamAkhir((String) row[9]);
            result.setLastUpdate((Timestamp) row[10]);
            result.setBranchId((String) row[11]);
            result.setBranchName((String) row[12]);
            result.setNoCheckup((String) row[13]);
            result.setIdDetailCheckup((String) row[14]);
            result.setFlagPeriksa((String) row[15]);
            result.setNoAntrian(Integer.toString(counter));
            result.setJumlahAntrian(String.valueOf(results.size()));
            counter++;
            listOfResult.add(result);
        }
        return listOfResult;
    }


}