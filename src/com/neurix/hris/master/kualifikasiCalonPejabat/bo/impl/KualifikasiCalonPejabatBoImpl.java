package com.neurix.hris.master.kualifikasiCalonPejabat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.kualifikasiCalonPejabat.bo.KualifikasiCalonPejabatBo;
import com.neurix.hris.master.kualifikasiCalonPejabat.dao.KualifikasiCalonPejabatDao;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.KualifikasiCalonPejabat;
import com.neurix.hris.master.kualifikasiCalonPejabat.model.ImHrisKualifikasiCalonPejabatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class KualifikasiCalonPejabatBoImpl implements KualifikasiCalonPejabatBo {
    protected static transient Logger logger = Logger.getLogger(KualifikasiCalonPejabatBoImpl.class);

    private KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KualifikasiCalonPejabatBoImpl.logger = logger;
    }

    public KualifikasiCalonPejabatDao getKualifikasiCalonPejabatDao() {
        return kualifikasiCalonPejabatDao;
    }

    public void setKualifikasiCalonPejabatDao(KualifikasiCalonPejabatDao kualifikasiCalonPejabatDao) {
        this.kualifikasiCalonPejabatDao = kualifikasiCalonPejabatDao;
    }

    @Override
    public void saveDelete(KualifikasiCalonPejabat bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KualifikasiCalonPejabat bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");

        boolean saved = false;

        if (bean!=null) {
            ImHrisKualifikasiCalonPejabatEntity entityData = new ImHrisKualifikasiCalonPejabatEntity();

            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                kualifikasiCalonPejabatDao.updateAndSave(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        if (saved){
            String id;
            try {
                // Generating ID, get from postgre sequence
                //id = groupDao.getNextGoupHistoryId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            /*ImHrisGroupHistory entityData = new ImHrisGroupHistory();
            entityData.setGroupId(bean.getGroupId());
            entityData.setGroupName(bean.getGroupName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setId(id);

            try {
                groupDao.addAndSaveHistory(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }*/
        }

        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public KualifikasiCalonPejabat saveAdd(KualifikasiCalonPejabat bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String groupId;
            try {
                // Generating ID, get from postgre sequence
                //groupId = groupDao.getNextGroupId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisKualifikasiCalonPejabatEntity entityData = new ImHrisKualifikasiCalonPejabatEntity();

            /*entityData.setGroupId("GR"+groupId);
            entityData.setGroupName(bean.getGroupName());*/
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                kualifikasiCalonPejabatDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<KualifikasiCalonPejabat> getByCriteria(KualifikasiCalonPejabat searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<KualifikasiCalonPejabat> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            /*if (searchBean.getGroupId() != null && !"".equalsIgnoreCase(searchBean.getGroupId())) {
                hsCriteria.put("group_id", searchBean.getGroupId());
            }
            if (searchBean.getGroupName() != null && !"".equalsIgnoreCase(searchBean.getGroupName())) {
                hsCriteria.put("group_name", searchBean.getGroupName());
            }*/
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImHrisKualifikasiCalonPejabatEntity> imHrisGroupShiftList = null;
            try {

                imHrisGroupShiftList = kualifikasiCalonPejabatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisGroupShiftList != null){
                KualifikasiCalonPejabat returnData;
                // Looping from dao to object and save in collection
                for(ImHrisKualifikasiCalonPejabatEntity listEntity : imHrisGroupShiftList){
                    returnData = new KualifikasiCalonPejabat();
                    /*returnData.setGroupId(listEntity.getGroupId());
                    returnData.setGroupName(listEntity.getGroupName());*/
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<KualifikasiCalonPejabat> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
