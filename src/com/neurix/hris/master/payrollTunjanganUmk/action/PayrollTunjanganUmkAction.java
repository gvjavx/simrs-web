package com.neurix.hris.master.payrollTunjanganUmk.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollTunjanganUmk.bo.PayrollTunjanganUmkBo;
import com.neurix.hris.master.payrollTunjanganUmk.model.payrollTunjanganUmk;
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

public class PayrollTunjanganUmkAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganUmkAction.class);
    private PayrollTunjanganUmkBo payrollTunjanganUmkBoProxy;
    private payrollTunjanganUmk payrollTunjanganUmk;

    public payrollTunjanganUmk getPayrollTunjanganUmk() {
        return payrollTunjanganUmk;
    }

    public void setPayrollTunjanganUmk(payrollTunjanganUmk payrollTunjanganUmk) {
        this.payrollTunjanganUmk = payrollTunjanganUmk;
    }

    public PayrollTunjanganUmkBo getPayrollTunjanganUmkBoProxy() {
        return payrollTunjanganUmkBoProxy;
    }

    public void setPayrollTunjanganUmkBoProxy(PayrollTunjanganUmkBo payrollTunjanganUmkBoProxy) {
        this.payrollTunjanganUmkBoProxy = payrollTunjanganUmkBoProxy;
    }

    private List<payrollTunjanganUmk> listComboPayrollTunjanganUmk = new ArrayList<payrollTunjanganUmk>();

    public List<payrollTunjanganUmk> getListComboPayrollTunjanganUmk() {
        return listComboPayrollTunjanganUmk;
    }

    public void setListComboPayrollTunjanganUmk(List<payrollTunjanganUmk> listComboPayrollTunjanganUmk) {
        this.listComboPayrollTunjanganUmk = listComboPayrollTunjanganUmk;
    }

    private List<payrollTunjanganUmk> initComboPayrollTunjanganUmk;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganUmkAction.logger = logger;
    }


    public List<payrollTunjanganUmk> getInitComboPayrollTunjanganUmk() {
        return initComboPayrollTunjanganUmk;
    }

    public void setInitComboPayrollTunjanganUmk(List<payrollTunjanganUmk> initComboPayrollTunjanganUmk) {
        this.initComboPayrollTunjanganUmk = initComboPayrollTunjanganUmk;
    }

    public payrollTunjanganUmk init(String kode, String flag){
        logger.info("[PayrollTunjanganUmkAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollTunjanganUmk> listOfResult = (List<payrollTunjanganUmk>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollTunjanganUmk payrollTunjanganUmk: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollTunjanganUmk.getUmkId()) && flag.equalsIgnoreCase(payrollTunjanganUmk.getFlag())){
                        setPayrollTunjanganUmk(payrollTunjanganUmk);
                        break;
                    }
                }
            } else {
                setPayrollTunjanganUmk(new payrollTunjanganUmk());
            }

            logger.info("[PayrollTunjanganUmkAction.init] end process >>>");
        }
        return getPayrollTunjanganUmk();
    }

    @Override
    public String add() {
        logger.info("[PayrollTunjanganUmkAction.add] start process >>>");
        payrollTunjanganUmk addPayrollTunjanganUmk = new payrollTunjanganUmk();
        setPayrollTunjanganUmk(addPayrollTunjanganUmk);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollTunjanganUmkAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollTunjanganUmkAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollTunjanganUmk editPayrollTunjanganUmk = new payrollTunjanganUmk();

        if(itemFlag != null){
            try {
                editPayrollTunjanganUmk = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.getPayrollTunjanganUmkByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganUmkAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollTunjanganUmkAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollTunjanganUmk != null) {
                setPayrollTunjanganUmk(editPayrollTunjanganUmk);
            } else {
                editPayrollTunjanganUmk.setFlag(itemFlag);
                setPayrollTunjanganUmk(editPayrollTunjanganUmk);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollTunjanganUmk.setFlag(getFlag());
            setPayrollTunjanganUmk(editPayrollTunjanganUmk);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollTunjanganUmkAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollTunjanganUmkAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollTunjanganUmk deletePayrollTunjanganUmk = new payrollTunjanganUmk();

        if (itemFlag != null ) {

            try {
                deletePayrollTunjanganUmk = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganUmkAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollTunjanganUmkAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollTunjanganUmk != null) {
                setPayrollTunjanganUmk(deletePayrollTunjanganUmk);

            } else {
                deletePayrollTunjanganUmk.setFlag(itemFlag);
                setPayrollTunjanganUmk(deletePayrollTunjanganUmk);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollTunjanganUmk.setFlag(itemFlag);
            setPayrollTunjanganUmk(deletePayrollTunjanganUmk);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollTunjanganUmkAction.delete] end process <<<");

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
        logger.info("[PayrollTunjanganUmkAction.saveEdit] start process >>>");
        try {

            payrollTunjanganUmk editPayrollTunjanganUmk = getPayrollTunjanganUmk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollTunjanganUmk.setLastUpdateWho(userLogin);
            editPayrollTunjanganUmk.setLastUpdate(updateTime);
            editPayrollTunjanganUmk.setAction("U");
            editPayrollTunjanganUmk.setFlag("Y");

            payrollTunjanganUmkBoProxy.saveEdit(editPayrollTunjanganUmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganUmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganUmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganUmkAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollTunjanganUmkAction.saveDelete] start process >>>");
        try {

            payrollTunjanganUmk deletePayrollTunjanganUmk = getPayrollTunjanganUmk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollTunjanganUmk.setLastUpdate(updateTime);
            deletePayrollTunjanganUmk.setLastUpdateWho(userLogin);
            deletePayrollTunjanganUmk.setAction("D");
            deletePayrollTunjanganUmk.setFlag("N");

            payrollTunjanganUmkBoProxy.saveDelete(deletePayrollTunjanganUmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganUmkAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganUmkAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganUmkAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollTunjanganUmkAction.saveAdd] start process >>>");

        try {
            payrollTunjanganUmk payrollTunjanganUmk = getPayrollTunjanganUmk();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollTunjanganUmk.setCreatedWho(userLogin);
            payrollTunjanganUmk.setLastUpdate(updateTime);
            payrollTunjanganUmk.setCreatedDate(updateTime);
            payrollTunjanganUmk.setLastUpdateWho(userLogin);
            payrollTunjanganUmk.setAction("C");
            payrollTunjanganUmk.setFlag("Y");

            payrollTunjanganUmkBoProxy.saveAdd(payrollTunjanganUmk);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollTunjanganUmkAction.search] start process >>>");

        payrollTunjanganUmk searchPayrollTunjanganUmk = getPayrollTunjanganUmk();
        List<payrollTunjanganUmk> listOfsearchPayrollTunjanganUmk = new ArrayList();

        try {
            listOfsearchPayrollTunjanganUmk = payrollTunjanganUmkBoProxy.getByCriteria(searchPayrollTunjanganUmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganUmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganUmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollTunjanganUmk);

        logger.info("[PayrollTunjanganUmkAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollTunjanganUmk() {
        logger.info("[PayrollTunjanganUmkAction.search] start process >>>");

        payrollTunjanganUmk searchPayrollTunjanganUmk = new payrollTunjanganUmk();
        searchPayrollTunjanganUmk.setFlag("Y");
        List<payrollTunjanganUmk> listOfsearchPayrollTunjanganUmk = new ArrayList();

        try {
            listOfsearchPayrollTunjanganUmk = payrollTunjanganUmkBoProxy.getByCriteria(searchPayrollTunjanganUmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganUmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganUmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollTunjanganUmk.addAll(listOfsearchPayrollTunjanganUmk);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollTunjanganUmkAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollTunjanganUmkAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollTunjanganUmk() {
        logger.info("[PayrollTunjanganUmkAction.search] start process >>>");

        payrollTunjanganUmk searchPayrollTunjanganUmk = new payrollTunjanganUmk();
        searchPayrollTunjanganUmk.setFlag("Y");
        List<payrollTunjanganUmk> listOfsearchPayrollTunjanganUmk = new ArrayList();

        try {
            listOfsearchPayrollTunjanganUmk = payrollTunjanganUmkBoProxy.getByCriteria(searchPayrollTunjanganUmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganUmkBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganUmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganUmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganUmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollTunjanganUmk");
        session.setAttribute("listOfResultPayrollTunjanganUmk", listOfsearchPayrollTunjanganUmk);

        logger.info("[PayrollTunjanganUmkAction.search] end process <<<");

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
