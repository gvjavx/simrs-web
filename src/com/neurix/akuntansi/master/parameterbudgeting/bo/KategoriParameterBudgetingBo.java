package com.neurix.akuntansi.master.parameterbudgeting.bo;

import com.neurix.akuntansi.master.parameterbudgeting.model.KategoriParameterBudgeting;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface KategoriParameterBudgetingBo {
    public List<KategoriParameterBudgeting> getByCriteria(KategoriParameterBudgeting searchBean) throws GeneralBOException;
    public void saveAdd(KategoriParameterBudgeting bean) throws GeneralBOException;
    public void saveEdit(KategoriParameterBudgeting bean) throws GeneralBOException;
    public void saveDelete(KategoriParameterBudgeting bean) throws GeneralBOException;

}
