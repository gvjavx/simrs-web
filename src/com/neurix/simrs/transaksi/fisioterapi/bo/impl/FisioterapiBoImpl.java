package com.neurix.simrs.transaksi.fisioterapi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.bo.FisioterapiBo;
import com.neurix.simrs.transaksi.fisioterapi.dao.FisioterapiDao;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsFisioterapiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FisioterapiBoImpl implements FisioterapiBo {

    private static transient Logger logger = Logger.getLogger(FisioterapiBoImpl.class);
    private FisioterapiDao fisioterapiDao;

    @Override
    public List<Fisioterapi> getByCriteria(Fisioterapi bean) throws GeneralBOException {
        List<Fisioterapi> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdFisioterapi() != null && !"".equalsIgnoreCase(bean.getIdFisioterapi())) {
                hsCriteria.put("id_fisioterapi", bean.getIdFisioterapi());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsFisioterapiEntity> entityList = new ArrayList<>();
            try {
                entityList = fisioterapiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsFisioterapiEntity entity : entityList) {
                    Fisioterapi fisioterapi = new Fisioterapi();
                    fisioterapi.setIdFisioterapi(entity.getIdFisioterapi());
                    fisioterapi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    fisioterapi.setParameter(entity.getParameter());
                    if("gambar".equalsIgnoreCase(entity.getTipe()) || "ttd".equalsIgnoreCase(entity.getTipe())){
                        if("ttd".equalsIgnoreCase(entity.getTipe())){
                            fisioterapi.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban());
                        }else{
                            fisioterapi.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_IMG_RM+entity.getJawaban());
                        }
                    }else{
                        fisioterapi.setJawaban(entity.getJawaban());
                    }
                    fisioterapi.setKeterangan(entity.getKeterangan());
                    fisioterapi.setAction(entity.getAction());
                    fisioterapi.setFlag(entity.getFlag());
                    fisioterapi.setCreatedDate(entity.getCreatedDate());
                    fisioterapi.setCreatedWho(entity.getCreatedWho());
                    fisioterapi.setLastUpdate(entity.getLastUpdate());
                    fisioterapi.setLastUpdateWho(entity.getLastUpdateWho());
                    fisioterapi.setSkor(entity.getSkor());
                    fisioterapi.setTipe(entity.getTipe());
                    list.add(fisioterapi);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<Fisioterapi> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (Fisioterapi bean : list) {
                ItSimrsFisioterapiEntity fisioterapi = new ItSimrsFisioterapiEntity();
                fisioterapi.setIdFisioterapi("FIS" + fisioterapiDao.getNextSeq());
                fisioterapi.setIdDetailCheckup(bean.getIdDetailCheckup());
                fisioterapi.setParameter(bean.getParameter());
                fisioterapi.setJawaban(bean.getJawaban());
                fisioterapi.setKeterangan(bean.getKeterangan());
                fisioterapi.setAction(bean.getAction());
                fisioterapi.setFlag(bean.getFlag());
                fisioterapi.setCreatedDate(bean.getCreatedDate());
                fisioterapi.setCreatedWho(bean.getCreatedWho());
                fisioterapi.setLastUpdate(bean.getLastUpdate());
                fisioterapi.setLastUpdateWho(bean.getLastUpdateWho());
                fisioterapi.setSkor(bean.getSkor());
                fisioterapi.setTipe(bean.getTipe());

                try {
                    fisioterapiDao.addAndSave(fisioterapi);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }

        return response;
    }

    @Override
    public CrudResponse saveDelete(Fisioterapi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        List<ItSimrsFisioterapiEntity> entityList = new ArrayList<>();
        try {
            entityList = fisioterapiDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            for (ItSimrsFisioterapiEntity entity: entityList){
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setAction("D");
                entity.setFlag("N");
                try {
                    fisioterapiDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("Tidak ada data traksaksi...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setFisioterapiDao(FisioterapiDao fisioterapiDao) {
        this.fisioterapiDao = fisioterapiDao;
    }
}
