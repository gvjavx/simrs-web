package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.bo.RencanaAsuhanKeperawatanBo;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.RencanaAsuhanKeperawatan;
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

public class RencanaAsuhanKeperawatanAction {

    public static transient Logger logger = Logger.getLogger(RencanaAsuhanKeperawatanAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RencanaAsuhanKeperawatanBo rencanaAsuhanKeperawatanBo = (RencanaAsuhanKeperawatanBo) ctx.getBean("rencanaAsuhanKeperawatanBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            RencanaAsuhanKeperawatan catatan = new RencanaAsuhanKeperawatan();
            catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            catatan.setWaktu(Timestamp.valueOf(obj.getString("waktu")));
            catatan.setDiagnosa(obj.getString("diagnosa"));
            catatan.setTujuan(obj.getString("tujuan"));
            catatan.setIntervensi(obj.getString("intervensi"));
            catatan.setKeterangan(obj.getString("keterangan"));

            if(obj.has("ttd_perawat")){
                if(!"".equalsIgnoreCase(obj.getString("ttd_perawat"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("ttd_perawat"));

                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");

                    String fileName1 = obj.getString("id_detail_checkup") + "-" + "ttd_perawat" + "-" + patten + ".png";
                    String uploadFile1 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName1;

                    BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(decodedBytes1));

                    if (image1 == null) {
                        logger.error("Buffered Image is null");
                        response.setStatus("error");
                        response.setMsg("Buffered Image is null");
                    } else {
                        File f1 = new File(uploadFile1);
                        // write the image
                        ImageIO.write(image1, "png", f1);

                        catatan.setTtdPerawat(fileName1);
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
                response = rencanaAsuhanKeperawatanBo.saveAdd(catatan);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<RencanaAsuhanKeperawatan> getListDetail(String idDetailCheckup) {
        List<RencanaAsuhanKeperawatan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RencanaAsuhanKeperawatanBo rencanaAsuhanKeperawatanBo = (RencanaAsuhanKeperawatanBo) ctx.getBean("rencanaAsuhanKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                RencanaAsuhanKeperawatan catatan = new RencanaAsuhanKeperawatan();
                catatan.setIdDetailCheckup(idDetailCheckup);
                list = rencanaAsuhanKeperawatanBo.getByCriteria(catatan);
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
