package com.neurix.akuntansi.transaksi.budgetingpendapatan.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunParameterBudgetingEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunParameterBudgetingRekeningEntity;
import com.neurix.akuntansi.master.parameterbudgeting.model.ParameterBudgeting;
import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.bo.BudgetingPerhitunganBo;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.constant.CommonConstant;
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
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by reza on 11/08/20.
 */
public class BgPendapatanAction {
    private static transient Logger logger = Logger.getLogger(BgPendapatanAction.class);
    private Budgeting budgeting;
    private String status;
    private String tipe;
    private String id;
    private String trans;
    private String tipeBudgeting = "pendapatan";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public Budgeting getBudgeting() {
        return budgeting;
    }

    public void setBudgeting(Budgeting budgeting) {
        this.budgeting = budgeting;
    }

    public String initForm(){
        String userBranchId = CommonUtil.userBranchLogin();
        Budgeting budgeting = new Budgeting();
        budgeting.setFlagKp(userBranchId.equalsIgnoreCase(CommonConstant.ID_KANPUS) ? "Y" : "N");
        budgeting.setBranchId(userBranchId);
        setBudgeting(budgeting);
        eraseAllSession();
        return "add_kp";
    }

    private void eraseAllSession(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfCoa");
        session.removeAttribute("listOfDetail");
        session.removeAttribute("listOfPengadaan");
        session.removeAttribute("listOfParam");
        session.removeAttribute("listOfPerhitungan");
        session.removeAttribute("listOfNilaiParam");
    }

    public CrudResponse getSearchListBudgeting(String tahun, String unit){
        logger.info("[BgPendapatanAction.getSearchListBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        Budgeting budgeting = new Budgeting();
        budgeting.setTahun(tahun);
        budgeting.setBranchId(unit);
        budgeting.setRekeningIdList(kodeRekeningBo.getListRekeningIdsByTipeBudgeting(this.tipeBudgeting));

        BudgetingAction budgetingAction = new BudgetingAction();

        // get last Status
        Budgeting budgetingStatus = budgetingAction.findLastStatus(budgeting.getBranchId(), budgeting.getTahun(), "pendapatan");
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
            logger.error("[BgPendapatanAction.getSearchListBudgeting] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.getSearchListBudgeting] ERROR. "+e.getMessage());
            return response;
        }


        logger.info("[BgPendapatanAction.getSearchListBudgeting] END <<<");
        return response;
    }

