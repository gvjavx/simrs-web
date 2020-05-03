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

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdCatatanTerintegrasi() != null && !"".equalsIgnoreCase(bean.getIdCatatanTerintegrasi())){
                hsCriteria.put("id_catatan_terintegrasi", bean.getIdCatatanTerintegrasi());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            List<ItSimrsCatatanTerintegrasiEntity> entityList = new ArrayList<>();
            try {
                entityList = catatanTerintegrasiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsCatatanTerintegrasiEntity entity: entityList){
                    CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                    catatan.setIdCatatanTerintegrasi(entity.getIdCatatanTerintegrasi());
                    catatan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    catatan.setWaktu(entity.getWaktu());
                    catatan.setPpa(entity.getPpa());
                    catatan.setJenis(entity.getJenis());
                    catatan.setIntruksi(entity.getIntruksi());
                    catatan.setTtdPetugas(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdPetugas());
                    catatan.setTtdDpjp(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdDpjp());
                    catatan.setKeterangan(entity.getKeterangan());
                    catatan.setAction(entity.getAction());
                    catatan.setFlag(entity.getFlag());
                    catatan.setCreatedDate(entity.getCreatedDate());
                    catatan.setCreatedWho(entity.getCreatedWho());
                    catatan.setLastUpdate(entity.getLastUpdate());
                    catatan.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(catatan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(CatatanTerintegrasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsCatatanTerintegrasiEntity catatanTerintegrasiEntity = new ItSimrsCatatanTerintegrasiEntity();
            catatanTerintegrasiEntity.setIdCatatanTerintegrasi("CTP"+catatanTerintegrasiDao.getNextSeq());
            catatanTerintegrasiEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            catatanTerintegrasiEntity.setWaktu(bean.getWaktu());
            catatanTerintegrasiEntity.setPpa(bean.getPpa());
            catatanTerintegrasiEntity.setJenis(bean.getJenis());
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

            try {
                catatanTerintegrasiDao.addAndSave(catatanTerintegrasiEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error "+e.getMessage());
                logger.error(e.getMessage());
            }
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
