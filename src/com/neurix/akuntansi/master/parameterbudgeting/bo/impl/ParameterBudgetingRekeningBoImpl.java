package com.neurix.akuntansi.master.parameterbudgeting.bo.impl;
import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingRekeningBo;

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
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingRekeningBo;

public class ParameterBudgetingRekeningBoImpl implements ParameterBudgetingRekeningBo {
    protected static transient Logger logger = Logger.getLogger(ParameterBudgetingRekeningBoImpl.class);
    private ParameterBudgetingRekeningDao parameterBudgetingRekeningDao;
    private KodeRekeningDao kodeRekeningDao;

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public ParameterBudgetingRekeningDao getParameterBudgetingRekeningDao() {
        return parameterBudgetingRekeningDao;
    }

    public void setParameterBudgetingRekeningDao(ParameterBudgetingRekeningDao parameterBudgetingRekeningDao) {
        this.parameterBudgetingRekeningDao = parameterBudgetingRekeningDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<ParameterBudgetingRekening> getByCriteria(ParameterBudgetingRekening bean) throws GeneralBOException {
        logger.info("[ParameterBudgetingRekeningBoImpl.getByCriteria] Start >>>>>>>");
        List<ParameterBudgetingRekening> listOfResultParameterBudgetingRekening = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())) {
                hsCriteria.put("rekening_id", bean.getRekeningId());
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

            List<ImAkunParameterBudgetingRekeningEntity> imAkunParameterBudgetingRekeningEntities = null;
            try {
                imAkunParameterBudgetingRekeningEntities = parameterBudgetingRekeningDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ParameterBudgetingRekeningBoImpl.getByCriteria] error when get data jenis obat by get by criteria " + e.getMessage());
            }

