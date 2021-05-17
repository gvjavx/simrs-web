package com.neurix.akuntansi.transaksi.kas.model;

public class EfakturDTO {
    private String kdJenisTransaksi;
    private String fgPengganti;
    private String nomorFaktur;
    private String tanggalFaktur;
    private String npwpPenjual;
    private String namaPenjual;
    private String alamatPenjual;
    private String npwpLawanTransaksi;
    private String namaLawanTransaksi;
    private String alamatLawanTransaksi;
    private String jumlahDpp;
    private String jumlahPpn;
    private String jumlahPpnBm;
    private String statusApproval;
    private String statusFaktur;
    private String referensi;

    //info perusahaan
    private String npwpPerusahaan;

    public String getNpwpPerusahaan() {
        return npwpPerusahaan;
    }

    public void setNpwpPerusahaan(String npwpPerusahaan) {
        this.npwpPerusahaan = npwpPerusahaan;
    }

    public String getKdJenisTransaksi() {
        return kdJenisTransaksi;
    }

    public void setKdJenisTransaksi(String kdJenisTransaksi) {
        this.kdJenisTransaksi = kdJenisTransaksi;
    }

    public String getFgPengganti() {
        return fgPengganti;
    }

    public void setFgPengganti(String fgPengganti) {
        this.fgPengganti = fgPengganti;
    }

    public String getNomorFaktur() {
        return nomorFaktur;
    }

    public void setNomorFaktur(String nomorFaktur) {
        this.nomorFaktur = nomorFaktur;
    }

    public String getTanggalFaktur() {
        return tanggalFaktur;
    }

    public void setTanggalFaktur(String tanggalFaktur) {
        this.tanggalFaktur = tanggalFaktur;
    }

    public String getNpwpPenjual() {
        return npwpPenjual;
    }

    public void setNpwpPenjual(String npwpPenjual) {
        this.npwpPenjual = npwpPenjual;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getAlamatPenjual() {
        return alamatPenjual;
    }

    public void setAlamatPenjual(String alamatPenjual) {
        this.alamatPenjual = alamatPenjual;
    }

    public String getNpwpLawanTransaksi() {
        return npwpLawanTransaksi;
    }

    public void setNpwpLawanTransaksi(String npwpLawanTransaksi) {
        this.npwpLawanTransaksi = npwpLawanTransaksi;
    }

    public String getNamaLawanTransaksi() {
        return namaLawanTransaksi;
    }

    public void setNamaLawanTransaksi(String namaLawanTransaksi) {
        this.namaLawanTransaksi = namaLawanTransaksi;
    }

    public String getAlamatLawanTransaksi() {
        return alamatLawanTransaksi;
    }

    public void setAlamatLawanTransaksi(String alamatLawanTransaksi) {
        this.alamatLawanTransaksi = alamatLawanTransaksi;
    }

    public String getJumlahDpp() {
        return jumlahDpp;
    }

    public void setJumlahDpp(String jumlahDpp) {
        this.jumlahDpp = jumlahDpp;
    }

    public String getJumlahPpn() {
        return jumlahPpn;
    }

    public void setJumlahPpn(String jumlahPpn) {
        this.jumlahPpn = jumlahPpn;
    }

    public String getJumlahPpnBm() {
        return jumlahPpnBm;
    }

    public void setJumlahPpnBm(String jumlahPpnBm) {
        this.jumlahPpnBm = jumlahPpnBm;
    }

    public String getStatusApproval() {
        return statusApproval;
    }

    public void setStatusApproval(String statusApproval) {
        this.statusApproval = statusApproval;
    }

    public String getStatusFaktur() {
        return statusFaktur;
    }

    public void setStatusFaktur(String statusFaktur) {
        this.statusFaktur = statusFaktur;
    }

    public String getReferensi() {
        return referensi;
    }

    public void setReferensi(String referensi) {
        this.referensi = referensi;
    }
}
