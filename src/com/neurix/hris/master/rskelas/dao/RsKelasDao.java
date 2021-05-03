package com.neurix.hris.master.rskelas.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelas;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelasHistory;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLiburHistory;
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
 * Created by thinkpad on 19/03/2018.
 */
public class RsKelasDao extends GenericDao<ImHrisRsKelas, String> {
    @Override
    protected Class<ImHrisRsKelas> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisRsKelas> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisRsKelas.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rs_kelas_id")!=null) {
                criteria.add(Restrictions.eq("rsKelasId", (String) mapCriteria.get("rs_kelas_id")));
            }
            if (mapCriteria.get("rs_id")!=null) {
                criteria.add(Restrictions.ilike("rsId", "%" + (String)mapCriteria.get("rs_id") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("rsKelasId"));

        List<ImHrisRsKelas> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getRsKelasId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rs_kelas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }
    // Generate surrogate id from postgre
    public String getRsKelasHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rs_kelas_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    public void addAndSaveHistory(ImHrisRsKelasHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisRsKelas> getListRskelasByKelompok(String term, String kelompokId) throws HibernateException {

        List<ImHrisRsKelas> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisRsKelas.class)
                .add(Restrictions.eq("kelompokId",kelompokId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("rsKelasId"))
                .list();

        return results;
    }

    public List<ImHrisRsKelas> getListRskelasByGolongan(String term, String golonganId) throws HibernateException {

        List<ImHrisRsKelas> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisRsKelas.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("golonganId",golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("rsKelasId"))
                .list();

        return results;
    }
}
