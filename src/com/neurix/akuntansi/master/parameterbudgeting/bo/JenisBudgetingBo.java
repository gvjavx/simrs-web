package com.neurix.akuntansi.master.parameterbudgeting.bo;


import com.neurix.akuntansi.master.parameterbudgeting.model.JenisBudgeting;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface JenisBudgetingBo {
    public List<JenisBudgeting> getByCriteria(JenisBudgeting searchBean) throws GeneralBOException;
    public void saveAdd(JenisBudgeting bean) throws GeneralBOException;
    public void saveEdit(JenisBudgeting bean) throws GeneralBOException;
    public void saveDelete(JenisBudgeting bean) throws GeneralBOException;
}
