package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.trans.bo.TransBo;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.PembayaranUtangPiutangBo;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.EfakturDTO;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.Lampiran;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.bo.CompanyBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.Company;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        session.removeAttribute("listOfResultLampiran");
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
        session.removeAttribute("listOfResultLampiran");
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
        session.removeAttribute("listOfResultLampiran");
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
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<Lampiran> lampiranList= (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        PembayaranUtangPiutang pembayaranUtangPiutang = getPembayaranUtangPiutang();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal bayar = BigDecimal.valueOf(Double.valueOf(pembayaranUtangPiutang.getStBayar().replace(".","")));

        if ("pengajuan_biaya".equalsIgnoreCase(pembayaranUtangPiutang.getTipeMaster())){
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(pembayaranUtangPiutang.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutang.getMetodePembayaran());

                //Jika pembayaran berhasil
                //Membuat Billing
                List<Map> dataMap = new ArrayList<>();
                String pengajuanBiayaDetailId="";
                String sumberDana ="";
                Map mapPph = new HashMap();
                Map mapPpn = new HashMap();

                for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutangDetail.getRekeningId());
                    BigDecimal jumlahPembayaran = new BigDecimal(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(".",""));
                    BigDecimal ppn = new BigDecimal(pembayaranUtangPiutangDetail.getStPpn().replace(".",""));
                    BigDecimal pph = new BigDecimal(pembayaranUtangPiutangDetail.getStPph().replace(".",""));
                    jumlahPembayaran=jumlahPembayaran.add(pph);
                    jumlahPembayaran=jumlahPembayaran.subtract(ppn);
                    Map hs = new HashMap();
                    hs.put("bukti",pembayaranUtangPiutangDetail.getNoNota());
                    hs.put("nilai",jumlahPembayaran);
                    hs.put("master_id", pembayaranUtangPiutangDetail.getMasterId());
                    hs.put("divisi_id", pembayaranUtangPiutangDetail.getDivisiId());
                    hs.put("rekening_id", rekeningId);
                    dataMap.add(hs);
                    pengajuanBiayaDetailId = pembayaranUtangPiutangDetail.getPengajuanBiayaDetailId();
                    sumberDana = pembayaranUtangPiutangDetail.getNoBugetting();

                    if(!"".equalsIgnoreCase(pembayaranUtangPiutangDetail.getNoFakturPajak())&&pembayaranUtangPiutangDetail.getStFileUpload() != null && !"".equalsIgnoreCase(pembayaranUtangPiutangDetail.getStFileUpload())){
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(pembayaranUtangPiutangDetail.getStFileUpload());
                        logger.info("Decoded upload data : " + decodedBytes.length);
                        String fileName = pembayaranUtangPiutangDetail.getNoFakturPajak()+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+fileName;
                        logger.info("File save path : " + uploadFile);
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                        if (image == null) {
                            logger.error("Buffered Image is null");
                        }else{
                            File f = new File(uploadFile);
                            // write the image
                            ImageIO.write(image, "png", f);
                            pembayaranUtangPiutangDetail.setUrlFakturImage(fileName);
                        }
                    }

                    mapPph.put("master_id",pembayaranUtangPiutangDetail.getMasterId());
                    mapPph.put("nilai",pph);

                    mapPpn.put("master_id",pembayaranUtangPiutangDetail.getMasterId());
                    mapPpn.put("nilai",ppn);
                }

                Map kas = new HashMap();
                kas.put("nilai",bayar);
                kas.put("rekening_id", rekeningIdBayar);

                Map data = new HashMap();
                data.put(parameter,dataMap);
                data.put("pph",mapPph);
                data.put("ppn",mapPpn);
                data.put("metode_bayar",kas);
                if ("Y".equalsIgnoreCase(pembayaranUtangPiutang.getTipePengajuanBiaya())){
                    List<ItJurnalEntity> jurnalEntityList = billingSystemBoProxy.getJurnalByPengajuanId(pengajuanBiayaDetailId);
                    if (jurnalEntityList.size()!=0){
                        throw new GeneralBOException("Pengajuan biaya dengan ID ini sudah di dilakukan pengeluaran kas");
                    }
                    data.put("pengajuan_id",pengajuanBiayaDetailId);
                    data.put("sumber_dana",sumberDana);
                }

                pembayaranUtangPiutang.setBayar(bayar);
                pembayaranUtangPiutang.setTanggal(CommonUtil.convertStringToDate2(pembayaranUtangPiutang.getStTanggal()));

                pembayaranUtangPiutang.setCreatedWho(userLogin);
                pembayaranUtangPiutang.setLastUpdate(updateTime);
                pembayaranUtangPiutang.setCreatedDate(updateTime);
                pembayaranUtangPiutang.setLastUpdateWho(userLogin);
                pembayaranUtangPiutang.setAction("C");
                pembayaranUtangPiutang.setFlag("Y");
                pembayaranUtangPiutang.setNoJurnal(billingSystemBoProxy.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),data,pembayaranUtangPiutang.getBranchId(),pembayaranUtangPiutang.getKeterangan(),"N").getNoJurnal());

                pembayaranUtangPiutangBoProxy.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList,lampiranList);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(pembayaranUtangPiutang.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutang.getMetodePembayaran());
                //Jika pembayaran berhasil
                //Membuat Billing
                List<Map> dataMap = new ArrayList<>();
                for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList) {
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(pembayaranUtangPiutangDetail.getRekeningId());
//                    String rekeningId = pembayaranUtangPiutangDetail.getRekeningId();
                    BigDecimal jumlahPembayaran = new BigDecimal(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(".", ""));
                    Map hs = new HashMap();
                    hs.put("bukti", pembayaranUtangPiutangDetail.getNoNota());
                    hs.put("nilai", jumlahPembayaran);
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

                pembayaranUtangPiutang.setBayar(bayar);
                pembayaranUtangPiutang.setTanggal(CommonUtil.convertStringToDate2(pembayaranUtangPiutang.getStTanggal()));

                pembayaranUtangPiutang.setCreatedWho(userLogin);
                pembayaranUtangPiutang.setLastUpdate(updateTime);
                pembayaranUtangPiutang.setCreatedDate(updateTime);
                pembayaranUtangPiutang.setLastUpdateWho(userLogin);
                pembayaranUtangPiutang.setAction("C");
                pembayaranUtangPiutang.setFlag("Y");
                pembayaranUtangPiutang.setNoJurnal(billingSystemBoProxy.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),data,pembayaranUtangPiutang.getBranchId(),pembayaranUtangPiutang.getKeterangan(),"N").getNoJurnal());

                pembayaranUtangPiutangBoProxy.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList,lampiranList);
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
        List<Lampiran> lampiranList= (List<Lampiran>) session.getAttribute("listOfResultLampiran");

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
            Jurnal jurnal = billingSystemBoProxy.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),data,pembayaranUtangPiutang.getBranchId(),pembayaranUtangPiutang.getKeterangan(),"N");
            noJurnal = jurnal.getNoJurnal();
            pembayaranUtangPiutang.setNoJurnal(noJurnal);
            pembayaranUtangPiutangBoProxy.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList,lampiranList);
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

    public String saveAddKoreksiPengajuan(String pengajuanId,String kodeRekeningLawan,String metodeBayar,String stJumlahPembayaran,
                                          String stNetto,String stPpn,String stPph, String kodeVendor,String stUangMuka,String nip,
                                          String buktiUangMuka,String noFakturPajak,String uploadFakturPajak,String keterangan,String branchId,
                                          String divisiId,String sumberDana){
        logger.info("[PembayaranUtangPiutangAction.saveAddKoreksiPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal="";
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<Lampiran> lampiranList= (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        //convert ke bigdecimal
        BigDecimal netto = new BigDecimal(stNetto.replace(".",""));;
        BigDecimal ppn = BigDecimal.ZERO;
        BigDecimal pph = BigDecimal.ZERO;
        BigDecimal nilaiUangMuka = new BigDecimal(stUangMuka.replace(".",""));
        BigDecimal jumlahPembayaran = new BigDecimal(stJumlahPembayaran.replace(".",""));

        if (!"".equalsIgnoreCase(stPpn)){
            ppn = new BigDecimal(stPpn.replace(".",""));
        }

        if (!"".equalsIgnoreCase(stPph)){
            pph = new BigDecimal(stPph.replace(".",""));
        }

        String tipeTransaksi="";///////>>>>>>>>>>>>>>>>>>>
        if (nilaiUangMuka.add(pph).compareTo(jumlahPembayaran.add(ppn))<0){
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_KURANG; // TIPE TRANSAKSI UANG MUKA KURANG
        }else{
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_LEBIH;; // TIPE TRANSAKSI UANG MUKA LEBIH ATAU PAS
        }

        PembayaranUtangPiutang pembayaranUtangPiutang = new PembayaranUtangPiutang();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        pembayaranUtangPiutang.setTipeTransaksi(tipeTransaksi);
        pembayaranUtangPiutang.setBayar(jumlahPembayaran);
        pembayaranUtangPiutang.setMetodePembayaran(metodeBayar);
        pembayaranUtangPiutang.setNoSlipBank("");
        pembayaranUtangPiutang.setBranchId(branchId);
        pembayaranUtangPiutang.setKeterangan(keterangan);
        pembayaranUtangPiutang.setTipePembayaran("KR");
        pembayaranUtangPiutang.setTanggal(new Date(new java.util.Date().getTime()));

        pembayaranUtangPiutang.setCreatedWho(userLogin);
        pembayaranUtangPiutang.setLastUpdate(updateTime);
        pembayaranUtangPiutang.setCreatedDate(updateTime);
        pembayaranUtangPiutang.setLastUpdateWho(userLogin);
        pembayaranUtangPiutang.setAction("C");
        pembayaranUtangPiutang.setFlag("Y");

        try {
            //get parameter pembayaran
            Map dataPostingJurnal = new HashMap();
            Map kas = new HashMap();
            String rekeningIdBayar = kodeRekeningBo.getRekeningIdByKodeRekening(metodeBayar);
            kas.put("rekening_id",rekeningIdBayar);
            kas.put("nilai",netto);

            Map mapPpn = new HashMap();
            mapPpn.put("master_id",kodeVendor);
            mapPpn.put("nilai",ppn);

            Map mapPph = new HashMap();
            mapPph.put("master_id",kodeVendor);
            mapPph.put("nilai",pph);

            Map uangMuka = new HashMap();
            uangMuka.put("master_id",nip);
            uangMuka.put("bukti",buktiUangMuka);
            uangMuka.put("nilai",nilaiUangMuka);

            Map bebanPokok = new HashMap();
            String rekeningId = kodeRekeningBo.getRekeningIdByKodeRekening(kodeRekeningLawan);
            bebanPokok.put("nilai",jumlahPembayaran);
            bebanPokok.put("rekening_id",rekeningId);
            bebanPokok.put("divisi_id",divisiId);

            dataPostingJurnal.put("metode_bayar",kas);
            dataPostingJurnal.put("ppn_masukan",mapPpn);
            dataPostingJurnal.put("pph",mapPph);
            dataPostingJurnal.put("beban",bebanPokok);
            dataPostingJurnal.put("uang_muka",uangMuka);
            dataPostingJurnal.put("pengajuan_id",pengajuanId);
            dataPostingJurnal.put("sumber_dana",sumberDana);

            Jurnal jurnal = billingSystemBo.createJurnal(pembayaranUtangPiutang.getTipeTransaksi(),dataPostingJurnal,branchId,keterangan,"N");
            noJurnal = jurnal.getNoJurnal();
            pembayaranUtangPiutang.setNoJurnal(noJurnal);
            pembayaranUtangPiutangBo.saveAddPembayaran(pembayaranUtangPiutang,pembayaranUtangPiutangDetailList,lampiranList);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBo.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangAction.saveAddKoreksiPengajuan");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.saveAddKoreksiPengajuan] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[PembayaranUtangPiutangAction.saveAddKoreksiPengajuan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[PembayaranUtangPiutangAction.saveAddKoreksiPengajuan] end process >>>");
        return "success menambahkan koreksi pengajuan";
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

        searchPembayaranUtangPiutang.setBranchIdUser(CommonUtil.userBranchLogin());

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

        searchPembayaranUtangPiutang.setBranchIdUser(CommonUtil.userBranchLogin());

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

        searchPembayaranUtangPiutang.setBranchIdUser(CommonUtil.userBranchLogin());

        setPembayaranUtangPiutang(searchPembayaranUtangPiutang);
        logger.info("[PembayaranUtangPiutangAction.searchKoreksi] end process <<<");

        return "success_koreksi";
    }

    public String searchKoreksiPengajuan() {
        logger.info("[PembayaranUtangPiutangAction.searchKoreksiPengajuan] start process >>>");
        PembayaranUtangPiutang searchPembayaranUtangPiutang = getPembayaranUtangPiutang();
        List<PembayaranUtangPiutang> listOfsearchPembayaranUtangPiutang = new ArrayList();
        try {
            listOfsearchPembayaranUtangPiutang = pembayaranUtangPiutangBoProxy.getByCriteria(searchPembayaranUtangPiutang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.searchKoreksiPengajuan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.searchKoreksiPengajuan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listPembayaranDetailModal");
        session.setAttribute("listOfResult", listOfsearchPembayaranUtangPiutang);

        searchPembayaranUtangPiutang.setBranchIdUser(CommonUtil.userBranchLogin());

        setPembayaranUtangPiutang(searchPembayaranUtangPiutang);
        logger.info("[PembayaranUtangPiutangAction.searchKoreksiPengajuan] end process <<<");

        return "success_koreksi_pengajuan";
    }

    @Override
    public String initForm() {
        logger.info("[PembayaranUtangPiutangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PembayaranUtangPiutang data = new PembayaranUtangPiutang();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
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
            data.setBranchIdUser(branchId);
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
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }
        setPembayaranUtangPiutang(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PembayaranUtangPiutangAction.initFormKoreksi] end process >>>");
        return "input_koreksi";
    }

    public String initFormKoreksiPengajuan() {
        logger.info("[PembayaranUtangPiutangAction.initFormKoreksiPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        PembayaranUtangPiutang data = new PembayaranUtangPiutang();
        if (branchId!=null){
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        }else{
            data.setBranchId("");
        }
        setPembayaranUtangPiutang(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPembayaranDetail");
        logger.info("[PembayaranUtangPiutangAction.initFormKoreksiPengajuan] end process >>>");
        return "input_koreksi_pengajuan";
    }

    public String saveDetailPembayaran(String kodeVendor,String namaVendor,String noNota,String jumlahPembayaran,
                                       String rekeningId,String divisiId,String divisiName,String tipePengajuanBiaya,
                                       String pengajuanBiayaDetailId,String noBudgetting,String pph,String ppn,String noFakturPajak,
                                       String fileUpload) {
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
            newData.setPengajuanBiayaDetailId(pengajuanBiayaDetailId);
            newData.setNoBugetting(noBudgetting);
            newData.setStPpn(ppn);
            newData.setStPph(pph);
            newData.setNoFakturPajak(noFakturPajak);
            newData.setStFileUpload(fileUpload);

            piutangDetailArrayList.add(newData);
            session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        }else{
            piutangDetailArrayList.addAll(piutangDetailList);
            for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail:piutangDetailList){
                if (pembayaranUtangPiutangDetail.getMasterId().equalsIgnoreCase(kodeVendor)&&pembayaranUtangPiutangDetail.getNoNota().equalsIgnoreCase(noNota)&&pembayaranUtangPiutangDetail.getRekeningId().equalsIgnoreCase(rekeningId)){
                    ada=true;
                    status="Pengeluaran/Pemasukan dengan data ini ( kode rekening,vendor , no nota ) sudah ada.";
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
                newData.setPengajuanBiayaDetailId(pengajuanBiayaDetailId);
                newData.setNoBugetting(noBudgetting);
                newData.setStPpn(ppn);
                newData.setStPph(pph);
                newData.setNoFakturPajak(noFakturPajak);
                newData.setStFileUpload(fileUpload);

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

    public void deleteDetailPembayaran(String rekeningId,String divisiId,String vendor,String noNota,String biaya) {
        logger.info("[PembayaranUtangPiutangAction.deleteDetailPembayaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PembayaranUtangPiutangDetail> piutangDetailList = (List<PembayaranUtangPiutangDetail>) session.getAttribute("listOfResultPembayaranDetail");
        List<PembayaranUtangPiutangDetail> piutangDetailArrayList = new ArrayList<>();
        for (PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail:piutangDetailList){
            if (pembayaranUtangPiutangDetail.getNoNota().equalsIgnoreCase(noNota)&&pembayaranUtangPiutangDetail.getRekeningId().equalsIgnoreCase(rekeningId)&&
            pembayaranUtangPiutangDetail.getMasterId().equalsIgnoreCase(vendor)&&pembayaranUtangPiutangDetail.getStJumlahPembayaran().equalsIgnoreCase(biaya)&&pembayaranUtangPiutangDetail.getDivisiId().equalsIgnoreCase(divisiId)){
            }else{
                piutangDetailArrayList.add(pembayaranUtangPiutangDetail);
            }
        }
        session.setAttribute("listOfResultPembayaranDetail",piutangDetailArrayList);
        logger.info("[PembayaranUtangPiutangAction.deleteDetailPembayaran] end process >>>");
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
            BigDecimal totalBayar = BigDecimal.valueOf(Double.valueOf(bayar.replace(".","").replace(",","")));
            BigDecimal bayarDetail = BigDecimal.ZERO;
            for (PembayaranUtangPiutangDetail data : pembayaranUtangPiutangDetailList){
                bayarDetail= bayarDetail.add( new BigDecimal(data.getStJumlahPembayaran().replace(".","").replace(",","")));
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

    public List<PembayaranUtangPiutangDetail> searchPengajuanBiaya(String branchId) {
        logger.info("[PembayaranUtangPiutangAction.searchPengajuanBiaya] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<PembayaranUtangPiutangDetail> listDataDb = pembayaranUtangPiutangBo.searchPengajuanBiaya(branchId);
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
        logger.info("[PembayaranUtangPiutangAction.searchPengajuanBiaya] end process >>>");
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
            data.setPembayaranUtangPiutangId(pembayaranId);
            data.setRegisteredDate(updateTime);
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
        }else if ("KM".equalsIgnoreCase(tipeTransaksi)){
            return "print_report_bukti_posting_masuk";
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
        List<Lampiran> lampiranList = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(itemId, itemFlag);
            if (modalPopUp!=null){
                listDetail = pembayaranUtangPiutangBo.getDetailPembayaran(pembayaranId);
                lampiranList = pembayaranUtangPiutangBo.getLampiranList(pembayaranId);
                session.setAttribute("listPembayaranDetailModal",listDetail);
                session.setAttribute("listOfResultLampiran",lampiranList);
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

    public EfakturDTO generateQrEfaktur(String url){
        EfakturDTO efakturDTO = new EfakturDTO();
        try {
            System.out.println(url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("resValidateFakturPm");
            if (errNodes.getLength() > 0) {
                Element err = (Element)errNodes.item(0);
                efakturDTO.setKdJenisTransaksi(err.getElementsByTagName("kdJenisTransaksi").item(0).getTextContent());
                efakturDTO.setFgPengganti(err.getElementsByTagName("fgPengganti").item(0).getTextContent());
                efakturDTO.setNomorFaktur(err.getElementsByTagName("nomorFaktur").item(0).getTextContent());
                efakturDTO.setTanggalFaktur(err.getElementsByTagName("tanggalFaktur").item(0).getTextContent());
                efakturDTO.setNpwpPenjual(err.getElementsByTagName("npwpPenjual").item(0).getTextContent());
                efakturDTO.setNamaPenjual(err.getElementsByTagName("namaPenjual").item(0).getTextContent());
                efakturDTO.setAlamatPenjual(err.getElementsByTagName("alamatPenjual").item(0).getTextContent());
                efakturDTO.setNpwpLawanTransaksi(err.getElementsByTagName("npwpLawanTransaksi").item(0).getTextContent());
                efakturDTO.setNamaLawanTransaksi(err.getElementsByTagName("namaLawanTransaksi").item(0).getTextContent());
                efakturDTO.setAlamatLawanTransaksi(err.getElementsByTagName("alamatLawanTransaksi").item(0).getTextContent());
                efakturDTO.setJumlahDpp(err.getElementsByTagName("jumlahDpp").item(0).getTextContent());
                efakturDTO.setJumlahPpn(err.getElementsByTagName("jumlahPpn").item(0).getTextContent());
                efakturDTO.setJumlahPpnBm(err.getElementsByTagName("jumlahPpnBm").item(0).getTextContent());
                efakturDTO.setStatusApproval(err.getElementsByTagName("statusApproval").item(0).getTextContent());
                efakturDTO.setStatusFaktur(err.getElementsByTagName("statusFaktur").item(0).getTextContent());
                efakturDTO.setReferensi(err.getElementsByTagName("referensi").item(0).getTextContent());

                //mengambil data company
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                CompanyBo companyBo= (CompanyBo) ctx.getBean("companyBoProxy");
                String companyId = CommonUtil.companyIdLogin();
                Company company = companyBo.getById(companyId);
                efakturDTO.setNpwpPerusahaan(company.getNpwp());
            } else {
                // success
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return efakturDTO;
    }

    public String approvePembayaran(String pembayaranId,String who,String flag){
        logger.info("[PembayaranUtangPiutangAction.approvePembayaran] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PembayaranUtangPiutangBo pembayaranUtangPiutangBo = (PembayaranUtangPiutangBo) ctx.getBean("pembayaranUtangPiutangBoProxy");
            PembayaranUtangPiutang data = new PembayaranUtangPiutang();
            String userLogin = CommonUtil.userLogin();
            String userIdLogin = CommonUtil.userIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setPembayaranUtangPiutangId(pembayaranId);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");

            if ("keu".equalsIgnoreCase(who)){
                data.setApprovalKeuanganFlag(flag);
                data.setApprovalKeuanganId(userIdLogin);
                data.setApprovalKeuanganName(userLogin);
                data.setApprovalKeuanganDate(updateTime);
            }else if ("kasub".equalsIgnoreCase(who)){
                data.setApprovalKasubKeuanganFlag(flag);
                data.setApprovalKasubKeuanganId(userIdLogin);
                data.setApprovalKasubKeuanganName(userLogin);
                data.setApprovalKasubKeuanganDate(updateTime);
            }

            pembayaranUtangPiutangBo.approvePembayaran(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pembayaranUtangPiutangBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.approvePembayaran");
            } catch (GeneralBOException e1) {
                logger.error("[PembayaranUtangPiutangAction.approvePembayaran] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PembayaranUtangPiutangAction.approvePembayaran] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PembayaranUtangPiutangAction.approvePembayaran] end process <<<");

        if ("Y".equalsIgnoreCase(flag)){
            return "Sukses Approve";
        }else{
            return "Sukses Not Approve";
        }
    }

    public String saveSessionLampiran(String namaLampiran , String fileUpload) {
        logger.info("[PembayaranUtangPiutangAction.saveSessionLampiran] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        if (lampiranList==null){
            lampiranList = new ArrayList<>();
        }

        Lampiran newData = new Lampiran();
        newData.setNamaLampiran(namaLampiran);
        newData.setUploadFile(fileUpload);
        lampiranList.add(newData);
        session.setAttribute("listOfResultLampiran",lampiranList);

        logger.info("[PembayaranUtangPiutangAction.saveSessionLampiran] end process >>>");
        return status;
    }

    public String deleteSessionLampiran(String namaLampiran){
        logger.info("[PembayaranUtangPiutangAction.deleteSessionLampiran] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");
        List<Lampiran> lampirans = new ArrayList<>();
        for (Lampiran data : lampiranList){
            if (!namaLampiran.equalsIgnoreCase(data.getNamaLampiran())){
                lampirans.add(data);
            }
        }

        session.setAttribute("listOfResultLampiran",lampirans);

        logger.info("[PembayaranUtangPiutangAction.deleteSessionLampiran] end process >>>");
        return status;
    }

    public String loadImageSessionLaporan(String namaLampiran){
        logger.info("[PembayaranUtangPiutangAction.loadImageSessionLaporan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        String images="";
        for (Lampiran lampiran : lampiranList){
            if (namaLampiran.equalsIgnoreCase(lampiran.getNamaLampiran())){
                images = lampiran.getUploadFile();
            }
        }

        logger.info("[PembayaranUtangPiutangAction.loadImageSessionLaporan] end process >>>");
        return images;
    }

    public List<Lampiran> loadSessionLampiran(){
        logger.info("[PembayaranUtangPiutangAction.loadSessionLampiran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        logger.info("[PembayaranUtangPiutangAction.loadSessionLampiran] end process >>>");
        return lampiranList;
    }

    public PembayaranUtangPiutang getViewApproval(String pembayaranId,String who) {
        logger.info("[PembayaranUtangPiutangAction.getViewApproval] start process >>>");
        String itemFlag = "Y";
        PembayaranUtangPiutang modalApproval;
        try {
            modalApproval = init(pembayaranId, itemFlag);
        } catch (GeneralBOException e) {
            throw new GeneralBOException("Error saat mengambil data approval ");
        }
        return modalApproval;
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
