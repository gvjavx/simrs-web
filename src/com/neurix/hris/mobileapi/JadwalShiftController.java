package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.mobileapi.model.JadwalShiftMobile;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 22/09/20 12:17
 */
public class JadwalShiftController implements ModelDriven<Object> {
    private static final transient org.apache.log4j.Logger logger = Logger.getLogger(JadwalShiftController.class);

    JadwalShiftKerjaBo jadwalShiftKerjaBoProxy;
    BiodataBo biodataBoProxy;
    JamKerjaBo jamKerjaBoProxy;

    private JadwalShiftMobile model = new JadwalShiftMobile();
    private Collection<JadwalShiftMobile> listOfJadwalShift;

    private String action;
    private String nip;
    private String branchId;
    private String profesiId;
    private String bulan;
    private String tahun;
    private String today;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JamKerjaBo getJamKerjaBoProxy() {
        return jamKerjaBoProxy;
    }

    public void setJamKerjaBoProxy(JamKerjaBo jamKerjaBoProxy) {
        this.jamKerjaBoProxy = jamKerjaBoProxy;
    }

    public Collection<JadwalShiftMobile> getListOfJadwalShift() {
        return listOfJadwalShift;
    }

    public void setListOfJadwalShift(Collection<JadwalShiftMobile> listOfJadwalShift) {
        this.listOfJadwalShift = listOfJadwalShift;
    }

    public void setModel(JadwalShiftMobile model) {
        this.model = model;
    }

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

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

        if(action.equalsIgnoreCase("getJadwalKerja")) {

        }




        logger.info("[JadwalShiftController.create] end process POST /jadwalShift <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
