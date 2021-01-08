package com.neurix.simrs.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.mobileapi.model.PengirimanObatMobile;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Thursday, 18/06/20 11:32
 */
public class PengirimanObatController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PengirimanObatController.class);

    private PengirimanObatMobile model = new PengirimanObatMobile();
    private Collection<PengirimanObatMobile> listOfPengirimanObat;
    private TelemedicBo telemedicBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private String action;
    private String idKurir;
    private String idPasien;
    private String idTele;
    private String idPengirimanObat;
    private String keterangan;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    private File fotoKirim;

    public File getFotoKirim() {
        return fotoKirim;
    }

    public void setFotoKirim(File fotoKirim) {
        this.fotoKirim = fotoKirim;
    }

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public String getIdPengirimanObat() {
        return idPengirimanObat;
    }

    public void setIdPengirimanObat(String idPengirimanObat) {
        this.idPengirimanObat = idPengirimanObat;
    }

    public String getIdTele() {
        return idTele;
    }

    public void setIdTele(String idTele) {
        this.idTele = idTele;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(PengirimanObatMobile model) {
        this.model = model;
    }

    public Collection<PengirimanObatMobile> getListOfPengirimanObat() {
        return listOfPengirimanObat;
    }

    public void setListOfPengirimanObat(Collection<PengirimanObatMobile> listOfPengirimanObat) {
        this.listOfPengirimanObat = listOfPengirimanObat;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    @Override
    public Object getModel() {
        return listOfPengirimanObat != null ? listOfPengirimanObat : model;
    }

    public HttpHeaders create() {
        logger.info("[PengirimanObatController.create] START >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("getPengiriman")) {
            listOfPengirimanObat = new ArrayList<>();

            List<PengirimanObat> result = new ArrayList<>();

            try { result = telemedicBoProxy.getListPengirimanById(idKurir, idPasien);
            } catch (GeneralBOException e){
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            for (PengirimanObat item : result) {
                PengirimanObatMobile pengirimanObatMobile = new PengirimanObatMobile();
                pengirimanObatMobile.setId(item.getId());
                pengirimanObatMobile.setIdKurir(item.getIdKurir());
                pengirimanObatMobile.setIdPasien(item.getIdPasien());
                pengirimanObatMobile.setIdPelayanan(item.getIdPelayanan());
                pengirimanObatMobile.setBranchId(item.getBranchId());
                pengirimanObatMobile.setAlamat(item.getAlamat());
                pengirimanObatMobile.setFlagPickup(item.getFlagPickup());
                pengirimanObatMobile.setFlagDiterimaPasien(item.getFlagDiterimaPasien());
                pengirimanObatMobile.setBranchName(item.getBranchName());
                pengirimanObatMobile.setKurirName(item.getKurirName());
                pengirimanObatMobile.setPelayananName(item.getPelayananName());
                pengirimanObatMobile.setPasienName(item.getPasienName());
                pengirimanObatMobile.setNoTelp(item.getNoTelp());
                pengirimanObatMobile.setNoTelpKurir(item.getNoTelpKurir());
                pengirimanObatMobile.setNoPolisi(item.getNoPolisi());
                pengirimanObatMobile.setIdResep(item.getIdResep());
                pengirimanObatMobile.setLat(item.getLat());
                pengirimanObatMobile.setLon(item.getLon());
                pengirimanObatMobile.setFotoKirim(item.getFotoKirim());
                pengirimanObatMobile.setKeterangan(item.getKeterangan());

                listOfPengirimanObat.add(pengirimanObatMobile);
            }

        }

        if  (action.equalsIgnoreCase("updateFlagDiterima")) {

            List<PengirimanObat> result = new ArrayList<>();

            PengirimanObat bean = new PengirimanObat();
            bean.setIdPasien(idPasien);
            bean.setId(idPengirimanObat);
            bean.setFlag("Y");

            try{
               result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setFlagDiterimaPasien("Y");
            newPengirimanObat.setFlag("N");
            newPengirimanObat.setLastUpdate(now);
            newPengirimanObat.setLastUpdateWho(idPasien);
            newPengirimanObat.setAction("U");

            try {
                telemedicBoProxy.saveEditPengirimanObat(newPengirimanObat);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }
        }

        if  (action.equalsIgnoreCase("updateFlagPickup")) {

            List<PengirimanObat> result = new ArrayList<>();

            PengirimanObat bean = new PengirimanObat();
            bean.setIdPasien(idPasien);
            bean.setId(idPengirimanObat);
            bean.setFlag("Y");

            try{
                result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setFlagPickup("Y");
            newPengirimanObat.setLastUpdate(now);
            newPengirimanObat.setLastUpdateWho(idPasien);
            newPengirimanObat.setAction("U");

            try {
                telemedicBoProxy.saveEditPengirimanObat(newPengirimanObat);

                Notifikasi notifBean = new Notifikasi();
                notifBean.setTipeNotifId("TN12");
                notifBean.setNip(idPasien);
                notifBean.setNamaPegawai("admin");
                notifBean.setNote("Kurir telah mengambil obat di apotik");
                notifBean.setTo("RS0104200035");
                notifBean.setFromPerson("admin");
                notifBean.setNoRequest(newPengirimanObat.getId());
                notifBean.setFlag("Y");
                notifBean.setRead("N");
                notifBean.setAction("C");
                notifBean.setCreatedDate(now);
                notifBean.setLastUpdate(now);
                notifBean.setCreatedWho("admin");
                notifBean.setLastUpdateWho("admin");

                notifikasiBoProxy.saveAdd(notifBean);
                model.setMessage("Success");


                List<NotifikasiFcm> resultNotif = new ArrayList<>();
                NotifikasiFcm beanNotif = new NotifikasiFcm();
                beanNotif.setUserId(idPasien);

                resultNotif = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Pengiriman Obat", "Kurir telah mengambil obat di apotik", "SK", resultNotif.get(0).getOs(), null);

            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }
        }

        if  (action.equalsIgnoreCase("updateFlagTerkirim")) {

            List<PengirimanObat> result = new ArrayList<>();

            PengirimanObat bean = new PengirimanObat();
            bean.setIdPasien(idPasien);
            bean.setId(idPengirimanObat);
            bean.setFlag("Y");

            try{
                result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setFlagTerkirim("Y");
            newPengirimanObat.setFlagDiterimaPasien("Y");
            newPengirimanObat.setFlag("N");
            newPengirimanObat.setLastUpdate(now);
            newPengirimanObat.setLastUpdateWho(idPasien);
            newPengirimanObat.setAction("U");

            try {
                telemedicBoProxy.saveEditPengirimanObat(newPengirimanObat);

                Notifikasi notifBean = new Notifikasi();
                notifBean.setTipeNotifId("TN12");
                notifBean.setNip(idPasien);
                notifBean.setNamaPegawai("admin");
                notifBean.setNote("Kurir telah mengirimkan obat");
                notifBean.setTo(idPasien);
                notifBean.setFromPerson("admin");
                notifBean.setNoRequest(newPengirimanObat.getId());
                notifBean.setFlag("Y");
                notifBean.setRead("N");
                notifBean.setAction("C");
                notifBean.setCreatedDate(now);
                notifBean.setLastUpdate(now);
                notifBean.setCreatedWho("admin");
                notifBean.setLastUpdateWho("admin");

                notifikasiBoProxy.saveAdd(notifBean);
                model.setMessage("Success");

                List<NotifikasiFcm> resultNotif = new ArrayList<>();
                NotifikasiFcm beanNotif = new NotifikasiFcm();
                beanNotif.setUserId(idPasien);

                resultNotif = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Pengiriman Obat", "Kurir telah mengirimkan obat", "SK", resultNotif.get(0).getOs(), null);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }
        }

        if (action.equalsIgnoreCase("uploadFotoKirim")) {

            String fileName = "";
            if (fotoKirim != null) {
                fileName = idPengirimanObat+".jpeg";
                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+ CommonConstant.RESOURCE_PATH_FOTO_KIRIM, fileName);
//                try {
//                    BufferedImage bufferedImage = ImageIO.read(fotoKirim);
//                    String imageType = CommonUtil.getImageFormat(fotoKirim);
//                    CrudResponse crudResponse = CommonUtil.compressImage(bufferedImage, imageType,CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_FOTO_KIRIM+"/"+fileName);
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
                try {
                    FileUtils.copyFile(fotoKirim, fileCreate);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            List<PengirimanObat> result = new ArrayList<>();

            PengirimanObat bean = new PengirimanObat();
            bean.setIdPasien(idPasien);
            bean.setId(idPengirimanObat);
            bean.setFlag("Y");

            try {
                result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setLastUpdate(now);
            newPengirimanObat.setLastUpdateWho(idKurir);
            newPengirimanObat.setAction("U");
            newPengirimanObat.setFotoKirim(fileName);
            newPengirimanObat.setKeterangan(keterangan);

            try {
                telemedicBoProxy.saveEditPengirimanObat(newPengirimanObat);

                Notifikasi notifBean = new Notifikasi();
                notifBean.setTipeNotifId("TN12");
                notifBean.setNip(idPasien);
                notifBean.setNamaPegawai("admin");
                notifBean.setNote("Kurir telah menitipkan obat. Silahkan cek di aplikasi");
                notifBean.setTo(idPasien);
                notifBean.setFromPerson("admin");
                notifBean.setNoRequest(newPengirimanObat.getId());
                notifBean.setFlag("Y");
                notifBean.setRead("N");
                notifBean.setAction("C");
                notifBean.setCreatedDate(now);
                notifBean.setLastUpdate(now);
                notifBean.setCreatedWho("admin");
                notifBean.setLastUpdateWho("admin");

                notifikasiBoProxy.saveAdd(notifBean);
                model.setMessage("Success");

                List<NotifikasiFcm> resultNotif = new ArrayList<>();
                NotifikasiFcm beanNotif = new NotifikasiFcm();
                beanNotif.setUserId(idPasien);

                resultNotif = notifikasiFcmBoProxy.getByCriteria(beanNotif);
                FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Pengiriman Obat", "Kurir telah menitipkan obat. Silahkan cek di aplikasi", "SK", resultNotif.get(0).getOs(), null);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }
        }

        if(action.equalsIgnoreCase("getHistoryPengiriman")){

            List<PengirimanObat> result = new ArrayList<>();
            listOfPengirimanObat = new ArrayList<>();

            try {
               result = telemedicBoProxy.getHistoryPengiriman(idKurir);
            } catch (GeneralBOException e){
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            if (result.size() > 0) {
                for (PengirimanObat item : result) {
                    PengirimanObatMobile pengirimanObatMobile = new PengirimanObatMobile();
                    pengirimanObatMobile.setId(item.getId());
                    pengirimanObatMobile.setIdKurir(item.getIdKurir());
                    pengirimanObatMobile.setIdPasien(item.getIdPasien());
                    pengirimanObatMobile.setIdPelayanan(item.getIdPelayanan());
                    pengirimanObatMobile.setBranchId(item.getBranchId());
                    pengirimanObatMobile.setAlamat(item.getAlamat());
                    pengirimanObatMobile.setFlagPickup(item.getFlagPickup());
                    pengirimanObatMobile.setFlagDiterimaPasien(item.getFlagDiterimaPasien());
                    pengirimanObatMobile.setBranchName(item.getBranchName());
                    pengirimanObatMobile.setKurirName(item.getKurirName());
                    pengirimanObatMobile.setPelayananName(item.getPelayananName());
                    pengirimanObatMobile.setPasienName(item.getPasienName());
                    pengirimanObatMobile.setNoTelp(item.getNoTelp());
                    pengirimanObatMobile.setNoTelpKurir(item.getNoTelpKurir());
                    pengirimanObatMobile.setNoPolisi(item.getNoPolisi());
                    pengirimanObatMobile.setIdResep(item.getIdResep());
                    pengirimanObatMobile.setLat(item.getLat());
                    pengirimanObatMobile.setLon(item.getLon());
                    pengirimanObatMobile.setFotoKirim(item.getFotoKirim());
                    pengirimanObatMobile.setKeterangan(item.getKeterangan());
                    pengirimanObatMobile.setCreatedDate(item.getCreatedDate().toLocaleString());

                    listOfPengirimanObat.add(pengirimanObatMobile);
                }

            }

        }

        logger.info("[PengirimanObatController.create] END >>>");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
