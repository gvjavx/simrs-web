package com.neurix.hris.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.hris.mobileapi.model.simrs.Poli;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.neurix.simrs.bpjs.BpjsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static transient Logger logger = Logger.getLogger(TestBpjsController.class);

    public TestBpjsController() throws GeneralSecurityException, IOException {
    }

    public HttpHeaders index() throws IOException, GeneralSecurityException, JSONException {
        getPoli("");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public void getPoli(String query) throws GeneralSecurityException, IOException, JSONException {

        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/referensi/poli/gigi";
        String result = GET(feature);

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
    }

    @Override
    public Object getModel() {
        return listOfPoli.isEmpty() ? model : listOfPoli;
    }

}
