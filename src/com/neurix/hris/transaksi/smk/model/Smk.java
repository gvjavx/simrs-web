package com.neurix.hris.transaksi.smk.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class Smk extends BaseModel {
    private String evaluasiPegawaiId;
    private String role;
    private String nip;
    private String nipNama;
    private String pegawaiName;
    private String divisiId;
    private String divisiName;
    private String branchId;
    private String bagianId;
    private String bagianName;
    private String branchName;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String MKG;
    private String kelompokId;
    private String golonganName;
    private int poin;
    private String poinPrestasi;
    private String poinPrestasiMin;
    private double nilaiRevisi;
    private String tipePegawaiId;
    private String statusPegawai;
    private String periode;
    private String strMasaKerjaBln;
    private String strMasaKerjaGolongan;
    private double pointBaru;
    private String hurufPrestasi;
    private String nilaiPrestasi;
    private int masaKerjaGolongan;
    private int masaKerjaBln;
    private String note;
    private boolean smkClosed = false;
    private boolean editAtasan = false;

    private String jabatanSmkId;
    private BigDecimal jumlahBobot;
    private String jabatanSmkDetailId;
    private String indikatorKinerja;
    private String indikatorKinerjaAtasan;
    private String checkList;
    private String subAspekA;
    private double bobot;
    private double pointPrestasi;
    private String target;
    private String realisasi;
    private String tipeAspekId;
    private String evaluasiPegawaiAspekId;
    private double nilai;
    private BigDecimal nilaiPrestasiItem;
    private String evaluasiPegawaiAspekDetailId;
    private double jan, feb, mar, apr, mei, jun, jul, ags, sep, okt, nov, des, rataRata;

    private String tipeSmk;
    private double unitUsaha;
    private String peristiwa;
    private String closed;
    private String activityId;
    private String printDraft;
    private String flagView;
    private String stTanggalPeristiwa;
    private String bulan;
    private String tahun;
    private Date tanggalPeristiwa;

    private String tahun1;
    private String tahun2;
    private String tahun3;
    private String tahun4;
    private String tahun5;

    private String nilaiPrestasi1;
    private String nilaiPrestasi2;
    private String nilaiPrestasi3;
    private String nilaiPrestasi4;
    private String nilaiPrestasi5;

    private String golongan1;
    private String golongan2;
    private String golongan3;
    private String golongan4;
    private String golongan5;

    boolean cekLis = false;
    boolean editNilai = false;
    boolean editTarget = false;
    boolean editRealisasi = false;

    public String getStrMasaKerjaBln() {
        return strMasaKerjaBln;
    }

    public void setStrMasaKerjaBln(String strMasaKerjaBln) {
        this.strMasaKerjaBln = strMasaKerjaBln;
    }

    public int getMasaKerjaBln() {
        return masaKerjaBln;
    }

    public void setMasaKerjaBln(int masaKerjaBln) {
        this.masaKerjaBln = masaKerjaBln;
    }

    public String getPoinPrestasi() {
        return poinPrestasi;
    }

    public void setPoinPrestasi(String poinPrestasi) {
        this.poinPrestasi = poinPrestasi;
    }

    public String getPoinPrestasiMin() {
        return poinPrestasiMin;
    }

    public void setPoinPrestasiMin(String poinPrestasiMin) {
        this.poinPrestasiMin = poinPrestasiMin;
    }

    public String getMKG() {
        return MKG;
    }

    public void setMKG(String MKG) {
        this.MKG = MKG;
    }

    public String getStrMasaKerjaGolongan() {
        return strMasaKerjaGolongan;
    }

    public void setStrMasaKerjaGolongan(String strMasaKerjaGolongan) {
        this.strMasaKerjaGolongan = strMasaKerjaGolongan;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getHurufPrestasi() {
        return hurufPrestasi;
    }

    public void setHurufPrestasi(String hurufPrestasi) {
        this.hurufPrestasi = hurufPrestasi;
    }

    public String getTahun1() {
        return tahun1;
    }

    public void setTahun1(String tahun1) {
        this.tahun1 = tahun1;
    }

    public String getTahun2() {
        return tahun2;
    }

    public void setTahun2(String tahun2) {
        this.tahun2 = tahun2;
    }

    public String getTahun3() {
        return tahun3;
    }

    public void setTahun3(String tahun3) {
        this.tahun3 = tahun3;
    }

    public String getTahun4() {
        return tahun4;
    }

    public void setTahun4(String tahun4) {
        this.tahun4 = tahun4;
    }

    public String getTahun5() {
        return tahun5;
    }

    public void setTahun5(String tahun5) {
        this.tahun5 = tahun5;
    }

    public String getNilaiPrestasi1() {
        return nilaiPrestasi1;
    }

    public void setNilaiPrestasi1(String nilaiPrestasi1) {
        this.nilaiPrestasi1 = nilaiPrestasi1;
    }

    public String getNilaiPrestasi2() {
        return nilaiPrestasi2;
    }

    public void setNilaiPrestasi2(String nilaiPrestasi2) {
        this.nilaiPrestasi2 = nilaiPrestasi2;
    }

    public String getNilaiPrestasi3() {
        return nilaiPrestasi3;
    }

    public void setNilaiPrestasi3(String nilaiPrestasi3) {
        this.nilaiPrestasi3 = nilaiPrestasi3;
    }

    public String getNilaiPrestasi4() {
        return nilaiPrestasi4;
    }

    public void setNilaiPrestasi4(String nilaiPrestasi4) {
        this.nilaiPrestasi4 = nilaiPrestasi4;
    }

    public String getNilaiPrestasi5() {
        return nilaiPrestasi5;
    }

    public void setNilaiPrestasi5(String nilaiPrestasi5) {
        this.nilaiPrestasi5 = nilaiPrestasi5;
    }

    public String getGolongan1() {
        return golongan1;
    }

    public void setGolongan1(String golongan1) {
        this.golongan1 = golongan1;
    }

    public String getGolongan2() {
        return golongan2;
    }

    public void setGolongan2(String golongan2) {
        this.golongan2 = golongan2;
    }

    public String getGolongan3() {
        return golongan3;
    }

    public void setGolongan3(String golongan3) {
        this.golongan3 = golongan3;
    }

    public String getGolongan4() {
        return golongan4;
    }

    public void setGolongan4(String golongan4) {
        this.golongan4 = golongan4;
    }

    public String getGolongan5() {
        return golongan5;
    }

    public void setGolongan5(String golongan5) {
        this.golongan5 = golongan5;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
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

    public String getFlagView() {
        return flagView;
    }

    public void setFlagView(String flagView) {
        this.flagView = flagView;
    }

    public String getPrintDraft() {
        return printDraft;
    }

    public void setPrintDraft(String printDraft) {
        this.printDraft = printDraft;
    }

    public boolean isEditAtasan() {
        return editAtasan;
    }

    public void setEditAtasan(boolean editAtasan) {
        this.editAtasan = editAtasan;
    }

    public String getTipeSmk() {
        return tipeSmk;
    }

    public void setTipeSmk(String tipeSmk) {
        this.tipeSmk = tipeSmk;
    }

    public double getUnitUsaha() {
        return unitUsaha;
    }

    public void setUnitUsaha(double unitUsaha) {
        this.unitUsaha = unitUsaha;
    }

    private String nipLogin;

    public String getNipLogin() {
        return nipLogin;
    }

    public void setNipLogin(String nipLogin) {
        this.nipLogin = nipLogin;
    }

    public String getRole() {
        return role;
    }

    public String getNipNama() {
        return nipNama;
    }

    public void setNipNama(String nipNama) {
        this.nipNama = nipNama;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIndikatorKinerjaAtasan() {
        return indikatorKinerjaAtasan;
    }

    public void setIndikatorKinerjaAtasan(String indikatorKinerjaAtasan) {
        this.indikatorKinerjaAtasan = indikatorKinerjaAtasan;
    }

    public double getNilaiRevisi() {
        return nilaiRevisi;
    }

    public void setNilaiRevisi(double nilaiRevisi) {
        this.nilaiRevisi = nilaiRevisi;
    }

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public boolean isCekLis() {
        return cekLis;
    }

    public void setCekLis(boolean cekLis) {
        this.cekLis = cekLis;
    }

    public double getAgs() {
        return ags;
    }

    public void setAgs(double ags) {
        this.ags = ags;
    }

    public double getApr() {
        return apr;
    }

    public void setApr(double apr) {
        this.apr = apr;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public double getDes() {
        return des;
    }

    public void setDes(double des) {
        this.des = des;
    }

    public double getFeb() {
        return feb;
    }

    public void setFeb(double feb) {
        this.feb = feb;
    }

    public double getJan() {
        return jan;
    }

    public void setJan(double jan) {
        this.jan = jan;
    }

    public double getJul() {
        return jul;
    }

    public void setJul(double jul) {
        this.jul = jul;
    }

    public double getJun() {
        return jun;
    }

    public void setJun(double jun) {
        this.jun = jun;
    }

    public double getMar() {
        return mar;
    }

    public void setMar(double mar) {
        this.mar = mar;
    }

    public double getMei() {
        return mei;
    }

    public void setMei(double mei) {
        this.mei = mei;
    }

    public double getNov() {
        return nov;
    }

    public void setNov(double nov) {
        this.nov = nov;
    }

    public double getOkt() {
        return okt;
    }

    public void setOkt(double okt) {
        this.okt = okt;
    }

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
    }

    public double getSep() {
        return sep;
    }

    public void setSep(double sep) {
        this.sep = sep;
    }

    public boolean isEditRealisasi() {
        return editRealisasi;
    }

    public void setEditRealisasi(boolean editRealisasi) {
        this.editRealisasi = editRealisasi;
    }

    public boolean isEditTarget() {
        return editTarget;
    }

    public void setEditTarget(boolean editTarget) {
        this.editTarget = editTarget;
    }

    public boolean isEditNilai() {
        return editNilai;
    }

    public void setEditNilai(boolean editNilai) {
        this.editNilai = editNilai;
    }

    public String getPeristiwa() {
        return peristiwa;
    }

    public void setPeristiwa(String peristiwa) {
        this.peristiwa = peristiwa;
    }

    public String getStTanggalPeristiwa() {
        return stTanggalPeristiwa;
    }

    public void setStTanggalPeristiwa(String stTanggalPeristiwa) {
        this.stTanggalPeristiwa = stTanggalPeristiwa;
    }

    public Date getTanggalPeristiwa() {
        return tanggalPeristiwa;
    }

    public void setTanggalPeristiwa(Date tanggalPeristiwa) {
        this.tanggalPeristiwa = tanggalPeristiwa;
    }

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
    }

    public String getTipeAspekId() {
        return tipeAspekId;
    }

    public void setTipeAspekId(String tipeAspekId) {
        this.tipeAspekId = tipeAspekId;
    }

    public double getBobot() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot = bobot;
    }

    public String getIndikatorKinerja() {
        return indikatorKinerja;
    }

    public void setIndikatorKinerja(String indikatorKinerja) {
        this.indikatorKinerja = indikatorKinerja;
    }

    public String getJabatanSmkDetailId() {
        return jabatanSmkDetailId;
    }

    public void setJabatanSmkDetailId(String jabatanSmkDetailId) {
        this.jabatanSmkDetailId = jabatanSmkDetailId;
    }

    public String getJabatanSmkId() {
        return jabatanSmkId;
    }

    public void setJabatanSmkId(String jabatanSmkId) {
        this.jabatanSmkId = jabatanSmkId;
    }

    public BigDecimal getJumlahBobot() {
        return jumlahBobot;
    }

    public void setJumlahBobot(BigDecimal jumlahBobot) {
        this.jumlahBobot = jumlahBobot;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiPrestasiItem() {
        return nilaiPrestasiItem;
    }

    public void setNilaiPrestasiItem(BigDecimal nilaiPrestasiItem) {
        this.nilaiPrestasiItem = nilaiPrestasiItem;
    }

    public double getPointPrestasi() {
        return pointPrestasi;
    }

    public void setPointPrestasi(double pointPrestasi) {
        this.pointPrestasi = pointPrestasi;
    }

    public String getRealisasi() {
        return realisasi;
    }

    public void setRealisasi(String realisasi) {
        this.realisasi = realisasi;
    }

    public String getSubAspekA() {
        return subAspekA;
    }

    public void setSubAspekA(String subAspekA) {
        this.subAspekA = subAspekA;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isSmkClosed() {
        return smkClosed;
    }

    public void setSmkClosed(boolean smkClosed) {
        this.smkClosed = smkClosed;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getEvaluasiPegawaiId() {
        return evaluasiPegawaiId;
    }

    public void setEvaluasiPegawaiId(String evaluasiPegawaiId) {
        this.evaluasiPegawaiId = evaluasiPegawaiId;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public int getMasaKerjaGolongan() {
        return masaKerjaGolongan;
    }

    public void setMasaKerjaGolongan(int masaKerjaGolongan) {
        this.masaKerjaGolongan = masaKerjaGolongan;
    }

    public String getNilaiPrestasi() {
        return nilaiPrestasi;
    }

    public void setNilaiPrestasi(String nilaiPrestasi) {
        this.nilaiPrestasi = nilaiPrestasi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPegawaiName() {
        return pegawaiName;
    }

    public void setPegawaiName(String pegawaiName) {
        this.pegawaiName = pegawaiName;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public double getPointBaru() {
        return pointBaru;
    }

    public void setPointBaru(double pointBaru) {
        this.pointBaru = pointBaru;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getTipePegawaiId() {
        return tipePegawaiId;
    }

    public void setTipePegawaiId(String tipePegawaiId) {
        this.tipePegawaiId = tipePegawaiId;
    }
}