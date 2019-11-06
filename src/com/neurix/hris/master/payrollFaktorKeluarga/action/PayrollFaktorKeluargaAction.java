package com.neurix.hris.master.payrollFaktorKeluarga.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollFaktorKeluarga.bo.PayrollFaktorKeluargaBo;
import com.neurix.hris.master.payrollFaktorKeluarga.model.payrollFaktorKeluarga;
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

public class PayrollFaktorKeluargaAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollFaktorKeluargaAction.class);
    private PayrollFaktorKeluargaBo payrollFaktorKeluargaBoProxy;
    private payrollFaktorKeluarga payrollFaktorKeluarga;

    public payrollFaktorKeluarga getPayrollFaktorKeluarga() {
        return payrollFaktorKeluarga;
    }

    public void setPayrollFaktorKeluarga(payrollFaktorKeluarga payrollFaktorKeluarga) {
        this.payrollFaktorKeluarga = payrollFaktorKeluarga;
    }

    public PayrollFaktorKeluargaBo getPayrollFaktorKeluargaBoProxy() {
        return payrollFaktorKeluargaBoProxy;
    }

    public void setPayrollFaktorKeluargaBoProxy(PayrollFaktorKeluargaBo payrollFaktorKeluargaBoProxy) {
        this.payrollFaktorKeluargaBoProxy = payrollFaktorKeluargaBoProxy;
    }

    private List<payrollFaktorKeluarga> listComboPayrollFaktorKeluarga = new ArrayList<payrollFaktorKeluarga>();

    public List<payrollFaktorKeluarga> getListComboPayrollFaktorKeluarga() {
        return listComboPayrollFaktorKeluarga;
    }

    public void setListComboPayrollFaktorKeluarga(List<payrollFaktorKeluarga> listComboPayrollFaktorKeluarga) {
        this.listComboPayrollFaktorKeluarga = listComboPayrollFaktorKeluarga;
    }

    private List<payrollFaktorKeluarga> initComboPayrollFaktorKeluarga;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollFaktorKeluargaAction.logger = logger;
    }


    public List<payrollFaktorKeluarga> getInitComboPayrollFaktorKeluarga() {
        return initComboPayrollFaktorKeluarga;
    }

    public void setInitComboPayrollFaktorKeluarga(List<payrollFaktorKeluarga> initComboPayrollFaktorKeluarga) {
        this.initComboPayrollFaktorKeluarga = initComboPayrollFaktorKeluarga;
    }

    public payrollFaktorKeluarga init(String kode, String flag){
        logger.info("[PayrollFaktorKeluargaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollFaktorKeluarga> listOfResult = (List<payrollFaktorKeluarga>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollFaktorKeluarga payrollFaktorKeluarga: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollFaktorKeluarga.getFaktorKeluargaId()) && flag.equalsIgnoreCase(payrollFaktorKeluarga.getFlag())){
                        setPayrollFaktorKeluarga(payrollFaktorKeluarga);
                        break;
                    }
                }
            } else {
                setPayrollFaktorKeluarga(new payrollFaktorKeluarga());
            }

            logger.info("[PayrollFaktorKeluargaAction.init] end process >>>");
        }
        return getPayrollFaktorKeluarga();
    }

    @Override
    public String add() {
        logger.info("[PayrollFaktorKeluargaAction.add] start process >>>");
        payrollFaktorKeluarga addPayrollFaktorKeluarga = new payrollFaktorKeluarga();
        setPayrollFaktorKeluarga(addPayrollFaktorKeluarga);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollFaktorKeluargaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollFaktorKeluargaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollFaktorKeluarga editPayrollFaktorKeluarga = new payrollFaktorKeluarga();

        if(itemFlag != null){
            try {
                editPayrollFaktorKeluarga = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.getPayrollFaktorKeluargaByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollFaktorKeluargaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollFaktorKeluargaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollFaktorKeluarga != null) {
                setPayrollFaktorKeluarga(editPayrollFaktorKeluarga);
            } else {
                editPayrollFaktorKeluarga.setFlag(itemFlag);
                setPayrollFaktorKeluarga(editPayrollFaktorKeluarga);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollFaktorKeluarga.setFlag(getFlag());
            setPayrollFaktorKeluarga(editPayrollFaktorKeluarga);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollFaktorKeluargaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollFaktorKeluargaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollFaktorKeluarga deletePayrollFaktorKeluarga = new payrollFaktorKeluarga();

        if (itemFlag != null ) {

            try {
                deletePayrollFaktorKeluarga = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollFaktorKeluargaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollFaktorKeluargaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollFaktorKeluarga != null) {
                setPayrollFaktorKeluarga(deletePayrollFaktorKeluarga);

            } else {
                deletePayrollFaktorKeluarga.setFlag(itemFlag);
                setPayrollFaktorKeluarga(deletePayrollFaktorKeluarga);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollFaktorKeluarga.setFlag(itemFlag);
            setPayrollFaktorKeluarga(deletePayrollFaktorKeluarga);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollFaktorKeluargaAction.delete] end process <<<");

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
        logger.info("[PayrollFaktorKeluargaAction.saveEdit] start process >>>");
        try {

            payrollFaktorKeluarga editPayrollFaktorKeluarga = getPayrollFaktorKeluarga();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollFaktorKeluarga.setLastUpdateWho(userLogin);
            editPayrollFaktorKeluarga.setLastUpdate(updateTime);
            editPayrollFaktorKeluarga.setAction("U");
            editPayrollFaktorKeluarga.setFlag("Y");

            payrollFaktorKeluargaBoProxy.saveEdit(editPayrollFaktorKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollFaktorKeluargaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollFaktorKeluargaAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollFaktorKeluargaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollFaktorKeluargaAction.saveDelete] start process >>>");
        try {

            payrollFaktorKeluarga deletePayrollFaktorKeluarga = getPayrollFaktorKeluarga();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollFaktorKeluarga.setLastUpdate(updateTime);
            deletePayrollFaktorKeluarga.setLastUpdateWho(userLogin);
            deletePayrollFaktorKeluarga.setAction("D");
            deletePayrollFaktorKeluarga.setFlag("N");

            payrollFaktorKeluargaBoProxy.saveDelete(deletePayrollFaktorKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollFaktorKeluargaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollFaktorKeluargaAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollFaktorKeluargaAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollFaktorKeluargaAction.saveAdd] start process >>>");

        try {
            payrollFaktorKeluarga payrollFaktorKeluarga = getPayrollFaktorKeluarga();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollFaktorKeluarga.setCreatedWho(userLogin);
            payrollFaktorKeluarga.setLastUpdate(updateTime);
            payrollFaktorKeluarga.setCreatedDate(updateTime);
            payrollFaktorKeluarga.setLastUpdateWho(userLogin);
            payrollFaktorKeluarga.setAction("C");
            payrollFaktorKeluarga.setFlag("Y");

            payrollFaktorKeluargaBoProxy.saveAdd(payrollFaktorKeluarga);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollFaktorKeluargaAction.search] start process >>>");

        payrollFaktorKeluarga searchPayrollFaktorKeluarga = getPayrollFaktorKeluarga();
        List<payrollFaktorKeluarga> listOfsearchPayrollFaktorKeluarga = new ArrayList();

        try {
            listOfsearchPayrollFaktorKeluarga = payrollFaktorKeluargaBoProxy.getByCriteria(searchPayrollFaktorKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollFaktorKeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollFaktorKeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollFaktorKeluarga);

        logger.info("[PayrollFaktorKeluargaAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollFaktorKeluarga() {
        logger.info("[PayrollFaktorKeluargaAction.search] start process >>>");

        payrollFaktorKeluarga searchPayrollFaktorKeluarga = new payrollFaktorKeluarga();
        searchPayrollFaktorKeluarga.setFlag("Y");
        List<payrollFaktorKeluarga> listOfsearchPayrollFaktorKeluarga = new ArrayList();

        try {
            listOfsearchPayrollFaktorKeluarga = payrollFaktorKeluargaBoProxy.getByCriteria(searchPayrollFaktorKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollFaktorKeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollFaktorKeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollFaktorKeluarga.addAll(listOfsearchPayrollFaktorKeluarga);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollFaktorKeluargaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollFaktorKeluargaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollFaktorKeluarga() {
        logger.info("[PayrollFaktorKeluargaAction.search] start process >>>");

        payrollFaktorKeluarga searchPayrollFaktorKeluarga = new payrollFaktorKeluarga();
        searchPayrollFaktorKeluarga.setFlag("Y");
        List<payrollFaktorKeluarga> listOfsearchPayrollFaktorKeluarga = new ArrayList();

        try {
            listOfsearchPayrollFaktorKeluarga = payrollFaktorKeluargaBoProxy.getByCriteria(searchPayrollFaktorKeluarga);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollFaktorKeluargaBoProxy.saveErrorMessage(e.getMessage(), "PayrollFaktorKeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollFaktorKeluargaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollFaktorKeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollFaktorKeluarga");
        session.setAttribute("listOfResultPayrollFaktorKeluarga", listOfsearchPayrollFaktorKeluarga);

        logger.info("[PayrollFaktorKeluargaAction.search] end process <<<");

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
