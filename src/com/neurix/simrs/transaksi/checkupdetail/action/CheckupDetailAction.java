package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.neurix.simrs.bpjs.vclaim.model.SepResponse;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;

import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckupDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupDetailAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private KelasRuanganBo kelasRuanganBoProxy;
    private RuanganBo ruanganBoProxy;
    private KeteranganKeluarBo keteranganKeluarBoProxy;
    private boolean enabledPoli = false;
    private boolean enabledAddPasien = false;
    private HeaderCheckup headerCheckup;
    private TindakanRawatBo tindakanRawatBoProxy;
    private PeriksaLabBo periksaLabBoProxy;
    private RiwayatTindakanBo riwayatTindakanBoProxy;
    private PasienBo pasienBoProxy;
    private DokterBo dokterBoProxy;
    private BpjsBo bpjsBoProxy;
    private EklaimBo eklaimBoProxy;
    private BranchBo branchBoProxy;
    private BillingSystemBo billingSystemBoProxy;


    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    private File fileUploadDoc;
    private String fileUploadDocFileName;
    private String fileUploadDocContentType;

    private String idResep;
    private BigInteger tarifCoverBpjs;
    private BigInteger tarifTotalTindakan;
    private String tipe;

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public BpjsBo getBpjsBoProxy() {
        return bpjsBoProxy;
    }

    public void setBpjsBoProxy(BpjsBo bpjsBoProxy) {
        this.bpjsBoProxy = bpjsBoProxy;
    }

    public EklaimBo getEklaimBoProxy() {
        return eklaimBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    public void setRiwayatTindakanBoProxy(RiwayatTindakanBo riwayatTindakanBoProxy) {
        this.riwayatTindakanBoProxy = riwayatTindakanBoProxy;
    }

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public BigInteger getTarifCoverBpjs() {
        return tarifCoverBpjs;
    }

    public void setTarifCoverBpjs(BigInteger tarifCoverBpjs) {
        this.tarifCoverBpjs = tarifCoverBpjs;
    }

    public BigInteger getTarifTotalTindakan() {
        return tarifTotalTindakan;
    }

    public void setTarifTotalTindakan(BigInteger tarifTotalTindakan) {
        this.tarifTotalTindakan = tarifTotalTindakan;
    }

    public String getIdResep() {
        return idResep;
    }

    public void setIdResep(String idResep) {
        this.idResep = idResep;
    }

    public File getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(File fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public String getFileUploadDocFileName() {
        return fileUploadDocFileName;
    }

    public void setFileUploadDocFileName(String fileUploadDocFileName) {
        this.fileUploadDocFileName = fileUploadDocFileName;
    }

    public String getFileUploadDocContentType() {
        return fileUploadDocContentType;
    }

    public void setFileUploadDocContentType(String fileUploadDocContentType) {
        this.fileUploadDocContentType = fileUploadDocContentType;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
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

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public boolean isEnabledAddPasien() {
        return enabledAddPasien;
    }

    public void setEnabledAddPasien(boolean enabledAddPasien) {
        this.enabledAddPasien = enabledAddPasien;
    }

    public boolean isEnabledPoli() {
        return enabledPoli;
    }

    public void setEnabledPoli(boolean enabledPoli) {
        this.enabledPoli = enabledPoli;
    }

    public void setKeteranganKeluarBoProxy(KeteranganKeluarBo keteranganKeluarBoProxy) {
        this.keteranganKeluarBoProxy = keteranganKeluarBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public void setKelasRuanganBoProxy(KelasRuanganBo kelasRuanganBoProxy) {
        this.kelasRuanganBoProxy = kelasRuanganBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    private String id;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();
    private List<KategoriTindakan> listOfKategoriTindakan = new ArrayList<>();
    private List<KelasRuangan> listOfKelasRuangan = new ArrayList<>();
    private List<KeteranganKeluar> listOfKeterangan = new ArrayList<>();

    public List<KeteranganKeluar> getListOfKeterangan() {
        return listOfKeterangan;
    }

    public void setListOfKeterangan(List<KeteranganKeluar> listOfKeterangan) {
        this.listOfKeterangan = listOfKeterangan;
    }

    public List<KelasRuangan> getListOfKelasRuangan() {
        return listOfKelasRuangan;
    }

    public void setListOfKelasRuangan(List<KelasRuangan> listOfKelasRuangan) {
        this.listOfKelasRuangan = listOfKelasRuangan;
    }

    public List<KategoriTindakan> getListOfKategoriTindakan() {
        return listOfKategoriTindakan;
    }

    public void setListOfKategoriTindakan(List<KategoriTindakan> listOfKategoriTindakan) {
        this.listOfKategoriTindakan = listOfKategoriTindakan;
    }

    public List<Diagnosa> getListOfComboDiagnosa() {
        return listOfComboDiagnosa;
    }

    public void setListOfComboDiagnosa(List<Diagnosa> listOfComboDiagnosa) {
        this.listOfComboDiagnosa = listOfComboDiagnosa;
    }


    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public static void setLogger(Logger logger) {
        CheckupDetailAction.logger = logger;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public String add() {
        logger.info("[CheckupDetailAction.add] start process >>>");

        //get data from session
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<HeaderDetailCheckup> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();

        HeaderCheckup checkup = new HeaderCheckup();
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
            detailCheckup.setStatusPeriksa("1");
            detailCheckup.setFlag("Y");
            detailCheckup.setAction("U");
            detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            detailCheckup.setLastUpdateWho(CommonUtil.userLogin());

            try {
                checkupDetailBoProxy.saveEdit(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.add] Error when update checkup detail");
            }

            detailCheckup.setIdPasien(checkup.getIdPasien());
            detailCheckup.setNamaPasien(checkup.getNama());
            detailCheckup.setAlamat(checkup.getJalan());
            detailCheckup.setDesa(checkup.getNamaDesa());
            detailCheckup.setKecamatan(checkup.getNamaKecamatan());
            detailCheckup.setKota(checkup.getNamaKota());
            detailCheckup.setProvinsi(checkup.getNamaProvinsi());
            detailCheckup.setIdPelayanan(checkup.getIdPelayanan());
            detailCheckup.setNamaPelayanan(checkup.getNamaPelayanan());
            if (checkup.getJenisKelamin() != null) {
                if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                    jk = "Perempuan";
                } else {
                    jk = "laki-Laki";
                }
            }
            detailCheckup.setJenisKelamin(jk);
            detailCheckup.setTempatLahir(checkup.getTempatLahir());
            detailCheckup.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
            detailCheckup.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            detailCheckup.setNik(checkup.getNoKtp());
            detailCheckup.setIdJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
            detailCheckup.setUrlKtp(checkup.getUrlKtp());
            detailCheckup.setTinggi(checkup.getTinggi());
            detailCheckup.setBerat(checkup.getBerat());
            detailCheckup.setNoSep(checkup.getNoSep());
            detailCheckup.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            detailCheckup.setMetodePembayaran(checkup.getMetodePembayaran());
            setHeaderDetailCheckup(detailCheckup);

        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }


//        if (id != null && !"".equalsIgnoreCase(id)) {
//
//            if (listOfResult != null) {
//
//                for (HeaderDetailCheckup detailCheckup : listOfResult) {
//                    if (id.equalsIgnoreCase(detailCheckup.getNoCheckup())) {
//
//                        detailCheckup.setStatusPeriksa("1");
//                        detailCheckup.setFlag("Y");
//                        detailCheckup.setAction("U");
//                        detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
//                        detailCheckup.setLastUpdateWho(CommonUtil.userLogin());
//
//                        try {
//                            checkupDetailBoProxy.saveEdit(detailCheckup);
//                        } catch (GeneralBOException e) {
//                            logger.error("[CheckupDetailAction.add] Error when update checkup detail");
//                        }
//
//                        HeaderCheckup headerCheckup = getHeaderCheckup(detailCheckup.getNoCheckup());
//                        detailCheckup.setIdPasien(headerCheckup.getIdPasien());
//                        detailCheckup.setNamaPasien(headerCheckup.getNama());
//                        detailCheckup.setAlamat(headerCheckup.getJalan());
//                        detailCheckup.setDesa(headerCheckup.getNamaDesa());
//                        detailCheckup.setKecamatan(headerCheckup.getNamaKecamatan());
//                        detailCheckup.setKota(headerCheckup.getNamaKota());
//                        detailCheckup.setProvinsi(headerCheckup.getNamaProvinsi());
//                        detailCheckup.setIdPelayanan(headerCheckup.getIdPelayanan());
//                        detailCheckup.setNamaPelayanan(headerCheckup.getNamaPelayanan());
//                        if (headerCheckup.getJenisKelamin() != null) {
//                            if ("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
//                                jk = "Perempuan";
//                            } else {
//                                jk = "laki-Laki";
//                            }
//                        }
//                        detailCheckup.setJenisKelamin(jk);
//                        detailCheckup.setTempatLahir(headerCheckup.getTempatLahir());
//                        detailCheckup.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
//                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
//                        detailCheckup.setTempatTglLahir(headerCheckup.getTempatLahir() + ", " + formatDate);
//                        detailCheckup.setNik(headerCheckup.getNoKtp());
//                        detailCheckup.setIdJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
//                        detailCheckup.setUrlKtp(headerCheckup.getUrlKtp());
//                        detailCheckup.setTinggi(headerCheckup.getTinggi());
//                        detailCheckup.setBerat(headerCheckup.getBerat());
//                        detailCheckup.setNoSep(headerCheckup.getNoSep());
//
//                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
//                        detailCheckup.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());
//
//                        setHeaderDetailCheckup(detailCheckup);
//
//                        if (headerCheckup.getTarifBpjs() != null && headerCheckup.getTarifBpjs().compareTo(new BigDecimal(String.valueOf(0))) == 1) {
//                            String stTarifCoverBpjs = headerCheckup.getTarifBpjs().toString();
//                            setTarifCoverBpjs(new BigInteger(stTarifCoverBpjs));
//                        }
//
//                        BigInteger totalTarif = new BigInteger(String.valueOf(0));
//
//                        try {
//                            totalTarif = checkupDetailBoProxy.getSumOfTindakanByNoCheckup(id);
//                        } catch (GeneralBOException e) {
//                            logger.error("[CheckupDetailAction.add] Error when get total tarif " + e.getMessage());
//                        }
//
//                        setTarifTotalTindakan(totalTarif);
//                        break;
//                    }
//                }
//
//            } else {
//                setHeaderDetailCheckup(new HeaderDetailCheckup());
//            }
//        } else {
//            setHeaderDetailCheckup(new HeaderDetailCheckup());
//        }

        logger.info("[CheckupDetailAction.add] end process <<<");

        return "init_add";
    }

    public HeaderDetailCheckup getStatusBiayaTindakan(String idDetailCheckup, String jenis) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        LabBo labBo = (LabBo) ctx.getBean("labBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");


        // total biaya for tindakan rawat inap harus approve dulu
        if ("RI".equalsIgnoreCase(jenis)){
            HeaderDetailCheckup biayaTindakanRawatInap = getListBiayaForRawatInap(idDetailCheckup);
            if (biayaTindakanRawatInap.getTarifTindakan() != null && biayaTindakanRawatInap.getTarifTindakan().compareTo(new BigDecimal(0)) == 1){
                return biayaTindakanRawatInap;
            }
        }

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        try {
            detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        BigInteger tarifTindakan = new BigInteger(String.valueOf(0));

        try {
            tarifTindakan = checkupDetailBo.getSumOfTindakanByNoCheckup(idDetailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.add] Error when get total tarif " + e.getMessage());
        }

        if (!detailCheckupList.isEmpty()) {
            detailCheckup = detailCheckupList.get(0);

            if (detailCheckup.getTarifBpjs() != null && detailCheckup.getTarifBpjs().compareTo(new BigDecimal(String.valueOf(0))) == 1) {

                BigDecimal tarifResep = new BigDecimal(String.valueOf(0));
                BigDecimal tarifLab = new BigDecimal(String.valueOf(0));
                BigDecimal tarifGizi = new BigDecimal(String.valueOf(0));


                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(detailCheckup.getNoCheckup());
                List<HeaderCheckup> headerCheckupList = new ArrayList<>();

                try {
                    headerCheckupList = checkupBo.getByCriteria(headerCheckup);
                } catch (GeneralBOException e) {
                    logger.error("Gound Error" + e.getMessage());
                }

                if (headerCheckupList.size() > 0) {

                    headerCheckup = headerCheckupList.get(0);

                    if (headerCheckup != null) {
                        detailCheckup.setIdJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                    }
                }

                List<PeriksaLab> periksaLabList = new ArrayList<>();
                PeriksaLab periksaLab = new PeriksaLab();
                periksaLab.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                periksaLab.setApproveFlag("Y");

                try {
                    periksaLabList = periksaLabBo.getByCriteria(periksaLab);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                }

                if (periksaLabList.size() > 0) {

                    for (PeriksaLab entity : periksaLabList) {

                        List<Lab> labList = new ArrayList<>();
                        Lab lab = new Lab();
                        lab.setIdLab(entity.getIdLab());

                        try {
                            labList = labBo.getByCriteria(lab);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tarif tindakan :" + e.getMessage());
                        }

                        if (labList.size() > 0) {
                            lab = labList.get(0);
                            if (lab != null) {

                                BigDecimal tariff = new BigDecimal(String.valueOf(0));
                                if (lab.getTarif() != null) {
                                    tariff = lab.getTarif();
                                }

                                tarifLab = tarifLab.add(tariff);
                            }
                        }
                    }
                }

                List<PermintaanResep> resepList = new ArrayList<>();
                PermintaanResep resep = new PermintaanResep();
                resep.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());

                try {
                    resepList = permintaanResepBo.getByCriteria(resep);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
                }

                if (resepList.size() > 0) {
                    for (PermintaanResep entity : resepList) {

                        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
                        TransaksiObatDetail detail = new TransaksiObatDetail();
                        detail.setIdPermintaanResep(entity.getIdPermintaanResep());

                        try {
                            obatDetailList = transaksiObatBo.getSearchObatTransaksiByCriteria(detail);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
                        tarifResep = tarifResep.add(new BigDecimal(hitungTotalResep));
                    }
                }

                RawatInap rawatInap = new RawatInap();
                rawatInap.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                List<RawatInap> rawatInapList = new ArrayList<>();

                try {
                    rawatInapList = rawatInapBo.getByCriteria(rawatInap);
                } catch (GeneralBOException e) {
                    logger.error("Found Error" + e.getMessage());
                }

                if (rawatInapList.size() > 0) {

                    rawatInap = rawatInapList.get(0);
                    if (rawatInap != null) {

                        OrderGizi orderGizi = new OrderGizi();
                        orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                        List<OrderGizi> giziList = new ArrayList<>();

                        try {
                            giziList = orderGiziBo.getByCriteria(orderGizi);
                        } catch (GeneralBOException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        if (giziList.size() > 0) {

                            for (OrderGizi gizi : giziList) {

                                BigDecimal tariff = new BigDecimal(String.valueOf(0));
                                if (gizi.getTarifTotal() != null) {
                                    tariff = gizi.getTarifTotal();
                                }

                                tarifGizi = tarifGizi.add(tariff);
                            }
                        }
                    }
                }

                BigDecimal totalTarifTindakan = tarifLab.add(tarifResep).add(new BigDecimal(tarifTindakan)).add(tarifGizi);

                //=======START HITUNG TARIF TINDAKAN==========================

                detailCheckup.setTarifBpjs(detailCheckup.getTarifBpjs());
                detailCheckup.setKodeCbg(detailCheckup.getKodeCbg());
                detailCheckup.setTarifTindakan(totalTarifTindakan);

                //=======END HITUNG TARIF TINDAKAN==========================
            }

        }

        return detailCheckup;
    }

    public HeaderDetailCheckup getListBiayaForRawatInap(String idDetailCheckup){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){

            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            BigDecimal totalBiayaTindakan = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "");

            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

            try {
                detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
            }

            if (detailCheckupList.size() > 0){
                HeaderDetailCheckup headerDetailCheckup = detailCheckupList.get(0);
                if (headerDetailCheckup.getNoCheckup() != null && !"".equalsIgnoreCase(headerDetailCheckup.getNoCheckup())){

                    HeaderCheckup headerCheckup = new HeaderCheckup();
                    headerCheckup.setNoCheckup(headerDetailCheckup.getNoCheckup());

                    List<HeaderCheckup> headerCheckups = new ArrayList<>();
                    try {
                        headerCheckups = checkupBo.getByCriteria(headerCheckup);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
                    }

                    if (headerCheckups.size() > 0){
                        HeaderCheckup checkup = headerCheckups.get(0);

                        detailCheckup.setTarifBpjs(checkup.getTarifBpjs());
                        detailCheckup.setKodeCbg(checkup.getKodeCbg());
                        detailCheckup.setTarifTindakan(totalBiayaTindakan);
                    }
                }
            }
        }

        return detailCheckup;
    }


    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_save";
    }

    @Override
    public String search() {
        logger.info("[CheckupAction.search] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        headerDetailCheckup.setBranchId(CommonUtil.userBranchLogin());
//        if ("".equalsIgnoreCase(headerDetailCheckup.getIdPelayanan()) && headerDetailCheckup.getIdPelayanan() == null){
//            headerDetailCheckup.setIdPelayanan(CommonUtil.userPelayananIdLogin());
//        }

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        String userRoleLogin = CommonUtil.roleAsLogin();
        if (CommonConstant.ROLE_ADMIN_RS.equalsIgnoreCase(userRoleLogin)) {
            setEnabledPoli(true);
        }

        if (CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(userRoleLogin)) {
            setEnabledAddPasien(true);
        }

        if (CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin)) {
            HeaderDetailCheckup headerDetailCheckup1 = new HeaderDetailCheckup();
            headerDetailCheckup1.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            setHeaderDetailCheckup(headerDetailCheckup1);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupDetailAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);
        String userRoleLogin = CommonUtil.roleAsLogin();
        String idPelayanan = CommonUtil.userPelayananIdLogin();

        HeaderDetailCheckup checkupdetail = new HeaderDetailCheckup();
        checkupdetail.setStDateFrom(tglToday);
        checkupdetail.setStDateTo(tglToday);

        if (CommonConstant.ROLE_ADMIN_RS.equalsIgnoreCase(userRoleLogin)) {
            setEnabledPoli(true);
        }

        if (CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin)) {
            checkupdetail.setIdPelayanan(idPelayanan);
        }

        if (CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(userRoleLogin)) {
            checkupdetail.setIdPelayanan(idPelayanan);
            setEnabledAddPasien(true);
        }

        setHeaderDetailCheckup(checkupdetail);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.initForm] end process <<<");

        return "search";
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup) {
        logger.info("[CheckupDetailAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            result = headerCheckupList.get(0);
        }

        logger.info("[CheckupDetailAction.getHeaderCheckup] end process <<<");
        return result;
    }

    public String getListComboDiagnosa() {
        logger.info("[CheckupDetailAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[CheckupDetailAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
    }

    public String getListComboKategoriTindakan() {
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] start process >>>");

        List<KategoriTindakan> kategoriTindakanList = new ArrayList<>();
        KategoriTindakan kategoriTindakan = new KategoriTindakan();

        try {
            kategoriTindakanList = kategoriTindakanBoProxy.getByCriteria(kategoriTindakan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboKategoriTIndakan] Error when get kategori tindakan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKategoriTindakan.addAll(kategoriTindakanList);
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] end process <<<");
        return SUCCESS;
    }

    public List<Tindakan> getListComboTindakan(String idKategoriTindakan) {
        logger.info("[CheckupDetailAction.listOfDokter] start process >>>");

        List<Tindakan> tindakanList = new ArrayList<>();
        Tindakan tindakan = new Tindakan();
        tindakan.setIdKategoriTindakan(idKategoriTindakan);
        if ("ADMIN RS".equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            //tampil semua tindakan
        } else {
            tindakan.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

        try {
            tindakanList = tindakanBo.getComboBoxTindakan(tindakan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupDetailAction.listOfDokter] end process >>>");
        return tindakanList;
    }

    public CrudResponse saveKeterangan(String noCheckup, String idDetailCheckup, String idKtg, String poli, String kelas, String kamar, String idDokter, String ket, String tglCekup, String ketCekup, String jenisPasien, String caraPulang, String pendamping, String tujuan, String idPasien, String metodeBayar, String uangMuka, String jenisBayar) {
        logger.info("[CheckupDetailAction.saveKeterangan] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CrudResponse response = new CrudResponse();

        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
        headerDetailCheckup.setStatusPeriksa("3");
        headerDetailCheckup.setFlag("Y");
        headerDetailCheckup.setAction("U");
        String tglCheckup = tglCekup;

        if (tglCheckup != null && !"".equalsIgnoreCase(tglCheckup)) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tglCheckup);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                headerDetailCheckup.setTglCekup(sqlDate);
            } catch (ParseException e) {
                logger.error("[CheckupDetailAction.saveKeterangan] Error when format string to date for tgl cekup, ", e);
            }
        }

        headerDetailCheckup.setKeteranganCekupUlang(ketCekup);

        if ("selesai".equalsIgnoreCase(idKtg)) {
            headerDetailCheckup.setKeteranganSelesai(ket);
            headerDetailCheckup.setCaraPasienPulang(caraPulang);
            headerDetailCheckup.setPendamping(pendamping);
            headerDetailCheckup.setTempatTujuan(tujuan);
            headerDetailCheckup.setKeteranganCekupUlang(ketCekup);
            headerDetailCheckup.setStatus(idKtg);
            cekRawatInap(idDetailCheckup);
        }
        if ("pindah".equalsIgnoreCase(idKtg)) {
            headerDetailCheckup.setKeteranganSelesai("Pindah ke Poli Lain");
        }
        if ("rujuk".equalsIgnoreCase(idKtg)) {
            headerDetailCheckup.setIdJenisPeriksaPasien(jenisPasien);
            headerDetailCheckup.setKeteranganSelesai("Rujuk Rawat Inap");
        }

        // save approve tindakan
        saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien);

        // create jurnal if non tunai
        if ("non_tunai".equalsIgnoreCase(jenisBayar)) {
            JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, poli, idPasien);
            if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
                response.setMsg(jurnalResponse.getMsg());
                return response;
            } else {
                headerDetailCheckup.setInvoice(jurnalResponse.getInvoice());
            }
        }

        headerDetailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        headerDetailCheckup.setLastUpdateWho(CommonUtil.userLogin());

        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        try {
            checkupDetailBo.saveEdit(headerDetailCheckup);
            response.setStatus("success");
            response.setMsg("Berhasil Menyimpan transaksi");
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, ", e);
            response.setStatus("error");
            response.setMsg("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, "+ e);
            return response;
        }

        if ("pindah".equalsIgnoreCase(idKtg)) {
            pindahPoli(noCheckup, idDetailCheckup, poli, idDokter);
        } else if ("rujuk".equalsIgnoreCase(idKtg)) {
            rujukRawatInap(noCheckup, idDetailCheckup, kelas, kamar, metodeBayar, uangMuka);
        } else {
        }

        updateFlagPeriksaAntrianOnline(idDetailCheckup);

        logger.info("[CheckupDetailAction.saveKeterangan] end process >>>");
        return response;
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idPoli, String idPasien) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setIdPelayanan(idPoli);

        List<Pelayanan> pelayanans = new ArrayList<>();
        try {
            pelayanans = pelayananBo.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error when pelayanan, ", e);
        }

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        if (pelayanans.size() > 0) {
            Pelayanan pelayananData = pelayanans.get(0);

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setStatusBayar("Y");
            List<HeaderDetailCheckup> detailCheckupUangMuka = checkupDetailBo.getListUangPendaftaran(headerDetailCheckup);


            // mencari jumlah um dan no bukti uang muka
            BigDecimal jumlahUm = new BigDecimal(0);
            String idUm = "";
            if (detailCheckupUangMuka.size() > 0) {
                for (HeaderDetailCheckup detailCheckup : detailCheckupUangMuka) {
                    jumlahUm = new BigDecimal(detailCheckup.getJumlahUangMukaDibayar());
                    idUm = detailCheckup.getNoUangMuka();
                }
            }

            // get all sum tindakan, sum resep
            String isResep = checkupDetailBo.findResep(idDetailCheckup);
            BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "resep");
            BigDecimal jumlahTindakan = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "");
            BigDecimal ppnObat = new BigDecimal(0);
            if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            // jumlah tindakan saja. tindakan total - jumlah resep
            jumlahTindakan = jumlahTindakan.subtract(jumlahResep);

            Map mapUangMuka = new HashMap();
            mapUangMuka.put("bukti", idUm);
            mapUangMuka.put("nilai", jumlahUm);

            Map hsCriteria = new HashMap();
            hsCriteria.put("pasien_id", idPasien);
            // jumlah debit uang muka
            hsCriteria.put("uang_muka", mapUangMuka);

            BigDecimal jumlah = new BigDecimal(0);

            if ("Y".equalsIgnoreCase(isResep) || "rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                ketResep = "Dengan Obat";

                // kredit jumlah obat
                hsCriteria.put("pendapatan_obat_non_bpjs", jumlahResep);
                // kredit ppn
                hsCriteria.put("ppn_keluaran", ppnObat);

                // jika ada resep dan ppn untuk debit piutang
                jumlah = jumlah.add(jumlahResep.add(ppnObat));
            } else {
                ketResep = "Tanpa Obat";
            }

            if ("rawat_jalan".equalsIgnoreCase(pelayananData.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRJ";
                ketPoli = "Rawat Jalan";
            }
            if ("rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRI";
                ketPoli = "Rawat Inap";
            }

            // tambahkan jumlah tindakan juga untuk debit piutang
            jumlah = jumlah.add(jumlahTindakan);

            // create invoice nummber
            invoice = billingSystemBo.createInvoiceNumber(kode, branchId);
            if ("JRJ".equalsIgnoreCase(kode)) {

                // create list map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_non_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_jalan_non_bpjs", jumlahTindakan);

                if ("Y".equalsIgnoreCase(isResep)) {
                    transId = "05";
                } else {
                    transId = "04";
                }

            }
            if ("JRI".equalsIgnoreCase(kode)) {

                // create map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_non_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_inap_non_bpjs", jumlahTindakan);
                transId = "07";
            }

            String catatan = "Closing Pasien "+ketPoli+" Umum "+ketResep+" Piutang No Pasien "+idPasien;

            try {
                billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                response.setStatus("success");
                response.setMsg("[Berhasil]");
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
                return response;
            }
        }

        response.setInvoice(invoice);
        return response;
    }

    private JurnalResponse closingJurnalNonTunaiBpjs(String idDetailCheckup, String idPoli, String idPasien) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setIdPelayanan(idPoli);

        List<Pelayanan> pelayanans = new ArrayList<>();
        try {
            pelayanans = pelayananBo.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error when pelayanan, ", e);
        }

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        if (pelayanans.size() > 0) {
            Pelayanan pelayananData = pelayanans.get(0);

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setStatusBayar("Y");
            List<HeaderDetailCheckup> detailCheckupUangMuka = checkupDetailBo.getListUangPendaftaran(headerDetailCheckup);


            // mencari jumlah um dan no bukti uang muka
            BigDecimal jumlahUm = new BigDecimal(0);
            String idUm = "";
            if (detailCheckupUangMuka.size() > 0) {
                for (HeaderDetailCheckup detailCheckup : detailCheckupUangMuka) {
                    jumlahUm = new BigDecimal(detailCheckup.getJumlahUangMukaDibayar());
                    idUm = detailCheckup.getNoUangMuka();
                }
            }

            // get all sum tindakan, sum resep
            String isResep = checkupDetailBo.findResep(idDetailCheckup);
            BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "resep");
            BigDecimal jumlahTindakan = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "");
            BigDecimal ppnObat = new BigDecimal(0);
            if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            // jumlah tindakan saja. tindakan total - jumlah resep
            jumlahTindakan = jumlahTindakan.subtract(jumlahResep);


            Map hsCriteria = new HashMap();
            hsCriteria.put("pasien_id", idPasien);

            BigDecimal jumlah = new BigDecimal(0);

            if ("Y".equalsIgnoreCase(isResep) || "rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                ketResep = "Dengan Obat";

                // kredit jumlah obat
                hsCriteria.put("pendapatan_obat_bpjs", jumlahResep);
                // kredit ppn
                hsCriteria.put("ppn_keluaran", ppnObat);

                // jika ada resep dan ppn untuk debit piutang
                jumlah = jumlah.add(jumlahResep.add(ppnObat));
            } else {
                ketResep = "Tanpa Obat";
            }

            if ("rawat_jalan".equalsIgnoreCase(pelayananData.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRJ";
                ketPoli = "Rawat Jalan";
            }
            if ("rawat_inap".equalsIgnoreCase(pelayananData.getTipePelayanan())) {
                kode = "JRI";
                ketPoli = "Rawat Inap";
            }

            // tambahkan jumlah tindakan juga untuk debit piutang
            jumlah = jumlah.add(jumlahTindakan);

            // create invoice nummber
            invoice = billingSystemBo.createInvoiceNumber(kode, branchId);
            if ("JRJ".equalsIgnoreCase(kode)) {

                // create list map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_jalan_bpjs", jumlahTindakan);

                if ("Y".equalsIgnoreCase(isResep)) {
                    transId = "03";
                } else {
                    transId = "02";
                }

            }
            if ("JRI".equalsIgnoreCase(kode)) {

                // create map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));

                // debit piutang pasien
                hsCriteria.put("piutang_pasien_bpjs", mapPiutang);

                // kredit jumlah tindakan
                hsCriteria.put("pendapatan_rawat_inap_bpjs", jumlahTindakan);
                transId = "06";
            }

            String catatan = "Closing Pasien "+ketPoli+" BPJS "+ketResep+" Piutang No Pasien "+idPasien;

            try {
                billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                response.setStatus("success");
                response.setMsg("[Berhasil]");
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
                return response;
            }
        }

        response.setInvoice(invoice);
        return response;
    }



