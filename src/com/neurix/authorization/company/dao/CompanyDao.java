package com.neurix.authorization.company.dao;

import com.neurix.authorization.company.model.ImCompany;
import com.neurix.authorization.company.model.ImCompanyHistory;
import com.neurix.common.dao.GenericDao;
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
 * User: Thunderbird
 * Date: 19/01/13
 * Time: 9:47
 * To change this template use File | Settings | File Templates.
 */
public class CompanyDao extends GenericDao<ImCompany,String> {

    @Override
    protected Class getEntityClass() {
        return ImCompany.class;
    }

    public ImCompany getCompanyInfo(String activeFlag) throws HibernateException {

        List<ImCompany> results = this.sessionFactory.getCurrentSession().createCriteria(ImCompany.class)
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        return results.size() > 0 ? (ImCompany) results.get(0) : null;
    }

    public List<ImCompany> getBiayaJabatanPersentase() throws HibernateException {
        List<ImCompany> results = this.sessionFactory.getCurrentSession().createCriteria(ImCompany.class)
                .list();

        return results;
    }

    @Override
    public List<ImCompany> getByCriteria(Map mapCriteria) throws HibernateException {

        return null;
    }

    public void addAndSaveHistory(ImCompanyHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
}
