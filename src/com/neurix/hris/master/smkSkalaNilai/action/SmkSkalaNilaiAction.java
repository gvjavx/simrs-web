package com.neurix.hris.master.smkSkalaNilai.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkSkalaNilai.bo.SmkSkalaNilaiBo;
import com.neurix.hris.master.smkSkalaNilai.model.SmkSkalaNilai;
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

public class SmkSkalaNilaiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkSkalaNilaiAction.class);
    private SmkSkalaNilaiBo smkSkalaNilaiBoProxy;
    private SmkSkalaNilai smkSkalaNilai;

    private List<SmkSkalaNilai> listComboSmkSkalaNilai = new ArrayList<SmkSkalaNilai>();

    public List<SmkSkalaNilai> getListComboSmkSkalaNilai() {
        return listComboSmkSkalaNilai;
    }

    public void setListComboSmkSkalaNilai(List<SmkSkalaNilai> listComboSmkSkalaNilai) {
        this.listComboSmkSkalaNilai = listComboSmkSkalaNilai;
    }

    public SmkSkalaNilaiBo getSmkSkalaNilaiBoProxy() {
        return smkSkalaNilaiBoProxy;
    }

    public void setSmkSkalaNilaiBoProxy(SmkSkalaNilaiBo smkSkalaNilaiBoProxy) {
        this.smkSkalaNilaiBoProxy = smkSkalaNilaiBoProxy;
    }

    public SmkSkalaNilai getSmkSkalaNilai() {
        return smkSkalaNilai;
    }

    public void setSmkSkalaNilai(SmkSkalaNilai smkSkalaNilai) {
        this.smkSkalaNilai = smkSkalaNilai;
    }

    private List<SmkSkalaNilai> initComboSmkSkalaNilai;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaNilaiAction.logger = logger;
    }


    public List<SmkSkalaNilai> getInitComboSmkSkalaNilai() {
        return initComboSmkSkalaNilai;
    }

    public void setInitComboSmkSkalaNilai(List<SmkSkalaNilai> initComboSmkSkalaNilai) {
        this.initComboSmkSkalaNilai = initComboSmkSkalaNilai;
    }

    public SmkSkalaNilai init(String kode, String flag){
        logger.info("[SmkSkalaNilaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkSkalaNilai> listOfResult = (List<SmkSkalaNilai>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkSkalaNilai smkSkalaNilai: listOfResult) {
                    if(kode.equalsIgnoreCase(smkSkalaNilai.getSkalaNilaiId()) && flag.equalsIgnoreCase(smkSkalaNilai.getFlag())){
                        setSmkSkalaNilai(smkSkalaNilai);
                        break;
                    }
                }
            } else {
                setSmkSkalaNilai(new SmkSkalaNilai());
            }

            logger.info("[SmkSkalaNilaiAction.init] end process >>>");
        }
        return getSmkSkalaNilai();
    }

    @Override
    public String add() {
        logger.info("[SmkSkalaNilaiAction.add] start process >>>");
        SmkSkalaNilai addSmkSkalaNilai = new SmkSkalaNilai();
        setSmkSkalaNilai(addSmkSkalaNilai);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkSkalaNilaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkSkalaNilaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkSkalaNilai editSmkSkalaNilai = new SmkSkalaNilai();

        if(itemFlag != null){
            try {
                editSmkSkalaNilai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.getSmkSkalaNilaiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkSkalaNilaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkSkalaNilaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkSkalaNilai != null) {
                setSmkSkalaNilai(editSmkSkalaNilai);
            } else {
                editSmkSkalaNilai.setFlag(itemFlag);
                editSmkSkalaNilai.setSkalaNilaiId(itemId);
                setSmkSkalaNilai(editSmkSkalaNilai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkSkalaNilai.setSkalaNilaiId(itemId);
            editSmkSkalaNilai.setFlag(getFlag());
            setSmkSkalaNilai(editSmkSkalaNilai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkSkalaNilaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkSkalaNilaiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkSkalaNilai deleteSmkSkalaNilai = new SmkSkalaNilai();

        if (itemFlag != null ) {

            try {
                deleteSmkSkalaNilai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkSkalaNilaiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkSkalaNilaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkSkalaNilai != null) {
                setSmkSkalaNilai(deleteSmkSkalaNilai);

            } else {
                deleteSmkSkalaNilai.setSkalaNilaiId(itemId);
                deleteSmkSkalaNilai.setFlag(itemFlag);
                setSmkSkalaNilai(deleteSmkSkalaNilai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkSkalaNilai.setSkalaNilaiId(itemId);
            deleteSmkSkalaNilai.setFlag(itemFlag);
            setSmkSkalaNilai(deleteSmkSkalaNilai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkSkalaNilaiAction.delete] end process <<<");

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
        logger.info("[SmkSkalaNilaiAction.saveEdit] start process >>>");
        try {

            SmkSkalaNilai editSmkSkalaNilai = getSmkSkalaNilai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkSkalaNilai.setLastUpdateWho(userLogin);
            editSmkSkalaNilai.setLastUpdate(updateTime);
            editSmkSkalaNilai.setAction("U");
            editSmkSkalaNilai.setFlag("Y");

            smkSkalaNilaiBoProxy.saveEdit(editSmkSkalaNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkSkalaNilaiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkSkalaNilaiAction.saveDelete] start process >>>");
        try {

            SmkSkalaNilai deleteSmkSkalaNilai = getSmkSkalaNilai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkSkalaNilai.setLastUpdate(updateTime);
            deleteSmkSkalaNilai.setLastUpdateWho(userLogin);
            deleteSmkSkalaNilai.setAction("U");
            deleteSmkSkalaNilai.setFlag("N");

            smkSkalaNilaiBoProxy.saveDelete(deleteSmkSkalaNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkSkalaNilaiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkSkalaNilaiAction.saveAdd] start process >>>");

        try {
            SmkSkalaNilai smkSkalaNilai = getSmkSkalaNilai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkSkalaNilai.setCreatedWho(userLogin);
            smkSkalaNilai.setLastUpdate(updateTime);
            smkSkalaNilai.setCreatedDate(updateTime);
            smkSkalaNilai.setLastUpdateWho(userLogin);
            smkSkalaNilai.setAction("C");
            smkSkalaNilai.setFlag("Y");

            smkSkalaNilaiBoProxy.saveAdd(smkSkalaNilai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkSkalaNilaiAction.search] start process >>>");

        SmkSkalaNilai searchSmkSkalaNilai = getSmkSkalaNilai();
        List<SmkSkalaNilai> listOfsearchSmkSkalaNilai = new ArrayList();

        try {
            listOfsearchSmkSkalaNilai = smkSkalaNilaiBoProxy.getByCriteria(searchSmkSkalaNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkSkalaNilai);

        logger.info("[SmkSkalaNilaiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkSkalaNilai() {
        logger.info("[SmkSkalaNilaiAction.search] start process >>>");

        SmkSkalaNilai searchSmkSkalaNilai = new SmkSkalaNilai();
        searchSmkSkalaNilai.setFlag("Y");
        List<SmkSkalaNilai> listOfsearchSmkSkalaNilai = new ArrayList();

        try {
            listOfsearchSmkSkalaNilai = smkSkalaNilaiBoProxy.getByCriteria(searchSmkSkalaNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkSkalaNilai.addAll(listOfsearchSmkSkalaNilai);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkSkalaNilaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkSkalaNilaiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkSkalaNilai() {
        logger.info("[SmkSkalaNilaiAction.search] start process >>>");

        SmkSkalaNilai searchSmkSkalaNilai = new SmkSkalaNilai();
        searchSmkSkalaNilai.setFlag("Y");
        List<SmkSkalaNilai> listOfsearchSmkSkalaNilai = new ArrayList();

        try {
            listOfsearchSmkSkalaNilai = smkSkalaNilaiBoProxy.getByCriteria(searchSmkSkalaNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkSkalaNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkSkalaNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkSkalaNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkSkalaNilai");
        session.setAttribute("listOfResultSmkSkalaNilai", listOfsearchSmkSkalaNilai);

        logger.info("[SmkSkalaNilaiAction.search] end process <<<");

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
