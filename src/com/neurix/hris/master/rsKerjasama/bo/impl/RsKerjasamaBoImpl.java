package com.neurix.hris.master.rsKerjasama.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.rsKerjasama.bo.RsKerjasamaBo;
import com.neurix.hris.master.rsKerjasama.dao.RsKerjasamaDao;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaHistoryEntity;
import com.neurix.hris.master.rsKerjasama.model.RsKerjasama;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaEntity;
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
public class RsKerjasamaBoImpl implements RsKerjasamaBo {

    protected static transient Logger logger = Logger.getLogger(RsKerjasamaBoImpl.class);
    private RsKerjasamaDao rsKerjasamaDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RsKerjasamaBoImpl.logger = logger;
    }

    public RsKerjasamaDao getRsKerjasamaDao() {
        return rsKerjasamaDao;
    }

    public void setRsKerjasamaDao(RsKerjasamaDao rsKerjasamaDao) {
        this.rsKerjasamaDao = rsKerjasamaDao;
    }

    @Override
    public void saveDelete(RsKerjasama bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String rsKerjasamaId = bean.getRsId();

            ImRsKerjasamaEntity imRsKerjasamaEntity = null;

            try {
                // Get data from database by ID
                imRsKerjasamaEntity = rsKerjasamaDao.getById("rsId", rsKerjasamaId);
            } catch (HibernateException e) {
                logger.error("[RsKerjasamaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imRsKerjasamaEntity != null) {

                // Modify from bean to entity serializable
                imRsKerjasamaEntity.setRsId(bean.getRsId());
                imRsKerjasamaEntity.setKodeRs(bean.getKodeRs());
                imRsKerjasamaEntity.setAlamatRs(bean.getAlamatRs());
                imRsKerjasamaEntity.setTipeRs(bean.getTipeRs());
                imRsKerjasamaEntity.setRsName(bean.getRsName());

                imRsKerjasamaEntity.setFlag(bean.getFlag());
                imRsKerjasamaEntity.setAction(bean.getAction());
                imRsKerjasamaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRsKerjasamaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    rsKerjasamaDao.updateAndSave(imRsKerjasamaEntity);
                } catch (HibernateException e) {
                    logger.error("[RsKerjasamaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data RsKerjasama, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[RsKerjasamaBoImpl.saveDelete] Error, not found data RsKerjasama with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data RsKerjasama with request id, please check again your data ...");

            }
        }
        logger.info("[RsKerjasamaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(RsKerjasama bean) throws GeneralBOException {
        logger.info("[RsKerjasamaBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String rsKerjasamaId = bean.getRsId();
            String idhistory = "";

            ImRsKerjasamaEntity imRsKerjasamaEntity = null;
            ImRsKerjasamaHistoryEntity imRsKerjasamaHistoryEntity = new ImRsKerjasamaHistoryEntity();
            try {
                // Get data from database by ID
                imRsKerjasamaEntity = rsKerjasamaDao.getById("rsId", rsKerjasamaId);
                idhistory = rsKerjasamaDao.getNextRsKerjasamaHistoryId();
            } catch (HibernateException e) {
                logger.error("[RsKerjasamaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data RsKerjasama by Kode RsKerjasama, please inform to your admin...," + e.getMessage());
            }

            if (imRsKerjasamaEntity != null) {

                imRsKerjasamaHistoryEntity.setId(idhistory);
                imRsKerjasamaHistoryEntity.setRsId(imRsKerjasamaEntity.getRsId());
                imRsKerjasamaHistoryEntity.setKodeRs(imRsKerjasamaEntity.getKodeRs());
                imRsKerjasamaHistoryEntity.setAlamatRs(imRsKerjasamaEntity.getAlamatRs());
                imRsKerjasamaHistoryEntity.setTipeRs(imRsKerjasamaEntity.getTipeRs());
                imRsKerjasamaHistoryEntity.setRsName(imRsKerjasamaEntity.getRsName());
                imRsKerjasamaHistoryEntity.setFlag(imRsKerjasamaEntity.getFlag());
                imRsKerjasamaHistoryEntity.setAction(imRsKerjasamaEntity.getAction());
                imRsKerjasamaHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRsKerjasamaHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imRsKerjasamaHistoryEntity.setCreatedWho(imRsKerjasamaEntity.getLastUpdateWho());
                imRsKerjasamaHistoryEntity.setCreatedDate(imRsKerjasamaEntity.getLastUpdate());

                imRsKerjasamaEntity.setRsId(bean.getRsId());
                imRsKerjasamaEntity.setKodeRs(bean.getKodeRs());
                imRsKerjasamaEntity.setAlamatRs(bean.getAlamatRs());
                imRsKerjasamaEntity.setTipeRs(bean.getTipeRs());
                imRsKerjasamaEntity.setRsName(bean.getRsName());
                imRsKerjasamaEntity.setFlag(bean.getFlag());
                imRsKerjasamaEntity.setAction(bean.getAction());
                imRsKerjasamaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imRsKerjasamaEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    rsKerjasamaDao.updateAndSave(imRsKerjasamaEntity);
                    rsKerjasamaDao.addAndSaveHistory(imRsKerjasamaHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[RsKerjasamaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data RsKerjasama, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[RsKerjasamaBoImpl.saveEdit] Error, not found data RsKerjasama with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data RsKerjasama with request id, please check again your data ...");
            }
        }
        logger.info("[RsKerjasamaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public RsKerjasama saveAdd(RsKerjasama bean) throws GeneralBOException {
        logger.info("[RsKerjasamaBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String rsKerjasamaId;
            try {
                // Generating ID, get from postgre sequence
                rsKerjasamaId = rsKerjasamaDao.getNextRsKerjasamaId();
            } catch (HibernateException e) {
                logger.error("[RsKerjasamaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence rsKerjasamaId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImRsKerjasamaEntity imRsKerjasamaEntity = new ImRsKerjasamaEntity();

            imRsKerjasamaEntity.setRsId(rsKerjasamaId);
            imRsKerjasamaEntity.setKodeRs(bean.getKodeRs());
            imRsKerjasamaEntity.setAlamatRs(bean.getAlamatRs());
            imRsKerjasamaEntity.setTipeRs(bean.getTipeRs());
            imRsKerjasamaEntity.setRsName(bean.getRsName());

            imRsKerjasamaEntity.setFlag(bean.getFlag());
            imRsKerjasamaEntity.setAction(bean.getAction());
            imRsKerjasamaEntity.setCreatedWho(bean.getCreatedWho());
            imRsKerjasamaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imRsKerjasamaEntity.setCreatedDate(bean.getCreatedDate());
            imRsKerjasamaEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rsKerjasamaDao.addAndSave(imRsKerjasamaEntity);
            } catch (HibernateException e) {
                logger.error("[RsKerjasamaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data RsKerjasama, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[RsKerjasamaBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<RsKerjasama> getByCriteria(RsKerjasama searchBean) throws GeneralBOException {
        logger.info("[RsKerjasamaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RsKerjasama> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getRsId() != null && !"".equalsIgnoreCase(searchBean.getRsId())) {
                hsCriteria.put("rs_id", searchBean.getRsId());
            }
            if (searchBean.getRsName() != null && !"".equalsIgnoreCase(searchBean.getRsName())) {
                hsCriteria.put("rs_name", searchBean.getRsName());
            }

            if (searchBean.getAlamatRs() != null && !"".equalsIgnoreCase(searchBean.getAlamatRs())) {
                hsCriteria.put("alamat_rs", searchBean.getAlamatRs());
            }

            if (searchBean.getKodeRs() != null && !"".equalsIgnoreCase(searchBean.getKodeRs())) {
                hsCriteria.put("kode_rs", searchBean.getKodeRs());
            }

            if (searchBean.getTipeRs() != null && !"".equalsIgnoreCase(searchBean.getTipeRs())) {
                hsCriteria.put("tipe_rs", searchBean.getTipeRs());
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


            List<ImRsKerjasamaEntity> imRsKerjasamaEntity = null;
            try {

                imRsKerjasamaEntity = rsKerjasamaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RsKerjasamaBoImpl.getSearchRsKerjasamaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imRsKerjasamaEntity != null){
                RsKerjasama returnRsKerjasama;
                // Looping from dao to object and save in collection
                for(ImRsKerjasamaEntity rsKerjasamaEntity : imRsKerjasamaEntity){
                    returnRsKerjasama = new RsKerjasama();
                    returnRsKerjasama.setRsId(rsKerjasamaEntity.getRsId());
                    returnRsKerjasama.setRsName(rsKerjasamaEntity.getRsName());
                    returnRsKerjasama.setTipeRs(rsKerjasamaEntity.getTipeRs());
                    returnRsKerjasama.setAlamatRs(rsKerjasamaEntity.getAlamatRs());
                    returnRsKerjasama.setKodeRs(rsKerjasamaEntity.getKodeRs());


                    returnRsKerjasama.setCreatedWho(rsKerjasamaEntity.getCreatedWho());
                    returnRsKerjasama.setCreatedDate(rsKerjasamaEntity.getCreatedDate());
                    returnRsKerjasama.setLastUpdate(rsKerjasamaEntity.getLastUpdate());

                    returnRsKerjasama.setAction(rsKerjasamaEntity.getAction());
                    returnRsKerjasama.setFlag(rsKerjasamaEntity.getFlag());
                    listOfResult.add(returnRsKerjasama);
                }
            }
        }
        logger.info("[RsKerjasamaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<RsKerjasama> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<RsKerjasama> getComboRsKerjasamaWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<RsKerjasama> listComboRsKerjasama = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImRsKerjasamaEntity> listRsKerjasama = null;
        try {
            listRsKerjasama = rsKerjasamaDao.getListRsKerjasama(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listRsKerjasama != null) {
            for (ImRsKerjasamaEntity imRsKerjasamaEntity : listRsKerjasama) {
                RsKerjasama itemComboRsKerjasama = new RsKerjasama();
                itemComboRsKerjasama.setRsId(imRsKerjasamaEntity.getRsId());
                itemComboRsKerjasama.setTipeRs(imRsKerjasamaEntity.getTipeRs());
                itemComboRsKerjasama.setRsName(imRsKerjasamaEntity.getRsName());
                itemComboRsKerjasama.setAlamatRs(imRsKerjasamaEntity.getAlamatRs());
                itemComboRsKerjasama.setKodeRs(imRsKerjasamaEntity.getKodeRs());
                listComboRsKerjasama.add(itemComboRsKerjasama);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboRsKerjasama;
    }
}
