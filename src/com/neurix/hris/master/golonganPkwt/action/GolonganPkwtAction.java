package com.neurix.hris.master.golonganPkwt.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganPkwt.bo.GolonganPkwtBo;
import com.neurix.hris.master.golonganPkwt.model.GolonganPkwt;
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

public class GolonganPkwtAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(GolonganPkwtAction.class);
    private GolonganPkwt golonganPkwt;

    private List<GolonganPkwt> listComboGolongan = new ArrayList<GolonganPkwt>();
    private GolonganPkwtBo golonganPkwtBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganPkwtAction.logger = logger;
    }

    public GolonganPkwtBo getGolonganBoProxy() {
        return golonganPkwtBoProxy;
    }

    public void setGolonganBoProxy(GolonganPkwtBo golonganPkwtBoProxy) {
        this.golonganPkwtBoProxy = golonganPkwtBoProxy;
    }

    public GolonganPkwt getGolongan() {
        return golonganPkwt;
    }

    public void setGolongan(GolonganPkwt golonganPkwt) {
        this.golonganPkwt = golonganPkwt;
    }

    public List<GolonganPkwt> getListComboGolongan() {
        return listComboGolongan;
    }

    public void setListComboGolongan(List<GolonganPkwt> listComboGolongan) {
        this.listComboGolongan = listComboGolongan;
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

        listComboGolongan.addAll(listOfSearchGolongan);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public GolonganPkwt init(String kode, String flag){
        logger.info("[GolonganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GolonganPkwt> listOfResult = (List<GolonganPkwt>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (GolonganPkwt golonganPkwt: listOfResult) {
                    if(kode.equalsIgnoreCase(golonganPkwt.getGolonganPkwtId()) && flag.equalsIgnoreCase(golonganPkwt.getFlag())){
                        setGolongan(golonganPkwt);
                        break;
                    }
                }
            } else {
                setGolongan(new GolonganPkwt());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getGolongan();
    }

    @Override
    public String add() {
        logger.info("[GolonganAction.add] start process >>>");
        GolonganPkwt addGolongan = new GolonganPkwt();
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

        GolonganPkwt editGolongan = new GolonganPkwt();

        if(itemFlag != null){
            try {
                editGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getGolonganByCriteria");
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
                editGolongan.setGolonganPkwtId(itemId);
                setGolongan(editGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editGolongan.setGolonganPkwtId(itemId);
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
        GolonganPkwt deleteGolongan = new GolonganPkwt();

        if (itemFlag != null ) {

            try {
                deleteGolongan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getAlatById");
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
                deleteGolongan.setGolonganPkwtId(itemId);
                deleteGolongan.setFlag(itemFlag);
                setGolongan(deleteGolongan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteGolongan.setGolonganPkwtId(itemId);
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

            GolonganPkwt editGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editGolongan.setLastUpdateWho(userLogin);
            editGolongan.setLastUpdate(updateTime);
            editGolongan.setAction("U");
            editGolongan.setFlag("Y");

            golonganPkwtBoProxy.saveEdit(editGolongan);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[GolonganAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[GolonganAction.saveDelete] start process >>>");
        try {

            GolonganPkwt deleteGolongan = getGolongan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteGolongan.setLastUpdate(updateTime);
            deleteGolongan.setLastUpdateWho(userLogin);
            deleteGolongan.setAction("U");
            deleteGolongan.setFlag("N");

            golonganPkwtBoProxy.saveDelete(deleteGolongan);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[AlatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[AlatAction.saveAdd] start process >>>");

        try {
            GolonganPkwt golonganPkwt = getGolongan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            golonganPkwt.setCreatedWho(userLogin);
            golonganPkwt.setLastUpdate(updateTime);
            golonganPkwt.setCreatedDate(updateTime);
            golonganPkwt.setLastUpdateWho(userLogin);
            golonganPkwt.setAction("C");
            golonganPkwt.setFlag("Y");

            golonganPkwtBoProxy.saveAdd(golonganPkwt);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[GolonganAction.search] start process >>>");

        GolonganPkwt searchGolongan = getGolongan();
        List<GolonganPkwt> listOfsearchGolongan = new ArrayList();

        try {
            listOfsearchGolongan = golonganPkwtBoProxy.getByCriteria(searchGolongan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = golonganPkwtBoProxy.saveErrorMessage(e.getMessage(), "GolonganBO.getByCriteria");
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

    public void setGolonganPkwtBoProxy(GolonganPkwtBo golonganPkwtBoProxy) {
        this.golonganPkwtBoProxy = golonganPkwtBoProxy;
    }

    public GolonganPkwtBo getGolonganPkwtBoProxy() {
        return golonganPkwtBoProxy;
    }
}