    public String add(){
        logger.info("[BgPendapatanAction.add] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Budgeting> budgetingSessionList = (List<Budgeting>) session.getAttribute("listOfCoa");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        Budgeting budgeting = getBudgeting();
        Budgeting budgetingNew = new Budgeting();
        budgetingNew.setTipeBudgeting(this.tipeBudgeting);
        if (budgeting != null){
            budgetingNew.setTahun(budgeting.getTahun());
            budgetingNew.setBranchId(budgeting.getBranchId());
            budgetingNew.setTipe(budgeting.getTipe());
            budgetingNew.setIdKategoriBudgeting(budgeting.getIdKategoriBudgeting());
            budgetingNew.setNamaKategori(budgeting.getNamaKategori());
            budgetingNew.setJenis(budgeting.getJenis());
        }
        if (budgetingNew.getBranchId() == null || "".equalsIgnoreCase(budgetingNew.getBranchId())){
            budgetingNew.setFlagKp(branchId.equalsIgnoreCase(CommonConstant.ID_KANPUS) ? "Y" : "N");
            budgetingNew.setBranchId(branchId);
        }

        Branch branch = branchBo.getBranchById(budgetingNew.getBranchId(), "Y");
        if (branch != null){
            budgetingNew.setBranchName(branch.getBranchName());
        }
        String tipe = budgetingBo.checkLastTipeBudgeting();
        if (!"".equalsIgnoreCase(tipe)){
            budgetingNew.setTipe(tipe);
            budgetingNew.setFlagDisable("Y");
        }


        // sset tahun, unit, tipe
//        if (budgetingSessionList != null && budgeting != null){
//            for (Budgeting sessionBudgeting : budgetingSessionList){
//                sessionBudgeting.setTahun(budgeting.getTahun());
//                sessionBudgeting.setBranchId(budgeting.getBranchId());
//                sessionBudgeting.setTipe(budgeting.getTipe());
//
//                if ("add".equalsIgnoreCase(this.status)){
//                    sessionBudgeting.setStatus("ADD_DRAFT");
//                }
//            }
//        }

        logger.info("[BgPendapatanAction.add] END <<<");
        if ("add".equalsIgnoreCase(this.status) && "detail".equalsIgnoreCase(this.tipe)){
            setBudgeting(budgetingNew);
            return "edit";
        } else if ("add".equalsIgnoreCase(this.status)){
            setBudgeting(budgetingNew);
            return "add";
        } else {
            setBudgeting(budgetingNew);
            if (budgetingNew.getJenis() != null && "add".equalsIgnoreCase(budgetingNew.getJenis())){
                return "add_detail";
            }
            else if ("Y".equalsIgnoreCase(budgetingNew.getFlagKp())){
                eraseAllSession();
                return "add_kp";
            } else {
                return "add";
            }
        }
    }


    public CrudResponse getListKategoriParameter(String idJenis, String tahun, String branchId){
        logger.info("[BgPendapatanAction.getListKategoriParameter] START >>>");

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionNilaParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        KategoriParameter kategoriParameter = new KategoriParameter();
        kategoriParameter.setIdJenisBudgeting(idJenis);
        kategoriParameter.setTahun(tahun);
        kategoriParameter.setBranchId(branchId);

        List<KategoriParameter> kategoriParameters = new ArrayList<>();

        try {
            kategoriParameters = budgetingPerhitunganBo.getListKategoriParameter(kategoriParameter);

            // set nilai kategori parameter jika ada data pada session nilai parameter
            if (sessionNilaParam != null && kategoriParameters.size() > 0){
                for (KategoriParameter kategori : kategoriParameters){
                    List<ParameterBudgeting> parameterBudgetings = sessionNilaParam.stream().filter(p->p.getIdKategoriBudgeting().equalsIgnoreCase(kategori.getId())).collect(Collectors.toList());

                    BigDecimal nilaiTotal = new BigDecimal(0);
                    if (parameterBudgetings != null && parameterBudgetings.size() > 0){
                        for (ParameterBudgeting parameterBudgeting : parameterBudgetings){
                            nilaiTotal = nilaiTotal.add(parameterBudgeting.getNilaiTotal());
                        }
                        kategori.setNilaiTotal(nilaiTotal);
                    }
                }
            }

            response.setStatus("success");
            response.setList(kategoriParameters);
        } catch (GeneralBOException e){
            logger.error("[BgPendapatanAction.getListParametersBudgeting] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.getListParametersBudgeting] ERROR " + e);
            return response;
        }

        logger.info("[BgPendapatanAction.getListParametersBudgeting] END <<<");
        return response;
    }

    public List<ParameterBudgeting> getListParameterBudgeting(String idJenis, String tahun, String branchId, String tipe){
        logger.info("[BgPendapatanAction.getListParametersBudgeting] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ParameterBudgeting> listParamBudgeting = new ArrayList<>();

        if (sessionParam != null && !"new".equalsIgnoreCase(tipe)){
            // jika session ada
            listParamBudgeting = sessionParam;
        } else {

            // jika session kosong;
            // cari data dri table;
            ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
            parameterBudgeting.setIdJenisBudgeting(idJenis);
            parameterBudgeting.setBranchId(branchId);
            parameterBudgeting.setTahun(tahun);
            try {
                listParamBudgeting = budgetingPerhitunganBo.getSearchListParameterBudgeting(parameterBudgeting);
            } catch (GeneralBOException e){
                logger.error("[BgPendapatanAction.getListParametersBudgeting] ERROR", e);
            }

            // set Session
            sessionParam = new ArrayList<>();
            sessionParam.addAll(listParamBudgeting);
            session.setAttribute("listOfParam", sessionParam);
        }

        logger.info("[BgPendapatanAction.getListParametersBudgeting] END <<<N");
        return listParamBudgeting;
    }

    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String tahun, String tipe){
        logger.info("[BgPendapatanAction.getListParameterBudgeting] START >>>");
        String bulan = CommonUtil.getDateParted(new Date(System.currentTimeMillis()), "MONTH");
        Integer intBulanLalu = Integer.valueOf(bulan) - 1;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        return budgetingPerhitunganBo.getListPendapatanTindakan(branchId, intBulanLalu.toString(), tahun, tipe);
    }

    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String tahun, String tipe){
        logger.info("[BgPendapatanAction.getListPendapatanObat] START >>>");
        String bulan = CommonUtil.getDateParted(new Date(System.currentTimeMillis()), "MONTH");
        Integer intBulanLalu = Integer.valueOf(bulan) - 1;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        return budgetingPerhitunganBo.getListPendapatanObat(branchId, intBulanLalu.toString(), tahun, tipe);
    }

