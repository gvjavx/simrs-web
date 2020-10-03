package com.neurix.simrs.master.parampemeriksaan.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.parampemeriksaan.bo.ParameterPemeriksaanBo;
import com.neurix.simrs.master.parampemeriksaan.model.ParameterPemeriksaan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ParameterPemeriksaanAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(ParameterPemeriksaanAction.class);
    private ParameterPemeriksaan pemeriksaan;
    private ParameterPemeriksaanBo parameterPemeriksaanBoProxy;

    public ParameterPemeriksaan initParameterPemeriksaan(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterPemeriksaanBo parameterPemeriksaanBo = (ParameterPemeriksaanBo) ctx.getBean("parameterPemeriksaanBoProxy");
        ParameterPemeriksaan pemeriksaan = new ParameterPemeriksaan();
        List<ParameterPemeriksaan> list = new ArrayList<>();
        ParameterPemeriksaan parameterPemeriksaan = new ParameterPemeriksaan();
        parameterPemeriksaan.setIdParameterPemeriksaan(id);
        try {
            list = parameterPemeriksaanBo.getByCriteria(parameterPemeriksaan);
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        if(list.size() > 0){
            pemeriksaan = list.get(0);
        }
        return pemeriksaan;
    }

    @Override
    public String search() {
        List<ParameterPemeriksaan> pemeriksaanList = new ArrayList<>();
        ParameterPemeriksaan parameterPemeriksaan = getPemeriksaan();
        try {
            pemeriksaanList = parameterPemeriksaanBoProxy.getByCriteria(parameterPemeriksaan);
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", pemeriksaanList);
        return "search";
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLabDetail");
        setPemeriksaan(new ParameterPemeriksaan());
        return "search";
    }

    public CrudResponse saveAdd(String data){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ParameterPemeriksaanBo parameterPemeriksaanBo = (ParameterPemeriksaanBo) ctx.getBean("parameterPemeriksaanBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            JSONObject object = new JSONObject(data);
            if(object != null){
                ParameterPemeriksaan pemeriksaan = new ParameterPemeriksaan();
                pemeriksaan.setIdKategoriLab(object.getString("id_kategori_lab"));
                pemeriksaan.setNamaPemeriksaan(object.getString("nama_pemeriksaan"));
                pemeriksaan.setKeteranganAcuanP(object.getString("keterangan_acuan_p"));
                pemeriksaan.setKeteranganAcuanL(object.getString("keterangan_acuan_l"));
                pemeriksaan.setTarif(new BigDecimal(object.getString("tarif")));
                pemeriksaan.setSatuan(object.getString("satuan"));
                pemeriksaan.setCreatedWho(userLogin);
                pemeriksaan.setLastUpdate(updateTime);
                pemeriksaan.setCreatedDate(updateTime);
                pemeriksaan.setLastUpdateWho(userLogin);
                pemeriksaan.setAction("C");
                pemeriksaan.setFlag("Y");
                response = parameterPemeriksaanBo.saveAdd(pemeriksaan);
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data tindakan tidak ada...!");
            }
        }catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        return response;
    }

    public CrudResponse saveEdit(String data){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ParameterPemeriksaanBo parameterPemeriksaanBo = (ParameterPemeriksaanBo) ctx.getBean("parameterPemeriksaanBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            JSONObject object = new JSONObject(data);
            if(object != null){
                ParameterPemeriksaan pemeriksaan = new ParameterPemeriksaan();
                pemeriksaan.setIdParameterPemeriksaan(object.getString("id_parameter_pemeriksaan"));
                pemeriksaan.setIdKategoriLab(object.getString("id_kategori_lab"));
                pemeriksaan.setNamaPemeriksaan(object.getString("nama_pemeriksaan"));
                pemeriksaan.setKeteranganAcuanP(object.getString("keterangan_acuan_p"));
                pemeriksaan.setKeteranganAcuanL(object.getString("keterangan_acuan_l"));
                pemeriksaan.setTarif(new BigDecimal(object.getString("tarif")));
                pemeriksaan.setSatuan(object.getString("satuan"));
                pemeriksaan.setLastUpdate(updateTime);
                pemeriksaan.setLastUpdateWho(userLogin);
                pemeriksaan.setAction("U");
                pemeriksaan.setFlag("Y");
                response = parameterPemeriksaanBo.saveEdit(pemeriksaan);
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data tindakan tidak ada...!");
            }
        }catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        return response;
    }

    public CrudResponse saveDelete(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ParameterPemeriksaanBo parameterPemeriksaanBo = (ParameterPemeriksaanBo) ctx.getBean("parameterPemeriksaanBoProxy");
        CrudResponse response = new CrudResponse();
        if(id != null && !"".equalsIgnoreCase(id)){
            ParameterPemeriksaan pemeriksaan = new ParameterPemeriksaan();
            pemeriksaan.setIdParameterPemeriksaan(id);
            pemeriksaan.setLastUpdate(updateTime);
            pemeriksaan.setLastUpdateWho(userLogin);
            pemeriksaan.setAction("D");
            pemeriksaan.setFlag("N");
            response = parameterPemeriksaanBo.saveEdit(pemeriksaan);
        }else{
            response.setStatus("error");
            response.setMsg("Mohon maaf data tindakan tidak ada...!");
        }
        return response;
    }

    public ParameterPemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(ParameterPemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public void setParameterPemeriksaanBoProxy(ParameterPemeriksaanBo parameterPemeriksaanBoProxy) {
        this.parameterPemeriksaanBoProxy = parameterPemeriksaanBoProxy;
    }
}