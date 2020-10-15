package com.neurix.simrs.master.tindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.HeaderTindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import java.util.List;

public interface HeaderTindakanBo {
    public List<HeaderTindakan> getByCriteria(HeaderTindakan bean) throws GeneralBOException;
    public CrudResponse saveAdd(HeaderTindakan bean) throws GeneralBOException;
    public CrudResponse saveEdit(HeaderTindakan bean) throws GeneralBOException;
}