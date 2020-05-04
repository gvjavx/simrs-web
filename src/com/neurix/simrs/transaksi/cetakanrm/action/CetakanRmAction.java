package com.neurix.simrs.transaksi.cetakanrm.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.cetakanrm.bo.CetakanRmBo;
import com.neurix.simrs.transaksi.cetakanrm.model.CetakanRm;
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

public class CetakanRmAction {

    public static transient Logger logger = Logger.getLogger(CetakanRmAction.class);

    public CrudResponse save(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CetakanRmBo cetakanRmBo = (CetakanRmBo) ctx.getBean("cetakanRmBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            CetakanRm catatan = new CetakanRm();
            catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            catatan.setKeterangan(obj.getString("keterangan"));

            if(obj.has("doc_rm")){
                if(!"".equalsIgnoreCase(obj.getString("doc_rm"))){
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] decodedBytes1 = decoder.decodeBuffer(obj.getString("doc_rm"));
                    String wkt = time.toString();
                    String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                    String fileName1 = obj.getString("id_detail_checkup") + "-" + obj.getString("keterangan") + "-" + patten + ".png";
                    String uploadFile1 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_DOC_RM + fileName1;
                    BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(decodedBytes1));

                    if (image1 == null) {
                        logger.error("Buffered Image is null");
                        response.setStatus("error");
                        response.setMsg("Buffered Image is null");
                    } else {
                        File f1 = new File(uploadFile1);
                        // write the image
                        ImageIO.write(image1, "png", f1);
                        catatan.setDocRm(fileName1);
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
                response = cetakanRmBo.saveAdd(catatan);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<CetakanRm> getListDetail(String idDetailCheckup, String keterangan) {
        List<CetakanRm> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CetakanRmBo cetakanRmBo = (CetakanRmBo) ctx.getBean("cetakanRmBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                CetakanRm cetakanRm = new CetakanRm();
                cetakanRm.setIdDetailCheckup(idDetailCheckup);
                cetakanRm.setKeterangan(keterangan);
                list = cetakanRmBo.getByCriteria(cetakanRm);
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
