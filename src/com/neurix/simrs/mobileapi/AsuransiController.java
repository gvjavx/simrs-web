package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.mobileapi.model.AsuransiMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Friday, 26/06/20 20:21
 */
public class AsuransiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(AsuransiController.class);
    private AsuransiMobile model = new AsuransiMobile();
    private Collection<AsuransiMobile> listOfAsuransiMobile;
    private AsuransiBo asuransiBoProxy;

    private String action;

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(AsuransiMobile model) {
        this.model = model;
    }

    public Collection<AsuransiMobile> getListOfAsuransiMobile() {
        return listOfAsuransiMobile;
    }

    public void setListOfAsuransiMobile(Collection<AsuransiMobile> listOfAsuransiMobile) {
        this.listOfAsuransiMobile = listOfAsuransiMobile;
    }

    public AsuransiBo getAsuransiBoProxy() {
        return asuransiBoProxy;
    }

    public void setAsuransiBoProxy(AsuransiBo asuransiBoProxy) {
        this.asuransiBoProxy = asuransiBoProxy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Object getModel() {
        return listOfAsuransiMobile != null ? listOfAsuransiMobile : model;
    }

    public HttpHeaders create() {
        logger.info("[AsuransiController.create] start process POST / <<<");

        List<Asuransi> result = new ArrayList();
        listOfAsuransiMobile = new ArrayList<>();

        Asuransi bean = new Asuransi();
        bean.setFlag("Y");

        try {
           result = asuransiBoProxy.getByCriteria(bean);
        } catch (GeneralBOException e) {
            logger.error("[AsuransiController.create] Error, " + e.getMessage());
        }

        if (result.size() > 0) {
            for (Asuransi item : result) {
                AsuransiMobile asuransiMobile = new AsuransiMobile();
                asuransiMobile.setIdAsuransi(item.getIdAsuransi());
                asuransiMobile.setNamaAsuransi(item.getNamaAsuransi());

                listOfAsuransiMobile.add(asuransiMobile);
            }
        }

        logger.info("[AsuransiController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
