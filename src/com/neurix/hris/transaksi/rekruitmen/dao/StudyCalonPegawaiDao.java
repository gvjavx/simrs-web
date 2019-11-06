
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ItStudyCalonPegawaiEntity;
import com.neurix.hris.transaksi.rekruitmen.model.ImRekruitmenHistoryEntity;
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
public class StudyCalonPegawaiDao extends GenericDao<ItStudyCalonPegawaiEntity, String> {

    @Override
    protected Class<ItStudyCalonPegawaiEntity> getEntityClass() {
        return ItStudyCalonPegawaiEntity.class;
    }

    @Override
    public List<ItStudyCalonPegawaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItStudyCalonPegawaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("study_calon_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("studyCalonPegawaiId", (String) mapCriteria.get("study_calon_pegawai_id")));
            }
            if (mapCriteria.get("calon_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("calonPegawaiId", (String) mapCriteria.get("calon_pegawai_id")));
            }
            if (mapCriteria.get("tipe_study")!=null) {
                criteria.add(Restrictions.eq("tipeStudy", (String) mapCriteria.get("tipe_study")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("studyCalonPegawaiId"));

        List<ItStudyCalonPegawaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStudyCalonId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_study_calon_pegawai')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "SCP"+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItStudyCalonPegawaiEntity> getListPersonal(String term) throws HibernateException {

        List<ItStudyCalonPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItStudyCalonPegawaiEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ItStudyCalonPegawaiEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ItStudyCalonPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItStudyCalonPegawaiEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ItStudyCalonPegawaiEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ItStudyCalonPegawaiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItStudyCalonPegawaiEntity.class)
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
