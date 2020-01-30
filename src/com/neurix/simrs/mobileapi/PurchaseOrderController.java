package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.mobileapi.model.PurchaseOrderMobile;
import com.neurix.simrs.mobileapi.model.TransaksiObatMobile;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 28/01/20 10:47
 */
public class PurchaseOrderController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(PurchaseOrderController.class);
    private PurchaseOrderMobile model = new PurchaseOrderMobile();
    private Collection<PurchaseOrderMobile> listOfPurchaseOrder = new ArrayList<>();
    private Collection<TransaksiObatMobile> listOfTransaksiObat = new ArrayList<>();
    private PermintaanVendorBo permintaanVendorBoProxy;

    private String idPermintaanVendor;
    private String idTransaksiObatDetail;
    private String idApprovalObat;

    private String lembarPerBox;
    private String bijiPerLembar;
    private String qty;
    private String qtyApprove;
    private String expDate;

    private String flag;
    private String branchId;
    private String action;

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
            default:
                return model;
        }

    }

    public HttpHeaders create() {
        logger.info("[PurchaseOrderContoller.create] start process POST / <<<");

        List<PermintaanVendor> result = new ArrayList<>();

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
                        if (item.getKeterangan().equalsIgnoreCase("Proses Verifikasi")) {
                            PurchaseOrderMobile purchaseOrderMobile = new PurchaseOrderMobile();
                            purchaseOrderMobile.setIdPermintaanVendor(item.getIdPermintaanVendor());
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
                }


                if (action.equalsIgnoreCase("getTransaksi")) {
                    List<TransaksiObatDetail> resultTransaksi = new ArrayList<>();
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

                if (action.equalsIgnoreCase("saveApprove")) {

                    List<TransaksiObatDetail> listObat = new ArrayList<>();
                    TransaksiObatDetail obat = new TransaksiObatDetail();
                    obat.setIdApprovalObat(idApprovalObat);

                    List<TransaksiObatDetail> listObatNew = new ArrayList<>();

                   try {
                      result = permintaanVendorBoProxy.getByCriteria(bean);
                   } catch (GeneralBOException e){
                       logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                       throw new GeneralBOException("Found problem when retieving list purchase order, please info to your admin..." + e.getMessage());
                   }

                   try {
                       permintaanVendorBoProxy.saveConfirm(result.get(0), listObat, listObatNew);
                   } catch (GeneralBOException e) {
                       logger.error("[PurchaseOrderController.create] Error, " + e.getMessage());
                       throw new GeneralBOException("Found problem when retieving list purchase order, please info to your admin..." + e.getMessage());
                   }
                }
            }

        logger.info("[PurchaseOrderContoller.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
