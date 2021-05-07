package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.HistoryCuti;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Thursday, 04/04/19 11:47
 */
public class HistoryCutiController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(HistoryCutiController.class);
    private Collection<HistoryCuti> listOfHistoryCutis = null;
    private HistoryCuti model;
    private CutiPegawaiBo cutiPegawaiBoProxy;

    private String nip;
    private String cutiId;

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[DispensasiController.create] end process POST /historycuti <<<");

        List<CutiPegawai> cutiPegawais = null;

        try {
            cutiPegawais = cutiPegawaiBoProxy.getHistoryCuti(nip, cutiId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "DispensasiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[DispensasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if (cutiPegawais.size() > 0) {

            listOfHistoryCutis = new ArrayList<>();

            for (CutiPegawai cuti : cutiPegawais) {
                HistoryCuti history = new HistoryCuti();
                history.setCutiPegawaiId(cuti.getCutiPegawaiId());
                history.setCutiId(cuti.getCutiId());
                history.setPegawaiPenggantiSementara(cuti.getPegawaiPenggantiSementara());
                history.setCutiName(cuti.getCutiName());
                history.setLamaHariCuti(cuti.getLamaHariCuti());
                history.setSisaCutiHari(cuti.getSisaCutiHari());
                history.setApprovalId(cuti.getApprovalId());
//                history.setApprovalDate(CommonUtil.convertTimestampToString(cuti.getApprovalDate()));
                history.setApprovalFlag(cuti.getApprovalFlag());
                history.setNote(cuti.getNote());
                history.setNoteApproval(cuti.getNoteApproval());
                history.setCancelFlag(cuti.getCancelFlag());
                history.setAlamatCuti(cuti.getAlamatCuti());
                history.setKeterangan(cuti.getKeterangan());
                history.setTanggalDari(CommonUtil.convertDateToString(cuti.getTanggalDari()));
                history.setTanggalSelesai(CommonUtil.convertDateToString(cuti.getTanggalSelesai()));

                listOfHistoryCutis.add(history);
            }
        }

        logger.info("[DispensasiController.create] end process POST /historycuti <<<");

        return new DefaultHttpHeaders("index").disableCaching();
    }

    @Override
    public Object getModel() {
        return (listOfHistoryCutis == null ? model : listOfHistoryCutis);
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCutiId() {
        return cutiId;
    }

    public void setCutiId(String cutiId) {
        this.cutiId = cutiId;
    }
}
