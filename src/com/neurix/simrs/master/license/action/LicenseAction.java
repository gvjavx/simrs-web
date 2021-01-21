package com.neurix.simrs.master.license.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.license.bo.LicenseZebraBo;
import com.neurix.simrs.master.license.model.LicenseZebra;
import com.neurix.simrs.master.license.model.VersionZebra;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LicenseAction{

    protected static transient Logger logger = Logger.getLogger(LicenseAction.class);
    private LicenseZebra licenseZebra;
    private VersionZebra versionZebra;
    private LicenseZebraBo licenseZebraBoProxy;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    public String searchLicense() {
        List<LicenseZebra> licenseZebraList = new ArrayList<>();
        LicenseZebra licenseZebra = getLicenseZebra();
        try {
            licenseZebraList = licenseZebraBoProxy.getByCriteria(licenseZebra);
        }catch (Exception e){
            logger.error("[LicenseAction.searchLicense] Error when search license, "+e.getMessage());
            throw new GeneralBOException("[LicenseAction.searchLicense] Error when search license, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfLicense");
        session.setAttribute("listOfLicense", licenseZebraList);
        return "search";
    }

    public String initLicense() {
        setLicenseZebra(new LicenseZebra());
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfLicense");
        return "search";
    }

    public CrudResponse saveLicense(String data){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LicenseZebraBo licenseZebraBo = (LicenseZebraBo) ctx.getBean("licenseZebraBoProxy");
        if(data != null && !"".equalsIgnoreCase(data)){
            try {
                JSONObject obj = new JSONObject(data);
                if(obj != null){
                    LicenseZebra licenseZebra = new LicenseZebra();
                    String type = obj.getString("tipe");
                    licenseZebra.setDeviceId(obj.getString("device_id"));
                    licenseZebra.setCreatedDate(now);
                    licenseZebra.setCreatedWho(userLogin);
                    licenseZebra.setLastUpdate(now);
                    licenseZebra.setLastUpdateWho(userLogin);
                    licenseZebra.setFlag("Y");
                    try {
                        if("add".equalsIgnoreCase(type)){
                            licenseZebra.setAction("C");
                            licenseZebraBo.saveAdd(licenseZebra);
                            response.setStatus("success");
                            response.setMsg("OK");
                        } else if ("edit".equalsIgnoreCase(type)) {
                            licenseZebra.setLicenseId(obj.getString("license_id"));
                            licenseZebra.setAction("U");
                            licenseZebraBo.saveEdit(licenseZebra);
                            response.setStatus("success");
                            response.setMsg("OK");
                        } else {
                            response.setStatus("error");
                            response.setMsg("Error when save license...!, Tipe transaksi tidak ada..@-@");
                        }
                    }catch (Exception e){
                        logger.error("[LicenseAction.saveLicense] Error, "+e.getMessage());
                        response.setStatus("error");
                        response.setMsg("Error when save license...!, "+e.getMessage());
                    }
                }else{
                    logger.error("[LicenseAction.saveLicense] Data object tidak ada");
                    response.setStatus("error");
                    response.setMsg("Data object tidak ada...!");
                }
            }catch (Exception e){
                logger.error("[LicenseAction.saveLicense] Error, "+e.getMessage());
                response.setStatus("error");
                response.setMsg("Parse JSON error...!, "+e.getMessage());
            }
        }else{
            logger.error("[LicenseAction.saveLicense] Data object tidak ada");
            response.setStatus("error");
            response.setMsg("Data object tidak ada...!");
        }
        return response;
    }

    public String searchVersion() {
        List<VersionZebra> versionZebraList = new ArrayList<>();
        VersionZebra versionZebra = getVersionZebra();
        try {
            versionZebraList = licenseZebraBoProxy.getVersionByCriteria(versionZebra);
        }catch (Exception e){
            logger.error("[LicenseAction.searchVersion] Error when search version, "+e.getMessage());
            throw new GeneralBOException("[LicenseAction.searchVersion] Error when search version, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfVersion");
        session.setAttribute("listOfVersion", versionZebraList);
        return "search";
    }

    public String initVersion() {
        setVersionZebra(new VersionZebra());
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfVersion");
        return "search";
    }

    public CrudResponse saveVersion(String data){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LicenseZebraBo licenseZebraBo = (LicenseZebraBo) ctx.getBean("licenseZebraBoProxy");
        if(data != null && !"".equalsIgnoreCase(data)){
            try {
                JSONObject obj = new JSONObject(data);
                if(obj != null){
                    VersionZebra versionZebra = new VersionZebra();
                    String type = obj.getString("tipe");
                    versionZebra.setVersionName(obj.getString("version_name"));
                    versionZebra.setCreatedDate(now);
                    versionZebra.setCreatedWho(userLogin);
                    versionZebra.setLastUpdate(now);
                    versionZebra.setLastUpdateWho(userLogin);
                    versionZebra.setAction("C");
                    versionZebra.setFlag("Y");
                    try {
                        if("add".equalsIgnoreCase(type)){
                            licenseZebraBo.saveAddVersion(versionZebra);
                            response.setStatus("success");
                            response.setMsg("OK");
                        }else{
                            response.setStatus("error");
                            response.setMsg("Error when save version...!, Tipe transaksi tidak ada..@-@");
                        }
                    }catch (Exception e){
                        logger.error("[LicenseAction.saveVersion] Error, "+e.getMessage());
                        response.setStatus("error");
                        response.setMsg("Error when save version...!, "+e.getMessage());
                    }
                }else{
                    logger.error("[LicenseAction.saveVersion] Data object tidak ada");
                    response.setStatus("error");
                    response.setMsg("Data object tidak ada...!");
                }
            }catch (Exception e){
                logger.error("[LicenseAction.saveVersion] Error, "+e.getMessage());
                response.setStatus("error");
                response.setMsg("Parse JSON error...!, "+e.getMessage());
            }
        }else{
            logger.error("[LicenseAction.saveVersion] Data object tidak ada");
            response.setStatus("error");
            response.setMsg("Data object tidak ada...!");
        }
        return response;
    }

    public String saveVersion(){
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        VersionZebra versionZebra = getVersionZebra();
        if(versionZebra != null){
            try {
                if (this.fileUpload != null) {
                    if("application/vnd.android.package-archive".equalsIgnoreCase(this.fileUploadContentType)){
                        String fileName = this.fileUploadFileName;
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_APK_ZEBRA;
                        File fileToCreate = new File(filePath, fileName);
                        try {
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            versionZebra.setVersionName(fileName.replace(".apk",""));
                            versionZebra.setCreatedDate(now);
                            versionZebra.setCreatedWho(userLogin);
                            versionZebra.setCreatedDate(now);
                            versionZebra.setLastUpdate(now);
                            versionZebra.setLastUpdateWho(userLogin);
                            versionZebra.setAction("C");
                            versionZebra.setFlag("Y");
                            try {
                                licenseZebraBoProxy.saveAddVersion(versionZebra);
                            }catch (Exception e){
                                logger.error("[LicenseAction.saveVersion] error when save version @_@" + e.getMessage());
                                throw new GeneralBOException("[LicenseAction.saveVersion] error when save version @_@" + e.getMessage());
                            }
                        } catch (IOException e) {
                            logger.error("[LicenseAction.saveVersion] error, " + e.getMessage());
                            throw new GeneralBOException("[LicenseAction.saveVersion] error, " + e.getMessage());
                        }
                    }else{
                        logger.error("[LicenseAction.saveVersion] Detected virus application, @_@");
                        throw new GeneralBOException("[LicenseAction.saveVersion] Detected virus application, @_@");
                    }
                }else{
                    logger.error("[LicenseAction.saveVersion] File not found, @_@");
                    throw new GeneralBOException("[LicenseAction.saveVersion] File not found, @_@");
                }
            }catch (Exception e){
                logger.error("Error...!@_@"+e.getMessage());
                throw new GeneralBOException("Error when save Version...!@_@"+e.getMessage());
            }
        }else{
            logger.error("Not found data...!@-@");
            throw new GeneralBOException("Not found data...!@-@");
        }
        return "search";
    }

    public LicenseZebra getDataLicense(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LicenseZebraBo licenseZebraBo = (LicenseZebraBo) ctx.getBean("licenseZebraBoProxy");
        LicenseZebra licenseZebra = new LicenseZebra();
        List<LicenseZebra> licenseZebraList = new ArrayList<>();
        licenseZebra.setLicenseId(id);
        try {
            licenseZebraList = licenseZebraBo.getByCriteria(licenseZebra);
        }catch (Exception e){
            logger.error("[LicenseAction.searchVersion] Erro when get data license, "+e.getMessage());
        }
        if(licenseZebraList.size() > 0){
            licenseZebra = licenseZebraList.get(0);
        }
        return  licenseZebra;
    }

    public VersionZebra getDataVersion(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LicenseZebraBo licenseZebraBo = (LicenseZebraBo) ctx.getBean("licenseZebraBoProxy");
        VersionZebra versionZebra = new VersionZebra();
        List<VersionZebra> versionZebraList = new ArrayList<>();
        versionZebra.setIdVersion(id);
        try {
            versionZebraList = licenseZebraBo.getVersionByCriteria(versionZebra);
        }catch (Exception e){
            logger.error("[LicenseAction.searchVersion] Erro when get data license, "+e.getMessage());
        }
        if(versionZebraList.size() > 0){
            versionZebra = versionZebraList.get(0);
        }
        return  versionZebra;
    }

    public static Logger getLogger() {
        return logger;
    }

    public LicenseZebra getLicenseZebra() {
        return licenseZebra;
    }

    public void setLicenseZebra(LicenseZebra licenseZebra) {
        this.licenseZebra = licenseZebra;
    }

    public VersionZebra getVersionZebra() {
        return versionZebra;
    }

    public void setVersionZebra(VersionZebra versionZebra) {
        this.versionZebra = versionZebra;
    }

    public void setLicenseZebraBoProxy(LicenseZebraBo licenseZebraBoProxy) {
        this.licenseZebraBoProxy = licenseZebraBoProxy;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }
}
