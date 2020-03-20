
package com.neurix.hris.master.study.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.study.model.ImStudyEntity;
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
public class StudyDao extends GenericDao<ImStudyEntity, String> {

    @Override
    protected Class<ImStudyEntity> getEntityClass() {
        return ImStudyEntity.class;
    }

    @Override
    public List<ImStudyEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImStudyEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("study_id")!=null) {
                criteria.add(Restrictions.eq("studyId", (String) mapCriteria.get("study_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("study_name")!=null) {
                criteria.add(Restrictions.ilike("studyName", "%" + (String)mapCriteria.get("study_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tahunAkhir"));

        List<ImStudyEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStudyId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_study')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "S"+sId;
    }


    public List<ImStudyEntity> getListStudy(String term) throws HibernateException {
        List<ImStudyEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStudyEntity.class)
                .add(Restrictions.ilike("studyName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("studyId"))
                .list();

        return results;
    }

    public List<ImStudyEntity> getListStudyByNip(String nip) throws HibernateException {
        List<ImStudyEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStudyEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("tahunAkhir"))
                .list();

        return results;
    }

    public List<ImStudyEntity> getListStudyByNip(String nip, String typeStudy) throws HibernateException {
        List<ImStudyEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStudyEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("typeStudy", typeStudy))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("studyId"))
                .list();

        return results;
    }
    
}
