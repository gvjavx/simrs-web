package com.neurix.akuntansi.master.parameterbudgeting.action;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.parameterbudgeting.bo.ParameterBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunJenisBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunKategoriParameterBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunParameterBudgetingRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgeting;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

public class ParameterBudgetingAction {
    private static transient Logger logger = Logger.getLogger(ParameterBudgetingAction.class);
    private ParameterBudgetingBo parameterBudgetingBoProxy;

    public void setParameterBudgetingBoProxy(ParameterBudgetingBo parameterBudgetingBoProxy) {
        this.parameterBudgetingBoProxy = parameterBudgetingBoProxy;
    }

    public String initForm(){
        logger.info("[ParameterBudgetingAction.initForm] START >>>");
        resetAllSession();
        logger.info("[ParameterBudgetingAction.initForm] END <<<");
        return "success";
    }

    private void resetAllSession(){
        logger.info("[ParameterBudgetingAction.resetAllSession] START >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfParameterBudgeting");
        session.removeAttribute("listOfCoa");
        logger.info("[ParameterBudgetingAction.resetAllSession] END <<<");
    }

    public List<ParameterBudgeting> search(String jsonString) throws JSONException{
        logger.info("[ParameterBudgetingAction.initForm] START >>>");

        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++){
            JSONObject jsonObject = json.getJSONObject(i);
            parameterBudgeting.setIdKategoriBudgeting(jsonObject.get("id_kategori_budgeting").toString());
            parameterBudgeting.setMasterId(jsonObject.get("master_id").toString());
            parameterBudgeting.setDivisiId(jsonObject.get("divisi_id").toString());
            parameterBudgeting.setIdJenisBudgeting(jsonObject.get("id_jenis_budgeting").toString());
            parameterBudgeting.setIdParamRekening(jsonObject.get("id_item").toString());
        }
        //active data only
        parameterBudgeting.setFlag("Y");

        List<ParameterBudgeting> parameterBudgetings = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        try {
            parameterBudgetings = parameterBudgetingBo.getSearchByCriteria(parameterBudgeting);
        } catch (GeneralBOException e){
            logger.error("[ParameterBudgetingBoImpl.getSearchByCriteria] ERROR. ", e);
            throw new GeneralBOException("[ParameterBudgetingBoImpl.getSearchByCriteria] ERROR. ]", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfParameterBudgeting");
        session.setAttribute("listOfParameterBudgeting", parameterBudgetings);

        logger.info("[ParameterBudgetingAction.initForm] END <<<");
        return parameterBudgetings;
    }

    public ParameterBudgeting getFromSession(String id){
        logger.info("[ParameterBudgetingAction.getFromSession] START >>>");
        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> listParameter = (List<ParameterBudgeting>) session.getAttribute("listOfParameterBudgeting");

        if (listParameter != null && listParameter.size() > 0 && id != null && !"".equalsIgnoreCase(id)){
            List<ParameterBudgeting> filteredList = listParameter.stream().filter(p->p.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
            if (filteredList != null && filteredList.size() > 0){
                parameterBudgeting = filteredList.get(0);
            }
        }

        logger.info("[ParameterBudgetingAction.getFromSession] END <<<");
        return parameterBudgeting;
    }

    public CrudResponse saveAdd(String jsonString){
        logger.info("[ParameterBudgetingAction.saveAdd] START >>>");
        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            if (jsonArray.length() > 0){
                for (int i = 0 ; i<jsonArray.length() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    parameterBudgeting.setIdKategoriBudgeting(jsonObject.get("id_kategori_budgeting").toString());
                    parameterBudgeting.setIdJenisBudgeting(jsonObject.get("id_jenis_budgeting").toString());
                    parameterBudgeting.setMasterId(stringNullAscape(jsonObject.get("master_id").toString()));
                    parameterBudgeting.setDivisiId(stringNullAscape(jsonObject.get("divisi_id").toString()));
                    parameterBudgeting.setRekeningId(jsonObject.getString("rekening_id"));
                    parameterBudgeting.setPositionId(stringNullAscape(jsonObject.getString("position_id")));
                }
            }
        } catch (JSONException e){
            logger.info("[ParameterBudgetingAction.saveAdd] ERROR. ", e);
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }

        // selain pendapatan tidak ada master;
        if (!"PDT".equalsIgnoreCase(parameterBudgeting.getIdJenisBudgeting())){
            parameterBudgeting.setMasterId(parameterBudgeting.getIdJenisBudgeting());
            if ("INV".equalsIgnoreCase(parameterBudgeting.getIdJenisBudgeting())){
                parameterBudgeting.setDivisiId(null);
                parameterBudgeting.setPositionId(null);
            }
        }

        parameterBudgeting.setCreatedDate(times);
        parameterBudgeting.setCreatedWho(userLogin);
        parameterBudgeting.setLastUpdate(times);
        parameterBudgeting.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        try {
             parameterBudgetingBo.saveAdd(parameterBudgeting);
             response.setStatus("success");
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.saveAdd] ERROR. ", e);
            response.setStatus("error");
            response.setMsg(e.getCause().toString());
        }

        logger.info("[ParameterBudgetingAction.saveAdd] END <<<");
        return response;
    }

