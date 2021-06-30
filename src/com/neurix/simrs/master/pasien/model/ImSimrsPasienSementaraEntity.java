package com.neurix.simrs.master.pasien.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ImSimrsPasienSementaraEntity {
    private String id;
    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private Date tglLahir;
    private Long desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String noTelp;
    private String urlKtp;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;
    private String flagLogin;
    private String email;
    private String password;
    private String profesi;
    private String noRM;

    public String getNoRM() {
        return noRM;
    }

    public void setNoRM(String noRM) {
        this.noRM = noRM;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Long getDesaId() {
        return desaId;
    }

    public void setDesaId(Long desaId) {
        this.desaId = desaId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
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

    public String getFlagLogin() {
        return flagLogin;
    }

    public void setFlagLogin(String flagLogin) {
        this.flagLogin = flagLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImSimrsPasienSementaraEntity that = (ImSimrsPasienSementaraEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nama, that.nama) && Objects.equals(jenisKelamin, that.jenisKelamin) && Objects.equals(noKtp, that.noKtp) && Objects.equals(tempatLahir, that.tempatLahir) && Objects.equals(tglLahir, that.tglLahir) && Objects.equals(desaId, that.desaId) && Objects.equals(jalan, that.jalan) && Objects.equals(suku, that.suku) && Objects.equals(agama, that.agama) && Objects.equals(noTelp, that.noTelp) && Objects.equals(urlKtp, that.urlKtp) && Objects.equals(flag, that.flag) && Objects.equals(action, that.action) && Objects.equals(createdDate, that.createdDate) && Objects.equals(lastUpdate, that.lastUpdate) && Objects.equals(createdWho, that.createdWho) && Objects.equals(lastUpdateWho, that.lastUpdateWho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nama, jenisKelamin, noKtp, tempatLahir, tglLahir, desaId, jalan, suku, agama, noTelp, urlKtp, flag, action, createdDate, lastUpdate, createdWho, lastUpdateWho);
    }
}
