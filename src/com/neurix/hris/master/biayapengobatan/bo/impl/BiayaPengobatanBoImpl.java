package com.neurix.hris.master.biayapengobatan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biayapengobatan.bo.BiayaPengobatanBo;
import com.neurix.hris.master.biayapengobatan.dao.BiayaPengobatanDao;
import com.neurix.hris.master.biayapengobatan.model.BiayaPengobatan;
import com.neurix.hris.master.biayapengobatan.model.ImHrisBiayaPengobatan;
import com.neurix.hris.master.biayapengobatan.model.ImHrisBiayaPengobatanHistory;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class BiayaPengobatanBoImpl implements BiayaPengobatanBo {
    protected static transient Logger logger = Logger.getLogger(BiayaPengobatanBoImpl.class);

    private BiayaPengobatanDao biayaPengobatanDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiayaPengobatanBoImpl.logger = logger;
    }

    public BiayaPengobatanDao getBiayaPengobatanDao() {
        return biayaPengobatanDao;
    }

    public void setBiayaPengobatanDao(BiayaPengobatanDao biayaPengobatanDao) {
        this.biayaPengobatanDao = biayaPengobatanDao;
    }

    @Override
    public void saveDelete(BiayaPengobatan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(BiayaPengobatan bean) throws GeneralBOException {
        logger.info("[BiayaPengobatanBoImpl.saveEdit] start process >>>");

        boolean saved = false;

        if (bean!=null) {
            ImHrisBiayaPengobatan entityData = new ImHrisBiayaPengobatan();
            entityData.setBiayaPengobatanId(bean.getBiayaPengobatanId());
            entityData.setBiayaPengobatanName(bean.getBiayaPengobatanName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                biayaPengobatanDao.updateAndSave(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        if (saved){
            String id;
            try {
                // Generating ID, get from postgre sequence
                id = biayaPengobatanDao.getNextBiayaPengobatanHistoryId();
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisBiayaPengobatanHistory entityData = new ImHrisBiayaPengobatanHistory();
            entityData.setBiayaPengobatanId(bean.getBiayaPengobatanId());
            entityData.setBiayaPengobatanName(bean.getBiayaPengobatanName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setId(id);

            try {
                biayaPengobatanDao.addAndSaveHistory(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiayaPengobatanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public BiayaPengobatan saveAdd(BiayaPengobatan bean) throws GeneralBOException {
        logger.info("[BiayaPengobatanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String shiftId;
            try {
                // Generating ID, get from postgre sequence
                shiftId = biayaPengobatanDao.getNextBiayaPengobatanId();
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisBiayaPengobatan entityData = new ImHrisBiayaPengobatan();

            entityData.setBiayaPengobatanId("BP"+shiftId);
            entityData.setBiayaPengobatanName(bean.getBiayaPengobatanName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                biayaPengobatanDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BiayaPengobatanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<BiayaPengobatan> getByCriteria(BiayaPengobatan searchBean) throws GeneralBOException {
        logger.info("[BiayaPengobatanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<BiayaPengobatan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBiayaPengobatanId() != null && !"".equalsIgnoreCase(searchBean.getBiayaPengobatanId())) {
                hsCriteria.put("biaya_pengobatan_id", searchBean.getBiayaPengobatanId());
            }
            if (searchBean.getBiayaPengobatanName() != null && !"".equalsIgnoreCase(searchBean.getBiayaPengobatanName())) {
                hsCriteria.put("biaya_pengobatan_name", searchBean.getBiayaPengobatanName());
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


            List<ImHrisBiayaPengobatan> imHrisBiayaPengobatan = null;
            try {

                imHrisBiayaPengobatan = biayaPengobatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BiayaPengobatanBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisBiayaPengobatan != null){
                BiayaPengobatan returnData;
                // Looping from dao to object and save in collection
                for(ImHrisBiayaPengobatan listEntity : imHrisBiayaPengobatan){
                    returnData = new BiayaPengobatan();
                    returnData.setBiayaPengobatanId(listEntity.getBiayaPengobatanId());
                    returnData.setBiayaPengobatanName(listEntity.getBiayaPengobatanName());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[BiayaPengobatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<BiayaPengobatan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
