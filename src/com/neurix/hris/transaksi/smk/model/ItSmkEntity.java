package com.neurix.hris.transaksi.smk.model;

import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItSmkEntity implements Serializable {
    private String evaluasiPegawaiId;
    private String evaluasiPegawaiAspekId;
    private String nip;
    private String pegawaiName;
    private String divisiId;
    private String divisiName;
    private String branchName;
    private String branchId;
    private String positionId;
    private String positionName;
    private String golonganId;
    private String golonganName;
    private int poin;
    private int poinLebih;
    private String tipePegawaiId;
    private String statusPegawai;
    private String strMasaKerjaBln;
    private String periode;

    private String nilaiPrestasi;
    private String closed;
    private int masaKerjaGolongan;
    private int masaKerjaBln;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String jabatanSmkId;
    private String bagianId;
    private BigDecimal jumlahBobot;
    private String jabatanSmkDetailId;
    private String indikatorKinerja;
    private String checkList;
    private String subAspekA;
    private double bobot;
    private double pointPrestasi;
    private int pointnew;
    private double grandTotalNilaiPrestasi;
    private double grandTotalNilaiPrestasiRevisi;
    private String target;
    private String realisasi;
    private String evaluasiPegawaiAspekDetailId;
    private String activityId;
    private double nilai;
    private BigDecimal nilaiShare;
    private BigDecimal nilaiPrestasiItem;

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

    public int getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(int poinLebih) {
        this.poinLebih = poinLebih;
    }

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public BigDecimal getNilaiShare() {
        return nilaiShare;
    }

    public void setNilaiShare(BigDecimal nilaiShare) {
        this.nilaiShare = nilaiShare;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public double getGrandTotalNilaiPrestasiRevisi() {
        return grandTotalNilaiPrestasiRevisi;
    }

    public void setGrandTotalNilaiPrestasiRevisi(double grandTotalNilaiPrestasiRevisi) {
        this.grandTotalNilaiPrestasiRevisi = grandTotalNilaiPrestasiRevisi;
    }

    public double getGrandTotalNilaiPrestasi() {
        return grandTotalNilaiPrestasi;
    }

    public void setGrandTotalNilaiPrestasi(double grandTotalNilaiPrestasi) {
        this.grandTotalNilaiPrestasi = grandTotalNilaiPrestasi;
    }

    public int getPointnew() {
        return pointnew;
    }

    public void setPointnew(int pointnew) {
        this.pointnew = pointnew;
    }

    private double jan, feb, mar, apr, mei, jun, jul, ags, sep, okt, nov, des, rataRata;
    private ImBranches imBranches;
    private ImDepartmentEntity imDepartmentEntity;
    private ImPosition imPosition;
    private ImGolonganEntity imGolonganEntity;

    public String getEvaluasiPegawaiAspekId() {
        return evaluasiPegawaiAspekId;
    }

    public void setEvaluasiPegawaiAspekId(String evaluasiPegawaiAspekId) {
        this.evaluasiPegawaiAspekId = evaluasiPegawaiAspekId;
    }


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public double getRataRata() {
        return rataRata;
    }

    public void setRataRata(double rataRata) {
        this.rataRata = rataRata;
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

    public double getSep() {
        return sep;
    }

    public void setSep(double sep) {
        this.sep = sep;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public String getEvaluasiPegawaiAspekDetailId() {
        return evaluasiPegawaiAspekDetailId;
    }

    public void setEvaluasiPegawaiAspekDetailId(String evaluasiPegawaiAspekDetailId) {
        this.evaluasiPegawaiAspekDetailId = evaluasiPegawaiAspekDetailId;
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

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }

    public ImBranches getImBranches() {
        return imBranches;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
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

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }
}
