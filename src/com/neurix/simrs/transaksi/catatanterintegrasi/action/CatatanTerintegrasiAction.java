package com.neurix.simrs.transaksi.catatanterintegrasi.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.catatanterintegrasi.bo.CatatanTerintegrasiBo;
import com.neurix.simrs.transaksi.catatanterintegrasi.model.CatatanTerintegrasi;
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

    public CrudResponse saveCatatanTerintegrasi(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanTerintegrasiBo catatanTerintegrasiBo = (CatatanTerintegrasiBo) ctx.getBean("catatanTerintegrasiBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            CatatanTerintegrasi catatan = new CatatanTerintegrasi();
            catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            catatan.setWaktu(Timestamp.valueOf(obj.getString("waktu")));
            catatan.setPpa(obj.getString("ppa"));
            catatan.setJenis(obj.getString("jenis"));
            catatan.setIntruksi(obj.getString("instruksi"));
            catatan.setKeterangan(obj.getString("keterangan"));
            catatan.setTtdPetugas(obj.getString("ttd_petugas"));
            catatan.setTtdDpjp(obj.getString("ttd_dpjp"));

            if(obj.has("ttd_petugas") || obj.has("ttd_dpjp")){
                if(!"".equalsIgnoreCase(obj.getString("ttd_petugas")) || !"".equalsIgnoreCase(obj.getString("ttd_dpjp"))){
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
                }
            }

            catatan.setAction("C");
            catatan.setFlag("Y");
            catatan.setCreatedWho(userLogin);
            catatan.setCreatedDate(time);
            catatan.setLastUpdateWho(userLogin);
            catatan.setLastUpdate(time);

            try {
                response = catatanTerintegrasiBo.saveAdd(catatan);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<CatatanTerintegrasi> getListCatatanTerintegrasi(String idDetailCheckup) {
        List<CatatanTerintegrasi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CatatanTerintegrasiBo catatanTerintegrasiBo = (CatatanTerintegrasiBo) ctx.getBean("catatanTerintegrasiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                CatatanTerintegrasi catatan = new CatatanTerintegrasi();
                catatan.setIdDetailCheckup(idDetailCheckup);
                list = catatanTerintegrasiBo.getByCriteria(catatan);
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
