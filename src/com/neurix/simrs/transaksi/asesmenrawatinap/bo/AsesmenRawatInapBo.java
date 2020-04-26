package com.neurix.simrs.transaksi.asesmenrawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;

import java.util.List;

public interface AsesmenRawatInapBo {
    public List<AsesmenRawatInap> getByCriteria(AsesmenRawatInap bean) throws GeneralBOException;
    public CrudResponse saveAdd(AsesmenRawatInap bean) throws GeneralBOException;
}
