package com.neurix.simrs.master.tindakan.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakanina.bo.KategoriTindakanInaBo;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;
import com.neurix.simrs.master.tindakan.bo.HeaderTindakanBo;
import com.neurix.simrs.master.tindakan.model.HeaderTindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeaderTindakanAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(HeaderTindakanAction.class);
    private HeaderTindakan headerTindakan;
    private HeaderTindakanBo headerTindakanBoProxy;

    public HeaderTindakan initHeaderTindakan(String id){
        List<HeaderTindakan> tindakanList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderTindakanBo headerTindakanBo = (HeaderTindakanBo) ctx.getBean("headerTindakanBoProxy");
        HeaderTindakan tindakan = new HeaderTindakan();

        if(id != null && !"".equalsIgnoreCase(id)){
            HeaderTindakan headerTindakan = new HeaderTindakan();
            headerTindakan.setIdHeaderTindakan(id);
            headerTindakan.setFlag("Y");

            try {
                tindakanList = headerTindakanBo.getByCriteria(headerTindakan);
            }catch (GeneralBOException e){
                logger.error(e.getMessage());
            }
            if(tindakanList.size() > 0){
                tindakan = tindakanList.get(0);
            }
        }
        return tindakan;
    }

    @Override
    public String search() {
        HeaderTindakan tindakan = getHeaderTindakan();
        List<HeaderTindakan> headerTindakanList = new ArrayList();
        try {
            headerTindakanList = headerTindakanBoProxy.getByCriteria(tindakan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.save] Error when searching alat by criteria, Found problem when searching data by criteria, please inform to your admin."+ e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", headerTindakanList);
        setHeaderTindakan(tindakan);

        return "search";
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        HeaderTindakan headerTindakan = new HeaderTindakan();
        setHeaderTindakan(headerTindakan);
        return "search";
    }

    public CrudResponse saveAdd(String data){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        HeaderTindakanBo headerTindakanBo = (HeaderTindakanBo) ctx.getBean("headerTindakanBoProxy");
        CrudResponse response = new CrudResponse();

        try {
            JSONObject object = new JSONObject(data);
            if(object != null){
                HeaderTindakan headerTindakan = new HeaderTindakan();
                headerTindakan.setNamaTindakan(object.getString("nama_tindakan"));
                headerTindakan.setKategoriInaBpjs(object.getString("kategori_ina_bpjs"));
                headerTindakan.setStandardCost(new BigInteger(object.getString("tarif")));
                headerTindakan.setFlagKonsulTele(object.getString("flag_tele"));
                headerTindakan.setCreatedWho(userLogin);
                headerTindakan.setLastUpdate(updateTime);
                headerTindakan.setCreatedDate(updateTime);
                headerTindakan.setLastUpdateWho(userLogin);
                headerTindakan.setAction("C");
                headerTindakan.setFlag("Y");
                response = headerTindakanBo.saveAdd(headerTindakan);
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data headerTindakan tidak ada...!");
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
        HeaderTindakanBo headerTindakanBo = (HeaderTindakanBo) ctx.getBean("headerTindakanBoProxy");
        CrudResponse response = new CrudResponse();

        try {
            JSONObject object = new JSONObject(data);
            if(object != null){
                HeaderTindakan headerTindakan = new HeaderTindakan();
                headerTindakan.setIdHeaderTindakan(object.getString("id_tindakan"));
                headerTindakan.setNamaTindakan(object.getString("nama_tindakan"));
                headerTindakan.setKategoriInaBpjs(object.getString("kategori_ina_bpjs"));
                headerTindakan.setStandardCost(new BigInteger(object.getString("tarif")));
                headerTindakan.setLastUpdate(updateTime);
                headerTindakan.setLastUpdateWho(userLogin);
                headerTindakan.setAction("U");
                headerTindakan.setFlag("Y");
                response = headerTindakanBo.saveEdit(headerTindakan);
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data tindakan tidak ada...!");
            }
        }catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        logger.info("[tindakanAction.saveAdd] end process >>>");
        return response;
    }

    public CrudResponse saveDelete(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        HeaderTindakanBo headerTindakanBo = (HeaderTindakanBo) ctx.getBean("headerTindakanBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            HeaderTindakan headerTindakan = new HeaderTindakan();
            headerTindakan.setIdHeaderTindakan(id);
            headerTindakan.setLastUpdate(updateTime);
            headerTindakan.setLastUpdateWho(userLogin);
            headerTindakan.setAction("D");
            headerTindakan.setFlag("N");
            response = headerTindakanBo.saveEdit(headerTindakan);
        }catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        return response;
    }

    public List<KategoriTindakanIna> getComboInaBpjs() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KategoriTindakanInaBo kategoriTindakanInaBo = (KategoriTindakanInaBo) ctx.getBean("kategoriTindakanInaBoProxy");
        List<KategoriTindakanIna> kategoriTindakanInas = new ArrayList<>();
        KategoriTindakanIna kategoriTindakanIna = new KategoriTindakanIna();
        kategoriTindakanIna.setFlag("Y");
        try {
            kategoriTindakanInas = kategoriTindakanInaBo.getByCriteria(kategoriTindakanIna);
        } catch (GeneralBOException e) {
            logger.error("[KategoriTindakanInaAction.initComboKategoriTindakanIna] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return kategoriTindakanInas;
    }


    public void setHeaderTindakanBoProxy(HeaderTindakanBo headerTindakanBoProxy) {
        this.headerTindakanBoProxy = headerTindakanBoProxy;
    }

    public HeaderTindakan getHeaderTindakan() {
        return headerTindakan;
    }

    public void setHeaderTindakan(HeaderTindakan headerTindakan) {
        this.headerTindakan = headerTindakan;
    }
}