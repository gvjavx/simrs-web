package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.provinsi.bo.ProvinsiBo;
import com.neurix.hris.master.provinsi.model.Kecamatan;
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
public class KecamatanController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(KecamatanController.class);
    private Kecamatan model = new Kecamatan();
    private Collection<Kecamatan> listOfKecamatan = new ArrayList<>();
    private ProvinsiBo provinsiBoProxy;

    private String term;
    private String kotaId;
    private String tokenId;

    @Override
    public Object getModel() {
        return (listOfKecamatan != null ? listOfKecamatan : model);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(Kecamatan model) {
        this.model = model;
    }

    public Collection<Kecamatan> getListOfKecamatan() {
        return listOfKecamatan;
    }

    public void setListOfKecamatan(Collection<Kecamatan> listOfKecamatan) {
        this.listOfKecamatan = listOfKecamatan;
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

    public String getKotaId() {
        return kotaId;
    }

    public void setKotaId(String kotaId) {
        this.kotaId = kotaId;
    }

    public HttpHeaders create() {
        logger.info("[KecamatanController.create] start process POST / <<<");

        try {
            listOfKecamatan = provinsiBoProxy.getComboKecamatanWithCriteria(term, kotaId);
        } catch (GeneralBOException e) {
            logger.error("[KecamatanController.create] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list kecamatan, please info to your admin..." + e.getMessage());
        }

        logger.info("[KecamatanController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
