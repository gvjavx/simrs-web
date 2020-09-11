package com.neurix.simrs.transaksi.catatanterintegrasi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.CatatanTerintegrasi;

import java.util.List;

public interface CatatanTerintegrasiBo {
    public List<CatatanTerintegrasi> getByCriteria(CatatanTerintegrasi bean) throws GeneralBOException;
    public CrudResponse saveAdd(CatatanTerintegrasi bean) throws GeneralBOException;
    public CrudResponse saveDelete(CatatanTerintegrasi bean) throws GeneralBOException;
}
