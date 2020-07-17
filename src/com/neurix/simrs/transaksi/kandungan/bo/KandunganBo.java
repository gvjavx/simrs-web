package com.neurix.simrs.transaksi.kandungan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;

import java.util.List;

public interface KandunganBo {
    public List<Kandungan> getByCriteria(Kandungan bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<Kandungan> list) throws GeneralBOException;
}
