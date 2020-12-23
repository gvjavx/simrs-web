package com.neurix.simrs.mobileapi;

import com.google.gson.Gson;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.PlanKegiatanRawatMobile;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.mobileapi.model.*;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.File;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author gondok
 * Tuesday, 11/02/20 9:40
 */
public class RawatInapController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(RawatInapController.class);

    private Collection<RawatInapMobile> listOfRawatInap = new ArrayList<>();
    private Collection<TindakanRawatMobile> listOfTindakanRawat = new ArrayList<>();
    private Collection<KategoriTindakanMobile> listOfKategoriTindakan = new ArrayList<>();
    private Collection<TindakanMobile> listOfTindakan = new ArrayList<>();
    private Collection<DiagnosaMobile> listOfDiagnosa = new ArrayList<>();
    private Collection<DiagnosaRawatMobile> listOfDiagnosaRawat = new ArrayList<>();
    private Collection<DokterTeamMobile> listOfDokterTeam = new ArrayList<>();
    private Collection<OrderGiziMobile> listOfOrderGizi = new ArrayList<>();
    private Collection<MonCairanMobile> listOfMonCairanMobile = new ArrayList<>();
    private Collection<ObatMobile> listOfObatParenteral = new ArrayList<>();
    private Collection<MonVitalSignMobile> listOfMonVitalSign = new ArrayList<>();
    private Collection<MonPemberianObatMobile> listOfMonPemberianObat = new ArrayList<>();
    private Collection<PlanKegiatanRawatMobile> listOfPlanKegiatanRawat = new ArrayList<>();

    private RawatInapMobile model = new RawatInapMobile();
    private CrudResponse crudResponse;

    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private TindakanRawatBo tindakanRawatBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private TeamDokterBo teamDokterBoProxy;
    private OrderGiziBo orderGiziBoProxy;
    private PlanKegiatanRawatBo planKegiatanRawatBoProxy;

    private String idPasien;
    private String namaPasien;
    private String jenisKelamin;
    private String idKelas;
    private String idRuangan;

    private String noCheckup;
    private String idTindakanRawat;
    private String idDetailCheckup;
    private String idTindakan;
    private String namaTindakan;
    private String idDokter;
    private String idPerawat;
    private String tarif;
    private String qty;

    private String action;
    private String branchId;
    private String userId;
    private String username;
    private String idPelayanan;

    private String idKategoriTindakan;
    private String idDiagnosa;

    private String idRawatInap;
    private String idOrderGizi;

    private String kategori;

    private String jsonMonCairan;
    private String jsonMonVitalSign;
    private String jsonMonPemberianObat;

    private String fileTtdName;
    private File fileTtd;

    private String tgl;

    private String idMonCairan;
    private String idMonVitalSign;
    private String idPembObat;

    private String isMobile;

    private String idJenisPeriksaPasien;
    private String tglMasuk;

    public String getTglMasuk() {
        return tglMasuk;
    }

    public void setTglMasuk(String tglMasuk) {
        this.tglMasuk = tglMasuk;
    }

    public String getIdJenisPeriksaPasien() {
        return idJenisPeriksaPasien;
    }

    public void setIdJenisPeriksaPasien(String idJenisPeriksaPasien) {
        this.idJenisPeriksaPasien = idJenisPeriksaPasien;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public Collection<PlanKegiatanRawatMobile> getListOfPlanKegiatanRawat() {
        return listOfPlanKegiatanRawat;
    }

    public void setListOfPlanKegiatanRawat(Collection<PlanKegiatanRawatMobile> listOfPlanKegiatanRawat) {
        this.listOfPlanKegiatanRawat = listOfPlanKegiatanRawat;
    }

    public String getIdMonCairan() {
        return idMonCairan;
    }

    public void setIdMonCairan(String idMonCairan) {
        this.idMonCairan = idMonCairan;
    }

    public String getIdMonVitalSign() {
        return idMonVitalSign;
    }

    public void setIdMonVitalSign(String idMonVitalSign) {
        this.idMonVitalSign = idMonVitalSign;
    }

    public String getIdPembObat() {
        return idPembObat;
    }

    public void setIdPembObat(String idPembObat) {
        this.idPembObat = idPembObat;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public PlanKegiatanRawatBo getPlanKegiatanRawatBoProxy() {
        return planKegiatanRawatBoProxy;
    }

    public void setPlanKegiatanRawatBoProxy(PlanKegiatanRawatBo planKegiatanRawatBoProxy) {
        this.planKegiatanRawatBoProxy = planKegiatanRawatBoProxy;
    }

    public String getFileTtdName() {
        return fileTtdName;
    }

    public void setFileTtdName(String fileTtdName) {
        this.fileTtdName = fileTtdName;
    }

    public File getFileTtd() {
        return fileTtd;
    }

    public void setFileTtd(File fileTtd) {
        this.fileTtd = fileTtd;
    }

    public Collection<MonPemberianObatMobile> getListOfMonPemberianObat() {
        return listOfMonPemberianObat;
    }

    public void setListOfMonPemberianObat(Collection<MonPemberianObatMobile> listOfMonPemberianObat) {
        this.listOfMonPemberianObat = listOfMonPemberianObat;
    }

    public String getJsonMonPemberianObat() {
        return jsonMonPemberianObat;
    }

    public void setJsonMonPemberianObat(String jsonMonPemberianObat) {
        this.jsonMonPemberianObat = jsonMonPemberianObat;
    }

    public Collection<ObatMobile> getListOfObatParenteral() {
        return listOfObatParenteral;
    }

    public Collection<MonVitalSignMobile> getListOfMonVitalSign() {
        return listOfMonVitalSign;
    }

    public void setListOfMonVitalSign(Collection<MonVitalSignMobile> listOfMonVitalSign) {
        this.listOfMonVitalSign = listOfMonVitalSign;
    }

    public String getJsonMonVitalSign() {
        return jsonMonVitalSign;
    }

    public void setJsonMonVitalSign(String jsonMonVitalSign) {
        this.jsonMonVitalSign = jsonMonVitalSign;
    }

    public void setListOfObatParenteral(Collection<ObatMobile> listOfObatParenteral) {
        this.listOfObatParenteral = listOfObatParenteral;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIdOrderGizi() {
        return idOrderGizi;
    }

    public void setIdOrderGizi(String idOrderGizi) {
        this.idOrderGizi = idOrderGizi;
    }

    public OrderGiziBo getOrderGiziBoProxy() {
        return orderGiziBoProxy;
    }

    public void setOrderGiziBoProxy(OrderGiziBo orderGiziBoProxy) {
        this.orderGiziBoProxy = orderGiziBoProxy;
    }

    public CrudResponse getCrudResponse() {
        return crudResponse;
    }

    public void setCrudResponse(CrudResponse crudResponse) {
        this.crudResponse = crudResponse;
    }

    public String getIdRawatInap() {
        return idRawatInap;
    }

    public void setIdRawatInap(String idRawatInap) {
        this.idRawatInap = idRawatInap;
    }

    public String getJsonMonCairan() {
        return jsonMonCairan;
    }

    public void setJsonMonCairan(String jsonMonCairan) {
        this.jsonMonCairan = jsonMonCairan;
    }

    public TeamDokterBo getTeamDokterBoProxy() {
        return teamDokterBoProxy;
    }

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
    }

    public String getIdDiagnosa() {
        return idDiagnosa;
    }

    public void setIdDiagnosa(String idDiagnosa) {
        this.idDiagnosa = idDiagnosa;
    }

    public DiagnosaRawatBo getDiagnosaRawatBoProxy() {
        return diagnosaRawatBoProxy;
    }

    public void setDiagnosaRawatBoProxy(DiagnosaRawatBo diagnosaRawatBoProxy) {
        this.diagnosaRawatBoProxy = diagnosaRawatBoProxy;
    }

    public Collection<DiagnosaMobile> getListOfDiagnosa() {
        return listOfDiagnosa;
    }

    public void setListOfDiagnosa(Collection<DiagnosaMobile> listOfDiagnosa) {
        this.listOfDiagnosa = listOfDiagnosa;
    }

    public Collection<DiagnosaRawatMobile> getListOfDiagnosaRawat() {
        return listOfDiagnosaRawat;
    }

    public void setListOfDiagnosaRawat(Collection<DiagnosaRawatMobile> listOfDiagnosaRawat) {
        this.listOfDiagnosaRawat = listOfDiagnosaRawat;
    }

    public DiagnosaBo getDiagnosaBoProxy() {
        return diagnosaBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public String getIdTindakanRawat() {
        return idTindakanRawat;
    }

    public void setIdTindakanRawat(String idTindakanRawat) {
        this.idTindakanRawat = idTindakanRawat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdTindakan() {
        return idTindakan;
    }

    public void setIdTindakan(String idTindakan) {
        this.idTindakan = idTindakan;
    }

    public String getNamaTindakan() {
        return namaTindakan;
    }

    public void setNamaTindakan(String namaTindakan) {
        this.namaTindakan = namaTindakan;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(String idDokter) {
        this.idDokter = idDokter;
    }

    public String getIdPerawat() {
        return idPerawat;
    }

    public void setIdPerawat(String idPerawat) {
        this.idPerawat = idPerawat;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getIdKategoriTindakan() {
        return idKategoriTindakan;
    }

    public void setIdKategoriTindakan(String idKategoriTindakan) {
        this.idKategoriTindakan = idKategoriTindakan;
    }

    public Collection<TindakanMobile> getListOfTindakan() {
        return listOfTindakan;
    }

    public void setListOfTindakan(Collection<TindakanMobile> listOfTindakan) {
        this.listOfTindakan = listOfTindakan;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public Collection<KategoriTindakanMobile> getListOfKategoriTindakan() {
        return listOfKategoriTindakan;
    }

    public void setListOfKategoriTindakan(Collection<KategoriTindakanMobile> listOfKategoriTindakan) {
        this.listOfKategoriTindakan = listOfKategoriTindakan;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public Collection<TindakanRawatMobile> getListOfTindakanRawat() {
        return listOfTindakanRawat;
    }

    public void setListOfTindakanRawat(Collection<TindakanRawatMobile> listOfTindakanRawat) {
        this.listOfTindakanRawat = listOfTindakanRawat;
    }

    public TindakanRawatBo getTindakanRawatBoProxy() {
        return tindakanRawatBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public JenisPriksaPasienBo getJenisPriksaPasienBoProxy() {
        return jenisPriksaPasienBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
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

    public String getNoCheckup() {
        return noCheckup;
    }

    public void setNoCheckup(String noCheckup) {
        this.noCheckup = noCheckup;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getIdPelayanan() {
        return idPelayanan;
    }

    public void setIdPelayanan(String idPelayanan) {
        this.idPelayanan = idPelayanan;
    }

    public Collection<RawatInapMobile> getListOfRawatInap() {
        return listOfRawatInap;
    }

    public void setListOfRawatInap(Collection<RawatInapMobile> listOfRawatInap) {
        this.listOfRawatInap = listOfRawatInap;
    }

    public void setModel(RawatInapMobile model) {
        this.model = model;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getIdRuangan() {
        return idRuangan;
    }

    public void setIdRuangan(String idRuangan) {
        this.idRuangan = idRuangan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    public Collection<DokterTeamMobile> getListOfDokterTeam() {
        return listOfDokterTeam;
    }

    public void setListOfDokterTeam(Collection<DokterTeamMobile> listOfDokterTeam) {
        this.listOfDokterTeam = listOfDokterTeam;
    }

    public Collection<OrderGiziMobile> getListOfOrderGizi() {
        return listOfOrderGizi;
    }

    public void setListOfOrderGizi(Collection<OrderGiziMobile> listOfOrderGizi) {
        this.listOfOrderGizi = listOfOrderGizi;
    }

    public Collection<MonCairanMobile> getListOfMonCairanMobile() {
        return listOfMonCairanMobile;
    }

    public void setListOfMonCairanMobile(Collection<MonCairanMobile> listOfMonCairanMobile) {
        this.listOfMonCairanMobile = listOfMonCairanMobile;
    }

    @Override
    public Object getModel() {
        switch (action){
            case "getSearchRawatInap":
                return listOfRawatInap;
            case "getTindakanRawat":
                return listOfTindakanRawat;
            case "getDiagnosaRawat":
                return listOfDiagnosaRawat;
            case "getKategoriTindakan":
                return listOfKategoriTindakan;
            case "getTindakan":
                return listOfTindakan;
            case "getDokterTeam":
                return listOfDokterTeam;
            case "getOrderGizi":
                return listOfOrderGizi;
            case "getMonCairan":
                return listOfMonCairanMobile;
            case "getListObatParenteral":
                return listOfObatParenteral;
            case "getListObatNonParenteral":
                return  listOfObatParenteral;
            case "getMonVitalSign":
                return listOfMonVitalSign;
            case "getListPemberianObat":
                return listOfMonPemberianObat;
            case "getListGraf":
                return listOfMonVitalSign;
            case "getPlanKegiatanRawat":
                return  listOfPlanKegiatanRawat;
            default: return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[RawatInapontoller.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        MonCairanMobile addMonCairan = new MonCairanMobile();
        MonVitalSignMobile addMonVitalSign = new MonVitalSignMobile();
        MonPemberianObatMobile addMonPemberianObat = new MonPemberianObatMobile();

        if (jsonMonCairan != null && !jsonMonCairan.isEmpty()){
            Gson g = new Gson();
            addMonCairan = g.fromJson(jsonMonCairan, MonCairanMobile.class);
        }

        if (jsonMonVitalSign != null && !jsonMonVitalSign.isEmpty()){
            Gson g = new Gson();
            addMonVitalSign = g.fromJson(jsonMonVitalSign, MonVitalSignMobile.class);
        }

        if (jsonMonPemberianObat != null && !jsonMonPemberianObat.isEmpty()){
            Gson g = new Gson();
            addMonPemberianObat = g.fromJson(jsonMonPemberianObat, MonPemberianObatMobile.class);
        }

        if (action.equalsIgnoreCase("getSearchRawatInap")){

            List<RawatInap> result = new ArrayList<>();

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdPasien(idPasien);
            rawatInap.setNamaPasien(namaPasien);
            rawatInap.setJenisKelamin(jenisKelamin);
            rawatInap.setIdKelas(idKelas);
            rawatInap.setIdRuangan(idRuangan);
            rawatInap.setIdDetailCheckup(idDetailCheckup);
            rawatInap.setStatus("1");

            try {
                result = rawatInapBoProxy.getSearchRawatInap(rawatInap);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (RawatInap item : result){
                    RawatInapMobile rawatInapMobile = new RawatInapMobile();
                    rawatInapMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    rawatInapMobile.setNoCheckup(item.getNoCheckup());
                    rawatInapMobile.setIdPasien(item.getIdPasien());
                    rawatInapMobile.setNamaPasien(item.getNamaPasien());
                    rawatInapMobile.setDesaId(item.getDesaId());
                    rawatInapMobile.setStatusPeriksa(item.getStatusPeriksa());
                    rawatInapMobile.setStatusPeriksaName(item.getStatusPeriksaName());
                    rawatInapMobile.setKeteranganSelesai(item.getKeteranganSelesai());
                    rawatInapMobile.setIdRawatInap(item.getIdRawatInap());
                    rawatInapMobile.setIdRuangan(item.getIdRuangan());
                    rawatInapMobile.setNoRuangan(item.getNoRuangan());
                    rawatInapMobile.setKelasRuanganName(item.getKelasRuanganName());
                    rawatInapMobile.setIdKelas(item.getIdKelas());
                    rawatInapMobile.setAlamat(item.getAlamat());
                    rawatInapMobile.setNamaRangan(item.getNamaRangan());
                    rawatInapMobile.setNoSep(item.getNoSep());
                    rawatInapMobile.setIdJenisPeriksa(item.getIdJenisPeriksa());

                    listOfRawatInap.add(rawatInapMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getRawatInapDetail")){
            List<RawatInap> result = new ArrayList<>();
            List<HeaderCheckup> headerCheckups = new ArrayList<>();
            List<JenisPriksaPasien> jenisPriksaPasiens = new ArrayList<>();

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(idDetailCheckup);

            try {
               result = rawatInapBoProxy.getSearchRawatInap(rawatInap);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setNoCheckup(result.get(0).getNoCheckup());

            try{
                headerCheckups = checkupBoProxy.getByCriteria(headerCheckup);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            model.setIdDetailCheckup(result.get(0).getIdDetailCheckup());
            model.setNoCheckup(result.get(0).getNoCheckup());
            model.setIdPasien(result.get(0).getIdPasien());
            model.setNamaPasien(result.get(0).getNamaPasien());
            model.setDesaId(result.get(0).getDesaId());
            model.setStatusPeriksa(result.get(0).getStatusPeriksa());
            model.setStatusPeriksaName(result.get(0).getStatusPeriksaName());
            model.setKeteranganSelesai(result.get(0).getKeteranganSelesai());
            model.setIdRawatInap(result.get(0).getIdRawatInap());
            model.setIdRuangan(result.get(0).getIdRuangan());
            model.setNoRuangan(result.get(0).getNoRuangan());
            model.setKelasRuanganName(result.get(0).getKelasRuanganName());
            model.setIdKelas(result.get(0).getIdKelas());
            model.setAlamat(result.get(0).getAlamat());
            model.setNamaRangan(result.get(0).getNamaRangan());
            model.setKecamatan(headerCheckups.get(0).getNamaKecamatan());
            model.setKota(headerCheckups.get(0).getNamaKota());
            model.setProvinsi(headerCheckups.get(0).getNamaProvinsi());
            model.setIdPelayanan(headerCheckups.get(0).getIdPelayanan());
            model.setNamaPelayanan(headerCheckups.get(0).getNamaPelayanan());
            model.setTempatLahir(headerCheckups.get(0).getTempatLahir());
            model.setTglLahir(headerCheckups.get(0).getTglLahir() != null ? headerCheckups.get(0).getTglLahir().toString() : null);
            String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckups.get(0).getTglLahir());
            model.setTempatTglLahir(headerCheckups.get(0).getTempatLahir()+", "+formatDate);
            model.setIdJenisPeriksa(result.get(0).getIdJenisPeriksa());
            model.setNik(headerCheckups.get(0).getNoKtp());
            model.setUrlKtp(headerCheckups.get(0).getUrlKtp());
            model.setNoSep(result.get(0).getNoSep());

            if (headerCheckups.get(0).getJenisKelamin() != null){
                switch (headerCheckups.get(0).getJenisKelamin()){
                    case "p":
                        model.setJenisKelamin("Perempuan");
                        break;
                    case "l":
                        model.setJenisKelamin("Laki-Laki");
                        break;
                }
            }

            JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
            jenisPriksaPasien.setIdJenisPeriksaPasien(result.get(0).getIdJenisPeriksa());

            try {
                 jenisPriksaPasiens = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            model.setJenisPeriksaPasien(jenisPriksaPasiens.get(0).getKeterangan());

        }

        if (action.equalsIgnoreCase("getTindakanRawat")){
            List<TindakanRawat> result = new ArrayList<>();

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);

            try {
               result = tindakanRawatBoProxy.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            for (TindakanRawat item : result) {
                TindakanRawatMobile tindakanRawatMobile = new TindakanRawatMobile();
                tindakanRawatMobile.setAction(item.getAction());
                tindakanRawatMobile.setCreatedDate(item.getStCreatedDate());
                tindakanRawatMobile.setCreatedWho(item.getCreatedWho());
                tindakanRawatMobile.setFlag(item.getFlag());
                tindakanRawatMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                tindakanRawatMobile.setIdDokter(item.getIdDokter());
                tindakanRawatMobile.setIdKategoriTindakan(item.getIdKategoriTindakan());
                tindakanRawatMobile.setIdPerawat(item.getIdPerawat());
                tindakanRawatMobile.setIdTindakanRawat(item.getIdTindakanRawat());
                tindakanRawatMobile.setIdTindakan(item.getIdTindakan());
                tindakanRawatMobile.setNamaDokter(item.getNamaDokter());
                tindakanRawatMobile.setNamaPerawat(item.getNamaPerawat());
                tindakanRawatMobile.setNamaTindakan(item.getNamaTindakan());
                tindakanRawatMobile.setQty(item.getQty().toString());
                tindakanRawatMobile.setTarifTotal(item.getTarifTotal().toString());
                tindakanRawatMobile.setTarif(item.getTarif().toString());
                tindakanRawatMobile.setLastUpdate(item.getLastUpdate().toLocaleString());

                listOfTindakanRawat.add(tindakanRawatMobile);
            }
        }

        if (action.equalsIgnoreCase("getDiagnosaRawat")){
            List<DiagnosaRawat> result = new ArrayList<>();

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDetailCheckup(idDetailCheckup);

            try {
                result = diagnosaRawatBoProxy.getByCriteria(diagnosaRawat);
            } catch (GeneralBOException e ){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (DiagnosaRawat item : result){
                    DiagnosaRawatMobile diagnosaRawatMobile = new DiagnosaRawatMobile();
                    diagnosaRawatMobile.setIdDiagnosaRawat(item.getIdDiagnosaRawat());
                    diagnosaRawatMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    diagnosaRawatMobile.setIdDiagnosa(item.getIdDiagnosa());
                    diagnosaRawatMobile.setJenisDiagnosa(item.getJenisDiagnosa());
                    diagnosaRawatMobile.setKeteranganDiagnosa(item.getKeteranganDiagnosa());
                    diagnosaRawatMobile.setFlag(item.getFlag());
                    diagnosaRawatMobile.setAction(item.getAction());
                    diagnosaRawatMobile.setCreatedDate(item.getStCreatedDate());
                    diagnosaRawatMobile.setCreatedWho(item.getCreatedWho());
                    diagnosaRawatMobile.setLastUpdate(item.getStLastUpdate());
                    diagnosaRawatMobile.setLastUpdateWho(item.getLastUpdateWho());

                    listOfDiagnosaRawat.add(diagnosaRawatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getDiagnosa")){
            List<Diagnosa> result = new ArrayList<>();

            Diagnosa diagnosa = new Diagnosa();
            diagnosa.setIdDiagnosa(idDiagnosa);

            try {
                result = diagnosaBoProxy.getByCriteria(diagnosa);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (Diagnosa item : result){
                    DiagnosaMobile diagnosaMobile = new DiagnosaMobile();
                    diagnosaMobile.setIdDiagnosa(item.getIdDiagnosa());
                    diagnosaMobile.setDescOfDiagnosa(item.getDescOfDiagnosa());
                }
            }
        }

        if (action.equalsIgnoreCase("getKategoriTindakan")){
            List<KategoriTindakan> result = new ArrayList<>();

            try {
                result = kategoriTindakanBoProxy.getListKategoriTindakan(idPelayanan, null, branchId);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (KategoriTindakan item : result){
                    KategoriTindakanMobile kategoriTindakanMobile = new KategoriTindakanMobile();
                    kategoriTindakanMobile.setIdKategoriTindakan(item.getIdKategoriTindakan());
                    kategoriTindakanMobile.setKategoriTindakan(item.getKategoriTindakan());

                    listOfKategoriTindakan.add(kategoriTindakanMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getTindakan")){
            List<Tindakan> result = new ArrayList<>();

            Tindakan tindakan = new Tindakan();
            tindakan.setIdKategoriTindakan(idKategoriTindakan);

            try {
               result = tindakanBoProxy.getComboBoxTindakan(tindakan);
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (Tindakan item : result){
                    TindakanMobile tindakanMobile = new TindakanMobile();
                    tindakanMobile.setIdKategoriTindakan(item.getIdKategoriTindakan());
                    tindakanMobile.setIdTindakan(item.getIdTindakan());
                    tindakanMobile.setTindakan(item.getTindakan());
                    tindakan.setTindakan(item.getTindakan());

                    listOfTindakan.add(tindakanMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getDokterTeam")){
            List<DokterTeam> result = new ArrayList<>();

            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(idDetailCheckup);

            try {
                result = teamDokterBoProxy.getByCriteria(dokterTeam);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (DokterTeam item : result) {
                    DokterTeamMobile dokterTeamMobile = new DokterTeamMobile();
                    dokterTeamMobile.setIdDokter(item.getIdDokter());
                    dokterTeamMobile.setNamaDokter(item.getNamaDokter());
                    dokterTeamMobile.setIdDetailCheckup(item.getIdDetailCheckup());

                    listOfDokterTeam.add(dokterTeamMobile);

                }

            }
        }

        if (action.equalsIgnoreCase("getPlanKegiatanRawat")) {
            List<PlanKegiatanRawat> result = new ArrayList<>();

            PlanKegiatanRawat planKegiatanRawat = new PlanKegiatanRawat();
            planKegiatanRawat.setIdDetailCheckup(idDetailCheckup);
            planKegiatanRawat.setTglMulai(new Date(now.getTime()));

            try {
                result = planKegiatanRawatBoProxy.getSearchByCritria(planKegiatanRawat);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0) {
                for (PlanKegiatanRawat item : result) {
                    PlanKegiatanRawatMobile planKegiatanRawatMobile = new PlanKegiatanRawatMobile();
                    planKegiatanRawatMobile.setId(item.getId());
                    planKegiatanRawatMobile.setIdKategori(item.getIdKategori());
                    planKegiatanRawatMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    planKegiatanRawatMobile.setJamMulai(item.getJamMulai());
                    planKegiatanRawatMobile.setJamSelesai(item.getJamSelesai());
                    planKegiatanRawatMobile.setTglMulai(item.getTglMulai().toString());
                    planKegiatanRawatMobile.setTglSelesai(item.getTglSelesai().toString());
                    planKegiatanRawatMobile.setBranchId(item.getBranchId());
                    planKegiatanRawatMobile.setWaktu(item.getWaktu());
                    planKegiatanRawatMobile.setJenisKegiatan(item.getJenisKegiatan());
                    planKegiatanRawatMobile.setKeterangan(item.getKeterangan());
                    planKegiatanRawatMobile.setFlagDikerjakan(item.getFlagDikerjakan());
                    planKegiatanRawatMobile.setFlag(item.getFlag());
                    planKegiatanRawatMobile.setAction(item.getAction());
                    planKegiatanRawatMobile.setCreatedDate(item.getStCreatedDate());
                    planKegiatanRawatMobile.setLastUpdate(item.getStLastUpdate());
                    planKegiatanRawatMobile.setLastUpdateWho(item.getLastUpdateWho());
                    planKegiatanRawatMobile.setStTglMulai(item.getStTglMulai());

                    listOfPlanKegiatanRawat.add(planKegiatanRawatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getOrderGizi")){
            List<OrderGizi> result = new ArrayList<>();

            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdRawatInap(idRawatInap);

            try {
                result = orderGiziBoProxy.getByCriteria(orderGizi);
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (OrderGizi item : result){
                    OrderGiziMobile orderGiziMobile = new OrderGiziMobile();
                    orderGiziMobile.setIdOrderGizi(item.getIdOrderGizi());
                    orderGiziMobile.setDietPagi(item.getDietPagi());
                    orderGiziMobile.setBentukMakanPagi(item.getBentukMakanPagi());
                    orderGiziMobile.setDietSiang(item.getDietSiang());
                    orderGiziMobile.setBentukMakanSiang(item.getBentukMakanSiang());
                    orderGiziMobile.setDietMalam(item.getDietMalam());
                    orderGiziMobile.setBentukMakanMalam(item.getBentukMakanMalam());
                    orderGiziMobile.setIdOrderGizi(item.getIdOrderGizi());
                    orderGiziMobile.setIdRawatInap(item.getIdRawatInap());
                    orderGiziMobile.setApproveFlag(item.getApproveFlag());
                    orderGiziMobile.setDiterimaFlag(item.getDiterimaFlag());
                    orderGiziMobile.setBentukDiet(item.getBentukDiet());
                    orderGiziMobile.setIdDietGizi(item.getIdDietGizi());
                    orderGiziMobile.setKeterangan(item.getKeterangan());

                    if (item.getTglOrder() != null) {
                        orderGiziMobile.setTglOrder(item.getTglOrder().toString());
                    }

                    listOfOrderGizi.add(orderGiziMobile);
                }
            }

        }

        if  (action.equalsIgnoreCase("updateDiterimaFlagGizi")){
            CheckResponse checkResponse;

            OrderGizi orderGizi = new OrderGizi();
            orderGizi.setIdOrderGizi(idOrderGizi);

            try {
                checkResponse = orderGiziBoProxy.updateDiterimaFLag(orderGizi);
                if (checkResponse.getStatus().equalsIgnoreCase("Success")){
                    model.setMessage("Success");
                }
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if(action.equalsIgnoreCase("getMonVitalSign")){
            List<MonVitalSign> result = new ArrayList<>();

            MonVitalSign monVitalSign = new MonVitalSign();
            monVitalSign.setIdDetailCheckup(idDetailCheckup);
            monVitalSign.setId(idMonVitalSign);
            monVitalSign.setIsMobile(isMobile);

            try {
                result = rawatInapBoProxy.getListMonVitalSign(monVitalSign);
            }catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (MonVitalSign item : result){
                    MonVitalSignMobile monVitalSignMobile = new MonVitalSignMobile();
                    monVitalSignMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    monVitalSignMobile.setId(item.getId());
                    monVitalSignMobile.setJam(item.getJam().toString());
                    if  (item.getNadi() != null) {
                        monVitalSignMobile.setNadi(item.getNadi().toString());
                    }
                    if (item.getNafas() != null) {
                        monVitalSignMobile.setNafas(item.getNafas().toString());
                    }
                    if (item.getBb() != null) {
                        monVitalSignMobile.setBb(item.getBb().toString());
                    }
                    if (item.getTb() != null) {
                        monVitalSignMobile.setTb(item.getTb().toString());
                    }
                    if (item.getTensi() != null) {
                        monVitalSignMobile.setTensi(item.getTensi().toString());
                    }
                    if (item.getStDate() != null) {
                        monVitalSignMobile.setStDate(item.getStDate());
                    }
                    if (item.getSuhu() != null) {
                        monVitalSignMobile.setSuhu(item.getSuhu().toString());
                    }
                    monVitalSignMobile.setCreatedWho(item.getCreatedWho());

                    listOfMonVitalSign.add(monVitalSignMobile);

                }
            }
        }

        if (action.equalsIgnoreCase("getListObatParenteral")){
            List<Obat> result = new ArrayList<>();

            try{
                result = rawatInapBoProxy.getListObatParenteral(idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (Obat item : result){
                    ObatMobile obatMobile = new ObatMobile();
                    obatMobile.setNamaObat(item.getNamaObat());
                    obatMobile.setIdObat(item.getIdObat());

                    listOfObatParenteral.add(obatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getListObatNonParenteral")){
            List<Obat> result = new ArrayList<>();

            try{
                result = rawatInapBoProxy.getListObatNonParenteral(idDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (Obat item : result){
                    ObatMobile obatMobile = new ObatMobile();
                    obatMobile.setNamaObat(item.getNamaObat());
                    obatMobile.setIdObat(item.getIdObat());

                    listOfObatParenteral.add(obatMobile);
                }
            }
        }

        if  (action.equalsIgnoreCase("getListPemberianObat")){
            List<MonPemberianObat> result = new ArrayList<>();

            MonPemberianObat monPemberianObat = new MonPemberianObat();
            monPemberianObat.setIdDetailCheckup(idDetailCheckup);
            monPemberianObat.setKategori(kategori);
            monPemberianObat.setId(idPembObat);
            monPemberianObat.setIsMobile(isMobile);

            try {
                result = rawatInapBoProxy.getListPemberianObat(monPemberianObat);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (MonPemberianObat item : result){
                    MonPemberianObatMobile monPemberianObatMobile = new MonPemberianObatMobile();
                    monPemberianObatMobile.setId(item.getId());
                    monPemberianObatMobile.setNoCheckup(item.getNoCheckup());
                    monPemberianObatMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    monPemberianObatMobile.setNamaObat(item.getNamaObat());
                    monPemberianObatMobile.setCaraPemberian(item.getCaraPemberian());
                    monPemberianObatMobile.setDosis(item.getDosis());
                    monPemberianObatMobile.setSkinTes(item.getSkinTes());
                    monPemberianObatMobile.setWaktu(item.getWaktu());
                    monPemberianObatMobile.setKeterangan(item.getKeterangan());
                    monPemberianObatMobile.setFlag(item.getFlag());
                    monPemberianObatMobile.setAction(item.getAction());
                    monPemberianObatMobile.setStDate(item.getStDate());
                    monPemberianObatMobile.setCreatedWho(item.getCreatedWho());
                    monPemberianObatMobile.setLastUpdateWho(item.getLastUpdateWho());
                    monPemberianObatMobile.setKategori(item.getKategori());

                    listOfMonPemberianObat.add(monPemberianObatMobile);
                }
            }
        }

        if (action.equalsIgnoreCase("getMonCairan")){
            List<MonCairan> result = new ArrayList<>();

            MonCairan monCairan = new MonCairan();
            monCairan.setIdDetailCheckup(idDetailCheckup);
            monCairan.setId(idMonCairan);
            monCairan.setIsMobile(isMobile);

            try {
                result = rawatInapBoProxy.getListMonCairan(monCairan);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (MonCairan item : result){
                    MonCairanMobile monCairanMobile = new MonCairanMobile();
                    monCairanMobile.setIdDetailCheckup(item.getIdDetailCheckup());
                    monCairanMobile.setId(item.getId());
                    monCairanMobile.setBalanceCairan(item.getBalanceCairan());
                    monCairanMobile.setCekTambahanObat(item.getCekTambahanObat());
                    monCairanMobile.setDari(item.getDari());
                    monCairanMobile.setFlag(item.getFlag());
                    monCairanMobile.setJamMulai(item.getJamMulai());
                    monCairanMobile.setJamSelesai(item.getJamSelesai());
                    monCairanMobile.setJamUkurBuang(item.getJamUkurBuang());
                    monCairanMobile.setJumlah(item.getJumlah());
                    monCairanMobile.setKeterangan(item.getKeterangan());
                    monCairanMobile.setMacamCairan(item.getMacamCairan());
                    monCairanMobile.setMelalui(item.getMelalui());
                    monCairanMobile.setSisa(item.getSisa());
                    monCairanMobile.setStDate(item.getStDate());
                    monCairanMobile.setNoCheckup(item.getNoCheckup());
                    monCairanMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    monCairanMobile.setCreatedWho(item.getCreatedWho());
                    monCairanMobile.setLastUpdate(item.getLastUpdate().toLocaleString());
                    monCairanMobile.setLastUpdateWho(item.getLastUpdateWho());

                    listOfMonCairanMobile.add(monCairanMobile);
                }
            }
        }

        if  (action.equalsIgnoreCase("getListGraf")){
            List<MonVitalSign> result = new ArrayList<>();

            MonVitalSign monVitalSign = new MonVitalSign();
            monVitalSign.setIdDetailCheckup(idDetailCheckup);

            try {
                result = rawatInapBoProxy.getListGraf(monVitalSign);
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (MonVitalSign item : result){
                    MonVitalSignMobile monVitalSignMobile = new MonVitalSignMobile();
                    monVitalSignMobile.setCreatedDate(item.getCreatedDate().toLocaleString());
                    monVitalSignMobile.setJam(item.getJam().toString());
                    monVitalSignMobile.setNafas(item.getNafas().toString());
                    monVitalSignMobile.setNadi(item.getNadi().toString());
                    monVitalSignMobile.setSuhu(item.getSuhu().toString());
                    monVitalSignMobile.setStDate(item.getStDate());

                    listOfMonVitalSign.add(monVitalSignMobile);
                }
            }


        }

        if (action.equalsIgnoreCase("saveMonPemberianObat")){
            ItSimrsMonPemberianObatEntity itSimrsMonPemberianObatEntity = new ItSimrsMonPemberianObatEntity();
            itSimrsMonPemberianObatEntity.setNoCheckup(addMonPemberianObat.getNoCheckup());
            itSimrsMonPemberianObatEntity.setIdDetailCheckup(addMonPemberianObat.getIdDetailCheckup());
            itSimrsMonPemberianObatEntity.setNamaObat(addMonPemberianObat.getNamaObat());
            itSimrsMonPemberianObatEntity.setCaraPemberian(addMonPemberianObat.getCaraPemberian());
            itSimrsMonPemberianObatEntity.setDosis(addMonPemberianObat.getDosis());
            itSimrsMonPemberianObatEntity.setSkinTes(addMonPemberianObat.getSkinTes());
            itSimrsMonPemberianObatEntity.setWaktu(addMonPemberianObat.getWaktu());
            itSimrsMonPemberianObatEntity.setKeterangan(addMonPemberianObat.getKeterangan());
            itSimrsMonPemberianObatEntity.setKategori(addMonPemberianObat.getKategori());

            itSimrsMonPemberianObatEntity.setFlag("Y");
            itSimrsMonPemberianObatEntity.setAction("C");
            itSimrsMonPemberianObatEntity.setCreatedDate(now);
            itSimrsMonPemberianObatEntity.setCreatedWho(addMonPemberianObat.getCreatedWho());
            itSimrsMonPemberianObatEntity.setLastUpdate(now);
            itSimrsMonPemberianObatEntity.setLastUpdateWho(addMonPemberianObat.getLastUpdateWho());

            try {
                crudResponse = rawatInapBoProxy.saveMonPemberianObat(itSimrsMonPemberianObatEntity);
                if (crudResponse.getStatus().equalsIgnoreCase("success")){
                    model.setMessage("Success");
                } else model.setMessage(crudResponse.getMsg());
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("updateMonPemberianObat")){
            ItSimrsMonPemberianObatEntity itSimrsMonPemberianObatEntity = new ItSimrsMonPemberianObatEntity();
            itSimrsMonPemberianObatEntity.setId(addMonPemberianObat.getId());
            itSimrsMonPemberianObatEntity.setNoCheckup(addMonPemberianObat.getNoCheckup());
            itSimrsMonPemberianObatEntity.setIdDetailCheckup(addMonPemberianObat.getIdDetailCheckup());
            itSimrsMonPemberianObatEntity.setNamaObat(addMonPemberianObat.getNamaObat());
            itSimrsMonPemberianObatEntity.setCaraPemberian(addMonPemberianObat.getCaraPemberian());
            itSimrsMonPemberianObatEntity.setDosis(addMonPemberianObat.getDosis());
            itSimrsMonPemberianObatEntity.setSkinTes(addMonPemberianObat.getSkinTes());
            itSimrsMonPemberianObatEntity.setWaktu(addMonPemberianObat.getWaktu());
            itSimrsMonPemberianObatEntity.setKeterangan(addMonPemberianObat.getKeterangan());
            itSimrsMonPemberianObatEntity.setKategori(addMonPemberianObat.getKategori());

            itSimrsMonPemberianObatEntity.setFlag("U");
            itSimrsMonPemberianObatEntity.setAction("C");
            itSimrsMonPemberianObatEntity.setCreatedDate(now);
            itSimrsMonPemberianObatEntity.setCreatedWho(addMonPemberianObat.getCreatedWho());
            itSimrsMonPemberianObatEntity.setLastUpdate(now);
            itSimrsMonPemberianObatEntity.setLastUpdateWho(addMonPemberianObat.getLastUpdateWho());

            try {
                rawatInapBoProxy.saveUpdateMonPemberianObat(itSimrsMonPemberianObatEntity);
                model.setMessage("Success");
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveAddMonCairan")){
            ItSimrsMonCairanEntity itSimrsMonCairanEntity = new ItSimrsMonCairanEntity();
            itSimrsMonCairanEntity.setBalanceCairan(addMonCairan.getBalanceCairan());
            itSimrsMonCairanEntity.setCekTambahanObat(addMonCairan.getCekTambahanObat());
            itSimrsMonCairanEntity.setIdDetailCheckup(addMonCairan.getIdDetailCheckup());
            itSimrsMonCairanEntity.setJamMulai(addMonCairan.getJamMulai());
            itSimrsMonCairanEntity.setJamSelesai(addMonCairan.getJamSelesai());
            itSimrsMonCairanEntity.setJamUkurBuang(addMonCairan.getJamUkurBuang());
            itSimrsMonCairanEntity.setJumlah(addMonCairan.getJumlah());
            itSimrsMonCairanEntity.setDari(addMonCairan.getDari());
            itSimrsMonCairanEntity.setKeterangan(addMonCairan.getKeterangan());
            itSimrsMonCairanEntity.setMacamCairan(addMonCairan.getMacamCairan());
            itSimrsMonCairanEntity.setMelalui(addMonCairan.getMelalui());
            itSimrsMonCairanEntity.setNoCheckup(addMonCairan.getNoCheckup());
            itSimrsMonCairanEntity.setSisa(addMonCairan.getSisa());

            itSimrsMonCairanEntity.setAction("C");
            itSimrsMonCairanEntity.setFlag("Y");
            itSimrsMonCairanEntity.setCreatedDate(now);
            itSimrsMonCairanEntity.setCreatedWho(addMonCairan.getCreatedWho());
            itSimrsMonCairanEntity.setLastUpdate(now);
            itSimrsMonCairanEntity.setLastUpdateWho(addMonCairan.getLastUpdateWho());

            try {
                crudResponse = rawatInapBoProxy.saveMonCairan(itSimrsMonCairanEntity);
                if (crudResponse.getStatus().equalsIgnoreCase("Success")){
                    model.setMessage("Success");
                } else model.setMessage(crudResponse.getMsg());
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("updateMonCairan")){
            ItSimrsMonCairanEntity itSimrsMonCairanEntity = new ItSimrsMonCairanEntity();
            itSimrsMonCairanEntity.setId(addMonCairan.getId());
            itSimrsMonCairanEntity.setBalanceCairan(addMonCairan.getBalanceCairan());
            itSimrsMonCairanEntity.setCekTambahanObat(addMonCairan.getCekTambahanObat());
            itSimrsMonCairanEntity.setIdDetailCheckup(addMonCairan.getIdDetailCheckup());
            itSimrsMonCairanEntity.setJamMulai(addMonCairan.getJamMulai());
            itSimrsMonCairanEntity.setJamSelesai(addMonCairan.getJamSelesai());
            itSimrsMonCairanEntity.setJamUkurBuang(addMonCairan.getJamUkurBuang());
            itSimrsMonCairanEntity.setJumlah(addMonCairan.getJumlah());
            itSimrsMonCairanEntity.setDari(addMonCairan.getDari());
            itSimrsMonCairanEntity.setKeterangan(addMonCairan.getKeterangan());
            itSimrsMonCairanEntity.setMacamCairan(addMonCairan.getMacamCairan());
            itSimrsMonCairanEntity.setMelalui(addMonCairan.getMelalui());
            itSimrsMonCairanEntity.setNoCheckup(addMonCairan.getNoCheckup());
            itSimrsMonCairanEntity.setSisa(addMonCairan.getSisa());

            itSimrsMonCairanEntity.setAction("U");
            itSimrsMonCairanEntity.setFlag("Y");
            itSimrsMonCairanEntity.setCreatedDate(now);
            itSimrsMonCairanEntity.setCreatedWho(addMonCairan.getCreatedWho());
            itSimrsMonCairanEntity.setLastUpdate(now);
            itSimrsMonCairanEntity.setLastUpdateWho(addMonCairan.getLastUpdateWho());

            try {
                rawatInapBoProxy.saveUpdateMonCairan(itSimrsMonCairanEntity);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if  (action.equalsIgnoreCase("saveMonVitalSign")){
            ItSimrsMonVitalSignEntity itSimrsMonVitalSignEntity = new ItSimrsMonVitalSignEntity();
            itSimrsMonVitalSignEntity.setIdDetailCheckup(addMonVitalSign.getIdDetailCheckup());
            itSimrsMonVitalSignEntity.setNoCheckup(addMonVitalSign.getNoCheckup());
            itSimrsMonVitalSignEntity.setJam(Integer.valueOf(addMonVitalSign.getJam()));
            itSimrsMonVitalSignEntity.setNadi(Integer.valueOf(addMonVitalSign.getNadi()));
            itSimrsMonVitalSignEntity.setNafas(Integer.valueOf(addMonVitalSign.getNafas()));
            itSimrsMonVitalSignEntity.setSuhu(Integer.valueOf(addMonVitalSign.getSuhu()));
            itSimrsMonVitalSignEntity.setBb(Integer.valueOf(addMonVitalSign.getBb()));
            itSimrsMonVitalSignEntity.setTb(Integer.valueOf(addMonVitalSign.getTb()));
            itSimrsMonVitalSignEntity.setTensi(Integer.valueOf(addMonVitalSign.getTensi()));

            itSimrsMonVitalSignEntity.setAction("C");
            itSimrsMonVitalSignEntity.setFlag("Y");
            itSimrsMonVitalSignEntity.setLastUpdate(now);
            itSimrsMonVitalSignEntity.setLastUpdateWho(addMonVitalSign.getLastUpdateWho());

            try {
                crudResponse = rawatInapBoProxy.saveMonVitalSign(itSimrsMonVitalSignEntity);
                if (crudResponse.getStatus().equalsIgnoreCase("Success")){
                    model.setMessage("Success");
                } else model.setMessage(crudResponse.getMsg());
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());

            }
        }

        if  (action.equalsIgnoreCase("updateMonVitalSign")){
            ItSimrsMonVitalSignEntity itSimrsMonVitalSignEntity = new ItSimrsMonVitalSignEntity();
            itSimrsMonVitalSignEntity.setId(addMonVitalSign.getId());
            itSimrsMonVitalSignEntity.setIdDetailCheckup(addMonVitalSign.getIdDetailCheckup());
            itSimrsMonVitalSignEntity.setNoCheckup(addMonVitalSign.getNoCheckup());
            itSimrsMonVitalSignEntity.setJam(Integer.valueOf(addMonVitalSign.getJam()));
            itSimrsMonVitalSignEntity.setNadi(Integer.valueOf(addMonVitalSign.getNadi()));
            itSimrsMonVitalSignEntity.setNafas(Integer.valueOf(addMonVitalSign.getNafas()));
            itSimrsMonVitalSignEntity.setSuhu(Integer.valueOf(addMonVitalSign.getSuhu()));
            itSimrsMonVitalSignEntity.setBb(Integer.valueOf(addMonVitalSign.getBb()));
            itSimrsMonVitalSignEntity.setTb(Integer.valueOf(addMonVitalSign.getTb()));
            itSimrsMonVitalSignEntity.setTensi(Integer.valueOf(addMonVitalSign.getTensi()));

            itSimrsMonVitalSignEntity.setAction("U");
            itSimrsMonVitalSignEntity.setFlag("Y");
            itSimrsMonVitalSignEntity.setLastUpdate(now);
            itSimrsMonVitalSignEntity.setLastUpdateWho(addMonVitalSign.getLastUpdateWho());

            try {
                rawatInapBoProxy.saveUpdateMonVitalSign(itSimrsMonVitalSignEntity);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());

            }
        }

        if (action.equalsIgnoreCase("saveAddTindakanRawat")){
            List<Tindakan> result = new ArrayList<>();
            Tindakan bean = new Tindakan();
            bean.setIdTindakan(idTindakan);

            try {
                result = tindakanBoProxy.getByCriteria(bean);
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);
            tindakanRawat.setNamaTindakan(namaTindakan);
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setQty(new BigInteger(qty));
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");
            tindakanRawat.setCreatedDate(now);
            tindakanRawat.setCreatedWho(username);
            tindakanRawat.setLastUpdate(now);
            tindakanRawat.setLastUpdateWho(username);

            if (idJenisPeriksaPasien.equalsIgnoreCase("bpjs") || idJenisPeriksaPasien.equalsIgnoreCase("ptpn")){
                tindakanRawat.setTarif(result.get(0).getTarifBpjs());
            } else tindakanRawat.setTarif(result.get(0).getTarif());

            try {
                tindakanRawatBoProxy.saveAdd(tindakanRawat);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        if (action.equalsIgnoreCase("saveEditTindakanRawat")){
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(idTindakanRawat);
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);
            tindakanRawat.setNamaTindakan(namaTindakan);
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setTarif(new BigInteger(tarif));
            tindakanRawat.setQty(new BigInteger(qty));
            tindakanRawat.setAction("U");
            tindakanRawat.setFlag("Y");
            tindakanRawat.setCreatedDate(now);
            tindakanRawat.setCreatedWho(username);
            tindakanRawat.setLastUpdate(now);
            tindakanRawat.setLastUpdateWho(username);

            try {
                tindakanRawatBoProxy.saveEdit(tindakanRawat);
                model.setMessage("Success");
            } catch (GeneralBOException e){
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }
        }

        logger.info("[RawatInapContoller.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
