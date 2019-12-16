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
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

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

    public List<AntianOnline> getAntrianByCriteria(String idPelayanan, String idDokter, String noCheckupOnline, String tglCheckup, String jamAwal, String jamAkhir) {
        String searchPelayanan = "";
        String searchNoCheckupOnline = "";
        String searchDokter = "";
        String searchTglCheckup = "";
        String searchJamAwal = "";
        String searchJamAkhir = "";

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
            if (!tglCheckup.equalsIgnoreCase("")) {
                searchTglCheckup = " and a.tgl_checkup = '" + tglCheckup + "' ";
            }
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


        List<AntianOnline> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<>();
        String query = "SELECT a.id_antrian_online, a.id_pelayanan, a.id_dokter, c.nama_dokter, d.nama_pelayanan, a.no_checkup_online, b.nama, a.tgl_checkup, a.jam_awal, a.jam_akhir, b.last_update \n" +
                "FROM it_simrs_antian_online a \n" +
                "INNER JOIN it_simrs_registrasi_online b ON a.no_checkup_online = b.no_checkup_online\n" +
                "INNER JOIN im_simrs_dokter c ON a.id_dokter = c.id_dokter\n" +
                "INNER JOIN im_simrs_pelayanan d ON a.id_pelayanan = d.id_pelayanan \n" +
                "WHERE a.flag = 'Y' \n" +
                "AND b.flag = 'Y'\n" + searchPelayanan + searchNoCheckupOnline + searchDokter + searchTglCheckup + searchJamAwal + searchJamAkhir +
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
            result.setTglCheckup(CommonUtil.convertDateToString((Date) row[7]));
            result.setJamAwal((String) row[8]);
            result.setJamAkhir((String) row[9]);
            result.setNoAntrian(Integer.toString(counter));
            counter++;
            listOfResult.add(result);
        }
        return listOfResult;
    }


}