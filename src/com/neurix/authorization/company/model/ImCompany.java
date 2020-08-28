package com.neurix.authorization.company.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class ImCompany implements Serializable {

    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    private String npwp;

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Timestamp createdDate;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    private String createdWho;

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    private Timestamp lastUpdate;

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdateWho;

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String serviceOnOff;
    private String mailServer;
    private String portServer;
    private String userNameServer;
    private String passwordServer;
    private String defaultEmailSender;
    private String defaultEmailSubject;
    private String defaultEmailContent;
    private BigDecimal minimumLuasan;
    private BigDecimal biayaJabatanPersentase;
    private BigDecimal iuranPerusahaanJkmJkk;
    private BigInteger bulanJubilium;
    private BigInteger bulanPensiun;
    private BigDecimal faktorJubileum;
    private BigDecimal faktorJasprod;
    public BigDecimal kursDolar;

    public BigDecimal payrollThrPersentase;
    public BigDecimal payrollPendidikanPersentase;
    public BigDecimal payrollJasprodKali;

    private BigDecimal maxBpjsTk;
    private BigDecimal maxBpjsPensiun;
    private BigDecimal maxBpjsKesehatan ;

    private String periodeGaji;
    private BigDecimal paramDapen;
    private BigDecimal paramDapenPegawai;
    private BigDecimal biayaJabatan;

    public BigDecimal getParamDapenPegawai() {
        return paramDapenPegawai;
    }

    public void setParamDapenPegawai(BigDecimal paramDapenPegawai) {
        this.paramDapenPegawai = paramDapenPegawai;
    }

    public BigDecimal getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(BigDecimal biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
    }

    public BigDecimal getParamDapen() {
        return paramDapen;
    }

    public void setParamDapen(BigDecimal paramDapen) {
        this.paramDapen = paramDapen;
    }

    public String getPeriodeGaji() {
        return periodeGaji;
    }

    public void setPeriodeGaji(String periodeGaji) {
        this.periodeGaji = periodeGaji;
    }

    public BigDecimal getMaxBpjsTk() {
        return maxBpjsTk;
    }

    public void setMaxBpjsTk(BigDecimal maxBpjsTk) {
        this.maxBpjsTk = maxBpjsTk;
    }

    public BigDecimal getMaxBpjsPensiun() {
        return maxBpjsPensiun;
    }

    public void setMaxBpjsPensiun(BigDecimal maxBpjsPensiun) {
        this.maxBpjsPensiun = maxBpjsPensiun;
    }

    public BigDecimal getMaxBpjsKesehatan() {
        return maxBpjsKesehatan;
    }

    public void setMaxBpjsKesehatan(BigDecimal maxBpjsKesehatan) {
        this.maxBpjsKesehatan = maxBpjsKesehatan;
    }

    public BigDecimal getPayrollThrPersentase() {
        return payrollThrPersentase;
    }

    public void setPayrollThrPersentase(BigDecimal payrollThrPersentase) {
        this.payrollThrPersentase = payrollThrPersentase;
    }

    public BigDecimal getPayrollPendidikanPersentase() {
        return payrollPendidikanPersentase;
    }

    public void setPayrollPendidikanPersentase(BigDecimal payrollPendidikanPersentase) {
        this.payrollPendidikanPersentase = payrollPendidikanPersentase;
    }

    public BigDecimal getPayrollJasprodKali() {
        return payrollJasprodKali;
    }

    public void setPayrollJasprodKali(BigDecimal payrollJasprodKali) {
        this.payrollJasprodKali = payrollJasprodKali;
    }

    public BigDecimal getKursDolar() {
        return kursDolar;
    }

    public void setKursDolar(BigDecimal kursDolar) {
        this.kursDolar = kursDolar;
    }

    public BigDecimal getFaktorJasprod() {
        return faktorJasprod;
    }

    public void setFaktorJasprod(BigDecimal faktorJasprod) {
        this.faktorJasprod = faktorJasprod;
    }

    public BigDecimal getFaktorJubileum() {
        return faktorJubileum;
    }

    public void setFaktorJubileum(BigDecimal faktorJubileum) {
        this.faktorJubileum = faktorJubileum;
    }

    public BigInteger getBulanPensiun() {
        return bulanPensiun;
    }

    public void setBulanPensiun(BigInteger bulanPensiun) {
        this.bulanPensiun = bulanPensiun;
    }

    public BigInteger getBulanJubilium() {
        return bulanJubilium;
    }

    public void setBulanJubilium(BigInteger bulanJubilium) {
        this.bulanJubilium = bulanJubilium;
    }

    public BigDecimal getIuranPerusahaanJkmJkk() {
        return iuranPerusahaanJkmJkk;
    }

    public void setIuranPerusahaanJkmJkk(BigDecimal iuranPerusahaanJkmJkk) {
        this.iuranPerusahaanJkmJkk = iuranPerusahaanJkmJkk;
    }

    public BigDecimal getBiayaJabatanPersentase() {
        return biayaJabatanPersentase;
    }

    public void setBiayaJabatanPersentase(BigDecimal biayaJabatanPersentase) {
        this.biayaJabatanPersentase = biayaJabatanPersentase;
    }

    public BigDecimal getMinimumLuasan() {
        return minimumLuasan;
    }

    public void setMinimumLuasan(BigDecimal minimumLuasan) {
        this.minimumLuasan = minimumLuasan;
    }

    public String getServiceOnOff() {
        return serviceOnOff;
    }

    public void setServiceOnOff(String serviceOnOff) {
        this.serviceOnOff = serviceOnOff;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getPortServer() {
        return portServer;
    }

    public void setPortServer(String portServer) {
        this.portServer = portServer;
    }

    public String getUserNameServer() {
        return userNameServer;
    }

    public void setUserNameServer(String userNameServer) {
        this.userNameServer = userNameServer;
    }

    public String getPasswordServer() {
        return passwordServer;
    }

    public void setPasswordServer(String passwordServer) {
        this.passwordServer = passwordServer;
    }

    public String getDefaultEmailSender() {
        return defaultEmailSender;
    }

    public void setDefaultEmailSender(String defaultEmailSender) {
        this.defaultEmailSender = defaultEmailSender;
    }

    public String getDefaultEmailSubject() {
        return defaultEmailSubject;
    }

    public void setDefaultEmailSubject(String defaultEmailSubject) {
        this.defaultEmailSubject = defaultEmailSubject;
    }

    public String getDefaultEmailContent() {
        return defaultEmailContent;
    }

    public void setDefaultEmailContent(String defaultEmailContent) {
        this.defaultEmailContent = defaultEmailContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImCompany)) return false;

        ImCompany imCompany = (ImCompany) o;

        if (action != null ? !action.equals(imCompany.action) : imCompany.action != null) return false;
        if (address != null ? !address.equals(imCompany.address) : imCompany.address != null) return false;
        if (companyId != null ? !companyId.equals(imCompany.companyId) : imCompany.companyId != null) return false;
        if (companyName != null ? !companyName.equals(imCompany.companyName) : imCompany.companyName != null)
            return false;
        if (createdDate != null ? !createdDate.equals(imCompany.createdDate) : imCompany.createdDate != null)
            return false;
        if (createdWho != null ? !createdWho.equals(imCompany.createdWho) : imCompany.createdWho != null) return false;
        if (flag != null ? !flag.equals(imCompany.flag) : imCompany.flag != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(imCompany.lastUpdate) : imCompany.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(imCompany.lastUpdateWho) : imCompany.lastUpdateWho != null)
            return false;
        if (npwp != null ? !npwp.equals(imCompany.npwp) : imCompany.npwp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (npwp != null ? npwp.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImCompany{" +
                "companyId='" + companyId + '\'' +
                ", npwp='" + npwp + '\'' +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", createdDate=" + createdDate +
                ", createdWho='" + createdWho + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUpdateWho='" + lastUpdateWho + '\'' +
                ", flag='" + flag + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
