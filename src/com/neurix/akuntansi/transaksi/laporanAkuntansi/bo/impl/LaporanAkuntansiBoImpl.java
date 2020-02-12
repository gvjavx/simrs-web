package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.impl;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.dao.LaporanAkuntansiDao;
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

    }

    @Override
    public void saveEdit(LaporanAkuntansi bean) throws GeneralBOException {

    }

    @Override
    public LaporanAkuntansi saveAdd(LaporanAkuntansi bean) throws GeneralBOException {
        return null;
    }
    
    @Override
    public List<LaporanAkuntansi> getByCriteria(LaporanAkuntansi searchBean) throws GeneralBOException {
        List<LaporanAkuntansi> listOfResult = new ArrayList();
        logger.info("[LaporanAkuntansiBoImpl.getByCriteria] start process >>>");

        if (searchBean != null) {
            Map hsCriteria = new HashMap();


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
    public List<LaporanAkuntansi> getAll() throws GeneralBOException {
        return null;
    }


    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    
}