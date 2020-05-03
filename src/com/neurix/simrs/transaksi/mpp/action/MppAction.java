package com.neurix.simrs.transaksi.mpp.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.mpp.bo.MppBo;
import com.neurix.simrs.transaksi.mpp.model.Mpp;
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

public class MppAction {

    public static transient Logger logger = Logger.getLogger(MppAction.class);
    private String jenis;

    public CrudResponse saveMpp(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MppBo mppBo = (MppBo) ctx.getBean("mppBoProxy");
        JSONArray json = new JSONArray(data);

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Mpp mpp = new Mpp();
            mpp.setParameter(obj.getString("parameter"));
            mpp.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            mpp.setJawaban(obj.getString("jawaban"));
            mpp.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                mpp.setJenis(obj.getString("jenis"));
            }
            if(obj.has("waktu")){
                mpp.setWaktu(Timestamp.valueOf(obj.getString("waktu")));
            }

            mpp.setAction("C");
            mpp.setFlag("Y");
            mpp.setCreatedWho(userLogin);
            mpp.setCreatedDate(time);
            mpp.setLastUpdateWho(userLogin);
            mpp.setLastUpdate(time);

            try {
                response = mppBo.saveAdd(mpp);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }
        return response;
    }

    public List<Mpp> getListMpp(String idDetailCheckup, String keterangan) {
        List<Mpp> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MppBo mppBo = (MppBo) ctx.getBean("mppBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Mpp mpp = new Mpp();
                mpp.setIdDetailCheckup(idDetailCheckup);
                mpp.setKeterangan(keterangan);
                list = mppBo.getByCriteria(mpp);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public static Logger getLogger() {
        return logger;
    }
}