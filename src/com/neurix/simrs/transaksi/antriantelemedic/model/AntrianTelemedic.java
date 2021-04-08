package com.neurix.simrs.transaksi.antriantelemedic.model;

import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by reza on 08/06/20.
 */
public class AntrianTelemedic {

    private String id;
    private String idPasien;
    private String idDokter;
    private String idPelayanan;
    private String flagResep;
    private String status;
    private BigInteger biayaKonsultasi;
    private String flagBayarKonsultasi;
    private String flagBayarResep;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String flag;
    private String action;
    private String namaPasien;
    private String namaDokter;
    private String namaPelayanan;
    private String ketFlagResep;
    private String ketStatus;
    private String asuransi;
    private String noKartu;
    private String idJenisPeriksaPasien;
    private String idAsuransi;
    private String kodeBank;
    private String branchId;
    private String namaBranch;
    private String approveKonsultasi;
    private String approveResep;
    private String keterangan;
    private String keluhan;
    private String flagForVerifikatorPembayaran;
    private String statusTransaksi;
    private String flagEresep;
    private String idTransaksi;
    private String alamat;
    private String lat;
    private String lon;
    private String jenisPengambilan;
    private String noTelp;
    private String urlResep;
    private String namaAsuransi;
    private BigDecimal jumlahCover;
    private String jenisPembayaran;
    private String noJurnal;
    private String noRujukan;
    private String jenisRujukan;
    private String noSep;
    private String idDiagnosa;
    private String ketDiagnosa;
    private String flagBelumBayar;
    private String flagApproveConfirm;
    private BigDecimal dibayarPasien;
    private String urlFotoStruk;
    private String labelStatusAsuransi;
    private String flagBatalDokter;
    private String idBatalDokterTelemedic;
    private String alasanBatal;
    private String alasan;
    private String flagDanaDikembaLikan;
    private String isMobile;
    private String idRekening;
    private String flagDateNow;
    private String stDateFrom;
    private String stDateTo;
    private String stTangalUpload;
    private Timestamp tanggalUpload;
    private Integer urutan;
    private String flagKasir;
    private String shiftId;
    private String namaBank;
    private String stLastUpdate;
    private String flagCall;
    private String tipePencarian;

    public String getTipePencarian() {
        return tipePencarian;
    }

