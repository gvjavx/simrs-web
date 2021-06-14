package com.neurix.simrs.master.ruangan.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.bo.TempatTidurBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TempatTidurAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(TempatTidurAction.class);
    private TempatTidurBo tempatTidurBoProxy;
    private TempatTidur tempatTidur;

    public TempatTidur initTempatTidur(String id) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");
        TempatTidur tempatTidur = new TempatTidur();
        List<TempatTidur> tempatTidurList = new ArrayList<>();
        if (id != null && !"".equalsIgnoreCase(id)) {
            TempatTidur tt = new TempatTidur();
            tt.setIdTempatTidur(id);
            tt.setBranchId(CommonUtil.userBranchLogin());
            try {
                tempatTidurList = tempatTidurBo.getDataTempatTidur(tt);
            } catch (GeneralBOException e) {
                logger.error(e.getMessage());
            }
        }
        if (tempatTidurList.size() > 0) {
            tempatTidur = tempatTidurList.get(0);
        }
        return tempatTidur;
    }

    @Override
    public String search() {
        TempatTidur tempatTidur = getTempatTidur();
        tempatTidur.setBranchId(CommonUtil.userBranchLogin());
        List<TempatTidur> tempatTidurList = new ArrayList();
        try {
            tempatTidurList = tempatTidurBoProxy.getDataTempatTidur(tempatTidur);
        } catch (GeneralBOException e) {
            logger.error(e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", tempatTidurList);
        setTempatTidur(tempatTidur);
        return "search";
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        setTempatTidur(new TempatTidur());
        return "search";
    }

    public CrudResponse saveAdd(String data, String isNew) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        CrudResponse response = new CrudResponse();
        try {
            JSONArray json = new JSONArray(data);
            List<TempatTidur> tempatTidurList = new ArrayList<>();
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    TempatTidur tempatTidur = new TempatTidur();
                    String idRuangan = "";
                    if (obj.has("nama_ruangan")) {
                        tempatTidur.setNamaRuangan(obj.getString("nama_ruangan"));
                    }
                    if (obj.has("id_kelas_ruangan")) {
                        tempatTidur.setIdKelasRuangan(obj.getString("id_kelas_ruangan"));
                    }
                    if (obj.has("no_ruangan")) {
                        tempatTidur.setNoRuangan(obj.getString("no_ruangan"));
                    }
                    if (obj.has("tarif")) {
                        if(obj.getString("tarif") != null && !"".equalsIgnoreCase(obj.getString("tarif"))){
                            tempatTidur.setTarif(new BigInteger(obj.getString("tarif")));
                        }
                    }
                    if (obj.has("id_ruangan")) {
                        tempatTidur.setIdRuangan(obj.getString("id_ruangan"));
                        idRuangan = obj.getString("id_ruangan");
                    }
                    tempatTidur.setNamaTempatTidur(obj.getString("nama_tempat_tidur"));
                    tempatTidur.setCreatedWho(userLogin);
                    tempatTidur.setLastUpdate(updateTime);
                    tempatTidur.setCreatedDate(updateTime);
                    tempatTidur.setLastUpdateWho(userLogin);
                    tempatTidur.setAction("C");
                    tempatTidur.setFlag("Y");
                    tempatTidur.setBranchId(branchId);
                    List<MtSimrsRuanganTempatTidurEntity> tidurEntity = new ArrayList<>();
                    try {
                        tidurEntity = tempatTidurBo.cekTT(obj.getString("nama_tempat_tidur"), idRuangan);
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("Mohon maaf tidak dapat menukan data...!");
                    }
                    if(tidurEntity.size() > 0){
                        response.setStatus("error");
                        response.setMsg("Data tempat tidur "+obj.getString("nama_tempat_tidur")+" sudah ada..!");
                    }else{
                        tempatTidurList.add(tempatTidur);
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }
                }
                if (tempatTidurList.size() > 0 && "success".equalsIgnoreCase(response.getStatus())) {
                    response = tempatTidurBo.saveAdd(tempatTidurList, isNew);
                } else {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf tidak dapat menukan data...!");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Mohon maaf tidak dapat menukan data...!");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf data json yang dikirim bermasalah...!" + e.getMessage());
        }
        return response;
    }

    public CrudResponse saveEdit(String data) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            JSONObject object = new JSONObject(data);
            if (object != null) {
                TempatTidur tempatTidur = new TempatTidur();
                tempatTidur.setIdTempatTidur(object.getString("id_tempat_tidur"));
                tempatTidur.setNamaTempatTidur(object.getString("nama_tempat_tidur"));
                tempatTidur.setLastUpdate(updateTime);
                tempatTidur.setLastUpdateWho(userLogin);
                tempatTidur.setAction("U");
                tempatTidur.setFlag("Y");
                response = tempatTidurBo.saveEdit(tempatTidur);
            } else {
                response.setStatus("error");
                response.setMsg("Data json yang dikirim tidak dapat ditemukan...!");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Data json yang dikirim bermasalaah...!" + e.getMessage());
        }
        return response;
    }

    public CrudResponse saveDelete(String id) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");
        CrudResponse response = new CrudResponse();
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                TempatTidur tempatTidur = new TempatTidur();
                tempatTidur.setIdTempatTidur(id);
                tempatTidur.setLastUpdate(updateTime);
                tempatTidur.setLastUpdateWho(userLogin);
                tempatTidur.setAction("D");
                tempatTidur.setFlag("N");
                response = tempatTidurBo.saveEdit(tempatTidur);
            } catch (GeneralBOException e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("error saat delete data...!" + e.getMessage());
            }
        }
        return response;
    }

    public List<Ruangan> getRuanganByBranch(String idKelas) {
        List<Ruangan> ruanganList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        Ruangan ruangan = new Ruangan();
        ruangan.setBranchId(branchId);
        ruangan.setIdKelasRuangan(idKelas);
        ruangan.setFlag("Y");
        try {
            ruanganList = tempatTidurBo.getComboRuangan(ruangan);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ruanganList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public TempatTidur getTempatTidur() {
        return tempatTidur;
    }

    public void setTempatTidur(TempatTidur tempatTidur) {
        this.tempatTidur = tempatTidur;
    }

    public void setTempatTidurBoProxy(TempatTidurBo tempatTidurBoProxy) {
        this.tempatTidurBoProxy = tempatTidurBoProxy;
    }
}