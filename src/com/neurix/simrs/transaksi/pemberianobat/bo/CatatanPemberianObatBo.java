package com.neurix.simrs.transaksi.pemberianobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pemberianobat.model.CatatanPemberianObat;

import java.util.List;

public interface CatatanPemberianObatBo {
    public List<CatatanPemberianObat> getByCriteria(CatatanPemberianObat bean) throws GeneralBOException;
    public CrudResponse saveAdd(CatatanPemberianObat bean) throws GeneralBOException;
    public CrudResponse saveDelete(CatatanPemberianObat bean) throws GeneralBOException;
    public void saveUpdate(CatatanPemberianObat bean) throws GeneralBOException;
}
