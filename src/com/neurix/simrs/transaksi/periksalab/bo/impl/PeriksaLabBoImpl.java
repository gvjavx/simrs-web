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
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDetailDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabDetailEntity;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;
import com.neurix.simrs.transaksi.periksaradiologi.dao.PeriksaRadiologiDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 27/11/2019.
 */
public class PeriksaLabBoImpl implements PeriksaLabBo {
    private static transient Logger logger = Logger.getLogger(PeriksaLabBoImpl.class);
    private PeriksaLabDao periksaLabDao;
    private PeriksaLabDetailDao periksaLabDetailDao;
    private LabDetailDao labDetailDao;
    private LabDao labDao;
    private KategoriLabDao kategoriLabDao;
    private StatusPasienDao statusPasienDao;
    private PeriksaRadiologiDao periksaRadiologiDao;

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
                    if (periksaLabEntity.getStatusPeriksa() != null && !"".equalsIgnoreCase(periksaLabEntity.getStatusPeriksa())) {
                        ImSimrsStatusPasienEntity status = getMasterStatusPasienByIdStatus(periksaLabEntity.getStatusPeriksa());
                        if ("0".equalsIgnoreCase(status.getIdStatusPasien()) && "Y".equalsIgnoreCase(periksaLabEntity.getIsPending())) {
                            periksaLab.setStatusPeriksaName("Pending");
                        } else {
                            periksaLab.setStatusPeriksa(status.getIdStatusPasien());
                            periksaLab.setStatusPeriksaName(status.getKeterangan());
                        }
                    }
                    ImSimrsLabEntity labEntity = labDao.getById("idLab", periksaLabEntity.getIdLab());
                    if (labEntity != null) {
                        periksaLab.setIdLab(labEntity.getIdLab());
                        periksaLab.setLabName(labEntity.getNamaLab());
                    }
                    ImSimrsKategoriLabEntity kategoriLabEntity = kategoriLabDao.getById("idKategoriLab", periksaLabEntity.getIdKategoriLab());
                    if (kategoriLabEntity != null) {
                        periksaLab.setKategoriLabName(kategoriLabEntity.getNamaKategori());
                        periksaLab.setIdKategoriLab(kategoriLabEntity.getIdKategoriLab());
                        periksaLab.setKategori(kategoriLabEntity.getKategori());
                    }
                    periksaLab.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                    periksaLab.setIdDetailCheckup(periksaLabEntity.getIdDetailCheckup());
                    periksaLab.setFlag(periksaLabEntity.getFlag());
                    periksaLab.setAction(periksaLabEntity.getAction());
                    periksaLab.setCreatedDate(periksaLabEntity.getCreatedDate());
                    periksaLab.setCreatedWho(periksaLabEntity.getCreatedWho());
                    periksaLab.setLastUpdate(periksaLabEntity.getLastUpdate());
                    periksaLab.setLastUpdateWho(periksaLabEntity.getLastUpdateWho());
                    periksaLab.setApproveFlag(periksaLabEntity.getApproveFlag());
                    if (periksaLabEntity.getUrlImg() != null && !"".equalsIgnoreCase(periksaLabEntity.getUrlImg())) {
                        periksaLab.setUrlImg(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + periksaLabEntity.getUrlImg());
                    }
                    if (periksaLabEntity.getTtdPengirim() != null && !"".equalsIgnoreCase(periksaLabEntity.getTtdPengirim())) {
                        periksaLab.setTtdPengirim(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_DOKTER + periksaLabEntity.getTtdPengirim());
                    }
                    periksaLab.setIsPending(periksaLabEntity.getIsPending());

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
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdLab(bean.getIdLab());
        entity.setIdDokterPengirim(bean.getIdDokterPengirim());
        entity.setStatusPeriksa("0");
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

    @Override
    public CrudResponse saveEdit(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveEdit] START >>>>>>>>> ");
        CrudResponse response = new CrudResponse();
        if (periksaLab != null && labDetailIds.size() > 0) {
            ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
            try {
                periksaLabEntity = periksaLabDao.getById("idPeriksaLab", periksaLab.getIdPeriksaLab());
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById periksa lab ", e);
            }
            if (periksaLabEntity != null) {
                periksaLabEntity.setIdLab(periksaLab.getIdLab());
                periksaLabEntity.setAction(periksaLab.getAction());
                periksaLabEntity.setLastUpdate(periksaLab.getLastUpdate());
                periksaLabEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());
            }

            try {
                periksaLabDao.updateAndSave(periksaLabEntity);
                response.setMsg("success");
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
            }

            List<ItSimrsPeriksaLabDetailEntity> periksaLabDetailEntity = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_periksa_lab", periksaLab.getIdPeriksaLab());
            hsCriteria.put("flag", "Y");

