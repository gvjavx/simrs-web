package com.neurix.hris.master.smkBudget.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkBudget.bo.SmkBudgetBo;
import com.neurix.hris.master.smkBudget.model.SmkBudget;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
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

public class SmkBudgetAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkBudgetAction.class);
    private SmkBudgetBo smkBudgetBoProxy;
    private SmkBudget smkBudget;

    private List listOfComboPeriode = new ArrayList();


    public List getListOfComboPeriode() {
        return listOfComboPeriode;
    }

    public void setListOfComboPeriode(List listOfComboPeriode) {
        this.listOfComboPeriode = listOfComboPeriode;
    }

    public SmkBudgetBo getSmkBudgetBoProxy() {
        return smkBudgetBoProxy;
    }

    public void setSmkBudgetBoProxy(SmkBudgetBo smkBudgetBoProxy) {
        this.smkBudgetBoProxy = smkBudgetBoProxy;
    }

    public SmkBudget getSmkBudget() {
        return smkBudget;
    }

    public void setSmkBudget(SmkBudget smkBudget) {
        this.smkBudget = smkBudget;
    }

    private List<SmkBudget> initComboSmkBudget;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkBudgetAction.logger = logger;
    }


    public List<SmkBudget> getInitComboSmkBudget() {
        return initComboSmkBudget;
    }

    public void setInitComboSmkBudget(List<SmkBudget> initComboSmkBudget) {
        this.initComboSmkBudget = initComboSmkBudget;
    }

    public SmkBudget init(String kode, String flag){
        logger.info("[SmkBudgetAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkBudget> listOfResult = (List<SmkBudget>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkBudget smkBudget: listOfResult) {
                    if(kode.equalsIgnoreCase(smkBudget.getBudgetId()) && flag.equalsIgnoreCase(smkBudget.getFlag())){
                        setSmkBudget(smkBudget);
                        break;
                    }
                }
            } else {
                setSmkBudget(new SmkBudget());
            }

            logger.info("[SmkBudgetAction.init] end process >>>");
        }
        return getSmkBudget();
    }

    @Override
    public String add() {
        logger.info("[SmkBudgetAction.add] start process >>>");
        SmkBudget addSmkBudget = new SmkBudget();
        setSmkBudget(addSmkBudget);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkBudgetAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkBudgetAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkBudget editSmkBudget = new SmkBudget();

        if(itemFlag != null){
            try {
                editSmkBudget = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.getSmkBudgetByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkBudgetAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkBudgetAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkBudget != null) {
                setSmkBudget(editSmkBudget);
            } else {
                editSmkBudget.setFlag(itemFlag);
                editSmkBudget.setBudgetId(itemId);
                setSmkBudget(editSmkBudget);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkBudget.setBudgetId(itemId);
            editSmkBudget.setFlag(getFlag());
            setSmkBudget(editSmkBudget);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkBudgetAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkBudgetAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkBudget deleteSmkBudget = new SmkBudget();

        if (itemFlag != null ) {

            try {
                deleteSmkBudget = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkBudgetAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkBudgetAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkBudget != null) {
                setSmkBudget(deleteSmkBudget);

            } else {
                deleteSmkBudget.setBudgetId(itemId);
                deleteSmkBudget.setFlag(itemFlag);
                setSmkBudget(deleteSmkBudget);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkBudget.setBudgetId(itemId);
            deleteSmkBudget.setFlag(itemFlag);
            setSmkBudget(deleteSmkBudget);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkBudgetAction.delete] end process <<<");

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
        logger.info("[SmkBudgetAction.saveEdit] start process >>>");
        try {

            SmkBudget editSmkBudget = getSmkBudget();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkBudget.setLastUpdateWho(userLogin);
            editSmkBudget.setLastUpdate(updateTime);
            editSmkBudget.setAction("U");
            editSmkBudget.setFlag("Y");

            smkBudgetBoProxy.saveEdit(editSmkBudget);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkBudgetAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkBudgetAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkBudgetAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkBudgetAction.saveDelete] start process >>>");
        try {

            SmkBudget deleteSmkBudget = getSmkBudget();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkBudget.setLastUpdate(updateTime);
            deleteSmkBudget.setLastUpdateWho(userLogin);
            deleteSmkBudget.setAction("U");
            deleteSmkBudget.setFlag("N");

            smkBudgetBoProxy.saveDelete(deleteSmkBudget);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkBudgetAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkBudgetAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkBudgetAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkBudgetAction.saveAdd] start process >>>");

        try {
            SmkBudget smkBudget = getSmkBudget();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkBudget.setCreatedWho(userLogin);
            smkBudget.setLastUpdate(updateTime);
            smkBudget.setCreatedDate(updateTime);
            smkBudget.setLastUpdateWho(userLogin);
            smkBudget.setAction("C");
            smkBudget.setFlag("Y");

            smkBudgetBoProxy.saveAdd(smkBudget);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkBudgetAction.search] start process >>>");

        SmkBudget searchSmkBudget = getSmkBudget();
        List<SmkBudget> listOfsearchSmkBudget = new ArrayList();

        try {
            listOfsearchSmkBudget = smkBudgetBoProxy.getByCriteria(searchSmkBudget);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkBudgetAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkBudgetAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkBudget);

        logger.info("[SmkBudgetAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkBudgetAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkBudgetAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkBudget() {
        logger.info("[SmkBudgetAction.search] start process >>>");

        SmkBudget searchSmkBudget = new SmkBudget();
        searchSmkBudget.setFlag("Y");
        List<SmkBudget> listOfsearchSmkBudget = new ArrayList();

        try {
            listOfsearchSmkBudget = smkBudgetBoProxy.getByCriteria(searchSmkBudget);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBoProxy.saveErrorMessage(e.getMessage(), "SmkBudgetBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkBudgetAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkBudgetAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkBudget");
        session.setAttribute("listOfResultSmkBudget", listOfsearchSmkBudget);

        logger.info("[SmkBudgetAction.search] end process <<<");

        return "";
    }
    public String initComboPeriode() {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int batas=20;
        List listOfPeriode = new ArrayList();
        for (int i=0;i<batas;i++){
            listOfPeriode.add(year-i);
        }
        listOfComboPeriode.addAll(listOfPeriode);

        return "init_combo_periode";
    }
    public List initComboStrukturJabatan(String query, String branchId) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<StrukturJabatan> listOfStruktur = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        StrukturJabatanBo strukturJabatanBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfStruktur = strukturJabatanBo.getComboStrukturJabatanWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfStruktur;
    }
    public List initComboSmkBudget(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<SmkBudget> listOfSmkBudget = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBudgetBo smkBudgetBo = (SmkBudgetBo) ctx.getBean("smkBudgetBoProxy");
        try {
            listOfSmkBudget = smkBudgetBo.getComboSmkBudgetWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfSmkBudget;
    }
    public List PointCalculate (double bobot,double target,double realisasi){
        logger.info("[SMKBudget.PointCalculate] start process >>>");
        List<SmkBudget> listOfPointCalculate = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBudgetBo smkBudgetBo = (SmkBudgetBo) ctx.getBean("smkBudgetBoProxy");
        try {
            listOfPointCalculate = smkBudgetBo.getCalculatePoint(bobot,target,realisasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBudgetBo.saveErrorMessage(e.getMessage(), "SmkBudgetBo.getCalculatePoint");
            } catch (GeneralBOException e1) {
                logger.error("[SMKBudgetAction.PointCalculate] Error when saving error,", e1);
            }
            logger.error("[SMKBudgetAction.PointCalculate] Error when get Calculate Point," + "[" + logId + "] Found problem when retrieving Calculate Point, please inform to your admin.", e);
        }

        logger.info("[SMKBudgetAction.PointCalculate] end process <<<");


        return listOfPointCalculate;
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
