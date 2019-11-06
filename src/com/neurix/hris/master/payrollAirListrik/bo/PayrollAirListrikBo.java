package com.neurix.hris.master.payrollAirListrik.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollAirListrik.model.PayrollAirListrik;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollAirListrikBo extends BaseMasterBo<PayrollAirListrik>{
    public void saveDelete(PayrollAirListrik bean) throws GeneralBOException;
}
