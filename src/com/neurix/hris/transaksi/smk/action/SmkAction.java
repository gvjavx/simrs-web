package com.neurix.hris.transaksi.smk.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.transaksi.smk.bo.SmkBo;
import com.neurix.hris.transaksi.smk.model.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SmkAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkAction.class);
    private SmkBo smkBoProxy;
    private Smk smk;
    private SmkEvaluasiPegawaiAspekActivityPeristiwa smkEvaluasiPegawaiAspekActivityPeristiwa;
    private SmkAspekActivityMonthly smkMonthly;

    private String periode;
    private String branchId;
    private String positionId;
    private String nip;
    private String status;
    private String bobot;
    private String view;
    private String checklist;
    private String activityId;
    private int bulan;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public SmkAspekActivityMonthly getSmkMonthly() {
        return smkMonthly;
    }

    public void setSmkMonthly(SmkAspekActivityMonthly smkMonthly) {
        this.smkMonthly = smkMonthly;
    }

    public SmkEvaluasiPegawaiAspekActivityPeristiwa getSmkEvaluasiPegawaiAspekActivityPeristiwa() {
        return smkEvaluasiPegawaiAspekActivityPeristiwa;
    }

    public void setSmkEvaluasiPegawaiAspekActivityPeristiwa(SmkEvaluasiPegawaiAspekActivityPeristiwa smkEvaluasiPegawaiAspekActivityPeristiwa) {
        this.smkEvaluasiPegawaiAspekActivityPeristiwa = smkEvaluasiPegawaiAspekActivityPeristiwa;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    private List<Smk> listComboSmk = new ArrayList<Smk>();

    public List<Smk> getListComboSmk() {
        return listComboSmk;
    }

    public void setListComboSmk(List<Smk> listComboSmk) {
        this.listComboSmk = listComboSmk;
    }

    public SmkBo getSmkBoProxy() {
        return smkBoProxy;
    }

    public void setSmkBoProxy(SmkBo smkBoProxy) {
        this.smkBoProxy = smkBoProxy;
    }

    public Smk getSmk() {
        return smk;
    }

    public void setSmk(Smk smk) {
        this.smk = smk;
    }

    private List<Smk> initComboSmk;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkAction.logger = logger;
    }


    public List<Smk> getInitComboSmk() {
        return initComboSmk;
    }

    public void setInitComboSmk(List<Smk> initComboSmk) {
        this.initComboSmk = initComboSmk;
    }

    public Smk init(String kode, String flag){
        logger.info("[SmkAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Smk> listOfResult = (List<Smk>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Smk smk: listOfResult) {
                    if(kode.equalsIgnoreCase(smk.getEvaluasiPegawaiId()) && flag.equalsIgnoreCase(smk.getFlag())){
                        setSmk(smk);
                        break;
                    }
                }
            } else {
                setSmk(new Smk());
            }

            logger.info("[SmkAction.init] end process >>>");
        }
        return getSmk();
    }

    @Override
    public String add() {
        logger.info("[SmkAction.add] start process >>>");
        Smk addSmk = new Smk();
        addSmk.setNip(CommonUtil.userIdLogin());
        addSmk.setBranchId(CommonUtil.userBranchLogin());
        addSmk.setPositionId(CommonUtil.userPosisiId());
        addSmk.setPegawaiName(CommonUtil.userLogin());
        addSmk.setRole(CommonUtil.roleAsLogin());
        setSmk(addSmk);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("historyJabatanForSmk");
        session.removeAttribute("branchForSmk");
        session.removeAttribute("positionIdForSmk");

        List<ImPosition> position = new ArrayList<>();
        ImPosition position1 = new ImPosition();
        position1.setPositionId("-");
        position1.setPositionName("-");
        position.add(position1);

        List<Branch> branches = new ArrayList<>();
        session.setAttribute("branchForSmk", branches);
        session.setAttribute("positionIdForSmk", position);

        logger.info("[SmkAction.add] stop process >>>");
        return "init_add";
    }

    public String addPeristiwa() {
        logger.info("[SmkAction.add] start process >>>");
        SmkEvaluasiPegawaiAspekActivityPeristiwa addPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
        addPeristiwa.setAspekActivityMonthlyId(getId());
        setSmkEvaluasiPegawaiAspekActivityPeristiwa(addPeristiwa);
        setAddOrEdit(true);
        setAdd(true);

        logger.info("[SmkAction.add] stop process >>>");
        return "init_edit_Aspek_peristiwa_add";
    }

    public String addNilaiAspekA() {
        logger.info("[SmkAction.add] start process >>>");
        SmkAspekActivityMonthly addNilai = new SmkAspekActivityMonthly();
        addNilai.setAspekActivityMonthly(getId());
        addNilai.setBulan(getBulan());
        addNilai.setEvaluasiPegawaiAspekId(getActivityId());
        setSmkMonthly(addNilai);
        setAddOrEdit(true);
        setAdd(true);

        logger.info("[SmkAction.add] stop process >>>");
        return "init_add_nilai_a";
    }

    public String addNilaiSubAspekA() {
        logger.info("[SmkAction.add] start process >>>");
        SmkAspekActivityMonthly addNilai = new SmkAspekActivityMonthly();
        addNilai.setAspekActivityMonthly(getId());
        addNilai.setEvaluasiPegawaiAspekId(getActivityId());
        setSmkMonthly(addNilai);
        setAddOrEdit(true);
        setAdd(true);

        logger.info("[SmkAction.add] stop process >>>");
        return "init_add_nilai_sub_a";
    }

    public String saveEditAspek(String idAspekDetail, String evaluasiPegawaiAspekId, String monthlyId, String bulan, String nilai, String bobot){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {
            SmkAspekActivityMonthly editMonthly = new SmkAspekActivityMonthly();
            editMonthly.setIdAspekDetail(idAspekDetail);
            editMonthly.setEvaluasiPegawaiAspekId(evaluasiPegawaiAspekId);
            editMonthly.setAspekActivityMonthly(monthlyId);
            editMonthly.setBulan(Integer.parseInt(bulan));
            editMonthly.setNilai(Double.parseDouble(nilai));
            editMonthly.setBobot(Double.parseDouble(bobot));

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMonthly.setLastUpdateWho(userLogin);
            editMonthly.setLastUpdate(updateTime);
            editMonthly.setAction("U");
            editMonthly.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            smkBo.saveEditMonthly(editMonthly);
            smkBo.saveUpdateRataRata(editMonthly);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

        return "init_add_nilai_a";
    }

    public List comboUserSmk(String query, String branchId, String jabatan){
        List<Biodata> listBiodata = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        listBiodata = smkBo.comboUserSmkSys(query, branchId, jabatan);

        return listBiodata;
    }

    public String saveNilaiSubAspekA(String activityId, String bulan, String nilai){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {

            SmkAspekActivityMonthly editMonthly = new SmkAspekActivityMonthly();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMonthly.setEvaluasiPegawaiAspekId(activityId);
            editMonthly.setBulan(Integer.parseInt(bulan));
            editMonthly.setNilai(Double.parseDouble(nilai));

            editMonthly.setLastUpdateWho(userLogin);
            editMonthly.setLastUpdate(updateTime);
            editMonthly.setAction("U");
            editMonthly.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            smkBo.saveEditMonthlySub(editMonthly);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

        return "init_add_nilai_sub_a";
    }

    public String saveEditAspekDetail(){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {

            Smk editAspekDetail = getSmk();
            String smkId = "EP170020";
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAspekDetail.setLastUpdateWho(userLogin);
            editAspekDetail.setLastUpdate(updateTime);
            editAspekDetail.setAction("U");
            editAspekDetail.setFlag("Y");

            smkBoProxy.saveEditAspekDetail(editAspekDetail);
            smkBoProxy.updatePoinPrestasi(smkId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

        return "init_add_nilai_a";
    }

    public String saveEditAspekADetail(){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {

            Smk editAspekDetail = getSmk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAspekDetail.setLastUpdateWho(userLogin);
            editAspekDetail.setLastUpdate(updateTime);
            editAspekDetail.setAction("U");
            editAspekDetail.setFlag("Y");

            smkBoProxy.saveEditAspekADetail(editAspekDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

        return "init_add_nilai_a";
    }

    @Override
    public String edit() {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setEvaluasiPegawaiId(getId());
        searchSmk.setNip(getNip());
        searchSmk.setFlag("Y");
        searchSmk.setBranchId(getBranchId());
        searchSmk.setPositionId(getPositionId());
        searchSmk.setPeriode(getPeriode());
        searchSmk.setFlagView(getView());
        String userLogin = CommonUtil.userIdLogin();
        searchSmk.setNipLogin(userLogin);
        Smk listOfsearchSmk = null;

        try {
            listOfsearchSmk = smkBoProxy.getSearchEdit(searchSmk);
            if(listOfsearchSmk != null){
                setSmk(listOfsearchSmk);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        logger.info("[SmkAction.search] end process <<<");

        return "init_edit";
    }

    public String editAspek() {
        logger.info("[SmkAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        Smk searchSmk = new Smk();
        searchSmk.setEvaluasiPegawaiAspekDetailId(getId());

        Smk listOfsearchSmk = null;
        List<SmkAspekActivityMonthly> smkAspekActivityMonthlies = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            listOfsearchSmk = smkBo.getAspekDetail(searchSmk);
            smkAspekActivityMonthlies = smkBo.getAspekDetailMonthly(getId());
            listOfsearchSmk.setBobot(Double.valueOf(getBobot()));

            if(listOfsearchSmk != null){
                setSmk(listOfsearchSmk);

                session.removeAttribute("listAspekMonthly");
                session.setAttribute("listAspekMonthly", smkAspekActivityMonthlies);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        logger.info("[SmkAction.search] end process <<<");

        return "init_edit_Aspek";
    }

    public String editAspekA() {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setEvaluasiPegawaiAspekDetailId(getId());
        Smk listOfsearchSmk = null;
        //List<Smk> smkAspekActivityMonthlies = null;
        List<SmkAspekActivityMonthly> smkAspekActivityMonthlies = null;
        List<Smk> smkChecklist = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

            listOfsearchSmk = smkBo.getAspekADetail(searchSmk);
            listOfsearchSmk.setBobot(Integer.valueOf(getBobot()));
            smkAspekActivityMonthlies = smkBo.getAspekDetailMonthly(getId());
            if(getChecklist().equals("") || getChecklist() == null){
                //smkAspekActivityMonthlies = smkBo.getAspekDetailMonthlyCheckList(getId());
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listAspekMonthly");
                session.setAttribute("listAspekMonthly", smkAspekActivityMonthlies);
            }else{
                listOfsearchSmk.setCekLis(true);
                //smkChecklist = smkBo.getAspekDetailMonthlyCheckList(getId());
                HttpSession session = ServletActionContext.getRequest().getSession();
                session.removeAttribute("listAspekMonthly");
                session.setAttribute("listAspekMonthly", smkAspekActivityMonthlies);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        logger.info("[SmkAction.search] end process <<<");
        setSmk(listOfsearchSmk);
        return "init_edit_AspekA";
        /*if(getChecklist().equals("") || getChecklist() == null){
        }else{
            return "init_edit_AspekSubA";
        }*/
    }

    public String editNilaiAspek() {
        logger.info("[SmkAction.add] start process >>>");

        SmkAspekActivityMonthly addNilai = new SmkAspekActivityMonthly();
        addNilai.setAspekActivityMonthly(getId());
        addNilai.setEvaluasiPegawaiAspekId(getActivityId());
        addNilai.setBulan(getBulan());
        setSmkMonthly(addNilai);
        setAddOrEdit(true);
        setAdd(true);

        logger.info("[SmkAction.add] stop process >>>");

        return "init_add_nilai_aspek";
    }

    public void cancelSmk() {
        logger.info("[SmkAction.cancelSmk] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        smkBo.cancelSmkSys();

        logger.info("[SmkAction.cancelSmk] stop process >>>");
    }

    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> editAspekMonthly(String id) {
        logger.info("[SmkAction.search] start process >>>");

        SmkEvaluasiPegawaiAspekActivityPeristiwa searchPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
        searchPeristiwa.setAspekActivityMonthlyId(id);
        List<SmkEvaluasiPegawaiAspekActivityPeristiwa> smkEvaluasiPegawaiAspekActivityPeristiwaList= null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            smkEvaluasiPegawaiAspekActivityPeristiwaList = smkBo.getAspekDetailPeristiwa(id);

            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("listAspekMonthlyPeristiwa");
            session.setAttribute("listAspekMonthlyPeristiwa", smkEvaluasiPegawaiAspekActivityPeristiwaList);
            setSmkEvaluasiPegawaiAspekActivityPeristiwa(searchPeristiwa);
        } catch (GeneralBOException e) {
            Long logId = null;
        }

        logger.info("[SmkAction.search] end process <<<");

        return smkEvaluasiPegawaiAspekActivityPeristiwaList;
    }

    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> aspekPeristiwa(String aspekActivity) {
        logger.info("[SmkAction.search] start process >>>");

        List<SmkEvaluasiPegawaiAspekActivityPeristiwa> smkEvaluasiPegawaiAspekActivityPeristiwaList= null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            smkEvaluasiPegawaiAspekActivityPeristiwaList = smkBo.aspekPeristiwaSys(aspekActivity);
        } catch (GeneralBOException e) {
            Long logId = null;
        }

        logger.info("[SmkAction.search] end process <<<");

        return smkEvaluasiPegawaiAspekActivityPeristiwaList;
    }

    @Override
    public String delete() {
        logger.info("[SmkAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Smk deleteSmk = new Smk();

        if (itemFlag != null ) {

            try {
                deleteSmk = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SmkAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSmk != null) {
                setSmk(deleteSmk);

            } else {
                deleteSmk.setEvaluasiPegawaiId(itemId);
                deleteSmk.setFlag(itemFlag);
                setSmk(deleteSmk);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSmk.setEvaluasiPegawaiId(itemId);
            deleteSmk.setFlag(itemFlag);
            setSmk(deleteSmk);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SmkAction.delete] end process <<<");

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

    public Boolean cekApprove(String branchId, String positionId, String periode){
        //jika ckApprove false, berarti atasan belum diapprove
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        boolean atasanApprove = false;
        atasanApprove = smkBo.cekApproveSys(branchId, positionId, periode);
        return atasanApprove;
    }

    public void updatePoinPrestasi(String smkId){
        //jika ckApprove false, berarti atasan belum diapprove
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        smkBo.updatePoinPrestasi(smkId);
    }

    public String saveEdit(){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {

            Smk editSmk = getSmk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmk.setLastUpdateWho(userLogin);
            editSmk.setLastUpdate(updateTime);
            editSmk.setAction("U");
            editSmk.setFlag("Y");

            smkBoProxy.saveEdit(editSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    //inisialisasi Nilai dan Nilai prestasi dari jumlah aspek
    public Smk getNilaiPrestasi(String smkId){
        Smk smk = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        smk = smkBo.getNilaiPrestasiSys(smkId);
        return smk;
    }

    //inisialisasi get Nilai Share
    public BigDecimal getNilaiShare(String smkId, String unit, String positionId){
        Smk smk = new Smk();
        smk.setEvaluasiPegawaiId(smkId);
        smk.setBranchId(unit);
        smk.setPositionId(positionId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        BigDecimal hasil = new BigDecimal(0);

        try{
            hasil = smkBo.getNilaiShareSys(smk);
        }catch (Exception e){
            logger.error("[SmkAction.getNilaiShare] Error when saving error,", e);
        }
        return hasil;
    }

    //klasifikasi Nilai Prestasi
    public String getKlasifikasiNilai(String nilai){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        return smkBo.getKlasifikasiNilaiSys(nilai);
    }

    public String saveDelete(){
        logger.info("[SmkAction.saveDelete] start process >>>");
        try {

            Smk deleteSmk = getSmk();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmk.setLastUpdate(updateTime);
            deleteSmk.setLastUpdateWho(userLogin);
            deleteSmk.setAction("U");
            deleteSmk.setFlag("N");

            smkBoProxy.saveDelete(deleteSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkAction.saveAdd] start process >>>");

        try {
            Smk smk = getSmk();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if(smk.getTipeSmk().equalsIgnoreCase("US")){
                smk.setNip("US");
            }
            smk.setCreatedWho(userLogin);
            smk.setLastUpdate(updateTime);
            smk.setCreatedDate(updateTime);
            smk.setLastUpdateWho(userLogin);
            smk.setAction("C");
            smk.setFlag("Y");

            smkBoProxy.saveAdd(smk);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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


    public String saveAddPeristiwa(String aspekActivityMonhlyId, String tanggal, String kejadian){
        logger.info("[SmkAction.saveAdd] start process >>>");

        try {
            SmkEvaluasiPegawaiAspekActivityPeristiwa peristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
            peristiwa.setAspekActivityMonthlyId(aspekActivityMonhlyId);
            peristiwa.setStTanggalKejadian(tanggal);
            peristiwa.setKejadian(kejadian);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (peristiwa.getStTanggalKejadian() != null && !"".equalsIgnoreCase(peristiwa.getStTanggalKejadian())) {
                peristiwa.setTanggalKejadian(CommonUtil.convertToDate(peristiwa.getStTanggalKejadian()));
                peristiwa.setTanggalKejadian(CommonUtil.convertToDate(peristiwa.getStTanggalKejadian()));
            }

            peristiwa.setCreatedWho(userLogin);
            peristiwa.setLastUpdate(updateTime);
            peristiwa.setCreatedDate(updateTime);
            peristiwa.setLastUpdateWho(userLogin);
            peristiwa.setAction("C");
            peristiwa.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

            smkBo.saveAddPeristiwa(peristiwa);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return "Error";
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return "Error";
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success";
    }

    public String saveEditPeristiwa(String evaluasiActivityPeristiwaId, String tanggal, String kejadian){
        logger.info("[SmkAction.saveAdd] start process >>>");

        try {
            SmkEvaluasiPegawaiAspekActivityPeristiwa peristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
            peristiwa.setEvaluasiActivityPeristiwaId(evaluasiActivityPeristiwaId);
            peristiwa.setStTanggalKejadian(tanggal);
            peristiwa.setKejadian(kejadian);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            if (peristiwa.getStTanggalKejadian() != null && !"".equalsIgnoreCase(peristiwa.getStTanggalKejadian())) {
                peristiwa.setTanggalKejadian(CommonUtil.convertToDate(peristiwa.getStTanggalKejadian()));
                peristiwa.setTanggalKejadian(CommonUtil.convertToDate(peristiwa.getStTanggalKejadian()));
            }

            peristiwa.setCreatedWho(userLogin);
            peristiwa.setLastUpdate(updateTime);
            peristiwa.setCreatedDate(updateTime);
            peristiwa.setLastUpdateWho(userLogin);
            peristiwa.setAction("C");
            peristiwa.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

            smkBo.saveEditPeristiwa(peristiwa);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return "Error";
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return "Error";
        }

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success";
    }

    @Override
    public String search() {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = getSmk();
        List<Smk> listOfsearchSmk = new ArrayList();

        try {
            listOfsearchSmk = smkBoProxy.getByCriteria(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmk);

        logger.info("[SmkAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmk() {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setFlag("Y");
        List<Smk> listOfsearchSmk = new ArrayList();

        try {
            listOfsearchSmk = smkBoProxy.getByCriteria(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmk.addAll(listOfsearchSmk);
        return SUCCESS;
    }

    public String getIdMonthly(String activityId, String tanggal) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        String hasil = smkBo.getIdMonthlySys(activityId, tanggal);

        return hasil;
    }

    @Override
    public String initForm() {
        logger.info("[SmkAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmk() {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setFlag("Y");
        List<Smk> listOfsearchSmk = new ArrayList();

        try {
            listOfsearchSmk = smkBoProxy.getByCriteria(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSmk");
        session.setAttribute("listOfResultSmk", listOfsearchSmk);

        logger.info("[SmkAction.search] end process <<<");

        return "";
    }

    public List<SmkJabatanDetail> showDataAspekA(String nip, String branchId, String jabatanId, String periode){
        List<SmkJabatanDetail> listOfAspekA = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        listOfAspekA = smkBo.getDataIndikatorA(nip, jabatanId, branchId, periode);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkJabatanDetail> smkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");

        return smkJabatanDetail;
    }

    public List<SmkJabatanDetail> showDataAspekB(String branchId, String jabatanId){
        List<SmkJabatanDetail> listOfAspekB = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        //Hanya ada salah satu aspek B, kalau tidak Aspek B1 / aspek B2
        listOfAspekB = smkBo.getDataIndikator(branchId, jabatanId, "B1");
        if(listOfAspekB.size() == 0){
            listOfAspekB = smkBo.getDataIndikator(branchId, jabatanId, "B2");
        }

        return listOfAspekB;
    }

    public List<SmkJabatanDetail> showDataAspekC(String branchId, String jabatanId){
        List<SmkJabatanDetail> listOfAspekC = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        listOfAspekC = smkBo.getDataIndikator(branchId, jabatanId, "C");

        return listOfAspekC;
    }

    public boolean cekUserSmk(String nip, String periode){
        boolean hasil = false ;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        hasil = smkBo.cekUserSmkSys(nip, periode);
        return hasil;
    }

    public List<SmkHistoryEvaluasiPegawai> showDataAspekHistory(String nip, String tahun){
        List<SmkHistoryEvaluasiPegawai> listOfAspekHistory = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");

        listOfAspekHistory = smkBo.smkHistorySys(nip, tahun);
        return listOfAspekHistory;
    }

    public List<Smk> getDataSearch(String nip, String periode, String unit, String divisi, String jabatan) {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setNip(nip);
        searchSmk.setPeriode(periode);
        searchSmk.setBranchId(unit);
        searchSmk.setDivisiId(divisi);
        searchSmk.setPositionId(jabatan);

        List<Smk> listOfsearchSmk = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            listOfsearchSmk = smkBo.getSearch(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchSmk;
    }

    public SmkEvaluasiPegawaiAspekActivityPeristiwa getItemPeristiwa(String idPeristiwa) {
        logger.info("[SmkAction.search] start process >>>");

        SmkEvaluasiPegawaiAspekActivityPeristiwa peristiwa = null;

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            peristiwa = smkBo.getItemPeristiwaSys(idPeristiwa);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return peristiwa;
    }

    public void  saveDeletePeristiwa(String idPeristiwa){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        smkBo.saveDeletePeristiwaSys(idPeristiwa);
    }

    public List<Smk> getAspekA(String nip, String branchId, String positionId, String periode, String smkId) {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setNip(nip);
        searchSmk.setBranchId(branchId);
        searchSmk.setPositionId(positionId);
        searchSmk.setPeriode(periode);
        searchSmk.setEvaluasiPegawaiId(smkId);

        List<Smk> listOfsearchAspekA = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            listOfsearchAspekA = smkBo.getEditAspekA(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchAspekA;
    }

    public String printReportSmk() {
        logger.info("[SmkAction.printReportSmk] start process >>>");
        //get data musim tanam

        String id = getId();
        String periode = getPeriode();
        String draft = getFlag();
        String status = getStatus();
        String positionId = getPositionId();
        String reportHasil = "success_print_report_smk";

        if(status.equalsIgnoreCase("KS")){
            reportHasil = "success_print_report_smk";
        }else{
            reportHasil = "success_print_report_smk_kns";
        }

        if (id != null) {

            String hasil = "";
            if(getAspek(getNip(), periode, "B1", id).size() > 0){
                hasil = "B1";
            }else{
                hasil = "B2";
            }
            getAspek(getNip(), periode, "B1", id);
            reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
            reportParams.put("titleReport", draft + " Report Evaluasi Pegawai");
            reportParams.put("periode", periode);
            reportParams.put("nip", getNip());
            reportParams.put("aspekB", hasil);
            reportParams.put("draft", draft);
            reportParams.put("atasanLangsung", "-");
            reportParams.put("atasanLangsung2", "-");
            //reportParams.put("subreport", CommonConstant.SUB_REPORT);
            /*reportParams.put("dataAspekA", aspekA);
            reportParams.put("dataAspekB", aspekB);
            reportParams.put("dataAspekC", aspekC);*/

            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = smkBoProxy.saveErrorMessage(e.getMessage(), "printReportSmk");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkAction.printReportSmk] Error when downloading ,", e1);
                }
                logger.error("[SmkAction.printReportSmk] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "";

            }

        } else {
            logger.error("[SmkAction.printReportSmk] Error when print report kpi unit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list kpi unit is empty, please inform to your admin.");
            return "";
        }

        logger.info("[SmkAction.printReportSmk] end process <<<");

        return reportHasil;
        //return "success_print_report_smk_new";
    }

    public List<Smk> getAspek(String nip, String periode, String tipeAspekId, String smkId) {
        logger.info("[SmkAction.search] start process >>>");

        Smk searchSmk = new Smk();
        searchSmk.setNip(nip);
        searchSmk.setPeriode(periode);
        searchSmk.setTipeAspekId(tipeAspekId);
        searchSmk.setEvaluasiPegawaiId(smkId);

        List<Smk> listOfsearchAspekA = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            listOfsearchAspekA = smkBo.getEditAspek(searchSmk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.search] Error when saving error,", e1);
            }
            logger.error("[SmkAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchAspekA;
    }


    public void updateNilaiPrestasi(String idAspek, String nilai){
        logger.info("[SmkAction.saveEdit] start process >>>");
        try {

            SmkEvaluasiPegawaiAspek smkPegawaiAspek = new SmkEvaluasiPegawaiAspek();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            smkPegawaiAspek.setEvaluasiPegawaiAspekId(idAspek);
            smkPegawaiAspek.setPointPrestasi(Double.valueOf(nilai));

            smkPegawaiAspek.setLastUpdateWho(userLogin);
            smkPegawaiAspek.setLastUpdate(updateTime);
            smkPegawaiAspek.setAction("U");
            smkPegawaiAspek.setFlag("Y");

            if(!"".equalsIgnoreCase(idAspek)){
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
                smkBo.saveEditAspek(smkPegawaiAspek);
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.saveEdit] Error when saving error,", e1);
            }
            logger.error("[SmkAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[SmkAction.saveEdit] end process <<<");

    }

    public String applySmk(String evaluasiPegawaiId, String nip, String branchId, String periode, String positionId){
        logger.info("[SmkAction.dwr.aplySmk] start process >>>");

        Smk smk = new Smk();

        smk.setEvaluasiPegawaiId(evaluasiPegawaiId);
        smk.setNip(nip);
        smk.setBranchId(branchId);
        smk.setPositionId(positionId);
        smk.setPeriode(periode);

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
            smkBo.applySmk(smk);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkBoProxy.saveErrorMessage(e.getMessage(), "SmkAction.dwr.aplySmk");
            } catch (GeneralBOException e1) {
                logger.error("[SmkAction.SmkAction.dwr.aplySmk] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[`SmkAction.dwr.aplySmk] Error when editing " + "[" + logId + "] Found problem when saving apply data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkAction.dwr.aplySmk] end process <<<");
        return "";
    }

    public String searchReport(){
        logger.info("[SmkAction.searchReport] start process >>>");

        String hasil = "";
        Smk searchSmk = getSmk();
        List<Smk> listData = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

        String unit = "";

/*
        if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("KD01")){
            unit = "Kantor Direksi";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGKB")){
            unit = "Krebet Baru";
        }else if(searchPayroll.getBranchId() != null && searchPayroll.getBranchId().equalsIgnoreCase("PGRA")){
            unit = "Rejo Agung";
        }
*/

        reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("statusPegawai",  unit);
        //reportParams.put("strBulan", "Periode : " + CommonUtil.convertNumberToStringBulan(searchPayroll.getBulan()) + "  " + searchPayroll.getTahun());

        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        listData = smkBo.printReportSmkSys(searchSmk.getTahun(), searchSmk.getBranchId());
        JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(listData);

        reportParams.put("titleReport", "Usulan Kenaikan berkala Tahun " + searchSmk.getTahun());
        reportParams.put("itemDataSource", itemData);

        hasil = "report_smk_periode";

        if(searchSmk != null){
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = smkBoProxy.saveErrorMessage(e.getMessage(), "searchReport");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkAction.searchReport] Error when downloading ,", e1);
                }
                logger.error("[SmkAction.searchReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
        }

        logger.info("[SmkAction.searchReport] end process >>>");
        return hasil;
    }

    public String report() {
        logger.info("[SmkAction.report] start process >>>");

        logger.info("[SmkAction.report] end process >>>");
        return "report_smk";
    }

    public List<ImPosition> listPosisi(String nip, String periode){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        List<ImPosition> hasil = new ArrayList<>();
        hasil = smkBo.listPosisi(nip, periode);

        return hasil;
    }

    public List<Branch> listBranch(String nip, String periode){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        List<Branch> hasil = new ArrayList<>();
        hasil = smkBo.listBranch(nip, periode);

        return hasil;
    }

    public List<ImPosition> listPosisiDetail(String nip, String periode, String branchId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        List<ImPosition> hasil = new ArrayList<>();
        hasil = smkBo.listPosisi(nip, periode, branchId);

        return hasil;
    }

    public String masaKerjaBulan(String nip, String periode, String branchId, String positionId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SmkBo smkBo = (SmkBo) ctx.getBean("smkBoProxy");
        String hasil = "";
        hasil = smkBo.masaKerjaBulan(nip, periode, branchId, positionId);

        return hasil;
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
