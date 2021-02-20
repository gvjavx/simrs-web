package com.neurix.simrs.transaksi.reseponline.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kurir.dao.KurirDao;
import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.dao.PengirimanObatDao;
import com.neurix.simrs.transaksi.reseponline.dao.SettingHargaPengirimanDao;
import com.neurix.simrs.transaksi.reseponline.model.*;
import org.apache.log4j.Logger;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Wednesday, 17/06/20 9:55
 */
public class ResepOnlineBoImpl implements ResepOnlineBo {
    protected static transient Logger logger = Logger.getLogger(ResepOnlineBoImpl.class);

    private ResepOnlineDao resepOnlineDao;
    private PengirimanObatDao pengirimanObatDao;
    private PelayananDao pelayananDao;
    private PasienDao pasienDao;
    private BranchDao branchDao;
    private KurirDao kurirDao;
    private SettingHargaPengirimanDao settingHargaPengirimanDao;

    public void setSettingHargaPengirimanDao(SettingHargaPengirimanDao settingHargaPengirimanDao) {
        this.settingHargaPengirimanDao = settingHargaPengirimanDao;
    }

    public void setKurirDao(KurirDao kurirDao) {
        this.kurirDao = kurirDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setPengirimanObatDao(PengirimanObatDao pengirimanObatDao) {
        this.pengirimanObatDao = pengirimanObatDao;
    }

    public ResepOnlineDao getResepOnlineDao() {
        return resepOnlineDao;
    }

    public void setResepOnlineDao(ResepOnlineDao resepOnlineDao) {
        this.resepOnlineDao = resepOnlineDao;
    }

    @Override
    public List<ResepOnline> getByCriteria(ResepOnline bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getByCriteria] Start >>>>>>>");

        List<ResepOnline> resepOnlines = new ArrayList<>();
        if (bean != null) {
            List<ItSimrsResepOnlineEntity> itSimrsResepOnlineEntities = getEntityByCriteria(bean);

            if (!itSimrsResepOnlineEntities.isEmpty()) {
                resepOnlines = setTemplateResepOnline(itSimrsResepOnlineEntities);
            }
        }

        logger.info("[ResepOnlineBoImpl.getByCriteria] End >>>>>>>");
        return resepOnlines;
    }

    @Override
    public List<PengirimanObat> getListPengirimanObat(PengirimanObat bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getListPengirimanObat] Start >>>>>>>");

        List<ItSimrsPengirimanObatEntity> pengirimanObatEntities = new ArrayList<>();
        try {
            pengirimanObatEntities = getEntityPengirimanObat(bean);
        } catch (HibernateException e){
            logger.error("[ResepOnlineBoImpl.getListPengirimanObat] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.getListPengirimanObat] ERROR. ", e);
        }

