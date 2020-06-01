package com.neurix.simrs.transaksi.fisioterapi.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.fisioterapi.bo.FisioterapiBo;
import com.neurix.simrs.transaksi.fisioterapi.bo.MonitoringFisioterapiBo;
import com.neurix.simrs.transaksi.fisioterapi.model.Fisioterapi;
import com.neurix.simrs.transaksi.fisioterapi.model.MonitoringFisioterapi;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FisioterapiAction {

    public static transient Logger logger = Logger.getLogger(FisioterapiAction.class);

    public CrudResponse saveFisio(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FisioterapiBo fisioterapiBo = (FisioterapiBo) ctx.getBean("fisioterapiBoProxy");
        JSONArray json = new JSONArray(data);
        List<Fisioterapi> list = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            Fisioterapi fisioterapi = new Fisioterapi();
            fisioterapi.setParameter(obj.getString("parameter"));

            if("Scala Nyeri Paint".equalsIgnoreCase(obj.getString("parameter"))){
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                logger.info("Decoded upload data : " + decodedBytes.length);
                String wkt = time.toString();
                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                logger.info("PATTERN :" + patten);
                String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("keterangan")+i+ "-" + patten + ".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;

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
                    fisioterapi.setJawaban(fileName);
                }
            }else{
                fisioterapi.setJawaban(obj.getString("jawaban"));
            }

            fisioterapi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            fisioterapi.setKeterangan(obj.getString("keterangan"));
            fisioterapi.setAction("C");
            fisioterapi.setFlag("Y");
            fisioterapi.setCreatedWho(userLogin);
            fisioterapi.setCreatedDate(time);
            fisioterapi.setLastUpdateWho(userLogin);
            fisioterapi.setLastUpdate(time);
            list.add(fisioterapi);
        }

        try {
            response = fisioterapiBo.saveAdd(list);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }
        return response;
    }

    public CrudResponse saveMonFisio(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonitoringFisioterapiBo monitoringFisioterapiBo = (MonitoringFisioterapiBo) ctx.getBean("monitoringFisioterapiBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            MonitoringFisioterapi mon = new MonitoringFisioterapi();
            mon.setTanggal(Date.valueOf(obj.getString("tanggal")));
            mon.setTindakan(obj.getString("tindakan"));
            mon.setKeterangan(obj.getString("keterangan"));
            mon.setFisioterapi(obj.getString("fisioterapi"));
            mon.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            mon.setAction("C");
            mon.setFlag("Y");
            mon.setCreatedWho(userLogin);
            mon.setCreatedDate(time);
            mon.setLastUpdateWho(userLogin);
            mon.setLastUpdate(time);

            try {
                response = monitoringFisioterapiBo.saveAdd(mon);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }

        }
        return response;
    }

    public List<Fisioterapi> getListFisioTerapi(String idDetailCheckup, String keterangan) {
        List<Fisioterapi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FisioterapiBo fisioterapiBo = (FisioterapiBo) ctx.getBean("fisioterapiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Fisioterapi fisioterapi = new Fisioterapi();
                fisioterapi.setIdDetailCheckup(idDetailCheckup);
                fisioterapi.setKeterangan(keterangan);
                list = fisioterapiBo.getByCriteria(fisioterapi);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public List<MonitoringFisioterapi> getListMonFisio(String idDetailCheckup) {
        List<MonitoringFisioterapi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonitoringFisioterapiBo monitoringFisioterapiBo = (MonitoringFisioterapiBo) ctx.getBean("monitoringFisioterapiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                MonitoringFisioterapi fisioterapi = new MonitoringFisioterapi();
                fisioterapi.setIdDetailCheckup(idDetailCheckup);
                list = monitoringFisioterapiBo.getByCriteria(fisioterapi);
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
