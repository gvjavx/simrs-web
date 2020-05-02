package com.neurix.akuntansi.transaksi.budgeting.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingDetail;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingAction {
    private static transient Logger logger = Logger.getLogger(BudgetingAction.class);
    private BudgetingBo budgetingBoProxy;
    private Budgeting budgeting;
    private String id;
    private String tipe;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Budgeting getBudgeting() {
        return budgeting;
    }

    public void setBudgeting(Budgeting budgeting) {
        this.budgeting = budgeting;
    }

    public String initForm(){
        logger.info("[BudgetingAction.initForm] START >>>");

        setBudgeting(new Budgeting());

        logger.info("[BudgetingAction.initForm] END <<<");
        return "search";
    }

    public CrudResponse getSearchListBudgeting(String jsonString) throws JSONException {
        logger.info("[BudgetingAction.getSearchListBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        Budgeting budgeting = new Budgeting();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            if (!"".equalsIgnoreCase(obj.get("tahun").toString())){
                budgeting.setTahun(obj.getString("tahun").toString());
            }
            if (!"".equalsIgnoreCase(obj.get("coa").toString())){
                budgeting.setRekeningId(obj.getString("coa").toString());
            }
            if (!"".equalsIgnoreCase(obj.get("status").toString())){
                budgeting.setStatus((obj.get("status").toString()));
            }
            if (!"".equalsIgnoreCase(obj.get("unit").toString())){
                budgeting.setBranchId(obj.getString("unit").toString());
            }
        }

        List<Budgeting> budgetings = new ArrayList<>();

        CrudResponse response = new CrudResponse();
        try {
            budgetings = budgetingBo.getSearchByCriteria(budgeting);
            response.setStatus("success");
            response.setList(budgetings);
        } catch (GeneralBOException e){
            logger.error("[BudgetingAction.getSearchListBudgeting] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[BudgetingAction.getSearchListBudgeting] ERROR. "+e.getMessage());
            return response;
        }
        logger.info("[BudgetingAction.getSearchListBudgeting] END <<<");
        return response;
    }

    public String setToSessionCoa(String jsonString) throws JSONException{

        logger.info("[BudgetingAction.setToSessionCoa] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        Budgeting budgeting = new Budgeting();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            budgeting.setRekeningId(obj.getString("id").toString());
            budgeting.setKodeRekening(obj.getString("kode").toString());
            budgeting.setNamaKodeRekening(obj.getString("nama").toString());
            budgeting.setParentId(obj.getString("parent").toString());
        }

        // jika ditemukan nomor id sama tidak menambahkan seluruh post
        if (budgetingSessionList != null){
            List<Budgeting> budgetingList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgeting.getRekeningId())).collect(Collectors.toList());
            if (budgetingList.size() > 0){
                return "00";
            }
        } else {
            budgetingSessionList = new ArrayList<>();
        }


        // tambah seluruh post coa
        List<Budgeting> postCoa = getPostCoa(budgeting.getKodeRekening());
        if (postCoa.size() > 0){
            budgetingSessionList.addAll(getPostCoa(budgeting.getKodeRekening()));
        }

        session.removeAttribute("listOfCoa");
        session.setAttribute("listOfCoa", budgetingSessionList);
        logger.info("[BudgetingAction.setToSessionCoa] END <<<");
        return "01";
    }

    public List<Budgeting> getPostCoa(String kodeRekening){
        logger.info("[BudgetingAction.getPostCoa] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        List<Budgeting> budgetingList = new ArrayList<>();
        if (!"".equalsIgnoreCase(kodeRekening)){
            if (kodeRekening.contains(".")){
                String kode = kodeRekening;
                String[] arrKode = kode.split("\\.", 5);

                List<KodeRekening> postCoas = kodeRekeningBo.getPostByKodeRekening(arrKode[0].toString());

                Budgeting budgeting;
                for (KodeRekening rekening : postCoas){
                    budgeting = new Budgeting();
                    budgeting.setRekeningId(rekening.getRekeningId());
                    budgeting.setParentId(rekening.getParentId());
                    budgeting.setLevel(rekening.getLevel());
                    budgeting.setStLevel(rekening.getLevel() == null ? "" : String.valueOf(rekening.getLevel()));
                    budgeting.setKodeRekening(rekening.getKodeRekening());
                    budgeting.setNamaKodeRekening(rekening.getNamaKodeRekening());
                    budgetingList.add(budgeting);
                }
            }
        }

        Collections.sort(budgetingList, Budgeting.getKodeRekeningSorting());

        logger.info("[BudgetingAction.getPostCoa] END <<<");
        return budgetingList;
    }

    public String add(){
        logger.info("[BudgetingAction.add] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();

        Budgeting budgeting = getBudgeting();

        logger.info("[BudgetingAction.add] END <<<");
        if ("add".equalsIgnoreCase(this.status) && "detail".equalsIgnoreCase(this.tipe)){
            setBudgeting(budgeting);
            return "edit";
        } else if ("add".equalsIgnoreCase(this.status)){
            return "add";
        } else {
            setBudgeting(new Budgeting());
            session.removeAttribute("listOfCoa");
            session.setAttribute("listOfCoa", new ArrayList<>());
            return "add";
        }
    }

    public String edit(){
        logger.info("[BudgetingAction.edit] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        Budgeting budgeting = getBudgeting();

        logger.info("[BudgetingAction.edit] END <<<");
        if ("add".equalsIgnoreCase(this.status) || "edit".equalsIgnoreCase(this.status) && !"".equalsIgnoreCase(this.id) && this.id != null){

            // medapatakan parent
            List<Budgeting> budgetingList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(this.id)).collect(Collectors.toList());
            if (budgetingList.size() > 0){
                budgeting.setKodeRekening(budgetingList.get(0).getKodeRekening());
                budgeting.setNamaKodeRekening(budgetingList.get(0).getNamaKodeRekening());
                budgeting.setParentId(budgetingList.get(0).getParentId());
                if (!"-".equalsIgnoreCase(budgeting.getParentId()) && !"".equalsIgnoreCase(budgeting.getParentId())){
                    List<Budgeting> parentList =budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgeting.getParentId())).collect(Collectors.toList());
                    if (parentList.size() > 0){
                        budgeting.setKodeParent(parentList.get(0).getKodeRekening());
                        budgeting.setNamaParent(parentList.get(0).getNamaKodeRekening());
                    }
                }
            }

            setBudgeting(budgeting);
            return "edit_detail";
        } else if ("add".equalsIgnoreCase(this.status) || "edit".equalsIgnoreCase(this.status)){
            return "edit";
        } else {
            return "edit";
        }
    }

    public String setToSessionCoaDetail(String jsonString) throws JSONException{

        logger.info("[BudgetingAction.setToSessionCoa] START >>>");

//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
//        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");

        if (sessionDetail == null){
            sessionDetail = new ArrayList<>();
        }

        BudgetingDetail budgetingDetail;
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            budgetingDetail = new BudgetingDetail();
            JSONObject obj = json.getJSONObject(i);
            budgetingDetail.setDivisiId(obj.getString("divisi").toString());
            budgetingDetail.setNilai(new BigDecimal(obj.getString("nilai").toString()));
            budgetingDetail.setQty(new BigInteger(obj.getString("qty").toString()));
            budgetingDetail.setTipe(obj.getString("tipe").toString());
            sessionDetail.add(budgetingDetail);
        }

        session.removeAttribute("listOfDetail");
        session.setAttribute("listOfDetail", sessionDetail);
        logger.info("[BudgetingAction.setToSessionCoaDetail] END <<<");
        return "01";
    }

    public void setBudgetingBoProxy(BudgetingBo budgetingBoProxy) {
        this.budgetingBoProxy = budgetingBoProxy;
    }
}
