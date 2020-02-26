package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface LaporanAkuntansiBo extends BaseMasterBo<LaporanAkuntansi>{

    LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException;

    List<Aging> getAging(String branch, String periode, String masterId, String tipeAging, String reportId) throws GeneralBOException;
}