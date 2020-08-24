package com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo;

import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.BudgetingNilaiDasar;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by reza on 10/08/20.
 */
public interface BudgetingNilaiDasarBo {
    public List<BudgetingNilaiDasar> searchByTahun(String tahun);
    public List<BudgetingNilaiDasar> getSearchByCriteria(BudgetingNilaiDasar bean);
    public void saveTransBudgeting(List<BudgetingNilaiDasar> budgetingNilaiDasarList) throws GeneralBOException;
    public List<ImAkunBudgetingNilaiDasarEntity> getListMasterNilaiDasarEntityByCriteria(BudgetingNilaiDasar bean) throws GeneralBOException;
    public List<BudgetingNilaiDasar> getListMasterNilaiDasarAdd(BudgetingNilaiDasar bean) throws GeneralBOException;
}
