package com.neurix.hris.transaksi.logcron.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.logcron.bo.LogCronBo;
import com.neurix.hris.transaksi.logcron.dao.LogCronDao;
import com.neurix.hris.transaksi.logcron.model.ItLogCronEntity;
import com.neurix.hris.transaksi.logcron.model.LogCron;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogCronBoImpl implements LogCronBo {
    protected static transient Logger logger = Logger.getLogger(LogCronBoImpl.class);
    private LogCronDao logCronDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLogCronDao(LogCronDao logCronDao) {
        this.logCronDao = logCronDao;
    }

    @Override
    public void saveDelete(LogCron bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String logId = bean.getLogCronId();

            ItLogCronEntity itLogCronEntity = null;

            try {
                // Get data from database by ID
                itLogCronEntity = logCronDao.getById("logCronId", logId);
            } catch (HibernateException e) {
                logger.error("[LogCronBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by ID, please inform to your admin...," + e.getMessage());
            }

            if (itLogCronEntity != null) {
                itLogCronEntity.setFlag(bean.getFlag());
                itLogCronEntity.setAction(bean.getAction());
                itLogCronEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itLogCronEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    logCronDao.updateAndSave(itLogCronEntity);
                } catch (HibernateException e) {
                    logger.error("[LogCronBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Log Cron, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LogCronBoImpl.saveDelete] Error, not found data Log Cron with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Log Cron with request id, please check again your data ...");

            }
        }
        logger.info("[LogCronBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(LogCron bean) throws GeneralBOException {

    }

    @Override
    public LogCron saveAdd(LogCron bean) throws GeneralBOException {
        logger.info("[LogCronBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            // creating object entity serializables
            ItLogCronEntity itLogCronEntity = new ItLogCronEntity();
            String logId = logCronDao.getNextLogCronId();
            itLogCronEntity.setLogCronId(logId);
            itLogCronEntity.setCronName(bean.getCronName());
            itLogCronEntity.setStatus(bean.getStatus());
            itLogCronEntity.setCronDate(bean.getCronDate());
            itLogCronEntity.setNote(bean.getNote());

            itLogCronEntity.setFlag(bean.getFlag());
            itLogCronEntity.setAction(bean.getAction());
            itLogCronEntity.setCreatedWho(bean.getCreatedWho());
            itLogCronEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itLogCronEntity.setCreatedDate(bean.getCreatedDate());
            itLogCronEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                logCronDao.addAndSave(itLogCronEntity);
            } catch (HibernateException e) {
                logger.error("[LogCronBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Log Cron, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[LogCronBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<LogCron> getByCriteria(LogCron searchBean) throws GeneralBOException {
        logger.info("[LogCronBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<LogCron> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getLogCronId() != null && !"".equalsIgnoreCase(searchBean.getLogCronId())) {
                hsCriteria.put("logCronId", searchBean.getLogCronId());
            }
            if (searchBean.getCronName() != null && !"".equalsIgnoreCase(searchBean.getCronName())) {
                hsCriteria.put("cronName", searchBean.getCronName());
            }
            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }
            if (searchBean.getStCronDateStr() != null && !"".equalsIgnoreCase(searchBean.getStCronDateStr())) {
                Timestamp cronDateStr = CommonUtil.convertToTimestamp(searchBean.getStCronDateStr());
                hsCriteria.put("cronDateStr", cronDateStr);
            }
            if (searchBean.getStCronDateEnd() != null && !"".equalsIgnoreCase(searchBean.getStCronDateEnd())) {
                Timestamp cronDateEnd = CommonUtil.convertToTimestamp(searchBean.getStCronDateEnd());
                hsCriteria.put("cronDateEnd", cronDateEnd);
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

            List<ItLogCronEntity> itLogCronEntities = null;
            try {
                itLogCronEntities = logCronDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LogCronBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itLogCronEntities != null){
                LogCron returnLogCron;
                // Looping from dao to object and save in collection
                for(ItLogCronEntity itLogCronEntity : itLogCronEntities){
                    returnLogCron = new LogCron();

                    returnLogCron.setLogCronId(itLogCronEntity.getLogCronId());
                    returnLogCron.setCronName(itLogCronEntity.getCronName());
                    returnLogCron.setStatus(itLogCronEntity.getStatus());
                    returnLogCron.setNote(itLogCronEntity.getNote());
                    returnLogCron.setCronDate(itLogCronEntity.getCronDate());
                    returnLogCron.setStCronDate(CommonUtil.convertTimestampToString(itLogCronEntity.getCronDate()));

                    returnLogCron.setCreatedWho(itLogCronEntity.getCreatedWho());
                    returnLogCron.setCreatedDate(itLogCronEntity.getCreatedDate());
                    returnLogCron.setLastUpdateWho(itLogCronEntity.getLastUpdateWho());
                    returnLogCron.setLastUpdate(itLogCronEntity.getLastUpdate());
                    returnLogCron.setAction(itLogCronEntity.getAction());
                    returnLogCron.setFlag(itLogCronEntity.getFlag());

                    listOfResult.add(returnLogCron);
                }
            }
        }
        logger.info("[LogCronBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<LogCron> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
