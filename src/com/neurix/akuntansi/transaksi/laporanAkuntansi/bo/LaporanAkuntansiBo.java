package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

public interface LaporanAkuntansiBo extends BaseMasterBo<LaporanAkuntansi>{

    LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException;
}
