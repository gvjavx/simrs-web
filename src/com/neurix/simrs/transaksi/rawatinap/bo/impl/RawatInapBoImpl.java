package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
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
    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private StatusPasienDao statusPasienDao;

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

            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setNoCheckup(rawatInap.getNoCheckup());
            List<ItSimrsHeaderChekupEntity> headerChekupEntityList = getListEntityHeaderCheckup(headerCheckup);

            ItSimrsHeaderChekupEntity itSimrsHeaderChekupEntity = new ItSimrsHeaderChekupEntity();
            if (!headerChekupEntityList.isEmpty()){
                itSimrsHeaderChekupEntity = headerChekupEntityList.get(0);
            }

            if (itSimrsHeaderChekupEntity != null){
                rawatInap.setIdPasien(itSimrsHeaderChekupEntity.getIdPasien());
                rawatInap.setNamaPasien(itSimrsHeaderChekupEntity.getNama());
            }

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setNoCheckup(rawatInap.getNoCheckup());
            List<ItSimrsHeaderDetailCheckupEntity> headerDetailCheckupEntityList = getListEntityCheckupDetail(headerDetailCheckup);

            ItSimrsHeaderDetailCheckupEntity itSimrsHeaderDetailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
            if (!headerDetailCheckupEntityList.isEmpty()){
                itSimrsHeaderDetailCheckupEntity = headerDetailCheckupEntityList.get(0);
            }

            if (itSimrsHeaderDetailCheckupEntity != null){
                rawatInap.setIdStatusPeriksa(itSimrsHeaderDetailCheckupEntity.getStatusPeriksa());
                StatusPasien statusPasien = new StatusPasien();
                statusPasien.setIdStatusPasien(itSimrsHeaderDetailCheckupEntity.getStatusPeriksa());
                List<ImSimrsStatusPasienEntity> statusPasienEntityList = getListEntityStatusPasien(statusPasien);

                ImSimrsStatusPasienEntity imSimrsStatusPasienEntity = new ImSimrsStatusPasienEntity();
                if (!statusPasienEntityList.isEmpty()){
                    imSimrsStatusPasienEntity = statusPasienEntityList.get(0);
                }

                if (imSimrsStatusPasienEntity != null){
                    rawatInap.setStatusPeriksaName(imSimrsStatusPasienEntity.getKeterangan());
                }
            }

            results.add(rawatInap);
        }

        logger.info("[RawatInapBoImpl.setToRawatInapTemplate] End <<<<<<<<");
        return results;
    }

    public List<ItSimrsHeaderChekupEntity> getListEntityHeaderCheckup(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[RawatInapBoImpl.getHeaderCheckup] Start >>>>>>>");

        if (bean != null) {

            Boolean isPoli = false;
            Boolean isStatus = false;
            Map hsCriteria = new HashMap();

            //sodiq, 17 Nov 2019, penambahan no_checkup
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                isPoli = true;
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }
            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                isStatus = true;
                hsCriteria.put("status_periksa", bean.getStatusPeriksa());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
                hsCriteria.put("no_ktp", bean.getNoKtp());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            hsCriteria.put("flag", "Y");
            List<String> listOfNoCheckup = new ArrayList<>();

            try {
                listOfNoCheckup = headerCheckupDao.getListNoCheckupByCriteria(hsCriteria, isPoli, isStatus);
            } catch (HibernateException e){
                logger.error("[RawatInapBoImpl.getHeaderCheckup] error when get data by criteria on getListNoCheckupByCriteria "+ e.getMessage());
            }

            if (!listOfNoCheckup.isEmpty()){
                hsCriteria = new HashMap();
                hsCriteria.put("list_no_checkup", listOfNoCheckup);
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
                try {
                    headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[RawatInapBoImpl.getHeaderCheckup] error when get data by criteria "+ e.getMessage());
                }

                if (!headerChekupEntities.isEmpty()){
                    logger.info("[RawatInapBoImpl.getHeaderCheckup] End <<<<<<<");
                    return headerChekupEntities;
                }
            }
        }
        logger.info("[RawatInapBoImpl.getHeaderCheckup] End <<<<<<<");
        return null;
    }

    protected List<ItSimrsHeaderDetailCheckupEntity> getListEntityCheckupDetail(HeaderDetailCheckup bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getCheckupDetail] Start >>>>>>>");
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
            logger.error("[RawatInapBoImpl.getCheckupDetail] Error when get data detail checkup entity ",e);
            throw new GeneralBOException("Error when get data detail checkup entity "+e.getMessage());
        }

        logger.info("[RawatInapBoImpl.getCheckupDetail] End <<<<<<<<");
        return entityList;
    }

    protected List<ImSimrsStatusPasienEntity> getListEntityStatusPasien(StatusPasien bean){
        logger.info("[RawatInapBoImpl.getListEntityStatusPasien] Start >>>>>>>>>");

        List<ImSimrsStatusPasienEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_status_pasien", bean.getIdStatusPasien());

        try {
            results = statusPasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListEnstityStatusPasien] Error When get data status pasien");
        }

        logger.info("[RawatInapBoImpl.getListEntityStatusPasien] End <<<<<<<<<");
        return results;
    }

    public void setStatusPasienDao(StatusPasienDao statusPasienDao) { this.statusPasienDao = statusPasienDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) { this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) { this.headerCheckupDao = headerCheckupDao;
    }
    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }
}
