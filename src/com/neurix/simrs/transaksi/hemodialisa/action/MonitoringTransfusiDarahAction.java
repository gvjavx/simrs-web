package com.neurix.simrs.transaksi.hemodialisa.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.bo.MonitoringTransfusiDarahBo;
import com.neurix.simrs.transaksi.hemodialisa.model.MonitoringTransfusiDarah;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
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

    public CrudResponse saveMonitoringTransfusiDarah(String data, String dataPasien){
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MonitoringTransfusiDarahBo monitoringTransfusiDarahBo = (MonitoringTransfusiDarahBo) ctx.getBean("monitoringTransfusiDarahBoProxy");

        try {
            JSONObject obj = new JSONObject(data);
            if(obj != null) {
                MonitoringTransfusiDarah transfusiDarah = new MonitoringTransfusiDarah();
                transfusiDarah.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                transfusiDarah.setWaktu(obj.getString("waktu"));
                transfusiDarah.setTekananDarah(obj.getString("tekanan_darah"));
                transfusiDarah.setNadi(obj.getString("nadi"));
                transfusiDarah.setSuhu(obj.getString("suhu"));
                transfusiDarah.setRr(obj.getString("rr"));
                if(obj.has("tidak_ada_reaksi")){
                    transfusiDarah.setTidakAdaReaksi(obj.getString("tidak_ada_reaksi"));
                }
                if(obj.has("gatal")){
                    transfusiDarah.setGatal(obj.getString("gatal"));
                }
                if(obj.has("urtikaria_berat_sedang")){
                    transfusiDarah.setUrtikariaBeratSedang(obj.getString("urtikaria_berat_sedang"));
                }
                if(obj.has("kulit_kemerahan_sedang")){
                    transfusiDarah.setKulitKemerahanSedang(obj.getString("kulit_kemerahan_sedang"));
                }
                if(obj.has("demam_sedang")){
                    transfusiDarah.setDemamSedang(obj.getString("demam_sedang"));
                }
                if(obj.has("menggigil_sedang")){
                    transfusiDarah.setMenggigilSedang(obj.getString("menggigil_sedang"));
                }
                if(obj.has("gelisah_sedang")){
                    transfusiDarah.setGelisahSedang(obj.getString("gelisah_sedang"));
                }
                if(obj.has("peningkatan_detak_jantung_sedang")){
                    transfusiDarah.setPeningkatanDetakJantungSedang(obj.getString("peningkatan_detak_jantung_sedang"));
                }
                if(obj.has("demam_mengancam")){
                    transfusiDarah.setDemamMengancam(obj.getString("demam_mengancam"));
                }
                if(obj.has("menggigil_mengancam")){
                    transfusiDarah.setMenggigilMengancam(obj.getString("menggigil_mengancam"));
                }
                if(obj.has("gelisah_mengancam")){
                    transfusiDarah.setGelisahMengancam(obj.getString("gelisah_mengancam"));
                }
                if(obj.has("peningkatan_detak_jantung_mengancam")){
                    transfusiDarah.setPeningkatanDetakJantungMengancam(obj.getString("peningkatan_detak_jantung_mengancam"));
                }
                if(obj.has("nafas_cepat_mengancam")){
                    transfusiDarah.setNafasCepatMengancam(obj.getString("nafas_cepat_mengancam"));
                }
                if(obj.has("urin_mengancam")){
                    transfusiDarah.setUrinMengancam(obj.getString("urin_mengancam"));
                }
                if(obj.has("pendarahan_jantung_mengancam")){
                    transfusiDarah.setPendarahanJantungMengancam(obj.getString("pendarahan_jantung_mengancam"));
                }
                if(obj.has("kesadaran_mengancam")){
                    transfusiDarah.setKesadaranMengancam(obj.getString("kesadaran_mengancam"));
                }
                if(obj.has("keterangan")){
                    transfusiDarah.setKeterangan(obj.getString("keterangan"));
                }
                transfusiDarah.setAction("C");
                transfusiDarah.setFlag("Y");
                transfusiDarah.setCreatedWho(userLogin);
                transfusiDarah.setCreatedDate(time);
                transfusiDarah.setLastUpdateWho(userLogin);
                transfusiDarah.setLastUpdate(time);
                try {
                    response = monitoringTransfusiDarahBo.saveAdd(transfusiDarah);
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
                    response.setStatus("Error");
                    response.setMsg("Found Error " + e.getMessage());
                    return response;
                }
            }
        }catch (JSONException e){

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