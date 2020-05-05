package com.neurix.akuntansi.master.pembayaran.bo;

import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface PembayaranBo extends BaseMasterBo<Pembayaran>{
    public List<Pembayaran> getByCriteria(Pembayaran bean) throws GeneralBOException;
}
