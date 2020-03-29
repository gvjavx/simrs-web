package com.neurix.hris.transaksi.payroll.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtEntity;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.payroll.model.*;

import com.neurix.hris.transaksi.smk.model.SmkHistoryEvaluasiPegawai;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PayrollBo extends BaseMasterBo<Payroll>{
    public void saveDelete(Payroll bean) throws GeneralBOException;
    public void saveAddData(List<Payroll> payroll, Payroll bean) throws GeneralBOException;
    public List<Payroll> getSearchHome(Payroll bean) throws GeneralBOException;
    public List<PayrollInsentif> getSearchInsentif(Payroll bean) throws GeneralBOException;
    public List<Payroll> getCsvPajak(Payroll bean) throws GeneralBOException;
    public Payroll getDetailEditSys(String payrollId) throws GeneralBOException;

    ItPayrollEntity getPayrollById(String payrollId) throws GeneralBOException;

    public List<Payroll> getDataView(Payroll bean) throws GeneralBOException;
    public List<Payroll> dataAddPayroll(Payroll bean) throws GeneralBOException;
    public List<AbsensiPegawai> dataAbsensiLembur(AbsensiPegawai bean) throws GeneralBOException;
    public PayrollJubileum searchJubileumMasaKerjaDetailSys(PayrollJubileum bean) throws GeneralBOException;
    public Payroll getPayrollApprove(Payroll bean) throws GeneralBOException;

    public PayrollPph getDetailEditPphSys(String payrollId) throws GeneralBOException;
    // dwr. pph pengobatan
    public PayrollPph getDetailEditPphPengobatanSys(String tahun, String nip) throws GeneralBOException;
    public PayrollRapel getDetailEditRapelSys(String payrollId) throws GeneralBOException;
    public PayrollRapelThr getDetailEditRapelThrSys(String rapelId) throws GeneralBOException;
    public PayrollRapelJubileum getDetailEditRapelJubileumSys(String rapelId) throws GeneralBOException;
    public PayrollRapelPendidikan getDetailEditRapelPendidikanSys(String rapelId) throws GeneralBOException;
    public PayrollRapelInsentif getDetailEditRapelInsentifSys(String rapelId) throws GeneralBOException;
    public List<PayrollRapelLembur> getDetailEditRapelLemburSys(String rapelId) throws GeneralBOException;
    public PayrollThr getDetailEditThrSys(String payrollId) throws GeneralBOException;
    public PayrollPendidikan getDetailEditPendidikanSys(String payrollId) throws GeneralBOException;
    public PayrollJasprod getDetailEditJasprodSys(String payrollId) throws GeneralBOException;
    public PayrollInsentif getDetailEditInsentifSys(String payrollId) throws GeneralBOException;
    public PayrollJubileum getDetailEditJubileumSys(String payrollId) throws GeneralBOException;
    public PayrollPensiun getDetailEditPensiunSys(String payrollId) throws GeneralBOException;
    public void approvePayroll(Payroll bean) throws GeneralBOException;
    public List<Payroll> copyDataPayroll(List<Payroll> bean) throws GeneralBOException;
    public void saveEditDataSessionSys(Payroll payroll) throws GeneralBOException;
    public void saveEditSessionDataUsingPayrollIdSys(Payroll payroll) throws GeneralBOException;
    public void saveEditSessionDataUsingPayrollIdJasprodSys(Payroll payroll) throws GeneralBOException;
    public void editDataSys(Payroll payroll) throws GeneralBOException;
    //public void potonganLainSys(List<PayrollPotonganLain> potonganLainList) throws GeneralBOException;
    public void saveEditDataSys(Payroll bean) throws GeneralBOException;
    public void saveEditDataJasprodSys(Payroll bean) throws GeneralBOException;
    public void saveEditDataJanuari(Payroll bean) throws GeneralBOException;
    public List<PayrollUpahHarian> upahHarianSys(PayrollUpahHarian upahHarian) throws GeneralBOException;
    public List<PayrollUpahHarian> upahHarianDataSys(PayrollUpahHarian upahHarian) throws GeneralBOException;
    public void updateUpahHarianDataSys(PayrollUpahHarian upahHarian) throws GeneralBOException;

    public void removeDataGajiPkwtSys(String payrollId) throws GeneralBOException;
    public void saveDataGajiPkwtSys(PayrollUpahHarian payrollUpahHarian) throws GeneralBOException;

    public String cekAvailableSys(Payroll bean) throws GeneralBOException;
    public String cekBeforePayrollSys(Payroll bean) throws GeneralBOException;
    public String cekApproveSys(Payroll bean) throws GeneralBOException;
    public PayrollPotonganLain getDataPotonganLainSys(String payrollId) throws GeneralBOException;
    public void savePotonganLainData(PayrollPotonganLain bean) throws GeneralBOException;
    public String cekJubileumSys(String nip) throws GeneralBOException;
    public String cekPensiunSys(String nip) throws GeneralBOException;

    public List<SmkHistoryEvaluasiPegawai> listPromosiSys(String nip) throws GeneralBOException;
    public String getDirektur() throws GeneralBOException;
    public String getKabidSdm() throws GeneralBOException;

    void approvePayrollUnit(Payroll bean) throws GeneralBOException;

    public PayrollKonsistensi showKonsistensiGaji(String nip, String bulan, String tahun) throws GeneralBOException;
    public List<Payroll> printReportPayrollBulanSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollPotonganDinasSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollPotonganLainLainSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollPenghasilanKaryawanSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;

    public List<Payroll> printReportPayrollPendidikanSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollThrSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollJasprodSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollInsentifSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<PayrollRapelDetail> printReportPayrollRapelDetailSys(String bulan1, String tahun1, String nip) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelThrSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelJubileumSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelPendidikanSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelLemburSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;
    public List<Payroll> printReportPayrollRapelInsentifSys(String bulan1, String tahun1, String unit, String status) throws GeneralBOException;

    public List<Payroll> searchReportDanaPensiunSys(String bulan1, String tahun1, String unit) throws GeneralBOException;
    public List<Payroll> searchReportTransferGajiSys(String bulan1, String tahun1, String unit) throws GeneralBOException;
    public List<Payroll> searchReportRekapGajiSys(String bulan1, String tahun1, String unit, String statusPegawai, String strWhere) throws GeneralBOException;
    public List<Payroll> searchReportRekapThrSys(String bulan1, String tahun1, String unit, String statusPegawai, String strWhere) throws GeneralBOException;
    public List<Payroll> searchReportRekapJasprodSys(String bulan1, String tahun1, String unit, String statusPegawai, String strWhere) throws GeneralBOException;
    public List<Payroll> searchReportRekapInsentifSys(String bulan1, String tahun1, String unit, String statusPegawai, String strWhere) throws GeneralBOException;
    public List<Payroll> searchReportRekapPotonganSys(String bulan1, String tahun1, String unit, String statusPegawai, String strWhere) throws GeneralBOException;
    public List<Payroll> searchReportBpjs(String bulan1, String tahun1, String unit) throws GeneralBOException;

    public List<Payroll> searchReportTransferPendidikanSys(String bulan1, String tahun1, String unit) throws GeneralBOException;
    public List<Payroll> searchReportTransferThrSys(String bulan1, String tahun1, String unit) throws GeneralBOException;
    public List<Payroll> searchReportTransferJasprodSys(String bulan1, String tahun1, String unit) throws GeneralBOException;

    public List<PayrollJubileum> searchJubileumSys(Payroll payroll) throws GeneralBOException;
    public List<PayrollPensiun> searchPensiunSys(Payroll payroll) throws GeneralBOException;

    public void reprosesPayroll(String payrollId, String nip, String branchId, String bulan, String tahun) throws GeneralBOException;
    public BigDecimal reloadBiayaLembur(AbsensiPegawai absensiPegawai) throws GeneralBOException;

    public List<Payroll> getDetailPphTahun(String tahun, String nip) throws GeneralBOException;
    public List<PayrollRapelDetail> loadRapelDetail(String rapelId) throws GeneralBOException;

    public PayrollRapel rapelJumlahBulan(String bulan, String tahun, String unit) throws GeneralBOException;

    public String cekTunjanganInsentif(int bulanMulai, int bulanSampai, int tahun, String branchId) throws GeneralBOException;

    List<Payroll> searchReportEsptSys(String tahun, String unit) throws GeneralBOException;

    List<PayrollPendapatanPphDTO> searchReportPendapatanPph(String tahun, String unit) throws GeneralBOException;

    void saveEditPayrollPphSessionDataUsingPayrollId(Payroll payroll) throws GeneralBOException;

    void saveEditDataTambahanD(Payroll payroll) throws GeneralBOException;
    public BigDecimal getSkalaGaji(String golonganId);
    public ImPayrollTunjanganJabatanStrukturalEntity getTunjanganJabatanStrukturalSimRs(String kelompokId);
    public ImPayrollSkalaGajiEntity getSkalaGajiSimRs(String golonganId);
    public ImPayrollSkalaGajiPkwtEntity getSkalaGajiSimRsPkwt(String golonganId);

    public BigDecimal hitungIuranBpjs(BigDecimal dasarPerhitunganBpjs, Double percent);

    public List<PayrollTunjanganLain> getDetailEditTunjLainSys(String payrollId) throws GeneralBOException;

    void savePttDetail(List<Ptt> pttList, String payrollId,String nip,String bulan,String tahun) throws GeneralBOException;

    List<Ptt> getTotalLainLainSetahun(String nip, String tahun) throws GeneralBOException;

    List<PayrollModalDTO> getTotalPPh11Bulan(String nip, String tahun) throws GeneralBOException;

    PayrollModalDTO searchDetailPPhSeharusnya(String nip, String tahun, String totalA, String totalRlab, String tunjDapen, String tunjBpjsKs, String tunjBpjsTk, String iuranDapen, String iuranBpjsKs, String iuranBpjsTk, String statusKelurga, String jumlahAnak) throws GeneralBOException;

    List<Ptt> getPayrollPttByPayrollId(String payrollId) throws GeneralBOException;
}