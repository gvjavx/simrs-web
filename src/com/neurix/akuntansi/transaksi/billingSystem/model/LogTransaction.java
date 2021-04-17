package com.neurix.akuntansi.transaksi.billingSystem.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

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
    private String stSentDate;
    private Timestamp receivedDate;
    private String stReceivedDate;
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

    private Timestamp receivedDateFrom;
    private String stReceivedDateFrom;
    private Timestamp receivedDateTo;
    private String stReceivedDateTo;

    private Timestamp sentDateFrom;
    private String stSentDateFrom;
    private Timestamp sentDateTo;
    private String stSentDateTo;

    //RAKA-untuk search berdasarkan tanggal
    private Timestamp DateStr;
    private String stDateStr;
    private Timestamp DateEnd;
    private String stDateEnd;

    private String stInvDateFrom;
    private String stInvDateTo;


    public String getStInvDateFrom() {
        return stInvDateFrom;
    }

    public void setStInvDateFrom(String stInvDateFrom) {
        this.stInvDateFrom = stInvDateFrom;
    }

    public String getStInvDateTo() {
        return stInvDateTo;
    }

    public void setStInvDateTo(String stInvDateTo) {
        this.stInvDateTo = stInvDateTo;
    }

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
    //RAKA-end

    public Timestamp getReceivedDateFrom() {
        return receivedDateFrom;
    }

    public void setReceivedDateFrom(Timestamp receivedDateFrom) {
        this.receivedDateFrom = receivedDateFrom;
    }

    public String getStReceivedDateFrom() {
        return stReceivedDateFrom;
    }

    public void setStReceivedDateFrom(String stReceivedDateFrom) {
        this.stReceivedDateFrom = stReceivedDateFrom;
    }

    public Timestamp getReceivedDateTo() {
        return receivedDateTo;
    }

    public void setReceivedDateTo(Timestamp receivedDateTo) {
        this.receivedDateTo = receivedDateTo;
    }

    public String getStReceivedDateTo() {
        return stReceivedDateTo;
    }

    public void setStReceivedDateTo(String stReceivedDateTo) {
        this.stReceivedDateTo = stReceivedDateTo;
    }

    public Timestamp getSentDateFrom() {
        return sentDateFrom;
    }

    public void setSentDateFrom(Timestamp sentDateFrom) {
        this.sentDateFrom = sentDateFrom;
    }

    public String getStSentDateFrom() {
        return stSentDateFrom;
    }

    public void setStSentDateFrom(String stSentDateFrom) {
        this.stSentDateFrom = stSentDateFrom;
    }

    public Timestamp getSentDateTo() {
        return sentDateTo;
    }

    public void setSentDateTo(Timestamp sentDateTo) {
        this.sentDateTo = sentDateTo;
    }

    public String getStSentDateTo() {
        return stSentDateTo;
    }

    public void setStSentDateTo(String stSentDateTo) {
        this.stSentDateTo = stSentDateTo;
    }

    public String getStSentDate() {
        return stSentDate;
    }

    public void setStSentDate(String stSentDate) {
        this.stSentDate = stSentDate;
    }

    public String getStReceivedDate() {
        return stReceivedDate;
    }

    public void setStReceivedDate(String stReceivedDate) {
        this.stReceivedDate = stReceivedDate;
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
}
