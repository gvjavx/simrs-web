package com.neurix.hris.master.payrollSkalaGajiBod.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiBod.bo.PayrollSkalaGajiBodBo;
import com.neurix.hris.master.payrollSkalaGajiBod.model.PayrollSkalaGajiBod;
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

public class PayrollSkalaGajiBodAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiBodAction.class);
    private PayrollSkalaGajiBodBo payrollSkalaGajiBodBoProxy;
    private PayrollSkalaGajiBod payrollSkalaGajiBod;
    private List<PayrollSkalaGajiBod> listComboPayrollSkalaGajiBod = new ArrayList<PayrollSkalaGajiBod>();
    
    private List<PayrollSkalaGajiBod> initComboPayrollSkalaGajiBod;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiBodAction.logger = logger;
    }

    public PayrollSkalaGajiBodBo getPayrollSkalaGajiBodBoProxy() {
        return payrollSkalaGajiBodBoProxy;
    }

    public void setPayrollSkalaGajiBodBoProxy(PayrollSkalaGajiBodBo payrollSkalaGajiBodBoProxy) {
        this.payrollSkalaGajiBodBoProxy = payrollSkalaGajiBodBoProxy;
    }

    public PayrollSkalaGajiBod getPayrollSkalaGajiBod() {
        return payrollSkalaGajiBod;
    }

    public void setPayrollSkalaGajiBod(PayrollSkalaGajiBod payrollSkalaGajiBod) {
        this.payrollSkalaGajiBod = payrollSkalaGajiBod;
    }

    public List<PayrollSkalaGajiBod> getListComboPayrollSkalaGajiBod() {
        return listComboPayrollSkalaGajiBod;
    }

    public void setListComboPayrollSkalaGajiBod(List<PayrollSkalaGajiBod> listComboPayrollSkalaGajiBod) {
        this.listComboPayrollSkalaGajiBod = listComboPayrollSkalaGajiBod;
    }

    public List<PayrollSkalaGajiBod> getInitComboPayrollSkalaGajiBod() {
        return initComboPayrollSkalaGajiBod;
    }

    public void setInitComboPayrollSkalaGajiBod(List<PayrollSkalaGajiBod> initComboPayrollSkalaGajiBod) {
        this.initComboPayrollSkalaGajiBod = initComboPayrollSkalaGajiBod;
    }

    public PayrollSkalaGajiBod init(String kode, String flag){
        logger.info("[PayrollSkalaGajiBodAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollSkalaGajiBod> listOfResult = (List<PayrollSkalaGajiBod>) session.getAttribute("listOfResultBod");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollSkalaGajiBod payrollSkalaGajiBod: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiBod.getPayrollSkalaGajiBodId()) && flag.equalsIgnoreCase(payrollSkalaGajiBod.getFlag())){
                        setPayrollSkalaGajiBod(payrollSkalaGajiBod);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiBod(new PayrollSkalaGajiBod());
            }

            logger.info("[PayrollSkalaGajiBodAction.init] end process >>>");
        }
        return getPayrollSkalaGajiBod();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiBodAction.add] start process >>>");
        PayrollSkalaGajiBod addPayrollSkalaGajiBod = new PayrollSkalaGajiBod();
        setPayrollSkalaGajiBod(addPayrollSkalaGajiBod);

        logger.info("[PayrollSkalaGajiBodAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiBodAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollSkalaGajiBod editPayrollSkalaGajiBod = new PayrollSkalaGajiBod();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiBod = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.getPayrollSkalaGajiBodByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiBodAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiBodAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGajiBod != null) {
                setPayrollSkalaGajiBod(editPayrollSkalaGajiBod);
            } else {
                editPayrollSkalaGajiBod.setFlag(itemFlag);
                setPayrollSkalaGajiBod(editPayrollSkalaGajiBod);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollSkalaGajiBod.setFlag(getFlag());
            setPayrollSkalaGajiBod(editPayrollSkalaGajiBod);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiBodAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiBodAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollSkalaGajiBod deletePayrollSkalaGajiBod = new PayrollSkalaGajiBod();
        if (itemFlag != null ) {
            try {
                deletePayrollSkalaGajiBod = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiBodAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiBodAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGajiBod != null) {
                setPayrollSkalaGajiBod(deletePayrollSkalaGajiBod);

            } else {
                deletePayrollSkalaGajiBod.setFlag(itemFlag);
                setPayrollSkalaGajiBod(deletePayrollSkalaGajiBod);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollSkalaGajiBod.setFlag(itemFlag);
            setPayrollSkalaGajiBod(deletePayrollSkalaGajiBod);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiBodAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiBodAction.saveEdit] start process >>>");
        try {

            PayrollSkalaGajiBod editPayrollSkalaGajiBod = getPayrollSkalaGajiBod();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGajiBod.setLastUpdateWho(userLogin);
            editPayrollSkalaGajiBod.setLastUpdate(updateTime);
            editPayrollSkalaGajiBod.setAction("U");
            editPayrollSkalaGajiBod.setFlag("Y");

            payrollSkalaGajiBodBoProxy.saveEdit(editPayrollSkalaGajiBod);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiBodAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PayrollSkalaGajiBodAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PayrollSkalaGajiBodAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiBodAction.saveDelete] start process >>>");
        try {
            PayrollSkalaGajiBod deletePayrollSkalaGajiBod = getPayrollSkalaGajiBod();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGajiBod.setLastUpdate(updateTime);
            deletePayrollSkalaGajiBod.setLastUpdateWho(userLogin);
            deletePayrollSkalaGajiBod.setAction("D");
            deletePayrollSkalaGajiBod.setFlag("N");

            payrollSkalaGajiBodBoProxy.saveDelete(deletePayrollSkalaGajiBod);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiBodAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1);
            }
            logger.error("[PayrollSkalaGajiBodAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e);
        }
        logger.info("[PayrollSkalaGajiBodAction.saveDelete] end process <<<");
        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiBodAction.saveAdd] start process >>>");

        try {
            PayrollSkalaGajiBod payrollSkalaGajiBod = getPayrollSkalaGajiBod();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            payrollSkalaGajiBod.setCreatedWho(userLogin);
            payrollSkalaGajiBod.setLastUpdate(updateTime);
            payrollSkalaGajiBod.setCreatedDate(updateTime);
            payrollSkalaGajiBod.setLastUpdateWho(userLogin);
            payrollSkalaGajiBod.setAction("C");
            payrollSkalaGajiBod.setFlag("Y");

            payrollSkalaGajiBodBoProxy.saveAdd(payrollSkalaGajiBod);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollSkalaGajiBodAction.search] start process >>>");

        PayrollSkalaGajiBod searchPayrollSkalaGajiBod = getPayrollSkalaGajiBod();
        List<PayrollSkalaGajiBod> listOfsearchPayrollSkalaGajiBod = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiBod = payrollSkalaGajiBodBoProxy.getByCriteria(searchPayrollSkalaGajiBod);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiBodAction.search] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PayrollSkalaGajiBodAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBod");
        session.setAttribute("listOfResultBod", listOfsearchPayrollSkalaGajiBod);

        logger.info("[PayrollSkalaGajiBodAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiBod() {
        logger.info("[PayrollSkalaGajiBodAction.search] start process >>>");

        PayrollSkalaGajiBod searchPayrollSkalaGajiBod = new PayrollSkalaGajiBod();
        searchPayrollSkalaGajiBod.setFlag("Y");
        List<PayrollSkalaGajiBod> listOfsearchPayrollSkalaGajiBod = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiBod = payrollSkalaGajiBodBoProxy.getByCriteria(searchPayrollSkalaGajiBod);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiBodAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiBodAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiBod.addAll(listOfsearchPayrollSkalaGajiBod);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiBodAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiBodAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollSkalaGajiBod() {
        logger.info("[PayrollSkalaGajiBodAction.search] start process >>>");

        PayrollSkalaGajiBod searchPayrollSkalaGajiBod = new PayrollSkalaGajiBod();
        searchPayrollSkalaGajiBod.setFlag("Y");
        List<PayrollSkalaGajiBod> listOfsearchPayrollSkalaGajiBod = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiBod = payrollSkalaGajiBodBoProxy.getByCriteria(searchPayrollSkalaGajiBod);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBodBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBodBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiBodAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiBodAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGajiBod");
        session.setAttribute("listOfResultPayrollSkalaGajiBod", listOfsearchPayrollSkalaGajiBod);

        logger.info("[PayrollSkalaGajiBodAction.search] end process <<<");

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
