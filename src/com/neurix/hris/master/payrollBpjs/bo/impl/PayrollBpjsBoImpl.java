package com.neurix.hris.master.payrollBpjs.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollBpjs.bo.PayrollBpjsBo;
import com.neurix.hris.transaksi.payroll.dao.PayrollBpjsDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollBpjs;
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
public class PayrollBpjsBoImpl implements PayrollBpjsBo {

    protected static transient Logger logger = Logger.getLogger(PayrollBpjsBoImpl.class);
    private PayrollBpjsDao payrollBpjsDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollBpjsBoImpl.logger = logger;
    }

    public PayrollBpjsDao getPayrollBpjsDao() {
        return payrollBpjsDao;
    }

    public void setPayrollBpjsDao(PayrollBpjsDao payrollBpjsDao) {
        this.payrollBpjsDao = payrollBpjsDao;
    }

    @Override
    public void saveDelete(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getBpjsId();

            ImPayrollBpjsEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollBpjsDao.getById("bpjsId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setBpjsId(bean.getBpjsId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollBpjsDao.updateAndSave(imPayrollSkalaGajiEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[PayrollBpjsBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollBpjsId = bean.getBpjsId();

            ImPayrollBpjsEntity imPayrollBpjsEntity = null;
            //ImPayrollBpjsHistoryEntity imPayrollBpjsHistoryEntity = new ImPayrollBpjsHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollBpjsEntity = payrollBpjsDao.getById("bpjsId", payrollBpjsId);
                //historyId = payrollBpjsDao.getNextPayrollBpjsHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollBpjs by Kode PayrollBpjs, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollBpjsEntity != null) {
                /*imPayrollBpjsHistoryEntity.setId(historyId);
                imPayrollBpjsHistoryEntity.setPayrollBpjsId(imPayrollBpjsEntity.getPayrollBpjsId());
                imPayrollBpjsHistoryEntity.setPayrollBpjsName(imPayrollBpjsEntity.getPayrollBpjsName());
                imPayrollBpjsHistoryEntity.setFlag(imPayrollBpjsEntity.getFlag());
                imPayrollBpjsHistoryEntity.setAction(imPayrollBpjsEntity.getAction());
                imPayrollBpjsHistoryEntity.setLastUpdateWho(imPayrollBpjsEntity.getLastUpdateWho());
                imPayrollBpjsHistoryEntity.setLastUpdate(imPayrollBpjsEntity.getLastUpdate());
                imPayrollBpjsHistoryEntity.setCreatedWho(imPayrollBpjsEntity.getLastUpdateWho());
                imPayrollBpjsHistoryEntity.setCreatedDate(imPayrollBpjsEntity.getLastUpdate());*/

                imPayrollBpjsEntity.setBpjsId(bean.getBpjsId());
                imPayrollBpjsEntity.setFlag(bean.getFlag());
                imPayrollBpjsEntity.setAction(bean.getAction());
                imPayrollBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollBpjsEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollBpjsDao.updateAndSave(imPayrollBpjsEntity);
                    //payrollBpjsDao.addAndSaveHistory(imPayrollBpjsHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollBpjs, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollBpjsBoImpl.saveEdit] Error, not found data PayrollBpjs with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollBpjs with request id, please check again your data ...");
//                condition = "Error, not found data PayrollBpjs with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollBpjsBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollBpjs saveAdd(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[PayrollBpjsBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollBpjsId;
            try {
                // Generating ID, get from postgre sequence
                payrollBpjsId = payrollBpjsDao.getNextBpjs();
            } catch (HibernateException e) {
                logger.error("[PayrollBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollBpjsId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollBpjsEntity imPayrollBpjsEntity = new ImPayrollBpjsEntity();

            imPayrollBpjsEntity.setBpjsId(payrollBpjsId);
            imPayrollBpjsEntity.setFlag(bean.getFlag());
            imPayrollBpjsEntity.setAction(bean.getAction());
            imPayrollBpjsEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollBpjsEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollBpjsEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollBpjsDao.addAndSave(imPayrollBpjsEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollBpjs, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollBpjsBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollBpjs> getByCriteria(PayrollBpjs searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollBpjs> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBpjsId() != null && !"".equalsIgnoreCase(searchBean.getBpjsId())) {
                hsCriteria.put("bpjs_id", searchBean.getBpjsId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
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


            List<ImPayrollBpjsEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollBpjsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                PayrollBpjs returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollBpjsEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new PayrollBpjs();
                    returnPayrollSkalaGaji.setBpjsId(payrollSkalaGajiEntity.getBpjsId());
                    returnPayrollSkalaGaji.setBranchId(payrollSkalaGajiEntity.getBranchId());
                    returnPayrollSkalaGaji.setBranchName(payrollSkalaGajiEntity.getBranchName());


                    returnPayrollSkalaGaji.setMinBpjsKs(payrollSkalaGajiEntity.getMinBpjsKs());
                    returnPayrollSkalaGaji.setMaxBpjsKs(payrollSkalaGajiEntity.getMaxBpjsKs());
                    returnPayrollSkalaGaji.setMinBpjsTk(payrollSkalaGajiEntity.getMinBpjsTk());
                    returnPayrollSkalaGaji.setMaxBpjsTk(payrollSkalaGajiEntity.getMaxBpjsTk());

                    returnPayrollSkalaGaji.setIuranBpjsKsKaryPersen(payrollSkalaGajiEntity.getIuranBpjsKsKaryPersen());
                    returnPayrollSkalaGaji.setIuranBpjsKsPersPersen(payrollSkalaGajiEntity.getIuranBpjsKsPersPersen());
                    returnPayrollSkalaGaji.setIuranBpjsTkKaryPersen(payrollSkalaGajiEntity.getIuranBpjsTkKaryPersen());
                    returnPayrollSkalaGaji.setIuranBpjsTkPersPersen(payrollSkalaGajiEntity.getIuranBpjsTkPersPersen());

                    returnPayrollSkalaGaji.setCreatedWho(payrollSkalaGajiEntity.getCreatedWho());
                    returnPayrollSkalaGaji.setCreatedDate(payrollSkalaGajiEntity.getCreatedDate());
                    returnPayrollSkalaGaji.setLastUpdate(payrollSkalaGajiEntity.getLastUpdate());

                    returnPayrollSkalaGaji.setAction(payrollSkalaGajiEntity.getAction());
                    returnPayrollSkalaGaji.setFlag(payrollSkalaGajiEntity.getFlag());
                    listOfResult.add(returnPayrollSkalaGaji);
                }
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollBpjs> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PayrollBpjs> getComboPayrollBpjsWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}