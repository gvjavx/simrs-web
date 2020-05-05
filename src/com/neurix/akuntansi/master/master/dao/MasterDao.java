package com.neurix.akuntansi.master.master.dao;

import com.neurix.akuntansi.master.master.model.ImMasterEntity;
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
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MasterDao extends GenericDao<ImMasterEntity, String> {

    @Override
    protected Class<ImMasterEntity> getEntityClass() {
        return ImMasterEntity.class;
    }

    @Override
    public List<ImMasterEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasterEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekening_id")!=null) {
                criteria.add(Restrictions.eq("rekeningId", (String) mapCriteria.get("rekening_id")));
            }
            if (mapCriteria.get("nama_kode_rekening")!=null) {
                criteria.add(Restrictions.ilike("namaMaster", "%" + (String)mapCriteria.get("nama_kode_rekening") + "%"));
            }
            if (mapCriteria.get("kode_rekening")!=null) {
                criteria.add(Restrictions.eq("master", (String) mapCriteria.get("kode_rekening")));
            }
            if (mapCriteria.get("tipe_rekening_id")!=null) {
                criteria.add(Restrictions.eq("tipeRekeningId", (String) mapCriteria.get("tipe_rekening_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("rekeningId"));

        List<ImMasterEntity> results = criteria.list();

        return results;
    }

    public List<ImMasterEntity> getByInput(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasterEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nomor_master")!=null) {
                criteria.add(Restrictions.eq("primaryKey", (String) mapCriteria.get("nomor_master")));
            }
            if (mapCriteria.get("nama")!=null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String)mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("alamat")!=null) {
                criteria.add(Restrictions.eq("master", (String) mapCriteria.get("alamat")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("nama"));

        List<ImMasterEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMasterId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kode_rekening')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%010d", iter.next());
        return "KR"+sId;
    }

    public List<ImMasterEntity> getIdByCoa(String coa) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasterEntity.class);
        criteria.add(Restrictions.eq("master", coa));
        criteria.add(Restrictions.eq("flag", "Y"));
        // Order by
        criteria.addOrder(Order.asc("rekeningId"));

        List<ImMasterEntity> results = criteria.list();
        return results;
    }

    //for typeahead
    public List<ImMasterEntity> getMasterListByLike(String coa) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasterEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("primaryKey.nomorMaster", coa + "%"),
                        Restrictions.ilike("nama", "%"+coa+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("primaryKey.nomorMaster"));

        List<ImMasterEntity> results = criteria.list();
        return results;
    }
}
