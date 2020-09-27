package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
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
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekammedis.bo.RekamMedisPasienBo;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkup.model.PelayananPaket;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.OrderPeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.OrderPeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.annotations.Check;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
    private TeamDokterBo teamDokterBoProxy;

    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;

    private File fileUploadDoc;
    private String fileUploadDocFileName;
    private String fileUploadDocContentType;

    private File fileUploadPolisi;
    private String fileUploadPolisiFileName;
    private String fileUploadPolisiContentType;

    private String idResep;
    private BigInteger tarifCoverBpjs;
    private BigInteger tarifTotalTindakan;
    private String tipe;
    private String lab;
    private String idPasien;
    private String ids;


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public File getFileUploadPolisi() {
        return fileUploadPolisi;
    }

    public void setFileUploadPolisi(File fileUploadPolisi) {
        this.fileUploadPolisi = fileUploadPolisi;
    }

    public String getFileUploadPolisiFileName() {
        return fileUploadPolisiFileName;
    }

    public void setFileUploadPolisiFileName(String fileUploadPolisiFileName) {
        this.fileUploadPolisiFileName = fileUploadPolisiFileName;
    }

    public String getFileUploadPolisiContentType() {
        return fileUploadPolisiContentType;
    }

    public void setFileUploadPolisiContentType(String fileUploadPolisiContentType) {
        this.fileUploadPolisiContentType = fileUploadPolisiContentType;
    }

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

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
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

        String id = getId();

        HeaderCheckup checkup = new HeaderCheckup();
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

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
                    jk = "Laki-Laki";
                }
            }
            detailCheckup.setJenisKelamin(jk);
            detailCheckup.setTempatLahir(checkup.getTempatLahir());
            detailCheckup.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
            if (checkup.getTglLahir() != null) {
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                detailCheckup.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
            }
            detailCheckup.setNik(checkup.getNoKtp());
            detailCheckup.setIdJenisPeriksaPasien(checkup.getIdJenisPeriksaPasien());
            detailCheckup.setUrlKtp(checkup.getUrlKtp());
            detailCheckup.setTinggi(checkup.getTinggi());
            detailCheckup.setBerat(checkup.getBerat());
            detailCheckup.setNoSep(checkup.getNoSep());
            detailCheckup.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
            detailCheckup.setMetodePembayaran(checkup.getMetodePembayaran());
            detailCheckup.setNoRujukan(checkup.getNoRujukan());
            detailCheckup.setTglRujukan(checkup.getTglRujukan());
            detailCheckup.setSuratRujukan(checkup.getUrlDocRujuk());
            detailCheckup.setIdAsuransi(checkup.getIdAsuransi());
            detailCheckup.setNamaAsuransi(checkup.getNamaAsuransi());
            detailCheckup.setCoverBiaya(checkup.getCoverBiaya());
            detailCheckup.setIsLaka(checkup.getIsLaka());
            detailCheckup.setAnamnese("Autoanamnesis : " + checkup.getAutoanamnesis() + ", Heteroanamnesis : " + checkup.getHeteroanamnesis());
            detailCheckup.setAutoanamnesis(checkup.getAutoanamnesis());
            detailCheckup.setHeteroanamnesis(checkup.getHeteroanamnesis());
            detailCheckup.setNamaDiagnosa(checkup.getNamaDiagnosa());
            detailCheckup.setAlergi(checkup.getAlergi());
            detailCheckup.setPenunjangMedis(checkup.getPenunjangMedis());
            detailCheckup.setAlamatLengkap(checkup.getNamaDesa() + ", " + checkup.getNamaKecamatan() + ", " + checkup.getNamaKota());
            detailCheckup.setNoBpjs(checkup.getNoBpjs());
            String tahun = calculateAge(checkup.getTglLahir(), true);
            detailCheckup.setUmur(tahun);
            detailCheckup.setTensi(checkup.getTensi());
            detailCheckup.setSuhu(checkup.getSuhu());
            detailCheckup.setNadi(checkup.getNadi());
            detailCheckup.setPernafasan(checkup.getPernafasan());

            detailCheckup.setKategoriPelayanan(checkup.getKategoriPelayanan());
            String label = checkup.getNamaPelayanan().replace("Poli Spesialis", "");
            detailCheckup.setAsesmenLabel("Asesmen " + label);
            detailCheckup.setTipePelayanan(checkup.getTipePelayanan());

            if ("rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
                RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
                RekananOps ops = new RekananOps();
                String userBranch = CommonUtil.userBranchLogin();
                try {
                    ops = rekananOpsBo.getDetailRekananOps(checkup.getIdAsuransi(), userBranch);
                } catch (HibernateException e) {
                    logger.error("Error, " + e.getMessage());
                }
                if (ops != null) {
                    detailCheckup.setNamaRekanan(ops.getNamaRekanan());
                }
            }
            setHeaderDetailCheckup(detailCheckup);

        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }

        logger.info("[CheckupDetailAction.add] end process <<<");

        return "init_add";
    }

    public HeaderDetailCheckup getStatusBiayaTindakan(String idDetailCheckup, String jenis) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        // total biaya for tindakan rawat inap harus approve dulu
        if ("RI".equalsIgnoreCase(jenis)) {
            HeaderDetailCheckup biayaTindakanRawatInap = getListBiayaForRawatInap(idDetailCheckup);
            if (biayaTindakanRawatInap.getTarifTindakan() != null && biayaTindakanRawatInap.getTarifTindakan().compareTo(new BigDecimal(0)) == 1) {
                return biayaTindakanRawatInap;
            }
        }

        if ("RJ".equalsIgnoreCase(jenis)) {
            HeaderDetailCheckup biayaTindakanJalan = getListBiayaForRawatJalan(idDetailCheckup);
            if (biayaTindakanJalan.getTarifTindakan() != null && biayaTindakanJalan.getTarifTindakan().compareTo(new BigDecimal(0)) == 1) {
                return biayaTindakanJalan;
            }
        }

        if ("RWJ".equalsIgnoreCase(jenis)) {
            HeaderDetailCheckup biayaTotalTindakanJalan = getTotalBiayaForRawatJalan(idDetailCheckup);
            if (biayaTotalTindakanJalan.getTarifTindakan() != null && biayaTotalTindakanJalan.getTarifTindakan().compareTo(new BigDecimal(0)) == 1) {
                return biayaTotalTindakanJalan;
            }
        }

        return detailCheckup;
    }

    public HeaderDetailCheckup getListBiayaForRawatInap(String idDetailCheckup) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            detailCheckup.setIdDetailCheckup(idDetailCheckup);

            BigDecimal totalBiayaTindakan = checkupDetailBo.getSumJumlahTindakan(idDetailCheckup, "");

            List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

            try {
                detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
            }

            if (detailCheckupList.size() > 0) {
                HeaderDetailCheckup headerDetailCheckup = detailCheckupList.get(0);
                if (headerDetailCheckup.getNoCheckup() != null && !"".equalsIgnoreCase(headerDetailCheckup.getNoCheckup())) {

                    HeaderCheckup headerCheckup = new HeaderCheckup();
                    headerCheckup.setNoCheckup(headerDetailCheckup.getNoCheckup());

                    List<HeaderCheckup> headerCheckups = new ArrayList<>();
                    try {
                        headerCheckups = checkupBo.getByCriteria(headerCheckup);
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getListBiayaForRawatInap] Error When Get Header Checkup Data", e);
                    }

                    if (headerCheckups.size() > 0) {
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

    public HeaderDetailCheckup getListBiayaForRawatJalan(String idDetailCheckup) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                detailCheckup = checkupDetailBo.getBiayaTindakan(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("Found Error when serah rawat jalan tindakan " + e.getMessage());
            }
        }
        return detailCheckup;
    }

    public HeaderDetailCheckup getTotalBiayaForRawatJalan(String idDetailCheckup) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                detailCheckup = checkupDetailBo.getTotalBiayaTindakanBpjs(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("Found Error when serah rawat jalan tindakan " + e.getMessage());
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
        String userRoleLogin = CommonUtil.roleAsLogin();
        if (CommonConstant.ROLE_ADMIN_RS.equalsIgnoreCase(userRoleLogin)) {
            setEnabledPoli(true);
            headerDetailCheckup.setTipePelayanan("rawat_jalan");
        }

        if (CommonConstant.ROLE_ADMIN_IGD.equalsIgnoreCase(userRoleLogin)) {
            setEnabledAddPasien(true);
        }

        if (CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin)) {
            HeaderDetailCheckup headerDetailCheckup1 = new HeaderDetailCheckup();
            headerDetailCheckup1.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            setHeaderDetailCheckup(headerDetailCheckup1);
            headerDetailCheckup.setTipePelayanan("rawat_jalan");
        }

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
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
        String pelayanan = "";

        if ("ADMIN RS".equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            pelayanan = "";
        } else {
            pelayanan = CommonUtil.userPelayananIdLogin();
        }

        try {
            kategoriTindakanList = kategoriTindakanBoProxy.getListKategoriTindakan(pelayanan, null);
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
//        if ("ADMIN RS".equalsIgnoreCase(CommonUtil.roleAsLogin())) {
//            //tampil semua tindakan
//        } else {
//            tindakan.setIdPelayanan(CommonUtil.userPelayananIdLogin());
//        }

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

    public List<KategoriTindakan> getListComboTindakanKategori(String idPelayanan, String kategori) {
        logger.info("[CheckupDetailAction.listOfDokter] start process >>>");

        List<KategoriTindakan> kategoriTindakans = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KategoriTindakanBo kategoriTindakanBo = (KategoriTindakanBo) ctx.getBean("kategoriTindakanBoProxy");

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
            try {
                kategoriTindakans = kategoriTindakanBo.getListKategoriTindakan(idPelayanan, kategori);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
            }
        }

        logger.info("[CheckupDetailAction.listOfDokter] end process >>>");
        return kategoriTindakans;
    }

    public CrudResponse saveKeterangan(String noCheckup, String idDetailCheckup, String idKtg, String poli, String kelas, String kamar, String idDokter, String ket, String tglCekup, String ketCekup, String jenisPasien, String caraPulang, String pendamping, String tujuan, String idPasien, String metodeBayar, String uangMuka, String jenisBayar, String tipe, String dataPemeriksaan) throws JSONException {
        logger.info("[CheckupDetailAction.saveKeterangan] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");


        CrudResponse response = new CrudResponse();

        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
        headerDetailCheckup.setStatusPeriksa("3");
        headerDetailCheckup.setFlag("Y");
        headerDetailCheckup.setAction("U");

        if (tipe.equalsIgnoreCase("lab")) {
            response.setStatus("success");
        } else {
            response = cekAllTindakanRawat(idDetailCheckup);
        }

        if ("success".equalsIgnoreCase(response.getStatus())) {

            if (tglCekup != null && !"".equalsIgnoreCase(tglCekup)) {
                headerDetailCheckup.setTglCekup(java.sql.Date.valueOf(tglCekup));
                headerDetailCheckup.setNoCheckupUlang(CommonUtil.userBranchLogin() + dateFormater("yy") + dateFormater("MM") + dateFormater("dd"));
            }

            if ("selesai".equalsIgnoreCase(idKtg)) {
                if (!"".equalsIgnoreCase(dataPemeriksaan)) {
                    JSONObject obj = new JSONObject(dataPemeriksaan);
                    List<OrderPeriksaLab> orderPeriksaLab = new ArrayList<>();
                    if (obj != null) {
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        String user = CommonUtil.userLogin();
                        String idKategori = obj.getString("kategori_lab");
                        String idLab = obj.getString("unit_lab");
                        JSONArray json = obj.getJSONArray("parameter");
                        for (int i = 0; i < json.length(); i++) {
                            String value = json.getString(i);
                            OrderPeriksaLab order = new OrderPeriksaLab();
                            order.setIdDetailCheckup(idDetailCheckup);
                            order.setIdLab(idLab);
                            order.setIdLabDetail(value);
                            order.setIsPemeriksaan("N");
                            order.setAction("C");
                            order.setFlag("Y");
                            order.setCreatedWho(user);
                            order.setCreatedDate(now);
                            order.setLastUpdateWho(user);
                            order.setLastUpdate(now);
                            if ("KAL00000001".equalsIgnoreCase(idKategori)) {
                                order.setKeterangan("radiologi");
                            }
                            if ("KAL00000002".equalsIgnoreCase(idKategori)) {
                                order.setKeterangan("lab");
                            }
                            orderPeriksaLab.add(order);
                        }

                        if (orderPeriksaLab.size() > 0) {
                            response = saveLabCheckup(orderPeriksaLab);
                            if ("success".equalsIgnoreCase(response.getStatus())) {
                                headerDetailCheckup.setIsOrderLab("Y");
                            }
                        }
                    }
                }

                headerDetailCheckup.setKeteranganSelesai(ket);
                headerDetailCheckup.setCaraPasienPulang(caraPulang);
                headerDetailCheckup.setPendamping(pendamping);
                headerDetailCheckup.setTempatTujuan(tujuan);
                headerDetailCheckup.setKeteranganCekupUlang(ketCekup);
                headerDetailCheckup.setStatus(idKtg);
                cekRawatInap(idDetailCheckup);
            }

            if ("lanjut_biaya".equalsIgnoreCase(idKtg)) {
                headerDetailCheckup.setKeteranganSelesai("Lanjut Biaya");
                cekRawatInap(idDetailCheckup);
            }
            if ("rujuk_rs_lain".equalsIgnoreCase(idKtg)) {
                headerDetailCheckup.setKeteranganSelesai("Rujuk Rumah Sakit Lain");
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
            saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien);

            if ("asuransi".equalsIgnoreCase(jenisPasien) || "ptpn".equalsIgnoreCase(jenisPasien) || "paket_individu".equalsIgnoreCase(jenisPasien) || "paket_perusahaan".equalsIgnoreCase(jenisPasien)) {
                metodeBayar = "non_tunai";
            } else if ("umum".equalsIgnoreCase(jenisPasien)) {
                metodeBayar = jenisBayar;
            }

            // create jurnal if non tunai
            if ("non_tunai".equalsIgnoreCase(metodeBayar)) {
                JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, poli, idPasien);
                if (!"ptpn".equalsIgnoreCase(jurnalResponse.getStatus())) {
                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
                        response.setStatus("error");
                        response.setMsg(jurnalResponse.getMsg());
                        return response;
                    } else if (!"".equalsIgnoreCase(jurnalResponse.getInvoice())) {
                        headerDetailCheckup.setInvoice(jurnalResponse.getInvoice());
                    }
                }
            }


            if ("pindah".equalsIgnoreCase(idKtg)) {
                response = pindahPoli(idDetailCheckup, poli, idDokter, metodeBayar, uangMuka);
            }

            if ("rujuk".equalsIgnoreCase(idKtg)) {
                response = rujukRawatInap(noCheckup, idDetailCheckup, kelas, kamar, metodeBayar, uangMuka, idDokter, poli);
            }

            try {

                if ("success".equalsIgnoreCase(response.getStatus()) || "selesai".equalsIgnoreCase(idKtg) || "lanjut_biaya".equalsIgnoreCase(idKtg) || "rujuk_rs_lain".equalsIgnoreCase(idKtg)) {
                    headerDetailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    headerDetailCheckup.setLastUpdateWho(CommonUtil.userLogin());
                    response = checkupDetailBo.saveEdit(headerDetailCheckup);
                }

            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, ", e);
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, " + e);
                return response;
            }

            updateFlagPeriksaAntrianOnline(idDetailCheckup);

        }

        logger.info("[CheckupDetailAction.saveKeterangan] end process >>>");
        return response;
    }

    public CrudResponse closeTraksaksiPasien(String data) {
        logger.info("[CheckupDetailAction.closeTraksaksiPasien] START process >>>");
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();
        List<HeaderDetailCheckup> detailCheckups = new ArrayList<>();

        if (data != null && !"".equalsIgnoreCase(data)) {
            try {
                JSONObject object = new JSONObject(data);
                if (object != null) {
                    String noCheckup = object.getString("no_checkup");
                    String idDetailCheckup = object.getString("id_detail_checkup");
                    String idPasien = object.getString("id_pasien");
                    String jenisPasien = object.getString("jenis_pasien");
                    String idPelayanan = object.getString("id_pelayanan");
                    String jenisBayar = object.getString("metode_bayar");
                    String metodeBayar = "";
                    String justLab = "";
                    if (object.has("just_lab")) {
                        justLab = object.getString("just_lab");
                    }

                    List<HeaderDetailCheckup> detailCheckupList = checkupDetailBo.getIDDetailCheckup(noCheckup);

                    if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
                        if (justLab != null && !"".equalsIgnoreCase(justLab)) {
                            response.setStatus("success");
                        } else {
                            if(detailCheckupList.size() > 0){
                                for (HeaderDetailCheckup detail: detailCheckupList){
                                    response = cekAllTindakanRawat(detail.getIdDetailCheckup());
                                }
                            }
                        }
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            for (HeaderDetailCheckup detail: detailCheckupList){
                                saveAddToRiwayatTindakan(detail.getIdDetailCheckup(), jenisPasien);
                            }
                            if ("ptpn".equalsIgnoreCase(jenisPasien) || "paket_individu".equalsIgnoreCase(jenisPasien) || "paket_perusahaan".equalsIgnoreCase(jenisPasien)) {
                                metodeBayar = "non_tunai";
                            } else if ("umum".equalsIgnoreCase(jenisPasien)) {
                                metodeBayar = jenisBayar;
                            }

                            for (HeaderDetailCheckup detail: detailCheckupList){
                                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                                detailCheckup.setIdDetailCheckup(detail.getIdDetailCheckup());
                                detailCheckup.setLastUpdateWho(user);
                                detailCheckup.setLastUpdate(now);
                                detailCheckup.setFlagCloseTraksaksi("Y");
                                if (justLab != null && !"".equalsIgnoreCase(justLab)) {
                                    detailCheckup.setJustLab(justLab);
                                }
                                detailCheckups.add(detailCheckup);
                            }
                            // create jurnal if non tunai
//                            if ("non_tunai".equalsIgnoreCase(metodeBayar)) {
//                                JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, idPelayanan, idPasien);
//                                if (!"ptpn".equalsIgnoreCase(jurnalResponse.getStatus())) {
//                                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
//                                        response.setStatus("error");
//                                        response.setMsg(jurnalResponse.getMsg());
//                                        return response;
//                                    } else if (!"".equalsIgnoreCase(jurnalResponse.getInvoice())) {
//                                        detailCheckup.setInvoice(jurnalResponse.getInvoice());
//                                    }
//                                }
//                            }
                            response = checkupDetailBo.updateDetailCheckup(detailCheckups);
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("ID Detail Checkup tidak ada....!");
                    }
                } else {
                    response.setStatus("error");
                    response.setMsg("Data Object tidak ada....!");
                }
            } catch (JSONException e) {
                response.setStatus("error");
                response.setMsg("[Found Error] Terjadi kesalahan saat melakukan parse JSON Object..!, " + e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data tidak ada....!");
        }
        logger.info("[CheckupDetailAction.saveKeterangan] END process >>>");
        return response;
    }

    public CrudResponse savePeriksaPasien(String data) {
        CrudResponse response = new CrudResponse();
        logger.info("[CheckupDetailAction.savePeriksaPasien] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();

        if (data != null && !"".equalsIgnoreCase(data)) {

            try {
                JSONObject object = new JSONObject(data);
                String noCheckup = object.getString("no_checkup");
                String idDetailCheckup = object.getString("id_detail_checkup");
                String tindakLanjut = object.getString("tindak_lanjut");
                String jenisPasien = object.getString("jenis_pasien");
                String rsRujukan = null;
                String tglKontrol = null;
                String kategoriLab = null;
                String unitLab = null;
                String isOrderLab = null;
                JSONArray jsonParameter = new JSONArray();
                String poliLain = null;
                String idDokter = null;
                String metodeBayar = null;
                String uangMuka = null;
                String urutan = null;
                String idPaket = null;
                String idPelayananPaket = null;

                if (object.has("rs_rujukan")) {
                    rsRujukan = object.getString("rs_rujukan");
                }
                if (object.has("tgl_kontrol")) {
                    tglKontrol = object.getString("tgl_kontrol");
                }
                if (object.has("kategori_lab")) {
                    kategoriLab = object.getString("kategori_lab");
                }
                if (object.has("unit_lab")) {
                    unitLab = object.getString("unit_lab");
                }
                if (object.has("parameter")) {
                    jsonParameter = object.getJSONArray("parameter");
                }
                if (object.has("is_order_lab")) {
                    isOrderLab = object.getString("is_order_lab");
                }
                if (object.has("poli_lain")) {
                    poliLain = object.getString("poli_lain");
                }
                if (object.has("id_dokter")) {
                    idDokter = object.getString("id_dokter");
                }
                if (object.has("metode_bayar")) {
                    metodeBayar = object.getString("metode_bayar");
                }
                if (object.has("uang_muka")) {
                    uangMuka = object.getString("uang_muka");
                }
                if (object.has("urutan_paket")) {
                    urutan = object.getString("urutan_paket");
                }
                if (object.has("id_paket")) {
                    idPaket = object.getString("id_paket");
                }
                if (object.has("id_paket_pelayanan")) {
                    idPelayananPaket = object.getString("id_paket_pelayanan");
                }

                List<OrderPeriksaLab> orderPeriksaLab = new ArrayList<>();

                if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(tindakLanjut)) {
                    HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                    headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
                    headerDetailCheckup.setFlag("Y");
                    headerDetailCheckup.setAction("U");
                    headerDetailCheckup.setLastUpdate(now);
                    headerDetailCheckup.setLastUpdateWho(user);
                    headerDetailCheckup.setStatusPeriksa("3");
                    headerDetailCheckup.setTindakLanjut(tindakLanjut);
                    headerDetailCheckup.setCatatan(object.getString("catatan"));
                    headerDetailCheckup.setKeteranganSelesai(object.getString("keterangan"));
                    if (tglKontrol != null) {
                        headerDetailCheckup.setTglCekup(java.sql.Date.valueOf(tglKontrol));
                    }
                    headerDetailCheckup.setRsRujukan(rsRujukan);

                    saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien);

                    if ("Y".equalsIgnoreCase(isOrderLab)) {
                        for (int i = 0; i < jsonParameter.length(); i++) {
                            String value = jsonParameter.getString(i);
                            OrderPeriksaLab order = new OrderPeriksaLab();
                            order.setIdDetailCheckup(idDetailCheckup);
                            order.setIdLab(unitLab);
                            order.setIdLabDetail(value);
                            order.setIsPemeriksaan("N");
                            order.setAction("C");
                            order.setFlag("Y");
                            order.setCreatedWho(user);
                            order.setCreatedDate(now);
                            order.setLastUpdateWho(user);
                            order.setLastUpdate(now);
                            if ("KAL00000001".equalsIgnoreCase(kategoriLab)) {
                                order.setKeterangan("radiologi");
                            }
                            if ("KAL00000002".equalsIgnoreCase(kategoriLab)) {
                                order.setKeterangan("lab");
                            }
                            orderPeriksaLab.add(order);
                        }

                        if (orderPeriksaLab.size() > 0) {
                            response = saveLabCheckup(orderPeriksaLab);
                            if ("success".equalsIgnoreCase(response.getStatus())) {
                                headerDetailCheckup.setIsOrderLab("Y");
                            }
                        }
                    }

                    if ("pindah_poli".equalsIgnoreCase(tindakLanjut)) {
                        response = pindahPoli(idDetailCheckup, poliLain, idDokter, metodeBayar, uangMuka);
                    } else if ("lanjut_paket".equalsIgnoreCase(tindakLanjut)) {
                        response = nextPaket(noCheckup, idDetailCheckup, urutan, idPaket, idDokter, idPelayananPaket, poliLain);
                    } else {
                        response.setStatus("success");
                    }

                    try {
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            response = checkupDetailBo.saveEdit(headerDetailCheckup);
                            updateFlagPeriksaAntrianOnline(idDetailCheckup);
                        }
                    } catch (GeneralBOException e) {
                        response.setStatus("error");
                        response.setMsg("[Found Error] Terjadi kesalahan saat melakukan update data transaksi..!, " + e.getMessage());
                    }
                } else {
                    response.setStatus("error");
                    response.setMsg("[Found Error] ID Detail Chekcup Tidak Ditemukan...!");
                }
            } catch (JSONException e) {
                response.setStatus("error");
                response.setMsg("[Found Error] Terjadi kesalahan saat melakukan parse JSON Object..!, " + e.getMessage());

            }
        } else {
            response.setStatus("error");
            response.setMsg("[Found Error] Data yang dikirim tidak lengkap...!");
        }
        logger.info("[CheckupDetailAction.savePeriksaPasien] END process >>>");
        return response;
    }

    private CrudResponse nextPaket(String noCheckup, String idDetailCheckup, String urutan, String idPaket, String idDokter, String idPelayananPaket,  String idPelayanan) {
        CrudResponse response = new CrudResponse();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        PelayananPaket paket = new PelayananPaket();
        paket.setNoCheckup(noCheckup);
        paket.setIdDetailCheckup(idDetailCheckup);
        paket.setIdDokter(idDokter);
        paket.setIdPelayananPaket(idPelayananPaket);
        paket.setUrutan(new BigInteger(urutan));
        paket.setIdPaket(idPaket);
        paket.setCreatedDate(now);
        paket.setCreatedWho(user);
        paket.setLastUpdate(now);
        paket.setLastUpdateWho(user);

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setNoCheckup(noCheckup);
        detailCheckup.setIdPelayanan(idPelayanan);
        List<DokterTeam> teamList = new ArrayList<>();
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDokter(idDokter);
        dokterTeam.setIdPelayanan(idPelayanan);
        teamList.add(dokterTeam);
        detailCheckup.setDokterTeamList(teamList);

        try {
            response = checkupBo.nextItemPaketToPeriksa(paket, detailCheckup);
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        return response;
    }

    private CrudResponse saveLabCheckup(List<OrderPeriksaLab> bean) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        OrderPeriksaLabBo orderPeriksaLabBo = (OrderPeriksaLabBo) ctx.getBean("orderPeriksaLabBoProxy");
        try {
            response = orderPeriksaLabBo.saveAdd(bean);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error, " + e.getMessage());
        }
        return response;
    }

    private CrudResponse cekAllTindakanRawat(String idDetailCheckup) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        String cekTindakan = "Y";
        String cekLab = "Y";
        String cekGizi = "Y";
        String cekResep = "Y";

        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);

        try {
            tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMsg("Found Error, " + e.getMessage());
        }

        if (tindakanRawatList.size() > 0) {
            for (TindakanRawat rawat : tindakanRawatList) {
                if (!"Y".equalsIgnoreCase(rawat.getApproveFlag())) {
                    cekTindakan = "N";
                }
            }
        } else {
            cekTindakan = "N";
        }

        List<PeriksaLab> periksaLabList = new ArrayList<>();
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdDetailCheckup(idDetailCheckup);

        try {
            periksaLabList = periksaLabBo.getByCriteria(periksaLab);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMsg("Found Error, " + e.getMessage());
        }

        if (periksaLabList.size() > 0) {
            for (PeriksaLab lab : periksaLabList) {
                if (!"Y".equalsIgnoreCase(lab.getApproveFlag())) {
                    cekLab = "N";
                }
            }
        }

        List<RawatInap> rawatInapList = new ArrayList<>();
        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        try {
            rawatInapList = rawatInapBo.getByCriteria(rawatInap);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMsg("Found Error, " + e.getMessage());
        }

        if (rawatInapList.size() > 0) {

            rawatInap = rawatInapList.get(0);

            if (rawatInap.getIdRawatInap() != null) {

                OrderGizi orderGizi = new OrderGizi();
                orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                List<OrderGizi> giziList = new ArrayList<>();

                try {
                    giziList = orderGiziBo.getByCriteria(orderGizi);
                } catch (GeneralBOException e) {
                    logger.error("Found Error, " + e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Found Error, " + e.getMessage());
                }

                if (giziList.size() > 0) {
                    for (OrderGizi gizi : giziList) {
                        if (!"Y".equalsIgnoreCase(gizi.getDiterimaFlag())) {
                            cekGizi = "N";
                        }
                    }
                }
            }
        }

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdDetailCheckup(idDetailCheckup);

        try {
            permintaanResepList = permintaanResepBo.getByCriteria(permintaanResep);
        } catch (GeneralBOException e) {
            logger.error("Found Error, " + e.getMessage());
            response.setStatus("error");
            response.setMsg("Found Error, " + e.getMessage());
        }

        if (permintaanResepList.size() > 0) {
            for (PermintaanResep resep : permintaanResepList) {
                if (!"Y".equalsIgnoreCase(resep.getApproveFlag())) {
                    cekResep = "N";
                }
            }
        }

        if ("Y".equalsIgnoreCase(cekTindakan) && "Y".equalsIgnoreCase(cekLab) && "Y".equalsIgnoreCase(cekGizi) && "Y".equalsIgnoreCase(cekResep)) {
            response.setStatus("success");
            response.setMsg("Berhasil");
        } else {
            String msg = "Traksaksi tidak dapat di close dikarenakan, ";
            if ("N".equalsIgnoreCase(cekTindakan)) {
                msg = msg + " Tindakan tidak boleh kosong, dan silahkan klik tombol Save All Tindakan untuk meverifikasi tindakan yang sudah dilakukan, ";
            }
            if ("N".equalsIgnoreCase(cekLab)) {
                msg = msg + "Tindakan lab atau radiologi belum dilakukan, ";
            }
            if ("N".equalsIgnoreCase(cekGizi)) {
                msg = msg + " Order gizi belum diterima pasien, ";
            }
            if ("N".equalsIgnoreCase(cekResep)) {
                msg = msg + " Order resep belum diambil diapotek";
            }
            response.setStatus("error");
            response.setMsg(msg);
        }

        return response;
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
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)) {
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)) {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null) {

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0) {
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

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idPoli, String idPasien) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");

        String kode = "";
        String transId = "";
        String ketPoli = "";
        String ketResep = "";
        String divisiId = "";
        String masterId = "";
        String jenisPasien = "Umum ";
        String divisiResep = "";
        String noKartu = "";
        String idJenisPeriksaPasien = "";
        BigDecimal biayaCover = new BigDecimal(0);
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null) {
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }
        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        if (!"ptpn".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
            if (!"bpjs".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                    biayaCover = detailCheckupEntity.getCoverBiaya();

                    ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
                    if (asuransiEntity != null) {
                        masterId = asuransiEntity.getNoMaster();
                        jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " ";
                    } else {
                        logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                        return response;
                    }

                    noKartu = " No. Kartu Asuransi " + detailCheckupEntity.getNoKartuAsuransi();

                } else {
                    masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
                }

                String masterPerusahaan = "";
                if ("paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                    ItSimrsPaketPasienEntity paketPasienEntity = paketPeriksaBo.getPaketPasienEntityByIdPaket(detailCheckupEntity.getIdPaket(), idPasien);
                    if (paketPasienEntity != null) {
                        masterPerusahaan = paketPasienEntity.getIdPerusahaan();
                    }
                }

                ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
                if (pelayananEntity != null) {

                    // MENDAPATKAN DIVISI ID TINDAKAN / PENDAPATAN RAWAT;
                    divisiId = getDivisiId(idDetailCheckup, "", "");

                    // MENDAPATKAN NILAI UANG MUKA;
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
                    BigDecimal jumlahResep = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetailCheckup, "resep");
                    BigDecimal jumlahTindakan = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetailCheckup, "");
                    BigDecimal ppnObat = new BigDecimal(0);
                    if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                        ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }

                    // jumlah tindakan saja. tindakan total - jumlah resep
                    jumlahTindakan = jumlahTindakan.subtract(jumlahResep);

                    Map mapUangMuka = new HashMap();
                    mapUangMuka.put("bukti", idUm);
                    mapUangMuka.put("nilai", jumlahUm);
                    mapUangMuka.put("master_id", masterId);

                    Map mapPajakObat = new HashMap();
