package com.neurix.simrs.master.labdetail.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface LabDetailBo{
    public List<LabDetail> getByCriteria(LabDetail bean) throws GeneralBOException;
    public CrudResponse saveEdit(LabDetail bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<LabDetail> list, String isNew) throws GeneralBOException;
    public ImSimrsLabDetailEntity getLabDetailEntityById(String idParameter) throws GeneralBOException;
    public List<LabDetail> getDetaillab(String idLab, String branchId) throws GeneralBOException;
    public List<LabDetail> getDataParameterPemeriksaan(LabDetail bean) throws GeneralBOException;
}