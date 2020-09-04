package com.neurix.akuntansi.master.tipeLaporan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeLaporan.bo.TipeLaporanBo;
import com.neurix.akuntansi.master.tipeLaporan.model.TipeLaporan;
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

public class TipeLaporanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipeLaporanAction.class);
    private TipeLaporanBo tipeLaporanBoProxy;
    private TipeLaporan tipeLaporan;
    private List<TipeLaporan> listOfComboTipeLaporan = new ArrayList<TipeLaporan>();

    public List<TipeLaporan> getListOfComboTipeLaporan() {
        return listOfComboTipeLaporan;
    }

    public void setListOfComboTipeLaporan(List<TipeLaporan> listOfComboTipeLaporan) {
        this.listOfComboTipeLaporan = listOfComboTipeLaporan;
    }


    public TipeLaporanBo getTipeLaporanBoProxy() {
        return tipeLaporanBoProxy;
    }

    public void setTipeLaporanBoProxy(TipeLaporanBo tipeLaporanBoProxy) {
        this.tipeLaporanBoProxy = tipeLaporanBoProxy;
    }

    public TipeLaporan getTipeLaporan() {
        return tipeLaporan;
    }

    public void setTipeLaporan(TipeLaporan tipeLaporan) {
        this.tipeLaporan = tipeLaporan;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeLaporanAction.logger = logger;
    }


    public String initComboTipeLaporan() {
        logger.info("[TipeLaporanAction.initComboTipeLaporan] start process >>>");

        TipeLaporan search = new TipeLaporan();
        List<TipeLaporan> tipeLaporanList = new ArrayList();
        search.setFlag("Y");
        try {
            tipeLaporanList = tipeLaporanBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLaporanAction.initComboTipeLaporan] Error when saving error,", e1);
            }
            logger.error("[TipeLaporanAction.initComboTipeLaporan] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboTipeLaporan.addAll(tipeLaporanList);
        logger.info("[TipeLaporanAction.initComboTipeLaporan] end process <<<");
        return SUCCESS;
    }

    public TipeLaporan init(String kode, String flag){
        logger.info("[TipeLaporanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeLaporan> listOfResult = (List<TipeLaporan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeLaporan tipeLaporan: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeLaporan.getTipeLaporanId()) && flag.equalsIgnoreCase(tipeLaporan.getFlag())){
                        setTipeLaporan(tipeLaporan);
                        break;
                    }
                }
            } else {
                setTipeLaporan(new TipeLaporan());
            }

            logger.info("[TipeLaporanAction.init] end process >>>");
        }
        return getTipeLaporan();
    }

    @Override
    public String add() {
        logger.info("[TipeLaporanAction.add] start process >>>");
        TipeLaporan addTipeLaporan = new TipeLaporan();
        setTipeLaporan(addTipeLaporan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipeLaporanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipeLaporanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeLaporan editTipeLaporan = new TipeLaporan();

        if(itemFlag != null){
            try {
                editTipeLaporan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.getTipeLaporanByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeLaporanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipeLaporanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeLaporan != null) {
                setTipeLaporan(editTipeLaporan);
            } else {
                editTipeLaporan.setFlag(itemFlag);
                editTipeLaporan.setTipeLaporanId(itemId);
                setTipeLaporan(editTipeLaporan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeLaporan.setTipeLaporanId(itemId);
            editTipeLaporan.setFlag(getFlag());
            setTipeLaporan(editTipeLaporan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[TipeLaporanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipeLaporanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeLaporan deleteTipeLaporan = new TipeLaporan();

        if (itemFlag != null ) {

            try {
                deleteTipeLaporan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeLaporanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeLaporanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeLaporan != null) {
                setTipeLaporan(deleteTipeLaporan);

            } else {
                deleteTipeLaporan.setTipeLaporanId(itemId);
                deleteTipeLaporan.setFlag(itemFlag);
                setTipeLaporan(deleteTipeLaporan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeLaporan.setTipeLaporanId(itemId);
            deleteTipeLaporan.setFlag(itemFlag);
            setTipeLaporan(deleteTipeLaporan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }


        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[TipeLaporanAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeLaporan deleteTipeLaporan = new TipeLaporan();

        if (itemFlag != null ) {
            try {
                deleteTipeLaporan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeLaporanAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeLaporanAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeLaporan != null) {
                setTipeLaporan(deleteTipeLaporan);

            } else {
                deleteTipeLaporan.setTipeLaporanId(itemId);
                deleteTipeLaporan.setFlag(itemFlag);
                setTipeLaporan(deleteTipeLaporan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeLaporan.setTipeLaporanId(itemId);
            deleteTipeLaporan.setFlag(itemFlag);
            setTipeLaporan(deleteTipeLaporan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipeLaporanAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[TipeLaporanAction.saveEdit] start process >>>");
        try {
            TipeLaporan editTipeLaporan = getTipeLaporan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipeLaporan.setLastUpdateWho(userLogin);
            editTipeLaporan.setLastUpdate(updateTime);
            editTipeLaporan.setAction("U");
            editTipeLaporan.setFlag("Y");

            tipeLaporanBoProxy.saveEdit(editTipeLaporan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLaporanAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipeLaporanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TipeLaporanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipeLaporanAction.saveDelete] start process >>>");
        try {
            TipeLaporan deleteTipeLaporan = getTipeLaporan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipeLaporan.setLastUpdate(updateTime);
            deleteTipeLaporan.setLastUpdateWho(userLogin);
            deleteTipeLaporan.setAction("U");
            deleteTipeLaporan.setFlag("N");

            tipeLaporanBoProxy.saveDelete(deleteTipeLaporan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLaporanAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipeLaporanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[TipeLaporanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipeLaporanAction.saveAdd] start process >>>");

        try {
            TipeLaporan tipeLaporan = getTipeLaporan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeLaporan.setCreatedWho(userLogin);
            tipeLaporan.setLastUpdate(updateTime);
            tipeLaporan.setCreatedDate(updateTime);
            tipeLaporan.setLastUpdateWho(userLogin);
            tipeLaporan.setAction("C");
            tipeLaporan.setFlag("Y");

            tipeLaporanBoProxy.saveAdd(tipeLaporan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLaporanAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[TipeLaporanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
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
        logger.info("[TipeLaporanAction.search] start process >>>");
        TipeLaporan searchTipeLaporan = getTipeLaporan();
        List<TipeLaporan> listOfsearchTipeLaporan = new ArrayList();
        try {
            listOfsearchTipeLaporan = tipeLaporanBoProxy.getByCriteria(searchTipeLaporan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeLaporanBoProxy.saveErrorMessage(e.getMessage(), "TipeLaporanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeLaporanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeLaporanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTipeLaporan);

        logger.info("[TipeLaporanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeLaporanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipeLaporanAction.initForm] end process >>>");
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
