package com.neurix.hris.master.smkSkalaNilaiItem.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkSkalaNilaiItem.bo.SmkSkalaNilaiItemBo;
import com.neurix.hris.master.smkSkalaNilaiItem.model.SmkSkalaNilaiItem;
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

public class SmkSkalaNilaiItemAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkSkalaNilaiItemAction.class);
    private SmkSkalaNilaiItemBo smkSkalaNilaiItemBoProxy;
    private SmkSkalaNilaiItem smkSkalaNilaiItem;

    private List<SmkSkalaNilaiItem> listComboSmkSkalaNilaiItem = new ArrayList<SmkSkalaNilaiItem>();

    public List<SmkSkalaNilaiItem> getListComboSmkSkalaNilaiItem() {
        return listComboSmkSkalaNilaiItem;
    }

    public void setListComboSmkSkalaNilaiItem(List<SmkSkalaNilaiItem> listComboSmkSkalaNilaiItem) {
        this.listComboSmkSkalaNilaiItem = listComboSmkSkalaNilaiItem;
    }

    public SmkSkalaNilaiItemBo getSmkSkalaNilaiItemBoProxy() {
        return smkSkalaNilaiItemBoProxy;
    }

    public void setSmkSkalaNilaiItemBoProxy(SmkSkalaNilaiItemBo smkSkalaNilaiItemBoProxy) {
        this.smkSkalaNilaiItemBoProxy = smkSkalaNilaiItemBoProxy;
    }

    public SmkSkalaNilaiItem getSmkSkalaNilaiItem() {
        return smkSkalaNilaiItem;
    }

    public void setSmkSkalaNilaiItem(SmkSkalaNilaiItem smkSkalaNilaiItem) {
        this.smkSkalaNilaiItem = smkSkalaNilaiItem;
    }

    private List<SmkSkalaNilaiItem> initComboSmkSkalaNilaiItem;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaNilaiItemAction.logger = logger;
    }


    public List<SmkSkalaNilaiItem> getInitComboSmkSkalaNilaiItem() {
        return initComboSmkSkalaNilaiItem;
    }

    public void setInitComboSmkSkalaNilaiItem(List<SmkSkalaNilaiItem> initComboSmkSkalaNilaiItem) {
        this.initComboSmkSkalaNilaiItem = initComboSmkSkalaNilaiItem;
    }

    public SmkSkalaNilaiItem init(String kode, String flag){
        logger.info("[SmkSkalaNilaiItemAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkSkalaNilaiItem> listOfResult = (List<SmkSkalaNilaiItem>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkSkalaNilaiItem smkSkalaNilaiItem: listOfResult) {
                    if(kode.equalsIgnoreCase(smkSkalaNilaiItem.getSkalaNilaiItemId()) && flag.equalsIgnoreCase(smkSkalaNilaiItem.getFlag())){
                        setSmkSkalaNilaiItem(smkSkalaNilaiItem);
                        break;
                    }
                }
            } else {
                setSmkSkalaNilaiItem(new SmkSkalaNilaiItem());
            }

            logger.info("[SmkSkalaNilaiItemAction.init] end process >>>");
        }
        return getSmkSkalaNilaiItem();
    }

    @Override
    public String add() {
        logger.info("[SmkSkalaNilaiItemAction.add] start process >>>");
        SmkSkalaNilaiItem addSmkSkalaNilaiItem = new SmkSkalaNilaiItem();
        setSmkSkalaNilaiItem(addSmkSkalaNilaiItem);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkSkalaNilaiItemAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkSkalaNilaiItemAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkSkalaNilaiItem editSmkSkalaNilaiItem = new SmkSkalaNilaiItem();

        if(itemFlag != null){
            try {
                editSmkSkalaNilaiItem = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.getSmkSkalaNilaiItemByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkSkalaNilaiItemAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkSkalaNilaiItemAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkSkalaNilaiItem != null) {
                setSmkSkalaNilaiItem(editSmkSkalaNilaiItem);
            } else {
                editSmkSkalaNilaiItem.setFlag(itemFlag);
                editSmkSkalaNilaiItem.setSkalaNilaiItemId(itemId);
                setSmkSkalaNilaiItem(editSmkSkalaNilaiItem);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkSkalaNilaiItem.setSkalaNilaiItemId(itemId);
            editSmkSkalaNilaiItem.setFlag(getFlag());
            setSmkSkalaNilaiItem(editSmkSkalaNilaiItem);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkSkalaNilaiItemAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkSkalaNilaiItemAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkSkalaNilaiItem deleteSmkSkalaNilaiItem = new SmkSkalaNilaiItem();

        if (itemFlag != null ) {

            try {
                deleteSmkSkalaNilaiItem = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkSkalaNilaiItemAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkSkalaNilaiItemAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkSkalaNilaiItem != null) {
                setSmkSkalaNilaiItem(deleteSmkSkalaNilaiItem);

            } else {
                deleteSmkSkalaNilaiItem.setSkalaNilaiItemId(itemId);
                deleteSmkSkalaNilaiItem.setFlag(itemFlag);
                setSmkSkalaNilaiItem(deleteSmkSkalaNilaiItem);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkSkalaNilaiItem.setSkalaNilaiItemId(itemId);
            deleteSmkSkalaNilaiItem.setFlag(itemFlag);
            setSmkSkalaNilaiItem(deleteSmkSkalaNilaiItem);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkSkalaNilaiItemAction.delete] end process <<<");

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
        logger.info("[SmkSkalaNilaiItemAction.saveEdit] start process >>>");
        try {

            SmkSkalaNilaiItem editSmkSkalaNilaiItem = getSmkSkalaNilaiItem();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkSkalaNilaiItem.setLastUpdateWho(userLogin);
            editSmkSkalaNilaiItem.setLastUpdate(updateTime);
            editSmkSkalaNilaiItem.setAction("U");
            editSmkSkalaNilaiItem.setFlag("Y");

            smkSkalaNilaiItemBoProxy.saveEdit(editSmkSkalaNilaiItem);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiItemAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiItemAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkSkalaNilaiItemAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkSkalaNilaiItemAction.saveDelete] start process >>>");
        try {

            SmkSkalaNilaiItem deleteSmkSkalaNilaiItem = getSmkSkalaNilaiItem();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkSkalaNilaiItem.setLastUpdate(updateTime);
            deleteSmkSkalaNilaiItem.setLastUpdateWho(userLogin);
            deleteSmkSkalaNilaiItem.setAction("U");
            deleteSmkSkalaNilaiItem.setFlag("N");

            smkSkalaNilaiItemBoProxy.saveDelete(deleteSmkSkalaNilaiItem);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiItemAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiItemAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkSkalaNilaiItemAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkSkalaNilaiItemAction.saveAdd] start process >>>");

        try {
            SmkSkalaNilaiItem smkSkalaNilaiItem = getSmkSkalaNilaiItem();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkSkalaNilaiItem.setCreatedWho(userLogin);
            smkSkalaNilaiItem.setLastUpdate(updateTime);
            smkSkalaNilaiItem.setCreatedDate(updateTime);
            smkSkalaNilaiItem.setLastUpdateWho(userLogin);
            smkSkalaNilaiItem.setAction("C");
            smkSkalaNilaiItem.setFlag("Y");

            smkSkalaNilaiItemBoProxy.saveAdd(smkSkalaNilaiItem);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkSkalaNilaiItemAction.search] start process >>>");

        SmkSkalaNilaiItem searchSmkSkalaNilaiItem = getSmkSkalaNilaiItem();
        List<SmkSkalaNilaiItem> listOfsearchSmkSkalaNilaiItem = new ArrayList();

        try {
            listOfsearchSmkSkalaNilaiItem = smkSkalaNilaiItemBoProxy.getByCriteria(searchSmkSkalaNilaiItem);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiItemAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiItemAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkSkalaNilaiItem);

        logger.info("[SmkSkalaNilaiItemAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkSkalaNilaiItem() {
        logger.info("[SmkSkalaNilaiItemAction.search] start process >>>");

        SmkSkalaNilaiItem searchSmkSkalaNilaiItem = new SmkSkalaNilaiItem();
        searchSmkSkalaNilaiItem.setFlag("Y");
        List<SmkSkalaNilaiItem> listOfsearchSmkSkalaNilaiItem = new ArrayList();

        try {
            listOfsearchSmkSkalaNilaiItem = smkSkalaNilaiItemBoProxy.getByCriteria(searchSmkSkalaNilaiItem);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiItemAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiItemAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkSkalaNilaiItem.addAll(listOfsearchSmkSkalaNilaiItem);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkSkalaNilaiItemAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkSkalaNilaiItemAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkSkalaNilaiItem() {
        logger.info("[SmkSkalaNilaiItemAction.search] start process >>>");

        SmkSkalaNilaiItem searchSmkSkalaNilaiItem = new SmkSkalaNilaiItem();
        searchSmkSkalaNilaiItem.setFlag("Y");
        List<SmkSkalaNilaiItem> listOfsearchSmkSkalaNilaiItem = new ArrayList();

        try {
            listOfsearchSmkSkalaNilaiItem = smkSkalaNilaiItemBoProxy.getByCriteria(searchSmkSkalaNilaiItem);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiItemBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiItemBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiItemAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiItemAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkSkalaNilaiItem");
        session.setAttribute("listOfResultSmkSkalaNilaiItem", listOfsearchSmkSkalaNilaiItem);

        logger.info("[SmkSkalaNilaiItemAction.search] end process <<<");

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
