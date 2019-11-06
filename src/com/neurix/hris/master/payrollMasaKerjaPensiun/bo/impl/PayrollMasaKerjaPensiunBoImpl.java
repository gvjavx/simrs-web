package com.neurix.hris.master.payrollMasaKerjaPensiun.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollMasaKerjaPensiun.bo.PayrollMasaKerjaPensiunBo;
import com.neurix.hris.master.payrollMasaKerjaPensiun.dao.PayrollMasaKerjaPensiunDao;
import com.neurix.hris.master.payrollMasaKerjaPensiun.model.PayrollMasaKerjaPensiun;
import com.neurix.hris.master.payrollMasaKerjaPensiun.model.ImPayrollMasaKerjaPensiunEntity;
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
public class PayrollMasaKerjaPensiunBoImpl implements PayrollMasaKerjaPensiunBo {

    protected static transient Logger logger = Logger.getLogger(PayrollMasaKerjaPensiunBoImpl.class);
    private PayrollMasaKerjaPensiunDao payrollMasaKerjaPensiunDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollMasaKerjaPensiunBoImpl.logger = logger;
    }

    public PayrollMasaKerjaPensiunDao getMasaKerjaPensiunDao() {
        return payrollMasaKerjaPensiunDao;
    }

    public PayrollMasaKerjaPensiunDao getPayrollMasaKerjaPensiunDao() {
        return payrollMasaKerjaPensiunDao;
    }

    public void setPayrollMasaKerjaPensiunDao(PayrollMasaKerjaPensiunDao payrollMasaKerjaPensiunDao) {
        this.payrollMasaKerjaPensiunDao = payrollMasaKerjaPensiunDao;
    }

    @Override
    public void saveDelete(PayrollMasaKerjaPensiun bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String masaKerjaPensiunId = bean.getMasaKerjaPensiunId();

            ImPayrollMasaKerjaPensiunEntity imMasaKerjaPensiunEntity = null;

            try {
                // Get data from database by ID
                imMasaKerjaPensiunEntity = payrollMasaKerjaPensiunDao.getById("masaKerjaPensiunId", masaKerjaPensiunId);
            } catch (HibernateException e) {
                logger.error("[MasaKerjaPensiunBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imMasaKerjaPensiunEntity != null) {
                // Modify from bean to entity serializable
                imMasaKerjaPensiunEntity.setMasaKerjaPensiunId(bean.getMasaKerjaPensiunId());
                imMasaKerjaPensiunEntity.setFlag(bean.getFlag());
                imMasaKerjaPensiunEntity.setAction(bean.getAction());
                imMasaKerjaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMasaKerjaPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollMasaKerjaPensiunDao.updateAndSave(imMasaKerjaPensiunEntity);
                } catch (HibernateException e) {
                    logger.error("[MasaKerjaPensiunBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MasaKerjaPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MasaKerjaPensiunBoImpl.saveDelete] Error, not found data MasaKerjaPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MasaKerjaPensiun with request id, please check again your data ...");

            }
        }
        logger.info("[MasaKerjaPensiunBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollMasaKerjaPensiun bean) throws GeneralBOException {
        logger.info("[MasaKerjaPensiunBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String masaKerjaPensiunId = bean.getMasaKerjaPensiunId();

            ImPayrollMasaKerjaPensiunEntity imMasaKerjaPensiunEntity = null;
            try {
                // Get data from database by ID
                imMasaKerjaPensiunEntity = payrollMasaKerjaPensiunDao.getById("masaKerjaPensiunId", masaKerjaPensiunId);
                //historyId = payrollMasaKerjaPensiunDao.getNextMasaKerjaPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[MasaKerjaPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data MasaKerjaPensiun by Kode MasaKerjaPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imMasaKerjaPensiunEntity != null) {
                /*imMasaKerjaPensiunHistoryEntity.setId(historyId);
                imMasaKerjaPensiunHistoryEntity.setMasaKerjaPensiunId(imMasaKerjaPensiunEntity.getMasaKerjaPensiunId());
                imMasaKerjaPensiunHistoryEntity.setMasaKerjaPensiunName(imMasaKerjaPensiunEntity.getMasaKerjaPensiunName());
                imMasaKerjaPensiunHistoryEntity.setFlag(imMasaKerjaPensiunEntity.getFlag());
                imMasaKerjaPensiunHistoryEntity.setAction(imMasaKerjaPensiunEntity.getAction());
                imMasaKerjaPensiunHistoryEntity.setLastUpdateWho(imMasaKerjaPensiunEntity.getLastUpdateWho());
                imMasaKerjaPensiunHistoryEntity.setLastUpdate(imMasaKerjaPensiunEntity.getLastUpdate());
                imMasaKerjaPensiunHistoryEntity.setCreatedWho(imMasaKerjaPensiunEntity.getLastUpdateWho());
                imMasaKerjaPensiunHistoryEntity.setCreatedDate(imMasaKerjaPensiunEntity.getLastUpdate());*/

                imMasaKerjaPensiunEntity.setMasaKerjaPensiunId(masaKerjaPensiunId);
                imMasaKerjaPensiunEntity.setTahunDari(bean.getTahunDari());
                imMasaKerjaPensiunEntity.setTahunSampai(bean.getTahunSampai());
                imMasaKerjaPensiunEntity.setFaktorPensiun(bean.getFaktorKali());
                imMasaKerjaPensiunEntity.setFaktorPenghargaan(bean.getFaktorKali2());

                imMasaKerjaPensiunEntity.setFlag(bean.getFlag());
                imMasaKerjaPensiunEntity.setAction(bean.getAction());
                imMasaKerjaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMasaKerjaPensiunEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollMasaKerjaPensiunDao.updateAndSave(imMasaKerjaPensiunEntity);
                    //payrollMasaKerjaPensiunDao.addAndSaveHistory(imMasaKerjaPensiunHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[MasaKerjaPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MasaKerjaPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MasaKerjaPensiunBoImpl.saveEdit] Error, not found data MasaKerjaPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MasaKerjaPensiun with request id, please check again your data ...");
//                condition = "Error, not found data MasaKerjaPensiun with request id, please check again your data ...";
            }
        }
        logger.info("[MasaKerjaPensiunBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollMasaKerjaPensiun saveAdd(PayrollMasaKerjaPensiun bean) throws GeneralBOException {
        logger.info("[MasaKerjaPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String masaKerjaPensiunId;
            try {
                // Generating ID, get from postgre sequence
                masaKerjaPensiunId = payrollMasaKerjaPensiunDao.getNextMasaKerjaPensiunId();
            } catch (HibernateException e) {
                logger.error("[MasaKerjaPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence masaKerjaPensiunId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollMasaKerjaPensiunEntity imMasaKerjaPensiunEntity = new ImPayrollMasaKerjaPensiunEntity();

            imMasaKerjaPensiunEntity.setMasaKerjaPensiunId(masaKerjaPensiunId);
            imMasaKerjaPensiunEntity.setTahunDari(bean.getTahunDari());
            imMasaKerjaPensiunEntity.setTahunSampai(bean.getTahunSampai());
            imMasaKerjaPensiunEntity.setFaktorPensiun(bean.getFaktorKali());
            imMasaKerjaPensiunEntity.setFaktorPenghargaan(bean.getFaktorKali2());

            imMasaKerjaPensiunEntity.setFlag(bean.getFlag());
            imMasaKerjaPensiunEntity.setAction(bean.getAction());
            imMasaKerjaPensiunEntity.setCreatedWho(bean.getCreatedWho());
            imMasaKerjaPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imMasaKerjaPensiunEntity.setCreatedDate(bean.getCreatedDate());
            imMasaKerjaPensiunEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollMasaKerjaPensiunDao.addAndSave(imMasaKerjaPensiunEntity);
            } catch (HibernateException e) {
                logger.error("[MasaKerjaPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data MasaKerjaPensiun, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MasaKerjaPensiunBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollMasaKerjaPensiun> getByCriteria(PayrollMasaKerjaPensiun searchBean) throws GeneralBOException {
        logger.info("[payrollMasaKerjaPensiunBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollMasaKerjaPensiun> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMasaKerjaPensiunId() != null && !"".equalsIgnoreCase(searchBean.getMasaKerjaPensiunId())) {
                hsCriteria.put("masaKerja_id", searchBean.getMasaKerjaPensiunId());
            }
            if (searchBean.getTahunDari() != 0 ) {
                hsCriteria.put("tahun_dari", searchBean.getTahunDari());
            }
            if (searchBean.getTahunSampai() != 0 ) {
                hsCriteria.put("tahun_sampai", searchBean.getTahunSampai());
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


            List<ImPayrollMasaKerjaPensiunEntity> imPayrollMasaKerjaPensiunEntities = null;
            try {

                imPayrollMasaKerjaPensiunEntities = payrollMasaKerjaPensiunDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[payrollMasaKerjaPensiunBoImpl.getSearchpayrollMasaKerjaPensiunByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollMasaKerjaPensiunEntities!= null){
                PayrollMasaKerjaPensiun returnpayrollMasaKerjaPensiun;
                // Looping from dao to object and save in collection
                for(ImPayrollMasaKerjaPensiunEntity payrollMasaKerjaPensiunEntity : imPayrollMasaKerjaPensiunEntities){
                    returnpayrollMasaKerjaPensiun = new PayrollMasaKerjaPensiun();
                    returnpayrollMasaKerjaPensiun.setMasaKerjaPensiunId(payrollMasaKerjaPensiunEntity.getMasaKerjaPensiunId());
                    returnpayrollMasaKerjaPensiun.setTahunDari(payrollMasaKerjaPensiunEntity.getTahunDari());
                    returnpayrollMasaKerjaPensiun.setTahunSampai(payrollMasaKerjaPensiunEntity.getTahunSampai());
                    returnpayrollMasaKerjaPensiun.setFaktorKali(payrollMasaKerjaPensiunEntity.getFaktorPensiun());
                    returnpayrollMasaKerjaPensiun.setFaktorKali2(payrollMasaKerjaPensiunEntity.getFaktorPenghargaan());

                    returnpayrollMasaKerjaPensiun.setCreatedWho(payrollMasaKerjaPensiunEntity.getCreatedWho());
                    returnpayrollMasaKerjaPensiun.setCreatedDate(payrollMasaKerjaPensiunEntity.getCreatedDate());
                    returnpayrollMasaKerjaPensiun.setLastUpdate(payrollMasaKerjaPensiunEntity.getLastUpdate());

                    returnpayrollMasaKerjaPensiun.setAction(payrollMasaKerjaPensiunEntity.getAction());
                    returnpayrollMasaKerjaPensiun.setFlag(payrollMasaKerjaPensiunEntity.getFlag());
                    listOfResult.add(returnpayrollMasaKerjaPensiun);
                }
            }
        }
        logger.info("[payrollMasaKerjaPensiunBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollMasaKerjaPensiun> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PayrollMasaKerjaPensiun> getComboMasaKerjaPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}
