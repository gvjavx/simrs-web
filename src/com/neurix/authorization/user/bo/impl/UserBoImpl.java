
package com.neurix.authorization.user.bo.impl;

import com.neurix.authorization.company.dao.AreaDao;
import com.neurix.authorization.company.dao.AreasBranchesUsersDao;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.dao.CompanyDao;
import com.neurix.authorization.company.model.ImAreasBranchesUsers;
import com.neurix.authorization.company.model.ImAreasBranchesUsersPK;
import com.neurix.authorization.company.model.ImCompany;
import com.neurix.authorization.function.model.Functions;
import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.role.dao.RoleDao;
import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.Roles;
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.dao.ErrorLogDao;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.dao.UserRoleDao;
import com.neurix.authorization.user.dao.UserSessionLogDao;
import com.neurix.authorization.user.model.*;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Div;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 14/01/13
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class UserBoImpl implements UserBo {
    protected static transient Logger logger = Logger.getLogger(UserBoImpl.class);

    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private UserSessionLogDao userSessionLogDao;
    private ErrorLogDao errorLogDao;
    private RoleDao roleDao;
    private AreaDao areaDao;
    private BranchDao branchDao;
    private AreasBranchesUsersDao areasBranchesUsersDao;
    private PersonilPositionDao personilPositionDao;
    private BiodataDao biodataDao;
    private CompanyDao companyDao;
    private PositionDao positionDao;

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public void setAreaDao(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setErrorLogDao(ErrorLogDao errorLogDao) {
        this.errorLogDao = errorLogDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public void setAreasBranchesUsersDao(AreasBranchesUsersDao areasBranchesUsersDao) {
        this.areasBranchesUsersDao = areasBranchesUsersDao;
    }

    public void setUserSessionLogDao(UserSessionLogDao userSessionLogDao) {
        this.userSessionLogDao = userSessionLogDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDetailsLogin getUserByUsername(String username, String active, String contexPath) throws HibernateException,UsernameNotFoundException {
        logger.info("[UserBoImpl.getUserByUsername] start process >>>");

        ImUsersPK userPK = new ImUsersPK();
        userPK.setId(username);

        ImUsers loginUser = (ImUsers) userDao.getById(userPK,active);
        UserDetailsLogin userDetailsLogin = null;
        if (loginUser != null) {

            String userId = loginUser.getPrimaryKey().getId();
            String password = loginUser.getPassword();
            String userName = loginUser.getUserName();
//            String user = loginUser.getPrimaryKey().

            //get roles
            Collection<ImRoles> listUserRoles = loginUser.getImRoles();
            List<Roles> listRoles = new ArrayList();
            for (ImRoles imRoles : listUserRoles) {
                Roles roles = new Roles(imRoles.getRoleId(), imRoles.getRoleName());
                Collection<ImFunctions> listOfImFunction = imRoles.getImFunction();
                List<Functions> listOfFunction = new ArrayList();
                for ( ImFunctions imFunctions : listOfImFunction ) {
                    if (imFunctions.getMenu()==null) {
                        Functions functions=new Functions();
                        functions.setFuncId(imFunctions.getFuncId());
                        functions.setFuncName(imFunctions.getFuncName());
                        functions.setUrl(imFunctions.getUrl());
                        listOfFunction.add(functions);
                    }
                }

                roles.setListFunctions(listOfFunction);

                listRoles.add(roles);
            }
            String photoUrl = "";
            String statusCaption = "";
            String positionId = loginUser.getImPosition().getPositionId();
            String positionName = loginUser.getImPosition().getPositionName();
            String divisiId="";
            String divisiName="";
            if (loginUser.getImDepartmentEntity()!=null){
                divisiId = loginUser.getImDepartmentEntity().getDepartmentId();
                divisiName = loginUser.getImDepartmentEntity().getDepartmentName();
            }

            try{
                if(loginUser.getImBiodataEntity().getStatusCaption() != null){
                    statusCaption = loginUser.getImBiodataEntity().getStatusCaption();
                }else{
                    statusCaption = "-";
                }
                photoUrl = loginUser.getImBiodataEntity().getFotoUpload();
            }catch (Exception e){
                logger.info("[UserBoImpl.getUserByUsername] end process <<<" + e);
            }


            ImCompany imCompany=companyDao.getCompanyInfo("Y");
            String companyId = imCompany.getCompanyId();
            String companyName = imCompany.getCompanyName();
//            ImBiodataEntity imBiodataEntity = null;
            ImAreasBranchesUsers imAreasBranchesUsers = areasBranchesUsersDao.getAreasBranchesUsersByUserId(userId, "Y");

            String areaId = imAreasBranchesUsers.getImArea().getPrimaryKey().getId();
            String areaName = imAreasBranchesUsers.getImArea().getAreaName();

            String branchId = imAreasBranchesUsers.getImBranch().getPrimaryKey().getId();
            String branchName = imAreasBranchesUsers.getImBranch().getBranchName();

            userDetailsLogin = new UserDetailsLogin();
            userDetailsLogin.setUserId(userId);
            userDetailsLogin.setUsername(username);
            userDetailsLogin.setUserNameDetail(userName);
            userDetailsLogin.setPassword(password);
            userDetailsLogin.setRoles(listRoles);
            userDetailsLogin.setEnabled(true);
            userDetailsLogin.setNonBlocked(true);
            userDetailsLogin.setNonExpired(true);
            userDetailsLogin.setUserCredentialsNonExpired(true);
            //userDetailsLogin.setPositionId(positionId.toString());
            //userDetailsLogin.setPositionName(positionName);
            userDetailsLogin.setIdPleyanan(loginUser.getIdPelayanan());

            /*ItPersonilPositionEntity itPersonilPositionEntity = null ;
            itPersonilPositionEntity = personilPositionDao.getById("nip", userId, "Y");
            if(itPersonilPositionEntity != null){
                userDetailsLogin.setBranchId(itPersonilPositionEntity.getBranchId());
                userDetailsLogin.setBranchName(itPersonilPositionEntity.getImBranches().getBranchName());

                ImPosition imPosition = positionDao.getById("positionId",itPersonilPositionEntity.getPositionId(),"Y");
                if(imPosition != null){
                    userDetailsLogin.setDivisiId(imPosition.getDepartmentId());
                    userDetailsLogin.setPositionId(itPersonilPositionEntity.getPositionId());
                    userDetailsLogin.setPositionName(itPersonilPositionEntity.getImPosition().getPositionName());
                    if(imPosition.getDepartmentId() != null){
                        userDetailsLogin.setDivisiName(imPosition.getImDepartmentEntity().getDepartmentName());
                    }else{
                        userDetailsLogin.setDivisiName("");
                    }
                }else{
                    userDetailsLogin.setDivisiId("");
                    userDetailsLogin.setDivisiName("");
                    userDetailsLogin.setPositionId("");
                    userDetailsLogin.setPositionName("");
                }
            }else{
                userDetailsLogin.setBranchId(branchId);
                userDetailsLogin.setBranchName(branchName);
                userDetailsLogin.setDivisiId("");
                userDetailsLogin.setDivisiName("");
                userDetailsLogin.setPositionId("");
                userDetailsLogin.setPositionName("");
            }*/

            userDetailsLogin.setCompanyId(companyId);
            userDetailsLogin.setCompanyName(companyName);
            userDetailsLogin.setAreaId(areaId);
            userDetailsLogin.setAreaName(areaName);
            userDetailsLogin.setBranchId(branchId);
            userDetailsLogin.setBranchName(branchName);
            userDetailsLogin.setDivisiId(divisiId);
            userDetailsLogin.setDivisiName(divisiName);
            userDetailsLogin.setPositionId(positionId);
            userDetailsLogin.setPositionName(positionName);

            userDetailsLogin.setStatusCaption(statusCaption);
            userDetailsLogin.setPhotoUpload(photoUrl);
            /*userDetailsLogin.setNip(nik);
            userDetailsLogin.setStatusCaption(statusCaption);*/

            //updated : 29-04-2016 , get menu baru
            List<String> listOfMenu = getMenuUser(listRoles, contexPath);
            userDetailsLogin.setMenus(listOfMenu);


            //set customer id, customer name, npwp, and address -> for user payment gateway
//            ImPgUsersCustomer imPgUsersCustomer=usersCustomerDao.getCustomerByUserId(userId, "Y");
//            if (imPgUsersCustomer!=null) {
//                userDetailsLogin.setCustomerId(imPgUsersCustomer.getImPgCustomer().getCustomerId());
//                userDetailsLogin.setCustomerName(imPgUsersCustomer.getImPgCustomer().getCustomerName());
//                userDetailsLogin.setCustomerAddress(imPgUsersCustomer.getImPgCustomer().getAddress());
//                userDetailsLogin.setCustomerNPWP(imPgUsersCustomer.getImPgCustomer().getNpwp());
//
//                userDetailsLogin.setCustomerEmail(imPgUsersCustomer.getImPgCustomer().getEmail());
//            }
        }

        logger.info("[UserBoImpl.getUserByUsername] end process <<<");

        return userDetailsLogin;
    }

    public void insertUserSessionLog(String sessionId, UserDetailsLogin userDetailsLogin, String ip) throws HibernateException {
        logger.info("[UserBoImpl.insertUserSessionLog] start process >>>");

        String userName = userDetailsLogin.getUsername();
        String companyName = userDetailsLogin.getCompanyName();
        String branchName = userDetailsLogin.getBranchName();
        String areaName = userDetailsLogin.getAreaName();
        Timestamp loginTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        ItUserSessionLog itUserSessionLog = new ItUserSessionLog();
        itUserSessionLog.setSessionId(sessionId);
        itUserSessionLog.setUserName(userName);
        itUserSessionLog.setCompanyName(companyName);
        itUserSessionLog.setBranchName(branchName);
        itUserSessionLog.setLoginTimestamp(loginTime);
        itUserSessionLog.setIpAddress(ip);
        itUserSessionLog.setAreaName(areaName);

        userSessionLogDao.addAndSave(itUserSessionLog);

        logger.info("[UserBoImpl.insertUserSessionLog] end process <<<");
    }

    public void updateUserSessionLog(String sessionId) throws HibernateException {
        logger.info("[UserBoImpl.updateUserSessionLog] start process >>>");


        List<ItUserSessionLog>  listOfLogs = userSessionLogDao.getRecordByCriteria(sessionId);
        if (listOfLogs != null) {

            ItUserSessionLog itUserSessionLog = null;
            for (ItUserSessionLog sessionLog : listOfLogs) {
                Timestamp logoutTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                sessionLog.setLogoutTimestamp(logoutTime);

                itUserSessionLog = sessionLog;
            }

            userSessionLogDao.updateAndSave(itUserSessionLog);
        } else {
            logger.info("[UserBoImpl.updateUserSessionLog] not found session in database : " + sessionId);
        }

        logger.info("[UserBoImpl.updateUserSessionLog] end process <<<");
    }


    public UserDetailsLogin retrievePhotoUser(String path, String username) throws HibernateException,IOException {
        logger.info("[UserBoImpl.retrievePhotoUser] start process >>>");

        UserDetailsLogin userDetailsLogin = new UserDetailsLogin();
        boolean validUser = false;
        StringBuffer pathImage = new StringBuffer(path);
        ImUsers loginUser = userDao.getUserByUsername(username, "Y");

        if (loginUser != null) {


//            byte[] dataPhoto = loginUser.getPhoto();
//            if (dataPhoto != null) {
//                if (dataPhoto.length > 0) {
//                    try {
//                        pathImage.append(loginUser.getUserName());
//                        FileOutputStream fos = new FileOutputStream(pathImage.toString());
//                        fos.write(dataPhoto);
//                        fos.close();
//                    } catch (IOException e) {
//                        logger.error("[UserBoImpl.retrievePhotoUser] Unable to write file photo,  " + path + ", error : " + e.toString());
//                        throw new IOException(" Unable to write file photo,  " + path + ", error = ", e);
//                    }
//                }

            String photo = loginUser.getPhotoUrl();
            if (photo != null) {
                pathImage.append(loginUser.getPhotoUrl());
            } else {
                pathImage.append(CommonConstant.RESOURCE_PATH_UNKNOWN_PHOTO);
            }

            validUser = true;

        } else {

            validUser = false;

        }

        userDetailsLogin.setEnabled(validUser);
        userDetailsLogin.setPhotoUserUrl(pathImage.toString());
        logger.info("[UserBoImpl.retrievePhotoUser] end process <<<");

        return userDetailsLogin;
    }

    //updated : 29-04-2016 untuk update menu baru, saat login diambil data menu
    private List getMenuUser(List<Roles> userRoles, String contextPath) throws HibernateException {
        logger.info("[UserBoImpl.getMenuUser] start process >>>");

        //get function based on role user
        List<ImFunctions> listOfFunctions = new ArrayList<ImFunctions>();
        List listdownMenu=new ArrayList();
        List<String> listdownMenuString=new ArrayList<String>();

        for (Roles roles : userRoles) {

            ImRoles imRoles = (ImRoles) roleDao.getById("id",roles.getRoleId(),"Y");

            if (imRoles!=null) {

                Set<ImFunctions> listOfAllFunctions=imRoles.getImFunction();
                for (ImFunctions imFunctions : listOfAllFunctions) {
                    if ("Y".equalsIgnoreCase(imFunctions.getFlag()) && imFunctions.getMenu()!=null) {
                        listOfFunctions.add(imFunctions);
                    }
                }
            }
        }

        List<ImFunctions> listOfCleanMenuFunctions = new ArrayList<ImFunctions>();
        for (ImFunctions imFunctions : listOfFunctions) {
            if (!listOfCleanMenuFunctions.contains(imFunctions)) {
                listOfCleanMenuFunctions.add(imFunctions);
            }
        }

        if (!listOfCleanMenuFunctions.isEmpty()) {
            //sort of collection
            Collections.sort(listOfCleanMenuFunctions);

            //parsing function to formated list

            for (ImFunctions func : listOfCleanMenuFunctions) {
                if (func.getParent() == null || func.getFuncLevel()==1) {
                    if (!isHasMenuInListDownMenu(func, listdownMenu)) {
                        List itemMenu;
                        if (listdownMenu.isEmpty()) {

                            itemMenu = addToList(func.getFuncName(), func.getUrl() != null ? contextPath + func.getUrl() : null, func.getFuncLevel() + "", null, null, null, "0", "0", null, null);

                            //itemMenu="[\"" +  func.getFunctName() + "\",\"\", , , , , \"0\", \"0\", , ]";
                        } else {
                            itemMenu = addToList(func.getFuncName(), func.getUrl() != null ? contextPath + func.getUrl() : null, func.getFuncLevel() + "", null, null, null, "0", null, null, null);

                            //itemMenu="[\"" +  func.getFunctName() + "\",\"\", , , , , \"0\", , , ]";
                        }

                        listdownMenu.add(itemMenu);

                        getDetailMenu(listOfCleanMenuFunctions, listdownMenu, func.getMenu(), contextPath);

                    } else {

                        logger.info("[UserBoImpl.getMenuUser] has more one same item menu." + func);

                    }
                }
            }
        }

        List itemMenu;
        List itemMenuTmp;
        String menuName, menuNameString= "";

        for (int i = 0; i < listdownMenu.size(); i++) {

            itemMenu = (List) listdownMenu.get(i);
            menuName = (String) itemMenu.get(0);
            menuName = menuName.substring(menuName.lastIndexOf('|')+1); //get menu name
            String menuId   = (String) itemMenu.get(9);

            if (itemMenu.size()>1) { //get url

                if (itemMenu.get(2).equals("2")) {

                    if (menuName.equalsIgnoreCase("Project Manajemen")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-book\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Alat")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-briefcase\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Area")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-area-chart\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Branch")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-archive\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Company")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-bank\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Role")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gear\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Function")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gear\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Role-Function")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gear\"></i><span> " + menuName + "<span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Error Log")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gear\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Logout")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-sign-out\"></i><span><div style=\"color:#ff2445;\">" + menuName + "</div></span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Menu Utama")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-square-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("SPPD")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-car\"></i><span> " + menuName + "</span></a></li>";
                    } else if (menuName.equalsIgnoreCase("Medical Record")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-ambulance \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Training")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-desktop \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Mutasi")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-exchange \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Kualifikasi Calon Pejabat")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-balance-scale \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Dispensasi")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-close \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Cuti Pegawai")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-calendar-times-o \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Rekruitmen")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-users \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Evaluasi Pegawai")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-file-text \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Rekruitmen Pabrik")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user-plus \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Lembur")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-black-tie \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Jadwal Shift Kerja")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-sticky-note-o \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Absensi")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-plus-square-o \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Ijin Keluar Kantor")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-hourglass-start \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Payroll")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-paypal \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Indisipliner")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-warning \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Golongan")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-bullseye \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Department")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-university \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Struktur Jabatan")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa   fa-user-md \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Ijin")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-hourglass-start \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Kelompok Position")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-square-o \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Biodata")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-user \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Tipe Pegawai")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-paw \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Keluarga")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-child \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Study")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa  fa-book \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Tipe Notif")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-bell \"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Status Rekruitment")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-users\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("User")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Position")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-briefcase\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Ubah Password")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-unlock\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Dashboard")) {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-bar-chart\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Stok Obat")) {
                        menuNameString = "<li id=\"stok_obat_poli\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Permintaan Obat")) {
                        menuNameString = "<li id=\"permintaan_obat_poli\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Permintaan Obat Gudang")) {
                        menuNameString = "<li id=\"permintaan_obat_gudang\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Penerimaan Obat")) {
                        menuNameString = "<li id=\"penerimaan_obat_poli\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Rawat Jalan")) {
                        menuNameString = "<li id=\"bayar_rawat_jalan\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Uang Muka")) {
                        menuNameString = "<li id=\"pembayaran_uang_muka\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Kode Rekening")) {
                        menuNameString = "<li id=\"kode_rekening1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Rawat Inap")) {
                        menuNameString = "<li id=\"bayar_rawat_inap\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("FPK")) {
                        menuNameString = "<li id=\"fpk\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Tipe Jurnal")) {
                        menuNameString = "<li id=\"tipe_jurnal1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Tipe Rekening")) {
                        menuNameString = "<li id=\"tipe_rekening1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Tipe Transaksi")) {
                        menuNameString = "<li id=\"tipe_transaksi1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Mapping Jurnal ")) {
                        menuNameString = "<li id=\"mapping_jurnal1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Vendor")) {
                        menuNameString = "<li id=\"vendor1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Setting Report Akuntansi")) {
                        menuNameString = "<li id=\"setting_report_akuntansi\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-cog\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Setting User Report")) {
                        menuNameString = "<li id=\"setting_user_report1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-cog\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Laporan Akuntansi")) {
                        menuNameString = "<li id=\"laporan_akuntansi1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-tasks\"></i><span> " + menuName + "</span></a></li>";
                    }else if (menuName.equalsIgnoreCase("Pembayaran Hutang Piutang")) {
                        menuNameString = "<li id=\"pembayaran_hutang_piutang1\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-money\"></i><span> " + menuName + "</span></a></li>";
                    }else {
                        menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-circle-o\"></i><span> " + menuName + "</span></a></li>";
                    }

                    if(i < listdownMenu.size() - 1){
                        itemMenuTmp = (List) listdownMenu.get(i + 1);
                        if(itemMenuTmp.get(2).equals("1")){
                            menuNameString += "</ul>" +
                                    "</li>";
                        }
                    }

                } else if(itemMenu.get(2).equals("1")) {
                    //menuNameString = "<li><a href=\"#\"><i class=\"fa fa-files-o\"></i><span><strong> "+ menuName + "</strong></span></a></li>";
                    menuNameString = "";

                    if(itemMenu.get(1) != null){

                        if(menuName.equalsIgnoreCase("Dashboard")){
                                menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-bar-chart\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pendaftaran Rawat Jalan")) {
                            menuNameString = "<li id=\"pendaftaran\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user-md\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pel. Rawat Jalan")) {
                            menuNameString = "<li id=\"rawat_jalan\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-stethoscope\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rawat Inap")) {
                            menuNameString = "<li id=\"rawat_inap\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Periksa Lab")) {
                            menuNameString = "<li id=\"periksa_lab\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-hospital-o\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Periksa Radiologi")) {
                            menuNameString = "<li id=\"periksa_radiologi\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-heartbeat\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Obat")) {
                            menuNameString = "<li id=\"menu_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-plus-square\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Permintaan Obat")) {
                            menuNameString = "<li id=\"permintaan_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Transaksi Apotek")) {
                            menuNameString = "<li id=\"transaksi_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-plus-square\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("IGD")) {
                            menuNameString = "<li id=\"igd\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-plus-square\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Resep Poli")) {
                            menuNameString = "<li id=\"resep_poli\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-file-text-o\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Purchase Order")) {
                            menuNameString = "<li id=\"permintaan_po\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-tasks\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Verifikasi BPJS RJ")) {
                            menuNameString = "<li id=\"verifikasi_rawat_jalan\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Verifikasi BPJS RI")) {
                                menuNameString = "<li id=\"verifikasi_rawat_inap\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-check-square\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Permintaan Gizi")) {
                            menuNameString = "<li id=\"permintaan_gizi\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pembayaran")) {
                            menuNameString = "<li id=\"pembayaran\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-money\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Ruangan")) {
                            menuNameString = "<li id=\"monitor_ruangan\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-television\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pasien")) {
                            menuNameString = "<li id=\"pasien\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Harga Obat")) {
                            menuNameString = "<li id=\"harga_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Vendor Obat")) {
                            menuNameString = "<li id=\"vendor_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Paket Periksa")) {
                            menuNameString = "<li id=\"paket_periksa\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-briefcase\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pendaftaran Paket")) {
                            menuNameString = "<li id=\"daftar_paket\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-archive\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Status Pasien")) {
                            menuNameString = "<li id=\"status_pasien\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-suitcase\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Daftar Ulang Pasien")) {
                            menuNameString = "<li id=\"daftar_ulang_pasien\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Retur Obat")) {
                            menuNameString = "<li id=\"retur_obat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-refresh\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rekam Medik")) {
                            menuNameString = "<li id=\"rekam_medik\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-book\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rencana Kegiatan Rawat")) {
                            menuNameString = "<li id=\"rencana_kegiatan_rawat\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-calendar\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Verifikasi Non BPJS")) {
                            menuNameString = "<li id=\"verifikasi_transaksi_pasien\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gavel\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Pendaftaran Rawat Inap")) {
                            menuNameString = "<li id=\"tppri\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-user-md\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rawat Intensif")) {
                            menuNameString = "<li id=\"rawat_intensif\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-stethoscope\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rawat Isolasi")) {
                            menuNameString = "<li id=\"rawat_isolasi\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-hospital-o\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rawat Operasi")) {
                            menuNameString = "<li id=\"rawat_operasi\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-heartbeat\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Rawat Bersalin")) {
                            menuNameString = "<li id=\"rawat_bersalin\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-medkit\"></i><span> " + menuName + "</span></a></li>";
                        }else if (menuName.equalsIgnoreCase("Verifikasi Cover Asuransi")) {
                            menuNameString = "<li id=\"verifikasi_cover\"><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-money\"></i><span> " + menuName + "</span></a></li>";
                        }else{
                            menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-sign-out\"></i><span> " + menuName + "</span></a></li>";
                        }
                    }else{

                        //sodiq 17-12-2019, dicomment agar tidak benrantakan di sidebar

//                        if(i > 0){
//                            itemMenuTmp = (List) listdownMenu.get(i - 1);
//                            if(!itemMenuTmp.get(2).equals("2")){
//                                menuNameString = "</ul>" +
//                                        "</li>";
//                            }
//                        }
                        String icon ="";
                        String idLi = "";
                        String openLu ="";

                        if (("Transaksi").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-folder-open\"></i>";
                        } else if (("Master").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-database\"></i>";
                        }else if (("Approval").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-check-square-o\"></i>";
                        }else if (("Setting").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-cogs\"></i>";
                        }else if (("Kas/Bank Masuk").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-money\"></i>";
                        }else if (("Kas/Bank Keluar").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-money\"></i>";
                        }else if (("Obat").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-medkit\"></i>";
                            idLi = "obat_poli_active";
                            openLu = "obat_poli_open";
                        } else if (("Pembayaran").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-money\"></i>";
                            idLi = "pembayaran_active";
                            openLu = "pembayaran_open";
                        }else if (("Verifikasi BPJS / PTPN").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-gavel\"></i>";
                            idLi = "verif_bpjs_active";
                            openLu = "verif_bpjs_open";
                        }else if (("Verifikasi Umum").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-gavel\"></i>";
                            idLi = "verif_umum_active";
                            openLu = "verif_umum_open";
                        }else if (("Verifikasi Asuransi").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-gavel\"></i>";
                            idLi = "verif_asuransi_active";
                            openLu = "verif_asuransi_open";
                        }else if (("Pendaftaran").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-user-md\"></i>";
                            idLi = "pendaftaran_active";
                            openLu = "pendaftaran_open";
                        }else if (("Pel. Rawat Inap").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-medkit\"></i>";
                            idLi = "pel_ri_active";
                            openLu = "pel_ri_open";
                        }else if (("Penunjang Medis").equalsIgnoreCase(menuName)){
                            icon="<i class=\"fa fa-heartbeat\"></i>";
                            idLi = "penunjang_active";
                            openLu = "penunjang_open";
                        }



                        menuNameString +=
                                "<li class=\"treeview\" id="+idLi+"> " +
                                        "<a href=\"#\">"+icon+"<span> "+" "+ menuName+"</span>" +
                                        "<span class=\"pull-right-container\">" +
                                        "<i class=\"fa fa-angle-left pull-right\"></i>"+
                                        "<span>"+
                                        "</a>" +
                                        "<ul class=\"treeview-menu\" id="+openLu+">";
                    }
                } else if(itemMenu.get(2).equals("3")) {
                    //menuNameString = "<li><a href=\"#\"><i class=\"fa fa-files-o\"></i><span><strong> "+ menuName + "</strong></span></a></li>";
                    menuNameString = "";

                    if(i > 0){
                        itemMenuTmp = (List) listdownMenu.get(i + 1);
                        if(!itemMenuTmp.get(2).equals("4")){
                            menuNameString = "</ul>" +
                                    "</li>";
                        }
                    }

                    String icon = "<i class=\"fa fa-files-o\"></i>";
                    if(menuName.equalsIgnoreCase("SPPD")){
                        icon = "<i class=\"fa fa-car\"></i>";
                    }else if(menuName.equalsIgnoreCase("Payroll")){
                        icon = "<i class=\"fa fa-paypal\"></i>";
                    }else if(menuName.equalsIgnoreCase("SMK")){
                        icon = "<i class=\"fa fa-file-text\"></i>";
                    }else if(menuName.equalsIgnoreCase("Medical Record")){
                        icon = "<i class=\"fa fa-ambulance\"></i>";
                    }else if(menuName.equalsIgnoreCase("Lembur")){
                        icon = "<i class=\"fa fa-black-tie\"></i>";
                    }else if(menuName.equalsIgnoreCase("Jadwal Shift Kerja")){
                        icon = "<i class=\"fa fa-sticky-note-o\"></i>";
                    }else if(menuName.equalsIgnoreCase("Absensi")){
                        icon = "<i class=\"fa fa-plus-square-o\"></i>";
                    }else if(menuName.equalsIgnoreCase("Cuti")){
                        icon = "<i class=\"fa fa-calendar-times-o\"></i>";
                    }

                    menuNameString +=
                            "<li class=\"treeview\"> " +
                                    "<a href=\"#\">"+icon+" <span>"+menuName+"</span>" +
                                    "<span class=\"pull-right-container\">" +
                                    "<i class=\"fa fa-angle-left pull-right\"></i>"+
                                    "<span>"+
                                    "</a>" +
                                    "<ul class=\"treeview-menu\">";
                }
                else if(itemMenu.get(2).equals("4")) {
                    //menuNameString = "<li><a href=\"#\"><i class=\"fa fa-files-o\"></i><span><strong> "+ menuName + "</strong></span></a></li>";
                    menuNameString = "<li><a href=\"" + itemMenu.get(1) + "\"><i class=\"fa fa-gear\"></i><span> " + menuName + "</span></a></li>";
                    if(i < listdownMenu.size() - 1){
                        itemMenuTmp = (List) listdownMenu.get(i + 1);
                        if(!itemMenuTmp.get(2).equals("4")) {
                            menuNameString += "</ul>" +
                                    "</li>";
                        }
                    }
                }

            } else {
                String icon = "<i class=\"fa fa-files-o\"></i>";
                if(menuName.equalsIgnoreCase("SPPD")){
                }
                icon = "<i class=\"fa fa-car\"></i>";
                menuNameString = "<li><a href=\"#\">"+icon+"<span><strong> "+ menuName + "</strong></span></a></li>";

            }

            listdownMenuString.add(menuNameString);

        }

        logger.info("[UserBoImpl.getMenuUser] end process <<<");

        return listdownMenuString;

    }


    public List getMenuUser(UserDetailsLogin userDetailsLogin, String contextPath) throws HibernateException {
        logger.info("[UserBoImpl.getMenuUser] start process >>>");

        //get function based on role user
        List<Roles> userRoles = userDetailsLogin.getRoles();
        List<ImFunctions> listOfFunctions = new ArrayList<ImFunctions>();
        List listdownMenu=new ArrayList();

        for (Roles roles : userRoles) {

            ImRoles imRoles = (ImRoles) roleDao.getById("id",roles.getRoleId(),"Y");

            if (imRoles!=null) {

                Set<ImFunctions> listOfAllFunctions=imRoles.getImFunction();
                for (ImFunctions imFunctions : listOfAllFunctions) {
                    if ("Y".equalsIgnoreCase(imFunctions.getFlag()) && imFunctions.getMenu()!=null) {
                        listOfFunctions.add(imFunctions);
                    }
                }
            }
        }

        List<ImFunctions> listOfCleanMenuFunctions = new ArrayList<ImFunctions>();
        for (ImFunctions imFunctions : listOfFunctions) {
            if (!listOfCleanMenuFunctions.contains(imFunctions)) {
                listOfCleanMenuFunctions.add(imFunctions);
            }
        }

        if (!listOfCleanMenuFunctions.isEmpty()) {
            //sort of collection
            Collections.sort(listOfCleanMenuFunctions);

            //parsing function to formated list

            for (ImFunctions func : listOfCleanMenuFunctions) {
                if (func.getParent() == null || func.getFuncLevel()==1) {
                    if (!isHasMenuInListDownMenu(func, listdownMenu)) {
                        List itemMenu;
                        if (listdownMenu.isEmpty()) {

                            itemMenu = addToList(func.getFuncName(), func.getUrl() != null ? contextPath + func.getUrl() : null, null, null, null, null, "0", "0", null, null);

                            //itemMenu="[\"" +  func.getFunctName() + "\",\"\", , , , , \"0\", \"0\", , ]";
                        } else {

                            itemMenu = addToList(func.getFuncName(), func.getUrl() != null ? contextPath + func.getUrl() : null, null, null, null, null, "0", null, null, null);

                            //itemMenu="[\"" +  func.getFunctName() + "\",\"\", , , , , \"0\", , , ]";
                        }

                        listdownMenu.add(itemMenu);

                        getDetailMenu(listOfCleanMenuFunctions, listdownMenu, func.getMenu(), contextPath);

                    } else {

                        logger.info("[UserBoImpl.getMenuUser] has more one same item menu." + func);

                    }
                }
            }
        }


        logger.info("[UserBoImpl.getMenuUser] end process <<<");

        return listdownMenu;
    }

    /**
     * used to get list of record from im_functions based on whereclause string
     *
     * @param listOfMenuDB
     * @param whereCluse
     * @return
     */
    private List getListRecord(List listOfMenuDB, Long whereCluse) {
        List listRecord = new ArrayList();
        ImFunctions func;
        for (int i = 0; i < listOfMenuDB.size(); i++) {
            func = (ImFunctions) listOfMenuDB.get(i);
            if (func.getParent() != null && func.getParent() != null) {
                if (func.getParent().equals(whereCluse )) {
                    listRecord.add(func);
                }
            }
        }

        return listRecord;
    }

    /**
     * used to get list of menu based on user roles
     *
     * @param listOfMenuDB
     * @param listdownMenuString
     * @param menuId
     * @param contextPath
     */
    private void getDetailMenu(List listOfMenuDB, List listdownMenuString, Long menuId, String contextPath) {
        logger.info("begin execute method getDetailMenu ");

        List parentUseThisId = getListRecord(listOfMenuDB, menuId);
        List sFormatMenu;
        ImFunctions func;
        for (int i = 0; i < parentUseThisId.size(); i++) {
            func = (ImFunctions) parentUseThisId.get(i);
            if (!isHasMenuURLInListDownMenu(func, listdownMenuString)) {
                sFormatMenu = formatMenu(contextPath, func.getFuncName(), func.getUrl(), func.getFuncLevel());
                listdownMenuString.add(sFormatMenu);
                getDetailMenu(listOfMenuDB, listdownMenuString, func.getMenu(), contextPath);
            } else {
                logger.info("[getDetailMenu] has more one same item menu." + func);
            }

        }
        logger.info("end execute method getDetailMenu ");
    }

    /**
     * used to check a menu in list of menu or not
     *
     * @param selectedFunc
     * @param listdownMenu
     * @return
     */
    private boolean isHasMenuURLInListDownMenu(ImFunctions selectedFunc, List listdownMenu) {
        boolean flag = false;
        List itemMenu;
        for (int i = 0; i < listdownMenu.size() && !flag; i++) {
            itemMenu = (List) listdownMenu.get(i);
            if (selectedFunc.getUrl() != null && !selectedFunc.getUrl().equalsIgnoreCase("") && itemMenu.get(1) != null  ) {

                if (((String) itemMenu.get(1)).equalsIgnoreCase(selectedFunc.getUrl())) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * used to check if menu in list of menu or not
     *
     * @param selectedFunc
     * @param listdownMenu
     * @return
     */
    private boolean isHasMenuInListDownMenu(ImFunctions selectedFunc, List listdownMenu) {
        boolean flag = false;
        List itemMenu;
        for (int i = 0; i < listdownMenu.size() && !flag; i++) {
            itemMenu = (List) listdownMenu.get(i);
            //comment, updated 30-04-2016, menu dont have url, is null
//            if (!((String) itemMenu.get(0)).startsWith("|") && ((String) itemMenu.get(1)).equalsIgnoreCase("")) {
            if (!((String) itemMenu.get(0)).startsWith("|") && (itemMenu.get(1)==null)) {
                if (((String) itemMenu.get(0)).equalsIgnoreCase(selectedFunc.getFuncName())) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * used to format, items menu to string of list, ex : ["|xxxx","url",null,null,null,null,null,null,null,null]
     *
     * @param contextPath
     * @param nameOfMenu
     * @param url
     * @param level
     * @return
     */
    private List formatMenu(String contextPath, String nameOfMenu, String url, Long level) {
        //contoh :
        //["|||Jenis Biaya","/simpat-pelindo4/anggaranakuntansi/jenisbiaya/jenisBiaya_input.action", , , , , , , , ],
        //["||Master","", , , , , , , , ],

        logger.info("begin execute method formatMenu ");

        List sFormatMenu;
        long iLevel = level.longValue();
        String sPad = ""; // penambahan tanda '|' sebagai level tree di menu
        for (int i = 0; i < iLevel - 1; i++) {
            sPad += "|";
        }

        if (url != null && !url.equalsIgnoreCase("")) {
            sFormatMenu = addToList(sPad + nameOfMenu, contextPath + url, iLevel + "", null, null, null, null, null, null, null);
            //sFormatMenu="[\"" + sPad + nameOfMenu + "\",\"/" + contextPath + url + "\", , , , , , , , ]";
        } else {
            sFormatMenu = addToList(sPad + nameOfMenu, "", null, iLevel + "", null, null, null, null, null, null);
            //sFormatMenu="[\"" + sPad + nameOfMenu + "\",\"\", , , , , , , , ]";
        }

        logger.info("end execute method formatMenu ");

        return sFormatMenu;
    }

    /**
     * to add list menu based on format
     *
     * @param param1
     * @param param2
     * @param param3
     * @param param4
     * @param param5
     * @param param6
     * @param param7
     * @param param8
     * @param param9
     * @param param10
     * @return
     */
    private List addToList(String param1,
                           String param2,
                           String param3,
                           String param4,
                           String param5,
                           String param6,
                           String param7,
                           String param8,
                           String param9,
                           String param10) {

        List list = new ArrayList(10);
        list.add(param1);
        list.add(param2);
        list.add(param3);
        list.add(param4);
        list.add(param5);
        list.add(param6);
        list.add(param7);
        list.add(param8);
        list.add(param9);
        list.add(param10);

        return list;
    }

    public List<User> getUserSameBranchByCriteria(User searchUsers) throws GeneralBOException {
        logger.info("[UserBoImpl.getUserSameBranchByCriteria] start process >>>");

        List<User> listOfResultUsers = new ArrayList();

        if (searchUsers != null) {
            String branchId = searchUsers.getBranchId();
            Map hsCriteria = new HashMap();
            if (searchUsers.getPositionId() != null && !"".equalsIgnoreCase(searchUsers.getPositionId())) {
                hsCriteria.put("position_id", Long.valueOf(searchUsers.getPositionId()));
            }

            if (searchUsers.getFlag() != null && !"".equalsIgnoreCase(searchUsers.getFlag())) {
                if ("N".equalsIgnoreCase(searchUsers.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchUsers.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImUsers> listOfUsers = null;
            try {
                listOfUsers = userDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUsers != null) {
                User resultUsers;
                for (ImUsers imUsers : listOfUsers) {

                    if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                        List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                        for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                            if (branchId.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                                resultUsers = new User();

                                resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                resultUsers.setUsername(imUsers.getUserName());

                                listOfResultUsers.add(resultUsers);
                            }
                        }

                    }


                }
            }
        }

        logger.info("[UserBoImpl.getByCriteria] end process <<<");

        return listOfResultUsers;

    }

    public List<User> getUserLLByCriteria(User searchUsers) throws GeneralBOException {
        logger.info("[UserBoImpl.getUserLLByCriteria] start process >>>");

        List<User> listOfResultUsers = new ArrayList();

        if (searchUsers != null) {
            String branchId = searchUsers.getBranchId();
            String roleId = searchUsers.getRoleId();
            long lRoleId = Long.valueOf(roleId);

            Map hsCriteria = new HashMap();
            if (searchUsers.getPositionId() != null && !"".equalsIgnoreCase(searchUsers.getPositionId())) {
                hsCriteria.put("position_id", Long.valueOf(searchUsers.getPositionId()));
            }

            if (searchUsers.getFlag() != null && !"".equalsIgnoreCase(searchUsers.getFlag())) {
                if ("N".equalsIgnoreCase(searchUsers.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchUsers.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImUsers> listOfUsers = null;
            try {
                listOfUsers = userDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getUserLLByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUsers != null) {
                User resultUsers;
                for (ImUsers imUsers : listOfUsers) {

                    if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                        boolean isFound = false;
                        Set<ImRoles> listRoles = imUsers.getImRoles();
                        for (ImRoles imRoles : listRoles) {
                            if (imRoles.getRoleId() == lRoleId) {
                                isFound = true;
                            }
                        }

                        if (isFound) {

                            List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                            for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                                if (branchId.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                                    resultUsers = new User();

                                    resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                    resultUsers.setUsername(imUsers.getUserName());

                                    listOfResultUsers.add(resultUsers);
                                }
                            }

                        }


                    }


                }
            }
        }

        logger.info("[UserBoImpl.getUserLLByCriteria] end process <<<");

        return listOfResultUsers;

    }

    public List<User> getUserSamePositionByCriteria(User searchUsers) throws GeneralBOException {
        logger.info("[UserBoImpl.getUserSamePositionByCriteria] start process >>>");

        List<User> listOfResultUsers = new ArrayList();

        if (searchUsers != null) {
            Map hsCriteria = new HashMap();
            if (searchUsers.getPositionId() != null && !"".equalsIgnoreCase(searchUsers.getPositionId())) {
                hsCriteria.put("position_id", Long.valueOf(searchUsers.getPositionId()));
            }

            if (searchUsers.getFlag() != null && !"".equalsIgnoreCase(searchUsers.getFlag())) {
                if ("N".equalsIgnoreCase(searchUsers.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchUsers.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImUsers> listOfUsers = null;
            try {
                listOfUsers = userDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUsers != null) {
                User resultUsers;
                for (ImUsers imUsers : listOfUsers) {

                    if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                        resultUsers = new User();

                        resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                        resultUsers.setUsername(imUsers.getUserName());

                        listOfResultUsers.add(resultUsers);

                    }

                }
            }
        }

        logger.info("[UserBoImpl.getUserSamePositionByCriteria] end process <<<");

        return listOfResultUsers;

    }

    public List<User> getComboOpsGpsOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboOpsGpsOnlyWithCriteria] start process >>>");

        List<User> listComboOpsGps = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListOpsGps(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboOpsGpsOnlyWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list ops gps with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            User resultUsers;
            for (ImUsers imUsers : imUsersEntityList) {

                if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                    List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                    for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                        if (pabrikGula.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                            resultUsers = new User();

                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                            resultUsers.setUsername(imUsers.getUserName());

                            listComboOpsGps.add(resultUsers);
                        }
                    }

                }


            }
        }

        logger.info("[UserBoImpl.getComboOpsGpsOnlyWithCriteria] end process <<<");

        return listComboOpsGps;
    }

    public List<User> getComboAsmudOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboAsmudOnlyWithCriteria] start process >>>");

        List<User> listComboAsmud = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListAsmud(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboAsmudOnlyWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list asmud with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            User resultUsers;
            for (ImUsers imUsers : imUsersEntityList) {

                if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                    List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                    for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                        if (pabrikGula.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                            resultUsers = new User();

                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                            resultUsers.setUsername(imUsers.getUserName());

                            listComboAsmud.add(resultUsers);
                        }
                    }

                }


            }
        }

        logger.info("[UserBoImpl.getComboAsmudOnlyWithCriteria] end process <<<");

        return listComboAsmud;
    }

    public List<User> getComboAsmanOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboAsmanOnlyWithCriteria] start process >>>");

        List<User> listComboAsman = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListAsman(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboAsmanOnlyWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list asman with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            User resultUsers;
            for (ImUsers imUsers : imUsersEntityList) {

                if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                    List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                    for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                        if (pabrikGula.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                            resultUsers = new User();

                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                            resultUsers.setUsername(imUsers.getUserName());

                            listComboAsman.add(resultUsers);
                        }
                    }

                }


            }
        }

        logger.info("[UserBoImpl.getComboAsmanOnlyWithCriteria] end process <<<");

        return listComboAsman;
    }

    public List<User> getComboMantanOnlyWithCriteria(String query, String pabrikGula) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboMantanOnlyWithCriteria] start process >>>");

        List<User> listComboMantan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListAsman(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboMantanOnlyWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list mantan with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            User resultUsers;
            for (ImUsers imUsers : imUsersEntityList) {

                if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {

                    List<ImAreasBranchesUsers> listOfImBranches = new ArrayList<ImAreasBranchesUsers>(imUsers.getImAreasBranchesUsers());

                    for (ImAreasBranchesUsers imBranches : listOfImBranches) {
                        if (pabrikGula.equalsIgnoreCase(imBranches.getImBranch().getPrimaryKey().getId())) {
                            resultUsers = new User();

                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                            resultUsers.setUsername(imUsers.getUserName());

                            listComboMantan.add(resultUsers);
                        }
                    }

                }


            }
        }

        logger.info("[UserBoImpl.getComboMantanOnlyWithCriteria] end process <<<");

        return listComboMantan;
    }

    public List<User> getComboAsmanWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboAsmanWithCriteria] start process >>>");

        List<User> listComboAsman = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListAsman(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboAsmanWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list asman with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            for (ImUsers imUsersEntity : imUsersEntityList) {
                User itemComboAsman = new User();
                itemComboAsman.setUserId(imUsersEntity.getPrimaryKey().getId());
                itemComboAsman.setUsername(imUsersEntity.getUserName());

                listComboAsman.add(itemComboAsman);
            }
        }
        logger.info("[UserBoImpl.getComboAsmanWithCriteria] end process <<<");

        return listComboAsman;
    }


    public List<User> getComboAsmudWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboAsmudWithCriteria] start process >>>");

        List<User> listComboAsmud = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListAsmud(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboAsmudWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list asmud with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            for (ImUsers imUsersEntity : imUsersEntityList) {
                User itemComboAsmud = new User();
                itemComboAsmud.setUserId(imUsersEntity.getPrimaryKey().getId());
                itemComboAsmud.setUsername(imUsersEntity.getUserName());

                listComboAsmud.add(itemComboAsmud);
            }
        }
        logger.info("[UserBoImpl.getComboAsmudWithCriteria] end process <<<");

        return listComboAsmud;
    }

    @Override
    public boolean isActiveUserSessionLog(String sessionId) throws HibernateException {
        logger.info("[UserBoImpl.isActiveUserSessionLog] start process >>>");

        boolean isActive = false;
        List<ItUserSessionLog> listusersessionLog = userSessionLogDao.getRecordByCriteria(sessionId);
        if (listusersessionLog != null) {

            for (ItUserSessionLog userSessionLog : listusersessionLog) {

                if (userSessionLog.getLogoutTimestamp()==null) {
                    isActive = true;
                }

            }

        } else {
            isActive = false;
            logger.info("[UserBoImpl.isActiveUserSessionLog] not found session in database : " + sessionId);
        }

        logger.info("[UserBoImpl.isActiveUserSessionLog] end process <<<");

        return isActive;
    }

    @Override
    public boolean isFoundOtherSessionActiveUserSessionLog(String userId) throws HibernateException {
        logger.info("[UserBoImpl.isFoundOtherSessionActiveUserSessionLog] start process >>>");

        boolean isFound = userSessionLogDao.getRecordUserStillActive(userId);

        logger.info("[UserBoImpl.isFoundOtherSessionActiveUserSessionLog] end process <<<");

        return isFound;
    }

    @Override
    public UserDetailsLogin getMobileUserByUsername(String username, String active) throws HibernateException, UsernameNotFoundException {
        logger.info("[UserBoImpl.getMobileUserByUsername] start process >>>");

        ImUsersPK userPK = new ImUsersPK();
        userPK.setId(username);

        ImUsers loginUser = (ImUsers) userDao.getById(userPK,active);
        UserDetailsLogin userDetailsLogin = null;
        if (loginUser != null) {
            String namaPosisi = "";
            List <ItPersonilPositionEntity> personal = personilPositionDao.getListNip(loginUser.getPrimaryKey().getId());
            if(personal.size() > 0){
                for(ItPersonilPositionEntity peronilLoop: personal){
                    namaPosisi = peronilLoop.getImPosition().getPositionName();
                }
            }

            String userId = loginUser.getPrimaryKey().getId();
            String password = loginUser.getPassword();
            String userName = loginUser.getUserName();

            //get roles
            Collection<ImRoles> listUserRoles = loginUser.getImRoles();
            List<Roles> listRoles = new ArrayList();
            for (ImRoles imRoles : listUserRoles) {
                Roles roles = new Roles(imRoles.getRoleId(), imRoles.getRoleName());

                listRoles.add(roles);
            }

            String positionId = loginUser.getImPosition().getPositionId();

            String positionName = namaPosisi;

            String kelompokId =loginUser.getImPosition().getKelompokId();
            ImCompany imCompany=companyDao.getCompanyInfo("Y");
            String companyId = imCompany.getCompanyId();
            String companyName = imCompany.getCompanyName();

            ImAreasBranchesUsers imAreasBranchesUsers = areasBranchesUsersDao.getAreasBranchesUsersByUserId(userId, "Y");
            String areaId = imAreasBranchesUsers.getImArea().getPrimaryKey().getId();
            String areaName = imAreasBranchesUsers.getImArea().getAreaName();

            String branchId = imAreasBranchesUsers.getImBranch().getPrimaryKey().getId();
            String branchName = imAreasBranchesUsers.getImBranch().getBranchName();

            ImBiodataEntity biodata = biodataDao.getById("nip", userId, "Y");

            userDetailsLogin = new UserDetailsLogin();
            userDetailsLogin.setUserId(userId);
            userDetailsLogin.setUsername(username);
            userDetailsLogin.setUserNameDetail(userName);
            userDetailsLogin.setPassword(password);
            userDetailsLogin.setRoles(listRoles);
            userDetailsLogin.setEnabled(true);
            userDetailsLogin.setNonBlocked(true);
            userDetailsLogin.setNonExpired(true);
            userDetailsLogin.setUserCredentialsNonExpired(true);
            userDetailsLogin.setPositionId(positionId);
            userDetailsLogin.setPositionName(positionName);
            userDetailsLogin.setBranchId(branchId);
            userDetailsLogin.setBranchName(branchName);
            userDetailsLogin.setCompanyId(companyId);
            userDetailsLogin.setCompanyName(companyName);
            userDetailsLogin.setAreaId(areaId);
            userDetailsLogin.setAreaName(areaName);
            userDetailsLogin.setIdPleyanan(loginUser.getIdPelayanan());
            try {
                userDetailsLogin.setPin(biodata.getPin());
            } catch (NullPointerException e){
                e.printStackTrace();
            }
            userDetailsLogin.setIdDevice(loginUser.getIdDevice());

            if  (biodata != null) {
                userDetailsLogin.setJenisKelamin(biodata.getGender());
                userDetailsLogin.setFlagFingerMoblie(biodata.getFlagFingerMobile());
            }


        }

        logger.info("[UserBoImpl.getMobileUserByUsername] end process <<<");

        return userDetailsLogin;
    }

    public List<User> getComboApprovalPersonWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboApprovalPersonWithCriteria] start process >>>");

        List<User> listComboApproval = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListApprovalPerson(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboApprovalPersonWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list approval person with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            for (ImUsers imUsersEntity : imUsersEntityList) {
                User itemComboApproval = new User();
                itemComboApproval.setUserId(imUsersEntity.getPrimaryKey().getId());
                itemComboApproval.setUsername(imUsersEntity.getUserName());

                listComboApproval.add(itemComboApproval);
            }
        }
        logger.info("[UserBoImpl.getComboApprovalPersonWithCriteria] end process <<<");

        return listComboApproval;
    }

    public List<User> getComboTanamanPersonWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboTanamanPersonWithCriteria] start process >>>");

        List<User> listComboTanaman = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> imUsersEntityList = null;
        try {
            imUsersEntityList = userDao.getListTanamanPerson(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboTanamanPersonWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list tanaman person with criteria, please info to your admin..." + e.getMessage());
        }

        if (imUsersEntityList != null) {
            for (ImUsers imUsersEntity : imUsersEntityList) {
                User itemComboTanaman = new User();
                itemComboTanaman.setUserId(imUsersEntity.getPrimaryKey().getId());
                itemComboTanaman.setUsername(imUsersEntity.getUserName());

                listComboTanaman.add(itemComboTanaman);
            }
        }
        logger.info("[UserBoImpl.getComboTanamanPersonWithCriteria] end process <<<");

        return listComboTanaman;
    }

    public List<User> getByCriteria(User searchUsers) throws GeneralBOException {

        logger.info("[UserBoImpl.getByCriteria] start process >>>");

        List<User> listOfResultUsers = new ArrayList();

        if (searchUsers != null) {
            Map hsCriteria = new HashMap();
            if (searchUsers.getUserId() != null && !"".equalsIgnoreCase(searchUsers.getUserId())) {
                hsCriteria.put("user_id", searchUsers.getUserId());
            }

            if (searchUsers.getUsername() != null && !"".equalsIgnoreCase(searchUsers.getUsername())) {
                hsCriteria.put("user_name", searchUsers.getUsername());
            }

            if (searchUsers.getDivisiId() != null && !"".equalsIgnoreCase(searchUsers.getDivisiId())) {
                hsCriteria.put("divisi_id", searchUsers.getDivisiId());
            }

            if (searchUsers.getEmail() != null && !"".equalsIgnoreCase(searchUsers.getEmail())) {
                hsCriteria.put("email", searchUsers.getEmail());
            }

            if (searchUsers.getPositionId() != null && !"".equalsIgnoreCase(searchUsers.getPositionId())) {
                hsCriteria.put("position_id", searchUsers.getPositionId());
            }

            if (searchUsers.getIdDevice() != null && !"".equalsIgnoreCase(searchUsers.getIdDevice())) {
                hsCriteria.put("id_device", searchUsers.getIdDevice());
            }

            if (searchUsers.getFlag() != null && !"".equalsIgnoreCase(searchUsers.getFlag())) {
                if ("N".equalsIgnoreCase(searchUsers.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchUsers.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImUsers> listOfUsers = null;
            try {
                listOfUsers = userDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUsers != null) {
                User resultUsers;
                for (ImUsers imUsers : listOfUsers) {

                    if (searchUsers.getRoleId() != null && !"".equalsIgnoreCase(searchUsers.getRoleId())) {

                        if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {
                            List<ImRoles> listOfImRoles = new ArrayList<ImRoles>(imUsers.getImRoles());
                            ImRoles itemImRoles = listOfImRoles.get(0);
                            if (itemImRoles.getRoleId().toString().equalsIgnoreCase(searchUsers.getRoleId())) { //jika search berdasarkan role

                                List<ImAreasBranchesUsers> imAreasBranchesUsersList = new ArrayList<ImAreasBranchesUsers> (imUsers.getImAreasBranchesUsers());

                                if (imAreasBranchesUsersList!=null) {

                                    ImAreasBranchesUsers imAreasBranchesUsers = imAreasBranchesUsersList.get(0);

                                    if (searchUsers.getAreaId() != null && !"".equalsIgnoreCase(searchUsers.getAreaId())) {

                                        if (imAreasBranchesUsers.getImArea().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getAreaId())) { //jika search berdasarkan area

                                            if (searchUsers.getBranchId() != null && !"".equalsIgnoreCase(searchUsers.getBranchId())) {

                                                if (imAreasBranchesUsers.getImBranch().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getBranchId())) { //jika search berdasarkan unit

                                                    resultUsers = new User();
                                                    resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                                    resultUsers.setUsername(imUsers.getUserName());
                                                    resultUsers.setPassword(imUsers.getPassword());
                                                    resultUsers.setEmail(imUsers.getEmail());

                                                    resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                                    resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                                    resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                                    resultUsers.setRoleName(itemImRoles.getRoleName());

                                                    resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                                    resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                                    resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                                    resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                                    resultUsers.setFlag(imUsers.getFlag());
                                                    resultUsers.setAction(imUsers.getAction());
                                                    resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                                    resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                                    resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                                    resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                                    StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                                    imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                                    imageUpload.append(CommonUtil.getUploadFolderValue() + CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                                    if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                        imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                                    } else {
                                                        imageUpload.append(imUsers.getPhotoUrl());
                                                    }
                                                    imageUpload.append("\" border=\"none\" >");

                                                    resultUsers.setPreviewPhoto(imageUpload.toString());

                                                    listOfResultUsers.add(resultUsers);

                                                }

                                            } else {

                                                resultUsers = new User();
                                                resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                                resultUsers.setUsername(imUsers.getUserName());
                                                resultUsers.setPassword(imUsers.getPassword());
                                                resultUsers.setEmail(imUsers.getEmail());

                                                resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                                resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                                resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                                resultUsers.setRoleName(itemImRoles.getRoleName());

                                                resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                                resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                                resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                                resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                                resultUsers.setFlag(imUsers.getFlag());
                                                resultUsers.setAction(imUsers.getAction());
                                                resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                                resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                                resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                                resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                                StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                                imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                                if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                    imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                                } else {
                                                    imageUpload.append(imUsers.getPhotoUrl());
                                                }
                                                imageUpload.append("\" border=\"none\" >");

                                                resultUsers.setPreviewPhoto(imageUpload.toString());

                                                listOfResultUsers.add(resultUsers);
                                            }

                                        }

                                    } else {

                                        if (searchUsers.getBranchId() != null && !"".equalsIgnoreCase(searchUsers.getBranchId())) {
                                            if (imAreasBranchesUsers.getImBranch().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getBranchId())) { //jika search berdasarkan unit
                                                resultUsers = new User();
                                                resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                                resultUsers.setUsername(imUsers.getUserName());
                                                resultUsers.setPassword(imUsers.getPassword());
                                                resultUsers.setEmail(imUsers.getEmail());

                                                resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                                resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                                resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                                resultUsers.setRoleName(itemImRoles.getRoleName());

                                                resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                                resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                                resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                                resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                                resultUsers.setFlag(imUsers.getFlag());
                                                resultUsers.setAction(imUsers.getAction());
                                                resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                                resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                                resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                                resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                                StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                                imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                                if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                    imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                                } else {
                                                    imageUpload.append(imUsers.getPhotoUrl());
                                                }
                                                imageUpload.append("\" border=\"none\" >");

                                                resultUsers.setPreviewPhoto(imageUpload.toString());

                                                listOfResultUsers.add(resultUsers);
                                            }
                                        } else {

                                            resultUsers = new User();
                                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                            resultUsers.setUsername(imUsers.getUserName());
                                            resultUsers.setPassword(imUsers.getPassword());
                                            resultUsers.setEmail(imUsers.getEmail());

                                            resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                            resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                            resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                            resultUsers.setRoleName(itemImRoles.getRoleName());

                                            resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                            resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                            resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                            resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                            resultUsers.setFlag(imUsers.getFlag());
                                            resultUsers.setAction(imUsers.getAction());
                                            resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                            resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                            resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                            resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                            StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                            imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                            imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                            if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                            } else {
                                                imageUpload.append(imUsers.getPhotoUrl());
                                            }
                                            imageUpload.append("\" border=\"none\" >");

                                            resultUsers.setPreviewPhoto(imageUpload.toString());

                                            listOfResultUsers.add(resultUsers);

                                        }

                                    }

                                }

                            }
                        }

                    } else {

                        if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {
                            List<ImRoles> listOfImRoles = new ArrayList<ImRoles>(imUsers.getImRoles());
                            ImRoles itemImRoles = listOfImRoles.get(0);

                            List<ImAreasBranchesUsers> imAreasBranchesUsersList = new ArrayList<ImAreasBranchesUsers> (imUsers.getImAreasBranchesUsers());

                            if (imAreasBranchesUsersList!=null) {

                                ImAreasBranchesUsers imAreasBranchesUsers = imAreasBranchesUsersList.get(0);

                                if (searchUsers.getAreaId() != null && !"".equalsIgnoreCase(searchUsers.getAreaId())) {

                                    if (imAreasBranchesUsers.getImArea().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getAreaId())) { //jika search berdasarkan area

                                        if (searchUsers.getBranchId() != null && !"".equalsIgnoreCase(searchUsers.getBranchId())) {

                                            if (imAreasBranchesUsers.getImBranch().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getBranchId())) { //jika search berdasarkan unit

                                                resultUsers = new User();
                                                resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                                resultUsers.setUsername(imUsers.getUserName());
                                                resultUsers.setPassword(imUsers.getPassword());
                                                resultUsers.setEmail(imUsers.getEmail());

                                                resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                                resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                                resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                                resultUsers.setRoleName(itemImRoles.getRoleName());

                                                resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                                resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                                resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                                resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                                resultUsers.setFlag(imUsers.getFlag());
                                                resultUsers.setAction(imUsers.getAction());
                                                resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                                resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                                resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                                resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                                StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                                imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                                if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                    imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                                } else {
                                                    imageUpload.append(imUsers.getPhotoUrl());
                                                }
                                                imageUpload.append("\" border=\"none\" >");

                                                resultUsers.setPreviewPhoto(imageUpload.toString());

                                                listOfResultUsers.add(resultUsers);

                                            }

                                        } else {

                                            resultUsers = new User();
                                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                            resultUsers.setUsername(imUsers.getUserName());
                                            resultUsers.setPassword(imUsers.getPassword());
                                            resultUsers.setEmail(imUsers.getEmail());

                                            resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                            resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                            resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                            resultUsers.setRoleName(itemImRoles.getRoleName());

                                            resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                            resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                            resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                            resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                            resultUsers.setFlag(imUsers.getFlag());
                                            resultUsers.setAction(imUsers.getAction());
                                            resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                            resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                            resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                            resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                            StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                            imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                            imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                            if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                            } else {
                                                imageUpload.append(imUsers.getPhotoUrl());
                                            }
                                            imageUpload.append("\" border=\"none\" >");

                                            resultUsers.setPreviewPhoto(imageUpload.toString());

                                            listOfResultUsers.add(resultUsers);
                                        }

                                    }

                                } else {

                                    if (searchUsers.getBranchId() != null && !"".equalsIgnoreCase(searchUsers.getBranchId())) {
                                        if (imAreasBranchesUsers.getImBranch().getPrimaryKey().getId().equalsIgnoreCase(searchUsers.getBranchId())) { //jika search berdasarkan unit
                                            resultUsers = new User();
                                            resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                            resultUsers.setUsername(imUsers.getUserName());
                                            resultUsers.setPassword(imUsers.getPassword());
                                            resultUsers.setEmail(imUsers.getEmail());
                                            if(imUsers.getDivisiId() != null){
                                                resultUsers.setDivisiId(imUsers.getDivisiId());
                                                resultUsers.setDivisiName(imUsers.getImDepartmentEntity().getDepartmentName());
                                            }
                                            else{
                                                resultUsers.setDivisiName("");
                                                resultUsers.setDivisiId("");
                                            }

                                            resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                            resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                            resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                            resultUsers.setRoleName(itemImRoles.getRoleName());

                                            resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                            resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                            resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                            resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                            resultUsers.setFlag(imUsers.getFlag());
                                            resultUsers.setAction(imUsers.getAction());
                                            resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                            resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                            resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                            resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                            StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                                            imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                            imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                            if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                                imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                            } else {
                                                imageUpload.append(imUsers.getPhotoUrl());
                                            }
                                            imageUpload.append("\" border=\"none\" >");

                                            resultUsers.setPreviewPhoto(imageUpload.toString());

                                            listOfResultUsers.add(resultUsers);
                                        }
                                    } else {

                                        resultUsers = new User();
                                        resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                                        resultUsers.setUsername(imUsers.getUserName());
                                        resultUsers.setPassword(imUsers.getPassword());
                                        resultUsers.setEmail(imUsers.getEmail());
                                        if(imUsers.getDivisiId() != null){
                                            resultUsers.setDivisiName(imUsers.getImDepartmentEntity().getDepartmentName());
                                        }
                                        else{
                                            resultUsers.setDivisiName("");
                                        }
                                        resultUsers.setDivisiId(imUsers.getDivisiId());

                                        resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                                        resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                                        resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                                        resultUsers.setRoleName(itemImRoles.getRoleName());

                                        resultUsers.setAreaId(imAreasBranchesUsers.getImArea().getPrimaryKey().getId());
                                        resultUsers.setAreaName(imAreasBranchesUsers.getImArea().getAreaName());
                                        resultUsers.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                                        resultUsers.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());

                                        resultUsers.setFlag(imUsers.getFlag());
                                        resultUsers.setAction(imUsers.getAction());
                                        resultUsers.setCreatedDate(imUsers.getCreatedDate());
                                        resultUsers.setLastUpdate(imUsers.getLastUpdate());
                                        resultUsers.setCreatedWho(imUsers.getCreatedWho());
                                        resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());


                                        StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg img-cirle \" width='50px' src=\"");
                                        imageUpload.append(ServletActionContext.getRequest().getContextPath());
                                        imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                                        if (imUsers.getPhotoUrl() == null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                                            imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                                        } else {
                                            imageUpload.append(imUsers.getPhotoUrl());
                                        }
                                        imageUpload.append("\" border=\"none\" >");

                                        resultUsers.setPreviewPhoto(imageUpload.toString());

                                        listOfResultUsers.add(resultUsers);

                                    }

                                }
                            }

                        }
                    }
                }
            }
        }

        logger.info("[UserBoImpl.getByCriteria] end process <<<");

        return listOfResultUsers;
    }

    public User saveAdd(User addUsers) throws GeneralBOException {

        logger.info("[UserBoImpl.saveAdd] start process >>>");

        if (addUsers != null) {

            String userId = addUsers.getUserId();
            String branchId = addUsers.getBranchId();
            String areaId = addUsers.getAreaId();

            //cek if found same id, then throws exeception
            ImUsersPK primaryKey = new ImUsersPK();
            primaryKey.setId(userId);

            ImUsers imUsersCheck = null;
            try {
                imUsersCheck = userDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data user by id, please inform to your admin...," + e.getMessage());
            }

            if (imUsersCheck==null) { //if not exist in table user then save this record

                //save users table imUsers
                ImUsers imUsersNew = new ImUsers();
                ImUsersPK imUsersPK = new ImUsersPK();
                imUsersPK.setId(addUsers.getUserId());

                imUsersNew.setPrimaryKey(imUsersPK);
                imUsersNew.setUserName(addUsers.getUsername());
                imUsersNew.setPassword(addUsers.getPassword());
                imUsersNew.setEmail(addUsers.getEmail());
//                imUsersNew.setPhoto(addUsers.getContentFile());
                imUsersNew.setPhotoUrl(addUsers.getPhotoUserUrl());
                imUsersNew.setPositionId(String.valueOf(addUsers.getPositionId()));
                imUsersNew.setCreatedDate(addUsers.getCreatedDate());
                if(imUsersNew.getDivisiId() != null){
                    imUsersNew.setDivisiId(addUsers.getDivisiId());
                }
                imUsersNew.setCreatedWho(addUsers.getCreatedWho());
                imUsersNew.setLastUpdate(addUsers.getLastUpdate());
                imUsersNew.setLastUpdateWho(addUsers.getLastUpdateWho());
                imUsersNew.setAction(addUsers.getAction());
                imUsersNew.setFlag("Y");

                //sodiq, 10/12/2019, penambahan id pelayanan
                imUsersNew.setIdPelayanan(addUsers.getIdPelayanan());

                String userid = addUsers.getUserId();
                boolean isAda ;

                try {
                    isAda=userDao.isExistUser(userid);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveAddFunctions] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when checking exist function, please info to your admin..." + e.getMessage());
                }

                try {
                    userDao.addAndSave(imUsersNew);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data users, please info to your admin..." + e.getMessage());
                }

                //save users table imUsersRoles
                ImUsersRoles imUsersRolesNew = new ImUsersRoles();
                ImUsersRolesPK imUsersRolesPK = new ImUsersRolesPK();
                imUsersRolesPK.setUserId(addUsers.getUserId());
                imUsersRolesPK.setRoleId(Long.valueOf(addUsers.getRoleId()));

                imUsersRolesNew.setPrimaryKey(imUsersRolesPK);
                imUsersRolesNew.setCreatedDate(addUsers.getCreatedDate());
                imUsersRolesNew.setCreatedWho(addUsers.getCreatedWho());
                imUsersRolesNew.setLastUpdate(addUsers.getLastUpdate());
                imUsersRolesNew.setLastUpdateWho(addUsers.getLastUpdateWho());
                imUsersRolesNew.setFlag("Y");

                try {
                    userRoleDao.addAndSave(imUsersRolesNew);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data users roles, please info to your admin..." + e.getMessage());
                }

                //save uers into table im_areas_branches_users, update by ferdi, 10-10-2016
                ImAreasBranchesUsers imAreasBranchesUsers = new ImAreasBranchesUsers();
                ImAreasBranchesUsersPK imAreasBranchesUsersPK = new ImAreasBranchesUsersPK();
//                imAreasBranchesUsersPK.setUserId(userId);
//                imAreasBranchesUsersPK.setAreaId(areaId);
//                imAreasBranchesUsersPK.setBranchId(branchId);

                //sodiq, 10/12/2019, penambahan user id, area id, dan branch id form front end
                imAreasBranchesUsersPK.setUserId(addUsers.getUserId());
                imAreasBranchesUsersPK.setAreaId(addUsers.getAreaId());
                imAreasBranchesUsersPK.setBranchId(addUsers.getBranchId());

                imAreasBranchesUsers.setPrimaryKey(imAreasBranchesUsersPK);
                imAreasBranchesUsers.setCreatedDate(addUsers.getCreatedDate());
                imAreasBranchesUsers.setCreatedWho(addUsers.getCreatedWho());
                imAreasBranchesUsers.setLastUpdate(addUsers.getLastUpdate());
                imAreasBranchesUsers.setLastUpdateWho(addUsers.getLastUpdateWho());
                imAreasBranchesUsers.setFlag("Y");

                try {
                    areasBranchesUsersDao.addAndSave(imAreasBranchesUsers);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data area branch users, please info to your admin..." + e.getMessage());
                }


            } else { //throw exception if found existing user
                logger.error("[UserBoImpl.saveAdd] Error, Found problem when saving new data users, cause have similiar userId in database, please retry new user or info to your admin.");
                throw new GeneralBOException("Found problem when saving new data users, cause have similiar userId in database, please retry new user or info to your admin.");
            }

        }

        logger.info("[UserBoImpl.saveAdd] end process <<<");

        return addUsers;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

        Long result = GenerateBoLog.generateBoLog(userDao, message, moduleMethod);

        return result;
    }

    @Override
    public void saveEditIdDevice(User user) throws GeneralBOException {
        logger.info("[UserBoImpl.saveEdit] start process >>>");
        if (user != null) {


            ImUsersPK primaryKey = new ImUsersPK();
            primaryKey.setId(user.getUserId());

            ImUsers imUsersOld = null;
            try {
                imUsersOld = userDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.saveEditIdDevice] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving edit data users, please info to your admin..." + e.getMessage());
            }

            imUsersOld.setIdDevice(user.getIdDevice());
            imUsersOld.setLastUpdate(user.getLastUpdate());
            imUsersOld.setLastUpdateWho(user.getLastUpdateWho());

            try {
                userDao.updateAndSave(imUsersOld);
            } catch (GeneralBOException e){
                logger.error("[UserBoImpl.saveEditIdDevice] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving edit data users, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[UserBoImpl.saveEdit] start process >>>");
    }

    public void saveEdit(User usersNew) throws GeneralBOException {

        logger.info("[UserBoImpl.saveEdit] start process >>>");

        if (usersNew != null) {

            //retrieve last data by id
            String userId = usersNew.getUserId();
            String areaId = usersNew.getAreaId();
            String branchId = usersNew.getBranchId();
            String positionId = usersNew.getPositionId();

            ImUsersPK primaryKey = new ImUsersPK();
            primaryKey.setId(userId);

            ImUsers imUsersOld = null;
            try {
                imUsersOld = userDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving edit data users, please info to your admin..." + e.getMessage());
            }

            if (imUsersOld != null) {

                //update by ferdi, 12-12-2016, check if position user old is not equal new position and check branch old is not equal then new branch ( change person from pg to other pg )
                String positionIdOld = imUsersOld.getPositionId();
                String sPositionIdOld = positionIdOld.toString();

                List<ImAreasBranchesUsers> imAreasBranchesUsersListOld = new ArrayList<ImAreasBranchesUsers> (imUsersOld.getImAreasBranchesUsers());
                ImAreasBranchesUsers imAreasBranchesUsersOld = imAreasBranchesUsersListOld.get(0);
                String branchIdOld = imAreasBranchesUsersOld.getPrimaryKey().getBranchId();

                if (!branchIdOld.equalsIgnoreCase(branchId) || !sPositionIdOld.equalsIgnoreCase(positionId)) {

                    //jika asmud maka akan dicek transaksi list task dan notification
                    //jika opsgps maka akna dicek transaksi list task
                    //jika asman maka akan dicek transaksi approval kontrak dan cetak kontrak
                    //jika mantan maka akan dicek transaksi pembatalan kontrak

                    if (sPositionIdOld.equalsIgnoreCase("1")) { // mantan



                    } else if (sPositionIdOld.equalsIgnoreCase("8")) { //asman


                    } else if (sPositionIdOld.equalsIgnoreCase("7")) { //asmud



                    } else if (sPositionIdOld.equalsIgnoreCase("4")) { //ops gps


                    }

                }

                // move last data to table history
                ImUsersHistory imUsersDeactive = new ImUsersHistory();
                try {
                    BeanUtils.copyProperties(imUsersDeactive, imUsersOld);
                } catch (IllegalAccessException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object imUsersOld to imUsersDeactive, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping data object imUsersOld to imUsersDeactive, please info to your admin..." + e.getMessage());
                }

                imUsersDeactive.setUserId(imUsersOld.getPrimaryKey().getId());

                //save deactive to history user
                try {
                    userDao.addAndSaveHistory(imUsersDeactive);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data user, please info to your admin..." + e.getMessage());
                }

                //set updated data to im_users
                primaryKey = imUsersOld.getPrimaryKey();
                primaryKey.setId(usersNew.getUserId());
                imUsersOld.setPrimaryKey(primaryKey);
                imUsersOld.setUserName(usersNew.getUsername());
                imUsersOld.setPassword(usersNew.getPassword());
                imUsersOld.setEmail(usersNew.getEmail());
//                if (usersNew.getContentFile()!=null) imUsersOld.setPhoto(usersNew.getContentFile());
                if (usersNew.getPhotoUserUrl()!=null) imUsersOld.setPhotoUrl(usersNew.getPhotoUserUrl());
                imUsersOld.setPositionId(String.valueOf(usersNew.getPositionId()));
                imUsersOld.setLastUpdate(usersNew.getLastUpdate());
                imUsersOld.setLastUpdateWho(usersNew.getLastUpdateWho());
                imUsersOld.setAction(usersNew.getAction());
                try {
                    userDao.updateAndSave(imUsersOld);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data user, please info to your admin..." + e.getMessage());
                }

                // if no found data then create new data

                ImUsersRolesPK primaryKeyUserRole = new ImUsersRolesPK();
                primaryKeyUserRole.setUserId(userId);
                primaryKeyUserRole.setRoleId(Long.valueOf(usersNew.getRoleId()));

                ImUsersRoles imUsersRolesOld = null;
                try {
                    imUsersRolesOld = userRoleDao.getByCompositeKey(primaryKeyUserRole, "Y");
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving delete data users, please info to your admin..." + e.getMessage());
                }

                if (imUsersRolesOld==null) {

                    //update old to be N, and add new data
                    //deactive old data, set Flag = N
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("user_id",userId);
                    hsCriteria.put("flag","Y");

                    List<ImUsersRoles> listOfImUsersRoleses = new ArrayList<ImUsersRoles>();
                    try {
                        listOfImUsersRoleses = userRoleDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data users roles, please info to your admin..." + e.getMessage());
                    }

                    for (ImUsersRoles imUsersRoles : listOfImUsersRoleses) {

                        imUsersRoles.setLastUpdate(usersNew.getLastUpdate());
                        imUsersRoles.setLastUpdateWho(usersNew.getLastUpdateWho());
                        imUsersRoles.setFlag("N");

                        try {
                            userRoleDao.updateAndSave(imUsersRoles);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving deactive data user-role, please info to your admin..." + e.getMessage());
                        }
                    }

                    //create new data
                    ImUsersRoles imUsersRolesNew = new ImUsersRoles();
                    primaryKeyUserRole = new ImUsersRolesPK();
                    primaryKeyUserRole.setUserId(userId);
                    primaryKeyUserRole.setRoleId(Long.valueOf(usersNew.getRoleId()));
                    imUsersRolesNew.setPrimaryKey(primaryKeyUserRole);
                    imUsersRolesNew.setCreatedDate(usersNew.getLastUpdate());
                    imUsersRolesNew.setCreatedWho(usersNew.getLastUpdateWho());
                    imUsersRolesNew.setLastUpdate(usersNew.getLastUpdate());
                    imUsersRolesNew.setLastUpdateWho(usersNew.getLastUpdateWho());
                    imUsersRolesNew.setFlag("Y");

                    try {
                        userRoleDao.addAndSave(imUsersRolesNew);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data users roles, please info to your admin..." + e.getMessage());
                    }

                }

                //update im_areas_branches_users, if old same new one, skip, otherwise then non active old, and create new, update by ferdi, 10-10-2016
                Map hsCriteria = new HashMap();
                hsCriteria.put("user_id",userId);
                hsCriteria.put("area_id",areaId);
                hsCriteria.put("branch_id",branchId);
                hsCriteria.put("flag","Y");

                List<ImAreasBranchesUsers> imAreasBranchesUsersList = null;
                try {
                    imAreasBranchesUsersList = areasBranchesUsersDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data users, please info to your admin..." + e.getMessage());
                }

                if (imAreasBranchesUsersList.isEmpty()) {

                    //update old to be N, and add new data
                    //deactive old data, set Flag = N
                    hsCriteria = new HashMap();
                    hsCriteria.put("user_id", userId);
                    hsCriteria.put("flag", "Y");

                    imAreasBranchesUsersList = null;
                    try {
                        imAreasBranchesUsersList = areasBranchesUsersDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data area branch users, please info to your admin..." + e.getMessage());
                    }

                    for (ImAreasBranchesUsers imAreasBranchesUsers : imAreasBranchesUsersList) {

                        imAreasBranchesUsers.setLastUpdate(usersNew.getLastUpdate());
                        imAreasBranchesUsers.setLastUpdateWho(usersNew.getLastUpdateWho());
                        imAreasBranchesUsers.setFlag("N");

                        try {
                            areasBranchesUsersDao.updateAndSave(imAreasBranchesUsers);
                        } catch (HibernateException e) {
                            logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving deactive data area-branch-user, please info to your admin..." + e.getMessage());
                        }
                    }

                    ImAreasBranchesUsers imAreasBranchesUsers = new ImAreasBranchesUsers();
                    ImAreasBranchesUsersPK imAreasBranchesUsersPK = new ImAreasBranchesUsersPK();
                    imAreasBranchesUsersPK.setUserId(userId);
                    imAreasBranchesUsersPK.setAreaId(areaId);
                    imAreasBranchesUsersPK.setBranchId(branchId);

                    imAreasBranchesUsers.setPrimaryKey(imAreasBranchesUsersPK);
                    imAreasBranchesUsers.setCreatedDate(usersNew.getLastUpdate());
                    imAreasBranchesUsers.setCreatedWho(usersNew.getLastUpdateWho());
                    imAreasBranchesUsers.setLastUpdate(usersNew.getLastUpdate());
                    imAreasBranchesUsers.setLastUpdateWho(usersNew.getLastUpdateWho());
                    imAreasBranchesUsers.setFlag("Y");

                    try {
                        areasBranchesUsersDao.addAndSave(imAreasBranchesUsers);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data area-branch-users, please info to your admin..." + e.getMessage());
                    }
                }


            } else {
                logger.error("[UserBoImpl.saveEdit] Error, Found problem when saving update data users, cause no have userId in database, please info to your admin.");
                throw new GeneralBOException("Found problem when saving new data users, cause no have userId in database, please info to your admin.");
            }

        } else {
            logger.error("[UserBoImpl.saveEdit] Error, Found problem when saving update data users, cause no have userId, please info to your admin.");
            throw new GeneralBOException("Found problem when saving new data users, cause np have userId, please info to your admin.");
        }

        logger.info("[UserBoImpl.saveEdit] end process <<<");
    }

    public void saveDelete(User usersDelete) throws GeneralBOException {

        logger.info("[UserBoImpl.saveDelete] start process >>>");

        if (usersDelete != null) {

            String userId = usersDelete.getUserId();
            ImUsersPK primaryKey = new ImUsersPK();
            primaryKey.setId(userId);

            ImUsers imUsersOld = null;
            try {
                imUsersOld = userDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data users, please info to your admin..." + e.getMessage());
            }

            if (imUsersOld != null) {

                Map hsCriteria = new HashMap();
                hsCriteria.put("user_id",userId);
                hsCriteria.put("flag","Y");

                //cek into im_areas_branches_users
                ImAreasBranchesUsers imAreasBranchesUsers = null;
                try {
                    imAreasBranchesUsers = areasBranchesUsersDao.getAreasBranchesUsersByUserId(userId,"Y");
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving delete data users, please info to your admin..." + e.getMessage());
                }

                if (imAreasBranchesUsers!=null) {

                    imAreasBranchesUsers.setFlag("N");
                    imAreasBranchesUsers.setLastUpdate(usersDelete.getLastUpdate());
                    imAreasBranchesUsers.setLastUpdateWho(usersDelete.getLastUpdateWho());

                    try {
                        areasBranchesUsersDao.updateAndSave(imAreasBranchesUsers);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving deactive data ara-branch-user, please info to your admin..." + e.getMessage());
                    }

                }

                List<ImRoles> listOfImRoles = new ArrayList<ImRoles>(imUsersOld.getImRoles());
                ImRoles itemImRoles = listOfImRoles.get(0);

                ImUsersRolesPK primaryKeyUserRole = new ImUsersRolesPK();
                primaryKeyUserRole.setUserId(userId);
                primaryKeyUserRole.setRoleId(itemImRoles.getRoleId());

                ImUsersRoles imUsersRolesOld = null;
                try {
                    imUsersRolesOld = userRoleDao.getByCompositeKey(primaryKeyUserRole, "Y");
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving delete data users, please info to your admin..." + e.getMessage());
                }

                if (imUsersRolesOld!=null) {

                    imUsersRolesOld.setFlag("N");
                    imUsersRolesOld.setLastUpdate(usersDelete.getLastUpdate());
                    imUsersRolesOld.setLastUpdateWho(usersDelete.getLastUpdateWho());

                    try {
                        userRoleDao.updateAndSave(imUsersRolesOld);
                    } catch (HibernateException e) {
                        logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving deactive data user-role, please info to your admin..." + e.getMessage());
                    }

                }

                imUsersOld.setFlag("N");
                imUsersOld.setLastUpdate(usersDelete.getLastUpdate());
                imUsersOld.setLastUpdateWho(usersDelete.getLastUpdateWho());

                try {
                    userDao.updateAndSave(imUsersOld);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving deactive data user, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[UserBoImpl.saveDelete] Error, Found problem when deleting data users, cause no have userId in database, please info to your admin.");
                throw new GeneralBOException("Found problem when deleting data users, cause np have userId in database, please info to your admin.");
            }

        } else {
            logger.error("[UserBoImpl.saveDelete] Error, Found problem when deleting data users, cause no have userId in database, please info to your admin.");
            throw new GeneralBOException("Found problem when deleting data users, cause np have userId in database, please info to your admin.");
        }

        logger.info("[UserBoImpl.saveDelete] end process <<<");
    }

    public void saveEditPassword(User usersNew) throws GeneralBOException {

        logger.info("[UserBoImpl.saveEditPassword] start process >>>");

        if (usersNew != null) {

            //retrieve last data by id
            String userId = usersNew.getUserId();

            ImUsersPK primaryKey = new ImUsersPK();
            primaryKey.setId(userId);

            ImUsers imUsersOld = null;
            try {
                imUsersOld = userDao.getById(primaryKey, "Y");
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving edit data users, please info to your admin..." + e.getMessage());
            }

            if (imUsersOld != null) {

                imUsersOld.setUserName(usersNew.getUsername());
                if (usersNew.getPhotoUserUrl()!=null) imUsersOld.setPhotoUrl(usersNew.getPhotoUserUrl());

                imUsersOld.setPassword(usersNew.getPassword());
                imUsersOld.setLastUpdate(usersNew.getLastUpdate());
                imUsersOld.setLastUpdateWho(usersNew.getLastUpdateWho());
                imUsersOld.setAction(usersNew.getAction());
                try {
                    userDao.updateAndSave(imUsersOld);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.saveEditPassword] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data user, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[UserBoImpl.saveEditPassword] Error, Found problem when saving update data users, cause no have userId in database, please info to your admin.");
                throw new GeneralBOException("Found problem when saving new data users, cause no have userId in database, please info to your admin.");
            }

        } else {
            logger.error("[UserBoImpl.saveEditPassword] Error, Found problem when saving update data users, cause no have userId, please info to your admin.");
            throw new GeneralBOException("Found problem when saving new data users, cause np have userId, please info to your admin.");
        }

        logger.info("[UserBoImpl.saveEditPassword] end process <<<");
    }

    public User getUserById(String userId, String flag) throws GeneralBOException {

        logger.info("[UserBoImpl.getUserById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImUsersPK primaryKey=new ImUsersPK();
        primaryKey.setId(userId);

        ImUsers imUsers = null;
        try {
            imUsers = userDao.getById(primaryKey, getFlag);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getUserById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving departement based on userId and flag, please info to your admin..." + e.getMessage());
        }

        User resultUsers = new User();
        if (imUsers != null) {

            if (imUsers.getImRoles()!=null && !imUsers.getImRoles().isEmpty()) {
                List<ImRoles> listOfImRoles = new ArrayList<ImRoles>(imUsers.getImRoles());
                ImRoles itemImRoles = listOfImRoles.get(0);
                resultUsers = new User();

                resultUsers.setUserId(imUsers.getPrimaryKey().getId());
                resultUsers.setUsername(imUsers.getUserName());
                resultUsers.setPassword(imUsers.getPassword());
                resultUsers.setEmail(imUsers.getEmail());

                resultUsers.setPositionId(imUsers.getImPosition().getPositionId().toString());
                resultUsers.setPositionName(imUsers.getImPosition().getPositionName());

                resultUsers.setRoleId(itemImRoles.getRoleId().toString());
                resultUsers.setRoleName(itemImRoles.getRoleName());

                resultUsers.setFlag(imUsers.getFlag());
                resultUsers.setCreatedDate(imUsers.getCreatedDate());
                resultUsers.setLastUpdate(imUsers.getLastUpdate());
                resultUsers.setCreatedWho(imUsers.getCreatedWho());
                resultUsers.setLastUpdateWho(imUsers.getLastUpdateWho());

                StringBuffer imageUpload = new StringBuffer("<img border=\"0\" class=\"circularDetail centerImg\" src=\"");
                imageUpload.append(ServletActionContext.getRequest().getContextPath());
                imageUpload.append(CommonConstant.RESOURCE_PATH_USER_UPLOAD);
                if (imUsers.getPhotoUrl()==null || "".equalsIgnoreCase(imUsers.getPhotoUrl())) {
                    imageUpload.append(CommonConstant.RESOURCE_PATH_DEFAULT_USER_PHOTO_MINI);
                } else {
                    imageUpload.append(imUsers.getPhotoUrl());
                }
                imageUpload.append("\" border=\"none\" >");

                resultUsers.setPreviewPhoto(imageUpload.toString());

            }
        }

        logger.info("[UserBoImpl.getUserById] end process <<<");

        return resultUsers;
    }

    public List<User> getDataUser(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<User> listComboUser = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> listUser = null;

        try {
            listUser = userDao.getListUser2(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listUser != null) {
            for (ImUsers imUsers : listUser) {
                List<ImAreasBranchesUsers> imAreasBranchesUsersList = new ArrayList<ImAreasBranchesUsers> (imUsers.getImAreasBranchesUsers());
                ImAreasBranchesUsers imAreasBranchesUsers = imAreasBranchesUsersList.get(0);
                User itemComboUser = new User();
                itemComboUser.setUserId(imUsers.getPrimaryKey().getId());
                itemComboUser.setUsername(imUsers.getUserName());
                itemComboUser.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                if(imUsers.getImDepartmentEntity() != null){
                    itemComboUser.setDivisiId(imUsers.getImDepartmentEntity().getDepartmentId());
                }
                itemComboUser.setPositionId(imUsers.getImPosition().getPositionId().toString());
                listComboUser.add(itemComboUser);
            }
        }


        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboUser;
    }

    public List<User>getComboUserWithCriteria(String query, String Branch, String Divisi) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<User> listComboUser = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> listUser = null;

        try {
            listUser = userDao.getListUser(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listUser != null) {
            for (ImUsers imUsers : listUser) {
                List<ImAreasBranchesUsers> imAreasBranchesUsersList = new ArrayList<ImAreasBranchesUsers> (imUsers.getImAreasBranchesUsers());
                if (imUsers.getItPersonilPositionEntity() !=null) {
                    ImAreasBranchesUsers imAreasBranchesUsers = imAreasBranchesUsersList.get(0);
                    User itemComboUser = new User();
                    if(!Branch.equals("") && !Divisi.equals("")){
                        if(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId().equals(Branch) && imUsers.getImDepartmentEntity().getDepartmentId().equals(Divisi)){
                            itemComboUser.setUserId(imUsers.getPrimaryKey().getId());
                            itemComboUser.setUsername(imUsers.getImBiodataEntity().getNamaPegawai());
                            itemComboUser.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                            itemComboUser.setDivisiId(imUsers.getImDepartmentEntity().getDepartmentId());
                            itemComboUser.setPositionId(imUsers.getImPosition().getPositionId().toString());
                            listComboUser.add(itemComboUser);
                        }
                    }else{
                        itemComboUser.setUserId(imUsers.getPrimaryKey().getId());
                        itemComboUser.setUsername(imUsers.getImBiodataEntity().getNamaPegawai());
                        itemComboUser.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                        itemComboUser.setDivisiId(imUsers.getImPosition().getDepartmentId());
                        itemComboUser.setPositionId(imUsers.getImPosition().getPositionId().toString());
                        listComboUser.add(itemComboUser);
                    }
                }
            }
        }


        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboUser;
    }


    public List<ErrorLog> getErrorLogByCriteria(ErrorLog searchErrorLog) throws GeneralBOException {

        logger.info("[UserBoImpl.getErrorLogByCriteria] start process >>>");

        List<ErrorLog> listOfResultErrorLog = new ArrayList();

        if (searchErrorLog != null) {
            Map hsCriteria = new HashMap();
            if (searchErrorLog.getErrorId() != null && !"".equalsIgnoreCase(searchErrorLog.getErrorId())) {
                hsCriteria.put("error_id", Long.valueOf(searchErrorLog.getErrorId()));
            }

            if (searchErrorLog.getModuleMethod() != null && !"".equalsIgnoreCase(searchErrorLog.getModuleMethod())) {
                hsCriteria.put("module_method", searchErrorLog.getModuleMethod());
            }

            if (searchErrorLog.getMessage() != null && !"".equalsIgnoreCase(searchErrorLog.getMessage())) {
                hsCriteria.put("message", searchErrorLog.getMessage());
            }

            if (searchErrorLog.getUserId() != null && !"".equalsIgnoreCase(searchErrorLog.getUserId())) {
                hsCriteria.put("user_id", searchErrorLog.getUserId());
            }

            if (searchErrorLog.getBranchId() != null && !"".equalsIgnoreCase(searchErrorLog.getBranchId())) {
                hsCriteria.put("branch_id", searchErrorLog.getBranchId());
            }

            if (searchErrorLog.getErrorTimestampFrom() != null) {
                hsCriteria.put("error_date_from", searchErrorLog.getErrorTimestampFrom());
            }

            if (searchErrorLog.getErrorTimestampTo() != null) {
                hsCriteria.put("error_date_to", searchErrorLog.getErrorTimestampTo());
            }

            List<ItBusinessObjectLog> listOfErrorLog = null;
            try {
                listOfErrorLog = errorLogDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getErrorLogByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfErrorLog != null) {
                ErrorLog resultErrorLog;
                for (ItBusinessObjectLog itBusinessObjectLog : listOfErrorLog) {

                    resultErrorLog = new ErrorLog();

                    resultErrorLog.setId(itBusinessObjectLog.getId());
                    resultErrorLog.setErrorId(itBusinessObjectLog.getId().toString());
                    resultErrorLog.setErrorTimestamp(itBusinessObjectLog.getErrorTimestamp());
                    resultErrorLog.setModuleMethod(itBusinessObjectLog.getModuleMethod());
                    resultErrorLog.setMessage(itBusinessObjectLog.getMessage());
                    resultErrorLog.setUserId(itBusinessObjectLog.getUserId());
                    resultErrorLog.setBranchId(itBusinessObjectLog.getBranchId());
                    resultErrorLog.setStErrorTimestamp(CommonUtil.longDateFormat(itBusinessObjectLog.getErrorTimestamp()));

                    listOfResultErrorLog.add(resultErrorLog);
                }
            }
        }

        logger.info("[UserBoImpl.getErrorLogByCriteria] end process <<<");

        return listOfResultErrorLog;
    }

    public List<UserSessionLog> getUserSessionLogByCriteria(UserSessionLog searchUserSessionLog) throws GeneralBOException {

        logger.info("[UserBoImpl.getUserSessionLogByCriteria] start process >>>");

        List<UserSessionLog> listOfResultUserSessionLog = new ArrayList();

        if (searchUserSessionLog != null) {
            Map hsCriteria = new HashMap();
            if (searchUserSessionLog.getUserName() != null && !"".equalsIgnoreCase(searchUserSessionLog.getUserName())) {
                hsCriteria.put("user_name", searchUserSessionLog.getUserName());
            }

            if (searchUserSessionLog.getLoginTimestampFrom() != null) {
                hsCriteria.put("login_date_from", searchUserSessionLog.getLoginTimestampFrom());
            }

            if (searchUserSessionLog.getLoginTimestampTo() != null) {
                hsCriteria.put("login_date_to", searchUserSessionLog.getLoginTimestampTo());
            }

            if (searchUserSessionLog.getFlag() != null) {
                if ("Y".equalsIgnoreCase(searchUserSessionLog.getFlag())) {
                    hsCriteria.put("flag", "Y");
                } else {
                    hsCriteria.put("flag", "N");
                }
            }

            List<ItUserSessionLog> listOfUserSessionLog = null;
            try {
                listOfUserSessionLog = userSessionLogDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getUserSessionLogByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUserSessionLog != null) {
                UserSessionLog resultUserSessionLog;
                for (ItUserSessionLog itUserSessionLog : listOfUserSessionLog) {

                    resultUserSessionLog = new UserSessionLog();

                    resultUserSessionLog.setId(itUserSessionLog.getId());
                    resultUserSessionLog.setStId(itUserSessionLog.getId().toString());
                    resultUserSessionLog.setSessionId(itUserSessionLog.getSessionId());
                    resultUserSessionLog.setAreaName(itUserSessionLog.getAreaName());
                    resultUserSessionLog.setBranchName(itUserSessionLog.getBranchName());
                    resultUserSessionLog.setCompanyName(itUserSessionLog.getCompanyName());
                    resultUserSessionLog.setIpAddress(itUserSessionLog.getIpAddress());
                    resultUserSessionLog.setUserName(itUserSessionLog.getUserName());
                    resultUserSessionLog.setLoginTimestamp(itUserSessionLog.getLoginTimestamp());
                    resultUserSessionLog.setStLoginTimestamp(CommonUtil.longDateFormat(itUserSessionLog.getLoginTimestamp()));
                    resultUserSessionLog.setLogoutTimestamp(itUserSessionLog.getLogoutTimestamp());
                    resultUserSessionLog.setStLogoutTimestamp(itUserSessionLog.getLogoutTimestamp()!=null ? CommonUtil.longDateFormat(itUserSessionLog.getLogoutTimestamp()) : "" );

                    ImUsersPK usersPK = new ImUsersPK();
                    usersPK.setId(itUserSessionLog.getUserName());
                    ImUsers imUsers = userDao.getById(usersPK,"Y");

                    resultUserSessionLog.setName(imUsers.getUserName());

                    if (itUserSessionLog.getLogoutTimestamp()!=null) {
                        resultUserSessionLog.setEnabledKill(false);
                    } else {
                        resultUserSessionLog.setEnabledKill(true);
                    }

                    listOfResultUserSessionLog.add(resultUserSessionLog);
                }
            }
        }

        logger.info("[UserBoImpl.getUserSessionLogByCriteria] end process <<<");

        return listOfResultUserSessionLog;
    }

    public List<UserSessionLog> getUserSessionLogCount(UserSessionLog searchUserSessionLog) throws GeneralBOException {

        logger.info("[UserBoImpl.getUserSessionLogCount] start process >>>");

        List<UserSessionLog> listOfResultUserSessionLogCount = new ArrayList();

        if (searchUserSessionLog != null) {
            Map hsCriteria = new HashMap();
            if (searchUserSessionLog.getUserName() != null && !"".equalsIgnoreCase(searchUserSessionLog.getUserName())) {
                hsCriteria.put("user_name", searchUserSessionLog.getUserName());
            }

            if (searchUserSessionLog.getLoginTimestampFrom() != null) {
                hsCriteria.put("login_date_from", searchUserSessionLog.getLoginTimestampFrom());
            }

            if (searchUserSessionLog.getLoginTimestampTo() != null) {
                hsCriteria.put("login_date_to", searchUserSessionLog.getLoginTimestampTo());
            }

            List<ItUserSessionLog> listOfUserSessionLogCount = null;
            try {
                listOfUserSessionLogCount = userSessionLogDao.getByCount(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getUserSessionLogByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfUserSessionLogCount != null) {
                UserSessionLog resultUserSessionLog;
                for (ItUserSessionLog itUserSessionLog : listOfUserSessionLogCount) {

                    resultUserSessionLog = new UserSessionLog();

                    resultUserSessionLog.setUserName(itUserSessionLog.getUserName());
                    resultUserSessionLog.setName(itUserSessionLog.getName());
                    resultUserSessionLog.setJumlah(itUserSessionLog.getJumlah());
                    resultUserSessionLog.setStLoginTimestampFrom(itUserSessionLog.getDateFrom());

                    listOfResultUserSessionLogCount.add(resultUserSessionLog);
                }
            }
        }

        logger.info("[UserBoImpl.getUserSessionLogByCriteria] end process <<<");

        return listOfResultUserSessionLogCount;
    }

    @Override
    public List<User> getComboUser(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<User> listComboUser = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> listUser = null;
        try {
            listUser = userDao.getListUser(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listUser != null) {
            for (ImUsers imUsers : listUser) {
                User itemComboUser = new User();
                itemComboUser.setUserId(imUsers.getPrimaryKey().getId());
                itemComboUser.setUsername(imUsers.getUserName());
                listComboUser.add(itemComboUser);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboUser;
    }
    public List<User> getComboUserIdWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<User> listComboUser = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImUsers> listUser = null;
        try {
            listUser = userDao.getListUserId(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listUser != null) {
            for (ImUsers imUsers : listUser) {
                List<ImAreasBranchesUsers> imAreasBranchesUsersList = new ArrayList<ImAreasBranchesUsers> (imUsers.getImAreasBranchesUsers());
                ImAreasBranchesUsers imAreasBranchesUsers = imAreasBranchesUsersList.get(0);
                User itemComboUser = new User();
                itemComboUser.setUserId(imUsers.getPrimaryKey().getId());
                itemComboUser.setUsername(imUsers.getUserName());
                //itemComboUser.setUsername(imUsers.getImBiodataEntity().getNamaPegawai());
                itemComboUser.setBranchName(imAreasBranchesUsers.getImBranch().getBranchName());
                itemComboUser.setBranchId(imAreasBranchesUsers.getImBranch().getPrimaryKey().getId());
                itemComboUser.setDivisiName(imUsers.getImDepartmentEntity().getDepartmentName());
                itemComboUser.setDivisiId(imUsers.getImDepartmentEntity().getDepartmentId());
                itemComboUser.setPositionName(imUsers.getImPosition().getPositionName());
                itemComboUser.setPositionId(imUsers.getImPosition().getPositionId().toString());
                listComboUser.add(itemComboUser);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboUser;
    }

    @Override
    public User getUserByIdDevice(String idDevice) throws GeneralBOException {
        logger.info("[UserBoImpl.getUserByIdDevice] start process <<<");
        List<ImUsers> result = new ArrayList<>();

        try {
            result = userDao.getUserByIdDevice(idDevice);
        } catch (GeneralBOException e){
            logger.info("[UserBoImpl.getUserByIdDevice] error get user id device");
        }

        User user = new User();
        if (result.size() > 0){
            user.setUserId(result.get(0).getPrimaryKey().getId());
            user.setUsername(result.get(0).getUserName());
            user.setIdDevice(result.get(0).getIdDevice());
        }

        logger.info("[UserBoImpl.getUserByIdDevice] end process <<<");
        return user;
    }

    @Override
    public User getUserByIdPelayanan(String idPelayanan) throws GeneralBOException {
        logger.info("[UserBoImpl.getUserByIdDevice] start process <<<");
        ImUsers result = new ImUsers();

        try {
            result = userDao.getUserByIdPelayanan(idPelayanan);
        } catch (GeneralBOException e){
            logger.info("[UserBoImpl.getUserByIdDevice] error get user id device");
        }

        User user = new User();
        user.setUsername(result.getUserName());
        user.setUserId(result.getPrimaryKey().getId());
        user.setIdPelayanan(result.getIdPelayanan());

        logger.info("[UserBoImpl.getUserByIdDevice] end process <<<");
        return user;
    }
}