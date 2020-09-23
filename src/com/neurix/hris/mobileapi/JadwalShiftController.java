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

    private String nip;
    private String branchId;
    private String profesiId;
    private String bulan;
    private String tahun;

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

        com.neurix.hris.master.biodata.model.Biodata bio = new Biodata();
        try {
            bio = biodataBoProxy.detailBiodataSys(nip);
        } catch (GeneralBOException e) {
            logger.error("[JadwalShiftController.create] Error when saving error,", e);
        }

        //Jika pegawai memiliki flag shift Y, maka akan mengambil jadwal shift
        if (bio.getShift().equalsIgnoreCase("Y")) {

            List<JadwalShiftKerjaDetail> result = new ArrayList<>();
            listOfJadwalShift = new ArrayList<>();
            String tanggalAwal;
            String tanggalAkhir;

            //jika bulan kosong, maka akan mencari absensi dalam setahun
            if (!bulan.equalsIgnoreCase("")) {
                tanggalAwal = "01-"+bulan+"-"+tahun;

                //Mencari tanggal terakhir di bulan yg diinputkan
                Date dtTanggalAkhir = CommonUtil.convertStringToDate(tanggalAwal);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dtTanggalAkhir);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                tanggalAkhir = CommonUtil.convertDateToString(calendar.getTime());
            } else {
                tanggalAwal = "01-01-"+tahun;
                tanggalAkhir = "31-12-"+tahun;
            }

            try {
                result = jadwalShiftKerjaBoProxy.getJadwalShiftByBulanTahun(nip, branchId, profesiId, tanggalAwal, tanggalAkhir);
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
                    jadwalShiftMobile.setOnCall(item.getOnCall());
                    jadwalShiftMobile.setFlagPanggil(item.getFlagPanggil());

                    listOfJadwalShift.add(jadwalShiftMobile);
                }
            }
            //jika tidak memiliki flag shift Y, maka akan mengambil jam kerja
        } else {
            JamKerja bean = new JamKerja();
            bean.setBranchId(branchId);

            List<JamKerja> result = new ArrayList<>();
            listOfJadwalShift = new ArrayList<>();

            try {
               result = jamKerjaBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("[JadwalShiftController.create] Error when saving error,", e);
            }

            if (result.size()> 0) {
                for (JamKerja item : result){
                    JadwalShiftMobile jadwalShiftMobile = new JadwalShiftMobile();
                    jadwalShiftMobile.setJamKerjaId(item.getJamKerjaId());
                    jadwalShiftMobile.setHariName(item.getHariName());
                    jadwalShiftMobile.setJamAwalKerja(item.getJamAwalKerja());
                    jadwalShiftMobile.setJamAkhirKerja(item.getJamAkhirKerja());
                    jadwalShiftMobile.setIstirahatAwal(item.getIstirahatAwal());
                    jadwalShiftMobile.setIstirahatAkhir(item.getIstirahatAkhir());
                    jadwalShiftMobile.setBranchName(item.getBranchName());

                    listOfJadwalShift.add(jadwalShiftMobile);
                }
            }
        }

        logger.info("[JadwalShiftController.create] end process POST /jadwalShift <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }
}
