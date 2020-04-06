package com.neurix.simrs.transaksi.statusperiksa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.dao.DiagnosaDao;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupLogDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupLogEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.statusperiksa.bo.StatusPeriksaBo;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusPeriksaBoImpl implements StatusPeriksaBo {

    public static transient Logger logger = Logger.getLogger(StatusPeriksaBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private HeaderCheckupDao headerCheckupDao;
    private UangMukaDao uangMukaDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private TindakanRawatDao tindakanRawatDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private TindakanDao tindakanDao;
    private HeaderCheckupLogDao headerCheckupLogDao;
    private DokterTeamDao dokterTeamDao;

    @Override
    public List<HeaderDetailCheckup> getListStatusPeriksa(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException {
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        if (headerDetailCheckup != null) {
            try {
                detailCheckupList = checkupDetailDao.getStatusPeriksa(headerDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search status periksa " + e.getMessage());
            }
        }

        return detailCheckupList;
    }

    @Override
    public CheckResponse saveEditPerubahanStatus(HeaderDetailCheckup bean) throws GeneralBOException {
        CheckResponse response = new CheckResponse();
        if (bean != null) {

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
                ItSimrsHeaderChekupEntity headerChekupEntity = new ItSimrsHeaderChekupEntity();

                try {

                    headerChekupEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());

                    if (headerChekupEntity != null) {

                        headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        headerChekupEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            headerCheckupDao.updateAndSave(headerChekupEntity);
                            response.setStatus("success");
                            response.setMessage("Berhasil");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Found Error when save update " + e.getMessage());
                            logger.error("Found Error when save update header " + e.getMessage());
                        }
                    }

                    detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());

                    if (detailCheckupEntity != null) {

                        try {
                            ItSimrsHeaderChekupLogEntity logEntity = new ItSimrsHeaderChekupLogEntity();
                            logEntity.setIdHeaderCheckupLog("CKL" + getNextIdHeaderLog());
                            logEntity.setNoCheckup(detailCheckupEntity.getNoCheckup());
                            logEntity.setIdJenisPeriksaPasien(detailCheckupEntity.getIdJenisPeriksaPasien());
                            logEntity.setCreatedDate(bean.getLastUpdate());
                            logEntity.setCreatedWho(bean.getLastUpdateWho());
                            logEntity.setLastUpdate(bean.getLastUpdate());
                            logEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            logEntity.setAction("C");
                            logEntity.setFlag("Y");

                            try {
                                headerCheckupLogDao.addAndSave(logEntity);
                                response.setStatus("success");
                                response.setMessage("Berhasil");
                            } catch (HibernateException e) {
                                response.setStatus("error");
                                response.setMessage("Found Error when save log " + e.getMessage());
                                logger.error("Found Error when save log " + e.getMessage());
                            }
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Found Error when save log " + e.getMessage());
                            logger.error("Found Error when save log " + e.getMessage());
                        }

                        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            detailCheckupEntity.setNoRujukan(bean.getNoRujukan());
                            detailCheckupEntity.setNoPpkRujukan(bean.getNoPpk());
                            detailCheckupEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
                            detailCheckupEntity.setUrlDocRujuk(bean.getSuratRujukan());
                            detailCheckupEntity.setKelasPasien(bean.getIdKelas());
                            detailCheckupEntity.setRujuk(bean.getPerujuk());
                            detailCheckupEntity.setKetRujukan(bean.getNamaPerujuk());
                            detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayanan());
                            detailCheckupEntity.setNoSep(bean.getNoSep());
                            detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                            detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                        }

                        if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi());
                            detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi());
                            detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya());
                        }

                        if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran());
                        }

                        detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
                        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {

                            checkupDetailDao.updateAndSave(detailCheckupEntity);

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa())
                                        && bean.getNamaDiagnosa() != null && !"".equalsIgnoreCase(bean.getNamaDiagnosa())) {
                                    ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

                                    String id = getNextIdDiagnosa();
                                    entity.setIdDiagnosaRawat("DGR" + id);
                                    entity.setIdDiagnosa(bean.getDiagnosa());
                                    entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                                    entity.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                                    entity.setJenisDiagnosa("0");
                                    entity.setFlag("Y");
                                    entity.setAction("U");
                                    entity.setCreatedDate(bean.getLastUpdate());
                                    entity.setLastUpdate(bean.getLastUpdate());
                                    entity.setCreatedWho(bean.getLastUpdateWho());
                                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        diagnosaRawatDao.addAndSave(entity);
                                    } catch (HibernateException e) {
                                        logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                                        throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
                                    }
                                }

                                if (bean.getTindakanList().size() > 0) {

                                    ItSimrsDokterTeamEntity dokterTeamEntity = new ItSimrsDokterTeamEntity();
                                    if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                                        Map hsCriteria = new HashMap();
                                        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
                                        List<ItSimrsDokterTeamEntity> list = new ArrayList<>();

                                        try {
                                            list = dokterTeamDao.getByCriteria(hsCriteria);
                                        }catch (HibernateException e){
                                            response.setStatus("error");
                                            response.setMessage("Found Error "+e.getMessage());
                                            logger.error("found error"+e.getMessage());
                                        }
                                        if(list.size() > 0){
                                            dokterTeamEntity = list.get(0);
                                        }
                                    }

                                    for (Tindakan tindakan : bean.getTindakanList()) {
                                        List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                                        if (tindakanEntities.size() > 0) {

                                            ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);
                                            ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                                            tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                            tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                                            tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                                            tindakanRawatEntity.setNamaTindakan(tindakanEntity.getTindakan());
                                            tindakanRawatEntity.setIdDokter(dokterTeamEntity.getIdDokter());
                                            tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                                            tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                                            tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                                            tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                                            tindakanRawatEntity.setFlag("Y");
                                            tindakanRawatEntity.setAction("U");

                                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                                tindakanRawatEntity.setTarif(tindakanEntity.getTarifBpjs());
                                            } else {
                                                tindakanRawatEntity.setTarif(tindakanEntity.getTarif());
                                            }

                                            tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                                            tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                                            try {

                                                tindakanRawatDao.addAndSave(tindakanRawatEntity);

                                                if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                                    ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                                    riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                                    riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                                    riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                                    riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                                    riwayatTindakan.setApproveBpjsFlag("Y");
                                                    riwayatTindakan.setKategoriTindakanBpjs("konsultasi");
                                                    riwayatTindakan.setKeterangan("tindakan");
                                                    riwayatTindakan.setJenisPasien(bean.getIdJenisPeriksaPasien());
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
                                                    } catch (HibernateException e) {
                                                        logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                                                    }

                                                }

                                            } catch (HibernateException e) {
                                                logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                                                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }

                            if("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                                UangMuka uangMuka = new UangMuka();
                                uangMuka.setIdDetailCheckup(bean.getIdDetailCheckup());
                                ItSimrsUangMukaPendaftaranEntity entityUangMuka = getEntityUangMuka(uangMuka);

                                if (entityUangMuka != null) {

                                    entityUangMuka.setFlagRefund("R");
                                    entityUangMuka.setLastUpdate(bean.getLastUpdate());
                                    entityUangMuka.setLastUpdateWho(bean.getLastUpdateWho());

                                    try {
                                        uangMukaDao.updateAndSave(entityUangMuka);
                                        response.setStatus("success");
                                        response.setMessage("Berhasil");
                                    } catch (HibernateException e) {
                                        response.setStatus("error");
                                        response.setMessage("Found Error when save update " + e.getMessage());
                                        logger.error("Found Error when search uang muka " + e.getMessage());
                                    }
                                }
                            }

                            if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                if (bean.getJumlahUangMuka() != null && !"".equalsIgnoreCase(bean.getJumlahUangMuka().toString())) {
                                    ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                                    uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                                    uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                    uangMukaPendaftaranEntity.setFlag("Y");
                                    uangMukaPendaftaranEntity.setAction("C");
                                    uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
                                    uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
                                    uangMukaPendaftaranEntity.setLastUpdate(bean.getLastUpdate());
                                    uangMukaPendaftaranEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                    if (bean.getJumlahUangMuka() == null || bean.getJumlahUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0) {
                                        uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
                                    } else {
                                        uangMukaPendaftaranEntity.setJumlah(bean.getJumlahUangMuka());
                                    }

                                    try {
                                        uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                                        response.setStatus("success");
                                        response.setMessage("Berhasil");
                                    } catch (HibernateException e) {
                                        response.setStatus("error");
                                        response.setMessage("Found Error when save update " + e.getMessage());
                                        logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                                        throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                                    }
                                }
                            }

                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Found Error when save update " + e.getMessage());
                            logger.error("Found Error when save checkup detail " + e.getMessage());
                        }
                    }

                } catch (HibernateException e) {
                    logger.error("Found Error when search id detail checkup " + e.getMessage());
                    response.setStatus("error");
                    response.setMessage("Found Error when save update " + e.getMessage());
                }
            }


        }
        return response;
    }

    private ItSimrsUangMukaPendaftaranEntity getEntityUangMuka(UangMuka bean) {
        ItSimrsUangMukaPendaftaranEntity entityUangMuka = new ItSimrsUangMukaPendaftaranEntity();
        Map hsCriteria = new HashMap();

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        List<ItSimrsUangMukaPendaftaranEntity> entityList = new ArrayList<>();

        try {
            entityList = uangMukaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("Found Error when search uang muka " + e.getMessage());
        }

        if (entityList != null) {
            entityUangMuka = entityList.get(0);
        }

        return entityUangMuka;
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

    private String dateFormater(String type) {
        Date date = new Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
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

    private String getNextIdHeaderLog() {
        String id = "";
        try {
            id = headerCheckupLogDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdHeaderLog] ERROR When create sequences", e);
        }
        return id;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setHeaderCheckupLogDao(HeaderCheckupLogDao headerCheckupLogDao) {
        this.headerCheckupLogDao = headerCheckupLogDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }
}