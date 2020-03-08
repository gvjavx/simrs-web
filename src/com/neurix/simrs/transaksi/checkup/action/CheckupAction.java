package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.Branch;
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
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.bo.RegistrasiOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.*;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;

import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.*;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;

import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transfusi.model.ItSimrsTranfusiEntity;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private RegistrasiOnlineBo registrasiOnlineBoProxy;
    private AntrianOnlineBo antrianOnlineBoProxy;
    private BranchBo branchBoProxy;
    private BillingSystemBo billingSystemBoProxy;

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public void setRegistrasiOnlineBoProxy(RegistrasiOnlineBo registrasiOnlineBoProxy) {
        this.registrasiOnlineBoProxy = registrasiOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

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
    private List<Pelayanan> listOfPelayananIgd = new ArrayList<>();

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

    private String noCheckupOnline;

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
    }

    public List<Pelayanan> getListOfPelayananIgd() {
        return listOfPelayananIgd;
    }

    public void setListOfPelayananIgd(List<Pelayanan> listOfPelayananIgd) {
        this.listOfPelayananIgd = listOfPelayananIgd;
    }

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

        if (getIdPasien() != null && !"".equalsIgnoreCase(getIdPasien())) {
            String[] pasienFinger =idPasien.split(",");
            setTipe(pasienFinger[1]);
            tipe=pasienFinger[1];
            Pasien pasien = new Pasien();
            pasien.setIdPasien(pasienFinger[0]);
            List<Pasien> pasienList = new ArrayList<>();

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            }catch (GeneralBOException e){
                logger.error("FOund Error "+e.getMessage());
            }

            if(pasienList.size() > 0){

                pasien = pasienList.get(0);

                if(pasien.getIdPasien() != null){
                    checkup.setNoBpjs(pasien.getNoBpjs());
                    checkup.setIdPasien(pasien.getIdPasien());
                    checkup.setNoKtp(pasien.getNoKtp());
                    checkup.setNama(pasien.getNama());
                    checkup.setJenisKelamin(pasien.getJenisKelamin());
                    checkup.setTempatLahir(pasien.getTempatLahir());
                    checkup.setTglLahir(Date.valueOf(pasien.getTglLahir()));
                    checkup.setStTglLahir(pasien.getTglLahir());
                    checkup.setAgama(pasien.getAgama());
                    checkup.setProfesi(pasien.getProfesi());
                    checkup.setSuku(pasien.getSuku());
                    checkup.setJalan(pasien.getJalan());
                    checkup.setProvinsiId(pasien.getProvinsi());
                    checkup.setKotaId(pasien.getKota());
                    checkup.setKecamatanId(pasien.getKecamatan());
                    checkup.setDesaId(new BigInteger(pasien.getDesaId()));
                    checkup.setNamaDesa(pasien.getDesa());
                    checkup.setNamaKecamatan(pasien.getKecamatan());
                    checkup.setNamaKota(pasien.getKota());
                    checkup.setNamaProvinsi(pasien.getProvinsi());
                    checkup.setKecamatanId(pasien.getKecamatanId());
                    checkup.setKotaId(pasien.getKotaId());
                    checkup.setProvinsiId(pasien.getProvinsiId());
                    checkup.setUrlKtp(pasien.getUrlKtp());

                    List<HeaderCheckup> checkups = new ArrayList<>();
                    HeaderCheckup header = new HeaderCheckup();
                    header.setIdPasien(pasien.getIdPasien());

                    try {
                        checkups = checkupBoProxy.getByCriteria(header);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error when search pasien in traksaksi");
                    }

                    if (checkups.size() > 0) {
                        checkup.setJenisKunjungan("Lama");
                    } else {
                        checkup.setJenisKunjungan("Baru");
                    }
                }
            }

        }

        if (getNoCheckupOnline() != null && !"".equalsIgnoreCase(getNoCheckupOnline())) {

            RegistrasiOnline online = new RegistrasiOnline();

            List<RegistrasiOnline> registrasiOnlineList = new ArrayList<>();

            RegistrasiOnline registrasiOnline = new RegistrasiOnline();
            registrasiOnline.setNoCheckupOnline(noCheckupOnline);

            try {
                registrasiOnlineList = registrasiOnlineBoProxy.getByCriteria(registrasiOnline);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search no checkup online " + e.getMessage());
            }

            if (registrasiOnlineList.size() > 0) {

                online = registrasiOnlineList.get(0);

                AntianOnline antianOnline = new AntianOnline();
                antianOnline.setNoCheckupOnline(online.getNoCheckupOnline());
                List<AntianOnline> antianOnlineList = new ArrayList<>();

                try {
                    antianOnlineList = antrianOnlineBoProxy.getByCriteria(antianOnline);
                } catch (GeneralBOException e) {
                    logger.error("Founf Error when antrian online " + e.getMessage());
                }

                if (antianOnlineList.size() > 0) {

                    antianOnline = antianOnlineList.get(0);

                    checkup.setNama(online.getNama());
                    checkup.setIdPasien(online.getIdPasien());
                    checkup.setJenisKelamin(online.getJenisKelamin());
                    checkup.setNoKtp(online.getNoKtp());
                    checkup.setTempatLahir(online.getTempatLahir());
                    checkup.setTglLahir(online.getTglLahir());
                    checkup.setStTglLahir(online.getTglLahir().toString());
                    checkup.setJalan(online.getJalan());
                    checkup.setSuku(online.getSuku());
                    checkup.setProfesi(online.getProfesi());
                    checkup.setNoTelp(online.getNoTelp());
                    checkup.setIdJenisPeriksaPasien(online.getIdJenisPeriksaPasien());

                    Pasien pasien = new Pasien();
                    pasien.setIdPasien(idPasien);
                    List<Pasien> pasienList = new ArrayList<>();

                    try {
                        pasienList = pasienBoProxy.getByCriteria(pasien);
                    }catch (GeneralBOException e){
                        logger.error("FOund Error "+e.getMessage());
                    }

                    if(pasienList.size() > 0) {

                        pasien = pasienList.get(0);

                        if(pasien != null){
                            checkup.setUrlKtp(pasien.getUrlKtp());
                        }
                    }

                    checkup.setIdPelayanan(antianOnline.getIdPelayanan());
                    checkup.setIdDokter(antianOnline.getIdDokter());
                    checkup.setDesaId(online.getDesaId());
                    checkup.setKecamatanId(online.getKecamatanId());
                    checkup.setKotaId(online.getKotaId());
                    checkup.setProvinsiId(online.getProvinsiId());
                    checkup.setNamaDesa(online.getNamaDesa());
                    checkup.setNamaKecamatan(online.getNamaKecamatan());
                    checkup.setNamaKota(online.getNamaKota());
                    checkup.setNamaProvinsi(online.getNamaProvinsi());
                    checkup.setAgama(online.getAgama());
                    checkup.setNoCheckupOnline(online.getNoCheckupOnline());

                    List<HeaderCheckup> checkups = new ArrayList<>();
                    HeaderCheckup header = new HeaderCheckup();
                    header.setIdPasien(online.getIdPasien());

                    try {
                        checkups = checkupBoProxy.getByCriteria(header);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error when search pasien in traksaksi");
                    }

                    if (checkups.size() > 0) {
                        checkup.setJenisKunjungan("Lama");
                    } else {
                        checkup.setJenisKunjungan("Baru");
                    }

                    checkup.setIsOnline("Y");
                    checkup.setTglAntian(antianOnline.getLastUpdate());
                }
            }

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
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        HeaderCheckup checkup = new HeaderCheckup();
        checkup.setStTglFrom(tglToday);
        checkup.setGetStTglTo(tglToday);
        setHeaderCheckup(checkup);
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
            List<Branch> branchList = new ArrayList<>();
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

            Branch branch = new Branch();
            branch.setBranchId(userArea);
            branch.setFlag("Y");

            try {
                branchList = branchBoProxy.getByCriteria(branch);
            } catch (GeneralBOException e) {
                Long logId = null;
                logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when search id pasien, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            Branch getBranch = new Branch();

            if (branchList.size() > 0) {
                getBranch = branchList.get(0);

                if (getBranch.getPpkPelayanan() != null) {


                    if (pasienList.size() > 0) {

                        Pasien getPasien = pasienList.get(0);

                        SepRequest sepRequest = new SepRequest();
                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                        sepRequest.setTglSep(dateToday);
                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                        sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
                        sepRequest.setKlsRawat(checkup.getKelasPasien());//kelas rawat dari bpjs
                        sepRequest.setNoMr(checkup.getIdPasien());//id pasien
                        sepRequest.setAsalRujukan("1");//
                        sepRequest.setTglRujukan(checkup.getTglRujukan());
                        sepRequest.setNoRujukan(checkup.getNoRujukan());
                        sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                        sepRequest.setCatatan("");
                        sepRequest.setDiagAwal(checkup.getDiagnosa());
                        sepRequest.setPoliTujuan(checkup.getIdPelayananBpjs());
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
                        sepRequest.setKodeDpjp("31661");
                        sepRequest.setNoTelp(getPasien.getNoTelp());
                        sepRequest.setUserPembuatSep(userLogin);

                        SepResponse response = new SepResponse();

                        try {
                            response = bpjsBoProxy.insertSepBpjs(sepRequest, userArea);
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

                            String jk = "";

                            if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                                jk = "1";
                            } else {
                                jk = "2";
                            }
                            klaimRequest.setGender(jk);

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

                            if (tindakanList.size() > 0) {
                                tindakan = tindakanList.get(0);
                            }

                            if (responseNewClaim.getPatientId() != null) {
                                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                klaimDetailRequest.setNomorSep(genNoSep);
                                klaimDetailRequest.setNomorKartu(getPasien.getNoKtp());
                                klaimDetailRequest.setTglMasuk(updateTime.toString());
                                klaimDetailRequest.setTglPulang(updateTime.toString());
                                klaimDetailRequest.setJenisRawat("2");
                                klaimDetailRequest.setKelasRawat(checkup.getKelasPasien());
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
                                klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif());
                                klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId());
                                klaimDetailRequest.setPayorCd(getBranch.getPayorCd());
                                klaimDetailRequest.setCobCd("");
                                klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

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

                                                //=====kode CBG INA=======
                                                checkup.setKodeCbg(grouping1Response.getCbgCode());
                                            }
                                        }

                                        //======START SET TARIF BPJS=========

                                        checkup.setTarifBpjs(tarifCbg);

                                        //======END SET TARIF BPJS=========


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
                        }else{
                            addActionError("Error, " + "Insert SEP failed");
                            return ERROR;
                        }

                    }

                } else {
                    addActionError("Error, " + "PPK Pelayanan Tidak Ada");
                    return ERROR;
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
                List<Asesmen> asesmenList = new ArrayList<>();
                if(checkup.getAdmisi() != null && !"".equalsIgnoreCase(checkup.getAdmisi())){
                    JSONArray json = new JSONArray(checkup.getAdmisi());
                    for (int i=0; i < json.length(); i++){
                        JSONObject obj = json.getJSONObject(i);
                        Asesmen asesmen = new Asesmen();
                        asesmen.setParameter(obj.getString("parameter"));
                        asesmen.setJawaban(obj.getString("jawaban"));
                        asesmen.setKeterangan(obj.getString("keterangan"));
                        asesmenList.add(asesmen);
                    }
                }
                checkup.setAsesmenList(asesmenList);
            } catch (JSONException e) {
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

            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        String fileName = this.fileUploadDocFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-"+dateFormater("MM")+dateFormater("yy")+"-"+fileNameReplace;
                                // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(newFileName);
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

    private String dateFormater(String type){
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

//    private String createJurnalUangMuka(String idPasien, String jumlah){
////        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
////        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
//
//        String transId = "01";
//
//        String noNota = "";
//        try {
//            noNota = billingSystemBoProxy.createInvoiceNumber(transId);
//        } catch (GeneralBOException e){
//            logger.error("[CheckupAction.createJurnalUangMuka] Error create uang muka, ", e);
//        }
//
//        Map hsCriteria = new HashMap();
//        hsCriteria.put("master_id", idPasien);
//        hsCriteria.put("no_nota", noNota);
//        hsCriteria.put("uang_muka", new BigDecimal(jumlah));
//
//        try {
//            billingSystemBoProxy.createJurnal(transId, hsCriteria, CommonUtil.userBranchLogin(), "Uang Muka "+idPasien, "Y", "");
//        } catch (GeneralBOException e){
//            logger.error("[CheckupAction.createJurnalUangMuka] Error create uang muka, ", e);
//        }
//
//        return noNota;
//    }

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
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("rawat_jalan");
        pelayanan.setBranchId(CommonUtil.userBranchLogin());

        try {
            pelayananList = pelayananBoProxy.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayanan.addAll(pelayananList);
        return "init_add";
    }

    public String getComboPelayananIgd() {

        List<Pelayanan> pelayananList = new ArrayList<>();
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("igd");
        pelayanan.setBranchId(CommonUtil.userBranchLogin());

        try {
            pelayananList = pelayananBoProxy.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayananIgd.addAll(pelayananList);
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

    public HeaderCheckup listDataPasien(String idDetailCheckup) {
        logger.info("[CheckupAction.listDataPasien] start process >>>");
//        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        HeaderCheckup headerCheckup = new HeaderCheckup();
//        headerCheckup.setNoCheckup(noCheckup);
//        headerCheckup.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            headerCheckup = checkupBo.getDataDetailPasien(idDetailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.listDataPasien] Error when searching detail pasien, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.listDataPasien] end process >>>");
        return headerCheckup;

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
        String branchId = CommonUtil.userBranchLogin();

        try {
            result = checkupBo.completeBpjs(idBpjs, branchId);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.completeBpjs] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupAction.completeBpjs] end process >>>");
        if(result != null){
            return result;
        }else{
            result.setStatusBpjs("error");
            return result;
        }
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

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setIdPasien(idPasien);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            alertPasienList = checkupBo.listOfRekamMedic(headerCheckup);
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

        Integer umur = new Integer(0);
        if (!"".equalsIgnoreCase(tglLahir)) {
            LocalDate now = LocalDate.now();
            LocalDate birthDay = null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            birthDay = LocalDate.parse(tglLahir, formatter);

            if ((birthDay != null) && (now != null)) {
                umur = new Integer(Period.between(birthDay, now).getYears());
            }
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
            rencanaRawatEntities = checkupBo.getListRencanaRawat(noCheckup, idDetailCheckup, kategoriRawat);
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
            psikososialEntity = checkupBo.getDataPsikososial(noCheckup);
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

    public CrudResponse saveRencanaRawat(String noCheckup, String idDetail, String jsonString) throws JSONException {
        logger.info("[CheckupAction.saveRencanaRawat] START process <<<");

        CrudResponse crudResponse = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        JSONArray json = new JSONArray(jsonString);
        List<ItSimrsRencanaRawatEntity> rencanaRawatEntities = new ArrayList<>();
        ItSimrsRencanaRawatEntity rencanaRawatEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            rencanaRawatEntity = new ItSimrsRencanaRawatEntity();
            rencanaRawatEntity.setIdParameter(obj.getString("id"));
            rencanaRawatEntity.setNamaParameter(obj.getString("name"));
            rencanaRawatEntity.setCheck(obj.getString("val"));

            rencanaRawatEntity.setFlag("Y");
            rencanaRawatEntity.setAction("C");
            rencanaRawatEntity.setCreatedDate(now);
            rencanaRawatEntity.setLastUpdate(now);
            rencanaRawatEntity.setCreatedWho(userLogin);
            rencanaRawatEntity.setLastUpdateWho(userLogin);
            rencanaRawatEntities.add(rencanaRawatEntity);
        }


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");


        try {
            checkupBo.saveRencanaRawat(noCheckup, idDetail, rencanaRawatEntities);
            crudResponse.setStatus("success");
        } catch (GeneralBOException e) {
            crudResponse.setStatus("error");
            crudResponse.setMsg("CheckupAction.saveRencanaRawat] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.saveRencanaRawat] END process <<<");
        return crudResponse;
    }

    public CrudResponse saveResikoJatuh(String noCheckup, String jsonString) throws JSONException {
        logger.info("[CheckupAction.saveResikoJatuh] START process <<<");

        CrudResponse crudResponse = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        JSONArray json = new JSONArray(jsonString);
        List<ItSImrsResikoJatuhEntity> resikoJatuhEntities = new ArrayList<>();
        ItSImrsResikoJatuhEntity resikoJatuhEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            resikoJatuhEntity = new ItSImrsResikoJatuhEntity();
            resikoJatuhEntity.setIdParameter(obj.getString("id"));
            resikoJatuhEntity.setNamaParameter(obj.getString("name"));
            resikoJatuhEntity.setSkor(Integer.valueOf(obj.getString("val")));
            resikoJatuhEntity.setIdKategori(obj.getString("kat"));

            resikoJatuhEntity.setFlag("Y");
            resikoJatuhEntity.setAction("C");
            resikoJatuhEntity.setCreatedDate(now);
            resikoJatuhEntity.setLastUpdate(now);
            resikoJatuhEntity.setCreatedWho(userLogin);
            resikoJatuhEntity.setLastUpdateWho(userLogin);
            resikoJatuhEntities.add(resikoJatuhEntity);
        }


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");


        try {
            checkupBo.saveResikoJatuh(noCheckup, resikoJatuhEntities);
            crudResponse.setStatus("success");
        } catch (GeneralBOException e) {
            crudResponse.setStatus("error");
            crudResponse.setMsg("CheckupAction.saveResikoJatuh] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.saveResikoJatuh] END process <<<");
        return crudResponse;
    }

    public String getSumResikoJatuh(String noCheckup, String idKategori) {
        logger.info("[CheckupAction.getSumResikoJatuh] START process <<<");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        String skor = "";

        try {
            skor = checkupBo.getSumResikoJatuh(noCheckup, idKategori);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getSumResikoJatuh] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getSumResikoJatuh] END process <<<");
        return skor;
    }

    public ImSimrsKategoriResikoJatuhEntity getKategoriResiko(String tgllahir) {
        logger.info("[CheckupAction.getKategoriResiko] START process <<<");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        LocalDate now = LocalDate.now();
        LocalDate birthDay = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDay = LocalDate.parse(tgllahir, formatter);

        Integer umur = new Integer(0);
        if ((birthDay != null) && (now != null)) {
            umur = new Integer(Period.between(birthDay, now).getYears());
        }

        ImSimrsKategoriResikoJatuhEntity resikoJatuhEntity = new ImSimrsKategoriResikoJatuhEntity();

        try {
            resikoJatuhEntity = checkupBo.getKategoriResikoJatuh(umur);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getKategoriResiko] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getKategoriResiko] END process <<<");
        return resikoJatuhEntity;
    }

    public CrudResponse saveDataPsikososial(String noCheckup, String jsonString) throws JSONException {
        logger.info("[CheckupAction.getKategoriResiko] START process >>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        CrudResponse crudResponse = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        JSONArray json = new JSONArray(jsonString);
        List<ItSimrsDataPsikososialEntity> psikososialEntities = new ArrayList<>();
        ItSimrsDataPsikososialEntity psikososialEntity = new ItSimrsDataPsikososialEntity();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            psikososialEntity.setNoCheckup(noCheckup);
            psikososialEntity.setKomunikasi(obj.getString("komunikasi"));
            psikososialEntity.setKemampuanBicara(obj.getString("kemampuanBicara"));
            psikososialEntity.setTahuTentangSakitNya(obj.getString("tahuTentangSakitNya"));
            psikososialEntity.setKonsepDiri(obj.getString("konsepDiri"));
            psikososialEntity.setPernahDirawat(obj.getString("pernahDirawat"));
            psikososialEntity.setObatDariRumah(obj.getString("obatDariRumah"));
            psikososialEntity.setNyeri(obj.getString("nyeri"));
            psikososialEntity.setIntensitasNyeri(Integer.valueOf(obj.getString("intensitasNyeri")));
            psikososialEntity.setJenisIntensitasNyeri(obj.getString("jenisIntensitasNyeri"));
            psikososialEntity.setNumericRatingScale(obj.getString("numericRatingScale"));
            psikososialEntity.setWongBakerPainScale(obj.getString("wongBakerPainScale"));

            psikososialEntity.setFlag("Y");
            psikososialEntity.setAction("C");
            psikososialEntity.setCreatedDate(now);
            psikososialEntity.setLastUpdate(now);
            psikososialEntity.setCreatedWho(userLogin);
            psikososialEntity.setLastUpdateWho(userLogin);
        }

        try {
            checkupBo.saveDataPsikososial(noCheckup, psikososialEntity);
            crudResponse.setStatus("success");
        } catch (GeneralBOException e) {
            crudResponse.setStatus("error");
            crudResponse.setMsg("CheckupAction.getKategoriResiko] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getKategoriResiko] END process <<<");
        return crudResponse;
    }

    public List<ItSimrsTranfusiEntity> getListTranfusi(String noCheckup) {
        logger.info("[CheckupAction.getKategoriResiko] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        List<ItSimrsTranfusiEntity> tranfusiEntities = new ArrayList<>();

        try {
            tranfusiEntities = checkupBo.getListTranfusi(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getKategoriResiko] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getKategoriResiko] END process <<<");
        return tranfusiEntities;
    }

    public List<ItSImrsPatrusEntity> getListPatrus(String noCheckup) {
        logger.info("[CheckupAction.getPatrus] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        List<ItSImrsPatrusEntity> patrusEntities = new ArrayList<>();

        try {
            patrusEntities = checkupBo.getDataPatrus(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getPatrus] ERROR " + e.getMessage());
        }

        logger.info("[CheckupAction.getPatrus] END process <<<");
        return patrusEntities;
    }

    public List<ItSimrsRekonsiliasiObatEntity> getListRekonsiliasiObat(String noCheckup) {
        logger.info("[CheckupAction.getListRekonsiliasiObat] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        List<ItSimrsRekonsiliasiObatEntity> rekonsiliasiObatEntities = new ArrayList<>();

        try {
            rekonsiliasiObatEntities = checkupBo.getListRekonsiliasiObat(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.getListRekonsiliasiObat] ERROR " + e.getMessage());
        }
        logger.info("[CheckupAction.getListRekonsiliasiObat] END process <<<");
        return rekonsiliasiObatEntities;
    }

    public CrudResponse saveRekonsilisasiObat(String noCheckup, String jsonString) throws JSONException {

        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        ItSimrsRekonsiliasiObatEntity rekonsiliasiObatEntity = new ItSimrsRekonsiliasiObatEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            rekonsiliasiObatEntity.setNoCheckup(noCheckup);
            rekonsiliasiObatEntity.setNamaObat(obj.getString("nama"));
            rekonsiliasiObatEntity.setBentuk(obj.getString("bentuk"));
            rekonsiliasiObatEntity.setDosis(obj.getString("dosis"));
            rekonsiliasiObatEntity.setFrekuensi(obj.getString("frekuensi"));
            rekonsiliasiObatEntity.setRute(obj.getString("rute"));
            rekonsiliasiObatEntity.setObatMasukFlag(obj.getString("obatmasuk"));
            rekonsiliasiObatEntity.setObatDariRumahFlag(obj.getString("obatrumah"));
            rekonsiliasiObatEntity.setSatuanDosis(obj.getString("satuan"));

            rekonsiliasiObatEntity.setFlag("Y");
            rekonsiliasiObatEntity.setAction("C");
            rekonsiliasiObatEntity.setCreatedDate(now);
            rekonsiliasiObatEntity.setLastUpdate(now);
            rekonsiliasiObatEntity.setCreatedWho(userLogin);
            rekonsiliasiObatEntity.setLastUpdateWho(userLogin);
        }

        try {
            checkupBo.saveRekonObat(noCheckup, rekonsiliasiObatEntity);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("CheckupAction.getKategoriResiko] ERROR " + e.getMessage());
        }

        return response;
    }

    public CrudResponse savePatrus(String noCheckup, String ket) {
        logger.info("[CheckupAction.savePatrus] START process >>>");

        CrudResponse crudResponse = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        ItSImrsPatrusEntity patrusEntity = new ItSImrsPatrusEntity();
        patrusEntity.setKetPatrus(ket);
        patrusEntity.setNoCheckup(noCheckup);
        patrusEntity.setFlag("Y");
        patrusEntity.setAction("C");
        patrusEntity.setLastUpdate(now);
        patrusEntity.setLastUpdateWho(userLogin);
        patrusEntity.setCreatedDate(now);
        patrusEntity.setCreatedWho(userLogin);

        crudResponse = checkupBo.savePatrus(patrusEntity);
        logger.info("[CheckupAction.savePatrus] END <<<");
        return crudResponse;
    }

    public CrudResponse saveTranfusi(String noCheckup, String ket, String cc) {
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsTranfusiEntity tranfusiEntity = new ItSimrsTranfusiEntity();
        tranfusiEntity.setKetTransfusi(ket);
        tranfusiEntity.setCc(cc);
        tranfusiEntity.setNoCheckup(noCheckup);
        tranfusiEntity.setFlag("Y");
        tranfusiEntity.setAction("C");
        tranfusiEntity.setLastUpdate(now);
        tranfusiEntity.setLastUpdateWho(userLogin);
        tranfusiEntity.setCreatedDate(now);
        tranfusiEntity.setCreatedWho(userLogin);

        response = checkupBo.saveTranfusi(tranfusiEntity);
        return response;
    }

    public List<HeaderCheckup> getListAntriaPasien(String branch, String poli) {
        List<HeaderCheckup> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            result = checkupBo.getListAntrian(branch, poli);
        } catch (GeneralBOException e) {
            logger.error("Foun Error" + e);
        }

        return result;
    }

    public List<HeaderCheckup> getListPeriksaPasien(String branch, String poli) {
        List<HeaderCheckup> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            result = checkupBo.getListPeriksa(branch, poli);
        } catch (GeneralBOException e) {
            logger.error("Foun Error" + e);
        }

        return result;
    }

    public List<HeaderCheckup> getListAntrianApotikPeriksa(String branch, String poli) {
        List<HeaderCheckup> result = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            result = checkupBo.getListAntrianApotikPeriksa(branch, poli);
        } catch (GeneralBOException e) {
            logger.error("Foun Error" + e);
        }
        return result;
    }

    public List<ImSimrsKategoriSkorRanapEntity> getListKategoriSkorRanapByHead(String head) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        return rawatInapBo.getListKategoriSkorRanapByHead(head);
    }

    public List<Area> getListComboArea() {
        List<Area> areaList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AreaBo areaBo = (AreaBo) ctx.getBean("areaBoProxy");
        Area area = new Area();
        area.setFlag("Y");

        try {
            areaList = areaBo.getByCriteria(area);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e);
        }
        return areaList;
    }

    public List<Branch> getListComboBranch(String areaId) {
        List<Branch> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        Branch branch = new Branch();
        branch.setAreaId(areaId);
        branch.setFlag("Y");

        try {
            branchList = branchBo.getByCriteria(branch);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e);
        }
        return branchList;
    }

    public List<Pelayanan> getListComboPelayanan(String branchId) {
        List<Pelayanan> pelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setBranchId(branchId);
        pelayanan.setFlag("Y");
        pelayanan.setTipePelayanan("rawat_jalan");

        try {
            pelayananList = pelayananBo.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e);
        }
        return pelayananList;
    }

    public Branch getDataBranch(String branchId) {
        List<Branch> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        Branch branch = new Branch();
        branch.setBranchId(branchId);
        branch.setFlag("Y");

        try {
            branchList = branchBo.getByCriteria(branch);
        } catch (GeneralBOException e) {
            logger.error("Found Error" + e);
        }

        if (branchList.size() > 0) {
            branch = branchList.get(0);
        }

        if ("RS01".equalsIgnoreCase(branchId)) {
            branch.setLogoBranch(ServletActionContext.getRequest().getContextPath() + CommonConstant.LOGO_RS01);
        } else if ("RS02".equalsIgnoreCase(branchId)) {
            branch.setLogoBranch(ServletActionContext.getRequest().getContextPath() + CommonConstant.LOGO_RS02);
        } else if ("RS03".equalsIgnoreCase(branchId)) {
            branch.setLogoBranch(ServletActionContext.getRequest().getContextPath() + CommonConstant.LOGO_RS03);
        }

        return branch;
    }


    public RegistrasiOnline getDetailAntrianOnline(String noCheckup) {

        RegistrasiOnline oline = new RegistrasiOnline();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RegistrasiOnlineBo registrasiOnlineBo = (RegistrasiOnlineBo) ctx.getBean("registrasiOnlineBoProxy");
        AntrianOnlineBo antrianOnlineBo = (AntrianOnlineBo) ctx.getBean("antrianOnlineBoProxy");

        RegistrasiOnline registrasiOnline = new RegistrasiOnline();

        List<RegistrasiOnline> registrasiOnlineList = new ArrayList<>();
        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)) {

            registrasiOnline.setNoCheckupOnline(noCheckup);

            try {
                registrasiOnlineList = registrasiOnlineBo.getByCriteria(registrasiOnline);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search no checkup online " + e.getMessage());
            }

            if (registrasiOnlineList.size() > 0) {

                oline = registrasiOnlineList.get(0);

                AntianOnline antianOnline = new AntianOnline();
                antianOnline.setNoCheckupOnline(oline.getNoCheckupOnline());
                List<AntianOnline> antianOnlineList = new ArrayList<>();

                try {
                    antianOnlineList = antrianOnlineBo.getByCriteria(antianOnline);
                } catch (GeneralBOException e) {
                    logger.error("Founf Error when antrian online " + e.getMessage());
                }

                if (antianOnlineList.size() > 0) {
                    antianOnline = antianOnlineList.get(0);

                    oline.setNamaPelayanan(antianOnline.getNamaPelayanan());
                    oline.setNamaDokter(antianOnline.getNamaDokter());
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(antianOnline.getLastUpdate());
                    oline.setTglDaftar(formatDate);
                    oline.setJamAwal(antianOnline.getJamAwal());
                    oline.setJamAkhir(antianOnline.getJamAkhir());
                    oline.setStTglCheckup(antianOnline.getTglCheckup());

                }
            }

        }

        return oline;
    }

    public CheckResponse aktivasiAntrianOnline(String noCheckupOnline) {
        CheckResponse response = new CheckResponse();

        RegistrasiOnline online = new RegistrasiOnline();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RegistrasiOnlineBo registrasiOnlineBo = (RegistrasiOnlineBo) ctx.getBean("registrasiOnlineBoProxy");
        AntrianOnlineBo antrianOnlineBo = (AntrianOnlineBo) ctx.getBean("antrianOnlineBoProxy");

        RegistrasiOnline registrasiOnline = new RegistrasiOnline();

        List<RegistrasiOnline> registrasiOnlineList = new ArrayList<>();
        if (noCheckupOnline != null && !"".equalsIgnoreCase(noCheckupOnline)) {

            registrasiOnline.setNoCheckupOnline(noCheckupOnline);

            try {
                registrasiOnlineList = registrasiOnlineBo.getByCriteria(registrasiOnline);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search no checkup online " + e.getMessage());
            }

            if (registrasiOnlineList.size() > 0) {

                online = registrasiOnlineList.get(0);

                AntianOnline antianOnline = new AntianOnline();
                antianOnline.setNoCheckupOnline(online.getNoCheckupOnline());
                List<AntianOnline> antianOnlineList = new ArrayList<>();

                try {
                    antianOnlineList = antrianOnlineBo.getByCriteria(antianOnline);
                } catch (GeneralBOException e) {
                    logger.error("Founf Error when antrian online " + e.getMessage());
                }

                if (antianOnlineList.size() > 0) {
                    antianOnline = antianOnlineList.get(0);


                    HeaderCheckup headerCheckup = new HeaderCheckup();
                    headerCheckup.setNama(online.getNama());
                    headerCheckup.setIdPasien(online.getIdPasien());
                    headerCheckup.setJenisKelamin(online.getJenisKelamin());
                    headerCheckup.setNoKtp(online.getNoKtp());
                    headerCheckup.setTempatLahir(online.getTempatLahir());
                    headerCheckup.setTglLahir(online.getTglLahir());
                    headerCheckup.setJalan(online.getJalan());
                    headerCheckup.setSuku(online.getSuku());
                    headerCheckup.setProfesi(online.getProfesi());
                    headerCheckup.setNoTelp(online.getNoTelp());
                    headerCheckup.setIdJenisPeriksaPasien(online.getIdJenisPeriksaPasien());
//                    headerCheckup.setUrlKtp(online.getUrlKtp());
                    headerCheckup.setIdPelayanan(antianOnline.getIdPelayanan());
                    headerCheckup.setIdDokter(antianOnline.getIdDokter());
                    headerCheckup.setTglAntian(antianOnline.getLastUpdate());
                    setHeaderCheckup(headerCheckup);
                    saveAdd();
                    response.setMessage("success");
                    response.setMessage("Berhasil");
                }
            }

        }
        return response;

    }

    public AlertPasien getDataCheckupPasien(String noCheckup) {

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        List<AlertPasien> alertPasienList = checkupBo.listOfRekamMedic(headerCheckup);
        if (alertPasienList.size() > 0) {
            return alertPasienList.get(0);
        }
        return new AlertPasien();
    }

    public RingkasanKeluarMasukRs getRingkasanKeluarMasuk(String noCheckup, String kategori) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        return checkupBo.getRingkasanKeluarMasuk(noCheckup, kategori);
    }

    public List<TindakanRawat> getListTindakanRawat(String noCheckup, String kategori) {
        String kat = "";
        if ("igd".equalsIgnoreCase(kategori))
            kat = "igd";
        if ("ri".equalsIgnoreCase(kategori))
            kat = "rawat_inap";
        if ("rj".equalsIgnoreCase(kategori))
            kat = "rawat_jalan";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        return checkupBo.getListTindakan(noCheckup, kat);
    }

    public List getListRekamMedicLama(String idPasien) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        return checkupBo.getListRekamMedicLama(idPasien);
    }

    public List getListUploadRekamMedic(String headId) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        return checkupBo.getListUploadRekamMedicLama(headId);
    }

    public List getListTindakanBpjs(String namaTindakan) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        return bpjsBo.GetTindakanByAPIBpjs(namaTindakan, branchId);
    }

    public List getListTindakanBpjsByIdDetailCheckup(String idDetailCheckup) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        return null;
    }
}