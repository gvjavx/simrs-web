package com.neurix.simrs.transaksi.kasirrawatjalan.action;

import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.dao.JenisPeriksaPasienDao;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
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
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.checkupdetail.model.*;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
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
import java.security.PublicKey;
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
    private KlaimFpkDTO klaimFpkDTO;

    public KlaimFpkDTO getKlaimFpkDTO() {
        return klaimFpkDTO;
    }

    public void setKlaimFpkDTO(KlaimFpkDTO klaimFpkDTO) {
        this.klaimFpkDTO = klaimFpkDTO;
    }

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
//        headerDetailCheckup.setNotLike("bpjs");

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

    public List<RiwayatTindakan> getListTindakanRawat(String idDetail, String jenisPasien) {
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {
            List<RiwayatTindakan> result = new ArrayList<>();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

            RiwayatTindakan tindakanRawat = new RiwayatTindakan();
            if("bpjs".equalsIgnoreCase(jenisPasien) || "asuransi".equalsIgnoreCase(jenisPasien)){
                tindakanRawat.setJenisPasien("umum");
            }
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
        if(statusBayar != null && !"".equalsIgnoreCase(statusBayar)){
            uangMuka.setStatusBayar(statusBayar);
            uangMuka.setFlagRefund("Y");
        }
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
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        String masterId = "";
        ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
        if (jenisPeriksaPasienEntity != null){
            masterId = jenisPeriksaPasienEntity.getMasterId();
        }

        String idDetailCheckup = "";
        ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = kasirRawatJalanBo.getEnityUangMukaById(id);
        if (uangMukaPendaftaranEntity != null){
            idDetailCheckup = uangMukaPendaftaranEntity.getIdDetailCheckup();
        }

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
        hsCriteria.put("metode_bayar", metodeBayar);
        hsCriteria.put("bank", kodeBank);

        if (!"".equalsIgnoreCase(noRekening)) {
            hsCriteria.put("nomor_rekening", noRekening);
        }

        Map mapUangMuka = new HashMap();
        mapUangMuka.put("bukti", id);
        mapUangMuka.put("pasien_id", idPasien);
        mapUangMuka.put("nilai", new BigDecimal(jumlahDibayar));

        Map mapKas = new HashMap();
        mapKas.put("nilai", new BigDecimal(jumlahDibayar));
        mapKas.put("metode_bayar", metodeBayar);
        mapKas.put("bank", kodeBank);

        hsCriteria.put("uang_muka", mapUangMuka);
        hsCriteria.put("kas", mapKas);

        try {

            String catatan = "Uang Muka untuk No. Detail Checkup "+idDetailCheckup+". No. RM " + idPasien;
            if (!"".equalsIgnoreCase(noRekening)) {
                catatan = catatan + " No. Kartu " + noRekening;
            }

            Jurnal jurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");

            UangMuka uangMuka = new UangMuka();
            uangMuka.setNoNota(noNota);
            uangMuka.setId(id);
            uangMuka.setDibayar(new BigInteger(jumlahDibayar));
            uangMuka.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            uangMuka.setLastUpdateWho(CommonUtil.userLogin());
            uangMuka.setNoJurnal(jurnal.getNoJurnal());

            kasirRawatJalanBo.updateNotaUangMukaById(uangMuka);

            response.setStatus("success");
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("[KasirRawatJalanAction.saveUangMuka] ERROR " + e);
        }
        return response;
    }

    private BigDecimal hitungPPN(BigDecimal harga){

        BigDecimal jumlah = new BigDecimal(0);
        if (harga != null){
            jumlah = harga.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return jumlah;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        String divisiId = "";

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)){
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)){

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null){

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0){
                    Pelayanan pelayananData = pelayananList.get(0);

                    ImPosition position = positionBo.getPositionEntityById(pelayananData.getDivisiId());
                    if (position != null) {
                        divisiId = position.getKodering();
                    }
                }
            }

        } else {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                        if (lastRuangan != null) {
                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(lastRuangan.getIdRuang());
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
                                    }
                                }
                            }
                        }
                    }
                } catch (GeneralBOException e) {
                    throw new GeneralBOException("[getDivisiId] ERROR " + e);
                }
            } else {
                throw new GeneralBOException("[getDivisiId] ERROR gagal mendapakatkan divisi_id atau data detail checkup");
            }
        }
        return divisiId;
    }

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type) {
        logger.info("[CheckupDetailAction.getAcitivityList] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Map> activityList = new ArrayList<>();

        String idDokter = "";
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsDokterTeamEntity> dokterTeamEntities = teamDokterBo.getListEntityTeamDokter(dokterTeam);
        if (dokterTeamEntities.size() > 0) {
            ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamEntities.get(0);
            idDokter = dokterTeamEntity.getIdDokter();
        }

        // riwayat tindakan list
        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
        riwayatTindakan.setJenisPasien(jenisPasien);

        if ("".equalsIgnoreCase(ket)) {
            riwayatTindakan.setNotResep("Y");
        } else {
            riwayatTindakan.setKeterangan(ket);
        }

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // jika selain JRJ
                // maka obat dikenakan PPN
                BigDecimal ppn = new BigDecimal(0);

                // mencari apakah tindakan transitoris
                boolean nonTransitoris = true;
                if ("JRI".equalsIgnoreCase(type)) {
                    ItSimrsTindakanTransitorisEntity transitorisEntity = riwayatTindakanBo.getTindakanTransitorisById(riwayatTindakanEntity.getIdRiwayatTindakan());
                    if (transitorisEntity != null) {
                        // jika ditemukan transitoris
                        // maka transitoris;
                        nonTransitoris = false;
                    }
                }

                // jika bukan Transitoris
                // maka ditambahkan activity
                if (nonTransitoris) {
                    Map activityMap = new HashMap();
                    activityMap.put("activity_id", riwayatTindakanEntity.getIdTindakan());
                    activityMap.put("person_id", idDokter);
                    activityMap.put("nilai", riwayatTindakanEntity.getTotalTarif().add(ppn));
                    activityMap.put("no_trans", riwayatTindakanEntity.getIdDetailCheckup());
                    activityMap.put("tipe", riwayatTindakanEntity.getJenisPasien());
                    activityList.add(activityMap);
                }
            }
        }
        //** mencari tindakan dan dimasukan ke jurnal detail activity. END **//
        logger.info("[CheckupDetailAction.getAcitivityList] END <<<");
        return activityList;
    }

    private String getMasterIdByTipe(String idDetailCheckup, String jenis){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        String masterId = "";
        if ("bpjs".equalsIgnoreCase(jenis)){

            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById(jenis);
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        } else if ("asuransi".equalsIgnoreCase(jenis)){
            // jika asuransi
            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null){
                masterId = asuransiEntity.getNoMaster();
            } else {
            }

        } else if ("ptpn".equalsIgnoreCase(jenis)){
            masterId =  detailCheckupEntity.getIdAsuransi();
        } else {
            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        }

        return masterId;
    }

    public CrudResponse savePembayaranTagihan(String jsonString, String idPasien, String noNota, String withObat, String idDetailCheckup, String metodeBayar, String kodeBank, String type, String jenis, String noRekening) throws JSONException {

        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        String noKartu = "";
        String masterId = "";
        String jenisPasien = "";
        String kode = "";
        String ketPoli = "";
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " ";
                noKartu = " No. Kartu Asuransi " + detailCheckupEntity.getNoKartuAsuransi();
            } else {
                logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                return response;
            }
        }


        ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
        if ("rawat_jalan".equalsIgnoreCase(pelayananEntity.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
            kode = "JRJ";
            ketPoli = "Rawat Jalan ";
        }
        if ("rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
            kode = "JRI";
            ketPoli = "Rawat Inap ";
        }

        //** INISIALISASI MAP UNTUK CREATE JURNAL **//
        Map mapJurnal = new HashMap();
        List<Map> listOfMapTindakanUmum = new ArrayList<>();
        List<Map> listOfMapTindakanAsuransi = new ArrayList<>();
        List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
        if (listOfKeteranganRiwayat.size() > 0){

            for (String keterangan : listOfKeteranganRiwayat){
                Map mapTindakan = new HashMap();
                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "umum"));
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "umum", keterangan));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", keterangan));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "umum", keterangan, kode));
                listOfMapTindakanUmum.add(mapTindakan);
            }
            for (String keterangan : listOfKeteranganRiwayat){
                Map mapTindakan = new HashMap();
                mapTindakan.put("master_id", getMasterIdByTipe(idDetailCheckup, "asuransi"));
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "asuransi", keterangan));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "asuransi", keterangan));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "asuransi", keterangan, kode));
                listOfMapTindakanAsuransi.add(mapTindakan);
            }
        }

        String branchId = CommonUtil.userBranchLogin();
        //** CARI APAKAH ADA TRANSITORIS **//
        boolean isTransitoris = false;
        BigDecimal allTindakanTransUmum = new BigDecimal(0);
        BigDecimal allTindakanTransAsuransi = new BigDecimal(0);
        if (detailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoJurnalTrans())){

            // for tindakan transitoris;
            allTindakanTransUmum = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "umum","");
            allTindakanTransAsuransi = checkupDetailBo.getSumJumlajTindakanTransitorisByJenis(idDetailCheckup, "asuransi","");

            Map mapTransitoris = new HashMap();
            mapTransitoris.put("nilai", allTindakanTransUmum.add(allTindakanTransAsuransi));
            mapTransitoris.put("bukti", detailCheckupEntity.getInvoiceTrans());
            mapJurnal.put("piutang_transistoris_pasien_rawat_inap", mapTransitoris);
            isTransitoris = true;
        }

        BigDecimal uangMuka = new BigDecimal(0);
        BigDecimal uangPiutang = new BigDecimal(0);
        BigDecimal ppnObat = new BigDecimal(0);

        //** MAPPING NILAI PARAMETER DARI JSON YG DIKIRIM **//
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);

            if ("uang_muka".equalsIgnoreCase(obj.getString("type").toString())) {
                uangMuka = new BigDecimal(obj.getLong("nilai"));
            }
            else if ("ppn_keluaran".equalsIgnoreCase(obj.getString("type").toString())) {
                ppnObat = new BigDecimal(obj.getLong("nilai"));
            }
