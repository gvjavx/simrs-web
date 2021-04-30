package com.neurix.hris.master.smkSkalaNilai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaNilai.bo.SmkSkalaNilaiBo;
import com.neurix.hris.master.smkSkalaNilai.dao.SmkSkalaNilaiDao;
import com.neurix.hris.master.smkSkalaNilai.model.SmkSkalaNilai;
import com.neurix.hris.master.smkSkalaNilai.model.ImSmkSkalaNilaiEntity;
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
public class SmkSkalaNilaiBoImpl implements SmkSkalaNilaiBo {

    protected static transient Logger logger = Logger.getLogger(SmkSkalaNilaiBoImpl.class);
    private SmkSkalaNilaiDao smkSkalaNilaiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaNilaiBoImpl.logger = logger;
    }

    public SmkSkalaNilaiDao getSmkSkalaNilaiDao() {
        return smkSkalaNilaiDao;
    }


    public void setSmkSkalaNilaiDao(SmkSkalaNilaiDao smkSkalaNilaiDao) {
        this.smkSkalaNilaiDao = smkSkalaNilaiDao;
    }

    @Override
    public void saveDelete(SmkSkalaNilai bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiId = bean.getSkalaNilaiId();

            ImSmkSkalaNilaiEntity imSmkSkalaNilaiEntity = null;

            try {
                // Get data from database by ID
                imSmkSkalaNilaiEntity = smkSkalaNilaiDao.getById("skalaNilaiId", smkSkalaNilaiId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiEntity != null) {

                // Modify from bean to entity serializable
                imSmkSkalaNilaiEntity.setSkalaNilaiId(bean.getSkalaNilaiId());
                imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiEntity.setAction(bean.getAction());
                imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkSkalaNilaiDao.updateAndSave(imSmkSkalaNilaiEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkSkalaNilaiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkSkalaNilai, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkSkalaNilaiBoImpl.saveDelete] Error, not found data SmkSkalaNilai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkSkalaNilai with request id, please check again your data ...");

            }
        }
        logger.info("[SmkSkalaNilaiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkSkalaNilai bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String smkSkalaNilaiId = bean.getSkalaNilaiId();

            ImSmkSkalaNilaiEntity imSmkSkalaNilaiEntity = null;
            try {
                // Get data from database by ID
                imSmkSkalaNilaiEntity = smkSkalaNilaiDao.getById("skalaNilaiId", smkSkalaNilaiId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkSkalaNilai by Kode SmkSkalaNilai, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiEntity != null) {

                imSmkSkalaNilaiEntity.setSkalaNilaiId(bean.getSkalaNilaiId());
                imSmkSkalaNilaiEntity.setSkalaName(bean.getSkalaName());
                imSmkSkalaNilaiEntity.setKodeSkala(bean.getKodeSkala());
                imSmkSkalaNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
                imSmkSkalaNilaiEntity.setNilaiBawah(bean.getNilaiBawah());
                imSmkSkalaNilaiEntity.setBranchId(bean.getBranchId());
                imSmkSkalaNilaiEntity.setPoin(bean.getPoin());

                imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiEntity.setAction(bean.getAction());
                imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkSkalaNilaiDao.updateAndSave(imSmkSkalaNilaiEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkSkalaNilaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkSkalaNilai, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkSkalaNilaiBoImpl.saveEdit] Error, not found data SmkSkalaNilai with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkSkalaNilai with request id, please check again your data ...");
//                condition = "Error, not found data SmkSkalaNilai with request id, please check again your data ...";
            }
        }
        logger.info("[SmkSkalaNilaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkSkalaNilai saveAdd(SmkSkalaNilai bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiId;
            try {
                // Generating ID, get from postgre sequence
                smkSkalaNilaiId = smkSkalaNilaiDao.getNextSmkSkalaNilaiId();
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkSkalaNilaiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkSkalaNilaiEntity imSmkSkalaNilaiEntity = new ImSmkSkalaNilaiEntity();

            imSmkSkalaNilaiEntity.setSkalaNilaiId(smkSkalaNilaiId);
            imSmkSkalaNilaiEntity.setSkalaName(bean.getSkalaName());
            imSmkSkalaNilaiEntity.setKodeSkala(bean.getKodeSkala());
            imSmkSkalaNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
            imSmkSkalaNilaiEntity.setNilaiBawah(bean.getNilaiBawah());
            imSmkSkalaNilaiEntity.setBranchId(bean.getBranchId());
            imSmkSkalaNilaiEntity.setPoin(bean.getPoin());

            imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
            imSmkSkalaNilaiEntity.setAction(bean.getAction());
            imSmkSkalaNilaiEntity.setCreatedWho(bean.getCreatedWho());
            imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkSkalaNilaiEntity.setCreatedDate(bean.getCreatedDate());
            imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkSkalaNilaiDao.addAndSave(imSmkSkalaNilaiEntity);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkSkalaNilai, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkSkalaNilaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkSkalaNilai> getByCriteria(SmkSkalaNilai searchBean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkSkalaNilai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaNilaiId() != null && !"".equalsIgnoreCase(searchBean.getSkalaNilaiId())) {
                hsCriteria.put("skalaNilaiId", searchBean.getSkalaNilaiId());
            }
            if (searchBean.getSkalaName() != null && !"".equalsIgnoreCase(searchBean.getSkalaName())) {
                hsCriteria.put("skalaName", searchBean.getSkalaName());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
            }
            if (searchBean.getKodeSkala() != null && !"".equalsIgnoreCase(searchBean.getKodeSkala())) {
                hsCriteria.put("kodeSkala", searchBean.getKodeSkala());
            }
            if (searchBean.getNilaiAtas() != 0) {
                hsCriteria.put("nilaiAtas", searchBean.getNilaiAtas());
            }
            if (searchBean.getNilaiBawah() != 0) {
                hsCriteria.put("nilaiBawah", searchBean.getNilaiBawah());
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


            List<ImSmkSkalaNilaiEntity> imSmkSkalaNilaiEntity = null;
            try {

                imSmkSkalaNilaiEntity = smkSkalaNilaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.getSearchSmkSkalaNilaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkSkalaNilaiEntity != null){
                SmkSkalaNilai returnSmkSkalaNilai;
                // Looping from dao to object and save in collection
                for(ImSmkSkalaNilaiEntity smkSkalaNilaiEntity : imSmkSkalaNilaiEntity){
                    returnSmkSkalaNilai = new SmkSkalaNilai();
                    returnSmkSkalaNilai.setSkalaNilaiId(smkSkalaNilaiEntity.getSkalaNilaiId());
                    returnSmkSkalaNilai.setSkalaName(smkSkalaNilaiEntity.getSkalaName());
                    returnSmkSkalaNilai.setKodeSkala(smkSkalaNilaiEntity.getKodeSkala());
                    returnSmkSkalaNilai.setNilaiAtas(smkSkalaNilaiEntity.getNilaiAtas());
                    returnSmkSkalaNilai.setNilaiBawah(smkSkalaNilaiEntity.getNilaiBawah());
                    returnSmkSkalaNilai.setBranchId(smkSkalaNilaiEntity.getBranchId());
                    returnSmkSkalaNilai.setBranchName(smkSkalaNilaiEntity.getImBranches().getBranchName());
                    returnSmkSkalaNilai.setPoin(smkSkalaNilaiEntity.getPoin());

                    returnSmkSkalaNilai.setCreatedWho(smkSkalaNilaiEntity.getCreatedWho());
                    returnSmkSkalaNilai.setCreatedDate(smkSkalaNilaiEntity.getCreatedDate());
                    returnSmkSkalaNilai.setLastUpdate(smkSkalaNilaiEntity.getLastUpdate());

                    returnSmkSkalaNilai.setAction(smkSkalaNilaiEntity.getAction());
                    returnSmkSkalaNilai.setFlag(smkSkalaNilaiEntity.getFlag());
                    listOfResult.add(returnSmkSkalaNilai);
                }
            }
        }
        logger.info("[SmkSkalaNilaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkSkalaNilai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
