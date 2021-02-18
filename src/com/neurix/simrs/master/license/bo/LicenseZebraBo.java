package com.neurix.simrs.master.license.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.license.model.LicenseZebra;
import com.neurix.simrs.master.license.model.Version;

import java.util.List;

public interface LicenseZebraBo {

    //LICENSE ZEBRA
    public List<LicenseZebra> getByCriteria(LicenseZebra bean) throws GeneralBOException;
    public boolean isKeyAvailable(String licenseKey, String deviceId) throws GeneralBOException;
    public void updateFlag(LicenseZebra bean) throws GeneralBOException;
    public void saveAdd(LicenseZebra bean) throws GeneralBOException;
    public void saveEdit(LicenseZebra bean) throws GeneralBOException;

    //VERSION
    public List<Version> getVersionByCriteria(Version bean) throws GeneralBOException;
    public void saveAddVersion(Version bean) throws GeneralBOException;
}