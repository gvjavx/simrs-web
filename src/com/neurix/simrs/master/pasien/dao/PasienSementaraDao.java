package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienSementaraEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PasienSementaraDao extends GenericDao<ImSimrsPasienSementaraEntity, String> {

    @Override
    protected Class<ImSimrsPasienSementaraEntity> getEntityClass() {
        return ImSimrsPasienSementaraEntity.class;
    }

    @Override
    public List<ImSimrsPasienSementaraEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPasienSementaraEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id") != null) {
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
            }
            if (mapCriteria.get("desa_id") != null) {
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            }
            if (mapCriteria.get("no_ktp") != null) {
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("email") != null) {
                criteria.add(Restrictions.eq("email", mapCriteria.get("email").toString()));
            }
        }

        criteria.addOrder(Order.asc("createdDate"));
        List<ImSimrsPasienSementaraEntity> list = criteria.list();

        return list;
    }

    public String getNextIdPasienSementara() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pasien_sementara')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", iter.next());
        return sId;
    }
}
