package com.neurix.hris.mobileapi;

import com.neurix.hris.mobileapi.model.simrs.Poli;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.bo.model.KlaimRequest;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.PoliResponse;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.neurix.simrs.bpjs.BpjsService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Toshiba on 21/10/2019.
 */
public class TestBpjsController extends BpjsService implements ModelDriven<Object> {

    private Poli model;
    private List<Poli> listOfPoli = new ArrayList<>();
    private BpjsBo bpjsBoProxy;
    private EklaimBo eklaimBoProxy;

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

    private static transient Logger logger = Logger.getLogger(TestBpjsController.class);

    public TestBpjsController() throws GeneralSecurityException, IOException {
    }

    public HttpHeaders index() {
        insertEklaim();
        //        getPoli("");
        //        sendSep();
        //        TindakanBpjs tindakanBpjs = new TindakanBpjs();
        //        GetTindakanByAPIBpjs(tindakanBpjs);
        return new DefaultHttpHeaders("index").disableCaching();
    }

    /*public void getPoli(String query) throws GeneralSecurityException, IOException, JSONException {

        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/poli/gigi";
        String result = GET(feature,"KD01");
        JSONObject myResponseCheck = new JSONObject(result);
        JSONObject response = myResponseCheck.getJSONObject("response");
        JSONArray arrResponse = response.getJSONArray("poli");

        int length = arrResponse.length();
        for (int i=0;i<length;i++){
            JSONObject obj = arrResponse.getJSONObject(i);
            Poli poli = new Poli();
            poli.setKode(obj.getString("kode"));
            poli.setNama(obj.getString("nama"));
            listOfPoli.add(poli);
        }
    }*/

    /*public void GetTindakanByAPIBpjs(TindakanBpjs bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getListEntityJenisTindakan] Start >>>>>>>");
        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_PCARE + "/tindakan/kdTkp/10/0/3";
        String result = null;
        try {
            result = GET(feature);
            JSONObject myResponseCheck = new JSONObject(result);
            JSONObject response = myResponseCheck.getJSONObject("response");
            JSONArray arrResponse = response.getJSONArray("procedure");


            int length = arrResponse.length();
            for (int i=0;i<length;i++){
                JSONObject obj = arrResponse.getJSONObject(i);
                ImSimrsTindakanBpjsEntity tindakanBpjs = new ImSimrsTindakanBpjsEntity();
                tindakanBpjs.setKodeTindakanBpjs(obj.getString("kode"));
                tindakanBpjs.setNamaTindakanBpjs(obj.getString("nama"));

//                tindakanBpjs.setIdTindakanBpjs(tindakanBpjsDao.getNextTindakanBpjsId());
                tindakanBpjs.setFlag(bean.getFlag());
                tindakanBpjs.setAction(bean.getAction());
                tindakanBpjs.setCreatedDate(bean.getCreatedDate());
                tindakanBpjs.setCreatedWho(bean.getCreatedWho());
                tindakanBpjs.setLastUpdate(bean.getLastUpdate());
                tindakanBpjs.setLastUpdateWho(bean.getLastUpdateWho());

//                tindakanBpjsDao.addAndSave(tindakanBpjs);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        logger.info("[TindakanBoImpl.getListEntityJenisTindakan] End <<<<<<<");
    }*/

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
            logger.error("[TestBpjsController.sendSep] Error : " + "[" + e + "]");
        }
    }
    public void listPoli(){
        List<PoliResponse> poliResponseList = new ArrayList<>();
        try {
            poliResponseList= bpjsBoProxy.GetPoliByAPIBpjs("gigi","RS01");
        }catch (Exception e){
            logger.error("[TestBpjsController.sendSep] Error : " + "[" + e + "]");
        }
        for (PoliResponse poliResponse : poliResponseList){
            logger.info(poliResponse.getNamaPoliBpjs());
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
            logger.error("[TestBpjsController.sendSep] Error : " + "[" + e + "]");
        }
    }

    @Override
    public Object getModel() {
        return listOfPoli.isEmpty() ? model : listOfPoli;
    }

}
