package com.neurix.simrs.master.vendor.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.vendor.model.Vendor;

import java.util.List;

public interface VendorBo {
    public List<Vendor> getByCriteria(Vendor bean)throws GeneralBOException;
}