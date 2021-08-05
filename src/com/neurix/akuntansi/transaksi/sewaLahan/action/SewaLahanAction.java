package com.neurix.akuntansi.transaksi.sewaLahan.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.MappingDetail;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.kas.bo.KasBo;
import com.neurix.akuntansi.transaksi.kas.model.Kas;
import com.neurix.akuntansi.transaksi.sewaLahan.bo.SewaLahanBo;
import com.neurix.akuntansi.transaksi.sewaLahan.model.ItAkunSewaLahanEntity;
import com.neurix.akuntansi.transaksi.sewaLahan.model.SewaLahan;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.engine.Mapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class SewaLahanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(SewaLahanAction.class);
    private SewaLahanBo sewaLahanBoProxy;
    private SewaLahan sewaLahan;
    private List<SewaLahan> listOfComboSewaLahan = new ArrayList<SewaLahan>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SewaLahanAction.logger = logger;
    }

    public SewaLahanBo getSewaLahanBoProxy() {
        return sewaLahanBoProxy;
    }

    public void setSewaLahanBoProxy(SewaLahanBo sewaLahanBoProxy) {
        this.sewaLahanBoProxy = sewaLahanBoProxy;
    }

    public SewaLahan getSewaLahan() {
        return sewaLahan;
    }

    public void setSewaLahan(SewaLahan sewaLahan) {
        this.sewaLahan = sewaLahan;
    }

    public List<SewaLahan> getListOfComboSewaLahan() {
        return listOfComboSewaLahan;
    }

    public void setListOfComboSewaLahan(List<SewaLahan> listOfComboSewaLahan) {
        this.listOfComboSewaLahan = listOfComboSewaLahan;
    }

    public SewaLahan init(String kode, String flag){
        logger.info("[SewaLahanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SewaLahan> listOfResult = (List<SewaLahan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SewaLahan sewaLahan : listOfResult) {
                    if(kode.equalsIgnoreCase(sewaLahan.getSewaLahanId()) && flag.equalsIgnoreCase(sewaLahan.getFlag())){
                        setSewaLahan(sewaLahan);
                        break;
                    }
                }
            } else {
                setSewaLahan(new SewaLahan());
            }

            logger.info("[SewaLahanAction.init] end process >>>");
        }
        return getSewaLahan();
    }

    @Override
    public String add() {
        logger.info("[SewaLahanAction.add] start process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[SewaLahanAction.edit] start process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[SewaLahanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SewaLahan deleteSewaLahan = new SewaLahan();

        if (itemFlag != null ) {

            try {
                deleteSewaLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SewaLahanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[SewaLahanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSewaLahan != null) {
                setSewaLahan(deleteSewaLahan);

            } else {
                deleteSewaLahan.setSewaLahanId(itemId);
                deleteSewaLahan.setFlag(itemFlag);
                setSewaLahan(deleteSewaLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSewaLahan.setSewaLahanId(itemId);
            deleteSewaLahan.setFlag(itemFlag);
            setSewaLahan(deleteSewaLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[SewaLahanAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[SewaLahanAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        SewaLahan deleteSewaLahan = new SewaLahan();

        if (itemFlag != null ) {
            try {
                deleteSewaLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[SewaLahanAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[SewaLahanAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteSewaLahan != null) {
                setSewaLahan(deleteSewaLahan);

            } else {
                deleteSewaLahan.setSewaLahanId(itemId);
                deleteSewaLahan.setFlag(itemFlag);
                setSewaLahan(deleteSewaLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteSewaLahan.setSewaLahanId(itemId);
            deleteSewaLahan.setFlag(itemFlag);
            setSewaLahan(deleteSewaLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[SewaLahanAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[SewaLahanAction.saveEdit] start process >>>");
        try {
            SewaLahan editSewaLahan = getSewaLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSewaLahan.setLastUpdateWho(userLogin);
            editSewaLahan.setLastUpdate(updateTime);
            editSewaLahan.setAction("U");
            editSewaLahan.setFlag("Y");

            sewaLahanBoProxy.saveEdit(editSewaLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SewaLahanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SewaLahanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SewaLahanAction.saveDelete] start process >>>");
        try {
            SewaLahan deleteSewaLahan = getSewaLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSewaLahan.setLastUpdate(updateTime);
            deleteSewaLahan.setLastUpdateWho(userLogin);
            deleteSewaLahan.setAction("U");
            deleteSewaLahan.setFlag("N");

            sewaLahanBoProxy.saveDelete(deleteSewaLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[SewaLahanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[SewaLahanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public void saveAdd(String branchId,String namaPenyewa,String stTanggal,String stNilai,String metodeBayar,
                        String keterangan,String bank,String nilaiPpn,String nilaiPph,String nilaiNetto,String noFaktur,String fileUpload){
        logger.info("[SewaLahanAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");

        if ("".equalsIgnoreCase(nilaiPph)){
            nilaiPph="0";
        }
        if ("".equalsIgnoreCase(nilaiPpn)){
            nilaiPpn="0";
        }

        try {
            SewaLahan sewaLahan = new SewaLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sewaLahan.setNoFaktur(noFaktur);
            sewaLahan.setBranchId(branchId);
            sewaLahan.setNamaPenyewa(namaPenyewa);
            sewaLahan.setTanggalBayar(CommonUtil.convertStringToDate(stTanggal));
            sewaLahan.setNilai(BigDecimal.valueOf(Double.valueOf(stNilai.replace(".","").replace(",",""))));
            sewaLahan.setNilaiPpn(BigDecimal.valueOf(Double.valueOf(nilaiPpn.replace(".","").replace(",",""))));
            sewaLahan.setNilaiPph(BigDecimal.valueOf(Double.valueOf(nilaiPph.replace(".","").replace(",",""))));
            sewaLahan.setNilaiNetto(sewaLahan.getNilai().subtract(sewaLahan.getNilaiPpn()).subtract(sewaLahan.getNilaiPph()));
            sewaLahan.setMetodeBayar(metodeBayar);
            sewaLahan.setBank(bank);
            sewaLahan.setKeterangan(keterangan);
            sewaLahan.setCancelFlag("N");
            sewaLahan.setCreatedWho(userLogin);
            sewaLahan.setLastUpdate(updateTime);
            sewaLahan.setCreatedDate(updateTime);
            sewaLahan.setLastUpdateWho(userLogin);
            sewaLahan.setAction("C");
            sewaLahan.setFlag("Y");

            sewaLahanBo.saveAdd(sewaLahan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "sewaLahanBoProxy.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[SewaLahanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
    }

    @Override
    public String search() {
        logger.info("[SewaLahanAction.search] start process >>>");
        SewaLahan searchSewaLahan = getSewaLahan();
        List<SewaLahan> listOfsearchSewaLahan = new ArrayList();
        try {
            listOfsearchSewaLahan = sewaLahanBoProxy.getByCriteria(searchSewaLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SewaLahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        searchSewaLahan.setBranchIdUser(CommonUtil.userBranchLogin());
        setSewaLahan(searchSewaLahan);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSewaLahan);

        logger.info("[SewaLahanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SewaLahanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        SewaLahan data = new SewaLahan();

        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }

        setSewaLahan(data);

        session.removeAttribute("listOfResult");

        logger.info("[SewaLahanAction.initForm] end process >>>");
        return INPUT;
    }


    public SewaLahan getForModalPopUp(String sewaLahanId) {
        logger.info("[SewaLahanAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");
        String itemFlag = "Y";
        SewaLahan modalPopUp = new SewaLahan();
        try {
            modalPopUp = init(sewaLahanId, itemFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBo.saveErrorMessage(e.getMessage(), "SewaLahanAction.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[SewaLahanAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public String postingJurnal(String sewaLahanId){
        logger.info("[SewaLahanAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");
            BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            SewaLahan data = new SewaLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItAkunSewaLahanEntity itAkunSewaLahanEntity = sewaLahanBo.getPenyewaanLahanById(sewaLahanId);

            Map dataPostingJurnal = new HashMap();

           // mapping kas, start
            MappingDetail kas = new MappingDetail();
            kas.setMetodeBayar(itAkunSewaLahanEntity.getBank() == null || itAkunSewaLahanEntity.getBank().isEmpty() ? "tunai" : "transfer");

//            2021-07-26 Sigit
            if (itAkunSewaLahanEntity.getBank() == null) {
                kas.setCoa(CommonConstant.KAS_TUNAI);
            }

//            kas.put("bank", itAkunSewaLahanEntity.getBank());
            // END

            kas.setNilai(itAkunSewaLahanEntity.getNilai());

            List<MappingDetail> listKas = new ArrayList<>();
            listKas.add(kas);

            // mapping kas, end

            // mapping PPN, Start
            MappingDetail mapPpn = new MappingDetail();
            mapPpn.setNilai(itAkunSewaLahanEntity.getNilaiPpn());

            List<MappingDetail> listMappingPPN = new ArrayList<>();
            listMappingPPN.add(mapPpn);

            // mapping PPN, End


            // mapping PPH, Start
            MappingDetail mapPph = new MappingDetail();
            mapPph.setMasterId(itAkunSewaLahanEntity.getNamaPenyewa());
            mapPph.setNilai(itAkunSewaLahanEntity.getNilaiPph());

            List<MappingDetail> listMappingPPH = new ArrayList<>();
            listMappingPPN.add(mapPph);

            // mapping PPH, End


            // mapping pendapatan sewa, start
            MappingDetail pendapatanPenyewaan = new MappingDetail();
            pendapatanPenyewaan.setNilai(itAkunSewaLahanEntity.getNilaiNetto());

            List<MappingDetail> listPendapatan = new ArrayList<>();
            listPendapatan.add(pendapatanPenyewaan);
            // mapping pendapatan sewa, end


            dataPostingJurnal.put("kas",listKas);
            dataPostingJurnal.put("ppn",listMappingPPN);
            dataPostingJurnal.put("pph",listMappingPPH);
            dataPostingJurnal.put("pendapatan_sewa_lahan",listPendapatan);

            //disini untuk posting jurnal untuk mendapat nojurnal
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PENYEWAAN_LAHAN,dataPostingJurnal,itAkunSewaLahanEntity.getBranchId(),itAkunSewaLahanEntity.getKeterangan(),"Y");
            data.setSewaLahanId(sewaLahanId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setNoJurnal(jurnal.getNoJurnal());
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            sewaLahanBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SewaLahanAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SewaLahanAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String cancelSewaLahan(String sewaLahanId){
        logger.info("[SewaLahanAction.cancelSewaLahan] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");
            SewaLahan data = new SewaLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setSewaLahanId(sewaLahanId);
            data.setCancelDate(updateTime);
            data.setCancelFlag("Y");
            data.setCancelWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
//            data.setAction("U");
            data.setFlag("Y");

            sewaLahanBo.batalkanSewaLahan(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sewaLahanBoProxy.saveErrorMessage(e.getMessage(), "SewaLahanAction.cancelSewaLahan");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.cancelSewaLahan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SewaLahanAction.cancelSewaLahan] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SewaLahanAction.cancelSewaLahan] end process <<<");

        return "Berhasil Membatalkan Penyewaan Lahan";
    }

    public String cetakBuktiPembayaran(){
        logger.info("[SewaLahanAction.cetakBuktiPembayaran] start process >>>");
        List<SewaLahan> sewaLahanList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        SewaLahan data = getSewaLahan();
        Branch branch = new Branch();
        SewaLahan search = new SewaLahan();
        search.setSewaLahanId(data.getSewaLahanId());
        search.setFlag("Y");
        sewaLahanList = sewaLahanBo.getByCriteria(search);

        for (SewaLahan result : sewaLahanList){
            reportParams.put("namaPenyewa", result.getNamaPenyewaName());
            reportParams.put("jumlahTerbilang", CommonUtil.angkaToTerbilang(result.getNilai().longValue()));
            reportParams.put("keterangan", result.getKeterangan());
            reportParams.put("jumlah", result.getStNilai());
            reportParams.put("tanggalBayar",result.getStTanggalBayar());
            reportParams.put("namaPenerima",result.getCreatedWho());

            branch = branchBo.getBranchById(result.getBranchId(),"Y");
        }
        String reportName ="BUKTI PEMBAYARAN SEWA LAHAN";

        reportParams.put("reportTitle", reportName);
        reportParams.put("reportName", reportName);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", branch.getBranchId());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("sewaLahanId",data.getSewaLahanId());
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("areaId", CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = sewaLahanBo.saveErrorMessage(e.getMessage(), "cetakBuktiPembayaran");
            } catch (GeneralBOException e1) {
                logger.error("[SewaLahanAction.cetakBuktiPembayaran] Error when downloading ,", e1);
            }
            logger.error("[SewaLahanAction.cetakBuktiPembayaran] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[SewaLahanAction.cetakBuktiPembayaran] end process <<<");

        return "print_bukti_pembayaran";
    }

    public SewaLahan getViewApproval(String sewaLahanId) {
        logger.info("[SewaLahanAction.getViewApproval] start process >>>");
        String itemFlag = "Y";
        SewaLahan modalApproval = null;
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        try {
//            modalApproval = init(sewaLahanId, itemFlag);
            Kas kasBean = new Kas();
            kasBean.setKasId(sewaLahanId);
            kasBean.setFlag("Y");
            KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
            List<Kas> kasList = kasBo.getByCriteria(kasBean);
            for(Kas kas : kasList){
                if(kas.getKasId().equals(sewaLahanId)){
                    modalApproval = new SewaLahan();
                    modalApproval.setApprovalWho(kas.getRegisteredWho());
                    String stApproval = new SimpleDateFormat("dd-MM-yyyy").format(kas.getRegisteredDate());
                    modalApproval.setStApprovalDate(stApproval);
                    break;
                }
            }

            if(modalApproval == null){
                SewaLahanBo sewaLahanBo = (SewaLahanBo) ctx.getBean("sewaLahanBoProxy");
                SewaLahan sewaBean = new SewaLahan();
                sewaBean.setSewaLahanId(sewaLahanId);
                sewaBean.setFlag("Y");
                List<SewaLahan> sewaList = sewaLahanBo.getByCriteria(sewaBean);
                for(SewaLahan sewaLahan : sewaList){
                    if(sewaLahan.getSewaLahanId().equals(sewaLahanId)){
                        modalApproval = sewaLahan;
//                        modalApproval.setApprovalWho(sewaLahan.getApprovalWho());
//                        modalApproval.setCancelWho(sewaLahan.getCancelWho());
//                        String stApproval = new SimpleDateFormat("dd-MM-yyyy").format(sewaLahan.getApprovalDate());
//                        modalApproval.setStApprovalDate(stApproval);
                        break;
                    }
                }
            }
            
            
        } catch (GeneralBOException e) {
            throw new GeneralBOException("Error saat mengambil data approval ");
        }

        return modalApproval;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
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
