package com.neurix.akuntansi.transaksi.laporanAkuntansi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.LaporanAkuntansi;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LaporanAkuntansiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiAction.class);
    private LaporanAkuntansiBo laporanAkuntansiBoProxy;
    private LaporanAkuntansi laporanAkuntansi;
    private String tipeLaporan;
    private JRBeanCollectionDataSource dataPrint;
    List<Aging> myList = new ArrayList<>() ;

    public List<Aging> getMyList() {
        return myList;
    }

    public void setMyList(List<Aging> myList) {
        this.myList = myList;
    }

    public JRBeanCollectionDataSource getDataPrint() {
        return dataPrint;
    }

    public void setDataPrint(JRBeanCollectionDataSource dataPrint) {
        this.dataPrint = dataPrint;
    }

    public String getTipeLaporan() {
        return tipeLaporan;
    }

    public void setTipeLaporan(String tipeLaporan) {
        this.tipeLaporan = tipeLaporan;
    }

    private List<LaporanAkuntansi> listOfComboLaporanAkuntansi = new ArrayList<LaporanAkuntansi>();

    public List<LaporanAkuntansi> getListOfComboLaporanAkuntansi() {
        return listOfComboLaporanAkuntansi;
    }

    public void setListOfComboLaporanAkuntansi(List<LaporanAkuntansi> listOfComboLaporanAkuntansi) {
        this.listOfComboLaporanAkuntansi = listOfComboLaporanAkuntansi;
    }

    private List<LaporanAkuntansi> listComboLaporanAkuntansi = new ArrayList<LaporanAkuntansi>();

    public List<LaporanAkuntansi> getListComboLaporanAkuntansi() {
        return listComboLaporanAkuntansi;
    }

    public void setListComboLaporanAkuntansi(List<LaporanAkuntansi> listComboLaporanAkuntansi) {
        this.listComboLaporanAkuntansi = listComboLaporanAkuntansi;
    }

    public LaporanAkuntansiBo getLaporanAkuntansiBoProxy() {
        return laporanAkuntansiBoProxy;
    }

    public void setLaporanAkuntansiBoProxy(LaporanAkuntansiBo laporanAkuntansiBoProxy) {
        this.laporanAkuntansiBoProxy = laporanAkuntansiBoProxy;
    }

    public LaporanAkuntansi getLaporanAkuntansi() {
        return laporanAkuntansi;
    }

    public void setLaporanAkuntansi(LaporanAkuntansi laporanAkuntansi) {
        this.laporanAkuntansi = laporanAkuntansi;
    }

    private List<LaporanAkuntansi> initComboLaporanAkuntansi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanAkuntansiAction.logger = logger;
    }


    public List<LaporanAkuntansi> getInitComboLaporanAkuntansi() {
        return initComboLaporanAkuntansi;
    }

    public void setInitComboLaporanAkuntansi(List<LaporanAkuntansi> initComboLaporanAkuntansi) {
        this.initComboLaporanAkuntansi = initComboLaporanAkuntansi;
    }

    public LaporanAkuntansi init(String kode, String flag){
       /* logger.info("[LaporanAkuntansiAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<LaporanAkuntansi> listOfResult = (List<LaporanAkuntansi>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (LaporanAkuntansi laporanAkuntansi: listOfResult) {
                    if(kode.equalsIgnoreCase(laporanAkuntansi.getLaporanAkuntansiId()) && flag.equalsIgnoreCase(laporanAkuntansi.getFlag())){
                        setLaporanAkuntansi(laporanAkuntansi);
                        break;
                    }
                }
            } else {
                setLaporanAkuntansi(new LaporanAkuntansi());
            }

            logger.info("[LaporanAkuntansiAction.init] end process >>>");
        }*/
        return getLaporanAkuntansi();
    }

    @Override
    public String add() {
        logger.info("[LaporanAkuntansiAction.add] start process >>>");
        LaporanAkuntansi addLaporanAkuntansi = new LaporanAkuntansi();
        setLaporanAkuntansi(addLaporanAkuntansi);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LaporanAkuntansiAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[LaporanAkuntansiAction.edit] start process >>>");

        return "init_edit";
    }

    @Override
    public String delete() {


        logger.info("[LaporanAkuntansiAction.delete] end process <<<");

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

    public String saveLaporanAkuntansi(){
        logger.info("[LaporanAkuntansiAction.saveAdd] start process >>>");
        try {
            LaporanAkuntansi laporanAkuntansi = getLaporanAkuntansi();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[laporanAkuntansiAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            //logger.error("[laporanAkuntansiAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            //addActionError("Error, mohon periksa inputan anda kembali");
            laporanAkuntansi.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
            //addActionMessage("Error, mohon periksa inputan anda kembali");
            return "input";
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfLaporanAkuntansi");

        return INPUT;
    }

    public String searchLaporanAkuntansi() {
        logger.info("[LaporanAkuntansiAction.search] start process >>>");

        LaporanAkuntansi searchLaporanAkuntansi = new LaporanAkuntansi();
        searchLaporanAkuntansi.setFlag("Y");
        List<LaporanAkuntansi> listOfSearchLaporanAkuntansi = new ArrayList();
        try {
            listOfSearchLaporanAkuntansi = laporanAkuntansiBoProxy.getByCriteria(searchLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "laporanAkuntansiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[LaporanAkuntansiAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboLaporanAkuntansi.addAll(listOfSearchLaporanAkuntansi);
        return SUCCESS;
    }

    @Override
    public String search() {
        logger.info("[LaporanAkuntansiAction.search] start process >>>");

        LaporanAkuntansi searchLaporanAkuntansi = getLaporanAkuntansi();
        List<LaporanAkuntansi> listOfsearchLaporanAkuntansi = new ArrayList();

        try {
            listOfsearchLaporanAkuntansi = laporanAkuntansiBoProxy.getByCriteria(searchLaporanAkuntansi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBoProxy.saveErrorMessage(e.getMessage(), "LaporanAkuntansiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LaporanAkuntansiAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );

            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLaporanAkuntansi);

        logger.info("[LaporanAkuntansiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LaporanAkuntansiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LaporanAkuntansiAction.initForm] end process >>>");
        return INPUT;
    }

    public String searchReportNeracaSaldo() {
        logger.info("[LaporanAkuntansiAction.searchReportNeracaSaldo] start process >>>");

        return "search_laporan_neraca_saldo";
    }

    public String searchReportNeracaMutasi() {
        logger.info("[LaporanAkuntansiAction.searchReportNeracaMutasi] start process >>>");

        return "search_laporan_neraca_mutasi";
    }

    public String searchReportIkhtisarBukuBesar() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhitisarBukuBesar] start process >>>");

        return "search_laporan_ikhitisar_buku_besar";
    }

    public String searchReportKartuBukuBesar() {
        logger.info("[LaporanAkuntansiAction.searchReportKartuBukuBesar] start process >>>");

        return "search_laporan_kartu_buku_besar";
    }

    public String searchReportMutasiJurnal() {
        logger.info("[LaporanAkuntansiAction.searchReportMutasiJurnal] start process >>>");

        return "search_laporan_mutasi_jurnal";
    }
    public String searchReportIkhtisarPendapatan() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhtisarPendapatan] start process >>>");

        return "search_laporan_ikhtisar_pendapatan";
    }
    public String searchReportIkhtisar() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhtisarSubBukuBesar] start process >>>");

        switch(laporanAkuntansi.getTipeLaporan()) {
            case "hutang_usaha":
                laporanAkuntansi.setTipeLaporanName("HUTANG USAHA");
                break;
            case "piutang_usaha":
                laporanAkuntansi.setTipeLaporanName("PIUTANG USAHA");
                break;
            case "piutang_pasien":
                laporanAkuntansi.setTipeLaporanName("PIUTANG PASIEN");
                break;
            case "uang_muka":
                laporanAkuntansi.setTipeLaporanName("UANG MUKA");
                break;
            case "uang_muka_p":
                laporanAkuntansi.setTipeLaporanName("UANG MUKA PASIEN");
                break;
            default:
                laporanAkuntansi.setTipeLaporanName("NOT FOUND");
        }
        return "search_laporan_ikhitisar_sub_buku_besar";
    }
    public String searchReportAging() {
        logger.info("[LaporanAkuntansiAction.searchReportAging] start process >>>");

        switch(laporanAkuntansi.getTipeLaporan()) {
            case "hutang_usaha":
                laporanAkuntansi.setTipeLaporanName("HUTANG USAHA");
                laporanAkuntansi.setTipePerson("usaha");
                break;
            case "piutang_usaha":
                laporanAkuntansi.setTipeLaporanName("PIUTANG USAHA");
                laporanAkuntansi.setTipePerson("usaha");
                break;
            case "uang_muka":
                laporanAkuntansi.setTipeLaporanName("UANG MUKA");
                laporanAkuntansi.setTipePerson("usaha");
                break;
            case "piutang_pasien":
                laporanAkuntansi.setTipeLaporanName("PIUTANG PASIEN");
                laporanAkuntansi.setTipePerson("pasien");
                break;
            case "uang_muka_p":
                laporanAkuntansi.setTipeLaporanName("UANG MUKA PASIEN");
                laporanAkuntansi.setTipePerson("pasien");
                break;
            default:
                laporanAkuntansi.setTipeLaporanName("NOT FOUND");
        }
        return "search_laporan_aging";
    }

    public String printReportNeracaSaldo(){
        logger.info("[LaporanAkuntansiAction.printReportNeracaSaldo] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaSaldo");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportNeracaSaldo] end process <<<");
        return "print_report_akuntansi_neraca_saldo";
    }
    public String printReportNeracaMutasi(){
        logger.info("[LaporanAkuntansiAction.printReportNeracaMutasi] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaMutasi");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaMutasi] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaMutasi] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportNeracaMutasi] end process <<<");
        return "print_report_akuntansi_neraca_mutasi";
    }

    public String printReportIkhtisarBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarBukuBesar] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportNeracaSaldo");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportNeracaSaldo] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarBukuBesar] end process <<<");
        return "print_report_akuntansi_ikhitisar_buku_besar";
    }

    public String printReportKartuBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportKartuBukuBesar] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("kode_rekening",data.getKodeRekening());
        reportParams.put("nomor_master",data.getMasterId());
        reportParams.put("branch_id",data.getUnit());
        reportParams.put("rekening_id",data.getRekeningId());
        reportParams.put("reportTitle", "KARTU BUKU BESAR PER BUKU BANTU");
        reportParams.put("areaId", CommonUtil.userAreaName());

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportKartuBukuBesar");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportKartuBukuBesar] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportKartuBukuBesar] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportKartuBukuBesar] end process <<<");
        return "print_report_akuntansi_kartu_buku_besar";
    }

    public String printReportMutasiJurnal(){
        logger.info("[LaporanAkuntansiAction.printReportMutasiJurnal] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";

        switch (data.getTipeJurnalId()){
            case ("JKM"):
                titleReport="MUTASI JURNAL KAS MASUK";
                break;
            case ("JKK"):
                titleReport="MUTASI JURNAL KAS KELUAR";
                break;
            case ("JPD"):
                titleReport="MUTASI JURNAL PERSEDIAAN";
                break;
            case ("JRI"):
                titleReport="MUTASI JURNAL RAWAT INAP";
                break;
            case ("JRJ"):
                titleReport="MUTASI JURNAL RAWAT JALAN";
                break;
            case ("JJO"):
                titleReport="MUTASI JURNAL JUAL OBAT";
                break;
                default:
                    titleReport="";
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("jurnalType",data.getTipeJurnalId());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportMutasiJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportMutasiJurnal] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportMutasiJurnal] end process <<<");
        return "print_report_mutasi_jurnal";
    }

    public String printReportIkhtisarSubBukuBesar(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="";
        String reportId="";
        String report="";

        switch (data.getTipeLaporan()){
            case ("hutang_usaha"):
                titleReport="IKHTISAR HUTANG USAHA";
                reportId="RPT04";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case("piutang_usaha"):
                titleReport="IKHTISAR PIUTANG USAHA";
                reportId="RPT05";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case ("uang_muka"):
                titleReport="IKHTISAR UANG MUKA";
                reportId="RPT06";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar";
                break;
            case ("uang_muka_p"):
                titleReport="IKHTISAR UANG MUKA PASIEN";
                reportId="RPT15";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar_pasien";
                break;
            case ("piutang_pasien"):
                titleReport="IKHTISAR PIUTANG PASIEN";
                reportId="RPT13";
                report="print_report_akuntansi_ikhitisar_sub_buku_besar_pasien";
                break;
                default:
                    titleReport="";
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportIkhtisarSubBukuBesar");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarSubBukuBesar] end process <<<");
        return report;
    }

    public String printReportIkhtisarPendapatan(){
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");
        String titleReport="IKHTISAR PENDAPATAN";
        String reportId="RPT12";

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
        reportParams.put("branchId", data.getUnit());
        reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(data.getBulan())+" "+data.getTahun());
        Date now = new Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
        reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
        reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
        reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
        reportParams.put("periode", data.getBulan()+"-"+data.getTahun());
        reportParams.put("kota",branch.getBranchName());
        reportParams.put("alamatSurat",branch.getAlamatSurat());
        reportParams.put("areaId",CommonUtil.userAreaName());
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportIkhtisarPendapatan");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] Error when downloading ,", e1);
            }
            logger.error("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[LaporanAkuntansiAction.printReportIkhtisarPendapatan] end process <<<");
        return "print_report_akuntansi_ikhitisar_pendapatan";
    }

    public String printReportAging(){
        logger.info("[LaporanAkuntansiAction.printReportAging] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi dataLaporan = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(dataLaporan.getUnit());
        Branch branch = branchBo.getBranchById(dataLaporan.getUnit(),"Y");
        String periode =dataLaporan.getBulan()+"-"+dataLaporan.getTahun();
        String titleReport="";
        String reportId="";
        String tipeAging ="";

        switch (dataLaporan.getTipeLaporan()){
            case("hutang_usaha") :
                titleReport="AGING HUTANG USAHA";
                reportId="RPT07";
                tipeAging ="usaha";
                break;
            case ("piutang_usaha"):
                titleReport="AGING PIUTANG USAHA";
                reportId="RPT08";
                tipeAging ="usaha";
                break;
            case ("uang_muka"):
                titleReport="AGING UANG MUKA";
                reportId="RPT09";
                tipeAging ="usaha";
                break;
            case ("uang_muka_p"):
                titleReport="AGING UANG MUKA PASIEN";
                reportId="RPT16";
                tipeAging ="pasien";
                break;
            case ("piutang_pasien"):
                titleReport="AGING PIUTANG PASIEN";
                reportId="RPT14";
                tipeAging ="pasien";
                break;
                default:
                    titleReport="";
        }

        List<Aging> agingList = laporanAkuntansiBo.getAging(dataLaporan.getUnit(),periode,dataLaporan.getMasterId(),tipeAging,reportId);
        List<Aging> listOfAgingTemp = new ArrayList<>();

        String []jumlahTanggalTahunKabisat = {"","31","29","31","30","31","30","31","31","30","31","30","31"};
        String []jumlahTanggalTahunBiasa = {"","31","28","31","30","31","30","31","31","30","31","30","31"};
        String jumlahTanggalTemp="";

        String periodeTitle[] = periode.split("-");

        if(Integer.parseInt(periodeTitle[0]) % 4 == 0){
            jumlahTanggalTemp=jumlahTanggalTahunKabisat[Integer.parseInt(periodeTitle[0])];
        }else{
            jumlahTanggalTemp=jumlahTanggalTahunBiasa[Integer.parseInt(periodeTitle[0])];
        }

        if(agingList.size()>0){
            Aging listAgingTempLast=null;
            Aging listAgingTempNewRecord=null;
            listOfAgingTemp=new ArrayList();

            for (Aging objMod : agingList){
                if(cekIsObjectIsDoubel(objMod,listOfAgingTemp)){
                    listAgingTempLast=getSameItem(objMod,listOfAgingTemp);

                    //Cache baru dibuka untuk menampung data aging yang baru
                    listAgingTempNewRecord = new Aging();
                    listAgingTempNewRecord.setNoNota(listAgingTempLast.getNoNota());
                    listAgingTempNewRecord.setTglJurnal(listAgingTempLast.getTglJurnal());
                    listAgingTempNewRecord.setKurs(objMod.getKurs());
                    listAgingTempNewRecord.setMataUang(listAgingTempLast.getMataUang());
                    //
                    listAgingTempNewRecord.setTotal(objMod.getTotal().add(listAgingTempLast.getTotal()));
                    listAgingTempNewRecord.setMasterId(listAgingTempLast.getMasterId());
                    listAgingTempNewRecord.setNamaMaster(listAgingTempLast.getNamaMaster());
                    listAgingTempNewRecord.setPeriode(getPeriode());

                    //hapus record yang lama
                    listOfAgingTemp.remove(listAgingTempLast);

                    //tambahkan record yang baru
                    listOfAgingTemp.add(listAgingTempNewRecord);
                }else{
                    listOfAgingTemp.add(objMod);
                }
            }
        }else{
            return ERROR;
        }

        if(listOfAgingTemp.size()>0){
            myList=new ArrayList();
            for(Aging data:listOfAgingTemp){
                data.setTotal(data.getTotal().abs());
                data.setJumlah1(new BigDecimal(0));
                data.setJumlah2(new BigDecimal(0));
                data.setJumlah3(new BigDecimal(0));
                data.setJumlah4(new BigDecimal(0));
                data.setJumlah5(new BigDecimal(0));
                data.setJumlah6(new BigDecimal(0));
                data.setJumlah7(new BigDecimal(0));

                //Tentukan posisi umur aging.
                //Yaitu dengan mengammbil periode sedang berjalan (tanggal aging di cetak) dan periode tanggal dari jurnal tersebut
                int agingPosition=0;
                String periodeJurnal =CommonUtil.convertDateToString(data.getTglJurnal());
                String periodeJurnalArrTemp[]= periodeJurnal.split("-");

                Integer tglCetak = Integer.parseInt(jumlahTanggalTemp);
                Integer tglJurnal =Integer.parseInt(periodeJurnalArrTemp[0]);
                Integer blnCetak = Integer.parseInt(periodeTitle[0]);
                Integer blnJurnal =Integer.parseInt(periodeJurnalArrTemp[1]);
                Integer thnCetak = Integer.parseInt(periodeTitle[1]);
                Integer thnJurnal =Integer.parseInt(periodeJurnalArrTemp[2]);

                if(thnCetak > thnJurnal){
                    agingPosition=(((thnCetak-thnJurnal)*12)-blnJurnal)+blnCetak;
                    if(tglCetak > tglJurnal) agingPosition+=1;
                }else{
                    agingPosition=blnCetak-blnJurnal;
                    if(tglCetak > tglJurnal) agingPosition+=1;
                }

                //umur 0 s/d 1 bulan
                if(agingPosition<=1){
                    data.setJumlah1(data.getTotal().abs());
                }
                //umur 1 s/d 2 bulan
                else if(agingPosition<=2 && agingPosition>1){
                    data.setJumlah2(data.getTotal().abs());
                }
                //umur 2 s/d 3 bulan
                else if(agingPosition<=3 && agingPosition>2){
                    data.setJumlah3(data.getTotal().abs());
                }
                //umur 3 s/d 6 bulan
                else if(agingPosition<=6 && agingPosition>3){
                    data.setJumlah4(data.getTotal().abs());
                }
                //umur 6 s/d 12 bulan
                else if(agingPosition<=12 && agingPosition>6){
                    data.setJumlah5(data.getTotal().abs());
                }
                //umur 1 s/d 2 tahun
                else if(agingPosition<=24 && agingPosition>12){
                    data.setJumlah6(data.getTotal().abs());
                }
                //umur > 2 tahun
                else{
                    data.setJumlah7(data.getTotal().abs());
                }

                data.setStrKurs(getMataUangKurs("033"));
                if(data.getTotal().doubleValue()!=0){
                    myList.add(data);
                }
            }
        }else{
            return ERROR;
        }

        //JIka list yang akan dicetak di report tidak ada, maka tidak usah dilanjutkan ke pencetakan report
        if(myList.size()>0){
            List<Aging> forReport = new ArrayList<>();

            myList.addAll(forReport);
            reportParams.put("reportTitle", titleReport);
            reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
            reportParams.put("cabangId", dataLaporan.getUnit());
            reportParams.put("periodeTitle", CommonUtil.convertNumberToStringBulan(dataLaporan.getBulan())+" "+dataLaporan.getTahun());
            Date now = new Date();
            reportParams.put("tanggal", CommonUtil.convertDateToString(now));
            reportParams.put("namaGeneralManager", dataAtasan.getNamaGeneralManager());
            reportParams.put("nipGeneralManager", dataAtasan.getNipGeneralManager());
            reportParams.put("namaManagerKeuangan", dataAtasan.getNamaManagerKeuangan());
            reportParams.put("nipManagerKeuangan", dataAtasan.getNipManagerKeuangan());
            reportParams.put("periode",periode);
            reportParams.put("kota",branch.getBranchName());
            reportParams.put("alamatSurat",branch.getAlamatSurat());
            reportParams.put("areaId",CommonUtil.userAreaName());
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = laporanAkuntansiBo.saveErrorMessage(e.getMessage(), "printReportAging");
                } catch (GeneralBOException e1) {
                    logger.error("[LaporanAkuntansiAction.printReportAging] Error when downloading ,", e1);
                }
                logger.error("[LaporanAkuntansiAction.printReportAging] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            }
            logger.info("[LaporanAkuntansiAction.printReportAging] end process <<<");
            return "print_report_akuntansi_aging";
        }else{
            return "print_report_akuntansi_aging";
        }
    }

    public boolean cekIsObjectIsDoubel(Aging data, List<Aging> listData){
        //cek ada yg no nota sama ato tidak, jika ada yg sama return true, else false
        boolean flag = false;
        Aging aging=null;
        for (int i = 0; i < listData.size() && !flag; i++){
            aging = listData.get(i);
            if (aging.getNoNota().equalsIgnoreCase(data.getNoNota()) && data.getMataUang().equalsIgnoreCase(aging.getMataUang())){
                flag = true;
            }
        }
        return flag;
    }

    private Aging getSameItem(Aging record, List<Aging> listData){
        boolean flag = false;
        Aging dataReturn = null;
        for (int i = 0; i < listData.size() && !flag; i++){
            dataReturn = listData.get(i);
            if (dataReturn.getNoNota().equalsIgnoreCase(record.getNoNota())&& dataReturn.getMataUang().equalsIgnoreCase(record.getMataUang())){
                flag = true;
            }
        }
        return dataReturn;
    }
    private String getMataUangKurs(String mataUangId) {
        return "0";
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
