package com.neurix.akuntansi.master.tipeJurnal.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.tipeJurnal.model.TipeJurnal;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class TipeJurnalAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TipeJurnalAction.class);
    private TipeJurnalBo tipeJurnalBoProxy;
    private TipeJurnal tipeJurnal;
    private List<TipeJurnal> listOfComboTipeJurnal = new ArrayList<TipeJurnal>();

    public List<TipeJurnal> getListOfComboTipeJurnal() {
        return listOfComboTipeJurnal;
    }

    public void setListOfComboTipeJurnal(List<TipeJurnal> listOfComboTipeJurnal) {
        this.listOfComboTipeJurnal = listOfComboTipeJurnal;
    }


    public TipeJurnalBo getTipeJurnalBoProxy() {
        return tipeJurnalBoProxy;
    }

    public void setTipeJurnalBoProxy(TipeJurnalBo tipeJurnalBoProxy) {
        this.tipeJurnalBoProxy = tipeJurnalBoProxy;
    }

    public TipeJurnal getTipeJurnal() {
        return tipeJurnal;
    }

    public void setTipeJurnal(TipeJurnal tipeJurnal) {
        this.tipeJurnal = tipeJurnal;
    }

    private List<TipeJurnal> initComboTipeJurnal;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipeJurnalAction.logger = logger;
    }


    public List<TipeJurnal> getInitComboTipeJurnal() {
        return initComboTipeJurnal;
    }

    public void setInitComboTipeJurnal(List<TipeJurnal> initComboTipeJurnal) {
        this.initComboTipeJurnal = initComboTipeJurnal;
    }

    public TipeJurnal init(String kode, String flag){
        logger.info("[TipeJurnalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TipeJurnal> listOfResult = (List<TipeJurnal>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (TipeJurnal tipeJurnal: listOfResult) {
                    if(kode.equalsIgnoreCase(tipeJurnal.getTipeJurnalId()) && flag.equalsIgnoreCase(tipeJurnal.getFlag())){
                        setTipeJurnal(tipeJurnal);
                        break;
                    }
                }
            } else {
                setTipeJurnal(new TipeJurnal());
            }

            logger.info("[TipeJurnalAction.init] end process >>>");
        }
        return getTipeJurnal();
    }

    @Override
    public String add() {
        logger.info("[TipeJurnalAction.add] start process >>>");
        TipeJurnal addTipeJurnal = new TipeJurnal();
        setTipeJurnal(addTipeJurnal);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipeJurnalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[TipeJurnalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        TipeJurnal editTipeJurnal = new TipeJurnal();

        if(itemFlag != null){
            try {
                editTipeJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.getTipeJurnalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeJurnalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[TipeJurnalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editTipeJurnal != null) {
                setTipeJurnal(editTipeJurnal);
            } else {
                editTipeJurnal.setFlag(itemFlag);
                editTipeJurnal.setTipeJurnalId(itemId);
                setTipeJurnal(editTipeJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editTipeJurnal.setTipeJurnalId(itemId);
            editTipeJurnal.setFlag(getFlag());
            setTipeJurnal(editTipeJurnal);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<KodeRekening> kodeRekeningList = tipeJurnalBoProxy.getMappingJurnalInKodeRekening(itemId);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKodeRekening",kodeRekeningList);

        logger.info("[TipeJurnalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[TipeJurnalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeJurnal deleteTipeJurnal = new TipeJurnal();

        if (itemFlag != null ) {

            try {
                deleteTipeJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeJurnalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeJurnalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeJurnal != null) {
                setTipeJurnal(deleteTipeJurnal);

            } else {
                deleteTipeJurnal.setTipeJurnalId(itemId);
                deleteTipeJurnal.setFlag(itemFlag);
                setTipeJurnal(deleteTipeJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeJurnal.setTipeJurnalId(itemId);
            deleteTipeJurnal.setFlag(itemFlag);
            setTipeJurnal(deleteTipeJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[TipeJurnalAction.delete] end process <<<");
        List<KodeRekening> kodeRekeningList = tipeJurnalBoProxy.getMappingJurnalInKodeRekening(itemId);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKodeRekening",kodeRekeningList);

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[TipeJurnalAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        TipeJurnal deleteTipeJurnal = new TipeJurnal();

        if (itemFlag != null ) {
            try {
                deleteTipeJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[TipeJurnalAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[TipeJurnalAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteTipeJurnal != null) {
                setTipeJurnal(deleteTipeJurnal);

            } else {
                deleteTipeJurnal.setTipeJurnalId(itemId);
                deleteTipeJurnal.setFlag(itemFlag);
                setTipeJurnal(deleteTipeJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteTipeJurnal.setTipeJurnalId(itemId);
            deleteTipeJurnal.setFlag(itemFlag);
            setTipeJurnal(deleteTipeJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        List<KodeRekening> kodeRekeningList = tipeJurnalBoProxy.getMappingJurnalInKodeRekening(itemId);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKodeRekening",kodeRekeningList);

        logger.info("[TipeJurnalAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[TipeJurnalAction.saveEdit] start process >>>");
        try {
            TipeJurnal editTipeJurnal = getTipeJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editTipeJurnal.setLastUpdateWho(userLogin);
            editTipeJurnal.setLastUpdate(updateTime);
            editTipeJurnal.setAction("U");
            editTipeJurnal.setFlag("Y");

            tipeJurnalBoProxy.saveEdit(editTipeJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[TipeJurnalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeJurnalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeJurnalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[TipeJurnalAction.saveDelete] start process >>>");
        try {
            TipeJurnal deleteTipeJurnal = getTipeJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteTipeJurnal.setLastUpdate(updateTime);
            deleteTipeJurnal.setLastUpdateWho(userLogin);
            deleteTipeJurnal.setAction("U");
            deleteTipeJurnal.setFlag("N");

            tipeJurnalBoProxy.saveDelete(deleteTipeJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[TipeJurnalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeJurnalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[TipeJurnalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[TipeJurnalAction.saveAdd] start process >>>");

        try {
            TipeJurnal tipeJurnal = getTipeJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            tipeJurnal.setCreatedWho(userLogin);
            tipeJurnal.setLastUpdate(updateTime);
            tipeJurnal.setCreatedDate(updateTime);
            tipeJurnal.setLastUpdateWho(userLogin);
            tipeJurnal.setAction("C");
            tipeJurnal.setFlag("Y");

            tipeJurnalBoProxy.saveAdd(tipeJurnal);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[TipeJurnalAction.search] start process >>>");
        TipeJurnal searchTipeJurnal = getTipeJurnal();
        List<TipeJurnal> listOfsearchTipeJurnal = new ArrayList();
        try {
            listOfsearchTipeJurnal = tipeJurnalBoProxy.getByCriteria(searchTipeJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tipeJurnalBoProxy.saveErrorMessage(e.getMessage(), "TipeJurnalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TipeJurnalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TipeJurnalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.setAttribute("listOfResult", listOfsearchTipeJurnal);

        logger.info("[TipeJurnalAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TipeJurnalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[TipeJurnalAction.initForm] end process >>>");
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
