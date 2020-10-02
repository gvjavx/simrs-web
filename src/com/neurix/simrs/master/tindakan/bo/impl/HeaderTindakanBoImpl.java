package com.neurix.simrs.master.tindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.bo.HeaderTindakanBo;
import com.neurix.simrs.master.tindakan.dao.HeaderTindakanDao;
import com.neurix.simrs.master.tindakan.model.HeaderTindakan;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderTindakanBoImpl implements HeaderTindakanBo {
    private static transient Logger logger = Logger.getLogger(HeaderTindakanBoImpl.class);
    private HeaderTindakanDao headerTindakanDao;

    @Override
    public List<HeaderTindakan> getByCriteria(HeaderTindakan bean) throws GeneralBOException {
        List<HeaderTindakan> headerTindakanList = new ArrayList<>();
        List<ImSimrsHeaderTindakanEntity> results = new ArrayList<>();
        Map hsCiteria = new HashMap();
        if (bean.getIdHeaderTindakan() != null && !"".equalsIgnoreCase(bean.getIdHeaderTindakan())) {
            hsCiteria.put("id_header_tindakan", bean.getIdHeaderTindakan());
        }
        if (bean.getNamaTindakan() != null && !"".equalsIgnoreCase(bean.getNamaTindakan())) {
            hsCiteria.put("nama_tindakan", bean.getNamaTindakan());
        }
        if (bean.getKategoriInaBpjs() != null && !"".equalsIgnoreCase(bean.getKategoriInaBpjs())) {
            hsCiteria.put("kategori_ina", bean.getKategoriInaBpjs());
        }
        if (bean.getStandardCost() != null && !"".equalsIgnoreCase(bean.getStandardCost().toString())) {
            hsCiteria.put("standart_cost", bean.getStandardCost());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            hsCiteria.put("flag", bean.getFlag());
        } else {
            hsCiteria.put("flag", "Y");
        }

        try {
            results = headerTindakanDao.getByCriteria(hsCiteria);
        } catch (HibernateException e) {
            logger.error("[TindakanBoImpl.getListEntityTindakan] Error when get data tindakan ", e);
        }

        if(results.size() > 0){
            for (ImSimrsHeaderTindakanEntity entity: results){
                HeaderTindakan headerTindakan = new HeaderTindakan();
                headerTindakan.setIdHeaderTindakan(entity.getIdHeaderTindakan());
                headerTindakan.setNamaTindakan(entity.getNamaTindakan());
                headerTindakan.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                headerTindakan.setStandardCost(entity.getStandardCost());
                headerTindakan.setAction(entity.getAction());
                headerTindakan.setFlag(entity.getFlag());
                headerTindakan.setCreatedDate(entity.getCreatedDate());
                headerTindakan.setCreatedWho(entity.getCreatedWho());
                headerTindakan.setLastUpdate(entity.getLastUpdate());
                headerTindakan.setLastUpdateWho(entity.getLastUpdateWho());
                headerTindakanList.add(headerTindakan);
            }
        }

        return headerTindakanList;
    }

    @Override
    public CrudResponse saveAdd(HeaderTindakan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            String id = null;
            try {
                id = headerTindakanDao.getNextSeq();
                response.setStatus("success");
                response.setMsg("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("SEQ Header Tindakan sudah ada...!"+ e.getMessage());
                logger.error(e.getMessage());
            }
            if(id != null){
                ImSimrsHeaderTindakanEntity imSimrsHeaderTindakanEntity = new ImSimrsHeaderTindakanEntity();
                imSimrsHeaderTindakanEntity.setIdHeaderTindakan(id);
                imSimrsHeaderTindakanEntity.setNamaTindakan(bean.getNamaTindakan());
                imSimrsHeaderTindakanEntity.setKategoriInaBpjs(bean.getKategoriInaBpjs());
                imSimrsHeaderTindakanEntity.setStandardCost(bean.getStandardCost());
                imSimrsHeaderTindakanEntity.setAction(bean.getAction());
                imSimrsHeaderTindakanEntity.setFlag(bean.getFlag());
                imSimrsHeaderTindakanEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsHeaderTindakanEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsHeaderTindakanEntity.setLastUpdate(bean.getLastUpdate());
                imSimrsHeaderTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    headerTindakanDao.addAndSave(imSimrsHeaderTindakanEntity);
                    response.setStatus("success");
                    response.setMsg("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Error saat insert ke database...!, dikarenakan "+ e.getMessage());
                    logger.error(e.getMessage());
                }
            }else{
                response.setStatus("error");
                response.setMsg("Mohon Maaf ID tindakan tidak bisa digenerate");
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveEdit(HeaderTindakan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ImSimrsHeaderTindakanEntity entity = new ImSimrsHeaderTindakanEntity();
            try {
                entity = headerTindakanDao.getById("idHeaderTindakan", bean.getIdHeaderTindakan());
                response.setStatus("success");
                response.setMsg("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("SEQ Header Tindakan sudah ada...!"+ e.getMessage());
                logger.error(e.getMessage());
            }
            if(entity != null){
                entity.setNamaTindakan(bean.getNamaTindakan());
                entity.setStandardCost(bean.getStandardCost());
                entity.setAction(bean.getAction());
                entity.setFlag(bean.getFlag());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    headerTindakanDao.updateAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Error saat update ke database...!, dikarenakan "+ e.getMessage());
                    logger.error(e.getMessage());
                }
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf ID tindakan tidak ditemukan...!");
            }
        }
        return response;
    }

    public void setHeaderTindakanDao(HeaderTindakanDao headerTindakanDao) {
        this.headerTindakanDao = headerTindakanDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}