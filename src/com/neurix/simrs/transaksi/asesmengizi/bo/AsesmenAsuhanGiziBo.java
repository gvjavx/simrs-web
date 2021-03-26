package com.neurix.simrs.transaksi.asesmengizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenAsuhanGizi;

import java.util.List;

public interface AsesmenAsuhanGiziBo {
    public List<AsesmenAsuhanGizi> getByCriteria(AsesmenAsuhanGizi bean) throws GeneralBOException;
    public void saveAdd(AsesmenAsuhanGizi bean) throws GeneralBOException;
    public void saveDelete(AsesmenAsuhanGizi bean) throws GeneralBOException;
}
