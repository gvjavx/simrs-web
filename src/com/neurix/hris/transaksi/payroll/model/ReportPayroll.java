package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Aji Noor on 11/05/2021
 */
public class ReportPayroll {
    private String nip;
    private String nama;
    private String golKary;
    private String golPens;
    private String statPeg;
    private String noCek;
    private	String statKeluarga;
    private BigDecimal gaji;
    private	BigDecimal sankhus;
    private	BigDecimal tunjJab;
    private	BigDecimal tunjStruk;
    private	BigDecimal tunjFung;
    private	BigDecimal tunjProf;
    private	BigDecimal tjAlihGapok;
    private	BigDecimal tjAlihSankhus;
    private	BigDecimal tjAlihTunj;
    private	BigDecimal tunjAlihTot;
    private	BigDecimal rplGaji;
    private	BigDecimal rplSankhus;
    private	BigDecimal rplTunjJab;
    private	BigDecimal rplTunjStr;
    private	BigDecimal rplTunjFung;
    private	BigDecimal rplTunjProf;
    private	BigDecimal rplTunjAlih;
    private	BigDecimal tunjKom;
    private	BigDecimal tunjTbh;
    private	BigDecimal tunjLain;
    private	BigDecimal tunjLok;
    private	BigDecimal jamLbr;
    private	BigDecimal fJamLbr;
    private	BigDecimal byLembur;
    private	BigDecimal upahLembur;
    private	BigDecimal tunjRmh;
    private	BigDecimal tunjList;
    private	BigDecimal tunjAir;
    private	BigDecimal tunjBbm;
    private	BigDecimal tunjSos;
    private	BigDecimal rplRlab;
    private	BigDecimal tunjSiaga;
    private	BigDecimal tjPensiunPers;
    private	BigDecimal tjBpjsTkPers;
    private	BigDecimal tjBpjsKsPers;
    private	BigDecimal pendRutin;
    private	BigDecimal pendTdkRutin;
    private	BigDecimal poPph;
    private	BigDecimal poYpks;
    private	BigDecimal iurPensiunPeg;
    private	BigDecimal iurPensiunPers;
    private	BigDecimal iurBpjsTkPeg;
    private	BigDecimal iurBpjsTkPers;
    private	BigDecimal iurBpjsKsPeg;
    private	BigDecimal iurBpjsKsPers;
    private	BigDecimal poLain;
    private	String perkbayar;
    private	String klomperk;
    private	String kdpoli;
    private	String bkodeb;
    private	String pgol;
    private	String pruang;
    private	String pmasa;
    private	String noskdapen;
    private	String noskgaji;
    private	String noidbio;
    private	String prypks;
    private	String prdapenp;
    private	String prdapeng;
    private	String prbpjstp;
    private	String prbpjstg;
    private	String prbpjssp;
    private	String prbpjssg;

    private String npwp;
    private Date tglAwalLbr;
    private Date tglAkhirLbr;

    private String nipLama;
    private String tipePegawai;
    private String golonganId;
    private String tahunSkalaGaji;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGolKary() {
        return golKary;
    }

    public void setGolKary(String golKary) {
        this.golKary = golKary;
    }

    public String getGolPens() {
        return golPens;
    }

    public void setGolPens(String golPens) {
        this.golPens = golPens;
    }

    public String getStatPeg() {
        return statPeg;
    }

    public void setStatPeg(String statPeg) {
        this.statPeg = statPeg;
    }

    public String getNoCek() {
        return noCek;
    }

    public void setNoCek(String noCek) {
        this.noCek = noCek;
    }

    public String getStatKeluarga() {
        return statKeluarga;
    }

