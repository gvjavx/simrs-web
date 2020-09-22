package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.JadwalShiftMobile;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 22/09/20 12:17
 */
public class JadwalShiftController implements ModelDriven<Object> {
    private static final transient org.apache.log4j.Logger logger = Logger.getLogger(JadwalShiftController.class);

    JadwalShiftKerjaBo jadwalShiftKerjaBoProxy;

    private JadwalShiftMobile model = new JadwalShiftMobile();
    private Collection<JadwalShiftMobile> listOfJadwalShift;

    private String nip;
    private String branchId;
    private String profesiId;

    public static Logger getLogger() {
        return logger;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getProfesiId() {
        return profesiId;
    }

    public void setProfesiId(String profesiId) {
        this.profesiId = profesiId;
    }

    public JadwalShiftKerjaBo getJadwalShiftKerjaBoProxy() {
        return jadwalShiftKerjaBoProxy;
    }

    public void setJadwalShiftKerjaBoProxy(JadwalShiftKerjaBo jadwalShiftKerjaBoProxy) {
        this.jadwalShiftKerjaBoProxy = jadwalShiftKerjaBoProxy;
    }

    @Override
    public Object getModel() {
        return listOfJadwalShift != null ? listOfJadwalShift : model;
    }

    public HttpHeaders create() {
        logger.info("[JadwalShiftController.create] start process POST /jadwalShift <<<");

        List<JadwalShiftKerjaDetail> result = new ArrayList<>();
        listOfJadwalShift = new ArrayList<>();

        try {
            result = jadwalShiftKerjaBoProxy.getJadwalShiftThisMonth(nip, branchId, profesiId);
        } catch (GeneralBOException e) {
            logger.error("[JadwalShiftController.create] Error when saving error,", e);
        }

        if (result.size() > 0) {
            for (JadwalShiftKerjaDetail item : result){
                JadwalShiftMobile jadwalShiftMobile = new JadwalShiftMobile();
                jadwalShiftMobile.setJadwalShiftKerjaDetailId(item.getJadwalShiftKerjaDetailId());
                jadwalShiftMobile.setJadwalName(item.getJadwalName());
                jadwalShiftMobile.setShiftName(item.getShiftName());
                jadwalShiftMobile.setJamAwal(item.getJamAwal());
                jadwalShiftMobile.setJamAkhir(item.getJamAkhir());
                jadwalShiftMobile.setNip(item.getNip());
                jadwalShiftMobile.setNamaPegawai(item.getNamaPegawai());
                jadwalShiftMobile.setProfesiName(item.getProfesiName());
                jadwalShiftMobile.setBranchId(item.getBranchId());

                listOfJadwalShift.add(jadwalShiftMobile);
            }
        }

        logger.info("[JadwalShiftController.create] end process POST /jadwalShift <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
