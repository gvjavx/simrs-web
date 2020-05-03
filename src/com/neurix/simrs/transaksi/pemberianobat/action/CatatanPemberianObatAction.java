package com.neurix.simrs.transaksi.pemberianobat.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pemberianobat.bo.CatatanPemberianObatBo;
import com.neurix.simrs.transaksi.pemberianobat.model.CatatanPemberianObat;
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

    public CrudResponse saveCatatanPemberianObat(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
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

            if(obj.has("ttd_dokter") || obj.has("ttd_apoteker")){
                if(!"".equalsIgnoreCase(obj.getString("ttd_dokter")) || !"".equalsIgnoreCase(obj.getString("ttd_apoteker"))){
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
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<CatatanPemberianObat> getListCatatanPemberianObat(String idDetailCheckup) {
        List<CatatanPemberianObat> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanPemberianObatBo catatanPemberianObatBo = (CatatanPemberianObatBo) ctx.getBean("catatanPemberianObatBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                CatatanPemberianObat catatan = new CatatanPemberianObat();
                catatan.setIdDetailCheckup(idDetailCheckup);
                list = catatanPemberianObatBo.getByCriteria(catatan);
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
