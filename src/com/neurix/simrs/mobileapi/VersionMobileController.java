package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.mobileapi.model.VersionMobileObj;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VersionMobileController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(VersionMobileController.class);
    private VersionMobileObj model = new VersionMobileObj();
    private Collection<VersionMobileObj> listOfVersionMobile = new ArrayList();
    private VersionMobileBo versionMobileBoProxy;

    String action;
    String idVersionMobile;
    String namaVersion;
    String flag;
    String os;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdVersionMobile() {
        return idVersionMobile;
    }

    public void setIdVersionMobile(String idVersionMobile) {
        this.idVersionMobile = idVersionMobile;
    }

    public String getNamaVersion() {
        return namaVersion;
    }

    public void setNamaVersion(String namaVersion) {
        this.namaVersion = namaVersion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setVersionMobileBoProxy(VersionMobileBo versionMobileBoProxy) {
        this.versionMobileBoProxy = versionMobileBoProxy;
    }

    @Override
    public Object getModel() {
        return listOfVersionMobile != null ? listOfVersionMobile : model;
    }

    public HttpHeaders create() {
        logger.info("[VersionMobile.create] start process POST / <<<");

        if (action.equalsIgnoreCase("getByCriteria"))
            getByCriteria();
        if (action.equalsIgnoreCase("saveAdd"))
            saveAdd();

        logger.info("[VersionMobile.create] start process POST / <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    private void getByCriteria() {
        List<VersionMobile> result = new ArrayList<>();
        listOfVersionMobile = new ArrayList<>();

        VersionMobile bean = new VersionMobile();
        bean.setIdVersionMobile(idVersionMobile);
        bean.setNamaVersion(namaVersion);
        bean.setFlag(flag);
        bean.setOs(os);

        try {
           result = versionMobileBoProxy.getByCriteria(bean);
        } catch (GeneralBOException e) {
            logger.error("[VersionMobile.getByCriteria] Error when search getByCriteria, " + e.getMessage());
            throw new GeneralBOException("[LicenseAction.searchLicense] Error when search license, " + e.getMessage());
        }

        if (result.size() > 0) {
            for (VersionMobile item : result) {
                VersionMobileObj versionMobileObj = new VersionMobileObj();
                versionMobileObj.setIdVersionMobile(item.getIdVersionMobile());
                versionMobileObj.setNamaVersion(item.getNamaVersion());
                versionMobileObj.setOs(item.getOs());
                versionMobileObj.setFlag(item.getFlag());
                versionMobileObj.setAction(item.getAction());
                versionMobileObj.setCreatedDate(item.getCreatedDate().toLocaleString());
                versionMobileObj.setLastUpdate(item.getLastUpdate().toLocaleString());
                versionMobileObj.setCreatedWho(item.getCreatedWho());
                versionMobileObj.setLastUpdateWho(item.getLastUpdateWho());

                listOfVersionMobile.add(versionMobileObj);
            }
        }
    }

    private void saveAdd() {

        VersionMobile bean = new VersionMobile();
        bean.setNamaVersion(namaVersion);
        bean.setOs(os);
        bean.setFlag("Y");
        bean.setAction("C");
        bean.setLastUpdate(CommonUtil.getCurrentDateTimes());
        bean.setLastUpdateWho("admin");
        bean.setCreatedDate(CommonUtil.getCurrentDateTimes());
        bean.setCreatedWho("admin");

        try {
            versionMobileBoProxy.saveAdd(bean);
            model.setMessage("success");
        } catch (GeneralBOException e) {
            logger.error("[VersionMobileController.saveAdd] Error when saveAdd, " + e.getMessage());
            throw new GeneralBOException("[VersionMobileController.saveAdd] Error saveAdd, " + e.getMessage());
        }
    }
}
