package com.neurix.akuntansi.transaksi.budgeting.bo;

import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by reza on 29/04/20.
 */
public interface BudgetingBo {

    public List<Budgeting> getSearchByCriteria(Budgeting bean) throws GeneralBOException;
    public void saveAddCoaBudgeting(List<Budgeting> budgetingList) throws GeneralBOException;
    public void saveBudgetingDetail(List<Budgeting> budgetingList, String type) throws GeneralBOException;
    public void saveAllBudgeting(List<Budgeting> budgetingList, String type) throws GeneralBOException;

}
