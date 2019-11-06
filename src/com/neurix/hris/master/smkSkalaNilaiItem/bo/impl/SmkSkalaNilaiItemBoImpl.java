package com.neurix.hris.master.smkSkalaNilaiItem.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaNilaiItem.dao.SmkSkalaNilaiItemDao;
import com.neurix.hris.master.smkSkalaNilaiItem.model.ImSmkSkalaNilaiItemEntity;
import com.neurix.hris.master.smkSkalaNilaiItem.model.SmkSkalaNilaiItem;
import com.neurix.hris.master.smkSkalaNilaiItem.bo.SmkSkalaNilaiItemBo;
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
public class SmkSkalaNilaiItemBoImpl implements SmkSkalaNilaiItemBo {

    protected static transient Logger logger = Logger.getLogger(SmkSkalaNilaiItemBoImpl.class);
    private SmkSkalaNilaiItemDao smkSkalaNilaiItemDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaNilaiItemBoImpl.logger = logger;
    }

    public SmkSkalaNilaiItemDao getSmkSkalaNilaiItemDao() {
        return smkSkalaNilaiItemDao;
    }


    public void setSmkSkalaNilaiItemDao(SmkSkalaNilaiItemDao smkSkalaNilaiItemDao) {
        this.smkSkalaNilaiItemDao = smkSkalaNilaiItemDao;
    }

    @Override
    public void saveDelete(SmkSkalaNilaiItem bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiItemId = bean.getSkalaNilaiItemId();

            ImSmkSkalaNilaiItemEntity imSmkSkalaNilaiItemEntity = null;

            try {
                // Get data from database by ID
                imSmkSkalaNilaiItemEntity = smkSkalaNilaiItemDao.getById("skalaNilaiItemId", smkSkalaNilaiItemId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiItemEntity != null) {

                // Modify from bean to entity serializable
                imSmkSkalaNilaiItemEntity.setSkalaNilaiItemId(bean.getSkalaNilaiItemId());
                imSmkSkalaNilaiItemEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiItemEntity.setAction(bean.getAction());
                imSmkSkalaNilaiItemEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiItemEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkSkalaNilaiItemDao.updateAndSave(imSmkSkalaNilaiItemEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkSkalaNilaiItemBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkSkalaNilaiItem, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveDelete] Error, not found data SmkSkalaNilaiItem with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkSkalaNilaiItem with request id, please check again your data ...");

            }
        }
        logger.info("[SmkSkalaNilaiItemBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkSkalaNilaiItem bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiItemBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String smkSkalaNilaiItemId = bean.getSkalaNilaiItemId();

            ImSmkSkalaNilaiItemEntity imSmkSkalaNilaiItemEntity = null;
            try {
                // Get data from database by ID
                imSmkSkalaNilaiItemEntity = smkSkalaNilaiItemDao.getById("skalaNilaiItemId", smkSkalaNilaiItemId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkSkalaNilaiItem by Kode SmkSkalaNilaiItem, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiItemEntity != null) {

                imSmkSkalaNilaiItemEntity.setSkalaNilaiItemId(bean.getSkalaNilaiItemId());
                imSmkSkalaNilaiItemEntity.setPersenBatasBawah(bean.getPersenBatasBawah());
                imSmkSkalaNilaiItemEntity.setPersenBatasAtas(bean.getPersenBatasAtas());
                imSmkSkalaNilaiItemEntity.setNilaiAtas(bean.getNilaiAtas());
                imSmkSkalaNilaiItemEntity.setNilaiBawah(bean.getNilaiBawah());
                imSmkSkalaNilaiItemEntity.setBranchId(bean.getBranchId());

                imSmkSkalaNilaiItemEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiItemEntity.setAction(bean.getAction());
                imSmkSkalaNilaiItemEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiItemEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkSkalaNilaiItemDao.updateAndSave(imSmkSkalaNilaiItemEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkSkalaNilaiItemBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkSkalaNilaiItem, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveEdit] Error, not found data SmkSkalaNilaiItem with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkSkalaNilaiItem with request id, please check again your data ...");
//                condition = "Error, not found data SmkSkalaNilaiItem with request id, please check again your data ...";
            }
        }
        logger.info("[SmkSkalaNilaiItemBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkSkalaNilaiItem saveAdd(SmkSkalaNilaiItem bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiItemBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiItemId;
            try {
                // Generating ID, get from postgre sequence
                smkSkalaNilaiItemId = smkSkalaNilaiItemDao.getNextSmkSkalaNilaiItemId();
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkSkalaNilaiItemId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkSkalaNilaiItemEntity imSmkSkalaNilaiItemEntity = new ImSmkSkalaNilaiItemEntity();

            imSmkSkalaNilaiItemEntity.setSkalaNilaiItemId(smkSkalaNilaiItemId);
            imSmkSkalaNilaiItemEntity.setPersenBatasBawah(bean.getPersenBatasBawah());
            imSmkSkalaNilaiItemEntity.setPersenBatasAtas(bean.getPersenBatasAtas());
            imSmkSkalaNilaiItemEntity.setNilaiAtas(bean.getNilaiAtas());
            imSmkSkalaNilaiItemEntity.setNilaiBawah(bean.getNilaiBawah());
            imSmkSkalaNilaiItemEntity.setBranchId(bean.getBranchId());

            imSmkSkalaNilaiItemEntity.setFlag(bean.getFlag());
            imSmkSkalaNilaiItemEntity.setAction(bean.getAction());
            imSmkSkalaNilaiItemEntity.setCreatedWho(bean.getCreatedWho());
            imSmkSkalaNilaiItemEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkSkalaNilaiItemEntity.setCreatedDate(bean.getCreatedDate());
            imSmkSkalaNilaiItemEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkSkalaNilaiItemDao.addAndSave(imSmkSkalaNilaiItemEntity);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiItemBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkSkalaNilaiItem, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkSkalaNilaiItemBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkSkalaNilaiItem> getByCriteria(SmkSkalaNilaiItem searchBean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiItemBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkSkalaNilaiItem> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaNilaiItemId() != null && !"".equalsIgnoreCase(searchBean.getSkalaNilaiItemId())) {
                hsCriteria.put("skalaNilaiItemId", searchBean.getSkalaNilaiItemId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
            }
            if (searchBean.getNilaiAtas() != null) {
                hsCriteria.put("nilaiAtas", searchBean.getNilaiAtas());
            }
            if (searchBean.getPersenBatasAtas() != null) {
                hsCriteria.put("persenBatasAtas", searchBean.getPersenBatasAtas());
            }
            if (searchBean.getPersenBatasBawah() != null) {
                hsCriteria.put("persenBatasBawah", searchBean.getPersenBatasBawah());
            }
            if (searchBean.getNilaiBawah() != null) {
                hsCriteria.put("nilaiBawah", searchBean.getNilaiBawah());
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


            List<ImSmkSkalaNilaiItemEntity> imSmkSkalaNilaiItemEntity = null;
            try {

                imSmkSkalaNilaiItemEntity = smkSkalaNilaiItemDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiItemBoImpl.getSearchSmkSkalaNilaiItemByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkSkalaNilaiItemEntity != null){
                SmkSkalaNilaiItem returnSmkSkalaNilaiItem;
                // Looping from dao to object and save in collection
                for(ImSmkSkalaNilaiItemEntity smkSkalaNilaiItemEntity : imSmkSkalaNilaiItemEntity){
                    returnSmkSkalaNilaiItem = new SmkSkalaNilaiItem();
                    returnSmkSkalaNilaiItem.setSkalaNilaiItemId(smkSkalaNilaiItemEntity.getSkalaNilaiItemId());
                    returnSmkSkalaNilaiItem.setPersenBatasAtas(smkSkalaNilaiItemEntity.getPersenBatasAtas());
                    returnSmkSkalaNilaiItem.setPersenBatasBawah(smkSkalaNilaiItemEntity.getPersenBatasBawah());
                    returnSmkSkalaNilaiItem.setNilaiAtas(smkSkalaNilaiItemEntity.getNilaiAtas());
                    returnSmkSkalaNilaiItem.setNilaiBawah(smkSkalaNilaiItemEntity.getNilaiBawah());
                    returnSmkSkalaNilaiItem.setBranchId(smkSkalaNilaiItemEntity.getBranchId());
                    returnSmkSkalaNilaiItem.setBranchName(smkSkalaNilaiItemEntity.getImBranches().getBranchName());

                    returnSmkSkalaNilaiItem.setCreatedWho(smkSkalaNilaiItemEntity.getCreatedWho());
                    returnSmkSkalaNilaiItem.setCreatedDate(smkSkalaNilaiItemEntity.getCreatedDate());
                    returnSmkSkalaNilaiItem.setLastUpdate(smkSkalaNilaiItemEntity.getLastUpdate());

                    returnSmkSkalaNilaiItem.setAction(smkSkalaNilaiItemEntity.getAction());
                    returnSmkSkalaNilaiItem.setFlag(smkSkalaNilaiItemEntity.getFlag());
                    listOfResult.add(returnSmkSkalaNilaiItem);
                }
            }
        }
        logger.info("[SmkSkalaNilaiItemBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkSkalaNilaiItem> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
