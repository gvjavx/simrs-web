package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ImtPelatihanJabatanUserEntity;
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
public class PelatihanJabatanUserDao extends GenericDao<ImtPelatihanJabatanUserEntity, String> {

    @Override
    protected Class<ImtPelatihanJabatanUserEntity> getEntityClass() {
        return ImtPelatihanJabatanUserEntity.class;
    }

    @Override
    public List<ImtPelatihanJabatanUserEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextPelatihanJabatan() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelatihan_jabatan_user')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "PJU"+sId;
    }

    public List<ImtPelatihanJabatanUserEntity> getAllData(String nip) throws HibernateException {
        List<ImtPelatihanJabatanUserEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtPelatihanJabatanUserEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}