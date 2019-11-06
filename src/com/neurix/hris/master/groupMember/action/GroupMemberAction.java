package com.neurix.hris.master.groupMember.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.groupMember.bo.GroupMemberBo;
import com.neurix.hris.master.groupMember.model.GroupMember;
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
public class GroupMemberAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(GroupMemberAction.class);

    private GroupMember groupMember;
    private GroupMemberBo groupMemberBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GroupMemberAction.logger = logger;
    }

    public GroupMember getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    public GroupMemberBo getGroupMemberBoProxy() {
        return groupMemberBoProxy;
    }

    public void setGroupMemberBoProxy(GroupMemberBo groupMemberBoProxy) {
        this.groupMemberBoProxy = groupMemberBoProxy;
    }

    public GroupMember init(String kode, String flag){
        logger.info("[GroupMemberShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GroupMember> listOfResult = (List<GroupMember>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (GroupMember groupMemberShift: listOfResult) {
                    if(kode.equalsIgnoreCase(groupMemberShift.getGroupMemberId()) && flag.equalsIgnoreCase(groupMemberShift.getFlag())){
                        setGroupMember(groupMemberShift);
                        break;
                    }
                }
            } else {
                setGroupMember(new GroupMember());
            }

            logger.info("[GroupMemberShiftAction.init] end process >>>");
        }
        return getGroupMember();
    }

    @Override
    public String add() {
        logger.info("[BiayaPengobatanAction.add] start process >>>");
        GroupMember addGroupMember = new GroupMember();
        setGroupMember(addGroupMember);
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

        GroupMember editGroupMember = new GroupMember();

        if(itemFlag != null){
            try {
                editGroupMember = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "groupMemberBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroupMember != null) {
                setGroupMember(editGroupMember);
            } else {
                editGroupMember.setFlag(itemFlag);
                editGroupMember.setGroupMemberId(itemId);
                setGroupMember(editGroupMember);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroupMember.setGroupMemberId(itemId);
            editGroupMember.setFlag(getFlag());
            setGroupMember(editGroupMember);
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

        GroupMember editGroupMember = new GroupMember();

        if(itemFlag != null){
            try {
                editGroupMember = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "groupMemberBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.delete] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGroupMember != null) {
                setGroupMember(editGroupMember);
            } else {
                editGroupMember.setFlag(itemFlag);
                editGroupMember.setGroupMemberId(itemId);
                setGroupMember(editGroupMember);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGroupMember.setGroupMemberId(itemId);
            editGroupMember.setFlag(getFlag());
            setGroupMember(editGroupMember);
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
            GroupMember groupMember = getGroupMember();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupMember.setCreatedWho(userLogin);
            groupMember.setLastUpdate(updateTime);
            groupMember.setCreatedDate(updateTime);
            groupMember.setLastUpdateWho(userLogin);
            groupMember.setAction("C");
            groupMember.setFlag("Y");


            groupMemberBoProxy.saveAdd(groupMember);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "GroupMemberShifBO.saveAdd");
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
            GroupMember groupMemberShift = getGroupMember();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupMemberShift.setCreatedWho(groupMemberShift.getCreatedWho());
            groupMemberShift.setLastUpdate(updateTime);
            groupMemberShift.setCreatedDate(groupMemberShift.getCreatedDate());
            groupMemberShift.setLastUpdateWho(userLogin);
            groupMemberShift.setAction("U");
            groupMemberShift.setFlag("Y");


            groupMemberBoProxy.saveEdit(groupMemberShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "groupMemberShiftBo.saveEdit");
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
            GroupMember groupMemberShift = getGroupMember();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            groupMemberShift.setCreatedWho(groupMemberShift.getCreatedWho());
            groupMemberShift.setLastUpdate(updateTime);
            groupMemberShift.setCreatedDate(groupMemberShift.getCreatedDate());
            groupMemberShift.setLastUpdateWho(userLogin);
            groupMemberShift.setAction("U");
            groupMemberShift.setFlag("N");

            groupMemberBoProxy.saveEdit(groupMemberShift);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "groupMemberShiftBo.saveEdit");
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

        GroupMember searchGroupMember = getGroupMember();
        List<GroupMember> listOfSearchGroupMember = new ArrayList();

        try {
            listOfSearchGroupMember = groupMemberBoProxy.getByCriteria(searchGroupMember);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBoProxy.saveErrorMessage(e.getMessage(), "GroupMemberShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupMemberShiftAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GroupMemberShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchGroupMember);

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[GroupMemberAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[GroupMemberAction.initForm] end process >>>");
        return INPUT;
    }
    public List<GroupMember> searchGroupMember(String GroupId) {
        logger.info("[GroupMemberAction.searchHistoriPegawai] start process >>>");

        GroupMember searchGroupMember = new GroupMember();
        searchGroupMember.setGroupId(GroupId);
        searchGroupMember.setFlag("Y");

        List<GroupMember> groupMemberList = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupMemberBo groupMemberBo = (GroupMemberBo) ctx.getBean("groupMemberBoProxy");
        try {
            groupMemberList = groupMemberBo.getByCriteria(searchGroupMember);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBo.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return null;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        logger.info("[BiodataAction.search] end process <<<");

        return groupMemberList;
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
