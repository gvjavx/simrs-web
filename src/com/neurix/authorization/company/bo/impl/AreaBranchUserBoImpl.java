package com.neurix.authorization.company.bo.impl;

import com.neurix.authorization.company.bo.AreaBranchUserBo;
import com.neurix.authorization.company.dao.AreasBranchesUsersDao;
import com.neurix.authorization.company.model.AreaBranchUser;
import com.neurix.authorization.company.model.ImAreasBranchesUsers;
import com.neurix.authorization.company.model.ImAreasBranchesUsersPK;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.displaytag.DisplayObject;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class AreaBranchUserBoImpl implements AreaBranchUserBo {

    protected static transient Logger logger = Logger.getLogger(AreaBranchUserBoImpl.class);
    private AreasBranchesUsersDao areasBranchesUsersDao;
    private UserDao userDao;

    public void setAreasBranchesUsersDao(AreasBranchesUsersDao areasBranchesUsersDao) {
        this.areasBranchesUsersDao = areasBranchesUsersDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getDefaultUser() throws GeneralBOException {
        logger.info("[AreaBranchUserBoImpl.getDefaultUser] start process >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        List<ImUsers> listOfResultImUser = null;
        try {
            listOfResultImUser = userDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[AreaBranchUserBoImpl.getDefaultUser] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        List<User> listDefaultUser=new ArrayList<User>();
        for (ImUsers imUsers : listOfResultImUser) {

            User user = new User();
            user.setUserId(imUsers.getPrimaryKey().getId());
            user.setUsername(imUsers.getUserName());
            user.setEmail(imUsers.getEmail());
            user.setPositionId(imUsers.getImPosition().getPositionId().toString());
            user.setPositionName(imUsers.getImPosition().getPositionName());
            user.setPhotoUserUrl(imUsers.getPhotoUrl());

            StringBuffer imagePreview = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
            imagePreview.append(ServletActionContext.getRequest().getContextPath());
            imagePreview.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);

            if (imUsers.getPhotoUrl()==null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                imagePreview.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
            } else {
                imagePreview.append(imUsers.getPhotoUrl());
            }

            imagePreview.append("\" border=\"none\" >");

            user.setPreviewPhoto(imagePreview.toString());

            DisplayObject displayObject = new DisplayObject();
            displayObject.setCheckBox(user.getUserId(), "userIdChecked", false, false, null);

            user.setDisplayObjectCheck(displayObject);

            listDefaultUser.add(user);
        }

        logger.info("[AreaBranchUserBoImpl.getDefaultUser] end process >>>");

        return listDefaultUser;
    }

    public List<AreaBranchUser> getByCriteria(AreaBranchUser searchAreaBranchUser) throws GeneralBOException {

        logger.info("[AreaBranchUserBoImpl.getByCriteria] start process >>>");

        List<AreaBranchUser> listOfResultAreaBranchUser = new ArrayList();

        if (searchAreaBranchUser != null) {
            Map hsCriteria = new HashMap();
            if (searchAreaBranchUser.getAreaId() != null && !"".equalsIgnoreCase(searchAreaBranchUser.getAreaId())) {
                hsCriteria.put("area_id", searchAreaBranchUser.getAreaId());
            }

            if (searchAreaBranchUser.getBranchId() != null && !"".equalsIgnoreCase(searchAreaBranchUser.getBranchId())) {
                hsCriteria.put("branch_id", searchAreaBranchUser.getBranchId());
            }

            if (searchAreaBranchUser.getUserId() != null && !"".equalsIgnoreCase(searchAreaBranchUser.getUserId())) {
                hsCriteria.put("user_id", searchAreaBranchUser.getUserId());
            }

            if (searchAreaBranchUser.getFlag() != null && !"".equalsIgnoreCase(searchAreaBranchUser.getFlag())) {
                if ("N".equalsIgnoreCase(searchAreaBranchUser.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchAreaBranchUser.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImAreasBranchesUsers> listOfResult = null;
            try {
                listOfResult = areasBranchesUsersDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBranchUserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            hsCriteria = new HashMap();
            hsCriteria.put("flag", "Y");
            List<ImUsers> listOfResultImUser = null;
            try {
                listOfResultImUser = userDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBranchUserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            List<User> listDefaultUser=new ArrayList<User>();
            for (ImUsers imUsers : listOfResultImUser) {
                User user = new User();
                user.setUserId(imUsers.getPrimaryKey().getId());
                user.setUsername(imUsers.getUserName());
                user.setEmail(imUsers.getEmail());
                user.setPositionId(imUsers.getImPosition().getPositionId().toString());
                user.setPositionName(imUsers.getImPosition().getPositionName());
                user.setPhotoUserUrl(imUsers.getPhotoUrl());

                StringBuffer imagePreview = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                imagePreview.append(ServletActionContext.getRequest().getContextPath());
                imagePreview.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                if (imUsers.getPhotoUrl()==null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                    imagePreview.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                } else {
                    imagePreview.append(imUsers.getPhotoUrl());
                }
                imagePreview.append("\" border=\"none\" >");

                user.setPreviewPhoto(imagePreview.toString());

                DisplayObject displayObject = new DisplayObject();
                displayObject.setCheckBox(user.getUserId(), "userIdChecked", false, false, null);

                user.setDisplayObjectCheck(displayObject);

                listDefaultUser.add(user);
            }

            if (listOfResult != null) {
                AreaBranchUser resultAreaBranchUser = null;
                List<User> listCheckUser = null;
                for (ImAreasBranchesUsers imAreasBranchesUsers : listOfResult) {

                    String areaId = imAreasBranchesUsers.getImArea().getPrimaryKey().getId();
                    String areaName = imAreasBranchesUsers.getImArea().getAreaName();
                    String branchId = imAreasBranchesUsers.getImBranch().getPrimaryKey().getId();
                    String branchName = imAreasBranchesUsers.getImBranch().getBranchName();

                    if (resultAreaBranchUser!=null
                            && areaId.equals(resultAreaBranchUser.getAreaId())
                            && branchId.equals(resultAreaBranchUser.getBranchId())) {

                        storeListCheckUser(listCheckUser,imAreasBranchesUsers);

                    } else {
                        if (resultAreaBranchUser!=null) {
                            resultAreaBranchUser.setListOfUser(listCheckUser);
                            listOfResultAreaBranchUser.add(resultAreaBranchUser);
                        }

                        resultAreaBranchUser = new AreaBranchUser();
                        resultAreaBranchUser.setAreaId(areaId);
                        resultAreaBranchUser.setAreaName(areaName);
                        resultAreaBranchUser.setBranchId(branchId);
                        resultAreaBranchUser.setBranchName(branchName);
                        resultAreaBranchUser.setFlag(imAreasBranchesUsers.getFlag());

                        listCheckUser = new ArrayList<User>();
                        for (User itemUser : listDefaultUser) {
                            User userClone = (User) SerializationUtils.clone(itemUser);
                            listCheckUser.add(userClone);
                        }

                        storeListCheckUser(listCheckUser,imAreasBranchesUsers);
                    }

                }

                if (!listOfResult.isEmpty()) { //for handling the last object
                    if (resultAreaBranchUser!=null) {
                        resultAreaBranchUser.setListOfUser(listCheckUser);
                        listOfResultAreaBranchUser.add(resultAreaBranchUser);
                    }
                }

            }

        }

        logger.info("[AreaBranchUserBoImpl.getByCriteria] end process <<<");

        return listOfResultAreaBranchUser;
    }

    private void storeListCheckUser(List<User> listCheckUser, ImAreasBranchesUsers imAreasBranchesUsers) {

        String userId = imAreasBranchesUsers.getImUser().getPrimaryKey().getId();
        for (User user : listCheckUser) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                user.getDisplayObjectCheck().setCheckBox(user.getUserId(), "userIdChecked", true, false, null);
                break;
            }
        }

    }

    public AreaBranchUser saveAdd(AreaBranchUser areaBranchUser) throws GeneralBOException {

        logger.info("[AreaBranchUserBoImpl.saveAdd] start process >>>");

        if (areaBranchUser != null) {

            String areaId = areaBranchUser.getAreaId();
            String branchId = areaBranchUser.getBranchId();

            Map hsCriteria = new HashMap();
            hsCriteria.put("area_id", areaId);
            hsCriteria.put("branch_id", branchId);
            hsCriteria.put("flag", "Y");

            List<ImAreasBranchesUsers> listOfResult = new ArrayList<ImAreasBranchesUsers>();

            try {
                listOfResult = areasBranchesUsersDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBranchUserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (listOfResult != null && listOfResult.isEmpty()) {

                for (User user : areaBranchUser.getListOfUser()) {
                    ImAreasBranchesUsers imAreasBranchesUsers = new ImAreasBranchesUsers();

                    ImAreasBranchesUsersPK imAreasBranchesUsersPK = new ImAreasBranchesUsersPK();
                    imAreasBranchesUsersPK.setAreaId(areaBranchUser.getAreaId());
                    imAreasBranchesUsersPK.setBranchId(areaBranchUser.getBranchId());
                    imAreasBranchesUsersPK.setUserId(user.getUserId());

                    imAreasBranchesUsers.setPrimaryKey(imAreasBranchesUsersPK);
                    imAreasBranchesUsers.setCreatedDate(areaBranchUser.getCreatedDate());
                    imAreasBranchesUsers.setCreatedWho(areaBranchUser.getCreatedWho());
                    imAreasBranchesUsers.setLastUpdate(areaBranchUser.getLastUpdate());
                    imAreasBranchesUsers.setLastUpdateWho(areaBranchUser.getLastUpdateWho());
                    imAreasBranchesUsers.setFlag("Y");

                    try {
                        areasBranchesUsersDao.addAndSave(imAreasBranchesUsers);
                    } catch (HibernateException e) {
                        logger.error("[AreaBranchUserBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data area branch user, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }

        logger.info("[AreaBranchUserBoImpl.saveAdd] end process <<<");

        return areaBranchUser;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(areasBranchesUsersDao, message, moduleMethod);

        return result;
    }

    public void saveEdit(AreaBranchUser areaBranchUser) throws GeneralBOException {

        logger.info("[AreaBranchUserBoImpl.saveEdit] start process >>>");

        if (areaBranchUser != null) {

            String areaId = areaBranchUser.getAreaId();
            String branchId = areaBranchUser.getBranchId();

            Map hsCriteria = new HashMap();
            hsCriteria.put("area_id", areaId);
            hsCriteria.put("branch_id", branchId);
            hsCriteria.put("flag", "Y");

            List<ImAreasBranchesUsers> listOfResult = null;

            try {
                listOfResult = areasBranchesUsersDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBranchUserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfResult != null && !listOfResult.isEmpty()) {

                //set flag N if exist data from tabel not in list updated area-branch-user
                for (ImAreasBranchesUsers imAreasBranchesUsers : listOfResult) {
                    boolean found = false;
                    for (User user : areaBranchUser.getListOfUser()) {
                        if (user.getUserId().equalsIgnoreCase(imAreasBranchesUsers.getImUser().getPrimaryKey().getId())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        imAreasBranchesUsers.setFlag("N");
                        imAreasBranchesUsers.setLastUpdate(areaBranchUser.getLastUpdate());
                        imAreasBranchesUsers.setLastUpdateWho(areaBranchUser.getLastUpdateWho());

                        try {
                            areasBranchesUsersDao.updateAndSave(imAreasBranchesUsers);
                        } catch (HibernateException e) {
                            logger.error("[AreaBranchUserBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving updated data area branch user, please inform to your admin...," + e.getMessage());
                        }

                    }
                }

                //add new if not in exist data from tabel but in list updated area-branch-user
                for (User user : areaBranchUser.getListOfUser()) {
                    boolean found = false;
                    for (ImAreasBranchesUsers imAreasBranchesUsers : listOfResult) {
                        if (user.getUserId().equalsIgnoreCase(imAreasBranchesUsers.getImUser().getPrimaryKey().getId())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        ImAreasBranchesUsers imAreasBranchesUsers = new ImAreasBranchesUsers();
                        ImAreasBranchesUsersPK imAreasBranchesUsersPK = new ImAreasBranchesUsersPK();
                        imAreasBranchesUsersPK.setAreaId(areaId);
                        imAreasBranchesUsersPK.setBranchId(branchId);
                        imAreasBranchesUsersPK.setUserId(user.getUserId());

                        imAreasBranchesUsers.setPrimaryKey(imAreasBranchesUsersPK);
                        imAreasBranchesUsers.setCreatedDate(areaBranchUser.getLastUpdate());
                        imAreasBranchesUsers.setCreatedWho(areaBranchUser.getLastUpdateWho());
                        imAreasBranchesUsers.setLastUpdate(areaBranchUser.getLastUpdate());
                        imAreasBranchesUsers.setLastUpdateWho(areaBranchUser.getLastUpdateWho());
                        imAreasBranchesUsers.setFlag("Y");

                        try {
                            areasBranchesUsersDao.addAndSave(imAreasBranchesUsers);
                        } catch (HibernateException e) {
                            logger.error("[AreaBranchUserBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data area branch user, please info to your admin..." + e.getMessage());
                        }
                    }
                }
            }

        } else {
            logger.error("[AreaBranchUserBoImpl.saveEdit] Unable to save edit cause no have area and branch id.");
            throw new GeneralBOException("Found problem when saving edit data cause no have area and branch id, please info to your admin...");
        }

        logger.info("[AreaBranchUserBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(AreaBranchUser areaBranchUser) throws GeneralBOException {

        logger.info("[AreaBranchUserBoImpl.saveDelete] start process >>>");

        if (areaBranchUser != null) {

            String areaId = areaBranchUser.getAreaId();
            String branchId = areaBranchUser.getBranchId();

            Map hsCriteria = new HashMap();
            hsCriteria.put("area_id", areaId);
            hsCriteria.put("branch_id", branchId);
            hsCriteria.put("flag", "Y");

            List<ImAreasBranchesUsers> listOfResult = null;

            try {
                listOfResult = areasBranchesUsersDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AreaBranchUserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }


            if (listOfResult!=null && !listOfResult.isEmpty()) {

                for (ImAreasBranchesUsers imAreasBranchesUsers : listOfResult) {

                    //update data with flag=N
                    imAreasBranchesUsers.setFlag("N");
                    imAreasBranchesUsers.setLastUpdate(areaBranchUser.getLastUpdate());
                    imAreasBranchesUsers.setLastUpdateWho(areaBranchUser.getLastUpdateWho());

                    try {
                        areasBranchesUsersDao.updateAndSave(imAreasBranchesUsers);
                    } catch (HibernateException e) {
                        logger.error("[AreaBranchUserBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving delete data area branch user, please info to your admin..." + e.getMessage());
                    }

                }

            } else {
                logger.error("[AreaBranchUserBoImpl.saveDelete] Unable to delete cause no have reference data exist in area branch user table.");
                throw new GeneralBOException("Found problem when saving delete data role cause no have reference data exist in user and function table, please info to your admin...");
            }
        } else {
            logger.error("[AreaBranchUserBoImpl.saveDelete] Unable to delete cause no have area and branch id.");
            throw new GeneralBOException("Found problem when saving delete data role cause no have area and branch id, please info to your admin...");
        }

        logger.info("[AreaBranchUserBoImpl.saveDelete] end process <<<");
    }



}
