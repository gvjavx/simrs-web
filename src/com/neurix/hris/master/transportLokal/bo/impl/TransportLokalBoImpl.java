package com.neurix.hris.master.transportLokal.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.transportLokal.bo.TransportLokalBo;
import com.neurix.hris.master.transportLokal.dao.TransportLokalDao;
import com.neurix.hris.master.transportLokal.model.ImTransportLokalHistoryEntity;
import com.neurix.hris.master.transportLokal.model.TransportLokal;
import com.neurix.hris.master.transportLokal.model.ImTransportLokalEntity;
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
public class TransportLokalBoImpl implements TransportLokalBo {

    protected static transient Logger logger = Logger.getLogger(TransportLokalBoImpl.class);
    private TransportLokalDao transportLokalDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TransportLokalBoImpl.logger = logger;
    }

    public TransportLokalDao getTransportLokalDao() {
        return transportLokalDao;
    }

    public void setTransportLokalDao(TransportLokalDao transportLokalDao) {
        this.transportLokalDao = transportLokalDao;
    }

    @Override
    public void saveDelete(TransportLokal bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String transportLokalId = bean.getTransportLokalId();

            ImTransportLokalEntity imTransportLokalEntity = null;

            try {
                // Get data from database by ID
                imTransportLokalEntity = transportLokalDao.getById("transportLokalId", transportLokalId);
            } catch (HibernateException e) {
                logger.error("[TransportLokalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTransportLokalEntity != null) {

                // Modify from bean to entity serializable
                imTransportLokalEntity.setTransportLokalId(bean.getTransportLokalId());
                imTransportLokalEntity.setTransportLokalName(bean.getTransportLokalName());
                imTransportLokalEntity.setTransportLokalKe(bean.getTransportLokalKe());
                imTransportLokalEntity.setJumlahBiaya(bean.getJumlahBiaya());
                imTransportLokalEntity.setTransportLokalName(bean.getTransportLokalName());
                imTransportLokalEntity.setFlag(bean.getFlag());
                imTransportLokalEntity.setAction(bean.getAction());
                imTransportLokalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTransportLokalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    transportLokalDao.updateAndSave(imTransportLokalEntity);
                } catch (HibernateException e) {
                    logger.error("[TransportLokalBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TransportLokal, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[TransportLokalBoImpl.saveDelete] Error, not found data TransportLokal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TransportLokal with request id, please check again your data ...");

            }
        }
        logger.info("[TransportLokalBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(TransportLokal bean) throws GeneralBOException {
        logger.info("[TransportLokalBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String transportLokalId = bean.getTransportLokalId();
            String idHistory = "";
            ImTransportLokalEntity imTransportLokalEntity = null;
            ImTransportLokalHistoryEntity imTransportLokalHistoryEntity = new ImTransportLokalHistoryEntity();
            try {
                // Get data from database by ID
                imTransportLokalEntity = transportLokalDao.getById("transportLokalId", transportLokalId);
                idHistory = transportLokalDao.getNextTransportLokalHistooryId();
            } catch (HibernateException e) {
                logger.error("[TransportLokalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data TransportLokal by Kode TransportLokal, please inform to your admin...," + e.getMessage());
            }

            if (imTransportLokalEntity != null) {
                imTransportLokalHistoryEntity.setId(idHistory);
                imTransportLokalHistoryEntity.setTransportLokalId(imTransportLokalEntity.getTransportLokalId());
                imTransportLokalHistoryEntity.setTransportLokalName(imTransportLokalEntity.getTransportLokalName());
                imTransportLokalHistoryEntity.setTransportLokalKe(imTransportLokalEntity.getTransportLokalKe());
                imTransportLokalHistoryEntity.setJumlahBiaya(imTransportLokalEntity.getJumlahBiaya());
                imTransportLokalHistoryEntity.setFlag(imTransportLokalEntity.getFlag());
                imTransportLokalHistoryEntity.setAction(imTransportLokalEntity.getAction());
                imTransportLokalHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTransportLokalHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imTransportLokalHistoryEntity.setCreatedWho(imTransportLokalEntity.getCreatedWho());
                imTransportLokalHistoryEntity.setCreatedDate(imTransportLokalEntity.getCreatedDate());

                imTransportLokalEntity.setTransportLokalId(bean.getTransportLokalId());
                imTransportLokalEntity.setTransportLokalName(bean.getTransportLokalName());
                imTransportLokalEntity.setTransportLokalKe(bean.getTransportLokalKe());
                imTransportLokalEntity.setJumlahBiaya(bean.getJumlahBiaya());
                imTransportLokalEntity.setFlag(bean.getFlag());
                imTransportLokalEntity.setAction(bean.getAction());
                imTransportLokalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTransportLokalEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    transportLokalDao.updateAndSave(imTransportLokalEntity);
                    transportLokalDao.addAndSaveHistory(imTransportLokalHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[TransportLokalBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data TransportLokal, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TransportLokalBoImpl.saveEdit] Error, not found data TransportLokal with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data TransportLokal with request id, please check again your data ...");
//                condition = "Error, not found data TransportLokal with request id, please check again your data ...";
            }
        }
        logger.info("[TransportLokalBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TransportLokal saveAdd(TransportLokal bean) throws GeneralBOException {
        logger.info("[TransportLokalBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String transportLokalId;
            try {
                // Generating ID, get from postgre sequence
                transportLokalId = transportLokalDao.getNextTransportLokalId();
            } catch (HibernateException e) {
                logger.error("[TransportLokalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence transportLokalId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImTransportLokalEntity imTransportLokalEntity = new ImTransportLokalEntity();

            imTransportLokalEntity.setTransportLokalId(transportLokalId);
            imTransportLokalEntity.setTransportLokalName(bean.getTransportLokalName());
            imTransportLokalEntity.setTransportLokalKe(bean.getTransportLokalKe());
            imTransportLokalEntity.setJumlahBiaya(bean.getJumlahBiaya());
            imTransportLokalEntity.setFlag(bean.getFlag());
            imTransportLokalEntity.setAction(bean.getAction());
            imTransportLokalEntity.setCreatedWho(bean.getCreatedWho());
            imTransportLokalEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTransportLokalEntity.setCreatedDate(bean.getCreatedDate());
            imTransportLokalEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                transportLokalDao.addAndSave(imTransportLokalEntity);
            } catch (HibernateException e) {
                logger.error("[TransportLokalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data TransportLokal, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TransportLokalBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TransportLokal> getByCriteria(TransportLokal searchBean) throws GeneralBOException {
        logger.info("[TransportLokalBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TransportLokal> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTransportLokalId() != null && !"".equalsIgnoreCase(searchBean.getTransportLokalId())) {
                hsCriteria.put("transport_lokal_id", searchBean.getTransportLokalId());
            }
            if (searchBean.getTransportLokalName() != null && !"".equalsIgnoreCase(searchBean.getTransportLokalName())) {
                hsCriteria.put("transport_lokal_name", searchBean.getTransportLokalName());
            }
            if (searchBean.getTransportLokalKe() != null && !"".equalsIgnoreCase(searchBean.getTransportLokalKe())) {
                hsCriteria.put("transport_lokal_ke", searchBean.getTransportLokalKe());
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


            List<ImTransportLokalEntity> imTransportLokalEntity = null;
            try {

                imTransportLokalEntity = transportLokalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TransportLokalBoImpl.getSearchTransportLokalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTransportLokalEntity != null){
                TransportLokal returnTransportLokal;
                // Looping from dao to object and save in collection
                for(ImTransportLokalEntity transportLokalEntity : imTransportLokalEntity){
                    returnTransportLokal = new TransportLokal();
                    returnTransportLokal.setTransportLokalId(transportLokalEntity.getTransportLokalId());

                    returnTransportLokal.setTransportLokalName(transportLokalEntity.getImKotaEntity().getKotaName());
                    returnTransportLokal.setTransportLokalKe(transportLokalEntity.getTransportLokalKe());
                    returnTransportLokal.setJumlahBiaya(transportLokalEntity.getJumlahBiaya());

                    returnTransportLokal.setCreatedWho(transportLokalEntity.getCreatedWho());
                    returnTransportLokal.setCreatedDate(transportLokalEntity.getCreatedDate());
                    returnTransportLokal.setLastUpdate(transportLokalEntity.getLastUpdate());

                    returnTransportLokal.setAction(transportLokalEntity.getAction());
                    returnTransportLokal.setFlag(transportLokalEntity.getFlag());
                    listOfResult.add(returnTransportLokal);
                }
            }
        }
        logger.info("[TransportLokalBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TransportLokal> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<TransportLokal> getComboTransportLokalWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<TransportLokal> listComboTransportLokal = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImTransportLokalEntity> listTransportLokal = null;
        try {
            listTransportLokal = transportLokalDao.getListTransportLokal(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listTransportLokal != null) {
            for (ImTransportLokalEntity imTransportLokalEntity : listTransportLokal) {
                TransportLokal itemComboTransportLokal = new TransportLokal();
                itemComboTransportLokal.setTransportLokalId(imTransportLokalEntity.getTransportLokalId());
                itemComboTransportLokal.setTransportLokalName(imTransportLokalEntity.getTransportLokalName());
                itemComboTransportLokal.setTransportLokalKe(imTransportLokalEntity.getTransportLokalKe());
                itemComboTransportLokal.setJumlahBiaya(imTransportLokalEntity.getJumlahBiaya());
                listComboTransportLokal.add(itemComboTransportLokal);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboTransportLokal;
    }
}
