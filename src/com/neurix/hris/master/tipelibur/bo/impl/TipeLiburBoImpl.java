package com.neurix.hris.master.tipelibur.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.libur.action.LiburAction;
import com.neurix.hris.master.libur.dao.LiburDao;
import com.neurix.hris.master.libur.model.ImLiburEntity;
import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.dao.TipeLiburDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLiburHistory;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TipeLiburBoImpl implements TipeLiburBo{
    protected static transient Logger logger = Logger.getLogger(TipeLiburBoImpl.class);

    private TipeLiburDao tipeLiburDao;
    private LiburDao liburDao;

    public void setLiburDao(LiburDao liburDao) {
        this.liburDao = liburDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeLiburBoImpl.logger = logger;
    }

    public TipeLiburDao getTipeLiburDao() {
        return tipeLiburDao;
    }

    public void setTipeLiburDao(TipeLiburDao tipeLiburDao) {
        this.tipeLiburDao = tipeLiburDao;
    }

    @Override
    public void saveDelete(TipeLibur bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(TipeLibur bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveEdit] start process >>>");
        boolean saved = false;
        if (bean!=null) {
            String liburId = bean.getTipeLiburId();
            ImHrisTipeLibur imHrisTipeLibur = null;
            ImHrisTipeLiburHistory historyEntity = new ImHrisTipeLiburHistory();
            String tipelLiburIdHistory = "";

            List<ImLiburEntity> liburEntityList =new ArrayList();
            if("N".equalsIgnoreCase(bean.getFlag())){
                Map criteria = new HashMap();
                criteria.put("tipe_libur_id",bean.getTipeLiburId());
                criteria.put("flag", "Y");
                try {
                    liburEntityList = liburDao.getByCriteria(criteria);
                }catch(HibernateException e){
                    logger.error("[TipeLiburBoImpl] Error, "+e.getMessage());
                    throw new GeneralBOException("Found problem when retrieving Libur, " + e.getMessage());
                }

                if(liburEntityList.size() != 0){
                    logger.error("Data tidak dapat dihapus karena telah digunakan transaksi.");
                    throw new GeneralBOException("Data tidak dapat dihapus karena telah digunakan transaksi.");
                }
            }else if("U".equalsIgnoreCase(bean.getAction())) {
                List<ImHrisTipeLibur> tipeLiburList = new ArrayList();
                try {
                    Map criteria = new HashMap();
                    criteria.put("tipe_libur_name", bean.getTipeLiburName());
                    criteria.put("flag", "Y");
                    tipeLiburList = tipeLiburDao.getByCriteria(criteria);
                } catch (HibernateException e) {
                    logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting tipe libur by criteria, please info to your admin..." + e.getMessage());
                }

                if(tipeLiburList.size()>0){
                    logger.error("Data dengan nama nama tersebut sudah tersedia.");
                    throw new GeneralBOException("Data tipe libur dengan nama tersebut sudah tersedia.");
                }
            }


            try {
                // Get data from database by ID
                imHrisTipeLibur = tipeLiburDao.getById("tipeLiburId", liburId);
                tipelLiburIdHistory = tipeLiburDao.getNextLiburHistoryId();
            } catch (HibernateException e) {
                logger.error("[TipeLiburBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Tipe Libur by ID, please inform to your admin...," + e.getMessage());
            }

            if (imHrisTipeLibur != null){
                historyEntity.setTipeLiburHistoryId(tipelLiburIdHistory);
                historyEntity.setTipeLiburId(imHrisTipeLibur.getTipeLiburId());
                historyEntity.setTipeLiburName(imHrisTipeLibur.getTipeLiburName());
                historyEntity.setFlag(imHrisTipeLibur.getFlag());
                historyEntity.setAction(imHrisTipeLibur.getAction());
                historyEntity.setCreateDateWho(imHrisTipeLibur.getCreateDateWho());
                historyEntity.setLastUpdateWho(imHrisTipeLibur.getLastUpdateWho());
                historyEntity.setCreatedDate(imHrisTipeLibur.getCreatedDate());
                historyEntity.setLastUpdate(imHrisTipeLibur.getLastUpdate());

                imHrisTipeLibur.setTipeLiburId(bean.getTipeLiburId());
                imHrisTipeLibur.setTipeLiburName(bean.getTipeLiburName());
                imHrisTipeLibur.setFlag(bean.getFlag());
                imHrisTipeLibur.setAction(bean.getAction());
                imHrisTipeLibur.setLastUpdateWho(bean.getLastUpdateWho());
                imHrisTipeLibur.setLastUpdate(bean.getLastUpdate());
                try {
                    tipeLiburDao.updateAndSave(imHrisTipeLibur);
                    tipeLiburDao.addAndSaveHistory(historyEntity);
                    saved = true;
                } catch (HibernateException e) {
                    logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }
        }

//        if (saved){
//            String id;
//            try {
//                // Generating ID, get from postgre sequence
//                id = tipeLiburDao.getNextLiburHistoryId();
//            } catch (HibernateException e) {
//                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
//            }
//
//            ImHrisTipeLiburHistory entityData = new ImHrisTipeLiburHistory();
//            entityData.setTipeLiburId(bean.getTipeLiburId());
//            entityData.setTipeLiburName(bean.getTipeLiburName());
//            entityData.setFlag(bean.getFlag());
//            entityData.setAction(bean.getAction());
//            entityData.setCreateDateWho(bean.getCreatedWho());
//            entityData.setLastUpdateWho(bean.getLastUpdateWho());
//            entityData.setCreatedDate(bean.getCreatedDate());
//            entityData.setLastUpdate(bean.getLastUpdate());
//            entityData.setId(id);
//            try {
//                tipeLiburDao.addAndSaveHistory(entityData);
//                saved = true;
//            } catch (HibernateException e) {
//                logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
//            }
//        }
        logger.info("[TipePegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeLibur saveAdd(TipeLibur bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            List<ImHrisTipeLibur> tipeLiburList = new ArrayList();
            try{
                Map criteria = new HashMap();
                criteria.put("tipe_libur_name", bean.getTipeLiburName());
                criteria.put("flag", "Y");
                tipeLiburList = tipeLiburDao.getByCriteria(criteria);
            }catch (HibernateException e){
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting tipe libur by criteria, please info to your admin..." + e.getMessage());
            }

            if(tipeLiburList.size()==0) {
                String liburId;
                try {
                    // Generating ID, get from postgre sequence
                    liburId = tipeLiburDao.getNextLiburId();
                } catch (HibernateException e) {
                    logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence tipe libur id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImHrisTipeLibur entityData = new ImHrisTipeLibur();

                entityData.setTipeLiburId("TL" + liburId);
                entityData.setTipeLiburName(bean.getTipeLiburName());
                entityData.setFlag(bean.getFlag());
                entityData.setAction(bean.getAction());
                entityData.setCreateDateWho(bean.getCreatedWho());
                entityData.setLastUpdateWho(bean.getLastUpdateWho());
                entityData.setCreatedDate(bean.getCreatedDate());
                entityData.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    tipeLiburDao.addAndSave(entityData);
                } catch (HibernateException e) {
                    logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }else {
                logger.error("Data dengan nama nama tersebut sudah tersedia.");
                throw new GeneralBOException("Data tipe libur dengan nama tersebut sudah tersedia.");
            }
        }

        logger.info("[TipePegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeLibur> getByCriteria(TipeLibur searchBean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeLibur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeLiburId() != null && !"".equalsIgnoreCase(searchBean.getTipeLiburId())) {
                hsCriteria.put("tipe_libur_id", searchBean.getTipeLiburId());
            }
            if (searchBean.getTipeLiburName() != null && !"".equalsIgnoreCase(searchBean.getTipeLiburName())) {
                hsCriteria.put("tipe_libur_name", searchBean.getTipeLiburName());
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


            List<ImHrisTipeLibur> imApbAlatEntities = null;
            try {

                imApbAlatEntities = tipeLiburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imApbAlatEntities != null){
                TipeLibur returnData;
                // Looping from dao to object and save in collection
                for(ImHrisTipeLibur liburEntity : imApbAlatEntities){
                    returnData = new TipeLibur();
                    returnData.setTipeLiburId(liburEntity.getTipeLiburId());
                    returnData.setTipeLiburName(liburEntity.getTipeLiburName());
                    returnData.setStCreatedDate(liburEntity.getCreatedDate().toString());
                    returnData.setStLastUpdate(liburEntity.getLastUpdate().toString());
                    returnData.setCreatedDate(liburEntity.getCreatedDate());
                    returnData.setCreatedWho(liburEntity.getCreateDateWho());
                    returnData.setLastUpdate(liburEntity.getLastUpdate());
                    returnData.setLastUpdateWho(liburEntity.getLastUpdateWho());
                    returnData.setFlag(liburEntity.getFlag());
                    returnData.setAction(liburEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[TipePegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeLibur> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
