package com.neurix.simrs.transaksi.appendecitomy.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.appendecitomy.bo.AppendecitomyBo;
import com.neurix.simrs.transaksi.appendecitomy.model.Appendecitomy;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
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

public class AppendecitomyAction {

    public static transient Logger logger = Logger.getLogger(AppendecitomyAction.class);

    public CrudResponse saveAppendecitomy(String data) throws JSONException, IOException {
        String tes = ServletActionContext.getRequest().getContextPath();
        logger.info("PATH APA INI :"+tes);
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AppendecitomyBo appendecitomyBo = (AppendecitomyBo) ctx.getBean("appendecitomyBoProxy");
        JSONArray json = new JSONArray(data);
        List<Appendecitomy> appendecitomyList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Appendecitomy appendecitomy = new Appendecitomy();
            appendecitomy.setParameter(obj.getString("parameter"));
            appendecitomy.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            appendecitomy.setKeterangan(obj.getString("keterangan"));
            if (obj.has("jawaban1")) {
                if ("appendecitomy_penyataan".equalsIgnoreCase(obj.getString("keterangan"))) {
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban1"));
                    logger.info("Decoded upload data : " + decodedBytes.length);
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    logger.info("PATTERN :" + patten);
                    String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis") + i + "-" + patten + ".png";
                    String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
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
                        appendecitomy.setJawaban1(fileName);
                    }
                } else {
                    appendecitomy.setJawaban1(obj.getString("jawaban1"));
                }
            }

            if (obj.has("jawaban2")) {
                appendecitomy.setJawaban2(obj.getString("jawaban2"));
            }
            if (obj.has("jenis")) {
                appendecitomy.setJenis(obj.getString("jenis"));
            }
            appendecitomy.setAction("C");
            appendecitomy.setFlag("Y");
            appendecitomy.setCreatedWho(userLogin);
            appendecitomy.setCreatedDate(time);
            appendecitomy.setLastUpdateWho(userLogin);
            appendecitomy.setLastUpdate(time);
            appendecitomyList.add(appendecitomy);

        }

        try {
            response = appendecitomyBo.saveAdd(appendecitomyList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }

        return response;
    }

    public List<Appendecitomy> getListAppendecitomy(String idDetailCheckup, String keterangan) {
        List<Appendecitomy> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AppendecitomyBo appendecitomyBo = (AppendecitomyBo) ctx.getBean("appendecitomyBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Appendecitomy appendecitomy = new Appendecitomy();
                appendecitomy.setIdDetailCheckup(idDetailCheckup);
                appendecitomy.setKeterangan(keterangan);
                list = appendecitomyBo.getByCriteria(appendecitomy);
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