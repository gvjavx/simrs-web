package com.neurix.hris.master.perjalananDinas.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.perjalananDinas.bo.PerjalananDinasBo;
import com.neurix.hris.master.perjalananDinas.model.PerjalananDinas;
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

public class PerjalananDinasAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PerjalananDinasAction.class);
    private PerjalananDinasBo perjalananDinasBoProxy;
    private PerjalananDinas perjalananDinas;

    public PerjalananDinasBo getPerjalananDinasBoProxy() {
        return perjalananDinasBoProxy;
    }

    public void setPerjalananDinasBoProxy(PerjalananDinasBo perjalananDinasBoProxy) {
        this.perjalananDinasBoProxy = perjalananDinasBoProxy;
    }

    public PerjalananDinas getPerjalananDinas() {
        return perjalananDinas;
    }

    public void setPerjalananDinas(PerjalananDinas perjalananDinas) {
        this.perjalananDinas = perjalananDinas;
    }

    private List<PerjalananDinas> initComboPerjalananDinas;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PerjalananDinasAction.logger = logger;
    }


    public List<PerjalananDinas> getInitComboPerjalananDinas() {
        return initComboPerjalananDinas;
    }

    public void setInitComboPerjalananDinas(List<PerjalananDinas> initComboPerjalananDinas) {
        this.initComboPerjalananDinas = initComboPerjalananDinas;
    }

    public PerjalananDinas init(String kode, String flag){
        logger.info("[PerjalananDinasAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PerjalananDinas> listOfResult = (List<PerjalananDinas>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PerjalananDinas perjalananDinas: listOfResult) {
                    if(kode.equalsIgnoreCase(perjalananDinas.getPerjalananDinasId()) && flag.equalsIgnoreCase(perjalananDinas.getFlag())){
                        setPerjalananDinas(perjalananDinas);
                        break;
                    }
                }
            } else {
                setPerjalananDinas(new PerjalananDinas());
            }

            logger.info("[PerjalananDinasAction.init] end process >>>");
        }
        return getPerjalananDinas();
    }

    @Override
    public String add() {
        logger.info("[PerjalananDinasAction.add] start process >>>");
        PerjalananDinas addPerjalananDinas = new PerjalananDinas();
        setPerjalananDinas(addPerjalananDinas);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PerjalananDinasAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PerjalananDinasAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PerjalananDinas editPerjalananDinas = new PerjalananDinas();

        if(itemFlag != null){
            try {
                editPerjalananDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.getPerjalananDinasByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PerjalananDinasAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PerjalananDinasAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPerjalananDinas != null) {
                setPerjalananDinas(editPerjalananDinas);
            } else {
                editPerjalananDinas.setFlag(itemFlag);
                editPerjalananDinas.setPerjalananDinasId(itemId);
                setPerjalananDinas(editPerjalananDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPerjalananDinas.setPerjalananDinasId(itemId);
            editPerjalananDinas.setFlag(getFlag());
            setPerjalananDinas(editPerjalananDinas);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PerjalananDinasAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PerjalananDinasAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PerjalananDinas deletePerjalananDinas = new PerjalananDinas();

        if (itemFlag != null ) {

            try {
                deletePerjalananDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PerjalananDinasAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PerjalananDinasAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePerjalananDinas != null) {
                setPerjalananDinas(deletePerjalananDinas);

            } else {
                deletePerjalananDinas.setPerjalananDinasId(itemId);
                deletePerjalananDinas.setFlag(itemFlag);
                setPerjalananDinas(deletePerjalananDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePerjalananDinas.setPerjalananDinasId(itemId);
            deletePerjalananDinas.setFlag(itemFlag);
            setPerjalananDinas(deletePerjalananDinas);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PerjalananDinasAction.delete] end process <<<");

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
        logger.info("[PerjalananDinasAction.saveEdit] start process >>>");
        try {

            PerjalananDinas editPerjalananDinas = getPerjalananDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPerjalananDinas.setLastUpdateWho(userLogin);
            editPerjalananDinas.setLastUpdate(updateTime);
            editPerjalananDinas.setAction("U");
            editPerjalananDinas.setFlag("Y");

            perjalananDinasBoProxy.saveEdit(editPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PerjalananDinasAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PerjalananDinasAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PerjalananDinasAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PerjalananDinasAction.saveDelete] start process >>>");
        try {

            PerjalananDinas deletePerjalananDinas = getPerjalananDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePerjalananDinas.setLastUpdate(updateTime);
            deletePerjalananDinas.setLastUpdateWho(userLogin);
            deletePerjalananDinas.setAction("U");
            deletePerjalananDinas.setFlag("N");

            perjalananDinasBoProxy.saveDelete(deletePerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PerjalananDinasAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PerjalananDinasAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PerjalananDinasAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PerjalananDinasAction.saveAdd] start process >>>");

        try {
            PerjalananDinas perjalananDinas = getPerjalananDinas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            perjalananDinas.setCreatedWho(userLogin);
            perjalananDinas.setLastUpdate(updateTime);
            perjalananDinas.setCreatedDate(updateTime);
            perjalananDinas.setLastUpdateWho(userLogin);
            perjalananDinas.setAction("C");
            perjalananDinas.setFlag("Y");

            perjalananDinasBoProxy.saveAdd(perjalananDinas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "perjalananDinasBo.saveAdd");
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
        logger.info("[PerjalananDinasAction.search] start process >>>");

        PerjalananDinas searchPerjalananDinas = getPerjalananDinas();
        List<PerjalananDinas> listOfsearchPerjalananDinas = new ArrayList();

        try {
            listOfsearchPerjalananDinas = perjalananDinasBoProxy.getByCriteria(searchPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PerjalananDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PerjalananDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPerjalananDinas);

        logger.info("[PerjalananDinasAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PerjalananDinasAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PerjalananDinasAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPerjalananDinas() {
        logger.info("[PerjalananDinasAction.search] start process >>>");

        PerjalananDinas searchPerjalananDinas = new PerjalananDinas();
        searchPerjalananDinas.setFlag("Y");
        List<PerjalananDinas> listOfsearchPerjalananDinas = new ArrayList();

        try {
            listOfsearchPerjalananDinas = perjalananDinasBoProxy.getByCriteria(searchPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = perjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "PerjalananDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PerjalananDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PerjalananDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPerjalananDinas");
        session.setAttribute("listOfResultPerjalananDinas", listOfsearchPerjalananDinas);

        logger.info("[PerjalananDinasAction.search] end process <<<");

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