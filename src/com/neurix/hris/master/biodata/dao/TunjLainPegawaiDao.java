package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ItTunjLainPegawaiEntity;
import org.hibernate.Criteria;
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
public class TunjLainPegawaiDao extends GenericDao<ItTunjLainPegawaiEntity, String> {

    @Override
    protected Class<ItTunjLainPegawaiEntity> getEntityClass() {
        return ItTunjLainPegawaiEntity.class;
    }

    @Override
    public List<ItTunjLainPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItTunjLainPegawaiEntity.class);
        if (mapCriteria.get("tunj_lain_id") != null)
            criteria.add(Restrictions.eq("tunjLainId", (String) mapCriteria.get("tunj_lain_id")));
        if (mapCriteria.get("nip") != null)
            criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
        return criteria.list();
    }

    public String getNextTunjLain() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tunj_lain_peg')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "TL"+sId;
    }

    public List<ItTunjLainPegawaiEntity> getAllData(String nip) throws HibernateException {
        List<ItTunjLainPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItTunjLainPegawaiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}