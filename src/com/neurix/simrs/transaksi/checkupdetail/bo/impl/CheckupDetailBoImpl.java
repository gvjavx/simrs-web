package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.dao.TempatTidurDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.dao.AntrianOnlineDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UploadPendukungPemeriksaanDao;
import com.neurix.simrs.transaksi.checkupdetail.model.*;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderGiziDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketPasienDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class CheckupDetailBoImpl extends CheckupModuls implements CheckupDetailBo {

    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(CheckupDetailBoImpl.class);

    private CheckupDetailDao checkupDetailDao;
    private DokterTeamDao dokterTeamDao;
    private HeaderCheckupDao headerCheckupDao;
    private TindakanRawatDao tindakanRawatDao;
    private PeriksaLabDao periksaLabDao;
    private PermintaanResepDao permintaanResepDao;
    private OrderGiziDao orderGiziDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private TindakanDao tindakanDao;
    private AntrianOnlineDao antrianOnlineDao;
    private UangMukaDao uangMukaDao;
    private DokterDao dokterDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private PaketPasienDao paketPasienDao;
    private TempatTidurDao tempatTidurDao;
    private PelayananDao pelayananDao;
    private PasienDao pasienDao;
    private UploadPendukungPemeriksaanDao uploadPendukungPemeriksaanDao;

    @Override
    public List<HeaderDetailCheckup> getByCriteria(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getByCriteria] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        if (bean != null) {
            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = getListEntityByCriteria(bean);
            if (!detailCheckupEntityList.isEmpty()) {
                results = setToDetailCheckupTemplate(detailCheckupEntityList);
            }
        }

        logger.info("[CheckupDetailBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    @Override
    public List<HeaderDetailCheckup> getSearchRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getSearchRawatJalan] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();
        if (bean != null) {
            try {
                results = checkupDetailDao.getSearchRawatJalan(bean);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailBoImpl.getSearchRawatJalan] Error when get data detail checkup ", e);
                throw new GeneralBOException("Error when get data detail checkup" + e.getMessage());
            }
        }
        logger.info("[CheckupDetailBoImpl.getSearchRawatJalan] End <<<<<<<<");
        return results;
    }

    @Override
    public List<ItSimrsHeaderDetailCheckupEntity> getListEntityByCriteria(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        try {
            entityList = checkupDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ", e);
            throw new GeneralBOException("Error when get data detail checkup entity " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    @Override
    public HeaderDetailCheckup getBiayaTindakan(String idDetailCheckup) throws GeneralBOException {
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                headerDetailCheckup = checkupDetailDao.getBiayaTindakan(idDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search tindaksan " + e.getMessage());
            }
        }
        return headerDetailCheckup;
    }

    @Override
    public CheckResponse saveUpdateDataAsuransi(HeaderCheckup bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {

            ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();

            try {

                entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());

                if (entity != null) {

                    entity.setNoRujukan(bean.getNoRujukan());
                    entity.setTglRujukan(java.sql.Date.valueOf(bean.getTglRujukan()));
                    entity.setUrlDocRujuk(bean.getUrlDocRujuk());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        checkupDetailDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Found Error " + e.getMessage());
                        logger.error("Found Error " + e.getMessage());
                    }
                }
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("Found Error " + e.getMessage());
            }
        }
        return response;
    }

    @Override
    public PermintaanResep getDataDokter(String id) throws GeneralBOException {
        PermintaanResep resep = new PermintaanResep();
        try {
            resep = checkupDetailDao.getDataDokterWithResep(id);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return resep;
    }

    @Override
    public HeaderDetailCheckup getCoverBiayaAsuransi(String idDetailCheckup) throws GeneralBOException {
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        try {
            detailCheckup = checkupDetailDao.getCoverBiayaAsuransi(idDetailCheckup);
        } catch (HibernateException e) {
            logger.error("found Error " + e.getMessage());
        }
        return detailCheckup;
    }

    public boolean editVideoRm(String idDetailCheckup, String path) {
        logger.info("[CheckupDetailBoImpl.editVideoRm] Start <<<<<<<<");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = null;
        boolean isSuccess = false;

        try {
            detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", idDetailCheckup, "Y");
        } catch (GeneralBOException e) {
            logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
        }

        if (detailCheckupEntity != null) {
            detailCheckupEntity.setVideoRm(path);
            try {
                checkupDetailDao.updateAndSave(detailCheckupEntity);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
            }
        }

        logger.info("[CheckupDetailBoImpl.editVideoRm] End <<<<<<<<");
        return isSuccess;
    }

    public boolean editFlagCall(String idDetailCheckup, String flagCall) {
        logger.info("[CheckupDetailBoImpl.editVideoRm] Start <<<<<<<<");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = null;
        boolean isSuccess = false;

        try {
            detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", idDetailCheckup, "Y");
        } catch (GeneralBOException e) {
            logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
        }

        if (detailCheckupEntity != null) {
            detailCheckupEntity.setFlagCall(flagCall);
            try {
                checkupDetailDao.updateAndSave(detailCheckupEntity);
                isSuccess = true;
            } catch (GeneralBOException e) {
                logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
            }
        }

        logger.info("[CheckupDetailBoImpl.editVideoRm] End <<<<<<<<");
        return isSuccess;
    }

    @Override
    public ItSimrsHeaderDetailCheckupEntity getEntityDetailCheckupByIdTransaksi(String id) throws GeneralBOException {
        return checkupDetailDao.getById("idTransaksiOnline", id);
    }

    @Override
    public List<HeaderDetailCheckup> getListVerifTransaksi(HeaderDetailCheckup detailCheckup) throws GeneralBOException {
        return checkupDetailDao.getListVerifTransaksi(detailCheckup);
    }

    @Override
    public CrudResponse updateDetailCheckup(List<HeaderDetailCheckup> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            for (HeaderDetailCheckup bean: list){
                try {
                    ItSimrsHeaderChekupEntity chekupEntity = new ItSimrsHeaderChekupEntity();
                    ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
                    entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                    if (entity != null) {
                        if (bean.getInvoice() != null) {
                            entity.setInvoice(bean.getInvoice());
                        }
                        if (bean.getFlagCloseTraksaksi() != null) {
                            entity.setFlagCloseTraksaksi(bean.getFlagCloseTraksaksi());
                        }
                        if (bean.getFlagTppri() != null) {
                            entity.setFlagTppri(bean.getFlagTppri());
                        }
                        if (!"asuransi".equalsIgnoreCase(entity.getIdJenisPeriksaPasien())) {
                            entity.setFlagCover("Y");
                        }
                        if ("Y".equalsIgnoreCase(bean.getJustLab())) {
                            entity.setStatusPeriksa("3");
                            entity.setKeteranganSelesai("Selesai");
                            entity.setTindakLanjut("selesai");
                        }

                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());
                        entity.setAction("U");

                        try {
                            checkupDetailDao.updateAndSave(entity);
                            response.setStatus("success");
                            response.setMsg("Berhasil");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("IError when update invoice, " + e.getMessage());
                        }

                        if ("Y".equalsIgnoreCase(bean.getJustLab())) {
                            chekupEntity = headerCheckupDao.getById("noCheckup", entity.getNoCheckup());
                            if (chekupEntity != null) {
                                chekupEntity.setLastUpdate(bean.getLastUpdate());
                                chekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                chekupEntity.setTglKeluar(bean.getLastUpdate());
                                try {
                                    headerCheckupDao.updateAndSave(chekupEntity);
                                    response.setStatus("success");
                                    response.setMsg("Berhasil");
                                } catch (HibernateException e) {
                                    response.setStatus("error");
                                    response.setMsg("IError when update invoice, " + e.getMessage());
                                }
                            }
                        }

                    } else {
                        response.setStatus("error");
                        response.setMsg("ID Detail Checkup Tidak ditemukan");
                    }
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error when update invoice, " + e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse updatePindahRuangan(String idRawatInapNew, String idRawatInapPindah) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsRawatInapEntity rawatInapEntityNew = new ItSimrsRawatInapEntity();
        ItSimrsRawatInapEntity rawatInapEntityPindah = new ItSimrsRawatInapEntity();
        try {
            rawatInapEntityNew = rawatInapDao.getById("idRawatInap", idRawatInapNew);
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        if (rawatInapEntityNew != null) {
            rawatInapEntityNew.setStatus("1");
            rawatInapEntityNew.setKeterangan("sudah_pindah");
            rawatInapEntityNew.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            rawatInapEntityNew.setLastUpdateWho(CommonUtil.userLogin());
            try {
                rawatInapDao.updateAndSave(rawatInapEntityNew);
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }

            try {
                rawatInapEntityPindah = rawatInapDao.getById("idRawatInap", idRawatInapPindah);
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
            if (rawatInapEntityPindah != null) {
                rawatInapEntityPindah.setStatus("3");
                rawatInapEntityPindah.setTglKeluar(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                rawatInapEntityPindah.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                rawatInapEntityPindah.setLastUpdateWho(CommonUtil.userLogin());
                try {
                    rawatInapDao.updateAndSave(rawatInapEntityPindah);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }

                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(rawatInapEntityPindah.getIdRuangan());
                List<MtSimrsRuanganEntity> ruanganEntities = getListEntityRuangan(ruangan);

                if (ruanganEntities.size() > 0) {
                    MtSimrsRuanganEntity ruanganEntity = ruanganEntities.get(0);
                    if (ruanganEntity != null) {
                        ruanganEntity.setStatusRuangan("Y");
                        ruanganEntity.setAction("U");
                        ruanganEntity.setSisaKuota(ruanganEntity.getSisaKuota() + 1);
                        ruanganEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        ruanganEntity.setLastUpdateWho(CommonUtil.userLogin());
                        try {
                            ruanganDao.updateAndSave(ruanganEntity);
                            response.setStatus("success");
                            response.setMsg("berhasil");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Error" + e.getMessage());
                        }
                    }
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("ID rawat inap tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public List<HeaderDetailCheckup> getIDDetailCheckup(String noCheckup, String status, String jenisPasien) throws GeneralBOException {
        return checkupDetailDao.getIDDetailCheckup(noCheckup, status, jenisPasien);
    }

    @Override
    public CrudResponse setNoRujukan(HeaderDetailCheckup bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
        if(bean.getNoRujukanInternal() != null && !"".equalsIgnoreCase(bean.getNoRujukanInternal())){
            try {
                entity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("Error when search data detail checkup, "+e.getMessage());
            }
            if(entity != null){
                entity.setNoRujukanInternal(bean.getNoRujukanInternal());
                entity.setAction("U");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setPoliRujukanInternal(bean.getPoliRujukanInternal());
                try {
                    checkupDetailDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Error when search data detail checkup, "+e.getMessage());
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("No rujukan tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public CrudResponse saveUploadPemeriksaan(ItSimrsUploadPendukungPemeriksaanEntity bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            List<ItSimrsUploadPendukungPemeriksaanEntity> pemeriksaanEntityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            hsCriteria.put("url_img", bean.getUrlImg());
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            try {
                pemeriksaanEntityList = uploadPendukungPemeriksaanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(pemeriksaanEntityList.size() > 0){
                for (ItSimrsUploadPendukungPemeriksaanEntity pendukungPemeriksaanEntity: pemeriksaanEntityList){
                    try {
                        uploadPendukungPemeriksaanDao.deleteAndSave(pendukungPemeriksaanEntity);
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                        throw new GeneralBOException("Error when delete data");
                    }
                }
            }

            bean.setIdUpload(uploadPendukungPemeriksaanDao.getNextId());
            try {
                uploadPendukungPemeriksaanDao.addAndSave(bean);
                response.setMsg(bean.getIdUpload());
            }catch (HibernateException e){
                logger.error(e.getMessage());
                throw new GeneralBOException("Error when save data");
            }
        }
        return response;
    }

    @Override
    public void deleteUploadPemeriksaan(String id) throws GeneralBOException {
        if(id != null){
            ItSimrsUploadPendukungPemeriksaanEntity pendukungPemeriksaanEntity = uploadPendukungPemeriksaanDao.getById("idUpload", id);
            if(pendukungPemeriksaanEntity != null){
                try {
                    uploadPendukungPemeriksaanDao.deleteAndSave(pendukungPemeriksaanEntity);
                    File myFile = new File(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PENDUKUNG_PEMERIKSAAN+pendukungPemeriksaanEntity.getUrlImg());
                    if (!myFile.isDirectory()){
                        myFile.delete();
                    }
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    throw new GeneralBOException("Error when delete data");
                }
            }
        }
    }

    @Override
    public List<UploadPendukungPemeriksaan> getListUploadPemeriksaan(UploadPendukungPemeriksaan bean) throws GeneralBOException {
        List<UploadPendukungPemeriksaan> uploadPendukungPemeriksaanList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsUploadPendukungPemeriksaanEntity> pendukungPemeriksaanEntityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdUpload() != null){
                hsCriteria.put("id_upload", bean.getIdUpload());
            }
            if(bean.getIdDetailCheckup() != null){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            try {
                pendukungPemeriksaanEntityList = uploadPendukungPemeriksaanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(pendukungPemeriksaanEntityList.size() > 0){
                for (ItSimrsUploadPendukungPemeriksaanEntity entity: pendukungPemeriksaanEntityList){
                    UploadPendukungPemeriksaan pendukungPemeriksaanEntity = new UploadPendukungPemeriksaan();
                    pendukungPemeriksaanEntity.setIdUpload(entity.getIdUpload());
                    pendukungPemeriksaanEntity.setIdDetailCheckup(entity.getIdDetailCheckup());
                    pendukungPemeriksaanEntity.setKeterangan(entity.getKeterangan());
                    pendukungPemeriksaanEntity.setUrlImg(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_PENDUKUNG_PEMERIKSAAN+entity.getUrlImg());
                    pendukungPemeriksaanEntity.setFlag(entity.getFlag());
                    pendukungPemeriksaanEntity.setAction(entity.getAction());
                    pendukungPemeriksaanEntity.setCreatedWho(entity.getCreatedWho());
                    pendukungPemeriksaanEntity.setCreatedDate(entity.getCreatedDate());
                    pendukungPemeriksaanEntity.setLastUpdateWho(entity.getLastUpdateWho());
                    pendukungPemeriksaanEntity.setLastUpdate(entity.getLastUpdate());
                    uploadPendukungPemeriksaanList.add(pendukungPemeriksaanEntity);
                }
            }
        }
        return uploadPendukungPemeriksaanList;
    }

    @Override
    public HeaderDetailCheckup getTotalBiayaTindakanBpjs(String idDetailCheckup) throws GeneralBOException {
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        try {
            detailCheckup = checkupDetailDao.getTotalBiayaTindakanBpjs(idDetailCheckup);
        } catch (HibernateException e) {
            logger.error("found Error " + e.getMessage());
        }
        return detailCheckup;
    }

    protected List<HeaderDetailCheckup> setToDetailCheckupTemplate(List<ItSimrsHeaderDetailCheckupEntity> entityList) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        HeaderDetailCheckup detailCheckup;
        for (ItSimrsHeaderDetailCheckupEntity entity : entityList) {
            detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(entity.getIdDetailCheckup());
            detailCheckup.setNoCheckup(entity.getNoCheckup());
            detailCheckup.setIdPelayanan(entity.getIdPelayanan());
            detailCheckup.setStatusPeriksa(entity.getStatusPeriksa());
            detailCheckup.setStatusBayar(entity.getStatusBayar());
            detailCheckup.setTotalBiaya(entity.getTotalBiaya());
            detailCheckup.setKeteranganSelesai(entity.getKeteranganSelesai());
            detailCheckup.setJenisLab(entity.getJenisLab());
            detailCheckup.setBranchId(entity.getBranchId());
            detailCheckup.setFlag(entity.getFlag());
            detailCheckup.setAction(entity.getAction());
            detailCheckup.setCreatedDate(entity.getCreatedDate());
            detailCheckup.setCreatedWho(entity.getCreatedWho());
            detailCheckup.setLastUpdate(entity.getLastUpdate());
            detailCheckup.setLastUpdateWho(entity.getLastUpdateWho());
            detailCheckup.setTarifBpjs(entity.getTarifBpjs());
            detailCheckup.setNoSep(entity.getNoSep());
            detailCheckup.setInvoice(entity.getInvoice());
            detailCheckup.setNoJurnal(entity.getNoJurnal());
            detailCheckup.setKodeCbg(entity.getKodeCbg());
            detailCheckup.setNamaPerujuk(entity.getKetRujukan());
            detailCheckup.setNoRujukan(entity.getNoRujukan());
            detailCheckup.setNoPpk(entity.getNoPpkRujukan());
            detailCheckup.setTglRujukan(entity.getTglRujukan() != null ? entity.getTglRujukan().toString() : "");
            detailCheckup.setIdKelas(entity.getKelasPasien());
            detailCheckup.setIdPelayananBpjs(entity.getIdPelayananBpjs());
            detailCheckup.setIdJenisPeriksaPasien(entity.getIdJenisPeriksaPasien());
            detailCheckup.setNoKartuAsuransi(entity.getNoKartuAsuransi());
            detailCheckup.setIdAsuransi(entity.getIdAsuransi());
            detailCheckup.setCoverBiaya(entity.getCoverBiaya());
            detailCheckup.setMetodePembayaran(entity.getMetodePembayaran());
            detailCheckup.setPerujuk(entity.getRujuk());
            detailCheckup.setSuratRujukan(entity.getUrlDocRujuk());
            detailCheckup.setDibayarPasien(entity.getDibayarPasien() != null ? CommonUtil.numbericFormat(entity.getDibayarPasien(), "###,###") : "0");

            if (detailCheckup.getStatusPeriksa() != null && !"".equalsIgnoreCase(detailCheckup.getStatusPeriksa())) {
                StatusPasien statusPasien = new StatusPasien();
                statusPasien.setIdStatusPasien(detailCheckup.getStatusPeriksa());
                detailCheckup.setStatusPeriksa(getListEntityStatusPasien(statusPasien).get(0).getKeterangan());
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
            List<ItSimrsRawatInapEntity> rawatInapEntitys = getListEntityRawatInap(rawatInap);

            ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
            if (!rawatInapEntitys.isEmpty()) {
                rawatInapEntity = rawatInapEntitys.get(0);
            }

            if (rawatInapEntity != null) {
                detailCheckup.setNoRuangan(rawatInapEntity.getNoRuangan());
                detailCheckup.setIdRuangan(rawatInapEntity.getIdRuangan());
                detailCheckup.setNamaRuangan(rawatInapEntity.getNamaRangan());
            }

            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(detailCheckup.getIdPelayanan());
            Pelayanan resultPelayanan = pelayananDao.getObjectPelayanan(pelayanan);
            if(resultPelayanan != null){
                detailCheckup.setNamaPelayanan(resultPelayanan.getNamaPelayanan());
                detailCheckup.setTipePelayanan(resultPelayanan.getTipePelayanan());
            }
            results.add(detailCheckup);
        }

        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] End <<<<<<<<");
        return results;
    }

    @Override
    public CrudResponse saveEdit(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();

        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = null;
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());

        detailCheckupEntityList = getListEntityByCriteria(detailCheckup);
        if (!detailCheckupEntityList.isEmpty()) {
            ItSimrsHeaderDetailCheckupEntity entity = detailCheckupEntityList.get(0);
            entity.setStatusPeriksa(bean.getStatusPeriksa() == null ? entity.getStatusBayar() : bean.getStatusPeriksa());
            entity.setKeteranganSelesai(bean.getKeteranganSelesai() == null ? entity.getKeteranganSelesai() : bean.getKeteranganSelesai());
            entity.setKeteranganCekupUlang(bean.getKeteranganCekupUlang() != null && !"".equalsIgnoreCase(bean.getKeteranganCekupUlang()) ? bean.getKeteranganCekupUlang() : null);
            entity.setTglCekup(bean.getTglCekup() == null ? entity.getTglCekup() : bean.getTglCekup());
            entity.setJenisLab(bean.getJenisLab() == null ? entity.getJenisLab() : bean.getJenisLab());
            entity.setFlag(bean.getFlag() == null ? entity.getFlag() : bean.getFlag());
            entity.setAction(bean.getAction() == null ? entity.getAction() : bean.getAction());
            entity.setLastUpdate(bean.getLastUpdate() == null ? entity.getLastUpdate() : bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho() == null ? entity.getLastUpdateWho() : bean.getLastUpdateWho());
            entity.setCaraPasienPulang(bean.getCaraPasienPulang() != null && !"".equalsIgnoreCase(bean.getCaraPasienPulang()) ? bean.getCaraPasienPulang() : null);
            entity.setPendamping(bean.getPendamping() != null && !"".equalsIgnoreCase(bean.getPendamping()) ? bean.getPendamping() : null);
            entity.setTempatTujuan(bean.getTempatTujuan() != null && !"".equalsIgnoreCase(bean.getTempatTujuan()) ? bean.getTempatTujuan() : null);
            entity.setInvoice(bean.getInvoice() == null ? entity.getInvoice() : bean.getInvoice());
            entity.setUrlTtd(bean.getUrlTtd() == null ? entity.getUrlTtd() : bean.getUrlTtd());
            entity.setNoSep(bean.getNoSep() == null ? entity.getNoSep() : bean.getNoSep());
            entity.setTarifBpjs(bean.getTarifBpjs() == null ? entity.getTarifBpjs() : bean.getTarifBpjs());
            entity.setKodeCbg(bean.getKodeCbg() == null ? entity.getKodeCbg() : bean.getKodeCbg());
            entity.setIdPelayananBpjs(bean.getIdPelayananBpjs() == null ? entity.getIdPelayananBpjs() : bean.getIdPelayananBpjs());
            entity.setNoCheckupUlang(bean.getNoCheckupUlang());
            entity.setIsOrderLab(bean.getIsOrderLab());
            entity.setTindakLanjut(bean.getTindakLanjut());
            if ("".equalsIgnoreCase(bean.getCatatan()) && bean.getCatatan() != null) {
                entity.setCatatan(bean.getCatatan());
            }
            if(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan())){
                entity.setNoRujukan(bean.getNoRujukan());
            }
            if(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan())){
                entity.setTglRujukan(java.sql.Date.valueOf(bean.getTglRujukan()));
            }
            if(bean.getSuratRujukan() != null && !"".equalsIgnoreCase(bean.getSuratRujukan())){
                entity.setUrlDocRujuk(bean.getSuratRujukan());
            }
            entity.setRsRujukan(bean.getRsRujukan());

            try {
                checkupDetailDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error when update detail checkup " + e.getMessage());
                logger.error("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup ", e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup " + e.getMessage());
            }

            // update tgl kluar if selesai
            if ("selesai".equalsIgnoreCase(bean.getTindakLanjut()) ||
                    "rujuk_rs_lain".equalsIgnoreCase(bean.getTindakLanjut()) ||
                    "kontrol_ulang".equalsIgnoreCase(bean.getTindakLanjut()) ||
                    "lanjut_biaya".equalsIgnoreCase(bean.getTindakLanjut()) ||
                    "rujuk_internal".equalsIgnoreCase(bean.getTindakLanjut())) {

                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(entity.getNoCheckup());
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = getListEntityCheckup(headerCheckup);
                if (headerChekupEntities != null && headerChekupEntities.size() > 0) {
                    ItSimrsHeaderChekupEntity headerChekupEntity = headerChekupEntities.get(0);
                    headerChekupEntity.setLastUpdate(bean.getLastUpdate());
                    headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerChekupEntity.setAction("U");
                    headerChekupEntity.setTglKeluar(bean.getLastUpdate());

                    // section update flag selesai paket pasien perusahaan, 2020-06-05 Sigit
                    if ("paket_perusahaan".equalsIgnoreCase(entity.getIdJenisPeriksaPasien())) {
                        Map hsCriteria = new HashMap();
                        hsCriteria.put("id_paket", entity.getIdPaket());
                        hsCriteria.put("id_pasien", headerChekupEntity.getIdPasien());
                        hsCriteria.put("flag_selesai_null", "Y");

                        List<ItSimrsPaketPasienEntity> paketPasienEntities = paketPasienDao.getByCriteria(hsCriteria);
                        if (paketPasienEntities.size() > 0) {
                            ItSimrsPaketPasienEntity paketPasienEntity = paketPasienEntities.get(0);
                            paketPasienEntity.setFlagSelesai("Y");

                            try {
                                paketPasienDao.updateAndSave(paketPasienEntity);
                            } catch (HibernateException e) {
                                response.setStatus("error");
                                response.setMsg("Error when update paket perusahaan status " + e.getMessage());
                                logger.error("[CheckupDetailBoImpl.saveEdit] Error update paket perusahaan status ", e);
                                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error update paket perusahaan status " + e.getMessage());
                            }
                        }
                    }
                    // section END

                    try {
                        headerCheckupDao.updateAndSave(headerChekupEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Error when update checkup " + e.getMessage());
                        logger.error("[CheckupDetailBoImpl.saveEdit] Error when update checkup ", e);
                        throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update checkup " + e.getMessage());
                    }
                }
            }

            //sodiq, update flag meninggal, minggu malem 23.35 2021,03,28
            if("Y".equalsIgnoreCase(bean.getIsMeninggal())){
                ItSimrsHeaderChekupEntity chekupEntity = headerCheckupDao.getById("noCheckup", entity.getNoCheckup());
                if(chekupEntity != null){
                    ImSimrsPasienEntity pasienEntity = pasienDao.getById("idPasien", chekupEntity.getIdPasien());
                    if(pasienEntity != null){
                        pasienEntity.setLastUpdate(bean.getLastUpdate());
                        pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        pasienEntity.setAction("U");
                        pasienEntity.setFlagMeninggal("Y");
                        pasienEntity.setTanggalMeninggal(bean.getLastUpdate());
                        try {
                            pasienDao.updateAndSave(pasienEntity);
                        }catch (HibernateException e){
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
        }

        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(bean.getIdDetailCheckup());
        rawatInap.setFlag("Y");
        List<ItSimrsRawatInapEntity> rawatInapEntities = getListEntityRawatInap(rawatInap);

        // if rawat inap
        if (!rawatInapEntities.isEmpty() && rawatInapEntities.size() > 0) {
            for (ItSimrsRawatInapEntity rawatInapEntity : rawatInapEntities) {
                rawatInapEntity.setFlag("N");
                rawatInapEntity.setAction("U");
                rawatInapEntity.setTglKeluar(new Timestamp(System.currentTimeMillis()));
                rawatInapEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                rawatInapEntity.setLastUpdateWho(CommonUtil.userLogin());
                try {
                    rawatInapDao.updateAndSave(rawatInapEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error when Update data transaksi inap " + e.getMessage());
                    logger.error("[CheckupDetailBoImpl.saveEdit] Error when Update data transaksi inap ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when Update data transaksi inap " + e.getMessage());
                }

                if (rawatInapEntity.getIdRuangan() != null && !"".equalsIgnoreCase(rawatInapEntity.getIdRuangan())) {
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdRuangan(rawatInapEntity.getIdRuangan());
//                    ruangan.setStatusRuangan("N");
                    List<MtSimrsRuanganEntity> ruanganEntities = getListEntityRuangan(ruangan);

                    if (!ruanganEntities.isEmpty() && ruanganEntities.size() > 0) {
                        for (MtSimrsRuanganEntity ruanganEntity : ruanganEntities) {
                            ruanganEntity.setStatusRuangan("Y");
                            ruanganEntity.setAction("U");
                            ruanganEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            ruanganEntity.setLastUpdateWho(CommonUtil.userLogin());

                            try {
                                ruanganDao.updateAndSave(ruanganEntity);
                                response.setStatus("success");
                                response.setMsg("Berhasil");
                            } catch (HibernateException e) {
                                response.setStatus("error");
                                response.setMsg("Error when Update status master ruangan " + e.getMessage());
                                logger.error("[CheckupDetailBoImpl.saveEdit] Error when Update status master ruangan ", e);
                                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when Update status master ruangan " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    private List<ItSimrsHeaderChekupEntity> getListEntityCheckup(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        List<ItSimrsHeaderChekupEntity> entities = null;

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());

        try {
            entities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.saveEdit] ERROR when get by criteria" + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] ERROR when get by criteria" + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
        return entities;
    }

    @Override
    public CrudResponse saveAdd(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();

        // create new detail
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
        detailCheckupEntity.setIdDetailCheckup("DCM" + getNextDetailCheckupId());
        detailCheckupEntity.setNoCheckup(bean.getNoCheckup());
        detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
        detailCheckupEntity.setFlag("Y");
        detailCheckupEntity.setAction("C");
        detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
        detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
        detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
        detailCheckupEntity.setNoSep(bean.getNoSep());
        detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
        detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
        detailCheckupEntity.setBranchId(bean.getBranchId());
        detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
        detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi());
        detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya());
        detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi());
        detailCheckupEntity.setTindakLanjut(bean.getTindakLanjut());
        detailCheckupEntity.setKelasPasien(bean.getIdKelas());
        detailCheckupEntity.setBerkas(bean.getBerkas());
        detailCheckupEntity.setFlagKunjungan(bean.getFlagKunjungan());
        detailCheckupEntity.setIsEksekutif(bean.getIsEksekutif());
        detailCheckupEntity.setIsVaksin(bean.getIsVaksin());

        if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
            detailCheckupEntity.setMetodePembayaran("non_tunai");
        }else{
            if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                detailCheckupEntity.setMetodePembayaran("tunai");
            }
        }

        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
            detailCheckupEntity.setRujuk(bean.getPerujuk() != null ? bean.getPerujuk() : null);
            detailCheckupEntity.setKetRujukan(bean.getNamaPerujuk() != null ? bean.getNamaPerujuk() : null);
            detailCheckupEntity.setKelasPasien(bean.getIdKelas());
            detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayananBpjs());
            detailCheckupEntity.setNoPpkRujukan(bean.getNoPpk() != null ? bean.getNoPpk() : null);
            detailCheckupEntity.setNoRujukan(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan()) ? bean.getNoRujukan() : null);
            detailCheckupEntity.setTglRujukan(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan()) ? java.sql.Date.valueOf(bean.getTglRujukan()) : null);
            detailCheckupEntity.setUrlDocRujuk(bean.getSuratRujukan() != null && !"".equalsIgnoreCase(bean.getSuratRujukan()) ? bean.getSuratRujukan() : null);
        }

        if("pindah_poli".equalsIgnoreCase(bean.getTypeTransaction())){
            if("igd".equalsIgnoreCase(bean.getTipePelayanan())){
                detailCheckupEntity.setStatusPeriksa("1");
            }else {
                if(!"paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                    String noAntrian = "";
                    HeaderCheckup lastAntrian = new HeaderCheckup();
                    String idDokter = null;
                    if(bean.getDokterTeamList().size() > 0){
                        idDokter = bean.getDokterTeamList().get(0).getIdDokter();
                    }
                    try {
                        lastAntrian = headerCheckupDao.lastAntrian(bean.getBranchId(), bean.getIdPelayanan(), idDokter);
                    }catch (HibernateException e){
                        logger.error("[CheckupBoImpl.saveAdd] Error When search no antrian" + e.getMessage());
                        throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When search no antrian");
                    }
                    if(lastAntrian.getStNoAntrian() != null){
                        int jumlah = Integer.valueOf(lastAntrian.getStNoAntrian()) + 1;
                        noAntrian = String.valueOf(jumlah);
                    }else{
                        noAntrian = "1";
                    }

                    if(!"".equalsIgnoreCase(noAntrian)){
                        detailCheckupEntity.setNoAntrian(noAntrian);
                    }
                }
                detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
            }
        }else{
            detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
        }

        try {
            checkupDetailDao.addAndSave(detailCheckupEntity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error When Saving data detail checkup " + e.getMessage());
            logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup");
        }

        if (bean.getDokterTeamList().size() > 0) {
            for (DokterTeam dokterTeam : bean.getDokterTeamList()) {
                DokterTeam dkt = new DokterTeam();
                dkt.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                dkt.setIdDokter(dokterTeam.getIdDokter());
                dkt.setIdPelayanan(dokterTeam.getIdPelayanan());
                dkt.setJenisDpjp(dokterTeam.getJenisDpjp());
                dkt.setCreatedWho(bean.getCreatedWho());
                dkt.setLastUpdateWho(bean.getLastUpdateWho());
                dkt.setFlagApprove(dokterTeam.getFlagApprove());
                response = saveTeamDokter(dkt);
            }
        }

        // save to table rawat inap
        if (bean.getRawatInap()) {
            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            rawatInap.setIdRuangan(bean.getIdRuangan());
            rawatInap.setNoCheckup(bean.getNoCheckup());
            rawatInap.setCreatedDate(bean.getCreatedDate());
            rawatInap.setCreatedWho(bean.getCreatedWho());
            rawatInap.setTglMasuk(bean.getCreatedDate());
            rawatInap.setStatus("1");
            response = saveRawatInap(rawatInap);
        }

        if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
            for (Tindakan tindakan : bean.getTindakanList()) {
                ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                tindakanRawatEntity.setIdTindakan(tindakan.getIdTindakan());
                tindakanRawatEntity.setNamaTindakan(tindakan.getTindakan());
                tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                tindakanRawatEntity.setFlag("Y");
                tindakanRawatEntity.setAction("U");
                tindakanRawatEntity.setApproveFlag("Y");

                if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    tindakanRawatEntity.setTarif(tindakan.getTarifBpjs());
                } else {
                    tindakanRawatEntity.setTarif(tindakan.getTarif());
                }

                tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                try {

                    tindakanRawatDao.addAndSave(tindakanRawatEntity);

                    if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                        ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                        riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                        riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                        riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                        riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                        riwayatTindakan.setApproveBpjsFlag("Y");
                        riwayatTindakan.setKategoriTindakanBpjs(tindakan.getKategoriInaBpjs());
                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien("bpjs");
                        riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        riwayatTindakan.setFlagUpdateKlaim("Y");
                        riwayatTindakan.setCreatedWho(bean.getCreatedWho());
                        riwayatTindakan.setCreatedDate(bean.getCreatedDate());
                        riwayatTindakan.setLastUpdate(bean.getLastUpdate());
                        riwayatTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setTanggalTindakan(bean.getCreatedDate());

                        try {
                            riwayatTindakanDao.addAndSave(riwayatTindakan);
                            response.setStatus("success");
                            response.setMsg("Berhasil");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Error when saving tindakan rawat " + e.getMessage());
                            logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                        }

                    }

                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error when saving tindakan rawat " + e.getMessage());
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                }
            }
        }

        if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
            if(bean.getJumlahUangMuka() != null && !"".equalsIgnoreCase(bean.getJumlahUangMuka().toString())){
                ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                if (bean.getBranchId() != null && !bean.getBranchId().equalsIgnoreCase("")) {
                    uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                } else{
                    uangMukaPendaftaranEntity.setId("UM" + CommonUtil.userBranchLogin() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                }
                uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                uangMukaPendaftaranEntity.setFlag("Y");
                uangMukaPendaftaranEntity.setAction("C");
                uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
                uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
                uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
                uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());
                if (bean.getInvoice() != null) {
                    uangMukaPendaftaranEntity.setNoNota(bean.getInvoice());
                    uangMukaPendaftaranEntity.setStatusBayar("Y");
                }
                uangMukaPendaftaranEntity.setJumlah(bean.getJumlahUangMuka());
                try {
                    uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error When Saving uang muka " + e.getMessage());
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                }
            }
        }

        if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
            diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
            if(bean.getRawatInap()){
                diagnosaRawat.setJenisDiagnosa("diagnosa_awal");
            }else{
                diagnosaRawat.setJenisDiagnosa("1");
            }
            response = saveDiagnosa(diagnosaRawat);
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
        return response;
    }

    private CrudResponse saveTeamDokter(DokterTeam bean) {
        logger.info("[CheckupDetailBoImpl.saveTeamDokter] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT" + id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(bean.getLastUpdateWho());
        entity.setJenisDpjp(bean.getJenisDpjp());
        entity.setFlagApprove(bean.getFlagApprove());

        try {
            dokterTeamDao.addAndSave(entity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error when save add dokter team " + e.getMessage());
            logger.error("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team ", e);
            throw new GeneralBOException("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.savaAdd] End <<<<<<<<");
        return response;
    }

    public CrudResponse saveDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>>>");
        CrudResponse response = new CrudResponse();

        if (bean != null && bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

            String id = getNextIdDiagnosa();
            entity.setIdDiagnosaRawat("DGR" + id);
            entity.setIdDiagnosa(bean.getIdDiagnosa());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
            entity.setJenisDiagnosa(bean.getJenisDiagnosa());
            entity.setTipe(bean.getTipe());
            entity.setFlag("Y");
            entity.setAction("U");
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            entity.setCreatedWho(CommonUtil.userLogin());
            entity.setLastUpdateWho(CommonUtil.userLogin());

            try {
                diagnosaRawatDao.addAndSave(entity);
                response.setStatus("success");
                response.setMsg("Bewrhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error when saveing diagnosa " + e.getMessage());
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
        return response;
    }

    private CrudResponse saveRawatInap(RawatInap bean) {
        logger.info("[CheckupDetailBoImpl.saveRawatInap] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup()) &&
                bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            TempatTidur tempatTidur = new TempatTidur();
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                TempatTidur tt = new TempatTidur();
                tt.setIdTempatTidur(bean.getIdRuangan());
                List<TempatTidur> tempatTidurList = tempatTidurDao.getDataTempatTidur(tt);
                if (tempatTidurList.size() > 0) {
                    tempatTidur = tempatTidurList.get(0);
                    if (tempatTidur != null) {
                        entity.setIdRuangan(tempatTidur.getIdTempatTidur());
                        entity.setTarif(tempatTidur.getTarif());
                        entity.setNamaRangan(tempatTidur.getNamaRuangan());
                        entity.setNoRuangan(tempatTidur.getNoRuangan());
                    }
                }
                String id = getNextRawatInapId();
                entity.setIdRawatInap("RNP" + id);
                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setNoCheckup(bean.getNoCheckup());
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getCreatedDate());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getCreatedWho());
                entity.setTglMasuk(bean.getTglMasuk());
                entity.setStatus(bean.getStatus());
                entity.setKeterangan(bean.getKeterangan());

                try {
                    rawatInapDao.addAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg(" Error when save add Rawat Inap " + e.getMessage());
                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when save add  Rawat Inap " + e.getMessage());
                }

                // set ketersedian ruangan to tidak tersedia
                if (tempatTidur != null) {
                    MtSimrsRuanganTempatTidurEntity tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", tempatTidur.getIdTempatTidur());
                    if(tempatTidurEntity != null){
                        tempatTidurEntity.setStatus("N");
                        tempatTidurEntity.setAction("U");
                        tempatTidurEntity.setLastUpdate(bean.getCreatedDate());
                        tempatTidurEntity.setLastUpdateWho(bean.getCreatedWho());
                        try {
                            tempatTidurDao.updateAndSave(tempatTidurEntity);
                            response.setStatus("success");
                            response.setMsg(entity.getIdRawatInap());
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Error When Saving data detail checkup " + e.getMessage());
                            logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                            throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan " + e.getMessage());
                        }
                    }
                }
            }
        }
        logger.info("[CheckupDetailBoImpl.saveRawatInap] End <<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse updateRuanganInap(String idRawatInap, String idRuangan, String idDetailCheckup, String tanggal) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.updateRuanganInap] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();

        ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
        try {
            rawatInapEntity = rawatInapDao.getById("idRawatInap", idRawatInap);
            response.setStatus("success");
            response.setMsg("berhasil");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error" + e.getMessage());
        }
        if (rawatInapEntity != null) {
            rawatInapEntity.setKeterangan("pindah");
//            rawatInapEntity.setStatus("3");
//            rawatInapEntity.setTglKeluar(new Timestamp(System.currentTimeMillis()));
            rawatInapEntity.setLastUpdateWho(CommonUtil.userLogin());
            rawatInapEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            try {
                rawatInapDao.updateAndSave(rawatInapEntity);
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error" + e.getMessage());
            }

            if (idRuangan != null && !"".equalsIgnoreCase(idRuangan) && idDetailCheckup != null && !"".equalsIgnoreCase(idRuangan) &&
                    rawatInapEntity.getNoCheckup() != null && !"".equalsIgnoreCase(rawatInapEntity.getNoCheckup())) {
                // save new data to table rawat inap
                RawatInap rawatInapNew = new RawatInap();
                rawatInapNew.setIdDetailCheckup(idDetailCheckup);
                rawatInapNew.setIdRuangan(idRuangan);
                rawatInapNew.setNoCheckup(rawatInapEntity.getNoCheckup());
                rawatInapNew.setTglMasuk(Timestamp.valueOf(tanggal));
                rawatInapNew.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                rawatInapNew.setCreatedWho(CommonUtil.userLogin());
                rawatInapNew.setKeterangan("new");
                rawatInapNew.setStatus("0");
                response = saveRawatInap(rawatInapNew);
            }
        }
        logger.info("[CheckupDetailBoImpl.updateRuanganInap] End <<<<<<<<<");
        return response;
    }

    private List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListEntityTindakan] Start >>>>>>>");

        List<ImSimrsTindakanEntity> tindakanEntities = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdTindakan() != null) {
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdKategoriTindakan() != null) {
                hsCriteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
            }
            if (bean.getFlag() != null) {
                hsCriteria.put("flag", bean.getFlag());
            }
            try {
                tindakanEntities = tindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
            }

        }
        logger.info("[CheckupBoImpl.getListEntityTindakan] End <<<<<<<");
        return tindakanEntities;

    }

    @Override
    public BigInteger getSumOfTindakanByNoCheckup(String idDetailCheckup) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getSumOfTindakanByNoCheckup] Start >>>>>>>>");

        BigInteger result = new BigInteger(String.valueOf(0));

        try {
            result = checkupDetailDao.sumOfTindakanByNoCheckup(idDetailCheckup);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error ", e);
            throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getSumOfTindakanByNoCheckup] End <<<<<<<<");
        return result;
    }

    @Override
    public CheckResponse saveApproveAllTindakanRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveApproveAllTindakanRawatJalan] START <<<<<<<<");
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ItSimrsTindakanRawatEntity> entityTindakanList = getListEntityTindakanRawat(tindakanRawat);

            if (entityTindakanList.size() > 0) {
                for (ItSimrsTindakanRawatEntity entity : entityTindakanList) {
                    entity.setApproveFlag("Y");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        tindakanRawatDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil update tindakan");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMessage("Error when update tindakan, Found Eror: " + e.getMessage());
                        logger.error("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag ", e);
                        throw new GeneralBOException("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag " + e.getMessage());
                    }
                }
            } else {
                response.setStatus("error");
                response.setMessage("Tidak ada tindakan yang dilakukan");
            }
        }
        logger.info("[CheckupDetailBoImpl.saveApproveAllTindakanRawatJalan] END <<<<<<<<");
        return response;
    }

    protected List<ItSimrsTindakanRawatEntity> getListEntityTindakanRawat(TindakanRawat bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] Start >>>>>>>");
        List<ItSimrsTindakanRawatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdTindakanRawat() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawat())) {
            hsCriteria.put("id_tindakan_rawat", bean.getIdTindakanRawat());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        hsCriteria.put("flag", "Y");
        try {
            results = tindakanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TindakanRawatBoImpl.getListEntityTindakanRawat] Erro when searching tindakan rawat ", e);
        }

        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] End <<<<<<");
        return results;
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

    private List<ImSimrsPermintaanResepEntity> getListEntityResep(PermintaanResep bean) throws GeneralBOException {
        logger.info("[PermintaanResepBoImpl.getListEntityResep] START >>>>>>>");

        List<ImSimrsPermintaanResepEntity> permintaanResepEntityList = new ArrayList<>();

        if (bean != null) {

            Map hsCriteria = new HashMap();

            if (bean.getIdPermintaanResep() != null && !"".equalsIgnoreCase(bean.getIdPermintaanResep())) {
                hsCriteria.put("id_permintaan_resep", bean.getIdPermintaanResep());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdApprovalObat() != null && !"".equalsIgnoreCase(bean.getIdApprovalObat())) {
                hsCriteria.put("id_approval_obat", bean.getIdApprovalObat());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                permintaanResepEntityList = permintaanResepDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getListEntityResep] ERROR when get data permintaan resep entity by criteria. ", e);
            }
        }

        logger.info("[PermintaanResepBoImpl.getListEntityResep] END <<<<<<<");
        return permintaanResepEntityList;
    }

    @Override
    public List<HeaderDetailCheckup> getListUangPendaftaran(HeaderDetailCheckup bean) throws GeneralBOException {
        return checkupDetailDao.getListPembayaranUangMuka(bean);
    }

    @Override
    public void updateFlagPeriksaAntrianOnline(String idDetailCheckup) throws GeneralBOException {

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            List<ItSimrsAntianOnlineEntity> onlineEntityList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", idDetailCheckup);

            try {
                onlineEntityList = antrianOnlineDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("Found Error when search antrian online " + e.getMessage());
            }

            ItSimrsAntianOnlineEntity onlineEntity = new ItSimrsAntianOnlineEntity();
            if (onlineEntityList.size() > 0) {
                onlineEntity = onlineEntityList.get(0);
                if (onlineEntity.getIdAntrianOnline() != null) {
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                    onlineEntity.setLastUpdateWho(CommonUtil.userLogin());
                    onlineEntity.setLastUpdate(updateTime);
                    onlineEntity.setFlagPeriksa("Y");

                    try {
                        antrianOnlineDao.updateAndSave(onlineEntity);
                    } catch (HibernateException e) {
                        logger.error("Found Error when update save flag antrian online");
                    }
                }
            }
        }
    }


    public void updateStatusBayarDetailCheckup(List<HeaderDetailCheckup> list) throws GeneralBOException {

        if(list.size() > 0){
            for (HeaderDetailCheckup bean: list){
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                if (detailCheckupEntity != null) {
                    detailCheckupEntity.setStatusBayar("Y");
                    detailCheckupEntity.setAction("U");
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    detailCheckupEntity.setInvoice(bean.getInvoice());
                    detailCheckupEntity.setNoJurnal(bean.getNoJurnal());
                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR when save status bayar. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR when status bayar. ", e);
                    }
                }
            }
        }
    }

    @Override
    public CheckResponse updateInvoiceBpjs(String idDetailCheckup, String invNumber) {

        CheckResponse response = new CheckResponse();

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = getListEntityByCriteria(detailCheckup);

        if (detailCheckupEntities.size() > 0) {
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.get(0);
            detailCheckupEntity.setInvoice(invNumber);
            try {
                checkupDetailDao.updateAndSave(detailCheckupEntity);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. " + e);
                logger.error("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. ", e);
            }
        }

        return response;
    }

    @Override
    public ItSimrsHeaderDetailCheckupEntity getEntityDetailCheckupByIdDetail(String idDetailCheckup) throws GeneralBOException {
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", idDetailCheckup);

            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = new ArrayList<>();
            try {
                detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
            }

            if (detailCheckupEntities.size() > 0) {
                return detailCheckupEntities.get(0);
            }
        }
        return new ItSimrsHeaderDetailCheckupEntity();
    }

    @Override
    public void saveUpdateNoJuran(HeaderDetailCheckup bean) throws GeneralBOException {
        if (bean != null) {

            HeaderDetailCheckup detail = new HeaderDetailCheckup();
            detail.setIdDetailCheckup(bean.getIdDetailCheckup());

            List<ItSimrsHeaderDetailCheckupEntity> details = getListEntityByCriteria(detail);
            if (details.size() > 0) {
                for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : details) {
                    detailCheckupEntity.setAction(bean.getAction());
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    // for transitoris or jurnal rawat
                    if (bean.getNoJurnalTrans() != null && !"".equalsIgnoreCase(bean.getNoJurnalTrans())) {
                        detailCheckupEntity.setNoJurnalTrans(bean.getNoJurnalTrans());
                        detailCheckupEntity.setTransPeriode(bean.getTransPeriode());
                        detailCheckupEntity.setTransDate(bean.getTransDate());
                        detailCheckupEntity.setInvoiceTrans(bean.getInvoice());
                    } else {

                        String invoice = bean.getInvoice() == null ? detailCheckupEntity.getInvoice() : bean.getInvoice();
                        String noJurnal = bean.getNoJurnal() == null ? detailCheckupEntity.getNoJurnal() : bean.getNoJurnal();

                        detailCheckupEntity.setInvoice(invoice);
                        detailCheckupEntity.setNoJurnal(noJurnal);
                    }

                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                    }
                }
            }
        }
    }

    @Override
    public BigDecimal getSumJumlahTindakan(String idDetailCheckup, String ket) {
        return checkupDetailDao.getSumAllTarifTindakan(idDetailCheckup, "bpjs", ket);
    }

    @Override
    public BigDecimal getSumJumlahTindakanNonBpjs(String idDetailCheckup, String ket) {
        return checkupDetailDao.getSumAllTarifTindakan(idDetailCheckup, "", ket);
    }

    @Override
    public BigDecimal getSumJumlahTindakanTransitoris(String idDetailCheckup, String ket) {
        return checkupDetailDao.getSumAllTarifTransitoris(idDetailCheckup, ket);
    }

    @Override
    public BigDecimal getSumJumlahTindakanByJenis(String idDetailCheckup, String jenis, String ket) {
        return checkupDetailDao.getSumAllTarifTindakan(idDetailCheckup, jenis, ket);
    }

    @Override
    public BigDecimal getSumJumlahTindakanByJenisRI(String idDetailCheckup, String jenis, String ket, String idRuangan) {
        return checkupDetailDao.getSumAllTarifTindakanRI(idDetailCheckup, jenis, ket, idRuangan);
    }

    @Override
    public BigDecimal getSumJumlajTindakanTransitorisByJenis(String idDetailCheckup, String jenis, String ket) {
        return checkupDetailDao.getSumAllTarifTransitorisByJenis(idDetailCheckup, jenis, ket);
    }

    @Override
    public String findResep(String idDetailCheckup) {
        return checkupDetailDao.getFindResepInRiwayatTrans(idDetailCheckup);
    }

    @Override
    public List<Dokter> getListDokterByDetailCheckup(String idDetailCheckup) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", idDetailCheckup);
        hsCriteria.put("flag", "Y");

        List<ItSimrsDokterTeamEntity> dokterTeamEntities = dokterTeamDao.getByCriteria(hsCriteria);
        List<Dokter> dokterList = new ArrayList<>();
        if (dokterTeamEntities.size() > 0) {
            for (ItSimrsDokterTeamEntity dokterTeamEntity : dokterTeamEntities) {

                hsCriteria = new HashMap();
                hsCriteria.put("id_dokter", dokterTeamEntity.getIdDokter());

                List<ImSimrsDokterEntity> dokterEntities = dokterDao.getByCriteria(hsCriteria);
                if (dokterEntities.size() > 0) {
                    Dokter dokter;
                    for (ImSimrsDokterEntity dokterEntity : dokterEntities) {
                        dokter = new Dokter();
                        dokter.setIdDokter(dokterEntity.getIdDokter());
                        dokter.setNamaDokter(dokterEntity.getNamaDokter());
                        dokterList.add(dokter);
                    }
                }
            }
        }
        return dokterList;
    }

    @Override
    public Boolean checkAdaTransitoris(String idDetailCheckup) throws GeneralBOException {
        return riwayatTindakanDao.checkIsTransitoris(idDetailCheckup);
    }

    @Override
    public ItSimrsHeaderDetailCheckupEntity getDetailCheckupById(String id) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getDetailCheckupById] START >>>>");
        logger.info("[CheckupDetailBoImpl.getDetailCheckupById] END <<<");
        return checkupDetailDao.getById("idDetailCheckup", id);
    }

    @Override
    public List<HeaderDetailCheckup> getListRawatInapExisiting(String branchId) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getListRawatInapExisiting] START >>>>");
        logger.info("[CheckupDetailBoImpl.getListRawatInapExisiting] END <<<");
        return checkupDetailDao.getListRawatInapExisting(branchId);
    }

    private String getNextIdDiagnosa() {
        String id = "";
        try {
            id = diagnosaRawatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[DiagnosaRawatBoImpl.getNextId] Error when get next diagnosa rawat id ", e);
        }
        return id;
    }

    private String getNextDetailCheckupId() {
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextDetailCheckupId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextTindakanRawatId() {
        String id = "";
        try {
            id = tindakanRawatDao.getNextTindakanRawatId();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextIdRiwayatTindakan() {
        String id = "";
        try {
            id = riwayatTindakanDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextTeamDokterId() {
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextTeamDokterId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextTeamDokterId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextRawatInapId() {
        String id = "";
        try {
            id = rawatInapDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextRawatInapId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextRawatInapId] Error When Error get next seq id");
        }
        return id;
    }

    @Override
    public void saveTtd(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveTtd] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = null;

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());

        detailCheckupEntityList = getListEntityByCriteria(detailCheckup);
        if (!detailCheckupEntityList.isEmpty()) {
            ItSimrsHeaderDetailCheckupEntity entity = detailCheckupEntityList.get(0);
            entity.setUrlTtd(bean.getUrlTtd());

            try {
                checkupDetailDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup ", e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup " + e.getMessage());
            }
            logger.info("[CheckupDetailBoImpl.saveTtd] End >>>>>>>");

        }
    }

    @Override
    public List<RiwayatTindakanDTO> getRiwayatTindakanDanDokter(String idDetailCheckup) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getRiwayatTindakanDanDokter] START >>>>");
        logger.info("[CheckupDetailBoImpl.getRiwayatTindakanDanDokter] END <<<");
        return checkupDetailDao.getRiwayatTindakanDanDokter(idDetailCheckup);
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setPeriksaLabDao(PeriksaLabDao periksaLabDao) {
        this.periksaLabDao = periksaLabDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setOrderGiziDao(OrderGiziDao orderGiziDao) {
        this.orderGiziDao = orderGiziDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setAntrianOnlineDao(AntrianOnlineDao antrianOnlineDao) {
        this.antrianOnlineDao = antrianOnlineDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    public void setPaketPasienDao(PaketPasienDao paketPasienDao) {
        this.paketPasienDao = paketPasienDao;
    }

    public void setTempatTidurDao(TempatTidurDao tempatTidurDao) {
        this.tempatTidurDao = tempatTidurDao;
    }

    @Override
    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setUploadPendukungPemeriksaanDao(UploadPendukungPemeriksaanDao uploadPendukungPemeriksaanDao) {
        this.uploadPendukungPemeriksaanDao = uploadPendukungPemeriksaanDao;
    }
}
