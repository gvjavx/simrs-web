package com.neurix.simrs.transaksi.fisioterapi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;
import com.neurix.simrs.transaksi.fisioterapi.model.MonitoringFisioterapi;

import java.util.List;

public interface MonitoringFisioterapiBo {
    public List<MonitoringFisioterapi> getByCriteria(MonitoringFisioterapi bean) throws GeneralBOException;
    public CrudResponse saveAdd(MonitoringFisioterapi bean) throws GeneralBOException;
}
