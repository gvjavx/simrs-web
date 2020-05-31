package com.neurix.hris.transaksi.cutiPegawai.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface CutiPegawaiBo extends BaseMasterBo<CutiPegawai> {
    public void saveDelete(CutiPegawai bean) throws GeneralBOException;
    public void saveEdit(CutiPegawai bean) throws GeneralBOException;

    public void saveCancel(CutiPegawai bean) throws GeneralBOException;

    List<Notifikasi> saveAddCuti(CutiPegawai bean) throws GeneralBOException;

    CutiPegawai saveCutiBersama(CutiPegawai bean) throws GeneralBOException;

    List<CutiPegawai> getByCriteriaForAbsensi(CutiPegawai bean, String tanggal);

    public List<CutiPegawai> getComboCutiPegawaiWithCriteria(String query) throws GeneralBOException;
    public List<CutiPegawai> getBiodatawithCriteria(String query) throws GeneralBOException;

    List<CutiPegawai> getListCekNipCuti(String query) throws GeneralBOException;

    List<CutiPegawai> getHistoryCuti(String nip, String cutiId) throws GeneralBOException;

    public List<CutiPanjang> getComboCutiPanjangFull(String golonganId, String branchId) throws GeneralBOException;

    public List<CutiPegawai> getComboSisaCutiPegawaiWithCriteria(String query,String cutiId,String branchId) throws GeneralBOException;

    List<CutiPegawai> getCutiUser(CutiPegawai bean);

    BigInteger sisaCutiTahunan(CutiPegawai search);

    BigInteger sisaCutiPanjang(CutiPegawai search);

    public List<CutiPegawai> getComboSisaCutiPegawaiWithCriteria(String query) throws GeneralBOException;
    public List<CutiPegawai> sisaCutiSys(String nip) throws GeneralBOException;

    public List getComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) throws GeneralBOException;

    List getCriteriaForResetCuti(String unit) throws GeneralBOException;

    List getCriteriaForCutiBersama(String unit) throws GeneralBOException;

    List getCriteriaForInisialisasiCuti(String unit) throws GeneralBOException;

    public CutiPegawai getSisaCuti(String nip) throws GeneralBOException;

    public List<Notifikasi> saveApprove(CutiPegawai bean) throws GeneralBOException;
    //public CutiPegawai saveAdd(CutiPegawai bean, SppdPerson bean2) throws GeneralBOException ;
    List<Object[]> findInfoCuti(String idCutiPegawai, String nip) throws GeneralBOException;
    List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException;

    List<CutiPegawai> getListCutiForReport(CutiPegawai bean);

    List<CutiPegawai> getListSetCuti(String nip);

    List<CutiPegawai> getListSetCuti2(String nip, String jenisCuti);

    List getListCutiForView(String nip) throws GeneralBOException;
    public String findCutiAktif(String branchId);
    public String getBagianPegawai(String positionId);
    public String cekIfAbsensi(String nip, String tglDari, String tglSelesai);
    public void editSisaCuti(CutiPegawai bean);
    public String getKabidSdmUmum(String branchId);
    public String getTanggalPensiun(String nip);
}
