package com.neurix.simrs.transaksi.asesmenicu.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.KeseimbanganIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.dao.KeseimbanganIcuDao;
import com.neurix.simrs.transaksi.asesmenicu.model.ItSimrsKeseimbanganIcuEntity;
import com.neurix.simrs.transaksi.asesmenicu.model.KeseimbanganIcu;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeseimbanganIcuBoImpl implements KeseimbanganIcuBo {
    private static transient Logger logger = Logger.getLogger(KeseimbanganIcuBoImpl.class);
    private KeseimbanganIcuDao keseimbanganIcuDao;

    @Override
    public List<KeseimbanganIcu> getByCriteria(KeseimbanganIcu bean) throws GeneralBOException {
        List<KeseimbanganIcu> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdKeseimbanganIcu() != null && !"".equalsIgnoreCase(bean.getIdKeseimbanganIcu())) {
                hsCriteria.put("id_keseimbangan_icu", bean.getIdKeseimbanganIcu());
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

            List<ItSimrsKeseimbanganIcuEntity> entityList = new ArrayList<>();

            try {
                entityList = keseimbanganIcuDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsKeseimbanganIcuEntity entity : entityList) {
                    KeseimbanganIcu asesmenIcu = new KeseimbanganIcu();
                    asesmenIcu.setIdKeseimbanganIcu(entity.getIdKeseimbanganIcu());
                    asesmenIcu.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenIcu.setWaktu(entity.getWaktu());
                    asesmenIcu.setJenis(entity.getJenis());
                    asesmenIcu.setKeterangan(entity.getKeterangan());
                    asesmenIcu.setNilai(entity.getNilai());
                    asesmenIcu.setAction(entity.getAction());
                    asesmenIcu.setFlag(entity.getFlag());
                    asesmenIcu.setCreatedDate(entity.getCreatedDate());
                    asesmenIcu.setCreatedWho(entity.getCreatedWho());
                    asesmenIcu.setLastUpdate(entity.getLastUpdate());
                    asesmenIcu.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(asesmenIcu);
                }
            }
        }

        return list;
    }

    @Override
    public void saveAdd(List<KeseimbanganIcu> list) throws GeneralBOException {
        if (list.size() > 0) {
            KeseimbanganIcu asesmenIcu = list.get(0);
            KeseimbanganIcu icu = new KeseimbanganIcu();
            icu.setIdDetailCheckup(asesmenIcu.getIdDetailCheckup());
            icu.setKeterangan(asesmenIcu.getKeterangan());
            for (KeseimbanganIcu bean : list) {
                ItSimrsKeseimbanganIcuEntity asesmenIcuEntity = new ItSimrsKeseimbanganIcuEntity();
                asesmenIcuEntity.setIdKeseimbanganIcu(keseimbanganIcuDao.getNextSeq());
                asesmenIcuEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                asesmenIcuEntity.setWaktu(bean.getWaktu());
                asesmenIcuEntity.setJenis(bean.getJenis());
                asesmenIcuEntity.setKeterangan(bean.getKeterangan());
                asesmenIcuEntity.setNilai(bean.getNilai());
                asesmenIcuEntity.setAction(bean.getAction());
                asesmenIcuEntity.setFlag(bean.getFlag());
                asesmenIcuEntity.setCreatedDate(bean.getCreatedDate());
                asesmenIcuEntity.setCreatedWho(bean.getCreatedWho());
                asesmenIcuEntity.setLastUpdate(bean.getLastUpdate());
                asesmenIcuEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    keseimbanganIcuDao.addAndSave(asesmenIcuEntity);
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                    throw new GeneralBOException("Error, "+e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveDelete(KeseimbanganIcu bean) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        if(bean.getIdKeseimbanganIcu() != null){
            hsCriteria.put("id_keseimbangan_icu", bean.getIdKeseimbanganIcu());
        }
        if(bean.getIdDetailCheckup() != null){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if(bean.getKeterangan() != null){
            hsCriteria.put("keterangan", bean.getKeterangan());
        }
        if (bean.getCreatedDate() != null && !"".equalsIgnoreCase(bean.getCreatedWho())) {
            hsCriteria.put("created_date", bean.getCreatedDate());
        }
        List<ItSimrsKeseimbanganIcuEntity> entityList = new ArrayList<>();
        try {
            entityList = keseimbanganIcuDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new GeneralBOException("Error, "+e.getMessage());
        }
        if (entityList.size() > 0) {
            for (ItSimrsKeseimbanganIcuEntity entity : entityList) {
                entity.setFlag("N");
                entity.setAction("D");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    keseimbanganIcuDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                    throw new GeneralBOException("Error, "+e.getMessage());
                }
            }
        } else {
            throw new GeneralBOException("Found Error, Data yang dicari tidak ditemukan...!");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKeseimbanganIcuDao(KeseimbanganIcuDao keseimbanganIcuDao) {
        this.keseimbanganIcuDao = keseimbanganIcuDao;
    }
}
