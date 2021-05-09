package com.neurix.hris.master.statusRekruitment.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusRekruitment.bo.StatusRekruitmentBo;
import com.neurix.hris.master.statusRekruitment.dao.StatusRekruitmentDao;
import com.neurix.hris.master.statusRekruitment.model.StatusRekruitment;
import com.neurix.hris.master.statusRekruitment.model.ImStatusRekruitmentEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
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
public class StatusRekruitmentBoImpl implements StatusRekruitmentBo {

    protected static transient Logger logger = Logger.getLogger(StatusRekruitmentBoImpl.class);
    private StatusRekruitmentDao statusRekruitmentDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StatusRekruitmentBoImpl.logger = logger;
    }

    public StatusRekruitmentDao getStatusRekruitmentDao() {
        return statusRekruitmentDao;
    }

    public void setStatusRekruitmentDao(StatusRekruitmentDao statusRekruitmentDao) {
        this.statusRekruitmentDao = statusRekruitmentDao;
    }

    @Override
    public void saveDelete(StatusRekruitment bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            BigInteger statusRekruitmentId = bean.getStatusRekruitmentId();

            List<ImStatusRekruitmentEntity> imStatusRekruitmentEntities = new ArrayList<>();

            try {
                // Get data from database by ID
                imStatusRekruitmentEntities = statusRekruitmentDao.getListStatusRekruitment(statusRekruitmentId);

            } catch (HibernateException e) {
                logger.error("[StatusRekruitmentBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data StatusRekruitment by Kode StatusRekruitment, please inform to your admin...," + e.getMessage());
            }

            if (imStatusRekruitmentEntities != null) {
                for (ImStatusRekruitmentEntity statusRekruitmentEntity : imStatusRekruitmentEntities){
                    statusRekruitmentEntity.setStatusRekruitmentName(bean.getStatusRekruitmentName());
                    statusRekruitmentEntity.setFlag(bean.getFlag());
                    statusRekruitmentEntity.setAction(bean.getAction());
                    statusRekruitmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    statusRekruitmentEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        statusRekruitmentDao.updateAndSave(statusRekruitmentEntity);

//                    condition = "Data SuccessFully Updated";
                    } catch (HibernateException e) {
                        logger.error("[StatusRekruitmentBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StatusRekruitment, please info to your admin..." + e.getMessage());
                    }
                }


            } else {
                logger.error("[StatusRekruitmentBoImpl.saveDelete] Error, not found data StatusRekruitment with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StatusRekruitment with request id, please check again your data ...");

            }
        }
        logger.info("[StatusRekruitmentBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(StatusRekruitment bean) throws GeneralBOException {
        logger.info("[StatusRekruitmentBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            BigInteger statusRekruitmentId = bean.getStatusRekruitmentId();

            List<ImStatusRekruitmentEntity> imStatusRekruitmentEntities = new ArrayList<>();

            try {
                // Get data from database by ID
                imStatusRekruitmentEntities = statusRekruitmentDao.getListStatusRekruitment(statusRekruitmentId);

            } catch (HibernateException e) {
                logger.error("[StatusRekruitmentBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data StatusRekruitment by Kode StatusRekruitment, please inform to your admin...," + e.getMessage());
            }

            if (imStatusRekruitmentEntities != null) {
                for (ImStatusRekruitmentEntity statusRekruitmentEntity : imStatusRekruitmentEntities){
                    statusRekruitmentEntity.setStatusRekruitmentName(bean.getStatusRekruitmentName());
                    statusRekruitmentEntity.setFlag(bean.getFlag());
                    statusRekruitmentEntity.setAction(bean.getAction());
                    statusRekruitmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    statusRekruitmentEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        statusRekruitmentDao.updateAndSave(statusRekruitmentEntity);

//                    condition = "Data SuccessFully Updated";
                    } catch (HibernateException e) {
                        logger.error("[StatusRekruitmentBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StatusRekruitment, please info to your admin..." + e.getMessage());
                    }
                }
            } else {
                logger.error("[StatusRekruitmentBoImpl.saveEdit] Error, not found data StatusRekruitment with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StatusRekruitment with request id, please check again your data ...");
//                condition = "Error, not found data StatusRekruitment with request id, please check again your data ...";
            }
        }
        logger.info("[StatusRekruitmentBoImpl.saveEdit] end process <<<");
    }

    @Override
    public StatusRekruitment saveAdd(StatusRekruitment bean) throws GeneralBOException {
        logger.info("[StatusRekruitmentBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

/*            String statusRekruitmentId;
            try {
                // Generating ID, get from postgre sequence
                statusRekruitmentId = statusRekruitmentDao.getNextStatusRekruitmentId();
            } catch (HibernateException e) {
                logger.error("[StatusRekruitmentBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence statusRekruitmentId id, please info to your admin..." + e.getMessage());
            }*/

            // creating object entity serializable
            ImStatusRekruitmentEntity imStatusRekruitmentEntity = new ImStatusRekruitmentEntity();

            imStatusRekruitmentEntity.setStatusRekruitmentId(bean.getStatusRekruitmentId());
            imStatusRekruitmentEntity.setStatusRekruitmentName(bean.getStatusRekruitmentName());
            imStatusRekruitmentEntity.setFlag(bean.getFlag());
            imStatusRekruitmentEntity.setAction(bean.getAction());
            imStatusRekruitmentEntity.setCreatedWho(bean.getCreatedWho());
            imStatusRekruitmentEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imStatusRekruitmentEntity.setCreatedDate(bean.getCreatedDate());
            imStatusRekruitmentEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                statusRekruitmentDao.addAndSave(imStatusRekruitmentEntity);
            } catch (HibernateException e) {
                logger.error("[StatusRekruitmentBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data StatusRekruitment, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[StatusRekruitmentBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<StatusRekruitment> getByCriteria(StatusRekruitment searchBean) throws GeneralBOException {
        logger.info("[StatusRekruitmentBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StatusRekruitment> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getStatusRekruitmentId() != null && !"".equals(searchBean.getStatusRekruitmentId())) {
                hsCriteria.put("statusRekruitment_id", searchBean.getStatusRekruitmentId());
            }
            if (searchBean.getStatusRekruitmentName() != null && !"".equalsIgnoreCase(searchBean.getStatusRekruitmentName())) {
                hsCriteria.put("statusRekruitment_name", searchBean.getStatusRekruitmentName());
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


            List<ImStatusRekruitmentEntity> imStatusRekruitmentEntity = null;
            try {

                imStatusRekruitmentEntity = statusRekruitmentDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StatusRekruitmentBoImpl.getSearchStatusRekruitmentByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imStatusRekruitmentEntity != null){
                StatusRekruitment returnStatusRekruitment;
                int i = 0;
                // Looping from dao to object and save in collection
                for(ImStatusRekruitmentEntity statusRekruitmentEntity : imStatusRekruitmentEntity){
                    returnStatusRekruitment = new StatusRekruitment();
                    returnStatusRekruitment.setStatusRekruitmentId(statusRekruitmentEntity.getStatusRekruitmentId());
                    returnStatusRekruitment.setStatusRekruitmentName(i+" - "+statusRekruitmentEntity.getStatusRekruitmentName());

                    returnStatusRekruitment.setCreatedWho(statusRekruitmentEntity.getCreatedWho());
                    returnStatusRekruitment.setCreatedDate(statusRekruitmentEntity.getCreatedDate());
                    returnStatusRekruitment.setLastUpdate(statusRekruitmentEntity.getLastUpdate());

                    returnStatusRekruitment.setAction(statusRekruitmentEntity.getAction());
                    returnStatusRekruitment.setFlag(statusRekruitmentEntity.getFlag());
                    listOfResult.add(returnStatusRekruitment);
                    i++;
                }
            }
        }
        logger.info("[StatusRekruitmentBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<StatusRekruitment> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<StatusRekruitment> getComboStatusRekruitmentWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<StatusRekruitment> listComboStatusRekruitment = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImStatusRekruitmentEntity> listStatusRekruitment = null;
        try {
            listStatusRekruitment = statusRekruitmentDao.getListStatusRekruitment(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listStatusRekruitment != null) {
            for (ImStatusRekruitmentEntity imStatusRekruitmentEntity : listStatusRekruitment) {
                StatusRekruitment itemComboStatusRekruitment = new StatusRekruitment();
                itemComboStatusRekruitment.setStatusRekruitmentId(imStatusRekruitmentEntity.getStatusRekruitmentId());
                itemComboStatusRekruitment.setStatusRekruitmentName(imStatusRekruitmentEntity.getStatusRekruitmentName());
                listComboStatusRekruitment.add(itemComboStatusRekruitment);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboStatusRekruitment;
    }
}
