package com.neurix.akuntansi.master.parameterbudgeting.bo;


import com.neurix.akuntansi.master.parameterbudgeting.model.*;
import com.neurix.common.exception.GeneralBOException;


import java.util.List;

public interface ParameterBudgetingRekeningBo {
    public List<ParameterBudgetingRekening> getByCriteria(ParameterBudgetingRekening searchBean) throws GeneralBOException;
    public void saveAdd(ParameterBudgetingRekening bean) throws GeneralBOException;
    public void saveEdit(ParameterBudgetingRekening bean) throws GeneralBOException;
    public void saveDelete(ParameterBudgetingRekening bean) throws GeneralBOException;

}
