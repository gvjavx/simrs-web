package com.neurix.hris.master.payrollTunjanganJabatanStruktural.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo.PayrollTunjanganJabatanStrukturalBo;
import com.neurix.hris.transaksi.payroll.model.PayrollTunjanganJabatanStruktural;
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

public class PayrollTunjanganJabatanStrukturalAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganJabatanStrukturalAction.class);
    private PayrollTunjanganJabatanStrukturalBo payrollTunjanganJabatanStrukturalBoProxy;
    private PayrollTunjanganJabatanStruktural payrollTunjanganJabatanStruktural;


    private List<PayrollTunjanganJabatanStruktural> listComboPayrollTunjanganJabatanStruktural = new ArrayList<PayrollTunjanganJabatanStruktural>();

    public List<PayrollTunjanganJabatanStruktural> getListComboPayrollTunjanganJabatanStruktural() {
        return listComboPayrollTunjanganJabatanStruktural;
    }

    public void setListComboPayrollTunjanganJabatanStruktural(List<PayrollTunjanganJabatanStruktural> listComboPayrollTunjanganJabatanStruktural) {
        this.listComboPayrollTunjanganJabatanStruktural = listComboPayrollTunjanganJabatanStruktural;
    }

    public PayrollTunjanganJabatanStrukturalBo getPayrollTunjanganJabatanStrukturalBoProxy() {
        return payrollTunjanganJabatanStrukturalBoProxy;
    }

    public void setPayrollTunjanganJabatanStrukturalBoProxy(PayrollTunjanganJabatanStrukturalBo payrollTunjanganJabatanStrukturalBoProxy) {
        this.payrollTunjanganJabatanStrukturalBoProxy = payrollTunjanganJabatanStrukturalBoProxy;
    }

    public PayrollTunjanganJabatanStruktural getPayrollTunjanganJabatanStruktural() {
        return payrollTunjanganJabatanStruktural;
    }

    public void setPayrollTunjanganJabatanStruktural(PayrollTunjanganJabatanStruktural payrollTunjanganJabatanStruktural) {
        this.payrollTunjanganJabatanStruktural = payrollTunjanganJabatanStruktural;
    }

    private List<PayrollTunjanganJabatanStruktural> initComboPayrollTunjanganJabatanStruktural;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganJabatanStrukturalAction.logger = logger;
    }


    public List<PayrollTunjanganJabatanStruktural> getInitComboPayrollTunjanganJabatanStruktural() {
        return initComboPayrollTunjanganJabatanStruktural;
    }

    public void setInitComboPayrollTunjanganJabatanStruktural(List<PayrollTunjanganJabatanStruktural> initComboPayrollTunjanganJabatanStruktural) {
        this.initComboPayrollTunjanganJabatanStruktural = initComboPayrollTunjanganJabatanStruktural;
    }

    public PayrollTunjanganJabatanStruktural init(String kode, String flag){
        logger.info("[PayrollTunjanganJabatanStrukturalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PayrollTunjanganJabatanStruktural> listOfResult = (List<PayrollTunjanganJabatanStruktural>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PayrollTunjanganJabatanStruktural payrollTunjanganJabatanStruktural: listOfResult) {
                    if(kode.equalsIgnoreCase(payrollTunjanganJabatanStruktural.getTunjJabStrukturId()) && flag.equalsIgnoreCase(payrollTunjanganJabatanStruktural.getFlag())){
                        setPayrollTunjanganJabatanStruktural(payrollTunjanganJabatanStruktural);
                        break;
                    }
                }
            } else {
                setPayrollTunjanganJabatanStruktural(new PayrollTunjanganJabatanStruktural());
            }

            logger.info("[PayrollTunjanganJabatanStrukturalAction.init] end process >>>");
        }
        return getPayrollTunjanganJabatanStruktural();
    }

    @Override
    public String add() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.add] start process >>>");
        PayrollTunjanganJabatanStruktural addPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();
        setPayrollTunjanganJabatanStruktural(addPayrollTunjanganJabatanStruktural);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollTunjanganJabatanStrukturalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PayrollTunjanganJabatanStruktural editPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();

        if(itemFlag != null){
            try {
                editPayrollTunjanganJabatanStruktural = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.getPayrollTunjanganJabatanStrukturalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganJabatanStrukturalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollTunjanganJabatanStrukturalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollTunjanganJabatanStruktural != null) {
                setPayrollTunjanganJabatanStruktural(editPayrollTunjanganJabatanStruktural);
            } else {
                editPayrollTunjanganJabatanStruktural.setFlag(itemFlag);
                //editPayrollTunjanganJabatanStruktural.getSkalaGajiId(itemId);
                setPayrollTunjanganJabatanStruktural(editPayrollTunjanganJabatanStruktural);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollTunjanganJabatanStruktural.getSkalaGajiId(itemId);
            editPayrollTunjanganJabatanStruktural.setFlag(getFlag());
            setPayrollTunjanganJabatanStruktural(editPayrollTunjanganJabatanStruktural);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PayrollTunjanganJabatanStrukturalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PayrollTunjanganJabatanStruktural deletePayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();

        if (itemFlag != null ) {

            try {
                deletePayrollTunjanganJabatanStruktural = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollTunjanganJabatanStrukturalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollTunjanganJabatanStrukturalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollTunjanganJabatanStruktural != null) {
                setPayrollTunjanganJabatanStruktural(deletePayrollTunjanganJabatanStruktural);

            } else {
                //deletePayrollTunjanganJabatanStruktural.getSkalaGajiId(itemId);
                deletePayrollTunjanganJabatanStruktural.setFlag(itemFlag);
                setPayrollTunjanganJabatanStruktural(deletePayrollTunjanganJabatanStruktural);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollTunjanganJabatanStruktural.getSkalaGajiId(itemId);
            deletePayrollTunjanganJabatanStruktural.setFlag(itemFlag);
            setPayrollTunjanganJabatanStruktural(deletePayrollTunjanganJabatanStruktural);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PayrollTunjanganJabatanStrukturalAction.delete] end process <<<");

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
        logger.info("[PayrollTunjanganJabatanStrukturalAction.saveEdit] start process >>>");
        try {

            PayrollTunjanganJabatanStruktural editPayrollTunjanganJabatanStruktural = getPayrollTunjanganJabatanStruktural();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollTunjanganJabatanStruktural.setLastUpdateWho(userLogin);
            editPayrollTunjanganJabatanStruktural.setLastUpdate(updateTime);
            editPayrollTunjanganJabatanStruktural.setAction("U");
            editPayrollTunjanganJabatanStruktural.setFlag("Y");

            payrollTunjanganJabatanStrukturalBoProxy.saveEdit(editPayrollTunjanganJabatanStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganJabatanStrukturalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganJabatanStrukturalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganJabatanStrukturalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PayrollTunjanganJabatanStrukturalAction.saveDelete] start process >>>");
        try {

            PayrollTunjanganJabatanStruktural deletePayrollTunjanganJabatanStruktural = getPayrollTunjanganJabatanStruktural();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollTunjanganJabatanStruktural.setLastUpdate(updateTime);
            deletePayrollTunjanganJabatanStruktural.setLastUpdateWho(userLogin);
            deletePayrollTunjanganJabatanStruktural.setAction("U");
            deletePayrollTunjanganJabatanStruktural.setFlag("N");

            payrollTunjanganJabatanStrukturalBoProxy.saveDelete(deletePayrollTunjanganJabatanStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganJabatanStrukturalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganJabatanStrukturalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PayrollTunjanganJabatanStrukturalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PayrollTunjanganJabatanStrukturalAction.saveAdd] start process >>>");

        try {
            PayrollTunjanganJabatanStruktural payrollTunjanganJabatanStruktural = getPayrollTunjanganJabatanStruktural();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollTunjanganJabatanStruktural.setCreatedWho(userLogin);
            payrollTunjanganJabatanStruktural.setLastUpdate(updateTime);
            payrollTunjanganJabatanStruktural.setCreatedDate(updateTime);
            payrollTunjanganJabatanStruktural.setLastUpdateWho(userLogin);
            payrollTunjanganJabatanStruktural.setAction("C");
            payrollTunjanganJabatanStruktural.setFlag("Y");

            payrollTunjanganJabatanStrukturalBoProxy.saveAdd(payrollTunjanganJabatanStruktural);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "payrollTunjanganJabatanStrukturalBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[payrollTunjanganJabatanStrukturalAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[payrollTunjanganJabatanStrukturalAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[payrollTunjanganJabatanStrukturalAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.search] start process >>>");

        PayrollTunjanganJabatanStruktural searchPayrollTunjanganJabatanStruktural = getPayrollTunjanganJabatanStruktural();
        List<PayrollTunjanganJabatanStruktural> listOfsearchPayrollTunjanganJabatanStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganJabatanStruktural = payrollTunjanganJabatanStrukturalBoProxy.getByCriteria(searchPayrollTunjanganJabatanStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganJabatanStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganJabatanStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPayrollTunjanganJabatanStruktural);

        logger.info("[PayrollTunjanganJabatanStrukturalAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollTunjanganJabatanStruktural() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.search] start process >>>");

        PayrollTunjanganJabatanStruktural searchPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();
        searchPayrollTunjanganJabatanStruktural.setFlag("Y");
        List<PayrollTunjanganJabatanStruktural> listOfsearchPayrollTunjanganJabatanStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganJabatanStruktural = payrollTunjanganJabatanStrukturalBoProxy.getByCriteria(searchPayrollTunjanganJabatanStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganJabatanStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganJabatanStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollTunjanganJabatanStruktural.addAll(listOfsearchPayrollTunjanganJabatanStruktural);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PayrollTunjanganJabatanStrukturalAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPayrollTunjanganJabatanStruktural() {
        logger.info("[PayrollTunjanganJabatanStrukturalAction.search] start process >>>");

        PayrollTunjanganJabatanStruktural searchPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();
        searchPayrollTunjanganJabatanStruktural.setFlag("Y");
        List<PayrollTunjanganJabatanStruktural> listOfsearchPayrollTunjanganJabatanStruktural = new ArrayList();

        try {
            listOfsearchPayrollTunjanganJabatanStruktural = payrollTunjanganJabatanStrukturalBoProxy.getByCriteria(searchPayrollTunjanganJabatanStruktural);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollTunjanganJabatanStrukturalBoProxy.saveErrorMessage(e.getMessage(), "PayrollTunjanganJabatanStrukturalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollTunjanganJabatanStrukturalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollTunjanganJabatanStrukturalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPayrollTunjanganJabatanStruktural");
        session.setAttribute("listOfResultPayrollTunjanganJabatanStruktural", listOfsearchPayrollTunjanganJabatanStruktural);

        logger.info("[PayrollTunjanganJabatanStrukturalAction.search] end process <<<");

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
