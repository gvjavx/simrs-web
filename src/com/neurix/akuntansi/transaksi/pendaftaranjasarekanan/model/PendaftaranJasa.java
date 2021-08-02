package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class PendaftaranJasa {
    private String id;
    private String namaJasa;
    private String idVendor;
    private String branchId;
    private BigInteger biaya;
    private String status;
    private String urlDoc;
    private String approveKeu;
    private String approveKasubKeu;
    private String approveKaKeu;
    private String flag;
    private String action;
    private String alasanBatal;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaJasa() {
        return namaJasa;
    }

    public void setNamaJasa(String namaJasa) {
        this.namaJasa = namaJasa;
    }

    public String getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(String idVendor) {
        this.idVendor = idVendor;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public BigInteger getBiaya() {
        return biaya;
    }

    public void setBiaya(BigInteger biaya) {
        this.biaya = biaya;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlDoc() {
        return urlDoc;
    }

    public void setUrlDoc(String urlDoc) {
        this.urlDoc = urlDoc;
    }

    public String getApproveKeu() {
        return approveKeu;
    }

    public void setApproveKeu(String approveKeu) {
        this.approveKeu = approveKeu;
    }

    public String getApproveKasubKeu() {
        return approveKasubKeu;
    }

    public void setApproveKasubKeu(String approveKasubKeu) {
        this.approveKasubKeu = approveKasubKeu;
    }

    public String getApproveKaKeu() {
        return approveKaKeu;
    }

    public void setApproveKaKeu(String approveKaKeu) {
        this.approveKaKeu = approveKaKeu;
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

    public String getAlasanBatal() {
        return alasanBatal;
    }

    public void setAlasanBatal(String alasanBatal) {
        this.alasanBatal = alasanBatal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PendaftaranJasa that = (PendaftaranJasa) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (namaJasa != null ? !namaJasa.equals(that.namaJasa) : that.namaJasa != null) return false;
        if (idVendor != null ? !idVendor.equals(that.idVendor) : that.idVendor != null) return false;
        if (branchId != null ? !branchId.equals(that.branchId) : that.branchId != null) return false;
        if (biaya != null ? !biaya.equals(that.biaya) : that.biaya != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (urlDoc != null ? !urlDoc.equals(that.urlDoc) : that.urlDoc != null) return false;
        if (approveKeu != null ? !approveKeu.equals(that.approveKeu) : that.approveKeu != null) return false;
        if (approveKasubKeu != null ? !approveKasubKeu.equals(that.approveKasubKeu) : that.approveKasubKeu != null)
            return false;
        if (approveKaKeu != null ? !approveKaKeu.equals(that.approveKaKeu) : that.approveKaKeu != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (alasanBatal != null ? !alasanBatal.equals(that.alasanBatal) : that.alasanBatal != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namaJasa != null ? namaJasa.hashCode() : 0);
        result = 31 * result + (idVendor != null ? idVendor.hashCode() : 0);
        result = 31 * result + (branchId != null ? branchId.hashCode() : 0);
        result = 31 * result + (biaya != null ? biaya.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (urlDoc != null ? urlDoc.hashCode() : 0);
        result = 31 * result + (approveKeu != null ? approveKeu.hashCode() : 0);
        result = 31 * result + (approveKasubKeu != null ? approveKasubKeu.hashCode() : 0);
        result = 31 * result + (approveKaKeu != null ? approveKaKeu.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (alasanBatal != null ? alasanBatal.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
