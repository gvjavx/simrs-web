package com.neurix.simrs.transaksi.icu.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.icu.bo.HeaderIcuBo;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IcuAction {

    public static transient Logger logger = Logger.getLogger(IcuAction.class);

    public CrudResponse save(String data, String cek) throws JSONException{

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");
        JSONArray json = new JSONArray(data);
        Boolean isNew = Boolean.valueOf(cek);

        List<HeaderIcu> headerIcuList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            HeaderIcu headerIcu = new HeaderIcu();

            if(obj.has("id_header_icu")){
                headerIcu.setIdHeaderIcu(obj.getString("id_header_icu"));
            }

            if(obj.has("jenis")){
                headerIcu.setJenis(obj.getString("jenis"));
            }

            headerIcu.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            headerIcu.setKategori(obj.getString("kategori"));
            headerIcu.setNilai(obj.getString("nilai"));
            headerIcu.setWaktu(obj.getString("waktu"));
            headerIcu.setAction("C");
            headerIcu.setFlag("Y");
            headerIcu.setCreatedWho(userLogin);
            headerIcu.setCreatedDate(time);
            headerIcu.setLastUpdateWho(userLogin);
            headerIcu.setLastUpdate(time);
            headerIcuList.add(headerIcu);
        }

        if(headerIcuList.size() > 0){
            HeaderIcu headIcu = headerIcuList.get(0);
            Boolean cekData = headerIcuBo.cekData(headIcu.getIdDetailCheckup(), headIcu.getWaktu(), headIcu.getKategori());

            if(cekData){
                response.setStatus("error");
                response.setMsg("[Found Error], Data pada jam "+headIcu.getWaktu()+" Sudah ada dalam database...!");
            }else {
                try {
                    response = headerIcuBo.saveAdd(headerIcuList, isNew);
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    return response;
                }
            }
        }
        return response;
    }

    public List<HeaderIcu> getListDetailParameter(String idDetailCheckup, String kategori) {
        List<HeaderIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(kategori)) {
            try {
                HeaderIcu headerIcu = new HeaderIcu();
                headerIcu.setIdDetailCheckup(idDetailCheckup);
                headerIcu.setKategori(kategori);
                list = headerIcuBo.getByCriteria(headerIcu);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }

        return list;
    }

    public List<HeaderIcu> getListDetail(String idDetailCheckup, String kategori) {
        List<HeaderIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(kategori)) {
            try {
                list = headerIcuBo.getListDetail(idDetailCheckup, kategori);
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