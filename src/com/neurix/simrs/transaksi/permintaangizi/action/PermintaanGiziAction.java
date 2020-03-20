package com.neurix.simrs.transaksi.permintaangizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.permintaangizi.bo.PermintaanGiziBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PermintaanGiziAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(PermintaanGiziAction.class);
    private RawatInap rawatInap;
    private PermintaanGiziBo permintaanGiziBoProxy;
    private String id;
    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setPermintaanGiziBoProxy(PermintaanGiziBo permintaanGiziBoProxy) {
        this.permintaanGiziBoProxy = permintaanGiziBoProxy;
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
        logger.info("[PermintaanGiziAction.search] start process >>>");

        RawatInap rawatInap = getRawatInap();
        rawatInap.setBranchId(CommonUtil.userBranchLogin());
        List<RawatInap> listOfRawatInap = new ArrayList();

        try {
            listOfRawatInap = permintaanGiziBoProxy.getListOrderGizi(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanGiziAction.save] Error when searching rawat inap by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[PermintaanGiziAction.search] end process <<<");
        return "search";
    }

    public List<OrderGizi> getListOrderGizi(String idRawatInap){
        List<OrderGizi> orderGiziList = new ArrayList<>();
        if(idRawatInap != null && !"".equalsIgnoreCase(idRawatInap)){
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdRawatInap(idRawatInap);

            try {
                orderGiziList = orderGiziBo.getByCriteria(orderGizi);
            }catch (GeneralBOException e){
                logger.error("[PermintaanGiziAcnrion] Found Error when search list order gizi : "+e);
            }
        }
        return orderGiziList;
    }

    public CheckResponse updateApproveFLag(String idOrderGizi){
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        CheckResponse response = new CheckResponse();
        if(idOrderGizi != null && !"".equalsIgnoreCase(idOrderGizi)){
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PermintaanGiziBo permintaanGiziBo = (PermintaanGiziBo) ctx.getBean("permintaanGiziBoProxy");
            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrderGizi);
            orderGizi.setLastUpdate(now);
            orderGizi.setLastUpdateWho(userLogin);

            try {
                response = permintaanGiziBo.updateApproveFlag(orderGizi);
            }catch (GeneralBOException e){
                logger.error("[PermintaanGiziAction.updateApproveFlag Found Eror when update order gizi]");
            }
        }
        return response;
    }

    public String printBarcodeGizi(){

        String id = getId();
        String idOrder = getOrder();

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (RawatInap rawatInap : listOfResult) {
                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {

                        reportParams.put("idOrderGizi",idOrder);
                        reportParams.put("nama",rawatInap.getNamaPasien());
                        reportParams.put("ruang",rawatInap.getNamaRangan()+" ["+rawatInap.getNoRuangan()+"]");
                        break;
                    }
                }
            }
        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_barcode_gizi";
    }

    @Override
    public String initForm() {
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
