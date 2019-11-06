package com.neurix.hris.master.positionBagian.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.positionBagian;
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

public class PositionBagianAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(PositionBagianAction.class);
    private PositionBagianBo positionBagianBoProxy;
    private positionBagian positionBagian;

    private List<positionBagian> comboListOfPositionBagian = new ArrayList<positionBagian>();

    public List<positionBagian> getComboListOfPositionBagian() {
        return comboListOfPositionBagian;
    }

    public void setComboListOfPositionBagian(List<positionBagian> comboListOfPositionBagian) {
        this.comboListOfPositionBagian = comboListOfPositionBagian;
    }

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    public positionBagian getPositionBagian() {
        return positionBagian;
    }

    public void setPositionBagian(positionBagian positionBagian) {
        this.positionBagian = positionBagian;
    }

    private List<positionBagian> initComboPositionBagian;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PositionBagianAction.logger = logger;
    }


    public List<positionBagian> getInitComboPositionBagian() {
        return initComboPositionBagian;
    }

    public void setInitComboPositionBagian(List<positionBagian> initComboPositionBagian) {
        this.initComboPositionBagian = initComboPositionBagian;
    }

    public positionBagian init(String kode, String flag){
        logger.info("[PositionBagianAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<positionBagian> listOfResult = (List<positionBagian>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (positionBagian positionBagian: listOfResult) {
                    if(kode.equalsIgnoreCase(positionBagian.getBagianId()) && flag.equalsIgnoreCase(positionBagian.getFlag())){
                        setPositionBagian(positionBagian);
                        break;
                    }
                }
            } else {
                setPositionBagian(new positionBagian());
            }

            logger.info("[PositionBagianAction.init] end process >>>");
        }
        return getPositionBagian();
    }

    @Override
    public String add() {
        logger.info("[PositionBagianAction.add] start process >>>");
        positionBagian addPositionBagian = new positionBagian();
        setPositionBagian(addPositionBagian);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PositionBagianAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PositionBagianAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        positionBagian editPositionBagian = new positionBagian();

        if(itemFlag != null){
            try {
                editPositionBagian = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.getPositionBagianByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PositionBagianAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PositionBagianAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPositionBagian != null) {
                setPositionBagian(editPositionBagian);
            } else {
                editPositionBagian.setFlag(itemFlag);
                editPositionBagian.setBagianId(itemId);
                setPositionBagian(editPositionBagian);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPositionBagian.setBagianId(itemId);
            editPositionBagian.setFlag(getFlag());
            setPositionBagian(editPositionBagian);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[PositionBagianAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PositionBagianAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        positionBagian deletePositionBagian = new positionBagian();

        if (itemFlag != null ) {

            try {
                deletePositionBagian = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PositionBagianAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PositionBagianAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePositionBagian != null) {
                setPositionBagian(deletePositionBagian);

            } else {
                deletePositionBagian.setBagianId(itemId);
                deletePositionBagian.setFlag(itemFlag);
                setPositionBagian(deletePositionBagian);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePositionBagian.setBagianId(itemId);
            deletePositionBagian.setFlag(itemFlag);
            setPositionBagian(deletePositionBagian);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PositionBagianAction.delete] end process <<<");

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
        logger.info("[PositionBagianAction.saveEdit] start process >>>");
        try {

            positionBagian editPositionBagian = getPositionBagian();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPositionBagian.setLastUpdateWho(userLogin);
            editPositionBagian.setLastUpdate(updateTime);
            editPositionBagian.setAction("U");
            editPositionBagian.setFlag("Y");

            positionBagianBoProxy.saveEdit(editPositionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PositionBagianAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PositionBagianAction.saveDelete] start process >>>");
        try {

            positionBagian deletePositionBagian = getPositionBagian();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePositionBagian.setLastUpdate(updateTime);
            deletePositionBagian.setLastUpdateWho(userLogin);
            deletePositionBagian.setAction("U");
            deletePositionBagian.setFlag("N");

            positionBagianBoProxy.saveDelete(deletePositionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PositionBagianAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PositionBagianAction.saveAdd] start process >>>");

        try {
            positionBagian positionBagian = getPositionBagian();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            positionBagian.setCreatedWho(userLogin);
            positionBagian.setLastUpdate(updateTime);
            positionBagian.setCreatedDate(updateTime);
            positionBagian.setLastUpdateWho(userLogin);
            positionBagian.setAction("C");
            positionBagian.setFlag("Y");

            positionBagianBoProxy.saveAdd(positionBagian);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[PositionBagianAction.search] start process >>>");

        positionBagian searchPositionBagian = getPositionBagian();
        List<positionBagian> listOfsearchPositionBagian = new ArrayList();

        try {
            listOfsearchPositionBagian = positionBagianBoProxy.getByCriteria(searchPositionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPositionBagian);

        logger.info("[PositionBagianAction.search] end process <<<");

        return SUCCESS;
    }

    public void searchPositionBagian() {
        logger.info("[PositionBagianAction.searchPositionBagian] start process >>>");

        positionBagian positionBagian = new positionBagian();
        positionBagian.setFlag("Y");
        List<positionBagian> listOfSearchTipePegawai = new ArrayList();

        try {
            listOfSearchTipePegawai = positionBagianBoProxy.getByCriteria(positionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.searchPositionBagian] Error when saving error,", e1);
            }
            logger.error("[PositionBagianAction.searchPositionBagian] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        comboListOfPositionBagian.addAll(listOfSearchTipePegawai);
    }

    public String searchKelompok() {
        logger.info("[PositionBagianAction.search] start process >>>");

        positionBagian searchPositionBagian = new positionBagian();

        searchPositionBagian.setFlag("Y");
        List<positionBagian> listOfsearchPositionBagian = new ArrayList();

        try {
            listOfsearchPositionBagian = positionBagianBoProxy.getByCriteria(searchPositionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        comboListOfPositionBagian.addAll(listOfsearchPositionBagian);

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PositionBagianAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[PositionBagianAction.initForm] end process >>>");
        return INPUT;
    }

    public String initPositionBagian() {
        logger.info("[PositionBagianAction.search] start process >>>");

        positionBagian searchPositionBagian = new positionBagian();
        searchPositionBagian.setFlag("Y");
        List<positionBagian> listOfsearchPositionBagian = new ArrayList();

        try {
            listOfsearchPositionBagian = positionBagianBoProxy.getByCriteria(searchPositionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBoProxy.saveErrorMessage(e.getMessage(), "PositionBagianBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultPositionBagian");
        session.setAttribute("listOfResultPositionBagian", listOfsearchPositionBagian);

        logger.info("[PositionBagianAction.search] end process <<<");

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
