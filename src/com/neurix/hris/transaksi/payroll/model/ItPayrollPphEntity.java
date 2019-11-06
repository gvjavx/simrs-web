package com.neurix.hris.transaksi.payroll.model;


import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.department.model.ImDepartmentEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ItPayrollPphEntity implements Serializable {
    private String pphId;
    private String payrollId;
    private BigDecimal ptkp;
    private BigDecimal gaji;
    private BigDecimal tunjanganUmk;
    private BigDecimal tunjanganStrategis;
    private BigDecimal tunjanganPeralihan;
    private BigDecimal tunjanganJabatanStruktural;
    private BigDecimal tunjanganStruktural;
    private BigDecimal tunjanganKompensasi;
    private BigDecimal tunjanganTransport;
    private BigDecimal tunjanganAirListrik;
    private BigDecimal tunjanganPerumahan;
    private BigDecimal tunjanganPengobatan;
    private BigDecimal tunjanganPph;
    private BigDecimal tunjanganLembur;
    private BigDecimal tunjanganLainLain;
    private BigDecimal thr;
    private BigDecimal asumsiThr;
    private BigDecimal rapel;
    private BigDecimal asumsiPendidikan;
    private BigDecimal pendidikan;
    private BigDecimal jasprod;
    private BigDecimal insentif;
    private BigDecimal asumsiJasprod;
    private BigDecimal jubileum;
    private BigDecimal pensiun;
    private BigDecimal pakaianDinas;
    private BigDecimal iuranJkmJkk;
    private BigDecimal biayaJabatan;
    private BigDecimal iuranPensiun;
    private BigDecimal pkp;
    private BigDecimal hutangPph;

    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private String flagKalkulasiPph;
    private String flagKalkulasiPphPengobatan;

    private BigDecimal jumlahPengobatan;
    private BigDecimal jumlahPphPengobatan;
    private BigDecimal kurangPphPengobatan;
    private BigDecimal hutangPphPengobatan;
    private BigDecimal pphPengobatan;

    private BigDecimal danaPensiun;
    private BigDecimal bpjsJht;
    private BigDecimal bpjsPensiun;
    private BigDecimal bruto;
    private BigDecimal reduce;
    private BigDecimal pphGaji;

    public String getFlagKalkulasiPphPengobatan() {
        return flagKalkulasiPphPengobatan;
    }

    public void setFlagKalkulasiPphPengobatan(String flagKalkulasiPphPengobatan) {
        this.flagKalkulasiPphPengobatan = flagKalkulasiPphPengobatan;
    }

    public BigDecimal getHutangPphPengobatan() {
        return hutangPphPengobatan;
    }

    public void setHutangPphPengobatan(BigDecimal hutangPphPengobatan) {
        this.hutangPphPengobatan = hutangPphPengobatan;
    }

    public BigDecimal getJumlahPengobatan() {
        return jumlahPengobatan;
    }

    public void setJumlahPengobatan(BigDecimal jumlahPengobatan) {
        this.jumlahPengobatan = jumlahPengobatan;
    }

    public BigDecimal getJumlahPphPengobatan() {
        return jumlahPphPengobatan;
    }

    public void setJumlahPphPengobatan(BigDecimal jumlahPphPengobatan) {
        this.jumlahPphPengobatan = jumlahPphPengobatan;
    }

    public BigDecimal getKurangPphPengobatan() {
        return kurangPphPengobatan;
    }

    public void setKurangPphPengobatan(BigDecimal kurangPphPengobatan) {
        this.kurangPphPengobatan = kurangPphPengobatan;
    }

    public BigDecimal getPphPengobatan() {
        return pphPengobatan;
    }

    public void setPphPengobatan(BigDecimal pphPengobatan) {
        this.pphPengobatan = pphPengobatan;
    }

    public BigDecimal getInsentif() {
        return insentif;
    }

    public void setInsentif(BigDecimal insentif) {
        this.insentif = insentif;
    }

    public String getFlagKalkulasiPph() {
        return flagKalkulasiPph;
    }

    public void setFlagKalkulasiPph(String flagKalkulasiPph) {
        this.flagKalkulasiPph = flagKalkulasiPph;
    }

    public BigDecimal getAsumsiJasprod() {
        return asumsiJasprod;
    }

    public void setAsumsiJasprod(BigDecimal asumsiJasprod) {
        this.asumsiJasprod = asumsiJasprod;
    }

    public BigDecimal getAsumsiThr() {
        return asumsiThr;
    }

    public void setAsumsiThr(BigDecimal asumsiThr) {
        this.asumsiThr = asumsiThr;
    }

    public BigDecimal getAsumsiPendidikan() {
        return asumsiPendidikan;
    }

    public void setAsumsiPendidikan(BigDecimal asumsiPendidikan) {
        this.asumsiPendidikan = asumsiPendidikan;
    }

    public BigDecimal getPphGaji() {
        return pphGaji;
    }

    public void setPphGaji(BigDecimal pphGaji) {
        this.pphGaji = pphGaji;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }

    public BigDecimal getBpjsJht() {
        return bpjsJht;
    }

    public void setBpjsJht(BigDecimal bpjsJht) {
        this.bpjsJht = bpjsJht;
    }

    public BigDecimal getBpjsPensiun() {
        return bpjsPensiun;
    }

    public void setBpjsPensiun(BigDecimal bpjsPensiun) {
        this.bpjsPensiun = bpjsPensiun;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(BigDecimal danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public BigDecimal getRapel() {
        return rapel;
    }

    public void setRapel(BigDecimal rapel) {
        this.rapel = rapel;
    }

    public BigDecimal getPensiun() {
        return pensiun;
    }

    public void setPensiun(BigDecimal pensiun) {
        this.pensiun = pensiun;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(BigDecimal biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getHutangPph() {
        return hutangPph;
    }

    public void setHutangPph(BigDecimal hutangPph) {
        this.hutangPph = hutangPph;
    }

    public BigDecimal getIuranJkmJkk() {
        return iuranJkmJkk;
    }

    public void setIuranJkmJkk(BigDecimal iuranJkmJkk) {
        this.iuranJkmJkk = iuranJkmJkk;
    }

    public BigDecimal getIuranPensiun() {
        return iuranPensiun;
    }

    public void setIuranPensiun(BigDecimal iuranPensiun) {
        this.iuranPensiun = iuranPensiun;
    }

    public BigDecimal getJasprod() {
        return jasprod;
    }

    public void setJasprod(BigDecimal jasprod) {
        this.jasprod = jasprod;
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

    public BigDecimal getJubileum() {
        return jubileum;
    }

    public void setJubileum(BigDecimal jubileum) {
        this.jubileum = jubileum;
    }

    public BigDecimal getPakaianDinas() {
        return pakaianDinas;
    }

    public void setPakaianDinas(BigDecimal pakaianDinas) {
        this.pakaianDinas = pakaianDinas;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(BigDecimal pendidikan) {
        this.pendidikan = pendidikan;
    }

    public BigDecimal getPkp() {
        return pkp;
    }

    public void setPkp(BigDecimal pkp) {
        this.pkp = pkp;
    }

    public String getPphId() {
        return pphId;
    }

    public void setPphId(String pphId) {
        this.pphId = pphId;
    }

    public BigDecimal getPtkp() {
        return ptkp;
    }

    public void setPtkp(BigDecimal ptkp) {
        this.ptkp = ptkp;
    }

    public BigDecimal getThr() {
        return thr;
    }

    public void setThr(BigDecimal thr) {
        this.thr = thr;
    }

    public BigDecimal getTunjanganAirListrik() {
        return tunjanganAirListrik;
    }

    public void setTunjanganAirListrik(BigDecimal tunjanganAirListrik) {
        this.tunjanganAirListrik = tunjanganAirListrik;
    }

    public BigDecimal getTunjanganJabatanStruktural() {
        return tunjanganJabatanStruktural;
    }

    public void setTunjanganJabatanStruktural(BigDecimal tunjanganJabatanStruktural) {
        this.tunjanganJabatanStruktural = tunjanganJabatanStruktural;
    }

    public BigDecimal getTunjanganKompensasi() {
        return tunjanganKompensasi;
    }

    public void setTunjanganKompensasi(BigDecimal tunjanganKompensasi) {
        this.tunjanganKompensasi = tunjanganKompensasi;
    }

    public BigDecimal getTunjanganLainLain() {
        return tunjanganLainLain;
    }

    public void setTunjanganLainLain(BigDecimal tunjanganLainLain) {
        this.tunjanganLainLain = tunjanganLainLain;
    }

    public BigDecimal getTunjanganLembur() {
        return tunjanganLembur;
    }

    public void setTunjanganLembur(BigDecimal tunjanganLembur) {
        this.tunjanganLembur = tunjanganLembur;
    }

    public BigDecimal getTunjanganPengobatan() {
        return tunjanganPengobatan;
    }

    public void setTunjanganPengobatan(BigDecimal tunjanganPengobatan) {
        this.tunjanganPengobatan = tunjanganPengobatan;
    }

    public BigDecimal getTunjanganPeralihan() {
        return tunjanganPeralihan;
    }

    public void setTunjanganPeralihan(BigDecimal tunjanganPeralihan) {
        this.tunjanganPeralihan = tunjanganPeralihan;
    }

    public BigDecimal getTunjanganPerumahan() {
        return tunjanganPerumahan;
    }

    public void setTunjanganPerumahan(BigDecimal tunjanganPerumahan) {
        this.tunjanganPerumahan = tunjanganPerumahan;
    }

    public BigDecimal getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getTunjanganStrategis() {
        return tunjanganStrategis;
    }

    public void setTunjanganStrategis(BigDecimal tunjanganStrategis) {
        this.tunjanganStrategis = tunjanganStrategis;
    }

    public BigDecimal getTunjanganStruktural() {
        return tunjanganStruktural;
    }

    public void setTunjanganStruktural(BigDecimal tunjanganStruktural) {
        this.tunjanganStruktural = tunjanganStruktural;
    }

    public BigDecimal getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(BigDecimal tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public BigDecimal getTunjanganUmk() {
        return tunjanganUmk;
    }

    public void setTunjanganUmk(BigDecimal tunjanganUmk) {
        this.tunjanganUmk = tunjanganUmk;
    }
}
