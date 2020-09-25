package com.neurix.simrs.master.labdetail.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;

import java.util.List;

public interface LabDetailBo extends BaseMasterBo<LabDetail>{
    public List<LabDetail> getByCriteria(LabDetail bean) throws GeneralBOException;
    public ImSimrsLabDetailEntity getLabDetailEntityById(String idParameter) throws GeneralBOException;
    public List<LabDetail> getDetaillab(String idLab, String branchId) throws GeneralBOException;
}