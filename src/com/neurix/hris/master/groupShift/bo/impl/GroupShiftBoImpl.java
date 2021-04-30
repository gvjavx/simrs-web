package com.neurix.hris.master.groupShift.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.groupShift.bo.GroupShiftBo;
import com.neurix.hris.master.groupShift.dao.GroupShiftDao;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.master.groupShift.model.ImHrisGroupShift;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class GroupShiftBoImpl implements GroupShiftBo {
    protected static transient Logger logger = Logger.getLogger(GroupShiftBoImpl.class);

    private GroupShiftDao groupShiftDao;
    private ShiftDao shiftDao;
    private GroupDao groupDao;


    public ShiftDao getShiftDao() {
        return shiftDao;
    }

    public void setShiftDao(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupShiftBoImpl.logger = logger;
    }

    public GroupShiftDao getGroupShiftDao() {
        return groupShiftDao;
    }

    public void setGroupShiftDao(GroupShiftDao groupShiftDao) {
        this.groupShiftDao = groupShiftDao;
    }

    @Override
    public void saveDelete(GroupShift bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveDelete] start process >>>");

        if (bean!=null) {
            ImHrisGroupShift entityData = new ImHrisGroupShift();

            entityData.setGroupShiftId(bean.getGroupShiftId());
            entityData.setGroupId(bean.getGroupId());
            entityData.setShiftId(bean.getShiftId());
            entityData.setGroupShiftName(bean.getGroupShiftName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupShiftDao.updateAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveEdit(GroupShift bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImHrisGroupShift entityData = new ImHrisGroupShift();

            entityData.setGroupShiftId(bean.getGroupShiftId());
            entityData.setGroupId(bean.getGroupId());
            entityData.setShiftId(bean.getShiftId());
            entityData.setGroupShiftName(bean.getGroupShiftName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupShiftDao.updateAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public GroupShift saveAdd(GroupShift bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String groupShiftId;
            try {
                // Generating ID, get from postgre sequence
                groupShiftId = groupShiftDao.getNextGroupShifId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisGroupShift entityData = new ImHrisGroupShift();

            entityData.setGroupShiftId("GS"+groupShiftId);
            entityData.setShiftId(bean.getShiftId());
            entityData.setGroupShiftName(bean.getGroupShiftName());
            entityData.setGroupId(bean.getGroupId());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                groupShiftDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<GroupShift> getByCriteria(GroupShift searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<GroupShift> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGroupShiftId() != null && !"".equalsIgnoreCase(searchBean.getGroupShiftId())) {
                hsCriteria.put("group_shift_id", searchBean.getGroupShiftId());
            }
            if (searchBean.getGroupId() != null && !"".equalsIgnoreCase(searchBean.getGroupId())) {
                hsCriteria.put("group_id", searchBean.getGroupId());
            }
            if (searchBean.getShiftId() != null && !"".equalsIgnoreCase(searchBean.getShiftId())) {
                hsCriteria.put("shift_id", searchBean.getShiftId());
            }
            if (searchBean.getGroupShiftId() != null && !"".equalsIgnoreCase(searchBean.getGroupShiftId())) {
                hsCriteria.put("group_shift_id", searchBean.getGroupShiftId());
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


            List<ImHrisGroupShift> imHrisGroupShiftList = null;
            ImHrisShiftEntity imHrisShiftEntity= null;
            ImHrisGroupEntity imHrisGroupEntity = null;

            try {
                imHrisGroupShiftList = groupShiftDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisGroupShiftList != null){
                GroupShift returnData;
                // Looping from dao to object and save in collection
                for(ImHrisGroupShift listEntity : imHrisGroupShiftList){
                    returnData = new GroupShift();
                    imHrisShiftEntity = shiftDao.getById("shiftId",listEntity.getShiftId());
                    imHrisGroupEntity = groupDao.getById("groupId",listEntity.getGroupId());

                    returnData.setGroupShiftId(listEntity.getGroupShiftId());
                    returnData.setShiftId(listEntity.getShiftId());
                    returnData.setGroupId(listEntity.getGroupId());
                    returnData.setGroupShiftName(listEntity.getGroupShiftName());

                    returnData.setGroupName(imHrisGroupEntity.getGroupName());
                    returnData.setShiftName(imHrisShiftEntity.getShiftName());

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
    public List<GroupShift> getByCriteriaShift(GroupShift searchBean) throws GeneralBOException {
        logger.info("[GroupShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<GroupShift> listOfResult = new ArrayList();
        ImHrisShiftEntity imHrisShiftEntity= new ImHrisShiftEntity();
        ImHrisGroupEntity imHrisGroupEntity = new ImHrisGroupEntity();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            if (searchBean.getShiftId()!=null && !"".equalsIgnoreCase(searchBean.getShiftId())){
                hsCriteria.put("shift_id", searchBean.getShiftId());
            }
            hsCriteria.put("flag", "Y");


            List<ImHrisGroupShift> imHrisGroupShifts = null;
            ImHrisGroupShift imHrisGroupShift= null;
            try {
                imHrisGroupShifts = groupShiftDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GroupShiftBoImpl.getByCriteriaShift] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (imHrisGroupShifts!=null){
                GroupShift returnGroupShift;
                    // Looping from dao to object and save in collection
                for (ImHrisGroupShift groupShiftEntity : imHrisGroupShifts){
                    returnGroupShift = new GroupShift();
                    imHrisShiftEntity = shiftDao.getById("shiftId",groupShiftEntity.getShiftId(),"Y");
                    imHrisGroupEntity = groupDao.getById("groupId",groupShiftEntity.getGroupId(),"Y");

                    returnGroupShift.setGroupShiftId(groupShiftEntity.getGroupShiftId());
                    returnGroupShift.setShiftId(groupShiftEntity.getShiftId());
                    returnGroupShift.setGroupId(groupShiftEntity.getGroupId());
                    returnGroupShift.setShiftName(imHrisShiftEntity.getShiftName());
                    returnGroupShift.setGroupName(imHrisGroupEntity.getGroupName());

                    returnGroupShift.setCreatedWho(groupShiftEntity.getCreateDateWho());
                    returnGroupShift.setCreatedDate(groupShiftEntity.getCreatedDate());
                    returnGroupShift.setLastUpdate(groupShiftEntity.getLastUpdate());
                    returnGroupShift.setAction(groupShiftEntity.getAction());
                    returnGroupShift.setFlag(groupShiftEntity.getFlag());
                    listOfResult.add(returnGroupShift);
                }
            }
        }
        logger.info("[BiodataBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public List<GroupShift> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
