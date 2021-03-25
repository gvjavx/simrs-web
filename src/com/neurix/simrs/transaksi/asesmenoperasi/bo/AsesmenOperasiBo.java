package com.neurix.simrs.transaksi.asesmenoperasi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;

import java.util.List;

public interface AsesmenOperasiBo {
    public List<AsesmenOperasi> getByCriteria(AsesmenOperasi bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<AsesmenOperasi> list) throws GeneralBOException;
    public CrudResponse saveDelete(AsesmenOperasi bean) throws GeneralBOException;
    public String getDataByKey(String id, String params) throws GeneralBOException;
    public void saveEdit(AsesmenOperasi bean) throws GeneralBOException;
}
