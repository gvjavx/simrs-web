
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.sppd.model.ImSppdEntity;
import com.neurix.hris.transaksi.sppd.model.ItSppdDocEntity;
import com.neurix.hris.transaksi.sppd.model.ItSppdPersonEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.Timestamp;
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
public class SppdDocDao extends GenericDao<ItSppdDocEntity, String> {

    @Override
    protected Class<ItSppdDocEntity> getEntityClass() {
        return ItSppdDocEntity.class;
    }

    @Override
    public List<ItSppdDocEntity> getByCriteria(Map mapCriteria) {
        List<ItSppdDocEntity> results = null;
        return results;
    }

    public String getNextSppdDoc() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_doc')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "Doc"+sId;
    }

    public List<ItSppdDocEntity> getListSppdPerson(String term) throws HibernateException {
        List<ItSppdDocEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItSppdDocEntity.class)
                .add(Restrictions.eq("sppdId", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("docSppdId"))
                .list();
        return results;
    }

}
