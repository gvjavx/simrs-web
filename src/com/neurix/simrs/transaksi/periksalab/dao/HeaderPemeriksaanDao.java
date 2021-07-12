package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsHeaderPemeriksaanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class HeaderPemeriksaanDao extends GenericDao<ItSimrsHeaderPemeriksaanEntity, String> {
    @Override
    protected Class<ItSimrsHeaderPemeriksaanEntity> getEntityClass() {
        return ItSimrsHeaderPemeriksaanEntity.class;
    }

    @Override
    public List<ItSimrsHeaderPemeriksaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderPemeriksaanEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_header_pemeriksaan") != null) {
                criteria.add(Restrictions.eq("idHeaderPemeriksaan", (String) mapCriteria.get("id_header_pemeriksaan")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("tanggal_masuk_periksa") != null) {
                criteria.add(Restrictions.eq("tanggalMasukPeriksa", (Timestamp) mapCriteria.get("tanggal_masuk_periksa")));
            }
            if (mapCriteria.get("tanggal_selesai_periksa") != null) {
                criteria.add(Restrictions.eq("tanggalSelesaiPeriksa", (Timestamp) mapCriteria.get("tanggal_selesai_periksa")));
            }
            if (mapCriteria.get("id_dokter_pengirim") != null) {
                criteria.add(Restrictions.eq("idDokterPengirim", (String) mapCriteria.get("id_dokter_pengirim")));
            }
            if (mapCriteria.get("status") != null) {
                criteria.add(Restrictions.eq("statusPeriksa", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("approve_flag") != null) {
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }

        criteria.addOrder(Order.asc("idHeaderPemeriksaan"));
        List<ItSimrsHeaderPemeriksaanEntity> results = criteria.list();
        return results;
    }

    public List<String> getListIdHeaderPemeriksaanByIdDetailCheckup(String idDetailCheckup){

        String SQL = "SELECT \n" +
                "id_header_pemeriksaan \n" +
                "FROM it_simrs_header_pemeriksaan \n" +
                "WHERE id_detail_checkup = '"+idDetailCheckup+"'\n" +
                "AND flag = 'Y'";

        List<Object> list = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (list.size() > 0){

            List<String> listIdHeader = new ArrayList<>();

            for (Object obj : list){
                listIdHeader.add(obj.toString());
            }

            return listIdHeader;
        }
        return null;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_header_pemeriksaan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "HEP"+sId;
    }
}