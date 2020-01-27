package com.neurix.hris.master.payrollSkalaGajiPensiunDapenbun.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiPensiunDapenbun.bo.PayrollSkalaGajiPensiunRniBo;
import com.neurix.hris.master.payrollSkalaGajiPensiunDapenbun.model.payrollSkalaGajiPensiunRni;
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

public class PayrollSkalaGajiPensiunRniAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunRniAction.class);
    private PayrollSkalaGajiPensiunRniBo payrollSkalaGajiPensiunBoProxy;
    private payrollSkalaGajiPensiunRni payrollSkalaGajiPensiun;

    public payrollSkalaGajiPensiunRni getPayrollSkalaGajiPensiun() {
        return payrollSkalaGajiPensiun;
    }

    public void setPayrollSkalaGajiPensiun(payrollSkalaGajiPensiunRni payrollSkalaGajiPensiun) {
        this.payrollSkalaGajiPensiun = payrollSkalaGajiPensiun;
    }

    public PayrollSkalaGajiPensiunRniBo getPayrollSkalaGajiPensiunBoProxy() {
        return payrollSkalaGajiPensiunBoProxy;
    }

    public void setPayrollSkalaGajiPensiunBoProxy(PayrollSkalaGajiPensiunRniBo payrollSkalaGajiPensiunBoProxy) {
        this.payrollSkalaGajiPensiunBoProxy = payrollSkalaGajiPensiunBoProxy;
    }

    private List<payrollSkalaGajiPensiunRni> listComboPayrollSkalaGajiPensiun = new ArrayList<payrollSkalaGajiPensiunRni>();

    public List<payrollSkalaGajiPensiunRni> getListComboPayrollSkalaGajiPensiun() {
        return listComboPayrollSkalaGajiPensiun;
    }

    public void setListComboPayrollSkalaGajiPensiun(List<payrollSkalaGajiPensiunRni> listComboPayrollSkalaGajiPensiun) {
        this.listComboPayrollSkalaGajiPensiun = listComboPayrollSkalaGajiPensiun;
    }

    private List<payrollSkalaGajiPensiunRni> initComboPayrollSkalaGajiPensiun;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPensiunRniAction.logger = logger;
    }


    public List<payrollSkalaGajiPensiunRni> getInitComboPayrollSkalaGajiPensiun() {
        return initComboPayrollSkalaGajiPensiun;
    }

    public void setInitComboPayrollSkalaGajiPensiun(List<payrollSkalaGajiPensiunRni> initComboPayrollSkalaGajiPensiun) {
        this.initComboPayrollSkalaGajiPensiun = initComboPayrollSkalaGajiPensiun;
    }

    public payrollSkalaGajiPensiunRni init(String kode, String flag){
        logger.info("[PayrollSkalaGajiPensiunAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollSkalaGajiPensiunRni> listOfResult = (List<payrollSkalaGajiPensiunRni>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollSkalaGajiPensiunRni payrollSkalaGajiPensiun: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiPensiun.getSkalaGajiPensiunId()) && flag.equalsIgnoreCase(payrollSkalaGajiPensiun.getFlag())){
                        setPayrollSkalaGajiPensiun(payrollSkalaGajiPensiun);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiPensiun(new payrollSkalaGajiPensiunRni());
            }

            logger.info("[PayrollSkalaGajiPensiunAction.init] end process >>>");
        }
        return getPayrollSkalaGajiPensiun();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiPensiunAction.add] start process >>>");
        payrollSkalaGajiPensiunRni addPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiunRni();
        setPayrollSkalaGajiPensiun(addPayrollSkalaGajiPensiun);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiPensiunAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiPensiunAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollSkalaGajiPensiunRni editPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiunRni();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getPayrollSkalaGajiPensiunByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiPensiunAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiPensiunAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGajiPensiun != null) {
                setPayrollSkalaGajiPensiun(editPayrollSkalaGajiPensiun);
            } else {
                editPayrollSkalaGajiPensiun.setFlag(itemFlag);
                setPayrollSkalaGajiPensiun(editPayrollSkalaGajiPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollSkalaGajiPensiun.setFlag(getFlag());
            setPayrollSkalaGajiPensiun(editPayrollSkalaGajiPensiun);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiPensiunAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiPensiunAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollSkalaGajiPensiunRni deletePayrollSkalaGajiPensiun = new payrollSkalaGajiPensiunRni();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGajiPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiPensiunAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiPensiunAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGajiPensiun != null) {
                setPayrollSkalaGajiPensiun(deletePayrollSkalaGajiPensiun);

            } else {
                deletePayrollSkalaGajiPensiun.setFlag(itemFlag);
                setPayrollSkalaGajiPensiun(deletePayrollSkalaGajiPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollSkalaGajiPensiun.setFlag(itemFlag);
            setPayrollSkalaGajiPensiun(deletePayrollSkalaGajiPensiun);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiPensiunAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiPensiunAction.saveEdit] start process >>>");
        try {

            payrollSkalaGajiPensiunRni editPayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGajiPensiun.setLastUpdateWho(userLogin);
            editPayrollSkalaGajiPensiun.setLastUpdate(updateTime);
            editPayrollSkalaGajiPensiun.setAction("U");
            editPayrollSkalaGajiPensiun.setFlag("Y");

            payrollSkalaGajiPensiunBoProxy.saveEdit(editPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPensiunAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPensiunAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiPensiunAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiPensiunAction.saveDelete] start process >>>");
        try {

            payrollSkalaGajiPensiunRni deletePayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGajiPensiun.setLastUpdate(updateTime);
            deletePayrollSkalaGajiPensiun.setLastUpdateWho(userLogin);
            deletePayrollSkalaGajiPensiun.setAction("D");
            deletePayrollSkalaGajiPensiun.setFlag("N");

            payrollSkalaGajiPensiunBoProxy.saveDelete(deletePayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPensiunAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPensiunAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiPensiunAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiPensiunAction.saveAdd] start process >>>");

        try {
            payrollSkalaGajiPensiunRni payrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollSkalaGajiPensiun.setCreatedWho(userLogin);
            payrollSkalaGajiPensiun.setLastUpdate(updateTime);
            payrollSkalaGajiPensiun.setCreatedDate(updateTime);
            payrollSkalaGajiPensiun.setLastUpdateWho(userLogin);
            payrollSkalaGajiPensiun.setAction("C");
            payrollSkalaGajiPensiun.setFlag("Y");

            payrollSkalaGajiPensiunBoProxy.saveAdd(payrollSkalaGajiPensiun);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollSkalaGajiPensiunAction.search] start process >>>");

        payrollSkalaGajiPensiunRni searchPayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();
        List<payrollSkalaGajiPensiunRni> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGajiPensiun);

        logger.info("[PayrollSkalaGajiPensiunAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiPensiunAction.search] start process >>>");

        payrollSkalaGajiPensiunRni searchPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiunRni();
        searchPayrollSkalaGajiPensiun.setFlag("Y");
        List<payrollSkalaGajiPensiunRni> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiPensiun.addAll(listOfsearchPayrollSkalaGajiPensiun);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiPensiunAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiPensiunAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiPensiunAction.search] start process >>>");

        payrollSkalaGajiPensiunRni searchPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiunRni();
        searchPayrollSkalaGajiPensiun.setFlag("Y");
        List<payrollSkalaGajiPensiunRni> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGajiPensiun");
        session.setAttribute("listOfResultPayrollSkalaGajiPensiun", listOfsearchPayrollSkalaGajiPensiun);

        logger.info("[PayrollSkalaGajiPensiunAction.search] end process <<<");

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
