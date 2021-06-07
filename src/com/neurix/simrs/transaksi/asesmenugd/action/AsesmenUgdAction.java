package com.neurix.simrs.transaksi.asesmenugd.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenugd.bo.AsesmenUgdBo;
import com.neurix.simrs.transaksi.asesmenugd.model.AsesmenUgd;
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

public class AsesmenUgdAction {

    public static transient Logger logger = Logger.getLogger(AsesmenUgdAction.class);

    public CrudResponse saveAsesmenUgd(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            JSONObject dtaoObj = new JSONObject(dataPasien);
            List<AsesmenUgd> ugdList = new ArrayList<>();

            for (int i = 0; i < json.length(); i++) {

                JSONObject obj = json.getJSONObject(i);
                AsesmenUgd asesmenUgd = new AsesmenUgd();
                asesmenUgd.setParameter(obj.getString("parameter"));
                asesmenUgd.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                asesmenUgd.setKeterangan(obj.getString("keterangan"));
                if (obj.has("jenis")) {
                    asesmenUgd.setJenis(obj.getString("jenis"));
                }
                if (obj.has("skor")) {
                    asesmenUgd.setSkor(Integer.valueOf(obj.getString("skor")));
                }
                if (obj.has("nama_terang")) {
                    asesmenUgd.setNamaTerang(obj.getString("nama_terang"));
                }
                if (obj.has("sip")) {
                    asesmenUgd.setSip(obj.getString("sip"));
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
                            String fileName = obj.getString("id_detail_checkup") + "-" + i + "-" + patten + ".png";
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
                                // write the image
                                ImageIO.write(image, "png", f);
                                asesmenUgd.setJawaban(fileName);
                            }
                        } catch (IOException e) {
                            response.setStatus("Error");
                            response.setMsg("Found Error " + e.getMessage());
                        }
                    } else {
                        asesmenUgd.setJawaban(obj.getString("jawaban"));
                    }
                    asesmenUgd.setTipe(obj.getString("tipe"));
                } else {
                    asesmenUgd.setJawaban(obj.getString("jawaban"));
                }
                asesmenUgd.setAction("C");
                asesmenUgd.setFlag("Y");
                asesmenUgd.setCreatedWho(userLogin);
                asesmenUgd.setCreatedDate(time);
                asesmenUgd.setLastUpdateWho(userLogin);
                asesmenUgd.setLastUpdate(time);
                if (dtaoObj.has("no_checkup")) {
                    asesmenUgd.setNoCheckup(dtaoObj.getString("no_checkup"));
                }
                ugdList.add(asesmenUgd);
            }
            try {
                response = asesmenUgdBo.saveAdd(ugdList);
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
        } catch (Exception e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
        }
        return response;
    }

    public List<AsesmenUgd> getListAsesmenUgd(String idDetailCheckup, String keterangan) {
        List<AsesmenUgd> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenUgd asesmenUgd = new AsesmenUgd();
                asesmenUgd.setIdDetailCheckup(idDetailCheckup);
                asesmenUgd.setKeterangan(keterangan);
                list = asesmenUgdBo.getByCriteria(asesmenUgd);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idDetailCheckup, String keterangan, String dataPasien) {
        CrudResponse response = new CrudResponse();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = CommonUtil.userLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenUgd asesmenUgd = new AsesmenUgd();
                asesmenUgd.setIdDetailCheckup(idDetailCheckup);
                asesmenUgd.setKeterangan(keterangan);
                asesmenUgd.setLastUpdate(time);
                asesmenUgd.setLastUpdateWho(userLogin);
                response = asesmenUgdBo.saveDelete(asesmenUgd);
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

    public List<AsesmenUgd> getKesimpulanAsesmen(String idDetailCheckup) {
        List<AsesmenUgd> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenUgdBo asesmenUgdBo = (AsesmenUgdBo) ctx.getBean("asesmenUgdBoProxy");
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                AsesmenUgd asesmenUgd = new AsesmenUgd();
                asesmenUgd.setIdDetailCheckup(idDetailCheckup);
                List<String> listIn = new ArrayList<>();
                listIn.add("total");
                listIn.add("kesimpulan");
                asesmenUgd.setTipeAsesmen(listIn);
                list = asesmenUgdBo.getByCriteria(asesmenUgd);
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
