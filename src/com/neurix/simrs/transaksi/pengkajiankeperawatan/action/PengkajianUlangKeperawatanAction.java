package com.neurix.simrs.transaksi.pengkajiankeperawatan.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.bo.PengkajianUlangKeperawatanBo;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.PengkajianUlangKeperawatan;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PengkajianUlangKeperawatanAction {

    public static transient Logger logger = Logger.getLogger(PengkajianUlangKeperawatanAction.class);

    public CrudResponse savePengkajianKeperawatan(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengkajianUlangKeperawatanBo pengkajianUlangKeperawatanBo = (PengkajianUlangKeperawatanBo) ctx.getBean("pengkajianUlangKeperawatanBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<PengkajianUlangKeperawatan> list = new ArrayList<>();
            PengkajianUlangKeperawatan pengkajian = new PengkajianUlangKeperawatan();
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                PengkajianUlangKeperawatan keperawatan = new PengkajianUlangKeperawatan();
                keperawatan.setParameter(obj.getString("parameter"));
                keperawatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                keperawatan.setKeterangan(obj.getString("keterangan"));
                keperawatan.setTanggal(Date.valueOf(obj.getString("tanggal")));
                keperawatan.setKodeParameter(obj.getString("kode"));
                keperawatan.setWaktu(obj.getString("waktu"));
                if (obj.has("jawaban")) {
                    if ("pagi".equalsIgnoreCase(obj.getString("waktu"))) {
                        keperawatan.setPagi(obj.getString("jawaban"));
                    }
                    if ("siang".equalsIgnoreCase(obj.getString("waktu"))) {
                        keperawatan.setSiang(obj.getString("jawaban"));
                    }
                    if ("malam".equalsIgnoreCase(obj.getString("waktu"))) {
                        keperawatan.setMalam(obj.getString("jawaban"));
                    }
                }
                if (obj.has("jenis")) {
                    keperawatan.setJenis(obj.getString("jenis"));
                    pengkajian.setJenis(obj.getString("jenis"));
                }

                keperawatan.setAction("C");
                keperawatan.setFlag("Y");
                keperawatan.setCreatedWho(userLogin);
                keperawatan.setCreatedDate(time);
                keperawatan.setLastUpdateWho(userLogin);
                keperawatan.setLastUpdate(time);
                list.add(keperawatan);

                pengkajian.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                pengkajian.setKeterangan(obj.getString("keterangan"));
                pengkajian.setTanggal(Date.valueOf(obj.getString("tanggal")));
                pengkajian.setWaktu(obj.getString("waktu"));
            }

            try {
                response = pengkajianUlangKeperawatanBo.saveAdd(pengkajian, list);
                if("success".equalsIgnoreCase(response.getStatus())){
                    RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                    JSONObject obj = new JSONObject(dataPasien);
                    if(obj != null){
                        StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                        status.setNoCheckup(obj.getString("no_checkup"));
                        status.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                        status.setIdPasien(obj.getString("id_pasien"));
                        status.setIdRekamMedisPasien(obj.getString("id_rm"));
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
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg("Found Error Parse JSON Parse, "+e.getMessage());
        }
        return response;
    }

    public List<PengkajianUlangKeperawatan> getListPengkajianKeperawatan(String idDetailCheckup, String keterangan) {
        List<PengkajianUlangKeperawatan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengkajianUlangKeperawatanBo pengkajianUlangKeperawatanBo = (PengkajianUlangKeperawatanBo) ctx.getBean("pengkajianUlangKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                PengkajianUlangKeperawatan keperawatan = new PengkajianUlangKeperawatan();
                keperawatan.setIdDetailCheckup(idDetailCheckup);
                keperawatan.setKeterangan(keterangan);
                list = pengkajianUlangKeperawatanBo.getByCriteria(keperawatan);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String waktu, String idAsesmen, String dataPasien) {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengkajianUlangKeperawatanBo pengkajianUlangKeperawatanBo = (PengkajianUlangKeperawatanBo) ctx.getBean("pengkajianUlangKeperawatanBoProxy");
        if (!"".equalsIgnoreCase(waktu) && !"".equalsIgnoreCase(idAsesmen)) {
            try {
                PengkajianUlangKeperawatan keperawatan = new PengkajianUlangKeperawatan();
                keperawatan.setWaktu(waktu);
                keperawatan.setIdPengkajianUlangKeperawatan(idAsesmen);
                keperawatan.setLastUpdate(time);
                keperawatan.setLastUpdateWho(userLogin);
                response = pengkajianUlangKeperawatanBo.saveDelete(keperawatan);
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
