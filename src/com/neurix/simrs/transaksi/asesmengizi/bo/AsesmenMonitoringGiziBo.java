package com.neurix.simrs.transaksi.asesmengizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenMonitoringGizi;

import java.util.List;

public interface AsesmenMonitoringGiziBo {
    public List<AsesmenMonitoringGizi> getByCriteria(AsesmenMonitoringGizi bean) throws GeneralBOException;
    public void saveAdd(AsesmenMonitoringGizi bean) throws GeneralBOException;
    public void saveDelete(AsesmenMonitoringGizi bean) throws GeneralBOException;
}
