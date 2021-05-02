package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.model.Desa;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Tuesday, 10/12/19 9:23
 */
public class DesaController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(DesaController.class);
    private Desa model = new Desa();
    private Collection<Desa> listOfDesa = new ArrayList<>();
    private ProvinsiBo provinsiBoProxy;

    private String term;
    private String kecamatanId;
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

    public void setModel(Desa model) {
        this.model = model;
    }

    public Collection<Desa> getListOfDesa() {
        return listOfDesa;
    }

    public void setListOfDesa(Collection<Desa> listOfDesa) {
        this.listOfDesa = listOfDesa;
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

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    @Override
    public Object getModel() {
        return (listOfDesa != null ? listOfDesa : model);
    }

    public HttpHeaders create() {
        logger.info("[DesaController.create] start process POST / <<<");

        try {
            listOfDesa = provinsiBoProxy.getComboDesaWithCriteria(term, kecamatanId);
        } catch (GeneralBOException e) {
            logger.error("[DesaController.create] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list desa, please info to your admin..." + e.getMessage());
        }

        logger.info("[DesaController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