            if (imAkunParameterBudgetingRekeningEntities.size() > 0) {
                for (ImAkunParameterBudgetingRekeningEntity parameterBudgetingRekeningEntity : imAkunParameterBudgetingRekeningEntities) {
                    ParameterBudgetingRekening parameterBudgetingRekening = new ParameterBudgetingRekening();
                    parameterBudgetingRekening.setId(parameterBudgetingRekeningEntity.getId());
                    parameterBudgetingRekening.setNama(parameterBudgetingRekeningEntity.getNama());
                    parameterBudgetingRekening.setRekeningId(parameterBudgetingRekeningEntity.getRekeningId());
                    ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningDao.getById("id", parameterBudgetingRekeningEntity.getRekeningId());
                    if(kodeRekeningEntity != null){
                        parameterBudgetingRekening.setNamaRekening(kodeRekeningEntity.getNamaKodeRekening());
                    }
                    parameterBudgetingRekening.setFlag(parameterBudgetingRekeningEntity.getFlag());
                    parameterBudgetingRekening.setAction(parameterBudgetingRekeningEntity.getAction());
                    parameterBudgetingRekening.setCreatedDate(parameterBudgetingRekeningEntity.getCreatedDate());
                    parameterBudgetingRekening.setCreatedWho(parameterBudgetingRekeningEntity.getCreatedWho());
                    parameterBudgetingRekening.setLastUpdate(parameterBudgetingRekeningEntity.getLastUpdate());
                    parameterBudgetingRekening.setLastUpdateWho(parameterBudgetingRekeningEntity.getLastUpdateWho());
                    listOfResultParameterBudgetingRekening.add(parameterBudgetingRekening);
                }
            }
        }
        return listOfResultParameterBudgetingRekening;
    }

    @Override
    public void saveAdd(ParameterBudgetingRekening bean) throws GeneralBOException {

                String ParameterBudgetingRekeningId;
                try {
                    // Generating ID, get from postgre sequence
                    ParameterBudgetingRekeningId = parameterBudgetingRekeningDao.getNextSeq();
                } catch (HibernateException e) {
                    logger.error("[Parameter Budgeting RekeningDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting Parameter Budgeting Rekening id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImAkunParameterBudgetingRekeningEntity imAkunParameterBudgetingRekeningEntity = new ImAkunParameterBudgetingRekeningEntity();
                imAkunParameterBudgetingRekeningEntity.setId(ParameterBudgetingRekeningId);
                imAkunParameterBudgetingRekeningEntity.setNama(bean.getNama());
                imAkunParameterBudgetingRekeningEntity.setRekeningId(bean.getRekeningId());

                imAkunParameterBudgetingRekeningEntity.setFlag(bean.getFlag());
                imAkunParameterBudgetingRekeningEntity.setAction(bean.getAction());
                imAkunParameterBudgetingRekeningEntity.setCreatedWho(bean.getCreatedWho());
                imAkunParameterBudgetingRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imAkunParameterBudgetingRekeningEntity.setCreatedDate(bean.getCreatedDate());
                imAkunParameterBudgetingRekeningEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    parameterBudgetingRekeningDao.addAndSave(imAkunParameterBudgetingRekeningEntity);
                } catch (HibernateException e) {
                    logger.error("[Parameter Budgeting Rekening BoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Parameter Budgeting Rekening, please info to your admin..." + e.getMessage());
                }
            }


    @Override
    public void saveEdit(ParameterBudgetingRekening bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.saveEdit] start process >>>");

                String id = bean.getId();
                ImAkunParameterBudgetingRekeningEntity imAkunParameterBudgetingRekeningEntity = null;
                try {
                    // Get data from database by ID
                    imAkunParameterBudgetingRekeningEntity = parameterBudgetingRekeningDao.getById("id", id);

                } catch (HibernateException e) {
                    logger.error("[parameter Budgeting Rekening BoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data parameter Budgeting Rekening by Kode parameter Budgeting Rekening, " +
                            "please inform to your admin...," + e.getMessage());
                }
                if (imAkunParameterBudgetingRekeningEntity != null) {
                    imAkunParameterBudgetingRekeningEntity.setNama(bean.getNama());
                    imAkunParameterBudgetingRekeningEntity.setRekeningId(bean.getRekeningId());

                    imAkunParameterBudgetingRekeningEntity.setFlag(bean.getFlag());
                    imAkunParameterBudgetingRekeningEntity.setAction(bean.getAction());
                    imAkunParameterBudgetingRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imAkunParameterBudgetingRekeningEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        parameterBudgetingRekeningDao.updateAndSave(imAkunParameterBudgetingRekeningEntity);
                    } catch (HibernateException e) {
                        logger.error("[ParameterBudgetingRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update ParameterBudgetingRekening, please info to your admin..." + e.getMessage());
                    }
                }
            }



    @Override
    public void saveDelete(ParameterBudgetingRekening bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String id = bean.getId();
            ImAkunParameterBudgetingRekeningEntity imAkunParameterBudgetingRekeningEntity = null;

            try {
                // Get data from database by ID
                imAkunParameterBudgetingRekeningEntity = parameterBudgetingRekeningDao.getById("id", id);
            } catch (HibernateException e) {
                logger.error("[ParameterBudgetingRekeningBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data ParameterBudgetingRekening by Kode ParameterBudgetingRekening, please inform to your admin...," + e.getMessage());
            }

            if (imAkunParameterBudgetingRekeningEntity != null) {
                imAkunParameterBudgetingRekeningEntity.setId(bean.getId());

                imAkunParameterBudgetingRekeningEntity.setFlag(bean.getFlag());
                imAkunParameterBudgetingRekeningEntity.setAction(bean.getAction());
                imAkunParameterBudgetingRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imAkunParameterBudgetingRekeningEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    parameterBudgetingRekeningDao.updateAndSave(imAkunParameterBudgetingRekeningEntity);
                } catch (HibernateException e) {
                    logger.error("[imAkunParameterBudgetingRekeningEntityBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data imAkunParameterBudgetingRekeningEntity, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[imAkunParameterBudgetingRekeningEntityBoImpl.saveDelete] Error, not found data imAkunParameterBudgetingRekeningEntity with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data imAkunParameterBudgetingRekeningEntity with request id, please check again your data ...");
            }
        }
        logger.info("[imAkunParameterBudgetingRekeningEntityBoImpl.saveDelete] end process <<<");
    }
}
