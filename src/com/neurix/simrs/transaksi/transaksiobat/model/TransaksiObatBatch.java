package com.neurix.simrs.transaksi.transaksiobat.model;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by reza on 16/01/20.
 */
public class TransaksiObatBatch {

    private BigInteger id;
    private String idTransaksiObatDetail;
    private Integer noBatch;
    private String jenisSatuan;
    private String status;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private String approveFlag;
    private BigInteger qtyApprove;
    private Date expiredDate;
    private String flag;
    private String action;
    private String idBarang;
    private String diterimaFlag;

    public String getDiterimaFlag() {
        return diterimaFlag;
    }

    public void setDiterimaFlag(String diterimaFlag) {
        this.diterimaFlag = diterimaFlag;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getIdTransaksiObatDetail() {
        return idTransaksiObatDetail;
    }

    public void setIdTransaksiObatDetail(String idTransaksiObatDetail) {
        this.idTransaksiObatDetail = idTransaksiObatDetail;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public BigInteger getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(BigInteger qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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
}
