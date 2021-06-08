package com.neurix.simrs.transaksi.permintaanvendor.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.MappingDetail;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImAreas;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pabrikobat.model.PabrikObat;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.*;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import io.agora.recording.common.Common;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
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
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PermintaanVendorAction.class);
    private PermintaanVendorBo permintaanVendorBoProxy;
    private PermintaanVendor permintaanVendor;
    private VendorBo vendorBoProxy;
    private Vendor vendor;
    private BranchBo branchBoProxy;

    private File fileUpload;
    private String fileUploadFileName;

    private String fileUploadContentType;

    private String id;
    private String isBatch;
    private Integer noBatch;
    private String newBatch;
    private String tipe;

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNewBatch() {
        return newBatch;
    }

    public void setNewBatch(String newBatch) {
        this.newBatch = newBatch;
    }

    public Integer getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(Integer noBatch) {
        this.noBatch = noBatch;
    }

    public String getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

        // get parameters
        String id = getId();
        String isBatch = getIsBatch();
        String newBatch = getNewBatch();
        String tipe = getTipe();
        setTipe(tipe);

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(id);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.edit] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.edit] ERROR error when get searh obat. " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfObatDetail");

        String idApproval = "";
        Boolean isNew = true;
        if (permintaanVendorList.size() > 0) {

            PermintaanVendor requestVendor = permintaanVendorList.get(0);
            idApproval = requestVendor.getIdApprovalObat();

            // get last number of batch
            Integer noBatch = 0;
            try {
                noBatch = permintaanVendorBoProxy.getLastNoBatch(requestVendor.getIdApprovalObat());
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.edit] ERROR. ", e);
                addActionError("[PermintaanVendorAction.edit] ERROR. " + e.getMessage());
            }

            if (noBatch.compareTo(0) == 1) {
                isNew = false;
            }

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

            // if edit from list batch then sorted with table batch data to get qty approve and status
            if ("Y".equalsIgnoreCase(isBatch)) {
                isNew = true;

                // if new add new batch
                if ("Y".equalsIgnoreCase(newBatch)) {
                    noBatch = noBatch + 1;
                    requestVendor.setNoBatch(noBatch);
                } else {
                    requestVendor.setNoBatch(getNoBatch());
                }

                try {
                    transaksiObatDetails = permintaanVendorBoProxy.getListTransByBatchSorted(requestVendor.getListOfTransaksiObatDetail(), requestVendor.getNoBatch(), "N");
                } catch (GeneralBOException e) {
                    logger.error("[PermintaanVendorAction.edit] ERROR. ", e);
                    addActionError("[PermintaanVendorAction.edit] ERROR. " + e.getMessage());
                }

            } else {
                transaksiObatDetails.addAll(requestVendor.getListOfTransaksiObatDetail());
            }

            setId(id);
            setPermintaanVendor(requestVendor);
            session.setAttribute("listOfObatDetail", transaksiObatDetails);

            Vendor vendor = new Vendor();
            vendor.setIdVendor(requestVendor.getIdVendor());
            List<Vendor> vendorList = new ArrayList<>();

            try {
                vendorList = vendorBoProxy.getByCriteria(vendor);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.edit] ERROR error when get searh vendor. ", e);
                addActionError("[PermintaanVendorAction.edit] ERROR error when get searh vendor. " + e.getMessage());
            }

            Vendor vendorResult = new Vendor();
            if (!vendorList.isEmpty()) {
                vendorResult = vendorList.get(0);
                if (vendorResult != null) {
                    setVendor(vendorResult);
                }
            }
        }

        logger.info("[PermintaanVendorAction.edit] END <<<<<<<");
        if (isNew && "reture".equalsIgnoreCase(tipe)) {
            return "init_edit_reture";
        } else if (isNew){
            return "init_edit";
        }else {
            return initListBatch(idApproval, id);
        }
    }

    public CheckObatResponse checkIdPabrikan(String idObat, String idPabrikScan) {
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
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.checkIdPabrikan] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.checkIdPabrikan] ERROR error when get searh obat. " + e.getMessage());
        }

        if (obats.size() > 0) {
            checkObatResponse.setStatus("success");
            checkObatResponse.setMessage("Obat Terverivikasi");
        } else {
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("Obat Tidak Ditemukan Check Kembali Obat Anda");
        }

        logger.info("[PermintaanVendorAction.checkIdPabrikan] END <<<<<<<");
        return checkObatResponse;
    }

    public CheckObatResponse saveUpdateListObat(String dataJson) {
        logger.info("[PermintaanVendorAction.checkIdPabrikan] START >>>>>>>");
        CheckObatResponse checkObatResponse = new CheckObatResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            if(dataJson != null && !"".equalsIgnoreCase(dataJson)){
                JSONObject obj = new JSONObject(dataJson);
                if(obj != null){
                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(obj.getString("id_detail_obat"));
                    transaksiObatDetail.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    transaksiObatDetail.setLastUpdateWho(CommonUtil.userLogin());
                    transaksiObatDetail.setQty(new BigInteger(obj.getString("qty_approve")));
                    transaksiObatDetail.setIdPabrik(obj.getString("no_produksi"));
                    transaksiObatDetail.setIdPabrikObat(obj.getString("id_pabrik"));
                    transaksiObatDetail.setNomorProduksi(obj.getString("no_produksi"));
                    transaksiObatDetail.setFlagDiterima("Y");
                    transaksiObatDetail.setLembarPerBox(new BigInteger(obj.getString("lembar_per_box")));
                    transaksiObatDetail.setBijiPerLembar(new BigInteger(obj.getString("biji_per_lembar")));
                    transaksiObatDetail.setNoBatch(Integer.valueOf(obj.getString("no_batch")));
                    transaksiObatDetail.setExpDate(Date.valueOf(obj.getString("expired")));
                    transaksiObatDetail.setQtyApprove(new BigInteger(obj.getString("qty_approve")));
                    transaksiObatDetail.setDiskon(new BigDecimal(obj.getString("diskon")));
                    transaksiObatDetail.setBruto(new BigDecimal(obj.getString("bruto")));
                    transaksiObatDetail.setNetto(new BigDecimal(obj.getString("netto")));
                    transaksiObatDetail.setMerek(obj.getString("merek"));

                    try {
                        checkObatResponse = permintaanVendorBo.saveUpdateTransObatDetail(transaksiObatDetail);
                    } catch (HibernateException e) {
                        checkObatResponse.setStatus("error");
                        checkObatResponse.setMessage("Error when "+e.getMessage());
                        logger.error("[PermintaanVendorAction.saveUpdateListObat] ERROR error when update data. ", e);
                    }
                }else{
                    checkObatResponse.setStatus("error");
                    checkObatResponse.setMessage("Error when parse JSON, data yang dikirim tidak ada @_@");
                    logger.error("[PermintaanVendorAction.saveUpdateListObat] ERROR error when update data. ");
                }
            }else{
                checkObatResponse.setStatus("error");
                checkObatResponse.setMessage("Error when parse JSON, data yang dikirim tidak ada @_@");
                logger.error("[PermintaanVendorAction.saveUpdateListObat] ERROR error when update data. ");
            }
        }catch (Exception e){
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("Error when "+e.getMessage());
            logger.error("[PermintaanVendorAction.saveUpdateListObat] ERROR error when update data. ", e);
        }
        logger.info("[PermintaanVendorAction.checkIdPabrikan] END <<<<<<<");
        return checkObatResponse;
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
        permintaanVendor.setTipeTransaksi("request");

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

    public CheckResponse savePermintaanPO(String idVendor, String tgl, String po) {

        logger.info("[PermintaanVendorAction.savePermintaanPO] START >>>>>>>");
        CheckResponse response = new CheckResponse();
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
        permintaanVendor.setTglCair(tgl);

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        TransaksiObatDetail obatDetail;

        try {
            if (po != null && !"".equalsIgnoreCase(po)) {
                JSONArray json = new JSONArray(po);
                for (int i = 0; i < json.length(); i++) {
                    obatDetail = new TransaksiObatDetail();

                    JSONObject obj = json.getJSONObject(i);

                    obatDetail.setIdObat(obj.getString("id_obat"));

                    if ("box".equalsIgnoreCase(obj.getString("jenis_satuan"))) {
                        obatDetail.setAverageHargaBox(new BigDecimal(obj.getString("harga")));
                    }
                    if ("lembar".equalsIgnoreCase(obj.getString("jenis_satuan"))) {
                        obatDetail.setAverageHargaLembar(new BigDecimal(obj.getString("harga")));
                    }
                    if ("biji".equalsIgnoreCase(obj.getString("jenis_satuan"))) {
                        obatDetail.setAverageHargaBiji(new BigDecimal(obj.getString("harga")));
                    }

                    obatDetail.setQty(new BigInteger(obj.getString("qty")));
                    obatDetail.setJenisSatuan(obj.getString("jenis_satuan"));

                    if(obj.has("biji_per_lemar")){
                        obatDetail.setBijiPerLembar(new BigInteger(obj.getString("biji_per_lembar")));
                    }
                    if(obj.has("lembar_per_box")){
                        obatDetail.setLembarPerBox(new BigInteger(obj.getString("lembar_per_box")));
                    }
                    if(obj.has("id_pabrik_obat")){
                        obatDetail.setIdPabrikObat(obj.getString("id_pabrik_obat"));
                    }
                    if(obj.has("nomor_produksi")){
                        obatDetail.setNomorProduksi(obj.getString("nomor_produksi"));
                    }
                    if(obj.has("tipe_obat")){
                        obatDetail.setTipeObat(obj.getString("tipe_obat"));
                    }
                    obatDetailList.add(obatDetail);
                }

                permintaanVendor.setListOfTransaksiObatDetail(obatDetailList);
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMessage("Found Error when save permintaan vendor " + e.getMessage());
            logger.error("[PermintaanVendorAction.savePermintaanPO] Error when save permintaan po", e);
        }

        try {
            response = permintaanVendorBo.saveListObatPo(permintaanVendor);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("Found Error when save permintaan vendor " + e.getMessage());
            logger.error("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. ", e);
        }

        logger.info("[PermintaanVendorAction.savePermintaanPO] END <<<<<<<");
        return response;
    }

    public String getComboVendor() {

        List<Vendor> listVendor = new ArrayList<>();
        Vendor vendor = new Vendor();

        try {
            listVendor = vendorBoProxy.getByCriteria(vendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.getComboVendor] Error when get data for combo list of vendor", e);
            addActionError(" Error when get data for combo list of vendor" + e.getMessage());
        }

        listOfVendor.addAll(listVendor);

        return SUCCESS;
    }

//    public String saveNewPabrik(String idTranskasi, String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger lembarBox, BigInteger bijiLembar, BigDecimal harga, BigInteger qty, BigInteger qtyApp, String jenisSatuan, String idApproval) {
//
//        logger.info("[PermintaanVendorAction.saveNewPabrik] start process >>>");
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<TransaksiObatDetail> transaksiObatDetails = (List) session.getAttribute("listOfObatDetail");
//
//        try {
//            String userLogin = CommonUtil.userLogin();
//            String userArea = CommonUtil.userBranchLogin();
//            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//
//            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//            PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
//
//            TransaksiObatDetail obatDetail = new TransaksiObatDetail();
//
//            obatDetail.setIdTransaksiObatDetail(idTranskasi);
//            obatDetail.setIdApprovalObat(idApproval);
//            obatDetail.setNamaObat(namaObat);
//            obatDetail.setMerek(merek);
//            obatDetail.setIdPabrik(pabrik);
//            obatDetail.setLembarPerBox(lembarBox);
//            obatDetail.setBijiPerLembar(bijiLembar);
//            obatDetail.setQty(qty);
//            obatDetail.setCreatedDate(updateTime);
//            obatDetail.setCreatedWho(userLogin);
//            obatDetail.setLastUpdate(updateTime);
//            obatDetail.setLastUpdateWho(userLogin);
//            obatDetail.setBranchId(userArea);
//            obatDetail.setHargaPo(harga);
//            obatDetail.setJenisSatuan(jenisSatuan);
//            obatDetail.setQtyApprove(qtyApp);
//            obatDetail.setFlag("Y");
//            obatDetail.setFlagDiterima("R");
//            obatDetail.setKeterangan("Buat obat baru");
//            obatDetail.setAction("C");
//
//            permintaanVendorBo.saveNewPabrik(obatDetail, jenisObat);
//
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            logger.error("[PermintaanVendorAction.saveNewPabrik] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
//            return ERROR;
//        }
//
//        logger.info("[PermintaanVendorAction.saveNewPabrik] end process >>>");
//        return "search";
//
//    }

    public CheckResponse saveNewPabrik(String idTranskasi, String namaObat, List<String> jenisObat, String merek, String pabrik, BigInteger lembarBox, BigInteger bijiLembar, BigDecimal harga, BigInteger qty, BigInteger qtyApp, String jenisSatuan, String idApproval) {

        CheckResponse response = new CheckResponse();

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

            response = permintaanVendorBo.saveNewPabrik(obatDetail, jenisObat);

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Found Error "+e.getMessage());
            logger.error("Found Error "+e.getMessage());
        }

        logger.info("[PermintaanVendorAction.saveNewPabrik] end process >>>");
        return response;

    }

    public String saveApprove() {
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
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.saveApprove] Error when save data approve PO", e);
            addActionError(" Error when save data approve PO" + e.getMessage());
        }

        logger.info("[PermintaanVendorAction.saveApprove] END <<<<<<<");
        return "init_approve";
    }

    public CheckObatResponse saveApproveBatch(String idPermintaanVendor, String data, String jenis, String listImg) throws JSONException, IOException {
        logger.info("[PermintaanVendorAction.saveApproveBatch] START >>>>>>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();

        CheckObatResponse checkObatResponse = new CheckObatResponse();

        String pelayananId = CommonUtil.userPelayananIdLogin();

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaanVendor);
        permintaanVendor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        permintaanVendor.setLastUpdateWho(CommonUtil.userLogin());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        VendorBo vendorBo  = (VendorBo) ctx.getBean("vendorBoProxy");
        PositionBo positionBo  = (PositionBo) ctx.getBean("positionBoProxy");

        Integer noBatch = null;
        String noFaktur = "";
        String tglFaktur = "";
        String noInvoice = "";
        String noDo = "";
        String tglInvoice = "";
        String tglDo = "";

        JSONObject obj = new JSONObject(data);

        if(obj != null){

            noBatch = Integer.valueOf(obj.getString("no_batch"));
            noFaktur = obj.getString("no_faktur");
            tglFaktur = obj.getString("tgl_faktur");
            noInvoice = obj.getString("no_invoice");
            noDo = obj.getString("no_do");
            tglInvoice = obj.getString("tgl_invoice");
            tglDo = obj.getString("tgl_do");
        }

        List<ItSimrsDocPoEntity> docPoEntities = new ArrayList<>();
        if (listImg != null && !"".equalsIgnoreCase(listImg)){

            JSONArray json = new JSONArray(listImg);
            for (int i = 0; i < json.length(); i++) {
                obj = json.getJSONObject(i);
                ItSimrsDocPoEntity docPoEntity = new ItSimrsDocPoEntity();
                if (!"".equalsIgnoreCase(obj.get("jenisnomor").toString())){
                    docPoEntity.setJenisNomor(obj.get("jenisnomor") == null ? "" : obj.get("jenisnomor").toString());
                }
                if (!"".equalsIgnoreCase(obj.get("batch").toString())){
                    docPoEntity.setNoBatch(obj.get("batch") == null ? null : new Integer(obj.get("batch").toString()) );
                }
                if (!"".equalsIgnoreCase(obj.get("iditem").toString())){
                    docPoEntity.setIdItem(obj.get("iditem") == null ? null : obj.get("iditem").toString());
                }
                // upload img
                if (!"".equalsIgnoreCase(obj.get("img").toString())){
                    String cekPath =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_DOC_PO;
                    File theDir = new File(cekPath);
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                        theDir.setReadable(true);
                        theDir.setExecutable(true);
                        theDir.setWritable(true);
                    }

                    if(obj.has("eks")){
                        String eks = obj.getString("eks");
                        String fileName = i + docPoEntity.getIdItem()+"-"+dateFormater("MM")+dateFormater("yy");
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img"));
                        if ("pdf".equalsIgnoreCase(eks)) {
                            fileName = fileName + ".pdf";
                            File file = new File(cekPath + fileName);
                            FileOutputStream fop = new FileOutputStream(file);
                            fop.write(decodedBytes);
                            fop.flush();
                            fop.close();
                            docPoEntity.setUrlImg(fileName);
                            docPoEntity.setTipe("PDF");
                        } else {
                            fileName = fileName + ".jpg";
                            String uploadFile = cekPath + fileName;
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                            if (image == null) {
                                logger.error("Buffered Image is null");
                            } else {
                                CrudResponse response = CommonUtil.compressImage(image, "png", uploadFile);
                                if("success".equalsIgnoreCase(response.getStatus())){
                                    docPoEntity.setUrlImg(fileName);
                                    docPoEntity.setTipe("IMG");
                                }else{
                                    checkObatResponse.setStatus("error");
                                    checkObatResponse.setMessage("Kompress IMG Error, "+response.getMsg());
                                    return checkObatResponse;
                                }
                            }
                        }
                    }
                }

                docPoEntity.setIdPermintaanObatVendor(idPermintaanVendor);
                docPoEntity.setFlag("Y");
                docPoEntity.setAction("C");
                docPoEntity.setCreatedDate(time);
                docPoEntity.setCreatedWho(userLogin);
                docPoEntity.setLastUpdate(time);
                docPoEntity.setLastUpdateWho(userLogin);
                docPoEntities.add(docPoEntity);
            }

            try {
                permintaanVendorBo.saveListDocVendor(docPoEntities);
            } catch (GeneralBOException e){
                logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR error when save DOC PO. ", e);
                addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR error when save DOC PO. " + e.getMessage());
            }
        }

        permintaanVendor.setNoFaktur(noFaktur != null && !"".equalsIgnoreCase(noFaktur) ? noFaktur : null);
        permintaanVendor.setTanggalFaktur(tglFaktur != null && !"".equalsIgnoreCase(tglFaktur) ? Date.valueOf(tglFaktur) : null);
        permintaanVendor.setTglInvoice( "".equalsIgnoreCase(tglInvoice) ? null : Date.valueOf(tglInvoice));
        permintaanVendor.setTglDo( "".equalsIgnoreCase(tglDo) ? null : Date.valueOf(tglDo));
        permintaanVendor.setNoInvoice(noInvoice != null && !"".equalsIgnoreCase(noInvoice) ? noInvoice : null);
        permintaanVendor.setNoDo(noDo != null && !"".equalsIgnoreCase(noDo) ? noDo : null);
        permintaanVendor.setIdPelayanan(pelayananId);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. " + e.getMessage());
        }

        if (permintaanVendorList.size() > 0) {
            PermintaanVendor requestVendor = permintaanVendorList.get(0);

            // set permintaanVendor OBJECT idVendor Object;
            permintaanVendor.setIdVendor(requestVendor.getIdVendor());

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
            try {
                transaksiObatDetails = permintaanVendorBo.getListTransByBatchSorted(requestVendor.getListOfTransaksiObatDetail(), noBatch, "Y");
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR. ", e);
                addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR. " + e.getMessage());
            }

            // List<TransaksiObatDetail> transaksiObatDetailNew = new ArrayList<>();
            // fitur list obat baru sudah dinonaktifkan

            try {
                checkObatResponse = permintaanVendorBo.saveConfirm(permintaanVendor, transaksiObatDetails, null);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.saveApproveBatch] Error when save data approve PO", e);
                addActionError(" Error when save data approve PO" + e.getMessage());
            }

            List<MappingDetail> listMapPersediaan = new ArrayList<>();
            BigDecimal hutangUsaha = new BigDecimal(0);
            BigDecimal ppn = new BigDecimal(0);
            if (transaksiObatDetails.size() > 0) {
                for (TransaksiObatDetail trans : transaksiObatDetails) {

                    ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(trans.getIdBarang());
                    if (obatEntity == null){
                        logger.error("Found Error when search master obat is null");
                        checkObatResponse.setMessage("Found Error when search master obat is null");
                        checkObatResponse.setStatus("error");
                    }

                    BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

                    // jika harga bukan pengembalian reture pakai harga terakhir;
                    BigDecimal hargaRata = new BigDecimal(0);
                    BigDecimal hargaTotal = new BigDecimal(0);
                    if (!"reture".equalsIgnoreCase(jenis)){
                        hargaRata = trans.getNetto();
                        hargaTotal = hargaRata;
                    } else {
                        if ("box".equalsIgnoreCase(trans.getJenisSatuan())){
                            hargaRata = hargaRata.add(obatEntity.getAverageHargaBox());
                        } if ("lembar".equalsIgnoreCase(trans.getJenisSatuan())){
                            hargaRata = hargaRata.add(obatEntity.getAverageHargaLembar());
                        } if ("biji".equalsIgnoreCase(trans.getJenisSatuan())){
                            hargaRata = hargaRata.add(obatEntity.getAverageHargaBiji());
                        }
                        hargaTotal = hargaRata.multiply(new BigDecimal(trans.getQtyApprove()));
                    }

                    BigDecimal hargaPpn = new BigDecimal( 0);

                    // hutang usaha
                    hutangUsaha = hutangUsaha.add(hargaTotal);

                    //ppn
                    // jika bukan pengembalian reture maka pakai ppn
                    if (!"reture".equalsIgnoreCase(jenis)){
                        hargaPpn = hargaTotal.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        ppn = ppn.add(hargaPpn);
                    }

                    MappingDetail mapHutangUsaha = new MappingDetail();
                    mapHutangUsaha.setKodeBarang(trans.getIdBarang());
                    mapHutangUsaha.setNilai(hargaTotal.subtract(hargaPpn));
                    listMapPersediaan.add(mapHutangUsaha);
                }
            }

            String divisiId = "";
            String transId = "";
            String catatan = "";
            String namaVendor = "";

            namaVendor = "";
            ImSimrsVendorEntity vendorEntity = vendorBo.getEntityVendorById(requestVendor.getIdVendor());
            if (vendorEntity != null){
                namaVendor = vendorEntity.getNamaVendor();
            }

            Map jurnalMap = new HashMap();
            if ("reture".equalsIgnoreCase(jenis)){

                Pelayanan pelayananEntity = pelayananEntity = pelayananBo.getPelayananById(pelayananId);
                if (pelayananEntity != null){

                    ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                    if (position != null){
                        divisiId = position.getKodering();
                    }
                }

                List<MappingDetail> listMapBiaya = new ArrayList<>();

                MappingDetail mapBiaya = new MappingDetail();
                mapBiaya.setMasterId(divisiId);
                mapBiaya.setNilai(hutangUsaha);
                listMapBiaya.add(mapBiaya);

                jurnalMap.put("divisi_id", divisiId);
                jurnalMap.put("persediaan_gudang", listMapPersediaan);
                jurnalMap.put("biaya_persediaan_obat", listMapBiaya);

                catatan = "Pengganti Barang No. Transaksi "+idPermintaanVendor+". Retur Vendor ke Gudang dari Vendor " + requestVendor.getIdVendor() + " - " + namaVendor;
                transId = "36";
            } else {

                List<MappingDetail> listMapPajakObat = new ArrayList<>();

                MappingDetail mapPajakObat = new MappingDetail();
                mapPajakObat.setBukti(noFaktur);
                mapPajakObat.setNilai(ppn);
                mapPajakObat.setMasterId(requestVendor.getIdVendor());
                listMapPajakObat.add(mapPajakObat);

                List<MappingDetail> listMapHutangVendor = new ArrayList<>();

                MappingDetail mapHutangVendor = new MappingDetail();
                mapHutangVendor.setBukti(noDo);
                mapHutangVendor.setNilai(hutangUsaha);
                mapHutangVendor.setMasterId(requestVendor.getIdVendor());
                mapHutangVendor.setDivisiId(divisiId);
                listMapHutangVendor.add(mapHutangVendor);

                jurnalMap.put("persediaan_gudang", listMapPersediaan);
                jurnalMap.put("hutang_farmasi_vendor", listMapHutangVendor);
                jurnalMap.put("ppn_masukan", listMapPajakObat);

                catatan = "Penerimaan Barang Gudang dari Vendor " + requestVendor.getIdVendor() + " - " + namaVendor;
                transId = "27";
            }

            String noJurnal = "";
            try {
                Jurnal jurnal = billingSystemBo.createJurnal(transId, jurnalMap, CommonUtil.userBranchLogin(), catatan, "Y");
                noJurnal = jurnal.getNoJurnal();
            } catch (GeneralBOException e) {
                logger.error("Found Error when search permintaan vendor " + e.getMessage());
                checkObatResponse.setMessage("Found Error when search permintaan vendor " + e.getMessage());
                checkObatResponse.setStatus("error");
                addActionError(" Error when save data approve PO" + e.getMessage());
                return checkObatResponse;
            }
        }

        logger.info("[PermintaanVendorAction.saveApproveBatch] START >>>>>>>");
        return checkObatResponse;
    }

    public CrudResponse saveDocPo(String idPermintaanVendor, String noBatch, String listImg, String objData) throws JSONException, IOException{
        logger.info("[PermintaanVendorAction.saveDocPo] Start >>>");

        String userLogin    = CommonUtil.userLogin();
        Timestamp time      = CommonUtil.getCurrentDateTimes();

        CrudResponse response = new CrudResponse();

        List<ItSimrsDocPoEntity> docPoEntities = new ArrayList<>();

        if (idPermintaanVendor == null || "".equalsIgnoreCase(idPermintaanVendor)){
            response.hasError("Tidak Ditemukan No. Permintaan");
            return response;
        }

        if (noBatch == null || "".equalsIgnoreCase(noBatch)){
            response.hasError("Tidak Ditemukan No. Batch");
            return response;
        }

        if (listImg == null || "".equalsIgnoreCase(listImg)){
            response.hasError("Tidak Ditemukan list Document Dikirim");
            return response;
        }

        if (objData == null || "".equalsIgnoreCase(objData)){
            response.hasError("Tidak Ditemukan data No. Document");
            return response;
        }

        // passing data uploaded document to list document entity
        JSONArray json = new JSONArray(listImg);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            ItSimrsDocPoEntity docPoEntity = new ItSimrsDocPoEntity();
            if (!"".equalsIgnoreCase(obj.get("jenisnomor").toString())) {
                docPoEntity.setJenisNomor(obj.get("jenisnomor") == null ? "" : obj.get("jenisnomor").toString());
            }
            if (!"".equalsIgnoreCase(obj.get("iditem").toString())) {
                docPoEntity.setIdItem(obj.get("iditem") == null ? null : obj.get("iditem").toString());
            }
            // upload img
            if (!"".equalsIgnoreCase(obj.get("img").toString())) {

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img"));
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = i + docPoEntity.getIdItem() + "-" + dateFormater("MM") + dateFormater("yy") + ".jpg";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_PO + fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                } else {
                    response = CommonUtil.compressImage(image, "png", uploadFile);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        docPoEntity.setUrlImg(fileName);
                    } else {
                        response.hasError("Kompress IMG Error, " + response.getMsg());
                    }
                }
            }

            docPoEntity.setTipe("IMG");
            docPoEntity.setIdPermintaanObatVendor(idPermintaanVendor);
            docPoEntity.setNoBatch(Integer.valueOf(noBatch));
            docPoEntity.setFlag("Y");
            docPoEntity.setAction("C");
            docPoEntity.setCreatedDate(time);
            docPoEntity.setCreatedWho(userLogin);
            docPoEntity.setLastUpdate(time);
            docPoEntity.setLastUpdateWho(userLogin);
            docPoEntities.add(docPoEntity);
        }
        // END

        // set json object to object BatchPermintaanObat
        BatchPermintaanObat batchPermintaanObat = new BatchPermintaanObat();
        JSONObject obj = new JSONObject(objData);

        batchPermintaanObat.setNoFaktur(obj.getString("no_faktur"));
        batchPermintaanObat.setNoInvoice(obj.getString("no_invoice"));
        batchPermintaanObat.setNoDo(obj.getString("no_do"));
        batchPermintaanObat.setTanggalFaktur(obj.getString("tgl_faktur") == null ? null : CommonUtil.convertStringToDate2(obj.getString("tgl_faktur")));
        batchPermintaanObat.setTglInvoice(obj.getString("tgl_invoice") == null ? null : CommonUtil.convertStringToDate2(obj.getString("tgl_invoice")));
        batchPermintaanObat.setTglDo(obj.getString("tgl_do") == null ? null : CommonUtil.convertStringToDate2(obj.getString("tgl_do")));
        batchPermintaanObat.setIdPermintaan(idPermintaanVendor);
        batchPermintaanObat.setStNoBatch(noBatch);
        batchPermintaanObat.setLastUpdate(time);
        batchPermintaanObat.setLastUpdateWho(userLogin);
        // END

        // check jika list dokumen tidak ada nomor dokumen
        response = checkDocumentNumb(docPoEntities, batchPermintaanObat);
        if ("error".equalsIgnoreCase(response.getStatus())){
            return response;
        }
        // END

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            permintaanVendorBo.saveUploadDocPoAfterApprove(docPoEntities, batchPermintaanObat);
            response.hasSuccess("Berhasil Menyimpan");
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR error when save DOC PO. ", e);
            response.hasError("[PermintaanVendorAction.saveApproveBatch] ERROR error when save DOC PO. " + e.getMessage());
            return response;
        }

        logger.info("[PermintaanVendorAction.saveDocPo] END <<<");
        return response;
    }

    private CrudResponse checkDocumentNumb(List<ItSimrsDocPoEntity> docPoEntities, BatchPermintaanObat batchObat){
        logger.info("[PermintaanVendorAction.checkDocumentNumb] Start >>>");
        CrudResponse response = new CrudResponse();

        if (batchObat.getNoFaktur() == null
                || "".equalsIgnoreCase(batchObat.getNoFaktur())
                || batchObat.getTanggalFaktur() == null )
        {
            List<ItSimrsDocPoEntity> filterdList = docPoEntities.stream().filter(
                    p->p.getJenisNomor().equalsIgnoreCase("faktur")
            ).collect(Collectors.toList());

            if (filterdList.size() > 0){
                response.hasError("Tidak ditemukan no. faktur pada list faktur yang dikirim");
                return response;
            }
        }

        if (batchObat.getNoInvoice() == null
                || "".equalsIgnoreCase(batchObat.getNoInvoice())
                || batchObat.getTglInvoice() == null)
        {
            List<ItSimrsDocPoEntity> filterdList = docPoEntities.stream().filter(
                    p->p.getJenisNomor().equalsIgnoreCase("invoice")
            ).collect(Collectors.toList());

            if (filterdList.size() > 0){
                response.hasError("Tidak ditemukan no. invoice pada list invoice yang dikirim");
                return response;
            }
        }

        if (batchObat.getNoDo() == null
                || "".equalsIgnoreCase(batchObat.getNoDo())
                || batchObat.getTglDo() == null)
        {
            List<ItSimrsDocPoEntity> filterdList = docPoEntities.stream().filter(
                    p->p.getJenisNomor().equalsIgnoreCase("do")
            ).collect(Collectors.toList());

            if (filterdList.size() > 0){
                response.hasError("Tidak ditemukan no. invoice pada list invoice yang dikirim");
                return response;
            }
        }

        response.setStatus("success");
        logger.info("[PermintaanVendorAction.checkDocumentNumb] End <<<");
        return response;
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

//    public List<TransaksiObatDetail> initApproval(String idpermintaanPo, String isBatchValue, String newBatchValue, Integer noBatchValue) {
//
//        logger.info("[PermintaanVendorAction.initApproval] START process >>>");
//
//        // get parameters
//        String id = idpermintaanPo;
//        String isBatch = isBatchValue;
//        String newBatch = newBatchValue;
//
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
//        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");
//
//        PermintaanVendor permintaanVendor = new PermintaanVendor();
//        permintaanVendor.setIdPermintaanVendor(id);
//
//        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
//        try {
//            permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
//        } catch (HibernateException e) {
//            logger.error("[PermintaanVendorAction.edit] ERROR error when get searh obat. ", e);
//            addActionError("[PermintaanVendorAction.edit] ERROR error when get searh obat. " + e.getMessage());
//        }
//
//
////        HttpSession session = ServletActionContext.getRequest().getSession();
////        session.removeAttribute("listOfObatDetail");
//
//        String idApproval = "";
//        Boolean isNew = true;
//        if (permintaanVendorList.size() > 0) {
//
//            PermintaanVendor requestVendor = permintaanVendorList.get(0);
//            idApproval = requestVendor.getIdApprovalObat();
//
//            // get last number of batch
//            Integer noBatch = 0;
//            try {
//                noBatch = permintaanVendorBo.getLastNoBatch(requestVendor.getIdApprovalObat());
//            } catch (GeneralBOException e) {
//                logger.error("[PermintaanVendorAction.edit] ERROR. ", e);
//                addActionError("[PermintaanVendorAction.edit] ERROR. " + e.getMessage());
//            }
//
//            if (noBatch.compareTo(0) == 1) {
//                isNew = false;
//            }
//            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
//
//            // if edit from list batch then sorted with table batch data to get qty approve and status
//            if ("Y".equalsIgnoreCase(isBatch)) {
//                isNew = true;
//
//                // if new add new batch
//                if ("Y".equalsIgnoreCase(newBatch)) {
//                    noBatch = noBatch + 1;
//                    requestVendor.setNoBatch(noBatch);
//                } else {
//                    requestVendor.setNoBatch(noBatchValue);
//                }
//
//                try {
//                    transaksiObatDetails = permintaanVendorBo.getListTransByBatchSorted(requestVendor.getListOfTransaksiObatDetail(), requestVendor.getNoBatch(), "Y");
//                } catch (GeneralBOException e) {
//                    logger.error("[PermintaanVendorAction.edit] ERROR. ", e);
//                    addActionError("[PermintaanVendorAction.edit] ERROR. " + e.getMessage());
//                }
//
//            } else {
//                transaksiObatDetails.addAll(requestVendor.getListOfTransaksiObatDetail());
//            }
//
//            setPermintaanVendor(requestVendor);
////            session.setAttribute("listOfObatDetail", transaksiObatDetails);
//
//            Vendor vendor = new Vendor();
//            vendor.setIdVendor(requestVendor.getIdVendor());
//            List<Vendor> vendorList = new ArrayList<>();
//
//            try {
//                vendorList = vendorBo.getByCriteria(vendor);
//            } catch (GeneralBOException e) {
//                logger.error("[PermintaanVendorAction.edit] ERROR error when get searh vendor. ", e);
//                addActionError("[PermintaanVendorAction.edit] ERROR error when get searh vendor. " + e.getMessage());
//            }
//
//            Vendor vendorResult = new Vendor();
//            if (!vendorList.isEmpty()) {
//                vendorResult = vendorList.get(0);
//                if (vendorResult != null) {
//                    setVendor(vendorResult);
//                }
//            }
//
//            logger.info("[PermintaanVendorAction.edit] END <<<<<<<");
//            return transaksiObatDetails;
//        }
//
//        return null;
//        if (isNew){
//            return "init_edit";
//        } else {
//            return initListBatch(idApproval, id);
//        }
//        logger.info("[PermintaanVendorAction.initApproval] END process <<<");
//        return "init_approve";

//    }

    public List<TransaksiObatDetail> initApprovalByNoDo(String idpermintaanPo, String noDo) {
        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        if (idpermintaanPo != null && !"".equalsIgnoreCase(idpermintaanPo) && noDo != null && !"".equalsIgnoreCase(noDo)) {
            try {
                transaksiObatDetails = permintaanVendorBo.getListObatByBatchByDo(idpermintaanPo, noDo);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search list obat " + e.getMessage());
            }
        }
        return transaksiObatDetails;
    }

    public List<TransaksiObatDetail> initApproval(String idpermintaanPo, Integer noBatchValue) {
        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        if (idpermintaanPo != null && !"".equalsIgnoreCase(idpermintaanPo) && noBatchValue != null && !"".equalsIgnoreCase(noBatchValue.toString())) {
            try {
                transaksiObatDetails = permintaanVendorBo.getListObatByBatch(idpermintaanPo, noBatchValue);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search list obat " + e.getMessage());
            }
        }
        return transaksiObatDetails;
    }

    public List<Obat> searchObat(String query) {
        List<Obat> obatArrayList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        if (query != null && !"".equalsIgnoreCase(query) && branchId != null && !"".equalsIgnoreCase(branchId)) {
            try {
                obatArrayList = permintaanVendorBo.getSearchObat(query, branchId);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search list obat " + e.getMessage());
            }
        }
        return obatArrayList;
    }

    public String printPermintaanPO() {

        logger.info("[PermintaanVendorAction.printPermintaanPO] START process <<<");

        String idPermintaan = getId();
        Integer noBatch = getNoBatch();

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaan);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.printPermintaanPO] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.printPermintaanPO] ERROR error when get searh obat. " + e.getMessage());
        }
        if (permintaanVendorList.size() > 0) {
            setPermintaanVendor(permintaanVendorList.get(0));

            Vendor vendor = new Vendor();
            vendor.setIdVendor(permintaanVendorList.get(0).getIdVendor());
            List<Vendor> vendorList = new ArrayList<>();

            try {
                vendorList = vendorBoProxy.getByCriteria(vendor);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.printPermintaanPO] ERROR error when get searh vendor. ", e);
                addActionError("[PermintaanVendorAction.printPermintaanPO] ERROR error when get searh vendor. " + e.getMessage());
            }

            Vendor vendorResult = new Vendor();
            if (!vendorList.isEmpty()) {
                vendorResult = vendorList.get(0);
            }

            List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

            TransaksiObatDetail detail = new TransaksiObatDetail();
            detail.setIdApprovalObat(permintaanVendorList.get(0).getIdApprovalObat());
            detail.setFlagDiterima("R");

            try {
                obatDetailList = permintaanVendorBoProxy.getNewObatDetail(detail);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.printPermintaanPO] Error when searching permintan vendor by criteria,", e);
            }

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

            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(obatDetailList);

            reportParams.put("permintaanId", idPermintaan);
            reportParams.put("logo", logo);
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("idVendor", vendorResult.getIdVendor());
            reportParams.put("namaVendor", vendorResult.getNamaVendor());
            reportParams.put("email", vendorResult.getEmail());
            reportParams.put("noTelp", vendorResult.getNoTelp());
            reportParams.put("alamat", vendorResult.getAlamat());
            reportParams.put("petugas", CommonUtil.userLogin());
            reportParams.put("listNewObat", itemData);
            reportParams.put("noBatch", noBatch);
            reportParams.put("tglCair", permintaanVendorList.get(0).getTglCair());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }

            logger.info("[PermintaanVendorAction.printPermintaanPO] LOGO : " + CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_NMU);
        }
        logger.info("[PermintaanVendorAction.printPermintaanPO] END process <<<");
        return "print_po";
    }

    public CheckObatResponse checkFisikObat(String idObat, String idPabrik, String lembarPerBox, String bijiPerLembar) {
        logger.info("[PermintaanVendorAction.checkFisikObatByIdPabrik] START process >>>");
        CheckObatResponse checkObatResponse = new CheckObatResponse();

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setIdPabrik(idPabrik);
        obat.setLembarPerBox(new BigInteger(lembarPerBox));
        obat.setBijiPerLembar(new BigInteger(bijiPerLembar));
        obat.setBranchId(CommonUtil.userBranchLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            checkObatResponse = obatBo.checkFisikObat(obat);
        } catch (GeneralBOException e) {
            checkObatResponse.setStatus("error");
            checkObatResponse.setMessage("[ERROR] " + e.getMessage());
        }

        logger.info("[PermintaanVendorAction.checkFisikObatByIdPabrik] END process <<<");
        return checkObatResponse;
    }

    public String initListBatch(String idApproval, String idPermintaanVendor) {
        logger.info("[PermintaanVendorAction.edit] START >>>>>>>");

        List<BatchPermintaanObat> batchList = new ArrayList<>();
        try {
            batchList = permintaanVendorBoProxy.getListBatchObatByIdApproval(idApproval);
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.edit] ERROR. ", e);
            addActionError("[PermintaanVendorAction.edit] ERROR. " + e.getMessage());
        }

        setId(idPermintaanVendor);
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfBatch");
        session.setAttribute("listOfBatch", batchList);

        logger.info("[PermintaanVendorAction.edit] END <<<<<<<");
        return "list_batch";
    }

    public String printBarcodeBarang() {

        logger.info("[PermintaanVendorAction.printBarcodeBarang] START process <<<");

        String idBarang = getId();

        reportParams.put("idBarang", idBarang);

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printBarcodeBarang] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        logger.info("[PermintaanVendorAction.printBarcodeBarang] END process <<<");
        return "print_barcode_barang";
    }

    public List<TransaksiObatDetail> getListDetailObatApproved(String idPermintaanVendor, Integer noBatch) {
        logger.info("[PermintaanVendorAction.getListDetailObatApproved] START >>>>>>>");

        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            obatDetailList = permintaanVendorBo.getListApprovedBatch(idPermintaanVendor, noBatch);
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.getListDetailObatApproved] ERROR. ", e);
            addActionError("[PermintaanVendorAction.getListDetailObatApproved] ERROR. " + e.getMessage());
        }
        logger.info("[PermintaanVendorAction.getListDetailObatApproved] END <<<<<<<");
        return obatDetailList;
    }

    public List<BatchPermintaanObat> listBatch(String approval){
        List<BatchPermintaanObat> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            list = permintaanVendorBo.getListBatchObatByIdApproval(approval);
        }catch (GeneralBOException e){
            logger.error("Found Error "+e.getMessage());
        }
        return list;
    }

    public CrudResponse tutupPurchaseOrder(String idPermintaanVendor) {

        CrudResponse response = new CrudResponse();
        if (idPermintaanVendor != null && !"".equalsIgnoreCase(idPermintaanVendor)) {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
            VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");

            // create jurnal
            PermintaanVendor permintaanVendor = new PermintaanVendor();
            permintaanVendor.setIdPermintaanVendor(idPermintaanVendor);
            List<PermintaanVendor> permintaanVendorEntities = permintaanVendorBo.getByCriteria(permintaanVendor);

            String idVendor = "";
            String idApproval = "";
            String namaVendor = "";
            if (permintaanVendorEntities.size() > 0) {
                for (PermintaanVendor data : permintaanVendorEntities) {
                    idVendor = data.getIdVendor();
                    idApproval = data.getIdApprovalObat();

                    if (!"".equalsIgnoreCase(idVendor)) {
                        Vendor vendor = new Vendor();
                        vendor.setIdVendor(idVendor);

                        List<Vendor> vendors = vendorBo.getByCriteria(vendor);
                        if (vendors.size() > 0) {
                            for (Vendor dataVendor : vendors) {
                                namaVendor = dataVendor.getNamaVendor();
                            }
                        }
                    }
                }
            }

            List<TransaksiObatDetail> transaksiObatDetails = transaksiObatBo.getListPermintaanBatch(idApproval, "Y");
            List<Map> listMapPersediaan = new ArrayList<>();
            BigDecimal hutangUsaha = new BigDecimal(0);
            BigDecimal ppn = new BigDecimal(0);
            if (transaksiObatDetails.size() > 0) {
                for (TransaksiObatDetail trans : transaksiObatDetails) {

                    BigDecimal hargaRata = new BigDecimal(trans.getHarga());
                    BigDecimal hargaTotal = hargaRata.multiply(new BigDecimal(trans.getQtyApprove()));
                    BigDecimal hargaPpn = hargaTotal.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);

                    // hutang usaha
                    hutangUsaha = hutangUsaha.add(hargaTotal);

                    //ppn
                    ppn = ppn.add(hargaPpn);

                    Map mapHutangUsaha = new HashMap();
                    mapHutangUsaha.put("kd_barang", trans.getIdBarang());
                    mapHutangUsaha.put("nilai", hargaTotal.subtract(hargaPpn));
                    listMapPersediaan.add(mapHutangUsaha);
                }
            }

            // ppn
//            ppn = hutangUsaha.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);

            Map mapHutangVendor = new HashMap();
            mapHutangVendor.put("bukti", idPermintaanVendor);
            mapHutangVendor.put("nilai", hutangUsaha);

            Map jurnalMap = new HashMap();
            jurnalMap.put("master_id", idVendor);
            jurnalMap.put("persediaan_gudang", listMapPersediaan);
            jurnalMap.put("ppn_masukan", ppn);
            jurnalMap.put("hutang_vendor", mapHutangVendor);

            String catatan = "Penerimaan Barang Gudang dari Vendor " + idVendor + " - " + namaVendor;

            String noJurnal = "";
            try {
                Jurnal jurnal = billingSystemBo.createJurnal("13", jurnalMap, CommonUtil.userBranchLogin(), catatan, "Y");
                noJurnal = jurnal.getNoJurnal();
                response.setStatus("success");
            } catch (GeneralBOException e) {
                logger.error("Found Error when search permintaan vendor " + e.getMessage());
                response.setStatus("error");
                response.setMsg("Found Error when search permintaan vendor " + e);
                return response;
            }

            try {
                response = permintaanVendorBo.tutupPurchaseOrder(idPermintaanVendor, noJurnal);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search permintaan vendor " + e.getMessage());
            }
        }
        return response;
    }

    public String uploadDocVendor() {

        PermintaanVendor getPermintaan = getPermintaanVendor();

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(getPermintaan.getIdPermintaanVendor());

        String idPermintaanVendor = getPermintaan.getIdPermintaanVendor();
        String idApproval = "";
        String uploadName = "";
        List<PermintaanVendor> listPermintaan = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        if (listPermintaan.size() > 0) {
            for (PermintaanVendor data : listPermintaan) {
                idApproval = data.getIdApprovalObat();
            }
        }

        permintaanVendor.setNotaVendor(getPermintaan.getNotaVendor());
        permintaanVendor.setUrlDocPo(uploadName);
        permintaanVendor.setAction("U");
        permintaanVendor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        permintaanVendor.setLastUpdateWho(CommonUtil.userLogin());

        try {
            permintaanVendorBoProxy.saveUpoadDocPermintaanVendor(permintaanVendor);
        } catch (GeneralBOException e) {
            logger.error("[PermintaanVendorAction.uploadDocVendor] ERROR ", e);
            addActionError("[PermintaanVendorAction.uploadDocVendor] ERROR " + e);
            return "list_batch";
        }

        return initListBatch(idApproval, idPermintaanVendor);
    }

    public List<MtSimrsPermintaanVendorEntity> getListPermintaanVendorDoc(String idPermintaanVendor) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        return permintaanVendorBo.getListPermintaanVendorDoc(idPermintaanVendor);

    }

    public String uploadImage() {

        List<PermintaanVendor> permintaanVendorList = new ArrayList<PermintaanVendor>();
        PermintaanVendor permintaan = new PermintaanVendor();
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("poupload");

        try {

            PermintaanVendor permintaanVendor = getPermintaanVendor();

            if (this.fileUpload != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                    if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                        // file name
                        String fileName = this.fileUploadFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = permintaanVendor.getIdVendor() + "-" + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_PO;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            permintaan.setUrlDocPo(newFileName);
                            permintaan.setIdVendor(permintaanVendor.getIdVendor());

                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }
        } catch (GeneralBOException e) {
            logger.error("[PermintaanPoAction.AddDoc] Error when adding item ," + "[] Found problem when add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=] Found problem when add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("poupload", permintaan);
        return "search";
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public String initFormVendor(){
        String userId = CommonUtil.userIdLogin();
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        ImUserVendorEntity userVendorEntity = permintaanVendorBo.getEntityUserVendorByIdUser(userId);
        if (userVendorEntity != null){
            permintaanVendor.setIdVendor(userVendorEntity.getIdVendor());
        }
        setPermintaanVendor(permintaanVendor);
        eraseAllSession();
        return "search_vendor";
    }

    public String searchPoVendor(){
        return "search_vendor";
    }

    private void eraseAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfBatch");
    }

    public CrudResponse getListPermintaanVendor(String idPermintaan, String idApproval, String idVendor){

        CrudResponse response = new CrudResponse();
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setTipeTransaksi("request");
        permintaanVendor.setIdPermintaanVendor(idPermintaan);
        permintaanVendor.setIdApprovalObat(idApproval);
        permintaanVendor.setIdVendor(idVendor);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        List<PermintaanVendor> listOfPemintaanVendor = new ArrayList();
        try {
            listOfPemintaanVendor = permintaanVendorBo.getByCriteria(permintaanVendor);
            response.setStatus("success");
//            response.setList(listOfPemintaanVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanVendorAction.getListPermintaanVendor] Error when searching permintan vendor by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            String errorMsg = "[PermintaanVendorAction.getListPermintaanVendor] Error when searching permintan vendor by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin."+ e;
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            response.setStatus("error");
            response.setMsg(errorMsg);
            return response;
        }

        setPermintaanVendor(permintaanVendor);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfPemintaanVendor);
        return response;
    }

    public String addPoVendor(){
        logger.info("[PermintaanVendorAction.edit] START >>>>>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");

        // get parameters
        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(this.id);

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        List<BatchPermintaanObat> listBatch = new ArrayList<>();

        try {
            permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.edit] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.edit] ERROR error when get searh obat. " + e.getMessage());
        }

        if (permintaanVendorList.size() > 0) {
            PermintaanVendor requestVendor = permintaanVendorList.get(0);

            String idApproval = requestVendor.getIdApprovalObat();

            Vendor vendor = new Vendor();
            vendor.setIdVendor(requestVendor.getIdVendor());
            List<Vendor> vendorList = new ArrayList<>();

            try {
                vendorList = vendorBo.getByCriteria(vendor);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.edit] ERROR error when get searh vendor. ", e);
                addActionError("[PermintaanVendorAction.edit] ERROR error when get searh vendor. " + e.getMessage());
            }

            Vendor vendorResult = new Vendor();
            if (!vendorList.isEmpty()) {
                vendorResult = vendorList.get(0);
                if (vendorResult != null) {
                    setVendor(vendorResult);
                }
            }

            try {
                listBatch = permintaanVendorBo.getListBatchObatByIdApproval(idApproval);
            } catch (GeneralBOException e){
                logger.error("[PermintaanVendorAction.edit] ERROR error when get searh list batch. ", e);
                addActionError("[PermintaanVendorAction.edit] ERROR error when get searh list batch. " + e.getMessage());
            }

            setPermintaanVendor(requestVendor);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfBatch");
        session.setAttribute("listOfBatch", listBatch);
        logger.info("[PermintaanVendorAction.edit] END <<<<<<<");
        return "add_po_vendor";
    }

    public List<TransaksiObatDetail> getListTransaksiAdd(String idPermintaan){

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.getListTransaksiAdd] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.getListTransaksiAdd] ERROR error when get searh obat. " + e.getMessage());
        }

        if (permintaanVendorList.size() > 0){
            return permintaanVendorList.get(0).getListOfTransaksiObatDetail();
        }

        return null;
    }

    public CrudResponse saveDo(String idPermintaan, String noDo, String noInvoice, String noFaktur, String tglFaktur, String jsonString , String listImg, String tglInvoice, String tglDo) throws JSONException, IOException {

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        Integer noBatch = new Integer(0);
        MtSimrsPermintaanVendorEntity permintaanVendorEntity = permintaanVendorBo.getPermintaanVendorEntityById(idPermintaan);
        if (permintaanVendorEntity != null){
            noBatch = permintaanVendorBo.getLastNoBatch(permintaanVendorEntity.getIdApprovalObat());
        }
        noBatch = noBatch + 1;

        CrudResponse response = new CrudResponse();
        List<MtSimrsTransaksiObatDetailBatchEntity> listBatchEntity = new ArrayList<>();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
            if (!"".equalsIgnoreCase(obj.get("idtrans").toString())){
                batchEntity.setIdTransaksiObatDetail(obj.get("idtrans") == null ? "" : obj.get("idtrans").toString());
            }
            if (!"".equalsIgnoreCase(obj.get("qty").toString())){
                batchEntity.setQtyApprove(obj.get("qty") == null ? new BigInteger(String.valueOf(0)) : new BigInteger(obj.get("qty").toString()) );
            }
            if (!"".equalsIgnoreCase(obj.get("expdate").toString())){
                batchEntity.setExpiredDate(obj.get("expdate") == null ? null : Date.valueOf(obj.get("expdate").toString()));
            }
            if (!"".equalsIgnoreCase(obj.get("diskon").toString())){
                batchEntity.setDiskon(obj.get("diskon") == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj.get("diskon").toString()) );
            }
            if (!"".equalsIgnoreCase(obj.get("bruto").toString())){
                batchEntity.setBruto(obj.get("bruto") == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj.get("bruto").toString()) );
            }
            if (!"".equalsIgnoreCase(obj.get("nett").toString())){
                batchEntity.setNetto(obj.get("nett") == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(obj.get("nett").toString()) );
            }

            batchEntity.setJenis("do");
            batchEntity.setNoBatch(noBatch);
            batchEntity.setStatus("Y");
            batchEntity.setFlag("Y");
            batchEntity.setAction("C");
            batchEntity.setNoDo(noDo);
            batchEntity.setNoInvoice(noInvoice);
            batchEntity.setNoFaktur(noFaktur);
            batchEntity.setTanggalFaktur(!"".equalsIgnoreCase(tglFaktur) ? Date.valueOf(tglFaktur) : null);
            batchEntity.setTglInvoice(!"".equalsIgnoreCase(tglInvoice) ? Date.valueOf(tglInvoice) : null);
            batchEntity.setTglDo(!"".equalsIgnoreCase(tglDo) ? Date.valueOf(tglDo) : null);
            batchEntity.setCreatedDate(time);
            batchEntity.setCreatedWho(userLogin);
            batchEntity.setLastUpdate(time);
            batchEntity.setLastUpdateWho(userLogin);
            listBatchEntity.add(batchEntity);
        }

        List<ItSimrsDocPoEntity> docPoEntities = new ArrayList<>();
        if (listImg != null && !"".equalsIgnoreCase(listImg)){

            json = new JSONArray(listImg);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                ItSimrsDocPoEntity docPoEntity = new ItSimrsDocPoEntity();
                if (!"".equalsIgnoreCase(obj.get("jenisnomor").toString())){
                    docPoEntity.setJenisNomor(obj.get("jenisnomor") == null ? "" : obj.get("jenisnomor").toString());
                }
//                if (!"".equalsIgnoreCase(obj.get("batch").toString())){
//                    docPoEntity.setNoBatch(obj.get("batch") == null ? null : new Integer(obj.get("batch").toString()) );
//                }
                if (!"".equalsIgnoreCase(obj.get("iditem").toString())){
                    docPoEntity.setIdItem(obj.get("iditem") == null ? null : obj.get("iditem").toString());
                }
                // upload img
                if (!"".equalsIgnoreCase(obj.get("img").toString())){

                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("img"));
                    logger.info("Decoded upload data : " + decodedBytes.length);
                    String fileName = i + docPoEntity.getIdItem()+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_DOC_PO+fileName;
                    logger.info("File save path : " + uploadFile);
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        logger.error("Buffered Image is null");
                    }else{
                        File f = new File(uploadFile);
                        // write the image
                        ImageIO.write(image, "png", f);
                        docPoEntity.setUrlImg(fileName);
                    }
                }

                docPoEntity.setNoBatch(noBatch);
                docPoEntity.setTipe("IMG");
                docPoEntity.setIdPermintaanObatVendor(idPermintaan);
                docPoEntity.setFlag("Y");
                docPoEntity.setAction("C");
                docPoEntity.setCreatedDate(time);
                docPoEntity.setCreatedWho(userLogin);
                docPoEntity.setLastUpdate(time);
                docPoEntity.setLastUpdateWho(userLogin);
                docPoEntities.add(docPoEntity);
            }

            try {
                permintaanVendorBo.saveListDocVendor(docPoEntities);
            } catch (GeneralBOException e){
                logger.error("[PermintaanVendorAction.saveDo] ERROR error when save DOC PO. ", e);
                response.setMsg("[PermintaanVendorAction.saveDo] ERROR. "+ e);
                response.setStatus("error");
            }
        }

        try {
            permintaanVendorBo.saveListBatch(listBatchEntity);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[PermintaanVendorAction.saveDo] ERROR. ", e);
            response.setMsg("[PermintaanVendorAction.saveDo] ERROR. "+ e);
            response.setStatus("error");
            return response;
        }
        return response;
    }

    public TransaksiObatBatch getTransaksiObatByIdTrans(String idTrans, String noBatch){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        return permintaanVendorBo.getBatchByIdTransAndNoBatch(idTrans, noBatch);
    }

    public String printPo(){
        PermintaanVendor getPermintaan = getPermintaanVendor();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        AreaBo areaBo = (AreaBo) ctx.getBean("areaBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        List<PermintaanVendor> permintaanVendors = permintaanVendorBo.getByCriteria(getPermintaan);
        if (permintaanVendors.size() > 0){

            String areaName = "";
            String logo = "";
            PermintaanVendor permintaanVendor = permintaanVendors.get(0);
            if (permintaanVendor.getBranchId() != null){
                Branch branch = branchBo.getBranchById(permintaanVendor.getBranchId(), "Y");
                if (branch != null){

                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branch.getLogoName();
                    Area area = areaBo.getAreaById(branch.getAreaId(), "Y");
                    if (area != null){
                        areaName = area.getAreaName();
                    }
                }
            }

            reportParams.put("idPermintaanVendor", permintaanVendor.getIdPermintaanVendor());
            reportParams.put("idApproval", permintaanVendor.getIdApprovalObat());
            reportParams.put("namaVendor", permintaanVendor.getNamaVendor());
            reportParams.put("namaBranch", permintaanVendor.getBranchName());
            reportParams.put("area", areaName);
            reportParams.put("unit", permintaanVendor.getBranchName());
            reportParams.put("logo", logo);
            reportParams.put("petugas", permintaanVendor.getCreatedWho());


            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }

        }

        return "print_po_vendor";
    }

    public List<DocPo> getListItemDoc(String idPermintaan, String batch){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        return permintaanVendorBo.getListItemDoc(idPermintaan, batch);
    }

    public List<DocPo> getListImg(String idItem){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        return permintaanVendorBo.getListImgByItem(idItem);
    }

    public List<TransaksiObatBatch> getListBatchByJenisItem(String idItem, String jenis) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        return permintaanVendorBo.getListBatchByJenisItem(idItem, jenis);
    }

    public List<TransaksiObatBatch> getListBatchByJenisItem(String idItem, String jenis, String idPermintaan, String batch) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        MtSimrsPermintaanVendorEntity permintaanVendorEntity = permintaanVendorBo.getPermintaanVendorEntityById(idPermintaan);

        return permintaanVendorBo.getListBatchByJenisItem(idItem, jenis, permintaanVendorEntity.getIdApprovalObat(), batch);
    }

    public List<PabrikObat> getListPabrikObatForPo(String idObat, String tipePencarian){
        logger.info("[PermintaanVendorAction.getListPabrikObatForPo] START >>>>>>>");

        List<PabrikObat> pabrikObatList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            pabrikObatList = permintaanVendorBo.getListPabrikObatByIdObatForPo(idObat, tipePencarian);
        } catch (HibernateException e){
            logger.error("[PermintaanVendorBoImpl.getListPabrikObatForPo] ERROR when get data. " + e.getMessage());
            throw new GeneralBOException("[PermintaanVendorBoImpl.getListPabrikObatForPo] ERROR when get data. " + e.getMessage());
        }


        logger.info("[PermintaanVendorAction.getListPabrikObatForPo] END <<<<<<<");
        return pabrikObatList;
    }

    public Obat cekNoProduksi(String noProduksi) {
        Obat obatArrayList = new Obat();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        if (noProduksi != null && !"".equalsIgnoreCase(noProduksi)) {
            try {
                obatArrayList = permintaanVendorBo.cekNoProduksi(noProduksi);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search list obat " + e.getMessage());
            }
        }
        return obatArrayList;
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