//                    mapPajakObat.put("bukti", invoice);
                    mapPajakObat.put("nilai", ppnObat);
                    mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                    Map hsCriteria = new HashMap();
                    BigDecimal jumlah = new BigDecimal(0);

                    if ("rawat_jalan".equalsIgnoreCase(pelayananEntity.getTipePelayanan()) || "igd".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
                        kode = "JRJ";
                        ketPoli = "Rawat Jalan ";
                    }
                    if ("rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {
                        kode = "JRI";
                        ketPoli = "Rawat Inap ";
                    }

                    // untuk transitoris
                    boolean isTransitoris = false;
                    BigDecimal allTindakanTrans = new BigDecimal(0);
                    if (detailCheckupEntity.getNoJurnalTrans() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoJurnalTrans())) {

                        allTindakanTrans = checkupDetailBo.getSumJumlahTindakanTransitoris(idDetailCheckup, "");
                        Map mapTransitoris = new HashMap();
                        mapTransitoris.put("nilai", allTindakanTrans);
                        mapTransitoris.put("bukti", detailCheckupEntity.getInvoiceTrans());
                        hsCriteria.put("piutang_transistoris_pasien_rawat_inap", mapTransitoris);
                        isTransitoris = true;
                    }

                    // tambahkan jumlah tindakan juga untuk debit piutang
                    //jumlah = jumlah.add(jumlahTindakan);

                    if ("Y".equalsIgnoreCase(isResep)) {
                        ketResep = "Dengan Obat ";
                    } else {
                        ketResep = "Tanpa Obat ";
                    }

                    // MAP ALL TINDAKAN BY KETERANGAN
                    List<Map> listOfTindakan = new ArrayList<>();
                    List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
                    if (listOfKeteranganRiwayat.size() > 0) {

                        for (String keterangan : listOfKeteranganRiwayat) {
                            Map mapTindakan = new HashMap();
                            mapTindakan.put("master_id", masterId);
                            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan));
                            mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan));
                            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode));
                            listOfTindakan.add(mapTindakan);
                        }
                    }

                    // MENDAPATKAN SEMUA BIAYA RAWAT;
                    jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");

                    // create invoice nummber
                    invoice = billingSystemBo.createInvoiceNumber(kode, branchId);
                    if ("JRJ".equalsIgnoreCase(kode)) {

                        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                            // dengan pembayaran tunai di luar cover asuransi
                            if (jumlahTindakan.compareTo(biayaCover) == 1) {
                                response.setStatus("success");
                                return response;
                            }

                            // kredit jumlah tindakan asuransi
                            hsCriteria.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);

                            if ("Y".equalsIgnoreCase(isResep)) {

                                // ppn obat asuransi
                                hsCriteria.put("ppn_keluaran", mapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("master_id", masterId);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                                transId = "17";

                            } else {

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                mapPiutang.put("master_id", masterId);
//                                mapPiutang.put("pasien_id", idPasien);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                                transId = "09";
                            }

                        } else {


                            //**** UMUM ***//
                            // kredit jumlah tindakan
                            hsCriteria.put("pendapatan_rawat_jalan_umum", listOfTindakan);

                            // jumlah debit uang muka

                            if (!"paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) && !"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                hsCriteria.put("uang_muka", mapUangMuka);
                            }

                            if ("Y".equalsIgnoreCase(isResep)) {

                                // kredit ppn obat umum
                                hsCriteria.put("ppn_keluaran", mapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.put("pasien_id", idPasien);
                                } else {
                                    mapPiutang.put("master_id", masterPerusahaan);
                                }

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);

                                if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    transId = "62";
                                } else {
                                    transId = "14";
                                }
                            } else {

                                // create list map piutang
                                Map mapPiutang = new HashMap();
//                                mapPiutang.put("bukti", invoice);
                                mapPiutang.put("nilai", jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.put("pasien_id", idPasien);
                                } else {
                                    mapPiutang.put("master_id", masterPerusahaan);
                                }

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", mapPiutang);
                                if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    transId = "61";
                                } else {
                                    transId = "07";
                                }
                            }
                        }
                    }

                    // Untuk Rawat Inap
                    if ("JRI".equalsIgnoreCase(kode)) {
                        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

                            //**** ASURANSI ***//

                            // dengan pembayaran tunai di luar cover asuransi
                            if (jumlahTindakan.compareTo(biayaCover) == 1) {
                                response.setStatus("success");
                                return response;
                            }

                            // kredit jumlah tindakan asuransis
                            hsCriteria.put("pendapatan_rawat_inap_asuransi", listOfTindakan);

                            // create map piutang asuransi
                            Map mapPiutang = new HashMap();
//                            mapPiutang.put("bukti", invoice);
                            mapPiutang.put("nilai", jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.put("master_id", masterId);

                            // debit piutang pasien asuransi
                            hsCriteria.put("piutang_pasien_asuransi", mapPiutang);

                            if (isTransitoris) {
                                jenisPasien = jenisPasien + "Terhadap Transitoris ";
                                transId = "41";
                            } else {
                                transId = "24";
                            }
                        } else {

                            //**** UMUM ***//

                            // create map piutang
                            Map mapPiutang = new HashMap();
//                            mapPiutang.put("bukti", invoice);
                            mapPiutang.put("nilai", jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.put("pasien_id", idPasien);

                            // debit piutang pasien
                            hsCriteria.put("piutang_pasien_umum", mapPiutang);
                            hsCriteria.put("uang_muka", mapUangMuka);

                            // kredit jumlah tindakan
                            hsCriteria.put("pendapatan_rawat_inap_umum", listOfTindakan);

                            if (isTransitoris) {
                                jenisPasien = jenisPasien + "Terhadap Transitoris ";
                                transId = "39";
                            } else {
                                transId = "21";
                            }
                        }
                    }

                    String catatan = "";
                    if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {

                        MtSimrsPaketEntity paketEntity = paketPeriksaBo.getPaketEntityById(detailCheckupEntity.getIdPaket());
                        String namaPaket = "";
                        if (paketEntity != null) {
                            namaPaket = paketEntity.getNamaPaket() + " ";
                        }

                        // if paket perusahaan
                        if ("paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {

                            String namaPerusahaan = "";
                            ImMasterEntity masterEntity = masterBo.getEntityMasterById(masterPerusahaan);
                            if (masterEntity != null) {
                                namaPerusahaan = masterEntity.getNama();
                            }

                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + " " + namaPerusahaan + " No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        } else {
                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + "No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        }

                    } else {
                        catatan = "Closing Pasien " + ketPoli + jenisPasien + ketResep + "No.Detail Checkup " + idDetailCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                    }

                    try {
                        Jurnal noJurnal = billingSystemBo.createJurnal(transId, hsCriteria, branchId, catatan, "Y");
                        response.setInvoice(noJurnal.getNoJurnal());
                        response.setStatus("success");
                        response.setMsg("[Berhasil]");
                    } catch (GeneralBOException e) {
                        logger.info("pendapatan rawat K: " + jumlahTindakan);
                        logger.info("pendapatan obat K: " + jumlahResep);
                        logger.info("piutang transitoris K: " + allTindakanTrans);
                        logger.info("piutang rawat inap D: " + jumlah);
                        logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
                        return response;
                    }
                }
            }
        } else {
            response.setStatus("ptpn");
        }

        return response;
    }

    private BigDecimal hitungPPN(BigDecimal harga) {
        BigDecimal jumlah = new BigDecimal(0);
        if (harga != null) {
            jumlah = harga.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return jumlah;
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

    private CrudResponse pindahPoli(String idDetailCheckup, String idPoli, String idDokter, String metodeBayar, String uangMuka) {
        logger.info("[CheckupDetailAction.pindahPoli] start process >>>");

        CrudResponse finalResponse = new CrudResponse();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        String genNoSep = "";

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(idPoli) && !"".equalsIgnoreCase(idDokter)) {
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
                    checkup.setNoCheckup(detailCheckup.getNoCheckup());

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
                        diagnosaRawat.setOrderCreated("Y");
                        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

                        try {
                            diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                        } catch (GeneralBOException e) {
                            logger.error("[Foun Error] when search diagnosa awal " + e);
                        }

                        if (diagnosaRawatList.size() > 0) {
                            diagnosaRawat = diagnosaRawatList.get(0);
                        }

                        // DIRUBAH SIGIT, 2020-05-07 dari checkup.getIdJenisPeriksaPasien -> detailCheckup.getIdJenisPeriksaPasien());
                        if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

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

                                            String namaDokter = "";
                                            String kodeDpjp = "";

                                            List<Dokter> dokterArrayList = new ArrayList<>();
                                            Dokter dokter = new Dokter();
                                            dokter.setIdDokter(idDokter);
                                            dokter.setFlag("Y");

                                            try {
                                                dokterArrayList = dokterBo.getByCriteria(dokter);
                                            } catch (GeneralBOException e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (dokterArrayList.size() > 0) {
                                                namaDokter = dokterArrayList.get(0).getNamaDokter();
                                                kodeDpjp = dokterArrayList.get(0).getNamaDokter();
                                            }

                                            SepRequest sepRequest = new SepRequest();
                                            sepRequest.setNoKartu(getPasien.getNoBpjs());
                                            sepRequest.setTglSep(now.toString());
                                            sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                            sepRequest.setJnsPelayanan("2");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                            sepRequest.setKlsRawat(detailCheckup.getIdKelas());//kelas rawat dari bpjs
                                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                            sepRequest.setAsalRujukan(detailCheckup.getPerujuk());//
                                            sepRequest.setTglRujukan(detailCheckup.getTglRujukan());
                                            sepRequest.setNoRujukan(detailCheckup.getNoRujukan());
                                            sepRequest.setPpkRujukan(detailCheckup.getNoPpk());
                                            sepRequest.setCatatan("");
                                            sepRequest.setDiagAwal(diagnosaRawat.getIdDiagnosa());
                                            sepRequest.setPoliTujuan(detailCheckup.getIdPelayananBpjs());
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
                                            sepRequest.setKodeDpjp(kodeDpjp);
                                            sepRequest.setNoTelp(getPasien.getNoTelp());
                                            sepRequest.setUserPembuatSep(user);

                                            SepResponse response = new SepResponse();

                                            try {
                                                response = bpjsBo.insertSepBpjs(sepRequest, branchId);
                                            } catch (Exception e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (response.getNoSep() != null) {

                                                headerDetailCheckup.setNoSep(response.getNoSep());
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
                                                klaimRequest.setCoderNik(getBranch.getCoderNik());

                                                KlaimResponse responseNewClaim = new KlaimResponse();
                                                try {
                                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, branchId);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                                tindakan.setIsIna("Y");

                                                try {
                                                    tindakanList = tindakanBo.getByCriteria(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                                    throw new GeneralBOException("Error when new tindakan", e);
                                                }

                                                BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));


                                                if (tindakanList.size() > 0) {
                                                    List<Tindakan> tindakans = new ArrayList<>();
                                                    for (Tindakan entity : tindakanList) {
                                                        if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObat = tarifRsObat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsAlkes = tarifRsAlkes.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKamar = tarifRsKamar.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsBmhp = tarifRsBmhp.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        Tindakan tin = new Tindakan();
                                                        tin.setIdTindakan(entity.getIdTindakan());
                                                        tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                                        tindakans.add(tin);
                                                    }
                                                    headerDetailCheckup.setTindakanList(tindakans);
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

                                                    //set tindakan untuk mendapatkan cover bpjs

                                                    klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                                    klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                                    klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                                    klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                                    klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                                    klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());
                                                    klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                                    klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                                    klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                                    klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                                    klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                                    klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());
                                                    klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                                    klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                                    klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                                    klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toString());
                                                    klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                                    klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                                    //end set tindakan
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

                                                                    //=====START SET TARIF BPJS DARI E-KLAIM====

                                                                    headerDetailCheckup.setTarifBpjs(tarifCbg);

                                                                    headerDetailCheckup.setKodeCbg(grouping1Response.getCbgCode());
                                                                    finalResponse.setStatus("success");

                                                                    //=====END SET TARIF BPJS DARI E-KLAIM=====
                                                                } else {
                                                                    finalResponse.setStatus("error");
                                                                    finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }
                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return finalResponse;
                                                            }

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
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return finalResponse;
                                                        }

                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                        return finalResponse;
                                                    }
                                                } else {
                                                    finalResponse.setStatus("error");
                                                    finalResponse.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                    logger.error("[CheckupAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                    return finalResponse;
                                                }
                                            } else {
                                                finalResponse.setStatus("error");
                                                finalResponse.setMsg("Failed To Generate SEP " + response.getMessage());
                                                logger.error("[CheckupAction.saveAdd] Failed To Generate SEP " + response.getMessage());
                                                return finalResponse;
                                            }
                                        } else {
                                            finalResponse.setStatus("error");
                                            finalResponse.setMsg("Tidak dapat menemukan ID pasien");
                                            logger.error("Found Error when search enemukan ID pasien");
                                            return finalResponse;
                                        }
                                    }
                                } else {
                                    finalResponse.setStatus("error");
                                    finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit");
                                    logger.error("Found Error when search branch id");
                                    return finalResponse;
                                }
                            }

                            headerDetailCheckup.setPerujuk(detailCheckup.getPerujuk());
                            headerDetailCheckup.setNamaPerujuk(detailCheckup.getNamaPerujuk());
                            headerDetailCheckup.setIdKelas(detailCheckup.getIdKelas());
                            headerDetailCheckup.setIdPelayananBpjs(detailCheckup.getIdPelayananBpjs());
                            headerDetailCheckup.setNoPpk(detailCheckup.getNoPpk());
                            headerDetailCheckup.setNoRujukan(detailCheckup.getNoRujukan());
                            headerDetailCheckup.setTglRujukan(detailCheckup.getTglRujukan());
                            headerDetailCheckup.setSuratRujukan(detailCheckup.getSuratRujukan());
                        }

                        if ("ptpn".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                            headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                            headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                        } else if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                            headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                            headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                        } else if ("umum".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            if (uangMuka != null && !"".equalsIgnoreCase(uangMuka)) {
                                headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                                headerDetailCheckup.setMetodePembayaran(metodeBayar);
                            }
                        } else {
                            headerDetailCheckup.setMetodePembayaran(metodeBayar);
                        }

                        headerDetailCheckup.setNoCheckup(detailCheckup.getNoCheckup());
                        headerDetailCheckup.setIdPelayanan(idPoli);
                        headerDetailCheckup.setStatusPeriksa("0");
                        headerDetailCheckup.setCreatedDate(now);
                        headerDetailCheckup.setCreatedWho(user);
                        headerDetailCheckup.setLastUpdate(now);
                        headerDetailCheckup.setLastUpdateWho(user);

                        List<DokterTeam> dokterTeams = new ArrayList<>();
                        DokterTeam dokterTeam = new DokterTeam();
                        dokterTeam.setIdDokter(idDokter);
                        dokterTeam.setIdPelayanan(idPoli);
                        dokterTeams.add(dokterTeam);
                        headerDetailCheckup.setDokterTeamList(dokterTeams);

                        headerDetailCheckup.setIdJenisPeriksaPasien(detailCheckup.getIdJenisPeriksaPasien());
                        headerDetailCheckup.setBranchId(branchId);

                        try {
                            finalResponse = checkupDetailBo.saveAdd(headerDetailCheckup);
                        } catch (GeneralBOException e) {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Error when saving add new detail poli " + e.getMessage());
                            logger.error("[CheckupDetailAction.rujukRawatInap] Error when saving add new detail poli, ", e);
                        }
                    }
                }
            }
        }

        return finalResponse;
    }

    private CrudResponse
    rujukRawatInap(String noCheckup, String idDetailCheckup, String kelas, String kamar, String metodeBayar, String uangMuka, String idDokterDpjp, String idPoli) {
        logger.info("[CheckupDetailAction.rujukRawatInap] start process >>>");

        CrudResponse finalResponse = new CrudResponse();

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
                        diagnosaRawat.setOrderCreated("Y");
                        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();

                        try {
                            diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                        } catch (GeneralBOException e) {
                            logger.error("[Foun Error] when search diagnosa awal " + e);
                        }

                        if (diagnosaRawatList.size() > 0) {
                            diagnosaRawat = diagnosaRawatList.get(0);

                            headerDetailCheckup.setDiagnosa(diagnosaRawat.getIdDiagnosa());
                            headerDetailCheckup.setNamaDiagnosa(diagnosaRawat.getKeteranganDiagnosa());
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

                        if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

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

                                            String namaDokter = "";
                                            String idDokter = "";
                                            String kodeDpjp = "";

                                            String noSkdpVal = detailCheckup.getNoSep();
                                            String noSkdp = noSkdpVal.substring(noSkdpVal.length() - 6);
                                            logger.info("NO SKDP : " + noSkdp);

                                            String noPPK = noSkdpVal.substring(0, 8);

                                            List<Dokter> dokterArrayList = new ArrayList<>();
                                            Dokter dokter = new Dokter();
                                            dokter.setIdDokter(idDokterDpjp);
                                            dokter.setFlag("Y");

                                            try {
                                                dokterArrayList = dokterBo.getByCriteria(dokter);
                                            } catch (GeneralBOException e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (dokterArrayList.size() > 0) {
                                                namaDokter = dokterArrayList.get(0).getNamaDokter();
                                                idDokter = dokterArrayList.get(0).getIdDokter();
                                                kodeDpjp = dokterArrayList.get(0).getKodeDpjp();
                                            }

//                                            List<DokterTeam> dokterList = new ArrayList<>();
//                                            DokterTeam dokterTeam = new DokterTeam();
//                                            dokterTeam.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
//
//                                            try {
//                                                dokterList = teamDokterBo.getByCriteria(dokterTeam);
//                                            } catch (GeneralBOException e) {
//                                                logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
//                                            }
//
//                                            if (dokterList.size() > 0) {
//                                                dokterTeam = dokterList.get(0);
//                                            }

                                            SepRequest sepRequest = new SepRequest();
                                            sepRequest.setNoKartu(getPasien.getNoBpjs());
                                            sepRequest.setTglSep(now.toString());
                                            sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                                            sepRequest.setJnsPelayanan("1");//jenis rawat inap, apa jalan 2 rawat jalan, 1 rawat inap
                                            sepRequest.setKlsRawat(detailCheckup.getIdKelas());//kelas rawat dari bpjs
                                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                                            sepRequest.setAsalRujukan("2");//
                                            sepRequest.setTglRujukan(now.toString());
                                            sepRequest.setNoRujukan(detailCheckup.getNoSep());
                                            sepRequest.setPpkRujukan(noPPK);
                                            sepRequest.setCatatan("");
                                            sepRequest.setDiagAwal(diagnosaRawat.getIdDiagnosa());
                                            sepRequest.setPoliTujuan(detailCheckup.getIdPelayananBpjs());
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
                                            sepRequest.setNoSuratSkdp(noSkdp);
                                            sepRequest.setKodeDpjp(kodeDpjp);
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

                                                headerDetailCheckup.setNoSep(response.getNoSep());

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
                                                klaimRequest.setCoderNik(getBranch.getCoderNik());

                                                KlaimResponse responseNewClaim = new KlaimResponse();
                                                try {
                                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, branchId);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                                tindakan.setIsIna("Y");

                                                try {
                                                    tindakanList = tindakanBo.getByCriteria(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                                    throw new GeneralBOException("Error when new tindakan", e);
                                                }

                                                BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                                                BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                                                BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));


                                                if (tindakanList.size() > 0) {
                                                    List<Tindakan> tindakans = new ArrayList<>();
                                                    for (Tindakan entity : tindakanList) {
                                                        if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObat = tarifRsObat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                                        }
                                                        if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsAlkes = tarifRsAlkes.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKamar = tarifRsKamar.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsBmhp = tarifRsBmhp.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        //--------------
                                                        if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }
                                                        if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                                            tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(entity.getTarifBpjs().toString()));

                                                        }

                                                        Tindakan tin = new Tindakan();
                                                        tin.setIdTindakan(entity.getIdTindakan());
                                                        tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                                        tindakans.add(tin);
                                                    }
                                                    headerDetailCheckup.setTindakanList(tindakans);
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

                                                    //set tindakan untuk mendapatkan cover bpjs

                                                    klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                                    klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                                    klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                                    klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                                    klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                                    klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());
                                                    klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                                    klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                                    klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                                    klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                                    klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                                    klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());
                                                    klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                                    klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                                    klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                                    klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toString());
                                                    klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                                    klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                                    //end set tindakan

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

                                                                    //=====START SET TARIF BPJS DARI E-KLAIM====

                                                                    headerDetailCheckup.setTarifBpjs(tarifCbg);
                                                                    headerDetailCheckup.setKodeCbg(grouping1Response.getCbgCode());
                                                                    finalResponse.setStatus("success");

                                                                    //=====END SET TARIF BPJS DARI E-KLAIM=====
                                                                } else {
                                                                    finalResponse.setStatus("error");
                                                                    finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }
                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return finalResponse;
                                                            }

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
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return finalResponse;
                                                        }

                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                        return finalResponse;
                                                    }
                                                } else {
                                                    finalResponse.setStatus("error");
                                                    finalResponse.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                    logger.error("[CheckupAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                    return finalResponse;
                                                }
                                            } else {
                                                finalResponse.setStatus("error");
                                                finalResponse.setMsg("Failed To Generate SEP " + response.getMessage());
                                                logger.error("[CheckupAction.saveAdd] Failed To Generate SEP " + response.getMessage());
                                                return finalResponse;
                                            }
                                        }
                                    }
                                } else {
                                    finalResponse.setStatus("error");
                                    finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit");
                                    logger.error("Found Error when search branch id");
                                    return finalResponse;
                                }
                            }

                            if ("ptpn".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                                headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                                headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                                headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                            }
                        } else if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
