package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.impl;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.dao.LaporanAkuntansiDao;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaporanAkuntansiBoImpl implements LaporanAkuntansiBo {

    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiBoImpl.class);
    private LaporanAkuntansiDao laporanAkuntansiDao;
    private PersonilPositionDao personilPositionDao;
    private BiodataDao biodataDao;

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanAkuntansiBoImpl.logger = logger;
    }

    public LaporanAkuntansiDao getLaporanAkuntansiDao() {
        return laporanAkuntansiDao;
    }


    public void setLaporanAkuntansiDao(LaporanAkuntansiDao laporanAkuntansiDao) {
        this.laporanAkuntansiDao = laporanAkuntansiDao;
    }


    @Override
    public void saveDelete(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveDelete] start process >>>");
        if (bean!=null) {
            ItLaporanAkuntansiEntity imVendorEntity = new ItLaporanAkuntansiEntity();
            try {
                // Get data from database by ID
                imVendorEntity = laporanAkuntansiDao.getById("laporanAkuntansiId", bean.getLaporanAkuntansiId());
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (imVendorEntity != null) {
                // Modify from bean to entity serializable
                imVendorEntity.setFlag(bean.getFlag());
                imVendorEntity.setAction(bean.getAction());
                imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imVendorEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    laporanAkuntansiDao.updateAndSave(imVendorEntity);
                } catch (HibernateException e) {
                    logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");

            }
        }
        logger.info("[LaporanAkuntansiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ItLaporanAkuntansiEntity imVendorEntity = null;
            try {
                // Get data from database by ID
                imVendorEntity = laporanAkuntansiDao.getById("laporanAkuntansiId", bean.getLaporanAkuntansiId());
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Vendor by Kode Vendor, please inform to your admin...," + e.getMessage());
            }
            if (imVendorEntity != null) {
                imVendorEntity.setLaporanAkuntansiName(bean.getLaporanAkuntansiName());
                imVendorEntity.setUrl(bean.getUrl());
                imVendorEntity.setLevelKodeRekening(bean.getLevelKodeRekening());
                imVendorEntity.setFlag(bean.getFlag());
                imVendorEntity.setAction(bean.getAction());
                imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imVendorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    laporanAkuntansiDao.updateAndSave(imVendorEntity);
                } catch (HibernateException e) {
                    logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");
            }
        }
        logger.info("[LaporanAkuntansiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public LaporanAkuntansi saveAdd(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String reportId;
            try {
                // Generating ID, get from postgre sequence
                reportId = laporanAkuntansiDao.getNextLaporanAkuntansiId();
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence vendorId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItLaporanAkuntansiEntity imVendorEntity = new ItLaporanAkuntansiEntity();

            imVendorEntity.setLaporanAkuntansiId(reportId);
            imVendorEntity.setLaporanAkuntansiName(bean.getLaporanAkuntansiName());
            imVendorEntity.setUrl(bean.getUrl());
            imVendorEntity.setLevelKodeRekening(bean.getLevelKodeRekening());
            imVendorEntity.setFlag(bean.getFlag());
            imVendorEntity.setAction(bean.getAction());
            imVendorEntity.setCreatedWho(bean.getCreatedWho());
            imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imVendorEntity.setCreatedDate(bean.getCreatedDate());
            imVendorEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                laporanAkuntansiDao.addAndSave(imVendorEntity);
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[LaporanAkuntansiBoImpl.saveAdd] end process <<<");
        return null;
    }
    
    @Override
    public List<LaporanAkuntansi> getByCriteria(LaporanAkuntansi searchBean) throws GeneralBOException {
        List<LaporanAkuntansi> listOfResult = new ArrayList();
        logger.info("[LaporanAkuntansiBoImpl.getByCriteria] start process >>>");

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getLaporanAkuntansiId() != null && !"".equalsIgnoreCase(searchBean.getLaporanAkuntansiId())) {
                hsCriteria.put("laporan_akuntansi_id", searchBean.getLaporanAkuntansiId());
            }
            if (searchBean.getLaporanAkuntansiName() != null && !"".equalsIgnoreCase(searchBean.getLaporanAkuntansiName())) {
                hsCriteria.put("laporan_akuntansi_name", searchBean.getLaporanAkuntansiName());
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItLaporanAkuntansiEntity> imLaporanAkuntansiEntity = null;
            try {
                imLaporanAkuntansiEntity = laporanAkuntansiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imLaporanAkuntansiEntity != null) {
                LaporanAkuntansi returnLaporanAkuntansi;
                // Looping from dao to object and save in collection
                for(ItLaporanAkuntansiEntity itLaporanAkuntansiEntity: imLaporanAkuntansiEntity){
                    returnLaporanAkuntansi = new LaporanAkuntansi();

                    returnLaporanAkuntansi.setLaporanAkuntansiId(itLaporanAkuntansiEntity.getLaporanAkuntansiId());
                    returnLaporanAkuntansi.setLaporanAkuntansiName(itLaporanAkuntansiEntity.getLaporanAkuntansiName());
                    returnLaporanAkuntansi.setUrl(itLaporanAkuntansiEntity.getUrl());
                    returnLaporanAkuntansi.setLevelKodeRekening(itLaporanAkuntansiEntity.getLevelKodeRekening());
                    returnLaporanAkuntansi.setCreatedWho(itLaporanAkuntansiEntity.getCreatedWho());
                    returnLaporanAkuntansi.setCreatedDate(itLaporanAkuntansiEntity.getCreatedDate());
                    returnLaporanAkuntansi.setLastUpdate(itLaporanAkuntansiEntity.getLastUpdate());
                    returnLaporanAkuntansi.setAction(itLaporanAkuntansiEntity.getAction());
                    returnLaporanAkuntansi.setFlag(itLaporanAkuntansiEntity.getFlag());
                    listOfResult.add(returnLaporanAkuntansi);
                }
            }
        }
        logger.info("[LaporanAkuntansiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException {
        LaporanAkuntansi nama = new LaporanAkuntansi();
        List<ItPersonilPositionEntity> managerList = new ArrayList<>();

        //untuk manager keuangan
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,"201");
        if (managerList.size()!=0){
            for (ItPersonilPositionEntity manager : managerList){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",manager.getNip());
                nama.setNipManagerKeuangan(biodataEntity.getNip());
                nama.setNamaManagerKeuangan(biodataEntity.getNamaPegawai());
            }
        }
        //untuk general manager
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,"4");
        if (managerList.size()!=0){
            for (ItPersonilPositionEntity manager : managerList){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",manager.getNip());
                nama.setNipGeneralManager(biodataEntity.getNip());
                nama.setNamaGeneralManager(biodataEntity.getNamaPegawai());
            }
        }

        return nama;
    }

    @Override
    public List<Aging> getAging(String branch, String periode, String masterId,String tipeAging,String reportId) throws GeneralBOException {
        List<Aging> agingList = new ArrayList<>();
        try {
            if (("usaha").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAging(branch,periode,masterId,reportId);
            }else if (("pasien").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAgingPasien(branch,periode,masterId,reportId);

            }
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return agingList;
    }

    @Override
    public String levelKodeRekening(String reportId) throws GeneralBOException {
        String level="";
        try {
            level = laporanAkuntansiDao.getLevelKodeRekening(reportId);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return level;
    }
    @Override
    public List<LaporanAkuntansi> getAll() throws GeneralBOException {
        return null;
    }


    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    
}