package com.neurix.simrs.transaksi.paketperiksa.action;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(PaketPeriksaAction.class);

    private PaketPeriksaBo paketPeriksaBoProxy;
    //private MasterVendorBo masterVendorBoProxy;
    private PaketPeriksa paketPeriksa;

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public String add(){
        PaketPeriksa paketPeriksa = new PaketPeriksa();
        setPaketPeriksa(paketPeriksa);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "init_add";
    }

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    public PaketPeriksa getPaketPeriksa() {
        return paketPeriksa;
    }

    public void setPaketPeriksa(PaketPeriksa paketPeriksa) {
        this.paketPeriksa = paketPeriksa;
    }
}
