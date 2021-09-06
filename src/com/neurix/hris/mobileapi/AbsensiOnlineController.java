package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.MesinAbsensiMobile;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiDetailBo;
import com.neurix.hris.transaksi.absensi.model.ItHrisMesinAbsensiDetailOnCallEntity;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetailOnCall;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 24/03/20 10:18
 */
public class AbsensiOnlineController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(AbsensiOnlineController.class);
    private MesinAbsensiMobile model = new MesinAbsensiMobile();
    private MesinAbsensiDetailBo mesinAbsensiDetailBoProxy;
    private AbsensiBo absensiBoProxy;
    private Collection<MesinAbsensiMobile> listOfMesinAbsensi = new ArrayList<>();

    String pin;
    String jam;
    String tanggal;
    String action;
    String username;
    String branchId;
    String keterangan;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Collection<MesinAbsensiMobile> getListOfMesinAbsensi() {
        return listOfMesinAbsensi;
    }

    public void setListOfMesinAbsensi(Collection<MesinAbsensiMobile> listOfMesinAbsensi) {
        this.listOfMesinAbsensi = listOfMesinAbsensi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(MesinAbsensiMobile model) {
        this.model = model;
    }

    public MesinAbsensiDetailBo getMesinAbsensiDetailBoProxy() {
        return mesinAbsensiDetailBoProxy;
    }

    public void setMesinAbsensiDetailBoProxy(MesinAbsensiDetailBo mesinAbsensiDetailBoProxy) {
        this.mesinAbsensiDetailBoProxy = mesinAbsensiDetailBoProxy;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public Object getModel() {
        switch (action) {

            default: return model;

        }
    }

    public HttpHeaders create() {
        logger.info("AbsensiOnlineController.create] start process POST /absensi <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String nowDate = sdf.format(now);
        String nowTime = sdf2.format(now);

        if (action.equalsIgnoreCase("saveAdd")){
            MesinAbsensiDetail bean = new MesinAbsensiDetail();
            bean.setPin(pin);
            bean.setStatus("1");
            bean.setVerifyMode("M");
            bean.setWorkCode("0");
            bean.setStScanDate(nowDate);
            bean.setJam(nowTime);
            bean.setFlag("Y");
            bean.setAction("C");
            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setCreatedWho(username);
            bean.setLastUpdateWho(username);
            bean.setBranchId(branchId);

            //SYAMS 6SEP21 => Tambah keterangan
            bean.setKeterangan(keterangan);

            try {
                mesinAbsensiDetailBoProxy.saveAdd(bean);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("AbsensiOnlineController.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving data AbsensiOnlineController, please info to your admin..." + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getAbsensi")) {

            List<MesinAbsensiDetail> result = new ArrayList<>();

            MesinAbsensiDetail bean = new MesinAbsensiDetail();
            bean.setPin(pin);
            bean.setTanggalDari(CommonUtil.convertToDate(tanggal));

            try {
                result = mesinAbsensiDetailBoProxy.getByCriteriaMobile(bean);
            } catch (GeneralBOException e) {
                logger.error("AbsensiOnlineController.getAbensi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when get data AbsensiOnlineController, please info to your admin..." + e.getMessage());
            }

            // prepare pengambilan data absen masuk dan keluar
            // rule: absen masuk = absen paling awal, dan pulang = absen paling akhir.
            if(result.size() > 0)
            {
                // jam masuk
                String[] temp = result.get(0).getScanDate().toString().split(" ");
                String[] temp2 = temp[1].split(":");
                model.setAbsMasuk(temp2[0]+":"+temp2[1]);

                // check if result more than 1
                if(result.size() > 1)
                {
                    // jam pulang
                    temp = result.get(result.size()-1).getScanDate().toString().split(" ");
                    temp2 = temp[1].split(":");
                    model.setAbsKeluar(temp2[0]+":"+temp2[1]);
                }

            }
            else
            {
                model = null;
            }




        }

        if  (action.equalsIgnoreCase("saveAddOnCall")) {

            MesinAbsensiDetailOnCall bean = new MesinAbsensiDetailOnCall();
            bean.setPin(pin);
            bean.setStatus("M");
            bean.setScanDate(new Timestamp(System.currentTimeMillis()));
            bean.setFlag("Y");
            bean.setAction("C");
            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setCreatedWho(username);
            bean.setLastUpdateWho(username);

            try {
                absensiBoProxy.saveAddAbsensiOnCall(bean);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("AbsensiOnlineController.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving data AbsensiOnlineController, please info to your admin..." + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getAbsensiOnCall")) {

            List<MesinAbsensiDetailOnCall> result = new ArrayList<>();

            MesinAbsensiDetailOnCall bean = new MesinAbsensiDetailOnCall();
            bean.setPin(pin);
            bean.setTanggalDari(CommonUtil.convertToDate(tanggal));

            try {
               result = absensiBoProxy.getAbsensiOnCallByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("AbsensiOnlineController.getAbensi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when get data AbsensiOnlineController, please info to your admin..." + e.getMessage());
            }

            //ambil jam absen yang terbaru
            String[] temp = result.get(result.size()-1).getScanDate().toString().split(" ");
            String[] temp2 = temp[1].split(":");

            model.setScanDate(temp2[0]+":"+temp2[1]);
        }

        logger.info("AbsensiOnlineController.create] end process POST /absensi <<<");
        return new DefaultHttpHeaders("success")
                .disableCaching();
    }
}
