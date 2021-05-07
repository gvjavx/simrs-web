package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollKonsistensi extends BaseModel {
    private String tipeKonsistensi;

    private String strGajiKotor;
    private String strReduce;
    private String strLainLain;
    private String strPenambahan;
    private String strNilai;

    private BigDecimal gajiKotor;
    private BigDecimal reduce;
    private BigDecimal lainLain;
    private BigDecimal penambahan;
    private BigDecimal nilai;

    private String strGajiKotorSebelumnya;
    private String strReduceSebelumnya;
    private String strLainLainSebelumnya;
    private String strPenambahanSebelumnya;
    private String strNilaiSebelumnya;

    private BigDecimal gajiKotorSebelumnya;
    private BigDecimal reduceSebelumnya;
    private BigDecimal lainLainSebelumnya;
    private BigDecimal penambahanSebelumnya;
    private BigDecimal nilaiSebelumnya;

    public BigDecimal getGajiKotorSebelumnya() {
        return gajiKotorSebelumnya;
    }

    public void setGajiKotorSebelumnya(BigDecimal gajiKotorSebelumnya) {
        this.gajiKotorSebelumnya = gajiKotorSebelumnya;
    }

    public BigDecimal getLainLainSebelumnya() {
        return lainLainSebelumnya;
    }

    public void setLainLainSebelumnya(BigDecimal lainLainSebelumnya) {
        this.lainLainSebelumnya = lainLainSebelumnya;
    }

    public BigDecimal getNilaiSebelumnya() {
        return nilaiSebelumnya;
    }

    public void setNilaiSebelumnya(BigDecimal nilaiSebelumnya) {
        this.nilaiSebelumnya = nilaiSebelumnya;
    }

    public BigDecimal getPenambahanSebelumnya() {
        return penambahanSebelumnya;
    }

    public void setPenambahanSebelumnya(BigDecimal penambahanSebelumnya) {
        this.penambahanSebelumnya = penambahanSebelumnya;
    }

    public BigDecimal getReduceSebelumnya() {
        return reduceSebelumnya;
    }

    public void setReduceSebelumnya(BigDecimal reduceSebelumnya) {
        this.reduceSebelumnya = reduceSebelumnya;
    }

    public String getStrGajiKotorSebelumnya() {
        return strGajiKotorSebelumnya;
    }

    public void setStrGajiKotorSebelumnya(String strGajiKotorSebelumnya) {
        this.strGajiKotorSebelumnya = strGajiKotorSebelumnya;
    }

    public String getStrLainLainSebelumnya() {
        return strLainLainSebelumnya;
    }

    public void setStrLainLainSebelumnya(String strLainLainSebelumnya) {
        this.strLainLainSebelumnya = strLainLainSebelumnya;
    }

    public String getStrNilaiSebelumnya() {
        return strNilaiSebelumnya;
    }

    public void setStrNilaiSebelumnya(String strNilaiSebelumnya) {
        this.strNilaiSebelumnya = strNilaiSebelumnya;
    }

    public String getStrPenambahanSebelumnya() {
        return strPenambahanSebelumnya;
    }

    public void setStrPenambahanSebelumnya(String strPenambahanSebelumnya) {
        this.strPenambahanSebelumnya = strPenambahanSebelumnya;
    }

    public String getStrReduceSebelumnya() {
        return strReduceSebelumnya;
    }

    public void setStrReduceSebelumnya(String strReduceSebelumnya) {
        this.strReduceSebelumnya = strReduceSebelumnya;
    }

    public BigDecimal getGajiKotor() {
        return gajiKotor;
    }

    public void setGajiKotor(BigDecimal gajiKotor) {
        this.gajiKotor = gajiKotor;
    }

    public BigDecimal getLainLain() {
        return lainLain;
    }

    public void setLainLain(BigDecimal lainLain) {
        this.lainLain = lainLain;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getPenambahan() {
        return penambahan;
    }

    public void setPenambahan(BigDecimal penambahan) {
        this.penambahan = penambahan;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }

    public String getStrGajiKotor() {
        return strGajiKotor;
    }

    public void setStrGajiKotor(String strGajiKotor) {
        this.strGajiKotor = strGajiKotor;
    }

    public String getStrLainLain() {
        return strLainLain;
    }

    public void setStrLainLain(String strLainLain) {
        this.strLainLain = strLainLain;
    }

    public String getStrNilai() {
        return strNilai;
    }

    public void setStrNilai(String strNilai) {
        this.strNilai = strNilai;
    }

    public String getStrPenambahan() {
        return strPenambahan;
    }

    public void setStrPenambahan(String strPenambahan) {
        this.strPenambahan = strPenambahan;
    }

    public String getStrReduce() {
        return strReduce;
    }

    public void setStrReduce(String strReduce) {
        this.strReduce = strReduce;
    }

    public String getTipeKonsistensi() {
        return tipeKonsistensi;
    }

    public void setTipeKonsistensi(String tipeKonsistensi) {
        this.tipeKonsistensi = tipeKonsistensi;
    }
}