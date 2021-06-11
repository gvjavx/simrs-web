package com.neurix.simrs.transaksi.asesmengizi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenMonitoringGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.dao.AsesmenMonitoringGiziDao;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenMonitoringGizi;
import com.neurix.simrs.transaksi.asesmengizi.model.ItSimrsAsesmenMonitoringGiziEntity;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsesmenMonitoringGiziBoImpl implements AsesmenMonitoringGiziBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private AsesmenMonitoringGiziDao asesmenMonitoringGiziDao;

    @Override
    public List<AsesmenMonitoringGizi> getByCriteria(AsesmenMonitoringGizi bean) throws GeneralBOException {
        logger.info("[AsesmenMonitoringGiziBoImpl.getByCriteria] Start >>>>>>>");
        String method = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info(method);
        List<AsesmenMonitoringGizi> asesmenGiziList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsAsesmenMonitoringGiziEntity> entityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdMonitoringGizi() != null && !"".equalsIgnoreCase(bean.getIdMonitoringGizi())){
                hsCriteria.put("id_monitoring_gizi", bean.getIdMonitoringGizi());
            }
            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            try {
                entityList = asesmenMonitoringGiziDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[AsesmenMonitoringGiziBoImpl.getByCriteria] Error, "+e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAsesmenMonitoringGiziEntity entity: entityList){
                    AsesmenMonitoringGizi asesmenGizi = new AsesmenMonitoringGizi();
                    asesmenGizi.setIdMonitoringGizi(entity.getIdMonitoringGizi());
                    asesmenGizi.setNoCheckup(entity.getNoCheckup());
                    asesmenGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenGizi.setTanggal(entity.getTanggal());
                    asesmenGizi.setBeratBadan(entity.getBeratBadan());
                    asesmenGizi.setStatusGizi(entity.getStatusGizi());
                    asesmenGizi.setIntake(entity.getIntake());
                    asesmenGizi.setFisik(entity.getFisik());
                    asesmenGizi.setBiokimia(entity.getBiokimia());
                    asesmenGizi.setIntervensi(entity.getIntervensi());
                    asesmenGizi.setLainLain(entity.getLainLain());
                    if(entity.getTtdPasien() != null){
                        asesmenGizi.setTtdPasien(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdPasien());
                    }
                    if(entity.getTtdDokter() != null){
                        asesmenGizi.setTtdDokter(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdDokter());
                    }
                    asesmenGizi.setNamaPasien(entity.getNamaPasien());
                    asesmenGizi.setNamaDokter(entity.getNamaDokter());
                    asesmenGizi.setSipDokter(entity.getSipDokter());
                    asesmenGizi.setFlag(entity.getFlag());
                    asesmenGizi.setAction(entity.getAction());
                    asesmenGizi.setCreatedDate(entity.getCreatedDate());
                    asesmenGizi.setCreatedWho(entity.getCreatedWho());
                    asesmenGizi.setLastUpdate(entity.getLastUpdate());
                    asesmenGizi.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenGiziList.add(asesmenGizi);
                }
            }
        }
        logger.info("[AsesmenMonitoringGiziBoImpl.getByCriteria] end >>>>>>>");
        return asesmenGiziList;
    }

    @Override
    public void saveAdd(AsesmenMonitoringGizi bean) throws GeneralBOException {
        logger.info("[AsesmenMonitoringGiziBoImpl.saveAdd] start >>>>>>>");
        if(bean != null){
            ItSimrsAsesmenMonitoringGiziEntity asesmenGizi = new ItSimrsAsesmenMonitoringGiziEntity();
            asesmenGizi.setIdMonitoringGizi(asesmenMonitoringGiziDao.getNextSeq());
            asesmenGizi.setNoCheckup(bean.getNoCheckup());
            asesmenGizi.setIdDetailCheckup(bean.getIdDetailCheckup());
            asesmenGizi.setTanggal(bean.getTanggal());
            asesmenGizi.setBeratBadan(bean.getBeratBadan());
            asesmenGizi.setStatusGizi(bean.getStatusGizi());
            asesmenGizi.setIntake(bean.getIntake());
            asesmenGizi.setFisik(bean.getFisik());
            asesmenGizi.setBiokimia(bean.getBiokimia());
            asesmenGizi.setIntervensi(bean.getIntervensi());
            asesmenGizi.setLainLain(bean.getLainLain());
            asesmenGizi.setTtdPasien(bean.getTtdPasien());
            asesmenGizi.setTtdDokter(bean.getTtdDokter());
            asesmenGizi.setNamaPasien(bean.getNamaPasien());
            asesmenGizi.setNamaDokter(bean.getNamaDokter());
            asesmenGizi.setSipDokter(bean.getSipDokter());
            asesmenGizi.setFlag(bean.getFlag());
            asesmenGizi.setAction(bean.getAction());
            asesmenGizi.setCreatedDate(bean.getCreatedDate());
            asesmenGizi.setCreatedWho(bean.getCreatedWho());
            asesmenGizi.setLastUpdate(bean.getLastUpdate());
            asesmenGizi.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                asesmenMonitoringGiziDao.addAndSave(asesmenGizi);
            }catch (HibernateException e){
                logger.error("[AsesmenMonitoringGiziBoImpl.saveAdd] Error, "+e.getMessage());
                throw new GeneralBOException("Error when save asesmen gizi, "+e.getMessage());
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenMonitoringGiziBoImpl.saveAdd] end >>>>>>>");
    }

    @Override
    public void saveDelete(AsesmenMonitoringGizi bean) throws GeneralBOException {
        logger.info("[AsesmenMonitoringGiziBoImpl.saveDelete] start >>>>>>>");
        if(bean != null){
            try {
                ItSimrsAsesmenMonitoringGiziEntity entity = asesmenMonitoringGiziDao.getById("idMonitoringGizi", bean.getIdMonitoringGizi());
                if(entity != null){
                    entity.setFlag("N");
                    entity.setAction("D");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    asesmenMonitoringGiziDao.updateAndSave(entity);
                }else{
                    throw new GeneralBOException("Data tidak ada ditemukan @_@");
                }
            }catch (HibernateException e){
                logger.error("[AsesmenMonitoringGiziBoImpl.saveDelete] Error, "+e.getMessage());
                throw new GeneralBOException("Error when delete asesmen gizi @_@");
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenMonitoringGiziBoImpl.saveDelete] end >>>>>>>");
    }

    public void setAsesmenMonitoringGiziDao(AsesmenMonitoringGiziDao asesmenMonitoringGiziDao) {
        this.asesmenMonitoringGiziDao = asesmenMonitoringGiziDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
