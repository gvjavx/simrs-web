package com.neurix.hris.master.smkPersenSmkNilai.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkPersenSmkNilai.bo.SmkPersenSmkNilaiBo;
import com.neurix.hris.master.smkPersenSmkNilai.model.smkPersenSmkNilai;
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

public class SmkPersenSmkNilaiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkPersenSmkNilaiAction.class);
    private SmkPersenSmkNilaiBo smkPersenSmkNilaiBoProxy;
    private smkPersenSmkNilai smkPersenSmkNilai;

    public smkPersenSmkNilai getSmkPersenSmkNilai() {
        return smkPersenSmkNilai;
    }

    public void setSmkPersenSmkNilai(smkPersenSmkNilai smkPersenSmkNilai) {
        this.smkPersenSmkNilai = smkPersenSmkNilai;
    }

    public SmkPersenSmkNilaiBo getSmkPersenSmkNilaiBoProxy() {
        return smkPersenSmkNilaiBoProxy;
    }

    public void setSmkPersenSmkNilaiBoProxy(SmkPersenSmkNilaiBo smkPersenSmkNilaiBoProxy) {
        this.smkPersenSmkNilaiBoProxy = smkPersenSmkNilaiBoProxy;
    }

    private List<smkPersenSmkNilai> listComboSmkPersenSmkNilai = new ArrayList<smkPersenSmkNilai>();

    public List<smkPersenSmkNilai> getListComboSmkPersenSmkNilai() {
        return listComboSmkPersenSmkNilai;
    }

    public void setListComboSmkPersenSmkNilai(List<smkPersenSmkNilai> listComboSmkPersenSmkNilai) {
        this.listComboSmkPersenSmkNilai = listComboSmkPersenSmkNilai;
    }

    private List<smkPersenSmkNilai> initComboSmkPersenSmkNilai;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkPersenSmkNilaiAction.logger = logger;
    }


    public List<smkPersenSmkNilai> getInitComboSmkPersenSmkNilai() {
        return initComboSmkPersenSmkNilai;
    }

    public void setInitComboSmkPersenSmkNilai(List<smkPersenSmkNilai> initComboSmkPersenSmkNilai) {
        this.initComboSmkPersenSmkNilai = initComboSmkPersenSmkNilai;
    }

    public smkPersenSmkNilai init(String kode, String flag){
        logger.info("[SmkPersenSmkNilaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<smkPersenSmkNilai> listOfResult = (List<smkPersenSmkNilai>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (smkPersenSmkNilai smkPersenSmkNilai: listOfResult) {
                    if(kode.equalsIgnoreCase(smkPersenSmkNilai.getSmkNilaiId()) && flag.equalsIgnoreCase(smkPersenSmkNilai.getFlag())){
                        setSmkPersenSmkNilai(smkPersenSmkNilai);
                        break;
                    }
                }
            } else {
                setSmkPersenSmkNilai(new smkPersenSmkNilai());
            }

            logger.info("[SmkPersenSmkNilaiAction.init] end process >>>");
        }
        return getSmkPersenSmkNilai();
    }

    @Override
    public String add() {
        logger.info("[SmkPersenSmkNilaiAction.add] start process >>>");
        smkPersenSmkNilai addSmkPersenSmkNilai = new smkPersenSmkNilai();
        setSmkPersenSmkNilai(addSmkPersenSmkNilai);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkPersenSmkNilaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkPersenSmkNilaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        smkPersenSmkNilai editSmkPersenSmkNilai = new smkPersenSmkNilai();

        if(itemFlag != null){
            try {
                editSmkPersenSmkNilai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.getSmkPersenSmkNilaiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkPersenSmkNilaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkPersenSmkNilaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkPersenSmkNilai != null) {
                setSmkPersenSmkNilai(editSmkPersenSmkNilai);
            } else {
                editSmkPersenSmkNilai.setFlag(itemFlag);
                setSmkPersenSmkNilai(editSmkPersenSmkNilai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkPersenSmkNilai.setFlag(getFlag());
            setSmkPersenSmkNilai(editSmkPersenSmkNilai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkPersenSmkNilaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkPersenSmkNilaiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        smkPersenSmkNilai deleteSmkPersenSmkNilai = new smkPersenSmkNilai();

        if (itemFlag != null ) {

            try {
                deleteSmkPersenSmkNilai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkPersenSmkNilaiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkPersenSmkNilaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkPersenSmkNilai != null) {
                setSmkPersenSmkNilai(deleteSmkPersenSmkNilai);

            } else {
                deleteSmkPersenSmkNilai.setFlag(itemFlag);
                setSmkPersenSmkNilai(deleteSmkPersenSmkNilai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkPersenSmkNilai.setFlag(itemFlag);
            setSmkPersenSmkNilai(deleteSmkPersenSmkNilai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkPersenSmkNilaiAction.delete] end process <<<");

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
        logger.info("[SmkPersenSmkNilaiAction.saveEdit] start process >>>");
        try {

            smkPersenSmkNilai editSmkPersenSmkNilai = getSmkPersenSmkNilai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkPersenSmkNilai.setLastUpdateWho(userLogin);
            editSmkPersenSmkNilai.setLastUpdate(updateTime);
            editSmkPersenSmkNilai.setAction("U");
            editSmkPersenSmkNilai.setFlag("Y");

            smkPersenSmkNilaiBoProxy.saveEdit(editSmkPersenSmkNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPersenSmkNilaiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPersenSmkNilaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkPersenSmkNilaiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkPersenSmkNilaiAction.saveDelete] start process >>>");
        try {

            smkPersenSmkNilai deleteSmkPersenSmkNilai = getSmkPersenSmkNilai();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkPersenSmkNilai.setLastUpdate(updateTime);
            deleteSmkPersenSmkNilai.setLastUpdateWho(userLogin);
            deleteSmkPersenSmkNilai.setAction("D");
            deleteSmkPersenSmkNilai.setFlag("N");

            smkPersenSmkNilaiBoProxy.saveDelete(deleteSmkPersenSmkNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPersenSmkNilaiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPersenSmkNilaiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkPersenSmkNilaiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkPersenSmkNilaiAction.saveAdd] start process >>>");

        try {
            smkPersenSmkNilai smkPersenSmkNilai = getSmkPersenSmkNilai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkPersenSmkNilai.setCreatedWho(userLogin);
            smkPersenSmkNilai.setLastUpdate(updateTime);
            smkPersenSmkNilai.setCreatedDate(updateTime);
            smkPersenSmkNilai.setLastUpdateWho(userLogin);
            smkPersenSmkNilai.setAction("C");
            smkPersenSmkNilai.setFlag("Y");

            smkPersenSmkNilaiBoProxy.saveAdd(smkPersenSmkNilai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkPersenSmkNilaiAction.search] start process >>>");

        smkPersenSmkNilai searchSmkPersenSmkNilai = getSmkPersenSmkNilai();
        List<smkPersenSmkNilai> listOfsearchSmkPersenSmkNilai = new ArrayList();

        try {
            listOfsearchSmkPersenSmkNilai = smkPersenSmkNilaiBoProxy.getByCriteria(searchSmkPersenSmkNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPersenSmkNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPersenSmkNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkPersenSmkNilai);

        logger.info("[SmkPersenSmkNilaiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkPersenSmkNilai() {
        logger.info("[SmkPersenSmkNilaiAction.search] start process >>>");

        smkPersenSmkNilai searchSmkPersenSmkNilai = new smkPersenSmkNilai();
        searchSmkPersenSmkNilai.setFlag("Y");
        List<smkPersenSmkNilai> listOfsearchSmkPersenSmkNilai = new ArrayList();

        try {
            listOfsearchSmkPersenSmkNilai = smkPersenSmkNilaiBoProxy.getByCriteria(searchSmkPersenSmkNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPersenSmkNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPersenSmkNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkPersenSmkNilai.addAll(listOfsearchSmkPersenSmkNilai);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkPersenSmkNilaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkPersenSmkNilaiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkPersenSmkNilai() {
        logger.info("[SmkPersenSmkNilaiAction.search] start process >>>");

        smkPersenSmkNilai searchSmkPersenSmkNilai = new smkPersenSmkNilai();
        searchSmkPersenSmkNilai.setFlag("Y");
        List<smkPersenSmkNilai> listOfsearchSmkPersenSmkNilai = new ArrayList();

        try {
            listOfsearchSmkPersenSmkNilai = smkPersenSmkNilaiBoProxy.getByCriteria(searchSmkPersenSmkNilai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkPersenSmkNilaiBoProxy.saveErrorMessage(e.getMessage(), "SmkPersenSmkNilaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPersenSmkNilaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPersenSmkNilaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkPersenSmkNilai");
        session.setAttribute("listOfResultSmkPersenSmkNilai", listOfsearchSmkPersenSmkNilai);

        logger.info("[SmkPersenSmkNilaiAction.search] end process <<<");

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
