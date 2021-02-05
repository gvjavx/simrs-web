package com.neurix.simrs.master.license.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.license.model.VersionMobile;

import java.util.List;

public interface VersionMobileBo  {
    public List<VersionMobile> getByCriteria(VersionMobile bean) throws GeneralBOException;
    public void saveAdd(VersionMobile bean) throws GeneralBOException;
}
