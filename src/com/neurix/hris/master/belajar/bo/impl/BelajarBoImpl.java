package com.neurix.hris.master.belajar.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.belajar.bo.BelajarBo;
import com.neurix.hris.master.belajar.dao.BelajarDao;
import com.neurix.hris.master.belajar.model.Belajar;
import com.neurix.hris.master.belajar.model.ImBelajarEntity;

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
public class BelajarBoImpl implements BelajarBo {

    protected static transient Logger logger = Logger.getLogger(BelajarBoImpl.class);
    private BelajarDao belajarDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BelajarBoImpl.logger = logger;
    }

    public BelajarDao getBelajarDao() {
        return belajarDao;
    }

    public void setBelajarDao(BelajarDao belajarDao) {
        this.belajarDao = belajarDao;
    }

    @Override
    public void saveDelete(Belajar bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String belajarId = bean.getBelajarId();

            ImBelajarEntity imBelajarEntity = null;

            try {
                // Get data from database by ID
                imBelajarEntity = belajarDao.getById("belajarId", belajarId);
            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imBelajarEntity != null) {

                // Modify from bean to entity serializable
                imBelajarEntity.setBelajarId(bean.getBelajarId());
                imBelajarEntity.setBelajarName(bean.getBelajarName());
                imBelajarEntity.setFlag(bean.getFlag());
                imBelajarEntity.setAction(bean.getAction());
                imBelajarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBelajarEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    belajarDao.updateAndSave(imBelajarEntity);
                } catch (HibernateException e) {
                    logger.error("[BelajarBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Belajar, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[BelajarBoImpl.saveDelete] Error, not found data Belajar with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Belajar with request id, please check again your data ...");

            }
        }
        logger.info("[BelajarBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Belajar bean) throws GeneralBOException {
        logger.info("[BelajarBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String belajarId = bean.getBelajarId();

            ImBelajarEntity imBelajarEntity = null;

            try {
                // Get data from database by ID
                imBelajarEntity = belajarDao.getById("belajarId", belajarId);

            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Belajar by Kode Belajar, please inform to your admin...," + e.getMessage());
            }

            if (imBelajarEntity != null) {


                imBelajarEntity.setBelajarId(bean.getBelajarId());
                imBelajarEntity.setBelajarName(bean.getBelajarName());
                imBelajarEntity.setFlag(bean.getFlag());
                imBelajarEntity.setAction(bean.getAction());
                imBelajarEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBelajarEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    belajarDao.updateAndSave(imBelajarEntity);

//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[BelajarBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Belajar, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BelajarBoImpl.saveEdit] Error, not found data Belajar with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Belajar with request id, please check again your data ...");
//                condition = "Error, not found data Belajar with request id, please check again your data ...";
            }
        }
        logger.info("[BelajarBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Belajar saveAdd(Belajar bean) throws GeneralBOException {
        logger.info("[BelajarBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String belajarId;
            try {
                // Generating ID, get from postgre sequence
                belajarId = belajarDao.getNextBelajarId();
            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence belajarId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImBelajarEntity imBelajarEntity = new ImBelajarEntity();

            imBelajarEntity.setBelajarId(belajarId);
            imBelajarEntity.setBelajarName(bean.getBelajarName());
            imBelajarEntity.setFlag(bean.getFlag());
            imBelajarEntity.setAction(bean.getAction());
            imBelajarEntity.setCreatedWho(bean.getCreatedWho());
            imBelajarEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imBelajarEntity.setCreatedDate(bean.getCreatedDate());
            imBelajarEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                belajarDao.addAndSave(imBelajarEntity);
            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Belajar, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BelajarBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Belajar> getByCriteria(Belajar searchBean) throws GeneralBOException {
        logger.info("[BelajarBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Belajar> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBelajarId() != null && !"".equalsIgnoreCase(searchBean.getBelajarId())) {
                hsCriteria.put("belajar_id", searchBean.getBelajarId());
            }
            if (searchBean.getBelajarName() != null && !"".equalsIgnoreCase(searchBean.getBelajarName())) {
                hsCriteria.put("belajar_name", searchBean.getBelajarName());
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


            List<ImBelajarEntity> imBelajarEntity = null;
            try {

                imBelajarEntity = belajarDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BelajarBoImpl.getSearchBelajarByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imBelajarEntity != null){
                Belajar returnBelajar;
                // Looping from dao to object and save in collection
                for(ImBelajarEntity belajarEntity : imBelajarEntity){
                    returnBelajar = new Belajar();
                    returnBelajar.setBelajarId(belajarEntity.getBelajarId());
                    returnBelajar.setBelajarName(belajarEntity.getBelajarName());

                    returnBelajar.setCreatedWho(belajarEntity.getCreatedWho());
                    returnBelajar.setCreatedDate(belajarEntity.getCreatedDate());
                    returnBelajar.setLastUpdate(belajarEntity.getLastUpdate());

                    returnBelajar.setAction(belajarEntity.getAction());
                    returnBelajar.setFlag(belajarEntity.getFlag());
                    listOfResult.add(returnBelajar);
                }
            }
        }
        logger.info("[BelajarBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Belajar> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Belajar> getComboBelajarWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Belajar> listComboBelajar = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBelajarEntity> listBelajar = null;
        try {
            listBelajar = belajarDao.getListBelajar(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listBelajar != null) {
            for (ImBelajarEntity imBelajarEntity : listBelajar) {
                Belajar itemComboBelajar = new Belajar();
                itemComboBelajar.setBelajarId(imBelajarEntity.getBelajarId());
                itemComboBelajar.setBelajarName(imBelajarEntity.getBelajarName());
                listComboBelajar.add(itemComboBelajar);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBelajar;
    }
}
