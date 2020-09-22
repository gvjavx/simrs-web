package com.neurix.simrs.transaksi.asesmenrawatinap.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.ImplementasiPerawatBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ImplementasiPerawat;
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

public class AsesmenRawatInapAction {

    public static transient Logger logger = Logger.getLogger(AsesmenRawatInapAction.class);
    private String userLogin = CommonUtil.userLogin();
    private Timestamp time = new Timestamp(System.currentTimeMillis());

    public CrudResponse saveAsesmenRawat(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");

        try {
            JSONArray json = new JSONArray(data);

            List<AsesmenRawatInap> rawatInapList = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                asesmenRawatInap.setParameter(obj.getString("parameter"));
                asesmenRawatInap.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                asesmenRawatInap.setKeterangan(obj.getString("keterangan"));

                if (obj.has("jawaban")) {
                    if (obj.has("tipe")) {
                        if ("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))) {
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
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    File f = new File(uploadFile);
                                    // write the image
                                    ImageIO.write(image, "png", f);
                                    asesmenRawatInap.setJawaban(fileName);
                                }
                            } catch (IOException e) {
                                response.setStatus("error");
                                response.setMsg("Found Error, parse IO Image tidak bisa...!");
                            }
                        } else {
                            asesmenRawatInap.setJawaban(obj.getString("jawaban"));
                        }
                        asesmenRawatInap.setTipe(obj.getString("tipe"));
                    } else {
                        asesmenRawatInap.setJawaban(obj.getString("jawaban"));
                    }
                }

                if (obj.has("jenis")) {
                    asesmenRawatInap.setJenis(obj.getString("jenis"));
                }
                if (obj.has("skor")) {
                    asesmenRawatInap.setSkor(Integer.valueOf(obj.getString("skor")));
                }
                if (obj.has("informasi")) {
                    asesmenRawatInap.setInformasi(obj.getString("informasi"));
                }
                if (obj.has("nama_terang")) {
                    asesmenRawatInap.setNamaTerang(obj.getString("nama_terang"));
                }
                if (obj.has("sip")) {
                    asesmenRawatInap.setSip(obj.getString("sip"));
                }

                asesmenRawatInap.setAction("C");
                asesmenRawatInap.setFlag("Y");
                asesmenRawatInap.setCreatedWho(userLogin);
                asesmenRawatInap.setCreatedDate(time);
                asesmenRawatInap.setLastUpdateWho(userLogin);
                asesmenRawatInap.setLastUpdate(time);
                rawatInapList.add(asesmenRawatInap);
            }

            try {
                response = asesmenRawatInapBo.saveAdd(rawatInapList);
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
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Found Error, parse JSON tidak bisa...!");
        }
        return response;
    }

    public List<AsesmenRawatInap> getListAsesmenRawat(String idDetailCheckup, String keterangan) {
        List<AsesmenRawatInap> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                asesmenRawatInap.setIdDetailCheckup(idDetailCheckup);
                asesmenRawatInap.setKeterangan(keterangan);
                list = asesmenRawatInapBo.getByCriteria(asesmenRawatInap);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String idAsesmen, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
            asesmenRawatInap.setIdDetailCheckup(idDetailCheckup);
            asesmenRawatInap.setKeterangan(keterangan);
            asesmenRawatInap.setLastUpdate(time);
            asesmenRawatInap.setLastUpdateWho(userLogin);
            if(idAsesmen != null && !"".equalsIgnoreCase(idAsesmen)){
                asesmenRawatInap.setIdAsesmenKeperawatanRawatInap(idAsesmen);
            }
            response = asesmenRawatInapBo.saveDelete(asesmenRawatInap);
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
        }else{
            response.setStatus("error");
            response.setMsg("ID detail Checkup tidak ditemukan...!");
        }
        return response;
    }

    public CrudResponse saveImplementasiPerawat(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ImplementasiPerawatBo implementasiPerawatBo = (ImplementasiPerawatBo) ctx.getBean("implementasiPerawatBoProxy");
        try {
            JSONObject object = new JSONObject(data);
            ImplementasiPerawat implementasiPerawat = new ImplementasiPerawat();
            if(object != null){
                implementasiPerawat.setIdDetailCheckup(object.getString("id_detail_checkup"));
                implementasiPerawat.setWaktu(object.getString("waktu"));
                implementasiPerawat.setKeterangan(object.getString("keterangan"));
                implementasiPerawat.setNamaTerang(object.getString("nama_terang"));
                implementasiPerawat.setSip(object.getString("sip"));
                implementasiPerawat.setAction("C");
                implementasiPerawat.setFlag("Y");
                implementasiPerawat.setCreatedDate(time);
                implementasiPerawat.setCreatedWho(userLogin);
                implementasiPerawat.setLastUpdate(time);
                implementasiPerawat.setLastUpdateWho(userLogin);
                if(object.has("ttd")){
                    if(!"".equalsIgnoreCase(object.getString("ttd"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(object.getString("ttd"));
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = object.getString("id_detail_checkup") + "-" + patten + ".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;

                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                implementasiPerawat.setTtd(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg(e.getMessage());
                        }
                    }
                }
                response = implementasiPerawatBo.saveAdd(implementasiPerawat);
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
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    public List<ImplementasiPerawat> getListImplementasiPerawat(String idDetailCheckup) {
        List<ImplementasiPerawat> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ImplementasiPerawatBo implementasiPerawatBo = (ImplementasiPerawatBo) ctx.getBean("implementasiPerawatBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                ImplementasiPerawat implementasiPerawat = new ImplementasiPerawat();
                implementasiPerawat.setIdDetailCheckup(idDetailCheckup);
                list = implementasiPerawatBo.getByCriteria(implementasiPerawat);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDeleteImplementasiPerawat(String idAsesmen, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ImplementasiPerawatBo implementasiPerawatBo = (ImplementasiPerawatBo) ctx.getBean("implementasiPerawatBoProxy");
        if (!"".equalsIgnoreCase(idAsesmen)) {
            ImplementasiPerawat implementasiPerawat = new ImplementasiPerawat();
            implementasiPerawat.setIdImplementasiPerawat(idAsesmen);
            response = implementasiPerawatBo.saveDelete(implementasiPerawat);
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
        }else{
            response.setStatus("error");
            response.setMsg("ID detail Checkup tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}
