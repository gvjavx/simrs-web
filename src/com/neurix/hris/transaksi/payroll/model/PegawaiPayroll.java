/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.model;


import com.neurix.common.constant.CommonConstant;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.mappingpersengaji.model.MappingPersenGaji;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/*
* Created by ferdi, 01-12-2020, for rebuild payroll
*/
public class PegawaiPayroll extends BasePayroll {

    private String payrollId;
    private String payrollHeaderId;
    //biodata pegawai

    private String divisiId;
    private String divisiName;
    private String subDivisiId;
    private String subDivisiName;
    private String kelompokPosisiId;
    private String kelompokPosisiName;
    private String posisiId;
    private String posisiName;
    private String profesiId;
    private String profesiName;
    private String tipePegawai;
    private String tipePegawaiName;
    private String statusPegawai;
    private String statusPegawaiName;
    private String jenisPegawai;
    private String jenisPegawaiName;
    private String golonganId;
    private String golonganName;
    private int gradeLevel;
    private String stGradeLevel;
    private String masaKerjaGol;
    private int iMasaKerjaGol;
    private String golonganDapen;
    private String danaPensiun;
    private String danaPensiunName;
    private Date tanggalPraPensiun;
    private String stTanggalPraPensiun;
    private int jumlahAnakDitanggung;
    private String stJumlahAnakDitanggung;
    private String tahunSkalaGaji;

    private Date tanggalAwalLembur;
    private Date tanggalAkhirLembur;

    //gaji dan tunjangan
    private String gajiPensiun;
    private BigDecimal gajiPensiunNilai;

    //tunj PPH dari proses grossup
    private PayrollPph payrollPph; //untuk menampung perhitungan pph

    private String santunanKhusus;
    private BigDecimal santunanKhususNilai;
    private String tunjRumah;
    private BigDecimal tunjRumahNilai;
    private String tunjListrik;
    private BigDecimal tunjListrikNilai;
    private String tunjAir;
    private BigDecimal tunjAirNilai;
    private String tunjBbm;
    private BigDecimal tunjBbmNilai;
    private String tunjJabatan;
    private BigDecimal tunjJabatanNilai;
    private String tunjStruktural;
    private BigDecimal tunjStrukturalNilai;
    private String tunjFungsional;
    private BigDecimal tunjFungsionalNilai;
    private String tunjTambahan;
    private BigDecimal tunjTambahanNilai;
    private String totalRLAB;
    private BigDecimal totalRLABNilai;

    private String totalTunjPeralihan;
    private BigDecimal totalTunjPeralihanNilai;
    private String tunjPeralihanGapok;
    private BigDecimal tunjPeralihanGapokNilai;
    private String tunjPeralihanSankhus;
    private BigDecimal tunjPeralihanSankhusNilai;
    private String tunjPeralihanTunj;
    private BigDecimal tunjPeralihanTunjNilai;
    private String tunjKomunikasi;
    private BigDecimal tunjKomunikasiNilai;
    private String tunjPemondokan;
    private BigDecimal tunjPemondokanNilai;
    private String tunjLembur;
    private BigDecimal tunjLemburNilai;
    private String tunjSupervisi;
    private BigDecimal tunjSupervisiNilai;
    private String tunjLokal;
    private BigDecimal tunjLokalNilai;
    private String tunjSiaga;
    private BigDecimal tunjSiagaNilai;
    private String tunjProfesional;
    private BigDecimal tunjProfesionalNilai;
    private String incomeTidakTetap;
    private BigDecimal incomeTidakTetapNilai;

    //tunjangan - tunjangan
    private String tunjanganLain;
    private BigDecimal tunjanganLainNilai;
    private String tunjanganDapen;
    private BigDecimal tunjanganDapenNilai;
    private String tunjanganPph;
    private BigDecimal tunjanganPphNilai;
    private String tunjanganBpjsKs;
    private BigDecimal tunjanganBpjsKsNilai;
    private String tunjanganBpjsTk;
    private BigDecimal tunjanganBpjsTkNilai;
    private String tunjanganSosialLain;
    private BigDecimal tunjanganSosialLainNilai;
    private String pemondokan;
    private BigDecimal pemondokanNilai;
    private String komunikasi;
    private BigDecimal komunikasiNilai;

    //iuran Pensiun
    private String iuranDapenKary;
    private BigDecimal iuranDapenKaryNilai;
    private String iuranDapenPersh;
    private BigDecimal iuranDapenPershNilai;

    private String persenDapenPersh;
    private BigDecimal persenDapenPershNilai;

    private String persenDapenKary;
    private BigDecimal persenDapenKaryNilai;


    //iuran bpjs
    private String totalIuranBpjsTkKary;
    private BigDecimal totalIuranBpjsTkKaryNilai;
    private String iuranBpjsTkKary;
    private BigDecimal iuranBpjsTkKaryNilai;
    private String totalIuranBpjsTkPers;
    private BigDecimal totalIuranBpjsTkPersNilai;

    private String iuranBpjsKsKary;
    private BigDecimal iuranBpjsKsKaryNilai;
    private String iuranBpjsKsPers;
    private BigDecimal iuranBpjsKsPersNilai;

    private String dasarPerhitunganBpjsKs;
    private BigDecimal dasarPerhitunganBpjsKsNilai;
    private String dasarPerhitunganBpjsTk;
    private BigDecimal dasarPerhitunganBpjsTkNilai;
    private String jpkBpjsTkKary;
    private BigDecimal jpkBpjsTkKaryNilai;
    private String jpkBpjsTkPersh;
    private BigDecimal jpkBpjsTkPershNilai;
    private String jkkBpjsTkPersh;
    private BigDecimal jkkBpjsTkPershNilai;
    private String jhtBpjsTkPersh;
    private BigDecimal jhtBpjsTkPershNilai;
    private String jkmBpjsTkPersh;
    private BigDecimal jkmBpjsTkPershNilai;

    private BigDecimal umrNilai;
    private String umr;

    private String minBpjsKs;
    private BigDecimal minBpjsKsNilai;
    private String maxBpjsKs;
    private BigDecimal maxBpjsKsNilai;

    private String minBpjsTk;
    private BigDecimal minBpjsTkNilai;
    private String maxBpjsTk;
    private BigDecimal maxBpjsTkNilai;

    private String persenBpjsKsKary;
    private BigDecimal persenBpjsKsKaryNilai;
    private String persenBpjsKsPersh;
    private BigDecimal persenBpjsKsPershNilai;

    private String persenBpjsTkIuranKary;
    private BigDecimal persenBpjsTkIuranKaryNilai;
    private String persenBpjsTkJpkKary;
    private BigDecimal persenBpjsTkJpkKaryNilai;

    private String persenBpjsTkJhtPersh;
    private BigDecimal persenBpjsTkJhtPershNilai;
    private String persenBpjsTkJkkPersh;
    private BigDecimal persenBpjsTkJkkPershNilai;

    private String persenBpjsTkJkmPersh;
    private BigDecimal persenBpjsTkJkmPershNilai;
    private String persenBpjsTkJpkPersh;
    private BigDecimal persenBpjsTkJpkPershNilai;

    //potongan rincian C
    private String totalPotonganLain;
    private BigDecimal totalPotonganLainNilai;

    private String kopkar;
    private String iuranSp;
    private String iuranPiikb;
    private String bankBri;
    private String bankMandiri;
    private String infaq;
    private String PerkesDanObat;
    private String listrik;
    private String iuranProfesi;
    private String potonganLain;
    private BigDecimal kopkarNilai;
    private BigDecimal iuranSpNilai;
    private BigDecimal iuranPiikbNilai;
    private BigDecimal bankBriNilai;
    private BigDecimal bankMandiriNilai;
    private BigDecimal infaqNilai;
    private BigDecimal PerkesDanObatNilai;
    private BigDecimal listrikNilai;
    private BigDecimal iuranProfesiNilai;
    private BigDecimal potonganLainNilai;

    //biaya jabatan sebagai pengurang
    private String biayaJabatan;
    private BigDecimal biayaJabatanNilai;
//    private BigDecimal paramBiayaJabatanNilai;
    private BigDecimal persenBiayaJabatan;

    //pph untuk bulan 12
    private BigDecimal pphSeharusnyaNilai;
    private BigDecimal selisihPphNilai;
    private BigDecimal totalPphUntilNovNilai;
    private List<PayrollPph> listOfPphUntilNov;

    private String pphSeharusnya;
    private String selisihPph;
    private String totalPphUntilNov;

    private boolean kantorPusat =  false;
    private boolean keuanganKantorPusat =  false;
    private boolean cetakSatuan = false;
    private boolean adaCheckBox = false;
    private boolean sdm = false ;

    //set mapping persen gaji
    private List<MappingPersenGaji> listOfMappingPersenGaji;
    private String componentA;
    private String componentB;
    private String componentC;

    private BigDecimal componentANilai;
    private BigDecimal componentBNilai;
    private BigDecimal componentCNilai;
    private BigDecimal multifikatorNilai;
    private String multifikator;
    private String jumlahPegawai;

    //untuk posting jurnal
    private Timestamp approvalDate;
    private String stApprovalDate;
    private String approvalFlag;
    private String flagEdit;
    private String flagPrint;

    //RAKA-23MEI2021==>untuk koreksi aks
    private String flagKoreksi;
    private String noteKoreksi;

    public String getFlagKoreksi() {
        return flagKoreksi;
    }

    public void setFlagKoreksi(String flagKoreksi) {
        this.flagKoreksi = flagKoreksi;
    }

    public String getNoteKoreksi() {
        return noteKoreksi;
    }

    public void setNoteKoreksi(String noteKoreksi) {
        this.noteKoreksi = noteKoreksi;
    }

