package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.PembayaranUtangPiutangBo;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
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
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PembayaranUtangPiutangAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PembayaranUtangPiutangAction.class);
    private PembayaranUtangPiutangBo pembayaranUtangPiutangBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private MappingJurnalBo mappingJurnalBoProxy;
    private TipeJurnalBo tipeJurnalBoProxy;
    private KodeRekeningBo kodeRekeningBoProxy;
    private PembayaranUtangPiutang pembayaranUtangPiutang;
    private List<PembayaranUtangPiutang> listOfComboPembayaranUtangPiutang = new ArrayList<PembayaranUtangPiutang>();

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

    public List<PembayaranUtangPiutang> getListOfComboPembayaranUtangPiutang() {
        return listOfComboPembayaranUtangPiutang;
    }

    public void setListOfComboPembayaranUtangPiutang(List<PembayaranUtangPiutang> listOfComboPembayaranUtangPiutang) {
        this.listOfComboPembayaranUtangPiutang = listOfComboPembayaranUtangPiutang;
    }


    public PembayaranUtangPiutangBo getPembayaranUtangPiutangBoProxy() {
        return pembayaranUtangPiutangBoProxy;
    }

    public void setPembayaranUtangPiutangBoProxy(PembayaranUtangPiutangBo pembayaranUtangPiutangBoProxy) {
        this.pembayaranUtangPiutangBoProxy = pembayaranUtangPiutangBoProxy;
    }

    public PembayaranUtangPiutang getPembayaranUtangPiutang() {
        return pembayaranUtangPiutang;
    }

    public void setPembayaranUtangPiutang(PembayaranUtangPiutang pembayaranUtangPiutang) {
        this.pembayaranUtangPiutang = pembayaranUtangPiutang;
    }



    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PembayaranUtangPiutangAction.logger = logger;
    }


    public String initComboPembayaranUtangPiutang() {
        logger.info("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] start process >>>");

        PembayaranUtangPiutang search = new PembayaranUtangPiutang();
        List<PembayaranUtangPiutang> pembayaranUtangPiutangList = new ArrayList();
        search.setFlag("Y");
        try {
            pembayaranUtangPiutangList = pembayaranUtangPiutangBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] Error when saving error,", e1);
            }
            logger.error("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }
        listOfComboPembayaranUtangPiutang.addAll(pembayaranUtangPiutangList);
        logger.info("[PembayaranUtangPiutangAction.initComboPembayaranUtangPiutang] end process <<<");
        return SUCCESS;
    }

    public PembayaranUtangPiutang init(String kode, String flag){
        logger.info("[PembayaranUtangPiutangAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutang> listOfResult = (List<PembayaranUtangPiutang>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PembayaranUtangPiutang pembayaranUtangPiutang: listOfResult) {
                    if(kode.equalsIgnoreCase(pembayaranUtangPiutang.getPembayaranUtangPiutangId()) && flag.equalsIgnoreCase(pembayaranUtangPiutang.getFlag())){
                        setPembayaranUtangPiutang(pembayaranUtangPiutang);
                        break;
                    }
                }
            } else {
                setPembayaranUtangPiutang(new PembayaranUtangPiutang());
            }

            logger.info("[PembayaranUtangPiutangAction.init] end process >>>");
        }
        return getPembayaranUtangPiutang();
    }

    @Override
    public String add() {
        logger.info("[PembayaranUtangPiutangAction.add] start process >>>");
        PembayaranUtangPiutang addPembayaranUtangPiutang = new PembayaranUtangPiutang();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            addPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(addPembayaranUtangPiutang);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.add] stop process >>>");
        return "init_add";
    }

    public String addPemasukan() {
        logger.info("[PembayaranUtangPiutangAction.addPemasukan] start process >>>");
        PembayaranUtangPiutang addPembayaranUtangPiutang = new PembayaranUtangPiutang();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            addPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(addPembayaranUtangPiutang);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.addPemasukan] stop process >>>");
        return "init_add_pemasukan";
    }

    public String addKoreksi() {
        logger.info("[PembayaranUtangPiutangAction.addKoreksi] start process >>>");
        PembayaranUtangPiutang addPembayaranUtangPiutang = new PembayaranUtangPiutang();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            addPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            addPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(addPembayaranUtangPiutang);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.addKoreksi] stop process >>>");
        return "init_add_koreksi";
    }

    @Override
    public String edit() {
        logger.info("[PembayaranUtangPiutangAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        PembayaranUtangPiutang editPembayaranUtangPiutang = new PembayaranUtangPiutang();

        if(itemFlag != null){
            try {
                editPembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getPembayaranUtangPiutangByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editPembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(editPembayaranUtangPiutang);
            } else {
                editPembayaranUtangPiutang.setFlag(itemFlag);
                editPembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                setPembayaranUtangPiutang(editPembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editPembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            editPembayaranUtangPiutang.setFlag(getFlag());
            setPembayaranUtangPiutang(editPembayaranUtangPiutang);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        HttpSession session = ServletActionContext.getRequest().getSession();

        logger.info("[PembayaranUtangPiutangAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PembayaranUtangPiutangAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PembayaranUtangPiutang deletePembayaranUtangPiutang = new PembayaranUtangPiutang();

        if (itemFlag != null ) {

            try {
                deletePembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);

            } else {
                deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                deletePembayaranUtangPiutang.setFlag(itemFlag);
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            deletePembayaranUtangPiutang.setFlag(itemFlag);
            setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PembayaranUtangPiutangAction.delete] end process <<<");
        HttpSession session = ServletActionContext.getRequest().getSession();
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PembayaranUtangPiutangAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PembayaranUtangPiutang deletePembayaranUtangPiutang = new PembayaranUtangPiutang();

        if (itemFlag != null ) {
            try {
                deletePembayaranUtangPiutang = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePembayaranUtangPiutang != null) {
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);

            } else {
                deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
                deletePembayaranUtangPiutang.setFlag(itemFlag);
                setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePembayaranUtangPiutang.setPembayaranUtangPiutangId(itemId);
            deletePembayaranUtangPiutang.setFlag(itemFlag);
            setPembayaranUtangPiutang(deletePembayaranUtangPiutang);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        logger.info("[PembayaranUtangPiutangAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PembayaranUtangPiutangAction.saveEdit] start process >>>");
        try {
            PembayaranUtangPiutang editPembayaranUtangPiutang = getPembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPembayaranUtangPiutang.setLastUpdateWho(userLogin);
            editPembayaranUtangPiutang.setLastUpdate(updateTime);
            editPembayaranUtangPiutang.setAction("U");
            editPembayaranUtangPiutang.setFlag("Y");

            pembayaranUtangPiutangBoProxy.saveEdit(editPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PembayaranUtangPiutangAction.saveDelete] start process >>>");
        try {
            PembayaranUtangPiutang deletePembayaranUtangPiutang = getPembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePembayaranUtangPiutang.setLastUpdate(updateTime);
            deletePembayaranUtangPiutang.setLastUpdateWho(userLogin);
            deletePembayaranUtangPiutang.setAction("U");
            deletePembayaranUtangPiutang.setFlag("N");

            pembayaranUtangPiutangBoProxy.saveDelete(deletePembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[PembayaranUtangPiutangAction.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal="";
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");

        PembayaranUtangPiutang pembayaranUtangPiutang = getPembayaranUtangPiutang();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(pembayaranUtangPiutang.getStBayar().replace(".","")));
        pembayaranUtangPiutang.setBayar(bayar);
        pembayaranUtangPiutang.setTanggal(CommonUtil.convertStringToDate2(pembayaranUtangPiutang.getStTanggal()));

        pembayaranUtangPiutang.setCreatedWho(userLogin);
        pembayaranUtangPiutang.setLastUpdate(updateTime);
        pembayaranUtangPiutang.setCreatedDate(updateTime);
        pembayaranUtangPiutang.setLastUpdateWho(userLogin);
        pembayaranUtangPiutang.setAction("C");
        pembayaranUtangPiutang.setFlag("Y");

        try {
            //get parameter pembayaran
            String parameter = billingSystemBoProxy.getParameterPembayaran(pembayaranUtangPiutang.getTipeTransaksi());
            String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutang.getMetodePembayaran());

            //Jika pembayaran berhasil
            //Membuat Billing
            List<Map> dataMap = new ArrayList<>();
            for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutangDetail.getRekeningId());
                BigDecimal jumlahPembayaran = new BigDecimal(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(".",""));
                Map hs = new HashMap();
                hs.put("bukti",pembayaranUtangPiutangDetail.getNoNota());
                hs.put("nilai",jumlahPembayaran);
                hs.put("master_id", pembayaranUtangPiutangDetail.getMasterId());
                hs.put("divisi_id", pembayaranUtangPiutangDetail.getDivisiId());
                hs.put("rekening_id", rekeningId);
                dataMap.add(hs);
            }

            Map kas = new HashMap();
            kas.put("nilai",bayar);
            kas.put("rekening_id", rekeningIdBayar);

            Map data = new HashMap();
            data.put(parameter,dataMap);
            data.put("metode_bayar",kas);
            noJurnal= billingSystemBoProxy.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),data,pembayaranUtangPiutang.getBranchId(),pembayaranUtangPiutang.getKeterangan(),"N");
            pembayaranUtangPiutang.setNoJurnal(noJurnal);
            pembayaranUtangPiutangBoProxy.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangAction.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PembayaranUtangPiutangAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.saveAdd] end process >>>");
        if ("KK".equalsIgnoreCase(pembayaranUtangPiutang.getTipePembayaran())){
            return "success_save_add";
        }else{
            return "success_save_add_pemasukan";
        }
    }

    public String saveAddKoreksi(){
        logger.info("[PembayaranUtangPiutangAction.saveAddKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal="";
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");

        PembayaranUtangPiutang pembayaranUtangPiutang = getPembayaranUtangPiutang();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal bayar = BigDecimal.ZERO;
        pembayaranUtangPiutang.setBayar(bayar);
        pembayaranUtangPiutang.setMetodePembayaran("");
        pembayaranUtangPiutang.setBank("");
        pembayaranUtangPiutang.setNoSlipBank("");
        pembayaranUtangPiutang.setTanggal(CommonUtil.convertStringToDate2(pembayaranUtangPiutang.getStTanggal()));

        pembayaranUtangPiutang.setCreatedWho(userLogin);
        pembayaranUtangPiutang.setLastUpdate(updateTime);
        pembayaranUtangPiutang.setCreatedDate(updateTime);
        pembayaranUtangPiutang.setLastUpdateWho(userLogin);
        pembayaranUtangPiutang.setAction("C");
        pembayaranUtangPiutang.setFlag("Y");

        try {
            //get parameter pembayaran
            Map data = new HashMap();

            MappingJurnal search = new MappingJurnal();
            search.setTransId(pembayaranUtangPiutang.getTipeTransaksi());
            search.setFlag("Y");

            List<MappingJurnal> mappingJurnalList = mappingJurnalBoProxy.getByCriteria(search);
            for (MappingJurnal mappingJurnal : mappingJurnalList){
                List<Map> dataMap = new ArrayList<>();
                for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutangDetail.getRekeningId());
                    if (mappingJurnal.getPosisi().equalsIgnoreCase(pembayaranUtangPiutangDetail.getPosisiCoa())){
                        BigDecimal jumlahPembayaran = new BigDecimal(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(".",""));
                        Map hs = new HashMap();
                        hs.put("bukti",pembayaranUtangPiutangDetail.getNoNota());
                        hs.put("nilai",jumlahPembayaran);
                        hs.put("master_id", pembayaranUtangPiutangDetail.getMasterId());
                        hs.put("divisi_id", pembayaranUtangPiutangDetail.getDivisiId());
                        hs.put("rekening_id", rekeningId);
                        dataMap.add(hs);
                    }
                }
                data.put(mappingJurnal.getKeterangan(),dataMap);
            }
            noJurnal= billingSystemBoProxy.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),data,pembayaranUtangPiutang.getBranchId(),pembayaranUtangPiutang.getKeterangan(),"N");
            pembayaranUtangPiutang.setNoJurnal(noJurnal);
            pembayaranUtangPiutangBoProxy.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangAction.saveAddKoreksi");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveAddKoreksi] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PembayaranUtangPiutangAction.saveAddKoreksi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.saveAddKoreksi] end process >>>");
        return "success_save_add_koreksi";
    }

    @Override
    public String search() {
        logger.info("[PembayaranUtangPiutangAction.search] start process >>>");
        PembayaranUtangPiutang searchPembayaranUtangPiutang = getPembayaranUtangPiutang();
        List<PembayaranUtangPiutang> listOfsearchPembayaranUtangPiutang = new ArrayList();
        try {
            listOfsearchPembayaranUtangPiutang = pembayaranUtangPiutangBoProxy.getByCriteria(searchPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listPembayaranDetailModal");
        session.setAttribute("listOfResult", listOfsearchPembayaranUtangPiutang);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            searchPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(searchPembayaranUtangPiutang);
        logger.info("[PembayaranUtangPiutangAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPemasukan() {
        logger.info("[PembayaranUtangPiutangAction.searchPemasukan] start process >>>");
        PembayaranUtangPiutang searchPembayaranUtangPiutang = getPembayaranUtangPiutang();
        List<PembayaranUtangPiutang> listOfsearchPembayaranUtangPiutang = new ArrayList();
        try {
            listOfsearchPembayaranUtangPiutang = pembayaranUtangPiutangBoProxy.getByCriteria(searchPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.searchPemasukan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.searchPemasukan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listPembayaranDetailModal");
        session.setAttribute("listOfResult", listOfsearchPembayaranUtangPiutang);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            searchPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(searchPembayaranUtangPiutang);
        logger.info("[PembayaranUtangPiutangAction.searchPemasukan] end process <<<");

        return "success_pemasukan";
    }

    public String searchKoreksi() {
        logger.info("[PembayaranUtangPiutangAction.searchKoreksi] start process >>>");
        PembayaranUtangPiutang searchPembayaranUtangPiutang = getPembayaranUtangPiutang();
        List<PembayaranUtangPiutang> listOfsearchPembayaranUtangPiutang = new ArrayList();
        try {
            listOfsearchPembayaranUtangPiutang = pembayaranUtangPiutangBoProxy.getByCriteria(searchPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.searchKoreksi] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.searchKoreksi] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listPembayaranDetailModal");
        session.setAttribute("listOfResult", listOfsearchPembayaranUtangPiutang);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPembayaranUtangPiutang.setBranchId(branchId);
        }else{
            searchPembayaranUtangPiutang.setBranchId("");
        }
        setPembayaranUtangPiutang(searchPembayaranUtangPiutang);
        logger.info("[PembayaranUtangPiutangAction.searchKoreksi] end process <<<");

        return "success_koreksi";
    }

    @Override
    public String initForm() {
        logger.info("[PembayaranUtangPiutangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PembayaranUtangPiutang data = new PembayaranUtangPiutang();
        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }
        setPembayaranUtangPiutang(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PembayaranUtangPiutangAction.initForm] end process >>>");
        return INPUT;
    }

    public String initFormPemasukan() {
        logger.info("[PembayaranUtangPiutangAction.initFormPemasukan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PembayaranUtangPiutang data = new PembayaranUtangPiutang();
        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }
        setPembayaranUtangPiutang(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PembayaranUtangPiutangAction.initFormPemasukan] end process >>>");
        return "input_pemasukan";
    }

    public String initFormKoreksi() {
        logger.info("[PembayaranUtangPiutangAction.initFormKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PembayaranUtangPiutang data = new PembayaranUtangPiutang();
        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }
        setPembayaranUtangPiutang(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PembayaranUtangPiutangAction.initFormKoreksi] end process >>>");
        return "input_koreksi";
    }

    public String saveDetailPembayaran(String kodeVendor,String namaVendor,String noNota,String jumlahPembayaran,String rekeningId,String divisiId,String divisiName) {
        logger.info("[PembayaranUtangPiutangAction.saveDetailPembayaran] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> piutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<PembayaranUtangPiutangDetail> piutangDetailArrayList = new ArrayList<>();
        boolean ada=false;
        if (piutangDetailList==null){
            PembayaranUtangPiutangDetail newData = new PembayaranUtangPiutangDetail();
            newData.setStJumlahPembayaran(jumlahPembayaran);
            newData.setMasterId(kodeVendor);
            newData.setNoNota(noNota);
            newData.setMasterName(namaVendor);
            newData.setRekeningId(rekeningId);
            newData.setDivisiId(divisiId);
            newData.setDivisiName(divisiName);

            piutangDetailArrayList.add(newData);
            session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        }else{
            piutangDetailArrayList.addAll(piutangDetailList);
            for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail:piutangDetailList){
                if (pembayaranUtangPiutangDetail.getMasterId().equalsIgnoreCase(kodeVendor)&&pembayaranUtangPiutangDetail.getNoNota().equalsIgnoreCase(noNota)){
                    ada=true;
                    status="Pembayaran vendor dengan nomor nota ini sudah ada";
                    break;
                }
            }
            if (!ada){
                PembayaranUtangPiutangDetail newData = new PembayaranUtangPiutangDetail();
                newData.setStJumlahPembayaran(jumlahPembayaran);
                newData.setMasterId(kodeVendor);
                newData.setNoNota(noNota);
                newData.setMasterName(namaVendor);
                newData.setRekeningId(rekeningId);
                newData.setDivisiId(divisiId);
                newData.setDivisiName(divisiName);
                piutangDetailArrayList.add(newData);
                session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
            }
        }
        logger.info("[PembayaranUtangPiutangAction.saveDetailPembayaran] end process >>>");
        return status;
    }

    public String saveDetailKoreksi(String kodeVendor,String namaVendor,String noNota,String jumlahPembayaran,String rekeningId,String divisiId,String divisiName,String transId) {
        logger.info("[PembayaranUtangPiutangAction.saveDetailKoreksi] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String status="";
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        String posisiCoa = pembayaranUtangPiutangBo.getPosisiCoaDiMappingJurnal(transId,rekeningId);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> piutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<PembayaranUtangPiutangDetail> piutangDetailArrayList = new ArrayList<>();

        Comparator<PembayaranUtangPiutangDetail> comparator = (left, right) -> {
            int angka1 = 1;
            int angka2 = 1;

            if ("D".equalsIgnoreCase(left.getPosisiCoa())){
                angka1=0;
            }
            if ("D".equalsIgnoreCase(right.getPosisiCoa())){
                angka2=0;
            }
            return (angka1-angka2);
        };

        if (piutangDetailList==null){
            PembayaranUtangPiutangDetail newData = new PembayaranUtangPiutangDetail();
            newData.setStJumlahPembayaran(jumlahPembayaran);
            newData.setMasterId(kodeVendor);
            newData.setNoNota(noNota);
            newData.setMasterName(namaVendor);
            newData.setRekeningId(rekeningId);
            newData.setDivisiId(divisiId);
            newData.setDivisiName(divisiName);
            newData.setPosisiCoa(posisiCoa);

            piutangDetailArrayList.add(newData);

            Collections.sort(piutangDetailArrayList, comparator);
            session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        }else{
            piutangDetailArrayList.addAll(piutangDetailList);

            PembayaranUtangPiutangDetail newData = new PembayaranUtangPiutangDetail();
            newData.setStJumlahPembayaran(jumlahPembayaran);
            newData.setMasterId(kodeVendor);
            newData.setNoNota(noNota);
            newData.setMasterName(namaVendor);
            newData.setRekeningId(rekeningId);
            newData.setDivisiId(divisiId);
            newData.setDivisiName(divisiName);
            newData.setPosisiCoa(posisiCoa);
            piutangDetailArrayList.add(newData);

            Collections.sort(piutangDetailArrayList, comparator);
            session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        }

        logger.info("[PembayaranUtangPiutangAction.saveDetailKoreksi] end process >>>");
        return status;
    }

    public String deleteDetailPembayaran(String noNota) {
        logger.info("[PembayaranUtangPiutangAction.deleteDetailPembayaran] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> piutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<PembayaranUtangPiutangDetail> piutangDetailArrayList = new ArrayList<>();
        for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail:piutangDetailList){
            if (pembayaranUtangPiutangDetail.getNoNota().equalsIgnoreCase(noNota)){
            }else{
                piutangDetailArrayList.add(pembayaranUtangPiutangDetail);
            }
        }
        session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        logger.info("[PembayaranUtangPiutangAction.deleteDetailPembayaran] end process >>>");
        return status;
    }
    public List<PembayaranUtangPiutangDetail> searchDetailPembayaran() {
        logger.info("[PembayaranUtangPiutangAction.searchDetailPembayaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");

        logger.info("[PembayaranUtangPiutangAction.searchDetailPembayaran] end process >>>");
        return pembayaranUtangPiutangDetailList;
    }
    public String cekBeforeSave(String tipeTransaksi,String tanggal,String metodeBayar,String bayar,String keterangan,String noslipBank,String branchId){
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");

        if (tipeTransaksi.equalsIgnoreCase("")||tanggal.equalsIgnoreCase("")||metodeBayar.equalsIgnoreCase("")||bayar.equalsIgnoreCase("")||branchId.equalsIgnoreCase("")){
            if (tipeTransaksi.equalsIgnoreCase("")){
                status+="Tipe transaksi masih kosong <br>";
            }
            if (tanggal.equalsIgnoreCase("")){
                status+="Tanggal masih kosong <br>";
            }
            if (metodeBayar.equalsIgnoreCase("")){
                status+="Kode rekening kas masih kosong <br>";
            }
            if (bayar.equalsIgnoreCase("")){
                status+="Jumlah pembayaran masih kosong <br>";
            }
            if (branchId.equalsIgnoreCase("")){
                status+="Unit masih kosong <br>";
            }
            /*if (keterangan.equalsIgnoreCase("")){
                status+="Keterangan masih kosong <br>";
            }
            if (noslipBank.equalsIgnoreCase("")){
                status+="No. Slip masih kosong <br>";
            }*/
        }else if(pembayaranUtangPiutangDetailList==null||pembayaranUtangPiutangDetailList.size()==0){
            status+="Detail pembayaran belum ada <br>";
        }else{
            BigDecimal totalBayar = BigDecimal.valueOf(Double.valueOf(bayar.replace(".","")));
            BigDecimal bayarDetail = BigDecimal.ZERO;
            for (PembayaranUtangPiutangDetail data : pembayaranUtangPiutangDetailList){
                bayarDetail= bayarDetail.add( new BigDecimal(data.getStJumlahPembayaran().replace(".","")));
            }
            if (!totalBayar.equals(bayarDetail)){
                if (totalBayar.compareTo(bayarDetail)>0){
                    status="Jumlah pembayaran masih kurang dari total bayar";
                }else if (totalBayar.compareTo(bayarDetail)<0){
                    status="Jumlah pembayaran melebihi total bayar";
                }
            }
        }
        return status;
    }

    public String cekBeforeSaveKoreksi(String tipeTransaksi,String tanggal,String keterangan, String branchId){
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");

        if (tipeTransaksi.equalsIgnoreCase("")||tanggal.equalsIgnoreCase("")||keterangan.equalsIgnoreCase("")){
            if (tipeTransaksi.equalsIgnoreCase("")){
                status+="Tipe transaksi masih kosong <br>";
            }
            if (tanggal.equalsIgnoreCase("")){
                status+="Tanggal masih kosong <br>";
            }
            if (branchId.equalsIgnoreCase("")){
                status+="Unit masih kosong <br>";
            }
            if (keterangan.equalsIgnoreCase("")){
                status+="Keterangan masih kosong <br>";
            }
        }else if(pembayaranUtangPiutangDetailList==null||pembayaranUtangPiutangDetailList.size()==0){
            status+="Detail pembayaran belum ada <br>";
        }else{
            BigDecimal bayarDetailDebit = BigDecimal.ZERO;
            BigDecimal bayarDetailKredit = BigDecimal.ZERO;
            for (PembayaranUtangPiutangDetail data : pembayaranUtangPiutangDetailList){
                if ("K".equalsIgnoreCase(data.getPosisiCoa())){
                    bayarDetailKredit= bayarDetailKredit.add( new BigDecimal(data.getStJumlahPembayaran().replace(".","")));
                }else {
                    bayarDetailDebit= bayarDetailDebit.add( new BigDecimal(data.getStJumlahPembayaran().replace(".","")));
                }
            }
            if (!bayarDetailKredit.equals(bayarDetailDebit)){
                if (bayarDetailKredit.compareTo(bayarDetailDebit)>0){
                    status="Jumlah pembayaran Kredit melebihi Jumlah Pembayaran Debit";
                }else if (bayarDetailKredit.compareTo(bayarDetailDebit)<0){
                    status="Jumlah pembayaran Debit melebihi Jumlah Pembayaran Kredit";
                }
            }
        }
        return status;
    }

    public List<PembayaranUtangPiutangDetail> searchNotaPembayaran(String masterId,String transaksiId,String branchId,String divisiId,String coa) {
        logger.info("[PembayaranUtangPiutangAction.searchNotaPembayaran] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<PembayaranUtangPiutangDetail> listDataDb = pembayaranUtangPiutangBo.getSearchNotaPembayaran(masterId,transaksiId,branchId,divisiId,coa);
        List<PembayaranUtangPiutangDetail> listDataSession = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<PembayaranUtangPiutangDetail> result= new ArrayList<>();
        if (listDataSession==null){
            result.addAll(listDataDb);
        }else{
            //jika sudah ada pada session tidak usah ditampilkan
            for (PembayaranUtangPiutangDetail dataDb : listDataDb){
                boolean ada = false;
                for (PembayaranUtangPiutangDetail dataSession : listDataSession){
                    if (dataDb.getNoNota().equalsIgnoreCase(dataSession.getNoNota())){
                        ada=true;
                        break;
                    }
                }
                if (!ada){
                    result.add(dataDb);
                }
            }
        }
        logger.info("[PembayaranUtangPiutangAction.searchNotaPembayaran] end process >>>");
        return result;
    }


    public String getTipeMaster(String transaksiId) {
        logger.info("[PembayaranUtangPiutangAction.getTipeMaster] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");

        String masterId="";
        ImTransEntity transEntity = pembayaranUtangPiutangBo.getTipeMaster(transaksiId);
        masterId=transEntity.getMaster();

        logger.info("[PembayaranUtangPiutangAction.getTipeMaster] end process >>>");
        return masterId;
    }

    public String postingJurnal(String pembayaranId){
        logger.info("[PembayaranUtangPiutangAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
            PembayaranUtangPiutang data = new PembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            Date tanggalSekarang = new Date(new java.util.Date().getTime());
            data.setPembayaranUtangPiutangId(pembayaranId);
            data.setRegisteredDate(tanggalSekarang);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            pembayaranUtangPiutangBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String printReportBuktiPosting(){
        logger.info("[PembayaranUtangPiutangAction.printReportBuktiPosting] start process >>>");
        List<PembayaranUtangPiutang> pembayaranUtangPiutangList;
        String tipeTransaksi="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        PembayaranUtangPiutang data = getPembayaranUtangPiutang();
        Branch branch = branchBo.getBranchById(data.getBranchId(),"Y");
        PembayaranUtangPiutang search = new PembayaranUtangPiutang();
        search.setPembayaranUtangPiutangId(data.getPembayaranUtangPiutangId());
        search.setFlag("Y");
        pembayaranUtangPiutangList=pembayaranUtangPiutangBo.getByCriteria(search);

        //menambah printCount
        pembayaranUtangPiutangBo.addPrintCount(data.getNoJurnal());
        String kodeRekeningKasJurnal = pembayaranUtangPiutangBo.getNamaRekeningKasJurnal(data.getNoJurnal());

        for (PembayaranUtangPiutang result : pembayaranUtangPiutangList){
            reportParams.put("terbilang", CommonUtil.angkaToTerbilang(result.getBayar().longValue()));
            reportParams.put("uraian", result.getKeterangan());
            reportParams.put("totalBayar", CommonUtil.numbericFormat(result.getBayar(),"###,###.00"));
            reportParams.put("noSlipBank", result.getNoSlipBank());
            reportParams.put("coaKas", kodeRekeningKasJurnal);
            tipeTransaksi=result.getTipePembayaran();
        }
        String reportName ="";
        switch (tipeTransaksi){
            case("KK"):
                reportName="BUKTI KAS KELUAR";
                break;
            case ("KM"):
                reportName="BUKTI KAS MASUK";
                break;
            case ("KR"):
                reportName="BUKTI KOREKSI";
                break;
        }

        reportParams.put("reportTitle", reportName);
        reportParams.put("reportName", reportName);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getBranchId());
        java.util.Date now = new java.util.Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("noJurnal",data.getNoJurnal());
        reportParams.put("pembayaranId",data.getPembayaranUtangPiutangId());
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("areaId", CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBo.saveErrorMessage(e.getMessage(), "printReportBuktiPosting");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.printReportBuktiPosting] Error when downloading ,", e1);
            }
            logger.error("[PembayaranUtangPiutangAction.printReportBuktiPosting] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[PembayaranUtangPiutangAction.printReportBuktiPosting] end process <<<");
        if ("KR".equalsIgnoreCase(tipeTransaksi)){
            return "print_report_bukti_posting_koreksi";
        }else{
            return "print_report_bukti_posting";
        }
    }

    public PembayaranUtangPiutang getForModalPopUp(String pembayaranId) {
        logger.info("[PembayaranUtangPiutangAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        String itemId = pembayaranId;
        String itemFlag = "Y";
        PembayaranUtangPiutang modalPopUp = new PembayaranUtangPiutang();
        List<PembayaranUtangPiutangDetail> listDetail = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(itemId, itemFlag);
            if (modalPopUp!=null){
                listDetail = pembayaranUtangPiutangBo.getDetailPembayaran(pembayaranId);
                session.setAttribute("listPembayaranDetailModal",listDetail);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving delete data,", e1);
            }
            logger.error("[PembayaranUtangPiutangAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public List<PembayaranUtangPiutangDetail> searchPembayaranDetail (){
        HttpSession session = ServletActionContext.getRequest().getSession();

        return (List<PembayaranUtangPiutangDetail>) session.getAttribute("listPembayaranDetailModal");
    }

    public Trans getDisableTrans(String transaksiId,String coaLawan) {
        logger.info("[PembayaranUtangPiutangAction.getDisableTrans] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        logger.info("[PembayaranUtangPiutangAction.getDisableTrans] end process >>>");
        Trans data = pembayaranUtangPiutangBo.getDisableTrans(transaksiId,coaLawan);
        return data;
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
