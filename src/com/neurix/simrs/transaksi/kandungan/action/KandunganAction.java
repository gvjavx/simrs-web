package com.neurix.simrs.transaksi.kandungan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.kandungan.bo.KandunganBo;
import com.neurix.simrs.transaksi.kandungan.bo.PartografBo;
import com.neurix.simrs.transaksi.kandungan.model.Kandungan;
import com.neurix.simrs.transaksi.kandungan.model.Partograf;
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
                if (obj.has("jenis")) {
                    kandungan.setJenis(obj.getString("jenis"));
                }
                if (obj.has("skor")) {
                    kandungan.setScore(Integer.valueOf(obj.getString("skor")));
                }
                if (obj.has("nama_terang")) {
                    kandungan.setNamaTerang(obj.getString("nama_terang"));
                }
                if (obj.has("sip")) {
                    kandungan.setSip(obj.getString("sip"));
                }
                if (obj.has("informasi")) {
                    if (!"".equalsIgnoreCase(obj.getString("informasi"))) {
                        kandungan.setInformasi(obj.getString("informasi"));
                    }
                }
                if (obj.has("tipe")) {
                    if ("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            logger.info("PATTERN :" + patten);
                            String fileName = "";
                            String uploadFile = "";
                            if ("gambar".equalsIgnoreCase(obj.getString("tipe"))) {
                                fileName = obj.getString("id_detail_checkup") + "-" + i + "-" + patten + ".jpg";
                                uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                            } else {
                                fileName = obj.getString("id_detail_checkup") + "-" + i + "-" + patten + ".png";
                                uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                            }
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                if("gambar".equalsIgnoreCase(obj.getString("tipe"))){
                                    if (image == null) {
                                        logger.error("Buffered Image is null");
                                    } else {
                                        CommonUtil.compressImage(image, "png", uploadFile);
                                        kandungan.setJawaban(fileName);
                                    }
                                }else {
                                    File f = new File(uploadFile);
                                    ImageIO.write(image, "png", f);
                                    kandungan.setJawaban(fileName);
                                }
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMsg("Error when IO Images, " + e.getMessage());
                        }
                    } else {
                        kandungan.setJawaban(obj.getString("jawaban"));
                    }
                    kandungan.setTipe(obj.getString("tipe"));
                } else {
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
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    JSONObject obj = new JSONObject(dataPasien);
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
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Error when JSON parse, " + e.getMessage());
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

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien, String date) {
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
                if(date != null && !"".equalsIgnoreCase(date)){
                    kandungan.setCreatedDate(Timestamp.valueOf(date));
                }
                response = kandunganBo.saveDelete(kandungan);
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
                response.setStatus("error");
                response.setMsg("Found Error, "+e.getMessage());
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    public CrudResponse savePartograf(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PartografBo partografBo = (PartografBo) ctx.getBean("partografBoProxy");
        try {
            JSONObject obj = new JSONObject(data);
            if (obj != null) {
                Partograf partograf = new Partograf();
                partograf.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                if(obj.has("waktu")){
                    partograf.setWaktu(obj.getString("waktu"));
                }
                if(obj.has("djj")){
                    partograf.setDjj(obj.getString("djj"));
                }
                if(obj.has("air_ketuban")){
                    partograf.setAirKetuban(obj.getString("air_ketuban"));
                }
                if(obj.has("molase")){
                    partograf.setMolase(obj.getString("molase"));
                }
                if(obj.has("pembukaan")){
                    partograf.setPembukaan(obj.getString("pembukaan"));
                }
                if(obj.has("kontraksi")){
                    partograf.setKontraksi(obj.getString("kontraksi"));
                }
                if(obj.has("lama_kontraksi")){
                    partograf.setLamaKontraksi(obj.getString("lama_kontraksi"));
                }
                if(obj.has("oksitosin")){
                    partograf.setOksitosin(obj.getString("oksitosin"));
                }
                if(obj.has("tetes")){
                    partograf.setTetes(obj.getString("tetes"));
                }
                if(obj.has("obat_cairan")){
                    partograf.setObatCairan(obj.getString("obat_cairan"));
                }
                if(obj.has("nadi")){
                    partograf.setNadi(obj.getString("nadi"));
                }
                if(obj.has("tensi")){
                    partograf.setTensi(obj.getString("tensi"));
                }
                if(obj.has("suhu")){
                    partograf.setSuhu(obj.getString("suhu"));
                }
                if(obj.has("rr")){
                    partograf.setRr(obj.getString("rr"));
                }

                if(obj.has("ttd")){
                    if(!"".equalsIgnoreCase(obj.getString("ttd"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            logger.info("PATTERN :" + patten);
                            String fileName = obj.getString("id_detail_checkup") + "-" + patten + ".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                // write the image
                                ImageIO.write(image, "png", f);
                                partograf.setTtd(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Error when IO Images, "+e.getMessage());
                        }
                    }
                }

                partograf.setAction("C");
                partograf.setFlag("Y");
                partograf.setCreatedWho(userLogin);
                partograf.setCreatedDate(time);
                partograf.setLastUpdateWho(userLogin);
                partograf.setLastUpdate(time);

                try {
                    response = partografBo.saveAdd(partograf);
                    if("success".equalsIgnoreCase(response.getStatus())){
                        RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                        JSONObject objt = new JSONObject(dataPasien);
                        if(objt != null){
                            StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                            status.setNoCheckup(objt.getString("no_checkup"));
                            status.setIdDetailCheckup(objt.getString("id_detail_checkup"));
                            status.setIdPasien(objt.getString("id_pasien"));
                            status.setIdRekamMedisPasien(objt.getString("id_rm"));
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

            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Error when JSON parse, " + e.getMessage());
        }
        return response;
    }

    public List<Partograf> getListDetailPartograf(String idDetailCheckup) {
        List<Partograf> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PartografBo partografBo = (PartografBo) ctx.getBean("partografBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                Partograf partograf = new Partograf();
                partograf.setIdDetailCheckup(idDetailCheckup);
                list = partografBo.getByCriteria(partograf);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public List<Partograf> getListDetailByDate(String idDetailCheckup, String tanggal) {
        List<Partograf> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PartografBo partografBo = (PartografBo) ctx.getBean("partografBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(tanggal)) {
            try {
                list = partografBo.getListByDate(idDetailCheckup, tanggal);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse deletePartograf(String idPatograf, String isKala) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PartografBo partografBo = (PartografBo) ctx.getBean("partografBoProxy");
        KandunganBo kandunganBo = (KandunganBo) ctx.getBean("kandunganBoProxy");
        if (!"".equalsIgnoreCase(idPatograf)) {
            try {
                if("Y".equalsIgnoreCase(isKala)){
                    Kandungan kandungan = new Kandungan();
                    kandungan.setIdAsesmenKandungan(idPatograf);
                    kandungan.setLastUpdate(time);
                    kandungan.setLastUpdateWho(userLogin);
                    response = kandunganBo.saveDeleteById(kandungan);
                }else{
                    Partograf partograf = new Partograf();
                    partograf.setIdPartograf(idPatograf);
                    partograf.setLastUpdate(time);
                    partograf.setLastUpdateWho(userLogin);
                    response = partografBo.saveDelete(partograf);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Found Error, "+e.getMessage());
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

}
