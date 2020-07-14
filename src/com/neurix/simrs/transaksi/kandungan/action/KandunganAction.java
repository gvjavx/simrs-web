package com.neurix.simrs.transaksi.kandungan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.bo.KandunganBo;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;
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

public class KandunganAction {

    public static transient Logger logger = Logger.getLogger(KandunganAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
        JSONArray json = new JSONArray(data);
        List<Kandungan> icuList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Kandungan kandungan = new Kandungan();
            kandungan.setParameter(obj.getString("parameter"));
            kandungan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            kandungan.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                kandungan.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                kandungan.setScore(Integer.valueOf(obj.getString("skor")));
            }
            if(obj.has("informasi")){
                if(!"".equalsIgnoreCase(obj.getString("informasi"))){
                    kandungan.setInformasi(obj.getString("informasi"));
                }
            }
            if(obj.has("tipe")){
                if("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                    logger.info("Decoded upload data : " + decodedBytes.length);
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    logger.info("PATTERN :" + patten);
                    String fileName = obj.getString("id_detail_checkup") + "-"+i+"-" + patten + ".png";
                    String uploadFile = "";
                    if("gambar".equalsIgnoreCase(obj.getString("tipe"))){
                        uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                    }else{
                        uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                    }
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                    if (image == null) {
                        logger.error("Buffered Image is null");
                        response.setStatus("error");
                        response.setMsg("Buffered Image is null");
                    } else {
                        File f = new File(uploadFile);
                        // write the image
                        ImageIO.write(image, "png", f);
                        kandungan.setJawaban(fileName);
                    }
                }else{
                    kandungan.setJawaban(obj.getString("jawaban"));
                }
                kandungan.setTipe(obj.getString("tipe"));
            }else{
                kandungan.setJawaban(obj.getString("jawaban"));
            }

            kandungan.setAction("C");
            kandungan.setFlag("Y");
            kandungan.setCreatedWho(userLogin);
            kandungan.setCreatedDate(time);
            kandungan.setLastUpdateWho(userLogin);
            kandungan.setLastUpdate(time);
            icuList.add(kandungan);
        }
        try {
            response = kandunganBo.saveAdd(icuList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public List<Kandungan> getListDetail(String idDetailCheckup, String keterangan) {
        List<Kandungan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Kandungan kandungan = new Kandungan();
                kandungan.setIdDetailCheckup(idDetailCheckup);
                kandungan.setKeterangan(keterangan);
                list = kandunganBo.getByCriteria(kandungan);
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