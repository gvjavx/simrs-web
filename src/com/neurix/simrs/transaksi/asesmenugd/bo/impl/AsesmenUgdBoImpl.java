package com.neurix.simrs.transaksi.asesmenugd.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenugd.bo.AsesmenUgdBo;
import com.neurix.simrs.transaksi.asesmenugd.dao.AsesmenUgdDao;
import com.neurix.simrs.transaksi.asesmenugd.model.AsesmenUgd;
import com.neurix.simrs.transaksi.asesmenugd.model.ItSimrsAsesmenUgdEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenUgdBoImpl implements AsesmenUgdBo {
    private static transient Logger logger = Logger.getLogger(AsesmenUgdBoImpl.class);
    private AsesmenUgdDao asesmenUgdDao;
    @Override
    public List<AsesmenUgd> getByCriteria(AsesmenUgd bean) throws GeneralBOException {
        List<AsesmenUgd> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdAsesmenUgd() != null && !"".equalsIgnoreCase(bean.getIdAsesmenUgd())){
                hsCriteria.put("id_asesmen_ugd", bean.getIdAsesmenUgd());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if(bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())){
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsAsesmenUgdEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenUgdDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAsesmenUgdEntity entity: entityList){
                    AsesmenUgd asesmenUgd = new AsesmenUgd();
                    asesmenUgd.setIdAsesmenUgd(entity.getIdAsesmenUgd());
                    asesmenUgd.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenUgd.setParameter(entity.getParameter());
                    asesmenUgd.setJawaban(entity.getJawaban());
                    asesmenUgd.setKeterangan(entity.getKeterangan());
                    asesmenUgd.setJenis(entity.getJenis());
                    asesmenUgd.setSkor(entity.getSkor());
                    asesmenUgd.setAction(entity.getAction());
                    asesmenUgd.setFlag(entity.getFlag());
                    asesmenUgd.setCreatedDate(entity.getCreatedDate());
                    asesmenUgd.setCreatedWho(entity.getCreatedWho());
                    asesmenUgd.setLastUpdate(entity.getLastUpdate());
                    asesmenUgd.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(asesmenUgd);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(AsesmenUgd bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsAsesmenUgdEntity asesmenUgdEntity = new ItSimrsAsesmenUgdEntity();
            asesmenUgdEntity.setIdAsesmenUgd("AGD"+asesmenUgdDao.getNextSeq());
            asesmenUgdEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            asesmenUgdEntity.setParameter(bean.getParameter());
            asesmenUgdEntity.setJawaban(bean.getJawaban());
            asesmenUgdEntity.setKeterangan(bean.getKeterangan());
            asesmenUgdEntity.setJenis(bean.getJenis());
            asesmenUgdEntity.setSkor(bean.getSkor());
            asesmenUgdEntity.setAction(bean.getAction());
            asesmenUgdEntity.setFlag(bean.getFlag());
            asesmenUgdEntity.setCreatedDate(bean.getCreatedDate());
            asesmenUgdEntity.setCreatedWho(bean.getCreatedWho());
            asesmenUgdEntity.setLastUpdate(bean.getLastUpdate());
            asesmenUgdEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                asesmenUgdDao.addAndSave(asesmenUgdEntity);
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

    public void setAsesmenUgdDao(AsesmenUgdDao asesmenUgdDao) {
        this.asesmenUgdDao = asesmenUgdDao;
    }
}