package com.neurix.hris.master.jenisPegawai.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.jenisPegawai.bo.JenisPegawaiBo;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;
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

public class JenisPegawaiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(JenisPegawaiAction.class);
    private JenisPegawaiBo jenisPegawaiBoProxy;
    private JenisPegawai jenisPegawai;
    private List<JenisPegawai> listOfComboJenisPegawai = new ArrayList<JenisPegawai>();

    public List<JenisPegawai> getListOfComboJenisPegawai() {
        return listOfComboJenisPegawai;
    }

    public void setListOfComboJenisPegawai(List<JenisPegawai> listOfComboJenisPegawai) {
        this.listOfComboJenisPegawai = listOfComboJenisPegawai;
    }


    public JenisPegawaiBo getJenisPegawaiBoProxy() {
        return jenisPegawaiBoProxy;
    }

    public void setJenisPegawaiBoProxy(JenisPegawaiBo jenisPegawaiBoProxy) {
        this.jenisPegawaiBoProxy = jenisPegawaiBoProxy;
    }

    public JenisPegawai getJenisPegawai() {
        return jenisPegawai;
    }

    public void setJenisPegawai(JenisPegawai jenisPegawai) {
        this.jenisPegawai = jenisPegawai;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisPegawaiAction.logger = logger;
    }


    public String initComboJenisPegawai() {
        logger.info("[JenisPegawaiAction.initComboJenisPegawai] start process >>>");

        JenisPegawai search = new JenisPegawai();
        List<JenisPegawai> jenisPegawaiList = new ArrayList();
        search.setFlag("Y");
        try {
            jenisPegawaiList = jenisPegawaiBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JenisPegawaiAction.initComboJenisPegawai] Error when saving error,", e1);
            }
            logger.error("[JenisPegawaiAction.initComboJenisPegawai] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboJenisPegawai.addAll(jenisPegawaiList);
        logger.info("[JenisPegawaiAction.initComboJenisPegawai] end process <<<");
        return SUCCESS;
    }

    public JenisPegawai init(String kode, String flag){
        logger.info("[JenisPegawaiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JenisPegawai> listOfResult = (List<JenisPegawai>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (JenisPegawai jenisPegawai: listOfResult) {
                    if(kode.equalsIgnoreCase(jenisPegawai.getJenisPegawaiId()) && flag.equalsIgnoreCase(jenisPegawai.getFlag())){
                        setJenisPegawai(jenisPegawai);
                        break;
                    }
                }
            } else {
                setJenisPegawai(new JenisPegawai());
            }

            logger.info("[JenisPegawaiAction.init] end process >>>");
        }
        return getJenisPegawai();
    }

    @Override
    public String add() {
        logger.info("[JenisPegawaiAction.add] start process >>>");
        JenisPegawai addJenisPegawai = new JenisPegawai();
        setJenisPegawai(addJenisPegawai);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");

        logger.info("[JenisPegawaiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JenisPegawaiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        JenisPegawai editJenisPegawai = new JenisPegawai();

        if(itemFlag != null){
            try {
                editJenisPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.getJenisPegawaiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JenisPegawaiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[JenisPegawaiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editJenisPegawai != null) {
                setJenisPegawai(editJenisPegawai);
            } else {
                editJenisPegawai.setFlag(itemFlag);
                editJenisPegawai.setJenisPegawaiId(itemId);
                setJenisPegawai(editJenisPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisPegawai.setJenisPegawaiId(itemId);
            editJenisPegawai.setFlag(getFlag());
            setJenisPegawai(editJenisPegawai);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[JenisPegawaiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JenisPegawaiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisPegawai deleteJenisPegawai = new JenisPegawai();

        if (itemFlag != null ) {

            try {
                deleteJenisPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[JenisPegawaiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[JenisPegawaiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteJenisPegawai != null) {
                setJenisPegawai(deleteJenisPegawai);

            } else {
                deleteJenisPegawai.setJenisPegawaiId(itemId);
                deleteJenisPegawai.setFlag(itemFlag);
                setJenisPegawai(deleteJenisPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteJenisPegawai.setJenisPegawaiId(itemId);
            deleteJenisPegawai.setFlag(itemFlag);
            setJenisPegawai(deleteJenisPegawai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }


        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[JenisPegawaiAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisPegawai deleteJenisPegawai = new JenisPegawai();

        if (itemFlag != null ) {
            try {
                deleteJenisPegawai = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[JenisPegawaiAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[JenisPegawaiAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteJenisPegawai != null) {
                setJenisPegawai(deleteJenisPegawai);

            } else {
                deleteJenisPegawai.setJenisPegawaiId(itemId);
                deleteJenisPegawai.setFlag(itemFlag);
                setJenisPegawai(deleteJenisPegawai);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteJenisPegawai.setJenisPegawaiId(itemId);
            deleteJenisPegawai.setFlag(itemFlag);
            setJenisPegawai(deleteJenisPegawai);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[JenisPegawaiAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[JenisPegawaiAction.saveEdit] start process >>>");
        try {
            JenisPegawai editJenisPegawai = getJenisPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJenisPegawai.setLastUpdateWho(userLogin);
            editJenisPegawai.setLastUpdate(updateTime);
            editJenisPegawai.setAction("U");
            editJenisPegawai.setFlag("Y");

            jenisPegawaiBoProxy.saveEdit(editJenisPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[JenisPegawaiAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[JenisPegawaiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[JenisPegawaiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[JenisPegawaiAction.saveDelete] start process >>>");
        try {
            JenisPegawai deleteJenisPegawai = getJenisPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJenisPegawai.setLastUpdate(updateTime);
            deleteJenisPegawai.setLastUpdateWho(userLogin);
            deleteJenisPegawai.setAction("U");
            deleteJenisPegawai.setFlag("N");

            jenisPegawaiBoProxy.saveDelete(deleteJenisPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[JenisPegawaiAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[JenisPegawaiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[JenisPegawaiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[JenisPegawaiAction.saveAdd] start process >>>");

        try {
            JenisPegawai jenisPegawai = getJenisPegawai();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jenisPegawai.setCreatedWho(userLogin);
            jenisPegawai.setLastUpdate(updateTime);
            jenisPegawai.setCreatedDate(updateTime);
            jenisPegawai.setLastUpdateWho(userLogin);
            jenisPegawai.setAction("C");
            jenisPegawai.setFlag("Y");

            jenisPegawaiBoProxy.saveAdd(jenisPegawai);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[JenisPegawaiAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[JenisPegawaiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
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
        logger.info("[JenisPegawaiAction.search] start process >>>");
        JenisPegawai searchJenisPegawai = getJenisPegawai();
        List<JenisPegawai> listOfsearchJenisPegawai = new ArrayList();
        try {
            listOfsearchJenisPegawai = jenisPegawaiBoProxy.getByCriteria(searchJenisPegawai);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jenisPegawaiBoProxy.saveErrorMessage(e.getMessage(), "JenisPegawaiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JenisPegawaiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JenisPegawaiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchJenisPegawai);

        logger.info("[JenisPegawaiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[JenisPegawaiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[JenisPegawaiAction.initForm] end process >>>");
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
