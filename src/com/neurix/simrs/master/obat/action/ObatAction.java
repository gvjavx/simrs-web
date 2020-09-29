package com.neurix.simrs.master.obat.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.*;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import io.agora.recording.common.Common;
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
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ObatAction.class);
    private ObatBo obatBoProxy;
    private PelayananBo pelayananBoProxy;
    private Obat obat;
    private String idPabrik;
    private String type;
    private List<Obat> listOfObat = new ArrayList<>();
    List<TransaksiStok> report = new ArrayList<>();
    List<Aging> myList = new ArrayList<>() ;

    public List<Aging> getMyList() {
        return myList;
    }

    public void setMyList(List<Aging> myList) {
        this.myList = myList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TransaksiStok> getReport() {
        return report;
    }

    public void setReport(List<TransaksiStok> report) {
        this.report = report;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public List<Obat> getListOfObat() {
        return listOfObat;
    }

    public void setListOfObat(List<Obat> listOfObat) {
        this.listOfObat = listOfObat;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ObatAction.logger = logger;
    }

    public ObatBo getObatBoProxy() {
        return obatBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }

    public Obat getObat() {
        return obat;
    }

    public void setObat(Obat obat) {
        this.obat = obat;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    @Override
    public String add() {

        logger.info("[ObatAction.add] start process >>>");

        Obat obat = new Obat();
        setObat(obat);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ObatAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {

        logger.info("[ObatAction.search] START >>>>>>>");

        Obat obat = getObat();
        obat.setBranchId(CommonUtil.userBranchLogin());

        List<Obat> obatList = new ArrayList<>();
        try {
            obatList = obatBoProxy.getListObatByGroup(obat);
        } catch (HibernateException e) {
            logger.error("[ObatAction.search] ERROR when get data list obat, ", e);
            addActionError("[ObatAction.search] ERROR when get data list obat, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", obatList);

        logger.info("[ObatAction.search] END <<<<<<<");
        return "search";

    }

    @Override
    public String initForm() {
        logger.info("[ObatAction.initForm] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        setObat(new Obat());
        logger.info("[ObatAction.initForm] END <<<<<<<");
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

    public List<Obat> listObat(String idJenisObat) {

        logger.info("[ObatAction.listObat] start process >>>");
        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getListObatByJenisObat(idJenisObat, branchId);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.listObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.listObat] end process >>>");
        return obatList;

    }

    public List<Obat> getStokObat(String idObat) {

        logger.info("[ObatAction.getStokObat] start process >>>");
        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();
        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setBranchId(branchId);
        obat.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getByCriteria(obat);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.getStokObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.getStokObat] end process >>>");
        return obatList;

    }

    public List<Obat> listObatByJenis(String idjenisObat) {

        logger.info("[ObatAction.listObatByJenis] start process >>>");

        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();
        Obat obat = new Obat();
        obat.setIdJenisObat(idjenisObat);
        obat.setBranchId(branchId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getJenisObat(obat);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.listObatByJenis] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.listObatByJenis] end process >>>");
        return obatList;

    }

    public CheckObatResponse saveObat(String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger box, BigInteger lembarBox, BigInteger lembar, BigInteger bijiLembar, BigInteger biji, BigDecimal hargaBox, BigDecimal hargaLembar, BigDecimal hargaBiji, BigInteger minStok) {
        logger.info("[ObatAction.saveObatInap] start process >>>");

        CheckObatResponse checkObatResponse = new CheckObatResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

        Obat obat = new Obat();
        obat.setNamaObat(namaObat);
        obat.setMerk(merek);
        obat.setIdPabrik(pabrik);
        obat.setQtyBox(box);
        obat.setLembarPerBox(lembarBox);
        obat.setQtyLembar(lembar);
        obat.setBijiPerLembar(bijiLembar);
        obat.setQtyBiji(biji);
        obat.setAverageHargaBox(hargaBox);
        obat.setAverageHargaLembar(hargaLembar);
        obat.setAverageHargaBiji(hargaBiji);
        obat.setCreatedDate(updateTime);
        obat.setCreatedWho(userLogin);
        obat.setLastUpdate(updateTime);
        obat.setLastUpdateWho(userLogin);
        obat.setBranchId(userArea);
        obat.setFlag("Y");
        obat.setAction("C");
        obat.setMinStok(minStok);
        obat.setHargaTerakhir(new BigDecimal(String.valueOf(0)));

        if (kandunganObats != null && kandunganObats.size() > 0){
            obat.setKandunganObats(kandunganObats);
        }

        try {
            checkObatResponse = obatBo.checkFisikObatByIdPabrik(obat);
        } catch (GeneralBOException e) {
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("[ERROR] " + e.getMessage());
            return checkObatResponse;
        }

        if ("success".equalsIgnoreCase(checkObatResponse.getStatus())) {
            try {
                obatBo.saveAdd(obat, jenisObat);
            } catch (GeneralBOException e) {
                checkObatResponse.setStatus("error");
                logger.error("[ObatInapAction.saveObatInap] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
                checkObatResponse.setMessage("[ERROR] " + e.getMessage());
            }
        }

        logger.info("[ObatAction.saveObatInap] end process >>>");

        return checkObatResponse;
    }

    public CheckObatResponse editObat(String idObat, String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger lembarBox, BigInteger bijiLembar, BigInteger minStok) {
        logger.info("[ObatAction.saveObatInap] start process >>>");
        CheckObatResponse response = new CheckObatResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

            Obat obat = new Obat();
            obat.setIdObat(idObat);
            obat.setNamaObat(namaObat);
            obat.setMerk(merek);
            obat.setIdPabrik(pabrik);
            obat.setLembarPerBox(lembarBox);
            obat.setBijiPerLembar(bijiLembar);
            obat.setLastUpdate(updateTime);
            obat.setLastUpdateWho(userLogin);
            obat.setBranchId(userArea);
            obat.setAction("U");
            obat.setMinStok(minStok);

            if (kandunganObats != null && kandunganObats.size() > 0){
                List<KandunganObat> kandunganObatsFiltered = kandunganObats.stream().filter(p -> p.getIdObat().equalsIgnoreCase(idObat)).collect(Collectors.toList());
                if (kandunganObatsFiltered.size() > 0){
                    obat.setKandunganObats(kandunganObatsFiltered);
                }
            }

            response = obatBo.saveEdit(obat, jenisObat);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatInapAction.saveObatInap] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            response.setStatus("error");
            response.setMessage("Found Error when save edit obat " + e.getMessage());
        }

        logger.info("[ObatAction.saveObatInap] end process >>>");
        return response;
    }

    public List<Obat> getJenisObatByIdObat(String idObat) {

        logger.info("[ObatAction.getJenisObatByIdObat] start process >>>");

        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setBranchId(branchId);

        try {
            obatList = obatBo.getJenisObat(obat);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.getJenisObatByIdObat] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.getJenisObatByIdObat] end process >>>");
        return obatList;

    }

    public String getListObat() {

        logger.info("[ObatAction.getListObat] start process >>>");

        List<Obat> obatList = new ArrayList<>();
        Obat obat = new Obat();
        obat.setBranchId(CommonUtil.userBranchLogin());
        obat.setFlag("Y");

        try {
            obatList = obatBoProxy.getListObatGroup(obat);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.getListObat] Error when obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfObat.addAll(obatList);
        logger.info("[ObatAction.getListObat] end process <<<");
        return SUCCESS;

    }

    public List<Obat> getListNamaObat(String namaObat) {

        logger.info("[ObatAction.getListNamaObat] start process >>>");
        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();
        Obat obat = new Obat();
        obat.setNamaObat(namaObat);
        obat.setBranchId(branchId);
        obat.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getListNamaObat(obat);
        } catch (GeneralBOException e) {
            logger.error("[ObatAction.getListNamaObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.getListNamaObat] end process >>>");
        return obatList;

    }

    public String printIDPabrik() {

        logger.info("[PermintaanVendorAction.printBarcodeBarang] START process <<<");

        String idPabrik = getIdPabrik();

        reportParams.put("idPabrik", idPabrik);

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printBarcodeBarang] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        logger.info("[PermintaanVendorAction.printBarcodeBarang] END process <<<");
        return "print_barcode_id_pabrik";
    }

    public String searchHargaObat() {
        logger.info("[PermintaanVendorAction.searchHargaObat] START process <<<");

        Obat obat = getObat();
        obat.setBranchId(CommonUtil.userBranchLogin());

        List<Obat> obats = new ArrayList<>();
        try {
            obats = obatBoProxy.getListHargaObat(obat);
        } catch (GeneralBOException e) {
            logger.error("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", obats);

        logger.info("[PermintaanVendorAction.searchHargaObat] END process <<<");
        return "search";
    }

    public List<Obat> searchHargaObat(String idObat) {
        logger.info("[PermintaanVendorAction.searchHargaObat] START process <<<");

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setBranchId(CommonUtil.userBranchLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        List<Obat> obats = new ArrayList<>();
        try {
            obats = obatBo.getListHargaObat(obat);
        } catch (GeneralBOException e) {
            logger.error("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return obats;
        }

        logger.info("[PermintaanVendorAction.searchHargaObat] END process <<<");
        return obats;
    }

    public CrudResponse saveHargaObat(String idObat, String idBarang, String jsonString) throws JSONException {

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String branchId = CommonUtil.userBranchLogin();

        // maping untuk parameter lainnua
        JSONArray json = new JSONArray(jsonString);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        HargaObat hargaObat = new HargaObat();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            hargaObat.setIdHargaObat(idObat+branchId);
            hargaObat.setIdObat(idObat);
            hargaObat.setIdBarang(idBarang);
            hargaObat.setHargaNet(new BigDecimal(obj.getString("harga_net")));
            hargaObat.setDiskon(new BigDecimal(obj.getString("diskon")));
            hargaObat.setHargaJual(new BigDecimal(obj.getString("harga_jual")));
            hargaObat.setCreatedDate(time);
            hargaObat.setCreatedWho(userLogin);
            hargaObat.setLastUpdate(time);
            hargaObat.setLastUpdateWho(userLogin);
        }

        try {
            obatBo.saveHargaObat(hargaObat);
            response.setStatus("success");
        } catch (GeneralBOException e) {
            logger.error("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            response.setStatus("error");
            response.setMsg("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin." + e);
            return response;
        }
        return response;
    }

    public List<Obat> getListObatDetail(String idObat) {
        List<Obat> obatList = new ArrayList<>();

        if (idObat != null && !"".equalsIgnoreCase(idObat)) {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
            Obat obat = new Obat();
            obat.setIdObat(idObat);
            obat.setBranchId(CommonUtil.userBranchLogin());

            try {
                obatList = obatBo.getListObatDetail(obat);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search by id Obat " + e.getMessage());
            }
        }

        return obatList;
    }

    public CheckResponse saveReturObat(String jsonString, String data) throws JSONException {
        CheckResponse response = new CheckResponse();
        List<Obat> obatList = new ArrayList<>();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        String branchId = CommonUtil.userBranchLogin();
        String pelayananId = CommonUtil.userPelayananIdLogin();


        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {

            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Obat obat = new Obat();
                obat.setIdObat(obj.getString("id_obat"));
                obat.setIdBarang(obj.getString("id_barang"));
                //obat.setQty(new BigInteger(obj.getString("qty")));
                obatList.add(obat);
            }

            JSONObject object = new JSONObject(data);

            Obat bean = new Obat();
            if (data != null) {
                bean.setQty(new BigInteger(object.getString("qty_total")));
                bean.setIdVendor(object.getString("id_vendor"));

                ImSimrsVendorEntity vendorEntity = vendorBo.getEntityVendorById(bean.getIdVendor());
                if (vendorEntity != null){
                    bean.setNamaVendor(vendorEntity.getNamaVendor());
                }

                bean.setBranchId(CommonUtil.userBranchLogin());
                bean.setTglRetur(Timestamp.valueOf(object.getString("tgl_retur")));
                bean.setCreatedDate(time);
                bean.setCreatedWho(userLogin);
                bean.setLastUpdateWho(userLogin);
                bean.setLastUpdate(time);
            }

            String divisiId = "";
            String pelayananName = "";
            ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(CommonUtil.userPelayananIdLogin());
            if (pelayananEntity != null){

                ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                if (position != null){
                    divisiId = position.getKodering();
                }

            } else {
                logger.error("[ObatAction.saveReturObat] Tidak ditemukan divisi_id.");
                response.setStatus("error");
                response.setMessage("[ObatAction.saveReturObat] Tidak ditemukan divisi_id.");
                return response;
            }

            List<Map> listMapObat = new ArrayList<>();
            BigDecimal totalHarga = new BigDecimal(0);
            for (Obat obat : obatList){


                ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(obat.getIdBarang());
                BigDecimal harga = new BigDecimal(0);
                if (obatEntity != null){

                    BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());
                    BigInteger consLembar = obatEntity.getBijiPerLembar();

                    // get total qty (satuan terkecil)
                    BigInteger ttlQtyBox = obatEntity.getQtyBox().multiply(cons);
                    BigInteger ttlQtyLembar = obatEntity.getQtyLembar().multiply(consLembar);
                    BigInteger ttlQtyTerkecil = ttlQtyBox.add(ttlQtyLembar).add(obatEntity.getQtyBiji());

                    harga = obatEntity.getHargaTerakhir().multiply( new BigDecimal(ttlQtyTerkecil));

                    // set qty
                    obat.setQty(ttlQtyTerkecil);
                    obat.setHargaTerakhir(obatEntity.getHargaTerakhir());
                    obat.setAverageHargaBiji(obatEntity.getAverageHargaBiji());
                    obat.setIdVendor(bean.getIdVendor());
                    obat.setNamaVendor(bean.getNamaVendor());
                    obat.setLastUpdate(time);
                    obat.setLastUpdateWho(userLogin);
                    obat.setIdPelayanan(pelayananId);
                    obat.setBranchId(branchId);
                    obat.setCreatedWho(userLogin);
                    obat.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    obatBo.saveTransaksiStokOpname(obat);

                } else {
                    logger.error("[ObatAction.saveReturObat] Tidak ditemukan data Obat.");
                    response.setStatus("error");
                    response.setMessage("[ObatAction.saveReturObat] Tidak ditemukan data Obat.");
                    return response;
                }
                totalHarga = totalHarga.add(harga);

                Map mapObat = new HashMap();
                mapObat.put("kd_barang", obat.getIdBarang());
                mapObat.put("nilai", harga);
                listMapObat.add(mapObat);
            }



            Map mapBiaya = new HashMap();
            mapBiaya.put("divisi_id", divisiId);
            mapBiaya.put("nilai", totalHarga);

            Map mapJurnal = new HashMap();
            mapJurnal.put("biaya_persediaan_obat", mapBiaya);
            mapJurnal.put("persediaan_gudang", listMapObat);

            String catatan = "Retur Barang Gudang ke Vendor. "+bean.getIdVendor()+" - "+ bean.getNamaVendor();

            try {
                Jurnal jurnal = billingSystemBo.createJurnal("35", mapJurnal, branchId, catatan, "Y");
                bean.setNoJurnal(jurnal.getNoJurnal());
            } catch (GeneralBOException e) {
                response.setStatus("error");
                logger.error("[ObatAction.saveReturObat] ERROR. ", e);
                response.setStatus("error");
                response.setMessage("[ObatAction.saveReturObat] ERROR. " + e);
                return response;
            }

            try {
                response = obatBo.saveReturObat(bean, obatList);
            } catch (GeneralBOException e) {
                logger.error("Found Error", e);
                response.setStatus("error");
                response.setMessage("Found error " + e);
            }
        }

        return response;
    }

    public String searchRetureObat() {

        logger.info("[ObatAction.search] START >>>>>>>");
        Obat obat = getObat();
        obat.setBranchId(CommonUtil.userBranchLogin());
        List<Obat> obatList = new ArrayList<>();

        try {
            obatList = obatBoProxy.searchReturObat(obat);
        } catch (HibernateException e) {
            logger.error("[ObatAction.search] ERROR when get data list obat, ", e);
            throw new GeneralBOException("when get data list obat" + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", obatList);
        logger.info("[ObatAction.search] END <<<<<<<");
        return "search";

    }

    public List<Obat> detailReturObat(String idRetur) {
        List<Obat> obatList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.detailReturObat(idRetur);
        } catch (GeneralBOException e) {
            logger.error("Found Error");
        }

        return obatList;
    }

    public List<Obat> returObatByVendor(String idVendor) {
        List<Obat> obatList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        try {
            obatList = obatBo.searchObatByVendor(idVendor, branchId);
        } catch (GeneralBOException e) {
            logger.error("Found Error");
        }

        return obatList;
    }


    public String initPrintReportRiwayat(){
        logger.info("[ObatAction.initPrintReportRiwayat] START >>>");

        String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(new Timestamp(System.currentTimeMillis()));

        Obat obat = new Obat();
        obat.setStTglFrom(formatDate);
        obat.setStTglTo(formatDate);
        setObat(obat);

        logger.info("[ObatAction.initPrintReportRiwayat] END <<<");
        if ("sumary".equalsIgnoreCase(this.type)){
            return "init_print_sumary";
        } else {
            return "init_print";
        }
    }

    public String printReportRiwayat(){
        logger.info("[ObatAction.initPrintReportRiwayat] START >>>");

        Obat obat = getObat();

        String branch = CommonUtil.userBranchLogin();
        String branchName = CommonUtil.userBranchNameLogin();
        String logo = "";
        Branch branches = new Branch();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            branches = branchBo.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        String namaPelayanan = "";
        ImSimrsPelayananEntity pelayananEntity = new ImSimrsPelayananEntity();
        try {
            pelayananEntity = pelayananBoProxy.getPelayananById(obat.getIdPelayanan());
        } catch (GeneralBOException e){
            logger.error("[ObatAction.printReportRiwayat] ERROR. ",e);
            throw new GeneralBOException("[ObatAction.printReportRiwayat] ERROR. "+e.getMessage());
        }

        if (pelayananEntity != null){
            namaPelayanan = pelayananEntity.getNamaPelayanan();
        }

        // report list

        if ("sumary".equalsIgnoreCase(obat.getType())){
            try {
                report = obatBo.getListReportSumaryTransaksiObat(obat.getIdPelayanan(), obat.getTahun(), obat.getBulan());
            } catch (GeneralBOException e){
                logger.error("[ObatAction.initPrintReportRiwayat] ERROR. ", e);
                throw new GeneralBOException("[ObatAction.initPrintReportRiwayat] ERROR. " + e);
            }
        } else {
            try {
                report = obatBo.getListReporTransaksiObat(obat.getIdPelayanan(), obat.getTahun(), obat.getBulan(), obat.getIdObat());
            } catch (GeneralBOException e){
                logger.error("[ObatAction.initPrintReportRiwayat] ERROR. ", e);
                throw new GeneralBOException("[ObatAction.initPrintReportRiwayat] ERROR. " + e);
            }
        }


        String namaObat = "";
        if (report.size() > 0){
            TransaksiStok stok = report.get(0);
            namaObat = stok.getNamaObat();
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(new Timestamp(System.currentTimeMillis()));
        reportParams.put("tglForm", obat.getStTglFrom());
        reportParams.put("tglTo", obat.getStTglTo());
        reportParams.put("area", CommonUtil.userAreaName());
        reportParams.put("unit", branchName);
        reportParams.put("logo", logo);
        reportParams.put("printDate", formatDate);
        reportParams.put("qtyLalu", new BigDecimal(0));
        reportParams.put("totalLalu", new BigDecimal(0));
        reportParams.put("subTotalLalu", new BigDecimal(0));
        reportParams.put("namaObat", namaObat);
        reportParams.put("idObat", obat.getIdObat());
        reportParams.put("namaPelayanan", namaPelayanan);
        reportParams.put("bulan", obat.getBulan());
        reportParams.put("tahun", obat.getTahun());
        reportParams.put("periode", obat.getBulan()+"-"+obat.getTahun());

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        logger.info("[ObatAction.initPrintReportRiwayat] END <<<");
        if ("sumary".equalsIgnoreCase(obat.getType())){
            return "print_sumary";
        } else {
            return "print_riwayat";
        }
    }

    public CrudResponse createSessionKandunganObat(String idObat){
        logger.info("[ObatAction.listKandunganObat] START >>>");

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        List<KandunganObat> kandunganObats = new ArrayList<>();

        try {
            kandunganObats = obatBo.getListKandunganObatDetail(idObat);
            response.setStatus("success");
        } catch (GeneralBOException e){
            String erMsg = "[ReportAction.createSessionKandunganObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.";
            logger.error(erMsg, e);
            response.setMsg(erMsg + e);
            response.setStatus("error");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfKandunganObat");
        session.setAttribute("listOfKandunganObat", kandunganObats);

        logger.info("[ObatAction.createSessionKandunganObat] END <<<");
        return response;
    }

    public List<KandunganObat> getListFromSessionObat(String idObat){
        logger.info("[ObatAction.getListFromSessionObat] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

        if (kandunganObats != null && kandunganObats.size() > 0){
            List<KandunganObat> kandunganObatList = kandunganObats.stream().filter(p->p.getIdObat().equalsIgnoreCase(idObat)).collect(Collectors.toList());
            if (kandunganObatList.size() > 0){

                session.removeAttribute("listOfKandunganObat");
                session.setAttribute("listOfKandunganObat", kandunganObatList);
                return kandunganObatList;
            } else {
                session.removeAttribute("listOfKandunganObat");
                session.setAttribute("listOfKandunganObat", kandunganObats);
                return kandunganObats;
            }

        }
        logger.info("[ObatAction.getListFromSessionObat] END <<<");
        return new ArrayList<>();
    }

    public CrudResponse editKandunganObatDetail( String idObatKandunganObatDetail,
                                                 String idObat,
                                                 String idKandungan,
                                                 String bentuk,
                                                 String sediaan,
                                                 String satuan ){

        logger.info("[ObatAction.editKandunganObatDetail] START >>>");

        CrudResponse response = new CrudResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        if (kandunganObats.size() > 0){
            List<KandunganObat> kandunganObatList = kandunganObats.stream().filter(p->p.getId().equalsIgnoreCase(idObatKandunganObatDetail)).collect(Collectors.toList());
            if (kandunganObatList.size() > 0){

                for (KandunganObat kandunganObat : kandunganObatList){

                    BigDecimal bSediaan = new BigDecimal(0);
                    if ( sediaan != null && !"".equalsIgnoreCase(sediaan))
                        bSediaan = new BigDecimal(sediaan);

                    kandunganObat.setIdObat(idObat);
                    kandunganObat.setIdKandungan(idKandungan);
                    kandunganObat.setBentuk(bentuk);

                    if (bentuk != null && !"".equalsIgnoreCase(bentuk)){
                        ImSimrsBentukBarangEntity bentukBarangEntity = obatBo.getBentukBarangById(bentuk);
                        if (bentukBarangEntity != null){
                            kandunganObat.setNamaBentuk(bentukBarangEntity.getBentuk());
                        }
                    }

                    kandunganObat.setSediaan(bSediaan);
                    kandunganObat.setSatuanSediaan(satuan);

                }
                session.removeAttribute("listOfKandunganObat");
                session.setAttribute("listOfKandunganObat", kandunganObats);
                response.setStatus("success");
            }
        }
        logger.info("[ObatAction.editKandunganObatDetail] END <<<");
        return response;
    }

    public CrudResponse addKandunganObat( String idObat,
                                          String idKandungan,
                                          String bentuk,
                                          String sediaan,
                                          String satuan ){

        logger.info("[ObatAction.listKandunganObat] START >>>");

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        BigDecimal bSediaan = new BigDecimal(0);
        if ( sediaan != null && !"".equalsIgnoreCase(sediaan))
            bSediaan = new BigDecimal(sediaan);

        KandunganObat kandunganObat = new KandunganObat();
        kandunganObat.setId(obatBo.generateIdKandungan());
        kandunganObat.setIdObat(idObat);
        kandunganObat.setIdKandungan(idKandungan);
        kandunganObat.setBentuk(bentuk);
        kandunganObat.setSediaan(bSediaan);
        kandunganObat.setSatuanSediaan(satuan);

        if (idObat != null && !"".equalsIgnoreCase(idObat)){
            ImSimrsObatEntity obatEntity = obatBo.getObatByIdObat(idObat);
            if (obatEntity != null){
                kandunganObat.setNamaObat(obatEntity.getNamaObat());
            }
        }

        if (idKandungan != null && !"".equalsIgnoreCase(idKandungan)){
            ImSimrsKandunganObatEntity kandunganObatEntity = obatBo.getMasterKandunganObatById(idKandungan);
            if (kandunganObatEntity != null){
                kandunganObat.setKandungan(kandunganObatEntity.getKandungan());
            }
        }

        if (bentuk != null && !"".equalsIgnoreCase(bentuk)){
            ImSimrsBentukBarangEntity bentukBarangEntity = obatBo.getBentukBarangById(bentuk);
            if (bentukBarangEntity != null){
                kandunganObat.setNamaBentuk(bentukBarangEntity.getBentuk());
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

        if (kandunganObats != null && kandunganObats.size() > 0){
            kandunganObats.add(kandunganObat);
        } else {
            kandunganObats = new ArrayList<>();
            kandunganObats.add(kandunganObat);
        }

        session.removeAttribute("listOfKandunganObat");
        session.setAttribute("listOfKandunganObat", kandunganObats);

        logger.info("[ObatAction.listKandunganObat] END <<<");
        response.setStatus("success");
        return response;
    }

    public KandunganObat initEditKandunganObat(String idKandunganDetail){
        logger.info("[ObatAction.listKandunganObat] START >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KandunganObat> kandunganObats = (List<KandunganObat>) session.getAttribute("listOfKandunganObat");

        List<KandunganObat> filterKandunganObats = kandunganObats.stream().filter(p->p.getId().equalsIgnoreCase(idKandunganDetail)).collect(Collectors.toList());
        if (filterKandunganObats.size() > 0)
            return filterKandunganObats.get(0);

        logger.info("[ObatAction.listKandunganObat] END <<<");
        return null;
    }

    public List<ImSimrsKandunganObatEntity> getListKandunganObat(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        return obatBo.getListAllKandunganObat();
    }

    public void resetSessionKandunganObat(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfKandunganObat");
    }

    public String initReportAging(){
        return "init_print_aging";
    }

    public String printReportAging(){

        Obat obat = getObat();
        String branchLogin = CommonUtil.userBranchLogin();
        String periode = obat.getBulan() + "-" + obat.getTahun();
        String periodeAging = obat.getTahun() + "-" + obat.getBulan();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        String []jumlahTanggalTahunKabisat = {"","31","29","31","30","31","30","31","31","30","31","30","31"};
        String []jumlahTanggalTahunBiasa = {"","31","28","31","30","31","30","31","31","30","31","30","31"};
        String jumlahTanggalTemp="";

        String periodeTitle[] = periode.split("-");
        if(Integer.parseInt(periodeTitle[0]) % 4 == 0){
            jumlahTanggalTemp=jumlahTanggalTahunKabisat[Integer.parseInt(periodeTitle[0])];
        }else{
            jumlahTanggalTemp=jumlahTanggalTahunBiasa[Integer.parseInt(periodeTitle[0])];
        }

        Branch branch = branchBo.getBranchById(branchLogin,"Y");
        List<Aging> agingList = obatBo.getListAging(branch.getBranchId(), obat.getIdPelayanan(), periodeAging);
        for(Aging data: agingList){
            Aging newData = new Aging();

            BigDecimal total = data.getTotal();
            newData.setTotal(data.getTotalAwal().abs());
            newData.setJumlah1(new BigDecimal(0));
            newData.setJumlah2(new BigDecimal(0));
            newData.setJumlah3(new BigDecimal(0));
            newData.setJumlah4(new BigDecimal(0));
            newData.setJumlah5(new BigDecimal(0));
            newData.setJumlah6(new BigDecimal(0));
            newData.setJumlah7(new BigDecimal(0));

            //Tentukan posisi umur aging.
            //Yaitu dengan mengammbil periode sedang berjalan (tanggal aging di cetak) dan periode tanggal dari jurnal tersebut
            int agingPosition = 0;
            String periodeJurnal = CommonUtil.convertDateToString(data.getTglJurnal());
            String periodeJurnalArrTemp[]= periodeJurnal.split("-");

            Integer tglCetak    = Integer.parseInt(jumlahTanggalTemp);
            Integer tglJurnal   = Integer.parseInt(periodeJurnalArrTemp[0]);
            Integer blnCetak    = Integer.parseInt(periodeTitle[0]);
            Integer blnJurnal   = Integer.parseInt(periodeJurnalArrTemp[1]);
            Integer thnCetak    = Integer.parseInt(periodeTitle[1]);
            Integer thnJurnal   = Integer.parseInt(periodeJurnalArrTemp[2]);

            if(thnCetak > thnJurnal){
                agingPosition=(((thnCetak-thnJurnal)*12)-blnJurnal)+blnCetak;
                if(tglCetak > tglJurnal) agingPosition+=1;
            }else{
                agingPosition=blnCetak-blnJurnal;
                if(tglCetak > tglJurnal) agingPosition+=1;
            }

            //umur 0 s/d 1 bulan
            if(agingPosition<=1){
                newData.setJumlah1(total.abs());
            }
            //umur 1 s/d 2 bulan
            else if(agingPosition<=2 && agingPosition>1){
                newData.setJumlah2(total.abs());
            }
            //umur 2 s/d 3 bulan
            else if(agingPosition<=3 && agingPosition>2){
                newData.setJumlah3(total.abs());
            }
            //umur 3 s/d 6 bulan
            else if(agingPosition<=6 && agingPosition>3){
                newData.setJumlah4(total.abs());
            }
            //umur 6 s/d 12 bulan
            else if(agingPosition<=12 && agingPosition>6){
                newData.setJumlah5(total.abs());
            }
            //umur 1 s/d 2 tahun
            else if(agingPosition<=24 && agingPosition>12){
                newData.setJumlah6(total.abs());
            }
            //umur > 2 tahun
            else{
                newData.setJumlah7(total.abs());
            }

            newData.setNoNota(data.getNoNota());
            newData.setKodeRekening(data.getKodeRekening());
            newData.setTglJurnal(data.getTglJurnal());
            newData.setMasterId(data.getMasterId());
            newData.setNamaMaster(data.getNamaMaster());
            newData.setNamaRekening(data.getNamaRekening());
            newData.setIdItem(data.getIdItem());
            newData.setNamaItem(data.getNamaItem());
            myList.add(newData);
        }

        if(myList.size()>0){
//            List<Aging> forReport = new ArrayList<>();
//
//            myList.addAll(forReport);
            reportParams.put("reportTitle", "AGING PERSEDIAAN OBAT");
            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
            reportParams.put("cabangId", branch.getBranchId());
            reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(obat.getBulan())+" "+obat.getTahun());
            java.util.Date now = new java.util.Date();
            reportParams.put("tanggal", CommonUtil.convertDateToString(now));
            reportParams.put("namaGeneralManager", "");
            reportParams.put("nipGeneralManager", "");
            reportParams.put("namaManagerKeuangan", "");
            reportParams.put("nipManagerKeuangan", "");
            reportParams.put("periode",periode);
            reportParams.put("kota",branch.getBranchName());
            reportParams.put("alamatSurat",branch.getAlamatSurat());
            reportParams.put("areaId",CommonUtil.userAreaName());
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
//                try {
//                    logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportAging");
//                } catch (GeneralBOException e1) {
//                    logger.error("[LaporanAkuntansiAction.printReportAging] Error when downloading ,", e1);
//                }
                logger.error("[LaporanAkuntansiAction.printReportAging] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
            logger.info("[LaporanAkuntansiAction.printReportAging] end process <<<");
            return "print_aging";
        }

        return "print_aging";
    }

}