package com.neurix.akuntansi.master.settingReportUser.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.settingReportUser.bo.SettingReportUserBo;
import com.neurix.akuntansi.master.settingReportUser.dao.SettingReportUserDao;
import com.neurix.akuntansi.master.settingReportUser.model.ImSettingReportUserEntity;
import com.neurix.akuntansi.master.settingReportUser.model.SettingReportUser;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.dao.LaporanAkuntansiDao;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.ImUsersPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
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
public class SettingReportUserBoImpl implements SettingReportUserBo {

    protected static transient Logger logger = Logger.getLogger(SettingReportUserBoImpl.class);
    private SettingReportUserDao settingReportUserDao;
    private LaporanAkuntansiDao laporanAkuntansiDao;
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public LaporanAkuntansiDao getLaporanAkuntansiDao() {
        return laporanAkuntansiDao;
    }

    public void setLaporanAkuntansiDao(LaporanAkuntansiDao laporanAkuntansiDao) {
        this.laporanAkuntansiDao = laporanAkuntansiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SettingReportUserBoImpl.logger = logger;
    }

    public SettingReportUserDao getSettingReportUserDao() {
        return settingReportUserDao;
    }

    public void setSettingReportUserDao(SettingReportUserDao settingReportUserDao) {
        this.settingReportUserDao = settingReportUserDao;
    }

