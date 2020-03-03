package com.neurix.hris.mobileapi;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.hris.mobileapi.model.FingerPrintResponse;
import com.neurix.hris.mobileapi.model.simrs.Poli;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.DataPerKlaimResponse;
import com.neurix.simrs.bpjs.eklaim.model.KlaimRequest;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.*;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import com.neurix.simrs.bpjs.BpjsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            default:
                logger.info("==========NO ONE CARE============");
        }
        return result;
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
        Map uangMuka = new HashMap();
        uangMuka.put("bukti","INVHU000005");
        uangMuka.put("nilai",new BigDecimal(90000));

        Map data = new HashMap();
        data.put("master_id","00000006");
        data.put("bukti","IV000001111");
        data.put("uang_muka", uangMuka);
        data.put("kas", new BigDecimal(90000));
        data.put("metode_bayar", "tunai");

        try {
            billingSystemBoProxy.createJurnal("01",data,"RS03","TEST 1 : untuk pembayaran uang muka pasien sigit","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase1] Error : " + "[" + e + "]");
        }
    }

    //case 2 jika ada 2 debit parameter
    // case 2 untuk Closing Pasien Rawat Jalan Umum Piutang tanpa Obat
    public void createJurnalBillingCase2 (){
        Map piutangPasienNonBpjs = new HashMap();
        piutangPasienNonBpjs.put("bukti","INVHU000003");
        piutangPasienNonBpjs.put("nilai",new BigDecimal(110000));

        Map uangMuka = new HashMap();
        uangMuka.put("bukti","INVHU000004");
        uangMuka.put("nilai",new BigDecimal(90000));

        Map data = new HashMap();
        data.put("master_id","00000006");
        data.put("bukti","IV000001111");
        data.put("uang_muka", uangMuka);
        data.put("piutang_pasien_non_bpjs", piutangPasienNonBpjs);
        data.put("pendapatan_rawat_jalan_non_bpjs", new BigDecimal(200000));

        try {
            billingSystemBoProxy.createJurnal("04",data,"RS03","TEST 2 : Closing Pasien Rawat Jalan Umum Piutang tanpa Obat pasien sigit","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase1] Error : " + "[" + e + "]");
        }
    }
    //case 3
    // untuk pembayaran yang masuk ke dalam kas
    public void createJurnalBillingCase3 (){
        Map piutangPasienNonBpjs = new HashMap();
        piutangPasienNonBpjs.put("bukti","INVHU000002");
        piutangPasienNonBpjs.put("nilai",new BigDecimal(110000));

        Map data = new HashMap();
        data.put("master_id","00000006");
        data.put("kas", new BigDecimal(110000));
        data.put("piutang_pasien_non_bpjs", piutangPasienNonBpjs);
        data.put("metode_bayar", "transfer");
        data.put("bank", "bri");

        try {
            billingSystemBoProxy.createJurnal("11",data,"RS03","TEST 3 : untuk pembayaran yang masuk ke dalam bank","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    //case 4
    // untuk Pembuatan Hutang Usaha
    public void createJurnalBillingCase4 (){
        Map hutangUsaha = new HashMap();
        hutangUsaha.put("bukti","INVHU000001");
        hutangUsaha.put("nilai",new BigDecimal(10000000));

        Map data = new HashMap();
        data.put("master_id","00000008");
        data.put("bukti","CB000050");
        data.put("hutang_usaha",hutangUsaha);
        data.put("kas", new BigDecimal(10000000));
        data.put("metode_bayar", "tunai");

        try {
            billingSystemBoProxy.createJurnal("22",data,"RS03","TEST 4 : untuk pembuatan hutang usaha","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
        }
    }

    //case 4
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
        hutangVendor.put("nilai",new BigDecimal(100000));

        Map data = new HashMap();
        data.put("master_id","00000008");
        data.put("persediaan_gudang", barangList);
        data.put("hutang_vendor", hutangVendor);
        data.put("ppn_masukan", new BigDecimal(40000));

        try {
            billingSystemBoProxy.createJurnal("13",data,"RS03","TEST 5 : Untuk Persediaan Barang","Y");
        }catch (Exception e){
            logger.error("[BpjsController.createJurnalBillingCase3] Error : " + "[" + e + "]");
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
            billingSystemBoProxy.createJurnal("23",data,"RS03","TEST 6 : Untuk Pembayaran Piutang BPJS","Y");
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

}
