package com.neurix.simrs.transaksi.asesmenrawatinap.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AsesmenRawatInapAction {

    public static transient Logger logger = Logger.getLogger(AsesmenRawatInapAction.class);

    public CrudResponse saveAsesmenRawat(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        JSONArray json = new JSONArray(data);

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
            asesmenRawatInap.setParameter(obj.getString("parameter"));
            asesmenRawatInap.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenRawatInap.setKeterangan(obj.getString("keterangan"));

            if(obj.has("jawaban")){
                asesmenRawatInap.setJawaban(obj.getString("jawaban"));
            }
            if(obj.has("jenis")){
                asesmenRawatInap.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                asesmenRawatInap.setSkor(Integer.valueOf(obj.getString("skor")));
            }

            asesmenRawatInap.setAction("C");
            asesmenRawatInap.setFlag("Y");
            asesmenRawatInap.setCreatedWho(userLogin);
            asesmenRawatInap.setCreatedDate(time);
            asesmenRawatInap.setLastUpdateWho(userLogin);
            asesmenRawatInap.setLastUpdate(time);

            try {
                response = asesmenRawatInapBo.saveAdd(asesmenRawatInap);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }
        return response;
    }

    public List<AsesmenRawatInap> getListAsesmenRawat(String idDetailCheckup, String keterangan) {
        List<AsesmenRawatInap> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                asesmenRawatInap.setIdDetailCheckup(idDetailCheckup);
                asesmenRawatInap.setKeterangan(keterangan);
                list = asesmenRawatInapBo.getByCriteria(asesmenRawatInap);
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
