package com.neurix.hris.master.branchTunjangan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.branchTunjangan.bo.BranchTunjanganBo;
import com.neurix.hris.master.branchTunjangan.model.BranchTunjangan;
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

public class BranchTunjanganAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(BranchTunjanganAction.class);
    private BranchTunjanganBo branchTunjanganBoProxy;
    private BranchTunjangan branchTunjangan;

    private List<BranchTunjangan> listComboBranchTunjangan = new ArrayList<BranchTunjangan>();

    public List<BranchTunjangan> getListComboBranchTunjangan() {
        return listComboBranchTunjangan;
    }

    public void setListComboBranchTunjangan(List<BranchTunjangan> listComboBranchTunjangan) {
        this.listComboBranchTunjangan = listComboBranchTunjangan;
    }

    public BranchTunjanganBo getBranchTunjanganBoProxy() {
        return branchTunjanganBoProxy;
    }

    public void setBranchTunjanganBoProxy(BranchTunjanganBo branchTunjanganBoProxy) {
        this.branchTunjanganBoProxy = branchTunjanganBoProxy;
    }

    public BranchTunjangan getBranchTunjangan() {
        return branchTunjangan;
    }

    public void setBranchTunjangan(BranchTunjangan branchTunjangan) {
        this.branchTunjangan = branchTunjangan;
    }

    private List<BranchTunjangan> initComboBranchTunjangan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BranchTunjanganAction.logger = logger;
    }


    public List<BranchTunjangan> getInitComboBranchTunjangan() {
        return initComboBranchTunjangan;
    }

    public void setInitComboBranchTunjangan(List<BranchTunjangan> initComboBranchTunjangan) {
        this.initComboBranchTunjangan = initComboBranchTunjangan;
    }

    public BranchTunjangan init(String kode, String flag){
        logger.info("[BranchTunjanganAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BranchTunjangan> listOfResult = (List<BranchTunjangan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (BranchTunjangan branchTunjangan: listOfResult) {
                    if(kode.equalsIgnoreCase(branchTunjangan.getBranchTunjanganId()) && flag.equalsIgnoreCase(branchTunjangan.getFlag())){
                        setBranchTunjangan(branchTunjangan);
                        break;
                    }
                }
            } else {
                setBranchTunjangan(new BranchTunjangan());
            }

            logger.info("[BranchTunjanganAction.init] end process >>>");
        }
        return getBranchTunjangan();
    }

    @Override
    public String add() {
        logger.info("[BranchTunjanganAction.add] start process >>>");
        BranchTunjangan addBranchTunjangan = new BranchTunjangan();
        setBranchTunjangan(addBranchTunjangan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BranchTunjanganAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BranchTunjanganAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        BranchTunjangan editBranchTunjangan = new BranchTunjangan();

        if(itemFlag != null){
            try {
                editBranchTunjangan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.getBranchTunjanganByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BranchTunjanganAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BranchTunjanganAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBranchTunjangan != null) {
                setBranchTunjangan(editBranchTunjangan);
            } else {
                editBranchTunjangan.setFlag(itemFlag);
                editBranchTunjangan.setBranchTunjanganId(itemId);
                setBranchTunjangan(editBranchTunjangan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBranchTunjangan.setBranchTunjanganId(itemId);
            editBranchTunjangan.setFlag(getFlag());
            setBranchTunjangan(editBranchTunjangan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BranchTunjanganAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BranchTunjanganAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        BranchTunjangan deleteBranchTunjangan = new BranchTunjangan();

        if (itemFlag != null ) {

            try {
                deleteBranchTunjangan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[BranchTunjanganAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[BranchTunjanganAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteBranchTunjangan != null) {
                setBranchTunjangan(deleteBranchTunjangan);

            } else {
                deleteBranchTunjangan.setBranchTunjanganId(itemId);
                deleteBranchTunjangan.setFlag(itemFlag);
                setBranchTunjangan(deleteBranchTunjangan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteBranchTunjangan.setBranchTunjanganId(itemId);
            deleteBranchTunjangan.setFlag(itemFlag);
            setBranchTunjangan(deleteBranchTunjangan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[BranchTunjanganAction.delete] end process <<<");

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
        logger.info("[BranchTunjanganAction.saveEdit] start process >>>");
        try {

            BranchTunjangan editBranchTunjangan = getBranchTunjangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editBranchTunjangan.setLastUpdateWho(userLogin);
            editBranchTunjangan.setLastUpdate(updateTime);
            editBranchTunjangan.setAction("U");
            editBranchTunjangan.setFlag("Y");

            branchTunjanganBoProxy.saveEdit(editBranchTunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BranchTunjanganAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BranchTunjanganAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BranchTunjanganAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[BranchTunjanganAction.saveDelete] start process >>>");
        try {

            BranchTunjangan deleteBranchTunjangan = getBranchTunjangan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteBranchTunjangan.setLastUpdate(updateTime);
            deleteBranchTunjangan.setLastUpdateWho(userLogin);
            deleteBranchTunjangan.setAction("U");
            deleteBranchTunjangan.setFlag("N");

            branchTunjanganBoProxy.saveDelete(deleteBranchTunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[BranchTunjanganAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BranchTunjanganAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BranchTunjanganAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[BranchTunjanganAction.saveAdd] start process >>>");

        try {
            BranchTunjangan branchTunjangan = getBranchTunjangan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            branchTunjangan.setCreatedWho(userLogin);
            branchTunjangan.setLastUpdate(updateTime);
            branchTunjangan.setCreatedDate(updateTime);
            branchTunjangan.setLastUpdateWho(userLogin);
            branchTunjangan.setAction("C");
            branchTunjangan.setFlag("Y");

            branchTunjanganBoProxy.saveAdd(branchTunjangan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[BranchTunjanganAction.search] start process >>>");

        BranchTunjangan searchBranchTunjangan = getBranchTunjangan();
        List<BranchTunjangan> listOfsearchBranchTunjangan = new ArrayList();

        try {
            listOfsearchBranchTunjangan = branchTunjanganBoProxy.getByCriteria(searchBranchTunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchTunjanganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BranchTunjanganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchBranchTunjangan);

        logger.info("[BranchTunjanganAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchBranchTunjangan() {
        logger.info("[BranchTunjanganAction.search] start process >>>");

        BranchTunjangan searchBranchTunjangan = new BranchTunjangan();
        searchBranchTunjangan.setFlag("Y");
        List<BranchTunjangan> listOfsearchBranchTunjangan = new ArrayList();

        try {
            listOfsearchBranchTunjangan = branchTunjanganBoProxy.getByCriteria(searchBranchTunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchTunjanganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BranchTunjanganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboBranchTunjangan.addAll(listOfsearchBranchTunjangan);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BranchTunjanganAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BranchTunjanganAction.initForm] end process >>>");
        return INPUT;
    }

    public String initBranchTunjangan() {
        logger.info("[BranchTunjanganAction.search] start process >>>");

        BranchTunjangan searchBranchTunjangan = new BranchTunjangan();
        searchBranchTunjangan.setFlag("Y");
        List<BranchTunjangan> listOfsearchBranchTunjangan = new ArrayList();

        try {
            listOfsearchBranchTunjangan = branchTunjanganBoProxy.getByCriteria(searchBranchTunjangan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchTunjanganBoProxy.saveErrorMessage(e.getMessage(), "BranchTunjanganBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchTunjanganAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BranchTunjanganAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBranchTunjangan");
        session.setAttribute("listOfResultBranchTunjangan", listOfsearchBranchTunjangan);

        logger.info("[BranchTunjanganAction.search] end process <<<");

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
