package com.neurix.akuntansi.master.masterVendor.bo;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

public interface MasterVendorBo extends BaseMasterBo<MasterVendor> {
    public void saveDelete(MasterVendor bean) throws GeneralBOException;
}
