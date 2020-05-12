package com.neurix.simrs.transaksi.daftarulang.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.neurix.simrs.bpjs.vclaim.model.SepResponse;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.daftarulang.bo.DaftarUlangBo;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.statusperiksa.bo.StatusPeriksaBo;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DaftarUlangAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(DaftarUlangAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private DaftarUlangBo daftarUlangBoProxy;
    private CheckupBo checkupBoProxy;
    private String id;

    @Override
    public String search() {
        HeaderDetailCheckup detailCheckup = getHeaderDetailCheckup();
        detailCheckup.setBranchId(CommonUtil.userBranchLogin());
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        try {
            detailCheckupList = daftarUlangBoProxy.getListDaftarUlangPasien(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search status periksa " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", detailCheckupList);
        return "search";
    }

    @Override
    public String initForm() {
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setStDateFrom(tglToday);
        detailCheckup.setStDateTo(tglToday);
        setHeaderDetailCheckup(detailCheckup);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String add() {

        String id = getId();
        HeaderCheckup checkup = new HeaderCheckup();
        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
            detailCheckup.setIdPasien(checkup.getIdPasien());
            detailCheckup.setNamaPasien(checkup.getNama());
            detailCheckup.setAlamat(checkup.getJalan());
            detailCheckup.setDesa(checkup.getNamaDesa());
            detailCheckup.setKecamatan(checkup.getNamaKecamatan());
            detailCheckup.setKota(checkup.getNamaKota());
            detailCheckup.setProvinsi(checkup.getNamaProvinsi());
            detailCheckup.setIdPelayanan(checkup.getIdPelayanan());
            detailCheckup.setNamaPelayanan(checkup.getNamaPelayanan());
            if (checkup.getJenisKelamin() != null) {
                if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Perempuan";
                } else {
                    jk = "Laki-Laki";
                }
            }
            detailCheckup.setJenisKelamin(jk);
            detailCheckup.setTempatLahir(checkup.getTempatLahir());
            detailCheckup.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            detailCheckup.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            detailCheckup.setNik(checkup.getNoKtp());
            detailCheckup.setIdJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
            detailCheckup.setUrlKtp(checkup.getUrlKtp());
            detailCheckup.setTinggi(checkup.getTinggi());
            detailCheckup.setBerat(checkup.getBerat());
            detailCheckup.setNoSep(checkup.getNoSep());
            detailCheckup.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            detailCheckup.setMetodePembayaran(checkup.getMetodePembayaran());
            detailCheckup.setNoBpjs(checkup.getNoBpjs());
            if(checkup.getIdRawatInap() != null && !"".equalsIgnoreCase(checkup.getIdRawatInap())){
                detailCheckup.setRawatInap(true);
            }else{
                detailCheckup.setRawatInap(false);
            }
            detailCheckup.setIdRawatInap(checkup.getIdRawatInap());
            setHeaderDetailCheckup(detailCheckup);

        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }
        return "init_add";
    }

    public CheckResponse saveDaftarUlang(String data) throws JSONException, IOException {

        CheckResponse finalResponse = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        String genNoSep = "";
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        DaftarUlangBo daftarUlangBo = (DaftarUlangBo) ctx.getBean("daftarUlangBoProxy");

        JSONObject obj = new JSONObject(data);

        if (obj != null) {

            String idDetail = obj.getString("id_detail_checkup");
            String jenisPasien = obj.getString("jenis_periksa");
            Boolean rawatInap = Boolean.valueOf(obj.getString("rawat_inap"));
            String idRawatInap = obj.getString("id_rawat_inap");

            List<Branch> branchList = new ArrayList<>();
            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetail);

            try {
                detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.rujukRawatInap] Error when geting data detail poli, ", e);
            }

            if (!detailCheckupList.isEmpty()) {

                detailCheckup = detailCheckupList.get(0);

                if (detailCheckup != null) {

                    HeaderCheckup checkup = new HeaderCheckup();
                    checkup.setNoCheckup(detailCheckup.getNoCheckup());

                    List<HeaderCheckup> headerCheckupList = new ArrayList<>();

                    try {
                        headerCheckupList = checkupBo.getByCriteria(checkup);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
                    }

                    if (!headerCheckupList.isEmpty()) {

                        checkup = headerCheckupList.get(0);

                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

                        if ("bpjs".equalsIgnoreCase(jenisPasien)) {

                            Branch branch = new Branch();
                            branch.setBranchId(branchId);
                            branch.setFlag("Y");

                            try {
                                branchList = branchBo.getByCriteria(branch);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                            }

                            Branch getBranch = new Branch();

                            if (branchList.size() > 0) {

                                getBranch = branchList.get(0);

                                if (getBranch.getPpkPelayanan() != null) {

                                    List<Pasien> pasienList = new ArrayList<>();
                                    Pasien getPasien = new Pasien();
                                    getPasien.setIdPasien(checkup.getIdPasien());
                                    getPasien.setFlag("Y");

                                    try {
                                        pasienList = pasienBo.getByCriteria(getPasien);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                    }

                                    if (!pasienList.isEmpty()) {
                                        getPasien = pasienList.get(0);

                                        if (getPasien != null) {

                                            String namaDokter = "";
                                            String idDokter = "";
                                            String kodeDpjp = "";

                                            List<DokterTeam> dokterList = new ArrayList<>();
                                            DokterTeam dokterTeam = new DokterTeam();
                                            dokterTeam.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());

                                            try {
                                                dokterList = teamDokterBo.getByCriteria(dokterTeam);
                                            } catch (GeneralBOException e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (dokterList.size() > 0) {
                                                dokterTeam = dokterList.get(0);

                                                List<Dokter> dokterArrayList = new ArrayList<>();
                                                Dokter dokter = new Dokter();
                                                dokter.setIdDokter(dokterTeam.getIdDokter());
                                                dokter.setFlag("Y");

                                                try {
                                                    dokterArrayList = dokterBo.getByCriteria(dokter);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                if (dokterArrayList.size() > 0) {
                                                    namaDokter = dokterArrayList.get(0).getNamaDokter();
                                                    idDokter = dokterArrayList.get(0).getIdDokter();
                                                    kodeDpjp = dokterArrayList.get(0).getKodeDpjp();
                                                }

                                            }

                                            SepRequest sepRequest = new SepRequest();
                                            sepRequest.setNoKartu(obj.getString("no_bpjs"));
                                            sepRequest.setTglSep(time.toString());
                                            sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                            if(rawatInap){
                                                sepRequest.setJnsPelayanan("1");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                            }else{
                                                sepRequest.setJnsPelayanan("2");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                            }
                                            sepRequest.setKlsRawat(obj.getString("id_kelas"));//kelas rawat dari bpjs
                                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                            sepRequest.setAsalRujukan(obj.getString("perujuk"));//
                                            sepRequest.setTglRujukan(obj.getString("tanggal_rujukan"));
                                            sepRequest.setNoRujukan(obj.getString("no_rujukan"));
                                            sepRequest.setPpkRujukan(obj.getString("no_ppk"));
                                            sepRequest.setCatatan("");
                                            sepRequest.setDiagAwal(obj.getString("id_diagnosa"));
                                            sepRequest.setPoliTujuan(obj.getString("id_pelayanan"));
                                            sepRequest.setPoliEksekutif("0");
                                            sepRequest.setCob("0");
                                            sepRequest.setKatarak("0");
                                            sepRequest.setLakaLantas("0");
                                            sepRequest.setPenjamin("");
                                            sepRequest.setTglKejadian("");
                                            sepRequest.setKeterangan("");
                                            sepRequest.setSuplesi("0");
                                            sepRequest.setNoSepSuplesi("");
                                            sepRequest.setKdProvinsiLakaLantas("");
                                            sepRequest.setKdKecamatanLakaLantas("");
                                            sepRequest.setKdKabupatenLakaLantas("");
                                            sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp());
                                            sepRequest.setKodeDpjp(kodeDpjp);
                                            sepRequest.setNoTelp(getPasien.getNoTelp());
                                            sepRequest.setUserPembuatSep(userLogin);

                                            SepResponse response = new SepResponse();

                                            try {
                                                response = bpjsBo.insertSepBpjs(sepRequest, branchId);
                                            } catch (Exception e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (response.getNoSep() != null) {

                                                genNoSep = response.getNoSep();

                                                logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                                                KlaimRequest klaimRequest = new KlaimRequest();
                                                klaimRequest.setNoSep(genNoSep);
                                                klaimRequest.setNoKartu(obj.getString("no_bpjs"));
                                                klaimRequest.setNoRm(getPasien.getIdPasien());
                                                klaimRequest.setNamaPasien(getPasien.getNama());
                                                klaimRequest.setTglLahir(getPasien.getTglLahir());
                                                String jk = "";

                                                if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                                                    jk = "1";
                                                } else {
                                                    jk = "2";
                                                }

                                                klaimRequest.setGender(jk);
                                                klaimRequest.setCoderNik(getBranch.getCoderNik());

                                                KlaimResponse responseNewClaim = new KlaimResponse();
                                                try {
                                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, branchId);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                                tindakan.setIsIna("Y");

                                                try {
                                                    tindakanList = tindakanBo.getByCriteria(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                                    throw new GeneralBOException("Error when new tindakan", e);
                                                }

                                                BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));


                                                if (tindakanList.size() > 0) {
                                                    List<Tindakan> tindakans = new ArrayList<>();
                                                    for (Tindakan entity : tindakanList) {

                                                        if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObat = tarifRsObat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsAlkes = tarifRsAlkes.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKamar = tarifRsKamar.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsBmhp = tarifRsBmhp.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        Tindakan tin = new Tindakan();
                                                        tin.setIdTindakan(entity.getIdTindakan());
                                                        tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                                        tindakans.add(tin);
                                                    }

                                                    headerDetailCheckup.setTindakanList(tindakans);
                                                }

                                                if (responseNewClaim.getPatientId() != null) {
                                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                                    klaimDetailRequest.setNomorSep(genNoSep);
                                                    klaimDetailRequest.setNomorKartu(obj.getString("no_bpjs"));
                                                    klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                                    klaimDetailRequest.setTglPulang(time.toString());
                                                    if(rawatInap){
                                                        klaimDetailRequest.setJenisRawat("1");
                                                    }else{
                                                        klaimDetailRequest.setJenisRawat("2");
                                                    }
                                                    klaimDetailRequest.setKelasRawat("");
                                                    klaimDetailRequest.setAdlChronic("");
                                                    klaimDetailRequest.setIcuIndikator("");
                                                    klaimDetailRequest.setIcuLos("");
                                                    klaimDetailRequest.setVentilatorHour("");
                                                    klaimDetailRequest.setUpgradeClassInd("");
                                                    klaimDetailRequest.setUpgradeClassClass("");
                                                    klaimDetailRequest.setUpgradeClassLos("");
                                                    klaimDetailRequest.setAddPaymentPct("");
                                                    klaimDetailRequest.setBirthWeight("0");
                                                    klaimDetailRequest.setDischargeStatus("1");
                                                    klaimDetailRequest.setDiagnosa(obj.getString("id_diagnosa"));
                                                    klaimDetailRequest.setProcedure("");

                                                    //set tindakan untuk mendapatkan cover bpjs

                                                    klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                                    klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                                    klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                                    klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                                    klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                                    klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());
                                                    klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                                    klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                                    klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                                    klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                                    klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                                    klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());
                                                    klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                                    klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                                    klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                                    klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toString());
                                                    klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                                    klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                                    //end set tindakan

                                                    klaimDetailRequest.setTarifPoliEks("");
                                                    klaimDetailRequest.setNamaDokter(namaDokter);
                                                    klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif());
                                                    klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId());
                                                    klaimDetailRequest.setPayorCd(getBranch.getPayorCd());
                                                    klaimDetailRequest.setCobCd("");
                                                    klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                                    KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                                    try {
                                                        claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, branchId);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                                        Grouping1Response grouping1Response = new Grouping1Response();

                                                        try {
                                                            grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, branchId);
                                                        } catch (GeneralBOException e) {
                                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                        }

                                                        // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                                        if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                                            BigDecimal tarifCbg = new BigDecimal(0);
                                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {
                                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());

                                                                    //=====START SET TARIF BPJS DARI E-KLAIM====

                                                                    headerDetailCheckup.setTarifBpjs(tarifCbg);

                                                                    headerDetailCheckup.setKodeCbg(grouping1Response.getCbgCode());

                                                                    finalResponse.setStatus("success");

                                                                    //=====END SET TARIF BPJS DARI E-KLAIM=====
                                                                } else {
                                                                    finalResponse.setStatus("error");
                                                                    finalResponse.setMessage("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }
                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMessage("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return finalResponse;
                                                            }

                                                            // jika ada special cmg maka proses grouping stage 2
                                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                                    try {
                                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), branchId);
                                                                    } catch (GeneralBOException e) {
                                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);

                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMessage("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return finalResponse;
                                                        }

                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMessage("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                        return finalResponse;
                                                    }
                                                } else {
                                                    finalResponse.setStatus("error");
                                                    finalResponse.setMessage("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                    logger.error("[CheckupAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                    return finalResponse;
                                                }
                                            } else {
                                                finalResponse.setStatus("error");
                                                finalResponse.setMessage("Failed To Generate SEP " + response.getMessage());
                                                logger.error("[CheckupAction.saveAdd] Failed To Generate SEP " + response.getMessage());
                                                return finalResponse;
                                            }
                                        }
                                    }
                                } else {
                                    finalResponse.setStatus("error");
                                    finalResponse.setMessage("Tidak dapat menemukan PPK pelayanan Unit");
                                    logger.error("Found Error when search branch id");
                                    return finalResponse;
                                }
                            }
                        }

                        if("bpjs".equalsIgnoreCase(jenisPasien)){

                            headerDetailCheckup.setDiagnosa(obj.getString("id_diagnosa"));
                            headerDetailCheckup.setNamaDiagnosa(obj.getString("nama_diagnosa"));
                            headerDetailCheckup.setNoRujukan(obj.getString("no_rujukan"));
                            headerDetailCheckup.setPerujuk(obj.getString("perujuk"));
                            headerDetailCheckup.setNamaPerujuk(obj.getString("nama_perujuk"));
                            headerDetailCheckup.setIdPelayanan(obj.getString("id_pelayanan"));
                            headerDetailCheckup.setNoPpk(obj.getString("no_ppk"));
                            headerDetailCheckup.setIdKelas(obj.getString("id_kelas"));
                            headerDetailCheckup.setTglRujukan(obj.getString("tanggal_rujukan"));
                            headerDetailCheckup.setNoSep(genNoSep);

                            if(obj.getString("foto_surat") != null && !"".equalsIgnoreCase(obj.getString("foto_surat"))){
                                BASE64Decoder decoder = new BASE64Decoder();
                                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("foto_surat"));
                                logger.info("Decoded upload data : " + decodedBytes.length);
                                String fileName = detailCheckup.getNoCheckup()+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN+fileName;
                                logger.info("File save path : " + uploadFile);
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                                if (image == null) {
                                    logger.error("Buffered Image is null");
                                }else{
                                    File f = new File(uploadFile);
                                    // write the image
                                    ImageIO.write(image, "png", f);
                                    headerDetailCheckup.setSuratRujukan(fileName);
                                }
                            }

                        }

                        if("asuransi".equalsIgnoreCase(jenisPasien)){
                            headerDetailCheckup.setIdAsuransi(obj.getString("id_asuransi"));
                            headerDetailCheckup.setNoKartuAsuransi(obj.getString("no_kartu"));
                            headerDetailCheckup.setCoverBiaya(new BigDecimal(obj.getString("cover_biaya")));
                        }

                        if("umum".equalsIgnoreCase(jenisPasien)){
                            headerDetailCheckup.setMetodePembayaran(obj.getString("metode_pembayaran"));
                            headerDetailCheckup.setJumlahUangMuka(new BigInteger(obj.getString("uang_muka")));
                        }

                        headerDetailCheckup.setIdJenisPeriksaPasien(jenisPasien);
                        headerDetailCheckup.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                        headerDetailCheckup.setNoCheckup(detailCheckup.getNoCheckup());
                        headerDetailCheckup.setCreatedDate(time);
                        headerDetailCheckup.setCreatedWho(userLogin);
                        headerDetailCheckup.setLastUpdate(time);
                        headerDetailCheckup.setLastUpdateWho(userLogin);
                        headerDetailCheckup.setBranchId(branchId);
                        headerDetailCheckup.setRawatInap(rawatInap);
                        headerDetailCheckup.setIdRawatInap(idRawatInap);

                        try {
                            finalResponse = daftarUlangBo.saveDaftarUlang(headerDetailCheckup);
                        } catch (GeneralBOException e) {
                            finalResponse.setStatus("error");
                            finalResponse.setMessage("Error when saving add new rawat inap " + e.getMessage());
                            logger.error("[CheckupDetailAction.rujukRawatInap] Error when saving add new detail poli, ", e);
                        }
                    }
                }
            }
        }

        return finalResponse;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setDaftarUlangBoProxy(DaftarUlangBo daftarUlangBoProxy) {
        this.daftarUlangBoProxy = daftarUlangBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