    public CrudResponse setPerhitunganToSession(String idParam, String stPerhitungan, String masterId, String divisiId, String tahun, String unit, String idKategori, String periode) throws JSONException{
        logger.info("[BgPendapatanAction.setPerhitunganToSession] START >>>");

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ItAkunPerhitunganBudgetingEntity> sessionPerhitungan = (List<ItAkunPerhitunganBudgetingEntity>) session.getAttribute("listOfPerhitungan");
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        String idNilaiParam = "";
        boolean isNew = false;
        // hitung perhitungan budgeting
        if (sessionNilaiParam != null){
            List<ParameterBudgeting> nilaiParamsFilter = sessionNilaiParam.stream().filter(
                    p->
                            p.getIdParameter().equalsIgnoreCase(idParam) &&
                            p.getMasterId().equalsIgnoreCase(masterId) &&
                            p.getDivisiId().equalsIgnoreCase(divisiId) &&
                            p.getPeriode().equalsIgnoreCase(periode)
                    ).collect(Collectors.toList());

            isNew = nilaiParamsFilter.size() == 0;
            if (!isNew){

                if (sessionPerhitungan != null){
                    // dari lambda, jika ada hapus dahulu inputkan nilai baru;
                    List<ItAkunPerhitunganBudgetingEntity> listPerhitungan = sessionPerhitungan.stream().filter(p->p.getIdNilaiParameter().equalsIgnoreCase(nilaiParamsFilter.get(0).getIdNilaiParameter())).collect(Collectors.toList());
                    if (listPerhitungan.size() > 0 ){
                        for (ItAkunPerhitunganBudgetingEntity perhitungan : listPerhitungan){
                            sessionPerhitungan.remove(perhitungan);
                        }
                    }
                }

                if (nilaiParamsFilter.get(0).getIdNilaiParameter() != null && !"".equalsIgnoreCase(nilaiParamsFilter.get(0).getIdNilaiParameter())){
                    idNilaiParam = nilaiParamsFilter.get(0).getIdNilaiParameter();
                } else {
                    idNilaiParam = budgetingPerhitunganBo.getNextIdNilaiParameter(unit, tahun, idParam);
                }

            } else {
                idNilaiParam = budgetingPerhitunganBo.getNextIdNilaiParameter(unit, tahun, idParam);
            }
        } else {
            isNew = true;
            sessionNilaiParam = new ArrayList<>();
            idNilaiParam = budgetingPerhitunganBo.getNextIdNilaiParameter(unit, tahun, idParam);
        }

        if (sessionPerhitungan != null){
            // dari lambda, jika ada hapus dahulu inputkan nilai baru;

            List<ItAkunPerhitunganBudgetingEntity> listPerhitungan = sessionPerhitungan.stream().filter(p->p.getIdParameterBudgeting().equalsIgnoreCase(idParam)).collect(Collectors.toList());
            if (listPerhitungan.size() > 0 ){
                for (ItAkunPerhitunganBudgetingEntity perhitungan : listPerhitungan){
                    sessionPerhitungan.remove(perhitungan);
                }
            }
        } else {
            // jika tidak ada set list baru;
            sessionPerhitungan = new ArrayList<>();
        }

        // inputkan nilai baru dari st JSON;
        JSONArray json = new JSONArray(stPerhitungan);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            ItAkunPerhitunganBudgetingEntity perhitungan = new ItAkunPerhitunganBudgetingEntity();
            if (!"".equalsIgnoreCase(obj.get("nilai").toString())){
                perhitungan.setNilai(obj.get("nilai") == null ? new BigDecimal(0) : new BigDecimal(obj.get("nilai").toString()) );
            }
            if (!"".equalsIgnoreCase(obj.get("opr").toString())){
                perhitungan.setOperator(obj.get("opr").toString());
            }
            perhitungan.setUrutan(i);
            perhitungan.setIdParameterBudgeting(idParam);
            perhitungan.setIdNilaiParameter(idNilaiParam);

            // tambahkan ke session perhitungan
            sessionPerhitungan.add(perhitungan);
        }

