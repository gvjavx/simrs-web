package com.neurix.simrs.transaksi.cetakanrm.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.cetakanrm.bo.CetakanRmBo;
import com.neurix.simrs.transaksi.cetakanrm.dao.CetakanRmDao;
import com.neurix.simrs.transaksi.cetakanrm.model.CetakanRm;
import com.neurix.simrs.transaksi.cetakanrm.model.ItSimrsCetakanRmEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CetakanRmBoImpl implements CetakanRmBo {

    private static transient Logger logger = Logger.getLogger(CetakanRmBoImpl.class);
    private CetakanRmDao cetakanRmDao;

    @Override
    public List<CetakanRm> getByCriteria(CetakanRm bean) throws GeneralBOException {
        List<CetakanRm> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdCetakanRm() != null && !"".equalsIgnoreCase(bean.getIdCetakanRm())){
                hsCriteria.put("id_cetakan_rm", bean.getIdCetakanRm());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsCetakanRmEntity> entityList = new ArrayList<>();
            try {
                entityList = cetakanRmDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsCetakanRmEntity entity: entityList){
                    CetakanRm catatan = new CetakanRm();
                    catatan.setIdCetakanRm(entity.getIdCetakanRm());
                    catatan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    catatan.setDocRm(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_DOC_RM+entity.getDocRm());
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
    public CrudResponse saveAdd(CetakanRm bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsCetakanRmEntity cetakanRmEntity = new ItSimrsCetakanRmEntity();
            cetakanRmEntity.setIdCetakanRm("CRM"+cetakanRmDao.getNextSeq());
            cetakanRmEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            cetakanRmEntity.setDocRm(bean.getDocRm());
            cetakanRmEntity.setKeterangan(bean.getKeterangan());
            cetakanRmEntity.setAction(bean.getAction());
            cetakanRmEntity.setFlag(bean.getFlag());
            cetakanRmEntity.setCreatedDate(bean.getCreatedDate());
            cetakanRmEntity.setCreatedWho(bean.getCreatedWho());
            cetakanRmEntity.setLastUpdate(bean.getLastUpdate());
            cetakanRmEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                cetakanRmDao.addAndSave(cetakanRmEntity);
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

    public void setCetakanRmDao(CetakanRmDao cetakanRmDao) {
        this.cetakanRmDao = cetakanRmDao;
    }
}
