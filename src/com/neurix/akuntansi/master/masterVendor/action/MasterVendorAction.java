package com.neurix.akuntansi.master.masterVendor.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

public class MasterVendorAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MasterVendorAction.class);
    private MasterVendorBo masterVendorBoProxy;
    private MasterVendor masterVendor;
    private List<MasterVendor> listOfComboVendor = new ArrayList<MasterVendor>();

    public List<MasterVendor> getListOfComboVendor() {
        return listOfComboVendor;
    }

    public void setListOfComboVendor(List<MasterVendor> listOfComboVendor) {
        this.listOfComboVendor = listOfComboVendor;
    }

    public MasterVendorBo getMasterVendorBoProxy() {
        return masterVendorBoProxy;
    }

    public void setMasterVendorBoProxy(MasterVendorBo masterVendorBoProxy) {
        this.masterVendorBoProxy = masterVendorBoProxy;
    }

    public MasterVendor getMasterVendor() {
        return masterVendor;
    }

    public void setMasterVendor(MasterVendor masterVendor) {
        this.masterVendor = masterVendor;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasterVendorAction.logger = logger;
    }


    public String initComboVendor() {
        logger.info("[VendorAction.initComboVendor] start process >>>");

        MasterVendor search = new MasterVendor();
        List<MasterVendor> vendorList = new ArrayList();
        search.setFlag("Y");
        try {
            vendorList = masterVendorBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[VendorAction.initComboVendor] Error when saving error,", e1);
            }
            logger.error("[VendorAction.initComboVendor] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboVendor.addAll(vendorList);
        logger.info("[VendorAction.initComboVendor] end process <<<");
        return SUCCESS;
    }

    public MasterVendor init(String kode, String flag){
        logger.info("[VendorAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MasterVendor> listOfResult = (List<MasterVendor>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MasterVendor vendor: listOfResult) {
                    if(kode.equalsIgnoreCase(vendor.getNomorMaster()) && flag.equalsIgnoreCase(vendor.getFlag())){
                        setMasterVendor(vendor);
                        break;
                    }
                }
            } else {
                setMasterVendor(new MasterVendor());
            }

            logger.info("[VendorAction.init] end process >>>");
        }
        return getMasterVendor();
    }

    @Override
    public String add() {
        logger.info("[VendorAction.add] start process >>>");
        MasterVendor addVendor = new MasterVendor();
        setMasterVendor(addVendor);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[VendorAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[VendorAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MasterVendor editVendor = new MasterVendor();

        if(itemFlag != null){
            try {
                editVendor = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.getVendorByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[VendorAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[VendorAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editVendor != null) {
                setMasterVendor(editVendor);
            } else {
                editVendor.setFlag(itemFlag);
                editVendor.setNomorMaster(itemId);
                setMasterVendor(editVendor);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editVendor.setNomorMaster(itemId);
            editVendor.setFlag(getFlag());
            setMasterVendor(editVendor);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[VendorAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[VendorAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MasterVendor deleteVendor = new MasterVendor();

        if (itemFlag != null ) {

            try {
                deleteVendor = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[VendorAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[VendorAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteVendor != null) {
                setMasterVendor(deleteVendor);

            } else {
                deleteVendor.setNomorMaster(itemId);
                deleteVendor.setFlag(itemFlag);
                setMasterVendor(deleteVendor);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteVendor.setNomorMaster(itemId);
            deleteVendor.setFlag(itemFlag);
            setMasterVendor(deleteVendor);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[VendorAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[VendorAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MasterVendor deleteVendor = new MasterVendor();

        if (itemFlag != null ) {
            try {
                deleteVendor = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[VendorAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[VendorAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteVendor != null) {
                setMasterVendor(deleteVendor);

            } else {
                deleteVendor.setNomorMaster(itemId);
                deleteVendor.setFlag(itemFlag);
                setMasterVendor(deleteVendor);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteVendor.setNomorMaster(itemId);
            deleteVendor.setFlag(itemFlag);
            setMasterVendor(deleteVendor);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[VendorAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[VendorAction.saveEdit] start process >>>");
        try {
            MasterVendor editVendor = getMasterVendor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editVendor.setLastUpdateWho(userLogin);
            editVendor.setLastUpdate(updateTime);
            editVendor.setAction("U");
            editVendor.setFlag("Y");

            masterVendorBoProxy.saveEdit(editVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[VendorAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[VendorAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[VendorAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[VendorAction.saveDelete] start process >>>");
        try {
            MasterVendor deleteVendor = getMasterVendor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteVendor.setLastUpdate(updateTime);
            deleteVendor.setLastUpdateWho(userLogin);
            deleteVendor.setAction("U");
            deleteVendor.setFlag("N");

            masterVendorBoProxy.saveDelete(deleteVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[VendorAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[VendorAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[VendorAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[VendorAction.saveAdd] start process >>>");

        try {
            MasterVendor vendor = getMasterVendor();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            vendor.setCreatedWho(userLogin);
            vendor.setLastUpdate(updateTime);
            vendor.setCreatedDate(updateTime);
            vendor.setLastUpdateWho(userLogin);
            vendor.setAction("C");
            vendor.setFlag("Y");

            masterVendorBoProxy.saveAdd(vendor);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[VendorAction.search] start process >>>");
        MasterVendor searchVendor = getMasterVendor();
        List<MasterVendor> listOfsearchVendor = new ArrayList();
        try {
            listOfsearchVendor = masterVendorBoProxy.getByCriteria(searchVendor);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterVendorBoProxy.saveErrorMessage(e.getMessage(), "VendorBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[VendorAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[VendorAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchVendor);

        logger.info("[VendorAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[VendorAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[VendorAction.initForm] end process >>>");
        return INPUT;
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
