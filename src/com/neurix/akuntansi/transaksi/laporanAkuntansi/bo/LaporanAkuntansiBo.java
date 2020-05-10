package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ArusKasDTO;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.PendapatanDTO;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface LaporanAkuntansiBo extends BaseMasterBo<LaporanAkuntansi>{

    LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException;

    List<Aging> getAging(String branch, String periode, String masterId, String tipeAging, String reportId,String tipeLaporan) throws GeneralBOException;

    List<PendapatanDTO> getPendapatan(String reportId, String unit, String periode, String tipeLaporan) throws GeneralBOException;

    List<ArusKasDTO> getArusKas(String reportId, String unit, String periode, String tipeLaporan) throws GeneralBOException;

    List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchIdAll);

    List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsolUnit(String periode, String branchId1);

    LaporanAkuntansi getById(String reportId);

    String levelKodeRekening(String reportId) throws GeneralBOException;

    String getKodeRekeningkas() throws GeneralBOException;
}
