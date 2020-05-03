package com.neurix.simrs.transaksi.fisioterapi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.bo.MonitoringFisioterapiBo;
import com.neurix.simrs.transaksi.fisioterapi.dao.MonitoringFisioterapiDao;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsFisioterapiEntity;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsMonitoringFisioterapiEntity;
import com.neurix.simrs.transaksi.fisioterapi.model.MonitoringFisioterapi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitoringFisioterapiBoImpl implements MonitoringFisioterapiBo {

    private static transient Logger logger = Logger.getLogger(MonitoringFisioterapiBoImpl.class);
    private MonitoringFisioterapiDao monitoringFisioterapiDao;

    @Override
    public List<MonitoringFisioterapi> getByCriteria(MonitoringFisioterapi bean) throws GeneralBOException {
        List<MonitoringFisioterapi> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdMonitoringFisioterapi() != null && !"".equalsIgnoreCase(bean.getIdMonitoringFisioterapi())){
                hsCriteria.put("id_monitoring_fisioterapi", bean.getIdMonitoringFisioterapi());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            List<ItSimrsMonitoringFisioterapiEntity> entityList = new ArrayList<>();
            try {
                entityList = monitoringFisioterapiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsMonitoringFisioterapiEntity entity: entityList){
                    MonitoringFisioterapi fisioterapi = new MonitoringFisioterapi();
                    fisioterapi.setIdMonitoringFisioterapi(entity.getIdMonitoringFisioterapi());
                    fisioterapi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    fisioterapi.setTanggal(entity.getTanggal());
                    fisioterapi.setTindakan(entity.getTindakan());
                    fisioterapi.setKeterangan(entity.getKeterangan());
                    fisioterapi.setFisioterapi(entity.getFisioterapi());
                    fisioterapi.setAction(entity.getAction());
                    fisioterapi.setFlag(entity.getFlag());
                    fisioterapi.setCreatedDate(entity.getCreatedDate());
                    fisioterapi.setCreatedWho(entity.getCreatedWho());
                    fisioterapi.setLastUpdate(entity.getLastUpdate());
                    fisioterapi.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(fisioterapi);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(MonitoringFisioterapi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsMonitoringFisioterapiEntity fisioterapi = new ItSimrsMonitoringFisioterapiEntity();
            fisioterapi.setIdMonitoringFisioterapi("MFT"+monitoringFisioterapiDao.getNextSeq());
            fisioterapi.setIdDetailCheckup(bean.getIdDetailCheckup());
            fisioterapi.setTanggal(bean.getTanggal());
            fisioterapi.setTindakan(bean.getTindakan());
            fisioterapi.setKeterangan(bean.getKeterangan());
            fisioterapi.setFisioterapi(bean.getFisioterapi());
            fisioterapi.setAction(bean.getAction());
            fisioterapi.setFlag(bean.getFlag());
            fisioterapi.setCreatedDate(bean.getCreatedDate());
            fisioterapi.setCreatedWho(bean.getCreatedWho());
            fisioterapi.setLastUpdate(bean.getLastUpdate());
            fisioterapi.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                monitoringFisioterapiDao.addAndSave(fisioterapi);
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

    public void setMonitoringFisioterapiDao(MonitoringFisioterapiDao monitoringFisioterapiDao) {
        this.monitoringFisioterapiDao = monitoringFisioterapiDao;
    }
}
