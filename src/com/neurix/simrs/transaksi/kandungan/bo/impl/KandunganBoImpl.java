package com.neurix.simrs.transaksi.kandungan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;
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
            if("pemantauan_kalan4".equalsIgnoreCase(kandungan.getKeterangan())){
                Boolean cek = kandunganDao.cekData(kandungan.getIdDetailCheckup(), kandungan.getKeterangan(), kandungan.getJawaban());
                if(cek){
                    response.setStatus("error");
                    response.setMsg("Found Error, Data pada waktu "+kandungan.getJawaban()+" sudah ada...!");
                }else{
                    response = insertAsesmen(list);
                }
            }else{
                if(kandunganList.size() > 0){
                    response.setStatus("error");
                    response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
                }else{
                    response = insertAsesmen(list);
                }
            }
        }
        return response;
    }

    private CrudResponse insertAsesmen(List<Kandungan> list){
        CrudResponse response = new CrudResponse();
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
                return response;
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
        if(bean.getCreatedDate() != null && !"".equalsIgnoreCase(bean.getCreatedWho())){
            hsCriteria.put("created_date", bean.getCreatedDate());
        }
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
                entity.setAction("D");
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

    @Override
    public CrudResponse saveDeleteById(Kandungan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsAsesmenKandunganEntity kandunganEntity = kandunganDao.getById("idAsesmenKandungan", bean.getIdAsesmenKandungan());
        if(kandunganEntity != null){
            kandunganEntity.setFlag("N");
            kandunganEntity.setAction("D");
            kandunganEntity.setLastUpdate(bean.getLastUpdate());
            kandunganEntity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                kandunganDao.updateAndSave(kandunganEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public PersetujuanTindakanMedis getPersetujuanTindakan(Kandungan bean) throws GeneralBOException {
        PersetujuanTindakanMedis persetujuanTindakanMedis = new PersetujuanTindakanMedis();
        List<ItSimrsAsesmenKandunganEntity> entityList = new ArrayList<>();
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
            entityList = kandunganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            List<PersetujuanTindakanMedis> persetujuanTindakanMedisList = new ArrayList<>();
            for (ItSimrsAsesmenKandunganEntity list : entityList) {
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

    public void setKandunganDao(KandunganDao kandunganDao) {
        this.kandunganDao = kandunganDao;
    }
}
