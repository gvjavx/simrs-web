package com.neurix.hris.master.payrollTunjanganPerumahan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollTunjanganPerumahan.bo.PayrollTunjanganPerumahanBo;
import com.neurix.hris.master.payrollTunjanganPerumahan.model.payrollTunjanganPerumahan;
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

public class PayrollTunjanganPerumahanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganPerumahanAction.class);
    private PayrollTunjanganPerumahanBo payrollTunjanganPerumahanBoProxy;
    private payrollTunjanganPerumahan payrollTunjanganPerumahan;

    public payrollTunjanganPerumahan getPayrollTunjanganPerumahan() {
        return payrollTunjanganPerumahan;
    }

    public void setPayrollTunjanganPerumahan(payrollTunjanganPerumahan payrollTunjanganPerumahan) {
        this.payrollTunjanganPerumahan = payrollTunjanganPerumahan;
    }

    public PayrollTunjanganPerumahanBo getPayrollTunjanganPerumahanBoProxy() {
        return payrollTunjanganPerumahanBoProxy;
    }

    public void setPayrollTunjanganPerumahanBoProxy(PayrollTunjanganPerumahanBo payrollTunjanganPerumahanBoProxy) {
        this.payrollTunjanganPerumahanBoProxy = payrollTunjanganPerumahanBoProxy;
    }

    private List<payrollTunjanganPerumahan> listComboPayrollTunjanganPerumahan = new ArrayList<payrollTunjanganPerumahan>();

    public List<payrollTunjanganPerumahan> getListComboPayrollTunjanganPerumahan() {
        return listComboPayrollTunjanganPerumahan;
    }

    public void setListComboPayrollTunjanganPerumahan(List<payrollTunjanganPerumahan> listComboPayrollTunjanganPerumahan) {
        this.listComboPayrollTunjanganPerumahan = listComboPayrollTunjanganPerumahan;
    }

    private List<payrollTunjanganPerumahan> initComboPayrollTunjanganPerumahan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganPerumahanAction.logger = logger;
    }


    public List<payrollTunjanganPerumahan> getInitComboPayrollTunjanganPerumahan() {
        return initComboPayrollTunjanganPerumahan;
    }

    public void setInitComboPayrollTunjanganPerumahan(List<payrollTunjanganPerumahan> initComboPayrollTunjanganPerumahan) {
        this.initComboPayrollTunjanganPerumahan = initComboPayrollTunjanganPerumahan;
    }

    public payrollTunjanganPerumahan init(String kode, String flag){
        logger.info("[PayrollTunjanganPerumahanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollTunjanganPerumahan> listOfResult = (List<payrollTunjanganPerumahan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollTunjanganPerumahan payrollTunjanganPerumahan: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollTunjanganPerumahan.getTunjRumahId()) && flag.equalsIgnoreCase(payrollTunjanganPerumahan.getFlag())){
                        setPayrollTunjanganPerumahan(payrollTunjanganPerumahan);
                        break;
                    }
                }
            } else {
                setPayrollTunjanganPerumahan(new payrollTunjanganPerumahan());
            }

            logger.info("[PayrollTunjanganPerumahanAction.init] end process >>>");
        }
        return getPayrollTunjanganPerumahan();
    }

    @Override
    public String add() {
        logger.info("[PayrollTunjanganPerumahanAction.add] start process >>>");
        payrollTunjanganPerumahan addPayrollTunjanganPerumahan = new payrollTunjanganPerumahan();
        setPayrollTunjanganPerumahan(addPayrollTunjanganPerumahan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollTunjanganPerumahanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollTunjanganPerumahanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollTunjanganPerumahan editPayrollTunjanganPerumahan = new payrollTunjanganPerumahan();

        if(itemFlag != null){
            try {
                editPayrollTunjanganPerumahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.getPayrollTunjanganPerumahanByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganPerumahanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollTunjanganPerumahanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollTunjanganPerumahan != null) {
                setPayrollTunjanganPerumahan(editPayrollTunjanganPerumahan);
            } else {
                editPayrollTunjanganPerumahan.setFlag(itemFlag);
                setPayrollTunjanganPerumahan(editPayrollTunjanganPerumahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollTunjanganPerumahan.setFlag(getFlag());
            setPayrollTunjanganPerumahan(editPayrollTunjanganPerumahan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollTunjanganPerumahanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollTunjanganPerumahanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollTunjanganPerumahan deletePayrollTunjanganPerumahan = new payrollTunjanganPerumahan();

        if (itemFlag != null ) {

            try {
                deletePayrollTunjanganPerumahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganPerumahanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollTunjanganPerumahanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollTunjanganPerumahan != null) {
                setPayrollTunjanganPerumahan(deletePayrollTunjanganPerumahan);

            } else {
                deletePayrollTunjanganPerumahan.setFlag(itemFlag);
                setPayrollTunjanganPerumahan(deletePayrollTunjanganPerumahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollTunjanganPerumahan.setFlag(itemFlag);
            setPayrollTunjanganPerumahan(deletePayrollTunjanganPerumahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollTunjanganPerumahanAction.delete] end process <<<");

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
        logger.info("[PayrollTunjanganPerumahanAction.saveEdit] start process >>>");
        try {

            payrollTunjanganPerumahan editPayrollTunjanganPerumahan = getPayrollTunjanganPerumahan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollTunjanganPerumahan.setLastUpdateWho(userLogin);
            editPayrollTunjanganPerumahan.setLastUpdate(updateTime);
            editPayrollTunjanganPerumahan.setAction("U");
            editPayrollTunjanganPerumahan.setFlag("Y");

            payrollTunjanganPerumahanBoProxy.saveEdit(editPayrollTunjanganPerumahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganPerumahanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganPerumahanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganPerumahanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollTunjanganPerumahanAction.saveDelete] start process >>>");
        try {

            payrollTunjanganPerumahan deletePayrollTunjanganPerumahan = getPayrollTunjanganPerumahan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollTunjanganPerumahan.setLastUpdate(updateTime);
            deletePayrollTunjanganPerumahan.setLastUpdateWho(userLogin);
            deletePayrollTunjanganPerumahan.setAction("D");
            deletePayrollTunjanganPerumahan.setFlag("N");

            payrollTunjanganPerumahanBoProxy.saveDelete(deletePayrollTunjanganPerumahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganPerumahanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganPerumahanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganPerumahanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollTunjanganPerumahanAction.saveAdd] start process >>>");

        try {
            payrollTunjanganPerumahan payrollTunjanganPerumahan = getPayrollTunjanganPerumahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollTunjanganPerumahan.setCreatedWho(userLogin);
            payrollTunjanganPerumahan.setLastUpdate(updateTime);
            payrollTunjanganPerumahan.setCreatedDate(updateTime);
            payrollTunjanganPerumahan.setLastUpdateWho(userLogin);
            payrollTunjanganPerumahan.setAction("C");
            payrollTunjanganPerumahan.setFlag("Y");

            payrollTunjanganPerumahanBoProxy.saveAdd(payrollTunjanganPerumahan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollTunjanganPerumahanAction.search] start process >>>");

        payrollTunjanganPerumahan searchPayrollTunjanganPerumahan = getPayrollTunjanganPerumahan();
        List<payrollTunjanganPerumahan> listOfsearchPayrollTunjanganPerumahan = new ArrayList();

        try {
            listOfsearchPayrollTunjanganPerumahan = payrollTunjanganPerumahanBoProxy.getByCriteria(searchPayrollTunjanganPerumahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganPerumahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganPerumahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollTunjanganPerumahan);

        logger.info("[PayrollTunjanganPerumahanAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollTunjanganPerumahan() {
        logger.info("[PayrollTunjanganPerumahanAction.search] start process >>>");

        payrollTunjanganPerumahan searchPayrollTunjanganPerumahan = new payrollTunjanganPerumahan();
        searchPayrollTunjanganPerumahan.setFlag("Y");
        List<payrollTunjanganPerumahan> listOfsearchPayrollTunjanganPerumahan = new ArrayList();

        try {
            listOfsearchPayrollTunjanganPerumahan = payrollTunjanganPerumahanBoProxy.getByCriteria(searchPayrollTunjanganPerumahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganPerumahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganPerumahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollTunjanganPerumahan.addAll(listOfsearchPayrollTunjanganPerumahan);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollTunjanganPerumahanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollTunjanganPerumahanAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollTunjanganPerumahan() {
        logger.info("[PayrollTunjanganPerumahanAction.search] start process >>>");

        payrollTunjanganPerumahan searchPayrollTunjanganPerumahan = new payrollTunjanganPerumahan();
        searchPayrollTunjanganPerumahan.setFlag("Y");
        List<payrollTunjanganPerumahan> listOfsearchPayrollTunjanganPerumahan = new ArrayList();

        try {
            listOfsearchPayrollTunjanganPerumahan = payrollTunjanganPerumahanBoProxy.getByCriteria(searchPayrollTunjanganPerumahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganPerumahanBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganPerumahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganPerumahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganPerumahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollTunjanganPerumahan");
        session.setAttribute("listOfResultPayrollTunjanganPerumahan", listOfsearchPayrollTunjanganPerumahan);

        logger.info("[PayrollTunjanganPerumahanAction.search] end process <<<");

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
