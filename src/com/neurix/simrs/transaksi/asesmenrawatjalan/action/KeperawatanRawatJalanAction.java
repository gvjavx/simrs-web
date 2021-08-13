package com.neurix.simrs.transaksi.asesmenrawatjalan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatjalan.bo.KeperawatanRawatJalanBo;
import com.neurix.simrs.transaksi.asesmenrawatjalan.model.KeperawatanRawatJalan;
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

public class KeperawatanRawatJalanAction {

    public static transient Logger logger = Logger.getLogger(KeperawatanRawatJalanAction.class);
    private String userLogin = CommonUtil.userLogin();
    private Timestamp time = new Timestamp(System.currentTimeMillis());

    public CrudResponse saveAsesmenRawat(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");

        try {
            JSONArray json = new JSONArray(data);
            JSONObject pasienObject = new JSONObject(dataPasien);
            String noCheckup = null;
            if(pasienObject.has("no_checkup")){
                noCheckup = pasienObject.getString("no_checkup");
            }
            List<KeperawatanRawatJalan> keperawatanRawatJalanList = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                keperawatanRawatJalan.setParameter(obj.getString("parameter"));
                keperawatanRawatJalan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                keperawatanRawatJalan.setKeterangan(obj.getString("keterangan"));

                if(obj.has("jawaban")){
                    if(obj.has("tipe")){
                        if("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))){
                            try {
                                BASE64Decoder decoder = new BASE64Decoder();
                                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                                String wkt = time.toString();
                                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                                String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                                String uploadFile = "";
                                if("ttd".equalsIgnoreCase(obj.getString("tipe"))){
                                    uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                                }else{
                                    uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                                }

                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                                if (image == null) {
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    File f = new File(uploadFile);
                                    // write the image
                                    ImageIO.write(image, "png", f);
                                    keperawatanRawatJalan.setJawaban(fileName);
                                }
                            }catch (IOException e){
                                response.setStatus("error");
                                response.setMsg("Found Error when pasrse object to images, "+e.getMessage());
                            }
                        }else{
                            keperawatanRawatJalan.setJawaban(obj.getString("jawaban"));
                        }
                        keperawatanRawatJalan.setTipe(obj.getString("tipe"));
                    }else{
                        keperawatanRawatJalan.setJawaban(obj.getString("jawaban"));
                    }
                }

                if(obj.has("jenis")){
                    keperawatanRawatJalan.setJenis(obj.getString("jenis"));
                }
                if(obj.has("skor")){
                    keperawatanRawatJalan.setScore(Integer.valueOf(obj.getString("skor")));
                }
                if(obj.has("nama_terang")){
                    keperawatanRawatJalan.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("sip")){
                    keperawatanRawatJalan.setSip(obj.getString("sip"));
                }

                keperawatanRawatJalan.setAction("C");
                keperawatanRawatJalan.setFlag("Y");
                keperawatanRawatJalan.setCreatedWho(userLogin);
                keperawatanRawatJalan.setCreatedDate(time);
                keperawatanRawatJalan.setLastUpdateWho(userLogin);
                keperawatanRawatJalan.setLastUpdate(time);
                keperawatanRawatJalan.setNoCheckup(noCheckup);
                keperawatanRawatJalanList.add(keperawatanRawatJalan);
            }

            try {
                response = keperawatanRawatJalanBo.saveAdd(keperawatanRawatJalanList);
                if("success".equalsIgnoreCase(response.getStatus())){
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    if(pasienObject != null){
                        StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                        status.setNoCheckup(pasienObject.getString("no_checkup"));
                        status.setIdDetailCheckup(pasienObject.getString("id_detail_checkup"));
                        status.setIdPasien(pasienObject.getString("id_pasien"));
                        status.setIdRekamMedisPasien(pasienObject.getString("id_rm"));
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
        }catch (Exception e){
            response.setStatus("error");
            response.setMsg("Found Error when pasrse json, "+e.getMessage());
        }

        return response;
    }

    public List<KeperawatanRawatJalan> getListAsesmenRawat(String noCheckup, String keterangan) {
        List<KeperawatanRawatJalan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");
        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                keperawatanRawatJalan.setNoCheckup(noCheckup);
                keperawatanRawatJalan.setKeterangan(keterangan);
                list = keperawatanRawatJalanBo.getByCriteria(keperawatanRawatJalan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                keperawatanRawatJalan.setIdDetailCheckup(idDetailCheckup);
                keperawatanRawatJalan.setKeterangan(keterangan);
                keperawatanRawatJalan.setLastUpdate(time);
                keperawatanRawatJalan.setLastUpdateWho(userLogin);
                response = keperawatanRawatJalanBo.saveDelete(keperawatanRawatJalan);
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

    public CrudResponse saveTtdResumenMeis(String data){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KeperawatanRawatJalanBo keperawatanRawatJalanBo = (KeperawatanRawatJalanBo) ctx.getBean("keperawatanRawatJalanBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<KeperawatanRawatJalan> keperawatanRawatJalanList = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                KeperawatanRawatJalan keperawatanRawatJalan = new KeperawatanRawatJalan();
                keperawatanRawatJalan.setParameter(obj.getString("parameter"));
                keperawatanRawatJalan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                keperawatanRawatJalan.setKeterangan(obj.getString("keterangan"));
                keperawatanRawatJalan.setNoCheckup(obj.getString("no_checkup"));
                if(obj.has("jawaban")){
                    if(obj.has("tipe")){
                        if("ttd".equalsIgnoreCase(obj.getString("tipe"))){
                            try {
                                BASE64Decoder decoder = new BASE64Decoder();
                                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                                String wkt = time.toString();
                                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                                String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                                if (image == null) {
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    File f = new File(uploadFile);
                                    ImageIO.write(image, "png", f);
                                    keperawatanRawatJalan.setJawaban(fileName);
                                }
                            }catch (IOException e){
                                response.setStatus("error");
                                response.setMsg("Found Error when pasrse object to images, "+e.getMessage());
                            }
                        }else{
                            keperawatanRawatJalan.setJawaban(obj.getString("jawaban"));
                        }
                        keperawatanRawatJalan.setTipe(obj.getString("tipe"));
                    }else{
                        keperawatanRawatJalan.setJawaban(obj.getString("jawaban"));
                    }
                }

                if(obj.has("jenis")){
                    keperawatanRawatJalan.setJenis(obj.getString("jenis"));
                }

                keperawatanRawatJalan.setAction("C");
                keperawatanRawatJalan.setFlag("Y");
                keperawatanRawatJalan.setCreatedWho(userLogin);
                keperawatanRawatJalan.setCreatedDate(time);
                keperawatanRawatJalan.setLastUpdateWho(userLogin);
                keperawatanRawatJalan.setLastUpdate(time);
                keperawatanRawatJalanList.add(keperawatanRawatJalan);
            }

            try {
                response = keperawatanRawatJalanBo.saveAdd(keperawatanRawatJalanList);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (Exception e){
            response.setStatus("error");
            response.setMsg("Found Error when pasrse json, "+e.getMessage());
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}
