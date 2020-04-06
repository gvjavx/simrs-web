package com.neurix.hris.master.kelompokPosition.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.kelompokPosition.bo.KelompokPositionBo;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionHistoryEntity;
import com.neurix.hris.master.kelompokPosition.model.KelompokPosition;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
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
public class KelompokPositionBoImpl implements KelompokPositionBo {

    protected static transient Logger logger = Logger.getLogger(KelompokPositionBoImpl.class);
    private KelompokPositionDao kelompokPositionDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KelompokPositionBoImpl.logger = logger;
    }

    public KelompokPositionDao getKelompokPositionDao() {
        return kelompokPositionDao;
    }

    public void setKelompokPositionDao(KelompokPositionDao kelompokPositionDao) {
        this.kelompokPositionDao = kelompokPositionDao;
    }

    @Override
    public void saveDelete(KelompokPosition bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String kelompokPositionId = bean.getKelompokId();

            ImKelompokPositionEntity imKelompokPositionEntity = null;

            try {
                // Get data from database by ID
                imKelompokPositionEntity = kelompokPositionDao.getById("kelompokId", kelompokPositionId);
            } catch (HibernateException e) {
                logger.error("[KelompokPositionBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imKelompokPositionEntity != null) {

                // Modify from bean to entity serializable
                imKelompokPositionEntity.setKelompokId(bean.getKelompokId());
                imKelompokPositionEntity.setKelompokName(bean.getKelompokName());
                imKelompokPositionEntity.setFlag(bean.getFlag());
                imKelompokPositionEntity.setAction(bean.getAction());
                imKelompokPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imKelompokPositionEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kelompokPositionDao.updateAndSave(imKelompokPositionEntity);
                } catch (HibernateException e) {
                    logger.error("[KelompokPositionBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data KelompokPosition, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[KelompokPositionBoImpl.saveDelete] Error, not found data KelompokPosition with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data KelompokPosition with request id, please check again your data ...");

            }
        }
        logger.info("[KelompokPositionBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(KelompokPosition bean) throws GeneralBOException {
        logger.info("[KelompokPositionBoImpl.saveEdit] start process >>>");

//        String condition = null;
        if (bean!=null) {

            String status = cekStatus(bean.getKelompokName());
            if (!status.equalsIgnoreCase("exist")){

                String kelompokPositionId = bean.getKelompokId();
                String idHistory = "";
                ImKelompokPositionEntity imKelompokPositionEntity = null;
                ImKelompokPositionHistoryEntity imKelompokPositionHistoryEntity = new ImKelompokPositionHistoryEntity();
                try {
                    // Get data from database by ID
                    imKelompokPositionEntity = kelompokPositionDao.getById("kelompokId", kelompokPositionId);
                    idHistory = kelompokPositionDao.getNextKelompokPositionHistoryId();
                } catch (HibernateException e) {
                    logger.error("[KelompokPositionBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data KelompokPosition by Kode KelompokPosition, please inform to your admin...," + e.getMessage());
                }

                if (imKelompokPositionEntity != null) {
                    imKelompokPositionHistoryEntity.setId(idHistory);
                    imKelompokPositionHistoryEntity.setKelompokId(imKelompokPositionEntity.getKelompokId());
                    imKelompokPositionHistoryEntity.setKelompokName(imKelompokPositionEntity.getKelompokName());
                    imKelompokPositionHistoryEntity.setFlag(imKelompokPositionEntity.getFlag());
                    imKelompokPositionHistoryEntity.setAction(imKelompokPositionEntity.getAction());
                    imKelompokPositionHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imKelompokPositionHistoryEntity.setLastUpdate(bean.getLastUpdate());
                    imKelompokPositionHistoryEntity.setCreatedWho(imKelompokPositionEntity.getCreatedWho());
                    imKelompokPositionHistoryEntity.setCreatedDate(imKelompokPositionEntity.getCreatedDate());

                    imKelompokPositionEntity.setKelompokId(bean.getKelompokId());
                    imKelompokPositionEntity.setKelompokName(bean.getKelompokName());
                    imKelompokPositionEntity.setFlag(bean.getFlag());
                    imKelompokPositionEntity.setAction(bean.getAction());
                    imKelompokPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imKelompokPositionEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        kelompokPositionDao.updateAndSave(imKelompokPositionEntity);
                        kelompokPositionDao.addAndSaveHistory(imKelompokPositionHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[KelompokPositionBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data KelompokPosition, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[KelompokPositionBoImpl.saveEdit] Error, not found data KelompokPosition with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data KelompokPosition with request id, please check again your data ...");
//                condition = "Error, not found data KelompokPosition with request id, please check again your data ...";
                }

            }else {
                throw new GeneralBOException("Maaf Data Tersebut Sudah ada");
            }
        }
        logger.info("[KelompokPositionBoImpl.saveEdit] end process <<<");
    }

    @Override
    public KelompokPosition saveAdd(KelompokPosition bean) throws GeneralBOException {
        logger.info("[KelompokPositionBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getKelompokName());
            if (!status.equalsIgnoreCase("Exist")){
                String kelompokPositionId;
                try {
                    // Generating ID, get from postgre sequence
                    kelompokPositionId = kelompokPositionDao.getNextKelompokPositionId();
                } catch (HibernateException e) {
                    logger.error("[KelompokPositionBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence kelompokPositionId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImKelompokPositionEntity imKelompokPositionEntity = new ImKelompokPositionEntity();

                imKelompokPositionEntity.setKelompokId(kelompokPositionId);
                imKelompokPositionEntity.setKelompokName(bean.getKelompokName());
                imKelompokPositionEntity.setFlag(bean.getFlag());
                imKelompokPositionEntity.setAction(bean.getAction());
                imKelompokPositionEntity.setCreatedWho(bean.getCreatedWho());
                imKelompokPositionEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imKelompokPositionEntity.setCreatedDate(bean.getCreatedDate());
                imKelompokPositionEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    kelompokPositionDao.addAndSave(imKelompokPositionEntity);
                } catch (HibernateException e) {
                    logger.error("[KelompokPositionBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data KelompokPosition, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data Tersebut Sudah ada");
            }

        }

        logger.info("[KelompokPositionBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<KelompokPosition> getByCriteria(KelompokPosition searchBean) throws GeneralBOException {
        logger.info("[KelompokPositionBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<KelompokPosition> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKelompokId() != null && !"".equalsIgnoreCase(searchBean.getKelompokId())) {
                hsCriteria.put("kelompok_id", searchBean.getKelompokId());
            }
            if (searchBean.getKelompokName() != null && !"".equalsIgnoreCase(searchBean.getKelompokName())) {
                hsCriteria.put("kelompok_name", searchBean.getKelompokName());
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


            List<ImKelompokPositionEntity> imKelompokPositionEntity = null;
            try {

                imKelompokPositionEntity = kelompokPositionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KelompokPositionBoImpl.getSearchKelompokPositionByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imKelompokPositionEntity != null){
                KelompokPosition returnKelompokPosition;
                // Looping from dao to object and save in collection
                for(ImKelompokPositionEntity kelompokPositionEntity : imKelompokPositionEntity){
                    returnKelompokPosition = new KelompokPosition();
                    returnKelompokPosition.setKelompokId(kelompokPositionEntity.getKelompokId());
                    returnKelompokPosition.setKelompokName(kelompokPositionEntity.getKelompokName());

                    returnKelompokPosition.setCreatedWho(kelompokPositionEntity.getCreatedWho());
                    returnKelompokPosition.setCreatedDate(kelompokPositionEntity.getCreatedDate());
                    returnKelompokPosition.setLastUpdate(kelompokPositionEntity.getLastUpdate());
                    returnKelompokPosition.setLastUpdateWho(kelompokPositionEntity.getLastUpdateWho());

                    returnKelompokPosition.setAction(kelompokPositionEntity.getAction());
                    returnKelompokPosition.setFlag(kelompokPositionEntity.getFlag());
                    listOfResult.add(returnKelompokPosition);
                }
            }
        }
        logger.info("[KelompokPositionBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<KelompokPosition> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<KelompokPosition> getComboKelompokWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<KelompokPosition> listComboKelompokPosition = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImKelompokPositionEntity> listKelompokPosition = null;
        try {
            listKelompokPosition = kelompokPositionDao.getListKelompokPosition(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listKelompokPosition != null) {
            for (ImKelompokPositionEntity imKelompokPositionEntity : listKelompokPosition) {
                KelompokPosition itemComboKelompokPosition = new KelompokPosition();
                itemComboKelompokPosition.setKelompokId(imKelompokPositionEntity.getKelompokId());
                itemComboKelompokPosition.setKelompokName(imKelompokPositionEntity.getKelompokName());
                listComboKelompokPosition.add(itemComboKelompokPosition);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboKelompokPosition;
    }
    public String cekStatus(String golonganId)throws GeneralBOException{
        String status ="";
        List<ImKelompokPositionEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = kelompokPositionDao.getListKelompokPosition(golonganId);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}
