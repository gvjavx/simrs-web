
package com.neurix.hris.transaksi.rekruitmen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.rekruitmen.model.ItRekruitmenUploadDocEntity;
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
public class RekruitmenUploadDocDao extends GenericDao<ItRekruitmenUploadDocEntity, String> {

    @Override
    protected Class<ItRekruitmenUploadDocEntity> getEntityClass() {
        return ItRekruitmenUploadDocEntity.class;
    }

    @Override
    public List<ItRekruitmenUploadDocEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenUploadDocEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("calon_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("calonPegawaiId", (String) mapCriteria.get("calon_pegawai_id")));
            }
            if (mapCriteria.get("upload_doc_rek_id")!=null) {
                criteria.add(Restrictions.eq("uploadDocRekId", (String) mapCriteria.get("upload_doc_rek_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("uploadDocRekId"));

        List<ItRekruitmenUploadDocEntity> results = criteria.list();

        return results;
    }
    public String getNextRekruitmenDoc() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekruitmen_upload_doc')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "RekDoc"+sId;
    }
    // Generate surrogate id from postgre
    public String getNextPersonalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "D"+sId;
    }

    public String getNextPersonalHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_personal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ItRekruitmenUploadDocEntity> getListPersonal(String term) throws HibernateException {

        List<ItRekruitmenUploadDocEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenUploadDocEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }
    public List<ItRekruitmenUploadDocEntity> getListPersonalByBranch(String term, String branch) throws HibernateException {

        List<ItRekruitmenUploadDocEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenUploadDocEntity.class)
//                .add(Restrictions.ilike("namaPegawai",term))
                .add(Restrictions.eq("branch",branch))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("nip"))
                .list();

        return results;
    }

    public List<ItRekruitmenUploadDocEntity> getListPersonalByBranch2(String term) throws HibernateException {
        List<ItRekruitmenUploadDocEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItRekruitmenUploadDocEntity.class)
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