            try {
                periksaLabDetailEntity = periksaLabDetailDao.getByCriteria(hsCriteria);
                response.setMsg("success");
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById periksa lab detail", e);
            }

            if (periksaLabDetailEntity.size() > 0) {
                for (ItSimrsPeriksaLabDetailEntity entity : periksaLabDetailEntity) {
                    entity.setAction("D");
                    entity.setFlag("N");
                    entity.setLastUpdate(periksaLab.getLastUpdate());
                    entity.setLastUpdateWho(periksaLab.getLastUpdateWho());
                    try {
                        periksaLabDetailDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab ", e.getCause());
                    }
                }

                for (String labDetailId : labDetailIds) {
                    LabDetail detail = new LabDetail();
                    List<LabDetail> labDetailList = new ArrayList<>();
                    if (labDetailId != null && !"".equalsIgnoreCase(labDetailId)) {
                        LabDetail lab = new LabDetail();
                        lab.setIdLabDetail(labDetailId);
                        try {
                            labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                        } catch (GeneralBOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (labDetailList.size() > 0) {
                        detail = labDetailList.get(0);
                        ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                        detailEntity.setIdPeriksaLabDetail("DPL" + getNextDetailLapId());
                        detailEntity.setIdPeriksaLab(periksaLab.getIdPeriksaLab());
                        detailEntity.setIdLabDetail(labDetailId);
                        detailEntity.setNamaDetailPeriksa(detail.getNamaDetailPeriksa());
                        detailEntity.setKeteranganAcuanL(detail.getKeteranganAcuanL());
                        detailEntity.setKeteranganAcuanP(detail.getKeteranganAcuanP());
                        detailEntity.setSatuan(detail.getSatuan());
                        detailEntity.setTarif(detail.getTarif());
                        detailEntity.setFlag("Y");
                        detailEntity.setAction("C");
                        detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                        detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                        detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                        detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                        try {
                            periksaLabDetailDao.addAndSave(detailEntity);
                            response.setStatus("success");
                            response.setMsg("Oke");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg(e.getMessage());
                            logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                        }
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] START >>>>>>>>> ");
        CrudResponse response = new CrudResponse();
        if (periksaLab != null && labDetailIds.size() > 0) {
            ItSimrsPeriksaLabEntity entity = new ItSimrsPeriksaLabEntity();
            entity.setIdPeriksaLab("PRL" + getNextPeriksaLabId());
            entity.setIdLab(periksaLab.getIdLab());
            entity.setIdDetailCheckup(periksaLab.getIdDetailCheckup());
            entity.setIdDokterPengirim(periksaLab.getIdDokterPengirim());
            entity.setStatusPeriksa("0");
            entity.setFlag(periksaLab.getFlag());
            entity.setAction(periksaLab.getAction());
            entity.setCreatedDate(periksaLab.getCreatedDate());
            entity.setCreatedWho(periksaLab.getCreatedWho());
            entity.setLastUpdate(periksaLab.getLastUpdate());
            entity.setLastUpdateWho(periksaLab.getLastUpdateWho());
            entity.setTtdPengirim(periksaLab.getTtdPengirim());
            entity.setIdKategoriLab(periksaLab.getIdKategoriLab());
            entity.setIsPending(periksaLab.getIsPending());
            if ("Y".equalsIgnoreCase(entity.getIsPending())) {
                entity.setApproveFlag("Y");
            }

            try {
                periksaLabDao.addAndSave(entity);
                response.setStatus("success");
                response.setMsg("Oke");
            } catch (HibernateException e) {
                response.setMsg(e.getMessage());
                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
            }

            for (String labDetailId : labDetailIds) {
                LabDetail detail = new LabDetail();
                List<LabDetail> labDetailList = new ArrayList<>();
                if (labDetailId != null && !"".equalsIgnoreCase(labDetailId)) {
                    LabDetail lab = new LabDetail();
                    lab.setIdLabDetail(labDetailId);
                    try {
                        labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                    } catch (GeneralBOException e) {
                        logger.error(e.getMessage());
                    }
                }
                if (labDetailList.size() > 0) {
                    detail = labDetailList.get(0);
                    ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                    detailEntity.setIdPeriksaLabDetail("DPL" + getNextDetailLapId());
                    detailEntity.setIdPeriksaLab(entity.getIdPeriksaLab());
                    detailEntity.setIdLabDetail(labDetailId);
                    detailEntity.setNamaDetailPeriksa(detail.getNamaDetailPeriksa());
                    detailEntity.setKeteranganAcuanL(detail.getKeteranganAcuanL());
                    detailEntity.setKeteranganAcuanP(detail.getKeteranganAcuanP());
                    detailEntity.setSatuan(detail.getSatuan());
                    detailEntity.setTarif(detail.getTarif());
                    detailEntity.setFlag("Y");
                    detailEntity.setAction("C");
                    detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                    detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                    detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                    detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                    try {
                        periksaLabDetailDao.addAndSave(detailEntity);
                        response.setStatus("success");
                        response.setMsg("Oke");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                        logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                        throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                    }
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data yang dikirim tidak ditemukan...!");
        }
        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] END <<<<<<<<< ");
        return response;
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
    public List<PeriksaLabDetail> getListParameterLab(PeriksaLabDetail bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListParameterLab] START >>>>>>>>> ");
        List<PeriksaLabDetail> periksaLabDetailList = new ArrayList<>();
        if (bean != null) {
            PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();
            periksaLabDetail.setIdPeriksaLab(bean.getIdPeriksaLab());
            List<ItSimrsPeriksaLabDetailEntity> listEntity = null;
            try {
                listEntity = getListEntityPerikasDetailLab(periksaLabDetail);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail ", e);
                throw new GeneralBOException("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail " + e.getCause());
            }
            if (listEntity.size() > 0) {
                for (ItSimrsPeriksaLabDetailEntity entity : listEntity) {
                    PeriksaLabDetail detail = new PeriksaLabDetail();
                    detail.setIdPeriksaLabDetail(entity.getIdPeriksaLabDetail());
                    detail.setIdPeriksaLab(entity.getIdPeriksaLab());
                    detail.setIdLabDetail(entity.getIdLabDetail());
                    detail.setNamaDetailPeriksa(entity.getNamaDetailPeriksa());
                    detail.setSatuan(entity.getSatuan());
                    detail.setHasil(entity.getHasil());
                    detail.setKeteranganAcuanL(entity.getKeteranganAcuanL());
                    detail.setKeteranganAcuanP(entity.getKeteranganAcuanP());
                    detail.setTarif(entity.getTarif());
                    detail.setKeteranganPeriksa(entity.getKeteranganPeriksa());
                    detail.setFlag(entity.getFlag());
                    detail.setAction(entity.getAction());
                    detail.setCreatedDate(entity.getCreatedDate());
                    detail.setCreatedWho(entity.getCreatedWho());
                    detail.setLastUpdate(entity.getLastUpdate());
                    detail.setLastUpdateWho(entity.getLastUpdateWho());
                    periksaLabDetailList.add(detail);
                }
            }
        }
        logger.info("[PeriksaLabBoImpl.getListParameterLab] END >>>>>>>>> ");
        return periksaLabDetailList;
    }

    @Override
    public CheckResponse saveDokterLab(PeriksaLab bean, List<PeriksaLabDetail> list) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveDokterLab] start <<<<<<<<<");
        CheckResponse response = new CheckResponse();
        if (bean != null) {
            if (list.size() > 0) {
                for (PeriksaLabDetail detail : list) {
                    ItSimrsPeriksaLabDetailEntity labDetailEntity = periksaLabDetailDao.getById("idPeriksaLabDetail", detail.getIdPeriksaLabDetail());
                    if (labDetailEntity != null) {
                        labDetailEntity.setHasil(detail.getHasil());
                        labDetailEntity.setKeteranganPeriksa(detail.getKeteranganPeriksa());
                        labDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        labDetailEntity.setLastUpdate(bean.getLastUpdate());
                        labDetailEntity.setAction("U");
                        try {
                            periksaLabDetailDao.updateAndSave(labDetailEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil");
                        } catch (HibernateException e) {
                            logger.error(e.getMessage());
                            response.setStatus("error");
                            response.setMessage("Error " + e.getMessage());
                        }
                    }
                }
            }
            ItSimrsPeriksaLabEntity entity = null;
            try {
                entity = periksaLabDao.getById("idPeriksaLab", bean.getIdPeriksaLab());
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error " + e.getMessage());
                logger.error("[PeriksaLabBoImpl.saveDokterLab] Error when getById periksa lab ", e);
                throw new GeneralBOException("[PeriksaLabBoImpl.saveDokterLab] Error when save edit periksa lab " + e.getMessage());
            }
            if (entity != null) {

                entity.setIdDokter(bean.getIdDokter());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setTanggalSelesaiPeriksa(bean.getLastUpdate());
                entity.setStatusPeriksa("3");
                entity.setApproveFlag("Y");
                entity.setUrlImg(bean.getUrlImg());
                entity.setIdPemeriksa(bean.getIdPemeriksa());
                entity.setTtdDokter(bean.getTtdDokter());
                entity.setTtdPetugas(bean.getTtdPetugas());
            }

            try {
                periksaLabDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error " + e.getMessage());
                logger.error("[PeriksaLabBoImpl.saveDokterLab] Error when periksa lab ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        logger.info("[PeriksaLabBoImpl.saveDokterLab] End <<<<<<<<<");
        return response;
    }

    @Override
    public CheckResponse updateFlagApprovePeriksaLab(PeriksaLab bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.updateFlagApprovePeriksaLab] START <<<<<<<<<");

        CheckResponse response = new CheckResponse();
        if (bean != null) {
            List<ItSimrsPeriksaLabEntity> entityList = getListEntityPeriksaLab(bean);
            if (entityList.size() > 0) {
                for (ItSimrsPeriksaLabEntity entity : entityList) {

                    entity.setApproveFlag("Y");
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

    private List<ItSimrsPeriksaLabDetailEntity> getListEntityPerikasDetailLab(PeriksaLabDetail bean) throws
            GeneralBOException {
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

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
            hsCriteria.put("status", bean.getStatusPeriksa());
        }

        if (bean.getApproveFlag() != null && !"".equalsIgnoreCase(bean.getApproveFlag())) {
            hsCriteria.put("approve_flag", bean.getApproveFlag());
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
            lab.setIdKategoriLab(labEntity.getIdKategoriLab());

            if (labEntity.getIdKategoriLab() != null && !"".equalsIgnoreCase(labEntity.getIdKategoriLab())) {
                ImSimrsKategoriLabEntity kategoriLabEntity = getKategoriLabById(labEntity.getIdKategoriLab());
                if (kategoriLabEntity != null) {
                    lab.setKategoriLabName(kategoriLabEntity.getNamaKategori());
                    lab.setKategori(kategoriLabEntity.getNamaKategori());
                }
            }
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
            ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
            try {
                periksaLabEntity = periksaLabDao.getById("idPeriksaLab", bean.getIdPeriksaLab());
                if (periksaLabEntity.getIdPeriksaLab() != null) {
                    periksaLabEntity.setStatusPeriksa("1");
                    periksaLabEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    periksaLabEntity.setLastUpdate(bean.getLastUpdate());

                    if (periksaLabEntity.getTanggalMasukLab() == null || "".equalsIgnoreCase(periksaLabEntity.getTanggalMasukLab().toString())) {
                        periksaLabEntity.setTanggalMasukLab(bean.getTanggalMasukLab());
                    }

                    try {
                        periksaLabDao.updateAndSave(periksaLabEntity);
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
    public PeriksaLab getNamaLab(String idPeriksa) throws GeneralBOException {
        return periksaLabDao.getNamaLab(idPeriksa);
    }

    @Override
    public CrudResponse saveUpdateParameter(PeriksaLab bean, List<String> listParams) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null && listParams.size() > 0) {
            for (String labDetailId : listParams) {
                LabDetail detail = new LabDetail();
                List<LabDetail> labDetailList = new ArrayList<>();
                if (labDetailId != null && !"".equalsIgnoreCase(labDetailId)) {
                    LabDetail lab = new LabDetail();
                    lab.setIdLabDetail(labDetailId);
                    try {
                        labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                    } catch (GeneralBOException e) {
                        logger.error(e.getMessage());
                    }
                }
                if (labDetailList.size() > 0) {
                    detail = labDetailList.get(0);
                    ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                    detailEntity.setIdPeriksaLabDetail("DPL" + getNextDetailLapId());
                    detailEntity.setIdPeriksaLab(bean.getIdPeriksaLab());
                    detailEntity.setIdLabDetail(labDetailId);
                    detailEntity.setNamaDetailPeriksa(detail.getNamaDetailPeriksa());
                    detailEntity.setKeteranganAcuanL(detail.getKeteranganAcuanL());
                    detailEntity.setKeteranganAcuanP(detail.getKeteranganAcuanP());
                    detailEntity.setSatuan(detail.getSatuan());
                    detailEntity.setTarif(detail.getTarif());
                    detailEntity.setFlag("Y");
                    detailEntity.setAction("C");
                    detailEntity.setCreatedDate(bean.getCreatedDate());
                    detailEntity.setCreatedWho(bean.getCreatedWho());
                    detailEntity.setLastUpdate(bean.getLastUpdate());
                    detailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        periksaLabDetailDao.addAndSave(detailEntity);
                        response.setStatus("success");
                        response.setMsg("Oke");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                        logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                        throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                    }
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data yang dikirim tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public ItSimrsPeriksaLabEntity getPeriksaLabEntityById(String id) throws GeneralBOException {
        return periksaLabDao.getById("idPeriksaLab", id);
    }

    @Override
    public List<PeriksaLab> getListLab(String noChekcup) throws GeneralBOException {
        return periksaLabDao.getListLab(noChekcup);
    }

    @Override
    public List<PeriksaLab> pushListLab(String kategori, String branchId) throws GeneralBOException {
        return periksaLabDao.pushNotifLab(kategori, branchId);
    }
}
