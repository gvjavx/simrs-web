package com.neurix.simrs.transaksi.edukasipasien.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.edukasipasien.bo.EdukasiPasienBo;
import com.neurix.simrs.transaksi.edukasipasien.model.EdukasiPasien;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EdukasiPasienAction {

    public static transient Logger logger = Logger.getLogger(EdukasiPasienAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        EdukasiPasienBo edukasiPasienBo = (EdukasiPasienBo) ctx.getBean("edukasiPasienBoProxy");
        JSONArray json = new JSONArray(data);

        List<EdukasiPasien> rawatInapList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            EdukasiPasien edukasiPasien = new EdukasiPasien();
            edukasiPasien.setIdDetailCheckup(obj.getString("id_detail_checkup"));

            if(obj.has("durasi")){
                edukasiPasien.setDurasi(obj.getString("durasi"));
            }
            if(obj.has("edukator")){
                if(!"".equalsIgnoreCase(obj.getString("edukator"))){
                    edukasiPasien.setEdukator(obj.getString("edukator"));
                }
            }
            if(obj.has("edukasi")){
                edukasiPasien.setEdukasi(obj.getString("edukasi"));
            }
            if(obj.has("pemahaman_awal")){
                edukasiPasien.setPemahamanAwal(obj.getString("pemahaman_awal"));
            }
            if(obj.has("metode_edukasi")){
                edukasiPasien.setMetodeEdukasi(obj.getString("metode_edukasi"));
            }
            if(obj.has("media_edukasi")){
                edukasiPasien.setMediaEdukasi(obj.getString("media_edukasi"));
            }
            if(obj.has("evaluasi")){
                edukasiPasien.setEvaluasi(obj.getString("evaluasi"));
            }
            if(obj.has("tipe")){
                edukasiPasien.setTipe(obj.getString("tipe"));
            }

            edukasiPasien.setKeterangan(obj.getString("keterangan"));

            if (obj.has("ttd_pasien")) {
                if(!"".equalsIgnoreCase(obj.getString("ttd_pasien"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd_pasien"));
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    String fileName = obj.getString("id_detail_checkup") + "-" + "ttd_pasien" + i + "-" + patten + ".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        response.setStatus("error");
                        response.setMsg("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);

                        ImageIO.write(image, "png", f);
                        edukasiPasien.setTtdPasien(fileName);
                    }
                }
            }

            if (obj.has("ttd_staf")) {
                if(!"".equalsIgnoreCase(obj.getString("ttd_staf"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd_staf"));
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    String fileName = obj.getString("id_detail_checkup") + "-" + "ttd_staf" + i + "-" + patten + ".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        response.setStatus("error");
                        response.setMsg("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);

                        ImageIO.write(image, "png", f);
                        edukasiPasien.setTtdStaf(fileName);
                    }
                }
            }

            edukasiPasien.setAction("C");
            edukasiPasien.setFlag("Y");
            edukasiPasien.setCreatedWho(userLogin);
            edukasiPasien.setCreatedDate(time);
            edukasiPasien.setLastUpdateWho(userLogin);
            edukasiPasien.setLastUpdate(time);
            rawatInapList.add(edukasiPasien);
        }

        try {
            response = edukasiPasienBo.saveAdd(rawatInapList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }

        return response;
    }

    public List<EdukasiPasien> getListDetail(String idDetailCheckup, String keterangan) {
        List<EdukasiPasien> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        EdukasiPasienBo edukasiPasienBo = (EdukasiPasienBo) ctx.getBean("edukasiPasienBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                EdukasiPasien edukasiPasien = new EdukasiPasien();
                edukasiPasien.setIdDetailCheckup(idDetailCheckup);
                edukasiPasien.setKeterangan(keterangan);
                list = edukasiPasienBo.getByCriteria(edukasiPasien);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public static Logger getLogger() {
        return logger;
    }
}
