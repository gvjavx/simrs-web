package com.neurix.hris.master.pengaliFaktorLembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLemburEntity;
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
public class PengaliFaktorLemburDao extends GenericDao<PengaliFaktorLemburEntity, String> {

    @Override
    protected Class<PengaliFaktorLemburEntity> getEntityClass() {
        return PengaliFaktorLemburEntity.class;
    }

    @Override
    public List<PengaliFaktorLemburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(PengaliFaktorLemburEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengali_faktor_id")!=null) {
                criteria.add(Restrictions.ilike("pengaliFaktorId","%" + (String) mapCriteria.get("pengali_faktor_id")+ "%"));
            }
            if (mapCriteria.get("tipe_hari")!=null) {
                criteria.add(Restrictions.eq("tipeHari",(String) mapCriteria.get("tipe_hari")));
            }
            if (mapCriteria.get("faktor")!=null) {
                criteria.add(Restrictions.eq("faktor", mapCriteria.get("faktor")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pengaliFaktorId"));

        List<PengaliFaktorLemburEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPengaliFaktorId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_pengali_faktor_lembur')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "PF"+sId;
    }

    public List<PengaliFaktorLemburEntity> getListPengaliFaktorLembur(String term) throws HibernateException {

        List<PengaliFaktorLemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(PengaliFaktorLemburEntity.class)
                .add(Restrictions.ilike("pengaliFaktorLemburId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodeAlat"))
                .list();

        return results;
    }
}
