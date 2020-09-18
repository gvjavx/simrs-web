package com.neurix.simrs.transaksi.mpp.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.mpp.bo.MppBo;
import com.neurix.simrs.transaksi.mpp.dao.MppDao;
import com.neurix.simrs.transaksi.mpp.model.ItSimrsMppEntity;
import com.neurix.simrs.transaksi.mpp.model.Mpp;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MppBoImpl implements MppBo {
    private static transient Logger logger = Logger.getLogger(MppBoImpl.class);
    private MppDao mppDao;

    @Override
    public List<Mpp> getByCriteria(Mpp bean) throws GeneralBOException {
        List<Mpp> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdMpp() != null && !"".equalsIgnoreCase(bean.getIdMpp())) {
                hsCriteria.put("id_mpp", bean.getIdMpp());
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

            List<ItSimrsMppEntity> entityList = new ArrayList<>();

            try {
                entityList = mppDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsMppEntity entity : entityList) {
                    Mpp mpp = new Mpp();
                    mpp.setIdMpp(entity.getIdMpp());
                    mpp.setIdDetailCheckup(entity.getIdDetailCheckup());
                    mpp.setParameter(entity.getParameter());
                    mpp.setJawaban(entity.getJawaban());
                    mpp.setKeterangan(entity.getKeterangan());
                    mpp.setJenis(entity.getJenis());
                    mpp.setWaktu(entity.getWaktu());
                    mpp.setAction(entity.getAction());
                    mpp.setFlag(entity.getFlag());
                    mpp.setCreatedDate(entity.getCreatedDate());
                    mpp.setCreatedWho(entity.getCreatedWho());
                    mpp.setLastUpdate(entity.getLastUpdate());
                    mpp.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(mpp);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<Mpp> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            Mpp mpp = list.get(0);
            Mpp mp = new Mpp();
            mp.setIdDetailCheckup(mpp.getIdDetailCheckup());
            mp.setKeterangan(mpp.getKeterangan());
            List<Mpp> mppList = getByCriteria(mp);
            if (mppList.size() > 0) {
                response.setStatus("error");
                response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
            } else {
                for (Mpp bean : list) {
                    ItSimrsMppEntity asesmenUgdEntity = new ItSimrsMppEntity();
                    asesmenUgdEntity.setIdMpp("MPP" + mppDao.getNextSeq());
                    asesmenUgdEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    asesmenUgdEntity.setParameter(bean.getParameter());
                    asesmenUgdEntity.setJawaban(bean.getJawaban());
                    asesmenUgdEntity.setKeterangan(bean.getKeterangan());
                    asesmenUgdEntity.setJenis(bean.getJenis());
                    asesmenUgdEntity.setWaktu(bean.getWaktu());
                    asesmenUgdEntity.setAction(bean.getAction());
                    asesmenUgdEntity.setFlag(bean.getFlag());
                    asesmenUgdEntity.setCreatedDate(bean.getCreatedDate());
                    asesmenUgdEntity.setCreatedWho(bean.getCreatedWho());
                    asesmenUgdEntity.setLastUpdate(bean.getLastUpdate());
                    asesmenUgdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        mppDao.addAndSave(asesmenUgdEntity);
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

    @Override
    public CrudResponse saveDelete(Mpp bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        List<ItSimrsMppEntity> entityList = new ArrayList<>();

        try {
            entityList = mppDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
            logger.error(e.getMessage());
        }

        if(entityList.size() > 0){
            for (ItSimrsMppEntity entity : entityList){
                entity.setFlag("N");
                entity.setAction("D");
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    mppDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error, "+e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("Found Error, Data yang dicari tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setMppDao(MppDao mppDao) {
        this.mppDao = mppDao;
    }
}
