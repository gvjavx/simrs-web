package com.neurix.hris.master.payrollAirListrik.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollAirListrik.bo.PayrollAirListrikBo;
import com.neurix.hris.master.payrollAirListrik.model.PayrollAirListrik;
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

public class PayrollAirListrikAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollAirListrikAction.class);
    private PayrollAirListrikBo payrollAirListrikBoProxy;
    private PayrollAirListrik payrollAirListrik;

    private List<PayrollAirListrik> listComboPayrollAirListrik = new ArrayList<PayrollAirListrik>();

    public List<PayrollAirListrik> getListComboPayrollAirListrik() {
        return listComboPayrollAirListrik;
    }

    public void setListComboPayrollAirListrik(List<PayrollAirListrik> listComboPayrollAirListrik) {
        this.listComboPayrollAirListrik = listComboPayrollAirListrik;
    }

    public PayrollAirListrikBo getPayrollAirListrikBoProxy() {
        return payrollAirListrikBoProxy;
    }

    public void setPayrollAirListrikBoProxy(PayrollAirListrikBo payrollAirListrikBoProxy) {
        this.payrollAirListrikBoProxy = payrollAirListrikBoProxy;
    }

    public PayrollAirListrik getPayrollAirListrik() {
        return payrollAirListrik;
    }

    public void setPayrollAirListrik(PayrollAirListrik payrollAirListrik) {
        this.payrollAirListrik = payrollAirListrik;
    }

    private List<PayrollAirListrik> initComboPayrollAirListrik;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollAirListrikAction.logger = logger;
    }


    public List<PayrollAirListrik> getInitComboPayrollAirListrik() {
        return initComboPayrollAirListrik;
    }

    public void setInitComboPayrollAirListrik(List<PayrollAirListrik> initComboPayrollAirListrik) {
        this.initComboPayrollAirListrik = initComboPayrollAirListrik;
    }

    public PayrollAirListrik init(String kode, String flag){
        logger.info("[PayrollAirListrikAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollAirListrik> listOfResult = (List<PayrollAirListrik>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollAirListrik payrollAirListrik: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollAirListrik.getTunjAirListrikId()) && flag.equalsIgnoreCase(payrollAirListrik.getFlag())){
                        setPayrollAirListrik(payrollAirListrik);
                        break;
                    }
                }
            } else {
                setPayrollAirListrik(new PayrollAirListrik());
            }

            logger.info("[PayrollAirListrikAction.init] end process >>>");
        }
        return getPayrollAirListrik();
    }

    @Override
    public String add() {
        logger.info("[PayrollAirListrikAction.add] start process >>>");
        PayrollAirListrik addPayrollAirListrik = new PayrollAirListrik();
        setPayrollAirListrik(addPayrollAirListrik);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollAirListrikAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollAirListrikAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollAirListrik editPayrollAirListrik = new PayrollAirListrik();

        if(itemFlag != null){
            try {
                editPayrollAirListrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.getPayrollAirListrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollAirListrikAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollAirListrikAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollAirListrik != null) {
                setPayrollAirListrik(editPayrollAirListrik);
            } else {
                editPayrollAirListrik.setFlag(itemFlag);
                //editPayrollAirListrik.getSkalaGajiId(itemId);
                setPayrollAirListrik(editPayrollAirListrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollAirListrik.getSkalaGajiId(itemId);
            editPayrollAirListrik.setFlag(getFlag());
            setPayrollAirListrik(editPayrollAirListrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollAirListrikAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollAirListrikAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollAirListrik deletePayrollAirListrik = new PayrollAirListrik();

        if (itemFlag != null ) {

            try {
                deletePayrollAirListrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollAirListrikAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollAirListrikAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollAirListrik != null) {
                setPayrollAirListrik(deletePayrollAirListrik);

            } else {
                //deletePayrollAirListrik.getSkalaGajiId(itemId);
                deletePayrollAirListrik.setFlag(itemFlag);
                setPayrollAirListrik(deletePayrollAirListrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollAirListrik.getSkalaGajiId(itemId);
            deletePayrollAirListrik.setFlag(itemFlag);
            setPayrollAirListrik(deletePayrollAirListrik);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollAirListrikAction.delete] end process <<<");

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
        logger.info("[PayrollAirListrikAction.saveEdit] start process >>>");
        try {

            PayrollAirListrik editPayrollAirListrik = getPayrollAirListrik();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollAirListrik.setLastUpdateWho(userLogin);
            editPayrollAirListrik.setLastUpdate(updateTime);
            editPayrollAirListrik.setAction("U");
            editPayrollAirListrik.setFlag("Y");

            payrollAirListrikBoProxy.saveEdit(editPayrollAirListrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAirListrikAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAirListrikAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollAirListrikAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollAirListrikAction.saveDelete] start process >>>");
        try {

            PayrollAirListrik deletePayrollAirListrik = getPayrollAirListrik();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollAirListrik.setLastUpdate(updateTime);
            deletePayrollAirListrik.setLastUpdateWho(userLogin);
            deletePayrollAirListrik.setAction("U");
            deletePayrollAirListrik.setFlag("N");

            payrollAirListrikBoProxy.saveDelete(deletePayrollAirListrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAirListrikAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAirListrikAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollAirListrikAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollAirListrikAction.saveAdd] start process >>>");

        try {
            PayrollAirListrik payrollAirListrik = getPayrollAirListrik();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollAirListrik.setCreatedWho(userLogin);
            payrollAirListrik.setLastUpdate(updateTime);
            payrollAirListrik.setCreatedDate(updateTime);
            payrollAirListrik.setLastUpdateWho(userLogin);
            payrollAirListrik.setAction("C");
            payrollAirListrik.setFlag("Y");

            payrollAirListrikBoProxy.saveAdd(payrollAirListrik);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "payrollAirListrikBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollAirListrikAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollAirListrikAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollAirListrikAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollAirListrikAction.search] start process >>>");

        PayrollAirListrik searchPayrollAirListrik = getPayrollAirListrik();
        List<PayrollAirListrik> listOfsearchPayrollAirListrik = new ArrayList();

        try {
            listOfsearchPayrollAirListrik = payrollAirListrikBoProxy.getByCriteria(searchPayrollAirListrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAirListrikAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAirListrikAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollAirListrik);

        logger.info("[PayrollAirListrikAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollAirListrik() {
        logger.info("[PayrollAirListrikAction.search] start process >>>");

        PayrollAirListrik searchPayrollAirListrik = new PayrollAirListrik();
        searchPayrollAirListrik.setFlag("Y");
        List<PayrollAirListrik> listOfsearchPayrollAirListrik = new ArrayList();

        try {
            listOfsearchPayrollAirListrik = payrollAirListrikBoProxy.getByCriteria(searchPayrollAirListrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAirListrikAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAirListrikAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollAirListrik.addAll(listOfsearchPayrollAirListrik);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollAirListrikAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollAirListrikAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollAirListrik() {
        logger.info("[PayrollAirListrikAction.search] start process >>>");

        PayrollAirListrik searchPayrollAirListrik = new PayrollAirListrik();
        searchPayrollAirListrik.setFlag("Y");
        List<PayrollAirListrik> listOfsearchPayrollAirListrik = new ArrayList();

        try {
            listOfsearchPayrollAirListrik = payrollAirListrikBoProxy.getByCriteria(searchPayrollAirListrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollAirListrikBoProxy.saveErrorMessage(e.getMessage(), "PayrollAirListrikBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollAirListrikAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollAirListrikAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollAirListrik");
        session.setAttribute("listOfResultPayrollAirListrik", listOfsearchPayrollAirListrik);

        logger.info("[PayrollAirListrikAction.search] end process <<<");

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
