package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ImPengalamanKerjaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class PengalamanKerjaDao extends GenericDao<ImPengalamanKerjaEntity, String> {

    @Override
    protected Class<ImPengalamanKerjaEntity> getEntityClass() {
        return ImPengalamanKerjaEntity.class;
    }

    @Override
    public List<ImPengalamanKerjaEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextPengalamanKerja() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengalaman_kerja')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "PK"+sId;
    }

    public List<ImPengalamanKerjaEntity> getAllData(String nip) throws HibernateException {
        List<ImPengalamanKerjaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPengalamanKerjaEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}