package com.neurix.hris.master.payrollBpjs.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollBpjs.bo.PayrollBpjsBo;
import com.neurix.hris.transaksi.payroll.model.PayrollBpjs;
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

public class PayrollBpjsAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollBpjsAction.class);
    private PayrollBpjsBo payrollBpjsBoProxy;
    private PayrollBpjs payrollBpjs;

    public PayrollBpjs getPayrollBpjs() {
        return payrollBpjs;
    }

    public void setPayrollBpjs(PayrollBpjs payrollBpjs) {
        this.payrollBpjs = payrollBpjs;
    }

    public PayrollBpjsBo getPayrollBpjsBoProxy() {
        return payrollBpjsBoProxy;
    }

    public void setPayrollBpjsBoProxy(PayrollBpjsBo payrollBpjsBoProxy) {
        this.payrollBpjsBoProxy = payrollBpjsBoProxy;
    }

    private List<PayrollBpjs> listComboPayrollBpjs = new ArrayList<PayrollBpjs>();

    public List<PayrollBpjs> getListComboPayrollBpjs() {
        return listComboPayrollBpjs;
    }

    public void setListComboPayrollBpjs(List<PayrollBpjs> listComboPayrollBpjs) {
        this.listComboPayrollBpjs = listComboPayrollBpjs;
    }

    private List<PayrollBpjs> initComboPayrollBpjs;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollBpjsAction.logger = logger;
    }


    public List<PayrollBpjs> getInitComboPayrollBpjs() {
        return initComboPayrollBpjs;
    }

    public void setInitComboPayrollBpjs(List<PayrollBpjs> initComboPayrollBpjs) {
        this.initComboPayrollBpjs = initComboPayrollBpjs;
    }

    public PayrollBpjs init(String kode, String flag){
        logger.info("[PayrollBpjsAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollBpjs> listOfResult = (List<PayrollBpjs>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollBpjs payrollBpjs: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollBpjs.getBpjsId()) && flag.equalsIgnoreCase(payrollBpjs.getFlag())){
                        setPayrollBpjs(payrollBpjs);
                        break;
                    }
                }
            } else {
                setPayrollBpjs(new PayrollBpjs());
            }

            logger.info("[PayrollBpjsAction.init] end process >>>");
        }
        return getPayrollBpjs();
    }

    @Override
    public String add() {
        logger.info("[PayrollBpjsAction.add] start process >>>");
        PayrollBpjs addPayrollBpjs = new PayrollBpjs();
        setPayrollBpjs(addPayrollBpjs);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PayrollBpjsAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollBpjsAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollBpjs editPayrollBpjs = new PayrollBpjs();

        if(itemFlag != null){
            try {
                editPayrollBpjs = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.getPayrollBpjsByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollBpjsAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollBpjsAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollBpjs != null) {
                setPayrollBpjs(editPayrollBpjs);
            } else {
                editPayrollBpjs.setFlag(itemFlag);
                setPayrollBpjs(editPayrollBpjs);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollBpjs.setFlag(getFlag());
            setPayrollBpjs(editPayrollBpjs);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollBpjsAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollBpjsAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollBpjs deletePayrollBpjs = new PayrollBpjs();

        if (itemFlag != null ) {

            try {
                deletePayrollBpjs = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollBpjsAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollBpjsAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollBpjs != null) {
                setPayrollBpjs(deletePayrollBpjs);

            } else {
                deletePayrollBpjs.setFlag(itemFlag);
                setPayrollBpjs(deletePayrollBpjs);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollBpjs.setFlag(itemFlag);
            setPayrollBpjs(deletePayrollBpjs);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollBpjsAction.delete] end process <<<");

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
        logger.info("[PayrollBpjsAction.saveEdit] start process >>>");
        try {

            PayrollBpjs editPayrollBpjs = getPayrollBpjs();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollBpjs.setLastUpdateWho(userLogin);
            editPayrollBpjs.setLastUpdate(updateTime);
            editPayrollBpjs.setAction("U");
            editPayrollBpjs.setFlag("Y");

            payrollBpjsBoProxy.saveEdit(editPayrollBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBpjsAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBpjsAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollBpjsAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollBpjsAction.saveDelete] start process >>>");
        try {

            PayrollBpjs deletePayrollBpjs = getPayrollBpjs();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollBpjs.setLastUpdate(updateTime);
            deletePayrollBpjs.setLastUpdateWho(userLogin);
            deletePayrollBpjs.setAction("D");
            deletePayrollBpjs.setFlag("N");

            payrollBpjsBoProxy.saveDelete(deletePayrollBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBpjsAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBpjsAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollBpjsAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollBpjsAction.saveAdd] start process >>>");

        try {
            PayrollBpjs payrollBpjs = getPayrollBpjs();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollBpjs.setCreatedWho(userLogin);
            payrollBpjs.setLastUpdate(updateTime);
            payrollBpjs.setCreatedDate(updateTime);
            payrollBpjs.setLastUpdateWho(userLogin);
            payrollBpjs.setAction("C");
            payrollBpjs.setFlag("Y");

            payrollBpjsBoProxy.saveAdd(payrollBpjs);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollBpjsAction.search] start process >>>");

        PayrollBpjs searchPayrollBpjs = getPayrollBpjs();
        List<PayrollBpjs> listOfsearchPayrollBpjs = new ArrayList();

        try {
            listOfsearchPayrollBpjs = payrollBpjsBoProxy.getByCriteria(searchPayrollBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollBpjs);

        logger.info("[PayrollBpjsAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollBpjs() {
        logger.info("[PayrollBpjsAction.search] start process >>>");

        PayrollBpjs searchPayrollBpjs = getPayrollBpjs();
        List<PayrollBpjs> listOfsearchPayrollBpjs = new ArrayList();

        try {
            listOfsearchPayrollBpjs = payrollBpjsBoProxy.getByCriteria(searchPayrollBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollBpjs.addAll(listOfsearchPayrollBpjs);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollBpjsAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollBpjsAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollBpjs() {
        logger.info("[PayrollBpjsAction.search] start process >>>");

        PayrollBpjs searchPayrollBpjs = new PayrollBpjs();
        searchPayrollBpjs.setFlag("Y");
        List<PayrollBpjs> listOfsearchPayrollBpjs = new ArrayList();

        try {
            listOfsearchPayrollBpjs = payrollBpjsBoProxy.getByCriteria(searchPayrollBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollBpjs");
        session.setAttribute("listOfResultPayrollBpjs", listOfsearchPayrollBpjs);

        logger.info("[PayrollBpjsAction.search] end process <<<");

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
