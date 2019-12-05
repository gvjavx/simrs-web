package com.neurix.hris.master.kelompokPosition.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.kelompokPosition.bo.KelompokPositionBo;
import com.neurix.hris.master.kelompokPosition.model.KelompokPosition;
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

public class KelompokPositionAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(KelompokPositionAction.class);
    private KelompokPositionBo kelompokPositionBoProxy;
    private KelompokPosition kelompokPosition;

    private List<KelompokPosition> comboListOfKelompokPosition = new ArrayList<KelompokPosition>();
    private List<KelompokPosition> listOfComboKelompokPosition = new ArrayList<>() ;

    public List<KelompokPosition> getListOfComboKelompokPosition() {
        return listOfComboKelompokPosition;
    }

    public void setListOfComboKelompokPosition(List<KelompokPosition> listOfComboKelompokPosition) {
        this.listOfComboKelompokPosition = listOfComboKelompokPosition;
    }

    public List<KelompokPosition> getComboListOfKelompokPosition() {
        return comboListOfKelompokPosition;
    }

    public void setComboListOfKelompokPosition(List<KelompokPosition> comboListOfKelompokPosition) {
        this.comboListOfKelompokPosition = comboListOfKelompokPosition;
    }

    public KelompokPositionBo getKelompokPositionBoProxy() {
        return kelompokPositionBoProxy;
    }

    public void setKelompokPositionBoProxy(KelompokPositionBo kelompokPositionBoProxy) {
        this.kelompokPositionBoProxy = kelompokPositionBoProxy;
    }

    public KelompokPosition getKelompokPosition() {
        return kelompokPosition;
    }

    public void setKelompokPosition(KelompokPosition kelompokPosition) {
        this.kelompokPosition = kelompokPosition;
    }

    private List<KelompokPosition> initComboKelompokPosition;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KelompokPositionAction.logger = logger;
    }


    public List<KelompokPosition> getInitComboKelompokPosition() {
        return initComboKelompokPosition;
    }

    public void setInitComboKelompokPosition(List<KelompokPosition> initComboKelompokPosition) {
        this.initComboKelompokPosition = initComboKelompokPosition;
    }

    public KelompokPosition init(String kode, String flag){
        logger.info("[KelompokPositionAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KelompokPosition> listOfResult = (List<KelompokPosition>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (KelompokPosition kelompokPosition: listOfResult) {
                    if(kode.equalsIgnoreCase(kelompokPosition.getKelompokId()) && flag.equalsIgnoreCase(kelompokPosition.getFlag())){
                        setKelompokPosition(kelompokPosition);
                        break;
                    }
                }
            } else {
                setKelompokPosition(new KelompokPosition());
            }

            logger.info("[KelompokPositionAction.init] end process >>>");
        }
        return getKelompokPosition();
    }

    @Override
    public String add() {
        logger.info("[KelompokPositionAction.add] start process >>>");
        KelompokPosition addKelompokPosition = new KelompokPosition();
        setKelompokPosition(addKelompokPosition);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KelompokPositionAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KelompokPositionAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        KelompokPosition editKelompokPosition = new KelompokPosition();

        if(itemFlag != null){
            try {
                editKelompokPosition = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.getKelompokPositionByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[KelompokPositionAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[KelompokPositionAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editKelompokPosition != null) {
                setKelompokPosition(editKelompokPosition);
            } else {
                editKelompokPosition.setFlag(itemFlag);
                editKelompokPosition.setKelompokId(itemId);
                setKelompokPosition(editKelompokPosition);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editKelompokPosition.setKelompokId(itemId);
            editKelompokPosition.setFlag(getFlag());
            setKelompokPosition(editKelompokPosition);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KelompokPositionAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KelompokPositionAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KelompokPosition deleteKelompokPosition = new KelompokPosition();

        if (itemFlag != null ) {

            try {
                deleteKelompokPosition = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[KelompokPositionAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KelompokPositionAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteKelompokPosition != null) {
                setKelompokPosition(deleteKelompokPosition);

            } else {
                deleteKelompokPosition.setKelompokId(itemId);
                deleteKelompokPosition.setFlag(itemFlag);
                setKelompokPosition(deleteKelompokPosition);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteKelompokPosition.setKelompokId(itemId);
            deleteKelompokPosition.setFlag(itemFlag);
            setKelompokPosition(deleteKelompokPosition);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[KelompokPositionAction.delete] end process <<<");

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
        logger.info("[KelompokPositionAction.saveEdit] start process >>>");
        try {

            KelompokPosition editKelompokPosition = getKelompokPosition();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKelompokPosition.setLastUpdateWho(userLogin);
            editKelompokPosition.setLastUpdate(updateTime);
            editKelompokPosition.setAction("U");
            editKelompokPosition.setFlag("Y");

            kelompokPositionBoProxy.saveEdit(editKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KelompokPositionAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KelompokPositionAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[KelompokPositionAction.saveDelete] start process >>>");
        try {

            KelompokPosition deleteKelompokPosition = getKelompokPosition();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKelompokPosition.setLastUpdate(updateTime);
            deleteKelompokPosition.setLastUpdateWho(userLogin);
            deleteKelompokPosition.setAction("U");
            deleteKelompokPosition.setFlag("N");

            kelompokPositionBoProxy.saveDelete(deleteKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KelompokPositionAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KelompokPositionAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[KelompokPositionAction.saveAdd] start process >>>");

        try {
            KelompokPosition kelompokPosition = getKelompokPosition();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            kelompokPosition.setCreatedWho(userLogin);
            kelompokPosition.setLastUpdate(updateTime);
            kelompokPosition.setCreatedDate(updateTime);
            kelompokPosition.setLastUpdateWho(userLogin);
            kelompokPosition.setAction("C");
            kelompokPosition.setFlag("Y");

            kelompokPositionBoProxy.saveAdd(kelompokPosition);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[KelompokPositionAction.search] start process >>>");

        KelompokPosition searchKelompokPosition = getKelompokPosition();
        List<KelompokPosition> listOfsearchKelompokPosition = new ArrayList();

        try {
            listOfsearchKelompokPosition = kelompokPositionBoProxy.getByCriteria(searchKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KelompokPositionAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchKelompokPosition);

        logger.info("[KelompokPositionAction.search] end process <<<");

        return SUCCESS;
    }



    public String searchKelompok() {
        logger.info("[KelompokPositionAction.search] start process >>>");

        KelompokPosition searchKelompokPosition = new KelompokPosition();

        searchKelompokPosition.setFlag("Y");
        List<KelompokPosition> listOfsearchKelompokPosition = new ArrayList();

        try {
            listOfsearchKelompokPosition = kelompokPositionBoProxy.getByCriteria(searchKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KelompokPositionAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        comboListOfKelompokPosition.addAll(listOfsearchKelompokPosition);

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KelompokPositionAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[KelompokPositionAction.initForm] end process >>>");
        return INPUT;
    }

    public String initKelompokPosition() {
        logger.info("[KelompokPositionAction.search] start process >>>");

        KelompokPosition searchKelompokPosition = new KelompokPosition();
        searchKelompokPosition.setFlag("Y");
        List<KelompokPosition> listOfsearchKelompokPosition = new ArrayList();

        try {
            listOfsearchKelompokPosition = kelompokPositionBoProxy.getByCriteria(searchKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "KelompokPositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KelompokPositionAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKelompokPosition");
        session.setAttribute("listOfResultKelompokPosition", listOfsearchKelompokPosition);

        logger.info("[KelompokPositionAction.search] end process <<<");

        return "";
    }
    public String initComboKelompokPosition() {
        logger.info("[KelompokPositionAction.initComboKelompokPosition] start process >>>");

        KelompokPosition searchKelompokPosition = new KelompokPosition();
        List<KelompokPosition> listOfSearchKelompokPosition = new ArrayList();
        searchKelompokPosition.setFlag("Y");
        try {
            listOfSearchKelompokPosition = kelompokPositionBoProxy.getByCriteria(searchKelompokPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kelompokPositionBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KelompokPositionAction.initComboKelompokPosition] Error when saving error,", e1);
            }
            logger.error("[KelompokPositionAction.initComboKelompokPosition] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboKelompokPosition.addAll(listOfSearchKelompokPosition);
        logger.info("[KelompokPositionAction.initComboKelompokPosition] end process <<<");

        return SUCCESS;
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
