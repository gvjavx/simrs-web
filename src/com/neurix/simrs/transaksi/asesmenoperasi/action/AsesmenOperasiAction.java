package com.neurix.simrs.transaksi.asesmenoperasi.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.AsesmenOperasiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.MonAnestesiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import com.neurix.simrs.transaksi.asesmenoperasi.model.MonitoringAnestesi;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AsesmenOperasiAction {

    public static transient Logger logger = Logger.getLogger(AsesmenOperasiAction.class);

    public CrudResponse saveAsesmenOperasi(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        String noCheckup = "";
        String idDetailCheckup = "";
        String idPasien = "";
        String idRm = "";

        try {
            JSONObject objt = new JSONObject(dataPasien);
            noCheckup = objt.getString("no_checkup");
            idDetailCheckup = objt.getString("id_detail_checkup");
            idPasien = objt.getString("id_pasien");
            idRm = objt.getString("id_rm");

            JSONArray json = new JSONArray(data);
            List<AsesmenOperasi> operasiList = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                AsesmenOperasi asesmenOperasi = new AsesmenOperasi();
                if(obj.has("parameter")){
                    asesmenOperasi.setParameter(obj.getString("parameter"));
                }
                asesmenOperasi.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                asesmenOperasi.setKeterangan(obj.getString("keterangan"));

                if (obj.has("tipe")) {
                    if ("ttd".equalsIgnoreCase(obj.getString("tipe")) ||
                            "penanda".equalsIgnoreCase(obj.getString("tipe")) ||
                            "gambar".equalsIgnoreCase(obj.getString("tipe"))) {
                        try {
                            if(obj.getString("jawaban1") != null && !"".equalsIgnoreCase(obj.getString("jawaban1"))){
                                String name = obj.getString("jawaban1");
                                String nameFile1 = name.substring(name.length() - 13);
                                String nameFile2 = nameFile1.replace("=", "a");
                                BASE64Decoder decoder = new BASE64Decoder();
                                byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban1"));
                                logger.info("Decoded upload data : " + decodedBytes.length);
                                String wkt = time.toString();
                                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                                logger.info("PATTERN :" + patten);
                                String fileName = obj.getString("id_detail_checkup") + "-" + nameFile2 + i + "-" + patten + ".png";
                                String uploadFile = "";

                                if ("penanda".equalsIgnoreCase(obj.getString("tipe"))) {
                                    uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_AREA_OPERASI + fileName;
                                }
                                if ("ttd".equalsIgnoreCase(obj.getString("tipe"))) {
                                    uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                                }
                                if ("gambar".equalsIgnoreCase(obj.getString("tipe"))) {
                                    uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                                }

                                logger.info("File save path : " + uploadFile);
                                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                                if (image == null) {
                                    logger.error("Buffered Image is null");
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    File f = new File(uploadFile);
                                    ImageIO.write(image, "png", f);
                                    asesmenOperasi.setJawaban1(fileName);
                                }
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMsg("Error when parse IO Images " + e.getMessage());
                        }
                    } else {
                        if (obj.has("jawaban1")) {
                            if (!"".equalsIgnoreCase(obj.getString("jawaban1"))) {
                                asesmenOperasi.setJawaban1(obj.getString("jawaban1"));
                            }
                        }
                    }
                    asesmenOperasi.setTipe(obj.getString("tipe"));
                } else {
                    if (obj.has("jawaban1")) {
                        if (!"".equalsIgnoreCase(obj.getString("jawaban1"))) {
                            asesmenOperasi.setJawaban1(obj.getString("jawaban1"));
                        }
                    }
                }

                if (obj.has("jawaban2")) {
                    if (!"".equalsIgnoreCase(obj.getString("jawaban2"))) {
                        asesmenOperasi.setJawaban2(obj.getString("jawaban2"));
                    }
                }

                if (obj.has("jenis")) {
                    if (!"".equalsIgnoreCase(obj.getString("jenis"))) {
                        asesmenOperasi.setJenis(obj.getString("jenis"));
                    }
                }
                if (obj.has("skor")) {
                    if (!"".equalsIgnoreCase(obj.getString("skor"))) {
                        asesmenOperasi.setSkor(Integer.valueOf(obj.getString("skor")));
                    }
                }
                if (obj.has("nama_terang")) {
                    asesmenOperasi.setNamaterang(obj.getString("nama_terang"));
                }
                if (obj.has("sip")) {
                    asesmenOperasi.setSip(obj.getString("sip"));
                }

                asesmenOperasi.setAction("C");
                asesmenOperasi.setFlag("Y");
                asesmenOperasi.setCreatedWho(userLogin);
                asesmenOperasi.setCreatedDate(time);
                asesmenOperasi.setLastUpdateWho(userLogin);
                asesmenOperasi.setLastUpdate(time);
                asesmenOperasi.setNoCheckup(noCheckup);
                operasiList.add(asesmenOperasi);
            }

            try {
                response = asesmenOperasiBo.saveAdd(operasiList);
                if ("success".equalsIgnoreCase(response.getStatus())) {
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    if (objt != null) {
                        StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                        status.setNoCheckup(noCheckup);
                        status.setIdDetailCheckup(idDetailCheckup);
                        status.setIdPasien(idPasien);
                        status.setIdRekamMedisPasien(idRm);
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
            response.setMsg("Error when parse JSON array, " + e.getMessage());
        }
        return response;
    }

    public List<AsesmenOperasi> getListAsesmenOperasi(String noCheckup, String keterangan) {
        List<AsesmenOperasi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        if (!"".equalsIgnoreCase(noCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenOperasi operasi = new AsesmenOperasi();
                operasi.setNoCheckup(noCheckup);
                operasi.setKeterangan(keterangan);
                list = asesmenOperasiBo.getByCriteria(operasi);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveMonAnestesi(String data) throws JSONException {

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonAnestesiBo monAnestesiBo = (MonAnestesiBo) ctx.getBean("monAnestesiBoProxy");

        JSONObject obj = new JSONObject(data);
        MonitoringAnestesi monitoringAnestesi = new MonitoringAnestesi();
        if (obj != null) {

            String idDetailCheckup = obj.getString("id_detail_checkup");
            String jam = obj.getString("waktu");
            String ket = obj.getString("keterangan");

            monitoringAnestesi.setIdDetailCheckup(idDetailCheckup);
            monitoringAnestesi.setWaktu(jam);
            monitoringAnestesi.setRr(obj.getString("rr"));
            monitoringAnestesi.setNadi(obj.getString("nadi"));
            monitoringAnestesi.setSistole(obj.getString("sistole"));
            monitoringAnestesi.setDiastole(obj.getString("diastole"));
            monitoringAnestesi.setO2(obj.getString("o2"));
            monitoringAnestesi.setN2O(obj.getString("n2o"));
            monitoringAnestesi.setInhalasi(obj.getString("inhalasi"));
            monitoringAnestesi.setKeterangan(ket);
            monitoringAnestesi.setJenis(obj.getString("jenis"));
            monitoringAnestesi.setAction("C");
            monitoringAnestesi.setFlag("Y");
            monitoringAnestesi.setCreatedWho(userLogin);
            monitoringAnestesi.setCreatedDate(time);
            monitoringAnestesi.setLastUpdateWho(userLogin);
            monitoringAnestesi.setLastUpdate(time);

            Boolean cekData = monAnestesiBo.cekDataAnestesi(idDetailCheckup, jam, ket);

            if (cekData) {
                response.setStatus("error");
                response.setMsg("Data pada jam " + jam + " untuk hari ini sudah ada..!");
            } else {
                try {
                    response = monAnestesiBo.saveAdd(monitoringAnestesi);
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    return response;
                }
            }
        } else {
            response.setStatus("Error");
            response.setMsg("[Found Error], Data tidak ada...!");
            return response;
        }
        return response;
    }

    public List<MonitoringAnestesi> getListMonAnestesi(String idDetailCheckup, String keterangan) {
        List<MonitoringAnestesi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonAnestesiBo monAnestesiBo = (MonAnestesiBo) ctx.getBean("monAnestesiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                MonitoringAnestesi monitoringAnestesi = new MonitoringAnestesi();
                monitoringAnestesi.setIdDetailCheckup(idDetailCheckup);
                monitoringAnestesi.setKeterangan(keterangan);
                list = monAnestesiBo.getByCriteria(monitoringAnestesi);
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
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenOperasi operasi = new AsesmenOperasi();
                operasi.setIdDetailCheckup(idDetailCheckup);
                operasi.setKeterangan(keterangan);
                operasi.setLastUpdate(time);
                operasi.setLastUpdateWho(userLogin);
                response = asesmenOperasiBo.saveDelete(operasi);
                if ("success".equalsIgnoreCase(response.getStatus())) {
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
                    } catch (JSONException e) {
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

    public CrudResponse deleteMonAnestesi(String idMon) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonAnestesiBo monAnestesiBo = (MonAnestesiBo) ctx.getBean("monAnestesiBoProxy");
        if (!"".equalsIgnoreCase(idMon) && idMon != null) {
            try {
                MonitoringAnestesi monitoringAnestesi = new MonitoringAnestesi();
                monitoringAnestesi.setIdMonitoringAnestesi(idMon);
                monitoringAnestesi.setLastUpdate(time);
                monitoringAnestesi.setLastUpdateWho(userLogin);
                response = monAnestesiBo.saveDelete(monitoringAnestesi);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    public String getDataByKey(String id, String key) {
        String responses = "";
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        if (!"".equalsIgnoreCase(id) && id != null) {
            try {
                String params = "";
                if("dokter_anestesi".equalsIgnoreCase(key)){
                    params = "Dokter Spesialis Anestesi";
                }else if("perawat_anestesi".equalsIgnoreCase(key)){
                    params = "Perawat Anestesi";
                }else if("dokter_bedah".equalsIgnoreCase(key)){
                    params = "Dokter Bedah";
                }else if("asisten_instrumen".equalsIgnoreCase(key)){
                    params = "Asisten dan Instrumen";
                }
                responses = asesmenOperasiBo.getDataByKey(id, params);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return responses;
    }

    public CrudResponse saveEditAsesmenOP(String id, String jawaban, String jenis) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (!"".equalsIgnoreCase(id) && !"".equalsIgnoreCase(jawaban)) {
            try {
                AsesmenOperasi asesmenOperasi = new AsesmenOperasi();
                asesmenOperasi.setIdAsesmenOperasi(id);
                asesmenOperasi.setJawaban2(jawaban);
                asesmenOperasi.setLastUpdate(time);
                asesmenOperasi.setLastUpdateWho(userLogin);
                asesmenOperasi.setJenis(jenis);
                asesmenOperasiBo.saveEdit(asesmenOperasi);
                response.setStatus("success");
                response.setMsg("OK");
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg("Error, "+e.getMessage());
            }
        }
        return response;
    }

    public CrudResponse updateTtdPerawat(String id, String ttd, String sip, String nama) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenOperasiBo asesmenOperasiBo = (AsesmenOperasiBo) ctx.getBean("asesmenOperasiBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (!"".equalsIgnoreCase(id) && !"".equalsIgnoreCase(ttd)) {
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(ttd);
                String wkt = time.toString();
                String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                String fileName = id + "-" + patten + ".png";
                String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                if (image == null) {
                    response.setStatus("error");
                    response.setMsg("Buffered Image is null");
                    return response;
                } else {
                    File f = new File(uploadFile);
                    ImageIO.write(image, "png", f);
                    AsesmenOperasi asesmenOperasi = new AsesmenOperasi();
                    asesmenOperasi.setIdAsesmenOperasi(id);
                    asesmenOperasi.setJawaban1(fileName);
                    asesmenOperasi.setNamaterang(nama);
                    asesmenOperasi.setSip(sip);
                    asesmenOperasi.setLastUpdate(time);
                    asesmenOperasi.setLastUpdateWho(userLogin);
                    asesmenOperasi.setJenis("ttd");
                    asesmenOperasiBo.saveEdit(asesmenOperasi);
                    response.setStatus("success");
                    response.setMsg("OK");
                }
            } catch (Exception e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg("Error, "+e.getMessage());
            }
        }
        return response;
    }

    public MonitoringAnestesi getVitalSingMonAnestesi(String idDetailCheckup) {
        MonitoringAnestesi monitoringAnestesi = new MonitoringAnestesi();
        List<MonitoringAnestesi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonAnestesiBo monAnestesiBo = (MonAnestesiBo) ctx.getBean("monAnestesiBoProxy");
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                monitoringAnestesi.setIdDetailCheckup(idDetailCheckup);
                monitoringAnestesi.setIsDesc("Y");
                list = monAnestesiBo.getByCriteria(monitoringAnestesi);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
            if(list.size() > 0){
                monitoringAnestesi = list.get(0);
            }
        }
        return monitoringAnestesi;
    }

    public static Logger getLogger() {
        return logger;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }
}