    @Override
    public void saveDelete(SettingReportUser bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImSettingReportUserEntity imSettingReportUserEntity = new ImSettingReportUserEntity();
            try {
                // Get data from database by ID
                imSettingReportUserEntity = settingReportUserDao.getById("settingReportUserId", bean.getSettingReportUserId());
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSettingReportUserEntity != null) {
                // Modify from bean to entity serializable
                imSettingReportUserEntity.setFlag(bean.getFlag());
                imSettingReportUserEntity.setAction(bean.getAction());
                imSettingReportUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSettingReportUserEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    settingReportUserDao.updateAndSave(imSettingReportUserEntity);
                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SettingReportUser, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SettingReportUserBoImpl.saveDelete] Error, not found data SettingReportUser with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SettingReportUser with request id, please check again your data ...");

            }
        }
        logger.info("[SettingReportUserBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SettingReportUser bean) throws GeneralBOException {
        logger.info("[SettingReportUserBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            List<ImSettingReportUserEntity> settingReportUserList = new ArrayList<>();
            try {
                // Generating ID, get from postgre sequence
                settingReportUserList = settingReportUserDao.getListByReportIdNUserId(bean.getReportId(),bean.getUserId());
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence settingReportUserId id, please info to your admin..." + e.getMessage());
            }
            if (settingReportUserList.size()>0){
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, duplicate data");
                throw new GeneralBOException("Error : Duplicate data, please info to your admin...");
            }

            ImSettingReportUserEntity imSettingReportUserEntity = null;
            try {
                // Get data from database by ID
                imSettingReportUserEntity = settingReportUserDao.getById("settingReportUserId", bean.getSettingReportUserId());
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SettingReportUser by Kode SettingReportUser, please inform to your admin...," + e.getMessage());
            }
            if (imSettingReportUserEntity != null) {
                imSettingReportUserEntity.setReportId(bean.getReportId());
                imSettingReportUserEntity.setUserId(bean.getUserId());
                imSettingReportUserEntity.setFlag(bean.getFlag());
                imSettingReportUserEntity.setAction(bean.getAction());
                imSettingReportUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSettingReportUserEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    settingReportUserDao.updateAndSave(imSettingReportUserEntity);
                } catch (HibernateException e) {
                    logger.error("[SettingReportUserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SettingReportUser, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SettingReportUserBoImpl.saveEdit] Error, not found data SettingReportUser with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SettingReportUser with request id, please check again your data ...");
            }
        }
        logger.info("[SettingReportUserBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SettingReportUser saveAdd(SettingReportUser bean) throws GeneralBOException {
        logger.info("[SettingReportUserBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            List<ImSettingReportUserEntity> settingReportUserList = new ArrayList<>();
            try {
                // Generating ID, get from postgre sequence
                settingReportUserList = settingReportUserDao.getListByReportIdNUserId(bean.getReportId(),bean.getUserId());
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence settingReportUserId id, please info to your admin..." + e.getMessage());
            }
            if (settingReportUserList.size()>0){
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, duplicate data");
                throw new GeneralBOException("Error : Duplicate data, please info to your admin...");
            }

            String settingReportUserId;
            try {
                // Generating ID, get from postgre sequence
                settingReportUserId = settingReportUserDao.getNextSettingReportUserId();
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence settingReportUserId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSettingReportUserEntity imSettingReportUserEntity = new ImSettingReportUserEntity();

            imSettingReportUserEntity.setSettingReportUserId(settingReportUserId);
            imSettingReportUserEntity.setReportId(bean.getReportId());
            imSettingReportUserEntity.setUserId(bean.getUserId());
            imSettingReportUserEntity.setFlag(bean.getFlag());
            imSettingReportUserEntity.setAction(bean.getAction());
            imSettingReportUserEntity.setCreatedWho(bean.getCreatedWho());
            imSettingReportUserEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSettingReportUserEntity.setCreatedDate(bean.getCreatedDate());
            imSettingReportUserEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                settingReportUserDao.addAndSave(imSettingReportUserEntity);
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SettingReportUser, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SettingReportUserBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SettingReportUser> getByCriteria(SettingReportUser searchBean) throws GeneralBOException {
        logger.info("[SettingReportUserBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SettingReportUser> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSettingReportUserId() != null && !"".equalsIgnoreCase(searchBean.getSettingReportUserId())) {
                hsCriteria.put("setting_report_user_id", searchBean.getSettingReportUserId());
            }
            if (searchBean.getReportId() != null && !"".equalsIgnoreCase(searchBean.getReportId())) {
                hsCriteria.put("report_id", searchBean.getReportId());
            }
            if (searchBean.getUserId() != null && !"".equalsIgnoreCase(searchBean.getUserId())) {
                hsCriteria.put("user_id", searchBean.getUserId());
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

            List<ImSettingReportUserEntity> imSettingReportUserEntity = null;
            try {

                imSettingReportUserEntity = settingReportUserDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SettingReportUserBoImpl.getSearchSettingReportUserByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSettingReportUserEntity != null){
                SettingReportUser returnSettingReportUser;
                // Looping from dao to object and save in collection
                for(ImSettingReportUserEntity settingReportUserEntity : imSettingReportUserEntity){
                    returnSettingReportUser = new SettingReportUser();
                    returnSettingReportUser.setSettingReportUserId(settingReportUserEntity.getSettingReportUserId());
                    returnSettingReportUser.setReportId(settingReportUserEntity.getReportId());
                    returnSettingReportUser.setUserId(settingReportUserEntity.getUserId());

                    ItLaporanAkuntansiEntity laporanAkuntansiEntity ;
                    if (settingReportUserEntity.getReportId()!=null){
                        laporanAkuntansiEntity = laporanAkuntansiDao.getById("laporanAkuntansiId",settingReportUserEntity.getReportId());
                        returnSettingReportUser.setReportName(laporanAkuntansiEntity.getLaporanAkuntansiName());
                    }else{
                        returnSettingReportUser.setReportName("");
                    }

                    ImUsers imUsers;
                    ImUsersPK primaryKey = new ImUsersPK();
                    if (settingReportUserEntity.getUserId()!=null){
                        primaryKey.setId(settingReportUserEntity.getUserId());
                        imUsers = userDao.getById(primaryKey, "Y");
                        returnSettingReportUser.setUserName(imUsers.getUserName());
                    }

                    returnSettingReportUser.setCreatedWho(settingReportUserEntity.getCreatedWho());
                    returnSettingReportUser.setCreatedDate(settingReportUserEntity.getCreatedDate());
                    returnSettingReportUser.setLastUpdate(settingReportUserEntity.getLastUpdate());
                    returnSettingReportUser.setAction(settingReportUserEntity.getAction());
                    returnSettingReportUser.setFlag(settingReportUserEntity.getFlag());
                    listOfResult.add(returnSettingReportUser);
                }
            }
        }
        logger.info("[SettingReportUserBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SettingReportUser> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
