package com.neurix.simrs.transaksi.hemodialisa.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.MonitoringTransfusiDarah;

import java.util.List;

public interface MonitoringTransfusiDarahBo {
    public List<MonitoringTransfusiDarah> getByCriteria(MonitoringTransfusiDarah bean) throws GeneralBOException;
    public CrudResponse saveAdd(MonitoringTransfusiDarah bean) throws GeneralBOException;
    public CrudResponse saveDelete(MonitoringTransfusiDarah bean) throws GeneralBOException;
}