    public void setStatKeluarga(String statKeluarga) {
        this.statKeluarga = statKeluarga;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public BigDecimal getSankhus() {
        return sankhus;
    }

    public void setSankhus(BigDecimal sankhus) {
        this.sankhus = sankhus;
    }

    public BigDecimal getTunjJab() {
        return tunjJab;
    }

    public void setTunjJab(BigDecimal tunjJab) {
        this.tunjJab = tunjJab;
    }

    public BigDecimal getTunjStruk() {
        return tunjStruk;
    }

    public void setTunjStruk(BigDecimal tunjStruk) {
        this.tunjStruk = tunjStruk;
    }

    public BigDecimal getTunjFung() {
        return tunjFung;
    }

    public void setTunjFung(BigDecimal tunjFung) {
        this.tunjFung = tunjFung;
    }

    public BigDecimal getTunjProf() {
        return tunjProf;
    }

    public void setTunjProf(BigDecimal tunjProf) {
        this.tunjProf = tunjProf;
    }

    public BigDecimal getTjAlihGapok() {
        return tjAlihGapok;
    }

    public void setTjAlihGapok(BigDecimal tjAlihGapok) {
        this.tjAlihGapok = tjAlihGapok;
    }

    public BigDecimal getTjAlihSankhus() {
        return tjAlihSankhus;
    }

    public void setTjAlihSankhus(BigDecimal tjAlihSankhus) {
        this.tjAlihSankhus = tjAlihSankhus;
    }

    public BigDecimal getTjAlihTunj() {
        return tjAlihTunj;
    }

    public void setTjAlihTunj(BigDecimal tjAlihTunj) {
        this.tjAlihTunj = tjAlihTunj;
    }

    public BigDecimal getTunjAlihTot() {
        return tunjAlihTot;
    }

    public void setTunjAlihTot(BigDecimal tunjAlihTot) {
        this.tunjAlihTot = tunjAlihTot;
    }

    public BigDecimal getRplGaji() {
        return rplGaji;
    }

    public void setRplGaji(BigDecimal rplGaji) {
        this.rplGaji = rplGaji;
    }

    public BigDecimal getRplSankhus() {
        return rplSankhus;
    }

    public void setRplSankhus(BigDecimal rplSankhus) {
        this.rplSankhus = rplSankhus;
    }

    public BigDecimal getRplTunjJab() {
        return rplTunjJab;
    }

    public void setRplTunjJab(BigDecimal rplTunjJab) {
        this.rplTunjJab = rplTunjJab;
    }

    public BigDecimal getRplTunjStr() {
        return rplTunjStr;
    }

    public void setRplTunjStr(BigDecimal rplTunjStr) {
        this.rplTunjStr = rplTunjStr;
    }

    public BigDecimal getRplTunjFung() {
        return rplTunjFung;
    }

    public void setRplTunjFung(BigDecimal rplTunjFung) {
        this.rplTunjFung = rplTunjFung;
    }

    public BigDecimal getRplTunjProf() {
        return rplTunjProf;
    }

    public void setRplTunjProf(BigDecimal rplTunjProf) {
        this.rplTunjProf = rplTunjProf;
    }

    public BigDecimal getRplTunjAlih() {
        return rplTunjAlih;
    }

    public void setRplTunjAlih(BigDecimal rplTunjAlih) {
        this.rplTunjAlih = rplTunjAlih;
    }

    public BigDecimal getTunjKom() {
        return tunjKom;
    }

    public void setTunjKom(BigDecimal tunjKom) {
        this.tunjKom = tunjKom;
    }

    public BigDecimal getTunjTbh() {
        return tunjTbh;
    }

    public void setTunjTbh(BigDecimal tunjTbh) {
        this.tunjTbh = tunjTbh;
    }

    public BigDecimal getTunjLain() {
        return tunjLain;
    }

    public void setTunjLain(BigDecimal tunjLain) {
        this.tunjLain = tunjLain;
    }

    public BigDecimal getTunjLok() {
        return tunjLok;
    }

    public void setTunjLok(BigDecimal tunjLok) {
        this.tunjLok = tunjLok;
    }

    public BigDecimal getJamLbr() {
        return jamLbr;
    }

    public void setJamLbr(BigDecimal jamLbr) {
        this.jamLbr = jamLbr;
    }

    public BigDecimal getfJamLbr() {
        return fJamLbr;
    }

    public void setfJamLbr(BigDecimal fJamLbr) {
        this.fJamLbr = fJamLbr;
    }

    public BigDecimal getByLembur() {
        return byLembur;
    }

    public void setByLembur(BigDecimal byLembur) {
        this.byLembur = byLembur;
    }

    public BigDecimal getUpahLembur() {
        return upahLembur;
    }

    public void setUpahLembur(BigDecimal upahLembur) {
        this.upahLembur = upahLembur;
    }

    public BigDecimal getTunjRmh() {
        return tunjRmh;
    }

    public void setTunjRmh(BigDecimal tunjRmh) {
        this.tunjRmh = tunjRmh;
    }

    public BigDecimal getTunjList() {
        return tunjList;
    }

    public void setTunjList(BigDecimal tunjList) {
        this.tunjList = tunjList;
    }

    public BigDecimal getTunjAir() {
        return tunjAir;
    }

    public void setTunjAir(BigDecimal tunjAir) {
        this.tunjAir = tunjAir;
    }

    public BigDecimal getTunjBbm() {
        return tunjBbm;
    }

    public void setTunjBbm(BigDecimal tunjBbm) {
        this.tunjBbm = tunjBbm;
    }

    public BigDecimal getTunjSos() {
        return tunjSos;
    }

    public void setTunjSos(BigDecimal tunjSos) {
        this.tunjSos = tunjSos;
    }

    public BigDecimal getRplRlab() {
        return rplRlab;
    }

    public void setRplRlab(BigDecimal rplRlab) {
        this.rplRlab = rplRlab;
    }

    public BigDecimal getTunjSiaga() {
        return tunjSiaga;
    }

    public void setTunjSiaga(BigDecimal tunjSiaga) {
        this.tunjSiaga = tunjSiaga;
    }

    public BigDecimal getTjPensiunPers() {
        return tjPensiunPers;
    }

    public void setTjPensiunPers(BigDecimal tjPensiunPers) {
        this.tjPensiunPers = tjPensiunPers;
    }

    public BigDecimal getTjBpjsTkPers() {
        return tjBpjsTkPers;
    }

    public void setTjBpjsTkPers(BigDecimal tjBpjsTkPers) {
        this.tjBpjsTkPers = tjBpjsTkPers;
    }

    public BigDecimal getTjBpjsKsPers() {
        return tjBpjsKsPers;
    }

    public void setTjBpjsKsPers(BigDecimal tjBpjsKsPers) {
        this.tjBpjsKsPers = tjBpjsKsPers;
    }

    public BigDecimal getPendRutin() {
        return pendRutin;
    }

    public void setPendRutin(BigDecimal pendRutin) {
        this.pendRutin = pendRutin;
    }

    public BigDecimal getPendTdkRutin() {
        return pendTdkRutin;
    }

    public void setPendTdkRutin(BigDecimal pendTdkRutin) {
        this.pendTdkRutin = pendTdkRutin;
    }

    public BigDecimal getPoPph() {
        return poPph;
    }

    public void setPoPph(BigDecimal poPph) {
        this.poPph = poPph;
    }

    public BigDecimal getPoYpks() {
        return poYpks;
    }

    public void setPoYpks(BigDecimal poYpks) {
        this.poYpks = poYpks;
    }

    public BigDecimal getIurPensiunPeg() {
        return iurPensiunPeg;
    }

    public void setIurPensiunPeg(BigDecimal iurPensiunPeg) {
        this.iurPensiunPeg = iurPensiunPeg;
    }

    public BigDecimal getIurPensiunPers() {
        return iurPensiunPers;
    }

    public void setIurPensiunPers(BigDecimal iurPensiunPers) {
        this.iurPensiunPers = iurPensiunPers;
    }

    public BigDecimal getIurBpjsTkPeg() {
        return iurBpjsTkPeg;
    }

    public void setIurBpjsTkPeg(BigDecimal iurBpjsTkPeg) {
        this.iurBpjsTkPeg = iurBpjsTkPeg;
    }

    public BigDecimal getIurBpjsTkPers() {
        return iurBpjsTkPers;
    }

    public void setIurBpjsTkPers(BigDecimal iurBpjsTkPers) {
        this.iurBpjsTkPers = iurBpjsTkPers;
    }

    public BigDecimal getIurBpjsKsPeg() {
        return iurBpjsKsPeg;
    }

    public void setIurBpjsKsPeg(BigDecimal iurBpjsKsPeg) {
        this.iurBpjsKsPeg = iurBpjsKsPeg;
    }

    public BigDecimal getIurBpjsKsPers() {
        return iurBpjsKsPers;
    }

    public void setIurBpjsKsPers(BigDecimal iurBpjsKsPers) {
        this.iurBpjsKsPers = iurBpjsKsPers;
    }

    public BigDecimal getPoLain() {
        return poLain;
    }

    public void setPoLain(BigDecimal poLain) {
        this.poLain = poLain;
    }

    public String getPerkbayar() {
        return perkbayar;
    }

    public void setPerkbayar(String perkbayar) {
        this.perkbayar = perkbayar;
    }

    public String getKlomperk() {
        return klomperk;
    }

    public void setKlomperk(String klomperk) {
        this.klomperk = klomperk;
    }

    public String getKdpoli() {
        return kdpoli;
    }

    public void setKdpoli(String kdpoli) {
        this.kdpoli = kdpoli;
    }

    public String getBkodeb() {
        return bkodeb;
    }

    public void setBkodeb(String bkodeb) {
        this.bkodeb = bkodeb;
    }

    public String getPgol() {
        return pgol;
    }

    public void setPgol(String pgol) {
        this.pgol = pgol;
    }

    public String getPruang() {
        return pruang;
    }

    public void setPruang(String pruang) {
        this.pruang = pruang;
    }

    public String getPmasa() {
        return pmasa;
    }

    public void setPmasa(String pmasa) {
        this.pmasa = pmasa;
    }

    public String getNoskdapen() {
        return noskdapen;
    }

    public void setNoskdapen(String noskdapen) {
        this.noskdapen = noskdapen;
    }

    public String getNoskgaji() {
        return noskgaji;
    }

    public void setNoskgaji(String noskgaji) {
        this.noskgaji = noskgaji;
    }

    public String getNoidbio() {
        return noidbio;
    }

    public void setNoidbio(String noidbio) {
        this.noidbio = noidbio;
    }

    public String getPrypks() {
        return prypks;
    }

    public void setPrypks(String prypks) {
        this.prypks = prypks;
    }

    public String getPrdapenp() {
        return prdapenp;
    }

    public void setPrdapenp(String prdapenp) {
        this.prdapenp = prdapenp;
    }

    public String getPrdapeng() {
        return prdapeng;
    }

    public void setPrdapeng(String prdapeng) {
        this.prdapeng = prdapeng;
    }

    public String getPrbpjstp() {
        return prbpjstp;
    }

    public void setPrbpjstp(String prbpjstp) {
        this.prbpjstp = prbpjstp;
    }

    public String getPrbpjstg() {
        return prbpjstg;
    }

    public void setPrbpjstg(String prbpjstg) {
        this.prbpjstg = prbpjstg;
    }

    public String getPrbpjssp() {
        return prbpjssp;
    }

    public void setPrbpjssp(String prbpjssp) {
        this.prbpjssp = prbpjssp;
    }

    public String getPrbpjssg() {
        return prbpjssg;
    }

    public void setPrbpjssg(String prbpjssg) {
        this.prbpjssg = prbpjssg;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public Date getTglAwalLbr() {
        return tglAwalLbr;
    }

    public void setTglAwalLbr(Date tglAwalLbr) {
        this.tglAwalLbr = tglAwalLbr;
    }

    public Date getTglAkhirLbr() {
        return tglAkhirLbr;
    }

    public void setTglAkhirLbr(Date tglAkhirLbr) {
        this.tglAkhirLbr = tglAkhirLbr;
    }

    public String getNipLama() {
        return nipLama;
    }

    public void setNipLama(String nipLama) {
        this.nipLama = nipLama;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getTahunSkalaGaji() {
        return tahunSkalaGaji;
    }

    public void setTahunSkalaGaji(String tahunSkalaGaji) {
        this.tahunSkalaGaji = tahunSkalaGaji;
    }
}
