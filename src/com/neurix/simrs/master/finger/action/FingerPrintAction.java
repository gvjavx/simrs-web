package com.neurix.simrs.master.finger.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.finger.bo.FingerPrintBo;
import com.neurix.simrs.master.finger.model.FingerPrint;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FingerPrintAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(FingerPrintAction.class);

    private FingerPrint fingerPrint;
    private String userId;
    private FingerPrintBo fingerPrintBoProxy;
    private List<FingerPrint> listOffingerPrint = new ArrayList<>();

    private String tipe;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        FingerPrintAction.logger = logger;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FingerPrintBo getFingerPrintBoProxy() {
        return fingerPrintBoProxy;
    }

    public List<FingerPrint> getListOffingerPrint() {
        return listOffingerPrint;
    }

    public void setListOffingerPrint(List<FingerPrint> listOffingerPrint) {
        this.listOffingerPrint = listOffingerPrint;
    }

    public FingerPrint init(String kode, String flag){
        logger.info("[FingerPrintAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<FingerPrint> listOfResult = (List<FingerPrint>) session.getAttribute("listOfResult");
        List<FingerPrint> listFingerPrint = new ArrayList<>();

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (FingerPrint fingerPrint : listOfResult){
                    if (kode.equalsIgnoreCase(fingerPrint.getIdFingerPrint()) && flag.equalsIgnoreCase(fingerPrint.getFlag())){
                        setFingerPrint(fingerPrint);
                        break;
                    }
                }
            } else {
                setFingerPrint(new FingerPrint());
            }
            logger.info("[FingerPrintAction.init] end process >>>>>");
        }
        return getFingerPrint();
    }

    @Override
    public String add() {
        logger.info("[FingerPrintAction.add] start process");

        FingerPrint addFingerPrint = new FingerPrint();
        setFingerPrint(addFingerPrint);
        setAdd(true);
        setAddOrEdit(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[FingerPrint.add] stop process");
        return "add";
    }

    @Override
    public String edit() {
        logger.info("[FingerPrintAction.edit] start process >>>>");
        String fingerPrintId = getId();
        String fingerPrintFlag = getFlag();

        FingerPrint editFingerPrint = new FingerPrint();

        if (fingerPrintFlag != null){
            try{
                editFingerPrint = init(fingerPrintId, fingerPrintFlag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = fingerPrintBoProxy.saveErrorMessage(e.getMessage(), "fingerPrintBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[FingerPrintAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[FingerPrintAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editFingerPrint != null){
                setFingerPrint(editFingerPrint);
            }else {
                editFingerPrint.setFlag(fingerPrintFlag);
                editFingerPrint.setIdFingerPrint(fingerPrintId);
                setFingerPrint(editFingerPrint);
                addActionError("Error, Unable to find data with id = "+ fingerPrintId);
                return "failure";
            }
        }else {
            editFingerPrint.setIdFingerPrint(fingerPrintId);
            editFingerPrint.setFlag(fingerPrintFlag);
            setFingerPrint(editFingerPrint);
            addActionError("Error, Unable to find data with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[FingerPrintAction.edit] end process >>>>>");
        return "edit";
    }

    @Override
    public String delete() {
        logger.info("[FingerPrintAction.delete] start process");

        String idFingerPrint = getId();
        String flag = getFlag();
        FingerPrint deleteFingerPrint = new FingerPrint();

        if (flag != null){
            try{
                deleteFingerPrint = init(idFingerPrint, flag);
            }catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = fingerPrintBoProxy.saveErrorMessage(e.getMessage(), "FingerPrintBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[FingerPrintAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[FingerPrintAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteFingerPrint != null){
                setFingerPrint(deleteFingerPrint);
            }else {
                deleteFingerPrint.setIdFingerPrint(idFingerPrint);
                deleteFingerPrint.setFlag(flag);
                setFingerPrint(deleteFingerPrint);
                addActionError("Error, Unable to find data with id = " + idFingerPrint);
                return "failure";
            }
        }else {
            deleteFingerPrint.setIdFingerPrint(idFingerPrint);
            deleteFingerPrint.setFlag(flag);
            setFingerPrint(deleteFingerPrint);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[FingerPrintAction.delete] end process <<<<<<");
        return "delete";
    }


    @Override
    public String view() {
        logger.info("[FingerPrintAction.view] start process >>>>");

        FingerPrint fingerPrint = init(this.id,"Y");
        setFingerPrint(fingerPrint);

        if ("edit".equalsIgnoreCase(getTipe())){
            return "edit_password";
        }
        logger.info("[FingerPrintAction.view] end process <<<<<<");
        return "create_user";
    }

    @Override
    public String save() {
        logger.info("[FingerPrintAction.save] start process >>>>");

        FingerPrint fingerPrint = getFingerPrint();
        fingerPrint.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        fingerPrint.setLastUpdateWho(CommonUtil.userLogin());


        logger.info("[FingerPrintAction.save] end process <<<<<<");
        return "create_user";
    }

    @Override
    public String search() {
        logger.info("[FingerPrintAction.search] start process");

        FingerPrint searchPesien = getFingerPrint();
        List<FingerPrint> listOfFingerPrint = new ArrayList<>();

        try{
            listOfFingerPrint = fingerPrintBoProxy.getByCriteria(searchPesien);
        }catch (GeneralBOException e) {
            logger.error("[FingerPrintAction.getByCriteria] Error when get by criteria fingerPrint, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfFingerPrint);

        logger.info("[BelajarAction.search] end process <<<");

        return "search";
    }

    public String saveAdd(){
        logger.info(("[FingerPrintAction.saveAdd] start process"));

        try{
            FingerPrint fingerPrint = getFingerPrint();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            fingerPrint.setFlag("Y");
            fingerPrint.setAction("C");
            fingerPrint.setCreatedDate(updateTime);
            fingerPrint.setCreatedWho(userLogin);
            fingerPrint.setLastUpdate(updateTime);
            fingerPrint.setLastUpdateWho(userLogin);

            fingerPrintBoProxy.saveAdd(fingerPrint);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = fingerPrintBoProxy.saveErrorMessage(e.getMessage(), "fingerPrintBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[fingerPrintAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[fingerPrintAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[fingerPrintAction.saveAdd] end process >>>>");
        return "add";
    }

    public String saveEdit(){
        logger.info("[FingerPrintAction.saveEdit] start process >>>");
        try {

            FingerPrint editFingerPrint = getFingerPrint();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editFingerPrint.setLastUpdateWho(userLogin);
            editFingerPrint.setLastUpdate(updateTime);
            editFingerPrint.setAction("U");
            editFingerPrint.setFlag("Y");

            fingerPrintBoProxy.saveEdit(editFingerPrint);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = fingerPrintBoProxy.saveErrorMessage(e.getMessage(), "FingerPrintBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[FingerPrintAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[FingerPrintAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[FingerPrintAction.saveEdit] end process <<<");
        return "edit";
    }

    public String saveDelete(){
        logger.info("[fingerPrintAction.saveDelete] start process >>>>");

        try{
            FingerPrint deleteFingerPrint = getFingerPrint();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteFingerPrint.setLastUpdate(updateTime);
            deleteFingerPrint.setLastUpdateWho(userLogin);
            deleteFingerPrint.setAction("U");
            deleteFingerPrint.setFlag("N");

            fingerPrintBoProxy.saveDelete(deleteFingerPrint);
        }catch (GeneralBOException e){
            Long logId = null;
            try {
                logId = fingerPrintBoProxy.saveErrorMessage(e.getMessage(), "FingerPrintBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[FingerPrintAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[FingerPrintAction.saveDelete] Error when editing item fingerPrint," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[FingerPrintAction.saveDelete] end process <<<");
        return "delete";
    }


    @Override
    public String initForm() {
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public List getListComboFingerPrint(String query){
        logger.info("[FingerPrintAction.getListComboFingerPrint] start process >>>");

        List<FingerPrint> listOfFingerPrint = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FingerPrintBo fingerPrintBo = (FingerPrintBo) ctx.getBean("fingerPrintBoProxy");

        try {
            listOfFingerPrint = fingerPrintBo.getListComboFingerPrint(query);
        } catch (GeneralBOException e) {
            logger.error("[FingerPrintAction.getListComboFingerPrint] Error when get combo fingerPrint, please inform to your admin.", e);
        }

        logger.info("[FingerPrintAction.getListComboFingerPrint] end process <<<");
        return listOfFingerPrint;
    }

    public String getListComboSelectFingerPrint(){
        logger.info("[FingerPrintAction.getListComboSelectFingerPrint] start process >>>");

        List<FingerPrint> fingerPrintList = new ArrayList<>();
        FingerPrint fingerPrint = new FingerPrint();

        try {
            fingerPrintList = fingerPrintBoProxy.getByCriteria(fingerPrint);
        }catch (GeneralBOException e){
            logger.error("[FingerPrintAction.getListComboSelectFingerPrint] Error when get data fingerPrint ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOffingerPrint.addAll(fingerPrintList);
        logger.info("[FingerPrintAction.getListComboSelectFingerPrint] end process <<<");
        return SUCCESS;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public FingerPrint getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(FingerPrint fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public void setFingerPrintBoProxy(FingerPrintBo fingerPrintBoProxy) {
        this.fingerPrintBoProxy = fingerPrintBoProxy;
    }
}