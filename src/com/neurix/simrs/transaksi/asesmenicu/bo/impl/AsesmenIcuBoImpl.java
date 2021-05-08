package com.neurix.simrs.transaksi.asesmenicu.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.AsesmenIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.dao.AsesmenIcuDao;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
import com.neurix.simrs.transaksi.asesmenicu.model.ItSimrsAsesmenIcuEntity;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenIcuBoImpl implements AsesmenIcuBo {
    private static transient Logger logger = Logger.getLogger(AsesmenIcuBoImpl.class);
    private AsesmenIcuDao asesmenIcuDao;

    @Override
    public List<AsesmenIcu> getByCriteria(AsesmenIcu bean) throws GeneralBOException {
        List<AsesmenIcu> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenIcu() != null && !"".equalsIgnoreCase(bean.getIdAsesmenIcu())) {
                hsCriteria.put("id_asesmen_icu", bean.getIdAsesmenIcu());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsAsesmenIcuEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenIcuDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenIcuEntity entity : entityList) {
                    AsesmenIcu asesmenIcu = new AsesmenIcu();
                    asesmenIcu.setIdAsesmenIcu(entity.getIdAsesmenIcu());
                    asesmenIcu.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenIcu.setParameter(entity.getParameter());

                    if ("gambar".equalsIgnoreCase(entity.getTipe()) || "ttd".equalsIgnoreCase(entity.getTipe())) {
                        if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                            asesmenIcu.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                        } else {
                            asesmenIcu.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                    } else {
                        asesmenIcu.setJawaban(entity.getJawaban());
                    }

                    asesmenIcu.setKeterangan(entity.getKeterangan());
                    asesmenIcu.setJenis(entity.getJenis());
                    asesmenIcu.setScore(entity.getScore());
                    asesmenIcu.setAction(entity.getAction());
                    asesmenIcu.setFlag(entity.getFlag());
                    asesmenIcu.setCreatedDate(entity.getCreatedDate());
                    asesmenIcu.setCreatedWho(entity.getCreatedWho());
                    asesmenIcu.setLastUpdate(entity.getLastUpdate());
                    asesmenIcu.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenIcu.setTipe(entity.getTipe());
                    asesmenIcu.setInformasi(entity.getInformasi());
                    asesmenIcu.setNamaTerang(entity.getNamaTerang());
                    asesmenIcu.setSip(entity.getSip());
                    list.add(asesmenIcu);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenIcu> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            AsesmenIcu asesmenIcu = list.get(0);
            AsesmenIcu icu = new AsesmenIcu();
            icu.setIdDetailCheckup(asesmenIcu.getIdDetailCheckup());
            icu.setKeterangan(asesmenIcu.getKeterangan());
            List<AsesmenIcu> asesmenIcus = getByCriteria(icu);
            if(asesmenIcus.size() > 0){
                response.setStatus("error");
                response.setMsg("Data yang anda masukan sudah ada...!");
            }else{
                for (AsesmenIcu bean : list) {
                    ItSimrsAsesmenIcuEntity asesmenIcuEntity = new ItSimrsAsesmenIcuEntity();
                    asesmenIcuEntity.setIdAsesmenIcu("ICU" + asesmenIcuDao.getNextSeq());
                    asesmenIcuEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    asesmenIcuEntity.setParameter(bean.getParameter());
                    asesmenIcuEntity.setJawaban(bean.getJawaban());
                    asesmenIcuEntity.setKeterangan(bean.getKeterangan());
                    asesmenIcuEntity.setJenis(bean.getJenis());
                    asesmenIcuEntity.setScore(bean.getScore());
                    asesmenIcuEntity.setAction(bean.getAction());
                    asesmenIcuEntity.setFlag(bean.getFlag());
                    asesmenIcuEntity.setCreatedDate(bean.getCreatedDate());
                    asesmenIcuEntity.setCreatedWho(bean.getCreatedWho());
                    asesmenIcuEntity.setLastUpdate(bean.getLastUpdate());
                    asesmenIcuEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    asesmenIcuEntity.setTipe(bean.getTipe());
                    asesmenIcuEntity.setInformasi(bean.getInformasi());
                    asesmenIcuEntity.setNamaTerang(bean.getNamaTerang());
                    asesmenIcuEntity.setSip(bean.getSip());

                    try {
                        asesmenIcuDao.addAndSave(asesmenIcuEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(AsesmenIcu bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        if(bean.getCreatedDate() != null && !"".equalsIgnoreCase(bean.getCreatedWho())){
            hsCriteria.put("created_date", bean.getCreatedDate());
        }
        List<ItSimrsAsesmenIcuEntity> entityList = new ArrayList<>();
        try {
            entityList = asesmenIcuDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            for (ItSimrsAsesmenIcuEntity entity : entityList){
                entity.setFlag("N");
                entity.setAction("D");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    asesmenIcuDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error, "+e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public PersetujuanTindakanMedis getPersetujuanTindakan(AsesmenIcu bean) throws GeneralBOException {
        PersetujuanTindakanMedis persetujuanTindakanMedis = new PersetujuanTindakanMedis();
        List<ItSimrsAsesmenIcuEntity> entityList = new ArrayList<>();
        Map hsCriteria = new HashMap();
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
            hsCriteria.put("keterangan", bean.getKeterangan());
        }
        if (bean.getCreatedDate() != null && !"".equalsIgnoreCase(bean.getCreatedDate().toString())) {
            hsCriteria.put("created_date", bean.getCreatedDate());
        }

        try {
            entityList = asesmenIcuDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            List<PersetujuanTindakanMedis> persetujuanTindakanMedisList = new ArrayList<>();
            for (ItSimrsAsesmenIcuEntity list : entityList) {
                String parameter = "";
                String jawaban = "";
                String informasi = "";
                if(list.getParameter() != null){
                    parameter = list.getParameter();
                }
                if(list.getJawaban() != null){
                    jawaban = list.getJawaban();
                }
                if(list.getInformasi() != null){
                    informasi = list.getInformasi();
                }
                PersetujuanTindakanMedis persetujuan = new PersetujuanTindakanMedis();
                if ("TTD yang menyatakan".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdMenyatakan(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    persetujuanTindakanMedis.setNamaMenyatakan(list.getNamaTerang());
                    persetujuanTindakanMedis.setSipMenyatakan(list.getSip());
                } else if ("Saksi I".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdPihak1(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    persetujuanTindakanMedis.setPihak1(list.getNamaTerang());
                } else if ("Saksi II".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdPihak2(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    persetujuanTindakanMedis.setPihak2(list.getNamaTerang());
                } else {
                    String pernytaan1 = "sedemikian rupa sehingga telah memahaminya";
                    String pernytaan2 = "informasi sebagaimana di atas dan telah memahaminya";
                    String pernytaan3 = "Yang bertanda tangan dibawah ini";
                    if(parameter.toLowerCase().contains(pernytaan1.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan1(parameter);
                        persetujuanTindakanMedis.setNamaPernyataan1(list.getNamaTerang());
                        persetujuanTindakanMedis.setSipPernyataan1(list.getSip());
                        persetujuanTindakanMedis.setTtdPernyataan1(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    }else if(parameter.toLowerCase().contains(pernytaan2.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan2(parameter);
                        persetujuanTindakanMedis.setNamaPernyataan2(list.getNamaTerang());
                        persetujuanTindakanMedis.setTtdPernyataan2(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    }else if(jawaban.toLowerCase().contains(pernytaan3.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan3(jawaban);
                    }else{
                        if(!"Persetujuan Tindakan Medis".equalsIgnoreCase(jawaban)){
                            if ("pernyataan".equalsIgnoreCase(parameter)) {
                                persetujuan.setParameter(jawaban);
                            } else {
                                persetujuan.setParameter(parameter);
                                persetujuan.setJawaban(jawaban);
                                persetujuan.setInformasi(informasi);
                            }
                            persetujuanTindakanMedisList.add(persetujuan);
                        }
                    }
                }
            }
            persetujuanTindakanMedis.setTindakanMedisList(persetujuanTindakanMedisList);
        }
        return persetujuanTindakanMedis;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsesmenIcuDao(AsesmenIcuDao asesmenIcuDao) {
        this.asesmenIcuDao = asesmenIcuDao;
    }
}
