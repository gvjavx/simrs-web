/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.akuntansi.transaksi.billingSystem.dao;

import com.neurix.akuntansi.transaksi.billingSystem.model.ItPgLogTransactionEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
                criteria.add(Restrictions.eq("pgLogTrxId", (BigInteger) mapCriteria.get("pg_log_trx_id")));
            }

            if (mapCriteria.get("trx_id")!=null) {
                criteria.add(Restrictions.eq("trxId", (String) mapCriteria.get("trx_id")));
            }

            if (mapCriteria.get("tipe_trx")!=null) {
                criteria.add(Restrictions.ilike("tipeTrx", (String) mapCriteria.get("tipe_trx")));
            }

            if (mapCriteria.get("bank_name")!=null) {
                criteria.add(Restrictions.ilike("bankName", (String) mapCriteria.get("bank_name")));
            }

            if (mapCriteria.get("no_virtual_account")!=null) {
                criteria.add(Restrictions.eq("noVirtualAccount", (String) mapCriteria.get("no_virtual_account")));
            }

            if (mapCriteria.get("no_rekam_medik")!=null) {
                criteria.add(Restrictions.eq("noRekamMedik", (String) mapCriteria.get("no_rekam_medik")));
            }

            if (mapCriteria.get("name_person")!=null) {
                criteria.add(Restrictions.ilike("namePerson", (String) "%" + mapCriteria.get("name_person") + "%"));
            }

            if (mapCriteria.get("status_bank")!=null) {
                criteria.add(Restrictions.ilike("statusBank", (String) mapCriteria.get("status_bank")));
            }

            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.ilike("status", (String) mapCriteria.get("status")));
            }

            if (mapCriteria.get("channel")!=null) {
                criteria.add(Restrictions.ilike("channel", (String) mapCriteria.get("channel")));
            }

            if (mapCriteria.get("invoice_number")!=null) {
                criteria.add(Restrictions.eq("invoiceNumber", (BigInteger) mapCriteria.get("invoice_number")));
            }

            if (mapCriteria.get("invoice_date_from")!=null){
                criteria.add(Restrictions.ge("invoiceDate", (Date) mapCriteria.get("invoice_date_from")));
            }
            if (mapCriteria.get("invoice_date_to")!=null){
                criteria.add(Restrictions.le("invoiceDate", (Date) mapCriteria.get("invoice_date_to")));
            }
            //RAKA-16APR2021 ==> Handle status all, by tanggal
            if (mapCriteria.get("received_date_from")!=null && mapCriteria.get("received_date_to") != null && mapCriteria.get("sent_date_from")!=null && mapCriteria.get("sent_date_to") != null) {
                criteria.add(Restrictions.or(
                        Restrictions.between("receivedDate", mapCriteria.get("received_date_from"), mapCriteria.get("received_date_to")),
                        Restrictions.between("sentDate", mapCriteria.get("sent_date_from"), mapCriteria.get("sent_date_to"))
                ));
            } else if (mapCriteria.get("received_date_from")!=null && mapCriteria.get("sent_date_from") != null) {
                criteria.add(Restrictions.or(
                        Restrictions.ge("receivedDate", mapCriteria.get("received_date_from")),
                        Restrictions.ge("sentDate", mapCriteria.get("sent_date_from"))
                ));
            }else if(mapCriteria.get("received_date_to")!=null && mapCriteria.get("sent_date_to") != null){
                criteria.add(Restrictions.or(
                        Restrictions.le("receivedDate", mapCriteria.get("received_date_to")),
                        Restrictions.le("sentDate", mapCriteria.get("sent_date_to"))
                ));
            //RAKA-end
            }else{
                if (mapCriteria.get("received_date_from") != null && mapCriteria.get("received_date_to") != null) {
                    criteria.add(Restrictions.between("receivedDate", mapCriteria.get("received_date_from"), mapCriteria.get("received_date_to")));
                }else if (mapCriteria.get("sent_date_from") != null && mapCriteria.get("sent_date_to") != null) {
                    criteria.add(Restrictions.between("sentDate", mapCriteria.get("sent_date_from"), mapCriteria.get("sent_date_to")));
                }else if(mapCriteria.get("sent_date_from") != null){
                    criteria.add(Restrictions.ge("sentDate", mapCriteria.get("sent_date_from")));
                }else if(mapCriteria.get("sent_date_to") != null){
                    criteria.add(Restrictions.le("sentDate", mapCriteria.get("sent_date_to")));
                }else if(mapCriteria.get("received_date_from") != null){
                    criteria.add(Restrictions.ge("receivedDate", mapCriteria.get("received_date_from")));
                }else if(mapCriteria.get("received_date_to") != null){
                    criteria.add(Restrictions.le("receivedDate", mapCriteria.get("received_date_to")) );
                }
            }
        }

        // Order by
        criteria.addOrder(Order.desc("pgLogTrxId"));

        List<ItPgLogTransactionEntity> results = criteria.list();

        return results;
    }

}
