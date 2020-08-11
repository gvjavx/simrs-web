package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.HistoryPegawaiMobile;
import com.neurix.hris.mobileapi.model.ProfilSisaCuti;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 11/08/20 13:32
 */
public class HistoryPegawaiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(HistoryPegawaiController.class);

    HistoryPegawaiMobile model = new HistoryPegawaiMobile();
    Collection<HistoryPegawaiMobile> listOfHistoryPegawaiMoblile;

    CutiPegawaiBo cutiPegawaiBoProxy;
    IjinKeluarBo ijinKeluarBoProxy;
    AbsensiBo absensiBoProxy;

    private String nip;
    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public HistoryPegawaiMobile getModel() {
        return model;
    }

    public void setModel(HistoryPegawaiMobile model) {
        this.model = model;
    }

    public Collection<HistoryPegawaiMobile> getListOfHistoryPegawaiMoblile() {
        return listOfHistoryPegawaiMoblile;
    }

    public void setListOfHistoryPegawaiMoblile(Collection<HistoryPegawaiMobile> listOfHistoryPegawaiMoblile) {
        this.listOfHistoryPegawaiMoblile = listOfHistoryPegawaiMoblile;
    }

    public CutiPegawaiBo getCutiPegawaiBoProxy() {
        return cutiPegawaiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public IjinKeluarBo getIjinKeluarBoProxy() {
        return ijinKeluarBoProxy;
    }

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }



    public HttpHeaders create() {
        logger.info("[HistoryPegawaiController.create] start process POST /historypegawai/ <<<");
        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<CutiPegawai> cutiPegawai = null;
        List<AbsensiPegawai> absensiPegawai = null;
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

        try {
            absensiPegawai = absensiBoProxy.getHistoryAbsensiByMonth(nip, branchId, new Date(now.getDate()));
        } catch (GeneralBOException e) {
            logger.error("[SisaCutiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e);
        }

        //jika cuti tahunan 0, ambil sisa cuti panjang
        boolean isCutiTahunan = true;
        for (CutiPegawai item : cutiPegawai) {
            if (item.getCutiName().equalsIgnoreCase("Cuti Tahunan")) {
                if (!item.getSisaCutiHari().toString().equalsIgnoreCase("0")) {
                    model.setSisaCuti(item.getSisaCutiHari().toString());
                    break;
                } else isCutiTahunan = false;
            }
            if (item.getCutiName().equalsIgnoreCase("Cuti Panjang")) {
                if (!isCutiTahunan){
                    model.setSisaCuti(item.getSisaCutiHari().toString());
                }
            }
        }

        logger.info("[HistoryPegawaiConteroller.create] end process POST /historypegawai/ <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