    public String stringNullAscape(String id){
        if (id == null || "".equalsIgnoreCase(id)){
            return null;
        } else {
            return id;
        }
    }

    public CrudResponse saveEdit(String jsonString){
        logger.info("[ParameterBudgetingAction.saveEdit] START >>>");
        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            if (jsonArray.length() > 0){
                for (int i = 0 ; i<jsonArray.length() ; i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    parameterBudgeting.setId(jsonObject.getString("id"));
                    parameterBudgeting.setIdJenisBudgeting(jsonObject.getString("id_jenis_budgeting").toString());
                    parameterBudgeting.setIdKategoriBudgeting(jsonObject.get("id_kategori_budgeting").toString());
                    parameterBudgeting.setMasterId(stringNullAscape(jsonObject.get("master_id").toString()));
                    parameterBudgeting.setDivisiId(stringNullAscape(jsonObject.get("divisi_id").toString()));
                    parameterBudgeting.setRekeningId(jsonObject.get("rekening_id").toString());
                    parameterBudgeting.setFlag(jsonObject.get("flag").toString());
                    parameterBudgeting.setPositionId(stringNullAscape(jsonObject.getString("position_id")));
                }
            }
        } catch (JSONException e){
            logger.info("[ParameterBudgetingAction.saveEdit] ERROR. ", e);
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }

        // selain pendapatan tidak ada master;
        if (!"PDT".equalsIgnoreCase(parameterBudgeting.getIdJenisBudgeting())){
            parameterBudgeting.setMasterId(parameterBudgeting.getIdJenisBudgeting());
            if ("INV".equalsIgnoreCase(parameterBudgeting.getIdJenisBudgeting())){
                parameterBudgeting.setDivisiId(null);
                parameterBudgeting.setPositionId(null);
            }
        }


