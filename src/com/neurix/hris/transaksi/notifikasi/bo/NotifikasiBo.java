package com.neurix.hris.transaksi.notifikasi.bo;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar;
import com.neurix.hris.transaksi.indisipliner.model.Indisipliner;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrikDetail;
import com.neurix.hris.transaksi.training.model.TrainingPerson;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface NotifikasiBo extends BaseMasterBo<Notifikasi>{
    public void saveDelete(Notifikasi bean) throws GeneralBOException;

    public void saveEditMobile(String nip, String typeNotifId, String notifId) throws GeneralBOException;

    List<Notifikasi> getByCriteriaForNotif(Notifikasi searchBean) throws GeneralBOException;

    public List<Notifikasi> getComboNotifikasiWithCriteria(String query) throws GeneralBOException;

    void SendNotifPlt(String nip, String id, String tipeNotifName, String note, String createdWho, String pengganti, String os);

    void SendNotifKeKabag(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho, String os);

    void SendNotifKeKabid(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho, String os);

    void sendNotif(Notifikasi notifikasi);

    public List<PersonilPosition> daftarAtasanHngVp(Notifikasi bean);

    void SendNotifKeAtasanLangsung(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho,String os);

    List<PersonilPosition> daftarAtasanLangsung(Notifikasi bean);

    void SendNotifSelf(String nip, String id, String tipeNotifName, String note, String createdWho, String os);

//    void SendNotifKeAllAtasan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho);

//    void SendNotifKeAllBawahan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho);

    public List<TrainingPerson> searchTrainingPerson(TrainingPerson bean) throws GeneralBOException;
    public void setReadNotif(Notifikasi bean) throws GeneralBOException;
    public List<IjinKeluar> searchIjinKeluarPerson(IjinKeluar bean) throws GeneralBOException;

    List<Lembur> searchLemburPerson(Lembur bean) throws GeneralBOException;

    List<Indisipliner> searchIndisiplinerPerson(Indisipliner bean) throws GeneralBOException;

    List<RekruitmenPabrikDetail> searchRekruitmenPabrikDetailPerson(RekruitmenPabrikDetail bean) throws GeneralBOException;

    public List<CutiPegawai> searchCutiPegawaiPerson(CutiPegawai bean) throws GeneralBOException;

    List<AbsensiPegawai> searchAbsensiPegawaiPerson(AbsensiPegawai bean) throws GeneralBOException;

    List<Object[]> findAllNotifByNip(String nip, String typeNotifId) throws GeneralBOException;
    List<Object[]> findAllNotifTypeNotif(String nip, String typeNotifId) throws GeneralBOException;
    List<Object[]> findAllNotifSppd(String nip, String typeNotifId) throws GeneralBOException;
    List<Object[]> findAllNotifTraining(String nip, String typeNotifId) throws GeneralBOException;
    List<Object[]> findAllNotifLembur(String nip, String typeNotifId) throws GeneralBOException;

    List<Object[]> findNotifByTypeNotif(String nip, String typeNotifId) throws GeneralBOException;

    List<Object[]> updateNotifikasiFlag(String nip, String typeNotifId, String notifId) throws GeneralBOException;

    List<Notifikasi> getCutiPensiun();

    List<Notifikasi> getCutiTahunan();

    List<Notifikasi> getPengajuanBiayaMenggantung(String branchId);

    List<Notifikasi> getTerimaRkPengajuanBiaya(String branchId);

    List<Notifikasi> getCutiPanjang();

    List<PersonilPosition> daftarKabag(String nip);

    List<PersonilPosition> daftarKabid(String nip);
    public List<String> getKabid(String nip);
    public List<Notifikasi> getJubilium();

    void sendNotifDitentukan(String nip, String id, String tipeNotifId, String tipeNotifName, String note, String createdWho);

    List<PengajuanBiaya> searchPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException;

    List<PengajuanBiaya> searchPengajuanBiayaRk(PengajuanBiaya bean) throws GeneralBOException;
    public List<RefreshLembur> searchRefreshLembur(RefreshLembur bean) throws GeneralBOException;

    }
