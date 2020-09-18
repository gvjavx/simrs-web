package com.neurix.hris.master.statusMutasi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusMutasi.model.StatusMutasi;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StatusMutasiBo extends BaseMasterBo<StatusMutasi> {
    public void saveDelete(StatusMutasi bean) throws GeneralBOException;
    public StatusMutasi getStatusMutasiById(String tipePayrollId) throws GeneralBOException;
    
}