//    private String getInvoiceNumber(String transId){ return billingSystemBoProxy.createInvoiceNumber(transId);}

    private void pindahPoli(String noCheckup, String idDetailCheckup, String idPoli, String idDokter) {
        logger.info("[CheckupDetailAction.pindahPoli] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();

        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(idPoli) && !"".equalsIgnoreCase(idDokter)) {

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            String genNoSep = "";


            HeaderCheckup checkup = new HeaderCheckup();
            checkup.setNoCheckup(noCheckup);

            List<HeaderCheckup> headerCheckupList = new ArrayList<>();

            try {
                headerCheckupList = checkupBo.getByCriteria(checkup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
            }

            if (!headerCheckupList.isEmpty()) {

                checkup = headerCheckupList.get(0);

                DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
                List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

                try {
                    diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                } catch (GeneralBOException e) {
                    logger.error("[Foun Error] when search diagnosa awal " + e);
                }

                if (diagnosaRawatList.size() > 0) {
                    diagnosaRawat = diagnosaRawatList.get(0);
                }

                if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

                    List<Pasien> pasienList = new ArrayList<>();
                    Pasien getPasien = new Pasien();
                    getPasien.setIdPasien(checkup.getIdPasien());
                    getPasien.setFlag("Y");

                    try {
                        pasienList = pasienBo.getByCriteria(getPasien);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                    }

                    if (!pasienList.isEmpty()) {
                        getPasien = pasienList.get(0);

                        if (getPasien != null) {

                            SepRequest sepRequest = new SepRequest();
                            sepRequest.setNoKartu(getPasien.getNoBpjs());
                            sepRequest.setTglSep(now.toString());
                            sepRequest.setPpkPelayanan("1311R003");//cons id rumah sakit
                            sepRequest.setJnsPelayanan("2");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                            sepRequest.setKlsRawat("3");//kelas rawat dari bpjs
                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                            sepRequest.setAsalRujukan("1");//
                            sepRequest.setTglRujukan(checkup.getTglRujukan());
                            sepRequest.setNoRujukan(checkup.getNoRujukan());
                            sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                            sepRequest.setCatatan("");
                            sepRequest.setDiagAwal(diagnosaRawat.getIdDiagnosa());
                            sepRequest.setPoliTujuan("IGD");
                            sepRequest.setPoliEksekutif("0");
                            sepRequest.setCob("0");
                            sepRequest.setKatarak("0");
                            sepRequest.setLakaLantas("0");
                            sepRequest.setPenjamin("");
                            sepRequest.setTglKejadian("");
                            sepRequest.setKeterangan("");
                            sepRequest.setSuplesi("0");
                            sepRequest.setNoSepSuplesi("");
                            sepRequest.setKdProvinsiLakaLantas("");
                            sepRequest.setKdKecamatanLakaLantas("");
                            sepRequest.setKdKabupatenLakaLantas("");
                            sepRequest.setNoSuratSkdp("000002");
                            sepRequest.setKodeDpjp("31661");
                            sepRequest.setNoTelp(getPasien.getNoTelp());
                            sepRequest.setUserPembuatSep(user);

                            SepResponse response = new SepResponse();

                            try {
                                response = bpjsBo.insertSepBpjs(sepRequest, branchId);
                            } catch (Exception e) {
                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                            }

                            if (response.getNoSep() != null) {

                                genNoSep = response.getNoSep();
                                logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                                KlaimRequest klaimRequest = new KlaimRequest();
                                klaimRequest.setNoSep(genNoSep);
                                klaimRequest.setNoKartu(getPasien.getNoBpjs());
                                klaimRequest.setNoRm(getPasien.getIdPasien());
                                klaimRequest.setNamaPasien(getPasien.getNama());
                                klaimRequest.setTglLahir(getPasien.getTglLahir());
                                klaimRequest.setGender(getPasien.getJenisKelamin());

                                KlaimResponse responseNewClaim = new KlaimResponse();
                                try {
                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, branchId);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                }

                                List<Tindakan> tindakanList = new ArrayList<>();
                                Tindakan tindakan = new Tindakan();
                                tindakan.setIdTindakan("03");

                                try {
                                    tindakanList = tindakanBo.getByCriteria(tindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                }

                                if (tindakanList.size() > 0) {
                                    tindakan = tindakanList.get(0);
                                }

                                if (responseNewClaim.getPatientId() != null) {
                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                    klaimDetailRequest.setNomorSep(genNoSep);
                                    klaimDetailRequest.setNomorKartu(getPasien.getNoBpjs());
                                    klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                    klaimDetailRequest.setTglPulang(now.toString());
                                    klaimDetailRequest.setJenisRawat("2");
                                    klaimDetailRequest.setKelasRawat("");
                                    klaimDetailRequest.setAdlChronic("");
                                    klaimDetailRequest.setIcuIndikator("");
                                    klaimDetailRequest.setIcuLos("");
                                    klaimDetailRequest.setVentilatorHour("");
                                    klaimDetailRequest.setUpgradeClassInd("");
                                    klaimDetailRequest.setUpgradeClassClass("");
                                    klaimDetailRequest.setUpgradeClassLos("");
                                    klaimDetailRequest.setAddPaymentPct("");
                                    klaimDetailRequest.setBirthWeight("0");
                                    klaimDetailRequest.setDischargeStatus("1");
                                    klaimDetailRequest.setDiagnosa(diagnosaRawat.getIdDiagnosa());
                                    klaimDetailRequest.setProcedure("");

                                    klaimDetailRequest.setTarifRsNonBedah("");
                                    klaimDetailRequest.setTarifRsProsedurBedah("");

                                    klaimDetailRequest.setTarifRsKonsultasi(tindakan.getTarifBpjs().toString());
                                    klaimDetailRequest.setTarifRsTenagaAhli("");
                                    klaimDetailRequest.setTarifRsKeperawatan("");
                                    klaimDetailRequest.setTarifRsPenunjang("");
                                    klaimDetailRequest.setTarifRsRadiologi("");
                                    klaimDetailRequest.setTarifRsLaboratorium("");
                                    klaimDetailRequest.setTarifRsPelayananDarah("");
                                    klaimDetailRequest.setTarifRsRehabilitasi("");
                                    klaimDetailRequest.setTarifRsKamar("");
                                    klaimDetailRequest.setTarifRsRawatIntensif("");
                                    klaimDetailRequest.setTarifRsObat("");
                                    klaimDetailRequest.setTarifRsObatKronis("");
                                    klaimDetailRequest.setTarifRsObatKemoterapi("");
                                    klaimDetailRequest.setTarifRsAlkes("");
                                    klaimDetailRequest.setTarifRsBmhp("");
                                    klaimDetailRequest.setTarifRsSewaAlat("");

                                    List<DokterTeam> dokterList = new ArrayList<>();
                                    DokterTeam dokterTeam = new DokterTeam();
                                    dokterTeam.setIdDetailCheckup(idDetailCheckup);

                                    try {
                                        dokterList = teamDokterBo.getByCriteria(dokterTeam);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                    }

                                    String namaDokter = "";

                                    if (dokterList.size() > 0) {
                                        dokterTeam = dokterList.get(0);

                                        List<Dokter> dokterArrayList = new ArrayList<>();
                                        Dokter dokter = new Dokter();
                                        dokter.setIdDokter(dokterTeam.getIdDokter());
                                        dokter.setFlag("Y");

                                        try {
                                            dokterArrayList = dokterBo.getByCriteria(dokter);
                                        } catch (GeneralBOException e) {
                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                        }

                                        if (dokterArrayList.size() > 0) {
                                            namaDokter = dokterArrayList.get(0).getNamaDokter();
                                        }

                                    }

                                    klaimDetailRequest.setTarifPoliEks("");
                                    klaimDetailRequest.setNamaDokter(namaDokter);
                                    klaimDetailRequest.setKodeTarif("AP");
                                    klaimDetailRequest.setTarifRsPayorId("3");
                                    klaimDetailRequest.setPayorCd("JKN");
                                    klaimDetailRequest.setCobCd("");
                                    klaimDetailRequest.setCoderNik("123456");

                                    KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                    try {
                                        claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, branchId);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                    }

                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                        Grouping1Response grouping1Response = new Grouping1Response();

                                        try {
                                            grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, branchId);
                                        } catch (GeneralBOException e) {
                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                        }

                                        // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                        if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                            BigDecimal tarifCbg = new BigDecimal(0);
                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {
                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());
                                                }
                                            }

                                            //=====START SET TARIF BPJS DARI E-KLAIM====

                                            headerDetailCheckup.setTarifBpjs(tarifCbg);

                                            //=====END SET TARIF BPJS DARI E-KLAIM=====

                                            // jika ada special cmg maka proses grouping stage 2
                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                    try {
                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), branchId);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);

                                                    }
                                                }
                                            }
                                        }

                                    } else {
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }

                headerDetailCheckup.setNoCheckup(noCheckup);
                headerDetailCheckup.setIdPelayanan(idPoli);
                headerDetailCheckup.setStatusPeriksa("1");
                headerDetailCheckup.setCreatedDate(now);
                headerDetailCheckup.setCreatedWho(user);
                headerDetailCheckup.setLastUpdate(now);
                headerDetailCheckup.setLastUpdateWho(user);
                headerDetailCheckup.setIdDokter(idDokter);
                headerDetailCheckup.setNoSep(genNoSep);
