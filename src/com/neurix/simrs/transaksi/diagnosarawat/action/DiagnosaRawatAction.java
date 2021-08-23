package com.neurix.simrs.transaksi.diagnosarawat.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
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
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.verifikator.bo.VerifikatorBo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DiagnosaRawatAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DiagnosaRawatAction.class);
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private DiagnosaRawat diagnosaRawat;
    private DiagnosaBo diagnosaBoProxy;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();

    public List<Diagnosa> getListOfComboDiagnosa() {
        return listOfComboDiagnosa;
    }

    public void setListOfComboDiagnosa(List<Diagnosa> listOfComboDiagnosa) {
        this.listOfComboDiagnosa = listOfComboDiagnosa;
    }

    public DiagnosaBo getDiagnosaBoProxy() {
        return diagnosaBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DiagnosaRawatAction.logger = logger;
    }

    public DiagnosaRawatBo getDiagnosaRawatBoProxy() {
        return diagnosaRawatBoProxy;
    }

    public void setDiagnosaRawatBoProxy(DiagnosaRawatBo diagnosaRawatBoProxy) {
        this.diagnosaRawatBoProxy = diagnosaRawatBoProxy;
    }

    public DiagnosaRawat getDiagnosaRawat() {
        return diagnosaRawat;
    }

    public void setDiagnosaRawat(DiagnosaRawat diagnosaRawat) {
        this.diagnosaRawat = diagnosaRawat;
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
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public CrudResponse saveDiagnosa(String idDetailCheckup, String idDiagnosa, String jenisDiagnosa, String ketDiagnosa, String jenisPasien) {
        logger.info("[DiagnosaRawatAction.saveDiagnosa] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
            diagnosaRawat.setIdDiagnosa(idDiagnosa);
            diagnosaRawat.setKeteranganDiagnosa(ketDiagnosa);
            diagnosaRawat.setJenisDiagnosa(jenisDiagnosa);
            diagnosaRawat.setCreatedWho(userLogin);
            diagnosaRawat.setLastUpdate(updateTime);
            diagnosaRawat.setCreatedDate(updateTime);
            diagnosaRawat.setLastUpdateWho(userLogin);
            diagnosaRawat.setAction("C");
            diagnosaRawat.setFlag("Y");

            boolean createSEP = true;
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            if(detailCheckupEntity != null){
                if(detailCheckupEntity.getNoSep() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoSep())){
                    createSEP = false;
                }
            }

            if("diagnosa_utama".equalsIgnoreCase(jenisDiagnosa) || "diagnosa_awal".equalsIgnoreCase(jenisDiagnosa)|| "diagnosa_primer".equalsIgnoreCase(jenisDiagnosa)){
                DiagnosaRawat dr = new DiagnosaRawat();
                dr.setIdDetailCheckup(idDetailCheckup);
                dr.setJenisDiagnosa(jenisDiagnosa);
                Boolean cek = diagnosaRawatBo.cekDiagnosa(dr);
                if(cek){
                    response.setStatus("error");
                    response.setMsg("Data "+jenisDiagnosa.replace("_", " ").toUpperCase()+" sudah ada ...!");
                }else{
                    if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                        response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            if(createSEP){
                                try {
                                    createSEP(idDetailCheckup, idDiagnosa);
                                    response.setMsg("reload");
                                }catch (Exception e){
                                    DiagnosaRawat diagnosaR = new DiagnosaRawat();
                                    diagnosaR.setIdDetailCheckup(idDetailCheckup);
                                    diagnosaR.setIdDiagnosa(idDiagnosa);
                                    diagnosaRawatBo.saveDelete(diagnosaR);
                                    response.setStatus("error");
                                    response.setMsg("Error Create SEP, "+e.getMessage());
                                }
                            }else{
                                response = updateCoverBpjs(idDetailCheckup);
                            }
                        }else{
                            response.setStatus("error");
                            response.setMsg("Found Error When "+response.getMsg());
                            return response;
                        }
                    } else {
                        response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    }
                }
            }else{
                if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                    response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    if ("success".equalsIgnoreCase(response.getStatus())) {
                        response = updateCoverBpjs(idDetailCheckup);
                    }else{
                        response.setStatus("error");
                        response.setMsg("Found Error When "+response.getMsg());
                    }
                } else {
                    response = diagnosaRawatBo.saveAdd(diagnosaRawat);
                    if("success".equalsIgnoreCase(response.getStatus())){
                        insertProfilRJ(idDetailCheckup, idDiagnosa+"-"+ketDiagnosa);
                    }
                }
            }

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Found Error When "+e.getMessage());
        }
        logger.info("[DiagnosaRawatAction.saveDiagnosa] end process >>>");

        return response;
    }

    public List<DiagnosaRawat> listDiagnosa(String idDetailCheckup) {

        logger.info("[DiagnosaRawatAction.listDiagnosa] start process >>>");
        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();
        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
        diagnosaRawat.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
            } catch (GeneralBOException e) {
                logger.error("[DiagnosaRawatAction.listDiagnosa] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[DiagnosaRawatAction.listDiagnosa] end process <<<");
            return diagnosaRawatList;
        } else {
            return null;
        }
    }

    public String getListComboDiagnosa() {
        logger.info("[DiagnosaRawatAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        } catch (GeneralBOException e) {
            logger.error("[DiagnosaRawatAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[DiagnosaRawatAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
    }

    public CrudResponse editDiagnosa(String idRawatDiagnosa, String idDiagnosa, String jenisDiagnosa, String ketDiagnosa, String jenisPasien, String idDetailCheckup) {
        CrudResponse response = new CrudResponse();
        logger.info("[DiagnosaRawatAction.editDiagnosa] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            diagnosaRawat.setIdDiagnosaRawat(idRawatDiagnosa);
            diagnosaRawat.setIdDiagnosa(idDiagnosa);
            diagnosaRawat.setKeteranganDiagnosa(ketDiagnosa);
            diagnosaRawat.setJenisDiagnosa(jenisDiagnosa);
            diagnosaRawat.setLastUpdate(updateTime);
            diagnosaRawat.setLastUpdateWho(userLogin);
            diagnosaRawat.setAction("U");

            ItSimrsDiagnosaRawatEntity entity = diagnosaRawatBo.getById(idRawatDiagnosa);
            if(entity != null){
                if(!"diagnosa_utama".equalsIgnoreCase(entity.getJenisDiagnosa()) && !"diagnosa_awal".equalsIgnoreCase(entity.getJenisDiagnosa()) && !"diagnosa_primer".equalsIgnoreCase(entity.getJenisDiagnosa())){
                    if("diagnosa_utama".equalsIgnoreCase(jenisDiagnosa) || "diagnosa_awal".equalsIgnoreCase(jenisDiagnosa)|| "diagnosa_primer".equalsIgnoreCase(jenisDiagnosa)) {
                        DiagnosaRawat dr = new DiagnosaRawat();
                        dr.setIdDetailCheckup(idDetailCheckup);
                        dr.setJenisDiagnosa(jenisDiagnosa);
                        Boolean cek = diagnosaRawatBo.cekDiagnosa(dr);
                        if (cek) {
                            response.setStatus("error");
                            response.setMsg("Data " + jenisDiagnosa.replace("_", " ").toUpperCase() + " sudah ada ...!");
                            return response;
                        }
                    }
                }
            }

            if ("bpjs".equalsIgnoreCase(jenisPasien) || "bpjs_rekanan".equalsIgnoreCase(jenisPasien)) {
                response = diagnosaRawatBo.saveEdit(diagnosaRawat);
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    response = updateCoverBpjs(idDetailCheckup);
                }else{
                    response.setStatus("error");
                    response.setMsg("Error when "+response.getMsg());
                }
            } else {
                response = diagnosaRawatBo.saveEdit(diagnosaRawat);
            }

            insertProfilRJ(idDetailCheckup, idDiagnosa+"-"+ketDiagnosa);

        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error when "+e.getMessage());
        }
        logger.info("[DiagnosaRawatAction.editDiagnosa] end process >>>");

        return response;
    }

    private void createSEP(String idDetailCheckup, String idDiagnosa){
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);
        String userArea = CommonUtil.userBranchLogin();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);

        if(detailCheckupEntity != null){
            ItSimrsHeaderChekupEntity headerChekupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
            if(headerChekupEntity != null){
                List<Pasien> pasienList = new ArrayList<>();
                Pasien pasien = new Pasien();
                pasien.setIdPasien(headerChekupEntity.getIdPasien());
                pasien.setFlag("Y");

                try {
                    pasienList = pasienBo.getByCriteria(pasien);
                } catch (GeneralBOException e) {
                    logger.error("[DiagnosaRawatAction.createSEP] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                }

                if(pasienList.size() > 0){
                    Pasien getPasien = pasienList.get(0);
                    Branch branch = new Branch();

                    try {
                        branch = branchBo.getBranchById(userArea, "Y");
                    } catch (GeneralBOException e) {
                        logger.error("[DiagnosaRawatAction.createSEP] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                    }

                    if(branch != null){

                        List<DokterTeam> dokterTeamList = new ArrayList<>();
                        DokterTeam dokterTeam = new DokterTeam();
                        dokterTeam.setIdDetailCheckup(idDetailCheckup);
                        dokterTeam.setFlag("Y");

                        try {
                            dokterTeamList = teamDokterBo.getByCriteria(dokterTeam);
                        }catch (GeneralBOException e){
                            logger.error("[DiagnosaRawatAction.createSEP] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                        }

                        if(dokterTeamList.size() > 0){
                            dokterTeam = dokterTeamList.get(0);
                            List<Dokter> dokterList = new ArrayList<>();
                            Dokter dokter = new Dokter();
                            dokter.setIdDokter(dokterTeam.getIdDokter());
                            dokter.setFlag("Y");

                            try {
                                dokterList = dokterBo.getByCriteria(dokter);
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
                            sepRequest.setPpkPelayanan(branch.getPpkPelayanan());//cons id rumah sakit
                            sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
                            sepRequest.setKlsRawat(detailCheckupEntity.getKelasPasien());//kelas rawat dari bpjs
                            sepRequest.setNoMr(getPasien.getIdPasien());//id pasien
                            sepRequest.setAsalRujukan("2");
                            sepRequest.setTglRujukan("");
                            sepRequest.setNoRujukan("");
                            sepRequest.setPoliTujuan("IGD");
                            sepRequest.setPpkRujukan("");
                            sepRequest.setCatatan("");
                            sepRequest.setDiagAwal(idDiagnosa);
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
                            sepRequest.setNoSuratSkdp(branch.getSuratSkdp());
                            sepRequest.setKodeDpjp(kodeDpjp);
                            sepRequest.setNoTelp(getPasien.getNoTelp());
                            sepRequest.setUserPembuatSep(userLogin);

                            SepResponse response = new SepResponse();

                            try {
                                response = bpjsBo.insertSepBpjs(sepRequest, userArea);
                            } catch (Exception e) {
                                logger.error("[DiagnosaRawatAction.createSEP] Error when insert SEP ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                throw new GeneralBOException("Error when new insert SEP", e);
                            }

                            if (response.getNoSep() != null) {

                                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                                detailCheckup.setNoSep(response.getNoSep());

                                KlaimRequest klaimRequest = new KlaimRequest();
                                klaimRequest.setNoSep(response.getNoSep());
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
                                klaimRequest.setCoderNik(branch.getCoderNik());

                                KlaimResponse responseNewClaim = new KlaimResponse();

                                try {
                                    responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, userArea);
                                } catch (GeneralBOException e) {
                                    logger.error("[DiagnosaRawatAction.createSEP] Error when new claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                    throw new GeneralBOException("Error when new claim", e);
                                }

                                List<TindakanRawat> tindakanRawatList = new ArrayList<>();
                                TindakanRawat tindakanRawat = new TindakanRawat();
                                tindakanRawat.setIdDetailCheckup(idDetailCheckup);

                                try {
                                    tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
                                } catch (GeneralBOException e) {
                                    logger.error("[DiagnosaRawatAction.createSEP] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                    throw new GeneralBOException("Error when tindakan, [" + e.getMessage() + "]");
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


                                if (tindakanRawatList.size() > 0) {
                                    for (TindakanRawat entity : tindakanRawatList) {

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
                                    }
                                }

                                if (responseNewClaim.getPatientId() != null) {

                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                    klaimDetailRequest.setNomorSep(response.getNoSep());
                                    klaimDetailRequest.setNomorKartu(getPasien.getNoKtp());
                                    klaimDetailRequest.setTglMasuk(updateTime.toString());
                                    klaimDetailRequest.setTglPulang(updateTime.toString());
                                    klaimDetailRequest.setJenisRawat("2");
                                    klaimDetailRequest.setKelasRawat(detailCheckupEntity.getKelasPasien());
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
                                    klaimDetailRequest.setDiagnosa(idDiagnosa);
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
                                    klaimDetailRequest.setKodeTarif(branch.getKodeTarif());
                                    klaimDetailRequest.setTarifRsPayorId(branch.getTarifPayorId());
                                    klaimDetailRequest.setPayorCd(branch.getPayorCd());
                                    klaimDetailRequest.setCobCd("");
                                    klaimDetailRequest.setCoderNik(branch.getCoderNik());

                                    KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                    try {
                                        claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, userArea);
                                    } catch (GeneralBOException e) {
                                        logger.error("[DiagnosaRawatAction.createSEP] Error when update claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                        throw new GeneralBOException("Error Error when update claim, [" + claimEklaimResponse.getMessage() + "]");
                                    }

                                    if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                        Grouping1Response grouping1Response = new Grouping1Response();

                                        try {
                                            grouping1Response = eklaimBo.groupingStage1Eklaim(response.getNoSep(), userArea);
                                        } catch (GeneralBOException e) {
                                            logger.error("[DiagnosaRawatAction.createSEP] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
                                            throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                        }

                                        // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                        if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                            BigDecimal tarifCbg = new BigDecimal(0);
                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());

                                                    //=====kode CBG INA=======
                                                    detailCheckup.setKodeCbg(grouping1Response.getCbgCode());

                                                    //======START SET TARIF BPJS=========

                                                    detailCheckup.setTarifBpjs(tarifCbg);

                                                    //======END SET TARIF BPJS=========

                                                    //Update SEP
                                                    checkupDetailBo.updateSEP(detailCheckup);

                                                } else {
                                                    logger.error("[DiagnosaRawatAction.createSEP] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                                    throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                                }
                                            } else {
                                                logger.error("[DiagnosaRawatAction.createSEP] Error when get cover biaya BPJS " + grouping1Response.getMessage());
                                                throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                            }


                                            // jika ada special cmg maka proses grouping stage 2
                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                    try {
                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(response.getNoSep(), specialCmgResponse.getCode(), userArea);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[DiagnosaRawatAction.createSEP] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                                                    }
                                                }
                                            }
                                        } else {
                                            logger.error("[DiagnosaRawatAction.createSEP] Error when get Tarif cover INA CBG ,update claim not success " + grouping1Response.getMessage());
                                            throw new GeneralBOException("Error when get Tarif cover INA CBG, [" + grouping1Response.getMessage() + "]");
                                        }

                                    } else {
                                        logger.error("[DiagnosaRawatAction.createSEP] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
                                        throw new GeneralBOException("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                    }
                                } else {
                                    logger.error("[DiagnosaRawatAction.createSEP] Error when get pastien Eklaim, " + responseNewClaim.getMsg());
                                    throw new GeneralBOException("Error when get pastien Eklaim, [" + responseNewClaim.getMsg() + "]");
                                }
                            } else {
                                logger.error("[DiagnosaRawatAction.createSEP] Error when generate SEP, " + response.getMessage());
                                throw new GeneralBOException("Error when generate SEP, [" + response.getMessage() + "]");
                            }
                        }
                    }
                }
            }
        }
    }

    private CrudResponse updateCoverBpjs(String idDetailCheckup) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String unitId = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        String tempDiagnosa = "";

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBo.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

        if (!detailCheckupList.isEmpty()) {
            headerDetailCheckup = detailCheckupList.get(0);

            if (headerDetailCheckup != null) {

                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setNoCheckup(headerDetailCheckup.getNoCheckup());

                List<HeaderCheckup> headerCheckupList = new ArrayList<>();
                List<Branch> branchList = new ArrayList<>();

                try {
                    headerCheckupList = checkupBo.getByCriteria(headerCheckup);
                } catch (GeneralBOException e) {
                    logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                }

                HeaderCheckup checkup = new HeaderCheckup();
                if (!headerCheckupList.isEmpty()) {
                    checkup = headerCheckupList.get(0);

                    if (checkup != null) {

                        Branch branch = new Branch();
                        branch.setBranchId(unitId);
                        branch.setFlag("Y");

                        try {
                            branchList = branchBo.getByCriteria(branch);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupAction.saveAdd] Error when search item ," + "Found problem when saving add data, please inform to your admin.", e);
                        }

                        Branch getBranch = new Branch();

                        if (branchList.size() > 0) {
                            getBranch = branchList.get(0);

                            if (getBranch.getCoderNik() != null) {

                                DataPerKlaimResponse klaimResponse = new DataPerKlaimResponse();

                                //search detail tindakan dari eklaim
                                try {
                                    klaimResponse = eklaimBo.detailPerKlaimEklaim(headerDetailCheckup.getNoSep(), unitId);
                                } catch (GeneralBOException e) {
                                    logger.error("[VerifikatorAction.saveApproveTindakan] Error When Get Header Checkup Data", e);
                                }

                                if (klaimResponse.getNomorSep() != null) {

                                    KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                    klaimDetailRequest.setTglMasuk(checkup.getCreatedDate().toString());
                                    klaimDetailRequest.setTglPulang(updateTime.toString());
                                    klaimDetailRequest.setNomorSep(klaimResponse.getNomorSep());
                                    klaimDetailRequest.setNomorKartu(klaimResponse.getNomorKartu());
                                    klaimDetailRequest.setJenisRawat(klaimResponse.getJenisRawat().toString());
                                    klaimDetailRequest.setKelasRawat(klaimResponse.getKelasRawat().toString());
                                    klaimDetailRequest.setAdlSubAcute(klaimResponse.getAdlSubAcute().toString());
                                    klaimDetailRequest.setAdlChronic(klaimResponse.getAdlChronic().toString());
                                    klaimDetailRequest.setIcuIndikator(klaimResponse.getIcuIndicator().toString());
                                    klaimDetailRequest.setIcuLos(klaimResponse.getIcuLos());
                                    klaimDetailRequest.setVentilatorHour(klaimResponse.getVentilatorHour());
                                    klaimDetailRequest.setUpgradeClassInd(klaimResponse.getUpgradeClassInd());
                                    klaimDetailRequest.setUpgradeClassClass(klaimResponse.getUpgradeClassClass());
                                    klaimDetailRequest.setUpgradeClassLos(klaimResponse.getUpgradeClassLos());
                                    klaimDetailRequest.setAddPaymentPct(klaimResponse.getAddPaymenPct());
                                    klaimDetailRequest.setBirthWeight(klaimResponse.getBeratLahir());
                                    klaimDetailRequest.setDischargeStatus(klaimResponse.getDischargeStatus().toString());

                                    //set diagnosa to eklam
                                    DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                                    diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
                                    diagnosaRawat.setOrderJenisDiagnosa("Y");
                                    List<DiagnosaRawat> diagnosaRawats = diagnosaRawatBo.getByCriteria(diagnosaRawat);
                                    if(diagnosaRawats.size() > 0){
                                        for (DiagnosaRawat dr: diagnosaRawats){
                                            if(!"".equalsIgnoreCase(tempDiagnosa)){
                                                tempDiagnosa = tempDiagnosa+"#"+dr.getIdDiagnosa();
                                            }else{
                                                tempDiagnosa = dr.getIdDiagnosa();
                                            }
                                        }
                                    }
                                    klaimDetailRequest.setDiagnosa(tempDiagnosa);

                                    klaimDetailRequest.setProcedure(klaimResponse.getProcedure());
                                    klaimDetailRequest.setTarifRsNonBedah(klaimResponse.getTarifRsProsedurNonBedah().toString());
                                    klaimDetailRequest.setTarifRsProsedurBedah(klaimResponse.getTarifRsProsedurBedah().toString());
                                    klaimDetailRequest.setTarifRsKonsultasi(klaimResponse.getTarifRsKonsultasi().toString());
                                    klaimDetailRequest.setTarifRsTenagaAhli(klaimResponse.getTarifRsTenagaAhli().toString());
                                    klaimDetailRequest.setTarifRsKeperawatan(klaimResponse.getTarifRsKeperawatan().toString());
                                    klaimDetailRequest.setTarifRsPenunjang(klaimResponse.getTarifRsPenunjang().toString());

                                    klaimDetailRequest.setTarifRsRadiologi(klaimResponse.getTarifRsRadiologi().toString());
                                    klaimDetailRequest.setTarifRsLaboratorium(klaimResponse.getTarifRsLaboratorium().toString());
                                    klaimDetailRequest.setTarifRsPelayananDarah(klaimResponse.getTarifRsPelayananDarah().toString());
                                    klaimDetailRequest.setTarifRsRehabilitasi(klaimResponse.getTarifRsRehabilitasi().toString());
                                    klaimDetailRequest.setTarifRsKamar(klaimResponse.getTarifRsKamar().toString());
                                    klaimDetailRequest.setTarifRsRawatIntensif(klaimResponse.getTarifRsRawatIntensif().toString());

                                    klaimDetailRequest.setTarifRsObat(klaimResponse.getTarifRsObat().toString());
                                    klaimDetailRequest.setTarifRsObatKronis(klaimResponse.getTarifRsObatKronis().toString());
                                    klaimDetailRequest.setTarifRsObatKemoterapi(klaimResponse.getTarifRsObatKemotrapis().toString());
                                    klaimDetailRequest.setTarifRsObatAlkes(klaimResponse.getTarifRsAlkes().toString());
                                    klaimDetailRequest.setTarifRsBmhp(klaimResponse.getTarifRsBmhp().toString());
                                    klaimDetailRequest.setTarifRsSewaAlat(klaimResponse.getTarifRsSewaAlat().toString());

                                    klaimDetailRequest.setTarifPoliEks(klaimResponse.getTarifPoliEks());
                                    klaimDetailRequest.setNamaDokter(klaimResponse.getNamaDokter());
                                    klaimDetailRequest.setKodeTarif(klaimResponse.getKodeTarif());
                                    klaimDetailRequest.setTarifRsPayorId(klaimResponse.getPayorId());
                                    klaimDetailRequest.setPayorCd(klaimResponse.getKlaimStatusCd());
                                    klaimDetailRequest.setCobCd(klaimResponse.getKlaimStatusCd());
                                    klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                    KlaimDetailResponse klaimDetailResponse = new KlaimDetailResponse();

                                    try {
                                        klaimDetailResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, unitId);
                                        response.setStatus(klaimDetailResponse.getStatus());
                                        response.setMsg(klaimDetailResponse.getMessage());
                                    } catch (GeneralBOException e) {
                                        logger.error("[VerifikatorAction.saveApproveTindakan] Error When update tarif tindakan to eklaim", e);
                                        response.setStatus("error");
                                        response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                    }

                                    if (klaimDetailResponse != null) {
                                        if ("200".equalsIgnoreCase(klaimDetailResponse.getStatus())) {
                                            Grouping1Response grouping1Response = new Grouping1Response();

                                            //groper setelah update tarif tindakan
                                            try {
                                                grouping1Response = eklaimBo.groupingStage1Eklaim(headerDetailCheckup.getNoSep(), unitId);
                                            } catch (GeneralBOException e) {
                                                logger.error("[CheckupAction.saveAdd] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
                                                response.setStatus("error");
                                                response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                            }

                                            BigDecimal tarifCbg = new BigDecimal(0);
                                            if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                                if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                    tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());


                                                    //======START SET TARIF BPJS kode CBG INA=========

                                                    headerDetailCheckup.setTarifBpjs(tarifCbg);
                                                    headerDetailCheckup.setKodeCbg(grouping1Response.getCbgCode());
                                                    headerDetailCheckup.setLastUpdateWho(userLogin);
                                                    headerDetailCheckup.setLastUpdate(updateTime);

                                                    response = diagnosaRawatBo.updateCoverBpjs(headerDetailCheckup);

                                                    //======END SET TARIF BPJS=========


                                                } else {
                                                    response.setStatus("error");
                                                    response.setMsg("Error when get cover biaya BPJS, [" + grouping1Response.getMessage());
                                                }
                                            } else {
                                                response.setStatus("error");
                                                response.setMsg("Error when get cover biaya BPJS, [" + grouping1Response.getMessage());
                                            }

                                            // jika ada special cmg maka proses grouping stage 2
                                            if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                                for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                    Grouping2Response grouping2Response = new Grouping2Response();
                                                    try {
                                                        grouping2Response = eklaimBo.groupingStage2Eklaim(headerDetailCheckup.getNoSep(), specialCmgResponse.getCode(), unitId);
                                                    } catch (GeneralBOException e) {
                                                        logger.error("[CheckupAction.saveAdd] Error when adding item ,Found problem when saving add data, please inform to your admin.", e);
                                                        response.setStatus("error");
                                                        response.setMsg("[VerifikatorAction.saveApproveTindakan] Found Error: " + e);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                } else {
                                    response.setStatus("error");
                                    response.setMsg("Coder Nik Petugas Bpjs Tidak ada..!");
                                }
                            }
                        }
                    }
                }
            }
        }
        return response;
    }

    public void insertProfilRJ(String idDetailCheckup, String diagnosa) {
        CrudResponse response = new CrudResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setDiagnosa(diagnosa);
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setDiagnosa(diagnosa);
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
    }
}