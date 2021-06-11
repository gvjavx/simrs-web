
package com.neurix.authorization.user.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Area;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.authorization.role.bo.RoleBo;
import com.neurix.authorization.role.model.Roles;
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 02/10/2014.
 */
public class UserAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(UserAction.class);

    private UserBo userBoProxy;
    private RoleBo roleBoProxy;
    private AreaBo areaBoProxy;
    private BranchBo branchBoProxy;
    private PositionBo positionBoProxy;
    private MasterBo masterBoProxy;

    private User users;
    private List<Roles> listOfComboRoles = new ArrayList<Roles>();
    private List<Area> listOfComboAreas = new ArrayList<Area>();
    private List<Branch> listOfComboBranches = new ArrayList<Branch>();
    private List<Position> listOfComboPositions = new ArrayList<Position>();
    private List<Master> listOfComboMaster = new ArrayList<Master>();
    private File fileUpload;
    private String userId;

    public void setMasterBoProxy(MasterBo masterBoProxy) {
        this.masterBoProxy = masterBoProxy;
    }

    public void setAreaBoProxy(AreaBo areaBoProxy) {
        this.areaBoProxy = areaBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public List<Area> getListOfComboAreas() {
        return listOfComboAreas;
    }

    public void setListOfComboAreas(List<Area> listOfComboAreas) {
        this.listOfComboAreas = listOfComboAreas;
    }

    public List<Branch> getListOfComboBranches() {
        return listOfComboBranches;
    }

    public void setListOfComboBranches(List<Branch> listOfComboBranches) {
        this.listOfComboBranches = listOfComboBranches;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public void setRoleBoProxy(RoleBo roleBoProxy) {
        this.roleBoProxy = roleBoProxy;
    }

    public void setPositionBoProxy(PositionBo positionBoProxy) {
        this.positionBoProxy = positionBoProxy;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public List<Roles> getListOfComboRoles() {
        return listOfComboRoles;
    }

    public void setListOfComboRoles(List<Roles> listOfComboRoles) {
        this.listOfComboRoles = listOfComboRoles;
    }

    public List<Position> getListOfComboPositions() {
        return listOfComboPositions;
    }

    public void setListOfComboPositions(List<Position> listOfComboPositions) {
        this.listOfComboPositions = listOfComboPositions;
    }

    public List<Master> getListOfComboMaster() {
        return listOfComboMaster;
    }

    public void setListOfComboMaster(List<Master> listOfComboMaster) {
        this.listOfComboMaster = listOfComboMaster;
    }

    public String initComboRole() {

        Roles roles = new Roles();
        roles.setFlag("Y");

        List<Roles> listOfRoles = new ArrayList<Roles>();
        try {
            listOfRoles = roleBoProxy.getByCriteria(roles);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = roleBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboRole] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboRole] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboRoles.clear();
        listOfComboRoles.addAll(listOfRoles);

        return "init_combo_role";
    }

    public String initComboMaster() {

        Master master = new Master();
        master.setFlag("Y");

        List<Master> listOfMaster = new ArrayList<Master>();
        try {
            listOfMaster = masterBoProxy.getByCriteria(master);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = masterBoProxy.saveErrorMessage(e.getMessage(), "RoleBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboRole] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboRole] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboMaster.addAll(listOfMaster);

        return "init_combo_nomaster";
    }

    public String initComboArea() {

        Area area = new Area();
        area.setFlag("Y");

        List<Area> listOfAreas = new ArrayList<Area>();
        try {
            listOfAreas = areaBoProxy.getByCriteria(area);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = areaBoProxy.saveErrorMessage(e.getMessage(), "AreaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboArea] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboArea] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboAreas.clear();
        listOfComboAreas.addAll(listOfAreas);

        return "init_combo_area";
    }

    public String initComboBranch() {

        Branch braches = new Branch();
        braches.setFlag("Y");

        List<Branch> listOfBranches = new ArrayList<Branch>();
        try {
            listOfBranches = branchBoProxy.getByCriteria(braches);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboBranches.addAll(listOfBranches);

        return "init_combo_branch";
    }

    public String initComboBranchSelainKp() {

        Branch braches = new Branch();
        braches.setFlag("Y");

        List<Branch> listOfBranches = new ArrayList<Branch>();
        List<Branch> listOfResult = new ArrayList<Branch>();
        try {
            listOfBranches = branchBoProxy.getByCriteria(braches);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranchSelainKp] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranchSelainKp] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        for (Branch branch : listOfBranches) {
            if (!CommonConstant.BRANCH_KP.equalsIgnoreCase(branch.getBranchId())) {
                listOfResult.add(branch);
            }
        }

        listOfComboBranches.addAll(listOfResult);

        return "init_combo_branch";
    }

    public String initComboPosition() {

        Position position = new Position();
        position.setFlag("Y");

        List<Position> listOfPosition = new ArrayList<Position>();
        try {
            listOfPosition = positionBoProxy.getByCriteria(position);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPositions.addAll(listOfPosition);

        return "init_combo_position";
    }

    public String initComboPositionBod() {

//        Position position = new Position();
//        position.setKelompokId("KL44");
//        position.setFlag("Y");

        List<Position> listOfPosition = new ArrayList<Position>();
        try {
//            listOfPosition = positionBoProxy.getByCriteria(position);
            listOfPosition = positionBoProxy.getComboBodBoc();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPositions.addAll(listOfPosition);

        return "init_combo_position";
    }

    @Override
    public String edit() {
        logger.info("[UserAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        User editUser = new User();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<User> listOfResult = (List<User>) session.getAttribute("listOfResult");

            for (User itemUser : listOfResult) {
                if (itemUser.getUserId().equalsIgnoreCase(itemId)) {
                    editUser.setUserId(itemUser.getUserId());
                    editUser.setUsername(itemUser.getUsername());
                    editUser.setPassword("");
                    editUser.setConfirmPassword("");
                    editUser.setEmail(itemUser.getEmail());
                    editUser.setPositionId(itemUser.getPositionId());
                    editUser.setRoleId(itemUser.getRoleId());
                    editUser.setBranchId(itemUser.getBranchId());
                    editUser.setAreaId(itemUser.getAreaId());
                    editUser.setDivisiId(itemUser.getDivisiId());
                    editUser.setIdPelayanan(itemUser.getIdPelayanan());
                    editUser.setIdRuangan(itemUser.getIdRuangan());
                    editUser.setIdVendor(itemUser.getIdVendor());
                    setUserId(itemUser.getUserId());
                    break;
                }
            }

        }

        setUsers(editUser);
        setAddOrEdit(true);
        setAdd(true);

        logger.info("[UserAction.edit] end process <<<");

        return "view_detail";
    }

    @Override
    public String add() {
        logger.info("[UserAction.add] start process >>>");

        User addUsers = new User();
        setUsers(addUsers);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[UserAction.add] end process <<<");

        return "view_detail_add";
    }

    @Override
    public String delete() {
        logger.info("[UserAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        User deleteUsers = new User();

        if (itemFlag != null && "Y".equalsIgnoreCase(itemFlag)) {

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<User> listOfResult = (List<User>) session.getAttribute("listOfResult");

            for (User itemUser : listOfResult) {
                if (itemUser.getUserId().equalsIgnoreCase(itemId)) {
                    deleteUsers.setUserId(itemUser.getUserId());
                    deleteUsers.setUsername(itemUser.getUsername());
                    deleteUsers.setPassword(itemUser.getPassword());
                    deleteUsers.setConfirmPassword(itemUser.getPassword());
                    deleteUsers.setEmail(itemUser.getEmail());
                    deleteUsers.setPositionId(itemUser.getPositionId());
                    deleteUsers.setRoleId(itemUser.getRoleId());
                    deleteUsers.setBranchId(itemUser.getBranchId());
                    deleteUsers.setAreaId(itemUser.getAreaId());
                    setUserId(itemUser.getUserId());
                    break;
                }
            }

        }

        setUsers(deleteUsers);
        setDelete(true);

        logger.info("[UserAction.delete] end process <<<");

        return "view_detail";
    }

    @Override
    public String view() {
        logger.info("[UserAction.view] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<User> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (User userDetailsLogin : listOfResult) {
                    if (id.equalsIgnoreCase(userDetailsLogin.getUserId())) {
                        setUsers(userDetailsLogin);
                        break;
                    }
                }

            } else {
                setUsers(new User());
            }
        } else {
            setUsers(new User());
        }

        logger.info("[UserAction.view] end process <<<");

        return "view_detail";
    }

    @Override
    public String save() {
        logger.info("[UserAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) { //edit

                User editUser = getUsers();
                String rawPassword = editUser.getPassword();
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                String hashedPassword = passwordEncoder.encode(rawPassword);

                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
                String hashedPassword = passwordEncoder.encodePassword(rawPassword, null);

                editUser.setPassword(hashedPassword);

                String itemId = editUser.getUserId() != null ? editUser.getUserId() : getUserId();
                if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                    if (this.fileUpload != null) {

                        //note : for linux directory
                        //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                        //note : for windows directory
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                        String fileName = itemId + ".jpg";
                        File fileToCreate = new File(filePath, fileName);

                        //create file to save to folder '/upload'
                        byte[] contentFile = null;
                        try {
                            FileUtils.copyFile(this.fileUpload, fileToCreate);
                            contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                        } catch (IOException e) {
                            Long logId = null;
                            try {
                                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.save] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                            return ERROR;
                        }

                        if (contentFile != null) {
                            editUser.setContentFile(contentFile);
                            editUser.setPhotoUserUrl(fileName);
                        }
                    }

                    try {

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        editUser.setUserId(getUserId());
                        editUser.setLastUpdate(updateTime);
                        editUser.setLastUpdateWho(userLogin);
                        editUser.setAction("U");

                        userBoProxy.saveEdit(editUser);
                        users.setSuccessMessage("Data Successfully Updated");

                    } catch (UsernameNotFoundException e) {
                        logger.error("[UserAction.save] Error when editing item user,", e);
                        addActionError("Error, " + e.getMessage());
                        users.setErrorMessage("Error, " + e.getMessage());
                        users.setSuccessMessage("");
                        return ERROR;
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.save");
                        } catch (GeneralBOException e1) {
                            logger.error("[UserAction.save] Error when saving error,", e1);
                        }
                        logger.error("[UserAction.save] Error when editing item user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.");
                        users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.");
                        users.setSuccessMessage("");
                        return ERROR;
                    }
                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                return "success_save_edit";


            } else { //add

                User addUser = getUsers();
                String rawPassword = addUser.getPassword();
//                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                String hashedPassword = passwordEncoder.encode(rawPassword);

                ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
                String hashedPassword = passwordEncoder.encodePassword(rawPassword, null);

                if (rawPassword.equalsIgnoreCase(addUser.getConfirmPassword())) {

                    addUser.setPassword(hashedPassword);

                    String itemId = addUser.getUserId();
                    if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                        if (this.fileUpload != null) {

                            //note : for linux directory
                            //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                            //note : for windows directory
                            String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                            String fileName = itemId + ".jpg";
                            File fileToCreate = new File(filePath, fileName);

                            //create file to save to folder '/upload'
                            byte[] contentFile = null;
                            try {
                                FileUtils.copyFile(this.fileUpload, fileToCreate);
                                contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                            } catch (IOException e) {
                                Long logId = null;
                                try {
                                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.save");
                                } catch (GeneralBOException e1) {
                                    logger.error("[UserAction.save] Error when saving error,", e1);
                                }
                                logger.error("[UserAction.save] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                                users.setSuccessMessage("");
                                users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                                return ERROR;
                            }

                            if (contentFile != null) {
                                addUser.setContentFile(contentFile);
                                addUser.setPhotoUserUrl(fileName);
                            }
                        }

                        try {

                            String userLogin = CommonUtil.userLogin();
                            Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                            addUser.setCreatedDate(createTime);
                            addUser.setCreatedWho(userLogin);
                            addUser.setLastUpdate(createTime);
                            addUser.setLastUpdateWho(userLogin);
                            addUser.setAction("C");

                            userBoProxy.saveAdd(addUser);
                            users.setSuccessMessage("Successfully Entry New Data");
                        } catch (UsernameNotFoundException e) {
                            logger.error("[UserAction.save] Error when adding item user,", e);
                            addActionError("Error, " + e.getMessage());
                            users.setSuccessMessage("");
                            users.setErrorMessage("Error, " + e.getMessage());
                            return ERROR;
                        } catch (GeneralBOException e) {
                            Long logId = null;
                            try {
                                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.saveAdd");
                            } catch (GeneralBOException e1) {
                                logger.error("[UserAction.save] Error when saving error,", e1);
                            }
                            logger.error("[UserAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.");
                            users.setSuccessMessage("");
                            users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.");
                            return ERROR;
                        }
                    }

                } else {

                    addActionError("Please checked your password not same with confirm password.");
                    users.setSuccessMessage("");
                    users.setErrorMessage("Please checked your password not same with confirm password.");
                    return ERROR;

                }

                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listOfResult");

                return "success_save_add";
            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                User deleteUsers = getUsers() != null ? getUsers() : new User();

                if (getUsers() == null) deleteUsers.setUserId(getUserId());
                deleteUsers.setLastUpdate(updateTime);
                deleteUsers.setLastUpdateWho(userLogin);
                deleteUsers.setAction("D");

                userBoProxy.saveDelete(deleteUsers);
                addActionMessage("Save Delete is success.");
                users.setSuccessMessage("Save Delete is success.");
            } catch (UsernameNotFoundException e) {
                logger.error("[UserAction.save] Error when deleting item,", e);
                addActionError("Error, " + e.getMessage());
                users.setErrorMessage("Error, " + e.getMessage());
                users.setSuccessMessage("");
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[UserAction.save] Error when saving error,", e1);
                }
                logger.error("[UserAction.save] Error when deleting item ," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.");
                users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.");
                users.setSuccessMessage("");
                return ERROR;
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listOfResult");

            return "success_save_delete";

        }

        logger.info("[UserAction.save] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        User initUsers = new User();
        setUsers(initUsers);
        setDelete(false);
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
        logger.info("[UserAction.search] start process >>>");

        User searchUsers = getUsers();
        List<User> listOfSearchUsers = new ArrayList();
        try {
            listOfSearchUsers = userBoProxy.getListUserByQuery(searchUsers);
            users.setSuccessMessage("Data Search Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.search] Error when saving error,", e1);
            }
            logger.error("[UserAction.search] Error when searching user by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            users.setSuccessMessage("");
            return "failure";
        }

        if (listOfSearchUsers.size() == 0) {
            users.setErrorMessage("Cannot Found Data");
            users.setSuccessMessage("");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchUsers);

        logger.info("[UserAction.search] end process <<<");

        return SUCCESS;
    }

    public List<User> searchUserNotif(String branch, String Position, String Divisi) {
        logger.info("[UserAction.search] start process >>>");

        User searchUsers = new User();
        searchUsers.setPositionId(Position);
        searchUsers.setBranchId(branch);
        searchUsers.setDivisiId(Divisi);

        List<User> listOfSearchUsers = new ArrayList();
        try {
            listOfSearchUsers = userBoProxy.getByCriteria(searchUsers);
            users.setSuccessMessage("Data Search Success");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.search] Error when saving error,", e1);
            }
            logger.error("[UserAction.search] Error when searching user by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            users.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            users.setSuccessMessage("");
        }

        if (listOfSearchUsers.size() == 0) {
            users.setErrorMessage("Cannot Found Data");
            users.setSuccessMessage("");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchUsers);

        logger.info("[UserAction.search] end process <<<");

        return listOfSearchUsers;
    }


    public List initComboUser(String query, String branch, String divisi) {
        logger.info("[UserAction.initComboUser] start process >>>");

        List<User> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        try {
            listOfUser = userBo.getComboUserWithCriteria(query, branch, divisi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBo.saveErrorMessage(e.getMessage(), "UserBO.getComboUserWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboUser] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboUser] Error when get combo User," + "[" + logId + "] Found problem when retrieving combo User data, please inform to your admin.", e);
        }

        logger.info("[UserAction.initComboUser] end process <<<");

        return listOfUser;
    }

    public List comboUser(String query) {
        logger.info("[UserAction.initComboUser] start process >>>");

        List<User> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        try {
            listOfUser = userBo.getDataUser(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBo.saveErrorMessage(e.getMessage(), "UserBO.getListUser2");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboUser] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboUser] Error when get combo User," + "[" + logId + "] Found problem when retrieving combo User data, please inform to your admin.", e);
        }

        logger.info("[UserAction.initComboUser] end process <<<");
        return listOfUser;
    }

    public String initChangeNewPasswordForm() {

        clearMessages();
        clearActionErrors();
        User initUsers = new User();
        initUsers.setUserId(CommonUtil.userIdLogin());
        initUsers.setUsername(CommonUtil.userLogin());
        setUsers(initUsers);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        return "init_change_password";
    }

    public List initComboUserId(String query) {
        logger.info("[UserAction.initComboUser] start process >>>");

        List<User> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        try {
            listOfUser = userBo.getComboUserIdWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = userBo.saveErrorMessage(e.getMessage(), "UserBO.getComboUserWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboUser] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboUser] Error when get combo User," + "[" + logId + "] Found problem when retrieving combo User data, please inform to your admin.", e);
        }

        logger.info("[UserAction.initComboUser] end process <<<");

        return listOfUser;
    }

    public String saveNewPassword() {

        logger.info("[UserAction.saveNewPassword] end process <<<");

        User addUser = getUsers();
        String rawPassword = addUser.getPassword();
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(rawPassword);

        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
        String hashedPassword = passwordEncoder.encodePassword(rawPassword, null);


        if (rawPassword.equalsIgnoreCase(addUser.getConfirmPassword())) {

            addUser.setPassword(hashedPassword);

            String itemId = addUser.getUserId() != null ? addUser.getUserId() : CommonUtil.userIdLogin();

            if (itemId != null && !"".equalsIgnoreCase(itemId)) {

                if (this.fileUpload != null) {

                    //note : for linux directory
                    //String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;

                    //note : for windows directory
                    String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD;
                    String fileName = itemId + ".jpg";
                    File fileToCreate = new File(filePath, fileName);

                    //create file to save to folder '/upload'
                    byte[] contentFile = null;
                    try {
                        FileUtils.copyFile(this.fileUpload, fileToCreate);
                        contentFile = FileUtils.readFileToByteArray(this.fileUpload);
                    } catch (IOException e) {
                        Long logId = null;
                        try {
                            logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserAction.saveNewPassword");
                        } catch (GeneralBOException e1) {
                            logger.error("[UserAction.saveNewPassword] Error when saving error,", e1);
                        }
                        logger.error("[UserAction.saveNewPassword] Error when uploading and saving user," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when uploading and saving user, please inform to your admin. Cause : " + e.getMessage());
                        return ERROR;
                    }

                    if (contentFile != null) {
                        addUser.setContentFile(contentFile);
                        addUser.setPhotoUserUrl(fileName);
                    }
                }

                try {

                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    if (addUser.getUserId() == null) addUser.setUserId(itemId);
                    addUser.setCreatedDate(createTime);
                    addUser.setCreatedWho(userLogin);
                    addUser.setLastUpdate(createTime);
                    addUser.setLastUpdateWho(userLogin);
                    addUser.setAction("U");

                    userBoProxy.saveEditPassword(addUser);

                } catch (UsernameNotFoundException e) {
                    logger.error("[UserAction.saveNewPassword] Error when adding item user,", e);
                    addActionError("Error, " + e.getMessage());
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = userBoProxy.saveErrorMessage(e.getMessage(), "UserBO.saveNewPassword");
                    } catch (GeneralBOException e1) {
                        logger.error("[UserAction.saveNewPassword] Error when saving error,", e1);
                    }
                    logger.error("[UserAction.saveNewPassword] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.");
                    return ERROR;
                }
            }

        } else {
            addActionError("Please checked your password not same with confirm password.");
            return ERROR;
        }

        logger.info("[UserAction.saveNewPassword] end process <<<");

        return "success_save_newpassword";
    }

    public User getUserData() {
        logger.info("[UserAction.getUserData] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        String branchId = CommonUtil.userBranchLogin();
        User user = new User();
        user.setUsername(CommonUtil.userLogin());
        user.setBranchName(CommonUtil.userBranchNameLogin());
        user.setAreaName(CommonUtil.userAreaName());

        Branch branches = new Branch();

        try {
            branches = branchBo.getBranchById(branchId, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches.getBranchId() != null) {
            user.setLogoBranch(ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName());
        }

        logger.info("[UserAction.getUserData] end process <<<");
        return user;
    }

    public CrudResponse checkEmailAvailable(String email) {
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        try {
            ImUsers users = userBo.getUserByEmailId(email);
            if (users == null) {
                response.setStatus("success");
            } else {
                response.setStatus("error");
                response.setMsg("Email Not Available");
            }
        } catch (GeneralBOException e) {
            logger.error("Error Where Search By Email Address");
            response.setStatus("error");
            response.setMsg("Error Where Search By Email Address");
        }

        return response;
    }

    public String getStringUrlPhotoProfile() {

        String url = "";
        String userId = CommonUtil.userIdLogin();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        User user = new User();

        try {
            user = userBo.getUserById(userId, "Y");
        } catch (GeneralBOException e) {
            logger.error("[UserAction.getStringUrlPhotoProfile] Error Where Search User ID. " + e);
            throw new GeneralBOException("[UserAction.getStringUrlPhotoProfile] Error Where Search User ID. " + e);
        }

        if (user != null && user.getUserId() != null && user.getPhotoUserUrl() != null)
            url = ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD + user.getPhotoUserUrl();

        return url;
    }

    public User initUser(String userId, String flag) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
        User user = new User();
        List<User> userList = new ArrayList<>();
        if (userId != null && !"".equalsIgnoreCase(userId)) {
            user.setUserId(userId);
            user.setFlag(flag);
            try {
                userList = userBo.getListUserByQuery(user);
            } catch (GeneralBOException e) {
                logger.error(e.getMessage());
            }

            for(User eachUser : userList) {
                if(eachUser.getUserId().equalsIgnoreCase(userId)){
                    user = eachUser;
                    break;
                }
            }
        }
        return user;
    }

    public CrudResponse checkUserIdAvailable(String userId) {
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");

        try {
            ImUsers users = userBo.getUserByUserId(userId);
            if (users == null) {
                response.setStatus("success");
            } else {
                response.setStatus("error");
                response.setMsg("User ID Not Available");
            }
        } catch (GeneralBOException e) {
            logger.error("Error Where Search By User ID Address");
            response.setStatus("error");
            response.setMsg("Error Where Search By User ID Address");
        }
        return response;
    }

    public CrudResponse saveAdd(String data) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        UserBo userBo = (UserBo) ctx.getBean("userBoProxy");
        if (data != null && !"".equalsIgnoreCase(data)) {
            try {
                JSONObject obj = new JSONObject(data);
                if (obj != null) {
                    User user = new User();
                    String tipe = obj.getString("tipe_action");

                    if(!"delete".equalsIgnoreCase(tipe)){
                        if (obj.getString("foto") != null && !"".equalsIgnoreCase(obj.getString("foto"))) {
                            try {
                                BASE64Decoder decoder = new BASE64Decoder();
                                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("foto"));
                                String fileName = obj.getString("user_id") + ".jpg";
                                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD + fileName;
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                                if (image == null) {
                                    logger.error("Buffered Image is null");
                                } else {
                                    CrudResponse crudResponse = CommonUtil.compressImage(image, "png", uploadFile);
                                    if("success".equalsIgnoreCase(crudResponse.getStatus())){
                                        user.setPhotoUserUrl(fileName);
                                    }else{
                                        return crudResponse;
                                    }

                                }
                            } catch (IOException e) {
                                response.setStatus("error");
                                response.setMsg("Error " + e.getMessage());
                                return response;
                            }
                        }
                    }

                    if(obj.getString("password") != null && !"".equalsIgnoreCase(obj.getString("password"))){
                        String rawPassword = obj.getString("password");
                        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
                        String hashedPassword = passwordEncoder.encodePassword(rawPassword, null);
                        user.setPassword(hashedPassword);
                    }

                    user.setUserId(obj.getString("user_id"));
                    user.setUsername(obj.getString("user_name"));
                    user.setAreaId(obj.getString("area_id"));
                    user.setBranchId(obj.getString("branch_id"));
                    user.setDivisiId(obj.getString("divisi_id"));
                    user.setPositionId(obj.getString("position_id"));
                    user.setRoleId(obj.getString("role_id"));

                    if(obj.has("id_pelayanan")){
                        if(obj.getString("id_pelayanan") != null && !"".equalsIgnoreCase(obj.getString("id_pelayanan"))){
                            user.setIdPelayanan(obj.getString("id_pelayanan"));
                        }
                    }
                    if(obj.has("id_ruangan")){
                        if(obj.getString("id_ruangan") != null && !"".equalsIgnoreCase(obj.getString("id_ruangan"))){
                            user.setIdRuangan(obj.getString("id_ruangan"));
                        }
                    }
                    if(obj.has("id_vendor")){
                        if(obj.getString("id_vendor") != null && !"".equalsIgnoreCase(obj.getString("id_vendor"))){
                            user.setIdVendor(obj.getString("id_vendor"));
                        }
                    }

                    user.setEmail(obj.getString("email"));

                    if("edit".equalsIgnoreCase(tipe)){
                        user.setAction("U");
                        user.setFlag("Y");
                    }else if ("delete".equalsIgnoreCase(tipe)){
                        user.setAction("D");
                        user.setFlag("N");
                    }else{
                        user.setFlag("Y");
                        user.setAction("C");
                        user.setCreatedDate(now);
                        user.setCreatedWho(userLogin);
                    }

                    user.setLastUpdate(now);
                    user.setLastUpdateWho(userLogin);

                    try {
                        if("edit".equalsIgnoreCase(tipe)){
                            userBo.saveEdit(user);
                            response.setStatus("success");
                            response.setMsg("OK");
                        }else if("delete".equalsIgnoreCase(tipe)){
                            userBo.saveDelete(user);
                            response.setStatus("success");
                            response.setMsg("OK");
                        }else{
                            userBo.saveAdd(user);
                            response.setStatus("success");
                            response.setMsg("OK");
                        }
                    }catch (Exception e){
                        response.setStatus("error");
                        response.setMsg("Error when save user...! "+e.getMessage());
                    }

                } else {
                    response.setStatus("error");
                    response.setMsg("Data object yang dikirim tidak ada...!");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("JSON parse tidak bisa...!" + e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data object yang dikirim tidak ada...!");
        }
        return response;
    }
}