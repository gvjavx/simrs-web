package com.neurix.simrs.master.keteranganobat.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.transaksi.CrudResponse;
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
import java.util.stream.Collectors;

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

        logger.info("[KeteranganObatAction.getListKeteranganObatBySubJenisObatAndParam] END <<< ");
        return keteranganObats;
    }

    public String initForm(){
        logger.info("[KeteranganObatAction.initForm] START >>> ");
        resetAllSession();
        logger.info("[KeteranganObatAction.initForm] END <<< ");
        return "search";
    }

    private void resetAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfKeteranganObat");
    }

    public List<KeteranganObat> search(String jsonString) throws JSONException{
        logger.info("[KeteranganObatAction.search] START >>> ");

        KeteranganObat keteranganObat = new KeteranganObat();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++){
            JSONObject jsonObject = json.getJSONObject(i);
            keteranganObat.setKeterangan(jsonObject.getString("keterangan"));
            keteranganObat.setIdParameterKeterangan(jsonObject.getString("id_parameter"));
            keteranganObat.setIdSubJenis(jsonObject.getString("id_sub_jenis"));
            keteranganObat.setIdJenis(jsonObject.getString("id_jenis"));
        }

        List<KeteranganObat> results = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            results = keteranganObatBo.getListSearchByCriteria(keteranganObat);
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.search] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfKeteranganObat");
        session.setAttribute("listOfKeteranganObat",results);

        logger.info("[KeteranganObatAction.search] END <<< ");
        return results;
    }

    public List<ImSimrsParameterKeteranganObatEntity> getAllParameterKeterangan(){
        logger.info("[KeteranganObatAction.getAllParameterKeterangan] START >>> ");

        List<ImSimrsParameterKeteranganObatEntity> results = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            results = keteranganObatBo.getAllParameterKeterangan();
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getAllParameterKeterangan] ERROR. ", e);
        }

        logger.info("[KeteranganObatAction.getAllParameterKeterangan] END <<< ");
        return results;
    }

    public List<ImSimrsJenisPersediaanObatEntity> getAllJenisPersediaanObat(){
        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] START >>> ");

        List<ImSimrsJenisPersediaanObatEntity> results = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            results = keteranganObatBo.getAllJenisPersedian();
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getAllJenisPersediaanObat] ERROR. ", e);
        }

        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] END <<< ");
        return results;
    }

    public List<ImSimrsJenisPersediaanObatSubEntity> getAllJenisPersediaanSubByIdJenis(String idJenis){
        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] START >>> ");

        List<ImSimrsJenisPersediaanObatSubEntity> results = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            results = keteranganObatBo.getAllJenisPersediaanSub();
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.getAllJenisPersediaanObat] ERROR. ", e);
        }

        List<ImSimrsJenisPersediaanObatSubEntity> filteredResults = results.stream().filter(p->p.getIdJenisObat().equalsIgnoreCase(idJenis)).collect(Collectors.toList());
        if (filteredResults != null && results.size() > 0){
            logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] END <<< ");
            return filteredResults;
        }

        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] END <<< ");
        return null;
    }

    public CrudResponse saveAdd(String jsonString){
        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] START >>> ");

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();
        KeteranganObat keteranganObat = new KeteranganObat();
        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0 ; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                keteranganObat.setIdSubJenis(jsonObject.getString("id_sub_jenis"));
                keteranganObat.setIdParameterKeterangan(jsonObject.getString("id_parameter_keterangan"));
                keteranganObat.setKeterangan(jsonObject.getString("keterangan"));
                keteranganObat.setWarnaLabel(jsonObject.getString("warna_label"));
                keteranganObat.setWarnaBackground(jsonObject.getString("warna_background"));
            }

        } catch (JSONException e){
            logger.error("[KeteranganObatAction.saveAdd] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("ERROR. " + e);
        }

        keteranganObat.setCreatedDate(times);
        keteranganObat.setCreatedWho(userLogin);
        keteranganObat.setLastUpdate(times);
        keteranganObat.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            keteranganObatBo.saveAdd(keteranganObat);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.saveAdd] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("ERROR. " + e);
        }

        logger.info("[KeteranganObatAction.getAllJenisPersediaanObat] END <<< ");
        return response;
    }

    public CrudResponse saveEdit(String jsonString){
        logger.info("[KeteranganObatAction.saveEdit] START >>> ");

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();
        KeteranganObat keteranganObat = new KeteranganObat();
        try {

            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0 ; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                keteranganObat.setId(jsonObject.getString("id"));
                keteranganObat.setIdSubJenis(jsonObject.getString("id_sub_jenis"));
                keteranganObat.setIdParameterKeterangan(jsonObject.getString("id_parameter_keterangan"));
                keteranganObat.setKeterangan(jsonObject.getString("keterangan"));
                keteranganObat.setFlag(jsonObject.getString("flag"));
                keteranganObat.setWarnaLabel(jsonObject.getString("warna_label"));
                keteranganObat.setWarnaBackground(jsonObject.getString("warna_background"));
            }

        } catch (JSONException e){
            logger.error("[KeteranganObatAction.saveEdit] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("ERROR. " + e);
        }

        keteranganObat.setLastUpdate(times);
        keteranganObat.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganObatBo keteranganObatBo = (KeteranganObatBo) ctx.getBean("keteranganObatBoProxy");

        try {
            keteranganObatBo.saveEdit(keteranganObat);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[KeteranganObatAction.saveEdit] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("ERROR. " + e);
        }

        logger.info("[KeteranganObatAction.saveEdit] END <<< ");
        return response;
    }

    public KeteranganObat getFromSession(String id){
        logger.info("[KeteranganObatAction.getFromSession] START >>>");
        KeteranganObat keteranganObat = new KeteranganObat();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KeteranganObat> listOfKeteranganObat = (List<KeteranganObat>) session.getAttribute("listOfKeteranganObat");

        if (listOfKeteranganObat != null && listOfKeteranganObat.size() > 0 && id != null && !"".equalsIgnoreCase(id)){
            List<KeteranganObat> filteredList = listOfKeteranganObat.stream().filter(p->p.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
            if (filteredList != null && filteredList.size() > 0){
                keteranganObat = filteredList.get(0);
            }
        }

        logger.info("[KeteranganObatAction.getFromSession] END <<<");
        return keteranganObat;
    }
}
