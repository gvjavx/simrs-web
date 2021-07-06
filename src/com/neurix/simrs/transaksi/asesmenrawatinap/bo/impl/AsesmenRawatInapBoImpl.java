package com.neurix.simrs.transaksi.asesmenrawatinap.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.dao.AsesmenRawatInapDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsAsesmenRawatInapEntity;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenRawatInapBoImpl implements AsesmenRawatInapBo {
    private static transient Logger logger = Logger.getLogger(AsesmenRawatInapBoImpl.class);
    private AsesmenRawatInapDao asesmenRawatInapDao;

    @Override
    public List<AsesmenRawatInap> getByCriteria(AsesmenRawatInap bean) throws GeneralBOException {
        List<AsesmenRawatInap> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenKeperawatanRawatInap() != null && !"".equalsIgnoreCase(bean.getIdAsesmenKeperawatanRawatInap())) {
                hsCriteria.put("id_asesmen_keperawatan_rawat_inap", bean.getIdAsesmenKeperawatanRawatInap());
            }
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
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

            List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenRawatInapEntity entity : entityList) {
                    AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                    asesmenRawatInap.setIdAsesmenKeperawatanRawatInap(entity.getIdAsesmenKeperawatanRawatInap());
                    asesmenRawatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenRawatInap.setParameter(entity.getParameter());
                    if ("ttd".equalsIgnoreCase(entity.getTipe())) {
                        if(entity.getJawaban() != null && !"".equalsIgnoreCase(entity.getJawaban())){
                            asesmenRawatInap.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                    } else if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                        asesmenRawatInap.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                    } else {
                        asesmenRawatInap.setJawaban(entity.getJawaban());
                    }
                    asesmenRawatInap.setKeterangan(entity.getKeterangan());
                    asesmenRawatInap.setJenis(entity.getJenis());
                    asesmenRawatInap.setSkor(entity.getSkor());
                    asesmenRawatInap.setAction(entity.getAction());
                    asesmenRawatInap.setFlag(entity.getFlag());
                    asesmenRawatInap.setCreatedDate(entity.getCreatedDate());
                    asesmenRawatInap.setCreatedWho(entity.getCreatedWho());
                    asesmenRawatInap.setLastUpdate(entity.getLastUpdate());
                    asesmenRawatInap.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenRawatInap.setTipe(entity.getTipe());
                    asesmenRawatInap.setInformasi(entity.getInformasi());
                    asesmenRawatInap.setNamaTerang(entity.getNamaTerang());
                    asesmenRawatInap.setSip(entity.getSip());
                    asesmenRawatInap.setNoCheckup(entity.getNoCheckup());
                    list.add(asesmenRawatInap);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenRawatInap> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            AsesmenRawatInap inap = list.get(0);
            AsesmenRawatInap rawatInap = new AsesmenRawatInap();
            rawatInap.setIdDetailCheckup(inap.getIdDetailCheckup());
            rawatInap.setKeterangan(inap.getKeterangan());
            List<AsesmenRawatInap> rawatInapList = getByCriteria(rawatInap);

            if ("hand_over".equalsIgnoreCase(inap.getJenis())) {
                AsesmenRawatInap rw = new AsesmenRawatInap();
                rw.setIdDetailCheckup(inap.getIdDetailCheckup());
                rw.setJawaban(inap.getJawaban());
                rw.setJenis(inap.getJenis());
                Boolean cekData = asesmenRawatInapDao.cekHandOver(rw);
                if (cekData) {
                    response.setStatus("error");
                    response.setMsg("Found error, Data pada shift " + inap.getJawaban() + " sudah ada..!");
                } else {
                    response = saveAsesmen(list);
                }
            } else {
                if ("transfer_pasien".equalsIgnoreCase(inap.getJenis()) || "add_tindakan_ina".equalsIgnoreCase(inap.getKeterangan())) {
                    response = saveAsesmen(list);
                } else {
                    if (rawatInapList.size() > 0) {
                        response.setStatus("error");
                        response.setMsg("Found error, Data yang anda masukan sudah ada...!");
                    } else {
                        response = saveAsesmen(list);
                    }
                }
            }
        }
        return response;
    }

    private CrudResponse saveAsesmen(List<AsesmenRawatInap> list) {
        CrudResponse response = new CrudResponse();
        for (AsesmenRawatInap bean : list) {
            ItSimrsAsesmenRawatInapEntity asesmenRawatInapEntity = new ItSimrsAsesmenRawatInapEntity();
            asesmenRawatInapEntity.setIdAsesmenKeperawatanRawatInap("ARI" + asesmenRawatInapDao.getNextSeq());
            asesmenRawatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            asesmenRawatInapEntity.setParameter(bean.getParameter());
            asesmenRawatInapEntity.setJawaban(bean.getJawaban());
            asesmenRawatInapEntity.setKeterangan(bean.getKeterangan());
            asesmenRawatInapEntity.setJenis(bean.getJenis());
            asesmenRawatInapEntity.setSkor(bean.getSkor());
            asesmenRawatInapEntity.setAction(bean.getAction());
            asesmenRawatInapEntity.setFlag(bean.getFlag());
            asesmenRawatInapEntity.setCreatedDate(bean.getCreatedDate());
            asesmenRawatInapEntity.setCreatedWho(bean.getCreatedWho());
            asesmenRawatInapEntity.setLastUpdate(bean.getLastUpdate());
            asesmenRawatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());
            asesmenRawatInapEntity.setTipe(bean.getTipe());
            asesmenRawatInapEntity.setInformasi(bean.getInformasi());
            asesmenRawatInapEntity.setNamaTerang(bean.getNamaTerang());
            asesmenRawatInapEntity.setSip(bean.getSip());
            asesmenRawatInapEntity.setNoCheckup(bean.getNoCheckup());

            try {
                asesmenRawatInapDao.addAndSave(asesmenRawatInapEntity);
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
    public CrudResponse saveDelete(AsesmenRawatInap bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());

        if (bean.getIdAsesmenKeperawatanRawatInap() != null && !"".equalsIgnoreCase(bean.getIdAsesmenKeperawatanRawatInap())) {
            ItSimrsAsesmenRawatInapEntity entity = asesmenRawatInapDao.getById("idAsesmenKeperawatanRawatInap", bean.getIdAsesmenKeperawatanRawatInap());
            hsCriteria.put("created_date", entity.getCreatedDate());
        }

        try {
            entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Found Error, " + e.getMessage());
            logger.error(e.getMessage());
            return response;
        }

        if (entityList.size() > 0) {
            for (ItSimrsAsesmenRawatInapEntity inap : entityList) {
                inap.setLastUpdate(bean.getLastUpdate());
                inap.setLastUpdateWho(bean.getLastUpdateWho());
                inap.setFlag("N");
                inap.setAction("D");
                try {
                    asesmenRawatInapDao.updateAndSave(inap);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error, " + e.getMessage());
                    logger.error(e.getMessage());
                    return response;
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data yang anda delete tidak ditemukan");
        }

        return response;
    }

    @Override
    public List<AsesmenRawatInap> getListRI(String id, String jenis) throws GeneralBOException {
        return asesmenRawatInapDao.getListAsesmenRI(id, jenis);
    }

    @Override
    public CrudResponse saveEdit(AsesmenRawatInap bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean.getIdAsesmenKeperawatanRawatInap() != null) {
            ItSimrsAsesmenRawatInapEntity asesmenRawatInapEntity = asesmenRawatInapDao.getById("idAsesmenKeperawatanRawatInap", bean.getIdAsesmenKeperawatanRawatInap());
            if (asesmenRawatInapEntity != null) {
                if(bean.getNamaTerang() != null){
                    asesmenRawatInapEntity.setJawaban(bean.getJawaban());
                    asesmenRawatInapEntity.setNamaTerang(bean.getNamaTerang());
                    asesmenRawatInapEntity.setSip(bean.getSip());
                }else{
                    asesmenRawatInapEntity.setJawaban(asesmenRawatInapEntity.getJawaban() + "|" + bean.getJawaban());
                }
                asesmenRawatInapEntity.setAction("U");
                asesmenRawatInapEntity.setLastUpdate(bean.getLastUpdate());
                asesmenRawatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    asesmenRawatInapDao.updateAndSave(asesmenRawatInapEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public PersetujuanTindakanMedis getPersetujuanTindakan(AsesmenRawatInap bean) throws GeneralBOException {
        PersetujuanTindakanMedis persetujuanTindakanMedis = new PersetujuanTindakanMedis();
        List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();
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
            entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            List<PersetujuanTindakanMedis> persetujuanTindakanMedisList = new ArrayList<>();
            for (ItSimrsAsesmenRawatInapEntity list : entityList) {
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
                } else if ("Saksi Keluarga".equalsIgnoreCase(parameter)) {
                    persetujuanTindakanMedis.setTtdPihak1(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + jawaban);
                    persetujuanTindakanMedis.setPihak1(list.getNamaTerang());
                } else if ("Perawat Pendamping".equalsIgnoreCase(parameter)) {
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

    public void setAsesmenRawatInapDao(AsesmenRawatInapDao asesmenRawatInapDao) {
        this.asesmenRawatInapDao = asesmenRawatInapDao;
    }
}
