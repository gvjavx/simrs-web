package com.neurix.hris.master.golonganDapen.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganDapen.bo.GolonganDapenBo;
import com.neurix.hris.master.golonganDapen.model.GolonganDapen;
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

public class GolonganDapenAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(GolonganDapenAction.class);
    private GolonganDapen golonganDapen;
    private GolonganDapenBo golonganDapenBoProxy;
    private List<GolonganDapen> golonganDapenList;
    private List<GolonganDapen> listComboGolongan = new ArrayList<GolonganDapen>();


    public List<GolonganDapen> getGolonganDapenList() {
        return golonganDapenList;
    }

    public void setGolonganDapenList(List<GolonganDapen> golonganDapenList) {
        this.golonganDapenList = golonganDapenList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganDapenAction.logger = logger;
    }

    public GolonganDapenBo getGolonganBoProxy() {
        return golonganDapenBoProxy;
    }

    public void setGolonganBoProxy(GolonganDapenBo golonganDapenBoProxy) {
        this.golonganDapenBoProxy = golonganDapenBoProxy;
    }

    public GolonganDapen getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(GolonganDapen golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public GolonganDapen getGolongan() {
        return golonganDapen;
    }

    public void setGolongan(GolonganDapen golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public List<GolonganDapen> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<GolonganDapen> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
    }

    public String initComboGolonganDapen() {
        logger.info("[BranchAction.search] start process >>>");

        GolonganDapen searchGolongan = new GolonganDapen();
        List<GolonganDapen> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = golonganDapenBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboGolongan.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public GolonganDapen init(String kode, String flag){
        logger.info("[GolonganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GolonganDapen> listOfResult = (List<GolonganDapen>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (GolonganDapen golonganDapen: listOfResult) {
                    if(kode.equalsIgnoreCase(golonganDapen.getGolonganDapenId()) && flag.equalsIgnoreCase(golonganDapen.getFlag())){
                        setGolongan(golonganDapen);
                        break;
                    }
                }
            } else {
                setGolongan(new GolonganDapen());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getGolongan();
    }

    @Override
    public String add() {
        logger.info("[GolonganAction.add] start process >>>");
        GolonganDapen addGolongan = new GolonganDapen();
        setGolongan(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GolonganAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[GolonganAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        GolonganDapen editGolongan = new GolonganDapen();

        if(itemFlag != null){
            try {
                editGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getGolonganByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GolonganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[GolonganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGolongan != null) {
                setGolongan(editGolongan);
            } else {
                editGolongan.setFlag(itemFlag);
                editGolongan.setGolonganDapenId(itemId);
                setGolongan(editGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGolongan.setGolonganDapenId(itemId);
            editGolongan.setFlag(getFlag());
            setGolongan(editGolongan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[GolonganAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[GolonganAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        GolonganDapen deleteGolongan = new GolonganDapen();

        if (itemFlag != null ) {

            try {
                deleteGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[GolonganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[GolonganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteGolongan != null) {
                setGolongan(deleteGolongan);

            } else {
                deleteGolongan.setGolonganDapenId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setGolongan(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteGolongan.setGolonganDapenId(itemId);
            deleteGolongan.setFlag(itemFlag);
            setGolongan(deleteGolongan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

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
        logger.info("[GolonganAction.saveEdit] start process >>>");
        try {

            GolonganDapen editGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            golonganDapenBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GolonganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[GolonganAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[GolonganAction.saveDelete] start process >>>");
        try {

            GolonganDapen deleteGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            golonganDapenBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            GolonganDapen golonganDapen = getGolongan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            golonganDapen.setCreatedWho(userLogin);
            golonganDapen.setLastUpdate(updateTime);
            golonganDapen.setCreatedDate(updateTime);
            golonganDapen.setLastUpdateWho(userLogin);
            golonganDapen.setAction("C");
            golonganDapen.setFlag("Y");

            golonganDapenBoProxy.saveAdd(golonganDapen);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[GolonganAction.search] start process >>>");

        GolonganDapen searchGolongan = getGolongan();
        List<GolonganDapen> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = golonganDapenBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganDapenBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[GolonganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchGolongan);

        logger.info("[AlatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[GolonganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[GolonganAction.initForm] end process >>>");
        return INPUT;
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

    public void setGolonganDapenBoProxy(GolonganDapenBo golonganDapenBoProxy) {
        this.golonganDapenBoProxy = golonganDapenBoProxy;
    }

    public GolonganDapenBo getGolonganDapenBoProxy() {
        return golonganDapenBoProxy;
    }
}
