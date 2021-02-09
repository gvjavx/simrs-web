package com.neurix.akuntansi.master.parameterbudgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;

import com.neurix.akuntansi.master.parameterbudgeting.bo.JenisBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.dao.JenisBudgetingDao;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunJenisBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.JenisBudgeting;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisBudgetingBoImpl implements JenisBudgetingBo {
    protected static transient Logger logger = Logger.getLogger(JenisBudgetingBoImpl.class);
    private JenisBudgetingDao jenisBudgetingDao;
    private KodeRekeningDao kodeRekeningDao;

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public JenisBudgetingDao getJenisBudgetingDao() {
        return jenisBudgetingDao;
    }

    public void setJenisBudgetingDao(JenisBudgetingDao jenisBudgeting) {
        this.jenisBudgetingDao = jenisBudgeting;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<JenisBudgeting> getByCriteria(JenisBudgeting bean) throws GeneralBOException {
        logger.info("[JenisBudgetingBoImpl.getByCriteria] Start >>>>>>>");
        List<JenisBudgeting> listOfResultJenisBudgeting = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNamaJenis() != null && !"".equalsIgnoreCase(bean.getNamaJenis())) {
                hsCriteria.put("nama_jenis", bean.getNamaJenis());
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

            List<ImAkunJenisBudgetingEntity> imAkunJenisBudgetingEntities = null;
            try {
                imAkunJenisBudgetingEntities = jenisBudgetingDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[JenisBudgetingBoImpl.getByCriteria] error when get data jenis obat by get by criteria " + e.getMessage());
            }

            if (imAkunJenisBudgetingEntities.size() > 0) {
                for (ImAkunJenisBudgetingEntity parameterBudgetingRekeningEntity : imAkunJenisBudgetingEntities) {
                    JenisBudgeting parameterBudgetingRekening = new JenisBudgeting();
                    parameterBudgetingRekening.setId(parameterBudgetingRekeningEntity.getId());
                    parameterBudgetingRekening.setNamaJenis(parameterBudgetingRekeningEntity.getNamaJenis());
                   
                    parameterBudgetingRekening.setFlag(parameterBudgetingRekeningEntity.getFlag());
                    parameterBudgetingRekening.setAction(parameterBudgetingRekeningEntity.getAction());
                    parameterBudgetingRekening.setCreatedDate(parameterBudgetingRekeningEntity.getCreatedDate());
                    parameterBudgetingRekening.setCreatedWho(parameterBudgetingRekeningEntity.getCreatedWho());
                    parameterBudgetingRekening.setLastUpdate(parameterBudgetingRekeningEntity.getLastUpdate());
                    parameterBudgetingRekening.setLastUpdateWho(parameterBudgetingRekeningEntity.getLastUpdateWho());
                    listOfResultJenisBudgeting.add(parameterBudgetingRekening);
                }
            }

        }
        return listOfResultJenisBudgeting;

    }

    @Override
    public void saveAdd(JenisBudgeting bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(JenisBudgeting bean) throws GeneralBOException {

    }

    @Override
    public void saveDelete(JenisBudgeting bean) throws GeneralBOException {

    }


}
