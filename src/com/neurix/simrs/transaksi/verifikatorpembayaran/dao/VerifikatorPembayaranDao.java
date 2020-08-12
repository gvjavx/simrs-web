package com.neurix.simrs.transaksi.verifikatorpembayaran.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranDao extends GenericDao<ItSimrsPembayaranOnlineEntity, String> {

    @Override
    protected Class<ItSimrsPembayaranOnlineEntity> getEntityClass() {
        return ItSimrsPembayaranOnlineEntity.class;
    }

    @Override
    public List<ItSimrsPembayaranOnlineEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPembayaranOnlineEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_antrian_telemedic") != null){
            criteria.add(Restrictions.eq("idAntrianTelemedic", mapCriteria.get("id_antrian_telemedic").toString()));
        }
        if (mapCriteria.get("keterangan") != null){
            criteria.add(Restrictions.eq("keterangan", mapCriteria.get("keterangan").toString()));
        }
        if (mapCriteria.get("approve_flag") != null){
            criteria.add(Restrictions.eq("approveFlag", mapCriteria.get("approve_flag").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
