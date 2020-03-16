package com.neurix.hris.master.payrollSkalaGajiPensiunDplk.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.bo.PayrollSkalaGajiPensiunDplkBo;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.dao.PayrollSkalaGajiPensiunDplkDao;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.ImPayrollSkalaGajiPensiunDplkEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.payrollSkalaGajiPensiunDplk;
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
public class PayrollSkalaGajiPensiunDplkBoImpl implements PayrollSkalaGajiPensiunDplkBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunDplkBoImpl.class);
    private PayrollSkalaGajiPensiunDplkDao payrollSkalaGajiPensiunDplkDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPensiunDplkBoImpl.logger = logger;
    }

    public PayrollSkalaGajiPensiunDplkDao getPayrollSkalaGajiPensiunDplkDao() {
        return payrollSkalaGajiPensiunDplkDao;
    }

    public void setPayrollSkalaGajiPensiunDplkDao(PayrollSkalaGajiPensiunDplkDao payrollSkalaGajiPensiunDplkDao) {
        this.payrollSkalaGajiPensiunDplkDao = payrollSkalaGajiPensiunDplkDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiPensiunDplk bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunDplkEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDplkDao.getById("skalaGajiPensiunId", payrollSkalaGajiId);
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
                    payrollSkalaGajiPensiunDplkDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiPensiunDplk bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPensiunId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunDplkEntity imPayrollSkalaGajiPensiunEntity = null;
            //ImPayrollSkalaGajiPensiunHistoryEntity imPayrollSkalaGajiPensiunHistoryEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiPensiunEntity = payrollSkalaGajiPensiunDplkDao.getById("skalaGajiPensiunId", payrollSkalaGajiPensiunId);
                //historyId = payrollSkalaGajiPensiunDplkDao.getNextPayrollSkalaGajiPensiunHistoryId();
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

                /*imPayrollSkalaGajiPensiunEntity.setSkalaGajiPensiunId(bean.getSkalaGajiPensiunId());
                imPayrollSkalaGajiPensiunEntity.setGolonganId(bean.getGolonganId());*/
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiPensiunDplkDao.updateAndSave(imPayrollSkalaGajiPensiunEntity);
                    //payrollSkalaGajiPensiunDplkDao.addAndSaveHistory(imPayrollSkalaGajiPensiunHistoryEntity);
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
    public payrollSkalaGajiPensiunDplk saveAdd(payrollSkalaGajiPensiunDplk bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getGolonganId(),bean.getPoin());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollSkalaGajiPensiunId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiPensiunId = payrollSkalaGajiPensiunDplkDao.getNextSkalaGajiPensiun();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPensiunId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiPensiunDplkEntity imPayrollSkalaGajiPensiunEntity = new ImPayrollSkalaGajiPensiunDplkEntity();

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
                    payrollSkalaGajiPensiunDplkDao.addAndSave(imPayrollSkalaGajiPensiunEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Level dan Masa Golongan Tersebut Sudah Ada");
            }
        }

        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiPensiunDplk> getByCriteria(payrollSkalaGajiPensiunDplk searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiPensiunDplk> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPensiunId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPensiunId())) {
                hsCriteria.put("skalaGajiPensiunId", searchBean.getSkalaGajiPensiunId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golonganId", searchBean.getGolonganId());
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


            List<ImPayrollSkalaGajiPensiunDplkEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDplkDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiPensiunDplk returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiPensiunDplkEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiPensiunDplk();
                    returnPayrollSkalaGaji.setSkalaGajiPensiunId(payrollSkalaGajiEntity.getSkalaGajiPensiunId());
                    returnPayrollSkalaGaji.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    if(payrollSkalaGajiEntity.getGolonganId() != null){
                        returnPayrollSkalaGaji.setGolonganName(payrollSkalaGajiEntity.getImGolonganEntity().getGolonganName());
                    }
                    returnPayrollSkalaGaji.setNilai(payrollSkalaGajiEntity.getNilai());
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
    public List<payrollSkalaGajiPensiunDplk> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiPensiunDplk> getComboPayrollSkalaGajiPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganId, Integer masa)throws GeneralBOException{
        String status ="";
        List<ImPayrollSkalaGajiPensiunDplkEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollSkalaGajiPensiunDplkDao.getSkalaGajiPensiunDplk(golonganId, masa);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}