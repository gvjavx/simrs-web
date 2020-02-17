package com.neurix.simrs.transaksi.monvitalsign.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/02/20.
 */
public class MonVitalSignDao extends GenericDao<ItSimrsMonVitalSignEntity, String>{
    @Override
    protected Class<ItSimrsMonVitalSignEntity> getEntityClass() {
        return ItSimrsMonVitalSignEntity.class;
    }

    @Override
    public List<ItSimrsMonVitalSignEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonVitalSignEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        criteria.addOrder(Order.desc("createdDate"));
        List<ItSimrsMonVitalSignEntity> resuts = criteria.list();
        return resuts;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_mon_vital_sign')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<MonVitalSign> getListGraf(String idDetail){

        String SQL = "SELECT \n" +
                "created_date,\n" +
                "jam, \n" +
                "nafas,\n" +
                "nadi,\n" +
                "suhu\n" +
                "FROM it_simrs_mon_vital_sign vital\n" +
                "WHERE id_detail_checkup = :detail \n" +
                "ORDER BY created_date, jam";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("detail", idDetail)
                .list();

        List<MonVitalSign> monVitalSigns = new ArrayList<>();
        if (results.size() > 0){
            MonVitalSign monVitalSign;
            for (Object[] obj : results){
                monVitalSign = new MonVitalSign();
                monVitalSign.setCreatedDate((Timestamp) obj[0]);
                monVitalSign.setJam((Integer) obj[1]);
                monVitalSign.setNafas((Integer) obj[2]);
                monVitalSign.setNadi((Integer) obj[3]);
                monVitalSign.setSuhu((Integer) obj[4]);
                monVitalSigns.add(monVitalSign);
            }
        }
        return monVitalSigns;
    }
}
