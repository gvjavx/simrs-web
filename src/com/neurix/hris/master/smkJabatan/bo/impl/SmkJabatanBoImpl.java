package com.neurix.hris.master.smkJabatan.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkJabatan.bo.SmkJabatanBo;
import com.neurix.hris.master.smkJabatan.dao.SmkJabatanDao;
import com.neurix.hris.master.smkJabatan.dao.SmkJabatanDetailDao;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanDetailEntity;
import com.neurix.hris.master.smkJabatan.model.SmkJabatan;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanEntity;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.master.smkKategoriAspek.dao.SmkKategoriAspekDao;
import com.neurix.hris.master.smkKategoriAspek.model.ImSmkKategoriAspekEntity;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;
import com.neurix.hris.master.smkKategoriAspekBobot.dao.SmkKategoriAspekBobotDao;
import com.neurix.hris.master.smkKategoriAspekBobot.model.ImSmkKategoriAspekBobotEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class SmkJabatanBoImpl implements SmkJabatanBo {

    protected static transient Logger logger = Logger.getLogger(SmkJabatanBoImpl.class);
    private SmkJabatanDao smkJabatanDao;
    private SmkJabatanDetailDao smkJabatanDetailDao;
    private SmkKategoriAspekBobotDao smkKategoriAspekBobotDao;
    private SmkKategoriAspekDao smkKategoriAspekDao;
    private SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao;
    private PositionDao positionDao;
    private StrukturJabatanDao strukturJabatanDao;

    public SmkKategoriAspekBobotDao getSmkKategoriAspekBobotDao() {
        return smkKategoriAspekBobotDao;
    }

    public void setSmkKategoriAspekBobotDao(SmkKategoriAspekBobotDao smkKategoriAspekBobotDao) {
        this.smkKategoriAspekBobotDao = smkKategoriAspekBobotDao;
    }

    public SmkKategoriAspekDao getSmkKategoriAspekDao() {
        return smkKategoriAspekDao;
    }

    public void setSmkKategoriAspekDao(SmkKategoriAspekDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public SmkIndikatorPenilaianAspekDao getSmkIndikatorPenilaianAspekDao() {
        return smkIndikatorPenilaianAspekDao;
    }

    public void setSmkIndikatorPenilaianAspekDao(SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao) {
        this.smkIndikatorPenilaianAspekDao = smkIndikatorPenilaianAspekDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public SmkJabatanDetailDao getSmkJabatanDetailDao() {
        return smkJabatanDetailDao;
    }

    public void setSmkJabatanDetailDao(SmkJabatanDetailDao smkJabatanDetailDao) {
        this.smkJabatanDetailDao = smkJabatanDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkJabatanBoImpl.logger = logger;
    }

    public SmkJabatanDao getSmkJabatanDao() {
        return smkJabatanDao;
    }

    public void setSmkJabatanDao(SmkJabatanDao smkJabatanDao) {
        this.smkJabatanDao = smkJabatanDao;
    }

    @Override
    public void saveDelete(SmkJabatan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            String smkJabatanId = bean.getJabatanSmkId();
            ImtSmkJabatanEntity imtSmkJabatanEntity = null;

            try {
                // Get data from database by ID
                imtSmkJabatanEntity = smkJabatanDao.getById("jabatanSmkId", smkJabatanId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imtSmkJabatanEntity != null) {
                imtSmkJabatanEntity.setFlag(bean.getFlag());
                imtSmkJabatanEntity.setAction(bean.getAction());
                imtSmkJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imtSmkJabatanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkJabatanDao.updateAndSave(imtSmkJabatanEntity);
                    smkJabatanDetailDao.deleteIndikator(smkJabatanId);
                } catch (HibernateException e) {
                    logger.error("[SmkKategoriBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkKategoriAspek, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[SmkKategoriBoImpl.saveDelete] Error, not found data SmkKategoriAspek with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkKategoriAspek with request id, please check again your data ...");
            }
        }
        logger.info("[SmkKategoriBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkJabatan bean) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String smkJabatanId = bean.getJabatanSmkId();
            ImtSmkJabatanEntity imtSmkJabatanEntity = null;
            List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;
            try {
                // Get data from database by ID
                imtSmkJabatanEntity = smkJabatanDao.getById("jabatanSmkId", smkJabatanId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkJabatan by Kode SmkJabatan, please inform to your admin...," + e.getMessage());
            }

            if (imtSmkJabatanEntity != null) {
                imtSmkJabatanEntity.setJabatanSmkId(bean.getJabatanSmkId());
                imtSmkJabatanEntity.setTipeAspekId(bean.getTipeAspekId());
                imtSmkJabatanEntity.setPositionId(bean.getPositionId());
                imtSmkJabatanEntity.setDivisiId(bean.getDivisiId());
                imtSmkJabatanEntity.setBranchId(bean.getBranchId());
                imtSmkJabatanEntity.setBobot(bean.getBobot());

                imtSmkJabatanEntity.setFlag(bean.getFlag());
                imtSmkJabatanEntity.setAction(bean.getAction());
                imtSmkJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imtSmkJabatanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    smkJabatanDao.updateAndSave(imtSmkJabatanEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkJabatan, please info to your admin..." + e.getMessage());
                }

                String flag;

                HttpSession session = ServletActionContext.getRequest().getSession();
                List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList<>();

                if(imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("A")){
                    smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
                }else if(imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("C")){
                    smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanBC");
                }else{
                    smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanBC");
                }

                imtSmkJabatanDetailEntities = smkJabatanDetailDao.getListData(bean.getJabatanSmkId());

                if(imtSmkJabatanDetailEntities != null){
                    for (ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntityDatabase: imtSmkJabatanDetailEntities) {
                        String smkJabatanDetailId = imtSmkJabatanDetailEntityDatabase.getJabatanSmkDetailId();
                        ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity = null;
                        try {
                            imtSmkJabatanDetailEntity = smkJabatanDetailDao.getById("jabatanSmkDetailId", smkJabatanDetailId);
                        } catch (HibernateException e) {
                            logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data SmkIndikator by Kode SmkIndikator, please inform to your admin...," + e.getMessage());
                        }
                        if (imtSmkJabatanDetailEntity != null) {
                            imtSmkJabatanDetailEntity.setFlag("N");
                            imtSmkJabatanDetailEntity.setAction("D");
                        }
                        imtSmkJabatanDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imtSmkJabatanDetailEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            smkJabatanDetailDao.updateAndSave(imtSmkJabatanDetailEntity);
                        } catch (HibernateException e) {
                            logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data SmkIndikator, please info to your admin..." + e.getMessage());
                        }
                    }

                    if(smkJabatanDetailList != null){
                        for (SmkJabatanDetail smkJabatanDetail: smkJabatanDetailList) {
                            String smkJabatanDetailId = smkJabatanDetailDao.getNextSmkJabatanDetailId();
                            ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity = new ImtSmkJabatanDetailEntity();

                            imtSmkJabatanDetailEntity.setJabatanSmkDetailId(smkJabatanDetailId);
                            imtSmkJabatanDetailEntity.setJabatanSmkId(bean.getJabatanSmkId());
                            imtSmkJabatanDetailEntity.setIndikatorKinerja(smkJabatanDetail.getIndikatorKinerja());
                            imtSmkJabatanDetailEntity.setBobot(smkJabatanDetail.getBobot());
                            imtSmkJabatanDetailEntity.setSatuan(smkJabatanDetail.getSatuan());

                            imtSmkJabatanDetailEntity.setFlag(bean.getFlag());
                            imtSmkJabatanDetailEntity.setAction(bean.getAction());
                            imtSmkJabatanDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                            imtSmkJabatanDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            imtSmkJabatanDetailEntity.setCreatedDate(bean.getLastUpdate());
                            imtSmkJabatanDetailEntity.setLastUpdate(bean.getLastUpdate());
                            smkJabatanDetailDao.addAndSave(imtSmkJabatanDetailEntity);
                            /*if(smkJabatanDetail.getJabatanSmkDetailId() == null){
                            }*/
                        }
                    }
                        /*if(smkJabatanDetailList != null){
                            flag = "N";
                            String indikatorKinerja = "";
                            BigDecimal bobot = BigDecimal.valueOf(0);
                            for (SmkJabatanDetail smkJabatanDetailSession: smkJabatanDetailList) {
                                if(imtSmkJabatanDetailEntityDatabase.getIndikatorKinerja().equalsIgnoreCase(smkJabatanDetailSession.getIndikatorKinerja())
                                        && imtSmkJabatanDetailEntityDatabase.getBobot() == smkJabatanDetailSession.getBobot()){
                                    flag = "Y";
                                }else{
                                    indikatorKinerja = smkJabatanDetailSession.getIndikatorKinerja();
                                    bobot = smkJabatanDetailSession.getBobot();
                                }
                            }
                            if(flag == "N"){
                                String smkJabatanDetailId = imtSmkJabatanDetailEntityDatabase.getJabatanSmkDetailId();
                                ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity = null;
                                try {
                                    imtSmkJabatanDetailEntity = smkJabatanDetailDao.getById("jabatanSmkDetailId", smkJabatanDetailId);
                                } catch (HibernateException e) {
                                    logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data SmkIndikator by Kode SmkIndikator, please inform to your admin...," + e.getMessage());
                                }
                                if (imtSmkJabatanDetailEntity != null) {
                                    imtSmkJabatanDetailEntity.setFlag("N");
                                    imtSmkJabatanDetailEntity.setAction("D");
                                    *//*if(imtSmkJabatanDetailEntity.getJabatanSmkDetailId() == null){
                                    }else{
                                        imtSmkJabatanDetailEntity.setIndikatorKinerja(indikatorKinerja);
                                        imtSmkJabatanDetailEntity.setBobot(bobot);
                                    }*//*
                                    imtSmkJabatanDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    imtSmkJabatanDetailEntity.setLastUpdate(bean.getLastUpdate());
                                    try {
                                        smkJabatanDetailDao.updateAndSave(imtSmkJabatanDetailEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving update data SmkIndikator, please info to your admin..." + e.getMessage());
                                    }
                                }
                            }
                        }*/
                    }
                }

            } else {
                logger.error("[SmkJabatanBoImpl.saveEdit] Error, not found data SmkJabatan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkJabatan with request id, please check again your data ...");
            }

        logger.info("[SmkJabatanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkJabatan saveAdd(SmkJabatan bean) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkJabatanId;
            try {
                // Generating ID, get from postgre sequence
                smkJabatanId = smkJabatanDao.getNextSmkJabatanId();
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkJabatanId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImtSmkJabatanEntity imtSmkJabatanEntity = new ImtSmkJabatanEntity();

            imtSmkJabatanEntity.setJabatanSmkId(smkJabatanId);
            imtSmkJabatanEntity.setTipeAspekId(bean.getTipeAspekId());
            imtSmkJabatanEntity.setPositionId(bean.getPositionId());
            imtSmkJabatanEntity.setDivisiId(bean.getDivisiId());
            imtSmkJabatanEntity.setBranchId(bean.getBranchId());
            imtSmkJabatanEntity.setBobot(bean.getBobot());

            imtSmkJabatanEntity.setFlag(bean.getFlag());
            imtSmkJabatanEntity.setAction(bean.getAction());
            imtSmkJabatanEntity.setCreatedWho(bean.getCreatedWho());
            imtSmkJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imtSmkJabatanEntity.setCreatedDate(bean.getCreatedDate());
            imtSmkJabatanEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkJabatanDao.addAndSave(imtSmkJabatanEntity);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkJabatan, please info to your admin..." + e.getMessage());
            }

            List<SmkJabatanDetail> smkJabatanDetailList = null;
            HttpSession session = ServletActionContext.getRequest().getSession();
            if(bean.getTipeAspekId().equalsIgnoreCase("A")){
                smkJabatanDetailList = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
                if(smkJabatanDetailList != null) {
                    for (SmkJabatanDetail smkJabatanDetail : smkJabatanDetailList) {
                        String SmkJabatanDetailId ;
                        try {
                            SmkJabatanDetailId = smkJabatanDetailDao.getNextSmkJabatanDetailId();
                        } catch (HibernateException e) {
                            logger.error("[SmkJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence smkJabatanId id, please info to your admin..." + e.getMessage());
                        }
                        ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity = new ImtSmkJabatanDetailEntity();

                        imtSmkJabatanDetailEntity.setJabatanSmkDetailId(SmkJabatanDetailId);
                        imtSmkJabatanDetailEntity.setJabatanSmkId(smkJabatanId);
                        imtSmkJabatanDetailEntity.setIndikatorKinerja(smkJabatanDetail.getIndikatorKinerja());
                        imtSmkJabatanDetailEntity.setBobot(smkJabatanDetail.getBobot());
                        imtSmkJabatanDetailEntity.setSatuan(smkJabatanDetail.getSatuan());
                        if(smkJabatanDetail.getSubAspekA() != null || !smkJabatanDetail.getSubAspekA().equals("")){
                            imtSmkJabatanDetailEntity.setSubAspekA(smkJabatanDetail.getSubAspekA());
                        }

                        imtSmkJabatanDetailEntity.setFlag(bean.getFlag());
                        imtSmkJabatanDetailEntity.setAction("C");
                        imtSmkJabatanDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                        imtSmkJabatanDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imtSmkJabatanDetailEntity.setCreatedDate(bean.getLastUpdate());
                        imtSmkJabatanDetailEntity.setLastUpdate(bean.getLastUpdate());
                        smkJabatanDetailDao.addAndSave(imtSmkJabatanDetailEntity);
                    }
                    session.removeAttribute("listSmkJabatanSubTipeA");
                }
            }

            if(!bean.getTipeAspekId().equalsIgnoreCase("A")){
                List<SmkJabatanDetail> smkJabatanDetailList1 = null;
                smkJabatanDetailList1 = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanBC");
                if(smkJabatanDetailList1 != null) {
                    for (SmkJabatanDetail smkJabatanDetail : smkJabatanDetailList1) {
                        String SmkJabatanDetailId ;
                        try {
                            SmkJabatanDetailId = smkJabatanDetailDao.getNextSmkJabatanDetailId();
                        } catch (HibernateException e) {
                            logger.error("[SmkJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when getting sequence smkJabatanId id, please info to your admin..." + e.getMessage());
                        }
                        ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity = new ImtSmkJabatanDetailEntity();

                        imtSmkJabatanDetailEntity.setJabatanSmkDetailId(SmkJabatanDetailId);
                        imtSmkJabatanDetailEntity.setJabatanSmkId(smkJabatanId);
                        imtSmkJabatanDetailEntity.setIndikatorKinerja(smkJabatanDetail.getIndikatorKinerja());
                        imtSmkJabatanDetailEntity.setBobot(smkJabatanDetail.getBobot());

                        imtSmkJabatanDetailEntity.setFlag(bean.getFlag());
                        imtSmkJabatanDetailEntity.setAction("C");
                        imtSmkJabatanDetailEntity.setCreatedWho(bean.getLastUpdateWho());
                        imtSmkJabatanDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imtSmkJabatanDetailEntity.setCreatedDate(bean.getLastUpdate());
                        imtSmkJabatanDetailEntity.setLastUpdate(bean.getLastUpdate());
                        smkJabatanDetailDao.addAndSave(imtSmkJabatanDetailEntity);
                    }
                    session.removeAttribute("listSmkJabatanBC");
                }
            }


        }

        logger.info("[SmkJabatanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkJabatanDetail> getListSmkJabatanDetail(SmkJabatanDetail bean) throws GeneralBOException {
        List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;
        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList<>();

        imtSmkJabatanDetailEntities = smkJabatanDetailDao.getListData(bean.getJabatanSmkId());
        if(imtSmkJabatanDetailEntities != null){
            for(ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity: imtSmkJabatanDetailEntities){
                SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();
                smkJabatanDetail.setJabatanSmkDetailId(imtSmkJabatanDetailEntity.getJabatanSmkDetailId());
                smkJabatanDetail.setJabatanSmkId(imtSmkJabatanDetailEntity.getJabatanSmkId());
                smkJabatanDetail.setIndikatorKinerja(imtSmkJabatanDetailEntity.getIndikatorKinerja());
                smkJabatanDetail.setBobot(imtSmkJabatanDetailEntity.getBobot());
                smkJabatanDetail.setSatuan(imtSmkJabatanDetailEntity.getSatuan());
                smkJabatanDetail.setSubAspekA(imtSmkJabatanDetailEntity.getSubAspekA());
                if(imtSmkJabatanDetailEntity.getSubAspekA() != null && !imtSmkJabatanDetailEntity.getSubAspekA().equalsIgnoreCase("")){
                    smkJabatanDetail.setSubAspekANama(imtSmkJabatanDetailEntity.getImSmkCheckListEntity().getCheckListName());
                }else{
                    smkJabatanDetail.setSubAspekANama("");
                }
                smkJabatanDetailList.add(smkJabatanDetail);
            }
        }

        return smkJabatanDetailList;
    }

    @Override
    public List<SmkJabatan> getByCriteria(SmkJabatan searchBean) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkJabatan> listOfResult = new ArrayList();

        if (searchBean != null) {
            List<ImtSmkJabatanEntity> imtSmkJabatanEntity = null;
            try {

                imtSmkJabatanEntity = smkJabatanDao.getDataSearch(searchBean.getPositionId(), searchBean.getBranchId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imtSmkJabatanEntity != null){
                SmkJabatan returnSmkJabatan;
                // Looping from dao to object and save in collection
                for(ImtSmkJabatanEntity smkJabatanEntity : imtSmkJabatanEntity){
                    returnSmkJabatan = new SmkJabatan();
                    returnSmkJabatan.setJabatanSmkId(smkJabatanEntity.getJabatanSmkId());
                    returnSmkJabatan.setTipeAspekId(smkJabatanEntity.getTipeAspekId());
                    returnSmkJabatan.setPositionId(smkJabatanEntity.getPositionId());
                    returnSmkJabatan.setPositionName(smkJabatanEntity.getPositionName());
                    returnSmkJabatan.setDivisiId(smkJabatanEntity.getDivisiId());
                    returnSmkJabatan.setDivisiName(smkJabatanEntity.getDivisiName());
                    returnSmkJabatan.setBranchId(smkJabatanEntity.getBranchId());
                    returnSmkJabatan.setBranchName(smkJabatanEntity.getBranchName());
                    returnSmkJabatan.setBobot(smkJabatanEntity.getBobot());

                    listOfResult.add(returnSmkJabatan);
                }
            }
        }
        logger.info("[SmkJabatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkJabatan> getViewSmkJabatan(SmkJabatan bean) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.getByCriteria] start process >>>");

        List<SmkJabatan> listOfResult = new ArrayList();

        if (bean != null) {
            List<ImtSmkJabatanEntity> imtSmkJabatanEntity = null;
            try {

                imtSmkJabatanEntity = smkJabatanDao.getListView(bean.getPositionId(), bean.getDivisiId(), bean.getBranchId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            int jumlahBobot = 0 ;
            if(imtSmkJabatanEntity != null){
                SmkJabatan returnSmkJabatan;
                for(ImtSmkJabatanEntity smkJabatanEntity : imtSmkJabatanEntity){
                    returnSmkJabatan = new SmkJabatan();
                    jumlahBobot += smkJabatanEntity.getBobot();
                    returnSmkJabatan.setJabatanSmkId(smkJabatanEntity.getJabatanSmkId());
                    returnSmkJabatan.setTipeAspekId(smkJabatanEntity.getTipeAspekId());
                    returnSmkJabatan.setBobot(smkJabatanEntity.getBobot());
                    returnSmkJabatan.setJumlahBobot(jumlahBobot);
                    listOfResult.add(returnSmkJabatan);
                }
            }
        }
        logger.info("[SmkJabatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public SmkJabatan getEditSmkJabatan(String id) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        SmkJabatan smkJabatan = new SmkJabatan();
        ImtSmkJabatanEntity imtSmkJabatanEntity = null;
        List<ImtSmkJabatanDetailEntity>imtSmkJabatanDetailEntityList= null;
        try {
            // Get data from database by ID
            imtSmkJabatanEntity = smkJabatanDao.getById("jabatanSmkId", id);
        } catch (HibernateException e) {
            logger.error("[SmkJabatanBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data SmkJabatan by Kode SmkJabatan, please inform to your admin...," + e.getMessage());
        }
        if(imtSmkJabatanEntity != null){
            smkJabatan.setJabatanSmkId(imtSmkJabatanEntity.getJabatanSmkId());
            smkJabatan.setBranchId(imtSmkJabatanEntity.getBranchId());
            smkJabatan.setDivisiId(imtSmkJabatanEntity.getDivisiId());
            smkJabatan.setPositionId(imtSmkJabatanEntity.getPositionId());
            smkJabatan.setBobot(imtSmkJabatanEntity.getBobot());
            smkJabatan.setTipeAspekId(imtSmkJabatanEntity.getTipeAspekId());
        }

        return smkJabatan;
    }

    @Override
    public List<SmkJabatanDetail> getViewSmkJabatanDetail(SmkJabatanDetail bean) throws GeneralBOException {
        logger.info("[SmkJabatanBoImpl.getByCriteria] start process >>>");
        List<SmkJabatanDetail> listOfResult = new ArrayList();
        if (bean != null) {
            List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;
            try {
                imtSmkJabatanDetailEntities = smkJabatanDetailDao.getListData(bean.getJabatanSmkId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imtSmkJabatanDetailEntities != null){
                SmkJabatanDetail returnSmkJabatanDetail;
                for(ImtSmkJabatanDetailEntity smkJabatanDetailEntity : imtSmkJabatanDetailEntities){
                    returnSmkJabatanDetail = new SmkJabatanDetail();
                    returnSmkJabatanDetail.setJabatanSmkDetailId(smkJabatanDetailEntity.getJabatanSmkDetailId());
                    returnSmkJabatanDetail.setJabatanSmkId(smkJabatanDetailEntity.getJabatanSmkId());
                    returnSmkJabatanDetail.setIndikatorKinerja(smkJabatanDetailEntity.getIndikatorKinerja());
                    returnSmkJabatanDetail.setBobot(smkJabatanDetailEntity.getBobot());
                    returnSmkJabatanDetail.setSubAspekA(smkJabatanDetailEntity.getSubAspekA());
                    if(smkJabatanDetailEntity.getSubAspekA() != null){
                        if(!smkJabatanDetailEntity.getSubAspekA().equals("")){
                            returnSmkJabatanDetail.setSubAspekANama(smkJabatanDetailEntity.getImSmkCheckListEntity().getCheckListName());
                        }else{
                            returnSmkJabatanDetail.setSubAspekANama("");
                        }
                    }

                    listOfResult.add(returnSmkJabatanDetail);
                }
            }
        }
        logger.info("[SmkJabatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Position> cekJabatan(String branchId, String id) throws GeneralBOException {
        List<Position>positions = new ArrayList();
        List<ImStrukturJabatanEntity>strukturJabatanEntityList= new ArrayList();
        ImPosition posisi= null;
        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSmkJabatanSubTipeA");

        strukturJabatanEntityList = strukturJabatanDao.getParent(id, branchId);
        posisi = positionDao.getById("positionId", id);
        String kelompok = "";

        if(posisi != null){
            kelompok = posisi.getKelompokId();
        }

        if(strukturJabatanEntityList.size() > 0){
            String parentStrukturId = "";
            String kelompokId = "";
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatanEntityList){
                parentStrukturId = strukturJabatanEntity.getParentId();
                kelompokId = strukturJabatanEntity.getKelompokId();
            }
            if(kelompok.equalsIgnoreCase("KL01")){
                parentStrukturId = "-";
            }
            SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();

            int index = 1;
            for(StrukturJabatan strukturJabatan : getAtasanStruktur(parentStrukturId)){
                if(strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL01") || strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL02") ||
                        strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL03") || strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL04") ||
                        strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL05")){
                    smkJabatanDetail = new SmkJabatanDetail();
                    String label[] = strukturJabatan.getPositionName().split(" ");
                    smkJabatanDetail.setJabatanSmkDetailId("-");
                    if(strukturJabatan.getPositionKelompokId() != null && strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL01")){
                        smkJabatanDetail.setIndikatorKinerja("Unit Usaha");
                    }else{
                        smkJabatanDetail.setIndikatorKinerja(label[1]);
                    }
                    smkJabatanDetail.setBobot(BigDecimal.valueOf(5));
                    smkJabatanDetail.setSubAspekA("");
                    smkJabatanDetail.setSubAspekANama("");
                    smkJabatanDetail.setSatuan("%");

                    smkJabatanDetail.setJabatanSmkId(index + "");
                    index++;
                    smkJabatanDetailList.add(smkJabatanDetail);
                }
            }
            List<SmkJabatanDetail> sortSmkJabatan = new ArrayList<>();
            if(smkJabatanDetailList.size() > 0){
                for(int a = index ; a > 0 ; a--){
                    for(SmkJabatanDetail smkJabatanDetail1: smkJabatanDetailList){
                        if(smkJabatanDetail1.getJabatanSmkId().equalsIgnoreCase(a + "")){
                            sortSmkJabatan.add(smkJabatanDetail1);
                            break;
                        }
                    }
                }
            }

            session.setAttribute("listSmkJabatanSubTipeA", sortSmkJabatan);
        }
        return positions;
    }

    private List<StrukturJabatan>getAtasanStruktur(String parentId){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntity = null;
        List<StrukturJabatan> strukturJabatanList = new ArrayList();
        String kelompok = "";
        String kelompok2 = "Y";

        if(!parentId.equalsIgnoreCase("-")){
            while(!kelompok.equalsIgnoreCase("KL01") && kelompok2.equalsIgnoreCase("Y")){
                imStrukturJabatanEntity = null;
                imStrukturJabatanEntity = strukturJabatanDao.getTambahanAspekA(parentId);
                if(imStrukturJabatanEntity != null){
                    for(ImStrukturJabatanEntity imStrukturJabatanEntity1 : imStrukturJabatanEntity){
                        parentId = imStrukturJabatanEntity1.getParentId();
                        kelompok = imStrukturJabatanEntity1.getKelompokId() ;
                        if(parentId.equalsIgnoreCase("-")){
                            kelompok2 = "N";
                        }

                        StrukturJabatan strukturJabatan = new StrukturJabatan();
                        strukturJabatan.setParentId(imStrukturJabatanEntity1.getParentId());
                        strukturJabatan.setPositionId(imStrukturJabatanEntity1.getPositionId());
                        strukturJabatan.setPositionName(imStrukturJabatanEntity1.getPositionName());
                        strukturJabatan.setPositionKelompokId(imStrukturJabatanEntity1.getKelompokId());
                        strukturJabatan.setPositionKelompokName(imStrukturJabatanEntity1.getKelompokName());
                        strukturJabatanList.add(strukturJabatan);
                    }
                }

            }
        }

        return strukturJabatanList;
    }

    @Override
    public List<SmkJabatanDetail> getAspek(String branchId, String tipeAspek) throws GeneralBOException {
        List<SmkJabatanDetail> listOfResult = new ArrayList();
        if (!branchId.equals("")) {
            List<ImSmkKategoriAspekEntity> imSmkKategoriAspekEntityList = null;
            try {
                imSmkKategoriAspekEntityList = smkKategoriAspekDao.getDataKategori(tipeAspek, branchId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkKategoriAspekEntityList != null){
                SmkJabatanDetail smkKategoriAspek;
                // Looping from dao to object and save in collection
                for(ImSmkKategoriAspekEntity imSmkKategoriAspekEntity : imSmkKategoriAspekEntityList){
                    smkKategoriAspek = new SmkJabatanDetail();
                    smkKategoriAspek.setJabatanSmkDetailId(imSmkKategoriAspekEntity.getKategoriAspekId());
                    smkKategoriAspek.setIndikatorKinerja(imSmkKategoriAspekEntity.getKategoriName());
                    /*smkKategoriAspek.setTipeAspekId(imSmkKategoriAspekEntity.getTipeAspekId());
                    smkKategoriAspek.setBranchId(imSmkKategoriAspekEntity.getBranchId());*/
                    smkKategoriAspek.setBobot(BigDecimal.valueOf(2.5));

                    listOfResult.add(smkKategoriAspek);
                }
            }
        }

        return listOfResult;
    }

    @Override
    public SmkJabatan getNilaiAspek(String branchId, String positionId) throws GeneralBOException {
        SmkJabatan smkJabatan = new SmkJabatan();
        smkJabatan.setTotalAspekA(0);
        smkJabatan.setTotalAspekB(0);
        smkJabatan.setTotalAspekC(0);

        List<ImtSmkJabatanEntity> imtSmkJabatanEntityList = new ArrayList<>();

        imtSmkJabatanEntityList = smkJabatanDao.getDataAspek(positionId, branchId);

        if(imtSmkJabatanEntityList.size() > 0){
            for(ImtSmkJabatanEntity imtSmkJabatanEntity: imtSmkJabatanEntityList){
                smkJabatan.setPositionId(imtSmkJabatanEntity.getPositionId());
                if(imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("A")){
                    smkJabatan.setTotalAspekA(imtSmkJabatanEntity.getBobot());
                }else if(imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("B1") || imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("B2")){
                    smkJabatan.setTotalAspekB(imtSmkJabatanEntity.getBobot());
                }else if(imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("C")){
                    smkJabatan.setTotalAspekC(imtSmkJabatanEntity.getBobot());
                }
            }
        }

        return smkJabatan;
    }

    @Override
    public String cekSudahTerinput(String branchId, String positionId, String aspek) throws GeneralBOException {
        List<ImtSmkJabatanEntity> imtSmkJabatanEntity = null;
        int jumlah = 0;
        try {
            imtSmkJabatanEntity = smkJabatanDao.cekListView(branchId, positionId, aspek);
        } catch (HibernateException e) {
            logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        int jumlahBobot = 0 ;
        if(imtSmkJabatanEntity != null){
            SmkJabatan returnSmkJabatan;
            for(ImtSmkJabatanEntity smkJabatanEntity : imtSmkJabatanEntity){
                jumlah++;
            }
        }
        return jumlah + "";
    }

    @Override
    public List<SmkJabatan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

}
