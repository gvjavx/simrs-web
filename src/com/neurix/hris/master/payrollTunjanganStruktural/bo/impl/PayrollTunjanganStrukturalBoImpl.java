package com.neurix.hris.master.payrollTunjanganStruktural.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollTunjanganStruktural.bo.PayrollTunjanganStrukturalBo;
import com.neurix.hris.master.payrollTunjanganStruktural.dao.PayrollTunjanganStrukturalDao;
import com.neurix.hris.master.payrollTunjanganStruktural.model.ImPayrollTunjanganStrukturalEntity;
import com.neurix.hris.master.payrollTunjanganStruktural.model.payrollTunjanganStruktural;
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
public class PayrollTunjanganStrukturalBoImpl implements PayrollTunjanganStrukturalBo {

    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganStrukturalBoImpl.class);
    private PayrollTunjanganStrukturalDao PayrollTunjanganStrukturalDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganStrukturalBoImpl.logger = logger;
    }

    public PayrollTunjanganStrukturalDao getPayrollTunjanganStrukturalDao() {
        return PayrollTunjanganStrukturalDao;
    }


    public void setPayrollTunjanganStrukturalDao(PayrollTunjanganStrukturalDao PayrollTunjanganStrukturalDao) {
        this.PayrollTunjanganStrukturalDao = PayrollTunjanganStrukturalDao;
    }

    @Override
    public void saveDelete(payrollTunjanganStruktural bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String PayrollTunjanganStrukturalId = bean.getTunjStrukturId();

            ImPayrollTunjanganStrukturalEntity imPayrollTunjanganStrukturalEntity = null;

            try {
                // Get data from database by ID
                imPayrollTunjanganStrukturalEntity = PayrollTunjanganStrukturalDao.getById("tunjStrukturId", PayrollTunjanganStrukturalId);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganStrukturalEntity != null) {

                // Modify from bean to entity serializable
                imPayrollTunjanganStrukturalEntity.setTunjStrukturId(bean.getTunjStrukturId());
                imPayrollTunjanganStrukturalEntity.setFlag(bean.getFlag());
                imPayrollTunjanganStrukturalEntity.setAction(bean.getAction());
                imPayrollTunjanganStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganStrukturalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    PayrollTunjanganStrukturalDao.updateAndSave(imPayrollTunjanganStrukturalEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrukturalBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganStruktural, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveDelete] Error, not found data PayrollTunjanganStruktural with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganStruktural with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollTunjanganStrukturalBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(payrollTunjanganStruktural bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrukturalBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String PayrollTunjanganStrukturalId = bean.getTunjStrukturId();

            ImPayrollTunjanganStrukturalEntity imPayrollTunjanganStrukturalEntity = null;
            //ImPayrollTunjanganStrukturalHistoryEntity imPayrollTunjanganStrukturalHistoryEntity = new ImPayrollTunjanganStrukturalHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganStrukturalEntity = PayrollTunjanganStrukturalDao.getById("tunjStrukturId", PayrollTunjanganStrukturalId);
                //historyId = PayrollTunjanganStrukturalDao.getNextPayrollTunjanganStrukturalHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollTunjanganStruktural by Kode PayrollTunjanganStruktural, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganStrukturalEntity != null) {
                /*imPayrollTunjanganStrukturalHistoryEntity.setId(historyId);
                imPayrollTunjanganStrukturalHistoryEntity.setPayrollTunjanganStrukturalId(imPayrollTunjanganStrukturalEntity.getPayrollTunjanganStrukturalId());
                imPayrollTunjanganStrukturalHistoryEntity.setPayrollTunjanganStrukturalName(imPayrollTunjanganStrukturalEntity.getPayrollTunjanganStrukturalName());
                imPayrollTunjanganStrukturalHistoryEntity.setFlag(imPayrollTunjanganStrukturalEntity.getFlag());
                imPayrollTunjanganStrukturalHistoryEntity.setAction(imPayrollTunjanganStrukturalEntity.getAction());
                imPayrollTunjanganStrukturalHistoryEntity.setLastUpdateWho(imPayrollTunjanganStrukturalEntity.getLastUpdateWho());
                imPayrollTunjanganStrukturalHistoryEntity.setLastUpdate(imPayrollTunjanganStrukturalEntity.getLastUpdate());
                imPayrollTunjanganStrukturalHistoryEntity.setCreatedWho(imPayrollTunjanganStrukturalEntity.getLastUpdateWho());
                imPayrollTunjanganStrukturalHistoryEntity.setCreatedDate(imPayrollTunjanganStrukturalEntity.getLastUpdate());*/

                imPayrollTunjanganStrukturalEntity.setTunjStrukturId(bean.getTunjStrukturId());
                imPayrollTunjanganStrukturalEntity.setGolonganId(bean.getGolonganId());

                imPayrollTunjanganStrukturalEntity.setTahun(bean.getTahun());
                imPayrollTunjanganStrukturalEntity.setNilai(bean.getNilai());
                imPayrollTunjanganStrukturalEntity.setFlag(bean.getFlag());
                imPayrollTunjanganStrukturalEntity.setAction(bean.getAction());
                imPayrollTunjanganStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganStrukturalEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    PayrollTunjanganStrukturalDao.updateAndSave(imPayrollTunjanganStrukturalEntity);
                    //PayrollTunjanganStrukturalDao.addAndSaveHistory(imPayrollTunjanganStrukturalHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrukturalBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganStruktural, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveEdit] Error, not found data PayrollTunjanganStruktural with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganStruktural with request id, please check again your data ...");
//                condition = "Error, not found data PayrollTunjanganStruktural with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollTunjanganStrukturalBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollTunjanganStruktural saveAdd(payrollTunjanganStruktural bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrukturalBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String PayrollTunjanganStrukturalId;
            try {
                // Generating ID, get from postgre sequence
                PayrollTunjanganStrukturalId = PayrollTunjanganStrukturalDao.getNextStruktural();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence PayrollTunjanganStrukturalId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollTunjanganStrukturalEntity imPayrollTunjanganStrukturalEntity = new ImPayrollTunjanganStrukturalEntity();

            imPayrollTunjanganStrukturalEntity.setTunjStrukturId(PayrollTunjanganStrukturalId);
            imPayrollTunjanganStrukturalEntity.setGolonganId(bean.getGolonganId());

            imPayrollTunjanganStrukturalEntity.setTahun(bean.getTahun());
            imPayrollTunjanganStrukturalEntity.setNilai(bean.getNilai());
            imPayrollTunjanganStrukturalEntity.setFlag(bean.getFlag());
            imPayrollTunjanganStrukturalEntity.setAction(bean.getAction());
            imPayrollTunjanganStrukturalEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollTunjanganStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollTunjanganStrukturalEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollTunjanganStrukturalEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                PayrollTunjanganStrukturalDao.addAndSave(imPayrollTunjanganStrukturalEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollTunjanganStruktural, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollTunjanganStrukturalBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollTunjanganStruktural> getByCriteria(payrollTunjanganStruktural searchBean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrukturalBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollTunjanganStruktural> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjStrukturId() != null && !"".equalsIgnoreCase(searchBean.getTunjStrukturId())) {
                hsCriteria.put("tunjangan_umk_id", searchBean.getTunjStrukturId());
            }

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }

            if (searchBean.getTahun() != null && !"".equalsIgnoreCase(searchBean.getTahun())) {
                hsCriteria.put("tahun", searchBean.getTahun());
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


            List<ImPayrollTunjanganStrukturalEntity> imPayrollTunjanganStrukturalEntity = null;
            try {

                imPayrollTunjanganStrukturalEntity = PayrollTunjanganStrukturalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrukturalBoImpl.getSearchPayrollTunjanganStrukturalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganStrukturalEntity != null){
                payrollTunjanganStruktural returnPayrollTunjanganStruktural;
                // Looping from dao to object and save in collection
                for(ImPayrollTunjanganStrukturalEntity PayrollTunjanganStrukturalEntity : imPayrollTunjanganStrukturalEntity){
                    returnPayrollTunjanganStruktural = new payrollTunjanganStruktural();
                    returnPayrollTunjanganStruktural.setTunjStrukturId(PayrollTunjanganStrukturalEntity.getTunjStrukturId());

                    returnPayrollTunjanganStruktural.setGolonganId(PayrollTunjanganStrukturalEntity.getGolonganId());
                    if(PayrollTunjanganStrukturalEntity.getGolonganId() != null){
                        returnPayrollTunjanganStruktural.setGolonganName(PayrollTunjanganStrukturalEntity.getImGolonganEntity().getGolonganName());
                    }

                    returnPayrollTunjanganStruktural.setNilai(PayrollTunjanganStrukturalEntity.getNilai());
                    returnPayrollTunjanganStruktural.setTahun(PayrollTunjanganStrukturalEntity.getTahun());
                    returnPayrollTunjanganStruktural.setCreatedWho(PayrollTunjanganStrukturalEntity.getCreatedWho());
                    returnPayrollTunjanganStruktural.setCreatedDate(PayrollTunjanganStrukturalEntity.getCreatedDate());
                    returnPayrollTunjanganStruktural.setLastUpdate(PayrollTunjanganStrukturalEntity.getLastUpdate());

                    returnPayrollTunjanganStruktural.setAction(PayrollTunjanganStrukturalEntity.getAction());
                    returnPayrollTunjanganStruktural.setFlag(PayrollTunjanganStrukturalEntity.getFlag());
                    listOfResult.add(returnPayrollTunjanganStruktural);
                }
            }
        }
        logger.info("[PayrollTunjanganStrukturalBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<payrollTunjanganStruktural> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
