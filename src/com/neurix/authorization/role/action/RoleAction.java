package com.neurix.authorization.role.action;


import com.neurix.authorization.role.bo.RoleBo;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.Roles;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 02/10/2014.
 */
public class RoleAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(RoleAction.class);

    private RoleBo roleBoProxy;
    private List<Roles> roleList;
    private Roles roles;

    public void setRoleBoProxy(RoleBo roleBoProxy) {
        this.roleBoProxy = roleBoProxy;
    }

    public List<Roles> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Roles> roleList) {
        this.roleList = roleList;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    private Roles init(String id, String flag) throws NumberFormatException, GeneralBOException {

        logger.info("[RoleAction.init] start process >>>");

        Roles initRoles = new Roles();
        if (id != null && !"".equalsIgnoreCase(id)) {
            long lId = Long.parseLong(id);
            initRoles = roleBoProxy.getRoleById(lId, flag);
        }

        logger.info("[RoleAction.init] end process <<<");

        return initRoles;
    }

    @Override
    public String edit() {

        logger.info("[RoleAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Roles editRoles = new Roles();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            try {
                editRoles = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[RoleAction.edit] error parsing long.", e);
                addFieldError("Role.roleId", "Role Id must number.");
                roles.setErrorMessage("Role.roleId, Role Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.getRoleById");
                } catch (GeneralBOException e1) {
                    logger.error("[RoleAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RoleAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editRoles != null) {
                if (editRoles.getFlag().equalsIgnoreCase("Y")) {
                    setAddOrEdit(true);
                    setRoles(editRoles);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    editRoles.setStRoleId(itemId);
                    editRoles.setFlag(itemFlag);
                    setRoles(editRoles);
                    addActionError("Error, Unable to edit again with flag = N.");
                    roles.setErrorMessage("Error, Unable to edit again with flag = N.");
                    return "failure";
                }
            } else {
                editRoles.setStRoleId(itemId);
                editRoles.setFlag(itemFlag);
                setRoles(editRoles);
                addActionError("Error, Unable to find data with id = " + itemId);
                roles.setErrorMessage("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRoles.setStRoleId(itemId);
            editRoles.setFlag(itemFlag);
            setRoles(editRoles);
            addActionError("Error, Unable to edit again with flag = N.");
            roles.setErrorMessage("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[RoleAction.edit] end process <<<");

        return INPUT;
    }

    @Override
    public String add() {
        logger.info("[RoleAction.add] start process >>>");
        Roles addRoles = new Roles();
        setRoles(addRoles);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RoleAction.add] end process <<<");
        return INPUT;
    }

    @Override
    public String delete() {
        logger.info("[RoleAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Roles deleteRoles = new Roles();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag) ) {

            try {
                deleteRoles = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[RoleAction.delete] error parsing long.", e);
                addFieldError("roles.roleId", "Role Id must number.");
                roles.setErrorMessage("roles.roleId, Role Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.getRoleById");
                } catch (GeneralBOException e1) {
                    logger.error("[RoleAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[RoleAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteRoles != null) {
                if (deleteRoles.getFlag().equalsIgnoreCase("Y")) {
                    setDelete(true);
                    setRoles(deleteRoles);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    deleteRoles.setStRoleId(itemId);
                    deleteRoles.setFlag(itemFlag);
                    setRoles(deleteRoles);
                    addActionError("Error, Unable to delete again with flag = N.");
                    roles.setErrorMessage("Error, Unable to delete again with flag = N.");
                    return "failure";
                }
            } else {
                deleteRoles.setStRoleId(itemId);
                deleteRoles.setFlag(itemFlag);
                setRoles(deleteRoles);
                addActionError("Error, Unable to find data with id = " + itemId);
                roles.setErrorMessage("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteRoles.setStRoleId(itemId);
            deleteRoles.setFlag(itemFlag);
            setRoles(deleteRoles);
            addActionError("Error, Unable to delete again with flag = N.");
            roles.setErrorMessage("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[RoleAction.delete] end process <<<");

        return INPUT;
    }

    @Override
    public String view() {

        return INPUT;
    }

    @Override
    public String save() {
        logger.info("[RoleAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {
                String itemId = getRoles().getStRoleId();
                if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                    //edit
                    try {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        Roles editRoles = getRoles();
                        Long lItemId = Long.parseLong(itemId);
                        editRoles.setRoleId(lItemId);
                        editRoles.setCreatedDate(updateTime);
                        editRoles.setCreatedWho(userLogin);
                        editRoles.setLastUpdate(updateTime);
                        editRoles.setLastUpdateWho(userLogin);
                        editRoles.setAction("U");

                        roleBoProxy.saveEdit(editRoles);
                        roles.setSuccessMessage("Successfully Updated Data");
                    } catch (NumberFormatException e) {
                        logger.error("[RoleAction.save] Error when editing item role,", e);
                        addActionError("Error, " + "Role Id must number.");
                        roles.setSuccessMessage("");
                        roles.setErrorMessage("Error, " + "Role Id must number.");
                        return ERROR;
                    } catch (UsernameNotFoundException e) {
                        logger.error("[RoleAction.save] Error when editing item role,", e);
                        addActionError("Error, " + e.getMessage());
                        roles.setSuccessMessage("");
                        roles.setErrorMessage("Error, " + e.getMessage());
                        return ERROR;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[RoleAction.save] Error when saving error,", e1);
                        }
                        logger.error("[RoleAction.save] Error when editing item role," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        roles.setSuccessMessage("");
                        roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        return ERROR;
                    }
                }
            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    Roles entryRoles = getRoles();

                    entryRoles.setCreatedDate(createTime);
                    entryRoles.setCreatedWho(userLogin);
                    entryRoles.setLastUpdate(createTime);
                    entryRoles.setLastUpdateWho(userLogin);
                    entryRoles.setAction("C");

                    roleBoProxy.saveAdd(entryRoles);
                    roles.setSuccessMessage("Successfully Entry New Data");
                } catch (UsernameNotFoundException e) {
                    logger.error("[RoleAction.save] Error when adding item role,", e);
                    addActionError("Error, " + e.getMessage());
                    roles.setSuccessMessage("");
                    roles.setErrorMessage("Error, " + e.getMessage());
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[RoleAction.save] Error when saving error,", e1);
                    }
                    logger.error("[RoleAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    roles.setSuccessMessage("");
                    roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                Roles deleteRoles = getRoles();

                if (deleteRoles.getStRoleId() != null && !"".equalsIgnoreCase(deleteRoles.getStRoleId()))
                    deleteRoles.setRoleId(Long.parseLong(deleteRoles.getStRoleId()));

                deleteRoles.setLastUpdate(updateTime);
                deleteRoles.setLastUpdateWho(userLogin);
                deleteRoles.setAction("D");

                roleBoProxy.saveDelete(deleteRoles);
                roles.setSuccessMessage("Successfully Deleting Data");

            } catch (UsernameNotFoundException e) {
                logger.error("[RoleAction.save] Error when deleting item role,", e);
                addActionError("Error, " + e.getMessage());
                roles.setSuccessMessage("");
                roles.setErrorMessage("Error, " + e.getMessage());
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[RoleAction.save] Error when saving error,", e1);
                }
                logger.error("[RoleAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                roles.setSuccessMessage("");
                roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RoleAction.save] end process <<<");

        return "success_save";
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        Roles initRoles = new Roles();
        setRoles(initRoles);
        setDelete(false);
        setAdd(false);
        setAddOrEdit(false);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

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

    @Override
    public String search() {
        logger.info("[RoleAction.search] start process >>>");

        Roles searchRoles = getRoles();
        List<Roles> listOfSearchRoles = new ArrayList();
        try {
            listOfSearchRoles = roleBoProxy.getByCriteria(searchRoles);
            roles.setSuccessMessage("Search Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RoleAction.search] Error when saving error,", e1);
            }
            logger.error("[RoleAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            roles.setSuccessMessage("");
            roles.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchRoles);

        logger.info("[RoleAction.search] end process <<<");

        return SUCCESS;
    }

    public List initComboRole(String query) {
        logger.info("[RoleAction.initComboRole] start process >>>");

        List<Roles> listOfRoles = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RoleBo roleBo = (RoleBo) ctx.getBean("roleBoProxy");

        try {
            listOfRoles = roleBo.getComboRoleWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = roleBo.saveErrorMessage(e.getMessage(), "RoleBO.getComboRoleWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RoleAction.initComboRole] Error when saving error,", e1);
            }
            logger.error("[RoleAction.initComboRole] Error when get combo role," + "[" + logId + "] Found problem when retrieving combo role data, please inform to your admin.", e);
        }

        logger.info("[RoleAction.initComboRole] end process <<<");

        return listOfRoles;
    }

    public Roles getRoleById(String roleId){
        logger.info("[RoleAction.getRoleById] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RoleBo roleBo = (RoleBo) ctx.getBean("roleBoProxy");

        Long lRoleId = Long.parseLong(roleId);
        if (roleId != null && !"".equalsIgnoreCase(roleId));

        Roles result = roleBo.getRoleById(lRoleId, "Y");
        return result;
    }

}