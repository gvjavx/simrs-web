package com.neurix.akuntansi.master.settingReportKeuanganKonsol.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.bo.AkunSettingReportKeuanganKonsolDetailBo;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SettingReportKeuanganKonsolAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(SettingReportKeuanganKonsolAction.class);
    private AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganKonsolBoProxy;
    private AkunSettingReportKeuanganKonsolDetailBo akunSettingReportKeuanganKonsolDetailBoProxy;

    private AkunSettingReportKeuanganKonsol akunSettingReportKeuanganKonsol;
    private List<AkunSettingReportKeuanganKonsol> listOfComboSettingReportKeuanganKonsol = new ArrayList<AkunSettingReportKeuanganKonsol>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SettingReportKeuanganKonsolAction.logger = logger;
    }

    public AkunSettingReportKeuanganKonsolDetailBo getAkunSettingReportKeuanganKonsolDetailBoProxy() {
        return akunSettingReportKeuanganKonsolDetailBoProxy;
    }

    public void setAkunSettingReportKeuanganKonsolDetailBoProxy(AkunSettingReportKeuanganKonsolDetailBo akunSettingReportKeuanganKonsolDetailBoProxy) {
        this.akunSettingReportKeuanganKonsolDetailBoProxy = akunSettingReportKeuanganKonsolDetailBoProxy;
    }

    public AkunSettingReportKeuanganKonsolBo getAkunSettingReportKeuanganKonsolBoProxy() {
        return akunSettingReportKeuanganKonsolBoProxy;
    }

    public void setAkunSettingReportKeuanganKonsolBoProxy(AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganKonsolBoProxy) {
        this.akunSettingReportKeuanganKonsolBoProxy = akunSettingReportKeuanganKonsolBoProxy;
    }

    public AkunSettingReportKeuanganKonsol getAkunSettingReportKeuanganKonsol() {
        return akunSettingReportKeuanganKonsol;
    }

    public void setAkunSettingReportKeuanganKonsol(AkunSettingReportKeuanganKonsol akunSettingReportKeuanganKonsol) {
        this.akunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsol;
    }

    public List<AkunSettingReportKeuanganKonsol> getListOfComboSettingReportKeuanganKonsol() {
        return listOfComboSettingReportKeuanganKonsol;
    }

    public void setListOfComboSettingReportKeuanganKonsol(List<AkunSettingReportKeuanganKonsol> listOfComboSettingReportKeuanganKonsol) {
        this.listOfComboSettingReportKeuanganKonsol = listOfComboSettingReportKeuanganKonsol;
    }

    public AkunSettingReportKeuanganKonsol init(String kode, String flag) {
        logger.info("[AkunSettingReportKeuanganKonsolAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AkunSettingReportKeuanganKonsol> listOfResult = (List<AkunSettingReportKeuanganKonsol>) session.getAttribute("listOfResult");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResult != null) {
                for (AkunSettingReportKeuanganKonsol reportKeuanganKonsol : listOfResult) {
                    if (kode.equalsIgnoreCase(reportKeuanganKonsol.getSettingReportKonsolId()) && flag.equalsIgnoreCase(reportKeuanganKonsol.getFlag())) {
                        setAkunSettingReportKeuanganKonsol(reportKeuanganKonsol);
                        break;
                    }
                }
            } else {
                setAkunSettingReportKeuanganKonsol(new AkunSettingReportKeuanganKonsol());
            }

            logger.info("[AkunSettingReportKeuanganKonsolAction.init] end process >>>");
        }
        return getAkunSettingReportKeuanganKonsol();
    }

