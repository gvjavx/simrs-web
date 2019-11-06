package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.common.model.BaseModel;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class Rekruitmen extends BaseModel {
    private String calonPegawaiId;
    private String namaCalonPegawai;
    private String gelarDepan;
    private String gelarBelakang;
    private String noKtp;
    private String alamat;
    private String rtRw;
    private String stTanggalLahir;
    private Date tanggalLahir;
    private String tempatLahir;
    private String fotoUpload;
    private String noTelp;
    private String statusKeluarga;
    private String jenisKelamin;
    private Integer jumlahAnak;
    private String branchId;
    private String branchName;
    private String statusInput;
    private String positionId;
    private String posisiName;
    private String divisiName;
    private String status;
    private String departmentId;
    private String statusPegawai;
    private String tipePegawai;
    private String statusGiling;
    private String noKontrak;
    private String closed;

    private String provinsiId;
    private String provinsiName;
    private String kotaId;
    private String kotaName;
    private String kecamatanId;
    private String kecamatanName;
    private String desaId;
    private String desaName;
    private String statusSaatIni;
    private String nip;
    private Date tanggalAktif;
    private BigInteger poin;
    private String golongan;
    private String stTanggalAktif;

    private Date kontrakDari;
    private Date kontrakSelesai;
    private String stKontrakDari;
    private String stKontrakSelesai;
    private String bagianName;
    private String alamatUnit;
    private BigInteger upah;

    private boolean rekruitmenClosed = false;
    private boolean rekruitmenDocClosed = false;
    private boolean readyclosed = false;

    public BigInteger getUpah() {
        return upah;
    }

    public void setUpah(BigInteger upah) {
        this.upah = upah;
    }

    public String getAlamatUnit() {
        return alamatUnit;
    }

    public void setAlamatUnit(String alamatUnit) {
        this.alamatUnit = alamatUnit;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public Date getKontrakDari() {
        return kontrakDari;
    }

    public void setKontrakDari(Date kontrakDari) {
        this.kontrakDari = kontrakDari;
    }

    public Date getKontrakSelesai() {
        return kontrakSelesai;
    }

    public void setKontrakSelesai(Date kontrakSelesai) {
        this.kontrakSelesai = kontrakSelesai;
    }

    public String getStKontrakDari() {
        return stKontrakDari;
    }

    public void setStKontrakDari(String stKontrakDari) {
        this.stKontrakDari = stKontrakDari;
    }

    public String getStKontrakSelesai() {
        return stKontrakSelesai;
    }

    public void setStKontrakSelesai(String stKontrakSelesai) {
        this.stKontrakSelesai = stKontrakSelesai;
    }

    public String getStatusSaatIni() {
        return statusSaatIni;
    }

    public void setStatusSaatIni(String statusSaatIni) {
        this.statusSaatIni = statusSaatIni;
    }

    public String getStTanggalAktif() {
        return stTanggalAktif;
    }

    public void setStTanggalAktif(String stTanggalAktif) {
        this.stTanggalAktif = stTanggalAktif;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(Date tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public BigInteger getPoin() {
        return poin;
    }

    public void setPoin(BigInteger poin) {
        this.poin = poin;
    }

    public String getGolongan() {
        return golongan;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;
    }

    public boolean isReadyclosed() {
        return readyclosed;
    }

    public void setReadyclosed(boolean readyclosed) {
        this.readyclosed = readyclosed;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public boolean isRekruitmenDocClosed() {
        return rekruitmenDocClosed;
    }

    public boolean isRekruitmenClosed() {
        return rekruitmenClosed;
    }

    public void setRekruitmenClosed(boolean rekruitmenClosed) {
        this.rekruitmenClosed = rekruitmenClosed;
    }

    public void setRekruitmenDocClosed(boolean rekruitmenDocClosed) {
        this.rekruitmenDocClosed = rekruitmenDocClosed;
    }

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusKeluarga() {
        return statusKeluarga;
    }

    public void setStatusKeluarga(String statusKeluarga) {
        this.statusKeluarga = statusKeluarga;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    private byte[] contentFile;

    public byte[] getContentFile() {
        return contentFile;
    }

    public void setContentFile(byte[] contentFile) {
        this.contentFile = contentFile;
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

    public String getNamaCalonPegawai() {
        return namaCalonPegawai;
    }

    public void setNamaCalonPegawai(String namaPegawai) {
        this.namaCalonPegawai = namaPegawai;
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

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getStTanggalLahir() {
        return stTanggalLahir;
    }

    public void setStTanggalLahir(String stTanggalLahir) {
        this.stTanggalLahir = stTanggalLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
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

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getFotoUpload() {
        return fotoUpload;
    }

    public void setFotoUpload(String fotoUpload) {
        this.fotoUpload = fotoUpload;
    }

    public String getCalonPegawaiId() {
        return calonPegawaiId;
    }

    public void setCalonPegawaiId(String calonPegawaiId) {
        this.calonPegawaiId = calonPegawaiId;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatusInput() {
        return statusInput;
    }

    public void setStatusInput(String statusInput) {
        this.statusInput = statusInput;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }
}