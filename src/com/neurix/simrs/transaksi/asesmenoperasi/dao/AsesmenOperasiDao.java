package com.neurix.simrs.transaksi.asesmenoperasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsAsesmenOperasiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenOperasiDao extends GenericDao<ItSimrsAsesmenOperasiEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenOperasiEntity> getEntityClass() {
        return ItSimrsAsesmenOperasiEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenOperasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenOperasiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_operasi")!=null) {
                criteria.add(Restrictions.eq("idAsesmenOperasi", (String) mapCriteria.get("id_asesmen_operasi")));
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
        criteria.addOrder(Order.asc("idAsesmenOperasi"));

        List<ItSimrsAsesmenOperasiEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_operasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}