package com.neurix.hris.master.smkIndikatorPenilaianAspek.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.SmkIndikatorPenilaianAspekBo;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
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

public class SmkIndikatorPenilaianAspekAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkIndikatorPenilaianAspekAction.class);
    private SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy;
    private SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek;
    
    private List<SmkIndikatorPenilaianAspek> listComboSmkIndikator = new ArrayList<SmkIndikatorPenilaianAspek>();

    public List<SmkIndikatorPenilaianAspek> getListComboSmkIndikator() {
        return listComboSmkIndikator;
    }

    public void setListComboSmkIndikator(List<SmkIndikatorPenilaianAspek> listComboSmkIndikator) {
        this.listComboSmkIndikator = listComboSmkIndikator;
    }

    public SmkIndikatorPenilaianAspekBo getSmkIndikatorPenilaianAspekBoProxy() {
        return smkIndikatorPenilaianAspekBoProxy;
    }

    public void setSmkIndikatorPenilaianAspekBoProxy(SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy) {
        this.smkIndikatorPenilaianAspekBoProxy = smkIndikatorPenilaianAspekBoProxy;
    }

    public SmkIndikatorPenilaianAspek getSmkIndikatorPenilaianAspek() {
        return smkIndikatorPenilaianAspek;
    }

    public void setSmkIndikatorPenilaianAspek(SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek) {
        this.smkIndikatorPenilaianAspek = smkIndikatorPenilaianAspek;
    }

    private List<SmkIndikatorPenilaianAspek> initComboSmkIndikator;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorPenilaianAspekAction.logger = logger;
    }


    public List<SmkIndikatorPenilaianAspek> getInitComboSmkIndikator() {
        return initComboSmkIndikator;
    }

    public void setInitComboSmkIndikator(List<SmkIndikatorPenilaianAspek> initComboSmkIndikator) {
        this.initComboSmkIndikator = initComboSmkIndikator;
    }

    public SmkIndikatorPenilaianAspek init(String kode, String flag){
        logger.info("[SmkIndikatorAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianAspek> listOfResult = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkIndikatorPenilaianAspek smkIndikator: listOfResult) {
                    if(kode.equalsIgnoreCase(smkIndikator.getIndikatorPenilaianAspekId()) && flag.equalsIgnoreCase(smkIndikator.getFlag())){
                        setSmkIndikatorPenilaianAspek(smkIndikator);
                        break;
                    }
                }
            } else {
                setSmkIndikatorPenilaianAspek(new SmkIndikatorPenilaianAspek());
            }

            logger.info("[SmkIndikatorAction.init] end process >>>");
        }
        return getSmkIndikatorPenilaianAspek();
    }

    @Override
    public String add() {
        logger.info("[SmkIndikatorAction.add] start process >>>");
        SmkIndikatorPenilaianAspek addSmkIndikator = new SmkIndikatorPenilaianAspek();
        setSmkIndikatorPenilaianAspek(addSmkIndikator);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkIndikatorAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkIndikatorAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkIndikatorPenilaianAspek editSmkIndikator = new SmkIndikatorPenilaianAspek();

        if(itemFlag != null){
            try {
                editSmkIndikator = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.getSmkIndikatorByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkIndikatorAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkIndikator != null) {
                setSmkIndikatorPenilaianAspek(editSmkIndikator);
            } else {
                editSmkIndikator.setFlag(itemFlag);
                editSmkIndikator.setIndikatorPenilaianAspekId(itemId);
                setSmkIndikatorPenilaianAspek(editSmkIndikator);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkIndikator.setIndikatorPenilaianAspekId(itemId);
            editSmkIndikator.setFlag(getFlag());
            setSmkIndikatorPenilaianAspek(editSmkIndikator);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkIndikatorAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkIndikatorAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkIndikatorPenilaianAspek deleteSmkIndikator = new SmkIndikatorPenilaianAspek();

        if (itemFlag != null ) {

            try {
                deleteSmkIndikator = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkIndikatorAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkIndikator != null) {
                setSmkIndikatorPenilaianAspek(deleteSmkIndikator);

            } else {
                deleteSmkIndikator.setIndikatorPenilaianAspekId(itemId);
                deleteSmkIndikator.setFlag(itemFlag);
                setSmkIndikatorPenilaianAspek(deleteSmkIndikator);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkIndikator.setIndikatorPenilaianAspekId(itemId);
            deleteSmkIndikator.setFlag(itemFlag);
            setSmkIndikatorPenilaianAspek(deleteSmkIndikator);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkIndikatorAction.delete] end process <<<");

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
        logger.info("[SmkIndikatorAction.saveEdit] start process >>>");
        try {

            SmkIndikatorPenilaianAspek editSmkIndikator = getSmkIndikatorPenilaianAspek();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkIndikator.setLastUpdateWho(userLogin);
            editSmkIndikator.setLastUpdate(updateTime);
            editSmkIndikator.setAction("U");
            editSmkIndikator.setFlag("Y");

            smkIndikatorPenilaianAspekBoProxy.saveEdit(editSmkIndikator);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkIndikatorAction.saveDelete] start process >>>");
        try {

            SmkIndikatorPenilaianAspek deleteSmkIndikator = getSmkIndikatorPenilaianAspek();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkIndikator.setLastUpdate(updateTime);
            deleteSmkIndikator.setLastUpdateWho(userLogin);
            deleteSmkIndikator.setAction("U");
            deleteSmkIndikator.setFlag("N");

            smkIndikatorPenilaianAspekBoProxy.saveDelete(deleteSmkIndikator);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkIndikatorAction.saveAdd] start process >>>");

        try {
            SmkIndikatorPenilaianAspek smkIndikator = getSmkIndikatorPenilaianAspek();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkIndikator.setCreatedWho(userLogin);
            smkIndikator.setLastUpdate(updateTime);
            smkIndikator.setCreatedDate(updateTime);
            smkIndikator.setLastUpdateWho(userLogin);
            smkIndikator.setAction("C");
            smkIndikator.setFlag("Y");

            smkIndikatorPenilaianAspekBoProxy.saveAdd(smkIndikator);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkIndikatorAction.search] start process >>>");

        SmkIndikatorPenilaianAspek searchSmkIndikator = getSmkIndikatorPenilaianAspek();
        List<SmkIndikatorPenilaianAspek> listOfsearchSmkIndikator = new ArrayList();

        try {
            listOfsearchSmkIndikator = smkIndikatorPenilaianAspekBoProxy.getByCriteria(searchSmkIndikator);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkIndikator);

        logger.info("[SmkIndikatorAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkIndikator() {
        logger.info("[SmkIndikatorAction.search] start process >>>");

        SmkIndikatorPenilaianAspek searchSmkIndikator = new SmkIndikatorPenilaianAspek();
        searchSmkIndikator.setFlag("Y");
        List<SmkIndikatorPenilaianAspek> listOfsearchSmkIndikator = new ArrayList();

        try {
            listOfsearchSmkIndikator = smkIndikatorPenilaianAspekBoProxy.getByCriteria(searchSmkIndikator);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkIndikator.addAll(listOfsearchSmkIndikator);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkIndikatorAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkIndikatorAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkIndikator() {
        logger.info("[SmkIndikatorAction.search] start process >>>");

        SmkIndikatorPenilaianAspek searchSmkIndikator = new SmkIndikatorPenilaianAspek();
        searchSmkIndikator.setFlag("Y");
        List<SmkIndikatorPenilaianAspek> listOfsearchSmkIndikator = new ArrayList();

        try {
            listOfsearchSmkIndikator = smkIndikatorPenilaianAspekBoProxy.getByCriteria(searchSmkIndikator);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkIndikator");
        session.setAttribute("listOfResultSmkIndikator", listOfsearchSmkIndikator);

        logger.info("[SmkIndikatorAction.search] end process <<<");

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
