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
import com.neurix.simrs.master.rekananops.bo.DetailRekananOpsBo;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailRekananOpsAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DetailRekananOpsAction.class);
    private RekananOps rekananOps;
    private RekananOpsBo rekananOpsBoProxy;
    private PositionBo positionBoProxy;

    private DetailRekananOpsBo detailRekananOpsBoProxy;
    private DetailRekananOps detailRekananOps;
    private Branch branch;
    private BranchBo branchBoProxy;

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
        logger.info("[PayrollSkalaGajiAction.init] start process >>>");
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
            logger.info("[PayrollSkalaGajiAction.init] end process >>>");
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
                deleteDetailRekananOps = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteDetailRekananOps != null) {
                setDetailRekananOps(deleteDetailRekananOps);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteDetailRekananOps.setFlag(itemFlag);
                setDetailRekananOps(deleteDetailRekananOps);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteDetailRekananOps.setFlag(itemFlag);
            setDetailRekananOps(deleteDetailRekananOps);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiAction.saveDelete] start process >>>");
        try {

            RekananOps deleteRekananOps = getRekananOps();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRekananOps.setLastUpdate(updateTime);
            deleteRekananOps.setLastUpdateWho(userLogin);
            deleteRekananOps.setAction("U");
            deleteRekananOps.setFlag("N");

            rekananOpsBoProxy.saveDelete(deleteRekananOps);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[PayrollSkalaGajiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[PayrollSkalaGajiAction.saveEdit] start process >>>");
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
//        String branchId = CommonUtil.userBranchLogin();
//        RekananOps data = new RekananOps();
//        if (branchId != null){
//            data.setBranchUser(branchId);
//        }else {
//            data.setBranchUser("");
//        }
//        rekananOps = data;

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
}