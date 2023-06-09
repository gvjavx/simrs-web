package com.neurix.simrs.transaksi.catatanterintegrasi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.catatanterintegrasi.bo.CatatanTerintegrasiBo;
import com.neurix.simrs.transaksi.catatanterintegrasi.dao.CatatanTerintegrasiDao;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.CatatanTerintegrasi;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.ItSimrsCatatanTerintegrasiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatatanTerintegrasiBoImpl implements CatatanTerintegrasiBo {

    private static transient Logger logger = Logger.getLogger(CatatanTerintegrasiBoImpl.class);
    private CatatanTerintegrasiDao catatanTerintegrasiDao;

    @Override
    public List<CatatanTerintegrasi> getByCriteria(CatatanTerintegrasi bean) throws GeneralBOException {
        List<CatatanTerintegrasi> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdCatatanTerintegrasi() != null && !"".equalsIgnoreCase(bean.getIdCatatanTerintegrasi())) {
                hsCriteria.put("id_catatan_terintegrasi", bean.getIdCatatanTerintegrasi());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsCatatanTerintegrasiEntity> entityList = new ArrayList<>();
            try {
                entityList = catatanTerintegrasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsCatatanTerintegrasiEntity entity : entityList) {
                    CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                    catatan.setIdCatatanTerintegrasi(entity.getIdCatatanTerintegrasi());
                    catatan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    catatan.setWaktu(entity.getWaktu());
                    catatan.setPpa(entity.getPpa());
                    catatan.setSubjective(entity.getSubjective());
                    catatan.setIntruksi(entity.getIntruksi());
                    catatan.setTtdPetugas(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdPetugas());
                    catatan.setTtdDpjp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdDpjp());
                    catatan.setKeterangan(entity.getKeterangan());
                    catatan.setAction(entity.getAction());
                    catatan.setFlag(entity.getFlag());
                    catatan.setCreatedDate(entity.getCreatedDate());
                    catatan.setCreatedWho(entity.getCreatedWho());
                    catatan.setLastUpdate(entity.getLastUpdate());
                    catatan.setLastUpdateWho(entity.getLastUpdateWho());
                    catatan.setNadi(entity.getNadi());
                    catatan.setSuhu(entity.getSuhu());
                    catatan.setRr(entity.getRr());
                    catatan.setTensi(entity.getTensi());
                    catatan.setObjective(entity.getObjective());
                    catatan.setAssesment(entity.getAssesment());
                    catatan.setPlanning(entity.getPlanning());
                    catatan.setNamaDokter(entity.getNamaDokter());
                    catatan.setNamaPetugas(entity.getNamaPetugas());
                    catatan.setSipDokter(entity.getSipDokter());
                    catatan.setSipPetugas(entity.getSipPetugas());
                    list.add(catatan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(CatatanTerintegrasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {

            ItSimrsCatatanTerintegrasiEntity catatanTerintegrasiEntity = new ItSimrsCatatanTerintegrasiEntity();
            catatanTerintegrasiEntity.setIdCatatanTerintegrasi("CTP" + catatanTerintegrasiDao.getNextSeq());
            catatanTerintegrasiEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            catatanTerintegrasiEntity.setWaktu(bean.getWaktu());
            catatanTerintegrasiEntity.setPpa(bean.getPpa());
            catatanTerintegrasiEntity.setSubjective(bean.getSubjective());
            catatanTerintegrasiEntity.setIntruksi(bean.getIntruksi());
            catatanTerintegrasiEntity.setTtdPetugas(bean.getTtdPetugas());
            catatanTerintegrasiEntity.setTtdDpjp(bean.getTtdDpjp());
            catatanTerintegrasiEntity.setKeterangan(bean.getKeterangan());
            catatanTerintegrasiEntity.setAction(bean.getAction());
            catatanTerintegrasiEntity.setFlag(bean.getFlag());
            catatanTerintegrasiEntity.setCreatedDate(bean.getCreatedDate());
            catatanTerintegrasiEntity.setCreatedWho(bean.getCreatedWho());
            catatanTerintegrasiEntity.setLastUpdate(bean.getLastUpdate());
            catatanTerintegrasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            catatanTerintegrasiEntity.setNadi(bean.getNadi());
            catatanTerintegrasiEntity.setSuhu(bean.getSuhu());
            catatanTerintegrasiEntity.setRr(bean.getRr());
            catatanTerintegrasiEntity.setTensi(bean.getTensi());
            catatanTerintegrasiEntity.setObjective(bean.getObjective());
            catatanTerintegrasiEntity.setAssesment(bean.getAssesment());
            catatanTerintegrasiEntity.setPlanning(bean.getPlanning());
            catatanTerintegrasiEntity.setNamaDokter(bean.getNamaDokter());
            catatanTerintegrasiEntity.setNamaPetugas(bean.getNamaPetugas());
            catatanTerintegrasiEntity.setSipDokter(bean.getSipDokter());
            catatanTerintegrasiEntity.setSipPetugas(bean.getSipPetugas());

            try {
                catatanTerintegrasiDao.addAndSave(catatanTerintegrasiEntity);
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
    public CrudResponse saveDelete(CatatanTerintegrasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsCatatanTerintegrasiEntity entity = new ItSimrsCatatanTerintegrasiEntity();
        try {
            entity = catatanTerintegrasiDao.getById("idCatatanTerintegrasi", bean.getIdCatatanTerintegrasi());
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }
        if (entity != null) {
            entity.setFlag("N");
            entity.setAction("D");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                catatanTerintegrasiDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error, " + e.getMessage());
                logger.error(e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCatatanTerintegrasiDao(CatatanTerintegrasiDao catatanTerintegrasiDao) {
        this.catatanTerintegrasiDao = catatanTerintegrasiDao;
    }
}
