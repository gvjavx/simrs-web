package com.neurix.simrs.transaksi.asesmengizi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenAsuhanGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.dao.AsesmenAsuhanGiziDao;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenAsuhanGizi;
import com.neurix.simrs.transaksi.asesmengizi.model.ItSimrsAsesmenAsuhanGiziEntity;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsesmenAsuhanGiziBoImpl implements AsesmenAsuhanGiziBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private AsesmenAsuhanGiziDao asesmenAsuhanGiziDao;

    @Override
    public List<AsesmenAsuhanGizi> getByCriteria(AsesmenAsuhanGizi bean) throws GeneralBOException {
        logger.info("[AsesmenAsuhanGiziBoImpl.getByCriteria] Start >>>>>>>");
        String method = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info(method);
        List<AsesmenAsuhanGizi> asesmenGiziList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsAsesmenAsuhanGiziEntity> entityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdAsuhanGizi() != null && !"".equalsIgnoreCase(bean.getIdAsuhanGizi())){
                hsCriteria.put("id_asuhan_gizi", bean.getIdAsuhanGizi());
            }
            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            try {
                entityList = asesmenAsuhanGiziDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[AsesmenAsuhanGiziBoImpl.getByCriteria] Error, "+e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAsesmenAsuhanGiziEntity entity: entityList){
                    AsesmenAsuhanGizi asesmenGizi = new AsesmenAsuhanGizi();
                    asesmenGizi.setIdAsuhanGizi(entity.getIdAsuhanGizi());
                    asesmenGizi.setNoCheckup(entity.getNoCheckup());
                    asesmenGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenGizi.setAsesmen(entity.getAsesmen());
                    asesmenGizi.setDiagnosa(entity.getDiagnosa());
                    asesmenGizi.setIntervensi(entity.getIntervensi());
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
        logger.info("[AsesmenAsuhanGiziBoImpl.getByCriteria] end >>>>>>>");
        return asesmenGiziList;
    }

    @Override
    public void saveAdd(AsesmenAsuhanGizi bean) throws GeneralBOException {
        logger.info("[AsesmenAsuhanGiziBoImpl.saveAdd] start >>>>>>>");
        if(bean != null){
            ItSimrsAsesmenAsuhanGiziEntity asesmenGizi = new ItSimrsAsesmenAsuhanGiziEntity();;
            asesmenGizi.setIdAsuhanGizi(asesmenAsuhanGiziDao.getNextSeq());
            asesmenGizi.setNoCheckup(bean.getNoCheckup());
            asesmenGizi.setIdDetailCheckup(bean.getIdDetailCheckup());
            asesmenGizi.setAsesmen(bean.getAsesmen());
            asesmenGizi.setDiagnosa(bean.getDiagnosa());
            asesmenGizi.setIntervensi(bean.getIntervensi());
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
                asesmenAsuhanGiziDao.addAndSave(asesmenGizi);
            }catch (HibernateException e){
                logger.error("[AsesmenAsuhanGiziBoImpl.saveAdd] Error, "+e.getMessage());
                throw new GeneralBOException("Error when save asesmen gizi, "+e.getMessage());
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenAsuhanGiziBoImpl.saveAdd] end >>>>>>>");
    }

    @Override
    public void saveDelete(AsesmenAsuhanGizi bean) throws GeneralBOException {
        logger.info("[AsesmenAsuhanGiziBoImpl.saveDelete] start >>>>>>>");
        if(bean != null){
            try {
                ItSimrsAsesmenAsuhanGiziEntity entity = asesmenAsuhanGiziDao.getById("idAsuhanGizi", bean.getIdAsuhanGizi());
                if(entity != null){
                    entity.setFlag("N");
                    entity.setAction("D");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    asesmenAsuhanGiziDao.updateAndSave(entity);
                }else{
                    throw new GeneralBOException("Data tidak ada ditemukan @_@");
                }
            }catch (HibernateException e){
                logger.error("[AsesmenAsuhanGiziBoImpl.saveDelete] Error, "+e.getMessage());
                throw new GeneralBOException("Error when delete asesmen gizi @_@");
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenAsuhanGiziBoImpl.saveDelete] end >>>>>>>");
    }

    public void setAsesmenAsuhanGiziDao(AsesmenAsuhanGiziDao asesmenAsuhanGiziDao) {
        this.asesmenAsuhanGiziDao = asesmenAsuhanGiziDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
