package com.neurix.hris.master.smkSkalaPointPrestasi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkSkalaPointPrestasi.bo.SmkSkalaPointPrestasiBo;
import com.neurix.hris.master.smkSkalaPointPrestasi.dao.SmkSkalaPointPrestasiDao;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.ImSmkSkalaPointPrestasiEntity;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.SmkSkalaPointPrestasi;
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
public class SmkSkalaPointPrestasiBoImpl implements SmkSkalaPointPrestasiBo {

    protected static transient Logger logger = Logger.getLogger(SmkSkalaPointPrestasiBoImpl.class);
    private SmkSkalaPointPrestasiDao smkSkalaPointPrestasiDao;

    public SmkSkalaPointPrestasiDao getSmkSkalaPointPrestasiDao() {
        return smkSkalaPointPrestasiDao;
    }

    public void setSmkSkalaPointPrestasiDao(SmkSkalaPointPrestasiDao smkSkalaPointPrestasiDao) {
        this.smkSkalaPointPrestasiDao = smkSkalaPointPrestasiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaPointPrestasiBoImpl.logger = logger;
    }

    public SmkSkalaPointPrestasiDao getSmkSkalaNilaiDao() {
        return smkSkalaPointPrestasiDao;
    }


    public void setSmkSkalaNilaiDao(SmkSkalaPointPrestasiDao smkSkalaPointPrestasiDao) {
        this.smkSkalaPointPrestasiDao = smkSkalaPointPrestasiDao;
    }

    @Override
    public void saveDelete(SmkSkalaPointPrestasi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiId = bean.getPointPrestasiId();

            ImSmkSkalaPointPrestasiEntity imSmkSkalaNilaiEntity = null;

            try {
                // Get data from database by ID
                imSmkSkalaNilaiEntity = smkSkalaPointPrestasiDao.getById("pointPrestasiId", smkSkalaNilaiId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiEntity != null) {

                // Modify from bean to entity serializable
                imSmkSkalaNilaiEntity.setPointPrestasiId(bean.getPointPrestasiId());
                imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiEntity.setAction(bean.getAction());
                imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkSkalaPointPrestasiDao.updateAndSave(imSmkSkalaNilaiEntity);
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
    public void saveEdit(SmkSkalaPointPrestasi bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String smkSkalaNilaiId = bean.getPointPrestasiId();

            ImSmkSkalaPointPrestasiEntity imSmkSkalaNilaiEntity = null;
            try {
                // Get data from database by ID
                imSmkSkalaNilaiEntity = smkSkalaPointPrestasiDao.getById("pointPrestasiId", smkSkalaNilaiId);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkSkalaNilai by Kode SmkSkalaNilai, please inform to your admin...," + e.getMessage());
            }

            if (imSmkSkalaNilaiEntity != null) {

                imSmkSkalaNilaiEntity.setPointPrestasiId(bean.getPointPrestasiId());

                imSmkSkalaNilaiEntity.setPoint(bean.getPoint());
                imSmkSkalaNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
                imSmkSkalaNilaiEntity.setNilaiBawah(bean.getNilaiBawah());
                imSmkSkalaNilaiEntity.setBranchId(bean.getBranchId());

                imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
                imSmkSkalaNilaiEntity.setAction(bean.getAction());
                imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkSkalaPointPrestasiDao.updateAndSave(imSmkSkalaNilaiEntity);
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
    public SmkSkalaPointPrestasi saveAdd(SmkSkalaPointPrestasi bean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String smkSkalaNilaiId;
            try {
                // Generating ID, get from postgre sequence
                smkSkalaNilaiId = smkSkalaPointPrestasiDao.getNextSmkSkalaPointPrestasiId();
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkSkalaNilaiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkSkalaPointPrestasiEntity imSmkSkalaNilaiEntity = new ImSmkSkalaPointPrestasiEntity();

            imSmkSkalaNilaiEntity.setPointPrestasiId(smkSkalaNilaiId);
            imSmkSkalaNilaiEntity.setPoint(bean.getPoint());
            imSmkSkalaNilaiEntity.setNilaiAtas(bean.getNilaiAtas());
            imSmkSkalaNilaiEntity.setNilaiBawah(bean.getNilaiBawah());
            imSmkSkalaNilaiEntity.setBranchId(bean.getBranchId());

            imSmkSkalaNilaiEntity.setFlag(bean.getFlag());
            imSmkSkalaNilaiEntity.setAction(bean.getAction());
            imSmkSkalaNilaiEntity.setCreatedWho(bean.getCreatedWho());
            imSmkSkalaNilaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkSkalaNilaiEntity.setCreatedDate(bean.getCreatedDate());
            imSmkSkalaNilaiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkSkalaPointPrestasiDao.addAndSave(imSmkSkalaNilaiEntity);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkSkalaNilai, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkSkalaNilaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkSkalaPointPrestasi> getByCriteria(SmkSkalaPointPrestasi searchBean) throws GeneralBOException {
        logger.info("[SmkSkalaNilaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkSkalaPointPrestasi> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPointPrestasiId() != null && !"".equalsIgnoreCase(searchBean.getPointPrestasiId())) {
                hsCriteria.put("pointPrestasiId", searchBean.getPointPrestasiId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
            }
            if (searchBean.getPoint() != null) {
                hsCriteria.put("point", searchBean.getPoint());
            }
            if (searchBean.getNilaiAtas() < 0) {
                hsCriteria.put("nilaiAtas", searchBean.getNilaiAtas());
            }
            if (searchBean.getNilaiBawah() < 0) {
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


            List<ImSmkSkalaPointPrestasiEntity> imSmkSkalaNilaiEntity = null;
            try {

                imSmkSkalaNilaiEntity = smkSkalaPointPrestasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkSkalaNilaiBoImpl.getSearchSmkSkalaNilaiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkSkalaNilaiEntity != null){
                SmkSkalaPointPrestasi returnSmkSkalaNilai;
                // Looping from dao to object and save in collection
                for(ImSmkSkalaPointPrestasiEntity smkSkalaNilaiEntity : imSmkSkalaNilaiEntity){
                    returnSmkSkalaNilai = new SmkSkalaPointPrestasi();
                    returnSmkSkalaNilai.setPointPrestasiId(smkSkalaNilaiEntity.getPointPrestasiId());
                    returnSmkSkalaNilai.setPoint(smkSkalaNilaiEntity.getPoint());
                    returnSmkSkalaNilai.setNilaiAtas(smkSkalaNilaiEntity.getNilaiAtas());
                    returnSmkSkalaNilai.setNilaiBawah(smkSkalaNilaiEntity.getNilaiBawah());
                    returnSmkSkalaNilai.setBranchId(smkSkalaNilaiEntity.getBranchId());
                    returnSmkSkalaNilai.setBranchName(smkSkalaNilaiEntity.getImBranches().getBranchName());

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
    public List<SmkSkalaPointPrestasi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
