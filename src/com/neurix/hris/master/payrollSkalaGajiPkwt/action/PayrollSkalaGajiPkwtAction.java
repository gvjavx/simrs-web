package com.neurix.hris.master.payrollSkalaGajiPkwt.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiPkwt.bo.PayrollSkalaGajiPkwtBo;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.payrollSkalaGajiPkwt;
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

public class PayrollSkalaGajiPkwtAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPkwtAction.class);
    private PayrollSkalaGajiPkwtBo payrollSkalaGajiPkwtBoProxy;
    private payrollSkalaGajiPkwt payrollSkalaGajiPkwt;

    public payrollSkalaGajiPkwt getPayrollSkalaGajiPkwt() {
        return payrollSkalaGajiPkwt;
    }

    public void setPayrollSkalaGajiPkwt(payrollSkalaGajiPkwt payrollSkalaGajiPkwt) {
        this.payrollSkalaGajiPkwt = payrollSkalaGajiPkwt;
    }

    public PayrollSkalaGajiPkwtBo getPayrollSkalaGajiPkwtBoProxy() {
        return payrollSkalaGajiPkwtBoProxy;
    }

    public void setPayrollSkalaGajiPkwtBoProxy(PayrollSkalaGajiPkwtBo payrollSkalaGajiPkwtBoProxy) {
        this.payrollSkalaGajiPkwtBoProxy = payrollSkalaGajiPkwtBoProxy;
    }

    private List<payrollSkalaGajiPkwt> listComboPayrollSkalaGajiPkwt = new ArrayList<payrollSkalaGajiPkwt>();

    public List<payrollSkalaGajiPkwt> getListComboPayrollSkalaGajiPkwt() {
        return listComboPayrollSkalaGajiPkwt;
    }

    public void setListComboPayrollSkalaGajiPkwt(List<payrollSkalaGajiPkwt> listComboPayrollSkalaGajiPkwt) {
        this.listComboPayrollSkalaGajiPkwt = listComboPayrollSkalaGajiPkwt;
    }

    private List<payrollSkalaGajiPkwt> initComboPayrollSkalaGajiPkwt;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPkwtAction.logger = logger;
    }


    public List<payrollSkalaGajiPkwt> getInitComboPayrollSkalaGajiPkwt() {
        return initComboPayrollSkalaGajiPkwt;
    }

    public void setInitComboPayrollSkalaGajiPkwt(List<payrollSkalaGajiPkwt> initComboPayrollSkalaGajiPkwt) {
        this.initComboPayrollSkalaGajiPkwt = initComboPayrollSkalaGajiPkwt;
    }

    public payrollSkalaGajiPkwt init(String kode, String flag){
        logger.info("[PayrollSkalaGajiPkwtAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollSkalaGajiPkwt> listOfResult = (List<payrollSkalaGajiPkwt>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollSkalaGajiPkwt payrollSkalaGajiPkwt: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiPkwt.getSkalaGajiPkwtId()) && flag.equalsIgnoreCase(payrollSkalaGajiPkwt.getFlag())){
                        setPayrollSkalaGajiPkwt(payrollSkalaGajiPkwt);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiPkwt(new payrollSkalaGajiPkwt());
            }

            logger.info("[PayrollSkalaGajiPkwtAction.init] end process >>>");
        }
        return getPayrollSkalaGajiPkwt();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiPkwtAction.add] start process >>>");
        payrollSkalaGajiPkwt addPayrollSkalaGajiPkwt = new payrollSkalaGajiPkwt();
        setPayrollSkalaGajiPkwt(addPayrollSkalaGajiPkwt);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiPkwtAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiPkwtAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollSkalaGajiPkwt editPayrollSkalaGajiPkwt = new payrollSkalaGajiPkwt();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiPkwt = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.getPayrollSkalaGajiPkwtByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiPkwtAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiPkwtAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGajiPkwt != null) {
                setPayrollSkalaGajiPkwt(editPayrollSkalaGajiPkwt);
            } else {
                editPayrollSkalaGajiPkwt.setFlag(itemFlag);
                setPayrollSkalaGajiPkwt(editPayrollSkalaGajiPkwt);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollSkalaGajiPkwt.setFlag(getFlag());
            setPayrollSkalaGajiPkwt(editPayrollSkalaGajiPkwt);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiPkwtAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiPkwtAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollSkalaGajiPkwt deletePayrollSkalaGajiPkwt = new payrollSkalaGajiPkwt();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGajiPkwt = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiPkwtAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiPkwtAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGajiPkwt != null) {
                setPayrollSkalaGajiPkwt(deletePayrollSkalaGajiPkwt);

            } else {
                deletePayrollSkalaGajiPkwt.setFlag(itemFlag);
                setPayrollSkalaGajiPkwt(deletePayrollSkalaGajiPkwt);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollSkalaGajiPkwt.setFlag(itemFlag);
            setPayrollSkalaGajiPkwt(deletePayrollSkalaGajiPkwt);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiPkwtAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiPkwtAction.saveEdit] start process >>>");
        try {

            payrollSkalaGajiPkwt editPayrollSkalaGajiPkwt = getPayrollSkalaGajiPkwt();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGajiPkwt.setLastUpdateWho(userLogin);
            editPayrollSkalaGajiPkwt.setLastUpdate(updateTime);
            editPayrollSkalaGajiPkwt.setAction("U");
            editPayrollSkalaGajiPkwt.setFlag("Y");

            payrollSkalaGajiPkwtBoProxy.saveEdit(editPayrollSkalaGajiPkwt);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPkwtAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPkwtAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiPkwtAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiPkwtAction.saveDelete] start process >>>");
        try {

            payrollSkalaGajiPkwt deletePayrollSkalaGajiPkwt = getPayrollSkalaGajiPkwt();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGajiPkwt.setLastUpdate(updateTime);
            deletePayrollSkalaGajiPkwt.setLastUpdateWho(userLogin);
            deletePayrollSkalaGajiPkwt.setAction("D");
            deletePayrollSkalaGajiPkwt.setFlag("N");

            payrollSkalaGajiPkwtBoProxy.saveDelete(deletePayrollSkalaGajiPkwt);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPkwtAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPkwtAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiPkwtAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiPkwtAction.saveAdd] start process >>>");

        try {
            payrollSkalaGajiPkwt payrollSkalaGajiPkwt = getPayrollSkalaGajiPkwt();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollSkalaGajiPkwt.setCreatedWho(userLogin);
            payrollSkalaGajiPkwt.setLastUpdate(updateTime);
            payrollSkalaGajiPkwt.setCreatedDate(updateTime);
            payrollSkalaGajiPkwt.setLastUpdateWho(userLogin);
            payrollSkalaGajiPkwt.setAction("C");
            payrollSkalaGajiPkwt.setFlag("Y");

            payrollSkalaGajiPkwtBoProxy.saveAdd(payrollSkalaGajiPkwt);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollSkalaGajiPkwtAction.search] start process >>>");

        payrollSkalaGajiPkwt searchPayrollSkalaGajiPkwt = getPayrollSkalaGajiPkwt();
        List<payrollSkalaGajiPkwt> listOfsearchPayrollSkalaGajiPkwt = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPkwt = payrollSkalaGajiPkwtBoProxy.getByCriteria(searchPayrollSkalaGajiPkwt);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPkwtAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPkwtAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGajiPkwt);

        logger.info("[PayrollSkalaGajiPkwtAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiPkwt() {
        logger.info("[PayrollSkalaGajiPkwtAction.search] start process >>>");

        payrollSkalaGajiPkwt searchPayrollSkalaGajiPkwt = new payrollSkalaGajiPkwt();
        searchPayrollSkalaGajiPkwt.setFlag("Y");
        List<payrollSkalaGajiPkwt> listOfsearchPayrollSkalaGajiPkwt = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPkwt = payrollSkalaGajiPkwtBoProxy.getByCriteria(searchPayrollSkalaGajiPkwt);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPkwtAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPkwtAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiPkwt.addAll(listOfsearchPayrollSkalaGajiPkwt);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiPkwtAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiPkwtAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollSkalaGajiPkwt() {
        logger.info("[PayrollSkalaGajiPkwtAction.search] start process >>>");

        payrollSkalaGajiPkwt searchPayrollSkalaGajiPkwt = new payrollSkalaGajiPkwt();
        searchPayrollSkalaGajiPkwt.setFlag("Y");
        List<payrollSkalaGajiPkwt> listOfsearchPayrollSkalaGajiPkwt = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPkwt = payrollSkalaGajiPkwtBoProxy.getByCriteria(searchPayrollSkalaGajiPkwt);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPkwtBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPkwtBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiPkwtAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiPkwtAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGajiPkwt");
        session.setAttribute("listOfResultPayrollSkalaGajiPkwt", listOfsearchPayrollSkalaGajiPkwt);

        logger.info("[PayrollSkalaGajiPkwtAction.search] end process <<<");

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
