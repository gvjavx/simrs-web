package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;

public class PayrollModalDTO {
    private String bulan;
    private String tahun;
    private BigDecimal nilai;
    private String stNilai;
    private String jenisPtt;
    private BigDecimal jumlahNilai;
    private String stJumlahNilai;
    private String jenisPayroll;

    //modal pph seharusnya
    private BigDecimal totPendBruto;
    private BigDecimal totTunjPPh;
    private BigDecimal totPendTidakTeratur;
    private BigDecimal totBrut;
    private BigDecimal totIuran;
    private BigDecimal totBijab;
    private BigDecimal totReduce;
    private BigDecimal netSetahun;
    private BigDecimal ptkp;
    private BigDecimal pkp;
    private BigDecimal pphSeharusnya;

    private String stTotPendBruto;
    private String stTotTunjPPh;
    private String stTotPendTidakTeratur;
    private String stTotBrut;
    private String stTotIuran;
    private String stTotBijab;
    private String stTotReduce;
    private String stNetSetahun;
    private String stPtkp;
    private String stPkp;
    private String stPphSeharusnya;

    public String getJenisPayroll() {
        return jenisPayroll;
    }

    public void setJenisPayroll(String jenisPayroll) {
        this.jenisPayroll = jenisPayroll;
    }

    public BigDecimal getTotPendBruto() {
        return totPendBruto;
    }

    public void setTotPendBruto(BigDecimal totPendBruto) {
        this.totPendBruto = totPendBruto;
    }

    public BigDecimal getTotTunjPPh() {
        return totTunjPPh;
    }

    public void setTotTunjPPh(BigDecimal totTunjPPh) {
        this.totTunjPPh = totTunjPPh;
    }

    public BigDecimal getTotPendTidakTeratur() {
        return totPendTidakTeratur;
    }

    public void setTotPendTidakTeratur(BigDecimal totPendTidakTeratur) {
        this.totPendTidakTeratur = totPendTidakTeratur;
    }

    public BigDecimal getTotBrut() {
        return totBrut;
    }

    public void setTotBrut(BigDecimal totBrut) {
        this.totBrut = totBrut;
    }

    public BigDecimal getTotIuran() {
        return totIuran;
    }

    public void setTotIuran(BigDecimal totIuran) {
        this.totIuran = totIuran;
    }

    public BigDecimal getTotBijab() {
        return totBijab;
    }

    public void setTotBijab(BigDecimal totBijab) {
        this.totBijab = totBijab;
    }

    public BigDecimal getTotReduce() {
        return totReduce;
    }

    public void setTotReduce(BigDecimal totReduce) {
        this.totReduce = totReduce;
    }

    public BigDecimal getNetSetahun() {
        return netSetahun;
    }

    public void setNetSetahun(BigDecimal netSetahun) {
        this.netSetahun = netSetahun;
    }

    public BigDecimal getPtkp() {
        return ptkp;
    }

    public void setPtkp(BigDecimal ptkp) {
        this.ptkp = ptkp;
    }

    public BigDecimal getPkp() {
        return pkp;
    }

    public void setPkp(BigDecimal pkp) {
        this.pkp = pkp;
    }

    public BigDecimal getPphSeharusnya() {
        return pphSeharusnya;
    }

    public void setPphSeharusnya(BigDecimal pphSeharusnya) {
        this.pphSeharusnya = pphSeharusnya;
    }

    public String getStTotPendBruto() {
        return stTotPendBruto;
    }

    public void setStTotPendBruto(String stTotPendBruto) {
        this.stTotPendBruto = stTotPendBruto;
    }

    public String getStTotTunjPPh() {
        return stTotTunjPPh;
    }

    public void setStTotTunjPPh(String stTotTunjPPh) {
        this.stTotTunjPPh = stTotTunjPPh;
    }

    public String getStTotPendTidakTeratur() {
        return stTotPendTidakTeratur;
    }

    public void setStTotPendTidakTeratur(String stTotPendTidakTeratur) {
        this.stTotPendTidakTeratur = stTotPendTidakTeratur;
    }

    public String getStTotBrut() {
        return stTotBrut;
    }

    public void setStTotBrut(String stTotBrut) {
        this.stTotBrut = stTotBrut;
    }

    public String getStTotIuran() {
        return stTotIuran;
    }

    public void setStTotIuran(String stTotIuran) {
        this.stTotIuran = stTotIuran;
    }

    public String getStTotBijab() {
        return stTotBijab;
    }

    public void setStTotBijab(String stTotBijab) {
        this.stTotBijab = stTotBijab;
    }

    public String getStTotReduce() {
        return stTotReduce;
    }

    public void setStTotReduce(String stTotReduce) {
        this.stTotReduce = stTotReduce;
    }

    public String getStNetSetahun() {
        return stNetSetahun;
    }

    public void setStNetSetahun(String stNetSetahun) {
        this.stNetSetahun = stNetSetahun;
    }

    public String getStPtkp() {
        return stPtkp;
    }

    public void setStPtkp(String stPtkp) {
        this.stPtkp = stPtkp;
    }

    public String getStPkp() {
        return stPkp;
    }

    public void setStPkp(String stPkp) {
        this.stPkp = stPkp;
    }

    public String getStPphSeharusnya() {
        return stPphSeharusnya;
    }

    public void setStPphSeharusnya(String stPphSeharusnya) {
        this.stPphSeharusnya = stPphSeharusnya;
    }

    public BigDecimal getJumlahNilai() {
        return jumlahNilai;
    }

    public void setJumlahNilai(BigDecimal jumlahNilai) {
        this.jumlahNilai = jumlahNilai;
    }

    public String getStJumlahNilai() {
        return stJumlahNilai;
    }

    public void setStJumlahNilai(String stJumlahNilai) {
        this.stJumlahNilai = stJumlahNilai;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public String getStNilai() {
        return stNilai;
    }

    public void setStNilai(String stNilai) {
        this.stNilai = stNilai;
    }

    public String getJenisPtt() {
        return jenisPtt;
    }

    public void setJenisPtt(String jenisPtt) {
        this.jenisPtt = jenisPtt;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

}
