package com.neurix.akuntansi.master.tipeRekening.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeRekening.bo.TipeRekeningBo;
import com.neurix.akuntansi.master.tipeRekening.model.TipeRekening;
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

public class TipeRekeningAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipeRekeningAction.class);
    private TipeRekeningBo tipeRekeningBoProxy;
    private TipeRekening tipeRekening;
    private List<TipeRekening> listOfComboTipeRekening = new ArrayList<TipeRekening>();

    public List<TipeRekening> getListOfComboTipeRekening() {
        return listOfComboTipeRekening;
    }

    public void setListOfComboTipeRekening(List<TipeRekening> listOfComboTipeRekening) {
        this.listOfComboTipeRekening = listOfComboTipeRekening;
    }


    public TipeRekeningBo getTipeRekeningBoProxy() {
        return tipeRekeningBoProxy;
    }

    public void setTipeRekeningBoProxy(TipeRekeningBo tipeRekeningBoProxy) {
        this.tipeRekeningBoProxy = tipeRekeningBoProxy;
    }

    public TipeRekening getTipeRekening() {
        return tipeRekening;
    }

    public void setTipeRekening(TipeRekening tipeRekening) {
        this.tipeRekening = tipeRekening;
    }

    private List<TipeRekening> initComboTipeRekening;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeRekeningAction.logger = logger;
    }


    public List<TipeRekening> getInitComboTipeRekening() {
        return initComboTipeRekening;
    }

    public void setInitComboTipeRekening(List<TipeRekening> initComboTipeRekening) {
        this.initComboTipeRekening = initComboTipeRekening;
    }

    public TipeRekening init(String kode, String flag){
        logger.info("[TipeRekeningAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeRekening> listOfResult = (List<TipeRekening>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeRekening tipeRekening: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeRekening.getTipeRekeningId()) && flag.equalsIgnoreCase(tipeRekening.getFlag())){
                        setTipeRekening(tipeRekening);
                        break;
                    }
                }
            } else {
                setTipeRekening(new TipeRekening());
            }

            logger.info("[TipeRekeningAction.init] end process >>>");
        }
        return getTipeRekening();
    }

    @Override
    public String add() {
        logger.info("[TipeRekeningAction.add] start process >>>");
        TipeRekening addTipeRekening = new TipeRekening();
        setTipeRekening(addTipeRekening);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipeRekeningAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipeRekeningAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeRekening editTipeRekening = new TipeRekening();

        if(itemFlag != null){
            try {
                editTipeRekening = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.getTipeRekeningByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeRekeningAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipeRekeningAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeRekening != null) {
                setTipeRekening(editTipeRekening);
            } else {
                editTipeRekening.setFlag(itemFlag);
                editTipeRekening.setTipeRekeningId(itemId);
                setTipeRekening(editTipeRekening);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeRekening.setTipeRekeningId(itemId);
            editTipeRekening.setFlag(getFlag());
            setTipeRekening(editTipeRekening);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[TipeRekeningAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipeRekeningAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeRekening deleteTipeRekening = new TipeRekening();

        if (itemFlag != null ) {

            try {
                deleteTipeRekening = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeRekeningAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeRekeningAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeRekening != null) {
                setTipeRekening(deleteTipeRekening);

            } else {
                deleteTipeRekening.setTipeRekeningId(itemId);
                deleteTipeRekening.setFlag(itemFlag);
                setTipeRekening(deleteTipeRekening);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeRekening.setTipeRekeningId(itemId);
            deleteTipeRekening.setFlag(itemFlag);
            setTipeRekening(deleteTipeRekening);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipeRekeningAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[TipeRekeningAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeRekening deleteTipeRekening = new TipeRekening();

        if (itemFlag != null ) {
            try {
                deleteTipeRekening = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeRekeningAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeRekeningAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeRekening != null) {
                setTipeRekening(deleteTipeRekening);

            } else {
                deleteTipeRekening.setTipeRekeningId(itemId);
                deleteTipeRekening.setFlag(itemFlag);
                setTipeRekening(deleteTipeRekening);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeRekening.setTipeRekeningId(itemId);
            deleteTipeRekening.setFlag(itemFlag);
            setTipeRekening(deleteTipeRekening);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[TipeRekeningAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[TipeRekeningAction.saveEdit] start process >>>");
        try {
            TipeRekening editTipeRekening = getTipeRekening();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipeRekening.setLastUpdateWho(userLogin);
            editTipeRekening.setLastUpdate(updateTime);
            editTipeRekening.setAction("U");
            editTipeRekening.setFlag("Y");

            tipeRekeningBoProxy.saveEdit(editTipeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipeRekeningAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeRekeningAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeRekeningAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipeRekeningAction.saveDelete] start process >>>");
        try {
            TipeRekening deleteTipeRekening = getTipeRekening();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipeRekening.setLastUpdate(updateTime);
            deleteTipeRekening.setLastUpdateWho(userLogin);
            deleteTipeRekening.setAction("U");
            deleteTipeRekening.setFlag("N");

            tipeRekeningBoProxy.saveDelete(deleteTipeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipeRekeningAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeRekeningAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeRekeningAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipeRekeningAction.saveAdd] start process >>>");

        try {
            TipeRekening tipeRekening = getTipeRekening();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeRekening.setCreatedWho(userLogin);
            tipeRekening.setLastUpdate(updateTime);
            tipeRekening.setCreatedDate(updateTime);
            tipeRekening.setLastUpdateWho(userLogin);
            tipeRekening.setAction("C");
            tipeRekening.setFlag("Y");

            tipeRekeningBoProxy.saveAdd(tipeRekening);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[TipeRekeningAction.search] start process >>>");
        TipeRekening searchTipeRekening = getTipeRekening();
        List<TipeRekening> listOfsearchTipeRekening = new ArrayList();
        try {
            listOfsearchTipeRekening = tipeRekeningBoProxy.getByCriteria(searchTipeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "TipeRekeningBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeRekeningAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeRekeningAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTipeRekening);

        logger.info("[TipeRekeningAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeRekeningAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[TipeRekeningAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboTipeRekening() {
        logger.info("[TipeRekeningAction.initComboTipeRekening] start process >>>");

        TipeRekening search = new TipeRekening();
        List<TipeRekening> listOfSearchTipeRekening = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchTipeRekening = tipeRekeningBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeRekeningBoProxy.saveErrorMessage(e.getMessage(), "tipeRekeningBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeRekeningAction.initComboTipeRekening] Error when saving error,", e1);
            }
            logger.error("[TipeRekeningAction.initComboTipeRekening] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboTipeRekening.addAll(listOfSearchTipeRekening);
        logger.info("[TipeRekeningAction.initComboTipeRekening] end process <<<");

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
