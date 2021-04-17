package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.transaksi.billingSystem.bo.LogTrxBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.akuntansi.transaksi.billingSystem.dao.LogTrxDao;
import com.neurix.akuntansi.transaksi.billingSystem.model.ItPgLogTransactionEntity;
import com.neurix.akuntansi.transaksi.billingSystem.model.LogTransaction;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class LogTrxBoImpl implements LogTrxBo {
    protected static transient Logger logger = Logger.getLogger(LogTrxBoImpl.class);
    private LogTrxDao logTrxDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLogTrxDao(LogTrxDao logTrxDao) {
        this.logTrxDao = logTrxDao;
    }

    @Override
    public void saveDelete(LogTransaction bean) throws GeneralBOException {
        logger.info("[LogTrxBoImpl.saveDelete] start process >>>");

        if (bean != null) {

            ItPgLogTransactionEntity itLogTransactionEntity = new ItPgLogTransactionEntity();

            BigInteger logId = bean.getPgLogTrxId();
            List<ItPgLogTransactionEntity> itLogTransactionEntities = new ArrayList<>();
            Map criteria = new HashMap();
            criteria.put("pg_log_trx_id", logId);
            criteria.put("flag", "Y");

            try {
                // Get data from database by ID
                itLogTransactionEntities = logTrxDao.getByCriteria(criteria);
            } catch (HibernateException e) {
                logger.error("[LogTrxBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by ID, please inform to your admin..., " + e.getMessage());
            }
            if (itLogTransactionEntities.size() > 0) {
                itLogTransactionEntity = itLogTransactionEntities.get(0);

                itLogTransactionEntity.setFlag(bean.getFlag());
                itLogTransactionEntity.setAction(bean.getAction());
                itLogTransactionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itLogTransactionEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    logTrxDao.updateAndSave(itLogTransactionEntity);
                } catch (HibernateException e) {
                    logger.error("[LogTrxBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Log Transaction, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LogTrxBoImpl.saveDelete] Error, not found data Log Transaction with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Log Transaction with request id, please check again your data ...");
            }
        }
        logger.info("[LogTrxBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(LogTransaction bean) throws GeneralBOException {

    }

    @Override
    public LogTransaction saveAdd(LogTransaction bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<LogTransaction> getByCriteria(LogTransaction searchBean) throws GeneralBOException {
        logger.info("[LogTrxBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<LogTransaction> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPgLogTrxId() != null) {
                hsCriteria.put("pg_log_trx_id", searchBean.getPgLogTrxId());
            }
            if (searchBean.getTrxId() != null && !"".equalsIgnoreCase(searchBean.getTrxId())) {
                hsCriteria.put("trx_id", searchBean.getTrxId());
            }
            if (searchBean.getTipeTrx() != null && !"".equalsIgnoreCase(searchBean.getTipeTrx())) {
                hsCriteria.put("tipe_trx", searchBean.getTipeTrx());
            }
            if (searchBean.getBankName() != null && !"".equalsIgnoreCase(searchBean.getBankName())) {
                hsCriteria.put("bank_name", searchBean.getBankName());
            }

            if (searchBean.getNoVirtualAccount() != null && !"".equalsIgnoreCase(searchBean.getNoVirtualAccount())) {
                hsCriteria.put("no_virtual_account", searchBean.getNoVirtualAccount());
            }
            if (searchBean.getNoRekamMedik() != null && !"".equalsIgnoreCase(searchBean.getNoRekamMedik())) {
                hsCriteria.put("no_rekam_medik", searchBean.getNoRekamMedik());
            }
            if (searchBean.getNamePerson() != null && !"".equalsIgnoreCase(searchBean.getNamePerson())) {
                hsCriteria.put("name_person", searchBean.getNamePerson());
            }
            if (searchBean.getStatusBank() != null && !"".equalsIgnoreCase(searchBean.getStatusBank())) {
                hsCriteria.put("status_bank", searchBean.getStatusBank());
            }

            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }

            if (searchBean.getChannel() != null && !"".equalsIgnoreCase(searchBean.getChannel())) {
                hsCriteria.put("channel", searchBean.getChannel());
            }
            if (searchBean.getInvoiceNumber() != null) {
                hsCriteria.put("invoice_number", searchBean.getStatus());
            }
            if(searchBean.getStInvDateFrom() != null){
                Date invDateFrom = CommonUtil.convertStringToDate(searchBean.getStInvDateFrom());
                hsCriteria.put("invoice_date_from", invDateFrom);
            }
            if(searchBean.getStInvDateTo() != null){
                Date invDateTo = CommonUtil.convertStringToDate(searchBean.getStInvDateTo());
                hsCriteria.put("invoice_date_from", invDateTo);
            }

            if (searchBean.getStReceivedDateFrom() != null && !"".equalsIgnoreCase(searchBean.getStReceivedDateFrom())) {
                Timestamp receivedDateFrom = CommonUtil.convertToTimestamp(searchBean.getStReceivedDateFrom());
                hsCriteria.put("received_date_from", receivedDateFrom);
            }
            if (searchBean.getStReceivedDateTo() != null && !"".equalsIgnoreCase(searchBean.getStReceivedDateTo())) {
                Timestamp receivedDateTo = CommonUtil.convertToTimestamp(searchBean.getStReceivedDateTo());
                hsCriteria.put("received_date_to", receivedDateTo);
            }

            if (searchBean.getStSentDateFrom() != null && !"".equalsIgnoreCase(searchBean.getStSentDateFrom())) {
                Timestamp sentDateFrom = CommonUtil.convertToTimestamp(searchBean.getStSentDateFrom());
                hsCriteria.put("sent_date_from", sentDateFrom);
            }
            if (searchBean.getStSentDateTo() != null && !"".equalsIgnoreCase(searchBean.getStSentDateTo())) {
                Timestamp sentDateTo = CommonUtil.convertToTimestamp(searchBean.getStSentDateTo());
                hsCriteria.put("sent_date_to", sentDateTo);
            }

            if (searchBean.getStDateStr() != null && !"".equalsIgnoreCase(searchBean.getStDateStr())) {
                Timestamp date_str = CommonUtil.convertToTimestamp(searchBean.getStDateStr());
                if(!"out".equalsIgnoreCase(searchBean.getStatus())) {
                    hsCriteria.put("received_date_from", date_str);
                }
                if (!"in".equalsIgnoreCase(searchBean.getStatus())){
                    hsCriteria.put("sent_date_from", date_str);
                }
            }
            if (searchBean.getStDateEnd() != null && !"".equalsIgnoreCase(searchBean.getStDateEnd())) {
                Timestamp date_end = CommonUtil.convertToTimestamp(searchBean.getStDateEnd());
                date_end.setTime(date_end.getTime() + 86400000);
                if(!"out".equalsIgnoreCase(searchBean.getStatus())) {
                    hsCriteria.put("received_date_to", date_end);
                }
                if (!"in".equalsIgnoreCase(searchBean.getStatus())){
                    hsCriteria.put("sent_date_to", date_end);
                }
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ItPgLogTransactionEntity> itLogTransactionEntities = null;
            try {
                itLogTransactionEntities = logTrxDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LogTrxBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itLogTransactionEntities != null) {
                LogTransaction returnLogTransaction;
                // Looping from dao to object and save in collection
                for (ItPgLogTransactionEntity itLogTransactionEntity : itLogTransactionEntities) {
                    returnLogTransaction = new LogTransaction();

                    returnLogTransaction.setPgLogTrxId(itLogTransactionEntity.getPgLogTrxId());
                    returnLogTransaction.setTrxId(itLogTransactionEntity.getTrxId());
                    returnLogTransaction.setTipeTrx(itLogTransactionEntity.getTipeTrx());
                    returnLogTransaction.setBankName(itLogTransactionEntity.getBankName());
                    returnLogTransaction.setNoVirtualAccount(itLogTransactionEntity.getNoVirtualAccount());
                    returnLogTransaction.setNoRekamMedik(itLogTransactionEntity.getNoRekamMedik());
                    returnLogTransaction.setTrxAmount(itLogTransactionEntity.getTrxAmount());
                    returnLogTransaction.setNamePerson(itLogTransactionEntity.getNamePerson());
                    returnLogTransaction.setAddressPerson(itLogTransactionEntity.getAddressPerson());
                    returnLogTransaction.setPhonePerson(itLogTransactionEntity.getPhonePerson());
                    returnLogTransaction.setEmailPerson(itLogTransactionEntity.getEmailPerson());
                    returnLogTransaction.setStatus(itLogTransactionEntity.getStatus());
                    returnLogTransaction.setMessage(itLogTransactionEntity.getMessage());
                    returnLogTransaction.setSentDate(itLogTransactionEntity.getSentDate());
                    returnLogTransaction.setReceivedDate(itLogTransactionEntity.getReceivedDate());

                    returnLogTransaction.setStatusBank(itLogTransactionEntity.getStatusBank());
                    returnLogTransaction.setChannel(itLogTransactionEntity.getChannel());
                    returnLogTransaction.setStInvoiceDate(CommonUtil.ddMMyyyyFormat(itLogTransactionEntity.getInvoiceDate()));
                    returnLogTransaction.setInvoiceDate(itLogTransactionEntity.getInvoiceDate());
                    returnLogTransaction.setInvoiceNumber(itLogTransactionEntity.getInvoiceNumber());

                    returnLogTransaction.setCreatedWho(itLogTransactionEntity.getCreatedWho());
                    returnLogTransaction.setCreatedDate(itLogTransactionEntity.getCreatedDate());
                    returnLogTransaction.setLastUpdateWho(itLogTransactionEntity.getLastUpdateWho());
                    returnLogTransaction.setLastUpdate(itLogTransactionEntity.getLastUpdate());
                    returnLogTransaction.setAction(itLogTransactionEntity.getAction());
                    returnLogTransaction.setFlag(itLogTransactionEntity.getFlag());

                    listOfResult.add(returnLogTransaction);
                }
            }
        }
        logger.info("[LogTrxBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<LogTransaction> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
