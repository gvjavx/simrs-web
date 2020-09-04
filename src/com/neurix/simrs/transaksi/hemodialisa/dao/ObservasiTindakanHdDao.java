package com.neurix.simrs.transaksi.hemodialisa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsObservasiTindakanHdEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ObservasiTindakanHdDao extends GenericDao<ItSimrsObservasiTindakanHdEntity, String> {

    @Override
    protected Class<ItSimrsObservasiTindakanHdEntity> getEntityClass() {
        return ItSimrsObservasiTindakanHdEntity.class;
    }

    @Override
    public List<ItSimrsObservasiTindakanHdEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsObservasiTindakanHdEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_observasi_tindakan_hd")!=null) {
                criteria.add(Restrictions.eq("idObservasiTindakanHd", (String) mapCriteria.get("id_observasi_tindakan_hd")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idObservasiTindakanHd"));

        List<ItSimrsObservasiTindakanHdEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_observasi_hd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}