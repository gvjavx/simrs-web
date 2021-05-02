package com.neurix.simrs.master.operatorlab.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;

import java.util.List;

public interface OperatorLabBo extends BaseMasterBo<OperatorLab>{

    public List<OperatorLab> getByCriteria(OperatorLab bean) throws GeneralBOException;

}