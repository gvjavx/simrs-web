package com.neurix.hris.master.smkIndikatorPenilaianCheckList.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.bo.SmkIndikatorPenilaianCheckListBo;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.dao.SmkIndikatorPenilaianCheckListDao;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.ImSmkIndikatorPenilaianCheckListEntity;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;
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
public class SmkIndikatorPenilaianCheckListBoImpl implements SmkIndikatorPenilaianCheckListBo {

    protected static transient Logger logger = Logger.getLogger(SmkIndikatorPenilaianCheckListBoImpl.class);
    private SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorPenilaianCheckListBoImpl.logger = logger;
    }

    public SmkIndikatorPenilaianCheckListDao getSmkIndikatorPenilaianCheckListDao() {
        return smkIndikatorPenilaianCheckListDao;
    }


    public void setSmkIndikatorPenilaianCheckListDao(SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao) {
        this.smkIndikatorPenilaianCheckListDao = smkIndikatorPenilaianCheckListDao;
    }

    @Override
    public void saveDelete(SmkIndikatorPenilaianCheckList bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkIndikatorPenilaianCheckListId = bean.getIndikatorPenilaianCheckListId();

            ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorPenilaianCheckListEntity = null;

            try {
                // Get data from database by ID
                imSmkIndikatorPenilaianCheckListEntity = smkIndikatorPenilaianCheckListDao.getById("indikatorPenilaianCheckListId", smkIndikatorPenilaianCheckListId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorPenilaianCheckListEntity != null) {

                // Modify from bean to entity serializable
                imSmkIndikatorPenilaianCheckListEntity.setIndikatorPenilaianCheckListId(bean.getIndikatorPenilaianCheckListId());
                imSmkIndikatorPenilaianCheckListEntity.setFlag(bean.getFlag());
                imSmkIndikatorPenilaianCheckListEntity.setAction(bean.getAction());
                imSmkIndikatorPenilaianCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorPenilaianCheckListEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkIndikatorPenilaianCheckListDao.updateAndSave(imSmkIndikatorPenilaianCheckListEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikatorPenilaianCheckList, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveDelete] Error, not found data SmkIndikatorPenilaianCheckList with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikatorPenilaianCheckList with request id, please check again your data ...");

            }
        }
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkIndikatorPenilaianCheckList bean) throws GeneralBOException {
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String smkIndikatorPenilaianCheckListId = bean.getIndikatorPenilaianCheckListId();

            ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorPenilaianCheckListEntity = null;
            try {
                // Get data from database by ID
                imSmkIndikatorPenilaianCheckListEntity = smkIndikatorPenilaianCheckListDao.getById("indikatorPenilaianCheckListId", smkIndikatorPenilaianCheckListId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkIndikatorPenilaianCheckList by Kode SmkIndikatorPenilaianCheckList, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorPenilaianCheckListEntity != null) {
                imSmkIndikatorPenilaianCheckListEntity.setIndikatorPenilaianCheckListId(bean.getIndikatorPenilaianCheckListId());
                imSmkIndikatorPenilaianCheckListEntity.setCheckListId(bean.getCheckListId());
                imSmkIndikatorPenilaianCheckListEntity.setIndikatorName(bean.getIndikatorName());
                imSmkIndikatorPenilaianCheckListEntity.setNilai(bean.getNilai());

                imSmkIndikatorPenilaianCheckListEntity.setFlag(bean.getFlag());
                imSmkIndikatorPenilaianCheckListEntity.setAction(bean.getAction());
                imSmkIndikatorPenilaianCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorPenilaianCheckListEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkIndikatorPenilaianCheckListDao.updateAndSave(imSmkIndikatorPenilaianCheckListEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikatorPenilaianCheckList, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveEdit] Error, not found data SmkIndikatorPenilaianCheckList with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikatorPenilaianCheckList with request id, please check again your data ...");
            }
        }
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkIndikatorPenilaianCheckList saveAdd(SmkIndikatorPenilaianCheckList bean) throws GeneralBOException {
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkIndikatorPenilaianCheckListId;
            try {
                // Generating ID, get from postgre sequence
                smkIndikatorPenilaianCheckListId = smkIndikatorPenilaianCheckListDao.getNextSmkIndikatorPenilaianCheckListId();
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkIndikatorPenilaianCheckListId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorPenilaianCheckListEntity = new ImSmkIndikatorPenilaianCheckListEntity();

            imSmkIndikatorPenilaianCheckListEntity.setIndikatorPenilaianCheckListId(smkIndikatorPenilaianCheckListId);
            imSmkIndikatorPenilaianCheckListEntity.setCheckListId(bean.getCheckListId());
            imSmkIndikatorPenilaianCheckListEntity.setIndikatorName(bean.getIndikatorName());
            imSmkIndikatorPenilaianCheckListEntity.setNilai(bean.getNilai());
            imSmkIndikatorPenilaianCheckListEntity.setFlag(bean.getFlag());
            imSmkIndikatorPenilaianCheckListEntity.setAction(bean.getAction());
            imSmkIndikatorPenilaianCheckListEntity.setCreatedWho(bean.getCreatedWho());
            imSmkIndikatorPenilaianCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkIndikatorPenilaianCheckListEntity.setCreatedDate(bean.getCreatedDate());
            imSmkIndikatorPenilaianCheckListEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkIndikatorPenilaianCheckListDao.addAndSave(imSmkIndikatorPenilaianCheckListEntity);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkIndikatorPenilaianCheckList, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkIndikatorPenilaianCheckList> getByCriteria(SmkIndikatorPenilaianCheckList searchBean) throws GeneralBOException {
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkIndikatorPenilaianCheckList> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIndikatorPenilaianCheckListId() != null && !"".equalsIgnoreCase(searchBean.getIndikatorPenilaianCheckListId())) {
                hsCriteria.put("indikatorPenilaianCheckListId", searchBean.getIndikatorPenilaianCheckListId());
            }
            if (searchBean.getCheckListId() != null && !"".equalsIgnoreCase(searchBean.getCheckListId())) {
                hsCriteria.put("checkListId", searchBean.getCheckListId());
            }
            if (searchBean.getIndikatorName() != null && !"".equalsIgnoreCase(searchBean.getIndikatorName())) {
                hsCriteria.put("indikatorName", searchBean.getIndikatorName());
            }
            if (searchBean.getNilai() != null && !"".equalsIgnoreCase(searchBean.getIndikatorName())) {
                hsCriteria.put("nilai", searchBean.getIndikatorName());
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


            List<ImSmkIndikatorPenilaianCheckListEntity> imSmkIndikatorPenilaianCheckListEntity = null;
            try {

                imSmkIndikatorPenilaianCheckListEntity = smkIndikatorPenilaianCheckListDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorPenilaianCheckListBoImpl.getSearchSmkIndikatorPenilaianCheckListByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkIndikatorPenilaianCheckListEntity != null){
                SmkIndikatorPenilaianCheckList returnSmkIndikatorPenilaianCheckList;
                // Looping from dao to object and save in collection
                for(ImSmkIndikatorPenilaianCheckListEntity smkIndikatorPenilaianCheckListEntity : imSmkIndikatorPenilaianCheckListEntity){
                    returnSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
                    returnSmkIndikatorPenilaianCheckList.setIndikatorPenilaianCheckListId(smkIndikatorPenilaianCheckListEntity.getIndikatorPenilaianCheckListId());
                    returnSmkIndikatorPenilaianCheckList.setCheckListId(smkIndikatorPenilaianCheckListEntity.getCheckListId());
                    returnSmkIndikatorPenilaianCheckList.setCheckListName(smkIndikatorPenilaianCheckListEntity.getImSmkCheckListEntity().getCheckListName());
                    returnSmkIndikatorPenilaianCheckList.setIndikatorName(smkIndikatorPenilaianCheckListEntity.getIndikatorName());
                    returnSmkIndikatorPenilaianCheckList.setNilai(smkIndikatorPenilaianCheckListEntity.getNilai());

                    returnSmkIndikatorPenilaianCheckList.setCreatedWho(smkIndikatorPenilaianCheckListEntity.getCreatedWho());
                    returnSmkIndikatorPenilaianCheckList.setCreatedDate(smkIndikatorPenilaianCheckListEntity.getCreatedDate());
                    returnSmkIndikatorPenilaianCheckList.setLastUpdate(smkIndikatorPenilaianCheckListEntity.getLastUpdate());

                    returnSmkIndikatorPenilaianCheckList.setAction(smkIndikatorPenilaianCheckListEntity.getAction());
                    returnSmkIndikatorPenilaianCheckList.setFlag(smkIndikatorPenilaianCheckListEntity.getFlag());
                    listOfResult.add(returnSmkIndikatorPenilaianCheckList);
                }
            }
        }
        logger.info("[SmkIndikatorPenilaianCheckListBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkIndikatorPenilaianCheckList> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

}
