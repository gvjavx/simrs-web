package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.ProfilAbsensi;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

public class ProfilAbsensiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(ProfilAbsensiController.class);

    private AbsensiBo absensiBoProxy;

    private ProfilAbsensi model = new ProfilAbsensi();
    private List<ProfilAbsensi> listOfAbsensi = new ArrayList<>();

    private String nip;

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[AbsensiController.create] end process POST /activity <<<");

        AbsensiPegawai searchAbsensi = new AbsensiPegawai();
        searchAbsensi.setNip(nip);
        searchAbsensi.setFlag("Y");

        List<AbsensiPegawai> listAbsensi = null;
        try {
            listAbsensi = absensiBoProxy.getByCriteria(searchAbsensi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBoProxy.saveErrorMessage(e.getMessage(), "AbsensiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[AbsensiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[AbsensiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }



        if(listAbsensi != null){
            ProfilAbsensi returnAbsensi;
            for(AbsensiPegawai personal : listAbsensi){
                returnAbsensi = new ProfilAbsensi();
                returnAbsensi.setNip(personal.getNip());
                returnAbsensi.setNamaPegawai(personal.getNama());
                returnAbsensi.setJabatan(personal.getJabatan());
                returnAbsensi.setUnit(personal.getUnit());
                returnAbsensi.setStatus(personal.getStatusName());
                returnAbsensi.setLembur(personal.getLembur().equals("N") ? "Tidak" : "Ya");
                returnAbsensi.setIjin(personal.getIjin().equals("N") ? "Tidak" : "Ya");
                returnAbsensi.setTanggal(personal.getStTanggal());
                listOfAbsensi.add(returnAbsensi);
            }
        }
        logger.info("[AbsensiController.create] end process POST /activity <<<");
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
        return (listOfAbsensi !=null ? listOfAbsensi : model);
    }
}
