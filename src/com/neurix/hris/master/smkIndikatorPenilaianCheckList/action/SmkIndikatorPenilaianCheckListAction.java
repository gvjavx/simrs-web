package com.neurix.hris.master.smkIndikatorPenilaianCheckList.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.bo.SmkIndikatorPenilaianCheckListBo;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;
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

public class SmkIndikatorPenilaianCheckListAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkIndikatorPenilaianCheckListAction.class);
    private SmkIndikatorPenilaianCheckListBo smkIndikatorPenilaianCheckListBoProxy;
    private SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList;

    private List<SmkIndikatorPenilaianCheckList> listComboSmkIndikatorPenilaianCheckList = new ArrayList<SmkIndikatorPenilaianCheckList>();

    public List<SmkIndikatorPenilaianCheckList> getListComboSmkIndikatorPenilaianCheckList() {
        return listComboSmkIndikatorPenilaianCheckList;
    }

    public void setListComboSmkIndikatorPenilaianCheckList(List<SmkIndikatorPenilaianCheckList> listComboSmkIndikatorPenilaianCheckList) {
        this.listComboSmkIndikatorPenilaianCheckList = listComboSmkIndikatorPenilaianCheckList;
    }

    public SmkIndikatorPenilaianCheckListBo getSmkIndikatorPenilaianCheckListBoProxy() {
        return smkIndikatorPenilaianCheckListBoProxy;
    }

    public void setSmkIndikatorPenilaianCheckListBoProxy(SmkIndikatorPenilaianCheckListBo smkIndikatorPenilaianCheckListBoProxy) {
        this.smkIndikatorPenilaianCheckListBoProxy = smkIndikatorPenilaianCheckListBoProxy;
    }

    public SmkIndikatorPenilaianCheckList getSmkIndikatorPenilaianCheckList() {
        return smkIndikatorPenilaianCheckList;
    }

    public void setSmkIndikatorPenilaianCheckList(SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList) {
        this.smkIndikatorPenilaianCheckList = smkIndikatorPenilaianCheckList;
    }

    private List<SmkIndikatorPenilaianCheckList> initComboSmkIndikatorPenilaianCheckList;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkIndikatorPenilaianCheckListAction.logger = logger;
    }


    public List<SmkIndikatorPenilaianCheckList> getInitComboSmkIndikatorPenilaianCheckList() {
        return initComboSmkIndikatorPenilaianCheckList;
    }

    public void setInitComboSmkIndikatorPenilaianCheckList(List<SmkIndikatorPenilaianCheckList> initComboSmkIndikatorPenilaianCheckList) {
        this.initComboSmkIndikatorPenilaianCheckList = initComboSmkIndikatorPenilaianCheckList;
    }

    public SmkIndikatorPenilaianCheckList init(String kode, String flag){
        logger.info("[SmkIndikatorPenilaianCheckListAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianCheckList> listOfResult = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList: listOfResult) {
                    if(kode.equalsIgnoreCase(smkIndikatorPenilaianCheckList.getIndikatorPenilaianCheckListId()) && flag.equalsIgnoreCase(smkIndikatorPenilaianCheckList.getFlag())){
                        setSmkIndikatorPenilaianCheckList(smkIndikatorPenilaianCheckList);
                        break;
                    }
                }
            } else {
                setSmkIndikatorPenilaianCheckList(new SmkIndikatorPenilaianCheckList());
            }

            logger.info("[SmkIndikatorPenilaianCheckListAction.init] end process >>>");
        }
        return getSmkIndikatorPenilaianCheckList();
    }

    @Override
    public String add() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.add] start process >>>");
        SmkIndikatorPenilaianCheckList addSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        setSmkIndikatorPenilaianCheckList(addSmkIndikatorPenilaianCheckList);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkIndikatorPenilaianCheckListAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkIndikatorPenilaianCheckList editSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();

        if(itemFlag != null){
            try {
                editSmkIndikatorPenilaianCheckList = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.getSmkIndikatorPenilaianCheckListByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorPenilaianCheckListAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkIndikatorPenilaianCheckListAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkIndikatorPenilaianCheckList != null) {
                setSmkIndikatorPenilaianCheckList(editSmkIndikatorPenilaianCheckList);
            } else {
                editSmkIndikatorPenilaianCheckList.setFlag(itemFlag);
                editSmkIndikatorPenilaianCheckList.setCheckListId(itemId);
                setSmkIndikatorPenilaianCheckList(editSmkIndikatorPenilaianCheckList);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkIndikatorPenilaianCheckList.setCheckListId(itemId);
            editSmkIndikatorPenilaianCheckList.setFlag(getFlag());
            setSmkIndikatorPenilaianCheckList(editSmkIndikatorPenilaianCheckList);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkIndikatorPenilaianCheckListAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkIndikatorPenilaianCheckList deleteSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();

        if (itemFlag != null ) {

            try {
                deleteSmkIndikatorPenilaianCheckList = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkIndikatorPenilaianCheckListAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkIndikatorPenilaianCheckListAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkIndikatorPenilaianCheckList != null) {
                setSmkIndikatorPenilaianCheckList(deleteSmkIndikatorPenilaianCheckList);

            } else {
                deleteSmkIndikatorPenilaianCheckList.setCheckListId(itemId);
                deleteSmkIndikatorPenilaianCheckList.setFlag(itemFlag);
                setSmkIndikatorPenilaianCheckList(deleteSmkIndikatorPenilaianCheckList);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkIndikatorPenilaianCheckList.setCheckListId(itemId);
            deleteSmkIndikatorPenilaianCheckList.setFlag(itemFlag);
            setSmkIndikatorPenilaianCheckList(deleteSmkIndikatorPenilaianCheckList);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkIndikatorPenilaianCheckListAction.delete] end process <<<");

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
        logger.info("[SmkIndikatorPenilaianCheckListAction.saveEdit] start process >>>");
        try {

            SmkIndikatorPenilaianCheckList editSmkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkIndikatorPenilaianCheckList.setLastUpdateWho(userLogin);
            editSmkIndikatorPenilaianCheckList.setLastUpdate(updateTime);
            editSmkIndikatorPenilaianCheckList.setAction("U");
            editSmkIndikatorPenilaianCheckList.setFlag("Y");

            smkIndikatorPenilaianCheckListBoProxy.saveEdit(editSmkIndikatorPenilaianCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorPenilaianCheckListAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorPenilaianCheckListAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorPenilaianCheckListAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkIndikatorPenilaianCheckListAction.saveDelete] start process >>>");
        try {

            SmkIndikatorPenilaianCheckList deleteSmkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkIndikatorPenilaianCheckList.setLastUpdate(updateTime);
            deleteSmkIndikatorPenilaianCheckList.setLastUpdateWho(userLogin);
            deleteSmkIndikatorPenilaianCheckList.setAction("U");
            deleteSmkIndikatorPenilaianCheckList.setFlag("N");

            smkIndikatorPenilaianCheckListBoProxy.saveDelete(deleteSmkIndikatorPenilaianCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorPenilaianCheckListAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorPenilaianCheckListAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkIndikatorPenilaianCheckListAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkIndikatorPenilaianCheckListAction.saveAdd] start process >>>");

        try {
            SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkIndikatorPenilaianCheckList.setCreatedWho(userLogin);
            smkIndikatorPenilaianCheckList.setLastUpdate(updateTime);
            smkIndikatorPenilaianCheckList.setCreatedDate(updateTime);
            smkIndikatorPenilaianCheckList.setLastUpdateWho(userLogin);
            smkIndikatorPenilaianCheckList.setAction("C");
            smkIndikatorPenilaianCheckList.setFlag("Y");

            smkIndikatorPenilaianCheckListBoProxy.saveAdd(smkIndikatorPenilaianCheckList);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkIndikatorPenilaianCheckListAction.search] start process >>>");

        SmkIndikatorPenilaianCheckList searchSmkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();
        List<SmkIndikatorPenilaianCheckList> listOfsearchSmkIndikatorPenilaianCheckList = new ArrayList();

        try {
            listOfsearchSmkIndikatorPenilaianCheckList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(searchSmkIndikatorPenilaianCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorPenilaianCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorPenilaianCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkIndikatorPenilaianCheckList);

        logger.info("[SmkIndikatorPenilaianCheckListAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkIndikatorPenilaianCheckList() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.search] start process >>>");

        SmkIndikatorPenilaianCheckList searchSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        searchSmkIndikatorPenilaianCheckList.setFlag("Y");
        List<SmkIndikatorPenilaianCheckList> listOfsearchSmkIndikatorPenilaianCheckList = new ArrayList();

        try {
            listOfsearchSmkIndikatorPenilaianCheckList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(searchSmkIndikatorPenilaianCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorPenilaianCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorPenilaianCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkIndikatorPenilaianCheckList.addAll(listOfsearchSmkIndikatorPenilaianCheckList);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkIndikatorPenilaianCheckListAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkIndikatorPenilaianCheckList() {
        logger.info("[SmkIndikatorPenilaianCheckListAction.search] start process >>>");

        SmkIndikatorPenilaianCheckList searchSmkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        searchSmkIndikatorPenilaianCheckList.setFlag("Y");
        List<SmkIndikatorPenilaianCheckList> listOfsearchSmkIndikatorPenilaianCheckList = new ArrayList();

        try {
            listOfsearchSmkIndikatorPenilaianCheckList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(searchSmkIndikatorPenilaianCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkIndikatorPenilaianCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkIndikatorPenilaianCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkIndikatorPenilaianCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkIndikatorPenilaianCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkIndikatorPenilaianCheckList");
        session.setAttribute("listOfResultSmkIndikatorPenilaianCheckList", listOfsearchSmkIndikatorPenilaianCheckList);

        logger.info("[SmkIndikatorPenilaianCheckListAction.search] end process <<<");

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
