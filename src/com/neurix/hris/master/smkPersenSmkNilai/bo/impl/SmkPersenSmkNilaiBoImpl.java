package com.neurix.hris.master.smkPersenSmkNilai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkPersenSmkNilai.bo.SmkPersenSmkNilaiBo;
import com.neurix.hris.master.smkPersenSmkNilai.dao.SmkPersenSmkNilaiDao;
import com.neurix.hris.master.smkPersenSmkNilai.model.ImSmkPersenSmkNilaiEntity;
import com.neurix.hris.master.smkPersenSmkNilai.model.smkPersenSmkNilai;
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
public class SmkPersenSmkNilaiBoImpl implements SmkPersenSmkNilaiBo {

    protected static transient Logger logger = Logger.getLogger(SmkPersenSmkNilaiBoImpl.class);
    private SmkPersenSmkNilaiDao smkPersenSmkNilaiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkPersenSmkNilaiBoImpl.logger = logger;
    }

    public SmkPersenSmkNilaiDao getSmkPersenSmkNilaiDao() {
        return smkPersenSmkNilaiDao;
    }


    public void setSmkPersenSmkNilaiDao(SmkPersenSmkNilaiDao smkPersenSmkNilaiDao) {
        this.smkPersenSmkNilaiDao = smkPersenSmkNilaiDao;
    }

    @Override
    public void saveDelete(smkPersenSmkNilai bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String SmkPersenSmkNilaiId = bean.getSmkNilaiId();

            ImSmkPersenSmkNilaiEntity imSmkPersenSmkNilaiEntity = null;

            try {
                // Get data from database by ID
                imSmkPersenSmkNilaiEntity = smkPersenSmkNilaiDao.getById("umkId", SmkPersenSmkNilaiId);
            } catch (HibernateException e) {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkPersenSmkNilaiEntity != null) {

                // Modify from bean to entity serializable
                imSmkPersenSmkNilaiEntity.setSmkNilaiId(bean.getSmkNilaiId());
                imSmkPersenSmkNilaiEntity.setFlag(bean.getFlag());
                imSmkPersenSmkNilaiEntity.setAction(bean.getAction());
                imSmkPersenSmkNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkPersenSmkNilaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkPersenSmkNilaiDao.updateAndSave(imSmkPersenSmkNilaiEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkPersenSmkNilaiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkPersenSmkNilai, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveDelete] Error, not found data SmkPersenSmkNilai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkPersenSmkNilai with request id, please check again your data ...");

            }
        }
        logger.info("[SmkPersenSmkNilaiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(smkPersenSmkNilai bean) throws GeneralBOException {
        logger.info("[SmkPersenSmkNilaiBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String SmkPersenSmkNilaiId = bean.getSmkNilaiId();

            ImSmkPersenSmkNilaiEntity imSmkPersenSmkNilaiEntity = null;
            //ImSmkPersenSmkNilaiHistoryEntity imSmkPersenSmkNilaiHistoryEntity = new ImSmkPersenSmkNilaiHistoryEntity();
            try {
                // Get data from database by ID
                imSmkPersenSmkNilaiEntity = smkPersenSmkNilaiDao.getById("smkNilaiId", SmkPersenSmkNilaiId);
                //historyId = smkPersenSmkNilaiDao.getNextSmkPersenSmkNilaiHistoryId();
            } catch (HibernateException e) {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkPersenSmkNilai by Kode SmkPersenSmkNilai, please inform to your admin...," + e.getMessage());
            }

            if (imSmkPersenSmkNilaiEntity != null) {
                /*imSmkPersenSmkNilaiHistoryEntity.setId(historyId);
                imSmkPersenSmkNilaiHistoryEntity.setSmkPersenSmkNilaiId(imSmkPersenSmkNilaiEntity.getSmkPersenSmkNilaiId());
                imSmkPersenSmkNilaiHistoryEntity.setSmkPersenSmkNilaiName(imSmkPersenSmkNilaiEntity.getSmkPersenSmkNilaiName());
                imSmkPersenSmkNilaiHistoryEntity.setFlag(imSmkPersenSmkNilaiEntity.getFlag());
                imSmkPersenSmkNilaiHistoryEntity.setAction(imSmkPersenSmkNilaiEntity.getAction());
                imSmkPersenSmkNilaiHistoryEntity.setLastUpdateWho(imSmkPersenSmkNilaiEntity.getLastUpdateWho());
                imSmkPersenSmkNilaiHistoryEntity.setLastUpdate(imSmkPersenSmkNilaiEntity.getLastUpdate());
                imSmkPersenSmkNilaiHistoryEntity.setCreatedWho(imSmkPersenSmkNilaiEntity.getLastUpdateWho());
                imSmkPersenSmkNilaiHistoryEntity.setCreatedDate(imSmkPersenSmkNilaiEntity.getLastUpdate());*/

                imSmkPersenSmkNilaiEntity.setSmkNilaiId(bean.getSmkNilaiId());
                imSmkPersenSmkNilaiEntity.setNama(bean.getNama());
                imSmkPersenSmkNilaiEntity.setBranchId(bean.getBranchId());
                imSmkPersenSmkNilaiEntity.setNilai(bean.getNilai());
                imSmkPersenSmkNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
                imSmkPersenSmkNilaiEntity.setNilaiBawah(bean.getNilaiBawah());

                imSmkPersenSmkNilaiEntity.setNilai(bean.getNilai());
                imSmkPersenSmkNilaiEntity.setFlag(bean.getFlag());
                imSmkPersenSmkNilaiEntity.setAction(bean.getAction());
                imSmkPersenSmkNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkPersenSmkNilaiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkPersenSmkNilaiDao.updateAndSave(imSmkPersenSmkNilaiEntity);
                    //smkPersenSmkNilaiDao.addAndSaveHistory(imSmkPersenSmkNilaiHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[SmkPersenSmkNilaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkPersenSmkNilai, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveEdit] Error, not found data SmkPersenSmkNilai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkPersenSmkNilai with request id, please check again your data ...");
//                condition = "Error, not found data SmkPersenSmkNilai with request id, please check again your data ...";
            }
        }
        logger.info("[SmkPersenSmkNilaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public smkPersenSmkNilai saveAdd(smkPersenSmkNilai bean) throws GeneralBOException {
        logger.info("[SmkPersenSmkNilaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String SmkPersenSmkNilaiId;
            try {
                // Generating ID, get from postgre sequence
                SmkPersenSmkNilaiId = smkPersenSmkNilaiDao.getNextPersenNilaiSmk();
            } catch (HibernateException e) {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence SmkPersenSmkNilaiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkPersenSmkNilaiEntity imSmkPersenSmkNilaiEntity = new ImSmkPersenSmkNilaiEntity();

            imSmkPersenSmkNilaiEntity.setSmkNilaiId(SmkPersenSmkNilaiId);
            imSmkPersenSmkNilaiEntity.setNama(bean.getNama());
            imSmkPersenSmkNilaiEntity.setBranchId(bean.getBranchId());
            imSmkPersenSmkNilaiEntity.setNilai(bean.getNilai());
            imSmkPersenSmkNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
            imSmkPersenSmkNilaiEntity.setNilaiBawah(bean.getNilaiBawah());

            imSmkPersenSmkNilaiEntity.setNilai(bean.getNilai());
            imSmkPersenSmkNilaiEntity.setFlag(bean.getFlag());
            imSmkPersenSmkNilaiEntity.setAction(bean.getAction());
            imSmkPersenSmkNilaiEntity.setCreatedWho(bean.getCreatedWho());
            imSmkPersenSmkNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkPersenSmkNilaiEntity.setCreatedDate(bean.getCreatedDate());
            imSmkPersenSmkNilaiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkPersenSmkNilaiDao.addAndSave(imSmkPersenSmkNilaiEntity);
            } catch (HibernateException e) {
                logger.error("[SmkPersenSmkNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkPersenSmkNilai, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkPersenSmkNilaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<smkPersenSmkNilai> getByCriteria(smkPersenSmkNilai searchBean) throws GeneralBOException {
        logger.info("[SmkPersenSmkNilaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<smkPersenSmkNilai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSmkNilaiId() != null && !"".equalsIgnoreCase(searchBean.getSmkNilaiId())) {
                hsCriteria.put("persen_id", searchBean.getSmkNilaiId());
            }
            if (searchBean.getNama() != null && !"".equalsIgnoreCase(searchBean.getNama())) {
                hsCriteria.put("nama", searchBean.getNama());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getNilaiBawah() != null) {
                hsCriteria.put("nilai_bawah", searchBean.getNilaiBawah());
            }
            if (searchBean.getNilaiAtas() != null) {
                hsCriteria.put("nilai_atas", searchBean.getNilaiAtas());
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


            List<ImSmkPersenSmkNilaiEntity> imSmkPersenSmkNilaiEntity = null;
            try {

                imSmkPersenSmkNilaiEntity = smkPersenSmkNilaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkPersenSmkNilaiBoImpl.getSearchSmkPersenSmkNilaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkPersenSmkNilaiEntity != null){
                smkPersenSmkNilai returnSmkPersenSmkNilai;
                // Looping from dao to object and save in collection
                for(ImSmkPersenSmkNilaiEntity SmkPersenSmkNilaiEntity : imSmkPersenSmkNilaiEntity){
                    returnSmkPersenSmkNilai = new smkPersenSmkNilai();
                    returnSmkPersenSmkNilai.setSmkNilaiId(SmkPersenSmkNilaiEntity.getSmkNilaiId());
                    returnSmkPersenSmkNilai.setBranchId(SmkPersenSmkNilaiEntity.getBranchId());
                    if(SmkPersenSmkNilaiEntity.getBranchId() != null){
                        returnSmkPersenSmkNilai.setBranchName(SmkPersenSmkNilaiEntity.getImBranches().getBranchName());
                    }
                    returnSmkPersenSmkNilai.setNama(SmkPersenSmkNilaiEntity.getNama());
                    returnSmkPersenSmkNilai.setBranchId(SmkPersenSmkNilaiEntity.getBranchId());
                    returnSmkPersenSmkNilai.setNilai(SmkPersenSmkNilaiEntity.getNilai());
                    returnSmkPersenSmkNilai.setNilaiAtas(SmkPersenSmkNilaiEntity.getNilaiAtas());
                    returnSmkPersenSmkNilai.setNilaiBawah(SmkPersenSmkNilaiEntity.getNilaiBawah());
                    
                    returnSmkPersenSmkNilai.setCreatedWho(SmkPersenSmkNilaiEntity.getCreatedWho());
                    returnSmkPersenSmkNilai.setCreatedDate(SmkPersenSmkNilaiEntity.getCreatedDate());
                    returnSmkPersenSmkNilai.setLastUpdate(SmkPersenSmkNilaiEntity.getLastUpdate());

                    returnSmkPersenSmkNilai.setAction(SmkPersenSmkNilaiEntity.getAction());
                    returnSmkPersenSmkNilai.setFlag(SmkPersenSmkNilaiEntity.getFlag());
                    listOfResult.add(returnSmkPersenSmkNilai);
                }
            }
        }
        logger.info("[SmkPersenSmkNilaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<smkPersenSmkNilai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
