package com.neurix.simrs.transaksi.diagnosarawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;

import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 18/11/2019.
 */
public class DiagnosaRawatBoImpl implements DiagnosaRawatBo {
    private static transient Logger logger = Logger.getLogger(DiagnosaRawatBoImpl.class);
    private DiagnosaRawatDao diagnosaRawatDao;

    @Override
    public List<DiagnosaRawat> getByCriteria(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.getByCriteria] Start >>>>>>>>>");

        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();
        if (bean != null){
            List<ItSimrsDiagnosaRawatEntity> entities = getListEntityDiagnosaRawat(bean);
            if (!entities.isEmpty()){
                DiagnosaRawat diagnosaRawat;
                for (ItSimrsDiagnosaRawatEntity entity : entities){
                    diagnosaRawat = new DiagnosaRawat();
                    diagnosaRawat.setIdDiagnosaRawat(entity.getIdDiagnosaRawat());
                    diagnosaRawat.setIdDetailCheckup(entity.getIdDetailCheckup());
                    diagnosaRawat.setIdDiagnosa(entity.getIdDiagnosa());
                    diagnosaRawat.setJenisDiagnosa(entity.getJenisDiagnosa());
                    diagnosaRawat.setKeteranganDiagnosa(entity.getKeteranganDiagnosa());
                    diagnosaRawat.setFlag(entity.getFlag());
                    diagnosaRawat.setAction(entity.getAction());
                    diagnosaRawat.setCreatedDate(entity.getCreatedDate());
                    diagnosaRawat.setCreatedWho(entity.getCreatedWho());
                    diagnosaRawat.setLastUpdate(entity.getLastUpdate());
                    diagnosaRawat.setLastUpdateWho(entity.getLastUpdateWho());
                    diagnosaRawatList.add(diagnosaRawat);
                }
            }
        }

        logger.info("[DiagnosaRawatBoImpl.getByCriteria] End <<<<<<<<<");
        return diagnosaRawatList;

    }

    @Override
    public void saveAdd(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>>>");

        if (bean != null && bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

            String id = getNextId();
            entity.setIdDiagnosaRawat("DGR"+id);
            entity.setIdDiagnosa(bean.getIdDiagnosa());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
            entity.setJenisDiagnosa(bean.getJenisDiagnosa());
            entity.setTipe(bean.getTipe());
            entity.setFlag("Y");
            entity.setAction("U");
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                diagnosaRawatDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
    }

    @Override
    public void saveEdit(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveEdit] Start >>>>>>>>>");

        if (bean != null){

            ItSimrsDiagnosaRawatEntity entity = null;

            try {
                entity = diagnosaRawatDao.getById("idDiagnosaRawat", bean.getIdDiagnosaRawat());
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById diagnosa rawat ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit diagnosa rawat "+e.getMessage());
            }
            if(entity != null){
                entity.setIdDiagnosa(bean.getIdDiagnosa());
                entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
                entity.setJenisDiagnosa(bean.getJenisDiagnosa());
                entity.setTipe(bean.getTipe());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
            }

            try {
                diagnosaRawatDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveEdit] End <<<<<<<<<");
    }

    protected List<ItSimrsDiagnosaRawatEntity> getListEntityDiagnosaRawat(DiagnosaRawat bean) throws GeneralBOException{
        logger.info("[DiagnosaRawatBoImpl.getListEntityDiagnosaRawat] Start >>>>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getIdDiagnosaRawat() != null && !"".equalsIgnoreCase(bean.getIdDiagnosaRawat())){
            hsCriteria.put("id_diagnosa_rawat", bean.getIdDiagnosaRawat());

        }

        List<ItSimrsDiagnosaRawatEntity> entities = new ArrayList<>();
        try {
            entities = diagnosaRawatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[DiagnosaRawatBoImpl.getListEntityDiagnosaRawat] Error when get data diagnosa rawat entity by criteria ", e);
        }

        logger.info("[DiagnosaRawatBoImpl.getListEntityDiagnosaRawat] End <<<<<<<<<");
        return entities;
    }

    private String getNextId(){
        String id = "";
        try {
            id = diagnosaRawatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[DiagnosaRawatBoImpl.getNextId] Error when get next diagnosa rawat id ", e);
        }
        return id;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;

    }
}
