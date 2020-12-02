package com.neurix.authorization.role.bo.impl;

import com.neurix.authorization.role.bo.RoleBo;
import com.neurix.authorization.role.dao.RoleDao;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.ImRolesHistory;
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
public class RoleBoImpl implements RoleBo {

    protected static transient Logger logger = Logger.getLogger(RoleBoImpl.class);
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Roles> getAll() throws GeneralBOException {

        logger.info("[RoleBoImpl.getAll] start process >>>");

        List<Roles> listOfResultRoles = new ArrayList();
        List<ImRoles> listOfRoles = null;
        try {
            listOfRoles = roleDao.getAll();
        } catch (HibernateException e) {
            logger.error("[RoleBoImpl.getAll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting all data, please info to your admin..." + e.getMessage());
        }

        if ( listOfRoles != null) {
            Roles resultRoles;
            for (ImRoles imRoles : listOfRoles) {
                resultRoles = new Roles();

                try {
                    BeanUtils.copyProperties(resultRoles, imRoles);
                } catch (IllegalAccessException e) {
                    logger.error("[RoleBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving imRoles to resultRoles at getting all data, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[RoleBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving imRoles to resultRoles at getting all data, please info to your admin..." + e.getMessage());
                }

                resultRoles.setFlag(imRoles.getFlag());
                listOfResultRoles.add(resultRoles);
            }
        }

        logger.info("[RoleBoImpl.getAll] end process <<<");

        return listOfResultRoles;
    }

