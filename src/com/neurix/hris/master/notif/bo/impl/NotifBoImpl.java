package com.neurix.hris.master.notif.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.notif.bo.NotifBo;
import com.neurix.hris.master.notif.dao.NotifDao;
import com.neurix.hris.master.notif.model.ImNotif;
import com.neurix.hris.master.notif.model.Notif;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class NotifBoImpl implements NotifBo {
    protected static transient Logger logger = Logger.getLogger(NotifBoImpl.class);

    private NotifDao notifDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        NotifBoImpl.logger = logger;
    }

    public NotifDao getNotifDao() {
        return notifDao;
    }

    public void setNotifDao(NotifDao notifDao) {
        this.notifDao = notifDao;
    }

    @Override
    public void saveDelete(Notif bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Notif bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");

//        if (bean!=null) {
//            ItHrisTrainingPersonEntity entityData = new ItHrisTrainingPersonEntity();
//            entityData.setGroupId(bean.getGroupId());
//            entityData.setGroupName(bean.getGroupName());
//            entityData.setFlag(bean.getFlag());
//            entityData.setAction(bean.getAction());
//            entityData.setCreateDateWho(bean.getCreatedWho());
//            entityData.setLastUpdateWho(bean.getLastUpdateWho());
//            entityData.setCreatedDate(bean.getCreatedDate());
//            entityData.setLastUpdate(bean.getLastUpdate());
//
//            try {
//                groupDao.updateAndSave(entityData);
//            } catch (HibernateException e) {
//                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
//            }
//        }


        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Notif saveAdd(Notif bean) throws GeneralBOException {
               logger.info("[ShiftBoImpl.saveAdd] start process >>>");

//        if (bean!=null) {
//
//            String shiftId;
//            try {
//                // Generating ID, get from postgre sequence
//                shiftId = groupDao.getNextGroupId();
//            } catch (HibernateException e) {
//                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
//            }
//
//            ItHrisTrainingPersonEntity entityData = new ItHrisTrainingPersonEntity();
//
//            entityData.setGroupId("GR"+shiftId);
//            entityData.setGroupName(bean.getGroupName());
//            entityData.setFlag(bean.getFlag());
//            entityData.setAction(bean.getAction());
//            entityData.setCreateDateWho(bean.getCreatedWho());
//            entityData.setLastUpdateWho(bean.getLastUpdateWho());
//            entityData.setCreatedDate(bean.getCreatedDate());
//            entityData.setLastUpdate(bean.getLastUpdate());
//
//            try {
//                groupDao.addAndSave(entityData);
//            } catch (HibernateException e) {
//                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
//            }
//        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Notif> getByCriteria(Notif searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Notif> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNotifId() != null && !"".equalsIgnoreCase(searchBean.getNotifId())) {
                hsCriteria.put("notif_id", searchBean.getNotifId());
            }
            if (searchBean.getNotifName() != null && !"".equalsIgnoreCase(searchBean.getNotifName())) {
                hsCriteria.put("notif_name", searchBean.getNotifName());
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


            List<ImNotif> imNotifs = null;
            try {

                imNotifs = notifDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imNotifs != null){
                Notif returnData;
                // Looping from dao to object and save in collection
                for(ImNotif listEntity : imNotifs){
                    returnData = new Notif();
                    returnData.setNotifId(listEntity.getNotifId());
                    returnData.setNotifName(listEntity.getNotifName());
                    returnData.setUrl(listEntity.getUrl());
                    returnData.setTypeNotif(listEntity.getTypeNotif());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    Integer jml = imNotifs.size();
                    String stJml = jml.toString();
                    returnData.setJml(stJml);
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    public void getReadNotif(Notif bean) throws GeneralBOException{
        if (bean.getNotifId() != null){
            ImNotif notifNew = new ImNotif();
            notifNew.setNotifId(bean.getNotifId());
            notifNew.setNotifName(bean.getNotifName());
            notifNew.setUrl(bean.getUrl());
            notifNew.setTypeNotif(bean.getTypeNotif());
            notifNew.setCreatedDate(bean.getCreatedDate());
            notifNew.setCreateDateWho(bean.getCreatedWho());
            notifNew.setLastUpdate(bean.getLastUpdate());
            notifNew.setLastUpdateWho(bean.getLastUpdateWho());
            notifNew.setFlag(bean.getFlag());
            notifNew.setAction(bean.getAction());
            try {
                notifDao.updateAndSave(notifNew);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when save data by criteria, please info to your admin..." + e.getMessage());
            }
        }
    }

    public List<Notif> getCount() throws GeneralBOException{
//        String jml = "";
        Integer intJumlah;
        List<Notif> listOfResult = new ArrayList<Notif>();
        try {
//         intJumlah = notifDao.getCount("notif_id","20","Y");
            listOfResult = notifDao.getJumlahNotif();
        } catch (HibernateException e) {
            logger.error("[NotifBoImpl.getCount] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if (intJumlah != null){
//            Notif notif = new Notif();
//            String jml = intJumlah.toString();
//            notif.setJml(jml);
//            listOfResult.add(notif);
//        }
        return listOfResult;
    }

    @Override
    public List<Notif> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
