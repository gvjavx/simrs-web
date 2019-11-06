package com.neurix.hris.master.payrollMasterInsentif.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollMasterInsentif.bo.PayrollMasterInsentifBo;
import com.neurix.hris.master.payrollMasterInsentif.dao.PayrollMasterInsentifDao;
import com.neurix.hris.master.payrollMasterInsentif.model.ImPayrollMasterInsentifEntity;
import com.neurix.hris.master.payrollMasterInsentif.model.PayrollMasterInsentif;
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
public class PayrollMasterInsentifBoImpl implements PayrollMasterInsentifBo {

    protected static transient Logger logger = Logger.getLogger(PayrollMasterInsentifBoImpl.class);
    private PayrollMasterInsentifDao payrollMasterInsentifDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollMasterInsentifBoImpl.logger = logger;
    }

    public PayrollMasterInsentifDao getPayrollMasterInsentifDao() {
        return payrollMasterInsentifDao;
    }

    public void setPayrollMasterInsentifDao(PayrollMasterInsentifDao payrollMasterInsentifDao) {
        this.payrollMasterInsentifDao = payrollMasterInsentifDao;
    }

    @Override
    public void saveDelete(PayrollMasterInsentif bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollInsentif = bean.getPayrollInsentifId();

            ImPayrollMasterInsentifEntity imPayrollInsentifEntity = null;

            try {
                // Get data from database by ID
                imPayrollInsentifEntity = payrollMasterInsentifDao.getById("payrollInsentifId", payrollInsentif);
            } catch (HibernateException e) {
                logger.error("[PayrollInsentifBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollInsentifEntity != null) {

                // Modify from bean to entity serializable
                imPayrollInsentifEntity.setFlag(bean.getFlag());
                imPayrollInsentifEntity.setAction(bean.getAction());
                imPayrollInsentifEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollInsentifEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollMasterInsentifDao.updateAndSave(imPayrollInsentifEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollInsentifBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollInsentif, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollInsentifBoImpl.saveDelete] Error, not found data PayrollInsentif with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollInsentif with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollInsentifBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollMasterInsentif bean) throws GeneralBOException {
        logger.info("[PayrollInsentifBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String payrollInsentif = bean.getPayrollInsentifId();

            ImPayrollMasterInsentifEntity imPayrollInsentifEntity = null;
            try {
                // Get data from database by ID
                imPayrollInsentifEntity = payrollMasterInsentifDao.getById("payrollInsentifId", payrollInsentif);
                //historyId = payrollMasterInsentifDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollInsentifBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollInsentif by Kode PayrollInsentif, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollInsentifEntity != null) {
                imPayrollInsentifEntity.setPayrollInsentifId(bean.getPayrollInsentifId());
                imPayrollInsentifEntity.setBranchId(bean.getBranchId());
                imPayrollInsentifEntity.setPotonganInsentif(bean.getPotonganInsentif());
                imPayrollInsentifEntity.setSmkStandart(bean.getSmkStandart());

                imPayrollInsentifEntity.setFlag(bean.getFlag());
                imPayrollInsentifEntity.setAction(bean.getAction());
                imPayrollInsentifEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollInsentifEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollMasterInsentifDao.updateAndSave(imPayrollInsentifEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollInsentifBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollInsentif, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollInsentifBoImpl.saveEdit] Error, not found data PayrollInsentif with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollInsentif with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollInsentifBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollMasterInsentif saveAdd(PayrollMasterInsentif bean) throws GeneralBOException {
        logger.info("[PayrollInsentifBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String payrollInsentif;
            try {
                // Generating ID, get from postgre sequence
                payrollInsentif = payrollMasterInsentifDao.getNextPayrollInsentif();
            } catch (HibernateException e) {
                logger.error("[PayrollInsentifBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollMasterInsentif id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollMasterInsentifEntity imPayrollInsentifEntity = new ImPayrollMasterInsentifEntity();

            imPayrollInsentifEntity.setPayrollInsentifId(payrollInsentif);
            imPayrollInsentifEntity.setSmkStandart(bean.getSmkStandart());
            imPayrollInsentifEntity.setPotonganInsentif(bean.getPotonganInsentif());
            imPayrollInsentifEntity.setBranchId(bean.getBranchId());

            imPayrollInsentifEntity.setFlag(bean.getFlag());
            imPayrollInsentifEntity.setAction(bean.getAction());
            imPayrollInsentifEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollInsentifEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollInsentifEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollInsentifEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollMasterInsentifDao.addAndSave(imPayrollInsentifEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollInsentifBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollInsentif, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollInsentifBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollMasterInsentif> getByCriteria(PayrollMasterInsentif searchBean) throws GeneralBOException {
        logger.info("[PayrollInsentifBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollMasterInsentif> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPayrollInsentifId() != null && !"".equalsIgnoreCase(searchBean.getPayrollInsentifId())) {
                hsCriteria.put("payroll_insentif_id", searchBean.getPayrollInsentifId());
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


            List<ImPayrollMasterInsentifEntity> imPayrollInsentifEntity = null;
            try {

                imPayrollInsentifEntity = payrollMasterInsentifDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollInsentifBoImpl.getSearchPayrollInsentifByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollInsentifEntity != null){
                PayrollMasterInsentif returnPayrollInsentif;
                // Looping from dao to object and save in collection
                for(ImPayrollMasterInsentifEntity payrollInsentifEntity : imPayrollInsentifEntity){
                    returnPayrollInsentif = new PayrollMasterInsentif();
                    returnPayrollInsentif.setPayrollInsentifId(payrollInsentifEntity.getPayrollInsentifId());
                    returnPayrollInsentif.setBranchId(payrollInsentifEntity.getBranchId());
                    returnPayrollInsentif.setBranchName(payrollInsentifEntity.getImBranches().getBranchName());
                    returnPayrollInsentif.setPotonganInsentif(payrollInsentifEntity.getPotonganInsentif());
                    returnPayrollInsentif.setSmkStandart(payrollInsentifEntity.getSmkStandart());

                    returnPayrollInsentif.setCreatedWho(payrollInsentifEntity.getCreatedWho());
                    returnPayrollInsentif.setCreatedDate(payrollInsentifEntity.getCreatedDate());
                    returnPayrollInsentif.setLastUpdate(payrollInsentifEntity.getLastUpdate());

                    returnPayrollInsentif.setAction(payrollInsentifEntity.getAction());
                    returnPayrollInsentif.setFlag(payrollInsentifEntity.getFlag());
                    listOfResult.add(returnPayrollInsentif);
                }
            }
        }
        logger.info("[PayrollInsentifBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollMasterInsentif> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
