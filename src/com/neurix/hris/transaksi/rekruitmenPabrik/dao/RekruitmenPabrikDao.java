
package com.neurix.hris.transaksi.rekruitmenPabrik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikEntity;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.ItRekruitmenPabrikHistoryEntity;
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
public class RekruitmenPabrikDao extends GenericDao<ItRekruitmenPabrikEntity, String> {

    @Override
    protected Class<ItRekruitmenPabrikEntity> getEntityClass() {
        return ItRekruitmenPabrikEntity.class;
    }

    @Override
    public List<ItRekruitmenPabrikEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekruitmen_pabrik_id")!=null) {
                criteria.add(Restrictions.eq("rekruitmenPabrikId", (String) mapCriteria.get("rekruitmen_pabrik_id")));
            }
            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("rekruitmenPabrikId"));

        List<ItRekruitmenPabrikEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRekruitmenPabrikId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_pabrik')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String formattedDate = df.format(Calendar.getInstance().getTime());
        return "RPB"+formattedDate+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItRekruitmenPabrikEntity> getListPersonal(String term) throws HibernateException {

        List<ItRekruitmenPabrikEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ItRekruitmenPabrikEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ItRekruitmenPabrikEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ItRekruitmenPabrikEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ItRekruitmenPabrikEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenPabrikEntity.class)
                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.ilike("nip",term))
                //.add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextRekruitmenPabrikHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_pabrik_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    public void addAndSaveHistory(ItRekruitmenPabrikHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
