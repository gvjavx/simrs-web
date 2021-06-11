package com.neurix.simrs.master.rekananops.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
//import com.neurix.simrs.master.rekananOps.bo.RekananOpsBo;
//import com.neurix.simrs.master.rekananOps.model.RekananOps;
import com.neurix.hris.master.payrollPtkp.model.PayrollPtkp;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekananops.bo.DetailRekananOpsBo;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.xml.soap.Detail;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class DetailRekananOpsAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DetailRekananOpsAction.class);
    private RekananOps rekananOps;
    private RekananOpsBo rekananOpsBoProxy;
    private PositionBo positionBoProxy;

    private DetailRekananOpsBo detailRekananOpsBoProxy;
    private DetailRekananOps detailRekananOps;
    private Branch branch;
    private BranchBo branchBoProxy;
    private CrudResponse response;
    private String transTipe;

    public CrudResponse getResponse() {
        return response;
    }

    public void setResponse(CrudResponse response) {
        this.response = response;
    }

    public String getTransTipe() {
        return transTipe;
    }

    public void setTransTipe(String transTipe) {
        this.transTipe = transTipe;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public DetailRekananOps getDetailRekananOps() {
        return detailRekananOps;
    }

    public void setDetailRekananOps(DetailRekananOps detailRekananOps) {
        this.detailRekananOps = detailRekananOps;
    }
    private List<DetailRekananOps> listOfComboDetailRekananOps = new ArrayList<DetailRekananOps>();
    private List<RekananOps> listOfComboRekananOps = new ArrayList<RekananOps>();
    private List<Branch>listOfComboBranch = new ArrayList<Branch>();

    public List<RekananOps> getListOfComboRekananOps() {
        return listOfComboRekananOps;
    }

    public void setListOfComboRekananOps(List<RekananOps> listOfComboRekananOps) {
        this.listOfComboRekananOps = listOfComboRekananOps;
    }

    private List<Position> listOfComboPositions = new ArrayList<Position>();


    private List<RekananOps> listOfCombo = new ArrayList<RekananOps>();

    public List<RekananOps> getListOfCombo() {
        return listOfCombo;
    }

    public void setListOfCombo(List<RekananOps> listOfCombo) {
        this.listOfCombo = listOfCombo;
    }

    public List<Position> getListOfComboPositions() {
        return listOfComboPositions;
    }

    public void setListOfComboPositions(List<Position> listOfComboPositions) {
        this.listOfComboPositions = listOfComboPositions;
    }

    public PositionBo getPositionBoProxy() {
        return positionBoProxy;
    }

    public void setPositionBoProxy(PositionBo positionBoProxy) {
        this.positionBoProxy = positionBoProxy;
    }

    public RekananOps getRekananOps() {
        return rekananOps;
    }

    public void setRekananOps(RekananOps rekananOps) {
        this.rekananOps = rekananOps;
    }

    public RekananOpsBo getRekananOpsBoProxy() {
        return rekananOpsBoProxy;
    }

    public void setRekananOpsBoProxy(RekananOpsBo rekananOpsBoProxy) {
        this.rekananOpsBoProxy = rekananOpsBoProxy;
    }

    public void setDetailRekananOpsBoProxy(DetailRekananOpsBo detailRekananOpsBoProxy) {
        this.detailRekananOpsBoProxy = detailRekananOpsBoProxy;
    }

    public DetailRekananOps init(String kode, String flag){
        logger.info("[DetailRekananOpsAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DetailRekananOps> listOfResult = (List<DetailRekananOps>) session.getAttribute("listOfResultRekananOps");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (DetailRekananOps detailRekananOps: listOfResult) {
                    if(kode.equalsIgnoreCase(detailRekananOps.getIdDetailRekananOps()) && flag.equalsIgnoreCase(detailRekananOps.getFlag())){
                        setDetailRekananOps(detailRekananOps);
                        break;
                    }
                }
            } else {
                setDetailRekananOps(new DetailRekananOps());
            }
            logger.info("[DetailRekananOpsAction.init] end process >>>");
        }
        return getDetailRekananOps();
    }

    @Override
    public String add() {
        logger.info("[DetailRekananOpsAction.add] start process >>>");
        DetailRekananOps addDetailRekananOps = new DetailRekananOps();
        if(!"ADMIN KP".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            addDetailRekananOps.setBranchId(CommonUtil.userBranchLogin());
        }
        setDetailRekananOps(addDetailRekananOps);
        setAddOrEdit(true);
        setAdd(true);
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[DetailRekananOpsAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[DetailRekananOpsAction.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        DetailRekananOps editDetailRekananOps = new DetailRekananOps();
        if(itemFlag != null){
            try {
                editDetailRekananOps = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("dfsdfdsf"+e.getMessage());
                throw new GeneralBOException("sdfdsfdf, "+e.getMessage());
            }

            if(editDetailRekananOps != null) {
                setDetailRekananOps(editDetailRekananOps);
            } else {
                editDetailRekananOps.setFlag(itemFlag);
                setDetailRekananOps(editDetailRekananOps);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editDetailRekananOps.setFlag(getFlag());
            setDetailRekananOps(editDetailRekananOps);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[DetailRekananOpsAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DetailRekananOpsAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        DetailRekananOps deleteDetailRekananOps = new DetailRekananOps();
        if (itemFlag != null ) {
            try {
                deleteDetailRekananOps = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteDetailRekananOps != null) {
                setDetailRekananOps(deleteDetailRekananOps);

            } else {
                //deleteDetailRekananOps.getSkalaGajiId(itemId);
                deleteDetailRekananOps.setFlag(itemFlag);
                setDetailRekananOps(deleteDetailRekananOps);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deleteDetailRekananOps.getSkalaGajiId(itemId);
            deleteDetailRekananOps.setFlag(itemFlag);
            setDetailRekananOps(deleteDetailRekananOps);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[DetailRekananOpsAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {

        return null;
    }

    @Override
    public String save() {

        return null;
    }

    public String saveDelete(){
        logger.info("[DetailRekananOpsAction.saveDelete] start process >>>");
        try {

            DetailRekananOps deleteDetailRekananOps = getDetailRekananOps();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteDetailRekananOps.setLastUpdate(updateTime);
            deleteDetailRekananOps.setLastUpdateWho(userLogin);
            deleteDetailRekananOps.setAction("U");
            deleteDetailRekananOps.setFlag("N");

            detailRekananOpsBoProxy.saveDelete(deleteDetailRekananOps);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[DetailRekananOpsiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[DetailRekananOpsAction.saveEdit] start process >>>");
        try {

            DetailRekananOps editDetailRekananOps = getDetailRekananOps();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editDetailRekananOps.setLastUpdateWho(userLogin);
            editDetailRekananOps.setLastUpdate(updateTime);
            editDetailRekananOps.setAction("U");
            editDetailRekananOps.setFlag("Y");

            detailRekananOpsBoProxy.saveEdit(editDetailRekananOps);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[DetailRekananOpsAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveAdd(){
        logger.info("[DetailRekananOpsAction.saveAdd] start process >>>");

        try {
            DetailRekananOps detailRekananOps = getDetailRekananOps();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            detailRekananOps.setCreatedWho(userLogin);
            detailRekananOps.setLastUpdate(updateTime);
            detailRekananOps.setCreatedDate(updateTime);
            detailRekananOps.setLastUpdateWho(userLogin);
            detailRekananOps.setAction("C");
            detailRekananOps.setFlag("Y");

            detailRekananOpsBoProxy.saveAdd(detailRekananOps);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRekananOps");

        logger.info("[rekananOpsAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[DetailRekananOpsAction.search] start process >>>");

        DetailRekananOps searchRekananOps = getDetailRekananOps();
        List<DetailRekananOps> listOfsearchRekananOps = new ArrayList();
        try {
            listOfsearchRekananOps = detailRekananOpsBoProxy.getSearchByCriteria(searchRekananOps);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultRekananOps");
        session.setAttribute("listOfResultRekananOps", listOfsearchRekananOps);

        logger.info("[DetailRekananOpsAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[DetailRekananOpsAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        RekananOps data = new RekananOps();
//        if (branchId != null){
//            data.setBranchUser(branchId);
//            data.setBranchId(branchId);
//        }else {
//            data.setBranchUser("");
//            data.setBranchId("");
//        }
//        rekananOps = data;

        session.removeAttribute("listOfResultRekananOps");
        session.removeAttribute("listOfTindakan");
        logger.info("[DetailRekananOpsAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {

        return null;
    }

    @Override
    public String downloadXls() {

        return null;
    }

//    public String initComboPosition() {
//
//        Position position = new Position();
//        position.setFlag("Y");
////        position.setKategori("rekananOps");
//        List<Position> listOfPosition = new ArrayList<Position>();
//        try {
//            listOfPosition = positionBoProxy.getByCriteria(position);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
//            } catch (GeneralBOException e1) {
//                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
//            }
//            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
//            return "failure";
//        }
//
//        listOfComboPositions.addAll(listOfPosition);
//
//        return "init_combo_position";
//    }
//
    public String initComboRekananOps(){

        RekananOps rekananOps = new RekananOps();

        List<RekananOps> rekananOpss = new ArrayList<>();
        try {
            rekananOpss = rekananOpsBoProxy.getByCriteria(rekananOps);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.initComboRekananOps] ERROR. ", e);
            throw new GeneralBOException("[DetailRekananOpsAction.initComboRekananOps] ERROR. "+e.getMessage());
        }

        listOfCombo.addAll(rekananOpss);
        return "init_combo_farmasi";
    }

    public RekananOps getDataRekananOps(String idRekananOps) {

        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");
        RekananOps response = new RekananOps();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");

        if(idRekananOps != null && !"".equalsIgnoreCase(idRekananOps)){
            List<RekananOps> rekananOpsList = new ArrayList<>();
            RekananOps rekananOps = new RekananOps();
            rekananOps.setIdRekananOps(idRekananOps);
            rekananOps.setBranchId(CommonUtil.userBranchLogin());

            try {
                rekananOpsList = rekananOpsBo.getByCriteria(rekananOps);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.saveAdd] Error when get data for rekananOps", e);
                throw new GeneralBOException("Error when pasien id rekananOps", e);
            }

            RekananOps poli = new RekananOps();
            if(rekananOpsList.size() > 0) {

                poli = rekananOpsList.get(0);

                if (poli.getIdRekananOps() != null) {
                    response = poli;
                }
            }
        }

        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return response;
    }

    public String initComboRekanan() {

        RekananOps rekananOps = new RekananOps();
//        position.setFlag("Y");
        List<RekananOps> listOfRekananOps = new ArrayList<RekananOps>();
        try {
            listOfRekananOps = rekananOpsBoProxy.getByCriteria(rekananOps);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "detailRekananOpsBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboRekananOps.addAll(listOfRekananOps);
        return SUCCESS;
    }

    public String initComboBranch() {

        Branch branch = new Branch();
//        position.setFlag("Y");
        List<Branch> listOfBranch = new ArrayList<Branch>();
        try {
            listOfBranch =branchBoProxy.getByCriteria(branch);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "detailRekananOpsBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboBranch.addAll(listOfBranch);
        return SUCCESS;
    }

    // Sigit 2020-03-10
    public String initDetailTarif(){
        logger.info("[CheckupAction.initComboTarif] START process >>>");

        String id = this.id;
        String tipe = this.transTipe;

        // jika refresh tidak mengambil session lagi
        if ("refresh".equalsIgnoreCase(tipe)){
            logger.info("[CheckupAction.initComboTarif] refresh END process <<<");
            return "init_detail_per_tarif";
        }

        // mendapatkan data parent yg di set ke object DetailRekananOps;
        try {
            List<DetailRekananOps> detailRekananOps = detailRekananOpsBoProxy.getParentDataById(id);

            // set jika ditemukan parent
            if (detailRekananOps.size() > 0)
                setDetailRekananOps(detailRekananOps.get(0));
            // END

        } catch (GeneralBOException e){
            logger.error("[CheckupAction.initComboTarif] ERROR get Parent ", e);
            throw new GeneralBOException("ERROR get Parent ", e);
        }
        // END

        // mendapatkan list detail per tindakan untuk masing - masing tarif dengan id sebagai parent
        // kemudian di set ke session "listOfTindakan";
        List<DetailRekananOps> listDetailTindakan = new ArrayList<>();
        try {
            listDetailTindakan = detailRekananOpsBoProxy.getDetailDataByIdParent(id);
        } catch (GeneralBOException e){
            logger.error("[CheckupAction.initComboTarif] ERROR get Detail ", e);
            throw new GeneralBOException("ERROR get Detail ", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfTindakan");
        session.setAttribute("listOfTindakan", listDetailTindakan);
        // END

        logger.info("[CheckupAction.initComboTarif] END process <<<");
        return "init_detail_per_tarif";
    }

    // Sigit 2020-03-10
    // untuk prosess save add tarif per tindakan pada detail rekanan;
    public CrudResponse saveAddToSessionTindakan(String stJson) throws JSONException{
        logger.info("[CheckupAction.saveAddToSessionTindakan] START process >>>");

        CrudResponse response = new CrudResponse();

        String userid = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // validasi jika data pada json tidak ada
        if (stJson == null || "".equalsIgnoreCase(stJson)){
            response.addResponse("error","Tidak Bisa Menerima Data Karna Kosong");
            return response;
        }
        // END

        // set to detailRekananOps dari stJson
        JSONObject jsonObject = new JSONObject(stJson);
        DetailRekananOps detailRekananOps = new DetailRekananOps();
        detailRekananOps.setIdItem(jsonObject.getString("id_item"));
        detailRekananOps.setTarif(nullEscapeBigDecimalString(jsonObject.getString("tarif")));
        detailRekananOps.setTarifBpjs(nullEscapeBigDecimalString(jsonObject.getString("tarif_pbjs")));
        detailRekananOps.setDiskonNonBpjs(nullEscapeBigDecimalString(jsonObject.getString("diskon_non_bpjs")));
        detailRekananOps.setDiskonBpjs(nullEscapeBigDecimalString(jsonObject.getString("diskon_bpjs")));
        detailRekananOps.setParentId(jsonObject.getString("parent_id"));
        detailRekananOps.setBranchId(jsonObject.getString("branch_id"));
        detailRekananOps.setTipe("add");
        detailRekananOps.setFlag("Y");
        detailRekananOps.setAction("C");
        detailRekananOps.setCreatedDate(time);
        detailRekananOps.setCreatedWho(userid);
        detailRekananOps.setLastUpdate(time);
        detailRekananOps.setLastUpdateWho(userid);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            detailRekananOpsBo.saveAddDetail(detailRekananOps);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.saveAddToSessionTindakan] ERROR get Detail ", e);
            response.addResponse("error","[DetailRekananOpsAction.saveAddToSessionTindakan] ERROR get Detail " +e);
            return response;
        }

        // set success
        response.addResponse("success","Data Berhasil Tersimpan");

        logger.info("[DetailRekananOpsAction.saveAddToSessionTindakan] END process <<<");
        return response;
    }

    // Sigit 2020-03-16
    // untuk prosess save edit tarif per tindakan pada detail rekanan;
    public CrudResponse saveEditToSessionTindakan(String stJson) throws JSONException{
        logger.info("[DetailRekananOpsAction.saveEditToSessionTindakan] START process >>>");

        CrudResponse response = new CrudResponse();
        String userid = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // validasi jika data pada json tidak ada
        if (stJson == null || "".equalsIgnoreCase(stJson)){
            getResponse().addResponse("error","Tidak Bisa Menerima Data Karna Kosong");
            return getResponse();
        }
        // END

        // mendapatkan id item untuk mencari data pada list pada session
        JSONObject jsonObject = new JSONObject(stJson);
        String idDetailRekananOps = jsonObject.getString("id_detail_rekanan_ops");
        if (idDetailRekananOps == null || "".equalsIgnoreCase(idDetailRekananOps)){
            getResponse().addResponse("error","Tidak Bisa Data Id Item");
            return getResponse();
        }
        // END

        // set nilai tarif
        // set to session;
        DetailRekananOps detail = new DetailRekananOps();
        detail.setIdDetailRekananOps(jsonObject.getString("id_detail_rekanan_ops"));
        detail.setTarif(nullEscapeBigDecimalString(jsonObject.getString("tarif")));
        detail.setTarifBpjs(nullEscapeBigDecimalString(jsonObject.getString("tarif_bpjs")));
        detail.setDiskonNonBpjs(nullEscapeBigDecimalString(jsonObject.getString("diskon_non_bpjs")));
        detail.setDiskonBpjs(nullEscapeBigDecimalString(jsonObject.getString("diskon_bpjs")));
        detail.setFlag("Y");
        detail.setAction("U");
        detail.setLastUpdate(time);
        detail.setLastUpdateWho(userid);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            detailRekananOpsBo.saveEditDetail(detail);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.saveEditToSessionTindakan] ERROR get Detail ", e);
            response.addResponse("error","[DetailRekananOpsAction.saveEditToSessionTindakan] ERROR get Detail " +e);
            return response;
        }

        // set success
        response.addResponse("success","Data Berhasil Diupdate");

        logger.info("[DetailRekananOpsAction.saveEditToSessionTindakan] END process <<<");
        return response;
    }

    // Sigit 2020-03-16
    // untuk prosess save delete tarif per tindakan pada detail rekanan;
    public CrudResponse saveDeleteToSessionTindakan(String stJson) throws JSONException{
        logger.info("[DetailRekananOpsAction.saveDeleteToSessionTindakan] START process >>>");

        CrudResponse response = new CrudResponse();

        String userid = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        // validasi jika data pada json tidak ada
        if (stJson == null || "".equalsIgnoreCase(stJson)){
            response.addResponse("error","Tidak Bisa Menerima Data Karna Kosong");
            return response;
        }
        // END

        // mendapatkan id item untuk mencari data pada list pada session
        JSONObject jsonObject = new JSONObject(stJson);
        String idDetailRekananOps = jsonObject.getString("id_detail_rekanan_ops");
        if (idDetailRekananOps == null || "".equalsIgnoreCase(idDetailRekananOps)){
            response.addResponse("error","Tidak Bisa Data Id Item");
            return response;
        }
        // END

        DetailRekananOps detail = new DetailRekananOps();
        detail.setIdDetailRekananOps(jsonObject.getString("id_detail_rekanan_ops"));
        detail.setAction("U");
        detail.setLastUpdate(time);
        detail.setLastUpdateWho(userid);


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            detailRekananOpsBo.saveDeleteDetail(detail);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.saveEditToSessionTindakan] ERROR get Detail ", e);
            response.addResponse("error","[DetailRekananOpsAction.saveEditToSessionTindakan] ERROR get Detail " +e);
            return response;
        }

        // set success
        response.addResponse("success","Data Berhasil Dihapus");

        logger.info("[DetailRekananOpsAction.saveDeleteToSessionTindakan] END process <<<");
        return response;
    }

    public List<DetailRekananOps> refreshSessionDetailRekanan(){
        logger.info("[DetailRekananOpsAction.getSessionByIdItem] START >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DetailRekananOps> detailRekananOps = (List<DetailRekananOps>) session.getAttribute("listOfTindakan");

        session.removeAttribute("listOfTindakan");
        session.setAttribute("listOfTindakan", detailRekananOps);

        logger.info("[DetailRekananOpsAction.getSessionByIdItem] END <<<");
        return detailRekananOps;
    }


    public DetailRekananOps getSessionByIdItem(String idDetailRekananOps){
        logger.info("[DetailRekananOpsAction.getSessionByIdItem] START >>>");

        DetailRekananOps detailRekananOps = new DetailRekananOps();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DetailRekananOps> listOfTindakan = (List<DetailRekananOps>) session.getAttribute("listOfTindakan");

        if (listOfTindakan.size() > 0){
            List<DetailRekananOps> filteredList = listOfTindakan.stream().filter(p->p.getIdDetailRekananOps().equalsIgnoreCase(idDetailRekananOps)).collect(Collectors.toList());
            if (filteredList.size() > 0){
                detailRekananOps = filteredList.get(0);
            }
        }

        logger.info("[DetailRekananOpsAction.getSessionByIdItem] END <<<");
        return detailRekananOps;
    }

    private BigDecimal nullEscapeBigDecimalString(String item){
        if (item == null || "".equalsIgnoreCase(item))
            return new BigDecimal(0);
        else
            return new BigDecimal(item);
    }

    public List<Pelayanan> getListPelayananByBranchId(String branchId){
        logger.info("[DetailRekananOpsAction.getListPelayananByBranchId] START >>>");

        List<Pelayanan> pelayanans = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            pelayanans = detailRekananOpsBo.getListPelayananByBranchId(branchId);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.getListPelayananByBranchId] ERROR get Detail ", e);
            throw new GeneralBOException("ERROR get Detail ", e);
        }


        logger.info("[DetailRekananOpsAction.getListPelayananByBranchId] END <<<");
        return pelayanans;
    }

    public List<Tindakan> getListTindakanByPelayanan(String idPelayanan){
        logger.info("[DetailRekananOpsAction.getListTindakanByPelayanan] START >>>");

        List<Tindakan> tindakans = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            tindakans = detailRekananOpsBo.getListTindakanByPelayanan(idPelayanan);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.getListTindakanByPelayanan] ERROR get Detail ", e);
            throw new GeneralBOException("ERROR get Detail ", e);
        }


        logger.info("[DetailRekananOpsAction.getListTindakanByPelayanan] END <<<");
        return tindakans;
    }

    public Tindakan getTindakanById(String idTindakan){
        logger.info("[DetailRekananOpsAction.getTindakanById] START >>>");

        Tindakan tindakan = new Tindakan();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailRekananOpsBo detailRekananOpsBo = (DetailRekananOpsBo) ctx.getBean("detailRekananOpsBoProxy");

        try {
            tindakan = detailRekananOpsBo.getTindakanById(idTindakan);
        } catch (GeneralBOException e){
            logger.error("[DetailRekananOpsAction.getTindakanById] ERROR get Detail ", e);
            throw new GeneralBOException("ERROR get Detail ", e);
        }

        logger.info("[DetailRekananOpsAction.getTindakanById] END <<<");
        return tindakan;
    }
}