package com.neurix.simrs.transaksi.hemodialisa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsHemodialisaEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HemodialisaDao extends GenericDao<ItSimrsHemodialisaEntity, String> {

    @Override
    protected Class<ItSimrsHemodialisaEntity> getEntityClass() {
        return ItSimrsHemodialisaEntity.class;
    }

    @Override
    public List<ItSimrsHemodialisaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHemodialisaEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_hemodialisa")!=null) {
                criteria.add(Restrictions.eq("idHemodialisa", (String) mapCriteria.get("id_hemodialisa")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idHemodialisa"));

        List<ItSimrsHemodialisaEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hemodialisa')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}