package com.neurix.akuntansi.transaksi.laporanAkuntansi.action;

//import com.neurix.authorization.company.bo.AreaBo;
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

    public String searchReportIkhtisarSubBukuBesar() {
        logger.info("[LaporanAkuntansiAction.searchReportIkhtisarSubBukuBesar] start process >>>");

        return "search_laporan_ikhitisar_sub_buku_besar";
    }
    public String searchReportAging() {
        logger.info("[LaporanAkuntansiAction.searchReportAging] start process >>>");

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

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+CommonConstant.RS01);
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

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+CommonConstant.RS01);
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

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+CommonConstant.RS01);
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

        if (data.getTipeLaporan().equalsIgnoreCase("hutang_usaha")){
            titleReport="IKHTISAR HUTANG USAHA";
            reportId="RPT04";
        }

        reportParams.put("reportTitle", titleReport);
        reportParams.put("reportId", reportId);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+CommonConstant.RS01);
        reportParams.put("cabangId", data.getUnit());
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
        return "print_report_akuntansi_ikhitisar_sub_buku_besar";
    }

    public String printReportAging(){
        logger.info("[LaporanAkuntansiAction.printReportAging] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LaporanAkuntansiBo laporanAkuntansiBo = (LaporanAkuntansiBo) ctx.getBean("laporanAkuntansiBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        LaporanAkuntansi data = getLaporanAkuntansi();
        LaporanAkuntansi dataAtasan = laporanAkuntansiBo.getNipDanNamaManagerKeuanganDanGeneralManager(data.getUnit());
        Branch branch = branchBo.getBranchById(data.getUnit(),"Y");

        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT+CommonConstant.RS01);
        reportParams.put("cabangId", data.getUnit());
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
