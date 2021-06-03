package com.neurix.hris.master.payrollSkalaGajiDplkPegawai.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.bo.PayrollSkalaGajiDplkPegawaiBo;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.payrollSkalaGajiDplkPegawai;
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
// MENU : Iuran DPLK
public class PayrollSkalaGajiDplkPegawaiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiDplkPegawaiAction.class);
    private PayrollSkalaGajiDplkPegawaiBo payrollSkalaGajiDplkPegawaiBoProxy;
    private payrollSkalaGajiDplkPegawai payrollSkalaGajiDplkPegawai;

    public payrollSkalaGajiDplkPegawai getPayrollSkalaGajiDplkPegawai() {
        return payrollSkalaGajiDplkPegawai;
    }

    public void setPayrollSkalaGajiDplkPegawai(payrollSkalaGajiDplkPegawai payrollSkalaGajiDplkPegawai) {
        this.payrollSkalaGajiDplkPegawai = payrollSkalaGajiDplkPegawai;
    }

    public PayrollSkalaGajiDplkPegawaiBo getPayrollSkalaGajiDplkPegawaiBoProxy() {
        return payrollSkalaGajiDplkPegawaiBoProxy;
    }

    public void setPayrollSkalaGajiDplkPegawaiBoProxy(PayrollSkalaGajiDplkPegawaiBo payrollSkalaGajiDplkPegawaiBoProxy) {
        this.payrollSkalaGajiDplkPegawaiBoProxy = payrollSkalaGajiDplkPegawaiBoProxy;
    }

    private List<payrollSkalaGajiDplkPegawai> listComboPayrollSkalaGajiDplkPegawai = new ArrayList<payrollSkalaGajiDplkPegawai>();

    public List<payrollSkalaGajiDplkPegawai> getListComboPayrollSkalaGajiDplkPegawai() {
        return listComboPayrollSkalaGajiDplkPegawai;
    }

    public void setListComboPayrollSkalaGajiDplkPegawai(List<payrollSkalaGajiDplkPegawai> listComboPayrollSkalaGajiDplkPegawai) {
        this.listComboPayrollSkalaGajiDplkPegawai = listComboPayrollSkalaGajiDplkPegawai;
    }

    private List<payrollSkalaGajiDplkPegawai> initComboPayrollSkalaGajiDplkPegawai;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiDplkPegawaiAction.logger = logger;
    }


    public List<payrollSkalaGajiDplkPegawai> getInitComboPayrollSkalaGajiDplkPegawai() {
        return initComboPayrollSkalaGajiDplkPegawai;
    }

    public void setInitComboPayrollSkalaGajiDplkPegawai(List<payrollSkalaGajiDplkPegawai> initComboPayrollSkalaGajiDplkPegawai) {
        this.initComboPayrollSkalaGajiDplkPegawai = initComboPayrollSkalaGajiDplkPegawai;
    }

    public payrollSkalaGajiDplkPegawai init(String kode, String flag){
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<payrollSkalaGajiDplkPegawai> listOfResult = (List<payrollSkalaGajiDplkPegawai>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (payrollSkalaGajiDplkPegawai payrollSkalaGajiDplkPegawai : listOfResult) {
                    if(kode.equalsIgnoreCase(payrollSkalaGajiDplkPegawai.getSkalaGajiPensiunId()) && flag.equalsIgnoreCase(payrollSkalaGajiDplkPegawai.getFlag())){
                        setPayrollSkalaGajiDplkPegawai(payrollSkalaGajiDplkPegawai);
                        break;
                    }
                }
            } else {
                setPayrollSkalaGajiDplkPegawai(new payrollSkalaGajiDplkPegawai());
            }

            logger.info("[PayrollSkalaGajiDplkPegawaiAction.init] end process >>>");
        }
        return getPayrollSkalaGajiDplkPegawai();
    }

    @Override
    public String add() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.add] start process >>>");
        payrollSkalaGajiDplkPegawai addPayrollSkalaGajiDplkPegawai = new payrollSkalaGajiDplkPegawai();
        setPayrollSkalaGajiDplkPegawai(addPayrollSkalaGajiDplkPegawai);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        payrollSkalaGajiDplkPegawai editPayrollSkalaGajiDplkPegawai = new payrollSkalaGajiDplkPegawai();

        if(itemFlag != null){
            try {
                editPayrollSkalaGajiDplkPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getPayrollSkalaGajiPensiunByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPayrollSkalaGajiDplkPegawai != null) {
                setPayrollSkalaGajiDplkPegawai(editPayrollSkalaGajiDplkPegawai);
            } else {
                editPayrollSkalaGajiDplkPegawai.setFlag(itemFlag);
                setPayrollSkalaGajiDplkPegawai(editPayrollSkalaGajiDplkPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPayrollSkalaGajiDplkPegawai.setFlag(getFlag());
            setPayrollSkalaGajiDplkPegawai(editPayrollSkalaGajiDplkPegawai);
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
        payrollSkalaGajiDplkPegawai deletePayrollSkalaGajiDplkPegawai = new payrollSkalaGajiDplkPegawai();

        if (itemFlag != null ) {

            try {
                deletePayrollSkalaGajiDplkPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePayrollSkalaGajiDplkPegawai != null) {
                setPayrollSkalaGajiDplkPegawai(deletePayrollSkalaGajiDplkPegawai);

            } else {
                deletePayrollSkalaGajiDplkPegawai.setFlag(itemFlag);
                setPayrollSkalaGajiDplkPegawai(deletePayrollSkalaGajiDplkPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePayrollSkalaGajiDplkPegawai.setFlag(itemFlag);
            setPayrollSkalaGajiDplkPegawai(deletePayrollSkalaGajiDplkPegawai);
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

            payrollSkalaGajiDplkPegawai editPayrollSkalaGajiDplkPegawai = getPayrollSkalaGajiDplkPegawai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPayrollSkalaGajiDplkPegawai.setLastUpdateWho(userLogin);
            editPayrollSkalaGajiDplkPegawai.setLastUpdate(updateTime);
            editPayrollSkalaGajiDplkPegawai.setAction("U");
            editPayrollSkalaGajiDplkPegawai.setFlag("Y");

            payrollSkalaGajiDplkPegawaiBoProxy.saveEdit(editPayrollSkalaGajiDplkPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.saveEdit");
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

            payrollSkalaGajiDplkPegawai deletePayrollSkalaGajiDplkPegawai = getPayrollSkalaGajiDplkPegawai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePayrollSkalaGajiDplkPegawai.setLastUpdate(updateTime);
            deletePayrollSkalaGajiDplkPegawai.setLastUpdateWho(userLogin);
            deletePayrollSkalaGajiDplkPegawai.setAction("D");
            deletePayrollSkalaGajiDplkPegawai.setFlag("N");

            payrollSkalaGajiDplkPegawaiBoProxy.saveDelete(deletePayrollSkalaGajiDplkPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.saveDelete");
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
            payrollSkalaGajiDplkPegawai payrollSkalaGajiDplkPegawai = getPayrollSkalaGajiDplkPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            payrollSkalaGajiDplkPegawai.setCreatedWho(userLogin);
            payrollSkalaGajiDplkPegawai.setLastUpdate(updateTime);
            payrollSkalaGajiDplkPegawai.setCreatedDate(updateTime);
            payrollSkalaGajiDplkPegawai.setLastUpdateWho(userLogin);
            payrollSkalaGajiDplkPegawai.setAction("C");
            payrollSkalaGajiDplkPegawai.setFlag("Y");

            payrollSkalaGajiDplkPegawaiBoProxy.saveAdd(payrollSkalaGajiDplkPegawai);
        }catch (GeneralBOException e) {
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.saveAdd] " + e.getMessage());
            throw new GeneralBOException(e);
//            Long logId = null;
//            try {
//                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
//            } catch (GeneralBOException e1) {
//                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
//                return ERROR;
//            }
//            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
//            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiDplkPegawai searchPayrollSkalaGajiDplkPegawai = getPayrollSkalaGajiDplkPegawai();
        List<payrollSkalaGajiDplkPegawai> listOfsearchPayrollSkalaGajiDplkPegawai = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiDplkPegawai = payrollSkalaGajiDplkPegawaiBoProxy.getByCriteria(searchPayrollSkalaGajiDplkPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
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
        session.setAttribute("listOfResult", listOfsearchPayrollSkalaGajiDplkPegawai);

        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiDplkPegawai searchPayrollSkalaGajiDplkPegawai = getPayrollSkalaGajiDplkPegawai();

        searchPayrollSkalaGajiDplkPegawai.setFlag("Y");
        List<payrollSkalaGajiDplkPegawai> listOfsearchPayrollSkalaGajiDplkPegawai = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiDplkPegawai = payrollSkalaGajiDplkPegawaiBoProxy.getByCriteria(searchPayrollSkalaGajiDplkPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PayrollSkalaGajiDplkPegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PayrollSkalaGajiDplkPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPayrollSkalaGajiDplkPegawai.addAll(listOfsearchPayrollSkalaGajiDplkPegawai);
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

    public String initPayrollSkalaGajiPensiun() {
        logger.info("[PayrollSkalaGajiDplkPegawaiAction.search] start process >>>");

        payrollSkalaGajiDplkPegawai searchPayrollSkalaGajiDplkPegawai = new payrollSkalaGajiDplkPegawai();
        searchPayrollSkalaGajiDplkPegawai.setFlag("Y");
        List<payrollSkalaGajiDplkPegawai> listOfsearchPayrollSkalaGajiDplkPegawai = new ArrayList();

        try {
            listOfsearchPayrollSkalaGajiDplkPegawai = payrollSkalaGajiDplkPegawaiBoProxy.getByCriteria(searchPayrollSkalaGajiDplkPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = payrollSkalaGajiDplkPegawaiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiPensiunBO.getByCriteria");
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
        session.setAttribute("listOfResultPayrollSkalaGajiPensiun", listOfsearchPayrollSkalaGajiDplkPegawai);

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
