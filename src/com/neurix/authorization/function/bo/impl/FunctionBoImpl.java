package com.neurix.authorization.function.bo.impl;

import com.neurix.authorization.function.bo.FunctionBo;
import com.neurix.authorization.function.dao.FunctionDao;
import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.authorization.function.model.ImFunctionsHistory;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.Roles;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 18/01/13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class FunctionBoImpl implements FunctionBo {
    protected static transient Logger logger = Logger.getLogger(FunctionBoImpl.class);
    private FunctionDao functionDao;

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }

    public Long getParentLevel(Long parentId) throws GeneralBOException {
        logger.info("[FunctionBoImpl.getParentLevel] start process >>>");

        ImFunctions functionsParentLevel = null;
        try {
            functionsParentLevel = functionDao.getParentLevel(parentId, "Y");
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getParentLevel] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get parent level, please info to your admin..." + e.getMessage());
        }

        Long parentLevel = null;
        if (functionsParentLevel != null) {
            parentLevel = functionsParentLevel.getFuncLevel();
        }

        logger.info("[FunctionBoImpl.getParentLevel] start process >>>");
        return parentLevel;
    }

    public List<Functions> getComboParent() throws GeneralBOException {
        logger.info("[FunctionBoImpl.getComboParent] start process >>>");

        List<Functions> listComboParent = new ArrayList();
        List<ImFunctions> listParent = null;
        try {
            listParent = functionDao.getListParent();
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getComboParent] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving combo parent, please info to your admin..." + e.getMessage());
        }

        if (listParent != null) {
            for (ImFunctions imFunctions : listParent) {
                Functions itemComboParent = new Functions();
                itemComboParent.setStFuncId(imFunctions.getFuncId().toString());
                itemComboParent.setFuncName(imFunctions.getFuncName());
                listComboParent.add(itemComboParent);
            }
        }
        logger.info("[FunctionBoImpl.getComboParent] end process <<<");
        return listComboParent;
    }

    public List<Functions> getComboParentWithCriteria(String query) throws GeneralBOException {
        logger.info("[FunctionBoImpl.getComboParentWithCriteria] start process >>>");

        List<Functions> listComboParent = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImFunctions> listParent = null;
        try {
            listParent = functionDao.getListParent(criteria);
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getComboParentWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list parent with criteria, please info to your admin..." + e.getMessage());
        }

        if (listParent != null) {
            for (ImFunctions imFunctions : listParent) {
                Functions itemComboParent = new Functions();
                itemComboParent.setStFuncId(imFunctions.getFuncId().toString());
                itemComboParent.setFuncName(imFunctions.getFuncName());
                listComboParent.add(itemComboParent);
            }
        }
        logger.info("[FunctionBoImpl.getComboParentWithCriteria] end process <<<");
        return listComboParent;
    }

    public List<Functions> getComboFunctionWithCriteria(String query) throws GeneralBOException {
        logger.info("[FunctionBoImpl.getComboFunctionWithCriteria] start process >>>");

        List<Functions> listComboFunction = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImFunctions> listFunction = null;
        try {
            listFunction = functionDao.getListFunction(criteria);
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getComboFunctionWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list function with criteria, please info to your admin..." + e.getMessage());
        }

        if (listFunction != null) {
            for (ImFunctions imFunctions : listFunction) {
                Functions itemComboFunction = new Functions();
                itemComboFunction.setStFuncId(imFunctions.getFuncId().toString());
                itemComboFunction.setFuncName(imFunctions.getFuncName());
                listComboFunction.add(itemComboFunction);
            }
        }
        logger.info("[FunctionBoImpl.getComboFunctionWithCriteria] end process <<<");
        return listComboFunction;
    }

    public List<Roles> getRolesByFunctionURL(String url) throws GeneralBOException {
        logger.info("[FunctionBoImpl.getRolesByFunctionURL] start process >>>");

        ImFunctions rolesOfURL = null;
        try {
            rolesOfURL = functionDao.getFunctionByURL(url, "Y");
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getRolesByFunctionURL] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving function URL, please info to your admin..." + e.getMessage());
        }
        Collection<ImRoles> listOfImRoles = null;
        List<Roles> listRoles = new ArrayList();
        if (rolesOfURL != null) {
            listOfImRoles = rolesOfURL.getImRole();
            for (ImRoles imRoles : listOfImRoles) {
                Roles roles = new Roles(imRoles.getRoleId(), imRoles.getRoleName());
                listRoles.add(roles);
            }
        }

        logger.info("[FunctionBoImpl.getRolesByFunctionURL] end process <<<");

        return listRoles;
    }

    public List<Functions> getAll() throws GeneralBOException {

        logger.info("[FunctionBoImpl.getAll] start process >>>");

        List<Functions> listOfResultFunctions = new ArrayList();
        List<ImFunctions> listOfFunctions = null;
        try {
            listOfFunctions = functionDao.getAll();
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getAll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting all data, please info to your admin..." + e.getMessage());
        }

        if (listOfFunctions != null) {
            Functions resultFunctions;
            for (ImFunctions imFunctions : listOfFunctions) {
                resultFunctions = new Functions();

                try {
                    BeanUtils.copyProperties(resultFunctions, imFunctions);
                } catch (IllegalAccessException e) {
                    logger.error("[FunctionBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving imFunctions to resultFunctions at getting all data, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[FunctionBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving imFunctions to resultFunctions at getting all data, please info to your admin..." + e.getMessage());
                }

                resultFunctions.setFuncId(imFunctions.getFuncId().longValue());
                resultFunctions.setFlag(imFunctions.getFlag());
                listOfResultFunctions.add(resultFunctions);
            }
        }

        logger.info("[FunctionBoImpl.getAll] end process <<<");

        return listOfResultFunctions;
    }

    public List<Functions> getByCriteria(Functions searchFunctions) throws GeneralBOException {

        logger.info("[FunctionBoImpl.getFunctionByCriteria] start process >>>");

        List<Functions> listOfResultFunctions = new ArrayList();

        if (searchFunctions != null) {
            Map hsCriteria = new HashMap();
            if (searchFunctions.getStFuncId() != null && !"".equalsIgnoreCase(searchFunctions.getStFuncId())) {
                hsCriteria.put("func_id", Long.parseLong(searchFunctions.getStFuncId()));
            }

            if (searchFunctions.getFuncName() != null && !"".equalsIgnoreCase(searchFunctions.getFuncName())) {
                hsCriteria.put("func_name", searchFunctions.getFuncName());
            }

            if (searchFunctions.getUrl() != null && !"".equalsIgnoreCase(searchFunctions.getUrl())) {
                hsCriteria.put("url", searchFunctions.getUrl());
            }

            if (searchFunctions.getStFuncLevel() != null && !"".equalsIgnoreCase(searchFunctions.getStFuncLevel())) {
                hsCriteria.put("func_level", Long.parseLong(searchFunctions.getStFuncLevel()));
            }

            if (searchFunctions.getStMenu() != null && !"".equalsIgnoreCase(searchFunctions.getStMenu())) {
                hsCriteria.put("menu", Long.parseLong(searchFunctions.getStMenu()));
            }

            if (searchFunctions.getStParent() != null && !"".equalsIgnoreCase(searchFunctions.getStParent())) {
                hsCriteria.put("parent", Long.parseLong(searchFunctions.getStParent()));
            }

            if (searchFunctions.getFlag() != null && !"".equalsIgnoreCase(searchFunctions.getFlag())) {
                if ("N".equalsIgnoreCase(searchFunctions.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchFunctions.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImFunctions> listOfFunctions = null;
            try {
                listOfFunctions = functionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[FunctionBoImpl.getFunctionByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfFunctions != null) {
                Functions resultFunctions;
                for (ImFunctions imFunctions : listOfFunctions) {
                    resultFunctions = new Functions();

                    try {
                        BeanUtils.copyProperties(resultFunctions, imFunctions);
                    } catch (IllegalAccessException e) {
                        logger.error("[FunctionBoImpl.getFunctionByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping saving imFunctions to resultFunctions at search criteria, please info to your admin..." + e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error("[FunctionBoImpl.getFunctionByCriteria] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping saving imFunctions to resultFunctions at search criteria, please info to your admin..." + e.getMessage());
                    }

                    resultFunctions.setFuncId(imFunctions.getFuncId().longValue());
                    resultFunctions.setFlag(imFunctions.getFlag());
                    listOfResultFunctions.add(resultFunctions);
                }
            }

        }

        logger.info("[FunctionBoImpl.getFunctionByCriteria] end process <<<");

        return listOfResultFunctions;
    }

    public Functions saveAdd(Functions functions) throws GeneralBOException {

        logger.info("[FunctionBoImpl.saveAddFunctions] start process >>>");

        if (functions != null) {
            ImFunctions imFunctions = new ImFunctions();

            try {
                BeanUtils.copyProperties(imFunctions, functions);
            } catch (IllegalAccessException e) {
                logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping addFunctions to imFunctions, please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping addFunctions to imFunctions, please info to your admin..." + e.getMessage());
            }

            if (imFunctions.getFuncLevel().longValue()==0) imFunctions.setFuncLevel(null);

            long funcId = functions.getFuncId();
            boolean isExistFunc;
            try {
                isExistFunc=functionDao.isExistFunction(funcId, "Y");
            } catch (HibernateException e) {
                logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when checking exist function, please info to your admin..." + e.getMessage());
            }

            if (!isExistFunc) {

                imFunctions.setFuncId(funcId);
                imFunctions.setFlag("Y");

                if (functions.isStatusMenuFunc()) {

//                    try {
//                        imFunctions.setMenu(functionDao.getNextMenu());
//                    } catch (HibernateException e) {
//                        logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when getting sequence menu, please info to your admin..." + e.getMessage());
//                    }

                    imFunctions.setMenu(funcId);

                } else {
                    imFunctions.setMenu(null);
                }

                try {
                    functionDao.addAndSave(imFunctions);
                } catch (HibernateException e) {
                    logger.error("[FunctionBoImpl.saveAddFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data function, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[FunctionBoImpl.saveAddFunctions] Error, have duplicate key in function.");
                throw new GeneralBOException("Found problem when saving new data function, please info to your admin...," + "have duplicate key in function.");
            }
        }

        logger.info("[FunctionBoImpl.saveAddFunctions] end process <<<");

        return functions;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(functionDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(Functions functionsNew) throws GeneralBOException {

        logger.info("[FunctionBoImpl.saveEditFunctions] start process >>>");

        if (functionsNew != null) {

            //copy new data to model tabel
            ImFunctions imFunctionsNew = new ImFunctions();
            try {
                BeanUtils.copyProperties(imFunctionsNew, functionsNew);
            } catch (IllegalAccessException e) {
                logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object functionsNew to imFunctionsNew, please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object functionsNew to imFunctionsNew, please info to your admin..." + e.getMessage());
            }

            if (imFunctionsNew.getFuncLevel().longValue()==0) imFunctionsNew.setFuncLevel(null);

            //retrieve last data by id
            long functionId = functionsNew.getFuncId();

            ImFunctions imFunctionsOld = null;
            try {
                imFunctionsOld = functionDao.getById("funcId",functionId, "Y");
            } catch (HibernateException e) {
                logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data function by id, please inform to your admin...," + e.getMessage());
            }

            if (imFunctionsOld != null) {

                // move last data to table history
                ImFunctionsHistory imFunctionsDeactive = new ImFunctionsHistory();
                try {
                    BeanUtils.copyProperties(imFunctionsDeactive, imFunctionsOld);
                } catch (IllegalAccessException e) {
                    logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object functionsOld to imFunctionsBeforeDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object functionsOld to imFunctionsBeforeDeactive, please info to your admin..." + e.getMessage());
                }

                if (imFunctionsDeactive.getFuncLevel().longValue()==0) imFunctionsDeactive.setFuncLevel(null);

                try {
                    functionDao.addAndSaveHistory(imFunctionsDeactive);
                } catch (HibernateException e) {
                    logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data function, please info to your admin..." + e.getMessage());
                }

                //update some of last data become new data
                imFunctionsNew.setFuncId(functionId);
                imFunctionsNew.setCreatedDate(imFunctionsOld.getCreatedDate());
                imFunctionsNew.setCreatedWho(imFunctionsOld.getCreatedWho());
                imFunctionsNew.setFlag(imFunctionsOld.getFlag());

                if (functionsNew.isStatusMenuFunc()) {
                    imFunctionsNew.setMenu(imFunctionsOld.getMenu());
                } else {
                    imFunctionsNew.setMenu(null);
                }

                imFunctionsNew.setImRole(imFunctionsOld.getImRole());

                ImFunctions imFunctionsActive = (ImFunctions) functionDao.getSessionFactory().getCurrentSession().merge(imFunctionsNew);

                try {
                    functionDao.updateAndSave(imFunctionsActive);
                } catch (HibernateException e) {
                    logger.error("[FunctionBoImpl.saveEditFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving updated data function, please inform to your admin...," + e.getMessage());
                }

            } else {
                logger.error("[FunctionBoImpl.saveEdit] Unable to save edit data cause not found function key.");
                throw new GeneralBOException("Found problem when saving edit data function cause not found function key, please info to your admin...");

            }
        }

        logger.info("[FunctionBoImpl.saveEditFunctions] end process <<<");
    }

    public void saveDelete(Functions functions) throws GeneralBOException {

        logger.info("[FunctionBoImpl.saveDeleteFunctions] start process >>>");

        if (functions != null) {

            long functionId = functions.getFuncId();

            ImFunctions imFunctionsOld = null;
            try {
                imFunctionsOld = functionDao.getById("funcId",functionId, "Y");
            } catch (HibernateException e) {
                logger.error("[FunctionBoImpl.saveDeleteFunctions] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data function, please info to your admin..." + e.getMessage());
            }

            if (imFunctionsOld != null) {

                Set listOfImRoles = imFunctionsOld.getImRole();

                if (listOfImRoles.size() == 0) {

                    ImFunctions imFunctionsToDeactive = new ImFunctions();

                    try {
                        BeanUtils.copyProperties(imFunctionsToDeactive, imFunctionsOld);
                    } catch (IllegalAccessException e) {
                        logger.error("[FunctionBoImpl.saveDeleteFunctions] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object functions Will be Delete to imFunctionsBeforeDeactive, please info to your admin..." + e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error("[FunctionBoImpl.saveDeleteFunctions] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object functions  Will be Delete to imFunctionsBeforeDeactive, please info to your admin..." + e.getMessage());
                    }

                    //update data with flag=N
                    imFunctionsToDeactive.setFlag("N");
                    imFunctionsToDeactive.setAction(functions.getAction());
                    imFunctionsToDeactive.setLastUpdate(functions.getLastUpdate());
                    imFunctionsToDeactive.setLastUpdateWho(functions.getLastUpdateWho());
                    if (imFunctionsToDeactive.getFuncLevel().longValue()==0) imFunctionsToDeactive.setFuncLevel(null);
                    if (imFunctionsToDeactive.getMenu().longValue()==0) imFunctionsToDeactive.setMenu(null);

                    ImFunctions imFunctionsDeactive = (ImFunctions) functionDao.getSessionFactory().getCurrentSession().merge(imFunctionsToDeactive);

                    try {
                        functionDao.updateAndSave(imFunctionsDeactive);
                    } catch (HibernateException e) {
                        logger.error("[FunctionBoImpl.saveDeleteFunctions] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data function, please info to your admin..." + e.getMessage());
                    }

                } else {
                    logger.error("[FunctionBoImpl.saveDeleteFunctions] Unable to delete cause have reference data exist in im_func_roles.");
                    throw new GeneralBOException("Found problem when saving delete data function cause have reference data exist in function_roles, please info to your admin...");
                }
            } else {
                logger.error("[FunctionBoImpl.saveDeleteFunctions] Unable to delete cause not found function key.");
                throw new GeneralBOException("Found problem when saving delete data function cause not found function key, please info to your admin...");
            }
        }

        logger.info("[FunctionBoImpl.saveDeleteFunctions] end process <<<");
    }

    public Functions getFunctionById(long functionId, String flag) throws GeneralBOException {

        logger.info("[FunctionBoImpl.getFunctionById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImFunctions imFunctions = null;
        try {
            imFunctions = functionDao.getById("funcId",(Long)functionId,getFlag);
        } catch (HibernateException e) {
            logger.error("[FunctionBoImpl.getFunctionById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving function based on id and flag, please info to your admin..." + e.getMessage());
        }
        Functions resultFunctions = new Functions();
        if (imFunctions != null) {

            try {
                BeanUtils.copyProperties(resultFunctions, imFunctions);
            } catch (IllegalAccessException e) {
                logger.error("[FunctionBoImpl.getFunctionById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imFunctions to result Functions to display , please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[FunctionBoImpl.getFunctionById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imFunctions to result Functions to display , please info to your admin..." + e.getMessage());
            }

            resultFunctions.setFuncId(imFunctions.getFuncId().longValue());
            resultFunctions.setFlag(imFunctions.getFlag());
        }

        logger.info("[FunctionBoImpl.getFunctionById] end process <<<");

        return resultFunctions;
    }


}
