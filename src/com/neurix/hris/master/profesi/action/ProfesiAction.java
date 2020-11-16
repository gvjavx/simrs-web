package com.neurix.hris.master.profesi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.profesi.bo.ProfesiBo;
import com.neurix.hris.master.profesi.model.Profesi;
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

public class ProfesiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(ProfesiAction.class);
    private ProfesiBo profesiBoProxy;
    private Profesi profesi;

    private List<Profesi> listComboProfesi = new ArrayList<Profesi>();

    public List<Profesi> getListComboProfesi() {
        return listComboProfesi;
    }

    public void setListComboProfesi(List<Profesi> listComboProfesi) {
        this.listComboProfesi = listComboProfesi;
    }

    public ProfesiBo getProfesiBoProxy() {
        return profesiBoProxy;
    }

    public void setProfesiBoProxy(ProfesiBo profesiBoProxy) {
        this.profesiBoProxy = profesiBoProxy;
    }

    public Profesi getProfesi() {
        return profesi;
    }

    public void setProfesi(Profesi profesi) {
        this.profesi = profesi;
    }

    private List<Profesi> initComboProfesi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ProfesiAction.logger = logger;
    }


    public List<Profesi> getInitComboProfesi() {
        return initComboProfesi;
    }

    public void setInitComboProfesi(List<Profesi> initComboProfesi) {
        this.initComboProfesi = initComboProfesi;
    }

    public Profesi init(String kode, String flag){
        logger.info("[ProfesiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Profesi> listOfResult = (List<Profesi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Profesi profesi: listOfResult) {
                    if(kode.equalsIgnoreCase(profesi.getProfesiId()) && flag.equalsIgnoreCase(profesi.getFlag())){
                        setProfesi(profesi);
                        break;
                    }
                }
            } else {
                setProfesi(new Profesi());
            }

            logger.info("[ProfesiAction.init] end process >>>");
        }
        return getProfesi();
    }

    @Override
    public String add() {
        logger.info("[ProfesiAction.add] start process >>>");
        Profesi addProfesi = new Profesi();
        setProfesi(addProfesi);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[ProfesiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[ProfesiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Profesi editProfesi = new Profesi();

        if(itemFlag != null){
            try {
                editProfesi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.getProfesiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[ProfesiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[ProfesiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editProfesi != null) {
                setProfesi(editProfesi);
            } else {
                editProfesi.setFlag(itemFlag);
                editProfesi.setProfesiId(itemId);
                setProfesi(editProfesi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editProfesi.setProfesiId(itemId);
            editProfesi.setFlag(getFlag());
            setProfesi(editProfesi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[ProfesiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[ProfesiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Profesi deleteProfesi = new Profesi();

        if (itemFlag != null ) {

            try {
                deleteProfesi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[ProfesiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[ProfesiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteProfesi != null) {
                setProfesi(deleteProfesi);

            } else {
                deleteProfesi.setProfesiId(itemId);
                deleteProfesi.setFlag(itemFlag);
                setProfesi(deleteProfesi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteProfesi.setProfesiId(itemId);
            deleteProfesi.setFlag(itemFlag);
            setProfesi(deleteProfesi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[ProfesiAction.delete] end process <<<");

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
        logger.info("[ProfesiAction.saveEdit] start process >>>");
        try {

            Profesi editProfesi = getProfesi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editProfesi.setLastUpdateWho(userLogin);
            editProfesi.setLastUpdate(updateTime);
            editProfesi.setAction("U");
            editProfesi.setFlag("Y");

            profesiBoProxy.saveEdit(editProfesi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[ProfesiAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[ProfesiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[ProfesiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[ProfesiAction.saveDelete] start process >>>");
        try {

            Profesi deleteProfesi = getProfesi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteProfesi.setLastUpdate(updateTime);
            deleteProfesi.setLastUpdateWho(userLogin);
            deleteProfesi.setAction("U");
            deleteProfesi.setFlag("N");

            profesiBoProxy.saveDelete(deleteProfesi);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[ProfesiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[ProfesiAction.saveAdd] start process >>>");

        try {
            Profesi profesi = getProfesi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            profesi.setCreatedWho(userLogin);
            profesi.setLastUpdate(updateTime);
            profesi.setCreatedDate(updateTime);
            profesi.setLastUpdateWho(userLogin);
            profesi.setAction("C");
            profesi.setFlag("Y");

            profesiBoProxy.saveAdd(profesi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
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
        logger.info("[ProfesiAction.search] start process >>>");

        Profesi searchProfesi = getProfesi();
        List<Profesi> listOfsearchProfesi = new ArrayList();

        try {
            listOfsearchProfesi = profesiBoProxy.getByCriteria(searchProfesi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProfesiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProfesiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchProfesi);

        logger.info("[ProfesiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchProfesi() {
        logger.info("[ProfesiAction.search] start process >>>");

        Profesi searchProfesi = new Profesi();
        searchProfesi.setFlag("Y");
        List<Profesi> listOfsearchProfesi = new ArrayList();

        try {
            listOfsearchProfesi = profesiBoProxy.getByCriteria(searchProfesi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProfesiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProfesiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboProfesi.addAll(listOfsearchProfesi);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[ProfesiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[ProfesiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initProfesi() {
        logger.info("[ProfesiAction.search] start process >>>");

        Profesi searchProfesi = new Profesi();
        searchProfesi.setFlag("Y");
        List<Profesi> listOfsearchProfesi = new ArrayList();

        try {
            listOfsearchProfesi = profesiBoProxy.getByCriteria(searchProfesi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = profesiBoProxy.saveErrorMessage(e.getMessage(), "ProfesiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ProfesiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ProfesiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultProfesi");
        session.setAttribute("listOfResultProfesi", listOfsearchProfesi);

        logger.info("[ProfesiAction.search] end process <<<");

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
