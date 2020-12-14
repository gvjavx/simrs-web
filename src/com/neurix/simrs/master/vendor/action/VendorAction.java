package com.neurix.simrs.master.vendor.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VendorAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(VendorAction.class);
    private Vendor vendor;
    private VendorBo vendorBoProxy;

    public void setVendorBoProxy(VendorBo vendorBoProxy) {
        this.vendorBoProxy = vendorBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String add() {
        return null;
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
        logger.info("[VendorAction.search] START process >>>");

        Vendor vendor = getVendor();
        List<Vendor> vendorList = new ArrayList();

        try {
            vendorList = vendorBoProxy.getByCriteria(vendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[VendorAction.serach] Error when searching vendor by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", vendorList);

        logger.info("[VendorAction.search] END process >>>");
        return "search";

    }

    @Override
    public String initForm() {
        logger.info("[VendorAction.initForm] START process >>>");
        String userBranch = CommonUtil.userBranchLogin();

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        Vendor vendor = new Vendor();
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            vendor.setIsKP("Y");
            logger.info("====>>> isKP ="+ vendor.getIsKP());
        }
        setVendor(vendor);

        logger.info("[VendorAction.initForm] END process >>>");
        return "search";
    }

    public CheckResponse saveVendor(String data) {
        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");

        if (data != null && !"".equalsIgnoreCase(data)) {
            try {

                Vendor vendor = new Vendor();
                JSONObject obj = new JSONObject(data);
                vendor.setNamaVendor(obj.getString("nama_vendor"));
                vendor.setNpwp(obj.getString("npwp"));
                vendor.setAlamat(obj.getString("alamat"));
                vendor.setEmail(obj.getString("email"));
                vendor.setNoTelp(obj.getString("no_telp"));
                vendor.setFlag("Y");
                vendor.setAction("C");
                vendor.setCreatedWho(userLogin);
                vendor.setCreatedDate(updateTime);
                vendor.setLastUpdate(updateTime);
                vendor.setLastUpdateWho(userLogin);

                try {
                    response = vendorBo.saveAdd(vendor);
                }catch (GeneralBOException e){
                    response.setStatus("error");
                    response.setMessage("Found Error when save add vendor "+e.getMessage());
                    logger.error("Found Error when save add vendor "+e.getMessage());
                }

            } catch (JSONException e) {
                response.setStatus("error");
                response.setMessage("Found Error when convert json to data "+e);
                logger.error("[VendorAction.saveVendor] Error Convert json to data vendor.", e);
            }
        }

        return response;
    }

    public CheckResponse saveEditVendor(String data) {

        CheckResponse response = new CheckResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");

        if (data != null && !"".equalsIgnoreCase(data)) {
            try {

                Vendor vendor = new Vendor();
                JSONObject obj = new JSONObject(data);
                vendor.setIdVendor(obj.getString("id_vendor"));
                vendor.setNamaVendor(obj.getString("nama_vendor"));
                vendor.setNpwp(obj.getString("npwp"));
                vendor.setAlamat(obj.getString("alamat"));
                vendor.setEmail(obj.getString("email"));
                vendor.setNoTelp(obj.getString("no_telp"));
                vendor.setAction("U");
                vendor.setLastUpdate(updateTime);
                vendor.setLastUpdateWho(userLogin);

                try {
                    response = vendorBo.saveEdit(vendor);
                }catch (GeneralBOException e){
                    response.setStatus("error");
                    response.setMessage("Found Error when save edit vendor "+e.getMessage());
                    logger.error("Found Error when save edit vendor "+e.getMessage());
                }

            } catch (JSONException e) {
                response.setStatus("error");
                response.setMessage("Found Error when convert json to data "+e);
                logger.error("[VendorAction.saveVendor] Error Convert json to data vendor.", e);
            }
        }

        return response;
    }

    public List<Vendor> getListVendor(){
        logger.info("[VendorAction.getListVendor] START process >>>");

        List<Vendor> vendorList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VendorBo vendorBo = (VendorBo) ctx.getBean("vendorBoProxy");
        Vendor vendor = new Vendor();

        try {
            vendorList = vendorBo.getByCriteria(vendor);
        } catch (HibernateException e) {
            logger.error("[VendorAction.getListVendor] Error when get data for combo list of Vendor", e);
            addActionError(" Error when get data for combo list of Vendor" + e.getMessage());
        }

        logger.info("[VendorAction.getListVendor] END process <<<");
        return vendorList;
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
