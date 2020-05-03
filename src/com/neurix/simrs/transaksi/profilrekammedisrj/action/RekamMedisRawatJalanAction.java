package com.neurix.simrs.transaksi.profilrekammedisrj.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RekamMedisRawatJalanAction {

    public static transient Logger logger = Logger.getLogger(RekamMedisRawatJalanAction.class);

    public CrudResponse saveRekamMedisRJ(String data) throws JSONException, IOException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
        if (data != null) {
            JSONObject obj = new JSONObject(data);
            RekamMedisRawatJalan catatan = new RekamMedisRawatJalan();
            catatan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            catatan.setWaktu(Timestamp.valueOf(obj.getString("waktu")));
            catatan.setAnamnese(obj.getString("anamnese"));
            catatan.setPemeriksaanFisik(obj.getString("pemeriksaan_fisik"));
            catatan.setDiagnosa(obj.getString("diagnosa"));
            catatan.setPlaning(obj.getString("planing"));
            catatan.setKeterangan(obj.getString("keterangan"));
            catatan.setAction("C");
            catatan.setFlag("Y");
            catatan.setCreatedWho(userLogin);
            catatan.setCreatedDate(time);
            catatan.setLastUpdateWho(userLogin);
            catatan.setLastUpdate(time);

            try {
                response = rekamMedisRawatJalanBo.saveAdd(catatan);
            }catch (GeneralBOException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }
        return response;
    }

    public List<RekamMedisRawatJalan> getListRekamMedisRJ(String idDetailCheckup) {
        List<RekamMedisRawatJalan> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                RekamMedisRawatJalan catatan = new RekamMedisRawatJalan();
                catatan.setIdDetailCheckup(idDetailCheckup);
                list = rekamMedisRawatJalanBo.getByCriteria(catatan);
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
