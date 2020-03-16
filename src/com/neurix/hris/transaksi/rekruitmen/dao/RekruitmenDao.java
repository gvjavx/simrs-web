
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ImRekruitmenEntity;
import com.neurix.hris.transaksi.rekruitmen.model.ImRekruitmenHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class RekruitmenDao extends GenericDao<ImRekruitmenEntity, String> {

    @Override
    protected Class<ImRekruitmenEntity> getEntityClass() {
        return ImRekruitmenEntity.class;
    }

    @Override
    public List<ImRekruitmenEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImRekruitmenEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("calon_pegawai_id")!=null) {
                criteria.add(Restrictions.ilike("calonPegawaiId", "%" + (String)mapCriteria.get("calon_pegawai_id") + "%"));
            }
            if (mapCriteria.get("nama_calon_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaCalonPegawai", "%" + (String)mapCriteria.get("nama_calon_pegawai") + "%"));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId",(String)mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("department_id")!=null) {
                criteria.add(Restrictions.eq("departmentId",(String)mapCriteria.get("department_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId",(String)mapCriteria.get("position_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("lastUpdate"));

        List<ImRekruitmenEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextCalonPegawaiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen')");
        Iterator<BigInteger> iter=query.list().iterator();
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        String sId = String.format("%05d", iter.next());

        return "CAPEG"+formattedDate+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "H"+sId;
    }

    public List<ImRekruitmenEntity> getListPersonal(String term) throws HibernateException {

        List<ImRekruitmenEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImRekruitmenEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ImRekruitmenEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ImRekruitmenEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImRekruitmenEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ImRekruitmenEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ImRekruitmenEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImRekruitmenEntity.class)
                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.ilike("nip",term))
                //.add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImRekruitmenHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
}
