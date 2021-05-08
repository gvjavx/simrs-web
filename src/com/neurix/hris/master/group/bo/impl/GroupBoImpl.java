package com.neurix.hris.master.group.bo.impl;

import com.neurix.common.exception.GeneralBOException;
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
public class GroupBoImpl implements GroupBo {
    protected static transient Logger logger = Logger.getLogger(GroupBoImpl.class);

    private GroupDao groupDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupBoImpl.logger = logger;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void saveDelete(Group bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Group bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");

        boolean saved = false;

        if (bean!=null) {
            ImHrisGroupEntity entityData = new ImHrisGroupEntity();
            entityData.setGroupId(bean.getGroupId());
            entityData.setGroupName(bean.getGroupName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupDao.updateAndSave(entityData);
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
                id = groupDao.getNextGoupHistoryId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisGroupHistory entityData = new ImHrisGroupHistory();
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
            }
        }

        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Group saveAdd(Group bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String groupId;
            try {
                // Generating ID, get from postgre sequence
                groupId = groupDao.getNextGroupId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisGroupEntity entityData = new ImHrisGroupEntity();

            entityData.setGroupId("GR"+groupId);
            entityData.setGroupName(bean.getGroupName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Group> getByCriteria(Group searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Group> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGroupId() != null && !"".equalsIgnoreCase(searchBean.getGroupId())) {
                hsCriteria.put("group_id", searchBean.getGroupId());
            }
            if (searchBean.getGroupName() != null && !"".equalsIgnoreCase(searchBean.getGroupName())) {
                hsCriteria.put("group_name", searchBean.getGroupName());
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


            List<ImHrisGroupEntity> imHrisGroupShiftList = null;
            try {

                imHrisGroupShiftList = groupDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisGroupShiftList != null){
                Group returnData;
                // Looping from dao to object and save in collection
                for(ImHrisGroupEntity listEntity : imHrisGroupShiftList){
                    returnData = new Group();
                    returnData.setGroupId(listEntity.getGroupId());
                    returnData.setGroupName(listEntity.getGroupName());
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
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Group> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
