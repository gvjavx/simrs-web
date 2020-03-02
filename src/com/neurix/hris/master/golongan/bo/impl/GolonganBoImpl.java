package com.neurix.hris.master.golongan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.golongan.bo.GolonganBo;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golongan.model.ImGolonganHistoryEntity;
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
public class GolonganBoImpl implements GolonganBo {

    protected static transient Logger logger = Logger.getLogger(GolonganBoImpl.class);
    private GolonganDao golonganDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganBoImpl.logger = logger;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
    }

    @Override
    public void saveDelete(Golongan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String golonganId = bean.getGolonganId();

            ImGolonganEntity imGolonganEntity = null;

            try {
                // Get data from database by ID
                imGolonganEntity = golonganDao.getById("golonganId", golonganId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {

                // Modify from bean to entity serializable
                imGolonganEntity.setGolonganId(bean.getGolonganId());
                imGolonganEntity.setGolonganName(bean.getGolonganName());
                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    golonganDao.updateAndSave(imGolonganEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganBoImpl.saveDelete] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");

            }
        }
        logger.info("[GolonganBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Golongan bean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String golonganId = bean.getGolonganId();
            String historyId = "";

            ImGolonganEntity imGolonganEntity = null;
            ImGolonganHistoryEntity imGolonganHistoryEntity = new ImGolonganHistoryEntity();

            try {
                // Get data from database by ID
                imGolonganEntity = golonganDao.getById("golonganId", golonganId);
                historyId = golonganDao.getNextGolonganHistoryId();
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Golongan by Kode Golongan, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {
                //
                imGolonganHistoryEntity.setId(historyId);
                imGolonganHistoryEntity.setGolonganId(imGolonganEntity.getGolonganId());
                imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                imGolonganHistoryEntity.setFlag(imGolonganEntity.getFlag());
                imGolonganHistoryEntity.setAction(imGolonganEntity.getAction());
                imGolonganHistoryEntity.setLastUpdateWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setLastUpdate(imGolonganEntity.getLastUpdate());
                imGolonganHistoryEntity.setCreatedWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setCreatedDate(imGolonganEntity.getLastUpdate());

                imGolonganEntity.setGolonganId(bean.getGolonganId());
                imGolonganEntity.setGolonganName(bean.getGolonganName());
                imGolonganEntity.setLevel(Integer.parseInt(bean.getStLevel()));
                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    golonganDao.updateAndSave(imGolonganEntity);
                    golonganDao.addAndSaveHistory(imGolonganHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[GolonganBoImpl.saveEdit] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");
//                condition = "Error, not found data Golongan with request id, please check again your data ...";
            }
        }
        logger.info("[GolonganBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Golongan saveAdd(Golongan bean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String golonganId;
            try {
                // Generating ID, get from postgre sequence
                golonganId = golonganDao.getNextGolonganId();
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImGolonganEntity imGolonganEntity = new ImGolonganEntity();

            imGolonganEntity.setGolonganId(golonganId);
            imGolonganEntity.setGolonganName(bean.getGolonganName());
            imGolonganEntity.setLevel(Integer.parseInt(bean.getStLevel()));
            imGolonganEntity.setFlag(bean.getFlag());
            imGolonganEntity.setAction(bean.getAction());
            imGolonganEntity.setCreatedWho(bean.getCreatedWho());
            imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imGolonganEntity.setCreatedDate(bean.getCreatedDate());
            imGolonganEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                golonganDao.addAndSave(imGolonganEntity);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[GolonganBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Golongan> getByCriteria(Golongan searchBean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Golongan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }
            if (searchBean.getGolonganName() != null && !"".equalsIgnoreCase(searchBean.getGolonganName())) {
                hsCriteria.put("golongan_name", searchBean.getGolonganName());
            }
            if (searchBean.getStLevel() != null && !"".equalsIgnoreCase(searchBean.getStLevel())) {
                hsCriteria.put("grade_level", Integer.parseInt(searchBean.getStLevel()));
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


            List<ImGolonganEntity> imGolonganEntity = null;
            try {

                imGolonganEntity = golonganDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.getSearchGolonganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganEntity != null){
                Golongan returnGolongan;
                // Looping from dao to object and save in collection
                for(ImGolonganEntity golonganEntity : imGolonganEntity){
                    returnGolongan = new Golongan();
                    returnGolongan.setGolonganId(golonganEntity.getGolonganId());
                    returnGolongan.setGolonganName(golonganEntity.getGolonganName());
                    returnGolongan.setLevel(golonganEntity.getLevel());
                    returnGolongan.setStLevel(golonganEntity.getLevel().toString());

                    returnGolongan.setCreatedWho(golonganEntity.getCreatedWho());
                    returnGolongan.setCreatedDate(golonganEntity.getCreatedDate());
                    returnGolongan.setLastUpdate(golonganEntity.getLastUpdate());

                    returnGolongan.setAction(golonganEntity.getAction());
                    returnGolongan.setFlag(golonganEntity.getFlag());
                    listOfResult.add(returnGolongan);
                }
            }
        }
        logger.info("[GolonganBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Golongan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Golongan> getComboGolonganWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Golongan> listComboGolongan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImGolonganEntity> listGolongan = null;
        try {
            listGolongan = golonganDao.getListGolongan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImGolonganEntity imGolonganEntity : listGolongan) {
                Golongan itemComboGolongan = new Golongan();
                itemComboGolongan.setGolonganId(imGolonganEntity.getGolonganId());
                itemComboGolongan.setGolonganName(imGolonganEntity.getGolonganName());
                listComboGolongan.add(itemComboGolongan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboGolongan;
    }
}
