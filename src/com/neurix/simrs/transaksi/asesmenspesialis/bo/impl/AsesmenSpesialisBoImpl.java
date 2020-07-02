package com.neurix.simrs.transaksi.asesmenspesialis.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenspesialis.bo.AsesmenSpesialisBo;
import com.neurix.simrs.transaksi.asesmenspesialis.dao.AsesmenSpesialisDao;
import com.neurix.simrs.transaksi.asesmenspesialis.model.AsesmenSpesialis;
import com.neurix.simrs.transaksi.asesmenspesialis.model.ItSimrsAsesmenSpesialisEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenSpesialisBoImpl implements AsesmenSpesialisBo {
    private static transient Logger logger = Logger.getLogger(AsesmenSpesialisBoImpl.class);
    private AsesmenSpesialisDao asesmenSpesialisDao;
    @Override
    public List<AsesmenSpesialis> getByCriteria(AsesmenSpesialis bean) throws GeneralBOException {
        List<AsesmenSpesialis> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdAsesmenPoliSpesialis() != null && !"".equalsIgnoreCase(bean.getIdAsesmenPoliSpesialis())){
                hsCriteria.put("id_asesmen_poli_spesialis", bean.getIdAsesmenPoliSpesialis());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if(bean.getTipe() != null && !"".equalsIgnoreCase(bean.getTipe())){
                hsCriteria.put("tipe", bean.getJenis());
            }

            List<ItSimrsAsesmenSpesialisEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenSpesialisDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAsesmenSpesialisEntity entity: entityList){
                    AsesmenSpesialis asesmenSpesialis = new AsesmenSpesialis();
                    asesmenSpesialis.setIdAsesmenPoliSpesialis(entity.getIdAsesmenPoliSpesialis());
                    asesmenSpesialis.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenSpesialis.setParameter(entity.getParameter());
                    asesmenSpesialis.setKeterangan(entity.getKeterangan());
                    asesmenSpesialis.setJenis(entity.getJenis());
                    asesmenSpesialis.setTipe(entity.getTipe());
                    if(!"".equalsIgnoreCase(entity.getTipe()) && entity.getTipe() != null){
                        if("ttd".equalsIgnoreCase(entity.getTipe())){
                            asesmenSpesialis.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban());
                        }else{
                            asesmenSpesialis.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_IMG_RM+entity.getJawaban());
                        }
                    }else{
                        asesmenSpesialis.setJawaban(entity.getJawaban());
                    }
                    asesmenSpesialis.setAction(entity.getAction());
                    asesmenSpesialis.setFlag(entity.getFlag());
                    asesmenSpesialis.setCreatedDate(entity.getCreatedDate());
                    asesmenSpesialis.setCreatedWho(entity.getCreatedWho());
                    asesmenSpesialis.setLastUpdate(entity.getLastUpdate());
                    asesmenSpesialis.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(asesmenSpesialis);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenSpesialis> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            for (AsesmenSpesialis bean: list){
                ItSimrsAsesmenSpesialisEntity asesmenUgdEntity = new ItSimrsAsesmenSpesialisEntity();
                asesmenUgdEntity.setIdAsesmenPoliSpesialis("ASP"+asesmenSpesialisDao.getNextSeq());
                asesmenUgdEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                asesmenUgdEntity.setParameter(bean.getParameter());
                asesmenUgdEntity.setJawaban(bean.getJawaban());
                asesmenUgdEntity.setKeterangan(bean.getKeterangan());
                asesmenUgdEntity.setJenis(bean.getJenis());
                asesmenUgdEntity.setTipe(bean.getTipe());
                asesmenUgdEntity.setAction(bean.getAction());
                asesmenUgdEntity.setFlag(bean.getFlag());
                asesmenUgdEntity.setCreatedDate(bean.getCreatedDate());
                asesmenUgdEntity.setCreatedWho(bean.getCreatedWho());
                asesmenUgdEntity.setLastUpdate(bean.getLastUpdate());
                asesmenUgdEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    asesmenSpesialisDao.addAndSave(asesmenUgdEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error "+e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsesmenSpesialisDao(AsesmenSpesialisDao asesmenSpesialisDao) {
        this.asesmenSpesialisDao = asesmenSpesialisDao;
    }
}
