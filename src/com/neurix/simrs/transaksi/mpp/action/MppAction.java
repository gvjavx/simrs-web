package com.neurix.simrs.transaksi.mpp.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.mpp.bo.MppBo;
import com.neurix.simrs.transaksi.mpp.model.Mpp;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
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

    public CrudResponse saveMpp(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MppBo mppBo = (MppBo) ctx.getBean("mppBoProxy");
        try {
            List<Mpp> mppList = new ArrayList<>();
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
                mppList.add(mpp);
            }
            if (mppList.size() > 0){
                try {
                    response = mppBo.saveAdd(mppList);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                        JSONObject obj = new JSONObject(dataPasien);
                        if (obj != null) {
                            StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                            status.setNoCheckup(obj.getString("no_checkup"));
                            status.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                            status.setIdPasien(obj.getString("id_pasien"));
                            status.setIdRekamMedisPasien(obj.getString("id_rm"));
                            status.setIsPengisian("Y");
                            status.setAction("C");
                            status.setFlag("Y");
                            status.setCreatedWho(userLogin);
                            status.setCreatedDate(time);
                            status.setLastUpdateWho(userLogin);
                            status.setLastUpdate(time);
                            response = rekamMedikBo.saveAdd(status);
                        }
                    }
                } catch (GeneralBOException e) {
                    response.setStatus("Error");
                    response.setMsg("Found Error " + e.getMessage());
                    return response;
                }
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg(e.getMessage());
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

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien) {
        CrudResponse response = new CrudResponse();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MppBo mppBo = (MppBo) ctx.getBean("mppBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Mpp mpp = new Mpp();
                mpp.setIdDetailCheckup(idDetailCheckup);
                mpp.setKeterangan(keterangan);
                mpp.setLastUpdate(time);
                mpp.setLastUpdateWho(userLogin);
                response = mppBo.saveDelete(mpp);
                if("success".equalsIgnoreCase(response.getStatus())){
                    try {
                        JSONObject obj = new JSONObject(dataPasien);
                        RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                        if (obj != null) {
                            StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                            status.setNoCheckup(obj.getString("no_checkup"));
                            status.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                            status.setIdPasien(obj.getString("id_pasien"));
                            status.setIdRekamMedisPasien(obj.getString("id_rm"));
                            status.setLastUpdateWho(userLogin);
                            status.setLastUpdate(time);
                            response = rekamMedikBo.saveEdit(status);
                        }
                    }catch (JSONException e){
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                    }
                }
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
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