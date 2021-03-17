package com.neurix.simrs.transaksi.asesmengizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenGizi;

import java.util.List;

public interface AsesmenGiziBo {
    public List<AsesmenGizi> getByCriteria(AsesmenGizi bean) throws GeneralBOException;
    public void saveAdd(List<AsesmenGizi> bean) throws GeneralBOException;
    public void saveDelete(AsesmenGizi bean) throws GeneralBOException;
}
