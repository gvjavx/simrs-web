package com.neurix.simrs.transaksi.fisioterapi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;
import com.neurix.simrs.transaksi.fisioterapi.model.MonitoringFisioterapi;

import java.util.List;

public interface FisioterapiBo {
    public List<Fisioterapi> getByCriteria(Fisioterapi bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<Fisioterapi> list) throws GeneralBOException;
    public CrudResponse saveDelete(Fisioterapi bean) throws GeneralBOException;
    public List<MonitoringFisioterapi> getKunjunganFisio(String idPasien, String branchId) throws GeneralBOException;
}
