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
                    if(entity.getTtdDpjp() != null && !"".equalsIgnoreCase(entity.getTtdDpjp())){
                        catatan.setTtdDpjp(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdDpjp());
                    }
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
                    catatan.setKesadaran(entity.getKesadaran());
                    catatan.setSpo2(entity.getSpo2());
                    catatan.setO2(entity.getO2());
                    catatan.setEws(entity.getEws());
                    catatan.setKesimpulan(entity.getKesimpulan());
                    catatan.setMonitoring(entity.getMonitoring());
                    catatan.setDataEws(entity.getDataEws());
                    catatan.setNamaPemberi(entity.getNamaPemberi());
                    catatan.setSipPemberi(entity.getSipPemberi());
                    catatan.setTtdPemberi(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtdPemberi());
                    catatan.setNamaPemberi(entity.getNamaPenerima());
                    catatan.setSipPenerima(entity.getSipPenerima());
                    if(entity.getTtdPenerima() != null && !"".equalsIgnoreCase(entity.getTtdPenerima())){
                        catatan.setTtdPenerima(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getNamaPenerima());
                    }
                    catatan.setTipe(entity.getTipe());
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
            catatanTerintegrasiEntity.setKesadaran(bean.getKesadaran());
            catatanTerintegrasiEntity.setSpo2(bean.getSpo2());
            catatanTerintegrasiEntity.setO2(bean.getO2());
            catatanTerintegrasiEntity.setEws(bean.getEws());
            catatanTerintegrasiEntity.setKesimpulan(bean.getKesimpulan());
            catatanTerintegrasiEntity.setMonitoring(bean.getMonitoring());
            catatanTerintegrasiEntity.setDataEws(bean.getDataEws());

            catatanTerintegrasiEntity.setNamaPemberi(bean.getNamaPemberi());
            catatanTerintegrasiEntity.setSipPemberi(bean.getSipPemberi());
            catatanTerintegrasiEntity.setTtdPemberi(bean.getTtdPemberi());

            catatanTerintegrasiEntity.setNamaPenerima(bean.getNamaPenerima());
            catatanTerintegrasiEntity.setSipPenerima(bean.getNamaPenerima());
            catatanTerintegrasiEntity.setTtdPenerima(bean.getTtdPenerima());
            catatanTerintegrasiEntity.setTipe(bean.getTipe());

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

    @Override
    public void saveEdit(CatatanTerintegrasi bean) throws GeneralBOException {
        ItSimrsCatatanTerintegrasiEntity entity = new ItSimrsCatatanTerintegrasiEntity();
        try {
            entity = catatanTerintegrasiDao.getById("idCatatanTerintegrasi", bean.getIdCatatanTerintegrasi());
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new GeneralBOException("Error when get id cppt, "+e.getMessage());
        }
        if (entity != null) {
            if(bean.getTtdDpjp() != null && !"".equalsIgnoreCase(bean.getTtdDpjp())){
                entity.setTtdDpjp(bean.getTtdDpjp());
            }
            if(bean.getNamaDokter() != null && !"".equalsIgnoreCase(bean.getNamaDokter())){
                entity.setNamaDokter(bean.getNamaDokter());
            }
            if(bean.getSipDokter() != null && !"".equalsIgnoreCase(bean.getSipDokter())){
                entity.setSipDokter(bean.getSipDokter());
            }

            if(bean.getTtdPenerima() != null && !"".equalsIgnoreCase(bean.getTtdPenerima())){
                entity.setTtdPenerima(bean.getTtdPenerima());
            }
            if(bean.getNamaPemberi() != null && !"".equalsIgnoreCase(bean.getNamaPemberi())){
                entity.setNamaPenerima(bean.getNamaPemberi());
            }
            if(bean.getSipPemberi() != null && !"".equalsIgnoreCase(bean.getSipPemberi())){
                entity.setSipPenerima(bean.getSipPemberi());
            }

            entity.setFlag("Y");
            entity.setAction("U");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                catatanTerintegrasiDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
                throw new GeneralBOException("Error when saveedit cppt, "+e.getMessage());
            }
        } else {
            logger.error("Data cppt tidak ditemukan");
            throw new GeneralBOException("Data cppt tidak ditemukan");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCatatanTerintegrasiDao(CatatanTerintegrasiDao catatanTerintegrasiDao) {
        this.catatanTerintegrasiDao = catatanTerintegrasiDao;
    }
}
