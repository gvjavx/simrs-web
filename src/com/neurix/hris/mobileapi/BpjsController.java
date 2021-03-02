package com.neurix.hris.mobileapi;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.FingerPrintResponse;
import com.neurix.hris.mobileapi.model.simrs.Poli;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.neurix.hris.transaksi.absensi.model.AbsensiOnCall;
import com.neurix.hris.transaksi.absensi.model.AbsensiPegawai;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensi;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.DataPerKlaimResponse;
import com.neurix.simrs.bpjs.eklaim.model.KlaimRequest;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.*;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import com.neurix.simrs.bpjs.BpjsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.*;


/**
 * Created by Toshiba on 21/10/2019.
 */
public class BpjsController extends BpjsService implements ModelDriven<Object> {

    private Collection<TindakanResponse> listOfTindakanResponse = new ArrayList<>();

    private String data;
    private String keyword;
    private String userId;
    private String RegTemp;
    private String result;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private FingerPrintResponse model;
    private List<Poli> listOfPoli = new ArrayList<>();
    private BpjsBo bpjsBoProxy;
    private EklaimBo eklaimBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private AbsensiBo absensiBoProxy;
    private UserBo userBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    public UserBo getUserBoProxy() {
        return userBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public AbsensiBo getAbsensiBoProxy() {
        return absensiBoProxy;
    }

    public void setAbsensiBoProxy(AbsensiBo absensiBoProxy) {
        this.absensiBoProxy = absensiBoProxy;
    }

    public BillingSystemBo getBillingSystemBoProxy() {
        return billingSystemBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public String getRegTemp() {
        return RegTemp;
    }

    public void setRegTemp(String regTemp) {
        RegTemp = regTemp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setModel(FingerPrintResponse model) {
        this.model = model;
    }

    public List<Poli> getListOfPoli() {
        return listOfPoli;
    }

    public void setListOfPoli(List<Poli> listOfPoli) {
        this.listOfPoli = listOfPoli;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BpjsController.logger = logger;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BpjsBo getBpjsBoProxy() {
        return bpjsBoProxy;
    }

    public EklaimBo getEklaimBoProxy() {
        return eklaimBoProxy;
    }

    public void setEklaimBoProxy(EklaimBo eklaimBoProxy) {
        this.eklaimBoProxy = eklaimBoProxy;
    }

    public void setBpjsBoProxy(BpjsBo bpjsBoProxy) {
        this.bpjsBoProxy = bpjsBoProxy;
    }

    private static transient Logger logger = Logger.getLogger(BpjsController.class);

    public BpjsController() throws GeneralSecurityException, IOException {
    }

    public String index() {
        logger.info(data);
        switch (data){
            case "finger-register":
                result=registerFinger(userId);
                break;
            case "finger-register-proses":
                registerFingerProses(RegTemp);
                break;
            case "bpjs-poli":
                listPoli(keyword);
                break;
            case "bpjs-tindakan":
                listTindakan(keyword);
                break;
            case "bpjs-diagnosa":
                listDiagnosa(keyword);
                break;
            case "bpjs-peserta-bpjs":
                getPesertaBpjsByAPIBpjs(keyword);
                break;
            case "bpjs-peserta-nik-bpjs":
                getPesertaNikByAPIBpjs(keyword);
                break;
            case "bpjs-provinsi":
                listProvinsi();
                break;
            case "eklaim-detail-data":
                dataDetailEklaim(keyword);
                break;
            case "eklaim-print-data":
                printEklaim(keyword);
                break;
            case "insert-sep":
                sendSep();
                break;
            case "cek-rujukan":
                cekRujukan(keyword,tipe);
                break;
            case "cek-rujukan-bpjs":
                cekRujukanNoBpjs(keyword,tipe);
                break;
            case "create-jurnal-bpjs-1":
                createJurnalBillingCase1();
                break;
            case "create-jurnal-bpjs-2":
                createJurnalBillingCase2();
                break;
            case "create-jurnal-bpjs-3":
                createJurnalBillingCase3();
                break;
            case "create-jurnal-bpjs-4":
                createJurnalBillingCase4();
                break;
            case "create-jurnal-bpjs-5":
                createJurnalBillingCase5();
                break;
            case "create-jurnal-bpjs-6":
                createJurnalBillingCase6();
                break;
            case "delete-sep":
                deleteSep(keyword);
                break;
            case "create-jurnal-bpjs-7":
                createJurnalBillingCase7();
                break;
            case "cron-mesin-absensi":
                cronJobMesinAbsensi();
                break;
            case "cron-absensi-pegawai":
                cronJobAbsensiPegawai();
                break;
            default:
                logger.info("==========NO ONE CARE============");
        }
        return result;
    }

    public void deleteSep(String key){
        SepResponse poliResponseList = new SepResponse();
        SepRequest request = new SepRequest();
        request.setNoSep(key);
        request.setUserPembuatSep(CommonUtil.userLogin());
        try {
            poliResponseList= bpjsBoProxy.deleteSepBpjs(request,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listPoli] Error : " + "[" + e + "]");
        }
        logger.info(poliResponseList.getMessage());
//        for (SepResponse poliResponse : poliResponseList){
//            logger.info(poliResponse.getMessage());
//            logger.info(poliResponse.getMessage());
//        }
    }

    public void listPoli(String key){
        List<PoliResponse> poliResponseList = new ArrayList<>();
        try {
            poliResponseList= bpjsBoProxy.GetPoliByAPIBpjs(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listPoli] Error : " + "[" + e + "]");
        }
        for (PoliResponse poliResponse : poliResponseList){
            logger.info(poliResponse.getNamaPoliBpjs());
            logger.info(poliResponse.getKodePoliBpjs());
        }
    }
    public void cekRujukan(String rujukanId,String tipeRujukan){
        RujukanResponse rujukanResponse = new RujukanResponse();
        try {
            rujukanResponse= bpjsBoProxy.caraRujukanBerdasarNomorBpjs(rujukanId,tipeRujukan,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listPoli] Error : " + "[" + e + "]");
        }
        logger.info(rujukanResponse);

    }
    public void cekRujukanNoBpjs(String noBpjs,String tipeRujukan){
        RujukanResponse rujukanResponse = new RujukanResponse();
        try {
            rujukanResponse= bpjsBoProxy.caraRujukanBerdasarNomorkartuBpjs(noBpjs,tipeRujukan,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listPoli] Error : " + "[" + e + "]");
        }
        logger.info(rujukanResponse);

    }
    public void listTindakan(String key){
        List<TindakanResponse> tindakanResponseList = new ArrayList<>();
        try {
            tindakanResponseList= bpjsBoProxy.GetTindakanByAPIBpjs(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listTindakan] Error : " + "[" + e + "]");
        }
        for (TindakanResponse tindakanResponse : tindakanResponseList){
            logger.info(tindakanResponse.getNamaTindakanBpjs());
        }

        listOfTindakanResponse.addAll(tindakanResponseList);
    }
    public void listDiagnosa(String key){
        List<DiagnosaResponse> diagnosaResponseList = new ArrayList<>();
        try {
            diagnosaResponseList= bpjsBoProxy.getDiagnosaByAPIBpjs(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (DiagnosaResponse diagnosaResponse : diagnosaResponseList){
            logger.info(diagnosaResponse.getNamaDiagnosaBpjs());
        }
    }
    public void listProvinsi(){
        List<ProvinsiResponse> provinsiResponseList = new ArrayList<>();
        try {
            provinsiResponseList= bpjsBoProxy.GetProvinsiByAPIBpjs("RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (ProvinsiResponse provinsiResponse : provinsiResponseList){
            logger.info(provinsiResponse.getNamaProvinsiBpjs());
        }
    }
    public void listKabupaten(String key){
        List<KabupatenResponse> kabupatenResponseList = new ArrayList<>();
        try {
            kabupatenResponseList= bpjsBoProxy.GetKabupatenByAPIBpjs(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (KabupatenResponse kabupatenResponse : kabupatenResponseList){
            logger.info(kabupatenResponse.getNamaKabupatenBpjs());
        }
    }
    public void listKecamatan(String key){
        List<KecamatanResponse> kecamatanResponseList = new ArrayList<>();
        try {
            kecamatanResponseList= bpjsBoProxy.GetKecamatanByAPIBpjs(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (KecamatanResponse kecamatanResponse : kecamatanResponseList){
            logger.info(kecamatanResponse.getNamaKecamatanBpjs());
        }
    }

    public void listKelasRawat(){
        List<KelasRawatResponse> kecamatanResponseList = new ArrayList<>();
        try {
            kecamatanResponseList= bpjsBoProxy.GetKelasRawatByAPIBpjs("RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (KelasRawatResponse kelasRawatResponse : kecamatanResponseList){
            logger.info(kelasRawatResponse.getNamaKelasRawatBpjs());
        }
    }

    public void listFaskes(String key){
        List<FaskesResponse> faskesResponseList = new ArrayList<>();
        try {
            faskesResponseList= bpjsBoProxy.GetFaskesByAPIBpjs(key,"","RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        for (FaskesResponse faskesResponse : faskesResponseList){
            logger.info(faskesResponse.getNamaFaskesBpjs());
        }
    }
    public void getPesertaBpjsByAPIBpjs(String key){
        PesertaResponse pesertaResponse = new PesertaResponse();
        try {
            pesertaResponse= bpjsBoProxy.GetPesertaBpjsByAPIBpjs(key,"2020-01-16","RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        logger.info(pesertaResponse.getNama());
    }
    public void getPesertaNikByAPIBpjs(String key){
        PesertaResponse pesertaResponse = new PesertaResponse();
        try {
            pesertaResponse= bpjsBoProxy.GetPesertaNikByAPIBpjs(key,"2020-01-16","RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        logger.info(pesertaResponse.getNama());
    }

    public String printEklaim(String key){
        String data = "";
        try {
            data= eklaimBoProxy.printKlaimPerSepEklaim(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        return data;
    }

    public void dataDetailEklaim(String key){
        DataPerKlaimResponse dataPerKlaimResponse = new DataPerKlaimResponse();
        try {
            dataPerKlaimResponse= eklaimBoProxy.detailPerKlaimEklaim(key,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.listDiagnosa] Error : " + "[" + e + "]");
        }
        logger.info(dataPerKlaimResponse.getNamaPasien());
    }

    public void sendSep(){
        SepRequest sepRequest = new SepRequest();
        sepRequest.setNoKartu("0001543129907");
        sepRequest.setTglSep("2020-02-05");
        sepRequest.setPpkPelayanan("1311R003");
        sepRequest.setJnsPelayanan("2");
        sepRequest.setKlsRawat("3");
        sepRequest.setNoMr("123456");
        sepRequest.setAsalRujukan("1");
        sepRequest.setTglRujukan("2020-01-31");
        sepRequest.setNoRujukan("0189B0220120P000650");
        sepRequest.setPpkRujukan("0189B022");
        sepRequest.setCatatan("test");
        sepRequest.setDiagAwal("I63");
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
        sepRequest.setNoTelp("081919999");
        sepRequest.setUserPembuatSep("Coba Ws");
        try {
            bpjsBoProxy.insertSepBpjs(sepRequest,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.sendSep] Error : " + "[" + e + "]");
        }
    }


    public void insertEklaim(){
        KlaimRequest request = new KlaimRequest();
        request.setNoKartu("010101");
        request.setNoSep("888888");
        request.setNoRm("8989");
        request.setNamaPasien("DAUSMEN");
        request.setTglLahir("1996-10-01");
        request.setGender("1");

        try {
            eklaimBoProxy.insertNewClaimEklaim(request,"RS01");
        }catch (Exception e){
            logger.error("[BpjsController.sendSep] Error : " + "[" + e + "]");
        }
    }
    public String registerFinger(String userId){
        logger.info("[BpjsController.registerFinger] start process >>>");
        String result;
        result= userId +";SecurityKey;"+ CommonConstant.timeLimitReg+";"+"http://localhost:8080/simrs/mobileapi/bpjs?data=finger-register-proses"+";08PB-D59F-8682-C4AA-B348-BYXMK520J01750;";
        logger.info("[BpjsController.registerFinger] end process <<<");
        return result;
    }
    public String registerFingerProses(String result){
        logger.info("[BpjsController.registerFingerProses] start process >>>");
        logger.info(result);
        logger.info("[BpjsController.registerFingerProses] end process <<<");
        return result;
    }

    //case 1 jika hanya 1 parameter
    //untuk membuat uang muka
    public void createJurnalBillingCase1(){
        Map piutangTerverif = new HashMap();
        piutangTerverif.put("bukti","INVHU000005");
        piutangTerverif.put("nilai",new BigDecimal(90000));

        Map data = new HashMap();
        data.put("pasien_id","00000006");
        data.put("bukti","IV000001111");
        data.put("piutang_terverif", piutangTerverif);
        data.put("kas", new BigDecimal(90000));
        data.put("metode_bayar", "tunai");

        try {
            billingSystemBoProxy.createJurnal("01",data,"KP","TEST 1 : pembayaran piutang pasien bpjs atas NO FPK","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase1] Error : " + "[" + e + "]");
        }
    }
    //case 2
    // untuk Case ada list
    public void createJurnalBillingCase2 (){
        List<Map> detailJurnal = new ArrayList<>();
        Map dataJurnal = new HashMap();
        dataJurnal.put("bukti","1111");
        dataJurnal.put("master_id","VN00011");
        dataJurnal.put("nilai",new BigDecimal(20000));
        detailJurnal.add(dataJurnal);
        dataJurnal = new HashMap();
        dataJurnal.put("bukti","2222");
        dataJurnal.put("master_id","VN00011");
        dataJurnal.put("nilai",new BigDecimal(30000));
        detailJurnal.add(dataJurnal);
        dataJurnal = new HashMap();
        dataJurnal.put("bukti","3333");
        dataJurnal.put("master_id","VN00011");
        dataJurnal.put("nilai",new BigDecimal(40000));
        detailJurnal.add(dataJurnal);

        Map data = new HashMap();
        data.put("piutang_rekanan", detailJurnal);
        data.put("kas",new BigDecimal(90000) );
        data.put("metode_bayar","tunai" );

        try {
            billingSystemBoProxy.createJurnal("03",data,"KP","TEST 2 : pembayaran piutang rekanan","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    //case 3
    // untuk pembayaran yang masuk ke divisi
    public void createJurnalBillingCase3 (){
        Map piutangPasienNonBpjs = new HashMap();
        piutangPasienNonBpjs.put("bukti","INVHU000003");
        piutangPasienNonBpjs.put("nilai",new BigDecimal(110000));
        piutangPasienNonBpjs.put("pasien_id","00000006");

        Map pendapatanpasien = new HashMap();
        pendapatanpasien.put("nilai",new BigDecimal(110000));
        pendapatanpasien.put("pasien_id","00000006");
        pendapatanpasien.put("divisi_id","01.01.01");

        Map data = new HashMap();
        data.put("pendapatan_rawat_jalan_bpjs", pendapatanpasien);
        data.put("piutang_pasien_bpjs", piutangPasienNonBpjs);

        try {
            billingSystemBoProxy.createJurnal("06",data,"KP","TEST 3 : Closing Pasien Rawat Jalan BPJS tanpa Obat","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    // case 4 ada detail pembayaran activity
    public void createJurnalBillingCase4 (){
        List<Map> activityList = new ArrayList<>();
        Map activity = new HashMap();
        activity.put("activity_id","1111");
        activity.put("person_id","88888888");
        activity.put("nilai",new BigDecimal(20000));
        activityList.add(activity);

        Map piutangPasienNonBpjs = new HashMap();
        piutangPasienNonBpjs.put("bukti","INVHU000003");
        piutangPasienNonBpjs.put("nilai",new BigDecimal(20000));
        piutangPasienNonBpjs.put("activity",activityList);

        Map data = new HashMap();
        data.put("pasien_id","00000006");
        data.put("bukti","IV000001111");
        data.put("kas", new BigDecimal(20000));
        data.put("metode_bayar", "tunai");
        data.put("piutang_pasien_non_bpjs", piutangPasienNonBpjs);

        try {
            billingSystemBoProxy.createJurnal("02",data,"KP","TEST 4 : pembayaran piutang pasien non bpjs","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase1] Error : " + "[" + e + "]");
        }
    }

    //case 5
    // untuk pengiriman barang
    public void createJurnalBillingCase5 (){
        //list barang
        List<Map> barangList = new ArrayList<>();
        Map dataBarang = new HashMap();
        dataBarang.put("kd_barang","09111112422");
        dataBarang.put("nilai",new BigDecimal(20000));
        barangList.add(dataBarang);
        dataBarang = new HashMap();
        dataBarang.put("kd_barang","80800800");
        dataBarang.put("nilai",new BigDecimal(20000));
        barangList.add(dataBarang);
        dataBarang.put("kd_barang","1234567");
        dataBarang.put("nilai",new BigDecimal(20000));
        barangList.add(dataBarang);

        // map yang memiliki bukti
        Map hutangVendor = new HashMap();
        hutangVendor.put("bukti","INV000921234");
        hutangVendor.put("nilai",new BigDecimal(160000));

        // map yang memiliki bukti
        Map ppnMasukan = new HashMap();
        ppnMasukan.put("bukti","INV000921233");
        ppnMasukan.put("nilai",new BigDecimal(100000));

        Map data = new HashMap();
        data.put("master_id","VN00011");
        data.put("persediaan_gudang", barangList);
        data.put("hutang_farmasi_vendor", hutangVendor);
        data.put("ppn_masukan",ppnMasukan);

        try {
            billingSystemBoProxy.createJurnal("27",data,"KP","TEST 5 : Pengiriman Barang Gudang ke Apotik","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    //case 7
    // untuk pembayaran yang masuk ke dalam kas
    public void createJurnalBillingCase7 (){
        List<Map> activityList = new ArrayList<>();
        Map activity = new HashMap();
        activity.put("activity_id","1111");
        activity.put("person_id","88888888");
        activity.put("no_trans","DCM11111");
        activity.put("nilai",new BigDecimal(110000));
        activityList.add(activity);

        Map uangMuka = new HashMap();
        uangMuka.put("pasien_id","P0000001");
        uangMuka.put("bukti","INVHU000003");
        uangMuka.put("nilai",new BigDecimal(110000));
        uangMuka.put("activity",activityList);

        Map kas = new HashMap();
        kas.put("metode_bayar", "transfer");
        kas.put("bank", "1.1.01.02.01");
        kas.put("nilai",new BigDecimal(110000));

        Map data = new HashMap();
        data.put("kas", kas);
        data.put("uang_muka", uangMuka);

        try {
            billingSystemBoProxy.createJurnal("04",data,"RS01","TEST 4 : pembayaran uang muka","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    public void cronJobMesinAbsensi (){
        String metode = CommonConstant.METODE_MESIN_ABSENSI;
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Date tanggalSekarang = new Date();
        String branchId=CommonConstant.ID_KANPUS;
        MesinAbsensi mesinAbsensi = new MesinAbsensi();
        mesinAbsensi.setCreatedWho("cron");
        mesinAbsensi.setLastUpdate(updateTime);

        if ("all".equalsIgnoreCase(metode)){
            try {
                absensiBoProxy.getAllDataFromMesin(mesinAbsensi);
            }catch (Exception e){
                String error = "ERROR WHEN GET MESIN: " + "[" + e + "]";
                absensiBoProxy.saveErrorMessage(error,"BpjsController.cronJobAbsensiPegawai");

                //Kirim Notif
                List<User> usersList = userBoProxy.getUserByRoleAndBranch(CommonConstant.ROLE_ID_ADMIN,branchId);
                for (User user : usersList){
                    Notifikasi notif = new Notifikasi();
                    notif.setNip(user.getUserId());
                    notif.setNoRequest("");
                    notif.setTipeNotifId("umum");
                    notif.setTipeNotifName(("Pemberitahuan"));
                    notif.setNote("[getAllDataFromMesin] Data mesin absensi pada tanggal "+CommonUtil.convertDateToString(tanggalSekarang)+" tidak bisa di sync secara otomatis lakukan sync manual");
                    notif.setCreatedWho("Cron");
                    notif.setTo("self");

                    notifikasiBoProxy.sendNotif(notif);
                }

                logger.error(error);
            }
        }else {
            try {
                absensiBoProxy.getDataFromMesin(mesinAbsensi);
            }catch (Exception e){
                String error = "ERROR WHEN GET MESIN: " + "[" + e + "]";
                absensiBoProxy.saveErrorMessage(error,"BpjsController.cronJobAbsensiPegawai");

                //Kirim Notif
                List<User> usersList = userBoProxy.getUserByRoleAndBranch(CommonConstant.ROLE_ID_ADMIN,branchId);
                for (User user : usersList){
                    Notifikasi notif = new Notifikasi();
                    notif.setNip(user.getUserId());
                    notif.setNoRequest("");
                    notif.setTipeNotifId("umum");
                    notif.setTipeNotifName(("Pemberitahuan"));
                    notif.setNote("[getDataFromMesin] Data mesin absensi pada tanggal "+CommonUtil.convertDateToString(tanggalSekarang)+" tidak bisa di sync secara otomatis lakukan sync manual");
                    notif.setCreatedWho("Cron");
                    notif.setTo("self");

                    notifikasiBoProxy.sendNotif(notif);
                }

                logger.error(error);
            }
        }
    }

    public void createJurnalBillingCase6 (){
        //list barang
        List<Map> detailJurnal = new ArrayList<>();
        Map dataJurnal = new HashMap();
        dataJurnal.put("bukti","1111");
        dataJurnal.put("pasien_id","11111");
        dataJurnal.put("nilai",new BigDecimal(20000));
        detailJurnal.add(dataJurnal);
        dataJurnal = new HashMap();
        dataJurnal.put("bukti","2222");
        dataJurnal.put("pasien_id","22222");
        dataJurnal.put("nilai",new BigDecimal(30000));
        detailJurnal.add(dataJurnal);
        dataJurnal = new HashMap();
        dataJurnal.put("bukti","3333");
        dataJurnal.put("pasien_id","33333");
        dataJurnal.put("nilai",new BigDecimal(40000));
        detailJurnal.add(dataJurnal);

        // map yang memiliki bukti
        Map data = new HashMap();
        data.put("kas", new BigDecimal(90000));
        data.put("piutang_pasien_bpjs",detailJurnal);
        data.put("metode_bayar", "tunai");

        try {
            billingSystemBoProxy.createJurnal("23",data,"RS01","TEST 6 : Untuk Pembayaran Piutang BPJS","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    //case 4
    // untuk Pembuatan Hutang Usaha
    public void createJurnalBillingCase8 (){
        Map piutangPasienBpjs = new HashMap();
        piutangPasienBpjs.put("bukti","INVHU000001");
        piutangPasienBpjs.put("nilai",new BigDecimal(10000000));

        Map data = new HashMap();
        data.put("pasien_id","RS0103200013");
        data.put("piutang_pasien_bpjs",piutangPasienBpjs);
        data.put("pendapatan_rawat_jalan_bpjs", new BigDecimal(10000000));
        data.put("metode_bayar", "transfer");
        data.put("bank", "mandiri");

        try {
            billingSystemBoProxy.createJurnal("04",data,"RS01","TEST 7 : Pembayaran uang muka","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    @Override
    public Object getModel() {
        switch (data){
            case "finger-register":
                return result;
            case "bpjs-tindakan":
                return listOfTindakanResponse;
            default: return null;
        }
    }

    public void cronJobAbsensiPegawai (){
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<AbsensiPegawai> absensiPegawaiList= new ArrayList<>();
        List<AbsensiOnCall> listOfResultOnCall = new ArrayList<>();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String branchId=CommonConstant.ID_KANPUS;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date tanggalSekarang = new Date(cal.getTimeInMillis());

        AbsensiPegawai search = new AbsensiPegawai();
        search.setCreatedWho("cron");
        search.setLastUpdate(updateTime);
        search.setLastUpdateWho("cron");
        search.setCreatedDate(updateTime);
        search.setFlag("Y");
        search.setAction("U");

        AbsensiPegawai data = new AbsensiPegawai();
        data.setBranchId(branchId);
        data.setTanggalUtil(tanggalSekarang);

        try {
            absensiPegawaiList = absensiBoProxy.cronInquiry(data);
            listOfResultOnCall = (List<AbsensiOnCall>) session.getAttribute("listOfResultOnCall");
            if (listOfResultOnCall==null){
                listOfResultOnCall = new ArrayList<>();
            }
            absensiBoProxy.saveAddAbsensi(absensiPegawaiList,listOfResultOnCall,search);
        }catch (Exception e){
            String error = "ERROR WHEN GET ABSENSI PEGAWAI : " + "[" + e + "]";
            absensiBoProxy.saveErrorMessage(error,"BpjsController.cronJobAbsensiPegawai");

            //Kirim Notif
            List<User> usersList = userBoProxy.getUserByRoleAndBranch(CommonConstant.ROLE_ID_ADMIN,branchId);
            for (User user : usersList){
                Notifikasi notif = new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest("");
                notif.setTipeNotifId("umum");
                notif.setTipeNotifName(("Pemberitahuan"));
                notif.setNote("Data absensi pada tanggal "+CommonUtil.convertDateToString(tanggalSekarang)+" tidak bisa diinquiry secara otomatis lakukan inquiry manual");
                notif.setCreatedWho("Cron");
                notif.setTo("self");

                notifikasiBoProxy.sendNotif(notif);
            }

            logger.error(error);
        }
    }

}
