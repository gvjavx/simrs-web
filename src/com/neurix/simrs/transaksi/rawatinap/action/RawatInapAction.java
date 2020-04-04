package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dietgizi.bo.DietGiziBo;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private String id;
    private String idResep;
    private String idDetail;

    private List<DietGizi> listOfDietGizi = new ArrayList<>();

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

        if (id != null && !"".equalsIgnoreCase(id)) {

            HeaderCheckup checkup = new HeaderCheckup();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

                try {
                    riwayatTindakanList = riwayatTindakanBoProxy.cekTodayTarifKamar(checkup.getIdDetailCheckup());
                } catch (GeneralBOException e) {
                    logger.error("[RawatInapAction.add] Found Error when search tindakan riwayat >>>" + e);
                }

                if (riwayatTindakanList.isEmpty()) {

                    List<Ruangan> ruanganList = new ArrayList<>();
                    Ruangan ruangan = new Ruangan();
                    ruangan.setIdRuangan(checkup.getIdRuangan());

                    try {
                        ruanganList = ruanganBoProxy.getByCriteria(ruangan);
                    } catch (GeneralBOException e) {
                        logger.error("[RawatInapAction.add] Found Error when search ruangan >>>" + e);
                    }

                    if (!ruanganList.isEmpty()) {
                        ruangan = ruanganList.get(0);

                        if (ruanganList != null) {

                            String user = CommonUtil.userLogin();
                            Timestamp now = new Timestamp(System.currentTimeMillis());

                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(ruangan.getIdRuangan());
                            tindakan.setNamaTindakan("Tarif Kamar " + ruangan.getNamaRuangan() + " No. " + ruangan.getNoRuangan());
                            tindakan.setKeterangan("kamar");
                            tindakan.setTotalTarif(new BigDecimal(ruangan.getTarif()));
                            tindakan.setIdDetailCheckup(checkup.getIdDetailCheckup());
                            tindakan.setJenisPasien(checkup.getIdJenisPeriksaPasien());
                            tindakan.setAction("C");
                            tindakan.setFlag("Y");
                            tindakan.setCreatedDate(now);
                            tindakan.setCreatedWho(user);
                            tindakan.setLastUpdate(now);
                            tindakan.setLastUpdateWho(user);
                            tindakan.setTanggalTindakan(now);

                            try {
                                riwayatTindakanBoProxy.saveAdd(tindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[RawatInapAction] Found Error when save add riwayat tindakan " + e);
                            }
                        }
                    }
                }

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

                setRawatInap(rawatInap);
            }else{
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
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }

        if (checkup != null) {

            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+permintaanResep.getTtdDokter());
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

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (RawatInap rawatInap : listOfResult) {
                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {

                        HeaderCheckup headerCheckup = getHeaderCheckup(id);
                        reportParams.put("noCheckup", id);
                        reportParams.put("idDetailCheckup", rawatInap.getIdDetailCheckup());
                        reportParams.put("nama", headerCheckup.getNama());
                        reportParams.put("ruang", rawatInap.getNamaRangan() + " [" + rawatInap.getNoRuangan() + "]");
                        break;
                    }
                }
            }
        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
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
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.saveUpdateMonVitalSign] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonVitalSign] ERROR. "+ e);
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
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.saveUpdateMonCairan] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonCairan] ERROR. "+ e);
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
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.saveUpdateMonPemberianObat] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[RawatInapAction.saveUpdateMonPemberianObat] ERROR. "+ e);
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

}