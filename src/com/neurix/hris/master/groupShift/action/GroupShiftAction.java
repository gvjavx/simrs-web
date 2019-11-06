package com.neurix.hris.master.groupShift.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.groupShift.bo.GroupShiftBo;
import com.neurix.hris.master.groupShift.model.GroupShift;
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

/**
 * Created by thinkpad on 19/03/2018.
 */
public class GroupShiftAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(GroupShiftAction.class);

    private GroupShift groupShift;
    private GroupShiftBo groupShiftBoProxy;
    private List<GroupShift> listOfComboGroupShift = new ArrayList<GroupShift>();
    private List<Group> listOfComboGroup = new ArrayList<Group>();
    private List<Shift> listOfComboShift = new ArrayList<Shift>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupShiftAction.logger = logger;
    }

    public List<Group> getListOfComboGroup() {
        return listOfComboGroup;
    }

    public void setListOfComboGroup(List<Group> listOfComboGroup) {
        this.listOfComboGroup = listOfComboGroup;
    }

    public List<Shift> getListOfComboShift() {
        return listOfComboShift;
    }

    public void setListOfComboShift(List<Shift> listOfComboShift) {
        this.listOfComboShift = listOfComboShift;
    }

    public List<GroupShift> getListOfComboGroupShift() {
        return listOfComboGroupShift;
    }

    public void setListOfComboGroupShift(List<GroupShift> listOfComboGroupShift) {
        this.listOfComboGroupShift = listOfComboGroupShift;
    }

    public GroupShift getGroupShift() {
        return groupShift;
    }

    public void setGroupShift(GroupShift groupShift) {
        this.groupShift = groupShift;
    }

    public GroupShiftBo getGroupShiftBoProxy() {
        return groupShiftBoProxy;
    }

    public void setGroupShiftBoProxy(GroupShiftBo groupShiftBoProxy) {
        this.groupShiftBoProxy = groupShiftBoProxy;
    }

    public GroupShift init(String kode, String flag){
        logger.info("[GroupShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GroupShift> listOfResult = (List<GroupShift>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (GroupShift groupShift: listOfResult) {
                    if(kode.equalsIgnoreCase(groupShift.getGroupShiftId()) && flag.equalsIgnoreCase(groupShift.getFlag())){
                        setGroupShift(groupShift);
                        break;
                    }
                }
            } else {
                setGroupShift(new GroupShift());
            }

            logger.info("[GroupShiftAction.init] end process >>>");
        }
        return getGroupShift();
    }

    @Override
    public String add() {
        logger.info("[GroupShiftAction.add] start process >>>");
        GroupShift addTipeLibur = new GroupShift();
        setGroupShift(addTipeLibur);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GroupShiftAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[GroupShiftAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        GroupShift editGroupShift = new GroupShift();

        if(itemFlag != null){
            try {
                editGroupShift = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GroupShiftAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[GroupShiftAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroupShift != null) {
                setGroupShift(editGroupShift);
            } else {
                editGroupShift.setFlag(itemFlag);
                editGroupShift.setGroupShiftId(itemId);
                setGroupShift(editGroupShift);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroupShift.setGroupShiftId(itemId);
            editGroupShift.setFlag(getFlag());
            setGroupShift(editGroupShift);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[GroupShiftAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[GroupShiftAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        GroupShift editGroupShift = new GroupShift();

        if(itemFlag != null){
            try {
                editGroupShift = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GroupShiftAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[GroupShiftAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroupShift != null) {
                setGroupShift(editGroupShift);
            } else {
                editGroupShift.setFlag(itemFlag);
                editGroupShift.setGroupShiftId(itemId);
                setGroupShift(editGroupShift);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroupShift.setGroupShiftId(itemId);
            editGroupShift.setFlag(getFlag());
            setGroupShift(editGroupShift);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[GroupShiftAction.delete] end process >>>");
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[GroupShiftAction.saveAdd] start process >>>");

        try {
            GroupShift groupShift = getGroupShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setCreatedWho(userLogin);
            groupShift.setLastUpdate(updateTime);
            groupShift.setCreatedDate(updateTime);
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("C");
            groupShift.setFlag("Y");


            groupShiftBoProxy.saveAdd(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "GroupShifBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GroupShiftAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[GroupShiftAction.saveEdit] start process >>>");

        try {
            GroupShift groupShift = getGroupShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setLastUpdate(updateTime);
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("Y");


            groupShiftBoProxy.saveEdit(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GroupShiftAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[GroupShiftAction.saveDelete] start process >>>");

        try {
            GroupShift groupShift = getGroupShift();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setLastUpdate(updateTime);
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("N");

            groupShiftBoProxy.saveDelete(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GroupShiftAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[GroupShiftAction.search] start process >>>");

        GroupShift searchShift = getGroupShift();
        List<GroupShift> listOfSearchGroupShift = new ArrayList();

        try {
            listOfSearchGroupShift = groupShiftBoProxy.getByCriteria(searchShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchGroupShift);

        logger.info("[GroupShiftAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[GroupShiftAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[GroupShiftAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboGroupShift() {

        GroupShift groupShift = new GroupShift();
        groupShift.setFlag("Y");

        List<GroupShift> listOfGroupShift = new ArrayList<GroupShift>();
        try {
            listOfGroupShift = groupShiftBoProxy.getByCriteria(groupShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "GroupShiftBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftBO.getByCriteria] Error when saving error,", e1);
            }
            logger.error("[GroupShiftBO.getByCriteria] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboGroupShift.addAll(listOfGroupShift);
        return "init_combo_group_shift";
    }
    public String initComboGroup() {

        Group group= new Group();
        group.setFlag("Y");

        List<Group> listOfGroup = new ArrayList<Group>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupBo groupBo = (GroupBo) ctx.getBean("groupBoProxy");
        try {
            listOfGroup = groupBo.getByCriteria(group);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[groupBo.getByCriteria] Error when saving error,", e1);
            }
            logger.error("[groupBo.getByCriteria] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboGroup.addAll(listOfGroup);
        return "init_combo_group";
    }
    public String initComboShift() {
        Shift shift = new Shift();
        shift.setFlag("Y");

        List<Shift> listOfShift = new ArrayList<Shift>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ShiftBo shiftBo = (ShiftBo) ctx.getBean("shiftBoProxy");
        try {
            listOfShift = shiftBo.getByCriteria(shift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "shiftBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[shiftBo.getByCriteria] Error when saving error,", e1);
            }
            logger.error("[shiftBo.getByCriteria] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboShift.addAll(listOfShift);
        return "init_combo_shift";
    }
    public String searchGroupShiftId(String groupId,String shiftId) {
        logger.info("[GroupShiftAction.searchGroupShiftId] start process >>>");
        String result="";
        GroupShift searchShift = new GroupShift();
        searchShift.setShiftId(shiftId);
        searchShift.setGroupId(groupId);
        List<GroupShift> listOfSearchGroupShift = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupShiftBo groupShiftBo = (GroupShiftBo) ctx.getBean("groupShiftBoProxy");
        try {
            listOfSearchGroupShift = groupShiftBo.getByCriteria(searchShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.searchGroupShiftId] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupShiftAction.searchGroupShiftId] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        for (GroupShift groupShift:listOfSearchGroupShift){
            result=groupShift.getGroupShiftId();
        }
        logger.info("[GroupShiftAction.searchGroupShiftId] end process <<<");
        return result;
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
