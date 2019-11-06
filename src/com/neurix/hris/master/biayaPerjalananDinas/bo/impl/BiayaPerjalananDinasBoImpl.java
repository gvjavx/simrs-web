package com.neurix.hris.master.biayaPerjalananDinas.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biayaPerjalananDinas.bo.BiayaPerjalananDinasBo;
import com.neurix.hris.master.biayaPerjalananDinas.dao.BiayaPerjalananDinasDao;
import com.neurix.hris.master.biayaPerjalananDinas.model.BiayaPerjalananDinas;
import com.neurix.hris.master.biayaPerjalananDinas.model.ImBiayaPerjalananDinasEntity;
import com.neurix.hris.master.biayaPerjalananDinas.model.ImBiayaPerjalananDinasHistoryEntity;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasHistoryEntity;
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
public class BiayaPerjalananDinasBoImpl implements BiayaPerjalananDinasBo {

    protected static transient Logger logger = Logger.getLogger(BiayaPerjalananDinasBoImpl.class);
    private BiayaPerjalananDinasDao biayaPerjalananDinasDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiayaPerjalananDinasBoImpl.logger = logger;
    }

    public BiayaPerjalananDinasDao getBiayaPerjalananDinasDao() {
        return biayaPerjalananDinasDao;
    }

    public void setBiayaPerjalananDinasDao(BiayaPerjalananDinasDao biayaPerjalananDinasDao) {
        this.biayaPerjalananDinasDao = biayaPerjalananDinasDao;
    }

    @Override
    public void saveDelete(BiayaPerjalananDinas bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String biayaPerjalananDinasId = bean.getBiayaId();

            ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity = null;

            try {
                // Get data from database by ID
                imBiayaPerjalananDinasEntity = biayaPerjalananDinasDao.getById("biayaId", biayaPerjalananDinasId);
            } catch (HibernateException e) {
                logger.error("[BiayaPerjalananDinasBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imBiayaPerjalananDinasEntity != null) {

                // Modify from bean to entity serializable
                imBiayaPerjalananDinasEntity.setBiayaId(bean.getBiayaId());
                imBiayaPerjalananDinasEntity.setBiayaName(bean.getBiayaName());
                imBiayaPerjalananDinasEntity.setFlag(bean.getFlag());
                imBiayaPerjalananDinasEntity.setAction(bean.getAction());
                imBiayaPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiayaPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    biayaPerjalananDinasDao.updateAndSave(imBiayaPerjalananDinasEntity);
                } catch (HibernateException e) {
                    logger.error("[BiayaPerjalananDinasBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data BiayaPerjalananDinas, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[BiayaPerjalananDinasBoImpl.saveDelete] Error, not found data BiayaPerjalananDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data BiayaPerjalananDinas with request id, please check again your data ...");

            }
        }
        logger.info("[BiayaPerjalananDinasBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(BiayaPerjalananDinas bean) throws GeneralBOException {
        logger.info("[BiayaPerjalananDinasBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String biayaPerjalananDinasId = bean.getBiayaId();
            String idHistory = "";
            ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity = null;
            ImBiayaPerjalananDinasHistoryEntity imBiayaPerjalananDinasHistoryEntity = new ImBiayaPerjalananDinasHistoryEntity();
            try {
                // Get data from database by ID
                imBiayaPerjalananDinasEntity = biayaPerjalananDinasDao.getById("biayaId", biayaPerjalananDinasId);
                idHistory = biayaPerjalananDinasDao.getNextBiayaHistoryId();
            } catch (HibernateException e) {
                logger.error("[BiayaPerjalananDinasBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data BiayaPerjalananDinas by Kode BiayaPerjalananDinas, please inform to your admin...," + e.getMessage());
            }

            if (imBiayaPerjalananDinasEntity != null) {

                imBiayaPerjalananDinasHistoryEntity.setId(idHistory);
                imBiayaPerjalananDinasHistoryEntity.setBiayaId(imBiayaPerjalananDinasEntity.getBiayaId());
                imBiayaPerjalananDinasHistoryEntity.setBiayaName(imBiayaPerjalananDinasEntity.getBiayaName());
                imBiayaPerjalananDinasHistoryEntity.setFlag(imBiayaPerjalananDinasEntity.getFlag());
                imBiayaPerjalananDinasHistoryEntity.setAction(imBiayaPerjalananDinasEntity.getAction());
                imBiayaPerjalananDinasHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiayaPerjalananDinasHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imBiayaPerjalananDinasHistoryEntity.setCreatedWho(imBiayaPerjalananDinasEntity.getLastUpdateWho());
                imBiayaPerjalananDinasHistoryEntity.setCreatedDate(imBiayaPerjalananDinasEntity.getLastUpdate());

                imBiayaPerjalananDinasEntity.setBiayaId(bean.getBiayaId());
                imBiayaPerjalananDinasEntity.setBiayaName(bean.getBiayaName());
                imBiayaPerjalananDinasEntity.setFlag(bean.getFlag());
                imBiayaPerjalananDinasEntity.setAction(bean.getAction());
                imBiayaPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBiayaPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    biayaPerjalananDinasDao.updateAndSave(imBiayaPerjalananDinasEntity);
                    biayaPerjalananDinasDao.addAndSaveHistory(imBiayaPerjalananDinasHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[BiayaPerjalananDinasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data BiayaPerjalananDinas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BiayaPerjalananDinasBoImpl.saveEdit] Error, not found data BiayaPerjalananDinas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data BiayaPerjalananDinas with request id, please check again your data ...");
//                condition = "Error, not found data BiayaPerjalananDinas with request id, please check again your data ...";
            }
        }
        logger.info("[BiayaPerjalananDinasBoImpl.saveEdit] end process <<<");
    }

    @Override
    public BiayaPerjalananDinas saveAdd(BiayaPerjalananDinas bean) throws GeneralBOException {
        logger.info("[BiayaPerjalananDinasBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String biayaPerjalananDinasId;
            try {
                // Generating ID, get from postgre sequence
                biayaPerjalananDinasId = biayaPerjalananDinasDao.getNextBiayaId();
            } catch (HibernateException e) {
                logger.error("[BiayaPerjalananDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence biayaPerjalananDinasId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity = new ImBiayaPerjalananDinasEntity();

            imBiayaPerjalananDinasEntity.setBiayaId(biayaPerjalananDinasId);
            imBiayaPerjalananDinasEntity.setBiayaName(bean.getBiayaName());
            imBiayaPerjalananDinasEntity.setFlag(bean.getFlag());
            imBiayaPerjalananDinasEntity.setAction(bean.getAction());
            imBiayaPerjalananDinasEntity.setCreatedWho(bean.getCreatedWho());
            imBiayaPerjalananDinasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imBiayaPerjalananDinasEntity.setCreatedDate(bean.getCreatedDate());
            imBiayaPerjalananDinasEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                biayaPerjalananDinasDao.addAndSave(imBiayaPerjalananDinasEntity);
            } catch (HibernateException e) {
                logger.error("[BiayaPerjalananDinasBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data BiayaPerjalananDinas, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiayaPerjalananDinasBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<BiayaPerjalananDinas> getByCriteria(BiayaPerjalananDinas searchBean) throws GeneralBOException {
        logger.info("[BiayaPerjalananDinasBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<BiayaPerjalananDinas> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBiayaId() != null && !"".equalsIgnoreCase(searchBean.getBiayaId())) {
                hsCriteria.put("biaya_dinas_id", searchBean.getBiayaId());
            }
            if (searchBean.getBiayaName() != null && !"".equalsIgnoreCase(searchBean.getBiayaName())) {
                hsCriteria.put("biaya_name", searchBean.getBiayaName());
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


            List<ImBiayaPerjalananDinasEntity> imBiayaPerjalananDinasEntity = null;
            try {

                imBiayaPerjalananDinasEntity = biayaPerjalananDinasDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BiayaPerjalananDinasBoImpl.getSearchBiayaPerjalananDinasByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imBiayaPerjalananDinasEntity != null){
                BiayaPerjalananDinas returnBiayaPerjalananDinas;
                // Looping from dao to object and save in collection
                for(ImBiayaPerjalananDinasEntity biayaPerjalananDinasEntity : imBiayaPerjalananDinasEntity){
                    returnBiayaPerjalananDinas = new BiayaPerjalananDinas();
                    returnBiayaPerjalananDinas.setBiayaId(biayaPerjalananDinasEntity.getBiayaId());
                    returnBiayaPerjalananDinas.setBiayaName(biayaPerjalananDinasEntity.getBiayaName());

                    returnBiayaPerjalananDinas.setCreatedWho(biayaPerjalananDinasEntity.getCreatedWho());
                    returnBiayaPerjalananDinas.setCreatedDate(biayaPerjalananDinasEntity.getCreatedDate());
                    returnBiayaPerjalananDinas.setLastUpdate(biayaPerjalananDinasEntity.getLastUpdate());

                    returnBiayaPerjalananDinas.setAction(biayaPerjalananDinasEntity.getAction());
                    returnBiayaPerjalananDinas.setFlag(biayaPerjalananDinasEntity.getFlag());
                    listOfResult.add(returnBiayaPerjalananDinas);
                }
            }
        }
        logger.info("[BiayaPerjalananDinasBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<BiayaPerjalananDinas> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<BiayaPerjalananDinas> getComboBiayaWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<BiayaPerjalananDinas> listComboBiayaPerjalananDinas = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiayaPerjalananDinasEntity> listBiayaPerjalananDinas = null;
        try {
            listBiayaPerjalananDinas = biayaPerjalananDinasDao.getListBiayaPerjalananDinas(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listBiayaPerjalananDinas != null) {
            for (ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity : listBiayaPerjalananDinas) {
                BiayaPerjalananDinas itemComboBiayaPerjalananDinas = new BiayaPerjalananDinas();
                itemComboBiayaPerjalananDinas.setBiayaId(imBiayaPerjalananDinasEntity.getBiayaId());
                itemComboBiayaPerjalananDinas.setBiayaName(imBiayaPerjalananDinasEntity.getBiayaName());
                listComboBiayaPerjalananDinas.add(itemComboBiayaPerjalananDinas);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBiayaPerjalananDinas;
    }
}
