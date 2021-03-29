package com.neurix.simrs.transaksi.makananpendamping.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.makananpendamping.bo.DetailPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.dao.DetailPendampingMakananDao;
import com.neurix.simrs.transaksi.makananpendamping.dao.HeaderPendampingMakananDao;
import com.neurix.simrs.transaksi.makananpendamping.model.DetailPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsDetailPendampingMakananEntity;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsHeaderPendampingMakananEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailPendampingMakananBoImpl implements DetailPendampingMakananBo {
    private static transient Logger logger = Logger.getLogger(DetailPendampingMakananBoImpl.class);
    private DetailPendampingMakananDao detailPendampingMakananDao;
    private HeaderPendampingMakananDao headerPendampingMakananDao;

    @Override
    public List<DetailPendampingMakanan> getByCriteria(DetailPendampingMakanan bean) throws GeneralBOException {
        logger.info("[DetailPendampingMakananBoImpl.getByCriteria] Start >>>>>>>");
        List<DetailPendampingMakanan> results = new ArrayList<>();
        List<ItSimrsDetailPendampingMakananEntity> list = new ArrayList<>();
        if (bean != null){
            List<ItSimrsDetailPendampingMakananEntity> orderGiziEntityList = getListEntity(bean);
            if(orderGiziEntityList.size() > 0){
                for (ItSimrsDetailPendampingMakananEntity entity: list){
                    DetailPendampingMakanan DetailPendampingMakanan = new DetailPendampingMakanan();
                    DetailPendampingMakanan.setIdDetailPendampingMakanan(entity.getIdDetailPendampingMakanan());
                    DetailPendampingMakanan.setIdHeaderPendampingMakanan(entity.getIdHeaderPendampingMakanan());
                    DetailPendampingMakanan.setNama(entity.getNama());
                    DetailPendampingMakanan.setQty(entity.getQty());
                    DetailPendampingMakanan.setTarif(entity.getTarif());
                    DetailPendampingMakanan.setTotalTarif(entity.getTotalTarif());
                    DetailPendampingMakanan.setKeterangan(entity.getKeterangan());
                    DetailPendampingMakanan.setAction(entity.getAction());
                    DetailPendampingMakanan.setFlag(entity.getFlag());
                    DetailPendampingMakanan.setCreatedWho(entity.getCreatedWho());
                    DetailPendampingMakanan.setCreatedDate(entity.getCreatedDate());
                    DetailPendampingMakanan.setLastUpdate(entity.getLastUpdate());
                    DetailPendampingMakanan.setLastUpdateWho(entity.getLastUpdateWho());
                    results.add(DetailPendampingMakanan);
                }
            }
        }
        logger.info("[DetailPendampingMakananBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveEdit(DetailPendampingMakanan bean) throws GeneralBOException {
        logger.info("[DetailPendampingMakananBoImpl.saveEdit] Start <<<<<<");
        if(bean != null){
            ItSimrsDetailPendampingMakananEntity entity = detailPendampingMakananDao.getById("idDetailPendampingMakanan", bean.getIdDetailPendampingMakanan());
            if(entity != null){
                try {
                    entity.setNama(bean.getNama());
                    entity.setKeterangan(bean.getKeterangan());
                    entity.setQty(bean.getQty());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    detailPendampingMakananDao.updateAndSave(entity);
                }catch (HibernateException e){
                    logger.error("[DetailPendampingMakananBoImpl.saveEdit] Error when save edit pedamping data ", e);
                    throw new GeneralBOException("Error, "+e.getMessage());
                }
            }
        }
        logger.info("[DetailPendampingMakananBoImpl.saveEdit] End <<<<<<");
    }

    @Override
    public void updateDetail(List<DetailPendampingMakanan> bean) throws GeneralBOException {
        logger.info("[DetailPendampingMakananBoImpl.updateDetail] Start <<<<<<");
        if(bean.size() > 0){
            DetailPendampingMakanan detailPendampingMakanan = bean.get(0);
            ItSimrsHeaderPendampingMakananEntity pendampingMakananEntity = headerPendampingMakananDao.getById("idHeaderPendampingMakanan", detailPendampingMakanan.getIdHeaderPendampingMakanan());
            if(pendampingMakananEntity != null){
                pendampingMakananEntity.setStatus("3");
                pendampingMakananEntity.setAction(detailPendampingMakanan.getAction());
                pendampingMakananEntity.setLastUpdate(detailPendampingMakanan.getLastUpdate());
                pendampingMakananEntity.setLastUpdateWho(detailPendampingMakanan.getLastUpdateWho());
                try {
                    headerPendampingMakananDao.updateAndSave(pendampingMakananEntity);
                }catch (HibernateException e){
                    logger.error("[DetailPendampingMakananBoImpl.updateDetail] Error when save edit pedamping data ", e);
                    throw new GeneralBOException("Error, "+e.getMessage());
                }
            }
            for (DetailPendampingMakanan detail: bean){
                ItSimrsDetailPendampingMakananEntity entity = detailPendampingMakananDao.getById("idDetailPendampingMakanan", detail.getIdDetailPendampingMakanan());
                if(entity != null){
                    try {
                        entity.setTarif(detail.getTarif());
                        entity.setTotalTarif(entity.getTarif().multiply(new BigDecimal(entity.getQty())));
                        entity.setAction(detail.getAction());
                        entity.setLastUpdate(detail.getLastUpdate());
                        entity.setLastUpdateWho(detail.getLastUpdateWho());
                        detailPendampingMakananDao.updateAndSave(entity);
                    }catch (HibernateException e){
                        logger.error("[DetailPendampingMakananBoImpl.updateDetail] Error when save edit pedamping data ", e);
                        throw new GeneralBOException("Error, "+e.getMessage());
                    }
                }
            }
        }
        logger.info("[DetailPendampingMakananBoImpl.updateDetail] End <<<<<<");
    }

    protected List<ItSimrsDetailPendampingMakananEntity> getListEntity(DetailPendampingMakanan bean) throws GeneralBOException{
        logger.info("[DetailPendampingMakananBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsDetailPendampingMakananEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdDetailPendampingMakanan() != null && !"".equalsIgnoreCase(bean.getIdDetailPendampingMakanan())){
            hsCriteria.put("id_detail_pendamping_makanan", bean.getIdDetailPendampingMakanan());
        }
        if (bean.getIdHeaderPendampingMakanan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPendampingMakanan())){
            hsCriteria.put("id_header_pendamping_makanan", bean.getIdHeaderPendampingMakanan());
        }

        try {
            results = detailPendampingMakananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[DetailPendampingMakananBoImpl.getListEntity] Error when searching pedamping data ", e);
            throw new GeneralBOException("Error, "+e.getMessage());
        }
        logger.info("[DetailPendampingMakananBoImpl.getListEntity] End <<<<<<");
        return results;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setDetailPendampingMakananDao(DetailPendampingMakananDao detailPendampingMakananDao) {
        this.detailPendampingMakananDao = detailPendampingMakananDao;
    }

    public void setHeaderPendampingMakananDao(HeaderPendampingMakananDao headerPendampingMakananDao) {
        this.headerPendampingMakananDao = headerPendampingMakananDao;
    }
}

