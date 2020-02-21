package com.neurix.akuntansi.master.trans.bo.impl;

import com.neurix.akuntansi.master.trans.bo.TransBo;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.common.exception.GeneralBOException;
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
public class TransBoImpl implements TransBo {

    protected static transient Logger logger = Logger.getLogger(TransBoImpl.class);
    private TransDao transDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TransBoImpl.logger = logger;
    }

    public TransDao getTransDao() {
        return transDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    @Override
    public void saveDelete(Trans bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImTransEntity imTransEntity = new ImTransEntity();
            try {
                // Get data from database by ID
                imTransEntity = transDao.getById("transId", bean.getTransId());
            } catch (HibernateException e) {
                logger.error("[TransBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imTransEntity != null) {
                // Modify from bean to entity serializable
                imTransEntity.setFlag(bean.getFlag());
                imTransEntity.setAction(bean.getAction());
                imTransEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTransEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    transDao.updateAndSave(imTransEntity);
                } catch (HibernateException e) {
                    logger.error("[TransBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Trans, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[TransBoImpl.saveDelete] Error, not found data Trans with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Trans with request id, please check again your data ...");

            }
        }
        logger.info("[TransBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Trans bean) throws GeneralBOException {
        logger.info("[TransBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImTransEntity imTransEntity = null;
            try {
                // Get data from database by ID
                imTransEntity = transDao.getById("transId", bean.getTransId());
            } catch (HibernateException e) {
                logger.error("[TransBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Trans by Kode Trans, please inform to your admin...," + e.getMessage());
            }
            if (imTransEntity != null) {
                imTransEntity.setTransName(bean.getTransName());
                imTransEntity.setFlag(bean.getFlag());
                imTransEntity.setAction(bean.getAction());
                imTransEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imTransEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    transDao.updateAndSave(imTransEntity);
                } catch (HibernateException e) {
                    logger.error("[TransBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Trans, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[TransBoImpl.saveEdit] Error, not found data Trans with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Trans with request id, please check again your data ...");
            }
        }
        logger.info("[TransBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Trans saveAdd(Trans bean) throws GeneralBOException {
        logger.info("[TransBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String transId;
            try {
                // Generating ID, get from postgre sequence
                transId = transDao.getNextTransId();
            } catch (HibernateException e) {
                logger.error("[TransBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence transId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImTransEntity imTransEntity = new ImTransEntity();

            imTransEntity.setTransId(transId);
            imTransEntity.setTransName(bean.getTransName());
            imTransEntity.setFlag(bean.getFlag());
            imTransEntity.setAction(bean.getAction());
            imTransEntity.setCreatedWho(bean.getCreatedWho());
            imTransEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imTransEntity.setCreatedDate(bean.getCreatedDate());
            imTransEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                transDao.addAndSave(imTransEntity);
            } catch (HibernateException e) {
                logger.error("[TransBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Trans, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TransBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Trans> getByCriteria(Trans searchBean) throws GeneralBOException {
        logger.info("[TransBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Trans> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTransId() != null && !"".equalsIgnoreCase(searchBean.getTransId())) {
                hsCriteria.put("trans_id", searchBean.getTransId());
            }
            if (searchBean.getTransName() != null && !"".equalsIgnoreCase(searchBean.getTransName())) {
                hsCriteria.put("trans_name", searchBean.getTransName());
            }
            if (searchBean.getFlagPembayaranUtangPiutang() != null && !"".equalsIgnoreCase(searchBean.getFlagPembayaranUtangPiutang())) {
                hsCriteria.put("flag_pembayaran", searchBean.getFlagPembayaranUtangPiutang());
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

            List<ImTransEntity> imTransEntity = null;
            try {

                imTransEntity = transDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TransBoImpl.getSearchTransByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imTransEntity != null){
                Trans returnTrans;
                // Looping from dao to object and save in collection
                for(ImTransEntity transEntity : imTransEntity){
                    returnTrans = new Trans();
                    returnTrans.setTransId(transEntity.getTransId());
                    returnTrans.setTransName(transEntity.getTransName());;

                    returnTrans.setCreatedWho(transEntity.getCreatedWho());
                    returnTrans.setCreatedDate(transEntity.getCreatedDate());
                    returnTrans.setLastUpdate(transEntity.getLastUpdate());
                    returnTrans.setAction(transEntity.getAction());
                    returnTrans.setFlag(transEntity.getFlag());
                    listOfResult.add(returnTrans);
                }
            }
        }
        logger.info("[TransBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Trans> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
