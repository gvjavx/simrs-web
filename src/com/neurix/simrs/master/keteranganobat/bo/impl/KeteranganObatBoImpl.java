package com.neurix.simrs.master.keteranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.dao.JenisPersdiaanObatSubDao;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.dao.KeteranganObatDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.dao.ParameterKeteranganObatDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeteranganObatBoImpl implements KeteranganObatBo{
    private static transient Logger logger = Logger.getLogger(KeteranganObatBoImpl.class);
    private KeteranganObatDao keteranganObatDao;
    private ParameterKeteranganObatDao parameterKeteranganObatDao;
    private JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao;
    private JenisPersediaanObatDao jenisPersediaanObatDao;

    public void setKeteranganObatDao(KeteranganObatDao keteranganObatDao) {
        this.keteranganObatDao = keteranganObatDao;
    }

    public void setParameterKeteranganObatDao(ParameterKeteranganObatDao parameterKeteranganObatDao) {
        this.parameterKeteranganObatDao = parameterKeteranganObatDao;
    }

    public void setJenisPersdiaanObatSubDao(JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao) {
        this.jenisPersdiaanObatSubDao = jenisPersdiaanObatSubDao;
    }

    public void setJenisPersediaanObatDao(JenisPersediaanObatDao jenisPersediaanObatDao) {
        this.jenisPersediaanObatDao = jenisPersediaanObatDao;
    }

    @Override
    public List<ImSimrsKeteranganObatEntity> getListEntitiyByCriteria(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getListEntitiyByCriteria] START >>> ");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan()))
            hsCriteria.put("keterangan", bean.getKeterangan());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdSubJenis() != null && !"".equalsIgnoreCase(bean.getIdSubJenis()))
            hsCriteria.put("id_sub_jenis", bean.getIdSubJenis());
        if (bean.getIdParameterKeterangan() != null && !"".equalsIgnoreCase(bean.getIdParameterKeterangan()))
            hsCriteria.put("id_parameter_keterangan", bean.getIdParameterKeterangan());

        List<ImSimrsKeteranganObatEntity> keteranganObatEntities = new ArrayList<>();

        try {
            keteranganObatEntities = keteranganObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getListEntitiyByCriteria] END <<< ");
        return keteranganObatEntities;
    }

    @Override
    public List<KeteranganObat> getListSearchByCriteria(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getListSearchByCriteria] START >>> ");

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        try {
            keteranganObats = keteranganObatDao.getListSearchKeteranganObat(bean);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getListSearchByCriteria] END <<< ");
        return keteranganObats;
    }

    @Override
    public void saveAdd(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.saveAdd] START >>> ");

        boolean found = false;
        try {
            found = keteranganObatDao.checkIfAvailableByCriteria(bean.getIdSubJenis(), bean.getIdParameterKeterangan(), bean.getKeterangan(), bean.getWarnaLabel(), bean.getWarnaBackground());
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. "+e);
        }

        if (found){
            throw new GeneralBOException("Tidak dapat insert karna data yang sama sudah ada. ");
        }

        ImSimrsKeteranganObatEntity keteranganObatEntity = new ImSimrsKeteranganObatEntity();
        keteranganObatEntity.setId(getNextId());
        keteranganObatEntity.setIdSubJenis(bean.getIdSubJenis());
        keteranganObatEntity.setIdParameterKeterangan(bean.getIdParameterKeterangan());
        keteranganObatEntity.setKeterangan(bean.getKeterangan());
        keteranganObatEntity.setCreatedDate(bean.getCreatedDate());
        keteranganObatEntity.setCreatedWho(bean.getCreatedWho());
        keteranganObatEntity.setLastUpdate(bean.getLastUpdate());
        keteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
        keteranganObatEntity.setFlag("Y");
        keteranganObatEntity.setAction("C");
        keteranganObatEntity.setWarnaLabel(bean.getWarnaLabel());
        keteranganObatEntity.setWarnaBackground(bean.getWarnaBackground());

        try {
            keteranganObatDao.addAndSave(keteranganObatEntity);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.saveAdd] END <<< ");
    }

    @Override
    public void saveEdit(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.saveEdit] START >>> ");

        boolean found = false;
        if ("Y".equalsIgnoreCase(bean.getFlag())){
            try {
                found = keteranganObatDao.checkIfAvailableByCriteria(bean.getIdSubJenis(), bean.getIdParameterKeterangan(), bean.getKeterangan(), bean.getWarnaLabel(), bean.getWarnaBackground());
            } catch (HibernateException e){
                logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
                throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
            }
        }

        if (found){
            throw new GeneralBOException("Tidak dapat insert karna data yang sama sudah ada. ");
        }

        ImSimrsKeteranganObatEntity keteranganObatEntity = new ImSimrsKeteranganObatEntity();
        try {
            keteranganObatEntity = keteranganObatDao.getById("id", bean.getId());
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
        }

        if (keteranganObatEntity != null && keteranganObatEntity.getId() != null){
            keteranganObatEntity.setIdSubJenis(bean.getIdSubJenis());
            keteranganObatEntity.setIdParameterKeterangan(bean.getIdParameterKeterangan());
            keteranganObatEntity.setKeterangan(bean.getKeterangan());
            keteranganObatEntity.setWarnaLabel(bean.getWarnaLabel());
            keteranganObatEntity.setWarnaBackground(bean.getWarnaBackground());
            keteranganObatEntity.setFlag(bean.getFlag());
            keteranganObatEntity.setAction("U");
            keteranganObatEntity.setLastUpdate(bean.getLastUpdate());
            keteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                keteranganObatDao.updateAndSave(keteranganObatEntity);
            } catch (HibernateException e){
                logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
                throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
            }
        }

        logger.info("[KeteranganObatBoImpl.saveEdit] END <<< ");
    }

    @Override
    public List<KeteranganObat> getKeteranganObat(String idParam) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getKeteranganObat] START >>> ");
        List<KeteranganObat> keteranganObatList = new ArrayList<>();
        try {
            keteranganObatList = keteranganObatDao.getKeteranganObat(idParam);
        }catch (HibernateException e){
            logger.error(e);
        }
        logger.info("[KeteranganObatBoImpl.getKeteranganObat] END <<< ");
        return keteranganObatList;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getAllParameterKeterangan() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllParameterKeterangan] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllParameterKeterangan] END <<< ");
        return parameterKeteranganObatDao.getAll();
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getAllJenisPersedian() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllJenisPersedian] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllJenisPersedian] END <<< ");
        return jenisPersediaanObatDao.getAll();
    }

    @Override
    public List<ImSimrsJenisPersediaanObatSubEntity> getAllJenisPersediaanSub() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllJenisPersediaanSub] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllJenisPersediaanSub] END <<< ");
        return jenisPersdiaanObatSubDao.getAll();
    }

    private String getNextId() throws GeneralBOException{
        logger.info("[KeteranganObatBoImpl.getNextId] START >>> ");

        String id = "";
        try {
            id = keteranganObatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getNextId] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getNextId] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getNextId] END <<< ");
        return id;
    }
}
