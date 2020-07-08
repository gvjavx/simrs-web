package com.neurix.simrs.transaksi.hemodialisa.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.HemodialisaBo;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
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

public class HemodialisaAction {

    public static transient Logger logger = Logger.getLogger(HemodialisaAction.class);

    public CrudResponse saveHemodialisa(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        JSONArray json = new JSONArray(data);
        List<Hemodialisa> hemodialisaList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Hemodialisa hemodialisa = new Hemodialisa();
            hemodialisa.setParameter(obj.getString("parameter"));
            hemodialisa.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            hemodialisa.setKeterangan(obj.getString("keterangan"));
            if(obj.has("is_ttd")){
                if("Y".equalsIgnoreCase(obj.getString("is_ttd"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban1"));
                    logger.info("Decoded upload data : " + decodedBytes.length);
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    logger.info("PATTERN :" + patten);
                    String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                    String uploadFile = "";
                    if("Scala Nyeri Paint".equalsIgnoreCase(obj.getString("parameter"))){
                        uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                        hemodialisa.setIsTtd("G");
                    }else{
                        uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                        hemodialisa.setIsTtd("Y");
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
                        hemodialisa.setJawaban1(fileName);
                    }
                }
            }else{
                hemodialisa.setJawaban1(obj.getString("jawaban1"));
            }

            if(obj.has("jawaban2")){
                hemodialisa.setJawaban2(obj.getString("jawaban2"));
            }
            if(obj.has("jenis")){
                hemodialisa.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                hemodialisa.setSkor(Integer.valueOf(obj.getString("skor")));
            }

            hemodialisa.setAction("C");
            hemodialisa.setFlag("Y");
            hemodialisa.setCreatedWho(userLogin);
            hemodialisa.setCreatedDate(time);
            hemodialisa.setLastUpdateWho(userLogin);
            hemodialisa.setLastUpdate(time);
            hemodialisaList.add(hemodialisa);
        }

        try {
            response = hemodialisaBo.saveAdd(hemodialisaList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public List<Hemodialisa> getListHemodialisa(String idDetailCheckup, String keterangan) {
        List<Hemodialisa> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Hemodialisa hemodialisa = new Hemodialisa();
                hemodialisa.setIdDetailCheckup(idDetailCheckup);
                hemodialisa.setKeterangan(keterangan);
                list = hemodialisaBo.getByCriteria(hemodialisa);
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