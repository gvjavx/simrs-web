package com.neurix.simrs.master.rekananops.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
//import com.neurix.simrs.master.rekananOps.bo.RekananOpsBo;
//import com.neurix.simrs.master.rekananOps.model.RekananOps;
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

public class RekananOpsAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(RekananOpsAction.class);
    RekananOps rekananOps;
    RekananOpsBo rekananOpsBoProxy;
    private PositionBo positionBoProxy;

    private List<Position> listOfComboPositions = new ArrayList<Position>();
    private List<RekananOps> listOfComboFarmasi = new ArrayList<RekananOps>();

    public List<RekananOps> getListOfComboFarmasi() {
        return listOfComboFarmasi;
    }

    public void setListOfComboFarmasi(List<RekananOps> listOfComboFarmasi) {
        this.listOfComboFarmasi = listOfComboFarmasi;
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

    public RekananOps init(String kode, String flag){
        logger.info("[PayrollSkalaGajiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekananOps> listOfResult = (List<RekananOps>) session.getAttribute("listOfResultRekananOps");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (RekananOps rekananOps: listOfResult) {
                    if(kode.equalsIgnoreCase(rekananOps.getIdRekananOps()) && flag.equalsIgnoreCase(rekananOps.getFlag())){
                        setRekananOps(rekananOps);
                        break;
                    }
                }
            } else {
                setRekananOps(new RekananOps());
            }
            logger.info("[PayrollSkalaGajiAction.init] end process >>>");
        }
        return getRekananOps();
    }

    @Override
    public String add() {
        logger.info("[RekananOpsAction.add] start process >>>");
        RekananOps addRekananOps = new RekananOps();
        if(!"ADMIN KP".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            addRekananOps.setBranchId(CommonUtil.userBranchLogin());
        }
        setRekananOps(addRekananOps);
        setAddOrEdit(true);
        setAdd(true);
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[RekananOpsAction.add] stop process >>>");
        return "init_add";
    }
//
    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekananOps editRekananOps = new RekananOps();

        if(itemFlag != null){
            try {
                editRekananOps = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if(editRekananOps != null) {
                setRekananOps(editRekananOps);
            } else {
                editRekananOps.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setRekananOps(editRekananOps);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editRekananOps.setFlag(getFlag());
            setRekananOps(editRekananOps);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[RekananOpsAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        RekananOps deleteRekananOps = new RekananOps();

        if (itemFlag != null ) {

            try {
                deleteRekananOps = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteRekananOps != null) {
                setRekananOps(deleteRekananOps);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteRekananOps.setFlag(itemFlag);
                setRekananOps(deleteRekananOps);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteRekananOps.setFlag(itemFlag);
            setRekananOps(deleteRekananOps);
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

            RekananOps editRekananOps = getRekananOps();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editRekananOps.setLastUpdateWho(userLogin);
            editRekananOps.setLastUpdate(updateTime);
            editRekananOps.setAction("U");
            editRekananOps.setFlag("Y");

            rekananOpsBoProxy.saveEdit(editRekananOps);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[PayrollSkalaGajiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveAdd(){
        logger.info("[RekananOpsAction.saveAdd] start process >>>");

        try {
            RekananOps rekananOps = getRekananOps();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rekananOps.setCreatedWho(userLogin);
            rekananOps.setLastUpdate(updateTime);
            rekananOps.setCreatedDate(updateTime);
            rekananOps.setLastUpdateWho(userLogin);
            rekananOps.setAction("C");
            rekananOps.setFlag("Y");

            rekananOpsBoProxy.saveAdd(rekananOps);
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
        logger.info("[RekananOpsAction.search] start process >>>");

        RekananOps searchRekananOps = getRekananOps();
        List<DetailRekananOps> listOfsearchRekananOps = new ArrayList();
        try {
            listOfsearchRekananOps = rekananOpsBoProxy.getSearchByCriteria(searchRekananOps);
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

        logger.info("[RekananOpsAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[RekananOpsAction.initForm] start process >>>");
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
        logger.info("[RekananOpsAction.initForm] end process >>>");
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

    public String initComboPosition() {

        Position position = new Position();
        position.setFlag("Y");
//        position.setKategori("rekananOps");
        List<Position> listOfPosition = new ArrayList<Position>();
        try {
            listOfPosition = positionBoProxy.getByCriteria(position);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPositions.addAll(listOfPosition);

        return "init_combo_position";
    }

//    public String initComboRekananOpsFarmasi(){
//
//        String branchId = CommonUtil.userBranchLogin();
//
//        List<RekananOps> rekananOpss = new ArrayList<>();
//        try {
//            rekananOpss = rekananOpsBoProxy.getListRekananOps(branchId);
//        } catch (GeneralBOException e){
//            logger.error("[RekananOpsAction.initComboRekananOpsFarmasi] ERROR. ", e);
//            throw new GeneralBOException("[RekananOpsAction.initComboRekananOpsFarmasi] ERROR. "+e.getMessage());
//        }
//
//        listOfComboFarmasi.addAll(rekananOpss);
//        return "init_combo_farmasi";
//    }

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
}