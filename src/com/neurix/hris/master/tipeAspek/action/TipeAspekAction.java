package com.neurix.hris.master.tipeAspek.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipeAspek.bo.TipeAspekBo;
import com.neurix.hris.master.tipeAspek.model.TipeAspek;
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

public class TipeAspekAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(TipeAspekAction.class);
    private TipeAspekBo tipeAspekBoProxy;
    private TipeAspek tipeAspek;

    private List<TipeAspek> tipeAspekList = new ArrayList<TipeAspek>();

    public List<TipeAspek> getTipeAspekList() {
        return tipeAspekList;
    }

    public void setTipeAspekList(List<TipeAspek> tipeAspekList) {
        this.tipeAspekList = tipeAspekList;
    }

    public TipeAspekBo getTipeAspekBoProxy() {
        return tipeAspekBoProxy;
    }

    public void setTipeAspekBoProxy(TipeAspekBo tipeAspekBoProxy) {
        this.tipeAspekBoProxy = tipeAspekBoProxy;
    }

    public TipeAspek getTipeAspek() {
        return tipeAspek;
    }

    public void setTipeAspek(TipeAspek tipeAspek) {
        this.tipeAspek = tipeAspek;
    }

    private List<TipeAspek> initComboTipeAspek;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeAspekAction.logger = logger;
    }


    public List<TipeAspek> getInitComboTipeAspek() {
        return initComboTipeAspek;
    }

    public void setInitComboTipeAspek(List<TipeAspek> initComboTipeAspek) {
        this.initComboTipeAspek = initComboTipeAspek;
    }

    public TipeAspek init(String kode, String flag){
        logger.info("[TipeAspekAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeAspek> listOfResult = (List<TipeAspek>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeAspek tipeAspek: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeAspek.getTipeAspekId()) && flag.equalsIgnoreCase(tipeAspek.getFlag())){
                        setTipeAspek(tipeAspek);
                        break;
                    }
                }
            } else {
                setTipeAspek(new TipeAspek());
            }

            logger.info("[TipeAspekAction.init] end process >>>");
        }
        return getTipeAspek();
    }

    public String initComboTipeAspek() {
        logger.info("[BranchAction.search] start process >>>");

        TipeAspek smkCheckList = new TipeAspek();
        List<TipeAspek> tipeAspekList1 = new ArrayList();
        smkCheckList.setFlag("Y");
        try {
            tipeAspekList1 = tipeAspekBoProxy.getByCriteria(smkCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        tipeAspekList.addAll(tipeAspekList1);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String add() {
        logger.info("[TipeAspekAction.add] start process >>>");
        TipeAspek addTipeAspek = new TipeAspek();
        setTipeAspek(addTipeAspek);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipeAspekAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipeAspekAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeAspek editTipeAspek = new TipeAspek();

        if(itemFlag != null){
            try {
                editTipeAspek = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.getTipeAspekByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeAspekAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipeAspekAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeAspek != null) {
                setTipeAspek(editTipeAspek);
            } else {
                editTipeAspek.setFlag(itemFlag);
                editTipeAspek.setTipeAspekId(itemId);
                setTipeAspek(editTipeAspek);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeAspek.setTipeAspekId(itemId);
            editTipeAspek.setFlag(getFlag());
            setTipeAspek(editTipeAspek);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TipeAspekAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipeAspekAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeAspek deleteTipeAspek = new TipeAspek();

        if (itemFlag != null ) {

            try {
                deleteTipeAspek = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeAspekAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeAspekAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeAspek != null) {
                setTipeAspek(deleteTipeAspek);

            } else {
                deleteTipeAspek.setTipeAspekId(itemId);
                deleteTipeAspek.setFlag(itemFlag);
                setTipeAspek(deleteTipeAspek);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeAspek.setTipeAspekId(itemId);
            deleteTipeAspek.setFlag(itemFlag);
            setTipeAspek(deleteTipeAspek);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipeAspekAction.delete] end process <<<");

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
        logger.info("[TipeAspekAction.saveEdit] start process >>>");
        try {

            TipeAspek editTipeAspek = getTipeAspek();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipeAspek.setLastUpdateWho(userLogin);
            editTipeAspek.setLastUpdate(updateTime);
            editTipeAspek.setAction("U");
            editTipeAspek.setFlag("Y");

            tipeAspekBoProxy.saveEdit(editTipeAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipeAspekAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeAspekAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeAspekAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipeAspekAction.saveDelete] start process >>>");
        try {

            TipeAspek deleteTipeAspek = getTipeAspek();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipeAspek.setLastUpdate(updateTime);
            deleteTipeAspek.setLastUpdateWho(userLogin);
            deleteTipeAspek.setAction("U");
            deleteTipeAspek.setFlag("N");

            tipeAspekBoProxy.saveDelete(deleteTipeAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipeAspekAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeAspekAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeAspekAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipeAspekAction.saveAdd] start process >>>");

        try {
            TipeAspek tipeAspek = getTipeAspek();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            tipeAspek.setCreatedWho(userLogin);
            tipeAspek.setLastUpdate(updateTime);
            tipeAspek.setCreatedDate(updateTime);
            tipeAspek.setLastUpdateWho(userLogin);
            tipeAspek.setAction("C");
            tipeAspek.setFlag("Y");

            tipeAspekBoProxy.saveAdd(tipeAspek);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[TipeAspekAction.search] start process >>>");

        TipeAspek searchTipeAspek = getTipeAspek();
        List<TipeAspek> listOfsearchTipeAspek = new ArrayList();

        try {
            listOfsearchTipeAspek = tipeAspekBoProxy.getByCriteria(searchTipeAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeAspekAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeAspekAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTipeAspek);

        logger.info("[TipeAspekAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeAspekAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipeAspekAction.initForm] end process >>>");
        return INPUT;
    }

    public String initTipeAspek() {
        logger.info("[TipeAspekAction.search] start process >>>");

        TipeAspek searchTipeAspek = new TipeAspek();
        searchTipeAspek.setFlag("Y");
        List<TipeAspek> listOfsearchTipeAspek = new ArrayList();

        try {
            listOfsearchTipeAspek = tipeAspekBoProxy.getByCriteria(searchTipeAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeAspekBoProxy.saveErrorMessage(e.getMessage(), "TipeAspekBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeAspekAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeAspekAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultTipeAspek");
        session.setAttribute("listOfResultTipeAspek", listOfsearchTipeAspek);

        logger.info("[TipeAspekAction.search] end process <<<");

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
