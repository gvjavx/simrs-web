package com.neurix.hris.master.payrollAirListrik.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollAirListrik.bo.PayrollAirListrikBo;
import com.neurix.hris.master.payrollAirListrik.dao.PayrollAirListrikDao;
import com.neurix.hris.master.payrollAirListrik.model.ImPayrollAirListrikEntity;
import com.neurix.hris.master.payrollAirListrik.model.ImPayrollAirListrikHistoryEntity;
import com.neurix.hris.master.payrollAirListrik.model.PayrollAirListrik;
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
public class PayrollAirListrikBoImpl implements PayrollAirListrikBo {

    protected static transient Logger logger = Logger.getLogger(PayrollAirListrikBoImpl.class);
    private PayrollAirListrikDao payrollAirListrikDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollAirListrikBoImpl.logger = logger;
    }

    public PayrollAirListrikDao getPayrollAirListrikDao() {
        return payrollAirListrikDao;
    }


    public void setPayrollAirListrikDao(PayrollAirListrikDao payrollAirListrikDao) {
        this.payrollAirListrikDao = payrollAirListrikDao;
    }

    @Override
    public void saveDelete(PayrollAirListrik bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollAirListrik = bean.getTunjAirListrikId();

            ImPayrollAirListrikEntity imPayrollAirListrikEntity = null;

            try {
                // Get data from database by ID
                imPayrollAirListrikEntity = payrollAirListrikDao.getById("tunjAirListrikId", payrollAirListrik);
            } catch (HibernateException e) {
                logger.error("[PayrollAirListrikBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollAirListrikEntity != null) {

                // Modify from bean to entity serializable
                imPayrollAirListrikEntity.setTunjAirListrikId(bean.getTunjAirListrikId());
                imPayrollAirListrikEntity.setFlag(bean.getFlag());
                imPayrollAirListrikEntity.setAction(bean.getAction());
                imPayrollAirListrikEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollAirListrikEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollAirListrikDao.updateAndSave(imPayrollAirListrikEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollAirListrikBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollAirListrik, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollAirListrikBoImpl.saveDelete] Error, not found data PayrollAirListrik with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollAirListrik with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollAirListrikBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollAirListrik bean) throws GeneralBOException {
        logger.info("[PayrollAirListrikBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String payrollAirListrik = bean.getTunjAirListrikId();

            ImPayrollAirListrikEntity imPayrollAirListrikEntity = null;
            ImPayrollAirListrikHistoryEntity imPayrollAirListrikHistoryEntity = new ImPayrollAirListrikHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollAirListrikEntity = payrollAirListrikDao.getById("tunjAirListrikId", payrollAirListrik);
                //historyId = payrollAirListrikDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollAirListrikBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollAirListrik by Kode PayrollAirListrik, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollAirListrikEntity != null) {
                /*imPayrollAirListrikHistoryEntity.setId(historyId);
                imPayrollAirListrikHistoryEntity.setPayrollAirListrik(imPayrollAirListrikEntity.getPayrollAirListrik());
                imPayrollAirListrikHistoryEntity.setPayrollAirListrikName(imPayrollAirListrikEntity.getPayrollAirListrikName());
                imPayrollAirListrikHistoryEntity.setFlag(imPayrollAirListrikEntity.getFlag());
                imPayrollAirListrikHistoryEntity.setAction(imPayrollAirListrikEntity.getAction());
                imPayrollAirListrikHistoryEntity.setLastUpdateWho(imPayrollAirListrikEntity.getLastUpdateWho());
                imPayrollAirListrikHistoryEntity.setLastUpdate(imPayrollAirListrikEntity.getLastUpdate());
                imPayrollAirListrikHistoryEntity.setCreatedWho(imPayrollAirListrikEntity.getLastUpdateWho());
                imPayrollAirListrikHistoryEntity.setCreatedDate(imPayrollAirListrikEntity.getLastUpdate());*/

                imPayrollAirListrikEntity.setTunjAirListrikId(bean.getTunjAirListrikId());
                imPayrollAirListrikEntity.setGolonganId(bean.getGolonganId());
                imPayrollAirListrikEntity.setNilai(bean.getNilai());
                
                imPayrollAirListrikEntity.setFlag(bean.getFlag());
                imPayrollAirListrikEntity.setAction(bean.getAction());
                imPayrollAirListrikEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollAirListrikEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollAirListrikDao.updateAndSave(imPayrollAirListrikEntity);
                    //payrollAirListrikDao.addAndSaveHistory(imPayrollAirListrikHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollAirListrikBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollAirListrik, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollAirListrikBoImpl.saveEdit] Error, not found data PayrollAirListrik with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollAirListrik with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollAirListrikBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollAirListrik saveAdd(PayrollAirListrik bean) throws GeneralBOException {
        logger.info("[PayrollAirListrikBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String payrollAirListrik;
            try {
                // Generating ID, get from postgre sequence
                payrollAirListrik = payrollAirListrikDao.getNextAirListrik();
            } catch (HibernateException e) {
                logger.error("[PayrollAirListrikBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollAirListrik id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollAirListrikEntity imPayrollAirListrikEntity = new ImPayrollAirListrikEntity();

            imPayrollAirListrikEntity.setTunjAirListrikId(payrollAirListrik);
            imPayrollAirListrikEntity.setGolonganId(bean.getGolonganId());

            imPayrollAirListrikEntity.setNilai(bean.getNilai());
            imPayrollAirListrikEntity.setFlag(bean.getFlag());
            imPayrollAirListrikEntity.setAction(bean.getAction());
            imPayrollAirListrikEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollAirListrikEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollAirListrikEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollAirListrikEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollAirListrikDao.addAndSave(imPayrollAirListrikEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollAirListrikBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollAirListrik, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollAirListrikBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollAirListrik> getByCriteria(PayrollAirListrik searchBean) throws GeneralBOException {
        logger.info("[PayrollAirListrikBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollAirListrik> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjAirListrikId() != null && !"".equalsIgnoreCase(searchBean.getTunjAirListrikId())) {
                hsCriteria.put("air_listrik_id", searchBean.getTunjAirListrikId());
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


            List<ImPayrollAirListrikEntity> imPayrollAirListrikEntity = null;
            try {

                imPayrollAirListrikEntity = payrollAirListrikDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollAirListrikBoImpl.getSearchPayrollAirListrikByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollAirListrikEntity != null){
                PayrollAirListrik returnPayrollAirListrik;
                // Looping from dao to object and save in collection
                for(ImPayrollAirListrikEntity payrollAirListrikEntity : imPayrollAirListrikEntity){
                    returnPayrollAirListrik = new PayrollAirListrik();
                    returnPayrollAirListrik.setTunjAirListrikId(payrollAirListrikEntity.getTunjAirListrikId());
                    returnPayrollAirListrik.setGolonganId(payrollAirListrikEntity.getGolonganId());
                    if(payrollAirListrikEntity.getGolonganId() != null){
                        returnPayrollAirListrik.setGolonganName(payrollAirListrikEntity.getImGolonganEntity().getGolonganName());
                    }
                    returnPayrollAirListrik.setNilai(payrollAirListrikEntity.getNilai());

                    returnPayrollAirListrik.setCreatedWho(payrollAirListrikEntity.getCreatedWho());
                    returnPayrollAirListrik.setCreatedDate(payrollAirListrikEntity.getCreatedDate());
                    returnPayrollAirListrik.setLastUpdate(payrollAirListrikEntity.getLastUpdate());

                    returnPayrollAirListrik.setAction(payrollAirListrikEntity.getAction());
                    returnPayrollAirListrik.setFlag(payrollAirListrikEntity.getFlag());
                    listOfResult.add(returnPayrollAirListrik);
                }
            }
        }
        logger.info("[PayrollAirListrikBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollAirListrik> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
