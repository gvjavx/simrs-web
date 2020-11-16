package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.neurix.simrs.bpjs.vclaim.model.SepResponse;
import com.neurix.simrs.master.dietgizi.bo.DietGiziBo;
import com.neurix.simrs.master.dietgizi.dao.JenisDietDao;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.DetailJenisDiet;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.OrderPeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.OrderPeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RawatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(RawatInapAction.class);
    private RawatInap rawatInap;
    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private RuanganBo ruanganBoProxy;
    private RiwayatTindakanBo riwayatTindakanBoProxy;
    private DietGiziBo dietGiziBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private BranchBo branchBoProxy;
    private PeriksaLabBo periksaLabBoProxy;

    private String id;
    private String idResep;
    private String idDetail;
    private String tipe;
    private String lab;
    private String idx;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    private List<DietGizi> listOfDietGizi = new ArrayList<>();
    private List<JenisDiet> listOfJenisDiet = new ArrayList<>();

    public List<JenisDiet> getListOfJenisDiet() {
        return listOfJenisDiet;
    }

    public void setListOfJenisDiet(List<JenisDiet> listOfJenisDiet) {
        this.listOfJenisDiet = listOfJenisDiet;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setDietGiziBoProxy(DietGiziBo dietGiziBoProxy) {
        this.dietGiziBoProxy = dietGiziBoProxy;
    }

    public List<DietGizi> getListOfDietGizi() {
        return listOfDietGizi;
    }

    public void setListOfDietGizi(List<DietGizi> listOfDietGizi) {
        this.listOfDietGizi = listOfDietGizi;
    }

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public RiwayatTindakanBo getRiwayatTindakanBoProxy() {
        return riwayatTindakanBoProxy;
    }

    public void setRiwayatTindakanBoProxy(RiwayatTindakanBo riwayatTindakanBoProxy) {
        this.riwayatTindakanBoProxy = riwayatTindakanBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RawatInapAction.logger = logger;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RawatInapAction.add] start process >>>");

        String id = getId();
        String jk = "";
        String idx = getIdx();

        if (id != null && !"".equalsIgnoreCase(id)) {

            HeaderCheckup checkup = new HeaderCheckup();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {
                RawatInap rawatInap = new RawatInap();
                rawatInap.setNoCheckup(checkup.getNoCheckup());
                rawatInap.setIdDetailCheckup(checkup.getIdDetailCheckup());
                rawatInap.setIdRawatInap(checkup.getIdRawatInap());
                rawatInap.setIdRuangan(checkup.getIdRuangan());
                rawatInap.setNamaRangan(checkup.getNamaRuangan());
                rawatInap.setIdPasien(checkup.getIdPasien());
                rawatInap.setNamaPasien(checkup.getNama());
                rawatInap.setAlamat(checkup.getJalan());
                rawatInap.setDesa(checkup.getNamaDesa());
                rawatInap.setKecamatan(checkup.getNamaKecamatan());
                rawatInap.setKota(checkup.getNamaKota());
                rawatInap.setProvinsi(checkup.getNamaProvinsi());
                rawatInap.setIdPelayanan(checkup.getIdPelayanan());
                rawatInap.setNamaPelayanan(checkup.getNamaPelayanan());
                if (checkup.getJenisKelamin() != null) {
                    if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                        jk = "Perempuan";
                    } else {
                        jk = "Laki-Laki";
                    }
                }
                rawatInap.setJenisKelamin(jk);
                rawatInap.setTempatLahir(checkup.getTempatLahir());
                rawatInap.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                rawatInap.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
                rawatInap.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
                rawatInap.setNik(checkup.getNoKtp());
                rawatInap.setUrlKtp(checkup.getUrlKtp());
                rawatInap.setNoSep(checkup.getNoSep());
                rawatInap.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
                rawatInap.setMetodePembayaran(checkup.getMetodePembayaran());
                rawatInap.setNoRujukan(checkup.getNoRujukan());
                rawatInap.setTglRujukan(checkup.getTglRujukan());
                rawatInap.setSuratRujukan(checkup.getUrlDocRujuk());
                rawatInap.setIdAsuransi(checkup.getIdAsuransi());
                rawatInap.setNamaAsuransi(checkup.getNamaAsuransi());
                rawatInap.setCoverBiaya(checkup.getCoverBiaya());
                rawatInap.setIsLaka(checkup.getIsLaka());
                if (checkup.getTglLahir() != null && !"".equalsIgnoreCase(checkup.getTglLahir().toString())) {
                    rawatInap.setUmur(calculateAge(checkup.getTglLahir(), true));
                }
                rawatInap.setNamaDiagnosa(checkup.getNamaDiagnosa());
                rawatInap.setTinggi(checkup.getTinggi());
                rawatInap.setBerat(checkup.getBerat());
                rawatInap.setPenunjangMedis(checkup.getPenunjangMedis());
                rawatInap.setAnamnese("Autoanamnesis : " + checkup.getAutoanamnesis() + ", Heteroanamnesis : " + checkup.getHeteroanamnesis());
                rawatInap.setAlamatLengkap(checkup.getNamaDesa() + ", " + checkup.getNamaKecamatan() + ", " + checkup.getNamaKota());
                rawatInap.setIsStay(checkup.getIsStay());
                rawatInap.setIdKelas(checkup.getKelasPasien());
                RawatInap rt = new RawatInap();
                rt.setIdRawatInap(idx);
                rt.setIdDetailCheckup(id);
                List<RawatInap> rwtList = rawatInapBoProxy.getRuanganRawatInap(rt);
                if (rwtList.size() > 0) {
                    RawatInap rwt = rwtList.get(0);
                    rawatInap.setIdRawatInap(rwt.getIdRawatInap());
                    rawatInap.setIdRuangan(rwt.getIdTempatTidur());
                    rawatInap.setNoRuangan(rwt.getNoRuangan());
                    rawatInap.setNamaRangan(rwt.getNamaRangan());
                    rawatInap.setKategoriRuangan(rwt.getKategoriRuangan());
                    rawatInap.setIdKelasRuangan(rwt.getIdKelasRuangan());
                    rawatInap.setKelasRuanganName(rwt.getKelasRuanganName());
                }
                setRawatInap(rawatInap);

            } else {
                setRawatInap(new RawatInap());
            }
        }

        logger.info("[RawatInapAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_save";
    }

    @Override
    public String search() {

        logger.info("[RawatInapAction.search] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("rawat_inap");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        RawatInap rawatInap = new RawatInap();
        rawatInap.setStTglTo(tglToday);
        rawatInap.setStTglFrom(tglToday);
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.initForm] end process <<<");

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

    private HeaderCheckup getHeaderCheckup(String noCheckup) {
        logger.info("[RawatInapAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            result = headerCheckupList.get(0);
        }

        logger.info("[RawatInapAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa) {
        logger.info("[RawatInapAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()) {
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[RawatInapAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String printResepPasien() {

        HeaderCheckup checkup = new HeaderCheckup();
        String idResep = getIdResep();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();
        PermintaanResep permintaanResep = new PermintaanResep();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        try {
            permintaanResep = checkupDetailBoProxy.getDataDokter(idResep);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }

        if (checkup != null) {

            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + permintaanResep.getTtdDokter());
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("resepId", idResep);
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

        return "print_resep";
    }

    public List<SkorRanap> getListParameterByKategori(String noCheckup, String idDetailCheckup, String kategori) {
        logger.info("[RawatInapAction.getListSkorByKategori] start process >>>");

        List<SkorRanap> skorRanapEntities = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        SkorRanap skorRanap = new SkorRanap();
        skorRanap.setNoCheckup(noCheckup);
        skorRanap.setIdDetailCheckup(idDetailCheckup);
        skorRanap.setIdKategori(kategori);

        skorRanapEntities = rawatInapBo.getListSkorRanap(skorRanap);

        logger.info("[RawatInapAction.getListSkorByKategori] end process <<<");
        return skorRanapEntities;
    }

    public List<ImSimrsSkorRanapEntity> getListSkorRanapByParam(String iParameter) {
        logger.info("[RawatInapAction.getListSkorByKategori] start process >>>");

        List<ImSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        skorRanapEntities = rawatInapBo.getListMasterSkor(iParameter);

        logger.info("[RawatInapAction.getListSkorByKategori] end process <<<");
        return skorRanapEntities;
    }

    public CrudResponse saveSkorRanapByKategori(String noCheckup, String idDetail, String kategori, String jsonString) throws JSONException {

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<ItSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();
        JSONArray json = new JSONArray(jsonString);

        ItSimrsSkorRanapEntity ranapEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            ranapEntity = new ItSimrsSkorRanapEntity();
            ranapEntity.setIdParameter(obj.getString("id"));
            ranapEntity.setNamaParameter(obj.getString("name"));
            ranapEntity.setSkor(Integer.valueOf(obj.getString("val")));
            ranapEntity.setKeterangan(obj.getString("ket"));
            ranapEntity.setIdKategori(kategori);
            ranapEntity.setNoCheckup(noCheckup);
            ranapEntity.setIdDetailCheckup(idDetail);

            ranapEntity.setFlag("Y");
            ranapEntity.setAction("U");
            ranapEntity.setCreatedDate(now);
            ranapEntity.setCreatedWho(userLogin);
            ranapEntity.setLastUpdate(now);
            ranapEntity.setLastUpdateWho(userLogin);
            skorRanapEntities.add(ranapEntity);
        }

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        response = rawatInapBo.saveAddSkorRanap(noCheckup, idDetail, skorRanapEntities);

        return response;
    }

    public List<SkorRanap> getListGroupSkorRanap(String noCheckup, String idDetail, String kategori) {
        logger.info("[RawatInapAction.getListGroupSkorRanap] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        logger.info("[RawatInapAction.getListGroupSkorRanap] end process <<<");
        return rawatInapBo.getListSumSkorRanap(noCheckup, idDetail, kategori);
    }

    public ImSimrsKategoriSkorRanapEntity getKategoriSkorRanap(String id) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.kategoriSkorRanap(id);
    }

    public List<SkorRanap> getListViewSkorRanapByGrupId(String groupId) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        SkorRanap skorRanap = new SkorRanap();
        skorRanap.setGroupId(groupId);

        return rawatInapBo.getListSkorRanap(skorRanap);
    }


    public String printGelangPasien() {

        String id = getId();
        HeaderCheckup headerCheckup = new HeaderCheckup();
        //get data from session
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");
//
//        if (id != null && !"".equalsIgnoreCase(id)) {
//
//            if (listOfResult != null) {
//
//                for (RawatInap rawatInap : listOfResult) {
//                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {
//
//                        HeaderCheckup headerCheckup = getHeaderCheckup(id);
//                        reportParams.put("idPasien", rawatInap.getIdPasien());
//                        reportParams.put("noCheckup", id);
//                        reportParams.put("idDetailCheckup", rawatInap.getIdDetailCheckup());
//                        reportParams.put("nama", headerCheckup.getNama());
//                        reportParams.put("ruang", rawatInap.getNamaRangan() + " [" + rawatInap.getNoRuangan() + "]");
//                        break;
//                    }
//                }
//            }
//        }
        try {
            headerCheckup = rawatInapBoProxy.getDetailGelang(id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }

        if(headerCheckup != null){
            reportParams.put("idPasien", headerCheckup.getIdPasien());
            reportParams.put("noCheckup", headerCheckup.getNoCheckup());
            reportParams.put("idDetailCheckup", headerCheckup.getIdDetailCheckup());
            reportParams.put("nama", headerCheckup.getNama());
            reportParams.put("tglLahir", headerCheckup.getStTglLahir());
            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }
        return "print_gelang_pasien";
    }

    public String getComboBoxDietGizi() {

        List<DietGizi> dietGiziArrayList = new ArrayList<>();
        DietGizi dietGizi = new DietGizi();
        dietGizi.setBranchId(CommonUtil.userBranchLogin());
        dietGizi.setFlag("Y");

        try {
            dietGiziArrayList = dietGiziBoProxy.getByCriteria(dietGizi);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.getComboBoxDietGizi] Error when get data for combo list of diet gizi", e);
            addActionError(" Error when get data for combo list of diet gizi" + e.getMessage());
        }

        listOfDietGizi.addAll(dietGiziArrayList);
        return SUCCESS;
    }

    public List<JenisDiet> getComboBoxJenisGizi() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DietGiziBo dietGiziBo = (DietGiziBo) ctx.getBean("dietGiziBoProxy");
        List<JenisDiet> jenisDietList = new ArrayList<>();
        JenisDiet jenisDiet = new JenisDiet();
        jenisDiet.setFlag("Y");
        try {
            jenisDietList = dietGiziBo.getJenisDietByCiteria(jenisDiet);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.getComboBoxDietGizi] Error when get data for combo list of diet gizi", e);
        }
        return jenisDietList;
    }

    public List<DetailJenisDiet> getComboBoxOrderJenisGizi(String idOrderGizi) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
        List<DetailJenisDiet> jenisDietList = new ArrayList<>();
        DetailJenisDiet detailJenisDiet = new DetailJenisDiet();
        detailJenisDiet.setIdOrderGizi(idOrderGizi);
        detailJenisDiet.setFlag("Y");
        try {
            jenisDietList = orderGiziBo.getByCriteriaJenisDiet(detailJenisDiet);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.getComboBoxDietGizi] Error when get data for combo list of diet gizi", e);
        }
        return jenisDietList;
    }

    public List<MonVitalSign> getListMonVitalSign(String noCheckup, String idDetailCheckup, String id) {

        MonVitalSign monVitalSign = new MonVitalSign();

        if (!"".equalsIgnoreCase(noCheckup))
            monVitalSign.setNoCheckup(noCheckup);

        if (!"".equalsIgnoreCase(idDetailCheckup))
            monVitalSign.setIdDetailCheckup(idDetailCheckup);

        if (!"".equalsIgnoreCase(id))
            monVitalSign.setId(id);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        monVitalSign.setIsMobile("N");

        return rawatInapBo.getListMonVitalSign(monVitalSign);
    }

    public MonVitalSign getDataMonVitalSign(String id) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getMonVitalSignById(id);
    }

    public MonCairan getDataMonCairan(String id) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getMonCairanById(id);
    }

    public MonPemberianObat getMonPemberianObat(String id) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getMonPemberianObatById(id);
    }

    public CrudResponse saveMonVitalSign(String noCheckup, String idDetail, String jsonString) throws JSONException {

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonVitalSignEntity monVitalSignEntity = new ItSimrsMonVitalSignEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monVitalSignEntity.setNoCheckup(noCheckup);
            monVitalSignEntity.setIdDetailCheckup(idDetail);
            monVitalSignEntity.setJam(Integer.valueOf(obj.getString("jam").toString()));
            monVitalSignEntity.setNadi(Integer.valueOf(obj.getString("nadi").toString()));
            monVitalSignEntity.setNafas(Integer.valueOf(obj.getString("nafas").toString()));
            monVitalSignEntity.setSuhu(Integer.valueOf(obj.getString("suhu").toString()));
            monVitalSignEntity.setTensi(Integer.valueOf(obj.getString("tensi").toString()));
            monVitalSignEntity.setTb(Integer.valueOf(obj.getString("tb").toString()));
            monVitalSignEntity.setBb(Integer.valueOf(obj.getString("bb").toString()));

            monVitalSignEntity.setFlag("Y");
            monVitalSignEntity.setAction("C");
            monVitalSignEntity.setCreatedDate(now);
            monVitalSignEntity.setCreatedWho(userLogin);
            monVitalSignEntity.setLastUpdate(now);
            monVitalSignEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.saveMonVitalSign(monVitalSignEntity);
    }

    public CrudResponse saveUpdateMonVitalSign(String id, String jsonString) throws JSONException {

        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonVitalSignEntity monVitalSignEntity = new ItSimrsMonVitalSignEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monVitalSignEntity.setId(id);
            monVitalSignEntity.setJam(Integer.valueOf(obj.getString("jam").toString()));
            monVitalSignEntity.setNadi(Integer.valueOf(obj.getString("nadi").toString()));
            monVitalSignEntity.setNafas(Integer.valueOf(obj.getString("nafas").toString()));
            monVitalSignEntity.setSuhu(Integer.valueOf(obj.getString("suhu").toString()));
            monVitalSignEntity.setTensi(Integer.valueOf(obj.getString("tensi").toString()));
            monVitalSignEntity.setTb(Integer.valueOf(obj.getString("tb").toString()));
            monVitalSignEntity.setBb(Integer.valueOf(obj.getString("bb").toString()));

            monVitalSignEntity.setFlag("Y");
            monVitalSignEntity.setAction("U");
            monVitalSignEntity.setLastUpdate(now);
            monVitalSignEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInapBo.saveUpdateMonVitalSign(monVitalSignEntity);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.saveUpdateMonVitalSign] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonVitalSign] ERROR. " + e);
        }

        return response;
    }

    public List<MonCairan> getListMonCairan(String noCheckup, String idDetailCheckup, String id) {

        MonCairan monCairan = new MonCairan();
        if (!"".equalsIgnoreCase(noCheckup))
            monCairan.setNoCheckup(noCheckup);

        if (!"".equalsIgnoreCase(idDetailCheckup))
            monCairan.setIdDetailCheckup(idDetailCheckup);

        if (!"".equalsIgnoreCase(id))
            monCairan.setId(id);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getListMonCairan(monCairan);
    }

    public CrudResponse saveMonCairan(String noCheckup, String idDetail, String jsonString) throws JSONException {

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonCairanEntity monCairanEntity = new ItSimrsMonCairanEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monCairanEntity.setNoCheckup(noCheckup);
            monCairanEntity.setIdDetailCheckup(idDetail);
            monCairanEntity.setMacamCairan(obj.getString("macam"));
            monCairanEntity.setMelalui(obj.getString("melalui"));
            monCairanEntity.setJumlah(obj.getString("jumlah"));
            monCairanEntity.setJamMulai(obj.getString("mulai"));
            monCairanEntity.setJamSelesai(obj.getString("selesai"));
            monCairanEntity.setCekTambahanObat(obj.getString("cek"));
            monCairanEntity.setJamUkurBuang(obj.getString("jam_ukur_buang"));
            monCairanEntity.setDari(obj.getString("dari"));
            monCairanEntity.setBalanceCairan(obj.getString("balance"));
            monCairanEntity.setKeterangan(obj.getString("ket"));
            monCairanEntity.setSisa(obj.getString("sisa"));
            monCairanEntity.setFlag("Y");
            monCairanEntity.setAction("C");
            monCairanEntity.setCreatedDate(now);
            monCairanEntity.setCreatedWho(userLogin);
            monCairanEntity.setLastUpdate(now);
            monCairanEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.saveMonCairan(monCairanEntity);
    }

    public CrudResponse saveUpdateMonCairan(String id, String jsonString) throws JSONException {

        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonCairanEntity monCairanEntity = new ItSimrsMonCairanEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monCairanEntity.setId(id);
            monCairanEntity.setMacamCairan(obj.getString("macam"));
            monCairanEntity.setMelalui(obj.getString("melalui"));
            monCairanEntity.setJumlah(obj.getString("jumlah"));
            monCairanEntity.setJamMulai(obj.getString("mulai"));
            monCairanEntity.setJamSelesai(obj.getString("selesai"));
            monCairanEntity.setCekTambahanObat(obj.getString("cek"));
            monCairanEntity.setJamUkurBuang(obj.getString("jam_ukur_buang"));
            monCairanEntity.setDari(obj.getString("dari"));
            monCairanEntity.setBalanceCairan(obj.getString("balance"));
            monCairanEntity.setKeterangan(obj.getString("ket"));
            monCairanEntity.setSisa(obj.getString("sisa"));
            monCairanEntity.setFlag("Y");
            monCairanEntity.setAction("U");
            monCairanEntity.setLastUpdate(now);
            monCairanEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInapBo.saveUpdateMonCairan(monCairanEntity);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.saveUpdateMonCairan] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonCairan] ERROR. " + e);
        }

        return response;
    }

    public List<MonPemberianObat> getListMonPemberianObat(String noCheckup, String idDetailCheckup, String kategori, String id) {

        MonPemberianObat monPemberianObat = new MonPemberianObat();
        if (!"".equalsIgnoreCase(noCheckup))
            monPemberianObat.setNoCheckup(noCheckup);

        if (!"".equalsIgnoreCase(idDetailCheckup))
            monPemberianObat.setIdDetailCheckup(idDetailCheckup);

        if (!"".equalsIgnoreCase(id))
            monPemberianObat.setId(id);

        if (!"".equalsIgnoreCase(kategori)) {
            monPemberianObat.setKategori(kategori);
        }

        monPemberianObat.setIsMobile("N");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getListPemberianObat(monPemberianObat);
    }

    public CrudResponse saveMonPemberianObat(String noCheckup, String idDetail, String jsonString) throws JSONException {

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonPemberianObatEntity monPemberianObatEntity = new ItSimrsMonPemberianObatEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monPemberianObatEntity.setNoCheckup(noCheckup);
            monPemberianObatEntity.setIdDetailCheckup(idDetail);
            monPemberianObatEntity.setNamaObat(obj.getString("name"));
            monPemberianObatEntity.setCaraPemberian(obj.getString("cara"));
            monPemberianObatEntity.setDosis(obj.getString("dosis"));
            monPemberianObatEntity.setSkinTes(obj.getString("tes"));
            monPemberianObatEntity.setWaktu(obj.getString("waktu"));
            monPemberianObatEntity.setKeterangan(obj.getString("ket"));
            monPemberianObatEntity.setKategori(obj.getString("kat"));
            monPemberianObatEntity.setFlag("Y");
            monPemberianObatEntity.setAction("C");
            monPemberianObatEntity.setCreatedDate(now);
            monPemberianObatEntity.setCreatedWho(userLogin);
            monPemberianObatEntity.setLastUpdate(now);
            monPemberianObatEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.saveMonPemberianObat(monPemberianObatEntity);
    }

    public CrudResponse saveUpdateMonPemberianObat(String id, String jsonString) throws JSONException {

        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsMonPemberianObatEntity monPemberianObatEntity = new ItSimrsMonPemberianObatEntity();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            monPemberianObatEntity.setId(id);
            monPemberianObatEntity.setNamaObat(obj.getString("name"));
            monPemberianObatEntity.setCaraPemberian(obj.getString("cara"));
            monPemberianObatEntity.setDosis(obj.getString("dosis"));
            monPemberianObatEntity.setSkinTes(obj.getString("tes"));
            monPemberianObatEntity.setWaktu(obj.getString("waktu"));
            monPemberianObatEntity.setKeterangan(obj.getString("ket"));
            monPemberianObatEntity.setAction("U");
            monPemberianObatEntity.setLastUpdate(now);
            monPemberianObatEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInapBo.saveUpdateMonPemberianObat(monPemberianObatEntity);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.saveUpdateMonPemberianObat] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonPemberianObat] ERROR. " + e);
        }

        return response;
    }

    public List<Obat> getListObatParenteral(String id) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        return rawatInapBo.getListObatParenteral(id);
    }

    public List<Obat> getListObatNonParenteral(String id, String kategori) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        return rawatInapBo.getListObatNonParenteral(id, kategori);
    }

    public List<MonVitalSign> getListGraf(String idDetail, String noCheckup) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        MonVitalSign monVitalSign = new MonVitalSign();

        if (!"".equalsIgnoreCase(noCheckup))
            monVitalSign.setNoCheckup(noCheckup);
        if (!"".equalsIgnoreCase(idDetail))
            monVitalSign.setIdDetailCheckup(idDetail);

        return rawatInapBo.getListGraf(monVitalSign);
    }

    public String printLabRadiologi() {

        HeaderCheckup checkup = new HeaderCheckup();
        String lab = getLab();
        String id = getId();
        String tipe = getTipe();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            PeriksaLab periksalb = new PeriksaLab();
            try {
                periksalb = periksaLabBoProxy.getNamaLab(lab);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if (periksalb.getIdPeriksaLab() != null) {
                if ("lab".equalsIgnoreCase(tipe)) {
                    reportParams.put("title", "Hasil Periksa Lab " + periksalb.getLabName());
                } else {
                    reportParams.put("title", "Hasil Periksa Radiologi " + periksalb.getLabName());
                }
            }

            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idPeriksaLab", lab);
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
            reportParams.put("diagnosa", checkup.getNamaDiagnosa());
            reportParams.put("sipDokter", periksalb.getSipDokter());
            reportParams.put("sipPengirim", periksalb.getSipPengirim());
            reportParams.put("dokterPengirim", periksalb.getDokterPengirim());
            reportParams.put("petugas", periksalb.getNamaPetugas());
            reportParams.put("dokter", periksalb.getNamaDokter());
            reportParams.put("ttdDokter", periksalb.getTtdDokter());
            reportParams.put("ttdPetugas", periksalb.getTtdPetugas());
            reportParams.put("ttdPengirim", periksalb.getTtdPengirim());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        if ("lab".equalsIgnoreCase(tipe)) {
            return "print_lab";
        } else {
            return "print_radiologi";
        }
    }

    private String calculateAge(java.sql.Date birthDate, boolean justTahun) {
        String umur = "";
        if (birthDate != null && !"".equalsIgnoreCase(birthDate.toString())) {
            int years = 0;
            int months = 0;
            int days = 0;

            //create calendar object for birth day
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(birthDate.getTime());

            //create calendar object for current day
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            //Get difference between years
            years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            int currMonth = now.get(Calendar.MONTH) + 1;
            int birthMonth = birthDay.get(Calendar.MONTH) + 1;

            //Get difference between months
            months = currMonth - birthMonth;

            //if month difference is in negative then reduce years by one
            //and calculate the number of months.
            if (months < 0) {
                years--;
                months = 12 - birthMonth + currMonth;
                if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                    months--;
            } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                years--;
                months = 11;
            }

            //Calculate the days
            if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
                days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
            else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                int today = now.get(Calendar.DAY_OF_MONTH);
                now.add(Calendar.MONTH, -1);
                days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
            } else {
                days = 0;
                if (months == 12) {
                    years++;
                    months = 0;
                }
            }

            if (justTahun) {
                umur = String.valueOf(years);
            } else {
                if (days > 0) {
                    umur = years + " Tahun, " + months + " Bulan, " + days + " Hari";
                } else if (months > 0) {
                    umur = years + " Tahun, " + months + " Bulan";
                } else {
                    umur = years + " Tahun";
                }
            }

        }

        return umur;
    }

    public String initTppri() {
        logger.info("[RawatInapAction.initTppri] START process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        RawatInap rawatInap = new RawatInap();
        rawatInap.setStTglTo(tglToday);
        rawatInap.setStTglFrom(tglToday);
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.initTppri] END process <<<");
        return "search_tppri";
    }

    public String searchTppri() {
        logger.info("[RawatInapAction.searchTppri] START process <<<");
        List<RawatInap> list = new ArrayList<>();
        RawatInap rawatInap = getRawatInap();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());

        try {
            list = rawatInapBoProxy.getListTppri(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.searchTppri] Error Found, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", list);

        logger.info("[RawatInapAction.searchTppri] END process <<<");
        return "search_tppri";
    }

    public CrudResponse saveTppri(String data) {
        logger.info("[RawatInap.saveTppri] START process >>>");
        CrudResponse finalResponse = new CrudResponse();
        try {
            JSONObject object = new JSONObject(data);
            if (object != null) {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                String user = CommonUtil.userLogin();
                String branchId = CommonUtil.userBranchLogin();
                String genNoSep = "";
                String noCheckup = object.getString("no_checkup");
                String idDetailCheckup = object.getString("id_detail_checkup");
                String jenisPasien = object.getString("jenis_pasien");
                String idPasien = object.getString("id_pasien");
                String tindakLanjut = object.getString("tindak_lanjut");
                String kelasKamar = object.getString("kelas_kamar");
                String kamar = object.getString("kamar");
                String kunjungan = "";
                String berkas = "";
                String metodeBayar = null;
                String uangMuka = null;
                String idDokterDpjp = null;

                List<DokterTeam> teamList = new ArrayList<>();
                JSONArray jsonArray = object.getJSONArray("data_dpjp");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    DokterTeam dokterTeam = new DokterTeam();
                    dokterTeam.setIdDokter(obj.getString("id_dpjp"));
                    dokterTeam.setIdPelayanan(obj.getString("id_pelayanan"));
                    dokterTeam.setJenisDpjp(obj.getString("prioritas"));
                    if ("dpjp_1".equalsIgnoreCase(obj.getString("prioritas"))) {
                        idDokterDpjp = obj.getString("id_dpjp");
                        dokterTeam.setFlagApprove("Y");
                    }
                    if ("konsultasi".equalsIgnoreCase(obj.getString("prioritas"))) {
                        //PUSH NOTIF

                        List<NotifikasiFcm> resultNotif = new ArrayList<>();
                        NotifikasiFcm beanNotif = new NotifikasiFcm();
                        beanNotif.setUserId(dokterTeam.getIdDokter());

                        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");

                        resultNotif = notifikasiFcmBo.getByCriteria(beanNotif);
                        if (resultNotif.size() > 0) {
                            FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Persetujuan konsultasi", "dr. meminta persetujuan untuk konsultasi", "SK", resultNotif.get(0).getOs(), null);
                        }

                    }
                    if ("rawat_bersama".equalsIgnoreCase(obj.getString("prioritas"))) {
                        //PUSH NOTIF

                        List<NotifikasiFcm> resultNotif = new ArrayList<>();
                        NotifikasiFcm beanNotif = new NotifikasiFcm();
                        beanNotif.setUserId(dokterTeam.getIdDokter());

                        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");

                        resultNotif = notifikasiFcmBo.getByCriteria(beanNotif);
                        if (resultNotif.size() > 0) {
                            FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Persetujuan rawat bersama", "dr. meminta persetujuan untuk rawat bersama", "SK", resultNotif.get(0).getOs(), null);
                        }

                    }
                    teamList.add(dokterTeam);
                }

                if (object.has("metode_pembayaran")) {
                    metodeBayar = object.getString("metode_pembayaran");
                }
                if (object.has("uang_muka")) {
                    uangMuka = object.getString("uang_muka");
                }

                if (object.has("img_berkas")) {
                    berkas = object.getString("img_berkas");
                }
                if (object.has("kunjungan")) {
                    kunjungan = object.getString("kunjungan");
                }
                if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(idDetailCheckup)) {

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

                    List<Branch> branchList = new ArrayList<>();
                    List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setIdDetailCheckup(idDetailCheckup);

                    try {
                        detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
                    } catch (GeneralBOException e) {
                        logger.error("[RawatInap.saveTppri] Error when geting data detail poli, ", e);
                    }

                    if (!detailCheckupList.isEmpty()) {

                        detailCheckup = detailCheckupList.get(0);

                        if (detailCheckup != null) {

                            HeaderCheckup checkup = new HeaderCheckup();
                            checkup.setNoCheckup(noCheckup);

                            List<HeaderCheckup> headerCheckupList = new ArrayList<>();

                            try {
                                headerCheckupList = checkupBo.getByCriteria(checkup);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
                            }

                            if (!headerCheckupList.isEmpty()) {
                                checkup = headerCheckupList.get(0);

                                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

                                DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                                diagnosaRawat.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                                diagnosaRawat.setOrderCreated("Y");
                                List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

                                try {
                                    diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                                } catch (GeneralBOException e) {
                                    logger.error("[Foun Error] when search diagnosa awal " + e);
                                }

                                if (diagnosaRawatList.size() > 0) {
                                    diagnosaRawat = diagnosaRawatList.get(0);

                                    headerDetailCheckup.setDiagnosa(diagnosaRawat.getIdDiagnosa());
                                    headerDetailCheckup.setNamaDiagnosa(diagnosaRawat.getKeteranganDiagnosa());
                                }

                                List<Pelayanan> pelayananList = new ArrayList<>();
                                Pelayanan pelayanan = new Pelayanan();
                                pelayanan.setTipePelayanan("rawat_inap");
                                pelayanan.setBranchId(CommonUtil.userBranchLogin());

                                try {
                                    pelayananList = pelayananBo.getByCriteria(pelayanan);
                                } catch (GeneralBOException e) {
                                    logger.error("[Found Error] when search pelayanan " + e.getMessage());
                                }

                                if (pelayananList.size() > 0) {
                                    pelayanan = pelayananList.get(0);
                                }

                                if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

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

                                                    String noSkdpVal = detailCheckup.getNoSep();
                                                    String noSkdp = noSkdpVal.substring(noSkdpVal.length() - 6);
                                                    logger.info("NO SKDP : " + noSkdp);

                                                    String noPPK = noSkdpVal.substring(0, 8);

                                                    List<Dokter> dokterArrayList = new ArrayList<>();
                                                    Dokter dokter = new Dokter();
                                                    dokter.setIdDokter(idDokterDpjp);
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

                                                    SepRequest sepRequest = new SepRequest();
                                                    sepRequest.setNoKartu(getPasien.getNoBpjs());
                                                    sepRequest.setTglSep(now.toString());
                                                    sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                                    sepRequest.setJnsPelayanan("1");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                                    sepRequest.setKlsRawat(detailCheckup.getIdKelas());//kelas rawat dari bpjs
                                                    sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                                    sepRequest.setAsalRujukan("2");//
                                                    sepRequest.setTglRujukan(now.toString());
                                                    sepRequest.setNoRujukan(detailCheckup.getNoSep());
                                                    sepRequest.setPpkRujukan(noPPK);
                                                    sepRequest.setCatatan("");
                                                    sepRequest.setDiagAwal(diagnosaRawat.getIdDiagnosa());
                                                    sepRequest.setPoliTujuan(detailCheckup.getIdPelayananBpjs());
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
                                                    sepRequest.setNoSuratSkdp(noSkdp);
                                                    sepRequest.setKodeDpjp(kodeDpjp);
                                                    sepRequest.setNoTelp(getPasien.getNoTelp());
                                                    sepRequest.setUserPembuatSep(user);

                                                    SepResponse response = new SepResponse();

                                                    try {
                                                        response = bpjsBo.insertSepBpjs(sepRequest, branchId);
                                                    } catch (Exception e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    if (response.getNoSep() != null) {

                                                        genNoSep = response.getNoSep();

                                                        headerDetailCheckup.setNoSep(response.getNoSep());

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
                                                            tindakanList = tindakanBo.getDataTindakan(tindakan);
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
                                                                tin.setTindakan(entity.getTindakan());
                                                                tin.setTarifBpjs(entity.getTarifBpjs());
                                                                tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                                                tindakans.add(tin);
                                                            }
                                                            headerDetailCheckup.setTindakanList(tindakans);
                                                        }

                                                        if (responseNewClaim.getPatientId() != null) {

                                                            KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                                            klaimDetailRequest.setNomorSep(genNoSep);
                                                            klaimDetailRequest.setNomorKartu(getPasien.getNoBpjs());
                                                            klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                                            klaimDetailRequest.setTglPulang(now.toString());
                                                            klaimDetailRequest.setJenisRawat("1");
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
                                                            klaimDetailRequest.setDiagnosa(diagnosaRawat.getIdDiagnosa());
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
                                                                            finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                            logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                            return finalResponse;
                                                                        }
                                                                    } else {
                                                                        finalResponse.setStatus("error");
                                                                        finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
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
                                                                    finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }

                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                                logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                                return finalResponse;
                                                            }
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                            logger.error("[CheckupAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                            return finalResponse;
                                                        }
                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMsg("Failed To Generate SEP " + response.getMessage());
                                                        logger.error("[CheckupAction.saveAdd] Failed To Generate SEP " + response.getMessage());
                                                        return finalResponse;
                                                    }
                                                }
                                            }
                                        } else {
                                            finalResponse.setStatus("error");
                                            finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit");
                                            logger.error("Found Error when search branch id");
                                            return finalResponse;
                                        }
                                    }

                                    if ("rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                                        headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                                        headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                                        headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                                    }
                                } else if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                                    headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                                    headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                                    if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                                        headerDetailCheckup.setCoverBiaya(new BigDecimal(uangMuka));
                                    }
                                    headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                                } else {
                                    headerDetailCheckup.setMetodePembayaran(metodeBayar);
                                }

                                headerDetailCheckup.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                                headerDetailCheckup.setNoCheckup(noCheckup);
                                headerDetailCheckup.setIdPelayanan(pelayanan.getIdPelayanan());
                                headerDetailCheckup.setIdRuangan(kamar);
                                headerDetailCheckup.setStatusPeriksa("1");
                                headerDetailCheckup.setCreatedDate(now);
                                headerDetailCheckup.setCreatedWho(user);
                                headerDetailCheckup.setLastUpdate(now);
                                headerDetailCheckup.setLastUpdateWho(user);
                                headerDetailCheckup.setRawatInap(true);
                                headerDetailCheckup.setIdJenisPeriksaPasien(detailCheckup.getIdJenisPeriksaPasien());
                                headerDetailCheckup.setBranchId(branchId);
                                headerDetailCheckup.setDokterTeamList(teamList);
                                headerDetailCheckup.setIdDokter(idDokterDpjp);
                                headerDetailCheckup.setTindakLanjut(tindakLanjut);
                                headerDetailCheckup.setIdKelas(detailCheckup.getIdKelas());

                                if ("umum".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                                    if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                                        headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                                    }
                                }

                                if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                                    if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                                        headerDetailCheckup.setCoverBiaya(new BigDecimal(uangMuka));
                                    }
                                }

                                if (berkas != null && !"".equalsIgnoreCase(berkas)) {
                                    try {
                                        BASE64Decoder decoder = new BASE64Decoder();
                                        byte[] decodedBytes = decoder.decodeBuffer(berkas);
                                        String fileName = idDetailCheckup + "-berkas-pasien.png";
                                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                                        if (image == null) {
                                            logger.error("Buffered Image is null");
                                            finalResponse.setStatus("error");
                                            finalResponse.setMsg("Buffered Image is null");
                                        } else {
                                            File f = new File(uploadFile);
                                            ImageIO.write(image, "png", f);
                                            headerDetailCheckup.setBerkas(fileName);
                                        }
                                    } catch (IOException e) {
                                        finalResponse.setStatus("error");
                                        finalResponse.setMsg("Found Error, " + e.getMessage());
                                    }
                                }

                                if (kunjungan != null && !"".equalsIgnoreCase(kunjungan)) {
                                    headerDetailCheckup.setFlagKunjungan(kunjungan);
                                }

                                try {
                                    HeaderDetailCheckup detail = new HeaderDetailCheckup();
                                    detail.setIdDetailCheckup(idDetailCheckup);
                                    detail.setLastUpdate(now);
                                    detail.setLastUpdateWho(user);
                                    detail.setFlagTppri("Y");
                                    List<HeaderDetailCheckup> list = new ArrayList<>();
                                    list.add(detail);
                                    finalResponse = checkupDetailBo.updateDetailCheckup(list);
                                    if ("success".equalsIgnoreCase(finalResponse.getStatus())) {
                                        finalResponse = checkupDetailBo.saveAdd(headerDetailCheckup);
                                    }
                                } catch (GeneralBOException e) {
                                    finalResponse.setStatus("error");
                                    finalResponse.setMsg("Error when saving add new rawat inap " + e.getMessage());
                                    logger.error("[RawatInap.saveTppri] Error when saving add new detail poli, ", e);
                                }
                            } else {
                                finalResponse.setStatus("error");
                                finalResponse.setMsg("Tidak dapat menemukan NO Checkup Pasien");
                            }

                        } else {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Tidak dapat menemukan ID Detail Checkup Pasien");
                        }
                    }
                }

            } else {
                finalResponse.setStatus("error");
                finalResponse.setMsg("Data object yang dikirim tidak ada..!");
            }
        } catch (JSONException e) {
            finalResponse.setStatus("error");
            finalResponse.setMsg("Error when JSON Parse, " + e.getMessage());
        }

        logger.info("[RawatInap.saveTppri] END process >>>");
        return finalResponse;

    }

    public CrudResponse saveTindakLanjutRawatInap(String data) {
        logger.info("[RawatInapAction.saveTindakLanjutRawatInap] START process >>>");
        CrudResponse response = new CrudResponse();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String user = CommonUtil.userLogin();
            String branchId = CommonUtil.userBranchLogin();
            JSONObject object = new JSONObject(data);
            if (object != null) {
                String noChekcup = object.getString("no_checkup");
                String idDetailCheckup = object.getString("id_detail_checkup");
                String idRawatInap = object.getString("id_rawat_inap");
                String tindakLanjut = object.getString("tindak_lanjut");
                String catatan = object.getString("catatan");
                String keterangan = object.getString("keterangan");
                String jenisPasien = object.getString("jenis_pasien");

                String idRuangan = null;
                String isStay = null;
                String rsRujukan = null;
                String tglKontrol = null;
                String kategoriLab = null;
                String unitLab = null;
                String isOrderLab = null;
                String idRuanganLama = null;
                JSONArray jsonParameter = new JSONArray();

                if (object.has("id_ruangan")) {
                    idRuangan = object.getString("id_ruangan");
                }
                if (object.has("is_stay")) {
                    isStay = object.getString("is_stay");
                }
                if (object.has("rs_rujukan")) {
                    rsRujukan = object.getString("rs_rujukan");
                }
                if (object.has("tgl_kontrol")) {
                    tglKontrol = object.getString("tgl_kontrol");
                }
                if (object.has("kategori_lab")) {
                    kategoriLab = object.getString("kategori_lab");
                }
                if (object.has("unit_lab")) {
                    unitLab = object.getString("unit_lab");
                }
                if (object.has("parameter")) {
                    jsonParameter = object.getJSONArray("parameter");
                }
                if (object.has("is_order_lab")) {
                    isOrderLab = object.getString("is_order_lab");
                }
                if (object.has("id_ruangan_lama")) {
                    idRuanganLama = object.getString("id_ruangan_lama");
                }

                List<OrderPeriksaLab> orderPeriksaLab = new ArrayList<>();

                if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && idRawatInap != null && !"".equalsIgnoreCase(idRawatInap)) {
                    RawatInap rawatInap = new RawatInap();
                    if ("Y".equalsIgnoreCase(isOrderLab)) {
                        for (int i = 0; i < jsonParameter.length(); i++) {
                            String value = jsonParameter.getString(i);
                            OrderPeriksaLab order = new OrderPeriksaLab();
                            order.setIdDetailCheckup(idDetailCheckup);
                            order.setIdLab(unitLab);
                            order.setIdLabDetail(value);
                            order.setIsPemeriksaan("N");
                            order.setAction("C");
                            order.setFlag("Y");
                            order.setCreatedWho(user);
                            order.setCreatedDate(now);
                            order.setLastUpdateWho(user);
                            order.setLastUpdate(now);
                            if ("KAL00000001".equalsIgnoreCase(kategoriLab)) {
                                order.setKeterangan("radiologi");
                            }
                            if ("KAL00000002".equalsIgnoreCase(kategoriLab)) {
                                order.setKeterangan("lab");
                            }
                            orderPeriksaLab.add(order);
                        }

                        if (orderPeriksaLab.size() > 0) {
                            response = saveLabCheckup(orderPeriksaLab);
                            if ("success".equalsIgnoreCase(response.getStatus())) {
                                rawatInap.setIsOrderLab("Y");
                            } else {
                                return response;
                            }
                        }
                    }

                    rawatInap.setIdRawatInap(idRawatInap);
                    rawatInap.setNoCheckup(noChekcup);
                    rawatInap.setIdDetailCheckup(idDetailCheckup);
                    rawatInap.setIdRuangan(idRuangan);
                    rawatInap.setTindakLanjut(tindakLanjut);
                    rawatInap.setIsStay(isStay);
                    rawatInap.setCreatedDate(now);
                    rawatInap.setCreatedWho(user);
                    rawatInap.setLastUpdate(now);
                    rawatInap.setLastUpdateWho(user);
                    rawatInap.setAction("C");
                    rawatInap.setFlag("Y");
                    rawatInap.setRsRujukan(rsRujukan);
                    rawatInap.setTglRujukan(tglKontrol);
                    rawatInap.setCatatan(catatan);
                    rawatInap.setKeteranganSelesai(keterangan);
                    rawatInap.setIdRuangLama(idRuanganLama);
                    rawatInap.setIdJenisPeriksa(jenisPasien);

                    saveApproveAllTindakan(idDetailCheckup, jenisPasien);
                    response = rawatInapBo.saveAdd(rawatInap);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        rawatInapBo.updateRiwayatKamar(rawatInap);
                    }

                } else {
                    response.setStatus("error");
                    response.setMsg("Data Id Detail Chekcup tidak ada...!");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data object di JSON tidak ada...!");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Errror when JSON Parse " + e.getMessage());
        }
        logger.info("[RawatInapAction.saveTindakLanjutRawatInap] END process >>>");
        return response;
    }

    private CrudResponse saveLabCheckup(List<OrderPeriksaLab> bean) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderPeriksaLabBo orderPeriksaLabBo = (OrderPeriksaLabBo) ctx.getBean("orderPeriksaLabBoProxy");
        try {
            response = orderPeriksaLabBo.saveAdd(bean);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        return response;
    }

    public String saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            String jenPasien = "";
            if ("rekanan".equalsIgnoreCase(jenisPasien)) {
                jenPasien = "bpjs";
            } else {
                jenPasien = jenisPasien;
            }

            String idPaket = "";
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetail);
            if (detailCheckupEntity != null) {
                idPaket = detailCheckupEntity.getIdPaket();
            }

            List<TindakanRawat> listTindakan = new ArrayList<>();
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setApproveFlag("Y");

            try {
                listTindakan = tindakanRawatBo.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());

                        if (!"".equalsIgnoreCase(idPaket)) {

                            // mengambil berdasarkan idPaket dan idTindakan;
                            MtSimrsItemPaketEntity itemPaketEntity = riwayatTindakanBo.getItemPaketEntity(idPaket, entity.getIdTindakan());
                            if (itemPaketEntity != null) {

                                // jika ada paket;
                                riwayatTindakan.setTotalTarif(new BigDecimal(itemPaketEntity.getHarga()));
                            } else {

                                // jika tidak ada item paket namun golongan paket, maka tarif tindakan asli yang dipakai
                                riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                            }

                        } else {

                            // jika bukan paket maka tarif tindakan asli
                            riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        }

                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());
                        riwayatTindakan.setIdRuangan(entity.getIdRuangan());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(idDetail);
            periksaLab.setApproveFlag("Y");

            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        BigDecimal totalTarif = null;

                        try {
                            totalTarif = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdPeriksaLab());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan("Periksa " + entity.getKategoriLabName() + " " + entity.getLabName());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket)) {

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null) {

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(totalTarif);
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            riwayatTindakan.setTotalTarif(totalTarif);
                        }

                        riwayatTindakan.setKeterangan(entity.getKategori());
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);

            try {
                resepList = permintaanResepBo.getByCriteria(resep);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            riwayatTindakan.setKeterangan("resep");
                            riwayatTindakan.setJenisPasien(obatDetailList.getJenisResep());
                            riwayatTindakan.setAction("C");
                            riwayatTindakan.setFlag("Y");
                            riwayatTindakan.setCreatedWho(user);
                            riwayatTindakan.setCreatedDate(updateTime);
                            riwayatTindakan.setLastUpdate(updateTime);
                            riwayatTindakan.setLastUpdateWho(user);
                            riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                            try {
                                riwayatTindakanBo.saveAdd(riwayatTindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            }
                        }
                    }
                }
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(idDetail);
            List<RawatInap> rawatInapList = new ArrayList<>();

            try {
                rawatInapList = rawatInapBo.getByCriteria(rawatInap);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }

            if (rawatInapList.size() > 0) {

                rawatInap = rawatInapList.get(0);

                if (rawatInap != null) {

                    OrderGizi orderGizi = new OrderGizi();
                    orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                    orderGizi.setDiterimaFlag("Y");
                    List<OrderGizi> giziList = new ArrayList<>();

                    try {
                        giziList = orderGiziBo.getByCriteria(orderGizi);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error" + e.getMessage());
                    }

                    if (giziList.size() > 0) {

                        for (OrderGizi gizi : giziList) {

                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(gizi.getIdOrderGizi());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(gizi.getCreatedDate());

                                try {
                                    riwayatTindakanBo.saveAdd(riwayatTindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }

        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] END process >>>");
        return SUCCESS;
    }

    public String searchIntensif() {

        logger.info("[RawatInapAction.searchIntensif] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("rawat_intensif");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria, Found problem when searching data by criteria, please inform to your admin." + e.getMessage());
            throw new GeneralBOException("Error when search pasien intensif", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.searchIntensif] end process <<<");
        return "search";
    }

    public String searchIsolasi() {

        logger.info("[RawatInapAction.searchIsolasi] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("rawat_isolasi");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria, Found problem when searching data by criteria, please inform to your admin." + e.getMessage());
            throw new GeneralBOException("Error when search pasien intensif", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.searchIsolasi] end process <<<");
        return "search";
    }

    public String searchOperasi() {

        logger.info("[RawatInapAction.searchOperasi] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("kamar_operasi");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria, Found problem when searching data by criteria, please inform to your admin." + e.getMessage());
            throw new GeneralBOException("Error when search pasien intensif", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.searchOperasi] end process <<<");
        return "search";
    }

    public String searchRR() {

        logger.info("[RawatInapAction.searchRR] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("rr");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria, Found problem when searching data by criteria, please inform to your admin." + e.getMessage());
            throw new GeneralBOException("Error when search pasien intensif", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.searchRR] end process <<<");
        return "search";
    }

    public String searchBersalin() {

        logger.info("[RawatInapAction.searchBersalin] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        rawatInap.setTindakLanjut("ruang_bersalin");

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria, Found problem when searching data by criteria, please inform to your admin." + e.getMessage());
            throw new GeneralBOException("Error when search pasien intensif", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.searchBersalin] end process <<<");
        return "search";
    }

    public CrudResponse saveApproveAllTindakan(String idDetailCheckup, String jenisPasien) {

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CrudResponse finalResponse = new CrudResponse();
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setLastUpdate(updateTime);
            headerDetailCheckup.setLastUpdateWho(user);

            try {
                response = checkupDetailBo.saveApproveAllTindakanRawatJalan(headerDetailCheckup);
            } catch (GeneralBOException e) {
                finalResponse.setStatus("errror");
                finalResponse.setMsg(response.getMessage());
                logger.error("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if ("success".equalsIgnoreCase(response.getStatus())) {
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien);
            }
        }

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] END process >>>");

        return finalResponse;
    }

    public CrudResponse saveUangMuka(String idDetailCheckup, String uangMuka) {
        CrudResponse finalResponse = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
            UangMuka muka = new UangMuka();
            muka.setIdDetailCheckup(idDetailCheckup);
            muka.setJumlah(new BigInteger(uangMuka));
            muka.setCreatedWho(user);
            muka.setCreatedDate(now);
            muka.setLastUpdateWho(user);
            muka.setLastUpdate(now);
            muka.setBranchId(branchId);

            try {
                finalResponse = rawatInapBo.saveUangMuka(muka);
            } catch (GeneralBOException e) {
                finalResponse.setStatus("error");
                finalResponse.setMsg("Error when error");
                logger.error(e.getMessage());
            }
        }
        return finalResponse;
    }

    public List<UangMuka> getListUangMuka(String noCheckup) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        List<UangMuka> uangMukas = new ArrayList<>();
        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)) {
            UangMuka uangMuka = new UangMuka();
            uangMuka.setNoCheckup(noCheckup);
            try {
                uangMukas = rawatInapBo.getAllListUangMuka(uangMuka);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
            }
        }
        return uangMukas;
    }

    public List<ItSimrsDokterTeamEntity> cekDokterApporve(String idDetailCheckup) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        List<ItSimrsDokterTeamEntity> dokterTeamList = new ArrayList<>();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                dokterTeamList = teamDokterBo.cekRequestDokter(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
            }
        }
        return dokterTeamList;
    }

    public CrudResponse saveNewTppri(String data) {
        logger.info("[RawatInap.saveTppri] START process >>>");
        CrudResponse response = new CrudResponse();
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

        try {
            JSONObject obj = new JSONObject(data);
            HeaderCheckup checkup = new HeaderCheckup();
            Pasien pasien = new Pasien();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String user = CommonUtil.userLogin();
            String branchId = CommonUtil.userBranchLogin();
            String genNoSep = "";

            if (obj != null) {
                String jenisPasien = obj.getString("jenis_pasien");
                String kelasKamar = obj.getString("kelas_kamar");
                String kamar = obj.getString("kamar");
                String metodeBayar = null;
                String uangMuka = null;
                String idDokterDpjp = null;

                pasien.setNoBpjs(obj.getString("no_bpjs"));
                pasien.setNoKtp(obj.getString("nik"));
                pasien.setNama(obj.getString("nama"));
                pasien.setJenisKelamin(obj.getString("jk"));
                pasien.setTempatLahir(obj.getString("tempat_lahir"));
                pasien.setTglLahir(obj.getString("tanggal_lahir"));
                pasien.setAgama(obj.getString("agama"));
                pasien.setProfesi(obj.getString("profesi"));
                pasien.setSuku(obj.getString("suku"));
                pasien.setJalan(obj.getString("alamat"));
                pasien.setNoTelp(obj.getString("no_telp"));
                pasien.setDesaId(obj.getString("desa_id"));
                pasien.setCreatedDate(now);
                pasien.setCreatedWho(user);
                pasien.setLastUpdate(now);
                pasien.setLastUpdateWho(user);

                if (obj.getString("img_ktp") != null && !"".equalsIgnoreCase(obj.getString("img_ktp"))) {
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img_ktp"));
                        String fileName = checkup.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_KTP_PASIEN + fileName;
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                        if (image == null) {
                            logger.error("Buffered Image is null");
                        } else {
                            File f = new File(uploadFile);
                            ImageIO.write(image, "png", f);
                            pasien.setUrlKtp(fileName);
                        }
                    } catch (IOException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                    }
                }

                if (obj.getString("img_rujukan") != null && !"".equalsIgnoreCase(obj.getString("img_rujukan"))) {
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img_rujukan"));
                        String fileName = checkup.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN + fileName;
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                        if (image == null) {
                            logger.error("Buffered Image is null");
                        } else {
                            File f = new File(uploadFile);
                            ImageIO.write(image, "png", f);
                            checkup.setUrlDocRujuk(fileName);
                        }
                    } catch (IOException e) {
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                    }
                }

                Pasien responsePasien = pasienBo.saveAddWithResponse(pasien);
                if ("success".equalsIgnoreCase(responsePasien.getStatus())) {
                    List<DokterTeam> teamList = new ArrayList<>();
                    JSONArray jsonArray = obj.getJSONArray("data_dpjp");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objt = jsonArray.getJSONObject(i);
                        DokterTeam dokterTeam = new DokterTeam();
                        dokterTeam.setIdDokter(objt.getString("id_dpjp"));
                        dokterTeam.setIdPelayanan(objt.getString("id_pelayanan"));
                        dokterTeam.setJenisDpjp(objt.getString("prioritas"));
                        if ("dpjp_1".equalsIgnoreCase(objt.getString("prioritas"))) {
                            idDokterDpjp = objt.getString("id_dpjp");
                            dokterTeam.setFlagApprove("Y");
                        }
                        teamList.add(dokterTeam);
                    }

                    if (obj.has("metode_pembayaran")) {
                        metodeBayar = obj.getString("metode_pembayaran");
                    }
                    if (obj.has("uang_muka")) {
                        uangMuka = obj.getString("uang_muka");
                    }

                    List<Branch> branchList = new ArrayList<>();
                    List<Pelayanan> pelayananList = new ArrayList<>();
                    Pelayanan pelayanan = new Pelayanan();
                    pelayanan.setTipePelayanan("rawat_inap");
                    pelayanan.setBranchId(CommonUtil.userBranchLogin());

                    try {
                        pelayananList = pelayananBo.getByCriteria(pelayanan);
                    } catch (GeneralBOException e) {
                        logger.error("[Found Error] when search pelayanan " + e.getMessage());
                    }

                    if (pelayananList.size() > 0) {
                        pelayanan = pelayananList.get(0);
                    }

                    if ("bpjs".equalsIgnoreCase(jenisPasien)) {
                        checkup.setRujuk(obj.getString("perujuk"));
                        checkup.setTglRujukan(obj.getString("tgl_ppk"));
                        checkup.setNoRujukan(obj.getString("no_rujukan"));
                        checkup.setNoPpkRujukan(obj.getString("no_ppk"));

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

                                        List<Dokter> dokterArrayList = new ArrayList<>();
                                        Dokter dokter = new Dokter();
                                        dokter.setIdDokter(idDokterDpjp);
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

                                        SepRequest sepRequest = new SepRequest();
                                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                                        sepRequest.setTglSep(now.toString());
                                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                        sepRequest.setJnsPelayanan("1");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                        sepRequest.setKlsRawat(obj.getString("id_kelas"));//kelas rawat dari bpjs
                                        sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                        if (checkup.getRujuk() != null && !"".equalsIgnoreCase(checkup.getRujuk())) {
                                            sepRequest.setAsalRujukan(checkup.getRujuk());
                                        } else {
                                            sepRequest.setAsalRujukan("2");
                                        }

                                        if (checkup.getTglRujukan() != null && !"".equalsIgnoreCase(checkup.getTglRujukan())) {
                                            sepRequest.setTglRujukan(checkup.getTglRujukan());
                                        } else {
                                            sepRequest.setTglRujukan("");
                                        }

                                        if (checkup.getNoRujukan() != null && !"".equalsIgnoreCase(checkup.getNoRujukan())) {
                                            sepRequest.setNoRujukan(checkup.getNoRujukan());
                                        } else {
                                            sepRequest.setNoRujukan("");
                                        }

                                        if (checkup.getIdPelayananBpjs() != null && !"".equalsIgnoreCase(checkup.getIdPelayananBpjs())) {
                                            sepRequest.setPoliTujuan(checkup.getIdPelayananBpjs());
                                        } else {
                                            sepRequest.setPoliTujuan("IGD");
                                        }

                                        if (checkup.getNoPpkRujukan() != null && !"".equalsIgnoreCase(checkup.getNoPpkRujukan())) {
                                            sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                                        } else {
                                            sepRequest.setPpkRujukan("");
                                        }
                                        sepRequest.setCatatan("");
                                        sepRequest.setDiagAwal(obj.getString("diagnosa"));
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
                                        sepRequest.setUserPembuatSep(user);

                                        SepResponse responseSep = new SepResponse();

                                        try {
                                            responseSep = bpjsBo.insertSepBpjs(sepRequest, branchId);
                                        } catch (Exception e) {
                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                        }

                                        if (responseSep.getNoSep() != null) {

                                            genNoSep = responseSep.getNoSep();

                                            checkup.setNoSep(responseSep.getNoSep());

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
                                                tindakanList = tindakanBo.getDataTindakan(tindakan);
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
                                                    tin.setTindakan(entity.getTindakan());
                                                    tin.setTarifBpjs(entity.getTarifBpjs());
                                                    tindakans.add(tin);
                                                }
                                                checkup.setTindakanList(tindakans);
                                            }

                                            if (responseNewClaim.getPatientId() != null) {

                                                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                                klaimDetailRequest.setNomorSep(genNoSep);
                                                klaimDetailRequest.setNomorKartu(getPasien.getNoBpjs());
                                                klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                                klaimDetailRequest.setTglPulang(now.toString());
                                                klaimDetailRequest.setJenisRawat("1");
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
                                                klaimDetailRequest.setDiagnosa(obj.getString("diagnosa"));
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

                                                                checkup.setTarifBpjs(tarifCbg);
                                                                checkup.setKodeCbg(grouping1Response.getCbgCode());
                                                                response.setStatus("success");

                                                                //=====END SET TARIF BPJS DARI E-KLAIM=====
                                                            } else {
                                                                response.setStatus("error");
                                                                response.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return response;
                                                            }
                                                        } else {
                                                            response.setStatus("error");
                                                            response.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return response;
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
                                                        response.setStatus("error");
                                                        response.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                        logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                        return response;
                                                    }

                                                } else {
                                                    response.setStatus("error");
                                                    response.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                    return response;
                                                }
                                            } else {
                                                response.setStatus("error");
                                                response.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                logger.error("[CheckupAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                return response;
                                            }
                                        } else {
                                            response.setStatus("error");
                                            response.setMsg("Failed To Generate SEP " + response.getMsg());
                                            logger.error("[CheckupAction.saveAdd] Failed To Generate SEP " + response.getMsg());
                                            return response;
                                        }
                                    }
                                }
                            } else {
                                response.setStatus("error");
                                response.setMsg("Tidak dapat menemukan PPK pelayanan Unit");
                                logger.error("Found Error when search branch id");
                                return response;
                            }
                        }
                    }

                    checkup.setNoBpjs(obj.getString("no_bpjs"));
                    checkup.setNoKtp(obj.getString("nik"));
                    checkup.setNama(obj.getString("nama"));
                    checkup.setJenisKelamin(obj.getString("jk"));
                    checkup.setTempatLahir(obj.getString("tempat_lahir"));
                    checkup.setTglLahir(Date.valueOf(obj.getString("tanggal_lahir")));
                    checkup.setAgama(obj.getString("agama"));
                    checkup.setProfesi(obj.getString("profesi"));
                    checkup.setSuku(obj.getString("suku"));
                    checkup.setJalan(obj.getString("alamat"));
                    checkup.setNoTelp(obj.getString("no_telp"));
                    checkup.setDesaId(new BigInteger(obj.getString("desa_id")));
                    checkup.setCreatedDate(now);
                    checkup.setCreatedWho(user);
                    checkup.setLastUpdate(now);
                    checkup.setLastUpdateWho(user);
                    checkup.setIdRuangan(kamar);
                    checkup.setIdPelayanan(pelayanan.getIdPelayanan());
                    checkup.setMetodePembayaran(metodeBayar);
                    checkup.setDiagnosa(obj.getString("diagnosa"));
                    checkup.setNamaDiagnosa(obj.getString("ket_diagnosa"));
                    if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                        checkup.setUangMuka(new BigInteger(uangMuka));
                    }
                    checkup.setIdJenisPeriksaPasien(jenisPasien);
                    checkup.setBranchId(branchId);
                    checkup.setIdPasien(responsePasien.getIdPasien());
                    checkup.setStatusPeriksa("1");
                    checkup.setJenisKunjungan("Baru");
                    checkup.setKunjunganPoli("Baru");
                    checkup.setUrlKtp(responsePasien.getImgKtp());
                    checkup.setRawatInap(true);
                    response = checkupBo.saveAddWithResponse(checkup);
                }
            }

        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }
}