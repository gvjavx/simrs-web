package com.neurix.simrs.transaksi.asesmenoperasi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.MonAnestesiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.dao.MonAnestesiDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsMonitoringAnestesiEntity;
import com.neurix.simrs.transaksi.asesmenoperasi.model.MonitoringAnestesi;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
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
            if (bean.getIsDesc() != null && !"".equalsIgnoreCase(bean.getIsDesc())) {
                hsCriteria.put("is_desc", bean.getIsDesc());
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
                    monitoringAnestesi.setSistole(entity.getSistole());
                    monitoringAnestesi.setO2(entity.getO2());
                    monitoringAnestesi.setN2O(entity.getN2O());
                    monitoringAnestesi.setDiastole(entity.getDiastole());
                    monitoringAnestesi.setInhalasi(entity.getInhalasi());
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
        if (bean != null) {
            ItSimrsMonitoringAnestesiEntity monitoringAnestesiEntity = new ItSimrsMonitoringAnestesiEntity();
            monitoringAnestesiEntity.setIdMonitoringAnestesi("MON" + monAnestesiDao.getNextSeq());
            monitoringAnestesiEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            monitoringAnestesiEntity.setWaktu(bean.getWaktu());
            monitoringAnestesiEntity.setRr(bean.getRr());
            monitoringAnestesiEntity.setNadi(bean.getNadi());
            monitoringAnestesiEntity.setSistole(bean.getSistole());
            monitoringAnestesiEntity.setDiastole(bean.getDiastole());
            monitoringAnestesiEntity.setO2(bean.getO2());
            monitoringAnestesiEntity.setN2O(bean.getN2O());
            monitoringAnestesiEntity.setInhalasi(bean.getInhalasi());
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

    @Override
    public CrudResponse saveDelete(MonitoringAnestesi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsMonitoringAnestesiEntity entity = monAnestesiDao.getById("idMonitoringAnestesi", bean.getIdMonitoringAnestesi());
        if (entity != null) {
            entity.setFlag("N");
            entity.setAction("D");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                monAnestesiDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMsg("berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data tidak diketahui...!");
        }
        return response;
    }

    public void setMonAnestesiDao(MonAnestesiDao monAnestesiDao) {
        this.monAnestesiDao = monAnestesiDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
