package com.neurix.simrs.transaksi.mpp.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenugd.model.AsesmenUgd;
import com.neurix.simrs.transaksi.mpp.model.Mpp;

import java.util.List;

public interface MppBo {
    public List<Mpp> getByCriteria(Mpp bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<Mpp> list) throws GeneralBOException;
    public CrudResponse saveDelete(Mpp bean) throws GeneralBOException;
}