        List<PengirimanObat> results = new ArrayList<>();
        if (pengirimanObatEntities.size() > 0){

            PengirimanObat pengirimanObat;
            for (ItSimrsPengirimanObatEntity obatEntity : pengirimanObatEntities){
                pengirimanObat = new PengirimanObat();
                pengirimanObat.setId(obatEntity.getId());
                pengirimanObat.setIdKurir(obatEntity.getIdKurir());
                pengirimanObat.setIdResep(obatEntity.getIdResep());
                pengirimanObat.setFlagPickup(obatEntity.getFlagPickup());
                pengirimanObat.setFlagDiterimaPasien(obatEntity.getFlagDiterimaPasien());
                pengirimanObat.setIdPasien(obatEntity.getIdPasien());
                pengirimanObat.setFlag(obatEntity.getFlag());
                pengirimanObat.setAction(obatEntity.getAction());
                pengirimanObat.setIdPelayanan(obatEntity.getIdPelayanan());
                pengirimanObat.setBranchId(obatEntity.getBranchId());
                pengirimanObat.setCreatedDate(obatEntity.getCreatedDate());
                pengirimanObat.setCreatedWho(obatEntity.getCreatedWho());
                pengirimanObat.setLastUpdate(obatEntity.getLastUpdate());
                pengirimanObat.setLastUpdateWho(obatEntity.getLastUpdateWho());
                pengirimanObat.setDesaId(obatEntity.getDesaId());
                pengirimanObat.setAlamat(obatEntity.getAlamat());
                pengirimanObat.setNoTelp(obatEntity.getNoTelp());
                pengirimanObat.setLat(obatEntity.getLat());
                pengirimanObat.setLon(obatEntity.getLon());

                //pengirimanObat.setDescOfLocation();

                if (obatEntity.getIdPelayanan() != null){
                    Pelayanan pelayananEntity = pelayananDao.getPelayananById("idPelayanan", obatEntity.getIdPelayanan());
                    pengirimanObat.setPelayananName(pelayananEntity.getNamaPelayanan() == null ? "" : pelayananEntity.getNamaPelayanan());
                }
                if (obatEntity.getIdPasien() != null){
                    ImSimrsPasienEntity pasienEntity = pasienDao.getById("idPasien", obatEntity.getIdPasien());
                    pengirimanObat.setPasienName(pasienEntity.getNama() == null ? "" : pasienEntity.getNama());
                }
                if (obatEntity.getIdKurir() != null){
                    ImSimrsKurirEntity kurirEntity = kurirDao.getById("idKurir", obatEntity.getIdKurir());
                    pengirimanObat.setKurirName(kurirEntity.getNama() == null ? "" : kurirEntity.getNama());
                }
                if (obatEntity.getBranchId() != null){
                    ImBranchesPK branchesPK = new ImBranchesPK();
                    branchesPK.setId(obatEntity.getBranchId());
                    ImBranches imBranches = branchDao.getById("primaryKey", branchesPK);
                    pengirimanObat.setBranchName(imBranches == null ? "" : imBranches.getBranchName());
                }
                results.add(pengirimanObat);
            }
        }

