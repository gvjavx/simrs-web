package com.neurix.simrs.transaksi.edukasipasien.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.edukasipasien.bo.EdukasiPasienBo;
import com.neurix.simrs.transaksi.edukasipasien.dao.EdukasiPasienDao;
import com.neurix.simrs.transaksi.edukasipasien.model.EdukasiPasien;
import com.neurix.simrs.transaksi.edukasipasien.model.ItSimrsEdukasiPasienEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdukasiPasienBoImpl implements EdukasiPasienBo {
    private static transient Logger logger = Logger.getLogger(EdukasiPasienBoImpl.class);
    private EdukasiPasienDao edukasiPasienDao;

    @Override
    public List<EdukasiPasien> getByCriteria(EdukasiPasien bean) throws GeneralBOException {
        List<EdukasiPasien> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdEdukasiPasien() != null && !"".equalsIgnoreCase(bean.getIdEdukasiPasien())) {
                hsCriteria.put("id_edukasi_pasien", bean.getIdEdukasiPasien());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsEdukasiPasienEntity> entityList = new ArrayList<>();

            try {
                entityList = edukasiPasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsEdukasiPasienEntity entity : entityList) {
                    EdukasiPasien edukasiPasien = new EdukasiPasien();
                    edukasiPasien.setIdEdukasiPasien(entity.getIdEdukasiPasien());
                    edukasiPasien.setIdDetailCheckup(entity.getIdDetailCheckup());
                    edukasiPasien.setDurasi(entity.getDurasi());
                    edukasiPasien.setEdukator(entity.getEdukator());
                    edukasiPasien.setEdukasi(entity.getEdukasi());
                    edukasiPasien.setPemahamanAwal(entity.getPemahamanAwal());
                    edukasiPasien.setMetodeEdukasi(entity.getMetodeEdukasi());
                    edukasiPasien.setMediaEdukasi(entity.getMediaEdukasi());
                    edukasiPasien.setEvaluasi(entity.getEvaluasi());

                    if(entity.getTtdPasien() != null){
                        edukasiPasien.setTtdPasien(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdPasien());
                    }
                    if(entity.getTtdStaf() != null){
                        edukasiPasien.setTtdStaf(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdStaf());
                    }

                    edukasiPasien.setKeterangan(entity.getKeterangan());
                    edukasiPasien.setAction(entity.getAction());
                    edukasiPasien.setFlag(entity.getFlag());
                    edukasiPasien.setCreatedDate(entity.getCreatedDate());
                    edukasiPasien.setCreatedWho(entity.getCreatedWho());
                    edukasiPasien.setLastUpdate(entity.getLastUpdate());
                    edukasiPasien.setLastUpdateWho(entity.getLastUpdateWho());
                    edukasiPasien.setTipe(entity.getTipe());
                    list.add(edukasiPasien);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<EdukasiPasien> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (EdukasiPasien entity : list) {
                ItSimrsEdukasiPasienEntity edukasiPasien = new ItSimrsEdukasiPasienEntity();
                edukasiPasien.setIdEdukasiPasien("EPT"+edukasiPasienDao.getNextSeq());
                edukasiPasien.setIdDetailCheckup(entity.getIdDetailCheckup());
                edukasiPasien.setDurasi(entity.getDurasi());
                edukasiPasien.setEdukator(entity.getEdukator());
                edukasiPasien.setEdukasi(entity.getEdukasi());
                edukasiPasien.setPemahamanAwal(entity.getPemahamanAwal());
                edukasiPasien.setMetodeEdukasi(entity.getMetodeEdukasi());
                edukasiPasien.setMediaEdukasi(entity.getMediaEdukasi());
                edukasiPasien.setEvaluasi(entity.getEvaluasi());
                edukasiPasien.setTtdPasien(entity.getTtdPasien());
                edukasiPasien.setTtdStaf(entity.getTtdStaf());
                edukasiPasien.setKeterangan(entity.getKeterangan());
                edukasiPasien.setAction(entity.getAction());
                edukasiPasien.setFlag(entity.getFlag());
                edukasiPasien.setCreatedDate(entity.getCreatedDate());
                edukasiPasien.setCreatedWho(entity.getCreatedWho());
                edukasiPasien.setLastUpdate(entity.getLastUpdate());
                edukasiPasien.setLastUpdateWho(entity.getLastUpdateWho());
                edukasiPasien.setTipe(entity.getTipe());

                try {
                    edukasiPasienDao.addAndSave(edukasiPasien);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setEdukasiPasienDao(EdukasiPasienDao edukasiPasienDao) {
        this.edukasiPasienDao = edukasiPasienDao;
    }
}
