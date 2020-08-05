package com.neurix.simrs.master.telemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.simrs.master.telemedic.model.ImSimrsRekeningTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Monday, 03/08/20 10:42
 */
public class RekeningTelemedicDao extends GenericDao<ImSimrsRekeningTelemedicEntity, String> {
    @Override
    protected Class<ImSimrsRekeningTelemedicEntity> getEntityClass() {
        return ImSimrsRekeningTelemedicEntity.class;
    }

    @Override
    public List<ImSimrsRekeningTelemedicEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekeningTelemedicEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_rekening") != null) {
                criteria.add(Restrictions.eq("idRekening", mapCriteria.get("id_rekening").toString()));
            }
            if (mapCriteria.get("pembayaran_id") != null) {
                criteria.add(Restrictions.eq("pembayaranId", mapCriteria.get("pembayaran_id").toString()));
            }
            if (mapCriteria.get("pembayaran_name") != null) {
                criteria.add(Restrictions.ilike("pembayaranName", "%" + mapCriteria.get("pembayaran_name").toString() + "%"));
            }
            if (mapCriteria.get("no_rekening") != null) {
                criteria.add(Restrictions.eq("noRekening", mapCriteria.get("no_rekening").toString()));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsRekeningTelemedicEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public String getNextIdRekening() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekening_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%04d", iter.next());
        return sId;
    }
}
