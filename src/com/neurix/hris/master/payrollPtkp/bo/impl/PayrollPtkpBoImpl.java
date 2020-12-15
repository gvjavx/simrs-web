package com.neurix.hris.master.payrollPtkp.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollPtkp.bo.PayrollPtkpBo;
import com.neurix.hris.master.payrollPtkp.dao.PayrollPtkpDao;
import com.neurix.hris.master.payrollPtkp.model.ImHrisPayrollPtkpEntity;
import com.neurix.hris.master.payrollPtkp.model.PayrollPtkp;
import com.neurix.hris.master.payrollPtkp.model.ImHrisPayrollPtkpHistoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
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
public class PayrollPtkpBoImpl implements PayrollPtkpBo {

    protected static transient Logger logger = Logger.getLogger(PayrollPtkpBoImpl.class);
    private PayrollPtkpDao payrollPtkpDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollPtkpBoImpl.logger = logger;
    }

    public PayrollPtkpDao getPayrollPtkpDao() {
        return payrollPtkpDao;
    }


    public void setPayrollPtkpDao(PayrollPtkpDao payrollPtkpDao) {
        this.payrollPtkpDao = payrollPtkpDao;
    }

    @Override
    public void saveDelete(PayrollPtkp bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean != null) {

            String payrollPtkp = bean.getIdPtkp();

            ImHrisPayrollPtkpEntity imPayrollPtkpEntity = null;

            try {
                // Get data from database by ID
                imPayrollPtkpEntity = payrollPtkpDao.getById("idPtkp", payrollPtkp);
            } catch (HibernateException e) {
                logger.error("[PayrollPtkpBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollPtkpEntity != null) {

                // Modify from bean to entity serializable
                imPayrollPtkpEntity.setIdPtkp(bean.getIdPtkp());
                imPayrollPtkpEntity.setFlag(bean.getFlag());
                imPayrollPtkpEntity.setAction(bean.getAction());
                imPayrollPtkpEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollPtkpEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollPtkpDao.updateAndSave(imPayrollPtkpEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollPtkpBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollPtkp, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollPtkpBoImpl.saveDelete] Error, not found data PayrollPtkp with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollPtkp with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollPtkpBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollPtkp bean) throws GeneralBOException {
        logger.info("[PayrollPtkpBoImpl.saveEdit] start process >>>");
        if (bean != null) {
            String historyId = "";
            String payrollPtkp = bean.getIdPtkp();

            ImHrisPayrollPtkpEntity imPayrollPtkpEntity = null;
            try {
                // Get data from database by ID
                imPayrollPtkpEntity = payrollPtkpDao.getById("idPtkp", payrollPtkp);
                historyId = payrollPtkpDao.getNextPayrollPtkpHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollPtkpBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollPtkp by Kode PayrollPtkp, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollPtkpEntity != null) {
                ImHrisPayrollPtkpHistoryEntity imPayrollPtkpHistoryEntity = new ImHrisPayrollPtkpHistoryEntity();

                imPayrollPtkpHistoryEntity.setId(historyId);
                imPayrollPtkpHistoryEntity.setIdPtkp(imPayrollPtkpEntity.getIdPtkp());
                imPayrollPtkpHistoryEntity.setStatusKeluarga(imPayrollPtkpEntity.getStatusKeluarga());
                imPayrollPtkpHistoryEntity.setJumlahTanggungan(imPayrollPtkpEntity.getJumlahTanggungan());
                imPayrollPtkpHistoryEntity.setNilai(imPayrollPtkpEntity.getNilai().intValue());
                imPayrollPtkpHistoryEntity.setFlag(imPayrollPtkpEntity.getFlag());
                imPayrollPtkpHistoryEntity.setAction(imPayrollPtkpEntity.getAction());
                imPayrollPtkpHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollPtkpHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imPayrollPtkpHistoryEntity.setCreatedWho(imPayrollPtkpEntity.getLastUpdateWho());
                imPayrollPtkpHistoryEntity.setCreatedDate(imPayrollPtkpEntity.getLastUpdate());

                imPayrollPtkpEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imPayrollPtkpEntity.setJumlahTanggungan(bean.getJumlahTanggungan());
                imPayrollPtkpEntity.setNilai(bean.getNilai());

                imPayrollPtkpEntity.setFlag(bean.getFlag());
                imPayrollPtkpEntity.setAction(bean.getAction());
                imPayrollPtkpEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollPtkpEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollPtkpDao.updateAndSave(imPayrollPtkpEntity);
                    payrollPtkpDao.addAndSaveHistory(imPayrollPtkpHistoryEntity);
                    //payrollPtkpDao.addAndSaveHistory(imPayrollPtkpHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollPtkpBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollPtkp, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollPtkpBoImpl.saveEdit] Error, not found data PayrollPtkp with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollPtkp with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollPtkpBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollPtkp saveAdd(PayrollPtkp bean) throws GeneralBOException {
        logger.info("[PayrollPtkpBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisPayrollPtkpEntity> list = new ArrayList<>();
            try {
                list = payrollPtkpDao.checkData(bean.getStatusKeluarga(), bean.getJumlahTanggungan());
            } catch (HibernateException e) {
                logger.error("[PayrollPtkpBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollPtkp id, please info to your admin..." + e.getMessage());
            }


            if (list.size() > 0) {
                logger.error("Status Keluarga dan Jumlah Tanggungan sudah ada");
                throw new GeneralBOException("Status Keluarga dan Jumlah Tanggungan sudah ada");
            } else {

                String payrollPtkp;
                try {
                    // Generating ID, get from postgre sequence
                    payrollPtkp = payrollPtkpDao.getNextIdPtkp();
                } catch (HibernateException e) {
                    logger.error("[PayrollPtkpBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollPtkp id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImHrisPayrollPtkpEntity imPayrollPtkpEntity = new ImHrisPayrollPtkpEntity();
                imPayrollPtkpEntity.setIdPtkp(payrollPtkp);
                imPayrollPtkpEntity.setStatusKeluarga(bean.getStatusKeluarga());
                imPayrollPtkpEntity.setJumlahTanggungan(bean.getJumlahTanggungan());
                imPayrollPtkpEntity.setNilai(bean.getNilai());

                imPayrollPtkpEntity.setFlag(bean.getFlag());
                imPayrollPtkpEntity.setAction(bean.getAction());
                imPayrollPtkpEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollPtkpEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollPtkpEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollPtkpEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollPtkpDao.addAndSave(imPayrollPtkpEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollPtkpBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollPtkp, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[PayrollPtkpBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollPtkp> getByCriteria(PayrollPtkp filter) throws GeneralBOException {
        logger.info("[PayrollPtkpBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollPtkp> listOfResult = new ArrayList();

        if (filter != null) {
            Map hsCriteria = new HashMap();

            if (filter.getIdPtkp() != null && !"".equalsIgnoreCase(filter.getIdPtkp())) {
                hsCriteria.put("ptkp_id", filter.getIdPtkp());
            }
            if (filter.getStatusKeluarga() != null && !"".equalsIgnoreCase(filter.getStatusKeluarga())) {
                hsCriteria.put("status_keluarga", filter.getStatusKeluarga());
            }
            if (filter.getJumlahTanggungan() != null && !"".equalsIgnoreCase(filter.getJumlahTanggungan().toString())) {
                hsCriteria.put("jumlah_tanggungan", filter.getJumlahTanggungan());
            }
            if (filter.getNilai() != null && !"".equalsIgnoreCase(filter.getNilai().toString())) {
                hsCriteria.put("nilai", filter.getNilai());
            }

            if (filter.getFlag() != null && !"".equalsIgnoreCase(filter.getFlag())) {
                if ("N".equalsIgnoreCase(filter.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", filter.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImHrisPayrollPtkpEntity> imPayrollPtkpEntity = null;
            try {

                imPayrollPtkpEntity = payrollPtkpDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollPtkpBoImpl.getSearchPayrollPtkpByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imPayrollPtkpEntity != null) {
                PayrollPtkp returnPayrollPtkp;
                // Looping from dao to object and save in collection
                for (ImHrisPayrollPtkpEntity payrollPtkpEntity : imPayrollPtkpEntity) {
                    returnPayrollPtkp = new PayrollPtkp();
                    returnPayrollPtkp.setIdPtkp(payrollPtkpEntity.getIdPtkp());
                    returnPayrollPtkp.setStatusKeluarga(payrollPtkpEntity.getStatusKeluarga());
                    returnPayrollPtkp.setJumlahTanggungan(payrollPtkpEntity.getJumlahTanggungan());

                    returnPayrollPtkp.setNilai(payrollPtkpEntity.getNilai());

                    if ("B".equalsIgnoreCase(payrollPtkpEntity.getStatusKeluarga())){
                        returnPayrollPtkp.setStatusKeluargaName("Bujang");
                    }else{
                        returnPayrollPtkp.setStatusKeluargaName("Keluarga");
                    }

                    returnPayrollPtkp.setCreatedWho(payrollPtkpEntity.getCreatedWho());
                    returnPayrollPtkp.setCreatedDate(payrollPtkpEntity.getCreatedDate());
                    returnPayrollPtkp.setLastUpdate(payrollPtkpEntity.getLastUpdate());

                    returnPayrollPtkp.setAction(payrollPtkpEntity.getAction());
                    returnPayrollPtkp.setFlag(payrollPtkpEntity.getFlag());
                    listOfResult.add(returnPayrollPtkp);
                }
            }
        }
        logger.info("[PayrollPtkpBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollPtkp> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
