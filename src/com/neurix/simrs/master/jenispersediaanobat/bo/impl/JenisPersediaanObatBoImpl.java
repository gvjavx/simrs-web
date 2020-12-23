package com.neurix.simrs.master.jenispersediaanobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.bo.JenisPersediaanObatBo;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisPersediaanObatBoImpl implements JenisPersediaanObatBo{
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatBoImpl.class);
    private JenisPersediaanObatDao jenisPersediaanObatDao;

    public void setJenisPersediaanObatDao(JenisPersediaanObatDao jenisPersediaanObatDao) {
        this.jenisPersediaanObatDao = jenisPersediaanObatDao;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getListEntity(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatBoImpl.getListEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama()))
            hsCriteria.put("nama", bean.getNama());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        List<ImSimrsJenisPersediaanObatEntity> jenisPersediaanObatEntities = new ArrayList<>();
        try {
            jenisPersediaanObatEntities = jenisPersediaanObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[JenisPersediaanObatBoImpl.getListEntity] END <<<");
        return jenisPersediaanObatEntities;
    }

    @Override
    public List<JenisPersediaanObat> getSearchByCriteria(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatBoImpl.getSearchByCriteria] START >>>");

        List<ImSimrsJenisPersediaanObatEntity> jenisPersediaanObatEntities = new ArrayList<>();
        try {
            jenisPersediaanObatEntities = getListEntity(bean);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }
        List<JenisPersediaanObat> jenisPersediaanObats = listEntityToListModel(jenisPersediaanObatEntities);

        logger.info("[JenisPersediaanObatBoImpl.getSearchByCriteria] END <<<");
        return jenisPersediaanObats;
    }

    @Override
    public List<JenisPersediaanObat> getAll() throws GeneralBOException {
        return listEntityToListModel(jenisPersediaanObatDao.getAll());
    }

    @Override
    public List<JenisPersediaanObat> getListJenisPersediaanObat(String id) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveAdd(JenisPersediaanObat bean) throws GeneralBOException {
        if (bean!=null) {

                String jenidPersediaanObatId;
                try {
                    // Generating ID, get from postgre sequence
                    jenidPersediaanObatId = jenisPersediaanObatDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[jenisobatDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence jenis obat id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsJenisPersediaanObatEntity imSimrsJenisPersediaanObatEntity = new ImSimrsJenisPersediaanObatEntity();
                imSimrsJenisPersediaanObatEntity.setId(jenidPersediaanObatId);
                imSimrsJenisPersediaanObatEntity.setNama(bean.getNama());

                imSimrsJenisPersediaanObatEntity.setFlag(bean.getFlag());
                imSimrsJenisPersediaanObatEntity.setAction(bean.getAction());
                imSimrsJenisPersediaanObatEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsJenisPersediaanObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisPersediaanObatEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsJenisPersediaanObatEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    jenisPersediaanObatDao.addAndSave(imSimrsJenisPersediaanObatEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisPersediaanObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data JenisPersediaanObat, please info to your admin..." + e.getMessage());
                }
            }
        }


    @Override
    public void saveEdit(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String id = bean.getId();
            ImSimrsJenisPersediaanObatEntity imSimrsJenisPersediaanObatEntity = null;
            try {
                // Get data from database by ID
                imSimrsJenisPersediaanObatEntity = jenisPersediaanObatDao.getById("id", id);

            } catch (HibernateException e) {
                logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data JenisPersediaanObat by Kode JenisPersediaanObat, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsJenisPersediaanObatEntity != null) {
//                imSimrsJenisObatEntity.setIdJenisObat(idJenisObat);
                imSimrsJenisPersediaanObatEntity.setNama(bean.getNama());

                imSimrsJenisPersediaanObatEntity.setFlag(bean.getFlag());
                imSimrsJenisPersediaanObatEntity.setAction(bean.getAction());
                imSimrsJenisPersediaanObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisPersediaanObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jenisPersediaanObatDao.updateAndSave(imSimrsJenisPersediaanObatEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update JenisPersediaanObat, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, not found data JenisObat with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data JenisPersediaanObat with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String id = bean.getId();

            ImSimrsJenisPersediaanObatEntity imSimrsJenisPersediaanObatEntity = null;

            try {
                // Get data from database by ID
                imSimrsJenisPersediaanObatEntity = jenisPersediaanObatDao.getById("id", id);
            } catch (HibernateException e) {
                logger.error("[JenisObatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsJenisPersediaanObatEntity != null) {
                imSimrsJenisPersediaanObatEntity.setId(bean.getId());

                imSimrsJenisPersediaanObatEntity.setFlag(bean.getFlag());
                imSimrsJenisPersediaanObatEntity.setAction(bean.getAction());
                imSimrsJenisPersediaanObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisPersediaanObatEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jenisPersediaanObatDao.updateAndSave(imSimrsJenisPersediaanObatEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisPersediaanObatBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data jenisPersediaanObat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[jenisPersediaanObatBoImpl.saveDelete] Error, not found data jenisPersediaanObat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data jenisPersediaanObat with request id, please check again your data ...");
            }
        }
        logger.info("[jenisPersediaanObatBoImpl.saveDelete] end process <<<");
    }

    private List<JenisPersediaanObat> listEntityToListModel(List<ImSimrsJenisPersediaanObatEntity> list) throws GeneralBOException{
        List<JenisPersediaanObat> jenisPersediaanObats = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (ImSimrsJenisPersediaanObatEntity paramEntity : list){
                JenisPersediaanObat jenisPersediaanObat = new JenisPersediaanObat();
                jenisPersediaanObat.setId(paramEntity.getId());
                jenisPersediaanObat.setNama(paramEntity.getNama());
                jenisPersediaanObat.setFlag(paramEntity.getFlag());
                jenisPersediaanObat.setAction(paramEntity.getAction());
                jenisPersediaanObat.setCreatedDate(paramEntity.getCreatedDate());
                jenisPersediaanObat.setCreatedWho(paramEntity.getCreatedWho());
                jenisPersediaanObat.setLastUpdate(paramEntity.getLastUpdate());
                jenisPersediaanObat.setLastUpdateWho(paramEntity.getLastUpdateWho());
                jenisPersediaanObats.add(jenisPersediaanObat);
            }
        }
        return jenisPersediaanObats;
    }
}
