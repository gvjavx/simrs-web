package com.neurix.simrs.transaksi.asesmenicu.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.AsesmenIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
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

public class AsesmenIcuAction {

    public static transient Logger logger = Logger.getLogger(AsesmenIcuAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenIcuBo asesmenIcuBo = (AsesmenIcuBo) ctx.getBean("asesmenIcuBoProxy");
        JSONArray json = new JSONArray(data);
        List<AsesmenIcu> icuList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenIcu asesmenIcu = new AsesmenIcu();
            asesmenIcu.setParameter(obj.getString("parameter"));
            asesmenIcu.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenIcu.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                asesmenIcu.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                asesmenIcu.setScore(Integer.valueOf(obj.getString("skor")));
            }
            if(obj.has("tipe")){
                if(obj.getString("tipe") != null && !"".equalsIgnoreCase(obj.getString("tipe"))){
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
                        asesmenIcu.setJawaban(fileName);
                    }
                    asesmenIcu.setTipe(obj.getString("tipe"));
                }
            }else{
                asesmenIcu.setJawaban(obj.getString("jawaban"));
            }

            asesmenIcu.setAction("C");
            asesmenIcu.setFlag("Y");
            asesmenIcu.setCreatedWho(userLogin);
            asesmenIcu.setCreatedDate(time);
            asesmenIcu.setLastUpdateWho(userLogin);
            asesmenIcu.setLastUpdate(time);
            icuList.add(asesmenIcu);
        }
        try {
            response = asesmenIcuBo.saveAdd(icuList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public List<AsesmenIcu> getListDetail(String idDetailCheckup, String keterangan) {
        List<AsesmenIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenIcuBo asesmenIcuBo = (AsesmenIcuBo) ctx.getBean("asesmenIcuBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenIcu asesmenIcu = new AsesmenIcu();
                asesmenIcu.setIdDetailCheckup(idDetailCheckup);
                asesmenIcu.setKeterangan(keterangan);
                list = asesmenIcuBo.getByCriteria(asesmenIcu);
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
