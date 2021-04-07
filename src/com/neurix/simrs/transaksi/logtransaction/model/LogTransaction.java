package com.neurix.simrs.transaksi.logtransaction.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class LogTransaction {
    private BigInteger pgLogTrxId;
    private String trxId;
    private String tipeTrx;
    private String bankName;
    private String noVirtualAccount;
    private String noRekamMedik;
    private BigDecimal trxAmount;
    private String namePerson;
    private String addressPerson;
    private String phonePerson;
    private String emailPerson;
    private String status;
    private String message;
    private Timestamp sentDate;
    private Timestamp receivedDate;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;

    //RAKA-untuk search berdasarkan tanggal
    private Timestamp DateStr;
    private String stDateStr;
    private Timestamp DateEnd;
    private String stDateEnd;


    public Timestamp getDateStr() {
        return DateStr;
    }

    public void setDateStr(Timestamp dateStr) {
        DateStr = dateStr;
    }

    public String getStDateStr() {
        return stDateStr;
    }

    public void setStDateStr(String stDateStr) {
        this.stDateStr = stDateStr;
    }

    public Timestamp getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        DateEnd = dateEnd;
    }

    public String getStDateEnd() {
        return stDateEnd;
    }

    public void setStDateEnd(String stDateEnd) {
        this.stDateEnd = stDateEnd;
    }

    public BigInteger getPgLogTrxId() {
        return pgLogTrxId;
    }

    public void setPgLogTrxId(BigInteger pgLogTrxId) {
        this.pgLogTrxId = pgLogTrxId;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getTipeTrx() {
        return tipeTrx;
    }

    public void setTipeTrx(String tipeTrx) {
        this.tipeTrx = tipeTrx;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNoVirtualAccount() {
        return noVirtualAccount;
    }

    public void setNoVirtualAccount(String noVirtualAccount) {
        this.noVirtualAccount = noVirtualAccount;
    }

    public String getNoRekamMedik() {
        return noRekamMedik;
    }

    public void setNoRekamMedik(String noRekamMedik) {
        this.noRekamMedik = noRekamMedik;
    }

    public BigDecimal getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(BigDecimal trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getAddressPerson() {
        return addressPerson;
    }

    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson;
    }

    public String getPhonePerson() {
        return phonePerson;
    }

    public void setPhonePerson(String phonePerson) {
        this.phonePerson = phonePerson;
    }

    public String getEmailPerson() {
        return emailPerson;
    }

    public void setEmailPerson(String emailPerson) {
        this.emailPerson = emailPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
