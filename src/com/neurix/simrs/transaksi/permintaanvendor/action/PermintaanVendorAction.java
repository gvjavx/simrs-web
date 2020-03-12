package com.neurix.simrs.transaksi.permintaanvendor.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private String id;
    private String isBatch;
    private Integer noBatch;
    private String newBatch;

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
        if (isNew) {
            return "init_edit";
        } else {
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

    public String saveUpdateListObat(String idTransaksiDetailObat, String qty, String idPabrik, String flag, BigInteger lembarPerBox, BigInteger bijiPerLembar, Integer noBatch, String expDate) {
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
        transaksiObatDetail.setLembarPerBox(lembarPerBox);
        transaksiObatDetail.setBijiPerLembar(bijiPerLembar);
        transaksiObatDetail.setNoBatch(noBatch);
        transaksiObatDetail.setExpDate(Date.valueOf(expDate));
        transaksiObatDetail.setQtyApprove(new BigInteger(qty));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        try {
            permintaanVendorBo.saveUpdateTransObatDetail(transaksiObatDetail);
            msg = SUCCESS;
        } catch (HibernateException e) {
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

    public CheckResponse savePermintaanPO(String idVendor, String po) {

        logger.info("[PermintaanVendorAction.savePermintaanPO] START >>>>>>>");

        CheckResponse response = new CheckResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        PermintaanVendor permintaan = (PermintaanVendor) session.getAttribute("poupload");

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
        permintaanVendor.setUrlDocPo(permintaan.getUrlDocPo());

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
                    obatDetail.setLembarPerBox(new BigInteger(obj.getString("lembar_per_box")));
                    obatDetail.setBijiPerLembar(new BigInteger(obj.getString("biji_per_lembar")));
                    obatDetail.setTipeObat(obj.getString("tipe_obat"));

                    obatDetailList.add(obatDetail);
                }

                permintaanVendor.setListOfTransaksiObatDetail(obatDetailList);
            }
        } catch (JSONException e) {
            logger.error("[PermintaanVendorAction.savePermintaanPO] Error when save permintaan po", e);
        }

        try {
            response = permintaanVendorBo.saveListObatPo(permintaanVendor);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Found Error when searh save permintaan vendor " + e.getMessage());
            logger.error("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. " + e.getMessage());
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
        return "search";

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

    public CheckObatResponse saveApproveBatch(String idPermintaanVendor, Integer noBatch) {
        logger.info("[PermintaanVendorAction.saveApproveBatch] START >>>>>>>");

        CheckObatResponse checkObatResponse = new CheckObatResponse();

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setIdPermintaanVendor(idPermintaanVendor);
        permintaanVendor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        permintaanVendor.setLastUpdateWho(CommonUtil.userLogin());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");

        List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
        try {
            permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
        } catch (HibernateException e) {
            logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. " + e.getMessage());
        }

        if (permintaanVendorList.size() > 0) {
            PermintaanVendor requestVendor = permintaanVendorList.get(0);

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
            try {
                transaksiObatDetails = permintaanVendorBo.getListTransByBatchSorted(requestVendor.getListOfTransaksiObatDetail(), noBatch, "N");
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR. ", e);
                addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR. " + e.getMessage());
            }

            List<TransaksiObatDetail> transaksiObatDetailNew = new ArrayList<>();

            try {
                permintaanVendorBo.saveConfirm(permintaanVendor, transaksiObatDetails, transaksiObatDetailNew);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanVendorAction.saveApproveBatch] Error when save data approve PO", e);
                addActionError(" Error when save data approve PO" + e.getMessage());
            }

        }

        logger.info("[PermintaanVendorAction.saveApproveBatch] START >>>>>>>");
        return checkObatResponse;
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

    public String printPermintaanPO() {

        logger.info("[PermintaanVendorAction.printPermintaanPO] START process <<<");

        String idPermintaan = getId();

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

            switch (branch) {
                case CommonConstant.BRANCH_RS01:
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS01;
                    break;
                case CommonConstant.BRANCH_RS02:
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS02;
                    break;
                case CommonConstant.BRANCH_RS03:
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS03;
                    break;
            }

            JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(obatDetailList);

            reportParams.put("permintaanId", idPermintaan);
            reportParams.put("logo", logo);
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idVendor", vendorResult.getIdVendor());
            reportParams.put("namaVendor", vendorResult.getNamaVendor());
            reportParams.put("email", vendorResult.getEmail());
            reportParams.put("noTelp", vendorResult.getNoTelp());
            reportParams.put("alamat", vendorResult.getAlamat());
            reportParams.put("petugas", CommonUtil.userLogin());
            reportParams.put("listNewObat", itemData);

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
                        ;

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
                noJurnal = billingSystemBo.createJurnal("13", jurnalMap, CommonUtil.userBranchLogin(), catatan, "Y");
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


    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
