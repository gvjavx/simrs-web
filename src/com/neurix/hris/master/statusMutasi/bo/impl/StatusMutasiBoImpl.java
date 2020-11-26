package com.neurix.hris.master.statusMutasi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusMutasi.bo.StatusMutasiBo;
import com.neurix.hris.master.statusMutasi.dao.StatusMutasiDao;
import com.neurix.hris.master.statusMutasi.model.ImHrisStatusMutasiEntity;
import com.neurix.hris.master.statusMutasi.model.ImHrisStatusMutasiHistoryEntity;
import com.neurix.hris.master.statusMutasi.model.StatusMutasi;
import com.neurix.hris.transaksi.mutasi.dao.MutasiDao;
import com.neurix.hris.transaksi.mutasi.model.ItMutasiEntity;
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
public class StatusMutasiBoImpl implements StatusMutasiBo {

    protected static transient Logger logger = Logger.getLogger(StatusMutasiBoImpl.class);
    private StatusMutasiDao statusMutasiDao;
    private MutasiDao mutasiDao;

    public MutasiDao getMutasiDao() {
        return mutasiDao;
    }

    public void setMutasiDao(MutasiDao mutasiDao) {
        this.mutasiDao = mutasiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusMutasiBoImpl.logger = logger;
    }

    public StatusMutasiDao getStatusMutasiDao() {
        return statusMutasiDao;
    }

    public void setStatusMutasiDao(StatusMutasiDao statusMutasiDao) {
        this.statusMutasiDao = statusMutasiDao;
    }

