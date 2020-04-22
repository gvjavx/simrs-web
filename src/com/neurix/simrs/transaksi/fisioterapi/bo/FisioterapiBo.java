package com.neurix.simrs.transaksi.fisioterapi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;
import java.util.List;

public interface FisioterapiBo {
    public List<Fisioterapi> getByCriteria(Fisioterapi bean) throws GeneralBOException;
    public CrudResponse saveAdd(Fisioterapi bean) throws GeneralBOException;
}
