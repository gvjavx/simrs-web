package com.neurix.simrs.mobileapi;

import com.neurix.simrs.transaksi.antrianonline.bo.AntrianOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Thursday, 21/11/19 12:32
 */
public class AntrianOnlineController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AntrianOnlineController.class);
    private AntianOnline model = new AntianOnline();
    private Collection<AntianOnline> listOfAntrianOnline = new ArrayList<>();
    private AntrianOnlineBo antrianOnlineBoProxy;

    private String idAntrianOnline;
    private String noCheckupOnline;
    private String idPelayanan;
    private String idDokter;
    private String action;

    public Collection<AntianOnline> getListOfAntrianOnline() {
        return listOfAntrianOnline;
    }

    public void setListOfAntrianOnline(Collection<AntianOnline> listOfAntrianOnline) {
        this.listOfAntrianOnline = listOfAntrianOnline;
    }

    public String getIdAntrianOnline() {
        return idAntrianOnline;
    }

    public void setIdAntrianOnline(String idAntrianOnline) {
        this.idAntrianOnline = idAntrianOnline;
    }

    public String getNoCheckupOnline() {
        return noCheckupOnline;
    }

    public void setNoCheckupOnline(String noCheckupOnline) {
        this.noCheckupOnline = noCheckupOnline;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(AntianOnline model) {
        this.model = model;
    }

    public AntrianOnlineBo getAntrianOnlineBoProxy() {
        return antrianOnlineBoProxy;
    }

    public void setAntrianOnlineBoProxy(AntrianOnlineBo antrianOnlineBoProxy) {
        this.antrianOnlineBoProxy = antrianOnlineBoProxy;
    }

    @Override
    public Object getModel() {
       return (listOfAntrianOnline != null ? listOfAntrianOnline : model);
    }

    public HttpHeaders create() {
        logger.info("[AntrianOnlineController.create] start process POST / <<<");

        logger.info("[AntrianOnlineController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
