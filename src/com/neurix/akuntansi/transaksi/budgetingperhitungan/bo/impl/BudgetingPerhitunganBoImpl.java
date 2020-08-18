package com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.impl;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.dao.JenisBudgetingDao;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.dao.ParameterBudgetingDao;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.dao.PerhitunganBudgetingDao;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ImAkunParameterBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ParameterBudgeting;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.PerhitunganBudgeting;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class BudgetingPerhitunganBoImpl implements BudgetingPerhitunganBo {
    private static transient Logger logger = Logger.getLogger(BudgetingPerhitunganBoImpl.class);
    private PerhitunganBudgetingDao perhitunganBudgetingDao;
    private ParameterBudgetingDao parameterBudgetingDao;
    private JenisBudgetingDao jenisBudgetingDao;

    public void setPerhitunganBudgetingDao(PerhitunganBudgetingDao perhitunganBudgetingDao) {
        this.perhitunganBudgetingDao = perhitunganBudgetingDao;
    }

    public void setParameterBudgetingDao(ParameterBudgetingDao parameterBudgetingDao) {
        this.parameterBudgetingDao = parameterBudgetingDao;
    }

    public void setJenisBudgetingDao(JenisBudgetingDao jenisBudgetingDao) {
        this.jenisBudgetingDao = jenisBudgetingDao;
    }

    private String getNextIdPerhitungan(String branchId, String idParameter){
        return branchId + idParameter + perhitunganBudgetingDao.getNextId();
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getListParameterBudgetingEntity(ParameterBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdJenisBudgeting() != null)
            hsCriteria.put("id_jenis_budgeting", bean.getIdJenisBudgeting());

        List<ImAkunParameterBudgetingEntity> parameterBudgetingEntities = new ArrayList<>();
        try {
            parameterBudgetingEntities = parameterBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListParameterBudgetingEntity] END <<<");
        return parameterBudgetingEntities;
    }

    @Override
    public List<ItAkunPerhitunganBudgetingEntity> getListPerhitunganBudgetingEntity(PerhitunganBudgeting bean) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] START >>>");
        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getIdParameterBudgeting() != null)
            hsCriteria.put("id_parameter_budgeting", bean.getIdParameterBudgeting());
        if (bean.getBranchId() != null)
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getTahun() != null)
            hsCriteria.put("tahun", bean.getTahun());

        List<ItAkunPerhitunganBudgetingEntity> perhitunganBudgetingEntities = new ArrayList<>();
        try {
            perhitunganBudgetingEntities = perhitunganBudgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] ERROR. ", e);
            throw new GeneralBOException("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] ERROR. ", e);
        }
        logger.info("[BudgetingPerhitunganBoImpl.getListPerhitunganBudgetingEntity] END <<<");
        return perhitunganBudgetingEntities;
    }

    @Override
    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String bulan, String tahun) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPendapatanTindakan] START >>>");
        return perhitunganBudgetingDao.getListPerhitunganPendapatanTindakan(branchId, tahun, bulan);
    }

    @Override
    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String bulan, String tahun) throws GeneralBOException {
        logger.info("[BudgetingPerhitunganBoImpl.getListPendapatanObat] START >>>");
        return perhitunganBudgetingDao.getListPendapatanObatPeriksa(branchId, tahun, bulan);
    }
}
