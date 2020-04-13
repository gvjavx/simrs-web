package com.neurix.simrs.transaksi.asesmenoperasi.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.AsesmenOperasiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AsesmenOperasiAction {

    public static transient Logger logger = Logger.getLogger(AsesmenOperasiAction.class);

    public CrudResponse saveAsesmenOperasi(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        JSONArray json = new JSONArray(data);

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenOperasi asesmenOperasi = new AsesmenOperasi();
            asesmenOperasi.setParameter(obj.getString("parameter"));
            asesmenOperasi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            if(obj.has("jawaban1")){
                if(!"".equalsIgnoreCase(obj.getString("jawaban1"))){
                    asesmenOperasi.setJawaban1(obj.getString("jawaban1"));
                }
            }
            if(obj.has("jawaban2")){
                if(!"".equalsIgnoreCase(obj.getString("jawaban2"))){
                    asesmenOperasi.setJawaban2(obj.getString("jawaban2"));
                }
            }
            asesmenOperasi.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                if(!"".equalsIgnoreCase(obj.getString("jenis"))){
                    asesmenOperasi.setJenis(obj.getString("jenis"));
                }
            }
            if(obj.has("skor")){
                if(!"".equalsIgnoreCase(obj.getString("skor"))){
                    asesmenOperasi.setSkor(Integer.valueOf(obj.getString("skor")));
                }
            }

            asesmenOperasi.setAction("C");
            asesmenOperasi.setFlag("Y");
            asesmenOperasi.setCreatedWho(userLogin);
            asesmenOperasi.setCreatedDate(time);
            asesmenOperasi.setLastUpdateWho(userLogin);
            asesmenOperasi.setLastUpdate(time);

            try {
                response = asesmenOperasiBo.saveAdd(asesmenOperasi);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }
        return response;
    }

    public List<AsesmenOperasi> getListAsesmenOperasi(String idDetailCheckup, String keterangan) {
        List<AsesmenOperasi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenOperasi operasi = new AsesmenOperasi();
                operasi.setIdDetailCheckup(idDetailCheckup);
                operasi.setKeterangan(keterangan);
                list = asesmenOperasiBo.getByCriteria(operasi);
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
