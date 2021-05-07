
package com.neurix.hris.master.biodata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.ImBiodataHistoryEntity;
import com.neurix.hris.master.biodata.model.ImBiodataHistoryEntity;
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
public class BiodataHistoryDao extends GenericDao<ImBiodataHistoryEntity, String> {

    @Override
    protected Class<ImBiodataHistoryEntity> getEntityClass() {
        return ImBiodataHistoryEntity.class;
    }

    @Override
    public List<ImBiodataHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBiodataHistoryEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("nama_pegawai")!=null) {
                criteria.add(Restrictions.ilike("namaPegawai", "%" + (String)mapCriteria.get("nama_pegawai") + "%"));
            }
            if (mapCriteria.get("divisi")!=null) {
                criteria.add(Restrictions.eq("divisi", (String) mapCriteria.get("divisi")));
            }
            if (mapCriteria.get("branch")!=null) {
                criteria.add(Restrictions.eq("branch", (String) mapCriteria.get("branch")));
            }
            if (mapCriteria.get("tipe_pegawai")!=null) {
                criteria.add(Restrictions.eq("tipePegawai", (String) mapCriteria.get("tipe_pegawai")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("masa_giling")!=null) {
                criteria.add(Restrictions.eq("masaGiling", (String) mapCriteria.get("masa_giling")));
            }
            if (mapCriteria.get("from")!=null) {
                criteria.add(Restrictions.ne("tipePegawai", "TP1"));
            }
            if (mapCriteria.get("pin")!=null) {
                criteria.add(Restrictions.eq("pin", (String) mapCriteria.get("pin")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        criteria.addOrder(Order.asc("id"));
        List<ImBiodataHistoryEntity> results = criteria.list();

        return results;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "HP"+sId;
    }

    public List<ImBiodataHistoryEntity> getListPersonal(String term) throws HibernateException {
        List<ImBiodataHistoryEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataHistoryEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public List<ImBiodataHistoryEntity> getListPersonalPayroll(String nip) throws HibernateException {
        List<ImBiodataHistoryEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiodataHistoryEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();
        return results;
    }

    public void addAndSaveHistory(ImBiodataHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
