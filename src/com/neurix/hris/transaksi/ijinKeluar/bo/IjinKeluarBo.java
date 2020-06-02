package com.neurix.hris.transaksi.ijinKeluar.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggota;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface IjinKeluarBo extends BaseMasterBo<IjinKeluar> {
    public void saveDelete(IjinKeluar bean) throws GeneralBOException;
    public void saveEdit(IjinKeluar bean) throws GeneralBOException;

    void saveEditIjinKeluarKantor(IjinKeluar bean) throws GeneralBOException;

    List<Notifikasi> saveAddIjinKeluar(IjinKeluar bean) throws GeneralBOException;

    List<IjinKeluar> getByCriteriaForAbsensi(IjinKeluar bean, String tanggal);

    public List<IjinKeluar> getComboIjinKeluarWithCriteria(String query) throws GeneralBOException;
    public List<IjinKeluar> getBiodatawithCriteria(String query) throws GeneralBOException;

    List<IjinKeluarAnggota> getijinKeluarAnggota(String id) throws GeneralBOException;

    public List<IjinKeluar> getComboSisaIjinKeluarWithCriteria(String query) throws GeneralBOException;
    public List getComboTestTanggal(String nip, String tanggalAwal, String tanggalSelesai) throws GeneralBOException;
    public List<Notifikasi> saveApprove(IjinKeluar bean) throws GeneralBOException;
    List<Object[]> findInfoIjin(String idIjinPegawai, String nip) throws GeneralBOException;
    List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException;

    List<IjinKeluar> getByCriteriaForAbsensiIjinTidakMasuk(IjinKeluar bean, String tanggal);
    public List getListDispensasiMasal(String unit);

    public void saveAddDispensasiMasal(IjinKeluar bean);

    public String getNextSuratDokterId()throws GeneralBOException;

    public String cekIfAbsensi(String nip, String tglDari, String tglSelesai);
}