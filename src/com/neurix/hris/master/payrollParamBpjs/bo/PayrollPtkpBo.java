package com.neurix.hris.master.payrollParamBpjs.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollPtkpBo extends BaseMasterBo<PayrollPtkp>{
    public void saveDelete(PayrollPtkp bean) throws GeneralBOException;
}
