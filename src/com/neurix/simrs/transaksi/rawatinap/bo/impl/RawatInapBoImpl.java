package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class RawatInapBoImpl implements RawatInapBo {

    private static transient Logger logger = Logger.getLogger(RawatInapBoImpl.class);
    private RawatInapDao rawatInapDao;

    public List<RawatInap> getListEntityRawatInap(RawatInap bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getListEntityRawatInap] Start >>>>>>>");
        List<RawatInap> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsRawatInapEntity> rawatInapEntityList = getListEntityByCriteria(bean);
            if (!rawatInapEntityList.isEmpty()){
                results = setToRawatInapTemplate(rawatInapEntityList);
            }
        }

        logger.info("[RawatInapBoImpl.getListEntityRawatInap] End <<<<<<<<");
        return results;
    }


    protected List<ItSimrsRawatInapEntity> getListEntityByCriteria(RawatInap bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsRawatInapEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }

        hsCriteria.put("flag", "Y");

        try {
            entityList = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[RawatInapBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    protected List<RawatInap> setToRawatInapTemplate(List<ItSimrsRawatInapEntity> entityList) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.setToRawatInapTemplate] Start >>>>>>>");
        List<RawatInap> results = new ArrayList<>();

        RawatInap rawatInap;
        for (ItSimrsRawatInapEntity entity : entityList){
            rawatInap = new RawatInap();
            rawatInap.setIdRawatInap(entity.getIdRawatInap());
            rawatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
            rawatInap.setNoCheckup(entity.getNoCheckup());
            rawatInap.setIdRuangan(entity.getIdRuangan());
            rawatInap.setNamaRangan(entity.getNamaRangan());
            rawatInap.setNoRuangan(entity.getNoRuangan());
            rawatInap.setKeterangan(entity.getKeterangan());
            rawatInap.setTarif(entity.getTarif());
            rawatInap.setTglMasuk(entity.getTglMasuk());
            rawatInap.setTglKeluar(entity.getTglKeluar());
            rawatInap.setFlag(entity.getFlag());
            rawatInap.setAction(entity.getAction());
            rawatInap.setCreatedDate(entity.getCreatedDate());
            rawatInap.setCreatedWho(entity.getCreatedWho());
            rawatInap.setLastUpdate(entity.getLastUpdate());
            rawatInap.setLastUpdateWho(entity.getLastUpdateWho());

//            if (detailCheckup.getStatusPeriksa() != null && !"".equalsIgnoreCase(detailCheckup.getStatusPeriksa())){
//                StatusPasien statusPasien = new StatusPasien();
//                statusPasien.setIdStatusPasien(detailCheckup.getStatusPeriksa());
//                detailCheckup.setStatusPeriksa(getListEntityStatusPasien(statusPasien).get(0).getKeterangan());
//            }
//
//            RawatInap rawatInap = new RawatInap();
//            rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
//            List<ItSimrsRawatInapEntity> rawatInapEntitys = getListEntityRawatInap(rawatInap);
//
//            ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
//            if (!rawatInapEntitys.isEmpty()){
//                rawatInapEntity = rawatInapEntitys.get(0);
//            }
//
//            if (rawatInapEntity != null){
//                detailCheckup.setNoRuangan(rawatInapEntity.getNoRuangan());
//                detailCheckup.setIdRuangan(rawatInapEntity.getIdRuangan());
//                detailCheckup.setNamaRuangan(rawatInapEntity.getNamaRangan());
//            }
//
//            Pelayanan pelayanan = new Pelayanan();
//            pelayanan.setIdPelayanan(detailCheckup.getIdPelayanan());
//            List<ImSimrsPelayananEntity> pelayananEntityList = getListEntityPelayanan(pelayanan);
//
//            ImSimrsPelayananEntity pelayananEntity = new ImSimrsPelayananEntity();
//            if (!pelayananEntityList.isEmpty()){
//                pelayananEntity = pelayananEntityList.get(0);
//            }
//            if(pelayananEntity != null){
//                detailCheckup.setNamaPelayanan(pelayananEntity.getNamaPelayanan());
//            }

            results.add(rawatInap);
        }

        logger.info("[RawatInapBoImpl.setToRawatInapTemplate] End <<<<<<<<");
        return results;
    }


    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }
}
