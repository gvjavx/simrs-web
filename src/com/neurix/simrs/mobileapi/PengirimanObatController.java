package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.mobileapi.model.PengirimanObatMobile;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

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

    private String action;
    private String idKurir;
    private String idPasien;
    private String idTele;
    private String idPengirimanObat;

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

                listOfPengirimanObat.add(pengirimanObatMobile);
            }

        }

        if  (action.equalsIgnoreCase("updateFlagDiterima")) {

            List<PengirimanObat> result = new ArrayList<>();

            PengirimanObat bean = new PengirimanObat();
            bean.setIdPasien(idPasien);
            bean.setId(idPengirimanObat);

            try{
               result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setFlagDiterimaPasien("Y");
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
                model.setMessage("Success");
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

            try{
                result = telemedicBoProxy.getPengirimanByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[PengirimanObatController.create] ERROR. ", e);
                throw new GeneralBOException("[PengirimanObatController.create] ERROR. ", e);
            }

            PengirimanObat newPengirimanObat = result.get(0);
            newPengirimanObat.setFlagTerkirim("Y");
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


        logger.info("[PengirimanObatController.create] END >>>");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
