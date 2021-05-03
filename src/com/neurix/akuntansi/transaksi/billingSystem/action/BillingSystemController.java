/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.akuntansi.transaksi.billingSystem.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.exception.GeneralBOException;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BillingSystemController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(BillingSystemController.class);
    private BillingSystemBo billingSystemBoProxy;
    private String id;
    private String bank;

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //updated by ferdi, 01-12-2020, to update invoice after get response paymnet from payment gateway
    public String updateInvoicePaymentGateway() {
        logger.info("[BillingSystemController.updateInvoicePaymentGateway] end process POST /settlementinvoice <<<");

        Map mapParam = new HashMap();
        mapParam.put("bank_name", bank);
        mapParam.put("invoice_number", id);

        try {

            billingSystemBoProxy.settlementPGInvoice(mapParam);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = billingSystemBoProxy.saveErrorMessage(e.getMessage(), "BillingSystemController.updateInvoicePaymentGateway");
            } catch (GeneralBOException e1) {
                logger.error("[BillingSystemController.updateInvoicePaymentGateway] Error when saving error,", e1);
            }
            logger.error("[BillingSystemController.updateInvoicePaymentGateway] Error when updating settlement invoice from payment gateway," + "[" + logId + "] Found problem when updating settlement invoice from payment gateway, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        logger.info("[BillingSystemController.updateInvoicePaymentGateway] end process POST /settlementinvoice <<<");
        return "success";
    }

    @Override
    public Object getModel() {
        return null;
    }
}
