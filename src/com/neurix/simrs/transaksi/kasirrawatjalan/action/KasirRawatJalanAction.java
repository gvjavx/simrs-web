package com.neurix.simrs.transaksi.kasirrawatjalan.action;

import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.Fpk;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.RiwayatTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import javax.xml.soap.Detail;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KasirRawatJalanAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KasirRawatJalanAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private RiwayatTindakanBo riwayatTindakanBoProxy;
    private KasirRawatJalanBo kasirRawatJalanBoProxy;
    private TransaksiObatBo transaksiObatBoProxy;
    private String id;
    private String idDetailCheckup;
    private BillingSystemBo billingSystemBoProxy;
    private BranchBo branchBoProxy;
    private String jenis;
    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }

    public void setKasirRawatJalanBoProxy(KasirRawatJalanBo kasirRawatJalanBoProxy) {
        this.kasirRawatJalanBoProxy = kasirRawatJalanBoProxy;
    }

    public void setRiwayatTindakanBoProxy(RiwayatTindakanBo riwayatTindakanBoProxy) {
        this.riwayatTindakanBoProxy = riwayatTindakanBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public String add() {
        return null;
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
        logger.info("[KasirRawatJalanAction.search] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());
        headerDetailCheckup.setTypeTransaction("kasir");
        headerDetailCheckup.setNotLike("bpjs");

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KasirRawatJalanAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[KasirRawatJalanAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[KasirRawatJalanAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        HeaderDetailCheckup checkupdetail = new HeaderDetailCheckup();
        checkupdetail.setStDateFrom(tglToday);
        checkupdetail.setStDateTo(tglToday);

        setHeaderDetailCheckup(checkupdetail);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KasirRawatJalanAction.initForm] end process <<<");
        return "search";
    }

    public List<RiwayatTindakan> getListTindakanRawat(String idDetail) {
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {
            List<RiwayatTindakan> result = new ArrayList<>();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

            RiwayatTindakan tindakanRawat = new RiwayatTindakan();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setBranchId(CommonUtil.userBranchLogin());

            try {
                riwayatTindakanList = kasirRawatJalanBo.getListAllTindakan(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorAction.getListTindakanRawat] Error when get data tindakan rawat ", e);
            }
        }
        return riwayatTindakanList;
    }

    public List<TransaksiObatDetail> getListDetailResep(String idResep) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdPermintaanResep(idResep);
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();

        if (idResep != null && !"".equalsIgnoreCase(idResep)) {
            try {
                obatDetailList = transaksiObatBo.getSearchObatTransaksiByCriteria(transaksiObatDetail);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
            }
        }

        return obatDetailList;
    }

    public String printInvoice() {

        HeaderCheckup checkup = new HeaderCheckup();
        String id = getId();
        String jk = "";
        String jenisPasien = getJenis();

        String logo = "";
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
        List<UangMuka> uangMukaList = new ArrayList<>();

        if (id != null && !"".equalsIgnoreCase(id)) {

            String branch = CommonUtil.userBranchLogin();
            String unit = CommonUtil.userBranchNameLogin();
            String area = CommonUtil.userAreaName();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                Branch branches = new Branch();

                try {
                    branches = branchBoProxy.getBranchById(branch, "Y");
                } catch (GeneralBOException e) {
                    logger.error("Found Error when searhc branch logo");
                }

                if (branches != null) {
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
                }


                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                riwayatTindakan.setIdDetailCheckup(checkup.getIdDetailCheckup());
                riwayatTindakan.setBranchId(CommonUtil.userBranchLogin());

                try {
                    riwayatTindakanList = kasirRawatJalanBoProxy.getListAllTindakan(riwayatTindakan);
                } catch (GeneralBOException e) {
                    logger.error("Foun error when search riwayat tindakan " + e.getMessage());
                }

                RiwayatTindakan result = new RiwayatTindakan();
                List<TransaksiObatDetail> resultListObat = new ArrayList<>();

                if (riwayatTindakanList.size() > 0) {
                    result = riwayatTindakanList.get(0);

                    for (RiwayatTindakan riwayat : riwayatTindakanList) {
                        if ("resep".equalsIgnoreCase(riwayat.getKeterangan())) {
                            TransaksiObatDetail detail = new TransaksiObatDetail();
                            detail.setIdPermintaanResep(riwayat.getIdTindakan());

                            try {
                                obatDetailList = transaksiObatBoProxy.getSearchObatTransaksiByCriteria(detail);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                            }

                            resultListObat.addAll(obatDetailList);
                        }
                    }
                }

                UangMuka uangMuka = new UangMuka();
                uangMuka.setIdDetailCheckup(checkup.getIdDetailCheckup());
                uangMuka.setStatusBayar("Y");

                try {
                    uangMukaList = kasirRawatJalanBoProxy.getListUangMuka(uangMuka);
                } catch (GeneralBOException e) {
                    logger.error("Foun error when search riwayat tindakan " + e.getMessage());
                }


                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(riwayatTindakanList);
                JRBeanCollectionDataSource itemDataObat = new JRBeanCollectionDataSource(resultListObat);
                JRBeanCollectionDataSource itemDataUangMuka = new JRBeanCollectionDataSource(uangMukaList);

                BigDecimal tarifJasa = hitungTotalJasa(riwayatTindakanList);
                BigInteger tarifUangMuka = hitungTotalUangMuka(uangMukaList);
                BigInteger tarifObat = hitungTotalObat(obatDetailList);
                BigDecimal ppnObat = new BigDecimal(String.valueOf(0));
                ppnObat = new BigDecimal(tarifObat).multiply(new BigDecimal(0.1)).setScale(2, RoundingMode.HALF_UP);

                BigDecimal totalJasa = new BigDecimal(String.valueOf(0));
                totalJasa = (tarifJasa.subtract(new BigDecimal(tarifUangMuka))).add(ppnObat);
                String terbilang = angkaToTerbilang(totalJasa.longValue());

                reportParams.put("invoice", checkup.getInvoice());
                reportParams.put("idPasien", checkup.getIdPasien());
                reportParams.put("petugas", CommonUtil.userLogin());
                reportParams.put("title", "Invoice Rawat Jalan Pasien");
                reportParams.put("itemDataSource", itemData);
                reportParams.put("listObatDetail", itemDataObat);
                reportParams.put("listUangMuka", itemDataUangMuka);
                reportParams.put("totalJasa", totalJasa);
                reportParams.put("terbilang", terbilang);
                reportParams.put("unit", unit);
                reportParams.put("area", area);
                reportParams.put("idDetailCheckup", checkup.getIdDetailCheckup());
                reportParams.put("noCheckup", checkup.getNoCheckup());
                reportParams.put("logo", logo);
                reportParams.put("nik", checkup.getNoKtp());
                reportParams.put("nama", checkup.getNama());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);

                if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Laki-Laki";
                } else {
                    jk = "Perempuan";
                }
                reportParams.put("jenisKelamin", jk);
                reportParams.put("jenisPasien", checkup.getStatusPeriksaName());
                reportParams.put("poli", checkup.getNamaPelayanan());
                reportParams.put("provinsi", checkup.getNamaProvinsi());
                reportParams.put("kabupaten", checkup.getNamaKota());
                reportParams.put("kecamatan", checkup.getNamaKecamatan());
                reportParams.put("desa", checkup.getNamaDesa());


                try {
                    preDownload();
                } catch (SQLException e) {
                    logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                    return "search";
                }
            }
        }

        if ("bpjs".equalsIgnoreCase(jenisPasien)) {
            return "print_invoice_bpjs";
        } else {
            return "print_invoice_umum";
        }

    }

    private BigDecimal hitungTotalJasa(List<RiwayatTindakan> riwayatTindakanList) {

        BigDecimal total = new BigDecimal(String.valueOf("0"));
        if (riwayatTindakanList != null && riwayatTindakanList.size() > 0) {
            for (RiwayatTindakan trans : riwayatTindakanList) {
                total = total.add(trans.getTotalTarif());
            }
        }
        return total;
    }

    private BigInteger hitungTotalUangMuka(List<UangMuka> uangMukaList) {

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (uangMukaList != null && uangMukaList.size() > 0) {
            for (UangMuka trans : uangMukaList) {
                total = total.add(trans.getDibayar());
            }
        }
        return total;
    }

    private BigInteger hitungTotalObat(List<TransaksiObatDetail> transaksiObatDetailList) {

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (transaksiObatDetailList != null && transaksiObatDetailList.size() > 0) {
            for (TransaksiObatDetail trans : transaksiObatDetailList) {
                total = total.add(trans.getTotalHarga());
            }
        }
        return total;
    }

    public String angkaToTerbilang(Long angka) {

        String[] huruf = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

        if (angka < 12) {
            return huruf[angka.intValue()];
        }

        if (angka >= 12 && angka <= 19) {
            return huruf[angka.intValue() % 10] + " Belas";
        }

        if (angka >= 20 && angka <= 99) {
            return angkaToTerbilang(angka / 10) + " Puluh " + huruf[angka.intValue() % 10];
        }

        if (angka >= 100 && angka <= 199) {
            return "Seratus " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 200 && angka <= 999) {
            return angkaToTerbilang(angka / 100) + " Ratus " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 1000 && angka <= 1999) {
            return "Seribu " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 2000 && angka <= 999999) {
            return angkaToTerbilang(angka / 1000) + " Ribu " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 1000000 && angka <= 999999999) {
            return angkaToTerbilang(angka / 1000000) + " Juta " + angkaToTerbilang(angka % 1000000);
        }
        if (angka >= 1000000000 && angka <= 999999999999L) {
            return angkaToTerbilang(angka / 1000000000) + " Milyar " + angkaToTerbilang(angka % 1000000000);
        }

        if (angka >= 1000000000000L && angka <= 999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000L) + " Triliun " + angkaToTerbilang(angka % 1000000000000L);
        }

        if (angka >= 1000000000000000L && angka <= 999999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000000L) + " Quadrilyun " + angkaToTerbilang(angka % 1000000000000000L);
        }

        return "";
    }


    public String printBuktiUangMuka() {

        String id = getId();
        String jk = "";

        List<UangMuka> uangMukaList = new ArrayList<>();
        HeaderCheckup checkup = new HeaderCheckup();

        if (id != null && !"".equalsIgnoreCase(id)) {

            String branch = CommonUtil.userBranchLogin();
            String unit = CommonUtil.userBranchNameLogin();
            String area = CommonUtil.userAreaName();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }

            if (checkup != null) {

                Branch branches = new Branch();

                try {
                    branches = branchBoProxy.getBranchById(branch, "Y");
                } catch (GeneralBOException e) {
                    logger.error("Found Error when searhc branch logo");
                }

                String logo = "";

                if (branches != null) {
                    logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
                }

                UangMuka uangMuka = new UangMuka();
                uangMuka.setIdDetailCheckup(checkup.getIdDetailCheckup());
                uangMuka.setStatusBayar("Y");

                try {
                    uangMukaList = kasirRawatJalanBoProxy.getListUangMuka(uangMuka);
                } catch (GeneralBOException e) {
                    logger.error("Foun error when search riwayat tindakan " + e.getMessage());
                }

                JRBeanCollectionDataSource itemData = new JRBeanCollectionDataSource(uangMukaList);

                reportParams.put("itemDataSource", itemData);
                reportParams.put("petugas", CommonUtil.userLogin());
                reportParams.put("idPasien", checkup.getIdPasien());
                reportParams.put("unit", unit);
                reportParams.put("area", area);
                reportParams.put("idDetailCheckup", checkup.getIdDetailCheckup());
                reportParams.put("noCheckup", checkup.getNoCheckup());
                reportParams.put("logo", logo);
                reportParams.put("nik", checkup.getNoKtp());
                reportParams.put("nama", checkup.getNama());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
                if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Laki-Laki";
                } else {
                    jk = "Perempuan";
                }
                reportParams.put("jenisKelamin", jk);
                reportParams.put("jenisPasien", checkup.getStatusPeriksaName());
                reportParams.put("poli", checkup.getNamaPelayanan());
                reportParams.put("provinsi", checkup.getNamaProvinsi());
                reportParams.put("kabupaten", checkup.getNamaKota());
                reportParams.put("kecamatan", checkup.getNamaKecamatan());
                reportParams.put("desa", checkup.getNamaDesa());


                try {
                    preDownload();
                } catch (SQLException e) {
                    logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                    return "search";
                }
            }
        }

        return "print_uang_muka";
    }


    public String searchUangMuka() {

        logger.info("[KasirRawatJalanAction.searchUangMuka] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getListUangPendaftaran(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KasirRawatJalanAction.searchUangMuka] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[KasirRawatJalanAction.searchUangMuka] end process <<<");
        return "search";

    }

    public List<UangMuka> getListUangMuka(String idDetailCheckup, String statusBayar) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");
        UangMuka uangMuka = new UangMuka();
        uangMuka.setIdDetailCheckup(idDetailCheckup);
        uangMuka.setStatusBayar(statusBayar);
        List<UangMuka> obatDetailList = new ArrayList<>();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                obatDetailList = kasirRawatJalanBo.getListUangMuka(uangMuka);
            } catch (GeneralBOException e) {
                logger.error("[TransaksiObatAction.searchResep] ERROR error when get searh resep. ", e);
                addActionError("[TransaksiObatAction.searchResep] ERROR error when get searh resep. " + e.getMessage());
            }
        }

        return obatDetailList;
    }

    public CrudResponse saveUangMuka(String id, String idPasien, String biaya, String jumlahDibayar, String metodeBayar, String kodeBank, String noRekening) {
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

        String transId = "04";
        String noNota = "";

        String branchId = CommonUtil.userBranchLogin();
        try {
            noNota = billingSystemBo.createInvoiceNumber(transId, branchId);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[KasirRawatJalanAction.saveUangMuka] ERROR " + e);
            return response;
        }

        Map hsCriteria = new HashMap();
        hsCriteria.put("master_id", idPasien);
        hsCriteria.put("metode_bayar", metodeBayar);
        hsCriteria.put("bank", kodeBank);

        if (!"".equalsIgnoreCase(noRekening)) {
            hsCriteria.put("nomor_rekening", noRekening);
        }

        Map mapUangMuka = new HashMap();
        mapUangMuka.put("bukti", id);
        mapUangMuka.put("nilai", new BigDecimal(jumlahDibayar));

        hsCriteria.put("uang_muka", mapUangMuka);
        hsCriteria.put("kas", new BigDecimal(jumlahDibayar));

        try {

            String catatan = "Uang Muka untuk No Pasien " + idPasien;
            if (!"".equalsIgnoreCase(noRekening)) {
                catatan = catatan + " No. Rekening " + noRekening;
            }

            String noJurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");

            UangMuka uangMuka = new UangMuka();
            uangMuka.setNoNota(noNota);
            uangMuka.setId(id);
            uangMuka.setDibayar(new BigInteger(jumlahDibayar));
            uangMuka.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            uangMuka.setLastUpdateWho(CommonUtil.userLogin());
            uangMuka.setNoJurnal(noJurnal);

            kasirRawatJalanBo.updateNotaUangMukaById(uangMuka);

            response.setStatus("success");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[KasirRawatJalanAction.saveUangMuka] ERROR " + e);
        }
        return response;
    }

    public CrudResponse savePembayaranTagihan(String jsonString, String idPasien, String noNota, String withObat, String idDetailCheckup, String metodeBayar, String kodeBank, String type, String jenis, String noRekening) throws JSONException {

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        String divisiId = "";
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null){
            try {
                ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())){
                    divisiId = pelayananEntity.getKodering();
                } else {

                    RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                    if (lastRuangan != null){
                        MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(lastRuangan.getIdRuang());
                        if (ruanganEntity != null){
                            ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                            if (kelasRuanganEntity != null){
                                divisiId = kelasRuanganEntity.getKodering();
                            }
                        }
                    }
                }
            } catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR " + e);
                return response;
            }
        } else {
            response.setStatus("error");
            response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR gagal mendapakatkan divisi_id atau data detail checkup");
            return response;
        }


        Map hsCriteria = new HashMap();

        // master_id
        if ("bpjs".equalsIgnoreCase(jenis)){
            hsCriteria.put("master_id", "02.018");
        } else {
            hsCriteria.put("master_id", "01.000");
        }

        hsCriteria.put("metode_bayar", metodeBayar);
        hsCriteria.put("bank", kodeBank);
        if (!"".equalsIgnoreCase(noRekening)) {
            hsCriteria.put("nomor_rekening", noRekening);
        }

        String branchId = CommonUtil.userBranchLogin();

        BigDecimal uangMuka = new BigDecimal(0);
        BigDecimal uangPiutang = new BigDecimal(0);
        BigDecimal uangPendapatan = new BigDecimal(0);
        BigDecimal ppnObat = new BigDecimal(0);

        // maping untuk parameter lainnua
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            if ("uang_muka".equalsIgnoreCase(obj.getString("type").toString())) {
                uangMuka = new BigDecimal(obj.getLong("nilai"));
            } else if ("piutang_pasien_non_bpjs".equalsIgnoreCase(obj.getString("type").toString())) {
                uangPiutang = new BigDecimal(obj.getLong("nilai"));
            } else if ("ppn_keluaran".equalsIgnoreCase(obj.getString("type").toString())) {
                ppnObat = new BigDecimal(obj.getLong("nilai"));
            } else {
                hsCriteria.put(obj.getString("type").toString(), new BigDecimal(obj.getLong("nilai")));
            }
        }

        String invoiceNumber = billingSystemBo.createInvoiceNumber(type, branchId);
        Map mapPajakObat = new HashMap();

        String ketTerangan = "";
        String transId = "";

        if ("tunai".equalsIgnoreCase(jenis)){
            hsCriteria.put("divisi_id", divisiId);

            if ("JRJ".equalsIgnoreCase(type) && !"Y".equalsIgnoreCase(withObat)) {
                transId = "12";
                ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai tanpa Obat ";
            }
            if ("JRJ".equalsIgnoreCase(type) && "Y".equalsIgnoreCase(withObat)) {

                mapPajakObat.put("bukti", invoiceNumber);
                mapPajakObat.put("nilai", ppnObat);
                hsCriteria.put("ppn_keluaran", mapPajakObat);

                transId = "15";
                ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai dengan Obat ";
            }
            if ("JRI".equalsIgnoreCase(type)) {
                transId = "22";
                ketTerangan = "Closing Pasien Rawat Inap Umum Tunai ";
            }
        }



        // jika piutang
        String invNumber = "";
        if ("non_tunai".equalsIgnoreCase(jenis) || "bpjs".equalsIgnoreCase(jenis)) {

            if (detailCheckupEntity != null && detailCheckupEntity.getInvoice() != null){
                invNumber = detailCheckupEntity.getInvoice();
            } else {
                response.setStatus("error");
                response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR gagal mendapakatkan no_invoice atau data detail checkup");
                return response;
            }

            // map piutang
            Map mapPiutang = new HashMap();
            mapPiutang.put("bukti", invNumber);
            mapPiutang.put("nilai", uangPiutang);

            if ("bpjs".equalsIgnoreCase(jenis)) {
                transId = "10";
                ketTerangan = "Pembayaran Piutang Pasien BPJS";
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);
            } else {
                transId = "02";
                ketTerangan = "Pembayaran Piutang Pasien Umum";
                hsCriteria.put("piutang_pasien_non_bpjs", mapPiutang);
            }

        } else {
            // jika bukan piutang maka pakai map uang muka
            Map mapUangMuka = new HashMap();
            mapUangMuka.put("bukti", noNota);
            mapUangMuka.put("nilai", uangMuka);

            hsCriteria.put("uang_muka", mapUangMuka);
        }

        if (!"".equalsIgnoreCase(transId)) {
            try {
                String text = "";
                if (("transfer").equalsIgnoreCase(metodeBayar)) {
                    text = " pada Bank " + kodeBank;
                }

                String catatan = ketTerangan + " untuk No Pasien " + idPasien + " menggunakan metode " + metodeBayar + text;
                if (!"".equalsIgnoreCase(noRekening)) {
                    catatan = catatan + " No. Rekening " + noRekening;
                }

                String noJurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");

                detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                detailCheckup.setLastUpdateWho(CommonUtil.userLogin());
                detailCheckup.setNoJurnal(noJurnal);

                checkupDetailBo.updateStatusBayarDetailCheckup(detailCheckup);
                response.setStatus("success");
            } catch (GeneralBOException e) {
                logger.error("[KasirRawatJalanAction.savePembayaranTagihan] ERROR ",e);
                response.setStatus("error");
                response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR " + e);
            }
        } else {
            response.setStatus("error");
            response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR Method Belum ada");
        }
        return response;
    }

    public String searchFPK() {
        logger.info("[KasirRawatJalanAction.searchFPK] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();
        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());

        try {
            listOfsearchHeaderDetailCheckup = kasirRawatJalanBoProxy.getSearchFPK(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KasirRawatJalanAction.searchFPK] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[KasirRawatJalanAction.search] end process <<<");
        return "search";
    }

    public String importCsv() {
        logger.info("[KasirRawatJalanAction.importCsv] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfImportCsv");

        logger.info("[KasirRawatJalanAction.importCsv] end process <<<");
        return "import_csv";
    }

    public String goToHasilCsv() {
        logger.info("[KasirRawatJalanAction.goToHasilCsv] start process >>>");

        logger.info("[KasirRawatJalanAction.goToHasilCsv] end process <<<");
        return "hasil_import_csv";
    }

    public String prosesImportCsv() {
        logger.info("[KasirRawatJalanAction.prosesImportCsv] start process >>>");
        List<HeaderDetailCheckup> listOfResult = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        if (this.fileUpload != null) {
            if ("application/vnd.ms-excel".equalsIgnoreCase(this.fileUploadContentType)) {
                if (this.fileUpload.length() <= 5242880 && this.fileUpload.length() > 0) {
                    // file name
                    String fileName = this.fileUploadFileName;
                    String fileNameReplace = fileName.replace(" ", "_");
                    String newFileName = "CSV_FPK" + "_" + dateFormater("MM") + dateFormater("yy") + "_" + fileNameReplace;
                    // deklarasi path file
                    String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_FPK;
                    logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                    // persiapan pemindahan file
                    File fileToCreate = new File(filePath, newFileName);

                    try {
                        // pemindahan file
                        FileUtils.copyFile(this.fileUpload, fileToCreate);
                        logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                    } catch (IOException e) {
                        logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        throw new GeneralBOException("Found Error when upload CSV " + e.getMessage());
                    }

                    BufferedReader br = null;
                    String line = "";
                    String cvsSplitBy = ",";
                    int x = 1;
                    try {
                        br = new BufferedReader(new FileReader(this.fileUpload));
                        while ((line = br.readLine()) != null) {
                            //melewatkan judul nomor 1
                            if (x!=1){
                                // use comma as separator
                                String[] data = line.split(cvsSplitBy);

                                if (data.length!=2){
                                    String status="ERROR : file CSV tidak sesuai";
                                    logger.error("[CheckupAction.uploadImages] "+status );
                                    throw new GeneralBOException(status);
                                }

                                //hasilnya
                                HeaderDetailCheckup result = new HeaderDetailCheckup();
                                result.setNoSep(data[0]);
                                result.setTotalBiayaDariBpjs(new BigInteger(data[1]));

                                //dicari di tabel
                                HeaderDetailCheckup search = new HeaderDetailCheckup();
                                search.setNoSep(data[0]);
                                List<ItSimrsHeaderDetailCheckupEntity> resultList = kasirRawatJalanBoProxy.getSearchCheckupBySep(data[0]);
                                for (ItSimrsHeaderDetailCheckupEntity headerDetailCheckup : resultList){
                                    result.setTotalBiaya(headerDetailCheckup.getTotalBiaya());
                                    result.setStatus(headerDetailCheckup.getStatusBayar());
                                    result.setIdDetailCheckup(headerDetailCheckup.getIdDetailCheckup());
                                }
                                String statusBayar;
                                if (("Y").equalsIgnoreCase(result.getStatus())){
                                    statusBayar="SB";
                                }else if (result.getTotalBiaya()==null){
                                    statusBayar="N";
                                }else if (result.getTotalBiayaDariBpjs().compareTo(result.getTotalBiaya())<0){
                                    statusBayar="KB";
                                }else if (result.getTotalBiaya().compareTo(result.getTotalBiayaDariBpjs())<0){
                                    statusBayar="LB";
                                }else if (result.getTotalBiaya().compareTo(result.getTotalBiayaDariBpjs())==0){
                                    statusBayar="P";
                                }else{
                                    statusBayar="UK";
                                }
                                result.setStatusBayar(statusBayar);

                                listOfResult.add(result);
                            }
                            x++;
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    String status = "ERROR : Ukuran file terlalu besar";
                    logger.error("[CheckupAction.uploadImages] " + status);
                    throw new GeneralBOException(status);
                }
            }else{
                String status = "ERROR : file yang diupload tidak sesuai ( Gunakan CSV )";
                logger.error("[CheckupAction.uploadImages] " + status);
                throw new GeneralBOException(status);
            }
        }else{
            String status = "ERROR : file yang diupload tidak ada";
            logger.error("[CheckupAction.uploadImages] " + status);
            throw new GeneralBOException(status);
        }

        session.removeAttribute("listOfImportCsv");
        session.setAttribute("listOfImportCsv", listOfResult);

        logger.info("[KasirRawatJalanAction.prosesImportCsv] end process <<<");
        return "hasil_import_csv";
    }

    public String saveImportCsv() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        HeaderDetailCheckup data = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listCsvImport = (List<HeaderDetailCheckup>) session.getAttribute("listOfImportCsv");
        List<Fpk> fpkList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        for (HeaderDetailCheckup detailCheckup : listCsvImport){
            Fpk fpk = new Fpk();
            fpk.setNoFpk(data.getNoFpk());
            fpk.setNoSep(detailCheckup.getNoSep());
            fpk.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
            fpk.setIdPasien(detailCheckup.getIdPasien());
            fpk.setNoSlip(data.getNoSlipBank());

            fpkList.add(fpk);
        }

        // create map jurnal
        List<Map> mapListKlaim = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (Fpk fpk : fpkList){
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(fpk.getIdDetailCheckup());

            List<HeaderDetailCheckup> details = checkupDetailBo.getByCriteria(detailCheckup);
            if (details.size() > 0){
                for (HeaderDetailCheckup detail : details){
                    BigDecimal nilai = checkupDetailBo.getSumJumlahTindakan(detail.getIdDetailCheckup(), "");
                    BigDecimal nilaiObat = checkupDetailBo.getSumJumlahTindakan(detail.getIdDetailCheckup(), "resep");

                    BigDecimal ppn = nilaiObat.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal totalNilai = nilai.add(ppn);

                    total = total.add(totalNilai);

                    Map mapKlaim = new HashMap();
                    mapKlaim.put("bukti", detail.getInvoice());
                    mapKlaim.put("pasien_id", fpk.getIdPasien());
                    mapKlaim.put("nilai", totalNilai);
                    mapListKlaim.add(mapKlaim);
                }
            }
        }

        String branchId = CommonUtil.userBranchLogin();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        Map mapJurnal = new HashMap();
        mapJurnal.put("kas", total);
        mapJurnal.put("piutang_pasien_bpjs", mapListKlaim);
        mapJurnal.put("metode_bayar", "transfer");
        mapJurnal.put("bank", data.getBank());

        String noJurnal = "";
        String catatan = "Pembayaran Piutang BPJS Bank "+data.getBank()+" No. FPK "+data.getNoFpk()+" No. Referensi "+data.getNoSlipBank();
        try {
            noJurnal = billingSystemBo.createJurnal("10", mapJurnal, branchId, catatan, "Y");
            if (!"".equalsIgnoreCase(noJurnal)){
                for (Fpk fpk : fpkList){
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setIdDetailCheckup(fpk.getIdDetailCheckup());
                    detailCheckup.setNoJurnal(noJurnal);
                    detailCheckup.setBranchId(branchId);
                    detailCheckup.setAction("U");
                    detailCheckup.setLastUpdate(time);
                    detailCheckup.setLastUpdateWho(userLogin);

                    try {
                        checkupDetailBo.saveUpdateNoJuran(detailCheckup);
                    } catch (GeneralBOException e){
                        logger.error("Found Error");
                        throw new GeneralBOException(e.getMessage());
                    }
                }
            }
            kasirRawatJalanBo.pembayaranFPK(fpkList);
        }catch (GeneralBOException e){
            logger.error("Found Error");
            throw new GeneralBOException(e.getMessage());
        }

        return "success_save_import_fpk";
    }

    public CrudResponse saveNoFPK(String jsonString, String noFPK, String tanggal) throws JSONException {
        CrudResponse response = new CrudResponse();
        List<Fpk> fpkList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                Fpk fpk = new Fpk();
                JSONObject obj = json.getJSONObject(i);
                fpk.setNoSep(obj.getString("no_sep"));
                fpk.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                fpk.setNoFpk(noFPK);
                fpk.setTanggalFpk(Date.valueOf(tanggal));
                fpkList.add(fpk);
            }

            try {
                response = kasirRawatJalanBo.saveNoFPK(fpkList);
            } catch (GeneralBOException e) {
                logger.error("Found Error");
                response.setStatus("error");
                response.setMsg("Found error " + e);
            }
        }
        return response;
    }

    public CrudResponse savePembayaranFPK(String jsonString, String noSlip, String bank, String noRekeking) throws JSONException {
        CrudResponse response = new CrudResponse();
        List<Fpk> fpkList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        if (jsonString != null && !"".equalsIgnoreCase(jsonString)) {
            JSONArray json = new JSONArray(jsonString);
            String fpkId = "";
            for (int i = 0; i < json.length(); i++) {
                Fpk fpk = new Fpk();
                JSONObject obj = json.getJSONObject(i);
                fpk.setIdFpk(obj.getString("id_fpk"));
                fpk.setNoSep(obj.getString("no_sep"));
                fpk.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                fpk.setIdPasien(obj.getString("id_pasien"));
                fpk.setNoFpk(obj.getString("no_fpk"));
                fpk.setNoSlip(noSlip);

                fpkId = obj.getString("no_fpk");
                fpkList.add(fpk);
            }

            // create map jurnal
            List<Map> mapListKlaim = new ArrayList<>();
            BigDecimal total = new BigDecimal(0);
            for (Fpk fpk : fpkList){
                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(fpk.getIdDetailCheckup());

                List<HeaderDetailCheckup> details = checkupDetailBo.getByCriteria(detailCheckup);
                if (details.size() > 0) {
                    for (HeaderDetailCheckup detail : details) {
                        BigDecimal nilai = checkupDetailBo.getSumJumlahTindakan(detail.getIdDetailCheckup(), "");
                        BigDecimal nilaiObat = checkupDetailBo.getSumJumlahTindakan(detail.getIdDetailCheckup(), "resep");

                        BigDecimal ppn = nilaiObat.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal totalNilai = nilai.add(ppn);

                        total = total.add(totalNilai);

                        Map mapKlaim = new HashMap();
                        mapKlaim.put("bukti", detail.getInvoice());
                        mapKlaim.put("pasien_id", fpk.getIdPasien());
                        mapKlaim.put("nilai", totalNilai);
                        mapListKlaim.add(mapKlaim);
                    }
                }
            }

            String branchId = CommonUtil.userBranchLogin();
            String userLogin = CommonUtil.userLogin();
            Timestamp time = new Timestamp(System.currentTimeMillis());

            Map mapJurnal = new HashMap();
            mapJurnal.put("kas", total);
            mapJurnal.put("piutang_pasien_bpjs", mapListKlaim);
            mapJurnal.put("metode_bayar", "transfer");
            mapJurnal.put("bank", bank);

            String noJurnal = "";
            String catatan = "Pembayaran Piutang BPJS Bank " + bank + " No. FPK " + fpkId + " No. Referensi " + noSlip;
            try {

                noJurnal = billingSystemBo.createJurnal("10", mapJurnal, branchId, catatan, "Y");
                if (!"".equalsIgnoreCase(noJurnal)) {
                    for (Fpk fpk : fpkList) {
                        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                        detailCheckup.setIdDetailCheckup(fpk.getIdDetailCheckup());
                        detailCheckup.setNoJurnal(noJurnal);
                        detailCheckup.setBranchId(branchId);
                        detailCheckup.setAction("U");
                        detailCheckup.setLastUpdate(time);
                        detailCheckup.setLastUpdateWho(userLogin);

                        try {
                            checkupDetailBo.saveUpdateNoJuran(detailCheckup);
                        } catch (GeneralBOException e) {
                            logger.error("Found Error");
                            response.setStatus("error");
                            response.setMsg("Found error " + e);
                            return response;
                        }
                    }
                }

                response = kasirRawatJalanBo.pembayaranFPK(fpkList);
            } catch (GeneralBOException e) {
                logger.error("Found Error");
                response.setStatus("error");
                response.setMsg("Found error " + e);
                return response;
            }
        }
        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public CheckResponse saveRefund(String id){
        CheckResponse response = new CheckResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                response = kasirRawatJalanBo.saveRefund(id);
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMessage("Found Error " + e.getMessage());
                logger.error("Found Error when save refund" + e.getMessage());
            }
        }
        return response;
    }

    public List<ImAkunPembayaranEntity> getListPembayaran() {
        List<ImAkunPembayaranEntity> response = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

        try {
            response = kasirRawatJalanBo.getListPembayaran();
        } catch (GeneralBOException e) {
            logger.error("Found Error when save refund" + e.getMessage());
        }

        return response;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }
}
