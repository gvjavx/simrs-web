package com.neurix.simrs.transaksi.appendecitomy.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.appendecitomy.bo.AppendecitomyBo;
import com.neurix.simrs.transaksi.appendecitomy.dao.AppendecitomyDao;
import com.neurix.simrs.transaksi.appendecitomy.model.Appendecitomy;
import com.neurix.simrs.transaksi.appendecitomy.model.ItSimrsAppendecitomyEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppendecitomyBoImpl implements AppendecitomyBo {
    private static transient Logger logger = Logger.getLogger(AppendecitomyBoImpl.class);
    private AppendecitomyDao appendecitomyDao;

    @Override
    public List<Appendecitomy> getByCriteria(Appendecitomy bean) throws GeneralBOException {
        List<Appendecitomy> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdAppendecitomy() != null && !"".equalsIgnoreCase(bean.getIdAppendecitomy())){
                hsCriteria.put("id_appendecitomy", bean.getIdAppendecitomy());
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

            List<ItSimrsAppendecitomyEntity> entityList = new ArrayList<>();

            try {
                entityList = appendecitomyDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAppendecitomyEntity entity: entityList){
                    Appendecitomy appendecitomy = new Appendecitomy();
                    appendecitomy.setIdAppendecitomy(entity.getIdAppendecitomy());
                    appendecitomy.setIdDetailCheckup(entity.getIdDetailCheckup());
                    appendecitomy.setParameter(entity.getParameter());
                    if("appendecitomy_penyataan".equalsIgnoreCase(entity.getKeterangan())){
                        appendecitomy.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban1());
                    }else{
                        appendecitomy.setJawaban1(entity.getJawaban1());
                    }
                    appendecitomy.setJawaban2(entity.getJawaban2());
                    appendecitomy.setKeterangan(entity.getKeterangan());
                    appendecitomy.setJenis(entity.getJenis());
                    appendecitomy.setAction(entity.getAction());
                    appendecitomy.setFlag(entity.getFlag());
                    appendecitomy.setCreatedDate(entity.getCreatedDate());
                    appendecitomy.setCreatedWho(entity.getCreatedWho());
                    appendecitomy.setLastUpdate(entity.getLastUpdate());
                    appendecitomy.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(appendecitomy);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(Appendecitomy bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsAppendecitomyEntity appendecitomyEntity = new ItSimrsAppendecitomyEntity();
            appendecitomyEntity.setIdAppendecitomy("HDL"+appendecitomyDao.getNextSeq());
            appendecitomyEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            appendecitomyEntity.setParameter(bean.getParameter());
            appendecitomyEntity.setJawaban1(bean.getJawaban1());
            appendecitomyEntity.setJawaban2(bean.getJawaban2());
            appendecitomyEntity.setKeterangan(bean.getKeterangan());
            appendecitomyEntity.setJenis(bean.getJenis());
            appendecitomyEntity.setAction(bean.getAction());
            appendecitomyEntity.setFlag(bean.getFlag());
            appendecitomyEntity.setCreatedDate(bean.getCreatedDate());
            appendecitomyEntity.setCreatedWho(bean.getCreatedWho());
            appendecitomyEntity.setLastUpdate(bean.getLastUpdate());
            appendecitomyEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                appendecitomyDao.addAndSave(appendecitomyEntity);
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

    public void setAppendecitomyDao(AppendecitomyDao appendecitomyDao) {
        this.appendecitomyDao = appendecitomyDao;
    }
}
