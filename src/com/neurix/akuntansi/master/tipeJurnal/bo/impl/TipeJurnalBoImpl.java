package com.neurix.akuntansi.master.tipeJurnal.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.tipeJurnal.dao.TipeJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.tipeJurnal.model.TipeJurnal;
import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class TipeJurnalBoImpl implements TipeJurnalBo {

    protected static transient Logger logger = Logger.getLogger(TipeJurnalBoImpl.class);
    private TipeJurnalDao tipeJurnalDao;
    private MappingJurnalDao mappingJurnalDao;
    private BiodataDao biodataDao;
    private KodeRekeningDao kodeRekeningDao;

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeJurnalBoImpl.logger = logger;
    }

    public TipeJurnalDao getTipeJurnalDao() {
        return tipeJurnalDao;
    }

    public void setTipeJurnalDao(TipeJurnalDao tipeJurnalDao) {
        this.tipeJurnalDao = tipeJurnalDao;
    }

    @Override
    public void saveDelete(TipeJurnal bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            //Validasi jurnal pada mapping jurnal
            List<ImMappingJurnalEntity> mappingJurnalEntityList = new ArrayList<>();
            try {
                mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTipeJurnalId(bean.getTipeJurnalId());
            } catch (HibernateException e) {
                logger.error("[TipeJurnalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }
            if (mappingJurnalEntityList.size()>0){
                String status = "ERROR : Tipe jurnal sudah ada pada Mapping";
                logger.error("[TipeJurnalBoImpl.saveDelete] "+status);
                throw new GeneralBOException(status);
            }

            ImTipeJurnalEntity imTipeJurnalEntity = new ImTipeJurnalEntity();
            try {
                // Get data from database by ID
                imTipeJurnalEntity = tipeJurnalDao.getById("tipeJurnalId", bean.getTipeJurnalId());
            } catch (HibernateException e) {
                logger.error("[TipeJurnalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTipeJurnalEntity != null) {
                // Modify from bean to entity serializable
                imTipeJurnalEntity.setFlag(bean.getFlag());
                imTipeJurnalEntity.setAction(bean.getAction());
                imTipeJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeJurnalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    tipeJurnalDao.updateAndSave(imTipeJurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeJurnalBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeJurnal, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipeJurnalBoImpl.saveDelete] Error, not found data TipeJurnal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeJurnal with request id, please check again your data ...");

            }
        }
        logger.info("[TipeJurnalBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipeJurnal bean) throws GeneralBOException {
        logger.info("[TipeJurnalBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

            ImTipeJurnalEntity imTipeJurnalEntity = null;
            try {
                // Get data from database by ID
                imTipeJurnalEntity = tipeJurnalDao.getById("tipeJurnalId", bean.getTipeJurnalId());
            } catch (HibernateException e) {
                logger.error("[TipeJurnalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data TipeJurnal by Kode TipeJurnal, please inform to your admin...," + e.getMessage());
            }
            if (imTipeJurnalEntity != null) {
                imTipeJurnalEntity.setTipeJurnalName(bean.getTipeJurnalName());
                imTipeJurnalEntity.setFlag(bean.getFlag());
                imTipeJurnalEntity.setAction(bean.getAction());
                imTipeJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeJurnalEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    tipeJurnalDao.updateAndSave(imTipeJurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeJurnalBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipeJurnal, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipeJurnalBoImpl.saveEdit] Error, not found data TipeJurnal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipeJurnal with request id, please check again your data ...");
            }
        }
        logger.info("[TipeJurnalBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeJurnal saveAdd(TipeJurnal bean) throws GeneralBOException {
        logger.info("[TipeJurnalBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            ImTipeJurnalEntity jurnalEntity = null;
            try {
                jurnalEntity = tipeJurnalDao.getById("tipeJurnalId",bean.getTipeJurnalId());
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
            }
            if (jurnalEntity!=null){
                String status ="ERROR : tipe jurnal ID sudah ada";
                logger.error("[SettingReportUserBoImpl.saveEdit] "+status);
                throw new GeneralBOException(status);
            }

            // creating object entity serializable
            ImTipeJurnalEntity imTipeJurnalEntity = new ImTipeJurnalEntity();

            imTipeJurnalEntity.setTipeJurnalId(bean.getTipeJurnalId());
            imTipeJurnalEntity.setTipeJurnalName(bean.getTipeJurnalName());
            imTipeJurnalEntity.setFlag(bean.getFlag());
            imTipeJurnalEntity.setAction(bean.getAction());
            imTipeJurnalEntity.setCreatedWho(bean.getCreatedWho());
            imTipeJurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTipeJurnalEntity.setCreatedDate(bean.getCreatedDate());
            imTipeJurnalEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tipeJurnalDao.addAndSave(imTipeJurnalEntity);
            } catch (HibernateException e) {
                logger.error("[TipeJurnalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data TipeJurnal, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipeJurnalBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeJurnal> getByCriteria(TipeJurnal searchBean) throws GeneralBOException {
        logger.info("[TipeJurnalBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeJurnal> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeJurnalId() != null && !"".equalsIgnoreCase(searchBean.getTipeJurnalId())) {
                hsCriteria.put("tipe_jurnal_id", searchBean.getTipeJurnalId());
            }
            if (searchBean.getTipeJurnalName() != null && !"".equalsIgnoreCase(searchBean.getTipeJurnalName())) {
                hsCriteria.put("tipe_jurnal_name", searchBean.getTipeJurnalName());
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

            List<ImTipeJurnalEntity> imTipeJurnalEntity = null;
            try {

                imTipeJurnalEntity = tipeJurnalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipeJurnalBoImpl.getSearchTipeJurnalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTipeJurnalEntity != null){
                TipeJurnal returnTipeJurnal;
                // Looping from dao to object and save in collection
                for(ImTipeJurnalEntity tipeJurnalEntity : imTipeJurnalEntity){
                    returnTipeJurnal = new TipeJurnal();
                    returnTipeJurnal.setTipeJurnalId(tipeJurnalEntity.getTipeJurnalId());
                    returnTipeJurnal.setTipeJurnalName(tipeJurnalEntity.getTipeJurnalName());;

                    returnTipeJurnal.setCreatedWho(tipeJurnalEntity.getCreatedWho());
                    returnTipeJurnal.setCreatedDate(tipeJurnalEntity.getCreatedDate());
                    returnTipeJurnal.setLastUpdate(tipeJurnalEntity.getLastUpdate());
                    returnTipeJurnal.setAction(tipeJurnalEntity.getAction());
                    returnTipeJurnal.setFlag(tipeJurnalEntity.getFlag());
                    listOfResult.add(returnTipeJurnal);
                }
            }
        }
        logger.info("[TipeJurnalBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeJurnal> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    @Override
    public List<KodeRekening> getMappingJurnalInKodeRekening(String id) throws GeneralBOException {
        logger.info("[TipeJurnalBoImpl.getMappingJurnalInKodeRekening] start process >>>");
        List<ImMappingJurnalEntity> mappingJurnalEntityList = new ArrayList<>();
        try {
            // Get data from database by ID
            mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTipeJurnalId(id);
        } catch (HibernateException e) {
            logger.error("[TipeJurnalBoImpl.getMappingJurnalInKodeRekening] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data TipeJurnal by Kode TipeJurnal, please inform to your admin...," + e.getMessage());
        }

        List<KodeRekening> kodeRekeningList = new ArrayList<>();

        for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList){
            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setKodeRekening(mappingJurnalEntity.getKodeRekening());
            kodeRekening.setPosisi(mappingJurnalEntity.getPosisi());
            if (mappingJurnalEntity.getPosisi().equalsIgnoreCase("D")){
                kodeRekening.setPosisiName("Debet");
            }else{
                kodeRekening.setPosisiName("Kredit");
            }
            List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(mappingJurnalEntity.getKodeRekening());
            for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList){
                kodeRekening.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
            }
            kodeRekeningList.add(kodeRekening);
        }

        logger.info("[TipeJurnalBoImpl.getMappingJurnalInKodeRekening] end process <<<");
        return kodeRekeningList;
    }

    @Override
    public String getTipeJurnalByTransId ( String transId){
        logger.info("[TipeJurnalBoImpl.getTipeJurnalByTransId] start process >>>");
        String tipeJurnal=null;

        List<ImMappingJurnalEntity> mappingJurnalEntityList = new ArrayList<>();
        try {
            // Get data from database by ID
            mappingJurnalEntityList = mappingJurnalDao.getMappingByTransId(transId);
        } catch (HibernateException e) {
            logger.error("[TipeJurnalBoImpl.getTipeJurnalByTransId] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data TipeJurnal by Kode TipeJurnal, please inform to your admin...," + e.getMessage());
        }
        for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList){
            tipeJurnal=mappingJurnalEntity.getTipeJurnalId();
        }
        return tipeJurnal;
    }

    @Override
    public TipeJurnal getTipeJurnalById ( String tipeJurnalId){
        logger.info("[TipeJurnalBoImpl.getTipeJurnalById] start process >>>");
        TipeJurnal tipeJurnal=new TipeJurnal();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag","Y");
        hsCriteria.put("tipe_jurnal_id",tipeJurnalId);
        List<ImTipeJurnalEntity> tipeJurnalEntityList ;
        try {
            // Get data from database by ID
            tipeJurnalEntityList = tipeJurnalDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TipeJurnalBoImpl.getTipeJurnalById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data TipeJurnal by Kode TipeJurnal, please inform to your admin...," + e.getMessage());
        }
        for (ImTipeJurnalEntity tipeJurnalEntity: tipeJurnalEntityList){
            tipeJurnal.setTipeJurnalId(tipeJurnalEntity.getTipeJurnalId());
            tipeJurnal.setTipeJurnalName(tipeJurnalEntity.getTipeJurnalName());
        }
        return tipeJurnal;
    }

}
