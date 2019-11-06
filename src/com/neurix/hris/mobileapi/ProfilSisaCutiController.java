package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.ProfilSisaCuti;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class ProfilSisaCutiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ProfilSisaCutiController.class);

    private CutiPegawaiBo cutiPegawaiBoProxy;

    private ProfilSisaCuti model = new ProfilSisaCuti();
    private List<ProfilSisaCuti> listOfSisaCuti = new ArrayList<>();

    private String nip;

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[SisaCutiController.create] end process POST /activity <<<");

        List<CutiPegawai> cutiPegawai = null;
        try {
            cutiPegawai = cutiPegawaiBoProxy.sisaCutiSys(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "SisaCutiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }



        if(cutiPegawai != null){
            ProfilSisaCuti returnSisaCuti;
            for(CutiPegawai personal : cutiPegawai){
                returnSisaCuti = new ProfilSisaCuti();
                returnSisaCuti.setNip(personal.getNip());
                returnSisaCuti.setNamaCuti(personal.getCutiName());
                returnSisaCuti.setSisaCuti(personal.getSisaCutiHari().toString());
                returnSisaCuti.setCutiId(personal.getCutiId());
                listOfSisaCuti.add(returnSisaCuti);
            }
        }
        logger.info("[SisaCutiController.create] end process POST /activity <<<");
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
        return (listOfSisaCuti !=null ? listOfSisaCuti : model);
    }
}
