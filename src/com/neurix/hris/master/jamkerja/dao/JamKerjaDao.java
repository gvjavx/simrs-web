package com.neurix.hris.master.jamkerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerja;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerjaHistory;
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
public class JamKerjaDao extends GenericDao<ImHrisJamKerja,String> {
    @Override
    protected Class<ImHrisJamKerja> getEntityClass() {
        return ImHrisJamKerja.class;
    }

    @Override
    public List<ImHrisJamKerja> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisJamKerja.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jam_kerja_id")!=null) {
                criteria.add(Restrictions.eq("jamKerjaId", (String) mapCriteria.get("jam_kerja_id")));
            }
            if (mapCriteria.get("status_giling")!=null) {
                criteria.add(Restrictions.eq("statusGiling",(String)mapCriteria.get("status_giling")));
            }
            if (mapCriteria.get("tipe_pegawai")!=null) {
                criteria.add(Restrictions.eq("tipePegawaiId", (String) mapCriteria.get("tipe_pegawai")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("hari")!=null) {
                criteria.add(Restrictions.eq("hariKerja", mapCriteria.get("hari")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("jamKerjaId"));

        List<ImHrisJamKerja> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJamKerjaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jam_kerja')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "JK" + sId;
    }

    // Generate surrogate id from postgre
    public String getNextJamKerjaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jam_kerja_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String id = String.format("%03d", iter.next());
        return id;
    }


    public void addAndSaveHistory(ImHrisJamKerjaHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
