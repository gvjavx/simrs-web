package com.neurix.hris.master.cuti.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.cuti.bo.CutiBo;
import com.neurix.hris.master.cuti.model.Cuti;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.model.ItCutiPegawaiEntity;
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

public class CutiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(CutiAction.class);
    private CutiBo cutiBoProxy;
    private Cuti cuti;
    private List<Cuti> listComboCuti = new ArrayList<Cuti>();
    private List<Cuti> listComboCuti2 = new ArrayList<Cuti>();

    public List<Cuti> getListComboCuti2() {
        return listComboCuti2;
    }

    public void setListComboCuti2(List<Cuti> listComboCuti2) {
        this.listComboCuti2 = listComboCuti2;
    }

    public List<Cuti> getListComboCuti() {
        return listComboCuti;
    }

    public void setListComboCuti(List<Cuti> listComboCuti) {
        this.listComboCuti = listComboCuti;
    }

    public CutiBo getCutiBoProxy() {
        return cutiBoProxy;
    }

    public void setCutiBoProxy(CutiBo cutiBoProxy) {
        this.cutiBoProxy = cutiBoProxy;
    }

    public Cuti getCuti() {
        return cuti;
    }

    public void setCuti(Cuti cuti) {
        this.cuti = cuti;
    }

    private List<Cuti> initComboCuti;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiAction.logger = logger;
    }


    public List<Cuti> getInitComboCuti() {
        return initComboCuti;
    }

    public void setInitComboCuti(List<Cuti> initComboCuti) {
        this.initComboCuti = initComboCuti;
    }

    public Cuti init(String kode, String flag){
        logger.info("[CutiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Cuti> listOfResult = (List<Cuti>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Cuti cuti: listOfResult) {
                    if(kode.equalsIgnoreCase(cuti.getCutiId()) && flag.equalsIgnoreCase(cuti.getFlag())){
                        setCuti(cuti);
                        break;
                    }
                }
            } else {
                setCuti(new Cuti());
            }

            logger.info("[CutiAction.init] end process >>>");
        }
        return getCuti();
    }

    @Override
    public String add() {
        logger.info("[CutiAction.add] start process >>>");
        Cuti addCuti = new Cuti();
        setCuti(addCuti);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CutiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[CutiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Cuti editCuti = new Cuti();

        if(itemFlag != null){
            try {
                editCuti = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getCutiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[CutiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editCuti != null) {
                setCuti(editCuti);
            } else {
                editCuti.setFlag(itemFlag);
                editCuti.setCutiId(itemId);
                setCuti(editCuti);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editCuti.setCutiId(itemId);
            editCuti.setFlag(getFlag());
            setCuti(editCuti);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[CutiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[CutiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Cuti deleteCuti = new Cuti();

        if (itemFlag != null ) {

            try {
                deleteCuti = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[CutiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CutiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteCuti != null) {
                setCuti(deleteCuti);

            } else {
                deleteCuti.setCutiId(itemId);
                deleteCuti.setFlag(itemFlag);
                setCuti(deleteCuti);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteCuti.setCutiId(itemId);
            deleteCuti.setFlag(itemFlag);
            setCuti(deleteCuti);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[CutiAction.delete] end process <<<");

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
        logger.info("[CutiAction.saveEdit] start process >>>");
        try {

            Cuti editCuti = getCuti();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editCuti.setLastUpdateWho(userLogin);
            editCuti.setLastUpdate(updateTime);
            editCuti.setAction("U");
            editCuti.setFlag("Y");

            cutiBoProxy.saveEdit(editCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[CutiAction.saveDelete] start process >>>");
        try {

            Cuti deleteCuti = getCuti();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteCuti.setLastUpdate(updateTime);
            deleteCuti.setLastUpdateWho(userLogin);
            deleteCuti.setAction("U");
            deleteCuti.setFlag("N");

            cutiBoProxy.saveDelete(deleteCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CutiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[CutiAction.saveAdd] start process >>>");

        try {
            Cuti cuti = getCuti();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            cuti.setCreatedWho(userLogin);
            cuti.setLastUpdate(updateTime);
            cuti.setCreatedDate(updateTime);
            cuti.setLastUpdateWho(userLogin);
            cuti.setAction("C");
            cuti.setFlag("Y");

            cutiBoProxy.saveAdd(cuti);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[CutiAction.search] start process >>>");

        Cuti searchCuti = getCuti();
        List<Cuti> listOfsearchCuti = new ArrayList();

        try {
            listOfsearchCuti = cutiBoProxy.getByCriteria(searchCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchCuti);

        logger.info("[CutiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[CutiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[CutiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initCuti() {
        logger.info("[CutiAction.search] start process >>>");

        Cuti searchCuti = new Cuti();
        searchCuti.setFlag("Y");
        List<Cuti> listOfsearchCuti = new ArrayList();

        try {
            listOfsearchCuti = cutiBoProxy.getByCriteria(searchCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CutiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCuti");
        session.setAttribute("listOfResultCuti", listOfsearchCuti);

        logger.info("[CutiAction.search] end process <<<");

        return "";
    }

    public String initComboCuti() {
        logger.info("[BranchAction.search] start process >>>");

        Cuti searchCuti = new Cuti();
        List<Cuti> listOfSearchCuti = new ArrayList();
        searchCuti.setFlag("Y");
        searchCuti.setJenisCuti("normal");
        try {
            listOfSearchCuti = cutiBoProxy.getByCriteria(searchCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.search] Error when saving error,", e1);
            }
            logger.error("[CutiAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboCuti.addAll(listOfSearchCuti);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public String initComboCuti2() {
        logger.info("[BranchAction.search] start process >>>");

        Cuti searchCuti = new Cuti();
        List<Cuti> listOfSearchCuti = new ArrayList();
        searchCuti.setCutiId("CT007");
        searchCuti.setFlag("Y");
        try {
            listOfSearchCuti = cutiBoProxy.getByCriteria(searchCuti);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "CutiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiAction.search] Error when saving error,", e1);
            }
            logger.error("[CutiAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboCuti2.addAll(listOfSearchCuti);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public List initComboCutiTipe(String query) {
        logger.info("[PermohonanLahanAction.initComboLokasiKebun] start process >>>");

        List<Cuti> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CutiBo cutiBo = (CutiBo) ctx.getBean("cutiBoProxy");

        try {
            listOfAlat = cutiBo.getComboCutiTipeWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
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
