package com.neurix.hris.master.tipePayroll.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipePayroll.model.TipePayroll;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TipePayrollBo extends BaseMasterBo<TipePayroll> {
    public void saveDelete(TipePayroll bean) throws GeneralBOException;
    public TipePayroll getTipePayrollById(String tipePayrollId) throws GeneralBOException;
    
}
