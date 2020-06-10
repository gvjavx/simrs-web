package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.mobileapi.model.DokterMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Thursday, 23/01/20 14:40
 */
public class DokterController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(DokterController.class);
    private DokterMobile model = new DokterMobile();
    private Collection<DokterMobile> listOfDokter;
    private DokterBo dokterBoProxy;

    private String idDokter;
    private String namaDokter;
    private String idSpesialis;
    private String namaSpesialis;
    private String action;
    private String kuota;
    private String lat;
    private String lon;
    private String flagCall;
    private String flagTele;

    private String idPelayanan;
    private String branchId;

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

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

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

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(DokterMobile model) {
        this.model = model;
    }

    public Collection<DokterMobile> getListOfDokter() {
        return listOfDokter;
    }

    public void setListOfDokter(Collection<DokterMobile> listOfDokter) {
        this.listOfDokter = listOfDokter;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNamaSpesialis() {
        return namaSpesialis;
    }

    public void setNamaSpesialis(String namaSpesialis) {
        this.namaSpesialis = namaSpesialis;
    }

    public String getFlagTele() {
        return flagTele;
    }

    public void setFlagTele(String flagTele) {
        this.flagTele = flagTele;
    }

    @Override
    public Object getModel() {
        return (listOfDokter != null ? listOfDokter : model);
    }

    public HttpHeaders create() {
        logger.info("[DokterController.create] start process POST / <<<");

        List<Dokter> result = new ArrayList<>();

        Dokter bean = new Dokter();
        bean.setIdDokter(idDokter);
        bean.setIdSpesialis(idSpesialis);
        bean.setNamaDokter(namaDokter);
        bean.setNamaSpesialis(namaSpesialis);

        if (action.equalsIgnoreCase("get")) {

            try {
                result = dokterBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }

            model.setIdDokter(result.get(0).getIdDokter());
            model.setIdSpesialis(result.get(0).getIdSpesialis());
            model.setNamaDokter(result.get(0).getNamaDokter());
            model.setNamaSpesialis(result.get(0).getNamaSpesialis());
            model.setKuota(result.get(0).getKuota());
            model.setLat(result.get(0).getLat());
            model.setLon(result.get(0).getLon());
            model.setFlagCall(result.get(0).getFlagCall());
        }

        if (action.equalsIgnoreCase("kuota")) {

            try {
                dokterBoProxy.editKuota(idDokter, kuota);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveEditLoc")){
            try {
                dokterBoProxy.editLatLon(idDokter, lat, lon);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("editFlagCall")) {
            try {
                dokterBoProxy.editFlagCall(idDokter, flagCall);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("editFlagTele")) {
            try {
                dokterBoProxy.editFlagTele(idDokter, flagTele);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getDokterByPelayanan")) {
            listOfDokter = new ArrayList<>();
            try {
               result = dokterBoProxy.getDokterByPelayanan(idPelayanan);

               for (Dokter item : result ){
                   DokterMobile dokterMobile = new DokterMobile();
                   dokterMobile.setIdDokter(item.getIdDokter());
                   dokterMobile.setNamaDokter(item.getNamaDokter());
                   dokterMobile.setKuota(item.getKuota());
                   dokterMobile.setFlagTele(item.getFlagTele());

                   listOfDokter.add(dokterMobile);
               }
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        }

        logger.info("[DokterController.create] end process POST / <<<");

        return new DefaultHttpHeaders("create").disableCaching();

    }
}
