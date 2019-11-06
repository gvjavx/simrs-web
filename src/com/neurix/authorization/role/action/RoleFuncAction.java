package com.neurix.authorization.role.action;

import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.function.model.Menus;
import com.neurix.authorization.role.bo.RoleBo;
import com.neurix.authorization.role.bo.RoleFuncBo;
import com.neurix.authorization.role.model.RoleFunc;
import com.neurix.authorization.role.model.Roles;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ferdi on 26/01/2015.
 */
public class RoleFuncAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(RoleFuncAction.class);

    private RoleFuncBo roleFuncBoProxy;
    private RoleFunc roleFunc;
    private Menus menu;
    private boolean edit;
    private String roleId;
    private String[] menuIdChecked;
    private CutiPegawaiBo cutiPegawaiBoProxy;
    private Biodata biodata;

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RoleFuncAction.logger = logger;
    }

    public RoleFuncBo getRoleFuncBoProxy() {
        return roleFuncBoProxy;
    }

    public CutiPegawaiBo getCutiPegawaiBoProxy() {
        return cutiPegawaiBoProxy;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public String[] getMenuIdChecked() {
        return menuIdChecked;
    }

    public void setMenuIdChecked(String[] menuIdChecked) {
        this.menuIdChecked = menuIdChecked;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setRoleFuncBoProxy(RoleFuncBo roleFuncBoProxy) {
        this.roleFuncBoProxy = roleFuncBoProxy;
    }

    public RoleFunc getRoleFunc() {
        return roleFunc;
    }

    public void setRoleFunc(RoleFunc roleFunc) {
        this.roleFunc = roleFunc;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }



    @Override
    public String edit() {
        logger.info("[RoleFuncAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        RoleFunc editRoleFunc = new RoleFunc();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(itemId)) {
                    editRoleFunc.setRoleId(itemRoleFunc.getRoleId());
                    editRoleFunc.setStRoleId(itemRoleFunc.getStRoleId());
                    editRoleFunc.setRoleName(itemRoleFunc.getRoleName());
                    editRoleFunc.setListOfMenu(itemRoleFunc.getListOfMenu());
                    setRoleId(itemRoleFunc.getStRoleId());

                    break;
                }
            }

        }

        setRoleFunc(editRoleFunc);
        setAddOrEdit(true);

        logger.info("[RoleFuncAction.edit] end process <<<");

        return "view_detail";
    }

    @Override
    public String add() {
        logger.info("[RoleFuncAction.add] start process >>>");

        RoleFunc addRoleFunc = new RoleFunc();

        List<Menus> listOfDefaultMenu = new ArrayList();
        try {
            listOfDefaultMenu = roleFuncBoProxy.getDefaultMenu();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = roleFuncBoProxy.saveErrorMessage(e.getMessage(), "RoleFuncBO.getDefaultMenu");
            } catch (GeneralBOException e1) {
                logger.error("[RoleFuncAction.search] Error when saving error,", e1);
            }
            logger.error("[RoleFuncAction.save] Error when searching default menu func by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching default menu func data, please inform to your admin" );
            return "failure";
        }

        addRoleFunc.setListOfMenu(listOfDefaultMenu);

        setRoleFunc(addRoleFunc);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfDefaultMenu",listOfDefaultMenu);
        session.removeAttribute("listOfResult");

        logger.info("[RoleFuncAction.add] end process <<<");

        return "view_detail_add";
    }

    @Override
    public String delete() {
        logger.info("[RoleFuncAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        RoleFunc editRoleFunc = new RoleFunc();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(itemId)) {
                    editRoleFunc.setRoleId(itemRoleFunc.getRoleId());
                    editRoleFunc.setStRoleId(itemRoleFunc.getStRoleId());
                    editRoleFunc.setRoleName(itemRoleFunc.getRoleName());
                    editRoleFunc.setListOfMenu(itemRoleFunc.getListOfMenu());
                    setRoleId(itemRoleFunc.getStRoleId());
                    break;
                }
            }

        }

        setRoleFunc(editRoleFunc);
        setDelete(true);

        logger.info("[RoleFuncAction.delete] end process <<<");

        return "view_detail";
    }

    @Override
    public String view() {
        logger.info("[RoleFuncAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        RoleFunc editRoleFunc = new RoleFunc();

        if (itemFlag != null) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(itemId)) {
                    editRoleFunc.setRoleId(itemRoleFunc.getRoleId());
                    editRoleFunc.setStRoleId(itemRoleFunc.getStRoleId());
                    editRoleFunc.setRoleName(itemRoleFunc.getRoleName());
                    editRoleFunc.setListOfMenu(itemRoleFunc.getListOfMenu());

                    setRoleId(itemRoleFunc.getStRoleId());

                    break;
                }
            }

        }

        setRoleFunc(editRoleFunc);

        logger.info("[RoleFuncAction.view] end process <<<");

        return "view_detail";
    }

    public String viewDetail() {
        logger.info("[RoleFuncAction.viewDetail] start process <<<");

        boolean flagAdd = isAdd();
        boolean flagEdit = isEdit();

        String menuId = getId();
        String roleId = getRoleId();
        Menus selectedMenu = new Menus();

        if (menuId != null && !"".equalsIgnoreCase(menuId)) {

            if (flagAdd) {

                HttpSession session = ServletActionContext.getRequest().getSession();
                List<Menus> listOfMenus = (List<Menus>) session.getAttribute("listOfDefaultMenu");

                for (Menus menu : listOfMenus) {
                    if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {
                        selectedMenu.setListOfFunctions(menu.getListOfFunctions());
                        break;
                    }
                }

                setAddOrEdit(true);

            } else {

                if (roleId!=null && !"".equalsIgnoreCase(roleId)) {

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

                    for (RoleFunc itemRoleFunc : listOfResult) {
                        if (itemRoleFunc.getStRoleId().equalsIgnoreCase(roleId)) {

                            for (Menus menu : itemRoleFunc.getListOfMenu()) {
                                if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {
                                    selectedMenu.setListOfFunctions(menu.getListOfFunctions());
                                    break;
                                }
                            }

                            break;
                        }
                    }

                    if (flagEdit) setAddOrEdit(true);
                }

            }

        }

        setRoleId(roleId); //roleId
        setId(menuId); //menuId
        setMenu(selectedMenu);

        logger.info("[RoleFuncAction.viewDetail] end process <<<");

        return "view_detail_func";
    }

    @Override
    public String save() {
        logger.info("[RoleFuncAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {
                String itemId = "";
                if (getRoleFunc() != null) {
                    itemId = getRoleFunc().getStRoleId();
                } else {
                    itemId = getRoleId();
                }

                if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                    //edit
                    try {
                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        RoleFunc editRoleFunc = getRoleFunc()!= null ? getRoleFunc() : new RoleFunc();
                        String[] checkedMenu = getMenuIdChecked();
                        List<Functions> listOfAllFunc = new ArrayList<Functions>();
                        for (int i=0; i < checkedMenu.length; i++) { //store checked menu
                            Functions functions = new Functions();
                            functions.setFuncId(Long.valueOf(checkedMenu[i]));
                            listOfAllFunc.add(functions);
                        }

                        //store function each menu
                        HttpSession session = ServletActionContext.getRequest().getSession();
                        List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

                        for (RoleFunc itemRoleFunc : listOfResult) {
                            if (itemRoleFunc.getStRoleId().equalsIgnoreCase(itemId)) {

                                for (int i=0; i < checkedMenu.length; i++) {
                                    for (Menus sessionMenu : itemRoleFunc.getListOfMenu()) {
                                        if (sessionMenu.getFuncId().longValue() == Long.valueOf(checkedMenu[i]).longValue()) {
                                            for (Functions sessionFunc : sessionMenu.getListOfFunctions()) {
                                                if (sessionFunc.getDisplayObjectCheck().isCheckBoxChecked()) {
                                                    Functions functions = new Functions();
                                                    functions.setFuncId(sessionFunc.getFuncId());

                                                    listOfAllFunc.add(functions);
                                                }
                                            }

                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }

                        editRoleFunc.setRoleId(Long.valueOf(itemId));
                        editRoleFunc.setListOfAllFunctions(listOfAllFunc);
                        editRoleFunc.setCreatedDate(updateTime);
                        editRoleFunc.setCreatedWho(userLogin);
                        editRoleFunc.setLastUpdate(updateTime);
                        editRoleFunc.setLastUpdateWho(userLogin);

                        roleFuncBoProxy.saveEdit(editRoleFunc);


                    } catch (UsernameNotFoundException e) {
                        logger.error("[RoleFuncAction.save] Error when editing item role func,", e);
                        addActionError("Error, " + e.getMessage());


                        return ERROR;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = roleFuncBoProxy.saveErrorMessage(e.getMessage(), "RoleFuncBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[RoleFuncAction.save] Error when saving error,", e1);
                        }
                        logger.error("[RoleFuncAction.save] Error when editing item role func," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());


                        return ERROR;
                    }
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                return "success_save_edit";

            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    RoleFunc entryRoleFunc = getRoleFunc();
                    String[] checkedMenu = getMenuIdChecked();
                    List<Functions> listOfAllFunc = new ArrayList<Functions>();
                    for (int i=0; i < checkedMenu.length; i++) { //store checked menu
                        Functions functions = new Functions();
                        functions.setFuncId(Long.valueOf(checkedMenu[i]));
                        listOfAllFunc.add(functions);
                    }

                    //store function each menu
                    HttpSession session = ServletActionContext.getRequest().getSession();
                    List<Menus> listOfMenus = (List<Menus>) session.getAttribute("listOfDefaultMenu");

                    for (int i=0; i < checkedMenu.length; i++) {
                        for (Menus sessionMenu : listOfMenus) {
                            if (sessionMenu.getFuncId().longValue() == Long.valueOf(checkedMenu[i]).longValue()) {
                                for (Functions sessionFunc : sessionMenu.getListOfFunctions()) {
                                    if (sessionFunc.getDisplayObjectCheck().isCheckBoxChecked()) {
                                        Functions functions = new Functions();
                                        functions.setFuncId(sessionFunc.getFuncId());

                                        listOfAllFunc.add(functions);
                                    }
                                }
                                break;
                            }
                        }
                    }

                    entryRoleFunc.setRoleId(Long.valueOf(entryRoleFunc.getStRoleId()));
                    entryRoleFunc.setListOfAllFunctions(listOfAllFunc);
                    entryRoleFunc.setCreatedDate(createTime);
                    entryRoleFunc.setCreatedWho(userLogin);
                    entryRoleFunc.setLastUpdate(createTime);
                    entryRoleFunc.setLastUpdateWho(userLogin);

                    roleFuncBoProxy.saveAdd(entryRoleFunc);

                } catch (UsernameNotFoundException e) {
                    logger.error("[RoleFuncAction.save] Error when adding item role func,", e);
                    addActionError("Error, " + e.getMessage());

                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = roleFuncBoProxy.saveErrorMessage(e.getMessage(), "RoleFuncBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[RoleFuncAction.save] Error when saving error,", e1);
                    }
                    logger.error("[RoleFuncAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());

                    return ERROR;
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");
                session.removeAttribute("listOfDefaultMenu");

                return "success_save_add";

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                RoleFunc deleteRoleFunc = getRoleFunc() != null ? getRoleFunc() : new RoleFunc();
                if (getRoleFunc() != null) {
                    if (deleteRoleFunc.getStRoleId() != null && !"".equalsIgnoreCase(deleteRoleFunc.getStRoleId()))
                        deleteRoleFunc.setRoleId(Long.valueOf(deleteRoleFunc.getStRoleId()));
                } else {
                    deleteRoleFunc.setRoleId(Long.valueOf(getRoleId()));
                }

                deleteRoleFunc.setLastUpdate(updateTime);
                deleteRoleFunc.setLastUpdateWho(userLogin);

                roleFuncBoProxy.saveDelete(deleteRoleFunc);

            } catch (UsernameNotFoundException e) {
                logger.error("[RoleFuncAction.save] Error when deleting item role func,", e);
                addActionError("Error, " + e.getMessage());

                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = roleFuncBoProxy.saveErrorMessage(e.getMessage(), "RoleFuncBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[RoleFuncAction.save] Error when saving error,", e1);
                }
                logger.error("[RoleFuncAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());


                return ERROR;
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");

            return "success_save_delete";
        }

        logger.info("[RoleFuncAction.save] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        RoleFunc initRoleFunc = new RoleFunc();
        setRoleFunc(initRoleFunc);
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
        logger.info("[RoleFuncAction.search] start process >>>");

        RoleFunc searchRoleFunc = getRoleFunc();
        List<RoleFunc> listOfSearchRoleFunc = new ArrayList();
        try {
            listOfSearchRoleFunc = roleFuncBoProxy.getByCriteria(searchRoleFunc);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = roleFuncBoProxy.saveErrorMessage(e.getMessage(), "RoleFuncBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RoleFuncAction.search] Error when saving error,", e1);
            }
            logger.error("[RoleFuncAction.search] Error when searching role func by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );

            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchRoleFunc);

        logger.info("[RoleFuncAction.search] end process <<<");

        return SUCCESS;
    }

    public String saveListFunction(String[] arrCheckedFuncId, boolean addFlag, boolean editFlag, String roleId, String menuId) {

        logger.info("[RoleFuncAction.saveDetailFunction] start process <<<");

        //update to session
        HttpSession session = ServletActionContext.getRequest().getSession();
        boolean flag = false;
        if (addFlag) {

            List<Menus> listOfMenus = (List<Menus>) session.getAttribute("listOfDefaultMenu");

            for (Menus menu : listOfMenus) {
                if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {

                    //reset all item to unchecked
                    for (Functions functions : menu.getListOfFunctions()) {
                        functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                    }

                    //set item checked based on arrCheckedFuncId

                    for (int i=0; i < arrCheckedFuncId.length; i++) {
                        for (Functions functions : menu.getListOfFunctions()) {
                            if (arrCheckedFuncId[i].equalsIgnoreCase(functions.getStFuncId())) {
                                functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                                break;
                            }
                        }
                    }

                    flag = true;
                    break;
                }
            }

            session.removeAttribute("listOfDefaultMenu");
            session.setAttribute("listOfDefaultMenu",listOfMenus);

        } else if (editFlag) {

            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(roleId)) {

                    for (Menus menu : itemRoleFunc.getListOfMenu()) {
                        if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {

                            //reset all item to unchecked
                            for (Functions functions : menu.getListOfFunctions()) {
                                functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                            }

                            //set item checked based on arrCheckedFuncId

                            for (int i=0; i < arrCheckedFuncId.length; i++) {
                                for (Functions functions : menu.getListOfFunctions()) {
                                    if (arrCheckedFuncId[i].equalsIgnoreCase(functions.getStFuncId())) {
                                        functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                                        break;
                                    }
                                }
                            }

                            flag = true;
                            break;
                        }
                    }

                    break;
                }
            }

            session.removeAttribute("listOfResult");
            session.setAttribute("listOfResult",listOfResult);

        }


        logger.info("[RoleFuncAction.saveDetailFunction] end process <<<");

        if (flag) {
            return "00";
        } else {
            return "01";
        }

    }

    public String saveDetailFunction(boolean checked, boolean addFlag, boolean editFlag, String roleId, String menuId) {

        logger.info("[RoleFuncAction.saveDetailFunction] start process <<<");

        //update to session
        HttpSession session = ServletActionContext.getRequest().getSession();
        boolean flag = false;
        if (addFlag) {

            List<Menus> listOfMenus = (List<Menus>) session.getAttribute("listOfDefaultMenu");

            for (Menus menu : listOfMenus) {
                if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {

                    if (checked) {
                        //set all item to checked
                        for (Functions functions : menu.getListOfFunctions()) {
                            functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                        }
                    } else {
                        //set all item to unchecked
                        for (Functions functions : menu.getListOfFunctions()) {
                            functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                        }
                    }
                    flag = true;
                    break;
                }
            }

            session.removeAttribute("listOfDefaultMenu");
            session.setAttribute("listOfDefaultMenu",listOfMenus);

        } else if (editFlag) {

            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(roleId)) {

                    for (Menus menu : itemRoleFunc.getListOfMenu()) {
                        if (menu.getFuncId().longValue() == Long.valueOf(menuId).longValue()) {

                            if (checked) {
                                //set all item to checked
                                for (Functions functions : menu.getListOfFunctions()) {
                                    functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                                }
                            } else {
                                //set all item to unchecked
                                for (Functions functions : menu.getListOfFunctions()) {
                                    functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                                }
                            }

                            flag = true;
                            break;
                        }
                    }

                    break;
                }
            }

            session.removeAttribute("listOfResult");
            session.setAttribute("listOfResult",listOfResult);

        }


        logger.info("[RoleFuncAction.saveDetailFunction] end process <<<");

        if (flag) {
            return "00";
        } else {
            return "01";
        }

    }

    public String saveDetailFunction(boolean checked, boolean addFlag, boolean editFlag, String roleId) {

        logger.info("[RoleFuncAction.saveDetailFunction] start process <<<");

        //update to session
        HttpSession session = ServletActionContext.getRequest().getSession();
        boolean flag = false;
        if (addFlag) {

            List<Menus> listOfMenus = (List<Menus>) session.getAttribute("listOfDefaultMenu");

            for (Menus menu : listOfMenus) {
                if (checked) {
                    //set all item to checked
                    for (Functions functions : menu.getListOfFunctions()) {
                        functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                    }
                } else {
                    //set all item to unchecked
                    for (Functions functions : menu.getListOfFunctions()) {
                        functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                    }
                }
                flag = true;
            }

            session.removeAttribute("listOfDefaultMenu");
            session.setAttribute("listOfDefaultMenu",listOfMenus);

        } else if (editFlag) {

            List<RoleFunc> listOfResult = (List<RoleFunc>) session.getAttribute("listOfResult");

            for (RoleFunc itemRoleFunc : listOfResult) {
                if (itemRoleFunc.getStRoleId().equalsIgnoreCase(roleId)) {

                    for (Menus menu : itemRoleFunc.getListOfMenu()) {
                        if (checked) {
                            //set all item to checked
                            for (Functions functions : menu.getListOfFunctions()) {
                                functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                            }
                        } else {
                            //set all item to unchecked
                            for (Functions functions : menu.getListOfFunctions()) {
                                functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", false, false, null);
                            }
                        }

                        flag = true;
                    }

                    break;
                }
            }

            session.removeAttribute("listOfResult");
            session.setAttribute("listOfResult",listOfResult);

        }


        logger.info("[RoleFuncAction.saveDetailFunction] end process <<<");

        if (flag) {
            return "00";
        } else {
            return "01";
        }

    }

}