        // hitung dari list filter berdasarkan id Params;
        // hasil perhitungan untuk nilai pada parameter budgeting;
        String finalIdNilaiParam = idNilaiParam;
        List<ItAkunPerhitunganBudgetingEntity> listPerhitungan = sessionPerhitungan.stream().filter(p->p.getIdNilaiParameter().equalsIgnoreCase(finalIdNilaiParam)).collect(Collectors.toList());
        BigDecimal nilaiBudgeting = budgetingPerhitunganBo.hitungNilaiBudgeting(listPerhitungan);
        if (listPerhitungan.size() > 0 && !isNew) {

            // set nilai ke session Params
            for (ParameterBudgeting parameterNilaiBudgeting : sessionNilaiParam){
                if (idNilaiParam.equalsIgnoreCase(parameterNilaiBudgeting.getIdNilaiParameter())){
                    parameterNilaiBudgeting.setNilaiTotal(nilaiBudgeting);
                    parameterNilaiBudgeting.setDivisiId(divisiId);
                    parameterNilaiBudgeting.setNamaDivisi(budgetingPerhitunganBo.getPositionByKodering(divisiId).getPositionName());
                    parameterNilaiBudgeting.setMasterId(masterId);
                    parameterNilaiBudgeting.setNamaMaster(budgetingPerhitunganBo.getMasterByKodering(masterId).getNama());
                    parameterNilaiBudgeting.setIdKategoriBudgeting(idKategori);
                    parameterNilaiBudgeting.setPeriode(periode);
                    break;
                }
            }
        } else {
            ParameterBudgeting parameterNilaiBudgeting = new ParameterBudgeting();
            parameterNilaiBudgeting.setIdParameter(idParam);
            parameterNilaiBudgeting.setIdNilaiParameter(idNilaiParam);
            parameterNilaiBudgeting.setNilaiTotal(nilaiBudgeting);
            parameterNilaiBudgeting.setDivisiId(divisiId);
            parameterNilaiBudgeting.setNamaDivisi(budgetingPerhitunganBo.getPositionByKodering(divisiId).getPositionName());
            parameterNilaiBudgeting.setMasterId(masterId);
            parameterNilaiBudgeting.setNamaMaster(budgetingPerhitunganBo.getMasterByKodering(masterId).getNama());
            parameterNilaiBudgeting.setIdKategoriBudgeting(idKategori);
            parameterNilaiBudgeting.setPeriode(periode);
            sessionNilaiParam.add(parameterNilaiBudgeting);
        }

        session.removeAttribute("listOfNilaiParam");
        session.setAttribute("listOfNilaiParam", sessionNilaiParam);
        session.removeAttribute("listOfPerhitungan");
        session.setAttribute("listOfPerhitungan", sessionPerhitungan);

