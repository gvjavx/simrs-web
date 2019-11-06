package com.neurix.authorization.company.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
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
 * Created by Ferdi on 05/02/2015.
 */
public class BranchAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(BranchAction.class);

    private BranchBo branchBoProxy;
    private List<Branch> branchList;
    private List<Branch> listOfComboBranch = new ArrayList<Branch>();
    private Branch branch;

    public List<Branch> getListOfComboBranch() {
        return listOfComboBranch;
    }

    public void setListOfComboBranch(List<Branch> listOfComboBranch) {
        this.listOfComboBranch = listOfComboBranch;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    private Branch init(String id, String flag) throws NumberFormatException, GeneralBOException {

        logger.info("[BranchAction.init] start process >>>");

        Branch initBranch = new Branch();
        if (id != null && !"".equalsIgnoreCase(id)) {
            initBranch = branchBoProxy.getBranchById(id, flag);
        }

        logger.info("[BranchAction.init] end process <<<");

        return initBranch;
    }

    @Override
    public String edit() {

        logger.info("[BranchAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Branch editBranch = new Branch();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            try {
                editBranch = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[BranchAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BranchAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editBranch != null) {
                if (editBranch.getFlag().equalsIgnoreCase("Y")) {
                    setAddOrEdit(true);
                    setBranch(editBranch);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    editBranch.setBranchId(itemId);
                    editBranch.setFlag(itemFlag);
                    setBranch(editBranch);
                    addActionError("Error, Unable to edit again with flag = N.");
                    return "failure";
                }
            } else {
                editBranch.setBranchId(itemId);
                editBranch.setFlag(itemFlag);
                setBranch(editBranch);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBranch.setBranchId(itemId);
            editBranch.setFlag(itemFlag);
            setBranch(editBranch);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[BranchAction.edit] end process <<<");

        return INPUT;
    }

    @Override
    public String add() {
        logger.info("[BranchAction.add] start process >>>");
        Branch addBranch = new Branch();
        setBranch(addBranch);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BranchAction.add] end process <<<");
        return INPUT;
    }

    @Override
    public String delete() {
        logger.info("[BranchAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Branch deleteBranch = new Branch();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag) ) {

            try {
                deleteBranch = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getBranchById");
                } catch (GeneralBOException e1) {
                    logger.error("[BranchAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[BranchAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteBranch != null) {
                if (deleteBranch.getFlag().equalsIgnoreCase("Y")) {
                    setDelete(true);
                    setBranch(deleteBranch);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    deleteBranch.setBranchId(itemId);
                    deleteBranch.setFlag(itemFlag);
                    setBranch(deleteBranch);
                    addActionError("Error, Unable to delete again with flag = N.");
                    return "failure";
                }
            } else {
                deleteBranch.setBranchId(itemId);
                deleteBranch.setFlag(itemFlag);
                setBranch(deleteBranch);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteBranch.setBranchId(itemId);
            deleteBranch.setFlag(itemFlag);
            setBranch(deleteBranch);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[BranchAction.delete] end process <<<");

        return INPUT;
    }

    @Override
    public String view() {

        return INPUT;
    }


    @Override
    public String save() {
        logger.info("[BranchAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {
                String itemId = getBranch().getBranchId();
                if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                    //edit
                    try {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        Branch editBranch = getBranch();
                        editBranch.setBranchId(itemId);
                        editBranch.setLastUpdate(updateTime);
                        editBranch.setLastUpdateWho(userLogin);
                        editBranch.setAction("U");
                        branchBoProxy.saveEdit(editBranch);
                        branch.setSuccessMessage("Data Successfully Updated");
                    } catch (UsernameNotFoundException e) {
                        logger.error("[BranchAction.save] Error when editing item Branch,", e);
                        //addActionError("Error, " + e.getMessage());
                        branch.setErrorMessage("Error, " + e.getMessage());
                        branch.setSuccessMessage("");
                        return INPUT;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[BranchAction.save] Error when saving error,", e1);
                        }
                        logger.error("[BranchAction.save] Error when editing item Branch," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        //addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        branch.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        branch.setSuccessMessage("");
                        return INPUT;
                    }
                }
            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    Branch entryBranch = getBranch();

                    entryBranch.setCreatedDate(createTime);
                    entryBranch.setCreatedWho(userLogin);
                    entryBranch.setLastUpdate(createTime);
                    entryBranch.setLastUpdateWho(userLogin);
                    entryBranch.setAction("C");
                    branchBoProxy.saveAdd(entryBranch);
                    branch.setSuccessMessage("Successfully Entry New Data");

                } catch (UsernameNotFoundException e) {
                    logger.error("[BranchAction.save] Error when adding item Branch,", e);
                    addActionError("Error, " + e.getMessage());
                    branch.setErrorMessage("Error, " + e.getMessage());
                    branch.setSuccessMessage("");
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[BranchAction.save] Error when saving error,", e1);
                    }
                    logger.error("[BranchAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    branch.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    branch.setSuccessMessage("");
                    return ERROR;
                }

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                Branch deleteBranch = getBranch();

                deleteBranch.setLastUpdate(updateTime);
                deleteBranch.setLastUpdateWho(userLogin);
                deleteBranch.setAction("D");

                branchBoProxy.saveDelete(deleteBranch);
                branch.setSuccessMessage("Data Successfully Deleted");

            } catch (UsernameNotFoundException e) {
                logger.error("[BranchAction.save] Error when deleting item Branch,", e);
                //addActionError("Error, " + e.getMessage());
                branch.setErrorMessage("Error, " + e.getMessage());
                branch.setSuccessMessage("");
                return INPUT;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[BranchAction.save] Error when saving error,", e1);
                }
                logger.error("[BranchAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                //addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                branch.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());;
                branch.setSuccessMessage("");
                return INPUT;
            }

        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BranchAction.save] end process <<<");

        return "success_save";
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        Branch initBranch = new Branch();
        setBranch(initBranch);
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
        logger.info("[BranchAction.search] start process >>>");

        Branch searchBranch = getBranch();
        List<Branch> listOfSearchBranch = new ArrayList();
        try {
            listOfSearchBranch = branchBoProxy.getByCriteria(searchBranch);
            branch.setSuccessMessage("Search Data Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            branch.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            branch.setSuccessMessage("");
            return "failure";
        }

        if (listOfSearchBranch.size() == 0){
            branch.setErrorMessage("Cannot Found Data");
            branch.setSuccessMessage("");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchBranch);

        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public String initComboBranch() {
        logger.info("[BranchAction.search] start process >>>");

        Branch searchBranch = new Branch();
        List<Branch> listOfSearchBranch = new ArrayList();
        searchBranch.setFlag("Y");
        try {
            listOfSearchBranch = branchBoProxy.getByCriteria(searchBranch);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboBranch.addAll(listOfSearchBranch);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public List<Branch> getDataBranch () {
        logger.info("[BranchAction.search] start process >>>");

        Branch searchBranch = new Branch();
        List<Branch> listOfSearchBranch = new ArrayList();
        searchBranch.setFlag("Y");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
            listOfSearchBranch = branchBo.getByCriteria(searchBranch);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        listOfComboBranch.addAll(listOfSearchBranch);
        logger.info("[BranchAction.search] end process <<<");

        return listOfSearchBranch;
    }

}