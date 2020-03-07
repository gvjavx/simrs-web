package com.neurix.simrs.master.vendor.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.model.Vendor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        session.setAttribute("listOfResult",vendorList);

        logger.info("[VendorAction.search] END process >>>");
        return "search";

    }

    @Override
    public String initForm() {
        logger.info("[VendorAction.initForm] START process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        Vendor vendor = new Vendor();
        setVendor(vendor);

        logger.info("[VendorAction.initForm] END process >>>");
        return "search";
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
