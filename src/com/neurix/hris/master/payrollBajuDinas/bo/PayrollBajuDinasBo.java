package com.neurix.hris.master.payrollBajuDinas.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollBajuDinas.model.PayrollBajuDinas;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollBajuDinasBo extends BaseMasterBo<PayrollBajuDinas>{
    public void saveDelete(PayrollBajuDinas bean) throws GeneralBOException;

    public List<PayrollBajuDinas> getComboSmkBudgetWithCriteria(String query) throws GeneralBOException;
    public List<PayrollBajuDinas> getCalculatePoint(double bobot, double target, double realisasi) throws GeneralBOException;

}
