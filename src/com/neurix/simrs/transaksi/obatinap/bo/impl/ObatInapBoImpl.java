package com.neurix.simrs.transaksi.obatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.transaksi.obatinap.bo.ObatInapBo;
import com.neurix.simrs.transaksi.obatinap.dao.ObatInapDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatInapBoImpl implements ObatInapBo {
    private static transient Logger logger = Logger.getLogger(ObatInapBoImpl.class);
    private ObatInapDao obatInapDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setObatInapDao(ObatInapDao obatInapDao) {
        this.obatInapDao = obatInapDao;
    }

    @Override
    public List<ObatInap> getByCriteria(ObatInap bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.getByCriteria] Start >>>>>>>");

        List<ObatInap> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsObatInapEntity> obatInapEntities = getListEntity(bean);
            if (!obatInapEntities.isEmpty()){
                results = setToTemplate(obatInapEntities);
            }
        }

        logger.info("[OrderGiziBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(ObatInap bean) throws GeneralBOException {
        logger.info("[OrderGiziBoImpl.saveAdd] Start >>>>>>>");

        if (bean != null){
            String id = getNextId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsObatInapEntity obatInapEntity = new ItSimrsObatInapEntity();
                obatInapEntity.setIdObatInap("OBI" + id);
                obatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                obatInapEntity.setIdObat(bean.getIdObat());
                obatInapEntity.setNamaObat(bean.getNamaObat());
                obatInapEntity.setHarga(bean.getHarga());
                obatInapEntity.setQty(bean.getQty());
                obatInapEntity.setTotalHarga(bean.getTotalHarga());
                obatInapEntity.setFlag("Y");
                obatInapEntity.setAction("C");
                obatInapEntity.setCreatedDate(bean.getCreatedDate());
                obatInapEntity.setCreatedWho(bean.getCreatedWho());
                obatInapEntity.setLastUpdate(bean.getLastUpdate());
                obatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    obatInapDao.addAndSave(obatInapEntity);
                } catch (HibernateException e) {
                    logger.error("[OrderGiziBoImpl.saveAdd] Error when insert obat inap ", e);
                    throw new GeneralBOException("[TindakanRawatBoImpl.saveAdd] Error when insert obat inap " + e.getMessage());
                }
            }
        }
        // update total biaya pada di detail checkup
        // updateDetailCheckup(bean);
        logger.info("[OrderGiziBoImpl.saveAdd] End <<<<<<");
    }

    public String getNextId(){
        logger.info("[OrderGiziBoImpl.getNextId] Start >>>>>>>");

        String id = "";
        try {
            id = obatInapDao.getNextId();
        } catch (HibernateException e){
            logger.error("[OrderGiziBoImpl.getNextId] Error when get next id obat inap");
        }

        logger.info("[OrderGiziBoImpl.getNextId] End <<<<<<");
        return id;
    }

    protected List<ItSimrsObatInapEntity> getListEntity(ObatInap bean) throws GeneralBOException{
        logger.info("[OrderGiziBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsObatInapEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObatInap() != null && !"".equalsIgnoreCase(bean.getIdObatInap())){
            hsCriteria.put("id_obat_inap", bean.getIdObatInap());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            hsCriteria.put("id_obat", bean.getIdObat());
        }

        hsCriteria.put("flag","Y");
        try {
            results = obatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[OrderGiziBoImpl.getListEntity] Erro when searching data obat inap ", e);
        }

        logger.info("[OrderGiziBoImpl.getListEntityT] End <<<<<<");
        return results;
    }

    protected List<ObatInap> setToTemplate(List<ItSimrsObatInapEntity> entities) throws GeneralBOException{
        logger.info("[OrderGiziBoImpl.setToTemplate] Start >>>>>>>");
        List<ObatInap> results = new ArrayList<>();

        ObatInap obatInap;
        for (ItSimrsObatInapEntity entity : entities){

            obatInap = new ObatInap();
            obatInap.setIdObatInap(entity.getIdObatInap());
            obatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
            obatInap.setIdObat(entity.getIdObat());
            obatInap.setNamaObat(entity.getNamaObat());
            obatInap.setHarga(entity.getHarga());
            obatInap.setQty(entity.getQty());
            obatInap.setTotalHarga(entity.getTotalHarga());
            obatInap.setFlag(entity.getFlag());
            obatInap.setAction(entity.getAction());
            obatInap.setCreatedDate(entity.getCreatedDate());
            obatInap.setCreatedWho(entity.getCreatedWho());
            obatInap.setLastUpdate(entity.getLastUpdate());
            obatInap.setLastUpdateWho(entity.getLastUpdateWho());


            results.add(obatInap);
        }
        logger.info("[OrderGiziBoImpl.setToTemplate] End <<<<<<");
        return results;
    }
}