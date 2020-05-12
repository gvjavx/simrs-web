package com.neurix.simrs.transaksi.pengkajiankeperawatan.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.bo.PengkajianUlangKeperawatanBo;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.PengkajianUlangKeperawatan;
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

    public CrudResponse savePengkajianKeperawatan(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PengkajianUlangKeperawatanBo pengkajianUlangKeperawatanBo = (PengkajianUlangKeperawatanBo) ctx.getBean("pengkajianUlangKeperawatanBoProxy");
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
        } catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Found Error " + e.getMessage());
            return response;
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

    public static Logger getLogger() {
        return logger;
    }
}
