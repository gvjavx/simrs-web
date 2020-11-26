package com.neurix.simrs.transaksi.asesmenrawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ImplementasiPerawat;

import java.util.List;

public interface ImplementasiPerawatBo {
    public List<ImplementasiPerawat> getByCriteria(ImplementasiPerawat bean) throws GeneralBOException;
    public CrudResponse saveAdd(ImplementasiPerawat bean) throws GeneralBOException;
    public CrudResponse saveDelete(ImplementasiPerawat bean) throws GeneralBOException;
}
