package com.neurix.hris.master.smkIndikatorKinerja.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorKinerja.bo.SmkIndikatorKinerjaBo;
import com.neurix.hris.master.smkIndikatorKinerja.dao.SmkIndikatorKinerjaDao;
import com.neurix.hris.master.smkIndikatorKinerja.model.SmkIndikatorKinerja;
import com.neurix.hris.master.smkIndikatorKinerja.model.ImSmkIndikatorKinerjaEntity;
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
public class SmkIndikatorKinerjaBoImpl implements SmkIndikatorKinerjaBo {

    protected static transient Logger logger = Logger.getLogger(SmkIndikatorKinerjaBoImpl.class);
    private SmkIndikatorKinerjaDao smkIndikatorKinerjaDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorKinerjaBoImpl.logger = logger;
    }

    public SmkIndikatorKinerjaDao getSmkIndikatorKinerjaDao() {
        return smkIndikatorKinerjaDao;
    }


    public void setSmkIndikatorKinerjaDao(SmkIndikatorKinerjaDao smkIndikatorKinerjaDao) {
        this.smkIndikatorKinerjaDao = smkIndikatorKinerjaDao;
    }

    @Override
    public void saveDelete(SmkIndikatorKinerja bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkIndikatorKinerjaId = bean.getIndikatorKinerjaId();

            ImSmkIndikatorKinerjaEntity imSmkIndikatorKinerjaEntity = null;

            try {
                // Get data from database by ID
                imSmkIndikatorKinerjaEntity = smkIndikatorKinerjaDao.getById("smkIndikatorKinerjaId", smkIndikatorKinerjaId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorKinerjaEntity != null) {

                // Modify from bean to entity serializable
                imSmkIndikatorKinerjaEntity.setIndikatorKinerjaId(bean.getIndikatorKinerjaId());
                imSmkIndikatorKinerjaEntity.setFlag(bean.getFlag());
                imSmkIndikatorKinerjaEntity.setAction(bean.getAction());
                imSmkIndikatorKinerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorKinerjaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkIndikatorKinerjaDao.updateAndSave(imSmkIndikatorKinerjaEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorKinerjaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikatorKinerja, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveDelete] Error, not found data SmkIndikatorKinerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikatorKinerja with request id, please check again your data ...");

            }
        }
        logger.info("[SmkIndikatorKinerjaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkIndikatorKinerja bean) throws GeneralBOException {
        logger.info("[SmkIndikatorKinerjaBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String smkIndikatorKinerjaId = bean.getIndikatorKinerjaId();

            ImSmkIndikatorKinerjaEntity imSmkIndikatorKinerjaEntity = null;
            try {
                // Get data from database by ID
                imSmkIndikatorKinerjaEntity = smkIndikatorKinerjaDao.getById("smkIndikatorKinerjaId", smkIndikatorKinerjaId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkIndikatorKinerja by Kode SmkIndikatorKinerja, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorKinerjaEntity != null) {

                imSmkIndikatorKinerjaEntity.setIndikatorKinerjaId(bean.getIndikatorKinerjaId());
                imSmkIndikatorKinerjaEntity.setFlag(bean.getFlag());
                imSmkIndikatorKinerjaEntity.setAction(bean.getAction());
                imSmkIndikatorKinerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorKinerjaEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkIndikatorKinerjaDao.updateAndSave(imSmkIndikatorKinerjaEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorKinerjaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikatorKinerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveEdit] Error, not found data SmkIndikatorKinerja with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikatorKinerja with request id, please check again your data ...");
//                condition = "Error, not found data SmkIndikatorKinerja with request id, please check again your data ...";
            }
        }
        logger.info("[SmkIndikatorKinerjaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkIndikatorKinerja saveAdd(SmkIndikatorKinerja bean) throws GeneralBOException {
        logger.info("[SmkIndikatorKinerjaBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkIndikatorKinerjaId;
            try {
                // Generating ID, get from postgre sequence
                smkIndikatorKinerjaId = smkIndikatorKinerjaDao.getNextSmkIndikatorKinerjaId();
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkIndikatorKinerjaId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkIndikatorKinerjaEntity imSmkIndikatorKinerjaEntity = new ImSmkIndikatorKinerjaEntity();

            imSmkIndikatorKinerjaEntity.setIndikatorKinerjaId(smkIndikatorKinerjaId);
            imSmkIndikatorKinerjaEntity.setPositionId(bean.getPositionId());
            imSmkIndikatorKinerjaEntity.setIndikatorKinerja(bean.getIndikatorKinerja());
            imSmkIndikatorKinerjaEntity.setBobot(bean.getBobot());
            imSmkIndikatorKinerjaEntity.setTarget(bean.getTarget());
            imSmkIndikatorKinerjaEntity.setFlag(bean.getFlag());
            imSmkIndikatorKinerjaEntity.setAction(bean.getAction());
            imSmkIndikatorKinerjaEntity.setCreatedWho(bean.getCreatedWho());
            imSmkIndikatorKinerjaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkIndikatorKinerjaEntity.setCreatedDate(bean.getCreatedDate());
            imSmkIndikatorKinerjaEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkIndikatorKinerjaDao.addAndSave(imSmkIndikatorKinerjaEntity);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorKinerjaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkIndikatorKinerja, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkIndikatorKinerjaBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkIndikatorKinerja> getByCriteria(SmkIndikatorKinerja searchBean) throws GeneralBOException {
        logger.info("[SmkIndikatorKinerjaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkIndikatorKinerja> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIndikatorKinerjaId() != null && !"".equalsIgnoreCase(searchBean.getIndikatorKinerjaId())) {
                hsCriteria.put("smkIndikatorKinerja_id", searchBean.getIndikatorKinerjaId());
            }
            if (searchBean.getPositionId() != null && !"".equalsIgnoreCase(searchBean.getPositionId())) {
                hsCriteria.put("position_id", searchBean.getPositionId());
            }
            if (searchBean.getSatuanTarget() != null && !"".equalsIgnoreCase(searchBean.getSatuanTarget())) {
                hsCriteria.put("satuan_target", searchBean.getSatuanTarget());
            }
            if (searchBean.getBobot() != null) {
                hsCriteria.put("bobot", searchBean.getBobot());
            }
            if (searchBean.getTarget() != null) {
                hsCriteria.put("target", searchBean.getTarget());
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


            List<ImSmkIndikatorKinerjaEntity> imSmkIndikatorKinerjaEntity = null;
            try {

                imSmkIndikatorKinerjaEntity = smkIndikatorKinerjaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorKinerjaBoImpl.getSearchSmkIndikatorKinerjaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkIndikatorKinerjaEntity != null){
                SmkIndikatorKinerja returnSmkIndikatorKinerja;
                // Looping from dao to object and save in collection
                for(ImSmkIndikatorKinerjaEntity smkIndikatorKinerjaEntity : imSmkIndikatorKinerjaEntity){
                    returnSmkIndikatorKinerja = new SmkIndikatorKinerja();
                    returnSmkIndikatorKinerja.setIndikatorKinerjaId(smkIndikatorKinerjaEntity.getIndikatorKinerjaId());

                    returnSmkIndikatorKinerja.setCreatedWho(smkIndikatorKinerjaEntity.getCreatedWho());
                    returnSmkIndikatorKinerja.setCreatedDate(smkIndikatorKinerjaEntity.getCreatedDate());
                    returnSmkIndikatorKinerja.setLastUpdate(smkIndikatorKinerjaEntity.getLastUpdate());

                    returnSmkIndikatorKinerja.setAction(smkIndikatorKinerjaEntity.getAction());
                    returnSmkIndikatorKinerja.setFlag(smkIndikatorKinerjaEntity.getFlag());
                    listOfResult.add(returnSmkIndikatorKinerja);
                }
            }
        }
        logger.info("[SmkIndikatorKinerjaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkIndikatorKinerja> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<SmkIndikatorKinerja> getComboSmkIndikatorKinerjaWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SmkIndikatorKinerja> listComboSmkIndikatorKinerja = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImSmkIndikatorKinerjaEntity> listSmkIndikatorKinerja = null;
        try {
            listSmkIndikatorKinerja = smkIndikatorKinerjaDao.getListSmkIndikatorKinerja(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSmkIndikatorKinerja != null) {
            for (ImSmkIndikatorKinerjaEntity imSmkIndikatorKinerjaEntity : listSmkIndikatorKinerja) {
                SmkIndikatorKinerja itemComboSmkIndikatorKinerja = new SmkIndikatorKinerja();
                itemComboSmkIndikatorKinerja.setIndikatorKinerjaId(imSmkIndikatorKinerjaEntity.getIndikatorKinerjaId());
                listComboSmkIndikatorKinerja.add(itemComboSmkIndikatorKinerja);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkIndikatorKinerja;
    }
}
