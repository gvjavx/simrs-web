package com.neurix.simrs.transaksi.asesmenrawatinap.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.ImplementasiPerawatBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.dao.ImplementasiPerawatDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ImplementasiPerawat;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsImplementasiPerawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementasiPerawatBoImpl implements ImplementasiPerawatBo {
    private static transient Logger logger = Logger.getLogger(ImplementasiPerawatBoImpl.class);
    private ImplementasiPerawatDao implementasiPerawatDao;

    @Override
    public List<ImplementasiPerawat> getByCriteria(ImplementasiPerawat bean) throws GeneralBOException {
        List<ImplementasiPerawat> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdImplementasiPerawat() != null && !"".equalsIgnoreCase(bean.getIdImplementasiPerawat())) {
                hsCriteria.put("id_implementasi_perawat", bean.getIdImplementasiPerawat());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getWaktu() != null && !"".equalsIgnoreCase(bean.getWaktu())) {
                hsCriteria.put("waktu", bean.getWaktu());
            }

            List<ItSimrsImplementasiPerawatEntity> entityList = new ArrayList<>();

            try {
                entityList = implementasiPerawatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsImplementasiPerawatEntity entity : entityList) {
                    ImplementasiPerawat implementasiPerawat = new ImplementasiPerawat();
                    implementasiPerawat.setIdImplementasiPerawat(entity.getIdImplementasiPerawat());
                    implementasiPerawat.setIdDetailCheckup(entity.getIdDetailCheckup());
                    implementasiPerawat.setWaktu(entity.getWaktu());
                    implementasiPerawat.setTtd(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtd());
                    implementasiPerawat.setNamaTerang(entity.getNamaTerang());
                    implementasiPerawat.setKeterangan(entity.getKeterangan());
                    implementasiPerawat.setAction(entity.getAction());
                    implementasiPerawat.setFlag(entity.getFlag());
                    implementasiPerawat.setCreatedDate(entity.getCreatedDate());
                    implementasiPerawat.setCreatedWho(entity.getCreatedWho());
                    implementasiPerawat.setLastUpdate(entity.getLastUpdate());
                    implementasiPerawat.setLastUpdateWho(entity.getLastUpdateWho());
                    implementasiPerawat.setSip(entity.getSip());
                    list.add(implementasiPerawat);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(ImplementasiPerawat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            List<ImplementasiPerawat> implementasiPerawats = getByCriteria(bean);
            if(implementasiPerawats.size() > 0){
                response.setStatus("error");
                response.setMsg("Found error, Data yang anda masukan sudah ada...!");
            }else{
                ItSimrsImplementasiPerawatEntity implementasiPerawatEntity = new ItSimrsImplementasiPerawatEntity();
                implementasiPerawatEntity.setIdImplementasiPerawat("IMP" + implementasiPerawatDao.getNextSeq());
                implementasiPerawatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                implementasiPerawatEntity.setWaktu(bean.getWaktu());
                implementasiPerawatEntity.setKeterangan(bean.getKeterangan());
                implementasiPerawatEntity.setAction(bean.getAction());
                implementasiPerawatEntity.setFlag(bean.getFlag());
                implementasiPerawatEntity.setCreatedDate(bean.getCreatedDate());
                implementasiPerawatEntity.setCreatedWho(bean.getCreatedWho());
                implementasiPerawatEntity.setLastUpdate(bean.getLastUpdate());
                implementasiPerawatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                implementasiPerawatEntity.setNamaTerang(bean.getNamaTerang());
                implementasiPerawatEntity.setSip(bean.getSip());
                implementasiPerawatEntity.setTtd(bean.getTtd());

                try {
                    implementasiPerawatDao.addAndSave(implementasiPerawatEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                    return response;
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(ImplementasiPerawat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsImplementasiPerawatEntity entityList = implementasiPerawatDao.getById("idImplementasiPerawat", bean.getIdImplementasiPerawat());
        if(entityList != null){
            entityList.setLastUpdate(bean.getLastUpdate());
            entityList.setLastUpdateWho(bean.getLastUpdateWho());
            entityList.setFlag("N");
            entityList.setAction("D");
            try {
                implementasiPerawatDao.updateAndSave(entityList);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error, "+e.getMessage());
                logger.error(e.getMessage());
                return response;
            }
        }else{
            response.setStatus("error");
            response.setMsg("Data yang anda delete tidak ditemukan");
        }

        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setImplementasiPerawatDao(ImplementasiPerawatDao implementasiPerawatDao) {
        this.implementasiPerawatDao = implementasiPerawatDao;
    }
}
