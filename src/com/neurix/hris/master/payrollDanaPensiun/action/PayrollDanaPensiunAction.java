package com.neurix.hris.master.payrollDanaPensiun.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollDanaPensiun.bo.PayrollDanaPensiunBo;
import com.neurix.hris.master.payrollDanaPensiun.model.payrollDanaPensiun;
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

public class PayrollDanaPensiunAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollDanaPensiunAction.class);
    private PayrollDanaPensiunBo payrollDanaPensiunBoProxy;
    private payrollDanaPensiun payrollDanaPensiun;

    public payrollDanaPensiun getPayrollDanaPensiun() {
        return payrollDanaPensiun;
    }

    public void setPayrollDanaPensiun(payrollDanaPensiun payrollDanaPensiun) {
        this.payrollDanaPensiun = payrollDanaPensiun;
    }

    public PayrollDanaPensiunBo getPayrollDanaPensiunBoProxy() {
        return payrollDanaPensiunBoProxy;
    }

    public void setPayrollDanaPensiunBoProxy(PayrollDanaPensiunBo payrollDanaPensiunBoProxy) {
        this.payrollDanaPensiunBoProxy = payrollDanaPensiunBoProxy;
    }

    private List<payrollDanaPensiun> listComboPayrollDanaPensiun = new ArrayList<payrollDanaPensiun>();

    public List<payrollDanaPensiun> getListComboPayrollDanaPensiun() {
        return listComboPayrollDanaPensiun;
    }

    public void setListComboPayrollDanaPensiun(List<payrollDanaPensiun> listComboPayrollDanaPensiun) {
        this.listComboPayrollDanaPensiun = listComboPayrollDanaPensiun;
    }

    private List<payrollDanaPensiun> initComboPayrollDanaPensiun;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollDanaPensiunAction.logger = logger;
    }


    public List<payrollDanaPensiun> getInitComboPayrollDanaPensiun() {
        return initComboPayrollDanaPensiun;
    }

    public void setInitComboPayrollDanaPensiun(List<payrollDanaPensiun> initComboPayrollDanaPensiun) {
        this.initComboPayrollDanaPensiun = initComboPayrollDanaPensiun;
    }

    public payrollDanaPensiun init(String kode, String flag){
        logger.info("[PayrollDanaPensiunAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollDanaPensiun> listOfResult = (List<payrollDanaPensiun>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollDanaPensiun payrollDanaPensiun: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollDanaPensiun.getDanaPensiunId()) && flag.equalsIgnoreCase(payrollDanaPensiun.getFlag())){
                        setPayrollDanaPensiun(payrollDanaPensiun);
                        break;
                    }
                }
            } else {
                setPayrollDanaPensiun(new payrollDanaPensiun());
            }

            logger.info("[PayrollDanaPensiunAction.init] end process >>>");
        }
        return getPayrollDanaPensiun();
    }

    @Override
    public String add() {
        logger.info("[PayrollDanaPensiunAction.add] start process >>>");
        payrollDanaPensiun addPayrollDanaPensiun = new payrollDanaPensiun();
        setPayrollDanaPensiun(addPayrollDanaPensiun);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollDanaPensiunAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollDanaPensiunAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollDanaPensiun editPayrollDanaPensiun = new payrollDanaPensiun();

        if(itemFlag != null){
            try {
                editPayrollDanaPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.getPayrollDanaPensiunByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollDanaPensiunAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollDanaPensiunAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollDanaPensiun != null) {
                setPayrollDanaPensiun(editPayrollDanaPensiun);
            } else {
                editPayrollDanaPensiun.setFlag(itemFlag);
                setPayrollDanaPensiun(editPayrollDanaPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollDanaPensiun.setFlag(getFlag());
            setPayrollDanaPensiun(editPayrollDanaPensiun);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollDanaPensiunAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollDanaPensiunAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollDanaPensiun deletePayrollDanaPensiun = new payrollDanaPensiun();

        if (itemFlag != null ) {

            try {
                deletePayrollDanaPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollDanaPensiunAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollDanaPensiunAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollDanaPensiun != null) {
                setPayrollDanaPensiun(deletePayrollDanaPensiun);

            } else {
                deletePayrollDanaPensiun.setFlag(itemFlag);
                setPayrollDanaPensiun(deletePayrollDanaPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollDanaPensiun.setFlag(itemFlag);
            setPayrollDanaPensiun(deletePayrollDanaPensiun);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollDanaPensiunAction.delete] end process <<<");

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
        logger.info("[PayrollDanaPensiunAction.saveEdit] start process >>>");
        try {

            payrollDanaPensiun editPayrollDanaPensiun = getPayrollDanaPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollDanaPensiun.setLastUpdateWho(userLogin);
            editPayrollDanaPensiun.setLastUpdate(updateTime);
            editPayrollDanaPensiun.setAction("U");
            editPayrollDanaPensiun.setFlag("Y");

            payrollDanaPensiunBoProxy.saveEdit(editPayrollDanaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollDanaPensiunAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollDanaPensiunAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollDanaPensiunAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollDanaPensiunAction.saveDelete] start process >>>");
        try {

            payrollDanaPensiun deletePayrollDanaPensiun = getPayrollDanaPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollDanaPensiun.setLastUpdate(updateTime);
            deletePayrollDanaPensiun.setLastUpdateWho(userLogin);
            deletePayrollDanaPensiun.setAction("D");
            deletePayrollDanaPensiun.setFlag("N");

            payrollDanaPensiunBoProxy.saveDelete(deletePayrollDanaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollDanaPensiunAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollDanaPensiunAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollDanaPensiunAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollDanaPensiunAction.saveAdd] start process >>>");

        try {
            payrollDanaPensiun payrollDanaPensiun = getPayrollDanaPensiun();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollDanaPensiun.setCreatedWho(userLogin);
            payrollDanaPensiun.setLastUpdate(updateTime);
            payrollDanaPensiun.setCreatedDate(updateTime);
            payrollDanaPensiun.setLastUpdateWho(userLogin);
            payrollDanaPensiun.setAction("C");
            payrollDanaPensiun.setFlag("Y");

            payrollDanaPensiunBoProxy.saveAdd(payrollDanaPensiun);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollDanaPensiunAction.search] start process >>>");

        payrollDanaPensiun searchPayrollDanaPensiun = getPayrollDanaPensiun();
        List<payrollDanaPensiun> listOfsearchPayrollDanaPensiun = new ArrayList();

        try {
            listOfsearchPayrollDanaPensiun = payrollDanaPensiunBoProxy.getByCriteria(searchPayrollDanaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollDanaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollDanaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollDanaPensiun);

        logger.info("[PayrollDanaPensiunAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollDanaPensiun() {
        logger.info("[PayrollDanaPensiunAction.search] start process >>>");

        payrollDanaPensiun searchPayrollDanaPensiun = new payrollDanaPensiun();
        searchPayrollDanaPensiun.setFlag("Y");
        List<payrollDanaPensiun> listOfsearchPayrollDanaPensiun = new ArrayList();

        try {
            listOfsearchPayrollDanaPensiun = payrollDanaPensiunBoProxy.getByCriteria(searchPayrollDanaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollDanaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollDanaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollDanaPensiun.addAll(listOfsearchPayrollDanaPensiun);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollDanaPensiunAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollDanaPensiunAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollDanaPensiun() {
        logger.info("[PayrollDanaPensiunAction.search] start process >>>");

        payrollDanaPensiun searchPayrollDanaPensiun = new payrollDanaPensiun();
        searchPayrollDanaPensiun.setFlag("Y");
        List<payrollDanaPensiun> listOfsearchPayrollDanaPensiun = new ArrayList();

        try {
            listOfsearchPayrollDanaPensiun = payrollDanaPensiunBoProxy.getByCriteria(searchPayrollDanaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollDanaPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollDanaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollDanaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollDanaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollDanaPensiun");
        session.setAttribute("listOfResultPayrollDanaPensiun", listOfsearchPayrollDanaPensiun);

        logger.info("[PayrollDanaPensiunAction.search] end process <<<");

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
