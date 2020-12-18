package com.neurix.hris.master.payrollSkalaGajiPensiun.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganDapen.dao.GolonganDapenDao;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGaji.model.PayrollSkalaGaji;
import com.neurix.hris.master.payrollSkalaGajiPensiun.bo.PayrollSkalaGajiPensiunBo;
import com.neurix.hris.master.payrollSkalaGajiPensiun.dao.PayrollSkalaGajiPensiunDao;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.ImPayrollSkalaGajiPensiunEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.payrollSkalaGajiPensiun;
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
public class PayrollSkalaGajiPensiunBoImpl implements PayrollSkalaGajiPensiunBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunBoImpl.class);
    private PayrollSkalaGajiPensiunDao payrollSkalaGajiPensiunDao;
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
        PayrollSkalaGajiPensiunBoImpl.logger = logger;
    }

    public PayrollSkalaGajiPensiunDao getPayrollSkalaGajiPensiunDao() {
        return payrollSkalaGajiPensiunDao;
    }


    public void setPayrollSkalaGajiPensiunDao(PayrollSkalaGajiPensiunDao payrollSkalaGajiPensiunDao) {
        this.payrollSkalaGajiPensiunDao = payrollSkalaGajiPensiunDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDao.getById("skalaGajiPensiunId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setSkalaGajiPensiunId(bean.getSkalaGajiPensiunId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiPensiunDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPensiunId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiPensiunEntity = null;
            //ImPayrollSkalaGajiPensiunHistoryEntity imPayrollSkalaGajiPensiunHistoryEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiPensiunEntity = payrollSkalaGajiPensiunDao.getById("skalaGajiPensiunId", payrollSkalaGajiPensiunId);
                //historyId = payrollSkalaGajiPensiunDao.getNextPayrollSkalaGajiPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiPensiun by Kode PayrollSkalaGajiPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiPensiunEntity != null) {
                /*imPayrollSkalaGajiPensiunHistoryEntity.setId(historyId);
                imPayrollSkalaGajiPensiunHistoryEntity.setPayrollSkalaGajiPensiunId(imPayrollSkalaGajiPensiunEntity.getPayrollSkalaGajiPensiunId());
                imPayrollSkalaGajiPensiunHistoryEntity.setPayrollSkalaGajiPensiunName(imPayrollSkalaGajiPensiunEntity.getPayrollSkalaGajiPensiunName());
                imPayrollSkalaGajiPensiunHistoryEntity.setFlag(imPayrollSkalaGajiPensiunEntity.getFlag());
                imPayrollSkalaGajiPensiunHistoryEntity.setAction(imPayrollSkalaGajiPensiunEntity.getAction());
                imPayrollSkalaGajiPensiunHistoryEntity.setLastUpdateWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());
                imPayrollSkalaGajiPensiunHistoryEntity.setLastUpdate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());
                imPayrollSkalaGajiPensiunHistoryEntity.setCreatedWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());
                imPayrollSkalaGajiPensiunHistoryEntity.setCreatedDate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());*/

                imPayrollSkalaGajiPensiunEntity.setSkalaGajiPensiunId(bean.getSkalaGajiPensiunId());
                imPayrollSkalaGajiPensiunEntity.setGolonganId(bean.getGolonganId());
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiPensiunDao.updateAndSave(imPayrollSkalaGajiPensiunEntity);
                    //payrollSkalaGajiPensiunDao.addAndSaveHistory(imPayrollSkalaGajiPensiunHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiPensiun saveAdd(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiPensiunId;
            try {
                // Generating ID, get from postgre sequence
                payrollSkalaGajiPensiunId = payrollSkalaGajiPensiunDao.getNextSkalaGajiPensiun();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPensiunId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiPensiunEntity = new ImPayrollSkalaGajiPensiunEntity();

            imPayrollSkalaGajiPensiunEntity.setSkalaGajiPensiunId(payrollSkalaGajiPensiunId);
            imPayrollSkalaGajiPensiunEntity.setGolonganId(bean.getGolonganId());
            imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
            imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
            imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
            imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
            imPayrollSkalaGajiPensiunEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollSkalaGajiPensiunEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollSkalaGajiPensiunDao.addAndSave(imPayrollSkalaGajiPensiunEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiPensiun> getByCriteria(payrollSkalaGajiPensiun searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiPensiun> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPensiunId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPensiunId())) {
                hsCriteria.put("skala_gaji_pensiun_id", searchBean.getSkalaGajiPensiunId());
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


            List<ImPayrollSkalaGajiPensiunEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiPensiun returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiPensiunEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiPensiun();
                    returnPayrollSkalaGaji.setSkalaGajiPensiunId(payrollSkalaGajiEntity.getSkalaGajiPensiunId());
                    returnPayrollSkalaGaji.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    ImGolonganDapenEntity golonganDapenEntity = golonganDapenDao.getById("golonganDapenId",payrollSkalaGajiEntity.getGolonganId());
                    if (golonganDapenEntity!=null){
                        returnPayrollSkalaGaji.setGolonganName(golonganDapenEntity.getGolonganDapenName());
                    }

                    returnPayrollSkalaGaji.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGaji.setStNilai(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getNilai(), "###,###"));
                    returnPayrollSkalaGaji.setPoin(payrollSkalaGajiEntity.getPoin());

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
    public List<payrollSkalaGajiPensiun> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiPensiun> getComboPayrollSkalaGajiPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}