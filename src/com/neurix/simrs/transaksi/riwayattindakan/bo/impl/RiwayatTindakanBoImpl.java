package com.neurix.simrs.transaksi.riwayattindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.dao.TindakanTransitorisDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.impl.TeamDokterBoImpl;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiwayatTindakanBoImpl implements RiwayatTindakanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private TindakanTransitorisDao tindakanTransitorisDao;
    private ItemPaketDao itemPaketDao;

    public void setItemPaketDao(ItemPaketDao itemPaketDao) {
        this.itemPaketDao = itemPaketDao;
    }

    private static transient Logger logger = Logger.getLogger(RiwayatTindakanBoImpl.class);

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setTindakanTransitorisDao(TindakanTransitorisDao tindakanTransitorisDao) {
        this.tindakanTransitorisDao = tindakanTransitorisDao;
    }

    @Override
    public List<RiwayatTindakan> getByCriteria(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.getByCriteria] Start >>>>>>>>");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            List<ItSimrsRiwayatTindakanEntity> entityList = getListEntityRiwayatTindakan(bean);

            if(!entityList.isEmpty()){
                RiwayatTindakan riwayatTindakan;
                for (ItSimrsRiwayatTindakanEntity entity: entityList){
                    riwayatTindakan = new RiwayatTindakan();
                    riwayatTindakan.setIdRiwayatTindakan(entity.getIdRiwayatTindakan());
                    riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    riwayatTindakan.setIdTindakan(entity.getIdTindakan());
                    riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());
                    riwayatTindakan.setKeterangan(entity.getKeterangan());
                    riwayatTindakan.setTotalTarif(entity.getTotalTarif());
                    riwayatTindakan.setJenisPasien(entity.getJenisPasien());
                    riwayatTindakan.setKategoriTindakanBpjs(entity.getKategoriTindakanBpjs());
                    riwayatTindakan.setApproveBpjsFlag(entity.getApproveBpjsFlag());
                    riwayatTindakan.setAction(entity.getAction());
                    riwayatTindakan.setFlag(entity.getFlag());
                    riwayatTindakan.setCreatedDate(entity.getCreatedDate());
                    riwayatTindakan.setCreatedWho(entity.getCreatedWho());
                    riwayatTindakan.setLastUpdate(entity.getLastUpdate());
                    riwayatTindakan.setLastUpdateWho(entity.getLastUpdateWho());
                    riwayatTindakan.setFlagUpdateKlaim(entity.getFlagUpdateKlaim());
                    riwayatTindakan.setTanggalTindakan(entity.getTanggalTindakan());
                    result.add(riwayatTindakan);
                }
            }
        }
        logger.info("[TeamDokterBoImpl.getByCriteria] Start >>>>>>>>");
        return result;
    }

    @Override
    public void saveAdd(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[RiwayatTindakanBoImpl.saveAdd] Start >>>>>>>>");
        if(bean != null){
            ItSimrsRiwayatTindakanEntity entity = new ItSimrsRiwayatTindakanEntity();
            entity.setIdRiwayatTindakan("RWT"+getNextIdRiwayatTindakan());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setIdTindakan(bean.getIdTindakan());
            entity.setNamaTindakan(bean.getNamaTindakan());
            entity.setKeterangan(bean.getKeterangan());
            entity.setTotalTarif(bean.getTotalTarif());
            entity.setJenisPasien(bean.getJenisPasien());
            entity.setAction(bean.getAction());
            entity.setFlag(bean.getFlag());
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setTanggalTindakan(bean.getTanggalTindakan());
            if(bean.getIsKamar() != null && !"".equalsIgnoreCase(bean.getIsKamar())){
                entity.setIsKamar(bean.getIsKamar());
            }

            try {
                riwayatTindakanDao.addAndSave(entity);
            }catch (HibernateException e){
             logger.error("[RiwayatTindakanBoImpl.saveAdd] error when insert riwayat tindakan, Found error :["+e.getMessage()+"]");
            }
        }

        logger.info("[RiwayatTindakanBoImpl.saveAdd] Start >>>>>>>>");
    }

    @Override
    public void saveEdit(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[RiwayatTindakanBoImpl.saveAdd] Start >>>>>>>>");
        if(bean != null){
            List<ItSimrsRiwayatTindakanEntity> entityList = getListEntityRiwayatTindakan(bean);

            if(entityList.size() > 0){

                ItSimrsRiwayatTindakanEntity entity  = new ItSimrsRiwayatTindakanEntity();
                entity = entityList.get(0);

                entity.setTotalTarif(bean.getTotalTarif());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setAction("U");

                try {
                    riwayatTindakanDao.updateAndSave(entity);
                }catch (HibernateException e){
                    logger.error("[RiwayatTindakanBoImpl.saveAdd] error when insert riwayat tindakan, Found error :["+e.getMessage()+"]");
                }
            }
        }

        logger.info("[RiwayatTindakanBoImpl.saveAdd] Start >>>>>>>>");
    }

    @Override
    public List<RiwayatTindakan> cekTodayTarifKamar(String idDetail) throws GeneralBOException {
        logger.info("[RiwayatTindakanBoImpl.cekTodayTarifKamar] START >>>>>>>>");
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){
            try {
                riwayatTindakanList = riwayatTindakanDao.cekTodayTindakanTarifKamar(idDetail);
            }catch (HibernateException e) {
                logger.error("[RiwayatTindakanBoImpl.cekTodayTarifKamar] Error when get data riwayat tindakan");
            }
        }
        logger.info("[RiwayatTindakanBoImpl.cekTodayTarifKamar] END >>>>>>>>");
        return riwayatTindakanList;
    }

    @Override
    public List<ItSimrsRiwayatTindakanEntity> getListEntityRiwayatTindakan(RiwayatTindakan bean) {
        logger.info("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] Start >>>>>>>>");
        List<ItSimrsRiwayatTindakanEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean != null) {
            if (bean.getIdRiwayatTindakan() != null) {
                hsCriteria.put("id_riwayat_tindakan", bean.getIdRiwayatTindakan());
            }
            if (bean.getIdTindakan() != null) {
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdDetailCheckup() != null) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getApproveBpjsFlag() != null) {
                hsCriteria.put("approve_bpjs_flag", bean.getApproveBpjsFlag());
            }
            if (bean.getKeterangan() != null) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenisPasien() != null) {
                hsCriteria.put("jenis_pasien", bean.getJenisPasien());
            }
            if (bean.getNotResep() != null) {
                hsCriteria.put("not_resep", bean.getNotResep());
            }

            hsCriteria.put("flag", "Y");

            try {
                entities = riwayatTindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] Error when get data riwayat tindakan");
            }
        }

        logger.info("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] End <<<<<<<<");
        return entities;
    }

    @Override
    public void saveTindakanTransitoris(String idDetailCheckup, Timestamp time, String user) throws GeneralBOException {

        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);

        List<ItSimrsRiwayatTindakanEntity> tindakanEntities = getListEntityRiwayatTindakan(riwayatTindakan);
        if (tindakanEntities.size() > 0){
            for (ItSimrsRiwayatTindakanEntity tindakanEntity : tindakanEntities){

                ItSimrsTindakanTransitorisEntity transitorisEntity = new ItSimrsTindakanTransitorisEntity();
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                transitorisEntity.setNamaTindakan(tindakanEntity.getNamaTindakan());
                transitorisEntity.setKeterangan(tindakanEntity.getKeterangan());
                transitorisEntity.setJenisPasien(tindakanEntity.getJenisPasien());
                transitorisEntity.setTotalTarif(tindakanEntity.getTotalTarif());
                transitorisEntity.setKategoriTindakanBpjs(tindakanEntity.getKategoriTindakanBpjs());
                transitorisEntity.setApproveBpjsFlag(tindakanEntity.getApproveBpjsFlag());
                transitorisEntity.setAction(tindakanEntity.getAction());
                transitorisEntity.setFlag(tindakanEntity.getFlag());
                transitorisEntity.setTanggalTindakan(tindakanEntity.getTanggalTindakan());
                transitorisEntity.setIdDetailCheckup(idDetailCheckup);

                transitorisEntity.setCreatedDate(time);
                transitorisEntity.setCreatedWho(user);
                transitorisEntity.setLastUpdate(time);
                transitorisEntity.setLastUpdateWho(user);

                try {
                    tindakanTransitorisDao.addAndSave(transitorisEntity);
                } catch (HibernateException e){
                    logger.error("[RiwayatTindakanBoImpl.saveTindakanTransitoris] ERROR. ", e);
                    throw new GeneralBOException("[RiwayatTindakanBoImpl.saveTindakanTransitoris] ERROR. ", e);
                }
            }
        }
    }

    @Override
    public ItSimrsTindakanTransitorisEntity getTindakanTransitorisById(String id) throws GeneralBOException {
        return tindakanTransitorisDao.getById("idRiwayatTindakan", id);
    }

    @Override
    public List<RiwayatTindakan> typeaheadRiwayatTindakan(String riwayatTindakanName) throws GeneralBOException {
        logger.info("[KodeRekeningBoImpl.typeaheadKodeRekening] start process >>>");

        // Mapping with collection and put
        List<RiwayatTindakan> listOfResult = new ArrayList();
        List<ItSimrsRiwayatTindakanEntity> itSimrsRiwayatTindakanEntities = null;
        try {
            itSimrsRiwayatTindakanEntities = riwayatTindakanDao.getRiwayatTindakanListByLike(riwayatTindakanName);
        } catch (HibernateException e) {
            logger.error("[KodeRekeningBoImpl.typeaheadKodeRekening] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itSimrsRiwayatTindakanEntities != null){
            RiwayatTindakan riwayatTindakan;
            // Looping from dao to object and save in collection
            for(ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : itSimrsRiwayatTindakanEntities){
                riwayatTindakan = new RiwayatTindakan();
                riwayatTindakan.setIdTindakan(riwayatTindakanEntity.getIdTindakan());
                riwayatTindakan.setNamaTindakan(riwayatTindakanEntity.getNamaTindakan());
                listOfResult.add(riwayatTindakan);
            }
        }
        logger.info("[KodeRekeningBoImpl.typeaheadKodeRekening] end process <<<");

        return listOfResult;
    }

    @Override
    public void updateByEntity(ItSimrsRiwayatTindakanEntity entity) throws GeneralBOException {
        try {
            riwayatTindakanDao.updateAndSave(entity);
        } catch (HibernateException e){
            logger.error("[RiwayatTindakanBoImpl.updateByEntity] ERROR When update tindakan", e);
            throw new GeneralBOException("[RiwayatTindakanBoImpl.updateByEntity] ERROR When update tindakan", e);
        }
    }

    private String getNextIdRiwayatTindakan(){
        String id = "";
        try {
            id = riwayatTindakanDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    @Override
    public ItSimrsRiwayatTindakanEntity getRiwayatTindakanResep(String idDetail, String jenisPasien) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", idDetail);

        if (jenisPasien != null && !"".equalsIgnoreCase(jenisPasien)){
            hsCriteria.put("jenis_pasien", jenisPasien);
        }

        hsCriteria.put("keterangan", "resep");

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = new ArrayList<>();

        try {
            riwayatTindakanEntities = riwayatTindakanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RiwayatTindakanBoImpl.getRiwayatTindakanResep] ERROR. ", e);
            throw new GeneralBOException("[RiwayatTindakanBoImpl.getRiwayatTindakanResep] ERROR. ", e);
        }

        if (riwayatTindakanEntities.size() > 0){
            return riwayatTindakanEntities.get(0);
        }

        return new ItSimrsRiwayatTindakanEntity();
    }

    @Override
    public List<String> getListKeteranganByIdDetailCheckup(String idDetailCheckup) throws GeneralBOException {
        return riwayatTindakanDao.listOfKeteranganExistByIdDetailCheckup(idDetailCheckup);
    }

    @Override
    public MtSimrsItemPaketEntity getItemPaketEntity(String idPaket, String idItem) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_paket", idPaket);
        hsCriteria.put("id_item", idItem);

        List<MtSimrsItemPaketEntity> itemPaketEntities = new ArrayList<>();
        try {
            itemPaketEntities = itemPaketDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupDetailAction.getItemPaketEntity] ERROR. ", e);
            throw new GeneralBOException("[CheckupDetailAction.getItemPaketEntity] ERROR. "+ e.getMessage());
        }

        if (itemPaketEntities.size() > 0){
            return itemPaketEntities.get(0);
        }

        return null;
    }

    @Override
    public ItemPaket getTarifPaketLab(String idPaket, String idLab) throws GeneralBOException{
        return itemPaketDao.getSumTarifPaketLab(idPaket, idLab);
    }
}