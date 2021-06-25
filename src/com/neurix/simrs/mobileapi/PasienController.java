package com.neurix.simrs.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.PasienSementara;
import com.neurix.simrs.mobileapi.model.Pasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.security.access.method.P;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Toshiba on 19/12/2019.
 **/
public class PasienController extends ValidationAwareSupport implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(PasienController.class);

    private PasienBo pasienBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private Pasien model = new Pasien();
    private Collection<Pasien> listOfPasien;
    private String idPasien;
    private String password;
    private String tokenExpo;
    private String tokenFcm;
    private String id;
    private String os;
    private String action;
    private String profesi;

    private String newPassword;

    private String nama;
    private String jenisKelamin;
    private String noKtp;
    private String tempatLahir;
    private String tglLahir;
    private String desaId;
    private String jalan;
    private String suku;
    private String agama;
    private String noTelp;
    private String urlKtp;
    private String email;
    private String flag;

    private File fileUploadKtp;

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public File getFileUploadKtp() {
        return fileUploadKtp;
    }

    public void setFileUploadKtp(File fileUploadKtp) {
        this.fileUploadKtp = fileUploadKtp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getSuku() {
        return suku;
    }

    public void setSuku(String suku) {
        this.suku = suku;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getUrlKtp() {
        return urlKtp;
    }

    public void setUrlKtp(String urlKtp) {
        this.urlKtp = urlKtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenFcm() {
        return tokenFcm;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public HttpHeaders create(){
        logger.info("[PasienController.create] start process POST /loginpasien >>>");

        if (action.equalsIgnoreCase("login")){

            //check apakah yang login adalah pasien sementara
            List<PasienSementara> pasienSementaraList;
            PasienSementara bean = new PasienSementara();
            bean.setId(idPasien);
            bean.setFlag("Y");

            try {
               pasienSementaraList = pasienBoProxy.getPasienSementaraByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PasienController.isUserPasienById] Error when searching / inquiring data by criteria," + "[" + e.getMessage()+ "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            // jika tidak bisa dapat dari pasien id sementara, coba dengan email
            if (null==pasienSementaraList || pasienSementaraList.size() == 0)
            {
                try {
                    bean.setEmail(bean.getId());
                    bean.setId(null);
                    pasienSementaraList = pasienBoProxy.getPasienSementaraByCriteria(bean);
                } catch (GeneralBOException e) {
                    logger.error("[PasienController.isUserPasienById] Error when searching / inquiring data by criteria," + "[" + e.getMessage() + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    throw new GeneralBOException(e);
                }
            }

                if (pasienSementaraList.size() != 0) {
                PasienSementara pasienSementara = pasienSementaraList.get(0);
                if (password.equalsIgnoreCase(pasienSementara.getPassword())) {
                    if (!"Y".equalsIgnoreCase(pasienSementara.getFlagLogin())) {

                        model.setIdPasien(pasienSementara.getId());
                        model.setNama(pasienSementara.getNama());
                        model.setEmail(pasienSementara.getEmail());
                        model.setJenisKelamin(pasienSementara.getJenisKelamin());
                        model.setNoKtp(pasienSementara.getNoKtp());
                        model.setTempatLahir(pasienSementara.getTempatLahir());
                        model.setTglLahir(CommonUtil.convertDateToString(pasienSementara.getTglLahir()));
                        model.setDesaId(pasienSementara.getDesaId().toString());
                        model.setJalan(pasienSementara.getJalan());
                        model.setSuku(pasienSementara.getSuku());
                        model.setAgama(pasienSementara.getAgama());
                        model.setNoTelp(pasienSementara.getNoTelp());
                        model.setUrlKtp(pasienSementara.getUrlKtp());
                        model.setFlag(pasienSementara.getFlag());
                        model.setAction(pasienSementara.getAction());
                        model.setCreatedDate(pasienSementara.getCreatedDate());
                        model.setCreatedWho(pasienSementara.getCreatedWho());
                        model.setLastUpdate(pasienSementara.getLastUpdate());
                        model.setLastUpdateWho(pasienSementara.getLastUpdateWho());


                        pasienSementara.setFlagLogin("Y");
                        try {
                            pasienBoProxy.saveEditPasienSementara(pasienSementara);
                        } catch (GeneralBOException e) {
                            logger.error("[PasienController.isUserPasienById] Error when searching / inquiring data by criteria," + "[" + e.getMessage() + "] Found problem when searching data by criteria, please inform to your admin.", e);
                            throw new GeneralBOException(e);
                        }
                    } else {
                        logger.info("User ID tersebut telah login");
                        model.setActionError("User ID tersebut telah login");
                    }
                } else {
                    logger.info("User ID and Password not found.");
                    model.setActionError("User ID and Password not found");
                }
            }
            //Jika bukan pasien sementara, login seperti biasa
            else {
                Boolean isFound = false;

                try {
                    isFound = pasienBoProxy.isUserPasienById(idPasien, password);
                } catch (GeneralBOException e){
                    Long logId = null;
                    try {
                        logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienController.isUserPasienById");
                    } catch (GeneralBOException e1) {
                        logger.error("[PasienController.isUserPasienById] Error when saving error,", e1);
                    }
                    logger.error("[PasienController.isUserPasienById] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    throw new GeneralBOException(e);
                }

                if (isFound)
                {
                    com.neurix.simrs.master.pasien.model.Pasien pasien = new com.neurix.simrs.master.pasien.model.Pasien();

                    pasien.setIdPasien(idPasien);
                    pasien.setFlag("Y");

                    List<com.neurix.simrs.master.pasien.model.Pasien> pasienList = new ArrayList<>();
                    try {
                        pasienList = pasienBoProxy.getByCriteria(pasien);
                    } catch (GeneralBOException e){
                        Long logId = null;
                        try {
                            logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getByCriteria");
                        } catch (GeneralBOException e1) {
                            logger.error("[PasienController.getByCriteria] Error when saving error,", e1);
                        }
                        logger.error("[PasienController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        throw new GeneralBOException(e);
                    }

                    if (!pasienList.isEmpty() && pasienList.size() > 0) {
                        com.neurix.simrs.master.pasien.model.Pasien pasienData = pasienList.get(0);

                        if (pasienData.getFlagLogin() == null || !"Y".equalsIgnoreCase(pasienData.getFlagLogin())) {
                            model.setIdPasien(pasienData.getIdPasien());
                            model.setNama(pasienData.getNama());
                            model.setJenisKelamin(pasienData.getJenisKelamin());
                            model.setNoKtp(pasienData.getNoKtp());
                            model.setNoBpjs(pasienData.getNoBpjs());
                            model.setTempatLahir(pasienData.getTempatLahir());
                            model.setTglLahir(pasienData.getTglLahir());
                            model.setDesaId(pasienData.getDesaId());
                            model.setJalan(pasienData.getJalan());
                            model.setSuku(pasienData.getSuku());
                            model.setAgama(pasienData.getAgama());
                            model.setProfesi(pasienData.getProfesi());
                            model.setNoTelp(pasienData.getNoTelp());
                            model.setUrlKtp(pasienData.getUrlKtp());
                            model.setEmail(pasienData.getEmail());
                            model.setFlag(pasienData.getFlag());
                            model.setAction(pasienData.getAction());
                            model.setCreatedDate(pasienData.getCreatedDate());
                            model.setCreatedWho(pasienData.getCreatedWho());
                            model.setLastUpdate(pasienData.getLastUpdate());
                            model.setLastUpdateWho(pasienData.getLastUpdateWho());

                            List<com.neurix.simrs.master.pasien.model.Pasien> pasienDataList = new ArrayList<>();
                            try {
                                pasienDataList = pasienBoProxy.getDataPasien(pasienData.getDesaId());
                            } catch (GeneralBOException e){
                                Long logId = null;
                                try {
                                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getDataPasien");
                                } catch (GeneralBOException e1) {
                                    logger.error("[PasienController.getDataPasien] Error when saving error,", e1);
                                }
                                logger.error("[PasienController.getDataPasien] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                                throw new GeneralBOException(e);
                            }

                            if (!pasienDataList.isEmpty() && pasienDataList.size() > 0) {
                                com.neurix.simrs.master.pasien.model.Pasien pasienDataNew = new com.neurix.simrs.master.pasien.model.Pasien();
                                pasienDataNew = pasienList.get(0);

                                model.setProvinsiId(pasienDataNew.getProvinsi());
                                model.setKotaId(pasienDataNew.getKota());
                                model.setKecamatanId(pasienDataNew.getKecamatan());
                                model.setDesa(pasienDataNew.getDesa());
                            }

                            NotifikasiFcm notifikasiFcm = new NotifikasiFcm();
                            notifikasiFcm.setUserId(model.getIdPasien());
                            notifikasiFcm.setUserName(model.getNama());
                            notifikasiFcm.setTokenFcm(tokenFcm == null ? "" : tokenFcm);
                            notifikasiFcm.setTokenExpo(tokenExpo == null ? "" : tokenExpo);
                            notifikasiFcm.setLastUpdateWho(model.getNama());
                            notifikasiFcm.setCreatedWho(model.getNama());
                            notifikasiFcm.setOs(os);
                            try {
                                notifikasiFcmBoProxy.saveAdd(notifikasiFcm);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                try {
                                    logId = notifikasiFcmBoProxy.saveErrorMessage(e.getMessage(), "LoginMobileController.isFoundOtherSessionActiveUserSessionLog");
                                } catch (GeneralBOException e1) {
                                    logger.error("[LoginMobileController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                                }
                                logger.error("[LoginMobileController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                                throw new GeneralBOException(e);
                            }
                            pasienData.setFlagLogin("Y");
                            pasienData.setUrlKtp(null);

                            try {
                                pasienBoProxy.saveEdit(pasienData);
                            } catch (GeneralBOException e){
                                logger.error("[LoginMobileController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + e + "] Found problem when searching data by criteria, please inform to your admin.", e);
                                throw new GeneralBOException(e);
                            }
                        } else {
                            logger.info("User ID tersebut telah login");
                            model.setActionError("User ID tersebut telah login");
                        }
                    } else {
                        logger.info("User ID and Password not found.");
                        model.setActionError("User ID and Password not found");
                    }

                } else {
                    logger.info("User ID and Password not found.");
                    model.setActionError("User ID and Password not found");
                }
            }
        }
        if (action.equalsIgnoreCase("resetPassword")){
            List<com.neurix.simrs.master.pasien.model.Pasien> result = new ArrayList<>();

            com.neurix.simrs.master.pasien.model.Pasien pasien = new com.neurix.simrs.master.pasien.model.Pasien();
            pasien.setIdPasien(idPasien);

            try {
               result = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e){
                logger.error("Error get user data", e);
            }

            if (result.size() == 1){
                com.neurix.simrs.master.pasien.model.Pasien newPasien = result.get(0);
                if  (newPasien.getPassword().equalsIgnoreCase(password)){
                    newPasien.setPassword(newPassword);
                    try {
                        pasienBoProxy.saveEdit(newPasien);
                    } catch (GeneralBOException e){
                        logger.error("Error edit user password", e);
                        model.setActionError("Error edit user password");
                    }
                } else {
                    model.setActionError("Password lama tidak sesuai");
                }

            }
        }
        if (action.equalsIgnoreCase("logout")) {
            List<com.neurix.simrs.master.pasien.model.Pasien> result = new ArrayList<>();

            com.neurix.simrs.master.pasien.model.Pasien pasien = new com.neurix.simrs.master.pasien.model.Pasien();
            pasien.setIdPasien(idPasien);

            try {
                result = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e){
                logger.error("Error get user data", e);
            }

            if (result.size() == 1){
                com.neurix.simrs.master.pasien.model.Pasien newPasien = result.get(0);
                newPasien.setFlagLogin("N");
                newPasien.setUrlKtp(null);
                try {
                    pasienBoProxy.saveEdit(newPasien);
                } catch (GeneralBOException e){
                    logger.error("Gagal logout", e);
                    model.setActionError("Gagal Logout");
                }
            }
            else // log out for pasien sementara
            {
                PasienSementara bean = new PasienSementara();
                bean.setId(idPasien);

                List<PasienSementara> list = new ArrayList<>();

                try {
                    list = pasienBoProxy.getPasienSementaraByCriteria(bean);
                } catch (GeneralBOException e){
                    logger.error("Error get user data", e);
                }

                if (list.size() == 1){
                    bean = list.get(0);

                    bean.setFlagLogin("N");
                    bean.setUrlKtp(null);
                    try {
                        pasienBoProxy.saveEditPasienSementara(bean);
                    } catch (GeneralBOException e){
                        logger.error("Gagal logout", e);
                        model.setActionError("Gagal Logout");
                    }
                }
            }
        }

        if (action.equalsIgnoreCase("getPasien")) {
            com.neurix.simrs.master.pasien.model.Pasien pasien = new com.neurix.simrs.master.pasien.model.Pasien();

            pasien.setIdPasien(idPasien);
            pasien.setFlag("Y");

            List<com.neurix.simrs.master.pasien.model.Pasien> pasienList = new ArrayList<>();
            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = pasienBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienController.getByCriteria] Error when saving error,", e1);
                }
                logger.error("[PasienController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            if (pasienList.size() > 0) {
                com.neurix.simrs.master.pasien.model.Pasien pasienData = pasienList.get(0);
                model.setIdPasien(pasienData.getIdPasien());
                model.setNama(pasienData.getNama());
                model.setJenisKelamin(pasienData.getJenisKelamin());
                model.setNoKtp(pasienData.getNoKtp());
                model.setNoBpjs(pasienData.getNoBpjs());
                model.setTempatLahir(pasienData.getTempatLahir());
                model.setTglLahir(pasienData.getTglLahir());
                model.setDesaId(pasienData.getDesaId());
                model.setJalan(pasienData.getJalan());
                model.setSuku(pasienData.getSuku());
                model.setAgama(pasienData.getAgama());
                model.setProfesi(pasienData.getProfesi());
                model.setNoTelp(pasienData.getNoTelp());
                model.setUrlKtp(pasienData.getUrlKtp());
                model.setFlag(pasienData.getFlag());
                model.setAction(pasienData.getAction());
                model.setCreatedDate(pasienData.getCreatedDate());
                model.setCreatedWho(pasienData.getCreatedWho());
                model.setLastUpdate(pasienData.getLastUpdate());
                model.setLastUpdateWho(pasienData.getLastUpdateWho());
            }
        }

        if (action.equalsIgnoreCase("saveAddPasienSementara")) {

            PasienSementara bean = new PasienSementara();
            com.neurix.simrs.master.pasien.model.Pasien psBean;
            List<com.neurix.simrs.master.pasien.model.Pasien> listPSBean;
            List<PasienSementara> listResult = null;
            PasienSementara result;

            // validation email, saat pendaftaran tidak boleh ada email yang sama / sudah terdaftar di pasien sementara
            try {
                bean.setEmail(email);
                listResult = pasienBoProxy.getPasienSementaraByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PasienController.saveAddPasienSementara] Error when searching / inquiring data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            // jika list result lebih dari 0, maka sudah dipastikan ada email yang sudah terdaftar
            if (null!=listResult && listResult.size() > 0)
            {
                listOfPasien = new ArrayList<>();
                Pasien bn = new Pasien();
                bn.setErrorMsg("Error: Email sudah terdaftar, mohon gunakan email lain, atau login dengan email tersebut.");
                listOfPasien.add(bn);
                logger.error("[PasienController.create] Error karena email sudah terdaftar <<<");
                return new DefaultHttpHeaders("success")
                        .disableCaching();
            }
            else
            {

                // dan juga check di pasien tetap
                try {
                    psBean = new com.neurix.simrs.master.pasien.model.Pasien();
                    psBean.setEmail(email);
                    listPSBean = pasienBoProxy.getByCriteria(psBean);
                } catch (GeneralBOException e) {
                    logger.error("[PasienController.saveAddPasienSementara] Error when searching / inquiring data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
                    throw new GeneralBOException(e);
                }

                // jika list result lebih dari 0, maka sudah dipastikan ada email yang sudah terdaftar
                if (null!=listPSBean && listPSBean.size() > 0)
                {
                    listOfPasien = new ArrayList<>();
                    Pasien bn = new Pasien();
                    bn.setErrorMsg("Error: Email sudah terdaftar, mohon gunakan email lain, atau login dengan email tersebut.");
                    listOfPasien.add(bn);
                    logger.error("[PasienController.create] Error karena email sudah terdaftar <<<");
                    return new DefaultHttpHeaders("success")
                            .disableCaching();
                }
            }

            bean = new PasienSementara();
            bean.setNama(nama);
            bean.setJenisKelamin(jenisKelamin);
            bean.setNoKtp(noKtp);
            bean.setTempatLahir(tempatLahir);
            bean.setTglLahir(CommonUtil.convertStringToDate(tglLahir));
            bean.setDesaId(Long.parseLong(desaId));
            bean.setJalan(jalan);
            bean.setSuku(suku);
            bean.setAgama(agama);
            bean.setNoTelp(noTelp);
            bean.setEmail(email);
            bean.setPassword(password);
            bean.setProfesi(profesi);

            if (fileUploadKtp != null) {
                urlKtp = noKtp+".jpeg";
//                File fileCreate = new File(, fileName);
                try {
                    BufferedImage bufferedImage = ImageIO.read(fileUploadKtp);
                    String imageType = CommonUtil.getImageFormat(fileUploadKtp);
                    CrudResponse crudResponse = CommonUtil.compressImage(bufferedImage, imageType,CommonUtil.getPropertyParams("upload.folder")+ CommonConstant.RESOURCE_PATH_KTP_PASIEN+urlKtp);
                    bean.setUrlKtp(urlKtp);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            bean.setFlag("Y");
            bean.setAction("C");
            bean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            bean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            bean.setFlagLogin("N");
            try {
              result = pasienBoProxy.saveAddPasienSementara(bean);
            } catch (GeneralBOException e) {
                logger.error("[PasienController.saveAddPasienSementara] Error when searching / inquiring data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            model.setIdPasien(result.getId());
            model.setNama(result.getNama());
            model.setAgama(result.getAgama());
            model.setJenisKelamin(result.getJenisKelamin());
            model.setDesaId(result.getDesaId().toString());
            model.setAlamat(result.getJalan());
            model.setNoTelp(result.getNoTelp());
            model.setNoKtp(result.getNoKtp());
            model.setPassword(result.getPassword());
            model.setEmail(result.getEmail());
            model.setTempatLahir(result.getTempatLahir());
            model.setTglLahir(CommonUtil.convertDateToString(result.getTglLahir()));
            model.setSuku(result.getSuku());
            model.setUrlKtp(result.getUrlKtp());
            model.setProfesi(result.getProfesi());
        }

            if (action.equalsIgnoreCase("getPasienSementaraByCriteria")) {

            listOfPasien = new ArrayList<>();
            List<PasienSementara> result = new ArrayList<>();

            PasienSementara bean = new PasienSementara();
            bean.setId(id);
            bean.setNama(nama);
            bean.setDesaId(desaId != null ? Long.valueOf(desaId) : null);
            bean.setFlag(flag);
            bean.setNoKtp(noKtp);
            bean.setEmail(email);

            try {
              result = pasienBoProxy.getPasienSementaraByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PasienController.getPasienSementaraByCriteria] Error when searching / inquiring data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            if (result.size() > 0) {
                for (PasienSementara item : result) {
                    Pasien pasien = new Pasien();
                    pasien.setIdPasien(item.getId());
                    pasien.setNama(item.getNama());
                    pasien.setNoKtp(item.getNoKtp());
                    pasien.setEmail(item.getEmail());
                    pasien.setDesaId(item.getDesaId().toString());
                    pasien.setTempatLahir(item.getTempatLahir());
                    pasien.setTglLahir(CommonUtil.convertDateToString2(item.getTglLahir()));
                    pasien.setSuku(item.getSuku());
                    pasien.setJalan(item.getJalan());
                    pasien.setJenisKelamin(item.getJenisKelamin());
                    pasien.setUrlKtp(item.getUrlKtp());
                    pasien.setAgama(item.getAgama());
                    pasien.setFlag(item.getFlag());
                    pasien.setAction(item.getAction());
                    pasien.setNoTelp(item.getNoTelp());
                    pasien.setProfesi(item.getProfesi());

                    listOfPasien.add(pasien);
                }
            }
        }
        logger.info("[PasienController.create] end process POST /loginpasien <<<");
        return new DefaultHttpHeaders("success")
                .disableCaching();
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PasienController.logger = logger;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public void setModel(Pasien model) {
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTokenExpo() {
        return tokenExpo;
    }

    public void setTokenExpo(String tokenExpo) {
        this.tokenExpo = tokenExpo;
    }

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Object getModel() {
        return listOfPasien != null ? listOfPasien : model ;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
