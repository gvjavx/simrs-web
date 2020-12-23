package com.neurix.simrs.master.jenispersediaanobatsub.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;
import com.neurix.simrs.master.jenispersediaanobatsub.bo.JenisPersediaanObatSubBo;
import com.neurix.simrs.master.jenispersediaanobatsub.dao.JenisPersdiaanObatSubDao;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.JenisPersediaanObatSub;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisPersediaanObatSubBoImpl implements JenisPersediaanObatSubBo{
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatSubBoImpl.class);
    private JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao;
    private JenisPersediaanObatDao jenisPersediaanObatDao;

    public void setJenisPersdiaanObatSubDao(JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao) {
        this.jenisPersdiaanObatSubDao = jenisPersdiaanObatSubDao;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatSubEntity> getListEntity(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.getListEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama()))
            hsCriteria.put("nama", bean.getNama());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat()))
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());

        List<ImSimrsJenisPersediaanObatSubEntity> jenisPersediaanObatEntities = new ArrayList<>();

        try {
            jenisPersediaanObatEntities = jenisPersdiaanObatSubDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatSubBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatSubBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[JenisPersediaanObatSubBoImpl.getListEntity] END <<<");
        return jenisPersediaanObatEntities;
    }

    @Override
    public List<JenisPersediaanObatSub> getSearchByCriteria(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.getSearchByCriteria] START >>>");

        List<ImSimrsJenisPersediaanObatSubEntity> jenisPersediaanObatSubEntities = new ArrayList<>();

        try {
            jenisPersediaanObatSubEntities = getListEntity(bean);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatSubBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatSubBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = listEntityToListModel(jenisPersediaanObatSubEntities);

        logger.info("[JenisPersediaanObatSubBoImpl.getSearchByCriteria] END <<<");
        return jenisPersediaanObatSubs;
    }

    @Override
    public List<JenisPersediaanObatSub> getAll() throws GeneralBOException {
        return listEntityToListModel(jenisPersdiaanObatSubDao.getAll());
    }

    @Override
    public void saveAdd(JenisPersediaanObatSub bean) throws GeneralBOException {
        if (bean!=null) {

            String jenidPersediaanObatSubId;
            try {
                // Generating ID, get from postgre sequence
                jenidPersediaanObatSubId = jenisPersdiaanObatSubDao.getNextId();
            } catch (HibernateException e) {
                logger.error("[jenispersediaanobatSubDaoBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence jenis obat Sub id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImSimrsJenisPersediaanObatSubEntity imSimrsJenisPersediaanObatSubEntity = new ImSimrsJenisPersediaanObatSubEntity();

            imSimrsJenisPersediaanObatSubEntity.setId(jenidPersediaanObatSubId);
            imSimrsJenisPersediaanObatSubEntity.setNama(bean.getNama());
            imSimrsJenisPersediaanObatSubEntity.setIdJenisObat(bean.getIdJenisObat());

            imSimrsJenisPersediaanObatSubEntity.setFlag(bean.getFlag());
            imSimrsJenisPersediaanObatSubEntity.setAction(bean.getAction());
            imSimrsJenisPersediaanObatSubEntity.setCreatedWho(bean.getCreatedWho());
            imSimrsJenisPersediaanObatSubEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSimrsJenisPersediaanObatSubEntity.setCreatedDate(bean.getCreatedDate());
            imSimrsJenisPersediaanObatSubEntity.setLastUpdate(bean.getLastUpdate());
            try {
                // insert into database
                jenisPersdiaanObatSubDao.addAndSave(imSimrsJenisPersediaanObatSubEntity);
            } catch (HibernateException e) {
                logger.error("[JenisPersediaanObatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data JenisPersediaanObat, please info to your admin..." + e.getMessage());
            }
        }

    }

    @Override
    public void saveEdit(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String id = bean.getId();
            ImSimrsJenisPersediaanObatSubEntity imSimrsJenisPersediaanObatSubEntity = null;
            try {
                // Get data from database by ID
                imSimrsJenisPersediaanObatSubEntity = jenisPersdiaanObatSubDao.getById("id", id);

            } catch (HibernateException e) {
                logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data JenisPersediaanObat by Kode JenisPersediaanObat, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsJenisPersediaanObatSubEntity != null) {
//                imSimrsJenisObatEntity.setIdJenisObat(idJenisObat);
                imSimrsJenisPersediaanObatSubEntity.setNama(bean.getNama());
                imSimrsJenisPersediaanObatSubEntity.setIdJenisObat(bean.getIdJenisObat());

                imSimrsJenisPersediaanObatSubEntity.setFlag(bean.getFlag());
                imSimrsJenisPersediaanObatSubEntity.setAction(bean.getAction());
                imSimrsJenisPersediaanObatSubEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisPersediaanObatSubEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jenisPersdiaanObatSubDao.updateAndSave(imSimrsJenisPersediaanObatSubEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisPersediaanObatSubBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update JenisPersediaanObatSub, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[JenisPersediaanObatBoImpl.saveEdit] Error, not found data JenisObat with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data JenisPersediaanObat with request id, please check again your data ...");
        }

    }

    @Override
    public void saveDelete(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String id = bean.getId();

            ImSimrsJenisPersediaanObatSubEntity imSimrsJenisPersediaanObatSubEntity = null;

            try {
                // Get data from database by ID
                imSimrsJenisPersediaanObatSubEntity = jenisPersdiaanObatSubDao.getById("id", id);
            } catch (HibernateException e) {
                logger.error("[jenisPersediaanObatSubBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsJenisPersediaanObatSubEntity != null) {
                imSimrsJenisPersediaanObatSubEntity.setId(bean.getId());

                imSimrsJenisPersediaanObatSubEntity.setFlag(bean.getFlag());
                imSimrsJenisPersediaanObatSubEntity.setAction(bean.getAction());
                imSimrsJenisPersediaanObatSubEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisPersediaanObatSubEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jenisPersdiaanObatSubDao.updateAndSave(imSimrsJenisPersediaanObatSubEntity);
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

    private List<JenisPersediaanObatSub> listEntityToListModel(List<ImSimrsJenisPersediaanObatSubEntity> list) throws GeneralBOException{
        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (ImSimrsJenisPersediaanObatSubEntity paramEntity : list){
                JenisPersediaanObatSub jenisPersediaanObatSub = new JenisPersediaanObatSub();
                jenisPersediaanObatSub.setId(paramEntity.getId());
                jenisPersediaanObatSub.setNama(paramEntity.getNama());
                jenisPersediaanObatSub.setIdJenisObat(paramEntity.getIdJenisObat());

                ImSimrsJenisPersediaanObatEntity persediaanObatEntity = jenisPersediaanObatDao.getById("id", paramEntity.getIdJenisObat());
                if(persediaanObatEntity != null){
                    jenisPersediaanObatSub.setNamaJenis(persediaanObatEntity.getNama());
                }

                jenisPersediaanObatSub.setFlag(paramEntity.getFlag());
                jenisPersediaanObatSub.setAction(paramEntity.getAction());
                jenisPersediaanObatSub.setCreatedDate(paramEntity.getCreatedDate());
                jenisPersediaanObatSub.setCreatedWho(paramEntity.getCreatedWho());
                jenisPersediaanObatSub.setLastUpdate(paramEntity.getLastUpdate());
                jenisPersediaanObatSub.setLastUpdateWho(paramEntity.getLastUpdateWho());
                jenisPersediaanObatSubs.add(jenisPersediaanObatSub);
            }
        }
        return jenisPersediaanObatSubs;
    }

    public void setJenisPersediaanObatDao(JenisPersediaanObatDao jenisPersediaanObatDao) {
        this.jenisPersediaanObatDao = jenisPersediaanObatDao;
    }
}
