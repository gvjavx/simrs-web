package com.neurix.simrs.bpjs.fingerPrint.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.bpjs.fingerPrint.model.FingerPrint;

import java.util.List;

public interface FingerPrintBo{
    public List<FingerPrint> getByCriteria(FingerPrint searchBean) throws GeneralBOException;
}