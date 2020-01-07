package com.neurix.simrs.transaksi.permintaanvendor.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PermintaanVendorAction.class);
    private PermintaanVendorBo permintaanVendorBoProxy;
    private PermintaanVendor permintaanVendor;
    private VendorBo vendorBoProxy;
    private Vendor vendor;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    private List<Vendor> listOfVendor = new ArrayList<>();

    public List<Vendor> getListOfVendor() {
        return listOfVendor;
    }

    public void setListOfVendor(List<Vendor> listOfVendor) {
        this.listOfVendor = listOfVendor;
    }

    public void setVendorBoProxy(VendorBo vendorBoProxy) {
        this.vendorBoProxy = vendorBoProxy;
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

    public static void setLogger(Logger logger) {
        PermintaanVendorAction.logger = logger;
    }

    public void setPermintaanVendorBoProxy(PermintaanVendorBo permintaanVendorBoProxy) {
        this.permintaanVendorBoProxy = permintaanVendorBoProxy;
    }

    public PermintaanVendor getPermintaanVendor() {
        return permintaanVendor;
    }

    public void setPermintaanVendor(PermintaanVendor permintaanVendor) {
        this.permintaanVendor = permintaanVendor;
    }

    @Override
    public String add() {
        logger.info("[PermintaanVendorAction.add] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        setPermintaanVendor(new PermintaanVendor());

        logger.info("[PermintaanVendorAction.add] END <<<<<<<");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PermintaanVendorAction.edit] START >>>>>>>");

        String id = getId();
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(id);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorAction.edit] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.edit] ERROR error when get searh obat. " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfObatDetail");

        if (permintaanVendorList.size() > 0){
            setPermintaanVendor(permintaanVendorList.get(0));
            session.setAttribute("listOfObatDetail", permintaanVendorList.get(0).getListOfTransaksiObatDetail());

            Vendor vendor = new Vendor();
            vendor.setIdVendor(permintaanVendorList.get(0).getIdVendor());
            List<Vendor> vendorList = new ArrayList<>();

            try {
                vendorList = vendorBoProxy.getByCriteria(vendor);
            }catch (GeneralBOException e){
                logger.error("[PermintaanVendorAction.edit] ERROR error when get searh vendor. ", e);
                addActionError("[PermintaanVendorAction.edit] ERROR error when get searh vendor. " + e.getMessage());
            }

            Vendor vendorResult =  new Vendor();
            if (!vendorList.isEmpty()){
                vendorResult = vendorList.get(0);
                if(vendorResult != null){
                    setVendor(vendorResult);
                }
            }

        }

        logger.info("[PermintaanVendorAction.edit] END <<<<<<<");
        return "init_edit";
    }

    public CheckObatResponse checkIdPabrikan(String idObat, String idPabrikScan){
        logger.info("[PermintaanVendorAction.checkIdPabrikan] START >>>>>>>");

        CheckObatResponse checkObatResponse = new CheckObatResponse();

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setIdPabrik(idPabrikScan);
        obat.setBranchId(CommonUtil.userBranchLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        List<Obat> obats = new ArrayList<>();
        try {
            obats = obatBo.getByCriteria(obat);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorAction.checkIdPabrikan] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.checkIdPabrikan] ERROR error when get searh obat. " + e.getMessage());
        }

        if (obats.size() > 0){
            checkObatResponse.setStatus("success");
            checkObatResponse.setMessage("Obat Terverivikasi");
        } else {
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("Obat Tidak Ditemukan Check Kembali Obat Anda");
        }

        logger.info("[PermintaanVendorAction.checkIdPabrikan] END <<<<<<<");
        return checkObatResponse;
    }

    public String saveUpdateListObat(String idTransaksiDetailObat, String qty, String idPabrik, String flag){
        logger.info("[PermintaanVendorAction.checkIdPabrikan] START >>>>>>>");
        CheckObatResponse checkObatResponse = new CheckObatResponse();

        String msg = "";
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdTransaksiObatDetail(idTransaksiDetailObat);
        transaksiObatDetail.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        transaksiObatDetail.setLastUpdateWho(CommonUtil.userLogin());
        transaksiObatDetail.setQty(new BigInteger(qty));
        transaksiObatDetail.setIdPabrik(idPabrik);
        transaksiObatDetail.setFlagDiterima(flag);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            permintaanVendorBo.saveUpdateTransObatDetail(transaksiObatDetail);
            msg = SUCCESS;
        } catch (HibernateException e){
            logger.error("[PermintaanVendorAction.saveUpdateListObat] ERROR error when update data. ", e);
            msg = ERROR;
        }

        logger.info("[PermintaanVendorAction.checkIdPabrikan] END <<<<<<<");
        return msg;
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
        logger.info("[PermintaanVendorAction.search] START process >>>");

        PermintaanVendor permintaanVendor = getPermintaanVendor();
        List<PermintaanVendor> listOfPemintaanVendor = new ArrayList();
        permintaanVendor.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfPemintaanVendor = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanVendorAction.search] Error when searching permintan vendor by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfPemintaanVendor);

        logger.info("[PermintaanVendorAction.search] END process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PermintaanVendorAction.initForm] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        setPermintaanVendor(new PermintaanVendor());

        logger.info("[PermintaanVendorAction.initForm] END <<<<<<<");
        return "search";
    }

    public String savePermintaanPO(String idVendor, String po) {

        logger.info("[PermintaanVendorAction.savePermintaanPO] START >>>>>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        String userLogin = CommonUtil.userLogin();
        String userBranch = CommonUtil.userBranchLogin();
        String idPelayanan = CommonUtil.userPelayananIdLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setCreatedWho(userLogin);
        permintaanVendor.setCreatedDate(time);
        permintaanVendor.setLastUpdateWho(userLogin);
        permintaanVendor.setLastUpdate(time);
        permintaanVendor.setAction("C");
        permintaanVendor.setFlag("Y");
        permintaanVendor.setIdVendor(idVendor);
        permintaanVendor.setBranchId(userBranch);
        permintaanVendor.setIdPelayanan(idPelayanan);

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        TransaksiObatDetail obatDetail;

        try {
            if(po != null && !"".equalsIgnoreCase(po)){
                JSONArray json = new JSONArray(po);
                for (int i = 0; i < json.length(); i++) {
                    obatDetail = new TransaksiObatDetail();

                    JSONObject obj = json.getJSONObject(i);

                    obatDetail.setIdObat(obj.getString("ID"));
                    if("box".equalsIgnoreCase(obj.getString("Jenis Satuan"))){
                        obatDetail.setAverageHargaBox(new BigDecimal(obj.getString("Harga")));
                    }
                    if("lembar".equalsIgnoreCase(obj.getString("Jenis Satuan"))){
                        obatDetail.setAverageHargaLembar(new BigDecimal(obj.getString("Harga")));
                    }
                    if("biji".equalsIgnoreCase(obj.getString("Jenis Satuan"))){
                        obatDetail.setAverageHargaBiji(new BigDecimal(obj.getString("Harga")));
                    }

                    obatDetail.setQty(new BigInteger(obj.getString("Jumlah")));
                    obatDetail.setJenisSatuan(obj.getString("Jenis Satuan"));
                    obatDetailList.add(obatDetail);
                }

                permintaanVendor.setListOfTransaksiObatDetail(obatDetailList);
            }
        } catch (JSONException e) {
            logger.error("[PermintaanVendorAction.savePermintaanPO] Error when save permintaan po", e);
        }

        String fileName = "";
        if (this.fileUpload != null) {
            if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                    // file name
                    fileName = "DOC_PO" + "_" + this.fileUploadFileName;

                    // deklarasi path file
                    String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_PO;
//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_KTP_PASIEN;
                    logger.info("[savePermintaanPO.uploadImages] FILEPATH :" + filePath);

                    // persiapan pemindahan file
                    File fileToCreate = new File(filePath, fileName);

                    try {
                        // pemindahan file
                        FileUtils.copyFile(this.fileUpload, fileToCreate);
                        logger.info("[savePermintaanPO.uploadImages] SUCCES PINDAH");
                        permintaanVendor.setUrlDocPo(fileName);
                    } catch (IOException e) {
                        logger.error("[savePermintaanPO.uploadImages] error, " + e.getMessage());
                    }
                }
            }
        }

        try {
            permintaanVendorBo.saveListObatPo(permintaanVendor);
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. " + e.getMessage());
        }

        logger.info("[PermintaanVendorAction.savePermintaanPO] END <<<<<<<");
        return SUCCESS;
    }

    public String getComboVendor(){

        List<Vendor> listVendor = new ArrayList<>();
        Vendor vendor = new Vendor();

        try {
            listVendor = vendorBoProxy.getByCriteria(vendor);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorAction.getComboVendor] Error when get data for combo list of vendor", e);
            addActionError(" Error when get data for combo list of vendor" + e.getMessage());
        }

        listOfVendor.addAll(listVendor);

        return SUCCESS;
    }

    public String saveNewPabrik(String idTranskasi, String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger lembarBox, BigInteger bijiLembar, BigDecimal harga, BigInteger qty, BigInteger qtyApp, String jenisSatuan, String idApproval) {

        logger.info("[PermintaanVendorAction.saveNewPabrik] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> transaksiObatDetails = (List) session.getAttribute("listOfObatDetail");

        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

            TransaksiObatDetail obatDetail = new TransaksiObatDetail();

            obatDetail.setIdTransaksiObatDetail(idTranskasi);
            obatDetail.setIdApprovalObat(idApproval);
            obatDetail.setNamaObat(namaObat);
            obatDetail.setMerek(merek);
            obatDetail.setIdPabrik(pabrik);
            obatDetail.setLembarPerBox(lembarBox);
            obatDetail.setBijiPerLembar(bijiLembar);
            obatDetail.setQty(qty);
            obatDetail.setCreatedDate(updateTime);
            obatDetail.setCreatedWho(userLogin);
            obatDetail.setLastUpdate(updateTime);
            obatDetail.setLastUpdateWho(userLogin);
            obatDetail.setBranchId(userArea);
            obatDetail.setHargaPo(harga);
            obatDetail.setJenisSatuan(jenisSatuan);
            obatDetail.setQtyApprove(qtyApp);
            obatDetail.setFlag("Y");
            obatDetail.setFlagDiterima("R");
            obatDetail.setKeterangan("Buat obat baru");
            obatDetail.setAction("C");

            permintaanVendorBo.saveNewPabrik(obatDetail, jenisObat);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanVendorAction.saveNewPabrik] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PermintaanVendorAction.saveNewPabrik] end process >>>");
        return SUCCESS;

    }

    public String saveApprove(){
        logger.info("[PermintaanVendorAction.saveApprove] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransaksiObatDetail> transaksiObatDetails = (List) session.getAttribute("listOfObatDetail");
        List<TransaksiObatDetail> transaksiObatDetailNew = (List) session.getAttribute("listOfObatDetailNew");

        PermintaanVendor permintaanVendor = getPermintaanVendor();
        permintaanVendor.setBranchId(CommonUtil.userBranchLogin());
        permintaanVendor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        permintaanVendor.setLastUpdateWho(CommonUtil.userLogin());

        try {
            permintaanVendorBoProxy.saveConfirm(permintaanVendor, transaksiObatDetails, transaksiObatDetailNew);
        } catch (GeneralBOException e){
            logger.error("[PermintaanVendorAction.saveApprove] Error when save data approve PO", e);
            addActionError(" Error when save data approve PO" + e.getMessage());
        }

        logger.info("[PermintaanVendorAction.saveApprove] END <<<<<<<");
        return "init_approve";
    }

    public List<TransaksiObatDetail> searchNewListObat(String idApproval) {
        logger.info("[PermintaanVendorAction.searchNewListObat] START process >>>");

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        TransaksiObatDetail detail = new TransaksiObatDetail();
        detail.setIdApprovalObat(idApproval);
        detail.setFlagDiterima("R");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            obatDetails = permintaanVendorBo.getNewObatDetail(detail);
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.searchNewListObat] Error when searching permintan vendor by criteria,", e);
        }

        logger.info("[PermintaanVendorAction.searchNewListObat] END process <<<");
        return obatDetails;

    }

    public String initApproval() {

        logger.info("[PermintaanVendorAction.initApproval] START process >>>");

        String id = getId();
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(id);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorAction.initApproval] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.initApproval] ERROR error when get searh obat. " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfObatDetail");

        if (permintaanVendorList.size() > 0){
            setPermintaanVendor(permintaanVendorList.get(0));
            session.setAttribute("listOfObatDetail", permintaanVendorList.get(0).getListOfTransaksiObatDetail());

            Vendor vendor = new Vendor();
            vendor.setIdVendor(permintaanVendorList.get(0).getIdVendor());
            List<Vendor> vendorList = new ArrayList<>();

            try {
                vendorList = vendorBoProxy.getByCriteria(vendor);
            }catch (GeneralBOException e){
                logger.error("[PermintaanVendorAction.initApproval] ERROR error when get searh vendor. ", e);
                addActionError("[PermintaanVendorAction.initApproval] ERROR error when get searh vendor. " + e.getMessage());
            }

            Vendor vendorResult =  new Vendor();
            if (!vendorList.isEmpty()){
                vendorResult = vendorList.get(0);
                if(vendorResult != null){
                    setVendor(vendorResult);
                }
            }

            List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

            TransaksiObatDetail detail = new TransaksiObatDetail();
            detail.setIdApprovalObat(permintaanVendorList.get(0).getIdApprovalObat());
            detail.setFlagDiterima("R");

            try {
                obatDetailList = permintaanVendorBoProxy.getNewObatDetail(detail);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.initApproval] Error when searching permintan vendor by criteria,", e);
            }

            session.setAttribute("listOfObatDetailNew", obatDetailList);

        }

        logger.info("[PermintaanVendorAction.initApproval] END process <<<");

        return "init_approve";

    }


    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