        parameterBudgeting.setLastUpdate(times);
        parameterBudgeting.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        try {
            parameterBudgetingBo.saveEdit(parameterBudgeting);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.saveEdit] ERROR. ", e);
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }

        logger.info("[ParameterBudgetingAction.saveEdit] END <<<");
        return response;
    }

    public List<ImAkunJenisBudgetingEntity> getAllJenisBudgeting(){
        logger.info("[ParameterBudgetingAction.getAllJenisBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        logger.info("[ParameterBudgetingAction.getAllJenisBudgeting] END <<<");
        return parameterBudgetingBo.getAllJenisBudgeting();
    }

    public List<ImAkunKategoriParameterBudgetingEntity> getAllKategoriBudgeting(){
        logger.info("[ParameterBudgetingAction.getAllKategoriBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        logger.info("[ParameterBudgetingAction.getAllKategoriBudgeting] END <<<");
        return parameterBudgetingBo.getAllKategoriParameterBudgeting();
    }

    public List<Position> getAllPosition(){
        logger.info("[ParameterBudgetingAction.getAllPosition] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        Position position = new Position();
        position.setFlag("Y");
        position.setFlagCostUnit("Y");

        List<Position> positions = new ArrayList<>();
        try {
            positions = positionBo.getByCriteria(position);
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.getAllPosition] ERROR. ", e);
        }

        logger.info("[ParameterBudgetingAction.getAllPosition] END <<<");
        return positions;
    }

    public List<ImMasterEntity> getAllMaster(){
        logger.info("[ParameterBudgetingAction.getAllMaster] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        logger.info("[ParameterBudgetingAction.getAllMaster] END <<<");
        return parameterBudgetingBo.getAllMaster();
    }

    public List<ImAkunParameterBudgetingRekeningEntity> getAllParamRekening(){
        logger.info("[ParameterBudgetingAction.getAllParamRekening] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        logger.info("[ParameterBudgetingAction.getAllParamRekening] END <<<");
        return parameterBudgetingBo.getAllParameterRekening();
    }

    public List<ImAkunKategoriParameterBudgetingEntity> getAllKatagoriByIdJenis(String id){
        logger.info("[ParameterBudgetingAction.getAllKatagoriByIdJenis] START >>>");

        List<ImAkunKategoriParameterBudgetingEntity> listParameter = getAllKategoriBudgeting();

        if (listParameter != null && listParameter.size() > 0){
            List<ImAkunKategoriParameterBudgetingEntity> filteredList = listParameter.stream().filter(p->p.getIdJenisBudgeting().equalsIgnoreCase(id)).collect(Collectors.toList());
            if (filteredList.size() > 0){
                logger.info("[ParameterBudgetingAction.getAllKatagoriByIdJenis] END <<<");
                return filteredList;
            }
        }

        logger.info("[ParameterBudgetingAction.getAllKatagoriByIdJenis] END <<<");
        return null;
    }

    public CrudResponse getListKodeRekeningByTipeCoa(String tipeCoa){
        logger.info("[ParameterBudgetingAction.getListKodeRekeningByTipeCoa] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        List<KodeRekening> kodeRekeningList = new ArrayList<>();

        CrudResponse response = new CrudResponse();

        try {
            kodeRekeningList = parameterBudgetingBo.getListKodeRekeningByTipeCoa(tipeCoa);
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.getListKodeRekeningByTipeCoa] ERROR .",e);
            response.hasError("[ParameterBudgetingAction.getListKodeRekeningByTipeCoa] ERROR ."+e.getMessage());
        }

        logger.info("[ParameterBudgetingAction.getListKodeRekeningByTipeCoa] END <<<");
        response.hasSuccess("berhasil");
        response.setList(kodeRekeningList);
        return response;
    }

    public String getTipeKodeRekening(String idJenisBudgeting){
        logger.info("[ParameterBudgetingAction.getTipeKodeRekening] START >>>");

        String tipeCoa = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        try {
            tipeCoa = parameterBudgetingBo.getTipeKodeRekeningFromJenisBudgetingById(idJenisBudgeting);
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.getTipeKodeRekening] ERROR .",e);
        }

        logger.info("[ParameterBudgetingAction.getTipeKodeRekening] END <<<");
        return tipeCoa;
    }

    public String getKoderingByPositionId(String positionId){
        logger.info("[ParameterBudgetingAction.getTipeKodeRekening] START >>>");

        String kodering = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterBudgetingBo parameterBudgetingBo = (ParameterBudgetingBo) ctx.getBean("parameterBudgetingBoProxy");

        try {
            kodering = parameterBudgetingBo.getKoderingFromPositionByPositionId(positionId);
        } catch (GeneralBOException e){
            logger.info("[ParameterBudgetingAction.getTipeKodeRekening] ERROR .",e);
        }

        logger.info("[ParameterBudgetingAction.getTipeKodeRekening] END <<<");
        return kodering;
    }
}
