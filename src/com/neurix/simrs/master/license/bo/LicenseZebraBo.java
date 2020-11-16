package com.neurix.simrs.master.license.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.license.model.LicenseZebra;

import java.util.List;

public interface LicenseZebraBo {
    public List<LicenseZebra> getByCriteria(LicenseZebra bean) throws GeneralBOException;
    public boolean isKeyAvailable(String licenseKey, String deviceId) throws GeneralBOException;
    public void updateFlag(LicenseZebra bean) throws GeneralBOException;
}