//    public List<AkunSettingReportKeuanganKonsol> initTypeaheadSettingReportKeuangan(String coa) {
//        logger.info("[SettingReportKeuanganKonsolAction.initTypeaheadSettingReportKeuangan] start process >>>");
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganBo = (AkunSettingReportKeuanganKonsolBo) ctx.getBean("akunSettingReportKeuanganKonsolBoProxy");
//        List<AkunSettingReportKeuanganKonsol> akunSettingReportKeuanganList = new ArrayList();
//        try {
//            akunSettingReportKeuanganList = akunSettingReportKeuanganBo.typeaheadAkunSettingReportKeuanganKonsol(coa);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = akunSettingReportKeuanganBo.saveErrorMessage(e.getMessage(), "SettingReportKeuanganBO.typeaheadSettingReportKeuangan");
//            } catch (GeneralBOException e1) {
//                logger.error("[SettingReportKeuanganKonsolAction.initTypeaheadSettingReportKeuangan] Error when saving error,", e1);
//            }
//            logger.error("[SettingReportKeuanganKonsolAction.initTypeaheadSettingReportKeuangan] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
//        }
//        return akunSettingReportKeuanganList;
//    }

    public List<AkunSettingReportKeuanganKonsol> initSettingReportKeuanganKonsolSearch(String reportId, String namaReport, String coa) {
        logger.info("[SettingReportKeuanganKonsolAction.initSettingReportKeuanganKonsolSearch] start process >>>");

        List<AkunSettingReportKeuanganKonsol> listOfsearchReportKeuangan = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AkunSettingReportKeuanganKonsolBo akunSettingReportKeuanganKonsolBo = (AkunSettingReportKeuanganKonsolBo) ctx.getBean("akunSettingReportKeuanganKonsolBoProxy");

        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        search.setKodeRekeningAlias(coa);
        search.setSettingReportKonsolId(reportId);
        search.setNamaKodeRekeningAlias(namaReport);
        search.setFlag("Y");

        try {
            listOfsearchReportKeuangan = akunSettingReportKeuanganKonsolBo.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBo.saveErrorMessage(e.getMessage(), "akunSettingReportKeuanganKonsolBo.getDataStrukturCoa");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportKeuanganKonsolDetailAction.initSettingReportKeuanganKonsolSearch] Error when saving error,", e1);
            }
            logger.error("[SettingReportKeuanganKonsolDetailAction.initSettingReportKeuanganKonsolSearch] Error when searching Seting Report Keuangan Konsol by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }

        logger.info("[SettingReportKeuanganKonsolDetailAction.initSettingReportKeuanganKonsolSearch] end process <<<");
        return listOfsearchReportKeuangan;
    }

    @Override
    public String add() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.add] start process >>>");
        AkunSettingReportKeuanganKonsol addAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();
        setAkunSettingReportKeuanganKonsol(addAkunSettingReportKeuanganKonsol);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[AkunSettingReportKeuanganKonsolAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] start process >>>");
        String reportKonsolId = getId();
        List<AkunSettingReportKeuanganKonsol> reportKeuanganKonsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> reportKeuanganKonsolDetailList = new ArrayList<>();
        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        search.setSettingReportKonsolId(reportKonsolId);
        search.setFlag("Y");

        AkunSettingReportKeuanganKonsolDetail detail = new AkunSettingReportKeuanganKonsolDetail();
        detail.setSettingReportKonsolId(reportKonsolId);
        detail.setFlag("Y");

        try {
            reportKeuanganKonsolList = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(search);
            reportKeuanganKonsolDetailList = akunSettingReportKeuanganKonsolDetailBoProxy.getByCriteria(detail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolAction.edit");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        AkunSettingReportKeuanganKonsol edit = new AkunSettingReportKeuanganKonsol();
        for (AkunSettingReportKeuanganKonsol konsol : reportKeuanganKonsolList) {
            edit.setSettingReportKonsolId(konsol.getSettingReportKonsolId());
            edit.setKodeRekeningAlias(konsol.getKodeRekeningAlias());
            edit.setNamaKodeRekeningAlias(konsol.getNamaKodeRekeningAlias());
            edit.setFlagLabel(konsol.getFlagLabel());
            break;
        }
        akunSettingReportKeuanganKonsol = edit;
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKonsolDetail", reportKeuanganKonsolDetailList);

        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] end process <<<");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] start process >>>");
        String reportKonsolId = getId();
        List<AkunSettingReportKeuanganKonsol> reportKeuanganKonsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> reportKeuanganKonsolDetailList = new ArrayList<>();
        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        search.setSettingReportKonsolId(reportKonsolId);
        search.setFlag("Y");

        AkunSettingReportKeuanganKonsolDetail detail = new AkunSettingReportKeuanganKonsolDetail();
        detail.setSettingReportKonsolId(reportKonsolId);
        detail.setFlag("Y");

        try {
            reportKeuanganKonsolList = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(search);
            reportKeuanganKonsolDetailList = akunSettingReportKeuanganKonsolDetailBoProxy.getByCriteria(detail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolAction.edit");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        AkunSettingReportKeuanganKonsol edit = new AkunSettingReportKeuanganKonsol();
        for (AkunSettingReportKeuanganKonsol konsol : reportKeuanganKonsolList) {
            edit.setSettingReportKonsolId(konsol.getSettingReportKonsolId());
            edit.setKodeRekeningAlias(konsol.getKodeRekeningAlias());
            edit.setNamaKodeRekeningAlias(konsol.getNamaKodeRekeningAlias());
            edit.setFlagLabel(konsol.getFlagLabel());
            break;
        }
        akunSettingReportKeuanganKonsol = edit;
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKonsolDetail", reportKeuanganKonsolDetailList);

        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {

        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] start process >>>");
        String reportKonsolId = getId();
        List<AkunSettingReportKeuanganKonsol> reportKeuanganKonsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> reportKeuanganKonsolDetailList = new ArrayList<>();
        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        search.setSettingReportKonsolId(reportKonsolId);
        search.setFlag("Y");

        AkunSettingReportKeuanganKonsolDetail detail = new AkunSettingReportKeuanganKonsolDetail();
        detail.setSettingReportKonsolId(reportKonsolId);
        detail.setFlag("Y");

        try {
            reportKeuanganKonsolList = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(search);
            reportKeuanganKonsolDetailList = akunSettingReportKeuanganKonsolDetailBoProxy.getByCriteria(detail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolAction.edit");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.edit] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        AkunSettingReportKeuanganKonsol edit = new AkunSettingReportKeuanganKonsol();
        for (AkunSettingReportKeuanganKonsol konsol : reportKeuanganKonsolList) {
            edit.setSettingReportKonsolId(konsol.getSettingReportKonsolId());
            edit.setKodeRekeningAlias(konsol.getKodeRekeningAlias());
            edit.setNamaKodeRekeningAlias(konsol.getNamaKodeRekeningAlias());
            edit.setFlagLabel(konsol.getFlagLabel());
            break;
        }
        akunSettingReportKeuanganKonsol = edit;
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("listOfResultKonsolDetail", reportKeuanganKonsolDetailList);

        logger.info("[AkunSettingReportKeuanganKonsolAction.edit] end process <<<");
        return "init_view";

//        logger.info("[AkunSettingReportKeuanganKonsolAction.view] start process >>>");
//
//        String itemId = getId();
//        String itemFlag = getFlag();
//        AkunSettingReportKeuanganKonsol deleteAkunSettingReportKeuanganKonsol = new AkunSettingReportKeuanganKonsol();
//
//        if (itemFlag != null ) {
//            try {
//                deleteAkunSettingReportKeuanganKonsol = init(itemId, itemFlag);
//            } catch (GeneralBOException e) {
//                Long logId = null;
//                try {
//                    logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getAlatById");
//                } catch (GeneralBOException e1) {
//                    logger.error("[AkunSettingReportKeuanganKonsolAction.view] Error when retrieving delete data,", e1);
//                }
//                logger.error("[AkunSettingReportKeuanganKonsolAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
//                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
//                return "failure";
//            }
//
//            if (deleteAkunSettingReportKeuanganKonsol != null) {
//                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
//
//            } else {
//                deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
//                deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
//                setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
//                addActionError("Error, Unable to find data with id = " + itemId);
//                return "failure";
//            }
//        } else {
//            deleteAkunSettingReportKeuanganKonsol.setSettingReportKonsolId(itemId);
//            deleteAkunSettingReportKeuanganKonsol.setFlag(itemFlag);
//            setAkunSettingReportKeuanganKonsol(deleteAkunSettingReportKeuanganKonsol);
//            addActionError("Error, Unable to delete again with flag = N.");
//            return "failure";
//        }
//        logger.info("[AkunSettingReportKeuanganKonsolAction.view] end process <<<");
//        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveEdit] start process >>>");
        try {
            AkunSettingReportKeuanganKonsol editAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editAkunSettingReportKeuanganKonsol.setLastUpdateWho(userLogin);
            editAkunSettingReportKeuanganKonsol.setLastUpdate(updateTime);
            editAkunSettingReportKeuanganKonsol.setCreatedDate(updateTime);
            editAkunSettingReportKeuanganKonsol.setCreatedWho(userLogin);
            editAkunSettingReportKeuanganKonsol.setAction("U");
            editAkunSettingReportKeuanganKonsol.setFlag("Y");

            akunSettingReportKeuanganKonsolBoProxy.saveEdit(editAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[AkunSettingReportKeuanganKonsolAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveDelete] start process >>>");
        try {
            AkunSettingReportKeuanganKonsol deleteAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteAkunSettingReportKeuanganKonsol.setLastUpdate(updateTime);
            deleteAkunSettingReportKeuanganKonsol.setLastUpdateWho(userLogin);
            deleteAkunSettingReportKeuanganKonsol.setAction("U");
            deleteAkunSettingReportKeuanganKonsol.setFlag("N");

            akunSettingReportKeuanganKonsolBoProxy.saveDelete(deleteAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[AkunSettingReportKeuanganKonsolAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.saveAdd] start process >>>");

        try {
            AkunSettingReportKeuanganKonsol data = getAkunSettingReportKeuanganKonsol();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setCreatedWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setCreatedDate(updateTime);
            data.setLastUpdateWho(userLogin);
            data.setAction("C");
            data.setFlag("Y");

            akunSettingReportKeuanganKonsolBoProxy.saveAdd(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "akunSettingReportKeuanganKonsolBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportKeuanganKonsolAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SettingReportKeuanganKonsolAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKeuanganKonsol");

        session.removeAttribute("listOfResultKonsolDetail");
        session.removeAttribute("listOfResultKonsolDetailEdit");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

//    public String saveAdd(){
//        logger.info("[AkunSettingReportKeuanganKonsolAction.saveAdd] start process >>>");
//
//        try {
//            AkunSettingReportKeuanganKonsol trans = getAkunSettingReportKeuanganKonsol();
//            String userLogin = CommonUtil.userLogin();
//            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//
//            trans.setCreatedWho(userLogin);
//            trans.setLastUpdate(updateTime);
//            trans.setCreatedDate(updateTime);
//            trans.setLastUpdateWho(userLogin);
//            trans.setAction("C");
//            trans.setFlag("Y");
//
//            akunSettingReportKeuanganKonsolBoProxy.saveAdd(trans);
//        }catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
//            } catch (GeneralBOException e1) {
//                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
//                return ERROR;
//            }
//            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
//            return ERROR;
//        }
//
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");
//
//        logger.info("[liburAction.saveAdd] end process >>>");
//        return "success_save_add";
//    }

    @Override
    public String search() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.search] start process >>>");
        AkunSettingReportKeuanganKonsol searchAkunSettingReportKeuanganKonsol = getAkunSettingReportKeuanganKonsol();
        List<AkunSettingReportKeuanganKonsol> listOfsearchAkunSettingReportKeuanganKonsol = new ArrayList();
        try {
            listOfsearchAkunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(searchAkunSettingReportKeuanganKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKonsolDetail");
        session.removeAttribute("listOfResultKonsolDetailEdit");
        session.removeAttribute("listOfResultKeuanganKonsol");
        session.setAttribute("listOfResultKeuanganKonsol", listOfsearchAkunSettingReportKeuanganKonsol);

        logger.info("[AkunSettingReportKeuanganKonsolAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKeuanganKonsol");

        logger.info("[AkunSettingReportKeuanganKonsolAction.initForm] end process >>>");
        return INPUT;
    }

    public String initComboAkunSettingReportKeuanganKonsol() {
        logger.info("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] start process >>>");

        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        List<AkunSettingReportKeuanganKonsol> listOfSearchAkunSettingReportKeuanganKonsol = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchAkunSettingReportKeuanganKonsol = akunSettingReportKeuanganKonsolBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunSettingReportKeuanganKonsolBoProxy.saveErrorMessage(e.getMessage(), "transBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] Error when saving error,", e1);
            }
            logger.error("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfSearchAkunSettingReportKeuanganKonsol.addAll(listOfSearchAkunSettingReportKeuanganKonsol);
        logger.info("[AkunSettingReportKeuanganKonsolAction.initComboAkunSettingReportKeuanganKonsol] end process <<<");

        return SUCCESS;
    }

//    public List<KodeRekening> initTypeaheadKodeRekening(String coa) {
//        logger.info("[KodeRekeningAction.initTypeaheadKodeRekening] start process >>>");
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
//        List<KodeRekening> kodeRekeningList = new ArrayList();
//        try {
//            kodeRekeningList = kodeRekeningBo.typeaheadKodeRekening(coa);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
//            } catch (GeneralBOException e1) {
//                logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when saving error,", e1);
//            }
//            logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//        }
//        return kodeRekeningList;
//    }

    public String paging() {
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }

    public String cekBeforeSave(String kodeRekening, String metode) {
        String status = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AkunSettingReportKeuanganKonsolBo keuanganKonsolBo = (AkunSettingReportKeuanganKonsolBo) ctx.getBean("akunSettingReportKeuanganKonsolBoProxy");
        List<AkunSettingReportKeuanganKonsol> keuanganKonsolList = new ArrayList<>();
        AkunSettingReportKeuanganKonsol search = new AkunSettingReportKeuanganKonsol();
        search.setFlag("Y");
        search.setKodeRekeningAlias(kodeRekening);
        try {
            if (("add").equalsIgnoreCase(metode)) {
                keuanganKonsolList = keuanganKonsolBo.getByDataCriteria(search);
            }
        } catch (GeneralBOException e1) {
            logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when saving error,", e1);
        }
        if (keuanganKonsolList.size() != 0) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<AkunSettingReportKeuanganKonsol> listOfsearch = (List<AkunSettingReportKeuanganKonsol>) session.getAttribute("listOfResultKonsolDetail");

            if (listOfsearch == null) {
                status = "Belum ada report keuangan konsol detail, silahkan ditambahkan";
            }
        } else {
            status = "Parent Kode Rekening Alias Tidak Ada";
        }
        return status;
    }

    public void saveKonsolDetailSession(String idRekeneing, String namaKodeRekening, String operator) {
        logger.info("[SettingReportKeuanganKonsolAction.saveKoderingSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AkunSettingReportKeuanganKonsolDetail> listOfResult = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetail");
        List<AkunSettingReportKeuanganKonsolDetail> listOfResultEdit = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetailEdit");

        if (listOfResult == null) {
            listOfResult = new ArrayList<>();
        }

        AkunSettingReportKeuanganKonsolDetail result = new AkunSettingReportKeuanganKonsolDetail();
        result.setRekeningId(idRekeneing);
        result.setNamaRekening(namaKodeRekening);
        result.setOperator(operator);
        if (operator.equalsIgnoreCase("T"))
            result.setSbOperator("+");
        else
            result.setSbOperator("-");
        listOfResult.add(result);

        if (listOfResultEdit != null) {
            AkunSettingReportKeuanganKonsolDetail result1 = new AkunSettingReportKeuanganKonsolDetail();
            result1.setRekeningId(idRekeneing);
            result1.setNamaRekening(namaKodeRekening);
            result1.setOperator(operator);
            if (operator.equalsIgnoreCase("T"))
                result1.setSbOperator("+");
            else
                result1.setSbOperator("-");
            listOfResultEdit.add(result1);
        } else {
            listOfResultEdit = new ArrayList<>();
            AkunSettingReportKeuanganKonsolDetail result1 = new AkunSettingReportKeuanganKonsolDetail();
            result1.setRekeningId(idRekeneing);
            result1.setNamaRekening(namaKodeRekening);
            result1.setOperator(operator);
            if (operator.equalsIgnoreCase("T"))
                result1.setSbOperator("+");
            else
                result1.setSbOperator("-");
            listOfResultEdit.add(result1);
        }

        session.setAttribute("listOfResultKonsolDetail", listOfResult);
        session.setAttribute("listOfResultKonsolDetailEdit", listOfResultEdit);
        logger.info("[SettingReportKeuanganKonsolAction.saveKonsolDetailSession] end process <<<");
    }

    public List<AkunSettingReportKeuanganKonsolDetail> searchKonsolDetailSession() {
        logger.info("[SettingReportKeuanganKonsolAction.searchKonsolDetailSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AkunSettingReportKeuanganKonsolDetail> listOfsearch = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetail");
        return listOfsearch;
    }

    public String deleteSessionKonsolDetail(String rekeningId) {
        logger.info("[SettingReportKeuanganKonsolAction.deleteSessionKonsolDetail] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AkunSettingReportKeuanganKonsolDetail> konsolDetailList = (List<AkunSettingReportKeuanganKonsolDetail>) session.getAttribute("listOfResultKonsolDetail");
        List<AkunSettingReportKeuanganKonsolDetail> konsolDetailArrayList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> konsolDetailArrayListEdit = new ArrayList<>();

        for (AkunSettingReportKeuanganKonsolDetail keuanganKonsolDetail : konsolDetailList) {
            if (keuanganKonsolDetail.getRekeningId().equalsIgnoreCase(rekeningId)) {
                keuanganKonsolDetail.setFlag("N");
            } else {
                keuanganKonsolDetail.setFlag("Y");
                konsolDetailArrayList.add(keuanganKonsolDetail);
            }

//            AkunSettingReportKeuanganKonsolDetail detail = new AkunSettingReportKeuanganKonsolDetail();
//            keuanganKonsolDetail.setRekeningId(keuanganKonsolDetail.getRekeningId());
//            keuanganKonsolDetail.setOperator(keuanganKonsolDetail.getOperator());
            konsolDetailArrayListEdit.add(keuanganKonsolDetail);
        }

        session.setAttribute("listOfResultKonsolDetailEdit", konsolDetailArrayListEdit);
        session.setAttribute("listOfResultKonsolDetail", konsolDetailArrayList);
        logger.info("[SettingReportKeuanganKonsolAction.deleteSessionKonsolDetail] end process >>>");
        return status;
    }

    public List<AkunSettingReportKeuanganKonsol> cekAvailableCoa(String nilai) {
        logger.info("[SettingReportKeuanganKonsolAction.cekAvailableCoa] start process >>>");

        AkunSettingReportKeuanganKonsol searchReportKonsol = new AkunSettingReportKeuanganKonsol();
        searchReportKonsol.setFlag("Y");
        searchReportKonsol.setKodeRekeningAlias(nilai);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AkunSettingReportKeuanganKonsolBo reportKonsolBo = (AkunSettingReportKeuanganKonsolBo) ctx.getBean("AkunSettingReportKeuanganKonsolBoProxy");
        List<AkunSettingReportKeuanganKonsol> reportKonsolList = new ArrayList();
        try {
            reportKonsolList = reportKonsolBo.getByCriteria(searchReportKonsol);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportKonsolBo.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SettingReportKeuanganKonsolBoAction.cekAvailableCoa] Error when saving error,", e1);
            }
            logger.error("[SettingReportKeuanganKonsolBoAction.cekAvailableCoa] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }

        return reportKonsolList;
    }

    public boolean cekAvailableParent(String nilai) {
        logger.info("[SettingReportKeuanganKonsolBoAction.cekAvailableParent] start process >>>");
        boolean adaParent = true;

        String[] coa = nilai.split("\\.");
        String coaParent = "";
        for (int i = 0; i < coa.length; i++) {
            if (i == 0) {
                coaParent = coaParent + coa[i];
            } else {
                AkunSettingReportKeuanganKonsol searchReportKonsol = new AkunSettingReportKeuanganKonsol();
                searchReportKonsol.setFlag("Y");
                searchReportKonsol.setKodeRekeningAlias(nilai);

                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                AkunSettingReportKeuanganKonsolBo reportKonsolBo = (AkunSettingReportKeuanganKonsolBo) ctx.getBean("AkunSettingReportKeuanganKonsolBoProxy");
                List<AkunSettingReportKeuanganKonsol> reportKonsolList = new ArrayList();
                try {
                    reportKonsolList = reportKonsolBo.getByCriteria(searchReportKonsol);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = reportKonsolBo.saveErrorMessage(e.getMessage(), "AkunSettingReportKeuanganKonsolBO.getByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[SettingReportKeuanganKonsolAction.cekAvailableParent] Error when saving error,", e1);
                    }
                    logger.error("[SettingReportKeuanganKonsolAction.cekAvailableParent] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                }
                if (reportKonsolList.size() == 0) {
                    adaParent = false;
                    break;
                }
                coaParent = coaParent + "." + coa[i];
            }

        }
        return adaParent;
    }
}