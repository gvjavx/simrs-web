package com.neurix.hris.transaksi.lembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
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
                criteria.add(Restrictions.eq("pengaliFaktorId", (String) mapCriteria.get("pengali_faktor_id")));
            }
            if (mapCriteria.get("tipe_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("tipePegawaiId", (String) mapCriteria.get("tipe_pegawai_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengaliFaktorId"));

        List<PengaliFaktorLemburEntity> results = criteria.list();

        return results;
    }

    public String getNextLemburId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lembur')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "LE"+sId;
    }
}
