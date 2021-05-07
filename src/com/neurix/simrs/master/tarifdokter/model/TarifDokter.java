package com.neurix.simrs.master.tarifdokter.model;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TarifDokter {
    private String idTarifDokter;
    private String idDokter;
    private String idJenisPasien;
    private Long tarifKonsul;
    private Long tarifVisite;
    private Long tarifTindakan;

    public String getIdTarifDokter() {
        return idTarifDokter;
    }

    public void setIdTarifDokter(String idTarifDokter) {
        this.idTarifDokter = idTarifDokter;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdJenisPasien() {
        return idJenisPasien;
    }

    public void setIdJenisPasien(String idJenisPasien) {
        this.idJenisPasien = idJenisPasien;
    }

    public Long getTarifKonsul() {
        return tarifKonsul;
    }

    public void setTarifKonsul(Long tarifKonsul) {
        this.tarifKonsul = tarifKonsul;
    }

    public Long getTarifVisite() {
        return tarifVisite;
    }

    public void setTarifVisite(Long tarifVisite) {
        this.tarifVisite = tarifVisite;
    }

    public Long getTarifTindakan() {
        return tarifTindakan;
    }

    public void setTarifTindakan(Long tarifTindakan) {
        this.tarifTindakan = tarifTindakan;
    }

}
