package com.neurix.simrs.transaksi.ordergizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.ordergizi.model.PendampingGizi;

import java.util.List;

public interface PendampingGiziBo {
    public List<PendampingGizi> getByCriteria(PendampingGizi bean) throws GeneralBOException;
    public void saveAdd(List<PendampingGizi> bean) throws GeneralBOException;
    public void saveEdit(PendampingGizi bean) throws GeneralBOException;
}
