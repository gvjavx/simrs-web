package com.neurix.simrs.transaksi.obatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ObatInapDao extends GenericDao<ItSimrsObatInapEntity, String> {
    @Override
    protected Class<ItSimrsObatInapEntity> getEntityClass() {
        return ItSimrsObatInapEntity.class;
    }

    @Override
    public List<ItSimrsObatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsObatInapEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_obat_inap")!=null) {
                criteria.add(Restrictions.eq("idObatInap", (String) mapCriteria.get("id_obat_inap")));
            }
            if (mapCriteria.get("nama_obat")!=null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String)mapCriteria.get("nama_obat") + "%"));
            }
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idObatInap"));

        List<ItSimrsObatInapEntity> results = criteria.list();

        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat_inap')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}