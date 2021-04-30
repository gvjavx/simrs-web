package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.model.Provinsi;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Tuesday, 10/12/19 9:22
 */
public class ProvinsiController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(ProvinsiController.class);
    private Provinsi model = new Provinsi();
    private Collection<Provinsi> listOfProvinsi = new ArrayList<>();
    private ProvinsiBo provinsiBoProxy;

    private String term;
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(Provinsi model) {
        this.model = model;
    }

    public Collection<Provinsi> getListOfProvinsi() {
        return listOfProvinsi;
    }

    public void setListOfProvinsi(Collection<Provinsi> listOfProvinsi) {
        this.listOfProvinsi = listOfProvinsi;
    }

    public ProvinsiBo getProvinsiBoProxy() {
        return provinsiBoProxy;
    }

    public void setProvinsiBoProxy(ProvinsiBo provinsiBoProxy) {
        this.provinsiBoProxy = provinsiBoProxy;
    }

    @Override
    public Object getModel() {
        return (listOfProvinsi != null ? listOfProvinsi : model);
    }

    public HttpHeaders create() {
        logger.info("[ProvinsiController.create] start process POST / <<<");

        try {
          listOfProvinsi = provinsiBoProxy.getComboProvinsiWithCriteria(term);
        } catch (GeneralBOException e) {
            logger.error("[ProvinsiController.create] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list provinsi, please info to your admin..." + e.getMessage());
        }

        logger.info("[ProvinsiController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
