package com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.SmkIndikatorPenilaianAspekBo;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
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
public class SmkIndikatorPenilaianAspekBoImpl implements SmkIndikatorPenilaianAspekBo {

    protected static transient Logger logger = Logger.getLogger(SmkIndikatorPenilaianAspekBoImpl.class);
    private SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorPenilaianAspekBoImpl.logger = logger;
    }

    public SmkIndikatorPenilaianAspekDao getSmkIndikatorPenilaianAspekDao() {
        return smkIndikatorPenilaianAspekDao;
    }

    public void setSmkIndikatorPenilaianAspekDao(SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao) {
        this.smkIndikatorPenilaianAspekDao = smkIndikatorPenilaianAspekDao;
    }

    @Override
    public void saveDelete(SmkIndikatorPenilaianAspek bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkIndikatorId = bean.getIndikatorPenilaianAspekId();

            ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorEntity = null;

            try {
                // Get data from database by ID
                imSmkIndikatorEntity = smkIndikatorPenilaianAspekDao.getById("indikatorPenilaianAspekId", smkIndikatorId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorEntity != null) {

                imSmkIndikatorEntity.setIndikatorPenilaianAspekId(bean.getIndikatorPenilaianAspekId());
                imSmkIndikatorEntity.setFlag(bean.getFlag());
                imSmkIndikatorEntity.setAction(bean.getAction());
                imSmkIndikatorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    smkIndikatorPenilaianAspekDao.updateAndSave(imSmkIndikatorEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikator, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkIndikatorBoImpl.saveDelete] Error, not found data SmkIndikator with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikator with request id, please check again your data ...");

            }
        }
        logger.info("[SmkIndikatorBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkIndikatorPenilaianAspek bean) throws GeneralBOException {
        logger.info("[SmkIndikatorBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String smkIndikatorId = bean.getIndikatorPenilaianAspekId();

            ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorEntity = null;
            try {
                // Get data from database by ID
                imSmkIndikatorEntity = smkIndikatorPenilaianAspekDao.getById("indikatorPenilaianAspekId", smkIndikatorId);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkIndikator by Kode SmkIndikator, please inform to your admin...," + e.getMessage());
            }

            if (imSmkIndikatorEntity != null) {
                imSmkIndikatorEntity.setIndikatorPenilaianAspekId(bean.getIndikatorPenilaianAspekId());

                imSmkIndikatorEntity.setIndikatorName(bean.getIndikatorName());
                imSmkIndikatorEntity.setNilai(bean.getNilai());
                imSmkIndikatorEntity.setFlag(bean.getFlag());
                imSmkIndikatorEntity.setAction(bean.getAction());
                imSmkIndikatorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkIndikatorEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkIndikatorPenilaianAspekDao.updateAndSave(imSmkIndikatorEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkIndikator, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkIndikatorBoImpl.saveEdit] Error, not found data SmkIndikator with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkIndikator with request id, please check again your data ...");
            }
        }
        logger.info("[SmkIndikatorBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkIndikatorPenilaianAspek saveAdd(SmkIndikatorPenilaianAspek bean) throws GeneralBOException {
        logger.info("[SmkIndikatorBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkIndikatorId;
            try {
                // Generating ID, get from postgre sequence
                smkIndikatorId = smkIndikatorPenilaianAspekDao.getNextSmkIndikatorId();
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkIndikatorId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorEntity = new ImSmkIndikatorPenilaianAspekEntity();

            imSmkIndikatorEntity.setIndikatorPenilaianAspekId(smkIndikatorId);
            imSmkIndikatorEntity.setIndikatorName(bean.getIndikatorName());
            imSmkIndikatorEntity.setKategoriAspekId(bean.getKategoriAspekId());
            imSmkIndikatorEntity.setNilai(bean.getNilai());
            imSmkIndikatorEntity.setFlag(bean.getFlag());
            imSmkIndikatorEntity.setAction(bean.getAction());
            imSmkIndikatorEntity.setCreatedWho(bean.getCreatedWho());
            imSmkIndikatorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkIndikatorEntity.setCreatedDate(bean.getCreatedDate());
            imSmkIndikatorEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkIndikatorPenilaianAspekDao.addAndSave(imSmkIndikatorEntity);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkIndikator, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkIndikatorBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkIndikatorPenilaianAspek> getByCriteria(SmkIndikatorPenilaianAspek searchBean) throws GeneralBOException {
        logger.info("[SmkIndikatorBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkIndikatorPenilaianAspek> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getIndikatorPenilaianAspekId() != null && !"".equalsIgnoreCase(searchBean.getIndikatorPenilaianAspekId())) {
                hsCriteria.put("indikatorPenilaianAspekId", searchBean.getIndikatorPenilaianAspekId());
            }
            if (searchBean.getKategoriAspekId() != null && !"".equalsIgnoreCase(searchBean.getKategoriAspekId())) {
                hsCriteria.put("kategoriAspekId", searchBean.getKategoriAspekId());
            }
            if (searchBean.getIndikatorName() != null && !"".equalsIgnoreCase(searchBean.getIndikatorName())) {
                hsCriteria.put("indikatorName", searchBean.getIndikatorName());
            }
            if (searchBean.getNilai() != null) {
                hsCriteria.put("nilai", searchBean.getNilai());
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


            List<ImSmkIndikatorPenilaianAspekEntity> imSmkIndikatorEntity = null;
            try {

                imSmkIndikatorEntity = smkIndikatorPenilaianAspekDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkIndikatorBoImpl.getSearchSmkIndikatorByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkIndikatorEntity != null){
                SmkIndikatorPenilaianAspek returnSmkIndikator;
                // Looping from dao to object and save in collection
                for(ImSmkIndikatorPenilaianAspekEntity smkIndikatorEntity : imSmkIndikatorEntity){
                    returnSmkIndikator = new SmkIndikatorPenilaianAspek();
                    returnSmkIndikator.setIndikatorPenilaianAspekId(smkIndikatorEntity.getIndikatorPenilaianAspekId());
                    returnSmkIndikator.setKategoriAspekId(smkIndikatorEntity.getKategoriAspekId());
                    returnSmkIndikator.setKategoriAspekName(smkIndikatorEntity.getImSmkKategoriAspekEntity().getKategoriName());
                    returnSmkIndikator.setIndikatorName(smkIndikatorEntity.getIndikatorName());
                    returnSmkIndikator.setBobot(smkIndikatorEntity.getBobot());

                    returnSmkIndikator.setCreatedWho(smkIndikatorEntity.getCreatedWho());
                    returnSmkIndikator.setCreatedDate(smkIndikatorEntity.getCreatedDate());
                    returnSmkIndikator.setLastUpdate(smkIndikatorEntity.getLastUpdate());

                    returnSmkIndikator.setAction(smkIndikatorEntity.getAction());
                    returnSmkIndikator.setFlag(smkIndikatorEntity.getFlag());
                    listOfResult.add(returnSmkIndikator);
                }
            }
        }
        logger.info("[SmkIndikatorBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkIndikatorPenilaianAspek> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    
}
