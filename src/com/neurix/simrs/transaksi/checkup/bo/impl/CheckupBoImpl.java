package com.neurix.simrs.transaksi.checkup.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusRekruitment.bo.impl.StatusRekruitmentBoImpl;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */

public class CheckupBoImpl implements CheckupBo {
    protected static transient Logger logger = Logger.getLogger(StatusRekruitmentBoImpl.class);

    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    @Override
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {

            Boolean isPoli = false;
            Boolean isStatus = false;
            Map hsCriteria = new HashMap();

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
                logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria on getListNoCheckupByCriteria "+ e.getMessage());
            }

            if (!listOfNoCheckup.isEmpty()){
                hsCriteria = new HashMap();
                hsCriteria.put("list_no_checkup", listOfNoCheckup);
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
                try {
                    headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria "+ e.getMessage());
                }

                if (!headerChekupEntities.isEmpty()){
                    logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
                    return setTemplateToHeaderCheckupResult(headerChekupEntities);
                }
            }
        }
        logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String s) {
        return null;
    }

    public List<HeaderCheckup> setTemplateToHeaderCheckupResult(List<ItSimrsHeaderChekupEntity> listHeader){
        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] Start >>>>>>>");

        List<HeaderCheckup> result = new ArrayList<>();

        HeaderCheckup headerCheckup;
        for (ItSimrsHeaderChekupEntity headerList : listHeader){
            headerCheckup = new HeaderCheckup();
            headerCheckup.setNoCheckup(headerList.getNoCheckup());
            headerCheckup.setIdPasien(headerList.getIdPasien());
            headerCheckup.setNama(headerList.getNama());
            headerCheckup.setJenisKelamin(headerList.getJenisKelamin());
            headerCheckup.setNoKtp(headerList.getNoKtp());
            headerCheckup.setTempatLahir(headerList.getTempatLahir());
            headerCheckup.setTglLahir(headerList.getTglLahir());
            headerCheckup.setDesaId(headerList.getDesaId());
            headerCheckup.setJalan(headerList.getJalan());
            headerCheckup.setSuku(headerList.getSuku());
            headerCheckup.setAgama(headerList.getAgama());
            headerCheckup.setProfesi(headerList.getProfesi());
            headerCheckup.setNoTelp(headerList.getNoTelp());
            headerCheckup.setIdJenisPeriksaPasien(headerList.getIdJenisPeriksaPasien());
            headerCheckup.setKeteranganKeluar(headerList.getKeteranganKeluar());
            headerCheckup.setUrlKtp(headerList.getUrlKtp());
            headerCheckup.setBranchId(headerList.getBranchId());
            headerCheckup.setFlag(headerList.getFlag());
            headerCheckup.setCreatedDate(headerList.getCreatedDate());
            headerCheckup.setLastUpdate(headerList.getLastUpdate());
            headerCheckup.setCreatedWho(headerList.getCreatedWho());
            headerCheckup.setLastUpdateWho(headerList.getLastUpdateWho());

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            try {
                headerDetailCheckup = headerCheckupDao.getLastPoliAndStatus(headerList.getNoCheckup());
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.setTemplateToHeaderCheckupResult] error when get data on getLastPoliAndStatus "+ e.getMessage());
            }

            if (headerDetailCheckup != null){
                headerCheckup.setIdPelayanan(headerDetailCheckup.getIdPelayanan());
                headerCheckup.setStatusPeriksa(headerDetailCheckup.getStatusPeriksa());
                headerCheckup.setStatusPeriksaName(headerDetailCheckup.getStatusPeriksaName());
            }
            result.add(headerCheckup);
        }

        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] End <<<<<<<");
        return result;
    }

    @Override
    public void saveAdd(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAdd] Start >>>>>>>");
        if (bean != null){

            String id = "";
            id = getNextHeaderId();

            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            headerEntity.setNoCheckup("CKP"+id);
            headerEntity.setIdPasien(bean.getIdPasien());
            headerEntity.setNama(bean.getNama());
            headerEntity.setJenisKelamin(bean.getJenisKelamin());
            headerEntity.setNoKtp(bean.getNoKtp());
            headerEntity.setTempatLahir(bean.getTempatLahir());
            headerEntity.setTglLahir(bean.getTglLahir());
            headerEntity.setDesaId(bean.getDesaId());
            headerEntity.setJalan(bean.getJalan());
            headerEntity.setSuku(bean.getSuku());
            headerEntity.setAgama(bean.getProfesi());
            headerEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
            headerEntity.setUrlKtp(bean.getUrlKtp());
            headerEntity.setBranchId(bean.getBranchId());
            headerEntity.setFlag("Y");
            headerEntity.setAction("C");
            headerEntity.setCreatedDate(bean.getCreatedDate());
            headerEntity.setLastUpdate(bean.getLastUpdate());
            headerEntity.setCreatedWho(bean.getCreatedWho());
            headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerEntity.setJenisKunjungan(bean.getJenisKunjungan());

            try {
                headerCheckupDao.addAndSave(headerEntity);
            } catch (HibernateException e){
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                id = "";
                id = getNextDetailCheckupId();
                detailCheckupEntity.setIdDetailCheckup("DCM"+id);
                detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                detailCheckupEntity.setStatusPeriksa("0");
                detailCheckupEntity.setFlag("Y");
                detailCheckupEntity.setAction("C");
                detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
                detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setTglAntrian(bean.getCreatedDate());

                try {
                    checkupDetailDao.addAndSave(detailCheckupEntity);
                } catch (HibernateException e){
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup");
                }

            }
            logger.info("[CheckupBoImpl.saveAdd] End <<<<<<<");
        }
    }

    private String getNextHeaderId(){
        String id = "";
        try {
            id = headerCheckupDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextHeaderId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextHeaderId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextDetailCheckupId(){
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e){
            logger.error("[CheckupBoImpl.getNextDetailCheckupId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }
}
