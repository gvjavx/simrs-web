package com.neurix.simrs.master.keteranganobat.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class KeteranganObatAction {
    private static transient Logger logger = Logger.getLogger(KeteranganObatAction.class);
    private KeteranganObatBo keteranganObatBoProxy;

    public void setKeteranganObatBoProxy(KeteranganObatBo keteranganObatBoProxy) {
        this.keteranganObatBoProxy = keteranganObatBoProxy;
    }

    public List<KeteranganObat> getListKeteranganObatBySubJenisObatAndParam(String idSubJenis, String idParam){
        logger.info("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] START >>> ");

        KeteranganObat keteranganObat = new KeteranganObat();
        keteranganObat.setIdSubJenis(idSubJenis);
        keteranganObat.setIdParameterKeterangan(idParam);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        try {
            keteranganObats = keteranganObatBo.getListSearchByCriteria(keteranganObat);
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfKeteranganObat");
//        session.setAttribute("listOfKeteranganObat",keteranganObat);

        logger.info("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] END <<< ");
        return keteranganObats;
    }

    public String initForm(){
        logger.info("[KeteranganObatAction.initForm] START >>> ");
        logger.info("[KeteranganObatAction.initForm] END <<< ");
        return "success";
    }

    private void resetAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfKeteranganObat");
    }

    public List<KeteranganObat> searchListKeteraganObat(String jsonString) throws JSONException{
        logger.info("[KeteranganObatAction.searchListKeteraganObat] START >>> ");

        KeteranganObat keteranganObat = new KeteranganObat();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++){
            JSONObject jsonObject = new JSONObject(i);
            keteranganObat.setKeterangan(jsonObject.getString("keterangan"));
            keteranganObat.setIdParameterKeterangan(jsonObject.getString("id_parameter"));
            keteranganObat.setIdSubJenis(jsonObject.getString(jsonObject.getString("id_sub_jenis")));
        }

        List<KeteranganObat> results = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            results = keteranganObatBo.getListSearchByCriteria(keteranganObat);
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[KeteranganObatAction.searchListKeteraganObat] END <<< ");
        return results;
    }
}
