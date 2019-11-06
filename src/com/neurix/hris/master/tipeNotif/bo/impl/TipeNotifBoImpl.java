package com.neurix.hris.master.tipeNotif.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipeNotif.bo.TipeNotifBo;
import com.neurix.hris.master.tipeNotif.dao.TipeNotifDao;
import com.neurix.hris.master.tipeNotif.model.TipeNotif;
import com.neurix.hris.master.tipeNotif.model.ImTipeNotifEntity;

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
public class TipeNotifBoImpl implements TipeNotifBo {

    protected static transient Logger logger = Logger.getLogger(TipeNotifBoImpl.class);
    private TipeNotifDao tipeNotifDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeNotifBoImpl.logger = logger;
    }

    public TipeNotifDao getTipeNotifDao() {
        return tipeNotifDao;
    }

    public void setTipeNotifDao(TipeNotifDao tipeNotifDao) {
        this.tipeNotifDao = tipeNotifDao;
    }

    @Override
    public void saveDelete(TipeNotif bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String tipeNotifId = bean.getTipeNotifId();

            ImTipeNotifEntity imTipeNotifEntity = null;

            try {
                // Get data from database by ID
                imTipeNotifEntity = tipeNotifDao.getById("tipeNotifId", tipeNotifId);
            } catch (HibernateException e) {
                logger.error("[TipeNotifBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTipeNotifEntity != null) {

                // Modify from bean to entity serializable
                imTipeNotifEntity.setTipeNotifId(bean.getTipeNotifId());
                imTipeNotifEntity.setTipeNotifName(bean.getTipeNotifName());
                imTipeNotifEntity.setFlag(bean.getFlag());
                imTipeNotifEntity.setAction(bean.getAction());
                imTipeNotifEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeNotifEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    tipeNotifDao.updateAndSave(imTipeNotifEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeNotifBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeNotif, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[TipeNotifBoImpl.saveDelete] Error, not found data TipeNotif with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeNotif with request id, please check again your data ...");

            }
        }
        logger.info("[TipeNotifBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipeNotif bean) throws GeneralBOException {
        logger.info("[TipeNotifBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String tipeNotifId = bean.getTipeNotifId();

            ImTipeNotifEntity imTipeNotifEntity = null;

            try {
                // Get data from database by ID
                imTipeNotifEntity = tipeNotifDao.getById("tipeNotifId", tipeNotifId);

            } catch (HibernateException e) {
                logger.error("[TipeNotifBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data TipeNotif by Kode TipeNotif, please inform to your admin...," + e.getMessage());
            }

            if (imTipeNotifEntity != null) {


                imTipeNotifEntity.setTipeNotifId(bean.getTipeNotifId());
                imTipeNotifEntity.setTipeNotifName(bean.getTipeNotifName());
                imTipeNotifEntity.setFlag(bean.getFlag());
                imTipeNotifEntity.setAction(bean.getAction());
                imTipeNotifEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeNotifEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    tipeNotifDao.updateAndSave(imTipeNotifEntity);

//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[TipeNotifBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeNotif, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipeNotifBoImpl.saveEdit] Error, not found data TipeNotif with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeNotif with request id, please check again your data ...");
//                condition = "Error, not found data TipeNotif with request id, please check again your data ...";
            }
        }
        logger.info("[TipeNotifBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeNotif saveAdd(TipeNotif bean) throws GeneralBOException {
        logger.info("[TipeNotifBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String tipeNotifId;
            try {
                // Generating ID, get from postgre sequence
                tipeNotifId = tipeNotifDao.getNextTipeNotifId();
            } catch (HibernateException e) {
                logger.error("[TipeNotifBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipeNotifId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImTipeNotifEntity imTipeNotifEntity = new ImTipeNotifEntity();

            imTipeNotifEntity.setTipeNotifId(tipeNotifId);
            imTipeNotifEntity.setTipeNotifName(bean.getTipeNotifName());
            imTipeNotifEntity.setFlag(bean.getFlag());
            imTipeNotifEntity.setAction(bean.getAction());
            imTipeNotifEntity.setCreatedWho(bean.getCreatedWho());
            imTipeNotifEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTipeNotifEntity.setCreatedDate(bean.getCreatedDate());
            imTipeNotifEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tipeNotifDao.addAndSave(imTipeNotifEntity);
            } catch (HibernateException e) {
                logger.error("[TipeNotifBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data TipeNotif, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipeNotifBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeNotif> getByCriteria(TipeNotif searchBean) throws GeneralBOException {
        logger.info("[TipeNotifBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeNotif> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeNotifId() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifId())) {
                hsCriteria.put("tipeNotif_id", searchBean.getTipeNotifId());
            }
            if (searchBean.getTipeNotifName() != null && !"".equalsIgnoreCase(searchBean.getTipeNotifName())) {
                hsCriteria.put("tipeNotif_name", searchBean.getTipeNotifName());
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


            List<ImTipeNotifEntity> imTipeNotifEntity = null;
            try {

                imTipeNotifEntity = tipeNotifDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipeNotifBoImpl.getSearchTipeNotifByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTipeNotifEntity != null){
                TipeNotif returnTipeNotif;
                // Looping from dao to object and save in collection
                for(ImTipeNotifEntity tipeNotifEntity : imTipeNotifEntity){
                    returnTipeNotif = new TipeNotif();
                    returnTipeNotif.setTipeNotifId(tipeNotifEntity.getTipeNotifId());
                    returnTipeNotif.setTipeNotifName(tipeNotifEntity.getTipeNotifName());

                    returnTipeNotif.setCreatedWho(tipeNotifEntity.getCreatedWho());
                    returnTipeNotif.setCreatedDate(tipeNotifEntity.getCreatedDate());
                    returnTipeNotif.setLastUpdate(tipeNotifEntity.getLastUpdate());

                    returnTipeNotif.setAction(tipeNotifEntity.getAction());
                    returnTipeNotif.setFlag(tipeNotifEntity.getFlag());
                    listOfResult.add(returnTipeNotif);
                }
            }
        }
        logger.info("[TipeNotifBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeNotif> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<TipeNotif> getComboTipeNotifWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<TipeNotif> listComboTipeNotif = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImTipeNotifEntity> listTipeNotif = null;
        try {
            listTipeNotif = tipeNotifDao.getListTipeNotif(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listTipeNotif != null) {
            for (ImTipeNotifEntity imTipeNotifEntity : listTipeNotif) {
                TipeNotif itemComboTipeNotif = new TipeNotif();
                itemComboTipeNotif.setTipeNotifId(imTipeNotifEntity.getTipeNotifId());
                itemComboTipeNotif.setTipeNotifName(imTipeNotifEntity.getTipeNotifName());
                listComboTipeNotif.add(itemComboTipeNotif);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboTipeNotif;
    }
}
