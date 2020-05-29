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
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
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
    private NotifikasiBo notifikasiBoProxy;
    private PengajuanBiaya pengajuanBiaya;
    private List<PengajuanBiaya> listOfComboPengajuanBiaya = new ArrayList<PengajuanBiaya>();

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

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
        addPengajuanBiaya.setBranchIdKanpus("KP");
        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PengajuanBiayaAction.add] stop process >>>");
        return "init_add";
    }

    public String addPengajuan() {
        logger.info("[PengajuanBiayaAction.addPengajuan] start process >>>");
        PengajuanBiaya addPengajuanBiaya = new PengajuanBiaya();
        String branchId = CommonUtil.userBranchLogin();
        String divisiId = CommonUtil.userPosisiId();

        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
            addPengajuanBiaya.setDivisiId(divisiId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PengajuanBiayaAction.addPengajuan] stop process >>>");
        return "init_add_pengajuan";
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
        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(pengajuanBiaya.getStTotalBiaya().replace(".","")));
        pengajuanBiaya.setTotalBiaya(bayar);
        pengajuanBiaya.setCreatedWho(userLogin);
        pengajuanBiaya.setLastUpdate(updateTime);
        pengajuanBiaya.setCreatedDate(updateTime);
        pengajuanBiaya.setLastUpdateWho(userLogin);
        pengajuanBiaya.setAction("C");
        pengajuanBiaya.setFlag("Y");

        String branchId = "";
        if (("SMK").equalsIgnoreCase(pengajuanBiaya.getTransaksi())){
            branchId = CommonConstant.ID_KANPUS;
        }else if (("PDU").equalsIgnoreCase(pengajuanBiaya.getTransaksi())){
            branchId = CommonConstant.ID_KANPUS;
        }

        //membuat mapping
        Map dataMap = new HashMap();

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",bayar);
        rkUnit.put("rekening_id",kodeRekeningBoProxy.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaAjuan()));
        dataMap.put("rk_kd_unit",rkUnit);

        Map giro = new HashMap();
        giro.put("nilai",bayar);
        giro.put("rekening_id",kodeRekeningBoProxy.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaTarget()));
        dataMap.put("metode_bayar",giro);

        try {
            noJurnal= billingSystemBoProxy.createJurnal(pengajuanBiaya.getTipeTransaksi(),dataMap,branchId,pengajuanBiaya.getKeterangan(),"Y");

            pengajuanBiaya.setNoJurnal(noJurnal);
            List<Notifikasi> notif = pengajuanBiayaBoProxy.saveAddPengajuanBiaya(pengajuanBiaya);

            for (Notifikasi notifikasi : notif ){
                notifikasiBoProxy.sendNotif(notifikasi);
            }

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

        logger.info("[PengajuanBiayaAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveAddPengajuan(){
        logger.info("[PengajuanBiayaAction.saveAddPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal="";

        PengajuanBiaya pengajuanBiaya = getPengajuanBiaya();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(pengajuanBiaya.getStTotalBiaya().replace(".","")));
        BigDecimal sisaBudget = BigDecimal.valueOf(Double.valueOf(pengajuanBiaya.getStBudgetSaatIni().replace(".","")));
        BigDecimal budgetTerpakai = BigDecimal.valueOf(Double.valueOf(pengajuanBiaya.getStBudgetTerpakai().replace(".","")));
        pengajuanBiaya.setTotalBiaya(bayar);
        pengajuanBiaya.setBudgetSaatIni(sisaBudget);
        pengajuanBiaya.setBudgetTerpakai(budgetTerpakai);
        pengajuanBiaya.setCreatedWho(userLogin);
        pengajuanBiaya.setLastUpdate(updateTime);
        pengajuanBiaya.setCreatedDate(updateTime);
        pengajuanBiaya.setLastUpdateWho(userLogin);
        pengajuanBiaya.setAction("C");
        pengajuanBiaya.setFlag("Y");

        String branchId = pengajuanBiaya.getBranchId();

        //membuat mapping
        Map dataMap = new HashMap();

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",bayar);
        rkUnit.put("rekening_id",kodeRekeningBoProxy.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaAjuan()));
        dataMap.put("rk_kd_unit",rkUnit);

        Map giro = new HashMap();
        giro.put("nilai",bayar);
        giro.put("rekening_id",kodeRekeningBoProxy.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaTarget()));
        dataMap.put("metode_bayar",giro);

        try {
            noJurnal= billingSystemBoProxy.createJurnal(pengajuanBiaya.getTipeTransaksi(),dataMap,branchId,pengajuanBiaya.getKeterangan(),"Y");

            pengajuanBiaya.setNoJurnal(noJurnal);
            List<Notifikasi> notif = pengajuanBiayaBoProxy.saveAddPengajuanBiaya(pengajuanBiaya);

            for (Notifikasi notifikasi : notif ){
                notifikasiBoProxy.sendNotif(notifikasi);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaAction.saveAddPengajuan");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.saveAddPengajuan] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PengajuanBiayaAction.saveAddPengajuan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");

        logger.info("[PengajuanBiayaAction.saveAddPengajuan] end process >>>");
        return "success_save_add_pengajuan";
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

    public String searchPengajuan() {
        logger.info("[PengajuanBiayaAction.searchPengajuan] start process >>>");
        PengajuanBiaya searchPengajuanBiaya = getPengajuanBiaya();
        List<PengajuanBiaya> listOfsearchPengajuanBiaya = new ArrayList();
        try {
            listOfsearchPengajuanBiaya = pengajuanBiayaBoProxy.getByCriteria(searchPengajuanBiaya);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.searchPengajuan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.searchPengajuan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPengajuanBiaya);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanBiaya.setBranchId(branchId);
        }else{
            searchPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(searchPengajuanBiaya);
        logger.info("[PengajuanBiayaAction.searchPengajuan] end process <<<");

        return "success_pengajuan";
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
        logger.info("[PengajuanBiayaAction.initForm] end process >>>");
        return INPUT;
    }

    public String initFormPengajuan() {
        logger.info("[PengajuanBiayaAction.initFormPengajuan] start process >>>");
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
        logger.info("[PengajuanBiayaAction.initFormPengajuan] end process >>>");
        return "input_pengajuan";
    }

    public List<PengajuanBiaya> approveAtasan(String id) {
        logger.info("[PengajuanBiayaAction.approveAtasan] start process >>>");
        String itemFlag = "Y";
        List<PengajuanBiaya> pengajuanBiayaList = new ArrayList<PengajuanBiaya>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");

        if(itemFlag != null){
            try {
                PengajuanBiaya search = new PengajuanBiaya();
                search.setFlag("Y");
                search.setPengajuanBiayaId(id);
                pengajuanBiayaList=pengajuanBiayaBo.getByCriteria(search);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaAction.approveAtasan");
                } catch (GeneralBOException e1) {
                    logger.error("[PengajuanBiayaAction.approveAtasan] Error when retrieving edit data,", e1);
                }
                logger.error("[PengajuanBiayaAction.approveAtasan] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
            }
        }

        setAddOrEdit(true);
        logger.info("[PengajuanBiayaAction.approveAtasan] end process >>>");
        return pengajuanBiayaList;
    }
    public String saveApprove(String id, String statusApprove, String who,String coaBank,String coaRk,String jumlah,String tipeTransaksi,String branchId,String keterangan){
        logger.info("[PengajuanBiayaAction.saveApprove] start process >>>");
        PengajuanBiaya editPengajuanBiaya = new PengajuanBiaya();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        editPengajuanBiaya.setPengajuanBiayaId(id);
        if(who.equals("atasan")){
            if(statusApprove.equals("Y")){
                editPengajuanBiaya.setAprovalFlag(statusApprove);
            }else{
                editPengajuanBiaya.setAprovalFlag("N");
            }
        }

        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(jumlah.replace(".","")));

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        editPengajuanBiaya.setLastUpdateWho(userLogin);
        editPengajuanBiaya.setLastUpdate(updateTime);
        editPengajuanBiaya.setAction("U");
        editPengajuanBiaya.setFlag("Y");
        editPengajuanBiaya.setAprovalDate(new Date(updateTime.getTime()));

        try {
            //membuat mapping
            Map dataMap = new HashMap();

            Map rkUnit = new HashMap();
            rkUnit.put("nilai",bayar);
            rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(coaRk));
            dataMap.put("rk_kd_unit",rkUnit);

            Map giro = new HashMap();
            giro.put("nilai",bayar);
            giro.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(coaBank));
            dataMap.put("metode_bayar",giro);

            notifikasiList  = pengajuanBiayaBo.saveApprove(editPengajuanBiaya);

            billingSystemBo.createJurnal(tipeTransaksi,dataMap,branchId,keterangan,"Y");
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBo.saveErrorMessage(e.getMessage(), "PengajuanBiayaAction.saveApprove");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.saveApprove] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.saveApprove] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        logger.info("[PengajuanBiayaAction.saveApprove] end process <<<");

        return "success_save_edit";
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
