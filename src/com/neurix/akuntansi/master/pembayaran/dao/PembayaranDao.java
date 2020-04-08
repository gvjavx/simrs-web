package com.neurix.akuntansi.master.pembayaran.dao;

import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
}
