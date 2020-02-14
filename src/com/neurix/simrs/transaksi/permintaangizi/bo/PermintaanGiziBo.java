package com.neurix.simrs.transaksi.permintaangizi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;

import java.util.List;

public interface PermintaanGiziBo {
    public List<RawatInap> getListOrderGizi(RawatInap bean) throws GeneralBOException;
    public CheckResponse updateApproveFlag(OrderGizi orderGizi) throws GeneralBOException;
}
