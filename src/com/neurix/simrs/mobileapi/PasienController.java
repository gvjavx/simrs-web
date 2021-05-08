package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.mobileapi.model.Pasien;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 19/12/2019.
 */
public class PasienController extends ValidationAwareSupport implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(PasienController.class);

    private PasienBo pasienBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private Pasien model = new Pasien();
    private String idPasien;
    private String password;
    private String tokenExpo;
    private String tokenFcm;
    private String id;
    private String os;
    private String action;

    private String newPassword;

    public String getTokenFcm() {
        return tokenFcm;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public HttpHeaders create(){
        logger.info("[PasienController.create] start process POST /loginpasien >>>");

        if (action.equalsIgnoreCase("login")){
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

//        if (action.equalsIgnoreCase("saveAdd")) {
//            try {
//                pasienBoProxy.saveAdd(model);
//            } catch (GeneralBOException e) {
//                logger.error("[PasienController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + e + "] Found problem when searching data by criteria, please inform to your admin.", e);
//                throw new GeneralBOException(e);
//            }
//        }

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
        return model;
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
