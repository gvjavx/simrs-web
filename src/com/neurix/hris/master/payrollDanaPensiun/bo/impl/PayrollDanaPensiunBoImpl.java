package com.neurix.hris.master.payrollDanaPensiun.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollDanaPensiun.bo.PayrollDanaPensiunBo;
import com.neurix.hris.master.payrollDanaPensiun.dao.PayrollDanaPensiunDao;
import com.neurix.hris.master.payrollDanaPensiun.model.ImPayrollDanaPensiunEntity;
import com.neurix.hris.master.payrollDanaPensiun.model.payrollDanaPensiun;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class PayrollDanaPensiunBoImpl implements PayrollDanaPensiunBo {

    protected static transient Logger logger = Logger.getLogger(PayrollDanaPensiunBoImpl.class);
    private PayrollDanaPensiunDao payrollDanaPensiunDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollDanaPensiunBoImpl.logger = logger;
    }

    public PayrollDanaPensiunDao getPayrollDanaPensiunDao() {
        return payrollDanaPensiunDao;
    }


    public void setPayrollDanaPensiunDao(PayrollDanaPensiunDao payrollDanaPensiunDao) {
        this.payrollDanaPensiunDao = payrollDanaPensiunDao;
    }

    @Override
    public void saveDelete(payrollDanaPensiun bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollDanaPensiunId = bean.getDanaPensiunId();

            ImPayrollDanaPensiunEntity imPayrollDanaPensiunEntity = null;

            try {
                // Get data from database by ID
                imPayrollDanaPensiunEntity = payrollDanaPensiunDao.getById("danaPensiunId", payrollDanaPensiunId);
            } catch (HibernateException e) {
                logger.error("[PayrollDanaPensiunBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollDanaPensiunEntity != null) {

                // Modify from bean to entity serializable
                imPayrollDanaPensiunEntity.setDanaPensiunId(bean.getDanaPensiunId());
                imPayrollDanaPensiunEntity.setFlag(bean.getFlag());
                imPayrollDanaPensiunEntity.setAction(bean.getAction());
                imPayrollDanaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollDanaPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollDanaPensiunDao.updateAndSave(imPayrollDanaPensiunEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollDanaPensiunBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollDanaPensiun, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollDanaPensiunBoImpl.saveDelete] Error, not found data PayrollDanaPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollDanaPensiun with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollDanaPensiunBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollDanaPensiun bean) throws GeneralBOException {
        logger.info("[PayrollDanaPensiunBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollDanaPensiunId = bean.getDanaPensiunId();

            ImPayrollDanaPensiunEntity imPayrollDanaPensiunEntity = null;
            //ImPayrollDanaPensiunHistoryEntity imPayrollDanaPensiunHistoryEntity = new ImPayrollDanaPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollDanaPensiunEntity = payrollDanaPensiunDao.getById("danaPensiunId", payrollDanaPensiunId);
                //historyId = payrollDanaPensiunDao.getNextPayrollDanaPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollDanaPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollDanaPensiun by Kode PayrollDanaPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollDanaPensiunEntity != null) {
                /*imPayrollDanaPensiunHistoryEntity.setId(historyId);
                imPayrollDanaPensiunHistoryEntity.setPayrollDanaPensiunId(imPayrollDanaPensiunEntity.getPayrollDanaPensiunId());
                imPayrollDanaPensiunHistoryEntity.setPayrollDanaPensiunName(imPayrollDanaPensiunEntity.getPayrollDanaPensiunName());
                imPayrollDanaPensiunHistoryEntity.setFlag(imPayrollDanaPensiunEntity.getFlag());
                imPayrollDanaPensiunHistoryEntity.setAction(imPayrollDanaPensiunEntity.getAction());
                imPayrollDanaPensiunHistoryEntity.setLastUpdateWho(imPayrollDanaPensiunEntity.getLastUpdateWho());
                imPayrollDanaPensiunHistoryEntity.setLastUpdate(imPayrollDanaPensiunEntity.getLastUpdate());
                imPayrollDanaPensiunHistoryEntity.setCreatedWho(imPayrollDanaPensiunEntity.getLastUpdateWho());
                imPayrollDanaPensiunHistoryEntity.setCreatedDate(imPayrollDanaPensiunEntity.getLastUpdate());*/

                imPayrollDanaPensiunEntity.setDanaPensiunId(bean.getDanaPensiunId());
                imPayrollDanaPensiunEntity.setDanaPensiun(bean.getDanaPensiun());

                imPayrollDanaPensiunEntity.setPersentase(bean.getPersentase());
                imPayrollDanaPensiunEntity.setFlag(bean.getFlag());
                imPayrollDanaPensiunEntity.setAction(bean.getAction());
                imPayrollDanaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollDanaPensiunEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollDanaPensiunDao.updateAndSave(imPayrollDanaPensiunEntity);
                    //payrollDanaPensiunDao.addAndSaveHistory(imPayrollDanaPensiunHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollDanaPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollDanaPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollDanaPensiunBoImpl.saveEdit] Error, not found data PayrollDanaPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollDanaPensiun with request id, please check again your data ...");
//                condition = "Error, not found data PayrollDanaPensiun with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollDanaPensiunBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollDanaPensiun saveAdd(payrollDanaPensiun bean) throws GeneralBOException {
        logger.info("[PayrollDanaPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollDanaPensiunId;
            try {
                // Generating ID, get from postgre sequence
                payrollDanaPensiunId = payrollDanaPensiunDao.getNextDanaPensiunId();
            } catch (HibernateException e) {
                logger.error("[PayrollDanaPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollDanaPensiunId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollDanaPensiunEntity imPayrollDanaPensiunEntity = new ImPayrollDanaPensiunEntity();

            imPayrollDanaPensiunEntity.setDanaPensiunId(payrollDanaPensiunId);
            imPayrollDanaPensiunEntity.setDanaPensiun(bean.getDanaPensiun());

            imPayrollDanaPensiunEntity.setPersentase(bean.getPersentase());
            imPayrollDanaPensiunEntity.setFlag(bean.getFlag());
            imPayrollDanaPensiunEntity.setAction(bean.getAction());
            imPayrollDanaPensiunEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollDanaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollDanaPensiunEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollDanaPensiunEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollDanaPensiunDao.addAndSave(imPayrollDanaPensiunEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollDanaPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollDanaPensiun, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollDanaPensiunBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollDanaPensiun> getByCriteria(payrollDanaPensiun searchBean) throws GeneralBOException {
        logger.info("[PayrollDanaPensiunBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollDanaPensiun> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getDanaPensiunId() != null && !"".equalsIgnoreCase(searchBean.getDanaPensiunId())) {
                hsCriteria.put("dana_pensiun_id", searchBean.getDanaPensiunId());
            }
            if (searchBean.getDanaPensiun() != null && !"".equalsIgnoreCase(searchBean.getDanaPensiun())) {
                hsCriteria.put("dana_pensiun", searchBean.getDanaPensiun());
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


            List<ImPayrollDanaPensiunEntity> imPayrollDanaPensiunEntity = null;
            try {

                imPayrollDanaPensiunEntity = payrollDanaPensiunDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollDanaPensiunBoImpl.getSearchPayrollDanaPensiunByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollDanaPensiunEntity != null){
                payrollDanaPensiun returnPayrollDanaPensiun;
                // Looping from dao to object and save in collection
                for(ImPayrollDanaPensiunEntity payrollDanaPensiunEntity : imPayrollDanaPensiunEntity){
                    returnPayrollDanaPensiun = new payrollDanaPensiun();
                    returnPayrollDanaPensiun.setDanaPensiunId(payrollDanaPensiunEntity.getDanaPensiunId());
                    returnPayrollDanaPensiun.setDanaPensiun(payrollDanaPensiunEntity.getDanaPensiun());
                    returnPayrollDanaPensiun.setPersentase(payrollDanaPensiunEntity.getPersentase());
                    returnPayrollDanaPensiun.setCreatedWho(payrollDanaPensiunEntity.getCreatedWho());
                    returnPayrollDanaPensiun.setCreatedDate(payrollDanaPensiunEntity.getCreatedDate());
                    returnPayrollDanaPensiun.setLastUpdate(payrollDanaPensiunEntity.getLastUpdate());

                    returnPayrollDanaPensiun.setAction(payrollDanaPensiunEntity.getAction());
                    returnPayrollDanaPensiun.setFlag(payrollDanaPensiunEntity.getFlag());
                    listOfResult.add(returnPayrollDanaPensiun);
                }
            }
        }
        logger.info("[PayrollDanaPensiunBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollDanaPensiun> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
