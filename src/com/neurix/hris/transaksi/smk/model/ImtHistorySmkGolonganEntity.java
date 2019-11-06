package com.neurix.hris.transaksi.smk.model;

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

public class ImtHistorySmkGolonganEntity implements Serializable {
    private String idHistorySmkGolongan;
    private String nip;
    private String nama;
    private String tahun;
    private String nilaiHuruf;
    private String golonganId;
    private String golonganIdBefore;
    private String golonganName;
    private String golonganNameBefore;
    private String positionId;
    private String branchId;
    private String bagianId;
    private String bagianName;
    private String updateGolonganId;
    private String status;
    private String flagMutasi;
    private int poin;
    private int poinBefore;
    private int poinLebih;
    private int poinLebihBefore;
    private BigDecimal nilaiAngka;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public String getFlagMutasi() {
        return flagMutasi;
    }

    public void setFlagMutasi(String flagMutasi) {
        this.flagMutasi = flagMutasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGolonganIdBefore() {
        return golonganIdBefore;
    }

    public void setGolonganIdBefore(String golonganIdBefore) {
        this.golonganIdBefore = golonganIdBefore;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getGolonganNameBefore() {
        return golonganNameBefore;
    }

    public void setGolonganNameBefore(String golonganNameBefore) {
        this.golonganNameBefore = golonganNameBefore;
    }

    public int getPoinBefore() {
        return poinBefore;
    }

    public void setPoinBefore(int poinBefore) {
        this.poinBefore = poinBefore;
    }

    public int getPoinLebihBefore() {
        return poinLebihBefore;
    }

    public void setPoinLebihBefore(int poinLebihBefore) {
        this.poinLebihBefore = poinLebihBefore;
    }

    public String getUpdateGolonganId() {
        return updateGolonganId;
    }

    public void setUpdateGolonganId(String updateGolonganId) {
        this.updateGolonganId = updateGolonganId;
    }

    public String getIdHistorySmkGolongan() {
        return idHistorySmkGolongan;
    }

    public void setIdHistorySmkGolongan(String idHistorySmkGolongan) {
        this.idHistorySmkGolongan = idHistorySmkGolongan;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public int getPoinLebih() {
        return poinLebih;
    }

    public void setPoinLebih(int poinLebih) {
        this.poinLebih = poinLebih;
    }

    public BigDecimal getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka(BigDecimal nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
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
}
