package com.neurix.hris.master.branchTunjangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.branchTunjangan.bo.BranchTunjanganBo;
import com.neurix.hris.master.branchTunjangan.dao.BranchTunjanganDao;
import com.neurix.hris.master.branchTunjangan.model.BranchTunjangan;
import com.neurix.hris.master.branchTunjangan.model.ImBranchTunjanganEntity;
import com.neurix.hris.master.branchTunjangan.model.ImBranchTunjanganHistoryEntity;
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
public class BranchTunjanganBoImpl implements BranchTunjanganBo {

    protected static transient Logger logger = Logger.getLogger(BranchTunjanganBoImpl.class);
    private BranchTunjanganDao branchTunjanganDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BranchTunjanganBoImpl.logger = logger;
    }

    public BranchTunjanganDao getBranchTunjanganDao() {
        return branchTunjanganDao;
    }


    public void setBranchTunjanganDao(BranchTunjanganDao branchTunjanganDao) {
        this.branchTunjanganDao = branchTunjanganDao;
    }

    @Override
    public void saveDelete(BranchTunjangan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String branchTunjanganId = bean.getBranchTunjanganId();

            ImBranchTunjanganEntity imBranchTunjanganEntity = null;

            try {
                // Get data from database by ID
                imBranchTunjanganEntity = branchTunjanganDao.getById("branchTunjanganId", branchTunjanganId);
            } catch (HibernateException e) {
                logger.error("[BranchTunjanganBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imBranchTunjanganEntity != null) {

                // Modify from bean to entity serializable
                imBranchTunjanganEntity.setBranchTunjanganId(bean.getBranchTunjanganId());
                imBranchTunjanganEntity.setBranchId(bean.getBranchId());
                imBranchTunjanganEntity.setTunjanganId(bean.getTunjanganId());

                imBranchTunjanganEntity.setFlag(bean.getFlag());
                imBranchTunjanganEntity.setAction(bean.getAction());
                imBranchTunjanganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBranchTunjanganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    branchTunjanganDao.updateAndSave(imBranchTunjanganEntity);
                } catch (HibernateException e) {
                    logger.error("[BranchTunjanganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data BranchTunjangan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[BranchTunjanganBoImpl.saveDelete] Error, not found data BranchTunjangan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data BranchTunjangan with request id, please check again your data ...");

            }
        }
        logger.info("[BranchTunjanganBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(BranchTunjangan bean) throws GeneralBOException {
        logger.info("[BranchTunjanganBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String branchTunjanganId = bean.getBranchTunjanganId();

            ImBranchTunjanganEntity imBranchTunjanganEntity = null;
            ImBranchTunjanganHistoryEntity imBranchTunjanganHistoryEntity = new ImBranchTunjanganHistoryEntity();
            try {
                // Get data from database by ID
                imBranchTunjanganEntity = branchTunjanganDao.getById("branchTunjanganId", branchTunjanganId);
                historyId = branchTunjanganDao.getNextBranchTunjanganHistoryId();
            } catch (HibernateException e) {
                logger.error("[BranchTunjanganBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data BranchTunjangan by Kode BranchTunjangan, please inform to your admin...," + e.getMessage());
            }

            if (imBranchTunjanganEntity != null) {
                imBranchTunjanganHistoryEntity.setBranchTunjanganHistoryId(historyId);
                imBranchTunjanganHistoryEntity.setBranchTunjanganId(imBranchTunjanganEntity.getBranchTunjanganId());
                imBranchTunjanganHistoryEntity.setBranchId(imBranchTunjanganEntity.getBranchId());
                imBranchTunjanganHistoryEntity.setTunjanganId(imBranchTunjanganEntity.getTunjanganId());
                imBranchTunjanganHistoryEntity.setFlag(imBranchTunjanganEntity.getFlag());
                imBranchTunjanganHistoryEntity.setAction(imBranchTunjanganEntity.getAction());
                imBranchTunjanganHistoryEntity.setLastUpdateWho(imBranchTunjanganEntity.getLastUpdateWho());
                imBranchTunjanganHistoryEntity.setLastUpdate(imBranchTunjanganEntity.getLastUpdate());
                imBranchTunjanganHistoryEntity.setCreatedWho(imBranchTunjanganEntity.getLastUpdateWho());
                imBranchTunjanganHistoryEntity.setCreatedDate(imBranchTunjanganEntity.getLastUpdate());

                imBranchTunjanganEntity.setBranchTunjanganId(bean.getBranchTunjanganId());
                imBranchTunjanganEntity.setBranchId(bean.getBranchId());
                imBranchTunjanganEntity.setTunjanganId(bean.getTunjanganId());
                imBranchTunjanganEntity.setFlag(bean.getFlag());
                imBranchTunjanganEntity.setAction(bean.getAction());
                imBranchTunjanganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imBranchTunjanganEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    branchTunjanganDao.updateAndSave(imBranchTunjanganEntity);
                    branchTunjanganDao.addAndSaveHistory(imBranchTunjanganHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[BranchTunjanganBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data BranchTunjangan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BranchTunjanganBoImpl.saveEdit] Error, not found data BranchTunjangan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data BranchTunjangan with request id, please check again your data ...");
            }
        }
        logger.info("[BranchTunjanganBoImpl.saveEdit] end process <<<");
    }

    @Override
    public BranchTunjangan saveAdd(BranchTunjangan bean) throws GeneralBOException {
        logger.info("[BranchTunjanganBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String branchTunjanganId;
            try {
                // Generating ID, get from postgre sequence
                branchTunjanganId = branchTunjanganDao.getNextBranchTunjanganId();
            } catch (HibernateException e) {
                logger.error("[BranchTunjanganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence branchTunjanganId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImBranchTunjanganEntity imBranchTunjanganEntity = new ImBranchTunjanganEntity();

            imBranchTunjanganEntity.setBranchTunjanganId(branchTunjanganId);
            imBranchTunjanganEntity.setBranchId(bean.getBranchId());
            imBranchTunjanganEntity.setTunjanganId(bean.getTunjanganId());

            imBranchTunjanganEntity.setFlag(bean.getFlag());
            imBranchTunjanganEntity.setAction(bean.getAction());
            imBranchTunjanganEntity.setCreatedWho(bean.getCreatedWho());
            imBranchTunjanganEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imBranchTunjanganEntity.setCreatedDate(bean.getCreatedDate());
            imBranchTunjanganEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                branchTunjanganDao.addAndSave(imBranchTunjanganEntity);
            } catch (HibernateException e) {
                logger.error("[BranchTunjanganBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data BranchTunjangan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[BranchTunjanganBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<BranchTunjangan> getByCriteria(BranchTunjangan searchBean) throws GeneralBOException {
        logger.info("[BranchTunjanganBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<BranchTunjangan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBranchTunjanganId() != null && !"".equalsIgnoreCase(searchBean.getBranchTunjanganId())) {
                hsCriteria.put("branchTunjangan_id", searchBean.getBranchTunjanganId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTunjanganId() != null && !"".equalsIgnoreCase(searchBean.getTunjanganId())) {
                hsCriteria.put("tunjangan_id", searchBean.getTunjanganId());
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


            List<ImBranchTunjanganEntity> imBranchTunjanganEntity = null;
            try {

                imBranchTunjanganEntity = branchTunjanganDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[BranchTunjanganBoImpl.getSearchBranchTunjanganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imBranchTunjanganEntity != null){
                BranchTunjangan returnBranchTunjangan;
                // Looping from dao to object and save in collection
                for(ImBranchTunjanganEntity branchTunjanganEntity : imBranchTunjanganEntity){
                    returnBranchTunjangan = new BranchTunjangan();
                    returnBranchTunjangan.setBranchTunjanganId(branchTunjanganEntity.getBranchTunjanganId());
                    returnBranchTunjangan.setBranchId(branchTunjanganEntity.getBranchId());
                    returnBranchTunjangan.setTunjanganId(branchTunjanganEntity.getTunjanganId());

                    returnBranchTunjangan.setCreatedWho(branchTunjanganEntity.getCreatedWho());
                    returnBranchTunjangan.setCreatedDate(branchTunjanganEntity.getCreatedDate());
                    returnBranchTunjangan.setLastUpdate(branchTunjanganEntity.getLastUpdate());
                    returnBranchTunjangan.setAction(branchTunjanganEntity.getAction());
                    returnBranchTunjangan.setFlag(branchTunjanganEntity.getFlag());

                    listOfResult.add(returnBranchTunjangan);
                }
            }
        }
        logger.info("[BranchTunjanganBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<BranchTunjangan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<BranchTunjangan> getComboBranchTunjanganWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<BranchTunjangan> listComboBranchTunjangan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBranchTunjanganEntity> listBranchTunjangan = null;
        try {
            listBranchTunjangan = branchTunjanganDao.getListBranchTunjangan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listBranchTunjangan != null) {
            for (ImBranchTunjanganEntity imBranchTunjanganEntity : listBranchTunjangan) {
                BranchTunjangan itemComboBranchTunjangan = new BranchTunjangan();
                itemComboBranchTunjangan.setBranchTunjanganId(imBranchTunjanganEntity.getBranchTunjanganId());
                itemComboBranchTunjangan.setBranchId(imBranchTunjanganEntity.getBranchId());
                itemComboBranchTunjangan.setTunjanganId(imBranchTunjanganEntity.getTunjanganId());
                listComboBranchTunjangan.add(itemComboBranchTunjangan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboBranchTunjangan;
    }
}
