package com.neurix.simrs.master.pelayanan.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PelayananAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PelayananAction.class);
    Pelayanan pelayanan;
    PelayananBo pelayananBoProxy;
    private PositionBo positionBoProxy;

    private List<Position> listOfComboPositions = new ArrayList<Position>();

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

    public Pelayanan getPelayanan() {
        return pelayanan;
    }

    public void setPelayanan(Pelayanan pelayanan) {
        this.pelayanan = pelayanan;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public Pelayanan init(String kode, String flag){
        logger.info("[PayrollSkalaGajiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Pelayanan> listOfResult = (List<Pelayanan>) session.getAttribute("listOfResultPelayanan");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Pelayanan pelayanan: listOfResult) {
                    if(kode.equalsIgnoreCase(pelayanan.getIdPelayanan()) && flag.equalsIgnoreCase(pelayanan.getFlag())){
                        setPelayanan(pelayanan);
                        break;
                    }
                }
            } else {
                setPelayanan(new Pelayanan());
            }

            logger.info("[PayrollSkalaGajiAction.init] end process >>>");
        }
        return getPelayanan();
    }

    @Override
    public String add() {
        logger.info("[PelayananAction.add] start process >>>");
        Pelayanan addPelayanan = new Pelayanan();
        setPelayanan(addPelayanan);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PelayananAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Pelayanan editPelayanan = new Pelayanan();

        if(itemFlag != null){
            try {
                editPelayanan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getPayrollSkalaGajiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPelayanan != null) {
                setPelayanan(editPelayanan);
            } else {
                editPelayanan.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setPelayanan(editPelayanan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editPelayanan.setFlag(getFlag());
            setPelayanan(editPelayanan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PelayananAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Pelayanan deletePelayanan = new Pelayanan();

        if (itemFlag != null ) {

            try {
                deletePelayanan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePelayanan != null) {
                setPelayanan(deletePelayanan);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deletePelayanan.setFlag(itemFlag);
                setPelayanan(deletePelayanan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deletePelayanan.setFlag(itemFlag);
            setPelayanan(deletePelayanan);
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

            Pelayanan deletePelayanan = getPelayanan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePelayanan.setLastUpdate(updateTime);
            deletePelayanan.setLastUpdateWho(userLogin);
            deletePelayanan.setAction("U");
            deletePelayanan.setFlag("N");

            pelayananBoProxy.saveDelete(deletePelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[PayrollSkalaGajiAction.saveEdit] start process >>>");
        try {

            Pelayanan editPelayanan = getPelayanan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPelayanan.setLastUpdateWho(userLogin);
            editPelayanan.setLastUpdate(updateTime);
            editPelayanan.setAction("U");
            editPelayanan.setFlag("Y");

            pelayananBoProxy.saveEdit(editPelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveAdd(){
        logger.info("[PelayananAction.saveAdd] start process >>>");

        try {
            Pelayanan pelayanan = getPelayanan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            pelayanan.setCreatedWho(userLogin);
            pelayanan.setLastUpdate(updateTime);
            pelayanan.setCreatedDate(updateTime);
            pelayanan.setLastUpdateWho(userLogin);
            pelayanan.setAction("C");
            pelayanan.setFlag("Y");

            pelayananBoProxy.saveAdd(pelayanan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "payrollSkalaGajiBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[pelayananAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[pelayananAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPelayanan");

        logger.info("[pelayananAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PelayananAction.search] start process >>>");

        Pelayanan searchPelayanan = getPelayanan();
        List<Pelayanan> listOfsearchPelayanan = new ArrayList();

        try {
            listOfsearchPelayanan = pelayananBoProxy.getByCriteria(searchPelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelayananAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelayananAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String branchId = CommonUtil.userBranchLogin();
        Pelayanan data = new Pelayanan();
        if (branchId != null){
            data.setBranchUser(branchId);
        }else {
            data.setBranchUser("");
        }
        pelayanan = data;

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPelayanan");
        session.setAttribute("listOfResultPelayanan", listOfsearchPelayanan);

        logger.info("[PelayananAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PelayananAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Pelayanan data = new Pelayanan();
        if (branchId != null){
            data.setBranchUser(branchId);
            data.setBranchId(branchId);
        }else {
            data.setBranchUser("");
            data.setBranchId("");
        }
        pelayanan = data;

        session.removeAttribute("listOfResultPelayanan");
        logger.info("[PelayananAction.initForm] end process >>>");
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
        position.setKategori("pelayanan");
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
}