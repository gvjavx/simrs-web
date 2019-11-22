package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
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
    private RuanganDao ruanganDao;

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
//            entity.setStatusBayar(bean.getStatusBayar());
//            entity.setTotalBiaya(bean.getTotalBiaya());
            entity.setKeteranganSelesai(bean.getKeteranganSelesai());
            entity.setJenisLab(bean.getJenisLab());
            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                checkupDetailDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup ",e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveEdit] Error when update detail checkup "+e.getMessage());
            }
        }
        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
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

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            rawatInap.setNoCheckup(bean.getNoCheckup());
            rawatInap.setCreatedDate(bean.getCreatedDate());
            rawatInap.setCreatedWho(bean.getCreatedWho());
            saveRawatInap(rawatInap);
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
    }

    private void saveTeamDokter(DokterTeam bean){
        logger.info("[CheckupDetailBoImpl.savaAdd] Start >>>>>>>>");

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
            String id = getNextRawatInapId();

            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            entity.setIdRawatInap("RNP"+id);
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setNoCheckup(bean.getNoCheckup());
            entity.setIdRuangan("01");
            entity.setNamaRangan("MAWAR MELATI");
            entity.setNoRuangan("3");
            entity.setTarif(new BigInteger("200000"));
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
        }
        logger.info("[CheckupDetailBoImpl.saveRawatInap] End <<<<<<<<");
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
}
