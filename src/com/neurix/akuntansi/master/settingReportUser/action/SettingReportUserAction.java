package com.neurix.akuntansi.master.settingReportUser.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.settingReportUser.bo.SettingReportUserBo;
import com.neurix.akuntansi.master.settingReportUser.model.SettingReportUser;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SettingReportUserAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(SettingReportUserAction.class);
    private SettingReportUserBo settingReportUserBoProxy;
    private SettingReportUser settingReportUser;
    private List<SettingReportUser> listOfComboSettingReportUser = new ArrayList<SettingReportUser>();

    public List<SettingReportUser> getListOfComboSettingReportUser() {
        return listOfComboSettingReportUser;
    }

    public void setListOfComboSettingReportUser(List<SettingReportUser> listOfComboSettingReportUser) {
        this.listOfComboSettingReportUser = listOfComboSettingReportUser;
    }


    public SettingReportUserBo getSettingReportUserBoProxy() {
        return settingReportUserBoProxy;
    }

    public void setSettingReportUserBoProxy(SettingReportUserBo settingReportUserBoProxy) {
        this.settingReportUserBoProxy = settingReportUserBoProxy;
    }

    public SettingReportUser getSettingReportUser() {
        return settingReportUser;
    }

    public void setSettingReportUser(SettingReportUser settingReportUser) {
        this.settingReportUser = settingReportUser;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SettingReportUserAction.logger = logger;
    }


    public String initComboSettingReportUser() {
        logger.info("[SettingReportUserAction.initComboSettingReportUser] start process >>>");

        SettingReportUser search = new SettingReportUser();
        List<SettingReportUser> settingReportUserList = new ArrayList();
        search.setFlag("Y");
        try {
            settingReportUserList = settingReportUserBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportUserAction.initComboSettingReportUser] Error when saving error,", e1);
            }
            logger.error("[SettingReportUserAction.initComboSettingReportUser] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboSettingReportUser.addAll(settingReportUserList);
        logger.info("[SettingReportUserAction.initComboSettingReportUser] end process <<<");
        return SUCCESS;
    }

    public SettingReportUser init(String kode, String flag){
        logger.info("[SettingReportUserAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SettingReportUser> listOfResult = (List<SettingReportUser>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SettingReportUser settingReportUser: listOfResult) {
                    if(kode.equalsIgnoreCase(settingReportUser.getSettingReportUserId()) && flag.equalsIgnoreCase(settingReportUser.getFlag())){
                        setSettingReportUser(settingReportUser);
                        break;
                    }
                }
            } else {
                setSettingReportUser(new SettingReportUser());
            }

            logger.info("[SettingReportUserAction.init] end process >>>");
        }
        return getSettingReportUser();
    }

    @Override
    public String add() {
        logger.info("[SettingReportUserAction.add] start process >>>");
        SettingReportUser addSettingReportUser = new SettingReportUser();
        setSettingReportUser(addSettingReportUser);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[SettingReportUserAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SettingReportUserAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SettingReportUser editSettingReportUser = new SettingReportUser();

        if(itemFlag != null){
            try {
                editSettingReportUser = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.getSettingReportUserByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SettingReportUserAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SettingReportUserAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSettingReportUser != null) {
                setSettingReportUser(editSettingReportUser);
            } else {
                editSettingReportUser.setFlag(itemFlag);
                editSettingReportUser.setSettingReportUserId(itemId);
                setSettingReportUser(editSettingReportUser);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSettingReportUser.setSettingReportUserId(itemId);
            editSettingReportUser.setFlag(getFlag());
            setSettingReportUser(editSettingReportUser);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SettingReportUserAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SettingReportUserAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SettingReportUser deleteSettingReportUser = new SettingReportUser();

        if (itemFlag != null ) {

            try {
                deleteSettingReportUser = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SettingReportUserAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SettingReportUserAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSettingReportUser != null) {
                setSettingReportUser(deleteSettingReportUser);

            } else {
                deleteSettingReportUser.setSettingReportUserId(itemId);
                deleteSettingReportUser.setFlag(itemFlag);
                setSettingReportUser(deleteSettingReportUser);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSettingReportUser.setSettingReportUserId(itemId);
            deleteSettingReportUser.setFlag(itemFlag);
            setSettingReportUser(deleteSettingReportUser);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SettingReportUserAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[SettingReportUserAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SettingReportUser deleteSettingReportUser = new SettingReportUser();

        if (itemFlag != null ) {
            try {
                deleteSettingReportUser = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SettingReportUserAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[SettingReportUserAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSettingReportUser != null) {
                setSettingReportUser(deleteSettingReportUser);

            } else {
                deleteSettingReportUser.setSettingReportUserId(itemId);
                deleteSettingReportUser.setFlag(itemFlag);
                setSettingReportUser(deleteSettingReportUser);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSettingReportUser.setSettingReportUserId(itemId);
            deleteSettingReportUser.setFlag(itemFlag);
            setSettingReportUser(deleteSettingReportUser);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[SettingReportUserAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[SettingReportUserAction.saveEdit] start process >>>");
        try {
            SettingReportUser editSettingReportUser = getSettingReportUser();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSettingReportUser.setLastUpdateWho(userLogin);
            editSettingReportUser.setLastUpdate(updateTime);
            editSettingReportUser.setAction("U");
            editSettingReportUser.setFlag("Y");

            settingReportUserBoProxy.saveEdit(editSettingReportUser);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportUserAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1);
            }
            logger.error("[SettingReportUserAction.saveEdit] Error when editing," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e);
        }

        logger.info("[SettingReportUserAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SettingReportUserAction.saveDelete] start process >>>");
        try {
            SettingReportUser deleteSettingReportUser = getSettingReportUser();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSettingReportUser.setLastUpdate(updateTime);
            deleteSettingReportUser.setLastUpdateWho(userLogin);
            deleteSettingReportUser.setAction("U");
            deleteSettingReportUser.setFlag("N");

            settingReportUserBoProxy.saveDelete(deleteSettingReportUser);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportUserAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SettingReportUserAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SettingReportUserAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd() throws GeneralBOException{
        logger.info("[SettingReportUserAction.saveAdd] start process >>>");

        try {
            SettingReportUser settingReportUser = getSettingReportUser();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            settingReportUser.setCreatedWho(userLogin);
            settingReportUser.setLastUpdate(updateTime);
            settingReportUser.setCreatedDate(updateTime);
            settingReportUser.setLastUpdateWho(userLogin);
            settingReportUser.setAction("C");
            settingReportUser.setFlag("Y");

            settingReportUserBoProxy.saveAdd(settingReportUser);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportUserAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1);
            }
            logger.error("[SettingReportUserAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e);
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[SettingReportUserAction.search] start process >>>");
        SettingReportUser searchSettingReportUser = getSettingReportUser();
        List<SettingReportUser> listOfsearchSettingReportUser = new ArrayList();
        try {
            listOfsearchSettingReportUser = settingReportUserBoProxy.getByCriteria(searchSettingReportUser);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = settingReportUserBoProxy.saveErrorMessage(e.getMessage(), "SettingReportUserBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportUserAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SettingReportUserAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSettingReportUser);

        logger.info("[SettingReportUserAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SettingReportUserAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[SettingReportUserAction.initForm] end process >>>");
        return INPUT;
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
