package com.neurix.simrs.transaksi.asesmenugd.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenugd.model.AsesmenUgd;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;

import java.util.List;

public interface AsesmenUgdBo {
    public List<AsesmenUgd> getByCriteria(AsesmenUgd bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<AsesmenUgd> list) throws GeneralBOException;
}
