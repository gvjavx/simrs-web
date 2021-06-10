package com.neurix.simrs.transaksi.asesmengizi.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenAsuhanGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.bo.AsesmenMonitoringGiziBo;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenAsuhanGizi;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenGizi;
import com.neurix.simrs.transaksi.asesmengizi.model.AsesmenMonitoringGizi;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
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

public class AsesmenGiziAction {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);

    public CrudResponse saveAsesmen(String data, String dataPasien){
        logger.info("[AsesmenGiziAction.saveAsesmen] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenGiziBo asesmenGiziBo = (AsesmenGiziBo) ctx.getBean("asesmenGiziBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<AsesmenGizi> giziList = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                AsesmenGizi asesmenGizi = new AsesmenGizi();
                asesmenGizi.setNoCheckup(obj.getString("no_checkup"));
                asesmenGizi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                asesmenGizi.setParameter(obj.getString("parameter"));
                asesmenGizi.setKeterangan(obj.getString("keterangan"));
                if(obj.has("jenis")){
                    asesmenGizi.setJenis(obj.getString("jenis"));
                }
                if(obj.has("nama_terang")){
                    asesmenGizi.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("sip")){
                    asesmenGizi.setSip(obj.getString("sip"));
                }
                if(obj.has("skor")){
                    if(obj.getString("skor") != null && !"".equalsIgnoreCase(obj.getString("skor"))){
                        asesmenGizi.setSkor(Integer.valueOf(obj.getString("skor")));
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
                                asesmenGizi.setJawaban(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Found Error when IO Images, "+e.getMessage());
                        }
                    }else{
                        asesmenGizi.setJawaban(obj.getString("jawaban"));
                    }
                    asesmenGizi.setTipe(obj.getString("tipe"));
                }else{
                    asesmenGizi.setJawaban(obj.getString("jawaban"));
                }
                asesmenGizi.setAction("C");
                asesmenGizi.setFlag("Y");
                asesmenGizi.setCreatedWho(userLogin);
                asesmenGizi.setCreatedDate(time);
                asesmenGizi.setLastUpdateWho(userLogin);
                asesmenGizi.setLastUpdate(time);
                giziList.add(asesmenGizi);
            }
            try {
                asesmenGiziBo.saveAdd(giziList);
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
                    rekamMedikBo.saveAdd(status);
                    response.setStatus("success");
                    response.setMsg("OK");
                }
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (Exception e){
            response.setStatus("error");
            response.setMsg("Found Error when JSON parse, "+e.getMessage());
        }
        logger.info("[AsesmenGiziAction.saveAsesmen] Start >>>>>>>");
        return response;
    }

    public CrudResponse saveAsuhan(String data, String dataPasien){
        logger.info("[AsesmenGiziAction.saveAsesmen] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenAsuhanGiziBo asesmenAsuhanGiziBo = (AsesmenAsuhanGiziBo) ctx.getBean("asesmenAsuhanGiziBoProxy");
        try {
            JSONObject obj = new JSONObject(data);
            AsesmenAsuhanGizi asesmenGizi = new AsesmenAsuhanGizi();
            asesmenGizi.setNoCheckup(obj.getString("no_checkup"));
            asesmenGizi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenGizi.setAsesmen(obj.getString("asesmen"));
            asesmenGizi.setDiagnosa(obj.getString("diagnosa"));
            asesmenGizi.setIntervensi(obj.getString("intervensi"));
            asesmenGizi.setRencana(obj.getString("rencana"));
            asesmenGizi.setEdukasi(obj.getString("edukasi"));
            asesmenGizi.setNamaDokter(obj.getString("nama_dokter"));
            asesmenGizi.setSipDokter(obj.getString("sip_dokter"));
            if(obj.has("ttd_dokter")){
                if(obj.getString("ttd_dokter") != null && !"".equalsIgnoreCase(obj.getString("ttd_dokter"))){
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd_dokter"));
                        String wkt = time.toString();
                        String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                        String fileName = obj.getString("id_detail_checkup") + "-ttd_dokter-" + patten + ".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                        if (image == null) {
                            logger.error("Buffered Image is null");
                            response.setStatus("error");
                            response.setMsg("Buffered Image is null");
                            return response;
                        } else {
                            File f = new File(uploadFile);
                            ImageIO.write(image, "png", f);
                            asesmenGizi.setTtdDokter(fileName);
                        }
                    }catch (IOException e){
                        response.setStatus("error");
                        response.setMsg("Found Error when IO Images, "+e.getMessage());
                    }
                }
            }
            asesmenGizi.setAction("C");
            asesmenGizi.setFlag("Y");
            asesmenGizi.setCreatedWho(userLogin);
            asesmenGizi.setCreatedDate(time);
            asesmenGizi.setLastUpdateWho(userLogin);
            asesmenGizi.setLastUpdate(time);
            try {
                asesmenAsuhanGiziBo.saveAdd(asesmenGizi);
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
                    rekamMedikBo.saveAdd(status);
                    response.setStatus("success");
                    response.setMsg("OK");
                }
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (Exception e){
            response.setStatus("error");
            response.setMsg("Found Error when JSON parse, "+e.getMessage());
        }
        logger.info("[AsesmenGiziAction.saveAsesmen] Start >>>>>>>");
        return response;
    }

    public CrudResponse saveMonitoring(String data, String dataPasien){
        logger.info("[AsesmenGiziAction.saveMonitoring] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenMonitoringGiziBo asesmenMonitoringGiziBo = (AsesmenMonitoringGiziBo) ctx.getBean("asesmenMonitoringGiziBoProxy");
        try {
            JSONObject obj = new JSONObject(data);
            AsesmenMonitoringGizi asesmenGizi = new AsesmenMonitoringGizi();
            asesmenGizi.setNoCheckup(obj.getString("no_checkup"));
            asesmenGizi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenGizi.setTanggal(obj.getString("tanggal"));
            asesmenGizi.setBeratBadan(obj.getString("berat_badan"));
            asesmenGizi.setStatusGizi(obj.getString("status_gizi"));
            asesmenGizi.setIntake(obj.getString("intake"));
            asesmenGizi.setFisik(obj.getString("fisik"));
            asesmenGizi.setBiokimia(obj.getString("biokimia"));
            asesmenGizi.setIntervensi(obj.getString("intervensi"));
            asesmenGizi.setLainLain(obj.getString("lain_lain"));
            asesmenGizi.setNamaDokter(obj.getString("nama_dokter"));
            asesmenGizi.setSipDokter(obj.getString("sip_dokter"));
            if(obj.has("ttd_dokter")){
                if(obj.getString("ttd_dokter") != null && !"".equalsIgnoreCase(obj.getString("ttd_dokter"))){
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd_dokter"));
                        String wkt = time.toString();
                        String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                        String fileName = obj.getString("id_detail_checkup") + "-ttd_dokter-" + patten + ".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                        if (image == null) {
                            logger.error("Buffered Image is null");
                            response.setStatus("error");
                            response.setMsg("Buffered Image is null");
                            return response;
                        } else {
                            File f = new File(uploadFile);
                            ImageIO.write(image, "png", f);
                            asesmenGizi.setTtdDokter(fileName);
                        }
                    }catch (IOException e){
                        response.setStatus("error");
                        response.setMsg("Found Error when IO Images, "+e.getMessage());
                    }
                }
            }
            asesmenGizi.setAction("C");
            asesmenGizi.setFlag("Y");
            asesmenGizi.setCreatedWho(userLogin);
            asesmenGizi.setCreatedDate(time);
            asesmenGizi.setLastUpdateWho(userLogin);
            asesmenGizi.setLastUpdate(time);
            try {
                asesmenMonitoringGiziBo.saveAdd(asesmenGizi);
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
                    rekamMedikBo.saveAdd(status);
                    response.setStatus("success");
                    response.setMsg("OK");
                }
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (Exception e){
            response.setStatus("error");
            response.setMsg("Found Error when JSON parse, "+e.getMessage());
        }
        logger.info("[AsesmenGiziAction.saveMonitoring] Start >>>>>>>");
        return response;
    }

    public List<AsesmenGizi> getListAsesmen(String idDetailCheckup, String keterangan) {
        logger.info("[AsesmenGiziAction.getListAsesmen] Start >>>>>>>");
        List<AsesmenGizi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenGiziBo asesmenGiziBo = (AsesmenGiziBo) ctx.getBean("asesmenGiziBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenGizi asesmenGizi = new AsesmenGizi();
                asesmenGizi.setIdDetailCheckup(idDetailCheckup);
                asesmenGizi.setKeterangan(keterangan);
                list = asesmenGiziBo.getByCriteria(asesmenGizi);
            } catch (GeneralBOException e) {
                logger.error("[AsesmenGiziAction.getListAsesmen] Error,"+e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.getListAsesmen] End >>>>>>>");
        return list;
    }

    public List<AsesmenAsuhanGizi> getListAsuhan(String noCheckup) {
        logger.info("[AsesmenGiziAction.getListAsuhan] Start >>>>>>>");
        List<AsesmenAsuhanGizi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenAsuhanGiziBo asesmenAsuhanGiziBo = (AsesmenAsuhanGiziBo) ctx.getBean("asesmenAsuhanGiziBoProxy");
        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(noCheckup)) {
            try {
                AsesmenAsuhanGizi asesmenGizi = new AsesmenAsuhanGizi();
                asesmenGizi.setNoCheckup(noCheckup);
                list = asesmenAsuhanGiziBo.getByCriteria(asesmenGizi);
            } catch (GeneralBOException e) {
                logger.error("[AsesmenGiziAction.getListAsuhan] Error,"+e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.getListAsuhan] End >>>>>>>");
        return list;
    }

    public List<AsesmenMonitoringGizi> getListMonitoring(String noCheckup) {
        logger.info("[AsesmenGiziAction.getListAsuhan] Start >>>>>>>");
        List<AsesmenMonitoringGizi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenMonitoringGiziBo asesmenMonitoringGiziBo = (AsesmenMonitoringGiziBo) ctx.getBean("asesmenMonitoringGiziBoProxy");
        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(noCheckup)) {
            try {
                AsesmenMonitoringGizi asesmenGizi = new AsesmenMonitoringGizi();
                asesmenGizi.setNoCheckup(noCheckup);
                list = asesmenMonitoringGiziBo.getByCriteria(asesmenGizi);
            } catch (GeneralBOException e) {
                logger.error("[AsesmenGiziAction.getListAsuhan] Error,"+e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.getListAsuhan] End >>>>>>>");
        return list;
    }

    public CrudResponse saveDeleteAsesmen(String idDetailCheckup, String keterangan, String dataPasien, String idAsesmen) {
        logger.info("[AsesmenGiziAction.saveDeleteAsesmen] End >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenGiziBo asesmenGiziBo = (AsesmenGiziBo) ctx.getBean("asesmenGiziBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenGizi asesmenGizi = new AsesmenGizi();
                asesmenGizi.setIdDetailCheckup(idDetailCheckup);
                asesmenGizi.setKeterangan(keterangan);
                asesmenGizi.setLastUpdate(time);
                asesmenGizi.setLastUpdateWho(userLogin);
                asesmenGizi.setIdAsesmenGizi(idAsesmen);
                asesmenGiziBo.saveDelete(asesmenGizi);
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
                        rekamMedikBo.saveEdit(status);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }
                }catch (JSONException e){
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            } catch (Exception e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.saveDeleteAsesmen] End >>>>>>>");
        return response;
    }

    public CrudResponse saveDeleteAsuhan(String id, String dataPasien) {
        logger.info("[AsesmenGiziAction.saveDeleteAsuhan] End >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenAsuhanGiziBo asesmenAsuhanGiziBo = (AsesmenAsuhanGiziBo) ctx.getBean("asesmenAsuhanGiziBoProxy");
        if (!"".equalsIgnoreCase(id) && !"".equalsIgnoreCase(id)) {
            try {
                AsesmenAsuhanGizi asesmenGizi = new AsesmenAsuhanGizi();
                asesmenGizi.setIdAsuhanGizi(id);
                asesmenGizi.setLastUpdate(time);
                asesmenGizi.setLastUpdateWho(userLogin);
                asesmenAsuhanGiziBo.saveDelete(asesmenGizi);
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
                        rekamMedikBo.saveEdit(status);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }
                }catch (JSONException e){
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            } catch (Exception e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.saveDeleteAsuhan] End >>>>>>>");
        return response;
    }

    public CrudResponse saveDeleteMonitoring(String id, String dataPasien) {
        logger.info("[AsesmenGiziAction.saveDeleteMonitoring] End >>>>>>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenMonitoringGiziBo asesmenMonitoringGiziBo = (AsesmenMonitoringGiziBo) ctx.getBean("asesmenMonitoringGiziBoProxy");
        if (!"".equalsIgnoreCase(id) && !"".equalsIgnoreCase(id)) {
            try {
                AsesmenMonitoringGizi asesmenGizi = new AsesmenMonitoringGizi();
                asesmenGizi.setIdMonitoringGizi(id);
                asesmenGizi.setLastUpdate(time);
                asesmenGizi.setLastUpdateWho(userLogin);
                asesmenMonitoringGiziBo.saveDelete(asesmenGizi);
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
                        rekamMedikBo.saveEdit(status);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }
                }catch (JSONException e){
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            } catch (Exception e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        logger.info("[AsesmenGiziAction.saveDeleteMonitoring] End >>>>>>>");
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}
