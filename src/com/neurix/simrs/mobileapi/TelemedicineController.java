package com.neurix.simrs.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.mobileapi.model.ResepOnlineMobile;
import com.neurix.simrs.mobileapi.model.TelemedicineMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.antriantelemedic.model.StatusAntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author gondok
 * Wednesday, 10/06/20 11:25
 */
public class TelemedicineController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(TelemedicineController.class);
    private TelemedicineMobile model = new TelemedicineMobile();
    private Collection<TelemedicineMobile> listOfTelemedic;
    private TelemedicBo telemedicBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private ResepOnlineBo resepOnlineBoProxy;
    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;

    private String action;

    private String idPelayanan;
    private String tujuanPelayanan;
    private String idDokter;
    private String status;
    private String branchId;
    private String kodeBank;
    private String keluhan;

    private String idTele;
    private String idPembayaranOnline;
    private String idPasien;
    private String flagResep;
    private String flagEresep;
    private File fileUploadResep;

    private String jsonObat;
    private File fileUploadTtd;

    private boolean isResep;

    public String getFlagEresep() {
        return flagEresep;
    }

    public void setFlagEresep(String flagEresep) {
        this.flagEresep = flagEresep;
    }

    public File getFileUploadResep() {
        return fileUploadResep;
    }

    public void setFileUploadResep(File fileUploadResep) {
        this.fileUploadResep = fileUploadResep;
    }

    public String getTujuanPelayanan() {
        return tujuanPelayanan;
    }

    public void setTujuanPelayanan(String tujuanPelayanan) {
        this.tujuanPelayanan = tujuanPelayanan;
    }

    public VerifikatorPembayaranBo getVerifikatorPembayaranBoProxy() {
        return verifikatorPembayaranBoProxy;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public boolean isResep() {
        return isResep;
    }

    public void setResep(boolean resep) {
        isResep = resep;
    }

    public String getFlagResep() {
        return flagResep;
    }

    public void setFlagResep(String flagResep) {
        this.flagResep = flagResep;
    }

    public File getFileUploadTtd() {
        return fileUploadTtd;
    }

    public void setFileUploadTtd(File fileUploadTtd) {
        this.fileUploadTtd = fileUploadTtd;
    }

    public ResepOnlineBo getResepOnlineBoProxy() {
        return resepOnlineBoProxy;
    }

    public void setResepOnlineBoProxy(ResepOnlineBo resepOnlineBoProxy) {
        this.resepOnlineBoProxy = resepOnlineBoProxy;
    }

    public String getIdPembayaranOnline() {
        return idPembayaranOnline;
    }

    public void setIdPembayaranOnline(String idPembayaranOnline) {
        this.idPembayaranOnline = idPembayaranOnline;
    }

    public String getJsonObat() {
        return jsonObat;
    }

    public void setJsonObat(String jsonObat) {
        this.jsonObat = jsonObat;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }


    public String getIdTele() {
        return idTele;
    }

    public void setIdTele(String idTele) {
        this.idTele = idTele;
    }

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HttpHeaders create() {
        logger.info("[TelemedicineController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        StatusAntrianTelemedic status = new StatusAntrianTelemedic();

        if (action.equalsIgnoreCase("saveAdd")) {
            ItSimrsAntrianTelemedicEntity bean = new ItSimrsAntrianTelemedicEntity();
            bean.setIdDokter(model.getIdDokter());
            bean.setIdJenisPeriksaPasien(model.getIdJenisPeriksaPasien());
            bean.setIdPelayanan(model.getIdPelayanan());
            bean.setIdPasien(model.getIdPasien());
            bean.setIdAsuransi(model.getIdAsuransi());
            bean.setFlagResep(model.getFlagResep());
            bean.setNoKartu(model.getNoKartu());
            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setCreatedWho(model.getIdPasien());
            bean.setLastUpdateWho(model.getIdPasien());
            bean.setKeluhan(model.getKeluhan());
            bean.setAlamat(model.getAlamat());
            bean.setLat(model.getLat());
            bean.setLon(model.getLon());
            bean.setJenisPengambilan(model.getJenisPengambilan());
            bean.setNoTelp(model.getNoTelp());
            bean.setFlagEresep(model.getFlagEresep());

            String fileName = "";
            if (fileUploadResep != null) {
                fileName = model.getIdPasien()+".jpeg";
                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+ CommonConstant.RESOURCE_PATH_RESEP, fileName);
                try {
                    FileUtils.copyFile(fileUploadTtd, fileCreate);
                    bean.setUrlFotoResep(fileName);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            bean.setAction("C");
            bean.setFlag("Y");

            try {
                String msg = telemedicBoProxy.saveAdd(bean, branchId, kodeBank);
                model.setMessage(msg);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("editStatus")) {

            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);
            bean.setStatus(this.status);

            try {
                telemedicBoProxy.saveEdit(bean, branchId, "");
                if (this.status.equalsIgnoreCase("PD")) {

                    //KIRIM PUSH NOTIF JIKA STATUS MENJADI PD
                    List<NotifikasiFcm> result = new ArrayList<>();
                    NotifikasiFcm beanNotif = new NotifikasiFcm();
                    beanNotif.setUserId(idPasien);

                    result = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                    FirebasePushNotif.sendNotificationFirebase(result.get(0).getTokenFcm(), "Telemedic", "Dokter Memanggil ...", "PD", result.get(0).getOs());
                }
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("editFlag")) {
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);
            bean.setFlag("N");

            try {
                telemedicBoProxy.saveEdit(bean, branchId, "");
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getListAntrianSL")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setStatus(status.getStatusAntrianOrder().get(0).getStatus());
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
               result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                telemedicineMobile.setNoAntrian(String.valueOf(i));
                telemedicineMobile.setId(item.getId());
                telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                telemedicineMobile.setIdDokter(item.getIdDokter());
                telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                telemedicineMobile.setIdPasien(item.getIdPasien());
                telemedicineMobile.setStatus(item.getStatus());
                telemedicineMobile.setFlagResep(item.getFlagResep());
                telemedicineMobile.setNoKartu(item.getNoKartu());
                telemedicineMobile.setIdPelayanan(item.getIdPelayanan());

                listOfTelemedic.add(telemedicineMobile);
            }
        }

        if (action.equalsIgnoreCase("getListAntrianAll")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                    TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                    telemedicineMobile.setNoAntrian(String.valueOf(i++));
                    telemedicineMobile.setId(item.getId());
                    telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                    telemedicineMobile.setIdDokter(item.getIdDokter());
                    telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                    telemedicineMobile.setIdPasien(item.getIdPasien());
                    telemedicineMobile.setStatus(item.getStatus());
                    telemedicineMobile.setFlagResep(item.getFlagResep());
                    telemedicineMobile.setNoKartu(item.getNoKartu());
                    telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                    telemedicineMobile.setNamaDokter(item.getNamaDokter());
                    telemedicineMobile.setNamaPasien(item.getNamaPasien());
                    telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                    telemedicineMobile.setKetStatus(item.getKetStatus());
                    telemedicineMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    telemedicineMobile.setKeluhan(item.getKeluhan());
                    telemedicineMobile.setBranchId(item.getBranchId());

                    listOfTelemedic.add(telemedicineMobile);

            }
        }

        if(action.equalsIgnoreCase("getListAntrianNonSL")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                if (!item.getStatus().equalsIgnoreCase("SL")) {
                TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                telemedicineMobile.setNoAntrian(String.valueOf(i++));
                telemedicineMobile.setId(item.getId());
                telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                telemedicineMobile.setIdDokter(item.getIdDokter());
                telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                telemedicineMobile.setIdPasien(item.getIdPasien());
                telemedicineMobile.setStatus(item.getStatus());
                telemedicineMobile.setFlagResep(item.getFlagResep());
                telemedicineMobile.setNoKartu(item.getNoKartu());
                telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                telemedicineMobile.setNamaDokter(item.getNamaDokter());
                telemedicineMobile.setNamaPasien(item.getNamaPasien());
                telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                telemedicineMobile.setKetStatus(item.getKetStatus());

                listOfTelemedic.add(telemedicineMobile);
                }

            }
        }

        if(action.equalsIgnoreCase("getListAntrianEObat")) {
            listOfTelemedic = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");
            bean.setFlagEresep("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                if (!item.getStatus().equalsIgnoreCase("SL")) {
                    TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                    telemedicineMobile.setNoAntrian(String.valueOf(i++));
                    telemedicineMobile.setId(item.getId());
                    telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                    telemedicineMobile.setIdDokter(item.getIdDokter());
                    telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                    telemedicineMobile.setIdPasien(item.getIdPasien());
                    telemedicineMobile.setStatus(item.getStatus());
                    telemedicineMobile.setFlagResep(item.getFlagResep());
                    telemedicineMobile.setNoKartu(item.getNoKartu());
                    telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                    telemedicineMobile.setNamaDokter(item.getNamaDokter());
                    telemedicineMobile.setNamaPasien(item.getNamaPasien());
                    telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                    telemedicineMobile.setKetStatus(item.getKetStatus());
                    telemedicineMobile.setFlagEresep(item.getFlagEresep());
                    telemedicineMobile.setUrlResep(item.getUrlResep());

                    listOfTelemedic.add(telemedicineMobile);
                }

            }
        }

        if (action.equalsIgnoreCase("checkTele")) {

            listOfTelemedic = new ArrayList<>();
            List<AntrianTelemedic> result = new ArrayList<>();

            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);
            bean.setIdDokter(idDokter);
            bean.setFlagResep(flagResep);
            bean.setStatus(this.status);
            bean.setFlag("Y");

            try {
                result = telemedicBoProxy.getSearchByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.checkTele] Error, " + e.getMessage());
            }

            for (AntrianTelemedic item : result) {
                TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                telemedicineMobile.setId(item.getId());
                telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                telemedicineMobile.setIdDokter(item.getIdDokter());
                telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                telemedicineMobile.setIdPasien(item.getIdPasien());
                telemedicineMobile.setStatus(item.getStatus());
                telemedicineMobile.setFlagResep(item.getFlagResep());
                telemedicineMobile.setNoKartu(item.getNoKartu());
                telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                telemedicineMobile.setNamaDokter(item.getNamaDokter());
                telemedicineMobile.setNamaPasien(item.getNamaPasien());
                telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                telemedicineMobile.setKetStatus(item.getKetStatus());
                telemedicineMobile.setFlagBayarResep(item.getFlagBayarResep());
                telemedicineMobile.setFlagBayarKonsultasi(item.getFlagBayarKonsultasi());
                telemedicineMobile.setKeluhan(item.getKeluhan());
                telemedicineMobile.setJenisPengambilan(item.getJenisPengambilan());

                if (flagResep != null && flagResep.equalsIgnoreCase("Y")) {

                    List<ItSimrsPembayaranOnlineEntity> list = new ArrayList<>();

                    PembayaranOnline beanPembayaran = new PembayaranOnline();
                    beanPembayaran.setIdAntrianTelemedic(item.getId());
                    beanPembayaran.setKeterangan("resep");

                    try {
                      list = verifikatorPembayaranBoProxy.getSearchEntityByCriteria(beanPembayaran);
                    } catch (GeneralBOException e) {
                        logger.error("[TelemedicineController.checkTele] Error, " + e.getMessage());
                    }

                    if (list.size() > 0) {
                        List<ResepOnline> listResep = new ArrayList<>();

                        ResepOnline resepOnlineBean = new ResepOnline();
                        resepOnlineBean.setIdTransaksiOnline(list.get(0).getId());

                        try {
                          listResep = resepOnlineBoProxy.getByCriteria(resepOnlineBean);
                        } catch (GeneralBOException e) {
                            logger.error("[TelemedicineController.checkTele] Error, " + e.getMessage());
                        }

                        if (listResep.size() > 0) {
                            listOfTelemedic.add(telemedicineMobile);
                        }
                    }

                } else listOfTelemedic.add(telemedicineMobile);
            }



        }

        if(action.equalsIgnoreCase("checkNoAntrian")){
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setIdDokter(idDokter);
            bean.setIdPelayanan(idPelayanan);
            bean.setFlag("Y");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getListAntrianByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getListAntrianSL] Error, " + e.getMessage());
            }

            int i = 1;
            for(AntrianTelemedic item : result) {
                if(item.getId().equalsIgnoreCase(idTele)){
                    model.setNoAntrian(String.valueOf(i));
                    model.setJumlahAntrian(String.valueOf(result.size()));
                    break;
                }
                i++;
            }
        }

        if (action.equalsIgnoreCase("getDetailCheckup")) {
            HeaderCheckup beanHeader = new HeaderCheckup();
            beanHeader.setIdAntrianOnline(idTele);


            List<HeaderCheckup> resultCheckup = new ArrayList<>();
            List<HeaderDetailCheckup> resultDetailCheckup = new ArrayList<>();

            try {
               resultCheckup = checkupBoProxy.getByCriteria(beanHeader);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            HeaderDetailCheckup beanDetail = new HeaderDetailCheckup();
            beanDetail.setNoCheckup(resultCheckup.get(0).getNoCheckup());

            try {
                resultDetailCheckup = checkupDetailBoProxy.getByCriteria(beanDetail);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            model.setNoCheckup(resultDetailCheckup.get(0).getNoCheckup());
            model.setIdDetailCheckup(resultDetailCheckup.get(0).getIdDetailCheckup());

        }

        if (action.equalsIgnoreCase("insertResep")) {

            String fileName = "";
            if (fileUploadTtd != null) {
                fileName = idTele+".jpeg";
                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+ CommonConstant.RESOURCE_PATH_TTD_DOKTER, fileName);
                try {
                    FileUtils.copyFile(fileUploadTtd, fileCreate);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            List<TransaksiObatDetail> list = new ArrayList<>();
            JSONArray jsonArray;
            if (jsonObat != null && !jsonObat.equalsIgnoreCase("")){
                try{
                    jsonArray = (net.sf.json.JSONArray) JSONSerializer.toJSON(jsonObat);
                    for (int i = 0; i < jsonArray.size(); i++){
                        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                        transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                        transaksiObatDetail.setQty(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                        transaksiObatDetail.setQtyApprove(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                        transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
                        transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
                        transaksiObatDetail.setFlagRacik(jsonArray.getJSONObject(i).getString("flagRacik"));
                        transaksiObatDetail.setHariKronis(!jsonArray.getJSONObject(i).getString("hariKronis").equalsIgnoreCase("") ? Integer.valueOf(jsonArray.getJSONObject(i).getString("hariKronis")) : null);
                        transaksiObatDetail.setCreatedDate(now);
                        transaksiObatDetail.setLastUpdate(now);
                        transaksiObatDetail.setCreatedWho(idDokter);
                        transaksiObatDetail.setLastUpdateWho(idDokter);
                        transaksiObatDetail.setIdPelayanan(tujuanPelayanan);
                        transaksiObatDetail.setTtdDokter(fileName);

                        list.add(transaksiObatDetail);
                    }

                } catch (JSONException e) {
                    logger.error("[CheckupController.create] Error, get json resep " + e.getMessage());
                }
            }

            try {
               BigDecimal totalHarga = telemedicBoProxy.insertResepOnline(idPembayaranOnline, list);

                NotifikasiFcm notif = new NotifikasiFcm();
                notif.setUserId(idPasien);
                List<NotifikasiFcm> fcm = notifikasiFcmBoProxy.getByCriteria(notif);
                FirebasePushNotif.sendNotificationFirebase(fcm.get(0).getTokenFcm(),"Resep Online", "Silahkan buka aplikasi untuk melakukan pembayaran resep", "BAYAR_RESEP", fcm.get(0).getOs());

                model.setMessage(totalHarga.toString());
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }
        }

        logger.info("[TelemedicineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();

    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Object getModel() {
        return listOfTelemedic != null ? listOfTelemedic : model;
    }

    public void setModel(TelemedicineMobile model) {
        this.model = model;
    }

    public Collection<TelemedicineMobile> getListOfTelemedic() {
        return listOfTelemedic;
    }

    public void setListOfTelemedic(Collection<TelemedicineMobile> listOfTelemedic) {
        this.listOfTelemedic = listOfTelemedic;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }
}
