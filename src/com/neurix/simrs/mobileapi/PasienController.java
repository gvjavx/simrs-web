package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Tuesday, 10/12/19 16:34
 */
public class PasienController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PasienController.class);
    private Pasien model = new Pasien();
    private Collection<Pasien> listOfPasien = new ArrayList<>();
    private PasienBo pasienBoProxy;

    private String term;
    private String tokenId;

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(Pasien model) {
        this.model = model;
    }

    public Collection<Pasien> getListOfPasien() {
        return listOfPasien;
    }

    public void setListOfPasien(Collection<Pasien> listOfPasien) {
        this.listOfPasien = listOfPasien;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public Object getModel() {
        return (listOfPasien != null ? listOfPasien : model);
    }

    public HttpHeaders create() {
        logger.info("[PasienController.create] start process POST / <<<");

        try {
           listOfPasien = pasienBoProxy.getListComboPasien(term);


        } catch (GeneralBOException e) {
            logger.error("[PasienController.create] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list pasien, please info to your admin..." + e.getMessage());
        }

        logger.info("[PasienController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
