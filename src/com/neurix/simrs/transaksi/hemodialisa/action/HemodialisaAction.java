package com.neurix.simrs.transaksi.hemodialisa.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.HemodialisaBo;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HemodialisaAction {

    public static transient Logger logger = Logger.getLogger(HemodialisaAction.class);

    public CrudResponse saveHemodialisa(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        JSONArray json = new JSONArray(data);

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Hemodialisa hemodialisa = new Hemodialisa();
            hemodialisa.setParameter(obj.getString("parameter"));
            hemodialisa.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            hemodialisa.setJawaban(obj.getString("jawaban"));
            hemodialisa.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                hemodialisa.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                hemodialisa.setSkor(Integer.valueOf(obj.getString("skor")));
            }

            hemodialisa.setAction("C");
            hemodialisa.setFlag("Y");
            hemodialisa.setCreatedWho(userLogin);
            hemodialisa.setCreatedDate(time);
            hemodialisa.setLastUpdateWho(userLogin);
            hemodialisa.setLastUpdate(time);

            try {
                response = hemodialisaBo.saveAdd(hemodialisa);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }
        return response;
    }

    public List<Hemodialisa> getListHemodialisa(String idDetailCheckup, String keterangan) {
        List<Hemodialisa> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Hemodialisa hemodialisa = new Hemodialisa();
                hemodialisa.setIdDetailCheckup(idDetailCheckup);
                hemodialisa.setKeterangan(keterangan);
                list = hemodialisaBo.getByCriteria(hemodialisa);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public static Logger getLogger() {
        return logger;
    }
}