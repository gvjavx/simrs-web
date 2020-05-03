package com.neurix.akuntansi.transaksi.budgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDetailDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingPengadaanDao;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.*;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingBoImpl implements BudgetingBo {
    private static transient Logger logger = Logger.getLogger(BudgetingBoImpl.class);

    private BudgetingDao budgetingDao;
    private BudgetingDetailDao budgetingDetailDao;
    private BudgetingPengadaanDao budgetingPengadaanDao;
    private KodeRekeningDao kodeRekeningDao;
    private BranchDao branchDao;
    private PositionDao positionDao;

    @Override
    public List<Budgeting> getSearchByCriteria(Budgeting bean) throws GeneralBOException {
        logger.info("[BudgetingBoImpl.getSearchByCriteria] START >>>");

        List<ItAkunBudgetingEntity> budgetingEntities = new ArrayList<>();
        try {
            budgetingEntities = getListBudgetingEntityByCriteria(bean);
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getSearchByCriteria] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getSearchByCriteria] ERROR. ",e);
        }

        List<Budgeting> budgetings = new ArrayList<>();
        if (budgetingEntities.size() > 0){
            Budgeting budgeting;
            for (ItAkunBudgetingEntity budgetingEntity : budgetingEntities){
                budgeting = new Budgeting();
                budgeting.setNoBudgeting(budgetingEntity.getNoBudgeting());
                budgeting.setTahun(budgeting.getTahun());
                budgeting.setBranchId(budgetingEntity.getBranchId());
                budgeting.setRekeningId(budgetingEntity.getRekeningId());
                budgeting.setStatus(budgetingEntity.getStatus());
                budgeting.setNilaiTotal(budgetingEntity.getNilaiTotal());
                budgeting.setTipe(budgetingEntity.getTipe());
                budgeting.setSemester1(budgetingEntity.getSemester1());
                budgeting.setSemester2(budgetingEntity.getSemester2());
                budgeting.setQuartal1(budgetingEntity.getQuartal1());
                budgeting.setQuartal2(budgetingEntity.getQuartal2());
                budgeting.setQuartal3(budgetingEntity.getQuartal3());
                budgeting.setQuartal4(budgetingEntity.getQuartal4());
                budgeting.setSelisih(budgetingEntity.getSelisih());
                budgeting.setApproveFlag(budgetingEntity.getApproveFlag());
                budgeting.setApproveWho(budgetingEntity.getApproveWho());
                budgeting.setApproveTime(budgetingEntity.getApproveTime());
                budgeting.setFlag(budgetingEntity.getFlag());
                budgeting.setAction(budgetingEntity.getAction());
                budgeting.setCreatedDate(budgetingEntity.getCreatedDate());
                budgeting.setCreatedWho(budgetingEntity.getCreatedWho());
                budgeting.setLastUpdate(budgetingEntity.getLastUpdate());
                budgeting.setLastUpdateWho(budgetingEntity.getLastUpdateWho());

                // kode rekening;
                ImKodeRekeningEntity kodeRekeningEntity = getEntityKoderekeningById(budgetingEntity.getRekeningId());
                if (kodeRekeningEntity != null){
                    budgeting.setKodeRekening(kodeRekeningEntity.getKodeRekening());
                    budgeting.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
                    budgeting.setLevel(kodeRekeningEntity.getLevel());
                    budgeting.setParentId(kodeRekeningEntity.getParentId());
                }

                budgetings.add(budgeting);
            }
        }

        // short berdasarakan kodeRekening
        Collections.sort(budgetings, Budgeting.getKodeRekeningSorting());

        logger.info("[BudgetingBoImpl.getSearchByCriteria] END <<<");
        return budgetings;
    }

    public List<ItAkunBudgetingEntity> getListBudgetingEntityByCriteria(Budgeting bean){

        logger.info("[BudgetingBoImpl.getListBudgetingEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getTahun() != null){
            hsCriteria.put("tahun", bean.getTahun());
        }
        if (bean.getRekeningId() != null){
            hsCriteria.put("rekening_id", bean.getRekeningId());
        }
        if (bean.getStatus() != null){
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getTipe() != null){
            hsCriteria.put("tipe", bean.getTipe());
        }
        if (bean.getApproveFlag() != null){
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        }

        if (bean.getBranchId() != null){
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        List<ItAkunBudgetingEntity> budgetingEntities = new ArrayList<>();

        try {
            budgetingEntities = budgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getListBudgetingEntityByCriteria] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getListBudgetingEntityByCriteria] ERROR. ",e);
        }
        logger.info("[BudgetingBoImpl.getListBudgetingEntityByCriteria] END <<<<");
        return budgetingEntities;
    }

    private ImKodeRekeningEntity getEntityKoderekeningById(String id) throws GeneralBOException{
        return kodeRekeningDao.getById("rekeningId", id);
    }

    @Override
    public void saveAddCoaBudgeting(List<Budgeting> budgetingList) throws GeneralBOException {

    }

    @Override
    public void saveBudgetingDetail(List<Budgeting> budgetingList, String type) throws GeneralBOException {

    }

    @Override
    public void saveAllBudgeting(List<Budgeting> budgetingList, String type) throws GeneralBOException {

    }

    public void setBudgetingDao(BudgetingDao budgetingDao) {
        this.budgetingDao = budgetingDao;
    }

    public void setBudgetingDetailDao(BudgetingDetailDao budgetingDetailDao) {
        this.budgetingDetailDao = budgetingDetailDao;
    }

    public void setBudgetingPengadaanDao(BudgetingPengadaanDao budgetingPengadaanDao) {
        this.budgetingPengadaanDao = budgetingPengadaanDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}
