package com.neurix.hris.master.biodata.model;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImBiodataHistoryEntity implements Serializable {
    private String id;
    private String nip;
    private String namaPegawai;
    private String gelarDepan;
    private String gelarBelakang;
    private String noKtp;
    private String alamat;
    private String rtRw;
    private String desa;
    private String kecamatan;
    private String noTelp;
    private String kabupaten;
    private String provinsi;
    private String stTanggalLahir;
    private java.util.Date tanggalLahir;
    private String tempatLahir;
    private Date TanggalAktif;
    private String positionId;
    private String statusPegawai;

    private String masaGiling;
    private String jenisKelamin;
    private BigInteger jumlahAnak;
//    private String golonganId;

    private double point;


    private String tipePegawai;
    private String tipePegawaiName;
    private String golongan;
    private String golonganName;
    //    private String statusPegawai;
    private String statusPegawaiName;
    private String statusKeluarga;
    private String fotoUpload;
    private String statusCaption;
    private String keterangan;
    private String divisi;
    private String branch;
    private String provinsiId;
    private String provinsiName;
    private String kotaId;
    private String kotaName;
    private String kecamatanId;
    private String kecamatanName;
    private String desaId;
    private String desaName;
    private String pin;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String statusGiling;
    private String noSkAktif;
    private String zakatProfesi;
    private Date tanggalPensiun;
    private String danaPensiun;
    private String strukturGaji;
    private BigDecimal gaji;
    private int poinLebih;

    private String gender;
    private String agama;
    private String tglMasuk;
    private String branchId;
    private String posisiId;
    private String npwp;
    private String mt;
    private String noAnggotaDapen;
    private String noBpjsKetenagakerjaan;
    private String noBpjsKetenagakerjaanPensiun;
    private String noBpjsKesehatan;
    private String namaBank;
    private String noRekBank;
    private String cabangBank;
    private Date tanggalPraPensiun;
    private Integer masaKerjaGolongan;
    private String golonganDapenId;
    private Date TanggalMasuk;
    private String branchIdTerakhir;
    private String flagMess;
    private String flagPlt;
    private String flagPjs;
    private String flagFingerMobile;
    private String flagTunjRumah;
    private String flagTunjAir;
    private String flagTunjListrik;
    private String flagTunjBbm;
    private String flagBpjsKs;
    private String flagBpjsTk;
    private String flagPercobaan;

    private ImProvinsiEntity imProvinsiEntity ;
    private ImPosition imPosition;
    private ImKotaEntity imKotaEntity;
    private ImKecamatanEntity imKecamatanEntity;
    private ImDesaEntity imDesaEntity ;
    private ImGolonganEntity imGolonganEntity;
    private ItPersonilPositionEntity itPersonilPositionEntity;

    //RAKA-19MAR2021 ==> penambahan kolom
    private String nipLama;
    private String flagDokterKso;

    private Date tanggalKeluar;
    private String flagPegawaiCutiDiluarTanggungan;
    private Date tanggalCutiDiluarAwal;
    private Date tanggalCutiDiluarAkhir;
    //RAKA-end


    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getFlagDokterKso() {
        return flagDokterKso;
    }

    public void setFlagDokterKso(String flagDokterKso) {
        this.flagDokterKso = flagDokterKso;
    }

    public Date getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(Date tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getFlagPegawaiCutiDiluarTanggungan() {
        return flagPegawaiCutiDiluarTanggungan;
    }

    public void setFlagPegawaiCutiDiluarTanggungan(String flagPegawaiCutiDiluarTanggungan) {
        this.flagPegawaiCutiDiluarTanggungan = flagPegawaiCutiDiluarTanggungan;
    }

    public Date getTanggalCutiDiluarAwal() {
        return tanggalCutiDiluarAwal;
    }

    public void setTanggalCutiDiluarAwal(Date tanggalCutiDiluarAwal) {
        this.tanggalCutiDiluarAwal = tanggalCutiDiluarAwal;
    }

    public Date getTanggalCutiDiluarAkhir() {
        return tanggalCutiDiluarAkhir;
    }

    public void setTanggalCutiDiluarAkhir(Date tanggalCutiDiluarAkhir) {
        this.tanggalCutiDiluarAkhir = tanggalCutiDiluarAkhir;
    }

    public int getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(int poinLebih) {
        this.poinLebih = poinLebih;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public String getStrukturGaji() {
        return strukturGaji;
    }

    public void setStrukturGaji(String strukturGaji) {
        this.strukturGaji = strukturGaji;
    }

    public Date getTanggalPensiun() {
        return tanggalPensiun;
    }

    public void setTanggalPensiun(Date tanggalPensiun) {
        this.tanggalPensiun = tanggalPensiun;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getNoSkAktif() {
        return noSkAktif;
    }

    public void setNoSkAktif(String noSkAktif) {
        this.noSkAktif = noSkAktif;
    }

    public Date getTanggalAktif() {
        return TanggalAktif;
    }

    public String getZakatProfesi() {
        return zakatProfesi;
    }

    public void setZakatProfesi(String zakatProfesi) {
        this.zakatProfesi = zakatProfesi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getGelarDepan() {
        return gelarDepan;
    }

    public void setGelarDepan(String gelarDepan) {
        this.gelarDepan = gelarDepan;
    }

    public String getGelarBelakang() {
        return gelarBelakang;
    }

    public void setGelarBelakang(String gelarBelakang) {
        this.gelarBelakang = gelarBelakang;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRtRw() {
        return rtRw;
    }

    public void setRtRw(String rtRw) {
        this.rtRw = rtRw;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getStTanggalLahir() {
        return stTanggalLahir;
    }

    public void setStTanggalLahir(String stTanggalLahir) {
        this.stTanggalLahir = stTanggalLahir;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        TanggalAktif = tanggalAktif;
    }

    public java.util.Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(java.util.Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getMasaGiling() {
        return masaGiling;
    }

    public void setMasaGiling(String masaGiling) {
        this.masaGiling = masaGiling;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public BigInteger getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(BigInteger jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public String getFotoUpload() {
        return fotoUpload;
    }

    public void setFotoUpload(String fotoUpload) {
        this.fotoUpload = fotoUpload;
    }

    public String getStatusCaption() {
        return statusCaption;
    }

    public void setStatusCaption(String statusCaption) {
        this.statusCaption = statusCaption;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getProvinsiName() {
        return provinsiName;
    }

    public void setProvinsiName(String provinsiName) {
        this.provinsiName = provinsiName;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public String getKotaName() {
        return kotaName;
    }

    public void setKotaName(String kotaName) {
        this.kotaName = kotaName;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKecamatanName() {
        return kecamatanName;
    }

    public void setKecamatanName(String kecamatanName) {
        this.kecamatanName = kecamatanName;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getDesaName() {
        return desaName;
    }

    public void setDesaName(String desaName) {
        this.desaName = desaName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    public ImProvinsiEntity getImProvinsiEntity() {
        return imProvinsiEntity;
    }

    public void setImProvinsiEntity(ImProvinsiEntity imProvinsiEntity) {
        this.imProvinsiEntity = imProvinsiEntity;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public ImKecamatanEntity getImKecamatanEntity() {
        return imKecamatanEntity;
    }

    public void setImKecamatanEntity(ImKecamatanEntity imKecamatanEntity) {
        this.imKecamatanEntity = imKecamatanEntity;
    }

    public ImDesaEntity getImDesaEntity() {
        return imDesaEntity;
    }

    public void setImDesaEntity(ImDesaEntity imDesaEntity) {
        this.imDesaEntity = imDesaEntity;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }

    public ItPersonilPositionEntity getItPersonilPositionEntity() {
        return itPersonilPositionEntity;
    }

    public void setItPersonilPositionEntity(ItPersonilPositionEntity itPersonilPositionEntity) {
        this.itPersonilPositionEntity = itPersonilPositionEntity;
    }

    public String getFlagBpjsKs() {
        return flagBpjsKs;
    }

    public void setFlagBpjsKs(String flagBpjsKs) {
        this.flagBpjsKs = flagBpjsKs;
    }

    public String getFlagBpjsTk() {
        return flagBpjsTk;
    }

    public void setFlagBpjsTk(String flagBpjsTk) {
        this.flagBpjsTk = flagBpjsTk;
    }

    public String getFlagFingerMobile() {
        return flagFingerMobile;
    }

    public void setFlagFingerMobile(String flagFingerMobile) {
        this.flagFingerMobile = flagFingerMobile;
    }

    public String getFlagPercobaan() {
        return flagPercobaan;
    }

    public void setFlagPercobaan(String flagPercobaan) {
        this.flagPercobaan = flagPercobaan;
    }

    public String getFlagPjs() {
        return flagPjs;
    }

    public void setFlagPjs(String flagPjs) {
        this.flagPjs = flagPjs;
    }

    public String getFlagPlt() {
        return flagPlt;
    }

    public void setFlagPlt(String flagPlt) {
        this.flagPlt = flagPlt;
    }

    public String getFlagTunjAir() {
        return flagTunjAir;
    }

    public void setFlagTunjAir(String flagTunjAir) {
        this.flagTunjAir = flagTunjAir;
    }

    public String getFlagTunjBbm() {
        return flagTunjBbm;
    }

    public void setFlagTunjBbm(String flagTunjBbm) {
        this.flagTunjBbm = flagTunjBbm;
    }

    public String getFlagTunjListrik() {
        return flagTunjListrik;
    }

    public void setFlagTunjListrik(String flagTunjListrik) {
        this.flagTunjListrik = flagTunjListrik;
    }

    public String getFlagTunjRumah() {
        return flagTunjRumah;
    }

    public void setFlagTunjRumah(String flagTunjRumah) {
        this.flagTunjRumah = flagTunjRumah;
    }

    public String getFlagMess() {
        return flagMess;
    }

    public void setFlagMess(String flagMess) {
        this.flagMess = flagMess;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCabangBank() {
        return cabangBank;
    }

    public void setCabangBank(String cabangBank) {
        this.cabangBank = cabangBank;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGolonganDapenId() {
        return golonganDapenId;
    }

    public void setGolonganDapenId(String golonganDapenId) {
        this.golonganDapenId = golonganDapenId;
    }

    public Integer getMasaKerjaGolongan() {
        return masaKerjaGolongan;
    }

    public void setMasaKerjaGolongan(Integer masaKerjaGolongan) {
        this.masaKerjaGolongan = masaKerjaGolongan;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoAnggotaDapen() {
        return noAnggotaDapen;
    }

    public void setNoAnggotaDapen(String noAnggotaDapen) {
        this.noAnggotaDapen = noAnggotaDapen;
    }

    public String getNoBpjsKesehatan() {
        return noBpjsKesehatan;
    }

    public void setNoBpjsKesehatan(String noBpjsKesehatan) {
        this.noBpjsKesehatan = noBpjsKesehatan;
    }

    public String getNoBpjsKetenagakerjaan() {
        return noBpjsKetenagakerjaan;
    }

    public void setNoBpjsKetenagakerjaan(String noBpjsKetenagakerjaan) {
        this.noBpjsKetenagakerjaan = noBpjsKetenagakerjaan;
    }

    public String getNoBpjsKetenagakerjaanPensiun() {
        return noBpjsKetenagakerjaanPensiun;
    }

    public void setNoBpjsKetenagakerjaanPensiun(String noBpjsKetenagakerjaanPensiun) {
        this.noBpjsKetenagakerjaanPensiun = noBpjsKetenagakerjaanPensiun;
    }

    public String getNoRekBank() {
        return noRekBank;
    }

    public void setNoRekBank(String noRekBank) {
        this.noRekBank = noRekBank;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public Date getTanggalPraPensiun() {
        return tanggalPraPensiun;
    }

    public void setTanggalPraPensiun(Date tanggalPraPensiun) {
        this.tanggalPraPensiun = tanggalPraPensiun;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public Date getTanggalMasuk() {
        return TanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        TanggalMasuk = tanggalMasuk;
    }

    public String getBranchIdTerakhir() {
        return branchIdTerakhir;
    }

    public void setBranchIdTerakhir(String branchIdTerakhir) {
        this.branchIdTerakhir = branchIdTerakhir;
    }
}
