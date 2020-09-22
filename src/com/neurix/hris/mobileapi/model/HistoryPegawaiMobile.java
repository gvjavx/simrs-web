package com.neurix.hris.mobileapi.model;

/**
 * @author gondok
 * Tuesday, 11/08/20 13:33
 */
public class HistoryPegawaiMobile {

    private String jumlahHadir;
    private String jumlahDispen;
    private String sisaCuti;
    private String bulan;
    private String tahun;

    private String ijinId;
    private String ijinKeluarName;
    private String lamaIjin;
    private String tanggalAwal;
    private String tanggalAkhir;
    private String lamaijinDispen;

    private String absensiPegawaiId;
    private String jamDatang;
    private String jamPulang;
    private String status;
    private String namaStatus;
    private String tanggalAbsen;

    private String isLembur;
    private String isDispen;

    private String nip;
    private String tanggal;
    private String jamMasuk;
    private String jamKeluar;
    private String statusAbsensi;
    private String lembur;
    private String ijin;
    private String branchId;
    private String jenisLembur;
    private Double lamaLembur;
    private Double jamLembur;
    private Double biayaLembur;
    private String stBiayaLembur;
    private String tipeHari;
    private Double realisasiJamLembur;
    private String stRealisasiJamLembur;
    private String keterangan;
    private String flagUangMakan;
    private String approvalFlag;
    private String approvalDate;
    private String approvalId;
    private String approvalName;
    private String notapprovalNote;
    private String keteranganSesuaikan;
    private String sesuaikanFlag;

    private String userIdActive;
    private String userNameActive;
    private String jamMasukDb;
    private String jamPulangDb;
    private String pin;
    private String jabatan;
    private String divisi;

