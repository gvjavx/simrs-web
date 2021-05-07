package com.neurix.hris.master.payrollBajuDinas.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollBajuDinas.bo.PayrollBajuDinasBo;
import com.neurix.hris.master.payrollBajuDinas.model.PayrollBajuDinas;
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

public class PayrollBajuDinasAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollBajuDinasAction.class);
    private PayrollBajuDinasBo payrollBajuDinasBoProxy;
    private PayrollBajuDinas payrollBajuDinas;

    private List<PayrollBajuDinas> listComboPayrollBajuDinas = new ArrayList<PayrollBajuDinas>();

    public List<PayrollBajuDinas> getListComboPayrollBajuDinas() {
        return listComboPayrollBajuDinas;
    }

    public void setListComboPayrollBajuDinas(List<PayrollBajuDinas> listComboPayrollBajuDinas) {
        this.listComboPayrollBajuDinas = listComboPayrollBajuDinas;
    }

    public PayrollBajuDinasBo getPayrollBajuDinasBoProxy() {
        return payrollBajuDinasBoProxy;
    }

    public void setPayrollBajuDinasBoProxy(PayrollBajuDinasBo payrollBajuDinasBoProxy) {
        this.payrollBajuDinasBoProxy = payrollBajuDinasBoProxy;
    }

    public PayrollBajuDinas getPayrollBajuDinas() {
        return payrollBajuDinas;
    }

    public void setPayrollBajuDinas(PayrollBajuDinas payrollBajuDinas) {
        this.payrollBajuDinas = payrollBajuDinas;
    }

    private List<PayrollBajuDinas> initComboPayrollBajuDinas;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollBajuDinasAction.logger = logger;
    }


    public List<PayrollBajuDinas> getInitComboPayrollBajuDinas() {
        return initComboPayrollBajuDinas;
    }

    public void setInitComboPayrollBajuDinas(List<PayrollBajuDinas> initComboPayrollBajuDinas) {
        this.initComboPayrollBajuDinas = initComboPayrollBajuDinas;
    }

    public PayrollBajuDinas init(String kode, String flag){
        logger.info("[PayrollBajuDinasAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollBajuDinas> listOfResult = (List<PayrollBajuDinas>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollBajuDinas payrollBajuDinas: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollBajuDinas.getPakaianDinasId()) && flag.equalsIgnoreCase(payrollBajuDinas.getFlag())){
                        setPayrollBajuDinas(payrollBajuDinas);
                        break;
                    }
                }
            } else {
                setPayrollBajuDinas(new PayrollBajuDinas());
            }

            logger.info("[PayrollBajuDinasAction.init] end process >>>");
        }
        return getPayrollBajuDinas();
    }

    @Override
    public String add() {
        logger.info("[PayrollBajuDinasAction.add] start process >>>");
        PayrollBajuDinas addPayrollBajuDinas = new PayrollBajuDinas();
        setPayrollBajuDinas(addPayrollBajuDinas);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollBajuDinasAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollBajuDinasAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollBajuDinas editPayrollBajuDinas = new PayrollBajuDinas();

        if(itemFlag != null){
            try {
                editPayrollBajuDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.getPayrollBajuDinasByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollBajuDinasAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollBajuDinasAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollBajuDinas != null) {
                setPayrollBajuDinas(editPayrollBajuDinas);
            } else {
                editPayrollBajuDinas.setFlag(itemFlag);
                editPayrollBajuDinas.setPakaianDinasId(itemId);
                setPayrollBajuDinas(editPayrollBajuDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollBajuDinas.setPakaianDinasId(itemId);
            editPayrollBajuDinas.setFlag(getFlag());
            setPayrollBajuDinas(editPayrollBajuDinas);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollBajuDinasAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollBajuDinasAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollBajuDinas deletePayrollBajuDinas = new PayrollBajuDinas();

        if (itemFlag != null ) {

            try {
                deletePayrollBajuDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollBajuDinasAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollBajuDinasAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollBajuDinas != null) {
                setPayrollBajuDinas(deletePayrollBajuDinas);

            } else {
                deletePayrollBajuDinas.setPakaianDinasId(itemId);
                deletePayrollBajuDinas.setFlag(itemFlag);
                setPayrollBajuDinas(deletePayrollBajuDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollBajuDinas.setPakaianDinasId(itemId);
            deletePayrollBajuDinas.setFlag(itemFlag);
            setPayrollBajuDinas(deletePayrollBajuDinas);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollBajuDinasAction.delete] end process <<<");

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
        logger.info("[PayrollBajuDinasAction.saveEdit] start process >>>");
        try {

            PayrollBajuDinas editPayrollBajuDinas = getPayrollBajuDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollBajuDinas.setLastUpdateWho(userLogin);
            editPayrollBajuDinas.setLastUpdate(updateTime);
            editPayrollBajuDinas.setAction("U");
            editPayrollBajuDinas.setFlag("Y");

            payrollBajuDinasBoProxy.saveEdit(editPayrollBajuDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBajuDinasAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBajuDinasAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollBajuDinasAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollBajuDinasAction.saveDelete] start process >>>");
        try {

            PayrollBajuDinas deletePayrollBajuDinas = getPayrollBajuDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollBajuDinas.setLastUpdate(updateTime);
            deletePayrollBajuDinas.setLastUpdateWho(userLogin);
            deletePayrollBajuDinas.setAction("U");
            deletePayrollBajuDinas.setFlag("N");

            payrollBajuDinasBoProxy.saveDelete(deletePayrollBajuDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBajuDinasAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBajuDinasAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollBajuDinasAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollBajuDinasAction.saveAdd] start process >>>");

        try {
            PayrollBajuDinas payrollBajuDinas = getPayrollBajuDinas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollBajuDinas.setCreatedWho(userLogin);
            payrollBajuDinas.setLastUpdate(updateTime);
            payrollBajuDinas.setCreatedDate(updateTime);
            payrollBajuDinas.setLastUpdateWho(userLogin);
            payrollBajuDinas.setAction("C");
            payrollBajuDinas.setFlag("Y");

            payrollBajuDinasBoProxy.saveAdd(payrollBajuDinas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PayrollBajuDinasAction.search] start process >>>");

        PayrollBajuDinas searchPayrollBajuDinas = getPayrollBajuDinas();
        List<PayrollBajuDinas> listOfsearchPayrollBajuDinas = new ArrayList();

        try {
            listOfsearchPayrollBajuDinas = payrollBajuDinasBoProxy.getByCriteria(searchPayrollBajuDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBajuDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBajuDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollBajuDinas);

        logger.info("[PayrollBajuDinasAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollBajuDinas() {
        logger.info("[PayrollBajuDinasAction.search] start process >>>");

        PayrollBajuDinas searchPayrollBajuDinas = new PayrollBajuDinas();
        searchPayrollBajuDinas.setFlag("Y");
        List<PayrollBajuDinas> listOfsearchPayrollBajuDinas = new ArrayList();

        try {
            listOfsearchPayrollBajuDinas = payrollBajuDinasBoProxy.getByCriteria(searchPayrollBajuDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBajuDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBajuDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollBajuDinas.addAll(listOfsearchPayrollBajuDinas);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollBajuDinasAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollBajuDinasAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollBajuDinas() {
        logger.info("[PayrollBajuDinasAction.search] start process >>>");

        PayrollBajuDinas searchPayrollBajuDinas = new PayrollBajuDinas();
        searchPayrollBajuDinas.setFlag("Y");
        List<PayrollBajuDinas> listOfsearchPayrollBajuDinas = new ArrayList();

        try {
            listOfsearchPayrollBajuDinas = payrollBajuDinasBoProxy.getByCriteria(searchPayrollBajuDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollBajuDinasBoProxy.saveErrorMessage(e.getMessage(), "PayrollBajuDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollBajuDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollBajuDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollBajuDinas");
        session.setAttribute("listOfResultPayrollBajuDinas", listOfsearchPayrollBajuDinas);

        logger.info("[PayrollBajuDinasAction.search] end process <<<");

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
