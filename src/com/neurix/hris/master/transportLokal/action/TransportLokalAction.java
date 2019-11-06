package com.neurix.hris.master.transportLokal.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.transportLokal.bo.TransportLokalBo;
import com.neurix.hris.master.transportLokal.model.TransportLokal;
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

public class TransportLokalAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(TransportLokalAction.class);
    private TransportLokalBo transportLokalBoProxy;
    private TransportLokal transportLokal;

    public TransportLokalBo getTransportLokalBoProxy() {
        return transportLokalBoProxy;
    }

    public void setTransportLokalBoProxy(TransportLokalBo transportLokalBoProxy) {
        this.transportLokalBoProxy = transportLokalBoProxy;
    }

    public TransportLokal getTransportLokal() {
        return transportLokal;
    }

    public void setTransportLokal(TransportLokal transportLokal) {
        this.transportLokal = transportLokal;
    }

    private List<TransportLokal> initComboTransportLokal;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TransportLokalAction.logger = logger;
    }


    public List<TransportLokal> getInitComboTransportLokal() {
        return initComboTransportLokal;
    }

    public void setInitComboTransportLokal(List<TransportLokal> initComboTransportLokal) {
        this.initComboTransportLokal = initComboTransportLokal;
    }

    public TransportLokal init(String kode, String flag){
        logger.info("[TransportLokalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TransportLokal> listOfResult = (List<TransportLokal>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TransportLokal transportLokal: listOfResult) {
                    if(kode.equalsIgnoreCase(transportLokal.getTransportLokalId()) && flag.equalsIgnoreCase(transportLokal.getFlag())){
                        setTransportLokal(transportLokal);
                        break;
                    }
                }
            } else {
                setTransportLokal(new TransportLokal());
            }

            logger.info("[TransportLokalAction.init] end process >>>");
        }
        return getTransportLokal();
    }

    @Override
    public String add() {
        logger.info("[TransportLokalAction.add] start process >>>");
        TransportLokal addTransportLokal = new TransportLokal();
        setTransportLokal(addTransportLokal);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TransportLokalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TransportLokalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TransportLokal editTransportLokal = new TransportLokal();

        if(itemFlag != null){
            try {
                editTransportLokal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.getTransportLokalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TransportLokalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TransportLokalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTransportLokal != null) {
                setTransportLokal(editTransportLokal);
            } else {
                editTransportLokal.setFlag(itemFlag);
                editTransportLokal.setTransportLokalId(itemId);
                setTransportLokal(editTransportLokal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTransportLokal.setTransportLokalId(itemId);
            editTransportLokal.setFlag(getFlag());
            setTransportLokal(editTransportLokal);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TransportLokalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TransportLokalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TransportLokal deleteTransportLokal = new TransportLokal();

        if (itemFlag != null ) {

            try {
                deleteTransportLokal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TransportLokalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TransportLokalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTransportLokal != null) {
                setTransportLokal(deleteTransportLokal);

            } else {
                deleteTransportLokal.setTransportLokalId(itemId);
                deleteTransportLokal.setFlag(itemFlag);
                setTransportLokal(deleteTransportLokal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTransportLokal.setTransportLokalId(itemId);
            deleteTransportLokal.setFlag(itemFlag);
            setTransportLokal(deleteTransportLokal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TransportLokalAction.delete] end process <<<");

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
        logger.info("[TransportLokalAction.saveEdit] start process >>>");
        try {

            TransportLokal editTransportLokal = getTransportLokal();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTransportLokal.setLastUpdateWho(userLogin);
            editTransportLokal.setLastUpdate(updateTime);
            editTransportLokal.setAction("U");
            editTransportLokal.setFlag("Y");

            transportLokalBoProxy.saveEdit(editTransportLokal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TransportLokalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransportLokalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TransportLokalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TransportLokalAction.saveDelete] start process >>>");
        try {

            TransportLokal deleteTransportLokal = getTransportLokal();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTransportLokal.setLastUpdate(updateTime);
            deleteTransportLokal.setLastUpdateWho(userLogin);
            deleteTransportLokal.setAction("U");
            deleteTransportLokal.setFlag("N");

            transportLokalBoProxy.saveDelete(deleteTransportLokal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TransportLokalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransportLokalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TransportLokalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TransportLokalAction.saveAdd] start process >>>");

        try {
            TransportLokal transportLokal = getTransportLokal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            transportLokal.setCreatedWho(userLogin);
            transportLokal.setLastUpdate(updateTime);
            transportLokal.setCreatedDate(updateTime);
            transportLokal.setLastUpdateWho(userLogin);
            transportLokal.setAction("C");
            transportLokal.setFlag("Y");

            transportLokalBoProxy.saveAdd(transportLokal);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[TransportLokalAction.search] start process >>>");

        TransportLokal searchTransportLokal = getTransportLokal();
        List<TransportLokal> listOfsearchTransportLokal = new ArrayList();

        try {
            listOfsearchTransportLokal = transportLokalBoProxy.getByCriteria(searchTransportLokal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TransportLokalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransportLokalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTransportLokal);

        logger.info("[TransportLokalAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TransportLokalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TransportLokalAction.initForm] end process >>>");
        return INPUT;
    }

    public String initTransportLokal() {
        logger.info("[TransportLokalAction.search] start process >>>");

        TransportLokal searchTransportLokal = new TransportLokal();
        searchTransportLokal.setFlag("Y");
        List<TransportLokal> listOfsearchTransportLokal = new ArrayList();

        try {
            listOfsearchTransportLokal = transportLokalBoProxy.getByCriteria(searchTransportLokal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = transportLokalBoProxy.saveErrorMessage(e.getMessage(), "TransportLokalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TransportLokalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TransportLokalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultTransportLokal");
        session.setAttribute("listOfResultTransportLokal", listOfsearchTransportLokal);

        logger.info("[TransportLokalAction.search] end process <<<");

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
