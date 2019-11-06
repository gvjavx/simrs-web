package com.neurix.hris.master.carrerPath.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.carrerPath.model.ImCarrerPathEntity;
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
public class CarrerPathDao extends GenericDao<ImCarrerPathEntity, String> {

    @Override
    protected Class<ImCarrerPathEntity> getEntityClass() {
        return ImCarrerPathEntity.class;
    }

    @Override
    public List<ImCarrerPathEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImCarrerPathEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("carrer_path_id")!=null) {
                criteria.add(Restrictions.eq("carrerPathId", (String) mapCriteria.get("carrer_path_id")));
            }

            if (mapCriteria.get("jabatan_id")!=null) {
                criteria.add(Restrictions.eq("jabatanId", "%" + (String)mapCriteria.get("jabatan_id")));
            }

            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", "%" + (String)mapCriteria.get("divisi_id")));
            }

            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", "%" + (String)mapCriteria.get("bagian_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", "%" + (String)mapCriteria.get("branch_id")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("carrerPathId"));

        List<ImCarrerPathEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextCarrerPathId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_carrer_path')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "CP"+sId;
    }

    public List<ImCarrerPathEntity> getListStudyJurusan(String term) throws HibernateException {

        List<ImCarrerPathEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImCarrerPathEntity.class)
                .add(Restrictions.ilike("jurusanName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jurusanId"))
                .list();

        return results;
    }

}
