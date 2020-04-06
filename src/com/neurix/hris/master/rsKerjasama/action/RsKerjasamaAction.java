package com.neurix.hris.master.rsKerjasama.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.rsKerjasama.bo.RsKerjasamaBo;
import com.neurix.hris.master.rsKerjasama.model.RsKerjasama;
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

public class RsKerjasamaAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(RsKerjasamaAction.class);
    private RsKerjasamaBo cutiBoProxy;
    private RsKerjasama cuti;

    public RsKerjasamaBo getRsKerjasamaBoProxy() {
        return cutiBoProxy;
    }

    public void setRsKerjasamaBoProxy(RsKerjasamaBo cutiBoProxy) {
        this.cutiBoProxy = cutiBoProxy;
    }

    public RsKerjasama getRsKerjasama() {
        return cuti;
    }

    public void setRsKerjasama(RsKerjasama cuti) {
        this.cuti = cuti;
    }

    private List<RsKerjasama> initComboRsKerjasama;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RsKerjasamaAction.logger = logger;
    }


    public List<RsKerjasama> getInitComboRsKerjasama() {
        return initComboRsKerjasama;
    }

    public void setInitComboRsKerjasama(List<RsKerjasama> initComboRsKerjasama) {
        this.initComboRsKerjasama = initComboRsKerjasama;
    }

    public RsKerjasama init(String kode, String flag){
        logger.info("[RsKerjasamaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RsKerjasama> listOfResult = (List<RsKerjasama>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (RsKerjasama cuti: listOfResult) {
                    if(kode.equalsIgnoreCase(cuti.getRsId()) && flag.equalsIgnoreCase(cuti.getFlag())){
                        setRsKerjasama(cuti);
                        break;
                    }
                }
            } else {
                setRsKerjasama(new RsKerjasama());
            }

            logger.info("[RsKerjasamaAction.init] end process >>>");
        }
        return getRsKerjasama();
    }

    @Override
    public String add() {
        logger.info("[RsKerjasamaAction.add] start process >>>");
        RsKerjasama addRsKerjasama = new RsKerjasama();
        setRsKerjasama(addRsKerjasama);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RsKerjasamaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[RsKerjasamaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RsKerjasama editRsKerjasama = new RsKerjasama();

        if(itemFlag != null){
            try {
                editRsKerjasama = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.getRsKerjasamaByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[RsKerjasamaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[RsKerjasamaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRsKerjasama != null) {
                setRsKerjasama(editRsKerjasama);
            } else {
                editRsKerjasama.setFlag(itemFlag);
                editRsKerjasama.setRsId(itemId);
                setRsKerjasama(editRsKerjasama);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRsKerjasama.setRsId(itemId);
            editRsKerjasama.setFlag(getFlag());
            setRsKerjasama(editRsKerjasama);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[RsKerjasamaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[RsKerjasamaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        RsKerjasama deleteRsKerjasama = new RsKerjasama();

        if (itemFlag != null ) {

            try {
                deleteRsKerjasama = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[RsKerjasamaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[RsKerjasamaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteRsKerjasama != null) {
                setRsKerjasama(deleteRsKerjasama);

            } else {
                deleteRsKerjasama.setRsId(itemId);
                deleteRsKerjasama.setFlag(itemFlag);
                setRsKerjasama(deleteRsKerjasama);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteRsKerjasama.setRsId(itemId);
            deleteRsKerjasama.setFlag(itemFlag);
            setRsKerjasama(deleteRsKerjasama);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[RsKerjasamaAction.delete] end process <<<");

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
        logger.info("[RsKerjasamaAction.saveEdit] start process >>>");
        try {

            RsKerjasama editRsKerjasama = getRsKerjasama();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editRsKerjasama.setLastUpdateWho(userLogin);
            editRsKerjasama.setLastUpdate(updateTime);
            editRsKerjasama.setAction("U");
            editRsKerjasama.setFlag("Y");

            cutiBoProxy.saveEdit(editRsKerjasama);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[RsKerjasamaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKerjasamaAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RsKerjasamaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[RsKerjasamaAction.saveDelete] start process >>>");
        try {

            RsKerjasama deleteRsKerjasama = getRsKerjasama();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRsKerjasama.setLastUpdate(updateTime);
            deleteRsKerjasama.setLastUpdateWho(userLogin);
            deleteRsKerjasama.setAction("U");
            deleteRsKerjasama.setFlag("N");

            cutiBoProxy.saveDelete(deleteRsKerjasama);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[RsKerjasamaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKerjasamaAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RsKerjasamaAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[RsKerjasamaAction.saveAdd] start process >>>");

        try {
            RsKerjasama cuti = getRsKerjasama();
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
        logger.info("[RsKerjasamaAction.search] start process >>>");

        RsKerjasama searchRsKerjasama = getRsKerjasama();
        List<RsKerjasama> listOfsearchRsKerjasama = new ArrayList();

        try {
            listOfsearchRsKerjasama = cutiBoProxy.getByCriteria(searchRsKerjasama);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RsKerjasamaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKerjasamaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchRsKerjasama);

        logger.info("[RsKerjasamaAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[RsKerjasamaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[RsKerjasamaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initRsKerjasama() {
        logger.info("[RsKerjasamaAction.search] start process >>>");

        RsKerjasama searchRsKerjasama = new RsKerjasama();
        searchRsKerjasama.setFlag("Y");
        List<RsKerjasama> listOfsearchRsKerjasama = new ArrayList();

        try {
            listOfsearchRsKerjasama = cutiBoProxy.getByCriteria(searchRsKerjasama);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiBoProxy.saveErrorMessage(e.getMessage(), "RsKerjasamaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RsKerjasamaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RsKerjasamaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultRsKerjasama");
        session.setAttribute("listOfResultRsKerjasama", listOfsearchRsKerjasama);

        logger.info("[RsKerjasamaAction.search] end process <<<");

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
