package com.neurix.hris.master.shift.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.shift.bo.ShiftBo;
import com.neurix.hris.master.shift.model.Shift;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class ShiftAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(ShiftAction.class);

    private Shift shift;
    private ShiftBo shiftBoProxy;
    private List<Shift> listOfComboShift = new ArrayList<Shift>();

    public List<Shift> getListOfComboShift() {
        return listOfComboShift;
    }

    public void setListOfComboShift(List<Shift> listOfComboShift) {
        this.listOfComboShift = listOfComboShift;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ShiftAction.logger = logger;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public ShiftBo getShiftBoProxy() {
        return shiftBoProxy;
    }

    public void setShiftBoProxy(ShiftBo shiftBoProxy) {
        this.shiftBoProxy = shiftBoProxy;
    }

    public Shift init(String kode, String flag){
        logger.info("[ShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Shift> listOfResult = (List<Shift>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Shift shift: listOfResult) {
                    if(kode.equalsIgnoreCase(shift.getShiftId()) && flag.equalsIgnoreCase(shift.getFlag())){
                        String[] shiftname = shift.getShiftName().split(Pattern.quote(" | "));
                        if(shiftname.length>1){
                            shift.setShiftName(shiftname[0]);
                        }
                        setShift(shift);
                        break;
                    }
                }
            } else {
                setShift(new Shift());
            }

            logger.info("[ShiftAction.init] end process >>>");
        }
        return getShift();
    }

    @Override
    public String add() {
        logger.info("[ShiftAction.add] start process >>>");
        Shift addShift = new Shift();
        setShift(addShift);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[ShiftAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ShiftAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Shift editShift = new Shift();

        if(itemFlag != null){
            try {
                editShift = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[ShiftAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[ShiftAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editShift != null) {
                setShift(editShift);
            } else {
                editShift.setFlag(itemFlag);
                editShift.setShiftId(itemId);
                setShift(editShift);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editShift.setFlag(itemFlag);
            editShift.setShiftId(itemId);
            setShift(editShift);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[ShiftAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[ShiftAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Shift editShift = new Shift();

        if(itemFlag != null){
            try {
                editShift = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[ShiftAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[ShiftAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editShift != null) {
                setShift(editShift);
            } else {
                editShift.setFlag(itemFlag);
                editShift.setShiftId(itemId);
                setShift(editShift);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editShift.setFlag(itemFlag);
            editShift.setShiftId(itemId);
            setShift(editShift);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }
        logger.info("[ShiftAction.delete] end process >>>");
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[ShiftAction.saveAdd] start process >>>");

        try {
            Shift shift = getShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            shift.setCreatedWho(userLogin);
            shift.setLastUpdate(updateTime);
            shift.setCreatedDate(updateTime);
            shift.setLastUpdateWho(userLogin);
            shift.setAction("C");
            shift.setFlag("Y");


            shiftBoProxy.saveAdd(shift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[ShiftAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ShiftAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ShiftAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[ShiftAction.saveEdit] start process >>>");

        try {
            Shift groupShift = getShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setCreatedWho(groupShift.getCreatedWho());
            groupShift.setLastUpdate(updateTime);
            groupShift.setCreatedDate(groupShift.getCreatedDate());
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("Y");


            shiftBoProxy.saveEdit(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[ShiftAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ShiftAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ShiftAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[ShiftAction.saveDelete] start process >>>");

        try {
            Shift groupShift = getShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setCreatedWho(groupShift.getCreatedWho());
            groupShift.setLastUpdate(updateTime);
            groupShift.setCreatedDate(groupShift.getCreatedDate());
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("N");

            shiftBoProxy.saveDelete(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[ShiftAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ShiftAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ShiftAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[ShiftAction.search] start process >>>");

        Shift searchShift = getShift();
        List<Shift> listOfSearchShift = new ArrayList();

        try {
            listOfSearchShift = shiftBoProxy.getByCriteria(searchShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ShiftAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchShift);

        logger.info("[ShiftAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[ShiftAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[ShiftAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboShift() {

        Shift shift = new Shift();
        shift.setFlag("Y");

        List<Shift> listOfShift = new ArrayList<Shift>();
        try {
            listOfShift = shiftBoProxy.getByCriteria(shift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboShift.addAll(listOfShift);

        return "init_combo_shift";
    }

    public List<Shift> searchShiftByGrup(String profesiId) {
        Shift shift = new Shift();
        shift.setFlag("Y");
        shift.setProfesiId(profesiId);

        List<Shift> listOfShift = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ShiftBo shiftBo = (ShiftBo) ctx.getBean("shiftBoProxy");
        try {
            listOfShift = shiftBo.getByCriteria(shift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = shiftBo.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.searchShiftByGrup] Error when saving error,", e1);
            }
            logger.error("[UserAction.searchShiftByGrup] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }
        return listOfShift;
    }
    public String paging(){
        return SUCCESS;
    }
    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
