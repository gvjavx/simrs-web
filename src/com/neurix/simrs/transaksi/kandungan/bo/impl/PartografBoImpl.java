package com.neurix.simrs.transaksi.kandungan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.bo.PartografBo;
import com.neurix.simrs.transaksi.kandungan.dao.PartografDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsPartografEntity;
import com.neurix.simrs.transaksi.kandungan.model.Partograf;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartografBoImpl implements PartografBo {

    private static transient Logger logger = Logger.getLogger(PartografBoImpl.class);
    private PartografDao partografDao;

    @Override
    public List<Partograf> getByCriteria(Partograf bean) throws GeneralBOException {
        List<Partograf> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdPartograf() != null && !"".equalsIgnoreCase(bean.getIdPartograf())) {
                hsCriteria.put("id_partograf", bean.getIdPartograf());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }

            List<ItSimrsPartografEntity> entityList = new ArrayList<>();

            try {
                entityList = partografDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsPartografEntity entity : entityList) {
                    Partograf partograf = new Partograf();
                    partograf.setIdPartograf(entity.getIdPartograf());
                    partograf.setIdDetailCheckup(entity.getIdDetailCheckup());
                    partograf.setWaktu(entity.getWaktu());
                    partograf.setDjj(entity.getDjj());
                    partograf.setAirKetuban(entity.getAirKetuban());
                    partograf.setMolase(entity.getMolase());
                    partograf.setPembukaan(entity.getPembukaan());
                    partograf.setKontraksi(entity.getKontraksi());
                    partograf.setOksitosin(entity.getOksitosin());
                    partograf.setTetes(entity.getTetes());
                    partograf.setObatCairan(entity.getObatCairan());
                    partograf.setNadi(entity.getNadi());
                    partograf.setTensi(entity.getTensi());
                    partograf.setSuhu(entity.getSuhu());
                    partograf.setRr(entity.getRr());
                    partograf.setAction(entity.getAction());
                    partograf.setFlag(entity.getFlag());
                    partograf.setCreatedDate(entity.getCreatedDate());
                    partograf.setCreatedWho(entity.getCreatedWho());
                    partograf.setLastUpdate(entity.getLastUpdate());
                    partograf.setLastUpdateWho(entity.getLastUpdateWho());
                    partograf.setNamaTerang(entity.getNamaTerang());
                    partograf.setSip(entity.getSip());
                    partograf.setTtd(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getTtd());
                    partograf.setLamaKontraksi(entity.getLamaKontraksi());
                    list.add(partograf);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(Partograf bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            Boolean cek = partografDao.cekData(bean.getIdDetailCheckup(), bean.getWaktu());
            if(cek){
                response.setStatus("error");
                response.setMsg("Found Error, Data yang anda masukan sudah ada...!");
            }else{
                ItSimrsPartografEntity partografEntity = new ItSimrsPartografEntity();
                partografEntity.setIdPartograf("POT" + partografDao.getNextSeq());
                partografEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                partografEntity.setWaktu(bean.getWaktu());
                partografEntity.setDjj(bean.getDjj());
                partografEntity.setAirKetuban(bean.getAirKetuban());
                partografEntity.setMolase(bean.getMolase());
                partografEntity.setPembukaan(bean.getPembukaan());
                partografEntity.setKontraksi(bean.getKontraksi());
                partografEntity.setOksitosin(bean.getOksitosin());
                partografEntity.setTetes(bean.getTetes());
                partografEntity.setObatCairan(bean.getObatCairan());
                partografEntity.setNadi(bean.getNadi());
                partografEntity.setTensi(bean.getTensi());
                partografEntity.setSuhu(bean.getSuhu());
                partografEntity.setRr(bean.getRr());
                partografEntity.setAction(bean.getAction());
                partografEntity.setFlag(bean.getFlag());
                partografEntity.setCreatedDate(bean.getCreatedDate());
                partografEntity.setCreatedWho(bean.getCreatedWho());
                partografEntity.setLastUpdate(bean.getLastUpdate());
                partografEntity.setLastUpdateWho(bean.getLastUpdateWho());
                partografEntity.setTtd(bean.getTtd());
                partografEntity.setNamaTerang(bean.getNamaTerang());
                partografEntity.setSip(bean.getSip());
                try {
                    partografDao.addAndSave(partografEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(Partograf bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsPartografEntity partografEntity = new ItSimrsPartografEntity();
        try {
            partografEntity = partografDao.getById("idPartograf", bean.getIdPartograf());
        }catch (HibernateException e){
            response.setStatus("error");
            response.setMsg("Found Error, "+e.getMessage());
            logger.error("Found Error, "+e.getMessage());
        }
        if(partografEntity != null){
            partografEntity.setLastUpdateWho(bean.getLastUpdateWho());
            partografEntity.setLastUpdate(bean.getLastUpdate());
            partografEntity.setFlag("N");
            partografEntity.setAction("D");
            try {
                partografDao.updateAndSave(partografEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error, "+e.getMessage());
                logger.error("Found Error, "+e.getMessage());
                return response;
            }
        }
        return response;
    }

    @Override
    public List<Partograf> getListByDate(String idDetailCheckup, String tanggal) throws GeneralBOException {
        return partografDao.getListByDate(idDetailCheckup, tanggal);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setPartografDao(PartografDao partografDao) {
        this.partografDao = partografDao;
    }
}
