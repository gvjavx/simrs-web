package com.neurix.hris.master.golongan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golongan.bo.GolonganBo;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golonganDapen.bo.GolonganDapenBo;
import com.neurix.hris.master.golonganDapen.model.GolonganDapen;
import com.neurix.hris.master.golonganPkwt.bo.GolonganPkwtBo;
import com.neurix.hris.master.golonganPkwt.model.GolonganPkwt;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class GolonganAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(GolonganAction.class);
    private GolonganBo golonganBoProxy;
    private Golongan golongan;
    private List<Golongan> listComboGolongan = new ArrayList<Golongan>();
    private List<GolonganDapen> listComboGolonganDapen = new ArrayList<GolonganDapen>();
    private List<GolonganPkwt> listComboGolonganPkwt = new ArrayList<GolonganPkwt>();
    private List<GolonganPkwt> listComboGolonganPkwtHistory = new ArrayList<GolonganPkwt>();
    private GolonganDapenBo golonganDapenBoProxy;
    private GolonganDapen golonganDapen;
    private GolonganPkwtBo golonganPkwtBoProxy;
    private GolonganPkwt golonganPkwt;

    public List<GolonganPkwt> getListComboGolonganPkwtHistory() {
        return listComboGolonganPkwtHistory;
    }

    public void setListComboGolonganPkwtHistory(List<GolonganPkwt> listComboGolonganPkwtHistory) {
        this.listComboGolonganPkwtHistory = listComboGolonganPkwtHistory;
    }

    public GolonganPkwt getGolonganPkwt() {
        return golonganPkwt;
    }

    public void setGolonganPkwt(GolonganPkwt golonganPkwt) {
        this.golonganPkwt = golonganPkwt;
    }

    public GolonganPkwtBo getGolonganPkwtBoProxy() {
        return golonganPkwtBoProxy;
    }

    public void setGolonganPkwtBoProxy(GolonganPkwtBo golonganPkwtBoProxy) {
        this.golonganPkwtBoProxy = golonganPkwtBoProxy;
    }

    public List<GolonganPkwt> getListComboGolonganPkwt() {
        return listComboGolonganPkwt;
    }

    public void setListComboGolonganPkwt(List<GolonganPkwt> listComboGolonganPkwt) {
        this.listComboGolonganPkwt = listComboGolonganPkwt;
    }

    public GolonganDapen getGolonganDapen() {
        return golonganDapen;
    }

    public void setGolonganDapen(GolonganDapen golonganDapen) {
        this.golonganDapen = golonganDapen;
    }

    public GolonganDapenBo getGolonganDapenBoProxy() {
        return golonganDapenBoProxy;
    }

    public void setGolonganDapenBoProxy(GolonganDapenBo golonganDapenBoProxy) {
        this.golonganDapenBoProxy = golonganDapenBoProxy;
    }

    public List<GolonganDapen> getListComboGolonganDapen() {
        return listComboGolonganDapen;
    }

    public void setListComboGolonganDapen(List<GolonganDapen> listComboGolonganDapen) {
        this.listComboGolonganDapen = listComboGolonganDapen;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganAction.logger = logger;
    }

    public GolonganBo getGolonganBoProxy() {
        return golonganBoProxy;
    }

    public void setGolonganBoProxy(GolonganBo golonganBoProxy) {
        this.golonganBoProxy = golonganBoProxy;
    }

    public Golongan getGolongan() {
        return golongan;
    }

    public void setGolongan(Golongan golongan) {
        this.golongan = golongan;
    }

    public List<Golongan> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<Golongan> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
    }

    public String initComboGolongan() {
        logger.info("[BranchAction.search] start process >>>");

        Golongan searchGolongan = new Golongan();
        List<Golongan> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = golonganBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
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
    public String initComboGolonganDapen() {
        logger.info("[BranchAction.search] start process >>>");

        GolonganDapen searchGolongan = new GolonganDapen();
        List<GolonganDapen> listOfSearchGolonganDapen = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolonganDapen = golonganDapenBoProxy.getByCriteria(searchGolongan);
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

        listComboGolonganDapen.addAll(listOfSearchGolonganDapen);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public String initComboGolonganPkwt() {
        logger.info("[BranchAction.search] start process >>>");

        GolonganPkwt searchGolongan = new GolonganPkwt();
        List<GolonganPkwt> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = golonganPkwtBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboGolonganPkwt.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }
    public String initComboGolonganPkwtHistory() {
        logger.info("[BranchAction.search] start process >>>");

        GolonganPkwt searchGolongan = new GolonganPkwt();
        List<GolonganPkwt> listOfSearchGolongan = new ArrayList();
        searchGolongan.setFlag("Y");
        try {
            listOfSearchGolongan = golonganPkwtBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboGolonganPkwtHistory.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public Golongan init(String kode, String flag){
        logger.info("[GolonganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Golongan> listOfResult = (List<Golongan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Golongan golongan: listOfResult) {
                    if(kode.equalsIgnoreCase(golongan.getGolonganId()) && flag.equalsIgnoreCase(golongan.getFlag())){
                        setGolongan(golongan);
                        break;
                    }
                }
            } else {
                setGolongan(new Golongan());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getGolongan();
    }
    public GolonganPkwt initPkwt(String kode, String flag){
        logger.info("[GolonganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GolonganPkwt> listOfResult = (List<GolonganPkwt>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (GolonganPkwt golongan: listOfResult) {
                    if(kode.equalsIgnoreCase(golongan.getGolonganPkwtId()) && flag.equalsIgnoreCase(golongan.getFlag())){
                        setGolonganPkwt(golongan);
                        break;
                    }
                }
            } else {
                setGolongan(new Golongan());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getGolonganPkwt();
    }

    @Override
    public String add() {
        logger.info("[GolonganAction.add] start process >>>");
        Golongan addGolongan = new Golongan();
        setGolongan(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[GolonganAction.add] stop process >>>");
        return "init_add";
    }
    public String addPkwt() {
        logger.info("[GolonganAction.add] start process >>>");
        Golongan addGolongan = new Golongan();
        setGolongan(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[GolonganAction.add] stop process >>>");
        return "init_add_pkwt";
    }

    @Override
    public String edit() {
        logger.info("[GolonganAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Golongan editGolongan = new Golongan();

        if(itemFlag != null){
            try {
                editGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getGolonganByCriteria");
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
                editGolongan.setGolonganId(itemId);
                setGolongan(editGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGolongan.setGolonganId(itemId);
            editGolongan.setFlag(getFlag());
            setGolongan(editGolongan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[GolonganAction.edit] end process >>>");
        return "init_edit";
    }
    public String editPkwt() {
        logger.info("[GolonganAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        GolonganPkwt editGolongan = new GolonganPkwt();

        if(itemFlag != null){
            try {
                editGolongan = initPkwt(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getGolonganByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GolonganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[GolonganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editGolongan != null) {
                setGolonganPkwt(editGolongan);
            } else {
                editGolongan.setFlag(itemFlag);
                editGolongan.setGolonganPkwtId(itemId);
                setGolonganPkwt(editGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGolongan.setGolonganPkwtId(itemId);
            editGolongan.setFlag(getFlag());
            setGolonganPkwt(editGolongan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure_pkwt";
        }

        setAddOrEdit(true);
        logger.info("[GolonganAction.edit] end process >>>");
        return "init_edit_pkwt";
    }

    @Override
    public String delete() {
        logger.info("[GolonganAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Golongan deleteGolongan = new Golongan();

        if (itemFlag != null ) {

            try {
                deleteGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
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
                deleteGolongan.setGolonganId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setGolongan(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteGolongan.setGolonganId(itemId);
            deleteGolongan.setFlag(itemFlag);
            setGolongan(deleteGolongan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

        return "init_delete";
    }
    public String deletePkwt() {
        logger.info("[GolonganAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        GolonganPkwt deleteGolongan = new GolonganPkwt();

        if (itemFlag != null ) {

            try {
                deleteGolongan = initPkwt(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[GolonganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[GolonganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteGolongan != null) {
                setGolonganPkwt(deleteGolongan);

            } else {
                deleteGolongan.setGolonganPkwtId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setGolonganPkwt(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteGolongan.setGolonganPkwtId(itemId);
            deleteGolongan.setFlag(itemFlag);
            setGolonganPkwt(deleteGolongan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AlatAction.delete] end process <<<");

        return "init_delete_pkwt";
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

            Golongan editGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            golonganBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[GolonganAction.saveEdit] end process <<<");
        return "success_save_edit";
    }
    public String saveEditPkwt(){
        logger.info("[GolonganAction.saveEdit] start process >>>");
        try {

            GolonganPkwt editGolongan = getGolonganPkwt();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            golonganPkwtBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[GolonganAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[GolonganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[GolonganAction.saveEdit] end process <<<");

        return "success_save_edit_pkwt";
    }

    public String saveDelete(){
        logger.info("[GolonganAction.saveDelete] start process >>>");
        try {

            Golongan deleteGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            golonganBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AlatAction.saveDelete] end process <<<");
        return "success_save_delete";
    }
    public String saveDeletePkwt(){
        logger.info("[GolonganAction.saveDelete] start process >>>");
        try {

            GolonganPkwt deleteGolongan = getGolonganPkwt();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            golonganPkwtBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "LiburBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AlatAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete_pkwt";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            Golongan golongan = getGolongan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            golongan.setCreatedWho(userLogin);
            golongan.setLastUpdate(updateTime);
            golongan.setCreatedDate(updateTime);
            golongan.setLastUpdateWho(userLogin);
            golongan.setAction("C");
            golongan.setFlag("Y");

            golonganBoProxy.saveAdd(golongan);
        }catch (GeneralBOException e) {
           throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }
    public String saveAddPkwt(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            GolonganPkwt golongan = getGolonganPkwt();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            golongan.setCreatedWho(userLogin);
            golongan.setLastUpdate(updateTime);
            golongan.setCreatedDate(updateTime);
            golongan.setLastUpdateWho(userLogin);
            golongan.setAction("C");
            golongan.setFlag("Y");

            golonganPkwtBoProxy.saveAdd(golongan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        return "success_save_add_pkwt";
    }

    @Override
    public String search() {
        logger.info("[GolonganAction.search] start process >>>");

        Golongan searchGolongan = getGolongan();
        List<Golongan> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = golonganBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
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
    public String searchPkwt() {
        logger.info("[GolonganAction.search] start process >>>");

        GolonganPkwt searchGolongan = getGolonganPkwt();
        List<GolonganPkwt> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = golonganPkwtBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
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

        return "success_pkwt";
    }

    @Override
    public String initForm() {
        logger.info("[GolonganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[GolonganAction.initForm] end process >>>");
        return INPUT;
    }
    public String initFormPkwt() {
        logger.info("[GolonganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[GolonganAction.initForm] end process >>>");
        return "input_pkwt";
    }

    public List<Golongan> getRangeMasaGol(String golPen){
        List<Golongan> masaGol = new ArrayList<>();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            GolonganBo golonganBo = (GolonganBo) ctx.getBean("golonganBoProxy");
            masaGol = golonganBo.getRangeMasaGol(golPen);
        } catch (GeneralBOException e) {
            logger.error("[GolonganAction.getRangeMasaGol] Error when searching Masa Golongan by Golongan Pensiun," + "[] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=] Found problem when searching data by criteria, please inform to your admin" );
        }

        return masaGol;
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
