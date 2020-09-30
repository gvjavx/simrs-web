package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalPelayananDTO;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.bo.impl.EklaimBoImpl;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.*;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.obat.action.ObatAction;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekammedis.bo.RekamMedisPasienBo;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.master.tindakanicd9.bo.TindakanICD9Bo;
import com.neurix.simrs.master.tindakanicd9.model.TindakanICD9;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.bo.RegistrasiOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.*;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ObatKronis;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;

import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.*;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;

import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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
import java.sql.SQLException;
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
    private PermintaanResepBo permintaanResepBoProxy;
    private AsuransiBo asuransiBoProxy;

    public void setAsuransiBoProxy(AsuransiBo asuransiBoProxy) {
        this.asuransiBoProxy = asuransiBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

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
    private List<Asuransi> listOfAsuransi = new ArrayList<>();
    private List<Pelayanan> listOfPelayananPaket = new ArrayList<>();

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

    private File fileUploadPolisi;
    private String fileUploadPolisiFileName;
    private String fileUploadPolisiContentType;

    private String noCheckupOnline;

    private List<Pelayanan> listOfPelayananWithLab = new ArrayList<>();

    public List<Pelayanan> getListOfPelayananWithLab() {
        return listOfPelayananWithLab;
    }

    public void setListOfPelayananWithLab(List<Pelayanan> listOfPelayananWithLab) {
        this.listOfPelayananWithLab = listOfPelayananWithLab;
    }

    public File getFileUploadPolisi() {
        return fileUploadPolisi;
    }

    public void setFileUploadPolisi(File fileUploadPolisi) {
        this.fileUploadPolisi = fileUploadPolisi;
    }

    public String getFileUploadPolisiFileName() {
        return fileUploadPolisiFileName;
    }

    public void setFileUploadPolisiFileName(String fileUploadPolisiFileName) {
        this.fileUploadPolisiFileName = fileUploadPolisiFileName;
    }

    public String getFileUploadPolisiContentType() {
        return fileUploadPolisiContentType;
    }

    public void setFileUploadPolisiContentType(String fileUploadPolisiContentType) {
        this.fileUploadPolisiContentType = fileUploadPolisiContentType;
    }

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

    public List<Pelayanan> getListOfPelayananPaket() {
        return listOfPelayananPaket;
    }

    public void setListOfPelayananPaket(List<Pelayanan> listOfPelayananPaket) {
        this.listOfPelayananPaket = listOfPelayananPaket;
    }

    @Override
    public String add() {
        logger.info("[CheckupAction.add] start process >>>");

        // tipe transaksi
        String tipe = getTipe();
        setTipe(tipe);

        HeaderCheckup checkup = new HeaderCheckup();

        if (getIdPasien() != null && !"".equalsIgnoreCase(getIdPasien())) {
            String[] pasienFinger = idPasien.split(",");
            setTipe(pasienFinger[1]);
            tipe = pasienFinger[1];
            Pasien pasien = new Pasien();
            pasien.setIdPasien(pasienFinger[0]);
            List<Pasien> pasienList = new ArrayList<>();

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                logger.error("FOund Error " + e.getMessage());
            }

            if (pasienList.size() > 0) {

                pasien = pasienList.get(0);

                if (pasien.getIdPasien() != null) {
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
                    pasien.setIdPasien(online.getIdPasien());
                    List<Pasien> pasienList = new ArrayList<>();

                    try {
                        pasienList = pasienBoProxy.getByCriteria(pasien);
                    } catch (GeneralBOException e) {
                        logger.error("FOund Error " + e.getMessage());
                    }

                    if (pasienList.size() > 0) {

                        pasien = pasienList.get(0);

                        if (pasien != null) {
                            checkup.setUrlKtp(pasien.getUrlKtp());
                            checkup.setNoBpjs(pasien.getNoBpjs());
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
        RekananOps ops = new RekananOps();
        String genNoSep = "";
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String noCheckup = "CKP" + checkupBoProxy.getNextHeaderId();
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");

        //jika bpjs dan ptpn
        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) && "Y".equalsIgnoreCase(checkup.getIsRekananWithBpjs())) {

            List<Pasien> pasienList = new ArrayList<>();
            List<Branch> branchList = new ArrayList<>();
            Pasien pasien = new Pasien();
            pasien.setIdPasien(checkup.getIdPasien());
            pasien.setFlag("Y");

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.saveAdd] Error when search pasien id ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                throw new GeneralBOException("Error when pasien id", e);
            }

            Branch branch = new Branch();
            branch.setBranchId(userArea);
            branch.setFlag("Y");

            try {
                branchList = branchBoProxy.getByCriteria(branch);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.saveAdd] Error when search branch id ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                throw new GeneralBOException("Error when branch id ", e);
            }

            Branch getBranch = new Branch();

            if (branchList.size() > 0) {
                getBranch = branchList.get(0);

                if (getBranch.getPpkPelayanan() != null) {


                    if (pasienList.size() > 0) {

                        Pasien getPasien = pasienList.get(0);

                        String kodeDpjs = "";
                        String namaDokter = "";

                        List<Dokter> dokterList = new ArrayList<>();
                        Dokter dokter = new Dokter();
                        dokter.setIdDokter(checkup.getIdDokter());
                        dokter.setFlag("Y");

                        try {
                            dokterList = dokterBoProxy.getByCriteria(dokter);
                        } catch (GeneralBOException e) {
                            throw new GeneralBOException("Error when search idDokter " + e.getMessage());
                        }

                        if (dokterList.size() > 0) {
                            Dokter dok = dokterList.get(0);
                            kodeDpjs = dok.getKodeDpjp();
                            namaDokter = dok.getNamaDokter();
                        }

                        SepRequest sepRequest = new SepRequest();
                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                        sepRequest.setTglSep(updateTime.toString());
                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit // branch
                        sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan

                        sepRequest.setKlsRawat(checkup.getKelasPasien());//kelas rawat dari bpjs // checkup
                        sepRequest.setNoMr(checkup.getIdPasien());//id pasien // checkup
                        sepRequest.setAsalRujukan(checkup.getRujuk());//
                        sepRequest.setTglRujukan(checkup.getTglRujukan()); // checkup
                        sepRequest.setNoRujukan(checkup.getNoRujukan()); // checkup
                        sepRequest.setPpkRujukan(checkup.getNoPpkRujukan()); // checkup

                        sepRequest.setCatatan("");
                        sepRequest.setDiagAwal(checkup.getDiagnosa()); // checkup
                        sepRequest.setPoliTujuan(checkup.getIdPelayananBpjs()); // checkup
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
                        sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp()); // branch
                        sepRequest.setKodeDpjp(kodeDpjs);
                        sepRequest.setNoTelp(getPasien.getNoTelp()); // pasien
                        sepRequest.setUserPembuatSep(userLogin);

                        SepResponse response = new SepResponse();

                        try {
                            response = bpjsBoProxy.insertSepBpjs(sepRequest, userArea);
                        } catch (Exception e) {
                            logger.error("[CheckupAction.saveAdd] Error when insert SEP ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                            throw new GeneralBOException("Error when new insert SEP", e);
                        }

                        if (response.getNoSep() != null) {

                            genNoSep = response.getNoSep();

                            checkup.setNoSep(response.getNoSep());

                            logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                            KlaimRequest klaimRequest = new KlaimRequest();
                            klaimRequest.setNoSep(genNoSep);
                            klaimRequest.setNoKartu(getPasien.getNoBpjs()); // pasien
                            klaimRequest.setNoRm(getPasien.getIdPasien()); // pasien
                            klaimRequest.setNamaPasien(getPasien.getNama()); // pasien
                            klaimRequest.setTglLahir(getPasien.getTglLahir()); // pasien

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
                                responseNewClaim = eklaimBoProxy.insertNewClaimEklaim(klaimRequest, userArea);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when new claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new claim", e);
                            }

                            List<Tindakan> tindakanList = new ArrayList<>();
                            Tindakan tindakan = new Tindakan();
                            tindakan.setBranchId(CommonUtil.userBranchLogin());
                            tindakan.setIsIna("Y");

                            try {
                                tindakanList = tindakanBoProxy.getByCriteria(tindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new tindakan", e);
                            }

                            BigDecimal tarifRsProsedurNonBedah = new BigDecimal(0);
                            BigDecimal tarifRsTenagaAhli = new BigDecimal(0);
                            BigDecimal tarifRsRadiologi = new BigDecimal(0);
                            BigDecimal tarifRsRehabilitasi = new BigDecimal(0);
                            BigDecimal tarifRsObat = new BigDecimal(0);
                            BigDecimal tarifRsAlkes = new BigDecimal(0);

                            BigDecimal tarifRsProsedurBedah = new BigDecimal(0);
                            BigDecimal tarifRsKeperawatan = new BigDecimal(0);
                            BigDecimal tarifRsLaboratorium = new BigDecimal(0);
                            BigDecimal tarifRsKamar = new BigDecimal(0);
                            BigDecimal tarifRsObatKronis = new BigDecimal(0);
                            BigDecimal tarifRsBmhp = new BigDecimal(0);

                            BigDecimal tarifRsKonsultasi = new BigDecimal(0);
                            BigDecimal tarifRsPenunjang = new BigDecimal(0);
                            BigDecimal tarifRsPelayananDarah = new BigDecimal(0);
                            BigDecimal tarifRsRawatIntensif = new BigDecimal(0);
                            BigDecimal tarifRsObatKemoterapi = new BigDecimal(0);
                            BigDecimal tarifRsSewaAlat = new BigDecimal(0);

                            if ("rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
                                try {
                                    ops = rekananOpsBo.getDetailRekananOps(checkup.getIdAsuransi(), userArea);
                                } catch (GeneralBOException e) {
                                    throw new GeneralBOException("Tidak dapat mencari diskon rekanan...!");
                                }
                            }

                            if (tindakanList.size() > 0) {
                                List<Tindakan> tindakans = new ArrayList<>();
                                for (Tindakan entity : tindakanList) {

                                    BigDecimal tarif = new BigDecimal(entity.getTarifBpjs());

                                    if ("rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
                                        if (ops.getDiskon() != null) {
                                            tarif = new BigDecimal(entity.getTarifBpjs()).multiply(ops.getDiskon());
                                        } else {
                                            tarif = new BigDecimal(entity.getTarifBpjs());
                                        }
                                    }

                                    if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(tarif);
                                    }
                                    if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsTenagaAhli = tarifRsTenagaAhli.add(tarif);
                                    }
                                    if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRadiologi = tarifRsRadiologi.add(tarif);
                                    }
                                    if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRehabilitasi = tarifRsRehabilitasi.add(tarif);
                                    }
                                    if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObat = tarifRsObat.add(tarif);
                                    }
                                    if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsAlkes = tarifRsAlkes.add(tarif);
                                    }

                                    //--------------
                                    if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurBedah = tarifRsProsedurBedah.add(tarif);

                                    }
                                    if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKeperawatan = tarifRsKeperawatan.add(tarif);

                                    }
                                    if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsLaboratorium = tarifRsLaboratorium.add(tarif);

                                    }
                                    if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKamar = tarifRsKamar.add(tarif);

                                    }
                                    if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKronis = tarifRsObatKronis.add(tarif);

                                    }
                                    if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsBmhp = tarifRsBmhp.add(tarif);

                                    }

                                    //--------------
                                    if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKonsultasi = tarifRsKonsultasi.add(tarif);

                                    }
                                    if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPenunjang = tarifRsPenunjang.add(tarif);

                                    }
                                    if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPelayananDarah = tarifRsPelayananDarah.add(tarif);

                                    }
                                    if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRawatIntensif = tarifRsRawatIntensif.add(tarif);

                                    }
                                    if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(tarif);

                                    }
                                    if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsSewaAlat = tarifRsSewaAlat.add(tarif);

                                    }

                                    Tindakan tin = new Tindakan();
                                    tin.setIdTindakan(entity.getIdTindakan());
                                    tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                    tindakans.add(tin);
                                }
                                checkup.setTindakanList(tindakans);
                            }

                            if (responseNewClaim.getPatientId() != null) {

                                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                klaimDetailRequest.setNomorSep(genNoSep);
                                klaimDetailRequest.setNomorKartu(getPasien.getNoKtp()); // pasien
                                klaimDetailRequest.setTglMasuk(updateTime.toString());
                                klaimDetailRequest.setTglPulang(updateTime.toString());
                                klaimDetailRequest.setJenisRawat("2"); // checkup
                                klaimDetailRequest.setKelasRawat(checkup.getKelasPasien()); // checkup
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
                                klaimDetailRequest.setDiagnosa(checkup.getDiagnosa()); // checkup
                                klaimDetailRequest.setProcedure("");

                                //set tindakan untuk mendapatkan cover bpjs

                                klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsObat(tarifRsObat.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toBigInteger().toString());
                                klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toBigInteger().toString());

                                //end set tindakan

                                klaimDetailRequest.setTarifPoliEks("");
                                klaimDetailRequest.setNamaDokter(namaDokter);
                                klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif()); // branch
                                klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId()); // branch
                                klaimDetailRequest.setPayorCd(getBranch.getPayorCd()); // branch
                                klaimDetailRequest.setCobCd("");
                                klaimDetailRequest.setCoderNik(getBranch.getCoderNik()); // branch

                                KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                try {
                                    claimEklaimResponse = eklaimBoProxy.updateDataClaimEklaim(klaimDetailRequest, userArea);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupAction.saveAdd] Error when update claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                    throw new GeneralBOException("Error when update claim, [" + claimEklaimResponse.getMessage() + "]", e);
                                }

                                if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                    Grouping1Response grouping1Response = new Grouping1Response();

                                    try {
                                        grouping1Response = eklaimBoProxy.groupingStage1Eklaim(genNoSep, userArea);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                        throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]", e);
                                    }

                                    // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                    if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                        BigDecimal tarifCbg = new BigDecimal(0);
                                        if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                            if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());

                                                //=====kode CBG INA=======
                                                checkup.setKodeCbg(grouping1Response.getCbgCode());

                                                //======START SET TARIF BPJS=========

                                                checkup.setTarifBpjs(tarifCbg);

                                                //======END SET TARIF BPJS=========
                                            } else {
                                                logger.error("[CheckupAction.saveAdd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                                throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                            }
                                        } else {
                                            logger.error("[CheckupAction.saveAdd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                            throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                        }

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
                                    } else {
                                        logger.error("[CheckupAction.saveAdd] Error when get biaya cover BPJS, dengan kode diagnosa " + grouping1Response.getMessage());
                                        throw new GeneralBOException("Error when get biaya cover BPJS, dengan kode diagnosa " + checkup.getDiagnosa() + " [" + grouping1Response.getMessage() + "]");
                                    }

                                } else {
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                    throw new GeneralBOException("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                }
                            } else {
                                logger.error("[CheckupAction.saveAdd] Error when get Patien ID, " + responseNewClaim.getMsg());
                                throw new GeneralBOException("Error when get Patien ID, [" + responseNewClaim.getMsg() + "]");
                            }
                        } else {
                            logger.error("[CheckupAction.saveAdd] Error when generate SEP, " + response.getMessage());
                            throw new GeneralBOException("Error when generate SEP, [" + response.getMessage() + "]");
                        }

                    }

                } else {
                    logger.error("[CheckupAction.saveAdd] Error when search PPK pelayanan");
                    throw new GeneralBOException("Error when search PPK pelayanan");
                }
            }
        }

        try {

            try {
                List<Asesmen> asesmenList = new ArrayList<>();
                if (checkup.getAdmisi() != null && !"".equalsIgnoreCase(checkup.getAdmisi())) {
                    JSONArray json = new JSONArray(checkup.getAdmisi());
                    for (int i = 0; i < json.length(); i++) {
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

            checkup.setTglLahir(Date.valueOf(checkup.getStTglLahir()));
            checkup.setNoCheckup(noCheckup);
            checkup.setBranchId(userArea);
            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");
            checkup.setStatusPeriksa("0");

            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        String fileName = this.fileUploadDocFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
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
                            throw new GeneralBOException("Found Error when upload images rujukan " + e.getMessage());
                        }
                    }
                }
            }

            if (this.fileUploadPolisi != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadPolisiContentType)) {
                    if (this.fileUploadPolisi.length() <= 5242880 && this.fileUploadPolisi.length() > 0) {

                        // file name
                        String fileName = this.fileUploadPolisiFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-" + dateFormater("dd") + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadPolisi, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(newFileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                            throw new GeneralBOException("Found Error when upload images rujukan " + e.getMessage());
                        }
                    }
                }
            }

            checkupBoProxy.saveAdd(checkup);

        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
            throw new GeneralBOException("Found Error when adding item " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.saveAdd] end process >>>");
        return "success_add";

    }

    private String dateFormater(String type) {
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

    public List<JenisPriksaPasien> getComboJenisPeriksaByIdDetailCheckup(String jenis, String idDetail) {
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();
        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        try {
            lisJenisPeriksa = jenisPriksaPasienBo.getListJenisPeriksaByIdDetailCheckup(jenis, idDetail);
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

    public String getComboPelayananWithLab() {

        List<Pelayanan> pelayananList = new ArrayList<>();
        String tipe = getTipe();
        try {
            pelayananList = pelayananBoProxy.getListPelayananWithLab(tipe);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayananWithLab.addAll(pelayananList);
        return "init_add";
    }

    public List<Pelayanan> getListComboPoli(String branch) {

        List<Pelayanan> pelayananList = new ArrayList<>();
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("rawat_jalan");
        pelayanan.setBranchId(branch);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        try {
            pelayananList = pelayananBo.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return pelayananList;
    }

    public String getComboPelayananPaketPeriksa() {
        List<Pelayanan> pelayananList = new ArrayList<>();
        try {
            pelayananList = pelayananBoProxy.getListPelayananPaketPeriksa(CommonUtil.userBranchLogin());
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayananPaket.addAll(pelayananList);
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

    public List<Pelayanan> getListComboPelayananIgd(String branch) {

        List<Pelayanan> pelayananList = new ArrayList<>();
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("igd");
        pelayanan.setBranchId(branch);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        try {
            pelayananList = pelayananBo.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return pelayananList;
    }

    public String getComboApotekRi() {
        List<Pelayanan> pelayananList = new ArrayList<>();
        try {
            pelayananList = pelayananBoProxy.getListApotek(CommonUtil.userBranchLogin(), "apotek_ri");
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfApotek.addAll(pelayananList);
        return "init_add";
    }

    public String getComboApotek() {
        List<Pelayanan> pelayananList = new ArrayList<>();
        try {
            pelayananList = pelayananBoProxy.getListApotek(CommonUtil.userBranchLogin(), "apotek");
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfApotek.addAll(pelayananList);
        return "init_add";
    }

    public String getComboAllApotek() {
        List<Pelayanan> pelayananList = new ArrayList<>();
        try {
            pelayananList = pelayananBoProxy.getListApotek(CommonUtil.userBranchLogin(), "");
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfApotek.addAll(pelayananList);
        return "init_add";
    }

    public List<Pelayanan> getListComboApotek(String branch) {

        List<Pelayanan> pelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        try {
            pelayananList = pelayananBo.getListApotek(branch, "");
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return pelayananList;
    }

    public List<Pelayanan> getListComboGudang() {

        List<Pelayanan> pelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("gudang_obat");
        pelayanan.setBranchId(CommonUtil.userBranchLogin());

        try {
            pelayananList = pelayananBo.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return pelayananList;
    }

    public List<Pelayanan> getListComboGudangByBranch(String branchId) {

        List<Pelayanan> pelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan("gudang_obat");
        pelayanan.setBranchId(branchId);

        try {
            pelayananList = pelayananBo.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return pelayananList;
    }

    public HeaderCheckup listDataPasien(String idDetailCheckup) {
        logger.info("[CheckupAction.listDataPasien] start process >>>");
        HeaderCheckup headerCheckup = new HeaderCheckup();
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

    public List<JadwalPelayananDTO> listOfDokter(String idPelayanan, String notLike) {
        logger.info("[CheckupAction.listOfDokter] start process >>>");
        List<JadwalPelayananDTO> dokterList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
            try {
                dokterList = jadwalShiftKerjaBo.getListJadwalDokter(branchId, idPelayanan, notLike);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
            }
        }

        //        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
//            try {
//                dokterList = dokterBo.getDokterByPelayanan(idPelayanan, notLike);
//            } catch (GeneralBOException e) {
//                logger.error("[CheckupAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
//            }
//        }

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

        if (result != null) {
            return result;
        } else {
            result = new HeaderCheckup();
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

    public String saveAddAlergi(String alergi, String noCheckup, String jenis, String idPasien) {
        logger.info("[CheckupAction.saveAddAlergi] start process >>>");

        CheckupAlergi checkupAlergi = new CheckupAlergi();
        checkupAlergi.setNoCheckup(noCheckup);
        checkupAlergi.setAlergi(alergi);
        checkupAlergi.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        checkupAlergi.setLastUpdateWho(CommonUtil.userLogin());
        checkupAlergi.setJenis(jenis);
        checkupAlergi.setIdPasien(idPasien);

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

    public String saveEditAlergi(String alergi, String idAlergi, String jenis) {
        logger.info("[CheckupAction.saveEditAlergi] start process >>>");

        CheckupAlergi checkupAlergi = new CheckupAlergi();
        checkupAlergi.setIdAlergi(idAlergi);
        checkupAlergi.setAlergi(alergi);
        checkupAlergi.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        checkupAlergi.setLastUpdateWho(CommonUtil.userLogin());
        checkupAlergi.setJenis(jenis);

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
            logger.error("Found Error" + e);
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
            logger.error("Found Error" + e);
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
            logger.error("Found Error" + e);
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

    public ObatKronis findRiwayatObatKronis(String idPasien) {
        logger.info("[CheckupAction.findRiwayatObatKronis] Start >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        List<ObatKronis> obatKronisList = new ArrayList<>();
        ObatKronis obatKronis = new ObatKronis();

        try {
            obatKronisList = checkupBo.findRiwayatKronis(idPasien);
        } catch (GeneralBOException e) {
            logger.error("Error " + e.getMessage());
        }

        logger.info("[CheckupAction.findRiwayatObatKronis] End <<<");

        if (obatKronisList.size() > 0) {
            obatKronis = obatKronisList.get(0);
        }

        return obatKronis;
    }

    public List<TransaksiObatDetail> getListObatKronis(String idDetailCheckup, String idApproval) {
        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && idApproval != null && !"".equalsIgnoreCase(idApproval)) {
            try {
                transaksiObatDetails = checkupBo.getListObatKronis(idDetailCheckup, idApproval);
            } catch (GeneralBOException e) {
                logger.error("found error when search list obat kronis " + e.getMessage());
            }
        }
        return transaksiObatDetails;
    }

    public CrudResponse savePengambilanObatKronis(String idDetailCheckup, String jsonString, String pelayananTujuan) throws JSONException {

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        String noCheckup = "";
        String kodeDiagnosa = "";
        String stTotalBiayaObat = "";
        ItSimrsHeaderDetailCheckupEntity headerDetailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
        ItSimrsHeaderChekupEntity headerChekupEntity = new ItSimrsHeaderChekupEntity();
        ItSimrsDiagnosaRawatEntity diagnosaRawatEntity = new ItSimrsDiagnosaRawatEntity();
        ImSimrsPermintaanResepEntity permintaanResepEntity = new ImSimrsPermintaanResepEntity();
        List<ItSimrsDokterTeamEntity> dokterTeamEntityList = new ArrayList<>();
        List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities = new ArrayList<>();

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            List<ItSimrsHeaderDetailCheckupEntity> headerDetailCheckupEntities = new ArrayList<>();
            try {
                headerDetailCheckupEntities = checkupDetailBo.getListEntityByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
            }


            if (headerDetailCheckupEntities.size() > 0) {
                for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : headerDetailCheckupEntities) {
                    noCheckup = detailCheckupEntity.getNoCheckup();
                    headerDetailCheckupEntity = detailCheckupEntity;
                }
            }

            if (!"".equalsIgnoreCase(noCheckup)) {
                List<ItSimrsHeaderChekupEntity> checkupEntities = new ArrayList<>();
                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(noCheckup);

                try {
                    checkupEntities = checkupBo.getListEntityHeaderCheckup(headerCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
                }

                if (checkupEntities.size() > 0) {
                    headerChekupEntity = checkupEntities.get(0);
                }
            }

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
            diagnosaRawat.setOrderLastUpdate("Y");

            List<ItSimrsDiagnosaRawatEntity> diagnosaRawatEntities = new ArrayList<>();

            try {
                diagnosaRawatEntities = diagnosaRawatBo.getListEntityDiagnosaRawat(diagnosaRawat);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
            }

            if (diagnosaRawatEntities.size() > 0) {
                diagnosaRawatEntity = diagnosaRawatEntities.get(0);
            }

            // permintaan resep
            permintaanResepEntity.setTujuanPelayanan(pelayananTujuan);
            permintaanResepEntity.setIdPasien(headerChekupEntity.getIdPasien());
            permintaanResepEntity.setBranchId(headerChekupEntity.getBranchId());

            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(idDetailCheckup);

            try {
                dokterTeamEntityList = teamDokterBo.getListEntityTeamDokter(dokterTeam);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
            }

            BigDecimal totalBiaya = new BigDecimal(0);
            JSONArray json = new JSONArray(jsonString);
            ImtSimrsTransaksiObatDetailEntity obatDetail;
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                obatDetail = new ImtSimrsTransaksiObatDetailEntity();
                obatDetail.setIdObat(obj.getString("id_obat"));
                obatDetail.setJenisSatuan(obj.getString("jenis_satuan"));
                obatDetail.setQty(new BigInteger(obj.getString("qty")));
                obatDetail.setHariKronis(new Integer(obj.getString("hari_selanjutnya")));
                obatDetail.setIdTransaksiObatDetail(obj.getString("trans_id"));
                obatDetail.setKeterangan(obj.getString("keterangan"));
                obatDetail.setFlagKronisDiambil("Y");

                if (!"".equalsIgnoreCase(obatDetail.getIdTransaksiObatDetail())) {
                    try {
                        transaksiObatBo.saveEditFlagPengambilan(obatDetail.getIdTransaksiObatDetail());
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
                        response.setStatus("error");
                        response.setMsg("[CheckupAction.savePengambilanObatKronis] ERROR " + e);
                        return response;
                    }
                }

                if (!"".equalsIgnoreCase(obatDetail.getIdObat()) && obatDetail.getIdObat() != null) {

                    List<Obat> hargaObats = new ArrayList<>();

                    Obat obat = new Obat();
                    obat.setIdObat(obatDetail.getIdObat());
                    obat.setBranchId(headerChekupEntity.getBranchId());

                    try {
                        hargaObats = obatBo.getListHargaObat(obat);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
                    }

                    if (hargaObats.size() > 0) {
                        Obat hargaObat = hargaObats.get(0);
                        BigDecimal totalObat = hargaObat.getHargaJual().multiply(new BigDecimal(obatDetail.getQty()));
                        totalBiaya = totalBiaya.add(totalObat);
                    }
                }
                obatDetailEntities.add(obatDetail);
            }

            stTotalBiayaObat = totalBiaya.toString();
        }

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setIdDokter(dokterTeamEntityList.get(0).getIdDokter());
        headerCheckup.setDiagnosa(kodeDiagnosa);
        headerCheckup.setTotalBiaya(stTotalBiayaObat);

        HeaderCheckup createBpjs = new HeaderCheckup();

        createBpjs.setFlag("Y");
        createBpjs.setAction("C");
        createBpjs.setCreatedDate(time);
        createBpjs.setCreatedWho(userLogin);
        createBpjs.setLastUpdate(time);
        createBpjs.setLastUpdateWho(userLogin);

        try {
            checkupBo.savePengambilanObatKronis(createBpjs, headerChekupEntity, headerDetailCheckupEntity, diagnosaRawatEntity, permintaanResepEntity, dokterTeamEntityList, obatDetailEntities);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.savePengambilanObatKronis] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[CheckupAction.savePengambilanObatKronis] ERROR " + e);
        }


//        HeaderCheckup createBpjs =  createSepAndClaimForPengambilan(headerChekupEntity, headerCheckup);
//        if (createBpjs.getNoSep() != null && createBpjs.getTarifBpjs().compareTo(new BigDecimal(0)) == 1){
//
//            headerDetailCheckupEntity.setNoSep(createBpjs.getNoSep());
//            headerDetailCheckupEntity.setTarifBpjs(createBpjs.getTarifBpjs());
//
//            response.setStatus("success");
//        } else {
//            response.setStatus("error");
//            response.setMsg("Gagal Membuat No SEP atau Mendapatkan Cover BPJS");
//        }

        return response;
    }

    private HeaderCheckup createSepAndClaimForPengambilan(ItSimrsHeaderChekupEntity checkupEntity, HeaderCheckup bean) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");

        HeaderCheckup checkup = new HeaderCheckup();

        String userArea = CommonUtil.userBranchLogin();
        Date dateNow = new Date(System.currentTimeMillis());
        String dateToday = dateNow.toString();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());

        List<Pasien> pasienList = new ArrayList<>();
        List<Branch> branchList = new ArrayList<>();
        Pasien pasien = new Pasien();
        pasien.setIdPasien(checkupEntity.getIdPasien());
        pasien.setFlag("Y");

        try {
            pasienList = pasienBo.getByCriteria(pasien);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
        }

        Branch branch = new Branch();
        branch.setBranchId(userArea);
        branch.setFlag("Y");

        try {
            branchList = branchBo.getByCriteria(branch);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
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
                    sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit // branch
                    sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
//                    sepRequest.setKlsRawat(checkupEntity.getKelasPasien());//kelas rawat dari bpjs // checkup
                    sepRequest.setNoMr(checkupEntity.getIdPasien());//id pasien // checkup
                    sepRequest.setAsalRujukan("1");//
//                    sepRequest.setTglRujukan(checkupEntity.getTglRujukan().toString()); // checkup
//                    sepRequest.setNoRujukan(checkupEntity.getNoRujukan()); // checkup
//                    sepRequest.setPpkRujukan(checkupEntity.getNoPpkRujukan()); // checkup
                    sepRequest.setCatatan("");
                    sepRequest.setDiagAwal(bean.getDiagnosa()); // checkup
//                    sepRequest.setPoliTujuan(checkupEntity.getIdPelayananBpjs()); // checkup
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
                    sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp()); // branch
                    sepRequest.setKodeDpjp("31661");
                    sepRequest.setNoTelp(getPasien.getNoTelp()); // pasien
                    sepRequest.setUserPembuatSep(userLogin);

                    SepResponse response = new SepResponse();

                    try {
                        response = bpjsBo.insertSepBpjs(sepRequest, userArea);
                    } catch (Exception e) {
                        Long logId = null;
                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when insert SEP.\n" + e.getMessage());
                    }

                    if (response.getNoSep() != null) {

                        String genNoSep = response.getNoSep();
                        logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                        KlaimRequest klaimRequest = new KlaimRequest();
                        klaimRequest.setNoSep(genNoSep);
                        klaimRequest.setNoKartu(getPasien.getNoBpjs()); // pasien
                        klaimRequest.setNoRm(getPasien.getIdPasien()); // pasien
                        klaimRequest.setNamaPasien(getPasien.getNama()); // pasien
                        klaimRequest.setTglLahir(getPasien.getTglLahir()); // pasien

                        String jk = "";

                        if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                            jk = "1";
                        } else {
                            jk = "2";
                        }
                        klaimRequest.setGender(jk);

                        KlaimResponse responseNewClaim = new KlaimResponse();
                        try {
                            responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, userArea);
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                        }


                        if (responseNewClaim.getPatientId() != null) {
                            KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                            klaimDetailRequest.setNomorSep(genNoSep);
                            klaimDetailRequest.setNomorKartu(getPasien.getNoKtp()); // pasien
                            klaimDetailRequest.setTglMasuk(updateTime.toString());
                            klaimDetailRequest.setTglPulang(updateTime.toString());
                            klaimDetailRequest.setJenisRawat("2"); // checkup
//                            klaimDetailRequest.setKelasRawat(checkupEntity.getKelasPasien()); // checkup
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
                            klaimDetailRequest.setDiagnosa(bean.getDiagnosa()); // checkup
                            klaimDetailRequest.setProcedure("");

                            klaimDetailRequest.setTarifRsNonBedah("");
                            klaimDetailRequest.setTarifRsProsedurBedah("");

                            klaimDetailRequest.setTarifRsKonsultasi(""); // tindakan
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
                            klaimDetailRequest.setTarifRsObatKronis(bean.getTotalBiaya());
                            klaimDetailRequest.setTarifRsObatKemoterapi("");
                            klaimDetailRequest.setTarifRsAlkes("");
                            klaimDetailRequest.setTarifRsBmhp("");
                            klaimDetailRequest.setTarifRsSewaAlat("");

                            List<Dokter> dokterList = new ArrayList<>();
                            Dokter dokter = new Dokter();
                            dokter.setIdDokter(bean.getIdDokter());
                            dokter.setFlag("Y");
                            try {
                                dokterList = dokterBo.getByCriteria(dokter);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                            }

                            String namaDokter = "";
                            if (dokterList.size() > 0) {
                                namaDokter = dokterList.get(0).getNamaDokter();
                            }

                            klaimDetailRequest.setTarifPoliEks("");
                            klaimDetailRequest.setNamaDokter(namaDokter);
                            klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif()); // branch
                            klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId()); // branch
                            klaimDetailRequest.setPayorCd(getBranch.getPayorCd()); // branch
                            klaimDetailRequest.setCobCd("");
                            klaimDetailRequest.setCoderNik(getBranch.getCoderNik()); // branch

                            KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                            try {
                                claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, userArea);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                            }

                            if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                Grouping1Response grouping1Response = new Grouping1Response();

                                try {
                                    grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, userArea);
                                } catch (GeneralBOException e) {
                                    Long logId = null;
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
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
                                                grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), userArea);
                                            } catch (GeneralBOException e) {
                                                Long logId = null;
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                            }
                                        }
                                    }
                                }

                            } else {
                                logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                addActionError("Error, " + " Found problem when saving add data, please inform to your admin. update claim not success. \n" + claimEklaimResponse.getMessage());
                            }
                        }
                    } else {
                        addActionError("Error, " + "Insert SEP failed");
                    }

                }

            } else {
                addActionError("Error, " + "PPK Pelayanan Tidak Ada");
            }
        }

        return checkup;
    }

    public String printResepKronis() {

        HeaderCheckup checkup = new HeaderCheckup();
        String id = getId();
        String jk = "";

        String logo = "";

        if (id != null && !"".equalsIgnoreCase(id)) {

            String branch = CommonUtil.userBranchLogin();
            String unit = CommonUtil.userBranchNameLogin();
            String area = CommonUtil.userAreaName();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                Branch branches = new Branch();

                try {
                    branches = branchBoProxy.getBranchById(branch, "Y");
                } catch (GeneralBOException e) {
                    logger.error("Found Error when searhc branch logo");
                }

                if (branches != null) {
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
                }

                List<PermintaanResep> resepList = new ArrayList<>();
                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdDetailCheckup(id);

                try {
                    resepList = permintaanResepBoProxy.getByCriteria(permintaanResep);
                } catch (GeneralBOException e) {
                    logger.error("Found Error when " + e.getMessage());
                }

                PermintaanResep resep = new PermintaanResep();
                String idResep = "";
                if (resepList.size() > 0) {
                    resep = resepList.get(0);
                    if (resep.getIdPermintaanResep() != null && !"".equalsIgnoreCase(resep.getIdPermintaanResep())) {
                        idResep = resep.getIdPermintaanResep();
                    }
                }

                reportParams.put("idPasien", checkup.getIdPasien());
                reportParams.put("unit", unit);
                reportParams.put("isKronis", "Y");
                reportParams.put("area", area);
                reportParams.put("resepId", idResep);
                reportParams.put("idDetailCheckup", checkup.getIdDetailCheckup());
                reportParams.put("noCheckup", checkup.getNoCheckup());
                reportParams.put("logo", logo);
                reportParams.put("nik", checkup.getNoKtp());
                reportParams.put("nama", checkup.getNama());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);

                if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Laki-Laki";
                } else {
                    jk = "Perempuan";
                }

                reportParams.put("jenisKelamin", jk);
                reportParams.put("jenisPasien", checkup.getStatusPeriksaName());
                reportParams.put("poli", checkup.getNamaPelayanan());
                reportParams.put("provinsi", checkup.getNamaProvinsi());
                reportParams.put("kabupaten", checkup.getNamaKota());
                reportParams.put("kecamatan", checkup.getNamaKecamatan());
                reportParams.put("desa", checkup.getNamaDesa());


                try {
                    preDownload();
                } catch (SQLException e) {
                    logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                    return "search";
                }
            }
        }

        return "print_resep_kronis";
    }

    public List<Asuransi> getComboAsuransi() {

        List<Asuransi> asuransiList = new ArrayList<>();
        Asuransi asuransi = new Asuransi();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");

        try {
            asuransiList = asuransiBo.getByCriteria(asuransi);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return asuransiList;
    }

    public List<MasterVendor> getComboPtpn() {

        List<MasterVendor> vendorList = new ArrayList<>();
        Asuransi asuransi = new Asuransi();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            vendorList = checkupBo.getComboListPtpn();
        } catch (HibernateException e) {
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        return vendorList;
    }

    public List<HeaderCheckup> getListHistoryPasien(String idPasien) {
        List<HeaderCheckup> checkupList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        if (idPasien != null) {
            try {
                checkupList = checkupBo.getHistoryPasien(idPasien, branchId);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return checkupList;
    }

    public List<HeaderCheckup> getListDetailHistoryPasien(String id, String keterangan) {
        List<HeaderCheckup> checkupList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        if (id != null && keterangan != null) {
            try {
                checkupList = checkupBo.getListDetailHistory(id, keterangan);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return checkupList;
    }

    public List<HeaderCheckup> getListVideoRm(String id) {
        List<HeaderCheckup> checkupList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        if (id != null) {
            try {
                checkupList = checkupBo.getListVedioRm(id);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }
        }
        return checkupList;
    }

    public CrudResponse saveAnamnese(String auto, String hetero, String noCheckup, String idDetailCheckup, String tensi, String suhu, String nadi, String rr) {
        logger.info("[CheckupAction.savePenunjangPasien] start process >>>");
        CrudResponse response = new CrudResponse();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);
        headerCheckup.setAutoanamnesis(auto);
        headerCheckup.setHeteroanamnesis(hetero);
        headerCheckup.setTensi(tensi);
        headerCheckup.setSuhu(suhu);
        headerCheckup.setNadi(nadi);
        headerCheckup.setPernafasan(rr);
        headerCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        headerCheckup.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        try {
            response = checkupBo.updateAnamnese(headerCheckup);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error when update data " + e.getMessage());
        }

        if ("success".equalsIgnoreCase(response.getStatus())) {
            insertProfilRJ(idDetailCheckup, "Autoanamnesis : " + auto + ", Heteroanamnesis : " + hetero);
        }

        logger.info("[CheckupAction.savePenunjangPasien] end process <<<");
        return response;
    }

    public CrudResponse insertProfilRJ(String idDetailCheckup, String anamnese) {
        CrudResponse response = new CrudResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setAnamnese(anamnese);
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setAnamnese(anamnese);
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
        return response;
    }

    public String getDataByKey(String id, String key) {
        String response = "";
        if (id != null && !"".equalsIgnoreCase(id)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

            if ("alergi".equalsIgnoreCase(key)) {
                response = checkupBo.getAlergi(id);
            }
            if ("penunjang_medis".equalsIgnoreCase(key)) {
                response = checkupBo.getPenunjangMedis(id, null);
            }
            if ("lab".equalsIgnoreCase(key) || "radiologi".equalsIgnoreCase(key)) {
                response = checkupBo.getPenunjangMedis(id, key);
            }
            if ("resep".equalsIgnoreCase(key)) {
                response = checkupBo.getResepPasien(id);
            }
            if ("diagnosa".equalsIgnoreCase(key)) {
                response = checkupBo.getDiagnosaPasien(id);
            }
            if ("diagnosa_sekunder".equalsIgnoreCase(key)) {
                response = checkupBo.getDiagnosaSekunder(id);
            }
            if ("diagnosa_primer".equalsIgnoreCase(key)) {
                response = checkupBo.getDiagnosaPrimer(id);
            }
            if ("tindakan".equalsIgnoreCase(key)) {
                response = checkupBo.getTindakanRawat(id);
            }
        }
        return response;
    }

    public Dokter getDataDokterSip(String id, String key) {
        Dokter response = new Dokter();
        if (id != null && !"".equalsIgnoreCase(id)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            response = checkupBo.getNamaSipDokter(id, key);
        }
        return response;
    }

    public HeaderCheckup getDataPemeriksaanFisik(String id) {
        HeaderCheckup response = new HeaderCheckup();
        if (id != null && !"".equalsIgnoreCase(id)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            try {
                response = checkupBo.getDataPemeriksaanFisik(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data pemeriksan fisik " + e.getMessage());
            }
        }
        return response;
    }

    public List<Diagnosa> getICD10(String key) {
        logger.info("[CheckupAction.getDiagnosaRawatPasien] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaBo diagnosaBo = (DiagnosaBo) ctx.getBean("diagnosaBoProxy");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        if (!"".equalsIgnoreCase(key) && key != null) {
            try {
                diagnosaList = diagnosaBo.getSearchDiagnosa(key);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.getDiagnosaRawatPasien] Error when searching diagnosa pasien, Found problem when searching data, please inform to your admin.", e);
            }
        }

        logger.info("[CheckupAction.getDiagnosaRawatPasien] end process >>>");
        return diagnosaList;
    }

    public List<TindakanICD9> getICD9(String key) {
        logger.info("[CheckupAction.getDiagnosaRawatPasien] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanICD9Bo tindakanICD9Bo = (TindakanICD9Bo) ctx.getBean("tindakanICD9BoProxy");

        List<TindakanICD9> tindakanICD9List = new ArrayList<>();
        if (!"".equalsIgnoreCase(key) && key != null) {
            try {
                tindakanICD9List = tindakanICD9Bo.getSearchICD9(key);
            } catch (GeneralBOException e) {
                logger.error("[CheckupAction.getDiagnosaRawatPasien] Error when searching diagnosa pasien, Found problem when searching data, please inform to your admin.", e);
            }
        }

        logger.info("[CheckupAction.getDiagnosaRawatPasien] end process >>>");
        return tindakanICD9List;
    }

    public List<RekamMedisPasien> getListRekammedisPasien(String tipePelayanan, String jenis, String id) {

        logger.info("[CheckupAction.getListRekammedisPasien] START process >>>");

        List<RekamMedisPasien> listRM = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekamMedisPasienBo rekamMedisPasienBo = (RekamMedisPasienBo) ctx.getBean("rekamMedisPasienBoProxy");

        if (tipePelayanan != null && !"".equalsIgnoreCase(tipePelayanan)) {
            try {
                listRM = rekamMedisPasienBo.getListRekamMedisByTipePelayanan(tipePelayanan, jenis, id);
            } catch (GeneralBOException e) {
                logger.error("Found Error, " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.getListRekammedisPasien] END process >>>");
        return listRM;
    }

    public List<RekamMedisPasien> getRiwayatListRekammedisPasien(String id, String tipePelayanan, String jenis) {

        logger.info("[CheckupAction.getListRekammedisPasien] START process >>>");

        List<RekamMedisPasien> listRM = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekamMedisPasienBo rekamMedisPasienBo = (RekamMedisPasienBo) ctx.getBean("rekamMedisPasienBoProxy");

        if (tipePelayanan != null && !"".equalsIgnoreCase(tipePelayanan) && id != null && !"".equalsIgnoreCase(id)) {
            try {
                listRM = rekamMedisPasienBo.getRiwayatListRekamMedis(id, tipePelayanan, jenis);
            } catch (GeneralBOException e) {
                logger.error("Found Error, " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.getListRekammedisPasien] END process >>>");
        return listRM;
    }

    public List<Dokter> getListDokterByBranchId(String idDokter) {

        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");

        List<Dokter> dokterList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            try {
                dokterList = dokterBo.getListDokterByBranchId(branchId, idDokter);
            } catch (GeneralBOException e) {
                logger.error("Found Error, " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return dokterList;
    }

    public List<Dokter> getListDokterByIdDetailCheckup(String idDetailCheckup) {

        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");

        List<Dokter> dokterList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                dokterList = dokterBo.getListDokterByIdDetailCheckup(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("Found Error, " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return dokterList;
    }

    public List<HeaderCheckup> getRiwayatPemeriksaan(String idPasien) {

        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");

        List<HeaderCheckup> checkupList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {
            try {
                checkupList = checkupBo.getRiwayatPemeriksaan(idPasien);
            } catch (GeneralBOException e) {
                logger.error("Found Error, " + e.getMessage());
            }
        }

        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return checkupList;
    }

    public List<JenisObat> getListJenisObat() {
        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");
        List<JenisObat> jenisObatList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisObatBo jenisObatBo = (JenisObatBo) ctx.getBean("jenisObatBoProxy");
        JenisObat jenisObat = new JenisObat();
        try {
            jenisObatList = jenisObatBo.getByCriteria(jenisObat);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
        }
        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return jenisObatList;
    }

    public List<RekananOps> getListRekananOps() {
        logger.info("[CheckupAction.getListRekananOps] START process >>>");
        List<RekananOps> rekananOpsList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
        String userArea = CommonUtil.userBranchLogin();
        try {
            rekananOpsList = rekananOpsBo.getComboRekananOps(userArea);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
        }
        logger.info("[CheckupAction.getListRekananOps] END process >>>");
        return rekananOpsList;
    }

    public RekananOps cekRekananOps(String id) {
        logger.info("[CheckupAction.cekRekananOps] START process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
        String userArea = CommonUtil.userBranchLogin();
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsBo.getDetailRekananOps(id, userArea);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
        }
        logger.info("[CheckupAction.cekRekananOps] END process >>>");
        return rekananOps;
    }

    public RekananOps cekRekananOpsByDetail(String id) {
        logger.info("[CheckupAction.cekRekananOps] START process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
        String userArea = CommonUtil.userBranchLogin();
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsBo.getDetailRekananOpsByDetail(id, userArea);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
        }
        logger.info("[CheckupAction.cekRekananOps] END process >>>");
        return rekananOps;
    }

    public List<PelayananPaket> cekPelayananPaket(String noCheckup) {
        logger.info("[CheckupAction.cekPelayananPaket] START process >>>");
        List<PelayananPaket> pelayananPaketList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        try {
            pelayananPaketList = checkupBo.getListPelayananPaket(noCheckup);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
        }
        logger.info("[CheckupAction.cekPelayananPaket] END process >>>");
        return pelayananPaketList;
    }
}