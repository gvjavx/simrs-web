package com.neurix.simrs.transaksi.kandungan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.bo.KandunganBo;
import com.neurix.simrs.transaksi.kandungan.dao.KandunganDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsAsesmenKandunganEntity;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KandunganBoImpl implements KandunganBo {

    private static transient Logger logger = Logger.getLogger(KandunganBoImpl.class);
    private KandunganDao kandunganDao;

    @Override
    public List<Kandungan> getByCriteria(Kandungan bean) throws GeneralBOException {
        List<Kandungan> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenKandungan() != null && !"".equalsIgnoreCase(bean.getIdAsesmenKandungan())) {
                hsCriteria.put("id_asesmen_kandungan", bean.getIdAsesmenKandungan());
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

            List<ItSimrsAsesmenKandunganEntity> entityList = new ArrayList<>();

            try {
                entityList = kandunganDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenKandunganEntity entity : entityList) {
                    Kandungan kandungan = new Kandungan();
                    kandungan.setIdAsesmenKandungan(entity.getIdAsesmenKandungan());
                    kandungan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    kandungan.setParameter(entity.getParameter());

                    if ("gambar".equalsIgnoreCase(entity.getTipe()) || "ttd".equalsIgnoreCase(entity.getTipe())) {
                        if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                            kandungan.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                        } else {
                            kandungan.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                    } else {
                        kandungan.setJawaban(entity.getJawaban());
                    }

                    kandungan.setKeterangan(entity.getKeterangan());
                    kandungan.setJenis(entity.getJenis());
                    kandungan.setScore(entity.getScore());
                    kandungan.setAction(entity.getAction());
                    kandungan.setFlag(entity.getFlag());
                    kandungan.setCreatedDate(entity.getCreatedDate());
                    kandungan.setCreatedWho(entity.getCreatedWho());
                    kandungan.setLastUpdate(entity.getLastUpdate());
                    kandungan.setLastUpdateWho(entity.getLastUpdateWho());
                    kandungan.setTipe(entity.getTipe());
                    kandungan.setInformasi(entity.getInformasi());
                    kandungan.setNamaTerang(entity.getNamaTerang());
                    kandungan.setSip(entity.getSip());
                    list.add(kandungan);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(List<Kandungan> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            Kandungan kandungan = list.get(0);
            Kandungan kdn = new Kandungan();
            kdn.setIdDetailCheckup(kandungan.getIdDetailCheckup());
            kdn.setKeterangan(kandungan.getKeterangan());
            List<Kandungan> kandunganList = new ArrayList<>();
            kandunganList = getByCriteria(kdn);
            if(kandunganList.size() > 0){
                response.setStatus("error");
                response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
            }else{
                for (Kandungan bean : list) {
                    ItSimrsAsesmenKandunganEntity kandunganEntity = new ItSimrsAsesmenKandunganEntity();
                    kandunganEntity.setIdAsesmenKandungan("RBP" + kandunganDao.getNextSeq());
                    kandunganEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    kandunganEntity.setParameter(bean.getParameter());
                    kandunganEntity.setJawaban(bean.getJawaban());
                    kandunganEntity.setKeterangan(bean.getKeterangan());
                    kandunganEntity.setJenis(bean.getJenis());
                    kandunganEntity.setScore(bean.getScore());
                    kandunganEntity.setAction(bean.getAction());
                    kandunganEntity.setFlag(bean.getFlag());
                    kandunganEntity.setCreatedDate(bean.getCreatedDate());
                    kandunganEntity.setCreatedWho(bean.getCreatedWho());
                    kandunganEntity.setLastUpdate(bean.getLastUpdate());
                    kandunganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    kandunganEntity.setTipe(bean.getTipe());
                    kandunganEntity.setInformasi(bean.getInformasi());
                    kandunganEntity.setNamaTerang(bean.getNamaTerang());
                    kandunganEntity.setSip(bean.getSip());
                    try {
                        kandunganDao.addAndSave(kandunganEntity);
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
    public CrudResponse saveDelete(Kandungan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        List<ItSimrsAsesmenKandunganEntity> kandunganEntityList = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        try {
            kandunganEntityList = kandunganDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, "+e.getMessage());
            logger.error("Found Error, "+e.getMessage());
        }

        if(kandunganEntityList.size() > 0){
            for (ItSimrsAsesmenKandunganEntity entity: kandunganEntityList){
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setFlag("N");
                try {
                    kandunganDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error, "+e.getMessage());
                    logger.error("Found Error, "+e.getMessage());
                    return response;
                }
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKandunganDao(KandunganDao kandunganDao) {
        this.kandunganDao = kandunganDao;
    }
}
