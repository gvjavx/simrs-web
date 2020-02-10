package com.neurix.simrs.transaksi.riwayattindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_riwayat_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}