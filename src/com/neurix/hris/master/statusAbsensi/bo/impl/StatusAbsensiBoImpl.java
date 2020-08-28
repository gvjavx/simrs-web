package com.neurix.hris.master.statusAbsensi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusAbsensi.bo.StatusAbsensiBo;
import com.neurix.hris.master.statusAbsensi.dao.StatusAbsensiDao;
import com.neurix.hris.master.statusAbsensi.model.ImHrisStatusAbsensiEntity;
import com.neurix.hris.master.statusAbsensi.model.StatusAbsensi;
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
public class StatusAbsensiBoImpl implements StatusAbsensiBo {

    protected static transient Logger logger = Logger.getLogger(StatusAbsensiBoImpl.class);
    private StatusAbsensiDao statusAbsensiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusAbsensiBoImpl.logger = logger;
    }

    public StatusAbsensiDao getStatusAbsensiDao() {
        return statusAbsensiDao;
    }

    public void setStatusAbsensiDao(StatusAbsensiDao statusAbsensiDao) {
        this.statusAbsensiDao = statusAbsensiDao;
    }

    @Override
    public void saveDelete(StatusAbsensi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            //Validasi laporan pada mapping laporan

            ImHrisStatusAbsensiEntity imStatusAbsensiEntity = new ImHrisStatusAbsensiEntity();
            try {
                // Get data from database by ID
                imStatusAbsensiEntity = statusAbsensiDao.getById("statusAbsensiId", bean.getStatusAbsensiId());
            } catch (HibernateException e) {
                logger.error("[StatusAbsensiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data laporan by Kode laporan, please inform to your admin...," + e.getMessage());
            }

            if (imStatusAbsensiEntity != null) {
                // Modify from bean to entity serializable
                imStatusAbsensiEntity.setFlag(bean.getFlag());
                imStatusAbsensiEntity.setAction(bean.getAction());
                imStatusAbsensiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStatusAbsensiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    statusAbsensiDao.updateAndSave(imStatusAbsensiEntity);
                } catch (HibernateException e) {
                    logger.error("[StatusAbsensiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StatusAbsensi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[StatusAbsensiBoImpl.saveDelete] Error, not found data StatusAbsensi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StatusAbsensi with request id, please check again your data ...");

            }
        }
        logger.info("[StatusAbsensiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(StatusAbsensi bean) throws GeneralBOException {
        logger.info("[StatusAbsensiBoImpl.saveEdit] start process >>>");

        if (bean != null) {
            List<ImHrisStatusAbsensiEntity> list = new ArrayList<>();
            try {
                list = statusAbsensiDao.checkData(bean.getStatusAbsensiName());
            } catch (HibernateException e) {
                logger.error("[StatusAbsensiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence statusAbsensi id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Status Absensi sudah ada");
                throw new GeneralBOException("Nama Status Absensi sudah ada");
            } else {
                ImHrisStatusAbsensiEntity imStatusAbsensiEntity = null;
                try {
                    // Get data from database by ID
                    imStatusAbsensiEntity = statusAbsensiDao.getById("statusAbsensiId", bean.getStatusAbsensiId());
                } catch (HibernateException e) {
                    logger.error("[StatusAbsensiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data StatusAbsensi by Kode StatusAbsensi, please inform to your admin...," + e.getMessage());
                }
                if (imStatusAbsensiEntity != null) {
                    imStatusAbsensiEntity.setStatusAbsensiName(bean.getStatusAbsensiName());
                    imStatusAbsensiEntity.setFlag(bean.getFlag());
                    imStatusAbsensiEntity.setAction(bean.getAction());
                    imStatusAbsensiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStatusAbsensiEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        statusAbsensiDao.updateAndSave(imStatusAbsensiEntity);
                    } catch (HibernateException e) {
                        logger.error("[StatusAbsensiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StatusAbsensi, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[StatusAbsensiBoImpl.saveEdit] Error, not found data StatusAbsensi with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data StatusAbsensi with request id, please check again your data ...");
                }
            }

        }
        logger.info("[StatusAbsensiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public StatusAbsensi saveAdd(StatusAbsensi bean) throws GeneralBOException {
        logger.info("[StatusAbsensiBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisStatusAbsensiEntity> list = new ArrayList<>();
            try {
                list = statusAbsensiDao.checkData(bean.getStatusAbsensiName());
            } catch (HibernateException e) {
                logger.error("[StatusAbsensiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence statusAbsensi id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Status Absensi sudah ada");
                throw new GeneralBOException("Nama Status Absensi sudah ada");
            } else {
                ImHrisStatusAbsensiEntity laporanEntity = null;

                String statusAbsensiId;
                try {
                    statusAbsensiId = statusAbsensiDao.getNextStatusAbsensiId();

                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
                }


                // creating object entity serializable
                ImHrisStatusAbsensiEntity imStatusAbsensiEntity = new ImHrisStatusAbsensiEntity();

                imStatusAbsensiEntity.setStatusAbsensiId(statusAbsensiId);
                imStatusAbsensiEntity.setStatusAbsensiName(bean.getStatusAbsensiName());
                imStatusAbsensiEntity.setFlag(bean.getFlag());
                imStatusAbsensiEntity.setAction(bean.getAction());
                imStatusAbsensiEntity.setCreatedWho(bean.getCreatedWho());
                imStatusAbsensiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStatusAbsensiEntity.setCreatedDate(bean.getCreatedDate());
                imStatusAbsensiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    statusAbsensiDao.addAndSave(imStatusAbsensiEntity);
                } catch (HibernateException e) {
                    logger.error("[StatusAbsensiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data StatusAbsensi, please info to your admin..." + e.getMessage());
                }
            }


        }

        logger.info("[StatusAbsensiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<StatusAbsensi> getByCriteria(StatusAbsensi searchBean) throws GeneralBOException {
        logger.info("[StatusAbsensiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StatusAbsensi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getStatusAbsensiId() != null && !"".equalsIgnoreCase(searchBean.getStatusAbsensiId())) {
                hsCriteria.put("status_absensi_id", searchBean.getStatusAbsensiId());
            }
            if (searchBean.getStatusAbsensiName() != null && !"".equalsIgnoreCase(searchBean.getStatusAbsensiName())) {
                hsCriteria.put("status_absensi_name", searchBean.getStatusAbsensiName());
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

            List<ImHrisStatusAbsensiEntity> imStatusAbsensiEntity = null;
            try {

                imStatusAbsensiEntity = statusAbsensiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StatusAbsensiBoImpl.getSearchStatusAbsensiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imStatusAbsensiEntity != null) {
                StatusAbsensi returnStatusAbsensi;
                // Looping from dao to object and save in collection
                for (ImHrisStatusAbsensiEntity statusAbsensiEntity : imStatusAbsensiEntity) {
                    returnStatusAbsensi = new StatusAbsensi();
                    returnStatusAbsensi.setStatusAbsensiId(statusAbsensiEntity.getStatusAbsensiId());
                    returnStatusAbsensi.setStatusAbsensiName(statusAbsensiEntity.getStatusAbsensiName());
                    ;

                    returnStatusAbsensi.setCreatedWho(statusAbsensiEntity.getCreatedWho());
                    returnStatusAbsensi.setCreatedDate(statusAbsensiEntity.getCreatedDate());
                    returnStatusAbsensi.setLastUpdate(statusAbsensiEntity.getLastUpdate());
                    returnStatusAbsensi.setAction(statusAbsensiEntity.getAction());
                    returnStatusAbsensi.setFlag(statusAbsensiEntity.getFlag());
                    listOfResult.add(returnStatusAbsensi);
                }
            }
        }
        logger.info("[StatusAbsensiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<StatusAbsensi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public StatusAbsensi getStatusAbsensiById(String statusAbsensiId) {
        logger.info("[StatusAbsensiBoImpl.getStatusAbsensiById] start process >>>");
        StatusAbsensi statusAbsensi = new StatusAbsensi();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("status_absensi_id", statusAbsensiId);
        List<ImHrisStatusAbsensiEntity> statusAbsensiEntityList;
        try {
            // Get data from database by ID
            statusAbsensiEntityList = statusAbsensiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[StatusAbsensiBoImpl.getStatusAbsensiById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data StatusAbsensi by Kode StatusAbsensi, please inform to your admin...," + e.getMessage());
        }
        for (ImHrisStatusAbsensiEntity statusAbsensiEntity : statusAbsensiEntityList) {
            statusAbsensi.setStatusAbsensiId(statusAbsensiEntity.getStatusAbsensiId());
            statusAbsensi.setStatusAbsensiName(statusAbsensiEntity.getStatusAbsensiName());
        }
        return statusAbsensi;
    }

}
