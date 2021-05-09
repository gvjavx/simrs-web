package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo;

import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKas;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgettingDTO;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.*;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface LaporanAkuntansiBo extends BaseMasterBo<LaporanAkuntansi>{

    LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException;

    List<Aging> getAging(String branch, String periode, String masterId, String tipeAging, String reportId,String tipeLaporan) throws GeneralBOException;

    List<PendapatanDTO> getPendapatan(String reportId, String unit, String periode, String tipeLaporan) throws GeneralBOException;

    List<AkunSettingReportKeuanganArusKas> getArusKas(String reportId, String unit, String periode) throws GeneralBOException;

    List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchId5, String branchId6, String branchId7, String branchIdAll);

    List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsolUnit(String periode, String branchId1);

    LaporanAkuntansi getById(String reportId);

    String levelKodeRekening(String reportId) throws GeneralBOException;

    String getKodeRekeningkas() throws GeneralBOException;

    List<BudgettingDTO> getBudgetting(String tipeLaporan, String unit, String tahun);

    List<NeracaSaldoDTO> getListNeracaSaldo(String reportId, String periode, String branchId);

    List<NeracaSaldoDTO> getListNeracaMutasi(String reportId, String periode, String branchId);

    List<IkhtisarBukuBesarDTO> getListIkhitisarBukuBesar(String reportId, String periode, String branchId);

    List<KartuBukuBesarPerBukuBantuDTO> getListKartuBukuBesar (String reportId, String periode, String branchId, String kodeRekening, String nomorMaster, String tipe, Date tanggalDari, Date tanggalSampai);

    BigDecimal saldoAwalKodeRekening(String branchId, String kodeRekening, String masterId, String periode);

    BigDecimal saldoAwalKodeRekeningByTanggal(String branchId, String kodeRekening, String masterId, String periode, String tanggalAwal);
}
