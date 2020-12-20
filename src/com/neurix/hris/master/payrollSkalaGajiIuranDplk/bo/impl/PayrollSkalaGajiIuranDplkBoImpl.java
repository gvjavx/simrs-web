package com.neurix.hris.master.payrollSkalaGajiIuranDplk.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganDapen.dao.GolonganDapenDao;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.bo.PayrollSkalaGajiIuranDplkBo;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.dao.PayrollSkalaGajiIuranDplkDao;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.model.ImPayrollSkalaGajiIuranDplkEntity;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.model.payrollSkalaGajiIuranDplk;
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
public class PayrollSkalaGajiIuranDplkBoImpl implements PayrollSkalaGajiIuranDplkBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiIuranDplkBoImpl.class);
    private PayrollSkalaGajiIuranDplkDao payrollSkalaGajiIuranDplkDao;
    private GolonganDapenDao golonganDapenDao;

    public GolonganDapenDao getGolonganDapenDao() {
        return golonganDapenDao;
    }

    public void setGolonganDapenDao(GolonganDapenDao golonganDapenDao) {
        this.golonganDapenDao = golonganDapenDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiIuranDplkBoImpl.logger = logger;
    }

    public PayrollSkalaGajiIuranDplkDao getPayrollSkalaGajiIuranDplkDao() {
        return payrollSkalaGajiIuranDplkDao;
    }


    public void setPayrollSkalaGajiIuranDplkDao(PayrollSkalaGajiIuranDplkDao payrollSkalaGajiIuranDplkDao) {
        this.payrollSkalaGajiIuranDplkDao = payrollSkalaGajiIuranDplkDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiIuranDplk bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiIuranDplkId = bean.getSkalaGajiIuranDplkId();

            ImPayrollSkalaGajiIuranDplkEntity imPayrollSkalaGajiIuranDplkEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiIuranDplkEntity = payrollSkalaGajiIuranDplkDao.getById("skalaGajiIuranDplkId", payrollSkalaGajiIuranDplkId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Skala Gaji Iuran Dplk by ID, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiIuranDplkEntity!= null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiIuranDplkEntity.setSkalaGajiIuranDplkId(bean.getSkalaGajiIuranDplkId());
                imPayrollSkalaGajiIuranDplkEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiIuranDplkEntity.setAction(bean.getAction());
                imPayrollSkalaGajiIuranDplkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiIuranDplkEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiIuranDplkDao.updateAndSave(imPayrollSkalaGajiIuranDplkEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiIuranDPlk, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveDelete] Error, not found data PayrollSkalaGajiIuranDplk with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiIuranDplk with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollSkalaGajiIuranDplk bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiIuranDplkId = bean.getSkalaGajiIuranDplkId();

            ImPayrollSkalaGajiIuranDplkEntity imPayrollSkalaGajiIuranDplkEntity = null;
            //ImPayrollSkalaGajiIuranDplkHistoryEntity imPayrollSkalaGajiIuranDplkHistoryEntity = new ImPayrollSkalaGajiIuranDplkHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiIuranDplkEntity = payrollSkalaGajiIuranDplkDao.getById("skalaGajiIuranDplkId", payrollSkalaGajiIuranDplkId);
                //historyId = payrollSkalaGajiIuranDplkDao.getNextPayrollSkalaGajiIuranDplkHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiIuranDplk by ID PayrollSkalaGajiIuranDplk, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiIuranDplkEntity != null) {
                /*imPayrollSkalaGajiIuranDplkHistoryEntity.setId(historyId);
                imPayrollSkalaGajiIuranDplkHistoryEntity.setPayrollSkalaGajiIuranDplkId(imPayrollSkalaGajiIuranDplkEntity.getPayrollSkalaGajiIuranDplkId());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setPayrollSkalaGajiIuranDplkName(imPayrollSkalaGajiIuranDplkEntity.getPayrollSkalaGajiIuranDplkName());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setFlag(imPayrollSkalaGajiIuranDplkEntity.getFlag());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setAction(imPayrollSkalaGajiIuranDplkEntity.getAction());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setLastUpdateWho(imPayrollSkalaGajiIuranDplkEntity.getLastUpdateWho());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setLastUpdate(imPayrollSkalaGajiIuranDplkEntity.getLastUpdate());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setCreatedWho(imPayrollSkalaGajiIuranDplkEntity.getLastUpdateWho());
                imPayrollSkalaGajiIuranDplkHistoryEntity.setCreatedDate(imPayrollSkalaGajiIuranDplkEntity.getLastUpdate());*/

                imPayrollSkalaGajiIuranDplkEntity.setSkalaGajiIuranDplkId(bean.getSkalaGajiIuranDplkId());
                imPayrollSkalaGajiIuranDplkEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiIuranDplkEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiIuranDplkEntity.setAction(bean.getAction());
                imPayrollSkalaGajiIuranDplkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiIuranDplkEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiIuranDplkDao.updateAndSave(imPayrollSkalaGajiIuranDplkEntity);
                    //payrollSkalaGajiIuranDplkDao.addAndSaveHistory(imPayrollSkalaGajiIuranDplkHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiIuranDplk, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveEdit] Error, not found data PayrollSkalaGajiIuranDplk with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiIuranDplk with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiIuranDplk with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiIuranDplk saveAdd(payrollSkalaGajiIuranDplk bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiIuranDplkId;
            try {
                // Generating ID, get from postgre sequence
                payrollSkalaGajiIuranDplkId = payrollSkalaGajiIuranDplkDao.getNextSkalaGajiIuranDplk();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiIuranDplkId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollSkalaGajiIuranDplkEntity imPayrollSkalaGajiIuranDplkEntity = new ImPayrollSkalaGajiIuranDplkEntity();

            imPayrollSkalaGajiIuranDplkEntity.setSkalaGajiIuranDplkId(payrollSkalaGajiIuranDplkId);
            imPayrollSkalaGajiIuranDplkEntity.setGolonganId(bean.getGolonganId());
            imPayrollSkalaGajiIuranDplkEntity.setNilai(bean.getNilai());
            imPayrollSkalaGajiIuranDplkEntity.setFlag(bean.getFlag());
            imPayrollSkalaGajiIuranDplkEntity.setAction(bean.getAction());
            imPayrollSkalaGajiIuranDplkEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollSkalaGajiIuranDplkEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollSkalaGajiIuranDplkEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollSkalaGajiIuranDplkEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollSkalaGajiIuranDplkDao.addAndSave(imPayrollSkalaGajiIuranDplkEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiIuranDplk, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiIuranDplk> getByCriteria(payrollSkalaGajiIuranDplk searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiIuranDplk> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiIuranDplkId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiIuranDplkId())) {
                hsCriteria.put("skala_gaji_iuran_dplk_id", searchBean.getSkalaGajiIuranDplkId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
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


            List<ImPayrollSkalaGajiIuranDplkEntity> imPayrollSkalaGajiIuranDplkEntity= null;
            try {

                imPayrollSkalaGajiIuranDplkEntity= payrollSkalaGajiIuranDplkDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiIuranDplkBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiIuranDplkEntity!= null){
                payrollSkalaGajiIuranDplk returnPayrollSkalaGajiIuranDplk;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiIuranDplkEntity payrollSkalaGajiEntity : imPayrollSkalaGajiIuranDplkEntity){
                    returnPayrollSkalaGajiIuranDplk = new payrollSkalaGajiIuranDplk();
                    returnPayrollSkalaGajiIuranDplk.setSkalaGajiIuranDplkId(payrollSkalaGajiEntity.getSkalaGajiIuranDplkId());
                    returnPayrollSkalaGajiIuranDplk.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    ImGolonganDapenEntity golonganDapenEntity = golonganDapenDao.getById("golonganDapenId",payrollSkalaGajiEntity.getGolonganId());
                    if (golonganDapenEntity!=null){
                        returnPayrollSkalaGajiIuranDplk.setGolonganName(golonganDapenEntity.getGolonganDapenName());
                    }

                    returnPayrollSkalaGajiIuranDplk.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGajiIuranDplk.setStNilai(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getNilai(), "###,###"));

                    returnPayrollSkalaGajiIuranDplk.setCreatedWho(payrollSkalaGajiEntity.getCreatedWho());
                    returnPayrollSkalaGajiIuranDplk.setCreatedDate(payrollSkalaGajiEntity.getCreatedDate());
                    returnPayrollSkalaGajiIuranDplk.setLastUpdate(payrollSkalaGajiEntity.getLastUpdate());

                    returnPayrollSkalaGajiIuranDplk.setAction(payrollSkalaGajiEntity.getAction());
                    returnPayrollSkalaGajiIuranDplk.setFlag(payrollSkalaGajiEntity.getFlag());
                    listOfResult.add(returnPayrollSkalaGajiIuranDplk);
                }
            }
        }
        logger.info("[PayrollSkalaGajiIuranDplkBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollSkalaGajiIuranDplk> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiIuranDplk> getComboPayrollSkalaGajiIuranDplkWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}