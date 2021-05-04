package com.neurix.hris.master.payrollMasaKerjaPensiun.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollMasaKerjaPensiun.model.PayrollMasaKerjaPensiun;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollMasaKerjaPensiunBo extends BaseMasterBo<PayrollMasaKerjaPensiun>{
    public void saveDelete(PayrollMasaKerjaPensiun bean) throws GeneralBOException;
}
