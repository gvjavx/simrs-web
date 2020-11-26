package com.neurix.simrs.transaksi.tindakanrawaticd9.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.tindakanrawaticd9.bo.TindakanRawatICD9Bo;
import com.neurix.simrs.transaksi.tindakanrawaticd9.dao.TindakanRawatICD9Dao;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.ItSimrsTindakanRawatICD9Entity;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.TindakanRawatICD9;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TindakanRawatICD9BoImpl implements TindakanRawatICD9Bo {

    private static transient Logger logger = Logger.getLogger(TindakanRawatICD9BoImpl.class);
    private TindakanRawatICD9Dao tindakanRawatICD9Dao;

    @Override
    public List<TindakanRawatICD9> getByCriteria(TindakanRawatICD9 bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.getByCriteria] Start >>>>>>>");
        List<TindakanRawatICD9> results = new ArrayList<>();
        List<ItSimrsTindakanRawatICD9Entity> entityArrayList = new ArrayList<>();

        if (bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdTindakanRawatIcd9() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawatIcd9())){
                hsCriteria.put("id_tindakan_rawat_icd9", bean.getIdTindakanRawatIcd9());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getIdIcd9() != null && !"".equalsIgnoreCase(bean.getIdIcd9())){
                hsCriteria.put("id_icd9", bean.getIdIcd9());
            }
            if (bean.getNamaIcd9() != null && !"".equalsIgnoreCase(bean.getNamaIcd9())){
                hsCriteria.put("nama_icd9", bean.getNamaIcd9());
            }
            hsCriteria.put("flag","Y");

            try {
                entityArrayList = tindakanRawatICD9Dao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[TindakanRawatBoImpl.getListEntityTindakanRawat] Erro when searching tindakan rawat ", e);
            }

            if(entityArrayList.size() > 0){
                for (ItSimrsTindakanRawatICD9Entity entity: entityArrayList){
                    TindakanRawatICD9 tindakanRawatICD9 = new TindakanRawatICD9();
                    tindakanRawatICD9.setIdTindakanRawatIcd9(entity.getIdTindakanRawatIcd9());
                    tindakanRawatICD9.setIdIcd9(entity.getIdIcd9());
                    tindakanRawatICD9.setNamaIcd9(entity.getNamaIcd9());
                    tindakanRawatICD9.setIdDetailCheckup(entity.getIdDetailCheckup());
                    tindakanRawatICD9.setFlag(entity.getFlag());
                    tindakanRawatICD9.setAction(entity.getAction());
                    tindakanRawatICD9.setCreatedDate(entity.getCreatedDate());
                    tindakanRawatICD9.setCreatedWho(entity.getCreatedWho());
                    tindakanRawatICD9.setLastUpdate(entity.getLastUpdate());
                    tindakanRawatICD9.setLastUpdateWho(entity.getLastUpdateWho());
                    results.add(tindakanRawatICD9);
                }
            }
        }

        logger.info("[TindakanRawatBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public CrudResponse saveAdd(TindakanRawatICD9 bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.saveAdd] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean != null ){
            String id = getNextTindakanRawatId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsTindakanRawatICD9Entity tindakanRawatEntity = new ItSimrsTindakanRawatICD9Entity();
                tindakanRawatEntity.setIdTindakanRawatIcd9("TIC" + id);
                tindakanRawatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                tindakanRawatEntity.setIdIcd9(bean.getIdIcd9());
                tindakanRawatEntity.setNamaIcd9(bean.getNamaIcd9());
                tindakanRawatEntity.setFlag(bean.getFlag());
                tindakanRawatEntity.setAction(bean.getAction());
                tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                tindakanRawatEntity.setLastUpdate(bean.getLastUpdate());
                tindakanRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    tindakanRawatICD9Dao.addAndSave(tindakanRawatEntity);
                    response.setMsg("Berhasil");
                    response.setStatus("success");
                } catch (HibernateException e) {
                    response.setMsg("Found Error When "+e.getMessage());
                    response.setStatus("error");
                    logger.error("[TindakanRawatBoImpl.saveAdd] Error when insert tindakan rawat ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when insert tindakan rawat " + e.getMessage());
                }
            }
        }
        logger.info("[TindakanRawatBoImpl.saveAdd] End <<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveEdit(TindakanRawatICD9 bean) throws GeneralBOException {
        logger.info("[TindakanRawatBoImpl.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean != null && bean.getIdTindakanRawatIcd9() != null && !"".equalsIgnoreCase(bean.getIdTindakanRawatIcd9())){

            TindakanRawatICD9 tindakanRawatICD9 = new TindakanRawatICD9();
            tindakanRawatICD9.setIdTindakanRawatIcd9(bean.getIdTindakanRawatIcd9());

            ItSimrsTindakanRawatICD9Entity tindakanRawatEntity = tindakanRawatICD9Dao.getById("idTindakanRawatIcd9", bean.getIdTindakanRawatIcd9());

            if (tindakanRawatEntity.getIdTindakanRawatIcd9() != null){

                tindakanRawatEntity.setIdIcd9(bean.getIdIcd9());
                tindakanRawatEntity.setNamaIcd9(bean.getNamaIcd9());
                tindakanRawatEntity.setAction(bean.getAction());
                tindakanRawatEntity.setLastUpdate(bean.getLastUpdate());
                tindakanRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    tindakanRawatICD9Dao.updateAndSave(tindakanRawatEntity);
                    response.setMsg("Berhasil");
                    response.setStatus("success");
                }catch (HibernateException e){
                    response.setMsg("Found Error When "+e.getMessage());
                    response.setStatus("error");
                    logger.error("[TindakanRawatBoImpl.saveAdd] Error when update tindakan rawat ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when update tindakan rawat " + e.getMessage());
                }
            }
        }
        logger.info("[TindakanRawatBoImpl.saveEdit] End <<<<<<");
        return response;
    }

    public String getNextTindakanRawatId(){
        logger.info("[TindakanRawatBoImpl.getNextTindakanRawatId] Start >>>>>>>");

        String id = "";
        try {
            id = tindakanRawatICD9Dao.getNextTindakanRawatId();
        } catch (HibernateException e){
            logger.error("[TindakanRawatBoImpl.getNextTindakanRawatId] Error when get next id tindakan rawat");
        }

        logger.info("[TindakanRawatBoImpl.getNextTindakanRawatId] End <<<<<<");
        return id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setTindakanRawatICD9Dao(TindakanRawatICD9Dao tindakanRawatICD9Dao) {
        this.tindakanRawatICD9Dao = tindakanRawatICD9Dao;
    }
}
