package com.neurix.simrs.transaksi.diagnosarawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;

import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
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
    private CheckupDetailDao checkupDetailDao;

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
    public CrudResponse saveAdd(DiagnosaRawat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
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
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Error When "+e.getMessage());
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveEdit(DiagnosaRawat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        logger.info("[DiagnosaRawatBoImpl.saveEdit] Start >>>>>>>>>");

        if (bean != null){

            ItSimrsDiagnosaRawatEntity entity = null;

            try {
                entity = diagnosaRawatDao.getById("idDiagnosaRawat", bean.getIdDiagnosaRawat());
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Error When "+e.getMessage());
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
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Error When "+e.getMessage());
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit diagnosa ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveEdit] End <<<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse updateCoverBpjs(HeaderDetailCheckup bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
            try {
                detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error when "+e.getMessage());
            }

            if(detailCheckupEntity.getIdDetailCheckup() != null){
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                detailCheckupEntity.setKodeCbg(bean.getKodeCbg());

                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error when "+e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public Boolean cekDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        return diagnosaRawatDao.cekDiagnosa(bean);
    }

    @Override
    public ItSimrsDiagnosaRawatEntity getById(String id) throws GeneralBOException {
        ItSimrsDiagnosaRawatEntity diagnosaRawatEntity = new ItSimrsDiagnosaRawatEntity();
        try {
            diagnosaRawatEntity = diagnosaRawatDao.getById("idDiagnosaRawat", id);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return diagnosaRawatEntity;
    }

    @Override
    public List<ItSimrsDiagnosaRawatEntity> getListEntityDiagnosaRawat(DiagnosaRawat bean) throws GeneralBOException{
        logger.info("[DiagnosaRawatBoImpl.getListEntityDiagnosaRawat] Start >>>>>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getIdDiagnosaRawat() != null && !"".equalsIgnoreCase(bean.getIdDiagnosaRawat())){
            hsCriteria.put("id_diagnosa_rawat", bean.getIdDiagnosaRawat());
        }
        if (bean.getOrderLastUpdate() != null && !"".equalsIgnoreCase(bean.getOrderLastUpdate())){
            hsCriteria.put("order_last", bean.getOrderLastUpdate());
        }
        if (bean.getOrderCreated() != null && !"".equalsIgnoreCase(bean.getOrderCreated())){
            hsCriteria.put("order_created", bean.getOrderCreated());
        }
        if (bean.getOrderJenisDiagnosa() != null && !"".equalsIgnoreCase(bean.getOrderJenisDiagnosa())){
            hsCriteria.put("order_jenis_diagnosa", bean.getOrderJenisDiagnosa());
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

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }
}
