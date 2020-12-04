package com.neurix.simrs.mobileapi.antrian;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AntrianController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AntrianController.class);
    private Collection<HeaderCheckup> listOfAntrian = null;
    private HeaderCheckup model;
    private CheckupBo checkupBoProxy;

    private String branch;
    private String poli;

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[AntrianController.create] end process POST /historycuti <<<");

        List<HeaderCheckup> headerCheckupList = null;

        try {
            headerCheckupList = checkupBoProxy.getListAntrian(branch, "'"+poli+"'");
        } catch (GeneralBOException e) {
            logger.error("[AntrianController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + e + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if (headerCheckupList.size() > 0) {

            listOfAntrian = new ArrayList<>();
            HeaderCheckup checkup;
            for (HeaderCheckup entity : headerCheckupList) {
                checkup = new HeaderCheckup();
                checkup.setIdPasien(entity.getIdPasien());
                checkup.setNama(entity.getNama());
                checkup.setNamaDesa(entity.getNamaDesa());
                checkup.setNamaKecamatan(entity.getNamaKecamatan());
                checkup.setNamaPelayanan(entity.getNamaPelayanan());
                checkup.setNoCheckup(entity.getNoCheckup());
                checkup.setIdDetailCheckup(entity.getIdDetailCheckup());
                checkup.setNoAntrian(Integer.valueOf(entity.getStNoAntrian()));
                checkup.setBelumBayarUangMuka(entity.getBelumBayarUangMuka());
                checkup.setStatusBayar(entity.getStatusBayar());
                checkup.setIdJenisPeriksaPasien(entity.getStatusBayar());
                listOfAntrian.add(checkup);
            }
        }

        logger.info("[AntrianController.create] end process POST /historycuti <<<");

        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public Object getModel() {
        return (listOfAntrian == null ? model : listOfAntrian);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }
}