    @Override
    public void recalculateDasarBpjs() {

        BigDecimal perhitunganDasarBpjsKs = getGajiPokokNilai().add(santunanKhususNilai).add(tunjPeralihanGapokNilai).add(tunjPeralihanSankhusNilai);
        if (perhitunganDasarBpjsKs.compareTo(minBpjsKsNilai) < 0) { // perhitunganDasarBpjsKs < min bpjs ks maka dasar perhitungan bpjs ks = min bpjs ks

            dasarPerhitunganBpjsKsNilai = minBpjsKsNilai;

        } else if (perhitunganDasarBpjsKs.compareTo(maxBpjsKsNilai) > 0) { // perhitunganDasarBpjsKs > max bpjs ks maka dasar perhitungan bpjs ks = max bpjs ks

            dasarPerhitunganBpjsKsNilai = maxBpjsKsNilai;

        } else {

            dasarPerhitunganBpjsKsNilai = perhitunganDasarBpjsKs;

        }

        setDasarPerhitunganBpjsKs(CommonUtil.numbericFormat(getDasarPerhitunganBpjsKsNilai(),"###,###"));

        BigDecimal perhitunganDasarBpjsTk = getGajiPokokNilai().add(santunanKhususNilai).add(tunjPeralihanGapokNilai).add(tunjPeralihanSankhusNilai);
        if (perhitunganDasarBpjsTk.compareTo(minBpjsTkNilai) < 0) { // perhitunganDasarBpjsTk < min bpjs tk maka dasar perhitungan bpjs tk = min bpjs tk

            dasarPerhitunganBpjsTkNilai = minBpjsTkNilai;

        } else if (perhitunganDasarBpjsTk.compareTo(maxBpjsTkNilai) > 0) { // perhitunganDasarBpjsTk > max bpjs tk maka dasar perhitungan bpjs tk = max bpjs tk

            dasarPerhitunganBpjsTkNilai = maxBpjsTkNilai;

        } else {

            dasarPerhitunganBpjsTkNilai = perhitunganDasarBpjsTk;

        }

        setDasarPerhitunganBpjsTk(CommonUtil.numbericFormat(getDasarPerhitunganBpjsTkNilai(),"###,###"));

        if (getNoBpjsKs()!=null && !"".equalsIgnoreCase(getNoBpjsKs())) {

            iuranBpjsKsKaryNilai = (dasarPerhitunganBpjsKsNilai.multiply(persenBpjsKsKaryNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            iuranBpjsKsPersNilai = (dasarPerhitunganBpjsKsNilai.multiply(persenBpjsKsPershNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;

        } else {

            iuranBpjsKsKaryNilai = new BigDecimal(0);
            iuranBpjsKsPersNilai = new BigDecimal(0);
        }

        if (getNoBpjsTk()!=null && !"".equalsIgnoreCase(getNoBpjsTk())) {

            iuranBpjsTkKaryNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkIuranKaryNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            jpkBpjsTkKaryNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkJpkKaryNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            jkkBpjsTkPershNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkJkkPershNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            jhtBpjsTkPershNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkJhtPershNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            jkmBpjsTkPershNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkJkmPershNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;
            jpkBpjsTkPershNilai = (dasarPerhitunganBpjsTkNilai.multiply(persenBpjsTkJpkPershNilai).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP);;

        } else {

            iuranBpjsTkKaryNilai = new BigDecimal(0);
            jpkBpjsTkKaryNilai = new BigDecimal(0);
            jkkBpjsTkPershNilai = new BigDecimal(0);
            jhtBpjsTkPershNilai = new BigDecimal(0);
            jkmBpjsTkPershNilai = new BigDecimal(0);
            jpkBpjsTkPershNilai = new BigDecimal(0);
        }

        setIuranBpjsKsKary(CommonUtil.numbericFormat(getIuranBpjsKsKaryNilai(),"###,###"));
        setIuranBpjsKsPers(CommonUtil.numbericFormat(getIuranBpjsKsPersNilai(),"###,###"));
        setIuranBpjsTkKary(CommonUtil.numbericFormat(getIuranBpjsTkKaryNilai(),"###,###"));
        setJpkBpjsTkKary(CommonUtil.numbericFormat(getJpkBpjsTkKaryNilai(),"###,###"));
        setJkkBpjsTkPersh(CommonUtil.numbericFormat(getJkkBpjsTkPershNilai(),"###,###"));
        setJhtBpjsTkPersh(CommonUtil.numbericFormat(getJhtBpjsTkPershNilai(),"###,###"));
        setJkmBpjsTkPersh(CommonUtil.numbericFormat(getJkmBpjsTkPershNilai(),"###,###"));
        setJpkBpjsTkPersh(CommonUtil.numbericFormat(getJpkBpjsTkPershNilai(),"###,###"));
    }

    @Override
    public void calculateBasedMultifikator() {

        if (multifikatorNilai.compareTo(new BigDecimal(1)) < 0) { //jika multikator < 1

            if (CommonConstant.CODE_PAYROLL.equalsIgnoreCase(getTipePayroll())) { // untuk pendapatan rutin (payroll)

                tunjRumahNilai = (multifikatorNilai.multiply(tunjRumahNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjListrikNilai = (multifikatorNilai.multiply(tunjListrikNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjAirNilai = (multifikatorNilai.multiply(tunjAirNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjBbmNilai = (multifikatorNilai.multiply(tunjBbmNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanGapokNilai = (multifikatorNilai.multiply(tunjPeralihanGapokNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanSankhusNilai = (multifikatorNilai.multiply(tunjPeralihanSankhusNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanTunjNilai = (multifikatorNilai.multiply(tunjPeralihanTunjNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;

                santunanKhususNilai = (multifikatorNilai.multiply(santunanKhususNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjJabatanNilai = (multifikatorNilai.multiply(tunjJabatanNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjStrukturalNilai = (multifikatorNilai.multiply(tunjStrukturalNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjFungsionalNilai = (multifikatorNilai.multiply(tunjFungsionalNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjTambahanNilai = (multifikatorNilai.multiply(tunjTambahanNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;

                tunjSupervisiNilai = (multifikatorNilai.multiply(tunjSupervisiNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjSiagaNilai = (multifikatorNilai.multiply(tunjSiagaNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjLokalNilai = (multifikatorNilai.multiply(tunjLokalNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;

                tunjPemondokanNilai = (multifikatorNilai.multiply(tunjPemondokanNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjKomunikasiNilai = (multifikatorNilai.multiply(tunjKomunikasiNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;

                BigDecimal gapok = (multifikatorNilai.multiply(getGajiPokokNilai())).setScale(0, BigDecimal.ROUND_HALF_UP);;
                setGajiPokokNilai(gapok);
                setGajiPokok(CommonUtil.numbericFormat(gapok,"###,###"));

            } else { // untuk pendapatan tidak rutin (THR/Insentif/Jasop/Cuti Panjang/ Cuti Tahunan)

                tunjRumahNilai = (multifikatorNilai.multiply(tunjRumahNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjListrikNilai = (multifikatorNilai.multiply(tunjListrikNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjAirNilai = (multifikatorNilai.multiply(tunjAirNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjBbmNilai = (multifikatorNilai.multiply(tunjBbmNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanGapokNilai = (multifikatorNilai.multiply(tunjPeralihanGapokNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanSankhusNilai = (multifikatorNilai.multiply(tunjPeralihanSankhusNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjPeralihanTunjNilai = (multifikatorNilai.multiply(tunjPeralihanTunjNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                santunanKhususNilai = (multifikatorNilai.multiply(santunanKhususNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjJabatanNilai = (multifikatorNilai.multiply(tunjJabatanNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjStrukturalNilai = (multifikatorNilai.multiply(tunjStrukturalNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjFungsionalNilai = (multifikatorNilai.multiply(tunjFungsionalNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjTambahanNilai = (multifikatorNilai.multiply(tunjTambahanNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;
                tunjKomunikasiNilai = (multifikatorNilai.multiply(tunjKomunikasiNilai)).setScale(0, BigDecimal.ROUND_HALF_UP);;

                BigDecimal gapok = (multifikatorNilai.multiply(getGajiPokokNilai())).setScale(0, BigDecimal.ROUND_HALF_UP);;
                setGajiPokokNilai(gapok);
                setGajiPokok(CommonUtil.numbericFormat(gapok,"###,###"));

            }

        }
    }

    public void calculatePrePayroll() { //to calculate THP, component A,B,C

        //perhitungan gaji PT. NMU
        //thp = gaji pokok + sankhus + tunj. jabatan + tunj. struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + RLAB (tunj. rumah + listrik + air + bbm)
        //component A (penerimaan) = gaji pokok + sankhus + tunj. jabatan + tunj. struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj.lain (tunj.supervisi + tunj. siaga + tunj. lokasi) + tunj. tambahan + tunj.pemondakan + tunj. komunikasi + tunj. lembur
        //component B (tunjangan) = tunj. rumah + listrik + air + bbm + tunj. dapen (iuran dapen persh) + tunj. bpjs ks (iuran bpjs ks peg + iuran bpjs ks persh) + tunj. bpjs tk (iuran bpjs tk persh) + tunj.pph
        //component C (potongan) = iuran dapen peg + iuran dapen persh + iuran bpjs tk peg (iuran_kry + jpk_kry)+ iuran bpjs tk persh (jkk_pers + jht_pers + jkm_pers + jpk_pers) + iuran bpjs ks peg + iuran bpjs ks persh + potongan lainnya ( kopkar, bank, dll)
        //RLAB = tunj. rumah + listrik + air + bbm

        BigDecimal RLAB = new BigDecimal(0);
        RLAB = RLAB.add(tunjRumahNilai)
                .add(tunjListrikNilai)
                .add(tunjAirNilai)
                .add(tunjBbmNilai);

        BigDecimal tunjPeralihan = new BigDecimal(0);
        tunjPeralihan = tunjPeralihan.add(tunjPeralihanGapokNilai)
                .add(tunjPeralihanSankhusNilai)
                .add(tunjPeralihanTunjNilai);

        BigDecimal thp = new BigDecimal(0);
        thp = thp.add(getGajiPokokNilai())
                .add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(tunjPeralihan)
                .add(tunjTambahanNilai)
                .add(RLAB);

        BigDecimal tunjLain = new BigDecimal(0);
        tunjLain = tunjLain.add(tunjSupervisiNilai)
                .add(tunjSiagaNilai)
                .add(tunjLokalNilai);

        BigDecimal componentA = new BigDecimal(0);
        componentA = componentA.add(getGajiPokokNilai())
                .add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(tunjPeralihan)
                .add(tunjLain)
                .add(tunjTambahanNilai)
                .add(tunjPemondokanNilai)
                .add(tunjKomunikasiNilai)
                .add(tunjLemburNilai);

        BigDecimal tunjBpjsKs = new BigDecimal(0);
        tunjBpjsKs = tunjBpjsKs.add(iuranBpjsKsKaryNilai)
                .add(iuranBpjsKsPersNilai);

        BigDecimal tunjBpjsTk = new BigDecimal(0);
        tunjBpjsTk = tunjBpjsTk.add(jpkBpjsTkPershNilai)
                .add(jkkBpjsTkPershNilai)
                .add(jhtBpjsTkPershNilai)
                .add(jkmBpjsTkPershNilai);

        BigDecimal componentB = new BigDecimal(0);
        componentB = componentB.add(tunjRumahNilai)
                .add(tunjListrikNilai)
                .add(tunjAirNilai)
                .add(tunjBbmNilai)
                .add(iuranDapenPershNilai)
                .add(tunjBpjsKs)
                .add(tunjBpjsTk);

        BigDecimal iuranBpjsTkKaryawan = new BigDecimal(0);
        iuranBpjsTkKaryawan = iuranBpjsTkKaryawan.add(iuranBpjsTkKaryNilai)
                .add(jpkBpjsTkKaryNilai);

        BigDecimal totalPotonganLainnya = new BigDecimal(0);
        totalPotonganLainnya = totalPotonganLainnya.add(kopkarNilai)
                .add(iuranSpNilai)
                .add(iuranPiikbNilai)
                .add(bankBriNilai)
                .add(bankMandiriNilai)
                .add(infaqNilai)
                .add(PerkesDanObatNilai)
                .add(listrikNilai)
                .add(iuranProfesiNilai)
                .add(potonganLainNilai);

        BigDecimal componentC = new BigDecimal(0);
        componentC = componentC.add(iuranDapenKaryNilai)
                .add(iuranDapenPershNilai)
                .add(iuranBpjsTkKaryawan)
                .add(tunjBpjsTk)
                .add(iuranBpjsKsKaryNilai)
                .add(iuranBpjsKsPersNilai)
                .add(totalPotonganLainnya);

        setThpNilai(thp);
        setThp(CommonUtil.numbericFormat(thp,"###,###"));

        setComponentANilai(componentA);
        setComponentA(CommonUtil.numbericFormat(componentA,"###,###"));

        setComponentBNilai(componentB);
        setComponentB(CommonUtil.numbericFormat(componentB,"###,###"));

        setComponentCNilai(componentC);
        setComponentC(CommonUtil.numbericFormat(componentC,"###,###"));

        setTotalRLABNilai(RLAB);
        setTotalRLAB(CommonUtil.numbericFormat(RLAB,"###,###"));

        setTotalTunjPeralihanNilai(tunjPeralihan);
        setTotalTunjPeralihan(CommonUtil.numbericFormat(tunjPeralihan,"###,###"));

        setTunjanganLainNilai(tunjLain);
        setTunjanganLain(CommonUtil.numbericFormat(tunjLain,"###,###"));

        setTunjanganBpjsKsNilai(tunjBpjsKs);
        setTunjanganBpjsKs(CommonUtil.numbericFormat(tunjBpjsKs,"###,###"));

        setTunjanganBpjsTkNilai(tunjBpjsTk);
        setTunjanganBpjsTk(CommonUtil.numbericFormat(tunjBpjsTk,"###,###"));

        setTotalIuranBpjsTkKaryNilai(iuranBpjsTkKaryawan);
        setTotalIuranBpjsTkKary(CommonUtil.numbericFormat(iuranBpjsTkKaryawan,"###,###"));

        setTotalIuranBpjsTkPersNilai(tunjBpjsTk);
        setTotalIuranBpjsTkPers(CommonUtil.numbericFormat(tunjBpjsTk,"###,###"));

        setTunjanganDapenNilai(iuranDapenPershNilai);
        setTunjanganDapen(CommonUtil.numbericFormat(iuranDapenPershNilai,"###,###"));

        setTotalPotonganLainNilai(totalPotonganLainnya);
        setTotalPotonganLain(CommonUtil.numbericFormat(totalPotonganLainnya,"###,###"));

    }

    public void calculatePostPayroll() { //to calculate component B + tunj. pph , gross income and net income

        // gaji kotor = component A + component B
        // gaji bersih = component A + component B - component C - pph gaji

        BigDecimal sumOfComponentA = getComponentANilai();
        BigDecimal tunjPPHGaji = getTunjanganPphNilai();
        BigDecimal pphGaji = getPphGajiNilai();

        sumOfComponentA = sumOfComponentA.add(tunjPPHGaji);

        BigDecimal grossIncome = new BigDecimal(0);
        grossIncome = grossIncome.add(sumOfComponentA)
                .add(componentBNilai);

        setGajiKotorNilai(grossIncome);
        setGajiKotor(CommonUtil.numbericFormat(grossIncome,"###,###"));

        BigDecimal netIncome = new BigDecimal(0);
        netIncome = netIncome.add(sumOfComponentA)
                    .add(componentBNilai)
                    .subtract(componentCNilai)
                    .subtract(pphGaji);

        setGajiBersihNilai(netIncome);
        setGajiBersih(CommonUtil.numbericFormat(netIncome,"###,###"));

    }

    public void calculatePPH() { //calculate tax and grossup tax

        BigDecimal pphGaji = new BigDecimal(0);
        BigDecimal tunjPph = new BigDecimal(0);

        BigDecimal bruto = new BigDecimal(0);
        BigDecimal netto = new BigDecimal(0);

        BigDecimal pkpSetahun = new BigDecimal(0);
        BigDecimal pphTerhutangSetahun = new BigDecimal(0);
        Integer selisih;
        BigDecimal ptkp = getPtkpPegawaiNilai();
        String npwp = getNpwpPegawai();

        PayrollPph payrollPph = new PayrollPph();

        //tunj yang diperhitungkan pph = sankhus + tunj.jabatan + tunj.struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + tunj.lain (tunj.supervisi + tunj. siaga + tunj. lokasi) +
        // RLAB (tunj. rumah + listrik + air + bbm)+ tunj.pemondakan + tunj. komunikasi + tunj. lembur + tunj. dapen (iuran dapen persh) +
        // tunj. bpjs ks (iuran bpjs ks peg + iuran bpjs ks persh) + tunj. bpjs tk (iuran bpjs tk persh)

        BigDecimal tunjDalamPPH = new BigDecimal(0);
        tunjDalamPPH = tunjDalamPPH.add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(totalTunjPeralihanNilai)
                .add(tunjTambahanNilai)
                .add(tunjanganLainNilai)
                .add(totalRLABNilai)
                .add(tunjPemondokanNilai)
                .add(tunjKomunikasiNilai)
                .add(tunjLemburNilai)
                .add(iuranDapenPershNilai)
                .add(tunjanganBpjsKsNilai)
                .add(tunjanganBpjsTkNilai);

        //pengurang dalam pph = biaya jabatan dan iuran pegawai

        BigDecimal biayaJabatan = new BigDecimal(0);
        biayaJabatan = biayaJabatan.add(biayaJabatanNilai);

        //iuran pegawai = biaya yang dibayarkan pegawai (iuran pensiun dan bpjs tk)
        BigDecimal iuranPegawai = new BigDecimal(0);
        iuranPegawai = iuranPegawai.add(totalIuranBpjsTkKaryNilai)
                .add(iuranBpjsKsKaryNilai) //RAKA-mencoba menambahkan
                .add(iuranDapenKaryNilai);

        //menghitung bruto = gaji pokok + tunj.yang diperhitungkan pph + tunj.pph
        bruto = bruto.add(getGajiPokokNilai()).add(tunjDalamPPH);

        boolean isRoundUpPph = true;

        do {

            bruto = bruto.add(tunjPph);

            //jika persen_biaya_jabatan(5%) x bruto lebih kecil dari param biaya jabatan, maka biaya jabatan = 5% x bruto, sebaliknya diambil param biaya jabatan
            if ((CommonUtil.percentage(bruto, persenBiayaJabatan)).compareTo(biayaJabatanNilai)<1){
                biayaJabatan = CommonUtil.percentage(bruto, persenBiayaJabatan);
            }

            //menghitung netto = bruto - biaya jabatan - iuran pegawai
            netto = bruto.subtract(biayaJabatan).subtract(iuranPegawai);

            //menghitung pkp untuk setahun harus dikeluarkan pendapatan tidak tetap baru di kalikan 12 bulan
            netto = (netto).multiply(BigDecimal.valueOf(12));
            pkpSetahun = netto.subtract(ptkp);
//            pkpSetahun = pkpSetahun.setScale(3, BigDecimal.ROUND_HALF_UP);
            pkpSetahun = pkpSetahun.setScale(0, BigDecimal.ROUND_HALF_UP);
//            pkpSetahun = pkpSetahun.setScale(0, RoundingMode.UP);
            pkpSetahun = pkpSetahun.round(new MathContext(pkpSetahun.precision() - 3,RoundingMode.UP));

            if (pkpSetahun.compareTo(new BigDecimal(0)) < 0) {

                isRoundUpPph = false;

            } else {

                //menghitung pph setahun
                pphTerhutangSetahun = calculateTaxInOneYear(pkpSetahun,npwp);

                //pph setahun / 12 untuk mendapat pph perbulan
                pphGaji = pphTerhutangSetahun.divide(BigDecimal.valueOf(12),2, BigDecimal.ROUND_HALF_UP);

                //perhitungan selisih antara tunjPph dan pph gaji, jika selisih = 0 looping berhenti
                selisih = pphGaji.subtract(tunjPph).intValue();

                if (selisih > 0) {
                    bruto = bruto.subtract(tunjPph);
                } else {
                    isRoundUpPph = false;
                }

            }

            tunjPph = pphGaji;

        } while (isRoundUpPph);
//    } while (selisih > 0);

        //set pph yang didapat ke model pph payroll
        pphGaji = pphGaji.setScale(0, RoundingMode.HALF_UP); //Also tried with RoundingMode.UP
        BigDecimal reduce = biayaJabatan.add(iuranPegawai);

        payrollPph.setPkp(CommonUtil.numbericFormat(pkpSetahun,"###,###"));
        payrollPph.setPkpNilai(pkpSetahun);

        payrollPph.setPphGaji(CommonUtil.numbericFormat(pphGaji,"###,###"));
        payrollPph.setPphGajiNilai(pphGaji);

        tunjPph = tunjPph.setScale(0, RoundingMode.HALF_UP); //Also tried with RoundingMode.UP
        payrollPph.setTunjanganPphBulan(CommonUtil.numbericFormat(tunjPph,"###,###"));
        payrollPph.setTunjanganPphNilaiBulan(tunjPph);

        //set tunjangan pph untuk PT. NMU pph gaji ditanggung perusahaan
        setTunjanganPphNilai(tunjPph);
        setTunjanganPph(CommonUtil.numbericFormat(tunjPph,"###,###"));

        setPphGajiNilai(tunjPph);
        setPphGaji(CommonUtil.numbericFormat(tunjPph,"###,###"));

        payrollPph.setBruto(CommonUtil.numbericFormat(bruto,"###,###"));
        payrollPph.setBrutoNilai(bruto);
        payrollPph.setReduce(CommonUtil.numbericFormat(reduce,"###,###"));
        payrollPph.setReduceNilai(reduce);
        payrollPph.setNip(getNip());
        payrollPph.setBulan(getPeriodePayroll());
        payrollPph.setTahun(getTahunPayroll());
        payrollPph.setPtkp(CommonUtil.numbericFormat(ptkp,"###,###"));
        payrollPph.setPtkpNilai(ptkp);
        payrollPph.setNetto(CommonUtil.numbericFormat(netto,"###,###"));
        payrollPph.setNettoNilai(netto);
        payrollPph.setBiayaJabatan(CommonUtil.numbericFormat(biayaJabatan,"###,###"));
        payrollPph.setBiayaJabatanNilai(biayaJabatan);
        payrollPph.setHutangPph(CommonUtil.numbericFormat(pphTerhutangSetahun,"###,###"));
        payrollPph.setHutangPphNilai(pphTerhutangSetahun);

        payrollPph.setGaji(CommonUtil.numbericFormat(getGajiPokokNilai(),"###,###"));
        payrollPph.setGajiNilai(getGajiPokokNilai());
        payrollPph.setSankhus(CommonUtil.numbericFormat(santunanKhususNilai,"###,###"));
        payrollPph.setSankhusNilai(santunanKhususNilai);
        payrollPph.setTunjanganJabatanStruktural(CommonUtil.numbericFormat(tunjJabatanNilai,"###,###"));
        payrollPph.setTunjanganJabatanStrukturalNilai(tunjJabatanNilai);
        payrollPph.setTunjanganStruktural(CommonUtil.numbericFormat(tunjStrukturalNilai,"###,###"));
        payrollPph.setTunjanganStrukturalNilai(tunjStrukturalNilai);
        payrollPph.setTunjanganStrategis(CommonUtil.numbericFormat(tunjFungsionalNilai,"###,###"));
        payrollPph.setTunjanganStrategisNilai(tunjFungsionalNilai);
        payrollPph.setTunjanganPeralihan(CommonUtil.numbericFormat(totalTunjPeralihanNilai,"###,###"));
        payrollPph.setTunjanganPeralihanNilai(totalTunjPeralihanNilai);
        payrollPph.setTotalTunjanganLain(CommonUtil.numbericFormat(tunjanganLainNilai,"###,###"));
        payrollPph.setTotalTunjanganLainNilai(tunjanganLainNilai);
        payrollPph.setTunjanganTambahan(CommonUtil.numbericFormat(tunjTambahanNilai,"###,###"));
        payrollPph.setTunjanganTambahanNilai(tunjTambahanNilai);
        payrollPph.setPemondokan(CommonUtil.numbericFormat(tunjPemondokanNilai,"###,###"));
        payrollPph.setPemondokanNilai(tunjPemondokanNilai);
        payrollPph.setKomunikasi(CommonUtil.numbericFormat(tunjKomunikasiNilai,"###,###"));
        payrollPph.setKomunikasiNilai(tunjKomunikasiNilai);
        payrollPph.setTotalRlab(CommonUtil.numbericFormat(totalRLABNilai,"###,###"));
        payrollPph.setTotalRlabNilai(totalRLABNilai);
        payrollPph.setIuranPegawai(CommonUtil.numbericFormat(iuranPegawai,"###,###"));
        payrollPph.setIuranPegawaiNilai(iuranPegawai);
        payrollPph.setTunjanganLembur(CommonUtil.numbericFormat(tunjLemburNilai,"###,###"));
        payrollPph.setTunjanganLemburNilai(tunjLemburNilai);
        payrollPph.setTunjanganPensiun(CommonUtil.numbericFormat(iuranDapenPershNilai,"###,###"));
        payrollPph.setTunjanganPensiunNilai(iuranDapenPershNilai);
        payrollPph.setBpjsTk(CommonUtil.numbericFormat(tunjanganBpjsTkNilai,"###,###"));
        payrollPph.setBpjsTkNilai(tunjanganBpjsTkNilai);
        payrollPph.setBpjsKs(CommonUtil.numbericFormat(tunjanganBpjsKsNilai,"###,###"));
        payrollPph.setBpjsKsNilai(tunjanganBpjsKsNilai);

        setPayrollPph(payrollPph);
    }

    @Override
    public void calculatePPHAnnual() { //calculate tax and grossup tax for end of year (month 12)

        //total pend.bruto setahun = (bruto gaji bulan 1-11) + (tunj pph bulan 1 - 11) + total tunj pph pendapatan tidak tetap + total pendapatan tidak tetap + (gaji bulan 12 + tunj yang diperhitungkan dalam bulan 12)
        //total iuran setahun = iuran bulan 1-11 + iuran bulan 12
        //total pph21 setahun = pph21 bulan 1-11 dan pph21 bulan 12 + total pph21 pendapatan tidak tetap


        List<PayrollPph> filterPayrollRutinPphUntilNOv = listOfPphUntilNov.stream().filter(
                p -> p.getTipePayroll().equalsIgnoreCase(CommonConstant.CODE_PAYROLL)
        ).collect(Collectors.toList());

        BigDecimal totalBrutoPayrollRutinUntilNov = filterPayrollRutinPphUntilNOv.stream().map(p -> p.getBrutoNilai()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTunjPphPayrollRutinUntilNov = filterPayrollRutinPphUntilNOv.stream().map(p -> p.getTunjanganPphNilaiBulan()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalIuranPegawaiPayrollRutinUntilNov = filterPayrollRutinPphUntilNOv.stream().map(p -> p.getIuranPegawaiNilai()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPph21PayrollRutinUntilNov = filterPayrollRutinPphUntilNOv.stream().map(p -> p.getPphGajiNilai()).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<PayrollPph> filterPayrollNonRutinPphUntilNOv = listOfPphUntilNov.stream().filter(
                p -> !p.getTipePayroll().equalsIgnoreCase(CommonConstant.CODE_PAYROLL)
        ).collect(Collectors.toList());

        BigDecimal totalIncomeNonRutinUntilNov = filterPayrollNonRutinPphUntilNOv.stream().map(p -> p.getIncomeTidakTetapNilai()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalTunjPphPayrollNonRutinUntilNov = filterPayrollNonRutinPphUntilNOv.stream().map(p -> p.getTunjanganPphNilaiBulan()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPph21PayrollNonRutinUntilNov = filterPayrollNonRutinPphUntilNOv.stream().map(p -> p.getPphGajiNilai()).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal pphGaji = new BigDecimal(0);
        BigDecimal tunjPph = new BigDecimal(0);

        BigDecimal pkpSetahun = new BigDecimal(0);
        BigDecimal pphTerhutangSetahun = new BigDecimal(0);
        Integer selisih;
        BigDecimal ptkp = getPtkpPegawaiNilai();
        String npwp = getNpwpPegawai();

        PayrollPph payrollPph = new PayrollPph();

        //tunj yang diperhitungkan pph = sankhus + tunj.jabatan + tunj.struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + tunj.lain (tunj.supervisi + tunj. siaga + tunj. lokasi) +
        // RLAB (tunj. rumah + listrik + air + bbm)+ tunj.pemondakan + tunj. komunikasi + tunj. lembur + tunj. dapen (iuran dapen persh) +
        // tunj. bpjs ks (iuran bpjs ks peg + iuran bpjs ks persh) + tunj. bpjs tk (iuran bpjs tk persh)

        BigDecimal tunjDalamPPH = new BigDecimal(0);
        tunjDalamPPH = tunjDalamPPH.add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(totalTunjPeralihanNilai)
                .add(tunjTambahanNilai)
                .add(tunjanganLainNilai)
                .add(totalRLABNilai)
                .add(tunjPemondokanNilai)
                .add(tunjKomunikasiNilai)
                .add(tunjLemburNilai)
                .add(iuranDapenPershNilai)
                .add(tunjanganBpjsKsNilai)
                .add(tunjanganBpjsTkNilai);

        //pengurang dalam pph = biaya jabatan dan iuran pegawai

        BigDecimal biayaJabatan = new BigDecimal(0);
        biayaJabatan = biayaJabatan.add(biayaJabatanNilai);

        //iuran pegawai = biaya yang dibayarkan pegawai (iuran pensiun dan bpjs tk)
        BigDecimal iuranPegawai = new BigDecimal(0);
        iuranPegawai = iuranPegawai.add(totalIuranBpjsTkKaryNilai)
                .add(iuranDapenKaryNilai);

        BigDecimal brutoSetahun = new BigDecimal(0);
        BigDecimal nettoSetahun = new BigDecimal(0);
        BigDecimal iuranPegawaiSetahun = new BigDecimal(0);

        iuranPegawaiSetahun = iuranPegawaiSetahun.add(totalIuranPegawaiPayrollRutinUntilNov).add(iuranPegawai);

        brutoSetahun = brutoSetahun.add(totalBrutoPayrollRutinUntilNov).add(totalTunjPphPayrollRutinUntilNov).add(totalIncomeNonRutinUntilNov).add(totalTunjPphPayrollNonRutinUntilNov).add(getGajiPokokNilai()).add(tunjDalamPPH);
        boolean isRoundUpPph = true;

        do {
            //menghitung bruto setahun = bruto setahun + tunj.pph
            brutoSetahun = brutoSetahun.add(tunjPph);

            //jika persen_biaya_jabatan(5%) x bruto lebih kecil dari param biaya jabatan, maka biaya jabatan = 5% x bruto, sebaliknya diambil param biaya jabatan
            if ((CommonUtil.percentage(brutoSetahun, persenBiayaJabatan)).compareTo(biayaJabatanNilai.multiply(new BigDecimal(12)))<1){
                biayaJabatan = CommonUtil.percentage(brutoSetahun, persenBiayaJabatan);
            } else {
                biayaJabatan = biayaJabatan.multiply(new BigDecimal(12));
            }

            //menghitung nettoSetahun = brutoSetahun - biaya jabatan setahun - iuran pegawai setahun
            nettoSetahun = brutoSetahun.subtract(biayaJabatan).subtract(iuranPegawaiSetahun);

            pkpSetahun = nettoSetahun.subtract(ptkp);
//            pkpSetahun = pkpSetahun.setScale(3, BigDecimal.ROUND_HALF_UP);

            pkpSetahun = pkpSetahun.setScale(0, BigDecimal.ROUND_HALF_UP);
            pkpSetahun = pkpSetahun.round(new MathContext(pkpSetahun.precision() - 3,RoundingMode.UP));

            if (pkpSetahun.compareTo(new BigDecimal(0)) < 0) {

                isRoundUpPph = false;

            } else {

                //menghitung pph setahun
                pphTerhutangSetahun = calculateTaxInOneYear(pkpSetahun,npwp);

                //pph21 bulan 12 = pph setahun - pph 21 s.d bln 11 - total pph 21 income tidak tetap s.d bulan 11
                pphGaji = pphTerhutangSetahun.subtract(totalPph21PayrollRutinUntilNov).subtract(totalPph21PayrollNonRutinUntilNov);

                //perhitungan selisih antara tunjPph dan pph gaji, jika selisih = 0 looping berhenti
                selisih = pphGaji.subtract(tunjPph).intValue();

                if (selisih > 0) {
                    brutoSetahun = brutoSetahun.subtract(tunjPph);
                } else {
                    isRoundUpPph = false;
                }

            }

            tunjPph = pphGaji;

        } while (isRoundUpPph);
//        } while (selisih != 0);

        //set pph yang didapat ke model pph payroll
        pphGaji = pphGaji.setScale(0, RoundingMode.HALF_UP); //Also tried with RoundingMode.UP
        BigDecimal reduce = biayaJabatan.add(iuranPegawaiSetahun);

        payrollPph.setPkp(CommonUtil.numbericFormat(pkpSetahun,"###,###"));
        payrollPph.setPkpNilai(pkpSetahun);

        payrollPph.setPphGaji(CommonUtil.numbericFormat(pphGaji,"###,###"));
        payrollPph.setPphGajiNilai(pphGaji);

        payrollPph.setTunjanganPphBulan(CommonUtil.numbericFormat(tunjPph,"###,###"));
        payrollPph.setTunjanganPphNilaiBulan(tunjPph);

        //set tunjangan pph untuk PT. NMU pph gaji ditanggung perusahaan
        setTunjanganPphNilai(tunjPph);
        setTunjanganPph(CommonUtil.numbericFormat(tunjPph,"###,###"));

        setPphGajiNilai(tunjPph);
        setPphGaji(CommonUtil.numbericFormat(tunjPph,"###,###"));

        //set pph seharusnya saat bulan 12
        setPphSeharusnyaNilai(tunjPph);
        setPphSeharusnya(CommonUtil.numbericFormat(tunjPph,"###,###"));

        BigDecimal totalPph21UntilNov = totalTunjPphPayrollRutinUntilNov.add(totalTunjPphPayrollNonRutinUntilNov);
        setTotalPphUntilNovNilai(totalPph21UntilNov);
        setTotalPphUntilNov(CommonUtil.numbericFormat(totalPph21UntilNov,"###,###"));

        payrollPph.setBruto(CommonUtil.numbericFormat(brutoSetahun,"###,###"));
        payrollPph.setBrutoNilai(brutoSetahun);
        payrollPph.setReduce(CommonUtil.numbericFormat(reduce,"###,###"));
        payrollPph.setReduceNilai(reduce);
        payrollPph.setNip(getNip());
        payrollPph.setBulan(getPeriodePayroll());
        payrollPph.setTahun(getTahunPayroll());
        payrollPph.setPtkp(CommonUtil.numbericFormat(ptkp,"###,###"));
        payrollPph.setPtkpNilai(ptkp);
        payrollPph.setNetto(CommonUtil.numbericFormat(nettoSetahun,"###,###"));
        payrollPph.setNettoNilai(nettoSetahun);
        payrollPph.setBiayaJabatan(CommonUtil.numbericFormat(biayaJabatan,"###,###"));
        payrollPph.setBiayaJabatanNilai(biayaJabatan);
        payrollPph.setHutangPph(CommonUtil.numbericFormat(pphTerhutangSetahun,"###,###"));
        payrollPph.setHutangPphNilai(pphTerhutangSetahun);

        payrollPph.setGaji(CommonUtil.numbericFormat(getGajiPokokNilai(),"###,###"));
        payrollPph.setGajiNilai(getGajiPokokNilai());
        payrollPph.setSankhus(CommonUtil.numbericFormat(santunanKhususNilai,"###,###"));
        payrollPph.setSankhusNilai(santunanKhususNilai);
        payrollPph.setTunjanganJabatanStruktural(CommonUtil.numbericFormat(tunjJabatanNilai,"###,###"));
        payrollPph.setTunjanganJabatanStrukturalNilai(tunjJabatanNilai);
        payrollPph.setTunjanganStruktural(CommonUtil.numbericFormat(tunjStrukturalNilai,"###,###"));
        payrollPph.setTunjanganStrukturalNilai(tunjStrukturalNilai);
        payrollPph.setTunjanganStrategis(CommonUtil.numbericFormat(tunjFungsionalNilai,"###,###"));
        payrollPph.setTunjanganStrategisNilai(tunjFungsionalNilai);
        payrollPph.setTunjanganPeralihan(CommonUtil.numbericFormat(totalTunjPeralihanNilai,"###,###"));
        payrollPph.setTunjanganPeralihanNilai(totalTunjPeralihanNilai);
        payrollPph.setTotalTunjanganLain(CommonUtil.numbericFormat(tunjanganLainNilai,"###,###"));
        payrollPph.setTotalTunjanganLainNilai(tunjanganLainNilai);
        payrollPph.setTunjanganTambahan(CommonUtil.numbericFormat(tunjTambahanNilai,"###,###"));
        payrollPph.setTunjanganTambahanNilai(tunjTambahanNilai);
        payrollPph.setPemondokan(CommonUtil.numbericFormat(tunjPemondokanNilai,"###,###"));
        payrollPph.setPemondokanNilai(tunjPemondokanNilai);
        payrollPph.setKomunikasi(CommonUtil.numbericFormat(tunjKomunikasiNilai,"###,###"));
        payrollPph.setKomunikasiNilai(tunjKomunikasiNilai);
        payrollPph.setTotalRlab(CommonUtil.numbericFormat(totalRLABNilai,"###,###"));
        payrollPph.setTotalRlabNilai(totalRLABNilai);
        payrollPph.setIuranPegawai(CommonUtil.numbericFormat(iuranPegawai,"###,###"));
        payrollPph.setIuranPegawaiNilai(iuranPegawai);
        payrollPph.setTunjanganLembur(CommonUtil.numbericFormat(tunjLemburNilai,"###,###"));
        payrollPph.setTunjanganLemburNilai(tunjLemburNilai);
        payrollPph.setTunjanganPensiun(CommonUtil.numbericFormat(iuranDapenPershNilai,"###,###"));
        payrollPph.setTunjanganPensiunNilai(iuranDapenPershNilai);
        payrollPph.setBpjsTk(CommonUtil.numbericFormat(tunjanganBpjsTkNilai,"###,###"));
        payrollPph.setBpjsTkNilai(tunjanganBpjsTkNilai);
        payrollPph.setBpjsKs(CommonUtil.numbericFormat(tunjanganBpjsKsNilai,"###,###"));
        payrollPph.setBpjsKsNilai(tunjanganBpjsKsNilai);

        setPayrollPph(payrollPph);

    }

    //untuk menghitung pajak dalam setahun
    private BigDecimal calculateTaxInOneYear(BigDecimal pkp,String npwp){

        BigDecimal persenNonNpwp = new BigDecimal(0.2);

        BigDecimal range1 = new BigDecimal(50000000);
        BigDecimal persenRange1 = new BigDecimal(0.05);

        BigDecimal range2 = new BigDecimal(250000000);
        BigDecimal persenRange2 = new BigDecimal(0.15);

        BigDecimal range3 = new BigDecimal(500000000);
        BigDecimal persenRange3 = new BigDecimal(0.25);

        BigDecimal persenRange4 = new BigDecimal(0.3);

        BigDecimal hasil = new BigDecimal(0) ;

        if (!"".equalsIgnoreCase(npwp)) {

            if ( pkp.compareTo(range1) <= 0 ) { // jika < = 50 juta

                hasil = persenRange1.multiply(pkp);

            } else { // diatas 50 juta

                BigDecimal selisihRange1dan2 = new BigDecimal(0);
                selisihRange1dan2 = range2.subtract(range1);

                hasil = persenRange1.multiply(range1);

                BigDecimal pkpSisa = new BigDecimal(0);
                pkpSisa = pkp.subtract(range1);

                if ( pkpSisa.compareTo(selisihRange1dan2) <= 0 ){ //jika <=200 juta (selisih 50 juta s.d 250 juta)

                    hasil = hasil.add(persenRange2.multiply(pkpSisa));

                } else { //diatas 200 juta (selisih 50 juta s.d 250 juta)

                    BigDecimal selisihRange2dan3 = new BigDecimal(0);
                    selisihRange2dan3 = range3.subtract(range2);

                    hasil = hasil.add(persenRange2.multiply(selisihRange1dan2));
                    pkpSisa = pkpSisa.subtract(selisihRange1dan2);

                    if ( pkpSisa.compareTo(selisihRange2dan3) <= 0 ){ //jika <=250 juta (selisih 250 juta s.d 500 juta)

                        hasil = hasil.add(persenRange3.multiply(pkpSisa));

                    } else { //diatas 250 juta (selisih 250 juta s.d 500 juta)

                        hasil = hasil.add(persenRange3.multiply(selisihRange2dan3));
                        pkpSisa = pkpSisa.subtract(selisihRange2dan3);

                        hasil = hasil.add(persenRange4.multiply(pkpSisa));

                    }
                }
            }

        } else {

            if ( pkp.compareTo(range1) <= 0 ) { // jika < = 50 juta

                hasil = persenNonNpwp.multiply(persenRange1.multiply(pkp));

            } else { // diatas 50 juta

                BigDecimal selisihRange1dan2 = new BigDecimal(0);
                selisihRange1dan2 = range2.subtract(range1);

                hasil = persenNonNpwp.multiply(persenRange1.multiply(range1));

                BigDecimal pkpSisa = new BigDecimal(0);
                pkpSisa = pkp.subtract(range1);

                if ( pkpSisa.compareTo(selisihRange1dan2) <= 0 ){ //jika <=200 juta (selisih 50 juta s.d 250 juta)

                    hasil = hasil.add(persenNonNpwp.multiply(persenRange2.multiply(pkpSisa)));

                } else { //diatas 200 juta (selisih 50 juta s.d 250 juta)

                    BigDecimal selisihRange2dan3 = new BigDecimal(0);
                    selisihRange2dan3 = range3.subtract(range2);

                    hasil = hasil.add(persenNonNpwp.multiply(persenRange2.multiply(selisihRange1dan2)));
                    pkpSisa = pkpSisa.subtract(selisihRange1dan2);

                    if ( pkpSisa.compareTo(selisihRange2dan3) <= 0 ){ //jika <=250 juta (selisih 250 juta s.d 500 juta)

                        hasil = hasil.add(persenNonNpwp.multiply(persenRange3.multiply(pkpSisa)));

                    } else { //diatas 250 juta (selisih 250 juta s.d 500 juta)

                        hasil = hasil.add(persenNonNpwp.multiply(persenRange3.multiply(selisihRange2dan3)));
                        pkpSisa = pkpSisa.subtract(selisihRange2dan3);

                        hasil = hasil.add(persenNonNpwp.multiply(persenRange4.multiply(pkpSisa)));

                    }
                }
            }
        }

        return hasil.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void calculatePreNonRutinPayroll() { //calculate non rutin payroll ex : THR, Jasop , Insentif, Cuti Panjang, dan Cuti Tahunan
        //perhitungan THR/jasop/insentif/ non rutin lain nya di PT. NMU
        //thp = gaji pokok + sankhus + tunj. jabatan + tunj. struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + RLAB (tunj. rumah + listrik + air + bbm) + tunj. komunikasi

        BigDecimal RLAB = new BigDecimal(0);
        RLAB = RLAB.add(tunjRumahNilai)
                .add(tunjListrikNilai)
                .add(tunjAirNilai)
                .add(tunjBbmNilai);

        BigDecimal tunjPeralihan = new BigDecimal(0);
        tunjPeralihan = tunjPeralihan.add(tunjPeralihanGapokNilai)
                .add(tunjPeralihanSankhusNilai)
                .add(tunjPeralihanTunjNilai);

        BigDecimal thp = new BigDecimal(0);
        thp = thp.add(getGajiPokokNilai())
                .add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(tunjPeralihan)
                .add(tunjTambahanNilai)
                .add(RLAB)
                .add(tunjKomunikasiNilai);

        setThpNilai(thp);
        setThp(CommonUtil.numbericFormat(thp,"###,###"));

        setTotalRLABNilai(RLAB);
        setTotalRLAB(CommonUtil.numbericFormat(RLAB,"###,###"));

        setTotalTunjPeralihanNilai(tunjPeralihan);
        setTotalTunjPeralihan(CommonUtil.numbericFormat(tunjPeralihan,"###,###"));

        setIncomeTidakTetapNilai(thp);
        setIncomeTidakTetap(CommonUtil.numbericFormat(thp,"###,###"));
    }

    @Override
    public void calculatePostNonRutinPayroll() { //to calculate gross not routine income and net not routine income

        // gaji kotor = THP + tunj pph
        // gaji bersih = THP

        BigDecimal sumOfTHP = getThpNilai();
        BigDecimal tunjPPHGaji = getTunjanganPphNilai();
        BigDecimal pphGaji = getPphGajiNilai();

        sumOfTHP = sumOfTHP.add(tunjPPHGaji);

        BigDecimal grossIncome = sumOfTHP;

        setGajiKotorNilai(grossIncome);
        setGajiKotor(CommonUtil.numbericFormat(grossIncome,"###,###"));

        BigDecimal netIncome = new BigDecimal(0);
        netIncome = netIncome.add(sumOfTHP)
                .subtract(pphGaji);

        setGajiBersihNilai(netIncome);
        setGajiBersih(CommonUtil.numbericFormat(netIncome,"###,###"));

    }

    @Override
    public void calculateNonRutinPPH() { //calculate tax non rutin
        BigDecimal pphNonRutin = new BigDecimal(0);
        BigDecimal tunjPphNonRutin = new BigDecimal(0);

        BigDecimal pphRutin = new BigDecimal(0);
        BigDecimal tunjPphRutin = new BigDecimal(0);

        BigDecimal bruto = new BigDecimal(0);
        BigDecimal netto = new BigDecimal(0);

        BigDecimal pkpSetahun = new BigDecimal(0);
        BigDecimal pphTerhutangSetahun = new BigDecimal(0);
        Integer selisih;
        BigDecimal ptkp = getPtkpPegawaiNilai();
        String npwp = getNpwpPegawai();

        PayrollPph payrollPph = new PayrollPph();

        //tunj yang diperhitungkan pph non rutin = sankhus + tunj.jabatan + tunj.struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + RLAB (tunj. rumah + listrik + air + bbm)+  tunj. komunikasi

        BigDecimal tunjDalamPPH = new BigDecimal(0);
        tunjDalamPPH = tunjDalamPPH.add(santunanKhususNilai)
                .add(tunjJabatanNilai)
                .add(tunjStrukturalNilai)
                .add(tunjFungsionalNilai)
                .add(totalTunjPeralihanNilai)
                .add(tunjTambahanNilai)
                .add(totalRLABNilai)
                .add(tunjKomunikasiNilai);

        //pengurang dalam pph -> biaya jabatan

        BigDecimal biayaJabatan = new BigDecimal(0);
        biayaJabatan = biayaJabatan.add(biayaJabatanNilai);

        //perhitungan pph dengan normal pendapatan rutin ( gapok + sankhus + tunj.jabatan + tunj.struktural + tunj. fungsional + tunj. peralihan(gapok+sankhus+tunj) + tunj. tambahan + RLAB (tunj. rumah + listrik + air + bbm)+  tunj. komunikasi )

        //menghitung bruto = gaji pokok + tunj.yang diperhitungkan pph + tunj.pph
        bruto = bruto.add(getGajiPokokNilai()).add(tunjDalamPPH);
        boolean isRoundUpPph = true;

        do {

            bruto = bruto.add(tunjPphRutin);

            //jika persen_biaya_jabatan(5%) x bruto lebih kecil dari param biaya jabatan, maka biaya jabatan = 5% x bruto, sebaliknya diambil param biaya jabatan
            if ((CommonUtil.percentage(bruto, persenBiayaJabatan)).compareTo(biayaJabatanNilai) < 1) {
                biayaJabatan = CommonUtil.percentage(bruto, persenBiayaJabatan);
            }

            //menghitung netto = bruto - biaya jabatan
            netto = bruto.subtract(biayaJabatan);

            //menghitung pkp untuk setahun untuk pendapatan rutin
            netto = (netto).multiply(BigDecimal.valueOf(12));
            pkpSetahun = netto.subtract(ptkp);
//            pkpSetahun = pkpSetahun.setScale(3, BigDecimal.ROUND_HALF_UP);
            pkpSetahun = pkpSetahun.setScale(0, BigDecimal.ROUND_HALF_UP);
            pkpSetahun = pkpSetahun.round(new MathContext(pkpSetahun.precision() - 3, RoundingMode.UP));

            if (pkpSetahun.compareTo(new BigDecimal(0)) < 0) {
                isRoundUpPph = false;
            } else {
                //menghitung pph setahun
                pphTerhutangSetahun = calculateTaxInOneYear(pkpSetahun, npwp);

                //pph setahun / 12 untuk mendapat pph perbulan
                pphRutin = pphTerhutangSetahun.divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP);

                //perhitungan selisih antara tunjPph dan pph gaji, jika selisih = 0 looping berhenti
                selisih = pphRutin.subtract(tunjPphRutin).intValue();

                if (selisih > 0) {
                    bruto = bruto.subtract(tunjPphRutin);
                } else {
                    isRoundUpPph = false;
                }
            }

            tunjPphRutin = pphRutin;

        } while (isRoundUpPph);
//        } while (selisih != 0);


        //perhitungan pph dengan pendapatan rutin dan non rutin di gabung

        bruto = new BigDecimal(0);
        netto = new BigDecimal(0);

        pkpSetahun = new BigDecimal(0);
        pphTerhutangSetahun = new BigDecimal(0);

        //menghitung bruto = gaji pokok + tunj.yang diperhitungkan pph + tunj.pph
        bruto = bruto.add(getGajiPokokNilai()).add(tunjDalamPPH);
        isRoundUpPph = true;

        do {

            bruto = bruto.add(tunjPphNonRutin);

            //jika persen_biaya_jabatan(5%) x bruto lebih kecil dari param biaya jabatan, maka biaya jabatan = 5% x bruto, sebaliknya diambil param biaya jabatan
            if ((CommonUtil.percentage(bruto, persenBiayaJabatan)).compareTo(biayaJabatanNilai)<1){
                biayaJabatan = CommonUtil.percentage(bruto, persenBiayaJabatan);
            }

            //menghitung netto = bruto - biaya jabatan
            netto = bruto.subtract(biayaJabatan);

            //menghitung pkp untuk setahun harus dikeluarkan pendapatan tidak tetap baru di kalikan 12 bulan
            netto = (netto).multiply(BigDecimal.valueOf(12)).add(incomeTidakTetapNilai);
            pkpSetahun = netto.subtract(ptkp);
//            pkpSetahun = pkpSetahun.setScale(3, BigDecimal.ROUND_HALF_UP);

            pkpSetahun = pkpSetahun.setScale(0, BigDecimal.ROUND_HALF_UP);
            pkpSetahun = pkpSetahun.round(new MathContext(pkpSetahun.precision() - 3,RoundingMode.UP));

            if (pkpSetahun.compareTo(new BigDecimal(0)) < 0) {

                isRoundUpPph = false;

            } else {

                //menghitung pph setahun
                pphTerhutangSetahun = calculateTaxInOneYear(pkpSetahun,npwp);

                //pph setahun / 12 untuk mendapat pph perbulan
                pphNonRutin = pphTerhutangSetahun.divide(BigDecimal.valueOf(12),2, BigDecimal.ROUND_HALF_UP);

                //perhitungan selisih antara tunjPph dan pph gaji, jika selisih = 0 looping berhenti
                selisih = pphNonRutin.subtract(tunjPphNonRutin).intValue();

                if (selisih > 0) {
                    bruto = bruto.subtract(tunjPphNonRutin);
                } else {
                    isRoundUpPph = false;
                }
            }

            tunjPphNonRutin = pphNonRutin;

        } while (isRoundUpPph);
//        } while (selisih != 0);

        //set pph yang didapat ke model pph payroll
        //selisih tunj pph non rutin ( pph non rutin - rutin )
        BigDecimal selisihPphNonRutin = new BigDecimal(0);
        selisihPphNonRutin = pphNonRutin.subtract(pphRutin);
        selisihPphNonRutin = selisihPphNonRutin.setScale(0, RoundingMode.HALF_UP); //Also tried with RoundingMode.UP

        BigDecimal reduce = biayaJabatan;

        payrollPph.setPkp(CommonUtil.numbericFormat(pkpSetahun,"###,###"));
        payrollPph.setPkpNilai(pkpSetahun);

        payrollPph.setPphGaji(CommonUtil.numbericFormat(selisihPphNonRutin,"###,###"));
        payrollPph.setPphGajiNilai(selisihPphNonRutin);

        payrollPph.setTunjanganPphBulan(CommonUtil.numbericFormat(selisihPphNonRutin,"###,###"));
        payrollPph.setTunjanganPphNilaiBulan(selisihPphNonRutin);

        //set tunjangan pph untuk PT. NMU pph gaji ditanggung perusahaan
        setTunjanganPphNilai(selisihPphNonRutin);
        setTunjanganPph(CommonUtil.numbericFormat(selisihPphNonRutin,"###,###"));

        setPphGajiNilai(selisihPphNonRutin);
        setPphGaji(CommonUtil.numbericFormat(selisihPphNonRutin,"###,###"));

        payrollPph.setBruto(CommonUtil.numbericFormat(bruto,"###,###"));
        payrollPph.setBrutoNilai(bruto);
        payrollPph.setReduce(CommonUtil.numbericFormat(reduce,"###,###"));
        payrollPph.setReduceNilai(reduce);
        payrollPph.setNip(getNip());
        payrollPph.setBulan(getPeriodePayroll());
        payrollPph.setTahun(getTahunPayroll());
        payrollPph.setPtkp(CommonUtil.numbericFormat(ptkp,"###,###"));
        payrollPph.setPtkpNilai(ptkp);
        payrollPph.setNetto(CommonUtil.numbericFormat(netto,"###,###"));
        payrollPph.setNettoNilai(netto);
        payrollPph.setBiayaJabatan(CommonUtil.numbericFormat(biayaJabatan,"###,###"));
        payrollPph.setBiayaJabatanNilai(biayaJabatan);
        payrollPph.setHutangPph(CommonUtil.numbericFormat(pphTerhutangSetahun,"###,###"));
        payrollPph.setHutangPphNilai(pphTerhutangSetahun);

        payrollPph.setGaji(CommonUtil.numbericFormat(getGajiPokokNilai(),"###,###"));
        payrollPph.setGajiNilai(getGajiPokokNilai());
        payrollPph.setSankhus(CommonUtil.numbericFormat(santunanKhususNilai,"###,###"));
        payrollPph.setSankhusNilai(santunanKhususNilai);
        payrollPph.setTunjanganJabatanStruktural(CommonUtil.numbericFormat(tunjJabatanNilai,"###,###"));
        payrollPph.setTunjanganJabatanStrukturalNilai(tunjJabatanNilai);
        payrollPph.setTunjanganStruktural(CommonUtil.numbericFormat(tunjStrukturalNilai,"###,###"));
        payrollPph.setTunjanganStrukturalNilai(tunjStrukturalNilai);
        payrollPph.setTunjanganStrategis(CommonUtil.numbericFormat(tunjFungsionalNilai,"###,###"));
        payrollPph.setTunjanganStrategisNilai(tunjFungsionalNilai);
        payrollPph.setTunjanganPeralihan(CommonUtil.numbericFormat(totalTunjPeralihanNilai,"###,###"));
        payrollPph.setTunjanganPeralihanNilai(totalTunjPeralihanNilai);
//        payrollPph.setTotalTunjanganLain(CommonUtil.numbericFormat(tunjanganLainNilai,"###,###"));
//        payrollPph.setTotalTunjanganLainNilai(tunjanganLainNilai);
        payrollPph.setTunjanganTambahan(CommonUtil.numbericFormat(tunjTambahanNilai,"###,###"));
        payrollPph.setTunjanganTambahanNilai(tunjTambahanNilai);
        payrollPph.setKomunikasi(CommonUtil.numbericFormat(tunjKomunikasiNilai,"###,###"));
        payrollPph.setKomunikasiNilai(tunjKomunikasiNilai);
        payrollPph.setTotalRlab(CommonUtil.numbericFormat(totalRLABNilai,"###,###"));
        payrollPph.setTotalRlabNilai(totalRLABNilai);
//        payrollPph.setTunjanganLembur(CommonUtil.numbericFormat(tunjLemburNilai,"###,###"));
//        payrollPph.setTunjanganLemburNilai(tunjLemburNilai);

        payrollPph.setIncomeTidakTetap(CommonUtil.numbericFormat(incomeTidakTetapNilai,"###,###"));
        payrollPph.setIncomeTidakTetapNilai(incomeTidakTetapNilai);

        setPayrollPph(payrollPph);
    }

    public String getStApprovalDate() {
        return stApprovalDate;
    }

    public void setStApprovalDate(String stApprovalDate) {
        this.stApprovalDate = stApprovalDate;
    }

//    public String getApprovalUnitFlag() {
//        return approvalUnitFlag;
//    }
//
//    public void setApprovalUnitFlag(String approvalUnitFlag) {
//        this.approvalUnitFlag = approvalUnitFlag;
//    }
//
//    public String getApprovalUnitId() {
//        return approvalUnitId;
//    }
//
//    public void setApprovalUnitId(String approvalUnitId) {
//        this.approvalUnitId = approvalUnitId;
//    }
//
//    public Timestamp getApprovalUnitDate() {
//        return approvalUnitDate;
//    }
//
//    public void setApprovalUnitDate(Timestamp approvalUnitDate) {
//        this.approvalUnitDate = approvalUnitDate;
//    }
//
//    public String getApprovalUnitName() {
//        return approvalUnitName;
//    }
//
//    public void setApprovalUnitName(String approvalUnitName) {
//        this.approvalUnitName = approvalUnitName;
//    }

    public String getJumlahPegawai() {
        return jumlahPegawai;
    }

    public void setJumlahPegawai(String jumlahPegawai) {
        this.jumlahPegawai = jumlahPegawai;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public BigDecimal getMultifikatorNilai() {
        return multifikatorNilai;
    }

    public void setMultifikatorNilai(BigDecimal multifikatorNilai) {
        this.multifikatorNilai = multifikatorNilai;
    }

    public String getMultifikator() {
        return multifikator;
    }

    public void setMultifikator(String multifikator) {
        this.multifikator = multifikator;
    }

    public int getJumlahAnakDitanggung() {
        return jumlahAnakDitanggung;
    }

    public void setJumlahAnakDitanggung(int jumlahAnakDitanggung) {
        this.jumlahAnakDitanggung = jumlahAnakDitanggung;
    }

    public String getStJumlahAnakDitanggung() {
        return stJumlahAnakDitanggung;
    }

    public void setStJumlahAnakDitanggung(String stJumlahAnakDitanggung) {
        this.stJumlahAnakDitanggung = stJumlahAnakDitanggung;
    }

    public String getTahunSkalaGaji() {
        return tahunSkalaGaji;
    }

    public void setTahunSkalaGaji(String tahunSkalaGaji) {
        this.tahunSkalaGaji = tahunSkalaGaji;
    }

    public Date getTanggalAwalLembur() {
        return tanggalAwalLembur;
    }

    public void setTanggalAwalLembur(Date tanggalAwalLembur) {
        this.tanggalAwalLembur = tanggalAwalLembur;
    }

    public Date getTanggalAkhirLembur() {
        return tanggalAkhirLembur;
    }

    public void setTanggalAkhirLembur(Date tanggalAkhirLembur) {
        this.tanggalAkhirLembur = tanggalAkhirLembur;
    }

    public String getTotalTunjPeralihan() {
        return totalTunjPeralihan;
    }

    public void setTotalTunjPeralihan(String totalTunjPeralihan) {
        this.totalTunjPeralihan = totalTunjPeralihan;
    }

    public BigDecimal getTotalTunjPeralihanNilai() {
        return totalTunjPeralihanNilai;
    }

    public void setTotalTunjPeralihanNilai(BigDecimal totalTunjPeralihanNilai) {
        this.totalTunjPeralihanNilai = totalTunjPeralihanNilai;
    }

    public String getComponentA() {
        return componentA;
    }

    public void setComponentA(String componentA) {
        this.componentA = componentA;
    }

    public String getComponentB() {
        return componentB;
    }

    public void setComponentB(String componentB) {
        this.componentB = componentB;
    }

    public String getComponentC() {
        return componentC;
    }

    public void setComponentC(String componentC) {
        this.componentC = componentC;
    }

    public BigDecimal getComponentANilai() {
        return componentANilai;
    }

    public void setComponentANilai(BigDecimal componentANilai) {
        this.componentANilai = componentANilai;
    }

    public BigDecimal getComponentBNilai() {
        return componentBNilai;
    }

    public void setComponentBNilai(BigDecimal componentBNilai) {
        this.componentBNilai = componentBNilai;
    }

    public BigDecimal getComponentCNilai() {
        return componentCNilai;
    }

    public void setComponentCNilai(BigDecimal componentCNilai) {
        this.componentCNilai = componentCNilai;
    }

    public List<MappingPersenGaji> getListOfMappingPersenGaji() {
        return listOfMappingPersenGaji;
    }

    //setting persen gaji berdasarkan parameter
    public void setListOfMappingPersenGaji(List<MappingPersenGaji> listOfMappingPersenGaji) {

        this.listOfMappingPersenGaji = listOfMappingPersenGaji;

        BigDecimal seratusPersen = new BigDecimal(100);

        for ( MappingPersenGaji mappingPersenGaji : listOfMappingPersenGaji) {

            BigDecimal persentase = new BigDecimal(mappingPersenGaji.getPresentase());
            if ("gaji_golongan".equalsIgnoreCase(mappingPersenGaji.getJenisGaji())) {

                BigDecimal gajiPokok = getGajiPokokNilai();
                gajiPokok = gajiPokok.multiply(persentase).divide(seratusPersen);
                setGajiPokokNilai(gajiPokok);

            } else if ("tunjangan_jabatan".equalsIgnoreCase(mappingPersenGaji.getJenisGaji())) {

                BigDecimal tunjanganJabatan = getTunjJabatanNilai();
                tunjanganJabatan = tunjanganJabatan.multiply(persentase).divide(seratusPersen);
                setTunjJabatanNilai(tunjanganJabatan);

            } else if ("tunjangan_jabatan_struktural".equalsIgnoreCase(mappingPersenGaji.getJenisGaji())) {

                BigDecimal tunjanganStruktural = getTunjStrukturalNilai();
                tunjanganStruktural = tunjanganStruktural.multiply(persentase).divide(seratusPersen);
                setTunjStrukturalNilai(tunjanganStruktural);

            }

        }

    }

    public int getiMasaKerjaGol() {
        return iMasaKerjaGol;
    }

    public void setiMasaKerjaGol(int iMasaKerjaGol) {
        this.iMasaKerjaGol = iMasaKerjaGol;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getSubDivisiId() {
        return subDivisiId;
    }

    public void setSubDivisiId(String subDivisiId) {
        this.subDivisiId = subDivisiId;
    }

    public String getSubDivisiName() {
        return subDivisiName;
    }

    public void setSubDivisiName(String subDivisiName) {
        this.subDivisiName = subDivisiName;
    }

    public String getKelompokPosisiId() {
        return kelompokPosisiId;
    }

    public void setKelompokPosisiId(String kelompokPosisiId) {
        this.kelompokPosisiId = kelompokPosisiId;
    }

    public String getKelompokPosisiName() {
        return kelompokPosisiName;
    }

    public void setKelompokPosisiName(String kelompokPosisiName) {
        this.kelompokPosisiName = kelompokPosisiName;
    }

    public String getPosisiId() {
        return posisiId;
    }

    public void setPosisiId(String posisiId) {
        this.posisiId = posisiId;
    }

    public String getPosisiName() {
        return posisiName;
    }

    public void setPosisiName(String posisiName) {
        this.posisiName = posisiName;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public String getProfesiName() {
        return profesiName;
    }

    public void setProfesiName(String profesiName) {
        this.profesiName = profesiName;
    }

    public String getTipePegawai() {
        return tipePegawai;
    }

    public void setTipePegawai(String tipePegawai) {
        this.tipePegawai = tipePegawai;
    }

    public String getTipePegawaiName() {
        return tipePegawaiName;
    }

    public void setTipePegawaiName(String tipePegawaiName) {
        this.tipePegawaiName = tipePegawaiName;
    }

    public String getStatusPegawai() {
        return statusPegawai;
    }

    public void setStatusPegawai(String statusPegawai) {
        this.statusPegawai = statusPegawai;
    }

    public String getStatusPegawaiName() {
        return statusPegawaiName;
    }

    public void setStatusPegawaiName(String statusPegawaiName) {
        this.statusPegawaiName = statusPegawaiName;
    }

    public String getJenisPegawai() {
        return jenisPegawai;
    }

    public void setJenisPegawai(String jenisPegawai) {
        this.jenisPegawai = jenisPegawai;
    }

    public String getJenisPegawaiName() {
        return jenisPegawaiName;
    }

    public void setJenisPegawaiName(String jenisPegawaiName) {
        this.jenisPegawaiName = jenisPegawaiName;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getStGradeLevel() {
        return stGradeLevel;
    }

    public void setStGradeLevel(String stGradeLevel) {
        this.stGradeLevel = stGradeLevel;
    }

    public String getMasaKerjaGol() {
        return masaKerjaGol;
    }

    public void setMasaKerjaGol(String masaKerjaGol) {
        this.masaKerjaGol = masaKerjaGol;
    }

    public String getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(String golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public String getDanaPensiun() {
        return danaPensiun;
    }

    public void setDanaPensiun(String danaPensiun) {
        this.danaPensiun = danaPensiun;
    }

    public String getDanaPensiunName() {
        return danaPensiunName;
    }

    public void setDanaPensiunName(String danaPensiunName) {
        this.danaPensiunName = danaPensiunName;
    }



    public Date getTanggalPraPensiun() {
        return tanggalPraPensiun;
    }

    public void setTanggalPraPensiun(Date tanggalPraPensiun) {
        this.tanggalPraPensiun = tanggalPraPensiun;
    }

    public String getStTanggalPraPensiun() {
        return stTanggalPraPensiun;
    }

    public void setStTanggalPraPensiun(String stTanggalPraPensiun) {
        this.stTanggalPraPensiun = stTanggalPraPensiun;
    }

    public String getGajiPensiun() {
        return gajiPensiun;
    }

    public void setGajiPensiun(String gajiPensiun) {
        this.gajiPensiun = gajiPensiun;
    }

    public BigDecimal getGajiPensiunNilai() {
        return gajiPensiunNilai;
    }

    public void setGajiPensiunNilai(BigDecimal gajiPensiunNilai) {
        this.gajiPensiunNilai = gajiPensiunNilai;
    }


    public PayrollPph getPayrollPph() {
        return payrollPph;
    }

    public void setPayrollPph(PayrollPph payrollPph) {
        this.payrollPph = payrollPph;
    }

    public String getSantunanKhusus() {
        return santunanKhusus;
    }

    public void setSantunanKhusus(String santunanKhusus) {
        this.santunanKhusus = santunanKhusus;
    }

    public BigDecimal getSantunanKhususNilai() {
        return santunanKhususNilai;
    }

    public void setSantunanKhususNilai(BigDecimal santunanKhususNilai) {
        this.santunanKhususNilai = santunanKhususNilai;
    }

    public String getTunjRumah() {
        return tunjRumah;
    }

    public void setTunjRumah(String tunjRumah) {
        this.tunjRumah = tunjRumah;
    }

    public BigDecimal getTunjRumahNilai() {
        return tunjRumahNilai;
    }

    public void setTunjRumahNilai(BigDecimal tunjRumahNilai) {
        this.tunjRumahNilai = tunjRumahNilai;
    }

    public String getTunjListrik() {
        return tunjListrik;
    }

    public void setTunjListrik(String tunjListrik) {
        this.tunjListrik = tunjListrik;
    }

    public BigDecimal getTunjListrikNilai() {
        return tunjListrikNilai;
    }

    public void setTunjListrikNilai(BigDecimal tunjListrikNilai) {
        this.tunjListrikNilai = tunjListrikNilai;
    }

    public String getTunjAir() {
        return tunjAir;
    }

    public void setTunjAir(String tunjAir) {
        this.tunjAir = tunjAir;
    }

    public BigDecimal getTunjAirNilai() {
        return tunjAirNilai;
    }

    public void setTunjAirNilai(BigDecimal tunjAirNilai) {
        this.tunjAirNilai = tunjAirNilai;
    }

    public String getTunjBbm() {
        return tunjBbm;
    }

    public void setTunjBbm(String tunjBbm) {
        this.tunjBbm = tunjBbm;
    }

    public BigDecimal getTunjBbmNilai() {
        return tunjBbmNilai;
    }

    public void setTunjBbmNilai(BigDecimal tunjBbmNilai) {
        this.tunjBbmNilai = tunjBbmNilai;
    }

    public String getTunjJabatan() {
        return tunjJabatan;
    }

    public void setTunjJabatan(String tunjJabatan) {
        this.tunjJabatan = tunjJabatan;
    }

    public BigDecimal getTunjJabatanNilai() {
        return tunjJabatanNilai;
    }

    public void setTunjJabatanNilai(BigDecimal tunjJabatanNilai) {
        this.tunjJabatanNilai = tunjJabatanNilai;
    }

    public String getTunjStruktural() {
        return tunjStruktural;
    }

    public void setTunjStruktural(String tunjStruktural) {
        this.tunjStruktural = tunjStruktural;
    }

    public BigDecimal getTunjStrukturalNilai() {
        return tunjStrukturalNilai;
    }

    public void setTunjStrukturalNilai(BigDecimal tunjStrukturalNilai) {
        this.tunjStrukturalNilai = tunjStrukturalNilai;
    }

    public String getTunjFungsional() {
        return tunjFungsional;
    }

    public void setTunjFungsional(String tunjFungsional) {
        this.tunjFungsional = tunjFungsional;
    }

    public BigDecimal getTunjFungsionalNilai() {
        return tunjFungsionalNilai;
    }

    public void setTunjFungsionalNilai(BigDecimal tunjFungsionalNilai) {
        this.tunjFungsionalNilai = tunjFungsionalNilai;
    }

    public String getTunjTambahan() {
        return tunjTambahan;
    }

    public void setTunjTambahan(String tunjTambahan) {
        this.tunjTambahan = tunjTambahan;
    }

    public BigDecimal getTunjTambahanNilai() {
        return tunjTambahanNilai;
    }

    public void setTunjTambahanNilai(BigDecimal tunjTambahanNilai) {
        this.tunjTambahanNilai = tunjTambahanNilai;
    }

    public String getTotalRLAB() {
        return totalRLAB;
    }

    public void setTotalRLAB(String totalRLAB) {
        this.totalRLAB = totalRLAB;
    }

    public BigDecimal getTotalRLABNilai() {
        return totalRLABNilai;
    }

    public void setTotalRLABNilai(BigDecimal totalRLABNilai) {
        this.totalRLABNilai = totalRLABNilai;
    }

    public String getTunjPeralihanGapok() {
        return tunjPeralihanGapok;
    }

    public void setTunjPeralihanGapok(String tunjPeralihanGapok) {
        this.tunjPeralihanGapok = tunjPeralihanGapok;
    }

    public BigDecimal getTunjPeralihanGapokNilai() {
        return tunjPeralihanGapokNilai;
    }

    public void setTunjPeralihanGapokNilai(BigDecimal tunjPeralihanGapokNilai) {
        this.tunjPeralihanGapokNilai = tunjPeralihanGapokNilai;
    }

    public String getTunjPeralihanSankhus() {
        return tunjPeralihanSankhus;
    }

    public void setTunjPeralihanSankhus(String tunjPeralihanSankhus) {
        this.tunjPeralihanSankhus = tunjPeralihanSankhus;
    }

    public BigDecimal getTunjPeralihanSankhusNilai() {
        return tunjPeralihanSankhusNilai;
    }

    public void setTunjPeralihanSankhusNilai(BigDecimal tunjPeralihanSankhusNilai) {
        this.tunjPeralihanSankhusNilai = tunjPeralihanSankhusNilai;
    }

    public String getTunjPeralihanTunj() {
        return tunjPeralihanTunj;
    }

    public void setTunjPeralihanTunj(String tunjPeralihanTunj) {
        this.tunjPeralihanTunj = tunjPeralihanTunj;
    }

    public BigDecimal getTunjPeralihanTunjNilai() {
        return tunjPeralihanTunjNilai;
    }

    public void setTunjPeralihanTunjNilai(BigDecimal tunjPeralihanTunjNilai) {
        this.tunjPeralihanTunjNilai = tunjPeralihanTunjNilai;
    }

    public String getTunjKomunikasi() {
        return tunjKomunikasi;
    }

    public void setTunjKomunikasi(String tunjKomunikasi) {
        this.tunjKomunikasi = tunjKomunikasi;
    }

    public BigDecimal getTunjKomunikasiNilai() {
        return tunjKomunikasiNilai;
    }

    public void setTunjKomunikasiNilai(BigDecimal tunjKomunikasiNilai) {
        this.tunjKomunikasiNilai = tunjKomunikasiNilai;
    }

    public String getTunjPemondokan() {
        return tunjPemondokan;
    }

    public void setTunjPemondokan(String tunjPemondokan) {
        this.tunjPemondokan = tunjPemondokan;
    }

    public BigDecimal getTunjPemondokanNilai() {
        return tunjPemondokanNilai;
    }

    public void setTunjPemondokanNilai(BigDecimal tunjPemondokanNilai) {
        this.tunjPemondokanNilai = tunjPemondokanNilai;
    }

    public String getTunjLembur() {
        return tunjLembur;
    }

    public void setTunjLembur(String tunjLembur) {
        this.tunjLembur = tunjLembur;
    }

    public BigDecimal getTunjLemburNilai() {
        return tunjLemburNilai;
    }

    public void setTunjLemburNilai(BigDecimal tunjLemburNilai) {
        this.tunjLemburNilai = tunjLemburNilai;
    }

    public String getTunjSupervisi() {
        return tunjSupervisi;
    }

    public void setTunjSupervisi(String tunjSupervisi) {
        this.tunjSupervisi = tunjSupervisi;
    }

    public BigDecimal getTunjSupervisiNilai() {
        return tunjSupervisiNilai;
    }

    public void setTunjSupervisiNilai(BigDecimal tunjSupervisiNilai) {
        this.tunjSupervisiNilai = tunjSupervisiNilai;
    }

    public String getTunjLokal() {
        return tunjLokal;
    }

    public void setTunjLokal(String tunjLokal) {
        this.tunjLokal = tunjLokal;
    }

    public BigDecimal getTunjLokalNilai() {
        return tunjLokalNilai;
    }

    public void setTunjLokalNilai(BigDecimal tunjLokalNilai) {
        this.tunjLokalNilai = tunjLokalNilai;
    }

    public String getTunjSiaga() {
        return tunjSiaga;
    }

    public void setTunjSiaga(String tunjSiaga) {
        this.tunjSiaga = tunjSiaga;
    }

    public BigDecimal getTunjSiagaNilai() {
        return tunjSiagaNilai;
    }

    public void setTunjSiagaNilai(BigDecimal tunjSiagaNilai) {
        this.tunjSiagaNilai = tunjSiagaNilai;
    }

    public String getTunjProfesional() {
        return tunjProfesional;
    }

    public void setTunjProfesional(String tunjProfesional) {
        this.tunjProfesional = tunjProfesional;
    }

    public BigDecimal getTunjProfesionalNilai() {
        return tunjProfesionalNilai;
    }

    public void setTunjProfesionalNilai(BigDecimal tunjProfesionalNilai) {
        this.tunjProfesionalNilai = tunjProfesionalNilai;
    }

    public String getIncomeTidakTetap() {
        return incomeTidakTetap;
    }

    public void setIncomeTidakTetap(String incomeTidakTetap) {
        this.incomeTidakTetap = incomeTidakTetap;
    }

    public BigDecimal getIncomeTidakTetapNilai() {
        return incomeTidakTetapNilai;
    }

    public void setIncomeTidakTetapNilai(BigDecimal incomeTidakTetapNilai) {
        this.incomeTidakTetapNilai = incomeTidakTetapNilai;
    }

    public String getTunjanganLain() {
        return tunjanganLain;
    }

    public void setTunjanganLain(String tunjanganLain) {
        this.tunjanganLain = tunjanganLain;
    }

    public BigDecimal getTunjanganLainNilai() {
        return tunjanganLainNilai;
    }

    public void setTunjanganLainNilai(BigDecimal tunjanganLainNilai) {
        this.tunjanganLainNilai = tunjanganLainNilai;
    }

    public String getTunjanganDapen() {
        return tunjanganDapen;
    }

    public void setTunjanganDapen(String tunjanganDapen) {
        this.tunjanganDapen = tunjanganDapen;
    }

    public BigDecimal getTunjanganDapenNilai() {
        return tunjanganDapenNilai;
    }

    public void setTunjanganDapenNilai(BigDecimal tunjanganDapenNilai) {
        this.tunjanganDapenNilai = tunjanganDapenNilai;
    }

    public String getTunjanganPph() {
        return tunjanganPph;
    }

    public void setTunjanganPph(String tunjanganPph) {
        this.tunjanganPph = tunjanganPph;
    }

    public BigDecimal getTunjanganPphNilai() {
        return tunjanganPphNilai;
    }

    public void setTunjanganPphNilai(BigDecimal tunjanganPphNilai) {
        this.tunjanganPphNilai = tunjanganPphNilai;
    }

    public String getTunjanganBpjsKs() {
        return tunjanganBpjsKs;
    }

    public void setTunjanganBpjsKs(String tunjanganBpjsKs) {
        this.tunjanganBpjsKs = tunjanganBpjsKs;
    }

    public BigDecimal getTunjanganBpjsKsNilai() {
        return tunjanganBpjsKsNilai;
    }

    public void setTunjanganBpjsKsNilai(BigDecimal tunjanganBpjsKsNilai) {
        this.tunjanganBpjsKsNilai = tunjanganBpjsKsNilai;
    }

    public String getTunjanganBpjsTk() {
        return tunjanganBpjsTk;
    }

    public void setTunjanganBpjsTk(String tunjanganBpjsTk) {
        this.tunjanganBpjsTk = tunjanganBpjsTk;
    }

    public BigDecimal getTunjanganBpjsTkNilai() {
        return tunjanganBpjsTkNilai;
    }

    public void setTunjanganBpjsTkNilai(BigDecimal tunjanganBpjsTkNilai) {
        this.tunjanganBpjsTkNilai = tunjanganBpjsTkNilai;
    }

    public String getTunjanganSosialLain() {
        return tunjanganSosialLain;
    }

    public void setTunjanganSosialLain(String tunjanganSosialLain) {
        this.tunjanganSosialLain = tunjanganSosialLain;
    }

    public BigDecimal getTunjanganSosialLainNilai() {
        return tunjanganSosialLainNilai;
    }

    public void setTunjanganSosialLainNilai(BigDecimal tunjanganSosialLainNilai) {
        this.tunjanganSosialLainNilai = tunjanganSosialLainNilai;
    }

    public String getPemondokan() {
        return pemondokan;
    }

    public void setPemondokan(String pemondokan) {
        this.pemondokan = pemondokan;
    }

    public BigDecimal getPemondokanNilai() {
        return pemondokanNilai;
    }

    public void setPemondokanNilai(BigDecimal pemondokanNilai) {
        this.pemondokanNilai = pemondokanNilai;
    }

    public String getKomunikasi() {
        return komunikasi;
    }

    public void setKomunikasi(String komunikasi) {
        this.komunikasi = komunikasi;
    }

    public BigDecimal getKomunikasiNilai() {
        return komunikasiNilai;
    }

    public void setKomunikasiNilai(BigDecimal komunikasiNilai) {
        this.komunikasiNilai = komunikasiNilai;
    }

    public String getIuranDapenKary() {
        return iuranDapenKary;
    }

    public void setIuranDapenKary(String iuranDapenKary) {
        this.iuranDapenKary = iuranDapenKary;
    }

    public BigDecimal getIuranDapenKaryNilai() {
        return iuranDapenKaryNilai;
    }

    public void setIuranDapenKaryNilai(BigDecimal iuranDapenKaryNilai) {
        this.iuranDapenKaryNilai = iuranDapenKaryNilai;
    }

    public String getIuranDapenPersh() {
        return iuranDapenPersh;
    }

    public void setIuranDapenPersh(String iuranDapenPersh) {
        this.iuranDapenPersh = iuranDapenPersh;
    }

    public BigDecimal getIuranDapenPershNilai() {
        return iuranDapenPershNilai;
    }

    public void setIuranDapenPershNilai(BigDecimal iuranDapenPershNilai) {
        this.iuranDapenPershNilai = iuranDapenPershNilai;
    }

    public String getPersenDapenPersh() {
        return persenDapenPersh;
    }

    public void setPersenDapenPersh(String persenDapenPersh) {
        this.persenDapenPersh = persenDapenPersh;
    }

    public BigDecimal getPersenDapenPershNilai() {
        return persenDapenPershNilai;
    }

    public void setPersenDapenPershNilai(BigDecimal persenDapenPershNilai) {
        this.persenDapenPershNilai = persenDapenPershNilai;
    }

    public String getPersenDapenKary() {
        return persenDapenKary;
    }

    public void setPersenDapenKary(String persenDapenKary) {
        this.persenDapenKary = persenDapenKary;
    }

    public BigDecimal getPersenDapenKaryNilai() {
        return persenDapenKaryNilai;
    }

    public void setPersenDapenKaryNilai(BigDecimal persenDapenKaryNilai) {
        this.persenDapenKaryNilai = persenDapenKaryNilai;
    }

    public String getIuranBpjsTkKary() {
        return iuranBpjsTkKary;
    }

    public void setIuranBpjsTkKary(String iuranBpjsTkKary) {
        this.iuranBpjsTkKary = iuranBpjsTkKary;
    }

    public BigDecimal getIuranBpjsTkKaryNilai() {
        return iuranBpjsTkKaryNilai;
    }

    public void setIuranBpjsTkKaryNilai(BigDecimal iuranBpjsTkKaryNilai) {
        this.iuranBpjsTkKaryNilai = iuranBpjsTkKaryNilai;
    }

    public String getTotalIuranBpjsTkKary() {
        return totalIuranBpjsTkKary;
    }

    public void setTotalIuranBpjsTkKary(String totalIuranBpjsTkKary) {
        this.totalIuranBpjsTkKary = totalIuranBpjsTkKary;
    }

    public BigDecimal getTotalIuranBpjsTkKaryNilai() {
        return totalIuranBpjsTkKaryNilai;
    }

    public void setTotalIuranBpjsTkKaryNilai(BigDecimal totalIuranBpjsTkKaryNilai) {
        this.totalIuranBpjsTkKaryNilai = totalIuranBpjsTkKaryNilai;
    }

    public String getTotalIuranBpjsTkPers() {
        return totalIuranBpjsTkPers;
    }

    public void setTotalIuranBpjsTkPers(String totalIuranBpjsTkPers) {
        this.totalIuranBpjsTkPers = totalIuranBpjsTkPers;
    }

    public BigDecimal getTotalIuranBpjsTkPersNilai() {
        return totalIuranBpjsTkPersNilai;
    }

    public void setTotalIuranBpjsTkPersNilai(BigDecimal totalIuranBpjsTkPersNilai) {
        this.totalIuranBpjsTkPersNilai = totalIuranBpjsTkPersNilai;
    }

    public String getIuranBpjsKsKary() {
        return iuranBpjsKsKary;
    }

    public void setIuranBpjsKsKary(String iuranBpjsKsKary) {
        this.iuranBpjsKsKary = iuranBpjsKsKary;
    }

    public BigDecimal getIuranBpjsKsKaryNilai() {
        return iuranBpjsKsKaryNilai;
    }

    public void setIuranBpjsKsKaryNilai(BigDecimal iuranBpjsKsKaryNilai) {
        this.iuranBpjsKsKaryNilai = iuranBpjsKsKaryNilai;
    }

    public String getIuranBpjsKsPers() {
        return iuranBpjsKsPers;
    }

    public void setIuranBpjsKsPers(String iuranBpjsKsPers) {
        this.iuranBpjsKsPers = iuranBpjsKsPers;
    }

    public BigDecimal getIuranBpjsKsPersNilai() {
        return iuranBpjsKsPersNilai;
    }

    public void setIuranBpjsKsPersNilai(BigDecimal iuranBpjsKsPersNilai) {
        this.iuranBpjsKsPersNilai = iuranBpjsKsPersNilai;
    }

    public String getDasarPerhitunganBpjsKs() {
        return dasarPerhitunganBpjsKs;
    }

    public void setDasarPerhitunganBpjsKs(String dasarPerhitunganBpjsKs) {
        this.dasarPerhitunganBpjsKs = dasarPerhitunganBpjsKs;
    }

    public BigDecimal getDasarPerhitunganBpjsKsNilai() {
        return dasarPerhitunganBpjsKsNilai;
    }

    public void setDasarPerhitunganBpjsKsNilai(BigDecimal dasarPerhitunganBpjsKsNilai) {
        this.dasarPerhitunganBpjsKsNilai = dasarPerhitunganBpjsKsNilai;
    }

    public String getDasarPerhitunganBpjsTk() {
        return dasarPerhitunganBpjsTk;
    }

    public void setDasarPerhitunganBpjsTk(String dasarPerhitunganBpjsTk) {
        this.dasarPerhitunganBpjsTk = dasarPerhitunganBpjsTk;
    }

    public BigDecimal getDasarPerhitunganBpjsTkNilai() {
        return dasarPerhitunganBpjsTkNilai;
    }

    public void setDasarPerhitunganBpjsTkNilai(BigDecimal dasarPerhitunganBpjsTkNilai) {
        this.dasarPerhitunganBpjsTkNilai = dasarPerhitunganBpjsTkNilai;
    }

    public String getJpkBpjsTkKary() {
        return jpkBpjsTkKary;
    }

    public void setJpkBpjsTkKary(String jpkBpjsTkKary) {
        this.jpkBpjsTkKary = jpkBpjsTkKary;
    }

    public BigDecimal getJpkBpjsTkKaryNilai() {
        return jpkBpjsTkKaryNilai;
    }

    public void setJpkBpjsTkKaryNilai(BigDecimal jpkBpjsTkKaryNilai) {
        this.jpkBpjsTkKaryNilai = jpkBpjsTkKaryNilai;
    }

    public String getJpkBpjsTkPersh() {
        return jpkBpjsTkPersh;
    }

    public void setJpkBpjsTkPersh(String jpkBpjsTkPersh) {
        this.jpkBpjsTkPersh = jpkBpjsTkPersh;
    }

    public BigDecimal getJpkBpjsTkPershNilai() {
        return jpkBpjsTkPershNilai;
    }

    public void setJpkBpjsTkPershNilai(BigDecimal jpkBpjsTkPershNilai) {
        this.jpkBpjsTkPershNilai = jpkBpjsTkPershNilai;
    }

    public String getJkkBpjsTkPersh() {
        return jkkBpjsTkPersh;
    }

    public void setJkkBpjsTkPersh(String jkkBpjsTkPersh) {
        this.jkkBpjsTkPersh = jkkBpjsTkPersh;
    }

    public BigDecimal getJkkBpjsTkPershNilai() {
        return jkkBpjsTkPershNilai;
    }

    public void setJkkBpjsTkPershNilai(BigDecimal jkkBpjsTkPershNilai) {
        this.jkkBpjsTkPershNilai = jkkBpjsTkPershNilai;
    }

    public String getJhtBpjsTkPersh() {
        return jhtBpjsTkPersh;
    }

    public void setJhtBpjsTkPersh(String jhtBpjsTkPersh) {
        this.jhtBpjsTkPersh = jhtBpjsTkPersh;
    }

    public BigDecimal getJhtBpjsTkPershNilai() {
        return jhtBpjsTkPershNilai;
    }

    public void setJhtBpjsTkPershNilai(BigDecimal jhtBpjsTkPershNilai) {
        this.jhtBpjsTkPershNilai = jhtBpjsTkPershNilai;
    }

    public String getJkmBpjsTkPersh() {
        return jkmBpjsTkPersh;
    }

    public void setJkmBpjsTkPersh(String jkmBpjsTkPersh) {
        this.jkmBpjsTkPersh = jkmBpjsTkPersh;
    }

    public BigDecimal getJkmBpjsTkPershNilai() {
        return jkmBpjsTkPershNilai;
    }

    public void setJkmBpjsTkPershNilai(BigDecimal jkmBpjsTkPershNilai) {
        this.jkmBpjsTkPershNilai = jkmBpjsTkPershNilai;
    }

    public BigDecimal getUmrNilai() {
        return umrNilai;
    }

    public void setUmrNilai(BigDecimal umrNilai) {
        this.umrNilai = umrNilai;
    }

    public String getUmr() {
        return umr;
    }

    public void setUmr(String umr) {
        this.umr = umr;
    }

    public String getMinBpjsKs() {
        return minBpjsKs;
    }

    public void setMinBpjsKs(String minBpjsKs) {
        this.minBpjsKs = minBpjsKs;
    }

    public BigDecimal getMinBpjsKsNilai() {
        return minBpjsKsNilai;
    }

    public void setMinBpjsKsNilai(BigDecimal minBpjsKsNilai) {
        this.minBpjsKsNilai = minBpjsKsNilai;
    }

    public String getMaxBpjsKs() {
        return maxBpjsKs;
    }

    public void setMaxBpjsKs(String maxBpjsKs) {
        this.maxBpjsKs = maxBpjsKs;
    }

    public BigDecimal getMaxBpjsKsNilai() {
        return maxBpjsKsNilai;
    }

    public void setMaxBpjsKsNilai(BigDecimal maxBpjsKsNilai) {
        this.maxBpjsKsNilai = maxBpjsKsNilai;
    }

    public String getMinBpjsTk() {
        return minBpjsTk;
    }

    public void setMinBpjsTk(String minBpjsTk) {
        this.minBpjsTk = minBpjsTk;
    }

    public BigDecimal getMinBpjsTkNilai() {
        return minBpjsTkNilai;
    }

    public void setMinBpjsTkNilai(BigDecimal minBpjsTkNilai) {
        this.minBpjsTkNilai = minBpjsTkNilai;
    }

    public String getMaxBpjsTk() {
        return maxBpjsTk;
    }

    public void setMaxBpjsTk(String maxBpjsTk) {
        this.maxBpjsTk = maxBpjsTk;
    }

    public BigDecimal getMaxBpjsTkNilai() {
        return maxBpjsTkNilai;
    }

    public void setMaxBpjsTkNilai(BigDecimal maxBpjsTkNilai) {
        this.maxBpjsTkNilai = maxBpjsTkNilai;
    }

    public String getPersenBpjsKsKary() {
        return persenBpjsKsKary;
    }

    public void setPersenBpjsKsKary(String persenBpjsKsKary) {
        this.persenBpjsKsKary = persenBpjsKsKary;
    }

    public BigDecimal getPersenBpjsKsKaryNilai() {
        return persenBpjsKsKaryNilai;
    }

    public void setPersenBpjsKsKaryNilai(BigDecimal persenBpjsKsKaryNilai) {
        this.persenBpjsKsKaryNilai = persenBpjsKsKaryNilai;
    }

    public String getPersenBpjsKsPersh() {
        return persenBpjsKsPersh;
    }

    public void setPersenBpjsKsPersh(String persenBpjsKsPersh) {
        this.persenBpjsKsPersh = persenBpjsKsPersh;
    }

    public BigDecimal getPersenBpjsKsPershNilai() {
        return persenBpjsKsPershNilai;
    }

    public void setPersenBpjsKsPershNilai(BigDecimal persenBpjsKsPershNilai) {
        this.persenBpjsKsPershNilai = persenBpjsKsPershNilai;
    }

    public String getPersenBpjsTkIuranKary() {
        return persenBpjsTkIuranKary;
    }

    public void setPersenBpjsTkIuranKary(String persenBpjsTkIuranKary) {
        this.persenBpjsTkIuranKary = persenBpjsTkIuranKary;
    }

    public BigDecimal getPersenBpjsTkIuranKaryNilai() {
        return persenBpjsTkIuranKaryNilai;
    }

    public void setPersenBpjsTkIuranKaryNilai(BigDecimal persenBpjsTkIuranKaryNilai) {
        this.persenBpjsTkIuranKaryNilai = persenBpjsTkIuranKaryNilai;
    }

    public String getPersenBpjsTkJpkKary() {
        return persenBpjsTkJpkKary;
    }

    public void setPersenBpjsTkJpkKary(String persenBpjsTkJpkKary) {
        this.persenBpjsTkJpkKary = persenBpjsTkJpkKary;
    }

    public BigDecimal getPersenBpjsTkJpkKaryNilai() {
        return persenBpjsTkJpkKaryNilai;
    }

    public void setPersenBpjsTkJpkKaryNilai(BigDecimal persenBpjsTkJpkKaryNilai) {
        this.persenBpjsTkJpkKaryNilai = persenBpjsTkJpkKaryNilai;
    }

    public String getPersenBpjsTkJhtPersh() {
        return persenBpjsTkJhtPersh;
    }

    public void setPersenBpjsTkJhtPersh(String persenBpjsTkJhtPersh) {
        this.persenBpjsTkJhtPersh = persenBpjsTkJhtPersh;
    }

    public BigDecimal getPersenBpjsTkJhtPershNilai() {
        return persenBpjsTkJhtPershNilai;
    }

    public void setPersenBpjsTkJhtPershNilai(BigDecimal persenBpjsTkJhtPershNilai) {
        this.persenBpjsTkJhtPershNilai = persenBpjsTkJhtPershNilai;
    }

    public String getPersenBpjsTkJkkPersh() {
        return persenBpjsTkJkkPersh;
    }

    public void setPersenBpjsTkJkkPersh(String persenBpjsTkJkkPersh) {
        this.persenBpjsTkJkkPersh = persenBpjsTkJkkPersh;
    }

    public BigDecimal getPersenBpjsTkJkkPershNilai() {
        return persenBpjsTkJkkPershNilai;
    }

    public void setPersenBpjsTkJkkPershNilai(BigDecimal persenBpjsTkJkkPershNilai) {
        this.persenBpjsTkJkkPershNilai = persenBpjsTkJkkPershNilai;
    }

    public String getPersenBpjsTkJkmPersh() {
        return persenBpjsTkJkmPersh;
    }

    public void setPersenBpjsTkJkmPersh(String persenBpjsTkJkmPersh) {
        this.persenBpjsTkJkmPersh = persenBpjsTkJkmPersh;
    }

    public BigDecimal getPersenBpjsTkJkmPershNilai() {
        return persenBpjsTkJkmPershNilai;
    }

    public void setPersenBpjsTkJkmPershNilai(BigDecimal persenBpjsTkJkmPershNilai) {
        this.persenBpjsTkJkmPershNilai = persenBpjsTkJkmPershNilai;
    }

    public String getPersenBpjsTkJpkPersh() {
        return persenBpjsTkJpkPersh;
    }

    public void setPersenBpjsTkJpkPersh(String persenBpjsTkJpkPersh) {
        this.persenBpjsTkJpkPersh = persenBpjsTkJpkPersh;
    }

    public BigDecimal getPersenBpjsTkJpkPershNilai() {
        return persenBpjsTkJpkPershNilai;
    }

    public void setPersenBpjsTkJpkPershNilai(BigDecimal persenBpjsTkJpkPershNilai) {
        this.persenBpjsTkJpkPershNilai = persenBpjsTkJpkPershNilai;
    }

    public String getTotalPotonganLain() {
        return totalPotonganLain;
    }

    public void setTotalPotonganLain(String totalPotonganLain) {
        this.totalPotonganLain = totalPotonganLain;
    }

    public BigDecimal getTotalPotonganLainNilai() {
        return totalPotonganLainNilai;
    }

    public void setTotalPotonganLainNilai(BigDecimal totalPotonganLainNilai) {
        this.totalPotonganLainNilai = totalPotonganLainNilai;
    }

    public String getKopkar() {
        return kopkar;
    }

    public void setKopkar(String kopkar) {
        this.kopkar = kopkar;
    }

    public String getIuranSp() {
        return iuranSp;
    }

    public void setIuranSp(String iuranSp) {
        this.iuranSp = iuranSp;
    }

    public String getIuranPiikb() {
        return iuranPiikb;
    }

    public void setIuranPiikb(String iuranPiikb) {
        this.iuranPiikb = iuranPiikb;
    }

    public String getBankBri() {
        return bankBri;
    }

    public void setBankBri(String bankBri) {
        this.bankBri = bankBri;
    }

    public String getBankMandiri() {
        return bankMandiri;
    }

    public void setBankMandiri(String bankMandiri) {
        this.bankMandiri = bankMandiri;
    }

    public String getInfaq() {
        return infaq;
    }

    public void setInfaq(String infaq) {
        this.infaq = infaq;
    }

    public String getPerkesDanObat() {
        return PerkesDanObat;
    }

    public void setPerkesDanObat(String perkesDanObat) {
        PerkesDanObat = perkesDanObat;
    }

    public String getListrik() {
        return listrik;
    }

    public void setListrik(String listrik) {
        this.listrik = listrik;
    }

    public String getIuranProfesi() {
        return iuranProfesi;
    }

    public void setIuranProfesi(String iuranProfesi) {
        this.iuranProfesi = iuranProfesi;
    }

    public String getPotonganLain() {
        return potonganLain;
    }

    public void setPotonganLain(String potonganLain) {
        this.potonganLain = potonganLain;
    }

    public BigDecimal getKopkarNilai() {
        return kopkarNilai;
    }

    public void setKopkarNilai(BigDecimal kopkarNilai) {
        this.kopkarNilai = kopkarNilai;
    }

    public BigDecimal getIuranSpNilai() {
        return iuranSpNilai;
    }

    public void setIuranSpNilai(BigDecimal iuranSpNilai) {
        this.iuranSpNilai = iuranSpNilai;
    }

    public BigDecimal getIuranPiikbNilai() {
        return iuranPiikbNilai;
    }

    public void setIuranPiikbNilai(BigDecimal iuranPiikbNilai) {
        this.iuranPiikbNilai = iuranPiikbNilai;
    }

    public BigDecimal getBankBriNilai() {
        return bankBriNilai;
    }

    public void setBankBriNilai(BigDecimal bankBriNilai) {
        this.bankBriNilai = bankBriNilai;
    }

    public BigDecimal getBankMandiriNilai() {
        return bankMandiriNilai;
    }

    public void setBankMandiriNilai(BigDecimal bankMandiriNilai) {
        this.bankMandiriNilai = bankMandiriNilai;
    }

    public BigDecimal getInfaqNilai() {
        return infaqNilai;
    }

    public void setInfaqNilai(BigDecimal infaqNilai) {
        this.infaqNilai = infaqNilai;
    }

    public BigDecimal getPerkesDanObatNilai() {
        return PerkesDanObatNilai;
    }

    public void setPerkesDanObatNilai(BigDecimal perkesDanObatNilai) {
        PerkesDanObatNilai = perkesDanObatNilai;
    }

    public BigDecimal getListrikNilai() {
        return listrikNilai;
    }

    public void setListrikNilai(BigDecimal listrikNilai) {
        this.listrikNilai = listrikNilai;
    }

    public BigDecimal getIuranProfesiNilai() {
        return iuranProfesiNilai;
    }

    public void setIuranProfesiNilai(BigDecimal iuranProfesiNilai) {
        this.iuranProfesiNilai = iuranProfesiNilai;
    }

    public BigDecimal getPotonganLainNilai() {
        return potonganLainNilai;
    }

    public void setPotonganLainNilai(BigDecimal potonganLainNilai) {
        this.potonganLainNilai = potonganLainNilai;
    }

    public String getBiayaJabatan() {
        return biayaJabatan;
    }

    public void setBiayaJabatan(String biayaJabatan) {
        this.biayaJabatan = biayaJabatan;
    }

    public BigDecimal getBiayaJabatanNilai() {
        return biayaJabatanNilai;
    }

    public void setBiayaJabatanNilai(BigDecimal biayaJabatanNilai) {
        this.biayaJabatanNilai = biayaJabatanNilai;
    }

//    public BigDecimal getParamBiayaJabatanNilai() {
//        return paramBiayaJabatanNilai;
//    }
//
//    public void setParamBiayaJabatanNilai(BigDecimal paramBiayaJabatanNilai) {
//        this.paramBiayaJabatanNilai = paramBiayaJabatanNilai;
//    }

    public BigDecimal getTotalPphUntilNovNilai() {
        return totalPphUntilNovNilai;
    }

    public void setTotalPphUntilNovNilai(BigDecimal totalPphUntilNovNilai) {
        this.totalPphUntilNovNilai = totalPphUntilNovNilai;
    }

    public String getTotalPphUntilNov() {
        return totalPphUntilNov;
    }

    public void setTotalPphUntilNov(String totalPphUntilNov) {
        this.totalPphUntilNov = totalPphUntilNov;
    }

    public BigDecimal getPersenBiayaJabatan() {
        return persenBiayaJabatan;
    }

    public void setPersenBiayaJabatan(BigDecimal persenBiayaJabatan) {
        this.persenBiayaJabatan = persenBiayaJabatan;
    }

    public BigDecimal getPphSeharusnyaNilai() {
        return pphSeharusnyaNilai;
    }

    public void setPphSeharusnyaNilai(BigDecimal pphSeharusnyaNilai) {
        this.pphSeharusnyaNilai = pphSeharusnyaNilai;
    }

    public BigDecimal getSelisihPphNilai() {
        return selisihPphNilai;
    }

    public void setSelisihPphNilai(BigDecimal selisihPphNilai) {
        this.selisihPphNilai = selisihPphNilai;
    }

    public List<PayrollPph> getListOfPphUntilNov() {
        return listOfPphUntilNov;
    }

    public void setListOfPphUntilNov(List<PayrollPph> listOfPphUntilNov) {
        this.listOfPphUntilNov = listOfPphUntilNov;
    }

    public String getPphSeharusnya() {
        return pphSeharusnya;
    }

    public void setPphSeharusnya(String pphSeharusnya) {
        this.pphSeharusnya = pphSeharusnya;
    }

    public String getSelisihPph() {
        return selisihPph;
    }

    public void setSelisihPph(String selisihPph) {
        this.selisihPph = selisihPph;
    }

    public boolean isKantorPusat() {
        return kantorPusat;
    }

    public void setKantorPusat(boolean kantorPusat) {
        this.kantorPusat = kantorPusat;
    }

    public boolean isKeuanganKantorPusat() {
        return keuanganKantorPusat;
    }

    public void setKeuanganKantorPusat(boolean keuanganKantorPusat) {
        this.keuanganKantorPusat = keuanganKantorPusat;
    }

    public boolean isCetakSatuan() {
        return cetakSatuan;
    }

    public void setCetakSatuan(boolean cetakSatuan) {
        this.cetakSatuan = cetakSatuan;
    }

    public boolean isAdaCheckBox() {
        return adaCheckBox;
    }

    public void setAdaCheckBox(boolean adaCheckBox) {
        this.adaCheckBox = adaCheckBox;
    }

    public boolean isSdm() {
        return sdm;
    }

    public void setSdm(boolean sdm) {
        this.sdm = sdm;
    }

    public String getPayrollHeaderId() {
        return payrollHeaderId;
    }

    public void setPayrollHeaderId(String payrollHeaderId) {
        this.payrollHeaderId = payrollHeaderId;
    }

    public String getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        this.flagEdit = flagEdit;
    }

    public String getFlagPrint() {
        return flagPrint;
    }

    public void setFlagPrint(String flagPrint) {
        this.flagPrint = flagPrint;
    }
}
