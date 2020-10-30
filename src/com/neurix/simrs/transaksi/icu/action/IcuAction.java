package com.neurix.simrs.transaksi.icu.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodinamika.bo.HemodinamikaBo;
import com.neurix.simrs.transaksi.hemodinamika.model.Hemodinamika;
import com.neurix.simrs.transaksi.icu.bo.DetailIcuBo;
import com.neurix.simrs.transaksi.icu.bo.HeaderIcuBo;
import com.neurix.simrs.transaksi.icu.model.DetailIcu;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import com.neurix.simrs.transaksi.respirasi.bo.RespirasiBo;
import com.neurix.simrs.transaksi.respirasi.model.Respirasi;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IcuAction {

    public static transient Logger logger = Logger.getLogger(IcuAction.class);

    public CrudResponse save(String data, String dataPasien){

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");
        try {
            JSONArray json = new JSONArray(data);
            List<HeaderIcu> headerIcuList = new ArrayList<>();
            if(json != null){
                for (int i = 0; i < json.length(); i++) {

                    JSONObject obj = json.getJSONObject(i);
                    HeaderIcu headerIcu = new HeaderIcu();

                    if(obj.has("id_header_icu")){
                        headerIcu.setIdHeaderIcu(obj.getString("id_header_icu"));
                    }
                    if(obj.has("jenis")){
                        headerIcu.setJenis(obj.getString("jenis"));
                    }
                    if(obj.has("id_detail_icu")){
                        headerIcu.setIdDetailIcu(obj.getString("id_detail_icu"));
                    }
                    if(obj.has("is_new")){
                        headerIcu.setNew(Boolean.valueOf(obj.getString("is_new")));
                    }

                    headerIcu.setIdDetailCheckup(obj.getString("id_detail_checkup"));
                    headerIcu.setKategori(obj.getString("kategori"));
                    if(obj.has("nilai")){
                        headerIcu.setNilai(obj.getString("nilai"));
                    }
                    headerIcu.setWaktu(obj.getString("waktu"));
                    headerIcu.setAction("C");
                    headerIcu.setFlag("Y");
                    headerIcu.setCreatedWho(userLogin);
                    headerIcu.setCreatedDate(time);
                    headerIcu.setLastUpdateWho(userLogin);
                    headerIcu.setLastUpdate(time);
                    headerIcuList.add(headerIcu);
                }
            }else{
                response.setStatus("error");
                response.setMsg("[Found Error], Data JSON tidak ada...!");
            }

            if(headerIcuList.size() > 0){
                HeaderIcu headIcu = headerIcuList.get(0);
                Boolean cekData = headerIcuBo.cekData(headIcu.getIdDetailCheckup(), headIcu.getWaktu(), headIcu.getKategori());

                if(cekData){
                    response.setStatus("error");
                    response.setMsg("[Found Error], Data pada jam "+headIcu.getWaktu()+" Sudah ada dalam database...!");
                }else {
                    try {
                        response = headerIcuBo.saveAdd(headerIcuList);
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
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        return response;
                    }
                }
            }else{
                response.setStatus("error");
                response.setMsg("Tidak ada JSON data yang dikirim...!");
            }
        }catch (JSONException e){
            response.setStatus("error");
            response.setMsg("Erro when parse JSON, "+e.getMessage());
        }
        return response;
    }

    public List<HeaderIcu> getListDetailParameter(String idDetailCheckup, String kategori) {
        List<HeaderIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                HeaderIcu headerIcu = new HeaderIcu();
                headerIcu.setIdDetailCheckup(idDetailCheckup);
                headerIcu.setKategori(kategori);
                list = headerIcuBo.getByCriteria(headerIcu);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }

        return list;
    }

    public List<HeaderIcu> getListDetail(String idDetailCheckup, String kategori) {
        List<HeaderIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                list = headerIcuBo.getListDetail(idDetailCheckup, kategori);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }

        return list;
    }

    public List<HeaderIcu> getListHead(String idDetailCheckup, String kategori, String tgl) {
        List<HeaderIcu> list = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");

        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                list = headerIcuBo.getListHeadIcu(idDetailCheckup, kategori, tgl);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return list;
    }

    public CrudResponse saveDelete(String idIcu, String tipe) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");
        HemodinamikaBo hemodinamikaBo = (HemodinamikaBo) ctx.getBean("hemodinamikaBoProxy");
        RespirasiBo respirasiBo = (RespirasiBo) ctx.getBean("respirasiBoProxy");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        if (!"".equalsIgnoreCase(idIcu) && idIcu != null) {
            try {
                if("hemodinamika".equalsIgnoreCase(tipe)){
                    Hemodinamika hemodinamika = new Hemodinamika();
                    hemodinamika.setIdHemodinamika(idIcu);
                    hemodinamika.setLastUpdate(time);
                    hemodinamika.setLastUpdateWho(userLogin);
                    response = hemodinamikaBo.saveDelete(hemodinamika);
                }else if("respirasi".equalsIgnoreCase(tipe)){
                    Respirasi respirasi = new Respirasi();
                    respirasi.setIdRespirasi(idIcu);
                    respirasi.setLastUpdate(time);
                    respirasi.setLastUpdateWho(userLogin);
                    response = respirasiBo.saveDelete(respirasi);
                }else{
                    HeaderIcu headerIcu = new HeaderIcu();
                    headerIcu.setIdDetailIcu(idIcu);
                    headerIcu.setLastUpdate(time);
                    headerIcu.setLastUpdateWho(userLogin);
                    response = headerIcuBo.saveDelete(headerIcu);
                }
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }

        return response;
    }

    public CrudResponse editObat(String idHead, String idDetail, String nilai) {
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderIcuBo headerIcuBo = (HeaderIcuBo) ctx.getBean("headerIcuBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        if (!"".equalsIgnoreCase(idDetail) && !"".equalsIgnoreCase(idDetail) && !"".equalsIgnoreCase(nilai)) {
            HeaderIcu headerIcu = new HeaderIcu();
            headerIcu.setIdHeaderIcu(idHead);
            headerIcu.setIdDetailIcu(idDetail);
            headerIcu.setNilai(nilai);
            headerIcu.setLastUpdate(time);
            headerIcu.setLastUpdateWho(userLogin);

            try {
                response = headerIcuBo.editObat(headerIcu);
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg(e.getMessage());
                logger.error("Found Error" + e.getMessage());
            }
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }
}