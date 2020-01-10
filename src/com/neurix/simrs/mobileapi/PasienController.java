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
    private String id;

    public HttpHeaders create(){
        logger.info("[PasienController.create] start process POST /loginpasien >>>");

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

            if (!pasienList.isEmpty() && pasienList.size() > 0)
            {
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

                pasienList = new ArrayList<>();
                try {
                    pasienList = pasienBoProxy.getDataPasien(pasienData.getDesaId());
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

                if (!pasienList.isEmpty() && pasienList.size() > 0) {
                    pasienData = new com.neurix.simrs.master.pasien.model.Pasien();
                    pasienData = pasienList.get(0);

                    model.setProvinsiId(pasienData.getProvinsi());
                    model.setKotaId(pasienData.getKota());
                    model.setKecamatanId(pasienData.getKecamatan());
                    model.setDesa(pasienData.getDesa());
                }

                NotifikasiFcm notifikasiFcm = new NotifikasiFcm();
                notifikasiFcm.setUserId(model.getIdPasien());
                notifikasiFcm.setUserName(model.getNama());
                notifikasiFcm.setTokenFcm("");
                notifikasiFcm.setTokenExpo(tokenExpo == null ? "" : tokenExpo);
                notifikasiFcm.setLastUpdateWho(model.getNama());
                notifikasiFcm.setCreatedWho(model.getNama());
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
            }
        } else {
            logger.info("User ID and Password not found.");
            throw new GeneralBOException("User ID and Password not found.");
        }

        logger.info("[PasienController.create] end process POST /loginpasien <<<");
        return new DefaultHttpHeaders("success")
                .disableCaching();
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


}
