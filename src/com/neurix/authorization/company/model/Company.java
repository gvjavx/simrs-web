package com.neurix.authorization.company.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class Company extends BaseModel implements Serializable {
    private String companyId;
    private String companyName;
    private String address;
    private String npwp;
    private String serviceOnOff;
    private String mailServer;
    private String portServer;
    private String userNameServer;
    private String passwordServer;
    private String defaultEmailSender;
    private String defaultEmailSubject;
    private String defaultEmailContent;
    private BigDecimal minimumLuasan;
    private BigDecimal iuranPerusahaanJkmJkk;
    private String stMinimumLuasan;
    private double biayaJabatanPersentase;
    private BigInteger remainderJubileum;
    private BigInteger remainderPensiun;
    private BigDecimal kursDolar ;

    private BigDecimal payrollThrPersentase;
    private BigDecimal payrollPendidikanPersentase;
    private BigDecimal payrollJasprodKali ;

    private BigDecimal maxBpjsTk;
    private BigDecimal maxBpjsPensiun;
    private BigDecimal maxBpjsKesehatan ;
    private String periodeGaji;


    public String getPeriodeGaji() {
        return periodeGaji;
    }

    public void setPeriodeGaji(String periodeGaji) {
        this.periodeGaji = periodeGaji;
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

    public BigDecimal getKursDolar() {
        return kursDolar;
    }

    public void setKursDolar(BigDecimal kursDolar) {
        this.kursDolar = kursDolar;
    }

    public BigInteger getRemainderJubileum() {
        return remainderJubileum;
    }

    public void setRemainderJubileum(BigInteger remainderJubileum) {
        this.remainderJubileum = remainderJubileum;
    }

    public BigInteger getRemainderPensiun() {
        return remainderPensiun;
    }

    public void setRemainderPensiun(BigInteger remainderPensiun) {
        this.remainderPensiun = remainderPensiun;
    }

    public BigDecimal getIuranPerusahaanJkmJkk() {
        return iuranPerusahaanJkmJkk;
    }

    public void setIuranPerusahaanJkmJkk(BigDecimal iuranPerusahaanJkmJkk) {
        this.iuranPerusahaanJkmJkk = iuranPerusahaanJkmJkk;
    }

    public double getBiayaJabatanPersentase() {
        return biayaJabatanPersentase;
    }

    public void setBiayaJabatanPersentase(double biayaJabatanPersentase) {
        this.biayaJabatanPersentase = biayaJabatanPersentase;
    }

    public String getStMinimumLuasan() {
        return stMinimumLuasan;
    }

    public void setStMinimumLuasan(String stMinimumLuasan) {
        this.stMinimumLuasan = stMinimumLuasan;
    }

    public BigDecimal getMinimumLuasan() {
        return minimumLuasan;
    }

    public void setMinimumLuasan(BigDecimal minimumLuasan) {
        this.minimumLuasan = minimumLuasan;
    }

    public String getCompanyId() {

        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
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
}
