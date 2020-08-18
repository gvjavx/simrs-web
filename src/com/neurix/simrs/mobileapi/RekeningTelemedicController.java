package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.telemedic.bo.RekeningTelemedicBo;
import com.neurix.simrs.master.telemedic.model.RekeningTelemedic;
import com.neurix.simrs.mobileapi.model.RekeningTelemedicMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Monday, 03/08/20 11:30
 */
public class RekeningTelemedicController implements ModelDriven<Object> {
    private static transient Logger logger = Logger.getLogger(RekeningTelemedicController.class);
    private RekeningTelemedicBo rekeningTelemedicBoProxy;
    private RekeningTelemedicMobile model = new RekeningTelemedicMobile();
    private Collection<RekeningTelemedicMobile> listOfRekeningMobile;

    String pembayaranId;
    String pembayaranName;
    String noRekening;
    String namaRekening;
    String coa;
    String branchId;

    String action;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public RekeningTelemedicBo getRekeningTelemedicBoProxy() {
        return rekeningTelemedicBoProxy;
    }

    public void setModel(RekeningTelemedicMobile model) {
        this.model = model;
    }

    public Collection<RekeningTelemedicMobile> getListOfRekeningMobile() {
        return listOfRekeningMobile;
    }

    public void setListOfRekeningMobile(Collection<RekeningTelemedicMobile> listOfRekeningMobile) {
        this.listOfRekeningMobile = listOfRekeningMobile;
    }

    public String getPembayaranId() {
        return pembayaranId;
    }

    public void setPembayaranId(String pembayaranId) {
        this.pembayaranId = pembayaranId;
    }

    public String getPembayaranName() {
        return pembayaranName;
    }

    public void setPembayaranName(String pembayaranName) {
        this.pembayaranName = pembayaranName;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getCoa() {
        return coa;
    }

    public void setCoa(String coa) {
        this.coa = coa;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RekeningTelemedicController.logger = logger;
    }

    public void setRekeningTelemedicBoProxy(RekeningTelemedicBo rekeningTelemedicBoProxy) {
        this.rekeningTelemedicBoProxy = rekeningTelemedicBoProxy;
    }

    @Override
    public Object getModel() {
        return listOfRekeningMobile != null ? listOfRekeningMobile : model;
    }

    public HttpHeaders create() {
        logger.info("[RekeningTelemedicController.create] start process POST /rekening >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("getByCriteria")) {

            RekeningTelemedic bean = new RekeningTelemedic();
            bean.setBranchId(branchId);
            bean.setCoa(coa);
            bean.setPembayaranId(pembayaranId);

            List<RekeningTelemedic> result = new ArrayList<>();
            listOfRekeningMobile = new ArrayList<>();

            try {
                result = rekeningTelemedicBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[RekeningTelemedicController.getByCriteria] Error when getByCriteria", e);
            }

            if (result.size() > 0){
                for (RekeningTelemedic item : result){
                    RekeningTelemedicMobile rekeningTelemedicMobile = new RekeningTelemedicMobile();
                    rekeningTelemedicMobile.setBranchId(item.getBranchId());
                    rekeningTelemedicMobile.setIdRekening(item.getIdRekening());
                    rekeningTelemedicMobile.setPembayaranId(item.getPembayaranId());
                    rekeningTelemedicMobile.setPembayaranName(item.getPembayaranName());
                    rekeningTelemedicMobile.setNoRekening(item.getNoRekening());
                    rekeningTelemedicMobile.setNamaRekening(item.getNamaRekening());
                    rekeningTelemedicMobile.setCoa(item.getCoa());

                    listOfRekeningMobile.add(rekeningTelemedicMobile);
                }
            }

        }
        if (action.equalsIgnoreCase("saveAdd")){

            RekeningTelemedic rekeningTelemedic = new RekeningTelemedic();
            rekeningTelemedic.setPembayaranId(pembayaranId);
            rekeningTelemedic.setPembayaranName(pembayaranName);
            rekeningTelemedic.setNoRekening(noRekening);
            rekeningTelemedic.setNamaRekening(namaRekening);
            rekeningTelemedic.setCoa(coa);
            rekeningTelemedic.setBranchId(branchId);
            rekeningTelemedic.setCreatedDate(now);
            rekeningTelemedic.setLastUpdate(now);
            rekeningTelemedic.setCreatedWho("Admin");
            rekeningTelemedic.setLastUpdateWho("Admin");

            try {
                rekeningTelemedicBoProxy.saveAdd(rekeningTelemedic);
            } catch (GeneralBOException e){
                logger.error("[RekeningTelemedicController.saveAdd] Error when saving error,", e);
            }
        }

        logger.info("[RekeningTelemedicController.create] end process POST /rekening >>>");
        return new DefaultHttpHeaders("success").disableCaching();
    }
}
