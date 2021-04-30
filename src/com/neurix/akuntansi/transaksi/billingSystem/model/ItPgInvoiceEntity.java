package com.neurix.akuntansi.transaksi.billingSystem.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ItPgInvoiceEntity {
    private String pgInvoiceId;
    private String noInvoice;
    private Date invoiceDate;
    private String noRekamMedik;
    private BigDecimal trxAmount;
    private String namePerson;
    private String addressPerson;
    private String phonePerson;
    private String emailPerson;
    private String branchId;
    private String status;
    private String bankName;
    private String noVirtualAccount;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String action;
    private String flag;
    private String description;

    public String getPgInvoiceId() {
        return pgInvoiceId;
    }

    public void setPgInvoiceId(String pgInvoiceId) {
        this.pgInvoiceId = pgInvoiceId;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItPgInvoiceEntity that = (ItPgInvoiceEntity) o;
        return Objects.equals(pgInvoiceId, that.pgInvoiceId) &&
                Objects.equals(noInvoice, that.noInvoice) &&
                Objects.equals(invoiceDate, that.invoiceDate) &&
                Objects.equals(noRekamMedik, that.noRekamMedik) &&
                Objects.equals(trxAmount, that.trxAmount) &&
                Objects.equals(namePerson, that.namePerson) &&
                Objects.equals(addressPerson, that.addressPerson) &&
                Objects.equals(phonePerson, that.phonePerson) &&
                Objects.equals(emailPerson, that.emailPerson) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(noVirtualAccount, that.noVirtualAccount) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdWho, that.createdWho) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(lastUpdateWho, that.lastUpdateWho) &&
                Objects.equals(action, that.action) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pgInvoiceId, noInvoice, invoiceDate, noRekamMedik, trxAmount, namePerson, addressPerson, phonePerson, emailPerson, branchId, status, bankName, noVirtualAccount, createdDate, createdWho, lastUpdate, lastUpdateWho, action, flag, description);
    }
}
