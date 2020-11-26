package com.neurix.hris.master.jenisPegawai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.jenisPegawai.bo.JenisPegawaiBo;
import com.neurix.hris.master.jenisPegawai.dao.JenisPegawaiDao;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiEntity;
import com.neurix.hris.master.jenisPegawai.model.ImHrisJenisPegawaiHistoryEntity;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
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
public class JenisPegawaiBoImpl implements JenisPegawaiBo {

    protected static transient Logger logger = Logger.getLogger(JenisPegawaiBoImpl.class);
    private JenisPegawaiDao jenisPegawaiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisPegawaiBoImpl.logger = logger;
    }

    public JenisPegawaiDao getJenisPegawaiDao() {
        return jenisPegawaiDao;
    }

    public void setJenisPegawaiDao(JenisPegawaiDao jenisPegawaiDao) {
        this.jenisPegawaiDao = jenisPegawaiDao;
    }

    @Override
    public void saveDelete(JenisPegawai bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            //Validasi laporan pada mapping laporan

            ImHrisJenisPegawaiEntity imJenisPegawaiEntity = new ImHrisJenisPegawaiEntity();
            try {
                // Get data from database by ID
                imJenisPegawaiEntity = jenisPegawaiDao.getById("jenisPegawaiId", bean.getJenisPegawaiId());
            } catch (HibernateException e) {
                logger.error("[JenisPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data laporan by Kode laporan, please inform to your admin...," + e.getMessage());
            }

            if (imJenisPegawaiEntity != null) {
                // Modify from bean to entity serializable
                imJenisPegawaiEntity.setFlag(bean.getFlag());
                imJenisPegawaiEntity.setAction(bean.getAction());
                imJenisPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imJenisPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jenisPegawaiDao.updateAndSave(imJenisPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisPegawaiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data JenisPegawai, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[JenisPegawaiBoImpl.saveDelete] Error, not found data JenisPegawai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data JenisPegawai with request id, please check again your data ...");

            }
        }
        logger.info("[JenisPegawaiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(JenisPegawai bean) throws GeneralBOException {
        logger.info("[JenisPegawaiBoImpl.saveEdit] start process >>>");

        if (bean != null) {
            List<ImHrisJenisPegawaiEntity> list = new ArrayList<>();
            try {
                list = jenisPegawaiDao.checkData(bean.getJenisPegawaiName());
            } catch (HibernateException e) {
                logger.error("[JenisPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence jenisPegawai id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Payroll sudah ada");
                throw new GeneralBOException("Nama Tipe Payroll sudah ada");
            } else {
                ImHrisJenisPegawaiEntity imJenisPegawaiEntity = null;
                String idHistory = "";
                try {
                    // Get data from database by ID
                    imJenisPegawaiEntity = jenisPegawaiDao.getById("jenisPegawaiId", bean.getJenisPegawaiId());
                    idHistory = jenisPegawaiDao.getNextJenisPegawaiHistoryId();
                } catch (HibernateException e) {
                    logger.error("[JenisPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data JenisPegawai by Kode JenisPegawai, please inform to your admin...," + e.getMessage());
                }
                if (imJenisPegawaiEntity != null) {
                    ImHrisJenisPegawaiHistoryEntity imJenisPegawaiHistoryEntity = new ImHrisJenisPegawaiHistoryEntity();

                    imJenisPegawaiHistoryEntity.setId(idHistory);
                    imJenisPegawaiHistoryEntity.setJenisPegawaiId(imJenisPegawaiEntity.getJenisPegawaiId());
                    imJenisPegawaiHistoryEntity.setJenisPegawaiName(imJenisPegawaiEntity.getJenisPegawaiName());
                    imJenisPegawaiHistoryEntity.setFlag(imJenisPegawaiEntity.getFlag());
                    imJenisPegawaiHistoryEntity.setAction(imJenisPegawaiEntity.getAction());
                    imJenisPegawaiHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imJenisPegawaiHistoryEntity.setLastUpdate(bean.getLastUpdate());
                    imJenisPegawaiHistoryEntity.setCreatedWho(imJenisPegawaiEntity.getLastUpdateWho());
                    imJenisPegawaiHistoryEntity.setCreatedDate(imJenisPegawaiEntity.getLastUpdate());

                    imJenisPegawaiEntity.setJenisPegawaiName(bean.getJenisPegawaiName());
                    imJenisPegawaiEntity.setFlag(bean.getFlag());
                    imJenisPegawaiEntity.setAction(bean.getAction());
                    imJenisPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imJenisPegawaiEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        jenisPegawaiDao.updateAndSave(imJenisPegawaiEntity);
                        jenisPegawaiDao.addAndSaveHistory(imJenisPegawaiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[JenisPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data JenisPegawai, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[JenisPegawaiBoImpl.saveEdit] Error, not found data JenisPegawai with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data JenisPegawai with request id, please check again your data ...");
                }
            }

        }
        logger.info("[JenisPegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public JenisPegawai saveAdd(JenisPegawai bean) throws GeneralBOException {
        logger.info("[JenisPegawaiBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisJenisPegawaiEntity> list = new ArrayList<>();
            try {
                list = jenisPegawaiDao.checkData(bean.getJenisPegawaiName());
            } catch (HibernateException e) {
                logger.error("[JenisPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence jenisPegawai id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Payroll sudah ada");
                throw new GeneralBOException("Nama Tipe Payroll sudah ada");
            } else {
                ImHrisJenisPegawaiEntity laporanEntity = null;

                String jenisPegawaiId;
                try {
                    jenisPegawaiId = jenisPegawaiDao.getNextJenisPegawaiId();

                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
                }


                // creating object entity serializable
                ImHrisJenisPegawaiEntity imJenisPegawaiEntity = new ImHrisJenisPegawaiEntity();

                imJenisPegawaiEntity.setJenisPegawaiId(jenisPegawaiId);
                imJenisPegawaiEntity.setJenisPegawaiName(bean.getJenisPegawaiName());
                imJenisPegawaiEntity.setFlag(bean.getFlag());
                imJenisPegawaiEntity.setAction(bean.getAction());
                imJenisPegawaiEntity.setCreatedWho(bean.getCreatedWho());
                imJenisPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imJenisPegawaiEntity.setCreatedDate(bean.getCreatedDate());
                imJenisPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    jenisPegawaiDao.addAndSave(imJenisPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data JenisPegawai, please info to your admin..." + e.getMessage());
                }
            }


        }

        logger.info("[JenisPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<JenisPegawai> getByCriteria(JenisPegawai searchBean) throws GeneralBOException {
        logger.info("[JenisPegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<JenisPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJenisPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getJenisPegawaiId())) {
                hsCriteria.put("tipe_payroll_id", searchBean.getJenisPegawaiId());
            }
            if (searchBean.getJenisPegawaiName() != null && !"".equalsIgnoreCase(searchBean.getJenisPegawaiName())) {
                hsCriteria.put("tipe_payroll_name", searchBean.getJenisPegawaiName());
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

            List<ImHrisJenisPegawaiEntity> imJenisPegawaiEntity = null;
            try {

                imJenisPegawaiEntity = jenisPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[JenisPegawaiBoImpl.getSearchJenisPegawaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imJenisPegawaiEntity != null) {
                JenisPegawai returnJenisPegawai;
                // Looping from dao to object and save in collection
                for (ImHrisJenisPegawaiEntity jenisPegawaiEntity : imJenisPegawaiEntity) {
                    returnJenisPegawai = new JenisPegawai();
                    returnJenisPegawai.setJenisPegawaiId(jenisPegawaiEntity.getJenisPegawaiId());
                    returnJenisPegawai.setJenisPegawaiName(jenisPegawaiEntity.getJenisPegawaiName());
                    ;

                    returnJenisPegawai.setCreatedWho(jenisPegawaiEntity.getCreatedWho());
                    returnJenisPegawai.setCreatedDate(jenisPegawaiEntity.getCreatedDate());
                    returnJenisPegawai.setLastUpdate(jenisPegawaiEntity.getLastUpdate());
                    returnJenisPegawai.setAction(jenisPegawaiEntity.getAction());
                    returnJenisPegawai.setFlag(jenisPegawaiEntity.getFlag());
                    listOfResult.add(returnJenisPegawai);
                }
            }
        }
        logger.info("[JenisPegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<JenisPegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public JenisPegawai getJenisPegawaiById(String jenisPegawaiId) {
        logger.info("[JenisPegawaiBoImpl.getJenisPegawaiById] start process >>>");
        JenisPegawai jenisPegawai = new JenisPegawai();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("tipe_laporan_id", jenisPegawaiId);
        List<ImHrisJenisPegawaiEntity> jenisPegawaiEntityList;
        try {
            // Get data from database by ID
            jenisPegawaiEntityList = jenisPegawaiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[JenisPegawaiBoImpl.getJenisPegawaiById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data JenisPegawai by Kode JenisPegawai, please inform to your admin...," + e.getMessage());
        }
        for (ImHrisJenisPegawaiEntity jenisPegawaiEntity : jenisPegawaiEntityList) {
            jenisPegawai.setJenisPegawaiId(jenisPegawaiEntity.getJenisPegawaiId());
            jenisPegawai.setJenisPegawaiName(jenisPegawaiEntity.getJenisPegawaiName());
        }
        return jenisPegawai;
    }

}
