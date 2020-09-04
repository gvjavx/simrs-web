package com.neurix.simrs.transaksi.hemodialisa.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.HemodialisaBo;
import com.neurix.simrs.transaksi.hemodialisa.dao.HemodialisaDao;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsHemodialisaEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HemodialisaBoImpl implements HemodialisaBo {
    private static transient Logger logger = Logger.getLogger(HemodialisaBoImpl.class);
    private HemodialisaDao hemodialisaDao;

    @Override
    public List<Hemodialisa> getByCriteria(Hemodialisa bean) throws GeneralBOException {
        List<Hemodialisa> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdHemodialisa() != null && !"".equalsIgnoreCase(bean.getIdHemodialisa())){
                hsCriteria.put("id_hemodialisa", bean.getIdHemodialisa());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if(bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())){
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsHemodialisaEntity> entityList = new ArrayList<>();

            try {
                entityList = hemodialisaDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsHemodialisaEntity entity: entityList){
                    Hemodialisa hemodialisa = new Hemodialisa();
                    hemodialisa.setIdHemodialisa(entity.getIdHemodialisa());
                    hemodialisa.setIdDetailCheckup(entity.getIdDetailCheckup());
                    hemodialisa.setParameter(entity.getParameter());
                    if("Y".equalsIgnoreCase(entity.getIsTtd()) || "G".equalsIgnoreCase(entity.getIsTtd())){
                        if("G".equalsIgnoreCase(entity.getIsTtd())){
                            hemodialisa.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_IMG_RM+entity.getJawaban1());
                        }else{
                            hemodialisa.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban1());
                        }
                    }else{
                        hemodialisa.setJawaban1(entity.getJawaban1());
                    }
                    hemodialisa.setJawaban2(entity.getJawaban2());
                    hemodialisa.setKeterangan(entity.getKeterangan());
                    hemodialisa.setJenis(entity.getJenis());
                    hemodialisa.setSkor(entity.getSkor());
                    hemodialisa.setAction(entity.getAction());
                    hemodialisa.setFlag(entity.getFlag());
                    hemodialisa.setCreatedDate(entity.getCreatedDate());
                    hemodialisa.setCreatedWho(entity.getCreatedWho());
                    hemodialisa.setLastUpdate(entity.getLastUpdate());
                    hemodialisa.setLastUpdateWho(entity.getLastUpdateWho());
                    hemodialisa.setIsTtd(entity.getIsTtd());
                    hemodialisa.setNamaTerang(entity.getNamaTerang());
                    hemodialisa.setSip(entity.getSip());
                    list.add(hemodialisa);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<Hemodialisa> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            Hemodialisa hemodialisa = list.get(0);
            Hemodialisa hd = new Hemodialisa();
            hd.setIdDetailCheckup(hemodialisa.getIdDetailCheckup());
            hd.setKeterangan(hemodialisa.getKeterangan());
            List<Hemodialisa> hemodialisaList = getByCriteria(hd);
            if(hemodialisaList.size()  > 0){
                response.setStatus("error");
                response.setMsg("Found Error, Data yang anda masukkan sudah tersedia...!");
            }else{
                for (Hemodialisa bean: list){
                    ItSimrsHemodialisaEntity hemodialisaEntity = new ItSimrsHemodialisaEntity();
                    hemodialisaEntity.setIdHemodialisa("HDL"+hemodialisaDao.getNextSeq());
                    hemodialisaEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    hemodialisaEntity.setParameter(bean.getParameter());
                    hemodialisaEntity.setJawaban1(bean.getJawaban1());
                    hemodialisaEntity.setJawaban2(bean.getJawaban2());
                    hemodialisaEntity.setKeterangan(bean.getKeterangan());
                    hemodialisaEntity.setJenis(bean.getJenis());
                    hemodialisaEntity.setSkor(bean.getSkor());
                    hemodialisaEntity.setAction(bean.getAction());
                    hemodialisaEntity.setFlag(bean.getFlag());
                    hemodialisaEntity.setCreatedDate(bean.getCreatedDate());
                    hemodialisaEntity.setCreatedWho(bean.getCreatedWho());
                    hemodialisaEntity.setLastUpdate(bean.getLastUpdate());
                    hemodialisaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    hemodialisaEntity.setIsTtd(bean.getIsTtd());
                    hemodialisaEntity.setNamaTerang(bean.getNamaTerang());
                    hemodialisaEntity.setSip(bean.getSip());

                    try {
                        hemodialisaDao.addAndSave(hemodialisaEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("Found Error "+e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDetele(Hemodialisa bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        List<ItSimrsHemodialisaEntity> entityList = new ArrayList<>();
        try {
            entityList = hemodialisaDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            ItSimrsHemodialisaEntity entity = entityList.get(0);
            entity.setFlag("N");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                hemodialisaDao.updateAndSave(entity);
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

    public void setHemodialisaDao(HemodialisaDao hemodialisaDao) {
        this.hemodialisaDao = hemodialisaDao;
    }
}
