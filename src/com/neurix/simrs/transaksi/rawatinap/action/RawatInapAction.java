package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.SkorRanap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
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

    private String id;
    private String idResep;

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

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (RawatInap rawatInap : listOfResult) {
                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {

                        HeaderCheckup headerCheckup = getHeaderCheckup(rawatInap.getNoCheckup());
                        rawatInap.setIdPasien(headerCheckup.getIdPasien());
                        rawatInap.setNamaPasien(headerCheckup.getNama());
                        rawatInap.setAlamat(headerCheckup.getJalan());
                        rawatInap.setDesa(headerCheckup.getNamaDesa());
                        rawatInap.setKecamatan(headerCheckup.getNamaKecamatan());
                        rawatInap.setKota(headerCheckup.getNamaKota());
                        rawatInap.setProvinsi(headerCheckup.getNamaProvinsi());
                        rawatInap.setIdPelayanan(headerCheckup.getIdPelayanan());
                        rawatInap.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if (headerCheckup.getJenisKelamin() != null) {
                            if ("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
                                jk = "Perempuan";
                            } else {
                                jk = "laki-Laki";
                            }
                        }
                        rawatInap.setJenisKelamin(jk);
                        rawatInap.setTempatLahir(headerCheckup.getTempatLahir());
                        rawatInap.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
                        rawatInap.setTempatTglLahir(headerCheckup.getTempatLahir() + ", " + formatDate);
                        rawatInap.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setNik(headerCheckup.getNoKtp());
                        rawatInap.setUrlKtp(headerCheckup.getUrlKtp());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                        long millis = System.currentTimeMillis();
                        java.util.Date dateToday = new java.util.Date(millis);
                        String today = new SimpleDateFormat("dd-MM-yyyy").format(dateToday);

                        setRawatInap(rawatInap);

                        break;
                    }
                }

            } else {
                setRawatInap(new RawatInap());
            }
        } else {
            setRawatInap(new RawatInap());
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

        RawatInap rawatInap = new RawatInap();
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

        String idResep = getIdResep();
        String id = getId();
        String jk = "";

        HeaderCheckup headerCheckup = getHeaderCheckup(id);
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
        reportParams.put("resepId", idResep);
        reportParams.put("logo", CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_NMU);
        reportParams.put("nik", headerCheckup.getNoKtp());
        reportParams.put("nama", headerCheckup.getNama());
        reportParams.put("tglLahir", headerCheckup.getTempatLahir() + ", " + headerCheckup.getStTglLahir().toString());
        if ("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        reportParams.put("jenisKelamin", jk);
        reportParams.put("jenisPasien", jenisPriksaPasien.getKeterangan());
        reportParams.put("poli", headerCheckup.getNamaPelayanan());
        reportParams.put("provinsi", headerCheckup.getNamaProvinsi());
        reportParams.put("kabupaten", headerCheckup.getNamaKota());
        reportParams.put("kecamatan", headerCheckup.getNamaKecamatan());
        reportParams.put("desa", headerCheckup.getNamaDesa());


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_resep";
    }

    public List<SkorRanap> getListParameterByKategori(String noCheckup, String idDetailCheckup, String kategori){
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

    public List<ImSimrsSkorRanapEntity> getListSkorRanapByParam(String iParameter){
        logger.info("[RawatInapAction.getListSkorByKategori] start process >>>");

        List<ImSimrsSkorRanapEntity> skorRanapEntities = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        skorRanapEntities = rawatInapBo.getListMasterSkor(iParameter);

        logger.info("[RawatInapAction.getListSkorByKategori] end process <<<");
        return skorRanapEntities;
    }

    public CrudResponse saveSkorRanapByKategori(String noCheckup, String idDetail, String kategori, String jsonString) throws JSONException{

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

    public List<SkorRanap> getListGroupSkorRanap(String noCheckup, String idDetail, String kategori){
        logger.info("[RawatInapAction.getListGroupSkorRanap] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        logger.info("[RawatInapAction.getListGroupSkorRanap] end process <<<");
        return rawatInapBo.getListSumSkorRanap(noCheckup, idDetail, kategori);
    }

    public ImSimrsKategoriSkorRanapEntity getKategoriSkorRanap(String id){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.kategoriSkorRanap(id);
    }

    public List<SkorRanap> getListViewSkorRanapByGrupId(String groupId){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        SkorRanap skorRanap = new SkorRanap();
        skorRanap.setGroupId(groupId);

        return rawatInapBo.getListSkorRanap(skorRanap);
    }

    public List<MonVitalSign> getListMonVitalSign(String noCheckup, String idDetailCheckup, String id){

        MonVitalSign monVitalSign = new MonVitalSign();

        if ("".equalsIgnoreCase(noCheckup))
            monVitalSign.setNoCheckup(noCheckup);

        if ("".equalsIgnoreCase(idDetailCheckup))
            monVitalSign.setIdDetailCheckup(idDetailCheckup);

        if ("".equalsIgnoreCase(id))
            monVitalSign.setId(id);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.getListMonVitalSign(monVitalSign);
    }

    public CrudResponse saveMonVitalSign(String noCheckup, String idDetail, String jsonString) throws JSONException{

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

            monVitalSignEntity.setFlag("Y");
            monVitalSignEntity.setAction("U");
            monVitalSignEntity.setCreatedDate(now);
            monVitalSignEntity.setCreatedWho(userLogin);
            monVitalSignEntity.setLastUpdate(now);
            monVitalSignEntity.setLastUpdateWho(userLogin);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        return rawatInapBo.saveMonVitalSign(monVitalSignEntity);
    }

}