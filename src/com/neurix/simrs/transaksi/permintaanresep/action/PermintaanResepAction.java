package com.neurix.simrs.transaksi.permintaanresep.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.obatracik.model.ObatRacik;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.permintaanresep.model.ResepObat;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.transketeranganobat.model.ItSimrsKeteranganResepEntity;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import org.directwebremoting.json.types.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepAction extends BaseMasterAction{
    private static transient Logger logger = Logger.getLogger(PermintaanResepAction.class);

    private PermintaanResep permintaanResep;
    private PermintaanResepBo permintaanResepBoProxy;
    private TransaksiObatBo transaksiObatBoProxy;

    public void setTransaksiObatBoProxy(TransaksiObatBo transaksiObatBoProxy) {
        this.transaksiObatBoProxy = transaksiObatBoProxy;
    }
    public PermintaanResep getPermintaanResep() {
        return permintaanResep;
    }

    public void setPermintaanResep(PermintaanResep permintaanResep) {
        this.permintaanResep = permintaanResep;
    }

    public PermintaanResepBo getPermintaanResepBoProxy() {
        return permintaanResepBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }

    public CrudResponse saveResepPasien(String data) {
        CrudResponse response = new CrudResponse();
        logger.info("[PermintaanResepAction.saveResepPasien] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

            if(data != null && !"".equalsIgnoreCase(data)){
                try {
                    PermintaanResep permintaanResep = new PermintaanResep();
                    JSONObject obj = new JSONObject(data);
                    if(obj != null){
                        permintaanResep.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                        permintaanResep.setIdDokter(obj.getString("id_dokter"));
                        permintaanResep.setIdPelayanan(obj.getString("id_pelayanan"));
                        permintaanResep.setIdPasien(obj.getString("id_pasien"));
                        permintaanResep.setCreatedWho(userLogin);
                        permintaanResep.setLastUpdate(updateTime);
                        permintaanResep.setCreatedDate(updateTime);
                        permintaanResep.setLastUpdateWho(userLogin);
                        permintaanResep.setAction("U");
                        permintaanResep.setFlag("Y");
                        permintaanResep.setBranchId(userArea);
                        permintaanResep.setTujuanPelayanan(obj.getString("id_apotek"));
                        if(obj.getString("ttd") != null && !"".equalsIgnoreCase(obj.getString("ttd"))){
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(obj.getString("ttd"));
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String fileName = obj.getString("id_dokter")+"-"+obj.getString("id_detail_checkup")+"-"+dateFormater("MM")+dateFormater("yy")+".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+fileName;
                            logger.info("File save path : " + uploadFile);
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                            if (image == null) {
                                logger.error("Buffered Image is null");
                            }else{
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                permintaanResep.setTtdDokter(fileName);
                            }
                        }

                        List<ObatRacik> listNamaRacik = new ArrayList<>();
                        if (!"".equalsIgnoreCase(obj.getString("arr_nama_racik"))){
                            JSONArray arrNamaRacik = new JSONArray(obj.getString("arr_nama_racik"));
                            for (int i=0; i < arrNamaRacik.length(); i++){
                                JSONObject objNamaRacik = arrNamaRacik.getJSONObject(i);
                                ObatRacik obatRacik = new ObatRacik();
                                obatRacik.setId(objNamaRacik.getString("idracik"));
                                obatRacik.setNama(objNamaRacik.getString("racik"));
                                obatRacik.setSigna(objNamaRacik.getString("signa"));
                                obatRacik.setQty(new Integer(objNamaRacik.getString("qty")));
                                obatRacik.setKemasan(objNamaRacik.getString("kemasan"));
                                obatRacik.setWarna(objNamaRacik.getString("warna"));
                                listNamaRacik.add(obatRacik);
                            }
                        }

                        List<TransaksiObatDetail> listDetailObatRacik = new ArrayList<>();
                        if (!"".equalsIgnoreCase(obj.getString("arr_detail_racik"))){
                            JSONArray arrNamaRacik = new JSONArray(obj.getString("arr_detail_racik"));
                            for (int i=0; i < arrNamaRacik.length(); i++){
                                JSONObject objNamaRacik = arrNamaRacik.getJSONObject(i);
                                TransaksiObatDetail obatRacik = new TransaksiObatDetail();
                                obatRacik.setIdObat(objNamaRacik.getString("idobat"));
                                obatRacik.setNamaObat(objNamaRacik.getString("namaobat"));
                                obatRacik.setKeterangan(objNamaRacik.getString("dosis"));
                                obatRacik.setQty(new BigInteger(String.valueOf(0)));
                                obatRacik.setIdRacik(objNamaRacik.getString("idracik"));
                                listDetailObatRacik.add(obatRacik);
                            }
                        }

                        permintaanResep.setListNamaObatRacik(listNamaRacik);
                        permintaanResep.setListDetailObatRacik(listDetailObatRacik);

                        List<TransaksiObatDetail> detailList = new ArrayList<>();
                        if(obj.getString("data_obat") != null && !"".equalsIgnoreCase(obj.getString("data_obat"))){
                            JSONArray json = new JSONArray(obj.getString("data_obat"));
                            for (int i=0; i < json.length(); i++){
                                JSONObject objt = json.getJSONObject(i);
                                TransaksiObatDetail detail = new TransaksiObatDetail();
                                detail.setIdObat(objt.getString("id_obat"));
                                detail.setQty(new BigInteger(objt.getString("qty")));
                                detail.setKeterangan(objt.getString("keterangan"));
                                detail.setJenisSatuan(objt.getString("jenis_satuan"));
                                detail.setJenisResep(objt.getString("jenis_resep"));
                                if ("Y".equalsIgnoreCase(objt.getString("is_racik"))){
                                    detail.setFlagRacik(objt.getString("is_racik"));
                                    detail.setNamaRacik(objt.getString("nama_racik"));
                                    detail.setIdRacik(objt.getString("id_racik"));
                                } else {
                                    detail.setFlagRacik("");
                                }
                                if (objt.getString("hari_kronis") != null  && !"".equalsIgnoreCase(objt.getString("hari_kronis"))){
                                    detail.setHariKronis(new Integer(objt.getString("hari_kronis")));
                                }

                                if(objt.has("waktu_resep")){
                                    detail.setFrekuensi(objt.getString("waktu_resep"));
                                }


                                List<ItSimrsKeteranganResepEntity> resepEntityList = new ArrayList<>();
                                if(objt.has("keterangan_detail")){
                                    if(objt.getString("keterangan_detail") != null && !"".equalsIgnoreCase(objt.getString("keterangan_detail"))){
                                        JSONArray jsoon = new JSONArray(objt.getString("keterangan_detail"));
                                        for (int k=0; k < jsoon.length(); k++) {
                                            JSONObject ob = jsoon.getJSONObject(k);
                                            ItSimrsKeteranganResepEntity resepEntity = new ItSimrsKeteranganResepEntity();
                                            resepEntity.setIdKeteranganObat(ob.getString("id_waktu"));
                                            resepEntity.setKeteranganLain(ob.getString("keterangan"));
                                            resepEntity.setCreatedDate(updateTime);
                                            resepEntity.setCreatedWho(userArea);
                                            resepEntity.setLastUpdate(updateTime);
                                            resepEntity.setLastUpdateWho(userArea);
                                            resepEntity.setAction("C");
                                            resepEntity.setFlag("Y");
                                            resepEntityList.add(resepEntity);
                                        }
                                    }
                                }
                                detail.setKeteranganResepEntityList(resepEntityList);
                                detailList.add(detail);
                            }
                        }
                        response = permintaanResepBo.saveAdd(permintaanResep, detailList);
                        insertProfilRJ(obj.getString("id_detail_checkup"));
                    }else{
                        response.setStatus("error");
                        response.setMsg("Object yang dikirm tidak ada...!");
                    }
                }catch (Exception e){
                    response.setStatus("error");
                    response.setMsg("[PermintaanResepAction.saveResepPasien] Error when adding item, "+e.getMessage());
                }
            }
        }catch (Exception e) {
            logger.error("[PermintaanResepAction.saveResepPasien] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            response.setStatus("error");
            response.setMsg(e.getMessage());
        }
        return response;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    public List<PermintaanResep> listResepPasien(String IdChekupDetail){

        logger.info("[PermintaanResepAction.listTindakanRawat] start process >>>");
        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        PermintaanResep permintaanResep = new PermintaanResep();
        permintaanResep.setIdDetailCheckup(IdChekupDetail);
//        permintaanResep.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        if(!"".equalsIgnoreCase(IdChekupDetail)){
            try {
                permintaanResepList = permintaanResepBo.getByCriteria(permintaanResep);
            }catch (GeneralBOException e){
                logger.error("[PermintaanResepAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanResepAction.saveTindakanRawat] start process >>>");
            return permintaanResepList;

        }else{
            return null;
        }
    }

    public List<TransaksiObatDetail> listDetail(String idApprovalObat){

        logger.info("[PermintaanResepAction.listDetail] start process >>>");
        List<TransaksiObatDetail> transaksiObatDetailList = new ArrayList<>();
        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdApprovalObat(idApprovalObat);
//        transaksiObatDetail.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

        if(!"".equalsIgnoreCase(idApprovalObat)){
            try {
                transaksiObatDetailList = transaksiObatBo.getByCriteria(transaksiObatDetail);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listDetail] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanResepAction.listDetail] start process >>>");
            return transaksiObatDetailList;

        }else{
            return null;
        }
    }

    public String saveEditDetail(String idTransaksi, String idObat, BigInteger qty, String ket){
        logger.info("[PermintaanResepAction.saveDetailResep] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();

            transaksiObatDetail.setIdTransaksiObatDetail(idTransaksi);
            transaksiObatDetail.setIdObat(idObat);
            transaksiObatDetail.setQty(qty);
            transaksiObatDetail.setKeterangan(ket);
            transaksiObatDetail.setLastUpdate(updateTime);
            transaksiObatDetail.setLastUpdateWho(userLogin);
            transaksiObatDetail.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");

            transaksiObatBo.saveEditDetail(transaksiObatDetail);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanResepAction.saveDetailResep] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public CrudResponse insertProfilRJ(String idDetailCheckup){
        CrudResponse response = new CrudResponse();
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPlaning(checkupBo.getResepPasien(idDetailCheckup));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPlaning(checkupBo.getResepPasien(idDetailCheckup));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
        return response;
    }

    public List<PermintaanResep> getListRespPasien(String noCheckup, String jenis){
        logger.info("[PermintaanResepAction.getListRespPasien] start process >>>");
        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        if(!"".equalsIgnoreCase(noCheckup) && noCheckup != null){
            try {
                permintaanResepList = permintaanResepBo.getListResepPasien(noCheckup, jenis);
            }catch (GeneralBOException e){
                logger.error("[PermintaanResepAction.getListRespPasien] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

        }
        logger.info("[PermintaanResepAction.getListRespPasien] END process >>>");
        return permintaanResepList;
    }

    public List<PermintaanResep> getListResepTerakhir(String idPasien, String idPelayanan){
        logger.info("[PermintaanResepAction.getListResepTerakhir] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        try {
            permintaanResepList = permintaanResepBo.getListResepTerakhirByIdPelayanan(idPasien, idPelayanan);
        } catch (GeneralBOException e){
            logger.error("[PermintaanResepAction.getListResepTerakhir] Error.", e);

        }

        logger.info("[PermintaanResepAction.getListResepTerakhir] END process >>>");
        return permintaanResepList;
    }

    public List<PermintaanResep> getListResepTerakhirByIdApproval(String idApproval){
        logger.info("[PermintaanResepAction.getListResepTerakhirByIdApproval] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        List<PermintaanResep> permintaanResepList = new ArrayList<>();
        try {
            permintaanResepList = permintaanResepBo.getListResepTerakhirByIdApproval(idApproval);
        } catch (GeneralBOException e){
            logger.error("[PermintaanResepAction.getListResepTerakhirByIdApproval] Error.", e);
        }

        logger.info("[PermintaanResepAction.getListResepTerakhirByIdApproval] END process >>>");
        return permintaanResepList;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
