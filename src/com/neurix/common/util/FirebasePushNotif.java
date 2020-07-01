package com.neurix.common.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class FirebasePushNotif {

    public static Boolean sendNotificationFirebase(String tokenId, String title, String body, String CLICK_ACTION, String os, boolean isDataOnly){
        Integer responseCode = 0;
        Properties properties = new Properties();


        try {
            properties.load(CommonUtil.class.getClassLoader().getResourceAsStream("firebase.properties"));
            String authKey = properties.getProperty("firebase.auth.key");
            String FMCurl = properties.getProperty("firebase.url");

            URL url = new URL(FMCurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + authKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject data = new JSONObject();
            JSONObject info = new JSONObject();
            data.put("to", tokenId.trim());
            data.put("notification", info);
            info.put("title", title);
            info.put("body", body);
            info.put("sound", "default");
            info.put("click_action", CLICK_ACTION);
//            info.put("show_in_foreground", true);
            info.put("priority", "high");
            //info.put("click_action", "MAINACTIVITY");
            if (os.equalsIgnoreCase("Android")) {
                info.put("android_channel_id", "notif-channel");
            }


            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data.toString());
            wr.flush();
            wr.close();

            responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (responseCode == 200){
            return true;
        }else {
            return false;
        }
    }
}
