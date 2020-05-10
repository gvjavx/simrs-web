package com.neurix.akuntansi.master.pembayaran.dao;

import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
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

public class PembayaranDao extends GenericDao<ImAkunPembayaranEntity, String> {

    @Override
    protected Class<ImAkunPembayaranEntity> getEntityClass() {
        return ImAkunPembayaranEntity.class;
    }

    @Override
    public List<ImAkunPembayaranEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunPembayaranEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pembayaran_id")!=null) {
                criteria.add(Restrictions.eq("pembayaranId", (String) mapCriteria.get("pembayaran_id")));
            }
            if (mapCriteria.get("pembayaran_name")!=null) {
                criteria.add(Restrictions.ilike("pembayaranName", "%" + (String)mapCriteria.get("pembayaran_name") + "%"));
            }
            if (mapCriteria.get("coa")!=null) {
                criteria.add(Restrictions.eq("coa", (String) mapCriteria.get("coa")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }
        // Order by
        criteria.addOrder(Order.asc("pembayaranId"));
        List<ImAkunPembayaranEntity> results = criteria.list();

        return results;
    }

    public List<ImAkunPembayaranEntity> getDataAkunPembayaran(String namaPembayaran) throws HibernateException {
        List<ImAkunPembayaranEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImAkunPembayaranEntity.class)
                .add(Restrictions.eq("pembayaranName", namaPembayaran))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextLabDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_akun_pembayaran')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "APM" + sId;
    }
}
