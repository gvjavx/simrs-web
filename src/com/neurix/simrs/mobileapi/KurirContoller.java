package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.kurir.bo.KurirBo;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.mobileapi.model.KurirMobile;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAwareSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 16/06/20 11:31
 */
public class KurirContoller extends ValidationAwareSupport implements ModelDriven<Object> {
    private static transient Logger logger = Logger.getLogger(KurirContoller.class);

    private KurirBo kurirBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private KurirMobile model = new KurirMobile();
    private List<KurirMobile> listOfKurir;
    private String idKurir;
    private String nama;
    private String noKtp;
    private String noTelp;
    private String noPolisi;
    private String flagReady;
    private String password;
    private String branchId;

    private String tokenFcm;
    private String tokenExpo;
    private String os;
    private String action;

    private String lat;
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getFlagReady() {
        return flagReady;
    }

    public void setFlagReady(String flagReady) {
        this.flagReady = flagReady;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTokenFcm() {
        return tokenFcm;
    }

    public void setTokenFcm(String tokenFcm) {
        this.tokenFcm = tokenFcm;
    }

    public String getTokenExpo() {
        return tokenExpo;
    }

    public void setTokenExpo(String tokenExpo) {
        this.tokenExpo = tokenExpo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KurirContoller.logger = logger;
    }

    public KurirBo getKurirBoProxy() {
        return kurirBoProxy;
    }

    public void setKurirBoProxy(KurirBo kurirBoProxy) {
        this.kurirBoProxy = kurirBoProxy;
    }

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public void setModel(KurirMobile model) {
        this.model = model;
    }

    public List<KurirMobile> getListOfKurir() {
        return listOfKurir;
    }

    public void setListOfKurir(List<KurirMobile> listOfKurir) {
        this.listOfKurir = listOfKurir;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
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

    @Override
    public Object getModel() {
        return listOfKurir != null ? listOfKurir : model;
    }

    public HttpHeaders create() {
        logger.info("[KurirController.create] start process POST /kurir >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("login")){
            Boolean isFound = false;

            try {
                isFound = kurirBoProxy.isUserKurirById(idKurir, password);
            } catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = kurirBoProxy.saveErrorMessage(e.getMessage(), "PasienController.isUserPasienById");
                } catch (GeneralBOException e1) {
                    logger.error("[PasienController.isUserPasienById] Error when saving error,", e1);
                }
                logger.error("[PasienController.isUserPasienById] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            if (isFound)
            {
                Kurir kurir = new Kurir();

                kurir.setIdKurir(idKurir);
                kurir.setFlag("Y");

                List<Kurir> kurirList = new ArrayList<>();
                try {
                    kurirList = kurirBoProxy.getByCriteria(kurir);
                } catch (GeneralBOException e){
                    Long logId = null;
                    try {
                        logId = kurirBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[KurirController.getByCriteria] Error when saving error,", e1);
                    }
                    logger.error("[KurirController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    throw new GeneralBOException(e);
                }

                if (!kurirList.isEmpty() && kurirList.size() > 0)
                {
                   Kurir kurirData = kurirList.get(0);

                    model.setIdKurir(kurirData.getIdKurir());
                    model.setNama(kurirData.getNama());
                    model.setNoKtp(kurirData.getNoKtp());
                    model.setNoTelp(kurirData.getNoTelp());
                    model.setNoPolisi(kurirData.getNoPolisi());
                    model.setPassword(kurirData.getPassword());
                    model.setFlag(kurirData.getFlag());
                    model.setAction(kurirData.getAction());
                    model.setCreatedDate(kurirData.getStCreatedDate());
                    model.setCreatedWho(kurirData.getCreatedWho());
                    model.setLastUpdate(kurirData.getStLastUpdate());
                    model.setLastUpdateWho(kurirData.getLastUpdateWho());

                    NotifikasiFcm notifikasiFcm = new NotifikasiFcm();
                    notifikasiFcm.setUserId(model.getIdKurir());
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
                            logger.error("[KurirController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                        }
                        logger.error("[KurirController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        throw new GeneralBOException(e);
                    }
                }
            } else {
                logger.info("User ID and Password not found.");
                model.setActionError("User ID and Password not found");
            }
        } else if (action.equalsIgnoreCase("saveAdd")) {

            Kurir kurir = new Kurir();
            kurir.setNama(nama);
            kurir.setNoTelp(noTelp);
            kurir.setNoPolisi(noPolisi);
            kurir.setNoKtp(noKtp);
            kurir.setPassword(password);
            kurir.setFlagReady(flagReady);
            kurir.setFlag("Y");
            kurir.setAction("C");
            kurir.setCreatedDate(now);
            kurir.setLastUpdate(now);
            kurir.setCreatedWho(nama);
            kurir.setLastUpdateWho(nama);
            kurir.setBranchId(branchId);

            try {
                kurirBoProxy.saveAdd(kurir);
            } catch (GeneralBOException e) {
                logger.error("[KurirController.saveAdd] Error when saving error,", e);
            }
        } else if (action.equalsIgnoreCase("getLatLon")) {
            Kurir kurir = new Kurir();

            kurir.setIdKurir(idKurir);
            kurir.setFlag("Y");

            List<Kurir> kurirList = new ArrayList<>();
            try {
                       kurirList = kurirBoProxy.getByCriteria(kurir);
            } catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = kurirBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[KurirController.getByCriteria] Error when saving error,", e1);
                }
                logger.error("[KurirController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            model.setLat(kurirList.get(0).getLat());
            model.setLon(kurirList.get(0).getLon());
        } else if  (action.equalsIgnoreCase("saveEditLoc")) {
            Kurir kurir = new Kurir();

            kurir.setIdKurir(idKurir);
            kurir.setFlag("Y");

            List<Kurir> kurirList = new ArrayList<>();
            try {
                kurirList = kurirBoProxy.getByCriteria(kurir);
            } catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = kurirBoProxy.saveErrorMessage(e.getMessage(), "PasienController.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[KurirController.getByCriteria] Error when saving error,", e1);
                }
                logger.error("[KurirController.getByCriteria] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            Kurir newKurir = kurirList.get(0);
            newKurir.setLat(lat);
            newKurir.setLon(lon);

            try {
                kurirBoProxy.saveEdit(newKurir);
            } catch (GeneralBOException e2) {
                logger.error("[KurirController.getByCriteria] Error when saving error,", e2);
            }
        }
        logger.info("[KurirController.create] end process POST /kurir >>>");
        return new DefaultHttpHeaders("success")
                .disableCaching();
    }



}

