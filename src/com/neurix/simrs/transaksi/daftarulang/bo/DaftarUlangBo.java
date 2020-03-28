package com.neurix.simrs.transaksi.daftarulang.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import java.util.List;

public interface DaftarUlangBo {
    public List<HeaderDetailCheckup> getListDaftarUlangPasien(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException;
    public CheckResponse saveDaftarUlang(HeaderDetailCheckup bean) throws GeneralBOException;
}
