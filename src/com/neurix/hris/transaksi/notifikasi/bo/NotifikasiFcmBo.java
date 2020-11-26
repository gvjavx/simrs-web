package com.neurix.hris.transaksi.notifikasi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;

import java.util.List;

public interface NotifikasiFcmBo extends BaseMasterBo<NotifikasiFcm> {
    public List<NotifikasiFcm> getByCriteria(NotifikasiFcm bean) throws GeneralBOException ;
}