//                            HeaderDetailCheckup biayaCover = getBiayaAsuransi(detailCheckup.getIdDetailCheckup());
                            headerDetailCheckup.setIdAsuransi(detailCheckup.getIdAsuransi());
                            headerDetailCheckup.setNoKartuAsuransi(detailCheckup.getNoKartuAsuransi());
                            headerDetailCheckup.setCoverBiaya(new BigDecimal(uangMuka));
                            headerDetailCheckup.setMetodePembayaran(detailCheckup.getMetodePembayaran());
                        } else {
                            headerDetailCheckup.setMetodePembayaran(metodeBayar);
                        }

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
                        headerDetailCheckup.setIdJenisPeriksaPasien(detailCheckup.getIdJenisPeriksaPasien());
                        headerDetailCheckup.setBranchId(branchId);
                        headerDetailCheckup.setIdDokter(idDokterDpjp);
                        headerDetailCheckup.setIdPelayananDokter(idPoli);

                        if ("umum".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                        }

                        if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            headerDetailCheckup.setCoverBiaya(new BigDecimal(uangMuka));
                        }

                        try {
                            finalResponse = checkupDetailBo.saveAdd(headerDetailCheckup);
                        } catch (GeneralBOException e) {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Error when saving add new rawat inap " + e.getMessage());
                            logger.error("[CheckupDetailAction.rujukRawatInap] Error when saving add new detail poli, ", e);
                        }
                    } else {
                        finalResponse.setStatus("error");
                        finalResponse.setMsg("Tidak dapat menemukan NO Checkup Pasien");
                    }

                } else {
                    finalResponse.setStatus("error");
                    finalResponse.setMsg("Tidak dapat menemukan ID Detail Checkup Pasien");
                }
            }
        }

        logger.info("[CheckupDetailAction.rujukRawatInap] end process >>>");
        return finalResponse;

    }

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

    public List<KelasRuangan> getListKelasKamar(String kategori) {
        logger.info("[CheckupDetailAction.getListComboKelasRuangan] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        List<KelasRuangan> kelasRuanganList = new ArrayList<>();

        try {
            kelasRuanganList = kelasRuanganBo.getListKelasKamar(kategori);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboKelasRuangan] Error when get kelas ruangan ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[CheckupDetailAction.getListComboKelasRuangan] end process <<<");
        return kelasRuanganList;
    }

    public List<Ruangan> listRuangan(String idkelas, boolean flag, String kategori) {

        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<Ruangan> ruanganList = new ArrayList<>();
        Ruangan ruangan = new Ruangan();
        if (flag) {
            ruangan.setStatusRuangan("Y");
            ruangan.setSisaKuota(0);
        }
        ruangan.setIdKelasRuangan(idkelas);
        ruangan.setBranchId(CommonUtil.userBranchLogin());
        ruangan.setKategori(kategori);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");

        try {
            ruanganList = ruanganBo.getListRuangan(ruangan);
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

    public CrudResponse saveUpdateRuangan(String idRawatInap, String idRuangan, String idDetailCheckup, String tanggal) {
        logger.info("[CheckupDetailAction.saveUpdateRuangan] start process >>>");
        CrudResponse response = new CrudResponse();
        if (idRawatInap != null && !"".equalsIgnoreCase(idRawatInap) &&
                idRuangan != null && !"".equalsIgnoreCase(idRuangan) &&
                idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            try {
                response = checkupDetailBo.updateRuanganInap(idRawatInap, idRuangan, idDetailCheckup, tanggal);
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Found Error, " + e.getMessage());
                logger.error("[CheckupDetailAction.saveUpdateRuangan] Found problem when updating rawat inap, please inform to your admin.", e);
            }
        } else {
            response.setStatus("error");
            response.setMsg("Tidak ada data yang dikirim...!");
        }

        logger.info("[CheckupDetailAction.saveUpdateRuangan] end process >>>");
        return response;
    }

    public CrudResponse savePindahRuangan(String idRawatInapNew, String idRawatInapPindah) {
        logger.info("[CheckupDetailAction.savePindahRuangan] start process >>>");
        CrudResponse response = new CrudResponse();
        if (idRawatInapNew != null && !"".equalsIgnoreCase(idRawatInapNew) &&
                idRawatInapPindah != null && !"".equalsIgnoreCase(idRawatInapPindah)) {

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            try {
                response = checkupDetailBo.updatePindahRuangan(idRawatInapNew, idRawatInapPindah);
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Found Error, " + e.getMessage());
                logger.error("[CheckupDetailAction.savePindahRuangan] Found problem when updating rawat inap, please inform to your admin.", e);
            }
        } else {
            response.setStatus("error");
            response.setMsg("Tidak ada data yang dikirim...!");
        }

        logger.info("[CheckupDetailAction.savePindahRuangan] end process >>>");
        return response;
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

    public List<RawatInap> getListRuanganByIdDetailCheckup(String idDetailCheckup) {
        logger.info("[CheckupDetailAction.getListRuangInapByIdDetailCheckup] start process >>>");

        List<RawatInap> rawatInaps = new ArrayList<>();

        RawatInap rawatInap = new RawatInap();
        rawatInap.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");

        try {
            rawatInaps = rawatInapBo.getRuanganRawatInap(rawatInap);
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

        //jika bpjs dan ptpn
        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

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

                        List<Dokter> dokterList = new ArrayList<>();
                        Dokter dokter = new Dokter();
                        dokter.setIdDokter(checkup.getIdDokter());
                        dokter.setFlag("Y");
                        try {
                            dokterList = dokterBoProxy.getByCriteria(dokter);
                        } catch (GeneralBOException e) {
                            throw new GeneralBOException("Error when search idDokter " + e.getMessage());
                        }

                        String namaDokter = "";
                        String kodeDpjp = "";

                        if (dokterList.size() > 0) {
                            namaDokter = dokterList.get(0).getNamaDokter();
                            kodeDpjp = dokterList.get(0).getKodeDpjp();
                        }

                        SepRequest sepRequest = new SepRequest();
                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                        sepRequest.setTglSep(dateToday);
                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                        sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
                        sepRequest.setKlsRawat(checkup.getKelasPasien());//kelas rawat dari bpjs
                        sepRequest.setNoMr(checkup.getIdPasien());//id pasien

                        if (checkup.getRujuk() != null && !"".equalsIgnoreCase(checkup.getRujuk())) {
                            sepRequest.setAsalRujukan(checkup.getRujuk());
                        } else {
                            sepRequest.setAsalRujukan("2");
                        }

                        if (checkup.getTglRujukan() != null && !"".equalsIgnoreCase(checkup.getTglRujukan())) {
                            sepRequest.setTglRujukan(checkup.getTglRujukan());
                        } else {
                            sepRequest.setTglRujukan("");
                        }

                        if (checkup.getNoRujukan() != null && !"".equalsIgnoreCase(checkup.getNoRujukan())) {
                            sepRequest.setNoRujukan(checkup.getNoRujukan());
                        } else {
                            sepRequest.setNoRujukan("");
                        }

                        if (checkup.getIdPelayananBpjs() != null && !"".equalsIgnoreCase(checkup.getIdPelayananBpjs())) {
                            sepRequest.setPoliTujuan(checkup.getIdPelayananBpjs());
                        } else {
                            sepRequest.setPoliTujuan("IGD");
                        }

                        if (checkup.getNoPpkRujukan() != null && !"".equalsIgnoreCase(checkup.getNoPpkRujukan())) {
                            sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                        } else {
                            sepRequest.setPpkRujukan("");
                        }

                        sepRequest.setCatatan("");
                        sepRequest.setDiagAwal(checkup.getDiagnosa());
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
                        sepRequest.setKodeDpjp(kodeDpjp);
                        sepRequest.setNoTelp(getPasien.getNoTelp());
                        sepRequest.setUserPembuatSep(userLogin);

                        SepResponse response = new SepResponse();

                        try {
                            response = bpjsBoProxy.insertSepBpjs(sepRequest, userArea);
                        } catch (Exception e) {
                            logger.error("[CheckupAction.saveAdd] Error when insert SEP ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                            throw new GeneralBOException("Error when new insert SEP", e);
                        }

                        if (response.getNoSep() != null) {

                            genNoSep = response.getNoSep();

                            checkup.setNoSep(response.getNoSep());

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
                            klaimRequest.setCoderNik(getBranch.getCoderNik());

                            KlaimResponse responseNewClaim = new KlaimResponse();

                            try {
                                responseNewClaim = eklaimBoProxy.insertNewClaimEklaim(klaimRequest, userArea);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when new claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new claim", e);
                            }

                            List<Tindakan> tindakanList = new ArrayList<>();
                            Tindakan tindakan = new Tindakan();
                            tindakan.setBranchId(CommonUtil.userBranchLogin());
                            tindakan.setIsIna("Y");

                            try {
                                tindakanList = tindakanBoProxy.getByCriteria(tindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new tindakan", e);
                            }

                            BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                            BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                            BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));


                            if (tindakanList.size() > 0) {
                                List<Tindakan> tindakans = new ArrayList<>();

                                for (Tindakan entity : tindakanList) {

                                    if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObat = tarifRsObat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsAlkes = tarifRsAlkes.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }

                                    //--------------
                                    if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKamar = tarifRsKamar.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsBmhp = tarifRsBmhp.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }

                                    //--------------
                                    if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }

                                    Tindakan tin = new Tindakan();
                                    tin.setIdTindakan(entity.getIdTindakan());
                                    tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                    tindakans.add(tin);
                                }

                                checkup.setTindakanList(tindakans);
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


                                //set tindakan untuk mendapatkan cover bpjs

                                klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());
                                klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());
                                klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toString());
                                klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                //end set tindakan

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
                                    logger.error("[CheckupAction.saveAdd] Error when update claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                    throw new GeneralBOException("Error when update claim, [" + claimEklaimResponse.getMessage() + "]", e);
                                }

                                if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                    Grouping1Response grouping1Response = new Grouping1Response();

                                    try {
                                        grouping1Response = eklaimBoProxy.groupingStage1Eklaim(genNoSep, userArea);
                                    } catch (GeneralBOException e) {
                                        logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                        throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]", e);
                                    }

                                    // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                    if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                        BigDecimal tarifCbg = new BigDecimal(0);
                                        if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                            if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());

                                                //=====kode CBG INA=======
                                                checkup.setKodeCbg(grouping1Response.getCbgCode());

                                                //======START SET TARIF BPJS=========

                                                checkup.setTarifBpjs(tarifCbg);

                                                //======END SET TARIF BPJS=========
                                            } else {
                                                logger.error("[CheckupAction.saveAdd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                                throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                            }
                                        } else {
                                            logger.error("[CheckupAction.saveAdd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                            throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                        }


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
                                    throw new GeneralBOException("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                }
                            } else {
                                logger.error("[CheckupAction.saveAdd] Error when get pastien Eklaim, " + responseNewClaim.getMsg());
                                throw new GeneralBOException("Error when get pastien Eklaim, [" + responseNewClaim.getMsg() + "]");
                            }
                        } else {
                            logger.error("[CheckupAction.saveAdd] Error when generate SEP, " + response.getMessage());
                            throw new GeneralBOException("Error when generate SEP, [" + response.getMessage() + "]");
                        }

                    }
                } else {
                    logger.error("[CheckupAction.saveAdd] Error when search PPK pelayanan");
                    throw new GeneralBOException("Error when search PPK pelayanan");
                }
            }

            if (checkup.getIdPelayananBpjs() != null && !"".equalsIgnoreCase(checkup.getIdPelayananBpjs())) {
                checkup.setIdPelayananBpjs(checkup.getIdPelayananBpjs());
            } else {
                checkup.setIdPelayananBpjs("IGD");
            }

        }

        try {

            checkup.setTglLahir(java.sql.Date.valueOf(checkup.getStTglLahir()));
            checkup.setNoCheckup(noCheckup);
            checkup.setBranchId(userArea);
            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");
            checkup.setStatusPeriksa("1");
            checkup.setUrlKtp(checkup.getUrlKtp());

            if (this.fileUploadDoc != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadDocContentType)) {
                    if (this.fileUploadDoc.length() <= 5242880 && this.fileUploadDoc.length() > 0) {

                        // file name
                        String fileName = this.fileUploadDocFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-" + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
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
                            throw new GeneralBOException("Found Error when upload images rujukan " + e.getMessage());
                        }
                    }
                }
            }

            if (this.fileUploadPolisi != null) {
                if ("image/jpeg".equalsIgnoreCase(this.fileUploadPolisiContentType)) {
                    if (this.fileUploadPolisi.length() <= 5242880 && this.fileUploadPolisi.length() > 0) {

                        // file name
                        String fileName = this.fileUploadPolisiFileName;
                        String fileNameReplace = fileName.replace(" ", "_");
                        String newFileName = checkup.getNoKtp() + "-" + dateFormater("dd") + dateFormater("MM") + dateFormater("yy") + "-" + fileNameReplace;
                        // deklarasi path file
                        String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN;
                        logger.info("[CheckupAction.uploadImages] FILEPATH :" + filePath);

                        // persiapan pemindahan file
                        File fileToCreate = new File(filePath, newFileName);

                        try {
                            // pemindahan file
                            FileUtils.copyFile(this.fileUploadPolisi, fileToCreate);
                            logger.info("[CheckupAction.uploadImages] SUCCES PINDAH");
                            checkup.setUrlDocRujuk(newFileName);
                        } catch (IOException e) {
                            logger.error("[CheckupAction.uploadImages] error, " + e.getMessage());
                            throw new GeneralBOException("Found Error when upload images rujukan " + e.getMessage());
                        }
                    }
                }
            }

            checkupBoProxy.saveAdd(checkup);

        } catch (GeneralBOException e) {
            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
            throw new GeneralBOException("Found Error when adding item " + e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.saveAdd] end process >>>");
        return "init_add";

    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public String printResepPasien() {

        HeaderCheckup checkup = new HeaderCheckup();
        String idResep = getIdResep();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();
        PermintaanResep permintaanResep = new PermintaanResep();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        try {
            permintaanResep = checkupDetailBoProxy.getDataDokter(idResep);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }

        if (checkup != null) {

            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + permintaanResep.getTtdDokter());
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("resepId", idResep);
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
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

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

    public String printSuratKeterangan() {

        HeaderCheckup checkup = new HeaderCheckup();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            reportParams.put("dokter", "");
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            if (checkup.getTglLahir() != null) {
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
            }
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
            if (checkup.getTglCheckup() != null) {
                String formatCheckup = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglCheckup());
                reportParams.put("tglCheckup", formatCheckup);
            }
            reportParams.put("ketCheckup", checkup.getKeterangan());
            reportParams.put("idDetailCheckup", id);
            reportParams.put("diagnosa", checkup.getDiagnosa() + "-" + checkup.getNamaDiagnosa());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        return "print_checkup_ulang";
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
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            RekananOpsBo rekananOpsBo = (RekananOpsBo) ctx.getBean("rekananOpsBoProxy");
            String userBranch = CommonUtil.userBranchLogin();

            RekananOps ops = new RekananOps();
            if ("rekanan".equalsIgnoreCase(jenisPasien)) {
                try {
                    ops = rekananOpsBo.getDetailRekananOpsByDetail(idDetail, userBranch);
                } catch (GeneralBOException e) {
                    logger.error("Error, " + e.getMessage());
                }
            }

            String jenPasien = "";
            if ("rekanan".equalsIgnoreCase(jenisPasien) && "Y".equalsIgnoreCase(ops.getIsBpjs())) {
                jenPasien = "bpjs";
            } else {
                jenPasien = jenisPasien;
            }

            String idPaket = "";
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetail);
            if (detailCheckupEntity != null) {
                idPaket = detailCheckupEntity.getIdPaket();
            }

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

                        if (!"".equalsIgnoreCase(idPaket) && idPaket != null) {

                            // mengambil berdasarkan idPaket dan idTindakan;
                            MtSimrsItemPaketEntity itemPaketEntity = riwayatTindakanBo.getItemPaketEntity(idPaket, entity.getIdTindakan());
                            if (itemPaketEntity != null) {

                                // jika ada paket;
                                riwayatTindakan.setTotalTarif(new BigDecimal(itemPaketEntity.getHarga()));
                            } else {

                                // jika tidak ada item paket namun golongan paket, maka tarif tindakan asli yang dipakai
                                riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                            }

                        } else {

                            // jika bukan paket maka tarif tindakan asli
                            riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        }

                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());
                        riwayatTindakan.setIdRuangan(entity.getIdRuangan());

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

                        PeriksaLab lab = new PeriksaLab();

                        try {
                            lab = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdLab(), entity.getIdPeriksaLab());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        String namaLab = "";
                        if ("radiologi".equalsIgnoreCase(lab.getKategoriLabName())) {
                            namaLab = "Radiologi";
                        } else {
                            namaLab = "Laboratorium";
                        }
                        riwayatTindakan.setNamaTindakan("Periksa " + namaLab + " " + entity.getLabName());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket) && idPaket != null) {

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null) {

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            if ("rekanan".equalsIgnoreCase(jenisPasien)) {
                                if (ops.getDiskon() != null) {
                                    riwayatTindakan.setTotalTarif(lab.getTarif().multiply(ops.getDiskon()));
                                } else {
                                    riwayatTindakan.setTotalTarif(lab.getTarif());
                                }
                            } else {
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                            }
                        }

                        riwayatTindakan.setKeterangan(lab.getKategoriLabName());
                        riwayatTindakan.setJenisPasien(jenPasien);
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

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);

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

                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            if ("rekanan".equalsIgnoreCase(jenisPasien)) {
                                if (ops.getDiskon() != null) {
                                    riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()).multiply(ops.getDiskon()));
                                } else {
                                    riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                                }
                            } else {
                                riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            }
                            riwayatTindakan.setKeterangan("resep");
                            riwayatTindakan.setJenisPasien(obatDetailList.getJenisResep());
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
                    orderGizi.setDiterimaFlag("Y");
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
                                if ("rekanan".equalsIgnoreCase(jenisPasien)) {
                                    if (ops.getDiskon() != null) {
                                        riwayatTindakan.setTotalTarif(gizi.getTarifTotal().multiply(ops.getDiskon()));
                                    } else {
                                        riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                    }
                                } else {
                                    riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                }

                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenPasien);
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

    public CheckResponse saveUpdateDataAsuransi(String idDetailCheckup, String noPolisi, String tgl, String fotoRujuak) throws IOException {
        CheckResponse response = new CheckResponse();

        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String user = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setIdDetailCheckup(idDetailCheckup);
        headerCheckup.setNoRujukan(noPolisi);
        headerCheckup.setTglRujukan(tgl);
        headerCheckup.setLastUpdate(updateTime);
        headerCheckup.setLastUpdateWho(user);

        if (fotoRujuak != null && !"".equalsIgnoreCase(fotoRujuak)) {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(fotoRujuak);
            logger.info("Decoded upload data : " + decodedBytes.length);
            String fileName = idDetailCheckup + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN + fileName;
            logger.info("File save path : " + uploadFile);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

            if (image == null) {
                logger.error("Buffered Image is null");
            } else {
                File f = new File(uploadFile);
                // write the image
                ImageIO.write(image, "png", f);
                headerCheckup.setUrlDocRujuk(fileName);
            }
        }

        try {
            response = checkupDetailBo.saveUpdateDataAsuransi(headerCheckup);
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMessage("Error when save data asurasi " + e.getMessage());
        }
        return response;
    }

    public HeaderDetailCheckup getBiayaAsuransi(String idDetailCheckup) {
        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        try {
            detailCheckup = checkupDetailBo.getCoverBiayaAsuransi(idDetailCheckup);
        } catch (GeneralBOException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return detailCheckup;
    }

    public String initRekamMedik() {
        String id = getId();
        String tipe = getTipe();
        String jk = "";
        HeaderCheckup checkup = new HeaderCheckup();

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
        }

        if (checkup != null) {
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
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
                    jk = "Laki-Laki";
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
            detailCheckup.setNoRujukan(checkup.getNoRujukan());
            detailCheckup.setTglRujukan(checkup.getTglRujukan());
            detailCheckup.setSuratRujukan(checkup.getUrlDocRujuk());
            detailCheckup.setIdAsuransi(checkup.getIdAsuransi());
            detailCheckup.setNamaAsuransi(checkup.getNamaAsuransi());
            detailCheckup.setCoverBiaya(checkup.getCoverBiaya());
            detailCheckup.setTipePelayanan(tipe);
            setHeaderDetailCheckup(detailCheckup);

        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }

        return "init_rekam_medik";
    }

    public String printFormulirPindahRS() {

        HeaderCheckup checkup = new HeaderCheckup();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String branchName = CommonUtil.userBranchNameLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            reportParams.put("dokter", "");
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", branchName);
            reportParams.put("idPasien", checkup.getIdPasien());
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
            if (checkup.getTglCheckup() != null && !"".equalsIgnoreCase(checkup.getTglCheckup().toString())) {
                String formatCheckup = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglCheckup());
                reportParams.put("tglCheckup", formatCheckup);
            }
            reportParams.put("ketCheckup", checkup.getKeterangan());
            reportParams.put("idDetailCheckup", id);


            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }
        return "print_pindah_rs";
    }

    public String printSuratPernyataan() {

        HeaderCheckup checkup = new HeaderCheckup();
        Pasien pasien = new Pasien();
        String id = getId();
        String jk = "";
        String tipe = getTipe();
        String idPatien = getIdPasien();
        String idRm = getIds();

        String branch = CommonUtil.userBranchLogin();
        String branchName = CommonUtil.userBranchNameLogin();
        String logo = "";
        Branch branches = new Branch();
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found Error when search data detail pasien " + e.getMessage());
            }
        }

        if (idPatien != null && !"".equalsIgnoreCase(idPatien)) {
            try {
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
                List<Pasien> pasienList = new ArrayList<>();

                Pasien listPasien = new Pasien();
                listPasien.setIdPasien(idPatien);

                try {
                    pasienList = pasienBo.getByCriteria(listPasien);
                } catch (GeneralBOException e) {
                    logger.error("Found Error when search data pasien " + e.getMessage());
                }
                if (pasienList.size() > 0) {
                    pasien = pasienList.get(0);
                }

            } catch (GeneralBOException e) {
                logger.error("Fund Error when search data pasien, " + e.getMessage());
            }
        }

        if (checkup.getIdDetailCheckup() != null) {

            reportParams.put("dokter", "");
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", branchName);
            reportParams.put("unitKota", branches.getBranchAddress());
            reportParams.put("tglDibawah", branches.getBranchAddress() + ", " + tglToday);
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
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
            if (checkup.getTglCheckup() != null && !"".equalsIgnoreCase(checkup.getTglCheckup().toString())) {
                String formatCheckup = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglCheckup());
                reportParams.put("tglCheckup", formatCheckup);
            }
            reportParams.put("ketCheckup", checkup.getKeterangan());
            reportParams.put("idDetailCheckup", id);
            reportParams.put("namaRuang", checkup.getNamaRuangan());
            reportParams.put("namaPelayanan", checkup.getNamaPelayanan());
            reportParams.put("rawatInapId", checkup.getIdRawatInap());
            reportParams.put("noBpjs", checkup.getNoBpjs());
            reportParams.put("auto", "Autoanamnesis : " + checkup.getAutoanamnesis());
            reportParams.put("hetero", "Heteroanamnesis : " + checkup.getHeteroanamnesis());
            if (checkup.getTglLahir() != null) {
                String tahun = calculateAge(checkup.getTglLahir(), false);
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
                reportParams.put("umur", tahun);
            }
            reportParams.put("agama", checkup.getAgama());
            reportParams.put("pekerjaan", checkup.getProfesi());
            if (checkup.getCreatedDate() != null && !"".equalsIgnoreCase(checkup.getCreatedDate().toString())) {
                String tglMasuk = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getCreatedDate());
                reportParams.put("tglMasuk", tglMasuk);
            }

            String content1 = "I.\tPersetujuan Untuk Perawatan dan Pengobatan\n" +
                    "a. Saya mengetahui bahwa Saya memiliki kondisi yang membutuhkan perawatan medis, Saya memberi izin kepada dokter dan profesi kesehatan lainnya untuk melakukan prosedur diagnostik dan untuk memberi pengobatan medis seperti yang diperlukan untuk penilaian secara profesional. Prosedur diagnostik dan perawatan medis termasuk tetapi tidak terbatas pada ECG, X Ray, Tes Darah, terapi fisik dan pemberiaan obat.\n" +
                    "b. Saya sadar bahwa praktek kedokteran dan ilmu bedah bukanlah ilmu pasti dan Saya mengakui bahwa tidak ada jaminan atas hal apapun, terhadap perawatan prosedur atau pemeriksaan apapun yang dilakukan kepada saya\n" +
                    "c. Saya mengerti dan memahami bahwa:\n" +
                    "1. Saya memiliki hak untuk menanyakan tentang pengobatan yang diusulkan termasuk identitas setiap orang yang memberikan atau mengamati pengobatan setiap saat\n" +
                    "2. Saya memiliki hak untuk persetujuan, atau menolak persetujuan untuk setiap prosedur atau terapi (injeksi, rawat luka, pemasangan gips, infus, pemeriksaan penunjang lain)\n" +
                    "d. Privasi:\n" +
                    "Saya memberi kuasa kepada " + branchName + " untuk menjaga privasi dan kerahasiaan penyakit saya selama dalam perawatan\n" +
                    "e. Rahasia Kedokteran:\n" +
                    "Saya setuju kepada " + branchName + " wajib menjamin rahasia kedokteran Saya baik untuk kepentingan perawatan atau pengobatan, pendidikan maupun penelitian, kecuali saya mengucapkan sendiri atau orang lain yang saya beri kuasa sebagai penjamin, Saya setuju untuk membuka rahasia kedokteran terkait dengan kondisi kesehatan, asuhan dan pengobatan yang saya terima kepada:\n" +
                    "a. Dokter atau tenaga kesehatan yang memberikan asuhan kesehatan kepada saya\n" +
                    "b. Perusahaan asuransi kesehatan BPJS atau perusahaan lainnya atau pihak lain yang menjamin pembiayaan saya\n" +
                    "c. Pihak lain yang saya kehendaki\n" +
                    "II.\tBarang-Barang Milik Pasien\n" +
                    "a. Saya telah mengerti bahwa rumah sakit tidak bertanggung jawab atas semua kehilangan barang-barang milik saya, dan saya secara pribadi bertanggung jawab terhadap barang berharga yang saya miliki diantaranya uang, perhiasan, buku, cek, handphone, kartu kredit serta barang-barang berharga lainnya. dan apabila saya membutuhkan maka saya dapat menitipkan barang-barang saya kepada rumah sakit\n";

            String content2 = "III.Hak Pasien\n" +
                    "(Sesuai Permenkes No 4 Tahun 2018)\n" +
                    "1. Memperoleh informasi mengenai tata tertib dan peraturan yang berlaku di rumah sakit \n" +
                    "2. Memperoleh informasi tentang hak dan kewajiban pasien \n" +
                    "3. Memperoleh pelayanan yang manusiawi, adil, jujur, dan tanpa diskriminasi\n" +
                    "4. Memperoleh layanan kesehatan yang bermutu sesuai dengan standar profesi dan prosedur operasional (SPO)\n" +
                    "5. Memperoleh layanan yang efektif dan efisien sehingga pasien terhindar dari kerugian fisik\n" +
                    "6. Mengajukan pengaduan atas kualitas pelayanan yang didapatkan\n" +
                    "7. Memilih dokter dan kelas perawatan sesuai dengan keinginan dan peraturan yang berlaku di Rumah Sakit\n" +
                    "8. Meminta konsultasi tentang penyakit yang dideritanya kepada dokter lain yang mempunyai surat izin praktek (SIP) baik didalam maupun diluar rumah sakit\n" +
                    "9. Mendapatkan privasi dan kerahasiaan penyakit yang diderita termasuk data-data medisnya\n" +
                    "10. Mendapatkan informasi yang meliputi diagnosis dan tata cara tindakan medis, tujuan tindakan medis, alternatif tindakan, resiko dan komplikasi yang mungkin terjadi, dan prognosis terhadap tindakan yang dilakukan serta perkiraan biaya pengobatan\n" +
                    "11. Memberikan persetujuan atau menolak atas tindakan yang akan dilakukan oleh tenaga kesehatan terhadap penyakit yang dideritanya\n" +
                    "12. Didampingi keluarga dalam keadaan kritis\n" +
                    "13. Menjalankan ibadah sesuai agama atau kepercayaan yang dianutnya selama itu tidak mengganggu pasien lainnya\n" +
                    "14. Memperoleh keamanan dan keselamatan dirinya selama dalam perawatan di rumah sakit\n" +
                    "15. Mengajukan usul, saran perbaikan atas perlakukan rumah sakit terhadap dirinya\n" +
                    "16. Menolak bimbingan rohani yang tidak sesuai dengan agama dan kepercayaan yang dianutnya\n" +
                    "17. Menggugat atau menuntut rumah sakit apabila rumah sakit diduga memberikan pelayanan yang tidak sesuai dengan standart baik secara perdata maupun pidana\n" +
                    "18. Mengeluhkan pelayanan rumah sakit yang tidak sesuai dengan stardar pelayanan melalui media\n" +
                    "IV. Kewajiban Pasien dan Keluarga Pasien\n" +
                    "1. Mematuhi peraturan yang berlaku di Rumah Sakit Gatoel\n" +
                    "2. Menggunakan fasilitas rumah sakit " + branchName + " secara bertanggung jawab\n" +
                    "3. Menghormati hask pasien lain, pengunjung dan hak tenaga kesehatan serta petugas lainnya yang bekerja di rumah sakit\n" +
                    "4. Memberikan informasi yang jujur, lengkap dan akurat sesuai dengan kemampuan tetang masalah kesehatan\n" +
                    "5. Memberikan informasi tentang kemampuan finansial dan jaminan kesehatan yang dimiliki\n" +
                    "6. Mematuhi rencana terapi yang direkomendasikan oelh tenaga kesehatanan di rumah sakit dan di setujui oleh pasien yang bersangkutan setelah mendapatkan penjelasan sesuai dengan ketentuan peraturan perundang udangan\n" +
                    "7. Menerima segala kesalahan atas keputusan pribadinya untuk menolak rencana terapi yang di rekomendasikan oleh tenaga kesehatan dan/tidak mematuhi petunjuk yang diberikan oleh tenaga kesehatan untuk penyembuhan penyakit atau masalah kesehatannya\n" +
                    "8. Memberikan imbalan jasa atas pelayanan yang diterima\n" +
                    "Saya telah membaca dan sepenuhnya setuju dengan setiap pernyataan yang tersebut diatas dan menandatangani tanpa paksaan dan dengan kesadaran penuh\n";

            reportParams.put("data1", content1);
            reportParams.put("data2", content2);

            if ("SP15".equalsIgnoreCase(tipe) || "SP16".equalsIgnoreCase(tipe) || "SP17".equalsIgnoreCase(tipe)) {
                String penunjang = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), null);
                String terapi = checkupBoProxy.getResepPasien(checkup.getIdDetailCheckup());
                String diagnosaPrimer = checkupBoProxy.getDiagnosaPrimer(checkup.getIdDetailCheckup());
                String diagnosaSekunder = checkupBoProxy.getDiagnosaSekunder(checkup.getIdDetailCheckup());
                String tindakanIcd9 = checkupBoProxy.getTindakanRawatICD9(checkup.getIdDetailCheckup());
                String lab = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), "lab");
                String radiologi = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), "radiologi");
                reportParams.put("penunjang", penunjang);
                reportParams.put("diagnosaPrimer", diagnosaPrimer);
                reportParams.put("diagnosaSekunder", diagnosaSekunder);
                reportParams.put("diagnosa", diagnosaSekunder);
                reportParams.put("terapi", terapi);
                reportParams.put("tindakan", tindakanIcd9);
                reportParams.put("lab", lab);
                reportParams.put("radiologi", radiologi);
                reportParams.put("keterangan", checkup.getCatatan());
                DokterTeam dokterTeam = teamDokterBoProxy.getNamaDokter(checkup.getIdDetailCheckup());
                reportParams.put("dokter", dokterTeam.getNamaDokter());
                reportParams.put("sip", dokterTeam.getSip());
                reportParams.put("diagnosaMasuk", diagnosaSekunder);
            }
        }

        if (pasien.getIdPasien() != null) {

            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", branchName);
            reportParams.put("unitKota", branches.getBranchAddress());
            reportParams.put("idPasien", pasien.getIdPasien());
            reportParams.put("logo", logo);
            reportParams.put("nik", pasien.getNoKtp());
            reportParams.put("nama", pasien.getNama());
            if ("L".equalsIgnoreCase(pasien.getJenisKelamin())) {
                jk = "Laki-Laki";
            } else {
                jk = "Perempuan";
            }
            reportParams.put("jenisKelamin", jk);
            if (pasien.getTglLahir() != null) {
                String tahun = calculateAge(java.sql.Date.valueOf(pasien.getTglLahir()), false);
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(java.sql.Date.valueOf(pasien.getTglLahir()));
                reportParams.put("tglLahir", pasien.getTempatLahir() + ", " + formatDate);
                reportParams.put("umur", tahun);
            }

            String pernyataan = "";
            if ("SP10".equalsIgnoreCase(tipe)) {
                pernyataan = "Pasien / pihak keluarga menghendaki mengunakan BPJS akan " +
                        "tetapi belum bisa cetak SEP karena <b>NON AKTIF DI AKHIR BULAN.</b> " +
                        "Petugas Rumah Sakit sudah memotivasi untuk segera mengurus ke kantor " +
                        "BPJS agar kartu BPJS aktif dan bisa cetak SEP dalam waktu 3x24 jam. " +
                        "Jika dalam waktu tersebut SEP masih belum bisa tercetak / kartu BPJS " +
                        "belum bisa digunakan, maka tercatat sebagai pasien <b>SWASTA MURNI</b> sampai " +
                        "pulang / sembuh.";
            }
            if ("SP11".equalsIgnoreCase(tipe)) {
                pernyataan = "Pasien / pihak keluarga menghendaki menggunakan BPJS tetapi bisa cetak SEP dikarenakan <b>Kartu BPJS TIDAK DIBAWA</b>. Petugas Rumah Sakit sudah memotivasi untuk mengambilnya di rumah dan menyerahkan kartu BPJSnya dalam waktu 3x24 jam. Jika dalam waktu tersebut masih belum bisa tercetak / kartu BPJS belum diserahkan, maka tercatat sebagai pasien <b>SWASTA MURNI</b> sampai pulang / sembuh. Apabila kamar yang ditempati lebih tinggi dari hak kamar BPJSnya, dan pasien tidak berkenan menepati sesuai hak kamarnya, maka selisih dibayar di tempat.\n";
            }
            if ("SP12".equalsIgnoreCase(tipe)) {
                pernyataan = "Pasien / pihak keluarga menghendaki menggunakan BPJS tetapi bisa cetak SEP dikarenakan <b>TIDAK DITANGGUNG / KELUAR KEMAUAN SENDIRI</b>. Petugas Rumah Sakit sudah memotivasi untuk segera mengurus ke kantor BPJS aktif dan bisa cetak SEP dalam waktu 3x24 jam. Jika dalam waktu tersebut masih belum bisa tercetak / kartu BPJS belum diserahkan, maka tercatat sebagai pasien <b>SWASTA MURNI</b> sampai pulang / sembuh.";
            }
            if ("SP13".equalsIgnoreCase(tipe)) {
                pernyataan = "Pasien / pihak keluarga menghendaki menggunakan BPJS tetapi tidak aktif karena <b>peserta dalam 45 hari pengurusan administrasi denda pelayanan</b>. Petugas Rumah Sakit sudah memotivasi untuk mengurus ke kantor BPJS dalam waktu 3x24 jam. Jika dalam waktu tersebut masih belum bisa tercetak / kartu BPJS belum bisa digunakan, maka tercatat sebagai pasien SWASTA MURNI sampai pulang / sembuh.";
            }
            if ("SP14".equalsIgnoreCase(tipe)) {
                pernyataan = "Pasien / pihak keluarga menghendaki menggunakan BPJS tetapi belum bisa cetak SEP karena <b>PENANGGUHAN PESERTA</b>. Petugas Rumah Sakit sudah memotivasi untuk mengurus ke kantor BPJS agar kartu BPJS aktif dan bisa cetak SEP dalam waktu 3x24 jam. Jika dalam waktu tersebut masih belum bisa tercetak / kartu BPJS belum bisa digunakan, maka tercatat sebagai pasien <b>SWASTA MURNI</b> sampai pulang / sembuh. Apabila kamar yang ditempati lebih tinggi dari hak kamar BPJSnya, dan pasien tidak berkenan menepati sesuai hak kamarnya, maka selisih dibayar di tempat";
            }

            reportParams.put("pernyataan_sep", pernyataan);
        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        if (checkup.getIdDetailCheckup() != null || pasien.getIdPasien() != null) {
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");

            if (idRm != null && !"".equalsIgnoreCase(idRm) && !"undefined".equalsIgnoreCase(idRm)) {
                StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                status.setNoCheckup(checkup.getNoCheckup());
                status.setIdDetailCheckup(checkup.getIdDetailCheckup());
                status.setIdPasien(checkup.getIdPasien());
                status.setIdRekamMedisPasien(idRm);
                status.setIsPengisian("Y");
                status.setAction("C");
                status.setFlag("Y");
                status.setCreatedWho(user);
                status.setCreatedDate(updateTime);
                status.setLastUpdateWho(user);
                status.setLastUpdate(updateTime);
                rekamMedikBo.saveAdd(status);
            }

            if ("CK01".equalsIgnoreCase(tipe)) {
                return "print_general_concent";
            }
            if ("CK02".equalsIgnoreCase(tipe)) {
                return "print_pelepasan_informasi";
            }
            if ("CK03".equalsIgnoreCase(tipe)) {
                return "print_lembar_konsultasi";
            }
            if ("CK04".equalsIgnoreCase(tipe)) {
                return "print_hak_kewajiban_pasien";
            }
            if ("SP01".equalsIgnoreCase(tipe)) {
                return "print_gagal_sep";
            }
            if ("SP02".equalsIgnoreCase(tipe)) {
                return "print_selisih_bayar";
            }
            if ("SP03".equalsIgnoreCase(tipe)) {
                return "print_penolakan_tindakan";
            }
            if ("SP04".equalsIgnoreCase(tipe)) {
                return "print_surat_kematian";
            }
            if ("SP05".equalsIgnoreCase(tipe)) {
                return "print_pengantar_jensah";
            }
            if ("SP06".equalsIgnoreCase(tipe)) {
                return "print_non_bpjs";
            }
            if ("SP07".equalsIgnoreCase(tipe)) {
                return "print_kronologi";
            }
            if ("SP08".equalsIgnoreCase(tipe)) {
                return "print_pernyataan_rujuak";
            }
            if ("SP09".equalsIgnoreCase(tipe)) {
                return "print_pernyataan_selisih";
            }

            if ("SP10".equalsIgnoreCase(tipe) || "SP11".equalsIgnoreCase(tipe) ||
                    "SP12".equalsIgnoreCase(tipe) || "SP13".equalsIgnoreCase(tipe) ||
                    "SP14".equalsIgnoreCase(tipe)) {

                return "print_pernyataan_gagal_sep";
            }

            if ("SP15".equalsIgnoreCase(tipe)) {
                return "print_resume_medis_rawat_jalan";
            }
            if ("SP16".equalsIgnoreCase(tipe)) {
                return "print_resume_medis_rawat_inap";
            }
            if ("SP17".equalsIgnoreCase(tipe)) {
                return "print_gawat_darurat";
            }
            if ("SK01".equalsIgnoreCase(tipe)) {
                return "print_keterangan_dokter";
            }
            if ("SK02".equalsIgnoreCase(tipe)) {
                return "print_kamar_penuh";
            }
            if ("SK03".equalsIgnoreCase(tipe)) {
                return "print_keterangan_kesehatan";
            }
            if ("SK04".equalsIgnoreCase(tipe)) {
                return "print_keterangan_kelahiran";
            }
            if ("SK05".equalsIgnoreCase(tipe)) {
                return "print_keterangan_rekomendasi_dpjp";
            }
            if ("HV01".equalsIgnoreCase(tipe)) {
                return "print_persetujuan_hiv";
            }
            if ("RI01".equalsIgnoreCase(tipe)) {
                return "print_rawat_inap";
            }
            if ("RI01".equalsIgnoreCase(tipe)) {
                return "print_rawat_inap";
            }

        } else {
            return null;
        }

        return null;
    }

    public String printLabRadiologi() {

        HeaderCheckup checkup = new HeaderCheckup();
        String lab = getLab();
        String id = getId();
        String tipe = getTipe();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            PeriksaLab periksalb = new PeriksaLab();
            try {
                periksalb = periksaLabBoProxy.getNamaLab(lab);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if (periksalb.getIdPeriksaLab() != null) {
                reportParams.put("title", "Hasil Periksa Lab " + periksalb.getKategoriLabName());
            }

            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idPeriksaLab", lab);
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
            reportParams.put("diagnosa", checkup.getNamaDiagnosa());
            reportParams.put("petugas", periksalb.getNamaPetugas());
            reportParams.put("dokter", periksalb.getNamaDokter());
            reportParams.put("ttdDokter", periksalb.getTtdDokter());
            reportParams.put("ttdPetugas", periksalb.getTtdPetugas());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        if ("lab".equalsIgnoreCase(tipe)) {
            return "print_lab";
        } else {
            return "print_radiologi";
        }
    }

    private String calculateAge(java.sql.Date birthDate, boolean justTahun) {
        String umur = "";
        if (birthDate != null && !"".equalsIgnoreCase(birthDate.toString())) {
            int years = 0;
            int months = 0;
            int days = 0;

            //create calendar object for birth day
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTimeInMillis(birthDate.getTime());

            //create calendar object for current day
            long currentTime = System.currentTimeMillis();
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(currentTime);

            //Get difference between years
            years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
            int currMonth = now.get(Calendar.MONTH) + 1;
            int birthMonth = birthDay.get(Calendar.MONTH) + 1;

            //Get difference between months
            months = currMonth - birthMonth;

            //if month difference is in negative then reduce years by one
            //and calculate the number of months.
            if (months < 0) {
                years--;
                months = 12 - birthMonth + currMonth;
                if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                    months--;
            } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                years--;
                months = 11;
            }

            //Calculate the days
            if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
                days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
            else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                int today = now.get(Calendar.DAY_OF_MONTH);
                now.add(Calendar.MONTH, -1);
                days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
            } else {
                days = 0;
                if (months == 12) {
                    years++;
                    months = 0;
                }
            }

            if (justTahun) {
                umur = String.valueOf(years);
            } else {
                if (days > 0) {
                    umur = years + " Tahun, " + months + " Bulan, " + days + " Hari";
                } else if (months > 0) {
                    umur = years + " Tahun, " + months + " Bulan";
                } else {
                    umur = years + " Tahun";
                }
            }

        }

        return umur;
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