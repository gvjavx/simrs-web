package com.neurix.akuntansi.transaksi.pengajuanBiaya.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.bo.PengajuanBiayaBo;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaDetail;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaRk;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.StokDTO;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private PengajuanBiayaDetail pengajuanBiayaDetail;
    private PengajuanBiayaRk pengajuanBiayaRk;
    private List<PengajuanBiaya> listOfComboPengajuanBiaya = new ArrayList<PengajuanBiaya>();
    private String rkId;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PengajuanBiayaRk getPengajuanBiayaRk() {
        return pengajuanBiayaRk;
    }

    public void setPengajuanBiayaRk(PengajuanBiayaRk pengajuanBiayaRk) {
        this.pengajuanBiayaRk = pengajuanBiayaRk;
    }

    public String getRkId() {
        return rkId;
    }

    public void setRkId(String rkId) {
        this.rkId = rkId;
    }

    public PengajuanBiayaDetail getPengajuanBiayaDetail() {
        return pengajuanBiayaDetail;
    }

    public void setPengajuanBiayaDetail(PengajuanBiayaDetail pengajuanBiayaDetail) {
        this.pengajuanBiayaDetail = pengajuanBiayaDetail;
    }

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
        addPengajuanBiaya.setBranchIdKanpus(CommonConstant.ID_KANPUS);
        if (branchId!=null){
            addPengajuanBiaya.setBranchId(branchId);
        }else{
            addPengajuanBiaya.setBranchId("");
        }
        setPengajuanBiaya(addPengajuanBiaya);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPengajuanBiayaDetail");

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
        session.removeAttribute("listOfResultPengajuanBiayaDetail");

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

        try {

            List<Notifikasi> notif = pengajuanBiayaBoProxy.saveAddPengajuanBiaya(pengajuanBiaya);

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

    public String postingJurnal(String pengajuanId){
        logger.info("[PengajuanBiayaAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PengajuanBiayaBo pengajuanBiayaBo = (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
            NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
            KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
            BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            PengajuanBiaya pengajuanBiaya = new PengajuanBiaya();
            PengajuanBiaya search = new PengajuanBiaya();
            search.setPengajuanBiayaId(pengajuanId);
            search.setFlag("Y");
            List<PengajuanBiaya> pengajuanBiayaList = pengajuanBiayaBo.getByCriteria(search);
            for (PengajuanBiaya data : pengajuanBiayaList){
                pengajuanBiaya = data;
            }

            String branchId = "";
            String transId= "";
            if (("SMK").equalsIgnoreCase(pengajuanBiaya.getTransaksi())){
                branchId = CommonConstant.ID_KANPUS;
                transId = CommonConstant.TRANSAKSI_ID_KIRIM_RK;
            }else if (("PDU").equalsIgnoreCase(pengajuanBiaya.getTransaksi())){
                branchId = CommonConstant.ID_KANPUS;
                transId = CommonConstant.TRANSAKSI_ID_PENERIMAAN_PENDAPATAN_DARI_UNIT;
            }

            //membuat mapping
            Map dataMap = new HashMap();

            Map rkUnit = new HashMap();
            rkUnit.put("nilai",pengajuanBiaya.getTotalBiaya());
            rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaAjuan()));
            dataMap.put("rk_kd_unit",rkUnit);

            Map giro = new HashMap();
            giro.put("nilai",pengajuanBiaya.getTotalBiaya());
            giro.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(pengajuanBiaya.getCoaTarget()));
            dataMap.put("metode_bayar",giro);

            //membuat jurnal
            Jurnal jurnal = billingSystemBo.createJurnal(transId,dataMap,branchId,pengajuanBiaya.getKeterangan(),"Y");
            String noJurnal = jurnal.getNoJurnal();

            //kirim notif ke unit
            List<Notifikasi> notif = pengajuanBiayaBo.sendNotifikasiKeAdminAks(pengajuanBiaya.getBranchId(),pengajuanId,pengajuanBiaya.getKeterangan(),pengajuanBiaya.getCreatedWho());
            for (Notifikasi notifikasi : notif ){
                notifikasiBo.sendNotif(notifikasi);
            }

            //update data
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//            Date tanggalSekarang = new Date(new java.util.Date().getTime());
            PengajuanBiaya data = new PengajuanBiaya();
            data.setPengajuanBiayaId(pengajuanId);
            data.setNoJurnal(noJurnal);
