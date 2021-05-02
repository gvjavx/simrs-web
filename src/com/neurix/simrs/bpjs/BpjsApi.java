package com.neurix.simrs.bpjs;

import com.neurix.common.constant.CommonConstant;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;
import org.springframework.security.crypto.codec.Base64;
/**
 * Created by Toshiba on 21/10/2019.
 */
public class BpjsApi {
    public static void main(String[] args) throws GeneralSecurityException, IOException {

        String secretKey = "7eT69578CF";
        String salt = "0123456789";

        String generateHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey);
        System.out.println("Signature: " + generateHmacSHA256Signature);

        String urlEncodedSign = URLEncoder.encode(generateHmacSHA256Signature, "UTF-8");

        System.out.println("Url encoded value: " + urlEncodedSign);

        Date date70 = Date.valueOf("1970-01-01");
        Long time = System.currentTimeMillis() - date70.getTime();

        System.out.println("Timestamp: " + time);
    }

    public static String generateHmacSHA256Signature(String data, String key) throws GeneralSecurityException {
        byte[] hmacData = null;

        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            hmacData = mac.doFinal(data.getBytes("UTF-8"));

            byte[] bytes = new Base64().encode(hmacData);
            return new String(bytes);

        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static String getBpjsTimestamp(){

        Calendar cal70 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        Calendar curCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal70.set(1970,1,1);

        LocalDateTime ldt = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        int time = (int) ((System.currentTimeMillis()/1000) - ldt.getSecond());
        return String.valueOf(time);

    }

    public static String getSignatureKey() throws GeneralSecurityException, IOException{
        String secretKey = "7eT69578CF";
        String salt = "10356" +"&"+ getBpjsTimestamp();

        String generateHmacSHA256Signature = generateHmacSHA256Signature(salt, secretKey);
        String urlEncodedSign = URLEncoder.encode(generateHmacSHA256Signature, "UTF-8");
//        return urlEncodedSign.toString();
        return generateHmacSHA256Signature.toString();
    }
}


