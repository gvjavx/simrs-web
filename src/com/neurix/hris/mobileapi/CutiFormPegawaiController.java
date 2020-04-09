package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.cuti.bo.CutiBo;
import com.neurix.hris.mobileapi.model.PengajuanCuti;
import com.neurix.hris.mobileapi.model.PengajuanLembur;
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
public class CutiFormPegawaiController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CutiFormPegawaiController.class);

    private CutiPegawaiBo cutiPegawaiBoProxy;
    private BiodataBo biodataBoProxy;
    private CutiBo cutiBoProxy;
    private List<PengajuanCuti> listOfCutiPegawai;
    private PengajuanCuti model = new PengajuanCuti();

    private String id;

    private String nip;
    private String statusApprove;

    public void setCutiBoProxy(CutiBo cutiBoProxy) {
        this.cutiBoProxy = cutiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public HttpHeaders create() {

        logger.info("[CutiFormPegawaiController.create] end process POST /pengajuancuti <<<");

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

        List<com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai> modelCutiPegawai = null;
        try {
            modelCutiPegawai = cutiPegawaiBoProxy.getListSetCuti(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai tempModelCutiPegawai = null;
        for (com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai cutiPegawai : modelCutiPegawai){
            tempModelCutiPegawai = new com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai();
            tempModelCutiPegawai.setCutiId(cutiPegawai.getCutiId());
            tempModelCutiPegawai.setSisaHariCuti(cutiPegawai.getSisaCutiHari());
        }

        com.neurix.hris.master.cuti.model.Cuti masterCuti = null;
        try {
            masterCuti = cutiBoProxy.findCutiByIdcuti(tempModelCutiPegawai.getCutiId());
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }


        if(modelBiodata != null && modelCutiPegawai != null && masterCuti != null){
            model = new PengajuanCuti();
            model.setNip(modelBiodata.getNip());
            model.setNamaPegawai(modelBiodata.getNamaPegawai());
            model.setUnitId(modelBiodata.getBranch());
            model.setUnit(modelBiodata.getBranchName());
            model.setJabatanId(modelBiodata.getPositionId());
            model.setJabatan(modelBiodata.getPositionName());
            model.setBidangId(modelBiodata.getDivisi());
            model.setBidang(modelBiodata.getDivisiName());
            model.setCutiId(tempModelCutiPegawai.getCutiId());
            model.setJenisCuti(masterCuti.getCutiName());
            model.setSisaCuti(tempModelCutiPegawai.getSisaHariCuti());
            model.setTypeHariCuti(masterCuti.getTipeHari());
        }


        logger.info("[CutiFormPegawaiController.create] end process POST /pengajuancuti <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders update() {
        logger.info("[CutiFormPegawaiController.update] end process POST /pengajuancuti/{id} <<<");
        PengajuanCuti result = new PengajuanCuti();
        result.setActionError("");

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
            cutiPegawai.setFlagPerbaikan("N");
            cutiPegawai.setApprovalFlag("-");
            cutiPegawai.setSelf("");
            cutiPegawai.setLamaHariCuti(model.getLamaCuti());
            cutiPegawai.setFlag("Y");
            cutiPegawai.setAction("C");
            cutiPegawai.setCreatedWho(model.getNamaPegawai());
            cutiPegawai.setLastUpdateWho(model.getNamaPegawai());
            cutiPegawai.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            cutiPegawai.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            cutiPegawai.setOs(model.getOs());

            cutiPegawaiBoProxy.saveAdd(cutiPegawai);
        } catch (GeneralBOException e) {
            result.setActionError(e.getMessage());
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        logger.info("[CutiFormPegawaiController.update] end process POST /pengajuancuti/{id} <<<");

        return new DefaultHttpHeaders("update").disableCaching();
    }

    public String status() {
        logger.info("[CutiFormPegawaiController.update] end process POST /pengajuancuti/{nip}/status <<<");
        setNip(getId());

        com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai cutiPegawai = new com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai();
        cutiPegawai.setNip(nip);
        cutiPegawai.setForMobile(true);
        cutiPegawai.setFlag("Y");

        List<com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai> listOfCuti = null;

        try {
            listOfCuti = cutiPegawaiBoProxy.getByCriteria(cutiPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiFormPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(statusApprove.equals("Y")) {
            statusApprove = "Y";
        } else {
            statusApprove = "N";
        }

        if(listOfCuti != null){
            listOfCutiPegawai = new ArrayList<>();
            for(com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai modelCuti : listOfCuti){
                if(modelCuti.getApprovalFlag() == null) {
                    PengajuanCuti model = new PengajuanCuti();
                    model.setCutiPegawaiId(modelCuti.getCutiPegawaiId());
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

//                    listOfCutiPegawai.add(model);
                } else if(modelCuti.getApprovalFlag().equals("Y") && statusApprove.equals("Y")){

                    PengajuanCuti model = new PengajuanCuti();
                    model.setCutiPegawaiId(modelCuti.getCutiPegawaiId());
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

                    listOfCutiPegawai.add(model);
                } else if (modelCuti.getApprovalFlag().equals("N") && statusApprove.equals("N")){
                    PengajuanCuti model = new PengajuanCuti();
                    model.setCutiPegawaiId(modelCuti.getCutiPegawaiId());
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

                    listOfCutiPegawai.add(model);
                }
            }
        }

        logger.info("[CutiFormPegawaiController.create] end process POST /pengajuancuti/{nip}/status <<<");
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
        return (listOfCutiPegawai != null ? listOfCutiPegawai : model);
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

    public void setModel(PengajuanCuti model) {
        this.model = model;
    }
}
