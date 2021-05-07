package com.neurix.hris.master.payrollTunjanganUmk.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollTunjanganUmk.bo.PayrollTunjanganUmkBo;
import com.neurix.hris.master.payrollTunjanganUmk.dao.PayrollTunjanganUmkDao;
import com.neurix.hris.master.payrollTunjanganUmk.model.ImPayrollTunjanganUmkEntity;
import com.neurix.hris.master.payrollTunjanganUmk.model.payrollTunjanganUmk;
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
public class PayrollTunjanganUmkBoImpl implements PayrollTunjanganUmkBo {

    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganUmkBoImpl.class);
    private PayrollTunjanganUmkDao payrollTunjanganUmkDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganUmkBoImpl.logger = logger;
    }

    public PayrollTunjanganUmkDao getPayrollTunjanganUmkDao() {
        return payrollTunjanganUmkDao;
    }


    public void setPayrollTunjanganUmkDao(PayrollTunjanganUmkDao payrollTunjanganUmkDao) {
        this.payrollTunjanganUmkDao = payrollTunjanganUmkDao;
    }

    @Override
    public void saveDelete(payrollTunjanganUmk bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollTunjanganUmkId = bean.getUmkId();

            ImPayrollTunjanganUmkEntity imPayrollTunjanganUmkEntity = null;

            try {
                // Get data from database by ID
                imPayrollTunjanganUmkEntity = payrollTunjanganUmkDao.getById("umkId", payrollTunjanganUmkId);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganUmkBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganUmkEntity != null) {

                // Modify from bean to entity serializable
                imPayrollTunjanganUmkEntity.setUmkId(bean.getUmkId());
                imPayrollTunjanganUmkEntity.setFlag(bean.getFlag());
                imPayrollTunjanganUmkEntity.setAction(bean.getAction());
                imPayrollTunjanganUmkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganUmkEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollTunjanganUmkDao.updateAndSave(imPayrollTunjanganUmkEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganUmkBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganUmk, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollTunjanganUmkBoImpl.saveDelete] Error, not found data PayrollTunjanganUmk with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganUmk with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollTunjanganUmkBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollTunjanganUmk bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganUmkBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollTunjanganUmkId = bean.getUmkId();

            ImPayrollTunjanganUmkEntity imPayrollTunjanganUmkEntity = null;
            //ImPayrollTunjanganUmkHistoryEntity imPayrollTunjanganUmkHistoryEntity = new ImPayrollTunjanganUmkHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganUmkEntity = payrollTunjanganUmkDao.getById("umkId", payrollTunjanganUmkId);
                //historyId = payrollTunjanganUmkDao.getNextPayrollTunjanganUmkHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganUmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollTunjanganUmk by Kode PayrollTunjanganUmk, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganUmkEntity != null) {
                /*imPayrollTunjanganUmkHistoryEntity.setId(historyId);
                imPayrollTunjanganUmkHistoryEntity.setPayrollTunjanganUmkId(imPayrollTunjanganUmkEntity.getPayrollTunjanganUmkId());
                imPayrollTunjanganUmkHistoryEntity.setPayrollTunjanganUmkName(imPayrollTunjanganUmkEntity.getPayrollTunjanganUmkName());
                imPayrollTunjanganUmkHistoryEntity.setFlag(imPayrollTunjanganUmkEntity.getFlag());
                imPayrollTunjanganUmkHistoryEntity.setAction(imPayrollTunjanganUmkEntity.getAction());
                imPayrollTunjanganUmkHistoryEntity.setLastUpdateWho(imPayrollTunjanganUmkEntity.getLastUpdateWho());
                imPayrollTunjanganUmkHistoryEntity.setLastUpdate(imPayrollTunjanganUmkEntity.getLastUpdate());
                imPayrollTunjanganUmkHistoryEntity.setCreatedWho(imPayrollTunjanganUmkEntity.getLastUpdateWho());
                imPayrollTunjanganUmkHistoryEntity.setCreatedDate(imPayrollTunjanganUmkEntity.getLastUpdate());*/

                imPayrollTunjanganUmkEntity.setUmkId(bean.getUmkId());
                imPayrollTunjanganUmkEntity.setBranchId(bean.getBranchId());
                imPayrollTunjanganUmkEntity.setGolonganId(bean.getGolonganId());

                imPayrollTunjanganUmkEntity.setNilai(bean.getNilai());
                imPayrollTunjanganUmkEntity.setTahun(bean.getTahun());
                imPayrollTunjanganUmkEntity.setFlag(bean.getFlag());
                imPayrollTunjanganUmkEntity.setAction(bean.getAction());
                imPayrollTunjanganUmkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganUmkEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollTunjanganUmkDao.updateAndSave(imPayrollTunjanganUmkEntity);
                    //payrollTunjanganUmkDao.addAndSaveHistory(imPayrollTunjanganUmkHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganUmkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganUmk, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollTunjanganUmkBoImpl.saveEdit] Error, not found data PayrollTunjanganUmk with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganUmk with request id, please check again your data ...");
//                condition = "Error, not found data PayrollTunjanganUmk with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollTunjanganUmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollTunjanganUmk saveAdd(payrollTunjanganUmk bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganUmkBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollTunjanganUmkId;
            try {
                // Generating ID, get from postgre sequence
                payrollTunjanganUmkId = payrollTunjanganUmkDao.getNextUmk();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganUmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollTunjanganUmkId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollTunjanganUmkEntity imPayrollTunjanganUmkEntity = new ImPayrollTunjanganUmkEntity();

            imPayrollTunjanganUmkEntity.setUmkId(payrollTunjanganUmkId);
            imPayrollTunjanganUmkEntity.setGolonganId(bean.getGolonganId());
            imPayrollTunjanganUmkEntity.setBranchId(bean.getBranchId());

            imPayrollTunjanganUmkEntity.setNilai(bean.getNilai());
            imPayrollTunjanganUmkEntity.setTahun(bean.getTahun());
            imPayrollTunjanganUmkEntity.setFlag(bean.getFlag());
            imPayrollTunjanganUmkEntity.setAction(bean.getAction());
            imPayrollTunjanganUmkEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollTunjanganUmkEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollTunjanganUmkEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollTunjanganUmkEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollTunjanganUmkDao.addAndSave(imPayrollTunjanganUmkEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganUmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollTunjanganUmk, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollTunjanganUmkBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollTunjanganUmk> getByCriteria(payrollTunjanganUmk searchBean) throws GeneralBOException {
        logger.info("[PayrollTunjanganUmkBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollTunjanganUmk> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getUmkId() != null && !"".equalsIgnoreCase(searchBean.getUmkId())) {
                hsCriteria.put("tunjangan_umk_id", searchBean.getUmkId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }
            if (searchBean.getTahun() != null && !"".equalsIgnoreCase(searchBean.getTahun())) {
                hsCriteria.put("tahun", searchBean.getTahun());
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


            List<ImPayrollTunjanganUmkEntity> imPayrollTunjanganUmkEntity = null;
            try {

                imPayrollTunjanganUmkEntity = payrollTunjanganUmkDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganUmkBoImpl.getSearchPayrollTunjanganUmkByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganUmkEntity != null){
                payrollTunjanganUmk returnPayrollTunjanganUmk;
                // Looping from dao to object and save in collection
                for(ImPayrollTunjanganUmkEntity payrollTunjanganUmkEntity : imPayrollTunjanganUmkEntity){
                    returnPayrollTunjanganUmk = new payrollTunjanganUmk();
                    returnPayrollTunjanganUmk.setUmkId(payrollTunjanganUmkEntity.getUmkId());
                    returnPayrollTunjanganUmk.setBranchId(payrollTunjanganUmkEntity.getBranchId());
                    returnPayrollTunjanganUmk.setGolonganId(payrollTunjanganUmkEntity.getGolonganId());
                    if(payrollTunjanganUmkEntity.getGolonganId() != null){
                        returnPayrollTunjanganUmk.setGolonganName(payrollTunjanganUmkEntity.getImGolonganEntity().getGolonganName());
                    }
                    if(payrollTunjanganUmkEntity.getBranchId() != null){
                        returnPayrollTunjanganUmk.setBranchName(payrollTunjanganUmkEntity.getImBranches().getBranchName());
                    }
                    returnPayrollTunjanganUmk.setTahun(payrollTunjanganUmkEntity.getTahun());
                    returnPayrollTunjanganUmk.setNilai(payrollTunjanganUmkEntity.getNilai());
                    returnPayrollTunjanganUmk.setCreatedWho(payrollTunjanganUmkEntity.getCreatedWho());
                    returnPayrollTunjanganUmk.setCreatedDate(payrollTunjanganUmkEntity.getCreatedDate());
                    returnPayrollTunjanganUmk.setLastUpdate(payrollTunjanganUmkEntity.getLastUpdate());

                    returnPayrollTunjanganUmk.setAction(payrollTunjanganUmkEntity.getAction());
                    returnPayrollTunjanganUmk.setFlag(payrollTunjanganUmkEntity.getFlag());
                    listOfResult.add(returnPayrollTunjanganUmk);
                }
            }
        }
        logger.info("[PayrollTunjanganUmkBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollTunjanganUmk> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
