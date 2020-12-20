package com.neurix.hris.master.payrollSkalaGajiIuranDplk.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.bo.PayrollSkalaGajiIuranDplkBo;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.model.payrollSkalaGajiIuranDplk;
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

public class PayrollSkalaGajiIuranDplkAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiIuranDplkAction.class);
    private PayrollSkalaGajiIuranDplkBo payrollSkalaGajiIuranDplkBoProxy;
    private payrollSkalaGajiIuranDplk payrollSkalaGajiIuranDplk;

    public payrollSkalaGajiIuranDplk getPayrollSkalaGajiIuranDplk() {
        return payrollSkalaGajiIuranDplk;
    }

    public void setPayrollSkalaGajiIuranDplk(payrollSkalaGajiIuranDplk payrollSkalaGajiIuranDplk) {
        this.payrollSkalaGajiIuranDplk = payrollSkalaGajiIuranDplk;
    }

    public PayrollSkalaGajiIuranDplkBo getPayrollSkalaGajiIuranDplkBoProxy() {
        return payrollSkalaGajiIuranDplkBoProxy;
    }

    public void setPayrollSkalaGajiIuranDplkBoProxy(PayrollSkalaGajiIuranDplkBo payrollSkalaGajiIuranDplkBoProxy) {
        this.payrollSkalaGajiIuranDplkBoProxy = payrollSkalaGajiIuranDplkBoProxy;
    }

    private List<payrollSkalaGajiIuranDplk> listComboPayrollSkalaGajiIuranDplk = new ArrayList<payrollSkalaGajiIuranDplk>();

    public List<payrollSkalaGajiIuranDplk> getListComboPayrollSkalaGajiIuranDplk() {
        return listComboPayrollSkalaGajiIuranDplk;
    }

    public void setListComboPayrollSkalaGajiIuranDplk(List<payrollSkalaGajiIuranDplk> listComboPayrollSkalaGajiIuranDplk) {
        this.listComboPayrollSkalaGajiIuranDplk = listComboPayrollSkalaGajiIuranDplk;
    }

    private List<payrollSkalaGajiIuranDplk> initComboPayrollSkalaGajiIuranDplk;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiIuranDplkAction.logger = logger;
    }


    public List<payrollSkalaGajiIuranDplk> getInitComboPayrollSkalaGajiIuranDplk() {
        return initComboPayrollSkalaGajiIuranDplk;
    }

    public void setInitComboPayrollSkalaGajiIuranDplk(List<payrollSkalaGajiIuranDplk> initComboPayrollSkalaGajiIuranDplk) {
        this.initComboPayrollSkalaGajiIuranDplk = initComboPayrollSkalaGajiIuranDplk;
    }

    public payrollSkalaGajiIuranDplk init(String kode, String flag){
        logger.info("[PayrollSkalaGajiIuranDplkAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollSkalaGajiIuranDplk> listOfResult = (List<payrollSkalaGajiIuranDplk>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollSkalaGajiIuranDplk payrollSkalaGajiIuranDplk: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiIuranDplk.getSkalaGajiIuranDplkId()) && flag.equalsIgnoreCase(payrollSkalaGajiIuranDplk.getFlag())){
                        setPayrollSkalaGajiIuranDplk(payrollSkalaGajiIuranDplk);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiIuranDplk(new payrollSkalaGajiIuranDplk());
            }

            logger.info("[PayrollSkalaGajiIuranDplkAction.init] end process >>>");
        }
        return getPayrollSkalaGajiIuranDplk();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.add] start process >>>");
        payrollSkalaGajiIuranDplk addPayrollSkalaGajiIuranDplk = new payrollSkalaGajiIuranDplk();
        setPayrollSkalaGajiIuranDplk(addPayrollSkalaGajiIuranDplk);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiIuranDplkAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollSkalaGajiIuranDplk editPayrollSkalaGajiIuranDplk = new payrollSkalaGajiIuranDplk();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiIuranDplk = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.getPayrollSkalaGajiIuranDplkByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiIuranDplkAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiIuranDplkAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGajiIuranDplk != null) {
                setPayrollSkalaGajiIuranDplk(editPayrollSkalaGajiIuranDplk);
            } else {
                editPayrollSkalaGajiIuranDplk.setFlag(itemFlag);
                setPayrollSkalaGajiIuranDplk(editPayrollSkalaGajiIuranDplk);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollSkalaGajiIuranDplk.setFlag(getFlag());
            setPayrollSkalaGajiIuranDplk(editPayrollSkalaGajiIuranDplk);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiIuranDplkAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollSkalaGajiIuranDplk deletePayrollSkalaGajiIuranDplk = new payrollSkalaGajiIuranDplk();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGajiIuranDplk = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.init");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiIuranDplkAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiIuranDplkAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGajiIuranDplk != null) {
                setPayrollSkalaGajiIuranDplk(deletePayrollSkalaGajiIuranDplk);

            } else {
                deletePayrollSkalaGajiIuranDplk.setFlag(itemFlag);
                setPayrollSkalaGajiIuranDplk(deletePayrollSkalaGajiIuranDplk);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollSkalaGajiIuranDplk.setFlag(itemFlag);
            setPayrollSkalaGajiIuranDplk(deletePayrollSkalaGajiIuranDplk);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiIuranDplkAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiIuranDplkAction.saveEdit] start process >>>");
        try {

            payrollSkalaGajiIuranDplk editPayrollSkalaGajiIuranDplk = getPayrollSkalaGajiIuranDplk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGajiIuranDplk.setLastUpdateWho(userLogin);
            editPayrollSkalaGajiIuranDplk.setLastUpdate(updateTime);
            editPayrollSkalaGajiIuranDplk.setAction("U");
            editPayrollSkalaGajiIuranDplk.setFlag("Y");

            payrollSkalaGajiIuranDplkBoProxy.saveEdit(editPayrollSkalaGajiIuranDplk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiIuranDplkAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiIuranDplkAction.saveDelete] start process >>>");
        try {

            payrollSkalaGajiIuranDplk deletePayrollSkalaGajiIuranDplk = getPayrollSkalaGajiIuranDplk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGajiIuranDplk.setLastUpdate(updateTime);
            deletePayrollSkalaGajiIuranDplk.setLastUpdateWho(userLogin);
            deletePayrollSkalaGajiIuranDplk.setAction("D");
            deletePayrollSkalaGajiIuranDplk.setFlag("N");

            payrollSkalaGajiIuranDplkBoProxy.saveDelete(deletePayrollSkalaGajiIuranDplk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiIuranDplkAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiIuranDplkAction.saveAdd] start process >>>");

        try {
            payrollSkalaGajiIuranDplk payrollSkalaGajiIuranDplk = getPayrollSkalaGajiIuranDplk();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollSkalaGajiIuranDplk.setCreatedWho(userLogin);
            payrollSkalaGajiIuranDplk.setLastUpdate(updateTime);
            payrollSkalaGajiIuranDplk.setCreatedDate(updateTime);
            payrollSkalaGajiIuranDplk.setLastUpdateWho(userLogin);
            payrollSkalaGajiIuranDplk.setAction("C");
            payrollSkalaGajiIuranDplk.setFlag("Y");

            payrollSkalaGajiIuranDplkBoProxy.saveAdd(payrollSkalaGajiIuranDplk);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "payrollSkalaGajiIuranDplkBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiIuranDplkAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.search] start process >>>");

        payrollSkalaGajiIuranDplk searchPayrollSkalaGajiIuranDplk = getPayrollSkalaGajiIuranDplk();
        List<payrollSkalaGajiIuranDplk> listOfsearchPayrollSkalaGajiIuranDplk = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiIuranDplk = payrollSkalaGajiIuranDplkBoProxy.getByCriteria(searchPayrollSkalaGajiIuranDplk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGajiIuranDplk);

        logger.info("[PayrollSkalaGajiIuranDplkAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiIuranDplk() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.search] start process >>>");

        payrollSkalaGajiIuranDplk searchPayrollSkalaGajiIuranDplk = getPayrollSkalaGajiIuranDplk();

        searchPayrollSkalaGajiIuranDplk.setFlag("Y");
        List<payrollSkalaGajiIuranDplk> listOfsearchPayrollSkalaGajiIuranDplk = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiIuranDplk = payrollSkalaGajiIuranDplkBoProxy.getByCriteria(searchPayrollSkalaGajiIuranDplk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.save] Error when searching Skala Gaji Iuran Dplk by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiIuranDplk.addAll(listOfsearchPayrollSkalaGajiIuranDplk);
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listComboPayrollSkalaGajiIuranDplk);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiIuranDplkAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollSkalaGajiIuranDplk() {
        logger.info("[PayrollSkalaGajiIuranDplkAction.search] start process >>>");

        payrollSkalaGajiIuranDplk searchPayrollSkalaGajiIuranDplk = new payrollSkalaGajiIuranDplk();
        searchPayrollSkalaGajiIuranDplk.setFlag("Y");
        List<payrollSkalaGajiIuranDplk> listOfsearchPayrollSkalaGajiIuranDplk = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiIuranDplk = payrollSkalaGajiIuranDplkBoProxy.getByCriteria(searchPayrollSkalaGajiIuranDplk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiIuranDplkBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiIuranDplkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiIuranDplkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiIuranDplkAction.save] Error when searching Skala Gaji Iuran Dplk by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGajiIuranDplk");
        session.setAttribute("listOfResultPayrollSkalaGajiIuranDplk", listOfsearchPayrollSkalaGajiIuranDplk);

        logger.info("[PayrollSkalaGajiIuranDplkAction.search] end process <<<");

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