//                headerDetailCheckup.setNoNota(createJurnalUangMuka(checkup.getIdPasien(), "0"));

                try {
                    checkupDetailBo.saveAdd(headerDetailCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.pindahPoli] Error when saving add new detail poli, ", e);
                }
            }
        }
        logger.info("[CheckupDetailAction.pindahPoli] end process >>>");
    }

    private void rujukRawatInap(String noCheckup, String idDetailCheckup, String kelas, String kamar, String metodeBayar, String uangMuka) {
        logger.info("[CheckupDetailAction.rujukRawatInap] start process >>>");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        String genNoSep = "";

        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(idDetailCheckup)) {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
            BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
            PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
            DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
            TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
            PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

            List<Branch> branchList = new ArrayList<>();
            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            try {
                detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.rujukRawatInap] Error when geting data detail poli, ", e);
            }

            if (!detailCheckupList.isEmpty()) {

                detailCheckup = detailCheckupList.get(0);

                if (detailCheckup != null) {

                    HeaderCheckup checkup = new HeaderCheckup();
                    checkup.setNoCheckup(noCheckup);

                    List<HeaderCheckup> headerCheckupList = new ArrayList<>();

                    try {
                        headerCheckupList = checkupBo.getByCriteria(checkup);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
                    }

                    if (!headerCheckupList.isEmpty()) {
                        checkup = headerCheckupList.get(0);

                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

                        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                        diagnosaRawat.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

                        try {
                            diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                        } catch (GeneralBOException e) {
                            logger.error("[Foun Error] when search diagnosa awal " + e);
                        }

                        if (diagnosaRawatList.size() > 0) {
                            diagnosaRawat = diagnosaRawatList.get(0);
                        }

                        List<Pelayanan> pelayananList = new ArrayList<>();
                        Pelayanan pelayanan = new Pelayanan();
                        pelayanan.setTipePelayanan("rawat_inap");
                        pelayanan.setBranchId(CommonUtil.userBranchLogin());

                        try {
                            pelayananList = pelayananBo.getByCriteria(pelayanan);
                        } catch (GeneralBOException e) {
                            logger.error("[Found Error] when search pelayanan " + e.getMessage());
                        }

                        if (pelayananList.size() > 0) {
                            pelayanan = pelayananList.get(0);
                        }

                        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

                            Branch branch = new Branch();
                            branch.setBranchId(branchId);
                            branch.setFlag("Y");

                            try {
                                branchList = branchBo.getByCriteria(branch);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                            }

                            Branch getBranch = new Branch();

                            if (branchList.size() > 0) {
                                getBranch = branchList.get(0);

                                if (getBranch.getPpkPelayanan() != null) {
                                    List<Pasien> pasienList = new ArrayList<>();
                                    Pasien getPasien = new Pasien();
                                    getPasien.setIdPasien(checkup.getIdPasien());
                                    getPasien.setFlag("Y");

                                    try {
                                        pasienList = pasienBo.getByCriteria(getPasien);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                    }

                                    if (!pasienList.isEmpty()) {
                                        getPasien = pasienList.get(0);

                                        if (getPasien != null) {

                                            SepRequest sepRequest = new SepRequest();
                                            sepRequest.setNoKartu(getPasien.getNoBpjs());
                                            sepRequest.setTglSep(now.toString());
                                            sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                            sepRequest.setJnsPelayanan("2");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                            sepRequest.setKlsRawat("3");//kelas rawat dari bpjs
                                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                            sepRequest.setAsalRujukan("1");//
                                            sepRequest.setTglRujukan(checkup.getTglRujukan());
                                            sepRequest.setNoRujukan(checkup.getNoRujukan());
                                            sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                                            sepRequest.setCatatan("");
                                            sepRequest.setDiagAwal(diagnosaRawat.getIdDiagnosa());
                                            sepRequest.setPoliTujuan("IGD");
                                            sepRequest.setPoliEksekutif("0");
                                            sepRequest.setCob("0");
                                            sepRequest.setKatarak("0");
                                            sepRequest.setLakaLantas("0");
                                            sepRequest.setPenjamin("");
                                            sepRequest.setTglKejadian("");
                                            sepRequest.setKeterangan("");
                                            sepRequest.setSuplesi("0");
                                            sepRequest.setNoSepSuplesi("");
                                            sepRequest.setKdProvinsiLakaLantas("");
                                            sepRequest.setKdKecamatanLakaLantas("");
                                            sepRequest.setKdKabupatenLakaLantas("");
                                            sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp());
                                            sepRequest.setKodeDpjp("31661");
                                            sepRequest.setNoTelp(getPasien.getNoTelp());
                                            sepRequest.setUserPembuatSep(user);

                                            SepResponse response = new SepResponse();

                                            try {
                                                response = bpjsBo.insertSepBpjs(sepRequest, branchId);
                                            } catch (Exception e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (response.getNoSep() != null) {

                                                genNoSep = response.getNoSep();
                                                logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                                                KlaimRequest klaimRequest = new KlaimRequest();
                                                klaimRequest.setNoSep(genNoSep);
                                                klaimRequest.setNoKartu(getPasien.getNoBpjs());
                                                klaimRequest.setNoRm(getPasien.getIdPasien());
                                                klaimRequest.setNamaPasien(getPasien.getNama());
                                                klaimRequest.setTglLahir(getPasien.getTglLahir());
                                                String jk = "";

                                                if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                                                    jk = "1";
                                                } else {
                                                    jk = "2";
                                                }
                                                klaimRequest.setGender(jk);

                                                KlaimResponse responseNewClaim = new KlaimResponse();
                                                try {
                                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, branchId);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setIdTindakan("03");

                                                try {
                                                    tindakanList = tindakanBo.getByCriteria(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                if (tindakanList.size() > 0) {
                                                    tindakan = tindakanList.get(0);
                                                }

                                                if (responseNewClaim.getPatientId() != null) {
                                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                                    klaimDetailRequest.setNomorSep(genNoSep);
                                                    klaimDetailRequest.setNomorKartu(getPasien.getNoBpjs());
                                                    klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                                    klaimDetailRequest.setTglPulang(now.toString());
                                                    klaimDetailRequest.setJenisRawat("1");
                                                    klaimDetailRequest.setKelasRawat("");
                                                    klaimDetailRequest.setAdlChronic("");
                                                    klaimDetailRequest.setIcuIndikator("");
                                                    klaimDetailRequest.setIcuLos("");
                                                    klaimDetailRequest.setVentilatorHour("");
                                                    klaimDetailRequest.setUpgradeClassInd("");
                                                    klaimDetailRequest.setUpgradeClassClass("");
                                                    klaimDetailRequest.setUpgradeClassLos("");
                                                    klaimDetailRequest.setAddPaymentPct("");
                                                    klaimDetailRequest.setBirthWeight("0");
                                                    klaimDetailRequest.setDischargeStatus("1");
                                                    klaimDetailRequest.setDiagnosa(diagnosaRawat.getIdDiagnosa());
                                                    klaimDetailRequest.setProcedure("");

                                                    klaimDetailRequest.setTarifRsNonBedah("");
                                                    klaimDetailRequest.setTarifRsProsedurBedah("");

                                                    klaimDetailRequest.setTarifRsKonsultasi(tindakan.getTarifBpjs().toString());
                                                    klaimDetailRequest.setTarifRsTenagaAhli("");
                                                    klaimDetailRequest.setTarifRsKeperawatan("");
                                                    klaimDetailRequest.setTarifRsPenunjang("");
                                                    klaimDetailRequest.setTarifRsRadiologi("");
                                                    klaimDetailRequest.setTarifRsLaboratorium("");
                                                    klaimDetailRequest.setTarifRsPelayananDarah("");
                                                    klaimDetailRequest.setTarifRsRehabilitasi("");
                                                    klaimDetailRequest.setTarifRsKamar("");
                                                    klaimDetailRequest.setTarifRsRawatIntensif("");
                                                    klaimDetailRequest.setTarifRsObat("");
                                                    klaimDetailRequest.setTarifRsObatKronis("");
                                                    klaimDetailRequest.setTarifRsObatKemoterapi("");
                                                    klaimDetailRequest.setTarifRsAlkes("");
                                                    klaimDetailRequest.setTarifRsBmhp("");
                                                    klaimDetailRequest.setTarifRsSewaAlat("");

                                                    List<DokterTeam> dokterList = new ArrayList<>();
                                                    DokterTeam dokterTeam = new DokterTeam();
                                                    dokterTeam.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());

                                                    try {
                                                        dokterList = teamDokterBo.getByCriteria(dokterTeam);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    String namaDokter = "";

                                                    if (dokterList.size() > 0) {
                                                        dokterTeam = dokterList.get(0);

                                                        List<Dokter> dokterArrayList = new ArrayList<>();
                                                        Dokter dokter = new Dokter();
                                                        dokter.setIdDokter(dokterTeam.getIdDokter());
                                                        dokter.setFlag("Y");

                                                        try {
                                                            dokterArrayList = dokterBo.getByCriteria(dokter);
                                                        } catch (GeneralBOException e) {
                                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                        }

                                                        if (dokterArrayList.size() > 0) {
                                                            namaDokter = dokterArrayList.get(0).getNamaDokter();
                                                        }

                                                    }

                                                    klaimDetailRequest.setTarifPoliEks("");
                                                    klaimDetailRequest.setNamaDokter(namaDokter);
                                                    klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif());
                                                    klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId());
                                                    klaimDetailRequest.setPayorCd(getBranch.getPayorCd());
                                                    klaimDetailRequest.setCobCd("");
                                                    klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                                    KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                                    try {
                                                        claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, branchId);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                                        Grouping1Response grouping1Response = new Grouping1Response();

                                                        try {
                                                            grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, branchId);
                                                        } catch (GeneralBOException e) {
                                                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                        }

                                                        // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                                        if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                                            BigDecimal tarifCbg = new BigDecimal(0);
                                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {
                                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());
                                                                }
                                                            }

                                                            //=====START SET TARIF BPJS DARI E-KLAIM====

                                                            headerDetailCheckup.setTarifBpjs(tarifCbg);

                                                            //=====END SET TARIF BPJS DARI E-KLAIM=====

                                                            // jika ada special cmg maka proses grouping stage 2
                                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                                    try {
                                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), branchId);
                                                                    } catch (GeneralBOException e) {
                                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);

                                                                    }
                                                                }
                                                            }
                                                        }

                                                    } else {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    logger.error("Found Error when search branch id");
                                }
                            }
                        }

                        Tindakan tindakan = new Tindakan();
                        tindakan.setIdTindakan("03");

                        List<Tindakan> tindakans = new ArrayList<>();
                        tindakans.add(tindakan);

                        headerDetailCheckup.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                        headerDetailCheckup.setNoCheckup(noCheckup);
                        headerDetailCheckup.setIdPelayanan(pelayanan.getIdPelayanan());
                        headerDetailCheckup.setIdRuangan(kamar);
                        headerDetailCheckup.setStatusPeriksa("1");
                        headerDetailCheckup.setCreatedDate(now);
                        headerDetailCheckup.setCreatedWho(user);
                        headerDetailCheckup.setLastUpdate(now);
                        headerDetailCheckup.setLastUpdateWho(user);
                        headerDetailCheckup.setRawatInap(true);
                        headerDetailCheckup.setNoSep(genNoSep);
                        headerDetailCheckup.setTindakanList(tindakans);
                        headerDetailCheckup.setIdJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());

                        if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                            headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                        }

                        headerDetailCheckup.setMetodePembayaran(metodeBayar);

                        try {
                            checkupDetailBo.saveAdd(headerDetailCheckup);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.rujukRawatInap] Error when saving add new detail poli, ", e);
                        }
                    }
                }
            }
        }
        logger.info("[CheckupDetailAction.rujukRawatInap] end process >>>");

    }

