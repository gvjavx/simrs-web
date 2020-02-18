package com.neurix.simrs.transaksi.kasirrawatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.kasirrawatinap.bo.KasirRawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KasirRawatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KasirRawatInapAction.class);
    private RawatInap rawatInap;
    private RawatInapBo rawatInapBoProxy;

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public static Logger getLogger() {
        return logger;
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

        logger.info("[KasirRawatInapAction.search] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KasirRawatInapAction.save] Error when searching rawat inap by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[KasirRawatInapAction.search] end process <<<");

        return "search";
    }

    @Override
    public String initForm() {

        logger.info("[KasirRawatInapAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        RawatInap rawatInap = new RawatInap();
        rawatInap.setStTglTo(tglToday);
        rawatInap.setStTglFrom(tglToday);
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KasirRawatInapAction.initForm] end process <<<");

        return "search";
    }

    public List<RiwayatTindakan> getListTindakanRawat(String idDetail) {
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){
            List<RiwayatTindakan> result = new ArrayList<>();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasirRawatInapBo kasirRawatInapBo = (KasirRawatInapBo) ctx.getBean("kasirRawatInapBoProxy");

            RiwayatTindakan tindakanRawat = new RiwayatTindakan();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setBranchId(CommonUtil.userBranchLogin());

            try {
                riwayatTindakanList = kasirRawatInapBo.getListAllTindakan(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
            }
        }

        return riwayatTindakanList;

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
