package com.neurix.simrs.transaksi.logtransaction.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.logtransaction.model.ItPgLogTransactionEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class LogTransactionDao extends GenericDao<ItPgLogTransactionEntity, String> {
    @Override
    protected Class<ItPgLogTransactionEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItPgLogTransactionEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPgLogTransactionEntity.class);

        if(mapCriteria != null){
            if(mapCriteria.get("Log_Trx_Id")!=null){
                criteria.add(Restrictions.eq("pgLogTrxId", (BigInteger) mapCriteria.get("Log_Trx_Id")));
            }
            if(mapCriteria.get("trx_id")!=null){
                criteria.add(Restrictions.eq("trxId",(String) mapCriteria.get("trx_id")));
            }
            if(mapCriteria.get("tipe_trx")!=null){
                criteria.add(Restrictions.eq("tipeTrx",(String) mapCriteria.get("tipe_trx")));
            }
            if(mapCriteria.get("bank_name")!=null){
                criteria.add(Restrictions.eq("bankName",(String) mapCriteria.get("bank_name")));
            }
            if(mapCriteria.get("status")!=null){
                criteria.add(Restrictions.eq("status",(String) mapCriteria.get("status")));
            }
            if(mapCriteria.get("trx_id")!=null){
                criteria.add(Restrictions.eq("trxId",(String) mapCriteria.get("trx_id")));
            }


            if(mapCriteria.get("sentDate_str")!=null && mapCriteria.get("sentDate_end")!=null){
                criteria.add(Restrictions.between("sentDate",mapCriteria.get("sentDate_str"),mapCriteria.get("sentDate_end")));
            }else{
                if (mapCriteria.get("sentDate_str")!=null) {
                    criteria.add(Restrictions.ge("sentDate",mapCriteria.get("sentDate_str")));
                }
                if (mapCriteria.get("sentDate_end")!=null) {
                    criteria.add(Restrictions.le("sentDate",mapCriteria.get("sentDate_end")));
                }
            }

            if(mapCriteria.get("receivedDate_str")!=null && mapCriteria.get("receivedDate_end")!=null){
                criteria.add(Restrictions.between("receivedDate",mapCriteria.get("sentDate_str"),mapCriteria.get("sentDate_end")));
            }else{
                if (mapCriteria.get("receivedDate_str")!=null) {
                    criteria.add(Restrictions.ge("receivedDate",mapCriteria.get("receivedDate_str")));
                }
                if (mapCriteria.get("receivedDate_end")!=null) {
                    criteria.add(Restrictions.le("receivedDate",mapCriteria.get("receivedDate_end")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("lastUpdate"));

        List<ItPgLogTransactionEntity> results = criteria.list();

        return results;
    }
}
