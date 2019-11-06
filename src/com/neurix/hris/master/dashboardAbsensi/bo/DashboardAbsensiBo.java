package com.neurix.hris.master.dashboardAbsensi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.dashboardAbsensi.model.DashboardAbsensi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface DashboardAbsensiBo extends BaseMasterBo<DashboardAbsensi>{
    // <chart> get bagian
    public List<DashboardAbsensi>getKaryawanTelat(String bulan, String tahun) throws GeneralBOException;
    public List<DashboardAbsensi>getKaryawanAbsensiTelat(String tanggal) throws GeneralBOException;
    public List<DashboardAbsensi>getKaryawanAbsensiMangkir(String tanggal) throws GeneralBOException;
    public List<DashboardAbsensi>getKaryawanAbsensiRajin(String tanggal) throws GeneralBOException;

    // <Chart> karyawan telat By NIP
    public List<DashboardAbsensi>getKaryawanTelat(String bulan, String tahun, String nip) throws GeneralBOException;

    // Medical records
    public List<DashboardAbsensi>dashboardMedical(String bulan, String tahun) throws GeneralBOException;
    public List<DashboardAbsensi>dashboardMedical(String bulan, String tahun, String divisiId) throws GeneralBOException;
    public List<DashboardAbsensi>dashboardMedicalRecordsTop(String bulan, String tahun, String divisiName) throws GeneralBOException;
    public List<DashboardAbsensi>dashboardLembur(String bulan, String tahun) throws GeneralBOException;
    public List<DashboardAbsensi>dashboardLembur(String bulan, String tahun, String divisiId) throws GeneralBOException;
    public List<DashboardAbsensi>dashboardLemburTop(String bulan, String tahun, String divisiName) throws GeneralBOException;
}
