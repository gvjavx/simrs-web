package com.neurix.akuntansi.master.reportDetail.action;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.reportDetail.bo.ReportDetailBo;
import com.neurix.akuntansi.master.reportDetail.model.ReportDetail;
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

public class ReportDetailAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(ReportDetailAction.class);
    private ReportDetailBo reportDetailBoProxy;
    private ReportDetail reportDetail;
    private List<ReportDetail> listOfComboReportDetail = new ArrayList<ReportDetail>();

    @Override
    public String add() {
        logger.info("[ReportDetailAction.add] start process >>>");
        ReportDetail addReportDetail = new ReportDetail();
        setReportDetail(addReportDetail);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ReportDetailAction.add] stop process >>>");
        return "init_add";
    }

    public String saveAdd(){
        logger.info("[ReportDetailAction.saveAdd] start process >>>");

        try {
            ReportDetail reportDetail = getReportDetail();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reportDetail.setCreatedWho(userLogin);
            reportDetail.setLastUpdate(updateTime);
            reportDetail.setCreatedDate(updateTime);
            reportDetail.setLastUpdateWho(userLogin);
            reportDetail.setAction("C");
            reportDetail.setFlag("Y");

            reportDetailBoProxy.saveAdd(reportDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBoProxy.saveErrorMessage(e.getMessage(), "reportDetailBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ReportDetailAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ReportDetailAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String addReportDetail(String reportId,String rekeningId){
        logger.info("[ReportDetailAction.addReportDetail] start process >>>");
        String status="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ReportDetailBo reportDetailBo = (ReportDetailBo) ctx.getBean("reportDetailBoProxy");
        try {
            ReportDetail reportDetail = new ReportDetail();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reportDetail.setReportId(reportId);
            reportDetail.setRekeningId(rekeningId);
            reportDetail.setCreatedWho(userLogin);
            reportDetail.setLastUpdate(updateTime);
            reportDetail.setCreatedDate(updateTime);
            reportDetail.setLastUpdateWho(userLogin);
            reportDetail.setAction("C");
            reportDetail.setFlag("Y");

            reportDetailBo.saveAdd(reportDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBoProxy.saveErrorMessage(e.getMessage(), "reportDetailBo.addReportDetail");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.addReportDetail] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ReportDetailAction.addReportDetail] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ReportDetailAction.addReportDetail] end process >>>");
        return status;
    }

    public String deleteReportDetail(String reportId){
        logger.info("[ReportDetailAction.deleteReportDetail] start process >>>");
        String status="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ReportDetailBo reportDetailBo = (ReportDetailBo) ctx.getBean("reportDetailBoProxy");
        try {
            ReportDetail reportDetail = new ReportDetail();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            reportDetail.setReportId(reportId);
            reportDetail.setLastUpdate(updateTime);
            reportDetail.setLastUpdateWho(userLogin);
            reportDetail.setAction("U");
            reportDetail.setFlag("N");

            reportDetailBo.deleteReportDetail(reportDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBoProxy.saveErrorMessage(e.getMessage(), "reportDetailBo.deleteReportDetail");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.deleteReportDetail] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ReportDetailAction.deleteReportDetail] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ReportDetailAction.deleteReportDetail] end process >>>");
        return status;
    }
    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[ReportDetailAction.search] start process >>>");

        ReportDetail searchReportDetail = getReportDetail();
        List<ReportDetail> listOfsearchReportDetail = new ArrayList();

        try {
            listOfsearchReportDetail = reportDetailBoProxy.getByCriteria(searchReportDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBoProxy.saveErrorMessage(e.getMessage(), "reportDetailBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[ReportDetailAction.save] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchReportDetail);

        logger.info("[ReportDetailAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[ReportDetailAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[ReportDetailAction.initForm] end process >>>");
        return INPUT;
    }

    public List<ReportDetail> initReportDetailList(String nilai) {
        logger.info("[ReportDetailAction.initReportDetailList] start process >>>");

        ReportDetail searchReportDetail = new ReportDetail();
        searchReportDetail.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ReportDetailBo reportDetailBo = (ReportDetailBo) ctx.getBean("reportDetailBoProxy");
        List<ReportDetail> reportDetailList = new ArrayList();

        try {
            reportDetailList = reportDetailBo.getByCriteria(searchReportDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.initReportDetailList] Error when saving error,", e1);
            }
            logger.error("[ReportDetailAction.initReportDetailList] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return reportDetailList;
    }

    public List<KodeRekening> initReportDetailSearch(String reportId) {
        logger.info("[ReportDetailAction.initReportDetailSearch] start process >>>");

        List<ReportDetail> listOfsearchReportDetail = new ArrayList();
        List<KodeRekening> listOfsearchKodeRekening = new ArrayList();
        List<KodeRekening> listOfFinal = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ReportDetailBo reportDetailBo= (ReportDetailBo) ctx.getBean("reportDetailBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        ReportDetail searchReportDetail = new ReportDetail();
        searchReportDetail.setFlag("Y");
        searchReportDetail.setReportId(reportId);

        KodeRekening search = new KodeRekening();
        search.setFlag("Y");
        try {
            listOfsearchKodeRekening = kodeRekeningBo.getByCriteria(search);
            listOfsearchReportDetail = reportDetailBo.getByCriteria(searchReportDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBo.saveErrorMessage(e.getMessage(), "reportDetailBo.getDataStrukturCoa");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.initReportDetailSearch] Error when saving error,", e1);
            }
            logger.error("[ReportDetailAction.initReportDetailSearch] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        for (KodeRekening kodeRekening :listOfsearchKodeRekening){
            for (ReportDetail reportDetail : listOfsearchReportDetail){
                if (reportDetail.getRekeningId().equalsIgnoreCase(kodeRekening.getRekeningId())){
                    kodeRekening.setAdaRekeningReport(true);
                    break;
                }
            }
            listOfFinal.add(kodeRekening);
        }

        logger.info("[ReportDetailAction.initReportDetailSearch] end process <<<");
        return listOfFinal;
    }

    public Boolean saveEdit(String id, String reportId,String rekeningId,String tipeEdit){
        logger.info("[ReportDetailAction.saveEdit] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ReportDetail edit = new ReportDetail();
        edit.setReportDetailId(id);
        edit.setReportId(reportId);
        edit.setRekeningId(rekeningId);
        edit.setLastUpdateWho(userLogin);
        edit.setLastUpdate(updateTime);
        edit.setAction("U");
        if ("edit".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("Y");
        }else if ("delete".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("N");
        }
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ReportDetailBo reportDetailBo = (ReportDetailBo) ctx.getBean("reportDetailBoProxy");

        try {
            reportDetailBo.saveEdit(edit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = reportDetailBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[ReportDetailAction.saveEdit] Error when saving error,", e1);
                return false;
            }
            logger.error("[ReportDetailAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return false;
        }
        logger.info("[ReportDetailAction.saveEdit] end process <<<");
        return true;
    }
    public List<ReportDetail> searchReportDetail(String coa) {
        logger.info("[ReportDetailAction.searchReportDetail] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ReportDetail> reportDetailList = (List<ReportDetail>) session.getAttribute("listOfResultReportDetail");
        if (("").equalsIgnoreCase(coa)){
            session.setAttribute("listOfResultReportDetail",reportDetailList);
        }else{
            List<ReportDetail> reportDetails = new ArrayList<>();
            for (ReportDetail reportDetail:reportDetails){
                if (coa.equalsIgnoreCase(reportDetail.getReportDetailId())){
                    reportDetails.add(reportDetail);
                    session.setAttribute("listOfResultReportDetail",reportDetails);
                }
            }
        }
        logger.info("[ReportDetailAction.searchReportDetail] end process >>>");
        return reportDetailList;
    }

    public void saveAddReportDetail(String reportDetail,String reportId,String rekeningId) {
        logger.info("[ReportDetailAction.saveAddReportDetail] start process >>>");
        String posisiName = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ReportDetail> reportDetailList = (List<ReportDetail>) session.getAttribute("listOfResultReportDetail");
        List<ReportDetail> reportDetailArrayList = new ArrayList<>();
        boolean ada=false;
        if (reportDetailList==null){
            ReportDetail newData = new ReportDetail();
            newData.setReportDetailId(reportDetail);
            newData.setReportId(reportId);
            newData.setRekeningId(rekeningId);

            reportDetailArrayList.add(newData);
            session.setAttribute("listOfResultReportDetail",reportDetailArrayList);
        }else{
            reportDetailArrayList.addAll(reportDetailList);
            for (ReportDetail reportDetail1:reportDetailList){
                if (reportDetail1.getReportDetailId().equalsIgnoreCase(reportDetail)){
                    ada=true;
                    break;
                }
            }
            if (!ada){
                ReportDetail newData = new ReportDetail();
                newData.setReportDetailId(reportDetail);
                newData.setReportId(reportId);
                newData.setRekeningId(rekeningId);
                reportDetailArrayList.add(newData);
                session.setAttribute("listOfResultReportDetail",reportDetailArrayList);
            }
        }

        logger.info("[ReportDetailAction.saveAddReportDetail] end process >>>");
    }
    public void saveDeleteReportDetail(String coa) {
        logger.info("[ReportDetailAction.saveDeleteAnggota] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<ReportDetail> reportDetailList = (List<ReportDetail>) session.getAttribute("listOfResultReportDetail");
        List<ReportDetail> reportDetailArrayList = new ArrayList<>();
        for (ReportDetail reportDetail:reportDetailList){
            if (reportDetail.getReportDetailId().equalsIgnoreCase(coa)){
            }else{
                reportDetailArrayList.add(reportDetail);
            }
        }
        session.setAttribute("listOfResultReportDetail",reportDetailArrayList);
        logger.info("[ReportDetailAction.saveDeleteAnggota] end process >>>");
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }

    public String paging(){
        return SUCCESS;
    }

    public List<ReportDetail> getListOfComboReportDetail() {
        return listOfComboReportDetail;
    }

    public void setListOfComboReportDetail(List<ReportDetail> listOfComboReportDetail) {
        this.listOfComboReportDetail = listOfComboReportDetail;
    }


    public ReportDetailBo getReportDetailBoProxy() {
        return reportDetailBoProxy;
    }

    public void setReportDetailBoProxy(ReportDetailBo reportDetailBoProxy) {
        this.reportDetailBoProxy = reportDetailBoProxy;
    }

    public ReportDetail getReportDetail() {
        return reportDetail;
    }

    public void setReportDetail(ReportDetail reportDetail) {
        this.reportDetail = reportDetail;
    }

    private List<ReportDetail> initComboReportDetail;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ReportDetailAction.logger = logger;
    }


    public List<ReportDetail> getInitComboReportDetail() {
        return initComboReportDetail;
    }

    public void setInitComboReportDetail(List<ReportDetail> initComboReportDetail) {
        this.initComboReportDetail = initComboReportDetail;
    }
}
