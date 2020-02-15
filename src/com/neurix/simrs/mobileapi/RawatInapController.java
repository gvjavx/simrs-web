package com.neurix.simrs.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.mobileapi.model.*;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
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

import java.math.BigInteger;
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

    private RawatInapMobile model = new RawatInapMobile();

    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private TindakanRawatBo tindakanRawatBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private TeamDokterBo teamDokterBoProxy;

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
            default: return model;
        }
    }

    public HttpHeaders create() {
        logger.info("[RawatInapontoller.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (action.equalsIgnoreCase("getSearchRawatInap")){

            List<RawatInap> result = new ArrayList<>();

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdPasien(idPasien);
            rawatInap.setNamaPasien(namaPasien);
            rawatInap.setJenisKelamin(jenisKelamin);
            rawatInap.setIdKelas(idKelas);
            rawatInap.setIdRuangan(idRuangan);
            rawatInap.setIdDetailCheckup(idDetailCheckup);

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
            model.setIdJenisPeriksa(headerCheckups.get(0).getIdJenisPeriksaPasien());
            model.setNik(headerCheckups.get(0).getNoKtp());
            model.setUrlKtp(headerCheckups.get(0).getUrlKtp());

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
            jenisPriksaPasien.setIdJenisPeriksaPasien(headerCheckups.get(0).getIdJenisPeriksaPasien());

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

            KategoriTindakan kategoriTindakan = new KategoriTindakan();

            try {
                result = kategoriTindakanBoProxy.getByCriteria(kategoriTindakan);
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
               result = tindakanBoProxy.getByCriteria(tindakan);
            } catch (GeneralBOException e) {
                logger.error("[RawatInapController.create] Error, " + e.getMessage());
            }

            if (result.size() > 0){
                for (Tindakan item : result){
                    TindakanMobile tindakanMobile = new TindakanMobile();
                    tindakanMobile.setIdKategoriTindakan(item.getIdKategoriTindakan());
                    tindakanMobile.setIdTindakan(item.getIdTindakan());
                    tindakanMobile.setTarif(item.getTarif().toString());
                    tindakanMobile.setTarifBpjs(item.getTarifBpjs().toString());
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

        if (action.equalsIgnoreCase("getOrderGizi")){
            List<OrderGizi> result = new ArrayList<>();

            OrderGizi orderGizi = new OrderGizi();

        }


        if (action.equalsIgnoreCase("saveAddTindakanRawat")){
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);
            tindakanRawat.setNamaTindakan(namaTindakan);
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setTarif(new BigInteger(tarif));
            tindakanRawat.setQty(new BigInteger(qty));
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");
            tindakanRawat.setCreatedDate(now);
            tindakanRawat.setCreatedWho(username);
            tindakanRawat.setLastUpdate(now);
            tindakanRawat.setLastUpdateWho(username);

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
