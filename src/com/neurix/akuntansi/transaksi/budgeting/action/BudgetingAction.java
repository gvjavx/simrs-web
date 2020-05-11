package com.neurix.akuntansi.transaksi.budgeting.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingDetail;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingPengadaan;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Bean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction.StatusBudgeting.*;

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
    private String trans;

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

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

    ////=== CLASS STATUS BUDGETING START ===////
    class StatusBudgeting{

        String statusDetail;
        String status;
        Integer idx;

        public String getStatusDetail() {
            return statusDetail;
        }

        public void setStatusDetail(String statusDetail) {
            this.statusDetail = statusDetail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
        }
    }

    // sorting status budgeting acending
    class SortByIdxAsc implements Comparator<StatusBudgeting>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(StatusBudgeting a, StatusBudgeting b)
        {
            return a.getIdx() - b.getIdx();
        }
    }

    // sorting status budgeting decending
    class SortByIdxDesc implements Comparator<StatusBudgeting>
    {
        // Used for sorting in decending order of
        // roll number
        public int compare(StatusBudgeting a, StatusBudgeting b)
        {
            return b.getIdx() - a.getIdx();
        }
    }
    ////=== CLASS STATUS BUDGETING END ===////

    ////=== LIST STATUS BIDGETING SORTING BY IDX START ===////
    private List<StatusBudgeting> listOfStatusBudgeting(){

        StatusBudgeting statusBudgeting;
        List<StatusBudgeting> statusBudgetingList = new ArrayList<>();

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("ADD_DRAFT");
        statusBudgeting.setIdx(0);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("EDIT_DRAFT");
        statusBudgeting.setIdx(1);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("APPROVE_DRAFT");
        statusBudgeting.setIdx(2);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("ADJUST_DRAFT");
        statusBudgeting.setIdx(3);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("EDIT_FINAL");
        statusBudgeting.setIdx(4);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("APPROVE_FINAL");
        statusBudgeting.setIdx(5);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("ADJUST_FINAL");
        statusBudgeting.setIdx(6);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("EDIT_REVISI");
        statusBudgeting.setIdx(7);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("APPROVE_REVISI");
        statusBudgeting.setIdx(8);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("ADJUST_REVISI");
        statusBudgeting.setIdx(9);
        statusBudgetingList.add(statusBudgeting);

        Collections.sort(statusBudgetingList, new SortByIdxDesc());
        return statusBudgetingList;
    }
    ////=== LIST STATUS BIDGETING SORTING BY IDX END ===////


    public String initForm(){
        logger.info("[BudgetingAction.initForm] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfCoa");
        session.removeAttribute("listOfDetail");
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
//            if (!"".equalsIgnoreCase(obj.get("status").toString())){
//                budgeting.setStatus((obj.get("status").toString()));
//            }
            if (!"".equalsIgnoreCase(obj.get("unit").toString())){
                budgeting.setBranchId(obj.getString("unit").toString());
            }
        }

        // get last Status
        Budgeting budgetingStatus = findLastStatus(budgeting.getBranchId(), budgeting.getTahun());
        if (budgetingStatus != null){
            budgeting.setStatus(budgetingStatus.getStatus());
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

    public CrudResponse setToSessionCoa(String jsonString) throws JSONException{

        logger.info("[BudgetingAction.setToSessionCoa] START >>>");

        CrudResponse response = new CrudResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

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

        if (budgetingSessionList == null){
            budgetingSessionList = new ArrayList<>();

            ImKodeRekeningEntity parenEntity = kodeRekeningBo.getKodeRekeningById(budgeting.getParentId());
            if (parenEntity != null){
                budgetingSessionList.add(rekeningToBudgeting(parenEntity));
            }

            ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningBo.getKodeRekeningById(budgeting.getRekeningId());
            if (kodeRekeningEntity != null){
                budgetingSessionList.add(rekeningToBudgeting(kodeRekeningEntity));
            }
        } else {

            // mencari parent apakah ada
            List<Budgeting> parentList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgeting.getParentId())).collect(Collectors.toList());
            // jika ada parent
            if (parentList.size() > 0){
                // mencari anak pada parent
                List<Budgeting> childList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgeting.getRekeningId())).collect(Collectors.toList());
                // jika ditemukan anak yg sama pada parent yang sama maka return ERROR;
                if (childList.size() > 0){
                    response.setStatus("error");
                    response.setMsg("COA Sudah Ada. Silahkan Cek List");
                    return response;
                } else {

                    // jika tidak ada maka tambahkan baris
                     for (Budgeting sessionBudgeting : budgetingSessionList){
                         if (sessionBudgeting.getRekeningId().equalsIgnoreCase(budgeting.getParentId())){
                             // tambahkan ke list
                             ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningBo.getKodeRekeningById(budgeting.getRekeningId());
                             budgetingSessionList.add(rekeningToBudgeting(kodeRekeningEntity));
                             break;
                         }
                     }
                }
            } else {
                // jika tidak ditemukan parent buat parent sekaligus insert 1 child
                ImKodeRekeningEntity parenEntity = kodeRekeningBo.getKodeRekeningById(budgeting.getParentId());
                if (parenEntity != null){
                    budgetingSessionList.add(rekeningToBudgeting(parenEntity));
                }
                ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningBo.getKodeRekeningById(budgeting.getRekeningId());
                if (kodeRekeningEntity != null){
                    budgetingSessionList.add(rekeningToBudgeting(kodeRekeningEntity));
                }
            }
        }

        // tambah seluruh post coa
        session.removeAttribute("listOfCoa");
        session.setAttribute("listOfCoa", budgetingSessionList);
        logger.info("[BudgetingAction.setToSessionCoa] END <<<");
        response.setStatus("success");
        return response;
    }

    private Budgeting rekeningToBudgeting(ImKodeRekeningEntity kodeRekeningEntity){
        Budgeting budgetingNew = new Budgeting();

        budgetingNew.setRekeningId(kodeRekeningEntity.getRekeningId());
        budgetingNew.setParentId(kodeRekeningEntity.getParentId());
        budgetingNew.setLevel(kodeRekeningEntity.getLevel());
        budgetingNew.setStLevel(kodeRekeningEntity.getLevel() == null ? "" : String.valueOf(kodeRekeningEntity.getLevel()));
        budgetingNew.setKodeRekening(kodeRekeningEntity.getKodeRekening());
        budgetingNew.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());

        // escape nilai null
        budgetingNew.setNilaiTotal(nullEscape(budgetingNew.getNilaiTotal()));
        budgetingNew.setSemester1(nullEscape(budgetingNew.getSemester1()));
        budgetingNew.setSemester2(nullEscape(budgetingNew.getSemester2()));
        budgetingNew.setQuartal1(nullEscape(budgetingNew.getQuartal1()));
        budgetingNew.setQuartal2(nullEscape(budgetingNew.getQuartal2()));
        budgetingNew.setQuartal3(nullEscape(budgetingNew.getQuartal3()));
        budgetingNew.setQuartal4(nullEscape(budgetingNew.getQuartal4()));
        budgetingNew.setSelisih(nullEscape(budgetingNew.getSelisih()));
        budgetingNew.setNilaiAwal(nullEscape(budgetingNew.getNilaiAwal()));

        return budgetingNew;
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

                    // escape nilai null
                    budgeting.setNilaiTotal(nullEscape(budgeting.getNilaiTotal()));
                    budgeting.setSemester1(nullEscape(budgeting.getSemester1()));
                    budgeting.setSemester2(nullEscape(budgeting.getSemester2()));
                    budgeting.setQuartal1(nullEscape(budgeting.getQuartal1()));
                    budgeting.setQuartal2(nullEscape(budgeting.getQuartal2()));
                    budgeting.setQuartal3(nullEscape(budgeting.getQuartal3()));
                    budgeting.setQuartal4(nullEscape(budgeting.getQuartal4()));
                    budgeting.setSelisih(nullEscape(budgeting.getSelisih()));
                    budgeting.setNilaiAwal(nullEscape(budgeting.getNilaiAwal()));

                    budgetingList.add(budgeting);
                }
            }
        }

