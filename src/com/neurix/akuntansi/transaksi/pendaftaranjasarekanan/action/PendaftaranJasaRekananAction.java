package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.action;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.PendaftaranJasaRekananBo;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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
import java.util.List;

public class PendaftaranJasaRekananAction extends BaseTransactionAction{
    public static transient Logger logger = Logger.getLogger(PendaftaranJasaRekananAction.class);

    private PendaftaranJasaRekananBo pendaftatanJasaRekananBoProxy;
    private PendaftaranJasa pendaftaranJasa;
    private CrudResponse crudResponse;
    private String jenisJabatan;

    public String getJenisJabatan() {
        return jenisJabatan;
    }

    public void setJenisJabatan(String jenisJabatan) {
        this.jenisJabatan = jenisJabatan;
    }

    public PendaftaranJasa getPendaftaranJasa() {
        return pendaftaranJasa;
    }

    public void setPendaftaranJasa(PendaftaranJasa pendaftaranJasa) {
        this.pendaftaranJasa = pendaftaranJasa;
    }

    public void setPendaftatanJasaRekananBoProxy(PendaftaranJasaRekananBo pendaftatanJasaRekananBoProxy) {
        this.pendaftatanJasaRekananBoProxy = pendaftatanJasaRekananBoProxy;
    }

    public void PendaftaranJasaRekananAction(){
        this.crudResponse = new CrudResponse();
    }

    @Override
    public String search() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        PendaftaranJasa dataPendaftaranJasa = getPendaftaranJasa();

        setJenisJabatan(tentukanJenisJabatanbyRole(CommonUtil.roleIdAsLogin()));

        List<PendaftaranJasa> pendaftaranJasaList = new ArrayList<>();
        try {
            pendaftaranJasaList = pendaftatanJasaRekananBoProxy.getSearchByCriteria(dataPendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.initForm] Error ", e);
            addActionError("[PendaftaranJasaRekananAction.initForm] Error "+ e.getCause());
            return "success";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", pendaftaranJasaList);
        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }

    private String tentukanJenisJabatanbyRole(String roleId){

        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN_AKS)){
            return "keu";
        } else if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_KASUB_KEU)){
            return "kasubkeu";
        } else if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_KA_KEU)){
            return "kakeu";
        } else {
            return "unit";
        }
    }

    @Override
    public String initForm() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        setPendaftaranJasa(new PendaftaranJasa());
        setJenisJabatan(tentukanJenisJabatanbyRole(CommonUtil.roleIdAsLogin()));

        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }

    public CrudResponse saveAdd(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveAdd] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON)){
            logger.error("[PendaftaranJasaRekananAction.saveAdd] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("nama") == true)
            pendaftaranJasa.setNamaJasa(obj.getString("nama"));

        if (obj.has("novendor") == true)
            pendaftaranJasa.setIdVendor(obj.getString("novendor"));

        pendaftaranJasa.setStatus("1");
        pendaftaranJasa.setFlag("Y");
        pendaftaranJasa.setAction("C");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setCreatedDate(time);
        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setCreatedWho(userLogin);
        pendaftaranJasa.setCreatedWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        try {
            pendaftaranJasaRekananBo.saveAdd(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveAdd] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveAdd] End <<<");
        return response;
    }

    public CrudResponse saveEdit(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveEdit] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON)){
            logger.error("[PendaftaranJasaRekananAction.saveEdit] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("id") == true)
            pendaftaranJasa.setId(obj.getString("id"));

        if (obj.has("nama") == true)
            pendaftaranJasa.setNamaJasa(obj.getString("nama"));

        if (obj.has("biaya") == true)
            pendaftaranJasa.setBiaya(new BigDecimal(obj.getString("biaya")));

        if (obj.has("status") == true)
            pendaftaranJasa.setStatus(obj.getString("status"));

        if (obj.has("flag") == true)
            pendaftaranJasa.setFlag(obj.getString("flag"));

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        try {
            pendaftaranJasaRekananBo.saveEdit(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveEdit] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveEdit] End <<<");
        return response;
    }

    public CrudResponse saveApprove(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveApprove] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON)){
            logger.error("[PendaftaranJasaRekananAction.saveApprove] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("id") == true)
            pendaftaranJasa.setId(obj.getString("id"));

        if (obj.has("biaya") == true)
            pendaftaranJasa.setBiaya(new BigDecimal(obj.getString("biaya")));

        if (obj.has("flagapprove") == true)
            pendaftaranJasa.setFlagApprove(obj.getString("flagapprove"));

        if (obj.has("alasanbatal") == true)
            pendaftaranJasa.setAlasanBatal(obj.getString("alasanbatal"));

        if (obj.has("jenisjabatan") == true)
            pendaftaranJasa.setJenisJabatan(obj.getString("jenisjabatan"));


        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        try {
            pendaftaranJasaRekananBo.saveApprove(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveApprove] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveApprove] End <<<");
        return response;
    }
}
