package com.neurix.akuntansi.transaksi.pengajuanBiaya.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.bo.PengajuanBiayaBo;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PengajuanBiayaAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PengajuanBiayaAction.class);
    private PengajuanBiayaBo pengajuanBiayaBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private MappingJurnalBo mappingJurnalBoProxy;
    private TipeJurnalBo tipeJurnalBoProxy;
    private KodeRekeningBo kodeRekeningBoProxy;
    private PengajuanBiaya pengajuanBiaya;
    private List<PengajuanBiaya> listOfComboPengajuanBiaya = new ArrayList<PengajuanBiaya>();

    public KodeRekeningBo getKodeRekeningBoProxy() {
        return kodeRekeningBoProxy;
    }

    public void setKodeRekeningBoProxy(KodeRekeningBo kodeRekeningBoProxy) {
        this.kodeRekeningBoProxy = kodeRekeningBoProxy;
    }

    public MappingJurnalBo getMappingJurnalBoProxy() {
        return mappingJurnalBoProxy;
    }

    public void setMappingJurnalBoProxy(MappingJurnalBo mappingJurnalBoProxy) {
        this.mappingJurnalBoProxy = mappingJurnalBoProxy;
    }

    public TipeJurnalBo getTipeJurnalBoProxy() {
        return tipeJurnalBoProxy;
    }

    public void setTipeJurnalBoProxy(TipeJurnalBo tipeJurnalBoProxy) {
        this.tipeJurnalBoProxy = tipeJurnalBoProxy;
    }

    public BillingSystemBo getBillingSystemBoProxy() {
        return billingSystemBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public List<PengajuanBiaya> getListOfComboPengajuanBiaya() {
        return listOfComboPengajuanBiaya;
    }

    public void setListOfComboPengajuanBiaya(List<PengajuanBiaya> listOfComboPengajuanBiaya) {
        this.listOfComboPengajuanBiaya = listOfComboPengajuanBiaya;
    }


    public PengajuanBiayaBo getPengajuanBiayaBoProxy() {
        return pengajuanBiayaBoProxy;
    }

    public void setPengajuanBiayaBoProxy(PengajuanBiayaBo pengajuanBiayaBoProxy) {
        this.pengajuanBiayaBoProxy = pengajuanBiayaBoProxy;
    }

    public PengajuanBiaya getPengajuanBiaya() {
        return pengajuanBiaya;
    }

    public void setPengajuanBiaya(PengajuanBiaya pengajuanBiaya) {
        this.pengajuanBiaya = pengajuanBiaya;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PengajuanBiayaAction.logger = logger;
    }


    public String initComboPengajuanBiaya() {
        logger.info("[PengajuanBiayaAction.initComboPengajuanBiaya] start process >>>");

        PengajuanBiaya search = new PengajuanBiaya();
        List<PengajuanBiaya> pengajuanBiayaList = new ArrayList();
        search.setFlag("Y");
        try {
            pengajuanBiayaList = pengajuanBiayaBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.initComboPengajuanBiaya] Error when saving error,", e1);
            }
            logger.error("[PengajuanBiayaAction.initComboPengajuanBiaya] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboPengajuanBiaya.addAll(pengajuanBiayaList);
        logger.info("[PengajuanBiayaAction.initComboPengajuanBiaya] end process <<<");
        return SUCCESS;
    }

    public PengajuanBiaya init(String kode, String flag){
        logger.info("[PengajuanBiayaAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiaya> listOfResult = (List<PengajuanBiaya>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PengajuanBiaya pengajuanBiaya: listOfResult) {
                    if(kode.equalsIgnoreCase(pengajuanBiaya.getPengajuanBiayaId()) && flag.equalsIgnoreCase(pengajuanBiaya.getFlag())){
                        setPengajuanBiaya(pengajuanBiaya);
                        break;
                    }
                }
            } else {
                setPengajuanBiaya(new PengajuanBiaya());
            }

            logger.info("[PengajuanBiayaAction.init] end process >>>");
        }
        return getPengajuanBiaya();
    }

    @Override
    public String add() {
        logger.info("[PengajuanBiayaAction.add] start process >>>");
        PengajuanBiaya addPengajuanBiaya = new PengajuanBiaya();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PengajuanBiayaAction.add] stop process >>>");
        return "init_add";
    }

    public String addPemasukan() {
        logger.info("[PengajuanBiayaAction.addPemasukan] start process >>>");
        PengajuanBiaya addPengajuanBiaya = new PengajuanBiaya();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PengajuanBiayaAction.addPemasukan] stop process >>>");
        return "init_add_pemasukan";
    }

    public String addKoreksi() {
        logger.info("[PengajuanBiayaAction.addKoreksi] start process >>>");
        PengajuanBiaya addPengajuanBiaya = new PengajuanBiaya();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PengajuanBiayaAction.addKoreksi] stop process >>>");
        return "init_add_koreksi";
    }

    @Override
    public String edit() {
        logger.info("[PengajuanBiayaAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PengajuanBiaya editPengajuanBiaya = new PengajuanBiaya();

        if(itemFlag != null){
            try {
                editPengajuanBiaya = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.getPengajuanBiayaByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PengajuanBiayaAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PengajuanBiayaAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPengajuanBiaya != null) {
                setPengajuanBiaya(editPengajuanBiaya);
            } else {
                editPengajuanBiaya.setFlag(itemFlag);
                editPengajuanBiaya.setPengajuanBiayaId(itemId);
                setPengajuanBiaya(editPengajuanBiaya);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPengajuanBiaya.setPengajuanBiayaId(itemId);
            editPengajuanBiaya.setFlag(getFlag());
            setPengajuanBiaya(editPengajuanBiaya);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        HttpSession session = ServletActionContext.getRequest().getSession();

        logger.info("[PengajuanBiayaAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PengajuanBiayaAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PengajuanBiaya deletePengajuanBiaya = new PengajuanBiaya();

        if (itemFlag != null ) {

            try {
                deletePengajuanBiaya = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PengajuanBiayaAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PengajuanBiayaAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePengajuanBiaya != null) {
                setPengajuanBiaya(deletePengajuanBiaya);

            } else {
                deletePengajuanBiaya.setPengajuanBiayaId(itemId);
                deletePengajuanBiaya.setFlag(itemFlag);
                setPengajuanBiaya(deletePengajuanBiaya);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePengajuanBiaya.setPengajuanBiayaId(itemId);
            deletePengajuanBiaya.setFlag(itemFlag);
            setPengajuanBiaya(deletePengajuanBiaya);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PengajuanBiayaAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PengajuanBiayaAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PengajuanBiaya deletePengajuanBiaya = new PengajuanBiaya();

        if (itemFlag != null ) {
            try {
                deletePengajuanBiaya = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PengajuanBiayaAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PengajuanBiayaAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePengajuanBiaya != null) {
                setPengajuanBiaya(deletePengajuanBiaya);

            } else {
                deletePengajuanBiaya.setPengajuanBiayaId(itemId);
                deletePengajuanBiaya.setFlag(itemFlag);
                setPengajuanBiaya(deletePengajuanBiaya);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePengajuanBiaya.setPengajuanBiayaId(itemId);
            deletePengajuanBiaya.setFlag(itemFlag);
            setPengajuanBiaya(deletePengajuanBiaya);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PengajuanBiayaAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PengajuanBiayaAction.saveEdit] start process >>>");
        try {
            PengajuanBiaya editPengajuanBiaya = getPengajuanBiaya();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPengajuanBiaya.setLastUpdateWho(userLogin);
            editPengajuanBiaya.setLastUpdate(updateTime);
            editPengajuanBiaya.setAction("U");
            editPengajuanBiaya.setFlag("Y");

            pengajuanBiayaBoProxy.saveEdit(editPengajuanBiaya);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanBiayaAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PengajuanBiayaAction.saveDelete] start process >>>");
        try {
            PengajuanBiaya deletePengajuanBiaya = getPengajuanBiaya();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePengajuanBiaya.setLastUpdate(updateTime);
            deletePengajuanBiaya.setLastUpdateWho(userLogin);
            deletePengajuanBiaya.setAction("U");
            deletePengajuanBiaya.setFlag("N");

            pengajuanBiayaBoProxy.saveDelete(deletePengajuanBiaya);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanBiayaAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PengajuanBiayaAction.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal="";

        PengajuanBiaya pengajuanBiaya = getPengajuanBiaya();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        pengajuanBiaya.setCreatedWho(userLogin);
        pengajuanBiaya.setLastUpdate(updateTime);
        pengajuanBiaya.setCreatedDate(updateTime);
        pengajuanBiaya.setLastUpdateWho(userLogin);
        pengajuanBiaya.setAction("C");
        pengajuanBiaya.setFlag("Y");

        try {

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PengajuanBiayaAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PengajuanBiayaAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[PengajuanBiayaAction.search] start process >>>");
        PengajuanBiaya searchPengajuanBiaya = getPengajuanBiaya();
        List<PengajuanBiaya> listOfsearchPengajuanBiaya = new ArrayList();
        try {
            listOfsearchPengajuanBiaya = pengajuanBiayaBoProxy.getByCriteria(searchPengajuanBiaya);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listPembayaranDetailModal");
        session.setAttribute("listOfResult", listOfsearchPengajuanBiaya);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanBiaya.setBranchId(branchId);
        }else{
            searchPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(searchPengajuanBiaya);
        logger.info("[PengajuanBiayaAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PengajuanBiayaAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanBiaya data = new PengajuanBiaya();
        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }
        setPengajuanBiaya(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PengajuanBiayaAction.initForm] end process >>>");
        return INPUT;
    }
    public String paging(){
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
}
