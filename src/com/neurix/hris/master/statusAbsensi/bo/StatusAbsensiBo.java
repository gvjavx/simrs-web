package com.neurix.hris.master.statusAbsensi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.statusAbsensi.model.StatusAbsensi;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StatusAbsensiBo extends BaseMasterBo<StatusAbsensi> {
    public void saveDelete(StatusAbsensi bean) throws GeneralBOException;
    public StatusAbsensi getStatusAbsensiById(String tipePayrollId) throws GeneralBOException;
    
}
