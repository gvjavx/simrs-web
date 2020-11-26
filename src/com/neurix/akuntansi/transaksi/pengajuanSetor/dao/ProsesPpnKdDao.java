package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItAkunProsesPpnKdEntity;
import com.neurix.common.dao.GenericDao;
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
public class ProsesPpnKdDao extends GenericDao<ItAkunProsesPpnKdEntity, String> {

    @Override
    protected Class<ItAkunProsesPpnKdEntity> getEntityClass() {
        return ItAkunProsesPpnKdEntity.class;
    }

    @Override
    public List<ItAkunProsesPpnKdEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_proses_ppn_kd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PRK"+sId;
    }

    public List<ItAkunProsesPpnKdEntity> getProsesPpnKdList(String perhitunganPpnId) throws HibernateException {
        List<ItAkunProsesPpnKdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunProsesPpnKdEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("perhitunganPpnKdId", perhitunganPpnId))
                .addOrder(Order.asc("noUrut"))
                .list();
        return results;
    }
}
