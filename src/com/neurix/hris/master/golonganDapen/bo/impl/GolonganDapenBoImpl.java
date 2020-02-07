package com.neurix.hris.master.golonganDapen.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.golonganDapen.bo.GolonganDapenBo;
import com.neurix.hris.master.golonganDapen.dao.GolonganDapenDao;
import com.neurix.hris.master.golonganDapen.model.GolonganDapen;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
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
public class GolonganDapenBoImpl implements GolonganDapenBo {

    protected static transient Logger logger = Logger.getLogger(GolonganDapenBoImpl.class);
    private GolonganDapenDao golonganDapenDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganDapenBoImpl.logger = logger;
    }

    public GolonganDapenDao getGolonganDapenDao() {
        return golonganDapenDao;
    }

    public void setGolonganDapenDao(GolonganDapenDao golonganDapenDao) {
        this.golonganDapenDao = golonganDapenDao;
    }

    @Override
    public void saveDelete(GolonganDapen bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String golonganDapenId = bean.getGolonganDapenId();

            ImGolonganDapenEntity imGolonganDapenEntity = null;

            try {
                // Get data from database by ID
                imGolonganDapenEntity = golonganDapenDao.getById("golonganDapenId", golonganDapenId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganDapenEntity != null) {

                // Modify from bean to entity serializable
                imGolonganDapenEntity.setGolonganDapenId(bean.getGolonganDapenId());
                imGolonganDapenEntity.setGolonganDapenName(bean.getGolonganDapenName());
                imGolonganDapenEntity.setFlag(bean.getFlag());
                imGolonganDapenEntity.setAction(bean.getAction());
                imGolonganDapenEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganDapenEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    golonganDapenDao.updateAndSave(imGolonganDapenEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganDapenBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data GolonganDapen, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganDapenBoImpl.saveDelete] Error, not found data GolonganDapen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data GolonganDapen with request id, please check again your data ...");

            }
        }
        logger.info("[GolonganDapenBoImpl.saveDelete] end process <<<");
    }

    @Override
    public List<GolonganDapen> getComboGolonganDapenWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<GolonganDapen> listComboGolongan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImGolonganDapenEntity> listGolongan = null;
        try {
            listGolongan = golonganDapenDao.getListGolongan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImGolonganDapenEntity imGolonganEntity : listGolongan) {
                GolonganDapen itemComboGolongan = new GolonganDapen();
                itemComboGolongan.setGolonganDapenId(imGolonganEntity.getGolonganDapenId());
                itemComboGolongan.setGolonganDapenName(imGolonganEntity.getGolonganDapenName());
                listComboGolongan.add(itemComboGolongan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboGolongan;
    }

    @Override
    public void saveEdit(GolonganDapen bean) throws GeneralBOException {
        logger.info("[GolonganDapenBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String golonganDapenId = bean.getGolonganDapenId();
            String historyId = "";

            ImGolonganDapenEntity imGolonganDapenEntity = null;

            try {
                // Get data from database by ID
                imGolonganDapenEntity = golonganDapenDao.getById("golonganDapenId", golonganDapenId);
            } catch (HibernateException e) {
                logger.error("[GolonganDapenBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data GolonganDapen by Kode GolonganDapen, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganDapenEntity != null) {
                //
                imGolonganDapenEntity.setGolonganDapenId(bean.getGolonganDapenId());
                imGolonganDapenEntity.setGolonganDapenName(bean.getGolonganDapenName());
                imGolonganDapenEntity.setFlag(bean.getFlag());
                imGolonganDapenEntity.setAction(bean.getAction());
                imGolonganDapenEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganDapenEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    golonganDapenDao.updateAndSave(imGolonganDapenEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[GolonganDapenBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data GolonganDapen, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[GolonganDapenBoImpl.saveEdit] Error, not found data GolonganDapen with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data GolonganDapen with request id, please check again your data ...");
//                condition = "Error, not found data GolonganDapen with request id, please check again your data ...";
            }
        }
        logger.info("[GolonganDapenBoImpl.saveEdit] end process <<<");
    }

    @Override
    public GolonganDapen saveAdd(GolonganDapen bean) throws GeneralBOException {
        logger.info("[GolonganDapenBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String golonganDapenId;
            try {
                // Generating ID, get from postgre sequence
                golonganDapenId = golonganDapenDao.getNextGolonganDapenId();
            } catch (HibernateException e) {
                logger.error("[GolonganDapenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganDapenId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImGolonganDapenEntity imGolonganDapenEntity = new ImGolonganDapenEntity();

            imGolonganDapenEntity.setGolonganDapenId(golonganDapenId);
            imGolonganDapenEntity.setGolonganDapenName(bean.getGolonganDapenName());
            imGolonganDapenEntity.setFlag(bean.getFlag());
            imGolonganDapenEntity.setAction(bean.getAction());
            imGolonganDapenEntity.setCreatedWho(bean.getCreatedWho());
            imGolonganDapenEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imGolonganDapenEntity.setCreatedDate(bean.getCreatedDate());
            imGolonganDapenEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                golonganDapenDao.addAndSave(imGolonganDapenEntity);
            } catch (HibernateException e) {
                logger.error("[GolonganDapenBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data GolonganDapen, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[GolonganDapenBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<GolonganDapen> getByCriteria(GolonganDapen searchBean) throws GeneralBOException {
        logger.info("[GolonganDapenBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<GolonganDapen> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGolonganDapenId() != null && !"".equalsIgnoreCase(searchBean.getGolonganDapenId())) {
                hsCriteria.put("golongan_Dapen_id", searchBean.getGolonganDapenId());
            }
            if (searchBean.getGolonganDapenName() != null && !"".equalsIgnoreCase(searchBean.getGolonganDapenName())) {
                hsCriteria.put("golongan_Dapen_name", searchBean.getGolonganDapenName());
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


            List<ImGolonganDapenEntity> imGolonganDapenEntity = null;
            try {

                imGolonganDapenEntity = golonganDapenDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GolonganDapenBoImpl.getSearchGolonganDapenByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganDapenEntity != null){
                GolonganDapen returnGolonganDapen;
                // Looping from dao to object and save in collection
                for(ImGolonganDapenEntity golonganDapenEntity : imGolonganDapenEntity){
                    returnGolonganDapen = new GolonganDapen();
                    returnGolonganDapen.setGolonganDapenId(golonganDapenEntity.getGolonganDapenId());
                    returnGolonganDapen.setGolonganDapenName(golonganDapenEntity.getGolonganDapenName());

                    returnGolonganDapen.setCreatedWho(golonganDapenEntity.getCreatedWho());
                    returnGolonganDapen.setCreatedDate(golonganDapenEntity.getCreatedDate());
                    returnGolonganDapen.setLastUpdate(golonganDapenEntity.getLastUpdate());

                    returnGolonganDapen.setAction(golonganDapenEntity.getAction());
                    returnGolonganDapen.setFlag(golonganDapenEntity.getFlag());
                    listOfResult.add(returnGolonganDapen);
                }
            }
        }
        logger.info("[GolonganDapenBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<GolonganDapen> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

}
