package com.neurix.simrs.master.pelayanan.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.bo.HeaderPelayananBo;
import com.neurix.simrs.master.pelayanan.model.HeaderPelayanan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeaderPelayananAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(HeaderPelayananAction.class);
    private HeaderPelayananBo headerPelayananBoProxy;
    private HeaderPelayanan headerPelayanan;

    public HeaderPelayanan init(String id) {
        logger.info("[HeaderPelayanan.init] start process >>>");
        HeaderPelayanan result = new HeaderPelayanan();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderPelayananBo headerPelayananBo = (HeaderPelayananBo) ctx.getBean("headerPelayananBoProxy");
        if(id != null && !"".equalsIgnoreCase(id)){
            List<HeaderPelayanan> list = new ArrayList<>();
            try {
                HeaderPelayanan pelayanan = new HeaderPelayanan();
                pelayanan.setIdHeaderPelayanan(id);
                list = headerPelayananBo.getByCriteria(pelayanan);
            }catch (Exception e){
                logger.error("[HeaderPelayanan.init] Error when saving error,", e);
            }
            if(list.size() > 0){
                result = list.get(0);
            }
        }
        logger.info("[HeaderPelayanan.init] end process >>>");
        return result;
    }

    @Override
    public String search() {
        logger.info("[PelayananAction.search] start process >>>");
        HeaderPelayanan searchPelayanan = getHeaderPelayanan();
        List<HeaderPelayanan> list = new ArrayList();
        try {
            list = headerPelayananBoProxy.getByCriteria(searchPelayanan);
        } catch (GeneralBOException e) {
            logger.error("[PelayananAction.save] Error when searching alat by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException("Error search pelayanan, " + e.getMessage());
        }
        setHeaderPelayanan(searchPelayanan);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", list);
        logger.info("[PelayananAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        setHeaderPelayanan(new HeaderPelayanan());
        return "search";
    }

    public CrudResponse save(String data){
        CrudResponse response = new CrudResponse();
        logger.info("[PelayananAction.saveAdd] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderPelayananBo headerPelayananBo = (HeaderPelayananBo) ctx.getBean("headerPelayananBoProxy");
        try {
            if(data != null && !"".equalsIgnoreCase(data)){
                JSONObject obj = new JSONObject(data);
                if(obj != null){
                    HeaderPelayanan pelayanan = new HeaderPelayanan();
                    String type = obj.getString("tipe");
                    pelayanan.setNamaPelayanan(obj.getString("nama_pelayanan"));
                    pelayanan.setTipePelayanan(obj.getString("tipe_pelayanan"));
                    pelayanan.setKategoriPelayanan(obj.getString("kategori_pelayanan"));
                    pelayanan.setKodeVclaim(obj.getString("kode_vclaim"));
                    pelayanan.setDivisiId(obj.getString("divisi_id"));
                    pelayanan.setIsVaksin(obj.getString("is_vaksin"));
                    pelayanan.setCreatedDate(updateTime);
                    pelayanan.setCreatedWho(userLogin);
                    pelayanan.setLastUpdate(updateTime);
                    pelayanan.setLastUpdateWho(userLogin);
                    if("add".equalsIgnoreCase(type)){
                        pelayanan.setFlag("Y");
                        pelayanan.setAction("C");
                        headerPelayananBo.saveAdd(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else if("edit".equalsIgnoreCase(type)){
                        pelayanan.setIdHeaderPelayanan(obj.getString("id_header_pelayanan"));
                        pelayanan.setFlag("Y");
                        pelayanan.setAction("U");
                        headerPelayananBo.saveEdit(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else if("delete".equalsIgnoreCase(type)){
                        pelayanan.setIdHeaderPelayanan(obj.getString("id_header_pelayanan"));
                        pelayanan.setFlag("N");
                        pelayanan.setAction("D");
                        headerPelayananBo.saveDelete(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else{
                        response.setStatus("error");
                        response.setStatus("Type Method is not found @_@");
                    }
                }else{
                    response.setStatus("error");
                    response.setStatus("Data not found @_@");
                }
            }else{
                response.setStatus("error");
                response.setStatus("Data not found @_@");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error when parse JSON object @_@, "+e.getMessage());
            logger.error("[pelayananAction.saveAdd] Error when saving error,", e);
        }
        logger.info("[pelayananAction.saveAdd] end process >>>");
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPelayananBoProxy(HeaderPelayananBo headerPelayananBoProxy) {
        this.headerPelayananBoProxy = headerPelayananBoProxy;
    }

    public HeaderPelayanan getHeaderPelayanan() {
        return headerPelayanan;
    }

    public void setHeaderPelayanan(HeaderPelayanan headerPelayanan) {
        this.headerPelayanan = headerPelayanan;
    }
}