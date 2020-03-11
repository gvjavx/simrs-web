package com.neurix.simrs.mobileapi;

import com.google.gson.Gson;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.*;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author gondok
 * Friday, 24/01/20 11:08
 */
public class CheckupController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CheckupController.class);
    private CheckupMobile model = new CheckupMobile();
    private Collection<CheckupMobile> listOfCheckup = new ArrayList<>();
    private Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup = new ArrayList<>();
    private Collection<ObatPoliMobile> listOfObatPoli = new ArrayList<>();
    private Collection<PermintaanResepMobile> listOfPermintaanResep = new ArrayList<>();
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private ObatPoliBo obatPoliBoProxy;
    private PermintaanResepBo permintaanResepBoProxy;

    private String idDetailCheckup;
    private String idPasien;
    private String nama;
    private String idPoli;
    private String idStatusPasien;
    private String tglMasuk;
    private String branchId;
    private String action;

    private File fileUploadTtd;
    private String fileNameTtd;

    private String idPelayanan;
    private String idObat;

    private String jsonResep;

    private String tujuanPelayanan;
    private String idDokter;

    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PermintaanResepBo getPermintaanResepBoProxy() {
        return permintaanResepBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getTujuanPelayanan() {
        return tujuanPelayanan;
    }

    public void setTujuanPelayanan(String tujuanPelayanan) {
        this.tujuanPelayanan = tujuanPelayanan;
    }

    public String getJsonResep() {
        return jsonResep;
    }



    public void setJsonResep(String jsonResep) {
        this.jsonResep = jsonResep;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public Collection<ObatPoliMobile> getListOfObatPoli() {
        return listOfObatPoli;
    }

    public void setListOfObatPoli(Collection<ObatPoliMobile> listOfObatPoli) {
        this.listOfObatPoli = listOfObatPoli;
    }

    public ObatPoliBo getObatPoliBoProxy() {
        return obatPoliBoProxy;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    public String getFileNameTtd() {
        return fileNameTtd;
    }

    public void setFileNameTtd(String fileNameTtd) {
        this.fileNameTtd = fileNameTtd;
    }

    public Collection<HeaderDetailCheckupMobile> getListOfHeaderCheckup() {
        return listOfHeaderCheckup;
    }

    public void setListOfHeaderCheckup(Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup) {
        this.listOfHeaderCheckup = listOfHeaderCheckup;
    }

    public File getFileUploadTtd() {
        return fileUploadTtd;
    }

    public void setFileUploadTtd(File fileUploadTtd) {
        this.fileUploadTtd = fileUploadTtd;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPoli() {
        return idPoli;
    }

    public void setIdPoli(String idPoli) {
        this.idPoli = idPoli;
    }

    public String getIdStatusPasien() {
        return idStatusPasien;
    }

    public void setIdStatusPasien(String idStatusPasien) {
        this.idStatusPasien = idStatusPasien;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(CheckupMobile model) {
        this.model = model;
    }

    public Collection<CheckupMobile> getListOfCheckup() {
        return listOfCheckup;
    }

    public void setListOfCheckup(Collection<CheckupMobile> listOfCheckup) {
        this.listOfCheckup = listOfCheckup;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action) {
            case "search":
                return listOfCheckup;
            case "dataPasien":
                return listOfHeaderCheckup;
            case "getObatPoli":
                return listOfObatPoli;
            case "getObatPoliGroup":
                return listOfObatPoli;
            case "getPermintaanResep":
                return listOfPermintaanResep;
            default:
                return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[CheckupController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());


        if (action.equalsIgnoreCase("search")) {
            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdPasien(idPasien);
            bean.setNamaPasien(nama);
            bean.setIdPelayanan(idPoli);
            bean.setStDateFrom(tglMasuk);
            bean.setStDateTo(tglMasuk);
            bean.setBranchId(branchId);
            bean.setStatusPeriksa(idStatusPasien);
            bean.setFlag("Y");

            try {
               result = checkupDetailBoProxy.getSearchRawatJalan(bean);
            } catch (GeneralBOException e) {
                logger.error("CheckupController.create] Error when get rawat jalan",e);
            }

            if (result != null & result.size() > 0) {
                for (HeaderDetailCheckup item : result) {
                    CheckupMobile checkupMobile = new CheckupMobile();
                    checkupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    checkupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    checkupMobile.setJenisPeriksaPasien(item.getJenisPeriksaPasien());
                    checkupMobile.setNoCheckup(item.getNoCheckup());
                    checkupMobile.setIdPasien(item.getIdPasien());
                    checkupMobile.setNamaPasien(item.getNamaPasien());
                    checkupMobile.setAlamat(item.getAlamat());
                    checkupMobile.setStatusPeriksa(item.getStatusPeriksa());
                    checkupMobile.setStatusBayar(item.getStatusBayar());

                    listOfCheckup.add(checkupMobile);
                }
            }
        } else if (action.equalsIgnoreCase("dataPasien")){

            HeaderCheckup result = new HeaderCheckup();

            try {
               result = checkupBoProxy.getDataDetailPasien(idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get rawat jalan",e);

            }

            if (result != null) {
               HeaderDetailCheckupMobile headerDetailCheckupMobile = new HeaderDetailCheckupMobile();
               headerDetailCheckupMobile.setNoCheckup(result.getNoCheckup());
               headerDetailCheckupMobile.setIdPasien(result.getIdPasien());
               headerDetailCheckupMobile.setNamaPasien(result.getNama());
               headerDetailCheckupMobile.setJenisKelamin(result.getJenisKelamin());
               headerDetailCheckupMobile.setNik(result.getNoKtp());
               headerDetailCheckupMobile.setTempatLahir(result.getTempatLahir());
               headerDetailCheckupMobile.setIdJenisPeriksaPasien(result.getIdJenisPeriksaPasien());
               headerDetailCheckupMobile.setIdDetailCheckup(result.getIdDetailCheckup());
               headerDetailCheckupMobile.setNamaPelayanan(result.getNamaPelayanan());
               headerDetailCheckupMobile.setNoSep(result.getNoSep());
               headerDetailCheckupMobile.setTglLahir(result.getStTglLahir());
               headerDetailCheckupMobile.setUrlTtd(result.getUrlTtd());
               headerDetailCheckupMobile.setIdDokter(result.getIdDokter());
               headerDetailCheckupMobile.setIdPelayanan(result.getIdPelayanan());


               listOfHeaderCheckup.add(headerDetailCheckupMobile);
            }
        } else if (action.equalsIgnoreCase("uploadTtd")){

            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdDetailCheckup(idDetailCheckup);

            try {
                result = checkupDetailBoProxy.getByCriteria(bean);

            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get checkup detail by criteria",e);
            }

            if (result != null && result.size() > 0){
                if (fileUploadTtd != null) {
                    if(fileUploadTtd.length() > 0 && fileUploadTtd.length() <= 15728640) {
                        Random random = new Random( System.currentTimeMillis() );
                        String fileNamePhoto = "TTD_" + random.nextInt(999) + "_" + idDetailCheckup + CommonConstant.IMAGE_TYPE;
                        result.get(0).setUrlTtd(fileNamePhoto);
                        File fileCreate = new File(CommonConstant.RESOURCE_IMAGE_TTD, fileNamePhoto);
                        try {
                            FileUtils.copyFile(fileUploadTtd, fileCreate);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    try {
                        checkupDetailBoProxy.saveTtd(result.get(0));
                        model.setMessage("Success");
                    } catch (GeneralBOException e){
                        logger.error("CheckupController.create] Error when save edit",e);
                    }
                }

            }

        } else if (action.equalsIgnoreCase("getObatPoli")) {
            List<ObatPoli> result = new ArrayList<>();

            ObatPoli bean = new ObatPoli();
            bean.setIdObat(idObat);
            bean.setIdPelayanan(idPelayanan);
            bean.setBranchId(branchId);

            try {
                result = obatPoliBoProxy.getObatPoliByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli",e);
            }

            if (result != null && result.size() > 0){
                for (ObatPoli item : result){
                    ObatPoliMobile obatPoliMobile  = new ObatPoliMobile();
                    obatPoliMobile.setIdObat(item.getIdObat());
                    obatPoliMobile.setIdPelayanan(item.getIdPelayanan());
                    obatPoliMobile.setFlag(item.getFlag());
                    obatPoliMobile.setQtyBox(item.getQtyBox() != null ? item.getQtyBox().toString() : "0");
                    obatPoliMobile.setQtyLembar(item.getQtyLembar() != null ? item.getQtyLembar().toString() : "0");
                    obatPoliMobile.setQtyBiji(item.getQtyBiji() != null ? item.getQtyBiji().toString(): "0");
                    obatPoliMobile.setQty(item.getQty() != null ? item.getQty().toString() : "0");
                    obatPoliMobile.setAction(item.getAction());
                    obatPoliMobile.setCreatedDate(item.getCreatedDate() != null ? item.getCreatedDate().toLocaleString() : "");
                    obatPoliMobile.setCreatedWho(item.getCreatedWho());
                    obatPoliMobile.setLastUpdate(item.getLastUpdate() != null ? item.getLastUpdate().toLocaleString() : "");
                    obatPoliMobile.setLastUpdateWho(item.getLastUpdateWho());
                    obatPoliMobile.setBranchId(item.getBranchId());
                    obatPoliMobile.setIdPabrik(item.getIdPabrik());
                    obatPoliMobile.setExpiredDate(item.getExpiredDate() != null ? item.getExpiredDate().toLocaleString() : "");
                    obatPoliMobile.setIdBarang(item.getIdBarang());
                    obatPoliMobile.setNamaObat(item.getNamaObat());
                    obatPoliMobile.setLembarPerBox(item.getLembarPerBox() != null ? item.getLembarPerBox().toString() : "0");
                    obatPoliMobile.setBijiPerLembar(item.getBijiPerLembar() != null ? item.getBijiPerLembar().toString() : "0");
                    obatPoliMobile.setFlagKronis(item.getFlagKronis());

                    listOfObatPoli.add(obatPoliMobile);
                }
            }
        } else if (action.equalsIgnoreCase("getObatPoliGroup")){
            List<ObatPoli> result = new ArrayList<>();

            try{
               result = obatPoliBoProxy.getListObatPoliGroup(idPelayanan, branchId);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli group",e);
            }

            if (result != null && result.size() > 0) {
                for (ObatPoli item : result){
                    ObatPoliMobile obatPoliMobile = new ObatPoliMobile();
                    obatPoliMobile.setIdObat(item.getIdObat());
                    obatPoliMobile.setNamaObat(item.getNamaObat());
                    obatPoliMobile.setLembarPerBox(item.getLembarPerBox() != null ? item.getLembarPerBox().toString() : "");
                    obatPoliMobile.setBijiPerLembar(item.getBijiPerLembar() != null ? item.getBijiPerLembar().toString() : "");
                    obatPoliMobile.setFlagKronis(item.getFlagKronis());
                    obatPoliMobile.setQtyBox(item.getQtyBox() != null ? item.getQtyBox().toString() : "");
                    obatPoliMobile.setQtyLembar(item.getQtyLembar() != null ? item.getQtyLembar().toString() : "");
                    obatPoliMobile.setQtyBiji(item.getQtyBiji() != null ? item.getQtyLembar().toString() : "");

                    listOfObatPoli.add(obatPoliMobile);
                }
            }

        } else if (action.equalsIgnoreCase("saveAddResep")){
            PermintaanResep bean = new PermintaanResep();
            bean.setIdPelayanan(idPelayanan);
            bean.setBranchId(branchId);
            bean.setTujuanPelayanan(tujuanPelayanan);
            bean.setIdDetailCheckup(idDetailCheckup);
            bean.setIdPasien(idPasien);
            bean.setIdDokter(idDokter);
            bean.setCreatedDate(now);
            bean.setCreatedWho(username);
            bean.setLastUpdateWho(username);
            bean.setLastUpdate(now);

            List<TransaksiObatDetail> list = new ArrayList<>();
            JSONArray jsonArray;

            if (jsonResep != null && !jsonResep.equalsIgnoreCase("")){
                try{
                  jsonArray = (net.sf.json.JSONArray) JSONSerializer.toJSON(jsonResep);
                  for (int i = 0; i < jsonArray.size(); i++){
                      TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                      transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                      transaksiObatDetail.setQty(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                      transaksiObatDetail.setQtyApprove(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                      transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                      transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                      transaksiObatDetail.setFlagRacik(jsonArray.getJSONObject(i).getString("flagRacik"));
                      transaksiObatDetail.setHariKronis(!jsonArray.getJSONObject(i).getString("hariKronis").equalsIgnoreCase("") ? Integer.valueOf(jsonArray.getJSONObject(i).getString("hariKronis")) : null);


                      list.add(transaksiObatDetail);
                  }

                } catch (JSONException e) {
                    logger.error("[CheckupController.create] Error, get json resep " + e.getMessage());
                }
            }

            try {
                permintaanResepBoProxy.saveAdd(bean, list);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli group",e);
            }
        } else if (action.equalsIgnoreCase("getPermintaanResep")){
            List<PermintaanResep> result = new ArrayList<>();

            PermintaanResep bean = new PermintaanResep();
            bean.setIdDetailCheckup(idDetailCheckup);

            try{
                result = permintaanResepBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e ){
                logger.error("CheckupController.create] Error when get permintaan resep",e);

            }

            if (result.size() > 0 && result != null){
                for (PermintaanResep item : result){
                    PermintaanResepMobile permintaanResepMobile = new PermintaanResepMobile();
                    permintaanResepMobile.setIdPermintaanResep(item.getIdPermintaanResep());
                    permintaanResepMobile.setIdPasien(item.getIdPasien());
                    permintaanResepMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    permintaanResepMobile.setIdApprovalObat(item.getIdApprovalObat());
                    permintaanResepMobile.setKeterangan(item.getKeterangan());
                    permintaanResepMobile.setIdDokter(item.getIdDokter());
                    permintaanResepMobile.setTujuanPelayanan(item.getTujuanPelayanan());
                    permintaanResepMobile.setCreatedDate(CommonUtil.convertDateToString(item.getCreatedDate()));
                    permintaanResepMobile.setLastUpdate(CommonUtil.convertDateToString(item.getLastUpdate()));

                    listOfPermintaanResep.add(permintaanResepMobile);
                }
            }


        }

        logger.info("[CheckupController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
