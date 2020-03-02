package com.neurix.akuntansi.master.mappingJurnal.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.tipeJurnal.dao.TipeJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.common.exception.GeneralBOException;
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
public class MappingJurnalBoImpl implements MappingJurnalBo {

    protected static transient Logger logger = Logger.getLogger(MappingJurnalBoImpl.class);
    private MappingJurnalDao mappingJurnalDao;
    private TipeJurnalDao tipeJurnalDao;
    private TransDao transDao;

    public TipeJurnalDao getTipeJurnalDao() {
        return tipeJurnalDao;
    }

    public void setTipeJurnalDao(TipeJurnalDao tipeJurnalDao) {
        this.tipeJurnalDao = tipeJurnalDao;
    }

    public TransDao getTransDao() {
        return transDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MappingJurnalBoImpl.logger = logger;
    }

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    @Override
    public void saveDelete(MappingJurnal bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImMappingJurnalEntity imMappingJurnalEntity = new ImMappingJurnalEntity();
            try {
                // Get data from database by ID
                imMappingJurnalEntity = mappingJurnalDao.getById("mappingJurnalId", bean.getMappingJurnalId());
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imMappingJurnalEntity != null) {
                // Modify from bean to entity serializable
                imMappingJurnalEntity.setFlag(bean.getFlag());
                imMappingJurnalEntity.setAction(bean.getAction());
                imMappingJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMappingJurnalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    mappingJurnalDao.updateAndSave(imMappingJurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[MappingJurnalBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MappingJurnal, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[MappingJurnalBoImpl.saveDelete] Error, not found data MappingJurnal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MappingJurnal with request id, please check again your data ...");

            }
        }
        logger.info("[MappingJurnalBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MappingJurnal bean) throws GeneralBOException {
        logger.info("[MappingJurnalBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImMappingJurnalEntity imMappingJurnalEntity = null;
            try {
                // Get data from database by ID
                imMappingJurnalEntity = mappingJurnalDao.getById("mappingJurnalId", bean.getMappingJurnalId());
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data MappingJurnal by Kode MappingJurnal, please inform to your admin...," + e.getMessage());
            }
            if (imMappingJurnalEntity != null) {
                imMappingJurnalEntity.setFlag(bean.getFlag());
                imMappingJurnalEntity.setAction(bean.getAction());
                imMappingJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMappingJurnalEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    mappingJurnalDao.updateAndSave(imMappingJurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[MappingJurnalBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MappingJurnal, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MappingJurnalBoImpl.saveEdit] Error, not found data MappingJurnal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MappingJurnal with request id, please check again your data ...");
            }
        }
        logger.info("[MappingJurnalBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MappingJurnal saveAdd(MappingJurnal bean) throws GeneralBOException {
        logger.info("[MappingJurnalBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String mappingJurnalId;
            try {
                // Generating ID, get from postgre sequence
                mappingJurnalId = mappingJurnalDao.getNextMappingJurnalId();
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence mappingJurnalId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImMappingJurnalEntity imMappingJurnalEntity = new ImMappingJurnalEntity();

            imMappingJurnalEntity.setMappingJurnalId(mappingJurnalId);
            imMappingJurnalEntity.setFlag(bean.getFlag());
            imMappingJurnalEntity.setAction(bean.getAction());
            imMappingJurnalEntity.setCreatedWho(bean.getCreatedWho());
            imMappingJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imMappingJurnalEntity.setCreatedDate(bean.getCreatedDate());
            imMappingJurnalEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                mappingJurnalDao.addAndSave(imMappingJurnalEntity);
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data MappingJurnal, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MappingJurnalBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MappingJurnal> getByCriteria(MappingJurnal searchBean) throws GeneralBOException {
        logger.info("[MappingJurnalBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MappingJurnal> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeJurnalId() != null && !"".equalsIgnoreCase(searchBean.getTipeJurnalId())) {
                hsCriteria.put("tipe_jurnal_id", searchBean.getTipeJurnalId());
            }
            if (searchBean.getTransId() != null && !"".equalsIgnoreCase(searchBean.getTransId())) {
                hsCriteria.put("trans_id", searchBean.getTransId());
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

            List<ImMappingJurnalEntity> imMappingJurnalEntity = null;
            try {
                imMappingJurnalEntity = mappingJurnalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.getSearchMappingJurnalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imMappingJurnalEntity != null){
                MappingJurnal returnMappingJurnal;
                // Looping from dao to object and save in collection
                for(ImMappingJurnalEntity mappingJurnalEntity : imMappingJurnalEntity){
                    returnMappingJurnal = new MappingJurnal();
                    returnMappingJurnal.setMappingJurnalId(mappingJurnalEntity.getMappingJurnalId());
                    returnMappingJurnal.setTipeJurnalId(mappingJurnalEntity.getTipeJurnalId());

                    ImTipeJurnalEntity tipeJurnalEntity=null;
                    try {
                        tipeJurnalEntity = tipeJurnalDao.getById("tipeJurnalId",mappingJurnalEntity.getTipeJurnalId());
                    } catch (HibernateException e) {
                        logger.error("[MappingJurnalBoImpl.getSearchMappingJurnalByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                    if (tipeJurnalEntity!=null){
                        returnMappingJurnal.setTipeJurnalName(tipeJurnalEntity.getTipeJurnalName());
                    }else{
                        returnMappingJurnal.setTipeJurnalName("");
                    }
                    ImTransEntity transEntity=null;
                    try {
                        transEntity = transDao.getById("transId",mappingJurnalEntity.getTransId());
                    } catch (HibernateException e) {
                        logger.error("[MappingJurnalBoImpl.getSearchMappingJurnalByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                    if (transEntity!=null){
                        returnMappingJurnal.setTransName(transEntity.getTransName());
                    }else{
                        returnMappingJurnal.setTransName("");
                    }
                    returnMappingJurnal.setKeterangan(mappingJurnalEntity.getKeterangan());
                    returnMappingJurnal.setKodeRekening(mappingJurnalEntity.getKodeRekening());
                    returnMappingJurnal.setMasterId(mappingJurnalEntity.getMasterId());
                    returnMappingJurnal.setKodeBarang(mappingJurnalEntity.getKodeBarang());
                    returnMappingJurnal.setPosisi(mappingJurnalEntity.getPosisi());
                    returnMappingJurnal.setBukti(mappingJurnalEntity.getBukti());
                    returnMappingJurnal.setCreatedWho(mappingJurnalEntity.getCreatedWho());
                    returnMappingJurnal.setCreatedDate(mappingJurnalEntity.getCreatedDate());
                    returnMappingJurnal.setLastUpdate(mappingJurnalEntity.getLastUpdate());
                    returnMappingJurnal.setAction(mappingJurnalEntity.getAction());
                    returnMappingJurnal.setFlag(mappingJurnalEntity.getFlag());
                    listOfResult.add(returnMappingJurnal);
                }
            }
        }
        logger.info("[MappingJurnalBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MappingJurnal> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
