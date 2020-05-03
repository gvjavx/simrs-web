package com.neurix.akuntansi.master.settingReportKeuanganKonsol.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
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

public class SettingReportKeuanganKonsolAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(SettingReportKeuanganKonsolAction.class);
    private AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganKonsolBoProxy;
    private AkunSettingReportKeuanganKonsol akunSettingReportKeuanganKonsol;
    private List<AkunSettingReportKeuanganKonsol> listOfComboSettingReportKeuanganKonsol = new ArrayList<AkunSettingReportKeuanganKonsol>();
    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SettingReportKeuanganKonsolAction.logger = logger;
    }

    public AkunSettingReportKeuanganKonsolBo getAkunSettingReportKeuanganKonsolBoProxy() {
        return akunSettingReportKeuanganKonsolBoProxy;
    }

    public void setAkunSettingReportKeuanganKonsolBoProxy(AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganKonsolBoProxy) {
        this.akunSettingReportKeuanganKonsolBoProxy = akunSettingReportKeuanganKonsolBoProxy;
    }

    public AkunSettingReportKeuanganKonsol getAkunSettingReportKeuanganKonsol() {
        return akunSettingReportKeuanganKonsol;
    }

    public void setAkunSettingReportKeuanganKonsol(AkunSettingReportKeuanganKonsol akunSettingReportKeuanganKonsol) {
        this.akunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsol;
    }

    public List<AkunSettingReportKeuanganKonsol> getListOfComboSettingReportKeuanganKonsol() {
        return listOfComboSettingReportKeuanganKonsol;
    }

    public void setListOfComboSettingReportKeuanganKonsol(List<AkunSettingReportKeuanganKonsol> listOfComboSettingReportKeuanganKonsol) {
        this.listOfComboSettingReportKeuanganKonsol = listOfComboSettingReportKeuanganKonsol;
    }

    public AkunSettingReportKeuanganKonsol init(String kode, String flag){
        logger.info("[AkunSettingReportKeuanganKonsolAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AkunSettingReportKeuanganKonsol> listOfResult = (List<AkunSettingReportKeuanganKonsol>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (AkunSettingReportKeuanganKonsol reportKeuanganKonsol: listOfResult) {
                    if(kode.equalsIgnoreCase(reportKeuanganKonsol.getSettingReportKonsolId()) && flag.equalsIgnoreCase(reportKeuanganKonsol.getFlag())){
                        setAkunSettingReportKeuanganKonsol(reportKeuanganKonsol);
                        break;
                    }
                }
            } else {
                setAkunSettingReportKeuanganKonsol(new AkunSettingReportKeuanganKonsol());
            }

            logger.info("[AkunSettingReportKeuanganKonsolAction.init] end process >>>");
        }
        return getAkunSettingReportKeuanganKonsol();
    }

    @Override
    public String add() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.add] start process >>>");
        AkunSettingReportKeuanganKonsol addAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();
        setAkunSettingReportKeuanganKonsol(addAkunSettingReportKeuanganKonsol);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AkunSettingReportKeuanganKonsolAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        AkunSettingReportKeuanganKonsol editAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();

        if(itemFlag != null){
            try {
                editAkunSettingReportKeuanganKonsol = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getAkunSettingReportKeuanganKonsolByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editAkunSettingReportKeuanganKonsol != null) {
                setAkunSettingReportKeuanganKonsol(editAkunSettingReportKeuanganKonsol);
            } else {
                editAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
                editAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
                setAkunSettingReportKeuanganKonsol(editAkunSettingReportKeuanganKonsol);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
            editAkunSettingReportKeuanganKonsol.setFlag(getFlag());
            setAkunSettingReportKeuanganKonsol(editAkunSettingReportKeuanganKonsol);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        AkunSettingReportKeuanganKonsol deleteAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();

        if (itemFlag != null ) {

            try {
                deleteAkunSettingReportKeuanganKonsol = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AkunSettingReportKeuanganKonsolAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[AkunSettingReportKeuanganKonsolAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteAkunSettingReportKeuanganKonsol != null) {
                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);

            } else {
                deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
                deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
            deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
            setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AkunSettingReportKeuanganKonsolAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        AkunSettingReportKeuanganKonsol deleteAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();

        if (itemFlag != null ) {
            try {
                deleteAkunSettingReportKeuanganKonsol = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[AkunSettingReportKeuanganKonsolAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[AkunSettingReportKeuanganKonsolAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteAkunSettingReportKeuanganKonsol != null) {
                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);

            } else {
                deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
                deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
            deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
            setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[AkunSettingReportKeuanganKonsolAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveEdit] start process >>>");
        try {
            AkunSettingReportKeuanganKonsol editAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAkunSettingReportKeuanganKonsol.setLastUpdateWho(userLogin);
            editAkunSettingReportKeuanganKonsol.setLastUpdate(updateTime);
            editAkunSettingReportKeuanganKonsol.setAction("U");
            editAkunSettingReportKeuanganKonsol.setFlag("Y");

            akunSettingReportKeuanganKonsolBoProxy.saveEdit(editAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AkunSettingReportKeuanganKonsolAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveDelete] start process >>>");
        try {
            AkunSettingReportKeuanganKonsol deleteAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteAkunSettingReportKeuanganKonsol.setLastUpdate(updateTime);
            deleteAkunSettingReportKeuanganKonsol.setLastUpdateWho(userLogin);
            deleteAkunSettingReportKeuanganKonsol.setAction("U");
            deleteAkunSettingReportKeuanganKonsol.setFlag("N");

            akunSettingReportKeuanganKonsolBoProxy.saveDelete(deleteAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[AkunSettingReportKeuanganKonsolAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveAdd] start process >>>");

        try {
            AkunSettingReportKeuanganKonsol trans = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            trans.setCreatedWho(userLogin);
            trans.setLastUpdate(updateTime);
            trans.setCreatedDate(updateTime);
            trans.setLastUpdateWho(userLogin);
            trans.setAction("C");
            trans.setFlag("Y");

            akunSettingReportKeuanganKonsolBoProxy.saveAdd(trans);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.search] start process >>>");
        AkunSettingReportKeuanganKonsol searchAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
        List<AkunSettingReportKeuanganKonsol> listOfsearchAkunSettingReportKeuanganKonsol = new ArrayList();
        try {
            listOfsearchAkunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(searchAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchAkunSettingReportKeuanganKonsol);

        logger.info("[AkunSettingReportKeuanganKonsolAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[AkunSettingReportKeuanganKonsolAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboAkunSettingReportKeuanganKonsol() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] start process >>>");

        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        List<AkunSettingReportKeuanganKonsol> listOfSearchAkunSettingReportKeuanganKonsol = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchAkunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "transBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] Error when saving error,", e1);
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfSearchAkunSettingReportKeuanganKonsol.addAll(listOfSearchAkunSettingReportKeuanganKonsol);
        logger.info("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] end process <<<");

        return SUCCESS;
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
