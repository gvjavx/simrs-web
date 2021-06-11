package com.neurix.hris.master.payrollMasterInsentif.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollMasterInsentif.bo.PayrollMasterInsentifBo;
import com.neurix.hris.master.payrollMasterInsentif.model.PayrollMasterInsentif;
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

public class PayrollMasterInsentifAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollMasterInsentifAction.class);
    private PayrollMasterInsentifBo payrollMasterInsentifBoProxy;
    private PayrollMasterInsentif payrollMasterInsentif;

    private List<PayrollMasterInsentif> listComboPayrollMasterInsentif = new ArrayList<PayrollMasterInsentif>();

    public List<PayrollMasterInsentif> getListComboPayrollMasterInsentif() {
        return listComboPayrollMasterInsentif;
    }

    public void setListComboPayrollMasterInsentif(List<PayrollMasterInsentif> listComboPayrollMasterInsentif) {
        this.listComboPayrollMasterInsentif = listComboPayrollMasterInsentif;
    }

    public PayrollMasterInsentifBo getPayrollMasterInsentifBoProxy() {
        return payrollMasterInsentifBoProxy;
    }

    public void setPayrollMasterInsentifBoProxy(PayrollMasterInsentifBo payrollMasterInsentifBoProxy) {
        this.payrollMasterInsentifBoProxy = payrollMasterInsentifBoProxy;
    }

    public PayrollMasterInsentif getPayrollMasterInsentif() {
        return payrollMasterInsentif;
    }

    public void setPayrollMasterInsentif(PayrollMasterInsentif payrollMasterInsentif) {
        this.payrollMasterInsentif = payrollMasterInsentif;
    }

    private List<PayrollMasterInsentif> initComboPayrollMasterInsentif;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollMasterInsentifAction.logger = logger;
    }


    public List<PayrollMasterInsentif> getInitComboPayrollMasterInsentif() {
        return initComboPayrollMasterInsentif;
    }

    public void setInitComboPayrollMasterInsentif(List<PayrollMasterInsentif> initComboPayrollMasterInsentif) {
        this.initComboPayrollMasterInsentif = initComboPayrollMasterInsentif;
    }

    public PayrollMasterInsentif init(String kode, String flag){
        logger.info("[PayrollMasterInsentifAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollMasterInsentif> listOfResult = (List<PayrollMasterInsentif>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollMasterInsentif payrollMasterInsentif: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollMasterInsentif.getPayrollInsentifId()) && flag.equalsIgnoreCase(payrollMasterInsentif.getFlag())){
                        setPayrollMasterInsentif(payrollMasterInsentif);
                        break;
                    }
                }
            } else {
                setPayrollMasterInsentif(new PayrollMasterInsentif());
            }

            logger.info("[PayrollMasterInsentifAction.init] end process >>>");
        }
        return getPayrollMasterInsentif();
    }

    @Override
    public String add() {
        logger.info("[PayrollMasterInsentifAction.add] start process >>>");
        PayrollMasterInsentif addPayrollMasterInsentif = new PayrollMasterInsentif();
        setPayrollMasterInsentif(addPayrollMasterInsentif);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollMasterInsentifAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollMasterInsentifAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollMasterInsentif editPayrollMasterInsentif = new PayrollMasterInsentif();

        if(itemFlag != null){
            try {
                editPayrollMasterInsentif = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.getPayrollMasterInsentifByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollMasterInsentifAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollMasterInsentifAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollMasterInsentif != null) {
                setPayrollMasterInsentif(editPayrollMasterInsentif);
            } else {
                editPayrollMasterInsentif.setFlag(itemFlag);
                //editPayrollMasterInsentif.getSkalaGajiId(itemId);
                setPayrollMasterInsentif(editPayrollMasterInsentif);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollMasterInsentif.getSkalaGajiId(itemId);
            editPayrollMasterInsentif.setFlag(getFlag());
            setPayrollMasterInsentif(editPayrollMasterInsentif);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollMasterInsentifAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollMasterInsentifAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollMasterInsentif deletePayrollMasterInsentif = new PayrollMasterInsentif();

        if (itemFlag != null ) {

            try {
                deletePayrollMasterInsentif = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollMasterInsentifAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollMasterInsentifAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollMasterInsentif != null) {
                setPayrollMasterInsentif(deletePayrollMasterInsentif);

            } else {
                //deletePayrollMasterInsentif.getSkalaGajiId(itemId);
                deletePayrollMasterInsentif.setFlag(itemFlag);
                setPayrollMasterInsentif(deletePayrollMasterInsentif);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollMasterInsentif.getSkalaGajiId(itemId);
            deletePayrollMasterInsentif.setFlag(itemFlag);
            setPayrollMasterInsentif(deletePayrollMasterInsentif);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollMasterInsentifAction.delete] end process <<<");

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
        logger.info("[PayrollMasterInsentifAction.saveEdit] start process >>>");
        try {

            PayrollMasterInsentif editPayrollMasterInsentif = getPayrollMasterInsentif();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollMasterInsentif.setLastUpdateWho(userLogin);
            editPayrollMasterInsentif.setLastUpdate(updateTime);
            editPayrollMasterInsentif.setAction("U");
            editPayrollMasterInsentif.setFlag("Y");

            payrollMasterInsentifBoProxy.saveEdit(editPayrollMasterInsentif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollMasterInsentifAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollMasterInsentifAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollMasterInsentifAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollMasterInsentifAction.saveDelete] start process >>>");
        try {

            PayrollMasterInsentif deletePayrollMasterInsentif = getPayrollMasterInsentif();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollMasterInsentif.setLastUpdate(updateTime);
            deletePayrollMasterInsentif.setLastUpdateWho(userLogin);
            deletePayrollMasterInsentif.setAction("U");
            deletePayrollMasterInsentif.setFlag("N");

            payrollMasterInsentifBoProxy.saveDelete(deletePayrollMasterInsentif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollMasterInsentifAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollMasterInsentifAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollMasterInsentifAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollMasterInsentifAction.saveAdd] start process >>>");

        try {
            PayrollMasterInsentif payrollMasterInsentif = getPayrollMasterInsentif();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollMasterInsentif.setCreatedWho(userLogin);
            payrollMasterInsentif.setLastUpdate(updateTime);
            payrollMasterInsentif.setCreatedDate(updateTime);
            payrollMasterInsentif.setLastUpdateWho(userLogin);
            payrollMasterInsentif.setAction("C");
            payrollMasterInsentif.setFlag("Y");

            payrollMasterInsentifBoProxy.saveAdd(payrollMasterInsentif);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "payrollMasterInsentifBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollMasterInsentifAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollMasterInsentifAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollMasterInsentifAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollMasterInsentifAction.search] start process >>>");

        PayrollMasterInsentif searchPayrollMasterInsentif = getPayrollMasterInsentif();
        List<PayrollMasterInsentif> listOfsearchPayrollMasterInsentif = new ArrayList();

        try {
            listOfsearchPayrollMasterInsentif = payrollMasterInsentifBoProxy.getByCriteria(searchPayrollMasterInsentif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollMasterInsentifAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollMasterInsentifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollMasterInsentif);

        logger.info("[PayrollMasterInsentifAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollMasterInsentif() {
        logger.info("[PayrollMasterInsentifAction.search] start process >>>");

        PayrollMasterInsentif searchPayrollMasterInsentif = new PayrollMasterInsentif();
        searchPayrollMasterInsentif.setFlag("Y");
        List<PayrollMasterInsentif> listOfsearchPayrollMasterInsentif = new ArrayList();

        try {
            listOfsearchPayrollMasterInsentif = payrollMasterInsentifBoProxy.getByCriteria(searchPayrollMasterInsentif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollMasterInsentifAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollMasterInsentifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollMasterInsentif.addAll(listOfsearchPayrollMasterInsentif);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollMasterInsentifAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollMasterInsentifAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollMasterInsentif() {
        logger.info("[PayrollMasterInsentifAction.search] start process >>>");

        PayrollMasterInsentif searchPayrollMasterInsentif = new PayrollMasterInsentif();
        searchPayrollMasterInsentif.setFlag("Y");
        List<PayrollMasterInsentif> listOfsearchPayrollMasterInsentif = new ArrayList();

        try {
            listOfsearchPayrollMasterInsentif = payrollMasterInsentifBoProxy.getByCriteria(searchPayrollMasterInsentif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasterInsentifBoProxy.saveErrorMessage(e.getMessage(), "PayrollMasterInsentifBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollMasterInsentifAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollMasterInsentifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollMasterInsentif");
        session.setAttribute("listOfResultPayrollMasterInsentif", listOfsearchPayrollMasterInsentif);

        logger.info("[PayrollMasterInsentifAction.search] end process <<<");

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
