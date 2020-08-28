package com.neurix.akuntansi.transaksi.penyewaanLahan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.penyewaanLahan.bo.PenyewaanLahanBo;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.ItAkunPenyewaanLahanEntity;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.PenyewaanLahan;
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
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class PenyewaanLahanAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PenyewaanLahanAction.class);
    private PenyewaanLahanBo penyewaanLahanBoProxy;
    private PenyewaanLahan penyewaanLahan;
    private List<PenyewaanLahan> listOfComboPenyewaanLahan = new ArrayList<PenyewaanLahan>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PenyewaanLahanAction.logger = logger;
    }

    public PenyewaanLahanBo getPenyewaanLahanBoProxy() {
        return penyewaanLahanBoProxy;
    }

    public void setPenyewaanLahanBoProxy(PenyewaanLahanBo penyewaanLahanBoProxy) {
        this.penyewaanLahanBoProxy = penyewaanLahanBoProxy;
    }

    public PenyewaanLahan getPenyewaanLahan() {
        return penyewaanLahan;
    }

    public void setPenyewaanLahan(PenyewaanLahan penyewaanLahan) {
        this.penyewaanLahan = penyewaanLahan;
    }

    public List<PenyewaanLahan> getListOfComboPenyewaanLahan() {
        return listOfComboPenyewaanLahan;
    }

    public void setListOfComboPenyewaanLahan(List<PenyewaanLahan> listOfComboPenyewaanLahan) {
        this.listOfComboPenyewaanLahan = listOfComboPenyewaanLahan;
    }

    public PenyewaanLahan init(String kode, String flag){
        logger.info("[PenyewaanLahanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PenyewaanLahan> listOfResult = (List<PenyewaanLahan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (PenyewaanLahan penyewaanLahan: listOfResult) {
                    if(kode.equalsIgnoreCase(penyewaanLahan.getPenyewaanLahanId()) && flag.equalsIgnoreCase(penyewaanLahan.getFlag())){
                        setPenyewaanLahan(penyewaanLahan);
                        break;
                    }
                }
            } else {
                setPenyewaanLahan(new PenyewaanLahan());
            }

            logger.info("[PenyewaanLahanAction.init] end process >>>");
        }
        return getPenyewaanLahan();
    }

    @Override
    public String add() {
        logger.info("[PenyewaanLahanAction.add] start process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[PenyewaanLahanAction.edit] start process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[PenyewaanLahanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PenyewaanLahan deletePenyewaanLahan = new PenyewaanLahan();

        if (itemFlag != null ) {

            try {
                deletePenyewaanLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PenyewaanLahanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[PenyewaanLahanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePenyewaanLahan != null) {
                setPenyewaanLahan(deletePenyewaanLahan);

            } else {
                deletePenyewaanLahan.setPenyewaanLahanId(itemId);
                deletePenyewaanLahan.setFlag(itemFlag);
                setPenyewaanLahan(deletePenyewaanLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePenyewaanLahan.setPenyewaanLahanId(itemId);
            deletePenyewaanLahan.setFlag(itemFlag);
            setPenyewaanLahan(deletePenyewaanLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[PenyewaanLahanAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[PenyewaanLahanAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        PenyewaanLahan deletePenyewaanLahan = new PenyewaanLahan();

        if (itemFlag != null ) {
            try {
                deletePenyewaanLahan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[PenyewaanLahanAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[PenyewaanLahanAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deletePenyewaanLahan != null) {
                setPenyewaanLahan(deletePenyewaanLahan);

            } else {
                deletePenyewaanLahan.setPenyewaanLahanId(itemId);
                deletePenyewaanLahan.setFlag(itemFlag);
                setPenyewaanLahan(deletePenyewaanLahan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deletePenyewaanLahan.setPenyewaanLahanId(itemId);
            deletePenyewaanLahan.setFlag(itemFlag);
            setPenyewaanLahan(deletePenyewaanLahan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[PenyewaanLahanAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[PenyewaanLahanAction.saveEdit] start process >>>");
        try {
            PenyewaanLahan editPenyewaanLahan = getPenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editPenyewaanLahan.setLastUpdateWho(userLogin);
            editPenyewaanLahan.setLastUpdate(updateTime);
            editPenyewaanLahan.setAction("U");
            editPenyewaanLahan.setFlag("Y");

            penyewaanLahanBoProxy.saveEdit(editPenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[PenyewaanLahanAction.saveDelete] start process >>>");
        try {
            PenyewaanLahan deletePenyewaanLahan = getPenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deletePenyewaanLahan.setLastUpdate(updateTime);
            deletePenyewaanLahan.setLastUpdateWho(userLogin);
            deletePenyewaanLahan.setAction("U");
            deletePenyewaanLahan.setFlag("N");

            penyewaanLahanBoProxy.saveDelete(deletePenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PenyewaanLahanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PenyewaanLahanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public void saveAdd(String branchId,String namaPenyewa,String stTanggal,String stNilai,String metodeBayar,
                        String keterangan,String bank,String nilaiPpn,String nilaiPph,String nilaiNetto,String noFaktur,String fileUpload){
        logger.info("[PenyewaanLahanAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PenyewaanLahanBo penyewaanLahanBo= (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");

        try {
            PenyewaanLahan penyewaanLahan = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            //upload images
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(fileUpload);
            logger.info("Decoded upload data : " + decodedBytes.length);
            String fileName = noFaktur+"-"+dateFormater("MM")+dateFormater("yy")+".png";
            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+fileName;
            logger.info("File save path : " + uploadFile);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

            if (image == null) {
                logger.error("Buffered Image is null");
            }else{
                File f = new File(uploadFile);
                // write the image
                ImageIO.write(image, "png", f);
                penyewaanLahan.setUrlFakturImage(fileName);
            }

            penyewaanLahan.setNoFaktur(noFaktur);
            penyewaanLahan.setBranchId(branchId);
            penyewaanLahan.setNamaPenyewa(namaPenyewa);
            penyewaanLahan.setTanggalBayar(CommonUtil.convertStringToDate(stTanggal));
            penyewaanLahan.setNilai(BigDecimal.valueOf(Double.valueOf(stNilai.replace(".","").replace(",",""))));
            penyewaanLahan.setNilaiPpn(BigDecimal.valueOf(Double.valueOf(nilaiPpn.replace(".","").replace(",",""))));
            penyewaanLahan.setNilaiPph(BigDecimal.valueOf(Double.valueOf(nilaiPph.replace(".","").replace(",",""))));
            penyewaanLahan.setNilaiNetto(BigDecimal.valueOf(Double.valueOf(nilaiNetto.replace(".","").replace(",",""))));
            penyewaanLahan.setMetodeBayar(metodeBayar);
            penyewaanLahan.setBank(bank);
            penyewaanLahan.setKeterangan(keterangan);
            penyewaanLahan.setCancelFlag("N");
            penyewaanLahan.setCreatedWho(userLogin);
            penyewaanLahan.setLastUpdate(updateTime);
            penyewaanLahan.setCreatedDate(updateTime);
            penyewaanLahan.setLastUpdateWho(userLogin);
            penyewaanLahan.setAction("C");
            penyewaanLahan.setFlag("Y");

            penyewaanLahanBo.saveAdd(penyewaanLahan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "penyewaanLahanBoProxy.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[PenyewaanLahanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
    }

    @Override
    public String search() {
        logger.info("[PenyewaanLahanAction.search] start process >>>");
        PenyewaanLahan searchPenyewaanLahan = getPenyewaanLahan();
        List<PenyewaanLahan> listOfsearchPenyewaanLahan = new ArrayList();
        try {
            listOfsearchPenyewaanLahan = penyewaanLahanBoProxy.getByCriteria(searchPenyewaanLahan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchPenyewaanLahan);

        logger.info("[PenyewaanLahanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PenyewaanLahanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PenyewaanLahan data = new PenyewaanLahan();

        if (branchId!=null){
            data.setBranchId(branchId);
        }else{
            data.setBranchId("");
        }

        setPenyewaanLahan(data);

        session.removeAttribute("listOfResult");

        logger.info("[PenyewaanLahanAction.initForm] end process >>>");
        return INPUT;
    }


    public PenyewaanLahan getForModalPopUp(String penyewaanLahanId) {
        logger.info("[PenyewaanLahanAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PenyewaanLahanBo penyewaanLahanBo= (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
        String itemFlag = "Y";
        PenyewaanLahan modalPopUp = new PenyewaanLahan();
        try {
            modalPopUp = init(penyewaanLahanId, itemFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBo.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[PenyewaanLahanAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        return modalPopUp;
    }

    public String postingJurnal(String penyewaanLahanId){
        logger.info("[PenyewaanLahanAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PenyewaanLahanBo penyewaanLahanBo = (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
            BillingSystemBo billingSystemBo= (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            PenyewaanLahan data = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ItAkunPenyewaanLahanEntity penyewaanLahanEntity = penyewaanLahanBo.getPenyewaanLahanById(penyewaanLahanId);

            Map dataPostingJurnal = new HashMap();
            Map kas = new HashMap();
            kas.put("metode_bayar",penyewaanLahanEntity.getMetodeBayar());
            kas.put("bank", penyewaanLahanEntity.getBank());
            kas.put("nilai",penyewaanLahanEntity.getNilai());

            Map mapPpn = new HashMap();
//            mapPpn.put("master_id",);
            mapPpn.put("nilai",penyewaanLahanEntity.getNilaiPpn());

            Map pendapatanPenyewaan = new HashMap();
            pendapatanPenyewaan.put("nilai",penyewaanLahanEntity.getNilaiNetto());

            dataPostingJurnal.put("kas",kas);
            dataPostingJurnal.put("ppn",mapPpn);
            dataPostingJurnal.put("pendapatan_sewa_lahan",pendapatanPenyewaan);

            //disini untuk posting jurnal untuk mendapat nojurnal
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_PENYEWAAN_LAHAN,dataPostingJurnal,penyewaanLahanEntity.getBranchId(),penyewaanLahanEntity.getKeterangan(),"Y");
            data.setPenyewaanLahanId(penyewaanLahanId);
            data.setApprovalDate(updateTime);
            data.setApprovalFlag("Y");
            data.setApprovalWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setNoJurnal(jurnal.getNoJurnal());
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            penyewaanLahanBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String cancelPenyewaanLahan(String penyewaanLahanId){
        logger.info("[PenyewaanLahanAction.cancelPenyewaanLahan] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PenyewaanLahanBo penyewaanLahanBo = (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
            PenyewaanLahan data = new PenyewaanLahan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setPenyewaanLahanId(penyewaanLahanId);
            data.setCancelDate(updateTime);
            data.setCancelFlag("Y");
            data.setCancelWho(userLogin);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");

            penyewaanLahanBo.batalkanPenyewaanLahan(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBoProxy.saveErrorMessage(e.getMessage(), "PenyewaanLahanAction.cancelPenyewaanLahan");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.cancelPenyewaanLahan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PenyewaanLahanAction.cancelPenyewaanLahan] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PenyewaanLahanAction.cancelPenyewaanLahan] end process <<<");

        return "Berhasil Membatalkan Penyewaan Lahan";
    }

    public String cetakBuktiPembayaran(){
        logger.info("[PenyewaanLahanAction.cetakBuktiPembayaran] start process >>>");
        List<PenyewaanLahan> penyewaanLahanList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PenyewaanLahanBo penyewaanLahanBo = (PenyewaanLahanBo) ctx.getBean("penyewaanLahanBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        PenyewaanLahan data = getPenyewaanLahan();
        Branch branch = new Branch();
        PenyewaanLahan search = new PenyewaanLahan();
        search.setPenyewaanLahanId(data.getPenyewaanLahanId());
        search.setFlag("Y");
        penyewaanLahanList=penyewaanLahanBo.getByCriteria(search);

        for (PenyewaanLahan result : penyewaanLahanList){
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
        java.util.Date now = new java.util.Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("penyewaanLahanId",data.getPenyewaanLahanId());
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("areaId", CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = penyewaanLahanBo.saveErrorMessage(e.getMessage(), "cetakBuktiPembayaran");
            } catch (GeneralBOException e1) {
                logger.error("[PenyewaanLahanAction.cetakBuktiPembayaran] Error when downloading ,", e1);
            }
            logger.error("[PenyewaanLahanAction.cetakBuktiPembayaran] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[PenyewaanLahanAction.cetakBuktiPembayaran] end process <<<");

        return "print_bukti_pembayaran";
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
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