    @Override
    public void saveDelete(StatusMutasi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            //Validasi laporan pada mapping laporan
            List<ItMutasiEntity> mutasiPegawaiEntityList = new ArrayList<>();
            ImHrisStatusMutasiEntity imStatusMutasiEntity = new ImHrisStatusMutasiEntity();
            try {
                mutasiPegawaiEntityList = mutasiDao.checkDataDelete(bean.getStatusMutasiId());
                // Get data from database by ID
                imStatusMutasiEntity = statusMutasiDao.getById("statusMutasiId", bean.getStatusMutasiId());
            } catch (HibernateException e) {
                logger.error("[StatusMutasiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data laporan by Kode laporan, please inform to your admin...," + e.getMessage());
            }
            if (mutasiPegawaiEntityList.size() > 0) {
                logger.error("Nama Status Mutasi sudah ada tidak dapat dihapus");
                throw new GeneralBOException("Nama Status Mutasi sudah ada tidak dapat dihapus");
            } else {

                if (imStatusMutasiEntity != null) {
                    // Modify from bean to entity serializable
                    imStatusMutasiEntity.setFlag(bean.getFlag());
                    imStatusMutasiEntity.setAction(bean.getAction());
                    imStatusMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStatusMutasiEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        statusMutasiDao.updateAndSave(imStatusMutasiEntity);
                    } catch (HibernateException e) {
                        logger.error("[StatusMutasiBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StatusMutasi, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[StatusMutasiBoImpl.saveDelete] Error, not found data StatusMutasi with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data StatusMutasi with request id, please check again your data ...");

                }
            }
        }
        logger.info("[StatusMutasiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(StatusMutasi bean) throws GeneralBOException {
        logger.info("[StatusMutasiBoImpl.saveEdit] start process >>>");

        if (bean != null) {
            List<ImHrisStatusMutasiEntity> list = new ArrayList<>();
            try {
                list = statusMutasiDao.checkData(bean.getStatusMutasiName());
            } catch (HibernateException e) {
                logger.error("[StatusMutasiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence statusMutasi id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Status Mutasi sudah ada");
                throw new GeneralBOException("Nama Status Mutasi sudah ada");
            } else {
                ImHrisStatusMutasiEntity imStatusMutasiEntity = null;
                String idHistory = "";
                try {
                    // Get data from database by ID
                    imStatusMutasiEntity = statusMutasiDao.getById("statusMutasiId", bean.getStatusMutasiId());
                    idHistory = statusMutasiDao.getNextStatusMutasiHistoryId();
                } catch (HibernateException e) {
                    logger.error("[StatusMutasiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data StatusMutasi by Kode StatusMutasi, please inform to your admin...," + e.getMessage());
                }
                if (imStatusMutasiEntity != null) {
                    ImHrisStatusMutasiHistoryEntity imStatusMutasiHistoryEntity = new ImHrisStatusMutasiHistoryEntity();

                    imStatusMutasiHistoryEntity.setId(idHistory);
                    imStatusMutasiHistoryEntity.setStatusMutasiId(imStatusMutasiEntity.getStatusMutasiId());
                    imStatusMutasiHistoryEntity.setStatusMutasiName(imStatusMutasiEntity.getStatusMutasiName());
                    imStatusMutasiHistoryEntity.setFlag(imStatusMutasiEntity.getFlag());
                    imStatusMutasiHistoryEntity.setAction(imStatusMutasiEntity.getAction());
                    imStatusMutasiHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStatusMutasiHistoryEntity.setLastUpdate(bean.getLastUpdate());
                    imStatusMutasiHistoryEntity.setCreatedWho(imStatusMutasiEntity.getLastUpdateWho());
                    imStatusMutasiHistoryEntity.setCreatedDate(imStatusMutasiEntity.getLastUpdate());

                    imStatusMutasiEntity.setStatusMutasiName(bean.getStatusMutasiName());
                    imStatusMutasiEntity.setFlag(bean.getFlag());
                    imStatusMutasiEntity.setAction(bean.getAction());
                    imStatusMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStatusMutasiEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        statusMutasiDao.updateAndSave(imStatusMutasiEntity);
                        statusMutasiDao.addAndSaveHistory(imStatusMutasiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[StatusMutasiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StatusMutasi, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[StatusMutasiBoImpl.saveEdit] Error, not found data StatusMutasi with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data StatusMutasi with request id, please check again your data ...");
                }
            }

        }
        logger.info("[StatusMutasiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public StatusMutasi saveAdd(StatusMutasi bean) throws GeneralBOException {
        logger.info("[StatusMutasiBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisStatusMutasiEntity> list = new ArrayList<>();
            try {
                list = statusMutasiDao.checkData(bean.getStatusMutasiName());
            } catch (HibernateException e) {
                logger.error("[StatusMutasiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence statusMutasi id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Status Mutasi sudah ada");
                throw new GeneralBOException("Nama Status Mutasi sudah ada");
            } else {
                ImHrisStatusMutasiEntity laporanEntity = null;

                String statusMutasiId;
                try {
                    statusMutasiId = statusMutasiDao.getNextStatusMutasiId();

                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
                }


                // creating object entity serializable
                ImHrisStatusMutasiEntity imStatusMutasiEntity = new ImHrisStatusMutasiEntity();

                imStatusMutasiEntity.setStatusMutasiId(statusMutasiId);
                imStatusMutasiEntity.setStatusMutasiName(bean.getStatusMutasiName());
                imStatusMutasiEntity.setFlagGajiProporsional(bean.getFlagGajiProporsional());
                imStatusMutasiEntity.setFlag(bean.getFlag());
                imStatusMutasiEntity.setAction(bean.getAction());
                imStatusMutasiEntity.setCreatedWho(bean.getCreatedWho());
                imStatusMutasiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStatusMutasiEntity.setCreatedDate(bean.getCreatedDate());
                imStatusMutasiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    statusMutasiDao.addAndSave(imStatusMutasiEntity);
                } catch (HibernateException e) {
                    logger.error("[StatusMutasiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data StatusMutasi, please info to your admin..." + e.getMessage());
                }
            }


        }

        logger.info("[StatusMutasiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<StatusMutasi> getByCriteria(StatusMutasi searchBean) throws GeneralBOException {
        logger.info("[StatusMutasiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StatusMutasi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getStatusMutasiId() != null && !"".equalsIgnoreCase(searchBean.getStatusMutasiId())) {
                hsCriteria.put("status_mutasi_id", searchBean.getStatusMutasiId());
            }
            if (searchBean.getStatusMutasiName() != null && !"".equalsIgnoreCase(searchBean.getStatusMutasiName())) {
                hsCriteria.put("status_mutasi_name", searchBean.getStatusMutasiName());
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

            List<ImHrisStatusMutasiEntity> imStatusMutasiEntity = null;
            try {

                imStatusMutasiEntity = statusMutasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StatusMutasiBoImpl.getSearchStatusMutasiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imStatusMutasiEntity != null) {
                StatusMutasi returnStatusMutasi;
                // Looping from dao to object and save in collection
                for (ImHrisStatusMutasiEntity statusMutasiEntity : imStatusMutasiEntity) {
                    returnStatusMutasi = new StatusMutasi();
                    returnStatusMutasi.setStatusMutasiId(statusMutasiEntity.getStatusMutasiId());
                    returnStatusMutasi.setStatusMutasiName(statusMutasiEntity.getStatusMutasiName());
                    returnStatusMutasi.setFlagGajiProporsional(statusMutasiEntity.getFlagGajiProporsional());

                    returnStatusMutasi.setCreatedWho(statusMutasiEntity.getCreatedWho());
                    returnStatusMutasi.setCreatedDate(statusMutasiEntity.getCreatedDate());
                    returnStatusMutasi.setLastUpdate(statusMutasiEntity.getLastUpdate());
                    returnStatusMutasi.setAction(statusMutasiEntity.getAction());
                    returnStatusMutasi.setFlag(statusMutasiEntity.getFlag());
                    listOfResult.add(returnStatusMutasi);
                }
            }
        }
        logger.info("[StatusMutasiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<StatusMutasi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public StatusMutasi getStatusMutasiById(String statusMutasiId) {
        logger.info("[StatusMutasiBoImpl.getStatusMutasiById] start process >>>");
        StatusMutasi statusMutasi = new StatusMutasi();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("status_mutasi_id", statusMutasiId);
        List<ImHrisStatusMutasiEntity> statusMutasiEntityList;
        try {
            // Get data from database by ID
            statusMutasiEntityList = statusMutasiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[StatusMutasiBoImpl.getStatusMutasiById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data StatusMutasi by Kode StatusMutasi, please inform to your admin...," + e.getMessage());
        }
        for (ImHrisStatusMutasiEntity statusMutasiEntity : statusMutasiEntityList) {
            statusMutasi.setStatusMutasiId(statusMutasiEntity.getStatusMutasiId());
            statusMutasi.setStatusMutasiName(statusMutasiEntity.getStatusMutasiName());
        }
        return statusMutasi;
    }

}
