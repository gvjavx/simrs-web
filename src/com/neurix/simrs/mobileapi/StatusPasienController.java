package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.bo.StatusPasienBo;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.mobileapi.model.StatusPasienMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Friday, 24/01/20 10:20
 */
public class StatusPasienController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(StatusPasienController.class);
    private StatusPasienMobile model = new StatusPasienMobile();
    private Collection<StatusPasienMobile> listOfStatusPasien = new ArrayList<>();
    private StatusPasienBo statusPasienBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(StatusPasienMobile model) {
        this.model = model;
    }

    public Collection<StatusPasienMobile> getListOfStatusPasien() {
        return listOfStatusPasien;
    }

    public void setListOfStatusPasien(Collection<StatusPasienMobile> listOfStatusPasien) {
        this.listOfStatusPasien = listOfStatusPasien;
    }

    public StatusPasienBo getStatusPasienBoProxy() {
        return statusPasienBoProxy;
    }

    public void setStatusPasienBoProxy(StatusPasienBo statusPasienBoProxy) {
        this.statusPasienBoProxy = statusPasienBoProxy;
    }

    @Override
    public Object getModel() {
        return (listOfStatusPasien != null ? listOfStatusPasien : model);
    }

    public HttpHeaders index() {
        logger.info("[StatusPasienController.index] start process GET / <<<");

        List<ImSimrsStatusPasienEntity> result = new ArrayList<>();

        try {
           result = statusPasienBoProxy.getListEntityStatusPasien(new StatusPasien());
        } catch (GeneralBOException e) {
            logger.error("[StatusPasienController.index] Error, " + e.getMessage());
        }

        if (result != null && result.size() > 0) {
            for (ImSimrsStatusPasienEntity item : result) {
                StatusPasienMobile statusPasienMobile = new StatusPasienMobile();
                statusPasienMobile.setIdStatusPasien(item.getIdStatusPasien());
                statusPasienMobile.setKeterangan(item.getKeterangan());

                listOfStatusPasien.add(statusPasienMobile);
            }
        }

        logger.info("[StatusPasienController.index] end process GET / <<<");
        return new DefaultHttpHeaders("index").disableCaching();

    }
}
