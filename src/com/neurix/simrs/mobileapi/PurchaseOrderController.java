package com.neurix.simrs.mobileapi;

import com.google.gson.Gson;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.mobileapi.model.BatchPermintaanObatMobile;
import com.neurix.simrs.mobileapi.model.PurchaseOrderMobile;
import com.neurix.simrs.mobileapi.model.TransaksiObatMobile;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.BatchPermintaanObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gondok
 * Tuesday, 28/01/20 10:47
 */
public class PurchaseOrderController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(PurchaseOrderController.class);
    private PurchaseOrderMobile model = new PurchaseOrderMobile();
    private Collection<PurchaseOrderMobile> listOfPurchaseOrder = new ArrayList<>();
    private Collection<TransaksiObatMobile> listOfTransaksiObat = new ArrayList<>();
    private Collection<BatchPermintaanObatMobile> listOfBatchObat = new ArrayList<>();
    private PermintaanVendorBo permintaanVendorBoProxy;

    private String idPermintaanVendor;

    private String idTransaksiObatDetail;
    private String lembarPerBox;
    private String bijiPerLembar;
    private String qty;
    private String qtyApprove;
    private String expDate;
    private String noBatch;
    private String idPabrik;


    private String idApprovalObat;

    private String jsonObatLama;
    private String jsonObatBaru;
    private String jsonPurchaseOrder;

    private String userId;
    private String flag;
    private String branchId;
    private String action;

    private String diskon;
    private String bruto;
    private String netto;

    private String noFaktur;
    private String tglFaktur;
    private String noInvoice;
    private String noDo;

    private File fotoDocPo;

    private String idPelayanan;
    private String jenis = "";



    public File getFotoDocPo() {
        return fotoDocPo;
    }

    public void setFotoDocPo(File fotoDocPo) {
        this.fotoDocPo = fotoDocPo;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNoFaktur() {
        return noFaktur;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getTglFaktur() {
        return tglFaktur;
    }

    public void setTglFaktur(String tglFaktur) {
        this.tglFaktur = tglFaktur;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public String getNoDo() {
        return noDo;
    }

    public void setNoDo(String noDo) {
        this.noDo = noDo;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getBruto() {
        return bruto;
    }

    public void setBruto(String bruto) {
        this.bruto = bruto;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Collection<BatchPermintaanObatMobile> getListOfBatchObat() {
        return listOfBatchObat;
    }

    public void setListOfBatchObat(Collection<BatchPermintaanObatMobile> listOfBatchObat) {
        this.listOfBatchObat = listOfBatchObat;
    }

    public String getJsonPurchaseOrder() {
        return jsonPurchaseOrder;
    }

    public void setJsonPurchaseOrder(String jsonPurchaseOrder) {
        this.jsonPurchaseOrder = jsonPurchaseOrder;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public String getJsonObatLama() {
        return jsonObatLama;
    }

    public void setJsonObatLama(String jsonObatLama) {
        this.jsonObatLama = jsonObatLama;
    }

    public String getJsonObatBaru() {
        return jsonObatBaru;
    }

    public void setJsonObatBaru(String jsonObatBaru) {
        this.jsonObatBaru = jsonObatBaru;
    }

    public String getNoBatch() {
        return noBatch;
    }

    public void setNoBatch(String noBatch) {
        this.noBatch = noBatch;
    }

    public String getIdTransaksiObatDetail() {
        return idTransaksiObatDetail;
    }

    public void setIdTransaksiObatDetail(String idTransaksiObatDetail) {
        this.idTransaksiObatDetail = idTransaksiObatDetail;
    }

    public Collection<TransaksiObatMobile> getListOfTransaksiObat() {
        return listOfTransaksiObat;
    }

    public void setListOfTransaksiObat(Collection<TransaksiObatMobile> listOfTransaksiObat) {
        this.listOfTransaksiObat = listOfTransaksiObat;
    }

    public String getLembarPerBox() {
        return lembarPerBox;
    }

    public void setLembarPerBox(String lembarPerBox) {
        this.lembarPerBox = lembarPerBox;
    }

    public String getBijiPerLembar() {
        return bijiPerLembar;
    }

    public void setBijiPerLembar(String bijiPerLembar) {
        this.bijiPerLembar = bijiPerLembar;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQtyApprove() {
        return qtyApprove;
    }

    public void setQtyApprove(String qtyApprove) {
        this.qtyApprove = qtyApprove;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdPermintaanVendor() {
        return idPermintaanVendor;
    }

    public void setIdPermintaanVendor(String idPermintaanVendor) {
        this.idPermintaanVendor = idPermintaanVendor;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(PurchaseOrderMobile model) {
        this.model = model;
    }

    public Collection<PurchaseOrderMobile> getListOfPurchaseOrder() {
        return listOfPurchaseOrder;
    }

    public void setListOfPurchaseOrder(Collection<PurchaseOrderMobile> listOfPurchaseOrder) {
        this.listOfPurchaseOrder = listOfPurchaseOrder;
    }

    public PermintaanVendorBo getPermintaanVendorBoProxy() {
        return permintaanVendorBoProxy;
    }

    public void setPermintaanVendorBoProxy(PermintaanVendorBo permintaanVendorBoProxy) {
        this.permintaanVendorBoProxy = permintaanVendorBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action) {
            case "getPO":
                return listOfPurchaseOrder;
            case "getTransaksi":
                return listOfTransaksiObat;
            case "getBatch":
                return listOfBatchObat;
            case "getBatchSorted":
                return listOfTransaksiObat;
            case "addBatch":
                return listOfTransaksiObat;
            case "editBatch":
                return listOfTransaksiObat;
            default:
                return model;
        }

    }

    public HttpHeaders create() {
        logger.info("[PurchaseOrderContoller.create] start process POST / <<<");

        List<TransaksiObatDetail> obatLama = new ArrayList<>();
        List<TransaksiObatDetail> obatBaru = new ArrayList<>();
        PurchaseOrderMobile purchaseOrder = new PurchaseOrderMobile();
        JSONArray jsonArray;

        if(jsonPurchaseOrder!=null && !jsonPurchaseOrder.isEmpty()){
            Gson gson = new Gson();
            purchaseOrder = gson.fromJson(jsonPurchaseOrder, PurchaseOrderMobile.class);
            action = "saveApprove";
        }

        if (jsonObatLama != null && !jsonObatLama.equalsIgnoreCase("")){
            try {
                jsonArray = new JSONArray(jsonObatLama);
                for (int i = 0; i <jsonArray.length(); i++){
                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(jsonArray.getJSONObject(i).getString("idTransaksiObatDetail"));
                    transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                    transaksiObatDetail.setAverageHargaBox(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBox")));
                    transaksiObatDetail.setIdPabrik(jsonArray.getJSONObject(i).getString("idPabrik"));
                    transaksiObatDetail.setExpDate(CommonUtil.convertToDate(jsonArray.getJSONObject(i).getString("expDate")));
                    transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                    transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                    transaksiObatDetail.setIdBarang(jsonArray.getJSONObject(i).getString("idBarang"));
                    transaksiObatDetail.setHargaPo(new BigDecimal(jsonArray.getJSONObject(i).getString("hargaPo")));
                    transaksiObatDetail.setNamaObat(jsonArray.getJSONObject(i).getString("namaObat"));
                    transaksiObatDetail.setAction(jsonArray.getJSONObject(i).getString("action"));
                    transaksiObatDetail.setFlagDiterima(jsonArray.getJSONObject(i).getString("flagDiterima"));
                    if (!jsonArray.getJSONObject(i).getString("noBatch").isEmpty()){
                        transaksiObatDetail.setNoBatch(new Integer(jsonArray.getJSONObject(i).getString("noBatch")));
                    }
                    transaksiObatDetail.setBijiPerLembar(new BigInteger(jsonArray.getJSONObject(i).getString("bijiPerLembar")));
                    transaksiObatDetail.setBranchId(jsonArray.getJSONObject(i).getString("branchId"));
                    if (!jsonArray.getJSONObject(i).getString("averageHargaBiji").isEmpty()){
                        transaksiObatDetail.setAverageHargaBiji(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBiji")));
                    }
                    if (!jsonArray.getJSONObject(i).getString("averageHargaLembar").isEmpty()){
                        transaksiObatDetail.setAverageHargaLembar(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaLembar")));
                    }
                    transaksiObatDetail.setIdApprovalObat(jsonArray.getJSONObject(i).getString("idApprovalObat"));
                    transaksiObatDetail.setQty(new BigInteger(jsonArray.getJSONObject(i).getString("qty")));
                    transaksiObatDetail.setQtyApprove(new BigInteger(jsonArray.getJSONObject(i).getString("qtyApprove")));
                    transaksiObatDetail.setStatus(jsonArray.getJSONObject(i).getString("status"));

                    obatLama.add(transaksiObatDetail);
                }

            } catch (JSONException e) {
                logger.error("[PurchaseOrderController.create] Error, parse json " + e.getMessage());

            }
        }


        if (jsonObatBaru != null && !jsonObatBaru.equalsIgnoreCase("")){
            try {
                jsonArray = new JSONArray(jsonObatLama);
                for (int i = 0; i <jsonArray.length(); i++){
                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(jsonArray.getJSONObject(i).getString("idTransaksiObatDetail"));
                    transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                    transaksiObatDetail.setAverageHargaBox(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBox")));
                    transaksiObatDetail.setIdPabrik(jsonArray.getJSONObject(i).getString("idPabrik"));
                    transaksiObatDetail.setExpDate(CommonUtil.convertToDate(jsonArray.getJSONObject(i).getString("expDate")));
                    transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                    transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                    transaksiObatDetail.setIdBarang(jsonArray.getJSONObject(i).getString("idBarang"));
                    transaksiObatDetail.setHargaPo(new BigDecimal(jsonArray.getJSONObject(i).getString("hargaPo")));
                    transaksiObatDetail.setNamaObat(jsonArray.getJSONObject(i).getString("namaObat"));
                    transaksiObatDetail.setAction(jsonArray.getJSONObject(i).getString("action"));
                    transaksiObatDetail.setFlagDiterima(jsonArray.getJSONObject(i).getString("flagDiterima"));
                    transaksiObatDetail.setNoBatch(new Integer(jsonArray.getJSONObject(i).getString("noBatch")));
                    transaksiObatDetail.setBijiPerLembar(new BigInteger(jsonArray.getJSONObject(i).getString("bijiPerLembar")));
                    transaksiObatDetail.setBranchId(jsonArray.getJSONObject(i).getString("branchId"));
                    if (!jsonArray.getJSONObject(i).getString("averageHargaBiji").isEmpty()){
                        transaksiObatDetail.setAverageHargaBiji(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBiji")));
                    }
                    if (!jsonArray.getJSONObject(i).getString("averageHargaLembar").isEmpty()){
                        transaksiObatDetail.setAverageHargaLembar(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaLembar")));
                    }
                    transaksiObatDetail.setIdApprovalObat(jsonArray.getJSONObject(i).getString("idApprovalObat"));
                    transaksiObatDetail.setQty(new BigInteger(jsonArray.getJSONObject(i).getString("qty")));
                    transaksiObatDetail.setQtyApprove(new BigInteger(jsonArray.getJSONObject(i).getString("qtyApprove")));
                    transaksiObatDetail.setStatus(jsonArray.getJSONObject(i).getString("status"));

                    obatBaru.add(transaksiObatDetail);
                }

            } catch (JSONException e) {
                logger.error("[PurchaseOrderController.create] Error, parse json " + e.getMessage());

            }
        }


        List<PermintaanVendor> result = new ArrayList<>();
        List<TransaksiObatDetail> resultTransaksi = new ArrayList<>();
        List<BatchPermintaanObat> resultBatch = new ArrayList<>();

        PermintaanVendor bean = new PermintaanVendor();
        bean.setIdPermintaanVendor(idPermintaanVendor);
        bean.setIdApprovalObat(idApprovalObat);
        bean.setFlag(flag);
        bean.setBranchId(branchId);

            try {
                result = permintaanVendorBoProxy.getByCriteria(bean);
            }
            catch (GeneralBOException e) {
                logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list purchase order, please info to your admin..." + e.getMessage());
            }

            if (result != null && result.size() > 0) {
                if (action.equalsIgnoreCase("getPO")) {
                    for (PermintaanVendor item : result) {

                        PurchaseOrderMobile purchaseOrderMobile = new PurchaseOrderMobile();
                        purchaseOrderMobile.setIdPermintaanVendor(item.getIdPermintaanVendor());
                        purchaseOrderMobile.setIdApprovalObat(item.getIdApprovalObat());
                        purchaseOrderMobile.setIdVendor(item.getIdVendor());
                        purchaseOrderMobile.setFlag(item.getFlag());
                        purchaseOrderMobile.setAction(item.getFlag());
                        purchaseOrderMobile.setUrlDocPo(item.getUrlDocPo());
                        purchaseOrderMobile.setBranchId(item.getBranchId());
                        purchaseOrderMobile.setIdObat(item.getIdObat());
                        purchaseOrderMobile.setNamaObat(item.getNamaObat());
                        purchaseOrderMobile.setNamaVendor(item.getNamaVendor());
                        purchaseOrderMobile.setIdPelayanan(item.getIdPelayanan());
                        purchaseOrderMobile.setStCreatedDate(item.getStCreatedDate());
                        purchaseOrderMobile.setKeterangan(item.getKeterangan());
                        purchaseOrderMobile.setApprovalFlag(item.getApprovalFlag());
                        purchaseOrderMobile.setNotFlagR(item.getNotFlagR());

                        listOfPurchaseOrder.add(purchaseOrderMobile);
                    }
                }


                if (action.equalsIgnoreCase("getTransaksi")) {
                    resultTransaksi = result.get(0).getListOfTransaksiObatDetail();
                    for (TransaksiObatDetail item : resultTransaksi) {
                            TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();
                            transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                            transaksiObatMobile.setNamaObat(item.getNamaObat());
                            transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                            transaksiObatMobile.setIdObat(item.getIdObat());
                            transaksiObatMobile.setKeterangan(item.getKeterangan());
//                        transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
//                        transaksiObatMobile.setQtyBox(item.getQtyBox().toString());
//                        transaksiObatMobile.setQtyLembar(item.getQtyLembar().toString());
//                        transaksiObatMobile.setQtyBiji(item.getQtyBiji().toString());
                            transaksiObatMobile.setQty(item.getQty().toString());
                            transaksiObatMobile.setLembarPerBox(item.getLembarPerBox().toString());
                            transaksiObatMobile.setBijiPerLembar(item.getBijiPerLembar().toString());
                            transaksiObatMobile.setAverageHargaBox(item.getAverageHargaBox().toString());
//                        transaksiObatMobile.setAverageHargaLembar(item.getAverageHargaLembar().toString());
//                        transaksiObatMobile.setAverageHargaBiji(item.getAverageHargaBiji().toString());
                            transaksiObatMobile.setFlagDiterima(item.getFlagDiterima());
                            transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                            transaksiObatMobile.setIdPabrik(item.getIdPabrik());
                            transaksiObatMobile.setMerek(item.getMerek());
                            if (item.getSumQtyApprove() != null) {
                                transaksiObatMobile.setSumQtyApprove(item.getSumQtyApprove().toString());
                            } else transaksiObatMobile.setSumQtyApprove("0");


                        if ("box".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBox().toString());
                            }
                            if ("lembar".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaLembar().toString());
                            }
                            if ("biji".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBiji().toString());
                            }

                            listOfTransaksiObat.add(transaksiObatMobile);
                        }


                }

                if (action.equalsIgnoreCase("saveTransaksi")) {

                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(idTransaksiObatDetail);
                    transaksiObatDetail.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    transaksiObatDetail.setLastUpdateWho(userId);
                    transaksiObatDetail.setQty(new BigInteger(qty));
                    transaksiObatDetail.setIdPabrik(idPabrik);
                    transaksiObatDetail.setFlagDiterima(flag);
                    transaksiObatDetail.setLembarPerBox(new BigInteger(lembarPerBox));
                    transaksiObatDetail.setBijiPerLembar(new BigInteger(bijiPerLembar));

                    if (noBatch.isEmpty()){
                        transaksiObatDetail.setNoBatch(0);
                    } else transaksiObatDetail.setNoBatch(new Integer(noBatch));

                    transaksiObatDetail.setExpDate(CommonUtil.convertStringToDate(expDate));
                    transaksiObatDetail.setQtyApprove(new BigInteger(qtyApprove));
                    transaksiObatDetail.setBruto(new BigDecimal(bruto));
                    transaksiObatDetail.setNetto(new BigDecimal(netto));
                    transaksiObatDetail.setDiskon(new BigDecimal(diskon));

                    try {
                        permintaanVendorBoProxy.saveUpdateTransObatDetail(transaksiObatDetail);
                    } catch (GeneralBOException e) {
                        logger.error("[PurchaseOrderController.create] ERROR saveTransaksi. ", e);
                    }
                }

                if (action.equalsIgnoreCase("addBatch")) {

                    if (result.size() != 0) {
                        PermintaanVendor permintaanVendor = result.get(0);

                        Integer noBatch = 0;
                        try {
                            noBatch = permintaanVendorBoProxy.getLastNoBatch(permintaanVendor.getIdApprovalObat());
                        } catch (GeneralBOException e) {
                            logger.error("[PurchaseOrderController.create] ERROR addBatch. ", e);
                        }

                        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

                        noBatch += 1;
                        permintaanVendor.setNoBatch(noBatch);

                        try {
                            transaksiObatDetails = permintaanVendorBoProxy.getListTransByBatchSorted(permintaanVendor.getListOfTransaksiObatDetail(), permintaanVendor.getNoBatch(), "N");
                        } catch (GeneralBOException e) {
                            logger.error("[PurchaseOrderController.create] ERROR addBatch. ", e);
                        }

                        for(TransaksiObatDetail item : transaksiObatDetails){
                            TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();

                            transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                            transaksiObatMobile.setNamaObat(item.getNamaObat());
                            transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                            transaksiObatMobile.setIdObat(item.getIdObat());
                            transaksiObatMobile.setKeterangan(item.getKeterangan());
                            transaksiObatMobile.setQtyApprove(item.getSumQtyApprove().toString());
                            transaksiObatMobile.setIsFullOfQty(item.getIsFullOfQty());
                            transaksiObatMobile.setSumQtyApprove(item.getSumQtyApprove().toString());
    //                        transaksiObatMobile.setQtyBox(item.getQtyBox().toString());
    //                        transaksiObatMobile.setQtyLembar(item.getQtyLembar().toString());
    //                        transaksiObatMobile.setQtyBiji(item.getQtyBiji().toString());
//                            transaksiObatMobile.setExpDate(item.getExpDate().toString());
//                            transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
                            transaksiObatMobile.setQty(item.getQty().toString());
                            transaksiObatMobile.setLembarPerBox(item.getLembarPerBox().toString());
                            transaksiObatMobile.setBijiPerLembar(item.getBijiPerLembar().toString());
                            transaksiObatMobile.setAverageHargaBox(item.getAverageHargaBox().toString());
//                        transaksiObatMobile.setAverageHargaLembar(item.getAverageHargaLembar().toString());
//                        transaksiObatMobile.setAverageHargaBiji(item.getAverageHargaBiji().toString());
                            transaksiObatMobile.setFlagDiterima(item.getFlagDiterima());
                            transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                            transaksiObatMobile.setIdPabrik(item.getIdPabrik());
                            transaksiObatMobile.setMerek(item.getMerek());
                            transaksiObatMobile.setNoBatch(item.getNoBatch().toString());

                            if ("box".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBox().toString());
                            }
                            if ("lembar".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaLembar().toString());
                            }
                            if ("biji".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBiji().toString());
                            }


                            listOfTransaksiObat.add(transaksiObatMobile);
                        }
                    }
                }

                if (action.equalsIgnoreCase("editBatch")){
                        PermintaanVendor permintaanVendor = result.get(0);


                        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

                        transaksiObatDetails.addAll(permintaanVendor.getListOfTransaksiObatDetail());

                        for(TransaksiObatDetail item : transaksiObatDetails){
                            TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();

                            transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                            transaksiObatMobile.setNamaObat(item.getNamaObat());
                            transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                            transaksiObatMobile.setIdObat(item.getIdObat());
                            transaksiObatMobile.setKeterangan(item.getKeterangan());
                            transaksiObatMobile.setQtyApprove(item.getSumQtyApprove().toString());
                            transaksiObatMobile.setIsFullOfQty(item.getIsFullOfQty());
//                        transaksiObatMobile.setQtyBox(item.getQtyBox().toString());
//                        transaksiObatMobile.setQtyLembar(item.getQtyLembar().toString());
//                        transaksiObatMobile.setQtyBiji(item.getQtyBiji().toString());
//                            transaksiObatMobile.setExpDate(item.getExpDate().toString());
//                            transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
                            transaksiObatMobile.setQty(item.getQty().toString());
                            transaksiObatMobile.setLembarPerBox(item.getLembarPerBox().toString());
                            transaksiObatMobile.setBijiPerLembar(item.getBijiPerLembar().toString());
                            transaksiObatMobile.setAverageHargaBox(item.getAverageHargaBox().toString());
//                        transaksiObatMobile.setAverageHargaLembar(item.getAverageHargaLembar().toString());
//                        transaksiObatMobile.setAverageHargaBiji(item.getAverageHargaBiji().toString());
                            transaksiObatMobile.setFlagDiterima(item.getFlagDiterima());
                            transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                            transaksiObatMobile.setIdPabrik(item.getIdPabrik());
                            transaksiObatMobile.setMerek(item.getMerek());
                            transaksiObatMobile.setNoBatch(noBatch);

                            if ("box".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBox().toString());
                            }
                            if ("lembar".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaLembar().toString());
                            }
                            if ("biji".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBiji().toString());
                            }


                            listOfTransaksiObat.add(transaksiObatMobile);
                        }


                }

                if (action.equalsIgnoreCase("getBatch")){

                    try{
                        resultBatch = permintaanVendorBoProxy.getListBatchObatByIdApproval(idApprovalObat);
                    } catch (GeneralBOException e){
                        logger.error("[PurchaseOrderController.create] ERROR getBatch. ", e);
                    }

                    for (BatchPermintaanObat item : resultBatch){
                        BatchPermintaanObatMobile batchPermintaanObatMobile = new BatchPermintaanObatMobile();
                        batchPermintaanObatMobile.setIdApproval(item.getIdApproval());
                        batchPermintaanObatMobile.setIsApprove(item.getIsApprove());
                        batchPermintaanObatMobile.setLastUpdateWho(item.getLastUpdateWho());
                        batchPermintaanObatMobile.setStLastUpdateWho(item.getStLastUpdateWho());
                        batchPermintaanObatMobile.setLastUpdate(item.getLastUpdate().toLocaleString());
                        batchPermintaanObatMobile.setNoBatch(item.getNoBatch().toString());
                        batchPermintaanObatMobile.setStatus(item.getStatus());
                        batchPermintaanObatMobile.setStatusName(item.getStatusName());

                        listOfBatchObat.add(batchPermintaanObatMobile);
                    }
                }

                if (action.equalsIgnoreCase("getBatchSorted")){

                    List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

                    if (result.size() != 0) {
                        PermintaanVendor permintaanVendor = result.get(0);

                        try{
                          transaksiObatDetails = permintaanVendorBoProxy.getListTransByBatchSorted(permintaanVendor.getListOfTransaksiObatDetail(), Integer.valueOf(noBatch), "Y");

                        } catch (GeneralBOException e){
                            logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when retieving list batch , please info to your admin..." + e.getMessage());
                        }

                        for (TransaksiObatDetail item: transaksiObatDetails){
                            TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();
                            transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                            transaksiObatMobile.setNamaObat(item.getNamaObat());
                            transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                            transaksiObatMobile.setIdObat(item.getIdObat());
                            transaksiObatMobile.setKeterangan(item.getKeterangan());
                            transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
                            transaksiObatMobile.setIsFullOfQty(item.getIsFullOfQty());
//                        transaksiObatMobile.setQtyBox(item.getQtyBox().toString());
//                        transaksiObatMobile.setQtyLembar(item.getQtyLembar().toString());
//                        transaksiObatMobile.setQtyBiji(item.getQtyBiji().toString());
//                            transaksiObatMobile.setExpDate(item.getExpDate().toString());
//                            transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
                            transaksiObatMobile.setQty(item.getQty().toString());
                            transaksiObatMobile.setLembarPerBox(item.getLembarPerBox().toString());
                            transaksiObatMobile.setBijiPerLembar(item.getBijiPerLembar().toString());
                            transaksiObatMobile.setAverageHargaBox(item.getAverageHargaBox().toString());
//                        transaksiObatMobile.setAverageHargaLembar(item.getAverageHargaLembar().toString());
//                        transaksiObatMobile.setAverageHargaBiji(item.getAverageHargaBiji().toString());
                            transaksiObatMobile.setFlagDiterima(item.getFlagDiterima());
                            transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                            transaksiObatMobile.setIdPabrik(item.getIdPabrik());
                            transaksiObatMobile.setMerek(item.getMerek());
                            transaksiObatMobile.setNoBatch(item.getNoBatch().toString());


                            if ("box".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBox().toString());
                            }
                            if ("lembar".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaLembar().toString());
                            }
                            if ("biji".equalsIgnoreCase(item.getJenisSatuan())) {
                                transaksiObatMobile.setHargaPo(item.getAverageHargaBiji().toString());
                            }

                            listOfTransaksiObat.add(transaksiObatMobile);
                        }

                    }
                }

                if (action.equalsIgnoreCase("saveApprove")) {


                   try {
                      result = permintaanVendorBoProxy.getByCriteria(bean);
                   } catch (GeneralBOException e){
                       logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                       throw new GeneralBOException("Found problem when retieving list purchase order, please info to your admin..." + e.getMessage());
                   }

                   result.get(0).setBranchId(branchId);
                   result.get(0).setIsMobile("Y");

                   try {
                       permintaanVendorBoProxy.saveConfirm(result.get(0), obatLama, obatBaru);
                       model.setMessage("Success");
                   } catch (GeneralBOException e) {
                       logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                       throw new GeneralBOException("Found problem when retieving list purchase order, please info to your admin..." + e.getMessage());
                   }
                }

                if  (action.equalsIgnoreCase("konfirmasi")) {

                    CheckObatResponse checkObatResponse = new CheckObatResponse();

                    PermintaanVendor permintaanVendor = new PermintaanVendor();
                    permintaanVendor.setIdPermintaanVendor(idPermintaanVendor);
                    permintaanVendor.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                    permintaanVendor.setLastUpdateWho(userId);

                    ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                    PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
                    TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
                    ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
                    BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
                    PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
                    VendorBo vendorBo  = (VendorBo) ctx.getBean("vendorBoProxy");
                    PositionBo positionBo  = (PositionBo) ctx.getBean("positionBoProxy");


                    if  (fotoDocPo != null) {
                        String fileNamePhoto = noFaktur + "-" + dateFormater("MM") + dateFormater("yy") + CommonConstant.IMAGE_TYPE;
                        permintaanVendor.setUrlDoc(fileNamePhoto);
                        File fileCreate = new File(CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_PO+ fileNamePhoto);
                        try {
                            FileUtils.copyFile(fotoDocPo, fileCreate);
                        }catch (IOException e){
                            e.printStackTrace();
                            model.setMessage("error");
                        }
                    }


                    permintaanVendor.setNoFaktur(noFaktur);
                    permintaanVendor.setTanggalFaktur(CommonUtil.convertStringToDate(tglFaktur));
                    permintaanVendor.setNoInvoice(noInvoice);
                    permintaanVendor.setNoDo(noDo);
                    permintaanVendor.setIdPelayanan(idPelayanan);

                    List<PermintaanVendor> permintaanVendorList = new ArrayList<>();
                    try {
                        permintaanVendorList = permintaanVendorBo.getByCriteria(permintaanVendor);
                    } catch (HibernateException e) {
                        logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. ", e);
//                        addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR error when get searh obat. " + e.getMessage());
                        model.setMessage("error");

                    }

                    if (permintaanVendorList.size() > 0) {
                        PermintaanVendor requestVendor = permintaanVendorList.get(0);

                        // set permintaanVendor OBJECT idVendor Object;
                        permintaanVendor.setIdVendor(requestVendor.getIdVendor());
                        permintaanVendor.setBranchId(branchId);

                        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
                        try {
                            transaksiObatDetails = permintaanVendorBo.getListTransByBatchSorted(requestVendor.getListOfTransaksiObatDetail(), Integer.valueOf(noBatch), "Y");
                        } catch (GeneralBOException e) {
                            logger.error("[PermintaanVendorAction.saveApproveBatch] ERROR. ", e);
//                            addActionError("[PermintaanVendorAction.saveApproveBatch] ERROR. " + e.getMessage());
                            model.setMessage("error");
                        }

                        List<TransaksiObatDetail> transaksiObatDetailNew = new ArrayList<>();

                        try {
                            checkObatResponse = permintaanVendorBo.saveConfirm(permintaanVendor, transaksiObatDetails, transaksiObatDetailNew);
                        } catch (GeneralBOException e) {
                            logger.error("[PermintaanVendorAction.saveApproveBatch] Error when save data approve PO", e);
//                            addActionError(" Error when save data approve PO" + e.getMessage());
                            model.setMessage("error");

                        }



                        List<Map> listMapPersediaan = new ArrayList<>();
                        BigDecimal hutangUsaha = new BigDecimal(0);
                        BigDecimal ppn = new BigDecimal(0);
                        if (transaksiObatDetails.size() > 0) {
                            for (TransaksiObatDetail trans : transaksiObatDetails) {

                                ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(trans.getIdBarang());
                                if (obatEntity == null){
                                    logger.error("Found Error when search master obat is null");
                                    checkObatResponse.setMessage("Found Error when search master obat is null");
                                    checkObatResponse.setStatus("error");
                                    model.setMessage(checkObatResponse.getStatus());
                                }

                                BigInteger cons = obatEntity.getLembarPerBox().multiply(obatEntity.getBijiPerLembar());

                                // jika harga bukan pengembalian reture pakai harga terakhir;
                                BigDecimal hargaRata = new BigDecimal(0);
                                if (!"reture".equalsIgnoreCase(jenis)){
                                    hargaRata = obatEntity.getHargaTerakhir().multiply(new BigDecimal(cons));
                                } else {
                                    if ("box".equalsIgnoreCase(trans.getJenisSatuan())){
                                        hargaRata = hargaRata.add(obatEntity.getAverageHargaBox());
                                    } if ("lembar".equalsIgnoreCase(trans.getJenisSatuan())){
                                        hargaRata = hargaRata.add(obatEntity.getAverageHargaLembar());
                                    } if ("biji".equalsIgnoreCase(trans.getJenisSatuan())){
                                        hargaRata = hargaRata.add(obatEntity.getAverageHargaBiji());
                                    }
                                }

                                BigDecimal hargaTotal = hargaRata.multiply(new BigDecimal(trans.getQtyApprove()));
                                BigDecimal hargaPpn = new BigDecimal( 0);

                                // hutang usaha
                                hutangUsaha = hutangUsaha.add(hargaTotal);

                                //ppn
                                // jika bukan pengembalian reture maka pakai ppn
                                if (!"reture".equalsIgnoreCase(jenis)){
                                    hargaPpn = hargaTotal.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                                    ppn = ppn.add(hargaPpn);
                                }

                                Map mapHutangUsaha = new HashMap();
                                mapHutangUsaha.put("kd_barang", trans.getIdBarang());
                                mapHutangUsaha.put("nilai", hargaTotal.subtract(hargaPpn));
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

                            ImSimrsPelayananEntity pelayananEntity = pelayananEntity = pelayananBo.getPelayananById(idPelayanan);
                            if (pelayananEntity != null){

                                ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                                if (position != null){
                                    divisiId = position.getKodering();
                                }
                            }

                            Map mapBiaya = new HashMap();
                            mapBiaya.put("divisi_id", divisiId);
                            mapBiaya.put("nilai", hutangUsaha);

                            jurnalMap.put("divisi_id", divisiId);
                            jurnalMap.put("persediaan_gudang", listMapPersediaan);
                            jurnalMap.put("biaya_persediaan_obat", mapBiaya);

                            catatan = "Pengganti Barang No. Transaksi "+idPermintaanVendor+". Retur Vendor ke Gudang dari Vendor " + requestVendor.getIdVendor() + " - " + namaVendor;
                            transId = "36";
                        } else {

                            Map mapPajakObat = new HashMap();
                            mapPajakObat.put("bukti", noFaktur);
                            mapPajakObat.put("nilai", ppn);
                            mapPajakObat.put("master_id", requestVendor.getIdVendor());

                            Map mapHutangVendor = new HashMap();
                            mapHutangVendor.put("bukti", noDo);
                            mapHutangVendor.put("nilai", hutangUsaha);
                            mapHutangVendor.put("master_id", requestVendor.getIdVendor());
                            mapHutangVendor.put("divisi_id", divisiId);

                            jurnalMap.put("persediaan_gudang", listMapPersediaan);
                            jurnalMap.put("hutang_farmasi_vendor", mapHutangVendor);
                            jurnalMap.put("ppn_masukan", mapPajakObat);
                            jurnalMap.put("user_id", userId);

                            catatan = "Penerimaan Barang Gudang dari Vendor " + requestVendor.getIdVendor() + " - " + namaVendor;
                            transId = "27";
                        }

                        String noJurnal = "";
                        try {
                            Jurnal jurnal = billingSystemBo.createJurnal(transId, jurnalMap, branchId, catatan, "Y");
                            noJurnal = jurnal.getNoJurnal();
                            model.setMessage("success");
                        } catch (GeneralBOException e) {
                            logger.error("Found Error when search permintaan vendor " + e.getMessage());
                            checkObatResponse.setMessage("Found Error when search permintaan vendor " + e.getMessage());
                            checkObatResponse.setStatus("error");
//                            addActionError(" Error when save data approve PO" + e.getMessage());
                            model.setMessage(checkObatResponse.getStatus());
                        }
                    }
                }
            }

        logger.info("[PurchaseOrderContoller.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }
}
