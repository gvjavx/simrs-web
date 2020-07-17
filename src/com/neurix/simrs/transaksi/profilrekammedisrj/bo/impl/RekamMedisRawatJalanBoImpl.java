package com.neurix.simrs.transaksi.profilrekammedisrj.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.dao.RekamMedisRawatJalanDao;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.ItSimrsRekamMedisRawatJalanEntity;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekamMedisRawatJalanBoImpl implements RekamMedisRawatJalanBo {

    private static transient Logger logger = Logger.getLogger(RekamMedisRawatJalanBoImpl.class);
    private RekamMedisRawatJalanDao rekamMedisRawatJalanDao;

    @Override
    public List<RekamMedisRawatJalan> getByCriteria(RekamMedisRawatJalan bean) throws GeneralBOException {
        List<RekamMedisRawatJalan> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdProfilRekamMedisRj() != null && !"".equalsIgnoreCase(bean.getIdProfilRekamMedisRj())) {
                hsCriteria.put("id_profil_rekam_medis_rj", bean.getIdProfilRekamMedisRj());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            List<ItSimrsRekamMedisRawatJalanEntity> entityList = new ArrayList<>();
            try {
                entityList = rekamMedisRawatJalanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsRekamMedisRawatJalanEntity entity : entityList) {
                    RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                    rekamMedisRawatJalan.setIdProfilRekamMedisRj(entity.getIdProfilRekamMedisRj());
                    rekamMedisRawatJalan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    rekamMedisRawatJalan.setWaktu(entity.getWaktu());
                    rekamMedisRawatJalan.setAnamnese(entity.getAnamnese());
                    rekamMedisRawatJalan.setPemeriksaanFisik(entity.getPemeriksaanFisik());
                    rekamMedisRawatJalan.setDiagnosa(entity.getDiagnosa());
                    rekamMedisRawatJalan.setPlaning(entity.getPlaning());
                    rekamMedisRawatJalan.setKeterangan(entity.getKeterangan());
                    rekamMedisRawatJalan.setAction(entity.getAction());
                    rekamMedisRawatJalan.setFlag(entity.getFlag());
                    rekamMedisRawatJalan.setCreatedDate(entity.getCreatedDate());
                    rekamMedisRawatJalan.setCreatedWho(entity.getCreatedWho());
                    rekamMedisRawatJalan.setLastUpdate(entity.getLastUpdate());
                    rekamMedisRawatJalan.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(rekamMedisRawatJalan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(RekamMedisRawatJalan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {

            ItSimrsRekamMedisRawatJalanEntity rekamMedisRawatJalanEntity = new ItSimrsRekamMedisRawatJalanEntity();
            rekamMedisRawatJalanEntity.setIdProfilRekamMedisRj("RMJ" + rekamMedisRawatJalanDao.getNextSeq());
            rekamMedisRawatJalanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            rekamMedisRawatJalanEntity.setWaktu(bean.getWaktu());
            rekamMedisRawatJalanEntity.setAnamnese(bean.getAnamnese());
            rekamMedisRawatJalanEntity.setPemeriksaanFisik(bean.getPemeriksaanFisik());
            rekamMedisRawatJalanEntity.setDiagnosa(bean.getDiagnosa());
            rekamMedisRawatJalanEntity.setPlaning(bean.getPlaning());
            rekamMedisRawatJalanEntity.setKeterangan("ringkasan_rj_pasien");
            rekamMedisRawatJalanEntity.setAction(bean.getAction());
            rekamMedisRawatJalanEntity.setFlag(bean.getFlag());
            rekamMedisRawatJalanEntity.setCreatedDate(bean.getCreatedDate());
            rekamMedisRawatJalanEntity.setCreatedWho(bean.getCreatedWho());
            rekamMedisRawatJalanEntity.setLastUpdate(bean.getLastUpdate());
            rekamMedisRawatJalanEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                rekamMedisRawatJalanDao.addAndSave(rekamMedisRawatJalanEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error(e.getMessage());
            }
        }

        return response;
    }

    @Override
    public CrudResponse saveEdit(RekamMedisRawatJalan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdProfilRekamMedisRj() != null && !"".equalsIgnoreCase(bean.getIdProfilRekamMedisRj())) {
                hsCriteria.put("id_profil_rekam_medis_rj", bean.getIdProfilRekamMedisRj());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            List<ItSimrsRekamMedisRawatJalanEntity> entityList = new ArrayList<>();

            try {
                entityList = rekamMedisRawatJalanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                ItSimrsRekamMedisRawatJalanEntity entity = entityList.get(0);
                if (entity.getIdProfilRekamMedisRj() != null) {

                    entity.setWaktu(bean.getWaktu());

                    if (bean.getAnamnese() != null && !"".equalsIgnoreCase(bean.getAnamnese())) {
                        entity.setAnamnese(bean.getAnamnese());
                    }
                    if (bean.getPemeriksaanFisik() != null && !"".equalsIgnoreCase(bean.getPemeriksaanFisik())) {
                        entity.setPemeriksaanFisik(bean.getPemeriksaanFisik());
                    }
                    if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa())) {
                        entity.setDiagnosa(bean.getDiagnosa());
                    }
                    if (bean.getPlaning() != null && !"".equalsIgnoreCase(bean.getPlaning())) {
                        entity.setPlaning(bean.getPlaning());
                    }

                    entity.setAction(bean.getAction());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        rekamMedisRawatJalanDao.updateAndSave(entity);
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

    public static Logger getLogger() {
        return logger;
    }

    public void setRekamMedisRawatJalanDao(RekamMedisRawatJalanDao rekamMedisRawatJalanDao) {
        this.rekamMedisRawatJalanDao = rekamMedisRawatJalanDao;
    }
}
