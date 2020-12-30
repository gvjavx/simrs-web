package com.neurix.simrs.master.parameterketeranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.dao.ParameterKeteranganObatDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterKeteranganObatBoImpl implements ParameterKeteranganObatBo{
    private static transient Logger logger = Logger.getLogger(ParameterKeteranganObatBoImpl.class);
    private ParameterKeteranganObatDao parameterKeteranganObatDao;

    public void setParameterKeteranganObatDao(ParameterKeteranganObatDao parameterKeteranganObatDao) {
        this.parameterKeteranganObatDao = parameterKeteranganObatDao;
    }

    @Override
    public List<ParameterKeteranganObat> getByCriteria(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[ParameterKeteranganObatBoImpl.getByCriteria] Start >>>>>>>>");
        List<ParameterKeteranganObat> listOfResultParameterKeteranganObat = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsParameterKeteranganObatEntity> imSimrsParameterKeteranganObat = null;
            try {
                imSimrsParameterKeteranganObat = parameterKeteranganObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisDietBoImpl.getByCriteria] error when get data Jenis Diet by get by criteria "+ e.getMessage());
            }

            if (imSimrsParameterKeteranganObat.size() > 0) {
                for (ImSimrsParameterKeteranganObatEntity ParameterKeteranganObat : imSimrsParameterKeteranganObat){
                    ParameterKeteranganObat parameterKeteranganObat = new ParameterKeteranganObat();

                    parameterKeteranganObat.setId(ParameterKeteranganObat.getId());
                    parameterKeteranganObat.setNama(ParameterKeteranganObat.getNama());


                    parameterKeteranganObat.setAction(ParameterKeteranganObat.getAction());
                    parameterKeteranganObat.setFlag(ParameterKeteranganObat.getFlag());
                    parameterKeteranganObat.setCreatedDate(ParameterKeteranganObat.getCreatedDate());
                    parameterKeteranganObat.setCreatedWho(ParameterKeteranganObat.getCreatedWho());
                    parameterKeteranganObat.setLastUpdate(ParameterKeteranganObat.getLastUpdate());
                    parameterKeteranganObat.setLastUpdateWho(ParameterKeteranganObat.getLastUpdateWho());
                    listOfResultParameterKeteranganObat.add(parameterKeteranganObat);
                }
            }
        }

        logger.info("[ParameterKeteranganObatBoImpl.getByCriteria] End <<<<<<<<");
        return listOfResultParameterKeteranganObat;
    }

    @Override
    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis) throws GeneralBOException {
        List<ParameterKeteranganObat> parameterKeteranganObats = new ArrayList<>();
        try {
            parameterKeteranganObats = parameterKeteranganObatDao.getParameterKeterangan(idJenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return parameterKeteranganObats;
    }

    @Override
    public List<KeteranganObat> getParameterKeteranganWaktu(String idJenis) throws GeneralBOException {
        List<KeteranganObat> parameterKeteranganObats = new ArrayList<>();
        try {
            parameterKeteranganObats = parameterKeteranganObatDao.getKeteranganObatWaktu(idJenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return parameterKeteranganObats;
    }

    @Override
    public void saveAdd(ParameterKeteranganObat bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsParameterKeteranganObatEntity> cekList = new ArrayList<>();
            try {
                cekList = parameterKeteranganObatDao.getParameterKeteranganObat(bean.getNama());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }if (cekList.size()> 0){
                throw new GeneralBOException("nama Parameter KeteranganObat tidak boleh sama");
            }else {

                String paramid;
                try {
                    // Generating ID, get from postgre sequence
                    paramid = parameterKeteranganObatDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[Parameter KeteranganObat DaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Parameter KeteranganObat id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsParameterKeteranganObatEntity imSimrsParameterKeteranganObatEntity = new ImSimrsParameterKeteranganObatEntity();

                imSimrsParameterKeteranganObatEntity.setId(paramid);
                imSimrsParameterKeteranganObatEntity.setNama(bean.getNama());

                imSimrsParameterKeteranganObatEntity.setFlag(bean.getFlag());
                imSimrsParameterKeteranganObatEntity.setAction(bean.getAction());
                imSimrsParameterKeteranganObatEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsParameterKeteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsParameterKeteranganObatEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsParameterKeteranganObatEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    parameterKeteranganObatDao.addAndSave(imSimrsParameterKeteranganObatEntity);
                } catch (HibernateException e) {
                    logger.error("[ParameterKeteranganObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data ParameterKeteranganObat, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveEdit(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[ParameterKeteranganObatBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            List<ImSimrsParameterKeteranganObatEntity> ceklist = new ArrayList<>();
            try {
                ceklist = parameterKeteranganObatDao.getParameterKeteranganObat(bean.getNama());
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (ceklist.size() > 0) {

                throw new GeneralBOException("nama ParameterKeteranganObat tidak boleh sama");

            } else {
                String id = bean.getId();
                ImSimrsParameterKeteranganObatEntity imSimrsParameterKeteranganObatEntity = null;
                try {
                    // Get data from database by ID
                    imSimrsParameterKeteranganObatEntity = parameterKeteranganObatDao.getById("id", id);

                } catch (HibernateException e) {
                    logger.error("[ParameterKeteranganObatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data ParameterKeteranganObat by Kode ParameterKeteranganObat, please inform to your admin...," + e.getMessage());
                }
                if (imSimrsParameterKeteranganObatEntity != null) {
//                imSimrsJenisObatEntity.setIdJenisObat(idJenisObat);
                    imSimrsParameterKeteranganObatEntity.setNama(bean.getNama());
                    imSimrsParameterKeteranganObatEntity.setId(bean.getId());

                    imSimrsParameterKeteranganObatEntity.setFlag(bean.getFlag());
                    imSimrsParameterKeteranganObatEntity.setAction(bean.getAction());
                    imSimrsParameterKeteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsParameterKeteranganObatEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        parameterKeteranganObatDao.updateAndSave(imSimrsParameterKeteranganObatEntity);
                    } catch (HibernateException e) {
                        logger.error("[ParameterKeteranganObatSubBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update ParameterKeteranganObat please info to your admin..." + e.getMessage());
                    }
                }
            }
        } else {
            logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, not found data JenisObat with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data JenisPersediaanObat with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String id = bean.getId();

            ImSimrsParameterKeteranganObatEntity imSimrsParameterKeteranganObatEntity = null;

            try {
                // Get data from database by ID
                imSimrsParameterKeteranganObatEntity = parameterKeteranganObatDao.getById("id", id);
            } catch (HibernateException e) {
                logger.error("[jenisPersediaanObatSubBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsParameterKeteranganObatEntity != null) {
                imSimrsParameterKeteranganObatEntity.setId(bean.getId());

                imSimrsParameterKeteranganObatEntity.setFlag(bean.getFlag());
                imSimrsParameterKeteranganObatEntity.setAction(bean.getAction());
                imSimrsParameterKeteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsParameterKeteranganObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    parameterKeteranganObatDao.updateAndSave(imSimrsParameterKeteranganObatEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisPersediaanObatSubBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data jenisPersediaanObatSub, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[jenisPersediaanObatSubBoImpl.saveDelete] Error, not found data jenisPersediaanObat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data jenisPersediaanObatSub with request id, please check again your data ...");
            }
        }
        logger.info("[jenisPersediaanObatSubBoImpl.saveDelete] end process <<<");
    }
}
