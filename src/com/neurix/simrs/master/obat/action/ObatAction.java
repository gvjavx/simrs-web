package com.neurix.simrs.master.obat.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
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
import java.time.LocalDate;
import java.util.*;

public class ObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ObatAction.class);
    private ObatBo obatBoProxy;
    private Obat obat;
    private List<Obat> listOfObat = new ArrayList<>();
    private String idPabrik;

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

        try {
            checkObatResponse = obatBo.checkFisikObatByIdPabrik(obat);
        } catch (GeneralBOException e) {
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("[ERROR] " + e.getMessage());
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

        // maping untuk parameter lainnua
        JSONArray json = new JSONArray(jsonString);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        HargaObat hargaObat = new HargaObat();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
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


        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {

            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Obat obat = new Obat();
                obat.setIdObat(obj.getString("id_obat"));
                obat.setIdBarang(obj.getString("id_barang"));
                obat.setQty(new BigInteger(obj.getString("qty")));
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

            List<Map> listMapObat = new ArrayList<>();
            BigDecimal totalHarga = new BigDecimal(0);
            for (Obat obat : obatList){


                ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(obat.getIdBarang());
                BigDecimal harga = new BigDecimal(0);
                if (obatEntity != null){
                    harga = obatEntity.getAverageHargaBox().multiply( new BigDecimal(obat.getQty()));
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

            String divisiId = "";
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

            Map mapBiaya = new HashMap();
            mapBiaya.put("divisi_id", divisiId);
            mapBiaya.put("nilai", totalHarga);

            Map mapJurnal = new HashMap();
            mapJurnal.put("biaya_persediaan_obat", mapBiaya);
            mapJurnal.put("persediaan_gudang", listMapObat);

            String catatan = "Retur Barang Gudang ke Vendor. "+bean.getIdVendor()+" - "+ bean.getNamaVendor();
            String branchId = CommonUtil.userBranchLogin();

            try {
                String noJurnal = billingSystemBo.createJurnal("35", mapJurnal, branchId, catatan, "Y");
                bean.setNoJurnal(noJurnal);
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

}