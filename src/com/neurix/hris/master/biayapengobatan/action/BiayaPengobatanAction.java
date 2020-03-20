package com.neurix.hris.master.biayapengobatan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biayapengobatan.bo.BiayaPengobatanBo;
import com.neurix.hris.master.biayapengobatan.model.BiayaPengobatan;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.model.Group;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class BiayaPengobatanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(BiayaPengobatanAction.class);

    private BiayaPengobatan biayaPengobatan;
    private BiayaPengobatanBo biayaPengobatanBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiayaPengobatanAction.logger = logger;
    }

    public BiayaPengobatan getBiayaPengobatan() {
        return biayaPengobatan;
    }

    public void setBiayaPengobatan(BiayaPengobatan biayaPengobatan) {
        this.biayaPengobatan = biayaPengobatan;
    }

    public BiayaPengobatanBo getBiayaPengobatanBoProxy() {
        return biayaPengobatanBoProxy;
    }

    public void setBiayaPengobatanBoProxy(BiayaPengobatanBo biayaPengobatanBoProxy) {
        this.biayaPengobatanBoProxy = biayaPengobatanBoProxy;
    }

    public BiayaPengobatan init(String kode, String flag){
        logger.info("[GroupShiftAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BiayaPengobatan> listOfResult = (List<BiayaPengobatan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (BiayaPengobatan biayaPengobatan: listOfResult) {
                    if(kode.equalsIgnoreCase(biayaPengobatan.getBiayaPengobatanId()) && flag.equalsIgnoreCase(biayaPengobatan.getFlag())){
                        setBiayaPengobatan(biayaPengobatan);
                        break;
                    }
                }
            } else {
                setBiayaPengobatan(new BiayaPengobatan());
            }

            logger.info("[GroupShiftAction.init] end process >>>");
        }
        return getBiayaPengobatan();
    }

    @Override
    public String add() {
        logger.info("[BiayaPengobatanAction.add] start process >>>");
        BiayaPengobatan addBiayaPengobatan = new BiayaPengobatan();
        setBiayaPengobatan(addBiayaPengobatan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BiayaPengobatanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        BiayaPengobatan editBiayaPengobatan = new BiayaPengobatan();

        if(itemFlag != null){
            try {
                editBiayaPengobatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBiayaPengobatan != null) {
                setBiayaPengobatan(editBiayaPengobatan);
            } else {
                editBiayaPengobatan.setFlag(itemFlag);
                editBiayaPengobatan.setBiayaPengobatanId(itemId);
                setBiayaPengobatan(editBiayaPengobatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBiayaPengobatan.setBiayaPengobatanId(itemId);
            editBiayaPengobatan.setFlag(getFlag());
            setBiayaPengobatan(editBiayaPengobatan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BiayaPengobatanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BiayaPengobatanAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        BiayaPengobatan editBiayaPengobatan = new BiayaPengobatan();

        if(itemFlag != null){
            try {
                editBiayaPengobatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "groupBo.getAlatByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPengobatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPengobatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBiayaPengobatan != null) {
                setBiayaPengobatan(editBiayaPengobatan);
            } else {
                editBiayaPengobatan.setFlag(itemFlag);
                editBiayaPengobatan.setBiayaPengobatanId(itemId);
                setBiayaPengobatan(editBiayaPengobatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBiayaPengobatan.setBiayaPengobatanId(itemId);
            editBiayaPengobatan.setFlag(getFlag());
            setBiayaPengobatan(editBiayaPengobatan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        logger.info("[BiayaPengobatanAction.delete] end process >>>");
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[BiayaPengobatanAction.saveAdd] start process >>>");

        try {
            BiayaPengobatan biayaPengobatan = getBiayaPengobatan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            biayaPengobatan.setCreatedWho(userLogin);
            biayaPengobatan.setLastUpdate(updateTime);
            biayaPengobatan.setCreatedDate(updateTime);
            biayaPengobatan.setLastUpdateWho(userLogin);
            biayaPengobatan.setAction("C");
            biayaPengobatan.setFlag("Y");


            biayaPengobatanBoProxy.saveAdd(biayaPengobatan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "GroupShifBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveAdd] end process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[BiayaPengobatanAction.saveEdit] start process >>>");

        try {
            BiayaPengobatan biayaPengobatan = getBiayaPengobatan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            biayaPengobatan.setCreatedWho(biayaPengobatan.getCreatedWho());
            biayaPengobatan.setLastUpdate(updateTime);
            biayaPengobatan.setCreatedDate(biayaPengobatan.getCreatedDate());
            biayaPengobatan.setLastUpdateWho(userLogin);
            biayaPengobatan.setAction("U");
            biayaPengobatan.setFlag("Y");


            biayaPengobatanBoProxy.saveEdit(biayaPengobatan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveEdit] end process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        logger.info("[BiayaPengobatanAction.saveDelete] start process >>>");

        try {
            BiayaPengobatan biayaPengobatan = getBiayaPengobatan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            biayaPengobatan.setCreatedWho(biayaPengobatan.getCreatedWho());
            biayaPengobatan.setLastUpdate(updateTime);
            biayaPengobatan.setCreatedDate(biayaPengobatan.getCreatedDate());
            biayaPengobatan.setLastUpdateWho(userLogin);
            biayaPengobatan.setAction("U");
            biayaPengobatan.setFlag("N");

            biayaPengobatanBoProxy.saveEdit(biayaPengobatan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "groupShiftBo.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.saveDelete] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPengobatanAction.saveDelete] end process >>>");
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");

        BiayaPengobatan biayaPengobatan = getBiayaPengobatan();
        List<BiayaPengobatan> listOfSearchBiayaPengobatan = new ArrayList();

        try {
            listOfSearchBiayaPengobatan = biayaPengobatanBoProxy.getByCriteria(biayaPengobatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPengobatanBoProxy.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPengobatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPengobatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchBiayaPengobatan);

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return INPUT;
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
