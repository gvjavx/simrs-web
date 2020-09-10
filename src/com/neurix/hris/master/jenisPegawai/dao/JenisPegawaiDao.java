package com.neurix.hris.master.jenisPegawai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiEntity;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiHistoryEntity;
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
public class JenisPegawaiDao extends GenericDao<ImHrisJenisPegawaiEntity, String> {

    @Override
    protected Class<ImHrisJenisPegawaiEntity> getEntityClass() {
        return ImHrisJenisPegawaiEntity.class;
    }

    @Override
    public List<ImHrisJenisPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisJenisPegawaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_payroll_id")!=null) {
                criteria.add(Restrictions.eq("jenisPegawaiId", (String) mapCriteria.get("tipe_payroll_id")));
            }
            if (mapCriteria.get("tipe_payroll_name")!=null) {
                criteria.add(Restrictions.ilike("jenisPegawaiName", "%" + (String)mapCriteria.get("tipe_payroll_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jenisPegawaiId"));
        List<ImHrisJenisPegawaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJenisPegawaiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_pegawai')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "JP"+sId;
    }

    public String getNextJenisPegawaiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_pegawai_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HJ"+sId;
    }

    public void addAndSaveHistory(ImHrisJenisPegawaiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisJenisPegawaiEntity> checkData(String jenisPegawaiName) throws HibernateException {

        List<ImHrisJenisPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisJenisPegawaiEntity.class)
                .add(Restrictions.eq("jenisPegawaiName", jenisPegawaiName))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jenisPegawaiId"))
                .list();

        return results;
    }
}
