package com.neurix.hris.master.payrollSkalaGaji.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGaji.bo.PayrollSkalaGajiBo;
import com.neurix.hris.master.payrollSkalaGaji.model.PayrollSkalaGaji;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PayrollSkalaGajiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiAction.class);
    private PayrollSkalaGajiBo payrollSkalaGajiBoProxy;
    private PayrollSkalaGaji payrollSkalaGaji;

    private List<PayrollSkalaGaji> listComboPayrollSkalaGaji = new ArrayList<PayrollSkalaGaji>();

    public List<PayrollSkalaGaji> getListComboPayrollSkalaGaji() {
        return listComboPayrollSkalaGaji;
    }

    public void setListComboPayrollSkalaGaji(List<PayrollSkalaGaji> listComboPayrollSkalaGaji) {
        this.listComboPayrollSkalaGaji = listComboPayrollSkalaGaji;
    }

    public PayrollSkalaGajiBo getPayrollSkalaGajiBoProxy() {
        return payrollSkalaGajiBoProxy;
    }

    public void setPayrollSkalaGajiBoProxy(PayrollSkalaGajiBo payrollSkalaGajiBoProxy) {
        this.payrollSkalaGajiBoProxy = payrollSkalaGajiBoProxy;
    }

    public PayrollSkalaGaji getPayrollSkalaGaji() {
        return payrollSkalaGaji;
    }

    public void setPayrollSkalaGaji(PayrollSkalaGaji payrollSkalaGaji) {
        this.payrollSkalaGaji = payrollSkalaGaji;
    }

    private List<PayrollSkalaGaji> initComboPayrollSkalaGaji;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiAction.logger = logger;
    }


    public List<PayrollSkalaGaji> getInitComboPayrollSkalaGaji() {
        return initComboPayrollSkalaGaji;
    }

    public void setInitComboPayrollSkalaGaji(List<PayrollSkalaGaji> initComboPayrollSkalaGaji) {
        this.initComboPayrollSkalaGaji = initComboPayrollSkalaGaji;
    }

    public PayrollSkalaGaji init(String kode, String flag){
        logger.info("[PayrollSkalaGajiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollSkalaGaji> listOfResult = (List<PayrollSkalaGaji>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollSkalaGaji payrollSkalaGaji: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGaji.getSkalaGajiId()) && flag.equalsIgnoreCase(payrollSkalaGaji.getFlag())){
                        setPayrollSkalaGaji(payrollSkalaGaji);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGaji(new PayrollSkalaGaji());
            }

            logger.info("[PayrollSkalaGajiAction.init] end process >>>");
        }
        return getPayrollSkalaGaji();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiAction.add] start process >>>");
        PayrollSkalaGaji addPayrollSkalaGaji = new PayrollSkalaGaji();
        setPayrollSkalaGaji(addPayrollSkalaGaji);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollSkalaGaji editPayrollSkalaGaji = new PayrollSkalaGaji();

        if(itemFlag != null){
            try {
                editPayrollSkalaGaji = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getPayrollSkalaGajiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGaji != null) {
                setPayrollSkalaGaji(editPayrollSkalaGaji);
            } else {
                editPayrollSkalaGaji.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setPayrollSkalaGaji(editPayrollSkalaGaji);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editPayrollSkalaGaji.setFlag(getFlag());
            setPayrollSkalaGaji(editPayrollSkalaGaji);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollSkalaGajiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollSkalaGajiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollSkalaGaji deletePayrollSkalaGaji = new PayrollSkalaGaji();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGaji = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGaji != null) {
                setPayrollSkalaGaji(deletePayrollSkalaGaji);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deletePayrollSkalaGaji.setFlag(itemFlag);
                setPayrollSkalaGaji(deletePayrollSkalaGaji);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deletePayrollSkalaGaji.setFlag(itemFlag);
            setPayrollSkalaGaji(deletePayrollSkalaGaji);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollSkalaGajiAction.delete] end process <<<");

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

    public String cekIfExistDwr(String golonganId){
        String status = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PayrollSkalaGajiBo skalaGajiBo = (PayrollSkalaGajiBo) ctx.getBean("payrollSkalaGajiBo");
        status = skalaGajiBo.cekStatus(golonganId);
        return status;

    }

    public String saveEdit(){
        logger.info("[PayrollSkalaGajiAction.saveEdit] start process >>>");
        try {

            PayrollSkalaGaji editPayrollSkalaGaji = getPayrollSkalaGaji();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGaji.setLastUpdateWho(userLogin);
            editPayrollSkalaGaji.setLastUpdate(updateTime);
            editPayrollSkalaGaji.setAction("U");
            editPayrollSkalaGaji.setFlag("Y");

            payrollSkalaGajiBoProxy.saveEdit(editPayrollSkalaGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollSkalaGajiAction.saveDelete] start process >>>");
        try {

            PayrollSkalaGaji deletePayrollSkalaGaji = getPayrollSkalaGaji();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGaji.setLastUpdate(updateTime);
            deletePayrollSkalaGaji.setLastUpdateWho(userLogin);
            deletePayrollSkalaGaji.setAction("U");
            deletePayrollSkalaGaji.setFlag("N");

            payrollSkalaGajiBoProxy.saveDelete(deletePayrollSkalaGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollSkalaGajiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollSkalaGajiAction.saveAdd] start process >>>");

        try {
            PayrollSkalaGaji payrollSkalaGaji = getPayrollSkalaGaji();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollSkalaGaji.setCreatedWho(userLogin);
            payrollSkalaGaji.setLastUpdate(updateTime);
            payrollSkalaGaji.setCreatedDate(updateTime);
            payrollSkalaGaji.setLastUpdateWho(userLogin);
            payrollSkalaGaji.setAction("C");
            payrollSkalaGaji.setFlag("Y");

            payrollSkalaGajiBoProxy.saveAdd(payrollSkalaGaji);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "payrollSkalaGajiBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollSkalaGajiAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[payrollSkalaGajiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollSkalaGajiAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollSkalaGajiAction.search] start process >>>");

        PayrollSkalaGaji searchPayrollSkalaGaji = getPayrollSkalaGaji();
        List<PayrollSkalaGaji> listOfsearchPayrollSkalaGaji = new ArrayList();

        try {
            listOfsearchPayrollSkalaGaji = payrollSkalaGajiBoProxy.getByCriteria(searchPayrollSkalaGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGaji);

        logger.info("[PayrollSkalaGajiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGaji() {
        logger.info("[PayrollSkalaGajiAction.search] start process >>>");

        PayrollSkalaGaji searchPayrollSkalaGaji = new PayrollSkalaGaji();
        searchPayrollSkalaGaji.setFlag("Y");
        List<PayrollSkalaGaji> listOfsearchPayrollSkalaGaji = new ArrayList();

        try {
            listOfsearchPayrollSkalaGaji = payrollSkalaGajiBoProxy.getByCriteria(searchPayrollSkalaGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGaji.addAll(listOfsearchPayrollSkalaGaji);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollSkalaGajiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollSkalaGajiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollSkalaGaji() {
        logger.info("[PayrollSkalaGajiAction.search] start process >>>");

        PayrollSkalaGaji searchPayrollSkalaGaji = new PayrollSkalaGaji();
        searchPayrollSkalaGaji.setFlag("Y");
        List<PayrollSkalaGaji> listOfsearchPayrollSkalaGaji = new ArrayList();

        try {
            listOfsearchPayrollSkalaGaji = payrollSkalaGajiBoProxy.getByCriteria(searchPayrollSkalaGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollSkalaGaji");
        session.setAttribute("listOfResultPayrollSkalaGaji", listOfsearchPayrollSkalaGaji);

        logger.info("[PayrollSkalaGajiAction.search] end process <<<");

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
