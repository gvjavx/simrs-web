package com.neurix.simrs.transaksi.hemodinamika.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodinamika.bo.HemodinamikaBo;
import com.neurix.simrs.transaksi.hemodinamika.dao.HemodinamikaDao;
import com.neurix.simrs.transaksi.hemodinamika.model.Hemodinamika;
import com.neurix.simrs.transaksi.hemodinamika.model.ItSimrsHemodinamikaEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HemodinamikaBoImpl implements HemodinamikaBo {
    private static transient Logger logger = Logger.getLogger(HemodinamikaBoImpl.class);
    private HemodinamikaDao hemodinamikaDao;

    @Override
    public List<Hemodinamika> getByCriteria(Hemodinamika bean) throws GeneralBOException {
        List<Hemodinamika> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdHemodinamika() != null && !"".equalsIgnoreCase(bean.getIdHemodinamika())) {
                hsCriteria.put("id_hemodinamika", bean.getIdHemodinamika());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getTanggal() != null && !"".equalsIgnoreCase(bean.getTanggal().toString())) {
                hsCriteria.put("tanggal", bean.getTanggal());
            }

            List<ItSimrsHemodinamikaEntity> entityList = new ArrayList<>();

            try {
                entityList = hemodinamikaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsHemodinamikaEntity entity : entityList) {
                    Hemodinamika hemodinamika = new Hemodinamika();
                    hemodinamika.setIdHemodinamika(entity.getIdHemodinamika());
                    hemodinamika.setIdDetailCheckup(entity.getIdDetailCheckup());
                    hemodinamika.setWaktu(entity.getWaktu());
                    hemodinamika.setTanggal(entity.getTanggal());
                    hemodinamika.setTensi(entity.getTensi());
                    hemodinamika.setBp(entity.getBp());
                    hemodinamika.setSistole(entity.getSistole());
                    hemodinamika.setDiastole(entity.getDiastole());
                    hemodinamika.setHi(entity.getHi());
                    hemodinamika.setRr(entity.getRr());
                    hemodinamika.setEkg(entity.getEkg());
                    hemodinamika.setIcp(entity.getIcp());
                    hemodinamika.setIbp(entity.getIbp());
                    hemodinamika.setCvp(entity.getCvp());
                    hemodinamika.setMap(entity.getMap());
                    hemodinamika.setKeterangan(entity.getKeterangan());
                    hemodinamika.setAction(entity.getAction());
                    hemodinamika.setFlag(entity.getFlag());
                    hemodinamika.setCreatedDate(entity.getCreatedDate());
                    hemodinamika.setCreatedWho(entity.getCreatedWho());
                    hemodinamika.setLastUpdate(entity.getLastUpdate());
                    hemodinamika.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(hemodinamika);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(Hemodinamika bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            ItSimrsHemodinamikaEntity hemodinamikaEntity = new ItSimrsHemodinamikaEntity();
            hemodinamikaEntity.setIdHemodinamika("HDM" + hemodinamikaDao.getNextSeq());
            hemodinamikaEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            hemodinamikaEntity.setWaktu(bean.getWaktu());
            String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(bean.getCreatedDate());
            hemodinamikaEntity.setTanggal(Date.valueOf(formatDate));
            hemodinamikaEntity.setTensi(bean.getTensi());
            hemodinamikaEntity.setSistole(bean.getSistole());
            hemodinamikaEntity.setDiastole(bean.getDiastole());
            hemodinamikaEntity.setHi(bean.getHi());
            hemodinamikaEntity.setRr(bean.getRr());
            hemodinamikaEntity.setEkg(bean.getEkg());
            hemodinamikaEntity.setIcp(bean.getIcp());
            hemodinamikaEntity.setIbp(bean.getIbp());
            hemodinamikaEntity.setCvp(bean.getCvp());
            hemodinamikaEntity.setMap(bean.getMap());
            hemodinamikaEntity.setKeterangan(bean.getKeterangan());
            hemodinamikaEntity.setAction(bean.getAction());
            hemodinamikaEntity.setFlag(bean.getFlag());
            hemodinamikaEntity.setCreatedDate(bean.getCreatedDate());
            hemodinamikaEntity.setCreatedWho(bean.getCreatedWho());
            hemodinamikaEntity.setLastUpdate(bean.getLastUpdate());
            hemodinamikaEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                hemodinamikaDao.addAndSave(hemodinamikaEntity);
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
    public Boolean cekData(String id, String waktu) throws GeneralBOException {
        return hemodinamikaDao.cekData(id, waktu);
    }

    @Override
    public CrudResponse saveDelete(Hemodinamika bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsHemodinamikaEntity hemodinamikaEntity = hemodinamikaDao.getById("idHemodinamika", bean.getIdHemodinamika());
        if(hemodinamikaEntity != null){
            hemodinamikaEntity.setFlag("N");
            hemodinamikaEntity.setAction("D");
            hemodinamikaEntity.setLastUpdate(bean.getLastUpdate());
            hemodinamikaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                hemodinamikaDao.updateAndSave(hemodinamikaEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("Data tidak dutemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHemodinamikaDao(HemodinamikaDao hemodinamikaDao) {
        this.hemodinamikaDao = hemodinamikaDao;
    }
}
