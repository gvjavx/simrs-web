package com.neurix.hris.master.payrollMasaKerjaPensiun.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollMasaKerjaPensiun.bo.PayrollMasaKerjaPensiunBo;
import com.neurix.hris.master.payrollMasaKerjaPensiun.model.PayrollMasaKerjaPensiun;
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

public class PayrollMasaKerjaPensiunAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollMasaKerjaPensiunAction.class);
    private PayrollMasaKerjaPensiunBo payrollMasaKerjaPensiunBoProxy;
    private PayrollMasaKerjaPensiun payrollMasaKerjaPensiun;


    public PayrollMasaKerjaPensiun getPayrollMasaKerjaPensiun() {
        return payrollMasaKerjaPensiun;
    }

    public void setPayrollMasaKerjaPensiun(PayrollMasaKerjaPensiun payrollMasaKerjaPensiun) {
        this.payrollMasaKerjaPensiun = payrollMasaKerjaPensiun;
    }

    public PayrollMasaKerjaPensiunBo getPayrollMasaKerjaPensiunBoProxy() {
        return payrollMasaKerjaPensiunBoProxy;
    }

    public void setPayrollMasaKerjaPensiunBoProxy(PayrollMasaKerjaPensiunBo payrollMasaKerjaPensiunBoProxy) {
        this.payrollMasaKerjaPensiunBoProxy = payrollMasaKerjaPensiunBoProxy;
    }

    private List<PayrollMasaKerjaPensiun> listComboMasaKerjaPensiun = new ArrayList<PayrollMasaKerjaPensiun>();

    public List<PayrollMasaKerjaPensiun> getListComboMasaKerjaPensiun() {
        return listComboMasaKerjaPensiun;
    }

    public void setListComboMasaKerjaPensiun(List<PayrollMasaKerjaPensiun> listComboMasaKerjaPensiun) {
        this.listComboMasaKerjaPensiun = listComboMasaKerjaPensiun;
    }

    public PayrollMasaKerjaPensiunBo getMasaKerjaPensiunBoProxy() {
        return payrollMasaKerjaPensiunBoProxy;
    }

    public void setMasaKerjaPensiunBoProxy(PayrollMasaKerjaPensiunBo payrollMasaKerjaPensiunBoProxy) {
        this.payrollMasaKerjaPensiunBoProxy = payrollMasaKerjaPensiunBoProxy;
    }

    public PayrollMasaKerjaPensiun getMasaKerjaPensiun() {
        return payrollMasaKerjaPensiun;
    }

    public void setMasaKerjaPensiun(PayrollMasaKerjaPensiun payrollMasaKerjaPensiun) {
        this.payrollMasaKerjaPensiun = payrollMasaKerjaPensiun;
    }

    private List<PayrollMasaKerjaPensiun> initComboMasaKerjaPensiun;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollMasaKerjaPensiunAction.logger = logger;
    }


    public List<PayrollMasaKerjaPensiun> getInitComboMasaKerjaPensiun() {
        return initComboMasaKerjaPensiun;
    }

    public void setInitComboMasaKerjaPensiun(List<PayrollMasaKerjaPensiun> initComboMasaKerjaPensiun) {
        this.initComboMasaKerjaPensiun = initComboMasaKerjaPensiun;
    }

    public PayrollMasaKerjaPensiun init(String kode, String flag){
        logger.info("[MasaKerjaPensiunAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollMasaKerjaPensiun> listOfResult = (List<PayrollMasaKerjaPensiun>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollMasaKerjaPensiun payrollMasaKerjaPensiun: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollMasaKerjaPensiun.getMasaKerjaPensiunId()) && flag.equalsIgnoreCase(payrollMasaKerjaPensiun.getFlag())){
                        setMasaKerjaPensiun(payrollMasaKerjaPensiun);
                        break;
                    }
                }
            } else {
                setMasaKerjaPensiun(new PayrollMasaKerjaPensiun());
            }

            logger.info("[MasaKerjaPensiunAction.init] end process >>>");
        }
        return getMasaKerjaPensiun();
    }

    @Override
    public String add() {
        logger.info("[MasaKerjaPensiunAction.add] start process >>>");
        PayrollMasaKerjaPensiun addMasaKerjaPensiun = new PayrollMasaKerjaPensiun();
        setMasaKerjaPensiun(addMasaKerjaPensiun);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[MasaKerjaPensiunAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MasaKerjaPensiunAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollMasaKerjaPensiun editMasaKerjaPensiun = new PayrollMasaKerjaPensiun();

        if(itemFlag != null){
            try {
                editMasaKerjaPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.getMasaKerjaPensiunByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MasaKerjaPensiunAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MasaKerjaPensiunAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMasaKerjaPensiun != null) {
                setMasaKerjaPensiun(editMasaKerjaPensiun);
            } else {
                editMasaKerjaPensiun.setFlag(itemFlag);
                editMasaKerjaPensiun.setMasaKerjaPensiunId(itemId);
                setMasaKerjaPensiun(editMasaKerjaPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editMasaKerjaPensiun.setMasaKerjaPensiunId(itemId);
            editMasaKerjaPensiun.setFlag(getFlag());
            setMasaKerjaPensiun(editMasaKerjaPensiun);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[MasaKerjaPensiunAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MasaKerjaPensiunAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollMasaKerjaPensiun deleteMasaKerjaPensiun = new PayrollMasaKerjaPensiun();

        if (itemFlag != null ) {

            try {
                deleteMasaKerjaPensiun = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MasaKerjaPensiunAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MasaKerjaPensiunAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMasaKerjaPensiun != null) {
                setMasaKerjaPensiun(deleteMasaKerjaPensiun);

            } else {
                deleteMasaKerjaPensiun.setMasaKerjaPensiunId(itemId);
                deleteMasaKerjaPensiun.setFlag(itemFlag);
                setMasaKerjaPensiun(deleteMasaKerjaPensiun);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMasaKerjaPensiun.setMasaKerjaPensiunId(itemId);
            deleteMasaKerjaPensiun.setFlag(itemFlag);
            setMasaKerjaPensiun(deleteMasaKerjaPensiun);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[MasaKerjaPensiunAction.delete] end process <<<");

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
        logger.info("[MasaKerjaPensiunAction.saveEdit] start process >>>");
        try {

            PayrollMasaKerjaPensiun editMasaKerjaPensiun = getMasaKerjaPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMasaKerjaPensiun.setLastUpdateWho(userLogin);
            editMasaKerjaPensiun.setLastUpdate(updateTime);
            editMasaKerjaPensiun.setAction("U");
            editMasaKerjaPensiun.setFlag("Y");

            payrollMasaKerjaPensiunBoProxy.saveEdit(editMasaKerjaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MasaKerjaPensiunAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaKerjaPensiunAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MasaKerjaPensiunAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MasaKerjaPensiunAction.saveDelete] start process >>>");
        try {

            PayrollMasaKerjaPensiun deleteMasaKerjaPensiun = getMasaKerjaPensiun();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMasaKerjaPensiun.setLastUpdate(updateTime);
            deleteMasaKerjaPensiun.setLastUpdateWho(userLogin);
            deleteMasaKerjaPensiun.setAction("U");
            deleteMasaKerjaPensiun.setFlag("N");

            payrollMasaKerjaPensiunBoProxy.saveDelete(deleteMasaKerjaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MasaKerjaPensiunAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaKerjaPensiunAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MasaKerjaPensiunAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[MasaKerjaPensiunAction.saveAdd] start process >>>");

        try {
            PayrollMasaKerjaPensiun payrollMasaKerjaPensiun = getMasaKerjaPensiun();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollMasaKerjaPensiun.setCreatedWho(userLogin);
            payrollMasaKerjaPensiun.setLastUpdate(updateTime);
            payrollMasaKerjaPensiun.setCreatedDate(updateTime);
            payrollMasaKerjaPensiun.setLastUpdateWho(userLogin);
            payrollMasaKerjaPensiun.setAction("C");
            payrollMasaKerjaPensiun.setFlag("Y");

            payrollMasaKerjaPensiunBoProxy.saveAdd(payrollMasaKerjaPensiun);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[MasaKerjaPensiunAction.search] start process >>>");

        PayrollMasaKerjaPensiun searchMasaKerjaPensiun = getMasaKerjaPensiun();
        List<PayrollMasaKerjaPensiun> listOfsearchMasaKerjaPensiun = new ArrayList();

        try {
            listOfsearchMasaKerjaPensiun = payrollMasaKerjaPensiunBoProxy.getByCriteria(searchMasaKerjaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaKerjaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaKerjaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMasaKerjaPensiun);

        logger.info("[MasaKerjaPensiunAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchMasaKerjaPensiun() {
        logger.info("[MasaKerjaPensiunAction.search] start process >>>");

        PayrollMasaKerjaPensiun searchMasaKerjaPensiun = new PayrollMasaKerjaPensiun();
        searchMasaKerjaPensiun.setFlag("Y");
        List<PayrollMasaKerjaPensiun> listOfsearchMasaKerjaPensiun = new ArrayList();

        try {
            listOfsearchMasaKerjaPensiun = payrollMasaKerjaPensiunBoProxy.getByCriteria(searchMasaKerjaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaKerjaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaKerjaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboMasaKerjaPensiun.addAll(listOfsearchMasaKerjaPensiun);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MasaKerjaPensiunAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[MasaKerjaPensiunAction.initForm] end process >>>");
        return INPUT;
    }

    public String initMasaKerjaPensiun() {
        logger.info("[MasaKerjaPensiunAction.search] start process >>>");

        PayrollMasaKerjaPensiun searchMasaKerjaPensiun = new PayrollMasaKerjaPensiun();
        searchMasaKerjaPensiun.setFlag("Y");
        List<PayrollMasaKerjaPensiun> listOfsearchMasaKerjaPensiun = new ArrayList();

        try {
            listOfsearchMasaKerjaPensiun = payrollMasaKerjaPensiunBoProxy.getByCriteria(searchMasaKerjaPensiun);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollMasaKerjaPensiunBoProxy.saveErrorMessage(e.getMessage(), "MasaKerjaPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MasaKerjaPensiunAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MasaKerjaPensiunAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMasaKerjaPensiun");
        session.setAttribute("listOfResultMasaKerjaPensiun", listOfsearchMasaKerjaPensiun);

        logger.info("[MasaKerjaPensiunAction.search] end process <<<");

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