//            data.setRegisteredDate(tanggalSekarang);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");

            pengajuanBiayaBo.postingJurnal(data);

        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PengajuanBiayaAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String saveAddPengajuan(){
        logger.info("[PengajuanBiayaAction.saveAddPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> detailList = (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");

        PengajuanBiaya pengajuanBiaya = getPengajuanBiaya();
        String userLogin = CommonUtil.userLogin();
        String userIdLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal total = BigDecimal.valueOf(Double.valueOf(pengajuanBiaya.getStTotalBiaya().replace(".","").replace(",","")));
        pengajuanBiaya.setTotalBiaya(total);
        pengajuanBiaya.setCreatedWho(userLogin);
        pengajuanBiaya.setAprovalId(userIdLogin);
        pengajuanBiaya.setLastUpdate(updateTime);
        pengajuanBiaya.setCreatedDate(updateTime);
        pengajuanBiaya.setLastUpdateWho(userLogin);
        pengajuanBiaya.setAction("C");
        pengajuanBiaya.setFlag("Y");
        pengajuanBiaya.setFlagBatal("N");
        try {
            List<Notifikasi> notif = pengajuanBiayaBoProxy.saveAddPengajuan(pengajuanBiaya,detailList);

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
        searchPengajuanBiaya.setTransaksi("PB");
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

    public String initFormPembayaranDo() {
        logger.info("[PengajuanBiayaAction.initFormPembayaranDo] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanBiayaRk data = new PengajuanBiayaRk();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }

        setPengajuanBiayaRk(data);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanBiayaAction.initFormPembayaranDo] end process >>>");
        return "init_do";
    }

    public String addDo() {
        logger.info("[PengajuanBiayaAction.addDo] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PengajuanBiayaRk data = new PengajuanBiayaRk();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }

        setPengajuanBiayaRk(data);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanBiayaAction.addDo] end process >>>");
        return "init_add_do";
    }

    public String initFormPengajuan() {
        logger.info("[PengajuanBiayaAction.initFormPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        String divisi = CommonUtil.userPosisiId();
        PengajuanBiaya data = new PengajuanBiaya();
        PengajuanBiayaDetail data2 = new PengajuanBiayaDetail();
        if (branchId!=null){
            data.setBranchId(branchId);
            data2.setBranchId(branchId);
            data2.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
            data2.setBranchId("");
        }
        if (divisi!=null){
            data.setDivisiId(divisi);
        }else{
            data.setDivisiId("");
        }
        setPengajuanBiaya(data);
        setPengajuanBiayaDetail(data2);
        session.removeAttribute("listOfResult");
        logger.info("[PengajuanBiayaAction.initFormPengajuan] end process >>>");

        if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(CommonUtil.roleIdAsLogin())){
            return "input_pengajuan_admin";
        }else{
            return "input_pengajuan";
        }
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
    public String saveApprove(String id, String statusApprove, String who,String coaBank,String coaRk,String jumlah,String transaksi,String branchId,String keterangan){
        logger.info("[PengajuanBiayaAction.saveApprove] start process >>>");
        PengajuanBiaya editPengajuanBiaya = new PengajuanBiaya();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        String tipeTransaksi = "";
        if (("SMK").equalsIgnoreCase(transaksi)){
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_TERIMA_RK;
        }else if (("PDU").equalsIgnoreCase(transaksi)){
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_PENGIRIMAN_PENDAPATAN_KE_PUSAT;
        }

        editPengajuanBiaya.setPengajuanBiayaId(id);
        if(who.equals("atasan")){
            if(statusApprove.equals("Y")){
                editPengajuanBiaya.setAprovalFlag(statusApprove);
            }else{
                editPengajuanBiaya.setAprovalFlag("N");
            }
        }

        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(jumlah.replace(".","").replace(",","")));

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

    public String saveSessionPengajuan(String branchId , String divisiId,String stTanggal,String transaksi,
                                       String noBudgeting,String stJumlah,String stBudgetBiaya,String stBudgetTerpakai,
                                       String keperluan,String keterangan,String keperluanName,
                                       String stBudgetBiayaSdBulanIni,String stBudgetTerpakaiSdBulanIni,String stSisaBudget,
                                       String stSisaBudgetSdBulanIni, String stBudgetTerpakaiAplikasi ,String pembayaran) {
        logger.info("[PengajuanBiayaAction.saveSessionPengajuan] start process >>>");
        String error ="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> listOfResult = (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        boolean save = true;
        if (listOfResult==null){
            listOfResult= new ArrayList<>();
        }else{
            for (PengajuanBiayaDetail pengajuanBiayaDetail : listOfResult){
                if (keperluan.equalsIgnoreCase(pengajuanBiayaDetail.getKeperluan())){
                    save=false;
                    error="Data sudah ada";
                }
            }
        }
        BigDecimal budgetBiaya = BigDecimal.valueOf(Double.valueOf(stBudgetBiaya.replace(",","")));
        BigDecimal budgetBiayaSdBulanIni = BigDecimal.valueOf(Double.valueOf(stBudgetBiayaSdBulanIni.replace(",","")));
        BigDecimal budgetTerpakai = BigDecimal.valueOf(Double.valueOf(stBudgetTerpakai.replace(",","")));
        BigDecimal budgetTerpakaiSdBulanIni = BigDecimal.valueOf(Double.valueOf(stBudgetTerpakaiSdBulanIni.replace(",","")));
        BigDecimal sisaBudget = BigDecimal.valueOf(Double.valueOf(stSisaBudget.replace(",","")));
        BigDecimal sisaBudgetSdBulanIni = BigDecimal.valueOf(Double.valueOf(stSisaBudgetSdBulanIni.replace(",","")));

        PengajuanBiayaDetail result = new PengajuanBiayaDetail();
        result.setBranchId(branchId);
        result.setDivisiId(divisiId);
        result.setStTanggal(stTanggal);
        result.setTransaksi(transaksi);

        if ("I".equalsIgnoreCase(transaksi)){
            result.setKeperluanId(keperluan);
            result.setPembayaran(pembayaran);
            result.setNamaKontrak(keperluanName);

            //set no kontrak dan nama kontrak
            result.setNoKontrak(pengajuanBiayaBo.getNoKontrak(keperluan));
        }

        result.setNoBudgeting(noBudgeting);
        result.setKeperluan(keperluan);
        result.setKeperluanName(keperluanName);
        result.setKeterangan(keterangan);
        result.setStJumlah(stJumlah);
        result.setJumlah(BigDecimal.valueOf(Double.valueOf(stJumlah.replace(".","").replace(",",""))));

        result.setBudgetBiaya(budgetBiaya);
        result.setBudgetTerpakai(budgetTerpakai);
        result.setSisaBudget(sisaBudget);
        result.setBudgetBiayaSdBulanIni(budgetBiayaSdBulanIni);
        result.setBudgetTerpakaiSdBulanIni(budgetTerpakaiSdBulanIni);
        result.setSisaBudgetSdBulanIni(sisaBudgetSdBulanIni);

        result.setStBudgetBiaya(stBudgetBiaya);
        result.setStBudgetTerpakai(stBudgetTerpakai);
        result.setStSisaBudget(stSisaBudget);
        result.setStBudgetBiayaSdBulanIni(stBudgetBiayaSdBulanIni);
        result.setStBudgetTerpakaiSdBulanIni(stBudgetTerpakaiSdBulanIni);
        result.setStSisaBudgetSdBulanIni(stSisaBudgetSdBulanIni);

        result.setBudgetTerpakaiTransaksi(BigDecimal.valueOf(Double.valueOf(stBudgetTerpakaiAplikasi.replace(".","").replace(",",""))));
        result.setStBudgetTerpakaiTransaksi(stBudgetTerpakaiAplikasi);
        //

        if (result.getJumlah().compareTo(result.getSisaBudgetSdBulanIni().subtract(result.getBudgetTerpakaiTransaksi()))>0){
            save=false;
            error = "Jumlah yang dimasukkan melebihi sisa budget s/d bulan ini";
        }
        if (save){
            listOfResult.add(result);
        }

        session.setAttribute("listOfResultPengajuanBiayaDetail",listOfResult);
        logger.info("[PengajuanBiayaAction.saveSessionPengajuan] end process <<<");
        return error;
    }

    public List<PengajuanBiayaDetail> searchSessionPengajuan() {
        logger.info("[PengajuanBiayaAction.searchSessionPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> listOfsearch= (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        return listOfsearch;
    }

    public String deleteSessionPengajuan(String keperluan) {
        logger.info("[PengajuanBiayaAction.deleteSessionPengajuan] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> detailList = (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        List<PengajuanBiayaDetail> finalDetailList = new ArrayList<>();
        for (PengajuanBiayaDetail data:detailList){
            if (data.getKeperluan().equalsIgnoreCase(keperluan)){
            }else{
                finalDetailList.add(data);
            }
        }
        session.setAttribute("listOfResultPengajuanBiayaDetail",finalDetailList);
        logger.info("[PengajuanBiayaAction.deleteSessionPengajuan] end process >>>");
        return status;
    }

    public List<PengajuanBiayaDetail> searchPengajuanDetail(String pengajuanBiayaId) {
        logger.info("[PengajuanBiayaAction.searchPengajuanDetail] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.searchPengajuanDetail(pengajuanBiayaId);
    }

    public String searchPengajuanDetailImage(String pengajuanBiayaId) {
        logger.info("[PengajuanBiayaAction.searchPengajuanDetail] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.searchPengajuanDetailImage(pengajuanBiayaId);
    }

    public void saveApproveAtasanPengajuan(String pengajuanId,String status,String jumlah,String gambar,String flagUpload){
        logger.info("[PengajuanBiayaAction.saveApproveAtasanPengajuan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        String userLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal total = BigDecimal.valueOf(Double.valueOf(jumlah.replace(".","").replace(",","")));

        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setPengajuanBiayaDetailId(pengajuanId);
        data.setJumlah(total);
        data.setStatusApproval(status);
        data.setAction("U");
        data.setLastUpdateWho(userLogin);
        data.setLastUpdate(updateTime);

        if ("Y".equalsIgnoreCase(flagUpload)){
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(gambar);
                logger.info("Decoded upload data : " + decodedBytes.length);
                String fileName = pengajuanId+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_IPA+fileName;
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                }else{
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    data.setUrlIpa(fileName);
                    data.setFlagUpload("Y");
                }
            }catch (Exception e){
                String error = "ERROR : "+e.getMessage();
                logger.error(error);
                throw new GeneralBOException(error);
            }
        }

        switch (status){
            case "KS":
                data.setApprovalKasubdivFlag("Y");
                data.setApprovalKasubdivId(userLogin);
                data.setApprovalKasubdivDate(updateTime);
                break;
            case "KD":
                data.setApprovalKadivFlag("Y");
                data.setApprovalKadivId(userLogin);
                data.setApprovalKadivDate(updateTime);
                break;
            case "GM":
                data.setApprovalGmFlag("Y");
                data.setApprovalGmId(userLogin);
                data.setApprovalGmDate(updateTime);
                break;
            case "KE":
                data.setApprovalKeuanganFlag("Y");
                data.setApprovalKeuanganId(userLogin);
                data.setApprovalKeuanganDate(updateTime);
                break;
        }

        List<Notifikasi> notif = pengajuanBiayaBo.saveApproveAtasanPengajuan(data);

        for (Notifikasi notifikasi : notif ){
            notifikasiBo.sendNotif(notifikasi);
        }
    }

    public String saveApproveKeuanganPengajuan(String pengajuanId,String status,String statusKeuangan,String branchId,String keterangan,String stJumlah,String noBudgetting,String divisiId,String tanggalRealisasi){
        logger.info("[PengajuanBiayaAction.saveApproveKeuanganPengajuan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        String userLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal jumlah = BigDecimal.valueOf(Double.valueOf(stJumlah.replace(".","").replace(",","")));

        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setPengajuanBiayaDetailId(pengajuanId);
        data.setStatusApproval(status);
        data.setStatusKeuangan(statusKeuangan);
        data.setJumlah(jumlah);
        data.setAction("U");
        data.setLastUpdateWho(userLogin);
        data.setLastUpdate(updateTime);

        switch (status){
            case "KE":
                data.setTanggalRealisasi(CommonUtil.convertStringToDate(tanggalRealisasi));
                data.setApprovalKeuanganFlag("Y");
                data.setApprovalKeuanganId(userLogin);
                data.setApprovalKeuanganDate(updateTime);
                break;
        }

        if ("A".equalsIgnoreCase(statusKeuangan)){
           data.setStatusKeuangan("A");
           data.setDiterimaFlag("Y");
           pengajuanBiayaBo.saveApproveKeuanganPengajuan(data);
        }else if ("KP".equalsIgnoreCase(statusKeuangan)){
            //jika dana berasal dari KP
            data.setStatusKeuangan("KP");
            List<Notifikasi> notif = pengajuanBiayaBo.saveApproveAtasanPengajuan(data);
            for (Notifikasi notifikasi : notif ){
                notifikasiBo.sendNotif(notifikasi);
            }
        }
        return cekApakahSudahCloseSemua(pengajuanId);
    }

    public String saveApproveKeuanganKpPengajuan(String pengajuanId,String status,String statusKeuangan,String branchId,String keterangan,String stJumlah,String noBudgetting,String divisiId){
        logger.info("[PengajuanBiayaAction.saveApproveKeuanganKpPengajuan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        PositionBo positionBo= (PositionBo) ctx.getBean("positionBoProxy");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        String userLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal jumlah = BigDecimal.valueOf(Double.valueOf(stJumlah.replace(".","").replace(",","")));

        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setPengajuanBiayaDetailId(pengajuanId);
        data.setStatusApproval(status);
        data.setStatusKeuangan(statusKeuangan);
        data.setJumlah(jumlah);
        data.setAction("U");
        data.setLastUpdateWho(userLogin);
        data.setLastUpdate(updateTime);

        switch (status){
            case "KEKP":
                data.setApprovalKeuanganKpFlag("Y");
                data.setApprovalKeuanganKpId(userLogin);
                data.setApprovalKeuanganKpDate(updateTime);
                break;
            case "TKE":
                data.setDiterimaFlag("Y");
                data.setDiterimaId(userLogin);
                data.setDiterimaDate(updateTime);
                break;
        }

        if ("KEKP".equalsIgnoreCase(status)){
            data.setStatusKeuangan("KP");
            notifikasiList = pengajuanBiayaBo.saveApproveKeuanganPengajuan(data);
        }else if ("TKE".equalsIgnoreCase(status)){
            notifikasiList = pengajuanBiayaBo.saveApproveKeuanganPengajuan(data);
        }

        for (Notifikasi notifikasi : notifikasiList ){
            notifikasiBo.sendNotif(notifikasi);
        }

        return cekApakahSudahCloseSemua(pengajuanId);
    }

    public List<PengajuanBiayaDetail> detailPengajuanBiayaSession (){
        HttpSession session = ServletActionContext.getRequest().getSession();
        return (List<PengajuanBiayaDetail>) session.getAttribute("listPengajuanDetailModal");
    }

    public PengajuanBiaya getForModalPopUp(String pengajuanBiayaId) {
        logger.info("[PengajuanBiayaAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo = (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        String itemFlag = "Y";
        PengajuanBiaya modalPopUp = new PengajuanBiaya();
        List<PengajuanBiayaDetail> listDetail = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(pengajuanBiayaId, itemFlag);
            if (modalPopUp!=null){
                listDetail = pengajuanBiayaBo.getDetailPembayaran(pengajuanBiayaId);
                session.setAttribute("listPengajuanDetailModal",listDetail);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBo.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[PengajuanBiayaAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public void saveNotApprovePengajuanBiaya(String pengajuanId,String keterangan){
        logger.info("[PengajuanBiayaAction.saveNotApprovePengajuanBiaya] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        NotifikasiBo notifikasiBo= (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        String userLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setPengajuanBiayaDetailId(pengajuanId);
        data.setAction("U");
        data.setNotApprovalNote(keterangan);
        data.setLastUpdateWho(userLogin);
        data.setLastUpdate(updateTime);

        List<Notifikasi> notif = pengajuanBiayaBo.saveNotApprovePengajuanBiaya(data);

        for (Notifikasi notifikasi : notif ){
            notifikasiBo.sendNotif(notifikasi);
        }
    }

    public PengajuanBiaya getKeteranganPembuatanRk(ArrayList pengajuanId, String status,String coaKas,String branchId){
        logger.info("[PengajuanBiayaAction.getKeteranganPembuatanRk] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        PengajuanBiaya data = pengajuanBiayaBo.getPengajuanBiayaForRk(pengajuanId,status,coaKas,branchId);
        return data;
    }

    public String rkPengajuanBiayaKp(String id,String coaKas,String branchId,String status) throws JSONException {
        logger.info("[PengajuanBiayaAction.rkPengajuanBiayaKp] start process >>>");
        String response = "Berhasil membuat jurnal RK";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");

        BigDecimal jumlah = BigDecimal.ZERO;

        List<PengajuanBiayaDetail> pengajuanBiayaDetailList = new ArrayList<>();


        ArrayList<String> idPengajuan = new ArrayList<>();
        JSONArray json = new JSONArray(id);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            idPengajuan.add(obj.getString("id"));
        }

        for (int i = 0; i < idPengajuan.size(); i++){
            pengajuanBiayaDetailList.add(pengajuanBiayaBo.getDetailById(idPengajuan.get(i)));
        }

        for (PengajuanBiayaDetail pengajuanBiayaDetail : pengajuanBiayaDetailList){
            jumlah = jumlah.add(pengajuanBiayaDetail.getJumlah());
        }

        //Membuat Jurnal
        //membuat RK pengiriman modal
        Map dataRk = new HashMap();
        //mencari coa RK
        Branch branch = branchBo.getBranchById(branchId,"Y");
        Map rkUnit = new HashMap();
        rkUnit.put("nilai",jumlah);
        rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(branch.getCoaRk()));
        dataRk.put("rk_kd_unit",rkUnit);

        Map giro = new HashMap();
        giro.put("nilai",jumlah);
        giro.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(coaKas));
        dataRk.put("metode_bayar",giro);

        if ("K".equalsIgnoreCase(status)){
            //membuat jurnal RK dari kantor pusat
            billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_KIRIM_RK,dataRk,CommonConstant.ID_KANPUS,getKeteranganPembuatanRk(idPengajuan,status,coaKas,branchId).getKeterangan(),"Y");
            pengajuanBiayaBo.setRkSudahDikirim(idPengajuan,coaKas);

        }
        return response;
    }

    public String rkPengajuanBiayaUnit(String rkId) {
        logger.info("[PengajuanBiayaAction.rkPengajuanBiayaUnit] start process >>>");
        String response = "Berhasil membuat jurnal RK";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");

        String branchId = "";
        String coaKas = "";
        String status = "T";
        BigDecimal jumlah = BigDecimal.ZERO;
        ArrayList<String> idPengajuan = new ArrayList<>();
        List<PengajuanBiayaDetail> pengajuanBiayaDetailList = new ArrayList<>();
        pengajuanBiayaDetailList = pengajuanBiayaBo.cariPengajuanBiayaDetailDenganRkId(rkId);

        for (PengajuanBiayaDetail pengajuanBiayaDetail : pengajuanBiayaDetailList){
            jumlah = jumlah.add(pengajuanBiayaDetail.getJumlah());
            branchId = pengajuanBiayaDetail.getBranchId();
            coaKas = pengajuanBiayaDetail.getCoaTarget();
            idPengajuan.add(pengajuanBiayaDetail.getPengajuanBiayaDetailId());
        }

        //Membuat Jurnal
        //membuat RK pengiriman modal
        Map dataRk = new HashMap();
        //mencari coa RK
        Branch branch = branchBo.getBranchById(branchId,"Y");
        Map rkUnit = new HashMap();
        rkUnit.put("nilai",jumlah);
        rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(branch.getCoaRk()));
        dataRk.put("rk_kd_unit",rkUnit);

        Map giro = new HashMap();
        giro.put("nilai",jumlah);
        giro.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(coaKas));
        dataRk.put("metode_bayar",giro);

        // membuat jurnal rk dan jurnal biaya jika data sudah diterima unit
        billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_TERIMA_RK,dataRk,branchId,getKeteranganPembuatanRk(idPengajuan,status,coaKas,branchId).getKeterangan(),"Y");
//        pengajuanBiayaBo.cekApakahBisaDiClose(id);
        pengajuanBiayaBo.setRkDiterima(idPengajuan);
        return response;
    }

    public PengajuanBiaya cekApakahBolehRk (String pengajuanId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.cekApakahBolehRk(pengajuanId);
    }

    public String batalkanPengajuanBiaya(String pengajuanBiayaId,String keteranganBatal){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        String userLogin = CommonUtil.userIdLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        PengajuanBiaya data = new PengajuanBiaya();
        data.setFlagBatal("Y");
        data.setPengajuanBiayaId(pengajuanBiayaId);
        data.setKeteranganBatal(keteranganBatal);
        data.setAction("U");
        data.setLastUpdateWho(userLogin);
        data.setLastUpdate(updateTime);

        return pengajuanBiayaBo.batalkanPengajuanBiaya(data);

    }

    public String cekApakahSudahCloseSemua (String pengajuanDetailId){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.cekApakahSudahCloseSemua(pengajuanDetailId);
    }

    public List<StokDTO> getStockPerDivisi (String branchId, String divisiId, String stTanggal, String namaObat){
        List<StokDTO> results = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo= (PelayananBo) ctx.getBean("pelayananBoProxy");
        ObatBo obatBo= (ObatBo) ctx.getBean("obatBoProxy");
        String[] arrTanggal = stTanggal.split("-");

        ImSimrsPelayananEntity pelayanan = pelayananBo.getPelayananByDivisiId(divisiId,branchId);
        String idPelayanan = pelayanan.getIdPelayanan();

        List<TransaksiStok> transaksiStokList = obatBo.getListSummaryStok(branchId,idPelayanan,arrTanggal[0],arrTanggal[1],namaObat);

        for (TransaksiStok transaksiStok : transaksiStokList){
            StokDTO data = new StokDTO();
            data.setIdBarang(transaksiStok.getIdObat());
            data.setQty(String.valueOf(transaksiStok.getQtySaldo()));
            data.setTotalSaldo(CommonUtil.numbericFormat(transaksiStok.getTotalSaldo(),"###,###"));
            data.setSubTotalSaldo(CommonUtil.numbericFormat(transaksiStok.getSubTotalSaldo(),"###,###"));
            data.setQty(String.valueOf(transaksiStok.getQtySaldo()));
            data.setNamaBarang(transaksiStok.getNamaObat());
            results.add(data);
        }
        return results;
    }

    public List<PengajuanBiayaDetail> cariPengajuanBiayaDetail(String pengajuanBiayaDetailId) {
        logger.info("[PengajuanBiayaAction.cariPengajuanBiayaDetail] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.cariPengajuanBiayaDetail(pengajuanBiayaDetailId);
    }

    public List<PengajuanBiayaDetail> cariPengajuanBiayaDetailUangMuka(String pengajuanBiayaDetailId) {
        logger.info("[PengajuanBiayaAction.cariPengajuanBiayaDetail] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        return pengajuanBiayaBo.cariPengajuanBiayaDetailUangMuka(pengajuanBiayaDetailId);
    }

    public String cetakSurat(){
        logger.info("[PengajuanBiayaAction.printReportBuktiPosting] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo = (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");

        PengajuanBiayaDetail data =  pengajuanBiayaBo.getDetailPembayaranForReport(getId());
        Branch branch = branchBo.getBranchById(data.getBranchId(),"Y");
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getBranchId());

        String[] arrTanggal = data.getStTanggal().split("-");

        reportParams.put("reportTitle", "FORM PENGAJUAN ANGGARAN ( BUDGETING )");
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getBranchId());
        java.util.Date now = new java.util.Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(arrTanggal[1])+" "+arrTanggal[2]);
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("areaId", CommonUtil.userAreaName());
        reportParams.put("transaksiName", data.getTransaksiName());
        reportParams.put("pengajuanBiayaDetailId", data.getPengajuanBiayaDetailId());
        reportParams.put("stTanggal", data.getStTanggal());
        reportParams.put("stTanggalRealisasi", data.getStTanggalRealisasi());
        reportParams.put("divisiName", data.getDivisiName());
        reportParams.put("keperluanName", data.getKeperluanName());
        reportParams.put("stJumlah", data.getStJumlah());
        reportParams.put("noBudgetting", data.getNoBudgeting());
        reportParams.put("statusSaatIni", data.getStatusSaatIni());
        reportParams.put("keterangan", data.getKeterangan());
        reportParams.put("nama", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("jabatan", "Keuangan");
        reportParams.put("tanggalApprove", CommonUtil.convertDateToString(data.getApprovalKasubdivDate()));
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBo.saveErrorMessage(e.getMessage(), "cetakSurat");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.cetakSurat] Error when downloading ,", e1);
            }
            logger.error("[PengajuanBiayaAction.cetakSurat] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[PengajuanBiayaAction.cetakSurat] end process <<<");
        return "cetak_surat_pengajuan_biaya";
    }

    public PengajuanBiayaDetail getBudgetTerpakaiTransaksiSession(String branchId, String divisiId, String stTanggal, String noBudgetting) {
        logger.info("[PengajuanBiayaAction.searchSessionPengajuan] start process >>>");
        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setBudgetTerpakaiTransaksi(BigDecimal.ZERO);
        data.setStBudgetTerpakaiTransaksi(CommonUtil.numbericFormat(BigDecimal.ZERO,"###,###"));
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> listOfsearch= (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        if (listOfsearch!=null){
            for (PengajuanBiayaDetail pengajuanBiayaDetail : listOfsearch){
                if (pengajuanBiayaDetail.getNoBudgeting().equalsIgnoreCase(noBudgetting)){
                    data.setBudgetTerpakaiTransaksi(data.getBudgetTerpakaiTransaksi().add(pengajuanBiayaDetail.getJumlah()));
                    data.setStBudgetTerpakaiTransaksi(CommonUtil.numbericFormat(data.getBudgetTerpakaiTransaksi(),"###,###"));
                }
            }
        }

        return data;
    }

    public PengajuanBiayaDetail getInvestasiTerpakaiTransaksiSession(String branchId, String divisiId, String stTanggal, String idPengajuan) {
        logger.info("[PengajuanBiayaAction.searchSessionPengajuan] start process >>>");
        PengajuanBiayaDetail data = new PengajuanBiayaDetail();
        data.setBudgetTerpakaiTransaksi(BigDecimal.ZERO);
        data.setStBudgetTerpakaiTransaksi(CommonUtil.numbericFormat(BigDecimal.ZERO,"###,###"));
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> listOfsearch= (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        if (listOfsearch!=null){
            for (PengajuanBiayaDetail pengajuanBiayaDetail : listOfsearch){
                if (pengajuanBiayaDetail.getKeperluanId().equalsIgnoreCase(idPengajuan)){
                    data.setBudgetTerpakaiTransaksi(data.getBudgetTerpakaiTransaksi().add(pengajuanBiayaDetail.getJumlah()));
                    data.setStBudgetTerpakaiTransaksi(CommonUtil.numbericFormat(data.getBudgetTerpakaiTransaksi(),"###,###"));
                }
            }
        }

        return data;
    }

    public boolean cekApakahPengajuanBisaDiubah(String id,String stJumlah){
        boolean result ;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo = (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        BigDecimal jumlah = BigDecimal.valueOf(Double.valueOf(stJumlah.replace(".","").replace(",","")));
        result = pengajuanBiayaBo.cekApakahPengajuanBisaDiubah(id,jumlah);

        return result;
    }

    public String getTipeBudgetInSession() {
        logger.info("[PengajuanBiayaAction.searchSessionPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PengajuanBiayaDetail> listOfsearch= (List<PengajuanBiayaDetail>) session.getAttribute("listOfResultPengajuanBiayaDetail");
        String result = "";
        if (listOfsearch!=null){
            for (PengajuanBiayaDetail pengajuanBiayaDetail : listOfsearch){
                result = pengajuanBiayaDetail.getTransaksi();
            }
        }

        return result;
    }

    public String searchPengajuanAdmin() {
        logger.info("[PengajuanBiayaAction.searchPengajuanAdmin] start process >>>");
        PengajuanBiayaDetail searchPengajuanBiayaDetail = getPengajuanBiayaDetail();
        List<PengajuanBiayaDetail> listOfsearchPengajuanBiayaDetail = new ArrayList();
        try {
            listOfsearchPengajuanBiayaDetail = pengajuanBiayaBoProxy.getByCriteriaDetail(searchPengajuanBiayaDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.searchPengajuanAdmin");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.searchPengajuanAdmin] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.searchPengajuanAdmin] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPengajuanBiayaDetail);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanBiayaDetail.setBranchIdUser(branchId);
        }else{
            searchPengajuanBiayaDetail.setBranchIdUser("");
        }
        setPengajuanBiayaDetail(searchPengajuanBiayaDetail);
        logger.info("[PengajuanBiayaAction.searchPengajuanAdmin] end process <<<");

        return "success_pengajuan_admin";
    }

    public String terimaRk() {
        logger.info("[PengajuanBiayaAction.terimaRk] start process >>>");
        PengajuanBiayaDetail searchPengajuanBiayaDetail = new PengajuanBiayaDetail();
        searchPengajuanBiayaDetail.setFlag("Y");
        searchPengajuanBiayaDetail.setRkId(getRkId());

        List<PengajuanBiayaDetail> listOfsearchPengajuanBiayaDetail = new ArrayList();
        try {
            listOfsearchPengajuanBiayaDetail = pengajuanBiayaBoProxy.getByCriteriaDetail(searchPengajuanBiayaDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.terimaRk");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.terimaRk] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.terimaRk] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPengajuanBiayaDetail);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId!=null){
            searchPengajuanBiayaDetail.setBranchIdUser(branchId);
        }else{
            searchPengajuanBiayaDetail.setBranchIdUser("");
        }
        setPengajuanBiayaDetail(searchPengajuanBiayaDetail);

        logger.info("[PengajuanBiayaAction.terimaRk] end process <<<");

        return "terima_rk";
    }

    public PengajuanBiayaDetail getForModalPopUpDetail(String pengajuanBiayaDetailId) {
        logger.info("[PengajuanBiayaAction.getForModalPopUpDetail] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo = (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        PengajuanBiayaDetail modalPopUpDetail = pengajuanBiayaBo.modalPopUpDetail(pengajuanBiayaDetailId);

        return modalPopUpDetail;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public String searchPembayaranDo() {
        logger.info("[PengajuanBiayaAction.searchPembayaranDo] start process >>>");
        PengajuanBiayaRk search = getPengajuanBiayaRk();
        List<PengajuanBiayaRk> pengajuanBiayaRkList = new ArrayList();
        try {
            pengajuanBiayaRkList = pengajuanBiayaBoProxy.getDaftarPembayaranDo(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pengajuanBiayaBoProxy.saveErrorMessage(e.getMessage(), "PengajuanBiayaBO.searchPembayaranDo");
            } catch (GeneralBOException e1) {
                logger.error("[PengajuanBiayaAction.searchPembayaranDo] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PengajuanBiayaAction.searchPembayaranDo] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", pengajuanBiayaRkList);

        String branchId = CommonUtil.userBranchLogin();

        if (branchId!=null){
            search.setBranchIdUser(branchId);
        }else{
            search.setBranchIdUser("");
        }
        setPengajuanBiayaRk(search);
        logger.info("[PengajuanBiayaAction.searchPembayaranDo] end process <<<");

        // send to csv

        if ("csv".equalsIgnoreCase(search.getTipeCetak())){
            try {
                DataOutputStream doStream = null; // declare a print stream object

                doStream = new DataOutputStream(new FileOutputStream("tarikan_pembayaran_do.csv"));

                doStream.writeBytes("\n");
                doStream.writeBytes("Nama Report : Tarikan Pembayaran DO p");
                doStream.writeBytes("\n");
                doStream.writeBytes("Tanggal Penarikan : "+CommonUtil.convertDateToString(new java.util.Date()));
                doStream.writeBytes("\n");
                String[] headers = "ID,Unit,No. DO,Jatuh Tempo,ID Vendor,Vendor,Jumlah(RP),ID RK,No. Jurnal, Status".split(",");

                for(int i=0; i < headers.length; i++)
                {
                    if(i != headers.length-1)
                        doStream.writeBytes("\""+headers[i]+"\", ");
                    else
                        doStream.writeBytes("\""+headers[i]+"\"");
                }
                doStream.writeBytes("\n");

                for (PengajuanBiayaRk a : pengajuanBiayaRkList){
                    doStream.writeBytes("\""+a.getPengajuanBiayaRkId()+"\""+",");
                    doStream.writeBytes("\""+a.getBranchName()+"\""+",");
                    doStream.writeBytes("\""+a.getNoTransaksi()+"\""+",");
                    doStream.writeBytes("\""+a.getStTanggalInvoice()+"\""+",");
                    doStream.writeBytes("\""+a.getMasterId()+"\""+",");
                    doStream.writeBytes("\""+a.getMasterName()+"\""+",");
                    doStream.writeBytes("\""+a.getStJumlah()+"\""+",");
                    doStream.writeBytes("\""+a.getRkId()+"\""+",");
                    doStream.writeBytes("\""+a.getNoJurnal()+"\""+",");
                    doStream.writeBytes("\""+a.getStatusName()+"\"");
                    doStream.writeBytes("\n");
                }

                doStream.flush();
                doStream.close();
                setInputStream(new FileInputStream("tarikan_pembayaran_do.csv"));

            } // end try
            catch (Exception e) {
                e.printStackTrace();
            } // end catch

            return "export_hasil_csv_do";
        }else{
            if ("search".equalsIgnoreCase(search.getTipe())){
                return "success_pengajuan_rk";
            }else{
                return "success_add_pengajuan_rk";
            }
        }
    }


    public void kirimPengajuanPembayaranDoRk(String data,String branchId,String branchIdUser) {
        logger.info("[PengajuanBiayaAction.kirimPengajuanPembayaranDoRk] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        BigDecimal jumlah = BigDecimal.ZERO;
        List<PengajuanBiayaRk> pengajuanBiayaRkList = (List<PengajuanBiayaRk>) session.getAttribute("listOfResult");
        List<PengajuanBiayaRk> finalPengajuanBiayaRkList = new ArrayList<>();
        List<Map> pembayaranDo = new ArrayList<>();

        try {
            JSONArray json = new JSONArray(data);
            for (int i = 0; i < json.length(); i++) {
                PengajuanBiayaRk dataRk = new PengajuanBiayaRk();
                JSONObject obj = json.getJSONObject(i);
                dataRk.setNoTransaksi(obj.getString("noDo"));
                dataRk.setMasterId(obj.getString("masterId"));
                dataRk.setJumlah(BigDecimal.valueOf(Double.valueOf(obj.getString("jumlah").replace(",",""))));

                Map dataDo = new HashMap();
                dataDo.put("master_id",dataRk.getMasterId());
                dataDo.put("bukti",dataRk.getNoTransaksi());
                dataDo.put("nilai",dataRk.getJumlah());
                pembayaranDo.add(dataDo);

                jumlah = jumlah.add(dataRk.getJumlah());

                for (PengajuanBiayaRk pengajuanBiayaRk : pengajuanBiayaRkList){
                    if (pengajuanBiayaRk.getNoTransaksi().equalsIgnoreCase(dataRk.getNoTransaksi())){
                        pengajuanBiayaRk.setFlag("Y");
                        pengajuanBiayaRk.setAction("C");
                        pengajuanBiayaRk.setLastUpdateWho(CommonUtil.userIdLogin());
                        pengajuanBiayaRk.setCreatedWho(CommonUtil.userIdLogin());
                        pengajuanBiayaRk.setStatus("K");
                        pengajuanBiayaRk.setLastUpdate(new Timestamp (new java.util.Date().getTime()));
                        pengajuanBiayaRk.setCreatedDate(new Timestamp (new java.util.Date().getTime()));
                        pengajuanBiayaRk.setSave(true);
                        finalPengajuanBiayaRkList.add(pengajuanBiayaRk);
                    }
                }
            }

            //Membuat Jurnal
            //membuat RK pengiriman modal
            Map dataRk = new HashMap();
            //mencari coa RK
            Branch branch = branchBo.getBranchById(branchIdUser,"Y");
            Map rkUnit = new HashMap();
            rkUnit.put("nilai",jumlah);
            rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(branch.getCoaRk()));

            dataRk.put("rk_kd_unit",rkUnit);
            dataRk.put("data_do",pembayaranDo);

            String keterangan ="Pengajuan pembayaran DO Ke Kantor Pusat dari Unit :"+branch.getBranchName()+" pada tanggal "+CommonUtil.convertDateToString(new java.util.Date());

            //disimpan
            pengajuanBiayaBo.savePembayaranPengajuanDo(finalPengajuanBiayaRkList);

            //membuat jurnal RK dari kantor pusat
            billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_KIRIM_PENGAJUAN_PEMBAYARAN_DO,dataRk,branchIdUser,keterangan,"Y");
        }catch (Exception e){
            logger.error("[PengajuanBiayaAction.kirimPengajuanPembayaranDoRk] Error ," + "[" + e.getMessage() + "] ", e);
            throw new GeneralBOException(e);
        }
        logger.info("[PengajuanBiayaAction.kirimPengajuanPembayaranDoRk] end process <<<");
    }

    public void terimaPengajuanPembayaranDoRk(String data,String branchId,String branchIdUser) {
        logger.info("[PengajuanBiayaAction.terimaPengajuanPembayaranDoRk] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        BigDecimal jumlah = BigDecimal.ZERO;
        List<PengajuanBiayaRk> pengajuanBiayaRkList = (List<PengajuanBiayaRk>) session.getAttribute("listOfResult");
        List<PengajuanBiayaRk> finalPengajuanBiayaRkList = new ArrayList<>();
        List<Map> pembayaranDo = new ArrayList<>();

        try {
            JSONArray json = new JSONArray(data);
            for (int i = 0; i < json.length(); i++) {
                PengajuanBiayaRk dataRk = new PengajuanBiayaRk();
                JSONObject obj = json.getJSONObject(i);
                dataRk.setNoTransaksi(obj.getString("noDo"));
                dataRk.setMasterId(obj.getString("masterId"));
                dataRk.setJumlah(BigDecimal.valueOf(Double.valueOf(obj.getString("jumlah").replace(",",""))));

                Map dataDo = new HashMap();
                dataDo.put("master_id",dataRk.getMasterId());
                dataDo.put("nilai",dataRk.getJumlah());
                dataDo.put("bukti",dataRk.getNoTransaksi());
                pembayaranDo.add(dataDo);

                jumlah = jumlah.add(dataRk.getJumlah());

                for (PengajuanBiayaRk pengajuanBiayaRk : pengajuanBiayaRkList){
                    if (pengajuanBiayaRk.getNoTransaksi().equalsIgnoreCase(dataRk.getNoTransaksi())){
                        pengajuanBiayaRk.setAproveKeuId(CommonUtil.userIdLogin());
                        pengajuanBiayaRk.setApproveKeuDate(new Timestamp(new java.util.Date().getTime()));
                        pengajuanBiayaRk.setApproveKeuFlag("Y");
                        pengajuanBiayaRk.setLastUpdate(new Timestamp(new java.util.Date().getTime()));
                        pengajuanBiayaRk.setLastUpdateWho(CommonUtil.userIdLogin());
                        pengajuanBiayaRk.setAction("U");
                        pengajuanBiayaRk.setStatus("R");
                        pengajuanBiayaRk.setSave(true);
                        finalPengajuanBiayaRkList.add(pengajuanBiayaRk);
                    }
                }
            }

            //Membuat Jurnal
            //membuat RK pengiriman modal
            Map dataRk = new HashMap();
            //mencari coa RK
            Branch branch = branchBo.getBranchById(branchId,"Y");
            Map rkUnit = new HashMap();
            rkUnit.put("nilai",jumlah);
            rkUnit.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(branch.getCoaRk()));

            dataRk.put("rk_kd_unit",rkUnit);
            dataRk.put("data_do",pembayaranDo);

            String keterangan ="Penerimaan pembayaran DO dari Unit :"+branch.getBranchName()+" ke Kantor Pusat pada tanggal "+CommonUtil.convertDateToString(new java.util.Date());

            //disimpan
            pengajuanBiayaBo.approvePengajuanBiayaRk(finalPengajuanBiayaRkList);
            //membuat jurnal RK dari kantor pusat
            billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_TERIMA_PENGAJUAN_PEMBAYARAN_DO,dataRk,branchIdUser,keterangan,"Y");
        }catch (Exception e){
            logger.error("[PengajuanBiayaAction.kirimPengajuanPembayaranDoRk] Error ," + "[" + e.getMessage() + "] ", e);
            throw new GeneralBOException(e);
        }
        logger.info("[PengajuanBiayaAction.terimaPengajuanPembayaranDoRk] end process <<<");
    }

    public void pembayaranDo(String data,String metodeBayar, String branchIdUser) {
        logger.info("[PengajuanBiayaAction.pembayaranDo] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        BigDecimal jumlah = BigDecimal.ZERO;
        List<PengajuanBiayaRk> pengajuanBiayaRkList = (List<PengajuanBiayaRk>) session.getAttribute("listOfResult");
        List<PengajuanBiayaRk> finalPengajuanBiayaRkList = new ArrayList<>();
        List<Map> pembayaranDo = new ArrayList<>();

        try {
            JSONArray json = new JSONArray(data);
            for (int i = 0; i < json.length(); i++) {
                PengajuanBiayaRk dataRk = new PengajuanBiayaRk();
                JSONObject obj = json.getJSONObject(i);
                dataRk.setNoTransaksi(obj.getString("noDo"));
                dataRk.setMasterId(obj.getString("masterId"));
                dataRk.setJumlah(BigDecimal.valueOf(Double.valueOf(obj.getString("jumlah").replace(",",""))));

                Map dataDo = new HashMap();
                dataDo.put("master_id",dataRk.getMasterId());
                dataDo.put("bukti",dataRk.getNoTransaksi());
                dataDo.put("nilai",dataRk.getJumlah());
                pembayaranDo.add(dataDo);

                jumlah = jumlah.add(dataRk.getJumlah());

                for (PengajuanBiayaRk pengajuanBiayaRk : pengajuanBiayaRkList){
                    if (pengajuanBiayaRk.getNoTransaksi().equalsIgnoreCase(dataRk.getNoTransaksi())){
                        pengajuanBiayaRk.setAction("U");
                        pengajuanBiayaRk.setLastUpdateWho(CommonUtil.userIdLogin());
                        pengajuanBiayaRk.setStatus("D");
                        pengajuanBiayaRk.setLastUpdate(new Timestamp (new java.util.Date().getTime()));
                        pengajuanBiayaRk.setSave(true);
                        finalPengajuanBiayaRkList.add(pengajuanBiayaRk);
                    }
                }
            }

            //Membuat Jurnal
            //membuat RK pengiriman modal
            Map dataRk = new HashMap();

            Map giro = new HashMap();
            giro.put("nilai",jumlah);
            giro.put("rekening_id",kodeRekeningBo.getRekeningIdByKodeRekening(metodeBayar));
            dataRk.put("metode_bayar",giro);

            dataRk.put("data_do",pembayaranDo);

            String keterangan ="Pembayaran DO Kantor Pusat pada tanggal "+CommonUtil.convertDateToString(new java.util.Date());

            //membuat jurnal RK dari kantor pusat
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PEMBAYARAN_DO,dataRk,branchIdUser,keterangan,"Y");

            //disimpan
            pengajuanBiayaBo.savePembayaranPengajuanDoFinal(finalPengajuanBiayaRkList,jurnal.getNoJurnal(),metodeBayar);
        }catch (Exception e){
            logger.error("[PengajuanBiayaAction.pembayaranDo] Error ," + "[" + e.getMessage() + "] ", e);
            throw new GeneralBOException(e);
        }
        logger.info("[PengajuanBiayaAction.pembayaranDo] end process <<<");
    }

    public String eksportCsvDo(String branchId,String masterId,String pengajuanBiayaId,
                               String rkId,String stTanggalDari,String stTanggalSelesai,String statusPembayaran){

        PengajuanBiayaRk search = new PengajuanBiayaRk();
        search.setBranchId(branchId);
        search.setMasterId(masterId);
        search.setPengajuanBiayaRkId(pengajuanBiayaId);
        search.setRkId(rkId);
        search.setStTanggalDari(stTanggalDari);
        search.setStTanggalSelesai(stTanggalSelesai);
        search.setStatus(statusPembayaran);

        DataOutputStream doStream = null; // declare a print stream object
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengajuanBiayaBo pengajuanBiayaBo= (PengajuanBiayaBo) ctx.getBean("pengajuanBiayaBoProxy");

        List<PengajuanBiayaRk> pengajuanBiayaRkList = pengajuanBiayaBo.getDaftarPembayaranDo(search);

        try {
            doStream = new DataOutputStream(new FileOutputStream("tarikan_pembayaran_do.csv"));

            doStream.writeBytes("\n");
            doStream.writeBytes("Nama Report : Tarikan Pembayaran DO p");
            doStream.writeBytes("\n");
            doStream.writeBytes("Tanggal Penarikan : "+CommonUtil.convertDateToString(new java.util.Date()));
            doStream.writeBytes("\n");
            String[] headers = "ID,Unit,No. DO,Jatuh Tempo,ID Vendor,Vendor,Jumlah(RP),ID RK,No. Jurnal, Status".split(",");

            for(int i=0; i < headers.length; i++)
            {
                if(i != headers.length-1)
                    doStream.writeBytes("\""+headers[i]+"\", ");
                else
                    doStream.writeBytes("\""+headers[i]+"\"");
            }
            doStream.writeBytes("\n");

            for (PengajuanBiayaRk a : pengajuanBiayaRkList){
                doStream.writeBytes("\""+a.getPengajuanBiayaRkId()+"\""+",");
                doStream.writeBytes("\""+a.getBranchName()+"\""+",");
                doStream.writeBytes("\""+a.getNoTransaksi()+"\""+",");
                doStream.writeBytes("\""+a.getStTanggalInvoice()+"\""+",");
                doStream.writeBytes("\""+a.getMasterId()+"\""+",");
                doStream.writeBytes("\""+a.getMasterName()+"\""+",");
                doStream.writeBytes("\""+a.getStJumlah()+"\""+",");
                doStream.writeBytes("\""+a.getRkId()+"\""+",");
                doStream.writeBytes("\""+a.getNoJurnal()+"\""+",");
                doStream.writeBytes("\""+a.getStatusName()+"\"");
                doStream.writeBytes("\n");
            }

            doStream.flush();
            doStream.close();
            setInputStream(new FileInputStream("tarikan_pembayaran_do.csv"));

        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return "export_hasil_csv_do";
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
