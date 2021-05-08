package com.neurix.hris.master.masaTanam.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.masaTanam.bo.MasaTanamBo;
import com.neurix.hris.master.masaTanam.model.MasaTanam;
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

public class MasaTanamAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(MasaTanamAction.class);
    private MasaTanamBo masaTanamBoProxy;
    private MasaTanam masaTanam;

    private List<MasaTanam> listComboMasaTanam = new ArrayList<MasaTanam>();

    public List<MasaTanam> getListComboMasaTanam() {
        return listComboMasaTanam;
    }

    public void setListComboMasaTanam(List<MasaTanam> listComboMasaTanam) {
        this.listComboMasaTanam = listComboMasaTanam;
    }

    public MasaTanamBo getMasaTanamBoProxy() {
        return masaTanamBoProxy;
    }

    public void setMasaTanamBoProxy(MasaTanamBo masaTanamBoProxy) {
        this.masaTanamBoProxy = masaTanamBoProxy;
    }

    public MasaTanam getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(MasaTanam masaTanam) {
        this.masaTanam = masaTanam;
    }

    private List<MasaTanam> initComboMasaTanam;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasaTanamAction.logger = logger;
    }


    public List<MasaTanam> getInitComboMasaTanam() {
        return initComboMasaTanam;
    }

    public void setInitComboMasaTanam(List<MasaTanam> initComboMasaTanam) {
        this.initComboMasaTanam = initComboMasaTanam;
    }

    public MasaTanam init(String kode, String flag){
        logger.info("[MasaTanamAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MasaTanam> listOfResult = (List<MasaTanam>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MasaTanam masaTanam: listOfResult) {
                    if(kode.equalsIgnoreCase(masaTanam.getMasaTanamId()) && flag.equalsIgnoreCase(masaTanam.getFlag())){
                        setMasaTanam(masaTanam);
                        break;
                    }
                }
            } else {
                setMasaTanam(new MasaTanam());
            }

            logger.info("[MasaTanamAction.init] end process >>>");
        }
        return getMasaTanam();
    }

    @Override
    public String add() {
        logger.info("[MasaTanamAction.add] start process >>>");
        MasaTanam addMasaTanam = new MasaTanam();
        setMasaTanam(addMasaTanam);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MasaTanamAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MasaTanamAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MasaTanam editMasaTanam = new MasaTanam();

        if(itemFlag != null){
            try {
                editMasaTanam = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.getMasaTanamByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MasaTanamAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MasaTanamAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMasaTanam != null) {
                setMasaTanam(editMasaTanam);
            } else {
                editMasaTanam.setFlag(itemFlag);
                editMasaTanam.setMasaTanamId(itemId);
                setMasaTanam(editMasaTanam);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editMasaTanam.setMasaTanamId(itemId);
            editMasaTanam.setFlag(getFlag());
            setMasaTanam(editMasaTanam);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[MasaTanamAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MasaTanamAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MasaTanam deleteMasaTanam = new MasaTanam();

        if (itemFlag != null ) {

            try {
                deleteMasaTanam = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MasaTanamAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MasaTanamAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMasaTanam != null) {
                setMasaTanam(deleteMasaTanam);

            } else {
                deleteMasaTanam.setMasaTanamId(itemId);
                deleteMasaTanam.setFlag(itemFlag);
                setMasaTanam(deleteMasaTanam);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMasaTanam.setMasaTanamId(itemId);
            deleteMasaTanam.setFlag(itemFlag);
            setMasaTanam(deleteMasaTanam);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[MasaTanamAction.delete] end process <<<");

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

    public String saveEdit(){
        logger.info("[MasaTanamAction.saveEdit] start process >>>");
        try {

            MasaTanam editMasaTanam = getMasaTanam();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMasaTanam.setLastUpdateWho(userLogin);
            editMasaTanam.setLastUpdate(updateTime);
            editMasaTanam.setAction("U");
            editMasaTanam.setFlag("Y");

            masaTanamBoProxy.saveEdit(editMasaTanam);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MasaTanamAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaTanamAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MasaTanamAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MasaTanamAction.saveDelete] start process >>>");
        try {

            MasaTanam deleteMasaTanam = getMasaTanam();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMasaTanam.setLastUpdate(updateTime);
            deleteMasaTanam.setLastUpdateWho(userLogin);
            deleteMasaTanam.setAction("U");
            deleteMasaTanam.setFlag("N");

            masaTanamBoProxy.saveDelete(deleteMasaTanam);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MasaTanamAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaTanamAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MasaTanamAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[MasaTanamAction.saveAdd] start process >>>");

        try {
            MasaTanam masaTanam = getMasaTanam();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            masaTanam.setCreatedWho(userLogin);
            masaTanam.setLastUpdate(updateTime);
            masaTanam.setCreatedDate(updateTime);
            masaTanam.setLastUpdateWho(userLogin);
            masaTanam.setAction("C");
            masaTanam.setFlag("Y");

            masaTanamBoProxy.saveAdd(masaTanam);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[MasaTanamAction.search] start process >>>");

        MasaTanam searchMasaTanam = getMasaTanam();
        List<MasaTanam> listOfsearchMasaTanam = new ArrayList();

        try {
            listOfsearchMasaTanam = masaTanamBoProxy.getByCriteria(searchMasaTanam);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaTanamAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaTanamAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMasaTanam);

        logger.info("[MasaTanamAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchMasaTanam() {
        logger.info("[MasaTanamAction.search] start process >>>");

        MasaTanam searchMasaTanam = new MasaTanam();
        searchMasaTanam.setFlag("Y");
        List<MasaTanam> listOfsearchMasaTanam = new ArrayList();

        try {
            listOfsearchMasaTanam = masaTanamBoProxy.getByCriteria(searchMasaTanam);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaTanamAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaTanamAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboMasaTanam.addAll(listOfsearchMasaTanam);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MasaTanamAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[MasaTanamAction.initForm] end process >>>");
        return INPUT;
    }

    public String initMasaTanam() {
        logger.info("[MasaTanamAction.search] start process >>>");

        MasaTanam searchMasaTanam = new MasaTanam();
        searchMasaTanam.setFlag("Y");
        List<MasaTanam> listOfsearchMasaTanam = new ArrayList();

        try {
            listOfsearchMasaTanam = masaTanamBoProxy.getByCriteria(searchMasaTanam);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masaTanamBoProxy.saveErrorMessage(e.getMessage(), "MasaTanamBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaTanamAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaTanamAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMasaTanam");
        session.setAttribute("listOfResultMasaTanam", listOfsearchMasaTanam);

        logger.info("[MasaTanamAction.search] end process <<<");

        return "";
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
