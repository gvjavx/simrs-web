package com.neurix.hris.master.smkSkalaPointPrestasi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkSkalaPointPrestasi.bo.SmkSkalaPointPrestasiBo;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.SmkSkalaPointPrestasi;
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

public class SmkSkalaPointPrestasiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkSkalaPointPrestasiAction.class);
    private SmkSkalaPointPrestasiBo smkSkalaPointPrestasiBoProxy;
    private SmkSkalaPointPrestasi smkSkalaPointPrestasi;

    private List<SmkSkalaPointPrestasi> listComboSmkPointPrestasi = new ArrayList<SmkSkalaPointPrestasi>();

    public List<SmkSkalaPointPrestasi> getListComboSmkPointPrestasi() {
        return listComboSmkPointPrestasi;
    }

    public void setListComboSmkPointPrestasi(List<SmkSkalaPointPrestasi> listComboSmkPointPrestasi) {
        this.listComboSmkPointPrestasi = listComboSmkPointPrestasi;
    }

    public SmkSkalaPointPrestasiBo getSmkSkalaPointPrestasiBoProxy() {
        return smkSkalaPointPrestasiBoProxy;
    }

    public void setSmkSkalaPointPrestasiBoProxy(SmkSkalaPointPrestasiBo smkSkalaPointPrestasiBoProxy) {
        this.smkSkalaPointPrestasiBoProxy = smkSkalaPointPrestasiBoProxy;
    }

    public SmkSkalaPointPrestasi getSmkSkalaPointPrestasi() {
        return smkSkalaPointPrestasi;
    }

    public void setSmkSkalaPointPrestasi(SmkSkalaPointPrestasi smkSkalaPointPrestasi) {
        this.smkSkalaPointPrestasi = smkSkalaPointPrestasi;
    }

    private List<SmkSkalaPointPrestasi> initComboSmkPointPrestasi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkSkalaPointPrestasiAction.logger = logger;
    }


    public List<SmkSkalaPointPrestasi> getInitComboSmkPointPrestasi() {
        return initComboSmkPointPrestasi;
    }

    public void setInitComboSmkPointPrestasi(List<SmkSkalaPointPrestasi> initComboSmkPointPrestasi) {
        this.initComboSmkPointPrestasi = initComboSmkPointPrestasi;
    }

    public SmkSkalaPointPrestasi init(String kode, String flag){
        logger.info("[SmkPointPrestasiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkSkalaPointPrestasi> listOfResult = (List<SmkSkalaPointPrestasi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkSkalaPointPrestasi smkPointPrestasi: listOfResult) {
                    if(kode.equalsIgnoreCase(smkPointPrestasi.getPointPrestasiId()) && flag.equalsIgnoreCase(smkPointPrestasi.getFlag())){
                        setSmkSkalaPointPrestasi(smkPointPrestasi);
                        break;
                    }
                }
            } else {
                setSmkSkalaPointPrestasi(new SmkSkalaPointPrestasi());
            }

            logger.info("[SmkPointPrestasiAction.init] end process >>>");
        }
        return getSmkSkalaPointPrestasi();
    }

    @Override
    public String add() {
        logger.info("[SmkPointPrestasiAction.add] start process >>>");
        SmkSkalaPointPrestasi addSmkPointPrestasi = new SmkSkalaPointPrestasi();
        setSmkSkalaPointPrestasi(addSmkPointPrestasi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[SmkPointPrestasiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SmkPointPrestasiAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkSkalaPointPrestasi editSmkPointPrestasi = new SmkSkalaPointPrestasi();

        if(itemFlag != null){
            try {
                editSmkPointPrestasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.getSmkSkalaPointPrestasiByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkPointPrestasiAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkPointPrestasiAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkPointPrestasi != null) {
                setSmkSkalaPointPrestasi(editSmkPointPrestasi);
            } else {
                editSmkPointPrestasi.setFlag(itemFlag);
                editSmkPointPrestasi.setPointPrestasiId(itemId);
                setSmkSkalaPointPrestasi(editSmkPointPrestasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkPointPrestasi.setPointPrestasiId(itemId);
            editSmkPointPrestasi.setFlag(getFlag());
            setSmkSkalaPointPrestasi(editSmkPointPrestasi);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[SmkPointPrestasiAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SmkPointPrestasiAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SmkSkalaPointPrestasi deleteSmkPointPrestasi = new SmkSkalaPointPrestasi();

        if (itemFlag != null ) {

            try {
                deleteSmkPointPrestasi = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkPointPrestasiAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkPointPrestasiAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmkPointPrestasi != null) {
                setSmkSkalaPointPrestasi(deleteSmkPointPrestasi);

            } else {
                deleteSmkPointPrestasi.setPointPrestasiId(itemId);
                deleteSmkPointPrestasi.setFlag(itemFlag);
                setSmkSkalaPointPrestasi(deleteSmkPointPrestasi);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmkPointPrestasi.setPointPrestasiId(itemId);
            deleteSmkPointPrestasi.setFlag(itemFlag);
            setSmkSkalaPointPrestasi(deleteSmkPointPrestasi);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkPointPrestasiAction.delete] end process <<<");

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
        logger.info("[SmkPointPrestasiAction.saveEdit] start process >>>");
        try {

            SmkSkalaPointPrestasi editSmkPointPrestasi = getSmkSkalaPointPrestasi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkPointPrestasi.setLastUpdateWho(userLogin);
            editSmkPointPrestasi.setLastUpdate(updateTime);
            editSmkPointPrestasi.setAction("U");
            editSmkPointPrestasi.setFlag("Y");

            smkSkalaPointPrestasiBoProxy.saveEdit(editSmkPointPrestasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPointPrestasiAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPointPrestasiAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkPointPrestasiAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkPointPrestasiAction.saveDelete] start process >>>");
        try {

            SmkSkalaPointPrestasi deleteSmkPointPrestasi = getSmkSkalaPointPrestasi();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkPointPrestasi.setLastUpdate(updateTime);
            deleteSmkPointPrestasi.setLastUpdateWho(userLogin);
            deleteSmkPointPrestasi.setAction("U");
            deleteSmkPointPrestasi.setFlag("N");

            smkSkalaPointPrestasiBoProxy.saveDelete(deleteSmkPointPrestasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPointPrestasiAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPointPrestasiAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkPointPrestasiAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkPointPrestasiAction.saveAdd] start process >>>");

        try {
            SmkSkalaPointPrestasi smkPointPrestasi = getSmkSkalaPointPrestasi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkPointPrestasi.setCreatedWho(userLogin);
            smkPointPrestasi.setLastUpdate(updateTime);
            smkPointPrestasi.setCreatedDate(updateTime);
            smkPointPrestasi.setLastUpdateWho(userLogin);
            smkPointPrestasi.setAction("C");
            smkPointPrestasi.setFlag("Y");

            smkSkalaPointPrestasiBoProxy.saveAdd(smkPointPrestasi);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[SmkPointPrestasiAction.search] start process >>>");

        SmkSkalaPointPrestasi searchSmkPointPrestasi = getSmkSkalaPointPrestasi();
        List<SmkSkalaPointPrestasi> listOfsearchSmkPointPrestasi = new ArrayList();

        try {
            listOfsearchSmkPointPrestasi = smkSkalaPointPrestasiBoProxy.getByCriteria(searchSmkPointPrestasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPointPrestasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPointPrestasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkPointPrestasi);

        logger.info("[SmkPointPrestasiAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkPointPrestasi() {
        logger.info("[SmkPointPrestasiAction.search] start process >>>");

        SmkSkalaPointPrestasi searchSmkPointPrestasi = new SmkSkalaPointPrestasi();
        searchSmkPointPrestasi.setFlag("Y");
        List<SmkSkalaPointPrestasi> listOfsearchSmkPointPrestasi = new ArrayList();

        try {
            listOfsearchSmkPointPrestasi = smkSkalaPointPrestasiBoProxy.getByCriteria(searchSmkPointPrestasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPointPrestasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPointPrestasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkPointPrestasi.addAll(listOfsearchSmkPointPrestasi);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkPointPrestasiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkPointPrestasiAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkPointPrestasi() {
        logger.info("[SmkPointPrestasiAction.search] start process >>>");

        SmkSkalaPointPrestasi searchSmkPointPrestasi = new SmkSkalaPointPrestasi();
        searchSmkPointPrestasi.setFlag("Y");
        List<SmkSkalaPointPrestasi> listOfsearchSmkPointPrestasi = new ArrayList();

        try {
            listOfsearchSmkPointPrestasi = smkSkalaPointPrestasiBoProxy.getByCriteria(searchSmkPointPrestasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkSkalaPointPrestasiBoProxy.saveErrorMessage(e.getMessage(), "SmkPointPrestasiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkPointPrestasiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkPointPrestasiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkPointPrestasi");
        session.setAttribute("listOfResultSmkPointPrestasi", listOfsearchSmkPointPrestasi);

        logger.info("[SmkPointPrestasiAction.search] end process <<<");

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
