/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.akuntansi.transaksi.billingSystem.dao;

import com.neurix.akuntansi.transaksi.billingSystem.model.ItPgLogTransactionEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class LogTrxDao extends GenericDao<ItPgLogTransactionEntity, String> {

    @Override
    protected Class<ItPgLogTransactionEntity> getEntityClass() {
        return ItPgLogTransactionEntity.class;
    }

    @Override
    public List<ItPgLogTransactionEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPgLogTransactionEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pg_log_trx_id")!=null) {
                criteria.add(Restrictions.eq("pgLogTrxId", (String) mapCriteria.get("pg_log_trx_id")));
            }

            if (mapCriteria.get("trx_id")!=null) {
                criteria.add(Restrictions.eq("trxId", (String) mapCriteria.get("trx_id")));
            }

            if (mapCriteria.get("tipe_trx")!=null) {
                criteria.add(Restrictions.eq("tipeTrx", (String) mapCriteria.get("tipe_trx")));
            }

            if (mapCriteria.get("bank_name")!=null) {
                criteria.add(Restrictions.eq("bankName", (String) mapCriteria.get("bank_name")));
            }

            if (mapCriteria.get("no_virtual_account")!=null) {
                criteria.add(Restrictions.eq("noVirtualAccount", (String) mapCriteria.get("no_virtual_account")));
            }

            if (mapCriteria.get("no_rekam_medik")!=null) {
                criteria.add(Restrictions.eq("noRekamMedik", (String) mapCriteria.get("no_rekam_medik")));
            }

            if (mapCriteria.get("name_person")!=null) {
                criteria.add(Restrictions.eq("namePerson", (String) mapCriteria.get("name_person")));
            }

            if (mapCriteria.get("status_bank")!=null) {
                criteria.add(Restrictions.eq("statusBank", (String) mapCriteria.get("status_bank")));
            }

            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }

            if (mapCriteria.get("channel")!=null) {
                criteria.add(Restrictions.eq("channel", (String) mapCriteria.get("channel")));
            }

            if (mapCriteria.get("invoice_number")!=null) {
                criteria.add(Restrictions.eq("invoiceNumber", (String) mapCriteria.get("invoice_number")));
            }

            if (mapCriteria.get("received_date_from")!=null && mapCriteria.get("received_date_to")!=null) {
//                criteria.add(Restrictions.between("receivedDate", (Date) mapCriteria.get("received_date_from"), (Date) mapCriteria.get("received_date_to")));
                criteria.add(Restrictions.between("receivedDate", mapCriteria.get("received_date_from"), mapCriteria.get("received_date_to")));

            }

            if (mapCriteria.get("sent_date_from")!=null && mapCriteria.get("sent_date_to")!=null) {
//                criteria.add(Restrictions.between("sentDate", (Date) mapCriteria.get("sent_date_from"), (Date) mapCriteria.get("sent_date_to")));
                criteria.add(Restrictions.between("sentDate", mapCriteria.get("sent_date_from"), mapCriteria.get("sent_date_to")));

            }


        }

        // Order by
        criteria.addOrder(Order.desc("pgLogTrxId"));

        List<ItPgLogTransactionEntity> results = criteria.list();

        return results;
    }

}
