package com.neurix.akuntansi.master.parameterbudgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.dao.JenisBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.dao.KategoriParameterBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.dao.ParameterBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.dao.ParameterBudgetingRekeningDao;
import com.neurix.akuntansi.master.parameterbudgeting.model.*;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterBudgetingBoImpl implements ParameterBudgetingBo{
    private static transient Logger logger = Logger.getLogger(ParameterBudgetingBoImpl.class);
    private ParameterBudgetingDao parameterBudgetingDao;
    private JenisBudgetingDao jenisBudgetingDao;
    private KategoriParameterBudgetingDao kategoriParameterBudgetingDao;
    private MasterDao masterDao;
    private PositionDao positionDao;
    private ParameterBudgetingRekeningDao parameterBudgetingRekeningDao;

    public void setParameterBudgetingRekeningDao(ParameterBudgetingRekeningDao parameterBudgetingRekeningDao) {
        this.parameterBudgetingRekeningDao = parameterBudgetingRekeningDao;
    }

    public void setParameterBudgetingDao(ParameterBudgetingDao parameterBudgetingDao) {
        this.parameterBudgetingDao = parameterBudgetingDao;
    }

    public void setJenisBudgetingDao(JenisBudgetingDao jenisBudgetingDao) {
        this.jenisBudgetingDao = jenisBudgetingDao;
    }

    public void setKategoriParameterBudgetingDao(KategoriParameterBudgetingDao kategoriParameterBudgetingDao) {
        this.kategoriParameterBudgetingDao = kategoriParameterBudgetingDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getListEntity(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[ParameterBudgetingBoImpl.saveEdit] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getIdKategoriBudgeting() != null && !"".equalsIgnoreCase(bean.getIdKategoriBudgeting()))
            hsCriteria.put("id_kategori_budgeting", bean.getIdKategoriBudgeting());
        if (bean.getDivisiId() != null && !"".equalsIgnoreCase(bean.getDivisiId()))
            hsCriteria.put("divisi_id", bean.getDivisiId());
        if (bean.getMasterId() != null && !"".equalsIgnoreCase(bean.getMasterId()))
            hsCriteria.put("master_id", bean.getMasterId());
        if (bean.getIdParamRekening() != null && !"".equalsIgnoreCase(bean.getIdParamRekening()))
            hsCriteria.put("id_param_rekening", bean.getIdParamRekening());
        hsCriteria.put("flag", "Y");

        List<ImAkunParameterBudgetingEntity> parameterBudgetingEntities = new ArrayList<>();

        try {
            parameterBudgetingEntities = parameterBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.saveEdit] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.saveEdit] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.saveEdit] END <<<");
        return parameterBudgetingEntities;
    }

    @Override
    public List<ParameterBudgeting> getSearchByCriteria(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[ParameterBudgetingBoImpl.getSearchByCriteria] START >>>");

        List<ParameterBudgeting> parameterBudgetings = new ArrayList<>();

        try {
            parameterBudgetings = parameterBudgetingDao.getSearchParameterBudgeting(bean);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getSearchByCriteria] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getSearchByCriteria] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getSearchByCriteria] END <<<");
        return parameterBudgetings;
    }

    @Override
    public void saveAdd(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[ParameterBudgetingBoImpl.saveAdd] START >>>");

        if (bean != null){

            boolean foundParameter = true;
            try {
                foundParameter = parameterBudgetingDao.foundParameterBudgetingByCriteria(bean.getIdKategoriBudgeting(), bean.getDivisiId(), bean.getMasterId(), bean.getIdParamRekening());
            } catch (HibernateException e){
                logger.error("[ParameterBudgetingBoImpl.saveAdd] ERROR. ", e);
                throw new GeneralBOException("[ParameterBudgetingBoImpl.saveAdd] ERROR. ]", e);
            }

            if (foundParameter){
                throw new GeneralBOException("Tidak dapat insert. karna data sudah ada");
            }

            ImAkunParameterBudgetingEntity akunParameterBudgetingEntity = new ImAkunParameterBudgetingEntity();
            akunParameterBudgetingEntity.setId(getNextId());
            akunParameterBudgetingEntity.setIdKategoriBudgeting(bean.getIdKategoriBudgeting());
            akunParameterBudgetingEntity.setIdJenisBudgeting(bean.getIdJenisBudgeting());
            akunParameterBudgetingEntity.setMasterId(bean.getMasterId());
            akunParameterBudgetingEntity.setDivisiId(bean.getDivisiId());
            akunParameterBudgetingEntity.setPositionId(bean.getPositionId());
            akunParameterBudgetingEntity.setRekeningId(bean.getRekeningId());
            akunParameterBudgetingEntity.setFlag("Y");
            akunParameterBudgetingEntity.setAction("C");
            akunParameterBudgetingEntity.setCreatedDate(bean.getCreatedDate());
            akunParameterBudgetingEntity.setCreatedWho(bean.getCreatedWho());
            akunParameterBudgetingEntity.setLastUpdate(bean.getLastUpdate());
            akunParameterBudgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                parameterBudgetingDao.addAndSave(akunParameterBudgetingEntity);
            } catch (HibernateException e){
                logger.error("[ParameterBudgetingBoImpl.saveAdd] ERROR. ", e);
                throw new GeneralBOException("[ParameterBudgetingBoImpl.saveAdd] ERROR. ]", e);
            }
        }

        logger.info("[ParameterBudgetingBoImpl.saveAdd] END <<<");
    }

    @Override
    public void saveEdit(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[ParameterBudgetingBoImpl.saveEdit] START >>>");

        if (bean != null){
            boolean foundParameter = false;
            if ("Y".equalsIgnoreCase(bean.getFlag())){
                try {
                    foundParameter = parameterBudgetingDao.foundParameterBudgetingByCriteria(bean.getIdKategoriBudgeting(), bean.getDivisiId(), bean.getMasterId(), bean.getIdParamRekening());
                } catch (HibernateException e){
                    logger.error("[ParameterBudgetingBoImpl.saveEdit] ERROR. ", e);
                    throw new GeneralBOException("[ParameterBudgetingBoImpl.saveEdit] ERROR. ]", e);
                }
            }

            if (foundParameter){
                throw new GeneralBOException("Tidak dapat update. karna data sudah ada");
            }

            ImAkunParameterBudgetingEntity akunParameterBudgetingEntity = new ImAkunParameterBudgetingEntity();
            try {
                akunParameterBudgetingEntity = parameterBudgetingDao.getById("id", bean.getId());
            } catch (HibernateException e){
                logger.error("[ParameterBudgetingBoImpl.saveEdit] ERROR. ", e);
                throw new GeneralBOException("[ParameterBudgetingBoImpl.saveEdit] ERROR. ]", e);
            }

            if (akunParameterBudgetingEntity != null && akunParameterBudgetingEntity.getId() != null){

                akunParameterBudgetingEntity.setIdJenisBudgeting(bean.getIdJenisBudgeting());
                akunParameterBudgetingEntity.setPositionId(bean.getPositionId());
                akunParameterBudgetingEntity.setIdKategoriBudgeting(bean.getIdKategoriBudgeting());
                akunParameterBudgetingEntity.setDivisiId(bean.getDivisiId());
                akunParameterBudgetingEntity.setMasterId(bean.getMasterId());
                akunParameterBudgetingEntity.setRekeningId(bean.getRekeningId());
                akunParameterBudgetingEntity.setFlag(bean.getFlag());
                akunParameterBudgetingEntity.setLastUpdate(bean.getLastUpdate());
                akunParameterBudgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    parameterBudgetingDao.updateAndSave(akunParameterBudgetingEntity);
                } catch (HibernateException e){
                    logger.error("[ParameterBudgetingBoImpl.saveEdit] ERROR. ", e);
                    throw new GeneralBOException("[ParameterBudgetingBoImpl.saveEdit] ERROR. ]", e);
                }

            } else {
                throw new GeneralBOException("Tidak ditemukan data parameter budgeting.");
            }
        }

        logger.info("[ParameterBudgetingBoImpl.saveEdit] END <<<");
    }

    @Override
    public List<ImAkunJenisBudgetingEntity> getAllJenisBudgeting() {
        logger.info("[ParameterBudgetingBoImpl.getAllJenisBudgeting] START >>>");
        logger.info("[ParameterBudgetingBoImpl.getAllJenisBudgeting] END <<<");
        return jenisBudgetingDao.getAll();
    }

    @Override
    public List<ImAkunKategoriParameterBudgetingEntity> getAllKategoriParameterBudgeting() {
        logger.info("[ParameterBudgetingBoImpl.getAllKategoriParameterBudgeting] START >>>");
        logger.info("[ParameterBudgetingBoImpl.getAllKategoriParameterBudgeting] END <<<");
        return kategoriParameterBudgetingDao.getAll();
    }

    @Override
    public List<ImPosition> getAllPosition() {
        logger.info("[ParameterBudgetingBoImpl.getAllPosition] START >>>");
        logger.info("[ParameterBudgetingBoImpl.getAllPosition] END <<<");
        return positionDao.getAll();
    }

    @Override
    public List<ImMasterEntity> getAllMaster() {
        logger.info("[ParameterBudgetingBoImpl.getAllMaster] START >>>");
        logger.info("[ParameterBudgetingBoImpl.getAllMaster] END <<<");
        return masterDao.getAll();
    }

    @Override
    public List<ImAkunParameterBudgetingRekeningEntity> getAllParameterRekening() {
        logger.info("[ParameterBudgetingBoImpl.getAllParameterRekening] START >>>");
        logger.info("[ParameterBudgetingBoImpl.getAllParameterRekening] END <<<");
        return parameterBudgetingRekeningDao.getAll();
    }

    @Override
    public List<KodeRekening> getListKodeRekeningByTipeCoa(String tipeCoa) {
        logger.info("[ParameterBudgetingBoImpl.getListKodeRekeningByTipeCoa] START >>>");

        List<KodeRekening> kodeRekeningList = new ArrayList<>();

        try {
            kodeRekeningList = parameterBudgetingDao.getListKodeRekeningByTipeKodeRekening(tipeCoa);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getListKodeRekeningByTipeCoa] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getListKodeRekeningByTipeCoa] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getListKodeRekeningByTipeCoa] END <<<");
        return kodeRekeningList;
    }

    @Override
    public String getTipeKodeRekeningFromJenisBudgetingById(String id) {
        logger.info("[ParameterBudgetingBoImpl.getTipeKodeRekeningFromJenisBudgetingById] START >>>");

        String tipeCoa = "";
        try {
            tipeCoa = parameterBudgetingDao.getTipeKodeRekeningById(id);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getTipeKodeRekeningFromJenisBudgetingById] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getTipeKodeRekeningFromJenisBudgetingById] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getTipeKodeRekeningFromJenisBudgetingById] END <<<");
        return tipeCoa;
    }

    @Override
    public String getKoderingFromPositionByPositionId(String id) {
        logger.info("[ParameterBudgetingBoImpl.getKoderingFromPositionByPositionId] START >>>");

        String kodering = "";
        try {
            kodering = parameterBudgetingDao.getKoderingPositionByPositionId(id);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getKoderingFromPositionByPositionId] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getKoderingFromPositionByPositionId] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getKoderingFromPositionByPositionId] END <<<");
        return kodering;
    }

    @Override
    public ImAkunParameterBudgetingEntity getEntityById(String id) throws GeneralBOException {
        logger.info("[ParameterBudgetingBoImpl.getEntityById] START >>>");

        ImAkunParameterBudgetingEntity parameterBudgetingEntity = new ImAkunParameterBudgetingEntity();

        try {
            parameterBudgetingEntity = parameterBudgetingDao.getById("id", id);
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getEntityById] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getEntityById] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getEntityById] END <<<");
        return parameterBudgetingEntity;
    }

    private String getNextId(){
        logger.info("[ParameterBudgetingBoImpl.getNextId] START >>>");
        String id = "";

        try {
            id = parameterBudgetingDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[ParameterBudgetingBoImpl.getNextId] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getNextId] ERROR. ]", e);
        }

        logger.info("[ParameterBudgetingBoImpl.getNextId] END <<<");
        return id;
    }
}
