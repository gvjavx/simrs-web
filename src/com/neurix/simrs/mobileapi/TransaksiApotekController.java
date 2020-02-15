package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.mobileapi.model.ObatMobile;
import com.neurix.simrs.mobileapi.model.PermintaanObatMobile;
import com.neurix.simrs.mobileapi.model.PermintaanResepMobile;
import com.neurix.simrs.mobileapi.model.TransaksiObatMobile;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 05/02/20 15:12
 */
public class TransaksiApotekController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(TransaksiApotekController.class);
    private PermintaanResepMobile model = new PermintaanResepMobile();
    private Collection<PermintaanResepMobile> listOfPermintaanResep = new ArrayList<>();
    private Collection<TransaksiObatMobile> listOfTransaksiObat = new ArrayList<>();
    private Collection<ObatMobile> listOfObat = new ArrayList<>();

    private TransaksiObatBo transaksiObatBoProxy;
    private ObatPoliBo obatPoliBoProxy;
    private ObatBo obatBoProxy;

    private String idTransaksiObatDetail;

    private String idPermintaanResep;
    private String noCheckupDetail;
    private String nama;
    private String status;
    private String branchId;

    private String isUmum;

    private String idApprovalObat;
    private String idPelayanan;

    private String idPabrik;
    private String jenisSatuan;

    private String totalHarga;
    private String totalBayar;
    private String kembalian;

    private String idObat;

    private String jsonObatVerifikasi;
    private String jsonTransaksiObatDetail;

    private String username;

    private String action;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public ObatBo getObatBoProxy() {
        return obatBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }



    public String getIsUmum() {
        return isUmum;
    }

    public void setIsUmum(String isUmum) {
        this.isUmum = isUmum;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getKembalian() {
        return kembalian;
    }

    public void setKembalian(String kembalian) {
        this.kembalian = kembalian;
    }

    public String getJenisSatuan() {
        return jenisSatuan;
    }

    public void setJenisSatuan(String jenisSatuan) {
        this.jenisSatuan = jenisSatuan;
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

    public String getJsonTransaksiObatDetail() {
        return jsonTransaksiObatDetail;
    }

    public void setJsonTransaksiObatDetail(String jsonTransaksiObatDetail) {
        this.jsonTransaksiObatDetail = jsonTransaksiObatDetail;
    }

    public ObatPoliBo getObatPoliBoProxy() {
        return obatPoliBoProxy;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    public String getIdPabrik() {
        return idPabrik;
    }

    public void setIdPabrik(String idPabrik) {
        this.idPabrik = idPabrik;
    }

    public Collection<TransaksiObatMobile> getListOfTransaksiObat() {
        return listOfTransaksiObat;
    }

    public void setListOfTransaksiObat(Collection<TransaksiObatMobile> listOfTransaksiObat) {
        this.listOfTransaksiObat = listOfTransaksiObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdApprovalObat() {
        return idApprovalObat;
    }

    public void setIdApprovalObat(String idApprovalObat) {
        this.idApprovalObat = idApprovalObat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getIdTransaksiObatDetail() {
        return idTransaksiObatDetail;
    }

    public void setIdTransaksiObatDetail(String idTransaksiObatDetail) {
        this.idTransaksiObatDetail = idTransaksiObatDetail;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPermintaanResep() {
        return idPermintaanResep;
    }

    public void setIdPermintaanResep(String idPermintaanResep) {
        this.idPermintaanResep = idPermintaanResep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoCheckupDetail() {
        return noCheckupDetail;
    }

    public void setNoCheckupDetail(String noCheckupDetail) {
        this.noCheckupDetail = noCheckupDetail;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(PermintaanResepMobile model) {
        this.model = model;
    }

    public Collection<PermintaanResepMobile> getListOfPermintaanResep() {
        return listOfPermintaanResep;
    }

    public void setListOfPermintaanResep(Collection<PermintaanResepMobile> listOfPermintaanResep) {
        this.listOfPermintaanResep = listOfPermintaanResep;
    }

    public TransaksiObatBo getTransaksiObatBoProxy() {
        return transaksiObatBoProxy;
    }

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action){
            case "getListResep" :
                return listOfPermintaanResep;
            case "getSearchTransaksiByCriteria" :
                return listOfTransaksiObat;
            case "getObatPoliByCriteria" :
                return listOfObat;
            case "getListObatPoliGroup":
                return listOfObat;
            default:
                return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[TransaksiApotekController.create] start process POST / <<<");

        List<TransaksiObatDetail> jsonObatDetail = new ArrayList<>();
        List<Obat> jsonObat = new ArrayList<>();
        JSONArray jsonArray;

        Timestamp time = new Timestamp(System.currentTimeMillis());



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

        PermintaanResep bean = new PermintaanResep();
        bean.setIdPermintaanResep(idPermintaanResep);
        bean.setNamaPasien(nama);
        bean.setBranchId(branchId);
        bean.setNoCheckup(noCheckupDetail);
        bean.setStatus(status);
        bean.setIsUmum(isUmum);


        if (action.equalsIgnoreCase("getListResep")){

            List<PermintaanResep> result = new ArrayList<>();

            try {
               result = transaksiObatBoProxy.getListResepPasien(bean);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiApotekController.create] Error, get List Resep " + e.getMessage());
            }

            if (result.size()  > 0) {
                for (PermintaanResep item : result){
                    PermintaanResepMobile permintaanResepMobile = new PermintaanResepMobile();
                    permintaanResepMobile.setIdPermintaanResep(item.getIdPermintaanResep());
                    permintaanResepMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    permintaanResepMobile.setNamaPasien(item.getNamaPasien());
                    permintaanResepMobile.setStatus(item.getStatus());
                    permintaanResepMobile.setIdApprovalObat(item.getIdApprovalObat());
                    permintaanResepMobile.setFlag(item.getFlag());

                    listOfPermintaanResep.add(permintaanResepMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getSearchTransaksiByCriteria")){

            List<TransaksiObatDetail> resultTransaksi = new ArrayList<>();

            TransaksiObatDetail beanTransaksi = new TransaksiObatDetail();
            beanTransaksi.setIdApprovalObat(idApprovalObat);
            beanTransaksi.setBranchId(branchId);
            beanTransaksi.setFlag("Y");
            beanTransaksi.setIdPelayanan(idPelayanan);

            try {
                resultTransaksi = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(beanTransaksi);

            } catch (GeneralBOException e){
                logger.error("[TransaksiApotekController.create] Error, get search transaksi " + e.getMessage());
            }
            for (TransaksiObatDetail item : resultTransaksi){
                TransaksiObatMobile transaksiObatMobile = new TransaksiObatMobile();
                transaksiObatMobile.setIdTransaksiObatDetail(item.getIdTransaksiObatDetail());
                transaksiObatMobile.setIdObat(item.getIdObat());
                transaksiObatMobile.setIdApprovalObat(item.getIdApprovalObat());
                transaksiObatMobile.setQty(item.getQty().toString());
                transaksiObatMobile.setJenisSatuan(item.getJenisSatuan());
                transaksiObatMobile.setNamaObat(item.getNamaObat());
                transaksiObatMobile.setHarga(item.getHarga().toString());
                transaksiObatMobile.setIdPabrik(item.getIdPabrik());

                listOfTransaksiObat.add(transaksiObatMobile);
            }
        }

        if (action.equalsIgnoreCase("getObatPoliByCriteria")) {

            List<ObatPoli> result = new ArrayList<>();

            ObatPoli beanObatPoli = new ObatPoli();
            beanObatPoli.setIdPabrik(idPabrik);
            beanObatPoli.setIdPelayanan(idPelayanan);
            beanObatPoli.setBranchId(branchId);

            try {
               result = obatPoliBoProxy.getObatPoliByCriteria(beanObatPoli);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiApotekController.create] Error, get search transaksi " + e.getMessage());
            }

            for (ObatPoli item : result){
                ObatMobile obat = new ObatMobile();
                obat.setIdObat(item.getIdObat());
                obat.setFlag(item.getFlag());
                obat.setQtyBox(item.getQtyBox().toString());
                obat.setQtyLembar(item.getQtyLembar().toString());
                obat.setQtyBiji(item.getQtyBiji().toString());
//                obat.setQty(item.getQty().toString());
                obat.setAction(item.getAction());
                obat.setBranchId(item.getBranchId());
                obat.setIdBarang(item.getIdBarang());
                obat.setExpiredDate(item.getExpiredDate().toString());
                obat.setIdPabrik(item.getIdPabrik());
                obat.setNamaObat(item.getNamaObat());
                obat.setLembarPerBox(item.getLembarPerBox().toString());
                obat.setBijiPerLembar(item.getBijiPerLembar().toString());

                listOfObat.add(obat);
            }
        }

        if (action.equalsIgnoreCase("getListObatPoliGroup")){
            List<ObatPoli> result = new ArrayList<>();

            try {
                result = obatPoliBoProxy.getListObatPoliGroup(idPelayanan, branchId);
            } catch (GeneralBOException e){
                logger.error("[TransaksiApotekController.create] Error, get list obat by jenis obat " + e.getMessage());
            }

            for (ObatPoli item : result){
                ObatMobile obat = new ObatMobile();
                obat.setIdObat(item.getIdObat());
                obat.setFlag(item.getFlag());
                if  (item.getQtyBox() != null){
                    obat.setQtyBox(item.getQtyBox().toString());
                }
                if (item.getQtyLembar() != null) {
                    obat.setQtyLembar(item.getQtyLembar().toString());
                }
                if (item.getQtyBiji() != null){
                    obat.setQtyBiji(item.getQtyBiji().toString());
                }
//                obat.setQty(item.getQty().toString());
                obat.setAction(item.getAction());
                obat.setBranchId(item.getBranchId());
                obat.setIdBarang(item.getIdBarang());
//                obat.setExpiredDate(item.getExpiredDate().toString());
                obat.setIdPabrik(item.getIdPabrik());
                obat.setNamaObat(item.getNamaObat());
//                obat.setLembarPerBox(item.getLembarPerBox().toString());
//                obat.setBijiPerLembar(item.getBijiPerLembar().toString());

                listOfObat.add(obat);
            }

        }

        if (action.equalsIgnoreCase("saveVerifikasiObat")){


            List<MtSimrsTransaksiObatDetailBatchEntity> batchEntities = new ArrayList<>();

            if (jsonObat != null){
                for (Obat item : jsonObat){
                    MtSimrsTransaksiObatDetailBatchEntity batchEntity = new MtSimrsTransaksiObatDetailBatchEntity();
                    batchEntity.setIdTransaksiObatDetail(idTransaksiObatDetail);
                    batchEntity.setIdBarang(item.getIdBarang());
                    batchEntity.setExpiredDate(item.getExpiredDate());
                    batchEntity.setQtyApprove(item.getQtyApprove());
                    batchEntity.setJenisSatuan(item.getJenisSatuan());
                    batchEntity.setFlag("Y");
                    batchEntity.setFlag("C");
                    batchEntity.setLastUpdate(time);
                    batchEntity.setCreatedDate(time);
                    batchEntity.setCreatedWho(username);
                    batchEntity.setLastUpdateWho(username);

                    batchEntities.add(batchEntity);
                }
            }

            try {
                transaksiObatBoProxy.saveVerifikasiObat(batchEntities);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[TransaksiApotekController.create] Error, save verifikasi obat " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("saveApproveResepPoli")){

            TransaksiObatDetail beanTransaksiObat = new TransaksiObatDetail();
            beanTransaksiObat.setIdApprovalObat(idApprovalObat);
            beanTransaksiObat.setLastUpdateWho(username);
            beanTransaksiObat.setCreatedWho(username);
            beanTransaksiObat.setCreatedDate(time);
            beanTransaksiObat.setLastUpdate(time);

            try {
                transaksiObatBoProxy.saveApproveResepPoli(beanTransaksiObat);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[TransaksiApotekController.create] Error, save approve resep poli " + e.getMessage());
            }

        }

        if  (action.equalsIgnoreCase("saveAdd")){

            List<TransaksiObatDetail> resultTransaksi = new ArrayList<>();

            TransaksiObatDetail beanTransaksiObat = new TransaksiObatDetail();

            beanTransaksiObat.setIdTransaksiObatDetail(idTransaksiObatDetail);
            beanTransaksiObat.setIdApprovalObat(idApprovalObat);

            beanTransaksiObat.setBranchId(branchId);

            try{
                resultTransaksi = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(beanTransaksiObat);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatController.create] Error, get transaksi obat detail " + e.getMessage());
            }

            resultTransaksi.get(0).setTotalBayar(new BigInteger("0"));
            resultTransaksi.get(0).setTotalHarga(new BigInteger("0"));
            resultTransaksi.get(0).setKembalian(new BigInteger("0"));
            resultTransaksi.get(0).setNominal(new BigInteger("0"));
            resultTransaksi.get(0).setIdPermintaanResep(idPermintaanResep);

            if (jsonObatDetail != null) {
                try{
                    transaksiObatBoProxy.saveAdd(resultTransaksi.get(0), jsonObatDetail, null);
                    model.setMessage("Success");
                } catch (GeneralBOException e){
                    logger.error("[TransaksiApotekController.create] Error, save add " + e.getMessage());
                }

            }

        }

        if  (action.equalsIgnoreCase("saveListObatPembelian")) {

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdApprovalObat(idApprovalObat);
            transaksiObatDetail.setBranchId(branchId);
            transaksiObatDetail.setIdPelayanan(idPelayanan);
            transaksiObatDetail.setCreatedDate(time);
            transaksiObatDetail.setLastUpdate(time);

            CheckObatResponse response;

            try {
               response = transaksiObatBoProxy.saveListObatPembelian(transaksiObatDetail, jsonObatDetail);
               model.setMessage(response.getMessage());
            } catch (GeneralBOException e){
                logger.error("[TransaksiApotekController.create] Error, save list obat pembelian " + e.getMessage());
            }
        }

        logger.info("[TransaksiApotekController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
