package com.neurix.simrs.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.PesertaResponse;
import com.neurix.simrs.bpjs.vclaim.model.RujukanResponse;
import com.neurix.simrs.mobileapi.model.ResepOnlineMobile;
import com.neurix.simrs.mobileapi.model.TelemedicineMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.antriantelemedic.model.StatusAntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
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
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private BpjsBo bpjsBoProxy;
    private NotifikasiBo notifikasiBoProxy;

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

    private String keterangan;

    private boolean isResep;

    private String namaDokter;

    private String noCheckup;

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public BpjsBo getBpjsBoProxy() {
        return bpjsBoProxy;
    }

    public void setBpjsBoProxy(BpjsBo bpjsBoProxy) {
        this.bpjsBoProxy = bpjsBoProxy;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

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
            bean.setJenisPembayaran(model.getJenisPembayaran());
            bean.setJenisRujukan(model.getJenisRujukan());

            String fileName = "";
            if (fileUploadResep != null) {
                fileName = model.getIdPasien()+"_"+now.getTime()+".jpeg";
                File fileCreate = new File(CommonUtil.getPropertyParams("upload.external.dir")+ CommonConstant.RESOURCE_PATH_RESEP, fileName);
                try {
                    FileUtils.copyFile(fileUploadResep, fileCreate);
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

                    org.json.JSONObject sendData = new JSONObject();
                    sendData.put("namaDokter", namaDokter);
                    sendData.put("idDokter", idDokter);
                    sendData.put("idPasien", idPasien);
                    sendData.put("noCheckup", noCheckup);
                    sendData.put("branchId", branchId);

                    result = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                    FirebasePushNotif.sendNotificationFirebase(result.get(0).getTokenFcm(), "Telemedic", "Dokter Memanggil ...", "PD", result.get(0).getOs(), sendData);
                }
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            } catch (org.json.JSONException e) {
                e.printStackTrace();
            }
        }

        if (action.equalsIgnoreCase("editFlag")) {

            List<ItSimrsPembayaranOnlineEntity> list = new ArrayList<>();

            PembayaranOnline entity = new PembayaranOnline();
            entity.setIdAntrianTelemedic(idTele);

            try {
               list = verifikatorPembayaranBoProxy.getSearchEntityByCriteria(entity);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }

            if  (list.size() > 0) {
                for (ItSimrsPembayaranOnlineEntity item : list) {
                    item.setFlag("N");
                    item.setFlag("U");
                    item.setLastUpdateWho("admin");

                    try {
                        verifikatorPembayaranBoProxy.saveEdit(item);
                    } catch (GeneralBOException e) {
                        logger.error("[TelemedicineController.create] Error, " + e.getMessage());
                    }
                }
            }

            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);
            bean.setFlag("N");

            try {
                telemedicBoProxy.saveEdit(bean, branchId, "");
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.create] Error, " + e.getMessage());
            }

            //JIKA BATAL atau TIMEOUT, maka update list antrian
            if (keterangan.equalsIgnoreCase("batal") || keterangan.equalsIgnoreCase("timeout") ) {

                List<AntrianTelemedic> telemedicList = new ArrayList<>();

                AntrianTelemedic search = new AntrianTelemedic();
                search.setIdDokter(idDokter);
                search.setIdPelayanan(idPelayanan);
                search.setStatus("WL");
                search.setFlag("Y");

                try{
                    telemedicList = telemedicBoProxy.getSearchByCriteria(search);

                } catch (GeneralBOException e){
                    logger.error("[TelemedicineController.create] Error, " + e.getMessage());

                }

                if (telemedicList.size() < 3) {

                    ItSimrsAntrianTelemedicEntity firstOrderAntrian = null;

                    try {
                        firstOrderAntrian = telemedicBoProxy.getAntrianTelemedicFirstOrder(idPelayanan, idDokter, "LL");
                    } catch (GeneralBOException e) {
                        logger.error("[TelemedicineController.create] Error, " + e.getMessage());
                    }

                    if (firstOrderAntrian != null){

                        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                        antrianTelemedic.setId(antrianTelemedic.getId());
                        antrianTelemedic.setStatus("WL");
                        antrianTelemedic.setLastUpdate(now);
                        antrianTelemedic.setLastUpdateWho("adminMobile");

                        try {
                            telemedicBoProxy.saveEdit(antrianTelemedic, firstOrderAntrian.getBranchId(), firstOrderAntrian.getKodeBank());

                            Notifikasi notifBean = new Notifikasi();
                            notifBean.setTipeNotifId("TN10");
                            notifBean.setNip(firstOrderAntrian.getIdPasien());
                            notifBean.setNamaPegawai("admin");
                            notifBean.setNote("Anda telah memasuki Antrian Waiting List. Silahkan lakukan pembayaran");
                            notifBean.setTo(firstOrderAntrian.getIdPasien());
                            notifBean.setFromPerson("admin");
                            notifBean.setNoRequest(antrianTelemedic.getId());
                            notifBean.setFlag("Y");
                            notifBean.setRead("N");
                            notifBean.setAction("C");
                            notifBean.setCreatedDate(now);
                            notifBean.setLastUpdate(now);
                            notifBean.setCreatedWho("admin");
                            notifBean.setLastUpdateWho("admin");

                            notifikasiBoProxy.saveAdd(notifBean);

                            //Push Notif ke Pasien terkait perubahan status menjadi WL
                            NotifikasiFcm beanNotif = new NotifikasiFcm();
                            beanNotif.setUserId(firstOrderAntrian.getIdPasien());
                            List<NotifikasiFcm> notifikasiFcm = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                            FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Telemedic", "Anda telah memasuki Antrian Waiting List. Silahkan lakukan pembayaran", "WL", notifikasiFcm.get(0).getOs(), null);

                        } catch (GeneralBOException e){
                            logger.error("[TelemedicineController.create] Error, " + e.getMessage());
                        }
                    }

                }
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
            bean.setStatus("ER");

            List<AntrianTelemedic> result = new ArrayList<>();

            try {
                result = telemedicBoProxy.getSearchByCriteria(bean);
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
                    telemedicineMobile.setFlagEresep(item.getFlagEresep());
                    telemedicineMobile.setUrlResep(item.getUrlResep());

                    listOfTelemedic.add(telemedicineMobile);
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
                telemedicineMobile.setUrlResep(item.getUrlResep());
                telemedicineMobile.setJenisPembayaran(item.getJenisPembayaran());

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

                        listOfTelemedic.add(telemedicineMobile);

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
            ItSimrsHeaderChekupEntity entity = new ItSimrsHeaderChekupEntity();
            List<HeaderDetailCheckup> resultDetailCheckup = new ArrayList<>();

            try {
               entity = verifikatorPembayaranBoProxy.getHeaderCheckupByIdAntrinTelemedic(idTele);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            HeaderDetailCheckup beanDetail = new HeaderDetailCheckup();
            beanDetail.setNoCheckup(entity.getNoCheckup());

            try {
                resultDetailCheckup = checkupDetailBoProxy.getByCriteria(beanDetail);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            model.setNoCheckup(resultDetailCheckup.get(0).getNoCheckup());
            model.setIdDetailCheckup(resultDetailCheckup.get(0).getIdDetailCheckup());

        }

        if(action.equalsIgnoreCase("getHistoryByIdPasien")) {

            List<AntrianTelemedic> listTelemedic = new ArrayList<>();
            listOfTelemedic = new ArrayList<>();

            try {
               listTelemedic =  telemedicBoProxy.getHistoryByIdPasien(idPasien);
            } catch (GeneralBOException e){
                logger.error("[TelemedicineController.getDetailCheckup] Error, " + e.getMessage());
            }

            if(listTelemedic.size() > 0) {
                for (AntrianTelemedic item : listTelemedic) {
                    TelemedicineMobile telemedicineMobile = new TelemedicineMobile();
                    telemedicineMobile.setId(item.getId());
                    telemedicineMobile.setFlagResep(item.getFlagResep());
                    telemedicineMobile.setFlagEresep(item.getFlagEresep());
                    telemedicineMobile.setIdPasien(item.getIdPasien());
                    telemedicineMobile.setNamaDokter(item.getNamaDokter());
                    telemedicineMobile.setIdDokter(item.getIdDokter());
                    telemedicineMobile.setNamaPelayanan(item.getNamaPelayanan());
                    telemedicineMobile.setIdPelayanan(item.getIdPelayanan());
                    telemedicineMobile.setStatus(item.getStatus());
                    telemedicineMobile.setFlagBayarKonsultasi(item.getFlagBayarKonsultasi());
                    telemedicineMobile.setFlagBayarResep(item.getFlagBayarResep());
                    telemedicineMobile.setIdPembayaran(item.getIdPembayaran());
                    telemedicineMobile.setNominal(item.getNominal().toString());
                    telemedicineMobile.setNoKartu(item.getNoKartu());
                    telemedicineMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                    telemedicineMobile.setIdAsuransi(item.getIdAsuransi());
                    telemedicineMobile.setKodeBank(item.getKodeBank());
                    telemedicineMobile.setJenisPembayaran(item.getJenisPembayaran());
                    telemedicineMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    telemedicineMobile.setKeterangan(item.getKeterangan());
                    telemedicineMobile.setApprovedFlag(item.getApprovedFlag());

                    listOfTelemedic.add(telemedicineMobile);
                }
            }
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

                Notifikasi notifBean = new Notifikasi();
                notifBean.setTipeNotifId("TN11");
                notifBean.setNip(idPasien);
                notifBean.setNamaPegawai("admin");
                notifBean.setNote("Silahkan buka aplikasi untuk melakukan pembayaran resep");
                notifBean.setTo(idPasien);
                notifBean.setFromPerson("admin");
                notifBean.setNoRequest(idPembayaranOnline);
                notifBean.setFlag("Y");
                notifBean.setRead("N");
                notifBean.setAction("C");
                notifBean.setCreatedDate(now);
                notifBean.setLastUpdate(now);
                notifBean.setCreatedWho("admin");
                notifBean.setLastUpdateWho("admin");

                notifikasiBoProxy.saveAdd(notifBean);
                model.setMessage(totalHarga.toString());

                NotifikasiFcm notif = new NotifikasiFcm();
                notif.setUserId(idPasien);
                List<NotifikasiFcm> fcm = notifikasiFcmBoProxy.getByCriteria(notif);
                FirebasePushNotif.sendNotificationFirebase(fcm.get(0).getTokenFcm(),"Resep Online", "Silahkan buka aplikasi untuk melakukan pembayaran resep", "BAYAR_RESEP", fcm.get(0).getOs(), null);

            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }
        } else if (action.equalsIgnoreCase("createPembayaranResep")) {

            List<AntrianTelemedic> listTele = new ArrayList<>();
            AntrianTelemedic bean = new AntrianTelemedic();
            bean.setId(idTele);

            try {
               listTele = telemedicBoProxy.getSearchByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }

            AntrianTelemedic tele = listTele.get(0);

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
                telemedicBoProxy.createPembayaranResep(tele, list);

                Notifikasi notifBean = new Notifikasi();
                notifBean.setTipeNotifId("TN11");
                notifBean.setNip(idPasien);
                notifBean.setNamaPegawai("admin");
                notifBean.setNote("Silahkan buka aplikasi untuk melakukan pembayaran resep");
                notifBean.setTo(idPasien);
                notifBean.setFromPerson("admin");
                notifBean.setNoRequest(idPembayaranOnline);
                notifBean.setFlag("Y");
                notifBean.setRead("N");
                notifBean.setAction("C");
                notifBean.setCreatedDate(now);
                notifBean.setLastUpdate(now);
                notifBean.setCreatedWho("admin");
                notifBean.setLastUpdateWho("admin");

                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }
        } else if (action.equalsIgnoreCase("checkBpjs")) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String text = df.format(now);

            PesertaResponse response = new PesertaResponse();

            try {
               response = bpjsBoProxy.GetPesertaBpjsByAPIBpjs(model.getNoKartu(), text, model.getBranchId());
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }

            if (response.getNoKartu() != null) {
                model.setMessage("Success");
            }

        } else if (action.equalsIgnoreCase("checkRujukan")) {

            RujukanResponse response = new RujukanResponse();

            try {
               response = bpjsBoProxy.caraRujukanBerdasarNomorBpjs(model.getNoRujukan(), model.getJenisRujukan(), model.getBranchId());
            } catch (GeneralBOException e) {
                logger.error("[TelemedicineController.insertResep] Error, " + e.getMessage());
            }

            if (response.getNoRujukan() != null) {
                model.setMessage("Success");
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