//        Collections.sort(budgetingList, Budgeting.getKodeRekeningSorting());

        logger.info("[BudgetingAction.getPostCoa] END <<<");
        return budgetingList;
    }

    public String add(){
        logger.info("[BudgetingAction.add] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        Budgeting budgeting = getBudgeting();
        Budgeting budgetingNew = new Budgeting();
        if (budgeting != null){
            budgetingNew.setTahun(budgeting.getTahun());
            budgetingNew.setBranchId(budgeting.getBranchId());
            budgetingNew.setTipe(budgeting.getTipe());
        }

        String tipe = budgetingBoProxy.checkLastTipeBudgeting();
        if (!"".equalsIgnoreCase(tipe)){
            budgetingNew.setTipe(tipe);
            budgetingNew.setFlagDisable("Y");
        }


        // sset tahun, unit, tipe
        if (budgetingSessionList != null && budgeting != null){
            for (Budgeting sessionBudgeting : budgetingSessionList){
                sessionBudgeting.setTahun(budgeting.getTahun());
                sessionBudgeting.setBranchId(budgeting.getBranchId());
                sessionBudgeting.setTipe(budgeting.getTipe());

                if ("add".equalsIgnoreCase(this.status)){
                    sessionBudgeting.setStatus("ADD_DRAFT");
                }
            }
        }

        logger.info("[BudgetingAction.add] END <<<");
        if ("add".equalsIgnoreCase(this.status) && "detail".equalsIgnoreCase(this.tipe)){
            setBudgeting(budgetingNew);
            return "edit";
        } else if ("add".equalsIgnoreCase(this.status)){
            setBudgeting(budgetingNew);
            return "add";
        } else {
            setBudgeting(budgetingNew);
            session.removeAttribute("listOfCoa");
            session.setAttribute("listOfCoa", new ArrayList<>());
            return "add";
        }
    }

    public String edit(){
        logger.info("[BudgetingAction.edit] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");

        Budgeting budgeting = getBudgeting();

        Budgeting budgetingNew = new Budgeting();
        budgetingNew.setTahun(budgeting.getTahun());
        budgetingNew.setTipe(budgeting.getTipe());
        budgetingNew.setBranchId(budgeting.getBranchId());

        if (budgetingSessionList == null){
            budgetingSessionList = new ArrayList<>();
        }

        if (sessionDetail == null){
            sessionDetail = new ArrayList<>();
        }

        logger.info("[BudgetingAction.edit] END <<<");

        if (!"".equalsIgnoreCase(this.id) && this.id != null){
            // medapatakan parent
            List<Budgeting> budgetingList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(this.id)).collect(Collectors.toList());
            if (budgetingList.size() > 0){
                budgetingNew.setKodeRekening(budgetingList.get(0).getKodeRekening());
                budgetingNew.setNamaKodeRekening(budgetingList.get(0).getNamaKodeRekening());
                budgetingNew.setParentId(budgetingList.get(0).getParentId());
                if (!"-".equalsIgnoreCase(budgetingNew.getParentId()) && !"".equalsIgnoreCase(budgetingNew.getParentId())){
                    List<Budgeting> parentList = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgetingNew.getParentId())).collect(Collectors.toList());
                    if (parentList.size() > 0){
                        budgetingNew.setKodeParent(parentList.get(0).getKodeRekening());
                        budgetingNew.setNamaParent(parentList.get(0).getNamaKodeRekening());
                    }
                }
            }

            if (sessionDetail != null){

                // menncari jika ada list detail
                List<BudgetingDetail> detailList = sessionDetail.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(this.id)).collect(Collectors.toList());
                if (detailList.size() > 0 && budgetingList.get(0).getNilaiTotal().compareTo(new BigDecimal(0)) == 1) {

                    // jika ada pada listOfDetail maka pindahkan ke listOfDetailEdit
                    session.removeAttribute("listOfDetailEdit");
                    session.setAttribute("listOfDetailEdit", detailList);
                } else {
                    // jika tidak ada maka hapus;
                    session.removeAttribute("listOfDetailEdit");
                }
            } else {
                session.removeAttribute("listOfDetail");
                session.removeAttribute("listOfDetailEdit");
            }

            setBudgeting(budgetingNew);
            if ("add".equalsIgnoreCase(this.status) || "edit".equalsIgnoreCase(this.status) && this.id != null){
                return "edit_detail";
            } else {
                session.removeAttribute("listOfDetail");
                session.setAttribute("listOfDetail", new ArrayList<>());
                return "edit_detail";
            }
        } else {

            if (budgetingSessionList.size() == 0){
                if (this.trans != null){
                    for (StatusBudgeting statusBudgeting : this.listOfStatusBudgeting()){

                        Budgeting searchBudgeting = new Budgeting();
                        searchBudgeting.setTahun(budgeting.getTahun());
                        searchBudgeting.setBranchId(budgeting.getBranchId());
                        searchBudgeting.setStatus(statusBudgeting.getStatusDetail());

                        List<Budgeting> budgetingList = budgetingBoProxy.getSearchByCriteria(searchBudgeting);
                        if (budgetingList.size() > 0){

                            List<BudgetingDetail> budgetingDetailList = new ArrayList<>();
                            for (Budgeting budgetingData : budgetingList){
                                List<BudgetingDetail> budgetingDetails = budgetingBoProxy.getListBudgetingDetailByNoBudgeting(budgetingData.getIdBudgeting());
                                if (budgetingDetails.size() > 0){
                                    budgetingDetailList.addAll(budgetingDetails);
                                }
                            }

                            // set budgeting
                            searchBudgeting.setTipe(budgetingList.get(0).getTipe());
                            setBudgeting(searchBudgeting);

                            session.removeAttribute("listOfDetail");
                            session.setAttribute("listOfDetail", budgetingDetailList);

                            session.removeAttribute("listOfCoa");
                            session.setAttribute("listOfCoa", budgetingList);
                            break;
                        }
                    }
                }
            } else {
                Budgeting searchBudgeting = new Budgeting();
                searchBudgeting.setTahun(budgeting.getTahun());
                searchBudgeting.setBranchId(budgeting.getBranchId());
                searchBudgeting.setTipe(budgetingSessionList.get(0).getTipe());
                setBudgeting(searchBudgeting);
            }

            return "edit";
        }
    }


    public String setToSessionCoaDetail(String jsonString) throws JSONException{

        logger.info("[BudgetingAction.setToSessionCoa] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");
        List<BudgetingDetail> sessionDetailEdit = (List<BudgetingDetail>) session.getAttribute("listOfDetailEdit");


        if (sessionDetail == null){
            sessionDetail = new ArrayList<>();
        }

        if (sessionDetailEdit == null){
            sessionDetailEdit = new ArrayList<>();
        }

        BudgetingDetail budgetingDetail = new BudgetingDetail();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            budgetingDetail.setIdBudgetingDetail(budgetingBo.generateBudgetingDetailId());
            budgetingDetail.setRekeningId(obj.getString("rekeningid").toString());
            budgetingDetail.setDivisiId(obj.getString("divisi").toString());
            budgetingDetail.setPositionId(obj.getString("positionid").toString());
            budgetingDetail.setDivisiName(obj.get("divisiname").toString());
            budgetingDetail.setNilai(new BigDecimal(obj.getString("nilai").toString()));
            budgetingDetail.setQty(new BigInteger(obj.getString("qty").toString()));
            budgetingDetail.setTipe(obj.getString("tipe").toString());
            budgetingDetail.setSubTotal(budgetingDetail.getNilai().multiply(new BigDecimal(budgetingDetail.getQty().toString())));
        }

        // mengupdate budgetingSessionList
        for (Budgeting budgeting : budgetingSessionList){
            if (budgeting.getRekeningId().equalsIgnoreCase(budgetingDetail.getRekeningId())){

                // if null change to 0
                budgeting.setNilaiTotal(nullEscape(budgeting.getNilaiTotal()));
                budgeting.setSemester1(nullEscape(budgeting.getSemester1()));
                budgeting.setSemester2(nullEscape(budgeting.getSemester2()));
                budgeting.setQuartal1(nullEscape(budgeting.getQuartal1()));
                budgeting.setQuartal2(nullEscape(budgeting.getQuartal2()));
                budgeting.setQuartal3(nullEscape(budgeting.getQuartal3()));
                budgeting.setQuartal4(nullEscape(budgeting.getQuartal4()));

                if ("quartal1".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setQuartal1(budgeting.getQuartal1().add(budgetingDetail.getSubTotal()));
                if ("quartal2".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setQuartal2(budgeting.getQuartal2().add(budgetingDetail.getSubTotal()));
                if ("quartal3".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setQuartal3(budgeting.getQuartal3().add(budgetingDetail.getSubTotal()));
                if ("quartal4".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setQuartal4(budgeting.getQuartal4().add(budgetingDetail.getSubTotal()));
                if ("semester1".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setSemester1(budgeting.getSemester1().add(budgetingDetail.getSubTotal()));
                if ("semester2".equalsIgnoreCase(budgetingDetail.getTipe()))
                    budgeting.setSemester2(budgeting.getSemester2().add(budgetingDetail.getSubTotal()));

                budgeting.setNilaiTotal(budgeting.getNilaiTotal().add(budgetingDetail.getSubTotal()));
            }
        }

            // tambahkan ke sessionDetail dan session detailEdit
            // sessionDetailEdit / listOfDetailEdit untuk temporari session
        sessionDetail.add(budgetingDetail);
        sessionDetailEdit.add(budgetingDetail);

        // SUM KEPALA BUDGETING
        Long level = budgetingBo.getlastLevelKodeRekening();
        List<Budgeting> childBudgeting = budgetingSessionList.stream().filter(p -> p.getLevel().compareTo(level) == 0).collect(Collectors.toList());
        if (childBudgeting.size() > 0){
            sumParent(childBudgeting, level);
        }

        session.removeAttribute("listOfCoa");
        session.setAttribute("listOfCoa", budgetingSessionList);
        session.removeAttribute("listOfDetail");
        session.setAttribute("listOfDetail", sessionDetail);
        session.removeAttribute("listOfDetailEdit");
        session.setAttribute("listOfDetailEdit", sessionDetailEdit);
        logger.info("[BudgetingAction.setToSessionCoaDetail] END <<<");
        return "01";
    }

    private BigDecimal nullEscape(BigDecimal var1){
        return var1 == null ? new BigDecimal(0) : var1;
    }

    public CrudResponse saveBudgeting(String type, String tahun, String branchId, String tipe, String status){
        logger.info("[BudgetingAction.saveBudgeting] START >>>");

        CrudResponse response = new CrudResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        String statusTrans = "";
        String typeTrans = "";
        if (!"".equalsIgnoreCase(status)){
            String[] arrStatus = status.split("_", 2);
            if (arrStatus.length > 0){
                typeTrans = arrStatus[0].toString();
                statusTrans = arrStatus[1].toString();
            }
        }

        Boolean foundSameStatus = budgetingBo.foundWithSameStatus(tahun, branchId, statusTrans);

        Long level = budgetingBo.getlastLevelKodeRekening();
        List<Budgeting> childs = budgetingSessionList.stream().filter(p -> p.getLevel().compareTo(level) == 0).collect(Collectors.toList());

        if (childs.size() > 0){
            String userLogin = CommonUtil.userIdLogin();
            Timestamp time = new Timestamp(System.currentTimeMillis());

            Budgeting budgeting = new Budgeting();
            budgeting.setTahun(tahun);
            budgeting.setBranchId(branchId);
            budgeting.setTipe(tipe);
            budgeting.setStatus(status);
            budgeting.setFlag("Y");
            budgeting.setAction("C");
            budgeting.setCreatedDate(time);
            budgeting.setCreatedWho(userLogin);
            budgeting.setLastUpdate(time);
            budgeting.setLastUpdateWho(userLogin);

            response.setStatus("error");
            if ("add".equalsIgnoreCase(type)){
                try {
                    budgetingBo.saveAddBudgeting(childs, sessionDetail, new ArrayList<>(), statusTrans, budgeting);
                    response.setStatus("success");
                } catch (GeneralBOException e){
                    logger.error("[BudgetingAction.saveBudgeting] ERROR. ", e);
                    response.setMsg("[BudgetingAction.saveBudgeting] ERROR. "+ e);
                    return response;
                }
            } else {

                // edit
                // jika ditemukan masih satu status maka update
                if (foundSameStatus){
                    try {
                        budgetingBo.saveEditBudgeting(budgetingSessionList, sessionDetail, new ArrayList<>(), statusTrans, typeTrans, budgeting);
                        response.setStatus("success");
                    } catch (GeneralBOException e){
                        logger.error("[BudgetingAction.saveBudgeting] ERROR. ", e);
                        response.setMsg("[BudgetingAction.saveBudgeting] ERROR. "+ e);
                        return response;
                    }
                } else {
                    // jika tidak ditemukan satu status / status baru
                    // maka insert baru dengan status baru
                    try {
                        budgetingBo.saveAddBudgeting(childs, sessionDetail, new ArrayList<>(), statusTrans, budgeting);
                        response.setStatus("success");
                    } catch (GeneralBOException e){
                        logger.error("[BudgetingAction.saveBudgeting] ERROR. ", e);
                        response.setMsg("[BudgetingAction.saveBudgeting] ERROR. "+ e);
                        return response;
                    }
                }
            }

        }

        session.removeAttribute("listOfCoa");
        session.removeAttribute("listOfDetail");
        logger.info("[BudgetingAction.saveAdd] END >>>");
        return response;
    }

    private List<Budgeting> sumParent(List<Budgeting> childs, Long level){
        logger.info("[BudgetingAction.sumParent] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        if (childs.size() > 0){
            System.out.println("FIRST ROUND : LEVEL => "+level);
            if (level.compareTo(Long.valueOf(1)) == 1){

                List<Budgeting> listParent = new ArrayList<>();
                int n = 0;
                for (Budgeting child : childs){

                    if (listParent.size() == 0){
                        List<Budgeting> budgetings = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(child.getParentId())).collect(Collectors.toList());
                        if (budgetings.size() == 0){
                            // jika parent sudah tidak ditemukan maka return / keluar perhitunggan
                            return new ArrayList<>();
                        } else {
                            // jika masih ada parent maka tambahkan ke list parent;
                            Budgeting parent = budgetings.get(0);
                            parent.setNilaiTotal(child.getNilaiTotal());
                            parent.setSemester1(child.getSemester1());
                            parent.setSemester2(child.getSemester2());
                            parent.setQuartal1(child.getQuartal1());
                            parent.setQuartal2(child.getQuartal2());
                            parent.setQuartal3(child.getQuartal3());
                            parent.setQuartal4(child.getQuartal4());
                            parent.setSelisih(child.getNilaiTotal().subtract(child.getNilaiAwal()));
                            listParent.add(parent);
                        }
                        System.out.println("FIRST ROUND : rekening_id => "+listParent.get(n).getRekeningId()+" parent_id => "+listParent.get(n).getParentId());
                        n++;
                    } else {

                        // jika parent rekening id == child parent maka masih 1 parent
                        // sum nilai saja;
                        List<Budgeting> parents = listParent.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(child.getParentId())).collect(Collectors.toList());
                        if (parents.size() > 0){
                            Budgeting parent = parents.get(0);
                            parent.setNilaiTotal(child.getNilaiTotal().add(parent.getNilaiTotal()));
                            parent.setSemester1(child.getSemester1().add(parent.getSemester1()));
                            parent.setSemester2(child.getSemester2().add(parent.getSemester2()));
                            parent.setQuartal1(child.getQuartal1().add(parent.getQuartal1()));
                            parent.setQuartal2(child.getQuartal2().add(parent.getQuartal2()));
                            parent.setQuartal3(child.getQuartal3().add(parent.getQuartal3()));
                            parent.setQuartal4(child.getQuartal4().add(parent.getQuartal4()));

                            parent.setSelisih(parent.getNilaiTotal().subtract(parent.getNilaiAwal()));
                            System.out.println("INNER IF CHILD : rekening_id => "+parent.getRekeningId()+" parent_id => "+parent.getParentId()+" Nilai => "+parent.getNilaiTotal());

                            listParent.remove(parent);
                            listParent.add(parent);
                        } else {

                            // jika tidak ada maka jadi parent baru sekaligus menambahkan nilai dari child
                            Budgeting parentNew = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(child.getParentId())).collect(Collectors.toList()).get(0);
                            if (parentNew != null){

                                parentNew = kosongkanNilaiBudgeting(parentNew);
                                parentNew.setNilaiTotal(child.getNilaiTotal());
                                parentNew.setSemester1(child.getSemester1());
                                parentNew.setSemester2(child.getSemester2());
                                parentNew.setQuartal1(child.getQuartal1());
                                parentNew.setQuartal2(child.getQuartal2());
                                parentNew.setQuartal3(child.getQuartal3());
                                parentNew.setQuartal4(child.getQuartal4());
                                parentNew.setSelisih(parentNew.getNilaiTotal().subtract(parentNew.getNilaiAwal()));

                                System.out.println("NEW PARENT : rekening_id => "+parentNew.getRekeningId()+" parent_id => "+parentNew.getParentId()+" Nilai => "+parentNew.getNilaiTotal());
                                listParent.add(parentNew);
                                n++;
                            }
                        }
                    }
                }

                // updating session
                for (Budgeting parent : listParent){
                    budgetingSessionList.remove(parent);
                    budgetingSessionList.add(parent);
                }

                sumParent(listParent, level - Long.valueOf(1));
            }
        }
        return new ArrayList<>();
    }

    public Budgeting findLastStatus(String branchId, String tahun){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        Budgeting budgeting = new Budgeting();
        for (StatusBudgeting statusBudgeting : listOfStatusBudgeting()){
            Boolean foundBudgeting = budgetingBo.foundWithSameStatus(tahun, branchId, statusBudgeting.getStatusDetail());
            if (foundBudgeting){
                budgeting.setStatus(statusBudgeting.getStatusDetail());
                String[] arrStatus = statusBudgeting.getStatusDetail().split("_", 2);
                String typeTrans = arrStatus[0].toString();
                if ("APPROVE".equalsIgnoreCase(typeTrans) || "ADJUST".equalsIgnoreCase(typeTrans)){
                    budgeting.setApproveFlag("Y");
                }
                break;
            }
        }
        return budgeting;
    }

    public BudgetingDetail getBudgetinDetailById(String idDetail){

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");
        List<BudgetingDetail> sessionDetailEdit = (List<BudgetingDetail>) session.getAttribute("listOfDetailEdit");

        List<BudgetingDetail> budgetingDetails = sessionDetailEdit.stream().filter(p -> p.getIdBudgetingDetail().equalsIgnoreCase(idDetail)).collect(Collectors.toList());
        if (budgetingDetails.size() > 0){
            return budgetingDetails.get(0);
        }

        return new BudgetingDetail();
    }

    public CrudResponse saveEditDetail(String jsonString) throws JSONException{
        CrudResponse response = new CrudResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");
        List<BudgetingDetail> sessionDetailEdit = (List<BudgetingDetail>) session.getAttribute("listOfDetailEdit");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        String rekeningId = "";
        BudgetingDetail budgetingDetailData = new BudgetingDetail();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            budgetingDetailData.setIdBudgetingDetail(obj.getString("id").toString());
            budgetingDetailData.setQty(new BigInteger(obj.getString("qty").toString()));
            budgetingDetailData.setNilai(new BigDecimal(obj.getString("nilai").toString()));
            budgetingDetailData.setRekeningId(obj.getString("rekeningid").toString());
            rekeningId = obj.getString("rekeningid").toString();
        }

        for (BudgetingDetail budgetingDetail : sessionDetailEdit){
            if (budgetingDetail.getIdBudgetingDetail().equalsIgnoreCase(budgetingDetailData.getIdBudgetingDetail())){
                budgetingDetail.setQty(budgetingDetailData.getQty());
                budgetingDetail.setNilai(budgetingDetailData.getNilai());

                BigDecimal subTotal = budgetingDetail.getNilai().multiply(new BigDecimal(budgetingDetail.getQty()));
                budgetingDetail.setSubTotal(subTotal);

                // update detail
                sessionDetail.remove(budgetingDetail);
                sessionDetail.add(budgetingDetail);

                // update session budgeting
//                for (Budgeting budgeting : budgetingSessionList){
//                    if (budgeting.getRekeningId().equalsIgnoreCase(rekeningId)){
//
//                        budgeting.setNilaiTotal(nullEscape(budgeting.getNilaiTotal()));
//                        budgeting.setSemester1(nullEscape(budgeting.getSemester1()));
//                        budgeting.setSemester2(nullEscape(budgeting.getSemester2()));
//                        budgeting.setQuartal1(nullEscape(budgeting.getQuartal1()));
//                        budgeting.setQuartal2(nullEscape(budgeting.getQuartal2()));
//                        budgeting.setQuartal3(nullEscape(budgeting.getQuartal3()));
//                        budgeting.setQuartal4(nullEscape(budgeting.getQuartal4()));
//
//                        if ("quartal1".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setQuartal1(budgetingDetail.getSubTotal());
//                        if ("quartal2".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setQuartal2((budgetingDetail.getSubTotal()));
//                        if ("quartal3".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setQuartal3((budgetingDetail.getSubTotal()));
//                        if ("quartal4".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setQuartal4((budgetingDetail.getSubTotal()));
//                        if ("semester1".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setSemester1((budgetingDetail.getSubTotal()));
//                        if ("semester2".equalsIgnoreCase(budgetingDetail.getTipe()))
//                            budgeting.setSemester2((budgetingDetail.getSubTotal()));
//
//                        budgeting.setNilaiTotal(budgeting.getNilaiTotal().add(budgetingDetail.getSubTotal()));
//                    }
//                }
            }
        }

        List<Budgeting> budgetings = budgetingSessionList.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgetingDetailData.getRekeningId())).collect(Collectors.toList());
        if (budgetings.size() > 0){
            for (Budgeting budgeting : budgetings) {

                budgeting.setNilaiTotal(new BigDecimal(0));
                budgeting.setSemester1(new BigDecimal(0));
                budgeting.setSemester2(new BigDecimal(0));
                budgeting.setQuartal1(new BigDecimal(0));
                budgeting.setQuartal2(new BigDecimal(0));
                budgeting.setQuartal3(new BigDecimal(0));
                budgeting.setQuartal4(new BigDecimal(0));

                BigDecimal totalNilai = new BigDecimal(0);
                List<BudgetingDetail> budgetingDetailList = sessionDetailEdit.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(budgetingDetailData.getRekeningId())).collect(Collectors.toList());
                if (budgetingDetailList.size() > 0){
                    for (BudgetingDetail budgetingDetail : budgetingDetailList){
                        if ("quartal1".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setQuartal1(budgeting.getQuartal1().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                        if ("quartal2".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setQuartal2(budgeting.getQuartal2().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                        if ("quartal3".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setQuartal3(budgeting.getQuartal3().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                        if ("quartal4".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setQuartal4(budgeting.getQuartal4().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                        if ("semester1".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setSemester1(budgeting.getSemester1().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                        if ("semester2".equalsIgnoreCase(budgetingDetail.getTipe())){
                            budgeting.setSemester2(budgeting.getSemester2().add(budgetingDetail.getSubTotal()));
                            totalNilai = totalNilai.add(budgetingDetail.getSubTotal());
                        }
                    }
                }
                budgeting.setNilaiTotal(totalNilai);

                // updateBudgeting
                budgetingSessionList.remove(budgeting);
                budgetingSessionList.add(budgeting);
            }
        }

        // SUM KEPALA BUDGETING
        Long level = budgetingBo.getlastLevelKodeRekening();
        List<Budgeting> childBudgeting = budgetingSessionList.stream().filter(p -> p.getLevel().compareTo(level) == 0).collect(Collectors.toList());
        if (childBudgeting.size() > 0){
            sumParent(childBudgeting, level);

            response.setStatus("success");
            return response;
        }

        response.setStatus("error");
        response.setMsg("Gagal Save Session");
        return response;
    }

    public List<ImKodeRekeningEntity> getListKodeRekeningByLevel(String coa){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        Long level = budgetingBo.getlastLevelKodeRekening();
        return kodeRekeningBo.getListKodeRekeningByLevel(coa, level);
    }

    public CrudResponse saveAddPengadaan(String strJson, String namaPengadaan, String rekeningId, String tipe) throws JSONException{
        CrudResponse response = new CrudResponse();

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");
        List<BudgetingDetail> sessionDetail = (List<BudgetingDetail>) session.getAttribute("listOfDetail");
        List<BudgetingDetail> sessionDetailEdit = (List<BudgetingDetail>) session.getAttribute("listOfDetailEdit");
        List<BudgetingPengadaan> sessionPengadaan = (List<BudgetingPengadaan>) session.getAttribute("listOfPengadaan");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        List<BudgetingPengadaan> pengadaans = new ArrayList<>();
        JSONArray json = new JSONArray(strJson);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            BudgetingPengadaan pengadaan = new BudgetingPengadaan();
            pengadaan.setNamPengadaan(obj.getString("name").toString());
            pengadaan.setQty(new BigInteger(obj.getString("qty").toString()));
            pengadaan.setNilai(new BigDecimal(obj.getString("nilai").toString()));
            pengadaan.setSubTotal(new BigDecimal(pengadaan.getQty()).multiply(pengadaan.getNilai()));
            pengadaans.add(pengadaan);
        }

        BudgetingDetail budgetingDetail = new BudgetingDetail();
        budgetingDetail.setIdBudgetingDetail(budgetingBo.generateBudgetingDetailId());
        budgetingDetail.setRekeningId(rekeningId);
        budgetingDetail.setTipe(tipe);
        budgetingDetail.setQty(new BigInteger(String.valueOf(0)));
        budgetingDetail.setNilai(new BigDecimal(0));
        budgetingDetail.setSubTotal(new BigDecimal(0));
        for (BudgetingPengadaan pengadaan : pengadaans){
            // set to pengadaan;
            pengadaan.setIdBudgetingDetail(budgetingDetail.getIdBudgetingDetail());

            // set nilai pengadaan;
            budgetingDetail.setQty(budgetingDetail.getQty().add(pengadaan.getQty()));
            budgetingDetail.setNilai(budgetingDetail.getNilai().add(pengadaan.getNilai()));
            budgetingDetail.setSubTotal(budgetingDetail.getSubTotal().add(pengadaan.getSubTotal()));
        }

        return response;
    }

    // untuk mengosongkan nilai budgeting, digunakan untuk parent
    private Budgeting kosongkanNilaiBudgeting(Budgeting budgeting){
        BigDecimal nol = new BigDecimal(0);
        budgeting.setNilaiTotal(nol);
        budgeting.setSemester1(nol);
        budgeting.setSemester2(nol);
        budgeting.setQuartal1(nol);
        budgeting.setQuartal2(nol);
        budgeting.setQuartal3(nol);
        budgeting.setQuartal4(nol);
        return budgeting;
    }

    public Budgeting checkTransaksiBudgeting(String branch, String tahun){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        return budgetingBo.checkBudgeting(branch, tahun);
    }

    public List<Budgeting> getListOfCoaBudgetingSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        Collections.sort(budgetingSessionList, Budgeting.getKodeRekeningSorting());
        if (budgetingSessionList != null){
            return budgetingSessionList;
        }
        return new ArrayList<>();
    }

    public void setBudgetingBoProxy(BudgetingBo budgetingBoProxy) {
        this.budgetingBoProxy = budgetingBoProxy;
    }
}
