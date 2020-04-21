package com.neurix.simrs.transaksi.hemodialisa.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.MonitoringTransfusiDarahBo;
import com.neurix.simrs.transaksi.hemodialisa.model.MonitoringTransfusiDarah;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MonitoringTransfusiDarahAction {

    public static transient Logger logger = Logger.getLogger(MonitoringTransfusiDarahAction.class);

    public CrudResponse saveMonitoringTransfusiDarah(String data) throws JSONException {
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonitoringTransfusiDarahBo monitoringTransfusiDarahBo = (MonitoringTransfusiDarahBo) ctx.getBean("monitoringTransfusiDarahBoProxy");

        JSONObject obj = new JSONObject(data);

        if(obj != null){
            MonitoringTransfusiDarah transfusiDarah = new MonitoringTransfusiDarah();
            transfusiDarah.setIdDetailCheckup(obj.getString("id_detail_checkup"));
            transfusiDarah.setWaktu(obj.getString("waktu"));
            transfusiDarah.setTekananDarah(obj.getString("tekanan_darah"));
            transfusiDarah.setNadi(obj.getString("nadi"));
            transfusiDarah.setSuhu(obj.getString("suhu"));
            transfusiDarah.setRr(obj.getString("rr"));
            transfusiDarah.setTidakAdaReaksi(obj.getString("tidak_ada_reaksi"));
            transfusiDarah.setGatal(obj.getString("gatal"));
            transfusiDarah.setUrtikariaBeratSedang(obj.getString("urtikaria_berat_sedang"));
            transfusiDarah.setKulitKemerahanSedang(obj.getString("kulit_kemerahan_sedang"));
            transfusiDarah.setDemamSedang(obj.getString("demam_sedang"));
            transfusiDarah.setMenggigilSedang(obj.getString("menggigil_sedang"));
            transfusiDarah.setGelisahSedang(obj.getString("gelisah_sedang"));
            transfusiDarah.setPeningkatanDetakJantungSedang(obj.getString("peningkatan_detak_jantung_sedang"));
            transfusiDarah.setDemamMengancam(obj.getString("demam_mengancam"));
            transfusiDarah.setMenggigilMengancam(obj.getString("menggigil_mengancam"));
            transfusiDarah.setGelisahMengancam(obj.getString("gelisah_mengancam"));
            transfusiDarah.setPeningkatanDetakJantungMengancam(obj.getString("peningkatan_detak_jantung_mengancam"));
            transfusiDarah.setNafasCepatMengancam(obj.getString("nafas_cepat_mengancam"));
            transfusiDarah.setUrinMengancam(obj.getString("urin_mengancam"));
            transfusiDarah.setPendarahanJantungMengancam(obj.getString("pendarahan_jantung_mengancam"));
            transfusiDarah.setKesadaranMengancam(obj.getString("kesadaran_mengancam"));
            transfusiDarah.setKeterangan(obj.getString("keterangan"));
            transfusiDarah.setAction("C");
            transfusiDarah.setFlag("Y");
            transfusiDarah.setCreatedWho(userLogin);
            transfusiDarah.setCreatedDate(time);
            transfusiDarah.setLastUpdateWho(userLogin);
            transfusiDarah.setLastUpdate(time);
            try {
                response = monitoringTransfusiDarahBo.saveAdd(transfusiDarah);
            } catch (GeneralBOException e) {
                response.setStatus("Error");
                response.setMsg("Found Error " + e.getMessage());
                return response;
            }
        }
        return response;
    }

    public List<MonitoringTransfusiDarah> getListMonitoringTransfusiDarah(String idDetailCheckup) {
        List<MonitoringTransfusiDarah> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonitoringTransfusiDarahBo monitoringTransfusiDarahBo = (MonitoringTransfusiDarahBo) ctx.getBean("monitoringTransfusiDarahBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                MonitoringTransfusiDarah transfusiDarah = new MonitoringTransfusiDarah();
                transfusiDarah.setIdDetailCheckup(idDetailCheckup);
                list = monitoringTransfusiDarahBo.getByCriteria(transfusiDarah);
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