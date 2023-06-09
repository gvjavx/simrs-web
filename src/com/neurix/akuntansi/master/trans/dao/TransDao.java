package com.neurix.akuntansi.master.trans.dao;

import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.common.dao.GenericDao;
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
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class TransDao extends GenericDao<ImTransEntity, String> {

    @Override
    protected Class<ImTransEntity> getEntityClass() {
        return ImTransEntity.class;
    }

    @Override
    public List<ImTransEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTransEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("trans_id")!=null) {
                criteria.add(Restrictions.eq("transId", (String) mapCriteria.get("trans_id")));
            }
            if (mapCriteria.get("trans_name")!=null) {
                criteria.add(Restrictions.ilike("transName", "%" + (String)mapCriteria.get("trans_name") + "%"));
            }
            if (mapCriteria.get("tipe_pembayaran")!=null) {
                criteria.add(Restrictions.eq("tipePembayaran", (String) mapCriteria.get("tipe_pembayaran")));
            }
            if (mapCriteria.get("flag_menu")!=null) {
                criteria.add(Restrictions.eq("flagMenu", (String) mapCriteria.get("flag_menu")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("transId"));
        List<ImTransEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTransId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_trans')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return sId;
    }

    public String getTipeBayarByTransId(String transId){
        String result="";
        String query = "  select tipe_pembayaran from im_akun_trans where trans_id='"+transId+"' and flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }
}
