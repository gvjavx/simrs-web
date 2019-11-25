package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author gondok
 * Friday, 22/11/19 14:35
 */
public class PelayananController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(PelayananController.class);
    private Pelayanan model = new Pelayanan();
    private PelayananBo pelayananBoProxy;
    private Collection<Pelayanan> listOfPelayanan = new ArrayList<>();

    private String idPelayanan;
    private String namaPelayanan;
    private String flag;
    private String action;

    public static Logger getLogger() {
        return logger;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public Collection<Pelayanan> getListOfPelayanan() {
        return listOfPelayanan;
    }

    public void setListOfPelayanan(Collection<Pelayanan> listOfPelayanan) {
        this.listOfPelayanan = listOfPelayanan;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public String getNamaPelayanan() {
        return namaPelayanan;
    }

    public void setNamaPelayanan(String namaPelayanan) {
        this.namaPelayanan = namaPelayanan;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public Object getModel() {
        return (listOfPelayanan != null ? listOfPelayanan : model);
    }

    public HttpHeaders create() {
        logger.info("[PelayananController.create] start process POST / <<<");

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setIdPelayanan(idPelayanan);
        pelayanan.setNamaPelayanan(namaPelayanan);

        if (action.equalsIgnoreCase("show")) {
            try {
                listOfPelayanan = pelayananBoProxy.getListAllPelayanan();
            } catch (GeneralBOException e) {
//                Long logId = null;
//                try {
//                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "registrasi online index");
//                } catch (GeneralBOException el) {
//
//                }
            }
        }

        logger.info("[PelayananController.create] end process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();

    }
}
