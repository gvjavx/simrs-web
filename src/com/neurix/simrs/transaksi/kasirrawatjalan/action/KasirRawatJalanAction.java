package com.neurix.simrs.transaksi.kasirrawatjalan.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.Fpk;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.RiwayatTransaksiObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
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

        String transId = "01";
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
        hsCriteria.put("pasien_id", idPasien);
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

        Map hsCriteria = new HashMap();
        hsCriteria.put("pasien_id", idPasien);
        hsCriteria.put("metode_bayar", metodeBayar);
        hsCriteria.put("bank", kodeBank);
        if (!"".equalsIgnoreCase(noRekening)) {
            hsCriteria.put("nomor_rekening", noRekening);
        }

        String branchId = CommonUtil.userBranchLogin();

        BigDecimal uangMuka = new BigDecimal(0);
        BigDecimal uangPiutang = new BigDecimal(0);
        BigDecimal uangPendapatan = new BigDecimal(0);

        // maping untuk parameter lainnua
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            if ("uang_muka".equalsIgnoreCase(obj.getString("type").toString())) {
                uangMuka = new BigDecimal(obj.getLong("nilai"));
            } else if ("piutang_pasien_non_bpjs".equalsIgnoreCase(obj.getString("type").toString())) {
                uangPiutang = new BigDecimal(obj.getLong("nilai"));
            } else {
                hsCriteria.put(obj.getString("type").toString(), new BigDecimal(obj.getLong("nilai")));
            }
        }


        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        CrudResponse response = new CrudResponse();

        String ketTerangan = "";
        String transId = "";
        if ("tunai".equalsIgnoreCase(jenis) && "JRJ".equalsIgnoreCase(type) && !"Y".equalsIgnoreCase(withObat)) {
            transId = "18";
            ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai tanpa Obat ";
        }
        if ("tunai".equalsIgnoreCase(jenis) && "JRJ".equalsIgnoreCase(type) && "Y".equalsIgnoreCase(withObat)) {
            transId = "19";
            ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai dengan Obat ";
        }
        if ("tunai".equalsIgnoreCase(jenis) && "JRI".equalsIgnoreCase(type)) {
            transId = "20";
            ketTerangan = "Closing Pasien Rawat Inap Umum Tunai ";
        }

        // jika piutang
        String invNumber = "";
        if ("non_tunai".equalsIgnoreCase(jenis) || "bpjs".equalsIgnoreCase(jenis)) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);
            List<HeaderDetailCheckup> detailCheckups = checkupDetailBo.getByCriteria(detailCheckup);
            if (detailCheckups.size() > 0) {
                for (HeaderDetailCheckup data : detailCheckups) {
                    invNumber = data.getInvoice();
                }
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
                transId = "11";
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

                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                detailCheckup.setLastUpdateWho(CommonUtil.userLogin());
                detailCheckup.setNoJurnal(noJurnal);

                checkupDetailBo.updateStatusBayarDetailCheckup(detailCheckup);
                response.setStatus("success");
            } catch (GeneralBOException e) {
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
            }catch (GeneralBOException e){
                logger.error("Found Error");
                response.setStatus("error");
                response.setMsg("Found error "+e);
            }
        }
        return response;
    }

    public CrudResponse savePembayaranFPK(String jsonString, String noSlip) throws JSONException {
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
                fpk.setNoSlip(noSlip);

                fpkId = obj.getString("id_fpk");
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
                        total = total.add(nilai);

                        Map mapKlaim = new HashMap();
                        mapKlaim.put("bukti", detail.getInvoice());
                        mapKlaim.put("pasien_id", fpk.getIdPasien());
                        mapKlaim.put("nilai", nilai);
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
            mapJurnal.put("bank", "bri");
            mapJurnal.put("nomor_rekening", noSlip);

            String noJurnal = "";
            String catatan = "Pembayaran Piutang BPJS dengan no FPK "+fpkId;
            try {

                noJurnal = billingSystemBo.createJurnal("23", mapJurnal, branchId, catatan, "Y");
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
                            response.setStatus("error");
                            response.setMsg("Found error "+e);
                            return response;
                        }
                    }
                }

                response = kasirRawatJalanBo.pembayaranFPK(fpkList);
            }catch (GeneralBOException e){
                logger.error("Found Error");
                response.setStatus("error");
                response.setMsg("Found error "+e);
                return response;
            }
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
