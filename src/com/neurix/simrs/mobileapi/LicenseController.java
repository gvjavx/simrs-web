package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.license.bo.LicenseZebraBo;
import com.neurix.simrs.master.license.model.LicenseZebra;
import com.neurix.simrs.mobileapi.model.LicenseZebraMobile;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LicenseController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(LicenseController.class);
    private LicenseZebraMobile model = new LicenseZebraMobile();
    private Collection<LicenseZebraMobile> listOfLicenseMZebraobile;
    private LicenseZebraBo licenseZebraBoProxy;

    private String action;
    private String licenseId;
    private String licenseKey;
    private String deviceId;
    private String flag;

    public void setLicenseZebraBoProxy(LicenseZebraBo licenseZebraBoProxy) {
        this.licenseZebraBoProxy = licenseZebraBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(LicenseZebraMobile model) {
        this.model = model;
    }

    public Collection<LicenseZebraMobile> getListOfLicenseMZebraobile() {
        return listOfLicenseMZebraobile;
    }

    public void setListOfLicenseMZebraobile(Collection<LicenseZebraMobile> listOfLicenseMZebraobile) {
        this.listOfLicenseMZebraobile = listOfLicenseMZebraobile;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Object getModel() {
        return listOfLicenseMZebraobile != null ? listOfLicenseMZebraobile : model;
    }

    public HttpHeaders create() {
        logger.info("[LicenseController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("getZebraKey")) {
            List<LicenseZebra> result = new ArrayList();
            listOfLicenseMZebraobile = new ArrayList<>();

            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
            String encodedLicenseKey = passwordEncoder.encodePassword(licenseKey, null);

            LicenseZebra bean = new LicenseZebra();
            bean.setLicenseKey(encodedLicenseKey);
            bean.setDeviceId(deviceId);
            bean.setFlag(flag);

            try {
              result = licenseZebraBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("LicenseController.create] Error, " + e.getMessage());
            }

            if  (result.size() > 0) {
                for (LicenseZebra item : result) {
                    LicenseZebraMobile licenseZebraMobile = new LicenseZebraMobile();
                    licenseZebraMobile.setLicenseId(item.getLicenseId());
                    licenseZebraMobile.setLicenseKey(item.getLicenseKey());
                    licenseZebraMobile.setDeviceId(item.getDeviceId());
                    licenseZebraMobile.setFlag(item.getFlag());

                    listOfLicenseMZebraobile.add(licenseZebraMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("isKeyAvailable")) {
            boolean isAvailable = false;

            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
            String encodedlicenseKey = passwordEncoder.encodePassword(licenseKey, null);

            try {
               isAvailable = licenseZebraBoProxy.isKeyAvailable(encodedlicenseKey, deviceId);
            } catch (GeneralBOException e) {
                logger.error("LicenseController.create] Error, " + e.getMessage());
            }

            if (isAvailable) {
                model.setMessage("ok");
            } else model.setMessage("no");
        }

        if (action.equalsIgnoreCase("updateFlag")) {

            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
            String encodedlicenseKey = passwordEncoder.encodePassword(licenseKey, null);

            LicenseZebra bean = new LicenseZebra();
            bean.setLicenseKey(encodedlicenseKey);
            bean.setDeviceId(deviceId);
            bean.setFlag(flag);
            bean.setAction("U");
            bean.setLastUpdate(now);
            bean.setLastUpdateWho("admin");

            try {
                licenseZebraBoProxy.updateFlag(bean);
                model.setMessage("success");
            } catch (GeneralBOException e) {
                logger.error("LicenseController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveAdd")) {

            LicenseZebra bean = new LicenseZebra();

            ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
            String hashedKey = passwordEncoder.encodePassword(licenseKey,null);

            bean.setLicenseKey(hashedKey);
            bean.setDeviceId(deviceId);
            bean.setCreatedWho("admin");
            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setLastUpdateWho("admin");
            bean.setFlag("Y");
            bean.setAction("C");

            try {
                licenseZebraBoProxy.saveAdd(bean);
            } catch (GeneralBOException e){
                logger.error("LicenseController.create] Error, " + e.getMessage());
            }
        }

        logger.info("[LicenseController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
