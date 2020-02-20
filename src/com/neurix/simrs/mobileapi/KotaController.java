package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.model.Kota;
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
public class KotaController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(KotaController.class);
    private Kota model = new Kota();
    private Collection<Kota> listOfKota = new ArrayList<>();
    private ProvinsiBo provinsiBoProxy;

    private String term;
    private String provinsiId;
    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(Kota model) {
        this.model = model;
    }

    public Collection<Kota> getListOfKota() {
        return listOfKota;
    }

    public void setListOfKota(Collection<Kota> listOfKota) {
        this.listOfKota = listOfKota;
    }

    public ProvinsiBo getProvinsiBoProxy() {
        return provinsiBoProxy;
    }

    public void setProvinsiBoProxy(ProvinsiBo provinsiBoProxy) {
        this.provinsiBoProxy = provinsiBoProxy;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    @Override
    public Object getModel() {
        return (listOfKota != null ? listOfKota : model);
    }

    public HttpHeaders create() {
        logger.info("[KotaController.create] start process POST / <<<");

        try {
            listOfKota = provinsiBoProxy.getComboKotaWithCriteria(term, provinsiId);
        } catch (GeneralBOException e) {
            logger.error("[KotaController.create] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list kota, please info to your admin..." + e.getMessage());
        }

        logger.info("[KotaController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}

