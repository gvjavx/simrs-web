package com.neurix.simrs.transaksi.hemodialisa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.MonitoringTransfusiDarahBo;
import com.neurix.simrs.transaksi.hemodialisa.dao.MonitoringTransfusiDarahDao;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsMonitoringTransfusiDarahEntity;
import com.neurix.simrs.transaksi.hemodialisa.model.MonitoringTransfusiDarah;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitoringTransfusiDarahBoImpl implements MonitoringTransfusiDarahBo {
    private static transient Logger logger = Logger.getLogger(MonitoringTransfusiDarahBoImpl.class);
    private MonitoringTransfusiDarahDao monitoringTransfusiDarahDao;

    @Override
    public List<MonitoringTransfusiDarah> getByCriteria(MonitoringTransfusiDarah bean) throws GeneralBOException {
        List<MonitoringTransfusiDarah> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdMonitoringTransfusiDarah() != null && !"".equalsIgnoreCase(bean.getIdMonitoringTransfusiDarah())){
                hsCriteria.put("id_monitoring_transfusi_darah", bean.getIdMonitoringTransfusiDarah());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsMonitoringTransfusiDarahEntity> entityList = new ArrayList<>();

            try {
                entityList = monitoringTransfusiDarahDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsMonitoringTransfusiDarahEntity entity: entityList){
                    MonitoringTransfusiDarah transfusiDarah = new MonitoringTransfusiDarah();
                    transfusiDarah.setIdMonitoringTransfusiDarah(entity.getIdMonitoringTransfusiDarah());
                    transfusiDarah.setIdDetailCheckup(entity.getIdDetailCheckup());
                    transfusiDarah.setWaktu(entity.getWaktu());
                    transfusiDarah.setTekananDarah(entity.getTekananDarah());
                    transfusiDarah.setNadi(entity.getNadi());
                    transfusiDarah.setSuhu(entity.getSuhu());
                    transfusiDarah.setRr(entity.getRr());
                    transfusiDarah.setTidakAdaReaksi(bean.getTidakAdaReaksi());
                    transfusiDarah.setGatal(entity.getGatal());
                    transfusiDarah.setUrtikariaBeratSedang(entity.getUrtikariaBeratSedang());
                    transfusiDarah.setKulitKemerahanSedang(entity.getKulitKemerahanSedang());
                    transfusiDarah.setDemamSedang(entity.getDemamSedang());
                    transfusiDarah.setMenggigilSedang(entity.getMenggigilSedang());
                    transfusiDarah.setGelisahSedang(entity.getGelisahSedang());
                    transfusiDarah.setPeningkatanDetakJantungSedang(entity.getPeningkatanDetakJantungSedang());
                    transfusiDarah.setDemamMengancam(entity.getDemamMengancam());
                    transfusiDarah.setMenggigilMengancam(entity.getMenggigilMengancam());
                    transfusiDarah.setGelisahMengancam(entity.getGelisahMengancam());
                    transfusiDarah.setPeningkatanDetakJantungMengancam(entity.getPeningkatanDetakJantungMengancam());
                    transfusiDarah.setNafasCepatMengancam(entity.getNafasCepatMengancam());
                    transfusiDarah.setUrinMengancam(entity.getUrinMengancam());
                    transfusiDarah.setPendarahanJantungMengancam(entity.getPendarahanJantungMengancam());
                    transfusiDarah.setKesadaranMengancam(entity.getKesadaranMengancam());
                    transfusiDarah.setKeterangan(entity.getKeterangan());
                    transfusiDarah.setAction(entity.getAction());
                    transfusiDarah.setFlag(entity.getFlag());
                    transfusiDarah.setCreatedDate(entity.getCreatedDate());
                    transfusiDarah.setCreatedWho(entity.getCreatedWho());
                    transfusiDarah.setLastUpdate(entity.getLastUpdate());
                    transfusiDarah.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(transfusiDarah);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(MonitoringTransfusiDarah bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsMonitoringTransfusiDarahEntity transfusiDarah = new ItSimrsMonitoringTransfusiDarahEntity();
            transfusiDarah.setIdMonitoringTransfusiDarah("MTD"+monitoringTransfusiDarahDao.getNextSeq());
            transfusiDarah.setIdDetailCheckup(bean.getIdDetailCheckup());
            transfusiDarah.setWaktu(bean.getWaktu());
            transfusiDarah.setTekananDarah(bean.getTekananDarah());
            transfusiDarah.setNadi(bean.getNadi());
            transfusiDarah.setSuhu(bean.getSuhu());
            transfusiDarah.setRr(bean.getRr());
            transfusiDarah.setTidakAdaReaksi(bean.getTidakAdaReaksi());
            transfusiDarah.setGatal(bean.getGatal());
            transfusiDarah.setUrtikariaBeratSedang(bean.getUrtikariaBeratSedang());
            transfusiDarah.setKulitKemerahanSedang(bean.getKulitKemerahanSedang());
            transfusiDarah.setDemamSedang(bean.getDemamSedang());
            transfusiDarah.setMenggigilSedang(bean.getMenggigilSedang());
            transfusiDarah.setGelisahSedang(bean.getGelisahSedang());
            transfusiDarah.setPeningkatanDetakJantungSedang(bean.getPeningkatanDetakJantungSedang());
            transfusiDarah.setDemamMengancam(bean.getDemamMengancam());
            transfusiDarah.setMenggigilMengancam(bean.getMenggigilMengancam());
            transfusiDarah.setGelisahMengancam(bean.getGelisahMengancam());
            transfusiDarah.setPeningkatanDetakJantungMengancam(bean.getPeningkatanDetakJantungMengancam());
            transfusiDarah.setNafasCepatMengancam(bean.getNafasCepatMengancam());
            transfusiDarah.setUrinMengancam(bean.getUrinMengancam());
            transfusiDarah.setPendarahanJantungMengancam(bean.getPendarahanJantungMengancam());
            transfusiDarah.setKesadaranMengancam(bean.getKesadaranMengancam());
            transfusiDarah.setKeterangan(bean.getKeterangan());
            transfusiDarah.setAction(bean.getAction());
            transfusiDarah.setFlag(bean.getFlag());
            transfusiDarah.setCreatedDate(bean.getCreatedDate());
            transfusiDarah.setCreatedWho(bean.getCreatedWho());
            transfusiDarah.setLastUpdate(bean.getLastUpdate());
            transfusiDarah.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                monitoringTransfusiDarahDao.addAndSave(transfusiDarah);
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

    @Override
    public CrudResponse saveDelete(MonitoringTransfusiDarah bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsMonitoringTransfusiDarahEntity transfusiDarahEntity = new ItSimrsMonitoringTransfusiDarahEntity();
        try {
            transfusiDarahEntity = monitoringTransfusiDarahDao.getById("idMonitoringTransfusiDarah", bean.getIdMonitoringTransfusiDarah());
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        if(transfusiDarahEntity != null){
            transfusiDarahEntity.setFlag("N");
            transfusiDarahEntity.setLastUpdate(bean.getLastUpdate());
            transfusiDarahEntity.setLastUpdateWho(bean.getLastUpdateWho());
            transfusiDarahEntity.setAction("D");
            try {
                monitoringTransfusiDarahDao.updateAndSave(transfusiDarahEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("Data tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setMonitoringTransfusiDarahDao(MonitoringTransfusiDarahDao monitoringTransfusiDarahDao) {
        this.monitoringTransfusiDarahDao = monitoringTransfusiDarahDao;
    }
}
