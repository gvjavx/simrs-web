package com.neurix.simrs.transaksi.konsultasigizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import com.neurix.simrs.transaksi.konsultasigizi.bo.KonsultasiGiziBo;
import com.neurix.simrs.transaksi.konsultasigizi.dao.KonsultasiGiziDao;
import com.neurix.simrs.transaksi.konsultasigizi.model.ItSimrsKonsultasiGiziEntity;
import com.neurix.simrs.transaksi.konsultasigizi.model.KonsultasiGizi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KonsultasiGiziBoImpl implements KonsultasiGiziBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private KonsultasiGiziDao konsultasiGiziDao;

    @Override
    public List<KonsultasiGizi> getByCriteria(KonsultasiGizi bean) throws GeneralBOException {
        logger.info("[KonsultasiGiziBoImpl.getByCriteria] Start >>>>>>>");
        String method = new Object(){}.getClass().getEnclosingMethod().getName();
        logger.info(method);
        List<KonsultasiGizi> asesmenGiziList = new ArrayList<>();
        if(bean != null){
            List<ItSimrsKonsultasiGiziEntity> entityList = new ArrayList<>();
            HashMap hsCriteria = new HashMap();
            if(bean.getIdKonsultasiGizi() != null && !"".equalsIgnoreCase(bean.getIdKonsultasiGizi())){
                hsCriteria.put("id_konsultasi_gizi", bean.getIdKonsultasiGizi());
            }
            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            try {
                entityList = konsultasiGiziDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[KonsultasiGiziBoImpl.getByCriteria] Error, "+e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsKonsultasiGiziEntity entity: entityList){
                    KonsultasiGizi asesmenGizi = new KonsultasiGizi();
                    asesmenGizi.setIdKonsultasiGizi(entity.getIdKonsultasiGizi());
                    asesmenGizi.setNoCheckup(entity.getNoCheckup());
                    asesmenGizi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenGizi.setStatus(entity.getStatus());
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
        logger.info("[KonsultasiGiziBoImpl.getByCriteria] end >>>>>>>");
        return asesmenGiziList;
    }

    @Override
    public void saveEdit(KonsultasiGizi bean) throws GeneralBOException {
        logger.info("[KonsultasiGiziBoImpl.saveEdit] start >>>>>>>");
        if(bean != null){
            try {
                ItSimrsKonsultasiGiziEntity entity = konsultasiGiziDao.getById("idKonsultasiGizi", bean.getIdKonsultasiGizi());
                if(entity != null){
                    entity.setFlag("Y");
                    entity.setStatus("2");
                    entity.setAction("U");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    konsultasiGiziDao.updateAndSave(entity);
                }else{
                    throw new GeneralBOException("Data tidak ada ditemukan @_@");
                }
            }catch (HibernateException e){
                logger.error("[KonsultasiGiziBoImpl.saveEdit] Error, "+e.getMessage());
                throw new GeneralBOException("Error when delete asesmen gizi @_@");
            }
        }else{
            throw new GeneralBOException("Data tidak ada");
        }
        logger.info("[KonsultasiGiziBoImpl.saveEdit] end >>>>>>>");
    }

    @Override
    public List<KonsultasiGizi> pushNotif(String branchId) throws GeneralBOException {
        return null;
    }

    public void setKonsultasiGiziDao(KonsultasiGiziDao konsultasiGiziDao) {
        this.konsultasiGiziDao = konsultasiGiziDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
