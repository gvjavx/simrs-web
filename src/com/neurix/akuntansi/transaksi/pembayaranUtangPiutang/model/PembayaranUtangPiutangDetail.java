package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PembayaranUtangPiutangDetail extends BaseModel {
    private String pembayaranUtangPiutangDetailId;
    private String pembayaranUtangPiutangId;
    private String masterId;
    private String noNota;
    private String divisiId;
    private String divisiName;

    private String pengajuanBiayaDetailId;
    private String stJumlahPembayaran;
    private String masterName;
    private String rekeningId;
    private String kodeRekening;
    private String posisiCoa;
    private String noBugetting;
    private String stPpn;
    private String stPph;
    private String noFakturPajak;
    private String stFileUpload;
    private String urlFakturImage;

    public String getUrlFakturImage() {
        return urlFakturImage;
    }

    public void setUrlFakturImage(String urlFakturImage) {
        this.urlFakturImage = urlFakturImage;
    }

    public String getNoFakturPajak() {
        return noFakturPajak;
    }

    public void setNoFakturPajak(String noFakturPajak) {
        this.noFakturPajak = noFakturPajak;
    }

    public String getStFileUpload() {
        return stFileUpload;
    }

    public void setStFileUpload(String stFileUpload) {
        this.stFileUpload = stFileUpload;
    }

    public String getStPpn() {
        return stPpn;
    }

    public void setStPpn(String stPpn) {
        this.stPpn = stPpn;
    }

    public String getStPph() {
        return stPph;
    }

    public void setStPph(String stPph) {
        this.stPph = stPph;
    }

    public String getNoBugetting() {
        return noBugetting;
    }

    public void setNoBugetting(String noBugetting) {
        this.noBugetting = noBugetting;
    }

    public String getPengajuanBiayaDetailId() {
        return pengajuanBiayaDetailId;
    }

    public void setPengajuanBiayaDetailId(String pengajuanBiayaDetailId) {
        this.pengajuanBiayaDetailId = pengajuanBiayaDetailId;
    }

    public String getPosisiCoa() {
        return posisiCoa;
    }

    public void setPosisiCoa(String posisiCoa) {
        this.posisiCoa = posisiCoa;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getRekeningId() {
        return rekeningId;
    }

    public void setRekeningId(String rekeningId) {
        this.rekeningId = rekeningId;
    }

    public String getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(String kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getStJumlahPembayaran() {
        return stJumlahPembayaran;
    }

    public void setStJumlahPembayaran(String stJumlahPembayaran) {
        this.stJumlahPembayaran = stJumlahPembayaran;
    }

    public String getPembayaranUtangPiutangDetailId() {
        return pembayaranUtangPiutangDetailId;
    }

    public void setPembayaranUtangPiutangDetailId(String pembayaranUtangPiutangDetailId) {
        this.pembayaranUtangPiutangDetailId = pembayaranUtangPiutangDetailId;
    }

    public String getPembayaranUtangPiutangId() {
        return pembayaranUtangPiutangId;
    }

    public void setPembayaranUtangPiutangId(String pembayaranUtangPiutangId) {
        this.pembayaranUtangPiutangId = pembayaranUtangPiutangId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

}