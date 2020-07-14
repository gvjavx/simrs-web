package com.neurix.simrs.transaksi.asesmenspesialis.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenspesialis.bo.AsesmenSpesialisBo;
import com.neurix.simrs.transaksi.asesmenspesialis.model.AsesmenSpesialis;
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

public class AsesmenSpesialisAction {

    public static transient Logger logger = Logger.getLogger(AsesmenSpesialisAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenSpesialisBo asesmenSpesialisBo = (AsesmenSpesialisBo) ctx.getBean("asesmenSpesialisBoProxy");
        JSONArray json = new JSONArray(data);
        List<AsesmenSpesialis> asesmenSpesialisList= new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenSpesialis asesmenSpesialis = new AsesmenSpesialis();
            asesmenSpesialis.setParameter(obj.getString("parameter"));
            asesmenSpesialis.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenSpesialis.setKeterangan(obj.getString("keterangan"));
            if(obj.has("jenis")){
                asesmenSpesialis.setJenis(obj.getString("jenis"));
            }

            if(obj.has("tipe")){
                asesmenSpesialis.setTipe(obj.getString("tipe"));
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                logger.info("Decoded upload data : " + decodedBytes.length);
                String wkt = time.toString();
                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                logger.info("PATTERN :" + patten);
                String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                String uploadFile = "";
                if("ttd".equalsIgnoreCase(obj.getString("tipe"))){
                    uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                }else{
                    uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                }
                logger.info("File save path : " + uploadFile);
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                if (image == null) {
                    logger.error("Buffered Image is null");
                    response.setStatus("error");
                    response.setMsg("Buffered Image is null");
                } else {
                    File f = new File(uploadFile);
                    // write the image
                    ImageIO.write(image, "png", f);
                    asesmenSpesialis.setJawaban(fileName);
                }
            }else{
                asesmenSpesialis.setJawaban(obj.getString("jawaban"));
            }
            asesmenSpesialis.setAction("C");
            asesmenSpesialis.setFlag("Y");
            asesmenSpesialis.setCreatedWho(userLogin);
            asesmenSpesialis.setCreatedDate(time);
            asesmenSpesialis.setLastUpdateWho(userLogin);
            asesmenSpesialis.setLastUpdate(time);
            asesmenSpesialisList.add(asesmenSpesialis);
        }
        try {
            response = asesmenSpesialisBo.saveAdd(asesmenSpesialisList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public List<AsesmenSpesialis> getListDetail(String idDetailCheckup, String keterangan) {
        List<AsesmenSpesialis> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenSpesialisBo asesmenSpesialisBo = (AsesmenSpesialisBo) ctx.getBean("asesmenSpesialisBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenSpesialis asesmenSpesialis = new AsesmenSpesialis();
                asesmenSpesialis.setIdDetailCheckup(idDetailCheckup);
                asesmenSpesialis.setKeterangan(keterangan);
                list = asesmenSpesialisBo.getByCriteria(asesmenSpesialis);
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