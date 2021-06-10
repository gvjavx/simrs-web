package com.neurix.simrs.transaksi.asesmenrawatjalan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatjalan.bo.KeperawatanRawatJalanBo;
import com.neurix.simrs.transaksi.asesmenrawatjalan.dao.KeperawatanRawatJalanDao;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.ItSimrsAsesmenKeperawatanRawatJalanEntity;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.KeperawatanRawatJalan;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeperawatanRawatJalanBoImpl implements KeperawatanRawatJalanBo {
    private static transient Logger logger = Logger.getLogger(KeperawatanRawatJalanBoImpl.class);
    private KeperawatanRawatJalanDao keperawatanRawatJalanDao;
    private HeaderCheckupDao headerCheckupDao;

    @Override
    public List<KeperawatanRawatJalan> getByCriteria(KeperawatanRawatJalan bean) throws GeneralBOException {
        List<KeperawatanRawatJalan> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdKeperawatanRawatJalan() != null && !"".equalsIgnoreCase(bean.getIdKeperawatanRawatJalan())) {
                hsCriteria.put("id_keperawatan_rawat_jalan", bean.getIdKeperawatanRawatJalan());
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

            List<ItSimrsAsesmenKeperawatanRawatJalanEntity> entityList = new ArrayList<>();

            try {
                entityList = keperawatanRawatJalanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenKeperawatanRawatJalanEntity entity : entityList) {
                    KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                    keperawatanRawatJalan.setIdKeperawatanRawatJalan(entity.getIdKeperawatanRawatJalan());
                    keperawatanRawatJalan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    keperawatanRawatJalan.setParameter(entity.getParameter());
                    if ("ttd".equalsIgnoreCase(entity.getTipe())) {
                        keperawatanRawatJalan.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                    } else if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                        keperawatanRawatJalan.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                    } else {
                        keperawatanRawatJalan.setJawaban(entity.getJawaban());
                    }
                    keperawatanRawatJalan.setKeterangan(entity.getKeterangan());
                    keperawatanRawatJalan.setJenis(entity.getJenis());
                    keperawatanRawatJalan.setScore(entity.getScore());
                    keperawatanRawatJalan.setAction(entity.getAction());
                    keperawatanRawatJalan.setFlag(entity.getFlag());
                    keperawatanRawatJalan.setCreatedDate(entity.getCreatedDate());
                    keperawatanRawatJalan.setCreatedWho(entity.getCreatedWho());
                    keperawatanRawatJalan.setLastUpdate(entity.getLastUpdate());
                    keperawatanRawatJalan.setLastUpdateWho(entity.getLastUpdateWho());
                    keperawatanRawatJalan.setTipe(entity.getTipe());
                    keperawatanRawatJalan.setNamaTerang(entity.getNamaTerang());
                    keperawatanRawatJalan.setSip(entity.getSip());
                    list.add(keperawatanRawatJalan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<KeperawatanRawatJalan> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            KeperawatanRawatJalan keperawatanRawatJalan = list.get(0);
            KeperawatanRawatJalan kp = new KeperawatanRawatJalan();
            kp.setIdDetailCheckup(keperawatanRawatJalan.getIdDetailCheckup());
            kp.setKeterangan(keperawatanRawatJalan.getKeterangan());
            List<KeperawatanRawatJalan> keperawatanRawatJalanList = getByCriteria(kp);
            if ("resume_medis".equalsIgnoreCase(kp.getJenis())) {
                for (KeperawatanRawatJalan bean : list) {
                    ItSimrsAsesmenKeperawatanRawatJalanEntity keperawatanRawatJalanEntity = new ItSimrsAsesmenKeperawatanRawatJalanEntity();
                    keperawatanRawatJalanEntity.setIdKeperawatanRawatJalan("KRJ" + keperawatanRawatJalanDao.getNextSeq());
                    keperawatanRawatJalanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    keperawatanRawatJalanEntity.setParameter(bean.getParameter());
                    keperawatanRawatJalanEntity.setJawaban(bean.getJawaban());
                    keperawatanRawatJalanEntity.setKeterangan(bean.getKeterangan());
                    keperawatanRawatJalanEntity.setJenis(bean.getJenis());
                    keperawatanRawatJalanEntity.setScore(bean.getScore());
                    keperawatanRawatJalanEntity.setAction(bean.getAction());
                    keperawatanRawatJalanEntity.setFlag(bean.getFlag());
                    keperawatanRawatJalanEntity.setCreatedDate(bean.getCreatedDate());
                    keperawatanRawatJalanEntity.setCreatedWho(bean.getCreatedWho());
                    keperawatanRawatJalanEntity.setLastUpdate(bean.getLastUpdate());
                    keperawatanRawatJalanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    keperawatanRawatJalanEntity.setTipe(bean.getTipe());
                    keperawatanRawatJalanEntity.setNamaTerang(bean.getNamaTerang());
                    keperawatanRawatJalanEntity.setSip(bean.getSip());

                    try {
                        keperawatanRawatJalanDao.addAndSave(keperawatanRawatJalanEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            } else {
                if (keperawatanRawatJalanList.size() > 0) {
                    response.setStatus("error");
                    response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
                } else {
                    for (KeperawatanRawatJalan bean : list) {
                        ItSimrsAsesmenKeperawatanRawatJalanEntity keperawatanRawatJalanEntity = new ItSimrsAsesmenKeperawatanRawatJalanEntity();
                        keperawatanRawatJalanEntity.setIdKeperawatanRawatJalan("KRJ" + keperawatanRawatJalanDao.getNextSeq());
                        keperawatanRawatJalanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                        keperawatanRawatJalanEntity.setParameter(bean.getParameter());
                        keperawatanRawatJalanEntity.setJawaban(bean.getJawaban());
                        keperawatanRawatJalanEntity.setKeterangan(bean.getKeterangan());
                        keperawatanRawatJalanEntity.setJenis(bean.getJenis());
                        keperawatanRawatJalanEntity.setScore(bean.getScore());
                        keperawatanRawatJalanEntity.setAction(bean.getAction());
                        keperawatanRawatJalanEntity.setFlag(bean.getFlag());
                        keperawatanRawatJalanEntity.setCreatedDate(bean.getCreatedDate());
                        keperawatanRawatJalanEntity.setCreatedWho(bean.getCreatedWho());
                        keperawatanRawatJalanEntity.setLastUpdate(bean.getLastUpdate());
                        keperawatanRawatJalanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        keperawatanRawatJalanEntity.setTipe(bean.getTipe());
                        keperawatanRawatJalanEntity.setNamaTerang(bean.getNamaTerang());
                        keperawatanRawatJalanEntity.setSip(bean.getSip());

                        if("nyeri".equalsIgnoreCase(bean.getKeterangan())){
                            if("Intensitas".equalsIgnoreCase(bean.getParameter())){
                                HeaderCheckup headerCheckup = new HeaderCheckup();
                                headerCheckup.setNoCheckup(bean.getNoCheckup());
                                headerCheckup.setNyeri(bean.getJawaban());
                                updateKlinis(headerCheckup);
                            }
                        }

                        if("nyeri".equalsIgnoreCase(bean.getKeterangan())){
                            if("Total Skor Nyeri".equalsIgnoreCase(bean.getParameter())){
                                HeaderCheckup headerCheckup = new HeaderCheckup();
                                headerCheckup.setNoCheckup(bean.getNoCheckup());
                                headerCheckup.setNyeri(bean.getJawaban());
                                updateKlinis(headerCheckup);
                            }
                        }

                        if("resiko_jatuh".equalsIgnoreCase(bean.getKeterangan())){
                            if("Resiko Jatuh".equalsIgnoreCase(bean.getParameter())){
                                HeaderCheckup headerCheckup = new HeaderCheckup();
                                headerCheckup.setNoCheckup(bean.getNoCheckup());
                                headerCheckup.setResikoJatuh(bean.getJawaban());
                                updateKlinis(headerCheckup);
                            }
                        }

                        try {
                            keperawatanRawatJalanDao.addAndSave(keperawatanRawatJalanEntity);
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

    private void updateKlinis(HeaderCheckup bean){
        ItSimrsHeaderChekupEntity entity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
        if(entity != null){
            if(bean.getNyeri() != null){
                entity.setNyeri(bean.getNyeri());
            }
            if(bean.getResikoJatuh() != null){
                entity.setResikoJatuh(bean.getResikoJatuh());
            }
            try {
                headerCheckupDao.updateAndSave(entity);
            }catch (HibernateException e){
                throw new GeneralBOException("Error"+e.getMessage());
            }
        }
    }

    @Override
    public CrudResponse saveDelete(KeperawatanRawatJalan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        List<ItSimrsAsesmenKeperawatanRawatJalanEntity> entityList = new ArrayList<>();

        try {
            entityList = keperawatanRawatJalanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }
        if (entityList.size() > 0) {
            for (ItSimrsAsesmenKeperawatanRawatJalanEntity entity : entityList) {
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setAction("D");
                entity.setFlag("N");
                try {
                    keperawatanRawatJalanDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public HeaderCheckup getDataResumeMedis(KeperawatanRawatJalan bean) throws GeneralBOException {
        HeaderCheckup checkup = new HeaderCheckup();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }
            List<ItSimrsAsesmenKeperawatanRawatJalanEntity> entityList = new ArrayList<>();
            try {
                entityList = keperawatanRawatJalanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (entityList.size() > 0) {
                for (ItSimrsAsesmenKeperawatanRawatJalanEntity entity : entityList) {
                    if (entity.getParameter() != null) {
                        if ("penunjang_lab".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setPenunjangLab(entity.getJawaban());
                        }
                        if ("penunjang_radiologi".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setPenunjangRadiologi(entity.getJawaban());
                        }
                        if ("pasien".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setTtdPasien(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                        if ("dokter".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setTtdDokter(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                        }
                        if ("nama_pasien".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setNama(entity.getJawaban());
                        }
                        if ("nama_dokter".equalsIgnoreCase(entity.getParameter())) {
                            checkup.setNamaDokter(entity.getJawaban());
                        }
                    }
                }
            }
        }
        return checkup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKeperawatanRawatJalanDao(KeperawatanRawatJalanDao keperawatanRawatJalanDao) {
        this.keperawatanRawatJalanDao = keperawatanRawatJalanDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }
}
