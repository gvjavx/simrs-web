package com.neurix.hris.master.jamLembur.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.jamLembur.bo.JamLemburBo;
import com.neurix.hris.master.jamLembur.dao.JamLemburDao;
import com.neurix.hris.transaksi.lembur.model.JamLemburEntity;
import com.neurix.hris.transaksi.lembur.model.JamLembur;
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
public class JamLemburBoImpl implements JamLemburBo {

    protected static transient Logger logger = Logger.getLogger(JamLemburBoImpl.class);
    private JamLemburDao jamLemburDao;


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JamLemburBoImpl.logger = logger;
    }

    public JamLemburDao getJamLemburDao() {
        return jamLemburDao;
    }

    public void setJamLemburDao(JamLemburDao jamLemburDao) {
        this.jamLemburDao = jamLemburDao;
    }

    @Override
    public void saveDelete(JamLembur bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String jamLemburId = bean.getJamLemburId();

            JamLemburEntity jamLemburEntity = null;

            try {
                // Get data from database by ID
                jamLemburEntity = jamLemburDao.getById("jamLemburId", jamLemburId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (jamLemburEntity != null) {

                // Modify from bean to entity serializable
                jamLemburEntity.setJamLemburId(bean.getJamLemburId());
                jamLemburEntity.setJamLembur(bean.getJamLembur());
                jamLemburEntity.setPengaliJamLembur(bean.getPengaliJamLembur());
                jamLemburEntity.setTipeHari(bean.getTipeHari());
                jamLemburEntity.setFlag(bean.getFlag());
                jamLemburEntity.setAction(bean.getAction());
                jamLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                jamLemburEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jamLemburDao.updateAndSave(jamLemburEntity);
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[AlatBoImpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");

            }
        }
        logger.info("[AlatBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(JamLembur bean) throws GeneralBOException {
        logger.info("[JamLemburBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String jamLemburId = bean.getJamLemburId();

            JamLemburEntity jamLemburEntity = null;

            try {
                // Get data from database by ID
                jamLemburEntity = jamLemburDao.getById("jamLemburId", jamLemburId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (jamLemburEntity != null) {
                //
                jamLemburEntity.setJamLemburId(bean.getJamLemburId());
                jamLemburEntity.setTipeHari(bean.getTipeHari());
                jamLemburEntity.setPengaliJamLembur(bean.getPengaliJamLembur());
                jamLemburEntity.setJamLembur(bean.getJamLembur());
                jamLemburEntity.setFlag(bean.getFlag());
                jamLemburEntity.setAction(bean.getAction());
                jamLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                jamLemburEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    jamLemburDao.updateAndSave(jamLemburEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AlatBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[AlatBoImpl.saveEdit] end process <<<");
    }

    @Override
    public JamLembur saveAdd(JamLembur bean) throws GeneralBOException {
        logger.info("[JamLemburBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String alatId;
            try {
                // Generating ID, get from postgre sequence
                alatId = jamLemburDao.getNextJamLemburId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            JamLemburEntity jamLemburEntity = new JamLemburEntity();

            jamLemburEntity.setJamLemburId(alatId);
            jamLemburEntity.setTipeHari(bean.getTipeHari());
            jamLemburEntity.setPengaliJamLembur(bean.getPengaliJamLembur());
            jamLemburEntity.setJamLembur(bean.getJamLembur());

            jamLemburEntity.setFlag(bean.getFlag());
            jamLemburEntity.setAction(bean.getAction());
            jamLemburEntity.setCreatedWho(bean.getCreatedWho());
            jamLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
            jamLemburEntity.setCreatedDate(bean.getCreatedDate());
            jamLemburEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                jamLemburDao.addAndSave(jamLemburEntity);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[AlatBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<JamLembur> getByCriteria(JamLembur searchBean) throws GeneralBOException {
        logger.info("[JamLemburBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<JamLembur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJamLemburId() != null && !"".equalsIgnoreCase(searchBean.getJamLemburId())) {
                hsCriteria.put("jam_lembur_id", searchBean.getJamLemburId());
            }
            if (searchBean.getTipeHari() != null && !"".equalsIgnoreCase(searchBean.getTipeHari())) {
                hsCriteria.put("tipe_hari", searchBean.getTipeHari());
            }
            if (searchBean.getJamLembur() != null ) {
                hsCriteria.put("jam_lembur", searchBean.getJamLembur());
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

            List<JamLemburEntity> jamLemburEntityList = null;
            try {

                jamLemburEntityList = jamLemburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.getSearchAlatByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(jamLemburEntityList != null){
                JamLembur returnJamLembur;
                // Looping from dao to object and save in collection
                for(JamLemburEntity jamLemburEntity : jamLemburEntityList){
                    returnJamLembur = new JamLembur();
                    returnJamLembur.setJamLemburId(jamLemburEntity.getJamLemburId());
                    returnJamLembur.setJamLembur(jamLemburEntity.getJamLembur());
                    returnJamLembur.setPengaliJamLembur(jamLemburEntity.getPengaliJamLembur());
                    returnJamLembur.setTipeHari(jamLemburEntity.getTipeHari());
                    returnJamLembur.setCreatedWho(jamLemburEntity.getCreatedWho());
                    returnJamLembur.setCreatedDate(jamLemburEntity.getCreatedDate());
                    returnJamLembur.setLastUpdate(jamLemburEntity.getLastUpdate());
                    returnJamLembur.setAction(jamLemburEntity.getAction());
                    returnJamLembur.setFlag(jamLemburEntity.getFlag());
                    listOfResult.add(returnJamLembur);
                }
            }
        }
        logger.info("[AlatBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<JamLembur> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<JamLembur> getComboJamLemburWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<JamLembur> listComboJamLembur = new ArrayList();
        String criteria = "%" + query + "%";

        List<JamLemburEntity> listJamLembur = null;
        try {
            listJamLembur = jamLemburDao.getListJamLembur(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listJamLembur != null) {
            for (JamLemburEntity jamLemburEntity : listJamLembur) {
                JamLembur itemComboJamLembur = new JamLembur();
                itemComboJamLembur.setJamLemburId(jamLemburEntity.getJamLemburId());
                itemComboJamLembur.setPengaliJamLembur(jamLemburEntity.getPengaliJamLembur());
                itemComboJamLembur.setTipeHari(jamLemburEntity.getTipeHari());
                itemComboJamLembur.setJamLembur(jamLemburEntity.getJamLembur());
                listComboJamLembur.add(itemComboJamLembur);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboJamLembur;
    }
}
