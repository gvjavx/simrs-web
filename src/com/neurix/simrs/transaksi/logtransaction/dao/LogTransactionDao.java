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


            if(mapCriteria.get("dateStr")!=null && mapCriteria.get("dateEnd")!=null){
                if("in".equalsIgnoreCase((String) mapCriteria.get("status"))) {
                    criteria.add(Restrictions.between("receivedDate", mapCriteria.get("dateStr"), mapCriteria.get("dateEnd")));
                }else if("out".equalsIgnoreCase((String) mapCriteria.get("status"))){
                    criteria.add(Restrictions.between("sentDate", mapCriteria.get("dateStr"), mapCriteria.get("dateEnd")));
                }else{
                    criteria.add(Restrictions.or(
                            Restrictions.between("receivedDate", mapCriteria.get("dateStr"), mapCriteria.get("dateEnd")),
                            Restrictions.between("sentDate", mapCriteria.get("dateStr"), mapCriteria.get("dateEnd"))
                    ));
                }
            }else{
                if (mapCriteria.get("dateStr")!=null) {
                    if("in".equalsIgnoreCase((String) mapCriteria.get("status"))) {
                        criteria.add(Restrictions.ge("receivedDate", mapCriteria.get("dateStr")));
                    }else if("out".equalsIgnoreCase((String) mapCriteria.get("status"))){
                        criteria.add(Restrictions.ge("sentDate", mapCriteria.get("dateStr")));
                    }else{
                        criteria.add(Restrictions.or(
                                Restrictions.ge("receivedDate", mapCriteria.get("dateStr")),
                                Restrictions.ge("sentDate", mapCriteria.get("dateStr"))
                                ));
                    }
                }
                if (mapCriteria.get("dateEnd")!=null) {
                    if("in".equalsIgnoreCase((String) mapCriteria.get("status"))) {
                        criteria.add(Restrictions.le("receivedDate", mapCriteria.get("dateEnd")));
                    }else if("out".equalsIgnoreCase((String) mapCriteria.get("status"))){
                        criteria.add(Restrictions.le("sentDate", mapCriteria.get("dateEnd")));
                    }else{
                        criteria.add(Restrictions.or(
                                Restrictions.le("receivedDate", mapCriteria.get("dateEnd")),
                                Restrictions.le("sentDate", mapCriteria.get("dateEnd"))
                        ));
                    }
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
