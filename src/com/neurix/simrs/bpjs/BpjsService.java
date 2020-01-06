package com.neurix.simrs.bpjs;

import com.neurix.common.constant.CommonConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;



/**
 * Created by Toshiba on 21/10/2019.
 */
public class BpjsService {

    public Map<String,String> headers = new HashMap<>();

    public  Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public static String getBpjsTimestamp(){

        Calendar cal70 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal70.set(1970, Calendar.JANUARY,1);

        LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        int time = (int) ((System.currentTimeMillis()/1000) - ldt.getSecond());
        return String.valueOf(time);

    }

    public String getSignatureKey(String constId,String secretKey,String timestamp) throws GeneralSecurityException{
        String salt = constId +"&"+ timestamp;

        return generateHmacSHA256Signature(salt, secretKey);
    }

    private String generateHmacSHA256Signature(String salt, String key) throws GeneralSecurityException{
        byte[] hmacData = null;

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        hmacData = mac.doFinal(salt.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = new Base64().encode(hmacData);
        return new String(bytes);

    }

    public String GET(String feature,String consId,String secretKey) throws IOException, GeneralSecurityException {

        StringBuffer result = new StringBuffer();
        String consTimestamp=getBpjsTimestamp();
        String signature=getSignatureKey(consId,secretKey,consTimestamp);

        URL obj = new URL(feature);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        con.setDoOutput( true );
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        con.setRequestProperty("X-cons-id", consId);
        con.setRequestProperty("X-Timestamp", consTimestamp);
        con.setRequestProperty("X-Signature", signature);

        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        while ((line = rd.readLine()) != null){
            result.append(line);
        }

        return result.toString();
    }
    public String GETRequest(String feature,JSONObject request,String consId,String secretKey) throws IOException, GeneralSecurityException {
        String consTimestamp=getBpjsTimestamp();
        String signature=getSignatureKey(consId,secretKey,consTimestamp);
        URL obj = new URL(feature);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        con.setDoOutput( true );
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        con.setRequestProperty("X-cons-id", consId);
        con.setRequestProperty("X-Timestamp", consTimestamp);
        con.setRequestProperty("X-Signature", signature);

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(request.toString());
        wr.flush();

        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return "" + sb.toString();
        } else {
            return con.getResponseMessage();
        }
    }
}
