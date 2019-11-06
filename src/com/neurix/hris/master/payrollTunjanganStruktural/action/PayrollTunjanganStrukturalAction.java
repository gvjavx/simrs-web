package com.neurix.hris.master.payrollTunjanganStruktural.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollTunjanganStruktural.bo.PayrollTunjanganStrukturalBo;
import com.neurix.hris.master.payrollTunjanganStruktural.model.payrollTunjanganStruktural;
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

public class PayrollTunjanganStrukturalAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganStrukturalAction.class);
    private PayrollTunjanganStrukturalBo payrollTunjanganStrukturalBoProxy;
    private payrollTunjanganStruktural payrollTunjanganStruktural;

    public payrollTunjanganStruktural getPayrollTunjanganStruktural() {
        return payrollTunjanganStruktural;
    }

    public void setPayrollTunjanganStruktural(payrollTunjanganStruktural payrollTunjanganStruktural) {
        this.payrollTunjanganStruktural = payrollTunjanganStruktural;
    }

    public PayrollTunjanganStrukturalBo getPayrollTunjanganStrukturalBoProxy() {
        return payrollTunjanganStrukturalBoProxy;
    }

    public void setPayrollTunjanganStrukturalBoProxy(PayrollTunjanganStrukturalBo payrollTunjanganStrukturalBoProxy) {
        this.payrollTunjanganStrukturalBoProxy = payrollTunjanganStrukturalBoProxy;
    }

    private List<payrollTunjanganStruktural> listComboPayrollTunjanganStruktural = new ArrayList<payrollTunjanganStruktural>();

    public List<payrollTunjanganStruktural> getListComboPayrollTunjanganStruktural() {
        return listComboPayrollTunjanganStruktural;
    }

    public void setListComboPayrollTunjanganStruktural(List<payrollTunjanganStruktural> listComboPayrollTunjanganStruktural) {
        this.listComboPayrollTunjanganStruktural = listComboPayrollTunjanganStruktural;
    }

    private List<payrollTunjanganStruktural> initComboPayrollTunjanganStruktural;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganStrukturalAction.logger = logger;
    }


    public List<payrollTunjanganStruktural> getInitComboPayrollTunjanganStruktural() {
        return initComboPayrollTunjanganStruktural;
    }

    public void setInitComboPayrollTunjanganStruktural(List<payrollTunjanganStruktural> initComboPayrollTunjanganStruktural) {
        this.initComboPayrollTunjanganStruktural = initComboPayrollTunjanganStruktural;
    }

    public payrollTunjanganStruktural init(String kode, String flag){
        logger.info("[PayrollTunjanganStrukturalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollTunjanganStruktural> listOfResult = (List<payrollTunjanganStruktural>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollTunjanganStruktural payrollTunjanganStruktural: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollTunjanganStruktural.getTunjStrukturId()) && flag.equalsIgnoreCase(payrollTunjanganStruktural.getFlag())){
                        setPayrollTunjanganStruktural(payrollTunjanganStruktural);
                        break;
                    }
                }
            } else {
                setPayrollTunjanganStruktural(new payrollTunjanganStruktural());
            }

            logger.info("[PayrollTunjanganStrukturalAction.init] end process >>>");
        }
        return getPayrollTunjanganStruktural();
    }

    @Override
    public String add() {
        logger.info("[PayrollTunjanganStrukturalAction.add] start process >>>");
        payrollTunjanganStruktural addPayrollTunjanganStruktural = new payrollTunjanganStruktural();
        setPayrollTunjanganStruktural(addPayrollTunjanganStruktural);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollTunjanganStrukturalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollTunjanganStrukturalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollTunjanganStruktural editPayrollTunjanganStruktural = new payrollTunjanganStruktural();

        if(itemFlag != null){
            try {
                editPayrollTunjanganStruktural = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.getPayrollTunjanganStrukturalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganStrukturalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollTunjanganStrukturalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollTunjanganStruktural != null) {
                setPayrollTunjanganStruktural(editPayrollTunjanganStruktural);
            } else {
                editPayrollTunjanganStruktural.setFlag(itemFlag);
                setPayrollTunjanganStruktural(editPayrollTunjanganStruktural);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollTunjanganStruktural.setFlag(getFlag());
            setPayrollTunjanganStruktural(editPayrollTunjanganStruktural);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollTunjanganStrukturalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollTunjanganStrukturalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollTunjanganStruktural deletePayrollTunjanganStruktural = new payrollTunjanganStruktural();

        if (itemFlag != null ) {

            try {
                deletePayrollTunjanganStruktural = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganStrukturalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollTunjanganStrukturalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollTunjanganStruktural != null) {
                setPayrollTunjanganStruktural(deletePayrollTunjanganStruktural);

            } else {
                deletePayrollTunjanganStruktural.setFlag(itemFlag);
                setPayrollTunjanganStruktural(deletePayrollTunjanganStruktural);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollTunjanganStruktural.setFlag(itemFlag);
            setPayrollTunjanganStruktural(deletePayrollTunjanganStruktural);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollTunjanganStrukturalAction.delete] end process <<<");

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
        logger.info("[PayrollTunjanganStrukturalAction.saveEdit] start process >>>");
        try {

            payrollTunjanganStruktural editPayrollTunjanganStruktural = getPayrollTunjanganStruktural();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollTunjanganStruktural.setLastUpdateWho(userLogin);
            editPayrollTunjanganStruktural.setLastUpdate(updateTime);
            editPayrollTunjanganStruktural.setAction("U");
            editPayrollTunjanganStruktural.setFlag("Y");

            payrollTunjanganStrukturalBoProxy.saveEdit(editPayrollTunjanganStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrukturalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrukturalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganStrukturalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollTunjanganStrukturalAction.saveDelete] start process >>>");
        try {

            payrollTunjanganStruktural deletePayrollTunjanganStruktural = getPayrollTunjanganStruktural();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollTunjanganStruktural.setLastUpdate(updateTime);
            deletePayrollTunjanganStruktural.setLastUpdateWho(userLogin);
            deletePayrollTunjanganStruktural.setAction("D");
            deletePayrollTunjanganStruktural.setFlag("N");

            payrollTunjanganStrukturalBoProxy.saveDelete(deletePayrollTunjanganStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrukturalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrukturalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganStrukturalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollTunjanganStrukturalAction.saveAdd] start process >>>");

        try {
            payrollTunjanganStruktural payrollTunjanganStruktural = getPayrollTunjanganStruktural();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollTunjanganStruktural.setCreatedWho(userLogin);
            payrollTunjanganStruktural.setLastUpdate(updateTime);
            payrollTunjanganStruktural.setCreatedDate(updateTime);
            payrollTunjanganStruktural.setLastUpdateWho(userLogin);
            payrollTunjanganStruktural.setAction("C");
            payrollTunjanganStruktural.setFlag("Y");

            payrollTunjanganStrukturalBoProxy.saveAdd(payrollTunjanganStruktural);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollTunjanganStrukturalAction.search] start process >>>");

        payrollTunjanganStruktural searchPayrollTunjanganStruktural = getPayrollTunjanganStruktural();
        List<payrollTunjanganStruktural> listOfsearchPayrollTunjanganStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStruktural = payrollTunjanganStrukturalBoProxy.getByCriteria(searchPayrollTunjanganStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollTunjanganStruktural);

        logger.info("[PayrollTunjanganStrukturalAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollTunjanganStruktural() {
        logger.info("[PayrollTunjanganStrukturalAction.search] start process >>>");

        payrollTunjanganStruktural searchPayrollTunjanganStruktural = new payrollTunjanganStruktural();
        searchPayrollTunjanganStruktural.setFlag("Y");
        List<payrollTunjanganStruktural> listOfsearchPayrollTunjanganStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStruktural = payrollTunjanganStrukturalBoProxy.getByCriteria(searchPayrollTunjanganStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollTunjanganStruktural.addAll(listOfsearchPayrollTunjanganStruktural);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollTunjanganStrukturalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollTunjanganStrukturalAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollTunjanganStruktural() {
        logger.info("[PayrollTunjanganStrukturalAction.search] start process >>>");

        payrollTunjanganStruktural searchPayrollTunjanganStruktural = new payrollTunjanganStruktural();
        searchPayrollTunjanganStruktural.setFlag("Y");
        List<payrollTunjanganStruktural> listOfsearchPayrollTunjanganStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStruktural = payrollTunjanganStrukturalBoProxy.getByCriteria(searchPayrollTunjanganStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollTunjanganStruktural");
        session.setAttribute("listOfResultPayrollTunjanganStruktural", listOfsearchPayrollTunjanganStruktural);

        logger.info("[PayrollTunjanganStrukturalAction.search] end process <<<");

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
