package com.neurix.hris.master.payrollTunjanganPerumahan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollTunjanganPerumahan.bo.PayrollTunjanganPerumahanBo;
import com.neurix.hris.master.payrollTunjanganPerumahan.dao.PayrollTunjanganPerumahanDao;
import com.neurix.hris.master.payrollTunjanganPerumahan.model.ImPayrollTunjanganPerumahanEntity;
import com.neurix.hris.master.payrollTunjanganPerumahan.model.payrollTunjanganPerumahan;
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
public class PayrollTunjanganPerumahanBoImpl implements PayrollTunjanganPerumahanBo {

    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganPerumahanBoImpl.class);
    private PayrollTunjanganPerumahanDao payrollTunjanganPerumahanDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganPerumahanBoImpl.logger = logger;
    }

    public PayrollTunjanganPerumahanDao getPayrollTunjanganPerumahanDao() {
        return payrollTunjanganPerumahanDao;
    }


    public void setPayrollTunjanganPerumahanDao(PayrollTunjanganPerumahanDao payrollTunjanganPerumahanDao) {
        this.payrollTunjanganPerumahanDao = payrollTunjanganPerumahanDao;
    }

    @Override
    public void saveDelete(payrollTunjanganPerumahan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollTunjanganPerumahanId = bean.getTunjRumahId();

            ImPayrollTunjanganPerumahanEntity imPayrollTunjanganPerumahanEntity = null;

            try {
                // Get data from database by ID
                imPayrollTunjanganPerumahanEntity = payrollTunjanganPerumahanDao.getById("tunjRumahId", payrollTunjanganPerumahanId);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganPerumahanEntity != null) {

                // Modify from bean to entity serializable
                imPayrollTunjanganPerumahanEntity.setTunjRumahId(bean.getTunjRumahId());
                imPayrollTunjanganPerumahanEntity.setFlag(bean.getFlag());
                imPayrollTunjanganPerumahanEntity.setAction(bean.getAction());
                imPayrollTunjanganPerumahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganPerumahanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollTunjanganPerumahanDao.updateAndSave(imPayrollTunjanganPerumahanEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganPerumahanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganPerumahan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveDelete] Error, not found data PayrollTunjanganPerumahan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganPerumahan with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollTunjanganPerumahanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollTunjanganPerumahan bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganPerumahanBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollTunjanganPerumahanId = bean.getTunjRumahId();

            ImPayrollTunjanganPerumahanEntity imPayrollTunjanganPerumahanEntity = null;
            //ImPayrollTunjanganPerumahanHistoryEntity imPayrollTunjanganPerumahanHistoryEntity = new ImPayrollTunjanganPerumahanHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganPerumahanEntity = payrollTunjanganPerumahanDao.getById("tunjRumahId", payrollTunjanganPerumahanId);
                //historyId = payrollTunjanganPerumahanDao.getNextPayrollTunjanganPerumahanHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollTunjanganPerumahan by Kode PayrollTunjanganPerumahan, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganPerumahanEntity != null) {
                /*imPayrollTunjanganPerumahanHistoryEntity.setId(historyId);
                imPayrollTunjanganPerumahanHistoryEntity.setPayrollTunjanganPerumahanId(imPayrollTunjanganPerumahanEntity.getPayrollTunjanganPerumahanId());
                imPayrollTunjanganPerumahanHistoryEntity.setPayrollTunjanganPerumahanName(imPayrollTunjanganPerumahanEntity.getPayrollTunjanganPerumahanName());
                imPayrollTunjanganPerumahanHistoryEntity.setFlag(imPayrollTunjanganPerumahanEntity.getFlag());
                imPayrollTunjanganPerumahanHistoryEntity.setAction(imPayrollTunjanganPerumahanEntity.getAction());
                imPayrollTunjanganPerumahanHistoryEntity.setLastUpdateWho(imPayrollTunjanganPerumahanEntity.getLastUpdateWho());
                imPayrollTunjanganPerumahanHistoryEntity.setLastUpdate(imPayrollTunjanganPerumahanEntity.getLastUpdate());
                imPayrollTunjanganPerumahanHistoryEntity.setCreatedWho(imPayrollTunjanganPerumahanEntity.getLastUpdateWho());
                imPayrollTunjanganPerumahanHistoryEntity.setCreatedDate(imPayrollTunjanganPerumahanEntity.getLastUpdate());*/

                imPayrollTunjanganPerumahanEntity.setTunjRumahId(bean.getTunjRumahId());
                imPayrollTunjanganPerumahanEntity.setKelompokId(bean.getKelompokId());
                imPayrollTunjanganPerumahanEntity.setGolonganId(bean.getGolonganId());

                imPayrollTunjanganPerumahanEntity.setNilai(bean.getNilai());
                imPayrollTunjanganPerumahanEntity.setFlag(bean.getFlag());
                imPayrollTunjanganPerumahanEntity.setAction(bean.getAction());
                imPayrollTunjanganPerumahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganPerumahanEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollTunjanganPerumahanDao.updateAndSave(imPayrollTunjanganPerumahanEntity);
                    //payrollTunjanganPerumahanDao.addAndSaveHistory(imPayrollTunjanganPerumahanHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganPerumahanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganPerumahan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveEdit] Error, not found data PayrollTunjanganPerumahan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganPerumahan with request id, please check again your data ...");
//                condition = "Error, not found data PayrollTunjanganPerumahan with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollTunjanganPerumahanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollTunjanganPerumahan saveAdd(payrollTunjanganPerumahan bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganPerumahanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollTunjanganPerumahanId;
            try {
                // Generating ID, get from postgre sequence
                payrollTunjanganPerumahanId = payrollTunjanganPerumahanDao.getNextTunjRumah();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollTunjanganPerumahanId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollTunjanganPerumahanEntity imPayrollTunjanganPerumahanEntity = new ImPayrollTunjanganPerumahanEntity();

            imPayrollTunjanganPerumahanEntity.setTunjRumahId(payrollTunjanganPerumahanId);
            imPayrollTunjanganPerumahanEntity.setGolonganId(bean.getGolonganId());
            imPayrollTunjanganPerumahanEntity.setKelompokId(bean.getKelompokId());

            imPayrollTunjanganPerumahanEntity.setNilai(bean.getNilai());
            imPayrollTunjanganPerumahanEntity.setFlag(bean.getFlag());
            imPayrollTunjanganPerumahanEntity.setAction(bean.getAction());
            imPayrollTunjanganPerumahanEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollTunjanganPerumahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollTunjanganPerumahanEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollTunjanganPerumahanEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollTunjanganPerumahanDao.addAndSave(imPayrollTunjanganPerumahanEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganPerumahanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollTunjanganPerumahan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollTunjanganPerumahanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollTunjanganPerumahan> getByCriteria(payrollTunjanganPerumahan searchBean) throws GeneralBOException {
        logger.info("[PayrollTunjanganPerumahanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollTunjanganPerumahan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjRumahId() != null && !"".equalsIgnoreCase(searchBean.getTunjRumahId())) {
                hsCriteria.put("tunj_rumah_id", searchBean.getTunjRumahId());
            }
            if (searchBean.getKelompokId() != null && !"".equalsIgnoreCase(searchBean.getKelompokId())) {
                hsCriteria.put("kelompok_id", searchBean.getKelompokId());
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


            List<ImPayrollTunjanganPerumahanEntity> imPayrollTunjanganPerumahanEntity = null;
            try {

                imPayrollTunjanganPerumahanEntity = payrollTunjanganPerumahanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganPerumahanBoImpl.getSearchPayrollTunjanganPerumahanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganPerumahanEntity != null){
                payrollTunjanganPerumahan returnPayrollTunjanganPerumahan;
                // Looping from dao to object and save in collection
                for(ImPayrollTunjanganPerumahanEntity payrollTunjanganPerumahanEntity : imPayrollTunjanganPerumahanEntity){
                    returnPayrollTunjanganPerumahan = new payrollTunjanganPerumahan();
                    returnPayrollTunjanganPerumahan.setTunjRumahId(payrollTunjanganPerumahanEntity.getTunjRumahId());
                    returnPayrollTunjanganPerumahan.setKelompokId(payrollTunjanganPerumahanEntity.getKelompokId());
                    returnPayrollTunjanganPerumahan.setGolonganId(payrollTunjanganPerumahanEntity.getGolonganId());
                    if(payrollTunjanganPerumahanEntity.getGolonganId() != null){
                        if(!"".equalsIgnoreCase(payrollTunjanganPerumahanEntity.getGolonganId())){
                            returnPayrollTunjanganPerumahan.setGolonganName(payrollTunjanganPerumahanEntity.getImGolonganEntity().getGolonganName());
                        }
                    }
                    if(payrollTunjanganPerumahanEntity.getKelompokId() != null){
                        if(!"".equalsIgnoreCase(payrollTunjanganPerumahanEntity.getKelompokId())){
                            returnPayrollTunjanganPerumahan.setKelompokName(payrollTunjanganPerumahanEntity.getImKelompokPositionEntity().getKelompokName());
                        }
                    }
                    returnPayrollTunjanganPerumahan.setNilai(payrollTunjanganPerumahanEntity.getNilai());
                    returnPayrollTunjanganPerumahan.setCreatedWho(payrollTunjanganPerumahanEntity.getCreatedWho());
                    returnPayrollTunjanganPerumahan.setCreatedDate(payrollTunjanganPerumahanEntity.getCreatedDate());
                    returnPayrollTunjanganPerumahan.setLastUpdate(payrollTunjanganPerumahanEntity.getLastUpdate());

                    returnPayrollTunjanganPerumahan.setAction(payrollTunjanganPerumahanEntity.getAction());
                    returnPayrollTunjanganPerumahan.setFlag(payrollTunjanganPerumahanEntity.getFlag());
                    listOfResult.add(returnPayrollTunjanganPerumahan);
                }
            }
        }
        logger.info("[PayrollTunjanganPerumahanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollTunjanganPerumahan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
