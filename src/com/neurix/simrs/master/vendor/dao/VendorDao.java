package com.neurix.simrs.master.vendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class VendorDao extends GenericDao<ImSimrsVendorEntity, String> {
    @Override
    protected Class<ImSimrsVendorEntity> getEntityClass() {
        return ImSimrsVendorEntity.class;
    }

    @Override
    public List<ImSimrsVendorEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsVendorEntity.class);
        if (mapCriteria.get("id_vendor") != null)
            criteria.add(Restrictions.eq("idVendor", mapCriteria.get("id_vendor").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        List<ImSimrsVendorEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_vendor_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
