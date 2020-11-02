package com.neurix.hris.master.payrollParamBpjs.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollParamBpjs.bo.PayrollParamBpjsBo;
import com.neurix.hris.master.payrollParamBpjs.dao.PayrollParamBpjsDao;
import com.neurix.hris.master.payrollParamBpjs.model.ImHrisPayrollParamBpjsEntity;
import com.neurix.hris.master.payrollParamBpjs.model.ImHrisPayrollParamBpjsHistoryEntity;
import com.neurix.hris.master.payrollParamBpjs.model.PayrollParamBpjs;
//import com.neurix.hris.transaksi.payroll.dao.PayrollParamBpjsDao;
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
public class PayrollParamBpjsBoImpl implements PayrollParamBpjsBo {

    protected static transient Logger logger = Logger.getLogger(PayrollParamBpjsBoImpl.class);
    private PayrollParamBpjsDao payrollParamBpjsDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollParamBpjsBoImpl.logger = logger;
    }

    public PayrollParamBpjsDao getPayrollParamBpjsDao() {
        return payrollParamBpjsDao;
    }


    public void setPayrollParamBpjsDao(PayrollParamBpjsDao payrollParamBpjsDao) {
        this.payrollParamBpjsDao = payrollParamBpjsDao;
    }

    @Override
    public void saveDelete(PayrollParamBpjs bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean != null) {

            String payrollParamBpjs = bean.getPayrollParamBpjsId();

            ImHrisPayrollParamBpjsEntity imPayrollParamBpjsEntity = null;

            try {
                // Get data from database by ID
                imPayrollParamBpjsEntity = payrollParamBpjsDao.getById("idPtkp", payrollParamBpjs);
            } catch (HibernateException e) {
                logger.error("[PayrollParamBpjsBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollParamBpjsEntity != null) {

                // Modify from bean to entity serializable
                imPayrollParamBpjsEntity.setPayrollParamBpjsId(bean.getPayrollParamBpjsId());
                imPayrollParamBpjsEntity.setFlag(bean.getFlag());
                imPayrollParamBpjsEntity.setAction(bean.getAction());
                imPayrollParamBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollParamBpjsEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollParamBpjsDao.updateAndSave(imPayrollParamBpjsEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollParamBpjsBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollParamBpjs, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollParamBpjsBoImpl.saveDelete] Error, not found data PayrollParamBpjs with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollParamBpjs with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollParamBpjsBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollParamBpjs bean) throws GeneralBOException {
        logger.info("[PayrollParamBpjsBoImpl.saveEdit] start process >>>");
        if (bean != null) {
            String historyId = "";
            String payrollParamBpjs = bean.getPayrollParamBpjsId();

            ImHrisPayrollParamBpjsEntity imPayrollParamBpjsEntity = null;
            try {
                // Get data from database by ID
                imPayrollParamBpjsEntity = payrollParamBpjsDao.getById("payrollParamBpjsId", payrollParamBpjs);
                historyId = payrollParamBpjsDao.getNextPayrollParamBpjsHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollParamBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollParamBpjs by Kode PayrollParamBpjs, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollParamBpjsEntity != null) {
                ImHrisPayrollParamBpjsHistoryEntity imPayrollParamBpjsHistoryEntity = new ImHrisPayrollParamBpjsHistoryEntity();

                imPayrollParamBpjsHistoryEntity.setId(historyId);
                imPayrollParamBpjsHistoryEntity.setPayrollParamBpjsId(imPayrollParamBpjsEntity.getPayrollParamBpjsId());
                imPayrollParamBpjsHistoryEntity.setFlagGapok(imPayrollParamBpjsEntity.getFlagGapok());
                imPayrollParamBpjsHistoryEntity.setFlagSankhus(imPayrollParamBpjsEntity.getFlagSankhus());
                imPayrollParamBpjsHistoryEntity.setFlagPeralihanGapok(imPayrollParamBpjsEntity.getFlagPeralihanGapok());
                imPayrollParamBpjsHistoryEntity.setFlagPeralihanSankhus(imPayrollParamBpjsEntity.getFlagPeralihanSankhus());
                imPayrollParamBpjsHistoryEntity.setFlagPeralihanTunjangan(imPayrollParamBpjsEntity.getFlagPeralihanTunjangan());

                imPayrollParamBpjsHistoryEntity.setFlag(imPayrollParamBpjsEntity.getFlag());
                imPayrollParamBpjsHistoryEntity.setAction(imPayrollParamBpjsEntity.getAction());
                imPayrollParamBpjsHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollParamBpjsHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imPayrollParamBpjsHistoryEntity.setCreatedWho(imPayrollParamBpjsEntity.getLastUpdateWho());
                imPayrollParamBpjsHistoryEntity.setCreatedDate(imPayrollParamBpjsEntity.getLastUpdate());


                imPayrollParamBpjsEntity.setFlagGapok(bean.getFlagGapok());
                imPayrollParamBpjsEntity.setFlagSankhus(bean.getFlagSankhus());
                imPayrollParamBpjsEntity.setFlagPeralihanGapok(bean.getFlagPeralihanGapok());
                imPayrollParamBpjsEntity.setFlagPeralihanSankhus(bean.getFlagPeralihanSankhus());
                imPayrollParamBpjsEntity.setFlagPeralihanTunjangan(bean.getFlagPeralihanTunjangan());


                imPayrollParamBpjsEntity.setFlag(bean.getFlag());
                imPayrollParamBpjsEntity.setAction(bean.getAction());
                imPayrollParamBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollParamBpjsEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollParamBpjsDao.updateAndSave(imPayrollParamBpjsEntity);
                    payrollParamBpjsDao.addAndSaveHistory(imPayrollParamBpjsHistoryEntity);
                    //payrollParamBpjsDao.addAndSaveHistory(imPayrollParamBpjsHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollParamBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollParamBpjs, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollParamBpjsBoImpl.saveEdit] Error, not found data PayrollParamBpjs with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollParamBpjs with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollParamBpjsBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollParamBpjs saveAdd(PayrollParamBpjs bean) throws GeneralBOException {
        logger.info("[PayrollParamBpjsBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisPayrollParamBpjsEntity> list = new ArrayList<>();
            try {
                list = payrollParamBpjsDao.checkData(bean.getFlagGapok(), bean.getFlagSankhus());
            } catch (HibernateException e) {
                logger.error("[PayrollParamBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollParamBpjs id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Status Keluarga dan Jumlah Tanggungan sudah ada");
                throw new GeneralBOException("Status Keluarga dan Jumlah Tanggungan sudah ada");
            } else {

                String payrollParamBpjs;
                try {
                    // Generating ID, get from postgre sequence
                    payrollParamBpjs = payrollParamBpjsDao.getNextIdBpjs();
                } catch (HibernateException e) {
                    logger.error("[PayrollParamBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollParamBpjs id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImHrisPayrollParamBpjsEntity imPayrollParamBpjsEntity = new ImHrisPayrollParamBpjsEntity();

                imPayrollParamBpjsEntity.setPayrollParamBpjsId(payrollParamBpjs);
                imPayrollParamBpjsEntity.setFlagGapok(bean.getFlagGapok());
                imPayrollParamBpjsEntity.setFlagSankhus(bean.getFlagSankhus());
                imPayrollParamBpjsEntity.setFlagPeralihanGapok(bean.getFlagPeralihanGapok());
                imPayrollParamBpjsEntity.setFlagPeralihanSankhus(bean.getFlagPeralihanSankhus());
                imPayrollParamBpjsEntity.setFlagPeralihanTunjangan(bean.getFlagPeralihanTunjangan());

                imPayrollParamBpjsEntity.setFlag(bean.getFlag());
                imPayrollParamBpjsEntity.setAction(bean.getAction());
                imPayrollParamBpjsEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollParamBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollParamBpjsEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollParamBpjsEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollParamBpjsDao.addAndSave(imPayrollParamBpjsEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollParamBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollParamBpjs, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[PayrollParamBpjsBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollParamBpjs> getByCriteria(PayrollParamBpjs searchBean) throws GeneralBOException {
        logger.info("[PayrollParamBpjsBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollParamBpjs> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPayrollParamBpjsId() != null && !"".equalsIgnoreCase(searchBean.getPayrollParamBpjsId())) {
                hsCriteria.put("payroll_param_bpjs_id", searchBean.getPayrollParamBpjsId());
            }
            if (searchBean.getFlagGapok() != null && !"".equalsIgnoreCase(searchBean.getFlagGapok())) {
                hsCriteria.put("flag_gapok", searchBean.getFlagGapok());
            }
            if (searchBean.getFlagSankhus() != null && !"".equalsIgnoreCase(searchBean.getFlagSankhus())) {
                hsCriteria.put("flag_sankhus", searchBean.getFlagSankhus());
            }
            if (searchBean.getFlagPeralihanGapok() != null && !"".equalsIgnoreCase(searchBean.getFlagPeralihanGapok())) {
                hsCriteria.put("flag_peralihan_gapok", searchBean.getFlagPeralihanGapok());
            }

            if (searchBean.getFlagPeralihanSankhus() != null && !"".equalsIgnoreCase(searchBean.getFlagPeralihanSankhus())) {
                hsCriteria.put("flag_peralihan_sankhus", searchBean.getFlagPeralihanSankhus());
            }
            if (searchBean.getFlagPeralihanTunjangan() != null && !"".equalsIgnoreCase(searchBean.getFlagPeralihanTunjangan())) {
                hsCriteria.put("flag_peralihan_tunjangan", searchBean.getFlagPeralihanTunjangan());
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


            List<ImHrisPayrollParamBpjsEntity> imPayrollParamBpjsEntity = null;
            try {

                imPayrollParamBpjsEntity = payrollParamBpjsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollParamBpjsBoImpl.getSearchPayrollParamBpjsByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imPayrollParamBpjsEntity != null) {
                PayrollParamBpjs returnPayrollParamBpjs;

                // Looping from dao to object and save in collection
                for (ImHrisPayrollParamBpjsEntity payrollParamBpjsEntity : imPayrollParamBpjsEntity) {
                    returnPayrollParamBpjs = new PayrollParamBpjs();
                    returnPayrollParamBpjs.setPayrollParamBpjsId(payrollParamBpjsEntity.getPayrollParamBpjsId());
                    returnPayrollParamBpjs.setFlagGapok(payrollParamBpjsEntity.getFlagGapok());
                    returnPayrollParamBpjs.setFlagSankhus(payrollParamBpjsEntity.getFlagSankhus());
                    returnPayrollParamBpjs.setFlagPeralihanGapok(payrollParamBpjsEntity.getFlagPeralihanGapok());
                    returnPayrollParamBpjs.setFlagPeralihanSankhus(payrollParamBpjsEntity.getFlagPeralihanSankhus());
                    returnPayrollParamBpjs.setFlagPeralihanTunjangan(payrollParamBpjsEntity.getFlagPeralihanTunjangan());

                    returnPayrollParamBpjs.setCreatedWho(payrollParamBpjsEntity.getCreatedWho());
                    returnPayrollParamBpjs.setCreatedDate(payrollParamBpjsEntity.getCreatedDate());
                    returnPayrollParamBpjs.setLastUpdate(payrollParamBpjsEntity.getLastUpdate());
                    returnPayrollParamBpjs.setAction(payrollParamBpjsEntity.getAction());
                    returnPayrollParamBpjs.setFlag(payrollParamBpjsEntity.getFlag());
                    listOfResult.add(returnPayrollParamBpjs);
                }
            }
        }
        logger.info("[PayrollParamBpjsBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollParamBpjs> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