    public void setTipePencarian(String tipePencarian) {
        this.tipePencarian = tipePencarian;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getStLastUpdate() {
        return stLastUpdate;
    }

    public void setStLastUpdate(String stLastUpdate) {
        this.stLastUpdate = stLastUpdate;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getFlagKasir() {
        return flagKasir;
    }

    public void setFlagKasir(String flagKasir) {
        this.flagKasir = flagKasir;
    }

    public String getStTangalUpload() {
        return stTangalUpload;
    }

    public void setStTangalUpload(String stTangalUpload) {
        this.stTangalUpload = stTangalUpload;
    }

    public Timestamp getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(Timestamp tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public String getStDateFrom() {
        return stDateFrom;
    }

    public void setStDateFrom(String stDateFrom) {
        this.stDateFrom = stDateFrom;
    }

    public String getStDateTo() {
        return stDateTo;
    }

    public void setStDateTo(String stDateTo) {
        this.stDateTo = stDateTo;
    }

    public String getFlagDateNow() {
        return flagDateNow;
    }

    public void setFlagDateNow(String flagDateNow) {
        this.flagDateNow = flagDateNow;
    }

    public String getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(String idRekening) {
        this.idRekening = idRekening;
    }
    private String flagViewApproveConfirm;

    public String getFlagViewApproveConfirm() {
        return flagViewApproveConfirm;
    }

    public void setFlagViewApproveConfirm(String flagViewApproveConfirm) {
        this.flagViewApproveConfirm = flagViewApproveConfirm;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public String getFlagDanaDikembaLikan() {
        return flagDanaDikembaLikan;
    }

    public void setFlagDanaDikembaLikan(String flagDanaDikembaLikan) {
        this.flagDanaDikembaLikan = flagDanaDikembaLikan;
    }

    public String getAlasanBatal() {
        return alasanBatal;
    }

    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getIdBatalDokterTelemedic() {
        return idBatalDokterTelemedic;
    }

    public void setIdBatalDokterTelemedic(String idBatalDokterTelemedic) {
        this.idBatalDokterTelemedic = idBatalDokterTelemedic;
    }

    public String getFlagBatalDokter() {
        return flagBatalDokter;
    }

    public void setFlagBatalDokter(String flagBatalDokter) {
        this.flagBatalDokter = flagBatalDokter;
    }

    private String stCreatedDate;

    public String getStCreatedDate() {
        return stCreatedDate;
    }

    public void setStCreatedDate(String stCreatedDate) {
        this.stCreatedDate = stCreatedDate;
    }

    public String getLabelStatusAsuransi() {
        return labelStatusAsuransi;
    }

    public void setLabelStatusAsuransi(String labelStatusAsuransi) {
        this.labelStatusAsuransi = labelStatusAsuransi;
    }

    public String getUrlFotoStruk() {
        return urlFotoStruk;
    }

    public void setUrlFotoStruk(String urlFotoStruk) {
        this.urlFotoStruk = urlFotoStruk;
    }

    public BigDecimal getDibayarPasien() {
        return dibayarPasien;
    }

    public void setDibayarPasien(BigDecimal dibayarPasien) {
        this.dibayarPasien = dibayarPasien;
    }

    public String getFlagApproveConfirm() {
        return flagApproveConfirm;
    }

    public void setFlagApproveConfirm(String flagApproveConfirm) {
        this.flagApproveConfirm = flagApproveConfirm;
    }

    public String getFlagBelumBayar() {
        return flagBelumBayar;
    }

    public void setFlagBelumBayar(String flagBelumBayar) {
        this.flagBelumBayar = flagBelumBayar;
    }

    private String idPembayaran;
    private BigDecimal nominal;
    private String approvedFlag;

    public String getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(String approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(String idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getIdDiagnosa() {
        return idDiagnosa;
    }

    public void setIdDiagnosa(String idDiagnosa) {
        this.idDiagnosa = idDiagnosa;
    }

    public String getKetDiagnosa() {
        return ketDiagnosa;
    }

    public void setKetDiagnosa(String ketDiagnosa) {
        this.ketDiagnosa = ketDiagnosa;
    }

    public String getNoSep() {
        return noSep;
    }

    public void setNoSep(String noSep) {
        this.noSep = noSep;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getJenisRujukan() {
        return jenisRujukan;
    }

    public void setJenisRujukan(String jenisRujukan) {
        this.jenisRujukan = jenisRujukan;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getUrlResep() {
        return urlResep;
    }

    public void setUrlResep(String urlResep) {
        this.urlResep = urlResep;
    }


    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public BigDecimal getJumlahCover() {
        return jumlahCover;
    }

    public void setJumlahCover(BigDecimal jumlahCover) {
        this.jumlahCover = jumlahCover;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlagResep() {
        return flagResep;
    }

    public void setFlagResep(String flagResep) {
        this.flagResep = flagResep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getBiayaKonsultasi() {
        return biayaKonsultasi;
    }

    public void setBiayaKonsultasi(BigInteger biayaKonsultasi) {
        this.biayaKonsultasi = biayaKonsultasi;
    }

    public String getFlagBayarKonsultasi() {
        return flagBayarKonsultasi;
    }

    public void setFlagBayarKonsultasi(String flagBayarKonsultasi) {
        this.flagBayarKonsultasi = flagBayarKonsultasi;
    }

    public String getFlagBayarResep() {
        return flagBayarResep;
    }

    public void setFlagBayarResep(String flagBayarResep) {
        this.flagBayarResep = flagBayarResep;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
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

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getKetFlagResep() {
        return ketFlagResep;
    }

    public void setKetFlagResep(String ketFlagResep) {
        this.ketFlagResep = ketFlagResep;
    }

    public String getKetStatus() {
        return ketStatus;
    }

    public void setKetStatus(String ketStatus) {
        this.ketStatus = ketStatus;
    }

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNamaBranch() {
        return namaBranch;
    }

    public void setNamaBranch(String namaBranch) {
        this.namaBranch = namaBranch;
    }

    public String getApproveKonsultasi() {
        return approveKonsultasi;
    }

    public void setApproveKonsultasi(String approveKonsultasi) {
        this.approveKonsultasi = approveKonsultasi;
    }

    public String getApproveResep() {
        return approveResep;
    }

    public void setApproveResep(String approveResep) {
        this.approveResep = approveResep;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getFlagForVerifikatorPembayaran() {
        return flagForVerifikatorPembayaran;
    }

    public void setFlagForVerifikatorPembayaran(String flagForVerifikatorPembayaran) {
        this.flagForVerifikatorPembayaran = flagForVerifikatorPembayaran;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public String getFlagEresep() {
        return flagEresep;
    }

    public void setFlagEresep(String flagEresep) {
        this.flagEresep = flagEresep;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getJenisPengambilan() {
        return jenisPengambilan;
    }

    public void setJenisPengambilan(String jenisPengambilan) {
        this.jenisPengambilan = jenisPengambilan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

//    public static Comparator<AntrianTelemedic> urutanPembayaranSorting = new Comparator<AntrianTelemedic>() {
//        public int compare(AntrianTelemedic s1, AntrianTelemedic s2) {
//            Integer urutan1 = s1.getUrutan();
//            Integer urutan2 = s2.getUrutan();
//            //ascending order
//            return urutan1.compareTo(urutan2);
//
//            //descending order
//            //return kdRekening2.compareTo(kdRekening1);
//        }
//    };
//
//    public static Comparator<AntrianTelemedic> tanggalUploadSorting = new Comparator<AntrianTelemedic>() {
//        public int compare(AntrianTelemedic s1, AntrianTelemedic s2) {
//            Timestamp urutan1 = s1.getTanggalUpload() == null ? s1.getCreatedDate() : s1.getTanggalUpload();
//            Timestamp urutan2 = s2.getTanggalUpload() == null ? s2.getCreatedDate() : s2.getTanggalUpload();
//
//            //ascending order
//            return urutan1.compareTo(urutan2);
//
//            //descending order
////            return urutan2.compareTo(urutan1);
//        }
//    };
//
//    public static Comparator<AntrianTelemedic> getUrutanPembayaranSorting() {
//        return urutanPembayaranSorting;
//    }
//
//    public static void setUrutanPembayaranSorting(Comparator<AntrianTelemedic> urutanPembayaranSorting) {
//        AntrianTelemedic.urutanPembayaranSorting = urutanPembayaranSorting;
//    }
//
//    public static Comparator<AntrianTelemedic> getTanggalUploadSorting() {
//        return tanggalUploadSorting;
//    }
//
//    public static void setTanggalUploadSorting(Comparator<AntrianTelemedic> tanggalUploadSorting) {
//        AntrianTelemedic.tanggalUploadSorting = tanggalUploadSorting;
//    }
}
