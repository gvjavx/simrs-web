package com.neurix.simrs.mobileapi;

import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.mobileapi.model.RuanganMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

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

    private String idKelasRuangan;
    private String namaKelasRuangan;

    private String action;

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
        return  (listOfRuangan != null ? listOfRuangan : model);
    }

    public HttpHeaders create() {
        logger.info("[RuanganController.create] start process POST / <<<");


        logger.info("[RuanganController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
