package com.neurix.hris.transaksi.laporan.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.transaksi.laporan.bo.LaporanBo;
import com.neurix.hris.transaksi.laporan.dao.LaporanDao;
import com.neurix.hris.transaksi.laporan.model.ItLaporanEntity;
import com.neurix.hris.transaksi.laporan.model.Laporan;
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LaporanBoImpl implements LaporanBo {

    protected static transient Logger logger = Logger.getLogger(LaporanBoImpl.class);
    private LaporanDao laporanDao;
    private StrukturJabatanDao strukturJabatanDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private HistoryJabatanPegawaiDao historyJabatanPegawaiDaox;

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public HistoryJabatanPegawaiDao getHistoryJabatanPegawaiDaox() {
        return historyJabatanPegawaiDaox;
    }

    public void setHistoryJabatanPegawaiDaox(HistoryJabatanPegawaiDao historyJabatanPegawaiDaox) {
        this.historyJabatanPegawaiDaox = historyJabatanPegawaiDaox;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }


    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanBoImpl.logger = logger;
    }

    public LaporanDao getLaporanDao() {
        return laporanDao;
    }


    public void setLaporanDao(LaporanDao laporanDao) {
        this.laporanDao = laporanDao;
    }


    @Override
    public void saveDelete(Laporan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Laporan bean) throws GeneralBOException {

    }

    @Override
    public Laporan saveAdd(Laporan bean) throws GeneralBOException {
        return null;
    }
    
    @Override
    public List<Laporan> getByCriteria(Laporan searchBean) throws GeneralBOException {
        List<Laporan> listOfResult = new ArrayList();
        logger.info("[LaporanBoImpl.getByCriteria] start process >>>");

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


            List<ItLaporanEntity> imLaporanEntity = null;
            try {
                imLaporanEntity = laporanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LaporanBoImpl.getSearchLaporanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imLaporanEntity != null) {
                Laporan returnLaporan;
                // Looping from dao to object and save in collection
                for(ItLaporanEntity itLaporanEntity: imLaporanEntity){
                    returnLaporan = new Laporan();

                    returnLaporan.setLaporanId(itLaporanEntity.getLaporanId());
                    returnLaporan.setLaporanName(itLaporanEntity.getLaporanName());
                    returnLaporan.setUrl(itLaporanEntity.getUrl());
                    returnLaporan.setCreatedWho(itLaporanEntity.getCreatedWho());
                    returnLaporan.setCreatedDate(itLaporanEntity.getCreatedDate());
                    returnLaporan.setLastUpdate(itLaporanEntity.getLastUpdate());
                    returnLaporan.setAction(itLaporanEntity.getAction());
                    returnLaporan.setFlag(itLaporanEntity.getFlag());
                    listOfResult.add(returnLaporan);
                }
            }
        }
        logger.info("[LaporanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    
    @Override
    public List<Laporan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    
}