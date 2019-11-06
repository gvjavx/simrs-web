package com.neurix.hris.master.tipeAspek.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipeAspek.bo.TipeAspekBo;
import com.neurix.hris.master.tipeAspek.dao.TipeAspekDao;
import com.neurix.hris.master.tipeAspek.model.TipeAspek;
import com.neurix.hris.master.tipeAspek.model.ImTipeAspekEntity;
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
public class TipeAspekBoImpl implements TipeAspekBo {

    protected static transient Logger logger = Logger.getLogger(TipeAspekBoImpl.class);
    private TipeAspekDao tipeAspekDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeAspekBoImpl.logger = logger;
    }

    public TipeAspekDao getTipeAspekDao() {
        return tipeAspekDao;
    }

    public void setTipeAspekDao(TipeAspekDao tipeAspekDao) {
        this.tipeAspekDao = tipeAspekDao;
    }

    @Override
    public void saveDelete(TipeAspek bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String tipeAspekId = bean.getTipeAspekId();

            ImTipeAspekEntity imTipeAspekEntity = null;

            try {
                // Get data from database by ID
                imTipeAspekEntity = tipeAspekDao.getById("tipeAspekId", tipeAspekId);
            } catch (HibernateException e) {
                logger.error("[TipeAspekBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTipeAspekEntity != null) {

                // Modify from bean to entity serializable
                imTipeAspekEntity.setTipeAspekId(bean.getTipeAspekId());
                imTipeAspekEntity.setTipeAspekName(bean.getTipeAspekName());
                imTipeAspekEntity.setFlag(bean.getFlag());
                imTipeAspekEntity.setAction(bean.getAction());
                imTipeAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeAspekEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    tipeAspekDao.updateAndSave(imTipeAspekEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeAspekBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeAspek, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[TipeAspekBoImpl.saveDelete] Error, not found data TipeAspek with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeAspek with request id, please check again your data ...");

            }
        }
        logger.info("[TipeAspekBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipeAspek bean) throws GeneralBOException {
        logger.info("[TipeAspekBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String tipeAspekId = bean.getTipeAspekId();

            ImTipeAspekEntity imTipeAspekEntity = null;

            try {
                // Get data from database by ID
                imTipeAspekEntity = tipeAspekDao.getById("tipeAspekId", tipeAspekId);

            } catch (HibernateException e) {
                logger.error("[TipeAspekBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data TipeAspek by Kode TipeAspek, please inform to your admin...," + e.getMessage());
            }

            if (imTipeAspekEntity != null) {


                imTipeAspekEntity.setTipeAspekId(bean.getTipeAspekId());
                imTipeAspekEntity.setTipeAspekName(bean.getTipeAspekName());
                imTipeAspekEntity.setFlag(bean.getFlag());
                imTipeAspekEntity.setAction(bean.getAction());
                imTipeAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeAspekEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    tipeAspekDao.updateAndSave(imTipeAspekEntity);

//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[TipeAspekBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeAspek, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipeAspekBoImpl.saveEdit] Error, not found data TipeAspek with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeAspek with request id, please check again your data ...");
//                condition = "Error, not found data TipeAspek with request id, please check again your data ...";
            }
        }
        logger.info("[TipeAspekBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeAspek saveAdd(TipeAspek bean) throws GeneralBOException {
        logger.info("[TipeAspekBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String tipeAspekId;
            try {
                // Generating ID, get from postgre sequence
                //tipeAspekId = tipeAspekDao.getNextTipeAspekId();
            } catch (HibernateException e) {
                logger.error("[TipeAspekBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipeAspekId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImTipeAspekEntity imTipeAspekEntity = new ImTipeAspekEntity();

            imTipeAspekEntity.setTipeAspekId(bean.getTipeAspekId());
            imTipeAspekEntity.setTipeAspekName(bean.getTipeAspekName());
            imTipeAspekEntity.setFlag(bean.getFlag());
            imTipeAspekEntity.setAction(bean.getAction());
            imTipeAspekEntity.setCreatedWho(bean.getCreatedWho());
            imTipeAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTipeAspekEntity.setCreatedDate(bean.getCreatedDate());
            imTipeAspekEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tipeAspekDao.addAndSave(imTipeAspekEntity);
            } catch (HibernateException e) {
                logger.error("[TipeAspekBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data TipeAspek, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipeAspekBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeAspek> getByCriteria(TipeAspek searchBean) throws GeneralBOException {
        logger.info("[TipeAspekBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeAspek> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeAspekId() != null && !"".equalsIgnoreCase(searchBean.getTipeAspekId())) {
                hsCriteria.put("tipe_aspek_id", searchBean.getTipeAspekId());
            }
            if (searchBean.getTipeAspekName() != null && !"".equalsIgnoreCase(searchBean.getTipeAspekName())) {
                hsCriteria.put("tipe_aspek_name", searchBean.getTipeAspekName());
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


            List<ImTipeAspekEntity> imTipeAspekEntity = null;
            try {

                imTipeAspekEntity = tipeAspekDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipeAspekBoImpl.getSearchTipeAspekByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTipeAspekEntity != null){
                TipeAspek returnTipeAspek;
                // Looping from dao to object and save in collection
                for(ImTipeAspekEntity tipeAspekEntity : imTipeAspekEntity){
                    returnTipeAspek = new TipeAspek();
                    returnTipeAspek.setTipeAspekId(tipeAspekEntity.getTipeAspekId());
                    returnTipeAspek.setTipeAspekName(tipeAspekEntity.getTipeAspekName());

                    returnTipeAspek.setCreatedWho(tipeAspekEntity.getCreatedWho());
                    returnTipeAspek.setCreatedDate(tipeAspekEntity.getCreatedDate());
                    returnTipeAspek.setLastUpdate(tipeAspekEntity.getLastUpdate());

                    returnTipeAspek.setAction(tipeAspekEntity.getAction());
                    returnTipeAspek.setFlag(tipeAspekEntity.getFlag());
                    listOfResult.add(returnTipeAspek);
                }
            }
        }
        logger.info("[TipeAspekBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeAspek> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<TipeAspek> getComboTipeAspekWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<TipeAspek> listComboTipeAspek = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImTipeAspekEntity> listTipeAspek = null;
        try {
            listTipeAspek = tipeAspekDao.getListTipeAspek(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listTipeAspek != null) {
            for (ImTipeAspekEntity imTipeAspekEntity : listTipeAspek) {
                TipeAspek itemComboTipeAspek = new TipeAspek();
                itemComboTipeAspek.setTipeAspekId(imTipeAspekEntity.getTipeAspekId());
                itemComboTipeAspek.setTipeAspekName(imTipeAspekEntity.getTipeAspekName());
                listComboTipeAspek.add(itemComboTipeAspek);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboTipeAspek;
    }
}