//            else if ("piutang_pasien_non_bpjs".equalsIgnoreCase(obj.getString("type").toString())) {
//                uangPiutang = new BigDecimal(obj.getLong("nilai"));
//            } else if ("piutang_pasien_asuransi".equalsIgnoreCase(obj.getString("type").toString())) {
//                uangPiutang = new BigDecimal(obj.getLong("nilai"));
//            }
//            else if ("pendapatan_rawat_inap_asuransi".equalsIgnoreCase(obj.getString("type").toString()) || "pendapatan_rawat_jalan_asuransi".equalsIgnoreCase(obj.getString("type").toString())) {
//                pendapatanRawat = new BigDecimal(obj.getLong("nilai"));
//            } else if ("pendapatan_obat_asuransi".equalsIgnoreCase(obj.getString("type").toString())) {
//                pendapatanResep = new BigDecimal(obj.getLong("nilai"));
//            } else if ("pendapatan_rawat_jalan_umum".equalsIgnoreCase(obj.getString("type").toString()) || "pendapatan_rawat_inap_umum".equalsIgnoreCase(obj.getString("type").toString())) {
//                pendapatanRawatUmum = new BigDecimal(obj.getLong("nilai"));
//            } else if ("pendapatan_obat_umum".equalsIgnoreCase(obj.getString("type").toString())) {
//                pendapatanResepUmum = new BigDecimal(obj.getLong("nilai"));
//            } else if ("kas".equalsIgnoreCase(obj.getString("type").toString())) {
//                kas = new BigDecimal(obj.getLong("nilai"));
//            } else {
//                mapJurnal.put(obj.getString("type").toString(), new BigDecimal(obj.getLong("nilai")));
//            }
        }



        String ketTerangan = "";
        String transId = "";

        // MAPPING KAS
        Map mapKas = new HashMap();
        if ("paket_individu".equalsIgnoreCase(jenis)){
            mapKas.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "paket_individu", "").add(ppnObat).add(allTindakanTransUmum).subtract(uangMuka));
        } else {
            mapKas.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", "").add(ppnObat).add(allTindakanTransUmum).subtract(uangMuka));
        }
        mapKas.put("metode_bayar", metodeBayar);
        mapKas.put("bank", kodeBank);
        if (!"".equalsIgnoreCase(noRekening)) {
            mapKas.put("nomor_rekening", noRekening);
        }

        // kas
        mapJurnal.put("kas", mapKas);
        Map mapPajakObat = new HashMap();
        String invoiceNumber = billingSystemBo.createInvoiceNumber(type, branchId);

        if ("tunai".equalsIgnoreCase(jenis)){

            mapJurnal.put("pendapatan_rawat_jalan_umum", listOfMapTindakanUmum);

            if ("JRJ".equalsIgnoreCase(type) && !"Y".equalsIgnoreCase(withObat)) {

                transId = "12";
                ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai tanpa Obat ";
            }
            if ("JRJ".equalsIgnoreCase(type) && "Y".equalsIgnoreCase(withObat)) {

                mapPajakObat.put("bukti", invoiceNumber);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);
                mapJurnal.put("ppn_keluaran", mapPajakObat);

                transId = "15";
                ketTerangan = "Closing Pasien Rawat Jalan Umum Tunai dengan Obat ";
            }
            if ("JRI".equalsIgnoreCase(type)) {

                mapJurnal.put("pendapatan_rawat_inap_umum", listOfMapTindakanUmum);

                if (isTransitoris){
                    transId = "38";
                    ketTerangan = "Closing Pasien Rawat Inap Umum Tunai Terhadap Transitoris. ";
                } else {
                    transId = "22";
                    ketTerangan = "Closing Pasien Rawat Inap Umum Tunai ";
                }
            }
        }



        // jika piutang
        String invNumber = "";
        if ("non_tunai".equalsIgnoreCase(jenis) || "bpjs".equalsIgnoreCase(jenis) || "asuransi".equalsIgnoreCase(jenis) || "paket_individu".equalsIgnoreCase(jenis)) {

            if (!"asuransi".equalsIgnoreCase(jenis)){
                if (detailCheckupEntity != null && detailCheckupEntity.getInvoice() != null){
                    invNumber = detailCheckupEntity.getInvoice();
                } else {
                    response.setStatus("error");
                    response.setMsg("[KasirRawatJalanAction.savePembayaranTagihan] ERROR gagal mendapakatkan no_invoice atau data detail checkup");
                    return response;
                }
            }

            // map piutang
            Map mapPiutang = new HashMap();
            if ("bpjs".equalsIgnoreCase(jenis)) {

                //** BPJS **//

                mapPiutang.put("bukti", invNumber);
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", "").add(ppnObat).add(allTindakanTransUmum).subtract(uangMuka));
                mapPiutang.put("master_id", masterId);
//                mapPiutang.put("pasien_id", idPasien);

                transId = "10";
                ketTerangan = "Pembayaran Piutang Pasien BPJS";
                mapJurnal.put("piutang_pasien_bpjs", mapPiutang);

            } else if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){

                //** ASURANSI **//

                mapPiutang.put("bukti", billingSystemBo.createInvoiceNumber(type, branchId));
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "asuransi", "").add(allTindakanTransAsuransi));
                mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "asuransi"));

                mapJurnal.put("piutang_pasien_asuransi", mapPiutang);

                if ("JRI".equalsIgnoreCase(type)){

                    mapJurnal.put("pendapatan_rawat_inap_asuransi", listOfMapTindakanAsuransi);
                    mapJurnal.put("pendapaatan_rawat_inap_umum", listOfMapTindakanUmum);

                    transId = "26";
                    ketTerangan = "Closing pasien rawat Inap Asuransi piutang dan Tunai";

                } else {
                    if ("Y".equalsIgnoreCase(withObat)){

//                        Map mapResepAsuransi = new HashMap();
//                        mapResepAsuransi.put("master_id", masterId);
////                        mapResepAsuransi.put("pasien_id", idPasien);
//                        mapResepAsuransi.put("divisi_id", divisiId);
//                        mapResepAsuransi.put("nilai", pendapatanResep);
//                        mapResepAsuransi.put("activity", listActivityResep);
//
//                        Map mapResepUmum = new HashMap();
//                        mapResepUmum.put("master_id", masterUmum);
////                        mapResepUmum.put("pasien_id", idPasien);
//                        mapResepUmum.put("divisi_id", divisiId);
//                        mapResepUmum.put("nilai", pendapatanResepUmum);
//                        mapResepUmum.put("activity", listActivityResepUmum);

                        mapPajakObat.put("bukti", invoiceNumber);
                        mapPajakObat.put("nilai", ppnObat);
                        mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                        mapJurnal.put("ppn_keluaran", mapPajakObat);
                        mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfMapTindakanAsuransi);
                        mapJurnal.put("pendapaatan_rawat_jalan_umum", listOfMapTindakanUmum);

                        transId = "19";
                        ketTerangan = "Closing pasien rawat jalan Asuransi piutang dan Tunai dengan obat";
                    } else {

                        mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfMapTindakanAsuransi);
                        mapJurnal.put("pendapatan_rawat_jalan_umum", listOfMapTindakanUmum);

                        transId = "11";
                        ketTerangan = "Closing pasien rawat jalan Asuransi piutang dan Tunai tanpa obat";
                    }
                }

            } else {

                mapPiutang.put("bukti", invNumber);
                if ("paket_individu".equalsIgnoreCase(jenis)){
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "paket_individu", "").add(ppnObat).add(allTindakanTransUmum).subtract(uangMuka));
                } else {
                    mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", "").add(ppnObat).add(allTindakanTransUmum).subtract(uangMuka));
                }
                mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "umum"));
