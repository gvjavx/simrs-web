package com.neurix.hris.master.tipePayroll.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipePayroll.bo.TipePayrollBo;
import com.neurix.hris.master.tipePayroll.model.TipePayroll;
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

public class TipePayrollAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipePayrollAction.class);
    private TipePayrollBo tipePayrollBoProxy;
    private TipePayroll tipePayroll;
    private List<TipePayroll> listOfComboTipePayroll = new ArrayList<TipePayroll>();

    public List<TipePayroll> getListOfComboTipePayroll() {
        return listOfComboTipePayroll;
    }

    public void setListOfComboTipePayroll(List<TipePayroll> listOfComboTipePayroll) {
        this.listOfComboTipePayroll = listOfComboTipePayroll;
    }


    public TipePayrollBo getTipePayrollBoProxy() {
        return tipePayrollBoProxy;
    }

    public void setTipePayrollBoProxy(TipePayrollBo tipePayrollBoProxy) {
        this.tipePayrollBoProxy = tipePayrollBoProxy;
    }

    public TipePayroll getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(TipePayroll tipePayroll) {
        this.tipePayroll = tipePayroll;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipePayrollAction.logger = logger;
    }


    public String initComboTipePayroll() {
        logger.info("[TipePayrollAction.initComboTipePayroll] start process >>>");

        TipePayroll search = new TipePayroll();
        List<TipePayroll> tipePayrollList = new ArrayList();
        search.setFlag("Y");
        try {
            tipePayrollList = tipePayrollBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePayrollAction.initComboTipePayroll] Error when saving error,", e1);
            }
            logger.error("[TipePayrollAction.initComboTipePayroll] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboTipePayroll.addAll(tipePayrollList);
        logger.info("[TipePayrollAction.initComboTipePayroll] end process <<<");
        return SUCCESS;
    }

    public TipePayroll init(String kode, String flag){
        logger.info("[TipePayrollAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipePayroll> listOfResult = (List<TipePayroll>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipePayroll tipePayroll: listOfResult) {
                    if(kode.equalsIgnoreCase(tipePayroll.getTipePayrollId()) && flag.equalsIgnoreCase(tipePayroll.getFlag())){
                        setTipePayroll(tipePayroll);
                        break;
                    }
                }
            } else {
                setTipePayroll(new TipePayroll());
            }

            logger.info("[TipePayrollAction.init] end process >>>");
        }
        return getTipePayroll();
    }

    @Override
    public String add() {
        logger.info("[TipePayrollAction.add] start process >>>");
        TipePayroll addTipePayroll = new TipePayroll();
        setTipePayroll(addTipePayroll);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipePayrollAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipePayrollAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipePayroll editTipePayroll = new TipePayroll();

        if(itemFlag != null){
            try {
                editTipePayroll = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.getTipePayrollByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePayrollAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePayrollAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipePayroll != null) {
                setTipePayroll(editTipePayroll);
            } else {
                editTipePayroll.setFlag(itemFlag);
                editTipePayroll.setTipePayrollId(itemId);
                setTipePayroll(editTipePayroll);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipePayroll.setTipePayrollId(itemId);
            editTipePayroll.setFlag(getFlag());
            setTipePayroll(editTipePayroll);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[TipePayrollAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipePayrollAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipePayroll deleteTipePayroll = new TipePayroll();

        if (itemFlag != null ) {

            try {
                deleteTipePayroll = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePayrollAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipePayrollAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipePayroll != null) {
                setTipePayroll(deleteTipePayroll);

            } else {
                deleteTipePayroll.setTipePayrollId(itemId);
                deleteTipePayroll.setFlag(itemFlag);
                setTipePayroll(deleteTipePayroll);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipePayroll.setTipePayrollId(itemId);
            deleteTipePayroll.setFlag(itemFlag);
            setTipePayroll(deleteTipePayroll);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }


        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[TipePayrollAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipePayroll deleteTipePayroll = new TipePayroll();

        if (itemFlag != null ) {
            try {
                deleteTipePayroll = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePayrollAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[TipePayrollAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipePayroll != null) {
                setTipePayroll(deleteTipePayroll);

            } else {
                deleteTipePayroll.setTipePayrollId(itemId);
                deleteTipePayroll.setFlag(itemFlag);
                setTipePayroll(deleteTipePayroll);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipePayroll.setTipePayrollId(itemId);
            deleteTipePayroll.setFlag(itemFlag);
            setTipePayroll(deleteTipePayroll);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipePayrollAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[TipePayrollAction.saveEdit] start process >>>");
        try {
            TipePayroll editTipePayroll = getTipePayroll();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipePayroll.setLastUpdateWho(userLogin);
            editTipePayroll.setLastUpdate(updateTime);
            editTipePayroll.setAction("U");
            editTipePayroll.setFlag("Y");

            tipePayrollBoProxy.saveEdit(editTipePayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipePayrollAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipePayrollAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TipePayrollAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipePayrollAction.saveDelete] start process >>>");
        try {
            TipePayroll deleteTipePayroll = getTipePayroll();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipePayroll.setLastUpdate(updateTime);
            deleteTipePayroll.setLastUpdateWho(userLogin);
            deleteTipePayroll.setAction("U");
            deleteTipePayroll.setFlag("N");

            tipePayrollBoProxy.saveDelete(deleteTipePayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipePayrollAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipePayrollAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TipePayrollAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipePayrollAction.saveAdd] start process >>>");

        try {
            TipePayroll tipePayroll = getTipePayroll();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipePayroll.setCreatedWho(userLogin);
            tipePayroll.setLastUpdate(updateTime);
            tipePayroll.setCreatedDate(updateTime);
            tipePayroll.setLastUpdateWho(userLogin);
            tipePayroll.setAction("C");
            tipePayroll.setFlag("Y");

            tipePayrollBoProxy.saveAdd(tipePayroll);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[TipePayrollAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipePayrollAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[TipePayrollAction.search] start process >>>");
        TipePayroll searchTipePayroll = getTipePayroll();
        List<TipePayroll> listOfsearchTipePayroll = new ArrayList();
        try {
            listOfsearchTipePayroll = tipePayrollBoProxy.getByCriteria(searchTipePayroll);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipePayrollBoProxy.saveErrorMessage(e.getMessage(), "TipePayrollBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePayrollAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePayrollAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTipePayroll);

        logger.info("[TipePayrollAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipePayrollAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[TipePayrollAction.initForm] end process >>>");
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
