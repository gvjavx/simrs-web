package com.neurix.simrs.transaksi.asesmenrawatinap.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
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

public class AsesmenRawatInapAction {

    public static transient Logger logger = Logger.getLogger(AsesmenRawatInapAction.class);

    public CrudResponse saveAsesmenRawat(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        JSONArray json = new JSONArray(data);

        List<AsesmenRawatInap> rawatInapList = new ArrayList<>();

        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i);
            AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
            asesmenRawatInap.setParameter(obj.getString("parameter"));
            asesmenRawatInap.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            asesmenRawatInap.setKeterangan(obj.getString("keterangan"));

            if(obj.has("jawaban")){
                if(obj.has("tipe")){
                    if("ttd".equalsIgnoreCase(obj.getString("tipe")) || "gambar".equalsIgnoreCase(obj.getString("tipe"))){
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(obj.getString("jawaban"));
                        String wkt = time.toString();
                        String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                        String fileName = obj.getString("id_detail_checkup") + "-" + obj.getString("jenis")+i+ "-" + patten + ".png";
                        String uploadFile = "";
                        if("ttd".equalsIgnoreCase(obj.getString("tipe"))){
                            uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName;
                        }else{
                            uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_IMG_RM + fileName;
                        }

                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                        if (image == null) {
                            response.setStatus("error");
                            response.setMsg("Buffered Image is null");
                        } else {
                            File f = new File(uploadFile);
                            // write the image
                            ImageIO.write(image, "png", f);
                            asesmenRawatInap.setJawaban(fileName);
                        }
                    }else{
                        asesmenRawatInap.setJawaban(obj.getString("jawaban"));
                    }
                    asesmenRawatInap.setTipe(obj.getString("tipe"));
                }else{
                    asesmenRawatInap.setJawaban(obj.getString("jawaban"));
                }
            }

            if(obj.has("jenis")){
                asesmenRawatInap.setJenis(obj.getString("jenis"));
            }
            if(obj.has("skor")){
                asesmenRawatInap.setSkor(Integer.valueOf(obj.getString("skor")));
            }
            if(obj.has("informasi")){
                asesmenRawatInap.setInformasi(obj.getString("informasi"));
            }

            asesmenRawatInap.setAction("C");
            asesmenRawatInap.setFlag("Y");
            asesmenRawatInap.setCreatedWho(userLogin);
            asesmenRawatInap.setCreatedDate(time);
            asesmenRawatInap.setLastUpdateWho(userLogin);
            asesmenRawatInap.setLastUpdate(time);
            rawatInapList.add(asesmenRawatInap);
        }

        try {
            response = asesmenRawatInapBo.saveAdd(rawatInapList);
        } catch (GeneralBOException e) {
            response.setStatus("Error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
        }

        return response;
    }

    public List<AsesmenRawatInap> getListAsesmenRawat(String idDetailCheckup, String keterangan) {
        List<AsesmenRawatInap> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AsesmenRawatInapBo asesmenRawatInapBo = (AsesmenRawatInapBo) ctx.getBean("asesmenRawatInapBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                asesmenRawatInap.setIdDetailCheckup(idDetailCheckup);
                asesmenRawatInap.setKeterangan(keterangan);
                list = asesmenRawatInapBo.getByCriteria(asesmenRawatInap);
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
