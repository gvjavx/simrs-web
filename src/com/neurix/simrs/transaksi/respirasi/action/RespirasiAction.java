package com.neurix.simrs.transaksi.respirasi.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import com.neurix.simrs.transaksi.respirasi.bo.RespirasiBo;
import com.neurix.simrs.transaksi.respirasi.model.Respirasi;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RespirasiAction {

    public static transient Logger logger = Logger.getLogger(RespirasiAction.class);

    public CrudResponse save(String data, String dataPasien){

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RespirasiBo respirasiBo = (RespirasiBo) ctx.getBean("respirasiBoProxy");

        try {
            JSONObject obj = new JSONObject(data);
            String idDetailCheckup = obj.getString("id_detail_checkup");
            String waktu = obj.getString("waktu");

            Respirasi respirasi = new Respirasi();
            respirasi.setIdDetailCheckup(idDetailCheckup);
            respirasi.setKeterangan(obj.getString("keterangan"));
            respirasi.setWaktu(waktu);

            respirasi.setGcs(obj.getString("gcs"));
            respirasi.setDiameterPupil(obj.getString("diameter_pupil"));
            respirasi.setReflekCahaya(obj.getString("reflek_cahaya"));
            respirasi.setTk(obj.getString("tk"));
            respirasi.setKk(obj.getString("kk"));
            respirasi.setJenisNkRmNrm(obj.getString("jenis_nk_rm_nrm"));
            respirasi.setNkRmNrm(obj.getString("nk_rm_nrm"));
            respirasi.setJenisTPieceJRise(obj.getString("jenis_t_piece_j_rise"));
            respirasi.settPieceJRise(obj.getString("t_piece_j_rise"));
            respirasi.setTipeVentilasi(obj.getString("tipe_ventilasi"));
            respirasi.setJenisPeepCpapEt(obj.getString("jenis_peep_cpap_et"));
            respirasi.setPeepCpapEt(obj.getString("peep_cpap_et"));
            respirasi.setFrekwensi(obj.getString("frekwensi"));
            respirasi.setTv(obj.getString("tv"));
            respirasi.setMv(obj.getString("mv"));
            respirasi.setJenisPSupportPAsb(obj.getString("jenis_p_support_p_asb"));
            respirasi.setpSupportPAsb(obj.getString("p_support_p_asb"));
            respirasi.setJenisPInsPCon(obj.getString("jenis_p_ins_p_con"));
            respirasi.setpInsPCon(obj.getString("p_ins_p_con"));
            respirasi.setTriger(obj.getString("triger"));
            respirasi.setInsTime(obj.getString("ins_time"));
            respirasi.setFlow(obj.getString("flow"));
            respirasi.setJenisFioKon(obj.getString("jenis_fio_kon"));
            respirasi.setFioKon(obj.getString("fio_kon"));
            respirasi.setJenisUkuranKedalamaanEtt(obj.getString("jenis_ukuran_kedalamaan_ett"));
            respirasi.setUkuranKedalamaanEtt(obj.getString("ukuran_kedalamaan_ett"));
            respirasi.setSpo(obj.getString("spo"));
            respirasi.setSecret(obj.getString("secret"));

            respirasi.setAction("C");
            respirasi.setFlag("Y");
            respirasi.setCreatedWho(userLogin);
            respirasi.setCreatedDate(time);
            respirasi.setLastUpdateWho(userLogin);
            respirasi.setLastUpdate(time);

            Boolean cekData = respirasiBo.cekData(idDetailCheckup, waktu);
            if(cekData){
                response.setStatus("error");
                response.setMsg("Data pada waktu "+waktu+" sudah ada...!");
            }else{
                try {
                    response = respirasiBo.saveAdd(respirasi);
                    if("success".equalsIgnoreCase(response.getStatus())){
                        RekamMedikBo rekamMedikBo = (RekamMedikBo) ctx.getBean("rekamMedikBoProxy");
                        JSONObject objt = new JSONObject(dataPasien);
                        if(objt != null){
                            StatusPengisianRekamMedis status = new StatusPengisianRekamMedis();
                            status.setNoCheckup(objt.getString("no_checkup"));
                            status.setIdDetailCheckup(objt.getString("id_detail_checkup"));
                            status.setIdPasien(objt.getString("id_pasien"));
                            status.setIdRekamMedisPasien(objt.getString("id_rm"));
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
            response.setStatus("error");
            response.setMsg("Found Erro when JSON parse, "+e.getMessage());
        }
        return response;
    }

    public List<Respirasi> getListDetail(String idDetailCheckup, String keterangan) {
        List<Respirasi> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RespirasiBo respirasiBo = (RespirasiBo) ctx.getBean("respirasiBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Respirasi respirasi = new Respirasi();
                respirasi.setIdDetailCheckup(idDetailCheckup);
                respirasi.setKeterangan(keterangan);
                list = respirasiBo.getByCriteria(respirasi);
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
