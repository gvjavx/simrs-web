package com.neurix.hris.transaksi.jadwalShiftKerja.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.groupMember.bo.GroupMemberBo;
import com.neurix.hris.master.groupMember.model.GroupMember;
import com.neurix.hris.master.groupShift.bo.GroupShiftBo;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.transaksi.jadwalShiftKerja.bo.JadwalShiftKerjaBo;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.HistoryOnCall;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalKerjaDTO;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerja;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.simrs.transaksi.CrudResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class JadwalShiftKerjaAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(JadwalShiftKerjaAction.class);
    private JadwalShiftKerjaBo jadwalShiftKerjaBoProxy;
    private JadwalShiftKerja jadwalShiftKerja;
    private GroupShiftBo groupShiftBoProxy;
    private String tglFrom;
    private String tglTo;

    public String getTglFrom() {
        return tglFrom;
    }

    public void setTglFrom(String tglFrom) {
        this.tglFrom = tglFrom;
    }

    public String getTglTo() {
        return tglTo;
    }

    public void setTglTo(String tglTo) {
        this.tglTo = tglTo;
    }

    public GroupShiftBo getGroupShiftBoProxy() {
        return groupShiftBoProxy;
    }

    public void setGroupShiftBoProxy(GroupShiftBo groupShiftBoProxy) {
        this.groupShiftBoProxy = groupShiftBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JadwalShiftKerjaAction.logger = logger;
    }

    public JadwalShiftKerjaBo getJadwalShiftKerjaBoProxy() {
        return jadwalShiftKerjaBoProxy;
    }

    public void setJadwalShiftKerjaBoProxy(JadwalShiftKerjaBo jadwalShiftKerjaBoProxy) {
        this.jadwalShiftKerjaBoProxy = jadwalShiftKerjaBoProxy;
    }

    public JadwalShiftKerja getJadwalShiftKerja() {
        return jadwalShiftKerja;
    }

    public void setJadwalShiftKerja(JadwalShiftKerja jadwalShiftKerja) {
        this.jadwalShiftKerja = jadwalShiftKerja;
    }

    public JadwalShiftKerja init(String kode, String flag){
        logger.info("[JadwalShiftkerjaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerja> listOfResult = (List<JadwalShiftKerja>) session.getAttribute("listOfResultJadwalShiftKerja");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (JadwalShiftKerja jadwalShiftKerja : listOfResult) {
                    if(kode.equalsIgnoreCase(jadwalShiftKerja.getJadwalShiftKerjaId()) && flag.equalsIgnoreCase(jadwalShiftKerja.getFlag())){
                        setJadwalShiftKerja(jadwalShiftKerja);
                        break;
                    }
                }
            } else {
                setJadwalShiftKerja(new JadwalShiftKerja());
            }
            logger.info("[JadwalShiftkerjaAction.init] end process >>>");
        }
        return getJadwalShiftKerja();
    }
    @Override
    public String add() {
        logger.info("[JadwalShiftKerjaAction.add] start process >>>");
        JadwalShiftKerja addJadwalShiftKerja = new JadwalShiftKerja();
        String branchId = CommonUtil.userBranchLogin();
        addJadwalShiftKerja.setBranchId(branchId);
        addJadwalShiftKerja.setBranchIdUser(branchId);
        String roleId = CommonUtil.roleIdAsLogin();
        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN) && CommonConstant.BRANCH_KP.equalsIgnoreCase(branchId)){
            addJadwalShiftKerja.setAdminHcm(true);
        }
        setJadwalShiftKerja(addJadwalShiftKerja);

        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJadwalShiftKerja");
        session.removeAttribute("listOfResultPegawaiShift");

        logger.info("[JadwalShiftKerjaAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JadwalShiftkerjaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();
        JadwalShiftKerja editJadwalShiftKerja = new JadwalShiftKerja();
        List<JadwalShiftKerjaDetail> listOfsearchJadwalShiftKerjaDetail = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HttpSession session = ServletActionContext.getRequest().getSession();
        JadwalShiftKerjaDetail searchJadwalShiftKerjaDetail = new JadwalShiftKerjaDetail();

        if(itemFlag != null){
            try {
                editJadwalShiftKerja = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getPersonalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[JadwalShiftkerjaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[JadwalShiftkerjaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            editJadwalShiftKerja.setStTanggal(CommonUtil.convertDateToString(editJadwalShiftKerja.getTanggal()));
            if(editJadwalShiftKerja != null) {
                setJadwalShiftKerja(editJadwalShiftKerja);
            } else {
                editJadwalShiftKerja.setFlag(itemFlag);
                editJadwalShiftKerja.setJadwalShiftKerjaId(itemId);
                setJadwalShiftKerja(editJadwalShiftKerja);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJadwalShiftKerja.setFlag(itemFlag);
            editJadwalShiftKerja.setJadwalShiftKerjaId(itemId);
            setJadwalShiftKerja(editJadwalShiftKerja);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        logger.info("[JadwalShiftkerjaAction.edit] start process >>>");

        searchJadwalShiftKerjaDetail.setJadwalShiftKerjaId(itemId);
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        try {
            listOfsearchJadwalShiftKerjaDetail = jadwalShiftKerjaBo.getByCriteriaDetail(searchJadwalShiftKerjaDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBo.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.search] Error when saving error,", e1);
                return null;
            }
            logger.error("[JadwalShiftKerjaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }
        session.setAttribute("listOfResultPegawaiShift", listOfsearchJadwalShiftKerjaDetail);
        setAddOrEdit(true);
        logger.info("[JadwalShiftkerjaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public String save() {
        logger.info("[JadwalShiftkerjaAction.save] start process >>>");

        try {
            JadwalShiftKerja jadwalShiftKerja = getJadwalShiftKerja();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            if (jadwalShiftKerja.getStTanggalAwal() != null && !"".equalsIgnoreCase(jadwalShiftKerja.getStTanggalAwal())) {
                jadwalShiftKerja.setTanggalAwal(CommonUtil.convertToDate(jadwalShiftKerja.getStTanggalAwal()));
            }
            if (jadwalShiftKerja.getStTanggalAkhir() != null && !"".equalsIgnoreCase(jadwalShiftKerja.getStTanggalAkhir())) {
                jadwalShiftKerja.setTanggalAkhir(CommonUtil.convertToDate(jadwalShiftKerja.getStTanggalAkhir()));
            }
            if ((jadwalShiftKerja.getStTanggalAkhir() == null || "".equalsIgnoreCase(jadwalShiftKerja.getStTanggalAkhir()))){
                Calendar start = Calendar.getInstance();
                java.util.Date date;
                date = start.getTime();
                start.setTime(jadwalShiftKerja.getTanggalAwal());
                jadwalShiftKerja.setTanggal(convertUtilToSql(date));
                jadwalShiftKerja.setCreatedWho(userLogin);
                jadwalShiftKerja.setLastUpdate(updateTime);
                jadwalShiftKerja.setCreatedDate(updateTime);
                jadwalShiftKerja.setLastUpdateWho(userLogin);
                jadwalShiftKerja.setAction("C");
                jadwalShiftKerja.setFlag("Y");
                jadwalShiftKerjaBoProxy.saveAdd(jadwalShiftKerja);
            }
            else{
                Calendar start = Calendar.getInstance();
                start.setTime(jadwalShiftKerja.getTanggalAwal());
                Calendar end = Calendar.getInstance();
                end.setTime(jadwalShiftKerja.getTanggalAkhir());
                end.add(Calendar.DATE,1);
                java.util.Date date;
                for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    jadwalShiftKerja.setTanggal(convertUtilToSql(date));
                    jadwalShiftKerja.setCreatedWho(userLogin);
                    jadwalShiftKerja.setLastUpdate(updateTime);
                    jadwalShiftKerja.setCreatedDate(updateTime);
                    jadwalShiftKerja.setLastUpdateWho(userLogin);
                    jadwalShiftKerja.setAction("C");
                    jadwalShiftKerja.setFlag("Y");
                    jadwalShiftKerjaBoProxy.saveAdd(jadwalShiftKerja);
                }
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftkerjaAction.save] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JadwalShiftkerjaAction.save] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultJadwalShiftKerjaFinal");
        session.removeAttribute("listOfResultPegawaiShift");

        logger.info("[JadwalShiftkerjaAction.save] end process >>>");
        return null;
    }

    @Override
    public String search() {
        logger.info("[JadwalShiftKerjaAction.search] start process >>>");

        JadwalShiftKerja searchjadwalShiftKerja = getJadwalShiftKerja();
        String roleId = CommonUtil.roleIdAsLogin();
        List<JadwalShiftKerja> listOfSearchJadwalShiftKerja = new ArrayList();

        searchjadwalShiftKerja.setBranchIdUser(CommonUtil.userBranchLogin());
        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN)){
            searchjadwalShiftKerja.setAdminHcm(true);
        }

        try {
            listOfSearchJadwalShiftKerja = jadwalShiftKerjaBoProxy.getByCriteria(searchjadwalShiftKerja);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JadwalShiftKerjaAction.search] Error when searching lembur by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJadwalShiftKerja");
        session.removeAttribute("ListOfResultGroupShift");
        session.setAttribute("listOfResultJadwalShiftKerja", listOfSearchJadwalShiftKerja);

        logger.info("[JadwalShiftKerjaAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[JadwalShiftKerjaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJadwalShiftKerja");
        session.removeAttribute("ListOfResultGroupShift");
        session.removeAttribute("listOfResultPegawaiShift");

        String branchIdUser = CommonUtil.userBranchLogin();
        String roleId = CommonUtil.roleIdAsLogin();

        JadwalShiftKerja jadwalShiftKerja = new JadwalShiftKerja();
        jadwalShiftKerja.setBranchIdUser(branchIdUser);
        jadwalShiftKerja.setBranchId(branchIdUser);
        jadwalShiftKerja.setGroupId(CommonUtil.userBagianId());
        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN)){
            jadwalShiftKerja.setAdminHcm(true);
            jadwalShiftKerja.setGroupId("");
        }

        setJadwalShiftKerja(jadwalShiftKerja);
        logger.info("[JadwalShiftKerjaAction.initForm] end process >>>");
        return INPUT;
    }

    public List<GroupShift> searchGroupShift() {
        logger.info("[JadwalShiftKerjaAction.searchGroupShift] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GroupShift> listOfsearchGroupShift = (List<GroupShift>) session.getAttribute("ListOfResultGroupShift");
        logger.info("[JadwalShiftkerjaAction.searchGroupShift] end process <<<");
        return listOfsearchGroupShift;
    }
    public List<GroupShift> searchGroupShiftEdit(String JadwalShiftKerjaId) {
        logger.info("[JadwalShiftKerjaAction.searchGroupShiftEdit] start process >>>");

        JadwalShiftKerjaDetail searchJadwalShiftKerjaDetail = new JadwalShiftKerjaDetail();
        searchJadwalShiftKerjaDetail.setJadwalShiftKerjaId(JadwalShiftKerjaId);
        List<JadwalShiftKerjaDetail> listOfsearchJadwalShiftKerjaDetail = new ArrayList();
        List<GroupShift> listOfsearchGroupShift = new ArrayList();
        List<GroupShift> listOfsearchGroupShiftAll = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        try {
            listOfsearchJadwalShiftKerjaDetail = jadwalShiftKerjaBo.getByCriteriaDetail(searchJadwalShiftKerjaDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBo.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.searchGroupShiftEdit] Error when saving error,", e1);
                return null;
            }
            logger.error("[JadwalShiftKerjaAction.searchGroupShiftEdit] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        GroupShiftBo groupShiftBo = (GroupShiftBo) ctx.getBean("groupShiftBoProxy");
        if (listOfsearchJadwalShiftKerjaDetail!=null){
            for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail :listOfsearchJadwalShiftKerjaDetail){
                GroupShift groupShift = new GroupShift();
                groupShift.setGroupShiftId(jadwalShiftKerjaDetail.getShiftGroupId());
                listOfsearchGroupShift = groupShiftBo.getByCriteriaShift(groupShift);
                listOfsearchGroupShiftAll.addAll(listOfsearchGroupShift);
            }
        }
        logger.info("[JadwalShiftKerjaAction.searchGroupShiftEdit] end process <<<");

        return listOfsearchGroupShiftAll;
    }

    public List<GroupMember> searchGroupMember(String groupId) {
        logger.info("[JadwalShiftKerjaAction.searchGroupMember] start process >>>");

        GroupMember searchGroupMember = new GroupMember();
        searchGroupMember.setGroupId(groupId);
        List<GroupMember> listOfsearchGroupMember = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupMemberBo groupMemberBo = (GroupMemberBo) ctx.getBean("groupMemberBoProxy");
        try {
            listOfsearchGroupMember = groupMemberBo.getByCriteria(searchGroupMember);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupMemberBo.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.searchGroupMember] Error when saving error,", e1);
                return null;
            }
            logger.error("[JadwalShiftKerjaAction.searchGroupMember] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        logger.info("[JadwalShiftKerjaAction.searchGroupMember] end process <<<");

        return listOfsearchGroupMember;
    }

    public List<GroupShift> searchGroup(String groupShiftId) {
        logger.info("[JadwalShiftKerjaAction.searchGroup] start process >>>");

        GroupShift searchGroupShift = new GroupShift();
        searchGroupShift.setGroupShiftId(groupShiftId);
        searchGroupShift.setFlag("Y");
        List<GroupShift> listOfsearchGroupShift = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupShiftBo groupShiftBo = (GroupShiftBo) ctx.getBean("groupShiftBoProxy");
        try {
            listOfsearchGroupShift = groupShiftBo.getByCriteria(searchGroupShift);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = groupShiftBoProxy.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.searchGroup] Error when saving error,", e1);
                return null;
            }
            logger.error("[JadwalShiftKerjaAction.searchGroup] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        logger.info("[JadwalShiftKerjaAction.searchGroup] end process <<<");

        return listOfsearchGroupShift;
    }
    public String saveTmpShift(String groupShiftId){
        logger.info("[JadwalShiftKerjaAction.saveTmpShift] start process >>>");
        List<GroupShift> listOfResult = new ArrayList();
        List<GroupShift> listComboGroupShift = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<GroupShift> listOfsearchGroupShift = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        GroupShiftBo groupShiftBo = (GroupShiftBo) ctx.getBean("groupShiftBoProxy");
        GroupShift searchGroupShift = new GroupShift();
        searchGroupShift.setGroupShiftId(groupShiftId);
        try {
            listOfsearchGroupShift = groupShiftBo.getByCriteria(searchGroupShift);
            for (GroupShift groupShift : listOfsearchGroupShift){
                GroupShift returnGroupShift = new GroupShift();
                returnGroupShift.setGroupShiftId(groupShift.getGroupShiftId());
                returnGroupShift.setGroupId(groupShift.getGroupId());
                returnGroupShift.setGroupName(groupShift.getGroupName());
                returnGroupShift.setShiftId(groupShift.getShiftId());
                returnGroupShift.setShiftName(groupShift.getShiftName());
                listOfResult.add(returnGroupShift);
            }
            // creating object entity serializable
            listComboGroupShift = (List<GroupShift>) session.getAttribute("ListOfResultGroupShift");
            if(listComboGroupShift != null){
                listComboGroupShift.addAll(listOfResult);
            }else{
                listComboGroupShift = new ArrayList();
                listComboGroupShift.addAll(listOfResult);
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "jadwalShiftKerjaBo.saveTmpShift");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.saveTmpShift] Error when saving error,", e1);
            }
            logger.error("[JadwalShiftKerjaAction.saveTmpShift] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("ListOfResultGroupShift", listComboGroupShift);

        logger.info("[JadwalShiftKerjaAction.saveTmpShift] end process <<<");

        return null;
    }
    public String saveEdit() {
        logger.info("[JadwalShiftKerjaAction.saveEdit] start process >>>");

        try {
            JadwalShiftKerja jadwalShiftKerja = getJadwalShiftKerja();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jadwalShiftKerja.setLastUpdate(updateTime);
            jadwalShiftKerja.setLastUpdateWho(userLogin);
            jadwalShiftKerja.setCreatedDate(updateTime);
            jadwalShiftKerja.setCreatedWho(userLogin);
            jadwalShiftKerja.setAction("U");
            jadwalShiftKerja.setFlag("Y");

            jadwalShiftKerjaBoProxy.saveEdit(jadwalShiftKerja);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JadwalShiftKerjaAction.saveEdit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJadwalShiftKerja");
        session.removeAttribute("ListOfResultGroupShift");
        session.removeAttribute("listOfResultPegawaiShift");

        logger.info("[JadwalShiftKerjaAction.saveAdd] end process >>>");
        return null;
    }

    public List<GroupShift> deleteJadwalShiftKerja(String id) {
        logger.info("[JadwalShiftKerjaAction.deleteJadwalShiftKerja] start process >>>");

        List<GroupShift> jadwalShiftKerjaGroupShift = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");

            jadwalShiftKerjaGroupShift = jadwalShiftKerjaBo.deleteJadwalShiftKerja(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.deleteJadwalShiftKerja] Error when saving error,", e1);
            }
            logger.error("[JadwalShiftKerjaAction.deleteJadwalShiftKerja] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return null;
    }
    public String saveAddJadwalAuto(String stTanggalAwal,String stTanggalAkhir) throws Exception {
        logger.info("[JadwalShiftKerjaAction.saveAddJadwalAuto] start process >>>");
        JadwalShiftKerja searchJadwalShiftKerja = new JadwalShiftKerja();
        String status="";
        Date tanggalAwal = CommonUtil.convertStringToDate(stTanggalAwal);
        Date tanggalAkhir = CommonUtil.convertStringToDate(stTanggalAkhir);
        searchJadwalShiftKerja.setTanggalAwal(tanggalAwal);
        searchJadwalShiftKerja.setTanggalAkhir(tanggalAkhir);
        if (tanggalAwal.after(tanggalAkhir)){
            status="tanggal awal tidak boleh melebihi tanggal akhir";
        }
        else if (CommonUtil.countAllDays(stTanggalAwal,stTanggalAkhir)%7==0){
            status=jadwalShiftKerjaBoProxy.saveTanggalOtomatis(searchJadwalShiftKerja);
        }else{
            status="Masukkan tanggal dalam mingguan";
        }

        logger.info("[JadwalShiftKerjaAction.saveAddJadwalAuto] stop process >>>");
        return status;
    }

    public String printJadwalShiftKerja() {
        logger.info("[JadwalShiftKerjaAction.printJadwalShiftKerja] start process >>>");
        List<JadwalShiftKerja> jadwalShiftKerjaList = new ArrayList();
        List<JadwalKerjaDTO> listDataFinal = new ArrayList();
        List<Biodata> satpamList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");

        try {
            satpamList = biodataBo.getSatpam();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getSatpam");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when saving error,", e1);
            }
            logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        JadwalShiftKerja search = new JadwalShiftKerja();
        search.setFlag("Y");
        search.setStTanggalAwal(getTglFrom());
        search.setStTanggalAkhir(getTglTo());
        search.setTanggalAwal(CommonUtil.convertStringToDate(getTglFrom()));
        search.setTanggalAkhir(CommonUtil.convertStringToDate(getTglTo()));

        try {
            jadwalShiftKerjaList = jadwalShiftKerjaBoProxy.getJadwalforReport(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.printJadwalShiftKerja");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when saving error,", e1);
            }
            logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when searching absensi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        Date startDate = CommonUtil.convertToDate(getTglFrom());
        Date endDate = CommonUtil.convertToDate(getTglTo());
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.add(Calendar.DATE,1);
        java.util.Date date;

        //set header
        JadwalKerjaDTO jadwal = new JadwalKerjaDTO();
        jadwal.setNo("NO");
        jadwal.setNama("Nama");
        jadwal.setSenin("Senin");
        jadwal.setSelasa("Selasa");
        jadwal.setRabu("Rabu");
        jadwal.setKamis("Kamis");
        jadwal.setJumat("Jum'at");
        jadwal.setSabtu("Sabtu");
        jadwal.setMinggu("Minggu");
        listDataFinal.add(jadwal);

        for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Date tanggal = new java.sql.Date(date.getTime());
            String hari = CommonUtil.convertDateToDay(tanggal);

            switch (hari){
                case "Senin" :
                    jadwal.setSenin("Senin                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Selasa" :
                    jadwal.setSelasa("Selasa                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Rabu" :
                    jadwal.setRabu("Rabu                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Kamis" :
                    jadwal.setKamis("Kamis                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Jum'at" :
                    jadwal.setJumat("Jum'at                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Sabtu" :
                    jadwal.setSabtu("Sabtu                "+CommonUtil.convertDateToString(tanggal));
                    break;
                case "Minggu" :
                    jadwal.setMinggu("Minggu                "+CommonUtil.convertDateToString(tanggal));
                    break;
            }
        }

        int no=1;
        for (Biodata biodata : satpamList){
            JadwalKerjaDTO jadwalKerjaDTO = new JadwalKerjaDTO();
            jadwalKerjaDTO.setNama(biodata.getNamaPegawai());
            jadwalKerjaDTO.setNo(String.valueOf(no));
            for (JadwalShiftKerja jadwalShiftKerja : jadwalShiftKerjaList ){
                if (jadwalShiftKerja.getNip().equalsIgnoreCase(biodata.getNip())){
                    String hari = CommonUtil.convertDateToDay(jadwalShiftKerja.getTanggal());
                    String shift = jadwalShiftKerja.getShiftName();
                    String jadwalShift="";
                    switch (shift) {
                        case "Shift Pagi":
                            jadwalShift="P";
                            break;
                        case "Shift Sore":
                            jadwalShift="S";
                            break;
                        case "Shift Malam":
                            jadwalShift="M";
                            break;
                    }

                    switch (hari){
                        case "Senin" :
                            jadwalKerjaDTO.setSenin(jadwalShift);
                            break;
                        case "Selasa" :
                            jadwalKerjaDTO.setSelasa(jadwalShift);
                            break;
                        case "Rabu" :
                            jadwalKerjaDTO.setRabu(jadwalShift);
                            break;
                        case "Kamis" :
                            jadwalKerjaDTO.setKamis(jadwalShift);
                            break;
                        case "Jum'at" :
                            jadwalKerjaDTO.setJumat(jadwalShift);
                            break;
                        case "Sabtu" :
                            jadwalKerjaDTO.setSabtu(jadwalShift);
                            break;
                        case "Minggu" :
                            jadwalKerjaDTO.setMinggu(jadwalShift);
                            break;
                    }
                }
            }
            listDataFinal.add(jadwalKerjaDTO);
            no++;
        }

        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listDataFinal);
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date dataDate = new java.sql.Date(updateTime.getTime());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String stDate = dt1.format(dataDate);

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("titleReport", "JADWAL SHIFT SATPAM");
        reportParams.put("tanggalDari", getTglFrom());
        reportParams.put("tanggalSelesai", getTglTo());
        reportParams.put("itemDataSource", itemData);
        reportParams.put("date", stDate);

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "printJadwalShiftKerja");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when downloading ,", e1);
            }
            logger.error("[JadwalShiftKerjaAction.printJadwalShiftKerja] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return "failure_print_jadwal_shift";

        }
        logger.info("[JadwalShiftKerjaAction.printJadwalShiftKerja] end process <<<");

        return "print_jadwal_shift";
    }

    public List<JadwalShiftKerjaDetail> searchPegawaiByGrup(String groupId , String unit) {
        logger.info("[JadwalShiftKerjaAction.JadwalShiftKerjaDetail] start process >>>");
        List<JadwalShiftKerjaDetail> jadwalShiftKerjaDetailList = new ArrayList();
        List<JadwalShiftKerjaDetail> finalResult = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        try {
            jadwalShiftKerjaDetailList = jadwalShiftKerjaBo.getPegawaiByGrup(groupId,unit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBo.saveErrorMessage(e.getMessage(), "JadwalShiftKerjaAction.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.searchPegawaiByGrup] Error when saving error,", e1);
                return null;
            }
            logger.error("[JadwalShiftKerjaAction.searchPegawaiByGrup] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        logger.info("[JadwalShiftKerjaAction.searchPegawaiByGrup] end process <<<");
        return jadwalShiftKerjaDetailList;
    }
    public void savePegawaiShift(String nip , String nama,String posisi,String grup,String grupId, String shift,String shiftId,String onCall) {
        logger.info("[JadwalShiftKerjaAction.savePegawaiShift] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerjaDetail> listOfResult = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");
        if (listOfResult==null){
            listOfResult= new ArrayList<>();
        }
        JadwalShiftKerjaDetail result = new JadwalShiftKerjaDetail();
        result.setNip(nip);
        result.setNamaPegawai(nama);
        result.setPositionName(posisi);
        result.setProfesiName(grup);
        result.setProfesiid(grupId);
        result.setShiftName(shift);
        result.setShiftId(shiftId);
        result.setOnCall(onCall);
        listOfResult.add(result);

        session.setAttribute("listOfResultPegawaiShift",listOfResult);
        logger.info("[JadwalShiftKerjaAction.savePegawaiShift] end process <<<");
    }
    public List<JadwalShiftKerjaDetail> searchResultPegawai() {
        logger.info("[JadwalShiftKerjaAction.searchResultPegawai] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerjaDetail> listOfResult = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");

        logger.info("[JadwalShiftKerjaAction.searchResultPegawai] end process <<<");
        return listOfResult;
    }
    public List<JadwalShiftKerjaDetail> deletePegawaiShift(String nip,String shift) {
        logger.info("[JadwalShiftKerjaAction.deletePegawaiShift] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerjaDetail> listOfResult = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");
        List<JadwalShiftKerjaDetail> finalResult = new ArrayList<>();

        for (JadwalShiftKerjaDetail detail : listOfResult){
            if (!nip.equalsIgnoreCase(detail.getNip())||!shift.equalsIgnoreCase(detail.getShiftId())){
                finalResult.add(detail);
            }
        }

        session.setAttribute("listOfResultPegawaiShift",finalResult);
        logger.info("[JadwalShiftKerjaAction.deletePegawaiShift] end process <<<");
        return finalResult;
    }

    public String cekTanggal(String branchId,String tanggalDari,String tanggalSampai) {
        logger.info("[JadwalShiftKerjaAction.cekTanggal] start process >>>");
        String status ="00";
        List<JadwalShiftKerja> jadwalShiftKerjaList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");

        try {
            JadwalShiftKerja searchJadwalShiftKerja = new JadwalShiftKerja();
            searchJadwalShiftKerja.setBranchId(branchId);
            searchJadwalShiftKerja.setStTanggalAwal(tanggalDari);
            searchJadwalShiftKerja.setStTanggalAkhir(tanggalSampai);

            if (searchJadwalShiftKerja.getStTanggalAwal() != null && !"".equalsIgnoreCase(searchJadwalShiftKerja.getStTanggalAwal())) {
                searchJadwalShiftKerja.setTanggalAwal(CommonUtil.convertToDate(searchJadwalShiftKerja.getStTanggalAwal()));
            }
            if (searchJadwalShiftKerja.getStTanggalAkhir() != null && !"".equalsIgnoreCase(searchJadwalShiftKerja.getStTanggalAkhir())) {
                searchJadwalShiftKerja.setTanggalAkhir(CommonUtil.convertToDate(searchJadwalShiftKerja.getStTanggalAkhir()));
            }

            if ((searchJadwalShiftKerja.getStTanggalAkhir() == null || "".equalsIgnoreCase(searchJadwalShiftKerja.getStTanggalAkhir()))){
                jadwalShiftKerjaList=jadwalShiftKerjaBo.getJadwalShiftKerjaByUnitAndTanggal(searchJadwalShiftKerja.getBranchId(),searchJadwalShiftKerja.getTanggalAwal());
            } else{
                Calendar start = Calendar.getInstance();
                start.setTime(searchJadwalShiftKerja.getTanggalAwal());
                Calendar end = Calendar.getInstance();
                end.setTime(searchJadwalShiftKerja.getTanggalAkhir());
                end.add(Calendar.DATE,1);
                java.util.Date date;
                for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                    Date tanggal = new java.sql.Date(date.getTime());
                    List<JadwalShiftKerja> jadwalShiftKerjaList1 = jadwalShiftKerjaBo.getJadwalShiftKerjaByUnitAndTanggal(searchJadwalShiftKerja.getBranchId(),tanggal);
                    jadwalShiftKerjaList.addAll(jadwalShiftKerjaList1);
                }
            }
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = jadwalShiftKerjaBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[JadwalShiftKerjaAction.cekTanggal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[JadwalShiftKerjaAction.cekTanggal] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        if (jadwalShiftKerjaList.size()!=0) {
            status = "01";
        }

        logger.info("[JadwalShiftKerjaAction.cekTanggal] end process >>>");
        return status;
    }

    public CrudResponse cekLibur (String tanggalAwal, String tanggalAkhir){
        logger.info("[JadwalShiftKerjaAction.cekLibur] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        response = jadwalShiftKerjaBo.getListLibur(tanggalAwal, tanggalAkhir);
        logger.info("[JadwalShiftKerjaAction.cekLibur] end process >>>");
        return response;
    }

    public void savePanggilBerdasarkanId(String id,String keterangan){
        logger.info("[JadwalShiftKerjaAction.savePanggilBerdasarkanId] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        JadwalShiftKerjaDetail data = new JadwalShiftKerjaDetail();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        data.setJadwalShiftKerjaDetailId(id);
        data.setFlagPanggil("Y");
        data.setPanggilDate(updateTime);
        data.setPanggilWho(userLogin);
        data.setKeteranganPanggil(keterangan);

        List<Notifikasi> notifCuti = jadwalShiftKerjaBo.savePanggilBerdasarkanId(data);

        for (Notifikasi notifikasi : notifCuti ){
            notifikasiBo.sendNotif(notifikasi);
        }
        logger.info("[JadwalShiftKerjaAction.savePanggilBerdasarkanId] end process >>>");
    }

    public void saveLiburBerdasarkanId(String id){
        logger.info("[JadwalShiftKerjaAction.saveLiburBersadarkanId] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        JadwalShiftKerjaDetail data = new JadwalShiftKerjaDetail();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        data.setJadwalShiftKerjaDetailId(id);
        data.setFlagLibur("Y");
        data.setLastUpdate(updateTime);
        data.setLastUpdateWho(userLogin);

        jadwalShiftKerjaBo.saveLiburBerdasarkanId(data);
        logger.info("[JadwalShiftKerjaAction.saveLiburBersadarkanId] end process >>>");
    }

    public JadwalShiftKerja searchUserBagian(){
        logger.info("[JadwalShiftKerjaAction.searchUserBagian] start process >>>");
        JadwalShiftKerja data = new JadwalShiftKerja();
        String roleId = CommonUtil.roleIdAsLogin();
        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN)){
            data.setAdminHcm(true);
        }else{
            data.setGroupId(CommonUtil.userBagianId());
        }
        logger.info("[JadwalShiftKerjaAction.searchUserBagian] end process >>>");
        return data;
    }

    public JadwalShiftKerja cekJadwalKerja(String nip,String shiftId,String onCall){
        logger.info("[JadwalShiftKerjaAction.cekJadwalKerja] start process >>>");
        JadwalShiftKerja jadwalShiftKerja= new JadwalShiftKerja();
        String status ="";

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JadwalShiftKerjaDetail> listOfResult = (List<JadwalShiftKerjaDetail>) session.getAttribute("listOfResultPegawaiShift");
        int jumlahJadwal = 0;
        if (listOfResult!=null){
            for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : listOfResult){
                if (nip.equalsIgnoreCase(jadwalShiftKerjaDetail.getNip())){
                    jumlahJadwal++;
                }
                if (nip.equalsIgnoreCase(jadwalShiftKerjaDetail.getNip())&&shiftId.equalsIgnoreCase(jadwalShiftKerjaDetail.getShiftId())){
                    status="Pegawai dengan shift ini sudah ada";
                    break;
                }
            }
            if ("Y".equalsIgnoreCase(onCall)){
                for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : listOfResult) {
                    if (nip.equalsIgnoreCase(jadwalShiftKerjaDetail.getNip())) {
                        status="Jadwal dengan On Call Hanya Bisa 1 Kali";
                        break;
                    }
                }
            }else{
                for (JadwalShiftKerjaDetail jadwalShiftKerjaDetail : listOfResult){
                    if (nip.equalsIgnoreCase(jadwalShiftKerjaDetail.getNip())&&"Y".equalsIgnoreCase(jadwalShiftKerjaDetail.getOnCall())){
                        status="Jadwal dengan On Call Hanya Bisa 1 Kali";
                        break;
                    }
                }
            }
        }

        jadwalShiftKerja.setStatusSave(status);
        jadwalShiftKerja.setJumlahJadwal(jumlahJadwal);
        logger.info("[JadwalShiftKerjaAction.cekjadwalkerja] end process >>>");
        return jadwalShiftKerja;
    }

    public List<HistoryOnCall> searchHistoryOnCallSession() {
        logger.info("[JadwalShiftKerjaAction.searchHistoryOnCallSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HistoryOnCall> historyOnCalls = (List<HistoryOnCall>) session.getAttribute("listOfResultHistoryOnCall");
        logger.info("[JadwalShiftKerjaAction.searchHistoryOnCallSession] end process >>>");
        return historyOnCalls;
    }


    public void searchHistoryOnCall(String id,String nip) {
        logger.info("[JadwalShiftKerjaAction.searchHistoryOnCall] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JadwalShiftKerjaBo jadwalShiftKerjaBo = (JadwalShiftKerjaBo) ctx.getBean("jadwalShiftKerjaBoProxy");
        HistoryOnCall historyOnCall = new HistoryOnCall();
        historyOnCall.setFlag("Y");
        historyOnCall.setJadwalShiftKerjaDetailId(id);
        historyOnCall.setNip(nip);

        List<HistoryOnCall> historyOnCalls = jadwalShiftKerjaBo.getHistoryOnCall(historyOnCall);
        session.setAttribute("listOfResultHistoryOnCall",historyOnCalls);
        logger.info("[JadwalShiftKerjaAction.searchHistoryOnCall] end process >>>");
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