    private String unit;
    private String stTanggal;
    private String stTanggalAkhir;
    private String nama;
    private String statusName;
    private String tipePegawai;
    private String statusGiling;
    private String jumlahLembur;
    private String mulaiIzin;
    private String pulangIzin;
    private String awalLembur;
    private String selesaiLembur;
    private String stTanggalDari;
    private String stTanggalSelesai;
    private String divisiId;
    private String posisiId;
    private String absensi;
    private String stUangmakan;
    private String checkedValue;
    private Double lemburPerJam;
    private boolean clear;
    private boolean noted;
    private String positionName;
    private boolean absensiApprove = false;
    private boolean notApprove = false;
    private boolean cekAdmin = false;
    private String bagian;
    private Integer noUrutBagian;
    private Double hariKerja15;
    private Double hariKerja20;
    private Double hariLibur20;
    private Double hariLibur30;
    private Double hariLibur40;
    private Double biayaLemburPerjam;
    private String stJamLembur;
    private String stBiayaLemburPerjam;
    private String stLamaLembur;
    private String stHariKerja15;
    private String stHariKerja20;
    private String stHariLibur20;
    private String stHariLibur30;
    private String stHariLibur40;
    private String no;
    private String sJumlahHariKerja;
    private String sJumlahHariLibur;
    private String terlambatKurang60;
    private String terlambatLebih60;
    private String tidakAbsenMasuk;
    private String tidakAbsenPulang;
    private String cekPegawaiStatus;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public void setJamMasuk(String jamMasuk) {
        this.jamMasuk = jamMasuk;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(String jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public String getStatusAbsensi() {
        return statusAbsensi;
    }

    public void setStatusAbsensi(String statusAbsensi) {
        this.statusAbsensi = statusAbsensi;
    }

    public String getLembur() {
        return lembur;
    }

    public void setLembur(String lembur) {
        this.lembur = lembur;
    }

    public String getIjin() {
        return ijin;
    }

    public void setIjin(String ijin) {
        this.ijin = ijin;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getJenisLembur() {
        return jenisLembur;
    }

    public void setJenisLembur(String jenisLembur) {
        this.jenisLembur = jenisLembur;
    }

    public Double getLamaLembur() {
        return lamaLembur;
    }

    public void setLamaLembur(Double lamaLembur) {
        this.lamaLembur = lamaLembur;
    }

    public Double getJamLembur() {
        return jamLembur;
    }

    public void setJamLembur(Double jamLembur) {
        this.jamLembur = jamLembur;
    }

    public Double getBiayaLembur() {
        return biayaLembur;
    }

    public void setBiayaLembur(Double biayaLembur) {
        this.biayaLembur = biayaLembur;
    }

    public String getStBiayaLembur() {
        return stBiayaLembur;
    }

    public void setStBiayaLembur(String stBiayaLembur) {
        this.stBiayaLembur = stBiayaLembur;
    }

    public String getTipeHari() {
        return tipeHari;
    }

    public void setTipeHari(String tipeHari) {
        this.tipeHari = tipeHari;
    }

    public Double getRealisasiJamLembur() {
        return realisasiJamLembur;
    }

    public void setRealisasiJamLembur(Double realisasiJamLembur) {
        this.realisasiJamLembur = realisasiJamLembur;
    }

    public String getStRealisasiJamLembur() {
        return stRealisasiJamLembur;
    }

    public void setStRealisasiJamLembur(String stRealisasiJamLembur) {
        this.stRealisasiJamLembur = stRealisasiJamLembur;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFlagUangMakan() {
        return flagUangMakan;
    }

    public void setFlagUangMakan(String flagUangMakan) {
        this.flagUangMakan = flagUangMakan;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getNotapprovalNote() {
        return notapprovalNote;
    }

    public void setNotapprovalNote(String notapprovalNote) {
        this.notapprovalNote = notapprovalNote;
    }

    public String getKeteranganSesuaikan() {
        return keteranganSesuaikan;
    }

    public void setKeteranganSesuaikan(String keteranganSesuaikan) {
        this.keteranganSesuaikan = keteranganSesuaikan;
    }

    public String getSesuaikanFlag() {
        return sesuaikanFlag;
    }

    public void setSesuaikanFlag(String sesuaikanFlag) {
        this.sesuaikanFlag = sesuaikanFlag;
    }

    public String getUserIdActive() {
        return userIdActive;
    }

    public void setUserIdActive(String userIdActive) {
        this.userIdActive = userIdActive;
    }

    public String getUserNameActive() {
        return userNameActive;
    }

    public void setUserNameActive(String userNameActive) {
        this.userNameActive = userNameActive;
    }

    public String getJamMasukDb() {
        return jamMasukDb;
    }

    public void setJamMasukDb(String jamMasukDb) {
        this.jamMasukDb = jamMasukDb;
    }

    public String getJamPulangDb() {
        return jamPulangDb;
    }

    public void setJamPulangDb(String jamPulangDb) {
        this.jamPulangDb = jamPulangDb;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStTanggal() {
        return stTanggal;
    }

    public void setStTanggal(String stTanggal) {
        this.stTanggal = stTanggal;
    }

    public String getStTanggalAkhir() {
        return stTanggalAkhir;
    }

    public void setStTanggalAkhir(String stTanggalAkhir) {
        this.stTanggalAkhir = stTanggalAkhir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getStatusGiling() {
        return statusGiling;
    }

    public void setStatusGiling(String statusGiling) {
        this.statusGiling = statusGiling;
    }

    public String getJumlahLembur() {
        return jumlahLembur;
    }

    public void setJumlahLembur(String jumlahLembur) {
        this.jumlahLembur = jumlahLembur;
    }

    public String getMulaiIzin() {
        return mulaiIzin;
    }

    public void setMulaiIzin(String mulaiIzin) {
        this.mulaiIzin = mulaiIzin;
    }

    public String getPulangIzin() {
        return pulangIzin;
    }

    public void setPulangIzin(String pulangIzin) {
        this.pulangIzin = pulangIzin;
    }

    public String getAwalLembur() {
        return awalLembur;
    }

    public void setAwalLembur(String awalLembur) {
        this.awalLembur = awalLembur;
    }

    public String getSelesaiLembur() {
        return selesaiLembur;
    }

    public void setSelesaiLembur(String selesaiLembur) {
        this.selesaiLembur = selesaiLembur;
    }

    public String getStTanggalDari() {
        return stTanggalDari;
    }

    public void setStTanggalDari(String stTanggalDari) {
        this.stTanggalDari = stTanggalDari;
    }

    public String getStTanggalSelesai() {
        return stTanggalSelesai;
    }

    public void setStTanggalSelesai(String stTanggalSelesai) {
        this.stTanggalSelesai = stTanggalSelesai;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public String getAbsensi() {
        return absensi;
    }

    public void setAbsensi(String absensi) {
        this.absensi = absensi;
    }

    public String getStUangmakan() {
        return stUangmakan;
    }

    public void setStUangmakan(String stUangmakan) {
        this.stUangmakan = stUangmakan;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public Double getLemburPerJam() {
        return lemburPerJam;
    }

    public void setLemburPerJam(Double lemburPerJam) {
        this.lemburPerJam = lemburPerJam;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean isNoted() {
        return noted;
    }

    public void setNoted(boolean noted) {
        this.noted = noted;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean isAbsensiApprove() {
        return absensiApprove;
    }

    public void setAbsensiApprove(boolean absensiApprove) {
        this.absensiApprove = absensiApprove;
    }

    public boolean isNotApprove() {
        return notApprove;
    }

    public void setNotApprove(boolean notApprove) {
        this.notApprove = notApprove;
    }

    public boolean isCekAdmin() {
        return cekAdmin;
    }

    public void setCekAdmin(boolean cekAdmin) {
        this.cekAdmin = cekAdmin;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public Integer getNoUrutBagian() {
        return noUrutBagian;
    }

    public void setNoUrutBagian(Integer noUrutBagian) {
        this.noUrutBagian = noUrutBagian;
    }

    public Double getHariKerja15() {
        return hariKerja15;
    }

    public void setHariKerja15(Double hariKerja15) {
        this.hariKerja15 = hariKerja15;
    }

    public Double getHariKerja20() {
        return hariKerja20;
    }

    public void setHariKerja20(Double hariKerja20) {
        this.hariKerja20 = hariKerja20;
    }

    public Double getHariLibur20() {
        return hariLibur20;
    }

    public void setHariLibur20(Double hariLibur20) {
        this.hariLibur20 = hariLibur20;
    }

    public Double getHariLibur30() {
        return hariLibur30;
    }

    public void setHariLibur30(Double hariLibur30) {
        this.hariLibur30 = hariLibur30;
    }

    public Double getHariLibur40() {
        return hariLibur40;
    }

    public void setHariLibur40(Double hariLibur40) {
        this.hariLibur40 = hariLibur40;
    }

    public Double getBiayaLemburPerjam() {
        return biayaLemburPerjam;
    }

    public void setBiayaLemburPerjam(Double biayaLemburPerjam) {
        this.biayaLemburPerjam = biayaLemburPerjam;
    }

    public String getStJamLembur() {
        return stJamLembur;
    }

    public void setStJamLembur(String stJamLembur) {
        this.stJamLembur = stJamLembur;
    }

    public String getStBiayaLemburPerjam() {
        return stBiayaLemburPerjam;
    }

    public void setStBiayaLemburPerjam(String stBiayaLemburPerjam) {
        this.stBiayaLemburPerjam = stBiayaLemburPerjam;
    }

    public String getStLamaLembur() {
        return stLamaLembur;
    }

    public void setStLamaLembur(String stLamaLembur) {
        this.stLamaLembur = stLamaLembur;
    }

    public String getStHariKerja15() {
        return stHariKerja15;
    }

    public void setStHariKerja15(String stHariKerja15) {
        this.stHariKerja15 = stHariKerja15;
    }

    public String getStHariKerja20() {
        return stHariKerja20;
    }

    public void setStHariKerja20(String stHariKerja20) {
        this.stHariKerja20 = stHariKerja20;
    }

    public String getStHariLibur20() {
        return stHariLibur20;
    }

    public void setStHariLibur20(String stHariLibur20) {
        this.stHariLibur20 = stHariLibur20;
    }

    public String getStHariLibur30() {
        return stHariLibur30;
    }

    public void setStHariLibur30(String stHariLibur30) {
        this.stHariLibur30 = stHariLibur30;
    }

    public String getStHariLibur40() {
        return stHariLibur40;
    }

    public void setStHariLibur40(String stHariLibur40) {
        this.stHariLibur40 = stHariLibur40;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getsJumlahHariKerja() {
        return sJumlahHariKerja;
    }

    public void setsJumlahHariKerja(String sJumlahHariKerja) {
        this.sJumlahHariKerja = sJumlahHariKerja;
    }

    public String getsJumlahHariLibur() {
        return sJumlahHariLibur;
    }

    public void setsJumlahHariLibur(String sJumlahHariLibur) {
        this.sJumlahHariLibur = sJumlahHariLibur;
    }

    public String getTerlambatKurang60() {
        return terlambatKurang60;
    }

    public void setTerlambatKurang60(String terlambatKurang60) {
        this.terlambatKurang60 = terlambatKurang60;
    }

    public String getTerlambatLebih60() {
        return terlambatLebih60;
    }

    public void setTerlambatLebih60(String terlambatLebih60) {
        this.terlambatLebih60 = terlambatLebih60;
    }

    public String getTidakAbsenMasuk() {
        return tidakAbsenMasuk;
    }

    public void setTidakAbsenMasuk(String tidakAbsenMasuk) {
        this.tidakAbsenMasuk = tidakAbsenMasuk;
    }

    public String getTidakAbsenPulang() {
        return tidakAbsenPulang;
    }

    public void setTidakAbsenPulang(String tidakAbsenPulang) {
        this.tidakAbsenPulang = tidakAbsenPulang;
    }

    public String getCekPegawaiStatus() {
        return cekPegawaiStatus;
    }

    public void setCekPegawaiStatus(String cekPegawaiStatus) {
        this.cekPegawaiStatus = cekPegawaiStatus;
    }

    public String getNamaStatus() {
        return namaStatus;
    }

    public void setNamaStatus(String namaStatus) {
        this.namaStatus = namaStatus;
    }

    public String getIsLembur() {
        return isLembur;
    }

    public void setIsLembur(String isLembur) {
        this.isLembur = isLembur;
    }

    public String getIsDispen() {
        return isDispen;
    }

    public void setIsDispen(String isDispen) {
        this.isDispen = isDispen;
    }

    public String getAbsensiPegawaiId() {
        return absensiPegawaiId;
    }

    public void setAbsensiPegawaiId(String absensiPegawaiId) {
        this.absensiPegawaiId = absensiPegawaiId;
    }

    public String getIjinId() {
        return ijinId;
    }

    public void setIjinId(String ijinId) {
        this.ijinId = ijinId;
    }

    public String getJamPulang() {
        return jamPulang;
    }

    public void setJamPulang(String jamPulang) {
        this.jamPulang = jamPulang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggalAbsen() {
        return tanggalAbsen;
    }

    public void setTanggalAbsen(String tanggalAbsen) {
        this.tanggalAbsen = tanggalAbsen;
    }

    public String getJamDatang() {
        return jamDatang;
    }

    public void setJamDatang(String jamDatang) {
        this.jamDatang = jamDatang;
    }

    private String message;

    public String getIjinKeluarName() {
        return ijinKeluarName;
    }

    public void setIjinKeluarName(String ijinKeluarName) {
        this.ijinKeluarName = ijinKeluarName;
    }

    public String getLamaIjin() {
        return lamaIjin;
    }

    public void setLamaIjin(String lamaIjin) {
        this.lamaIjin = lamaIjin;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(String tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJumlahHadir() {
        return jumlahHadir;
    }

    public void setJumlahHadir(String jumlahHadir) {
        this.jumlahHadir = jumlahHadir;
    }

    public String getJumlahDispen() {
        return jumlahDispen;
    }

    public void setJumlahDispen(String jumlahDispen) {
        this.jumlahDispen = jumlahDispen;
    }

    public String getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(String sisaCuti) {
        this.sisaCuti = sisaCuti;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getLamaijinDispen() {
        return lamaijinDispen;
    }

    public void setLamaijinDispen(String lamaijinDispen) {
        this.lamaijinDispen = lamaijinDispen;
    }
}


