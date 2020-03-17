package com.neurix.hris.master.payrollFaktorKeluarga.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollFaktorKeluarga.bo.PayrollFaktorKeluargaBo;
import com.neurix.hris.master.payrollFaktorKeluarga.dao.PayrollFaktorKeluargaDao;
import com.neurix.hris.master.payrollFaktorKeluarga.model.ImPayrollFaktorKeluargaEntity;
import com.neurix.hris.master.payrollFaktorKeluarga.model.payrollFaktorKeluarga;
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
public class PayrollFaktorKeluargaBoImpl implements PayrollFaktorKeluargaBo {

    protected static transient Logger logger = Logger.getLogger(PayrollFaktorKeluargaBoImpl.class);
    private PayrollFaktorKeluargaDao payrollFaktorKeluargaDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollFaktorKeluargaBoImpl.logger = logger;
    }

    public PayrollFaktorKeluargaDao getPayrollFaktorKeluargaDao() {
        return payrollFaktorKeluargaDao;
    }


    public void setPayrollFaktorKeluargaDao(PayrollFaktorKeluargaDao payrollFaktorKeluargaDao) {
        this.payrollFaktorKeluargaDao = payrollFaktorKeluargaDao;
    }

    @Override
    public void saveDelete(payrollFaktorKeluarga bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollFaktorKeluargaId = bean.getFaktorKeluargaId();

            ImPayrollFaktorKeluargaEntity imPayrollFaktorKeluargaEntity = null;

            try {
                // Get data from database by ID
                imPayrollFaktorKeluargaEntity = payrollFaktorKeluargaDao.getById("faktorKeluargaId", payrollFaktorKeluargaId);
            } catch (HibernateException e) {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollFaktorKeluargaEntity != null) {

                // Modify from bean to entity serializable
                imPayrollFaktorKeluargaEntity.setFaktorKeluargaId(bean.getFaktorKeluargaId());
                imPayrollFaktorKeluargaEntity.setFlag(bean.getFlag());
                imPayrollFaktorKeluargaEntity.setAction(bean.getAction());
                imPayrollFaktorKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollFaktorKeluargaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollFaktorKeluargaDao.updateAndSave(imPayrollFaktorKeluargaEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollFaktorKeluargaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollFaktorKeluarga, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveDelete] Error, not found data PayrollFaktorKeluarga with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollFaktorKeluarga with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollFaktorKeluargaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollFaktorKeluarga bean) throws GeneralBOException {
        logger.info("[PayrollFaktorKeluargaBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollFaktorKeluargaId = bean.getFaktorKeluargaId();

            ImPayrollFaktorKeluargaEntity imPayrollFaktorKeluargaEntity = null;
            //ImPayrollFaktorKeluargaHistoryEntity imPayrollFaktorKeluargaHistoryEntity = new ImPayrollFaktorKeluargaHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollFaktorKeluargaEntity = payrollFaktorKeluargaDao.getById("faktorKeluargaId", payrollFaktorKeluargaId);
                //historyId = payrollFaktorKeluargaDao.getNextPayrollFaktorKeluargaHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollFaktorKeluarga by Kode PayrollFaktorKeluarga, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollFaktorKeluargaEntity != null) {
                /*imPayrollFaktorKeluargaHistoryEntity.setId(historyId);
                imPayrollFaktorKeluargaHistoryEntity.setPayrollFaktorKeluargaId(imPayrollFaktorKeluargaEntity.getPayrollFaktorKeluargaId());
                imPayrollFaktorKeluargaHistoryEntity.setPayrollFaktorKeluargaName(imPayrollFaktorKeluargaEntity.getPayrollFaktorKeluargaName());
                imPayrollFaktorKeluargaHistoryEntity.setFlag(imPayrollFaktorKeluargaEntity.getFlag());
                imPayrollFaktorKeluargaHistoryEntity.setAction(imPayrollFaktorKeluargaEntity.getAction());
                imPayrollFaktorKeluargaHistoryEntity.setLastUpdateWho(imPayrollFaktorKeluargaEntity.getLastUpdateWho());
                imPayrollFaktorKeluargaHistoryEntity.setLastUpdate(imPayrollFaktorKeluargaEntity.getLastUpdate());
                imPayrollFaktorKeluargaHistoryEntity.setCreatedWho(imPayrollFaktorKeluargaEntity.getLastUpdateWho());
                imPayrollFaktorKeluargaHistoryEntity.setCreatedDate(imPayrollFaktorKeluargaEntity.getLastUpdate());*/

                imPayrollFaktorKeluargaEntity.setFaktorKeluargaId(bean.getFaktorKeluargaId());
                imPayrollFaktorKeluargaEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imPayrollFaktorKeluargaEntity.setJumlahAnak(bean.getJumlahAnak());
                imPayrollFaktorKeluargaEntity.setPtkp(bean.getPtkp());

                imPayrollFaktorKeluargaEntity.setNilai(bean.getNilai());
                imPayrollFaktorKeluargaEntity.setFlag(bean.getFlag());
                imPayrollFaktorKeluargaEntity.setAction(bean.getAction());
                imPayrollFaktorKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollFaktorKeluargaEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollFaktorKeluargaDao.updateAndSave(imPayrollFaktorKeluargaEntity);
                    //payrollFaktorKeluargaDao.addAndSaveHistory(imPayrollFaktorKeluargaHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollFaktorKeluargaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollFaktorKeluarga, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveEdit] Error, not found data PayrollFaktorKeluarga with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollFaktorKeluarga with request id, please check again your data ...");
//                condition = "Error, not found data PayrollFaktorKeluarga with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollFaktorKeluargaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollFaktorKeluarga saveAdd(payrollFaktorKeluarga bean) throws GeneralBOException {
        logger.info("[PayrollFaktorKeluargaBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollFaktorKeluargaId;
            try {
                // Generating ID, get from postgre sequence
                payrollFaktorKeluargaId = payrollFaktorKeluargaDao.getNextFaktorKeluargaId();
            } catch (HibernateException e) {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollFaktorKeluargaId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollFaktorKeluargaEntity imPayrollFaktorKeluargaEntity = new ImPayrollFaktorKeluargaEntity();

            imPayrollFaktorKeluargaEntity.setFaktorKeluargaId(payrollFaktorKeluargaId);
            imPayrollFaktorKeluargaEntity.setStatusKeluarga(bean.getStatusKeluarga());
            imPayrollFaktorKeluargaEntity.setJumlahAnak(bean.getJumlahAnak());
            imPayrollFaktorKeluargaEntity.setPtkp(bean.getPtkp());

            imPayrollFaktorKeluargaEntity.setNilai(bean.getNilai());
            imPayrollFaktorKeluargaEntity.setFlag(bean.getFlag());
            imPayrollFaktorKeluargaEntity.setAction(bean.getAction());
            imPayrollFaktorKeluargaEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollFaktorKeluargaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollFaktorKeluargaEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollFaktorKeluargaEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollFaktorKeluargaDao.addAndSave(imPayrollFaktorKeluargaEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollFaktorKeluargaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollFaktorKeluarga, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollFaktorKeluargaBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollFaktorKeluarga> getByCriteria(payrollFaktorKeluarga searchBean) throws GeneralBOException {
        logger.info("[PayrollFaktorKeluargaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollFaktorKeluarga> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getFaktorKeluargaId() != null && !"".equalsIgnoreCase(searchBean.getFaktorKeluargaId())) {
                hsCriteria.put("faktor_id", searchBean.getFaktorKeluargaId());
            }
            if (searchBean.getStatusKeluarga() != null && !"".equalsIgnoreCase(searchBean.getStatusKeluarga())) {
                hsCriteria.put("status_id", searchBean.getStatusKeluarga());
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


            List<ImPayrollFaktorKeluargaEntity> imPayrollFaktorKeluargaEntity = null;
            try {

                imPayrollFaktorKeluargaEntity = payrollFaktorKeluargaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollFaktorKeluargaBoImpl.getSearchPayrollFaktorKeluargaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollFaktorKeluargaEntity != null){
                payrollFaktorKeluarga returnPayrollFaktorKeluarga;
                // Looping from dao to object and save in collection
                for(ImPayrollFaktorKeluargaEntity payrollFaktorKeluargaEntity : imPayrollFaktorKeluargaEntity){
                    returnPayrollFaktorKeluarga = new payrollFaktorKeluarga();
                    returnPayrollFaktorKeluarga.setFaktorKeluargaId(payrollFaktorKeluargaEntity.getFaktorKeluargaId());
                    returnPayrollFaktorKeluarga.setStatusKeluarga(payrollFaktorKeluargaEntity.getStatusKeluarga());
                    if ("B".equalsIgnoreCase(payrollFaktorKeluargaEntity.getStatusKeluarga())){
                        returnPayrollFaktorKeluarga.setStatusKeluargaName("Belum");
                    }
                    if ("K".equalsIgnoreCase(payrollFaktorKeluargaEntity.getStatusKeluarga())){
                        returnPayrollFaktorKeluarga.setStatusKeluargaName("Menikah");
                    }
                    returnPayrollFaktorKeluarga.setJumlahAnak(payrollFaktorKeluargaEntity.getJumlahAnak());
                    returnPayrollFaktorKeluarga.setPtkp(payrollFaktorKeluargaEntity.getPtkp());

                    returnPayrollFaktorKeluarga.setNilai(payrollFaktorKeluargaEntity.getNilai());
                    returnPayrollFaktorKeluarga.setCreatedWho(payrollFaktorKeluargaEntity.getCreatedWho());
                    returnPayrollFaktorKeluarga.setCreatedDate(payrollFaktorKeluargaEntity.getCreatedDate());
                    returnPayrollFaktorKeluarga.setLastUpdate(payrollFaktorKeluargaEntity.getLastUpdate());

                    returnPayrollFaktorKeluarga.setAction(payrollFaktorKeluargaEntity.getAction());
                    returnPayrollFaktorKeluarga.setFlag(payrollFaktorKeluargaEntity.getFlag());
                    listOfResult.add(returnPayrollFaktorKeluarga);
                }
            }
        }
        logger.info("[PayrollFaktorKeluargaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollFaktorKeluarga> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
