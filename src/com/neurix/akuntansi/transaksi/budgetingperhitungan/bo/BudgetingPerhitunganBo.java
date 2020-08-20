package com.neurix.akuntansi.transaksi.budgetingperhitungan.bo;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ImAkunParameterBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ParameterBudgeting;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.PerhitunganBudgeting;
import com.neurix.common.exception.GeneralBOException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by reza on 14/08/20.
 */
public interface BudgetingPerhitunganBo {
    public List<ImAkunParameterBudgetingEntity> getListParameterBudgetingEntity(ParameterBudgeting bean) throws GeneralBOException;
    public List<ItAkunPerhitunganBudgetingEntity> getListPerhitunganBudgetingEntity(PerhitunganBudgeting bean) throws GeneralBOException;
    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException;
    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException;
    public List<ParameterBudgeting> getSearchListParameterBudgeting(ParameterBudgeting bean) throws GeneralBOException;
    public BigDecimal hitungNilaiBudgeting(List<ItAkunPerhitunganBudgetingEntity> beans) throws GeneralBOException;
    public void saveAddPerhitunganBudgeting(List<ItAkunPerhitunganBudgetingEntity> beans, PerhitunganBudgeting bean) throws GeneralBOException;

}
