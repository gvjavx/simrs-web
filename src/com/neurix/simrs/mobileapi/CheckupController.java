package com.neurix.simrs.mobileapi;

import com.google.gson.Gson;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dashboard.bo.DashboardBo;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.neurix.simrs.bpjs.vclaim.model.SepResponse;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
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
import com.neurix.simrs.mobileapi.model.*;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.initdashboard.bo.InitDashboardBo;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.DetailPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
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
import com.neurix.simrs.transaksi.transketeranganobat.model.ItSimrsKeteranganResepEntity;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.*;
import org.apache.commons.collections.comparators.FixedOrderComparator;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gondok
 * Friday, 24/01/20 11:08
 */
public class CheckupController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CheckupController.class);
    private CheckupMobile model = new CheckupMobile();
    private CrudResponse crudResponse = new CrudResponse();
    private Collection<CheckupMobile> listOfCheckup = new ArrayList<>();
    private Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup = new ArrayList<>();
    private Collection<ObatPoliMobile> listOfObatPoli = new ArrayList<>();
    private Collection<PermintaanResepMobile> listOfPermintaanResep = new ArrayList<>();
    private Collection<DokterMobile> listOfDokter = new ArrayList<>();
    private Collection<PaketPeriksaMobile> listOfPaketPeriksa = new ArrayList<>();
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private ObatPoliBo obatPoliBoProxy;
    private PermintaanResepBo permintaanResepBoProxy;
    private RawatInapBo rawatInapBoProxy;
    private RuanganBo ruanganBoProxy;
    private DokterBo dokterBoProxy;
    private InitDashboardBo initDashboardBoProxy;
    private BranchBo branchBoProxy;
    private PaketPeriksaBo paketPeriksaBoProxy;

    private String idDetailCheckup;
    private String noCheckup;
    private String idPasien;
    private String nama;
    private String idPoli;
    private String idStatusPasien;
    private String tglMasuk;
    private String branchId;
    private String action;

    private File fileUploadTtd;
    private String fileNameTtd;
    private String base64Ttd;

    private String idPelayanan;
    private String idObat;

    private String jsonResep;

    private String tujuanPelayanan;
    private String idDokter;

    private String username;

    private String idKtg;
    private String kelas;
    private String ket;
    private String tglCheckup;
    private String ketCheckup;
    private String jenisPasien;
    private String caraPulang;
    private String pendamping;
    private String tujuan;
    private String metodeBayar;
    private String uangMuka;
    private String jenisBayar;

    private String flagCall;
    private String jenisResep;
    private String idJenisObat;

    private String idTindakan;
    private String keterangan;

    private String bulan;
    private String tahun;

    private String jenisKunjungan;

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    public String getJenisKunjungan() {
        return jenisKunjungan;
    }

    public void setJenisKunjungan(String jenisKunjungan) {
        this.jenisKunjungan = jenisKunjungan;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public InitDashboardBo getInitDashboardBoProxy() {
        return initDashboardBoProxy;
    }

    public void setInitDashboardBoProxy(InitDashboardBo initDashboardBoProxy) {
        this.initDashboardBoProxy = initDashboardBoProxy;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdJenisObat() {
        return idJenisObat;
    }

    public void setIdJenisObat(String idJenisObat) {
        this.idJenisObat = idJenisObat;
    }

    public String getJenisResep() {
        return jenisResep;
    }

    public void setJenisResep(String jenisResep) {
        this.jenisResep = jenisResep;
    }

    public String getFlagCall() {
        return flagCall;
    }

    public void setFlagCall(String flagCall) {
        this.flagCall = flagCall;
    }

    public String getBase64Ttd() {
        return base64Ttd;
    }

    public void setBase64Ttd(String base64Ttd) {
        this.base64Ttd = base64Ttd;
    }

    public CrudResponse getCrudResponse() {
        return crudResponse;
    }

    public void setCrudResponse(CrudResponse crudResponse) {
        this.crudResponse = crudResponse;
    }

    public Collection<DokterMobile> getListOfDokter() {
        return listOfDokter;
    }

    public void setListOfDokter(Collection<DokterMobile> listOfDokter) {
        this.listOfDokter = listOfDokter;
    }

    public DokterBo getDokterBoProxy() {
        return dokterBoProxy;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public RuanganBo getRuanganBoProxy() {
        return ruanganBoProxy;
    }

    public void setRuanganBoProxy(RuanganBo ruanganBoProxy) {
        this.ruanganBoProxy = ruanganBoProxy;
    }

    public String getIdKtg() {
        return idKtg;
    }

    public void setIdKtg(String idKtg) {
        this.idKtg = idKtg;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getTglCheckup() {
        return tglCheckup;
    }

    public void setTglCheckup(String tglCheckup) {
        this.tglCheckup = tglCheckup;
    }

    public String getKetCheckup() {
        return ketCheckup;
    }

    public void setKetCheckup(String ketCheckup) {
        this.ketCheckup = ketCheckup;
    }

    public String getJenisPasien() {
        return jenisPasien;
    }

    public void setJenisPasien(String jenisPasien) {
        this.jenisPasien = jenisPasien;
    }

    public String getCaraPulang() {
        return caraPulang;
    }

    public void setCaraPulang(String caraPulang) {
        this.caraPulang = caraPulang;
    }

    public String getPendamping() {
        return pendamping;
    }

    public void setPendamping(String pendamping) {
        this.pendamping = pendamping;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(String uangMuka) {
        this.uangMuka = uangMuka;
    }

    public String getJenisBayar() {
        return jenisBayar;
    }

    public void setJenisBayar(String jenisBayar) {
        this.jenisBayar = jenisBayar;
    }

    public Collection<PermintaanResepMobile> getListOfPermintaanResep() {
        return listOfPermintaanResep;
    }

    public void setListOfPermintaanResep(Collection<PermintaanResepMobile> listOfPermintaanResep) {
        this.listOfPermintaanResep = listOfPermintaanResep;
    }

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PermintaanResepBo getPermintaanResepBoProxy() {
        return permintaanResepBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getTujuanPelayanan() {
        return tujuanPelayanan;
    }

    public void setTujuanPelayanan(String tujuanPelayanan) {
        this.tujuanPelayanan = tujuanPelayanan;
    }

    public String getJsonResep() {
        return jsonResep;
    }



    public void setJsonResep(String jsonResep) {
        this.jsonResep = jsonResep;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public Collection<ObatPoliMobile> getListOfObatPoli() {
        return listOfObatPoli;
    }

    public void setListOfObatPoli(Collection<ObatPoliMobile> listOfObatPoli) {
        this.listOfObatPoli = listOfObatPoli;
    }

    public ObatPoliBo getObatPoliBoProxy() {
        return obatPoliBoProxy;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    public String getFileNameTtd() {
        return fileNameTtd;
    }

    public void setFileNameTtd(String fileNameTtd) {
        this.fileNameTtd = fileNameTtd;
    }

    public Collection<HeaderDetailCheckupMobile> getListOfHeaderCheckup() {
        return listOfHeaderCheckup;
    }

    public void setListOfHeaderCheckup(Collection<HeaderDetailCheckupMobile> listOfHeaderCheckup) {
        this.listOfHeaderCheckup = listOfHeaderCheckup;
    }

    public File getFileUploadTtd() {
        return fileUploadTtd;
    }

    public void setFileUploadTtd(File fileUploadTtd) {
        this.fileUploadTtd = fileUploadTtd;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public String getIdDetailCheckup() {
        return idDetailCheckup;
    }

    public void setIdDetailCheckup(String idDetailCheckup) {
        this.idDetailCheckup = idDetailCheckup;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPoli() {
        return idPoli;
    }

    public void setIdPoli(String idPoli) {
        this.idPoli = idPoli;
    }

    public String getIdStatusPasien() {
        return idStatusPasien;
    }

    public void setIdStatusPasien(String idStatusPasien) {
        this.idStatusPasien = idStatusPasien;
    }

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(CheckupMobile model) {
        this.model = model;
    }

    public Collection<CheckupMobile> getListOfCheckup() {
        return listOfCheckup;
    }

    public void setListOfCheckup(Collection<CheckupMobile> listOfCheckup) {
        this.listOfCheckup = listOfCheckup;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public Object getModel() {
        switch (action) {
            case "search":
                return listOfCheckup;
            case "dataPasien":
                return listOfHeaderCheckup;
            case "getObatPoli":
                return listOfObatPoli;
            case "getObatPoliGroup":
                return listOfObatPoli;
            case "getObatPoliGroupSerupa":
                return listOfObatPoli;
            case "getPermintaanResep":
                return listOfPermintaanResep;
            case "saveKeteranganRawatJalan":
                return crudResponse;
            case "getDokterByIdPelayanan":
                return listOfDokter;
            case "getHistoryPasien":
                return listOfHeaderCheckup;
            case "getDetailHistoryPasien":
                return listOfHeaderCheckup;
            case "getListAntrian":
                return listOfCheckup;
            case "getKunjunganRJ":
                return listOfCheckup;
            case "getDetailKunjunganRJ":
                return listOfCheckup;
            case "getTahunPeriksa":
                return listOfCheckup;
            case "getComboBranch":
                return listOfCheckup;
            case "getKamarTerpakai":
                return listOfCheckup;
            case "getDetailKunjunganJK":
                return listOfCheckup;
            case "getPaketPeriksa":
                return listOfPaketPeriksa;
            default:
                return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[CheckupController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());


        if (action.equalsIgnoreCase("search")) {
            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdPasien(idPasien);
            bean.setNamaPasien(nama);
            bean.setIdPelayanan(idPoli);
            bean.setStDateFrom(tglMasuk);
            bean.setStDateTo(tglMasuk);
            bean.setBranchId(branchId);
            bean.setStatusPeriksa(idStatusPasien);

            try {
               result = checkupDetailBoProxy.getSearchRawatJalan(bean);
            } catch (GeneralBOException e) {
                logger.error("CheckupController.create] Error when get rawat jalan",e);
            }

            if (result != null & result.size() > 0) {
                for (HeaderDetailCheckup item : result) {
                    CheckupMobile checkupMobile = new CheckupMobile();
                    checkupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    checkupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    checkupMobile.setJenisPeriksaPasien(item.getJenisPeriksaPasien());
                    checkupMobile.setNoCheckup(item.getNoCheckup());
                    checkupMobile.setIdPasien(item.getIdPasien());
                    checkupMobile.setNamaPasien(item.getNamaPasien());
                    checkupMobile.setAlamat(item.getAlamat());
                    checkupMobile.setStatusPeriksa(item.getStatusPeriksa());
                    checkupMobile.setStatusBayar(item.getStatusBayar());
                    checkupMobile.setCreatedDate(CommonUtil.convertDateToString(new Date(item.getCreatedDate().getTime())));

                    listOfCheckup.add(checkupMobile);
                }
            }
        } else if (action.equalsIgnoreCase("dataPasien")){

            HeaderCheckup result = new HeaderCheckup();

            try {
               result = checkupBoProxy.getDataDetailPasien(idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get rawat jalan",e);

            }

            if (result != null) {
               HeaderDetailCheckupMobile headerDetailCheckupMobile = new HeaderDetailCheckupMobile();
               headerDetailCheckupMobile.setNoCheckup(result.getNoCheckup());
               headerDetailCheckupMobile.setIdPasien(result.getIdPasien());
               headerDetailCheckupMobile.setNamaPasien(result.getNama());
               headerDetailCheckupMobile.setJenisKelamin(result.getJenisKelamin());
               headerDetailCheckupMobile.setNik(result.getNoKtp());
               headerDetailCheckupMobile.setTempatLahir(result.getTempatLahir());
               headerDetailCheckupMobile.setIdJenisPeriksaPasien(result.getIdJenisPeriksaPasien());
               headerDetailCheckupMobile.setIdDetailCheckup(result.getIdDetailCheckup());
               headerDetailCheckupMobile.setNamaPelayanan(result.getNamaPelayanan());
               headerDetailCheckupMobile.setNoSep(result.getNoSep());
               headerDetailCheckupMobile.setTglLahir(result.getStTglLahir());
               headerDetailCheckupMobile.setUrlTtd(result.getUrlTtd());
               headerDetailCheckupMobile.setIdDokter(result.getIdDokter());
               headerDetailCheckupMobile.setIdPelayanan(result.getIdPelayanan());
               headerDetailCheckupMobile.setFlagCall(result.getFlagCall());

               listOfHeaderCheckup.add(headerDetailCheckupMobile);
            }
        } else if (action.equalsIgnoreCase("uploadTtd")){

            List<HeaderDetailCheckup> result = new ArrayList<>();

            HeaderDetailCheckup bean = new HeaderDetailCheckup();
            bean.setIdDetailCheckup(idDetailCheckup);

            try {
                result = checkupDetailBoProxy.getByCriteria(bean);

            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get checkup detail by criteria",e);
            }

            if (result != null && result.size() > 0){
                if (fileUploadTtd != null) {
                    if(fileUploadTtd.length() > 0 && fileUploadTtd.length() <= 15728640) {
                        Random random = new Random( System.currentTimeMillis() );
                        String fileNamePhoto = "TTD_" + random.nextInt(999) + "_" + idDetailCheckup + CommonConstant.IMAGE_TYPE;
                        result.get(0).setUrlTtd(fileNamePhoto);
                        File fileCreate = new File(CommonConstant.RESOURCE_IMAGE_TTD, fileNamePhoto);
                        try {
                            FileUtils.copyFile(fileUploadTtd, fileCreate);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    try {
                        checkupDetailBoProxy.saveTtd(result.get(0));
                        model.setMessage("Success");
                    } catch (GeneralBOException e){
                        logger.error("CheckupController.create] Error when save edit",e);
                    }
                }

            }

        } else if (action.equalsIgnoreCase("getObatPoli")) {
            List<ObatPoli> result = new ArrayList<>();

            ObatPoli bean = new ObatPoli();
            bean.setIdObat(idObat);
            bean.setIdPelayanan(idPelayanan);
            bean.setBranchId(branchId);

            try {
                result = obatPoliBoProxy.getObatPoliByCriteria(bean);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli",e);
            }

            if (result != null && result.size() > 0){
                for (ObatPoli item : result){
                    ObatPoliMobile obatPoliMobile  = new ObatPoliMobile();
                    obatPoliMobile.setIdObat(item.getIdObat());
                    obatPoliMobile.setIdPelayanan(item.getIdPelayanan());
                    obatPoliMobile.setFlag(item.getFlag());
                    obatPoliMobile.setQtyBox(item.getQtyBox() != null ? item.getQtyBox().toString() : "0");
                    obatPoliMobile.setQtyLembar(item.getQtyLembar() != null ? item.getQtyLembar().toString() : "0");
                    obatPoliMobile.setQtyBiji(item.getQtyBiji() != null ? item.getQtyBiji().toString(): "0");
                    obatPoliMobile.setQty(item.getQty() != null ? item.getQty().toString() : "0");
                    obatPoliMobile.setAction(item.getAction());
                    obatPoliMobile.setCreatedDate(item.getCreatedDate() != null ? item.getCreatedDate().toLocaleString() : "");
                    obatPoliMobile.setCreatedWho(item.getCreatedWho());
                    obatPoliMobile.setLastUpdate(item.getLastUpdate() != null ? item.getLastUpdate().toLocaleString() : "");
                    obatPoliMobile.setLastUpdateWho(item.getLastUpdateWho());
                    obatPoliMobile.setBranchId(item.getBranchId());
                    obatPoliMobile.setIdPabrik(item.getIdPabrik());
                    obatPoliMobile.setExpiredDate(item.getExpiredDate() != null ? item.getExpiredDate().toLocaleString() : "");
                    obatPoliMobile.setIdBarang(item.getIdBarang());
                    obatPoliMobile.setNamaObat(item.getNamaObat());
                    obatPoliMobile.setLembarPerBox(item.getLembarPerBox() != null ? item.getLembarPerBox().toString() : "0");
                    obatPoliMobile.setBijiPerLembar(item.getBijiPerLembar() != null ? item.getBijiPerLembar().toString() : "0");
                    obatPoliMobile.setFlagKronis(item.getFlagKronis());

                    listOfObatPoli.add(obatPoliMobile);
                }
            }
        } else if (action.equalsIgnoreCase("getObatPoliGroup")){
            List<ObatPoli> result = new ArrayList<>();

            try{
               result = obatPoliBoProxy.getListObatGroupPoli(idPelayanan, branchId, jenisPasien, idJenisObat);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli group",e);
            }

            if (result != null && result.size() > 0) {
                for (ObatPoli item : result){
                    ObatPoliMobile obatPoliMobile = new ObatPoliMobile();
                    obatPoliMobile.setIdObat(item.getIdObat());
                    obatPoliMobile.setNamaObat(item.getNamaObat());
                    obatPoliMobile.setLembarPerBox(item.getLembarPerBox() != null ? item.getLembarPerBox().toString() : "");
                    obatPoliMobile.setBijiPerLembar(item.getBijiPerLembar() != null ? item.getBijiPerLembar().toString() : "");
                    obatPoliMobile.setFlagKronis(item.getFlagKronis());
                    obatPoliMobile.setQtyBox(item.getQtyBox() != null ? item.getQtyBox().toString() : "");
                    obatPoliMobile.setQtyLembar(item.getQtyLembar() != null ? item.getQtyLembar().toString() : "");
                    obatPoliMobile.setQtyBiji(item.getQtyBiji() != null ? item.getQtyLembar().toString() : "");
                    obatPoliMobile.setIdJenisObat(item.getIdJenisObat());

                    listOfObatPoli.add(obatPoliMobile);
                }
            }

        } else if (action.equalsIgnoreCase("saveAddResep")){
            PermintaanResep bean = new PermintaanResep();
            bean.setIdPelayanan(idPelayanan);
            bean.setBranchId(branchId);
            bean.setTujuanPelayanan(tujuanPelayanan);
            bean.setIdDetailCheckup(idDetailCheckup);
            bean.setIdPasien(idPasien);
            bean.setIdDokter(idDokter);
            bean.setCreatedDate(now);
            bean.setCreatedWho(username);
            bean.setLastUpdateWho(username);
            bean.setLastUpdate(now);
            bean.setJenisResep(jenisResep);
//            bean.setTtdDokter(base64Ttd);

            List<TransaksiObatDetail> list = new ArrayList<>();
            JSONArray jsonArray;


            if (fileUploadTtd != null) {
                if(fileUploadTtd.length() > 0 && fileUploadTtd.length() <= 15728640) {
                    String fileNamePhoto =  idDokter+"-"+idDetailCheckup+"-"+dateFormater("MM")+dateFormater("yy")+".png";

                    bean.setTtdDokter(fileNamePhoto);
                    File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_TTD_DOKTER, fileNamePhoto);
                    try {
                        FileUtils.copyFile(fileUploadTtd, fileCreate);
                        bean.setTtdDokter(fileNamePhoto);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            if (jsonResep != null && !jsonResep.equalsIgnoreCase("")){
                try{
                  jsonArray = (net.sf.json.JSONArray) JSONSerializer.toJSON(jsonResep);
                  for (int i = 0; i < jsonArray.size(); i++){
                      TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
                      transaksiObatDetail.setIdObat(jsonArray.getJSONObject(i).getString("idObat"));
                      transaksiObatDetail.setQty(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                      transaksiObatDetail.setQtyApprove(BigInteger.valueOf(Long.valueOf(jsonArray.getJSONObject(i).getString("qty"))));
                      transaksiObatDetail.setJenisSatuan(jsonArray.getJSONObject(i).getString("jenisSatuan"));
//                      transaksiObatDetail.setKeterangan(jsonArray.getJSONObject(i).getString("keterangan"));
//                      transaksiObatDetail.setFlagRacik(jsonArray.getJSONObject(i).getString("flagRacik"));
                      transaksiObatDetail.setHariKronis(!jsonArray.getJSONObject(i).getString("hariKronis").equalsIgnoreCase("") ? Integer.valueOf(jsonArray.getJSONObject(i).getString("hariKronis")) : null);
                      if ("Y".equalsIgnoreCase(jsonArray.getJSONObject(i).getString("flagRacik"))) {
                          transaksiObatDetail.setFlagRacik(jsonArray.getJSONObject(i).getString("flagRacik"));
                          transaksiObatDetail.setNamaRacik(jsonArray.getJSONObject(i).getString("namaRacik"));
                          transaksiObatDetail.setIdRacik(jsonArray.getJSONObject(i).getString("idRacik"));
                      } else {
                          transaksiObatDetail.setFlagRacik("");
                      }

                      List<ItSimrsKeteranganResepEntity> resepEntityList = new ArrayList<>();
                      JSONArray jsonKet = jsonArray.getJSONObject(i).getJSONArray("keteranganDetail");
                      for (int j = 0; j < jsonKet.size(); j++) {
                          JSONObject obj = jsonKet.getJSONObject(j);
                          ItSimrsKeteranganResepEntity resepEntity = new ItSimrsKeteranganResepEntity();
                          resepEntity.setIdKeteranganObat(obj.getString("idWaktu"));
                          resepEntity.setKeteranganLain(obj.getString("keterangan"));
                          resepEntity.setCreatedDate(now);
                          resepEntity.setLastUpdate(now);
                          resepEntity.setCreatedWho(username);
                          resepEntity.setLastUpdateWho(username);
                          resepEntity.setAction("C");
                          resepEntity.setFlag("Y");
                          resepEntityList.add(resepEntity);
                      }

                      transaksiObatDetail.setKeteranganResepEntityList(resepEntityList);
                      list.add(transaksiObatDetail);
                  }

                } catch (JSONException e) {
                    logger.error("[CheckupController.create] Error, get json resep " + e.getMessage());
                }
            }

            try {
                permintaanResepBoProxy.saveAdd(bean, list);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli group",e);
            }
        } else if (action.equalsIgnoreCase("getPermintaanResep")){
            List<PermintaanResep> result = new ArrayList<>();

            PermintaanResep bean = new PermintaanResep();
            bean.setIdDetailCheckup(idDetailCheckup);

            try{
                result = permintaanResepBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e ){
                logger.error("CheckupController.create] Error when get permintaan resep",e);

            }

            if (result.size() > 0 && result != null){
                for (PermintaanResep item : result){
                    PermintaanResepMobile permintaanResepMobile = new PermintaanResepMobile();
                    permintaanResepMobile.setIdPermintaanResep(item.getIdPermintaanResep());
                    permintaanResepMobile.setIdPasien(item.getIdPasien());
                    permintaanResepMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    permintaanResepMobile.setIdApprovalObat(item.getIdApprovalObat());
                    permintaanResepMobile.setKeterangan(item.getKeterangan());
                    permintaanResepMobile.setIdDokter(item.getIdDokter());
                    permintaanResepMobile.setTujuanPelayanan(item.getTujuanPelayanan());
                    permintaanResepMobile.setCreatedDate(CommonUtil.convertDateToString(item.getCreatedDate()));
                    permintaanResepMobile.setLastUpdate(CommonUtil.convertDateToString(item.getLastUpdate()));

                    listOfPermintaanResep.add(permintaanResepMobile);
                }
            }
        } else if (action.equalsIgnoreCase("saveKeteranganRawatJalan")){
            CrudResponse response = new CrudResponse();

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setStatusPeriksa("3");
            headerDetailCheckup.setFlag("Y");
            headerDetailCheckup.setAction("U");
            headerDetailCheckup.setAction("U");

            if (tglCheckup != null && !tglCheckup.equalsIgnoreCase("")){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                try{
                    Date date = format.parse(tglCheckup);
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    headerDetailCheckup.setTglCekup(sqlDate);
                } catch (ParseException e){
                    logger.error("[CheckupController.saveKeterangan] Date Parse Error, ", e);
                }
            }

            headerDetailCheckup.setKeteranganCekupUlang(ketCheckup);

            if ("selesai".equalsIgnoreCase(idKtg)){
                headerDetailCheckup.setKeteranganSelesai(ket);
                headerDetailCheckup.setCaraPasienPulang(caraPulang);
                headerDetailCheckup.setPendamping(pendamping);
                headerDetailCheckup.setTempatTujuan(tujuan);
                headerDetailCheckup.setKeteranganCekupUlang(ketCheckup);
                headerDetailCheckup.setStatus(idKtg);
                cekRawatInap(idDetailCheckup);

            }
            if ("pindah".equalsIgnoreCase(idKtg)){
                headerDetailCheckup.setKeteranganSelesai("Pindah ke Poli Lain");
                if  (jenisPasien.equalsIgnoreCase("umum")){
                    headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                }
            }
            if ("rujuk".equalsIgnoreCase(idKtg)){
                headerDetailCheckup.setIdJenisPeriksaPasien(jenisPasien);
                headerDetailCheckup.setKeteranganSelesai("Rujuk Rawat Inap");
            }
            saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien, username);

            // create jurnal if non tunai
            if ("non_tunai".equalsIgnoreCase(jenisBayar)) {
                JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, idPoli, idPasien);
                if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
                    response.setMsg(jurnalResponse.getMsg());
                    crudResponse = response;
                } else {
                    headerDetailCheckup.setInvoice(jurnalResponse.getInvoice());
                }
            }

            headerDetailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            headerDetailCheckup.setLastUpdateWho(username);

            try {
                checkupDetailBoProxy.saveEdit(headerDetailCheckup);
                response.setStatus("success");
                response.setMsg("Berhasil Menyimpan transaksi");
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, ", e);
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, "+ e);
                crudResponse = response;
            }

            if ("pindah".equalsIgnoreCase(idKtg)) {
                pindahPoli(noCheckup, idDetailCheckup, idPoli, idDokter);
            }

            updateFlagPeriksaAntrianOnline(idDetailCheckup);

            logger.info("[CheckupDetailAction.saveKeterangan] end process >>>");
            crudResponse =response;

        } else if (action.equalsIgnoreCase("getDokterByIdPelayanan")){
            List<Dokter> result = new ArrayList<>();

            try{
                result = dokterBoProxy.getByIdPelayanan(idPelayanan, branchId);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get dokter by id pelayanan",e);
            }

            if (result != null && result.size() > 0) {
                for (Dokter item : result){
                    DokterMobile dokterMobile = new DokterMobile();
                    dokterMobile.setIdDokter(item.getIdDokter());
                    dokterMobile.setNamaDokter(item.getNamaDokter());
                    dokterMobile.setNamaSpesialis(item.getNamaSpesialis());
                    dokterMobile.setIdSpesialis(item.getIdSpesialis());

                    listOfDokter.add(dokterMobile);
                }
            }
        } else if (action.equalsIgnoreCase("editFlagCall")) {
            try {
                checkupDetailBoProxy.editFlagCall(idDetailCheckup, flagCall);
            } catch (GeneralBOException e) {
                logger.error("[DokterController.create] Error, " + e.getMessage());
            }
        } else if (action.equalsIgnoreCase("getObatPoliGroupSerupa")) {
            List<ObatPoli> result = new ArrayList<>();

            try{
                result = obatPoliBoProxy.getListObatGroupPoliSerupa(idPelayanan, branchId, jenisPasien, idObat);
            } catch (GeneralBOException e){
                logger.error("CheckupController.create] Error when get obat poli group",e);
            }

            if (result != null && result.size() > 0) {
                for (ObatPoli item : result){
                    ObatPoliMobile obatPoliMobile = new ObatPoliMobile();
                    obatPoliMobile.setIdObat(item.getIdObat());
                    obatPoliMobile.setNamaObat(item.getNamaObat());
                    obatPoliMobile.setLembarPerBox(item.getLembarPerBox() != null ? item.getLembarPerBox().toString() : "");
                    obatPoliMobile.setBijiPerLembar(item.getBijiPerLembar() != null ? item.getBijiPerLembar().toString() : "");
                    obatPoliMobile.setFlagKronis(item.getFlagKronis());
                    obatPoliMobile.setQtyBox(item.getQtyBox() != null ? item.getQtyBox().toString() : "");
                    obatPoliMobile.setQtyLembar(item.getQtyLembar() != null ? item.getQtyLembar().toString() : "");
                    obatPoliMobile.setQtyBiji(item.getQtyBiji() != null ? item.getQtyLembar().toString() : "");
                    obatPoliMobile.setIdJenisObat(item.getIdJenisObat());

                    listOfObatPoli.add(obatPoliMobile);
                }
            }
        } else if(action.equalsIgnoreCase("getHistoryPasien")) {
            List<HeaderCheckup> result = new ArrayList<>();

            try {
               result = checkupBoProxy.getHistoryPasien(idPasien, branchId);
            } catch (GeneralBOException e) {
                logger.error("[CheckupController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (HeaderCheckup item : result){
                    HeaderDetailCheckupMobile headerCheckupMobile = new HeaderDetailCheckupMobile();
                    headerCheckupMobile.setIdRiwayatTindakan(item.getIdRiwayatTindakan());
                    headerCheckupMobile.setNoCheckup(item.getNoCheckup());
                    headerCheckupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    headerCheckupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    headerCheckupMobile.setKeteranganKeluar(item.getKeteranganKeluar());
                    headerCheckupMobile.setTglTindakan(item.getTglTindakan());
                    headerCheckupMobile.setNamaTindakan(item.getNamaTindakan());
                    headerCheckupMobile.setKeterangan(item.getKeterangan());
                    headerCheckupMobile.setVideoRm(item.getVideoRm());
                    headerCheckupMobile.setIdTindakan(item.getIdTindakan());

                    listOfHeaderCheckup.add(headerCheckupMobile);
                }
            }
        } else if(action.equalsIgnoreCase("getDetailHistoryPasien")) {
            List<HeaderCheckup> result = new ArrayList<>();
            try {
               result = checkupBoProxy.getListDetailHistory(idTindakan, keterangan);
            } catch (GeneralBOException e){
                logger.error("[CheckupController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (HeaderCheckup item : result){
                    HeaderDetailCheckupMobile headerDetailCheckupMobile = new HeaderDetailCheckupMobile();
                    headerDetailCheckupMobile.setIdDetailTindakan(item.getIdDetailTindakan());
                    headerDetailCheckupMobile.setNamaDetailLab(item.getNamaDetailLab());
                    headerDetailCheckupMobile.setSatuan(item.getSatuan());
                    headerDetailCheckupMobile.setAcuan(item.getAcuan());
                    headerDetailCheckupMobile.setKesimpulan(item.getKesimpulan());
                    headerDetailCheckupMobile.setKeterangan(item.getKeterangan());
                    headerDetailCheckupMobile.setNamaObat(item.getNamaObat());
                    headerDetailCheckupMobile.setQty(item.getQty());

                    listOfHeaderCheckup.add(headerDetailCheckupMobile);
                }
            }
        } else if(action.equalsIgnoreCase("getListAntrian")) {
            List<HeaderCheckup> result = new ArrayList<>();

            try{
               result = checkupBoProxy.getListAntrian(branchId, idPoli);
            } catch (GeneralBOException e) {
                logger.error("[CheckupController.create] Error, " + e.getMessage());
            }

            if(result.size() > 0) {
                for (HeaderCheckup item : result) {
                    CheckupMobile checkupMobile = new CheckupMobile();
                    checkupMobile.setIdPasien(item.getIdPasien());
                    checkupMobile.setNamaPasien(item.getNama());
                    checkupMobile.setDesa(item.getNamaDesa());
                    checkupMobile.setNamaPelayanan(item.getNamaPelayanan());
                    checkupMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    checkupMobile.setBelumBayarUangMuka(item.getBelumBayarUangMuka());
                    checkupMobile.setNoAntrian(item.getNoAntrian().toString());

                    listOfCheckup.add(checkupMobile);
                }


            }
        } else if(action.equalsIgnoreCase("getCountAll")){
            HeaderCheckup result = new HeaderCheckup();

            String branch = "";
            if(!branchId.equalsIgnoreCase("KP")) {
                branch = branchId;
            }

            try {
                result = initDashboardBoProxy.getCountAll(bulan, tahun, branch);
            } catch (GeneralBOException e){
                logger.error("Found Error getCountAll" + e);
            }

            model = new CheckupMobile();
            model.setJmlIGD(result.getJmlIGD());
            model.setJmlRI(result.getJmlRI());
            model.setJmlRJ(result.getJmlRJ());
            model.setJmlTelemedic(result.getJmlTelemedic());

        } else if(action.equalsIgnoreCase("getKunjunganRJ")) {

            List<HeaderCheckup> result = new ArrayList<>();

            try {
                result = initDashboardBoProxy.getKunjunganRJ(bulan, tahun, branchId, jenisKunjungan);
            } catch (GeneralBOException e){
                logger.error("Found Error getKunjunganRJ" + e);
            }

            listOfCheckup = new ArrayList<>();
            for (HeaderCheckup item : result){
                CheckupMobile checkupMobile = new CheckupMobile();
                checkupMobile.setBranchId(item.getBranchId());
                checkupMobile.setBranchName(item.getBranchName());
                checkupMobile.setTanggal(item.getTanggal() != null ? CommonUtil.convertDateToString(item.getTanggal()) : "");
                checkupMobile.setTotal(item.getTotal());

                listOfCheckup.add(checkupMobile);
            }
        } else if(action.equalsIgnoreCase("getDetailKunjunganRj")) {

            List<HeaderCheckup> result = new ArrayList<>();

            try{
              result = initDashboardBoProxy.getDetailKunjunganRJ(bulan, tahun, branchId, jenisKunjungan);
            } catch (GeneralBOException e){
                logger.error("Found Error getDetailKunjunganRJ" + e);
            }

            listOfCheckup = new ArrayList<>();
            for (HeaderCheckup item : result) {
                CheckupMobile checkupMobile = new CheckupMobile();
                checkupMobile.setBranchId(item.getBranchId());
                checkupMobile.setBranchName(item.getBranchName());
                checkupMobile.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                checkupMobile.setStatusPeriksaName(item.getStatusPeriksaName());
                checkupMobile.setTotal(item.getTotal());

                listOfCheckup.add(checkupMobile);
            }
        } else if (action.equalsIgnoreCase("getTahunPeriksa")) {
            List<HeaderCheckup> result = new ArrayList<>();

            try{
                result = initDashboardBoProxy.getTahunPeriksa();
            } catch (GeneralBOException e){
                logger.error("Found Error getDetailKunjunganRJ" + e);
            }

            listOfCheckup = new ArrayList<>();
            for (HeaderCheckup item : result) {
                CheckupMobile checkupMobile = new CheckupMobile();
                checkupMobile.setTahun(item.getTahun());

                listOfCheckup.add(checkupMobile);
            }

        } else if (action.equalsIgnoreCase("getComboBranch")) {
            List<Branch> result = new ArrayList<>();

            Branch bean = new Branch();
            if (!branchId.equalsIgnoreCase("KP")) {
                bean.setBranchId(branchId);
            }

            try {
                result = branchBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("Found Error getComboBranch" + e);
            }

            listOfCheckup = new ArrayList<>();
            for (Branch item : result) {
                if(!item.getBranchId().equalsIgnoreCase("KP")) {
                    CheckupMobile checkupMobile = new CheckupMobile();
                    checkupMobile.setBranchName(item.getBranchName());
                    checkupMobile.setBranchId(item.getBranchId());
                    checkupMobile.setWarna(item.getWarna());

                    listOfCheckup.add(checkupMobile);
                }

            }
        } else if (action.equalsIgnoreCase("getKamarTerpakai")) {
            List<HeaderCheckup> result = new ArrayList<>();

            try {
               result = initDashboardBoProxy.getKamarTerpakai(bulan, tahun, branchId);
            } catch (GeneralBOException e){
                logger.error("Found Error getKamarTerpakai" + e);
            }

            listOfCheckup = new ArrayList<>();
            for(HeaderCheckup item : result) {
                CheckupMobile checkupMobile = new CheckupMobile();
                checkupMobile.setBranchId(item.getBranchId());
                checkupMobile.setBranchName(item.getBranchName());
                checkupMobile.setTanggal(item.getTanggal() != null ? CommonUtil.convertDateToString(item.getTanggal()) : "");
                checkupMobile.setAll(item.getAll());
                checkupMobile.setTotal(item.getTotal());

                listOfCheckup.add(checkupMobile);
            }
        } else if (action.equalsIgnoreCase("getDetailKunjunganJK")) {
            List<HeaderCheckup> result = new ArrayList<>();

            try {
                result = initDashboardBoProxy.getDetailKunjunganJK(bulan, tahun, branchId, jenisKunjungan);
            } catch (GeneralBOException e){
                logger.error("Found Error getDetailKunjungan" + e);
            }

            listOfCheckup = new ArrayList<>();
            for(HeaderCheckup item : result) {
                CheckupMobile checkupMobile = new CheckupMobile();
                checkupMobile.setBranchId(item.getBranchId());
                checkupMobile.setBranchName(item.getBranchName());
                checkupMobile.setTotal(item.getTotal());
                checkupMobile.setJenisKelamin(item.getJenisKelamin());
                listOfCheckup.add(checkupMobile);
            }
        } else if (action.equalsIgnoreCase("getPaketPeriksa")) {

            List<PaketPeriksa> result = new ArrayList<>();

            try {
                result = paketPeriksaBoProxy.getListPaket(branchId, "rawat_jalan");
            } catch (GeneralBOException e) {
                logger.error("Found Error getPaketPeriksa" + e);
                throw new GeneralBOException(e.getMessage());
            }

            if(result != null) {
                for (PaketPeriksa item : result) {
                    PaketPeriksaMobile paketPeriksaMobile = new PaketPeriksaMobile();
                    paketPeriksaMobile.setIdPaket(item.getIdPaket());
                    paketPeriksaMobile.setNamaPaket(item.getNamaPaket());
                    paketPeriksaMobile.setIdPelayanan(item.getIdPelayanan());
                    paketPeriksaMobile.setNamaPelayanan(item.getNamaPelayanan());
                    paketPeriksaMobile.setTarif(item.getTarif().toString());

                    listOfPaketPeriksa.add(paketPeriksaMobile);
                }
            }
        }

        logger.info("[CheckupController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
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
        String user = username;
        String branchId = this.branchId;

        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(idPoli) && !"".equalsIgnoreCase(idDokter)) {

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdJenisPeriksaPasien(jenisPasien);
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

                    List<com.neurix.simrs.master.pasien.model.Pasien> pasienList = new ArrayList<>();
                    com.neurix.simrs.master.pasien.model.Pasien getPasien = new Pasien();
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
                headerDetailCheckup.setBranchId(branchId);
//                headerDetailCheckup.setNoNota(createJurnalUangMuka(checkup.getIdPasien(), "0"));
                if (jenisPasien.equalsIgnoreCase("umum")){
                    headerDetailCheckup.setJumlahUangMuka(new BigInteger(uangMuka));
                }

                try {
                    checkupDetailBo.saveAdd(headerDetailCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[CheckupDetailAction.pindahPoli] Error when saving add new detail poli, ", e);
                }
            }
        }
        logger.info("[CheckupDetailAction.pindahPoli] end process >>>");
    }

    public CheckResponse saveApproveAllTindakanRawatJalan(String idDetailCheckup, String jenisPasien, String username) {

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user;
            if (username != null) {
                user = username;
            } else user = CommonUtil.userLogin();

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
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien, username);
            }

        }

        logger.info("[CheckupDetailAction.saveApproveAllTindakanRawatJalan] END process >>>");

        return response;
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

    public String saveAddToRiwayatTindakan(String idDetail, String jenisPasien, String username) {
        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = username;
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
        return "success";

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
}
