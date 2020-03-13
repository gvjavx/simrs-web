package com.neurix.hris.master.payrollTunjanganStrategis.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollTunjanganStrategis.bo.PayrollTunjanganStrategisBo;
import com.neurix.hris.transaksi.payroll.model.PayrollTunjanganStrategis;
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

public class PayrollTunjanganStrategisAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganStrategisAction.class);
    private PayrollTunjanganStrategisBo payrollTunjanganStrategisBoProxy;
    private PayrollTunjanganStrategis payrollTunjanganStrategis;


    private List<PayrollTunjanganStrategis> listComboPayrollTunjanganStrategis = new ArrayList<PayrollTunjanganStrategis>();

    public List<PayrollTunjanganStrategis> getListComboPayrollTunjanganStrategis() {
        return listComboPayrollTunjanganStrategis;
    }

    public void setListComboPayrollTunjanganStrategis(List<PayrollTunjanganStrategis> listComboPayrollTunjanganStrategis) {
        this.listComboPayrollTunjanganStrategis = listComboPayrollTunjanganStrategis;
    }

    public PayrollTunjanganStrategisBo getPayrollTunjanganStrategisBoProxy() {
        return payrollTunjanganStrategisBoProxy;
    }

    public void setPayrollTunjanganStrategisBoProxy(PayrollTunjanganStrategisBo payrollTunjanganStrategisBoProxy) {
        this.payrollTunjanganStrategisBoProxy = payrollTunjanganStrategisBoProxy;
    }

    public PayrollTunjanganStrategis getPayrollTunjanganStrategis() {
        return payrollTunjanganStrategis;
    }

    public void setPayrollTunjanganStrategis(PayrollTunjanganStrategis payrollTunjanganStrategis) {
        this.payrollTunjanganStrategis = payrollTunjanganStrategis;
    }

    private List<PayrollTunjanganStrategis> initComboPayrollTunjanganStrategis;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganStrategisAction.logger = logger;
    }


    public List<PayrollTunjanganStrategis> getInitComboPayrollTunjanganStrategis() {
        return initComboPayrollTunjanganStrategis;
    }

    public void setInitComboPayrollTunjanganStrategis(List<PayrollTunjanganStrategis> initComboPayrollTunjanganStrategis) {
        this.initComboPayrollTunjanganStrategis = initComboPayrollTunjanganStrategis;
    }

    public PayrollTunjanganStrategis init(String kode, String flag){
        logger.info("[PayrollTunjanganStrategisAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollTunjanganStrategis> listOfResult = (List<PayrollTunjanganStrategis>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollTunjanganStrategis payrollTunjanganStrategis: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollTunjanganStrategis.getTunjStrategisId()) && flag.equalsIgnoreCase(payrollTunjanganStrategis.getFlag())){
                        setPayrollTunjanganStrategis(payrollTunjanganStrategis);
                        break;
                    }
                }
            } else {
                setPayrollTunjanganStrategis(new PayrollTunjanganStrategis());
            }

            logger.info("[PayrollTunjanganStrategisAction.init] end process >>>");
        }
        return getPayrollTunjanganStrategis();
    }

    @Override
    public String add() {
        logger.info("[PayrollTunjanganStrategisAction.add] start process >>>");
        PayrollTunjanganStrategis addPayrollTunjanganStrategis = new PayrollTunjanganStrategis();
        setPayrollTunjanganStrategis(addPayrollTunjanganStrategis);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollTunjanganStrategisAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollTunjanganStrategisAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollTunjanganStrategis editPayrollTunjanganStrategis = new PayrollTunjanganStrategis();

        if(itemFlag != null){
            try {
                editPayrollTunjanganStrategis = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.getPayrollTunjanganStrategisByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganStrategisAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollTunjanganStrategisAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollTunjanganStrategis != null) {
                setPayrollTunjanganStrategis(editPayrollTunjanganStrategis);
            } else {
                editPayrollTunjanganStrategis.setFlag(itemFlag);
                //editPayrollTunjanganStrategis.getSkalaGajiId(itemId);
                setPayrollTunjanganStrategis(editPayrollTunjanganStrategis);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollTunjanganStrategis.getSkalaGajiId(itemId);
            editPayrollTunjanganStrategis.setFlag(getFlag());
            setPayrollTunjanganStrategis(editPayrollTunjanganStrategis);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollTunjanganStrategisAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollTunjanganStrategisAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollTunjanganStrategis deletePayrollTunjanganStrategis = new PayrollTunjanganStrategis();

        if (itemFlag != null ) {

            try {
                deletePayrollTunjanganStrategis = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganStrategisAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollTunjanganStrategisAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollTunjanganStrategis != null) {
                setPayrollTunjanganStrategis(deletePayrollTunjanganStrategis);

            } else {
                //deletePayrollTunjanganStrategis.getSkalaGajiId(itemId);
                deletePayrollTunjanganStrategis.setFlag(itemFlag);
                setPayrollTunjanganStrategis(deletePayrollTunjanganStrategis);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollTunjanganStrategis.getSkalaGajiId(itemId);
            deletePayrollTunjanganStrategis.setFlag(itemFlag);
            setPayrollTunjanganStrategis(deletePayrollTunjanganStrategis);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollTunjanganStrategisAction.delete] end process <<<");

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
        logger.info("[PayrollTunjanganStrategisAction.saveEdit] start process >>>");
        try {

            PayrollTunjanganStrategis editPayrollTunjanganStrategis = getPayrollTunjanganStrategis();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollTunjanganStrategis.setLastUpdateWho(userLogin);
            editPayrollTunjanganStrategis.setLastUpdate(updateTime);
            editPayrollTunjanganStrategis.setAction("U");
            editPayrollTunjanganStrategis.setFlag("Y");

            payrollTunjanganStrategisBoProxy.saveEdit(editPayrollTunjanganStrategis);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrategisAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrategisAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganStrategisAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollTunjanganStrategisAction.saveDelete] start process >>>");
        try {

            PayrollTunjanganStrategis deletePayrollTunjanganStrategis = getPayrollTunjanganStrategis();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollTunjanganStrategis.setLastUpdate(updateTime);
            deletePayrollTunjanganStrategis.setLastUpdateWho(userLogin);
            deletePayrollTunjanganStrategis.setAction("U");
            deletePayrollTunjanganStrategis.setFlag("N");

            payrollTunjanganStrategisBoProxy.saveDelete(deletePayrollTunjanganStrategis);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrategisAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrategisAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganStrategisAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollTunjanganStrategisAction.saveAdd] start process >>>");

        try {
            PayrollTunjanganStrategis payrollTunjanganStrategis = getPayrollTunjanganStrategis();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollTunjanganStrategis.setCreatedWho(userLogin);
            payrollTunjanganStrategis.setLastUpdate(updateTime);
            payrollTunjanganStrategis.setCreatedDate(updateTime);
            payrollTunjanganStrategis.setLastUpdateWho(userLogin);
            payrollTunjanganStrategis.setAction("C");
            payrollTunjanganStrategis.setFlag("Y");

            payrollTunjanganStrategisBoProxy.saveAdd(payrollTunjanganStrategis);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "payrollTunjanganStrategisBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollTunjanganStrategisAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[payrollTunjanganStrategisAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollTunjanganStrategisAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollTunjanganStrategisAction.search] start process >>>");

        PayrollTunjanganStrategis searchPayrollTunjanganStrategis = getPayrollTunjanganStrategis();
        List<PayrollTunjanganStrategis> listOfsearchPayrollTunjanganStrategis = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStrategis = payrollTunjanganStrategisBoProxy.getByCriteria(searchPayrollTunjanganStrategis);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrategisAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrategisAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollTunjanganStrategis);

        logger.info("[PayrollTunjanganStrategisAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollTunjanganStrategis() {
        logger.info("[PayrollTunjanganStrategisAction.search] start process >>>");

        PayrollTunjanganStrategis searchPayrollTunjanganStrategis = new PayrollTunjanganStrategis();
        searchPayrollTunjanganStrategis.setFlag("Y");
        List<PayrollTunjanganStrategis> listOfsearchPayrollTunjanganStrategis = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStrategis = payrollTunjanganStrategisBoProxy.getByCriteria(searchPayrollTunjanganStrategis);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrategisAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrategisAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollTunjanganStrategis.addAll(listOfsearchPayrollTunjanganStrategis);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollTunjanganStrategisAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollTunjanganStrategisAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollTunjanganStrategis() {
        logger.info("[PayrollTunjanganStrategisAction.search] start process >>>");

        PayrollTunjanganStrategis searchPayrollTunjanganStrategis = new PayrollTunjanganStrategis();
        searchPayrollTunjanganStrategis.setFlag("Y");
        List<PayrollTunjanganStrategis> listOfsearchPayrollTunjanganStrategis = new ArrayList();

        try {
            listOfsearchPayrollTunjanganStrategis = payrollTunjanganStrategisBoProxy.getByCriteria(searchPayrollTunjanganStrategis);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganStrategisBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganStrategisBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganStrategisAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganStrategisAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollTunjanganStrategis");
        session.setAttribute("listOfResultPayrollTunjanganStrategis", listOfsearchPayrollTunjanganStrategis);

        logger.info("[PayrollTunjanganStrategisAction.search] end process <<<");

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
