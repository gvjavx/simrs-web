package com.neurix.hris.master.payrollParamBpjs.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollParamBpjs.bo.PayrollParamBpjsBo;
import com.neurix.hris.master.payrollParamBpjs.model.PayrollParamBpjs;
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

public class PayrollParamBpjsAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollParamBpjsAction.class);
    private PayrollParamBpjsBo payrollParamBpjsBoProxy;
    private PayrollParamBpjs payrollParamBpjs;
    private List<PayrollParamBpjs> listComboPayrollParamBpjs = new ArrayList<PayrollParamBpjs>();

    public List<PayrollParamBpjs> getListComboPayrollParamBpjs() {
        return listComboPayrollParamBpjs;
    }

    public void setListComboPayrollParamBpjs(List<PayrollParamBpjs> listComboPayrollParamBpjs) {
        this.listComboPayrollParamBpjs = listComboPayrollParamBpjs;
    }

    public PayrollParamBpjsBo getPayrollParamBpjsBoProxy() {
        return payrollParamBpjsBoProxy;
    }

    public void setPayrollParamBpjsBoProxy(PayrollParamBpjsBo payrollParamBpjsBoProxy) {
        this.payrollParamBpjsBoProxy = payrollParamBpjsBoProxy;
    }

    public PayrollParamBpjs getPayrollParamBpjs() {
        return payrollParamBpjs;
    }

    public void setPayrollParamBpjs(PayrollParamBpjs payrollParamBpjs) {
        this.payrollParamBpjs = payrollParamBpjs;
    }

    private List<PayrollParamBpjs> initComboPayrollParamBpjs;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollParamBpjsAction.logger = logger;
    }


    public List<PayrollParamBpjs> getInitComboPayrollParamBpjs() {
        return initComboPayrollParamBpjs;
    }

    public void setInitComboPayrollParamBpjs(List<PayrollParamBpjs> initComboPayrollParamBpjs) {
        this.initComboPayrollParamBpjs = initComboPayrollParamBpjs;
    }

    public PayrollParamBpjs init(String kode, String flag){
        logger.info("[PayrollParamBpjsAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollParamBpjs> listOfResult = (List<PayrollParamBpjs>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollParamBpjs payrollParamBpjs: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollParamBpjs.getPayrollParamBpjsId()) && flag.equalsIgnoreCase(payrollParamBpjs.getFlag())){
                        setPayrollParamBpjs(payrollParamBpjs);
                        break;
                    }
                }
            } else {
                setPayrollParamBpjs(new PayrollParamBpjs());
            }

            logger.info("[PayrollParamBpjsAction.init] end process >>>");
        }
        return getPayrollParamBpjs();
    }

    @Override
    public String add() {
        logger.info("[PayrollParamBpjsAction.add] start process >>>");
        PayrollParamBpjs addPayrollParamBpjs = new PayrollParamBpjs();
        setPayrollParamBpjs(addPayrollParamBpjs);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollParamBpjsAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollParamBpjsAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollParamBpjs editPayrollParamBpjs = new PayrollParamBpjs();

        if(itemFlag != null){
            try {
                editPayrollParamBpjs = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getPayrollParamBpjsByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollParamBpjsAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollParamBpjsAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollParamBpjs != null) {
                setPayrollParamBpjs(editPayrollParamBpjs);
            } else {
                editPayrollParamBpjs.setFlag(itemFlag);
                //editPayrollParamBpjs.getSkalaGajiId(itemId);
                setPayrollParamBpjs(editPayrollParamBpjs);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollParamBpjs.getSkalaGajiId(itemId);
            editPayrollParamBpjs.setFlag(getFlag());
            setPayrollParamBpjs(editPayrollParamBpjs);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollParamBpjsAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollParamBpjsAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        PayrollParamBpjs deletePayrollParamBpjs = new PayrollParamBpjs();
        if (itemFlag != null ) {
            try {
                deletePayrollParamBpjs = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollParamBpjsAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollParamBpjsAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollParamBpjs != null) {
                setPayrollParamBpjs(deletePayrollParamBpjs);

            } else {
                //deletePayrollParamBpjs.getSkalaGajiId(itemId);
                deletePayrollParamBpjs.setFlag(itemFlag);
                setPayrollParamBpjs(deletePayrollParamBpjs);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollParamBpjs.getSkalaGajiId(itemId);
            deletePayrollParamBpjs.setFlag(itemFlag);
            setPayrollParamBpjs(deletePayrollParamBpjs);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollParamBpjsAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PayrollParamBpjsAction.view] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        PayrollParamBpjs deletePayrollParamBpjs = new PayrollParamBpjs();

        if (itemFlag != null ) {
            try {
                deletePayrollParamBpjs = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollParamBpjsAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollParamBpjsAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollParamBpjs != null) {
                setPayrollParamBpjs(deletePayrollParamBpjs);

            } else {
                deletePayrollParamBpjs.setPayrollParamBpjsId(itemId);
                deletePayrollParamBpjs.setFlag(itemFlag);
                setPayrollParamBpjs(deletePayrollParamBpjs);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollParamBpjs.setPayrollParamBpjsId(itemId);
            deletePayrollParamBpjs.setFlag(itemFlag);
            setPayrollParamBpjs(deletePayrollParamBpjs);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollParamBpjsAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PayrollParamBpjsAction.saveEdit] start process >>>");
        try {

            PayrollParamBpjs editPayrollParamBpjs = getPayrollParamBpjs();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            editPayrollParamBpjs.setLastUpdateWho(userLogin);
            editPayrollParamBpjs.setLastUpdate(updateTime);
            editPayrollParamBpjs.setAction("U");
            editPayrollParamBpjs.setFlag("Y");

            payrollParamBpjsBoProxy.saveEdit(editPayrollParamBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollParamBpjsAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollParamBpjsAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollParamBpjsAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollParamBpjsAction.saveDelete] start process >>>");
        try {

            PayrollParamBpjs deletePayrollParamBpjs = getPayrollParamBpjs();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollParamBpjs.setLastUpdate(updateTime);
            deletePayrollParamBpjs.setLastUpdateWho(userLogin);
            deletePayrollParamBpjs.setAction("U");
            deletePayrollParamBpjs.setFlag("N");

            payrollParamBpjsBoProxy.saveDelete(deletePayrollParamBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollParamBpjsAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollParamBpjsAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollParamBpjsAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollParamBpjsAction.saveAdd] start process >>>");

        try {
            PayrollParamBpjs payrollParamBpjs = getPayrollParamBpjs();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollParamBpjs.setCreatedWho(userLogin);
            payrollParamBpjs.setLastUpdate(updateTime);
            payrollParamBpjs.setCreatedDate(updateTime);
            payrollParamBpjs.setLastUpdateWho(userLogin);
            payrollParamBpjs.setAction("C");
            payrollParamBpjs.setFlag("Y");

            payrollParamBpjsBoProxy.saveAdd(payrollParamBpjs);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "payrollParamBpjsBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollParamBpjsAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[payrollParamBpjsAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollParamBpjsAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollParamBpjsAction.search] start process >>>");

        PayrollParamBpjs searchPayrollParamBpjs = getPayrollParamBpjs();
        List<PayrollParamBpjs> listOfsearchPayrollParamBpjs = new ArrayList();

        try {
            listOfsearchPayrollParamBpjs = payrollParamBpjsBoProxy.getByCriteria(searchPayrollParamBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollParamBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollParamBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollParamBpjs);

        logger.info("[PayrollParamBpjsAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollParamBpjs() {
        logger.info("[PayrollParamBpjsAction.search] start process >>>");

        PayrollParamBpjs searchPayrollParamBpjs = new PayrollParamBpjs();
        searchPayrollParamBpjs.setFlag("Y");
        List<PayrollParamBpjs> listOfsearchPayrollParamBpjs = new ArrayList();

        try {
            listOfsearchPayrollParamBpjs = payrollParamBpjsBoProxy.getByCriteria(searchPayrollParamBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollParamBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollParamBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollParamBpjs.addAll(listOfsearchPayrollParamBpjs);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollParamBpjsAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollParamBpjsAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollParamBpjs() {
        logger.info("[PayrollParamBpjsAction.search] start process >>>");

        PayrollParamBpjs searchPayrollParamBpjs = new PayrollParamBpjs();
        searchPayrollParamBpjs.setFlag("Y");
        List<PayrollParamBpjs> listOfsearchPayrollParamBpjs = new ArrayList();

        try {
            listOfsearchPayrollParamBpjs = payrollParamBpjsBoProxy.getByCriteria(searchPayrollParamBpjs);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollParamBpjsBoProxy.saveErrorMessage(e.getMessage(), "PayrollParamBpjsBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollParamBpjsAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollParamBpjsAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollParamBpjs");
        session.setAttribute("listOfResultPayrollParamBpjs", listOfsearchPayrollParamBpjs);

        logger.info("[PayrollParamBpjsAction.search] end process <<<");

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
