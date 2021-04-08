package com.neurix.simrs.transaksi.asesmenicu.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenicu.bo.AsesmenIcuBo;
import com.neurix.simrs.transaksi.asesmenicu.model.AsesmenIcu;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
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

    public CrudResponse save(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenIcuBo asesmenIcuBo = (AsesmenIcuBo) ctx.getBean("asesmenIcuBoProxy");
        try {
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
                if(obj.has("informasi")){
                    asesmenIcu.setInformasi(obj.getString("informasi"));
                }
                if(obj.has("nama_terang")){
                    asesmenIcu.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("sip")){
                    asesmenIcu.setSip(obj.getString("sip"));
                }
                if(obj.has("tipe")){
                    if("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))){
                        try {
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
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Found Error when IO Images, "+e.getMessage());
                        }
                    }else{
                        asesmenIcu.setJawaban(obj.getString("jawaban"));
                    }
                    asesmenIcu.setTipe(obj.getString("tipe"));
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
                if("success".equalsIgnoreCase(response.getStatus())){
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    JSONObject object = new JSONObject(dataPasien);
                    if(object != null){
                        StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                        status.setNoCheckup(object.getString("no_checkup"));
                        status.setIdDetailCheckup(object.getString("id_detail_checkup"));
                        status.setIdPasien(object.getString("id_pasien"));
                        status.setIdRekamMedisPasien(object.getString("id_rm"));
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
            response.setStatus("error");
            response.setMsg("Found Error when JSON parse, "+e.getMessage());
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

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien, String date) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenIcuBo asesmenIcuBo = (AsesmenIcuBo) ctx.getBean("asesmenIcuBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenIcu asesmenIcu = new AsesmenIcu();
                asesmenIcu.setIdDetailCheckup(idDetailCheckup);
                asesmenIcu.setKeterangan(keterangan);
                asesmenIcu.setLastUpdate(time);
                asesmenIcu.setLastUpdateWho(userLogin);
                if(date != null && !"".equalsIgnoreCase(date)){
                    asesmenIcu.setCreatedDate(Timestamp.valueOf(date));
                }
                response = asesmenIcuBo.saveDelete(asesmenIcu);
                if("success".equalsIgnoreCase(response.getStatus())){
                    try {
                        JSONObject obj = new JSONObject(dataPasien);
                        RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                        if (obj != null) {
                            StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                            status.setNoCheckup(obj.getString("no_checkup"));
                            status.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                            status.setIdPasien(obj.getString("id_pasien"));
                            status.setIdRekamMedisPasien(obj.getString("id_rm"));
                            status.setLastUpdateWho(userLogin);
                            status.setLastUpdate(time);
                            response = rekamMedikBo.saveEdit(status);
                        }
                    }catch (JSONException e){
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
                    }
                }
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }
    public static Logger getLogger() {
        return logger;
    }
}
