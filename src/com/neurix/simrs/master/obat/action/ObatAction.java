package com.neurix.simrs.master.obat.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.CrudResponse;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
            obatList = obatBoProxy.getByCriteria(obat);
        } catch (HibernateException e){
            logger.error("[ObatAction.search] ERROR when get data list obat, ", e);
            addActionError("[ObatAction.search] ERROR when get data list obat, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", obatList);

        logger.info("[ObatAction.search] END <<<<<<<");
        return "search";

    }

    @Override
    public String initForm(){
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

    public List<Obat> listObat(String idJenisObat){

        logger.info("[ObatAction.listObat] start process >>>");
        List<Obat> obatList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getListObatByJenisObat(idJenisObat,branchId);
        }catch (GeneralBOException e){
            logger.error("[ObatAction.listObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.listObat] end process >>>");
        return obatList;

    }

    public List<Obat> getStokObat(String idObat){

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
        }catch (GeneralBOException e){
            logger.error("[ObatAction.getStokObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.getStokObat] end process >>>");
        return obatList;

    }

    public List<Obat> listObatByJenis(String idjenisObat){

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
        }catch (GeneralBOException e){
            logger.error("[ObatAction.listObatByJenis] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.listObatByJenis] end process >>>");
        return obatList;

    }

    public CheckObatResponse saveObat(String namaObat, List <String> jenisObat, String merek, String pabrik, BigInteger box, BigInteger lembarBox, BigInteger lembar, BigInteger bijiLembar, BigInteger biji, BigDecimal hargaBox, BigDecimal hargaLembar, BigDecimal hargaBiji){
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

        try {
            checkObatResponse = obatBo.checkFisikObatByIdPabrik(obat);
        }catch (GeneralBOException e){
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("[ERROR] "+e.getMessage());
        }

        if("success".equalsIgnoreCase(checkObatResponse.getStatus())){
            try {
                obatBo.saveAdd(obat, jenisObat);
            }catch (GeneralBOException e) {
                checkObatResponse.setStatus("error");
                logger.error("[ObatInapAction.saveObatInap] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
                checkObatResponse.setMessage("[ERROR] "+e.getMessage());
            }
        }

        logger.info("[ObatAction.saveObatInap] end process >>>");

        return checkObatResponse;
    }

    public String editObat(String idObat, String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger box, BigInteger lembarBox, BigInteger lembar, BigInteger bijiLembar, BigInteger biji, BigDecimal hargaBox, BigDecimal hargaLembar, BigDecimal hargaBiji){
        logger.info("[ObatAction.saveObatInap] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

            Obat obat = new Obat();
            obat.setIdSeqObat(idObat);
            obat.setNamaObat(namaObat);
            obat.setQtyBox(box);
            obat.setQtyLembar(lembar);
            obat.setQtyBiji(biji);
            obat.setMerk(merek);
            obat.setIdPabrik(pabrik);
            obat.setLembarPerBox(lembarBox);
            obat.setBijiPerLembar(bijiLembar);
            obat.setAverageHargaBox(hargaBox);
            obat.setAverageHargaLembar(hargaLembar);
            obat.setAverageHargaBiji(hargaBiji);
            obat.setLastUpdate(updateTime);
            obat.setLastUpdateWho(userLogin);
            obat.setBranchId(userArea);
            obat.setAction("U");

            obatBo.saveEdit(obat, jenisObat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatInapAction.saveObatInap] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ObatAction.saveObatInap] end process >>>");
        return SUCCESS;
    }

    public List<Obat> getJenisObatByIdObat(String idObat){

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
        }catch (GeneralBOException e){
            logger.error("[ObatAction.getJenisObatByIdObat] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.getJenisObatByIdObat] end process >>>");
        return obatList;

    }

    public String getListObat(){

        logger.info("[ObatAction.getListObat] start process >>>");

        List<Obat> obatList = new ArrayList<>();
        Obat obat = new Obat();
        obat.setBranchId(CommonUtil.userBranchLogin());
        obat.setFlag("Y");

        try {
            obatList = obatBoProxy.getListObatGroup(obat);
        }catch (GeneralBOException e){
            logger.error("[ObatAction.getListObat] Error when obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfObat.addAll(obatList);
        logger.info("[ObatAction.getListObat] end process <<<");
        return SUCCESS;

    }

    public List<Obat> getListNamaObat(String namaObat){

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
        }catch (GeneralBOException e){
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

    public String searchHargaObat(){
        logger.info("[PermintaanVendorAction.searchHargaObat] START process <<<");

        Obat obat = getObat();
        obat.setBranchId(CommonUtil.userBranchLogin());

        List<Obat> obats = new ArrayList<>();
        try {
            obats = obatBoProxy.getListHargaObat(obat);
        } catch (GeneralBOException e){
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

    public List<Obat> searchHargaObat(String idObat){
        logger.info("[PermintaanVendorAction.searchHargaObat] START process <<<");

        Obat obat = getObat();

        List<Obat> obats = new ArrayList<>();
        try {
            obats = obatBoProxy.getListHargaObat(obat);
        } catch (GeneralBOException e){
            logger.error("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return obats;
        }

        logger.info("[PermintaanVendorAction.searchHargaObat] END process <<<");
        return obats;
    }

    public CrudResponse saveHargaObat(String jsonString) throws JSONException{

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
        } catch (GeneralBOException e){
            logger.error("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            response.setStatus("error");
            response.setMsg("[ReportAction.searchHargaObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin."+ e);
            return response;
        }
        return response;
    }

}