/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.akuntansi.transaksi.billingSystem.dao;

import com.neurix.akuntansi.transaksi.billingSystem.model.ItPgInvoiceEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PaymentGatewayInvoiceDao  extends GenericDao<ItPgInvoiceEntity, String> {

    @Override
    protected Class<ItPgInvoiceEntity> getEntityClass() {
        return ItPgInvoiceEntity.class;
    }

    @Override
    public List<ItPgInvoiceEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPgInvoiceEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pg_invoice_id")!=null) {
                criteria.add(Restrictions.eq("pgInvoiceId", (String) mapCriteria.get("pg_invoice_id")));
            }

            if (mapCriteria.get("no_invoice")!=null) {
                criteria.add(Restrictions.eq("noInvoice", (String) mapCriteria.get("no_invoice")));
            }

            if (mapCriteria.get("no_rekam_medik")!=null) {
                criteria.add(Restrictions.eq("noRekamMedik", (String) mapCriteria.get("no_rekam_medik")));
            }

            if (mapCriteria.get("no_virtual_account")!=null) {
                criteria.add(Restrictions.eq("noVirtualAccount", (String) mapCriteria.get("no_virtual_account")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("name_person")!=null) {
                criteria.add(Restrictions.eq("namePerson", (String) mapCriteria.get("name_person")));
            }

            if (mapCriteria.get("bank_name")!=null) {
                criteria.add(Restrictions.eq("bankName", (String) mapCriteria.get("bank_name")));
            }

            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }

            if (mapCriteria.get("invoice_date_from")!=null && mapCriteria.get("invoice_date_to")!=null) {
                criteria.add(Restrictions.between("invoiceDate", (Date) mapCriteria.get("invoice_date_from"), (Date) mapCriteria.get("invoice_date_to")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("pgInvoiceId"));

        List<ItPgInvoiceEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPGInvoiceId(String bulan, String tahun, String branchId) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pg_invoice')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = branchId + bulan + per[2] + per[3];

        return "PG" + hasil + sId;
    }
}
