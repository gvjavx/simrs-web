package com.neurix.hris.transaksi.absensi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.absensi.model.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface AbsensiBo extends BaseMasterBo<AbsensiPegawai> {
    void saveApprove(AbsensiPegawai bean) throws GeneralBOException;

    void saveAddKeterangan(AbsensiPegawai bean) throws GeneralBOException;

    List getAllDataFromMesin() throws Exception;

    void getDataInquiryForCronJob() throws Exception;

    PegawaiTambahanAbsensi saveTambahan(PegawaiTambahanAbsensi bean) throws GeneralBOException;

    List<AbsensiPegawaiEntity> saveTmp(AbsensiPegawai bean , String tipeHari) throws GeneralBOException;

    List<AbsensiPegawaiEntity> getLemburBonusSatpam(List<AbsensiPegawaiEntity> absensiPegawaiEntityList) throws GeneralBOException;

    List<AbsensiPegawai> getByCriteriaForReport(AbsensiPegawai searchBean) throws GeneralBOException;

    List<AbsensiPegawai> getByCriteriaForReportUangMakan(AbsensiPegawai searchBean) throws GeneralBOException;

    List<PegawaiTambahanAbsensi> getByCriteriaForReportUangMakanTambahan(PegawaiTambahanAbsensi searchBean) throws GeneralBOException;

    List<AbsensiPegawai> getByCriteriaForLembur(AbsensiPegawai searchBean) throws GeneralBOException;

    String refreshDataAbsensi(String nip, Date tanggal);

    List<AbsensiPegawai> getByCriteriaForRekapLembur(AbsensiPegawai searchBean) throws GeneralBOException;

    List<MesinAbsensi> getByCriteriaMesin(MesinAbsensi searchBean) throws GeneralBOException;
    public List<AbsensiPegawai> cariAbseniSys(String nip, String tanggal, String statusabsensi) throws GeneralBOException;

    List getDataFromMesin() throws Exception;

    List<MesinAbsensi> inquiry(String tanggal,Boolean awalTanggal, String statusPegawai, String branchId) throws Exception;

    List<PegawaiTambahanAbsensi> inquiryTambahan(String tanggal, Boolean awalTanggal) throws Exception;

    String refreshDataLembur(String nip, Date tanggal, String jamAwal, String jamAkhir);

    String sesuaikanDataLembur(String nip, Date tanggal, String jamAwal, String jamAkhir, String pengajuan);

    String addAbsensiLembur(String nip, Date tanggal, String jamAwal, String jamAkhir, String pengajuan);

    List<MesinAbsensiDetail> getByCriteriaAbsensiDetail(MesinAbsensiDetail searchBean, String tanggal) throws GeneralBOException;

    List<MesinAbsensiDetail> getByCriteriaAbsensiDetailAll(MesinAbsensiDetail searchBean, String tanggal) throws GeneralBOException;

    List<AbsensiPegawai> searchDetailLembur(String nip, String tanggal) throws GeneralBOException;

    AbsensiPegawai getJamLembur(double lamaLembur, String tipeHari);

    String cariStatusTidakMasuk(String nip, Date tanggal);

    List<PegawaiTambahan> getDataPegawaiTambahan(String bagian);

    List<AbsensiTriwulanDTO> searchBiodataForTriwulan(String branchId, String nip, String stTanggalAwal, String stTanggalAkhir, String bagian);

    AbsensiPegawai getJadwalShiftKerja(String nip, Date tanggal);
}
