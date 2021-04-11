package com.neurix.hris.master.payrollSkalaGajiPensiun.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiPensiun.bo.PayrollSkalaGajiPensiunBo;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.payrollSkalaGajiPensiun;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.bo.PayrollSkalaGajiPensiunDplkBo;
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

public class PayrollSkalaGajiPensiunAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunAction.class);
    private PayrollSkalaGajiPensiunBo payrollSkalaGajiPensiunBoProxy;
    private payrollSkalaGajiPensiun payrollSkalaGajiPensiun;
    private PayrollSkalaGajiPensiunDplkBo payrollSkalaGajiPensiunDplkBoProxy;
    private com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.payrollSkalaGajiPensiunDplk payrollSkalaGajiPensiunDplk;

    public com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.payrollSkalaGajiPensiunDplk getPayrollSkalaGajiPensiunDplk() {
        return payrollSkalaGajiPensiunDplk;
    }

    public void setPayrollSkalaGajiPensiunDplk(com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.payrollSkalaGajiPensiunDplk payrollSkalaGajiPensiunDplk) {
        this.payrollSkalaGajiPensiunDplk = payrollSkalaGajiPensiunDplk;
    }

    public PayrollSkalaGajiPensiunDplkBo getPayrollSkalaGajiPensiunDplkBoProxy() {
        return payrollSkalaGajiPensiunDplkBoProxy;
    }

    public void setPayrollSkalaGajiPensiunDplkBoProxy(PayrollSkalaGajiPensiunDplkBo payrollSkalaGajiPensiunDplkBoProxy) {
        this.payrollSkalaGajiPensiunDplkBoProxy = payrollSkalaGajiPensiunDplkBoProxy;
    }

    public payrollSkalaGajiPensiun getPayrollSkalaGajiPensiun() {
        return payrollSkalaGajiPensiun;
    }

    public void setPayrollSkalaGajiPensiun(payrollSkalaGajiPensiun payrollSkalaGajiPensiun) {
        this.payrollSkalaGajiPensiun = payrollSkalaGajiPensiun;
    }

    public PayrollSkalaGajiPensiunBo getPayrollSkalaGajiPensiunBoProxy() {
        return payrollSkalaGajiPensiunBoProxy;
    }

    public void setPayrollSkalaGajiPensiunBoProxy(PayrollSkalaGajiPensiunBo payrollSkalaGajiPensiunBoProxy) {
        this.payrollSkalaGajiPensiunBoProxy = payrollSkalaGajiPensiunBoProxy;
    }

    private List<payrollSkalaGajiPensiun> listComboPayrollSkalaGajiPensiun = new ArrayList<payrollSkalaGajiPensiun>();

    public List<payrollSkalaGajiPensiun> getListComboPayrollSkalaGajiPensiun() {
        return listComboPayrollSkalaGajiPensiun;
    }

    public void setListComboPayrollSkalaGajiPensiun(List<payrollSkalaGajiPensiun> listComboPayrollSkalaGajiPensiun) {
        this.listComboPayrollSkalaGajiPensiun = listComboPayrollSkalaGajiPensiun;
    }

    private List<payrollSkalaGajiPensiun> initComboPayrollSkalaGajiPensiun;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPensiunAction.logger = logger;
    }


    public List<payrollSkalaGajiPensiun> getInitComboPayrollSkalaGajiPensiun() {
        return initComboPayrollSkalaGajiPensiun;
    }

    public void setInitComboPayrollSkalaGajiPensiun(List<payrollSkalaGajiPensiun> initComboPayrollSkalaGajiPensiun) {
        this.initComboPayrollSkalaGajiPensiun = initComboPayrollSkalaGajiPensiun;
    }

    public payrollSkalaGajiPensiun init(String kode, String flag){
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollSkalaGajiPensiun> listOfResult = (List<payrollSkalaGajiPensiun>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (com.neurix.hris.master.payrollSkalaGajiPensiun.model.payrollSkalaGajiPensiun payrollSkalaGajiPensiun: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiPensiun.getSkalaGajiPensiunId()) && flag.equalsIgnoreCase(payrollSkalaGajiPensiun.getFlag())){
                        setPayrollSkalaGajiPensiun(payrollSkalaGajiPensiun);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiPensiun(new payrollSkalaGajiPensiun());
            }

            logger.info("[PayrollSkalaGajiDplkPegawaiAction.init] end process >>>");
        }
        return getPayrollSkalaGajiPensiun();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.add] start process >>>");
        payrollSkalaGajiPensiun addPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiun();
        setPayrollSkalaGajiPensiun(addPayrollSkalaGajiPensiun);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollSkalaGajiPensiun editPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiun();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getPayrollSkalaGajiPensiunByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
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
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        payrollSkalaGajiPensiun deletePayrollSkalaGajiPensiun = new payrollSkalaGajiPensiun();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGajiPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
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

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.delete] end process <<<");

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
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.saveEdit] start process >>>");
        try {

            payrollSkalaGajiPensiun editPayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();

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
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.saveDelete] start process >>>");
        try {

            payrollSkalaGajiPensiun deletePayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();

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
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.saveAdd] start process >>>");

        try {
            payrollSkalaGajiPensiun payrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();
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
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiPensiun searchPayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();
        List<payrollSkalaGajiPensiun> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGajiPensiun);

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiPensiun searchPayrollSkalaGajiPensiun = getPayrollSkalaGajiPensiun();
        List<payrollSkalaGajiPensiun> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiPensiun.addAll(listOfsearchPayrollSkalaGajiPensiun);
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listComboPayrollSkalaGajiPensiun);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.initForm] end process >>>");
        return INPUT;
    }
    public String initFormDplk() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.initForm] end process >>>");
        return "input_dplk";
    }

    public String initPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiPensiun searchPayrollSkalaGajiPensiun = new payrollSkalaGajiPensiun();
        searchPayrollSkalaGajiPensiun.setFlag("Y");
        List<payrollSkalaGajiPensiun> listOfsearchPayrollSkalaGajiPensiun = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiPensiun = payrollSkalaGajiPensiunBoProxy.getByCriteria(searchPayrollSkalaGajiPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiPensiunBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGajiPensiun");
        session.setAttribute("listOfResultPayrollSkalaGajiPensiun", listOfsearchPayrollSkalaGajiPensiun);

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] end process <<<");

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
