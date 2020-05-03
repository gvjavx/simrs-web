package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.ItSimrsRencanaAsuhanKeperawatanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RencanaAsuhanKeperawatanDao extends GenericDao<ItSimrsRencanaAsuhanKeperawatanEntity, String> {

    @Override
    protected Class<ItSimrsRencanaAsuhanKeperawatanEntity> getEntityClass() {
        return ItSimrsRencanaAsuhanKeperawatanEntity.class;
    }

    @Override
    public List<ItSimrsRencanaAsuhanKeperawatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRencanaAsuhanKeperawatanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_rencana_asuhan_keperawatan")!=null) {
                criteria.add(Restrictions.eq("idRencanaAsuhanKeperawatan", (String) mapCriteria.get("id_rencana_asuhan_keperawatan")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idRencanaAsuhanKeperawatan"));

        List<ItSimrsRencanaAsuhanKeperawatanEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rencana_asuhan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}