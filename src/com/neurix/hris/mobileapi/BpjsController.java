package com.neurix.hris.mobileapi;

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
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Toshiba on 21/10/2019.
 */
public class BpjsController extends BpjsService implements ModelDriven<String> {

    private String data;
    private String keyword;
    private String userId;
    private String RegTemp;
    private String result;

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
        }
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
    }
    public void listDiagnosa(String key){
        List<DiagnosaResponse> diagnosaResponseList = new ArrayList<>();
        try {
            diagnosaResponseList= bpjsBoProxy.GetDiagnosaByAPIBpjs(key,"RS01");
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
        sepRequest.setNoKartu("0001112230666");
        sepRequest.setTglSep("2017-10-18");
        sepRequest.setPpkPelayanan("0301R001");
        sepRequest.setJnsPelayanan("2");
        sepRequest.setKlsRawat("3");
        sepRequest.setNoMr("123456");
        sepRequest.setAsalRujukan("1");
        sepRequest.setTglRujukan("2017-10-17");
        sepRequest.setNoRujukan("123456");
        sepRequest.setPpkRujukan("00010001");
        sepRequest.setCatatan("test");
        sepRequest.setDiagAwal("A00.1");
        sepRequest.setPoliTujuan("INT");
        sepRequest.setPoliEksekutif("0");
        sepRequest.setCob("0");
        sepRequest.setKatarak("0");
        sepRequest.setLakaLantas("0");
        sepRequest.setPenjamin("");
        sepRequest.setTglKejadian("");
        sepRequest.setKeterangan("");
        sepRequest.setSuplesi("");
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
    @Override
    public String getModel() {
        return result;
    }

}