    public List<Roles> getByCriteria(Roles searchRoles) throws GeneralBOException {

        logger.info("[RoleBoImpl.getByCriteria] start process >>>");

        List<Roles> listOfResultRoles = new ArrayList();

        if (searchRoles != null) {
            Map hsCriteria = new HashMap();
            if (searchRoles.getStRoleId() != null && !"".equalsIgnoreCase(searchRoles.getStRoleId())) {
                hsCriteria.put("role_id", Long.parseLong(searchRoles.getStRoleId()));
            }

            if (searchRoles.getRoleName() != null && !"".equalsIgnoreCase(searchRoles.getRoleName())) {
                hsCriteria.put("role_name", searchRoles.getRoleName());
            }

            if (searchRoles.getFlag() != null && !"".equalsIgnoreCase(searchRoles.getFlag())) {
                if ("N".equalsIgnoreCase(searchRoles.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchRoles.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImRoles> listOfRoles = null;
            try {
                listOfRoles = roleDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RoleBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfRoles != null) {
                Roles resultRoles;
                for (ImRoles imRoles : listOfRoles) {
                    resultRoles = new Roles();

//                    try {
//                        BeanUtils.copyProperties(resultRoles, imRoles);
//                    } catch (IllegalAccessException e) {
//                        logger.error("[RoleBoImpl.getByCriteria] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when coping saving imRoles to resultRoles at search criteria, please info to your admin..." + e.getMessage());
//                    } catch (InvocationTargetException e) {
//                        logger.error("[RoleBoImpl.getByCriteria] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when coping saving imRoles to resultRoles at search criteria, please info to your admin..." + e.getMessage());
//                    }

                    resultRoles.setStRoleId(imRoles.getRoleId().toString());
                    resultRoles.setFlag(imRoles.getFlag());
                    resultRoles.setRoleName(imRoles.getRoleName());
                    resultRoles.setAction(imRoles.getAction());
                    resultRoles.setCreatedDate(imRoles.getCreatedDate());
                    resultRoles.setCreatedWho(imRoles.getCreatedWho());
                    resultRoles.setLastUpdateWho(imRoles.getLastUpdateWho());
                    resultRoles.setLastUpdate(imRoles.getLastUpdate());

                    listOfResultRoles.add(resultRoles);
                }
            }

        }

        logger.info("[RoleBoImpl.getByCriteria] end process <<<");

        return listOfResultRoles;
    }

    public Roles saveAdd(Roles roles) throws GeneralBOException {

        logger.info("[RoleBoImpl.saveAdd] start process >>>");

        if (roles != null) {
            ImRoles imRoles = new ImRoles();

            try {
                BeanUtils.copyProperties(imRoles, roles);
            } catch (IllegalAccessException e) {
                logger.error("[RoleBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping addRoles to imRoles, please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[RoleBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping addRoles to imRoles, please info to your admin..." + e.getMessage());
            }

            imRoles.setFlag("Y");

            try {
                roleDao.addAndSave(imRoles);
            } catch (HibernateException e) {
                logger.error("[RoleBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data role, please info to your admin..." + e.getMessage());
            }

        }

        logger.info("[RoleBoImpl.saveAdd] end process <<<");

        return roles;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(roleDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(Roles rolesNew) throws GeneralBOException {

        logger.info("[RoleBoImpl.saveEdit] start process >>>");

        if (rolesNew != null) {

            //copy new data to model tabel
            ImRoles imRolesNew = new ImRoles();
            try {
                BeanUtils.copyProperties(imRolesNew, rolesNew);
            } catch (IllegalAccessException e) {
                logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object rolesNew to imRolesNew, please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping data object rolesNew to imRolesNew, please info to your admin..." + e.getMessage());
            }

            //retrieve last data by id
            long roleId = rolesNew.getRoleId();

            ImRoles imRolesOld = null;
            try {
                imRolesOld = roleDao.getById("roleId",roleId, "Y");
            } catch (HibernateException e) {
                logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data role by id, please inform to your admin...," + e.getMessage());
            }

            if (imRolesOld != null) {

                // move last data to table history
                ImRolesHistory imRolesDeactive = new ImRolesHistory();
                try {
                    BeanUtils.copyProperties(imRolesDeactive, imRolesOld);
                } catch (IllegalAccessException e) {
                    logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object rolesOld to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object rolesOld to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                }

                try {
                    roleDao.addAndSaveHistory(imRolesDeactive);
                } catch (HibernateException e) {
                    logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data role, please info to your admin..." + e.getMessage());
                }

                //update some of last data become new data
                imRolesNew.setRoleId(roleId);
                imRolesNew.setCreatedDate(imRolesOld.getCreatedDate());
                imRolesNew.setCreatedWho(imRolesOld.getCreatedWho());
                imRolesNew.setFlag(imRolesOld.getFlag());

                ImRoles imRolesActive = (ImRoles) roleDao.getSessionFactory().getCurrentSession().merge(imRolesNew);

                try {
                    roleDao.updateAndSave(imRolesActive);
                } catch (HibernateException e) {
                    logger.error("[RoleBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving updated data role, please inform to your admin...," + e.getMessage());
                }

            } else {
                logger.error("[RoleBoImpl.saveEdit] Unable to save edit cause no found role key.");
                throw new GeneralBOException("Found problem when saving edit data role cause no found role key., please info to your admin...");
            }
        }

        logger.info("[RoleBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(Roles roles) throws GeneralBOException {

        logger.info("[RoleBoImpl.saveDelete] start process >>>");

        if (roles != null) {

            long roleId = roles.getRoleId();

            ImRoles imRolesOld = null;
            try {
                imRolesOld = roleDao.getById("roleId",roleId, "Y");
            } catch (HibernateException e) {
                logger.error("[RoleBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data role, please info to your admin..." + e.getMessage());
            }

            if (imRolesOld != null) {

                Set listOfImUsers = imRolesOld.getImUser();
                Set listOfImFunctions = imRolesOld.getImFunction();

                if (listOfImUsers.size() == 0 && listOfImFunctions.size() == 0) {

                    ImRoles imRolesToDeactive = new ImRoles();

                    try {
                        BeanUtils.copyProperties(imRolesToDeactive, imRolesOld);
                    } catch (IllegalAccessException e) {
                        logger.error("[RoleBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object roles Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error("[RoleBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when coping data object roles  Will be Delete to imRolesBeforeDeactive, please info to your admin..." + e.getMessage());
                    }

                    //update data with flag=N
                    imRolesToDeactive.setFlag("N");
                    imRolesToDeactive.setAction(roles.getAction());
                    imRolesToDeactive.setLastUpdate(roles.getLastUpdate());
                    imRolesToDeactive.setLastUpdateWho(roles.getLastUpdateWho());

                    ImRoles imRolesDeactive = (ImRoles) roleDao.getSessionFactory().getCurrentSession().merge(imRolesToDeactive);

                    try {
                        roleDao.updateAndSave(imRolesDeactive);
                    } catch (HibernateException e) {
                        logger.error("[RoleBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data role, please info to your admin..." + e.getMessage());
                    }

                } else {
                    logger.error("[RoleBoImpl.saveDelete] Unable to delete cause have reference data exist in user and function table.");
                    throw new GeneralBOException("Found problem when saving delete data role cause have reference data exist in user and function table, please info to your admin...");
                }
            } else {
                logger.error("[RoleBoImpl.saveDelete] Unable to delete cause no found role key.");
                throw new GeneralBOException("Found problem when saving delete data role cause no found role key., please info to your admin...");
            }
        }

        logger.info("[RoleBoImpl.saveDelete] end process <<<");
    }

    public Roles getRoleById(long roleId, String flag) throws GeneralBOException {

        logger.info("[RoleBoImpl.getRoleById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImRoles imRoles = null;
        try {
            imRoles = roleDao.getById("roleId",(Long)roleId,getFlag);
        } catch (HibernateException e) {
            logger.error("[RoleBoImpl.getRoleById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving role based on id and flag, please info to your admin..." + e.getMessage());
        }

        Roles resultRoles = new Roles();
        if (imRoles != null) {

            try {
                BeanUtils.copyProperties(resultRoles, imRoles);
            } catch (IllegalAccessException e) {
                logger.error("[RoleBoImpl.getRoleById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imRoles to result roles to display , please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[RoleBoImpl.getRoleById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search imRoles to result roles to display , please info to your admin..." + e.getMessage());
            }

            resultRoles.setTipePelayanan(imRoles.getTipePelayanan());
            resultRoles.setStRoleId(imRoles.getRoleId().toString());
            resultRoles.setFlag(imRoles.getFlag());
        }

        logger.info("[RoleBoImpl.getRoleById] end process <<<");

        return resultRoles;
    }

    public List<Roles> getComboRoleWithCriteria(String query) throws GeneralBOException {
        logger.info("[RoleBoImpl.getComboRoleWithCriteria] start process >>>");

        List<Roles> listComboRoles = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImRoles> listRoles = null;
        try {
            listRoles = roleDao.getListRole(criteria);
        } catch (HibernateException e) {
            logger.error("[RoleBoImpl.getComboRoleWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list role with criteria, please info to your admin..." + e.getMessage());
        }

        if (listRoles != null) {
            for (ImRoles imRoles : listRoles) {
                Roles itemComboRoles = new Roles();
                itemComboRoles.setStRoleId(imRoles.getRoleId().toString());
                itemComboRoles.setRoleName(imRoles.getRoleName());
                listComboRoles.add(itemComboRoles);
            }
        }
        logger.info("[RoleBoImpl.getComboRoleWithCriteria] end process <<<");
        return listComboRoles;
    }
}
