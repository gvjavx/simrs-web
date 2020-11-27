package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.mobileapi.model.PengajuanCuti;
import com.neurix.hris.mobileapi.model.PengajuanLembur;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author gondok
 * Wednesday, 08/01/20 9:59
 */
public class LemburFormController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(LemburFormController.class);

    private LemburBo lemburBoProxy;
    private NotifikasiBo notifikasiBoProxy;
    private List<PengajuanLembur> listOfLemburPegawai;
    private PengajuanLembur model = new PengajuanLembur();

    private String id;
    private String statusApprove;

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLemburBoProxy(LemburBo lemburBoProxy) {
        this.lemburBoProxy = lemburBoProxy;
    }

    public void setListOfLemburPegawai(List<PengajuanLembur> listOfLemburPegawai) {
        this.listOfLemburPegawai = listOfLemburPegawai;
    }

    public void setModel(PengajuanLembur model) {
        this.model = model;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    @Override
    public Object getModel() {
        return (listOfLemburPegawai != null ? listOfLemburPegawai: model);
    }

    public HttpHeaders create() {
        logger.info("[LemburFormPegawaiController.create] start process POST /pengajuanlembur <<<");


        logger.info("[LemburFormPegawaiController.create] end process POST /pengajuanlembur <<<");

        return new DefaultHttpHeaders("create").disableCaching();
    }

    public HttpHeaders update() {
        logger.info("[LemburFormPegawaiController.update] start process PUT /pengajuanlembur/{id} <<<");
        PengajuanLembur result = new PengajuanLembur();
        result.setActionError("");

        List<Notifikasi> notif = new ArrayList<>();
        Lembur lembur = new Lembur();

        lembur.setNip(model.getNip());
        lembur.setPegawaiName(model.getPegawaiName());
        lembur.setDivisiId(model.getDivisiId());
        lembur.setDivisiName(model.getDivisiName());
        lembur.setPositionId(model.getPositionId());
        lembur.setPositionName(model.getPositionName());
        lembur.setGolonganId(model.getGolonganId());
        lembur.setGolonganName(model.getGolonganName());
        lembur.setTipePegawaiId(model.getTipePegawaiId());
        lembur.setTanggalAwal(CommonUtil.convertToDate(model.getStTanggalAwal()));
        lembur.setTanggalAkhir(CommonUtil.convertToDate(model.getStTanggalAkhir()));
        lembur.setJamAwal(model.getJamAwal());
        lembur.setJamAkhir(model.getJamAkhir());
        lembur.setLamaJam(model.getLamaJam().doubleValue());
        lembur.setTipeLembur(model.getTipeLembur());
        lembur.setKeterangan(model.getKeterangan());

        lembur.setFlag("Y");
        lembur.setAction("C");
        lembur.setApprovalFlag("N");
        lembur.setCreatedWho(model.getPegawaiName());
        lembur.setLastUpdateWho(model.getPegawaiName());
        lembur.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        lembur.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        lembur.setOs(model.getOs());

        try {
            notif = lemburBoProxy.saveAddLembur(lembur);
        } catch (GeneralBOException e) {
            model.setActionError(e.getMessage());
            logger.error("[LemburFormPegawaiController.update] Error when add lembur,", e);
        }

        for (Notifikasi notifikasi: notif) {
            notifikasiBoProxy.sendNotif(notifikasi);
        }

        logger.info("[LemburFormPegawaiController.update] end process PUT /pengajuanlembur/{id} <<<");
        return new DefaultHttpHeaders("update").disableCaching();
    }

    public String status() {
        logger.info("[LemburFormPegawaiController.status] start process PUT /pengajuanlembur/{id}/status <<<");
        String nip = getId();

        Lembur search = new Lembur();
        search.setNip(nip);
        search.setFlag("Y");

        List<Lembur> listOfLembur = null;

        try {
            listOfLembur = lemburBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = lemburBoProxy.saveErrorMessage(e.getMessage(), "LemburFormController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[LemburFormController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[LemburFormController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
            throw new GeneralBOException(e);
        }

        int size = listOfLembur.size();
        if (statusApprove.equals("Y")) {
            statusApprove = "Y";
        } else {
            statusApprove = "N";
        }
        listOfLemburPegawai = new ArrayList<>();

        if (listOfLembur != null && listOfLembur.size() != 0) {
            for(Lembur lembur : listOfLembur){
                if (lembur.getApprovalFlag() == null) {
                    PengajuanLembur model = new PengajuanLembur();
                    model.setDivisiName(lembur.getDivisiName());
                    model.setDivisiId(lembur.getDivisiId());
                    model.setNip(lembur.getNip());
                    model.setPegawaiName(lembur.getPegawaiName());
                    model.setStTanggalAwal(lembur.getStTanggalAwal());
                    model.setStTanggalAkhir(lembur.getStTanggalAkhir());
                    model.setBranchId(lembur.getBranchId());
                    model.setPositionName(lembur.getPositionName());
                    model.setPositionId(lembur.getPositionId());
                    model.setLemburId(lembur.getLemburId());
                    model.setKeterangan(lembur.getKeterangan());
                    model.setJamAwal(lembur.getJamAwal());
                    model.setJamAkhir(lembur.getJamAkhir());
                    model.setLamaJam(lembur.getLamaJam());
                } else if (lembur.getApprovalFlag().equals("Y") && statusApprove.equals("Y")) {
                    PengajuanLembur model = new PengajuanLembur();
                    model.setDivisiName(lembur.getDivisiName());
                    model.setDivisiId(lembur.getDivisiId());
                    model.setNip(lembur.getNip());
                    model.setPegawaiName(lembur.getPegawaiName());
                    model.setStTanggalAwal(lembur.getStTanggalAwal());
                    model.setStTanggalAkhir(lembur.getStTanggalAkhir());
                    model.setBranchId(lembur.getBranchId());
                    model.setPositionName(lembur.getPositionName());
                    model.setPositionId(lembur.getPositionId());
                    model.setLemburId(lembur.getLemburId());
                    model.setKeterangan(lembur.getKeterangan());
                    model.setJamAwal(lembur.getJamAwal());
                    model.setJamAkhir(lembur.getJamAkhir());
                    model.setLamaJam(lembur.getLamaJam());

                    listOfLemburPegawai.add(model);
                } else if (lembur.getApprovalFlag().equals("N") && statusApprove.equals("N")){
                    PengajuanLembur model = new PengajuanLembur();
                    model.setDivisiName(lembur.getDivisiName());
                    model.setDivisiId(lembur.getDivisiId());
                    model.setNip(lembur.getNip());
                    model.setPegawaiName(lembur.getPegawaiName());
                    model.setStTanggalAwal(lembur.getStTanggalAwal());
                    model.setStTanggalAkhir(lembur.getStTanggalAkhir());
                    model.setBranchId(lembur.getBranchId());
                    model.setPositionName(lembur.getPositionName());
                    model.setPositionId(lembur.getPositionId());
                    model.setLemburId(lembur.getLemburId());
                    model.setKeterangan(lembur.getKeterangan());
                    model.setJamAwal(lembur.getJamAwal());
                    model.setJamAkhir(lembur.getJamAkhir());
                    model.setLamaJam(lembur.getLamaJam());

                    listOfLemburPegawai.add(model);
                }
            }
        }

        logger.info("[LemburFormPegawaiController.status] end process PUT /pengajuanlembur/{id}/status <<<");
        return "success";
    }
}
