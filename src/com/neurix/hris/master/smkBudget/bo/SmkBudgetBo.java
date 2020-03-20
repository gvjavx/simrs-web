package com.neurix.hris.master.smkBudget.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkBudget.model.SmkBudget;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SmkBudgetBo extends BaseMasterBo<SmkBudget>{
    public void saveDelete(SmkBudget bean) throws GeneralBOException;

    public List<SmkBudget> getComboSmkBudgetWithCriteria(String query) throws GeneralBOException;
    public List<SmkBudget> getCalculatePoint(double bobot,double target,double realisasi) throws GeneralBOException;

}
