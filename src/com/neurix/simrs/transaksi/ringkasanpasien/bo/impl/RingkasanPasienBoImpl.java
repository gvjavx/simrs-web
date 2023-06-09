package com.neurix.simrs.transaksi.ringkasanpasien.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.ringkasanpasien.bo.RingkasanPasienBo;
import com.neurix.simrs.transaksi.ringkasanpasien.dao.RingkasanPasienDao;
import com.neurix.simrs.transaksi.ringkasanpasien.model.ItSimrsRingkasanPasienEntity;
import com.neurix.simrs.transaksi.ringkasanpasien.model.RingkasanPasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RingkasanPasienBoImpl implements RingkasanPasienBo {
    private static transient Logger logger = Logger.getLogger(RingkasanPasienBoImpl.class);
    private RingkasanPasienDao ringkasanPasienDao;

    @Override
    public List<RingkasanPasien> getByCriteria(RingkasanPasien bean) throws GeneralBOException {
        List<RingkasanPasien> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdRingkasanPasien() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_ringkasan_pasien", bean.getIdRingkasanPasien());
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

            List<ItSimrsRingkasanPasienEntity> entityList = new ArrayList<>();

            try {
                entityList = ringkasanPasienDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsRingkasanPasienEntity entity: entityList){
                    RingkasanPasien ringkasanPasien = new RingkasanPasien();
                    ringkasanPasien.setIdRingkasanPasien(entity.getIdRingkasanPasien());
                    ringkasanPasien.setIdDetailCheckup(entity.getIdDetailCheckup());
                    ringkasanPasien.setParameter(entity.getParameter());
                    if("gambar".equalsIgnoreCase(entity.getTipe()) || "ttd".equalsIgnoreCase(entity.getTipe())){
                        if("ttd".equalsIgnoreCase(entity.getTipe())){
                            ringkasanPasien.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban());
                        }else{
                            ringkasanPasien.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_IMG_RM+entity.getJawaban());
                        }
                    }else{
                        ringkasanPasien.setJawaban(entity.getJawaban());
                    }
                    ringkasanPasien.setKeterangan(entity.getKeterangan());
                    ringkasanPasien.setJenis(entity.getJenis());
                    ringkasanPasien.setJenis(entity.getJenis());
                    ringkasanPasien.setAction(entity.getAction());
                    ringkasanPasien.setFlag(entity.getFlag());
                    ringkasanPasien.setCreatedDate(entity.getCreatedDate());
                    ringkasanPasien.setCreatedWho(entity.getCreatedWho());
                    ringkasanPasien.setLastUpdate(entity.getLastUpdate());
                    ringkasanPasien.setLastUpdateWho(entity.getLastUpdateWho());
                    ringkasanPasien.setTipe(entity.getTipe());
                    ringkasanPasien.setNamaTerang(entity.getNamaTerang());
                    ringkasanPasien.setSip(entity.getSip());
                    list.add(ringkasanPasien);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<RingkasanPasien> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size()> 0){
            RingkasanPasien ringkasanPasien = list.get(0);
            RingkasanPasien ringkasan = new RingkasanPasien();
            ringkasan.setIdDetailCheckup(ringkasanPasien.getIdDetailCheckup());
            ringkasan.setKeterangan(ringkasanPasien.getKeterangan());
            List<RingkasanPasien> ringkasanPasienList = getByCriteria(ringkasan);
            if(ringkasanPasienList.size() > 0){
                response.setStatus("error");
                response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
            }else{
                for (RingkasanPasien bean: list){
                    ItSimrsRingkasanPasienEntity ringkasanPasienEntity = new ItSimrsRingkasanPasienEntity();
                    ringkasanPasienEntity.setIdRingkasanPasien("RGP"+ringkasanPasienDao.getNextSeq());
                    ringkasanPasienEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    ringkasanPasienEntity.setParameter(bean.getParameter());
                    ringkasanPasienEntity.setJawaban(bean.getJawaban());
                    ringkasanPasienEntity.setKeterangan(bean.getKeterangan());
                    ringkasanPasienEntity.setJenis(bean.getJenis());
                    ringkasanPasienEntity.setJenis(bean.getJenis());
                    ringkasanPasienEntity.setAction(bean.getAction());
                    ringkasanPasienEntity.setFlag(bean.getFlag());
                    ringkasanPasienEntity.setCreatedDate(bean.getCreatedDate());
                    ringkasanPasienEntity.setCreatedWho(bean.getCreatedWho());
                    ringkasanPasienEntity.setLastUpdate(bean.getLastUpdate());
                    ringkasanPasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    ringkasanPasienEntity.setNamaTerang(bean.getNamaTerang());
                    ringkasanPasienEntity.setSip(bean.getSip());
                    ringkasanPasienEntity.setTipe(bean.getTipe());

                    try {
                        ringkasanPasienDao.addAndSave(ringkasanPasienEntity);
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
    public CrudResponse saveDelete(RingkasanPasien bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        List<ItSimrsRingkasanPasienEntity> entityList = new ArrayList<>();
        try {
            entityList = ringkasanPasienDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            for (ItSimrsRingkasanPasienEntity entity : entityList){
                entity.setFlag("N");
                entity.setAction("D");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    ringkasanPasienDao.updateAndSave(entity);
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

    public static Logger getLogger() {
        return logger;
    }

    public void setRingkasanPasienDao(RingkasanPasienDao ringkasanPasienDao) {
        this.ringkasanPasienDao = ringkasanPasienDao;
    }
}
