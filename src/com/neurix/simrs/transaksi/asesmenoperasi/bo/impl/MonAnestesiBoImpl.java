package com.neurix.simrs.transaksi.asesmenoperasi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.MonAnestesiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.dao.MonAnestesiDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsMonitoringAnestesiEntity;
import com.neurix.simrs.transaksi.asesmenoperasi.model.MonitoringAnestesi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonAnestesiBoImpl implements MonAnestesiBo {
    private static transient Logger logger = Logger.getLogger(MonAnestesiBoImpl.class);
    private MonAnestesiDao monAnestesiDao;

    @Override
    public List<MonitoringAnestesi> getByCriteria(MonitoringAnestesi bean) throws GeneralBOException {
        List<MonitoringAnestesi> list = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdMonitoringAnestesi() != null && !"".equalsIgnoreCase(bean.getIdMonitoringAnestesi())) {
                hsCriteria.put("id_monitoring_anestesi", bean.getIdMonitoringAnestesi());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsMonitoringAnestesiEntity> entityList = new ArrayList<>();

            try {
                entityList = monAnestesiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (entityList.size() > 0) {
                for (ItSimrsMonitoringAnestesiEntity entity : entityList) {
                    MonitoringAnestesi monitoringAnestesi = new MonitoringAnestesi();
                    monitoringAnestesi.setIdMonitoringAnestesi(entity.getIdMonitoringAnestesi());
                    monitoringAnestesi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    monitoringAnestesi.setWaktu(entity.getWaktu());
                    monitoringAnestesi.setRr(entity.getRr());
                    monitoringAnestesi.setNadi(entity.getNadi());
                    monitoringAnestesi.setTensi(entity.getTensi());
                    monitoringAnestesi.setAnest(entity.getAnest());
                    monitoringAnestesi.setO2(entity.getO2());
                    monitoringAnestesi.setN2O(entity.getN2O());
                    monitoringAnestesi.setEthran(entity.getEthran());
                    monitoringAnestesi.setIso(entity.getIso());
                    monitoringAnestesi.setSevo(entity.getSevo());
                    monitoringAnestesi.setInfus(entity.getInfus());
                    monitoringAnestesi.setKeterangan(entity.getKeterangan());
                    monitoringAnestesi.setJenis(entity.getJenis());
                    monitoringAnestesi.setAction(entity.getAction());
                    monitoringAnestesi.setFlag(entity.getFlag());
                    monitoringAnestesi.setCreatedDate(entity.getCreatedDate());
                    monitoringAnestesi.setCreatedWho(entity.getCreatedWho());
                    monitoringAnestesi.setLastUpdate(entity.getLastUpdate());
                    monitoringAnestesi.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(monitoringAnestesi);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(MonitoringAnestesi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsMonitoringAnestesiEntity monitoringAnestesiEntity = new ItSimrsMonitoringAnestesiEntity();
            monitoringAnestesiEntity.setIdMonitoringAnestesi("MON"+monAnestesiDao.getNextSeq());
            monitoringAnestesiEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            monitoringAnestesiEntity.setWaktu(bean.getWaktu());
            monitoringAnestesiEntity.setRr(bean.getRr());
            monitoringAnestesiEntity.setNadi(bean.getNadi());
            monitoringAnestesiEntity.setTensi(bean.getTensi());
            monitoringAnestesiEntity.setAnest(bean.getAnest());
            monitoringAnestesiEntity.setO2(bean.getO2());
            monitoringAnestesiEntity.setN2O(bean.getN2O());
            monitoringAnestesiEntity.setEthran(bean.getEthran());
            monitoringAnestesiEntity.setIso(bean.getIso());
            monitoringAnestesiEntity.setSevo(bean.getSevo());
            monitoringAnestesiEntity.setInfus(bean.getInfus());
            monitoringAnestesiEntity.setKeterangan(bean.getKeterangan());
            monitoringAnestesiEntity.setJenis(bean.getJenis());
            monitoringAnestesiEntity.setAction(bean.getAction());
            monitoringAnestesiEntity.setFlag(bean.getFlag());
            monitoringAnestesiEntity.setCreatedDate(bean.getCreatedDate());
            monitoringAnestesiEntity.setCreatedWho(bean.getCreatedWho());
            monitoringAnestesiEntity.setLastUpdate(bean.getLastUpdate());
            monitoringAnestesiEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                monAnestesiDao.addAndSave(monitoringAnestesiEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public Boolean cekDataAnestesi(String id, String jam, String jenis) throws GeneralBOException {
        return monAnestesiDao.cekDataAnestesi(id, jam, jenis);
    }

    public void setMonAnestesiDao(MonAnestesiDao monAnestesiDao) {
        this.monAnestesiDao = monAnestesiDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
