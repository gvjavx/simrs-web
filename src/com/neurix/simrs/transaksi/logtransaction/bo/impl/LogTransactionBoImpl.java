package com.neurix.simrs.transaksi.logtransaction.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.logtransaction.bo.LogTransactionBo;
import com.neurix.simrs.transaksi.logtransaction.dao.LogTransactionDao;
import com.neurix.simrs.transaksi.logtransaction.model.ItPgLogTransactionEntity;
import com.neurix.simrs.transaksi.logtransaction.model.LogTransaction;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

public class LogTransactionBoImpl implements LogTransactionBo {
    protected static transient Logger logger = Logger.getLogger(LogTransactionBoImpl.class);
    private LogTransactionDao logTransactionDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLogTransactionDao(LogTransactionDao logTransactionDao) {
        this.logTransactionDao = logTransactionDao;
    }

    @Override
    public void saveDelete(LogTransaction bean) throws GeneralBOException {
        logger.info("[LogTransactionBoImpl.saveDelete] start process >>>");

        if (bean != null) {

            ItPgLogTransactionEntity itLogTransactionEntity = new ItPgLogTransactionEntity();

            BigInteger logId = bean.getPgLogTrxId();
            List<ItPgLogTransactionEntity> itLogTransactionEntities = new ArrayList<>();
            Map criteria = new HashMap();
            criteria.put("Log_Trx_Id", logId);
            criteria.put("flag", "Y");

            try {
                // Get data from database by ID
                itLogTransactionEntities = logTransactionDao.getByCriteria(criteria);
            } catch (HibernateException e) {
                logger.error("[LogTransactionBoImpl.saveDelete] Error, " + e.getMessage());
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
                    logTransactionDao.updateAndSave(itLogTransactionEntity);
                } catch (HibernateException e) {
                    logger.error("[LogTransactionBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Log Transaction, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LogTransactionBoImpl.saveDelete] Error, not found data Log Transaction with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Log Transaction with request id, please check again your data ...");
            }
        }
        logger.info("[LogTransactionBoImpl.saveDelete] end process <<<");
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
        logger.info("[LogTransactionBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<LogTransaction> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPgLogTrxId() != null) {
                hsCriteria.put("Log_Trx_Id", searchBean.getPgLogTrxId());
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
            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }

            if (searchBean.getStSentDateStr() != null && !"".equalsIgnoreCase(searchBean.getStSentDateStr())) {
                Timestamp sendDate_str = CommonUtil.convertToTimestamp(searchBean.getStSentDateStr());
                hsCriteria.put("sentDate_str", sendDate_str);
            }
            if (searchBean.getStSentDateEnd() != null && !"".equalsIgnoreCase(searchBean.getStSentDateEnd())) {
                Timestamp sendDate_end = CommonUtil.convertToTimestamp(searchBean.getStSentDateEnd());
                hsCriteria.put("sentDate_end", sendDate_end);
            }

            if (searchBean.getStReceivedDateStr() != null && !"".equalsIgnoreCase(searchBean.getStReceivedDateStr())) {
                Timestamp receivedDate_str = CommonUtil.convertToTimestamp(searchBean.getStReceivedDateStr());
                hsCriteria.put("receivedDate_str", receivedDate_str);
            }
            if (searchBean.getStReceivedDateEnd() != null && !"".equalsIgnoreCase(searchBean.getStReceivedDateEnd())) {
                Timestamp receivedDate_end = CommonUtil.convertToTimestamp(searchBean.getStReceivedDateEnd());
                hsCriteria.put("receivedDate_end", receivedDate_end);
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
                itLogTransactionEntities = logTransactionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LogTransactionBoImpl.getByCriteria] Error, " + e.getMessage());
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
        logger.info("[LogTransactionBoImpl.getByCriteria] end process <<<");

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
