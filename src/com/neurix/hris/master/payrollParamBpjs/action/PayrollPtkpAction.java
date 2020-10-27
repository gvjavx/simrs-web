package com.neurix.hris.master.payrollParamBpjs.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollParamBpjs.bo.PayrollPtkpBo;
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

public class PayrollPtkpAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollPtkpAction.class);
    private PayrollPtkpBo payrollPtkpBoProxy;
    private PayrollPtkp payrollPtkp;

    private List<PayrollPtkp> listComboPayrollPtkp = new ArrayList<PayrollPtkp>();

    public List<PayrollPtkp> getListComboPayrollPtkp() {
        return listComboPayrollPtkp;
    }

    public void setListComboPayrollPtkp(List<PayrollPtkp> listComboPayrollPtkp) {
        this.listComboPayrollPtkp = listComboPayrollPtkp;
    }

    public PayrollPtkpBo getPayrollPtkpBoProxy() {
        return payrollPtkpBoProxy;
    }

    public void setPayrollPtkpBoProxy(PayrollPtkpBo payrollPtkpBoProxy) {
        this.payrollPtkpBoProxy = payrollPtkpBoProxy;
    }

    public PayrollPtkp getPayrollPtkp() {
        return payrollPtkp;
    }

    public void setPayrollPtkp(PayrollPtkp payrollPtkp) {
        this.payrollPtkp = payrollPtkp;
    }

    private List<PayrollPtkp> initComboPayrollPtkp;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollPtkpAction.logger = logger;
    }


    public List<PayrollPtkp> getInitComboPayrollPtkp() {
        return initComboPayrollPtkp;
    }

    public void setInitComboPayrollPtkp(List<PayrollPtkp> initComboPayrollPtkp) {
        this.initComboPayrollPtkp = initComboPayrollPtkp;
    }

    public PayrollPtkp init(String kode, String flag){
        logger.info("[PayrollPtkpAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollPtkp> listOfResult = (List<PayrollPtkp>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollPtkp payrollPtkp: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollPtkp.getIdPtkp()) && flag.equalsIgnoreCase(payrollPtkp.getFlag())){
                        setPayrollPtkp(payrollPtkp);
                        break;
                    }
                }
            } else {
                setPayrollPtkp(new PayrollPtkp());
            }

            logger.info("[PayrollPtkpAction.init] end process >>>");
        }
        return getPayrollPtkp();
    }

    @Override
    public String add() {
        logger.info("[PayrollPtkpAction.add] start process >>>");
        PayrollPtkp addPayrollPtkp = new PayrollPtkp();
        setPayrollPtkp(addPayrollPtkp);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollPtkpAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollPtkpAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollPtkp editPayrollPtkp = new PayrollPtkp();

        if(itemFlag != null){
            try {
                editPayrollPtkp = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getPayrollPtkpByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollPtkpAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollPtkpAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollPtkp != null) {
                setPayrollPtkp(editPayrollPtkp);
            } else {
                editPayrollPtkp.setFlag(itemFlag);
                //editPayrollPtkp.getSkalaGajiId(itemId);
                setPayrollPtkp(editPayrollPtkp);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollPtkp.getSkalaGajiId(itemId);
            editPayrollPtkp.setFlag(getFlag());
            setPayrollPtkp(editPayrollPtkp);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollPtkpAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollPtkpAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        PayrollPtkp deletePayrollPtkp = new PayrollPtkp();
        if (itemFlag != null ) {
            try {
                deletePayrollPtkp = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollPtkpAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollPtkpAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollPtkp != null) {
                setPayrollPtkp(deletePayrollPtkp);

            } else {
                //deletePayrollPtkp.getSkalaGajiId(itemId);
                deletePayrollPtkp.setFlag(itemFlag);
                setPayrollPtkp(deletePayrollPtkp);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollPtkp.getSkalaGajiId(itemId);
            deletePayrollPtkp.setFlag(itemFlag);
            setPayrollPtkp(deletePayrollPtkp);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollPtkpAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PayrollPtkpAction.view] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        PayrollPtkp deletePayrollPtkp = new PayrollPtkp();

        if (itemFlag != null ) {
            try {
                deletePayrollPtkp = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollPtkpAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollPtkpAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollPtkp != null) {
                setPayrollPtkp(deletePayrollPtkp);

            } else {
                deletePayrollPtkp.setIdPtkp(itemId);
                deletePayrollPtkp.setFlag(itemFlag);
                setPayrollPtkp(deletePayrollPtkp);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollPtkp.setIdPtkp(itemId);
            deletePayrollPtkp.setFlag(itemFlag);
            setPayrollPtkp(deletePayrollPtkp);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollPtkpAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PayrollPtkpAction.saveEdit] start process >>>");
        try {

            PayrollPtkp editPayrollPtkp = getPayrollPtkp();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollPtkp.setLastUpdateWho(userLogin);
            editPayrollPtkp.setLastUpdate(updateTime);
            editPayrollPtkp.setAction("U");
            editPayrollPtkp.setFlag("Y");

            payrollPtkpBoProxy.saveEdit(editPayrollPtkp);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollPtkpAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollPtkpAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollPtkpAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollPtkpAction.saveDelete] start process >>>");
        try {

            PayrollPtkp deletePayrollPtkp = getPayrollPtkp();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollPtkp.setLastUpdate(updateTime);
            deletePayrollPtkp.setLastUpdateWho(userLogin);
            deletePayrollPtkp.setAction("U");
            deletePayrollPtkp.setFlag("N");

            payrollPtkpBoProxy.saveDelete(deletePayrollPtkp);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollPtkpAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollPtkpAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollPtkpAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollPtkpAction.saveAdd] start process >>>");

        try {
            PayrollPtkp payrollPtkp = getPayrollPtkp();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollPtkp.setCreatedWho(userLogin);
            payrollPtkp.setLastUpdate(updateTime);
            payrollPtkp.setCreatedDate(updateTime);
            payrollPtkp.setLastUpdateWho(userLogin);
            payrollPtkp.setAction("C");
            payrollPtkp.setFlag("Y");

            payrollPtkpBoProxy.saveAdd(payrollPtkp);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "payrollPtkpBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollPtkpAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[payrollPtkpAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollPtkpAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollPtkpAction.search] start process >>>");

        PayrollPtkp searchPayrollPtkp = getPayrollPtkp();
        List<PayrollPtkp> listOfsearchPayrollPtkp = new ArrayList();

        try {
            listOfsearchPayrollPtkp = payrollPtkpBoProxy.getByCriteria(searchPayrollPtkp);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollPtkpAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollPtkpAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollPtkp);

        logger.info("[PayrollPtkpAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollPtkp() {
        logger.info("[PayrollPtkpAction.search] start process >>>");

        PayrollPtkp searchPayrollPtkp = new PayrollPtkp();
        searchPayrollPtkp.setFlag("Y");
        List<PayrollPtkp> listOfsearchPayrollPtkp = new ArrayList();

        try {
            listOfsearchPayrollPtkp = payrollPtkpBoProxy.getByCriteria(searchPayrollPtkp);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollPtkpAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollPtkpAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollPtkp.addAll(listOfsearchPayrollPtkp);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollPtkpAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollPtkpAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollPtkp() {
        logger.info("[PayrollPtkpAction.search] start process >>>");

        PayrollPtkp searchPayrollPtkp = new PayrollPtkp();
        searchPayrollPtkp.setFlag("Y");
        List<PayrollPtkp> listOfsearchPayrollPtkp = new ArrayList();

        try {
            listOfsearchPayrollPtkp = payrollPtkpBoProxy.getByCriteria(searchPayrollPtkp);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollPtkpBoProxy.saveErrorMessage(e.getMessage(), "PayrollPtkpBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollPtkpAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollPtkpAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollPtkp");
        session.setAttribute("listOfResultPayrollPtkp", listOfsearchPayrollPtkp);

        logger.info("[PayrollPtkpAction.search] end process <<<");

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
