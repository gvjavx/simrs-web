package com.neurix.hris.master.tipelibur.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TipeLiburAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipeLiburAction.class);

    private TipeLibur tipeLibur;
    private TipeLiburBo tipeLiburBoProxy;
    private List<TipeLibur> listOfResultTipe = new ArrayList<TipeLibur>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeLiburAction.logger = logger;
    }

    public List<TipeLibur> getListOfResultTipe() {
        return listOfResultTipe;
    }

    public void setListOfResultTipe(List<TipeLibur> listOfResultTipe) {
        this.listOfResultTipe = listOfResultTipe;
    }

    public TipeLibur getTipeLibur() {
        return tipeLibur;
    }

    public void setTipeLibur(TipeLibur tipeLibur) {
        this.tipeLibur = tipeLibur;
    }

    public TipeLiburBo getTipeLiburBoProxy() {
        return tipeLiburBoProxy;
    }

    public void setTipeLiburBoProxy(TipeLiburBo tipeLiburBoProxy) {
        this.tipeLiburBoProxy = tipeLiburBoProxy;
    }

    public TipeLibur init(String kode, String flag){
        logger.info("[TipePegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeLibur> listOfResult = (List<TipeLibur>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeLibur tipeLibur: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeLibur.getTipeLiburId()) && flag.equalsIgnoreCase(tipeLibur.getFlag())){
                        setTipeLibur(tipeLibur);
                        break;
                    }
                }
            } else {
                setTipeLibur(new TipeLibur());
            }

            logger.info("[TipePegawaiAction.init] end process >>>");
        }
        return getTipeLibur();
    }

    @Override
    public String add() {
        logger.info("[TipePegawaiAction.add] start process >>>");
        TipeLibur addTipeLibur = new TipeLibur();
        setTipeLibur(addTipeLibur);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeLibur editTipeLibur = new TipeLibur();

        if(itemFlag != null){
            try {
                editTipeLibur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeLibur != null) {
                setTipeLibur(editTipeLibur);
            } else {
                editTipeLibur.setFlag(itemFlag);
                editTipeLibur.setTipeLiburId(itemId);
                setTipeLibur(editTipeLibur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeLibur.setTipeLiburId(itemId);
            editTipeLibur.setFlag(getFlag());
            setTipeLibur(editTipeLibur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TipePegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {

        logger.info("[TipePegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeLibur editTipeLibur = new TipeLibur();

        if(itemFlag != null){
            try {
                editTipeLibur = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "tipeLiburBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipePegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipePegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeLibur != null) {
                setTipeLibur(editTipeLibur);
            } else {
                editTipeLibur.setFlag(itemFlag);
                editTipeLibur.setTipeLiburId(itemId);
                setTipeLibur(editTipeLibur);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeLibur.setTipeLiburId(itemId);
            editTipeLibur.setFlag(getFlag());
            setTipeLibur(editTipeLibur);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[TipePegawaiAction.saveAdd] start process >>>");

        try {
            TipeLibur tipeLibur = getTipeLibur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeLibur.setCreatedWho(userLogin);
            tipeLibur.setLastUpdate(updateTime);
            tipeLibur.setCreatedDate(updateTime);
            tipeLibur.setLastUpdateWho(userLogin);
            tipeLibur.setAction("C");
            tipeLibur.setFlag("Y");


            tipeLiburBoProxy.saveAdd(tipeLibur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[TipePegawaiAction.saveEdit] start process >>>");

        try {
            TipeLibur tipeLibur = getTipeLibur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeLibur.setCreatedWho(tipeLibur.getCreatedWho());
            tipeLibur.setLastUpdate(updateTime);
            tipeLibur.setCreatedDate(tipeLibur.getCreatedDate());
            tipeLibur.setLastUpdateWho(userLogin);
            tipeLibur.setAction("U");
            tipeLibur.setFlag("Y");


            tipeLiburBoProxy.saveEdit(tipeLibur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[TipePegawaiAction.saveDelete] start process >>>");

        try {
            TipeLibur tipeLibur = getTipeLibur();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeLibur.setCreatedWho(tipeLibur.getCreatedWho());
            tipeLibur.setLastUpdate(updateTime);
            tipeLibur.setCreatedDate(tipeLibur.getCreatedDate());
            tipeLibur.setLastUpdateWho(userLogin);
            tipeLibur.setAction("U");
            tipeLibur.setFlag("N");


            tipeLiburBoProxy.saveEdit(tipeLibur);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[TipePegawaiAction.saveDelete] end process >>>");
        return "save_delete";
    }

    public String initTipeLibur() {
        logger.info("[TipeLiburAction.search] start process >>>");

        TipeLibur searchTipeLibur = new TipeLibur();
        List<TipeLibur> listOfSearchTipeLibur = new ArrayList();
        searchTipeLibur.setFlag("Y");
        try {
            listOfSearchTipeLibur = tipeLiburBoProxy.getByCriteria(searchTipeLibur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "TipeLiburBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLiburBO.search] Error when saving error,", e1);
            }
            logger.error("[TipeLiburBO.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfResultTipe.addAll(listOfSearchTipeLibur);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String search() {
        logger.info("[TipePegawaiAction.search] start process >>>");

        TipeLibur searchLibur = getTipeLibur();
        List<TipeLibur> listOfSearchTipeLibur = new ArrayList();

        try {
            listOfSearchTipeLibur = tipeLiburBoProxy.getByCriteria(searchLibur);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLiburBoProxy.saveErrorMessage(e.getMessage(), "TipePegawaiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipePegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipePegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchTipeLibur);

        logger.info("[TipePegawaiAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipePegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[TipePegawaiAction.initForm] end process >>>");
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
}
