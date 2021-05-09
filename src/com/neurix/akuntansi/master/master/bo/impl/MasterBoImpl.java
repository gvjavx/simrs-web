package com.neurix.akuntansi.master.master.bo.impl;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
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
public class MasterBoImpl implements MasterBo {

    protected static transient Logger logger = Logger.getLogger(MasterBoImpl.class);
    private MasterDao masterDao;
    private DokterDao dokterDao;
    private PasienDao pasienDao;
    private BiodataDao biodataDao;

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public PasienDao getPasienDao() {
        return pasienDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public DokterDao getDokterDao() {
        return dokterDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    @Override
    public void saveDelete(Master bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Master bean) throws GeneralBOException {
        logger.info("[MasterBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ImMasterEntity masterEntity= null;
            try {
                // Get data from database by ID
//                masterEntity = masterDao.getById("akunMasterId", bean.getAkunMasterId());
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (masterEntity != null) {
                if (("Y").equalsIgnoreCase(bean.getFlag())){

                }

                masterEntity.setFlag(bean.getFlag());
                masterEntity.setAction(bean.getAction());
                masterEntity.setLastUpdateWho(bean.getLastUpdateWho());
                masterEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    masterDao.updateAndSave(masterEntity);
                } catch (HibernateException e) {
                    logger.error("[MasterBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MasterBoImpl.saveEdit] Error, not found data with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data with request id, please check again your data ...");
            }
        }
        logger.info("[MasterBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<Master> getByCriteria(Master searchBean) throws GeneralBOException {
        logger.info("[MasterBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Master> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImMasterEntity> imMasterEntityList = null;
            try {
                imMasterEntityList = masterDao.getByInput(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imMasterEntityList != null){
                Master returnMaster;
                // Looping from dao to object and save in collection
                for(ImMasterEntity masterEntity : imMasterEntityList){
                    returnMaster = new Master();
                    returnMaster.setPrimaryKey(masterEntity.getPrimaryKey());
                    returnMaster.setNoMaster(masterEntity.getPrimaryKey().getNomorMaster());

                    returnMaster.setCreatedWho(masterEntity.getCreatedWho());
                    returnMaster.setCreatedDate(masterEntity.getCreatedDate());
                    returnMaster.setLastUpdate(masterEntity.getLastUpdate());
                    returnMaster.setAction(masterEntity.getAction());
                    returnMaster.setFlag(masterEntity.getFlag());
                    listOfResult.add(returnMaster);
                }
            }
        }
        logger.info("[MasterBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public Master saveAdd(Master bean) throws GeneralBOException {
        logger.info("[MasterBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String masterId;
            try {
                // Generating ID, get from postgre sequence
                masterId = masterDao.getNextMasterId();
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImMasterEntity imMasterEntity = new ImMasterEntity();

            imMasterEntity.setFlag(bean.getFlag());
            imMasterEntity.setAction(bean.getAction());
            imMasterEntity.setCreatedWho(bean.getCreatedWho());
            imMasterEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imMasterEntity.setCreatedDate(bean.getCreatedDate());
            imMasterEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                masterDao.addAndSave(imMasterEntity);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data , please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MasterBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<Master> typeaheadMaster(String key) throws GeneralBOException {
        logger.info("[MasterBoImpl.typeaheadMaster] start process >>>");

        // Mapping with collection and put
        List<Master> listOfResult = new ArrayList();
        List<ImMasterEntity> imMasterEntityList = null;
        try {

            imMasterEntityList = masterDao.getMasterListByLike(key);
        } catch (HibernateException e) {
            logger.error("[MasterBoImpl.typeaheadMaster] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(imMasterEntityList != null){
            Master returnMaster;
            // Looping from dao to object and save in collection
            for(ImMasterEntity masterEntity : imMasterEntityList){
                returnMaster = new Master();
//                returnMaster.setAkunMasterId(masterEntity.getAkunMasterId());
                returnMaster.setNomorVendor(masterEntity.getPrimaryKey().getNomorMaster());
                returnMaster.setNama(masterEntity.getNama());
                listOfResult.add(returnMaster);
            }
        }
        logger.info("[MasterBoImpl.typeaheadMaster] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Master> typeaheadMasterPembayaran(String key, String tipeMaster) throws GeneralBOException {
        logger.info("[MasterBoImpl.typeaheadMasterPembayaran] start process >>>");

        // Mapping with collection and put
        List<Master> listOfResult = new ArrayList();
        List<ImMasterEntity> imMasterEntityList = null;
        List<ImSimrsDokterEntity> imSimrsDokterEntityList= null;
        List<ImSimrsPasienEntity> imSimrsPasienEntityList= null;
        List<ImBiodataEntity> imBiodataEntityList= null;

        if ("dokter".equalsIgnoreCase(tipeMaster)){
            try {
                imSimrsDokterEntityList = dokterDao.getDokterListByLike(key);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.typeaheadMasterPembayaran] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSimrsDokterEntityList != null){
                Master returnMaster;
                // Looping from dao to object and save in collection
                for(ImSimrsDokterEntity dokterEntity : imSimrsDokterEntityList){
                    returnMaster = new Master();
                    returnMaster.setNomorVendor(dokterEntity.getKodering());
                    returnMaster.setNama(dokterEntity.getNamaDokter());
                    listOfResult.add(returnMaster);
                }
            }
        }else if ("pasien".equalsIgnoreCase(tipeMaster)){
            try {
                imSimrsPasienEntityList = pasienDao.getPasienListByLike(key);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.typeaheadMasterPembayaran] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSimrsPasienEntityList != null){
                Master returnMaster;
                // Looping from dao to object and save in collection
                for(ImSimrsPasienEntity pasienEntity : imSimrsPasienEntityList){
                    returnMaster = new Master();
                    returnMaster.setNomorVendor(pasienEntity.getIdPasien());
                    returnMaster.setNama(pasienEntity.getNama());
                    listOfResult.add(returnMaster);
                }
            }
        }else if ("karyawan".equalsIgnoreCase(tipeMaster)){
            try {
                imBiodataEntityList = biodataDao.getBiodataListByLike(key);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.typeaheadMasterPembayaran] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imBiodataEntityList != null){
                Master returnMaster;
                // Looping from dao to object and save in collection
                for(ImBiodataEntity biodataEntity: imBiodataEntityList){
                    returnMaster = new Master();
                    returnMaster.setNomorVendor(biodataEntity.getNip());
                    returnMaster.setNama(biodataEntity.getNamaPegawai());
                    listOfResult.add(returnMaster);
                }
            }
        }else {
            try {
                imMasterEntityList = masterDao.getMasterListByLike(key);
            } catch (HibernateException e) {
                logger.error("[MasterBoImpl.typeaheadMasterPembayaran] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imMasterEntityList != null){
                Master returnMaster;
                // Looping from dao to object and save in collection
                for(ImMasterEntity masterEntity : imMasterEntityList){
                    returnMaster = new Master();
                    returnMaster.setNomorVendor(masterEntity.getPrimaryKey().getNomorMaster());
                    returnMaster.setNama(masterEntity.getNama());
                    listOfResult.add(returnMaster);
                }
            }
        }
        logger.info("[MasterBoImpl.typeaheadMasterPembayaran] end process <<<");
        return listOfResult;
    }

    @Override
    public ImMasterEntity getEntityMasterById(String id) throws GeneralBOException {
        return masterDao.getById("primaryKey.nomorMaster", id);
    }

    @Override
    public List<Master> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasterBoImpl.logger = logger;
    }

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }


}
