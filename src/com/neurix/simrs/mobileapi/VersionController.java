package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.license.bo.LicenseZebraBo;
import com.neurix.simrs.master.license.model.Version;
import com.neurix.simrs.mobileapi.model.VersionMobile;
import com.opensymphony.xwork2.ModelDriven;

import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VersionController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(VersionController.class);
    private VersionMobile model = new VersionMobile();
    private Collection<VersionMobile> listOfVersionMobile;
    private LicenseZebraBo licenseZebraBoProxy;

    private String action;
    private String idVersion;
    private String versionName;
    private String flag;
    private String description;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(String idVersion) {
        this.idVersion = idVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(VersionMobile model) {
        this.model = model;
    }

    public Collection<VersionMobile> getListOfVersionMobile() {
        return listOfVersionMobile;
    }

    public void setListOfVersionMobile(Collection<VersionMobile> listOfVersionMobile) {
        this.listOfVersionMobile = listOfVersionMobile;
    }

    public LicenseZebraBo getLicenseZebraBoProxy() {
        return licenseZebraBoProxy;
    }

    public void setLicenseZebraBoProxy(LicenseZebraBo licenseZebraBoProxy) {
        this.licenseZebraBoProxy = licenseZebraBoProxy;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public Object getModel() {
        return listOfVersionMobile != null ? listOfVersionMobile : model;
    }

    public HttpHeaders create() {
        logger.info("[VersionController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Version bean = new Version();
        List<Version> result = new ArrayList<>();

        if (action.equalsIgnoreCase("saveAddVersion")) {

            bean.setCreatedDate(now);
            bean.setLastUpdate(now);
            bean.setCreatedWho("admin");
            bean.setLastUpdateWho("admin");
            bean.setDescription(description);
            bean.setVersionName(versionName);
            bean.setFlag("Y");
            bean.setAction("C");
            try {
                licenseZebraBoProxy.saveAddVersion(bean);
            } catch (GeneralBOException e){
                logger.error("VersionController Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("getVersionByCriteria")) {
            listOfVersionMobile = new ArrayList<>();

            bean.setVersionName(versionName);
            bean.setIdVersion(idVersion);
            bean.setFlag(flag);
            bean.setTipe("zebra");

            try {
               result = licenseZebraBoProxy.getVersionByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("VersionController Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (Version item : result) {
                    VersionMobile versionMobile = new VersionMobile();
                    versionMobile.setIdVersion(item.getIdVersion());
                    versionMobile.setVersionName(item.getVersionName());
                    versionMobile.setDescription(item.getDescription());
                    versionMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    versionMobile.setLastUpdate(item.getLastUpdate().toLocaleString());
                    versionMobile.setFlag(item.getFlag());
                    versionMobile.setAction(item.getAction());

                    listOfVersionMobile.add(versionMobile);

                }
            }
        }

        logger.info("[VersionController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
