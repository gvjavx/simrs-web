package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.action;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.askep.bo.DetailAsuhanKeperawatanBo;
import com.neurix.simrs.master.askep.bo.DiagnosaAsuhanKeperawatanBo;
import com.neurix.simrs.master.askep.model.DetailAsuhanKeperawatan;
import com.neurix.simrs.master.askep.model.DiagnosaAsuhanKeperawatan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
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

    public CrudResponse save(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RencanaAsuhanKeperawatanBo rencanaAsuhanKeperawatanBo = (RencanaAsuhanKeperawatanBo) ctx.getBean("rencanaAsuhanKeperawatanBoProxy");
        String ttdDokter = "";

        try {
            if (data != null) {
                JSONObject obj = new JSONObject(data);
                RencanaAsuhanKeperawatan catatan = new RencanaAsuhanKeperawatan();
                catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                catatan.setWaktu(obj.getString("waktu"));
                catatan.setDiagnosa(obj.getString("diagnosa"));
                catatan.setHasil(obj.getString("hasil"));
                catatan.setIntervensi(obj.getString("intervensi"));
                catatan.setImplementasi(obj.getString("implementasi"));
                catatan.setEvaluasi(obj.getString("evaluasi"));
                catatan.setKeterangan(obj.getString("keterangan"));
                if(obj.has("nama_terang")){
                    catatan.setNamaTerang(obj.getString("nama_terang"));
                }
                if(obj.has("ttd_dpjp")){
                    if(!"".equalsIgnoreCase(obj.getString("ttd_dpjp")) && obj.getString("ttd_dpjp") != null){
                        ttdDokter = obj.getString("ttd_dpjp");
                    }
                }
                if(obj.has("nama_dokter")){
                    catatan.setNamaDokter(obj.getString("nama_dokter"));
                }
                if(obj.has("sip")){
                    catatan.setSip(obj.getString("sip"));
                }
                if (obj.has("ttd_perawat")) {
                    if (!"".equalsIgnoreCase(obj.getString("ttd_perawat"))) {
                        try {
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
                            if(!"".equalsIgnoreCase(ttdDokter)){
                                BASE64Decoder decoder2 = new BASE64Decoder();
                                byte[] decodedBytes2 = decoder2.decodeBuffer(ttdDokter);
                                String fileName2 = obj.getString("id_detail_checkup") + "-" + "ttd_dpjp" + "-" + patten + ".png";
                                String uploadFile2 = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_RM + fileName2;
                                BufferedImage image2 = ImageIO.read(new ByteArrayInputStream(decodedBytes2));
                                if (image2 == null) {
                                    logger.error("Buffered Image is null");
                                    response.setStatus("error");
                                    response.setMsg("Buffered Image is null");
                                } else {
                                    File f2 = new File(uploadFile2);
                                    ImageIO.write(image2, "png", f2);
                                    catatan.setTtdDokter(fileName2);
                                }
                            }
                        }catch (IOException e){
                            response.setStatus("error");
                            response.setMsg("Error when parse IO Imgaes, "+e.getMessage());
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
                } catch (GeneralBOException e) {
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg("Error when parse JSON, "+e.getMessage());
        }
        return response;
    }

    public List<RencanaAsuhanKeperawatan> getListDetail(String idDetailCheckup, String keterangan) {
        List<RencanaAsuhanKeperawatan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RencanaAsuhanKeperawatanBo rencanaAsuhanKeperawatanBo = (RencanaAsuhanKeperawatanBo) ctx.getBean("rencanaAsuhanKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                RencanaAsuhanKeperawatan catatan = new RencanaAsuhanKeperawatan();
                catatan.setIdDetailCheckup(idDetailCheckup);
                catatan.setKeterangan(keterangan);
                list = rencanaAsuhanKeperawatanBo.getByCriteria(catatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public List<DiagnosaAsuhanKeperawatan> getDiagnosaAskep(String key, String tipe) {
        List<DiagnosaAsuhanKeperawatan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaAsuhanKeperawatanBo diagnosaAsuhanKeperawatanBo = (DiagnosaAsuhanKeperawatanBo) ctx.getBean("diagnosaAsuhanKeperawatanBoProxy");
        if(!"".equalsIgnoreCase(key) && key != null && !"".equalsIgnoreCase(tipe)){
            try {
                list = diagnosaAsuhanKeperawatanBo.getListDiagnosa(key, tipe);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public List<DetailAsuhanKeperawatan> getListDetailDiagnosaAskep(String idDiagnosa) {
        List<DetailAsuhanKeperawatan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DetailAsuhanKeperawatanBo detailAsuhanKeperawatanBo = (DetailAsuhanKeperawatanBo) ctx.getBean("detailAsuhanKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(idDiagnosa) && idDiagnosa != null) {
            try {
                DetailAsuhanKeperawatan detailAsuhanKeperawatan = new DetailAsuhanKeperawatan();
                detailAsuhanKeperawatan.setIdDiagnosaAsuhanKeperawatan(idDiagnosa);
                list = detailAsuhanKeperawatanBo.getByCriteria(detailAsuhanKeperawatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idAsesmen) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RencanaAsuhanKeperawatanBo rencanaAsuhanKeperawatanBo = (RencanaAsuhanKeperawatanBo) ctx.getBean("rencanaAsuhanKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(idAsesmen) && idAsesmen != null) {
            try {
                RencanaAsuhanKeperawatan rencanaAsuhanKeperawatan = new RencanaAsuhanKeperawatan();
                rencanaAsuhanKeperawatan.setIdRencanaAsuhanKeperawatan(idAsesmen);
                rencanaAsuhanKeperawatan.setLastUpdate(time);
                rencanaAsuhanKeperawatan.setLastUpdateWho(userLogin);
                response = rencanaAsuhanKeperawatanBo.saveDelete(rencanaAsuhanKeperawatan);
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
