package com.neurix.authorization.company.action;

import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.AreaBranchUserBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.AreaBranchUser;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.user.model.User;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class AreaBranchUserAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(AreaBranchUserAction.class);

    private AreaBranchUserBo areaBranchUserBoProxy;
    private AreaBo areaBoProxy;
    private BranchBo branchBoProxy;
    private AreaBranchUser areaBranchUser;
    private boolean edit;
    private String id2;
    private String[] userIdChecked;
    private List<Area> listOfComboArea=new ArrayList<Area>();
    private List<Branch> listOfComboBranch=new ArrayList<Branch>();
    private String areaId;
    private String branchId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setAreaBranchUserBoProxy(AreaBranchUserBo areaBranchUserBoProxy) {
        this.areaBranchUserBoProxy = areaBranchUserBoProxy;
    }

    public void setAreaBoProxy(AreaBo areaBoProxy) {
        this.areaBoProxy = areaBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public List<Area> getListOfComboArea() {
        return listOfComboArea;
    }

    public void setListOfComboArea(List<Area> listOfComboArea) {
        this.listOfComboArea = listOfComboArea;
    }

    public List<Branch> getListOfComboBranch() {
        return listOfComboBranch;
    }

    public void setListOfComboBranch(List<Branch> listOfComboBranch) {
        this.listOfComboBranch = listOfComboBranch;
    }

    public AreaBranchUser getAreaBranchUser() {
        return areaBranchUser;
    }

    public void setAreaBranchUser(AreaBranchUser areaBranchUser) {
        this.areaBranchUser = areaBranchUser;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String[] getUserIdChecked() {
        return userIdChecked;
    }

    public void setUserIdChecked(String[] userIdChecked) {
        this.userIdChecked = userIdChecked;
    }

    @Override
    public String edit() {
        logger.info("[AreaBranchUserAction.edit] start process >>>");

        String itemAreaId = getId();
        String itemBranchId = getId2();
        String itemFlag = getFlag();
        AreaBranchUser editAreaBranchUser = new AreaBranchUser();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AreaBranchUser> listOfResult = (List<AreaBranchUser>) session.getAttribute("listOfResult");

            for (AreaBranchUser itemAreaBranchUser : listOfResult) {
                if (itemAreaBranchUser.getAreaId().equalsIgnoreCase(itemAreaId) && 
                        itemAreaBranchUser.getBranchId().equalsIgnoreCase(itemBranchId)) {
                    editAreaBranchUser.setAreaId(itemAreaBranchUser.getAreaId());
                    editAreaBranchUser.setAreaName(itemAreaBranchUser.getAreaName());
                    editAreaBranchUser.setBranchId(itemAreaBranchUser.getBranchId());
                    editAreaBranchUser.setBranchName(itemAreaBranchUser.getBranchName());
                    editAreaBranchUser.setListOfUser(itemAreaBranchUser.getListOfUser());
                    setAreaId(itemAreaBranchUser.getAreaId());
                    setBranchId(itemAreaBranchUser.getBranchId());

                    break;
                }
            }

        }

        setAreaBranchUser(editAreaBranchUser);
        setAddOrEdit(true);

        logger.info("[AreaBranchUserAction.edit] end process <<<");

        return "view_detail";
    }

    @Override
    public String add() {
        logger.info("[AreaBranchUserAction.add] start process >>>");

        AreaBranchUser addAreaBranchUser = new AreaBranchUser();

        List<User> listOfDefaultUser = new ArrayList();
        try {
            listOfDefaultUser = areaBranchUserBoProxy.getDefaultUser();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBranchUserBoProxy.saveErrorMessage(e.getMessage(), "AreaBranchUserBO.getDefaultUser");
            } catch (GeneralBOException e1) {
                logger.error("[AreaBranchUserAction.search] Error when saving error,", e1);
            }
            logger.error("[AreaBranchUserAction.save] Error when searching default user by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching default user data, please inform to your admin" );
            return "failure";
        }

        addAreaBranchUser.setListOfUser(listOfDefaultUser);

        setAreaBranchUser(addAreaBranchUser);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfDefaultMenu",listOfDefaultUser);
        session.removeAttribute("listOfResult");

        logger.info("[AreaBranchUserAction.add] end process <<<");

        return "view_detail_add";
    }

    @Override
    public String delete() {
        logger.info("[AreaBranchUserAction.delete] start process >>>");

        String itemAreaId = getId();
        String itemBranchId = getId2();
        String itemFlag = getFlag();
        AreaBranchUser editAreaBranchUser = new AreaBranchUser();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AreaBranchUser> listOfResult = (List<AreaBranchUser>) session.getAttribute("listOfResult");

            for (AreaBranchUser itemAreaBranchUser : listOfResult) {
                if (itemAreaBranchUser.getAreaId().equalsIgnoreCase(itemAreaId) &&
                        itemAreaBranchUser.getBranchId().equalsIgnoreCase(itemBranchId)) {
                    editAreaBranchUser.setAreaId(itemAreaBranchUser.getAreaId());
                    editAreaBranchUser.setAreaName(itemAreaBranchUser.getAreaName());
                    editAreaBranchUser.setBranchId(itemAreaBranchUser.getBranchId());
                    editAreaBranchUser.setBranchName(itemAreaBranchUser.getBranchName());
                    editAreaBranchUser.setListOfUser(itemAreaBranchUser.getListOfUser());
                    setAreaId(itemAreaBranchUser.getAreaId());
                    setBranchId(itemAreaBranchUser.getBranchId());

                    break;
                }
            }

        }

        setAreaBranchUser(editAreaBranchUser);
        setDelete(true);

        logger.info("[AreaBranchUserAction.delete] end process <<<");

        return "view_detail";
    }

    @Override
    public String view() {
        logger.info("[AreaBranchUserAction.view] start process >>>");

        String itemAreaId = getId();
        String itemBranchId = getId2();
        String itemFlag = getFlag();
        AreaBranchUser editAreaBranchUser = new AreaBranchUser();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AreaBranchUser> listOfResult = (List<AreaBranchUser>) session.getAttribute("listOfResult");

            for (AreaBranchUser itemAreaBranchUser : listOfResult) {
                if (itemAreaBranchUser.getAreaId().equalsIgnoreCase(itemAreaId) &&
                        itemAreaBranchUser.getBranchId().equalsIgnoreCase(itemBranchId)) {
                    editAreaBranchUser.setAreaId(itemAreaBranchUser.getAreaId());
                    editAreaBranchUser.setAreaName(itemAreaBranchUser.getAreaName());
                    editAreaBranchUser.setBranchId(itemAreaBranchUser.getBranchId());
                    editAreaBranchUser.setBranchName(itemAreaBranchUser.getBranchName());
                    editAreaBranchUser.setListOfUser(itemAreaBranchUser.getListOfUser());

                    break;
                }
            }

        }
        setAreaBranchUser(editAreaBranchUser);

        logger.info("[AreaBranchUserAction.view] end process <<<");

        return "view_detail";
    }


    @Override
    public String save() {
        logger.info("[AreaBranchUserAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {

                String itemAreaId = "";
                String itemBranchId = "";
                if (getAreaBranchUser() != null) {
                    itemAreaId = getAreaBranchUser().getAreaId();
                    itemBranchId = getAreaBranchUser().getBranchId();
                } else {
                    itemAreaId = getAreaId();
                    itemBranchId = getBranchId();

                }

                if (itemAreaId != null && !"".equalsIgnoreCase(itemAreaId) &&
                        itemBranchId != null && !"".equalsIgnoreCase(itemBranchId)) {

                    //edit
                    try {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        AreaBranchUser editAreaBranchUser = getAreaBranchUser();
                        String[] checkedUser = getUserIdChecked();
                        List<User> listOfUser = new ArrayList<User>();
                        for (int i=0; i < checkedUser.length; i++) { //store checked menu
                            User user = new User();
                            user.setUserId(checkedUser[i]);
                            listOfUser.add(user);
                        }

                        editAreaBranchUser.setAreaId(itemAreaId);
                        editAreaBranchUser.setBranchId(itemBranchId);
                        editAreaBranchUser.setListOfUser(listOfUser);
                        editAreaBranchUser.setCreatedDate(updateTime);
                        editAreaBranchUser.setCreatedWho(userLogin);
                        editAreaBranchUser.setLastUpdate(updateTime);
                        editAreaBranchUser.setLastUpdateWho(userLogin);

                        areaBranchUserBoProxy.saveEdit(editAreaBranchUser);

                    } catch (UsernameNotFoundException e) {
                        logger.error("[AreaBranchUserAction.save] Error when editing item area branch user,", e);
                        addActionError("Error, " + e.getMessage());
                        return ERROR;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = areaBranchUserBoProxy.saveErrorMessage(e.getMessage(), "AreaBranchUserBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[AreaBranchUserAction.save] Error when saving error,", e1);
                        }
                        logger.error("[AreaBranchUserAction.save] Error when editing item area branch user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        return ERROR;
                    }
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                return "success_save_edit";

            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    AreaBranchUser entryAreaBranchUser = getAreaBranchUser();
                    String[] checkedUser = getUserIdChecked();
                    List<User> listOfUser = new ArrayList<User>();
                    for (int i=0; i < checkedUser.length; i++) { //store checked menu
                        User user = new User();
                        user.setUserId(checkedUser[i]);
                        listOfUser.add(user);
                    }

                    entryAreaBranchUser.setListOfUser(listOfUser);
                    entryAreaBranchUser.setCreatedDate(createTime);
                    entryAreaBranchUser.setCreatedWho(userLogin);
                    entryAreaBranchUser.setLastUpdate(createTime);
                    entryAreaBranchUser.setLastUpdateWho(userLogin);

                    areaBranchUserBoProxy.saveAdd(entryAreaBranchUser);

                } catch (UsernameNotFoundException e) {
                    logger.error("[AreaBranchUserAction.save] Error when adding item area branch user,", e);
                    addActionError("Error, " + e.getMessage());
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = areaBranchUserBoProxy.saveErrorMessage(e.getMessage(), "AreaBranchUserBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[AreaBranchUserAction.save] Error when saving error,", e1);
                    }
                    logger.error("[AreaBranchUserAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");
                session.removeAttribute("listOfDefaultMenu");

                return "success_save_add";

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                AreaBranchUser deleteAreaBranchUser = getAreaBranchUser() != null ? getAreaBranchUser() : new AreaBranchUser();
                if (getAreaBranchUser() == null) {
                    deleteAreaBranchUser.setAreaId(getAreaId());
                    deleteAreaBranchUser.setBranchId(getBranchId());
                }
                deleteAreaBranchUser.setLastUpdate(updateTime);
                deleteAreaBranchUser.setLastUpdateWho(userLogin);

                areaBranchUserBoProxy.saveDelete(deleteAreaBranchUser);

            } catch (UsernameNotFoundException e) {
                logger.error("[AreaBranchUserAction.save] Error when deleting item area branch user,", e);
                addActionError("Error, " + e.getMessage());
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = areaBranchUserBoProxy.saveErrorMessage(e.getMessage(), "AreaBranchUserBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[AreaBranchUserAction.save] Error when saving error,", e1);
                }
                logger.error("[AreaBranchUserAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");

            return "success_save_delete";
        }

        logger.info("[AreaBranchUserAction.save] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        AreaBranchUser initAreaBranchUser = new AreaBranchUser();
        setAreaBranchUser(initAreaBranchUser);
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
        logger.info("[AreaBranchUserAction.search] start process >>>");

        AreaBranchUser searchAreaBranchUser = getAreaBranchUser();
        List<AreaBranchUser> listOfSearchAreaBranchUser = new ArrayList();
        try {
            listOfSearchAreaBranchUser = areaBranchUserBoProxy.getByCriteria(searchAreaBranchUser);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBranchUserBoProxy.saveErrorMessage(e.getMessage(), "AreaBranchUserBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AreaBranchUserAction.search] Error when saving error,", e1);
            }
            logger.error("[AreaBranchUserAction.save] Error when searching Area-Branch-User by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchAreaBranchUser);

        logger.info("[AreaBranchUserAction.search] end process <<<");

        return SUCCESS;
    }


    public String initComboArea() {

        Area area=new Area();
        area.setFlag("Y");

        List<Area> listOfArea=new ArrayList<Area>();
        try {
            listOfArea = areaBoProxy.getByCriteria(area);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AreaBranchUserAction.initComboRoles] Error when saving error,", e1);
            }
            logger.error("[AreaBranchUserAction.initComboRoles] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        if (!listOfArea.isEmpty()) {
            listOfComboArea.addAll(listOfArea);
        }

        return "init_combo_area";
    }

    public String initComboBranch() {

        Branch branch=new Branch();
        branch.setFlag("Y");

        List<Branch> listOfBranch=new ArrayList<Branch>();
        try {
            listOfBranch = branchBoProxy.getByCriteria(branch);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AreaBranchUserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[AreaBranchUserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        if (!listOfBranch.isEmpty()) {
            listOfComboBranch.addAll(listOfBranch);
        }

        return "init_combo_branch";
    }


}