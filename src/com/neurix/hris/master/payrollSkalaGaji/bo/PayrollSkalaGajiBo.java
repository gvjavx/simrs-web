package com.neurix.hris.master.payrollSkalaGaji.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollSkalaGaji.model.PayrollSkalaGaji;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollSkalaGajiBo extends BaseMasterBo<PayrollSkalaGaji>{
    public void saveDelete(PayrollSkalaGaji bean) throws GeneralBOException;
}
