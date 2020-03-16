package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.mobileapi.model.Biodata;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gondok
 * Saturday, 23/02/19 16:21
 */

public class BiodataController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(BiodataController.class);

    private Biodata model;
    private List<Biodata> listOfBiodata = null;
    private BiodataBo biodataBoProxy;

    private String id;
    private String query;
    private String branchId;

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[BiodataController.create] end process POST /biodata/{id} <<<");

        List<com.neurix.hris.master.biodata.model.Biodata> listModelBiodata = null;
        try {
            listModelBiodata = biodataBoProxy.findBiodataLikeNama(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        listOfBiodata = new ArrayList<>();

        if(listModelBiodata != null) {
            for(com.neurix.hris.master.biodata.model.Biodata bio : listModelBiodata) {
                Biodata biodata = new Biodata();
                biodata.setNip(bio.getNip());
                biodata.setNamaPegawai(bio.getNamaPegawai());

                listOfBiodata.add(biodata);
            }
        }

        logger.info("[BiodataController.update] end process POST /biodata/{id} <<<");

        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders find() {
        logger.info("[BiodataController.find] end process POST /biodata/{id}/find <<<");

        com.neurix.hris.master.biodata.model.Biodata biodata = null;
        try {
            biodata = biodataBoProxy.detailBiodataSys(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BiodataController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(biodata != null) {
            model = new Biodata();
            model.setNip(biodata.getNip());
            model.setNamaPegawai(biodata.getNamaPegawai());
            model.setUnitId(biodata.getBranch());
            model.setUnit(biodata.getBranchName());
            model.setJabatanId(biodata.getPositionId());
            model.setJabatan(biodata.getPositionName());
            model.setBidangId(biodata.getDivisi());
            model.setBidang(biodata.getDivisiName());
            model.setGolongan(biodata.getGolonganName());
            model.setGolonganId(biodata.getGolonganId());
            model.setTipePegawaiId(biodata.getTipePegawai());
            model.setTipePegawai(biodata.getTipePegawaiName());
        }

        logger.info("[BiodataController.find] end process POST /biodata/{id}/find <<<");

        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public Object getModel() {
        return (listOfBiodata == null ? model : listOfBiodata);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
