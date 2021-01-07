package com.neurix.hris.master.biodata.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.biodata.model.PelatihanJabatanUser;
import com.neurix.hris.master.biodata.model.PengalamanKerja;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
import com.neurix.hris.master.keluarga.model.Keluarga;
import com.neurix.hris.master.reward.model.Reward;
import com.neurix.hris.master.sertifikat.model.Sertifikat;
import com.neurix.hris.master.study.model.ImStudyEntity;
import com.neurix.hris.master.study.model.Study;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.training.model.TrainingPerson;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface BiodataBo extends BaseMasterBo<Biodata>{
    public void saveDelete(Biodata bean) throws GeneralBOException;
    public void saveDeletePengalamanKerja(PengalamanKerja bean) throws GeneralBOException;
    public void saveDeleteReward(Reward bean) throws GeneralBOException;
    public void saveDeleteSertifikat(Sertifikat bean) throws GeneralBOException;
    public void saveDeletePelatihan(PelatihanJabatanUser bean) throws GeneralBOException;

    List<Biodata> getByCriteriaForRekruitmenPabrik(Biodata searchBean) throws GeneralBOException;

    Biodata getBiodataRekruitmen(Biodata searchBean) throws GeneralBOException;

    Biodata getShift(Biodata searchBean) throws GeneralBOException;

    List<Biodata> getPegawaiMess() throws GeneralBOException;

    public List<Biodata> getComboBiodataWithCriteria(String query) throws GeneralBOException;
    public void saveEditCaption(Biodata bean) throws GeneralBOException ;
    public void saveUploadImage(Biodata bean) throws GeneralBOException ;

    List<Biodata> getSatpam() throws GeneralBOException;

    Biodata getKabagSdm() throws GeneralBOException;

    Biodata getDirekturUtama() throws GeneralBOException;

    public List<Biodata> getListOfPersonil(String query, String branchId) throws GeneralBOException;
    public List<Biodata> getListOfPersonilBagian(String query) throws GeneralBOException;
    public List<Biodata> getListOfPersonilPosition(String query) throws GeneralBOException;
    public List<Biodata> getListOfPersonilForSmk(String query, String periode) throws GeneralBOException;

    public List<Payroll> viewPayrollSys(String nip, String branchId, String bulan, String tahun, String payrollId) throws GeneralBOException;

    public List<TrainingPerson> getListTrainingPerson(TrainingPerson bean) throws GeneralBOException;
    public List<HistoryJabatanPegawai> historyJabatanSys(String nip) throws GeneralBOException;
    public List<Payroll> searchPayrollSys(String nip) throws GeneralBOException;

    public Biodata detailBiodataSys(String nip) throws GeneralBOException;

    List<Biodata> getListOfRsKelas(String query, String status, String nip, String golonganId, String statusRawat) throws GeneralBOException;

    List<PersonilPosition> getByCriteriaPersonilPosition(PersonilPosition searchBean) throws GeneralBOException;

    public void addPengalamanKerja(HistoryJabatanPegawai bean) throws GeneralBOException;
    public void addReward(Reward bean) throws GeneralBOException;
    public void addSertifikat(Sertifikat bean) throws GeneralBOException;
    public void addPelatihan(PelatihanJabatanUser bean) throws GeneralBOException;
    public void saveEditPengalamanKerja(HistoryJabatanPegawai bean) throws GeneralBOException;
    public void saveEditReward(Reward bean) throws GeneralBOException;
    public void saveEditSertifikat(Sertifikat bean) throws GeneralBOException;
    public void saveEditPelatihan(PelatihanJabatanUser bean) throws GeneralBOException;
    public PengalamanKerja searchPengalamanKerja(String pengalamanId) throws GeneralBOException;
    public Reward searchReward(String rewardId) throws GeneralBOException;
    public Sertifikat searchSertifikat(String sertifikatId) throws GeneralBOException;
    public PelatihanJabatanUser searchPelatihan(String sertifikatId) throws GeneralBOException;
    public List<PengalamanKerja> searchDataPengalamanKerja(String nip) throws GeneralBOException;
    public List<PengalamanKerja> searchDataRiwayatKerja(String nip) throws GeneralBOException;
    public List<Reward> searchDataReward(String nip) throws GeneralBOException;
    public List<Sertifikat> searchDataSertifikat(String nip) throws GeneralBOException;
    public List<PelatihanJabatanUser> searchDataPelatihanjabatanUser(String nip) throws GeneralBOException;

    List<Biodata> getListOfPersonilOnlyName(String query, String branchId) throws GeneralBOException;
    List<Biodata> getTanggalAktif(String nip) throws GeneralBOException;

    List<ImBiodataEntity> searchKaryawanDanBatihSys(Biodata biodata) throws GeneralBOException;

    List<Biodata> getBiodataforAbsensi(String branchId, String divisiId, String bagianId, String nip);

    List<Biodata> getBiodataforUangMakan(String branchId, String divisiId, String bagianId, String nip);

    List<Biodata> getBiodataByBagian(String branchId, String divisiId, String bagianId, String nip);

    List<Biodata> findBiodataLikeNip(String keyword);
//    List<Biodata> findBiodataLikeNama(String keyword);

    /*This method for autocomplete nip in form mobile*/
    List<Biodata> findBiodataLikeNama(String keyword, String branchId);

    List<Keluarga> listKeluarga(String nip);
    List<HistoryJabatanPegawai> listRiwayatPekerjaan(String nip);
    List<Study> listStudy(String nip);
    public List<Biodata> getAllListOfPersonil(String query, String branchId) throws GeneralBOException;

    Biodata getBiodataByNip(String nip);

    public Boolean checkAvailJenisPegawaiDefault(List<String> listOfJenisPegawai) throws GeneralBOException;
    public List<JenisPegawai> getAllJenisPegawai();
}