package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.mobileapi.model.ProfilPayroll;
import com.neurix.hris.transaksi.payroll.bo.PayrollBo;
import com.neurix.hris.transaksi.payroll.model.Payroll;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class ProfilPayrollController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ProfilPayrollController.class);

    private BiodataBo biodataBoProxy;

    private ProfilPayroll model = new ProfilPayroll();
    private List<ProfilPayroll> listOfPayroll = new ArrayList<>();

    private String nip;

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[ProfilPayrollController.create] end process POST /activity <<<");

        List<Payroll> payroll = null;
        try {
            payroll = biodataBoProxy.searchPayrollSys(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "ProfilPayrollController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[ProfilPayrollController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[ProfilPayrollController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(payroll != null){
            ProfilPayroll returnPayroll;
            for(Payroll personal : payroll){
                returnPayroll = new ProfilPayroll();
                returnPayroll.setPayrollId(personal.getPayrollId());
                returnPayroll.setBranchId(personal.getBranchId());
                returnPayroll.setBranchName(personal.getBranchName());
                returnPayroll.setNip(personal.getNip());
                returnPayroll.setBulan(personal.getBulan());
                returnPayroll.setTahun(personal.getTahun());
                returnPayroll.setGajiBersih(personal.getTotalGajiBersih());
                returnPayroll.setFlagPayroll(personal.getFlagPayroll());
                returnPayroll.setFlagJaspord(personal.getFlagJasprod());
                returnPayroll.setFlagRapel(personal.getFlagRapel());
                returnPayroll.setFlagThr(personal.getFlagThr());
                returnPayroll.setFlagPensiun(personal.getFlagPensiun());
                returnPayroll.setFlagPendidikan(personal.getFlagPendidikan());
                returnPayroll.setFlagJubileum(personal.getFlagJubileum());
                returnPayroll.setFlagInsentif(personal.getFlagInsentif());
                listOfPayroll.add(returnPayroll);
            }
        }
        logger.info("[ProfilPayrollController.create] end process POST /activity <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public Object getModel() {
        return (listOfPayroll !=null ? listOfPayroll : model);
    }
}
