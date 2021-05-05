package com.neurix.hris.master.smkCheckList.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkCheckList.bo.SmkCheckListBo;
import com.neurix.hris.master.smkCheckList.model.SmkCheckList;
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

public class SmkCheckListAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkCheckListAction.class);
    private SmkCheckListBo smkCheckListBoProxy;
    private SmkIndikatorPenilaianCheckListBo smkIndikatorPenilaianCheckListBoProxy;
    private SmkCheckList smkCheckList;
    private SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList;

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

    public SmkCheckListBo getSmkCheckListBoProxy() {
        return smkCheckListBoProxy;
    }

    public void setSmkCheckListBoProxy(SmkCheckListBo smkCheckListBoProxy) {
        this.smkCheckListBoProxy = smkCheckListBoProxy;
    }

    public SmkCheckList getSmkCheckList() {
        return smkCheckList;
    }

    public void setSmkCheckList(SmkCheckList smkCheckList) {
        this.smkCheckList = smkCheckList;
    }

    private List<SmkCheckList> listComboSmkCheckList = new ArrayList<SmkCheckList>();

    public List<SmkCheckList> getListComboSmkCheckList() {
        return listComboSmkCheckList;
    }

    public void setListComboSmkCheckList(List<SmkCheckList> listComboSmkCheckList) {
        this.listComboSmkCheckList = listComboSmkCheckList;
    }

    public SmkCheckListBo getCheckListIdBoProxy() {
        return smkCheckListBoProxy;
    }

    public void setCheckListIdBoProxy(SmkCheckListBo smkCheckListBoProxy) {
        this.smkCheckListBoProxy = smkCheckListBoProxy;
    }

    public SmkCheckList getCheckListId() {
        return smkCheckList;
    }

    public void setCheckListId(SmkCheckList smkCheckList) {
        this.smkCheckList = smkCheckList;
    }

    private List<SmkCheckList> initComboSmkCheckListB;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkCheckListAction.logger = logger;
    }


    public List<SmkCheckList> getInitComboSmkCheckListB() {
        return initComboSmkCheckListB;
    }

    public void setInitComboSmkCheckListB(List<SmkCheckList> initComboSmkCheckListB) {
        this.initComboSmkCheckListB = initComboSmkCheckListB;
    }

    public SmkCheckList init(String kode, String flag){
        logger.info("[SmkCheckListAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkCheckList> listOfResult = (List<SmkCheckList>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkCheckList smkCheckList : listOfResult) {
                    if(kode.equalsIgnoreCase(smkCheckList.getCheckListId()) && flag.equalsIgnoreCase(smkCheckList.getFlag())){
                        setCheckListId(smkCheckList);
                        break;
                    }
                }
            } else {
                setCheckListId(new SmkCheckList());
            }

            logger.info("[SmkCheckListAction.init] end process >>>");
        }
        return getCheckListId();
    }

    public SmkIndikatorPenilaianCheckList initIndikator(String name){
        logger.info("[SmkCheckListAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianCheckList> listOfResult = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");

        if(name != null && !"".equalsIgnoreCase(name)){
            if(listOfResult != null){
                for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList1 : listOfResult) {
                    if(name.equalsIgnoreCase(smkIndikatorPenilaianCheckList1.getIndikatorName())){
                        setSmkIndikatorPenilaianCheckList(smkIndikatorPenilaianCheckList1);
                        break;
                    }
                }
            } else {
                setSmkIndikatorPenilaianCheckList(new SmkIndikatorPenilaianCheckList());
            }

            logger.info("[SmkCheckListAction.init] end process >>>");
        }
        return getSmkIndikatorPenilaianCheckList();
    }

    @Override
    public String add() {
        logger.info("[SmkCheckListAction.add] start process >>>");
        SmkCheckList addSmkCheckListB = new SmkCheckList();
        setCheckListId(addSmkCheckListB);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianCheckList> smkCheckListList = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");
        if(smkCheckListList != null){
            session.removeAttribute("checkListIndikator");
            session.setAttribute("checkListIndikator", smkCheckListList);
        }else{
            session.removeAttribute("checkListIndikator");
        }


        logger.info("[SmkCheckListAction.add] stop process >>>");
        return "init_add";
    }

    public String modalAdd() {
        SmkCheckList addSmkCheckList = new SmkCheckList();
        setCheckListId(addSmkCheckList);
        setAddOrEdit(true);
        setAdd(true);
        return "init_addIndikator";
    }

    public String addIndikator() {
        logger.info("[SmkCheckListAction.saveAdd] start process >>>");
        List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();

            //HttpSession session = ServletActionContext.getRequest().getSession();
            smkIndikatorPenilaianCheckListList = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");
            if(smkIndikatorPenilaianCheckListList != null){
                smkIndikatorPenilaianCheckListList.add(smkIndikatorPenilaianCheckList);
            }else{
                smkIndikatorPenilaianCheckListList = new ArrayList();
                smkIndikatorPenilaianCheckListList.add(smkIndikatorPenilaianCheckList);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        setAddOrEdit(true);
        setAdd(true);
        session.setAttribute("checkListIndikator", smkIndikatorPenilaianCheckListList);
        return "init_addIndikator";
    }

    public String initComboCheckList() {
        logger.info("[CheckListAction.search] start process >>>");

        SmkCheckList searchCheckList = new SmkCheckList();
        List<SmkCheckList> listOfSearchCheckList = new ArrayList();
        searchCheckList.setFlag("Y");
        try {
            listOfSearchCheckList = smkCheckListBoProxy.getByCriteria(searchCheckList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "CheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CheckListAction.search] Error when saving error,", e1);
            }
            logger.error("[CheckListAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboSmkCheckList.addAll(listOfSearchCheckList);
        logger.info("[CheckListAction.search] end process <<<");

        return SUCCESS;
    }
    
    public String deleteIndikator() {
        logger.info("[SmkCheckListAction.delete] start process >>>");

        String itemId = getId();
        SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();

        try {
            smkIndikatorPenilaianCheckList = initIndikator(itemId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.delete] Error when retrieving delete data,", e1);
            }
            logger.error("[SmkCheckListAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }

        if (smkIndikatorPenilaianCheckList != null) {
            setSmkIndikatorPenilaianCheckList(smkIndikatorPenilaianCheckList);

        } else {
            smkIndikatorPenilaianCheckList.setCheckListId(itemId);
            setSmkIndikatorPenilaianCheckList(smkIndikatorPenilaianCheckList);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        logger.info("[SmkCheckListAction.delete] end process <<<");

        return "deleteIndikator";
    }

    public String SavedeleteIndikator() {
        logger.info("[SpddAction.reroute] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");
        List<SmkIndikatorPenilaianCheckList> listHasil =  new ArrayList();
        SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = getSmkIndikatorPenilaianCheckList();

        if(smkIndikatorPenilaianCheckList.getIndikatorName() != null && !"".equalsIgnoreCase(smkIndikatorPenilaianCheckList.getIndikatorName())){
            if(smkIndikatorPenilaianCheckListList != null){
                for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList1: smkIndikatorPenilaianCheckListList) {
                    if(!smkIndikatorPenilaianCheckList.getIndikatorName().equalsIgnoreCase(smkIndikatorPenilaianCheckList1.getIndikatorName())){
                        listHasil.add(smkIndikatorPenilaianCheckList1);
                    }else{
                    }
                }
            }
            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("checkListIndikator");
        session.setAttribute("checkListIndikator", listHasil);
        return "init_addIndikator";
    }


    @Override
    public String edit() {
        logger.info("[SmkCheckListAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkCheckList editSmkCheckListB = new SmkCheckList();
        List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = new ArrayList();
        SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        smkIndikatorPenilaianCheckList.setFlag("Y");
        smkIndikatorPenilaianCheckList.setCheckListId(itemId);

        if(itemFlag != null){
            try {
                editSmkCheckListB = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getSmkCheckListByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkCheckListAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkCheckListAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkCheckListB != null) {
                setCheckListId(editSmkCheckListB);
                setAddOrEdit(true);
                smkIndikatorPenilaianCheckListList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(smkIndikatorPenilaianCheckList);
                HttpSession session = ServletActionContext.getRequest().getSession();
                //session.removeAttribute("checkListIndikator");
                List<SmkIndikatorPenilaianCheckList> smkCheckListList = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");
                if(smkCheckListList != null){
                    session.removeAttribute("checkListIndikator");
                    session.setAttribute("checkListIndikator", smkCheckListList);
                }else{
                    session.removeAttribute("checkListIndikator");
                    session.setAttribute("checkListIndikator", smkIndikatorPenilaianCheckListList);
                }


            } else {
                editSmkCheckListB.setFlag(itemFlag);
                editSmkCheckListB.setCheckListId(itemId);
                setCheckListId(editSmkCheckListB);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkCheckListB.setCheckListId(itemId);
            editSmkCheckListB.setFlag(getFlag());
            setCheckListId(editSmkCheckListB);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[SmkCheckListAction.edit] end process >>>");
        return "init_add";
    }

    public String cancelIndikator(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("checkListIndikator");
        return INPUT;
    }

    public String detail(){
        logger.info("[SmkCheckListAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkCheckList smkCheckList = new SmkCheckList();
        SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = new ArrayList();

        smkIndikatorPenilaianCheckList.setFlag("Y");
        smkIndikatorPenilaianCheckList.setCheckListId(itemId);

        if(itemFlag != null){
            try {
                smkCheckList = init(itemId, itemFlag);
                smkIndikatorPenilaianCheckListList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(smkIndikatorPenilaianCheckList);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getSmkCheckListByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkCheckListAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkCheckListAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(smkCheckList != null) {
                setCheckListId(smkCheckList);
            } else {
                smkCheckList.setFlag(itemFlag);
                smkCheckList.setCheckListId(itemId);
                setCheckListId(smkCheckList);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            smkCheckList.setCheckListId(itemId);
            smkCheckList.setFlag(getFlag());
            setCheckListId(smkCheckList);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("checkListIndikator");
        session.setAttribute("checkListIndikator", smkIndikatorPenilaianCheckListList);

        setAddOrEdit(true);
        logger.info("[SmkCheckListAction.edit] end process >>>");

        return "init_detail";
    }

    @Override
    public String delete() {
        logger.info("[SmkCheckListAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkCheckList editSmkCheckListB = new SmkCheckList();
        List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = new ArrayList();
        SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();
        smkIndikatorPenilaianCheckList.setFlag("Y");
        smkIndikatorPenilaianCheckList.setCheckListId(itemId);

        if(itemFlag != null){
            try {
                editSmkCheckListB = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getSmkCheckListByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkCheckListAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkCheckListAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkCheckListB != null) {
                setCheckListId(editSmkCheckListB);
                setDelete(true);
                smkIndikatorPenilaianCheckListList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(smkIndikatorPenilaianCheckList);
                HttpSession session = ServletActionContext.getRequest().getSession();
                //session.removeAttribute("checkListIndikator");
                session.setAttribute("checkListIndikator", smkIndikatorPenilaianCheckListList);

            } else {
                editSmkCheckListB.setFlag(itemFlag);
                editSmkCheckListB.setCheckListId(itemId);
                setCheckListId(editSmkCheckListB);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkCheckListB.setCheckListId(itemId);
            editSmkCheckListB.setFlag(getFlag());
            setCheckListId(editSmkCheckListB);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[SmkCheckListAction.edit] end process >>>");
        return "init_add";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        if (isAddOrEdit()) {
            if (!isAdd()) {
                logger.info("[SmkCheckListAction.saveEdit] start process >>>");
                try {
                    SmkCheckList editSmkCheckListB = getCheckListId();
                    SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList = new SmkIndikatorPenilaianCheckList();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    editSmkCheckListB.setLastUpdateWho(userLogin);
                    editSmkCheckListB.setLastUpdate(updateTime);
                    editSmkCheckListB.setAction("U");
                    editSmkCheckListB.setFlag("Y");

                    smkIndikatorPenilaianCheckList.setFlag("Y");
                    smkIndikatorPenilaianCheckList.setCheckListId(editSmkCheckListB.getCheckListId());

                    List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = new ArrayList();
                    smkIndikatorPenilaianCheckListList = smkIndikatorPenilaianCheckListBoProxy.getByCriteria(smkIndikatorPenilaianCheckList);

                    smkCheckListBoProxy.saveEdit(editSmkCheckListB, smkIndikatorPenilaianCheckListList);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[SmkCheckListAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[SmkCheckListAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
                logger.info("[SmkCheckListAction.saveEdit] end process <<<");

                return "success_save_edit";
            }else{
                logger.info("[saveAdd] start process >>>");
                try {
                    SmkCheckList smkCheckList = getSmkCheckList();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    smkCheckList.setCreatedWho(userLogin);
                    smkCheckList.setLastUpdate(updateTime);
                    smkCheckList.setCreatedDate(updateTime);
                    smkCheckList.setLastUpdateWho(userLogin);
                    smkCheckList.setAction("C");
                    smkCheckList.setFlag("Y");
                    smkCheckListBoProxy.saveAdd(smkCheckList);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
                logger.info("[liburAction.saveAdd] end process >>>");
            }
        }else if (isDelete()) {
            logger.info("[SmkCheckListAction.saveDelete] start process >>>");
            try {
                SmkCheckList deleteSmkCheckListB = getCheckListId();
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteSmkCheckListB.setLastUpdate(updateTime);
                deleteSmkCheckListB.setLastUpdateWho(userLogin);
                deleteSmkCheckListB.setAction("U");
                deleteSmkCheckListB.setFlag("N");

                smkCheckListBoProxy.saveDelete(deleteSmkCheckListB);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkCheckListAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[SmkCheckListAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }
            logger.info("[SmkCheckListAction.saveDelete] end process <<<");
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("checkListIndikator");
        return "init_addIndikator";
    }

    public String saveEdit(){
        logger.info("[SmkCheckListAction.saveEdit] start process >>>");
        try {

            SmkCheckList editSmkCheckListB = getCheckListId();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkCheckListB.setLastUpdateWho(userLogin);
            editSmkCheckListB.setLastUpdate(updateTime);
            editSmkCheckListB.setAction("U");
            editSmkCheckListB.setFlag("Y");

            smkCheckListBoProxy.saveEdit(editSmkCheckListB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkCheckListAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkCheckListAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkCheckListAction.saveDelete] start process >>>");
        try {

            SmkCheckList deleteSmkCheckListB = getCheckListId();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkCheckListB.setLastUpdate(updateTime);
            deleteSmkCheckListB.setLastUpdateWho(userLogin);
            deleteSmkCheckListB.setAction("U");
            deleteSmkCheckListB.setFlag("N");

            smkCheckListBoProxy.saveDelete(deleteSmkCheckListB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkCheckListAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkCheckListAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkCheckListAction.saveAdd] start process >>>");

        try {
            SmkCheckList smkCheckList = getCheckListId();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkCheckList.setCreatedWho(userLogin);
            smkCheckList.setLastUpdate(updateTime);
            smkCheckList.setCreatedDate(updateTime);
            smkCheckList.setLastUpdateWho(userLogin);
            smkCheckList.setAction("C");
            smkCheckList.setFlag("Y");

            smkCheckListBoProxy.saveAdd(smkCheckList);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkCheckListAction.search] start process >>>");

        SmkCheckList searchSmkCheckListB = getCheckListId();
        List<SmkCheckList> listOfsearchSmkCheckListB = new ArrayList();

        try {
            listOfsearchSmkCheckListB = smkCheckListBoProxy.getByCriteria(searchSmkCheckListB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkCheckListB);

        logger.info("[SmkCheckListAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkCheckList() {
        logger.info("[SmkCheckListAction.search] start process >>>");

        SmkCheckList searchSmkCheckListB = new SmkCheckList();
        searchSmkCheckListB.setFlag("Y");
        List<SmkCheckList> listOfsearchSmkCheckListB = new ArrayList();

        try {
            listOfsearchSmkCheckListB = smkCheckListBoProxy.getByCriteria(searchSmkCheckListB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkCheckList.addAll(listOfsearchSmkCheckListB);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkCheckListAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkCheckListAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkCheckList() {
        logger.info("[SmkCheckListAction.search] start process >>>");

        SmkCheckList searchSmkCheckListB = new SmkCheckList();
        searchSmkCheckListB.setFlag("Y");
        List<SmkCheckList> listOfsearchSmkCheckListB = new ArrayList();

        try {
            listOfsearchSmkCheckListB = smkCheckListBoProxy.getByCriteria(searchSmkCheckListB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkCheckListBoProxy.saveErrorMessage(e.getMessage(), "SmkCheckListBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkCheckListAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkCheckListAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkCheckList");
        session.setAttribute("listOfResultSmkCheckList", listOfsearchSmkCheckListB);

        logger.info("[SmkCheckListAction.search] end process <<<");

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
