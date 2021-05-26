package com.neurix.simrs.transaksi.pemberianobat.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pemberianobat.bo.CatatanPemberianObatBo;
import com.neurix.simrs.transaksi.pemberianobat.dao.CatatanPemberianObatDao;
import com.neurix.simrs.transaksi.pemberianobat.model.CatatanPemberianObat;
import com.neurix.simrs.transaksi.pemberianobat.model.ItSimrsCatatanPemberianObatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatatanPemberianObatBoImpl implements CatatanPemberianObatBo {

    private static transient Logger logger = Logger.getLogger(CatatanPemberianObatBoImpl.class);
    private CatatanPemberianObatDao catatanPemberianObatDao;

    @Override
    public List<CatatanPemberianObat> getByCriteria(CatatanPemberianObat bean) throws GeneralBOException {
        List<CatatanPemberianObat> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdCatatanPemberianObat() != null && !"".equalsIgnoreCase(bean.getIdCatatanPemberianObat())){
                hsCriteria.put("id_catatan_pemberian_obat", bean.getIdCatatanPemberianObat());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())){
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsCatatanPemberianObatEntity> entityList = new ArrayList<>();
            try {
                entityList = catatanPemberianObatDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsCatatanPemberianObatEntity entity: entityList){
                    CatatanPemberianObat catatan = new CatatanPemberianObat();
                    catatan.setIdCatatanPemberianObat(entity.getIdCatatanPemberianObat());
                    catatan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    catatan.setWaktu(entity.getWaktu());
                    catatan.setNamaDokter(entity.getNamaDokter());
                    catatan.setNamaObat(entity.getNamaObat());
                    catatan.setAturanPakai(entity.getAturanPakai());
                    catatan.setTanggalMulai(entity.getTanggalMulai());
                    catatan.setTanggalStop(entity.getTanggalStop());
                    if(entity.getTtdApoteker() != null && !"".equalsIgnoreCase(entity.getTtdApoteker())){
                        catatan.setTtdApoteker(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdApoteker());
                    }
                    if(entity.getTtdDokter() != null && !"".equalsIgnoreCase(entity.getTtdDokter())){
                        catatan.setTtdDokter(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdDokter());
                    }
                    catatan.setKeterangan(entity.getKeterangan());
                    catatan.setStatus(entity.getStatus());
                    catatan.setAction(entity.getAction());
                    catatan.setFlag(entity.getFlag());
                    catatan.setCreatedDate(entity.getCreatedDate());
                    catatan.setCreatedWho(entity.getCreatedWho());
                    catatan.setLastUpdate(entity.getLastUpdate());
                    catatan.setLastUpdateWho(entity.getLastUpdateWho());
                    catatan.setNamaTerangDokter(entity.getNamaTerangDokter());
                    catatan.setNamaTerangPerawat(entity.getNamaTerangPerawat());
                    catatan.setSipDokter(entity.getSipDokter());
                    catatan.setJenis(entity.getJenis());
                    list.add(catatan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(CatatanPemberianObat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsCatatanPemberianObatEntity catatanPemberianObatEntity = new ItSimrsCatatanPemberianObatEntity();
            catatanPemberianObatEntity.setIdCatatanPemberianObat("CPO"+catatanPemberianObatDao.getNextSeq());
            catatanPemberianObatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            catatanPemberianObatEntity.setWaktu(bean.getWaktu());
            catatanPemberianObatEntity.setNamaDokter(bean.getNamaDokter());
            catatanPemberianObatEntity.setNamaObat(bean.getNamaObat());
            catatanPemberianObatEntity.setAturanPakai(bean.getAturanPakai());
            catatanPemberianObatEntity.setTanggalMulai(bean.getTanggalMulai());
            catatanPemberianObatEntity.setTanggalStop(bean.getTanggalStop());
            catatanPemberianObatEntity.setTtdDokter(bean.getTtdDokter());
            catatanPemberianObatEntity.setTtdApoteker(bean.getTtdApoteker());
            catatanPemberianObatEntity.setKeterangan(bean.getKeterangan());
            catatanPemberianObatEntity.setStatus(bean.getStatus());
            catatanPemberianObatEntity.setAction(bean.getAction());
            catatanPemberianObatEntity.setFlag(bean.getFlag());
            catatanPemberianObatEntity.setCreatedDate(bean.getCreatedDate());
            catatanPemberianObatEntity.setCreatedWho(bean.getCreatedWho());
            catatanPemberianObatEntity.setLastUpdate(bean.getLastUpdate());
            catatanPemberianObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
            catatanPemberianObatEntity.setNamaTerangDokter(bean.getNamaTerangDokter());
            catatanPemberianObatEntity.setNamaTerangPerawat(bean.getNamaTerangPerawat());
            catatanPemberianObatEntity.setSipDokter(bean.getSipDokter());
            catatanPemberianObatEntity.setJenis(bean.getJenis());

            try {
                catatanPemberianObatDao.addAndSave(catatanPemberianObatEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error "+e.getMessage());
                logger.error(e.getMessage());
            }
        }

        return response;
    }

    @Override
    public CrudResponse saveDelete(CatatanPemberianObat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean.getIdCatatanPemberianObat() != null){
            ItSimrsCatatanPemberianObatEntity catatanPemberianObatEntity = new ItSimrsCatatanPemberianObatEntity();
            try {
                catatanPemberianObatEntity = catatanPemberianObatDao.getById("idCatatanPemberianObat", bean.getIdCatatanPemberianObat());
            }catch (HibernateException e){
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("Error mencari data pemberian obat "+e.getMessage());
            }
            if(catatanPemberianObatEntity != null){
                catatanPemberianObatEntity.setAction("D");
                catatanPemberianObatEntity.setFlag("N");
                catatanPemberianObatEntity.setLastUpdate(bean.getLastUpdate());
                catatanPemberianObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    catatanPemberianObatDao.updateAndSave(catatanPemberianObatEntity);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Error mencari data pemberian obat "+e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public void saveUpdate(CatatanPemberianObat bean) throws GeneralBOException {
        if(bean.getIdCatatanPemberianObat() != null){
            ItSimrsCatatanPemberianObatEntity catatanPemberianObatEntity = new ItSimrsCatatanPemberianObatEntity();
            try {
                catatanPemberianObatEntity = catatanPemberianObatDao.getById("idCatatanPemberianObat", bean.getIdCatatanPemberianObat());
            }catch (HibernateException e){
                logger.error(e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }

            HashMap hs = new HashMap();
            hs.put("waktu", catatanPemberianObatEntity.getWaktu());
            hs.put("created_date", catatanPemberianObatEntity.getCreatedDate());
            hs.put("id_detail_checkup", catatanPemberianObatEntity.getIdDetailCheckup());
            hs.put("jenis", catatanPemberianObatEntity.getJenis());

            List<ItSimrsCatatanPemberianObatEntity> pemberianObatEntities = catatanPemberianObatDao.getByCriteria(hs);
            for (ItSimrsCatatanPemberianObatEntity pemberianObatEntities1: pemberianObatEntities){
                pemberianObatEntities1.setWaktu(bean.getWaktu());
                pemberianObatEntities1.setTtdApoteker(bean.getTtdApoteker());
                pemberianObatEntities1.setTtdDokter(bean.getTtdDokter());
                pemberianObatEntities1.setKeterangan(bean.getKeterangan());
                pemberianObatEntities1.setNamaTerangDokter(bean.getNamaTerangDokter());
                pemberianObatEntities1.setNamaTerangPerawat(bean.getNamaTerangPerawat());
                pemberianObatEntities1.setSipDokter(bean.getSipDokter());
                pemberianObatEntities1.setSipPerawat(bean.getSipPerawat());
                pemberianObatEntities1.setAction("U");
                pemberianObatEntities1.setLastUpdate(bean.getLastUpdate());
                pemberianObatEntities1.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    catatanPemberianObatDao.updateAndSave(pemberianObatEntities1);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCatatanPemberianObatDao(CatatanPemberianObatDao catatanPemberianObatDao) {
        this.catatanPemberianObatDao = catatanPemberianObatDao;
    }
}
