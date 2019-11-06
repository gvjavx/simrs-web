package com.neurix.hris.master.pelatihanJabatan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.pelatihanJabatan.bo.PelatihanJabatanBo;
import com.neurix.hris.master.pelatihanJabatan.dao.PelatihanJabatanDao;
import com.neurix.hris.master.pelatihanJabatan.model.ImPelatihanJabatanEntitiy;
import com.neurix.hris.master.pelatihanJabatan.model.PelatihanJabatan;
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
public class PelatihanJabatanBoImpl implements PelatihanJabatanBo {

    protected static transient Logger logger = Logger.getLogger(PelatihanJabatanBoImpl.class);
    private PelatihanJabatanDao pelatihanJabatanDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PelatihanJabatanBoImpl.logger = logger;
    }

    public PelatihanJabatanDao getPelatihanJabatanDao() {
        return pelatihanJabatanDao;
    }

    public void setPelatihanJabatanDao(PelatihanJabatanDao pelatihanJabatanDao) {
        this.pelatihanJabatanDao = pelatihanJabatanDao;
    }

    @Override
    public void saveDelete(PelatihanJabatan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String pelatihanJabatanId = bean.getPelatihanId();

            ImPelatihanJabatanEntitiy imPelatihanJabatanEntity = null;

            try {
                // Get data from database by ID
                imPelatihanJabatanEntity = pelatihanJabatanDao.getById("pelatihanId", pelatihanJabatanId);
            } catch (HibernateException e) {
                logger.error("[PelatihanJabatanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPelatihanJabatanEntity != null) {

                // Modify from bean to entity serializable
                imPelatihanJabatanEntity.setPelatihanId(bean.getPelatihanId());
                imPelatihanJabatanEntity.setPelatihanName(bean.getPelatihanName());
                imPelatihanJabatanEntity.setFlag(bean.getFlag());
                imPelatihanJabatanEntity.setAction(bean.getAction());
                imPelatihanJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPelatihanJabatanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    pelatihanJabatanDao.updateAndSave(imPelatihanJabatanEntity);
                } catch (HibernateException e) {
                    logger.error("[PelatihanJabatanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PelatihanJabatan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PelatihanJabatanBoImpl.saveDelete] Error, not found data PelatihanJabatan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PelatihanJabatan with request id, please check again your data ...");

            }
        }
        logger.info("[PelatihanJabatanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PelatihanJabatan bean) throws GeneralBOException {
        logger.info("[PelatihanJabatanBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String pelatihanJabatanId = bean.getPelatihanId();
            String idHistory = "";
            ImPelatihanJabatanEntitiy imPelatihanJabatanEntity = null;
            try {
                // Get data from database by ID
                imPelatihanJabatanEntity = pelatihanJabatanDao.getById("pelatihanId", pelatihanJabatanId);
            } catch (HibernateException e) {
                logger.error("[PelatihanJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PelatihanJabatan by Kode PelatihanJabatan, please inform to your admin...," + e.getMessage());
            }

            if (imPelatihanJabatanEntity != null) {
                imPelatihanJabatanEntity.setPelatihanId(bean.getPelatihanId());
                imPelatihanJabatanEntity.setPelatihanName(bean.getPelatihanName());
                imPelatihanJabatanEntity.setFlag(bean.getFlag());
                imPelatihanJabatanEntity.setAction(bean.getAction());
                imPelatihanJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPelatihanJabatanEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    pelatihanJabatanDao.updateAndSave(imPelatihanJabatanEntity);
                } catch (HibernateException e) {
                    logger.error("[PelatihanJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PelatihanJabatan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PelatihanJabatanBoImpl.saveEdit] Error, not found data PelatihanJabatan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PelatihanJabatan with request id, please check again your data ...");
//                condition = "Error, not found data PelatihanJabatan with request id, please check again your data ...";
            }
        }
        logger.info("[PelatihanJabatanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PelatihanJabatan saveAdd(PelatihanJabatan bean) throws GeneralBOException {
        logger.info("[PelatihanJabatanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String pelatihanJabatanId;
            try {
                // Generating ID, get from postgre sequence
                pelatihanJabatanId = pelatihanJabatanDao.getNextPelatihanJabatanId();
            } catch (HibernateException e) {
                logger.error("[PelatihanJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence pelatihanJabatanId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPelatihanJabatanEntitiy imPelatihanJabatanEntity = new ImPelatihanJabatanEntitiy();

            imPelatihanJabatanEntity.setPelatihanId(pelatihanJabatanId);
            imPelatihanJabatanEntity.setPelatihanName(bean.getPelatihanName());
            imPelatihanJabatanEntity.setFlag(bean.getFlag());
            imPelatihanJabatanEntity.setAction(bean.getAction());
            imPelatihanJabatanEntity.setCreatedWho(bean.getCreatedWho());
            imPelatihanJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPelatihanJabatanEntity.setCreatedDate(bean.getCreatedDate());
            imPelatihanJabatanEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                pelatihanJabatanDao.addAndSave(imPelatihanJabatanEntity);
            } catch (HibernateException e) {
                logger.error("[PelatihanJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PelatihanJabatan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PelatihanJabatanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PelatihanJabatan> getByCriteria(PelatihanJabatan searchBean) throws GeneralBOException {
        logger.info("[PelatihanJabatanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PelatihanJabatan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPelatihanId() != null && !"".equalsIgnoreCase(searchBean.getPelatihanId())) {
                hsCriteria.put("kelompok_id", searchBean.getPelatihanId());
            }
            if (searchBean.getPelatihanName() != null && !"".equalsIgnoreCase(searchBean.getPelatihanName())) {
                hsCriteria.put("kelompok_name", searchBean.getPelatihanName());
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


            List<ImPelatihanJabatanEntitiy> imPelatihanJabatanEntity = null;
            try {

                imPelatihanJabatanEntity = pelatihanJabatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PelatihanJabatanBoImpl.getSearchPelatihanJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPelatihanJabatanEntity != null){
                PelatihanJabatan returnPelatihanJabatan;
                // Looping from dao to object and save in collection
                for(ImPelatihanJabatanEntitiy pelatihanJabatanEntity : imPelatihanJabatanEntity){
                    returnPelatihanJabatan = new PelatihanJabatan();
                    returnPelatihanJabatan.setPelatihanId(pelatihanJabatanEntity.getPelatihanId());
                    returnPelatihanJabatan.setPelatihanName(pelatihanJabatanEntity.getPelatihanName());

                    returnPelatihanJabatan.setCreatedWho(pelatihanJabatanEntity.getCreatedWho());
                    returnPelatihanJabatan.setCreatedDate(pelatihanJabatanEntity.getCreatedDate());
                    returnPelatihanJabatan.setLastUpdate(pelatihanJabatanEntity.getLastUpdate());

                    returnPelatihanJabatan.setAction(pelatihanJabatanEntity.getAction());
                    returnPelatihanJabatan.setFlag(pelatihanJabatanEntity.getFlag());
                    listOfResult.add(returnPelatihanJabatan);
                }
            }
        }
        logger.info("[PelatihanJabatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PelatihanJabatan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PelatihanJabatan> getComboKelompokWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<PelatihanJabatan> listComboPelatihanJabatan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImPelatihanJabatanEntitiy> listPelatihanJabatan = null;
        try {
            listPelatihanJabatan = pelatihanJabatanDao.getListPelatihanJabatan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPelatihanJabatan != null) {
            for (ImPelatihanJabatanEntitiy imPelatihanJabatanEntity : listPelatihanJabatan) {
                PelatihanJabatan itemComboPelatihanJabatan = new PelatihanJabatan();
                itemComboPelatihanJabatan.setPelatihanId(imPelatihanJabatanEntity.getPelatihanId());
                itemComboPelatihanJabatan.setPelatihanName(imPelatihanJabatanEntity.getPelatihanName());
                listComboPelatihanJabatan.add(itemComboPelatihanJabatan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboPelatihanJabatan;
    }
}
