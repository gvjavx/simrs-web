package com.neurix.simrs.transaksi.cetakanrm.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.cetakanrm.model.CetakanRm;

import java.util.List;

public interface CetakanRmBo {
    public List<CetakanRm> getByCriteria(CetakanRm bean) throws GeneralBOException;
    public CrudResponse saveAdd(CetakanRm bean) throws GeneralBOException;
}
