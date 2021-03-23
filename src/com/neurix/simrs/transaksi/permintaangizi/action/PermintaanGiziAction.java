package com.neurix.simrs.transaksi.permintaangizi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.permintaangizi.bo.PermintaanGiziBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
            logger.error("[PermintaanGiziAction.save] Error when searching rawat inap by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);
        setRawatInap(rawatInap);
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
                        reportParams.put("tglLahir",rawatInap.getTglLahir());
                        reportParams.put("idPasien",rawatInap.getIdPasien());
                        if(rawatInap.getNoRuangan() != null && !"".equalsIgnoreCase(rawatInap.getNoRuangan())){
                            reportParams.put("ruang",rawatInap.getNamaRangan()+" ["+rawatInap.getNoRuangan()+"]");
                        }else{
                            reportParams.put("ruang",rawatInap.getNamaRangan());
                        }
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
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        RawatInap rawatInap = new RawatInap();
        rawatInap.setStTglTo(tglToday);
        rawatInap.setStTglFrom(tglToday);
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        return "search";
    }

    public CrudResponse updateGizi(String data){
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanGiziBo permintaanGiziBo = (PermintaanGiziBo) ctx.getBean("permintaanGiziBoProxy");

        try {
            JSONArray json = new JSONArray(data);
            if (json != null) {
                try {
                    List<OrderGizi> orderGiziList = new ArrayList<>();
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject obj = json.getJSONObject(i);
                        OrderGizi orderGizi = new OrderGizi();
                        orderGizi.setIdOrderGizi(obj.getString("id_order_gizi"));
                        if(obj.has("keterangan")){
                            orderGizi.setKeterangan(obj.getString("keterangan"));
                        }
                        orderGizi.setApproveFlag(obj.getString("status"));
                        orderGizi.setLastUpdate(now);
                        orderGizi.setLastUpdateWho(userLogin);
                        orderGizi.setAction("U");
                        orderGizi.setFlag("Y");
                        orderGiziList.add(orderGizi);
                    }
                    response = permintaanGiziBo.updateGizi(orderGiziList);
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error :" + e.getMessage());
                    logger.error("[OrderGiziAction.saveOrderGizi] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data json tidak ada...!");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Error when parse JSON Objact, " + e.getMessage());
        }
        return response;
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