//    private String createJurnalUangMuka(String idPasien, String jumlah){
//        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
//        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
//
//        String transId = "01";
//
//        String noNota = "";
//        try {
//            noNota = billingSystemBo.createInvoiceNumber(transId);
//        } catch (GeneralBOException e){
//            logger.error("[CheckupDetailAction.createJurnalUangMuka] Error create uang muka, ", e);
//        }
//
//        Map hsCriteria = new HashMap();
//        hsCriteria.put("master_id", idPasien);
//        hsCriteria.put("no_nota", noNota);
//        hsCriteria.put("uang_muka", new BigDecimal(jumlah));
//
//        try {
//            billingSystemBo.createJurnal(transId, hsCriteria, CommonUtil.userBranchLogin(), "Uang Muka "+idPasien, "Y", "");
//        } catch (GeneralBOException e){
//            logger.error("[CheckupDetailAction.createJurnalUangMuka] Error create uang muka, ", e);
//        }
//
//        return noNota;
//    }

    private void cekRawatInap(String idDetailCheckup) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");

        List<RawatInap> rawatInapList = new ArrayList<>();
        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        try {
            rawatInapList = rawatInapBo.getByCriteria(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search rawat inap " + e.getMessage());
        }

        RawatInap rawat = new RawatInap();
        if (rawatInapList.size() > 0) {
            rawat = rawatInapList.get(0);

            if (rawat.getIdRuangan() != null && !"".equalsIgnoreCase(rawat.getIdRuangan())) {
                CheckResponse response = new CheckResponse();
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(rawat.getIdRuangan());

                try {
                    response = ruanganBo.updateRuangan(ruangan);
                } catch (HibernateException e) {
                    logger.error("Found error" + e.getMessage());
                }
            }
        }

    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa) {
        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()) {
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[CheckupDetailAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String getListComboKelasRuangan() {
        logger.info("[CheckupDetailAction.getListComboKelasRuangan] start process >>>");

        List<KelasRuangan> kelasRuanganList = new ArrayList<>();
        KelasRuangan kelasRuangan = new KelasRuangan();

        try {
            kelasRuanganList = kelasRuanganBoProxy.getByCriteria(kelasRuangan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboKelasRuangan] Error when get kelas ruangan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKelasRuangan.addAll(kelasRuanganList);
        logger.info("[CheckupDetailAction.getListComboKelasRuangan] end process <<<");
        return SUCCESS;
    }

    public List<Ruangan> listRuangan(String idkelas, boolean flag) {

        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<Ruangan> ruanganList = new ArrayList<>();
        Ruangan ruangan = new Ruangan();
        if (flag) {
            ruangan.setStatusRuangan("Y");
            ruangan.setSisaKuota(0);
        }
        ruangan.setIdKelasRuangan(idkelas);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");

        try {
            ruanganList = ruanganBo.getByCriteria(ruangan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        return ruanganList;

    }

    public String getListComboKeteranganKeluar() {
        logger.info("[CheckupDetailAction.getListComboKeteranganKeluar] start process >>>");

        List<KeteranganKeluar> keteranganKeluarList = new ArrayList<>();
        KeteranganKeluar keteranganKeluar = new KeteranganKeluar();

        try {
            keteranganKeluarList = keteranganKeluarBoProxy.getByCriteria(keteranganKeluar);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboKeteranganKeluar] Error when get keterangan keluar ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKeterangan.addAll(keteranganKeluarList);
        logger.info("[CheckupDetailAction.getListComboKeteranganKeluar] end process <<<");
        return SUCCESS;
    }

    public String saveUpdateRuangan(String idRuangan, String idDetailCheckup) {
        logger.info("[CheckupDetailAction.saveUpdateRuangan] start process >>>");

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan) && idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            try {
                checkupDetailBo.updateRuanganInap(idRuangan, idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveUpdateRuangan] Found problem when updating rawat inap, please inform to your admin.", e);
                return "ERROR, " + e.getMessage();
            }
        } else {
            return "ERROR, idRuangan OR idDetailCheckup Is NULL";
        }

        logger.info("[CheckupDetailAction.saveUpdateRuangan] end process >>>");
        return "SUCCESS";
    }

    public List<RawatInap> getListRuangInapByIdDetailCheckup(String idDetailCheckup) {
        logger.info("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] start process >>>");

        List<RawatInap> rawatInaps = new ArrayList<>();

        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInaps = rawatInapBo.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] Error when get data ruangan.", e);
            addActionError("Error Found problem when get  Error when get data ruangan , please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] end process >>>");
        return rawatInaps;
    }

    public String addRawatIgd() {

        logger.info("[CheckupDetailAction.add] start process >>>");

        // tipe transaksi
        String tipe = getTipe();
        setTipe(tipe);

        HeaderCheckup checkup = new HeaderCheckup();

        if (CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            checkup.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        checkup.setJenisTransaksi(tipe);
        setHeaderCheckup(checkup);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");


        logger.info("[CheckupDetailAction.add] end process <<<");

        return "init_daftar";
    }

    public String saveAddRawatIgd() {

        logger.info("[CheckupDetailAction.saveAdd] start process >>>");
        HeaderCheckup checkup = getHeaderCheckup();
        String genNoSep = "";
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String noCheckup = "CKP" + checkupBoProxy.getNextHeaderId();
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        //jika bpjs
        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

            List<Pasien> pasienList = new ArrayList<>();
            List<Branch> branchList = new ArrayList<>();
            Pasien pasien = new Pasien();
            pasien.setIdPasien(checkup.getIdPasien());
            pasien.setFlag("Y");

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                Long logId = null;
                logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when search id pasien, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            Branch branch = new Branch();
            branch.setBranchId(userArea);
            branch.setFlag("Y");

            try {
                branchList = branchBoProxy.getByCriteria(branch);
            } catch (GeneralBOException e) {
                Long logId = null;
                logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when search id pasien, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

            Branch getBranch = new Branch();

            if (branchList.size() > 0) {
                getBranch = branchList.get(0);

                if (getBranch.getPpkPelayanan() != null) {
                    if (pasienList.size() > 0) {

                        Pasien getPasien = pasienList.get(0);

                        SepRequest sepRequest = new SepRequest();
                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                        sepRequest.setTglSep(dateToday);
                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                        sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
                        sepRequest.setKlsRawat(checkup.getKelasPasien());//kelas rawat dari bpjs
                        sepRequest.setNoMr(checkup.getIdPasien());//id pasien
//                        sepRequest.setAsalRujukan("1");//
//                        sepRequest.setTglRujukan(checkup.getTglRujukan());
//                        sepRequest.setNoRujukan(checkup.getNoRujukan());
//                        sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());

                        sepRequest.setAsalRujukan("2");//
                        sepRequest.setTglRujukan("");
                        sepRequest.setNoRujukan("");
                        sepRequest.setPpkRujukan("");
                        sepRequest.setCatatan("");
                        sepRequest.setDiagAwal(checkup.getDiagnosa());
                        sepRequest.setPoliTujuan("IGD");
                        sepRequest.setPoliEksekutif("0");
                        sepRequest.setCob("0");
                        sepRequest.setKatarak("0");
                        sepRequest.setLakaLantas("0");
                        sepRequest.setPenjamin("");
                        sepRequest.setTglKejadian("");
                        sepRequest.setKeterangan("");
                        sepRequest.setSuplesi("0");
                        sepRequest.setNoSepSuplesi("");
                        sepRequest.setKdProvinsiLakaLantas("");
                        sepRequest.setKdKecamatanLakaLantas("");
                        sepRequest.setKdKabupatenLakaLantas("");
                        sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp());
                        sepRequest.setKodeDpjp("31661");
                        sepRequest.setNoTelp(getPasien.getNoTelp());
                        sepRequest.setUserPembuatSep(userLogin);

                        SepResponse response = new SepResponse();

                        try {
                            response = bpjsBoProxy.insertSepBpjs(sepRequest, userArea);
                        } catch (Exception e) {
                            Long logId = null;
                            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                            addActionError("Error, " + "[code=" + logId + "] Found problem when insert SEP.\n" + e.getMessage());
                            return ERROR;
                        }

                        if (response.getNoSep() != null) {

                            genNoSep = response.getNoSep();
                            logger.info("[CheckupAction.saveAdd] NO. SEP : " + genNoSep);

                            KlaimRequest klaimRequest = new KlaimRequest();
                            klaimRequest.setNoSep(genNoSep);
                            klaimRequest.setNoKartu(getPasien.getNoBpjs());
                            klaimRequest.setNoRm(getPasien.getIdPasien());
                            klaimRequest.setNamaPasien(getPasien.getNama());
                            klaimRequest.setTglLahir(getPasien.getTglLahir());
                            String jk = "";

                            if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                                jk = "1";
                            } else {
                                jk = "2";
                            }
                            klaimRequest.setGender(jk);

                            KlaimResponse responseNewClaim = new KlaimResponse();
                            try {
                                responseNewClaim = eklaimBoProxy.insertNewClaimEklaim(klaimRequest, userArea);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                return ERROR;
                            }

                            List<Tindakan> tindakanList = new ArrayList<>();
                            Tindakan tindakan = new Tindakan();
                            tindakan.setIdTindakan("03");

                            try {
                                tindakanList = tindakanBoProxy.getByCriteria(tindakan);
                            } catch (GeneralBOException e) {
                                Long logId = null;
                                logger.error("[CheckupAction.saveAdd] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                addActionError("Error, " + "[code=" + logId + "] Found problem when search tindakan, please inform to your admin.\n" + e.getMessage());
                                return ERROR;
                            }

                            if (tindakanList.size() > 0) {
                                tindakan = tindakanList.get(0);
                            }

                            if (responseNewClaim.getPatientId() != null) {
                                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                klaimDetailRequest.setNomorSep(genNoSep);
                                klaimDetailRequest.setNomorKartu(getPasien.getNoKtp());
                                klaimDetailRequest.setTglMasuk(updateTime.toString());
                                klaimDetailRequest.setTglPulang(updateTime.toString());
                                klaimDetailRequest.setJenisRawat("2");
                                klaimDetailRequest.setKelasRawat(checkup.getKelasPasien());
                                klaimDetailRequest.setAdlChronic("");
                                klaimDetailRequest.setIcuIndikator("");
                                klaimDetailRequest.setIcuLos("");
                                klaimDetailRequest.setVentilatorHour("");
                                klaimDetailRequest.setUpgradeClassInd("");
                                klaimDetailRequest.setUpgradeClassClass("");
                                klaimDetailRequest.setUpgradeClassLos("");
                                klaimDetailRequest.setAddPaymentPct("");
                                klaimDetailRequest.setBirthWeight("0");
                                klaimDetailRequest.setDischargeStatus("1");
                                klaimDetailRequest.setDiagnosa(checkup.getDiagnosa());
                                klaimDetailRequest.setProcedure("");

                                klaimDetailRequest.setTarifRsNonBedah("");
                                klaimDetailRequest.setTarifRsProsedurBedah("");

                                klaimDetailRequest.setTarifRsKonsultasi(tindakan.getTarifBpjs().toString());
                                klaimDetailRequest.setTarifRsTenagaAhli("");
                                klaimDetailRequest.setTarifRsKeperawatan("");
                                klaimDetailRequest.setTarifRsPenunjang("");
                                klaimDetailRequest.setTarifRsRadiologi("");
                                klaimDetailRequest.setTarifRsLaboratorium("");
                                klaimDetailRequest.setTarifRsPelayananDarah("");
                                klaimDetailRequest.setTarifRsRehabilitasi("");
                                klaimDetailRequest.setTarifRsKamar("");
                                klaimDetailRequest.setTarifRsRawatIntensif("");
                                klaimDetailRequest.setTarifRsObat("");
                                klaimDetailRequest.setTarifRsObatKronis("");
                                klaimDetailRequest.setTarifRsObatKemoterapi("");
                                klaimDetailRequest.setTarifRsAlkes("");
                                klaimDetailRequest.setTarifRsBmhp("");
                                klaimDetailRequest.setTarifRsSewaAlat("");

                                List<Dokter> dokterList = new ArrayList<>();
                                Dokter dokter = new Dokter();
                                dokter.setIdDokter(checkup.getIdDokter());
                                dokter.setFlag("Y");
                                try {
                                    dokterList = dokterBoProxy.getByCriteria(dokter);
                                } catch (GeneralBOException e) {
                                    Long logId = null;
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                    return ERROR;
                                }

                                String namaDokter = "";
                                if (dokterList.size() > 0) {
                                    namaDokter = dokterList.get(0).getNamaDokter();
                                }

                                klaimDetailRequest.setTarifPoliEks("");
                                klaimDetailRequest.setNamaDokter(namaDokter);
                                klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif());
                                klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId());
                                klaimDetailRequest.setPayorCd(getBranch.getPayorCd());
                                klaimDetailRequest.setCobCd("");
                                klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                try {
                                    claimEklaimResponse = eklaimBoProxy.updateDataClaimEklaim(klaimDetailRequest, userArea);
                                } catch (GeneralBOException e) {
                                    Long logId = null;
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                    return ERROR;
                                }

                                if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                    Grouping1Response grouping1Response = new Grouping1Response();

                                    try {
                                        grouping1Response = eklaimBoProxy.groupingStage1Eklaim(genNoSep, userArea);
                                    } catch (GeneralBOException e) {
                                        Long logId = null;
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                        return ERROR;
                                    }

                                    // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                    if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                        BigDecimal tarifCbg = new BigDecimal(0);
                                        if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                            if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {
                                                tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());
                                            }
                                        }

                                        //======START SET TARIF BPJS=========

                                        checkup.setTarifBpjs(tarifCbg);

                                        //======END SET TARIF BPJS=========


                                        // jika ada special cmg maka proses grouping stage 2
                                        if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                            for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                Grouping2Response grouping2Response = new Grouping2Response();
                                                try {
                                                    grouping2Response = eklaimBoProxy.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), userArea);
                                                } catch (GeneralBOException e) {
                                                    Long logId = null;
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                                    return ERROR;
                                                }
                                            }
                                        }
                                    }

                                } else {
                                    logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                    addActionError("Error, " + " Found problem when saving add data, please inform to your admin. update claim not success. \n" + claimEklaimResponse.getMessage());
                                    return ERROR;
                                }
                            }
                        } else {
                            addActionError("Error, " + "Generate SEP failed");
                            return ERROR;
                        }

                    }
                } else {
                    addActionError("Error, " + "PPK Pelayanan Tidak Ada");
                    return ERROR;
                }
            }
        }

        if (checkup.getDiagnosa() != null && !"".equalsIgnoreCase(checkup.getDiagnosa())
                && checkup.getNamaDiagnosa() != null && !"".equalsIgnoreCase(checkup.getNamaDiagnosa())) {
            //diagnosa ambil dari depan...
        } else {
            List<Diagnosa> diagnosaList = new ArrayList<>();
            Diagnosa diagnosaResult = new Diagnosa();

            Diagnosa diagnosa = new Diagnosa();
            diagnosa.setIdDiagnosa(checkup.getDiagnosa());

            try {
                diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
            } catch (GeneralBOException e) {
                logger.error("[DiagnosaRawatAction.saveDiagnosa] Error when search dec diagnosa by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!diagnosaList.isEmpty()) {
                diagnosaResult = diagnosaList.get(0);
                checkup.setNamaDiagnosa(diagnosaResult.getDescOfDiagnosa());
            }
        }

        try {

//            try {
//                JSONObject obj = new JSONObject(checkup.getAdmisi());
//                checkup.setKetKeyakinan(obj.getString("keyakinan"));
//                checkup.setBahasa(obj.getString("penerjemah"));
//                checkup.setAlatBantu(obj.getString("alatBantu"));
//                checkup.setAlergi(obj.getString("alergi"));
//            } catch (JSONException e) {
//                logger.error("[CheckupAction.saveAdd] Error Convert json to data admisi.", e);
//            }

            String tgl_lahir = checkup.getStTglLahir();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                java.util.Date date = format.parse(tgl_lahir);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                checkup.setTglLahir(sqlDate);
            } catch (ParseException e) {
                logger.error("[CheckupAction.saveAdd] Error Convert String Tgl Lahir to Date.", e);
            }

            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan("03");

            List<Tindakan> tindakans = new ArrayList<>();
            tindakans.add(tindakan);

            checkup.setNoCheckup(noCheckup);
            checkup.setBranchId(userArea);
            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");
            checkup.setStatusPeriksa("1");
            checkup.setNoSep(genNoSep);
            checkup.setTindakanList(tindakans);
            checkup.setUrlKtp(checkup.getUrlKtp());

//            String fileName = "";
//            if (this.fileUploadDoc != null) {
//                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
//                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {
//
//                        // file name
//                        fileName = "SURAT_RUJUK_" + checkup.getNoKtp() + "_" + this.fileUploadDocFileName;
//
//                        // deklarasi path file
//                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
//                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);
//
//                        // persiapan pemindahan file
//                        File fileToCreate = new File(filePath, fileName);
//
//                        try {
//                            // pemindahan file
//                            FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
//                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
//                            checkup.setUrlDocRujuk(fileName);
//                        } catch (IOException e) {
//                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
//                        }
//                    }
//                }
//            }
            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        String fileName = this.fileUploadDocFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-"+dateFormater("MM")+dateFormater("yy")+"-"+fileNameReplace;
                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(newFileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                        }
                    }
                }
            }

            checkupBoProxy.saveAdd(checkup);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.saveAdd] end process >>>");
        return "init_add";

    }

    private String dateFormater(String type){
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public String printResepPasien() {

        String idResep = getIdResep();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";

        switch (branch) {
            case CommonConstant.BRANCH_RS01:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS01;
                break;
            case CommonConstant.BRANCH_RS02:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS02;
                break;
            case CommonConstant.BRANCH_RS03:
                logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.LOGO_RS03;
                break;
        }

        HeaderCheckup headerCheckup = getHeaderCheckup(id);
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());

        reportParams.put("area", CommonUtil.userAreaName());
        reportParams.put("unit", CommonUtil.userBranchNameLogin());
        reportParams.put("idPasien", headerCheckup.getIdPasien());
        reportParams.put("resepId", idResep);
        reportParams.put("logo", logo);
        reportParams.put("nik", headerCheckup.getNoKtp());
        reportParams.put("nama", headerCheckup.getNama());
        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
        reportParams.put("tglLahir", headerCheckup.getTempatLahir() + ", " + formatDate);
        if ("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        reportParams.put("jenisKelamin", jk);
        reportParams.put("jenisPasien", jenisPriksaPasien.getKeterangan());
        reportParams.put("poli", headerCheckup.getNamaPelayanan());
        reportParams.put("provinsi", headerCheckup.getNamaProvinsi());
        reportParams.put("kabupaten", headerCheckup.getNamaKota());
        reportParams.put("kecamatan", headerCheckup.getNamaKecamatan());
        reportParams.put("desa", headerCheckup.getNamaDesa());


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_resep";
    }

    public String printGelangPasien() {

        String id = getId();
        HeaderCheckup headerCheckup = getHeaderCheckup(id);
        reportParams.put("noCheckup", id);
        reportParams.put("nama", headerCheckup.getNama());

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_gelang_pasien";
    }

    public CheckResponse saveApproveAllTindakanRawatJalan(String idDetailCheckup, String jenisPasien) {

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setLastUpdate(updateTime);
            headerDetailCheckup.setLastUpdateWho(user);

            try {
                response = checkupDetailBo.saveApproveAllTindakanRawatJalan(headerDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if ("success".equalsIgnoreCase(response.getStatus())) {
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien);
            }

        }

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] END process >>>");

        return response;
    }

    public String saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
            LabBo labBo = (LabBo) ctx.getBean("labBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

            List<TindakanRawat> listTindakan = new ArrayList<>();
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setApproveFlag("Y");

            try {
                listTindakan = tindakanRawatBo.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());
                        riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenisPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(idDetail);
            periksaLab.setApproveFlag("Y");

            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        List<Lab> labList = new ArrayList<>();
                        Lab lab = new Lab();
                        lab.setIdLab(entity.getIdLab());

                        try {
                            labList = labBo.getByCriteria(lab);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tarif tindakan :" + e.getMessage());
                        }

                        if (labList.size() > 0) {
                            lab = labList.get(0);

                            if (lab != null) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                                riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Periksa Lab " + entity.getLabName());
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                                riwayatTindakan.setKeterangan("lab_radiologi");
                                riwayatTindakan.setJenisPasien(jenisPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                                try {
                                    riwayatTindakanBo.saveAdd(riwayatTindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);
//            resep.setFlag("Y");

            try {
                resepList = permintaanResepBo.getByCriteria(resep);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
                        TransaksiObatDetail detail = new TransaksiObatDetail();
                        detail.setIdPermintaanResep(entity.getIdPermintaanResep());

                        try {
                            obatDetailList = transaksiObatBo.getSearchObatTransaksiByCriteria(detail);
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                        riwayatTindakan.setTotalTarif(new BigDecimal(hitungTotalResep));
                        riwayatTindakan.setKeterangan("resep");
                        riwayatTindakan.setJenisPasien(jenisPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(idDetail);
            List<RawatInap> rawatInapList = new ArrayList<>();

            try {
                rawatInapList = rawatInapBo.getByCriteria(rawatInap);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }

            if (rawatInapList.size() > 0) {

                rawatInap = rawatInapList.get(0);

                if (rawatInap != null) {

                    OrderGizi orderGizi = new OrderGizi();
                    orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                    List<OrderGizi> giziList = new ArrayList<>();

                    try {
                        giziList = orderGiziBo.getByCriteria(orderGizi);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error" + e.getMessage());
                    }

                    if (giziList.size() > 0) {

                        for (OrderGizi gizi : giziList) {

                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(gizi.getIdOrderGizi());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenisPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(gizi.getCreatedDate());

                                try {
                                    riwayatTindakanBo.saveAdd(riwayatTindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }

                        }
                    }
                }
            }
        }

        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] END process >>>");
        return SUCCESS;

    }

    private BigInteger hitungTotalBayar(List<TransaksiObatDetail> transaksiObatDetails) {

        BigInteger total = new BigInteger(String.valueOf("0"));
        if (transaksiObatDetails != null && transaksiObatDetails.size() > 0) {
            for (TransaksiObatDetail trans : transaksiObatDetails) {
                total = total.add(trans.getTotalHarga());
            }
        }
        return total;

    }

    private void updateFlagPeriksaAntrianOnline(String idDetailCheckup) {

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            try {
                checkupDetailBo.updateFlagPeriksaAntrianOnline(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("Found Error when update antrian online");
            }
        }
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