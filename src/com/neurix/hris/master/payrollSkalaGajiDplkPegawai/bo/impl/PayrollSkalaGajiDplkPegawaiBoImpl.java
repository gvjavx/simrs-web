package com.neurix.hris.master.payrollSkalaGajiDplkPegawai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganDapen.dao.GolonganDapenDao;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.bo.PayrollSkalaGajiDplkPegawaiBo;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.dao.PayrollSkalaGajiDplkPegawaiDao;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.ImPayrollSkalaGajiDplkPegawaiEntity;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.ImPayrollSkalaGajiDplkPegawaiHistoryEntity;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.payrollSkalaGajiDplkPegawai;
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
public class PayrollSkalaGajiDplkPegawaiBoImpl implements PayrollSkalaGajiDplkPegawaiBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiDplkPegawaiBoImpl.class);
    private PayrollSkalaGajiDplkPegawaiDao payrollSkalaGajiDplkPegawaiDao;
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
        PayrollSkalaGajiDplkPegawaiBoImpl.logger = logger;
    }

    public PayrollSkalaGajiDplkPegawaiDao getPayrollSkalaGajiDplkPegawaiDao() {
        return payrollSkalaGajiDplkPegawaiDao;
    }


    public void setPayrollSkalaGajiDplkPegawaiDao(PayrollSkalaGajiDplkPegawaiDao payrollSkalaGajiDplkPegawaiDao) {
        this.payrollSkalaGajiDplkPegawaiDao = payrollSkalaGajiDplkPegawaiDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiDplkPegawaiId = bean.getSkalaGajiPensiunDplkPegawaiId();

            ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiDplkPegawaiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiDplkPegawaiEntity = payrollSkalaGajiDplkPegawaiDao.getById("skalaGajiPensiunDplkPegawaiId", payrollSkalaGajiDplkPegawaiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Skala Gaji Pensiun Dplk Pegawai by ID, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiDplkPegawaiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiDplkPegawaiEntity.setSkalaGajiPensiunDplkPegawaiId(bean.getSkalaGajiPensiunDplkPegawaiId());
                imPayrollSkalaGajiDplkPegawaiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiDplkPegawaiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiDplkPegawaiDao.updateAndSave(imPayrollSkalaGajiDplkPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiDplkPegawai, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveDelete] Error, not found data PayrollSkalaGajiDplkPegawai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiDplkPegawai with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String historyId = "";
            String payrollSkalaGajiDplkPegawaiId = bean.getSkalaGajiPensiunDplkPegawaiId();

            ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiDplkPegawaiEntity = null;
            ImPayrollSkalaGajiDplkPegawaiHistoryEntity imPayrollSkalaGajiDplkPegawaiHistoryEntity = new ImPayrollSkalaGajiDplkPegawaiHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiDplkPegawaiEntity = payrollSkalaGajiDplkPegawaiDao.getById("skalaGajiPensiunDplkPegawaiId", payrollSkalaGajiDplkPegawaiId);
                historyId = payrollSkalaGajiDplkPegawaiDao.getNextSkalaGajiDplkPegawaiHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiDplkPegawai by ID PayrollSkalaGajiDplkPegawai, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiDplkPegawaiEntity != null) {
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setSkalaGajiPensiunDplkPegawaiHistoryId(historyId);
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setSkalaGajiPensiunDplkPegawaiId(imPayrollSkalaGajiDplkPegawaiEntity.getSkalaGajiPensiunDplkPegawaiId());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setGolonganId(imPayrollSkalaGajiDplkPegawaiEntity.getGolonganId());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setNilai(imPayrollSkalaGajiDplkPegawaiEntity.getNilai());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setFlag(imPayrollSkalaGajiDplkPegawaiEntity.getFlag());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setAction(imPayrollSkalaGajiDplkPegawaiEntity.getAction());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setLastUpdateWho(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setLastUpdate(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdate());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setCreatedWho(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiHistoryEntity.setCreatedDate(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdate());

                imPayrollSkalaGajiDplkPegawaiEntity.setSkalaGajiPensiunDplkPegawaiId(bean.getSkalaGajiPensiunDplkPegawaiId());
                imPayrollSkalaGajiDplkPegawaiEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiDplkPegawaiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiDplkPegawaiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiDplkPegawaiDao.updateAndSave(imPayrollSkalaGajiDplkPegawaiEntity);
                    //payrollSkalaGajiDplkPegawaiDao.addAndSaveHistory(imPayrollSkalaGajiDplkPegawaiHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiDplkPegawai, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, not found data PayrollSkalaGajiDplkPegawai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiDplkPegawai with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiDplkPegawai with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiDplkPegawai saveAdd(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiDplkPegawaiId;
            try {
                // Generating ID, get from postgre sequence
                payrollSkalaGajiDplkPegawaiId = payrollSkalaGajiDplkPegawaiDao.getNextSkalaGajiDplkPegawaiId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiDplkPegawaiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiDplkPegawaiEntity = new ImPayrollSkalaGajiDplkPegawaiEntity();

            imPayrollSkalaGajiDplkPegawaiEntity.setSkalaGajiPensiunDplkPegawaiId(payrollSkalaGajiDplkPegawaiId);
            imPayrollSkalaGajiDplkPegawaiEntity.setGolonganId(bean.getGolonganId());
            imPayrollSkalaGajiDplkPegawaiEntity.setNilai(bean.getNilai());
            imPayrollSkalaGajiDplkPegawaiEntity.setFlag(bean.getFlag());
            imPayrollSkalaGajiDplkPegawaiEntity.setAction(bean.getAction());
            imPayrollSkalaGajiDplkPegawaiEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollSkalaGajiDplkPegawaiEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollSkalaGajiDplkPegawaiDao.addAndSave(imPayrollSkalaGajiDplkPegawaiEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiDplkPegawai, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiDplkPegawai> getByCriteria(payrollSkalaGajiDplkPegawai searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiDplkPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPensiunDplkPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPensiunDplkPegawaiId())) {
                hsCriteria.put("skala_gaji_pensiun_dplk_pensiun_id", searchBean.getSkalaGajiPensiunDplkPegawaiId());
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


            List<ImPayrollSkalaGajiDplkPegawaiEntity> imPayrollSkalaGajiDplkPegawaiEntity = null;
            try {

                imPayrollSkalaGajiDplkPegawaiEntity = payrollSkalaGajiDplkPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiDplkPegawaiEntity != null){
                payrollSkalaGajiDplkPegawai returnPayrollSkalaGajiDplkPegawai;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiDplkPegawaiEntity payrollSkalaGajiEntity : imPayrollSkalaGajiDplkPegawaiEntity){
                    returnPayrollSkalaGajiDplkPegawai = new payrollSkalaGajiDplkPegawai();
                    returnPayrollSkalaGajiDplkPegawai.setSkalaGajiPensiunDplkPegawaiId(payrollSkalaGajiEntity.getSkalaGajiPensiunDplkPegawaiId());
                    returnPayrollSkalaGajiDplkPegawai.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    ImGolonganDapenEntity golonganDapenEntity = golonganDapenDao.getById("golonganDapenId",payrollSkalaGajiEntity.getGolonganId());
                    if (golonganDapenEntity!=null){
                        returnPayrollSkalaGajiDplkPegawai.setGolonganName(golonganDapenEntity.getGolonganDapenName());
                    }

                    returnPayrollSkalaGajiDplkPegawai.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGajiDplkPegawai.setStNilai(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getNilai(), "###,###"));

                    returnPayrollSkalaGajiDplkPegawai.setCreatedWho(payrollSkalaGajiEntity.getCreatedWho());
                    returnPayrollSkalaGajiDplkPegawai.setCreatedDate(payrollSkalaGajiEntity.getCreatedDate());
                    returnPayrollSkalaGajiDplkPegawai.setLastUpdate(payrollSkalaGajiEntity.getLastUpdate());

                    returnPayrollSkalaGajiDplkPegawai.setAction(payrollSkalaGajiEntity.getAction());
                    returnPayrollSkalaGajiDplkPegawai.setFlag(payrollSkalaGajiEntity.getFlag());
                    listOfResult.add(returnPayrollSkalaGajiDplkPegawai);
                }
            }
        }
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollSkalaGajiDplkPegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiDplkPegawai> getComboPayrollSkalaGajiDplkPegawaiWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}