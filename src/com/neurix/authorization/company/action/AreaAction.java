package com.neurix.authorization.company.action;

import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class AreaAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(AreaAction.class);

    private AreaBo areaBoProxy;
    private List<Area> areaList;
    private Area area;

    public void setAreaBoProxy(AreaBo areaBoProxy) {
        this.areaBoProxy = areaBoProxy;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    private Area init(String id, String flag) throws NumberFormatException, GeneralBOException {

        logger.info("[AreaAction.init] start process >>>");

        Area initArea = new Area();
        if (id != null && !"".equalsIgnoreCase(id)) {
            initArea = areaBoProxy.getAreaById(id, flag);
        }

        logger.info("[AreaAction.init] end process <<<");

        return initArea;
    }

    public String saveAdd(){
        logger.info("[areaAction.saveAdd] start process >>>");

        try {
            Area area = getArea();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            area.setCreatedWho(userLogin);
            area.setLastUpdate(updateTime);
            area.setCreatedDate(updateTime);
            area.setLastUpdateWho(userLogin);
            area.setAction("C");
            area.setFlag("Y");

            areaBoProxy.saveAdd(area);
            area.setSuccessMessage("Successfully Entry New Data");
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "areaBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[areaAction.saveAdd] Error when saving error,", e1);
                area.setErrorMessage("Error when saving error," + e1.getMessage());
                area.setSuccessMessage("");
                return ERROR;
            }
            logger.error("[areaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            area.setSuccessMessage("");
            return ERROR;
        }
//        catch (Exception ex){
//            String ster = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
//            ex.printStackTrace();
//            area.setErVerif("[areaAction.saveAdd] Error When Saved Variable,"+ster);
//            return ERROR;
//        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[areaAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String edit() {
        logger.info("[AreaAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Area editArea = new Area();

        if (itemFlag != null) {

            try {
                editArea = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.getAreaById");
                } catch (GeneralBOException e1) {
                    logger.error("[AreaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[AreaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editArea != null) {
                if (editArea.getFlag().equalsIgnoreCase("Y")) {
                    setAddOrEdit(true);
                    setArea(editArea);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    editArea.setAreaId(itemId);
                    editArea.setFlag(itemFlag);
                    setArea(editArea);
                    addActionError("Error, Unable to edit again with flag = N.");
                    return "failure";
                }
            } else {
                editArea.setAreaId(itemId);
                editArea.setFlag(itemFlag);
                setArea(editArea);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editArea.setAreaId(itemId);
            editArea.setFlag(itemFlag);
            setArea(editArea);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[AreaAction.edit] end process <<<");

        return INPUT;
    }

    @Override
    public String add() {
        logger.info("[AreaAction.add] start process >>>");
        Area addArea = new Area();
        setArea(addArea);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AreaAction.add] end process <<<");
        return INPUT;
    }

    @Override
    public String delete() {
        logger.info("[AreaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Area deleteArea = new Area();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag) ) {

            try {
                deleteArea = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.getAreaById");
                } catch (GeneralBOException e1) {
                    logger.error("[AreaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[AreaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteArea != null) {
                if (deleteArea.getFlag().equalsIgnoreCase("Y")) {
                    setDelete(true);
                    setArea(deleteArea);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    deleteArea.setAreaId(itemId);
                    deleteArea.setFlag(itemFlag);
                    setArea(deleteArea);
                    addActionError("Error, Unable to delete again with flag = N.");
                    return "failure";
                }
            } else {
                deleteArea.setAreaId(itemId);
                deleteArea.setFlag(itemFlag);
                setArea(deleteArea);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteArea.setAreaId(itemId);
            deleteArea.setFlag(itemFlag);
            setArea(deleteArea);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[AreaAction.delete] end process <<<");

        return INPUT;
    }

    @Override
    public String view() {

        return INPUT;
    }

    public String saveEdit(){
        logger.info("[AreaAction.saveEdit] start process >>>");
        try {

            Area editArea = getArea();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editArea.setLastUpdateWho(userLogin);
            editArea.setLastUpdate(updateTime);
            editArea.setAction("U");

            areaBoProxy.saveEdit(editArea);
            area.setSuccessMessage("Data Successfully Updated");

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[AlatAction.saveEdit] Error when saving error,", e1);
                area.setErrorMessage("Error when saving error," + e1.getMessage());
                area.setSuccessMessage("");
                return ERROR;
            }
            logger.error("[AlatAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            area.setSuccessMessage("");
            return ERROR;
        }

        logger.info("[AreaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    @Override
    public String save() {
        logger.info("[AreaAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {
                String itemId = getArea().getAreaId();
                if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                    //edit
                    try {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        Area editArea = getArea();
                        editArea.setAreaId(itemId);
                        editArea.setCreatedDate(updateTime);
                        editArea.setCreatedWho(userLogin);
                        editArea.setLastUpdate(updateTime);
                        editArea.setLastUpdateWho(userLogin);
                        editArea.setAction("U");
                        areaBoProxy.saveEdit(editArea);
                        area.setSuccessMessage("Data Successfully Updated");
                    } catch (UsernameNotFoundException e) {
                        logger.error("[AreaAction.save] Error when editing item area,", e);
                        addActionError("Error, " + e.getMessage());
                        area.setErrorMessage("Error, " + e.getMessage());
                        area.setSuccessMessage("");
                        return ERROR;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[AreaAction.save] Error when saving error,", e1);
                        }
                        logger.error("[AreaAction.save] Error when editing item area," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                        area.setSuccessMessage("");
                        return ERROR;
                    }
                }
            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    Area entryArea = getArea();

                    entryArea.setCreatedDate(createTime);
                    entryArea.setCreatedWho(userLogin);
                    entryArea.setLastUpdate(createTime);
                    entryArea.setLastUpdateWho(userLogin);
                    entryArea.setAction("C");

                    areaBoProxy.saveAdd(entryArea);
                    area.setSuccessMessage("Successfully Entry New Data");
                } catch (UsernameNotFoundException e) {
                    logger.error("[AreaAction.save] Error when adding item area,", e);
                    addActionError("Error, " + e.getMessage());
                    area.setErrorMessage("Error, " + e.getMessage());
                    area.setSuccessMessage("");
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[AreaAction.save] Error when saving error,", e1);
                    }
                    logger.error("[AreaAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    area.setSuccessMessage("");
                    return ERROR;
                }

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                Area deleteArea = getArea();

                deleteArea.setLastUpdate(updateTime);
                deleteArea.setLastUpdateWho(userLogin);
                deleteArea.setAction("D");
                areaBoProxy.saveDelete(deleteArea);
                area.setSuccessMessage("Data Successfully Deleted");

            } catch (UsernameNotFoundException e) {
                logger.error("[AreaAction.save] Error when deleting item area,", e);
                addActionError("Error, " + e.getMessage());
                area.setErrorMessage("Error, " + e.getMessage());
                area.setSuccessMessage("");
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[AreaAction.save] Error when saving error,", e1);
                }
                logger.error("[AreaAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                area.setSuccessMessage("");
                return ERROR;
            }

        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[AreaAction.save] end process <<<");

        return "success_save";
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        Area initArea = new Area();
        setArea(initArea);
        setDelete(false);
        setAdd(false);
        setAddOrEdit(false);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

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

    @Override
    public String search() {
        logger.info("[AreaAction.search] start process >>>");

        Area searchArea = getArea();
        List<Area> listOfSearchArea = new ArrayList();
        try {
            listOfSearchArea = areaBoProxy.getByCriteria(searchArea);
            area.setSuccessMessage("Search Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AreaAction.search] Error when saving error,", e1);
            }
            logger.error("[AreaAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            area.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            area.setSuccessMessage("");
            return "failure";
        }

        if (listOfSearchArea.size() == 0){
            area.setErrorMessage("Cannot Found Data");
            area.setSuccessMessage("");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchArea);

        logger.info("[AreaAction.search] end process <<<");

        return SUCCESS;
    }

    public List initComboArea(String query) {
        logger.info("[AreaAction.initComboArea] start process >>>");

        List<Area> listOfArea = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AreaBo areaBo = (AreaBo) ctx.getBean("areaBoProxy");

        try {
            listOfArea = areaBo.getComboAreaWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBo.saveErrorMessage(e.getMessage(), "AreaBO.getComboAreaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AreaAction.initComboArea] Error when saving error,", e1);
            }
            logger.error("[AreaAction.initComboArea] Error when get combo area," + "[" + logId + "] Found problem when retrieving combo area data, please inform to your admin.", e);
        }

        logger.info("[AreaAction.initComboArea] end process <<<");

        return listOfArea;
    }

}