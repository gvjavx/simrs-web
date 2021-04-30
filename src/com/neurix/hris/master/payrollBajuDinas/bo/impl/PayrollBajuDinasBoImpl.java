package com.neurix.hris.master.payrollBajuDinas.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollBajuDinas.bo.PayrollBajuDinasBo;
import com.neurix.hris.master.payrollBajuDinas.dao.PayrollBajuDinasDao;
import com.neurix.hris.master.payrollBajuDinas.model.ImPayrollBajuDinasEntity;
import com.neurix.hris.master.payrollBajuDinas.model.PayrollBajuDinas;
import com.neurix.hris.master.smkSkalaPointPrestasi.dao.SmkSkalaPointPrestasiDao;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.ImSmkSkalaPointPrestasiEntity;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.SmkSkalaPointPrestasi;
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
public class PayrollBajuDinasBoImpl implements PayrollBajuDinasBo {

    protected static transient Logger logger = Logger.getLogger(PayrollBajuDinasBoImpl.class);
    private PayrollBajuDinasDao payrollBajuDinasDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollBajuDinasBoImpl.logger = logger;
    }

    public PayrollBajuDinasDao getPayrollBajuDinasDao() {
        return payrollBajuDinasDao;
    }

    public void setPayrollBajuDinasDao(PayrollBajuDinasDao payrollBajuDinasDao) {
        this.payrollBajuDinasDao = payrollBajuDinasDao;
    }

    @Override
    public void saveDelete(PayrollBajuDinas bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            String payrollBajuDinasId = bean.getPakaianDinasId();

            ImPayrollBajuDinasEntity imPayrollBajuDinasEntity = null;

            try {
                // Get data from database by ID
                imPayrollBajuDinasEntity = payrollBajuDinasDao.getById("pakaianDinasId", payrollBajuDinasId);
            } catch (HibernateException e) {
                logger.error("[PayrollBajuDinasBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollBajuDinasEntity != null) {
                // Modify from bean to entity serializable
                imPayrollBajuDinasEntity.setPakaianDinasId(bean.getPakaianDinasId());
                imPayrollBajuDinasEntity.setFlag(bean.getFlag());
                imPayrollBajuDinasEntity.setAction(bean.getAction());
                imPayrollBajuDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollBajuDinasEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollBajuDinasDao.updateAndSave(imPayrollBajuDinasEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollBajuDinasBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollBajuDinas, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[PayrollBajuDinasBoImpl.saveDelete] Error, not found data PayrollBajuDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollBajuDinas with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollBajuDinasBoImpl.saveDelete] end process <<<");
    }


    @Override
    public void saveEdit(PayrollBajuDinas bean) throws GeneralBOException {
        logger.info("[PayrollBajuDinasBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String historyId = "";
            String payrollBajuDinasId = bean.getPakaianDinasId();

            ImPayrollBajuDinasEntity imPayrollBajuDinasEntity = null;
            //ImPayrollBajuDinasHistoryEntity imPayrollBajuDinasHistoryEntity = new ImPayrollBajuDinasHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollBajuDinasEntity = payrollBajuDinasDao.getById("pakaianDinasId", payrollBajuDinasId);
                //historyId = departmentDao.getNextPayrollBajuDinasHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollBajuDinasBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollBajuDinas by Kode PayrollBajuDinas, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollBajuDinasEntity != null) {
                /*imPayrollBajuDinasHistoryEntity.setId(historyId);
                imPayrollBajuDinasHistoryEntity.setPayrollBajuDinasId(imPayrollBajuDinasEntity.getPayrollBajuDinasId());
                imPayrollBajuDinasHistoryEntity.setPayrollBajuDinasName(imPayrollBajuDinasEntity.getPayrollBajuDinasName());
                imPayrollBajuDinasHistoryEntity.setFlag(imPayrollBajuDinasEntity.getFlag());
                imPayrollBajuDinasHistoryEntity.setAction(imPayrollBajuDinasEntity.getAction());
                imPayrollBajuDinasHistoryEntity.setLastUpdateWho(imPayrollBajuDinasEntity.getLastUpdateWho());
                imPayrollBajuDinasHistoryEntity.setLastUpdate(imPayrollBajuDinasEntity.getLastUpdate());
                imPayrollBajuDinasHistoryEntity.setCreatedWho(imPayrollBajuDinasEntity.getLastUpdateWho());
                imPayrollBajuDinasHistoryEntity.setCreatedDate(imPayrollBajuDinasEntity.getLastUpdate());*/

                imPayrollBajuDinasEntity.setPakaianDinasId(bean.getPakaianDinasId());
                imPayrollBajuDinasEntity.setBranchId(bean.getBranchId());
                imPayrollBajuDinasEntity.setGender(bean.getGender());
                imPayrollBajuDinasEntity.setNilai(bean.getNilai());
                imPayrollBajuDinasEntity.setFlag(bean.getFlag());
                imPayrollBajuDinasEntity.setAction(bean.getAction());
                imPayrollBajuDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollBajuDinasEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollBajuDinasDao.updateAndSave(imPayrollBajuDinasEntity);
                    //payrollBajuDinasDao.addAndSaveHistory(imPayrollBajuDinasHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollBajuDinasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollBajuDinas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollBajuDinasBoImpl.saveEdit] Error, not found data PayrollBajuDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollBajuDinas with request id, please check again your data ...");
//                condition = "Error, not found data PayrollBajuDinas with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollBajuDinasBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollBajuDinas saveAdd(PayrollBajuDinas bean) throws GeneralBOException {
        logger.info("[PayrollBajuDinasBoImpl.saveAdd] start process >>>");
        if (bean!=null) {

            String bajuDinasId;
            try {
                // Generating ID, get from postgre sequence
                bajuDinasId = payrollBajuDinasDao.getNextBajuDinasId();
            } catch (HibernateException e) {
                logger.error("[PayrollBajuDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollBajuDinasId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollBajuDinasEntity imPayrollBajuDinasEntity = new ImPayrollBajuDinasEntity();

            imPayrollBajuDinasEntity.setPakaianDinasId(bajuDinasId);
            imPayrollBajuDinasEntity.setBranchId(bean.getBranchId());
            imPayrollBajuDinasEntity.setNilai(bean.getNilai());
            imPayrollBajuDinasEntity.setGender(bean.getGender());
            imPayrollBajuDinasEntity.setFlag(bean.getFlag());
            imPayrollBajuDinasEntity.setAction(bean.getAction());
            imPayrollBajuDinasEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollBajuDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollBajuDinasEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollBajuDinasEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollBajuDinasDao.addAndSave(imPayrollBajuDinasEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollBajuDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollBajuDinas, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollBajuDinasBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollBajuDinas> getByCriteria(PayrollBajuDinas searchBean) throws GeneralBOException {
        logger.info("[PayrollBajuDinasBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollBajuDinas> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPakaianDinasId() != null && !"".equalsIgnoreCase(searchBean.getPakaianDinasId())) {
                hsCriteria.put("pakaian_dinas_id", searchBean.getPakaianDinasId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }

            if (searchBean.getGender() != null && !"".equalsIgnoreCase(searchBean.getGender())) {
                hsCriteria.put("gender", searchBean.getGender());
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


            List<ImPayrollBajuDinasEntity> imPayrollBajuDinasEntity = null;
            try {

                imPayrollBajuDinasEntity = payrollBajuDinasDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollBajuDinasBoImpl.getSearchPayrollBajuDinasByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollBajuDinasEntity != null){
                PayrollBajuDinas returnPayrollBajuDinas;
                // Looping from dao to object and save in collection
                for(ImPayrollBajuDinasEntity imPayrollBajuDinasEntity1 : imPayrollBajuDinasEntity){
                    returnPayrollBajuDinas = new PayrollBajuDinas();
                    returnPayrollBajuDinas.setPakaianDinasId(imPayrollBajuDinasEntity1.getPakaianDinasId());
                    returnPayrollBajuDinas.setBranchId(imPayrollBajuDinasEntity1.getBranchId());
                    if(imPayrollBajuDinasEntity1.getBranchId() != null){
                        returnPayrollBajuDinas.setBranchName(imPayrollBajuDinasEntity1.getImBranches().getBranchName());
                    }
                    returnPayrollBajuDinas.setGender(imPayrollBajuDinasEntity1.getGender());
                    if(imPayrollBajuDinasEntity1.getGender().equalsIgnoreCase("L")){
                        returnPayrollBajuDinas.setGenderLabel("Laki - Laki");
                    }else{
                        returnPayrollBajuDinas.setGenderLabel("Perempuan");
                    }

                    returnPayrollBajuDinas.setNilai(imPayrollBajuDinasEntity1.getNilai());
                    returnPayrollBajuDinas.setCreatedWho(imPayrollBajuDinasEntity1.getCreatedWho());
                    returnPayrollBajuDinas.setCreatedDate(imPayrollBajuDinasEntity1.getCreatedDate());
                    returnPayrollBajuDinas.setLastUpdate(imPayrollBajuDinasEntity1.getLastUpdate());

                    returnPayrollBajuDinas.setAction(imPayrollBajuDinasEntity1.getAction());
                    returnPayrollBajuDinas.setFlag(imPayrollBajuDinasEntity1.getFlag());
                    listOfResult.add(returnPayrollBajuDinas);
                }
            }
        }
        logger.info("[PayrollBajuDinasBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollBajuDinas> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PayrollBajuDinas> getComboSmkBudgetWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<PayrollBajuDinas> listComboSmkBudget = new ArrayList();
        return listComboSmkBudget;
    }

    @Override
    public List<PayrollBajuDinas> getCalculatePoint(double bobot, double target, double realisasi) throws GeneralBOException {
        return null;
    }
}
