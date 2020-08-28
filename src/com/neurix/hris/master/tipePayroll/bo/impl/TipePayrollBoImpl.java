package com.neurix.hris.master.tipePayroll.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipePayroll.bo.TipePayrollBo;
import com.neurix.hris.master.tipePayroll.dao.TipePayrollDao;
import com.neurix.hris.master.tipePayroll.model.ImHrisTipePayrollEntity;
import com.neurix.hris.master.tipePayroll.model.TipePayroll;
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
public class TipePayrollBoImpl implements TipePayrollBo {

    protected static transient Logger logger = Logger.getLogger(TipePayrollBoImpl.class);
    private TipePayrollDao tipePayrollDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipePayrollBoImpl.logger = logger;
    }

    public TipePayrollDao getTipePayrollDao() {
        return tipePayrollDao;
    }

    public void setTipePayrollDao(TipePayrollDao tipePayrollDao) {
        this.tipePayrollDao = tipePayrollDao;
    }

    @Override
    public void saveDelete(TipePayroll bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            //Validasi laporan pada mapping laporan

            ImHrisTipePayrollEntity imTipePayrollEntity = new ImHrisTipePayrollEntity();
            try {
                // Get data from database by ID
                imTipePayrollEntity = tipePayrollDao.getById("tipePayrollId", bean.getTipePayrollId());
            } catch (HibernateException e) {
                logger.error("[TipePayrollBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data laporan by Kode laporan, please inform to your admin...," + e.getMessage());
            }

            if (imTipePayrollEntity != null) {
                // Modify from bean to entity serializable
                imTipePayrollEntity.setFlag(bean.getFlag());
                imTipePayrollEntity.setAction(bean.getAction());
                imTipePayrollEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipePayrollEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    tipePayrollDao.updateAndSave(imTipePayrollEntity);
                } catch (HibernateException e) {
                    logger.error("[TipePayrollBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TipePayroll, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TipePayrollBoImpl.saveDelete] Error, not found data TipePayroll with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TipePayroll with request id, please check again your data ...");

            }
        }
        logger.info("[TipePayrollBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TipePayroll bean) throws GeneralBOException {
        logger.info("[TipePayrollBoImpl.saveEdit] start process >>>");

        if (bean != null) {
            List<ImHrisTipePayrollEntity> list = new ArrayList<>();
            try {
                list = tipePayrollDao.checkData(bean.getTipePayrollName());
            } catch (HibernateException e) {
                logger.error("[TipePayrollBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipePayroll id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Payroll sudah ada");
                throw new GeneralBOException("Nama Tipe Payroll sudah ada");
            } else {
                ImHrisTipePayrollEntity imTipePayrollEntity = null;
                try {
                    // Get data from database by ID
                    imTipePayrollEntity = tipePayrollDao.getById("tipePayrollId", bean.getTipePayrollId());
                } catch (HibernateException e) {
                    logger.error("[TipePayrollBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data TipePayroll by Kode TipePayroll, please inform to your admin...," + e.getMessage());
                }
                if (imTipePayrollEntity != null) {
                    imTipePayrollEntity.setTipePayrollName(bean.getTipePayrollName());
                    imTipePayrollEntity.setFlag(bean.getFlag());
                    imTipePayrollEntity.setAction(bean.getAction());
                    imTipePayrollEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imTipePayrollEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        tipePayrollDao.updateAndSave(imTipePayrollEntity);
                    } catch (HibernateException e) {
                        logger.error("[TipePayrollBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data TipePayroll, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[TipePayrollBoImpl.saveEdit] Error, not found data TipePayroll with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data TipePayroll with request id, please check again your data ...");
                }
            }

        }
        logger.info("[TipePayrollBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipePayroll saveAdd(TipePayroll bean) throws GeneralBOException {
        logger.info("[TipePayrollBoImpl.saveAdd] start process >>>");
        if (bean != null) {
            List<ImHrisTipePayrollEntity> list = new ArrayList<>();
            try {
                list = tipePayrollDao.checkData(bean.getTipePayrollName());
            } catch (HibernateException e) {
                logger.error("[TipePayrollBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence tipePayroll id, please info to your admin..." + e.getMessage());
            }
            if (list.size() > 0) {
                logger.error("Nama Tipe Payroll sudah ada");
                throw new GeneralBOException("Nama Tipe Payroll sudah ada");
            } else {
                ImHrisTipePayrollEntity laporanEntity = null;

                String tipePayrollId;
                try {
                    tipePayrollId = tipePayrollDao.getNextTipePayrollId();

                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Error , please info to your admin..." + e.getMessage());
                }


                // creating object entity serializable
                ImHrisTipePayrollEntity imTipePayrollEntity = new ImHrisTipePayrollEntity();

                imTipePayrollEntity.setTipePayrollId(tipePayrollId);
                imTipePayrollEntity.setTipePayrollName(bean.getTipePayrollName());
                imTipePayrollEntity.setFlag(bean.getFlag());
                imTipePayrollEntity.setAction(bean.getAction());
                imTipePayrollEntity.setCreatedWho(bean.getCreatedWho());
                imTipePayrollEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTipePayrollEntity.setCreatedDate(bean.getCreatedDate());
                imTipePayrollEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    tipePayrollDao.addAndSave(imTipePayrollEntity);
                } catch (HibernateException e) {
                    logger.error("[TipePayrollBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data TipePayroll, please info to your admin..." + e.getMessage());
                }
            }


        }

        logger.info("[TipePayrollBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipePayroll> getByCriteria(TipePayroll searchBean) throws GeneralBOException {
        logger.info("[TipePayrollBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipePayroll> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipePayrollId() != null && !"".equalsIgnoreCase(searchBean.getTipePayrollId())) {
                hsCriteria.put("tipe_payroll_id", searchBean.getTipePayrollId());
            }
            if (searchBean.getTipePayrollName() != null && !"".equalsIgnoreCase(searchBean.getTipePayrollName())) {
                hsCriteria.put("tipe_payroll_name", searchBean.getTipePayrollName());
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

            List<ImHrisTipePayrollEntity> imTipePayrollEntity = null;
            try {

                imTipePayrollEntity = tipePayrollDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipePayrollBoImpl.getSearchTipePayrollByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (imTipePayrollEntity != null) {
                TipePayroll returnTipePayroll;
                // Looping from dao to object and save in collection
                for (ImHrisTipePayrollEntity tipePayrollEntity : imTipePayrollEntity) {
                    returnTipePayroll = new TipePayroll();
                    returnTipePayroll.setTipePayrollId(tipePayrollEntity.getTipePayrollId());
                    returnTipePayroll.setTipePayrollName(tipePayrollEntity.getTipePayrollName());
                    ;

                    returnTipePayroll.setCreatedWho(tipePayrollEntity.getCreatedWho());
                    returnTipePayroll.setCreatedDate(tipePayrollEntity.getCreatedDate());
                    returnTipePayroll.setLastUpdate(tipePayrollEntity.getLastUpdate());
                    returnTipePayroll.setAction(tipePayrollEntity.getAction());
                    returnTipePayroll.setFlag(tipePayrollEntity.getFlag());
                    listOfResult.add(returnTipePayroll);
                }
            }
        }
        logger.info("[TipePayrollBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipePayroll> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public TipePayroll getTipePayrollById(String tipePayrollId) {
        logger.info("[TipePayrollBoImpl.getTipePayrollById] start process >>>");
        TipePayroll tipePayroll = new TipePayroll();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("tipe_laporan_id", tipePayrollId);
        List<ImHrisTipePayrollEntity> tipePayrollEntityList;
        try {
            // Get data from database by ID
            tipePayrollEntityList = tipePayrollDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[TipePayrollBoImpl.getTipePayrollById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data TipePayroll by Kode TipePayroll, please inform to your admin...," + e.getMessage());
        }
        for (ImHrisTipePayrollEntity tipePayrollEntity : tipePayrollEntityList) {
            tipePayroll.setTipePayrollId(tipePayrollEntity.getTipePayrollId());
            tipePayroll.setTipePayrollName(tipePayrollEntity.getTipePayrollName());
        }
        return tipePayroll;
    }

}
