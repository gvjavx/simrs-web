package com.neurix.simrs.transaksi.riwayattindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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

        if(mapCriteria != null){
            if(mapCriteria.get("id_riwayat_tindakan") != null){
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("id_riwayat_tindakan")));
            }
            if(mapCriteria.get("id_detail_checkup") != null){
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if(mapCriteria.get("id_tindakan") != null){
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
            if(mapCriteria.get("keterangan") != null){
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if(mapCriteria.get("kategori_tindakan_bpjs") != null){
                criteria.add(Restrictions.eq("kategoriTindakanBpjs", (String) mapCriteria.get("kategori_tindakan_bpjs")));
            }
            if(mapCriteria.get("approve_bpjs_flag") != null){
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("approve_bpjs_flag")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        criteria.addOrder(Order.asc("idRiwayatTindakan"));
        List<ItSimrsRiwayatTindakanEntity> result = criteria.list();
        return result;
    }

//    public List<RiwayatTindakan> cekTodayTindakanTarifKamar(String idDetail, String tanggal){
//
//        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
//
//        String SQL = "SELECT id_tindakan, id_detail_checkup FROM it_simrs_riwayat_tindakan \n" +
//                "WHERE CAST(created_date AS date) = to_date(:tanggal, 'dd-MM-yyyy') AND id_detail_checkup = :idDetail";
//
//        List<Object[]> result = new ArrayList<>();
//
//        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
//                .setParameter("tanggal", tanggal)
//                .setParameter("idDetail", idDetail)
//                .list();
//
//        RiwayatTindakan tindakan;
//        if (!result.isEmpty()){
//            for (Object[] obj : result){
//                tindakan = new RiwayatTindakan();
//                tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
//                tindakan.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
//                riwayatTindakanList.add(tindakan);
//            }
//        }
//        return riwayatTindakanList;
//    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_riwayat_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}