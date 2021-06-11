package com.neurix.simrs.transaksi.asesmengizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmengizi.model.ItSimrsAsesmenGiziEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenGiziDao extends GenericDao<ItSimrsAsesmenGiziEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenGiziEntity> getEntityClass() {
        return ItSimrsAsesmenGiziEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenGiziEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_gizi")!=null) {
                criteria.add(Restrictions.eq("idAsesmenGizi", (String) mapCriteria.get("id_asesmen_gizi")));
            }
            if (mapCriteria.get("no_checkup")!=null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
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
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.eq("createdDate", (Timestamp) mapCriteria.get("created_date")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenGizi"));
        List<ItSimrsAsesmenGiziEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_gizi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "ASG"+sId;
    }
}