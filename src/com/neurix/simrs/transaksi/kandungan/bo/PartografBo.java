package com.neurix.simrs.transaksi.kandungan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.model.Partograf;

import java.util.List;

public interface PartografBo {
    public List<Partograf> getByCriteria(Partograf bean) throws GeneralBOException;
    public CrudResponse saveAdd(Partograf bean) throws GeneralBOException;
    public CrudResponse saveDelete(Partograf bean) throws GeneralBOException;
    public List<Partograf> getListByDate(String idDetailCheckup, String tanggal) throws GeneralBOException;
}
