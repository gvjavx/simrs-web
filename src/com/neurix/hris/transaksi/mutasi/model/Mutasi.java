package com.neurix.hris.transaksi.mutasi.model;

import com.neurix.common.model.BaseModel;

import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class Mutasi extends BaseModel {
    private String mutasiId ;
    private String nip;
    private String status;
    private String stTanggalEfektif;
    private String stTanggalSekarang;
    private Timestamp tanggalEfektif;
    private String nama;
    private String divisiLamaId ;
    private String divisiLamaName ;
    private String branchLamaId ;
    private String branchLamaName ;
    private String positionLamaId ;
    private String positionLamaName ;
    private String branchBaruId ;
    private String branchBaruName ;
    private String divisiBaruId ;
    private String divisiBaruName ;
    private String positionBaruId ;
    private String positionBaruName;
    private String filePath;
    private String pjs;
    private String pjsLama;
    private String level;
    private String levelLama;
    private String levelBaru;
    private String penggantiNip;
    private String penggantiNama;
    private String tipeMutasi;
    private String tipeMutasiName;
    private String profesiLamaId;
    private String profesiLamaName;
    private String profesiBaruId;
    private String profesiBaruName;

    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String label5;
    private String label6;
    private String label7;

    private boolean SK1 = false ;
    private boolean SK2 = false ;

    private String statusName;

    private String statusPromosi;
    private String tanggalAktif;
    private String levelLamaName;
    private String levelBaruName;

    private String tipePegawai;

    public String getLevelBaruName() {
        return levelBaruName;
    }

    public void setLevelBaruName(String levelBaruName) {
        this.levelBaruName = levelBaruName;
    }

    public String getLevelLamaName() {
        return levelLamaName;
    }

    public void setLevelLamaName(String levelLamaName) {
        this.levelLamaName = levelLamaName;
    }

    public String getLevelBaru() {
        return levelBaru;
    }

    public void setLevelBaru(String levelBaru) {
        this.levelBaru = levelBaru;
    }

    public String getLevelLama() {
        return levelLama;
    }

    public void setLevelLama(String levelLama) {
        this.levelLama = levelLama;
    }

    public String getTipeMutasiName() {
        return tipeMutasiName;
    }

    public void setTipeMutasiName(String tipeMutasiName) {
        this.tipeMutasiName = tipeMutasiName;
    }

    public String getTanggalAktif() {
        return tanggalAktif;
    }

    public void setTanggalAktif(String tanggalAktif) {
        this.tanggalAktif = tanggalAktif;
    }

    public String getStatusPromosi() {
        return statusPromosi;
    }

    public void setStatusPromosi(String statusPromosi) {
        this.statusPromosi = statusPromosi;
    }

    public String getStatusName() {return statusName;}

    public void setStatusName(String statusName) {this.statusName = statusName;}

    public String getTipeMutasi() {
        return tipeMutasi;
    }

    public void setTipeMutasi(String tipeMutasi) {
        this.tipeMutasi = tipeMutasi;
    }

    public String getPenggantiNip() {
        return penggantiNip;
    }

    public void setPenggantiNip(String penggantiNip) {
        this.penggantiNip = penggantiNip;
    }

    public String getPenggantiNama() {
        return penggantiNama;
    }

    public void setPenggantiNama(String penggantiNama) {
        this.penggantiNama = penggantiNama;
    }

    public String getPjsLama() {
        return pjsLama;
    }

    public void setPjsLama(String pjsLama) {
        this.pjsLama = pjsLama;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getLabel4() {
        return label4;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    public String getLabel5() {
        return label5;
    }

    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    public String getLabel6() {
        return label6;
    }

    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    public String getLabel7() {
        return label7;
    }

    public void setLabel7(String label7) {
        this.label7 = label7;
    }

    public String getStTanggalSekarang() {
        return stTanggalSekarang;
    }

    public void setStTanggalSekarang(String stTanggalSekarang) {
        this.stTanggalSekarang = stTanggalSekarang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPjs() {
        return pjs;
    }

    public void setPjs(String pjs) {
        this.pjs = pjs;
    }

    public boolean isSK1() {
        return SK1;
    }

    public void setSK1(boolean SK1) {
        this.SK1 = SK1;
    }

    public boolean isSK2() {
        return SK2;
    }

    public void setSK2(boolean SK2) {
        this.SK2 = SK2;
    }

    public String getStTanggalEfektif() {
        return stTanggalEfektif;
    }

    public void setStTanggalEfektif(String stTanggalEfektif) {
        this.stTanggalEfektif = stTanggalEfektif;
    }

    public Timestamp getTanggalEfektif() {
        return tanggalEfektif;
    }

    public void setTanggalEfektif(Timestamp tanggalEfektif) {
        this.tanggalEfektif = tanggalEfektif;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getMutasiId() {
        return mutasiId;
    }

    public void setMutasiId(String mutasiId) {
        this.mutasiId = mutasiId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getDivisiLamaId() {
        return divisiLamaId;
    }

    public void setDivisiLamaId(String divisiLamaId) {
        this.divisiLamaId = divisiLamaId;
    }

    public String getDivisiLamaName() {
        return divisiLamaName;
    }

    public void setDivisiLamaName(String divisiLamaName) {
        this.divisiLamaName = divisiLamaName;
    }

    public String getBranchLamaId() {
        return branchLamaId;
    }

    public void setBranchLamaId(String branchLamaId) {
        this.branchLamaId = branchLamaId;
    }

    public String getBranchLamaName() {
        return branchLamaName;
    }

    public void setBranchLamaName(String branchLamaName) {
        this.branchLamaName = branchLamaName;
    }

    public String getPositionLamaName() {
        return positionLamaName;
    }

    public void setPositionLamaName(String positionLamaName) {
        this.positionLamaName = positionLamaName;
    }

    public String getBranchBaruId() {
        return branchBaruId;
    }

    public void setBranchBaruId(String branchBaruId) {
        this.branchBaruId = branchBaruId;
    }

    public String getBranchBaruName() {
        return branchBaruName;
    }

    public void setBranchBaruName(String branchBaruName) {
        this.branchBaruName = branchBaruName;
    }

    public String getDivisiBaruId() {
        return divisiBaruId;
    }

    public void setDivisiBaruId(String divisiBaruId) {
        this.divisiBaruId = divisiBaruId;
    }

    public String getDivisiBaruName() {
        return divisiBaruName;
    }

    public void setDivisiBaruName(String divisiBaruName) {
        this.divisiBaruName = divisiBaruName;
    }

    public String getPositionLamaId() {
        return positionLamaId;
    }

    public void setPositionLamaId(String positionLamaId) {
        this.positionLamaId = positionLamaId;
    }

    public String getPositionBaruId() {
        return positionBaruId;
    }

    public void setPositionBaruId(String positionBaruId) {
        this.positionBaruId = positionBaruId;
    }

    public String getPositionBaruName() {
        return positionBaruName;
    }

    public void setPositionBaruName(String positionBaruName) {
        this.positionBaruName = positionBaruName;
    }

    public String getProfesiBaruId() {
        return profesiBaruId;
    }

    public void setProfesiBaruId(String profesiBaruId) {
        this.profesiBaruId = profesiBaruId;
    }

    public String getProfesiBaruName() {
        return profesiBaruName;
    }

    public void setProfesiBaruName(String profesiBaruName) {
        this.profesiBaruName = profesiBaruName;
    }

    public String getProfesiLamaId() {
        return profesiLamaId;
    }

    public void setProfesiLamaId(String profesiLamaId) {
        this.profesiLamaId = profesiLamaId;
    }

    public String getProfesiLamaName() {
        return profesiLamaName;
    }

    public void setProfesiLamaName(String profesiLamaName) {
        this.profesiLamaName = profesiLamaName;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }
}