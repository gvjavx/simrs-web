package com.neurix.simrs.transaksi.statusperiksa.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import java.util.List;

public interface StatusPeriksaBo {
    public List<HeaderDetailCheckup> getListStatusPeriksa(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException;
    public CheckResponse saveEditPerubahanStatus(HeaderDetailCheckup bean) throws GeneralBOException;
}
