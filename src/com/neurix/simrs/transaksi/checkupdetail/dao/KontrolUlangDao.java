package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsKontrolUlangEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KontrolUlangDao extends GenericDao<ItSimrsKontrolUlangEntity, String> {

    @Override
    protected Class<ItSimrsKontrolUlangEntity> getEntityClass() {
        return ItSimrsKontrolUlangEntity.class;
    }

    @Override
    public List<ItSimrsKontrolUlangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsKontrolUlangEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_kontrol_ulang") != null) {
                criteria.add(Restrictions.eq("idKontrolUlang", (String) mapCriteria.get("id_kontrol_ulang")));
            }
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        List<ItSimrsKontrolUlangEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kontrol_ulang')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "KUL"+sId;
    }
}
