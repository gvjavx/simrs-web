package com.neurix.simrs.mobileapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsHeaderObatEntity;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import com.neurix.simrs.mobileapi.model.ObatMobile;
import com.neurix.simrs.mobileapi.model.PermintaanObatMobile;
import com.neurix.simrs.mobileapi.model.PurchaseOrderMobile;
import com.neurix.simrs.mobileapi.model.TransaksiObatMobile;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.opensymphony.xwork2.ModelDriven;
import groovy.json.JsonException;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Sunday, 02/02/20 20:10
 */
public class PermintaanObatController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(PermintaanObatController.class);
    private PermintaanObatMobile model = new PermintaanObatMobile();
    private Collection<PermintaanObatMobile> listOfPermintaanObat = new ArrayList<>();
    private Collection<TransaksiObatMobile> listOfTransaksiObatDetail = new ArrayList<>();
    private Collection<ObatMobile> listOfObat = new ArrayList<>();


    private ObatPoliBo obatPoliBoProxy;
    private ObatBo obatBoProxy;
    private ParameterKeteranganObatBo parameterKeteranganObatBoProxy;
    private KeteranganObatBo keteranganObatBoProxy;

    private String tipePermintaan;
    private String idPelayanan;
    private String flag;
    private String flagPoli;

    private String idApproval;
    private String idPermintaan;
    private String idPermintaanObatPoli;

    private String idObat;
    private String namaObat;

    private String branchId;
    private String action;

    private String idBatch;
    private String idJenis;

    private String idParam;

    private String jsonObatVerifikasi;
    private String jsonTransaksiObatDetail;
    private String jsonPermintaanObat;

    public String getIdParam() {
        return idParam;
    }

    public void setIdParam(String idParam) {
        this.idParam = idParam;
    }

    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public ParameterKeteranganObatBo getParameterKeteranganObatBoProxy() {
        return parameterKeteranganObatBoProxy;
    }

    public void setParameterKeteranganObatBoProxy(ParameterKeteranganObatBo parameterKeteranganObatBoProxy) {
        this.parameterKeteranganObatBoProxy = parameterKeteranganObatBoProxy;
    }

    public KeteranganObatBo getKeteranganObatBoProxy() {
        return keteranganObatBoProxy;
    }

    public void setKeteranganObatBoProxy(KeteranganObatBo keteranganObatBoProxy) {
        this.keteranganObatBoProxy = keteranganObatBoProxy;
    }

    public String getIdBatch() {
        return idBatch;
    }

    public void setIdBatch(String idBatch) {
        this.idBatch = idBatch;
    }

    public String getJsonTransaksiObatDetail() {
        return jsonTransaksiObatDetail;
    }

    public void setJsonTransaksiObatDetail(String jsonTransaksiObatDetail) {
        this.jsonTransaksiObatDetail = jsonTransaksiObatDetail;
    }

    public String getJsonPermintaanObat() {
        return jsonPermintaanObat;
    }

    public void setJsonPermintaanObat(String jsonPermintaanObat) {
        this.jsonPermintaanObat = jsonPermintaanObat;
    }

    public String getJsonObatVerifikasi() {
        return jsonObatVerifikasi;
    }

    public void setJsonObatVerifikasi(String jsonObatVerifikasi) {
        this.jsonObatVerifikasi = jsonObatVerifikasi;
    }

    public Collection<ObatMobile> getListOfObat() {
        return listOfObat;
    }

    public void setListOfObat(Collection<ObatMobile> listOfObat) {
        this.listOfObat = listOfObat;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public ObatBo getObatBoProxy() {
        return obatBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Collection<TransaksiObatMobile> getListOfTransaksiObatDetail() {
        return listOfTransaksiObatDetail;
    }

    public void setListOfTransaksiObatDetail(Collection<TransaksiObatMobile> listOfTransaksiObatDetail) {
        this.listOfTransaksiObatDetail = listOfTransaksiObatDetail;
    }

    public String getIdPermintaanObatPoli() {
        return idPermintaanObatPoli;
    }

    public void setIdPermintaanObatPoli(String idPermintaanObatPoli) {
        this.idPermintaanObatPoli = idPermintaanObatPoli;
    }

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public void setModel(PermintaanObatMobile model) {
        this.model = model;
    }

    public String getFlagPoli() {
        return flagPoli;
    }

    public void setFlagPoli(String flagPoli) {
        this.flagPoli = flagPoli;
    }

    public String getTipePermintaan() {
        return tipePermintaan;
    }

    public void setTipePermintaan(String tipePermintaan) {
        this.tipePermintaan = tipePermintaan;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Collection<PermintaanObatMobile> getListOfPermintaanObat() {
        return listOfPermintaanObat;
    }

    public void setListOfPermintaanObat(Collection<PermintaanObatMobile> listOfPermintaanObat) {
        this.listOfPermintaanObat = listOfPermintaanObat;
    }

    public ObatPoliBo getObatPoliBoProxy() {
        return obatPoliBoProxy;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action) {
            case "getPermintaan":
                return listOfPermintaanObat;
            case "getRequestObat":
                return listOfPermintaanObat;
            case "getListObatTelahDiterima":
                return listOfTransaksiObatDetail;
            case "getListObat":
                return listOfTransaksiObatDetail;
            case "getEntityObat":
                return listOfObat;
            case "getComboParameterWaktu":
                return listOfPermintaanObat;
            case "getComboParameterObat":
                return listOfPermintaanObat;
            case "getComboKeteranganObat":
                return listOfPermintaanObat;
            default: return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[PermintaanObatContoller.create] start process POST / <<<");

        List<Obat> jsonObat = new ArrayList<>();
        List<TransaksiObatDetail> jsonObatDetail = new ArrayList<>();
        PermintaanObatPoli permintaanObat = new PermintaanObatPoli();
        JSONArray jsonArray;

        if(jsonPermintaanObat!=null && !jsonPermintaanObat.isEmpty()){
            Gson gson = new Gson();
            permintaanObat = gson.fromJson(jsonPermintaanObat, PermintaanObatPoli.class);
        }


        if(jsonObatVerifikasi != null && !jsonObatVerifikasi.equalsIgnoreCase("")) {
            try {
                jsonArray = new JSONArray(jsonObatVerifikasi);
                for (int i = 0; i < jsonArray.length(); i++){
                    Obat obat = new Obat();
                    obat.setIdBarang(jsonArray.getJSONObject(i).getString("idBarang"));
                    obat.setIdTransaksiDetail(jsonArray.getJSONObject(i).getString("idTransaksiDetail"));
                    if (!jsonArray.getJSONObject(i).getString("qtyApprove").equalsIgnoreCase("") && jsonArray.getJSONObject(i).getString("qtyApprove") != null) {
                        obat.setQtyApprove(new BigInteger(jsonArray.getJSONObject(i).getString("qtyApprove")));
                    }
                    obat.setLastUpdate(CommonUtil.convertToTimestamp(jsonArray.getJSONObject(i).getString("lastUpdate")));
                    obat.setLastUpdateWho(jsonArray.getJSONObject(i).getString("lastUpdateWho"));
                    obat.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                    obat.setCreatedDate(CommonUtil.convertToTimestamp(jsonArray.getJSONObject(i).getString("createdDate")));
                    obat.setCreatedWho(jsonArray.getJSONObject(i).getString("createdWho"));
                    obat.setExpiredDate(Date.valueOf(jsonArray.getJSONObject(i).getString("expiredDate")));

                    jsonObat.add(obat);
                }
            } catch (JSONException e){
                logger.error("[PermintaanObatController.create] Error, get json obat " + e.getMessage());
            }
        }

        if (jsonTransaksiObatDetail != null && !jsonTransaksiObatDetail.equalsIgnoreCase("")){
            try {
                jsonArray = new JSONArray(jsonTransaksiObatDetail);
                for (int i = 0; i <jsonArray.length(); i++){
                    TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdTransaksiObatDetail(jsonArray.getJSONObject(i).getString("idTransaksiObatDetail"));
                    transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                    if (!jsonArray.getJSONObject(i).getString("averageHargaBox").isEmpty()){
                        transaksiObatDetail.setAverageHargaBox(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBox")));

                    }
                    transaksiObatDetail.setIdPabrik(jsonArray.getJSONObject(i).getString("idPabrik"));
                    transaksiObatDetail.setExpDate(CommonUtil.convertToDate(jsonArray.getJSONObject(i).getString("expDate")));
                    transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                    transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                    transaksiObatDetail.setIdBarang(jsonArray.getJSONObject(i).getString("idBarang"));
                    if (!jsonArray.getJSONObject(i).getString("hargaPo").isEmpty()){
                        transaksiObatDetail.setHargaPo(new BigDecimal(jsonArray.getJSONObject(i).getString("hargaPo")));
                    }
                    transaksiObatDetail.setNamaObat(jsonArray.getJSONObject(i).getString("namaObat"));
                    transaksiObatDetail.setAction(jsonArray.getJSONObject(i).getString("action"));
                    transaksiObatDetail.setFlagDiterima(jsonArray.getJSONObject(i).getString("flagDiterima"));
                    if (!jsonArray.getJSONObject(i).getString("noBatch").isEmpty()){
                        transaksiObatDetail.setNoBatch(new Integer(jsonArray.getJSONObject(i).getString("noBatch")));
                    }
                    if (!jsonArray.getJSONObject(i).getString("bijiPerLembar").isEmpty()){
                        transaksiObatDetail.setBijiPerLembar(new BigInteger(jsonArray.getJSONObject(i).getString("bijiPerLembar")));
                    }
                    transaksiObatDetail.setBranchId(jsonArray.getJSONObject(i).getString("branchId"));
                    if (!jsonArray.getJSONObject(i).getString("averageHargaBiji").isEmpty()){
                        transaksiObatDetail.setAverageHargaBiji(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaBiji")));
                    }
                    if (!jsonArray.getJSONObject(i).getString("averageHargaLembar").isEmpty()){
                        transaksiObatDetail.setAverageHargaLembar(new BigDecimal(jsonArray.getJSONObject(i).getString("averageHargaLembar")));
                    }
                    transaksiObatDetail.setIdApprovalObat(jsonArray.getJSONObject(i).getString("idApprovalObat"));
                    if (!jsonArray.getJSONObject(i).getString("qty").isEmpty()){
                        transaksiObatDetail.setQty(new BigInteger(jsonArray.getJSONObject(i).getString("qty")));
                    }
                    if (!jsonArray.getJSONObject(i).getString("qtyApprove").isEmpty()){
                        transaksiObatDetail.setQtyApprove(new BigInteger(jsonArray.getJSONObject(i).getString("qtyApprove")));
                    }
                    transaksiObatDetail.setStatus(jsonArray.getJSONObject(i).getString("status"));

                    jsonObatDetail.add(transaksiObatDetail);
                }

            } catch (JSONException e) {
                logger.error("[PurchaseOrderController.create] Error, parse json " + e.getMessage());

            }
        }

        List<PermintaanObatPoli> result = new ArrayList<>();

        PermintaanObatPoli bean = new PermintaanObatPoli();
        bean.setTipePermintaan(tipePermintaan);
        bean.setIdPelayanan(idPelayanan);
        bean.setBranchId(branchId);
        bean.setFlag(flag);
        bean.setIsMobile("Y");

        boolean isPoli = false;
        if (flagPoli != null) {
            if (flagPoli.equalsIgnoreCase("Y")){
                isPoli = true;
            }
        }

        try{
            result = obatPoliBoProxy.getSearchPermintaanObatPoli(bean, isPoli);
        } catch (GeneralBOException e){
            logger.error("[PermintaanObatController.create] Error, get search poli " + e.getMessage());
        }

        if (action.equalsIgnoreCase("getPermintaan")){
            for (PermintaanObatPoli item : result){
                PermintaanObatMobile permintaanObatPoli = new PermintaanObatMobile();
                permintaanObatPoli.setIdPermintaanObatPoli(item.getIdPermintaanObatPoli());
                permintaanObatPoli.setIdApprovalObat(item.getIdApprovalObat());
                permintaanObatPoli.setIdObat(item.getIdObat());
                permintaanObatPoli.setIdPelayanan(item.getIdPelayanan());
//            permintaanObatPoli.setQty(item.getQty().toString());
                permintaanObatPoli.setFlag(item.getFlag());
                permintaanObatPoli.setAction(item.getAction());
                permintaanObatPoli.setLastUpdate(item.getLastUpdate().toString());
                permintaanObatPoli.setLastUpdateWho(item.getLastUpdateWho());
                permintaanObatPoli.setCreatedDate(item.getCreatedDate().toString());
                permintaanObatPoli.setCreatedWho(item.getCreatedWho());
                permintaanObatPoli.setDiterimaFlag(item.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(item.getRetureFlag());
                permintaanObatPoli.setTujuanPelayanan(item.getTujuanPelayanan());
                permintaanObatPoli.setDiterimaFlag(item.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(item.getRetureFlag());
                permintaanObatPoli.setNamaObat(item.getNamaObat());
//            permintaanObatPoli.setQtyGudang(item.getQtyGudang().toString());
                permintaanObatPoli.setNamaPelayanan(item.getNamaPelayanan());
                permintaanObatPoli.setNamaTujuanPelayanan(item.getNamaTujuanPelayanan());
                permintaanObatPoli.setKeterangan(item.getKeterangan());
                permintaanObatPoli.setApprovalFlag(item.getApprovalFlag());
                permintaanObatPoli.setApprovePerson(item.getApprovePerson());
                permintaanObatPoli.setApprovalLastUpdate(item.getApprovalLastUpdate().toString());
                permintaanObatPoli.setApprovalLastUpdateWho(item.getApprovalLastUpdateWho());
                permintaanObatPoli.setStCreatedDate(item.getStCreatedDate());
                permintaanObatPoli.setTipePermintaan(item.getTipePermintaan());
                permintaanObatPoli.setIsRequest(item.getRequest().toString());
                permintaanObatPoli.setJumlahObat(item.getJumlahObat().toString());

                listOfPermintaanObat.add(permintaanObatPoli);
            }
        }

        if (action.equalsIgnoreCase("getListObat")){

            List<TransaksiObatDetail> resultObat = new ArrayList<>();
            List<PermintaanObatPoli> resultPermintaan = new ArrayList<>();

            TransaksiObatDetail beanObat = new TransaksiObatDetail();
            beanObat.setIdApprovalObat(idApproval);
            beanObat.setBranchId(branchId);


            try{
                resultObat = obatPoliBoProxy.getListTransObatDetail(beanObat);
            }catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get obat " + e.getMessage());
            }

            for (TransaksiObatDetail item : resultObat){
                TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();

                transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                transaksiObatMobile.setNamaObat(item.getNamaObat());
                transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                transaksiObatMobile.setIdObat(item.getIdObat());
                transaksiObatMobile.setKeterangan(item.getKeterangan());
//                transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
//                transaksiObatMobile.setQtyBox(item.getQtyBox().toString());
//                transaksiObatMobile.setQtyLembar(item.getQtyLembar().toString());
//                transaksiObatMobile.setQtyBiji(item.getQtyBiji().toString());
//                transaksiObatMobile.setExpDate(item.getExpDate().toString());
                transaksiObatMobile.setQty(item.getQty().toString());
//                transaksiObatMobile.setLembarPerBox(item.getLembarPerBox().toString());
//                transaksiObatMobile.setBijiPerLembar(item.getBijiPerLembar().toString());
//                transaksiObatMobile.setAverageHargaBox(item.getAverageHargaBox().toString());
//                transaksiObatMobile.setAverageHargaLembar(item.getAverageHargaLembar().toString());
//                transaksiObatMobile.setAverageHargaBiji(item.getAverageHargaBiji().toString());
                transaksiObatMobile.setFlagDiterima(item.getFlagDiterima());
                transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                transaksiObatMobile.setIdPabrik(item.getIdPabrik());
                transaksiObatMobile.setMerek(item.getMerek());

                listOfTransaksiObatDetail.add(transaksiObatMobile);
            }
        }

        if (action.equalsIgnoreCase("getEntityObat")){

            List<Obat> resultEntityObat = new ArrayList<>();

            Obat entityObat = new Obat();
            entityObat.setIdObat(idObat);
            entityObat.setNamaObat(namaObat);

            try {
                resultEntityObat = obatBoProxy.getEntityObatByCriteria(entityObat);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatController.create] Error, get entity obat " + e.getMessage());
            }

            for (Obat item: resultEntityObat){
                ObatMobile obatMobile = new ObatMobile();
                obatMobile.setIdBarang(item.getIdBarang());
                obatMobile.setExpiredDate(item.getExpiredDate().toString());
                obatMobile.setQtyBox(item.getQtyBox().toString());
                obatMobile.setQtyLembar(item.getQtyLembar().toString());
                obatMobile.setQtyBiji(item.getQtyBiji().toString());
                obatMobile.setJenisSatuan(item.getJenisSatuan());

                listOfObat.add(obatMobile);
            }
        }

        if  (action.equalsIgnoreCase("getListObatTelahDiterima")){

            List<TransaksiObatDetail> resultTransaksiObatDetail = new ArrayList<>();

            try {
                 resultTransaksiObatDetail = obatPoliBoProxy.getListObatTelahDiterima(idPermintaanObatPoli);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get list obat telah diterima " + e.getMessage());
            }

            if (resultTransaksiObatDetail.size() > 0) {
                for(TransaksiObatDetail item: resultTransaksiObatDetail){
                    TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();
                    transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                    transaksiObatMobile.setIdBarang(item.getIdBarang());
                    transaksiObatMobile.setNamaObat(item.getNamaObat());
                    transaksiObatMobile.setQty(item.getQty().toString());
                    transaksiObatMobile.setQtyApprove(item.getQtyApprove().toString());
                    transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                    transaksiObatMobile.setIdObat(item.getIdObat());

                    listOfTransaksiObatDetail.add(transaksiObatMobile);
                }
            }


        }


        if (action.equalsIgnoreCase("getRequestObat")){
            bean.setIdPermintaanObatPoli(idPermintaanObatPoli);
            bean.setBranchId(branchId);
            bean.setIdPelayanan(idPelayanan);

            try {
                result = obatPoliBoProxy.getListObatDetailRequest(bean);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get Request Obat " + e.getMessage());
            }

            for (PermintaanObatPoli item : result){
                PermintaanObatMobile permintaanObatPoli = new PermintaanObatMobile();
                permintaanObatPoli.setIdPermintaanObatPoli(item.getIdPermintaanObatPoli());
                permintaanObatPoli.setIdApprovalObat(item.getIdApprovalObat());
                permintaanObatPoli.setIdObat(item.getIdObat());
                permintaanObatPoli.setIdPelayanan(item.getIdPelayanan());
                permintaanObatPoli.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
//            permintaanObatPoli.setQty(item.getQty().toString());
                permintaanObatPoli.setQtyApprove(item.getQtyApprove().toString());
                permintaanObatPoli.setFlag(item.getFlag());
                permintaanObatPoli.setAction(item.getAction());
//                permintaanObatPoli.setLastUpdate(item.getLastUpdate().toString());
//                permintaanObatPoli.setLastUpdateWho(item.getLastUpdateWho());
//                permintaanObatPoli.setCreatedDate(item.getCreatedDate().toString());
//                permintaanObatPoli.setCreatedWho(item.getCreatedWho());
                permintaanObatPoli.setDiterimaFlag(item.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(item.getRetureFlag());
                permintaanObatPoli.setTujuanPelayanan(item.getTujuanPelayanan());
                permintaanObatPoli.setDiterimaFlag(item.getDiterimaFlag());
                permintaanObatPoli.setRetureFlag(item.getRetureFlag());
                permintaanObatPoli.setNamaObat(item.getNamaObat());
//            permintaanObatPoli.setQtyGudang(item.getQtyGudang().toString());
                permintaanObatPoli.setNamaPelayanan(item.getNamaPelayanan());
                permintaanObatPoli.setNamaTujuanPelayanan(item.getNamaTujuanPelayanan());
                permintaanObatPoli.setKeterangan(item.getKeterangan());
                permintaanObatPoli.setApprovalFlag(item.getApprovalFlag());
                permintaanObatPoli.setApprovePerson(item.getApprovePerson());
//                permintaanObatPoli.setApprovalLastUpdate(item.getApprovalLastUpdate().toString());
                permintaanObatPoli.setApprovalLastUpdateWho(item.getApprovalLastUpdateWho());
                permintaanObatPoli.setStCreatedDate(item.getStCreatedDate());
                permintaanObatPoli.setTipePermintaan(item.getTipePermintaan());
                permintaanObatPoli.setIsRequest(item.getRequest().toString());
                permintaanObatPoli.setStCreatedDate(item.getStCreatedDate());
                permintaanObatPoli.setIdBatch(item.getIdBatch());
                permintaanObatPoli.setIdBarang(item.getIdBarang());
                permintaanObatPoli.setJenisSatuan(item.getJenisSatuan());
                permintaanObatPoli.setExpiredDate(item.getExpiredDate().toString());

                listOfPermintaanObat.add(permintaanObatPoli);
            }
        }

        if (action.equalsIgnoreCase("updateDiterimaFlag")){
            TransaksiObatBatch batch = new TransaksiObatBatch();
            batch.setId(new BigInteger(idBatch));
            batch.setDiterimaFlag("Y");

            try{
                obatPoliBoProxy.updateDiterimaFlagBatch(batch);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, update flag " + e.getMessage());

            }
        }



        if (action.equalsIgnoreCase("saveVerifikasi")){

            if (jsonObat.size() > 0) {
                try {
                    obatPoliBoProxy.saveVerifikasiObat(jsonObat);
                    model.setMessage("Success");
                } catch (GeneralBOException e){
                    logger.error("[PermintaanObatController.create] Error, save verifikasi " + e.getMessage());
                }
            }
        }

        if (action.equalsIgnoreCase("saveApprove")){

            bean.setIdPermintaanObatPoli(idPermintaanObatPoli);
            bean.setBranchId(branchId);

            try{
                result = obatPoliBoProxy.getSearchPermintaanObatPoli(bean, isPoli);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get search poli " + e.getMessage());
            }

            try{
                obatPoliBoProxy.saveApproveRequest(result.get(0), jsonObatDetail, isPoli);
                model.setMessage("Success");
            } catch (JSONException j){
                logger.error("[PermintaanObatController.create] Error, save verifikasi " + j.getMessage());

            }
        }

        if (action.equalsIgnoreCase("saveApproveDiterima")){
            bean.setIdPermintaanObatPoli(idPermintaanObatPoli);
            bean.setBranchId(branchId);

            try{
                result = obatPoliBoProxy.getSearchPermintaanObatPoli(bean, isPoli);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get search poli " + e.getMessage());
            }
            try{
                obatPoliBoProxy.saveApproveDiterima(result.get(0), jsonObatDetail);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, update flag " + e.getMessage());

            }
        }

        if (action.equalsIgnoreCase("saveReture")){

            bean.setIdPermintaanObatPoli(idPermintaanObatPoli);
            bean.setBranchId(branchId);

            try{
                result = obatPoliBoProxy.getSearchPermintaanObatPoli(bean, isPoli);
                result.get(0).setBranchId(branchId);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get search poli " + e.getMessage());
            }

            try{
                obatPoliBoProxy.saveReture(result.get(0), jsonObatDetail);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, save reture " + e.getMessage());
            } catch (JSONException e){
                logger.error("[PermintaanObatController.create] Error, save reture " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveApproveReture")){
            bean.setIdPermintaanObatPoli(idPermintaanObatPoli);
            bean.setBranchId(branchId);

            try{
                result = obatPoliBoProxy.getSearchPermintaanObatPoli(bean, isPoli);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get search poli " + e.getMessage());
            }
            try {
                obatPoliBoProxy.saveApproveReture(result.get(0), false);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, save approve reture " + e.getMessage());
            }

        }

        if (action.equalsIgnoreCase("getHeaderObatById")) {
            ImSimrsHeaderObatEntity imSimrsHeaderObatEntity = null;
            try {
              imSimrsHeaderObatEntity = obatBoProxy.getHeaderObatById(idObat);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatController.create] Error, save approve reture " + e.getMessage());
            }

            if (imSimrsHeaderObatEntity != null) {
                model.setIdObat(imSimrsHeaderObatEntity.getIdObat());
                model.setIdJenisObat(imSimrsHeaderObatEntity.getIdSubJenis());
            }

        }

        if (action.equalsIgnoreCase("getComboParameterWaktu")) {
            List<KeteranganObat> keteranganObatList = new ArrayList<>();
            try {
               keteranganObatList = parameterKeteranganObatBoProxy.getParameterKeteranganWaktu(idJenis);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatController.create] Error, save approve reture " + e.getMessage());
            }

            if (keteranganObatList.size() > 0) {
                for (KeteranganObat item : keteranganObatList) {
                    PermintaanObatMobile permintaanObatMobile = new PermintaanObatMobile();
                    permintaanObatMobile.setIdWaktu(item.getId());
                    permintaanObatMobile.setNamaWaktu(item.getKeterangan());

                    listOfPermintaanObat.add(permintaanObatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getComboParameterObat")) {
            List<ParameterKeteranganObat> parameterKeteranganObatList = new ArrayList<>();
            try {
               parameterKeteranganObatList = parameterKeteranganObatBoProxy.getParameterKeterangan(idJenis);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatController.create] Error, save approve reture " + e.getMessage());
            }

            if (parameterKeteranganObatList.size() > 0) {
                for (ParameterKeteranganObat item : parameterKeteranganObatList) {
                    PermintaanObatMobile permintaanObatMobile = new PermintaanObatMobile();
                    permintaanObatMobile.setIdParam(item.getId());
                    permintaanObatMobile.setNamaParam(item.getNama());

                    listOfPermintaanObat.add(permintaanObatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getComboKeteranganObat")) {
            List<KeteranganObat> keteranganObatList = new ArrayList<>();
            try {
               keteranganObatList = keteranganObatBoProxy.getKeteranganObat(idParam);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, save approve reture " + e.getMessage());
            }

            if (keteranganObatList.size() > 0) {
                for (KeteranganObat item : keteranganObatList) {
                    PermintaanObatMobile permintaanObatMobile = new PermintaanObatMobile();
                    permintaanObatMobile.setIdKeterangan(item.getId());
                    permintaanObatMobile.setNamaKeterangan(item.getKeterangan());

                    listOfPermintaanObat.add(permintaanObatMobile);
                }
            }
        }

        logger.info("[PermintaanObatContoller.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();

    }
}
