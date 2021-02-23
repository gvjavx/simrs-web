package com.neurix.hris.master.jenisPegawai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiEntity;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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

            // Sigit 2020-01-10
            if (mapCriteria.get("jenis_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("jenisPegawaiId", (String) mapCriteria.get("jenis_pegawai_id")));
            }
            if (mapCriteria.get("jenis_pegawai_name")!=null) {
                criteria.add(Restrictions.ilike("jenisPegawaiName", "%" + (String)mapCriteria.get("jenis_pegawai_name") + "%"));
            }
            if (mapCriteria.get("flag_default") != null)
                criteria.add(Restrictions.eq("flagDefault", (String) mapCriteria.get("flag_default")));
            // END

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

    public Boolean checkJenisPegawaiIsDefault(String jenisPegawaiId){

        String SQL = "SELECT jenis_pegawai_id, jenis_pegawai_name FROM im_hris_jenis_pegawai \n" +
                "WHERE jenis_pegawai_Id = :jenis \n" +
                "AND flag = 'Y' \n" +
                "AND flag_default = 'Y' ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("jenis", jenisPegawaiId).list();

        if (results != null && results.size() > 0)
            return true;

        return false;
    }

    public BigDecimal getPersenGaji(String jenisId){
        BigDecimal persenGaji = new BigDecimal(0);
        List<ImHrisJenisPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisJenisPegawaiEntity.class)
                .add(Restrictions.eq("jenisPegawaiId", jenisId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jenisPegawaiId"))
                .list();
        for(ImHrisJenisPegawaiEntity jenisPegawai : results){
            persenGaji = jenisPegawai.getPersenGaji();
        }
        return persenGaji;

    }
}
