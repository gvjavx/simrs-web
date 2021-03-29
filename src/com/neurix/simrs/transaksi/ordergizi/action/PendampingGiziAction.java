package com.neurix.simrs.transaksi.ordergizi.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.ordergizi.bo.PendampingGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.PendampingGizi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PendampingGiziAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(PendampingGiziAction.class);
    private PendampingGiziBo pendampingGiziBoProxy;
    private PendampingGizi pendampingGizi;

    public CrudResponse savePendampingGizi(String data) {
        logger.info("[PendampingGiziAction.savePendampingGizi] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendampingGiziBo pendampingGiziBo = (PendampingGiziBo) ctx.getBean("pendampingGiziBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        try {
            JSONArray json = new JSONArray(data);
            if (json != null) {
                try {
                    List<PendampingGizi> pendampingGiziList = new ArrayList<>();
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject obj = json.getJSONObject(i);
                        PendampingGizi pendampingGizi = new PendampingGizi();
                        pendampingGizi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                        pendampingGizi.setNama(obj.getString("nama"));
                        pendampingGizi.setTipe(obj.getString("tipe"));
                        pendampingGizi.setCreatedWho(userLogin);
                        pendampingGizi.setCreatedDate(updateTime);
                        pendampingGizi.setLastUpdate(updateTime);
                        pendampingGizi.setLastUpdateWho(userLogin);
                        pendampingGizi.setAction("C");
                        pendampingGizi.setFlag("Y");
                        pendampingGiziList.add(pendampingGizi);
                    }
                    if (pendampingGiziList.size() > 0) {
                        pendampingGiziBo.saveAdd(pendampingGiziList);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error :" + e.getMessage());
                    logger.error("[PendampingGiziAction.savePendampingGizi] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data json tidak ada...!");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error when parse JSON Objact, " + e.getMessage());
        }
        logger.info("[PendampingGiziAction.savePendampingGizi] end process >>>");
        return response;
    }

    public List<PendampingGizi> listPendampingGizi(String idDetailCheckup) {
        logger.info("[PendampingGiziAction.listPendampingGizi] start process >>>");
        List<PendampingGizi> pendampingGizis = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendampingGiziBo pendampingGiziBo = (PendampingGiziBo) ctx.getBean("pendampingGiziBoProxy");

        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            PendampingGizi pendampingGizi = new PendampingGizi();
            pendampingGizi.setIdDetailCheckup(idDetailCheckup);
            try {
                pendampingGizis = pendampingGiziBo.getByCriteria(pendampingGizi);
            } catch (GeneralBOException e) {
                logger.info("[PendampingGiziAction.listPendampingGizi] error when search list of pendamping gizi, " + e.getMessage());
            }
        }

        logger.info("[PendampingGiziAction.listPendampingGizi] end process >>>");
        return pendampingGizis;
    }

    public CrudResponse editPendampingGizi(String id, String nama) {
        logger.info("[PendampingGiziAction.editPendampingGizi] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendampingGiziBo pendampingGiziBo = (PendampingGiziBo) ctx.getBean("pendampingGiziBoProxy");
        try {
            PendampingGizi pendampingGizi = new PendampingGizi();
            pendampingGizi.setIdPendampingGizi(id);
            pendampingGizi.setNama(nama);
            pendampingGizi.setLastUpdate(updateTime);
            pendampingGizi.setLastUpdateWho(userLogin);
            pendampingGizi.setAction("U");
            pendampingGizi.setFlag("Y");
            pendampingGiziBo.saveEdit(pendampingGizi);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Foun Error when save edit order gizi :" + e.getMessage());
            logger.error("[PendampingGiziAction.editPendampingGizi] Error when edit item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
        }
        logger.info("[PendampingGiziAction.editPendampingGizi] end process >>>");
        return response;
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PendampingGiziAction.initForm] start process >>>");
        PendampingGizi pendampingGizi = new PendampingGizi();
        setPendampingGizi(pendampingGizi);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[PendampingGiziAction.initForm] end process >>>");
        return "search";
    }

    public PendampingGizi getPendampingGizi() {
        return pendampingGizi;
    }

    public void setPendampingGizi(PendampingGizi pendampingGizi) {
        this.pendampingGizi = pendampingGizi;
    }

    public void setPendampingGiziBoProxy(PendampingGiziBo pendampingGiziBoProxy) {
        this.pendampingGiziBoProxy = pendampingGiziBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }
}