package com.neurix.simrs.bpjs.eklaim.bo.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class cobaKonvert {
    public static void main(String args[]){
        String text="{\n" +
                " \"metadata\": {\n" +
                " \"code\": 200,\n" +
                " \"message\": \"Ok\"\n" +
                " },\n" +
                " \"response\": {\n" +
                " \"count\": 3,\n" +
                " \"data\": [\n" +
                " [\n" +
                " \"Cholera, unspecified\",\n" +
                " \"A00.9\"\n" +
                " ],\n" +
                " [\n" +
                " \"Cholera due to vibrio cholerae 01, biovar eltor\",\n" +
                " \"A00.1\"\n" +
                " ],\n" +
                " [\n" +
                " \"Cholera due to vibrio cholerae 01, biovar cholerae\",\n" +
                " \"A00.0\"\n" +
                " ]\n" +
                " ]\n" +
                " }\n" +
                "}";
        try {
            JSONObject ex = new JSONObject(text);
            JSONObject response = ex.getJSONObject("response");
            JSONArray data = response.getJSONArray("data");
            int lengthProcedure = data.length();
            for (int i=0;i<lengthProcedure;i++) {
                JSONArray obj= data.getJSONArray(i);
                int length = obj.length();
                for (int j=0;j<length;j++) {
                    String hasil = String.valueOf(obj.get(j));
                    System.out.println(hasil);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
