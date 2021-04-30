package com.neurix.simrs.transaksi.periksalab.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.dao.HeaderPemeriksaanDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDetailDao;
import com.neurix.simrs.transaksi.periksalab.dao.UploadHasilPeriksaDao;
import com.neurix.simrs.transaksi.periksalab.model.*;
import com.neurix.simrs.transaksi.periksaradiologi.dao.PeriksaRadiologiDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PeriksaLabBoImpl implements PeriksaLabBo {
    private static transient Logger logger = Logger.getLogger(PeriksaLabBoImpl.class);
    private PeriksaLabDao periksaLabDao;
    private PeriksaLabDetailDao periksaLabDetailDao;
    private LabDetailDao labDetailDao;
    private LabDao labDao;
    private KategoriLabDao kategoriLabDao;
    private StatusPasienDao statusPasienDao;
    private PeriksaRadiologiDao periksaRadiologiDao;
    private UploadHasilPeriksaDao uploadHasilPeriksaDao;
    private HeaderPemeriksaanDao headerPemeriksaanDao;

    @Override
    public List<PeriksaLab> getSearchLab(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getSearchLab] START >>>>>>>>> ");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        try {
            periksaLabList = periksaLabDao.getSearchLab(bean);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.saveAdd] ERROR when search data periksa lab " + e.getMessage());
            throw new GeneralBOException("[PeriksaLabBoImpl.saveAdd] ERROR when search data periksa lab " + e.getMessage());
        }
        logger.info("[PeriksaLabBoImpl.getSearchLab] END <<<<<<<<< ");
        return periksaLabList;
    }

    @Override
    public List<PeriksaLab> getByCriteria(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getByCriteria] START >>>>>>>>> ");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        if (bean != null) {
            List<ItSimrsPeriksaLabEntity> periksaLabEntities = getListEntityPeriksaLab(bean);
            if (!periksaLabEntities.isEmpty() && periksaLabEntities.size() > 0) {
                for (ItSimrsPeriksaLabEntity periksaLabEntity : periksaLabEntities) {
                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                    periksaLab.setIdHeaderPemeriksaan(periksaLabEntity.getIdHeaderPemeriksaan());
                    periksaLab.setIdPemeriksaan(periksaLabEntity.getIdPemeriksaan());
                    periksaLab.setNamaPemeriksaan(periksaLabEntity.getNamaPemeriksaan());
                    periksaLab.setFlag(periksaLabEntity.getFlag());
                    periksaLab.setAction(periksaLabEntity.getAction());
                    periksaLab.setCreatedDate(periksaLabEntity.getCreatedDate());
                    periksaLab.setCreatedWho(periksaLabEntity.getCreatedWho());
                    periksaLab.setLastUpdate(periksaLabEntity.getLastUpdate());
                    periksaLab.setLastUpdateWho(periksaLabEntity.getLastUpdateWho());
                    periksaLabList.add(periksaLab);
                }
            }
        }
        logger.info("[PeriksaLabBoImpl.getByCriteria] END <<<<<<<<< ");
        return periksaLabList;
    }

    @Override
    public void saveAdd(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAdd] START >>>>>>>>> ");
        ItSimrsPeriksaLabEntity entity = new ItSimrsPeriksaLabEntity();
        String id = getNextPeriksaLabId();
        entity.setIdPeriksaLab("PRL" + id);
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            periksaLabDao.addAndSave(entity);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab ", e.getCause());
            throw new GeneralBOException("[PeriksaLabBoImpl.saveAdd] ERROR when saving data periksa lab " + e.getCause());
        }
        logger.info("[PeriksaLabBoImpl.saveAdd] END <<<<<<<<< ");
    }

    public void editOrderPemeriksaan(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.editOrderPemeriksaan] START >>>>>>>>> ");
        if (bean.getIdHeaderPemeriksaan() != null && bean.getListLab().size() > 0) {
            ItSimrsHeaderPemeriksaanEntity headerPemeriksaanEntity = headerPemeriksaanDao.getById("idHeaderPemeriksaan", bean.getIdHeaderPemeriksaan());
            if(headerPemeriksaanEntity != null){
                if(bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())){
                    headerPemeriksaanEntity.setIdKategoriLab(bean.getIdKategoriLab());
                }
                if(bean.getJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getJenisPeriksaPasien())){
                    headerPemeriksaanEntity.setJenisPasien(bean.getJenisPeriksaPasien());
                }
                headerPemeriksaanEntity.setAction(bean.getAction());
                headerPemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                headerPemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
                if(bean.getTarifLabLuar() != null){
                    headerPemeriksaanEntity.setTarifLabLuar(bean.getTarifLabLuar());
                }

                try {
                    headerPemeriksaanDao.updateAndSave(headerPemeriksaanEntity);
                }catch (HibernateException e){
                    logger.error("[PeriksaLabBoImpl.editOrderPemeriksaan] Error, "+e.getMessage());
                    throw new GeneralBOException("Errror"+e.getMessage());
                }

                List<ItSimrsPeriksaLabEntity> periksaLabEntityList = new ArrayList<>();
                HashMap hsCriteria = new HashMap();
                hsCriteria.put("id_header_pemeriksaan", headerPemeriksaanEntity.getIdHeaderPemeriksaan());
                try {
                    periksaLabEntityList = periksaLabDao.getByCriteria(hsCriteria);
                }catch (HibernateException e){
                    logger.error("[PeriksaLabBoImpl.editOrderPemeriksaan] Error, "+e.getMessage());
                    throw new GeneralBOException("Errror"+e.getMessage());
                }
                if(periksaLabEntityList.size() > 0){
                    for (ItSimrsPeriksaLabEntity periksaLabEntity: periksaLabEntityList){
                        List<ItSimrsPeriksaLabDetailEntity> labDetailEntityList = new ArrayList<>();
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_periksa_lab", periksaLabEntity.getIdPeriksaLab());

                        try {
                            labDetailEntityList = periksaLabDetailDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error("[PeriksaLabBoImpl.editOrderPemeriksaan] Error, "+e.getMessage());
                            throw new GeneralBOException("Errror"+e.getMessage());
                        }

                        if(labDetailEntityList.size() > 0){
                            for (ItSimrsPeriksaLabDetailEntity labDetailEntity: labDetailEntityList){
                                try {
                                    periksaLabDetailDao.deleteAndSave(labDetailEntity);
                                }catch (HibernateException e){
                                    logger.error("[PeriksaLabBoImpl.editOrderPemeriksaan] Error, "+e.getMessage());
                                    throw new GeneralBOException("Errror"+e.getMessage());
                                }
                            }
                        }
                        try {
                            periksaLabDao.deleteAndSave(periksaLabEntity);
                        }catch (HibernateException e){
                            logger.error("[PeriksaLabBoImpl.editOrderPemeriksaan] Error, "+e.getMessage());
                            throw new GeneralBOException("Errror"+e.getMessage());
                        }
                    }

                    for (PeriksaLab periksaLab: bean.getListLab()){
                        ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                        periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                        periksaLabEntity.setIdHeaderPemeriksaan(headerPemeriksaanEntity.getIdHeaderPemeriksaan());
                        periksaLabEntity.setIdPemeriksaan(periksaLab.getIdLab());
                        periksaLabEntity.setNamaPemeriksaan(periksaLab.getNamaLab());
                        periksaLabEntity.setFlag(bean.getFlag());
                        periksaLabEntity.setAction(bean.getAction());
                        periksaLabEntity.setCreatedWho(bean.getCreatedWho());
                        periksaLabEntity.setCreatedDate(bean.getCreatedDate());
                        periksaLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        periksaLabEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            periksaLabDao.addAndSave(periksaLabEntity);
                        } catch (HibernateException e) {
                            logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                        }

                        for (PeriksaLabDetail detail : periksaLab.getDetailLab()) {
                            if("Y".equalsIgnoreCase(bean.getIsLuar())){
                                ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                                detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                                detailEntity.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                                detailEntity.setIdLabDetail(detail.getIdPeriksaLabDetail());
                                detailEntity.setNamaDetailPeriksa(detail.getNamaDetailPeriksa());
                                detailEntity.setFlag(bean.getFlag());
                                detailEntity.setAction(bean.getAction());
                                detailEntity.setCreatedWho(bean.getCreatedWho());
                                detailEntity.setCreatedDate(bean.getCreatedDate());
                                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                detailEntity.setLastUpdate(bean.getLastUpdate());
                                try {
                                    periksaLabDetailDao.addAndSave(detailEntity);
                                } catch (HibernateException e) {
                                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                    throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                }
                            }else{
                                List<LabDetail> labDetailList = new ArrayList<>();
                                if(detail.getIdLabDetail() != null && !"".equalsIgnoreCase(detail.getIdLabDetail())){
                                    LabDetail lab = new LabDetail();
                                    lab.setIdLabDetail(detail.getIdLabDetail());
                                    try {
                                        labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                                    } catch (GeneralBOException e) {
                                        logger.error(e.getMessage());
                                        throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                    }
                                    if (labDetailList.size() > 0) {
                                        lab = labDetailList.get(0);
                                        ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                                        detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                                        detailEntity.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                                        detailEntity.setIdLabDetail(lab.getIdLabDetail());
                                        detailEntity.setNamaDetailPeriksa(lab.getNamaDetailPeriksa());
                                        detailEntity.setKeteranganAcuanL(lab.getKeteranganAcuanL());
                                        detailEntity.setKeteranganAcuanP(lab.getKeteranganAcuanP());
                                        detailEntity.setSatuan(lab.getSatuan());
                                        detailEntity.setTarif(lab.getTarif());
                                        detailEntity.setFlag(bean.getFlag());
                                        detailEntity.setAction(bean.getAction());
                                        detailEntity.setCreatedWho(bean.getCreatedWho());
                                        detailEntity.setCreatedDate(bean.getCreatedDate());
                                        detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        detailEntity.setLastUpdate(bean.getLastUpdate());

                                        try {
                                            periksaLabDetailDao.addAndSave(detailEntity);
                                        } catch (HibernateException e) {
                                            logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                        }
                                    }else{
                                        throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab ");
                                    }
                                }
                            }
                        }
                    }

                }else{
                    throw new GeneralBOException("Data pemeriksaan tidak ditemukan");
                }
            }else{
                throw new GeneralBOException("Data pemeriksaan tidak ditemukan");
            }

        }
        logger.info("[PeriksaLabBoImpl.editOrderPemeriksaan] END >>>>>>>>> ");
    }

    @Override
    public void saveOrderPemeriksaan(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveOrderPemeriksaan] START >>>>>>>>> ");
        if (bean.getListLab().size() > 0) {
            ItSimrsHeaderPemeriksaanEntity pemeriksaanEntity = new ItSimrsHeaderPemeriksaanEntity();
            pemeriksaanEntity.setIdHeaderPemeriksaan(headerPemeriksaanDao.getNextId());
            pemeriksaanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            pemeriksaanEntity.setIdKategoriLab(bean.getIdKategoriLab());
            pemeriksaanEntity.setIdDokterPengirim(bean.getIdDokterPengirim());
            pemeriksaanEntity.setNamaDokterPengirim(bean.getNamaDokterPengirim());
            pemeriksaanEntity.setTtdDokterPengirim(bean.getTtdPengirim());
            pemeriksaanEntity.setIsPending(bean.getIsPending());
            if("Y".equalsIgnoreCase(bean.getIsPending())){
                pemeriksaanEntity.setApproveFlag("Y");
            }
            pemeriksaanEntity.setIsPeriksaLuar(bean.getIsLuar());
            pemeriksaanEntity.setIsJustLab(bean.getIsJustLab());
            pemeriksaanEntity.setIsPending(bean.getIsPending());
            pemeriksaanEntity.setStatusPeriksa("0");
            pemeriksaanEntity.setFlag(bean.getFlag());
            pemeriksaanEntity.setAction(bean.getAction());
            pemeriksaanEntity.setCreatedWho(bean.getCreatedWho());
            pemeriksaanEntity.setCreatedDate(bean.getCreatedDate());
            pemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
            pemeriksaanEntity.setJenisPasien(bean.getJenisPeriksaPasien());
            if(bean.getTarifLabLuar() != null){
                pemeriksaanEntity.setTarifLabLuar(bean.getTarifLabLuar());
            }

            try {
                headerPemeriksaanDao.addAndSave(pemeriksaanEntity);
            }catch (HibernateException e){
                logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                throw new GeneralBOException("Error, "+e.getMessage());
            }

            for (PeriksaLab periksaLab: bean.getListLab()){
                ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                periksaLabEntity.setIdHeaderPemeriksaan(pemeriksaanEntity.getIdHeaderPemeriksaan());
                periksaLabEntity.setIdPemeriksaan(periksaLab.getIdLab());
                periksaLabEntity.setNamaPemeriksaan(periksaLab.getNamaLab());
                periksaLabEntity.setFlag(bean.getFlag());
                periksaLabEntity.setAction(bean.getAction());
                periksaLabEntity.setCreatedWho(bean.getCreatedWho());
                periksaLabEntity.setCreatedDate(bean.getCreatedDate());
                periksaLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                periksaLabEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    periksaLabDao.addAndSave(periksaLabEntity);
                } catch (HibernateException e) {
                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                    throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                }

                for (PeriksaLabDetail detail : periksaLab.getDetailLab()) {
                    if("Y".equalsIgnoreCase(bean.getIsLuar())){
                        ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                        detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                        detailEntity.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                        detailEntity.setIdLabDetail(detail.getIdPeriksaLabDetail());
                        detailEntity.setNamaDetailPeriksa(detail.getNamaDetailPeriksa());
                        detailEntity.setFlag(bean.getFlag());
                        detailEntity.setAction(bean.getAction());
                        detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                        detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                        detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());
                        detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                        try {
                            periksaLabDetailDao.addAndSave(detailEntity);
                        } catch (HibernateException e) {
                            logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                        }
                    }else{
                        List<LabDetail> labDetailList = new ArrayList<>();
                        if(detail.getIdLabDetail() != null && !"".equalsIgnoreCase(detail.getIdLabDetail())){
                            LabDetail lab = new LabDetail();
                            lab.setIdLabDetail(detail.getIdLabDetail());
                            try {
                                labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                            } catch (GeneralBOException e) {
                                logger.error(e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                            }
                            if (labDetailList.size() > 0) {
                                lab = labDetailList.get(0);
                                ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                                detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                                detailEntity.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                                detailEntity.setIdLabDetail(lab.getIdLabDetail());
                                detailEntity.setNamaDetailPeriksa(lab.getNamaDetailPeriksa());
                                detailEntity.setKeteranganAcuanL(lab.getKeteranganAcuanL());
                                detailEntity.setKeteranganAcuanP(lab.getKeteranganAcuanP());
                                detailEntity.setSatuan(lab.getSatuan());
                                detailEntity.setTarif(lab.getTarif());
                                detailEntity.setFlag(bean.getFlag());
                                detailEntity.setAction(bean.getAction());
                                detailEntity.setCreatedWho(bean.getCreatedWho());
                                detailEntity.setCreatedDate(bean.getCreatedDate());
                                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                detailEntity.setLastUpdate(bean.getLastUpdate());

                                try {
                                    periksaLabDetailDao.addAndSave(detailEntity);
                                } catch (HibernateException e) {
                                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                    throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                }
                            }else{
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab ");
                            }
                        }
                    }
                }
            }
        } else {
            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab ");
        }
        logger.info("[PeriksaLabBoImpl.saveOrderPemeriksaan] END <<<<<<<<< ");
    }

    @Override
    public CrudResponse saveUpdateHasilLab(PeriksaLabDetail bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveUpdateHasilLab] START >>>>>>>>> ");
        CrudResponse response = new CrudResponse();
        if (bean != null && bean.getIdPeriksaLabDetail() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLabDetail())) {
            List<ItSimrsPeriksaLabDetailEntity> labDetailEntities = getListEntityPerikasDetailLab(bean);
            if (!labDetailEntities.isEmpty() && labDetailEntities.size() > 0) {
                for (ItSimrsPeriksaLabDetailEntity labDetailEntity : labDetailEntities) {
                    labDetailEntity.setHasil(bean.getHasil());
                    labDetailEntity.setKeteranganPeriksa(bean.getKeteranganPeriksa());
                    labDetailEntity.setAction("U");
                    labDetailEntity.setLastUpdate(bean.getLastUpdate());
                    labDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        periksaLabDetailDao.updateAndSave(labDetailEntity);
                        response.setStatus("success");
                        response.setStatus("success");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setStatus("Error ketika save hasil" + e.getMessage());
                        logger.error("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When updating data periksa lab detail ", e);
                    }
                }
            }
        }
        logger.info("[PeriksaLabBoImpl.saveUpdateHasilLab] END <<<<<<<<< ");
        return response;
    }

    @Override
    public List<PeriksaLab> getListParameterLab(String idHeader) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListParameterLab] START >>>>>>>>> ");
        List<PeriksaLab> periksaLabDetailList = new ArrayList<>();
        if (idHeader != null) {
            try {
                periksaLabDetailList = periksaLabDao.getListParameterPemeriksaan(idHeader);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail ", e);
                throw new GeneralBOException("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail " + e.getCause());
            }
        }
        logger.info("[PeriksaLabBoImpl.getListParameterLab] END >>>>>>>>> ");
        return periksaLabDetailList;
    }

    @Override
    public void selesaiPemeriksaan(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.selesaiPemeriksaan] start <<<<<<<<<");
        if (bean != null) {
            ItSimrsHeaderPemeriksaanEntity entity = null;
            try {
                entity = headerPemeriksaanDao.getById("idHeaderPemeriksaan", bean.getIdHeaderPemeriksaan());
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.selesaiPemeriksaan] Error", e);
                throw new GeneralBOException("[PeriksaLabBoImpl.selesaiPemeriksaan] Error" + e.getMessage());
            }
            if (entity != null) {
                if(bean.getIdPetugas() != null && !"".equalsIgnoreCase(bean.getIdPetugas())){
                    entity.setIdPetugas(bean.getIdPetugas());
                }
                if(bean.getNamaPetugas() != null && !"".equalsIgnoreCase(bean.getNamaPetugas())){
                    entity.setNamaPetugas(bean.getNamaPetugas());
                }
                if(bean.getIdValidator() != null && !"".equalsIgnoreCase(bean.getIdValidator())){
                    entity.setIdValidator(bean.getIdValidator());
                }
                if(bean.getNamaValidator() != null && !"".equalsIgnoreCase(bean.getNamaValidator())){
                    entity.setNamaValidator(bean.getNamaValidator());
                }
                if(bean.getTtdValidator() != null && !"".equalsIgnoreCase(bean.getTtdValidator())){
                    entity.setTtdValidator(bean.getTtdValidator());
                }
                if(bean.getTtdPetugas() != null && !"".equalsIgnoreCase(bean.getTtdPetugas())){
                    entity.setTtdPetugas(bean.getTtdPetugas());
                }

                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setTanggalSelesaiPeriksa(bean.getLastUpdate());
                entity.setStatusPeriksa("3");
                entity.setApproveFlag("Y");
                entity.setTarifLabLuar(bean.getTarifLabLuar());
                entity.setCatatan(bean.getCatatan());
            }

            try {
                headerPemeriksaanDao.updateAndSave(entity);
            } catch (HibernateException e) { ;
                logger.error("[PeriksaLabBoImpl.selesaiPemeriksaan] Error", e);
                throw new GeneralBOException("Error" + e.getMessage());
            }
        }else{
            throw new GeneralBOException("Tidak menukan data pemeriksaan");
        }
        logger.info("[PeriksaLabBoImpl.selesaiPemeriksaan] End <<<<<<<<<");
    }

    @Override
    public CheckResponse updateFlagApprovePeriksaLab(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.updateFlagApprovePeriksaLab] START <<<<<<<<<");

        CheckResponse response = new CheckResponse();
        if (bean != null) {
            List<ItSimrsPeriksaLabEntity> entityList = getListEntityPeriksaLab(bean);
            if (entityList.size() > 0) {
                for (ItSimrsPeriksaLabEntity entity : entityList) {
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        periksaLabDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil update periksa lab");
                    } catch (HibernateException e) {
                        response.setMessage("error");
                        response.setMessage("Error when update periksa lab : " + e.getMessage());
                        logger.error("[PeriksaLabBoImpl.updateFlagApprovePeriksaLab] Error when update periksa lab ", e);
                        throw new GeneralBOException("Error when update periksa lab " + e.getMessage());
                    }
                }
            }
        }
        logger.info("[PeriksaLabBoImpl.updateFlagApprovePeriksaLab] END <<<<<<<<<");
        return response;
    }

    @Override
    public BigDecimal getTarifTotalPemeriksaan(String idPeriksaan) throws GeneralBOException {
        BigDecimal res = null;
        try {
            res = periksaLabDao.getTotalTarif(idPeriksaan);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return res;
    }

    private List<ItSimrsPeriksaLabDetailEntity> getListEntityPerikasDetailLab(PeriksaLabDetail bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] START >>>>>>>>> ");
        List<ItSimrsPeriksaLabDetailEntity> periksaLabDetailEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdPeriksaLabDetail() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLabDetail())) {
            hsCriteria.put("id_periksa_lab_detail", bean.getIdPeriksaLabDetail());
        }
        if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab())) {
            hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        }
        hsCriteria.put("flag", "Y");

        try {
            periksaLabDetailEntities = periksaLabDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When search data periksa lab detail ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When search data periksa lab detail " + e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] END <<<<<<<<< ");
        return periksaLabDetailEntities;
    }

    private List<ItSimrsPeriksaRadiologiEntity> getListEntityPerikasaRadiologi(PeriksaLabDetail bean) throws
            GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] START >>>>>>>>> ");
        List<ItSimrsPeriksaRadiologiEntity> periksaLabDetailEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdPeriksaLabDetail() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLabDetail())) {
            hsCriteria.put("id_periksa_lab_detail", bean.getIdPeriksaLabDetail());
        }
        if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab())) {
            hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        }
        hsCriteria.put("flag", "Y");

        try {
            periksaLabDetailEntities = periksaRadiologiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When search data periksa lab detail ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When search data periksa lab detail " + e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] END <<<<<<<<< ");
        return periksaLabDetailEntities;
    }

    private List<ItSimrsPeriksaLabEntity> getListEntityPeriksaLab(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab())) {
            hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        }

        if (bean.getIdHeaderPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPemeriksaan())) {
            hsCriteria.put("id_header_pemeriksaan", bean.getIdHeaderPemeriksaan());
        }

        if (bean.getIdPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdPemeriksaan())) {
            hsCriteria.put("id_pemeriksaan", bean.getIdPemeriksaan());
        }

        if (bean.getNamaPemeriksaan() != null && !"".equalsIgnoreCase(bean.getNamaPemeriksaan())) {
            hsCriteria.put("nama_pemeriksaan", bean.getNamaPemeriksaan());
        }

        hsCriteria.put("flag", "Y");
        List<ItSimrsPeriksaLabEntity> periksaLabEntities = new ArrayList<>();
        try {
            periksaLabEntities = periksaLabDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab " + e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] END <<<<<<<<< ");
        return periksaLabEntities;
    }

    private ImSimrsStatusPasienEntity getMasterStatusPasienByIdStatus(String idStatus) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_status_pasien", idStatus);

        List<ImSimrsStatusPasienEntity> statusPasienEntities = new ArrayList<>();
        try {
            statusPasienEntities = statusPasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data status ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data status " + e.getCause());
        }

        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] END <<<<<<<<< ");
        if (!statusPasienEntities.isEmpty() && statusPasienEntities.size() > 0) {
            return statusPasienEntities.get(0);
        }

        return new ImSimrsStatusPasienEntity();
    }

    private Lab getDatamasterLabById(String id) {
        logger.info("[PeriksaLabBoImpl.getDatamasterLabById] START >>>>>>>>> ");

        Lab lab = new Lab();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_lab", id);
        hsCriteria.put("flag", "Y");

        List<ImSimrsLabEntity> labEntities = null;
        try {
            labEntities = labDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getDatamasterLabById] ERROR When search data master lab ", e);
        }

        if (!labEntities.isEmpty() && labEntities.size() > 0) {
            ImSimrsLabEntity labEntity = labEntities.get(0);
            lab.setIdLab(labEntity.getIdLab());
            lab.setNamaLab(labEntity.getNamaLab());
        }

        logger.info("[PeriksaLabBoImpl.getDatamasterLabById] END <<<<<<<<< ");
        return lab;
    }

    private ImSimrsKategoriLabEntity getKategoriLabById(String id) {
        logger.info("[PeriksaLabBoImpl.getKategoriLabById] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_kategori_lab", id);
        hsCriteria.put("flag", "Y");

        List<ImSimrsKategoriLabEntity> kategoriLabEntities = new ArrayList<>();
        try {
            kategoriLabEntities = kategoriLabDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getKategoriLabById] ERROR When search data master lab kategori ", e);
        }

        logger.info("[PeriksaLabBoImpl.getKategoriLabById] END <<<<<<<<< ");
        if (!kategoriLabEntities.isEmpty() && kategoriLabEntities.size() > 0) {
            return kategoriLabEntities.get(0);
        }
        return new ImSimrsKategoriLabEntity();
    }

    private ImSimrsLabDetailEntity getDataMasterLabDetailByIdLab(String id) {
        logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] START >>>>>>>>> ");
        List<ImSimrsLabDetailEntity> labDetailEntities = new ArrayList<>();

        if (id != null && !"".equalsIgnoreCase(id)) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_lab_detail", id);
            hsCriteria.put("flag", "Y");

            try {
                labDetailEntities = labDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data master lab detail ", e);
            }

            logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] END <<<<<<<<< ");
            if (!labDetailEntities.isEmpty() && labDetailEntities.size() > 0) {
                return labDetailEntities.get(0);
            }
        }

        logger.info("[PeriksaLabBoImpl.getDataMasterLabDetailByIdLab] END <<<<<<<<< ");
        return new ImSimrsLabDetailEntity();
    }

    private String getNextPeriksaLabId() {
        String id = "";
        try {
            id = periksaLabDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getNextPeriksaLabId] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextDetailLapId() {
        String id = "";
        try {
            id = periksaLabDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getNextDetailLapId] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextLabRadiologyId() throws GeneralBOException {
        String id = "";
        try {
            id = periksaRadiologiDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getNextLabRadiologyId] ERROR When create sequences", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getNextLabRadiologyId] ERROR When create sequences", e);
        }
        return id;
    }

    public void setPeriksaLabDao(PeriksaLabDao periksaLabDao) {
        this.periksaLabDao = periksaLabDao;
    }

    public void setPeriksaLabDetailDao(PeriksaLabDetailDao periksaLabDetailDao) {
        this.periksaLabDetailDao = periksaLabDetailDao;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }

    public void setKategoriLabDao(KategoriLabDao kategoriLabDao) {
        this.kategoriLabDao = kategoriLabDao;
    }

    public void setStatusPasienDao(StatusPasienDao statusPasienDao) {
        this.statusPasienDao = statusPasienDao;
    }

    public void setPeriksaRadiologiDao(PeriksaRadiologiDao periksaRadiologiDao) {
        this.periksaRadiologiDao = periksaRadiologiDao;
    }

    @Override
    public String getDivisiIdKodering(String idDetailCheckup, String tipeLab) throws GeneralBOException {
        return periksaLabDao.getDivisiIdLabTransaction(idDetailCheckup, tipeLab);
    }

    @Override
    public void saveEditStatusPeriksa(PeriksaLab bean) throws GeneralBOException {
        if (bean != null) {
            ItSimrsHeaderPemeriksaanEntity headerPemeriksaanEntity = new ItSimrsHeaderPemeriksaanEntity();
            try {
                headerPemeriksaanEntity = headerPemeriksaanDao.getById("idHeaderPemeriksaan", bean.getIdHeaderPemeriksaan());
                if (headerPemeriksaanEntity != null) {
                    headerPemeriksaanEntity.setStatusPeriksa("1");
                    headerPemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerPemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
                    headerPemeriksaanEntity.setIsRead("Y");

                    if (headerPemeriksaanEntity.getTanggalMasukPeriksa() == null || "".equalsIgnoreCase(headerPemeriksaanEntity.getTanggalMasukPeriksa().toString())) {
                        headerPemeriksaanEntity.setTanggalMasukPeriksa(bean.getTanggalMasukLab());
                    }

                    try {
                        headerPemeriksaanDao.updateAndSave(headerPemeriksaanEntity);
                    } catch (HibernateException e) {
                        logger.error("Found Error" + e.getMessage());
                    }
                }
            } catch (HibernateException e) {
                logger.error("Found Error when " + e.getMessage());
            }
        }
    }

    @Override
    public List<Dokter> getListDokterLabRadiologi(String tipe) throws GeneralBOException {
        return periksaLabDao.getListDokterLabRadiologi(tipe);
    }

    @Override
    public void saveUpdateParameter(PeriksaLab bean) throws GeneralBOException {
        if (bean.getIdHeaderPemeriksaan() != null && bean.getListLab().size() > 0) {
            List<ItSimrsPeriksaLabEntity> periksaLabEntityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            hsCriteria.put("id_header_pemeriksaan", bean.getIdHeaderPemeriksaan());
            try {
                periksaLabEntityList = periksaLabDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Error"+e.getMessage());
            }

            if(periksaLabEntityList.size() > 0){
                for (ItSimrsPeriksaLabEntity entity: periksaLabEntityList){

                    List<ItSimrsPeriksaLabDetailEntity> detailEntityList = new ArrayList<>();
                    hsCriteria = new HashMap();
                    hsCriteria.put("id_periksa_lab", entity.getIdPeriksaLab());
                    try {
                        detailEntityList = periksaLabDetailDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        logger.error("Error"+e.getMessage());
                    }

                    if(detailEntityList.size() > 0){
                        for (ItSimrsPeriksaLabDetailEntity labDetailEntity: detailEntityList){
                            try {
                                periksaLabDetailDao.deleteAndSave(labDetailEntity);
                            }catch (HibernateException e){
                                logger.error(e.getMessage());
                            }
                        }
                    }

                    try {
                        periksaLabDao.deleteAndSave(entity);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }
                }
            }

            for (PeriksaLab periksaLab: bean.getListLab()){
                ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                periksaLabEntity.setIdHeaderPemeriksaan(bean.getIdHeaderPemeriksaan());
                periksaLabEntity.setIdPemeriksaan(periksaLab.getIdLab());
                periksaLabEntity.setNamaPemeriksaan(periksaLab.getNamaLab());
                periksaLabEntity.setFlag(bean.getFlag());
                periksaLabEntity.setAction(bean.getAction());
                periksaLabEntity.setCreatedWho(bean.getCreatedWho());
                periksaLabEntity.setCreatedDate(bean.getCreatedDate());
                periksaLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                periksaLabEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    periksaLabDao.addAndSave(periksaLabEntity);
                } catch (HibernateException e) {
                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                    throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                }

                for (PeriksaLabDetail detail : periksaLab.getDetailLab()) {
                    List<LabDetail> labDetailList = new ArrayList<>();
                    if(detail.getIdLabDetail() != null && !"".equalsIgnoreCase(detail.getIdLabDetail())){
                        LabDetail lab = new LabDetail();
                        lab.setIdLabDetail(detail.getIdLabDetail());
                        try {
                            labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                        } catch (GeneralBOException e) {
                            logger.error(e.getMessage());
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                        }
                        if (labDetailList.size() > 0) {
                            lab = labDetailList.get(0);
                            ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                            detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                            detailEntity.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                            detailEntity.setIdLabDetail(lab.getIdLabDetail());
                            detailEntity.setNamaDetailPeriksa(lab.getNamaDetailPeriksa());
                            detailEntity.setKeteranganAcuanL(lab.getKeteranganAcuanL());
                            detailEntity.setKeteranganAcuanP(lab.getKeteranganAcuanP());
                            detailEntity.setSatuan(lab.getSatuan());
                            detailEntity.setTarif(lab.getTarif());
                            detailEntity.setFlag(bean.getFlag());
                            detailEntity.setAction(bean.getAction());
                            detailEntity.setCreatedWho(bean.getCreatedWho());
                            detailEntity.setCreatedDate(bean.getCreatedDate());
                            detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            detailEntity.setLastUpdate(bean.getLastUpdate());

                            try {
                                periksaLabDetailDao.addAndSave(detailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                            }
                        }else{
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab ");
                        }
                    }
                }
            }

        } else {
            throw new GeneralBOException("Data yang dikirim tidak ditemukan...!");
        }
    }

    @Override
    public ItSimrsPeriksaLabEntity getPeriksaLabEntityById(String id) throws GeneralBOException {
        return periksaLabDao.getById("idPeriksaLab", id);
    }

    @Override
    public List<PeriksaLab> getListLab(String noChekcup, String jenis) throws GeneralBOException {
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        try {
            periksaLabList = periksaLabDao.getListLab(noChekcup, jenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return periksaLabList;
    }

    @Override
    public List<PeriksaLab> pushListLab(String kategori, String branchId) throws GeneralBOException {
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        try {
            periksaLabList = periksaLabDao.pushNotifLab(kategori, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return periksaLabList;
    }

    @Override
    public List<PeriksaLab> getHistoryLabRadiologi(String idPasien) throws GeneralBOException {
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        try {
            periksaLabList = periksaLabDao.getHistoryLabRadiologi(idPasien);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return periksaLabList;
    }

    @Override
    public void saveDetailParameter(PeriksaLab bean) throws GeneralBOException {
        if(bean != null){
            ItSimrsHeaderPemeriksaanEntity headerPemeriksaanEntity = headerPemeriksaanDao.getById("idHeaderPemeriksaan", bean.getIdHeaderPemeriksaan());
            if(headerPemeriksaanEntity != null){
                if(bean.getIdPetugas() != null && !"".equalsIgnoreCase(bean.getIdPetugas())){
                    headerPemeriksaanEntity.setIdPetugas(bean.getIdPetugas());
                }
                if(bean.getNamaPetugas() != null && !"".equalsIgnoreCase(bean.getNamaPetugas())){
                    headerPemeriksaanEntity.setNamaPetugas(bean.getNamaPetugas());
                }
                if(bean.getTtdPetugas() != null && !"".equalsIgnoreCase(bean.getTtdPetugas())){
                    headerPemeriksaanEntity.setTtdPetugas(bean.getTtdPetugas());
                }

                headerPemeriksaanEntity.setAction(bean.getAction());
                headerPemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
                headerPemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    headerPemeriksaanDao.updateAndSave(headerPemeriksaanEntity);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }

            ItSimrsPeriksaLabDetailEntity labDetailEntity = periksaLabDetailDao.getById("idPeriksaLabDetail", bean.getIdPeriksaLabDetail());
            if(labDetailEntity != null){
                if(bean.getHasil() != null && !"".equalsIgnoreCase(bean.getHasil())){
                    labDetailEntity.setHasil(bean.getHasil());
                }
                if(bean.getKeteranganHasil() != null && !"".equalsIgnoreCase(bean.getKeteranganHasil())){
                    labDetailEntity.setKeteranganPeriksa(bean.getKeteranganHasil());
                }

                labDetailEntity.setAction(bean.getAction());
                labDetailEntity.setLastUpdate(bean.getLastUpdate());
                labDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    periksaLabDetailDao.updateAndSave(labDetailEntity);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveSelesaiRadiologi(PeriksaLab bean) throws GeneralBOException {
        if(bean != null) {
            ItSimrsPeriksaLabEntity periksaLabEntity = periksaLabDao.getById("idPeriksaLab", bean.getIdPeriksaLab());
            if (periksaLabEntity != null) {
//                periksaLabEntity.setApproveFlag(bean.getApproveFlag());
//                periksaLabEntity.setTanggalSelesaiPeriksa(bean.getLastUpdate());
//                periksaLabEntity.setStatusPeriksa(bean.getStatusPeriksa());
                periksaLabEntity.setLastUpdate(bean.getLastUpdate());
                periksaLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                periksaLabEntity.setAction(bean.getAction());
                try {
                    periksaLabDao.updateAndSave(periksaLabEntity);
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }
        }
    }

    @Override
    public CrudResponse saveUpload(ItSimrsUploadHasilPemeriksaanEntity bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            if(bean.getUrlImg() != null && !"".equalsIgnoreCase(bean.getUrlImg())){
                HashMap hsCriteria = new HashMap();
                String fileName = bean.getUrlImg().replace(".jpg","").replace(".pdf", "").replace(".png","");
                if(fileName != null && !"".equalsIgnoreCase(fileName)){
                    hsCriteria.put("url_img", fileName);
                    List<ItSimrsUploadHasilPemeriksaanEntity> list = new ArrayList<>();
                    try {
                        list = uploadHasilPeriksaDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                        throw new GeneralBOException(e.getMessage());
                    }
                    if(list.size() > 0){
                        for (ItSimrsUploadHasilPemeriksaanEntity entity: list){
                            try {
                                uploadHasilPeriksaDao.deleteAndSave(entity);
                            }catch (HibernateException e){
                                logger.error(e.getMessage());
                                throw new GeneralBOException(e.getMessage());
                            }
                        }
                    }
                }
            }
            try {
                bean.setIdUploadHasilPemeriksaan(uploadHasilPeriksaDao.getNextId());
                uploadHasilPeriksaDao.addAndSave(bean);
                response.setMsg(bean.getIdUploadHasilPemeriksaan());
            }catch (HibernateException e){
                logger.error(e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public void deleteUpload(String id) throws GeneralBOException {
        if(id != null){
            ItSimrsUploadHasilPemeriksaanEntity entity = uploadHasilPeriksaDao.getById("idUploadHasilPemeriksaan", id);
            if(entity != null){
                try {
                    uploadHasilPeriksaDao.deleteAndSave(entity);
                    File myFile = new File(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PEMERIKSAAN+entity.getUrlImg());
                    if (!myFile.isDirectory()){
                        myFile.delete();
                    }
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<PeriksaLab> getByCriteriaHeaderPemeriksaan(PeriksaLab bean) throws GeneralBOException {
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsHeaderPemeriksaanEntity> pemeriksaanEntityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdHeaderPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPemeriksaan())){
                hsCriteria.put("id_header_pemeriksaan", bean.getIdHeaderPemeriksaan());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag())){
                hsCriteria.put("approve_flag", bean.getApproveFlag());
            }
            try {
                pemeriksaanEntityList = headerPemeriksaanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[PeriksaLabBoImpl.getByCriteriaHeaderPemeriksaan] ERROR " + e.getMessage());
                throw new GeneralBOException("[PeriksaLabBoImpl.getByCriteriaHeaderPemeriksaan] ERROR " + e.getMessage());
            }
            if(pemeriksaanEntityList.size() > 0){
                for (ItSimrsHeaderPemeriksaanEntity entity: pemeriksaanEntityList){
                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdHeaderPemeriksaan(entity.getIdHeaderPemeriksaan());
                    periksaLab.setIdDetailCheckup(entity.getIdDetailCheckup());
                    periksaLab.setIdKategoriLab(entity.getIdKategoriLab());
                    ImSimrsKategoriLabEntity kategoriLabEntity = getKategoriLabById(entity.getIdKategoriLab());
                    if(kategoriLabEntity != null){
                        periksaLab.setKategoriLabName(kategoriLabEntity.getNamaKategori());
                        periksaLab.setKategori(kategoriLabEntity.getKategori());
                    }
                    periksaLab.setIdDokterPengirim(entity.getIdDokterPengirim());
                    periksaLab.setNamaDokterPengirim(entity.getNamaDokterPengirim());
                    if(entity.getTtdDokterPengirim() != null){
                        periksaLab.setTtdDokter(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+entity.getTtdDokterPengirim());
                    }
                    periksaLab.setIdPetugas(entity.getIdPetugas());
                    periksaLab.setNamaPetugas(entity.getNamaPetugas());
                    if(entity.getTtdPetugas() != null){
                        periksaLab.setTtdPetugas(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+entity.getTtdPetugas());
                    }
                    periksaLab.setIdValidator(entity.getIdValidator());
                    periksaLab.setNamaValidator(entity.getNamaValidator());
                    if(entity.getTtdValidator() != null){
                        periksaLab.setTtdValidator(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+entity.getTtdValidator());
                    }

                    periksaLab.setStatusPeriksa(entity.getStatusPeriksa());
                    String statusName = "";
                    if("0".equalsIgnoreCase(entity.getStatusPeriksa())){
                        if("Y".equalsIgnoreCase(entity.getIsPending())){
                            statusName = "Pending";
                        }else {
                            statusName = "Antrian";
                        }
                    }else if("1".equalsIgnoreCase(entity.getStatusPeriksa())){
                        statusName = "Periksa";
                    }else if("3".equalsIgnoreCase(entity.getStatusPeriksa())){
                        statusName = "Selesai";
                    }

                    periksaLab.setStatusPeriksaName(statusName);
                    periksaLab.setCatatan(entity.getCatatan());
                    periksaLab.setTarifLabLuar(entity.getTarifLabLuar());
                    periksaLab.setApproveFlag(entity.getApproveFlag());
                    periksaLab.setIsJustLab(entity.getIsJustLab());
                    periksaLab.setIsPending(entity.getIsPending());
                    periksaLab.setIsPeriksaLuar(entity.getIsPeriksaLuar());
                    periksaLab.setIsRead(entity.getIsRead());
                    periksaLab.setTanggalMasukLab(entity.getTanggalMasukPeriksa());
                    periksaLab.setTanggalSelesaiPeriksa(entity.getTanggalSelesaiPeriksa());
                    periksaLab.setFlag(entity.getFlag());
                    periksaLab.setAction(entity.getAction());
                    periksaLab.setCreatedWho(entity.getCreatedWho());
                    periksaLab.setCreatedDate(entity.getCreatedDate());
                    periksaLab.setLastUpdateWho(entity.getLastUpdateWho());
                    periksaLab.setLastUpdate(entity.getLastUpdate());
                    periksaLab.setJenisPeriksaPasien(entity.getJenisPasien());

                    List<UploadHasilPemeriksaan> tempDalam = new ArrayList<>();
                    List<ItSimrsUploadHasilPemeriksaanEntity> dalam = new ArrayList<>();
                    try {
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_header_pemeriksaan", entity.getIdHeaderPemeriksaan());
                        hsCriteria.put("tipe", "dalam");
                        dalam = uploadHasilPeriksaDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }
                    if(dalam.size() > 0){
                        for (ItSimrsUploadHasilPemeriksaanEntity list1: dalam){
                            UploadHasilPemeriksaan pemeriksaan = new UploadHasilPemeriksaan();
                            if(list1.getUrlImg() != null){
                                pemeriksaan.setUrlImg(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_PEMERIKSAAN+list1.getUrlImg());
                                tempDalam.add(pemeriksaan);
                            }
                        }
                    }
                    if(tempDalam.size() > 0){
                        periksaLab.setUploadDalam(tempDalam);
                    }

                    List<UploadHasilPemeriksaan> tempLuar = new ArrayList<>();
                    List<ItSimrsUploadHasilPemeriksaanEntity> luar = new ArrayList<>();
                    try {
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_header_pemeriksaan", entity.getIdHeaderPemeriksaan());
                        hsCriteria.put("tipe", "luar");
                        luar = uploadHasilPeriksaDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }
                    if(luar.size() > 0){
                        for (ItSimrsUploadHasilPemeriksaanEntity list2: luar){
                            UploadHasilPemeriksaan pemeriksaan = new UploadHasilPemeriksaan();
                            if(list2.getUrlImg() != null){
                                pemeriksaan.setUrlImg(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_PEMERIKSAAN+list2.getUrlImg());
                                tempLuar.add(pemeriksaan);
                            }
                        }
                    }

                    if(tempLuar.size() > 0){
                        periksaLab.setUploadLuar(tempLuar);
                    }

                    periksaLabList.add(periksaLab);
                }
            }
        }
        return periksaLabList;
    }

    @Override
    public ItSimrsHeaderPemeriksaanEntity getEntityHeaderpemeriksaan(String id) throws GeneralBOException {
        ItSimrsHeaderPemeriksaanEntity entity = new ItSimrsHeaderPemeriksaanEntity();
        try {
            entity = headerPemeriksaanDao.getById("idHeaderPemeriksaan", id);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<UploadHasilPemeriksaan> hasilUploadPemeriksaan(String id) throws GeneralBOException {
        List<UploadHasilPemeriksaan> entity = new ArrayList<>();
        try {
            entity = periksaLabDao.getListUploadHasilPemeriksaan(id);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return entity;
    }

    public void setUploadHasilPeriksaDao(UploadHasilPeriksaDao uploadHasilPeriksaDao) {
        this.uploadHasilPeriksaDao = uploadHasilPeriksaDao;
    }

    public void setHeaderPemeriksaanDao(HeaderPemeriksaanDao headerPemeriksaanDao) {
        this.headerPemeriksaanDao = headerPemeriksaanDao;
    }
}
