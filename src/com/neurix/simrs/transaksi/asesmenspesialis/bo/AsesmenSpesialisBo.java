package com.neurix.simrs.transaksi.asesmenspesialis.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenspesialis.model.AsesmenSpesialis;

import java.util.List;

public interface AsesmenSpesialisBo {
    public List<AsesmenSpesialis> getByCriteria(AsesmenSpesialis bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<AsesmenSpesialis> list) throws GeneralBOException;
    public CrudResponse saveDelete(AsesmenSpesialis bean) throws GeneralBOException;
}
