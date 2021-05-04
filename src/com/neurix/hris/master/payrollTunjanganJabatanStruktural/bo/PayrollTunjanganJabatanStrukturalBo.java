package com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.payroll.model.PayrollTunjanganJabatanStruktural;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollTunjanganJabatanStrukturalBo extends BaseMasterBo<PayrollTunjanganJabatanStruktural>{
    public void saveDelete(PayrollTunjanganJabatanStruktural bean) throws GeneralBOException;
}
