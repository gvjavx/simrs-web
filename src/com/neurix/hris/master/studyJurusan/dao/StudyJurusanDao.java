package com.neurix.hris.master.studyJurusan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.studyJurusan.model.ImStudyJurusanEntity;
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
public class StudyJurusanDao extends GenericDao<ImStudyJurusanEntity, String> {

    @Override
    protected Class<ImStudyJurusanEntity> getEntityClass() {
        return ImStudyJurusanEntity.class;
    }

    @Override
    public List<ImStudyJurusanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImStudyJurusanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jurusan_id")!=null) {
                criteria.add(Restrictions.eq("jurusanId", (String) mapCriteria.get("jurusan_id")));
            }
            if (mapCriteria.get("jurusan_name")!=null) {
                criteria.add(Restrictions.ilike("jurusanName", "%" + (String)mapCriteria.get("jurusan_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("jurusanName"));

        List<ImStudyJurusanEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStudyJurusanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_study_jurusan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "J"+sId;
    }

    public List<ImStudyJurusanEntity> getListStudyJurusan(String term) throws HibernateException {

        List<ImStudyJurusanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStudyJurusanEntity.class)
                .add(Restrictions.ilike("jurusanName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jurusanId"))
                .list();

        return results;
    }

}
