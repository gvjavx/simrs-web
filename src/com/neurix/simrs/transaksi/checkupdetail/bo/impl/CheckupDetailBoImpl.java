package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class CheckupDetailBoImpl extends CheckupModuls implements CheckupDetailBo{

    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(CheckupDetailBoImpl.class);

    private CheckupDetailDao checkupDetailDao;
    private DokterTeamDao dokterTeamDao;
    private HeaderCheckupDao headerCheckupDao;
    private TindakanRawatDao tindakanRawatDao;
    private PeriksaLabDao periksaLabDao;
    private PermintaanResepDao permintaanResepDao;

    @Override
    public List<HeaderDetailCheckup> getByCriteria(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getByCriteria] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = getListEntityByCriteria(bean);
            if (!detailCheckupEntityList.isEmpty()){
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
        if (bean != null){
           try {
               results = checkupDetailDao.getSearchRawatJalan(bean);
           } catch (HibernateException e){
               logger.error("[CheckupDetailBoImpl.getSearchRawatJalan] Error when get data detail checkup ",e);
               throw new GeneralBOException("Error when get data detail checkup"+e.getMessage());
           }
       }
        logger.info("[CheckupDetailBoImpl.getSearchRawatJalan] End <<<<<<<<");
        return results;
    }

    protected List<ItSimrsHeaderDetailCheckupEntity> getListEntityByCriteria(HeaderDetailCheckup bean) throws GeneralBOException{
        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        try {
            entityList = checkupDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    protected List<HeaderDetailCheckup> setToDetailCheckupTemplate(List<ItSimrsHeaderDetailCheckupEntity> entityList) throws GeneralBOException{
        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        HeaderDetailCheckup detailCheckup;
        for (ItSimrsHeaderDetailCheckupEntity entity : entityList){
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

            if (detailCheckup.getStatusPeriksa() != null && !"".equalsIgnoreCase(detailCheckup.getStatusPeriksa())){
                StatusPasien statusPasien = new StatusPasien();
                statusPasien.setIdStatusPasien(detailCheckup.getStatusPeriksa());
                detailCheckup.setStatusPeriksa(getListEntityStatusPasien(statusPasien).get(0).getKeterangan());
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
            List<ItSimrsRawatInapEntity> rawatInapEntitys = getListEntityRawatInap(rawatInap);

            ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
            if (!rawatInapEntitys.isEmpty()){
                rawatInapEntity = rawatInapEntitys.get(0);
            }

            if (rawatInapEntity != null){
                detailCheckup.setNoRuangan(rawatInapEntity.getNoRuangan());
                detailCheckup.setIdRuangan(rawatInapEntity.getIdRuangan());
                detailCheckup.setNamaRuangan(rawatInapEntity.getNamaRangan());
            }

            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(detailCheckup.getIdPelayanan());
            List<ImSimrsPelayananEntity> pelayananEntityList = getListEntityPelayanan(pelayanan);

            ImSimrsPelayananEntity pelayananEntity = new ImSimrsPelayananEntity();
            if (!pelayananEntityList.isEmpty()){
                pelayananEntity = pelayananEntityList.get(0);
            }
            if(pelayananEntity != null){
               detailCheckup.setNamaPelayanan(pelayananEntity.getNamaPelayanan());
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
        if (!detailCheckupEntityList.isEmpty()){
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

            try {
                checkupDetailDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup ",e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup "+e.getMessage());
            }

            // update tgl kluar if selesai
            if ("selesai".equalsIgnoreCase(bean.getStatus()))
            {
                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(entity.getNoCheckup());
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = getListEntityCheckup(headerCheckup);
                if (headerChekupEntities != null && headerChekupEntities.size() > 0)
                {
                    ItSimrsHeaderChekupEntity headerChekupEntity = headerChekupEntities.get(0);
                    headerChekupEntity.setLastUpdate(bean.getLastUpdate());
                    headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    headerChekupEntity.setAction("U");
                    headerChekupEntity.setTglKeluar(bean.getLastUpdate());

                    try {
                        headerCheckupDao.updateAndSave(headerChekupEntity);
                    } catch (HibernateException e){
                        logger.error("[CheckupDetailBoImpl.saveEdit] Error when update checkup ",e);
                        throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update checkup "+e.getMessage());
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

    private List<ItSimrsHeaderChekupEntity> getListEntityCheckup(HeaderCheckup bean) throws GeneralBOException{
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        List<ItSimrsHeaderChekupEntity> entities = null;

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());

        try {
            entities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.saveEdit] ERROR when get by criteria"+ e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] ERROR when get by criteria"+ e.getMessage());
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
        detailCheckupEntity.setIdDetailCheckup("DCM"+id);
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

        try {
            checkupDetailDao.addAndSave(detailCheckupEntity);
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup");
        }

        // saving dokter
        if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup()))
        {
            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            dokterTeam.setIdDokter(bean.getIdDokter());
            dokterTeam.setCreatedWho(bean.getCreatedWho());
            dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
            saveTeamDokter(dokterTeam);
        }

        // for rawat Inap
        if (bean.getRawatInap()){
            List<ItSimrsDokterTeamEntity> dokterEntities = null;
            Map hsCriteria = new HashMap();
            // set criteria with old detail id for get old dokter team data
            hsCriteria.put("id_detail_checkup",bean.getIdDetailCheckup());
            try {
                dokterEntities = dokterTeamDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team" + e.getMessage());
                throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team");
            }

            if (!dokterEntities.isEmpty()){
                for (ItSimrsDokterTeamEntity entity : dokterEntities){

                    DokterTeam dokterTeam = new DokterTeam();
                    dokterTeam.setIdTeamDokter("");

                    // set id detail with new id detail
                    dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    dokterTeam.setIdDokter(entity.getIdDokter());
                    dokterTeam.setCreatedWho(bean.getCreatedWho());
                    dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
                    saveTeamDokter(dokterTeam);

                }
            }

            // save to table rawat inap
            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            rawatInap.setIdRuangan(bean.getIdRuangan());
            rawatInap.setNoCheckup(bean.getNoCheckup());
            rawatInap.setCreatedDate(bean.getCreatedDate());
            rawatInap.setCreatedWho(bean.getCreatedWho());
            saveRawatInap(rawatInap);
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
    }

    private void saveTeamDokter(DokterTeam bean){
        logger.info("[CheckupDetailBoImpl.saveTeamDokter] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT"+id);
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
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team ",e);
            throw new GeneralBOException("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team "+e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.savaAdd] End <<<<<<<<");
    }

    private void saveRawatInap(RawatInap bean){
        logger.info("[CheckupDetailBoImpl.saveRawatInap] Start >>>>>>>>");
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup()) &&
                bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()))
        {
            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            MtSimrsRuanganEntity ruanganEntity = new MtSimrsRuanganEntity();

            // get data ruangan;
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(bean.getIdRuangan());

                List<MtSimrsRuanganEntity> listRuangan = getListEntityRuangan(ruangan);

                if (!listRuangan.isEmpty()){
                    ruanganEntity = listRuangan.get(0);

                    if (ruanganEntity != null){

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
            entity.setIdRawatInap("RNP"+id);
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
            } catch (HibernateException e){
                logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ",e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when save add  Rawat Inap "+e.getMessage());
            }

            // set ketersedian ruangan to tidak tersedia
            if (ruanganEntity != null){
                ruanganEntity.setStatusRuangan("N");
                ruanganEntity.setAction("U");
                ruanganEntity.setLastUpdate(bean.getCreatedDate());
                ruanganEntity.setLastUpdateWho(bean.getCreatedWho());
                try {
                    ruanganDao.updateAndSave(ruanganEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ",e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan "+e.getMessage());
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
        if (!rawatInapEntities.isEmpty() && rawatInapEntities.size() > 0)
        {
            for (ItSimrsRawatInapEntity rawatInapEntity : rawatInapEntities)
            {
                // get noCheckup
                noCheckup = rawatInapEntity.getNoCheckup();

                rawatInapEntity.setFlag("N");
                rawatInapEntity.setAction("U");
                rawatInapEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                rawatInapEntity.setLastUpdateWho(CommonUtil.userLogin());
                try {
                    rawatInapDao.updateAndSave(rawatInapEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error when Update data transaksi inap ",e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error when Update data transaksi inap "+e.getMessage());
                }

                if (rawatInapEntity.getIdRuangan() != null && !"".equalsIgnoreCase(rawatInapEntity.getIdRuangan()))
                {
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdRuangan(rawatInapEntity.getIdRuangan());
                    ruangan.setStatusRuangan("N");
                    List<MtSimrsRuanganEntity> ruanganEntities = getListEntityRuangan(ruangan);

                    if (!ruanganEntities.isEmpty() && ruanganEntities.size() > 0)
                    {
                        for (MtSimrsRuanganEntity ruanganEntity : ruanganEntities)
                        {
                            ruanganEntity.setStatusRuangan("Y");
                            ruanganEntity.setAction("U");
                            ruanganEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            ruanganEntity.setLastUpdateWho(CommonUtil.userLogin());

                            try {
                                ruanganDao.updateAndSave(ruanganEntity);
                            } catch (HibernateException e){
                                logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error when Update status master ruangan ",e);
                                throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error when Update status master ruangan "+e.getMessage());
                            }
                        }
                    }
                }
            }

            if (idRuangan != null && !"".equalsIgnoreCase(idRuangan) && idDetailCheckup != null && !"".equalsIgnoreCase(idRuangan) && noCheckup != null && !"".equalsIgnoreCase(noCheckup))
            {
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

    @Override
    public BigInteger getSumOfTindakanByNoCheckup(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getSumOfTindakanByNoCheckup] Start >>>>>>>>");

        BigInteger result = new BigInteger(String.valueOf(0));

        try {
            result = checkupDetailDao.sumOfTindakanByNoCheckup(noCheckup);
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.updateRuanganInap] Error ",e);
            throw new GeneralBOException("[CheckupDetailBoImpl.updateRuanganInap] Error "+e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getSumOfTindakanByNoCheckup] End <<<<<<<<");
        return result;
    }

    @Override
    public CheckResponse saveApproveAllTindakanRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveApproveAllTindakanRawatJalan] START <<<<<<<<");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            String cek1 = "";
            String cek2 = "";
            String cek3 = "";

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ItSimrsTindakanRawatEntity> entityTindakanList = getListEntityTindakanRawat(tindakanRawat);

            if(entityTindakanList.size() > 0){
                for (ItSimrsTindakanRawatEntity entity: entityTindakanList){
                    entity.setApproveFlag("Y");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        tindakanRawatDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil update tindakan");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Error when update tindakan, Found Eror: "+e.getMessage());
                        logger.error("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag ", e);
                        throw new GeneralBOException("[TindakanRawatBoImpl.updateFlagApproveTindakan] Error when update approve flag " + e.getMessage());
                    }
                }
            }else{
                cek1 = "N";
            }

            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ItSimrsPeriksaLabEntity> entityPeriksaList = getListEntityPeriksaLab(periksaLab);
            if(entityPeriksaList.size() > 0){
                for (ItSimrsPeriksaLabEntity entity: entityPeriksaList){

                    entity.setApproveFlag("Y");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        periksaLabDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMessage("Berhasil update periksa lab");
                    }catch (HibernateException e){
                        response.setMessage("error");
                        response.setMessage("Error when update periksa lab : "+e.getMessage());
                        logger.error("[PeriksaLabBoImpl.updateFlagApprovePeriksaLab] Error when update periksa lab ", e);
                        throw new GeneralBOException("Error when update periksa lab " + e.getMessage());
                    }
                }
            }else{
                cek2 = "N";
            }

            PermintaanResep permintaanResep = new PermintaanResep();
            permintaanResep.setIdDetailCheckup(bean.getIdDetailCheckup());
            List<ImSimrsPermintaanResepEntity> resepEntities = getListEntityResep(permintaanResep);
            if(resepEntities.size() > 0){
                response.setStatus("success");
                response.setMessage("Berhasil mencari resep");
            }else{
               cek3 = "Y";
            }

            if(cek1.equalsIgnoreCase("N") && cek2.equalsIgnoreCase("N") && cek3.equalsIgnoreCase("N")){
                response.setStatus("error");
                response.setMessage("Tidak ada tindakan yang dilakukan, periksa lab, dan tidak ada order resep");
            }
        }
        logger.info("[CheckupDetailBoImpl.saveApproveAllTindakanRawatJalan] END <<<<<<<<");
        return response;
    }

    protected List<ItSimrsTindakanRawatEntity> getListEntityTindakanRawat(TindakanRawat bean) throws GeneralBOException{
        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] Start >>>>>>>");
        List<ItSimrsTindakanRawatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdTindakanRawat() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawat())){
            hsCriteria.put("id_tindakan_rawat", bean.getIdTindakanRawat());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        hsCriteria.put("flag","Y");
        try {
            results = tindakanRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getListEntityTindakanRawat] Erro when searching tindakan rawat ", e);
        }

        logger.info("[TindakanRawatBoImpl.getListEntityTindakanRawat] End <<<<<<");
        return results;
    }

    private List<ItSimrsPeriksaLabEntity> getListEntityPeriksaLab(PeriksaLab bean) throws GeneralBOException{
        logger.info("[PeriksaLabBoImpl.getListEntityPeriksaLab] START >>>>>>>>> ");

        Map hsCriteria = new HashMap();
        if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab())){
            hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        }

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())){
            hsCriteria.put("status", bean.getStatusPeriksa());
        }

        hsCriteria.put("flag","Y");
        List<ItSimrsPeriksaLabEntity> periksaLabEntities = new ArrayList<>();
        try {
            periksaLabEntities = periksaLabDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab ", e);
            throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab "+ e.getCause());
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

    private String getNextDetailCheckupId(){
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.getNextDetailCheckupId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextTeamDokterId(){
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.getNextTeamDokterId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextTeamDokterId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextRawatInapId(){
        String id = "";
        try {
            id = rawatInapDao.getNextId();
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.getNextRawatInapId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextRawatInapId] Error When Error get next seq id");
        }
        return id;
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
}