        logger.info("[ResepOnlineBoImpl.getListPengirimanObat] End <<<<<<<");
        return results;
    }

    @Override
    public List<PermintaanResep> getListResepTelemedic(String branchId) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getListResepTelemedic] Start >>>>>>>");
        logger.info("[ResepOnlineBoImpl.getListResepTelemedic] End <<<<<<<");
        return pengirimanObatDao.getListObatTelemedicApproved(branchId);
    }

    @Override
    public CrudResponse saveAddPengirimanObat(PengirimanObat bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.saveAddPengirimanObat] Start >>>>>>>");

        ItSimrsPengirimanObatEntity pengirimanObatEntity = new ItSimrsPengirimanObatEntity();
        pengirimanObatEntity.setId(getNextIdPengiriman(bean.getBranchId()));
        pengirimanObatEntity.setIdKurir(bean.getIdKurir());
        pengirimanObatEntity.setIdResep(bean.getIdResep());
        pengirimanObatEntity.setIdPelayanan(bean.getIdPelayanan());
        pengirimanObatEntity.setBranchId(bean.getBranchId());
        pengirimanObatEntity.setFlag("Y");
        pengirimanObatEntity.setAction("C");
        pengirimanObatEntity.setCreatedDate(bean.getCreatedDate());
        pengirimanObatEntity.setCreatedWho(bean.getCreatedWho());
        pengirimanObatEntity.setLastUpdate(bean.getLastUpdate());
        pengirimanObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
        pengirimanObatEntity.setIdPasien(bean.getIdPasien());
        pengirimanObatEntity.setLat(bean.getLat());
        pengirimanObatEntity.setLon(bean.getLon());
        pengirimanObatEntity.setAlamat(bean.getAlamat());
        pengirimanObatEntity.setNoTelp(bean.getNoTelp());

        ImSimrsPasienEntity pasienEntity = pasienDao.getById("idPasien", bean.getIdPasien());
        if (pasienEntity != null){
            pengirimanObatEntity.setDesaId(pasienEntity.getDesaId() == null ? "" : pasienEntity.getDesaId().toString());
        }

        CrudResponse response = new CrudResponse();

        try {
            pengirimanObatDao.addAndSave(pengirimanObatEntity);
            response.setStatus("success");
        } catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("[ResepOnlineBoImpl.saveAddPengirimanObat] ERROR. "+ e);
            logger.error("[ResepOnlineBoImpl.saveAddPengirimanObat] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.saveAddPengirimanObat] ERROR. ", e);
        }
        logger.info("[ResepOnlineBoImpl.saveAddPengirimanObat] End <<<<<<<");
        return response;
    }

    private List<ItSimrsPengirimanObatEntity> getEntityPengirimanObat(PengirimanObat bean) throws GeneralBOException{
        logger.info("[ResepOnlineBoImpl.getListPengirimanObat] Start >>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getFlagPickup() != null && !"".equalsIgnoreCase(bean.getFlagPickup()))
            hsCriteria.put("flag_pickup", bean.getFlagPickup());
        if (bean.getFlagDiterimaPasien() != null && !"".equalsIgnoreCase(bean.getFlagDiterimaPasien()))
            hsCriteria.put("flag_diterima", bean.getFlagDiterimaPasien());
        if (bean.getIdKurir() != null && !"".equalsIgnoreCase(bean.getIdKurir()))
            hsCriteria.put("id_kurir", bean.getIdKurir());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien()))
            hsCriteria.put("id_pasien", bean.getIdPasien());
        if (bean.getAlamat() != null && !"".equalsIgnoreCase(bean.getAlamat()))
            hsCriteria.put("alamat", bean.getAlamat());
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId()))
            hsCriteria.put("branch_id", bean.getBranchId());

        List<ItSimrsPengirimanObatEntity> pengirimanObatEntities = new ArrayList<>();
        try {
            pengirimanObatEntities = pengirimanObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ResepOnlineBoImpl.getEntityPengirimanObat] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.getEntityPengirimanObat] ERROR. ", e);
        }

        logger.info("[ResepOnlineBoImpl.getListPengirimanObat] End <<<<<<<");
        return pengirimanObatEntities;
    }

    public List<ItSimrsResepOnlineEntity> getEntityByCriteria(ResepOnline bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<ItSimrsResepOnlineEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
            hsCriteria.put("id", bean.getId());
        }
        if (bean.getIdTransaksiOnline() != null && !"".equalsIgnoreCase(bean.getIdTransaksiOnline())) {
            hsCriteria.put("id_transaksi_online", bean.getIdTransaksiOnline());
        }
        if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())) {
            hsCriteria.put("id_dokter", bean.getIdDokter());
        }

        List<ItSimrsResepOnlineEntity> itSimrsResepOnlineEntities = null;
        try {
            itSimrsResepOnlineEntities = resepOnlineDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e) {
            logger.error("[ResepOnlineBoImpl.getEntityByCriteria] Error when search entity by criteria " + e.getMessage());
        }

        if (!itSimrsResepOnlineEntities.isEmpty()) {
            results = itSimrsResepOnlineEntities;
        }

        logger.info("[ResepOnlineBoImpl.getEntityByCriteria] End >>>>>>>");
        return results;
    }

    public List<ResepOnline> setTemplateResepOnline(List<ItSimrsResepOnlineEntity> listEntity) {
        logger.info("[ResepOnlineBoImpl.setTemplateKurir] Start >>>>>>>");
        List<ResepOnline> list = new ArrayList<>();

        ResepOnline resepOnline;
        for (ItSimrsResepOnlineEntity data : listEntity) {
            resepOnline = new ResepOnline();
            resepOnline.setId(data.getId());
            resepOnline.setIdTransaksiOnline(data.getIdTransaksiOnline());
            resepOnline.setIdPelayanan(data.getIdPelayanan());
            resepOnline.setIdObat(data.getIdObat());
            resepOnline.setIdDokter(data.getIdDokter());
            resepOnline.setQty(data.getQty());
            resepOnline.setHarga(data.getHarga());
            resepOnline.setSubTotal(data.getSubTotal());
            resepOnline.setTtdDokter(data.getTtdDokter());
            resepOnline.setAction(data.getAction());
            resepOnline.setFlag(data.getFlag());
            resepOnline.setCreatedDate(data.getCreatedDate());
            resepOnline.setCreatedWho(data.getCreatedWho());
            resepOnline.setLastUpdate(data.getLastUpdate());
            resepOnline.setLastUpdateWho(data.getLastUpdateWho());
            resepOnline.setKeterangan(data.getKeterangan());

            list.add(resepOnline);
        }

        logger.info("[ResepOnlineBoImpl.setTemplateKurir] End >>>>>>>");
        return list;
    }

    private String getNextIdPengiriman(String branchId){
        logger.info("[ResepOnlineBoImpl.getNextIdPengiriman] END <<<");
        return branchId + CommonUtil.stDateSeq() + pengirimanObatDao.getNextSeq();
    }

    private ImBranches getBranchEntityBiId(String id) throws GeneralBOException{
        logger.info(" [ResepOnlineBoImpl.getBranchEntityBiId] START >>> END <<<< ");
        ImBranchesPK branchesPK = new ImBranchesPK();
        branchesPK.setId(id);
        return branchDao.getById("primaryKey", branchesPK);
    }

    @Override
    public List<SettingHargaPengiriman> getListResepOnlineByCriteria(SettingHargaPengiriman bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getListResepOnlineByCriteria] START >>>");

        List<SettingHargaPengiriman> settingHargaPengirimanList = new ArrayList<>();
        List<ImSimrsSettingHargaPengirimanEntity> settingHargaPengirimanEntities = new ArrayList<>();

        try {
            settingHargaPengirimanEntities = getListSettingHargaPengirimanEntity(bean);
        } catch (GeneralBOException e){
            logger.error("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] ERROR. " + e);
        }

        SettingHargaPengiriman settingHargaPengiriman;
        for (ImSimrsSettingHargaPengirimanEntity settingHargaPengirimanEntity : settingHargaPengirimanEntities){
            settingHargaPengiriman = new SettingHargaPengiriman();
            settingHargaPengiriman.setId(settingHargaPengirimanEntity.getId());
            settingHargaPengiriman.setTipe(settingHargaPengirimanEntity.getTipe());
            settingHargaPengiriman.setBranchId(settingHargaPengirimanEntity.getBranchId());
            settingHargaPengiriman.setFlag(settingHargaPengirimanEntity.getFlag());
            settingHargaPengiriman.setAction(settingHargaPengirimanEntity.getAction());
            settingHargaPengiriman.setCreatedDate(settingHargaPengirimanEntity.getCreatedDate());
            settingHargaPengiriman.setCreatedWho(settingHargaPengirimanEntity.getCreatedWho());
            settingHargaPengiriman.setLastUpdate(settingHargaPengirimanEntity.getLastUpdate());
            settingHargaPengiriman.setLastUpdateWho(settingHargaPengirimanEntity.getLastUpdateWho());

            ImBranches branchesEntity = getBranchEntityBiId(settingHargaPengirimanEntity.getBranchId());
            if (branchesEntity != null)
                settingHargaPengiriman.setBranchName(branchesEntity.getBranchName());

            settingHargaPengirimanList.add(settingHargaPengiriman);
        }

        logger.info("[ResepOnlineBoImpl.getListResepOnlineByCriteria] END <<<");
        return settingHargaPengirimanList;
    }

    private List<ImSimrsSettingHargaPengirimanEntity> getListSettingHargaPengirimanEntity(SettingHargaPengiriman bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId()))
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getTipe() != null && !"".equalsIgnoreCase(bean.getTipe()))
            hsCriteria.put("tipe", bean.getTipe());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        List<ImSimrsSettingHargaPengirimanEntity> settingHargaPengirimanEntities = new ArrayList<>();

        try {
            settingHargaPengirimanEntities = settingHargaPengirimanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] ERROR. " + e);
        }

        logger.info("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] END <<<");
        return settingHargaPengirimanEntities;
    }

    private ImSimrsSettingHargaPengirimanEntity getSettingHargaPengirimanEntityById(String id) throws GeneralBOException{
        logger.info("[ResepOnlineBoImpl.getSettingHargaPengirimanEntity] START >>> END <<<<");
        return settingHargaPengirimanDao.getById("id", id);
    }

    @Override
    public void saveAddSettingHargaPengiriman(SettingHargaPengiriman bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] START >>>");

        SettingHargaPengiriman settingHargaPengiriman = new SettingHargaPengiriman();
        settingHargaPengiriman.setBranchId(bean.getBranchId());
        settingHargaPengiriman.setTipe(bean.getTipe());

        List<ImSimrsSettingHargaPengirimanEntity> hargaPengirimanEntities = getListSettingHargaPengirimanEntity(settingHargaPengiriman);
        if (hargaPengirimanEntities.size() > 0){
            ImSimrsSettingHargaPengirimanEntity pengirimanEntity = hargaPengirimanEntities.get(0);
            logger.error("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] Data sudah ada pada ID" + pengirimanEntity.getId());
            throw new GeneralBOException("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] Data sudah ada pada ID. " + pengirimanEntity.getId());
        }

        ImSimrsSettingHargaPengirimanEntity settingHargaPengirimanEntity = new ImSimrsSettingHargaPengirimanEntity();
        settingHargaPengirimanEntity.setId(getNextIdSettingHargaPengiriman(bean.getBranchId()));
        settingHargaPengirimanEntity.setBranchId(bean.getBranchId());
        settingHargaPengirimanEntity.setTipe(bean.getTipe());
        settingHargaPengirimanEntity.setFlag(bean.getFlag());
        settingHargaPengirimanEntity.setAction(bean.getAction());
        settingHargaPengirimanEntity.setCreatedDate(bean.getCreatedDate());
        settingHargaPengirimanEntity.setCreatedWho(bean.getCreatedWho());
        settingHargaPengirimanEntity.setLastUpdate(bean.getLastUpdate());
        settingHargaPengirimanEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            settingHargaPengirimanDao.addAndSave(settingHargaPengirimanEntity);
        } catch (HibernateException e){
            logger.error("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] ERROR. " + e);
        }
        logger.info("[ResepOnlineBoImpl.saveAddSettingHargaPengiriman] END <<<");
    }

    @Override
    public void saveEditSettingHargaPengiriman(SettingHargaPengiriman bean) throws GeneralBOException {
        logger.info("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] START >>>");

        SettingHargaPengiriman settingHargaPengiriman = new SettingHargaPengiriman();
        settingHargaPengiriman.setBranchId(bean.getBranchId());
        settingHargaPengiriman.setTipe(bean.getTipe());

        List<ImSimrsSettingHargaPengirimanEntity> hargaPengirimanEntities = getListSettingHargaPengirimanEntity(settingHargaPengiriman);
        if (hargaPengirimanEntities.size() > 0){
            ImSimrsSettingHargaPengirimanEntity pengirimanEntity = hargaPengirimanEntities.get(0);
            logger.error("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] Data sudah ada pada ID" + pengirimanEntity.getId());
            throw new GeneralBOException("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] Data sudah ada pada ID. " + pengirimanEntity.getId());
        }

        ImSimrsSettingHargaPengirimanEntity settingHargaPengirimanEntity = getSettingHargaPengirimanEntityById(bean.getId());
        settingHargaPengirimanEntity.setBranchId(bean.getBranchId() == null ? settingHargaPengirimanEntity.getBranchId() : bean.getBranchId());
        settingHargaPengirimanEntity.setTipe(bean.getTipe() == null ? settingHargaPengirimanEntity.getTipe() : bean.getTipe());
        settingHargaPengirimanEntity.setFlag(bean.getFlag());
        settingHargaPengirimanEntity.setAction(bean.getAction());
        settingHargaPengirimanEntity.setCreatedDate(bean.getCreatedDate());
        settingHargaPengirimanEntity.setCreatedWho(bean.getCreatedWho());
        settingHargaPengirimanEntity.setLastUpdate(bean.getLastUpdate());
        settingHargaPengirimanEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            settingHargaPengirimanDao.addAndSave(settingHargaPengirimanEntity);
        } catch (HibernateException e){
            logger.error("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] ERROR. ", e);
            throw new GeneralBOException("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] ERROR. " + e);
        }
        logger.info("[ResepOnlineBoImpl.saveEditSettingHargaPengiriman] END <<<");
    }

    private String getNextIdSettingHargaPengiriman(String branchId) throws GeneralBOException{
        return "STH" + branchId + settingHargaPengirimanDao.getNextId();
    }
}
