package com.neurix.simrs.transaksi.ordergizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.dao.MasterPendampingGiziDao;
import com.neurix.simrs.transaksi.ordergizi.bo.PendampingGiziBo;
import com.neurix.simrs.transaksi.ordergizi.dao.PendampingGiziDao;
import com.neurix.simrs.transaksi.ordergizi.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.*;

public class PendampingGiziBoImpl implements PendampingGiziBo {
    private static transient Logger logger = Logger.getLogger(PendampingGiziBoImpl.class);
    private PendampingGiziDao pendampingGiziDao;
    private MasterPendampingGiziDao masterPendampingGiziDao;

    @Override
    public List<PendampingGizi> getByCriteria(PendampingGizi bean) throws GeneralBOException {
        logger.info("[PendampingGiziBoImpl.getByCriteria] Start >>>>>>>");
        List<PendampingGizi> results = new ArrayList<>();
        List<ItSimrsPendampingGiziEntity> list = new ArrayList<>();
        if (bean != null){
            List<ItSimrsPendampingGiziEntity> orderGiziEntityList = getListEntity(bean);
            if(orderGiziEntityList.size() > 0){
                for (ItSimrsPendampingGiziEntity entity: list){
                    PendampingGizi pendampingGizi = new PendampingGizi();
                    pendampingGizi.setIdPendampingGizi(entity.getIdPendampingGizi());
                    pendampingGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    pendampingGizi.setNama(entity.getNama());
                    pendampingGizi.setTarif(entity.getTarif());
                    pendampingGizi.setTipe(entity.getTipe());
                    pendampingGizi.setAction(entity.getAction());
                    pendampingGizi.setFlag(entity.getFlag());
                    pendampingGizi.setCreatedWho(entity.getCreatedWho());
                    pendampingGizi.setCreatedDate(entity.getCreatedDate());
                    pendampingGizi.setLastUpdate(entity.getLastUpdate());
                    pendampingGizi.setLastUpdateWho(entity.getLastUpdateWho());
                    results.add(pendampingGizi);
                }
            }
        }
        logger.info("[PendampingGiziBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(List<PendampingGizi> list) throws GeneralBOException {
        logger.info("[PendampingGiziBoImpl.saveAdd] Start >>>>>>>");
        if (list.size() > 0){
            for (PendampingGizi entity: list){
                ItSimrsPendampingGiziEntity pendampingGizi = new ItSimrsPendampingGiziEntity();
                pendampingGizi.setIdPendampingGizi(entity.getIdPendampingGizi());
                pendampingGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                pendampingGizi.setNama(entity.getNama());
                pendampingGizi.setTarif(entity.getTarif());
                pendampingGizi.setTipe(entity.getTipe());
                pendampingGizi.setAction(entity.getAction());
                pendampingGizi.setFlag(entity.getFlag());
                pendampingGizi.setCreatedWho(entity.getCreatedWho());
                pendampingGizi.setCreatedDate(entity.getCreatedDate());
                pendampingGizi.setLastUpdate(entity.getLastUpdate());
                pendampingGizi.setLastUpdateWho(entity.getLastUpdateWho());
                try {
                    pendampingGiziDao.addAndSave(pendampingGizi);
                }catch (HibernateException e){
                    logger.error("[PendampingGiziBoImpl.saveAdd] Error, "+e.getMessage());
                    throw new GeneralBOException("Error when save pendamping Gizi");
                }
            }
        }
        logger.info("[PendampingGiziBoImpl.saveAdd] End <<<<<<");
    }

    @Override
    public void saveEdit(PendampingGizi bean) throws GeneralBOException {
        logger.info("[PendampingGiziBoImpl.saveEdit] Start >>>>>>>");
        if(bean != null){
            ItSimrsPendampingGiziEntity pendampingGizi = pendampingGiziDao.getById("idPendampingGizi", bean.getIdPendampingGizi());
            if(pendampingGizi != null){
                if(bean.getNama() != null){
                    pendampingGizi.setNama(bean.getNama());
                }
                if(bean.getTarif() != null){
                    pendampingGizi.setTarif(bean.getTarif());
                }
                pendampingGizi.setAction("U");
                pendampingGizi.setLastUpdate(bean.getLastUpdate());
                pendampingGizi.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    pendampingGiziDao.addAndSave(pendampingGizi);
                }catch (HibernateException e){
                    logger.error("[PendampingGiziBoImpl.saveEdit] Error, "+e.getMessage());
                    throw new GeneralBOException("Error when edit pendamping Gizi");
                }
            }
        }
        logger.info("[PendampingGiziBoImpl.saveEdit] End <<<<<<");
    }

    protected List<ItSimrsPendampingGiziEntity> getListEntity(PendampingGizi bean) throws GeneralBOException{
        logger.info("[PendampingGiziBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsPendampingGiziEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdPendampingGizi() != null && !"".equalsIgnoreCase(bean.getIdPendampingGizi())){
            hsCriteria.put("id_pendamping_gizi", bean.getIdPendampingGizi());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getTipe() != null && !"".equalsIgnoreCase(bean.getTipe())){
            hsCriteria.put("tipe", bean.getTipe());
        }

        try {
            results = pendampingGiziDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PendampingGiziBoImpl.getListEntity] Error when searching pedamping data ", e);
            throw new GeneralBOException("Error, "+e.getMessage());
        }
        logger.info("[PendampingGiziBoImpl.getListEntity] End <<<<<<");
        return results;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setPendampingGiziDao(PendampingGiziDao pendampingGiziDao) {
        this.pendampingGiziDao = pendampingGiziDao;
    }

    public void setMasterPendampingGiziDao(MasterPendampingGiziDao masterPendampingGiziDao) {
        this.masterPendampingGiziDao = masterPendampingGiziDao;
    }
}

