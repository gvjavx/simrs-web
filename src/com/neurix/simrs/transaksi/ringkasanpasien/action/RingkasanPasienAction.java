package com.neurix.simrs.transaksi.ringkasanpasien.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import com.neurix.simrs.transaksi.ringkasanpasien.bo.RingkasanPasienBo;
import com.neurix.simrs.transaksi.ringkasanpasien.model.RingkasanPasien;
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

public class RingkasanPasienAction {

    public static transient Logger logger = Logger.getLogger(RingkasanPasienAction.class);

    public CrudResponse save(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RingkasanPasienBo ringkasanPasienBo = (RingkasanPasienBo) ctx.getBean("ringkasanPasienBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<RingkasanPasien> ringkasanPasienList = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                RingkasanPasien ringkasanPasien = new RingkasanPasien();
                ringkasanPasien.setParameter(obj.getString("parameter"));
                ringkasanPasien.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                ringkasanPasien.setKeterangan(obj.getString("keterangan"));
                if (obj.has("jawaban")) {
                    if ("ttd".equalsIgnoreCase(obj.getString("jenis"))) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            logger.info("PATTERN :" + patten);
                            String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("keterangan") + i + "-" + patten + ".png";
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
                                ringkasanPasien.setJawaban(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("Error");
                            response.setMsg("Found Error " + e.getMessage());
                        }
                    } else {
                        ringkasanPasien.setJawaban(obj.getString("jawaban"));
                    }
                }

                if (obj.has("jenis")) {
                    ringkasanPasien.setJenis(obj.getString("jenis"));
                }
                ringkasanPasien.setAction("C");
                ringkasanPasien.setFlag("Y");
                ringkasanPasien.setCreatedWho(userLogin);
                ringkasanPasien.setCreatedDate(time);
                ringkasanPasien.setLastUpdateWho(userLogin);
                ringkasanPasien.setLastUpdate(time);
                ringkasanPasienList.add(ringkasanPasien);

            }

            try {
                response = ringkasanPasienBo.saveAdd(ringkasanPasienList);
                if("success".equalsIgnoreCase(response.getStatus())){
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    JSONObject obj = new JSONObject(dataPasien);
                    if(obj != null){
                        StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                        status.setNoCheckup(obj.getString("no_checkup"));
                        status.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                        status.setIdPasien(obj.getString("id_pasien"));
                        status.setIdRekamMedisPasien(obj.getString("id_rm"));
                        status.setIsPengisian("Y");
                        status.setAction("C");
                        status.setFlag("Y");
                        status.setCreatedWho(userLogin);
                        status.setCreatedDate(time);
                        status.setLastUpdateWho(userLogin);
                        status.setLastUpdate(time);
                        response = rekamMedikBo.saveAdd(status);
                    }
                }
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (JSONException e){
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
        }
        return response;
    }

    public List<RingkasanPasien> getListDetail(String idDetailCheckup, String keterangan) {
        List<RingkasanPasien> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RingkasanPasienBo ringkasanPasienBo = (RingkasanPasienBo) ctx.getBean("ringkasanPasienBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                RingkasanPasien ringkasanPasien = new RingkasanPasien();
                ringkasanPasien.setIdDetailCheckup(idDetailCheckup);
                ringkasanPasien.setKeterangan(keterangan);
                list = ringkasanPasienBo.getByCriteria(ringkasanPasien);
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