        logger.info("[BgPendapatanAction.setPerhitunganToSession] END <<<");
        response.setStatus("success");
        return response;
    }

    public CrudResponse saveAdd(String unit, String tahun, String tipe){
        logger.info("[BgPendapatanAction.saveAdd] START >>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();

        CrudResponse response = new CrudResponse();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionParam = (List<ParameterBudgeting>) session.getAttribute("listOfParam");
        List<ItAkunPerhitunganBudgetingEntity> sessionPerhitungan = (List<ItAkunPerhitunganBudgetingEntity>) session.getAttribute("listOfPerhitungan");
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        BudgetingBo budgetingBo = (BudgetingBo) ctx.getBean("budgetingBoProxy");

        // insert to perhitungan budgeting;
        PerhitunganBudgeting perhitunganBudgeting = new PerhitunganBudgeting();
        perhitunganBudgeting.setCreatedDate(time);
        perhitunganBudgeting.setCreatedWho(userLogin);
        perhitunganBudgeting.setLastUpdate(time);
        perhitunganBudgeting.setLastUpdateWho(userLogin);
        perhitunganBudgeting.setFlag("Y");
        perhitunganBudgeting.setAction("C");
        perhitunganBudgeting.setTahun(tahun);
        perhitunganBudgeting.setBranchId(unit);
        perhitunganBudgeting.setTipe(tipe);

        List<ParameterBudgeting> filterListNilaiParam = sessionNilaiParam.stream().filter(p -> !"BYA".equalsIgnoreCase(p.getMasterId()) && !"INV".equalsIgnoreCase(p.getMasterId())).collect(Collectors.toList());

        try {
            budgetingPerhitunganBo.saveAddPerhitunganBudgeting( convertNilaiParameterToEntity(filterListNilaiParam) ,sessionPerhitungan, new ArrayList<>(), perhitunganBudgeting);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.info("[BgPendapatanAction.saveAdd] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[BgPendapatanAction.saveAdd] ERROR " + e);
        }

//        // convert ke list budgeting dan insert ke table budgeting
//        // set status draft dan nilainya
//        List<Budgeting> budgetingList = convertParamToListBudgeting(sessionParam, tahun, unit);
//        Budgeting budgeting = new Budgeting();
//        budgeting.setBranchId(unit);
//        budgeting.setTahun(tahun);
//        budgeting.setStatus("ADD_DRAFT");
//        budgeting.setTipe(tipe);
//        budgeting.setFlag("Y");
//        budgeting.setAction("C");
//        budgeting.setCreatedDate(time);
//        budgeting.setCreatedWho(userLogin);
//        budgeting.setLastUpdate(time);
//        budgeting.setLastUpdateWho(userLogin);
//
//        try {
//            budgetingBo.saveAddBudgeting(budgetingList, new ArrayList<>(), new ArrayList<>(), "DRAFT", budgeting);
//            response.setStatus("success");
//        } catch (GeneralBOException e){
//            logger.info("[BgPendapatanAction.saveAdd] ERROR ", e);
//            response.setStatus("error");
//            response.setMsg("[BgPendapatanAction.saveAdd] ERROR " + e);
//        }
        logger.info("[BgPendapatanAction.saveAdd] END <<<");
        return response;
    }

    private List<ItAkunNilaiParameterBudgetingEntity> convertNilaiParameterToEntity(List<ParameterBudgeting> parameterBudgetings){

        List<ItAkunNilaiParameterBudgetingEntity> entities = new ArrayList<>();
        for (ParameterBudgeting parameterBudgeting : parameterBudgetings){
            ItAkunNilaiParameterBudgetingEntity budgetingEntity = new ItAkunNilaiParameterBudgetingEntity();
            budgetingEntity.setId(parameterBudgeting.getIdNilaiParameter());
            budgetingEntity.setIdParameter(parameterBudgeting.getIdParameter());
            budgetingEntity.setDivisiId(parameterBudgeting.getDivisiId());
            budgetingEntity.setMasterId(parameterBudgeting.getMasterId());
            budgetingEntity.setNilaiTotal(parameterBudgeting.getNilaiTotal());
            budgetingEntity.setPeriode(parameterBudgeting.getPeriode());
            entities.add(budgetingEntity);
        }

        return entities;
    }

    private List<Budgeting> convertParamToListBudgeting(List<ParameterBudgeting> listParam, String tahun, String unit){
        BudgetingAction budgetingAction = new BudgetingAction();
        List<Budgeting> budgetingList = new ArrayList<>();
        if (listParam != null && listParam.size() > 0){
            for (ParameterBudgeting param : listParam){
                // membuat object budgeting baru;
                // convert null to 0 masing - masing nilai
                Budgeting budgeting = new Budgeting();
                budgeting = budgetingAction.kosongkanNilaiBudgeting(budgeting);
                if (budgetingList.size() == 0){
                    budgeting.setKodeRekening(getKodeRekeningById(param.getRekeningId()).getKodeRekening());
                    budgeting.setTahun(tahun);
                    budgeting.setBranchId(unit);
                    budgeting.setRekeningId(param.getRekeningId());
                    budgeting.setTipe("tahunan");
                    budgeting.setNilaiTotal(param.getNilaiTotal());
                    budgetingList.add(budgeting);
                } else {
                    boolean notFound = true;
                    for (Budgeting bg : budgetingList){
                        if (param.getRekeningId().equalsIgnoreCase(bg.getRekeningId())){
                            bg.setNilaiTotal(bg.getNilaiTotal().add(param.getNilaiTotal()));
                            notFound = false;
                            break;
                        }
                    }
                    if (notFound){
                        budgeting.setKodeRekening(getKodeRekeningById(param.getRekeningId()).getKodeRekening());
                        budgeting.setTahun(tahun);
                        budgeting.setBranchId(unit);
                        budgeting.setRekeningId(param.getRekeningId());
                        budgeting.setTipe("tahunan");
                        budgeting.setNilaiTotal(param.getNilaiTotal());
                        budgetingList.add(budgeting);
                    }
                }
            }
        }
        return budgetingList;
    }

    private ImKodeRekeningEntity getKodeRekeningById(String id){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        return kodeRekeningBo.getKodeRekeningById(id);
    }

    public List<ParameterBudgeting> getListParameterByIdKategori(String idKategori){
        logger.info("[BgPendapatanAction.saveAdd] getListParameterByIdKategori >>>");

        List<ParameterBudgeting> results = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
        parameterBudgeting.setIdKategoriBudgeting(idKategori);
        List<ParameterBudgeting> parameterBudgetings = budgetingPerhitunganBo.getSearchListParameterBudgeting(parameterBudgeting);
        if (parameterBudgetings.size() > 0){
            for (ParameterBudgeting dataParam : parameterBudgetings){
                results.add(dataParam);
            }
        }

        session.removeAttribute("listOfParam");
        session.setAttribute("listOfParam", parameterBudgetings);
        logger.info("[BgPendapatanAction.getListParameterByIdKategori] END <<<");
        return results;
    }

    public List<ParameterBudgeting> getListDataParam(String idParam) {
        List<ParameterBudgeting> results = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");
        if (sessionNilaiParam != null){
            for (ParameterBudgeting parameterBudgeting : sessionNilaiParam){
                if (idParam.equalsIgnoreCase(parameterBudgeting.getIdParameter())){
                    results = sessionNilaiParam.stream().filter(p->p.getIdParameter().equalsIgnoreCase(idParam)).collect(Collectors.toList());
                    break;
                }
            }
        }
        return results;
    }

    public List<ParameterBudgeting> getListMasterBudgeting(String idKategori, String branch, String tahun, String tipe){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        List<ParameterBudgeting> listMaster = new ArrayList<>();

        try {
            listMaster = budgetingPerhitunganBo.getListMasterParameterBudgetingByIdKategori(idKategori);
        } catch (GeneralBOException e){
            logger.info("[BgPendapatanAction.getListMasterBudgeting] ERROR ", e);
        }

        // cari ada di data base jika ada
        if (sessionNilaiParam == null || "DRAFT".equalsIgnoreCase(tipe)){
            sessionNilaiParam = new ArrayList<>();
            ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
            parameterBudgeting.setBranchId(branch);
            parameterBudgeting.setTahun(tahun);
//            parameterBudgeting.setMasterId(masterId);
            parameterBudgeting.setFlag("Y");

            try {
                sessionNilaiParam = budgetingPerhitunganBo.getNilaiParameterByNilaiParam(parameterBudgeting);
            } catch (GeneralBOException e){
                logger.info("[BgPendapatanAction.getListMasterBudgeting] ERROR ", e);
            }

            session.removeAttribute("listOfNilaiParam");
            session.setAttribute("listOfNilaiParam", sessionNilaiParam);
        }

        // cari session dengan parameter master;
        // hitung nilai total sesuai master;
        if (listMaster.size() > 0){
            for (ParameterBudgeting paramMaster : listMaster){
                if (sessionNilaiParam != null){
                    List<ParameterBudgeting> nilaiParamsFilter = sessionNilaiParam.stream().filter(
                            p->
                                    p.getMasterId().equalsIgnoreCase(paramMaster.getMasterId()) &&
                                    p.getIdKategoriBudgeting().equalsIgnoreCase(idKategori)
                    ).collect(Collectors.toList());
                    if (nilaiParamsFilter.size() > 0){
                        BigDecimal nilaiTotal = hitungNilaiTotalFromListParam(nilaiParamsFilter);
                        paramMaster.setNilaiTotal(nilaiTotal);
                    }
                }
            }
        }

        return listMaster;
    }

    private BigDecimal hitungNilaiTotalFromListParam(List<ParameterBudgeting> list){

        BigDecimal nilaiTotal = new BigDecimal(0);
        for (ParameterBudgeting param : list){
            nilaiTotal = nilaiTotal.add(param.getNilaiTotal() == null ? new BigDecimal(0) : param.getNilaiTotal());
        }
        return nilaiTotal;
    }

    public List<ParameterBudgeting> getListDivisiBudgeting( String idKategori, String masterId, String branch, String tahun){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ParameterBudgeting> sessionNilaiParam = (List<ParameterBudgeting>) session.getAttribute("listOfNilaiParam");

        List<ParameterBudgeting> listDivisi = new ArrayList<>();

        try {
            listDivisi = budgetingPerhitunganBo.getListDivisiParameterBudgetingByKategororiAndMaster(idKategori, masterId);
        } catch (GeneralBOException e){
            logger.info("[BgPendapatanAction.getListDivisiBudgeting] ERROR ", e);
        }

        // cari ada di data base jika ada
        if (sessionNilaiParam == null){
            sessionNilaiParam = new ArrayList<>();
            ParameterBudgeting parameterBudgeting = new ParameterBudgeting();
            parameterBudgeting.setBranchId(branch);
            parameterBudgeting.setTahun(tahun);
            parameterBudgeting.setMasterId(masterId);
            parameterBudgeting.setFlag("Y");

            try {
                sessionNilaiParam = budgetingPerhitunganBo.getNilaiParameterByNilaiParam(parameterBudgeting);
            } catch (GeneralBOException e){
                logger.info("[BgPendapatanAction.getListDivisiBudgeting] ERROR ", e);
            }

            session.removeAttribute("listOfNilaiParam");
            session.setAttribute("listOfNilaiParam", sessionNilaiParam);
        }

        // cari session dengan parameter master dan divisi;
        // hitung nilai total sesuai master dan divisi;
        if (listDivisi.size() > 0){
            for (ParameterBudgeting paramDivisi : listDivisi){
                if (sessionNilaiParam != null){
                    List<ParameterBudgeting> nilaiParamsFilter = sessionNilaiParam.stream().filter(
                            p->
                                    p.getMasterId().equalsIgnoreCase(masterId) &&
                                    p.getDivisiId().equalsIgnoreCase(paramDivisi.getDivisiId()) &&
                                    p.getIdKategoriBudgeting().equalsIgnoreCase(idKategori)

                    ).collect(Collectors.toList());
                    if (nilaiParamsFilter.size() > 0){
                        BigDecimal nilaiTotal = hitungNilaiTotalFromListParam(nilaiParamsFilter);
                        paramDivisi.setNilaiTotal(nilaiTotal);
                    }
                }
            }
        }

        return listDivisi;
    }

    public List<ParameterBudgeting> getListPendapatanBudgeting(String tahun, String branchId, String idParam, String divisiId, String master){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");

        String rekeningId = "";
        ImAkunParameterBudgetingEntity parameterBudgetingEntity = budgetingPerhitunganBo.getParameterBudgetingEntityById(idParam);
        if (parameterBudgetingEntity != null){
            ImAkunParameterBudgetingRekeningEntity rekeningEntity = budgetingPerhitunganBo.getParameterBudgetingRekeningEntityById(parameterBudgetingEntity.getIdParamRekening());
            if (rekeningEntity != null){
                rekeningId = rekeningEntity.getRekeningId();
            }
        }

        return budgetingPerhitunganBo.getListRefrensiBiaya(tahunLalu(tahun), branchId, rekeningId, divisiId, master);
    }

    private String tahunLalu(String tahun){
        Integer intTahun = Integer.valueOf(tahun);
        return String.valueOf(intTahun-1);
    }

    public List<ParameterBudgeting> getListPendapatan(String brancid, String tahun, String master, String divisi){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingPerhitunganBo budgetingPerhitunganBo = (BudgetingPerhitunganBo) ctx.getBean("budgetingPerhitunganBoProxy");
        return budgetingPerhitunganBo.getListPendapatan(brancid, tahun, master, divisi);
    }
}
