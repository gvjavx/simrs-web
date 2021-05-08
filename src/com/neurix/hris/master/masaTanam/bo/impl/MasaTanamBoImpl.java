package com.neurix.hris.master.masaTanam.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.masaTanam.bo.MasaTanamBo;
import com.neurix.hris.master.masaTanam.dao.MasaTanamDao;
import com.neurix.hris.master.masaTanam.model.MasaTanam;
import com.neurix.hris.master.masaTanam.model.ImMasaTanamEntity;
import com.neurix.hris.master.masaTanam.model.ImMasaTanamHistoryEntity;
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
public class MasaTanamBoImpl implements MasaTanamBo {

    protected static transient Logger logger = Logger.getLogger(MasaTanamBoImpl.class);
    private MasaTanamDao masaTanamDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasaTanamBoImpl.logger = logger;
    }

    public MasaTanamDao getMasaTanamDao() {
        return masaTanamDao;
    }


    public void setMasaTanamDao(MasaTanamDao masaTanamDao) {
        this.masaTanamDao = masaTanamDao;
    }

    @Override
    public void saveDelete(MasaTanam bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String masaTanamId = bean.getMasaTanamId();

            ImMasaTanamEntity imMasaTanamEntity = null;

            try {
                // Get data from database by ID
                imMasaTanamEntity = masaTanamDao.getById("masaTanamId", masaTanamId);
            } catch (HibernateException e) {
                logger.error("[MasaTanamBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imMasaTanamEntity != null) {

                // Modify from bean to entity serializable
                imMasaTanamEntity.setMasaTanamId(bean.getMasaTanamId());
                imMasaTanamEntity.setMasaTanamName(bean.getMasaTanamName());
                imMasaTanamEntity.setFlag(bean.getFlag());
                imMasaTanamEntity.setAction(bean.getAction());
                imMasaTanamEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMasaTanamEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    masaTanamDao.updateAndSave(imMasaTanamEntity);
                } catch (HibernateException e) {
                    logger.error("[MasaTanamBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MasaTanam, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[MasaTanamBoImpl.saveDelete] Error, not found data MasaTanam with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MasaTanam with request id, please check again your data ...");

            }
        }
        logger.info("[MasaTanamBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MasaTanam bean) throws GeneralBOException {

    }

    @Override
    public MasaTanam saveAdd(MasaTanam bean) throws GeneralBOException {
        logger.info("[MasaTanamBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String masaTanamId;
            try {
                // Generating ID, get from postgre sequence
                masaTanamId = masaTanamDao.getNextMasaTanamId();
            } catch (HibernateException e) {
                logger.error("[MasaTanamBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence masaTanamId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImMasaTanamEntity imMasaTanamEntity = new ImMasaTanamEntity();

            imMasaTanamEntity.setMasaTanamId(masaTanamId);
            imMasaTanamEntity.setMasaTanamName(bean.getMasaTanamName());
            imMasaTanamEntity.setFlag(bean.getFlag());
            imMasaTanamEntity.setAction(bean.getAction());
            imMasaTanamEntity.setCreatedWho(bean.getCreatedWho());
            imMasaTanamEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imMasaTanamEntity.setCreatedDate(bean.getCreatedDate());
            imMasaTanamEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                masaTanamDao.addAndSave(imMasaTanamEntity);
            } catch (HibernateException e) {
                logger.error("[MasaTanamBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data MasaTanam, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MasaTanamBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MasaTanam> getByCriteria(MasaTanam searchBean) throws GeneralBOException {
        logger.info("[MasaTanamBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MasaTanam> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMasaTanamId() != null && !"".equalsIgnoreCase(searchBean.getMasaTanamId())) {
                hsCriteria.put("masaTanam_id", searchBean.getMasaTanamId());
            }
            if (searchBean.getMasaTanamName() != null && !"".equalsIgnoreCase(searchBean.getMasaTanamName())) {
                hsCriteria.put("masaTanam_name", searchBean.getMasaTanamName());
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


            List<ImMasaTanamEntity> imMasaTanamEntity = null;
            try {

                imMasaTanamEntity = masaTanamDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MasaTanamBoImpl.getSearchMasaTanamByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imMasaTanamEntity != null){
                MasaTanam returnMasaTanam;
                // Looping from dao to object and save in collection
                for(ImMasaTanamEntity masaTanamEntity : imMasaTanamEntity){
                    returnMasaTanam = new MasaTanam();
                    returnMasaTanam.setMasaTanamId(masaTanamEntity.getMasaTanamId());
                    returnMasaTanam.setMasaTanamName(masaTanamEntity.getMasaTanamName());

                    returnMasaTanam.setCreatedWho(masaTanamEntity.getCreatedWho());
                    returnMasaTanam.setCreatedDate(masaTanamEntity.getCreatedDate());
                    returnMasaTanam.setLastUpdate(masaTanamEntity.getLastUpdate());

                    returnMasaTanam.setAction(masaTanamEntity.getAction());
                    returnMasaTanam.setFlag(masaTanamEntity.getFlag());
                    listOfResult.add(returnMasaTanam);
                }
            }
        }
        logger.info("[MasaTanamBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MasaTanam> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<MasaTanam> getComboMasaTanamWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<MasaTanam> listComboMasaTanam = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImMasaTanamEntity> listMasaTanam = null;
        try {
           // listMasaTanam = masaTanamDao.getListMasaTanam(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listMasaTanam != null) {
            for (ImMasaTanamEntity imMasaTanamEntity : listMasaTanam) {
                MasaTanam itemComboMasaTanam = new MasaTanam();
                itemComboMasaTanam.setMasaTanamId(imMasaTanamEntity.getMasaTanamId());
                itemComboMasaTanam.setMasaTanamName(imMasaTanamEntity.getMasaTanamName());
                listComboMasaTanam.add(itemComboMasaTanam);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboMasaTanam;
    }
}
