package com.neurix.simrs.transaksi.paketperiksa.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by reza on 12/03/20.
 */
public class ItemPaket {
    private String idItemPaket;
    private String idPaket;
    private String idItem;
    private String namaItem;
    private String idKategoriItem;
    private String namaKategoriItem;
    private String jenisItem;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal tarif;

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }

    public String getIdItemPaket() {
        return idItemPaket;
    }

    public void setIdItemPaket(String idItemPaket) {
        this.idItemPaket = idItemPaket;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public String getIdKategoriItem() {
        return idKategoriItem;
    }

    public void setIdKategoriItem(String idKategoriItem) {
        this.idKategoriItem = idKategoriItem;
    }

    public String getNamaKategoriItem() {
        return namaKategoriItem;
    }

    public void setNamaKategoriItem(String namaKategoriItem) {
        this.namaKategoriItem = namaKategoriItem;
    }

    public String getJenisItem() {
        return jenisItem;
    }

    public void setJenisItem(String jenisItem) {
        this.jenisItem = jenisItem;
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
}
