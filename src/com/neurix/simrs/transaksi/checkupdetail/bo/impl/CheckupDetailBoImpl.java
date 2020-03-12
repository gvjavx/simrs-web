package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.antrianonline.dao.AntrianOnlineDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.ordergizi.dao.OrderGiziDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
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
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
            List<ImSimrsPelayananEntity> pelayananEntityList = getListEntityPelayanan(pelayanan);

            ImSimrsPelayananEntity pelayananEntity = new ImSimrsPelayananEntity();
            if (!pelayananEntityList.isEmpty()) {
                pelayananEntity = pelayananEntityList.get(0);
            }
            if (pelayananEntity != null) {
                detailCheckup.setNamaPelayanan(pelayananEntity.getNamaPelayanan());
                detailCheckup.setTipePelayanan(pelayananEntity.getTipePelayanan());
            }
            results.add(detailCheckup);
        }

        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] End <<<<<<<<");
        return results;
    }

    @Override
    public void saveEdit(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = null;

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());

        detailCheckupEntityList = getListEntityByCriteria(detailCheckup);
        if (!detailCheckupEntityList.isEmpty()) {
            ItSimrsHeaderDetailCheckupEntity entity = detailCheckupEntityList.get(0);
            entity.setStatusPeriksa(bean.getStatusPeriksa());
            entity.setKeteranganSelesai(bean.getKeteranganSelesai());
            entity.setKeteranganCekupUlang(bean.getKeteranganCekupUlang());
            entity.setTglCekup(bean.getTglCekup());
            entity.setJenisLab(bean.getJenisLab());
            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setCaraPasienPulang(bean.getCaraPasienPulang());
            entity.setPendamping(bean.getPendamping());
            entity.setTempatTujuan(bean.getTempatTujuan());
            entity.setInvoice(bean.getInvoice());
            entity.setUrlTtd(bean.getUrlTtd());
//            entity.setMetodePembayaran(bean.getMetodePembayaran());


            try {
                checkupDetailDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup ", e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup " + e.getMessage());
            }

            // update tgl kluar if selesai
            if ("selesai".equalsIgnoreCase(bean.getStatus())) {
                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(entity.getNoCheckup());
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = getListEntityCheckup(headerCheckup);
                if (headerChekupEntities != null && headerChekupEntities.size() > 0) {
                    ItSimrsHeaderChekupEntity headerChekupEntity = headerChekupEntities.get(0);
                    headerChekupEntity.setLastUpdate(bean.getLastUpdate());
                    headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerChekupEntity.setAction("U");
                    headerChekupEntity.setTglKeluar(bean.getLastUpdate());

                    try {
                        headerCheckupDao.updateAndSave(headerChekupEntity);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailBoImpl.saveEdit] Error when update checkup ", e);
                        throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update checkup " + e.getMessage());
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
                } catch (HibernateException e) {
                    logger.error("[CheckupDetailBoImpl.saveEdit] Error when Update data transaksi inap ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when Update data transaksi inap " + e.getMessage());
                }

                if (rawatInapEntity.getIdRuangan() != null && !"".equalsIgnoreCase(rawatInapEntity.getIdRuangan())) {
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdRuangan(rawatInapEntity.getIdRuangan());
                    ruangan.setStatusRuangan("N");
                    List<MtSimrsRuanganEntity> ruanganEntities = getListEntityRuangan(ruangan);

                    if (!ruanganEntities.isEmpty() && ruanganEntities.size() > 0) {
                        for (MtSimrsRuanganEntity ruanganEntity : ruanganEntities) {
                            ruanganEntity.setStatusRuangan("Y");
                            ruanganEntity.setAction("U");
                            ruanganEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            ruanganEntity.setLastUpdateWho(CommonUtil.userLogin());

                            try {
                                ruanganDao.updateAndSave(ruanganEntity);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailBoImpl.saveEdit] Error when Update status master ruangan ", e);
                                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when Update status master ruangan " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
    }

    private String dateFormater(String type){
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
    public void saveAdd(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");

        // create new detail
        String id = "";
        id = getNextDetailCheckupId();

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
        detailCheckupEntity.setIdDetailCheckup("DCM" + id);
        detailCheckupEntity.setNoCheckup(bean.getNoCheckup());
        detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
        detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
        detailCheckupEntity.setFlag("Y");
        detailCheckupEntity.setAction("C");
        detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
        detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
        detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
        detailCheckupEntity.setNoSep(bean.getNoSep());
        detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
        detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran());
        detailCheckupEntity.setKodeCbg(bean.getKodeCbg());

        try {
            checkupDetailDao.addAndSave(detailCheckupEntity);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup");
        }

        // insert into uang muka
//        if (bean.getNoNota() != null && !"".equalsIgnoreCase(bean.getNoNota())){
//            ItSimrsUangMukaPendaftaranEntity uangMuka = new ItSimrsUangMukaPendaftaranEntity();
//            uangMuka.setId("UMK"+uangMukaDao.getNextId());
//            uangMuka.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
//            uangMuka.setJumlah(new BigInteger(String.valueOf(0)));
//            uangMuka.setNoNota(bean.getNoNota());
//            uangMuka.setStatusBayar("Y");
//            uangMuka.setFlag("Y");
//            uangMuka.setAction("C");
//            uangMuka.setCreatedDate(bean.getCreatedDate());
//            uangMuka.setCreatedWho(bean.getCreatedWho());
//            uangMuka.setLastUpdate(bean.getLastUpdate());
//            uangMuka.setLastUpdateWho(bean.getLastUpdateWho());
//            try {
//                uangMukaDao.addAndSave(uangMuka);
//            } catch (HibernateException e){
//                logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving uang muka" + e.getMessage());
//                throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving uang muka");
//            }
//        }

        // saving dokter
        if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            dokterTeam.setIdDokter(bean.getIdDokter());
            dokterTeam.setCreatedWho(bean.getCreatedWho());
            dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
            saveTeamDokter(dokterTeam);
        }

        // for rawat Inap
        if (bean.getRawatInap()) {
            List<ItSimrsDokterTeamEntity> dokterEntities = null;
            Map hsCriteria = new HashMap();
            // set criteria with old detail id for get old dokter team data
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());

            try {
                dokterEntities = dokterTeamDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team" + e.getMessage());
                throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team");
            }

            ItSimrsDokterTeamEntity dokTeam = new ItSimrsDokterTeamEntity();

            if (!dokterEntities.isEmpty()) {
                dokTeam = dokterEntities.get(0);

                DokterTeam dokterTeam = new DokterTeam();
                // set id detail with new id detail
                dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                dokterTeam.setIdDokter(dokTeam.getIdDokter());
                dokterTeam.setCreatedWho(bean.getCreatedWho());
                dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
                saveTeamDokter(dokterTeam);
            }

            // save to table rawat inap
            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            rawatInap.setIdRuangan(bean.getIdRuangan());
            rawatInap.setNoCheckup(bean.getNoCheckup());
            rawatInap.setCreatedDate(bean.getCreatedDate());
            rawatInap.setCreatedWho(bean.getCreatedWho());
            saveRawatInap(rawatInap);

            if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                for (Tindakan tindakan : bean.getTindakanList()) {
                    List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                    if (tindakanEntities.size() > 0) {

                        ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                        tindakanRawatEntity.setNamaTindakan(tindakanEntity.getTindakan());
                        tindakanRawatEntity.setIdDokter(dokTeam.getIdDokter());
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

        if(!"bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            // save uang muka
            ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
            if  (bean.getBranchId() != null && !bean.getBranchId().equalsIgnoreCase("")){
                uangMukaPendaftaranEntity.setId("UM"+bean.getBranchId()+dateFormater("MM")+dateFormater("yy")+uangMukaDao.getNextId());
            } else uangMukaPendaftaranEntity.setId("UM"+CommonUtil.userBranchLogin()+dateFormater("MM")+dateFormater("yy")+uangMukaDao.getNextId());
            uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            uangMukaPendaftaranEntity.setFlag("Y");
            uangMukaPendaftaranEntity.setAction("C");
            uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
            uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());

            if (bean.getInvoice() != null){
                uangMukaPendaftaranEntity.setNoNota(bean.getInvoice());
                uangMukaPendaftaranEntity.setStatusBayar("Y");
            }

            if ("".equalsIgnoreCase(bean.getJumlahUangMuka().toString()) || bean.getJumlahUangMuka() == null || bean.getJumlahUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0){
                uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
            } else {
                uangMukaPendaftaranEntity.setJumlah(bean.getJumlahUangMuka());
            }

            try {
                uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving" +e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving"+ e.getMessage());
            }
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
    }

    private void saveTeamDokter(DokterTeam bean) {
        logger.info("[CheckupDetailBoImpl.saveTeamDokter] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT" + id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            dokterTeamDao.addAndSave(entity);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team ", e);
            throw new GeneralBOException("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.savaAdd] End <<<<<<<<");
    }

    private void saveRawatInap(RawatInap bean) {
        logger.info("[CheckupDetailBoImpl.saveRawatInap] Start >>>>>>>>");
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup()) &&
                bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            MtSimrsRuanganEntity ruanganEntity = new MtSimrsRuanganEntity();

            // get data ruangan;
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(bean.getIdRuangan());

                List<MtSimrsRuanganEntity> listRuangan = getListEntityRuangan(ruangan);

                if (!listRuangan.isEmpty()) {
                    ruanganEntity = listRuangan.get(0);

                    if (ruanganEntity != null) {

                        // set to entity table rawat inap for ruangan data
                        entity.setIdRuangan(ruanganEntity.getIdRuangan());
                        entity.setTarif(ruanganEntity.getTarif());
                        entity.setNamaRangan(ruanganEntity.getNamaRuangan());
                        entity.setNoRuangan(ruanganEntity.getNoRuangan());
                    }
                }
            }

            // set to entity table rawat inap
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
            entity.setTglMasuk(bean.getCreatedDate());

            try {
                rawatInapDao.addAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ", e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when save add  Rawat Inap " + e.getMessage());
            }

            // set ketersedian ruangan to tidak tersedia
            if (ruanganEntity != null) {

                ruanganEntity.setSisaKuota(ruanganEntity.getSisaKuota() - 1);
                ruanganEntity.setAction("U");
                ruanganEntity.setLastUpdate(bean.getCreatedDate());
                ruanganEntity.setLastUpdateWho(bean.getCreatedWho());

                if (ruanganEntity.getSisaKuota() == 0) {
                    ruanganEntity.setStatusRuangan("N");
                }

                try {
                    ruanganDao.updateAndSave(ruanganEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan " + e.getMessage());
                }
            }
        }

        logger.info("[CheckupDetailBoImpl.saveRawatInap] End <<<<<<<<");
    }

    @Override
    public void updateRuanganInap(String idRuangan, String idDetailCheckup) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.updateRuanganInap] Start >>>>>>>>");

        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);
        rawatInap.setFlag("Y");
        List<ItSimrsRawatInapEntity> rawatInapEntities = getListEntityRawatInap(rawatInap);

        String noCheckup = "";
        if (!rawatInapEntities.isEmpty() && rawatInapEntities.size() > 0) {
            for (ItSimrsRawatInapEntity rawatInapEntity : rawatInapEntities) {
                // get noCheckup
                noCheckup = rawatInapEntity.getNoCheckup();

                rawatInapEntity.setFlag("N");
                rawatInapEntity.setAction("U");
                rawatInapEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                rawatInapEntity.setLastUpdateWho(CommonUtil.userLogin());
                try {
                    rawatInapDao.updateAndSave(rawatInapEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error when Update data transaksi inap ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error when Update data transaksi inap " + e.getMessage());
                }

                if (rawatInapEntity.getIdRuangan() != null && !"".equalsIgnoreCase(rawatInapEntity.getIdRuangan())) {
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdRuangan(rawatInapEntity.getIdRuangan());
                    ruangan.setStatusRuangan("N");
                    List<MtSimrsRuanganEntity> ruanganEntities = getListEntityRuangan(ruangan);

                    if (!ruanganEntities.isEmpty() && ruanganEntities.size() > 0) {
                        for (MtSimrsRuanganEntity ruanganEntity : ruanganEntities) {
                            ruanganEntity.setStatusRuangan("Y");
                            ruanganEntity.setAction("U");
                            ruanganEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            ruanganEntity.setLastUpdateWho(CommonUtil.userLogin());

                            try {
                                ruanganDao.updateAndSave(ruanganEntity);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error when Update status master ruangan ", e);
                                throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error when Update status master ruangan " + e.getMessage());
                            }
                        }
                    }
                }
            }

            if (idRuangan != null && !"".equalsIgnoreCase(idRuangan) && idDetailCheckup != null && !"".equalsIgnoreCase(idRuangan) && noCheckup != null && !"".equalsIgnoreCase(noCheckup)) {
                // save new data to table rawat inap
                RawatInap rawatInapNew = new RawatInap();
                rawatInapNew.setIdDetailCheckup(idDetailCheckup);
                rawatInapNew.setIdRuangan(idRuangan);
                rawatInapNew.setNoCheckup(idDetailCheckup);
                rawatInapNew.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                rawatInapNew.setCreatedWho(CommonUtil.userLogin());
                saveRawatInap(rawatInapNew);
            }
        }
        logger.info("[CheckupDetailBoImpl.updateRuanganInap] End <<<<<<<<<");
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

            String cek1 = "";
            String cek2 = "";
            String cek3 = "";
            String cek4 = "";

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
                cek1 = "N";
            }

            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ItSimrsPeriksaLabEntity> entityPeriksaList = getListEntityPeriksaLab(periksaLab);
            if (entityPeriksaList.size() > 0) {
                for (ItSimrsPeriksaLabEntity entity : entityPeriksaList) {

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
            } else {
                cek2 = "N";
            }

            PermintaanResep permintaanResep = new PermintaanResep();
            permintaanResep.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ImSimrsPermintaanResepEntity> resepEntities = getListEntityResep(permintaanResep);
            if (resepEntities.size() > 0) {
                response.setStatus("success");
                response.setMessage("Berhasil mencari resep");
            } else {
                cek3 = "N";
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ItSimrsRawatInapEntity> rawatInapList = getListEntityRawatInap(rawatInap);
            ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
            if (rawatInapList.size() > 0) {

                rawatInapEntity = rawatInapList.get(0);

                if (rawatInapEntity != null) {

                    List<ItSimrsOrderGiziEntity> giziEntityList = new ArrayList<>();

                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_rawat_inap", rawatInapEntity.getIdRawatInap());
                    try {
                        giziEntityList = orderGiziDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[Found Error] search gizi" + e.getMessage());
                    }

                    if (giziEntityList.size() > 0) {
                        response.setStatus("success");
                        response.setMessage("Berhasil mencari gizi");
                    } else {
                        cek4 = "N";
                    }
                }

            } else {
                cek4 = "N";
            }

            if ("N".equalsIgnoreCase(cek1) && "N".equalsIgnoreCase(cek2) && "N".equalsIgnoreCase(cek3) && "N".equalsIgnoreCase(cek4)) {
                response.setStatus("error");
                response.setMessage("Tidak ada tindakan yang dilakukan, periksa lab, dan tidak ada order resep");
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

            ItSimrsHeaderDetailCheckupEntity entity = new ItSimrsHeaderDetailCheckupEntity();
            try {
                entity = checkupDetailDao.getById("idDetailCheckup", idDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search detail checkup " + e.getMessage());
            }

            if (entity.getNoCheckupOnline() != null && !"".equalsIgnoreCase(entity.getNoCheckupOnline())) {

                List<ItSimrsAntianOnlineEntity> onlineEntityList = new ArrayList<>();
                Map hsCriteria = new HashMap();

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
    }


    public void updateStatusBayarDetailCheckup(HeaderDetailCheckup bean) throws GeneralBOException {

        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = getListEntityByCriteria(bean);
        if (detailCheckupEntities.size() > 0) {
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.get(0);
            if (bean.getIdDetailCheckup().equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
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

    @Override
    public CheckResponse updateInvoiceBpjs(String idDetailCheckup, String invNumber) {

        CheckResponse response = new CheckResponse();

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = getListEntityByCriteria(detailCheckup);

        if (detailCheckupEntities.size()>0){
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.get(0);
            detailCheckupEntity.setInvoice(invNumber);
            try {
                checkupDetailDao.updateAndSave(detailCheckupEntity);
                response.setStatus("success");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. "+ e);
                logger.error("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.updateStatusBayarDetailCheckup] ERROR. ", e);
            }
        }

        return response;
    }

    @Override
    public ItSimrsHeaderDetailCheckupEntity getEntityDetailCheckupByIdDetail(String idDetailCheckup) throws GeneralBOException {
        if (!"".equalsIgnoreCase(idDetailCheckup)){
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", idDetailCheckup);

            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = new ArrayList<>();
            try {
                detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
            }

            if (detailCheckupEntities.size() > 0){
                return detailCheckupEntities.get(0);
            }
        }
        return new ItSimrsHeaderDetailCheckupEntity();
    }

    @Override
    public void saveUpdateNoJuran(HeaderDetailCheckup bean) throws GeneralBOException {
        if (bean != null){

            HeaderDetailCheckup detail = new HeaderDetailCheckup();
            detail.setIdDetailCheckup(bean.getIdDetailCheckup());

            List<ItSimrsHeaderDetailCheckupEntity> details = getListEntityByCriteria(detail);
            if (details.size() > 0){
                for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : details){
                    detailCheckupEntity.setAction(bean.getAction());
                    detailCheckupEntity.setNoJurnal(bean.getNoJurnal());
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                    }
                }
            }
        }
    }

    @Override
    public BigDecimal getSumJumlahTindakan(String idDetailCheckup, String ket) {
        return checkupDetailDao.getSumAllTarifTindakan(idDetailCheckup, ket);
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
        if (dokterTeamEntities.size() > 0){
            for (ItSimrsDokterTeamEntity dokterTeamEntity : dokterTeamEntities){

                hsCriteria = new HashMap();
                hsCriteria.put("id_dokter", dokterTeamEntity.getIdDokter());

                List<ImSimrsDokterEntity> dokterEntities = dokterDao.getByCriteria(hsCriteria);
                if (dokterEntities.size() > 0){
                    Dokter dokter;
                    for (ImSimrsDokterEntity dokterEntity : dokterEntities){
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
}
