package com.neurix.simrs.transaksi.asesmenoperasi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.AsesmenOperasiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.dao.AsesmenOperasiDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsAsesmenOperasiEntity;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenOperasiBoImpl implements AsesmenOperasiBo {
    private static transient Logger logger = Logger.getLogger(AsesmenOperasiBoImpl.class);
    private AsesmenOperasiDao asesmenOperasiDao;

    @Override
    public List<AsesmenOperasi> getByCriteria(AsesmenOperasi bean) throws GeneralBOException {
        List<AsesmenOperasi> list = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenOperasi() != null && !"".equalsIgnoreCase(bean.getIdAsesmenOperasi())) {
                hsCriteria.put("id_asesmen_operasi", bean.getIdAsesmenOperasi());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsAsesmenOperasiEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenOperasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (entityList.size() > 0) {
                for (ItSimrsAsesmenOperasiEntity entity : entityList) {
                    AsesmenOperasi operasi = new AsesmenOperasi();
                    operasi.setIdAsesmenOperasi(entity.getIdAsesmenOperasi());
                    operasi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    operasi.setParameter(entity.getParameter());

                    if ("penanda".equalsIgnoreCase(entity.getTipe())) {
                        operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_AREA_OPERASI + entity.getJawaban1());
                    } else if ("ttd".equalsIgnoreCase(entity.getTipe())) {
                        if(entity.getJawaban1() != null && !"".equalsIgnoreCase(entity.getJawaban1())){
                            operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban1());
                        }
                    } else if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                        operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban1());
                    } else {
                        operasi.setJawaban1(entity.getJawaban1());
                    }

                    operasi.setJawaban2(entity.getJawaban2());
                    operasi.setKeterangan(entity.getKeterangan());
                    operasi.setJenis(entity.getJenis());
                    operasi.setSkor(entity.getSkor());
                    operasi.setAction(entity.getAction());
                    operasi.setFlag(entity.getFlag());
                    operasi.setCreatedDate(entity.getCreatedDate());
                    operasi.setCreatedWho(entity.getCreatedWho());
                    operasi.setLastUpdate(entity.getLastUpdate());
                    operasi.setLastUpdateWho(entity.getLastUpdateWho());
                    operasi.setTipe(entity.getTipe());
                    operasi.setNamaterang(entity.getNamaterang());
                    operasi.setSip(entity.getSip());
                    operasi.setNoCheckup(entity.getNoCheckup());
                    list.add(operasi);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenOperasi> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            AsesmenOperasi asesmenOperasi = list.get(0);
            if ("add_laporan_operasi".equalsIgnoreCase(asesmenOperasi.getJenis())) {
                for (AsesmenOperasi bean : list) {
                    ItSimrsAsesmenOperasiEntity operasi = new ItSimrsAsesmenOperasiEntity();
                    operasi.setIdAsesmenOperasi("ASO" + asesmenOperasiDao.getNextSeq());
                    operasi.setIdDetailCheckup(bean.getIdDetailCheckup());
                    operasi.setParameter(bean.getParameter());
                    operasi.setJawaban1(bean.getJawaban1());
                    operasi.setJawaban2(bean.getJawaban2());
                    operasi.setKeterangan(bean.getKeterangan());
                    operasi.setJenis(bean.getJenis());
                    operasi.setSkor(bean.getSkor());
                    operasi.setAction(bean.getAction());
                    operasi.setFlag(bean.getFlag());
                    operasi.setCreatedDate(bean.getCreatedDate());
                    operasi.setCreatedWho(bean.getCreatedWho());
                    operasi.setLastUpdate(bean.getLastUpdate());
                    operasi.setLastUpdateWho(bean.getLastUpdateWho());
                    operasi.setTipe(bean.getTipe());
                    operasi.setNamaterang(bean.getNamaterang());
                    operasi.setSip(bean.getSip());
                    operasi.setNoCheckup(bean.getNoCheckup());

                    try {
                        asesmenOperasiDao.addAndSave(operasi);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            } else {
                AsesmenOperasi op = new AsesmenOperasi();
                op.setIdDetailCheckup(asesmenOperasi.getIdDetailCheckup());
                op.setKeterangan(asesmenOperasi.getKeterangan());
                List<AsesmenOperasi> operasiList = getByCriteria(op);

                if (operasiList.size() > 0) {
                    response.setStatus("error");
                    response.setMsg("Found error, data yang anda masukan sudah ada...!");
                } else {
                    for (AsesmenOperasi bean : list) {
                        ItSimrsAsesmenOperasiEntity operasi = new ItSimrsAsesmenOperasiEntity();
                        operasi.setIdAsesmenOperasi("ASO" + asesmenOperasiDao.getNextSeq());
                        operasi.setIdDetailCheckup(bean.getIdDetailCheckup());
                        operasi.setParameter(bean.getParameter());
                        operasi.setJawaban1(bean.getJawaban1());
                        operasi.setJawaban2(bean.getJawaban2());
                        operasi.setKeterangan(bean.getKeterangan());
                        operasi.setJenis(bean.getJenis());
                        operasi.setSkor(bean.getSkor());
                        operasi.setAction(bean.getAction());
                        operasi.setFlag(bean.getFlag());
                        operasi.setCreatedDate(bean.getCreatedDate());
                        operasi.setCreatedWho(bean.getCreatedWho());
                        operasi.setLastUpdate(bean.getLastUpdate());
                        operasi.setLastUpdateWho(bean.getLastUpdateWho());
                        operasi.setTipe(bean.getTipe());
                        operasi.setNamaterang(bean.getNamaterang());
                        operasi.setSip(bean.getSip());
                        operasi.setNoCheckup(bean.getNoCheckup());

                        try {
                            asesmenOperasiDao.addAndSave(operasi);
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
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(AsesmenOperasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        if(bean.getCreatedDate() != null && !"".equalsIgnoreCase(bean.getCreatedWho())){
            hsCriteria.put("created_date", bean.getCreatedDate());
        }
        List<ItSimrsAsesmenOperasiEntity> entityList = new ArrayList<>();
        try {
            entityList = asesmenOperasiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            for (ItSimrsAsesmenOperasiEntity entity : entityList) {
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setAction("D");
                entity.setFlag("N");
                try {
                    asesmenOperasiDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Tidak ada data traksaksi...!");
        }
        return response;
    }

    @Override
    public String getDataByKey(String id, String params) throws GeneralBOException {
        String res = "";
        try {
            res = asesmenOperasiDao.getPraInduksiDataByKey(id, params);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return res;
    }

    @Override
    public void saveEdit(AsesmenOperasi bean) throws GeneralBOException {
        ItSimrsAsesmenOperasiEntity entity = asesmenOperasiDao.getById("idAsesmenOperasi", bean.getIdAsesmenOperasi());
        if (entity != null) {
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            entity.setAction("U");
            if("ttd".equalsIgnoreCase(bean.getJenis())) {
                entity.setJawaban1(bean.getJawaban1());
                entity.setNamaterang(bean.getNamaterang());
                entity.setSip(bean.getSip());
            }else if("kondisi_pasien".equalsIgnoreCase(bean.getJenis())){
                entity.setJawaban2(entity.getJawaban2()+"|"+bean.getJawaban2());
            }else{
                entity.setJawaban2(bean.getJawaban2());
            }
            try {
                asesmenOperasiDao.updateAndSave(entity);
            } catch (HibernateException e) {
                throw new GeneralBOException("Errror, "+e.getMessage());
            }
        }
    }

    @Override
    public PersetujuanTindakanMedis getPersetujuanTindakan(AsesmenOperasi bean) throws GeneralBOException {
        PersetujuanTindakanMedis persetujuanTindakanMedis = new PersetujuanTindakanMedis();
        List<ItSimrsAsesmenOperasiEntity> entityList = new ArrayList<>();
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
            entityList = asesmenOperasiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            List<PersetujuanTindakanMedis> persetujuanTindakanMedisList = new ArrayList<>();
            for (ItSimrsAsesmenOperasiEntity list : entityList) {
                String parameter = "";
                String jawaban1 = "";
                String jawaban2 = "";
                if(list.getParameter() != null){
                    parameter = list.getParameter();
                }
                if(list.getJawaban1() != null){
                    jawaban1 = list.getJawaban1();
                }
                if(list.getJawaban2() != null){
                    jawaban2 = list.getJawaban2();
                }
                PersetujuanTindakanMedis persetujuan = new PersetujuanTindakanMedis();
                if ("TTD yang menyatakan".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdMenyatakan(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban1);
                    persetujuanTindakanMedis.setNamaMenyatakan(list.getNamaterang());
                    persetujuanTindakanMedis.setSipMenyatakan(list.getSip());
                } else if ("Saksi I".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdPihak1(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban1);
                    persetujuanTindakanMedis.setPihak1(list.getNamaterang());
                } else if ("Saksi II".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdPihak2(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban1);
                    persetujuanTindakanMedis.setPihak2(list.getNamaterang());
                } else {
                    String pernytaan1 = "sedemikian rupa sehingga telah memahaminya";
                    String pernytaan2 = "informasi sebagaimana di atas dan telah memahaminya";
                    String pernytaan3 = "Yang bertanda tangan dibawah ini";
                    if(parameter.toLowerCase().contains(pernytaan1.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan1(parameter);
                        persetujuanTindakanMedis.setNamaPernyataan1(list.getNamaterang());
                        persetujuanTindakanMedis.setSipPernyataan1(list.getSip());
                        persetujuanTindakanMedis.setTtdPernyataan1(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban1);
                    }else if(parameter.toLowerCase().contains(pernytaan2.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan2(parameter);
                        persetujuanTindakanMedis.setNamaPernyataan2(list.getNamaterang());
                        persetujuanTindakanMedis.setTtdPernyataan2(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban1);
                    }else if(jawaban1.toLowerCase().contains(pernytaan3.toLowerCase())){
                        persetujuanTindakanMedis.setPernyataan3(jawaban1);
                    }else{
                        if(!"Persetujuan Tindakan Medis".equalsIgnoreCase(jawaban1)){
                            if ("pernyataan".equalsIgnoreCase(parameter)) {
                                persetujuan.setParameter(jawaban1);
                            } else {
                                persetujuan.setParameter(parameter);
                                persetujuan.setJawaban(jawaban1);
                                persetujuan.setInformasi(jawaban2);
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

    public void setAsesmenOperasiDao(AsesmenOperasiDao asesmenOperasiDao) {
        this.asesmenOperasiDao = asesmenOperasiDao;
    }
}
