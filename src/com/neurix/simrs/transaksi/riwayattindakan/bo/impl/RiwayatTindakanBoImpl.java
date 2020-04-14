package com.neurix.simrs.transaksi.riwayattindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.impl.TeamDokterBoImpl;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiwayatTindakanBoImpl implements RiwayatTindakanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private static transient Logger logger = Logger.getLogger(RiwayatTindakanBoImpl.class);

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
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

    private List<ItSimrsRiwayatTindakanEntity> getListEntityRiwayatTindakan(RiwayatTindakan bean) {
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
    public void saveTindakanTransitoris(String idDetailCheckup) throws GeneralBOException {

        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);

        List<ItSimrsRiwayatTindakanEntity> tindakanEntities = getListEntityRiwayatTindakan(riwayatTindakan);
        if (tindakanEntities.size() > 0){
            for (ItSimrsRiwayatTindakanEntity tindakanEntity : tindakanEntities){

                ItSimrsTindakanTransitorisEntity transitorisEntity = new ItSimrsTindakanTransitorisEntity();
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());

            }
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
}