package com.neurix.simrs.transaksi.rekonsiliasiobat.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekonsiliasiobat.bo.RekonsiliasiObatBo;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.RekonsiliasiObat;
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

public class RekonsiliasiObatAction {

    public static transient Logger logger = Logger.getLogger(RekonsiliasiObatAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekonsiliasiObatBo rekonsiliasiObatBo = (RekonsiliasiObatBo) ctx.getBean("rekonsiliasiObatBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            RekonsiliasiObat rekonsiliasiObat = new RekonsiliasiObat();
            rekonsiliasiObat.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            rekonsiliasiObat.setTanggal(Date.valueOf(obj.getString("tanggal")));
            rekonsiliasiObat.setRuangan(obj.getString("ruangan"));
            rekonsiliasiObat.setNamaObat(obj.getString("nama_obat"));
            rekonsiliasiObat.setDosis(obj.getString("dosis"));
            rekonsiliasiObat.setAturanPakai(obj.getString("aturan_pakai"));
            rekonsiliasiObat.setIndikasi(obj.getString("indikasi"));
            rekonsiliasiObat.setDiteruskan(obj.getString("diteruskan"));

            if(obj.has("ttd_pasien") || obj.has("ttd_apoteker")){
                if(!"".equalsIgnoreCase(obj.getString("ttd_pasien")) || !"".equalsIgnoreCase(obj.getString("ttd_apoteker"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("ttd_pasien"));
                    byte[] decodedBytes2 = decoder.decodeBuffer(obj.getString("ttd_apoteker"));

                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                    String fileName1 = obj.getString("id_detail_checkup") + "-" + "ttd_pasien" + "-" + patten + ".png";
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

                        rekonsiliasiObat.setTtdPasien(fileName1);
                        rekonsiliasiObat.setTtdApoteker(fileName2);
                    }
                }
            }

            rekonsiliasiObat.setAction("C");
            rekonsiliasiObat.setFlag("Y");
            rekonsiliasiObat.setCreatedWho(userLogin);
            rekonsiliasiObat.setCreatedDate(time);
            rekonsiliasiObat.setLastUpdateWho(userLogin);
            rekonsiliasiObat.setLastUpdate(time);

            try {
                response = rekonsiliasiObatBo.saveAdd(rekonsiliasiObat);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<RekonsiliasiObat> getListDetail(String idDetailCheckup) {
        List<RekonsiliasiObat> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekonsiliasiObatBo rekonsiliasiObatBo = (RekonsiliasiObatBo) ctx.getBean("rekonsiliasiObatBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                RekonsiliasiObat rekonsiliasiObat = new RekonsiliasiObat();
                rekonsiliasiObat.setIdDetailCheckup(idDetailCheckup);
                list = rekonsiliasiObatBo.getByCriteria(rekonsiliasiObat);
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
