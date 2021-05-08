package com.neurix.simrs.transaksi.hemodinamika.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodinamika.bo.HemodinamikaBo;
import com.neurix.simrs.transaksi.hemodinamika.model.Hemodinamika;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HemodinamikaAction {

    public static transient Logger logger = Logger.getLogger(HemodinamikaAction.class);

    public CrudResponse save(String data, String dataPasien){

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodinamikaBo hemodinamikaBo = (HemodinamikaBo) ctx.getBean("hemodinamikaBoProxy");

        try {
            JSONObject obj = new JSONObject(data);
            String idDetailCheckup = obj.getString("id_detail_checkup");
            String waktu = obj.getString("waktu");

            Hemodinamika hemodinamika = new Hemodinamika();
            hemodinamika.setIdDetailCheckup(idDetailCheckup);
            hemodinamika.setKeterangan(obj.getString("keterangan"));
            hemodinamika.setWaktu(waktu);

            if(!"".equalsIgnoreCase(obj.getString("tensi"))){
                hemodinamika.setTensi(obj.getString("tensi"));
            }
            if(!"".equalsIgnoreCase(obj.getString("st"))){
                hemodinamika.setSistole(new Integer(String.valueOf(obj.getString("st"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("dt"))){
                hemodinamika.setDiastole(new Integer(String.valueOf(obj.getString("dt"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("hi"))){
                hemodinamika.setHi(new Integer(String.valueOf(obj.getString("hi"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("rr"))){
                hemodinamika.setRr(new Integer(String.valueOf(obj.getString("rr"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("ekg"))){
                hemodinamika.setEkg(obj.getString("ekg"));
            }
            if(!"".equalsIgnoreCase(obj.getString("icp"))){
                hemodinamika.setIcp(new Integer(String.valueOf(obj.getString("icp"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("ibp"))){
                hemodinamika.setIbp(new Integer(String.valueOf(obj.getString("ibp"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("cvp"))){
                hemodinamika.setCvp(new Integer(String.valueOf(obj.getString("cvp"))));
            }
            if(!"".equalsIgnoreCase(obj.getString("map"))){
                hemodinamika.setMap(new Integer(String.valueOf(obj.getString("map"))));
            }

            hemodinamika.setAction("C");
            hemodinamika.setFlag("Y");
            hemodinamika.setCreatedWho(userLogin);
            hemodinamika.setCreatedDate(time);
            hemodinamika.setLastUpdateWho(userLogin);
            hemodinamika.setLastUpdate(time);

            Boolean cekData = hemodinamikaBo.cekData(idDetailCheckup, waktu);
            if(cekData){
                response.setStatus("error");
                response.setMsg("Data pada waktu "+waktu+" sudah ada...!");
            }else{
                try {
                    response = hemodinamikaBo.saveAdd(hemodinamika);
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
            response.setMsg("Found Errror when Parse JSON, "+e.getMessage());
        }
        return response;
    }

    public List<Hemodinamika> getListDetail(String idDetailCheckup, String keterangan, String tanggal) {
        List<Hemodinamika> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HemodinamikaBo hemodinamikaBo = (HemodinamikaBo) ctx.getBean("hemodinamikaBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(keterangan)) {
            try {
                Hemodinamika hemodinamika = new Hemodinamika();
                hemodinamika.setIdDetailCheckup(idDetailCheckup);
                hemodinamika.setKeterangan(keterangan);
                if(tanggal != null){
                    hemodinamika.setTanggal(Date.valueOf(tanggal));
                }
                list = hemodinamikaBo.getByCriteria(hemodinamika);
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
