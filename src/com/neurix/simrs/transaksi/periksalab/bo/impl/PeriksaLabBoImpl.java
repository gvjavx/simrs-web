package com.neurix.simrs.transaksi.periksalab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
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
            // get data transaction periksa lab by criteria (bean)
            List<ItSimrsPeriksaLabEntity> periksaLabEntities = getListEntityPeriksaLab(bean);

            if (!periksaLabEntities.isEmpty() && periksaLabEntities.size() > 0) {
                PeriksaLab periksaLab;
                for (ItSimrsPeriksaLabEntity periksaLabEntity : periksaLabEntities) {
                    periksaLab = new PeriksaLab();

                    // get master data lab
                    Lab lab = getDatamasterLabById(periksaLabEntity.getIdLab());

                    // get master data status periksa
                    if (periksaLabEntity.getStatusPeriksa() != null && !"".equalsIgnoreCase(periksaLabEntity.getStatusPeriksa())) {
                        ImSimrsStatusPasienEntity status = getMasterStatusPasienByIdStatus(periksaLabEntity.getStatusPeriksa());
                        periksaLab.setStatusPeriksa(status.getIdStatusPasien());
                        periksaLab.setStatusPeriksaName(status.getKeterangan());
                    }

                    periksaLab.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                    periksaLab.setIdDetailCheckup(periksaLabEntity.getIdDetailCheckup());
                    periksaLab.setIdLab(lab.getIdLab());
                    periksaLab.setLabName(lab.getNamaLab());
                    periksaLab.setKategoriLabName(lab.getKategoriLabName());
                    periksaLab.setIdKategoriLab(lab.getIdKategoriLab());
                    periksaLab.setFlag(periksaLabEntity.getFlag());
                    periksaLab.setAction(periksaLabEntity.getAction());
                    periksaLab.setCreatedDate(periksaLabEntity.getCreatedDate());
                    periksaLab.setCreatedWho(periksaLabEntity.getCreatedWho());
                    periksaLab.setLastUpdate(periksaLabEntity.getLastUpdate());
                    periksaLab.setLastUpdateWho(periksaLabEntity.getLastUpdateWho());
                    periksaLab.setApproveFlag(periksaLabEntity.getApproveFlag());

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
    public void saveEdit(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveEdit] START >>>>>>>>> ");

        if (periksaLab != null) {
            ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();

            try {
                periksaLabEntity = periksaLabDao.getById("idPeriksaLab", periksaLab.getIdPeriksaLab());
            } catch (HibernateException e) {
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById periksa lab ", e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit periksa lab " + e.getMessage());
            }

            if (periksaLabEntity != null) {
                periksaLabEntity.setIdLab(periksaLab.getIdLab());
                periksaLabEntity.setAction(periksaLab.getAction());
                periksaLabEntity.setLastUpdate(periksaLab.getLastUpdate());
                periksaLabEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());
            }

            try {
                periksaLabDao.updateAndSave(periksaLabEntity);
            } catch (HibernateException e) {
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }

            Lab labEntity = getDatamasterLabById(periksaLabEntity.getIdLab());

            if (labEntity != null) {
                if ("Radiologi".equalsIgnoreCase(labEntity.getKategoriLabName())) {
                    PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();
                    periksaLabDetail.setIdPeriksaLab(periksaLabEntity.getIdPeriksaLab());
                    List<ItSimrsPeriksaRadiologiEntity> list = getListEntityPerikasaRadiologi(periksaLabDetail);
                    if (list.size() > 0) {
                        for (ItSimrsPeriksaRadiologiEntity entity : list) {
                            entity.setFlag("N");
                            try {
                                periksaRadiologiDao.updateAndSave(entity);
                            } catch (HibernateException e) {
                                logger.error("Found Error " + e.getMessage());
                            }
                        }

                        if (labDetailIds != null && labDetailIds.size() > 0) {

                            for (String labDetail : labDetailIds) {

                                ItSimrsPeriksaRadiologiEntity radiologiEntity = new ItSimrsPeriksaRadiologiEntity();
                                radiologiEntity.setIdPeriksaRadiologi("RLG" + periksaRadiologiDao.getNextId());
                                radiologiEntity.setIdDetailCheckup(periksaLabEntity.getIdDetailCheckup());
                                radiologiEntity.setIdLab(labEntity.getIdKategoriLab());
                                radiologiEntity.setStatusPeriksa("0");
                                radiologiEntity.setIdPeriksaLab(periksaLab.getIdPeriksaLab());
                                radiologiEntity.setIdLabDetail(labDetail);

                                // get data from master lab detail
                                ImSimrsLabDetailEntity labDetailEntity = new ImSimrsLabDetailEntity();

                                try {
                                    labDetailEntity = labDetailDao.getById("idLabDetail", labDetail);
                                } catch (HibernateException e) {

                                }

                                if (labDetailEntity != null) {
                                    radiologiEntity.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                                }

                                radiologiEntity.setFlag("Y");
                                radiologiEntity.setAction("C");
                                radiologiEntity.setCreatedDate(periksaLab.getCreatedDate());
                                radiologiEntity.setCreatedWho(periksaLab.getCreatedWho());
                                radiologiEntity.setLastUpdate(periksaLab.getLastUpdate());
                                radiologiEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                                try {
                                    periksaRadiologiDao.addAndSave(radiologiEntity);
                                } catch (HibernateException e) {
                                    logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa radiology " + e.getMessage());
                                    throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa radiology " + e.getMessage());
                                }
                            }
                        }
                    }
                } else { // selain radiologi akan menyimpan lab parameters
                    List<ItSimrsPeriksaLabDetailEntity> periksaLabDetailEntity = new ArrayList<>();
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_periksa_lab", periksaLab.getIdPeriksaLab());
                    hsCriteria.put("flag", "Y");

                    try {
                        periksaLabDetailEntity = periksaLabDetailDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[TeamDokterBoImpl.saveEdit] Error when getById periksa lab detail", e);
                        throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit periksa lab detail" + e.getMessage());
                    }

                    if (periksaLabDetailEntity != null) {
                        for (ItSimrsPeriksaLabDetailEntity entity : periksaLabDetailEntity) {
                            entity.setFlag("N");
                            try {
                                periksaLabDetailDao.updateAndSave(entity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab ", e.getCause());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getCause());
                            }
                        }

                        if (periksaLab.getIdPeriksaLab() != null && !"".equalsIgnoreCase(periksaLab.getIdPeriksaLab()) && !labDetailIds.isEmpty() && labDetailIds.size() > 0 && labDetailIds != null) {
                            for (String labDetailId : labDetailIds) {
                                ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();

                                String id = getNextDetailLapId();
                                detailEntity.setIdPeriksaLabDetail("DPL" + id);
                                detailEntity.setIdPeriksaLab(periksaLab.getIdPeriksaLab());
                                detailEntity.setIdLabDetail(labDetailId);

                                // get data from master lab detail
                                ImSimrsLabDetailEntity labDetailEntity = new ImSimrsLabDetailEntity();
                                if (labDetailId != null && !"".equalsIgnoreCase(labDetailId)) {
                                    labDetailEntity = getDataMasterLabDetailByIdLab(labDetailId);
                                    if (labDetailEntity != null) {
                                        detailEntity.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                                        detailEntity.setKeteranganAcuan(labDetailEntity.getKetentuanAcuan());
                                        detailEntity.setSatuan(labDetailEntity.getSatuan());
                                    }
                                }

                                detailEntity.setFlag("Y");
                                detailEntity.setAction("U");

                                // from periksaLab
                                detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                                detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                                detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                                detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                                try {
                                    periksaLabDetailDao.addAndSave(detailEntity);
                                } catch (HibernateException e) {
                                    logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                                    throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                                }
                            }
                        }

                    }
                    logger.info("[PeriksaLabBoImpl.saveEdit] END <<<<<<<<< ");
                }
            }
        }
    }

    @Override
    public void saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] START >>>>>>>>> ");

        if (periksaLab != null) {
            ItSimrsPeriksaLabEntity entity = new ItSimrsPeriksaLabEntity();

            String id = getNextPeriksaLabId();
            entity.setIdPeriksaLab("PRL" + id);
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

            try {
                periksaLabDao.addAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
            }

            // get lab for kategori lab
            // save to Radiology transaction if Jenis Lab is Radiology
            Lab labEntity = getDatamasterLabById(periksaLab.getIdLab());
            if (labEntity != null) {
                if ("Radiologi".equalsIgnoreCase(labEntity.getKategoriLabName())) {
                    if (labDetailIds != null && labDetailIds.size() > 0) {

                        for (String labDetail : labDetailIds) {
                            ItSimrsPeriksaRadiologiEntity radiologiEntity = new ItSimrsPeriksaRadiologiEntity();

                            id = getNextLabRadiologyId();
                            radiologiEntity.setIdPeriksaRadiologi("RLG" + id);
                            radiologiEntity.setIdDetailCheckup(entity.getIdDetailCheckup());
                            radiologiEntity.setIdLab(labEntity.getIdKategoriLab());
                            radiologiEntity.setStatusPeriksa("0");
                            radiologiEntity.setIdPeriksaLab(entity.getIdPeriksaLab());
                            radiologiEntity.setIdLabDetail(labDetail);

                            // get data from master lab detail
                            ImSimrsLabDetailEntity labDetailEntity = new ImSimrsLabDetailEntity();

                            try {
                                labDetailEntity = labDetailDao.getById("idLabDetail", labDetail);
                            } catch (HibernateException e) {

                            }

                            if (labDetailEntity != null) {
                                radiologiEntity.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                            }

                            radiologiEntity.setFlag("Y");
                            radiologiEntity.setAction("C");
                            radiologiEntity.setCreatedDate(entity.getCreatedDate());
                            radiologiEntity.setCreatedWho(entity.getCreatedWho());
                            radiologiEntity.setLastUpdate(entity.getLastUpdate());
                            radiologiEntity.setLastUpdateWho(entity.getLastUpdateWho());

                            try {
                                periksaRadiologiDao.addAndSave(radiologiEntity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa radiology " + e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa radiology " + e.getMessage());
                            }
                        }
                    }
                }
                // selain radiologi akan menyimpan lab parameters
                else {
                    // save parameter lab to lab detail id jenis lab is laboratorium
                    if (entity.getIdPeriksaLab() != null && !"".equalsIgnoreCase(entity.getIdPeriksaLab()) && !labDetailIds.isEmpty() && labDetailIds.size() > 0 && labDetailIds != null) {
                        for (String labDetailId : labDetailIds) {
                            ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();

                            id = getNextDetailLapId();
                            detailEntity.setIdPeriksaLabDetail("DPL" + id);
                            detailEntity.setIdPeriksaLab(entity.getIdPeriksaLab());
                            detailEntity.setIdLabDetail(labDetailId);

                            // get data from master lab detail
                            ImSimrsLabDetailEntity labDetailEntity = new ImSimrsLabDetailEntity();
                            if (labDetailId != null && !"".equalsIgnoreCase(labDetailId)) {
                                labDetailEntity = getDataMasterLabDetailByIdLab(labDetailId);
                                if (labDetailEntity != null) {
                                    detailEntity.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                                    detailEntity.setKeteranganAcuan(labDetailEntity.getKetentuanAcuan());
                                    detailEntity.setSatuan(labDetailEntity.getSatuan());
                                }
                            }

                            detailEntity.setFlag("Y");
                            detailEntity.setAction("C");

                            // from periksaLab
                            detailEntity.setCreatedDate(periksaLab.getCreatedDate());
                            detailEntity.setCreatedWho(periksaLab.getCreatedWho());
                            detailEntity.setLastUpdate(periksaLab.getLastUpdate());
                            detailEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());

                            try {
                                periksaLabDetailDao.addAndSave(detailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }

        logger.info("[PeriksaLabBoImpl.saveAddWithParameter] END <<<<<<<<< ");
    }

    @Override
    public void saveUpdateHasilLab(PeriksaLabDetail bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.saveUpdateHasilLab] START >>>>>>>>> ");

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
                    } catch (HibernateException e) {
                        logger.error("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When updating data periksa lab detail ", e);
                        throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPerikasDetailLab] ERROR When updating data periksa lab detail " + e.getCause());
                    }
                }
            }
        }

        logger.info("[PeriksaLabBoImpl.saveUpdateHasilLab] END <<<<<<<<< ");
    }

    @Override
    public List<PeriksaLabDetail> getListParameterLab(PeriksaLabDetail bean) throws GeneralBOException {
        logger.info("[PeriksaLabBoImpl.getListParameterLab] START >>>>>>>>> ");

        if (bean != null) {

            List<PeriksaLabDetail> periksaLabDetailList = new ArrayList<>();

            PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();
            periksaLabDetail.setIdPeriksaLab(bean.getIdPeriksaLab());

            if ("Radiologi".equalsIgnoreCase(bean.getKategoriName())) {

                List<ItSimrsPeriksaRadiologiEntity> listEntity = new ArrayList<>();

                try {
                    listEntity = getListEntityPerikasaRadiologi(periksaLabDetail);
                } catch (HibernateException e) {
                    logger.error("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail ", e);
                    throw new GeneralBOException("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail " + e.getCause());
                }

                if (!listEntity.isEmpty()) {
                    PeriksaLabDetail periksaLabDetail1;
                    for (ItSimrsPeriksaRadiologiEntity entity : listEntity) {

                        periksaLabDetail1 = new PeriksaLabDetail();
                        periksaLabDetail1.setIdPeriksaLabDetail(entity.getIdPeriksaRadiologi());
                        periksaLabDetail1.setIdPeriksaLab(entity.getIdPeriksaLab());
                        periksaLabDetail1.setIdLabDetail(entity.getIdLabDetail());
                        periksaLabDetail1.setNamaDetailPeriksa(entity.getNamaDetailPeriksa());
                        periksaLabDetail1.setKeteranganPeriksa(entity.getKesimpulan());
                        periksaLabDetail1.setFlag(entity.getFlag());
                        periksaLabDetail1.setAction(entity.getAction());
                        periksaLabDetail1.setCreatedDate(entity.getCreatedDate());
                        periksaLabDetail1.setCreatedWho(entity.getCreatedWho());
                        periksaLabDetail1.setLastUpdate(entity.getLastUpdate());
                        periksaLabDetail1.setLastUpdateWho(entity.getLastUpdateWho());
                        periksaLabDetailList.add(periksaLabDetail1);
                    }
                }

            } else {
                List<ItSimrsPeriksaLabDetailEntity> listEntity = null;

                try {
                    listEntity = getListEntityPerikasDetailLab(periksaLabDetail);
                } catch (HibernateException e) {
                    logger.error("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail ", e);
                    throw new GeneralBOException("[PeriksaLabBoImpl.getListParameterLab] ERROR When search data periksa lab detail " + e.getCause());
                }

                if (!listEntity.isEmpty()) {
                    PeriksaLabDetail periksaLabDetail1;
                    for (ItSimrsPeriksaLabDetailEntity entity : listEntity) {

                        periksaLabDetail1 = new PeriksaLabDetail();

                        periksaLabDetail1.setIdPeriksaLabDetail(entity.getIdPeriksaLabDetail());
                        periksaLabDetail1.setIdPeriksaLab(entity.getIdPeriksaLab());
                        periksaLabDetail1.setIdLabDetail(entity.getIdLabDetail());
                        periksaLabDetail1.setNamaDetailPeriksa(entity.getNamaDetailPeriksa());
                        periksaLabDetail1.setSatuan(entity.getSatuan());
                        periksaLabDetail1.setHasil(entity.getHasil());
                        periksaLabDetail1.setKeteranganAcuan(entity.getKeteranganAcuan());
                        periksaLabDetail1.setKeteranganPeriksa(entity.getKeteranganPeriksa());
                        periksaLabDetail1.setFlag(entity.getFlag());
                        periksaLabDetail1.setAction(entity.getAction());
                        periksaLabDetail1.setCreatedDate(entity.getCreatedDate());
                        periksaLabDetail1.setCreatedWho(entity.getCreatedWho());
                        periksaLabDetail1.setLastUpdate(entity.getLastUpdate());
                        periksaLabDetail1.setLastUpdateWho(entity.getLastUpdateWho());
                        periksaLabDetailList.add(periksaLabDetail1);
                    }
                }
            }

            return periksaLabDetailList;
        }
        logger.info("[PeriksaLabBoImpl.getListParameterLab] END >>>>>>>>> ");
        return null;
    }

    @Override
    public CheckResponse saveDokterLab(PeriksaLab bean) throws GeneralBOException {

        logger.info("[PeriksaLabBoImpl.saveDokterLab] start <<<<<<<<<");

        CheckResponse response = new CheckResponse();

        if (bean != null) {

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
    public PeriksaLab getTarifTotalPemeriksaan(String idLab, String idPeriksaan) throws GeneralBOException {
        PeriksaLab periksaLab = new PeriksaLab();
        try {
            periksaLab = periksaLabDao.getTotalTarif(idLab, idPeriksaan);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return periksaLab;
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
}
