package com.neurix.simrs.transaksi.pemberianobat.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pemberianobat.bo.CatatanPemberianObatBo;
import com.neurix.simrs.transaksi.pemberianobat.model.CatatanPemberianObat;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CatatanPemberianObatAction {

    public static transient Logger logger = Logger.getLogger(CatatanPemberianObatAction.class);

    public CrudResponse saveCatatanPemberianObat(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
        try {
            if (data != null) {
                JSONObject obj = new JSONObject(data);
                CatatanPemberianObat catatan = new CatatanPemberianObat();
                catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                catatan.setWaktu(obj.getString("waktu"));
                catatan.setNamaDokter(obj.getString("nama_dokter"));
                catatan.setNamaObat(obj.getString("nama_obat"));
                catatan.setAturanPakai(obj.getString("aturan_pakai"));
                catatan.setTanggalMulai(Date.valueOf(obj.getString("tanggal_mulai")));
                catatan.setTanggalStop(Date.valueOf(obj.getString("tanggal_stop")));
                catatan.setKeterangan(obj.getString("keterangan"));
                catatan.setStatus(obj.getString("status"));
                catatan.setNamaTerangDokter(obj.getString("nama_terang_dokter"));
                catatan.setNamaTerangPerawat(obj.getString("nama_terang_perawat"));
                catatan.setSipDokter(obj.getString("sip_dokter"));

                if(obj.has("ttd_dokter") || obj.has("ttd_apoteker")){
                    if(!"".equalsIgnoreCase(obj.getString("ttd_dokter")) || !"".equalsIgnoreCase(obj.getString("ttd_apoteker"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("ttd_dokter"));
                            byte[] decodedBytes2 = decoder.decodeBuffer(obj.getString("ttd_apoteker"));

                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                            String fileName1 = obj.getString("id_detail_checkup") + "-" + "ttd_dokter" + "-" + patten + ".png";
                            String fileName2 = obj.getString("id_detail_checkup") + "-" + "ttd_apoteker" + "-" + patten + ".png";
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

                                catatan.setTtdDokter(fileName1);
                                catatan.setTtdApoteker(fileName2);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Errror when prse IO Images, "+e.getMessage());
                        }
                    }
                }

                catatan.setAction("C");
                catatan.setFlag("Y");
                catatan.setCreatedWho(userLogin);
                catatan.setCreatedDate(time);
                catatan.setLastUpdateWho(userLogin);
                catatan.setLastUpdate(time);

                try {
                    response = catatanPemberianObatBo.saveAdd(catatan);
                    if("success".equalsIgnoreCase(response.getStatus())){
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
                            response = rekamMedikBo.saveAdd(status);
                        }
                    }
                }catch (GeneralBOException e){
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg("Errror when prse JSON, "+e.getMessage());
        }
        return response;
    }

    public List<CatatanPemberianObat> getListCatatanPemberianObat(String idDetailCheckup, String jenis) {
        List<CatatanPemberianObat> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                CatatanPemberianObat catatan = new CatatanPemberianObat();
                catatan.setIdDetailCheckup(idDetailCheckup);
                if(!"".equalsIgnoreCase(jenis)){
                    catatan.setJenis(jenis);
                }
                list = catatanPemberianObatBo.getByCriteria(catatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idCatatan) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
        if (!"".equalsIgnoreCase(idCatatan)) {
            try {
                CatatanPemberianObat catatan = new CatatanPemberianObat();
                catatan.setIdCatatanPemberianObat(idCatatan);
                response = catatanPemberianObatBo.saveDelete(catatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    public CrudResponse saveUpdate(String data) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (!"".equalsIgnoreCase(data)) {
            try {
                JSONObject obj = new JSONObject(data);
                CatatanPemberianObat catatan = new CatatanPemberianObat();
                catatan.setIdCatatanPemberianObat(obj.getString("id"));
                catatan.setWaktu(obj.getString("waktu"));
                catatan.setNamaTerangDokter(obj.getString("nama1"));
                catatan.setNamaTerangPerawat(obj.getString("nama2"));
                if(obj.has("sip1")){
                    catatan.setSipDokter(obj.getString("sip1"));
                }
                if(obj.has("sip2")){
                    catatan.setSipPerawat(obj.getString("sip2"));
                }
                catatan.setKeterangan(obj.getString("keterangan"));
                if(obj.has("ttd_keluarga") || obj.has("ttd_perawat")){
                    if(!"".equalsIgnoreCase(obj.getString("ttd_keluarga")) || !"".equalsIgnoreCase(obj.getString("ttd_perawat"))){
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("ttd_keluarga"));
                            byte[] decodedBytes2 = decoder.decodeBuffer(obj.getString("ttd_perawat"));

                            String wkt = time.toString();
                            String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                            String fileName1 = obj.getString("id") + "-" + "ttd_keluarga" + "-" + patten + ".png";
                            String fileName2 = obj.getString("id") + "-" + "ttd_perawat" + "-" + patten + ".png";
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
                                ImageIO.write(image1, "png", f1);
                                ImageIO.write(image2, "png", f2);
                                catatan.setTtdDokter(fileName1);
                                catatan.setTtdApoteker(fileName2);
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Errror when prse IO Images, "+e.getMessage());
                        }
                    }
                }
                catatanPemberianObatBo.saveUpdate(catatan);
            } catch (Exception e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg("Error, "+e.getMessage());
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}
