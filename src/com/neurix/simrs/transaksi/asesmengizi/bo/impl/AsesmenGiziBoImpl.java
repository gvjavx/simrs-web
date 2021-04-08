package com.neurix.simrs.transaksi.asesmengizi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.dao.AsesmenGiziDao;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenGizi;
import com.neurix.simrs.transaksi.asesmengizi.model.ItSimrsAsesmenGiziEntity;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsesmenGiziBoImpl implements AsesmenGiziBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private AsesmenGiziDao asesmenGiziDao;

    @Override
    public List<AsesmenGizi> getByCriteria(AsesmenGizi bean) throws GeneralBOException {
        logger.info("[AsesmenGiziBoImpl.getByCriteria] Start >>>>>>>");
        String method = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info(method);
        List<AsesmenGizi> asesmenGiziList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsAsesmenGiziEntity> entityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdAsesmenGizi() != null && !"".equalsIgnoreCase(bean.getIdAsesmenGizi())){
                hsCriteria.put("id_asesmen_gizi", bean.getIdAsesmenGizi());
            }
            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
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

            try {
                entityList = asesmenGiziDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[AsesmenGiziBoImpl.getByCriteria] Error, "+e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsAsesmenGiziEntity entity: entityList){
                    AsesmenGizi asesmenGizi = new AsesmenGizi();
                    asesmenGizi.setIdAsesmenGizi(entity.getIdAsesmenGizi());
                    asesmenGizi.setNoCheckup(entity.getNoCheckup());
                    asesmenGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenGizi.setParameter(entity.getParameter());
                    asesmenGizi.setKeterangan(entity.getKeterangan());
                    asesmenGizi.setJenis(entity.getJenis());
                    if(entity.getTipe() != null && !"".equalsIgnoreCase(entity.getTipe())){
                        if("ttd".equalsIgnoreCase(entity.getTipe())){
                            asesmenGizi.setJawaban(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getJawaban());
                        }else{
                            asesmenGizi.setJawaban(entity.getJawaban());
                        }
                    }else{
                        asesmenGizi.setJawaban(entity.getJawaban());
                    }
                    asesmenGizi.setNamaTerang(entity.getNamaTerang());
                    asesmenGizi.setSip(entity.getSip());
                    asesmenGizi.setFlag(entity.getFlag());
                    asesmenGizi.setAction(entity.getAction());
                    asesmenGizi.setCreatedDate(entity.getCreatedDate());
                    asesmenGizi.setCreatedWho(entity.getCreatedWho());
                    asesmenGizi.setLastUpdate(entity.getLastUpdate());
                    asesmenGizi.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenGizi.setSkor(entity.getSkor());
                    asesmenGizi.setTipe(entity.getTipe());
                    asesmenGiziList.add(asesmenGizi);
                }
            }
        }
        logger.info("[AsesmenGiziBoImpl.getByCriteria] end >>>>>>>");
        return asesmenGiziList;
    }

    @Override
    public void saveAdd(List<AsesmenGizi> bean) throws GeneralBOException {
        logger.info("[AsesmenGiziBoImpl.saveAdd] start >>>>>>>");
        if(bean.size() > 0){
            for (AsesmenGizi list: bean){
                ItSimrsAsesmenGiziEntity asesmenGizi = new ItSimrsAsesmenGiziEntity();
                asesmenGizi.setIdAsesmenGizi(asesmenGiziDao.getNextSeq());
                asesmenGizi.setNoCheckup(list.getNoCheckup());
                asesmenGizi.setIdDetailCheckup(list.getIdDetailCheckup());
                asesmenGizi.setParameter(list.getParameter());
                asesmenGizi.setJawaban(list.getJawaban());
                asesmenGizi.setKeterangan(list.getKeterangan());
                asesmenGizi.setJenis(list.getJenis());
                asesmenGizi.setNamaTerang(list.getNamaTerang());
                asesmenGizi.setSip(list.getSip());
                asesmenGizi.setFlag(list.getFlag());
                asesmenGizi.setAction(list.getAction());
                asesmenGizi.setCreatedDate(list.getCreatedDate());
                asesmenGizi.setCreatedWho(list.getCreatedWho());
                asesmenGizi.setLastUpdate(list.getLastUpdate());
                asesmenGizi.setLastUpdateWho(list.getLastUpdateWho());
                asesmenGizi.setTipe(list.getTipe());
                asesmenGizi.setSkor(list.getSkor());
                try {
                    asesmenGiziDao.addAndSave(asesmenGizi);
                }catch (HibernateException e){
                    logger.error("[AsesmenGiziBoImpl.saveAdd] Error, "+e.getMessage());
                    throw new GeneralBOException("Error when save asesmen gizi, "+e.getMessage());
                }
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenGiziBoImpl.saveAdd] end >>>>>>>");
    }

    @Override
    public void saveDelete(AsesmenGizi bean) throws GeneralBOException {
        logger.info("[AsesmenGiziBoImpl.saveDelete] start >>>>>>>");
        if(bean != null){
            try {
                HashMap hsCriteria = new HashMap();
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
                hsCriteria.put("keterangan", bean.getKeterangan());
                List<ItSimrsAsesmenGiziEntity> entityList = new ArrayList<>();
                entityList = asesmenGiziDao.getByCriteria(hsCriteria);
                if(entityList.size() > 0){
                    for (ItSimrsAsesmenGiziEntity entity: entityList){
                        entity.setFlag("N");
                        entity.setAction("D");
                        entity.setLastUpdate(bean.getLastUpdate());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        asesmenGiziDao.updateAndSave(entity);
                    }
                }else{
                    throw new GeneralBOException("Data tidak ada ditemukan @_@");
                }
            }catch (HibernateException e){
                logger.error("[AsesmenGiziBoImpl.saveDelete] Error, "+e.getMessage());
                throw new GeneralBOException("Error when delete asesmen gizi @_@");
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[AsesmenGiziBoImpl.saveDelete] end >>>>>>>");
    }

    public void setAsesmenGiziDao(AsesmenGiziDao asesmenGiziDao) {
        this.asesmenGiziDao = asesmenGiziDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
