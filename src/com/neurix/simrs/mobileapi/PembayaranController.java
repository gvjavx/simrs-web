package com.neurix.simrs.mobileapi;

import com.neurix.akuntansi.master.pembayaran.bo.PembayaranBo;
import com.neurix.simrs.mobileapi.model.PembayaranMobile;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.HttpHeaders;

import java.util.Collection;

/**
 * @author gondok
 * Thursday, 11/06/20 11:01
 */
public class PembayaranController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PembayaranController.class);
    private PembayaranMobile model = new PembayaranMobile();
    private Collection<PembayaranMobile> listOfPembayaran;
    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public Object getModel() {
        return listOfPembayaran != null ? listOfPembayaran : model;
    }

    public void setModel(PembayaranMobile model) {
        this.model = model;
    }

    public Collection<PembayaranMobile> getListOfPembayaran() {
        return listOfPembayaran;
    }

    public void setListOfPembayaran(Collection<PembayaranMobile> listOfPembayaran) {
        this.listOfPembayaran = listOfPembayaran;
    }

    public VerifikatorPembayaranBo getVerifikatorPembayaranBoProxy() {
        return verifikatorPembayaranBoProxy;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

}
