package com.neurix.simrs.transaksi.kandungan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.bo.KandunganBo;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;
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

public class KandunganAction {

    public static transient Logger logger = Logger.getLogger(KandunganAction.class);
    private String userLogin = CommonUtil.userLogin();
    private Timestamp time = new Timestamp(System.currentTimeMillis());

    public CrudResponse save(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
        try {
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
                if(obj.has("nama_terang")){
                    kandungan.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("sip")){
                    kandungan.setSip(obj.getString("sip"));
                }
                if(obj.has("informasi")){
                    if(!"".equalsIgnoreCase(obj.getString("informasi"))){
                        kandungan.setInformasi(obj.getString("informasi"));
                    }
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
                                kandungan.setJawaban(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Error when IO Images, "+e.getMessage());
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
            response.setStatus("error");
            response.setMsg("Error when JSON parse, "+e.getMessage());
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

    public CrudResponse delete(String idDetailCheckup, String keterangan) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Kandungan kandungan = new Kandungan();
                kandungan.setIdDetailCheckup(idDetailCheckup);
                kandungan.setKeterangan(keterangan);
                kandungan.setLastUpdate(time);
                kandungan.setLastUpdateWho(userLogin);
                response = kandunganBo.saveDelete(kandungan);
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
