package com.neurix.simrs.transaksi.makananpendamping.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.makananpendamping.dao.DetailPendampingMakananDao;
import com.neurix.simrs.transaksi.makananpendamping.dao.HeaderPendampingMakananDao;
import com.neurix.simrs.transaksi.makananpendamping.bo.HeaderPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.model.DetailPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.HeaderPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsDetailPendampingMakananEntity;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsHeaderPendampingMakananEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.*;

public class HeaderPendampingMakananBoImpl implements HeaderPendampingMakananBo {
    private static transient Logger logger = Logger.getLogger(HeaderPendampingMakananBoImpl.class);
    private HeaderPendampingMakananDao headerPendampingMakananDao;
    private DetailPendampingMakananDao detailPendampingMakananDao;

    @Override
    public List<HeaderPendampingMakanan> getByCriteria(HeaderPendampingMakanan bean) throws GeneralBOException {
        logger.info("[HeaderPendampingMakananBoImpl.getByCriteria] Start >>>>>>>");
        List<HeaderPendampingMakanan> results = new ArrayList<>();
        if (bean != null){
            List<ItSimrsHeaderPendampingMakananEntity> headerPendampingMakananEntityList = getListEntity(bean);
            if(headerPendampingMakananEntityList.size() > 0){
                for (ItSimrsHeaderPendampingMakananEntity entity: headerPendampingMakananEntityList){
                    HeaderPendampingMakanan headerPendampingMakanan = new HeaderPendampingMakanan();
                    headerPendampingMakanan.setIdHeaderPendampingMakanan(entity.getIdHeaderPendampingMakanan());
                    headerPendampingMakanan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    headerPendampingMakanan.setIdRuangan(entity.getIdRuangan());
                    headerPendampingMakanan.setAction(entity.getAction());
                    headerPendampingMakanan.setFlag(entity.getFlag());
                    headerPendampingMakanan.setCreatedWho(entity.getCreatedWho());
                    headerPendampingMakanan.setCreatedDate(entity.getCreatedDate());
                    headerPendampingMakanan.setLastUpdate(entity.getLastUpdate());
                    headerPendampingMakanan.setLastUpdateWho(entity.getLastUpdateWho());
                    headerPendampingMakanan.setStatus(entity.getStatus());
                    results.add(headerPendampingMakanan);
                }
            }
        }
        logger.info("[HeaderPendampingMakananBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(HeaderPendampingMakanan bean) throws GeneralBOException {
        logger.info("[HeaderPendampingMakananBoImpl.saveAdd] Start >>>>>>>");
        if (bean != null){
            ItSimrsHeaderPendampingMakananEntity headerPendampingMakananEntity = new ItSimrsHeaderPendampingMakananEntity();
            headerPendampingMakananEntity.setIdHeaderPendampingMakanan(headerPendampingMakananDao.getNextId());
            headerPendampingMakananEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            headerPendampingMakananEntity.setIdRuangan(bean.getIdRuangan());
            headerPendampingMakananEntity.setAction(bean.getAction());
            headerPendampingMakananEntity.setFlag(bean.getFlag());
            headerPendampingMakananEntity.setCreatedWho(bean.getCreatedWho());
            headerPendampingMakananEntity.setCreatedDate(bean.getCreatedDate());
            headerPendampingMakananEntity.setLastUpdate(bean.getLastUpdate());
            headerPendampingMakananEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerPendampingMakananEntity.setStatus("0");

            try {
                headerPendampingMakananDao.addAndSave(headerPendampingMakananEntity);
            }catch (HibernateException e){
                logger.error("[HeaderPendampingMakananBoImpl.saveAdd] Error, "+e.getMessage());
                throw new GeneralBOException("Error when save pendamping Gizi");
            }

            if(bean.getDetailPendampingMakananList().size() > 0){
                for (ItSimrsDetailPendampingMakananEntity entity: bean.getDetailPendampingMakananList()){
                    try {
                        entity.setIdDetailPendampingMakanan(detailPendampingMakananDao.getNextId());
                        entity.setIdHeaderPendampingMakanan(headerPendampingMakananEntity.getIdHeaderPendampingMakanan());
                        detailPendampingMakananDao.addAndSave(entity);
                    }catch (HibernateException e){
                        logger.error("[HeaderPendampingMakananBoImpl.saveAdd] Error, "+e.getMessage());
                        throw new GeneralBOException("Error when detail makanan pendamping");
                    }
                }
            }
        }
        logger.info("[HeaderPendampingMakananBoImpl.saveAdd] End <<<<<<");
    }

    @Override
    public List<HeaderPendampingMakanan> getListSearch(HeaderPendampingMakanan bean) throws GeneralBOException {
        logger.info("[HeaderPendampingMakananBoImpl.getListSearch] Start >>>>>>>");
        List<HeaderPendampingMakanan> headerPendampingMakananList = new ArrayList<>();
        try {
            headerPendampingMakananList = headerPendampingMakananDao.getListSearch(bean);
        }catch (HibernateException e){
            logger.error("[HeaderPendampingMakananBoImpl.getListSearch] Error when searching pedamping data ", e);
            throw new GeneralBOException("Error, "+e.getMessage());
        }
        logger.info("[HeaderPendampingMakananBoImpl.getListSearch] End >>>>>>>");
        return headerPendampingMakananList;
    }

    private List<ItSimrsHeaderPendampingMakananEntity> getListEntity(HeaderPendampingMakanan bean) throws GeneralBOException{
        logger.info("[HeaderPendampingMakananBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsHeaderPendampingMakananEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdHeaderPendampingMakanan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPendampingMakanan())){
            hsCriteria.put("id_header_pendamping_makanan", bean.getIdHeaderPendampingMakanan());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }

        try {
            results = headerPendampingMakananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[HeaderPendampingMakananBoImpl.getListEntity] Error when searching pedamping data ", e);
            throw new GeneralBOException("Error, "+e.getMessage());
        }
        logger.info("[HeaderPendampingMakananBoImpl.getListEntity] End <<<<<<");
        return results;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPendampingMakananDao(HeaderPendampingMakananDao headerPendampingMakananDao) {
        this.headerPendampingMakananDao = headerPendampingMakananDao;
    }

    public void setDetailPendampingMakananDao(DetailPendampingMakananDao detailPendampingMakananDao) {
        this.detailPendampingMakananDao = detailPendampingMakananDao;
    }
}

