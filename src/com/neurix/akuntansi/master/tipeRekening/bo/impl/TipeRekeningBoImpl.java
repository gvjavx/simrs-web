package com.neurix.akuntansi.master.tipeRekening.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeRekening.bo.TipeRekeningBo;
import com.neurix.akuntansi.master.tipeRekening.dao.TipeRekeningDao;
import com.neurix.akuntansi.master.tipeRekening.model.ImTipeRekeningEntity;
import com.neurix.akuntansi.master.tipeRekening.model.TipeRekening;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
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
public class TipeRekeningBoImpl implements TipeRekeningBo {

    protected static transient Logger logger = Logger.getLogger(TipeRekeningBoImpl.class);
    private TipeRekeningDao tipeRekeningDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeRekeningBoImpl.logger = logger;
    }

    public TipeRekeningDao getTipeRekeningDao() {
        return tipeRekeningDao;
    }

    public void setTipeRekeningDao(TipeRekeningDao tipeRekeningDao) {
        this.tipeRekeningDao = tipeRekeningDao;
    }

    @Override
    public void saveDelete(TipeRekening bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImTipeRekeningEntity imTipeRekeningEntity = new ImTipeRekeningEntity();
            try {
                // Get data from database by ID
                imTipeRekeningEntity = tipeRekeningDao.getById("tipeRekeningId", bean.getTipeRekeningId());
            } catch (HibernateException e) {
                logger.error("[TipeRekeningBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTipeRekeningEntity != null) {
                // Modify from bean to entity serializable
                imTipeRekeningEntity.setFlag(bean.getFlag());
                imTipeRekeningEntity.setAction(bean.getAction());
                imTipeRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeRekeningEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    tipeRekeningDao.updateAndSave(imTipeRekeningEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeRekeningBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeRekening, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[TipeRekeningBoImpl.saveDelete] Error, not found data TipeRekening with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeRekening with request id, please check again your data ...");

            }
        }
        logger.info("[TipeRekeningBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipeRekening bean) throws GeneralBOException {
        logger.info("[TipeRekeningBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImTipeRekeningEntity imTipeRekeningEntity = null;
            try {
                // Get data from database by ID
                imTipeRekeningEntity = tipeRekeningDao.getById("tipeRekeningId", bean.getTipeRekeningId());
            } catch (HibernateException e) {
                logger.error("[TipeRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data TipeRekening by Kode TipeRekening, please inform to your admin...," + e.getMessage());
            }
            if (imTipeRekeningEntity != null) {
                imTipeRekeningEntity.setTipeRekeningName(bean.getTipeRekeningName());
                imTipeRekeningEntity.setFlag(bean.getFlag());
                imTipeRekeningEntity.setAction(bean.getAction());
                imTipeRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeRekeningEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    tipeRekeningDao.updateAndSave(imTipeRekeningEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeRekening, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipeRekeningBoImpl.saveEdit] Error, not found data TipeRekening with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeRekening with request id, please check again your data ...");
            }
        }
        logger.info("[TipeRekeningBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeRekening saveAdd(TipeRekening bean) throws GeneralBOException {
        logger.info("[TipeRekeningBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String tipeRekeningId;
            try {
                // Generating ID, get from postgre sequence
                tipeRekeningId = tipeRekeningDao.getNextTipeRekeningId();
            } catch (HibernateException e) {
                logger.error("[TipeRekeningBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipeRekeningId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImTipeRekeningEntity imTipeRekeningEntity = new ImTipeRekeningEntity();

            imTipeRekeningEntity.setTipeRekeningId(tipeRekeningId);
            imTipeRekeningEntity.setTipeRekeningName(bean.getTipeRekeningName());
            imTipeRekeningEntity.setFlag(bean.getFlag());
            imTipeRekeningEntity.setAction(bean.getAction());
            imTipeRekeningEntity.setCreatedWho(bean.getCreatedWho());
            imTipeRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTipeRekeningEntity.setCreatedDate(bean.getCreatedDate());
            imTipeRekeningEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tipeRekeningDao.addAndSave(imTipeRekeningEntity);
            } catch (HibernateException e) {
                logger.error("[TipeRekeningBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data TipeRekening, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipeRekeningBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeRekening> getByCriteria(TipeRekening searchBean) throws GeneralBOException {
        logger.info("[TipeRekeningBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeRekening> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeRekeningId() != null && !"".equalsIgnoreCase(searchBean.getTipeRekeningId())) {
                hsCriteria.put("tipe_rekening_id", searchBean.getTipeRekeningId());
            }
            if (searchBean.getTipeRekeningName() != null && !"".equalsIgnoreCase(searchBean.getTipeRekeningName())) {
                hsCriteria.put("tipe_rekening_name", searchBean.getTipeRekeningName());
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

            List<ImTipeRekeningEntity> imTipeRekeningEntity = null;
            try {

                imTipeRekeningEntity = tipeRekeningDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipeRekeningBoImpl.getSearchTipeRekeningByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTipeRekeningEntity != null){
                TipeRekening returnTipeRekening;
                // Looping from dao to object and save in collection
                for(ImTipeRekeningEntity tipeRekeningEntity : imTipeRekeningEntity){
                    returnTipeRekening = new TipeRekening();
                    returnTipeRekening.setTipeRekeningId(tipeRekeningEntity.getTipeRekeningId());
                    returnTipeRekening.setTipeRekeningName(tipeRekeningEntity.getTipeRekeningName());;

                    returnTipeRekening.setCreatedWho(tipeRekeningEntity.getCreatedWho());
                    returnTipeRekening.setCreatedDate(tipeRekeningEntity.getCreatedDate());
                    returnTipeRekening.setLastUpdate(tipeRekeningEntity.getLastUpdate());
                    returnTipeRekening.setAction(tipeRekeningEntity.getAction());
                    returnTipeRekening.setFlag(tipeRekeningEntity.getFlag());
                    listOfResult.add(returnTipeRekening);
                }
            }
        }
        logger.info("[TipeRekeningBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeRekening> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
