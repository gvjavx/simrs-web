package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.MesinAbsensiMobile;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiDetailBo;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    String action;

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

    @Override
    public Object getModel() {
        switch (action) {
            case "getAbsensi":
                return listOfMesinAbsensi;
            default: return model;

        }
    }

    public HttpHeaders create() {
        logger.info("AbsensiOnlineController.create] start process POST /absensi <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("saveAdd")){
            MesinAbsensiDetail bean = new MesinAbsensiDetail();
            bean.setPin(pin);
            bean.setStatus("1");
            bean.setVerifyMode("M");
            bean.setWorkCode("0");
            bean.setScanDate(now);

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

            String tgl = CommonUtil.convertTimestampToString(now);

            try {
                result = absensiBoProxy.getByCriteriaAbsensiDetail(bean, tgl);
            } catch (GeneralBOException e) {
                logger.error("AbsensiOnlineController.getAbensi] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when get data AbsensiOnlineController, please info to your admin..." + e.getMessage());
            }

            if (result.size() > 0) {
                for (MesinAbsensiDetail item : result) {
                    MesinAbsensiMobile mesinAbsensiMobile = new MesinAbsensiMobile();
                    mesinAbsensiMobile.setPin(pin);
                    mesinAbsensiMobile.setTanggal(item.getTanggal());
                    mesinAbsensiMobile.setStatus(item.getStatus());
                    mesinAbsensiMobile.setStatusName(item.getStatusName());

                    listOfMesinAbsensi.add(mesinAbsensiMobile);
                }
            }
        }


        logger.info("AbsensiOnlineController.create] end process POST /absensi <<<");
        return new DefaultHttpHeaders("success")
                .disableCaching();
    }
}