//                mapPiutang.put("pasien_id", idPasien);

                //** UMUM **//
                transId = "02";
                ketTerangan = "Pembayaran Piutang Pasien Umum";
                mapJurnal.put("piutang_pasien_non_bpjs", mapPiutang);
            }

        } else {

            // jika bukan piutang maka pakai map uang muka
            Map mapUangMuka = new HashMap();
            mapUangMuka.put("bukti", noNota);
            mapUangMuka.put("nilai", uangMuka);
            mapUangMuka.put("pasien_id", idPasien);
            mapJurnal.put("uang_muka", mapUangMuka);
        }

        if (!"".equalsIgnoreCase(transId)) {
            try {
                String text = "";
                if (("transfer").equalsIgnoreCase(metodeBayar)) {
                    text = " pada Bank " + getNamaBank(kodeBank);
                }

                String catatan = ketTerangan + " untuk No Pasien " + idPasien + " No. Detail Checkup " + idDetailCheckup + " menggunakan metode " + metodeBayar + text + noKartu;
                if (!"".equalsIgnoreCase(noRekening)) {
                    catatan = catatan + " No. Kartu Pembayaran " + noRekening;
                }

                //** creat Jurnal **//
                Jurnal jurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                detailCheckup.setLastUpdateWho(CommonUtil.userLogin());
                detailCheckup.setNoJurnal(jurnal.getNoJurnal());

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
        HttpSession session = ServletActionContext.getRequest().getSession();

        KlaimFpkDTO dataFpk = (KlaimFpkDTO) session.getAttribute("summaryFpk");
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);
        dataFpk.setStTanggalFpk(tglToday);
        setKlaimFpkDTO(dataFpk);

        logger.info("[KasirRawatJalanAction.goToHasilCsv] end process <<<");
        return "hasil_import_csv";
    }

    public String prosesImportCsv() {
        logger.info("[KasirRawatJalanAction.prosesImportCsv] start process >>>");
        List<KlaimFpkDTO> listOfResult = new ArrayList<>();
        KlaimFpkDTO dataFpk = new KlaimFpkDTO();
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
                        Integer jumlahSudahDiKlaim=0;
                        Integer jumlahSepTidakAda=0;
                        Integer jumlahBiayaBpjsKurangDariRs=0;
                        Integer jumlahBiayaBpjsLebihDariRs=0;
                        Integer jumlahBiayaBpjsSamaDenganRs=0;
                        Integer jumlahSalah=0;
                        BigInteger jumlahBiayaBpjs=BigInteger.ZERO;
                        BigInteger jumlahBiayaRs=BigInteger.ZERO;
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
                                KlaimFpkDTO result = new KlaimFpkDTO();
                                result.setNoSep(data[0]);
                                result.setTotalBiayaBpjs(new BigInteger(data[1]));

                                //dicari di tabel
                                HeaderDetailCheckup search = new HeaderDetailCheckup();
                                search.setNoSep(data[0]);
                                List<KlaimFpkDTO> resultList = kasirRawatJalanBoProxy.getSearchCheckupBySep(data[0]);
                                for (KlaimFpkDTO dataKlaim : resultList){
                                    result.setTotalBiaya(dataKlaim.getTotalBiaya());
                                    result.setStatus(dataKlaim.getStatusBayar());
                                    result.setIdDetailCheckup(dataKlaim.getIdDetailCheckup());
                                    result.setNamaPasien(dataKlaim.getNamaPasien());
                                    result.setIdPasien(dataKlaim.getIdPasien());
                                }

                                String statusBayar;
                                if (("Y").equalsIgnoreCase(result.getStatus())){
                                    statusBayar="SB";
                                    jumlahSudahDiKlaim=jumlahSudahDiKlaim+1;
                                }else if (result.getTotalBiaya()==null){
                                    statusBayar="N";
                                    jumlahSepTidakAda=jumlahSepTidakAda+1;
                                }else if (result.getTotalBiayaBpjs().compareTo(result.getTotalBiaya())<0){
                                    statusBayar="KB";
                                    jumlahBiayaBpjsKurangDariRs=jumlahBiayaBpjsKurangDariRs+1;
                                    jumlahBiayaBpjs=jumlahBiayaBpjs.add(result.getTotalBiayaBpjs());
                                }else if (result.getTotalBiaya().compareTo(result.getTotalBiayaBpjs())<0){
                                    statusBayar="LB";
                                    jumlahBiayaBpjsLebihDariRs=jumlahBiayaBpjsLebihDariRs+1;
                                    jumlahBiayaBpjs=jumlahBiayaBpjs.add(result.getTotalBiayaBpjs());
                                }else if (result.getTotalBiaya().compareTo(result.getTotalBiayaBpjs())==0){
                                    statusBayar="P";
                                    jumlahBiayaBpjsSamaDenganRs=jumlahBiayaBpjsSamaDenganRs+1;
                                    jumlahBiayaBpjs=jumlahBiayaBpjs.add(result.getTotalBiayaBpjs());
                                }else{
                                    statusBayar="UK";
                                    jumlahSalah=jumlahSalah+1;
                                }
                                result.setStatusBayar(statusBayar);
                                result.setStTotalBiayaBpjs(CommonUtil.numbericFormat(new BigDecimal(result.getTotalBiayaBpjs()),"###,###"));
                                if (result.getTotalBiaya()==null){
                                    result.setStTotalBiaya("0");
                                }else{
                                    result.setStTotalBiaya(CommonUtil.numbericFormat(new BigDecimal(result.getTotalBiaya()),"###,###"));
                                    jumlahBiayaRs=jumlahBiayaRs.add(result.getTotalBiaya());
                                }
                                listOfResult.add(result);
                            }
                            x++;
                        }
                        dataFpk.setJumlahSudahDiKlaim(jumlahSudahDiKlaim);
                        dataFpk.setJumlahSepTidakAda(jumlahSepTidakAda);
                        dataFpk.setJumlahBiayaBpjsKurangDariRs(jumlahBiayaBpjsKurangDariRs);
                        dataFpk.setJumlahBiayaBpjsLebihDariRs(jumlahBiayaBpjsLebihDariRs);
                        dataFpk.setJumlahBiayaBpjsSamaDenganRs(jumlahBiayaBpjsSamaDenganRs);
                        dataFpk.setJumlahSalah(jumlahSalah);
                        dataFpk.setJumlahSeluruhnyaBpjs(jumlahBiayaBpjs);
                        dataFpk.setJumlahSeluruhnya(jumlahBiayaRs);
                        dataFpk.setStJumlahSeluruhnya((CommonUtil.numbericFormat(new BigDecimal(dataFpk.getJumlahSeluruhnya()),"###,###")));
                        dataFpk.setStJumlahSeluruhnyaBpjs((CommonUtil.numbericFormat(new BigDecimal(dataFpk.getJumlahSeluruhnyaBpjs()),"###,###")));
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
        session.removeAttribute("summaryFpk");
        session.setAttribute("summaryFpk", dataFpk);

        logger.info("[KasirRawatJalanAction.prosesImportCsv] end process <<<");
        return "hasil_import_csv";
    }

    public String saveImportCsv() {
        logger.info("[KasirRawatJalanAction.saveImportCsv] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<KlaimFpkDTO> listOfResult = (List<KlaimFpkDTO>) session.getAttribute("listOfImportCsv");
        KlaimFpkDTO klaimFpkDTO = getKlaimFpkDTO();

        Map mapping = kasirRawatJalanBoProxy.setMappingJurnalFpk(klaimFpkDTO,listOfResult);
        kasirRawatJalanBoProxy.saveFpk(klaimFpkDTO,listOfResult);
        billingSystemBoProxy.createJurnal("31",mapping,CommonUtil.userBranchLogin(),"Klaim BPJS dengan nomor FPK : "+klaimFpkDTO.getNoFpk()+" pada tanggal :" +klaimFpkDTO.getStTanggalFpk(),"Y");

        logger.info("[KasirRawatJalanAction.saveImportCsv] end process <<<");
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
                        BigDecimal nilai = checkupDetailBo.getSumJumlahTindakanNonBpjs(detail.getIdDetailCheckup(), "");
                        BigDecimal nilaiObat = checkupDetailBo.getSumJumlahTindakanNonBpjs(detail.getIdDetailCheckup(), "resep");

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

                Jurnal jurnal = billingSystemBo.createJurnal("10", mapJurnal, branchId, catatan, "Y");
                noJurnal = jurnal.getNoJurnal();
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
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        String noNota = "";
        String noPasien = "";
        String masterId = "";
        BigDecimal uangMuka = new BigDecimal(0);
        ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = kasirRawatJalanBo.getEnityUangMukaById(id);
        if (uangMukaPendaftaranEntity != null){

            uangMuka = new BigDecimal(uangMukaPendaftaranEntity.getJumlahDibayar());
            noNota = uangMukaPendaftaranEntity.getId();

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(uangMukaPendaftaranEntity.getIdDetailCheckup());
            if (detailCheckupEntity != null){

                ItSimrsHeaderChekupEntity headerChekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
                if (headerChekupEntity != null){
                    noPasien = headerChekupEntity.getIdPasien();
                }

                // MENDAPATKAN DATA MASTER ID;
                masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
            }

        } else {
            response.setStatus("error");
            response.setMessage("[KasirRawatJalanAction.saveRefund] Tidak Dapat Data Uang Muka.");
            logger.error("[KasirRawatJalanAction.saveRefund] Tidak Dapat Data Uang Muka.");
        }

        Map mapUangMuka = new HashMap();
        mapUangMuka.put("bukti", noNota);
        mapUangMuka.put("nilai", uangMuka);
//        mapUangMuka.put("master_id", masterId);
        mapUangMuka.put("pasien_id", noPasien);

        Map mapKas = new HashMap();
        mapKas.put("nilai", uangMuka);
        mapKas.put("metode_bayar", "tunai");

        Map mapJurnal = new HashMap();
        mapJurnal.put("uang_muka", mapUangMuka);
        mapJurnal.put("kas", mapKas);

        String catatan = "pembayaran kembali uang muka. No. RM " + noPasien;

        String noJurnal = "";
        try {
            Jurnal jurnal = billingSystemBo.createJurnal("34", mapJurnal, CommonUtil.userBranchLogin(), catatan, "Y" );
            noJurnal = jurnal.getNoJurnal();
        } catch (GeneralBOException e){
            response.setStatus("error");
            response.setMessage("[KasirRawatJalanAction.saveRefund] ERROR. " + e.getMessage());
            logger.error("[KasirRawatJalanAction.saveRefund] ERROR. " + e.getMessage());
        }

        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                response = kasirRawatJalanBo.saveRefund(id , noJurnal);
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

    public String getCoverAsuransi(String idDetailCheckup){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        ItSimrsHeaderDetailCheckupEntity headerDetailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        if (headerDetailCheckupEntity != null){
            return headerDetailCheckupEntity.getCoverBiaya().toString();
        }
        return  "0";
    }

    public List<RiwayatTindakanDTO> getRiwayatTindakanDanDokter (String idDetailCheckup){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        List<RiwayatTindakanDTO> riwayatTindakanList  = checkupDetailBo.getRiwayatTindakanDanDokter(idDetailCheckup);

        return riwayatTindakanList;
    }

    public String getNamaBank(String coa){
        String nama = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasirRawatJalanBo kasirRawatJalanBo = (KasirRawatJalanBo) ctx.getBean("kasirRawatJalanBoProxy");

        ImAkunPembayaranEntity pembayaranEntity = kasirRawatJalanBo.getPembayaranEntityByCoa(coa);
        if (pembayaranEntity != null){
            nama = pembayaranEntity.getPembayaranName();
        }
        return nama;
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
