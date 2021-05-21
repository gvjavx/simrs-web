package com.neurix.simrs.transaksi.asesmenspesialis.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenspesialis.bo.AsesmenSpesialisBo;
import com.neurix.simrs.transaksi.asesmenspesialis.model.AsesmenSpesialis;
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

public class AsesmenSpesialisAction {

    public static transient Logger logger = Logger.getLogger(AsesmenSpesialisAction.class);

    public CrudResponse save(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        AsesmenSpesialisBo asesmenSpesialisBo = (AsesmenSpesialisBo) ctx.getBean("asesmenSpesialisBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<AsesmenSpesialis> asesmenSpesialisList = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                AsesmenSpesialis asesmenSpesialis = new AsesmenSpesialis();
                asesmenSpesialis.setParameter(obj.getString("parameter"));
                asesmenSpesialis.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                asesmenSpesialis.setKeterangan(obj.getString("keterangan"));
                if (obj.has("jenis")) {
                    asesmenSpesialis.setJenis(obj.getString("jenis"));
                }

                if (obj.has("tipe")) {
                    asesmenSpesialis.setTipe(obj.getString("tipe"));
                    if("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis") + i + "-" + patten + ".png";
                            String uploadFile = "";
                            if ("ttd".equalsIgnoreCase(obj.getString("tipe"))) {
                                uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                            } else {
                                uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                            }
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                asesmenSpesialis.setJawaban(fileName);
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMsg("Error Parse IO Img, " + e.getMessage());
                        }
                    }else{
                        asesmenSpesialis.setJawaban(obj.getString("jawaban"));
                    }
                } else {
                    asesmenSpesialis.setJawaban(obj.getString("jawaban"));
                }
                if (obj.has("nama_terang")) {
                    asesmenSpesialis.setNamaTerang(obj.getString("nama_terang"));
                }
                if (obj.has("sip")) {
                    asesmenSpesialis.setSip(obj.getString("sip"));
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
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    JSONObject obj = new JSONObject(dataPasien);
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    if (obj != null) {
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
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error Parse JSON, " + e.getMessage());
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

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenSpesialisBo asesmenSpesialisBo = (AsesmenSpesialisBo) ctx.getBean("asesmenSpesialisBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenSpesialis asesmenSpesialis = new AsesmenSpesialis();
                asesmenSpesialis.setIdDetailCheckup(idDetailCheckup);
                asesmenSpesialis.setKeterangan(keterangan);
                asesmenSpesialis.setLastUpdate(time);
                asesmenSpesialis.setLastUpdateWho(userLogin);
                response = asesmenSpesialisBo.saveDelete(asesmenSpesialis);
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
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}
