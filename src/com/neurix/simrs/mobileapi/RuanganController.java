package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.mobileapi.model.KelasRuanganMobile;
import com.neurix.simrs.mobileapi.model.RuanganMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Monday, 16/12/19 12:44
 */
public class RuanganController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(RuanganController.class);
    private RuanganMobile model = new RuanganMobile();
    private RuanganBo ruanganBoProxy;
    private KelasRuanganBo kelasRuanganBoProxy;
    private Collection<RuanganMobile> listOfRuangan = new ArrayList<RuanganMobile>();
    private Collection<KelasRuanganMobile> listOfKelasRuangan = new ArrayList<>();

    private String idKelasRuangan;
    private String namaKelasRuangan;

    private String idRuangan;
    private String namaRuangan;

    private String action;
    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Collection<KelasRuanganMobile> getListOfKelasRuangan() {
        return listOfKelasRuangan;
    }

    public void setListOfKelasRuangan(Collection<KelasRuanganMobile> listOfKelasRuangan) {
        this.listOfKelasRuangan = listOfKelasRuangan;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdKelasRuangan() {
        return idKelasRuangan;
    }

    public void setIdKelasRuangan(String idKelasRuangan) {
        this.idKelasRuangan = idKelasRuangan;
    }

    public String getNamaKelasRuangan() {
        return namaKelasRuangan;
    }

    public void setNamaKelasRuangan(String namaKelasRuangan) {
        this.namaKelasRuangan = namaKelasRuangan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(RuanganMobile model) {
        this.model = model;
    }

    public RuanganBo getRuanganBoProxy() {
        return ruanganBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public KelasRuanganBo getKelasRuanganBoProxy() {
        return kelasRuanganBoProxy;
    }

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public Collection<RuanganMobile> getListOfRuangan() {
        return listOfRuangan;
    }

    public void setListOfRuangan(Collection<RuanganMobile> listOfRuangan) {
        this.listOfRuangan = listOfRuangan;
    }

    @Override
    public Object getModel() {
        switch (action){
            case "getKelasRuangan":
                return listOfKelasRuangan;
            case "getRuangan":
                return listOfRuangan;
            default: return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[RuanganController.create] start process POST / <<<");

        if (action.equalsIgnoreCase("getKelasRuangan")){
            List<KelasRuangan> result = new ArrayList<>();

            KelasRuangan kelasRuangan = new KelasRuangan();

            try {
                result = kelasRuanganBoProxy.getByCriteria(kelasRuangan);
            } catch (GeneralBOException e){
                logger.error("[RuanganController.create] Error, " + e.getMessage());
            }

            for (KelasRuangan item : result){
                KelasRuanganMobile kelasRuanganMobile = new KelasRuanganMobile();
                kelasRuanganMobile.setIdKelasRuangan(item.getIdKelasRuangan());
                kelasRuanganMobile.setNamaKelasRuangan(item.getNamaKelasRuangan());

                listOfKelasRuangan.add(kelasRuanganMobile);
            }
        }

        if (action.equalsIgnoreCase("getRuangan")){
            List<Ruangan> result = new ArrayList<>();

            Ruangan ruangan = new Ruangan();
            ruangan.setIdKelasRuangan(idKelasRuangan);
            ruangan.setBranchId(branchId);

            try{
                result = ruanganBoProxy.getByCriteria(ruangan);
            } catch (GeneralBOException e){
                logger.error("[RuanganController.create] Error, " + e.getMessage());
            }

            for (Ruangan item : result){
                RuanganMobile ruanganMobile = new RuanganMobile();
                ruanganMobile.setIdKelasRuangan(item.getIdKelasRuangan());
                ruanganMobile.setNamaKelasRuangan(item.getNamaKelasRuangan());
                ruanganMobile.setNamaRuangan(item.getNamaRuangan());
                ruanganMobile.setIdRuangan(item.getIdRuangan());
                ruanganMobile.setNoRuangan(item.getNoRuangan());
                ruanganMobile.setBranchId(item.getBranchId());

                listOfRuangan.add(ruanganMobile);
            }
        }


        logger.info("[RuanganController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
