package com.neurix.simrs.master.kandunganObat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kandunganObat.bo.KandunganObatBo;
import com.neurix.simrs.master.obat.dao.KandunganObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsKandunganObatEntity;
import com.neurix.simrs.master.kandunganObat.model.KandunganObat;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KandunganObatBoImpl implements KandunganObatBo {
    protected static transient Logger logger = Logger.getLogger(KandunganObatBoImpl.class);
    private KandunganObatDao kandunganObatDao;

    public void setKandunganObatDao(KandunganObatDao kandunganObatDao) {
        this.kandunganObatDao = kandunganObatDao;
    }

    @Override
    public List<KandunganObat> getByCriteria(KandunganObat bean) throws GeneralBOException {
        logger.info("[KandunganBoImpl.getByCriteria] Start >>>>>>");

        List<KandunganObat> result = new ArrayList<>();
        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdKandungan() != null && !"".equalsIgnoreCase(bean.getIdKandungan())){
                hsCriteria.put("id_kandungan", bean.getIdKandungan());
            }
            if(bean.getKandungan() != null && !"".equalsIgnoreCase(bean.getKandungan())){
                hsCriteria.put("kandungan", bean.getIdKandungan());
            }
            if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsKandunganObatEntity> imKandunganObatEntityList = null;
            try {
                imKandunganObatEntityList = kandunganObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KandunganObatBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imKandunganObatEntityList != null){
                KandunganObat returnKandunganObat;
                for(ImSimrsKandunganObatEntity kandunganEntity : imKandunganObatEntityList) {
                    returnKandunganObat = new KandunganObat();
                    returnKandunganObat.setIdKandungan(kandunganEntity.getIdKandungan());
                    returnKandunganObat.setKandungan(kandunganEntity.getKandungan());
                    returnKandunganObat.setFlag(kandunganEntity.getFlag());
                    returnKandunganObat.setAction(kandunganEntity.getAction());
                    returnKandunganObat.setCreatedDate(kandunganEntity.getCreatedDate());
                    returnKandunganObat.setCreatedWho(kandunganEntity.getCreatedWho());
                    returnKandunganObat.setLastUpdate(kandunganEntity.getLastUpdate());
                    returnKandunganObat.setLastUpdateWho(kandunganEntity.getLastUpdateWho());
                    result.add(returnKandunganObat);
                }
            }
        }
        logger.info("[KandunganObatBoImpl.getByCriteria] end process <<<<<<");
        return result;
    }

    @Override
    public void saveEdit(KandunganObat bean) throws GeneralBOException {
        logger.info("[KandunganObatBoImpl.saveEdit] start process >>>");
        if (bean!=null) {

            List<ImSimrsKandunganObatEntity> list = new ArrayList<>();
            try {
                list = kandunganObatDao.checkData(bean.getKandungan());
            } catch (HibernateException e) {
                logger.error("[KandunganObatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence kanduunganObat id, please info to your admin..." + e.getMessage());
            }

            if (list.size() > 0) {
                logger.error("Kandungan Obat sudah ada");
                throw new GeneralBOException("Kandungan Obat sudah ada");
            } else {

                ImSimrsKandunganObatEntity kandunganObatEntity = null;
                try {
                    // Get data from database by ID
                    kandunganObatEntity = kandunganObatDao.getById("idKandungan", bean.getIdKandungan());
                } catch (HibernateException e) {
                    logger.error("[KandunganObatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
                }

                if (kandunganObatEntity != null) {
                    if (("Y").equalsIgnoreCase(bean.getFlag())) {
                        kandunganObatEntity.setIdKandungan(bean.getIdKandungan());
                        kandunganObatEntity.setKandungan(bean.getKandungan());
                    }
                    kandunganObatEntity.setFlag(bean.getFlag());
                    kandunganObatEntity.setAction(bean.getAction());
                    kandunganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    kandunganObatEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        kandunganObatDao.updateAndSave(kandunganObatEntity);
                    } catch (HibernateException e) {
                        logger.error("[KandunganObatBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[KandunganObatBoImpl.saveEdit] Error, not found data with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data with request id, please check again your data ...");
                }
            }
        }
        logger.info("[KandunganObatBoImpl.saveEdit] end process <<<");
    }

    @Override
    public KandunganObat saveAdd(KandunganObat bean) throws GeneralBOException {
        logger.info("[KandunganObatBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            List<ImSimrsKandunganObatEntity> list = new ArrayList<>();

            try {
                list = kandunganObatDao.checkData(bean.getKandungan());
            } catch (HibernateException e) {
                logger.error("[KandunganObatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence kanduunganObat id, please info to your admin..." + e.getMessage());
            }

            if (list.size() > 0) {
                logger.error("Kandungan Obat sudah ada");
                throw new GeneralBOException("Kandungan Obat sudah ada");
            } else {

                String kandunganObatId;
                try {
                    // Generating ID, get from postgre sequence
                    kandunganObatId = kandunganObatDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[KandunganObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsKandunganObatEntity imKandunganObatEntity = new ImSimrsKandunganObatEntity();

                imKandunganObatEntity.setIdKandungan(kandunganObatId);
                imKandunganObatEntity.setKandungan(bean.getKandungan());
                imKandunganObatEntity.setFlag(bean.getFlag());
                imKandunganObatEntity.setAction(bean.getAction());
                imKandunganObatEntity.setCreatedWho(bean.getCreatedWho());
                imKandunganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imKandunganObatEntity.setCreatedDate(bean.getCreatedDate());
                imKandunganObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    kandunganObatDao.addAndSave(imKandunganObatEntity);
                } catch (HibernateException e) {
                    logger.error("[KandunganObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data , please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[KandunganObatBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
