package com.neurix.simrs.transaksi.hemodialisa.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.ObservasiTindakanHdBo;
import com.neurix.simrs.transaksi.hemodialisa.dao.ObservasiTindakanHdDao;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsObservasiTindakanHdEntity;
import com.neurix.simrs.transaksi.hemodialisa.model.ObservasiTindakanHd;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObservasiTindakanHdBoImpl implements ObservasiTindakanHdBo {
    private static transient Logger logger = Logger.getLogger(ObservasiTindakanHdBoImpl.class);
    private ObservasiTindakanHdDao observasiTindakanHdDao;

    @Override
    public List<ObservasiTindakanHd> getByCriteria(ObservasiTindakanHd bean) throws GeneralBOException {
        List<ObservasiTindakanHd> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdObservasiTindakanHd() != null && !"".equalsIgnoreCase(bean.getIdObservasiTindakanHd())){
                hsCriteria.put("id_observasi_tindakan_hd", bean.getIdObservasiTindakanHd());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())){
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsObservasiTindakanHdEntity> entityList = new ArrayList<>();

            try {
                entityList = observasiTindakanHdDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsObservasiTindakanHdEntity entity: entityList){
                    ObservasiTindakanHd observasiTindakanHd = new ObservasiTindakanHd();
                    observasiTindakanHd.setIdObservasiTindakanHd(entity.getIdObservasiTindakanHd());
                    observasiTindakanHd.setIdDetailCheckup(entity.getIdDetailCheckup());
                    observasiTindakanHd.setObservasi(entity.getObservasi());
                    observasiTindakanHd.setWaktu(entity.getWaktu());
                    observasiTindakanHd.setKeterangan(entity.getKeterangan());
                    observasiTindakanHd.setJenis(entity.getJenis());
                    observasiTindakanHd.setQb(entity.getQb());
                    observasiTindakanHd.setTensi(entity.getTensi());
                    observasiTindakanHd.setNadi(entity.getNadi());
                    observasiTindakanHd.setRr(entity.getRr());
                    observasiTindakanHd.setSuhu(entity.getSuhu());
                    observasiTindakanHd.setCairanMasuk(entity.getCairanMasuk());
                    observasiTindakanHd.setMakanMinum(entity.getMakanMinum());
                    observasiTindakanHd.setMuntah(entity.getMuntah());
                    observasiTindakanHd.setUf(entity.getUf());
                    observasiTindakanHd.setAction(entity.getAction());
                    observasiTindakanHd.setFlag(entity.getFlag());
                    observasiTindakanHd.setCreatedDate(entity.getCreatedDate());
                    observasiTindakanHd.setCreatedWho(entity.getCreatedWho());
                    observasiTindakanHd.setLastUpdate(entity.getLastUpdate());
                    observasiTindakanHd.setLastUpdateWho(entity.getLastUpdateWho());
                    observasiTindakanHd.setTtd(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtd());
                    observasiTindakanHd.setNamaTerang(entity.getNamaTerang());
                    observasiTindakanHd.setSip(entity.getSip());
                    list.add(observasiTindakanHd);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<ObservasiTindakanHd> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            for (ObservasiTindakanHd bean: list){
                ItSimrsObservasiTindakanHdEntity observasiTindakanHdEntity = new ItSimrsObservasiTindakanHdEntity();
                observasiTindakanHdEntity.setIdObservasiTindakanHd("OBS"+observasiTindakanHdDao.getNextSeq());
                observasiTindakanHdEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                observasiTindakanHdEntity.setWaktu(bean.getWaktu());
                observasiTindakanHdEntity.setQb(bean.getQb());
                observasiTindakanHdEntity.setTensi(bean.getTensi());
                observasiTindakanHdEntity.setNadi(bean.getNadi());
                observasiTindakanHdEntity.setRr(bean.getRr());
                observasiTindakanHdEntity.setSuhu(bean.getSuhu());
                observasiTindakanHdEntity.setCairanMasuk(bean.getCairanMasuk());
                observasiTindakanHdEntity.setMakanMinum(bean.getMakanMinum());
                observasiTindakanHdEntity.setMuntah(bean.getMuntah());
                observasiTindakanHdEntity.setUf(bean.getUf());
                observasiTindakanHdEntity.setKeterangan(bean.getKeterangan());
                observasiTindakanHdEntity.setJenis(bean.getJenis());
                observasiTindakanHdEntity.setAction(bean.getAction());
                observasiTindakanHdEntity.setFlag(bean.getFlag());
                observasiTindakanHdEntity.setCreatedDate(bean.getCreatedDate());
                observasiTindakanHdEntity.setCreatedWho(bean.getCreatedWho());
                observasiTindakanHdEntity.setLastUpdate(bean.getLastUpdate());
                observasiTindakanHdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                observasiTindakanHdEntity.setTtd(bean.getTtd());
                observasiTindakanHdEntity.setNamaTerang(bean.getNamaTerang());
                observasiTindakanHdEntity.setSip(bean.getSip());

                try {
                    observasiTindakanHdDao.addAndSave(observasiTindakanHdEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error "+e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDetele(ObservasiTindakanHd bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("jenis", bean.getJenis());
        List<ItSimrsObservasiTindakanHdEntity> entityList = new ArrayList<>();
        try {
            entityList = observasiTindakanHdDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            ItSimrsObservasiTindakanHdEntity entity = entityList.get(0);
            entity.setFlag("N");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                observasiTindakanHdDao.updateAndSave(entity);
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error, "+e.getMessage());
                logger.error(e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setObservasiTindakanHdDao(ObservasiTindakanHdDao observasiTindakanHdDao) {
        this.observasiTindakanHdDao = observasiTindakanHdDao;
    }
}
