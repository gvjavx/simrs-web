package com.neurix.simrs.transaksi.icu.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;

import java.util.List;

public interface DetailIcuBo {
    public List<Hemodialisa> getByCriteria(Hemodialisa bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<Hemodialisa> list) throws GeneralBOException;
}