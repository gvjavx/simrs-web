package com.neurix.simrs.transaksi.icu.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import com.neurix.simrs.transaksi.icu.model.DetailIcu;

import java.util.List;

public interface DetailIcuBo {
    public List<DetailIcu> getByCriteria(DetailIcu bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<DetailIcu> list) throws GeneralBOException;
}
