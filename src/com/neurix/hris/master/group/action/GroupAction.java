package com.neurix.hris.master.group.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.model.Group;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class GroupAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(GroupAction.class);

    private Group group;
    private GroupBo groupBoProxy;
    private List<Group> listOfComboGroup = new ArrayList<>() ;

    public List<Group> getListOfComboGroup() {
        return listOfComboGroup;
    }

    public void setListOfComboGroup(List<Group> listOfComboGroup) {
        this.listOfComboGroup = listOfComboGroup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupAction.logger = logger;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupBo getGroupBoProxy() {
        return groupBoProxy;
    }

    public void setGroupBoProxy(GroupBo groupBoProxy) {
        this.groupBoProxy = groupBoProxy;
    }

    public Group init(String kode, String flag){
        logger.info("[GroupShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Group> listOfResult = (List<Group>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Group groupShift: listOfResult) {
                    if(kode.equalsIgnoreCase(groupShift.getGroupId()) && flag.equalsIgnoreCase(groupShift.getFlag())){
                        setGroup(groupShift);
                        break;
                    }
                }
            } else {
                setGroup(new Group());
            }

            logger.info("[GroupShiftAction.init] end process >>>");
        }
        return getGroup();
    }

    @Override
    public String add() {
        logger.info("[BiayaPengobatanAction.add] start process >>>");
        Group addGroup = new Group();
        setGroup(addGroup);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BiayaPengobatanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Group editGroup = new Group();

        if(itemFlag != null){
            try {
                editGroup = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroup != null) {
                setGroup(editGroup);
            } else {
                editGroup.setFlag(itemFlag);
                editGroup.setGroupId(itemId);
                setGroup(editGroup);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroup.setGroupId(itemId);
            editGroup.setFlag(getFlag());
            setGroup(editGroup);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BiayaPengobatanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BiayaPengobatanAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Group editGroup = new Group();

        if(itemFlag != null){
            try {
                editGroup = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroup != null) {
                setGroup(editGroup);
            } else {
                editGroup.setFlag(itemFlag);
                editGroup.setGroupId(itemId);
                setGroup(editGroup);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroup.setGroupId(itemId);
            editGroup.setFlag(getFlag());
            setGroup(editGroup);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[BiayaPengobatanAction.delete] end process >>>");
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[BiayaPengobatanAction.saveAdd] start process >>>");

        try {
            Group group = getGroup();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            group.setCreatedWho(userLogin);
            group.setLastUpdate(updateTime);
            group.setCreatedDate(updateTime);
            group.setLastUpdateWho(userLogin);
            group.setAction("C");
            group.setFlag("Y");


            groupBoProxy.saveAdd(group);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "GroupShifBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[BiayaPengobatanAction.saveEdit] start process >>>");

        try {
            Group groupShift = getGroup();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setCreatedWho(groupShift.getCreatedWho());
            groupShift.setLastUpdate(updateTime);
            groupShift.setCreatedDate(groupShift.getCreatedDate());
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("Y");


            groupBoProxy.saveEdit(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[BiayaPengobatanAction.saveDelete] start process >>>");

        try {
            Group groupShift = getGroup();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupShift.setCreatedWho(groupShift.getCreatedWho());
            groupShift.setLastUpdate(updateTime);
            groupShift.setCreatedDate(groupShift.getCreatedDate());
            groupShift.setLastUpdateWho(userLogin);
            groupShift.setAction("U");
            groupShift.setFlag("N");

            groupBoProxy.saveEdit(groupShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");

        Group searchShift = getGroup();
        List<Group> listOfSearchGroupShift = new ArrayList();

        try {
            listOfSearchGroupShift = groupBoProxy.getByCriteria(searchShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
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

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboGroup() {
        logger.info("[BranchAction.search] start process >>>");

        Group searchGroup = new Group();
        List<Group> listOfSearchGroup = new ArrayList();
        searchGroup.setFlag("Y");
        try {
            listOfSearchGroup = groupBoProxy.getByCriteria(searchGroup);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboGroup.addAll(listOfSearchGroup);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
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
