package com.neurix.simrs.transaksi.respirasi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.respirasi.model.Respirasi;

import java.util.List;

public interface RespirasiBo {

    public List<Respirasi> getByCriteria(Respirasi bean) throws GeneralBOException;
    public CrudResponse saveAdd(Respirasi bean) throws GeneralBOException;
    public Boolean cekData(String id, String waktu) throws GeneralBOException;
    public CrudResponse saveDelete(Respirasi bean) throws GeneralBOException;
}
