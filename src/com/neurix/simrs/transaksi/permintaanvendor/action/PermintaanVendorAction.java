package com.neurix.simrs.transaksi.permintaanvendor.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PermintaanVendorAction.class);
    private PermintaanVendorBo permintaanVendorBoProxy;
    private PermintaanVendor permintaanVendor;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

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

    public static void setLogger(Logger logger) {
        PermintaanVendorAction.logger = logger;
    }

    public void setPermintaanVendorBoProxy(PermintaanVendorBo permintaanVendorBoProxy) {
        this.permintaanVendorBoProxy = permintaanVendorBoProxy;
    }

    public PermintaanVendor getPermintaanVendor() {
        return permintaanVendor;
    }

    public void setPermintaanVendor(PermintaanVendor permintaanVendor) {
        this.permintaanVendor = permintaanVendor;
    }

    @Override
    public String add() {
        logger.info("[PermintaanVendorAction.add] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        setPermintaanVendor(new PermintaanVendor());

        logger.info("[PermintaanVendorAction.add] END <<<<<<<");
        return "init_add";
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[PermintaanVendorAction.search] START process >>>");

        PermintaanVendor permintaanVendor = getPermintaanVendor();
        List<PermintaanVendor> listOfPemintaanVendor = new ArrayList();
        permintaanVendor.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfPemintaanVendor = permintaanVendorBoProxy.getByCriteria(permintaanVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanVendorAction.search] Error when searching permintan vendor by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfPemintaanVendor);

        logger.info("[PermintaanVendorAction.search] END process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PermintaanVendorAction.initForm] START >>>>>>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        setPermintaanVendor(new PermintaanVendor());

        logger.info("[PermintaanVendorAction.initForm] END <<<<<<<");
        return "search";
    }

    public String savePermintaanPO(String idVendor, String po) {

        logger.info("[PermintaanVendorAction.savePermintaanPO] START >>>>>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanVendorBo permintaanVendorBo = (PermintaanVendorBo) ctx.getBean("permintaanVendorBoProxy");
        String userLogin = CommonUtil.userLogin();
        String userBranch = CommonUtil.userBranchLogin();
        String idPelayanan = CommonUtil.userPelayananIdLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        PermintaanVendor permintaanVendor = new PermintaanVendor();
        permintaanVendor.setCreatedWho(userLogin);
        permintaanVendor.setCreatedDate(time);
        permintaanVendor.setLastUpdateWho(userLogin);
        permintaanVendor.setLastUpdate(time);
        permintaanVendor.setAction("C");
        permintaanVendor.setFlag("Y");
        permintaanVendor.setIdVendor(idVendor);
        permintaanVendor.setBranchId(userBranch);
        permintaanVendor.setIdPelayanan(idPelayanan);

        String fileName = "";
        if (this.fileUpload != null) {
            if ("image/jpeg".equalsIgnoreCase(this.fileUploadContentType)) {
                if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {

                    // file name
                    fileName = "DOC_PO"+"_"+this.fileUploadFileName;

                    // deklarasi path file
                    String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_PO;
//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_KTP_PASIEN;
                    logger.info("[savePermintaanPO.uploadImages] FILEPATH :" + filePath);

                    // persiapan pemindahan file
                    File fileToCreate = new File(filePath, fileName);

                    try {
                        // pemindahan file
                        FileUtils.copyFile(this.fileUpload, fileToCreate);
                        logger.info("[savePermintaanPO.uploadImages] SUCCES PINDAH");
                        permintaanVendor.setUrlDocPo(fileName);
                    } catch (IOException e) {
                        logger.error("[savePermintaanPO.uploadImages] error, " + e.getMessage());
                    }
                }
            }
        }

        try {
            try {
                permintaanVendorBo.saveListObatPo(permintaanVendor, po);
            }catch (JSONException e){
                logger.error("[PermintaanVendorAction.savePermintaanPO] Error when save permintaan po", e);
            }
        }catch (GeneralBOException e){
            logger.error("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. ", e);
            addActionError("[PermintaanVendorAction.savePermintaanPO] ERROR error when get searh obat. " + e.getMessage());
        }

        logger.info("[PermintaanVendorAction.savePermintaanPO] END <<<<<<<");
        return SUCCESS;
    }


    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
