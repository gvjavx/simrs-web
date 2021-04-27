package com.neurix.akuntansi.transaksi.billingSystem.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

public class ItPgLogTransactionEntity {
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

    private String statusBank;
    private String channel;
    private Date invoiceDate;
    private BigInteger invoiceNumber;

    public String getStatusBank() {
        return statusBank;
    }

    public void setStatusBank(String statusBank) {
        this.statusBank = statusBank;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigInteger getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(BigInteger invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItPgLogTransactionEntity that = (ItPgLogTransactionEntity) o;
        return pgLogTrxId == that.pgLogTrxId &&
                Objects.equals(trxId, that.trxId) &&
                Objects.equals(tipeTrx, that.tipeTrx) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(noVirtualAccount, that.noVirtualAccount) &&
                Objects.equals(noRekamMedik, that.noRekamMedik) &&
                Objects.equals(trxAmount, that.trxAmount) &&
                Objects.equals(namePerson, that.namePerson) &&
                Objects.equals(addressPerson, that.addressPerson) &&
                Objects.equals(phonePerson, that.phonePerson) &&
                Objects.equals(emailPerson, that.emailPerson) &&
                Objects.equals(status, that.status) &&
                Objects.equals(message, that.message) &&
                Objects.equals(sentDate, that.sentDate) &&
                Objects.equals(receivedDate, that.receivedDate) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pgLogTrxId, trxId, tipeTrx, bankName, noVirtualAccount, noRekamMedik, trxAmount, namePerson, addressPerson, phonePerson, emailPerson, status, message, sentDate, receivedDate, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag);
    }
}
