package com.neurix.akuntansi.master.tipeLaporan.bo.impl;

import com.neurix.akuntansi.master.reportDetail.dao.ReportDetailDao;
import com.neurix.akuntansi.master.reportDetail.model.ImReportDetailEntity;
import com.neurix.akuntansi.master.tipeLaporan.bo.TipeLaporanBo;
import com.neurix.akuntansi.master.tipeLaporan.dao.TipeLaporanDao;
import com.neurix.akuntansi.master.tipeLaporan.model.ImAkunTipeLaporanEntity;
import com.neurix.akuntansi.master.tipeLaporan.model.TipeLaporan;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.akuntansi.master.tipeLaporan.model.ImAkunTipeLaporanHistoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class TipeLaporanBoImpl implements TipeLaporanBo {

    protected static transient Logger logger = Logger.getLogger(TipeLaporanBoImpl.class);
    private TipeLaporanDao tipeLaporanDao;
    private ReportDetailDao reportDetailDao;

    public ReportDetailDao getReportDetailDao() {
        return reportDetailDao;
    }

    public void setReportDetailDao(ReportDetailDao reportDetailDao) {
        this.reportDetailDao = reportDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeLaporanBoImpl.logger = logger;
    }

    public TipeLaporanDao getTipeLaporanDao() {
        return tipeLaporanDao;
    }

    public void setTipeLaporanDao(TipeLaporanDao tipeLaporanDao) {
        this.tipeLaporanDao = tipeLaporanDao;
    }

    @Override
    public void saveDelete(TipeLaporan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            //Validasi laporan pada mapping laporan
            List<ImReportDetailEntity> imReportDetailEntityList = new ArrayList<>();
            ImAkunTipeLaporanEntity imTipeLaporanEntity = new ImAkunTipeLaporanEntity();
            try {
                imReportDetailEntityList = reportDetailDao.checkDataDelete(bean.getTipeLaporanId());
                // Get data from database by ID
                imTipeLaporanEntity = tipeLaporanDao.getById("tipeLaporanId", bean.getTipeLaporanId());
            } catch (HibernateException e) {
                logger.error("[TipeLaporanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data laporan by Kode laporan, please inform to your admin...," + e.getMessage());
            }

            if (imReportDetailEntityList.size() > 0) {
                logger.error("Tipe Laporan sudah ada tidak dapat dihapus");
                throw new GeneralBOException("Tipe Laporan sudah ada tidak dapat dihapus");
            } else {

                if (imTipeLaporanEntity != null) {
                    // Modify from bean to entity serializable
                    imTipeLaporanEntity.setFlag(bean.getFlag());
                    imTipeLaporanEntity.setAction(bean.getAction());
                    imTipeLaporanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imTipeLaporanEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        tipeLaporanDao.updateAndSave(imTipeLaporanEntity);
                    } catch (HibernateException e) {
                        logger.error("[TipeLaporanBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data TipeLaporan, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[TipeLaporanBoImpl.saveDelete] Error, not found data TipeLaporan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data TipeLaporan with request id, please check again your data ...");

                }
            }
        }
        logger.info("[TipeLaporanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipeLaporan bean) throws GeneralBOException {
        logger.info("[TipeLaporanBoImpl.saveEdit] start process >>>");

        if (bean != null) {
            List<ImAkunTipeLaporanEntity> list = new ArrayList<>();
            try {
                list = tipeLaporanDao.checkData(bean.getTipeLaporanName());
            } catch (HibernateException e) {
                logger.error("[TipeLaporanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipeLaporan id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Laporan sudah ada");
                throw new GeneralBOException("Nama Tipe Laporan sudah ada");
            } else {
                ImAkunTipeLaporanEntity imTipeLaporanEntity = null;
                String idHistory = "";
                try {
                    // Get data from database by ID
                    imTipeLaporanEntity = tipeLaporanDao.getById("tipeLaporanId", bean.getTipeLaporanId());
                    idHistory = tipeLaporanDao.getNextTipeLaporanHistoryId();
                } catch (HibernateException e) {
                    logger.error("[TipeLaporanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data TipeLaporan by Kode TipeLaporan, please inform to your admin...," + e.getMessage());
                }
                if (imTipeLaporanEntity != null) {
                    ImAkunTipeLaporanHistoryEntity imTipeLaporanHistoryEntity = new ImAkunTipeLaporanHistoryEntity();

                    imTipeLaporanHistoryEntity.setId(idHistory);
                    imTipeLaporanHistoryEntity.setTipeLaporanId(imTipeLaporanEntity.getTipeLaporanId());
                    imTipeLaporanHistoryEntity.setTipeLaporanName(imTipeLaporanEntity.getTipeLaporanName());
                    imTipeLaporanHistoryEntity.setFlag(imTipeLaporanEntity.getFlag());
                    imTipeLaporanHistoryEntity.setAction(imTipeLaporanEntity.getAction());
                    imTipeLaporanHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imTipeLaporanHistoryEntity.setLastUpdate(bean.getLastUpdate());
                    imTipeLaporanHistoryEntity.setCreatedWho(imTipeLaporanEntity.getLastUpdateWho());
                    imTipeLaporanHistoryEntity.setCreatedDate(imTipeLaporanEntity.getLastUpdate());
                    
                    imTipeLaporanEntity.setTipeLaporanName(bean.getTipeLaporanName());
                    imTipeLaporanEntity.setFlag(bean.getFlag());
                    imTipeLaporanEntity.setAction(bean.getAction());
                    imTipeLaporanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imTipeLaporanEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        tipeLaporanDao.updateAndSave(imTipeLaporanEntity);
                        tipeLaporanDao.addAndSaveHistory(imTipeLaporanHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[TipeLaporanBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data TipeLaporan, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[TipeLaporanBoImpl.saveEdit] Error, not found data TipeLaporan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data TipeLaporan with request id, please check again your data ...");
                }
            }

        }
        logger.info("[TipeLaporanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipeLaporan saveAdd(TipeLaporan bean) throws GeneralBOException {
        logger.info("[TipeLaporanBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImAkunTipeLaporanEntity> list = new ArrayList<>();
            try {
                list = tipeLaporanDao.checkData(bean.getTipeLaporanName());
            } catch (HibernateException e) {
                logger.error("[TipeLaporanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipeLaporan id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Laporan sudah ada");
                throw new GeneralBOException("Nama Tipe Laporan sudah ada");
            } else {
                ImAkunTipeLaporanEntity laporanEntity = null;

                String tipeLaporanId;
                try {
                    tipeLaporanId = tipeLaporanDao.getNextTipeLaporanId();

                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
                }


                // creating object entity serializable
                ImAkunTipeLaporanEntity imTipeLaporanEntity = new ImAkunTipeLaporanEntity();

                imTipeLaporanEntity.setTipeLaporanId(tipeLaporanId);
                imTipeLaporanEntity.setTipeLaporanName(bean.getTipeLaporanName());
                imTipeLaporanEntity.setFlag(bean.getFlag());
                imTipeLaporanEntity.setAction(bean.getAction());
                imTipeLaporanEntity.setCreatedWho(bean.getCreatedWho());
                imTipeLaporanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipeLaporanEntity.setCreatedDate(bean.getCreatedDate());
                imTipeLaporanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    tipeLaporanDao.addAndSave(imTipeLaporanEntity);
                } catch (HibernateException e) {
                    logger.error("[TipeLaporanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data TipeLaporan, please info to your admin..." + e.getMessage());
                }
            }


        }

        logger.info("[TipeLaporanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipeLaporan> getByCriteria(TipeLaporan searchBean) throws GeneralBOException {
        logger.info("[TipeLaporanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipeLaporan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipeLaporanId() != null && !"".equalsIgnoreCase(searchBean.getTipeLaporanId())) {
                hsCriteria.put("tipe_laporan_id", searchBean.getTipeLaporanId());
            }
            if (searchBean.getTipeLaporanName() != null && !"".equalsIgnoreCase(searchBean.getTipeLaporanName())) {
                hsCriteria.put("tipe_laporan_name", searchBean.getTipeLaporanName());
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

            List<ImAkunTipeLaporanEntity> imTipeLaporanEntity = null;
            try {

                imTipeLaporanEntity = tipeLaporanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipeLaporanBoImpl.getSearchTipeLaporanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imTipeLaporanEntity != null) {
                TipeLaporan returnTipeLaporan;
                // Looping from dao to object and save in collection
                for (ImAkunTipeLaporanEntity tipeLaporanEntity : imTipeLaporanEntity) {
                    returnTipeLaporan = new TipeLaporan();
                    returnTipeLaporan.setTipeLaporanId(tipeLaporanEntity.getTipeLaporanId());
                    returnTipeLaporan.setTipeLaporanName(tipeLaporanEntity.getTipeLaporanName());
                    ;

                    returnTipeLaporan.setCreatedWho(tipeLaporanEntity.getCreatedWho());
                    returnTipeLaporan.setCreatedDate(tipeLaporanEntity.getCreatedDate());
                    returnTipeLaporan.setLastUpdate(tipeLaporanEntity.getLastUpdate());
                    returnTipeLaporan.setAction(tipeLaporanEntity.getAction());
                    returnTipeLaporan.setFlag(tipeLaporanEntity.getFlag());
                    listOfResult.add(returnTipeLaporan);
                }
            }
        }
        logger.info("[TipeLaporanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipeLaporan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public TipeLaporan getTipeLaporanById(String tipeLaporanId) {
        logger.info("[TipeLaporanBoImpl.getTipeLaporanById] start process >>>");
        TipeLaporan tipeLaporan = new TipeLaporan();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("tipe_laporan_id", tipeLaporanId);
        List<ImAkunTipeLaporanEntity> tipeLaporanEntityList;
        try {
            // Get data from database by ID
            tipeLaporanEntityList = tipeLaporanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TipeLaporanBoImpl.getTipeLaporanById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data TipeLaporan by Kode TipeLaporan, please inform to your admin...," + e.getMessage());
        }
        for (ImAkunTipeLaporanEntity tipeLaporanEntity : tipeLaporanEntityList) {
            tipeLaporan.setTipeLaporanId(tipeLaporanEntity.getTipeLaporanId());
            tipeLaporan.setTipeLaporanName(tipeLaporanEntity.getTipeLaporanName());
        }
        return tipeLaporan;
    }

}
