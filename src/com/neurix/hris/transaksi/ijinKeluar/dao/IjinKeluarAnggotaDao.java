package com.neurix.hris.transaksi.ijinKeluar.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggotaEntity;
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
public class IjinKeluarAnggotaDao extends GenericDao<IjinKeluarAnggotaEntity, String> {

    @Override
    protected Class<IjinKeluarAnggotaEntity> getEntityClass() {
        return IjinKeluarAnggotaEntity.class;
    }

    @Override
    public List<IjinKeluarAnggotaEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextIjinKeluarAnggotaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin_keluar_anggota')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());
        return "IJA"+sId;
    }

    public List<IjinKeluarAnggotaEntity> getIjinKeluarAnggotaById(String id) throws HibernateException {

        List<IjinKeluarAnggotaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarAnggotaEntity.class)
                .add(Restrictions.eq("ijinKeluarKantorId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("ijinKeluarAnggotaId"))
                .list();

        return results;
    }
}
