package com.neurix.simrs.master.vendor.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;

import java.util.List;

public interface VendorBo {
    public List<Vendor> getByCriteria(Vendor bean) throws GeneralBOException;
    public CheckResponse saveAdd(Vendor bean) throws GeneralBOException;
    public CheckResponse saveEdit(Vendor bean) throws GeneralBOException;
    public ImSimrsVendorEntity getEntityVendorById(String idVendor) throws GeneralBOException;
}