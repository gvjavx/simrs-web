package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.bo.impl.StatusPasienBoImpl;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
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
public class CheckupDetailBoImpl extends CheckupModuls implements CheckupDetailBo{

    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(CheckupDetailBoImpl.class);

    private CheckupDetailDao checkupDetailDao;

    @Override
    public List<HeaderDetailCheckup> getByCriteria(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getByCriteria] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntityList = getListEntityByCriteria(bean);
            if (!detailCheckupEntityList.isEmpty()){
                results = setToDetailCheckupTemplate(detailCheckupEntityList);
            }
        }

        logger.info("[CheckupDetailBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    protected List<ItSimrsHeaderDetailCheckupEntity> getListEntityByCriteria(HeaderDetailCheckup bean) throws GeneralBOException{
        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        try {
            entityList = checkupDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupDetailBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    protected List<HeaderDetailCheckup> setToDetailCheckupTemplate(List<ItSimrsHeaderDetailCheckupEntity> entityList) throws GeneralBOException{
        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] Start >>>>>>>");
        List<HeaderDetailCheckup> results = new ArrayList<>();

        HeaderDetailCheckup detailCheckup;
        for (ItSimrsHeaderDetailCheckupEntity entity : entityList){
            detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(entity.getIdDetailCheckup());
            detailCheckup.setNoCheckup(entity.getNoCheckup());
            detailCheckup.setIdPelayanan(entity.getIdPelayanan());
            detailCheckup.setStatusPeriksa(entity.getStatusPeriksa());
            detailCheckup.setStatusBayar(entity.getStatusBayar());
            detailCheckup.setTotalBiaya(entity.getTotalBiaya());
            detailCheckup.setKeteranganSelesai(entity.getKeteranganSelesai());
            detailCheckup.setJenisLab(entity.getJenisLab());
            detailCheckup.setBranchId(entity.getBranchId());
            detailCheckup.setFlag(entity.getFlag());
            detailCheckup.setAction(entity.getAction());
            detailCheckup.setCreatedDate(entity.getCreatedDate());
            detailCheckup.setCreatedWho(entity.getCreatedWho());
            detailCheckup.setLastUpdate(entity.getLastUpdate());
            detailCheckup.setLastUpdateWho(entity.getLastUpdateWho());

            if (detailCheckup.getStatusPeriksa() != null && !"".equalsIgnoreCase(detailCheckup.getStatusPeriksa())){
                StatusPasien statusPasien = new StatusPasien();
                statusPasien.setIdStatusPasien(detailCheckup.getStatusPeriksa());
                detailCheckup.setStatusPeriksa(getListEntityStatusPasien(statusPasien).get(0).getKeterangan());
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
            List<ItSimrsRawatInapEntity> rawatInapEntitys = getListEntityRawatInap(rawatInap);

            ItSimrsRawatInapEntity rawatInapEntity = new ItSimrsRawatInapEntity();
            if (!rawatInapEntitys.isEmpty()){
                rawatInapEntity = rawatInapEntitys.get(0);
            }


            if (rawatInapEntity != null){
                detailCheckup.setNoRuangan(rawatInapEntity.getNoRuangan());
                detailCheckup.setIdRuangan(rawatInapEntity.getIdRuangan());
                detailCheckup.setNamaRuangan(rawatInapEntity.getNamaRangan());
            }

            results.add(detailCheckup);
        }

        logger.info("[CheckupDetailBoImpl.setToDetailCheckupTemplate] End <<<<<<<<");
        return results;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

}
