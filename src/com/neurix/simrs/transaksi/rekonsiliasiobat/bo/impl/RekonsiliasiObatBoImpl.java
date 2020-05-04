package com.neurix.simrs.transaksi.rekonsiliasiobat.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekonsiliasiobat.bo.RekonsiliasiObatBo;
import com.neurix.simrs.transaksi.rekonsiliasiobat.dao.RekonsiliasiObatDao;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.RekonsiliasiObat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekonsiliasiObatBoImpl implements RekonsiliasiObatBo {

    private static transient Logger logger = Logger.getLogger(RekonsiliasiObatBoImpl.class);
    private RekonsiliasiObatDao rekonsiliasiObatDao;

    @Override
    public List<RekonsiliasiObat> getByCriteria(RekonsiliasiObat bean) throws GeneralBOException {
        List<RekonsiliasiObat> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
                hsCriteria.put("id", bean.getId());
            }

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }

            List<ItSimrsRekonsiliasiObatEntity> entityList = new ArrayList<>();
            try {
                entityList = rekonsiliasiObatDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsRekonsiliasiObatEntity entity: entityList){
                    RekonsiliasiObat rekonsiliasiObat = new RekonsiliasiObat();
                    rekonsiliasiObat.setId(entity.getId());
                    rekonsiliasiObat.setIdDetailCheckup(entity.getIdDetailCheckup());
                    rekonsiliasiObat.setNoCheckup(entity.getNoCheckup());
                    rekonsiliasiObat.setNamaObat(entity.getNamaObat());
                    rekonsiliasiObat.setDosis(entity.getDosis());
                    rekonsiliasiObat.setSatuanDosis(entity.getSatuanDosis());
                    rekonsiliasiObat.setAturanPakai(entity.getAturanPakai());
                    rekonsiliasiObat.setIndikasi(entity.getIndikasi());
                    rekonsiliasiObat.setDiteruskan(entity.getDiteruskan());
                    rekonsiliasiObat.setTanggal(entity.getTanggal());
                    rekonsiliasiObat.setRuangan(entity.getRuangan());
                    rekonsiliasiObat.setTtdPasien(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdPasien());
                    rekonsiliasiObat.setTtdApoteker(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdApoteker());
                    rekonsiliasiObat.setAction(entity.getAction());
                    rekonsiliasiObat.setFlag(entity.getFlag());
                    rekonsiliasiObat.setCreatedDate(entity.getCreatedDate());
                    rekonsiliasiObat.setCreatedWho(entity.getCreatedWho());
                    rekonsiliasiObat.setLastUpdate(entity.getLastUpdate());
                    rekonsiliasiObat.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(rekonsiliasiObat);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(RekonsiliasiObat bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsRekonsiliasiObatEntity rekonsiliasiObatEntity = new ItSimrsRekonsiliasiObatEntity();
            rekonsiliasiObatEntity.setId("RSO"+rekonsiliasiObatDao.getNextSeq());
            rekonsiliasiObatEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            rekonsiliasiObatEntity.setNoCheckup(bean.getNoCheckup());
            rekonsiliasiObatEntity.setNamaObat(bean.getNamaObat());
            rekonsiliasiObatEntity.setDosis(bean.getDosis());
            rekonsiliasiObatEntity.setSatuanDosis(bean.getSatuanDosis());
            rekonsiliasiObatEntity.setAturanPakai(bean.getAturanPakai());
            rekonsiliasiObatEntity.setIndikasi(bean.getIndikasi());
            rekonsiliasiObatEntity.setDiteruskan(bean.getDiteruskan());
            rekonsiliasiObatEntity.setTanggal(bean.getTanggal());
            rekonsiliasiObatEntity.setRuangan(bean.getRuangan());
            rekonsiliasiObatEntity.setTtdPasien(bean.getTtdPasien());
            rekonsiliasiObatEntity.setTtdApoteker(bean.getTtdApoteker());
            rekonsiliasiObatEntity.setAction(bean.getAction());
            rekonsiliasiObatEntity.setFlag(bean.getFlag());
            rekonsiliasiObatEntity.setCreatedDate(bean.getCreatedDate());
            rekonsiliasiObatEntity.setCreatedWho(bean.getCreatedWho());
            rekonsiliasiObatEntity.setLastUpdate(bean.getLastUpdate());
            rekonsiliasiObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                rekonsiliasiObatDao.addAndSave(rekonsiliasiObatEntity);
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

    public void setRekonsiliasiObatDao(RekonsiliasiObatDao rekonsiliasiObatDao) {
        this.rekonsiliasiObatDao = rekonsiliasiObatDao;
    }
}
