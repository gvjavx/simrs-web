package com.neurix.hris.transaksi.logcron.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.logcron.model.ItLogCronEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogCronDao extends GenericDao<ItLogCronEntity, String> {
    @Override
    protected Class<ItLogCronEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItLogCronEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItLogCronEntity.class);

        if(mapCriteria != null){
            if(mapCriteria.get("logCronId")!=null){
                criteria.add(Restrictions.eq("logCronId",(String) mapCriteria.get("logCronId")));
            }
            if(mapCriteria.get("cronName")!=null){
                criteria.add(Restrictions.ilike("cronName","%" + (String) mapCriteria.get("cronName") + "%"));
            }
            if(mapCriteria.get("status")!=null){
                criteria.add(Restrictions.eq("status",(String) mapCriteria.get("status")));
            }
            if(mapCriteria.get("cronDateStr")!=null && mapCriteria.get("cronDateEnd")!=null){
                criteria.add(Restrictions.between("cronDate",mapCriteria.get("cronDateStr"),mapCriteria.get("cronDateEnd")));
            }else{
                if (mapCriteria.get("cronDateStr")!=null) {
                    criteria.add(Restrictions.ge("cronDate",mapCriteria.get("cronDateStr")));
                }
                if (mapCriteria.get("cronDateEnd")!=null) {
                    criteria.add(Restrictions.le("cronDate",mapCriteria.get("cronDateEnd")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("cronDate"));

        List<ItLogCronEntity> results = criteria.list();

        return results;
    }

    public String getNextLogCronId() throws HibernateException{
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_log_cron')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());
        String nextId = "LC"+sId;
        return nextId;
    }
}


