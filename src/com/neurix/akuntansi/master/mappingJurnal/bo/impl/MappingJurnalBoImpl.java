package com.neurix.akuntansi.master.mappingJurnal.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.tipeJurnal.dao.TipeJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

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
    private KodeRekeningDao kodeRekeningDao;


    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setTipeJurnalDao(TipeJurnalDao tipeJurnalDao) {
        this.tipeJurnalDao = tipeJurnalDao;
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

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    @Override
    public void saveDelete(MappingJurnal bean) throws GeneralBOException {
        logger.info("[MappingJurnalBoImpl.saveDelete] start process >>>");
        if (bean!=null) {
            ImMappingJurnalEntity imMappingJurnalEntity;
            try {
                // Get data from database by ID
                String mappinfgJurnalId = bean.getMappingJurnalId();
                imMappingJurnalEntity = mappingJurnalDao.getById("mappingJurnalId", mappinfgJurnalId);
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
    public MappingJurnal saveMappingJurnalTransaction(MappingJurnal header, List<MappingJurnal> detail) throws GeneralBOException {
        String transId = transDao.getNextTransId();
        MappingJurnal result = null;

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        //simpan transaksi
        ImTransEntity imTransEntity = new ImTransEntity();
        imTransEntity.setTransId(transId);
        imTransEntity.setTransName(header.getTransName());
        imTransEntity.setMaster(header.getMaster());
        imTransEntity.setFlagSumberBaru(header.getIsSumberBaru() == null ? "N" : "Y");
        imTransEntity.setIsOtomatis(header.getIsOtomatis() == null ? "N" : "Y");
        imTransEntity.setFlagPengajuanBiaya(header.getIsPengajuanBiaya() == null ? "N" : "Y");

        imTransEntity.setTipeJurnalId(header.getTipeJurnalId());
        imTransEntity.setFlag("Y");
        imTransEntity.setAction("C");
        imTransEntity.setCreatedWho(userLogin);
        imTransEntity.setLastUpdateWho(userLogin);
        imTransEntity.setCreatedDate(updateTime);
        imTransEntity.setLastUpdate(updateTime);
        try {
            // insert into database
            transDao.addAndSave(imTransEntity);
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.saveMappingJurnalTransaction] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when save data transaksi , please inform to your admin...," + e.getMessage());
        }

        if (detail.size() > 0) {
            for (MappingJurnal mappingJurnal : detail) {
                // creating object entity serializable
                mappingJurnal.setTransId(transId);
                try {
                    saveAdd(mappingJurnal);
                } catch (HibernateException e) {
                    logger.error("[MappingJurnalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data MappingJurnal, please info to your admin..." + e.getMessage());
                }
                result = mappingJurnal;
            }
        }

        return result;
    }

    @Override
    public MappingJurnal editMappingJurnalTransaction(MappingJurnal header, List<MappingJurnal> detail) throws GeneralBOException {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String transId = header.getTransId();
        ImTransEntity transEntity = null;
        try {
            transEntity = transDao.getById("transId",transId);
            if(transEntity == null){
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when save data transaksi , please inform to your admin...," + e.getMessage());
        }
        transEntity.setTransName(header.getTransName());
        transEntity.setMaster(header.getMaster());
        transEntity.setFlagSumberBaru(header.getIsSumberBaru() == null ? "N" : "Y");
        transEntity.setIsOtomatis(header.getIsOtomatis() == null ? "N" : "Y");
        transEntity.setFlagPengajuanBiaya(header.getIsPengajuanBiaya() == null ? "N" : "Y");

        transEntity.setTipeJurnalId(header.getTipeJurnalId());
        transEntity.setFlag("Y");
        transEntity.setAction("U");
        transEntity.setCreatedWho(userLogin);
        transEntity.setLastUpdateWho(userLogin);
        transEntity.setCreatedDate(updateTime);
        transEntity.setLastUpdate(updateTime);
        try {
            // insert into database
            transDao.addAndSave(transEntity);
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when save data transaksi , please inform to your admin...," + e.getMessage());
        }
        if (detail.size() > 0) {
            for (MappingJurnal mappingJurnal : detail) {
                // creating object entity serializable
                mappingJurnal.setTransId(transId);
                try {
                    if(mappingJurnal.getAction().equalsIgnoreCase("C")){
                        saveAdd(mappingJurnal);
                    }
                    else if(mappingJurnal.getAction().equalsIgnoreCase("U")){
                        saveEdit(mappingJurnal);
                    }
                    else {
                        saveDelete(mappingJurnal);
                    }
                } catch (HibernateException e) {
                    logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data MappingJurnal, please info to your admin..." + e.getMessage());
                }
            }
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("param_id",transId);
        }

        return header;
    }

    @Override
    public Boolean saveDeleteMappingTransaction(MappingJurnal header, List<MappingJurnal> detail) throws GeneralBOException {
        Boolean result = false;

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String transId = header.getTransId();
        ImTransEntity transEntity = null;
        try {
            transEntity = transDao.getById("transId",transId);
            if(transEntity == null){ throw new Exception(); }
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when save data transaksi , please inform to your admin...," + e.getMessage());
        }
        transEntity.setFlag("N");
        transEntity.setAction("D");
        transEntity.setLastUpdateWho(userLogin);
        transEntity.setLastUpdate(updateTime);
        try {
            // insert into database
            transDao.updateAndSave(transEntity);
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when save data transaksi , please inform to your admin...," + e.getMessage());
        }
        if (detail.size() > 0) {
            for (MappingJurnal mappingJurnal : detail) {
                // creating object entity serializable
                mappingJurnal.setTransId(transId);
                mappingJurnal.setAction("D");
                mappingJurnal.setFlag("N");
                mappingJurnal.setLastUpdateWho(userLogin);
                mappingJurnal.setLastUpdate(updateTime);
                try {
                        saveDelete(mappingJurnal);
                } catch (HibernateException e) {
                    logger.error("[MappingJurnalBoImpl.editMappingJurnalTransaction] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data MappingJurnal, please info to your admin..." + e.getMessage());
                }
            }
            result = true;
        }

        return result;
    }

    @Override
    public Map<String, MappingJurnal> getListMappingJurnal(String transId) {
        logger.info("[MappingJurnalBoImpl.getListMappingJurnal] start process >>>");
        Map<String, MappingJurnal> resultList = new HashMap<>();
        List<ImMappingJurnalEntity> mappingJurnalList;
        try {
            mappingJurnalList = mappingJurnalDao.getMappingByTransId(transId);
        } catch (Exception e) {
            logger.error("[MappingJurnalBoImpl.getListMappingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data MappingJurnal by transaksiId , please inform to your admin...," + e.getMessage());
        }
        if (mappingJurnalList.size() > 0) {
            for (ImMappingJurnalEntity imMappingJurnalEntity : mappingJurnalList) {
                MappingJurnal mappingJurnal = new MappingJurnal();
                mappingJurnal.setBukti(imMappingJurnalEntity.getBukti());
                mappingJurnal.setDivisiId(imMappingJurnalEntity.getDivisiId());
                mappingJurnal.setEditBiaya(imMappingJurnalEntity.getEditBiaya());
                mappingJurnal.setKeterangan(imMappingJurnalEntity.getKeterangan());
                mappingJurnal.setKirimList(imMappingJurnalEntity.getKirimList());
                mappingJurnal.setKodeBarang(imMappingJurnalEntity.getKodeBarang());
                mappingJurnal.setKodeRekening(imMappingJurnalEntity.getKodeRekening());
                mappingJurnal.setTransId(imMappingJurnalEntity.getTransId());
                mappingJurnal.setMappingJurnalId(imMappingJurnalEntity.getMappingJurnalId());
                mappingJurnal.setMasterId(imMappingJurnalEntity.getMasterId());
                mappingJurnal.setPosisi(imMappingJurnalEntity.getPosisi());
                mappingJurnal.setTipeJurnalId(imMappingJurnalEntity.getTipeJurnalId());
                resultList.put(mappingJurnal.getKodeRekening(), mappingJurnal);
            }
        }
        logger.info("[MappingJurnalBoImpl.getListMappingJurnal] end process <<<");
        return resultList;
    }

    @Override
    public MappingJurnal getMappingJurnalById(String mappingJurnalId) {
        MappingJurnal mappingJurnalReturn = null;
        try {
            ImMappingJurnalEntity imMappingJurnalEntity = mappingJurnalDao.getById("mappingJurnalId",mappingJurnalId);
            mappingJurnalReturn = new MappingJurnal();
            mappingJurnalReturn.setBukti(imMappingJurnalEntity.getBukti());
            mappingJurnalReturn.setDivisiId(imMappingJurnalEntity.getDivisiId());
            mappingJurnalReturn.setEditBiaya(imMappingJurnalEntity.getEditBiaya());
            mappingJurnalReturn.setKeterangan(imMappingJurnalEntity.getKeterangan());
            mappingJurnalReturn.setKirimList(imMappingJurnalEntity.getKirimList());
            mappingJurnalReturn.setKodeBarang(imMappingJurnalEntity.getKodeBarang());
            mappingJurnalReturn.setKodeRekening(imMappingJurnalEntity.getKodeRekening());
            mappingJurnalReturn.setTransId(imMappingJurnalEntity.getTransId());
            mappingJurnalReturn.setMappingJurnalId(imMappingJurnalEntity.getMappingJurnalId());
            mappingJurnalReturn.setMasterId(imMappingJurnalEntity.getMasterId());
            mappingJurnalReturn.setPosisi(imMappingJurnalEntity.getPosisi());
            mappingJurnalReturn.setTipeJurnalId(imMappingJurnalEntity.getTipeJurnalId());

            mappingJurnalReturn.setAction(imMappingJurnalEntity.getAction());
            mappingJurnalReturn.setFlag(imMappingJurnalEntity.getFlag());
            mappingJurnalReturn.setCreatedDate(imMappingJurnalEntity.getCreatedDate());
            mappingJurnalReturn.setCreatedWho(imMappingJurnalEntity.getCreatedWho());
            mappingJurnalReturn.setLastUpdate(imMappingJurnalEntity.getLastUpdate());
            mappingJurnalReturn.setLastUpdateWho(imMappingJurnalEntity.getLastUpdateWho());

        }catch (Exception e){

        }
        return mappingJurnalReturn;
    }

    @Override
    public void saveEdit(MappingJurnal bean) throws GeneralBOException {
        logger.info("[MappingJurnalBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImMappingJurnalEntity imMappingJurnalEntity = null;
            try {
                // Get data from database by ID
                String mappingJurnalId = bean.getMappingJurnalId();
                imMappingJurnalEntity = mappingJurnalDao.getById("mappingJurnalId", mappingJurnalId);
            } catch (HibernateException e) {
                logger.error("[MappingJurnalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data MappingJurnal by Kode MappingJurnal, please inform to your admin...," + e.getMessage());
            }
            if (imMappingJurnalEntity != null) {
                imMappingJurnalEntity.setKodeRekening(bean.getKodeRekening());
                imMappingJurnalEntity.setPosisi(bean.getPosisi());
                imMappingJurnalEntity.setMasterId(bean.getMasterId());
                imMappingJurnalEntity.setBukti(bean.getBukti());
                imMappingJurnalEntity.setKodeBarang(bean.getKodeBarang());
                imMappingJurnalEntity.setKirimList(bean.getKirimList());
                imMappingJurnalEntity.setKeterangan(bean.getKeterangan());
                imMappingJurnalEntity.setDivisiId(bean.getDivisiId());
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
            imMappingJurnalEntity.setTipeJurnalId(bean.getTipeJurnalId());
            imMappingJurnalEntity.setTransId(bean.getTransId());
            imMappingJurnalEntity.setKodeRekening(bean.getKodeRekening());
            imMappingJurnalEntity.setPosisi(bean.getPosisi());
            imMappingJurnalEntity.setMasterId(bean.getMasterId());
            imMappingJurnalEntity.setBukti(bean.getBukti());
            imMappingJurnalEntity.setDivisiId(bean.getDivisiId());
            imMappingJurnalEntity.setKodeBarang(bean.getKodeBarang());
            imMappingJurnalEntity.setKirimList(bean.getKirimList());
            imMappingJurnalEntity.setKeterangan(bean.getKeterangan());
            imMappingJurnalEntity.setEditBiaya(bean.getEditBiaya());

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

            if (searchBean.getKodeRekening() != null && !"".equalsIgnoreCase(searchBean.getKodeRekening())) {
                hsCriteria.put("kode_rekening", searchBean.getKodeRekening());
            }
            if (searchBean.getTipeJurnalId() != null && !"".equalsIgnoreCase(searchBean.getTipeJurnalId())) {
                hsCriteria.put("tipe_jurnal_id", searchBean.getTipeJurnalId());
            }
            if (searchBean.getTransId() != null && !"".equalsIgnoreCase(searchBean.getTransId())) {
                hsCriteria.put("trans_id", searchBean.getTransId());
            }
            if (searchBean.getPosisi() != null && !"".equalsIgnoreCase(searchBean.getPosisi())) {
                hsCriteria.put("posisi", searchBean.getTransId());
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
                String transId="";
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
                    returnMappingJurnal.setKirimList(mappingJurnalEntity.getKirimList());
                    returnMappingJurnal.setKodeRekening(mappingJurnalEntity.getKodeRekening());
                    returnMappingJurnal.setKodeRekeningBintang(CommonUtil.formatKodeRekeningBintang(mappingJurnalEntity.getKodeRekening()));

                    if (mappingJurnalEntity.getKodeRekening()!=null){
                        List<ImKodeRekeningEntity> kodeRekeningList = new ArrayList<>();
                        try {
                            kodeRekeningList = kodeRekeningDao.getIdByCoa(mappingJurnalEntity.getKodeRekening());
                        } catch (HibernateException e) {
                            logger.error("[MappingJurnalBoImpl.getSearchMappingJurnalByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for (ImKodeRekeningEntity kodeRekeningEntity:kodeRekeningList){
                            returnMappingJurnal.setKodeRekeningName(kodeRekeningEntity.getNamaKodeRekening());
                        }
                    }
                    returnMappingJurnal.setMasterId(mappingJurnalEntity.getMasterId());
                    returnMappingJurnal.setTransId(mappingJurnalEntity.getTransId());
                    returnMappingJurnal.setKodeBarang(mappingJurnalEntity.getKodeBarang());
                    returnMappingJurnal.setPosisi(mappingJurnalEntity.getPosisi());
                    returnMappingJurnal.setBukti(mappingJurnalEntity.getBukti());
                    returnMappingJurnal.setDivisiId(mappingJurnalEntity.getDivisiId());
                    returnMappingJurnal.setCreatedWho(mappingJurnalEntity.getCreatedWho());
                    returnMappingJurnal.setCreatedDate(mappingJurnalEntity.getCreatedDate());
                    returnMappingJurnal.setLastUpdate(mappingJurnalEntity.getLastUpdate());
                    returnMappingJurnal.setLastUpdateWho(mappingJurnalEntity.getLastUpdateWho());
                    returnMappingJurnal.setAction(mappingJurnalEntity.getAction());
                    returnMappingJurnal.setFlag(mappingJurnalEntity.getFlag());

                    if (!transId.equalsIgnoreCase(mappingJurnalEntity.getTransId())){
                        transId=mappingJurnalEntity.getTransId();
                        returnMappingJurnal.setTransBaru(true);
                    }else{
                        returnMappingJurnal.setTipeJurnalName("");
                        returnMappingJurnal.setTransName("");
                    }

                    if (mappingJurnalEntity.getEditBiaya() != null)
                        returnMappingJurnal.setEditBiaya(mappingJurnalEntity.getEditBiaya());
                    else
                        returnMappingJurnal.setEditBiaya("-");

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
