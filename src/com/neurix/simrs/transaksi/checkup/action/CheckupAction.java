package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.bo.impl.EklaimBoImpl;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.*;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.CheckupAlergi;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsCheckupAlergiEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.ImSimrsSkorResikoJatuhEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.ResikoJatuh;
import com.neurix.simrs.transaksi.resikojatuh.model.ResikoJatuhResponse;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.method.P;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckupAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupAction.class);
    private CheckupBo checkupBoProxy;
    private PelayananBo pelayananBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private DokterBo dokterBoProxy;
    private PasienBo pasienBoProxy;
    private BpjsBo bpjsBoProxy;
    private EklaimBo eklaimBoProxy;
    private TindakanRawatBo tindakanRawatBoProxy;
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private TindakanBo tindakanBoProxy;

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public void setDiagnosaRawatBoProxy(DiagnosaRawatBo diagnosaRawatBoProxy) {
        this.diagnosaRawatBoProxy = diagnosaRawatBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    private List<JenisPriksaPasien> listOfJenisPriksaPasien = new ArrayList<>();
    private List<JenisPriksaPasien> listOfJenisPriksaNotBpjs = new ArrayList<>();
    private List<Pelayanan> listOfPelayanan = new ArrayList<>();
    private List<Pelayanan> listOfApotek = new ArrayList<>();

    private HeaderCheckup headerCheckup;
    private String id;
    private String userId;
    private String idPasien;
    private String tipe;
    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    private File fileUploadDoc;
    private String fileUploadDocFileName;
    private String fileUploadDocContentType;

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Pelayanan> getListOfApotek() {
        return listOfApotek;
    }

    public void setListOfApotek(List<Pelayanan> listOfApotek) {
        this.listOfApotek = listOfApotek;
    }

    public File getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(File fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public String getFileUploadDocFileName() {
        return fileUploadDocFileName;
    }

    public void setFileUploadDocFileName(String fileUploadDocFileName) {
        this.fileUploadDocFileName = fileUploadDocFileName;
    }

    public String getFileUploadDocContentType() {
        return fileUploadDocContentType;
    }

    public void setFileUploadDocContentType(String fileUploadDocContentType) {
        this.fileUploadDocContentType = fileUploadDocContentType;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CheckupAction.logger = logger;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public JenisPriksaPasienBo getJenisPriksaPasienBoProxy() {
        return jenisPriksaPasienBoProxy;
    }

    public List<JenisPriksaPasien> getListOfJenisPriksaPasien() {
        return listOfJenisPriksaPasien;
    }

    public void setListOfJenisPriksaPasien(List<JenisPriksaPasien> listOfJenisPriksaPasien) {
        this.listOfJenisPriksaPasien = listOfJenisPriksaPasien;
    }

    public List<Pelayanan> getListOfPelayanan() {
        return listOfPelayanan;
    }

    public void setListOfPelayanan(List<Pelayanan> listOfPelayanan) {
        this.listOfPelayanan = listOfPelayanan;
    }

    public BpjsBo getBpjsBoProxy() {
        return bpjsBoProxy;
    }

    public void setBpjsBoProxy(BpjsBo bpjsBoProxy) {
        this.bpjsBoProxy = bpjsBoProxy;
    }

    public EklaimBo getEklaimBoProxy() {
        return eklaimBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    @Override
    public String add() {
        logger.info("[CheckupAction.add] start process >>>");

        // tipe transaksi
        String tipe = getTipe();
        setTipe(tipe);

        HeaderCheckup checkup = new HeaderCheckup();
        ImSimrsPasienEntity pasien = null;
        if (idPasien != null) {
            pasien = pasienBoProxy.getPasienByIdPasien(idPasien);
            checkup.setNoBpjs(pasien.getNoBpjs());
            checkup.setIdPasien(pasien.getIdPasien());
            checkup.setNoKtp(pasien.getNoKtp());
            checkup.setNama(pasien.getNama());
            checkup.setJenisKelamin(pasien.getJenisKelamin());
            checkup.setTempatLahir(pasien.getTempatLahir());
            checkup.setTglLahir(CommonUtil.dateUtiltoDateSql(pasien.getTglLahir()));
            checkup.setStTglLahir(CommonUtil.convertDateToString2(pasien.getTglLahir()));
            checkup.setAgama(pasien.getAgama());
            checkup.setProfesi(pasien.getProfesi());
            checkup.setSuku(pasien.getSuku());
            checkup.setJalan(pasien.getJalan());
            checkup.setProvinsiId(pasien.getProvinsi());
            checkup.setKotaId(pasien.getKota());
            checkup.setKecamatanId(pasien.getKecamatan());
            checkup.setDesaId(pasien.getDesaId());
            //checkup.setIdJenisPeriksaPasien(tipe);

        }
//        checkup.setIdJenisPeriksaPasien(tipe);
        checkup.setJenisTransaksi(tipe);
        setHeaderCheckup(checkup);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.add] end process <<<");

        return "init_add";
    }

    @Override
    public String edit() {

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderCheckup headerCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
                        setHeaderCheckup(headerCheckup);
                        break;
                    }
                }

            } else {
                setHeaderCheckup(new HeaderCheckup());
            }
        } else {
            setHeaderCheckup(new HeaderCheckup());
        }

        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {

        logger.info("[CheckupAction.view] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderCheckup> listOfResult = (List) session.getAttribute("listOfResult");
        List<HeaderDetailCheckup> listOfsearchDetailCheckup = new ArrayList();
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderCheckup headerCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(headerCheckup.getNoCheckup())) {
                        setHeaderCheckup(headerCheckup);
                        detailCheckup.setNoCheckup(headerCheckup.getNoCheckup());
                        break;
                    }
                }

            } else {
                setHeaderCheckup(new HeaderCheckup());
            }
        } else {
            setHeaderCheckup(new HeaderCheckup());
        }

        try {
            listOfsearchDetailCheckup = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.view] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        session.removeAttribute("listOfRiwayat");
        session.setAttribute("listOfRiwayat", listOfsearchDetailCheckup);

        logger.info("[CheckupAction.view] DATA YANG DI PARAM ID: " + getId());
        logger.info("[CheckupAction.view] end process <<<");

        return "init_view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        logger.info("[CheckupAction.search] start process >>>");

        HeaderCheckup headerCheckup = getHeaderCheckup();
        List<HeaderCheckup> listOfsearchHeaderCheckup = new ArrayList();

        try {
            listOfsearchHeaderCheckup = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = checkupBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CheckupAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[CheckupAction.initForm] end process >>>");
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveAdd() {

        logger.info("[CheckupAction.saveAdd] start process >>>");

        HeaderCheckup checkup = getHeaderCheckup();
        String genNoSep = "";
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String noCheckup = "CKP" + checkupBoProxy.getNextHeaderId();
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        //jika bpjs
        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

            List<Pasien> pasienList = new ArrayList<>();
            Pasien pasien = new Pasien();
            pasien.setIdPasien(checkup.getIdPasien());
            pasien.setFlag("Y");

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                Long logId = null;
                logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when search id pasien, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            if (pasienList.size() > 0) {

                Pasien getPasien = pasienList.get(0);

                SepRequest sepRequest = new SepRequest();
                sepRequest.setNoKartu(getPasien.getNoBpjs());
                sepRequest.setTglSep(dateToday);
                sepRequest.setPpkPelayanan("1311R003");
                sepRequest.setJnsPelayanan("2");
                sepRequest.setKlsRawat("3");
                sepRequest.setNoMr("123456");
                sepRequest.setAsalRujukan("1");
                sepRequest.setTglRujukan(checkup.getTglRujukan());
                sepRequest.setNoRujukan(checkup.getNoRujukan());
                sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                sepRequest.setCatatan("test");
                sepRequest.setDiagAwal("I63");
                sepRequest.setPoliTujuan("IGD");
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
                sepRequest.setNoSuratSkdp("000002");
                sepRequest.setKodeDpjp("31661");
                sepRequest.setNoTelp("081919999");
                sepRequest.setUserPembuatSep("Coba Ws");

                SepResponse response = new SepResponse();

                try {
                    response = bpjsBoProxy.insertSepBpjs(sepRequest, "RS01");
                } catch (Exception e) {
                    Long logId = null;
                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when insert SEP.\n" + e.getMessage());
                    return ERROR;
                }

                if (response.getNoSep() != null) {

                    genNoSep = response.getNoSep();
                    logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                    KlaimRequest klaimRequest = new KlaimRequest();
                    klaimRequest.setNoSep(genNoSep);
                    klaimRequest.setNoKartu(getPasien.getNoBpjs());
                    klaimRequest.setNoRm(getPasien.getIdPasien());
                    klaimRequest.setNamaPasien(getPasien.getNama());
                    klaimRequest.setTglLahir(getPasien.getTglLahir());
                    klaimRequest.setGender(getPasien.getJenisKelamin());

                    KlaimResponse responseNewClaim = new KlaimResponse();
                    try {
                        responseNewClaim = eklaimBoProxy.insertNewClaimEklaim(klaimRequest, userArea);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                        return ERROR;
                    }

                    List<Tindakan> tindakanList = new ArrayList<>();
                    Tindakan tindakan = new Tindakan();
                    tindakan.setIdTindakan("03");

                    try {
                        tindakanList = tindakanBoProxy.getByCriteria(tindakan);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when search tindakan, please inform to your admin.\n" + e.getMessage());
                        return ERROR;
                    }

                    if(tindakanList.size() > 0){
                        tindakan = tindakanList.get(0);
                    }

                    if (responseNewClaim.getPatientId() != null) {
                        KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                        klaimDetailRequest.setNomorSep(genNoSep);
                        klaimDetailRequest.setNomorKartu(getPasien.getNoKtp());
                        klaimDetailRequest.setTglMasuk(updateTime.toString());
                        klaimDetailRequest.setTglPulang(updateTime.toString());
                        klaimDetailRequest.setJenisRawat("1");
                        klaimDetailRequest.setKelasRawat("2");
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
                        klaimDetailRequest.setDiagnosa(checkup.getDiagnosa());
                        klaimDetailRequest.setProcedure("");

                        klaimDetailRequest.setTarifRsNonBedah("");
                        klaimDetailRequest.setTarifRsProsedurBedah("");

                        klaimDetailRequest.setTarifRsKonsultasi(tindakan.getTarifBpjs().toString());
                        klaimDetailRequest.setTarifRsTenagaAhli("");
                        klaimDetailRequest.setTarifRsKeperawatan("");
                        klaimDetailRequest.setTarifRsPenunjang("");
                        klaimDetailRequest.setTarifRsRadiologi("");
                        klaimDetailRequest.setTarifRsLaboratorium("");
                        klaimDetailRequest.setTarifRsPelayananDarah("");
                        klaimDetailRequest.setTarifRsRehabilitasi("");
                        klaimDetailRequest.setTarifRsKamar("");
                        klaimDetailRequest.setTarifRsRawatIntensif("");
                        klaimDetailRequest.setTarifRsObat("");
                        klaimDetailRequest.setTarifRsObatKronis("");
                        klaimDetailRequest.setTarifRsObatKemoterapi("");
                        klaimDetailRequest.setTarifRsAlkes("");
                        klaimDetailRequest.setTarifRsBmhp("");
                        klaimDetailRequest.setTarifRsSewaAlat("");

                        List<Dokter> dokterList = new ArrayList<>();
                        Dokter dokter = new Dokter();
                        dokter.setIdDokter(checkup.getIdDokter());
                        dokter.setFlag("Y");
                        try {
                            dokterList = dokterBoProxy.getByCriteria(dokter);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                            return ERROR;
                        }

                        String namaDokter = "";
                        if (dokterList.size() > 0) {
                            namaDokter = dokterList.get(0).getNamaDokter();
                        }

                        klaimDetailRequest.setTarifPoliEks("");
                        klaimDetailRequest.setNamaDokter(namaDokter);
                        klaimDetailRequest.setKodeTarif("AP");
                        klaimDetailRequest.setTarifRsPayorId("3");
                        klaimDetailRequest.setPayorCd("JKN");
                        klaimDetailRequest.setCobCd("");
                        klaimDetailRequest.setCoderNik("123456");

                        KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                        try {
                            claimEklaimResponse = eklaimBoProxy.updateDataClaimEklaim(klaimDetailRequest, userArea);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                            return ERROR;
                        }

                        if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                            Grouping1Response grouping1Response = new Grouping1Response();

                            try {
                                grouping1Response = eklaimBoProxy.groupingStage1Eklaim(genNoSep, userArea);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                return ERROR;
                            }

                            // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                            if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                BigDecimal tarifCbg = new BigDecimal(0);
                                if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                    if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {
                                        tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());
                                    }
                                }

                                checkup.setTarifBpjs(tarifCbg);
                                // update tarif cbg
//                                updateInsertTarifBpjs(noCheckup, tarifCbg);

                                // jika ada special cmg maka proses grouping stage 2
                                if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                    for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                        Grouping2Response grouping2Response = new Grouping2Response();
                                        try {
                                            grouping2Response = eklaimBoProxy.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), userArea);
                                        } catch (GeneralBOException e) {
                                            Long logId = null;
                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                            return ERROR;
                                        }
                                    }
                                }
                            }

                        } else {
                            logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                            addActionError("Error, " + " Found problem when saving add data, please inform to your admin. update claim not success. \n" + claimEklaimResponse.getMessage());
                            return ERROR;
                        }
                    }
                }

            }
        }

        if (checkup.getDiagnosa() != null && !"".equalsIgnoreCase(checkup.getDiagnosa())
                && checkup.getNamaDiagnosa() != null && !"".equalsIgnoreCase(checkup.getNamaDiagnosa())) {
            //diagnosa ambil dari depan...
        } else {
            List<Diagnosa> diagnosaList = new ArrayList<>();
            Diagnosa diagnosaResult = new Diagnosa();

            Diagnosa diagnosa = new Diagnosa();
            diagnosa.setIdDiagnosa(checkup.getDiagnosa());

            try {
                diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
            } catch (GeneralBOException e) {
                logger.error("[DiagnosaRawatAction.saveDiagnosa] Error when search dec diagnosa by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!diagnosaList.isEmpty()) {
                diagnosaResult = diagnosaList.get(0);
                checkup.setNamaDiagnosa(diagnosaResult.getDescOfDiagnosa());
            }
        }

        try {

            try {
                JSONObject obj = new JSONObject(checkup.getAdmisi());
                checkup.setKetKeyakinan(obj.getString("keyakinan"));
                checkup.setBantuanBahasa(obj.getString("penerjemah"));
                checkup.setAlatBantu(obj.getString("alatBantu"));
                checkup.setAlergi(obj.getString("alergi"));
            }catch (JSONException e){
                logger.error("[CheckupAction.saveAdd] Error Convert json to data admisi.", e);
            }

            String tgl_lahir = checkup.getStTglLahir();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tgl_lahir);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                checkup.setTglLahir(sqlDate);
            } catch (ParseException e) {
                logger.error("[CheckupAction.saveAdd] Error Convert String Tgl Lahir to Date.", e);
            }

            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan("03");

            List<Tindakan> tindakans = new ArrayList<>();
            tindakans.add(tindakan);

            checkup.setNoCheckup(noCheckup);
            checkup.setBranchId(userArea);
            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");
            checkup.setStatusPeriksa("0");
            checkup.setNoSep(genNoSep);
            checkup.setTindakanList(tindakans);
            checkup.setUrlKtp(checkup.getUrlKtp());

            String fileName = "";
            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        fileName = "SURAT_RUJUK_" + checkup.getNoKtp() + "_" + this.fileUploadDocFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(fileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

            checkupBoProxy.saveAdd(checkup);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.saveAdd] end process >>>");
        return "success_add";

    }

    public String getComboJenisPeriksaPasien() {
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();

        try {
            lisJenisPeriksa = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboJenisPeriksaPasien] Error when get data for combo listOfJenisPriksaPasien", e);
            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
        }

        listOfJenisPriksaPasien.addAll(lisJenisPeriksa);
        return "init_add";
    }

    public List<JenisPriksaPasien> getComboJenisPeriksaPasienNotBpjs() {
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        try {
            lisJenisPeriksa = jenisPriksaPasienBo.getListJenisPeriksaNotBpjs(jenisPriksaPasien);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboJenisPeriksaPasienNotBpjs] Error when get data for combo listOfJenisPriksaPasien", e);
            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
        }

        return lisJenisPeriksa;
    }

    public List<JenisPriksaPasien> getComboJenisPeriksaPasienWithBpjs() {
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        try {
            lisJenisPeriksa = jenisPriksaPasienBo.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboJenisPeriksaPasienNotBpjs] Error when get data for combo listOfJenisPriksaPasien", e);
            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
        }

        return lisJenisPeriksa;
    }

    public String getComboPelayanan() {
        List<Pelayanan> pelayananList = new ArrayList<>();

        try {
            pelayananList = pelayananBoProxy.getListAllPelayanan();
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayanan.addAll(pelayananList);
        return "init_add";
    }

    public String getComboApotek() {
        List<Pelayanan> pelayananList = new ArrayList<>();

        try {
            pelayananList = pelayananBoProxy.getListApotek();
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfApotek.addAll(pelayananList);
        return "init_add";
    }

    public List<HeaderCheckup> listDataPasien(String noCheckup) {
        logger.info("[CheckupAction.listDataPasien] start process >>>");

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            headerCheckupList = checkupBo.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.listDataPasien] Error when searching detail pasien, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listDataPasien] end process >>>");
        return headerCheckupList;
    }

    public DiagnosaRawat getDiagnosaRawatPasien(String idDetail) {
        logger.info("[CheckupAction.getDiagnosaRawatPasien] start process >>>");
        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
        diagnosaRawat.setIdDetailCheckup(idDetail);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

        try {
            diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getDiagnosaRawatPasien] Error when searching diagnosa pasien, Found problem when searching data, please inform to your admin.", e);
        }

        if (diagnosaRawatList.size() > 0) {
            diagnosaRawat = diagnosaRawatList.get(0);
        }

        logger.info("[CheckupAction.getDiagnosaRawatPasien] end process >>>");
        return diagnosaRawat;
    }

    public List<HeaderDetailCheckup> listRiwayatPasien(String noCheckup) {
        logger.info("[CheckupAction.listRiwayatPasien] start process >>>");

        List<HeaderDetailCheckup> headerDetailCheckupList = new ArrayList<>();
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            headerDetailCheckupList = checkupDetailBo.getByCriteria(headerDetailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.listDataPasien] Error when searching detail pasien, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listRiwayatPasien] end process >>>");
        return headerDetailCheckupList;
    }

    public List<Dokter> listOfDokter(String idPelayanan) {
        logger.info("[CheckupAction.listOfDokter] start process >>>");

        List<Dokter> dokterList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");

        try {
            dokterList = dokterBo.getByIdPelayanan(idPelayanan, "");
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listOfDokter] end process >>>");
        return dokterList;
    }

    public String saveEdit() {

        logger.info("[CheckupAction.saveEdit] start process >>>");
        try {
            HeaderCheckup checkup = getHeaderCheckup();
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            String tgl_lahir = checkup.getStTglLahir();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tgl_lahir);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                checkup.setTglLahir(sqlDate);
            } catch (ParseException e) {

            }

            checkup.setBranchId(userArea);
            checkup.setLastUpdate(updateTime);
            checkup.setLastUpdateWho(userLogin);

            String fileName = "";
            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                        // file name
                        fileName = checkup.getNoKtp() + "_" + this.fileUploadFileName;

                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN;

                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, fileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlKtp(fileName);

                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

            checkupBoProxy.saveEdit(checkup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CheckupAction.saveEdit] end process >>>");
        return "search";

    }

    public AlertPasien initAlertPasien(String idPasien) {
        logger.info("[CheckupAction.getAlertPasien] start process >>>");

        AlertPasien alertPasien = new AlertPasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        String branchId = CommonUtil.userBranchLogin();
        try {
            alertPasien = checkupBo.getAlertPasien(idPasien, branchId);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.initAlertPasien] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getAlertPasien] end process <<<");
        return alertPasien;
    }

    public HeaderCheckup completeBpjs(String idBpjs) {
        logger.info("[CheckupAction.completeBpjs] start process >>>");

        HeaderCheckup result = new HeaderCheckup();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        try {
            result = checkupBo.completeBpjs(idBpjs, "RS01");
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.completeBpjs] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.completeBpjs] end process >>>");
        return result;
    }

    public String savePenunjangPasien(String tinggi, String beratBadan, String noCheckup) {
        logger.info("[CheckupAction.savePenunjangPasien] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);
        headerCheckup.setTinggi(tinggi);
        headerCheckup.setBerat(beratBadan);
        headerCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        headerCheckup.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            checkupBo.updatePenunjang(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.savePenunjangPasien] ERROR " + e.getMessage());
            return e.getMessage();
        }

        logger.info("[CheckupAction.savePenunjangPasien] end process <<<");
        return "success";
    }

    public List<ItSImrsCheckupAlergiEntity> getListAlergi(String noCheckup) {
        logger.info("[CheckupAction.getListAlergi] start process >>>");

        List<ItSImrsCheckupAlergiEntity> listAlergi = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            listAlergi = checkupBo.getListAlergi(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListAlergi] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListAlergi] end process <<<");
        return listAlergi;
    }

    public String saveAddAlergi(String alergi, String noCheckup) {
        logger.info("[CheckupAction.saveAddAlergi] start process >>>");

        CheckupAlergi checkupAlergi = new CheckupAlergi();
        checkupAlergi.setNoCheckup(noCheckup);
        checkupAlergi.setAlergi(alergi);
        checkupAlergi.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        checkupAlergi.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            checkupBo.saveAddAlergi(checkupAlergi);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.saveAddAlergi] ERROR " + e.getMessage());
            return e.getMessage();
        }

        logger.info("[CheckupAction.saveAddAlergi] end process <<<");
        return "success";
    }

    public String saveEditAlergi(String alergi, String idAlergi) {
        logger.info("[CheckupAction.saveEditAlergi] start process >>>");

        CheckupAlergi checkupAlergi = new CheckupAlergi();
        checkupAlergi.setIdAlergi(idAlergi);
        checkupAlergi.setAlergi(alergi);
        checkupAlergi.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        checkupAlergi.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            checkupBo.saveEditAlergi(checkupAlergi);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.saveEditAlergi] ERROR " + e.getMessage());
            return e.getMessage();
        }

        logger.info("[CheckupAction.saveEditAlergi] end process <<<");
        return "success";
    }

    public List<AlertPasien> listRekamMedic(String idPasien) {
        logger.info("[CheckupAction.getListRekamMedic] start process >>>");

        List<AlertPasien> alertPasienList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            alertPasienList = checkupBo.listOfRekamMedic(idPasien);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListRekamMedic] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListRekamMedic] end process <<<");
        return alertPasienList;
    }

    public String loginFinger() {
        logger.info("[CheckupAction.loginFinger] start process >>>");
        String result = "";


        logger.info("[CheckupAction.loginFinger] end process <<<");
        return result;
    }

    public List getListBpjsDiagnosaAwal(String query) {
        logger.info("[CheckupAction.getListBpjsDiagnosaAwal] start process >>>");

        String branchId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");

        List<DiagnosaResponse> diagnosaResponses = new ArrayList<>();
        try {
            diagnosaResponses = bpjsBo.getDiagnosaByAPIBpjs(query, branchId);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListBpjsDiagnosaAwal] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListBpjsDiagnosaAwal] end process <<<");
        return diagnosaResponses;
    }

    private void updateInsertTarifBpjs(String noCheckup, BigDecimal getJumlahTarif) {
        logger.info("[CheckupAction.updateInsertTarifBpjs] start process >>>");

        List<HeaderCheckup> headerCheckups = new ArrayList<>();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);
        headerCheckup.setFlag("Y");
        headerCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            headerCheckups = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListBpjsDiagnosaAwal] ERROR " + e.getMessage());
            addActionError("[CheckupAction.getListBpjsDiagnosaAwal] ERROR " + e.getMessage());
        }

        if (headerCheckups.size() > 0) {
            HeaderCheckup newHeaderCheckup = headerCheckups.get(0);
            newHeaderCheckup.setTarifBpjs(getJumlahTarif);

            try {
                checkupBoProxy.saveEdit(newHeaderCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.getListBpjsDiagnosaAwal] ERROR " + e.getMessage());
                addActionError("[CheckupAction.getListBpjsDiagnosaAwal] ERROR " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.updateInsertTarifBpjs] end process <<<");

    }

    public PesertaResponse checkStatusBpjs(String noBpjs) {

        logger.info("[CheckupAction.checkStatusBpjs] START process <<<");

        PesertaResponse response = new PesertaResponse();
        String unitId = CommonUtil.userBranchLogin();
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String tglSep = formatDate;
        logger.info("[CheckupAction.checkStatusBpjs] TGL        -->" + date);
        logger.info("[CheckupAction.checkStatusBpjs] TGL SEP    -->" + tglSep);
        logger.info("[CheckupAction.checkStatusBpjs] UnitID     -->" + unitId);


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");

        try {
            response = bpjsBo.GetPesertaBpjsByAPIBpjs(noBpjs, tglSep, unitId);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.checkStatusBpjs] ERROR " + e.getMessage());
            addActionError("[CheckupAction.checkStatusBpjs] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.checkStatusBpjs] END process <<<");
        return response;
    }

    public ItSimrsPemeriksaanFisikEntity getPemeriksaanFisikByNoCheckup(String noCheckup) {
        logger.info("[CheckupAction.getPemeriksaanFisikByNoCheckup] START process <<<");
        ItSimrsPemeriksaanFisikEntity pemeriksaanFisikEntity = new ItSimrsPemeriksaanFisikEntity();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            pemeriksaanFisikEntity = checkupBo.getEntityPemeriksaanFisikByNoCheckup(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getPemeriksaanFisikByNoCheckup] ERROR " + e.getMessage());
            addActionError("[CheckupAction.getPemeriksaanFisikByNoCheckup] ERROR " + e.getMessage());
        }
        logger.info("[CheckupAction.getPemeriksaanFisikByNoCheckup] END process <<<");
        return pemeriksaanFisikEntity;
    }

    public String savePemeriksaanFisik(String jsonParam) throws JSONException {
        logger.info("[CheckupAction.savePemeriksaanFisik] START process <<<");

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        JSONArray json = new JSONArray(jsonParam);
        PemeriksaanFisik pemeriksaanFisik = new PemeriksaanFisik();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            pemeriksaanFisik.setNoCheckup(obj.getString("nocheckup"));
            pemeriksaanFisik.setKepala(obj.getString("kepala"));
            pemeriksaanFisik.setMata(obj.getString("mata"));
            pemeriksaanFisik.setLeher(obj.getString("leher"));
            pemeriksaanFisik.setThorak(obj.getString("thorak"));
            pemeriksaanFisik.setThorakChor(obj.getString("thorakchor"));
            pemeriksaanFisik.setThorakPulmo(obj.getString("thorakpulmo"));
            pemeriksaanFisik.setAbdoman(obj.getString("abdomen"));
            pemeriksaanFisik.setExtrimitas(obj.getString("extrimitas"));
            pemeriksaanFisik.setTinggiBadan(obj.getString("tb"));
            pemeriksaanFisik.setBeratBadan(obj.getString("bb"));
            pemeriksaanFisik.setNadi(obj.getString("nadi"));
            pemeriksaanFisik.setRespirationRate(obj.getString("rr"));
            pemeriksaanFisik.setTekananDarah(obj.getString("td"));
            pemeriksaanFisik.setSuhu(obj.getString("suhu"));
//            pemeriksaanFisik.setTriase(obj.getString("triase"));

            pemeriksaanFisik.setFlag("Y");
            pemeriksaanFisik.setAction("C");
            pemeriksaanFisik.setCreatedDate(now);
            pemeriksaanFisik.setLastUpdate(now);
            pemeriksaanFisik.setCreatedWho(userLogin);
            pemeriksaanFisik.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            checkupBo.savePemeriksaanFisik(pemeriksaanFisik);
        } catch (GeneralBOException e) {
            return e.getMessage();
        }

        logger.info("[CheckupAction.savePemeriksaanFisik] END process <<<");
        return "success";
    }

    public ResikoJatuhResponse getListResikoJatuh(String noCheckup, String tglLahir) {
        logger.info("[CheckupAction.getListResikoJatuh] START process <<<");

        ResikoJatuhResponse response = new ResikoJatuhResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        LocalDate now = LocalDate.now();
        LocalDate birthDay = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDay = LocalDate.parse(tglLahir, formatter);

        Integer umur = new Integer(0);
        if ((birthDay != null) && (now != null)) {
            umur = new Integer(Period.between(birthDay, now).getYears());
        }

        ResikoJatuh resikoJatuh = new ResikoJatuh();
        resikoJatuh.setNoCheckup(noCheckup);
        resikoJatuh.setUmur(umur);

        try {
            response = checkupBo.getResikojatuh(resikoJatuh);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[CheckupAction.getListResikoJatuh] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListResikoJatuh] END process <<<");
        return response;
    }

    public List<ImSimrsSkorResikoJatuhEntity> getListResikoJatuh(String id) {
        logger.info("[CheckupAction.getListResikoJatuh] START process <<<");
        List<ImSimrsSkorResikoJatuhEntity> skors = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            skors = checkupBo.getListSkorResikoByIdParameter(id);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListResikoJatuh] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListResikoJatuh] END process <<<");
        return skors;
    }

    public List<ItSimrsRencanaRawatEntity> getListRencanaRawat(String noCheckup, String idDetailCheckup, String kategoriRawat) {
        logger.info("[CheckupAction.getListRencanaRawat] START process <<<");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        List<ItSimrsRencanaRawatEntity> rencanaRawatEntities = new ArrayList<>();
        try {

        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListRencanaRawat] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getListRencanaRawat] END process <<<");
        return rencanaRawatEntities;
    }

    public ItSimrsDataPsikososialEntity getPsikososial(String noCheckup) {
        logger.info("[CheckupAction.getPsikososial] START process <<<");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        ItSimrsDataPsikososialEntity psikososialEntity = new ItSimrsDataPsikososialEntity();

        try {

        } catch (HibernateException e) {
            logger.error("[CheckupAction.getPsikososial] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getPsikososial] END process <<<");
        return psikososialEntity;
    }

    public RujukanResponse checkSuratRujukan(String noRujukan, String jenisRujuk) {

        logger.info("[CheckupAction.checkSuratRujukan] START process <<<");

        RujukanResponse response = new RujukanResponse();
        String unitId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");

        try {
            response = bpjsBo.caraRujukanBerdasarNomorBpjs(noRujukan, jenisRujuk, unitId);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.checkSuratRujukan] ERROR " + e.getMessage());
            addActionError("[CheckupAction.checkSuratRujukan] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.checkSuratRujukan] END process <<<");
        return response;
    }
}