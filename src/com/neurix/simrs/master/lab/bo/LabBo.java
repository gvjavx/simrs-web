package com.neurix.simrs.master.lab.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.model.Lab;

import java.util.List;

public interface LabBo extends BaseMasterBo<Lab>{
    public List<Lab> getByCriteria(Lab bean) throws GeneralBOException;
}