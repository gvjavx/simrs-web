package com.neurix.simrs.transaksi.catatanterintegrasi.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.catatanterintegrasi.bo.CatatanTerintegrasiBo;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.CatatanTerintegrasi;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import org.apache.log4j.Logger;
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

public class CatatanTerintegrasiAction {

    public static transient Logger logger = Logger.getLogger(CatatanTerintegrasiAction.class);

    public CrudResponse saveCatatanTerintegrasi(String data, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanTerintegrasiBo catatanTerintegrasiBo = (CatatanTerintegrasiBo) ctx.getBean("catatanTerintegrasiBoProxy");
        if (data != null) {
            try {
                JSONObject obj = new JSONObject(data);
                CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                catatan.setWaktu(Timestamp.valueOf(obj.getString("waktu")));
                catatan.setPpa(obj.getString("ppa"));
                catatan.setKeterangan(obj.getString("keterangan"));
                catatan.setTtdPetugas(obj.getString("ttd_petugas"));
                catatan.setTtdDpjp(obj.getString("ttd_dpjp"));

                if (obj.has("ttd_petugas") || obj.has("ttd_dpjp")) {
                    if (!"".equalsIgnoreCase(obj.getString("ttd_petugas")) || !"".equalsIgnoreCase(obj.getString("ttd_dpjp"))) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("ttd_petugas"));
                            byte[] decodedBytes2 = decoder.decodeBuffer(obj.getString("ttd_dpjp"));

                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                            String fileName1 = obj.getString("id_detail_checkup") + "-" + "ttd_petugas" + "-" + patten + ".png";
                            String fileName2 = obj.getString("id_detail_checkup") + "-" + "ttd_dpjp" + "-" + patten + ".png";
                            String uploadFile1 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName1;
                            String uploadFile2 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName2;

                            BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(decodedBytes1));
                            BufferedImage image2 = ImageIO.read(new ByteArrayInputStream(decodedBytes2));

                            if (image1 == null || image2 == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                File f1 = new File(uploadFile1);
                                File f2 = new File(uploadFile2);
                                // write the image
                                ImageIO.write(image1, "png", f1);
                                ImageIO.write(image2, "png", f2);

                                catatan.setTtdPetugas(fileName1);
                                catatan.setTtdDpjp(fileName2);
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMsg("Found Error, " + e.getMessage());
                        }
                    }
                }

                if (obj.has("instruksi")) {
                    catatan.setIntruksi(obj.getString("instruksi"));
                }

                if (obj.has("subjective")) {
                    catatan.setSubjective(obj.getString("subjective"));
                }
                if (obj.has("nadi")) {
                    catatan.setNadi(obj.getString("nadi"));
                }
                if (obj.has("suhu")) {
                    catatan.setSuhu(obj.getString("suhu"));
                }
                if (obj.has("rr")) {
                    catatan.setRr(obj.getString("rr"));
                }
                if (obj.has("tensi")) {
                    catatan.setTensi(obj.getString("tensi"));
                }
                if (obj.has("objective")) {
                    catatan.setObjective(obj.getString("objective"));
                }
                if (obj.has("assesment")) {
                    catatan.setAssesment(obj.getString("assesment"));
                }
                if (obj.has("planning")) {
                    catatan.setPlanning(obj.getString("planning"));
                }
                if (obj.has("nama_dokter")) {
                    catatan.setNamaDokter(obj.getString("nama_dokter"));
                }
                if (obj.has("nama_petugas")) {
                    catatan.setNamaPetugas(obj.getString("nama_petugas"));
                }
                if (obj.has("sip_dokter")) {
                    catatan.setSipDokter(obj.getString("sip_dokter"));
                }
                if (obj.has("sip_petugas")) {
                    catatan.setSipPetugas(obj.getString("sip_petugas"));
                }

                catatan.setAction("C");
                catatan.setFlag("Y");
                catatan.setCreatedWho(userLogin);
                catatan.setCreatedDate(time);
                catatan.setLastUpdateWho(userLogin);
                catatan.setLastUpdate(time);

                try {
                    response = catatanTerintegrasiBo.saveAdd(catatan);
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
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            } catch (JSONException e) {
                response.setStatus("error");
                response.setMsg("Found Error, " + e.getMessage());
            }
        }
        return response;
    }

    public List<CatatanTerintegrasi> getListCatatanTerintegrasi(String idDetailCheckup, String keterangan) {
        List<CatatanTerintegrasi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanTerintegrasiBo catatanTerintegrasiBo = (CatatanTerintegrasiBo) ctx.getBean("catatanTerintegrasiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                catatan.setIdDetailCheckup(idDetailCheckup);
                catatan.setKeterangan(keterangan);
                list = catatanTerintegrasiBo.getByCriteria(catatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idCatatanTerintegrasi, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanTerintegrasiBo catatanTerintegrasiBo = (CatatanTerintegrasiBo) ctx.getBean("catatanTerintegrasiBoProxy");
        if (!"".equalsIgnoreCase(idCatatanTerintegrasi)) {
            try {
                CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                catatan.setIdCatatanTerintegrasi(idCatatanTerintegrasi);
                catatan.setLastUpdate(time);
                catatan.setLastUpdateWho(userLogin);
                response = catatanTerintegrasiBo.saveDelete(catatan);
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
