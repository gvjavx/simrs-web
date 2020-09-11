package com.neurix.simrs.transaksi.hemodialisa.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.HemodialisaBo;
import com.neurix.simrs.transaksi.hemodialisa.bo.ObservasiTindakanHdBo;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import com.neurix.simrs.transaksi.hemodialisa.model.ObservasiTindakanHd;
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

public class HemodialisaAction {

    public static transient Logger logger = Logger.getLogger(HemodialisaAction.class);
    private String userLogin = CommonUtil.userLogin();
    private Timestamp time = new Timestamp(System.currentTimeMillis());

    public CrudResponse saveHemodialisa(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<Hemodialisa> hemodialisaList = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                Hemodialisa hemodialisa = new Hemodialisa();
                hemodialisa.setParameter(obj.getString("parameter"));
                hemodialisa.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                hemodialisa.setKeterangan(obj.getString("keterangan"));
                if(obj.has("is_ttd")){
                    if("Y".equalsIgnoreCase(obj.getString("is_ttd"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban1"));
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            logger.info("PATTERN :" + patten);
                            String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                            String uploadFile = "";
                            if("Scala Nyeri Paint".equalsIgnoreCase(obj.getString("parameter"))){
                                uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                                hemodialisa.setIsTtd("G");
                            }else{
                                uploadFile =  CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                                hemodialisa.setIsTtd("Y");
                            }
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
                                hemodialisa.setJawaban1(fileName);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Found Error, "+e.getMessage());
                        }
                    }
                }else{
                    hemodialisa.setJawaban1(obj.getString("jawaban1"));
                }

                if(obj.has("jawaban2")){
                    hemodialisa.setJawaban2(obj.getString("jawaban2"));
                }
                if(obj.has("jenis")){
                    hemodialisa.setJenis(obj.getString("jenis"));
                }
                if(obj.has("skor")){
                    hemodialisa.setSkor(Integer.valueOf(obj.getString("skor")));
                }
                if(obj.has("nama_terang")){
                    hemodialisa.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("sip")){
                    hemodialisa.setSip(obj.getString("sip"));
                }

                hemodialisa.setAction("C");
                hemodialisa.setFlag("Y");
                hemodialisa.setCreatedWho(userLogin);
                hemodialisa.setCreatedDate(time);
                hemodialisa.setLastUpdateWho(userLogin);
                hemodialisa.setLastUpdate(time);
                hemodialisaList.add(hemodialisa);
            }

            try {
                response = hemodialisaBo.saveAdd(hemodialisaList);
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
            response.setMsg("Found Error, "+e.getMessage());
        }
        return response;
    }

    public List<Hemodialisa> getListHemodialisa(String idDetailCheckup, String keterangan) {
        List<Hemodialisa> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Hemodialisa hemodialisa = new Hemodialisa();
                hemodialisa.setIdDetailCheckup(idDetailCheckup);
                hemodialisa.setKeterangan(keterangan);
                list = hemodialisaBo.getByCriteria(hemodialisa);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodialisaBo hemodialisaBo = (HemodialisaBo) ctx.getBean("hemodialisaBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Hemodialisa hemodialisa = new Hemodialisa();
                hemodialisa.setIdDetailCheckup(idDetailCheckup);
                hemodialisa.setKeterangan(keterangan);
                hemodialisa.setLastUpdate(time);
                hemodialisa.setLastUpdateWho(userLogin);
                response = hemodialisaBo.saveDelete(hemodialisa);
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

    public CrudResponse saveObservasi(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObservasiTindakanHdBo observasiTindakanHdBo = (ObservasiTindakanHdBo) ctx.getBean("observasiTindakanHdBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<ObservasiTindakanHd> observasiTindakanHds = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                ObservasiTindakanHd observasiTindakanHd = new ObservasiTindakanHd();
                observasiTindakanHd.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                observasiTindakanHd.setKeterangan(obj.getString("keterangan"));
                observasiTindakanHd.setJenis(obj.getString("jenis"));
                if(obj.has("ttd")){
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd"));
                        logger.info("Decoded upload data : " + decodedBytes.length);
                        String wkt = time.toString();
                        String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                        logger.info("PATTERN :" + patten);
                        String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
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
                            observasiTindakanHd.setTtd(fileName);
                        }
                    }catch (IOException e){
                        response.setStatus("error");
                        response.setMsg("Found Error, "+e.getMessage());
                    }
                }

                if(obj.has("waktu")){
                    observasiTindakanHd.setWaktu(obj.getString("waktu"));
                }
                if(obj.has("observasi")){
                    observasiTindakanHd.setObservasi(obj.getString("observasi"));
                }
                if(obj.has("qb")){
                    observasiTindakanHd.setQb(obj.getString("qb"));
                }
                if(obj.has("tensi")){
                    observasiTindakanHd.setTensi(obj.getString("tensi"));
                }
                if(obj.has("nadi")){
                    observasiTindakanHd.setNadi(obj.getString("nadi"));
                }
                if(obj.has("suhu")){
                    observasiTindakanHd.setSuhu(obj.getString("suhu"));
                }
                if(obj.has("rr")){
                    observasiTindakanHd.setRr(obj.getString("rr"));
                }
                if(obj.has("cairan_masuk")){
                    observasiTindakanHd.setCairanMasuk(obj.getString("cairan_masuk"));
                }
                if(obj.has("makan_minum")){
                    observasiTindakanHd.setMakanMinum(obj.getString("makan_minum"));
                }
                if(obj.has("muntah")){
                    observasiTindakanHd.setMuntah(obj.getString("muntah"));
                }
                if(obj.has("uf")){
                    observasiTindakanHd.setUf(obj.getString("uf"));
                }

                observasiTindakanHd.setAction("C");
                observasiTindakanHd.setFlag("Y");
                observasiTindakanHd.setCreatedWho(userLogin);
                observasiTindakanHd.setCreatedDate(time);
                observasiTindakanHd.setLastUpdateWho(userLogin);
                observasiTindakanHd.setLastUpdate(time);
                observasiTindakanHds.add(observasiTindakanHd);
            }

            try {
                response = observasiTindakanHdBo.saveAdd(observasiTindakanHds);
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
            response.setMsg("Found Error, "+e.getMessage());
        }
        return response;
    }

    public List<ObservasiTindakanHd> getListObservasi(String idDetailCheckup, String keterangan) {
        List<ObservasiTindakanHd> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObservasiTindakanHdBo observasiTindakanHdBo = (ObservasiTindakanHdBo) ctx.getBean("observasiTindakanHdBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                ObservasiTindakanHd tindakanHd = new ObservasiTindakanHd();
                tindakanHd.setIdDetailCheckup(idDetailCheckup);
                tindakanHd.setJenis(keterangan);
                list = observasiTindakanHdBo.getByCriteria(tindakanHd);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse deleteObservasi(String idBservasi) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObservasiTindakanHdBo observasiTindakanHdBo = (ObservasiTindakanHdBo) ctx.getBean("observasiTindakanHdBoProxy");
        if (!"".equalsIgnoreCase(idBservasi)) {
            try {
                ObservasiTindakanHd tindakanHd = new ObservasiTindakanHd();
                tindakanHd.setIdObservasiTindakanHd(idBservasi);
                tindakanHd.setLastUpdate(time);
                tindakanHd.setLastUpdateWho(userLogin);
                response = observasiTindakanHdBo.saveDetele(tindakanHd);
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