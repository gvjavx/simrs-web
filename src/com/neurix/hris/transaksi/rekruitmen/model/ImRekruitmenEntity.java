package com.neurix.hris.transaksi.rekruitmen.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.provinsi.model.ImDesaEntity;
import com.neurix.hris.master.provinsi.model.ImKecamatanEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;
import com.neurix.hris.master.provinsi.model.ImProvinsiEntity;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;

import java.io.Serializable;
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
public class ImRekruitmenEntity implements Serializable {

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
    private String statusInput;
    private String positionId;
    private String departmentId;
    private String statusPegawai;
    private String tipePegawai;
    private String statusGiling;
    private String noKontrak;
    private String closed;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String tipePegawaiName;
    private String statusPegawaiName;

    private String provinsiId;
    private String provinsiName;
    private String kotaId;
    private String kotaName;
    private String kecamatanId;
    private String kecamatanName;
    private String desaId;
    private String desaName;

    private String nip;
    private Date tanggalAktif;
    private BigInteger poin;
    private BigInteger upah;
    private String golongan;
    private Date kontrakDari;
    private Date kontrakSelesai;

    private ImProvinsiEntity imProvinsiEntity ;
    private ImPosition imPosition;
    private ImKotaEntity imKotaEntity;
    private ImKecamatanEntity imKecamatanEntity;
    private ImDesaEntity imDesaEntity ;
    private ImGolonganEntity imGolonganEntity;
    private ItPersonilPositionEntity itPersonilPositionEntity;
    private ImDepartmentEntity imDepartmentEntity;
    private ImBranches imBranches;

    public BigInteger getUpah() {
        return upah;
    }

    public void setUpah(BigInteger upah) {
        this.upah = upah;
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

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public void setImBranches(ImBranches imBranches) {
        this.imBranches = imBranches;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public ItPersonilPositionEntity getItPersonilPositionEntity() {
        return itPersonilPositionEntity;
    }

    public void setItPersonilPositionEntity(ItPersonilPositionEntity itPersonilPositionEntity) {
        this.itPersonilPositionEntity = itPersonilPositionEntity;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
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

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    private ImHrisTipePegawai imHrisTipePegawai ;

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImHrisTipePegawai getImHrisTipePegawai() {
        return imHrisTipePegawai;
    }

    public void setImHrisTipePegawai(ImHrisTipePegawai imHrisTipePegawai) {
        this.imHrisTipePegawai = imHrisTipePegawai;
    }

    public ImProvinsiEntity getImProvinsiEntity() {
        return imProvinsiEntity;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public void setImProvinsiEntity(ImProvinsiEntity imProvinsiEntity) {
        this.imProvinsiEntity = imProvinsiEntity;
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

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
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
}
