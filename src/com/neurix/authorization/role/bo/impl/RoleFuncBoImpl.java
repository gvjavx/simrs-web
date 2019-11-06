package com.neurix.authorization.role.bo.impl;

import com.neurix.authorization.function.dao.FunctionDao;
import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.authorization.function.model.Menus;
import com.neurix.authorization.role.bo.RoleFuncBo;
import com.neurix.authorization.role.dao.RoleFuncDao;
import com.neurix.authorization.role.model.ImFuncRoles;
import com.neurix.authorization.role.model.ImFuncRolesPK;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.RoleFunc;
import com.neurix.common.displaytag.DisplayObject;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.*;

/**
 * Created by Ferdi on 26/01/2015.
 */
public class RoleFuncBoImpl implements RoleFuncBo {

    protected static transient Logger logger = Logger.getLogger(RoleFuncBoImpl.class);
    private RoleFuncDao roleFuncDao;
    private FunctionDao functionDao;

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }

    public void setRoleFuncDao(RoleFuncDao roleFuncDao) {
        this.roleFuncDao = roleFuncDao;
    }

    public List<Menus> getDefaultMenu() throws GeneralBOException {
        logger.info("[RoleFuncBoImpl.getDefaultMenu] start process >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        List<ImFunctions> listOfResultImFunction = null;
        try {
            listOfResultImFunction = functionDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[RoleFuncBoImpl.getDefaultMenu] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<Menus> listDefaultMenu=new ArrayList<Menus>();
        for (ImFunctions imFunctions : listOfResultImFunction) {
            if (imFunctions.getMenu()!=null && imFunctions.getMenu().longValue()!=0) {
                Menus menu=new Menus();
                menu.setFuncId(imFunctions.getFuncId());
                menu.setFuncName(imFunctions.getFuncName());
                menu.setStFuncId(imFunctions.getFuncId().toString());
                menu.setUrl(imFunctions.getUrl());
                menu.setFuncLevel(imFunctions.getFuncLevel());
                menu.setStFuncLevel(imFunctions.getFuncLevel().toString());
                menu.setParent(imFunctions.getParent());

                if (imFunctions.getParent()!=null) {
                    menu.setStParent(imFunctions.getParent().toString());
                }

                String path="";
                for (int i=0; i < imFunctions.getFuncLevel().longValue(); i++) {
                    path = path + "*";
                }

                menu.setStatusPath(path);

                if (menu.getUrl()!=null && !"".equalsIgnoreCase(menu.getUrl())) {
                    menu.setInitForm(true);
                }

                DisplayObject displayObject = new DisplayObject();
                displayObject.setCheckBox(imFunctions.getFuncId().toString(), "menuIdChecked", false, false, "selectItem(\'"+ imFunctions.getFuncId().toString() + "\',this," + "\'menuIdChecked\',\'childIdChecked\')");

                menu.setDisplayObjectCheck(displayObject);

                DisplayObject displayObjectDiv = new DisplayObject();
                if (imFunctions.getParent()!=null) {
                    displayObjectDiv.setDiv(imFunctions.getParent().toString(), "childIdChecked" );
                } else {
                    displayObjectDiv.setDiv("null", "childIdChecked" );
                }

                menu.setDisplayObjectDiv(displayObjectDiv);

                listDefaultMenu.add(menu);
            }
        }


        for (Menus menu : listDefaultMenu) {
            List<Functions> listOfDefaultFunc = new ArrayList<Functions>();

            for (ImFunctions imFunctions : listOfResultImFunction) {
                if (imFunctions.getMenu()==null) {
                    if (menu.getFuncId().longValue() == imFunctions.getParent().longValue()) {
                        Functions functions=new Functions();
                        functions.setFuncId(imFunctions.getFuncId());
                        functions.setStFuncId(imFunctions.getFuncId().toString());
                        functions.setFuncName(imFunctions.getFuncName());
                        functions.setUrl(imFunctions.getUrl());

                        DisplayObject displayObject = new DisplayObject();
                        displayObject.setCheckBox(imFunctions.getFuncId().toString(), "funcIdChecked", false, false, null);

                        functions.setDisplayObjectCheck(displayObject);

                        listOfDefaultFunc.add(functions);
                    }
                }
            }
            menu.setListOfFunctions(listOfDefaultFunc);
        }


        logger.info("[RoleFuncBoImpl.getDefaultMenu] end process >>>");

        return listDefaultMenu;
    }

    public List<RoleFunc> getByCriteria(RoleFunc searchRoleFunc) throws GeneralBOException {

        logger.info("[RoleFuncBoImpl.getByCriteria] start process >>>");

        List<RoleFunc> listOfResultRoleFunc = new ArrayList();

        if (searchRoleFunc != null) {
            Map hsCriteria = new HashMap();
            if (searchRoleFunc.getStRoleId() != null && !"".equalsIgnoreCase(searchRoleFunc.getStRoleId())) {
                hsCriteria.put("role_id", Long.parseLong(searchRoleFunc.getStRoleId()));
            }

            if (searchRoleFunc.getStFuncId() != null && !"".equalsIgnoreCase(searchRoleFunc.getStFuncId())) {
                hsCriteria.put("func_id", Long.parseLong(searchRoleFunc.getStFuncId()));
            }

            if (searchRoleFunc.getFlag() != null && !"".equalsIgnoreCase(searchRoleFunc.getFlag())) {
                if ("N".equalsIgnoreCase(searchRoleFunc.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchRoleFunc.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImFuncRoles> listOfResult = null;
            try {
                listOfResult = roleFuncDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            hsCriteria = new HashMap();
            hsCriteria.put("flag", "Y");
            List<ImFunctions> listOfResultImFunction = null;
            try {
                listOfResultImFunction = functionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            List<Menus> listDefaultMenu=new ArrayList<Menus>();
            for (ImFunctions imFunctions : listOfResultImFunction) {
                if (imFunctions.getMenu()!=null && imFunctions.getMenu().longValue()!=0) {
                    Menus menu=new Menus();
                    menu.setFuncId(imFunctions.getFuncId());
                    menu.setFuncName(imFunctions.getFuncName());
                    menu.setStFuncId(imFunctions.getFuncId().toString());
                    menu.setUrl(imFunctions.getUrl());
                    menu.setFuncLevel(imFunctions.getFuncLevel());
                    menu.setStFuncLevel(imFunctions.getFuncLevel().toString());
                    menu.setParent(imFunctions.getParent());

                    if (imFunctions.getParent()!=null) {
                        menu.setStParent(imFunctions.getParent().toString());
                    }

                    String path="";
                    for (int i=0; i < imFunctions.getFuncLevel().longValue(); i++) {
                        path = path + "*";
                    }

                    menu.setStatusPath(path);

                    if (menu.getUrl()!=null && !"".equalsIgnoreCase(menu.getUrl())) {
                        menu.setInitForm(true);
                    }

                    DisplayObject displayObject = new DisplayObject();
                    displayObject.setCheckBox(imFunctions.getFuncId().toString(), "menuIdChecked", false, false, "selectItem(\'"+ imFunctions.getFuncId().toString() + "\',this," + "\'menuIdChecked\',\'childIdChecked\')");

                    menu.setDisplayObjectCheck(displayObject);

                    DisplayObject displayObjectDiv = new DisplayObject();
                    if (imFunctions.getParent()!=null) {
                        displayObjectDiv.setDiv(imFunctions.getParent().toString(), "childIdChecked" );
                    } else {
                        displayObjectDiv.setDiv("null", "childIdChecked" );
                    }

                    menu.setDisplayObjectDiv(displayObjectDiv);

                    listDefaultMenu.add(menu);
                }
            }

            for (Menus menu : listDefaultMenu) {
                List<Functions> listOfDefaultFunc = new ArrayList<Functions>();

                for (ImFunctions imFunctions : listOfResultImFunction) {
                    if (imFunctions.getMenu()==null) {
                        if (menu.getFuncId().longValue() == imFunctions.getParent().longValue()) {
                            Functions functions=new Functions();
                            functions.setFuncId(imFunctions.getFuncId());
                            functions.setStFuncId(imFunctions.getFuncId().toString());
                            functions.setFuncName(imFunctions.getFuncName());
                            functions.setUrl(imFunctions.getUrl());

                            DisplayObject displayObject = new DisplayObject();
                            displayObject.setCheckBox(imFunctions.getFuncId().toString(), "funcIdChecked", false, false, null);

                            functions.setDisplayObjectCheck(displayObject);

                            listOfDefaultFunc.add(functions);
                        }
                    }
                }
                menu.setListOfFunctions(listOfDefaultFunc);
            }

            if (listOfResult != null) {
                RoleFunc resultRoleFunc = null;
                List<Menus> listCheckMenu = null;
                int i = 0 ;
                for (ImFuncRoles imFuncRoles : listOfResult) {
                    i++;
                    try {
                        if (resultRoleFunc!=null && imFuncRoles.getPrimaryKey().getRoleId().equals(resultRoleFunc.getRoleId())) {
                            storeListCheckMenu(listCheckMenu,imFuncRoles);
                        } else {
                            if (resultRoleFunc!=null) {
                                resultRoleFunc.setListOfMenu(listCheckMenu);
                                listOfResultRoleFunc.add(resultRoleFunc);
                            }

                            resultRoleFunc = new RoleFunc();
                            resultRoleFunc.setRoleId(imFuncRoles.getImRoles().getRoleId());
                            resultRoleFunc.setStRoleId(imFuncRoles.getImRoles().getRoleId().toString());
                            resultRoleFunc.setRoleName(imFuncRoles.getImRoles().getRoleName());
                            resultRoleFunc.setFlag(imFuncRoles.getFlag());

                            listCheckMenu = new ArrayList<Menus>();

                            for (Menus itemMenu : listDefaultMenu) {
                                Menus menuClone = (Menus) SerializationUtils.clone(itemMenu);
                                listCheckMenu.add(menuClone);
                            }

                            storeListCheckMenu(listCheckMenu, imFuncRoles);
                        }
                    }
                    catch(Exception e){
                        logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                    }


                }

                if (!listOfResult.isEmpty()) { //for handling the last object
                    if (resultRoleFunc!=null) {
                        resultRoleFunc.setListOfMenu(listCheckMenu);
                        listOfResultRoleFunc.add(resultRoleFunc);
                    }
                }

            }

        }

        logger.info("[RoleFuncBoImpl.getByCriteria] end process <<<");

        return listOfResultRoleFunc;
    }

    private void storeListCheckMenu(List<Menus> listCheckMenu, ImFuncRoles imFuncRoles) {
        ImFunctions imFunctions = imFuncRoles.getImFunctions();
        if (imFunctions.getMenu()!=null && imFunctions.getMenu().longValue()!=0) { //jika sebagai menu,
            for (Menus menu : listCheckMenu) {
                if (menu.getFuncId().longValue() == imFunctions.getFuncId().longValue()) {
                    menu.getDisplayObjectCheck().setCheckBox(menu.getFuncId().toString(), "menuIdChecked", true, false, "selectItem(\'"+ menu.getFuncId().toString() + "\',this," + "\'menuIdChecked\',\'childIdChecked\')");
                    break;
                }
            }
        } else { //jika sebagai function
            for (Menus menu : listCheckMenu) {
                if (menu.getFuncId().longValue() == imFunctions.getParent().longValue()) {
                    for (Functions functions : menu.getListOfFunctions()) {
                        if (functions.getFuncId().longValue() == imFunctions.getFuncId().longValue()) {
                            functions.getDisplayObjectCheck().setCheckBox(functions.getFuncId().toString(), "funcIdChecked", true, false, null);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public RoleFunc saveAdd(RoleFunc roleFunc) throws GeneralBOException {

        logger.info("[RoleFuncBoImpl.saveAdd] start process >>>");

        if (roleFunc != null) {

            long roleId = roleFunc.getRoleId().longValue();

            Map hsCriteria = new HashMap();
            hsCriteria.put("role_id", roleId);
            hsCriteria.put("flag", "Y");

            List<ImFuncRoles> listOfResult = new ArrayList<ImFuncRoles>();

            try {
                listOfResult = roleFuncDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfResult != null && listOfResult.isEmpty()) {

                for (Functions func : roleFunc.getListOfAllFunctions()) {
                    ImFuncRoles imfuncRoles = new ImFuncRoles();

                    ImFuncRolesPK imFuncRolesPK = new ImFuncRolesPK();
                    imFuncRolesPK.setRoleId(roleFunc.getRoleId());
                    imFuncRolesPK.setFuncId(func.getFuncId());

                    imfuncRoles.setPrimaryKey(imFuncRolesPK);
                    imfuncRoles.setCreatedDate(roleFunc.getCreatedDate());
                    imfuncRoles.setCreatedWho(roleFunc.getCreatedWho());
                    imfuncRoles.setLastUpdate(roleFunc.getLastUpdate());
                    imfuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());
                    imfuncRoles.setFlag("Y");

                    try {
                        roleFuncDao.addAndSave(imfuncRoles);
                    } catch (HibernateException e) {
                        logger.error("[RoleFuncBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data role func, please info to your admin..." + e.getMessage());
                    }

                }

            }
        }

        logger.info("[RoleFuncBoImpl.saveAdd] end process <<<");

        return roleFunc;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(roleFuncDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(RoleFunc roleFunc) throws GeneralBOException {

        logger.info("[RoleFuncBoImpl.saveEdit] start process >>>");

        if (roleFunc != null) {

            long roleId = roleFunc.getRoleId().longValue();

            Map hsCriteria = new HashMap();
            hsCriteria.put("role_id", roleId);
//            hsCriteria.put("flag", "Y");

            List<ImFuncRoles> listOfResult = null;

            try {
                listOfResult = roleFuncDao.getRoleFuncByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfResult != null && !listOfResult.isEmpty()) {

                for (ImFuncRoles funcRolesData :listOfResult){
                    funcRolesData.setFlag("N");
                    try {
                        roleFuncDao.updateAndSave(funcRolesData);
                    } catch (HibernateException e) {
                        logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving updated data role func, please inform to your admin...," + e.getMessage());
                    }
                }
                for (Functions functionsData : roleFunc.getListOfAllFunctions()){
                    boolean notFound = false;
                    for (ImFuncRoles funcRolesUpdate : listOfResult){
                        if (functionsData.getFuncId().longValue() == funcRolesUpdate.getPrimaryKey().getFuncId().longValue()){
                            funcRolesUpdate.setFlag("Y");
                            try {
                                roleFuncDao.updateAndSave(funcRolesUpdate);
                            } catch (HibernateException e) {
                                logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving updated data role func, please inform to your admin...," + e.getMessage());
                            }
                        } else {
                            hsCriteria.put("func_id",functionsData.getFuncId());
                            List<ImFuncRoles> listOfRoleFunc = null;
                            listOfRoleFunc = roleFuncDao.getRoleFuncByCriteria(hsCriteria);
                            if (listOfRoleFunc.isEmpty()){
                                notFound = true;
                            }
                        }
                    }
                    if (notFound){
                        ImFuncRoles imfuncRoles = new ImFuncRoles();
                        ImFuncRolesPK imFuncRolesPK = new ImFuncRolesPK();
                        imFuncRolesPK.setRoleId(roleFunc.getRoleId());
                        imFuncRolesPK.setFuncId(functionsData.getFuncId());
                        imfuncRoles.setPrimaryKey(imFuncRolesPK);
                        imfuncRoles.setCreatedDate(roleFunc.getLastUpdate());
                        imfuncRoles.setCreatedWho(roleFunc.getLastUpdateWho());
                        imfuncRoles.setLastUpdate(roleFunc.getLastUpdate());
                        imfuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());
                        imfuncRoles.setFlag("Y");
                        try {
                            roleFuncDao.addAndSave(imfuncRoles);
                        } catch (HibernateException e) {
                            logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data role func, please info to your admin..." + e.getMessage());
                        }
                    }
                }

//
//
////                //set flag N if exist data from tabel not in list updated role func
////                for (ImFuncRoles imFuncRoles : listOfResult) {
////                    boolean found = false;
////                    for (Functions functions : roleFunc.getListOfAllFunctions()) {
////                        if (functions.getFuncId().longValue() == imFuncRoles.getPrimaryKey().getFuncId().longValue()) {
////                            if("Y".equalsIgnoreCase(imFuncRoles.getFlag())){
////                                found = true;
////                            }
////                            break;
////                        }
////                    }
////
////                    if (!found) {
////                        imFuncRoles.setFlag("N");
////                        imFuncRoles.setLastUpdate(roleFunc.getLastUpdate());
////                        imFuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());
////
////                        try {
////                            roleFuncDao.updateAndSave(imFuncRoles);
////                        } catch (HibernateException e) {
////                            logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
////                            throw new GeneralBOException("Found problem when saving updated data role func, please inform to your admin...," + e.getMessage());
////                        }
////                    }
////                }
////
////                //add new if not in exist data from tabel but in list updated role func
////                for (Functions functions : roleFunc.getListOfAllFunctions()) {
////                    boolean found = false;
////                    boolean notActive = false;
////                    for (ImFuncRoles imFuncRoles : listOfResult) {
////                        if (functions.getFuncId().longValue() == imFuncRoles.getPrimaryKey().getFuncId().longValue()) {
////                            if ("Y".equalsIgnoreCase(imFuncRoles.getFlag())){
////                                found = true;
////                            }
////                            if ("N".equalsIgnoreCase(imFuncRoles.getFlag())){
////                                notActive = true;
////                            }
////                            break;
////                        }
////                    }
////
////                    if (!found) {
////                        ImFuncRoles imfuncRoles = new ImFuncRoles();
////                        ImFuncRolesPK imFuncRolesPK = new ImFuncRolesPK();
////                        imFuncRolesPK.setRoleId(roleFunc.getRoleId());
////                        imFuncRolesPK.setFuncId(functions.getFuncId());
////
////                        imfuncRoles.setPrimaryKey(imFuncRolesPK);
////                        imfuncRoles.setCreatedDate(roleFunc.getLastUpdate());
////                        imfuncRoles.setCreatedWho(roleFunc.getLastUpdateWho());
////                        imfuncRoles.setLastUpdate(roleFunc.getLastUpdate());
////                        imfuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());
////                        imfuncRoles.setFlag("Y");
////                        try {
////                            if (notActive){
////                                roleFuncDao.updateAndSave(imfuncRoles);
////                            } else {
////                                roleFuncDao.addAndSave(imfuncRoles);
////                            }
////                        } catch (HibernateException e) {
////                            logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
////                            throw new GeneralBOException("Found problem when saving new data role func, please info to your admin..." + e.getMessage());
////                        }
////                    }
//
////                    if (notActive){
////                        ImFuncRoles imfuncRoles = new ImFuncRoles();
////                        ImFuncRolesPK imFuncRolesPK = new ImFuncRolesPK();
////                        imFuncRolesPK.setRoleId(roleFunc.getRoleId());
////                        imFuncRolesPK.setFuncId(functions.getFuncId());
////
////                        imfuncRoles.setPrimaryKey(imFuncRolesPK);
////                        imfuncRoles.setCreatedDate(roleFunc.getLastUpdate());
////                        imfuncRoles.setCreatedWho(roleFunc.getLastUpdateWho());
////                        imfuncRoles.setLastUpdate(roleFunc.getLastUpdate());
////                        imfuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());
////                        imfuncRoles.setFlag("Y");
////
////                        try {
////                            roleFuncDao.updateAndSave(imfuncRoles);
////                        } catch (HibernateException e) {
////                            logger.error("[RoleFuncBoImpl.saveEdit] Error, " + e.getMessage());
////                            throw new GeneralBOException("Found problem when saving new data role func, please info to your admin..." + e.getMessage());
////                        }
////                    }
//                }
            }
        else {
                logger.error("[RoleFuncBoImpl.saveDelete] Unable to save edit cause no have reference data exist in user and function table.");
                throw new GeneralBOException("Found problem when saving edit data role cause no have reference data exist in user and function table, please info to your admin...");
            }

        } else {
            logger.error("[RoleFuncBoImpl.saveEdit] Unable to save edit cause no have role id.");
            throw new GeneralBOException("Found problem when saving edit data cause no have role id, please info to your admin...");
        }

        logger.info("[RoleFuncBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(RoleFunc roleFunc) throws GeneralBOException {

        logger.info("[RoleFuncBoImpl.saveDelete] start process >>>");

        if (roleFunc != null) {

            long roleId = roleFunc.getRoleId().longValue();

            Map hsCriteria = new HashMap();
            hsCriteria.put("role_id", roleId);
            hsCriteria.put("flag", "Y");

            List<ImFuncRoles> listOfResult = null;

            try {
                listOfResult = roleFuncDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleFuncBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }


            if (listOfResult!=null && !listOfResult.isEmpty()) {

                for (ImFuncRoles imFuncRoles : listOfResult) {

                    //update data with flag=N
                    imFuncRoles.setFlag("N");
                    imFuncRoles.setLastUpdate(roleFunc.getLastUpdate());
                    imFuncRoles.setLastUpdateWho(roleFunc.getLastUpdateWho());

                    try {
                        roleFuncDao.updateAndSave(imFuncRoles);
                    } catch (HibernateException e) {
                        logger.error("[RoleFuncBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data role func, please info to your admin..." + e.getMessage());
                    }

                }

            } else {
                logger.error("[RoleFuncBoImpl.saveDelete] Unable to delete cause no have reference data exist in user and function table.");
                throw new GeneralBOException("Found problem when saving delete data role cause no have reference data exist in user and function table, please info to your admin...");
            }
        } else {
            logger.error("[RoleFuncBoImpl.saveDelete] Unable to delete cause no have role id.");
            throw new GeneralBOException("Found problem when saving delete data role cause no have role id, please info to your admin...");
        }

        logger.info("[RoleFuncBoImpl.saveDelete] end process <<<");
    }



}
