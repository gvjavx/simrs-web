package com.neurix.authorization.company.dao;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesHistory;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class BranchDao extends GenericDao<ImBranches,ImBranchesPK> {

    @Override
    protected Class getEntityClass() {
        return ImBranches.class;
    }

    @Override
    public List<ImBranches> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.id", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("branch_name")!=null) {
                criteria.add(Restrictions.ilike("branchName", "%" + (String)mapCriteria.get("branch_name") + "%"));
            }
            if (mapCriteria.get("branch_address")!=null) {
                criteria.add(Restrictions.ilike("branchAddress", "%" + (String)mapCriteria.get("branch_address") + "%"));
            }
            if (mapCriteria.get("area_id")!=null) {
                criteria.add(Restrictions.eq("areaId", (String)mapCriteria.get("area_id")));
            }
            if (mapCriteria.get("not_like")!=null) {
                criteria.add(Restrictions.ne("primaryKey.id", (String)mapCriteria.get("not_like")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.id"));

        List<ImBranches> results = criteria.list();

        return results;
    }

    public boolean isExistBranch(String branchId) throws HibernateException {

        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.eq("primaryKey.id", branchId))
                /*.add(Restrictions.eq("flag", activeFlag))*/
                .list();


        return results.size() > 0 ? true : false;
    }

    public List<ImBranches> getListBranch(String term) throws HibernateException {

        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.ilike("branchName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }

    public List<ImBranches> getListBranchById(String id) throws HibernateException {

        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.eq("primaryKey.id",id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }
    public List<ImBranches> getAllBranch() throws HibernateException {

        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImBranches> getBranchList() throws HibernateException {

        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }

    public long getNextBranch() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_branch')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();
    }

    public void addAndSaveHistory(ImBranchesHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
    public ImBranches getConsSecrBranchById(String id) throws HibernateException {
        ImBranches result = new ImBranches();
        List<ImBranches> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class)
                .add(Restrictions.eq("primaryKey.id",id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        for (ImBranches data : results ){
            result.setBranchName(data.getBranchName());
            result.setConstId(data.getConstId());
            result.setSecretKey(data.getSecretKey());
            result.setUsername(data.getUsername());
            result.setPassword(data.getPassword());
            result.setKdAplikasi(data.getKdAplikasi());
            result.setEklaimAddress(data.getEklaimAddress());
            result.setKeyEklaim(data.getKeyEklaim());
        }
        return result;
    }

    public String getKodringBranches(Map mapCriteria){
        String kode = null;
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBranches.class);

        if (mapCriteria != null){
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.id", (String) mapCriteria.get("branch_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImBranches> results = criteria.list();

        if (results != null){
            for (ImBranches branches : results){
                kode = branches.getKodering();
            }
        }

        return kode;
    }
}
