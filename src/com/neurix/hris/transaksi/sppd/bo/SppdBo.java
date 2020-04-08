package com.neurix.hris.transaksi.sppd.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.sppd.model.*;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingPerson;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface SppdBo extends BaseMasterBo<Sppd>{
    public void saveDelete(Sppd bean) throws GeneralBOException;
    public void saveDeleteDoc(SppdDoc bean) throws GeneralBOException;

    List<SppdPerson> getByCriteriaSppdPerson(SppdPerson searchBean) throws GeneralBOException;

    public List<Sppd> getComboSppdWithCriteria(String query) throws GeneralBOException;
    public List<SppdPerson> getComboSppdPerson(String query) throws GeneralBOException;
    public List<SppdPerson> getComboSppdApproveBawahan(String query) throws GeneralBOException;
    public Boolean cekRerouteSys(String personId, String tanggal) throws GeneralBOException;
    public Boolean cekNipRerouteSys(String sppdPersonId) throws GeneralBOException;
    public String cekTanggalRerouteSys(String tanggalAwal, String tanggalAkhir, String personId) throws GeneralBOException;
    public List<SppdPerson> getComboSppdPersonApproved(String query) throws GeneralBOException;
    public List<SppdPerson> getReportSppd(String sppdId, String dinas) throws GeneralBOException;
    public List<SppdReroute> getComboSppdReroute(String SppdId) throws GeneralBOException;
    public List<SppdDoc> getComboSppdDoc(String SppdId) throws GeneralBOException;
    public List<SppdReroute> getComboSppdReroute2(String sppdId, String id) throws GeneralBOException;
    public List<SppdPerson> getComboSppdPerson(String sppdId, String personId) throws GeneralBOException;

    List<Notifikasi> saveAdd(Sppd bean, SppdPerson bean2) throws GeneralBOException;

    public List<SppdTanggal> getSppdTanggal(String sppdPersonId) throws GeneralBOException;
    public List<SppdPerson> getComboSppdPerson2(String query) throws GeneralBOException;
    public List<SppdDoc> getComboSppdDocument(String query) throws GeneralBOException;
    public SppdDoc addSppdDoc(SppdDoc bean) throws GeneralBOException ;
    public void saveEdit(SppdDoc bean) throws GeneralBOException ;
    public void saveEdit(SppdPerson bean) throws GeneralBOException ;
    public void saveEditBerangkat(SppdPerson bean) throws GeneralBOException ;
    public void saveEditReroute(SppdReroute bean) throws GeneralBOException ;
    public void saveAddReroute(SppdReroute bean) throws GeneralBOException ;
    public void saveDeleteReroute(SppdReroute bean) throws GeneralBOException ;
    public void saveApprove(SppdPerson bean) throws GeneralBOException ;
    public void saveEditSdm(Sppd bean) throws GeneralBOException ;
    public int jumlahHari(String sppdId) throws GeneralBOException ;
    public String getNextId() throws GeneralBOException ;
    List<Object[]> findInfoSppd(String idSppd, String nip) throws GeneralBOException;
    List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException;

    public boolean cekBiayaLokalSys(String lokalId) throws GeneralBOException;
    public boolean cekApproveAtasanSys(String sppdId) throws GeneralBOException;
    List<SppdPerson> getByCriteriaForAbsensi(SppdPerson bean, String tanggal);

    public void removeTanggalSppd(String sppdPersonId) throws GeneralBOException;
    public void updateTanggalSppd(String idTanggalSppd, int jumlahTanggal, int indexTanggal, BigDecimal biayaLain, BigDecimal biayaMakan) throws GeneralBOException;
    public List<TrainingPerson> cekIdTrainingSys(String idTraining) throws GeneralBOException;
    public String cekAvailableSppdSys(String nip, String tanggal1, String tanggal2) throws GeneralBOException;
    public String cekAvailableSppdSys(String nip, String tanggal1, String tanggal2, String sppdId) throws GeneralBOException;
    public String getTujuanSppd(String sppdId) throws GeneralBOException;

    public void addAnggotaSys(SppdPerson sppdPerson) throws GeneralBOException;
    public void deleteAnggotaSys(String sppdId, String nip) throws GeneralBOException;
    public void deleteSppdSys(String sppdId) throws GeneralBOException;

    public List<SppdPerson>anggotaSppd(String sppdId) throws GeneralBOException;

    // <chart> get SPPD tahun
    public List<Sppd>getSppdTahun(String tahun) throws GeneralBOException;
    public List<Sppd>getSppdTahun(String tahun, String divisi) throws GeneralBOException;

    // <chart> get SPPD tahun by bagian
    public List<Sppd>getSppdTahunBagian(String tahun, String bulan) throws GeneralBOException;
    public List<Sppd>getSppdTahunBagian(String tahun, String bulan, String divisiId) throws GeneralBOException;
    public List<Sppd>getSppdTahunByBagian(String tahun, String bagian) throws GeneralBOException;

    // <chart> get SPPD tahun by divisi
    public List<Sppd>getSppdTahunByDivisi(String tahun, String bulan) throws GeneralBOException;

    // <chart> get SPPD tahun by Nip
    public List<Sppd>getSppdTahunByNip(String tahun, String bulan, String divisiName) throws GeneralBOException;

    // <chart> get bagian
    public List<Sppd>getBagian(String tahun, String divisiId) throws GeneralBOException;

    // Mengambil / cek dokumen Training (Jika SPPD dengan Training dan belum upload dokumen training, maka SPPD tidak dapt diclose.)
    public String cekDokumenTraining(String sppdId) throws GeneralBOException;
}
