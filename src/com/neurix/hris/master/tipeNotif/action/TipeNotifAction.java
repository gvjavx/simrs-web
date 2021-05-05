package com.neurix.hris.master.tipeNotif.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipeNotif.bo.TipeNotifBo;
import com.neurix.hris.master.tipeNotif.model.TipeNotif;
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

public class TipeNotifAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(TipeNotifAction.class);
    private TipeNotifBo tipeNotifBoProxy;
    private TipeNotif tipeNotif;
    private List<TipeNotif> listOfComboTipeNotif = new ArrayList<TipeNotif>();

    public List<TipeNotif> getListOfComboTipeNotif() {
        return listOfComboTipeNotif;
    }

    public void setListOfComboTipeNotif(List<TipeNotif> listOfComboTipeNotif) {
        this.listOfComboTipeNotif = listOfComboTipeNotif;
    }

    public TipeNotifBo getTipeNotifBoProxy() {
        return tipeNotifBoProxy;
    }

    public void setTipeNotifBoProxy(TipeNotifBo tipeNotifBoProxy) {
        this.tipeNotifBoProxy = tipeNotifBoProxy;
    }

    public TipeNotif getTipeNotif() {
        return tipeNotif;
    }

    public void setTipeNotif(TipeNotif tipeNotif) {
        this.tipeNotif = tipeNotif;
    }

    private List<TipeNotif> initComboTipeNotif;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeNotifAction.logger = logger;
    }


    public List<TipeNotif> getInitComboTipeNotif() {
        return initComboTipeNotif;
    }

    public void setInitComboTipeNotif(List<TipeNotif> initComboTipeNotif) {
        this.initComboTipeNotif = initComboTipeNotif;
    }

    public TipeNotif init(String kode, String flag){
        logger.info("[TipeNotifAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeNotif> listOfResult = (List<TipeNotif>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeNotif tipeNotif: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeNotif.getTipeNotifId()) && flag.equalsIgnoreCase(tipeNotif.getFlag())){
                        setTipeNotif(tipeNotif);
                        break;
                    }
                }
            } else {
                setTipeNotif(new TipeNotif());
            }

            logger.info("[TipeNotifAction.init] end process >>>");
        }
        return getTipeNotif();
    }

    @Override
    public String add() {
        logger.info("[TipeNotifAction.add] start process >>>");
        TipeNotif addTipeNotif = new TipeNotif();
        setTipeNotif(addTipeNotif);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipeNotifAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipeNotifAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeNotif editTipeNotif = new TipeNotif();

        if(itemFlag != null){
            try {
                editTipeNotif = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.getTipeNotifByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeNotifAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipeNotifAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeNotif != null) {
                setTipeNotif(editTipeNotif);
            } else {
                editTipeNotif.setFlag(itemFlag);
                editTipeNotif.setTipeNotifId(itemId);
                setTipeNotif(editTipeNotif);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeNotif.setTipeNotifId(itemId);
            editTipeNotif.setFlag(getFlag());
            setTipeNotif(editTipeNotif);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TipeNotifAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipeNotifAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeNotif deleteTipeNotif = new TipeNotif();

        if (itemFlag != null ) {

            try {
                deleteTipeNotif = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeNotifAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeNotifAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeNotif != null) {
                setTipeNotif(deleteTipeNotif);

            } else {
                deleteTipeNotif.setTipeNotifId(itemId);
                deleteTipeNotif.setFlag(itemFlag);
                setTipeNotif(deleteTipeNotif);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeNotif.setTipeNotifId(itemId);
            deleteTipeNotif.setFlag(itemFlag);
            setTipeNotif(deleteTipeNotif);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipeNotifAction.delete] end process <<<");

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
        logger.info("[TipeNotifAction.saveEdit] start process >>>");
        try {

            TipeNotif editTipeNotif = getTipeNotif();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipeNotif.setLastUpdateWho(userLogin);
            editTipeNotif.setLastUpdate(updateTime);
            editTipeNotif.setAction("U");
            editTipeNotif.setFlag("Y");

            tipeNotifBoProxy.saveEdit(editTipeNotif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipeNotifAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeNotifAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeNotifAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipeNotifAction.saveDelete] start process >>>");
        try {

            TipeNotif deleteTipeNotif = getTipeNotif();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipeNotif.setLastUpdate(updateTime);
            deleteTipeNotif.setLastUpdateWho(userLogin);
            deleteTipeNotif.setAction("U");
            deleteTipeNotif.setFlag("N");

            tipeNotifBoProxy.saveDelete(deleteTipeNotif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipeNotifAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeNotifAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeNotifAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipeNotifAction.saveAdd] start process >>>");

        try {
            TipeNotif tipeNotif = getTipeNotif();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            tipeNotif.setCreatedWho(userLogin);
            tipeNotif.setLastUpdate(updateTime);
            tipeNotif.setCreatedDate(updateTime);
            tipeNotif.setLastUpdateWho(userLogin);
            tipeNotif.setAction("C");
            tipeNotif.setFlag("Y");

            tipeNotifBoProxy.saveAdd(tipeNotif);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[TipeNotifAction.search] start process >>>");

        TipeNotif searchTipeNotif = getTipeNotif();
        List<TipeNotif> listOfsearchTipeNotif = new ArrayList();

        try {
            listOfsearchTipeNotif = tipeNotifBoProxy.getByCriteria(searchTipeNotif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeNotifAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeNotifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTipeNotif);

        logger.info("[TipeNotifAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeNotifAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipeNotifAction.initForm] end process >>>");
        return INPUT;
    }

    public String initComboTipeNotif() {
        logger.info("[IjinAction.search] start process >>>");

        TipeNotif searchTipeNotif = new TipeNotif();
        List<TipeNotif> listOfSearchTipeNotif = new ArrayList();
        searchTipeNotif.setFlag("Y");
        try {
            listOfSearchTipeNotif = tipeNotifBoProxy.getByCriteria(searchTipeNotif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.search] Error when saving error,", e1);
            }
            logger.error("[IjinAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboTipeNotif.addAll(listOfSearchTipeNotif);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public String initTipeNotif() {
        logger.info("[TipeNotifAction.search] start process >>>");

        TipeNotif searchTipeNotif = new TipeNotif();
        searchTipeNotif.setFlag("Y");
        List<TipeNotif> listOfsearchTipeNotif = new ArrayList();

        try {
            listOfsearchTipeNotif = tipeNotifBoProxy.getByCriteria(searchTipeNotif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeNotifBoProxy.saveErrorMessage(e.getMessage(), "TipeNotifBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeNotifAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeNotifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultTipeNotif");
        session.setAttribute("listOfResultTipeNotif", listOfsearchTipeNotif);

        logger.info("[TipeNotifAction.search] end process <<<");

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
