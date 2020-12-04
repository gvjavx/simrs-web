package com.neurix.simrs.master.jenisobat.bo.impl;

import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisObatBoImpl implements JenisObatBo {

    protected static transient Logger logger = Logger.getLogger(JenisObatBoImpl.class);
    private JenisObatDao jenisObatDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    @Override
    public List<JenisObat> getByCriteria(JenisObat bean) throws GeneralBOException {
        logger.info("[JenisObatBoImpl.getByCriteria] Start >>>>>>>");
        List<JenisObat> listOfResultJenisObat = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }
            if (bean.getNamaJenisObat() != null && !"".equalsIgnoreCase(bean.getNamaJenisObat())) {
                hsCriteria.put("nama_jenis_obat", bean.getNamaJenisObat());
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

            List<ImSimrsJenisObatEntity> imSimrsJenisObatEntities = null;
            try {
                imSimrsJenisObatEntities = jenisObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisObatBoImpl.getByCriteria] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            if (imSimrsJenisObatEntities.size() > 0){
                for (ImSimrsJenisObatEntity jenisObatEntity : imSimrsJenisObatEntities){
                    JenisObat jenisObat = new JenisObat();
                    jenisObat.setIdJenisObat(jenisObatEntity.getIdJenisObat());
                    jenisObat.setNamaJenisObat(jenisObatEntity.getNamaJenisObat());
                    jenisObat.setFlag(jenisObatEntity.getFlag());
                    jenisObat.setAction(jenisObatEntity.getAction());
                    jenisObat.setCreatedDate(jenisObatEntity.getCreatedDate());
                    jenisObat.setCreatedWho(jenisObatEntity.getCreatedWho());
                    jenisObat.setLastUpdate(jenisObatEntity.getLastUpdate());
                    jenisObat.setLastUpdateWho(jenisObatEntity.getLastUpdateWho());
                    listOfResultJenisObat.add(jenisObat);
                }
            }
        }
        logger.info("[JenisObatBoImpl.getByCriteria] End <<<<<<<");
        return listOfResultJenisObat;
    }

    @Override
    public void saveAdd(JenisObat bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsJenisObatEntity> cekList = new ArrayList<>();
            try {
                cekList = jenisObatDao.getJenisObat(bean.getNamaJenisObat());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Nama Jenis Obat sudah ada...!");
            }else{
                String jenisobatId;
                try {
                    // Generating ID, get from postgre sequence
                    jenisobatId = jenisObatDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[jenisobatDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence jenis obat id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsJenisObatEntity imSimrsJenisObatEntity = new ImSimrsJenisObatEntity();
                imSimrsJenisObatEntity.setIdJenisObat(jenisobatId);

                imSimrsJenisObatEntity.setNamaJenisObat(bean.getNamaJenisObat());
                imSimrsJenisObatEntity.setFlag(bean.getFlag());
                imSimrsJenisObatEntity.setAction(bean.getAction());
                imSimrsJenisObatEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsJenisObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisObatEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsJenisObatEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    jenisObatDao.addAndSave(imSimrsJenisObatEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Jenis Obat, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveEdit(JenisObat bean) throws GeneralBOException {
        logger.info("[JenisObatBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idJenisObat = bean.getIdJenisObat();
            ImSimrsJenisObatEntity imSimrsJenisObatEntity = null;
            try {
                // Get data from database by ID
                imSimrsJenisObatEntity = jenisObatDao.getById("idJenisObat", idJenisObat);

            } catch (HibernateException e) {
                logger.error("[JenisObatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data JenisObat by Kode JenisObat, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsJenisObatEntity != null) {
//                imSimrsJenisObatEntity.setIdJenisObat(idJenisObat);
                imSimrsJenisObatEntity.setNamaJenisObat(bean.getNamaJenisObat());
                imSimrsJenisObatEntity.setIdJenisObat(bean.getIdJenisObat());

                imSimrsJenisObatEntity.setFlag(bean.getFlag());
                imSimrsJenisObatEntity.setAction(bean.getAction());
                imSimrsJenisObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jenisObatDao.updateAndSave(imSimrsJenisObatEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisObatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update dataJenisObat, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[JenisObatBoImpl.saveEdit] Error, not found data JenisObat with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data JenisObati with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(JenisObat bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idJenisObat = bean.getIdJenisObat();

            ImSimrsJenisObatEntity imSimrsJenisObatEntity = null;

            try {
                // Get data from database by ID
                imSimrsJenisObatEntity = jenisObatDao.getById("idJenisObat", idJenisObat);
            } catch (HibernateException e) {
                logger.error("[JenisObatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsJenisObatEntity != null) {
                imSimrsJenisObatEntity.setIdJenisObat(bean.getIdJenisObat());

                imSimrsJenisObatEntity.setFlag(bean.getFlag());
                imSimrsJenisObatEntity.setAction(bean.getAction());
                imSimrsJenisObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jenisObatDao.updateAndSave(imSimrsJenisObatEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisObatBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data JenisObat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[JenisObatBoImpl.saveDelete] Error, not found data JenisObat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data JenisObat with request id, please check again your data ...");
            }
        }
        logger.info("[JenisObatBoImpl.saveDelete] end process <<<");

    }
}