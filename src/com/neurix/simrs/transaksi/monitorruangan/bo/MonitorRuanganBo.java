package com.neurix.simrs.transaksi.monitorruangan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.ruangan.model.Ruangan;

import java.util.List;

public interface MonitorRuanganBo {

    public List<Ruangan> getListRuangan(Ruangan bean) throws GeneralBOException;
}
