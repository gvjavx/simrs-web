package com.neurix.simrs.transaksi.asesmenugd.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenugd.bo.AsesmenUgdBo;
import com.neurix.simrs.transaksi.asesmenugd.model.AsesmenUgd;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AsesmenUgdAction {

    public static transient Logger logger = Logger.getLogger(AsesmenUgdAction.class);

    public CrudResponse saveAsesmenUgd(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");
        JSONArray json = new JSONArray(data);
        List<AsesmenUgd> ugdList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenUgd asesmenUgd = new AsesmenUgd();
            asesmenUgd.setParameter(obj.getString("parameter"));
            asesmenUgd.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenUgd.setJawaban(obj.getString("jawaban"));
            asesmenUgd.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                asesmenUgd.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                asesmenUgd.setSkor(Integer.valueOf(obj.getString("skor")));
            }

            asesmenUgd.setAction("C");
            asesmenUgd.setFlag("Y");
            asesmenUgd.setCreatedWho(userLogin);
            asesmenUgd.setCreatedDate(time);
            asesmenUgd.setLastUpdateWho(userLogin);
            asesmenUgd.setLastUpdate(time);
            ugdList.add(asesmenUgd);
        }
        try {
            response = asesmenUgdBo.saveAdd(ugdList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public List<AsesmenUgd> getListAsesmenUgd(String idDetailCheckup, String keterangan) {
        List<AsesmenUgd> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenUgd asesmenUgd = new AsesmenUgd();
                asesmenUgd.setIdDetailCheckup(idDetailCheckup);
                asesmenUgd.setKeterangan(keterangan);
                list = asesmenUgdBo.getByCriteria(asesmenUgd);
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
