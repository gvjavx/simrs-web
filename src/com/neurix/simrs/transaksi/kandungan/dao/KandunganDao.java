package com.neurix.simrs.transaksi.kandungan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsAsesmenKandunganEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KandunganDao extends GenericDao<ItSimrsAsesmenKandunganEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenKandunganEntity> getEntityClass() {
        return ItSimrsAsesmenKandunganEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenKandunganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenKandunganEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_kandungan")!=null) {
                criteria.add(Restrictions.eq("idAsesmenKandungan", (String) mapCriteria.get("id_asesmen_kandungan")));
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
        criteria.addOrder(Order.asc("idAsesmenKandungan"));

        List<ItSimrsAsesmenKandunganEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_kandungan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}