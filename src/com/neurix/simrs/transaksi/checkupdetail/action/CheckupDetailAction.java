package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.Activity;
import com.neurix.akuntansi.transaksi.billingSystem.model.MappingDetail;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.RujukanRequest;
import com.neurix.simrs.bpjs.vclaim.model.RujukanResponse;
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
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.bo.TempatTidurBo;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.AsesmenIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.AsesmenOperasiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.PersetujuanTindakanMedis;
import com.neurix.simrs.transaksi.asesmenrawatjalan.bo.KeperawatanRawatJalanBo;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.KeperawatanRawatJalan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkup.model.PelayananPaket;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUploadPendukungPemeriksaanEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UploadPendukungPemeriksaan;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.kandungan.bo.KandunganBo;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;
import com.neurix.simrs.transaksi.makananpendamping.bo.DetailPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.bo.HeaderPendampingMakananBo;
import com.neurix.simrs.transaksi.makananpendamping.model.DetailPendampingMakanan;
import com.neurix.simrs.transaksi.makananpendamping.model.HeaderPendampingMakanan;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.OrderPeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.*;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import com.neurix.simrs.transaksi.ringkasanpasien.bo.RingkasanPasienBo;
import com.neurix.simrs.transaksi.ringkasanpasien.model.RingkasanPasien;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import io.agora.recording.common.Common;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
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
    private String keterangan;
    private String createdDate;

    private List<PersetujuanTindakanMedis> myList = new ArrayList<>();

    public List<PersetujuanTindakanMedis> getMyList() {
        return myList;
    }

    public void setMyList(List<PersetujuanTindakanMedis> myList) {
        this.myList = myList;
    }

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
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String jk = "";

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found error when detail pasien " + e.getMessage());
            throw new GeneralBOException("Error when get detail pasien," + e.getMessage());
        }

        if (checkup.getIdDetailCheckup() != null) {

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(checkup.getNoCheckup());
            detailCheckup.setIdDetailCheckup(checkup.getIdDetailCheckup());
            detailCheckup.setStatusPeriksa("1");
            detailCheckup.setFlag("Y");
            detailCheckup.setAction("U");
            detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            detailCheckup.setLastUpdateWho(CommonUtil.userLogin());

            try {
                checkupDetailBoProxy.updateStatusPeriksa(detailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.add] Error when update checkup detail");
                throw new GeneralBOException("Error when update checkup detail," + e.getMessage());
            }

            try {
                checkupDetailBoProxy.updateFlagPeriksaAntrianOnline(checkup.getIdDetailCheckup());
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.add] Error when update checkup online");
                throw new GeneralBOException("Error when update checkup online," + e.getMessage());
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
            detailCheckup.setNoTelp(checkup.getNoTelp());

            detailCheckup.setKategoriPelayanan(checkup.getKategoriPelayanan());
            String label = checkup.getNamaPelayanan().replace("Poli Spesialis", "");
            detailCheckup.setAsesmenLabel("Asesmen " + label);
            detailCheckup.setTipePelayanan(checkup.getTipePelayanan());
            detailCheckup.setIsEksekutif(checkup.getIsEksekutif());
            detailCheckup.setIsVaksin(checkup.getIsVaksin());
            detailCheckup.setCatatanKlinis(checkup.getCatatanKlinis());
            detailCheckup.setSpo2(checkup.getSpo2());

            if ("rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
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
        logger.info("[CheckupDetailAction.search] start process >>>");

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
            headerDetailCheckup.setIdPelayanan(CommonUtil.userPelayananIdLogin());
            headerDetailCheckup.setTipePelayanan("rawat_jalan");
        }

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.save] Error when searching pasien by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);
        setHeaderDetailCheckup(headerDetailCheckup);

        logger.info("[CheckupDetailAction.search] end process <<<");
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

        if (CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(userRoleLogin) || CommonConstant.ROLE_DOKTER_UMUM.equalsIgnoreCase(userRoleLogin)
                || CommonConstant.ROLE_DOKTER_SPESIALIS.equalsIgnoreCase(userRoleLogin)) {
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
            kategoriTindakanList = kategoriTindakanBoProxy.getListKategoriTindakan(pelayanan, null, CommonUtil.userBranchLogin());
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListComboKategoriTIndakan] Error when get kategori tindakan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKategoriTindakan.addAll(kategoriTindakanList);
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] end process <<<");
        return SUCCESS;
    }

    public List<Tindakan> getListComboTindakan(String idKategoriTindakan, String idKelasRuangan, String vaksin) {
        logger.info("[CheckupDetailAction.listOfDokter] start process >>>");
        List<Tindakan> tindakanList = new ArrayList<>();
        Tindakan tindakan = new Tindakan();
        tindakan.setIdKategoriTindakan(idKategoriTindakan);
        tindakan.setIdKelasRuangan(idKelasRuangan);
        tindakan.setIsVaksin(vaksin);
        tindakan.setBranchId(CommonUtil.userBranchLogin());
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
                kategoriTindakans = kategoriTindakanBo.getListKategoriTindakan(idPelayanan, kategori, CommonUtil.userBranchLogin());
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
            }
        }

        logger.info("[CheckupDetailAction.listOfDokter] end process >>>");
        return kategoriTindakans;
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
                    String isResep = "";
                    if (object.has("is_resep")) {
                        isResep = object.getString("is_resep");
                    }
                    String metodeBayar = "";
                    String justLab = "";
                    String statusPeriksa = "3";
                    if (object.has("just_lab")) {
                        justLab = object.getString("just_lab");
                        statusPeriksa = "";
                    }

                    List<HeaderDetailCheckup> detailCheckupList = checkupDetailBo.getIDDetailCheckup(noCheckup, statusPeriksa, jenisPasien);

                    if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
                        if (justLab != null && !"".equalsIgnoreCase(justLab)) {
                            response.setStatus("success");
                        } else {
                            if (detailCheckupList.size() > 0) {

                                for (HeaderDetailCheckup detail : detailCheckupList) {
                                    approveTindakanRawat(detail.getIdDetailCheckup());
                                }

                                for (HeaderDetailCheckup detail : detailCheckupList) {
                                    response = cekAllTindakanRawat(detail.getIdDetailCheckup());
                                }
                            }
                        }
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            for (HeaderDetailCheckup detail : detailCheckupList) {
                                saveAddToRiwayatTindakan(detail.getIdDetailCheckup(), jenisPasien);
                            }
                            if ("rekanan".equalsIgnoreCase(jenisPasien) || "paket_individu".equalsIgnoreCase(jenisPasien) || "paket_perusahaan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                                metodeBayar = "non_tunai";
                            } else if ("umum".equalsIgnoreCase(jenisPasien)) {
                                metodeBayar = jenisBayar;
                            }

                            // create jurnal if non tunai
                            String setInvoice = null;
                            if ("non_tunai".equalsIgnoreCase(metodeBayar)) {
                                JurnalResponse jurnalResponse = closingJurnalNonTunai(isResep, idPasien, noCheckup);
                                if (!"rekanan".equalsIgnoreCase(jurnalResponse.getStatus())) {
                                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())) {
                                        response.setStatus("error");
                                        response.setMsg(jurnalResponse.getMsg());
                                        return response;
                                    } else if (!"".equalsIgnoreCase(jurnalResponse.getInvoice())) {
                                        setInvoice = jurnalResponse.getInvoice();
                                    }
                                }
                            }

                            for (HeaderDetailCheckup detail : detailCheckupList) {
                                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                                detailCheckup.setIdDetailCheckup(detail.getIdDetailCheckup());
                                detailCheckup.setLastUpdateWho(user);
                                detailCheckup.setLastUpdate(now);
                                detailCheckup.setFlagCloseTraksaksi("Y");
                                if (justLab != null && !"".equalsIgnoreCase(justLab)) {
                                    detailCheckup.setJustLab(justLab);
                                }
                                detailCheckup.setInvoice(setInvoice);
                                detailCheckups.add(detailCheckup);
                            }

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

    public CrudResponse savePeriksaPasien(String data, String asuransi) {
        CrudResponse response = new CrudResponse();
        logger.info("[CheckupDetailAction.savePeriksaPasien] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        KategoriLabBo kategoriLabBo = (KategoriLabBo) ctx.getBean("kategoriLabBoProxy");
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
                String listPemeriksaan = null;
                String kategoriLab = null;
                String isOrderLab = null;
                String poliLain = null;
                String idDokter = null;
                String metodeBayar = null;
                String uangMuka = null;
                String urutan = null;
                String idPaket = null;
                String idPelayananPaket = null;
                String idPoliInternal = null;
                String eksekutif = null;
                String vaksin = null;
                String isMeninggal = "";
                String indikasi = null;

                if (object.has("rs_rujukan")) {
                    rsRujukan = object.getString("rs_rujukan");
                }
                if (object.has("tgl_kontrol")) {
                    tglKontrol = object.getString("tgl_kontrol");
                }
                if (object.has("id_kategori_lab")) {
                    kategoriLab = object.getString("id_kategori_lab");
                }
                if (object.has("list_pemeriksaan")) {
                    listPemeriksaan = object.getString("list_pemeriksaan");
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
                if (object.has("id_poli_internal")) {
                    idPoliInternal = object.getString("id_poli_internal");
                }
                if (object.has("is_eksekutif")) {
                    eksekutif = object.getString("is_eksekutif");
                }
                if (object.has("is_vaksin")) {
                    vaksin = object.getString("is_vaksin");
                }
                if (object.has("is_meninggal")) {
                    isMeninggal = object.getString("is_meninggal");
                }
                if (object.has("indikasi")) {
                    indikasi = object.getString("indikasi");
                }

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
                    headerDetailCheckup.setIsMeninggal(isMeninggal);
                    headerDetailCheckup.setIndikasi(indikasi);

                    saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien);

                    if ("Y".equalsIgnoreCase(isOrderLab)) {
                        if (listPemeriksaan != null) {
                            List<OrderPeriksaLab> detailList = new ArrayList<>();
                            JSONArray json = new JSONArray(listPemeriksaan);
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject ob = json.getJSONObject(i);
                                OrderPeriksaLab periksa = new OrderPeriksaLab();
                                periksa.setIdPemeriksaan(ob.getString("id_pemeriksaan"));
                                periksa.setNamaPemeriksaan(ob.getString("nama_pemeriksaan"));
                                String listParameter = ob.getString("list_parameter");
                                if (listParameter != null) {
                                    JSONArray jsn = new JSONArray(listParameter);
                                    for (int j = 0; j < jsn.length(); j++) {
                                        JSONObject oc = jsn.getJSONObject(j);
                                        OrderPeriksaLab detail = new OrderPeriksaLab();
                                        detail.setIdDetailCheckup(idDetailCheckup);
                                        detail.setIdPemeriksaan(ob.getString("id_pemeriksaan"));
                                        detail.setNamaPemeriksaan(ob.getString("nama_pemeriksaan"));
                                        detail.setIdLabDetail(oc.getString("id_parameter"));
                                        detail.setNamaDetailPemeriksaan(oc.getString("nama_parameter"));
                                        detail.setFlag("Y");
                                        detail.setAction("C");
                                        detail.setCreatedWho(user);
                                        detail.setCreatedDate(now);
                                        detail.setLastUpdateWho(user);
                                        detail.setLastUpdate(now);
                                        detail.setIsPemeriksaan("N");
                                        ImSimrsKategoriLabEntity kategoriLabEntity = kategoriLabBo.getDataLab(kategoriLab);
                                        if (kategoriLabEntity != null) {
                                            detail.setKeterangan(kategoriLabEntity.getKategori());
                                            detail.setIdKategoriLab(kategoriLab);
                                        }
                                        detailList.add(detail);
                                    }
                                } else {
                                    response.setStatus("error");
                                    response.setMsg("Data order jenis parameter pemeriksaan tidak ditemukan");
                                    return response;
                                }
                            }
                            if (detailList.size() > 0) {
                                response = saveLabCheckup(detailList);
                                if ("success".equalsIgnoreCase(response.getStatus())) {
                                    headerDetailCheckup.setIsOrderLab("Y");
                                }
                            }
                        }
                    }

                    if ("asuransi".equalsIgnoreCase(jenisPasien)) {
                        if (asuransi != null && !"".equalsIgnoreCase(asuransi)) {
                            try {
                                JSONObject oj = new JSONObject(asuransi);
                                if (oj != null) {
                                    if (oj.has("no_polisi")) {
                                        headerDetailCheckup.setNoRujukan(oj.getString("no_polisi"));
                                    }
                                    if (oj.has("tgl_kejadian")) {
                                        headerDetailCheckup.setTglRujukan(oj.getString("tgl_kejadian"));
                                    }
                                    if (oj.has("surat_rujukan")) {
                                        if (oj.getString("surat_rujukan") != null && !"".equalsIgnoreCase(oj.getString("surat_rujukan"))) {
                                            BASE64Decoder decoder = new BASE64Decoder();
                                            byte[] decodedBytes = decoder.decodeBuffer(oj.getString("surat_rujukan"));
                                            logger.info("Decoded upload data : " + decodedBytes.length);
                                            String fileName = idDetailCheckup + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN + fileName;
                                            logger.info("File save path : " + uploadFile);
                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                                            if (image == null) {
                                                logger.error("Buffered Image is null");
                                            } else {
                                                File f = new File(uploadFile);
                                                ImageIO.write(image, "png", f);
                                                headerDetailCheckup.setSuratRujukan(fileName);
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                response.setStatus("error");
                                response.setMsg("[Found Error] JSON data asuransi tidak bisa dilakukan...!");
                            }
                        }
                    }

                    if ("pindah_poli".equalsIgnoreCase(tindakLanjut)) {
                        response = pindahPoli(idDetailCheckup, poliLain, idDokter, metodeBayar, uangMuka, eksekutif, vaksin);
                    } else if ("lanjut_paket".equalsIgnoreCase(tindakLanjut)) {
                        response = nextPaket(noCheckup, idDetailCheckup, urutan, idPaket, idDokter, idPelayananPaket, poliLain);
                    } else if ("rujuk_internal".equalsIgnoreCase(tindakLanjut)) {
                        response = rujukInternal(idDetailCheckup, idPoliInternal, object.getString("catatan"));
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

    private CrudResponse nextPaket(String noCheckup, String idDetailCheckup, String urutan, String idPaket, String idDokter, String idPelayananPaket, String idPelayanan) {
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

    private CrudResponse rujukInternal(String idDetailCheckup, String idPoliRujukan, String catatan) {
        logger.info("[CheckupDetailAction.rujukInternal] start process >>>");
        CrudResponse finalResponse = new CrudResponse();
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String user = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(idPoliRujukan)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
            PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
            HeaderCheckup detailCheckup = new HeaderCheckup();
            try {
                detailCheckup = checkupBo.getDataDetailPasien(idDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.rujukRawatInap] Error when geting data detail poli, ", e);
            }
            if (detailCheckup != null) {
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
                    RujukanRequest request = new RujukanRequest();
                    RujukanResponse rujukanResponse = new RujukanResponse();
                    String noSEP = detailCheckup.getNoSep();
                    String noPPK = noSEP.substring(0, 8);
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(idPoliRujukan);
                    if (pelayananEntity.getKodePoliVclaim() != null) {
                        request.setNoSep(noSEP);
                        request.setTglRujukan(tglToday);
                        request.setPpkDirujuk(noPPK);
                        request.setJnsPelayanan("2");
                        request.setCatatan(catatan);
                        request.setDiagRujukan(diagnosaRawat.getIdDiagnosa());
                        request.setTipeRujukan("0");
                        request.setPoliRujukan(pelayananEntity.getKodePoliVclaim());
                        request.setUserPembuatRujukan(user);
                        try {
                            rujukanResponse = bpjsBo.insertRujukanBpjs(request, branchId);
                        } catch (HibernateException e) {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg(rujukanResponse.getMessage());
                        }

                        if ("200".equalsIgnoreCase(rujukanResponse.getStatus())) {
                            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
                            headerDetailCheckup.setPoliRujukanInternal(idPoliRujukan);
                            headerDetailCheckup.setNoRujukanInternal(rujukanResponse.getNoRujukan());
                            headerDetailCheckup.setLastUpdate(now);
                            headerDetailCheckup.setLastUpdateWho(user);
                            finalResponse = checkupDetailBo.setNoRujukan(headerDetailCheckup);
                        } else {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg(rujukanResponse.getMessage());
                        }
                    } else {
                        finalResponse.setStatus("error");
                        finalResponse.setMsg("Kode poli untuk vclaim tidak ditemukan...!");
                    }
                }
            }
        }
        logger.info("[CheckupDetailAction.rujukInternal] end process >>>");
        return finalResponse;
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
            periksaLabList = periksaLabBo.getByCriteriaHeaderPemeriksaan(periksaLab);
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
            } else {
                OrderGizi orderGizi = new OrderGizi();
                orderGizi.setIdDetailCheckup(idDetailCheckup);
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

    private JurnalResponse closingJurnalNonTunai(String isResep, String idPasien, String noCheckup) {

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
        String masterId = "";
        String jenisPasien = "Umum ";
        String noKartu = "";
        String idJenisPeriksaPasien = "";
        BigDecimal biayaCover = new BigDecimal(0);
        BigDecimal jumlahUm = new BigDecimal(0);
        String idUm = "";
        BigDecimal jumlahResep = new BigDecimal(0);
        BigDecimal jumlahTindakan = new BigDecimal(0);
        BigDecimal ppnObat = new BigDecimal(0);

        Map hsCriteria = new HashMap();
        hsCriteria.put("user_id", CommonUtil.userIdLogin());
        hsCriteria.put("user_who", CommonUtil.userLogin());

        List<MappingDetail> listMapUangMuka = new ArrayList<>();

        boolean isNoCheckup = noCheckup != null && !"".equalsIgnoreCase(noCheckup);
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
        if (isNoCheckup) {
            List<String> listDetilCheckup = riwayatTindakanBo.getListIdDetailCheckup(noCheckup);
            if (listDetilCheckup.size() > 0) {
                String idDetailCheckup = listDetilCheckup.get(listDetilCheckup.size() - 1);
                detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            }
        }
        if (detailCheckupEntity != null) {
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }
        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        if (!"rekanan".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
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

                Pelayanan pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());
                if (pelayananEntity != null) {


                    List<String> listDetailCheckup = riwayatTindakanBo.getListIdDetailCheckup(noCheckup);
                    if (listDetailCheckup.size() > 0) {
                        for (String idDetail : listDetailCheckup) {
                            // MENDAPATKAN NILAI UANG MUKA;
                            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                            headerDetailCheckup.setIdDetailCheckup(idDetail);
                            headerDetailCheckup.setStatusBayar("Y");
                            List<HeaderDetailCheckup> detailCheckupUangMuka = checkupDetailBo.getListUangPendaftaran(headerDetailCheckup);


                            if (detailCheckupUangMuka.size() > 0) {
                                for (HeaderDetailCheckup detailCheckup : detailCheckupUangMuka) {
                                    if (!"umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                                        if ("R".equalsIgnoreCase(detailCheckup.getFlagRefund())) {
                                            response.setStatus("error");
                                            response.setMsg("Silahkan lakukan Refund Uang Muka Pasien terlebih dahulu...!");
                                            return response;
                                        }
                                    }
                                    jumlahUm = new BigDecimal(detailCheckup.getJumlahUangMukaDibayar());
                                    idUm = detailCheckup.getNoUangMuka();

                                    MappingDetail mapUangMuka = new MappingDetail();
                                    mapUangMuka.setBukti(idUm);
                                    mapUangMuka.setNilai(jumlahUm);
                                    mapUangMuka.setMasterId(masterId);
                                    listMapUangMuka.add(mapUangMuka);
                                }
                                if (!"umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {
                                    jumlahUm = new BigDecimal(0);
                                }
                            }

                            // get all sum tindakan, sum resep
                            BigDecimal hitungResep = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetail, "resep");
                            BigDecimal hitungTindakan = checkupDetailBo.getSumJumlahTindakanNonBpjs(idDetail, "");

                            jumlahResep = jumlahResep.add(hitungResep);
                            jumlahTindakan = jumlahTindakan.add(hitungTindakan);

                            if (hitungResep.compareTo(new BigDecimal(0)) == 1) {
                                BigDecimal hitungPPN = hitungResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                                ppnObat = ppnObat.add(hitungPPN);
                            }
                        }
                    }


                    // jumlah tindakan saja. tindakan total - jumlah resep
                    jumlahTindakan = jumlahTindakan.subtract(jumlahResep);

                    List<MappingDetail> listMapPajakObat = new ArrayList<>();
                    MappingDetail mapPajakObat = new MappingDetail();
                    mapPajakObat.setNilai(ppnObat);
                    mapPajakObat.setMasterId(CommonConstant.MASTER_PAJAK_OBAT);
                    listMapPajakObat.add(mapPajakObat);

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

                        allTindakanTrans = checkupDetailBo.getSumJumlahTindakanTransitoris(detailCheckupEntity.getIdDetailCheckup(), "");

                        List<MappingDetail> listMapTransitoris = new ArrayList<>();

                        MappingDetail mapTransitoris = new MappingDetail();
                        mapTransitoris.setNilai(allTindakanTrans);
                        mapTransitoris.setBukti(detailCheckupEntity.getInvoiceTrans());
                        listMapTransitoris.add(mapTransitoris);

                        hsCriteria.put("piutang_transistoris_pasien_rawat_inap", listMapTransitoris);
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
                    List<MappingDetail> listOfTindakan = new ArrayList<>();
                    List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    if (listOfKeteranganRiwayat.size() > 0) {

                        // jika rawat inap
                        if ("JRI".equalsIgnoreCase(kode)) {
                            for (String keterangan : listOfKeteranganRiwayat) {
                                if ("kamar".equalsIgnoreCase(keterangan) || "tindakan".equalsIgnoreCase(keterangan)) {

                                    // mencari list ruangan
                                    List<String> listRuangan = riwayatTindakanBo.getListRuanganRiwayatTindakan(detailCheckupEntity.getIdDetailCheckup(), keterangan);
                                    if (listRuangan.size() > 0) {
                                        for (String ruangan : listRuangan) {
                                            MappingDetail mapTindakan = new MappingDetail();
                                            mapTindakan.setMasterId(masterId);
                                            mapTindakan.setDivisiId(getDivisiId(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ruangan));
                                            mapTindakan.setNilai(getJumlahNilaiBiayaByKeterangan(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ruangan, ""));
                                            mapTindakan.setListOfActivity(getListAcitivity(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ruangan));
                                            listOfTindakan.add(mapTindakan);
                                        }
                                    }
                                } else {
                                    MappingDetail mapTindakan = new MappingDetail();
                                    mapTindakan.setMasterId(masterId);
                                    mapTindakan.setDivisiId(getDivisiId(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                    mapTindakan.setNilai(getJumlahNilaiBiayaByKeterangan(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, "", ""));
                                    mapTindakan.setListOfActivity(getListAcitivity(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                    listOfTindakan.add(mapTindakan);
                                }
                            }
                        } else {

                            if (isNoCheckup) {
                                List<String> listIdDetailCheckup = riwayatTindakanBo.getListIdDetailCheckup(noCheckup);
                                if (listIdDetailCheckup.size() > 0) {
                                    for (String idDetail : listIdDetailCheckup) {

                                        List<String> listRiwayatTindakan = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetail);
                                        if (listOfKeteranganRiwayat.size() > 0) {

                                            for (String keterangan : listRiwayatTindakan) {
                                                MappingDetail mapTindakan = new MappingDetail();
                                                mapTindakan.setMasterId(masterId);
                                                mapTindakan.setDivisiId(getDivisiId(idDetail, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                                mapTindakan.setNilai(getJumlahNilaiBiayaByKeterangan(idDetail, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, "", ""));
                                                mapTindakan.setListOfActivity(getListAcitivity(idDetail, detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                                listOfTindakan.add(mapTindakan);

                                            }
                                        }
                                    }
                                }
                            } else {

                                // jika rawat jalan
                                for (String keterangan : listOfKeteranganRiwayat) {
                                    MappingDetail mapTindakan = new MappingDetail();
                                    mapTindakan.setMasterId(masterId);
                                    mapTindakan.setDivisiId(getDivisiId(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, ""));
                                    mapTindakan.setNilai(getJumlahNilaiBiayaByKeterangan(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, "", ""));
                                    mapTindakan.setListOfActivity(getListAcitivity(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdJenisPeriksaPasien(), keterangan, kode, ""));
                                    listOfTindakan.add(mapTindakan);
                                }
                            }
                        }
                    }

                    // MENDAPATKAN SEMUA BIAYA RAWAT;
                    jumlah = getJumlahNilaiBiayaByKeterangan("", "", "", "", noCheckup);

                    // create invoice nummber
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
                                hsCriteria.put("ppn_keluaran", listMapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                List<MappingDetail> listMapPiutang = new ArrayList<>();

                                // create list map piutang
                                MappingDetail mapPiutang = new MappingDetail();
                                mapPiutang.setNilai(jumlah.subtract(jumlahUm));
                                mapPiutang.setMasterId(masterId);
                                listMapPiutang.add(mapPiutang);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", listMapPiutang);

                                transId = "17";

                            } else {

                                List<MappingDetail> listMapPiutang = new ArrayList<>();

                                // create list map piutang
                                MappingDetail mapPiutang = new MappingDetail();
                                mapPiutang.setNilai(jumlah.subtract(jumlahUm));
                                mapPiutang.setMasterId(masterId);
                                listMapPiutang.add(mapPiutang);

                                // debit piutang pasien asuransi
                                hsCriteria.put("piutang_pasien_asuransi", listMapPiutang);
                                transId = "09";
                            }

                        } else {

                            //**** UMUM ***//
                            // kredit jumlah tindakan
                            hsCriteria.put("pendapatan_rawat_jalan_umum", listOfTindakan);

                            // jumlah debit uang muka
                            if ("Y".equalsIgnoreCase(isResep)) {

                                // kredit ppn obat umum
                                hsCriteria.put("ppn_keluaran", listMapPajakObat);

                                // jika ada resep dan ppn untuk debit piutang
                                jumlah = jumlah.add(ppnObat);

                                List<MappingDetail> listMapPiutang = new ArrayList<>();
                                // create list map piutang
                                MappingDetail mapPiutang = new MappingDetail();
                                mapPiutang.setNilai(jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.setPasienId(idPasien);
                                } else {
                                    mapPiutang.setMasterId(masterPerusahaan);
                                }

                                listMapPiutang.add(mapPiutang);

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", listMapPiutang);

                                if ("paket_individu".equalsIgnoreCase(idJenisPeriksaPasien) || "paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    transId = "62";
                                } else {
                                    transId = "14";
                                }
                            } else {

                                // create list map piutang

                                List<MappingDetail> listMapPiutang = new ArrayList<>();

                                MappingDetail mapPiutang = new MappingDetail();
                                mapPiutang.setNilai(jumlah.subtract(jumlahUm));
                                if (!"paket_perusahaan".equalsIgnoreCase(idJenisPeriksaPasien)) {
                                    mapPiutang.setPasienId(idPasien);
                                } else {
                                    mapPiutang.setMasterId(masterPerusahaan);
                                }
                                listMapPiutang.add(mapPiutang);

                                // debit piutang pasien
                                hsCriteria.put("piutang_pasien_umum", listMapPiutang);
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

                            List<MappingDetail> listMapPiutang = new ArrayList<>();

                            // create map piutang asuransi
                            MappingDetail mapPiutang = new MappingDetail();
                            mapPiutang.setNilai(jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.setMasterId(masterId);
                            listMapPiutang.add(mapPiutang);

                            // debit piutang pasien asuransi
                            hsCriteria.put("piutang_pasien_asuransi", listMapPiutang);

                            if (isTransitoris) {
                                jenisPasien = jenisPasien + "Terhadap Transitoris ";
                                transId = "41";
                            } else {
                                transId = "24";
                            }
                        } else {

                            //**** UMUM ***//

                            List<MappingDetail> listMapPiutang = new ArrayList<>();

                            // create map piutang
                            MappingDetail mapPiutang = new MappingDetail();
                            mapPiutang.setNilai(jumlah.add(allTindakanTrans).subtract(jumlahUm));
                            mapPiutang.setPasienId(idPasien);
                            listMapPiutang.add(mapPiutang);

                            // debit piutang pasien
                            hsCriteria.put("piutang_pasien_umum", listMapPiutang);
                            hsCriteria.put("uang_muka", listMapUangMuka);

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

                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + " " + namaPerusahaan + " No. Checkup " + noCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        } else {
                            catatan = "Closing Pasien Paket " + namaPaket + ketResep + "No. Checkup " + noCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
                        }

                    } else {
                        catatan = "Closing Pasien " + ketPoli + jenisPasien + ketResep + "No. Checkup " + noCheckup + " Piutang No Pasien " + " " + idPasien + noKartu;
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
            response.setStatus("rekanan");
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

    private CrudResponse pindahPoli(String idDetailCheckup, String idPoli, String idDokter, String metodeBayar, String uangMuka, String eksekutif, String vaksin) {
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
                        if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

                            Branch branch = new Branch();
                            branch.setBranchId(branchId);
                            branch.setFlag("Y");

                            try {
                                branchList = branchBo.getByCriteria(branch);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
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
                                        logger.error("[CheckupDetailAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
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
                                                logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
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
                                                logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (response.getNoSep() != null) {

                                                headerDetailCheckup.setNoSep(response.getNoSep());
                                                genNoSep = response.getNoSep();
                                                logger.info("[CheckupDetailAction.saveAdd] NO. SEP : " + genNoSep);

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
                                                    logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                                tindakan.setIsIna("Y");

                                                try {
                                                    tindakanList = tindakanBo.getDataTindakan(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupDetailAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
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
                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                                        Grouping1Response grouping1Response = new Grouping1Response();

                                                        try {
                                                            grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, branchId);
                                                        } catch (GeneralBOException e) {
                                                            logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
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
                                                                    logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }
                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return finalResponse;
                                                            }

                                                            // jika ada special cmg maka proses grouping stage 2
                                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                                    try {
                                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), branchId);
                                                                    } catch (GeneralBOException e) {
                                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);

                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return finalResponse;
                                                        }

                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                        return finalResponse;
                                                    }
                                                } else {
                                                    finalResponse.setStatus("error");
                                                    finalResponse.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                    logger.error("[CheckupDetailAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                    return finalResponse;
                                                }
                                            } else {
                                                finalResponse.setStatus("error");
                                                finalResponse.setMsg("Failed To Generate SEP " + response.getMessage());
                                                logger.error("[CheckupDetailAction.saveAdd] Failed To Generate SEP " + response.getMessage());
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

                        if ("rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
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
                        headerDetailCheckup.setIsEksekutif(eksekutif);
                        headerDetailCheckup.setIsVaksin(vaksin);

                        List<DokterTeam> dokterTeams = new ArrayList<>();
                        DokterTeam dokterTeam = new DokterTeam();
                        dokterTeam.setIdDokter(idDokter);
                        dokterTeam.setIdPelayanan(idPoli);
                        dokterTeams.add(dokterTeam);
                        headerDetailCheckup.setDokterTeamList(dokterTeams);

                        headerDetailCheckup.setIdJenisPeriksaPasien(detailCheckup.getIdJenisPeriksaPasien());
                        headerDetailCheckup.setBranchId(branchId);
                        headerDetailCheckup.setTypeTransaction("pindah_poli");

                        Pelayanan pelayanan = new Pelayanan();
                        pelayanan.setIdPelayanan(idPoli);
                        pelayanan = pelayananBo.getObjectPelayanan(pelayanan);
                        headerDetailCheckup.setTipePelayanan(pelayanan.getTipePelayanan());

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

    private CrudResponse rujukRawatInap(String noCheckup, String idDetailCheckup, String kelas, String kamar, String metodeBayar, String uangMuka, String idDokterDpjp, String idPoli) {
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
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Error saat mencari data diagnosa, " + e.getMessage());
                            return finalResponse;
                        }

                        if (diagnosaRawatList.size() > 0) {
                            diagnosaRawat = diagnosaRawatList.get(0);

                            headerDetailCheckup.setDiagnosa(diagnosaRawat.getIdDiagnosa());
                            headerDetailCheckup.setNamaDiagnosa(diagnosaRawat.getKeteranganDiagnosa());
                        }

                        Pelayanan pelayananResult = new Pelayanan();
                        Pelayanan pelayanan = new Pelayanan();
                        pelayanan.setTipePelayanan("rawat_inap");
                        pelayanan.setBranchId(CommonUtil.userBranchLogin());

                        try {
                            pelayananResult = pelayananBo.getObjectPelayanan(pelayanan);
                        } catch (GeneralBOException e) {
                            logger.error("[Found Error] when search pelayanan " + e.getMessage());
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Error saat mencari data pelayanan, " + e.getMessage());
                            return finalResponse;
                        }

                        if (pelayananResult.getIdPelayanan() == null || "".equalsIgnoreCase(pelayananResult.getIdPelayanan())) {
                            finalResponse.setStatus("error");
                            finalResponse.setMsg("Terjadi kesalahan ketika mencari pelayanan");
                            return finalResponse;
                        }

                        if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

                            Branch branch = new Branch();
                            branch.setBranchId(branchId);
                            branch.setFlag("Y");

                            try {
                                branchList = branchBo.getByCriteria(branch);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
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
                                        logger.error("[CheckupDetailAction.saveAdd] Error when search item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
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
                                                logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (dokterArrayList.size() > 0) {
                                                namaDokter = dokterArrayList.get(0).getNamaDokter();
                                                idDokter = dokterArrayList.get(0).getIdDokter();
                                                kodeDpjp = dokterArrayList.get(0).getKodeDpjp();
                                            }

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
                                                logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                            }

                                            if (response.getNoSep() != null) {

                                                genNoSep = response.getNoSep();

                                                headerDetailCheckup.setNoSep(response.getNoSep());

                                                logger.info("[CheckupDetailAction.saveAdd] NO. SEP : " + genNoSep);

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
                                                    logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                }

                                                List<Tindakan> tindakanList = new ArrayList<>();
                                                Tindakan tindakan = new Tindakan();
                                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                                tindakan.setIsIna("Y");

                                                try {
                                                    tindakanList = tindakanBo.getDataTindakan(tindakan);
                                                } catch (GeneralBOException e) {
                                                    logger.error("[CheckupDetailAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
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
                                                        tin.setTindakan(entity.getTindakan());
                                                        tin.setTarifBpjs(entity.getTarifBpjs());
                                                        tin.setTarif(entity.getTarif());
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
                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
                                                    }

                                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                                        Grouping1Response grouping1Response = new Grouping1Response();

                                                        try {
                                                            grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, branchId);
                                                        } catch (GeneralBOException e) {
                                                            logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);
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
                                                                    logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                    return finalResponse;
                                                                }
                                                            } else {
                                                                finalResponse.setStatus("error");
                                                                finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                                return finalResponse;
                                                            }

                                                            // jika ada special cmg maka proses grouping stage 2
                                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                                    try {
                                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), branchId);
                                                                    } catch (GeneralBOException e) {
                                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.", e);

                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            finalResponse.setStatus("error");
                                                            finalResponse.setMsg("Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            logger.error("[CheckupDetailAction.saveAdd] Failed To Get Cover Biaya BPJS " + grouping1Response.getMessage());
                                                            return finalResponse;
                                                        }

                                                    } else {
                                                        finalResponse.setStatus("error");
                                                        finalResponse.setMsg("Tidak dapat menemukan PPK pelayanan Unit, " + claimEklaimResponse.getMessage());
                                                        logger.error("[CheckupDetailAction.saveAdd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                                        return finalResponse;
                                                    }
                                                } else {
                                                    finalResponse.setStatus("error");
                                                    finalResponse.setMsg("Failed To getPastien from Eklaim " + responseNewClaim.getMsg());
                                                    logger.error("[CheckupDetailAction.saveAdd] Failed To getPastien from Eklaim  " + responseNewClaim.getMsg());
                                                    return finalResponse;
                                                }
                                            } else {
                                                finalResponse.setStatus("error");
                                                finalResponse.setMsg("Failed To Generate SEP " + response.getMessage());
                                                logger.error("[CheckupDetailAction.saveAdd] Failed To Generate SEP " + response.getMessage());
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

                            if ("rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
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
                        headerDetailCheckup.setIdPelayanan(pelayananResult.getIdPelayanan());
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
        logger.info("[CheckupDetailAction.getListKelasKamar] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        List<KelasRuangan> kelasRuanganList = new ArrayList<>();

        try {
            kelasRuanganList = kelasRuanganBo.getListKelasKamar(kategori);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getListKelasKamar] Error when get kelas ruangan ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[CheckupDetailAction.getListKelasKamar] end process <<<");
        return kelasRuanganList;
    }

    public List<Ruangan> listRuangan(String idkelas, boolean flag, String kategori) {
        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        List<Ruangan> ruanganList = new ArrayList<>();
        Ruangan ruangan = new Ruangan();
        if (flag) {
            ruangan.setStatus("Y");
        }
        ruangan.setIdKelasRuangan(idkelas);
        ruangan.setBranchId(CommonUtil.userBranchLogin());
        ruangan.setKategori(kategori);

        try {
            ruanganList = ruanganBo.getListRuangan(ruangan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        return ruanganList;
    }

    public List<Ruangan> listJustRuangan(String idKelas, String kategori) {
        List<Ruangan> ruanganList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        String branchId = CommonUtil.userBranchLogin();
        try {
            ruanganList = ruanganBo.getJustListRuangan(idKelas, branchId, kategori);
        } catch (GeneralBOException e) {
            logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
        }
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
        logger.info("[CheckupDetailAction.saveAddRawatIgd] start process >>>");
        HeaderCheckup checkup = getHeaderCheckup();
        String genNoSep = "";
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String noCheckup = "CKP" + checkupBoProxy.getNextHeaderId();
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        if (checkup.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
            List<Pasien> pasienList = new ArrayList<>();
            List<Branch> branchList = new ArrayList<>();
            Pasien pasien = new Pasien();
            pasien.setIdPasien(checkup.getIdPasien());
            pasien.setFlag("Y");

            try {
                pasienList = pasienBoProxy.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                throw new GeneralBOException("Data pasien tidak ditemukan...!");
            }

            if (pasienList.isEmpty()) {
                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when search branch id");
                throw new GeneralBOException("Data pasien tidak ditemukan...!");
            }

            //jika bpjs dan rekanan bpjs
            if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {
                Branch branch = new Branch();
                branch.setBranchId(userArea);
                branch.setFlag("Y");

                try {
                    branchList = branchBoProxy.getByCriteria(branch);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
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
                                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when insert SEP ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new insert SEP", e);
                            }

                            if (response.getNoSep() != null) {

                                genNoSep = response.getNoSep();

                                checkup.setNoSep(response.getNoSep());

                                logger.info("[CheckupDetailAction.saveAddRawatIgd] NO. SEP : " + genNoSep);

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
                                    logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when new claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                    throw new GeneralBOException("Error when new claim", e);
                                }

                                List<Tindakan> tindakanList = new ArrayList<>();
                                Tindakan tindakan = new Tindakan();
                                tindakan.setBranchId(CommonUtil.userBranchLogin());
                                tindakan.setIsIna("Y");

                                try {
                                    tindakanList = tindakanBoProxy.getDataTindakan(tindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
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
                                        logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when update claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                        throw new GeneralBOException("Error when update claim, [" + claimEklaimResponse.getMessage() + "]", e);
                                    }

                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                        Grouping1Response grouping1Response = new Grouping1Response();

                                        try {
                                            grouping1Response = eklaimBoProxy.groupingStage1Eklaim(genNoSep, userArea);
                                        } catch (GeneralBOException e) {
                                            logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
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
                                                    logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                                    throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                                }
                                            } else {
                                                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when get cover biaya BPJS " + grouping1Response.getMessage());
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
                                                        logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                                        addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                                                        return ERROR;
                                                    }
                                                }
                                            }
                                        } else {
                                            logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when get Tarif cover INA CBG ,update claim not success " + grouping1Response.getMessage());
                                            throw new GeneralBOException("Error when get Tarif cover INA CBG, [" + grouping1Response.getMessage() + "]");
                                        }

                                    } else {
                                        logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                        throw new GeneralBOException("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                    }
                                } else {
                                    logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when get pastien Eklaim, " + responseNewClaim.getMsg());
                                    throw new GeneralBOException("Error when get pastien Eklaim, [" + responseNewClaim.getMsg() + "]");
                                }
                            } else {
                                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when generate SEP, " + response.getMessage());
                                throw new GeneralBOException("Error when generate SEP, [" + response.getMessage() + "]");
                            }

                        }
                    } else {
                        logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when search PPK pelayanan");
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
                            logger.info("[CheckupDetailAction.uploadImages] FILEPATH :" + filePath);

                            // persiapan pemindahan file
                            File fileToCreate = new File(filePath, newFileName);

                            try {
                                // pemindahan file
                                FileUtils.copyFile(this.fileUploadDoc, fileToCreate);
                                logger.info("[CheckupDetailAction.uploadImages] SUCCES PINDAH");
                                checkup.setUrlDocRujuk(newFileName);
                            } catch (IOException e) {
                                logger.error("[CheckupDetailAction.uploadImages] error, " + e.getMessage());
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
                            logger.info("[CheckupDetailAction.uploadImages] FILEPATH :" + filePath);

                            // persiapan pemindahan file
                            File fileToCreate = new File(filePath, newFileName);

                            try {
                                // pemindahan file
                                FileUtils.copyFile(this.fileUploadPolisi, fileToCreate);
                                logger.info("[CheckupDetailAction.uploadImages] SUCCES PINDAH");
                                checkup.setUrlDocRujuk(newFileName);
                            } catch (IOException e) {
                                logger.error("[CheckupDetailAction.uploadImages] error, " + e.getMessage());
                                throw new GeneralBOException("Found Error when upload images rujukan " + e.getMessage());
                            }
                        }
                    }
                }

                checkupBoProxy.saveAdd(checkup);

            } catch (Exception e) {
                logger.error("[CheckupDetailAction.saveAddRawatIgd] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                throw new GeneralBOException("Found Error when adding item " + e.getMessage());
            }
        } else {
            throw new GeneralBOException("Jenis pasien tidak ditemukan...!");
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.saveAddRawatIgd] end process >>>");
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
            reportParams.put("idDokter", permintaanResep.getIdDokter());
            reportParams.put("dokter", permintaanResep.getNamaDokter());
            reportParams.put("ttdDokter", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + permintaanResep.getTtdDokter());
            reportParams.put("petugas", permintaanResep.getNamaApoteker());
            reportParams.put("ttdPasien", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_PASIEN + permintaanResep.getTtdPasien());
            reportParams.put("ttdApoteker", CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_APOTEKER + permintaanResep.getTtdApoteker());
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("resepId", idResep);
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            if(checkup.getTglLahir() != null){
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

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
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

    private void approveTindakanRawat(String idDetailCheckup) {
        logger.info("[CheckupDetailAction.approveTindakanRawat] START process >>>");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String user = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setLastUpdate(updateTime);
            headerDetailCheckup.setLastUpdateWho(user);

            try {
                checkupDetailBo.saveApproveAllTindakanRawatJalan(headerDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.approveTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }
        }
        logger.info("[CheckupDetailAction.approveTindakanRawat] END process >>>");
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
            reportParams.put("umur", CommonUtil.calculateAge(checkup.getTglLahir(), true) + " Tahun");

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        if ("KU".equalsIgnoreCase(getTipe())) {
            return "print_checkup_ulang";
        } else {
            return "print_pindah_rs";
        }
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
            HeaderPendampingMakananBo headerPendampingMakananBo = (HeaderPendampingMakananBo) ctx.getBean("headerPendampingMakananBoProxy");
            DetailPendampingMakananBo detailPendampingMakananBo = (DetailPendampingMakananBo) ctx.getBean("detailPendampingMakananBoProxy");
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
            String userBranch = CommonUtil.userBranchLogin();

            RekananOps ops = new RekananOps();
            if ("rekanan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                try {
                    ops = rekananOpsBo.getDetailRekananOpsByDetail(idDetail, userBranch);
                } catch (GeneralBOException e) {
                    logger.error("Error, " + e.getMessage());
                }
            }

            String jenPasien = "";
            if ("bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
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

                        List<Tindakan> tindakanList = new ArrayList<>();
                        Tindakan tin = new Tindakan();
                        tin.setIdTindakan(entity.getIdTindakan());

                        try {
                            tindakanList = tindakanBo.getDataTindakan(tin);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupAction.saveAdd] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                            throw new GeneralBOException("Error when new tindakan", e);
                        }

                        if (tindakanList.size() > 0) {
                            tin = tindakanList.get(0);
                            riwayatTindakan.setKategoriTindakanBpjs(tin.getKategoriInaBpjs());
                        }


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
                periksaLabList = periksaLabBo.getByCriteriaHeaderPemeriksaan(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdHeaderPemeriksaan());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        BigDecimal totaltarif = new BigDecimal(0);
                        String namaLab = "";

                        List<PeriksaLab> periksaLabs = new ArrayList<>();
                        PeriksaLab periksa = new PeriksaLab();
                        periksa.setIdHeaderPemeriksaan(entity.getIdHeaderPemeriksaan());
                        try {
                            periksaLabs = periksaLabBo.getByCriteria(periksa);
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                        }

                        if (periksaLabs.size() > 0) {
                            for (PeriksaLab pb : periksaLabs) {
                                if ("".equalsIgnoreCase(namaLab)) {
                                    namaLab = " " + pb.getNamaPemeriksaan();
                                } else {
                                    namaLab = namaLab + ", " + pb.getNamaPemeriksaan();
                                }
                            }
                        }

                        try {
                            totaltarif = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdHeaderPemeriksaan());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdHeaderPemeriksaan());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket) && idPaket != null) {

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null) {

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(totaltarif);
                            }
                        } else {

                            if ("Y".equalsIgnoreCase(entity.getIsPeriksaLuar())) {
                                totaltarif = entity.getTarifLabLuar();
                            }

                            // jika bukan paket maka pakai tarif asli
                            if ("rekanan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                                if (ops.getDiskon() != null && ops.getDiskon().intValue() > 0) {
                                    riwayatTindakan.setTotalTarif(totaltarif.multiply(ops.getDiskon()));
                                } else {
                                    riwayatTindakan.setTotalTarif(totaltarif);
                                }
                            } else {
                                riwayatTindakan.setTotalTarif(totaltarif);
                            }
                        }

                        riwayatTindakan.setNamaTindakan("Pemeriksaan " + entity.getKategoriLabName() + namaLab);
                        riwayatTindakan.setKeterangan(entity.getKategori());
                        riwayatTindakan.setJenisPasien(entity.getJenisPeriksaPasien());
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());
                        String ktb = "";
                        if ("lab".equalsIgnoreCase(entity.getKategori())) {
                            ktb = "laboratorium";
                        } else {
                            ktb = "radiologi";
                        }
                        riwayatTindakan.setKategoriTindakanBpjs(ktb);

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
                            if ("rekanan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                                if (ops.getDiskon() != null && ops.getDiskon().intValue() > 0) {
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
                                if ("rekanan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                                    if (ops.getDiskon() != null && ops.getDiskon().intValue() > 0) {
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
            } else {

                OrderGizi orderGizi = new OrderGizi();
                orderGizi.setIdDetailCheckup(idDetail);
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
                            if ("rekanan".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                                if (ops.getDiskon() != null && ops.getDiskon().intValue() > 0) {
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

            HeaderPendampingMakanan headerPendampingMakanan = new HeaderPendampingMakanan();
            headerPendampingMakanan.setIdDetailCheckup(idDetail);
            List<HeaderPendampingMakanan> headerPendampingMakananList = new ArrayList<>();
            try {
                headerPendampingMakananList = headerPendampingMakananBo.getByCriteria(headerPendampingMakanan);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Error, " + e.getMessage());
            }
            if (headerPendampingMakananList.size() > 0) {
                for (HeaderPendampingMakanan header : headerPendampingMakananList) {
                    DetailPendampingMakanan detailPendampingMakanan = new DetailPendampingMakanan();
                    detailPendampingMakanan.setIdHeaderPendampingMakanan(header.getIdHeaderPendampingMakanan());
                    List<DetailPendampingMakanan> detailPendampingMakananList = new ArrayList<>();
                    try {
                        detailPendampingMakananList = detailPendampingMakananBo.getByCriteria(detailPendampingMakanan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Error, " + e.getMessage());
                    }
                    if (detailPendampingMakananList.size() > 0) {
                        for (DetailPendampingMakanan detail : detailPendampingMakananList) {
                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(detail.getIdDetailPendampingMakanan());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }
                            if (riwayatTindakanList.isEmpty()) {
                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(detail.getIdDetailPendampingMakanan());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan(detail.getNama() + " (Qty " + detail.getQty() + ")");
                                riwayatTindakan.setTotalTarif(detail.getTotalTarif());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(detail.getCreatedDate());

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
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

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
            reportParams.put("catatanKlinis", checkup.getCatatanKlinis());
            reportParams.put("pemeriksaanFisik", "Tensi :"+checkup.getTensi()+" mmHg, Suhu : "+checkup.getSuhu()+" ˚C, RR : "+checkup.getPernafasan()+" x/menit, Nadi : "+checkup.getNadi()+" x/menit");
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

            if ("SP15".equalsIgnoreCase(tipe) || "SP16".equalsIgnoreCase(tipe) || "SP17".equalsIgnoreCase(tipe) || "SP19".equalsIgnoreCase(tipe)) {
                String penunjang = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), null);
                String terapi = checkupBoProxy.getResepPasien(checkup.getIdDetailCheckup());
                String diagnosaMasuk = checkupBoProxy.getDiagnosaMasuk(checkup.getIdDetailCheckup());
                String diagnosaPrimer = checkupBoProxy.getDiagnosaPrimer(checkup.getIdDetailCheckup());
                String diagnosaSekunder = checkupBoProxy.getDiagnosaSekunder(checkup.getIdDetailCheckup());
                String tindakanIcd9 = checkupBoProxy.getTindakanRawatICD9(checkup.getIdDetailCheckup());
                String lab = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), "lab");
                String radiologi = checkupBoProxy.getPenunjangMedis(checkup.getIdDetailCheckup(), "radiologi");
                reportParams.put("penunjang", penunjang);
                reportParams.put("diagnosaPrimer", diagnosaPrimer);
                reportParams.put("diagnosaSekunder", diagnosaSekunder);
                reportParams.put("diagnosa", diagnosaPrimer);
                reportParams.put("terapi", terapi);
                reportParams.put("tindakan", tindakanIcd9);
                reportParams.put("lab", lab);
                reportParams.put("radiologi", radiologi);
                reportParams.put("keterangan", checkup.getCatatan());
                DokterTeam dokterTeam = teamDokterBoProxy.getNamaDokter(checkup.getIdDetailCheckup(), false);
                reportParams.put("dokter", dokterTeam.getNamaDokter());
                reportParams.put("sip", dokterTeam.getSip());
                reportParams.put("diagnosaMasuk", diagnosaMasuk);
                reportParams.put("indikasi", checkup.getIndikasi());

                if("SP15".equalsIgnoreCase(tipe)){
                    KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");
                    KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                    keperawatanRawatJalan.setIdDetailCheckup(checkup.getIdDetailCheckup());
                    keperawatanRawatJalan.setJenis("resume_medis");
                    HeaderCheckup headerCheckup = keperawatanRawatJalanBo.getDataResumeMedis(keperawatanRawatJalan);
                    if(headerCheckup != null){
                        reportParams.put("ttdPasien", headerCheckup.getTtdPasien());
                        reportParams.put("ttdDokter", headerCheckup.getTtdDokter());
                        reportParams.put("penunjangLab", headerCheckup.getPenunjangLab());
                        reportParams.put("penunjangRadiologi", headerCheckup.getPenunjangRadiologi());
                    }
                }

                if("SP16".equalsIgnoreCase(tipe)){
                    RingkasanPasienBo ringkasanPasienBo = (RingkasanPasienBo) ctx.getBean("ringkasanPasienBoProxy");
                    HeaderCheckup headerCheckup = ringkasanPasienBo.getResumeMedis(checkup.getIdDetailCheckup());
                    if(headerCheckup != null){
                        reportParams.put("ttdPasien", headerCheckup.getTtdPasien());
                        reportParams.put("ttdDokter", headerCheckup.getTtdDokter());
                        reportParams.put("penunjangLab", headerCheckup.getPenunjangLab());
                        reportParams.put("penunjangRadiologi", headerCheckup.getPenunjangRadiologi());
                        reportParams.put("kondisiPulang", headerCheckup.getKondisiPulang());
                        reportParams.put("keadaanPulang", headerCheckup.getKeadaanPulang());
                        reportParams.put("penyakit", headerCheckup.getPenyakitDahulu());
                        reportParams.put("tglKeluar", headerCheckup.getStTglKeluar());
                        reportParams.put("terapiPulang", headerCheckup.getTerapi());
                        reportParams.put("tindakLanjut", headerCheckup.getTindakLanjut());
                        reportParams.put("prognosis", headerCheckup.getPrognosis());
                    }
                }
            }

            if("CK04".equalsIgnoreCase(tipe) || "CK01".equalsIgnoreCase(tipe)){
                KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");
                KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                keperawatanRawatJalan.setIdDetailCheckup(checkup.getIdDetailCheckup());
                keperawatanRawatJalan.setJenis("general_concent");
                HeaderCheckup headerCheckup = keperawatanRawatJalanBo.getDataResumeMedis(keperawatanRawatJalan);
                if(headerCheckup != null){
                    reportParams.put("namaPasien", headerCheckup.getNama());
                    reportParams.put("namaPemberi", headerCheckup.getNamaDokter());
                    reportParams.put("pasien", headerCheckup.getTtdPasien());
                    reportParams.put("petugas", headerCheckup.getTtdDokter());
                }
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

        if ("INA".equalsIgnoreCase(tipe) || "RB".equalsIgnoreCase(tipe) || "ICU".equalsIgnoreCase(tipe) || "OK".equalsIgnoreCase(tipe)) {
            AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
            AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
            AsesmenIcuBo asesmenIcuBo = (AsesmenIcuBo) ctx.getBean("asesmenIcuBoProxy");
            KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
            PersetujuanTindakanMedis persetujuanTindakanMedis = new PersetujuanTindakanMedis();
            reportParams.put("keterangan", getKeterangan());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            if (getCreatedDate() != null && !"".equalsIgnoreCase(getCreatedDate())) {
                try {
                    Date parsedDate = dateFormat.parse(getCreatedDate());
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    reportParams.put("createdDate", timestamp);
                    if ("INA".equalsIgnoreCase(tipe)) {
                        AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                        asesmenRawatInap.setIdDetailCheckup(getId());
                        asesmenRawatInap.setKeterangan(getKeterangan());
                        asesmenRawatInap.setCreatedDate(timestamp);
                        persetujuanTindakanMedis = asesmenRawatInapBo.getPersetujuanTindakan(asesmenRawatInap);
                    }
                    if ("ICU".equalsIgnoreCase(tipe)) {
                        AsesmenIcu asesmenIcu = new AsesmenIcu();
                        asesmenIcu.setIdDetailCheckup(getId());
                        asesmenIcu.setKeterangan(getKeterangan());
                        asesmenIcu.setCreatedDate(timestamp);
                        persetujuanTindakanMedis = asesmenIcuBo.getPersetujuanTindakan(asesmenIcu);
                    }
                    if ("RB".equalsIgnoreCase(tipe)) {
                        Kandungan kandungan = new Kandungan();
                        kandungan.setIdDetailCheckup(getId());
                        kandungan.setKeterangan(getKeterangan());
                        kandungan.setCreatedDate(timestamp);
                        persetujuanTindakanMedis = kandunganBo.getPersetujuanTindakan(kandungan);
                    }
                    if ("OK".equalsIgnoreCase(tipe)) {
                        AsesmenOperasi asesmenOperasi = new AsesmenOperasi();
                        asesmenOperasi.setIdDetailCheckup(getId());
                        asesmenOperasi.setKeterangan(getKeterangan());
                        asesmenOperasi.setCreatedDate(timestamp);
                        persetujuanTindakanMedis = asesmenOperasiBo.getPersetujuanTindakan(asesmenOperasi);
                    }

                    if (persetujuanTindakanMedis.getTindakanMedisList().size() > 0) {
                        reportParams.put("pihak1", persetujuanTindakanMedis.getPihak1());
                        reportParams.put("ttdPihak1", persetujuanTindakanMedis.getTtdPihak1());
                        reportParams.put("pihak2", persetujuanTindakanMedis.getPihak2());
                        reportParams.put("ttdPihak2", persetujuanTindakanMedis.getTtdPihak2());
                        reportParams.put("namaMenyatakan", persetujuanTindakanMedis.getNamaMenyatakan());
                        reportParams.put("sipMenyatakan", persetujuanTindakanMedis.getSipMenyatakan());
                        reportParams.put("ttdYangMenyatakan", persetujuanTindakanMedis.getTtdMenyatakan());
                        reportParams.put("pernyataan1", persetujuanTindakanMedis.getPernyataan1());
                        reportParams.put("pernyataan2", persetujuanTindakanMedis.getPernyataan2());
                        reportParams.put("pernyataan3", persetujuanTindakanMedis.getPernyataan3());
                        reportParams.put("namaPernyataan1", persetujuanTindakanMedis.getNamaPernyataan1());
                        reportParams.put("namaPernyataan2", persetujuanTindakanMedis.getNamaPernyataan2());
                        reportParams.put("sipPernyataan1", persetujuanTindakanMedis.getSipPernyataan1());
                        reportParams.put("ttdPernyataan1", persetujuanTindakanMedis.getTtdPernyataan1());
                        reportParams.put("ttdPernyataan2", persetujuanTindakanMedis.getTtdPernyataan2());
                        myList.addAll(persetujuanTindakanMedis.getTindakanMedisList());
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
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
            if ("SP18".equalsIgnoreCase(tipe)) {
                return "print_surat_cuti";
            }
            if ("SP19".equalsIgnoreCase(tipe)) {
                return "print_aturan_makan_diluar";
            }
            if ("SP20".equalsIgnoreCase(tipe)) {
                return "print_buta_warna";
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
            if ("INA".equalsIgnoreCase(tipe) ||
                    "RB".equalsIgnoreCase(tipe) ||
                    "ICU".equalsIgnoreCase(tipe) ||
                    "OK".equalsIgnoreCase(tipe)) {
                return "print_persetujuan_medis";
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

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab labData = new PeriksaLab();
            labData.setIdHeaderPemeriksaan(lab);
            try {
                periksaLabList = periksaLabBoProxy.getByCriteriaHeaderPemeriksaan(labData);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                labData = periksaLabList.get(0);
            }

            if ("lab".equalsIgnoreCase(tipe)) {
                reportParams.put("title", "Hasil Periksa Lab");
                reportParams.put("divisi", "Laboratorium");
            } else {
                reportParams.put("title", "Hasil Periksa Radiologi");
                reportParams.put("divisi", "Radiologi");
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
            reportParams.put("tgllahir", formatDate);
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

            reportParams.put("idDokterPengirim", labData.getIdDokterPengirim());
            reportParams.put("dokterPengirim", labData.getNamaDokterPengirim());
            reportParams.put("idPetugas", labData.getIdPetugas());
            reportParams.put("namaPetugas", labData.getNamaPetugas());
            reportParams.put("idValidator", labData.getIdValidator());
            reportParams.put("namaValidator", labData.getNamaValidator());
            reportParams.put("ttdPetugas", labData.getTtdPetugas());
            reportParams.put("ttdValidator", labData.getTtdValidator());
            reportParams.put("umur", CommonUtil.calculateAge(checkup.getTglLahir(), true));

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

    public String printNoRujukan() {
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
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idDetailCheckup", id);
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            reportParams.put("noBpjs", checkup.getNoBpjs());

            if (checkup.getTglLahir() != null) {
                String tahun = calculateAge(checkup.getTglLahir(), false);
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
                reportParams.put("umur", tahun);
            }

            if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                jk = "Laki-Laki";
            } else {
                jk = "Perempuan";
            }

            reportParams.put("jenisKelamin", jk);
            reportParams.put("diagnosa", "(" + checkup.getDiagnosa() + ") " + checkup.getNamaDiagnosa());

            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                return "init_add";
            }
        }
        return "print_rujukan";
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

    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan) {

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
        TempatTidurBo tempatTidurBo = (TempatTidurBo) ctx.getBean("tempatTidurBoProxy");

        String divisiId = "";

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan) || "lab".equalsIgnoreCase(keterangan)) {
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)) {

            detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
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

            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan)) {
                            List<TempatTidur> tempatTidurList = new ArrayList<>();
                            TempatTidur tt = new TempatTidur();
                            TempatTidur tempatTidur = new TempatTidur();
                            tt.setIdTempatTidur(idRuangan);
                            tempatTidurList = tempatTidurBo.getDataTempatTidur(tt);
                            if (tempatTidurList.size() > 0) {
                                divisiId = tempatTidurList.get(0).getKodering();
                            }
                        } else {
                            RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                            if (lastRuangan != null) {
                                List<TempatTidur> tempatTidurList = new ArrayList<>();
                                TempatTidur tt = new TempatTidur();
                                TempatTidur tempatTidur = new TempatTidur();
                                tt.setIdTempatTidur(idRuangan);
                                tempatTidurList = tempatTidurBo.getDataTempatTidur(tt);
                                if (tempatTidurList.size() > 0) {
                                    divisiId = tempatTidurList.get(0).getKodering();
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

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type, String idRuangan) {
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

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan))
            riwayatTindakan.setIdRuangan(idRuangan);

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // cari id dokter per keterangan riwayat tindakan
                String idDokterTindakan = getIdDokter(riwayatTindakanEntity.getIdTindakan(), riwayatTindakanEntity.getKeterangan());

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
                    activityMap.put("person_id", idDokterTindakan != null && !"".equalsIgnoreCase(idDokterTindakan) ? idDokterTindakan : idDokter);
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

    private List<Activity> getListAcitivity(String idDetailCheckup, String jenisPasien, String ket, String type, String idRuangan) {
        logger.info("[CheckupDetailAction.getListAcitivity] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Activity> activityList = new ArrayList<>();

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

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan))
            riwayatTindakan.setIdRuangan(idRuangan);

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // cari id dokter per keterangan riwayat tindakan
                String idDokterTindakan = getIdDokter(riwayatTindakanEntity.getIdTindakan(), riwayatTindakanEntity.getKeterangan());

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
                    Activity activity = new Activity();
                    activity.setActivityId(riwayatTindakanEntity.getIdTindakan());
                    activity.setPersonId(idDokterTindakan != null && !"".equalsIgnoreCase(idDokterTindakan) ? idDokterTindakan : idDokter);
                    activity.setNilai(riwayatTindakanEntity.getTotalTarif().add(ppn));
                    activity.setNoTrans(riwayatTindakanEntity.getIdDetailCheckup());
                    activity.setTipe(riwayatTindakanEntity.getJenisPasien());
                    activityList.add(activity);
                }
            }
        }
        //** mencari tindakan dan dimasukan ke jurnal detail activity. END **//
        logger.info("[CheckupDetailAction.getListAcitivity] END <<<");
        return activityList;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan, String noCheckup) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        boolean isNoCheckup = noCheckup != null && !"".equalsIgnoreCase(noCheckup);

        BigDecimal nilai = new BigDecimal(0);
        try {
            if (idRuangan != null && !"".equalsIgnoreCase(idRuangan)) {
                // untuk tipe transaksi rawat inap
                nilai = checkupDetailBo.getSumJumlahTindakanByJenisRI(idDetailCheckup, jenisPasien, keterangan, idRuangan);
            } else if (isNoCheckup) {

                // jika per noCheckup
                List<String> listIdDetailCheckup = riwayatTindakanBo.getListIdDetailCheckup(noCheckup);

                if (listIdDetailCheckup.size() > 0) {

                    for (String idDetail : listIdDetailCheckup) {
                        BigDecimal nilaiTindakan = checkupDetailBo.getSumJumlahTindakanByJenis(idDetail, jenisPasien, keterangan);
                        if (nilaiTindakan != null) {
                            nilai = nilai.add(nilaiTindakan);
                        }
                    }
                }
            } else {

                // jika per detail checkup selain RI
                nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
            }
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    private String getIdDokter(String idTindakan, String keterangan) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        String idDokter = "";
        if ("tindakan".equalsIgnoreCase(keterangan)) {
            ItSimrsTindakanRawatEntity tindakanRawatEntity = tindakanRawatBo.getTindakanRawatEntityById(idTindakan);
            if (tindakanRawatEntity != null) {
                idDokter = tindakanRawatEntity.getIdDokter();
            }
        }
        if ("laboratorium".equalsIgnoreCase(keterangan)) {
            ItSimrsPeriksaLabEntity periksaLabEntity = periksaLabBo.getPeriksaLabEntityById(idTindakan);
            if (periksaLabEntity != null) {
//                idDokter = periksaLabEntity.getIdDokter();
            }
        }
        if ("radiologi".equalsIgnoreCase(keterangan)) {
            ItSimrsPeriksaRadiologiEntity periksaRadiologiEntity = periksaRadiologiBo.getEntityPeriksaRadiologi(idTindakan);
            if (periksaRadiologiEntity != null) {
                idDokter = periksaRadiologiEntity.getIdDokterRadiologi();
            }
        }
        if ("resep".equalsIgnoreCase(keterangan)) {
            ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idTindakan);
            if (permintaanResepEntity != null) {
                idDokter = permintaanResepEntity.getIdDokter();
            }
        }
        return idDokter;
    }

    public String printNoAntrian() {
        String id = getId();
        String tipe = getTipe();
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

        reportParams.put("area", CommonUtil.userAreaName());
        reportParams.put("unit", CommonUtil.userBranchNameLogin());
        reportParams.put("logo", logo);
        reportParams.put("idPasien", id);
        reportParams.put("idPelayanan", tipe);

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            return "search";
        }

        return "print_no_antrian";
    }

    public KeteranganKeluar initKeteranganKeluar(String id) {
        KeteranganKeluar res = new KeteranganKeluar();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeteranganKeluarBo keteranganKeluarBo = (KeteranganKeluarBo) ctx.getBean("keteranganKeluarBoProxy");
        List<KeteranganKeluar> keluarList = new ArrayList<>();
        try {
            res.setIdKeterangan(id);
            keluarList = keteranganKeluarBo.getByCriteria(res);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        if (keluarList.size() > 0) {
            res = keluarList.get(0);
        }
        return res;
    }

    public CrudResponse uploadFilePemeriksaan(String data) {
        logger.info("[CheckupDetailAction.uploadFilePemeriksaan] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        if (data != null && !"".equalsIgnoreCase(data)) {
            try {
                JSONObject obj = new JSONObject(data);
                String idDetailCheckup = obj.getString("id_detail_checkup");
                String stringByte = obj.getString("byte");
                String keterangan = obj.getString("keterangan");
                String eks = obj.getString("eks");
                String name = obj.getString("file_name");
                String fileName = "";
                String wkt = updateTime.toString();
                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(stringByte);
                String cekPath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PENDUKUNG_PEMERIKSAAN;
                File theDir = new File(cekPath);
                if (!theDir.exists()) {
                    theDir.mkdirs();
                    theDir.setReadable(true);
                    theDir.setExecutable(true);
                    theDir.setWritable(true);
                }

                if ("pdf".equalsIgnoreCase(eks)) {
                    fileName = idDetailCheckup + "-" + patten + "-" + name + ".pdf";
                    File file = new File(cekPath + fileName);
                    FileOutputStream fop = new FileOutputStream(file);
                    fop.write(decodedBytes);
                    fop.flush();
                    fop.close();
                } else {
                    fileName = idDetailCheckup + "-" + patten + "-" + name + ".jpg";
                    String uploadFile = cekPath + fileName;
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                    if (image == null) {
                        logger.error("Buffered Image is null");
                    } else {
                        response = CommonUtil.compressImage(image, "png", uploadFile);
                    }
                }

                ItSimrsUploadPendukungPemeriksaanEntity entity = new ItSimrsUploadPendukungPemeriksaanEntity();
                entity.setIdDetailCheckup(idDetailCheckup);
                entity.setUrlImg(fileName);
                entity.setKeterangan(keterangan);
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedWho(userLogin);
                entity.setCreatedDate(updateTime);
                entity.setLastUpdate(updateTime);
                entity.setLastUpdateWho(userLogin);

                response = checkupDetailBo.saveUploadPemeriksaan(entity);
                response.setStatus("success");
            } catch (Exception e) {
                logger.error("[CheckupDetailAction.uploadFilePemeriksaan] Error, " + e.getMessage());
                response.setStatus("error");
                response.setMsg("ERROR, " + e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data yang dikirim tidak lengkap");
        }
        logger.info("[CheckupDetailAction.uploadFilePemeriksaan] end process >>>");
        return response;
    }

    public CrudResponse deleteUploadFilePemeriksaan(String id) {
        logger.info("[CheckupDetailAction.uploadFilePemeriksaan] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                checkupDetailBo.deleteUploadPemeriksaan(id);
                response.setStatus("success");
                response.setMsg("OK");
            } catch (Exception e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.uploadFilePemeriksaan] ERROR, " + e.getMessage());
            }
        }
        logger.info("[CheckupDetailAction.uploadFilePemeriksaan] end process >>>");
        return response;
    }

    public List<UploadPendukungPemeriksaan> getListUploadPendukungPemeriksaan(String id) {
        logger.info("[CheckupDetailAction.getListUploadPendukungPemeriksaan] start process >>>");
        List<UploadPendukungPemeriksaan> response = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        UploadPendukungPemeriksaan pendukungPemeriksaan = new UploadPendukungPemeriksaan();
        pendukungPemeriksaan.setIdDetailCheckup(id);
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                response = checkupDetailBo.getListUploadPemeriksaan(pendukungPemeriksaan);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("[CheckupDetailAction.getListUploadPendukungPemeriksaan] end process >>>");
        return response;
    }

    public CrudResponse sendToTppti(String id, String lanjut, String indikasi, String selesai) {
        logger.info("[CheckupDetailAction.sendToTppti] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        if (id != null && !"".equalsIgnoreCase(id)) {
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(id);
            detailCheckup.setTindakLanjut(lanjut);
            detailCheckup.setIndikasi(indikasi);
            detailCheckup.setKeteranganSelesai(selesai);
            detailCheckup.setLastUpdateWho(userLogin);
            detailCheckup.setLastUpdate(updateTime);
            try {
                checkupDetailBo.sendToTppti(detailCheckup);
                response.setStatus("success");
                response.setMsg("OK");
            } catch (Exception e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.sendToTppti] ERROR, " + e.getMessage());
            }
        }
        logger.info("[CheckupDetailAction.sendToTppti] end process >>>");
        return response;
    }

    public ItSimrsHeaderDetailCheckupEntity getDetailCheckup(String id) {
        logger.info("[CheckupDetailAction.getDetailCheckup] start process >>>");
        ItSimrsHeaderDetailCheckupEntity response = new ItSimrsHeaderDetailCheckupEntity();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                response = checkupDetailBo.getEntityById(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("[CheckupDetailAction.getDetailCheckup] end process >>>");
        return response;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
