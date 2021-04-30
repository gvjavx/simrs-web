package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.ijin.bo.IjinBo;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.neurix.hris.mobileapi.model.PengajuanIjin;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 20/02/19 14:40
 */
public class IjinFormPegawaiController {
    /*
        implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(IjinFormPegawaiController.class);

    private BiodataBo biodataBoProxy;
    private IjinBo ijinBoProxy;
    private List<PengajuanIjin> listOfPengajuanIjin;
    private PengajuanIjin model = new PengajuanIjin();

    private String id;

    private String nip;
    private String statusApprove;

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public void setIjinBoProxy(IjinBo ijinBoProxy) {
        this.ijinBoProxy = ijinBoProxy;
    }

    public HttpHeaders create() {

        logger.info("[IjinFormPegawaiController.create] end process POST /pengajuancuti <<<");

        com.neurix.hris.master.biodata.model.Biodata modelBiodata = null;
        try {
            modelBiodata = biodataBoProxy.detailBiodataSys(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "BiodataBoImpl.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataBoImpl.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[BiodataBoImpl.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }


        if(modelBiodata != null){
            model = new PengajuanIjin();

        }


        logger.info("[IjinFormPegawaiController.create] end process POST /pengajuancuti <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders update() {
        logger.info("[IjinFormPegawaiController.update] end process POST /pengajuancuti/{id} <<<");

        try {

            CutiPegawai cutiPegawai = new CutiPegawai();
            cutiPegawai.setNip(model.getNip());
            cutiPegawai.setPegawaiPenggantiSementara(model.getNikPegawaiPengganti());
            cutiPegawai.setCutiId(model.getCutiId());
            cutiPegawai.setDivisiId(model.getBidangId());
            cutiPegawai.setUnitId(model.getUnitId());
            cutiPegawai.setPosisiId(model.getJabatanId());
            cutiPegawai.setCutiName(model.getTypeHariCuti());
            cutiPegawai.setNamaPegawai(model.getNamaPegawai());
            cutiPegawai.setAlamatCuti(model.getAlamatCuti());
            cutiPegawai.setKeterangan(model.getKeterangan());
            cutiPegawai.setStTanggalDari(model.getStTanggalAwalCuti());
            cutiPegawai.setStTanggalSelesai(model.getStTanggalSelesaiCuti());
            cutiPegawai.setTanggalDari(CommonUtil.convertToDate(model.getStTanggalAwalCuti()));
            cutiPegawai.setTanggalSelesai(CommonUtil.convertToDate(model.getStTanggalSelesaiCuti()));
            cutiPegawai.setApprovalFlag("-");
            cutiPegawai.setSelf("");
            cutiPegawai.setLamaHariCuti(model.getLamaCuti());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("C");
            cutiPegawai.setCreatedWho(model.getNamaPegawai());
            cutiPegawai.setLastUpdateWho(model.getNamaPegawai());
            cutiPegawai.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            cutiPegawai.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

            cutiPegawaiBoProxy.saveAdd(cutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        logger.info("[IjinFormPegawaiController.update] end process POST /pengajuancuti/{id} <<<");

        return new DefaultHttpHeaders("update").disableCaching();
    }

    public String status() {
        logger.info("[IjinFormPegawaiController.update] end process POST /pengajuancuti/{nip}/status <<<");
        setNip(getId());

        CutiPegawai cutiPegawai = new CutiPegawai();
        cutiPegawai.setNip(nip);
        cutiPegawai.setForMobile(true);
        cutiPegawai.setFlag("Y");

        List<CutiPegawai> listOfCuti = null;

        try {
            listOfCuti = cutiPegawaiBoProxy.getByCriteria(cutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[IjinFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(statusApprove.equals("Y")) {
            statusApprove = "Y";
        } else {
            statusApprove = "N";
        }

        if(listOfCuti != null){
            listOfPengajuanIjin = new ArrayList<>();
            for(CutiPegawai modelCuti : listOfCuti){
                if(modelCuti.getApprovalFlag() == null) {
                    PengajuanIjin model = new PengajuanIjin();
                    model.setNip(modelCuti.getNip());
                    model.setUnit(modelCuti.getUnitName());
                    model.setNamaPegawai(modelCuti.getNamaPegawai());
                    model.setBidang(modelCuti.getDivisiName());
                    model.setCutiId(modelCuti.getCutiId());
                    model.setJenisCuti(modelCuti.getCutiName());
                    model.setSisaCuti(modelCuti.getSisaHariCuti());
                    model.setStTanggalAwalCuti(modelCuti.getTanggalDari().toString());
                    model.setStTanggalSelesaiCuti(modelCuti.getTanggalSelesai().toString());
                    model.setKeterangan(modelCuti.getKeterangan());
                    model.setAlamatCuti(modelCuti.getAlamatCuti());
                    model.setStatusApprove("Waiting");

//                    listOfPengajuanIjin.add(model);
                } else if(modelCuti.getApprovalFlag().equals("Y") && statusApprove.equals("Y")){

                    PengajuanIjin model = new PengajuanIjin();
                    model.setNip(modelCuti.getNip());
                    model.setUnit(modelCuti.getUnitName());
                    model.setNamaPegawai(modelCuti.getNamaPegawai());
                    model.setBidang(modelCuti.getDivisiName());
                    model.setCutiId(modelCuti.getCutiId());
                    model.setJenisCuti(modelCuti.getCutiName());
                    model.setSisaCuti(modelCuti.getSisaHariCuti());
                    model.setStTanggalAwalCuti(modelCuti.getTanggalDari().toString());
                    model.setStTanggalSelesaiCuti(modelCuti.getTanggalSelesai().toString());
                    model.setKeterangan(modelCuti.getKeterangan());
                    model.setAlamatCuti(modelCuti.getAlamatCuti());
                    model.setStatusApprove("Approved");

                    listOfPengajuanIjin.add(model);
                } else if (modelCuti.getApprovalFlag().equals("N") && statusApprove.equals("N")){
                    PengajuanIjin model = new PengajuanIjin();
                    model.setNip(modelCuti.getNip());
                    model.setUnit(modelCuti.getUnitName());
                    model.setNamaPegawai(modelCuti.getNamaPegawai());
                    model.setBidang(modelCuti.getDivisiName());
                    model.setCutiId(modelCuti.getCutiId());
                    model.setJenisCuti(modelCuti.getCutiName());
                    model.setSisaCuti(modelCuti.getSisaHariCuti());
                    model.setStTanggalAwalCuti(modelCuti.getTanggalDari().toString());
                    model.setStTanggalSelesaiCuti(modelCuti.getTanggalSelesai().toString());
                    model.setKeterangan(modelCuti.getKeterangan());
                    model.setAlamatCuti(modelCuti.getAlamatCuti());
                    model.setStatusApprove("Rejected");

                    listOfPengajuanIjin.add(model);
                }
            }
        }

        logger.info("[IjinFormPegawaiController.create] end process POST /pengajuancuti/{nip}/status <<<");
        return "success";
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public Object getModel() {
        return (listOfPengajuanIjin != null ? listOfPengajuanIjin : model);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    public void setModel(PengajuanIjin model) {
        this.model = model;
    }

    @Override
    public Object getModel() {
        return null;
    }*/
}
