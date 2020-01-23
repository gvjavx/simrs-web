package com.neurix.simrs.master.finger.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.finger.model.ImSimrsFingerPrintEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class FingerPrintDao extends GenericDao<ImSimrsFingerPrintEntity,String> {

    @Override
    protected Class<ImSimrsFingerPrintEntity> getEntityClass() {
        return ImSimrsFingerPrintEntity.class;
    }

    @Override
    public List<ImSimrsFingerPrintEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsFingerPrintEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("finger_print_id")!=null) {
                criteria.add(Restrictions.eq("idFingerPrint", (String) mapCriteria.get("finger_print_id")));
            }if (mapCriteria.get("device_name")!=null) {
                criteria.add(Restrictions.ilike("deviceName", "%" + (String)mapCriteria.get("device_name") + "%"));
            }if (mapCriteria.get("sn")!=null) {
                criteria.add(Restrictions.eq("sn", (String) mapCriteria.get("sn")));
            }if (mapCriteria.get("vc")!=null) {
                criteria.add(Restrictions.eq("vc", (String) mapCriteria.get("vc")));
            }if (mapCriteria.get("ac")!=null) {
                criteria.add(Restrictions.eq("ac", (String) mapCriteria.get("ac")));
            }if (mapCriteria.get("vkey")!=null) {
                criteria.add(Restrictions.eq("vkey", (String) mapCriteria.get("vkey")));
            }if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("user_id", (String) mapCriteria.get("user_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idFingerPrint"));

        List<ImSimrsFingerPrintEntity> results = criteria.list();

        return results;
    }

    public String getNextIdFingerData(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_finger_data')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%12d", iter.next());
        return "SFD"+sId;
    }
    public List<ImSimrsFingerPrintEntity> getFingerByUserId(String userId) throws HibernateException {
        List<ImSimrsFingerPrintEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsFingerPrintEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("userId", userId))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }
}
