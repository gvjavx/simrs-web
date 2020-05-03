package com.neurix.simrs.transaksi.asesmenugd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenugd.model.ItSimrsAsesmenUgdEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenUgdDao extends GenericDao<ItSimrsAsesmenUgdEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenUgdEntity> getEntityClass() {
        return ItSimrsAsesmenUgdEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenUgdEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenUgdEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_ugd")!=null) {
                criteria.add(Restrictions.eq("idAsesmenUgd", (String) mapCriteria.get("id_asesmen_ugd")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenUgd"));

        List<ItSimrsAsesmenUgdEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_ugd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}