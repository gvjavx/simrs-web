package com.neurix.hris.master.profesi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.profesi.bo.ProfesiBo;
import com.neurix.hris.master.profesi.dao.ProfesiDao;
import com.neurix.hris.master.profesi.model.ImProfesiHistoryEntity;
import com.neurix.hris.master.profesi.model.Profesi;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
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
public class ProfesiBoImpl implements ProfesiBo {

    protected static transient Logger logger = Logger.getLogger(ProfesiBoImpl.class);
    private ProfesiDao profesiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ProfesiBoImpl.logger = logger;
    }

    public ProfesiDao getProfesiDao() {
        return profesiDao;
    }


    public void setProfesiDao(ProfesiDao profesiDao) {
        this.profesiDao = profesiDao;
    }

    @Override
    public void saveDelete(Profesi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String profesiId = bean.getProfesiId();
            String profesiHistoryId = "";
            ImProfesiEntity imProfesiEntity = null;
            ImProfesiHistoryEntity historyEntity = new ImProfesiHistoryEntity();
            try {
                // Get data from database by ID
                imProfesiEntity = profesiDao.getById("profesiId", profesiId);
                profesiHistoryId = profesiDao.getNextProfesiHistoryId();
            } catch (HibernateException e) {
                logger.error("[ProfesiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imProfesiEntity != null) {
                historyEntity.setIdHistoryProfesi(profesiHistoryId);
                historyEntity.setProfesiId(imProfesiEntity.getProfesiId());
                historyEntity.setProfesiName(imProfesiEntity.getProfesiName());
                historyEntity.setCreatedDate(imProfesiEntity.getCreatedDate());
                historyEntity.setCreatedWho(imProfesiEntity.getCreatedWho());
                historyEntity.setLastUpdate(imProfesiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imProfesiEntity.getLastUpdateWho());

                // Modify from bean to entity serializable
                imProfesiEntity.setProfesiId(bean.getProfesiId());
                imProfesiEntity.setProfesiName(bean.getProfesiName());
                imProfesiEntity.setFlag(bean.getFlag());
                imProfesiEntity.setAction(bean.getAction());
                imProfesiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imProfesiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    profesiDao.updateAndSave(imProfesiEntity);
                    profesiDao.addAndSaveHistory(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[ProfesiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Profesi, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[ProfesiBoImpl.saveDelete] Error, not found data Profesi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Profesi with request id, please check again your data ...");

            }
        }
        logger.info("[ProfesiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Profesi bean) throws GeneralBOException {
        logger.info("[ProfesiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String status = cekStatus(bean.getProfesiName());
            if (!status.equalsIgnoreCase("exist")){
                String historyId = "";
                String profesiId = bean.getProfesiId();

                ImProfesiEntity imProfesiEntity = null;
                ImProfesiHistoryEntity historyEntity = new ImProfesiHistoryEntity();
                try {
                    // Get data from database by ID
                    imProfesiEntity = profesiDao.getById("profesiId", profesiId);
                    historyId = profesiDao.getNextProfesiHistoryId();
                } catch (HibernateException e) {
                    logger.error("[ProfesiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Profesi by Kode Profesi, please inform to your admin...," + e.getMessage());
                }

                if (imProfesiEntity != null) {
                    historyEntity.setIdHistoryProfesi(historyId);
                    historyEntity.setProfesiId(imProfesiEntity.getProfesiId());
                    historyEntity.setProfesiName(imProfesiEntity.getProfesiName());
                    historyEntity.setCreatedDate(imProfesiEntity.getCreatedDate());
                    historyEntity.setCreatedWho(imProfesiEntity.getCreatedWho());
                    historyEntity.setLastUpdate(imProfesiEntity.getLastUpdate());
                    historyEntity.setLastUpdateWho(imProfesiEntity.getLastUpdateWho());

                    imProfesiEntity.setProfesiId(bean.getProfesiId());
                    imProfesiEntity.setProfesiName(bean.getProfesiName());
                    imProfesiEntity.setFlag(bean.getFlag());
                    imProfesiEntity.setAction(bean.getAction());
                    imProfesiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imProfesiEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        profesiDao.updateAndSave(imProfesiEntity);
                        profesiDao.addAndSaveHistory(historyEntity);
//                    condition = "Data SuccessFully Updated";
                    } catch (HibernateException e) {
                        logger.error("[ProfesiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Profesi, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[ProfesiBoImpl.saveEdit] Error, not found data Profesi with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Profesi with request id, please check again your data ...");
//                condition = "Error, not found data Profesi with request id, please check again your data ...";
                }
            }else {
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }
        }
        logger.info("[ProfesiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Profesi saveAdd(Profesi bean) throws GeneralBOException {
        logger.info("[ProfesiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getProfesiName());
            if (!status.equalsIgnoreCase("Exist")){
                String profesiId;
                try {
                    // Generating ID, get from postgre sequence
                    profesiId = profesiDao.getNextProfesiId();
                } catch (HibernateException e) {
                    logger.error("[ProfesiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence profesiId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImProfesiEntity imProfesiEntity = new ImProfesiEntity();

                imProfesiEntity.setProfesiId(profesiId);
                imProfesiEntity.setProfesiName(bean.getProfesiName());
                imProfesiEntity.setFlag(bean.getFlag());
                imProfesiEntity.setAction(bean.getAction());
                imProfesiEntity.setCreatedWho(bean.getCreatedWho());
                imProfesiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imProfesiEntity.setCreatedDate(bean.getCreatedDate());
                imProfesiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    profesiDao.addAndSave(imProfesiEntity);
                } catch (HibernateException e) {
                    logger.error("[ProfesiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Profesi, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }
        }

        logger.info("[ProfesiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Profesi> getByCriteria(Profesi searchBean) throws GeneralBOException {
        logger.info("[ProfesiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Profesi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getProfesiId() != null && !"".equalsIgnoreCase(searchBean.getProfesiId())) {
                hsCriteria.put("profesi_id", searchBean.getProfesiId());
            }
            if (searchBean.getProfesiName() != null && !"".equalsIgnoreCase(searchBean.getProfesiName())) {
                hsCriteria.put("profesi_name", searchBean.getProfesiName());
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


            List<ImProfesiEntity> imProfesiEntity = null;
            try {

                imProfesiEntity = profesiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ProfesiBoImpl.getSearchProfesiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imProfesiEntity != null){
                Profesi returnProfesi;
                // Looping from dao to object and save in collection
                for(ImProfesiEntity profesiEntity : imProfesiEntity){
                    returnProfesi = new Profesi();
                    returnProfesi.setProfesiId(profesiEntity.getProfesiId());
                    returnProfesi.setProfesiName(profesiEntity.getProfesiName());

                    returnProfesi.setCreatedWho(profesiEntity.getCreatedWho());
                    returnProfesi.setCreatedDate(profesiEntity.getCreatedDate());
                    returnProfesi.setLastUpdate(profesiEntity.getLastUpdate());
                    returnProfesi.setLastUpdateWho(profesiEntity.getLastUpdateWho());

                    returnProfesi.setAction(profesiEntity.getAction());
                    returnProfesi.setFlag(profesiEntity.getFlag());
                    listOfResult.add(returnProfesi);
                }
            }
        }
        logger.info("[ProfesiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Profesi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Profesi> getComboProfesiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Profesi> listComboProfesi = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImProfesiEntity> listProfesi = null;
        try {
            listProfesi = profesiDao.getListProfesi(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listProfesi != null) {
            for (ImProfesiEntity imProfesiEntity : listProfesi) {
                Profesi itemComboProfesi = new Profesi();
                itemComboProfesi.setProfesiId(imProfesiEntity.getProfesiId());
                itemComboProfesi.setProfesiName(imProfesiEntity.getProfesiName());
                listComboProfesi.add(itemComboProfesi);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboProfesi;
    }
    public String cekStatus(String profesiName)throws GeneralBOException{
        String status ="";
        List<ImProfesiEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = profesiDao.getListProfesi(profesiName);
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
