package com.neurix.hris.master.smkIndikatorKinerja.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkIndikatorKinerja.bo.SmkIndikatorKinerjaBo;
import com.neurix.hris.master.smkIndikatorKinerja.model.SmkIndikatorKinerja;
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

public class SmkIndikatorKinerjaAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkIndikatorKinerjaAction.class);
    private SmkIndikatorKinerjaBo smkIndikatorKinerjaBoProxy;
    private SmkIndikatorKinerja smkIndikatorKinerja;

    private List<SmkIndikatorKinerja> listComboSmkIndikatorKinerja = new ArrayList<SmkIndikatorKinerja>();

    public List<SmkIndikatorKinerja> getListComboSmkIndikatorKinerja() {
        return listComboSmkIndikatorKinerja;
    }

    public void setListComboSmkIndikatorKinerja(List<SmkIndikatorKinerja> listComboSmkIndikatorKinerja) {
        this.listComboSmkIndikatorKinerja = listComboSmkIndikatorKinerja;
    }

    public SmkIndikatorKinerjaBo getSmkIndikatorKinerjaBoProxy() {
        return smkIndikatorKinerjaBoProxy;
    }

    public void setSmkIndikatorKinerjaBoProxy(SmkIndikatorKinerjaBo smkIndikatorKinerjaBoProxy) {
        this.smkIndikatorKinerjaBoProxy = smkIndikatorKinerjaBoProxy;
    }

    public SmkIndikatorKinerja getSmkIndikatorKinerja() {
        return smkIndikatorKinerja;
    }

    public void setSmkIndikatorKinerja(SmkIndikatorKinerja smkIndikatorKinerja) {
        this.smkIndikatorKinerja = smkIndikatorKinerja;
    }

    private List<SmkIndikatorKinerja> initComboSmkIndikatorKinerja;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorKinerjaAction.logger = logger;
    }


    public List<SmkIndikatorKinerja> getInitComboSmkIndikatorKinerja() {
        return initComboSmkIndikatorKinerja;
    }

    public void setInitComboSmkIndikatorKinerja(List<SmkIndikatorKinerja> initComboSmkIndikatorKinerja) {
        this.initComboSmkIndikatorKinerja = initComboSmkIndikatorKinerja;
    }

    public SmkIndikatorKinerja init(String kode, String flag){
        logger.info("[SmkIndikatorKinerjaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorKinerja> listOfResult = (List<SmkIndikatorKinerja>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkIndikatorKinerja smkIndikatorKinerja: listOfResult) {
                    if(kode.equalsIgnoreCase(smkIndikatorKinerja.getIndikatorKinerjaId()) && flag.equalsIgnoreCase(smkIndikatorKinerja.getFlag())){
                        setSmkIndikatorKinerja(smkIndikatorKinerja);
                        break;
                    }
                }
            } else {
                setSmkIndikatorKinerja(new SmkIndikatorKinerja());
            }

            logger.info("[SmkIndikatorKinerjaAction.init] end process >>>");
        }
        return getSmkIndikatorKinerja();
    }

    @Override
    public String add() {
        logger.info("[SmkIndikatorKinerjaAction.add] start process >>>");
        SmkIndikatorKinerja addSmkIndikatorKinerja = new SmkIndikatorKinerja();
        setSmkIndikatorKinerja(addSmkIndikatorKinerja);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkIndikatorKinerjaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkIndikatorKinerjaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkIndikatorKinerja editSmkIndikatorKinerja = new SmkIndikatorKinerja();

        if(itemFlag != null){
            try {
                editSmkIndikatorKinerja = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.getSmkIndikatorKinerjaByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorKinerjaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkIndikatorKinerjaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkIndikatorKinerja != null) {
                setSmkIndikatorKinerja(editSmkIndikatorKinerja);
            } else {
                editSmkIndikatorKinerja.setFlag(itemFlag);
                editSmkIndikatorKinerja.setIndikatorKinerjaId(itemId);
                setSmkIndikatorKinerja(editSmkIndikatorKinerja);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkIndikatorKinerja.setIndikatorKinerjaId(itemId);
            editSmkIndikatorKinerja.setFlag(getFlag());
            setSmkIndikatorKinerja(editSmkIndikatorKinerja);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkIndikatorKinerjaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkIndikatorKinerjaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkIndikatorKinerja deleteSmkIndikatorKinerja = new SmkIndikatorKinerja();

        if (itemFlag != null ) {

            try {
                deleteSmkIndikatorKinerja = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorKinerjaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkIndikatorKinerjaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkIndikatorKinerja != null) {
                setSmkIndikatorKinerja(deleteSmkIndikatorKinerja);

            } else {
                deleteSmkIndikatorKinerja.setIndikatorKinerjaId(itemId);
                deleteSmkIndikatorKinerja.setFlag(itemFlag);
                setSmkIndikatorKinerja(deleteSmkIndikatorKinerja);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkIndikatorKinerja.setIndikatorKinerjaId(itemId);
            deleteSmkIndikatorKinerja.setFlag(itemFlag);
            setSmkIndikatorKinerja(deleteSmkIndikatorKinerja);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkIndikatorKinerjaAction.delete] end process <<<");

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
        logger.info("[SmkIndikatorKinerjaAction.saveEdit] start process >>>");
        try {

            SmkIndikatorKinerja editSmkIndikatorKinerja = getSmkIndikatorKinerja();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkIndikatorKinerja.setLastUpdateWho(userLogin);
            editSmkIndikatorKinerja.setLastUpdate(updateTime);
            editSmkIndikatorKinerja.setAction("U");
            editSmkIndikatorKinerja.setFlag("Y");

            smkIndikatorKinerjaBoProxy.saveEdit(editSmkIndikatorKinerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorKinerjaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorKinerjaAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorKinerjaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkIndikatorKinerjaAction.saveDelete] start process >>>");
        try {

            SmkIndikatorKinerja deleteSmkIndikatorKinerja = getSmkIndikatorKinerja();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkIndikatorKinerja.setLastUpdate(updateTime);
            deleteSmkIndikatorKinerja.setLastUpdateWho(userLogin);
            deleteSmkIndikatorKinerja.setAction("U");
            deleteSmkIndikatorKinerja.setFlag("N");

            smkIndikatorKinerjaBoProxy.saveDelete(deleteSmkIndikatorKinerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorKinerjaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorKinerjaAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorKinerjaAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkIndikatorKinerjaAction.saveAdd] start process >>>");

        try {
            SmkIndikatorKinerja smkIndikatorKinerja = getSmkIndikatorKinerja();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkIndikatorKinerja.setCreatedWho(userLogin);
            smkIndikatorKinerja.setLastUpdate(updateTime);
            smkIndikatorKinerja.setCreatedDate(updateTime);
            smkIndikatorKinerja.setLastUpdateWho(userLogin);
            smkIndikatorKinerja.setAction("C");
            smkIndikatorKinerja.setFlag("Y");

            smkIndikatorKinerjaBoProxy.saveAdd(smkIndikatorKinerja);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkIndikatorKinerjaAction.search] start process >>>");

        SmkIndikatorKinerja searchSmkIndikatorKinerja = getSmkIndikatorKinerja();
        List<SmkIndikatorKinerja> listOfsearchSmkIndikatorKinerja = new ArrayList();

        try {
            listOfsearchSmkIndikatorKinerja = smkIndikatorKinerjaBoProxy.getByCriteria(searchSmkIndikatorKinerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorKinerjaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorKinerjaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkIndikatorKinerja);

        logger.info("[SmkIndikatorKinerjaAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkIndikatorKinerja() {
        logger.info("[SmkIndikatorKinerjaAction.search] start process >>>");

        SmkIndikatorKinerja searchSmkIndikatorKinerja = new SmkIndikatorKinerja();
        searchSmkIndikatorKinerja.setFlag("Y");
        List<SmkIndikatorKinerja> listOfsearchSmkIndikatorKinerja = new ArrayList();

        try {
            listOfsearchSmkIndikatorKinerja = smkIndikatorKinerjaBoProxy.getByCriteria(searchSmkIndikatorKinerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorKinerjaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorKinerjaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkIndikatorKinerja.addAll(listOfsearchSmkIndikatorKinerja);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkIndikatorKinerjaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkIndikatorKinerjaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkIndikatorKinerja() {
        logger.info("[SmkIndikatorKinerjaAction.search] start process >>>");

        SmkIndikatorKinerja searchSmkIndikatorKinerja = new SmkIndikatorKinerja();
        searchSmkIndikatorKinerja.setFlag("Y");
        List<SmkIndikatorKinerja> listOfsearchSmkIndikatorKinerja = new ArrayList();

        try {
            listOfsearchSmkIndikatorKinerja = smkIndikatorKinerjaBoProxy.getByCriteria(searchSmkIndikatorKinerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorKinerjaBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorKinerjaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorKinerjaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorKinerjaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkIndikatorKinerja");
        session.setAttribute("listOfResultSmkIndikatorKinerja", listOfsearchSmkIndikatorKinerja);

        logger.info("[SmkIndikatorKinerjaAction.search] end process <<<");

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
