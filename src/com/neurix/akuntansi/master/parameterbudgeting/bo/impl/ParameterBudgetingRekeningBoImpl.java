package com.neurix.akuntansi.master.parameterbudgeting.bo.impl;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingRekeningBo;
import com.neurix.akuntansi.master.parameterbudgeting.dao.JenisBudgetingDao;

import com.neurix.akuntansi.master.parameterbudgeting.dao.ParameterBudgetingRekeningDao;
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
                for (ImAkunParameterBudgetingRekeningEntity jenisObatEntity : imAkunParameterBudgetingRekeningEntities) {
                    ParameterBudgetingRekening parameterBudgetingRekening = new ParameterBudgetingRekening();
                    parameterBudgetingRekening.setId(jenisObatEntity.getId());
                    parameterBudgetingRekening.setNama(jenisObatEntity.getNama());
                    parameterBudgetingRekening.setRekeningId(jenisObatEntity.getRekeningId());

                    parameterBudgetingRekening.setFlag(jenisObatEntity.getFlag());
                    parameterBudgetingRekening.setAction(jenisObatEntity.getAction());
                    parameterBudgetingRekening.setCreatedDate(jenisObatEntity.getCreatedDate());
                    parameterBudgetingRekening.setCreatedWho(jenisObatEntity.getCreatedWho());
                    parameterBudgetingRekening.setLastUpdate(jenisObatEntity.getLastUpdate());
                    parameterBudgetingRekening.setLastUpdateWho(jenisObatEntity.getLastUpdateWho());
                    listOfResultParameterBudgetingRekening.add(parameterBudgetingRekening);
                }
            }

        }
        return listOfResultParameterBudgetingRekening;

    }

    @Override
    public void saveAdd(ParameterBudgetingRekening bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(ParameterBudgetingRekening bean) throws GeneralBOException {

    }

    @Override
    public void saveDelete(ParameterBudgetingRekening bean) throws GeneralBOException {

    }
}
